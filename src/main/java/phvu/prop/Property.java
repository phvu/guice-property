package phvu.prop;

public enum Property {

    SERVER("MY_SERVER", "my.server", "localhost", "The server"),
    PORT("", "my.port", "1234", "The port");

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

    public String getEnvironmentVar() {
        return environmentVar;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getValue() {
        return System.getenv().getOrDefault(this.environmentVar,
                System.getProperty(this.propertyKey, this.defaultValue));
    }

}
