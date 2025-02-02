package org.opencompare.api.java.impl.value;

import org.opencompare.api.java.impl.ValueImpl;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.StringValue;

/**
 * Created by gbecan on 09/10/14.
 */
public class StringValueImpl extends ValueImpl implements StringValue {

    private pcm.StringValue kStringValue;

    public StringValueImpl(pcm.StringValue kStringValue) {
        super(kStringValue);
        this.kStringValue = kStringValue;
    }

    public pcm.StringValue getkStringValue() {
        return kStringValue;
    }

    @Override
    public String getValue() {
        return kStringValue.getValue();
    }

    @Override
    public void setValue(String value) {
        kStringValue.setValue(value);
    }

    @Override
    public void accept(PCMVisitor visitor) {
        visitor.visit(this);
    }
}
