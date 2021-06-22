/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package bsh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author daniels
 */
public class Attribute {

    private Node parent;
    private String name;
    private String value;

    public Attribute(Node parent, String name, String value) {
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public String toString() {
        return "Name: " + name + "; value = " + getValue();
    }
}
