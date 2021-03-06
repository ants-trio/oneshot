$(function () {
  let initialRequest = { page: 0, size: 10 };

  let totalPage;
  let totalPost;
  let listAmount = 10;
  let amountChange = false;

  $.ajax({
    url: "post",
    type: "GET",
    data: initialRequest,
    contentType: "application/json; charset=utf-8",
    success: function (response) {
      initiatePost(response.data);
    },
    error: function (response) {
      console.log("error");
      console.log(response);
    },
  });

  $("#list-amount").on("change", function () {
    listAmount = $("#list-amount").find(":checked").val();
    let postRequest = {
      page: 0,
      size: listAmount,
    };
    amountChange = true;
    getPostData(postRequest, 0);
  });

  function initiatePost(postData) {
    totalPost = postData.posts.totalElements;
    totalPage = postData.posts.totalPages;

    pagination(1);
    expressListOutline(0, totalPost);
    expressPost(postData.posts.content);
  }

  function pagination(firstPage) {
    $("#post-list-page").empty();
    $("#post-list-page").append(`
        <li>
          <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none focus:shadow-outline-purple"
                    aria-label="Previous" id="post-list-btn-prev" value="${firstPage}">
            <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20">
            <path d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                        clip-rule="evenodd" fill-rule="evenodd"></path>
            </svg>
          </button>
        </li>
      `);
    for (let i = firstPage; i < firstPage + 10; i++) {
      if (i > totalPage) break;
      $("#post-list-page").append(`
        <li>
          <button class="px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-purple" id="presentPage">${i}</button>
        </li>
      `);
    }
    $("#post-list-page").append(`
      <li>
        <button class="px-3 py-1 rounded-md rounded-r-lg focus:outline-none focus:shadow-outline-purple"
                    aria-label="Next" id="post-list-btn-next" value="${firstPage}">
          <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20">
          <path d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                        clip-rule="evenodd" fill-rule="evenodd"></path>
          </svg>
        </button>
      </li>
    `);
  }

  function getPostData(request, selectedPage) {
    $.ajax({
      url: "post",
      type: "GET",
      data: request,
      contentType: "application/json; charset=utf-8",
      success: function (response) {
        totalPage = response.data.posts.totalPages;
        if (amountChange) {
          pagination(1);
          amountChange = false;
        }
        expressListOutline(selectedPage);
        expressPost(response.data.posts.content);
      },
      error: function (response) {
        console.log("error");
        console.log(response);
      },
    });
  }

  $(document).on("click", "#presentPage", function () {
    let selectedPage = $(this).html() * 1 - 1;
    let postRequest = {
      page: selectedPage,
      size: listAmount,
    };

    getPostData(postRequest, selectedPage);
  });

  $(document).on("click", "#post-list-btn-next", function () {
    let selectedPage = $(this).val() * 1 + 9;
    if (selectedPage < totalPage) {
      let postRequest = {
        page: selectedPage,
        size: listAmount,
      };
      pagination($(this).val() * 1 + 10);
      getPostData(postRequest, selectedPage);
    }
  });

  $(document).on("click", "#post-list-btn-prev", function () {
    let selectedPage = $(this).val() * 1 - 11;
    if (selectedPage >= 0) {
      let postRequest = {
        page: selectedPage,
        size: listAmount,
      };
      pagination($(this).val() * 1 - 10);
      getPostData(postRequest, selectedPage);
    }
  });

  function expressListOutline(selectedPage) {
    if (selectedPage * listAmount * 1 + listAmount * 1 <= totalPost) {
      $("#post-list-outline").empty().append(`
      Showing ${selectedPage * listAmount + 1}-${
        selectedPage * listAmount * 1 + listAmount * 1
      } of ${totalPost}
      `);
    } else {
      $("#post-list-outline").empty().append(`
      Showing ${selectedPage * listAmount + 1}-${totalPost} of ${totalPost}
      `);
    }
  }

  function expressPost(posts) {
    $("#post-list").empty();
    for (let i = 0; i < posts.length; i++) {
      $("#post-list").append(`
      <tr class="text-gray-700 dark:text-gray-400" id="view-post">
        <td class="px-4 py-3 text-xs">
          <span
            class="px-2 py-1 font-semibold leading-tight text-blue-700 bg-blue-100 rounded-full dark:bg-blue-700 dark:text-blue-100">${posts[i].id}</span>
        </td>
        <td class="px-4 py-3 text-sm">
          <a href="/posts/${posts[i].id}" th:href="@{/post/${posts[i].id}}" class="font-semibold post_more_btn">
          ${posts[i].title}</a>
        </td>
        <td class="px-4 py-3">
          <div class="flex items-center text-sm">
            <p class="">${posts[i].writer}</p>
          </div>
        </td>
        <td class="px-4 py-3 text-sm">
          ${posts[i].createdDate}
        </td>
      </tr>
      `);
    }
  }
});
