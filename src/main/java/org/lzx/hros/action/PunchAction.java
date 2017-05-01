package org.lzx.hros.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lzx.hros.service.EmpManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证是否能打卡的Action
 * @author Skye
 *
 */
public class PunchAction extends ActionSupport {

	@Autowired
	private EmpManager mgr;

	// 封装处理结果的punchIsValid属性
	private int punchIsValid;

	// punchIsValid属性的setter和getter方法
	public void setPunchIsValid(int punchIsValid) {
		this.punchIsValid = punchIsValid;
	}

	public int getPunchIsValid() {
		return this.punchIsValid;
	}

	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String user = (String) ctx.getSession().get(WebConstant.USER);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 格式化当前时间
		String dutyDay = sdf.format(new Date());
		// 调用业务逻辑方法处理用户请求
		int result = mgr.validPunch(user, dutyDay);
		setPunchIsValid(result);
		return SUCCESS;
	}

}
