$(function () {
  let postId = $("#view-post-id").html();
  $.ajax({
    url: "/post/" + postId,
    type: "GET",
    data: "",
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      postDetail(response.data.postResponseDto);
      expressComment(response.data.postResponseDto.comments);
      expressPostBtn(response.data.postResponseDto.role);
    },
    error: function () {
      console.log("게시물 로딩에 에러 발생");
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

  function expressComment(commentData) {
    $("#post-comment").empty();
    for (let i = 0; i < commentData.length; i++) {
      if (commentData[i].role == "WRITER") {
        $("#post-comment").append(`
        <div class="comment_item p-4 my-3">
          <div style="display: none" id="comment-id">${commentData[i].commentId}</div>
          <div class="comment_hd d-flex align-items-center justify-content-between">
            <p class="fw-bold" id="comment-writer">${commentData[i].writer}</p>
            <div class="review_control_wrap position-relative">
              <i class="fa-solid fa-ellipsis-vertical review_control_icon"></i>
              <ul class="position-absolute review_control_ul px-3 py-2 m-0">
                <li class="text-center review_control_modify_btn w-100" id="comment-modify">수정</li>
                <li class="text-center mt-2 review_control_delete_btn w-100" id="comment-delete">삭제</li>
              </ul>
            </div>
          </div>
          <div class="comment_body mt-3 mb-2">
            <p class="comment_content" id="comment-content">${commentData[i].content}</p>
          </div>
          <div class="comment_ft" id="comment-date-column">
            <p class="comment_date text-xs fw-lighter" id="comment-date" style="color:rgba(0,0,0,0.5)">${commentData[i].createdDate}</p>
          </div>
        </div>
        `);
      } else {
        $("#post-comment").append(`
        <div class="comment_item p-4 my-3">
          <div style="display: none" id="comment-id">${commentData[i].commentId}</div>
          <div class="comment_hd">
            <p class="fw-bold" id="comment-writer">${commentData[i].writer}</p>
          </div>
          <div class="comment_body mt-3 mb-2">
            <p class="comment_content" id="comment-content">${commentData[i].content}</p>
          </div>
          <div class="comment_ft">
            <p class="comment_date text-xs fw-lighter" id="comment-date" style="color:rgba(0,0,0,0.5)">${commentData[i].createdDate}</p>
          </div>
        </div>
      `);
      }
    }
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

  // 게시물 수정 기능
  $(document).on("click", "#post-modify", function () {
    let prevTitle = $("#post-title").text();
    let prevContent = $("#post-content").text();
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
    let updateRequest = {
      title: $("#update-title").val(),
      content: $("#update-content").val(),
    };

    $.ajax({
      url: "/post/" + postId,
      type: "PATCH",
      data: JSON.stringify(updateRequest),
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        alert("글 수정에 성공했습니다.");
        location.href = "/posts/" + postId;
      },
      error: function () {
        alert("글 수정에 실패했습니다.");
      },
    });
  });

  // 게시물 삭제 기능
  $(document).on("click", "#post-delete", function () {
    $.ajax({
      url: "/post/" + postId,
      type: "DELETE",
      data: "",
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        if (confirm("게시물을 삭제하시겠습니까?")) {
          alert("글 삭제에 성공했습니다.");
          location.href = "/posts";
        }
      },
      error: function () {
        alert("글 삭제에 실패했습니다.");
      },
    });
  });

  // 댓글 등록 기능
  $("#comment-register").on("click", function () {
    let commentData = $("#write-comment").val();
    if (commentData == null || commentData == "") {
      alert("댓글 내용을 입력해주세요");
    } else {
      let commentRequest = {
        content: commentData,
      };
      addComment(commentRequest);
    }
  });

  $(document).on("click", ".review_control_icon", function () {
    $(this).siblings().toggleClass("clicked");
  });
  $(document).on("click", ".review_control_ul", function () {
    $(this).removeClass("clicked");
  });

  // 댓글 수정 기능
  $(document).on("click", "#comment-modify", function () {
    let prevComment = $(this).parent().parent().parent().parent().find("#comment-content").text();
    $($(this).parent().parent().parent().parent().find("#comment-content"))
      .empty()
      .append(
        `<form action=""><textarea class="form-control" id="modify-comment" rows="3" style="min-height: 100px;">${prevComment}</textarea></form>`
      );

    $($(this).parent().parent().parent().parent().find("#comment-date-column")).empty().append(`
      <button type="button"
        class="px-3 py-2 mt-3 text-sm font-semibold leading-tight rounded-full comment_register_btn"
        id="comment-modify-complete">
        수정완료
      </button>
    `);
    $(this).parent().parent().parent().remove();
  });

  $(document).on("click", "#comment-modify-complete", function () {
    let modifiedComment = $(this).parent().parent().find("#modify-comment").val();
    let commentId = $(this).parent().parent().find("#comment-id").text();

    let commentUpdateRequest = {
      content: modifiedComment,
    };

    $.ajax({
      url: "/post/" + postId + "/comment/" + commentId,
      type: "PATCH",
      data: JSON.stringify(commentUpdateRequest),
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        reloadComment();
      },
      error: function () {
        alert("댓글 수정에 실패했습니다.");
      },
    });
  });

  // 댓글 삭제 기능
  $(document).on("click", "#comment-delete", function () {});

  function addComment(commentRequest) {
    $.ajax({
      url: "/post/" + postId + "/comment/new",
      type: "POST",
      data: JSON.stringify(commentRequest),
      contentType: "application/json; charset=utf-8",
      success: function () {
        reloadComment();
        $("#write-comment").val("");
      },
      error: function () {
        alert("댓글 작성에 실패했습니다.");
      },
    });
  }

  function reloadComment() {
    $.ajax({
      url: "/post/" + postId + "/comment",
      type: "GET",
      data: "",
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        expressComment(response.data.comments);
      },
      error: function () {
        console.log("댓글 로딩에 에러 발생");
      },
    });
  }
});
