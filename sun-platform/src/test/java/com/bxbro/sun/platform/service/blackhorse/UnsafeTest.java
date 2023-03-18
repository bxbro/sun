package com.bxbro.sun.platform.service.blackhorse;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过反射获取 Unsafe对象
 *
 * @author: dong
 * @date: 2023/3/15 18:41
 * @since: 1.0
 */
public class UnsafeTest {

    public static void main(String[] args) {
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);

        Unsafe unsafe1 = UnsafeAccessor.getUnsafe();
        System.out.println(unsafe1);
        
    }
}
class UnsafeAccessor {
    private static Unsafe UNSAFE = null;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Unsafe getUnsafe() {
        return UNSAFE;
    }
}
