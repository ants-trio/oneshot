$(function () {
  let postId = $("#view-post-id").html();
  $.ajax({
    url: "/post/" + postId,
    type: "GET",
    data: "",
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      postDetail(response.data.postDetailDto);
    },
    error: function () {
      console.log("error");
    },
  });

  function postDetail(postData) {
    console.log(postData);
    $("#post-title").empty().append(`
      <p>
        ${postData.title}
      </p>
    `);
    $("#post-content").empty().append(`
      <p>
        ${postData.content}
      </p>
    `);
  }
});
