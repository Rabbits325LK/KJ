package com.keepjob.common.util.json;
/**
 * 
 * JSON消息模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author 
 * 
 */
public class JSONMessage implements java.io.Serializable {

	private static final long serialVersionUID = -8444144791409348753L;

	private boolean success = false;
	
	//当success false 时消息体存在
	private String message = "";
	
	//后台返回数据字符串 JSON格式
	private String data = null;
	
	public JSONMessage(String message){
		this.setMessage(message);
	}
	
	public JSONMessage(String message, String data){
		this.setMessage(message);
		this.setData(data);
	}
	/**
	 * 创建成功消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createSuccessMessage(){
		return new SuccessJSONMessage("操作成功。");
	}
	/**
	 * 创建成功消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createSuccessMessage(String message){
		return new SuccessJSONMessage(message);
	}
	/**
	 * 创建成功消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createSuccessMessage(String message, String data){
		return new SuccessJSONMessage(message, data);
	}
	/**
	 * 创建失败消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createFailedMessage(){
		return new FailedJSONMessage("操作失败。");
	}
	/**
	 * 创建失败消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createFailedMessage(String message){
		return new FailedJSONMessage(message);
	}
	/**
	 * 创建失败消息
	 * @param message
	 * @return
	 */
	public static JSONMessage createFailedMessage(String message, String data){
		return new FailedJSONMessage(message, data);
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
/**
 * 成功消息
 * @author xl
 *
 */
class SuccessJSONMessage extends JSONMessage {
	private static final long serialVersionUID = 6655344701720068932L;
	
	public SuccessJSONMessage(String message) {
		super(message);
		this.setSuccess(true);
	}
	
	public SuccessJSONMessage(String message, String data) {
		super(message, data);
		this.setSuccess(true);
	}
}

/**
 * 失败消息
 * @author xl
 *
 */
class FailedJSONMessage extends JSONMessage {
	private static final long serialVersionUID = 7284501562088762039L;

	public FailedJSONMessage(String message) {
		super(message);
		this.setSuccess(false);
	}
	
	public FailedJSONMessage(String message, String data) {
		super(message, data);
		this.setSuccess(false);
	}
}
