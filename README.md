warhuhn-spark-template-jtwig
============================

warhuhn-spark-template-jtwig is a Jtwig implementation for the Java Spark framework.

It currently only supports a Map<String, Object> as a model for the view and templates are to be required to be 
inside a /templates folder in the Java classpath.

Examples
--------

### JtwigTemplateExample.java
> final Map<String, Object> modelMap = new HashMap<>();
>
> modelMap.put("gender", "f");
> modelMap.put("name", "Merkel");
>
> get("/hello", (rq, rs) -> new ModelAndView(modelMap, "hello.twig"), new JtwigTemplateEngine());

### hello.twig
>  Hello {% if model.gender == "m" %}
>      Mr.
>  {% elseif model.gender == "f" %}
>      Mrs.
>  {% endif %} {{ model.name }}!