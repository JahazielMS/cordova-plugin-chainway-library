var exec = require('cordova/exec');

// var chainway = {
//   IResult: function() {
//     return execute(null, null, 'MainScan', 'IResult', [])
//   }
// }

// exports.coolMethod = function(arg0, success, error) {
//   exec(success, error, 'MainScan', 'coolMethod', [arg0])
// }

exports.IResult = function(args, success, error) {
  return exec(success, error, 'MainScan', 'IResult', [])
}

// var execute = require('cordova/exec');
// var chainwayScan = {
//   coolMethod: function() {
//     return execute(null, null, 'MainActivity', 'coolMethod', [])
//   },
//   start: function() {
//     return execute(null, null, 'MainActivity', 'startScan', [])
//   },
//   stop: function() {
//     return execute(null, null, 'MainActivity', 'stopScan', [])
//   },
//   open: function() {
//     return execute(null, null, 'MainActivity', 'open', [])
//   },
//   close: function() {
//     return execute(null, null, 'MainActivity', 'close', [])
//   },
//   IResult: function() {
//     return execute(null, null, 'MainActivity', 'IResult', [])
//   }
// }

// module.exports = chainwayScan