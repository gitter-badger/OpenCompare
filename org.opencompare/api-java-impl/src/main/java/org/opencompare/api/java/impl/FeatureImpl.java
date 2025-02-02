package org.opencompare.api.java.impl;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.util.PCMVisitor;
import pcm.AbstractFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by gbecan on 08/10/14.
 */
public class FeatureImpl extends AbstractFeatureImpl implements Feature {

    private pcm.Feature kFeature;

    public FeatureImpl(pcm.Feature kFeature) {
        super(kFeature);
        this.kFeature = kFeature;
    }

    public pcm.Feature getkFeature() {
        return kFeature;
    }

    @Override
    public String getName() {
        return kFeature.getName();
    }

    @Override
    public void setName(String s) {
        kFeature.setName(s);
    }

    @Override
    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<Cell>();
        for (pcm.Cell kCell : kFeature.getCells()) {
            cells.add(new CellImpl(kCell));
        }
        return cells;
    }

    @Override
    public void accept(PCMVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureImpl feature = (FeatureImpl) o;

        if (this.getName() == null) {
            return feature.getName() == null;
        }

        if (!this.getName().equals(feature.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return "Feature(" + getName() + ")";
    }
}
