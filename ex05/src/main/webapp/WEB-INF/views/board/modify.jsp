<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ include file = "../includes/header.jsp" %>

<div class = 'bigPictureWrapper'><div class = 'bigPicture'></div></div> <!-- 첨부파일 css 및 기능은 style, script에서 구현 -->
<style>
	.uploadResult{
		width:100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow:row;
		justify-content: center;
		align-items : center;
	}
	.uploadResult ul li {
		list-style : none;
		padding : 10px;
		align-content : center;
		text-align : center;
	}
	
	.uploadResult ul li img{
		width: 100px;
	}
	
	.uploadResult ul li span {
    	color: white;
	}

	.bigPictureWrapper {
	    position: absolute;
	    display: none;
	    justify-content: center;
	    align-items: center;
	    top: 0%;
	    width: 100%;
	    height: 100%;
	    background-color : gray;
	    z-index: 100;
	    background : rgba(122, 122, 122, 0.5); /* 투명도 설정 */

	}
	
	.bigPicture {
	    position: relative;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}
	
	.bigPicture img {
	    width: 600px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form");
		
		$('button').on("click",function(e){
			
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation ==='list'){
				//move to list
				formObj.attr("action","/board/list").attr("method","get");
				
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}else if(operation == 'modify'){
				
				console.log("submit clicked");
				
				var str = "";
				
				$(".uploadResult ul li").each(function(i,obj){
					var jobj = $(obj);
					
					console.dir(jobj);
					
					str += "<input type='hidden' name = 'attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
					str += "<input type='hidden' name = 'attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
					str += "<input type='hidden' name = 'attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
					str += "<input type='hidden' name = 'attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
				});
				formObj.append(str).submit();
			}
			formObj.submit();
		});
	});
	
</script>
<script>
	$(document).ready(function(){
		
		(function(){
			var bno = '<c:out value = "${board.bno}"/>';
			
			
			$.getJSON("/board/getAttachList", {bno: bno}, function(arr){
				
				console.log(arr); //게시물 번호에 대한 첨부파일 정보 출력
				
				var str = "";
				
				$(arr).each(function(i, attach){
					
					if(attach.fileType){
						var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_"+attach.uuid+"_"+attach.fileName);
						
						str += "<li data-path='" + attach.uploadPath + "'";
						str += " data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'";
						str += " ><div>";
						str += "<button type='button' data-file=\'" + fileCallPath + "\' ";
						str += "data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/display?fileName=" + fileCallPath + "'>";
						str += "</div>";
						str += "</li>";
					}else{
						var fileCallPath = encodeURIComponent( attach.uploadPath +"/"+ 
		        				attach.uuid+"_"+attach.fileName);
		        		
		        		var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
		        		
						str += "<li ";
		        		str += "data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'><div>";
		        		str += "<span> " + attach.fileName + "</span>";
		        		str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='file' ";
		        		str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
		        		str += "<img src='/resources/img/attach.png'>";
		        		str += "</div>";
		        		str += "</li>";
					}
				});
				$(".uploadResult ul").html(str);
				
			}); // end getjson
		})(); // end function;
		
		$(".uploadResult").on("click", "button", function(e) {
		    console.log("delete file");
		    
		    if(confirm("Remove this file? ")){
		    	var targetLi = $(this).closest("li");
		    	targetLi.remove();
		    }
		   
		});
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5mb;
		
		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			return true;
		}
		$("input[type='file']").change(function(e){
			var formData = new FormData();
			var inputFile = $("input[name = 'uploadFile']");
			var files = inputFile[0].files;
			
			for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
					return false;	
				}
				formData.append("uploadFile",files[i]);
			}
			$.ajax({
				url : '/uploadAjaxAction',
				processData : false,
				contentType : false,data:
				formData,type:'POST',
				dataType : 'json',
					success : function(result){
						console.log(result);
						showUploadResult(result); //업로드 결과 처리 함수
					}
			}); //$.ajax
		});
		
		function showUploadResult(uploadResultArr){
			
			if(!uploadResultArr || uploadResultArr.length == 0){return;}
			
			var uploadUL = $(".uploadResult ul");
			var str = "";
			
			$(uploadResultArr).each(function(i,obj){
				if(obj.image){
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_"+obj.uuid+"_"+obj.fileName);
					
					str += "<li data-path='" + obj.uploadPath + "'";
					str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'";
					str += " ><div>";
					str += "<button type='button' data-file=\'" + fileCallPath + "\' ";
					str += "data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName=" + fileCallPath + "'>";
					str += "</div>";
					str += "</li>";
            	}else{
            		var fileCallPath = encodeURIComponent( obj.uploadPath +"/"+ 
            				obj.uuid+"_"+obj.fileName);
            		
            		var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
            		
            		str += "<li ";
            		str += "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'><div>";
            		str += "<span> " + obj.fileName + "</span>";
            		str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='file' ";
            		str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
            		str += "<img src='/resources/img/attach.png'>";
            		str += "</div>";
            		str += "</li>";
            	}
			});
			uploadUL.append(str);
		}
		function showImage(fileCallPath) {
		    alert(fileCallPath);
		    $(".bigPictureWrapper").css("display", "flex").show();
		    $(".bigPicture")
		        .html("<img src='/display?fileName=" + fileCallPath + "'>")
		        .animate({ width: '100%', height: '100%' }, 1000);
		}
		
		$(".bigPictureWrapper").on("click",function(e){
			$(".bigPicture").animate({width:'0%',height:'0%'},1000);
			setTimeout(function(){
				$('.bigPictureWrapper').hide();
			},1000);
		});
	});
