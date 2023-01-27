package mx.com.tutum;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static Date startupDateTime = Calendar.getInstance().getTime();
	public static long cacheTimeStamp = Calendar.getInstance().getTimeInMillis();

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
