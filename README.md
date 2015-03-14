warhuhn-spark-template-jtwig
============================

warhuhn-spark-template-jtwig is a Jtwig implementation for the Java Spark framework.

It currently only supports a Map<String, Object> as a model for the view and templates are to be required to be 
inside a /templates folder in the Java classpath.

When extending templates, the paths are relative to the current template.

*New* Maven (via jitpack.io)
----------------------------

You can now install this Template Engine via Maven (thanks to Jitpack.io).

Just add the following Dependency to your ```pom.xml```:

``` xml
<dependency>
    <groupId>com.github.warhuhn</groupId>
    <artifactId>warhuhn-spark-template-jtwig</artifactId>
    <version>0.3</version>
</dependency>
```

Examples
--------

### JtwigTemplateExample.java
``` java
public class JtwigTemplateExample {
    public static void main(String[] args) {
        final Map<String, Object> modelMap = new HashMap<>();
        
        modelMap.put("gender", "f");
        modelMap.put("name", "Merkel");
        
        get("/hello", (rq, rs) -> new ModelAndView(modelMap, "hello.twig"), new JtwigTemplateEngine());
    }
}
```

### layout.twig
``` twig
{# layout.twig #}
{#
 # ... LICENSE ...
 #}
<!DOCTYPE HTML>
<html>
    <head>
        <title>JtwigTemplateEngine Example</title>
    </head>
    <body>
        <h1>Welcome everybody!</h1>
        <div>
            {% block additional %}
                You won't see this because the inherited hello.twig Template overwrites it!
            {% endblock %}
        </div>
    </body>
</html>
```

### hello.twig
``` twig
{# hello.twig #}
{#
 # ... LICENSE ...
 #}

{# extend layout.twig for our basic layout #}
{% extends "layout.twig" %}

{# Example Twig template file. #}

{% block additional %}

Hello {% if model.gender == "m" -%}
    Mr.
{%- elseif model.gender == "f" -%}
    Mrs.
{%- endif %}
 <b>{{ model.name }}</b>!

{% endblock %}
```


### Output
``` html
<!DOCTYPE HTML>
<html>
    <head>
        <title>JtwigTemplateEngine Example</title>
    </head>
    <body>
        <h1>Welcome everybody!</h1>
        <div>

            Hello Mrs. <b>Merkel</b>!

        </div>
    </body>
</html>
```