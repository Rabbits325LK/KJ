package com.keepjob.wechat;

import javax.servlet.http.HttpServletRequest;

import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.user.LanguageType;
import org.sword.wechat4j.user.User;
import org.sword.wechat4j.user.UserManager;

public class KeepJob  extends WechatSupport{

	
	
	public KeepJob(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void click() {
		// TODO Auto-generated method stub
		String userName = this.wechatRequest.getFromUserName();
		System.out.println("click:"+userName);
	}

	@Override
	protected void location() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void locationSelect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onLink() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onLocation() {
		// TODO Auto-generated method stub
		System.out.println("onLocation");
	}

	@Override
	protected void onText() {
		// TODO Auto-generated method stub
		String userName = this.wechatRequest.getFromUserName();
		UserManager userManager = new UserManager();
		User user = userManager.getUserInfo(userName);
        String content = user.getNickName() + "，有什么可以帮到您?";
        responseText(content);
	}

	@Override
	protected void onUnknown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onVideo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onVoice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picPhotoOrAlbum() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picSysPhoto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void picWeixin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scan() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scanCodePush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void scanCodeWaitMsg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void subscribe() {
		// TODO Auto-generated method stub
		System.out.println("关注");
		MenuUtil menuUtil = new MenuUtil();
		try {
			menuUtil.createMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("创建菜单报错");
			e.printStackTrace();
		}
	}

	@Override
	protected void templateMsgCallback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void unSubscribe() {
		// TODO Auto-generated method stub
		System.out.println("取消关注");
		MenuUtil menuUtil = new MenuUtil();
		try {
			menuUtil.deleteMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("删除菜单报错");
			e.printStackTrace();
		}
	}

	@Override
	protected void view() {
		// TODO Auto-generated method stub
		MenuUtil menuUtil = new MenuUtil();
		
		System.out.println("click View");
	}

	@Override
	protected void kfCloseSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void kfCreateSession() {
		// TODO Auto-generated method stub
//		MenuUtil menuUtil = new MenuUtil();
//		try {
//			menuUtil.createMenu();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("创建会话");
	}

	@Override
	protected void kfSwitchSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onShortVideo() {
		// TODO Auto-generated method stub
		
	}

	
	
}
