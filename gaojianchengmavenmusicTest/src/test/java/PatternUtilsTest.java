import org.junit.Test;
import utils.PatternUtils;

import java.io.IOException;

/**
 * @author:Wilder Gao
 * @time:2018/3/1
 * @Discription：
 */

public class PatternUtilsTest {
    @Test
    public void UrlTest() throws IOException {
        String singerName = "周杰伦";
        System.out.println(PatternUtils.getSingerPicUrl(singerName));
    }
}
