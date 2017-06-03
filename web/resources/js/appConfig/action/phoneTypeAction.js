/**
 * Created by AdrianIonita on 6/3/2017.
 */
var PhoneTypeAction = function() {
    this.editedSpecIds = [];
    this.specTableSelection = null;
}

PhoneTypeAction.prototype.initPhoneTypePage = function() {
    this.getPhoneTypeList();
}


PhoneTypeAction.prototype.getPhoneTypeList = function(){
    var table = 'phoneType-table';
    if ($('#gview_'+table+' div.ui-jqgrid-bdiv').length > 0) {
        $("#phoneType-table").jqGrid('clearGridData');
        this.refreshPhoneType();
    } else {
        this.phoneTypeList();
    }
}

PhoneTypeAction.prototype.refreshPhoneType = function(){
    self = this;
    $.ajax({
        url: flunkyWorkspace.urlPrefix+"/config/client/getPhoneTypes",
        type: "GET",
        data: [],
        dataType: 'json',
        contentType: "application/json",
        success: function (resultedData) {
            if(resultedData.status == "ERROR"){
                flunkyWorkspace.showError(resultedData.statusMessage);
            }else{
                $grid = $("#phoneType-table");
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

PhoneTypeAction.prototype.afterGridLoad = function(){
}


PhoneTypeAction.prototype.phoneTypeList = function() {

    self = this;
    $grid = $("#phoneType-table");
    $grid.jqGrid({
        url: flunkyWorkspace.urlPrefix+"/config/client/getPhoneTypes",
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

            $('#gview_phoneType-table div.ui-jqgrid-bdiv').css({
                "height":"500px",
                "overflow":"hidden"
            });
            $('#gview_phoneType-table div.ui-jqgrid-bdiv').perfectScrollbar();

        }
    });
}


PhoneTypeAction.prototype.savePhoneType = function(){
    var ids = jQuery("#phoneType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        jQuery("#phoneType-table").jqGrid('saveRow', ids[i], false, 'clientArray');
    }
    var ids = jQuery("#phoneType-table").jqGrid('getDataIDs');
    for(var i=0;i <ids.length; i++) {
        var _row = jQuery("#phoneType-table").jqGrid("getRowData", ids[i]);
        if(_row.rowStatus == "INSERT"){
            console.log("phone Type "+JSON.stringify(_row, flunkyWorkspace.replaceSpaceString,["id", "code","description","dateIn","dateOut"]));
            $.ajax({
                url: flunkyWorkspace.urlPrefix+"/config/client/postPhoneType", type: 'POST',
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

PhoneTypeAction.prototype.addPhoneType = function(){
    var rowId = $.jgrid.randId();
    jQuery("#phoneType-table").addRowData( rowId, new PhoneTypeObject("INSERT"));
    $("#phoneType-table").jqGrid('editRow', rowId,true);
}