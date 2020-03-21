package com.song.pojo;

//这里 只做与数据库的映射
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name="t_user")
@ToString
@Data
public class TUser implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String uname;
  private String password;
  private String phone;
  private String email;
  private Long flag;
  private java.sql.Timestamp createTime;
  private Long createUser;
  private java.sql.Timestamp updateTime;
  private Long updateUser;
}
