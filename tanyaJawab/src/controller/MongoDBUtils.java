package controller;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import model.Jawaban;

import model.Person;
import model.Pertanyaan;


public class MongoDBUtils {	
	MongoDatabase database;
	MongoCollection<Person> personCollection;
	MongoCollection<Pertanyaan> pertanyaanCollection;
	MongoCollection<Jawaban> jawabanCollection;
	
	public MongoDBUtils() {
		// Creating Credentials 
	
		System.out.println("Connected to the database successfully");  
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));		
		 MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		// Accessing the database 
		database = mongo.getDatabase("tubes"); 
		database = database.withCodecRegistry(pojoCodecRegistry);
		
		personCollection = database.getCollection("personCollection", Person.class);
		pertanyaanCollection = database.getCollection("pertanyaanCollection", Pertanyaan.class);
		jawabanCollection = database.getCollection("jawabanCollection", Jawaban.class);
	}

	public ArrayList<Person> getPerson() throws IOException {		
		ArrayList<Person> resultList = new ArrayList<>();
		FindIterable<Person> personIterable = personCollection.find();		
		for (Person person : personIterable) {
			System.out.println(person);
			resultList.add(person);
		}		
		return resultList;
	}
	public ArrayList<Pertanyaan> getPertanyaan() throws IOException {		
		ArrayList<Pertanyaan> resultList = new ArrayList<>();
		FindIterable<Pertanyaan> pertanyaanIterable = pertanyaanCollection.find();		
		for (Pertanyaan Pertanyaan : pertanyaanIterable) {
			System.out.println(Pertanyaan);
			resultList.add(Pertanyaan);
		}		
		return resultList;
	}
	public ArrayList<Jawaban> getJawaban() throws IOException {		
		ArrayList<Jawaban> resultList = new ArrayList<>();
		FindIterable<Jawaban> jawabanIterable = jawabanCollection.find();		
		for (Jawaban jawaban : jawabanIterable) {
			System.out.println(jawaban);
			resultList.add(jawaban);
		}		
		return resultList;
	}
	public Person getOnePerson(String uname) throws IOException {		
		Person result=null;
		FindIterable<Person> personIterable = personCollection.find();	
		System.out.println("p1");
		for (Person person : personIterable) {
			System.out.println("p2");
			if(person.getUname().equals(uname)) {
				System.out.println(person.getUname()+" "+uname);
				result=person;
			}
			
		}		
		return result;
	}
	public Pertanyaan getOneQuestion(String idPertanyaan) throws IOException {		
		Pertanyaan result=null;
		FindIterable<Pertanyaan> pertanyaanIterable = pertanyaanCollection.find();	
		System.out.println("p1");
		for (Pertanyaan ask : pertanyaanIterable) {
			System.out.println("p2");
			if(ask.getKode().equals(idPertanyaan)) {
				System.out.println(ask.getKode()+" "+idPertanyaan);
				result=ask;
			}
			
		}		
		return result;
	}
	public boolean authenticator(String uname, String password) {
		FindIterable<Person> personIterable = personCollection.find();		
		for (Person person : personIterable) {
			if(person.getUname().equals(uname)) {
				System.out.println(person.getUname()+ " "+ uname);
				if(person.getPassword().equals(password)) {
					System.out.println(person.getPassword()+ " "+ password);
					return true;
				} 
				break;
			}
		}		
		return false;
	}
	public boolean userExist(String uname) {
		FindIterable<Person> personIterable = personCollection.find();		
		for (Person person : personIterable) {
			if(person.getUname().equals(uname)) {
				return true;
			}
		}		
		return false;
	}
	
	public boolean insertObjectQuestion(String uname, String kategori, String tingkat, int poinDiberikan, String pertanyaan) {
		List<String> idJawaban = new ArrayList<String>();
		String idPertanyaan = new ObjectId().toString();
		boolean status = false; 
		try {	
			Person person = getOnePerson(uname);
			if(person.getPoin()>poinDiberikan) {
				Pertanyaan tanya = new Pertanyaan(idPertanyaan, status, uname, kategori, tingkat, poinDiberikan, pertanyaan, idJawaban);	
				pertanyaanCollection.insertOne(tanya);			
				System.out.println("data inserted");
			}
			
			int newPoin = person.getPoin()-poinDiberikan;
			List<String>newListPertanyaan = person.getIdPertanyaan();
			newListPertanyaan.add(idPertanyaan);
			person.setIdPertanyaan(newListPertanyaan);
			personCollection.updateOne(Filters.eq("uname", uname), Updates.set("idPertanyaan", newListPertanyaan));
			personCollection.updateOne(Filters.eq("uname", uname), Updates.set("poin", newPoin));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	public boolean insertObjectAnswer(String idPertanyaan, String uname, String jawaban) {
		String idJawaban = new ObjectId().toString();
		boolean status = false; 
		int vote=0;
		try {	
			System.out.println("jawabannyaaa"+jawaban);
			Jawaban jawab = new Jawaban(idJawaban, uname, status, jawaban, vote);	
			jawabanCollection.insertOne(jawab);			
			System.out.println("data inserted");
			
			Person person = getOnePerson(uname);
			List<String>newListJawaban = person.getIdJawaban();
			newListJawaban.add(idJawaban);
			person.setIdJawaban(newListJawaban);
			personCollection.updateOne(Filters.eq("uname", uname), Updates.set("idJawaban", newListJawaban));
			
			Pertanyaan ask = getOneQuestion(idPertanyaan);
			List<String>newListJawaban2 = ask.getIdJawabanList();
			newListJawaban2.add(idJawaban);
			ask.setIdJawabanList(newListJawaban2);
			pertanyaanCollection.updateOne(Filters.eq("kode", idPertanyaan), Updates.set("idJawaban", newListJawaban2));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	public boolean insertObjectPerson(String password, String username, String education, String level, int poin) {
		List<String> idPertanyaan = new ArrayList<String>();
		List<String> idJawaban = new ArrayList<String>();
		try {	
			Person person = new Person (password, username, education, level, poin, idPertanyaan, idJawaban);	
			personCollection.insertOne(person);			
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}


}
