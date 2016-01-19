package com.keepjob.sys.generator;


public interface UniqueCodeGeneratorDAO {
	
	public UniqueCodeGenerator getUniqueCodeGenerator(String prefix);
	
	public boolean updateUniqueCodeGenerator(UniqueCodeGenerator record);

	public int saveUniqueCodeGenerator(UniqueCodeGenerator record);
}
