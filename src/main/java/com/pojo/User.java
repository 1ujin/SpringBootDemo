package com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.List;

// Models 中无论如何只有 User 这一个实体类。不是加上 @ApiModel 就可以加入 Swagger-UI 的 Models 中的，必须要在 controller 层中关联这个实体类。
@ApiModel(value = "用户", description = "这里是描述")
public class User implements Serializable {
    private static final long serialVersionUID = -992421524791416992L;
    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(notes = "用户名")
    // 国际化 可以添加多个相同注解(其中参数内容不同)，默认分组为Default，可以自行添加接口作为分组
    @NotBlank(message = "{user.name.NotBlank}", groups = Default.class)
    // 同时满足所有Size
    @Size.List({@Size(min = 1, message = "用户名长度大于等于1"), @Size(min = 3, message = "用户名长度大于等于3")})
    private String name;
    @ApiModelProperty(notes = "年龄")
    @Min(value = 0L, message = "年龄必须大于0")
    private Integer age;
    private List<MultipartFile> files;

    /**
     * <a href="https://blog.csdn.net/mzjnumber1/article/details/106545787">{@code Jackson}的反序列化需要无参构造函数</a>
     * <p>
     * {@code Mybatis}反射的{@code Class.forName("User").newInstance();}需要对应的类提供一个无参构造函数。
     */
    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(Long id, String name, Integer age, List<MultipartFile> files) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public List<MultipartFile> getFile() {
        return files;
    }

    public void setFile(List<MultipartFile> files) {
        this.files = files;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
