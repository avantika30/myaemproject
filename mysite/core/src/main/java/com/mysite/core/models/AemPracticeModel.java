package com.mysite.core.models;

import java.util.List;


public interface AemPracticeModel {
	
	public String getTitle();
	public String getImagePath();
	public boolean isBackgroundImage();
	public boolean getRunMode();
	public List<NavigationItem> getNavigationItems();
	
	class NavigationItem {
		String title;
		String path;
		
		NavigationItem(String title, String path){
			this.title = title;
			this.path = path;
		}

	public String getTitle() {
			return title;
		}

		public String getPath() {
			return path;
		}
		
	}

}


