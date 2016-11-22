package ru.sbt;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        //getting only public methods
        Method[] fromMethods = from.getClass().getMethods();
        Method[] toMethods = to.getClass().getMethods();

        for (Method fm : fromMethods) {
            if (isGetter(fm)) {
                for (Method tm : toMethods) {
                    if (isSetter(tm)) {
                        if (fm.getReturnType().getSuperclass() == (tm.getParameterTypes()[0]) ||
                                fm.getReturnType() == (tm.getParameterTypes()[0])) {
                            //System.out.println("setter: " + tm);
                            //System.out.println("getter: " + fm);
                            try {
                                tm.invoke(to, fm.invoke(from));
                            } catch (IllegalAccessException e) {
                                System.err.println("Method object is enforcing Java language " +
                                        "access control and the underlying method is inaccessible");
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                System.err.println("the underlying method throws an exception");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }

    
}
