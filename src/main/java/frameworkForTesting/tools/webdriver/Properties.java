package frameworkForTesting.tools.webdriver;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("properties")
public interface Properties {

    Properties props = PropertyLoader.newInstance().populate(Properties.class);

    @Property("started.page")
    String login();

    static String getUrl() {
        return props.login();
    }
}

