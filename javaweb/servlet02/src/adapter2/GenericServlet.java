package adapter2;

import adapter.MyInterface;

public abstract class GenericServlet implements MyInterface {

    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

    @Override
    public void m3() {

    }

    @Override
    public void m4() {

    }
    /**
     * 因为这个core最主要去使用
     * */
    @Override
    public abstract void core();
}
