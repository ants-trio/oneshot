$(function () {
  console.log($("#view-post-id").html());
  let postId = $("#view-post-id").html();

  $.ajax({
    url: "post",
    type: "GET",
    data: postId,
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      console.log(response);
    },
    error: function () {
      console.log("error");
    },
  });
});
