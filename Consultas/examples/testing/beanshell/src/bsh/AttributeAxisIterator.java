/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package bsh;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AttributeAxisIterator implements Iterator {

    private Object currObj;
    private int position;
    private List attrs = new ArrayList();

    public AttributeAxisIterator(Node contextNode) {
        this.position = 0;
        Field[] fields = contextNode.getClass().getFields();
        for (int i=0; i<fields.length; i++ ) {
            if (isAttributeAccessor(fields[i])) {
                try {
                    Attribute a = new Attribute(contextNode, fields[i].getName(), fields[i].get(contextNode).toString());
                    attrs.add(a);
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                }

            }
        }
        this.currObj = getNextAttribute();
    }

    public Object next() {
        if (currObj == null) {
            throw new IndexOutOfBoundsException();
        }
        Object ret = currObj;
        currObj = getNextAttribute();
        position++;
        return ret;
    }

    public boolean hasNext() {
        return currObj != null;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private Attribute getNextAttribute() {
        if (position == attrs.size()) {
            return null;
        }
        return (Attribute)attrs.get(position);
    }

    protected boolean isAttributeAccessor(Field field) {
        return field.getType().equals(String.class);
    }
}
