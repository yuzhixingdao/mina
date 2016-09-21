<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html>
<html>
	<head>
		
		<title>��Ϣ�б�</title>
		
		<%@include file="/base/base.jsp" %>
		
		<script type="text/javascript">
			Base.mainInfo("��Ϣ�б�");
		</script>
		
	</head>
	<body>
		
		<div class="rightinfo">
		
			<form id="form_page" class="form-horizontal" action="<s:url value="/back/message_queryPage.do" />" method="post">
			
			<div>
	            <div class="control-group span7">
	              <label class="control-label">�������ݣ�</label>
	              <div class="controls">
	                <input type="text" name="message.messageContent" value="<s:property value='message.messageContent' />" class="control-text"/>
	              </div>
	            </div>
	            <div class="form-actions span5">
	              <button id="btnSearch" type="submit" onclick="Page.submit();" class="button button-primary">��ѯ</button>
	            </div>
	            
            </div>
			<div style="border: 1px solid #fff;">
				<table class="table table-bordered">
					<thead>
						<tr>
			    			<th colspan="12" style="text-align: right;" align="right">
				            <ul class="toolbar">
				              <li><input type="button" class="button button-info" onclick="Form.action('<s:url value="/page/message_add.jsp" />');" value="���"/></li>
				              <li><input type="button" class="button button-info" onclick="Form.removes('<s:url value="/back/message_removeData.do" />','#form_page [name=\'ids\']');" value="ɾ��"/></li>
				            </ul>
				            </th>
				        </tr>
						<tr>
					        <th style="text-align: center; width:35px;">���</th>
					        <th style="text-align: center; width:30px;"><input id="checkboxAll" type="checkbox" value="" onclick="Base.checkboxAll(this,'ids')"/></th>
					        <th style="text-align: center; width:100px;">messageId</th>
					        <th style="text-align: center; width:100px;">Token</th>
					        <th style="text-align: center; width:100px;">�״η���ʱ��</th>
					        <th style="text-align: center; width:100px;">����ʱ��</th>
					        <th style="text-align: center; width:100px;">����ʱ��</th>
					        <th style="text-align: center; width:100px;">�Ƿ����</th>
					        <th style="text-align: center;">��������</th>
					        <th style="text-align: center;">�����û�</th>
					        <th width="150" style="text-align: center; width:100px;">����</th>
				        </tr>
					</thead>
					<tbody>
						<s:iterator value="page.List" status="status">
					        <tr>
						        <td style="text-align: center;"><s:property value="#status.index+1" /></td>
						        <td style="text-align: center;"><input id="checkboxId" name="ids" type="checkbox" value="<s:property value='message.id' />" /></td>
						        <td style="text-align: center;" title="${message.id }">
						        	...<tag:string operation="substringLast" value="${message.id }" sublength="10"/>
						        </td>
						        <td style="text-align: center;" title="${message.token }">
						        	...<tag:string operation="substringLast" value="${message.token }" sublength="10"/>
						        </td>
						        <td style="text-align: center;"><s:date name="message.firstDate" format="yyyy-MM-dd hh:mm:ss.S" /></td>
						        <td style="text-align: center;"><s:date name="message.sendDate" format="yyyy-MM-dd hh:mm:ss.S" /></td>
						        <td style="text-align: center;"><s:date name="message.receiveDate" format="yyyy-MM-dd hh:mm:ss.S" /></td>
						        <td style="text-align: center;"><s:property value='dictionaryInit.getDataByTypeCode("MESSAGE_SUCCESS_FLAG", message.successFlag)' /></td>
						        <td title='<s:property value="message.messageContent" />'><s:property value="(message.messageContent != null && message.messageContent.length() > 50) ? message.messageContent.substring(0,50) + \"...\" : message.messageContent" /></td>
						        <td style="text-align: center;" title="${devices.deviceNo}-${devices.userId}-${devices.other}">
						        	<label title='<s:property value="devices.deviceNo" />'><tag:string value="${devices.deviceNo }" operation="substringLast" sublength="5" /></label> - 
						        	<label title='<s:property value="devices.userId" />'><tag:string value="${devices.userId }" operation="substringLast" sublength="5" /></label> - 
						        	<label title='<s:property value="devices.other" />'><s:property value="devices.other" /></label>
						        </td>
						        <td style="text-align: center;">
						        	<a href="<s:url value='/back/message_queryData.do' />?id=<s:property value='message.id' />" class="tablelink">����</a>
						        	<a href="javascript:void(0);" onclick="Form.remove('<s:url value="/back/message_removeData.do" />?ids=<s:property value='message.id' />');" class="tablelink">ɾ��</a>
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