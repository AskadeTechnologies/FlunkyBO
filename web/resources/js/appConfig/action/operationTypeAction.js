/**
 * Created by AdrianIonita on 6/4/2017.
 */
var OperationTypeAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

OperationTypeAction.prototype.initOperationTypePage = function() {
    this.getOperationTypeList();
}


OperationTypeAction.prototype.getOperationTypeList = function(){
    var table = 'operationType-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#operationType-table").jqGrid('clearGridData');
        this.refreshOperationType();
    } else {
        this.operationTypeList();
    }
}

OperationTypeAction.prototype.refreshOperationType = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/opreation/getOperationTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#operationType-table");
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

OperationTypeAction.prototype.afterGridLoad = function(){
}


OperationTypeAction.prototype.operationTypeList = function() {

    self = this;
    $grid = $("#operationType-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/operation/getOperationTypes",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id','Code','Description','Data in', 'Data out', 'Save'],
        colModel:[{name:'id', index:'id', hidden: false, editable: true},
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
                        $(element).datepicker({ dateFormat: 'd-mm-yy' });
                    }
                }/*,
             editrules: {
             date: true,
             minValue: 0
             }*/} ,
            {name:'dateOut', index:'dateOut', hidden: false,
                align: 'right',
                formatter: 'date',
                editable: true,
                editoptions: {
                    size: 20,
                    maxlengh: 10,
                    dataInit: function (element) {
                        $(element).datepicker({ dateFormat: 'd-mm-yy' });
                    }
                }/*,
             editrules: {
             date: true,
             minValue: 0
             }*/},
            {name:'rowStatus', index:'rowStatus', hidden: true}],
        rowNum:10, rowList:[5,10,20],
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        autowidth:true,
        shrinkToFit: true,
        forceFit:true,
        height:"100%",
        width: '100%',
        loadonce: true,
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

            $('#gview_operationType-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_operationType-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


OperationTypeAction.prototype.saveOperationType = function(){
    var ids = jQuery("#operationType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#operationType-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#operationType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#operationType-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("operation Type "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString,["id", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/operation/addOperationType", type: 'POST',
                data: JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id","code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
                success: function (result) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Error setting new phone Type');
                    console.log(jqXHR);
                }
            });
        }
    }

}

OperationTypeAction.prototype.addOperationType = function(){
    var rowId = $.jgrid.randId();
    jQuery("#operationType-table").addRowData( rowId, new OperationTypeObject("INSERT"));
    $("#operationType-table").jqGrid('editRow', rowId,true);
}