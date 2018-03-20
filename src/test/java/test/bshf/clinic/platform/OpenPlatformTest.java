package test.bshf.clinic.platform;

import com.alibaba.fastjson.JSON;
import com.billy.demo.open.util.HttpClientUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-16 15:12
 */
public class OpenPlatformTest {

    private static final Logger LOG = LoggerFactory.getLogger(OpenPlatformTest.class);


    @Test
    public void testCreate() {
        String accessToken =
                "7_h1SAX_ufm3b2Edgfin6UK7vsNViLjnBmgQ_v1a4KBA9iuMPZlLJNiUR04w5Yxxft0JiT44pBRdedg4Ss1ZbJmALry-vCu_1q9Cfg51CvNCFSzhIWjHp_CC55bh3VWmUVIAHz1juyf8jf9AhyXMUcALDSHP";
        String appId = "wxc2e3af2933cc2c81";
        String url = "https://api.weixin.qq.com/cgi-bin/open/create?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        String s = HttpClientUtil.doPostString(url, JSON.toJSONString(params));
        //  {"open_appid":"wx03dd92428764e44b","errcode":0,"errmsg":"ok"}
        LOG.debug("result: " + s);
    }

    @Test
    public void testBind() { String accessToken =
            "7_h1SAX_ufm3b2Edgfin6UK7vsNViLjnBmgQ_v1a4KBA9iuMPZlLJNiUR04w5Yxxft0JiT44pBRdedg4Ss1ZbJmALry-vCu_1q9Cfg51CvNCFSzhIWjHp_CC55bh3VWmUVIAHz1juyf8jf9AhyXMUcALDSHP";
        String appId = "wxc2e3af2933cc2c81";
        String url = "https://api.weixin.qq.com/cgi-bin/open/bind?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("open_appid", "wx03dd92428764e44b");
        String s = HttpClientUtil.doPostString(url, JSON.toJSONString(params));
        LOG.debug("result: " + s);
    }
}
