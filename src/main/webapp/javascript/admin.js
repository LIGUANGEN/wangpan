$(document).ready(function(){
    getUserList();

});

function getUserList() {
    $.ajax({
        url: "/admin/userList",
        dataType: "json",
        success: function(data) {
            console.log(data);
            var domList = $('#userList');
            $("#userList").html("");
            //JSON对象转JavaScript对象
            //var json = JSON.parse(data)
            for(var i = 0; i < data.length; i++) {
                //获取节点模板，定义在index.jsp
                var tr = $("#userListTrTemp").html();
                domList.append(tr);
                var index = 0;
                //通过父节点修改DOM
                tr = $("#userList").children("tr")[i];
                for(var field in data[i]) {
                    $(tr).children("td")[index].innerText = data[i][field];
                    index++;
                }
                // console.log(index)
                var url = 'xadmin.open(\'编辑\',\'/admin/edit?'+'username='+data[i].username+'\')';
                var edit='<a id="edit" title="编辑"  onclick="'+url+'" > <i class="layui-icon">&#xe642;</i></a>'
                // alert(edit);
                var del=' <a  class= "delete" title="删除" onclick="member_del(this,\''+data[i].username +'\')" >\n' + '<i class="layui-icon">&#xe640;</i>\n' +'</a>'
               // alert(del);
                console.log( $(tr).children("td")[index]+'ligen')
                console.log($(tr))
                $(tr).children("td")[index].innerHTML=edit+del
                // console.log($(tr))
                // console.log($(tr).children("td")[index])
                // //设置链接
                // $(tr).find(".downloadFile").attr("href"), "/file/fileDownload?fileName=" + data[i].fileName + "." + data[i].fileType);
                // $(tr).find(".deleteFile").attr("href", "/servlet/FileDelete?fileId=" + data[i].fileId);
                // $(tr).find(".edit").attr("onclick", "xadmin.open('编辑','/admin/edit?username='"+data[i].username+"')");
                // var url = 'xadmin.open(\'编辑\',\'/admin/edit?'+'username='+data[i].username+'\')';
                // alert(url);
                // alert(data[i].username);
                // alert(data.length)
               // document.getElementsByClassName("edit").setAttribute("onclick",url);
               //  $(tr).find("edit").attr("onclick",url);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);
        }
    });
};
layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;




        form.on('submit(sreach)',function (){



            var data = {
                  username:$('input[name=username]').val()
            }
            console.log(data);
            $.ajax({
                url: "/admin/findUser",
                // dataType: "json",
                data:data,
                success: function(data) {
                    var domList = $('#userList');
                    $("#userList").html("");
                    //JSON对象转JavaScript对象
                    //var json = JSON.parse(data)
                    for(var i = 0; i < data.length; i++) {
                        //获取节点模板，定义在index.jsp
                        var tr = $("#userListTrTemp").html();
                        domList.append(tr);
                        var index = 0;
                        //通过父节点修改DOM
                        tr = $("#userList").children("tr")[i];
                        for(var field in data[i]) {
                            $(tr).children("td")[index].innerText = data[i][field];
                            index++;
                        }
                        // console.log(index)
                        var url = 'xadmin.open(\'编辑\',\'/admin/edit?'+'username='+data[i].username+'\')';
                        var edit='<a id="edit" title="编辑"  onclick="'+url+'" > <i class="layui-icon">&#xe642;</i></a>'
                        // alert(edit);
                        var del=' <a  class= "delete" title="删除" onclick="member_del(this,\''+data[i].username +'\')" >\n' + '<i class="layui-icon">&#xe640;</i>\n' +'</a>'
                        // alert(del);
                        console.log( $(tr).children("td")[index]+'ligen')
                        console.log($(tr))
                        $(tr).children("td")[index].innerHTML=edit+del
                        // console.log($(tr))
                        // console.log($(tr).children("td")[index])
                        // //设置链接
                        // $(tr).find(".downloadFile").attr("href"), "/file/fileDownload?fileName=" + data[i].fileName + "." + data[i].fileType);
                        // $(tr).find(".deleteFile").attr("href", "/servlet/FileDelete?fileId=" + data[i].fileId);
                        // $(tr).find(".edit").attr("onclick", "xadmin.open('编辑','/admin/edit?username='"+data[i].username+"')");
                        // var url = 'xadmin.open(\'编辑\',\'/admin/edit?'+'username='+data[i].username+'\')';
                        // alert(url);
                        // alert(data[i].username);
                        // alert(data.length)
                        // document.getElementsByClassName("edit").setAttribute("onclick",url);
                        //  $(tr).find("edit").attr("onclick",url);
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    // alert(XMLHttpRequest.status);
                    // alert(XMLHttpRequest.readyState);
                    // alert(textStatus);
                }
            });
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

    });