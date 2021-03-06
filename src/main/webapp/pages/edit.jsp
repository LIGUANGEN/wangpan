<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/3/20
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/jQuery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/javascript/xadmin.js"></script>



    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>登录名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="username" name="username"
                           autocomplete="off" class="layui-input"  value="${user.username}" disabled="true">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>无法修改
                </div>
            </div>
            <div class="layui-form-item">
                <label for="phone" class="layui-form-label">
                    <span class="x-red">*</span>手机
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" required="" lay-verify="phone"
                           autocomplete="off" class="layui-input" value="${user.tel}" >
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>
                </div>
            </div>
            <div class="layui-form-item">
            <label for="age" class="layui-form-label">
                <span class="x-red">*</span>年龄
            </label>
            <div class="layui-input-inline">
                <input type="text" id="age" name="age" required="" lay-verify="age"
                       autocomplete="off" class="layui-input" value="${user.age}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>性别</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="like1"  lay-skin="primary" value="男" title="男" >
                    <input type="checkbox" name="like1"   lay-skin="primary" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>角色</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="like2" lay-skin="primary" value="ROLE_ADMIN" title="超级管理员" >
                    <input type="checkbox" name="like2" lay-skin="primary" value="ROLE_USER" title="用户">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">
                    <span class="x-red">*</span>密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="L_pass" name="pass" required="" lay-verify="pass"
                           autocomplete="off" class="layui-input" value="${user.password}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    6到16个字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                           autocomplete="off" class="layui-input" value="${user.password}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                </label>
                <button  class="layui-btn" lay-filter="sub" lay-submit="">
                    修改
                </button>
            </div>
        </form>
    </div>
</div>
<script>
    // $(document).ready(function(){
    var sex='${user.sex}'
    var authority='${user.authority}'
    var i=0;
    $ = layui.jquery;



    var  unit= $('input[name=like1]');
    var  unit2= $('input[name=like2]');
    console.log(sex)
        console.log(authority)
    if(sex=='男'){
        unit[i].checked=true;
    }else{
        unit[i+1].checked=true;
    }
    if(authority=='ROLE_ADMIN'){
        unit2[i].checked=true;
    }else{
        unit2[i+1].checked=true;
    }
        // });
</script>
<script>layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function(value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function(value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });


        form.on('submit(sub)',function (){
                if($('input[name=like1]:checked').length==2) {
                    $(this).prop("checked", false);
                    form.render('checkbox');
                    layer.msg("性别只能选一个", {
                        time: 3000,
                        icon: 10
                    });
                    return false;
                }
                  if($('input[name=like1]:checked').length<1){
                      layer.msg("必须选一个性别", {
                          time: 3000,
                          icon: 10
                      });
                      return false;
                    }
            if($('input[name=like2]:checked').length==2) {
                $(this).prop("checked", false);
                form.render('checkbox');
                layer.msg("权限只能选一个", {
                    time: 3000,
                    icon: 10
                });
                return false;
            }
            if($('input[name=like2]:checked').length<1){
                layer.msg("超级管理员和用户必须选一个", {
                    time: 3000,
                    icon: 10
                });
                return false;
            }



                var data = {
                    username:$('#username').val(),
                    password:$('#L_pass').val(),
                    tel:$('#phone').val(),
                    age:$('#age').val(),
                    sex:$('input[name=like1]:checked').val(),
                    authority:$('input[name=like2]:checked').val(),
                }
                console.log(data);
                $.ajax({
                    url:'/admin/updateUser',
                    type:'post',
                    // dataType:'json',
                    contentType: 'application/json',
                    data:JSON.stringify(data),
                    timeout:2000,
                    // beforeSend:function (xhr) {
                    //     xhr.setRequestHeader(header,token);
                    // },
                    success:function(data){
                        console.log(data);
                        if(data == 'success'){
                            layer.msg("修改成功");
                            xadmin.close();
                            xadmin.father_reload();
                        }else{
                            layer.msg("修改失败")
                        }
                    },
                    error:function (error) {
                        console.log(error);
                        layer.msg("修改失败")
                    }
                })

            return false;
        })
        //监听提交
        // form.on('submit(add)',
        //     function(data) {
        //         console.log(data);
        //
        //         //发异步，把数据提交给php
        //         layer.alert("增加成功", {
        //                 icon: 6
        //             },
        //             function() {
        //                 //关闭当前frame
        //                 xadmin.close();
        //
        //                 // 可以对父窗口进行刷新
        //                 xadmin.father_reload();
        //             });
        //         return false;
        //     });

    });</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
