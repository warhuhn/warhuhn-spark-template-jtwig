/*
 * Copyright 2014 Jesko Karwasz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.warhuhn.java.web.spark;

import com.lyncode.jtwig.JtwigModelMap;
import com.lyncode.jtwig.JtwigTemplate;
import com.lyncode.jtwig.configuration.JtwigConfiguration;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * TemplateEngine for Spark based on Jtwig.
 */
public class JtwigTemplateEngine extends TemplateEngine {

    private String templateRoot = "templates/";

    private JtwigConfiguration jtwigConfiguration;

    /**
     * Default Constructor
     */
    public JtwigTemplateEngine() {
        this.jtwigConfiguration = new JtwigConfiguration();
    }

    /**
     * Constructor to add your own JtwigConfiguration
     *
     * @param jtwigConfiguration Your customized JtwigConfiguration
     */
    public JtwigTemplateEngine(JtwigConfiguration jtwigConfiguration) {
        this.jtwigConfiguration = jtwigConfiguration;
    }

    /**
     * Constructor to add your own JtwigConfiguration and own templateRoot for the templates.
     *
     * @param templateRoot       Root path in ClassPath pointing to the templates
     * @param jtwigConfiguration Your customized JtwigConfiguration
     */
    public JtwigTemplateEngine(String templateRoot, JtwigConfiguration jtwigConfiguration) {
        this.templateRoot = templateRoot;
        this.jtwigConfiguration = jtwigConfiguration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        ClasspathJtwigResource templateResource = new ClasspathJtwigResource(this.templateRoot + modelAndView.getViewName());

        JtwigTemplate template = new JtwigTemplate(templateResource, jtwigConfiguration);

        String result;

        try {
            // TODO: will break if the model is not a HashMap<String, Object>
            result = template.output(new JtwigModelMap().add((java.util.Map<String, Object>) modelAndView.getModel()));
        } catch (ParseException | CompileException | RenderException e) {
            throw new RuntimeIOException(e);
        }

        return result;
    }
}
