package com.august.objectPool;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年08月20日
 */
public class Test {
    public static void main(String[] args) {
        SemTest semTest = new SemTest(5);
        ObjectPool objectPool = new ObjectPool(1, semTest);

        SemTest st = (SemTest) objectPool.execute(tt -> {
            System.out.println("1:" + tt.toString());
            return tt;
        });
        st.add(1);
    }
}
