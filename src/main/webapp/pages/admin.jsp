

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/jQuery/jquery-3.2.1.js"></script>
    <script src="/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/javascript/xadmin.js"></script>
    <script type="text/javascript" src="/javascript/admin.js"></script>

    <!--[if lt IE 9]-->
<!--    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>-->
<!--    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="/login/logout"  title="注销">
        <i class="layui-icon " style="line-height:30px">注销</i></a>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">

                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('添加用户','/pages/add.jsp',600,400)"><i class="layui-icon"></i>添加</button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>

                            <th>用户名</th>
                            <th>密码</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th>角色</th>
                            <th>操作</th>
                        </thead>
                        <tbody id="userList">


                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body ">
                    <div class="page">
                        <div>
                            <a class="prev" href="">&lt;&lt;</a>
                            <a class="num" href="">1</a>
                            <span class="current">2</span>
                            <a class="num" href="">3</a>
                            <a class="num" href="">489</a>
                            <a class="next" href="">&gt;&gt;</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>


    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(){
            //发异步删除数据
            // var data = {
            //       username:id,
            // }
            // console.log(data);
            $.ajax({
                url:'/admin/deleteUser',
                type:'post',
                // dataType:'json',
                // contentType: 'application/json',
                data:{'username':id},
                timeout:2000,
                // beforeSend:function (xhr) {
                //     xhr.setRequestHeader(header,token);
                // },
                success:function(data){
                    console.log(data);
                    if(data == 'success'){
                        layer.msg("删除成功");
                        xadmin.father_reload();
                    }else{
                        layer.msg("删除失败")
                        return false;
                    }
                },
                error:function (error) {
                    console.log(error);
                    layer.msg("删除1失败")
                }
            });
            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }




</script>

<script id="userListTrTemp" type="text/html">
    <tr class="row">
        <td class="">#1</td>
        <td class="">#2</td>
        <td class="">#3</td>
        <td class="">#4</td>
        <td class="">#5</td>
        <td class="">#6</td>
        <td class="">
<%--            <a id="edit" title="编辑"  onclick="xadmin.open('编辑','/pages/edit.jsp')" href="javascript:;">--%>
<%--           <a class="edit" title="编辑"  >--%>
<%--                <i class="layui-icon">&#xe642;</i>--%>
<%--            </a>--%>
<%--            <a  class= "delete" title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">--%>
<%--                <i class="layui-icon">&#xe640;</i>--%>
<%--            </a>--%>
        </td>
    </tr>
</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</html>
