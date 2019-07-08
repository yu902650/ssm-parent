queryData();

/**
 *  获取数据
 */
function queryData() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../color_ticket/getTypes",
        // headers: token,
        // data:data,
        success: function (resp) {
            //默认成功执行方法
            if (resp.code === 200) {
                var data = {
                    count: resp.result.count,
                    item: JSON.parse(resp.result.colorTicketKey)
                };
                showDatas(data);
            }
        },
        error: function () {
            //默认异常执行方法
            console.log('失败！')
        }
    });


}

/**
 * 渲染数据
 */
function showDatas(queryObj) {
    //渲染数据
    function renderData(queryObj) {
        var dataHtml = '';
        var count = queryObj.count;
        var currData = queryObj.item;
        console.log(queryObj);
        if (count != 0) {
            for (var i = 0; i < 7; i++) {
                dataHtml += '<tr>';

                dataHtml += '<td>' + (i + 1) + '</td>';
                //缩写
                dataHtml += '<td class="lottery_id">' + currData[i].lottery_id + '</td>';
                //名称
                dataHtml += '<td>' + currData[i].lottery_name + '</td>';
                //彩票类型Id
                dataHtml += '<td>' + currData[i].lottery_type_id + '</td>';
                dataHtml += currData[i].lottery_type_id === 1 ? "福利彩票" : "体育彩票";
                //开奖时间
                dataHtml += '<td>' + currData[i].remarks + '</td>';
                dataHtml += '<td><button class="queryDetail">查询</button></td>';
                dataHtml += '</tr>';
            }
        } else {
            dataHtml = '<tr><td colspan="11">暂无数据</td></tr>';
        }
        return dataHtml;


    }

    $(".content_body").html(renderData(queryObj));


    let queryName = document.getElementsByClassName("queryDetail");
    //每个queryDetail添加点击方法
    for (let i = 0; i < queryName.length; i++) {
        queryName[i].onclick = function () {
            //获取父元素tr
            let children = queryName[i].parentNode.parentNode.childNodes;
            let lottery_id = undefined;
            //遍历tr子元素得到有lottery_id类名的列
            for (let j = 0; j < children.length; j++) {
                if (children[j].className.indexOf("lottery_id") !== -1) {
                    lottery_id = children[j];
                }
            }
            //获取lottery_id的内容
            let data = {
                lottery_id: lottery_id.innerText
            }
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "../color_ticket/queryResult",
                data: data,
                success: function (resp) {
                    console.log(resp);
                    openPopup({
                        height: 900,
                        width: 1200,
                        title: "彩票情况",
                        content: "" + resp.result.colorTicketKey
                    });
                },
                error: function () {
                    //默认异常执行方法
                    console.log('失败！')
                }
            });
        }
    }
}
