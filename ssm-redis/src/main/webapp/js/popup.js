function openPopup(setting) {
    let popup = document.createElement("div");
    popup.setAttribute("style", "height:" + setting.height + "px;width:" + setting.width + "px;");
    popup.className = "popup-box";
    popup.innerHTML =
        '<div class="popup-title">' + setting.title + '</div>' +
        '<div class="popup-content" style="height: ' + (setting.height - 43) + 'px">' + setting.content + '</div>' +
        '<div class="popup-close" onclick="closePopuo()">关闭</div>';

    document.body.appendChild(popup);
}

function closePopuo() {
    let x = document.getElementsByClassName("popup-box")[0];
    x.parentNode.removeChild(x);
}