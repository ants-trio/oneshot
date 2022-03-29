$(function () {
  let postId = $("#view-post-id").html();
  $.ajax({
    url: "/post/" + postId,
    type: "GET",
    data: "",
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      postDetail(response.data.postDetailDto);
      expressPostBtn(response.data.postDetailDto.role);
    },
    error: function () {
      console.log("error");
    },
  });

  function postDetail(postData) {
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

  function expressPostBtn(role) {
    console.log(role);
    $("#post-admin-btn").empty();
    if (role == "WRITER") {
      $("#post-admin-btn").append(`
      <button type="button"
        class="px-3 py-2 text-sm font-semibold leading-tight text-orange-700 bg-orange-100 rounded-full dark:text-white dark:bg-orange-600"
        id="post-modify">
        수정하기
      </button>
      <button
        class="flex items-center justify-between text-sm ms-2 px-3 py-2 font-semibold leading-tight text-red-700 bg-red-100 rounded-full dark:text-red-100 dark:bg-red-700"
        aria-label="Delete" id="post-delete">
        삭제하기
      </button>
      `);
    }
    $("#post-admin-btn").append(`
      <a href="postList.html" th:href="@{/posts}"
        class="list_more_btn flex ms-2 items-center justify-between text-sm px-3 py-2 font-semibold text-sm leading-tight text-green-700 bg-green-100 rounded-full dark:bg-green-700 dark:text-green-100"
        aria-label="Edit">
        목록
      </a>
    `);
  }
});
