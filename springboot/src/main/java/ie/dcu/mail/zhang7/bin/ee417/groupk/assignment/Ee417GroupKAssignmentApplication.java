package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletResponse;

@EnableWebMvc
@SpringBootApplication
public class Ee417GroupKAssignmentApplication implements WebMvcConfigurer {
	
	public static final String[] corsAllowedUrl = {
			"http://localhost:8080",
			"https://ee417.ince.app"
	};
	
	@Controller
	public class RootRedirectController {
	    @GetMapping(value = "/")
	    public void redirectToServices(HttpServletResponse httpServletResponse){
	        httpServletResponse.setHeader("Location", "/html/index.html");
	        httpServletResponse.setStatus(302);
	    }
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/**")
          .addResourceLocations(
        		"classpath:/META-INF/resources/EE417-GroupAssignment-main/", 
  				"classpath:/resources/EE417-GroupAssignment-main/", 
  				"classpath:/static/EE417-GroupAssignment-main/", 
  				"classpath:/public/EE417-GroupAssignment-main/"
        );
    }

	public static void main(String[] args) {
		SpringApplication.run(Ee417GroupKAssignmentApplication.class, args);
	}

}
