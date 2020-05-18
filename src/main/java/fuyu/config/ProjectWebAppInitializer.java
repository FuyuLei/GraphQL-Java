package fuyu.config;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer creates both the
 * DispatcherServlet and ContextLoaderListener so you don't need to declare it
 * in web.xml.
 * 
 * @author louis.huang
 *
 */
public class ProjectWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * The @Configuration classes returned from getRootConfigClasses() will be
	 * used to configure the application context created by
	 * ContextLoaderListener
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * The @Configuration classes returned from getServletConfigClass() will
	 * define beans for DispatcherServlet's application context.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * Setting up multi-part support to temporarily store uploaded files at SystemConfig.TEMPORARY_UPLOAD_DIRECTORY.
	 * you must specify the temporary file path where the file will be written during the upload. 
	 * StandardServletMultipartResolver won’t work unless you configure this minimum detail.
	 * The single-argument constructor for MultipartConfigElement that you’ve been using thus far 
	 * takes the absolute path to a directory in the file system where the uploaded file will be written temporarily.
	 * 
	 * The container may have already configured a temporary directory for file uploads?
	 */
//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//		// set max size limit to 15Mb, total size limit to 15Mb
//		// this will create another "tmp" directory inside the location!
//		registration.setMultipartConfig(
//				new MultipartConfigElement(SystemConfiguration.temp_Dir, 1024 * 1024 * 1024, 1024 * 1024 * 1024, 0));
//	}
}
