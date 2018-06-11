package com.test;

import com.test.webroute.WebRouteImplementation;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) {

        Class route = WebRouteImplementation.class;
        Method[] methods = route.getMethods();
        boolean invalidPath = true;

        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof WebRoute) {
                    WebRoute customAnnotation = (WebRoute) annotation;
                    if (customAnnotation.value().equals(request.getRequestURI().getRawPath())) {
                        try {
                            method.invoke(route.newInstance(), request);
                            invalidPath = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (invalidPath) {
            handleInvalidPath(request, route);
        }
    }

    private void handleInvalidPath(HttpExchange request, Class route) {
        try {
            Method invalidPage = route.getDeclaredMethod("invalidPage", HttpExchange.class);
            try {
                invalidPage.invoke(route.newInstance(), request);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
