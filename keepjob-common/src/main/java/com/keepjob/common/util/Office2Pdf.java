package com.keepjob.common.util;

import java.io.File;
import java.io.IOException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2Pdf {
	
	private int sofficePort;
	
	public Office2Pdf(int sofficePort){
		this.sofficePort=sofficePort;
	}
	
	public boolean office2pdf(String office,String pdf) throws IOException{
		File o = new File(office);
		File p = new File(pdf);
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(sofficePort); 
        try { 
        	try{
        		connection.connect();
        	}catch(java.net.ConnectException ce){
        		ce.printStackTrace();
        		return false;
        	}
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(o, p);
        } catch(Exception e) { 
            throw e;
        } finally { 
            try{ 
            	if(connection != null){
            		connection.disconnect();
            		connection = null;
        		}
        	}catch(Exception e){
            	
            } 
        } 
        return true;
	}
}
