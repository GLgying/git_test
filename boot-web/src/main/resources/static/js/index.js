/*全局函数入口*/
$(function(){
    init();
});

/*初始化函数*/
function init(){
    /*所有的绑定事件*/
    binding_nav();
    binding_center();
    // binding_all();
}
/*导航栏绑定*/
function binding_nav(){
    $("#nav li").on('click',function(){
        let url = $(this).attr("src");
        $("iframe").attr("src",url);
    });
}
/*个人中心绑定*/
function binding_center(){
    $("a").on('click',function(){
        let url = $(this).attr("src");
        $("iframe").attr("src",url);
    });
}

/*所有的跳转请求 都在指定位置显示*/
// function binding_all(){/*绑定所有标签 不是all*/
//     $("all").on('click',function(){
//         let url = $(this).attr("src");
//         $("iframe").attr("src",url);
//     });
// }