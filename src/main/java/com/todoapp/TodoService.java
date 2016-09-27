package com.todoapp;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoService {

    private final DB db;
    private final DBCollection collection;

    public TodoService(DB db) {
        this.db = db;
        this.collection = db.getCollection("todos");
    }

    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Todo((BasicDBObject) dbObject));
        }
        return todos;
    }
    
    public List<Todo> Rand() {
        List<Todo> todos = new ArrayList<>();
        List<Todo> ret = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Todo((BasicDBObject) dbObject));
        }
        for(int j=0;j<10;j++)
        	Collections.shuffle(todos);
        for(int i=0;i<5;i++)
        	ret.add(todos.get(i));
        return ret;
    }
    
    

    public void createNewTodo(String body) {
    	System.out.println(body);
        Todo todo = new Gson().fromJson(body, Todo.class);
        todo.createAnswers();
        collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()).append("answer1", todo.getAnswer(1)).append("answer2", todo.getAnswer(2)).append("answer3", todo.getAnswer(3)).append("answer4", todo.getAnswer(4)).append("coranswer", todo.getcoranswer()));
    }

    public Todo find(String id) {
        return new Todo((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public Todo update(String todoId, String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);
    }
    
    public Todo delete(String todoId, String body){
    	collection.remove(new BasicDBObject("_id", new ObjectId(todoId)));
    	return null;
    }
}
