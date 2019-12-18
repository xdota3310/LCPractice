package com.december.patternsDesign.abstract_factor;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月16日
 */
public class WinFactory extends AbstractFactory {

    @Override
    void doFile() {
        new WinFile().doFile();
    }

    @Override
    void doSocket() {
        new WinSocket().doSocket();
    }
}
