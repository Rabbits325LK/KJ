package com.keepjob.sys.generator;


public interface UniqueCodeGeneratorHandler {
	/**
	 * 根据code查询
	 * @param category
	 * @return
	 */
	public String getUniqueCode(UniqueCodeCategory category);
	
	/**
	 * 根据code查询
	 * @param category
	 * @return
	 */
	public String getUniqueCode(UniqueCodeCategory category, Integer length);
}
