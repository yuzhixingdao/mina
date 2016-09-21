/**
 * ����������
 */
var Form = {
	/**
	 * ���ύ
	 * @param {Object} jqueryClass jquery ѡ����
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
								ii.eq(ii.length-1).html('�����������');
							}else{
								alert('�����������');
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
								ii.eq(ii.length-1).html('�����������');
							}else{
								alert('�����������');
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
	 * ɾ������
	 * @param {Object} url ɾ����ַ
	 */
	remove : function(url){
		var ret = window.confirm("ȷ��ɾ������");
		if(ret){
			window.location.href=url;
		}
	},
	/**
	 * ����ɾ������
	 * @param {Object} url ɾ����ַ
	 * @param {Object} jqueryClass jquery ѡ����
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
			var ret = window.confirm("ȷ��ɾ������");
			if(ret){
				window.location.href = url + '?ids=' + ids;
			}
		}else{
			alert("��ѡ��Ҫɾ��������");
		}
	},
	/**
	 * �������
	 * @param {Object} url
	 */
	action : function(url){
		window.location.href = url;
	},
	/**
	 * �����б���������
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
						var htmlOption = '<option value="" selected="selected">��ѡ��</option>';
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
						var htmlOption = '<option value="" selected="selected">��ѡ��</option>';
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
	 * ������������
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
						var htmlOption = '<option value="" selected="selected">��ѡ��</option>';
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