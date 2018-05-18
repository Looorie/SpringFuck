package me.looorielovbb.springfuck.controller;

import me.looorielovbb.springfuck.dao.DeveloperDao;
import me.looorielovbb.springfuck.model.CommonModel;
import me.looorielovbb.springfuck.model.DeveloperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperDao developerDao;

    public DeveloperController(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @Autowired


    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String Hello() {
        return "hello";
    }

    @RequestMapping(value = "api/developers", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getAllDevelopers() {
        List<DeveloperModel> developerList = developerDao.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (developerList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setObject(developerList);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "api/developer", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id) {
        //自动匹配参数
        System.out.println("developerId=" + id);
        DeveloperModel developerModel = developerDao.getDeveloper(id);
        CommonModel commonModel = new CommonModel();
        if (developerModel != null) {
            commonModel.setSuccess();
            commonModel.setObject(developerModel);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(String name, String site, String avatar) {
        //自动匹配参数
        System.out.println("name=" + name);
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);
        developerModel.setAvatar(avatar);
        CommonModel commonModel = new CommonModel();
        if (developerDao.addDeveloper(developerModel)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(String id, String name) {
        //自动匹配参数
        System.out.println("name=" + name);
        CommonModel commonModel = new CommonModel();
        if (developerDao.updateDeveloper(id, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    //Spring MVC 不支持 put,delete方法实现传参,这里用到了PathVariable
    @RequestMapping(value = "/api/developer/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonModel deteleDeveloper(@PathVariable("id") String id) {
        //id 对应 {id}
        System.out.println("developerId=" + id);
        CommonModel commonModel = new CommonModel();
        if (developerDao.deleteDeveloper(id)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

}
