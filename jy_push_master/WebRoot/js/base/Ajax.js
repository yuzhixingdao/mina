var Ajax = {
	/**
	 * 发送请求
	 * @param {Object} jsonParam
	 */
	send : function(jsonParam){
		if(jsonParam != null && jsonParam != undefined){
			if(jsonParam.type == null || jsonParam.type == undefined) jsonParam.type = 'post';
			if(jsonParam.data == null || jsonParam.data == undefined) jsonParam.data = null;
			if(jsonParam.dataType == null || jsonParam.dataType == undefined) jsonParam.dataType = 'json';
			if(jsonParam.async == null || jsonParam.async == undefined) jsonParam.async = false;
			if(jsonParam.success == null || jsonParam.success == undefined) jsonParam.success = null;
			$.ajax({
				url : jsonParam.url,
				type : jsonParam.type,
				data : jsonParam.data,
				dataType : jsonParam.dataType,
				async : jsonParam.async,
				success : jsonParam.success
			});
		}
	}
	
};