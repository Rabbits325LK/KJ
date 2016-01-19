package com.keepjob.common.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class JAXBParser<T> {
	
	public String toXml(T object){
		StringWriter result = new StringWriter();
		try{
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.encoding", "GBK");
			marshaller.marshal(object, result);
			
			return result.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
