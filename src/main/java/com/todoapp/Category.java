package com.todoapp;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Category {
		private String id;
	    private String title;
	    private boolean done;
	    private Date createdOn = new Date();
	    
	    public Category(BasicDBObject dbObject) {
	        this.id = ((ObjectId) dbObject.get("_id")).toString();
	        this.title = dbObject.getString("title");
	        this.done = dbObject.getBoolean("done");
	        this.createdOn = dbObject.getDate("createdOn");
	    }

		public String getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public boolean isDone() {
			return done;
		}

		public Date getCreatedOn() {
			return createdOn;
		}
}
