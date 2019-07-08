function queryString(name) {
    var paramStr = location.search;
    if (paramStr.length == 0)return null;

    if (paramStr.charAt(0) != '?')return null;
    paramStr = unescape(paramStr);
    paramStr = paramStr.substring(1);
    if (paramStr.length == 0)return null;
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