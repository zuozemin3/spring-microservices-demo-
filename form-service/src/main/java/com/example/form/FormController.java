package com.example.form;

import com.example.form.domain.Form;
import com.example.form.service.impl.FormServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class FormController {
    private static final Logger LOG = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private Config config;

    @Autowired
    private FormServiceImpl formService;

    @ApiOperation(value = "获取应用DB链接url")
    @RequestMapping(value = "/dburl", method = RequestMethod.GET)
    @ResponseBody
    String home() {
        return config.getDbUrl();
    }

    @ApiOperation(value = "查询指定userId用户的表单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户的UserId", required = true, dataType = "string", paramType = "path", defaultValue="233422")
    })
    @RequestMapping(value = "/forms/{userId}", method = RequestMethod.GET)
    @ResponseBody
    List<Form> findFormsByUserId(@PathVariable String userId) {
        LOG.info("start to find");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return formService.findByUserIdAndStatus(Long.valueOf(userId)).stream().map(formUserDTO -> formUserDTO.getForm()).collect
                (Collectors.toList());
    }
}
