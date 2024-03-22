<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class= 'uploadDiv'>
		<input type = 'file' name = 'uploadFile' multiple>
	</div>
	<button id = 'uploadBtn'>Upload</button>
	
	<script
		src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous">
		
	</script>
	<script>
		$(document).ready(function(){
			
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 52428800; //50MB
			
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("사이즈 초과");
					return false;
				}
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			//초기상태를 복제
			var cloneObj = $(".uploadDiv").clone();
			
			$("#uploadBtn").on("click",function(e){
				var formData = new FormData();
			});
			
			$("#uploadBtn").on("click",function(e){
				var formData = new FormData();
				
				var inputFile = $("input[name = 'uploadFile']");
				
				var files = inputFile[0].files;
				
				console.log(files);
				
				for(var i =0; i<files.length;i++){
					if(!checkExtension(files[i].name,files[i].size)){
						return false;
					}
					formData.append("uploadFile",files[i]);
				}
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					dataType:'json',
					success : function(result){
						console.log(result);
						
						//초기화된 부분을 덮어쓰기 -> 초기 상태로 변경
						$(".uploadDiv").html(cloneObj.html());
					}
				}); //$.ajax
			});
		});
	</script>
	
</body>
</html>