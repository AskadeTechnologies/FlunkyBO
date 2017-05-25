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
        url: flunkyWorkspace.urlPrefix+"/config/client/getDocumentTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
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
        url: flunkyWorkspace.urlPrefix+"/config/client/getDocumentTypes",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id','Code','Description','Data in', 'Data out', 'Save'],
        colModel:[{name:'id', index:'id', hidden: true},
            {name:'code', index:'code', hidden: false, editable: true},
            {name:'description', index:'description', hidden: false,editable: true },
            {name:'dateIn',
                index:'dateIn',
                hidden: false,
                align: 'right',
                formatter: 'date',
                editable: true,
                editoptions: {
                    size: 20,
                    maxlengh: 10,
                    dataInit: function (element) {
                        $(element).datepicker();
                    }
                },
                editrules: {
                    date: true,
                    minValue: 0
                }} ,
            {name:'dateOut', index:'dateOut', hidden: false,
                align: 'right',
                formatter: 'date',
                editable: true,
                editoptions: {
                    size: 20,
                    maxlengh: 10,
                    dataInit: function (element) {
                        $(element).datepicker();
                    }
                },
                editrules: {
                    date: true,
                    minValue: 0
                }},
            {name:'rowStatus', index:'rowStatus', hidden: true}],
        rowNum:10, rowList:[5,10,20],
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        autowidth:true,
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
                flunkyWorkspace.showError(data.statusMessage);
            }
                $('#gview_docTypes-table div.ui-jqgrid-bdiv').css({
                    "height":"500px",
                    "overflow":"hidden"
                });
                $('#gview_docTypes-table div.ui-jqgrid-bdiv').perfectScrollbar();
            /*
            $('#gview_reportmapping-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_reportmapping-table div.ui-jqgrid-bdiv').perfectScrollbar();
            */
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


DocumentTypeAction.prototype.saveNewDocType = function(){

    //var _row = jQuery("#documentType-table").jqGrid("getRowData", rowid);
    var ids = jQuery("#documentType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#documentType-table").jqGrid('saveRow',ids[i], false, 'clientArray');
        var _row = jQuery("#documentType-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("document type "+JSON.stringify(_row, ["code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postNewDocumentType", type: 'POST',
                data: JSON.stringify(_row, ["code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
                success: function (result) {
                    //FlunkyWorkspace.actions().getProductAction().newAndEditedProductListTable(result.data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Error setting new Document Type');
                }
            });
        }
    }

}

DocumentTypeAction.prototype.addNewDocumentType = function(){
    jQuery("#documentType-table").addRowData( '1', new DocumentTypeObject("INSERT"));
    $("#documentType-table").jqGrid('editRow', '1',true);
    //saveRow = "<button id=\"btn-saveDocType\" type=\"button\" class=\"btn btn-block btn-primary\" onclick=\"FlunkyWorkspace.saveNewDocType("+'1'+")\" >Save</button>";

    //jQuery("#documentType-table").jqGrid('setRowData', '1', {save:saveRow});
}