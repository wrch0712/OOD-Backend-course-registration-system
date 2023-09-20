package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
MongoDBLogHelper provide useful tools for write log and read log in MongeDB database
functions of MongoDBLogHelper are called in RegieSystem and RegieStudentSystem to record log of the system
It's a tool class, functions are static, so they can be called using class name without instantiation
 */
public class MongoDBLogHelper {
    public static void writeLog (int userId, String action) {
        System.setProperty("org.mongodb.driver.connection.logging", "none");
        MongoClient mongoClient = null;
        try{
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("regieLog");
            MongoCollection<Document> collection = mongoDatabase.getCollection("regieStudentSystemLog");
            collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("user_id", userId)
                    .append("action", action)
                    .append("time", new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    // print num last log
    public static void showLog (int num) {
        System.setProperty("org.mongodb.driver.connection.logging", "none");
        MongoClient mongoClient = null;
        try{
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("regieLog");
            MongoCollection<Document> collection = mongoDatabase.getCollection("regieStudentSystemLog");
            List<Document> documents = collection.find(new Document()).projection(new Document().append("_id", 0))
                    .sort(new Document("time", -1)).limit(num).into(new ArrayList<>());
            for (Document document : documents) {
                System.out.println(document.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }
}
