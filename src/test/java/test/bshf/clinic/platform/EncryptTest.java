package test.bshf.clinic.platform;

import com.billy.demo.open.util.wx.WXBizMsgCrypt;
import org.junit.Test;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-15 19:55
 */
public class EncryptTest {
    @Test
    public void testDecrypt() {
        String encryptedStr = "<xml>\n" +
                "    <AppId><![CDATA[wx95bab1ed8125031a]]></AppId>\n" +
                "    <Encrypt><![CDATA[50HkNeDPEYGVdnYG44REsJeqGA8O/2N2HKkKy33bH3/Qm+0aF/hTVPU9BeWRT7TMGTFkb5Nnhx8tRGNWw09B3Jt3a86uzFPBBWM7PaZOYvx6bAHTZlFCpmbZM2W7QFpEIjxn108A0MO9cBuf15FqUxb4Z2C5xKcYgCS9/KBAuCq/cXYai6or2YGU70MKp4nx5ai+90430Y4ZPcj2fh1Ymb6G6PV6SDvWh79/aFl3NN5K0FjARtKTpwwB+u1z7E/Rp7vnbM6S4Si6iKdpqPanUUUBTwX8kv3tvWMLQotJmq/uMyZ4SJhHTnkfDO8F9J+CQBbt8LdMmmhgtEOQE06fnil7uLw8Ok3fhvv00q3CRuzAC5FHNqlnCvm5EfHN7qOdGMVaX5CNri3/cQ7A2qlFLT8lXx5JU+K8ZuXWbDF58XvhMHMQe2mEd3EZVMzvifU9TT+E33EcYPHStLz5RVlkDA==]]></Encrypt>\n" +
                "</xml>";

        String msgSignature = "41978225fd32311c36c1584acbd6bf822a3ba449";
        String timestamp = "sss";
        String nonce = "sdf";
        String result2 = WXBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, encryptedStr);
    }

    @Test
    public void test2() {
        String encryptedStr = "<xml>\n" +
                "    <ToUserName><![CDATA[gh_476fde26ddec]]></ToUserName>\n" +
                "    <Encrypt><![CDATA[jTQ+2I2tvTmWtd7e/rHyq2xtKDXN8zclEtjCJfrxwOJIMSD+HK4YMOeVIbYBQKJtlwiROOIJBYxLuhVlBvF0oWRknIzWqt1zY6V0A46fr3S1s6SrIlb+zYtFoCrVGzAWJ10USobWIZ9JuMQCzWTMU9YK4tfIcMxah8qneQgL7DPNggUoX16AjZGusT5grP2BuDW+YGfUatgHy1hPG06nZteBxRfqW7FPQ+weIXsnLaULGnkpjZATDMgpdda0oGZY9enXBzB1P0qG2dpIXbnud5PV2IQXwmTznkOqM3od7hZc8oYzc1ZBoNtmC+DrKueKRa2RYjRcuMQ4ikQRZjle3S67X11Pwcikn+R9ylob1FLZ9gW1A9l6Yp/YUtd/TAaBeOQfk0ArPzZ5XTCbAQjpHHnURrmeliSxie4ELbJhaA0Z/9WfiM5Fw3hhYGhLdQPyPgR6fVRZIjYFuwL/YQtSOg==]]></Encrypt>\n" +
                "</xml>";

        String nonce = "610725580";
        String msgSignature = "c89d853418ab7a0642447ec796b3a6378ae97334";
        String timestamp = "1521127751";
        String s = WXBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, encryptedStr);
        System.out.println(s);
    }
}
