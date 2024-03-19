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
	return {add:add};
})();