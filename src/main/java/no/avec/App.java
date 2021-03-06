package no.avec;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.util.Arrays.asList;

/**
 * Created by avec on 29/07/16.
 */
public class App {
    public static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static final String RESTAURANTS = "restaurants";

    private MongoClient mongoClient;
    private MongoDatabase db;


    void dropCollection() {
        db.getCollection(RESTAURANTS).drop();
    }

    /*
        String json = document.toJson(new JsonWriterSettings(true));
        System.out.println(json);
    */
    void findDocument() {
        FindIterable<Document> iterable = db.getCollection(RESTAURANTS).find();
        iterable.forEach((Block) document -> LOG.info(document.toString()));
    }

    void createDocument() {
        LOG.info("name: {}",db.getName());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        try {
            db.getCollection(RESTAURANTS).insertOne(
                    new Document("address",
                            new Document()
                                    .append("street", "2 Avenue")
                                    .append("zipcode", "10075")
                                    .append("building", "1480")
                                    .append("coord", asList(-73.9557413, 40.7720266)))
                            .append("borough", "Manhattan")
                            .append("cuisine", "Italian")
                            .append("grades", asList(
                                    new Document()
                                            .append("date", format.parse("2014-10-01T00:00:00Z"))
                                            .append("grade", "A")
                                            .append("score", 11),
                                    new Document()
                                            .append("date", format.parse("2014-01-16T00:00:00Z"))
                                            .append("grade", "B")
                                            .append("score", 17)))
                            .append("name", "Vella")
                            .append("restaurant_id", "41704620"));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    void connect(String host) {
        mongoClient = new MongoClient(host);
        db = mongoClient.getDatabase("test");
    }

    void close() {
        mongoClient.close();
    }
}
