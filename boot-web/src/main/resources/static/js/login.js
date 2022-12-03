$(function () {
    form.on('submit(submit)',function (data) {
        let url =  $("#loginForm").attr("action");

        CoreUtil.sendAjax(url,JSON.stringify(data.field),function (res, status, xhr) {
            /*后面配置后 在使用*/
            // CoreUtil.setData("access_token",res.data.accessToken);
            // CoreUtil.setData("refresh_token",res.data.refreshToken);
            // window.location.href="/index/home";
            // alert(xhr.getResponseHeader("authorization"));
            // console.log(xhr.getResponseHeader("authorization"))
            CoreUtil.setData("access_token",xhr.getResponseHeader("authorization"));
            // window.location.href="/web/index/home";
            window.location.href="/web/index/home";
            // CoreUtil.toHtml("/web/index/home");


        });
        return false;
    });
    //改变下时间间隔、动画类型、高度
    carousel.render({
        elem: '#carousel'
        ,interval: 2000
        ,anim: 'fade'
        , full: true        //全屏
        ,width: '100%'
        ,arrow: 'none'
    })
})