/**
 * Created by AdrianIonita on 6/2/2017.
 */

var AddressCategoryAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

AddressCategoryAction.prototype.initAddressCategoryPage = function() {
    this.getAddressCategoryList();
}


AddressCategoryAction.prototype.getAddressCategoryList = function(){
    var table = 'addressCategory-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#addressCategory-table").jqGrid('clearGridData');
        this.refreshAddressCategory();
    } else {
        this.addressCategoryList();
    }
}

AddressCategoryAction.prototype.refreshAddressCategory = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getAddressCategories",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#addressCategory-table");
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

AddressCategoryAction.prototype.afterGridLoad = function(){
}


AddressCategoryAction.prototype.addressCategoryList = function() {

    self = this;
    $grid = $("#addressCategory-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getAddressCategories",
        styleUI : 'Bootstrap',
        datatype: "json",
        colNames:['Id','Code','Description','Data in', 'Data out', 'Save'],
        colModel:[{name:'id', index:'id', hidden: false, editable: true},
            {name:'code', index:'code', hidden: false, editable: true},
            {name:'description', index:'description', hidden: false,editable: true },
            {name:'dateIn',
                index:'dateIn',
                hidden: false,
                align: 'center',
                formatter: 'date',
                editable: true,
                editoptions: {
                    size: 20,
                    maxlengh: 10,
                    dataInit: function (element) {
                        $(element).datepicker({ dateFormat: 'd-m-yy' });//{ dateFormat: 'd-mm-yy' });
                    }
                }/*,
                editrules: {
                    date: true,
                    minValue: 0
                }*/
            } ,
            {name:'dateOut', index:'dateOut', hidden: false,
                align: 'center',
                formatter: 'date',
                editable: true,
                editoptions: {
                    size: 20,
                    maxlengh: 10,
                    dataInit: function (element) {
                        $(element).datepicker({ dateFormat: 'd-m-yy' });//{ dateFormat: 'd-mm-yy' });
                    }
                }/*,
                editrules: {
                    date: fa,
                    minValue: 0
                }*/
            },
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

            $('#gview_addressCategory-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_addressCategory-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


AddressCategoryAction.prototype.saveAddressCategory = function(){

    //var _row = jQuery("#documentType-table").jqGrid("getRowData", rowid);
    var ids = jQuery("#addressCategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#addressCategory-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#addressCategory-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = null;
        _row = jQuery("#addressCategory-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("address category "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postAddressCategory", type: 'POST',
                data: JSON.stringify(_row, flunkyWorkspace.replaceSpaceString, ["id", "code","description","dateIn","dateOut"]), dataType: 'json', contentType: "application/json",
                success: function (result) {
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Error setting new Address Category');
                    console.log(jqXHR);
                }
            });
        }
    }

}

AddressCategoryAction.prototype.addNewAddressCategory = function(){
    var rowId = $.jgrid.randId();
    jQuery("#addressCategory-table").addRowData( rowId, new AddressCategoryObject("INSERT"));
    $("#addressCategory-table").jqGrid('editRow', rowId,true);
}