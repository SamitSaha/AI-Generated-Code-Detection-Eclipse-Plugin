package com.pdeplugin.mitfinalproject.views;

import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import com.pdeplugin.mitfinalproject.model.ModelResponse;

public class RadarChartCanvas extends Canvas {

    private List<ModelResponse> results;

    public RadarChartCanvas(Composite parent, int style) {
        super(parent, style);
        // Using a lambda for the paint listener
        addPaintListener(e -> drawRadarChart(e.gc));
    }

    public void setResults(List<ModelResponse> results) {
        this.results = results;
        redraw();
    }

    private void drawRadarChart(GC gc) {
        if (results == null || results.isEmpty()) {
            gc.drawText("No Analysis Data Available", 20, 20);
            return;
        }

        Rectangle area = getClientArea();
        int centerX = area.width / 2;
        int centerY = area.height / 2;
        int radius = Math.min(centerX, centerY) - 80; // More padding for labels

        String[] labels = {"AI Prob", "Human Prob", "Confidence"};
        int axisCount = labels.length;
        double angleStep = (2 * Math.PI) / axisCount;

        // Draw Axes
        gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        for (int i = 0; i < axisCount; i++) {
            double angle = i * angleStep - Math.PI/2; // Rotate so first axis is at top
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            gc.drawLine(centerX, centerY, x, y);
            gc.drawText(labels[i], x - 20, y > centerY ? y + 5 : y - 20);
        }

        Color[] colors = {
                getDisplay().getSystemColor(SWT.COLOR_RED),
                getDisplay().getSystemColor(SWT.COLOR_BLUE)
        };

        int modelIndex = 0;
        for (ModelResponse r : results) {
            // Normalize confidence to 0-1 range for the radar
            double[] values = { r.getAiProbability(), r.getHumanProbability(), r.getConfidence() / 100.0 };
            int[] polygon = new int[axisCount * 2];

            for (int i = 0; i < axisCount; i++) {
                double angle = i * angleStep - Math.PI/2;
                double val = values[i];
                polygon[i * 2] = (int) (centerX + (radius * val) * Math.cos(angle));
                polygon[i * 2 + 1] = (int) (centerY + (radius * val) * Math.sin(angle));
            }

            gc.setLineWidth(2);
            gc.setForeground(colors[modelIndex % colors.length]);
            gc.drawPolygon(polygon);
            gc.drawText(r.getModel() + " (Path)", 10, 10 + (modelIndex * 20));
            modelIndex++;
        }
    }
}

