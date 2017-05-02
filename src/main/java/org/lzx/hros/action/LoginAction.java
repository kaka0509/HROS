package org.lzx.hros.action;

import org.lzx.hros.domain.Manager;
import org.lzx.hros.service.EmpManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 登录Action
 * @author Skye
 *
 */
public class LoginAction extends ActionSupport {
	// 注入依赖的业务组件
	@Autowired
	private EmpManager mgr;

	// 员工登录成功Result
	private final String EMP_RESULT = "emp";
	// 经理登录成功Result
	private final String MGR_RESULT = "mgr";
	// 封装请求参数
	private Manager manager;
	// 登录的验证码
	private String vercode;
	// 登录后提示信息
	private String tip;

	// manager属性的setter和getter方法
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Manager getManager() {
		return this.manager;
	}

	// vercode属性的setter和getter方法
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getVercode() {
		return this.vercode;
	}

	// tip属性的setter和getter方法
	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return this.tip;
	}

	// 处理用户请求
	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的rand属性
		String ver2 = (String) ctx.getSession().get("rand");
		// 判断验证码是否通过
		if (vercode.equalsIgnoreCase(ver2)) {
			// 调用业务逻辑方法处理登录请求
			int result = mgr.validLogin(getManager());
			if (result == EmpManager.LOGIN_EMP) {
				ctx.getSession().put(WebConstant.USER, manager.getName());
				//为LEVEL属性赋值，用于拦截器拦截非登陆操作
				ctx.getSession().put(WebConstant.LEVEL, WebConstant.EMP_LEVEL);
				setTip("您已经成功登录系统");
				return EMP_RESULT;
			}
			if (result == EmpManager.LOGIN_MGR) {
				ctx.getSession().put(WebConstant.USER, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL, WebConstant.MGR_LEVEL);
				setTip("经理您好，您已经成功登录系统");
				return MGR_RESULT;
			}
			// 用户名和密码不匹配
			else {
				setTip("用户名/密码不匹配");
				return ERROR;
			}
		}
		// 验证码不匹配
		else {
			setTip("验证码不匹配，请重新输入");
			return ERROR;
		}
	}
	
}
