package hello.controllers;

import hello.models.Greeting;
import leap.core.validation.annotations.Required;
import leap.web.annotation.DefaultValue;
import leap.web.annotation.http.GET;
import leap.web.api.mvc.ApiController;
import leap.web.api.mvc.ApiResponse;
import java.util.concurrent.atomic.AtomicInteger;

public class GreetingController extends ApiController {

    private static final AtomicInteger counter = new AtomicInteger();

    @GET
    public ApiResponse<Greeting> greeting(@Required @DefaultValue("World") String name) {
        return ApiResponse.of(new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name)));
    }

}