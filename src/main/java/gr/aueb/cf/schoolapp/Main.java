package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

import gr.aueb.cf.schoolapp.controllerview.InsertForm;
import gr.aueb.cf.schoolapp.controllerview.InsertUser;
import gr.aueb.cf.schoolapp.controllerview.LoginPage;
import gr.aueb.cf.schoolapp.controllerview.Menu;
import gr.aueb.cf.schoolapp.controllerview.SearchForm;
import gr.aueb.cf.schoolapp.controllerview.SearchUser;
import gr.aueb.cf.schoolapp.controllerview.UpdateDeleteForm;
import gr.aueb.cf.schoolapp.controllerview.UpdateDeleteUsers;


public class Main {
	
	private static Menu menu;
	private static InsertForm insertForm;
	private static SearchForm searchForm;
	private static UpdateDeleteForm updateDeleteForm;
	private static LoginPage loginPage;
	private static InsertUser insertUser;
	private static SearchUser searchUser;
	private static UpdateDeleteUsers updateDeleteUsers;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(false);
					
					insertForm = new InsertForm();
					insertForm.setLocationRelativeTo(null);
					insertForm.setVisible(false);
					
					searchForm = new SearchForm();
					searchForm.setLocationRelativeTo(null);
					searchForm.setVisible(false);
					
					updateDeleteForm = new UpdateDeleteForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
					
					loginPage = new LoginPage();
					loginPage.setLocationRelativeTo(null);
					loginPage.setVisible(true);
					
					insertUser = new InsertUser();
					insertUser.setLocationRelativeTo(null);
					insertUser.setVisible(false);
					
					searchUser = new SearchUser();
					searchUser.setLocationRelativeTo(null);
					searchUser.setVisible(false);
					
					updateDeleteUsers = new UpdateDeleteUsers();
					updateDeleteUsers.setLocationRelativeTo(null);
					updateDeleteUsers.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}
	
	public static InsertForm getInsertForm() {
		return insertForm;
	}

	public static SearchForm getSearchForm() {
		return searchForm;
	}

	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}
	
	public static InsertUser getInsertUser() {
		return insertUser;
	}
	
	public static SearchUser getSearchUser() {
		return searchUser;
	}
	
	public static LoginPage getLoginPage() {
		return loginPage;
	}
	
	public static UpdateDeleteUsers getUpdateDeleteUsers() {
		return updateDeleteUsers;
	}
	
	

}
