//根据元素id判断出最小的那一个,且Info不为null
function getMinId() {
    var result = [];
    for (var index in globalDisks) {
        if (globalDisks[index].info === null) {
            result.push(globalDisks[index]);
        }
    }
    return Math.min.apply(null, getValuesByKey(result, "id"));

}

//移除某个元素
function remove(element) {
    var index = globalDisks.indexOf(element);
    if (index > -1) {
        globalDisks.splice(index, 1);
    }
}

//循环新的，如果旧的不包含新的那么就是新增的
//循环旧的，如果新的有减少那就是减少的
function compareDiff(arry1, arry2) {
    //如果旧的为空，则新的全是新增的
    if (arry1.length === 0) {
        return arry2;
    }
    var array = [];
    var array11 = getValuesByKey(arry1, "trainInfo");

    $.each(arry2, function (index, element) {
        if (array11.indexOf(element.trainInfo) < 0) {
            array.push(element);
        }
    });
    return array;
}

//根据对象，获取对象对应的某个属性的所有值
function getValuesByKey(objectArray, key) {
    var result = [];
    for (var index in objectArray) {
        result.push(objectArray[index][key]);
    }
    return result;
}

//根据对象的name的值获取对象
function getIdByName(objectArray, value, name, name1) {
    for (var index in objectArray) {
        if (objectArray[index][name][name1] === value) {
            return objectArray[index];
            break;
        }
    }
}

//根据对象的id修改name的值
function updateValueById(objectArray, value, id, name, obj) {
    for (var index in objectArray) {
        if (objectArray[index][id] === value) {
            objectArray[index][name] = obj;
            break;
        }
    }
}

function sessionExpired() {
    //每隔5分钟把用户信息清空
    setInterval(function () {
        console.log("time", "用户登录信息过期");
        sessionStorage.setItem("exportUser", null);
    }, 300000);
}

/*
* scanDiskSocket.onmessage = function (e) {

        //2.后台传过来的检测到的盘
        var data = JSON.parse(e.data);
        var disks = data.disks;
        var notInit = data.notInit;//没有初始化的盘
        console.log("未初始化的盘个数", notInit);
        if (notInit.length > 1) {//多个未初始化，请拔除
            layer.msg("多台设备未初始化，请拔出设备单个进行初始化");
            return;
        } else if (notInit.length === 1) {//只有一个未初始化，开始初始化
            $("#initModal").modal("show");
            $("#disknum").val(notInit[0].diskid);
            return;
        }

        //后台只管往来传，前台判断盘符的变化 用sessionStorage
        var oldDisks = JSON.parse(sessionStorage.getItem("oldDisks"));
        sessionStorage.setItem("oldDisks", JSON.stringify(disks));

        //计算出减少的盘
        var reducedDisks = compareDiff(disks, oldDisks);
        //计算出新增的盘
        var increasedDisks = compareDiff(oldDisks, disks);
        console.log("减少的盘", reducedDisks);
        console.log("增加的盘", increasedDisks);
        // console.log(globalDisks);
        // 根据减少的盘，移除页面样式,减少的是哪个位置的
        //要根据盘的value或去key
        if (reducedDisks.length > 0) {
            $.each(reducedDisks, function (index, disk) {
                var object = getIdByName(globalDisks, disk.trainInfo, "info", "trainInfo");
                console.log(object);
                //移除样式
                wipeGrid(object.id);
                //修改全局变量
                updateValueById(globalDisks, object.id, "id", "info", null);
            });
        }
        //先判断是否连接数据库
        var useDb = $("#useDb").val();
        if (increasedDisks.length > 0) {//length要大于0 不然没有盘也会验证
            if (useDb === "true") {
                //第一次插盘没有导出用户验证，需要验证一下
                var exportUser = JSON.parse(sessionStorage.getItem("exportUser"));
                console.log(exportUser);
                if (exportUser == null) {
                    $("#confirmModal").modal("show");
                } else {
                    //判断是否有未初始化的盘
                    beginExport(increasedDisks);
                }
            } else {
                beginExport(increasedDisks);
            }
        }
    };

    function beginExport(increasedDisks) {
        $.each(increasedDisks, function (index, disk) {
            var min = getMinId();
            updateValueById(globalDisks, min, "id", "info", disk);
            fillGrid(min, disk);
            exportData();
        });
    }
* */