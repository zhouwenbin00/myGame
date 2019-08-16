import com.demo.data.DataCenter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @Auther: zhouwenbin @Date: 2019/7/17 20:48 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            // 字符集判断
            Validate.isTrue(
                    StringUtils.equalsIgnoreCase(SystemUtils.FILE_ENCODING, "utf-8"),
                    "字符编码必须是utf-8");

            // 加载配置
            DataCenter.getInstance().serverConfig.init();

        } catch (Exception e) {
            LOGGER.error("启动失败!", e);
            System.exit(1);
        }
    }
}
