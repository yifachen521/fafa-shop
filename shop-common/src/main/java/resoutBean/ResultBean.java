package resoutBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 炜哥哥  将需要返回的信息统一封装进resultBean返回
 * @date 2020/3/18 17:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean {
    private Integer resultCode;//返回数据的状态码
    private String resultMsg;//返回数据的状态信息
    private  Object resultData;//需要返回的数据
}
