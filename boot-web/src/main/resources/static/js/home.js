/*全局js入口*/
$(function (){
    init();
});

/*初始化函数*/
function init() {
    //计算框架宽高
    frameWH();
    // 递归获取导航栏无线层级
    let getChild=function (item,ulHtml) {
        ulHtml += '<dl class="layui-nav-child">';
        $.each(item,function (index,child) {
            if(child.children !=null&& child.children.length>0){
                ulHtml +='<a>'+child.title+'</a>';
                ulHtml +=getchild(child.children,"");
            }else {
                ulHtml += '<dd><a href="javascript:;" data-url="'+child.url+'" data-title="'+child.title+'" data-id="'+child.id+'" class="menuNvaBar">';
                ulHtml += '<cite>'+child.title+'</cite></a></dd>';
            }

        });
        ulHtml += "</dl>"
        return ulHtml;
    };
    /*1.填充层级目录数据*/
    layerFill();
    /*2.绑定导航点击事件*/
    bindingMenuClickEvent();
    setTime();
}


function layerFill(){
    /*模拟后台返回数据*/
    let res = "{\"code\":0,\"msg\":\"操作成功\",\"data\":{\"userInfo\":{\"id\":\"fcf34b56-a7a2-4719-9236-867495e74c31\",\"username\":\"admin\",\"phone\":\"13888888888\",\"nickName\":\"小霍\",\"realName\":\"迎学教育\",\"deptId\":\"72a4f388-50f8-4019-8c67-530cd7c74e7a\",\"deptName\":\"迎学教育总公司\"},\"menus\":[{\"id\":\"d6214dcb-8b6d-494b-88fa-f519fc08ff8f\",\"title\":\"组织管理\",\"perms\":\"\",\"url\":\"xx.html\",\"method\":\"\",\"pid\":\"0\",\"pidName\":null,\"type\":1,\"code\":null,\"orderNum\":100,\"spread\":true,\"checked\":false,\"children\":[{\"id\":\"78f8e29a-cccd-49e5-ada7-5af40dd95312\",\"title\":\"用户管理\",\"perms\":\"\",\"url\":\"/index/users\",\"method\":\"GET\",\"pid\":\"d6214dcb-8b6d-494b-88fa-f519fc08ff8f\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":100,\"spread\":true,\"checked\":false,\"children\":[]},{\"id\":\"c038dc93-f30d-4802-a090-be352eab341a\",\"title\":\"部门管理\",\"perms\":\"\",\"url\":\"/index/depts\",\"method\":\"GET\",\"pid\":\"d6214dcb-8b6d-494b-88fa-f519fc08ff8f\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":100,\"spread\":true,\"checked\":false,\"children\":[]},{\"id\":\"e0b16b95-09de-4d60-a283-1eebd424ed47\",\"title\":\"角色管理\",\"perms\":\"\",\"url\":\"/index/roles\",\"method\":\"GET\",\"pid\":\"d6214dcb-8b6d-494b-88fa-f519fc08ff8f\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":99,\"spread\":true,\"checked\":false,\"children\":[]},{\"id\":\"3dac936c-c4e1-4560-ac93-905502f61ae0\",\"title\":\"菜单权限管理\",\"perms\":\"\",\"url\":\"/index/menus\",\"method\":\"GET\",\"pid\":\"d6214dcb-8b6d-494b-88fa-f519fc08ff8f\",\"pidName\":null,\"type\":2,\"code\":null,\"orderNum\":98,\"spread\":true,\"checked\":false,\"children\":[]}]},{\"id\":\"e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf\",\"title\":\"系统管理\",\"perms\":\"\",\"url\":\"xx.html\",\"method\":\"\",\"pid\":\"0\",\"pidName\":null,\"type\":1,\"code\":\"\",\"orderNum\":98,\"spread\":true,\"checked\":false,\"children\":[{\"id\":\"26764d88-1d90-402d-b355-a75deef116f2\",\"title\":\"接口管理\",\"perms\":\"\",\"url\":\"/swagger-ui.html\",\"method\":\"GET\",\"pid\":\"e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":100,\"spread\":true,\"checked\":false,\"children\":[]},{\"id\":\"37101ed5-e840-4082-ae33-682ca6e41ad8\",\"title\":\"日志管理\",\"perms\":\"\",\"url\":\"/index/logs\",\"method\":\"GET\",\"pid\":\"e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":100,\"spread\":true,\"checked\":false,\"children\":[]},{\"id\":\"0d99b687-3f46-4632-9d56-8dd5e476dae7\",\"title\":\"SQL 监控\",\"perms\":\"\",\"url\":\"/druid/sql.html\",\"method\":\"GET\",\"pid\":\"e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf\",\"pidName\":null,\"type\":2,\"code\":\"\",\"orderNum\":98,\"spread\":true,\"checked\":false,\"children\":[]}]}]}}";
    res =  JSON.parse(res);
    let data=res.data.menus;
    if(data!= "" && data.length>0){
        let ulHtml = '<ul class="layui-nav layui-nav-tree layui-left-nav">';
        if(data!= null&&data.length > 0){

            $.each(data,function(index,item){
                if(index == 0){
                    ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
                }else{
                    ulHtml += '<li class="layui-nav-item">';
                }
                ulHtml += '<a href="javascript:;">';
                ulHtml += '<i class="layui-icon layui-icon-spread-left"></i> ';
                ulHtml += '<cite>'+item.title+'</cite>';
                ulHtml += '</a>'
                if(item.children != null && item.children.length > 0){
                    ulHtml += '<dl class="layui-nav-child">';
                    $.each(item.children,function(index,child){
                        if(child.children !=null&& child.children.length>0){
                            ulHtml +='<a>'+child.title+'</a>';
                            ulHtml +=getChild(child.children,"");
                        }else {
                            ulHtml += '<dd><a href="javascript:;" data-url="'+child.url+'" data-title="'+child.title+'" data-id="'+child.id+'" class="menuNvaBar">';
                            ulHtml += '<i class="layui-icon layui-icon-search"></i> ';
                            ulHtml += '<cite>'+child.title+'</cite></a></dd>';
                        }

                    });
                    ulHtml += "</dl>"
                }
                ulHtml += '</li>'
            });
        }
        ulHtml += '</ul>';
        $(".navBar").html(ulHtml);
        element.init();  //初始化页面元素
    }else{
        $("#navBarId").empty();
    }
}

