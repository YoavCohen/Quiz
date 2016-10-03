package com.todoapp;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CategoryService {

	    private final DB db;
	    private final DBCollection collection;

	    public CategoryService(DB db) {
	        this.db = db;
	        this.collection = db.getCollection("Categorys");
	    }

	    public List<Category> findAll() {
	        List<Category> Categorys = new ArrayList<>();
	        DBCursor dbObjects = collection.find();
	        while (dbObjects.hasNext()) {
	            DBObject dbObject = dbObjects.next();
	            Categorys.add(new Category((BasicDBObject) dbObject));
	        }
	        return Categorys;
	    }
	    
	
	    
	    

	    public void createNewCategory(String body) {
	        Category Category = new Gson().fromJson(body, Category.class);
	        collection.insert(new BasicDBObject("title", Category.getTitle()).append("done", Category.isDone()).append("createdOn", new Date()));
	    }

	    public Category find(String id) {
	        return new Category((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
	    }

	    public Category update(String CategoryId, String body) {
	        Category Category = new Gson().fromJson(body, Category.class);
	        collection.update(new BasicDBObject("_id", new ObjectId(CategoryId)), new BasicDBObject("$set", new BasicDBObject("done", Category.isDone())));
	        return this.find(CategoryId);
	    }
	    
	    public Category delete(String CategoryId, String body){
	    	collection.remove(new BasicDBObject("_id", new ObjectId(CategoryId)));
	    	return null;
	    }
	}


