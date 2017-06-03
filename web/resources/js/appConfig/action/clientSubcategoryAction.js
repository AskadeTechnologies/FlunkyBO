/**
 * Created by AdrianIonita on 6/2/2017.
 */
var ClientSubcategoryAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

ClientSubcategoryAction.prototype.initClientSubcategoryPage = function() {
    this.getClientSubcategoryList();
}


ClientSubcategoryAction.prototype.getClientSubcategoryList = function(){
    this.clientCategories;
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientCategoriesLov", type: 'GET',
        async: false,
        data: [], dataType: 'json', contentType: "application/json",
        success: function (result) {
            self.clientCategories = result.data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Error setting new Document Type');
            console.log(jqXHR);
        }
    });

    var table = 'clientSubcategory-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#clientSubcategory-table").jqGrid('clearGridData');
        this.refreshClientSubcategory();
    } else {
        this.clientSubcategoryList();
    }
}

ClientSubcategoryAction.prototype.refreshClientSubcategory = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientSubcategories",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#clientSubcategory-table");
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

ClientSubcategoryAction.prototype.afterGridLoad = function(){
}


ClientSubcategoryAction.prototype.clientSubcategoryList = function() {

    self = this;
    $grid = $("#clientSubcategory-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getClientSubcategories",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id', "Category",'Code','Description','Data in', 'Data out', 'Save'],
        colModel:[{name:'id', index:'id', hidden: false, editable: true},
            { name: 'categoryId', index: 'categoryId', editable: true, edittype: "select", formatter: 'select' },
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

            $('#clientSubcategory-table').setColProp('categoryId', { editoptions: { value: self.clientCategories} });

            $('#gview_clientSubcategory-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_clientSubcategory-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


ClientSubcategoryAction.prototype.saveClientSubcategory = function(){
    var ids = jQuery("#clientSubcategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#clientSubcategory-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#clientSubcategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#clientSubcategory-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("client category "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "categoryId", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postClientSubcategory", type: 'POST',
                data: JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "categoryId", "code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
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

ClientSubcategoryAction.prototype.addClientSubcategory = function(){
    var rowId = $.jgrid.randId();
    jQuery("#clientSubcategory-table").addRowData( rowId, new ClientSubcategoryObject("INSERT"));
    $("#clientSubcategory-table").jqGrid('editRow', rowId,true);
}