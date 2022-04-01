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
    $("#post-title").empty().append(`<p class="text-2xl lh-base fw-bold">${postData.title}</p>`);
    $("#post-info").empty().append(`
      <p class="text-sm" style="color:rgba(0,0,0,0.7)">${postData.writer}</p>
      <p class="text-sm d-fle align-items-center">
        <i class="fa-solid fa-clock me-1" style="color:rgba(0,0,0,0.7)"></i>
        <span id="created_time" style="color:rgba(0,0,0,0.7)">${postData.createdDate}</span>
      </p>
    `);
    $("#post-content").empty().append(`<p class="1h-base">${postData.content}</p>`);
  }

  function expressPostBtn(role) {
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
      <a href="/posts" th:href="@{/posts}"
        class="list_more_btn flex ms-2 items-center justify-between text-sm px-3 py-2 font-semibold text-sm leading-tight rounded-full"
        aria-label="Edit">
        목록
      </a>
    `);
  }

  $(document).on("click", "#post-modify", function () {
    let prevTitle = $("#post-title").text();
    let prevContent = $("#post-content").text();
    console.log(prevContent);
    $("#post-title").empty().append(`
      <form action="">
        <input type="email" id="update-title" class="form-control"
        value= "${prevTitle}">
      </form>
    `);
    $("#post-content").empty().append(`
      <form action="">
        <textarea class="form-control" id="update-content" rows="5"
        style="min-height: 450px;">${prevContent}</textarea>
      </form>
    `);

    $("#post-admin-btn").empty();
    $("#post-admin-btn").append(`
      <button type="button"
        class="px-3 py-2 text-sm font-semibold leading-tight text-orange-700 bg-orange-100 rounded-full dark:text-white dark:bg-orange-600"
        id="post-update">
        수정완료
      </button>
      <a href="/posts" th:href="@{/posts}"
        class="list_more_btn flex ms-2 items-center justify-between text-sm px-3 py-2 font-semibold text-sm leading-tight rounded-full"
        aria-label="Edit">
        목록
      </a>
      `);
  });

  $(document).on("click", "#post-update", function () {
    //TODO
  });

  $(document).on("click", "#post-delete", function () {
    //TODO
  });
});
