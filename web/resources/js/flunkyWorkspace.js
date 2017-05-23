/**
 * Created by AdrianIonita on 5/1/2017.
 */
var FlunkyWorkspace = function() {

}

FlunkyWorkspace.prototype.init = function() {
    this.urlPrefix = "";
    this.showDashboard();
}

function isEmpty(value){
    return (value == null || value.length === 0);
}

FlunkyWorkspace.prototype.showError = function(errorMessage) {
    document.getElementById("error-message-text").innerHTML = errorMessage;
    $('#success-message').hide();
    $('#error-message').show();
}

FlunkyWorkspace.prototype.showSuccess = function(successMessage) {
    document.getElementById("success-message-text").innerHTML = successMessage;
    $('#success-message').show();
    $('#error-message').hide();
}

FlunkyWorkspace.prototype.hideMessages = function() {
    $('#success-message').hide();
    $('#error-message').hide();
}

FlunkyWorkspace.prototype.showDashboard = function(){
    $("#dashboard-page").show();
}

FlunkyWorkspace.prototype.showDocumentConfig = function(){
    this.documenTypeAction = new DocumentTypeAction();
    this.documenTypeAction.initDocumentTypePage();
    $("#dashboard-page").hide();
    $("#appConfig-page").show();
    $("#client-config").show();
}
