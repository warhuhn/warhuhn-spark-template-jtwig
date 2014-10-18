package de.warhuhn.java.web.spark;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by suigintou on 18.10.2014.
 */
public class JtwigTemplateExample {
    public static void main(String[] args) {
        final Map<String, Object> modelMap = new HashMap<>();

        modelMap.put("gender", "f");
        modelMap.put("name", "Merkel");

        get("/hello", (rq, rs) -> new ModelAndView(modelMap, "hello.twig"), new JtwigTemplateEngine());
    }
}
