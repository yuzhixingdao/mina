<%@ tag pageEncoding="GBK"%>

<%@ attribute name="operation" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="value" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="sublength" required="false" rtexprvalue="true" type="java.lang.Integer"%>

<!-- 参数说明 -->

<%
	if(operation != null && value != null){
		
		if(operation.equals("substringLast") && value.length() > sublength && sublength > 0){
			int length = value.length();
			out.print(value.substring(length - sublength));
		}else{
			out.print(value);
		}
		
	}else{
		out.print("");
	}
%>

