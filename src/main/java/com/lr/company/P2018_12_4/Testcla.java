package com.company.P2018_12_4;

import java.util.Objects;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年12月04日
 */
public class Testcla {
    private String a;
    private String b;

    public Testcla(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
/*
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Testcla)) {
            return false;
        }
        Testcla testcla = (Testcla) o;
        return Objects.equals(getA(), testcla.getA()) && Objects.equals(getB(), testcla.getB());
    }


    @Override
    public int hashCode() {

        return Objects.hash(getA(), getB());
    }*/

}
