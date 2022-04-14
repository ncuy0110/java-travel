function postComment(id) {
    const content = $("#content").val();
    // const content = document.getElementById("myText").value;
    var body = {}
    body["content"] = content;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: `/comment/${id}`,
        data: JSON.stringify(body),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            const username = data["username"];
            const e = `<div class="row h-10">
                <div class="col-11 ml-2"> 
                <span> <b>${username}</b> <span>${content}</span> </span>
                </div> 
            </div>`;
            $('#list_comment').append(e);
            $('#content').val('');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}