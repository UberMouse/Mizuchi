global.jQuery = require('jquery');
global.document = window.document;
global.navigator = window.navigator;
require('bootstrap');
require('babel/register');
var main = require('index');
main.init();
