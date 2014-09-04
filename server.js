/**
 * Created by Taylor on 4/09/14.
 */
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack.config');
config.output.publicPath = 'http://localhost:3000/' + config.output.publicPath;

new WebpackDevServer(webpack(config), {
    publicPath: config.output.publicPath,
    hot: true
}).listen(3000, 'localhost', function (err, result) {
    if (err) {
        console.log(err);
    }

    console.log('Listening at localhost:3000');
});