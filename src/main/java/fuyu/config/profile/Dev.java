package fuyu.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "dev" })
public class Dev {

	@Bean(name = "systemMainPage")
	public String systemMainPage() {
		return "http://10.1.21.34:8080/cleanExample/";
	}

	@Bean(name = "tempUrl")
	public String tempUrl() {
		return "/tmp/";
	}
}
