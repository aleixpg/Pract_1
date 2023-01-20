package Dynamic;

/**
 * http://javahowto.blogspot.com.es/2011/12/java-dynamic-proxy-example.html
 *
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestInvocationHandler implements InvocationHandler {
    private Object testImpl;

    public TestInvocationHandler(Object impl) {
        this.testImpl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object invocationResult = null;
        try
        {
            System.out.println("");
            System.out.println("Before method " + method.getName() + ":");
            invocationResult = method.invoke(this.testImpl, args);
            System.out.println("After method " + method.getName() + ":");
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }
}