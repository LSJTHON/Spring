console.log("Reply Module..............");

var replyService = (function(){
	//콜백 함수는 어떤 함수를 실행시키고 난 이후에 결과를 받을 함수 혹은, 그 다음에 실해될 함수를 의미
	function add(reply, callback, error){
		console.log("add reply............");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr,status,err){
				if(error){
					error();
				}
			});
		}
		
		function remove(rno, callback, error){
			$.ajax({
				type : 'delete',
				url : '/replies/' + rno,
				success : function(deleteResult, status, xhr){
					if(callback){
						callback(deleteResult);
						}
					},
					error : function(xhr, status, er){
						if(error){
							error(er);
						}
					}
				});
			}
	
		return {
			add:add,
			getList : getList,
			remove : remove
		};
})();