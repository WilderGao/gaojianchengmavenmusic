package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public class Feedback<T> {
    private int status ;
    private T data ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

}
