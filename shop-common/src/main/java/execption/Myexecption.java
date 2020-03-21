package execption;

import lombok.Getter;

/**
 * @author 炜哥哥   定义用户登录的异常
 * @date 2020/3/18 22:12
 */
@Getter
public class Myexecption extends RuntimeException{
    private Integer code;

    public Myexecption(String message,Integer code) {
        super(message);
        this.code=code;
    }

    public Myexecption() {
    }
}
