package com.august.guardedSuspension;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月27日
 */
public class Test {
    public static void main(String[] args) {
        String s = null;
        GuardedObject<String> g = GuardedObject.get(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
                g.onChange(new String("1"));
            }
        }).start();

        System.out.println(g.get(Test::get));
    }

    private static boolean get(String s){
        return s != null;
    }
}
