package hello.controllers;

import hello.models.Greeting;
import leap.core.doc.annotation.Doc;
import leap.core.security.SecurityContext;
import leap.core.security.UserPrincipal;
import leap.core.security.annotation.AllowAnonymous;
import leap.core.validation.annotations.Required;
import leap.lang.http.HTTP;
import leap.web.Request;
import leap.web.annotation.DefaultValue;
import leap.web.annotation.http.GET;
import leap.web.annotation.http.POST;
import leap.web.api.mvc.ApiController;
import leap.web.api.mvc.ApiResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GreetingController extends ApiController {

    private static final AtomicInteger counter = new AtomicInteger();

    @GET
    public ApiResponse<Greeting> greeting(@Required @DefaultValue("World") String name) {
        return ApiResponse.of(new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name)));
    }

    @GET("/anonymous")
    @AllowAnonymous
    public ApiResponse<Greeting> greetingAnonymous(@Required @DefaultValue("World") String name) {
        return ApiResponse.of(new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name)));
    }

    @GET("/userinfo1")
    public ApiResponse<Map<String,Object>> userinfo1(UserPrincipal user) {
        return ApiResponse.of(map(user));
    }

    @GET("/userinfo2")
    public ApiResponse<Map<String,Object>> userinfo2(Request request) {
        return ApiResponse.of(map(request.getUser()));
    }

    @GET("/userinfo3")
    public ApiResponse<Map<String,Object>> userinfo3() {
        return ApiResponse.of(map(Request.current().getUser()));
    }

    @GET("/userinfo4")
    public ApiResponse<Map<String,Object>> userinfo4() {
        return ApiResponse.of(map(SecurityContext.user()));
    }

    @GET("/perm")
    public ApiResponse<String> perm() {
        return ApiResponse.of("OK");
    }

    @POST("/test_log_ok")
    @Doc(summary = "测试成功操作")
    public ApiResponse testLogOk() {
        return ApiResponse.OK;
    }

    @POST("/test_log_err")
    @Doc(summary = "测试失败操作")
    public ApiResponse testLogErr() {
        return ApiResponse.err(HTTP.Status.BAD_REQUEST, "err");
    }

    protected Map<String,Object> map(UserPrincipal user) {
        Map<String,Object> map = new LinkedHashMap<>();

        map.put("id",        user.getId());
        map.put("name",      user.getName());
        map.put("loginName", user.getLoginName());

        return map;
    }

}