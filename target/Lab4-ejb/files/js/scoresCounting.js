var lvlScores = 0;
var currentScores = 0;
var spentScores = 0;

$(document).ready(initValues);

function initValues() {
    defineLvlScores();
    defineSpentScores();
    defineCurrentScores();
    showCurrentScores();
}

function defineLvlScores() {
    var lvl = parseInt($('.lvl-select').val());
    switch (lvl){
        case 1: lvlScores = 1000;
            break;
        case 2: lvlScores = 1200;
            break;
        case 3: lvlScores = 1500;
            break;
        case 4: lvlScores = 2000;
            break;
        case 5: lvlScores = 3000;
            break;
    }
}

function defineSpentScores() {
    $('.statistics').each(function () {
        var sc = $(this).val();
        if(sc.length > 0){
            spentScores += parseInt(sc);
        } else {
            spentScores = 0;
        }
    });
}

function defineCurrentScores() {
    currentScores = lvlScores - spentScores;
}

function showCurrentScores() {
    $('.scores').html(currentScores);
}

function recountLvlScores() {
    defineLvlScores();
    setToZeroStatistics();
    spentScores = 0;
    defineCurrentScores();
    showCurrentScores();
}

function setToZeroStatistics() {
    $('.statistics').each(function () {
        $(this).val('');
    });
}

function minusScores(scores) {
    if(scores.length == 0){
        return false;
    }
    if(scores >= 0 && scores <= currentScores){
        spentScores += parseInt(scores);
        currentScores -= scores;
        showCurrentScores();
    } else {
        spentScores += parseInt(scores);
        currentScores -= scores;
        showAlertInvalidScores();
    }
}

function addScores(scores) {
    if(scores.length == 0){
        return false;
    }
    if(scores >= 0 && scores <= spentScores){
        spentScores -= scores;
        currentScores += parseInt(scores);
        showCurrentScores();
    } else {
        spentScores -= scores;
        currentScores += parseInt(scores);
        showAlertInvalidScores();
    }
}

function isTotalScoresValid() {
    if (spentScores <= lvlScores && !isScoresNegative()){
        return true;
    } else return false;
}

function isScoresNegative() {
    var isNegative = false;
    $('.statistics').each(function () {
        console.log($(this).val());
        if($(this).val() < 0) isNegative = true;
    });
    return isNegative;
}