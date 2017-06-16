package hello.interceptors;

import leap.core.security.UserPrincipal;
import leap.core.validation.Validation;
import leap.web.action.ActionContext;
import leap.web.action.ActionExecution;
import leap.web.action.ActionInterceptor;
import leap.web.api.meta.model.MApiOperation;
import leap.web.api.meta.model.MApiPath;
import leap.web.route.Route;

import java.util.Date;

public class OpLogInterceptor implements ActionInterceptor {
    @Override
    public void completeExecuteAction(ActionContext context, Validation validation,
                                      ActionExecution execution) throws Throwable {
        Route route = context.getRoute();
        // url
        MApiPath path = route.getExtension(MApiPath.class);
        if(null != path) {
            // http method
            MApiOperation op = route.getExtension(MApiOperation.class);
            // user
            UserPrincipal user = context.getRequest().getUser();
            String u = user == null?"anonymous":user.getName();
            //todo: save log: {user} do {path + op} at now(); 
            System.out.println(u + " call [" + op.getMethod().name() + " " 
                    + path.getPathTemplate()+"] at " + new Date());
        }
    }
}
