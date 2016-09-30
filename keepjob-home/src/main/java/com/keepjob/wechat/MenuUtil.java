package com.keepjob.wechat;

import java.util.ArrayList;
import java.util.List;

import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.menu.Menu;
import org.sword.wechat4j.menu.MenuButton;
import org.sword.wechat4j.menu.MenuManager;

/**
 * 菜单创建工具类
 * @author lk
 *
 */
public class MenuUtil {
	
	/**
	 * 菜单工具类
	 */
	private MenuManager menuManager = new MenuManager();

	/**
	 * 创建菜单
	 * @throws Exception
	 */
	public void createMenu() throws Exception {
		Menu menu = new Menu();

		List<MenuButton> buttons = new ArrayList<MenuButton>();
		buttons.add(createbutton1());
		buttons.add(createbutton2());
		buttons.add(createbutton3());
		menu.setButton(buttons);

		menuManager.create(menu);
	}
	
	/**
	 * 删除菜单
	 * @throws Exception
	 */
	public void deleteMenu() throws Exception {
		menuManager.delete();
	}
	
	/**
	 * 查看菜单
	 * @throws Exception
	 */
	public void queryMenu() throws Exception {
		Menu menu = menuManager.getMenu();
		List<MenuButton> list = menu.getButton();
		for(MenuButton mb:list) {
			List<MenuButton> subbuttons = mb.getSubButton();
			System.out.println(subbuttons.get(0).getName());
		}
	}

	/**
	 * 创建第一个菜单项
	 * 
	 * @return
	 */
	public MenuButton createbutton1() {
		MenuButton menuButton = new MenuButton();
		menuButton.setName("菜单一");

		MenuButton mb1 = new MenuButton();
		mb1.setType(EventType.view);
		mb1.setName("菜单一按钮一");
		mb1.setUrl("http://xx");

		MenuButton mb2 = new MenuButton();
		mb2.setType(EventType.view);
		mb2.setName("菜单一按钮二");
		mb2.setUrl("http://xx");

		List<MenuButton> buttons = new ArrayList<MenuButton>();
		buttons.add(mb1);
		buttons.add(mb2);

		menuButton.setSubButton(buttons);
		return menuButton;
	}

	/**
	 * 创建第二个菜单项
	 * 
	 * @return
	 * @throws Exception
	 */
	public MenuButton createbutton2() throws Exception {
		MenuButton menuButton = new MenuButton();
		menuButton.setName("菜单二");

		MenuButton mb1 = new MenuButton();
		mb1.setType(EventType.view);
		mb1.setName("菜单二按钮一");
		mb1.setUrl("http://xx");

		MenuButton mb2 = new MenuButton();
		mb2.setType(EventType.view);
		mb2.setName("菜单二按钮二");
		mb2.setUrl("http://xx");

		List<MenuButton> buttons = new ArrayList<MenuButton>();
		buttons.add(mb1);
		buttons.add(mb2);

		menuButton.setSubButton(buttons);
		return menuButton;
	}

	public MenuButton createbutton3() throws Exception {
		MenuButton menuButton = new MenuButton();
		menuButton.setName("菜单三");
		return menuButton;
	}
	
	public static void main(String[] args) throws Exception {
		MenuUtil menuUtil = new MenuUtil();
		menuUtil.createMenu();
//		menuUtil.deleteMenu();
//		menuUtil.queryMenu();
	}
	
}
