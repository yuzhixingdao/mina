var Base = {
	/**
	 * 复选框选择操作(全选或不全选)
	 * @param {Object} th this
	 * @param {Object} htmlName 要选择复选框的name值
	 */
	checkboxAll : function(th, htmlName){
		var ret = th.checked;
		$('[name="'+htmlName+'"]').attr('checked', ret);
	},
	
	/**
	 * 
	 */
	mainInfo : function(txt){
		var info = window.top.infoFrame.document.getElementById("index_info");
		info.innerHTML = txt;
	}
};
