package phvu.prop;

public enum Property {

    FOO("FOO_SERVER", "foo.server", "DefaultFooServer", "Defines the foo server"),
    PORT("", "foo.port", "1234", "The port");

    private String environmentVar;
    private String propertyKey;
    private String description;
    private String defaultValue;

    Property(String environmentVar, String propertyKey, String defaultValue, String description) {
        this.environmentVar = environmentVar;
        this.propertyKey = propertyKey;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return System.getenv().getOrDefault(this.environmentVar,
                System.getProperty(this.propertyKey, this.defaultValue));
    }

}
