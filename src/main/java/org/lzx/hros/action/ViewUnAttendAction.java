package org.lzx.hros.action;

import java.util.List;

import org.lzx.hros.service.EmpManager;
import org.lzx.hros.vo.AttendBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 查看非正常考勤的Action
 * @author Skye
 *
 */
public class ViewUnAttendAction extends ActionSupport {

	@Autowired
	private EmpManager mgr;
	
	private List<AttendBean> unAttend;
	//unAttend属性的setter和getter方法
	public void setUnAttend(List<AttendBean> unAttend)
	{
		this.unAttend = unAttend;
	}
	public List<AttendBean> getUnAttend()
	{
		return this.unAttend;
	}
	public String execute()
		throws Exception
	{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		List<AttendBean> result = mgr.unAttend(user);
		setUnAttend(result);
		return SUCCESS;
	}
	
}
