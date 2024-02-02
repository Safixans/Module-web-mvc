package uz.pdp.springFrameworkCore.config;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import uz.pdp.springFrameworkCore.filters.RequestResponseTimeFIlter;
//import uz.pdp.springFrameworkCore.filters.SecurityFilter;
import uz.pdp.springFrameworkCore.interceptor.LogInInterceptor;

public class DispatcherInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                ""
        );
        registration.setMultipartConfig(multipartConfigElement);
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{/*new SecurityFilter(),*/new RequestResponseTimeFIlter()};
    }


}
