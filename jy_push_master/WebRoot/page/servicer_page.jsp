<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>消息列表</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("服务列表");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
		
			<form id="form_page" class="form-horizontal" action="<s:url value="/back/servicer_queryPage.do" />" method="post">
			
			<div>
	            <div class="control-group span7">
	              <label class="control-label">发送内容：</label>
	              <div class="controls">
	                <input type="text" name="message.messageContent" value="<s:property value='message.messageContent' />" class="control-text"/>
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
					        <th style="text-align: center; width:35px;">序号</th>
					        <th style="text-align: center; width:100px;">IP</th>
					        <th style="text-align: center; width:100px;">端口号</th>
					        <th style="text-align: center; width:100px;">状态</th>
					        <th style="text-align: center; width:100px;">Session数量</th>
					        <th style="text-align: center; width:100px;">ifc_url(slave地址)</th>
					        <th width="150" style="text-align: center; width:100px;">操作</th>
				        </tr>
					</thead>
					<tbody>
						<s:iterator value="page.List" status="status">
					        <tr>
						        <td style="text-align: center;"><s:property value="#status.index+1" /></td>
						        <td style="text-align: center;"><s:property value='ip' /></td>
						        <td style="text-align: center;"><s:property value='port' /></td>
						        <td style="text-align: center;"><s:property value='state' /></td>
						        <td style="text-align: center;"><s:property value='sessionNumber' /></td>
						        <td style="text-align: center;"><s:property value='ifcUrl' /></td>
						        <td style="text-align: center;">
						        	<a href="<s:url value='/back/servicer_queryData.do' />?id=<s:property value='id' />" class="tablelink">详情</a>
						        	<a href="<s:url value='/back/servicer_editData.do' />?id=<s:property value='id' />" class="tablelink">编辑</a>
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