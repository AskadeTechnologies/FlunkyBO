/**
 * Created by AdrianIonita on 5/31/2017.
 */
var ClientTypeAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

ClientTypeAction.prototype.initClientTypePage = function() {
    this.getClientTypeList();
}


ClientTypeAction.prototype.getClientTypeList = function(){
    var table = 'clientType-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#clientType-table").jqGrid('clearGridData');
        this.refreshClientType();
    } else {
        this.clientTypeList();
    }
}

ClientTypeAction.prototype.refreshClientType = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#clientType-table");
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

ClientTypeAction.prototype.afterGridLoad = function(){
}


ClientTypeAction.prototype.clientTypeList = function() {

    self = this;
    $grid = $("#clientType-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientTypes",
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

            $('#gview_clientType-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_clientType-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


ClientTypeAction.prototype.saveClientType = function(){
    var ids = jQuery("#clientType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#clientType-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#clientType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#clientType-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("client Type "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString,["id", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postNewClientType", type: 'POST',
                data: JSON.stringify(_row, ["id", flunkyWorkspace.replaceSpaceString, "code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
                success: function (result) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Error setting new Client Type');
                    console.log(jqXHR);
                }
            });
        }
    }

}

ClientTypeAction.prototype.addClientType = function(){
    var rowId = $.jgrid.randId();
    jQuery("#clientType-table").addRowData( rowId, new ClientTypeObject("INSERT"));
    $("#clientType-table").jqGrid('editRow', rowId,true);
}