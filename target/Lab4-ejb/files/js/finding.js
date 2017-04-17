<!-- Personage search -->
function findPersonage(id) {
    var personageId;
    if(arguments.length > 0) personageId = id;
    else personageId = $('#select-personage :selected').val();
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "get",
        headers: {"amount":"single", "responseType":"jspComponent"},
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
        headers: {"amount":"single", "responseType":"jspComponent"},
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
        headers: {"amount":"single", "responseType":"jspComponent"},
        data: {"equipmentId":equipmentId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Weapon search as XML -->
function findWeaponAsXml(id) {
    var weaponId;
    if(arguments.length > 0) weaponId = id;
    else weaponId = $('#select-weapon :selected').val();
    $.ajax({
        url: "/Lab4-ejb/weaponHandling",
        type: "get",
        headers: {"amount":"single", "responseType":"xml"},
        data: {"weaponId": weaponId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Equipment search as XML -->
function findEquipmentAsXml(id) {
    console.log("id is: " + id);
    var equipmentId;
    if(arguments.length > 0) equipmentId = id;
    else equipmentId = $('#select-equipment :selected').val();
    $.ajax({
        url: "/Lab4-ejb/equipmentHandling",
        type: "get",
        headers: {"amount": "single", "responseType":"xml"},
        data: {"equipmentId":equipmentId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Personage search as XML -->
function findPersonageAsXml(id) {
    var personageId;
    if(arguments.length > 0) personageId = id;
    else personageId = $('#select-personage :selected').val();
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "get",
        headers: {"amount": "single", "responseType":"xml"},
        data: {"personageId":personageId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}


