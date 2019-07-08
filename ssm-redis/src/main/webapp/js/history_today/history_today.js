queryData();

/**
 *  获取数据
 */
function queryData() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../history/today",
        // headers: token,
        // data:data,
        success: function (resp) {
            //默认成功执行方法
            if (resp.code === 200 && resp.result.historyKey != null) {
                console.log('成功,使用查询后的数据！')
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result.historyKey)
                };
                showDatas(data);
                console.log('historyKey有的，从接口获取的');
            }
            if (resp.code === 200 && resp.result.historyKey == null) {
                console.log('成功，使用缓存数据！')
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result)
                };
                showDatas(data);
                console.log('historyKey没的，从redis获取的');
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
        // var count = queryObj.count;
        var currData = queryObj.item;
        if (currData.length !== 0) {
            for (var i = 0; i < currData.length; i++) {
                dataHtml += '<tr>';
                //序号
                dataHtml += '<td>' + (i + 1) + '</td>';
                //标题
                dataHtml += '<td>' + currData[i].title + '</td>';
                //万年历
                dataHtml += '<td>' + currData[i].lunar + '</td>';
                //图片
                dataHtml += '<td><img src=" ' + currData[i].pic + '"></td>';
                //简介
                dataHtml += '<td>' + currData[i].des + '</td>';
                dataHtml += '</tr>';
            }
        } else {
            dataHtml = '<tr><td colspan="11">暂无数据</td></tr>';
        }
        return dataHtml;


    }
    $(".content_body").html(renderData(queryObj));

}
//导出
$(".exportXls").click(function () {
    var url = "../history/export"
    window.location.href = url;
})