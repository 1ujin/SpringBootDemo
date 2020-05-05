package com.pojo;

import io.swagger.annotations.ApiModel;

// 此处注解无效，不是加上 @ApiModel 就可以加入 Swagger-UI 的 Models 中的，必须要在 controller 层中关联这个实体类。
@ApiModel(value = "Greeting 类")
public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}