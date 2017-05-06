/**
 * Created by AdrianIonita on 5/1/2017.
 */
var Workspace = function() {

}

Workspace.prototype.init = function() {
    this.urlPrefix = "";
    this.showDashboard();
}

function isEmpty(value){
    return (value == null || value.length === 0);
}

Workspace.prototype.showError = function(errorMessage) {
    document.getElementById("error-message-text").innerHTML = errorMessage;
    $('#success-message').hide();
    $('#error-message').show();
}

Workspace.prototype.showSuccess = function(successMessage) {
    document.getElementById("success-message-text").innerHTML = successMessage;
    $('#success-message').show();
    $('#error-message').hide();
}

Workspace.prototype.hideMessages = function() {
    $('#success-message').hide();
    $('#error-message').hide();
}

Workspace.prototype.showDashboard = function(){
    $("#dashboard-page").show();
}

Workspace.prototype.showAppConfig = function(){
    this.documenTypeAction = new DocumentTypeAction();
    this.documenTypeAction.initDocumentTypePage();
    $("#dashboard-page").hide();
    $("#appConfig-page").show();
    $("#client-config").show();
}

Workspace.prototype.saveNewDocType = function(rowid){

    var _row = jQuery("#documentType-table").jqGrid("getRowData", rowid);
    $.ajax({
        url: "/addNewDocumentType", type: 'POST',
        data: JSON.stringify(_row), dataType: 'json', contentType: "application/json",
        success: function (result) {
            //workspace.actions().getProductAction().newAndEditedProductListTable(result.data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Error setting new Document Type');
        }
    });
}

Workspace.prototype.addNewDocumentType = function(){
    jQuery("#documentType-table").addRowData( '1', new DocumentTypeObject());
    $("#documentType-table").jqGrid('editRow', '1',true);
    saveRow = "<button id=\"btn-saveDocType\" type=\"button\" class=\"btn btn-block btn-primary\" onclick=\"workspace.saveNewDocType("+'1'+")\" >Save</button>";

    jQuery("#documentType-table").jqGrid('setRowData', '1', {save:saveRow});
}