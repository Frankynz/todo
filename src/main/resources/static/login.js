function registryUser() {
    let data = {
        username: $("#username-reg").val(),
        password: $("#password-reg").val(),
        firstName: $("#first-name-reg").val(),
        lastName: $("#last-name-reg").val()
    }

    $.ajax({
        url: "/registry",
        contentType: "application/json",
        type: "POST",
        data: JSON.stringify(data),
        success: function() {
            $("#sign-in-tab").click();
        },
        error: function() {
            alert("error");
        }
    })
}

$(document).ready(function () {
    $("#sign-up-btn").click(function (event) {
        event.preventDefault();
        registryUser();
    })
})