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

	$.ajax({
		url: "/file/fileList",
		dataType: "json",

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

				
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		}
	});
};