</script>




            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Board Read Page</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action ="/board/modify" method="post">
                            	<input type='hidden' name = 'pageNum' value='<c:out value="${cri.pageNum}"/>'>
                            	<input type='hidden' name = 'amount' value='<c:out value="${cri.amount}"/>'>
                            	<input type='hidden' name = 'keyword' value='<c:out value="${cri.keyword}"/>'>
                            	<input type='hidden' name = 'type' value='<c:out value="${cri.type}"/>'>
                            	<div class = "form-group">
                            		<label>Bno</label><input class="form-control" name = 'bno' 
                            		value='<c:out value="${board.bno}"/>' readonly="readonly">
                            	</div>
                            
                            	<div class = "form-group">
                            		<label>Title</label><input class="form-control" name = 'title' 
                            		value='<c:out value="${board.title}"/>'>
                            	</div>
                            	
                            	<div class = "form-group">
                            		<label>Text area</label>
                            		<textarea class="form-control" rows="3" name='content'><c:out 
                            			value = "${board.content}"/></textarea>		
                            	</div>
                            	
                            	<div class = "form-group">
                            		<label>Writer</label><input class="form-control" name = 'writer' 
                            		value='<c:out value="${board.writer}"/>'>
                            	</div>
                            	
                            	<div class="form-group">
                            		<label>RegDate</label>
                            		<input class="form-control" name='regDate'
                            			value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.
                            			regdate}"/>' readonly="readonly">
                            	</div>
                            	
                            	<div class="form-group">
                            		<label>Update Date</label>
                            		<input class="form-control" name='updateDate'
                            			value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.
                            			updateDate}"/>' readonly="readonly">
                            	</div>
                            	
                            	<button type="submit" data-oper='modify' class="btn btn-default"
                         			>Modify</button>
                         		<button type="submit" data-oper='remove' class="btn btn-danger"
                         			>remove</button>
                            	<button type="submit" data-oper='list' class="btn btn-info"
                         			>List</button>
                            </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    
                    
                    <div class="row">
						<div class="col-lg-12">
							<div class = "panel panel-default">
								<div class = "panel panel-heading">Files</div>
									<div class = "panel-body">
            							<div class = "form-group uploadDiv">
            								<input type = "file" name = 'uploadFile' multiple>
            							</div>
            						</div>
								<div class="panel-body">
									<div class = 'uploadResult'>
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->

<%@ include file = "../includes/footer.jsp" %>