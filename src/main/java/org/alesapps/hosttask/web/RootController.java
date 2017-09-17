package org.alesapps.hosttask.web;

import org.alesapps.hosttask.db.DBScripts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @PostMapping("/")
    public ModelAndView getScript(@RequestParam(name = "dbschema", required = false) String dbschema,
                                  @RequestParam(name = "dbtable", required = false) String dbtable,
                                  HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        String scriptString = null;
        if (request.getParameter("create") != null) {
            scriptString = DBScripts.getCreate(dbschema, dbtable);
        } else if (request.getParameter("select") != null) {
            scriptString = DBScripts.getSelect(dbschema, dbtable);
        } else if (request.getParameter("update") != null) {
            scriptString = DBScripts.getUpdate(dbschema, dbtable);
        }
        modelAndView.addObject("dbscript", scriptString);
        modelAndView.addObject("dbschema", dbschema);
        modelAndView.addObject("dbtable", dbtable);
        return modelAndView;
    }
}
