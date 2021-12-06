var exec = require('cordova/exec');

// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'MainActivity', 'coolMethod', [arg0]);
// }

var execute = require('cordova/exec');

var chainwayScan = {
  coolMethod: function() {
    return execute(null, null, 'MainActivity', 'coolMethod', [])
  },
  start: function() {
    return execute(null, null, 'MainActivity', 'startScan', [])
  },
  stop: function() {
    return execute(null, null, 'MainActivity', 'stopScan', [])
  },
  open: function() {
    return execute(null, null, 'MainActivity', 'open', [])
  },
  close: function() {
    return execute(null, null, 'ChainwayScannerPlugin', 'close', [])
  },
  IResult: function() {
    return execute(null, null, 'MainActivity', 'IResult', [])
  }
}

module.exports = chainwayScan