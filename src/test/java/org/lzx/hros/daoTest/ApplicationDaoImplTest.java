package org.lzx.hros.daoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lzx.hros.dao.impl.AttendTypeDaoImpl;
import org.lzx.hros.domain.AttendType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ApplicationDaoImplTest{

	@Autowired
	private AttendTypeDaoImpl attdao;
	
	
	@Test	
	public void test() {
	AttendType attendType = new AttendType(null, "加班", 300);
	attdao.save(attendType);
	System.out.println("保存新的attendtype成功!");
	}
	
	//HibernateTmplate模糊查询测试
	@Test
	public void test2(){
		
		List<AttendType> atts = new ArrayList<AttendType>();
		atts = attdao.findByLike();
		for (AttendType attendType : atts) {
			System.out.println("查询结果："+attendType);
		}
	}
}
