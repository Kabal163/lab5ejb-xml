<!-- Personage search -->
function findPersonage(id) {
    var personageId;
    if(arguments.length > 0) personageId = id;
    else personageId = $('#select-personage :selected').val();
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "get",
        headers: {"searchFor":"single"},
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
        headers: {"searchFor":"single"},
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
        headers: {"searchFor":"single"},
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
        headers: {"searchFor":"singleXml"},
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
        headers: {"searchFor":"singleXml"},
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
        headers: {"searchFor":"singleXml"},
        data: {"personageId":personageId},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- All items search as XML-->
function findAllItemsAsXml() {
    $.ajax({
        url: urlHadler,
        type: "get",
        headers: {"searchFor":"allXml"},
        dataType: "html",
        success: function (response) {
            $('.view-holder').html(response);
        }
    });
}

<!-- Weapon search by name and level -->
$(document).ready(setFuncOnSearchByName);
$(document).ready(setFuncOnSearchByLevel);

function setFuncOnSearchByName() {
    console.log(urlHadler);
    $('.form-search-by-name').submit( function (event) {
        var itemName = $('.form-search-by-name').serialize();
        $.ajax({
            url: urlHadler,
            type: "get",
            headers: {"searchFor":"byName"},
            data: itemName,
            dataType: "html",
            success: function (response) {
                $('.view-holder').html(response);
            }
        });
        event.preventDefault();
    });
}

function setFuncOnSearchByLevel() {
    console.log(urlHadler);
    $('.form-search-by-level').submit(function (event){
        var itemLevel = $('.form-search-by-level').serialize();
        $.ajax({
            url: urlHadler,
            type: "get",
            headers: {"searchFor":"byLevel"},
            data: itemLevel,
            dataType: "html",
            success: function (response) {
                $('.view-holder').html(response);
            }
        });
        event.preventDefault();
    });
}
