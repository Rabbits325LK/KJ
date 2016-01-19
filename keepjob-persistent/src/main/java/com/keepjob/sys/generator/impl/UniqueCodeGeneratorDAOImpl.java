package com.keepjob.sys.generator.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.sys.generator.UniqueCodeGenerator;
import com.keepjob.sys.generator.UniqueCodeGeneratorDAO;
import com.keepjob.sys.generator.UniqueCodeGeneratorMapper;

@Repository("uniqueCodeGeneratorDAO")
public class UniqueCodeGeneratorDAOImpl implements UniqueCodeGeneratorDAO {
	@Autowired
	private UniqueCodeGeneratorMapper uniqueCodeGeneratorMapper;

	@Override
	public UniqueCodeGenerator getUniqueCodeGenerator(String prefix) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("prefix", prefix);
		try{
			List<UniqueCodeGenerator> result = this.uniqueCodeGeneratorMapper.selectByExample(example);
			if(CollectionUtils.isNotEmpty(result)){
				return result.get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return null;
	}

	@Override
	public boolean updateUniqueCodeGenerator(UniqueCodeGenerator record) {
		try{
			this.uniqueCodeGeneratorMapper.updateByPrimaryKey(record);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}

	@Override
	public int saveUniqueCodeGenerator(UniqueCodeGenerator record) {
		return uniqueCodeGeneratorMapper.insert(record);
	}
}
