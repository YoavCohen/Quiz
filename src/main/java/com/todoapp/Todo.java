package com.todoapp;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Todo {

    private String id;
    private String title;
    private boolean done;
    private Date createdOn = new Date();
    private String coranswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer[]= new String[4];
    private List<String> test= new ArrayList<String>();
    public Todo(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.done = dbObject.getBoolean("done");
        this.createdOn = dbObject.getDate("createdOn");
        this.coranswer = dbObject.getString("coranswer");
        answer[0]=dbObject.getString("answer1");
        answer[1]=dbObject.getString("answer2");
        answer[2]=dbObject.getString("answer3");
        answer[3]=dbObject.getString("answer4");
        for(int i=0;i<4;i++)
        	test.add(i, answer[i]);
        Collections.shuffle(test); //shuffle answers
    }
    
    public void createAnswers(){
    	answer= new String[4];
    	answer[0]=answer1;
    	answer[1]=answer2;
    	answer[2]=answer3;
    	answer[3]=answer4;
    }
    
    public String getcoranswer(){
    	return coranswer;
    }
    
    public String[] getAnswers(){
    	return answer;
    }

    public String getTitle() {
        return title;
    }

    public String getAnswer(int i) {
        return answer[i-1];
    }
    
    public boolean isDone() {
        return done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
