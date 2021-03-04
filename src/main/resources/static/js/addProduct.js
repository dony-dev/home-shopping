$(document).ready(function () {
    /* click add product */
    $('#addProductModalBtn').click(function() {
        var category = null;
        $.ajax({
            type: "GET",
            url: "/api/category",
            success: function (response) {
                $('#addProductModalBtn input').empty();
                category = response;
                console.log('response: ', response);
                $.each(category, function (i, c) {
                    $('#categorySelect').append('<option value="'+c.id+'">'+c.categoryName+'</option>');
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    });

    /* submit product */
    $('#addProductModalForm').submit(function(event) {
        console.log("11111111111");
        event.preventDefault();

        var formData = $('#addProductModalForm').serializeObject();
        console.log(formData);
        var data = JSON.stringify(formData);
        console.log('data: ', data);

        $.ajax({
            url: "/api/products",
            type: "POST",
            contentType: 'application/json',
            data: data,
            success: function(data){
                alert('successfully submitted');
                location.reload();
            },
            error: function(e) {
                alert('fail');
            }
        });
    });
});