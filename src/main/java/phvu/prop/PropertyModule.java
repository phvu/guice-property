package phvu.prop;

import com.google.inject.AbstractModule;

import java.util.stream.Stream;

public class PropertyModule extends AbstractModule {

    @Override
    protected void configure() {
        Stream.of(Property.values()).forEach(p ->
                bindConstant().annotatedWith(new PropImpl(p)).to(p.getValue()));
    }
}
