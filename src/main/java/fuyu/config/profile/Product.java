package fuyu.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "product" })
public class Product {

	@Bean(name = "systemMainPage")
	public String systemMainPage() {
		return "https://onion.gigabyte.com.tw/cleanExample/";
	}
	
	@Bean(name = "tempUrl")
	public String tempUrl() {
		return "/home/GFaceManager/Temp/";
	}
}
