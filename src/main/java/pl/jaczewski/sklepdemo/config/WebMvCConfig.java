//package pl.jaczewski.sklepdemo.config;
//
//
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import pl.jaczewski.sklepdemo.util.StringToItemConverter;
//
//@Component
//@EnableSpringDataWebSupport
//public class WebMvCConfig extends WebMvcConfigurationSupport {
//
//    @Override
//    protected void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToItemConverter());
//        super.addFormatters(registry);
//    }
//
//    public StringToItemConverter stringToItemConverter() {
//        return new StringToItemConverter();
//    }
//}
