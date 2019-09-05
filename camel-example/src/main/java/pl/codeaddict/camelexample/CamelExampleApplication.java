package pl.codeaddict.camelexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CamelExampleApplication {
    private static final Logger log = LoggerFactory.getLogger(CamelExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CamelExampleApplication.class, args);
    }

}
