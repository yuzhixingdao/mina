/**
 * 表单基本操作
 */
var Form = {
	/**
	 * 表单提交
	 * @param {Object} jqueryClass jquery 选择器
	 */
	submit : function(jqueryClass){
		
		var dos = $(jqueryClass).find(':input[data-options]');
		
		if(dos != null && dos != undefined){
			
			//alert(dos.length);
			
			for(var i=0; i<dos.length; i++){
				var inp = dos.eq(i);
				var options = inp.attr("data-options");
				
				if(options != null && options != undefined){
					var json = eval("("+options+")");
					
					if(true == json.required){
						var ii = inp.next('[name="msg"]');
						if(inp.val()==""){
							if(ii != null && ii != undefined && ii.length > 0){
								ii.eq(ii.length-1).html('请输入必输项');
							}else{
								alert('请输入必输项');
							}
							inp.focus();
							return false;
						}else{
							if(ii != null && ii != undefined && ii.length > 0){
								ii.eq(ii.length-1).html('');
							}
						}
					}
					
				}
				
			}
		}
		
		
		dos = $(jqueryClass).find(':select[data-options]');
		
		if(dos != null && dos != undefined){
			
			//alert(dos.length);
			
			for(var i=0; i<dos.length; i++){
				var inp = dos.eq(i);
				var options = inp.attr("data-options");
				
				if(options != null && options != undefined){
					var json = eval("("+options+")");
					
					if(true == json.required){
						var ii = inp.next('[name="msg"]');
						if(inp.val()==""){
							if(ii != null && ii != undefined && ii.length > 0){
								ii.eq(ii.length-1).html('请输入必输项');
							}else{
								alert('请输入必输项');
							}
							inp.focus();
							return false;
						}else{
							if(ii != null && ii != undefined && ii.length > 0){
								ii.eq(ii.length-1).html('');
							}
						}
					}
					
				}
				
			}
		}
		
		return true;
	},
	/**
	 * 删除数据
	 * @param {Object} url 删除地址
	 */
	remove : function(url){
		var ret = window.confirm("确定删除数据");
		if(ret){
			window.location.href=url;
		}
	},
	/**
	 * 多项删除数据
	 * @param {Object} url 删除地址
	 * @param {Object} jqueryClass jquery 选择器
	 */
	removes : function(url, jqueryClass){
		var ckIds = $(jqueryClass);
		var ids = "";
		for(var i=0; i<ckIds.length; i++){
			if(ckIds[i].checked){
				ids += ckIds[i].value + ",";
			}
		}
		
		if(ids != ""){
			ids = ids.substring(0,ids.length-1);
			var ret = window.confirm("确定删除数据");
			if(ret){
				window.location.href = url + '?ids=' + ids;
			}
		}else{
			alert("请选择要删除的数据");
		}
	},
	/**
	 * 请求服务
	 * @param {Object} url
	 */
	action : function(url){
		window.location.href = url;
	},
	/**
	 * 下拉列表框加载数据
	 * @param {Object} htmlId
	 * @param {Object} url
	 * @param {Object} key
	 * @param {Object} val
	 */
	selectLoad : function(htmlId, url, key, val, selectVal){
		htmlId = htmlId.indexOf('#') < 0 ? '#'+htmlId : htmlId;
		
		if(selectVal != null && selectVal != undefined && selectVal != ''){
			Ajax.send({
				url : url,
				success : function(ret){
					if(ret){
						var htmlOption = '<option value="" selected="selected">请选择</option>';
						for(var i=0; i<ret.length; i++){
							if(selectVal==ret[i][key]){
								htmlOption += '<option value="'+ret[i][key]+'" selected="selected">'+ret[i][val]+'</option>';
							}else{
								htmlOption += '<option value="'+ret[i][key]+'">'+ret[i][val]+'</option>';
							}
						}
						$(htmlId).html(htmlOption);
					}
				}
			});
		}else{
			Ajax.send({
				url : url,
				success : function(ret){
					if(ret){
						var htmlOption = '<option value="" selected="selected">请选择</option>';
						for(var i=0; i<ret.length; i++){
							htmlOption += '<option value="'+ret[i][key]+'">'+ret[i][val]+'</option>';
						}
						$(htmlId).html(htmlOption);
					}
				}
			});
		}
	},
	/**
	 * 联动加载数据
	 * @param {Object} th
	 * @param {Object} linkageId
	 * @param {Object} url
	 * @param {Object} key
	 * @param {Object} val
	 */
	changeLinkage : function(th, linkageId, url, key, val){
		var vl = th.value;
		linkageId = linkageId.indexOf('#') < 0 ? '#'+linkageId : linkageId;
		if(vl != null && vl != undefined && vl != ''){
			Ajax.send({
				url : url + vl,
				success : function(ret){
					if(ret){
						var htmlOption = '<option value="" selected="selected">请选择</option>';
						for(var i=0; i<ret.length; i++){
							htmlOption += '<option value="'+ret[i][key]+'">'+ret[i][val]+'</option>';
						}
						$(linkageId).html(htmlOption);
					}
				}
			});
		}else{
			$(linkageId).html('');
		}
	}
	
	
};