package mk.ukim.finki.wp.lab.configuration;

import mk.ukim.finki.wp.lab.web.CourseChoiceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean courseChoiceFilterRegistrator() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CourseChoiceFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("Course Choice Filter");
        filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
}
