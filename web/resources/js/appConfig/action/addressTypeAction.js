/**
 * Created by AdrianIonita on 6/2/2017.
 */

var AddressTypeAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

AddressTypeAction.prototype.initAddressTypePage = function() {
    this.getAddressTypeList();
}


AddressTypeAction.prototype.getAddressTypeList = function(){
    this.addressCategories;
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getAddressCategoriesLov", type: 'GET',
        async: false,
        data: [], dataType: 'json', contentType: "application/json",
        success: function (result) {
            self.addressCategories = result.data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('Error setting new Document Type');
            console.log(jqXHR);
        }
    });
    var table = 'addressType-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#addressType-table").jqGrid('clearGridData');
        this.refreshAddressType();
    } else {
        this.addressTypeList();
    }
}

AddressTypeAction.prototype.refreshAddressType = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getAddressTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#addressType-table");
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

AddressTypeAction.prototype.afterGridLoad = function(){
}


AddressTypeAction.prototype.addressTypeList = function() {

    self = this;
    $grid = $("#addressType-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getAddressTypes",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id','Category', 'Code','Description','Data in', 'Data out', 'Save'],
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
                        $(element).datepicker({ dateFormat: 'd-m-yy' });
                        //{ dateFormat: 'd-mm-yy' });
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
                        $(element).datepicker({ dateFormat: 'd-m-yy' });
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

            $('#addressType-table').setColProp('categoryId', { editoptions: { value: self.addressCategories} });

            $('#gview_addressType-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_addressType-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


AddressTypeAction.prototype.saveAddressType = function(){
    var ids = jQuery("#addressType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#addressType-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#addressType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#addressType-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("address category "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString,["id", "categoryId", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postAddressType", type: 'POST',
                data: JSON.stringify(_row, flunkyWorkspace.replaceSpaceString,["id", "categoryId", "code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
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

AddressTypeAction.prototype.addNewAddressType = function(){
    var rowId = $.jgrid.randId();
    jQuery("#addressType-table").addRowData( rowId, new AddressTypeObject("INSERT"));
    $("#addressType-table").jqGrid('editRow', rowId,true);
}