package fuyu.config;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAsync
@EnableScheduling
@Configuration
@ComponentScan(basePackages = { "fuyu" }, excludeFilters = {@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {

	@Bean
	public MimetypesFileTypeMap mimetypesFileTypeMap() {
		return new MimetypesFileTypeMap();
	}
}