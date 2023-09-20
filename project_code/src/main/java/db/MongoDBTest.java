package db;

import com.mongodb.client.*;
import org.bson.Document;


public class MongoDBTest {

    public static void main(String[] args){
        System.setProperty("org.mongodb.driver.connection.logging", "none");
        MongoClient mongoClient = null;
        try{
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("regieLog");
            System.out.println("Connect to database successfully");
            // mongoDatabase.createCollection();
            MongoCollection<Document> collection = mongoDatabase.getCollection("regieStudentSystemLog");
            System.out.println("集合 test 选择成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }
    }

