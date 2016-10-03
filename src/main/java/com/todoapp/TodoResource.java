package com.todoapp;

	

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoResource {

    private static final String API_CONTEXT = "/api/v1";

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
            todoService.createNewTodo(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/todos", "application/json", (request, response)

                -> todoService.Rand(), new JsonTransformer());

        get(API_CONTEXT + "/getall", "application/json", (request, response)

                -> todoService.findAll(), new JsonTransformer());
        
        get(API_CONTEXT + "/getfilterd", "application/json", (request, response)

                -> todoService.findAllFilterd(request.body()), new JsonTransformer());
        
        
        post(API_CONTEXT + "/getfilterd", "application/json", (request, response) -> {
  
            return todoService.findAllFilterd(request.body());
        }, new JsonTransformer());
        
        
        
        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.delete(request.params(":id"), request.body()), new JsonTransformer());
    
    
    }


}
