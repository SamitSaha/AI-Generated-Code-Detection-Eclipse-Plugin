package com.pdeplugin.mitfinalproject.views;

import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtchart.Chart;
import org.eclipse.swtchart.ISeries;
import org.eclipse.swtchart.ISeries.SeriesType;
import org.eclipse.swtchart.ILineSeries;
import com.pdeplugin.mitfinalproject.model.ModelResponse;

public class VisualizationDashboard {

    private Shell shell;

    public VisualizationDashboard(Display display) {
        shell = new Shell(display);
        shell.setText("AI Detection Visual Dashboard");
        shell.setSize(900, 900);

        // One column, equal width false
        GridLayout layout = new GridLayout(1, false);
        shell.setLayout(layout);
    }

    public void open(List<ModelResponse> results) {
        createBarChart(results);
        createScatterChart(results);
        createRadarChart(results);

        shell.open();
        Display display = shell.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
    

    
    private void createBarChart(List<ModelResponse> results) {
        Chart chart = new Chart(shell, SWT.NONE);
       
        chart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        chart.getTitle().setText("Model AI Percentage Comparison");

        double[] ySeries = new double[results.size()];
        String[] categories = new String[results.size()];

        for (int i = 0; i < results.size(); i++) {
            ModelResponse r = results.get(i);
            ySeries[i] = r.getPercentage();
            categories[i] = r.getModel();
        }

        ISeries<Double> series = (ISeries<Double>) chart.getSeriesSet().createSeries(SeriesType.BAR, "AI Score");
        series.setYSeries(ySeries);

        // CRITICAL: Set X-Axis to category mode so labels show up
        chart.getAxisSet().getXAxis(0).enableCategory(true);
        chart.getAxisSet().getXAxis(0).setCategorySeries(categories);
        chart.getAxisSet().adjustRange();
    }

    private void createScatterChart(List<ModelResponse> results) {
        Chart chart = new Chart(shell, SWT.NONE);
        chart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        chart.getTitle().setText("Confidence vs AI Probability");

        double[] x = new double[results.size()];
        double[] y = new double[results.size()];

        for (int i = 0; i < results.size(); i++) {
            x[i] = results.get(i).getAiProbability();
            y[i] = (double) results.get(i).getConfidence();
        }

        ILineSeries<Double> series = (ILineSeries<Double>) chart.getSeriesSet().createSeries(SeriesType.LINE, "Confidence");
        series.setXSeries(x);
        series.setYSeries(y);
        // Line chart without lines (scatter)
        series.setLineStyle(org.eclipse.swtchart.LineStyle.NONE);

        chart.getAxisSet().getXAxis(0).getTitle().setText("AI Probability");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Confidence");
        chart.getAxisSet().adjustRange();
    }

    private void createRadarChart(List<ModelResponse> results) {
        RadarChartCanvas radar = new RadarChartCanvas(shell, SWT.BORDER);
        // CRITICAL: Radar needs room to draw
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.minimumHeight = 300; 
        radar.setLayoutData(gd);

        radar.setResults(results);
    }
}
