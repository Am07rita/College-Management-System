package edu.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import edu.cms.configure.JwtFilter;

@SpringBootApplication
public class CollegeManagementSystemApplication {

	@SuppressWarnings({ "rawtypes","unchecked" })
  @Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registerationBean=new FilterRegistrationBean ();
		registerationBean.setFilter(new JwtFilter());
		registerationBean.addUrlPatterns("/teacher/*","/course/*");
		return registerationBean;
	}
		public static void main(String[] args) {
			SpringApplication.run(CollegeManagementSystemApplication.class, args);
			System.out.println("College Management System is running!!");
	}
}
