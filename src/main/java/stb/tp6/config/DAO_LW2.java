package stb.tp6.config;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

import stb.tp6.model.STB;
import stb.tp6.model.STBList;

public class DAO_LW2 {

	private final String URI = "mongodb://lw2user:lw2user@ds041484.mlab.com:41484/lw2projet";
	private MongoCollection collection;
	private MongoClient mongoClient;
	
	public DAO_LW2(){
		MongoClientURI mongoClientURI = new MongoClientURI(URI);
		mongoClient = new MongoClient(mongoClientURI);
		Jongo jongo = new Jongo(mongoClient.getDB(mongoClientURI.getDatabase()));
		collection = jongo.getCollection("lw2collection");
	}
	
	public void insert(Object o){
		collection.save(o);
	}
	
	public STB find(int id){
		STB stb = collection.findOne("{id:"+id+"}").as(STB.class);
		return stb;
	}
	
	public STBList findAll(){
		MongoCursor<STB> stbCursor = collection.find().as(STB.class);
		STBList stbList = new STBList();
		while (stbCursor.hasNext()) {
		    stbList.getSTBs().add(stbCursor.next());
		}
		return stbList;
	}
	
}
