package com.example.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class MongoDBJava {

	public static void main(String[] args) {

		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase mongoDb = mongoClient.getDatabase("dbnashath");
			MongoCollection<Document> collection = mongoDb.getCollection("Users");

			MongoDBJava mongoDBJava = new MongoDBJava();
			mongoDBJava.updateDocument(collection);
			//mongoDBJava.addDocument(collection);
			//mongoDBJava.deleteDocument(collection);
			mongoDBJava.retrieveDocument(collection);
			
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}
	
	public void addDocument(MongoCollection<Document> collection){
		
		Document doc = new Document();
		doc.put("firstName", "Rifas");
		doc.put("LastName", "Nasar");
		doc.put("School", "Zahira College");
		doc.put("Age", 35);
		collection.insertOne(doc);
	}
	
	public void retrieveDocument(MongoCollection<Document> collection){
		
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> mongoIterator = documents.iterator();
		while(mongoIterator.hasNext()){
			Document document = mongoIterator.next();
			printDocument(document);
		}
		
	}
	
	public void deleteDocument(MongoCollection<Document> collection){
		
		Document document = new Document();
		document.put("_id", new ObjectId("58a2da955e9bbd141cb9cd3d"));
	    DeleteResult result = collection.deleteOne(document);
	    System.out.println(result);
	   
		
	}
	
	public void updateDocument(MongoCollection<Document> collection){
		Bson updateDoc = new Document("firstName","Nashath");
		Bson newDoc = new Document("School", "Zahira Collge");
		Bson updateOperationDocument = new Document("$set", newDoc);
		collection.updateOne(updateDoc, updateOperationDocument);
	}
	
	public void printDocument(Document document){
		System.out.println("======================");
		document.entrySet().stream().forEach(each -> {
			System.out.println("Key: " + each.getKey() + " Value: " + each.getValue());	
		});
		
	}
	

}
