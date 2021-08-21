package io.github.austerzockt.austermessages;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MessageFactory {
    private final HashMap<Class<?>, Function<Object, String>> resolvers = new HashMap<>();
    public <T> T getMessage(Class<T> tClass) {
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
                                if (p.isAnnotationPresent(Placeholder.class)) {
                                    Placeholder anno = p.getAnnotation(Placeholder.class);
                                    message = message.replaceAll("\\$" + anno.name() + "\\$", resolvers.get(p.getType()).apply(objects[i]));
                                }
                                }
                            return message;
                        }

                        return object;
                    });

            return (T) o;
         }
return null;
    }
    public <T> void  registerResolver(Class<? extends T> clazz, Function<T , String> resolver) {
        resolvers.put(clazz, (Function<Object, String>) resolver);

    }
    
}
