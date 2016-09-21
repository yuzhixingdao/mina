var BuiForm = {
	/**
	 * 创建表单中的控件
	 * @param {Object} id
	 */
	create : function(id){
		id = id.indexOf('#') < 0 ? '#'+id : id;
		BUI.use('bui/form',function (Form) {
			var form = new Form.HForm({
				srcNode : id
			});
			form.render();
		});
	}
};