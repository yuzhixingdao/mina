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
	 * text/html;charset=UTF-8 ���ñ���
	 */
	private String contentType = "text/html;charset=GBK";

	/**
	 * ��������
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;

	/**
	 * ��ҳ����
	 */
	protected Map<String, Object> page;// ��ҳ����
	protected Integer pageNum = 1;// ��ѯҳ��
	protected Integer pageSize = 10;// ÿҳ��Ŀ

	protected String msg;// ���ش�����Ϣ
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
	// * ��ȡ��¼��Ϣ
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
	 * ��ͷ��������ͨ���ַ���
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
	 * ��ͷ��������Ϣ
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
	 * ��ͷ��������Ϣ
	 * 
	 * @param json
	 *            json�ַ���
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
