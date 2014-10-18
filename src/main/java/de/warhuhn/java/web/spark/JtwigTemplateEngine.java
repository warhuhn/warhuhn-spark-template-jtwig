package de.warhuhn.java.web.spark;

import com.lyncode.jtwig.JtwigContext;
import com.lyncode.jtwig.JtwigTemplate;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Created by suigintou on 18.10.2014.
 */
public class JtwigTemplateEngine extends TemplateEngine {

    // TODO: Needs a way to further configure Jtwig

    @Override
    public String render(ModelAndView modelAndView) {
        JtwigTemplate template = new JtwigTemplate(new ClasspathJtwigResource("templates/" + modelAndView.getViewName()));

        String result;

        try {
            // TODO: will break if the model is not a HashMap<String, Object>
            result = template.output(new JtwigContext((com.lyncode.jtwig.JtwigModelMap) modelAndView.getModel()));
        } catch (ParseException | CompileException | RenderException e) {
            throw new RuntimeIOException(e);
        }

        return result;
    }
}
