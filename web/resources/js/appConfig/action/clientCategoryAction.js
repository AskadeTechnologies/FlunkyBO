/**
 * Created by AdrianIonita on 6/2/2017.
 */
var ClientCategoryAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

ClientCategoryAction.prototype.initClientCategoryPage = function() {
    this.getClientCategoryList();
}


ClientCategoryAction.prototype.getClientCategoryList = function(){
    var table = 'clientCategory-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#clientCategory-table").jqGrid('clearGridData');
        this.refreshClientCategory();
    } else {
        this.clientCategoryList();
    }
}

ClientCategoryAction.prototype.refreshClientCategory = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientCategories",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#clientCategory-table");
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

ClientCategoryAction.prototype.afterGridLoad = function(){
}


ClientCategoryAction.prototype.clientCategoryList = function() {

    self = this;
    $grid = $("#clientCategory-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientCategories",
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

            $('#gview_clientCategory-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_clientCategory-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


ClientCategoryAction.prototype.saveClientCategory = function(){
    var ids = jQuery("#clientCategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#clientCategory-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#clientCategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#clientCategory-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("client category "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postClientCategory", type: 'POST',
                data: JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
                success: function (result) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Error setting new Document Type');
                    console.log(jqXHR);
                }
            });
        }
    }

}

ClientCategoryAction.prototype.addClientCategory = function(){
    var rowId = $.jgrid.randId();
    jQuery("#clientCategory-table").addRowData( rowId, new ClientCategoryObject("INSERT"));
    $("#clientCategory-table").jqGrid('editRow', rowId,true);
}