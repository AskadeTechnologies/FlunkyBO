/**
 * Created by AdrianIonita on 5/1/2017.
 */


var DocumentTypeAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

DocumentTypeAction.prototype.initDocumentTypePage = function() {
    this.getDocumentTypeList();
}


DocumentTypeAction.prototype.getDocumentTypeList = function(){

    var table = 'documentType-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#documentType-table").jqGrid('clearGridData');
        this.refreshDocumentTypes();
    } else {
        //workspace.actions().getProductAction().newAndEditedProductListTable(result.data, table);
        this.documentTypeList();
    }

    //$('#pickAccountDialog').modal('show');

}

DocumentTypeAction.prototype.refreshDocumentTypes = function(){
    self = this;
    $.ajax({
        url: workspace.urlPrefix+"/config/clients/getDocumentTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                workspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#documentType-table");
                $grid.jqGrid('clearGridData')
                    .jqGrid('setGridParam',{data:resultedData.data}).trigger('reloadGrid', [{ page: 1}]);

                self.afterGridLoad();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Error: '+textStatus+' '+errorThrown);
        }
    });
}

DocumentTypeAction.prototype.afterGridLoad = function(){
    /*
    var ids = jQuery("#documentType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {

        var _row = jQuery("#documentType-table").jqGrid("getRowData", ids[i]);
        genereaza = "<button id=\"btn-run-report\" type=\"button\" class=\"btn btn-block btn-primary\" onclick=\"workspace.runReport("+ids[i]+")\" >Genereaza</button>";
        download = "<button id=\"btn-download-report\" type=\"button\" class=\"btn btn-block btn-primary\" onclick=\"workspace.downloadReport("+ids[i]+")\" >Download</button>";

        jQuery("#documentType-table").jqGrid('setRowData', ids[i], {generate:genereaza});
        jQuery("#documentType-table").jqGrid('setRowData', ids[i], {download:download});
    }*/
}


DocumentTypeAction.prototype.documentTypeList = function() {

    self = this;
    $grid = $("#documentType-table");
    $grid.jqGrid({
        url: workspace.urlPrefix+"/config/clients/getDocumentTypes",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id','Code','Description','Data in', 'Data out', 'Save'],
        colModel:[{name:'id', index:'id', hidden: true},
            {name:'code', index:'code', hidden: false, editable: true},
            {name:'description', index:'description', hidden: false,editable: true },
            {name:'dataIn', index:'dataIn', hidden: false, editable: true},
            {name:'dataOut', index:'dataOut', hidden: false, editable: true},
            {name:'save', index:'save', hidden: false}],
        rowNum:30, rowList:[10,20,30],
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        shrinkToFit: true,
        autowidth:true,
        forceFit:true,
        pager: '#pager',
        width: '100%',
        height: '100%',
        loadonce: true,
        pager: '#documentTypeTablePager',
        jsonReader: {
            root: "data"
        },
        gridComplete: function(){
            self.afterGridLoad();

        },
        loadComplete: function(data) {
            if(data.status == "ERROR"){
                workspace.showError(data.statusMessage);
            }

            $('#gview_reportmapping-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_reportmapping-table div.ui-jqgrid-bdiv').perfectScrollbar();
        }/*,
         onSelectRow: function(id) {
         var grid = $("#productspecmapping-table");
         var lastSel = workspace.actions().getProductAction().specTableSelection;

         if (id && id !== lastSel) {
         //Validation ? (else restoreRow)
         grid.jqGrid('saveRow', lastSel, false, 'clientArray');
         var editedSpecIds = workspace.actions().getProductAction().editedSpecIds;
         if (editedSpecIds.indexOf(lastSel) < 0) {
         editedSpecIds.push(lastSel);
         }
         grid.jqGrid('editRow', id, true, null, null, 'clientArray');
         workspace.actions().getProductAction().specTableSelection = id;
         }
         }*/
    });
}