package com.mysite.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationItem {

	private String title;
	private String path;

    /* Constructor Injection */
	@Inject
	public NavigationItem(@ValueMapValue(name = "title") String title, @ValueMapValue(name = "page_path") String path) {
		this.title= title;
		this.path = path;
	}
	public String toString() {
		return "[" + title + " " + path + "]";
	}

	public String getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}

}