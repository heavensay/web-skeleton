package com.www.skeleton.web.controller.hello;

import com.helix.dict.annotation.Dict;
import com.www.skeleton.service.hello.data.AnimalEnum;
import com.www.skeleton.service.hello.data.CountryEnum;
import com.www.skeleton.service.hello.data.DigitEnum;
import lombok.Data;

/**
 * @author ljy
 * @date 2019/7/4 9:47
 */
@Data
public class HelloBean {

    private String name;

    private String gentleman;

    @Dict(code = "gender",valueColumn = "gentleman")
    private String gentlemanText;

    private String country;

    @Dict(type = CountryEnum.class,valueColumn = "country")
    private String countryText;

    private Integer digit;

    @Dict(type = DigitEnum.class,valueColumn = "digit")
    private String digitText;

    private String animalName;

    @Dict(type = AnimalEnum.class,valueColumn = "animalName")
    private String animalNameText;

}
