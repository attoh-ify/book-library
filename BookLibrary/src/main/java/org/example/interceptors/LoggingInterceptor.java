package org.example.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@LoggingService
@Interceptor
public class LoggingInterceptor {
    @AroundInvoke
    public Object LogMethod(InvocationContext ctx) throws Exception {
        long startTime = System.currentTimeMillis();
        String method = ctx.getMethod().getDeclaringClass().getSimpleName()
                + "." + ctx.getMethod().getName();

        System.out.println("[LOG] -> Entering: " + method);

        try {
            Object result = ctx.proceed();
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("[LOG] <- Exiting: " + method + " (Execution: " + duration + " ms)");

            return result;
        } catch (Exception e) {
            System.out.println("[LOG] !! Exception in " + method + ": " + e.getMessage());
            throw e;
        }
    }
}