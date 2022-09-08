package com.mysite.core.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Model(adaptables =  {SlingHttpServletRequest.class, Resource.class}, 
adapters=AemPracticeModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AemPracticeModelImpl implements AemPracticeModel{
	

	 private static final Logger log = LoggerFactory.getLogger(AemPracticeModelImpl.class);
	@ValueMapValue
	String title;
	
	@ValueMapValue
	String link_path;
	
	@ValueMapValue
	boolean isBackground;
	
	@ChildResource
	Resource navigationList;
	
	@OSGiService
	SlingSettingsService slingSettings;
	
	@Override
	public boolean isBackgroundImage() {
		return isBackground;
	}
	@Override
	public boolean getRunMode() {
		return slingSettings.getRunModes().contains("author");
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public String getImagePath() {
		if(isBackground && slingSettings.getRunModes().contains("publish")) {
			return link_path;
		}
		return StringUtils.EMPTY;				
	}

	@Override
	public List<AemPracticeModel.NavigationItem> getNavigationItems() {
		List<NavigationItem> list = new ArrayList<>();
		navigationList.getChildren().forEach(item -> {
			list.add(new NavigationItem(item.getValueMap().get("title", ""), item.getValueMap().get("page_path", "")));
		});
		return list;
	}

	
	

}
