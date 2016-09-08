package phvu.prop;

import java.lang.annotation.Annotation;

class PropImpl implements Prop {

    private final Property value;

    PropImpl(Property value) {
        this.value = value;
    }

    @Override
    public Property value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prop)) {
            return false;
        }

        Prop other = (Prop) obj;
        return value.equals(other.value());
    }

    @Override
    public int hashCode() {
        // This is specified in java.lang.Annotation.
        return (127 * "value".hashCode()) ^ value.hashCode();
    }

    @Override
    public String toString() {
        return "@" + Prop.class.getName() + "(value=" + value + ")";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Prop.class;
    }
}
