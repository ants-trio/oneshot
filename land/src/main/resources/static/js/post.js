/*

initiateBoard();
expressBoard();



*/

$(function () {
  let posts = [];

  // 열자마자 ajax로 찍어서 response 가져왔다고 가정

  let responseTest = [
    {
      postId: 333,
      createDate: "2022-03-26 22:04:03",
      lastUpdateDate: "2022-03-26 22:04:03",
      postContent: "내용입니다",
      postLike: false,
      postTitle: "제목입니다",
      memberName: "임승민",
    },
    {
      postId: 523523,
      createDate: "2022-03-26 22:04:03",
      lastUpdateDate: "2022-03-26 22:04:03",
      postContent: "내용입니다",
      postLike: true,
      postTitle: "제목입니다",
      memberName: "강성엽",
    },
    {
      postId: 78723,
      createDate: "2022-03-26 22:04:03",
      lastUpdateDate: "2022-03-26 22:04:03",
      postContent: "내용입니다",
      postLike: true,
      postTitle: "제목입니다",
      memberName: "이수은",
    },
  ];
  // success 부분에 posts 배열에 push하는 내용 넣어두기

  $.each(responseTest, function () {
    posts.push($(this));
  });

  // 이것도 success 부분에 넣기
  initiatePost();

  function initiatePost() {
    $("#post-list").empty();
    for (let i = 0; i < posts.length; i++) {
      $("#post-list").append(`
      <tr class="text-gray-700 dark:text-gray-400">
        <td class="px-4 py-3 text-xs">
          <span
            class="px-2 py-1 font-semibold leading-tight text-blue-700 bg-blue-100 rounded-full dark:bg-blue-700 dark:text-blue-100">
            ${posts[i][0].postId}
          </span>
        </td>
        <td class="px-4 py-3 text-sm">
          <a href="javascript: viewPost(${posts[i][0].postId})" th:href="@{~/}" class="font-semibold post_more_btn">
          ${posts[i][0].postTitle}</a>
        </td>
        <td class="px-4 py-3">
          <div class="flex items-center text-sm">
            <p class="">${posts[i][0].memberName}</p>
          </div>
        </td>
        <td class="px-4 py-3 text-sm">
          ${posts[i][0].createDate}
        </td>
      </tr>
      `);
    }
  }
});
