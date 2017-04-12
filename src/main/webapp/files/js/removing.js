<!-- Removing elements -->

function removeWeapon(weaponId) {
    $.ajax({
        url: "/Lab4-ejb/weaponHandling",
        type: "post",
        headers: {"action":"remove"},
        data: {"weaponId":weaponId},
        dataType: "json",
        success: function (response) {
            showRemoveStatus(response);
            removeWeaponFromSelect();
        }
    });
}

function removeEquipment(equipmentId) {
    $.ajax({
        url: "/Lab4-ejb/equipmentHandling",
        type: "post",
        headers: {"action":"remove"},
        data: {"equipmentId":equipmentId},
        dataType: "json",
        success: function (response) {
            showRemoveStatus(response);
            removeEquipmentFromSelect();
        }
    });
}

function removePersonage(personageId) {
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "post",
        headers: {"action":"remove"},
        data: {"personageId":personageId},
        dataType: "json",
        success: function (response) {
            showRemoveStatus(response);
            removePersonageFromSelect()
        }
    });
}

function showRemoveStatus(status) {
    if(status.status.localeCompare("success") == 0){
        $('.view-pane').hide();
        $('.alert-server-error').hide();
        showAlertSuccessDeleted();
    } else {
        showAlertServerError();
    }
}

function removeWeaponFromSelect() {
    $('#select-weapon :selected').remove();
}

function removeEquipmentFromSelect() {
    $('#select-equipment :selected').remove();
}

function removePersonageFromSelect() {
    $('#select-personage :selected').remove();
}
