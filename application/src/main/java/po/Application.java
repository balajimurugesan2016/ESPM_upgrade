package po;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan({"com.sap.cloud.sdk", "po"})
@ServletComponentScan({"com.sap.cloud.sdk", "po"})
public class Application extends SpringBootServletInitializer implements ApplicationRunner
{


    @Override
    protected SpringApplicationBuilder configure( final SpringApplicationBuilder application )
    {
        return application.sources(Application.class);
    }

    public static void main( final String[] args )
    {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	
		
	}
    
    
}
