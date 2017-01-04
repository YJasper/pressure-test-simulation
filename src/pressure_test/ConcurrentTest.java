package pressure_test;

public class ConcurrentTest {
    public static void main(String[] args) {
        int n = 100; // 并发请求的数量
        String url = "http://localhost:8080/test";
        String para = "para1=A&para2=B&para3=C";

        HTTPThread[] t = new HTTPThread[n];
        for (int i = 0; i < n; i++) {
            t[i] = new HTTPThread(url, para);
            t[i].start();
        }

        // 统计RTT时间来反应服务器性能
        int sum1 = 0; // 超时请求的数量
        int sum2 = 0; // 总的RTT时间
        for (int i = 0; i < n; i++) {
            if (t[i].isTimeout == true)
                sum1++;
            sum2 += t[i].rtt;
        }

        System.out.println("超时的请求个数为" + sum1);
        System.out.println("平均的RTT时间为" + sum2 / n);
    }
}
