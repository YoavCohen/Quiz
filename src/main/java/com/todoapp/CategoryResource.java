package com.todoapp;





import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class CategoryResource {

    private static final String API_CONTEXT = "/api/v1";

    private final CategoryService CategoryService;

    public CategoryResource(CategoryService CategoryService) {
        this.CategoryService = CategoryService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/Categorys", "application/json", (request, response) -> {
        	System.out.println(request.body());
            CategoryService.createNewCategory(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/Categorys/:id", "application/json", (request, response)

                -> CategoryService.find(request.params(":id")), new JsonTransformer());


        get(API_CONTEXT + "/getallcategories", "application/json", (request, response)

                -> CategoryService.findAll(), new JsonTransformer());
        
        
        
        
        
        put(API_CONTEXT + "/Categorys/:id", "application/json", (request, response)

                -> CategoryService.delete(request.params(":id"), request.body()), new JsonTransformer());
    
    
    }


}
