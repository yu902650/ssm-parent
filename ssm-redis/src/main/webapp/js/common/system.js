var http = {
    request: function (url, param, callbak) {
        var token = localStorage.getItem("sign");
        $.ajax({
            url: url,
            type: "POST",
            dataType: "text",
            headers: {
                'token': token
            },
            data: param,
            success: function (response) {
                var resp = eval('(' + response + ')');
                if (resp.code == 200 || resp.respCode == 200) {
                    callbak(resp);
                } else if (resp.code == 1001 || resp.code == 20001) {
                    storage.setSign('');
                    login.sigin(ACCOUNT, PASSWORD);
                }
            },
            error: function () {
                console.log('error');
            }
        });
    },
    reqJson: function (url, param, callbak) {
        var token = localStorage.getItem("sign");
        $.ajax({
            url: url,
            type: "POST",
            dataType: "text",
            headers: {
                'token': token
            },
            data: param,
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            success: function (response) {
                var resp = eval('(' + response + ')');
                if (resp.code == 200 || resp.respCode == 200) {
                    callbak(resp);
                } else if (resp.code == 1001 || resp.code == 20001) {
                    storage.setSign('');
                    login.sigin(ACCOUNT, PASSWORD);
                }
            },
            error: function () {
                console.log('error');
            }
        });
    },
    outRequest: function (url, param, callbak) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            data: param,
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            success: function (response) {
                callbak(response);
            },
            error: function () {
                console.log('error');
            }
        });
    }
}


var page = {
    send: function (url) {
        location.href = url;
    }
}

function isNull(exp) {
    if (exp != 'undefined' && exp != null && typeof(exp) != 'undefined' && exp != '' && isEmptyObj(exp))
        return false;
    else
        return true;
}

function isEmpty(obj) {
    if (obj != "" && obj.length != 0)
        return true;
    else
        return false;
}

//数组参数唯一
function arrayIsOnly(array, value) {
    //array 数组   value 即将传入的值
    //都跟value元素做比对，只要有一个值相等，那说明不是唯一值了
    return array.some(function (item) {
        return item == value;
    });
}

//根据传入的值删除对应索引
function delObjArrays(array, value) {
    for (let i = 0; i < array.length; i++) {
        if (array[i] === value) {
            array.splice(i, 1);
            return array;
        }
    }
    return array;
}

//传入指定字符分割字符串
function joinArrays(array, value) {
    var str = "";
    if (array.length === 1) {
        return array[0] + "";
    }
    for (let i = 0; i < array.length - 1; i++) {
        str += array[i] + value;
    }
    str = str + array[array.length - 1];
    return str;
}


var dateUtil = {
    formatDateTime: function (timeStamp) {
        var date = new Date(timeStamp);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    },
    parserDate: function (date) {
        var t = Date.parse(date);
        if (!isNaN(t)) {
            return new Date(Date.parse(date.replace(/-/g, "/")));
        } else {
            return new Date();
        }
    },
    //date
    now: function (type) {
        let date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hours = date.getHours();
        let minutes = date.getMinutes();
        let seconds = date.getSeconds();
        if (type !== undefined) {
            return year + '-' + month + '-' + day;
        }
        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    },

    diff_seconds: function (b, e) {
        let begin = isDate(b) ? b : dateUtil.parserDate(b);
        let end = isDate(e) ? e : dateUtil.parserDate(e);
        //时间差的秒数
        return (end.getTime() - begin.getTime()) / 1000;
    },


    diff_days: function (b, e) {
        let begin = isDate(b) ? b : dateUtil.parserDate(b);
        let end = isDate(e) ? e : dateUtil.parserDate(e);
        //时间差的天数
        return (end.getTime() - begin.getTime()) / 1000 / 60 / 60 / 24;
    },
    getPastTimes: function (number, thisMonth) {
        let num = number - 1;
        let pastTime = new Date((new Date().getTime()) - num * 1000 * 60 * 60 * 24);
        let year = pastTime.getFullYear();
        let month = pastTime.getMonth() + 1;
        let day = pastTime.getDate();
        if (thisMonth) {
            let date = new Date();
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            return dateUtil.parserDate(year + '-' + month + '-' + '01' + ' ' + '00:00:00');
        } else {
            return dateUtil.parserDate(year + '-' + month + '-' + day + ' ' + '00:00:00');
        }

    },
    formatString: function (timeStamp) {
        let date = new Date(timeStamp);
        let y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let minute = date.getMinutes();
        let second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    },

    parserDate: function (date) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
    }
}

//判断对象是Date
function isDate(o) {
    return Object.prototype.toString.call(o) === "[object Date]";
}

//判断空对象
function isEmptyObj(obj) {
    if (JSON.stringify(obj) != '{}')
        return true;
    else
        return false;
}

function queryString(name) {
    var paramStr = location.search;
    if (paramStr.length == 0) return null;

    if (paramStr.charAt(0) != '?') return null;
    paramStr = unescape(paramStr);
    paramStr = paramStr.substring(1);
    if (paramStr.length == 0) return null;
    var params = paramStr.split('&');
    for (var i = 0; i < params.length; i++) {
        var parts = params[i].split('=', 2);
        if (parts[0] == name) {
            if (parts.length < 2 || typeof(parts[1]) == "undefined" || parts[1] == "undefined" || parts[1] == "null")
                return "";
            return parts[1];
        }
    }
    return null
}

var login = {
    sigin: function (account, password) {

        if (account == null || account == '') {
            alert('请输入帐号')
        } else if (password == null || password == '') {
            alert('请输入密码')
        }

        var url = AUTH_URL + 'oauth2/authorizeAccount';
        var param = {};
        param.account = account;
        param.password = password;
        param.appId = '3rqYnoFyqBkDo6wr';
        param.appSecret = '6EPasA09BjDz7Z8F5naIYUcYwqDIYeFx';

        http.request(url, param, function (req) {
            if (req.code == 200) {
                storage.setSign(req.result.sign);
            } else {
                alert(req.message);
                console.log('登录失败');
            }
        });
    }
}

//处理时间转换
var SecondTime = function (v) {
    if (v > 0 && v < 60) {
        return v + '秒';
    }
    if (v >= 60 && v < 3600) {
        var m = parseInt(v / 60);
        var s = v % 60;
        var t = m + '分钟';
        if (s > 0) {
            t += s + '秒';
        }
        return t;
    }
    if (v >= 3600 && v < 86400) {
        var h = parseInt(v / 3600);
        var m = parseInt((v - 3600 * h) / 60);
        var s = parseInt((v - 360 * h) % 60);
        var t = h > 0 ? h + '小时' : '';
        if (m > 0) {
            t += m + '分钟';
        }
        if (s > 0) {
            t += s + '秒';
        }
        return t;
    }
    if (v >= 86400) {
        var d = parseInt(v / 86400);
        var h = parseInt((v - 86400 * d) / 3600);
        var m = parseInt((v - 86400 * d - 3600 * h) / 60);
        var s = parseInt((v - 360 * h) % 60);
        var t = d > 0 ? d + '天' : '';
        if (h > 0) {
            t += h + '小时';
        }
        if (m > 0) {
            t += m + '分钟';
        }
        if (s > 0) {
            t += s + '秒';
        }
        return t;
    }
}


