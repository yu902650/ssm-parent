queryData();

/**
 * 获取查询的页面参数
 */
function getQueryObj() {
    var data = {};
    var phone = $("#phone").val();
    var key = "1cc7cea7c2e80d7486d0ad6903611e71" ;
    if ($.trim(phone) != '') {
        data.phone = phone;
    }
    data.key = key;
    alert(data);
    return data;

}
/**
 *  获取数据
 */
function queryData(queryObj) {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "../phone_belongs/writePhoneNo",
        data:queryObj,
        success: function (resp) {
            //默认成功执行方法
            if (resp.code === 200) {
                console.log('成功！')
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result.phoneKey)
                };
                showDatas(data);
            }
        },
        error: function () {
            //默认异常执行方法
            console.log('失败！')
        }
    })
}

/**
 * 渲染数据
 */
function showDatas(queryObj) {
    //渲染数据
    function renderData(queryObj) {
        var dataHtml = '';
        console.log(queryObj);

        var count = queryObj.count;
        var currData = queryObj.item;

        if (count != 0) {
            for (var i = 0; i < 1; i++) {
                dataHtml += '<tr>';
                //序号
                dataHtml += '<td>' + (i + 1) + '</td>';
                //	省份
                dataHtml += '<td>' + currData[i].province + '</td>';
                //	城市
                dataHtml += '<td>' + currData[i].city + '</td>';
                //	运营商
                dataHtml += '<td><img src=" ' + currData[i].company + '"></td>';
                dataHtml += '</tr>';
            }
        } else {
            dataHtml = '<tr><td colspan="11">暂无数据</td></tr>';
        }
        return dataHtml;


    }

    $(".content_body").html(renderData(queryObj));

}


//查询
$(".search_btn").click(function () {
    queryData(queryObj);
    layer.close(index);
})
