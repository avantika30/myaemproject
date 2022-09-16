package com.mysite.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.greendot.aem.core.sling.models.gdcorp.AemPracticeModel;
import com.greendot.aem.core.sling.models.gdcorp.AemPracticeModelImpl;

import ch.qos.logback.classic.Level;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class AemPracticeModelTest {

	private final AemContext ctx = new AemContext();

	@BeforeEach
	void setUp() throws Exception {

		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.WARN);

		ctx.addModelsForClasses(AemPracticeModelImpl.class);

		ctx.load().json("/com/mysite/core/resources/examplePage.json", "/content/commons/en/example");
	}

	@Test
	void testisBackGroundImage() {

		ctx.currentPage("/content/commons/en/example");

		Resource resource = ctx.request().getResource();
		AemPracticeModel model = resource.adaptTo(AemPracticeModel.class);

		assertNotNull(model, "Cannot adaptTo page");

		assertEquals(false, model.isBackgroundImage());
	}

	@Test
	void testisRunModel() {

		ctx.currentPage("/content/commons/en/example");

		Resource resource = ctx.request().getResource();
		AemPracticeModel model = resource.adaptTo(AemPracticeModel.class);

		assertNotNull(model, "Cannot adaptTo page");

		assertEquals(true, model.isRunMode());
	}

	@Test
	void testGetTitle() {

		ctx.currentPage("/content/commons/en/example");

		Resource resource = ctx.request().getResource();
		AemPracticeModel model = resource.adaptTo(AemPracticeModel.class);

		assertNotNull(model, "Cannot adaptTo page");

		assertEquals("ExamplePage", model.getTitle());
	}

	@Test
	void testGetImagePath() {
		ctx.currentPage("/content/commons/en/example");
		Resource resource = ctx.request().getResource();
		AemPracticeModel model = resource.adaptTo(AemPracticeModel.class);
		assertNotNull(model, "Cannot adaptTo page");
		assertEquals(null, model.getImagePath());
	}

	@Test
	void testGetNavigationItems() throws Exception {

		ctx.currentPage("/content/commons/en/example");
		ctx.currentResource(
				"/content/commons/en/example-page/jcr:content/root/responsivegrid/aem_practice/navigationList");
		Resource resource = ctx.request().getResource();
		AemPracticeModel model = resource.adaptTo(AemPracticeModel.class);
		model.getNavigationItems();
		assertNotNull(model);
	}

}
