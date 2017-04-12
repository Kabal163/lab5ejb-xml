<!-- Personage search -->
function findPersonage(id) {
    var personageId;
    if(arguments.length > 0) personageId = id;
    else personageId = $('#select-personage :selected').val();
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "get",
        headers: {"amount":"single"},
        data: {"personageId":personageId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Weapon search -->
function findWeapon(id) {
    var weaponId;
    if(arguments.length > 0) weaponId = id;
    else weaponId = $('#select-weapon :selected').val();
    $.ajax({
        url: "/Lab4-ejb/weaponHandling",
        type: "get",
        headers: {"amount":"single"},
        data: {"weaponId":weaponId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Equipment search -->
function findEquipment(id) {
    var equipmentId;
    if(arguments.length > 0) equipmentId = id;
    else equipmentId = $('#select-equipment :selected').val();
    $.ajax({
        url: "/Lab4-ejb/equipmentHandling",
        type: "get",
        headers: {"amount":"single"},
        data: {"equipmentId":equipmentId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}


