package hello.controllers;

import hello.models.Greeting;
import leap.core.validation.annotations.Required;
import leap.lang.util.ShortID;
import leap.web.annotation.http.GET;
import leap.web.api.mvc.ApiController;
import leap.web.api.mvc.ApiResponse;

public class GreetingController extends ApiController {

    @GET
    public ApiResponse<Greeting> greeting(@Required String name) {
        return ApiResponse.of(new Greeting(ShortID.randomID(), String.format("Hello, %s", name)));
    }

}