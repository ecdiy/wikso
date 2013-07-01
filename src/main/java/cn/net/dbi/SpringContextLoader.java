package cn.net.dbi ;
 

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 *  
 */
public class SpringContextLoader extends ContextLoaderListener {
	private static final Logger log = Logger
			.getLogger(SpringContextLoader.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);
		springBeanToServlet(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		super.contextDestroyed(sce);
	}

	public static void springBeanToServlet(ServletContext sc) {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		String names[] = applicationContext.getBeanDefinitionNames();
		StringBuffer info = new StringBuffer("\n");
		for (String name : names) {
			if (name.startsWith("org.springframework"))
				continue;
			Object obj = applicationContext.getBean(name);
			info.append("\t" + name + "=" + obj.getClass().getName() + ";\n");
			sc.setAttribute(name, obj);
		}
		if (log.isInfoEnabled())
			log.info(info.toString());
	}

}

