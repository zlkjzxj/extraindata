var html = "";
html = "<option value=''>--请选择车队--</option>";
$("#input_city").append("<option value=''>--请选择车组--</option>");
$("#input_area").append("<option value=''>--请选择车次--</option>");
$("#input_iden").append("<option value=''>--请选择编号--</option>");
$.each(pdata, function (idx, item) {
    if (parseInt(item.bmjb) == 4) {
        html += "<option value='" + item.bmjc + "' exid='" + item.glbm + "'>" + item.bmjc + "</option>";
    }
});
$("#input_province").append(html);
$("#input_province").change(function () {
    $("#cdnum").val("");
    $("#cznum").val("");
    $("#ccnum").val("");
    $("#bhnum").val("");
    $("#input_city option").remove();
    $("#input_area option").remove();
    $("#input_iden option").remove();
    $("#input_city").append("<option value=''>--请选择车组--</option>");
    $("#input_area").append("<option value=''>--请选择车次--</option>");
    $("#input_iden").append("<option value=''>--请选择编号--</option>");
    if ($(this).val() == "") {
        $("#input_city").find('option:eq(0)').attr('selected', true);
        $("#input_area").find('option:eq(0)').attr('selected', true);
        $("#input_iden").find('option:eq(0)').attr('selected', true);
        return;
    }
    var glbm = $(this).find("option:selected").attr("exid");

    $("#cdnum").val($(this).val());
    console.log(glbm, $("#cdnum").val());
    $("#ccxx").text($("#cdnum").val());
    var html = "";
    $.each(pdata, function (idx, item) {
        if (parseInt(item.bmjb) == 5 && glbm == item.sjbm) {
            html += "<option value='" + item.bmjc + "' exid='" + item.glbm + "'>" + item.bmjc + "</option>";
        }
    });
    $("#input_city").append(html);
});
$("#input_city").change(function () {
    $("#input_area option").remove();
    $("#input_iden option").remove();
    $("#input_area").append("<option value=''>--请选择车次--</option>");
    $("#input_iden").append("<option value=''>--请选择编号--</option>");
    if ($(this).val() == "") {
        $("#input_area").find('option:eq(0)').attr('selected', true);
        $("#input_iden").find('option:eq(0)').attr('selected', true);
        return;
    }
    $("#cznum").val("");
    $("#ccnum").val("");
    $("#bhnum").val("");
    var glbm = $(this).find("option:selected").attr("exid");
    $("#cznum").val($(this).val());
    $("#ccxx").text($("#cdnum").val() + "-" + $("#cznum").val());
    var html = "";
    $.each(pdata, function (idx, item) {
        if (parseInt(item.bmjb) == 6 && glbm == item.sjbm) {
            html += "<option value='" + item.bmjc + "' exid='" + item.glbm + "'>" + item.bmjc + "</option>";
        }
    });
    $("#input_area").append(html);
});
$("#input_area").change(function () {
    $("#input_iden option").remove();
    $("#input_iden").append("<option value=''>--请选择设备编号--</option>");
    if ($(this).val() == "") {
        $("#input_iden").find('option:eq(0)').attr('selected', true);
        return;
    }
    $("#ccnum").val("");
    $("#bhnum").val("");
    var glbm = $(this).find("option:selected").attr("exid");
    $("#ccnum").val($(this).val());
    $("#ccxx").text($("#cdnum").val() + "-" + $("#cznum").val() + "-" + $("#ccnum").val());
    var html = "";
    $.each(pdata, function (idx, item) {
        if (parseInt(item.bmjb) == 7 && glbm == item.sjbm) {
            html += "<option value='" + item.bmjc + "' exid='" + item.glbm + "'>" + item.bmjc + "</option>";
        }
    });
    $("#input_iden").append(html);
});
$("#input_iden").change(function () {
    if ($(this).val() == "") {
        $("#bhnum").val("");
        return;
    }
    $("#bhnum").val($(this).val());
    $("#ccxx").text($("#cdnum").val() + "-" + $("#cznum").val() + "-" + $("#ccnum").val() + "-" + $("#bhnum").val());
});
