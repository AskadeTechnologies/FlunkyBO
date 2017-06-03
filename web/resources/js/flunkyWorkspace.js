/**
 * Created by AdrianIonita on 5/1/2017.
 */
var FlunkyWorkspace = function() {

}

FlunkyWorkspace.prototype.init = function() {
    this.urlPrefix = "";
    this.showDashboard();

}

FlunkyWorkspace.prototype.replaceSpaceString = function(key, value){
    if(typeof value === 'string'){
        if(key == 'rowStatus'){
            return undefined;
        }
        if(isEmpty(value.trim())){
            return undefined;
        }
        return value.trim();
    }
    return value;
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
    this.hideAllPages();
    $("#dashboard-page").show();
}

FlunkyWorkspace.prototype.hideAllPages = function(){
    $("#dashboard-page").hide();
    $("#appConfig-page").hide();
    $("#document-config").hide();
    $("#addressCategory-config").hide();
    $("#addressType-config").hide();
    $("#clientType-config").hide();
    $("#clientCategory-config").hide();
    $("#clientSubcategory-config").hide();
}

FlunkyWorkspace.prototype.showDocumentConfig = function(){
    this.documenTypeAction = new DocumentTypeAction();
    this.documenTypeAction.initDocumentTypePage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#document-config").show();
}


FlunkyWorkspace.prototype.showAddressCategoryConfig = function(){
    this.addressCategoryAction = new AddressCategoryAction();
    this.addressCategoryAction.initAddressCategoryPage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#addressCategory-config").show();
}

FlunkyWorkspace.prototype.showAddressTypeConfig = function(){
    this.addressTypeAction = new AddressTypeAction();
    this.addressTypeAction.initAddressTypePage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#addressType-config").show();
}


FlunkyWorkspace.prototype.showClientTypeConfig = function(){
    this.clientTypeAction = new ClientTypeAction();
    this.clientTypeAction.initClientTypePage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#clientType-config").show();
}

FlunkyWorkspace.prototype.showClientCategoryConfig = function(){
    this.clientCategoryAction = new ClientCategoryAction();
    this.clientCategoryAction.initClientCategoryPage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#clientCategory-config").show();
}

FlunkyWorkspace.prototype.showClientSubcategoryConfig = function(){
    this.clientSubcategoryAction = new ClientSubcategoryAction();
    this.clientSubcategoryAction.initClientSubcategoryPage();
    this.hideAllPages();
    $("#appConfig-page").show();
    $("#clientSubcategory-config").show();
}