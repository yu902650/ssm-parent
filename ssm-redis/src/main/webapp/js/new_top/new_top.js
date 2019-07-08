queryData();

/**
 *  获取数据
 */
function queryData() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "../news_api/getNews",
        // headers: token,
        // data:data,
        success: function (resp) {
            //默认成功执行方法
            if (resp.code === 200 && resp.result.data != null) {
                console.log('成功,使用查询后的数据！')
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result.historyKey)
                };
                showDatas(data);
            }
            if (resp.code === 200 && resp.result.data == null) {
                console.log('成功，使用缓存数据！')
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result.data)
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
            for (var i = 0; i < 20; i++) {
                dataHtml += '<tr>';
                //序号
                dataHtml += '<td>' + (i + 1) + '</td>';
                //标题
                dataHtml += '<td>' + currData[i].title + '</td>';
                //万年历
                dataHtml += '<td>' + currData[i].date + '</td>';

                //图片
                dataHtml += '<td><img src=" ' + currData[i].thumbnail_pic_s + '"></td>';
                dataHtml += '<td><img src=" ' + currData[i].thumbnail_pic_s02 + '"></td>';
                dataHtml += '<td><img src=" ' + currData[i].thumbnail_pic_s03 + '"></td>';
                dataHtml += '<td><a href="'+currData[i].url +'"> 原文出处 </a></td>';
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