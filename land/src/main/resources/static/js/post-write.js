$(function () {
  $("#post-register").on("click", function () {
    let writeRequest = {
      title: $("#write-title").val(),
      content: $("#write-content").val(),
    };
    writePost(writeRequest);
  });

  function writePost(writeRequest) {
    $.ajax({
      url: "/post/new",
      type: "POST",
      data: JSON.stringify(writeRequest),
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        console.log(response);

        alert("글 작성에 성공했습니다.");
        location.href = "/posts";
      },
      error: function () {
        alert("글 작성에 실패했습니다.");
      },
    });
  }
});
