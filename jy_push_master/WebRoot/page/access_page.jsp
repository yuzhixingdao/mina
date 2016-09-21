<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Access管理</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("Access管理");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
		
			<form id="form_page" class="form-horizontal" action="<s:url value="/back/access_queryPage.do" />" method="post">
			
			<div>
	            <div class="control-group span7">
	              <label class="control-label">AccessKey：</label>
	              <div class="controls">
	                <input type="text" name="access.accessKey" value="<s:property value='access.accessKey' />" class="control-text"/>
	              </div>
	            </div>
	            <div class="control-group span7">
	              <label class="control-label">AccessId：</label>
	              <div class="controls">
	                <input type="text" name="access.accessId" value="<s:property value='access.accessId' />" class="control-text"/>
	              </div>
	            </div>
	            <div class="control-group span7">
	              <label class="control-label">secretKey：</label>
	              <div class="controls">
	                <input type="text" name="access.secretKey" value="<s:property value='access.secretKey' />" class="control-text"/>
	              </div>
	            </div>
	            <div class="form-actions span5">
	              <button id="btnSearch" type="submit" onclick="Page.submit();" class="button button-primary">查询</button>
	            </div>
	            
            </div>
			<div style="border: 1px solid #fff;">
				<table class="table table-bordered">
					<thead>
						<tr>
			    			<th colspan="12" style="text-align: right;" align="right">
				            <ul class="toolbar">
				              <li><input type="button" class="button button-info" onclick="Form.action('<s:url value="/page/access_edit.jsp" />');" value="添加"/></li>
				              <li><input type="button" class="button button-info" onclick="Form.removes('<s:url value="/back/access_removeData.do" />','#form_page [name=\'ids\']');" value="删除"/></li>
				            </ul>
				            </th>
				        </tr>
						<tr>
					        <th style="text-align: center; width:35px;">序号</th>
					        <th style="text-align: center; width:30px;"><input id="checkboxAll" type="checkbox" value="" onclick="Base.checkboxAll(this,'ids')"/></th>
					        <th style="text-align: center;">AccessKey</th>
					        <th style="text-align: center;">AccessId</th>
					        <th style="text-align: center;">secretKey</th>
					        <th style="text-align: center;">平台类型</th>
					        <th style="text-align: center;">创建时间</th>
					        <th width="150" style="text-align: center; width:100px;">操作</th>
				        </tr>
					</thead>
					<tbody>
						<s:iterator value="page.List" status="status">
					        <tr>
						        <td style="text-align: center;"><s:property value="#status.index+1" /></td>
						        <td style="text-align: center;"><input id="checkboxId" name="ids" type="checkbox" value="<s:property value='id' />" /></td>
						        <td style="text-align: center;"><s:property value="accessKey" /></td>
						        <td style="text-align: center;"><s:property value="accessId" /></td>
						        <td style="text-align: center;"><s:property value="secretKey" /></td>
						        <td style="text-align: center;"><s:property value='dictionaryInit.getDataByTypeCode("TERMINAL_TYPE", terminalType)' /></td>
						        <td style="text-align: center;"><s:date name="createDate" format="yyyy-MM-dd hh:mm:ss" /></td>
						        <td style="text-align: center;">
						        	<a href="<s:url value='/back/access_queryData.do' />?id=<s:property value='id' />" class="tablelink">详情</a>
						        	<a href="<s:url value='/back/access_editData.do' />?id=<s:property value='id' />" class="tablelink">编辑</a>
						        </td>
					        </tr>
				        </s:iterator>
					</tbody>
				</table>
				
				<tag:page page="${page }" formId="form_page"></tag:page>
			
			</div>
			
			</form>
			
			<br/>
		</div>
		
	</body>
</html>