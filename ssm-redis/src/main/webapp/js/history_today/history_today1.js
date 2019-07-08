layui.config({
    base: ""
}).use(['table', 'form', 'layer', 'laydate'], function () {
    var table = layui.table,
        form = layui.form,
        laydate = layui.laydate,
        $ = layui.$,
        url = '../history/today';
    console.log("进去")
    //渲染
    var tableIns = table.render({
        elem: '#newList'
        , height: '900'
        , url: url //数据接口
        , page: {
            layout: ['prev', 'page', 'next', 'count', 'skip']
            , limit: 50
            , prev: '上一页'
            , next: '下一页'
            , first: '首页'
            , last: '末页'
        } //开启分页

        , parseData: function (resp) { //res 即为原始返回的数据
            return {
                "code": resp.code,
                "msg": resp.message, //解析提示文本
                // "count": resp.result.rows.length, //解析数据长度
                "data": resp.result.rows//解析数据列表
            };
        }
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusCode: 200 //规定成功的状态码，默认：0
        }
        , cols: [[ //表头
            {
                templet: '<div>{{d.LAY_INDEX}}</div>',
                title: '序号',
                width: '5%',
                sort: true,
                fixed: 'left',
                align: 'center'
            }
            , {field: 'title', title: '标题', width: '10%', align: 'center'}
            , {
                templet: 'lunar', title: '发生时间', width: '10%', align: 'center'
                // , templet: function (d) {
                //     if (d.createTime == null) {
                //         return '';
                //     } else {
                //         return dateUtil.formatString(d.createTime);
                //     }
                // }
            }
            , {field: 'pic', title: '图片', width: '35%', align: 'center'}
            , {field: 'des', title: '简介', width: '40%', align: 'center'}
        ]]
    });

    $('#searchBatch').click(function () {
        var medicineName = $('#medicineName').val();
        if (medicineName === '' || medicineName.length === 0) {
            medicineName = null;
        }
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                code: medicineName
            }
            , page: {
                layout: ['prev', 'page', 'next', 'count', 'skip']
                , curr: 1 //重新从第 1 页开始
            }
        });
    });

});