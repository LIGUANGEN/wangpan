<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/3/27
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jQuery/jquery-3.2.1.js"></script>

</head>
<body>
<div>
    <input type="text"  name="share" id="share"/>

</div>
</body>
<script>
    var fileId='${fileId}';
    $.ajax({
        url: "/file/createUrl",
        data:{fileId:fileId},
        success: function (data) {
           console.log(data.url);
            console.log(data);

            $("#share").val(data.url);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });
</script>
</html>