function bindingMenuClickEvent(){
    $(document).on('click','.menuNvaBar',function () {
        let dataid = $(this);
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
            let isData = false;
            $.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            })
            if (isData == false) {
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            }
        }
        active.tabChange(dataid.attr("data-id"));
    });
}

// //触发事件
let active={
    tabAdd : function (url,id,title) {
        if(url!=""&&url!=null&&url!=undefined){
            element.tabAdd('tab',{
                title: title
                , content: '<iframe data-frameid="' + id + '" frameborder="0" name="content" width="100%" src="' + url + '"></iframe>'
                , id: id
            })
        }
        frameWH();//计算框架高度
    },
    tabChange: function (id) {
        //切换到指定Tab项
        element.tabChange('tab', id); //切换到：用户管理
        $("iframe[data-frameid='" + id + "']").attr("src", $("iframe[data-frameid='" + id + "']").attr("src"))//切换后刷新框架
    },
    tabDelete: function (id) {
        element.tabDelete("tab", id);//删除
    }
};
function frameWH() {
    let h = $(window).height() - 41 - 10 - 35 - 10 - 44 - 10;
    $("iframe").css("height", h + "px");
};

//推出登录
$("#logout").click(function () {
    logoutDialog();
});
// //删除前确认对话框
let logoutDialog=function () {
    layer.open({
        content: '确定要退出登录么？',
        yes: function(index, layero){
            layer.close(index); //如果设定了yes回调，需进行手工关闭
            CoreUtil.sendAjax("/sys/user/logout",null,function (res) {
                top.window.location.href="/index/login";
            },"GET",true);
        }
    });
}
function setTime(){
    let timer = setInterval(function(){
        $("#time").html(getNowTime());
    },1000)
}

function  getNowTime(){
    let dt=new Date();
    let arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
    let strWeek=arr_week[dt.getDay()];
    let strHour=dt.getHours();  //时
    let strMinutes=dt.getMinutes(); //分
    let strSeconds=dt.getSeconds(); //秒
    if (strMinutes<10) strMinutes="0"+strMinutes;
    if (strSeconds<10) strSeconds="0"+strSeconds;
    let strYear=dt.getFullYear();   //年
    let strMonth=(dt.getMonth()+1); //月
    let strDay=dt.getDate();    //日
    return strYear+"-"+strMonth+"-"+strDay+" "+strHour+":"+strMinutes+":"+strSeconds;
}