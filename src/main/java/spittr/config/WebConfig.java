package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.controller")
public class WebConfig implements WebMvcConfigurer {

	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver tr = new SpringResourceTemplateResolver();
		tr.setPrefix("/WEB-INF/templates/");
		tr.setSuffix(".html");
		tr.setTemplateMode("HTML5");
		tr.setCacheable(false);
		
		return tr;
	}
	
	@Bean
	public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		TemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);
		
		return engine;
	}
	
	@Bean
	public ViewResolver viewResolver(TemplateEngine templateEngine) {
//		InternalResourceViewResolver irvr = new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
//		irvr.setExposeContextBeansAsAttributes(true);
		
		ThymeleafViewResolver tvr = new ThymeleafViewResolver();
		tvr.setTemplateEngine(templateEngine);
		
		return tvr;
	}
}
