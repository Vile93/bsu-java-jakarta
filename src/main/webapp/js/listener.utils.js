const on = function (action, callback) {
    this.addEventListener(action,callback)
}

HTMLElement.prototype.on = on