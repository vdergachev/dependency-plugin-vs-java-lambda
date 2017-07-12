package com.abc.def;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vdergachev on 12.07.17.
 */
@RestController
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "bug-free-software";
    }

    private static void doNothing(final Object object) {

    }

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
        return container -> {
            if (container instanceof JettyEmbeddedServletContainerFactory) {
                ((JettyEmbeddedServletContainerFactory) container).addServerCustomizers(
                        Application::doNothing);
            }
        };
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(final ConfigurableEmbeddedServletContainer container) {
//                if (!(container instanceof JettyEmbeddedServletContainerFactory)) {
//                    return;
//                }
//                final JettyEmbeddedServletContainerFactory factory = ((JettyEmbeddedServletContainerFactory) container);
//                factory.addServerCustomizers(new JettyServerCustomizer() {
//                    @Override
//                    public void customize(final Server server) {
//                        doNothing(server.getState());
//                    }
//                });
//            }
//        };
//    }
}
