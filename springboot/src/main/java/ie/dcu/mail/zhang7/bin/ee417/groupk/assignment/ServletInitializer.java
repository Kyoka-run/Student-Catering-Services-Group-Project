package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ee417GroupKAssignmentApplication.class);
	}

}
