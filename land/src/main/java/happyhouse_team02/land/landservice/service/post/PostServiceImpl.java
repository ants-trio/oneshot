package happyhouse_team02.land.landservice.service.post;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import happyhouse_team02.land.landservice.domain.Member;
import happyhouse_team02.land.landservice.domain.Post;
import happyhouse_team02.land.landservice.domain.UploadFile;
import happyhouse_team02.land.landservice.exception.NoSuchPostException;
import happyhouse_team02.land.landservice.repository.PostRepository;
import happyhouse_team02.land.landservice.service.member.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final MemberService memberService;
	private final PostRepository postRepository;

	@Override
	public Page<Post> findPostPages(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Post findOneWithComment(Long postId) {
		return postRepository.findDistinctById(postId).orElseThrow(NoSuchPostException::new);
	}

	@Override
	public Post findOne(Long postId) {
		return postRepository.findById(postId).orElseThrow(NoSuchPostException::new);
	}

	@Override
	@Transactional
	public Long writePost(String loginEmail, PostDto postDto) {
		Member findMember = memberService.findOne(loginEmail);
		Post post = new Post.Builder().member(findMember)
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.build();
		return postRepository.save(post).getId();
	}

	@Override
	@Transactional
	public void updatePost(String loginEmail, PostDto postDto) {
		Post post = findOne(postDto.getId());
		post.confirmAuthority(loginEmail);
		post.updatePost(postDto.getTitle(), postDto.getContent());
	}

	@Override
	@Transactional
	public void deletePost(String loginEmail, Long postId) {
		Post post = findOne(postId);
		post.confirmAuthority(loginEmail);
		postRepository.delete(post);
	}

	private List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) {
		return  multipartFiles.stream()
			.filter(Objects::nonNull)
			.map(this::storeFile)
			.collect(toList());
	}

	private UploadFile storeFile(MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return null;
		}

		String originalFilename = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFilename);

		try {
			multipartFile.transferTo(new File(storeFileName)); // 경로 더해줘야 함
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new UploadFile(originalFilename, storeFileName);
	}

	private String createStoreFileName(String originFileName) {
		String ext = extractExt(originFileName);
		String uuid = UUID.randomUUID().toString();
		return uuid + ext;
	}

	private String extractExt(String originFileName) {
		int pos = originFileName.lastIndexOf(".");
		return originFileName.substring(pos);
	}
}
