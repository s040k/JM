
let usersList;
let newUser;

function getAllUsers() {
    getNewUser();
    $.ajax({
        type: 'get',
        url: "/allusers/",
        success: function (data) {
            usersList = data;
            updateTable(data);
        }
    });
}

function getNewUser() {

    $.ajax({
        type: 'get',
        url: "/newUser/",
        success: function (data) {
            newUser = data;
        }

    });

}

function updateTable(res) {
    //Удаляем таблицу

    let tablet = document.getElementById('ajax_table');
    if (tablet != null) {
        tablet.remove()
    }

    //Создаем таблицу
    let table = document.createElement('table');
    table.id = "ajax_table";
    table.setAttribute("border", "5");
    table.innerHTML = '<tr id="tr_main_inner_row"><td>ID</td><td>Login</td><td>Mail</td><td>Password</td><td>Role</td><td>Edit</td><td>Delete</td></tr>';
    document.body.append(table);


    //Создаем колонки
    res.forEach(function (item, i, res) {

        let tr = document.createElement('tr');
        tr.id = "tr" + item.id + "_inner_row";
        document.getElementById("ajax_table").append(tr);
//id
        let td = document.createElement('td');
        td.innerHTML = '<input type="text" value="' + item.id + '" disabled/>\n' +
            '    <input type="hidden" value="' + item.id + '" name="id_user_to_edit"\n' +
            '           id="id' + item.id + '"/>\n';
        document.getElementById("tr" + item.id + "_inner_row").append(td);
//login
        let td2 = document.createElement('td');
        td2.innerHTML = '<input type="text" value="' + item.login + '" name="login_user_to_edit"\n' +
            '                       id="login' + item.id + '"/>';
        document.getElementById("tr" + item.id + "_inner_row").append(td2);
//email
        let td3 = document.createElement('td');
        td3.innerHTML = '<input type="text" value="' + item.email + '" name="email_user_to_edit"\n' +
            '                       id="email' + item.id + '"/>';
        document.getElementById("tr" + item.id + "_inner_row").append(td3);
//password
        let td4 = document.createElement('td');
        td4.innerHTML = '<input type="text" value="' + item.password + '" name="password_user_to_edit"\n' +
            '                       id="password' + item.id + '"/>';
        document.getElementById("tr" + item.id + "_inner_row").append(td4);
//role
        let td5 = document.createElement('td');
        td5.innerHTML = '<input type="text" value="' + item.role.nameRole + '" name="role_user_to_edit"\n' +
            '                       id="role' + item.id + '"/>';
        document.getElementById("tr" + item.id + "_inner_row").append(td5);
//edit
        let td6 = document.createElement('td');
        td6.innerHTML = '<button type="submit" value="' + i + '" \n' +
            '                       id="edit' + item.id + '" onclick="editUser(usersList[this.value]);" type="text/javascript" >edit</button>';
        document.getElementById("tr" + item.id + "_inner_row").append(td6);
//delete
        let td7 = document.createElement('td');
        td7.innerHTML = '<button type="submit" value="' + item.id + '" \n' +
            '                       id="delete' + item.id + '" onclick="deleteUser(this.value);" type="text/javascript" >delete</button>';
        document.getElementById("tr" + item.id + "_inner_row").append(td7);
    });


//New User
//Удаляем таблицу

    let tabletoDelete = document.getElementById('ajax_table_newuser');
    if (tabletoDelete != null) {
        tabletoDelete.remove()
    }

    //Создаем таблицу
    let tableToAdd = document.createElement('table');
    tableToAdd.id = "ajax_table_newuser";
    tableToAdd.setAttribute("border", "5");
    document.body.append(tableToAdd);
    document.getElementById("ajax_table_newuser").append("New User");


    //Создаем колонки


    let tr = document.createElement('tr');
    tr.id = "tr_inner_row_newuser";
    document.getElementById("ajax_table_newuser").append(tr);
//id
    let td = document.createElement('td');
    td.innerHTML = '<input type="text" value="ID" disabled/>';
    document.getElementById("tr_inner_row_newuser").append(td);
//login
    let td2 = document.createElement('td');
    td2.innerHTML = '<input type="text"  name="login_user_to_edit"\n' +
        '                       id="login_user_add"/>';
    document.getElementById("tr_inner_row_newuser").append(td2);
//email
    let td3 = document.createElement('td');
    td3.innerHTML = '<input type="text"  name="email_user_to_edit"\n' +
        '                       id="email_user_add"/>';
    document.getElementById("tr_inner_row_newuser").append(td3);
//password
    let td4 = document.createElement('td');
    td4.innerHTML = '<input type="text"  name="password_user_to_edit"\n' +
        '                       id="password_user_add"/>';
    document.getElementById("tr_inner_row_newuser").append(td4);
//role
    let td5 = document.createElement('td');
    td5.innerHTML = '<input type="text"  name="role_user_to_edit"\n' +
        '                       id="role_user_add"/>';
    document.getElementById("tr_inner_row_newuser").append(td5);
//add
    let td6 = document.createElement('td');
    td6.innerHTML = '<button type="submit"  \n' +
        '                       onclick="addUser();" type="text/javascript" >add</button>';
    document.getElementById("tr_inner_row_newuser").append(td6);


}

function editUser(user) {

    user.login = document.getElementById("login" + user.id).value;
    user.email = document.getElementById("email" + user.id).value;
    user.password = document.getElementById("password" + user.id).value;
    user.role.nameRole = document.getElementById("role" + user.id).value;

    console.log(user.role.nameRole);
    console.log(user);
    var jsonFile = {json: JSON.stringify(user)};

    $.post({
        url: "/updateUser/",
        data: jsonFile,
        dataType: "json",
        success: function () {
            getAllUsers();
        }
    });

}

function addUser() {

    newUser.login = document.getElementById("login_user_add").value;
    newUser.email = document.getElementById("email_user_add").value;
    newUser.password = document.getElementById("password_user_add").value;
    newUser.role.nameRole = document.getElementById("role_user_add").value;

    var jsonFile = {json: JSON.stringify(newUser)};

    $.post({
        url: "/addUser/",
        data: jsonFile,
        dataType: "json",
        success: function () {
            getAllUsers();
        }
    });
}

function deleteUser(idUser) {

    console.log(idUser);

    var data = {"id": idUser};
    $.get({
        url: "/deleteUser/",
        data: data,
        success: function () {
            getAllUsers();
        }
    });
}
