package com.keepjob.sys.generator.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGenerator;
import com.keepjob.sys.generator.UniqueCodeGeneratorDAO;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;

@Service("uniqueCodeGeneratorHandler")
public class UniqueCodeGeneratorHandlerImpl implements UniqueCodeGeneratorHandler {
	
	private Lock lock = new ReentrantLock();
	
	@Autowired
	private UniqueCodeGeneratorDAO uniqueCodeGeneratorDAO;
		
	@Override
	public String getUniqueCode(UniqueCodeCategory category) {
		try{
			lock.lock();
			UniqueCodeGenerator record = uniqueCodeGeneratorDAO.getUniqueCodeGenerator(category.getValue());
			if (null == record) {
				record = new UniqueCodeGenerator();
				record.setCurrentValue(1L);
				record.setLength(16);
				record.setPrefix(category.getValue());
				uniqueCodeGeneratorDAO.saveUniqueCodeGenerator(record);
			}else{
				record.setCurrentValue(record.getCurrentValue()+1);
				uniqueCodeGeneratorDAO.updateUniqueCodeGenerator(record);
			}
			
			/*if(category == UniqueCodeCategory.HEALTH_PHYSICAL_CODE){
				return StringUtils.trimToEmpty("0") + StringUtils.patchZeroToString(String.valueOf(record.getCurrentValue()), (record.getLength()-StringUtils.trimToEmpty(category.getValue()).length()));
			}else{
				return StringUtils.trimToEmpty(category.getValue()) + StringUtils.patchZeroToString(String.valueOf(record.getCurrentValue()), (record.getLength()-StringUtils.trimToEmpty(category.getValue()).length()));
			}*/
			return StringUtils.trimToEmpty(category.getValue()) + StringUtils.patchZeroToString(String.valueOf(record.getCurrentValue()), (record.getLength()-StringUtils.trimToEmpty(category.getValue()).length()));
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException("获取业务唯一编码异常。");
		}finally{
			lock.unlock();
		}
	}

	@Override
	public String getUniqueCode(UniqueCodeCategory category, Integer length) {
		try{
			lock.lock();
			UniqueCodeGenerator record = uniqueCodeGeneratorDAO.getUniqueCodeGenerator(category.getValue());
			if (null == record) {
				record = new UniqueCodeGenerator();
				record.setCurrentValue(1L);
				record.setLength(length);
				record.setPrefix(category.getValue());
				uniqueCodeGeneratorDAO.saveUniqueCodeGenerator(record);
			}else{
				record.setCurrentValue(record.getCurrentValue()+1);
				uniqueCodeGeneratorDAO.updateUniqueCodeGenerator(record);
			}
			return StringUtils.trimToEmpty(category.getValue())+
					StringUtils.patchZeroToString(String.valueOf(record.getCurrentValue()), (record.getLength()-StringUtils.trimToEmpty(category.getValue()).length()));
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException("获取业务唯一编码异常。");
		}finally{
			lock.unlock();
		}
	}
}
