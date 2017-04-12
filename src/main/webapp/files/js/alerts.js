function showAlertSuccessCreate() {
    $('.alert-successfully-created').show();
    hideAlertsByTimeOut(5000);
}

function showAlertSuccessUpdate() {
    $('.alert-successfully-updated').show();
    hideAlertsByTimeOut(5000);
}

function showAlertSuccessDeleted() {
    $('.alert-successfully-deleted').show();
}

function showAlertInvalidScores() {
    $('.alert-invalid-scores').show();
    hideAlertsByTimeOut(5000);
}

function showAlertServerError() {
    $('.alert-server-error').show();
    hideAlertsByTimeOut(5000);
}

function showAlertCommitDeletion() {
    $('.alert-commit-deletion').show();
}

function hideAlertsByTimeOut(time) {
    setTimeout(function () {
       $('.alert').fadeOut(300);
    }, time);
}
