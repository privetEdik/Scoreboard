package kettlebell.servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import kettlebell.service.LoadingBasicDataService;

@WebListener
public class ApplicationListener implements ServletContextListener {
	private LoadingBasicDataService service;

    public void contextInitialized(ServletContextEvent sce)  { 
         service = new LoadingBasicDataService();
         service.loadingBasicData();
    }
	
}
