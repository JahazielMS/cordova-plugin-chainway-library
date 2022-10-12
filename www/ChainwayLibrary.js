var exec = require('cordova/exec');

// var chainway = {
//   IResult: function() {
//     return execute(null, null, 'MainScan', 'IResult', [])
//   }
// }

exports.coolMethod = function(arg0, success, error) {
  exec(success, error, 'MainScan', 'coolMethod', [arg0])
}

exports.IResult = function(arg0, success, error) {
  exec(success, error, 'MainScan', 'IResult', [arg0])
}