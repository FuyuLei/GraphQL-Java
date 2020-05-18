package fuyu.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class Default {
		
	@Bean(name = "systemMainPage")
	public String systemMainPage() {
		return "http://localhost:8080/cleanExample/";
	}

	@Bean(name = "tempUrl")
	public String tempUrl() {
		return "C:\\Users\\GIGABYTE\\git\\GFaceManager\\Temp\\";
	}
}
