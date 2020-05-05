package com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// Models 中无论如何只有 User 这一个实体类。不是加上 @ApiModel 就可以加入 Swagger-UI 的 Models 中的，必须要在 controller 层中关联这个实体类。
@ApiModel(value = "用户", description = "这里是描述")
public class User {
    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(notes = "用户名")
    private String name;
    @ApiModelProperty(notes = "年龄")
    private Integer age;

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
