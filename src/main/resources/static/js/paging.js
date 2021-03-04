$(document).ready(function () {
    $('#productTable').DataTable({
        lengthChange: false,
        searching: false,
        ordering: false,
        paging: false,
        bInfo: false
    });


    function fetchProducts(page, size) {
        var pageNumber = (typeof page !== 'undefined') ? page : 0;
        var sizeNumber = (typeof size !== 'undefined') ? size : 5;

        /**
         * Do a fetching to get data from Backend's RESTAPI
         */
        $.ajax({
            type: "GET",
            url: "/api/products/page",
            data: {
                page: pageNumber,
                size: sizeNumber
            },
            success: function (response) {
                console.log('response.content: ', response.content);
                $('#productTable tbody').empty();
                // add table rows
                $.each(response.content, (i, content) => {
                    // var tr_id = 'tr_' + content.id;
                    // var tr_id = 'tr_' + (i+1);
                    var productRow =
                        '<tr>' +
                            '<td><span class="custom-checkbox"><input type="checkbox" id="checkbox' + (i + 1) + '" name="options[]" value=' + (i + 1) + '"><label for= "checkbox1"></label></span></td>' +
                            '<td class="pid">' + content.productId + '</td>' +
                            '<td>' + content.categoryName + '</td>' +
                            '<td><a href ="#">' + content.productName + '</a></td>' +
                            '<td>' + content.productPrice + '</td>' +
                            '<td>' + content.productDescription + '</td>' +
                            '<td>' + content.productCommentCount + '</td>' +
                            '<td>' +
                                '<a href="#editProductModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>' +
                                '<a href="#deleteProductModal" id="btn_delete" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>' +
                            '</td>' +
                        '</tr>';
                    $('#productTable tbody').append(productRow);
                });

                if ($('ul.pagination li').length - 2 != response.totalPages) {
                    // build pagination list at the first time loading
                    $('ul.pagination').empty();
                    buildPagination(response.totalPages);
                }
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }

    /**
     * Check a string value is a number or NOT
     */
    function isNumeric(value) {
        return /^-{0,1}\d+$/.test(value);
    }

    /**
     *
     * Build the pagination Bar from totalPages
     */
    function buildPagination(totalPages) {
        // Build paging navigation
        var pageIndex = '<li class="page-item"><a class="page-link">Previous</a></li>';
        $("ul.pagination").append(pageIndex);

        // create pagination
        for (var i = 1; i <= totalPages; i++) {
            // adding .active class on the first pageIndex for the loading time
            if (i == 1) {
                pageIndex = "<li class='page-item active'><a class='page-link'>"
                    + i + "</a></li>"
            } else {
                pageIndex = "<li class='page-item'><a class='page-link'>"
                    + i + "</a></li>"
            }
            $("ul.pagination").append(pageIndex);
        }

        pageIndex = '<li class="page-item"><a class="page-link">Next</a></li>';
        $("ul.pagination").append(pageIndex);
    }

    /**
     *
     * Fetching the Customers from SpringBoot RestAPI at the initial time
     */
    (function () {
        // get first-page at initial time
        fetchProducts(0);
    })();

    /**
     * Fetch again the data from RestAPI when
     * 		having any click on pagination bar for pagination filtering and sorting
     */
    $(document).on("click", "ul.pagination li a", function () {
        var val = $(this).text();

        // click on the NEXT tag
        if (val.toUpperCase() === "NEXT") {
            var activeValue = parseInt($("ul.pagination li.active").text());
            var totalPages = $("ul.pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next
            if (activeValue < totalPages) {
                var currentActive = $("li.active");
                fetchProducts(activeValue, 5); // get next page value
                // remove .active class for the old li tag
                $("li.active").removeClass("active");
                // add .active to next-pagination li
                currentActive.next().addClass("active");
            }
        } else if (val.toUpperCase() === "PREVIOUS") {
            var activeValue = parseInt($("ul.pagination li.active").text());
            if (activeValue > 1) {
                // get the previous page
                fetchProducts(activeValue - 2, 5);
                var currentActive = $("li.active");
                currentActive.removeClass("active");
                // add .active to previous-pagination li
                currentActive.prev().addClass("active");
            }
        } else {
            fetchProducts(parseInt(val) - 1, 5);
            // add focus to the li tag
            $("li.active").removeClass("active");
            $(this).parent().addClass("active");
        }
    });
});