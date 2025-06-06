module.exports = {
    lintOnSave: false,
    chainWebpack: config =>{
        config.plugin('html')
            .tap(args => {
                args[0].title = "二手交易平台";
                args[0].keywords = "二手交易平台";
                args[0].description = "二手交易平台";
                return args;
            })
    },
};
