package com.mysite.core.models;

import java.util.List;
import java.util.stream.Collectors;

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

@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, adapters = AemPracticeModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AemPracticeModelImpl implements AemPracticeModel {

	private static final Logger log = LoggerFactory.getLogger(AemPracticeModelImpl.class);

	private static final String PREFIX = "Test_prefix";
	private static final String PATH = "/content/mysite/en_GB";

	/* Access Modifiers */
	@ValueMapValue
	private String title;

	/* Access Modifiers */
	@ValueMapValue
	private String link_path;

	/* Access Modifiers */
	@ValueMapValue
	private boolean isBackground;

	/* Child Resourced as List */
	@ChildResource(name = "navigationList")
	private List<NavigationItem> navigationList;

	/* Access Modifiers */
	@OSGiService
	protected SlingSettingsService slingSettings;

	@Override
	public boolean isBackgroundImage() {
		return isBackground;
	}

	@Override
	public boolean isRunMode() {
		return slingSettings.getRunModes().contains("author");
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getImagePath() {
		/* Ternary Operator used */
		return isBackground && slingSettings.getRunModes().contains("publish") ? link_path : StringUtils.EMPTY;
	}

	@Override
	public List<NavigationItem> getNavigationItems() {
		/* Used Streams to filter items where path starts with a path and return updated object wherein Title is updated */
		return navigationList.stream().filter(item -> item.getPath().startsWith(PATH)).map(item -> {
			return new NavigationItem(PREFIX + item.getTitle(), item.getPath());
		}).collect(Collectors.toList());

	}

}

