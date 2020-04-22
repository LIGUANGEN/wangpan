

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>main page</title>

<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/css/index.css">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="navbar-header">
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target=".js-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Share Point</a>
		</div>

		<div class="collapse navbar-collapse js-navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#">文件下载</a></li>
				<li><a href="/pages/file_upload.jsp">文件共享</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">
						${sessionScope.username}
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/user/edit?username=${sessionScope.username}">修改个人信息</a></li>

					</ul>
				<li><a href="/login/logout">注销</a></li>
			</ul>
		</div>
		</nav>
		<div id="left" class="col-sm-12">
			<div class="panel panel-warning download">
				<div class="panel-heading">
					<div class="panel-title">文件下载</div>
					<div class="pull-right">
						<button class="btn btn-default btn-xs btn-filter">
							<span class="glyphicon glyphicon-filter"></span>刷新
						</button>
					</div>
				</div>
				<div class="panel-body">
					<table
						class="table table-condensed table-bordered table-striped row text-center">
						<thead class="col-md-12">
							<tr class="filters row">
								<th class="col-md-1"><input style="text-align: center"
									type="text" class="form-control" placeholder="#" disabled></th>
								<th class="col-md-4"><input style="text-align: center"
									type="text" class="form-control" placeholder="文件名" disabled>
								</th>
								<th class="col-md-2"><input style="text-align: center"
									type="text" class="form-control" placeholder="上传作者" disabled></th>
								<th class="col-md-1"><input style="text-align: center"
									type="text" class="form-control" placeholder="类型" disabled></th>
								<th class="col-md-2"><input style="text-align: center"
									type="text" class="form-control" placeholder="上传时间" disabled></th>
								<th class="col-md-2"><input style="text-align: center"
									type="text" class="form-control" placeholder="操作" disabled></th>
							</tr>
						</thead>

						<tbody id="fileList" class="col-md-12">
							<!-- 列表 -->
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="/jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
	<script id="fileListTrTemp" type="text/html"> 
   		<tr class="row">
			<td class="col-md-1 text-center">#1</td>
			<td class="col-md-4 text-center">#2</td>
			<td class="col-md-2 text-center">#3</td>
			<td class="col-md-1">#4</td>
			<td class="col-md-2">#5</td>
			<td class="col-md-2">
				<a type="button" class="downloadFile btn-sm btn-success">下载</a>
				<a type="button" class="deleteFile btn-sm btn-danger">删除</a>
				<a type="button" class="shareFile btn-sm btn-success">分享</a>
			</td>
		</tr>
	</script>
	<script>
		if('${result}'=='error1'){
			alert('已经删除！')
		}else if('${result}'=='success'){
			alert("删除成功！");
			console.log('${result}');
		}else if('${result}'=='error'){
			alert("删除失败！");
			console.log('${result}');
		}



	</script>
	<script>
		$(document).ready(function(){
			getFileList();
			$(".dropdown").hover(
					function() {
						$('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
						$(this).toggleClass('open');
					},
					function() {
						$('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
						$(this).toggleClass('open');
					}
			);
		});

		function getFileList() {
			var username='${sessionScope.username}';
			console.log(username);
			$.ajax({
				url: "/file/fileList",
				// dataType: "json",
				data:{uploadAuthor:username},
				success: function(data) {
					var domList = $('#fileList');
					$("#fileList").html("");
					//JSON对象转JavaScript对象
					//var json = JSON.parse(data)
					for(var i = 0; i < data.length; i++) {
						//获取节点模板，定义在index.jsp
						var tr = $("#fileListTrTemp").html();
						domList.append(tr);
						var index = 0;
						//通过父节点修改DOM
						tr = $("#fileList").children("tr")[i];
						for(var field in data[i]) {
							$(tr).children("td")[index].innerText = data[i][field];
							index++;
						}
						//设置链接
						$(tr).find(".downloadFile").attr("href", "/file/fileDownload?fileName=" + data[i].fileName + "." + data[i].fileType);
						$(tr).find(".deleteFile").attr("href", "/file/deleteFile?fileId=" + data[i].fileId+"&username="+data[i].uploadAuthor);
						$(tr).find(".shareFile").attr("href", "/file/share?fileId=" + data[i].fileId);


					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
			});
		};
	</script>
<%--	<script type="text/javascript" src="/javascript/index.js"--%>
<%--		charset="UTF-8"></script>--%>
</body>
</html>
