var system = require('system');
var url = system.args[1];

var page = require('webpage').create();
page.open(url, function (status) {
  var source = page.evaluate(function () {
    return document.getElementsByTagName('html')[0].innerHTML;
  });
  console.log(source);
  phantom.exit();
});


