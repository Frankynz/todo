function clickActive(id) {
    $.ajax({
        url: "/set-active/" + id,
        type: "PUT",
        success: function () {
            getMyNotActiveTodo();
        }
    })
}

function clickNotActive(id) {
    $.ajax({
        url: "/set-not-active/" + id,
        type: "PUT",
        success: function () {
            getMyActiveTodo();
        }
    })
}

function deleteTodo(id) {
    $.ajax({
        url: "/delete/" + id,
        type: "DELETE",
        success: function () {
            if ($("#show-active-btn").prop("checked")) {
                getMyActiveTodo();
            } else if ($("#show-not-active-btn").prop("checked")) {
                getMyNotActiveTodo();
            }
        }
    })
}

function createTodo() {
    let data = {
        description: $("#todo-name").val(),
        isActive: false,
    }

    $.ajax({
        url: "/create-todo",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            $("#todo-name").val('');
            $("#show-active-btn").click();
            getMyActiveTodo();
        },
        error: function () {
            alert("error");
        }
    })
}

function getMyActiveTodo() {
    $.ajax({
        url: "/get-my-active-todo",
        type: "GET",
        success: function (result) {
            $("#todo-list").empty();
            $.each(result, function (i, todo) {
                $("#todo-list").append(`
                <div class="input-group col-7 p-0 m-0 mx-auto mt-2">
                    <div class="input-group-prepend">
                        <button class="btn btn-outline-secondary" type="button" onclick="clickNotActive(${todo.id})" id="btn-active">Done</button>
                    </div>
                    <input id='${todo.id}' class="form-control form-control-lg todo" aria-describedby='btn-active' value='${todo.description}' type="text" disabled>
                         
                    <div class="input-group-prepend">
                    <button type="button" onclick="deleteTodo(${todo.id})" class="btn btn-outline-secondary">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                        </svg>
                    </button>
                    </div>
                    
                </div>`);
            })
        },
        error: function () {
            alert("error");
        }
    })
}

function getMyNotActiveTodo() {
    $.ajax({
        url: "/get-my-not-active-todo",
        type: "GET",
        success: function (result) {
            $("#todo-list").empty();
            $.each(result, function (i, todo) {
                $("#todo-list").append(`
                <div class="input-group col-7 p-0 m-0 mx-auto mt-2">
                    <div class="input-group-prepend">
                        <button class="btn btn-outline-secondary" onclick="clickActive(${todo.id})" type="button" id="btn-active">Un Done</button>
                    </div>
                    
                    <input id='${todo.id}' class="form-control form-control-lg todo" aria-describedby='btn-active' value='${todo.description}' type="text" disabled>
                  <div class="input-group-prepend">
                    <button type="button" onclick="deleteTodo(${todo.id})" class="btn btn-outline-secondary">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                        </svg>
                    </button>
                    </div>
                    
                </div>`);
            })
        },
        error: function () {
            alert("error");
        }
    })
}

function editTodo(id) {
    let data = {
        description: $(`#${id}`).val(),
    }

    $.ajax({
        url: "/edit-todo/" + id,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            $(`#${id}`).attr("disabled", true);
        },
        error: function () {
            alert("error");
        }
    })
}

$(document).ready(function () {
    getMyActiveTodo();
    $("#todo-name").keydown(function (event) {
        if (event.keyCode === 13) {
            createTodo();
        }
    });

    $("body").on("dblclick", ".todo", function (event) {
        event.target.disabled = false;
        $(`#${event.target.id}`).keydown(function (e) {
            if (e.keyCode === 13) {
                editTodo(event.target.id);
            }
        })
    });

    $("#show-active-btn").click(function () {
        getMyActiveTodo();
    });

    $("#show-not-active-btn").click(function () {
        getMyNotActiveTodo();
    })


})