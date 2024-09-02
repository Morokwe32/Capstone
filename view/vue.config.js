const path = require('path');

module.exports = {
    devServer: {
        port: 3315,
        //proxy: 8080// or any other port number you prefer
    },
    configureWebpack: {
        resolve: {
            alias: {
                '@': path.resolve(__dirname, 'src'),
            }
        }
    }
};
