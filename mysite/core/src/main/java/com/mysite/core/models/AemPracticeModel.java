package com.mysite.core.models;

import java.util.List;

public interface AemPracticeModel {

	public String getTitle();

	public String getImagePath();

	public boolean isBackgroundImage();

	public boolean isRunMode();

	public List<NavigationItem> getNavigationItems();

}


