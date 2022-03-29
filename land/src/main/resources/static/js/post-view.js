$(function () {
  let postId;
  $(document).on("click", "#view-post", function () {
    console.log($(this).children().eq(0).children().html());
    postId = $(this).children().eq(0).children().html();
    location.href = "/posts/" + postId;
  });
  console.log(postId);
});
