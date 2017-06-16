package hello.controllers;

import hello.models.User;
import leap.core.doc.annotation.Doc;
import leap.lang.http.HTTP;
import leap.web.Request;
import leap.web.annotation.http.*;
import leap.web.api.mvc.ApiResponse;
import leap.web.api.mvc.ModelController;
import leap.web.api.mvc.params.DeleteOptions;
import leap.web.api.mvc.params.Partial;
import leap.web.api.mvc.params.QueryOptions;
import leap.web.api.mvc.params.QueryOptionsBase;
import leap.web.exception.ServerErrorException;

public class UserController extends ModelController<User> {

    @POST
    @Doc(summary = "创建用户", desc = "通过JSON指定属性创建一个用户")
    public ApiResponse createUser(Partial<User> user) {
        return createAndReturn(user);
    }

    @GET("/{id}")
    @Doc(summary = "获取用户", desc = "通过ID获取用户信息")
    public ApiResponse retrieveUser(String id, QueryOptionsBase options) {
        return get(id);
    }

    @PATCH("/{id}")
    @Doc(summary = "修改用户", desc = "修改指定ID的用户信息，没有传的属性表示不修改该属性")
    public ApiResponse updateUser(String id, Partial<User> user) {
        return updatePartial(id, user);
    }

    @DELETE("/{id}")
    @Doc(summary = "删除用户", desc = "通过指定ID删除用户")
    public ApiResponse deleteUser(String id) {
        return delete(id);
    }

    @GET
    @Doc(summary = "doc:user.md#summary", desc = "doc:user.md#desc")
    public ApiResponse queryUsers(QueryOptions options) {
        return queryList(options);
    }

    @GET("/err/400")
    @Doc(summary = "400响应", desc = "返回状态码400")
    public ApiResponse errorByResp(){
        // 返回错误对象
        return ApiResponse.err(HTTP.Status.BAD_REQUEST,
                "userError","用户参数错误");
    }
    @GET("/err/500")
    @Doc(summary = "500响应", desc = "如果没有传递任何参数，会返回500状态码")
    public ApiResponse errorByException(Request request){
        if(request.getParameters().isEmpty()){
            // 抛出异常
            throw new ServerErrorException(HTTP.SC_INTERNAL_SERVER_ERROR,"没有参数");
        }
        return ApiResponse.ACCEPTED;
    }
    @GET("/err/runtime")
    @Doc(summary = "未知异常", desc = "直接返回一个未知异常")
    public ApiResponse errorByRuntime(){
        throw new RuntimeException("未知异常");
    }

}