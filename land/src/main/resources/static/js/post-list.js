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
  ];
  // success 부분에 posts 배열에 push하는 내용 넣어두기

  $.each(responseTest, function () {
    posts.push($(this));
  });

  // 이것도 success 부분에 넣기
  initiatePost();

  function initiatePost() {
    $("#post-list").empty();
    for (let i = 0; i < posts.length; i++) {}
  }
});
