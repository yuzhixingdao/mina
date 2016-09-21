<%@ tag pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="page" required="true" rtexprvalue="true" type="java.util.Map"%>
<%@ attribute name="formId" required="true" rtexprvalue="true" type="java.lang.String"%>

<div class="pagin">
	<div class="message">
		总共<i class="blue">&nbsp;${page.RecTotal}&nbsp;</i>条，
		每页<i class="blue">&nbsp;<input name="pageSize" value="${page.PageSize}" style="border: 1px solid #DDDDDD; width: 30px; color: #056DAE; text-align: center;"/>&nbsp;</i>条；
		总共<i class="blue">&nbsp;${page.PageTotal}&nbsp;</i>页，
		当前第<i class="blue">&nbsp;${page.PageNum}&nbsp;</i>页
	</div>
	<ul class="paginList">
		<li class="paginItem ${page.PageNum==1 ? 'current' : ''}"><a href="javascript:void(0);" onclick="Page.gotoPage(1);"> &lt;&lt; </a></li>
		<li class="paginItem ${page.PageNum==1 ? 'current' : ''}"><a href="javascript:void(0);" onclick="Page.gotoPage(${page.PageNum-1});"> &lt; </a></li>
		
		<c:forEach begin="${page.PageNum-5 <= 1 ? 1 : page.PageNum-5}" end="${page.PageNum+5>=page.PageTotal ? page.PageTotal : page.PageNum+5}" step="1" var="PageNum">
			<li class="paginItem ${page.PageNum==PageNum ? 'current' : ''}"><a href="javascript:void(0);" onclick="Page.gotoPage(${PageNum});">${PageNum}</a></li>
		</c:forEach>
		
		<li class="paginItem ${page.PageNum==page.PageTotal ? 'current' : ''}"><a href="javascript:void(0);" onclick="Page.gotoPage(${page.PageNum+1});"> &gt; </a></li>
		<li class="paginItem ${page.PageNum==page.PageTotal ? 'current' : ''}"><a href="javascript:void(0);" onclick="Page.gotoPage(${page.PageTotal});"> &gt;&gt; </a></li>
	</ul>
</div>

<input type="hidden" id="page_pageNum" name="pageNum" value="${page.PageNum}" />
<%--<input type="hidden" id="page_pageSize" name="pageSize" value="${page.PageSize}" />--%>

<script type="text/javascript">

var Page = {
	gotoPage : function(pageNum){
		if(pageNum >= 1 && pageNum <= ${page.PageTotal}){
			$("#${formId} #page_pageNum").val(pageNum);
			$("#${formId}").submit();
		}
	},
	submit : function(){
		$("#${formId} #page_pageNum").val(1);
		$("#${formId}").submit();
	}
};

</script>