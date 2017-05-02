package org.lzx.hros.schedule;

import org.lzx.hros.service.EmpManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PayJob extends QuartzJobBean {
	// 判断作业是否执行的旗标
	private boolean isRunning = false;
	// 注入该作业类依赖的业务逻辑组件
	private EmpManager empMgr;	

	public void setEmpMgr(EmpManager empMgr) {
		this.empMgr = empMgr;
	}


	// 定义任务执行体
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		if (!isRunning) {
			System.out.println("开始调度任务： 自动结算工资！");
			isRunning = true;
			//调用业务逻辑方法
			empMgr.autoPay();
			//打卡结束。恢复旗标等待下次调度
			isRunning=false;
		}
	}

}
