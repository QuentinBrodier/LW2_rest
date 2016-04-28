package stb.tp6.config;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

public class DAO_LW2 {

	private final String URI = "mongodb://lw2user:lw2user@ds041484.mlab.com:41484/lw2projet";
	
	public DAO_LW2(){
		
	}
	
	public boolean insert(Object o){
		
		MongoClientURI mongoClientURI = new MongoClientURI(URI);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		Jongo jongo = new Jongo(mongoClient.getDB(mongoClientURI.getDatabase()));
		MongoCollection collection = jongo.getCollection("lw2collection");
		WriteResult wr = collection.save(o);
		System.out.println(wr);
		mongoClient.close();
		return true;
	}
	
}
