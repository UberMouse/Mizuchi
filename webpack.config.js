/**
 * Created by Taylor on 4/09/14.
 */
var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: [
        'webpack-dev-server/client?http://localhost:3000',
        'webpack/hot/dev-server',
        './app/mizuchi/assets/javascripts/main.coffee'
    ],
    devtool: "eval",
    output: {
        path: path.join(__dirname, 'public/javascripts/'),
        filename: 'bundle.js',
        publicPath: 'public/javascripts/'
    },
    resolve: {
        // Allow to omit extensions when requiring these files
        extensions: ['', '.js', '.jsx', '.coffee', '.coffee.jsx']
    },
    module: {
        loaders: [
            { test: /\.jsx/, loaders: ['react-hot'] },
            { test: /\.coffee$/, loader: 'coffee'}
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
};