package com.jy.master.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.jy.framework.util.GsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ServletContextAware {

	/**
	 * text/html;charset=UTF-8 设置编码
	 */
	private String contentType = "text/html;charset=GBK";

	/**
	 * 服务属性
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;

	/**
	 * 分页属性
	 */
	protected Map<String, Object> page;// 分页数据
	protected Integer pageNum = 1;// 查询页码
	protected Integer pageSize = 10;// 每页数目

	protected String msg;// 返回错误信息
	protected String id;
	protected String ids;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	// /**
	// * 获取登录信息
	// *
	// * @return
	// */
	// protected MUserDTO getSessionLoginUser() {
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// if (session != null) {
	// Object loginUser = session.get("LoginUser");
	// return loginUser == null ? null : (MUserDTO) loginUser;
	// }
	//
	// return null;
	// }

	/**
	 * 向客服端输出普通的字符串
	 * 
	 * @param str
	 */
	protected void writer(String str) {
		response.setContentType(contentType);
		PrintWriter out = null;
		try {
			if (str != null) {
				out = response.getWriter();
				out.print(str);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 向客服端输出信息
	 * 
	 * @param obj
	 */
	protected void writerObject(Object obj) {
		response.setContentType(contentType);
		PrintWriter out = null;
		try {
			if (obj != null) {
				out = response.getWriter();
				out.print(GsonUtil.toJson(obj));
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 向客服端输出信息
	 * 
	 * @param json
	 *            json字符串
	 */
	protected void writerJson(String json) {
		response.setContentType(contentType);
		PrintWriter out = null;
		try {
			if (json != null) {
				out = response.getWriter();
				out.print(json);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Map<String, Object> getPage() {
		return page;
	}

	public void setPage(Map<String, Object> page) {
		this.page = page;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
