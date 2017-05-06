/**
 * Created by AdrianIonita on 5/1/2017.
 */
var workspace = new Workspace();

$(document).ready(function() {
    workspace.init();
});

$(document).ajaxStart(function() { Pace.restart(); });