function submitWeaponChanges() {
    if(isTotalScoresValid()){
        var weapon = $('#form-weapon-edit').serialize();
        $.ajax({
            url: "/Lab4-ejb/weaponHandling",
            type: "post",
            headers: {"action":"update"},
            data: weapon,
            dataType: "json",
            success: function (response) {
                showUpdatingStatus(response);
            }
        });
    } else {
        showAlertInvalidScores();
    }
}

function submitEquipmentChanges() {
    if(isTotalScoresValid()){
        var equipment = $('#form-equipment-edit').serialize();
        $.ajax({
            url: "/Lab4-ejb/equipmentHandling",
            type: "post",
            headers: {"action":"update"},
            data: equipment,
            dataType: "json",
            success: function (response) {
                showUpdatingStatus(response);
            }
        });
    } else {
        showAlertInvalidScores();
    }
}

function submitPersonageChanges() {
    var personage = $('#form-personage-edit').serialize();
    $.ajax({
        url: "/Lab4-ejb/personageHandling",
        type: "post",
        headers: {"action": "update"},
        data: personage,
        dataType: "json",
        success: function (response) {
            showUpdatingStatus(response);
        }
    });
}

function showUpdatingStatus(status) {
    if(status.status.localeCompare("success") == 0) {
        showAlertSuccessUpdate();
    } else {
        showAlertServerError();
    }
}
