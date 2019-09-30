$('.btn').on('click', function () {
    var a, b, c;
    a = $(this).attr("value");

    if ($(this).attr("id") == 'buttonEdit') {
        b = document.getElementById('colId' + a).getAttribute('value');
        document.getElementById('id_seting_user').setAttribute("value", b);

        b = document.getElementById('colEmail' + a).getAttribute('value');
        document.getElementById('email_seting_user').setAttribute("value", b);

        b = document.getElementById('colLogin' + a).getAttribute('value');
        document.getElementById('login_seting_user').setAttribute("value", b);

        b = document.getElementById('colPassword' + a).getAttribute('value');
        document.getElementById('password_seting_user').setAttribute("value", b);

        b = document.getElementById('colRoles' + a).getAttribute('value');
        document.getElementById('roles_seting_user').setAttribute("value", b);

        b = document.getElementById('colEmail' + a).getAttribute('value');
        c = document.getElementById('colLogin' + a).getAttribute('value');
        document.getElementById('headerModalLabel').textContent = 'Edit user ' + b + ' ' + c;
    }
});

function userPageClick() {

    var userTagClasses = document.getElementById('userPage').getAttribute('class');
    if(!userTagClasses.includes("active")){
        userTagClasses = userTagClasses+' active';
    }
    document.getElementById('userPage').setAttribute('class',userTagClasses);

    var adminTagClasses = document.getElementById('adminPage').getAttribute('class');
    if(!adminTagClasses.includes(" active")){
        adminTagClasses = adminTagClasses.replace(' active','');
    }
    document.getElementById('adminPage').setAttribute('class',adminTagClasses);

}

function adminPageClick() {

    var adminTagClasses = document.getElementById('adminPage').getAttribute('class');
    if(!adminTagClasses.includes("active")){
        adminTagClasses = adminTagClasses+' active';
    }
    document.getElementById('adminPage').setAttribute('class',adminTagClasses);

    var userTagClasses = document.getElementById('userPage').getAttribute('class');
    if(!userTagClasses.includes(" active")){
        userTagClasses = userTagClasses.replace(' active','');
    }
    document.getElementById('userPage').setAttribute('class',userTagClasses);

}