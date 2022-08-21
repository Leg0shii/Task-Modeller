package de.legoshi.taskmodeller.util;

import de.legoshi.taskmodeller.gui.symbol.Drawable;
import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.AbstractTask;
import de.legoshi.taskmodeller.gui.symbol.item.ctt.SimpleTask;
import de.legoshi.taskmodeller.gui.symbol.item.misc.GroupingNode;
import de.legoshi.taskmodeller.gui.symbol.item.misc.TextSymbol;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CCircle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CPentagon;
import de.legoshi.taskmodeller.gui.symbol.item.standard.CTriangle;
import de.legoshi.taskmodeller.gui.symbol.item.standard.Square;
import de.legoshi.taskmodeller.gui.windows.Workplace;

public class NodesHelper {

    public static Drawable getDuplicate(Workplace workplace, Drawable drawable) {
        if (drawable instanceof Square) return prepareDrawable(workplace, Square.generateShape());
        else if (drawable instanceof CTriangle) return prepareDrawable(workplace, CTriangle.generateShape());
        else if (drawable instanceof CCircle) return prepareDrawable(workplace, CCircle.generateShape());
        else if (drawable instanceof CPentagon) return prepareDrawable(workplace, CPentagon.generateShape());
        else if (drawable instanceof TextSymbol) return prepareDrawable(workplace, TextSymbol.generateShape());
        else if (drawable instanceof SimpleTask) return prepareDrawable(workplace, SimpleTask.generateShape());
        else if (drawable instanceof AbstractTask) return prepareDrawable(workplace, AbstractTask.generateShape());
        else if (drawable instanceof GroupingNode) return prepareDrawable(workplace, GroupingNode.generateShape());
        return null;
    }

    public static Drawable prepareDrawable(Workplace workplace, Drawable drawable) {
        drawable.setRepresentative(false);
        drawable.registerEvents(workplace);
        return drawable;
    }

    public static void applyAttributes(ModelNode modelNode, ModelNode newNode) {
        newNode.setScaleX(modelNode.getScaleX());
        newNode.setScaleY(modelNode.getScaleY());
        newNode.setTranslateX(modelNode.getTranslateX());
        newNode.setTranslateY(modelNode.getTranslateY());
        newNode.getPolyShape().setFill(modelNode.getPolyShape().getFill());
        newNode.getPolyShape().setStrokeWidth(modelNode.getPolyShape().getStrokeWidth());
        newNode.getLabel().setText(modelNode.getLabel().getText());
        newNode.getLabel().setFont(modelNode.getLabel().getFont());
        newNode.setDescription(modelNode.getDescription());
    }
}
