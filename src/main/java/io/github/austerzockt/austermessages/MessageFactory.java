package io.github.austerzockt.austermessages;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MessageFactory {
    public static <T> T getMessage(Class<T> tClass) {
        if (tClass.isAnnotationPresent(AusterMessage.class)) {
            Object o = Proxy.newProxyInstance(
                    tClass.getClassLoader(),
                    new Class[]{tClass},
                    (InvocationHandler) (object, method, objects) -> {
                        if (method.isAnnotationPresent(Message.class)) {
                            Message m = method.getAnnotation(Message.class);
                            String message = m.message();
                            Parameter[] parameters = method.getParameters();
                            //Iterate through all Parameters and replace them in the string
                            for (int i = 0; i < parameters.length; i++) {
                                Parameter p = parameters[i];
                                //Check if the class passed as the parameter is either the io.github.austerzockt.austermessages.PlaceholderSupport functional interface or a class implementing it
                                if ( p.getType().equals(PlaceholderSupport.class) || Arrays.stream(p.getType().getInterfaces()).collect(Collectors.toList()).contains(PlaceholderSupport.class)) {
                                    PlaceholderSupport placeholderSupport = (PlaceholderSupport) objects[i];
                                    message = message.replace("$" + (i+1), placeholderSupport.placeholder());
                                }

                            } ;
                            return message;
                        }

                        return object;
                    });

            return (T) o;
         }
return null;
    }
    
}
