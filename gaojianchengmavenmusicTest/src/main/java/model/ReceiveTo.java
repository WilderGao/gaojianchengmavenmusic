package model;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public class ReceiveTo<T> {
    private int method;
    private T data;

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
