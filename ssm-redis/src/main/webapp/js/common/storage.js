var storage = {
    setSign: function (data) {
        localStorage.setItem("sign", data);
    },
    getSign: function () {
        return localStorage.getItem("sign");
    }
}