var Base = {
	/**
	 * ��ѡ��ѡ�����(ȫѡ��ȫѡ)
	 * @param {Object} th this
	 * @param {Object} htmlName Ҫѡ��ѡ���nameֵ
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
