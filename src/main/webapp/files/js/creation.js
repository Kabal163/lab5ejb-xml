<!-- Creation -->

function createWeapon() {
    if(isTotalScoresValid()) {
        var weaponData = $('#form-weapon-creation').serialize();
        $.ajax({
            url: "/Lab4-ejb/weaponHandling",
            type: "post",
            headers: {"action": "create"},
            data: weaponData,
            dataType: "json",
            success: function (response) {
                showCreationStatus(response);
                recountLvlScores();
            }
        });
    } else {
        showAlertInvalidScores();
    }
}

function createPersonage() {
    var personageData = $('#form-personage-creation').serialize();
    $.ajax({
        url:"/Lab4-ejb/personageHandling",
        type: "post",
        headers: {"action":"create"},
        data: personageData,
        dataType: "json",
        success: function (response) {
            showCreationStatus(response);
        }
    });
}

function createEquipment() {
    if(isTotalScoresValid()) {
        var equipmentData = $('#form-equipment-creation').serialize();
        $.ajax({
            url: "/Lab4-ejb/equipmentHandling",
            type: "post",
            headers: {"action":"create"},
            data: equipmentData,
            dataType: "json",
            success: function (response) {
                showCreationStatus(response);
                recountLvlScores();
            }
        });
    } else {
        showAlertInvalidScores();
    }
}

function showCreationStatus(status) {
    if(status.status.localeCompare("success") == 0) {
        setToZeroStatistics();
        showAlertSuccessCreate();
    } else {
        showAlertServerError();
    }
}

