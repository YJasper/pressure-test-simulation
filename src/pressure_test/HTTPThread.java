package pressure_test;

public class HTTPThread extends Thread {
    String url;
    String para;
    String type;
    long rtt;
    long threshold = 10000; // 如果大于临界值判断为超时
    boolean isTimeout = false;

    public HTTPThread(String url, String para) {
        super();
        this.url = url;
        this.para = para;
    }

    @Override
    public void run() {
        // demo:代理访问
        String sr = null;
        long t1 = System.currentTimeMillis();
        if (type.equals("post"))
            sr = HttpRequestUtil.sendPost(url, para, true);
        if (type.equals("get"))
            sr = HttpRequestUtil.sendGet(sr, para);
        System.out.println(sr);
        long t2 = System.currentTimeMillis();
        rtt = t2 - t1;
        if (rtt > threshold)
            isTimeout = true;
    }

}
