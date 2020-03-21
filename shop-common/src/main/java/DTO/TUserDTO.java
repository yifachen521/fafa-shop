package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 炜哥哥  用于 实现服务与服务之间的数据传输 所以要实现序列化的接口
 * @date 2020/3/18 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TUserDTO implements Serializable {
    private long id;
    private String uname;
    private String password;
    private String phone;
    private String email;
    private long flag;
    private java.sql.Timestamp createTime;
    private long createUser;
    private java.sql.Timestamp updateTime;
    private long updateUser;
}
