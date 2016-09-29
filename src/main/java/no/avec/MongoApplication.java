package no.avec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot starts here!
 */
@SpringBootApplication
public class MongoApplication {

    @Value("${mongodb.host}")
    private String mongoDbHost;

    public MongoApplication() { //NOSONAR
        App app = new App();
        app.connect(mongoDbHost);

        app.createDocument();
        app.findDocument();
        app.dropCollection();

        app.close();
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args); //NOSONAR
    }
}
