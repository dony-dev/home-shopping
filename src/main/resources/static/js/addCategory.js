$(document).ready(function () {
    /* submit product */
    $('#addCategoryModalForm').submit(function(event) {
        event.preventDefault();

        var formData = $('#addCategoryModalForm').serializeObject();
        console.log(formData);
        var data = JSON.stringify(formData);
        console.log('data: ', data);

        $.ajax({
            url: "/api/category",
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