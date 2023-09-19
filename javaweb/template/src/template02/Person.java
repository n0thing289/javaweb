package template02;
/**
 * 由于Teacher和Student类的重复代码多，可以进行复用
 * 而且只有一个方法的实现不同，
 * 因此可以使用模板方法设计模式
 *
 * 将共同拥有的方法封装到一个类（抽象类）中
 * 将特别的实现改成抽象方法
 * 让那两个类继承这个抽象类并实现dosome方法，就做到了更好的oop原则
 * */
public abstract class Person {
    public void day() {
        qichuang();
        xishu();
        chizaocan();
        dosome();
        chiwanfan();
        shuijiao();
    }

    public void qichuang() {
        System.out.println("起床");
    }

    public void xishu() {
        System.out.println("洗漱");
    }

    public void chizaocan() {
        System.out.println("吃早餐");
    }

    public abstract void dosome();

    public void chiwanfan() {
        System.out.println("吃完饭");
    }

    public void shuijiao() {
        System.out.println("睡觉");
    }
}
