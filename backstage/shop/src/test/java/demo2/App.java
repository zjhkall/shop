package demo2;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@MapperScan("com.example.demo2.mapper")
public class App implements WebMvcConfigurer {
	//主函数，整个项目的启动入口
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
	}

	//上传图片要用
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/shop/**").addResourceLocations("file:D:/create/shop/");
	}

}
