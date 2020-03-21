package com.patternsDesign.factory;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class HumanFactor extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human= c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) human;
    }

    public static void main(String[] args) {
        new HumanFactor().createHuman(HumanA.class);
    }
}
