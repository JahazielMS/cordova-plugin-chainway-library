var exec = require("cordova/exec");

var ChainwayPlugin = {
  IResult: function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, "ChainwayPlugin", "IResult", []);
  },
};

module.exports = ChainwayPlugin;
