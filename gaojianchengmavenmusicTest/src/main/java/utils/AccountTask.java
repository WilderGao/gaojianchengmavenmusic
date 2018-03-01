package utils;

import model.SessionMap;

/**
 * @author:Wilder Gao
 * @time:2018/3/1
 * @Discription：创建任务，验证码超过60秒将视为无效
 */
public class AccountTask implements Runnable {
    private String userEmail;
    public AccountTask(String userEmail){
        this.userEmail = userEmail;
    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end ;
        while(true){
            end = System.currentTimeMillis();
            if ((end - start)>1000*60*2) {
                SessionMap.emailMap.remove(userEmail);
                break;
            }
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
