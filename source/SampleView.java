package com.pdeplugin.mitfinalproject.views;

import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import com.pdeplugin.mitfinalproject.controller.AnalysisController;
import com.pdeplugin.mitfinalproject.model.ModelResponse;
import com.pdeplugin.mitfinalproject.model.EnsembleResult;

import com.pdeplugin.mitfinalproject.model.HighlightResult;
//import com.pdeplugin.mitfinalproject.views.HighlightWindow;



public class SampleView extends ViewPart {

    private Text codeBox;
    private final AnalysisController controller = new AnalysisController();
    
    private String selectedCode = "";
    private List<ModelResponse> results;
    private List<ModelResponse> lastResults;
    private EnsembleResult finalResult;

    @Override
    public void createPartControl(Composite parent) {

        parent.setLayout(new GridLayout(1, false));

        codeBox = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        codeBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        codeBox.setEditable(false);

        Button selectBtn = new Button(parent, SWT.PUSH);
        selectBtn.setText("Select Code");
        selectBtn.addListener(SWT.Selection, e -> readSelectedCode());

        Button analyzeBtn = new Button(parent, SWT.PUSH);
        analyzeBtn.setText("Analyze");
        analyzeBtn.addListener(SWT.Selection, e -> analyze());
        
        Button visualizeBtn = new Button(parent, SWT.PUSH);
        visualizeBtn.setText("Visual Representation");
        visualizeBtn.addListener(SWT.Selection, e -> openDashboard());
        
        Button highlightBtn = new Button(parent, SWT.PUSH);
        highlightBtn.setText("Highlight AI Code");
        highlightBtn.addListener(SWT.Selection, e -> highlightCode());
    }

    private void readSelectedCode() {

        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window == null) return;

        IWorkbenchPage page = window.getActivePage();
        if (page == null) return;

        IEditorPart editorPart = page.getActiveEditor();
        if (!(editorPart instanceof ITextEditor editor)) return;

        ISelection sel = editor.getSelectionProvider().getSelection();

        if (sel instanceof ITextSelection textSel) {
        	selectedCode = textSel.getText();
            codeBox.setText(textSel.getText());
        }
    }

    private void analyze() {

        String code = codeBox.getText();

        if (code == null || code.isBlank()) {
            codeBox.setText("ERROR: Select code first.");
            return;
        }

        results = controller.analyzeCode(code);
        finalResult = controller.computeFinal(results);

        StringBuilder output = new StringBuilder();

        for (ModelResponse r : results) {

            output.append("MODEL: ")
                  .append(r.getModel())
                  .append("\n");

            output.append("AI Probability: ")
                  .append(r.getAiProbability())
                  .append("\n");

            output.append("Human Probability: ")
                  .append(r.getHumanProbability())
                  .append("\n");

            output.append("Confidence: ")
                  .append(r.getConfidence())
                  .append("\n\n");

            output.append("RAW RESPONSE:\n")
                  .append(r.getRawContent())
                  .append("\n\n--------------------------------\n\n");
        }

        output.append("\n=========== FINAL RESULT ===========\n");

        output.append("Prediction: ")
              .append(finalResult.getPrediction())
              .append("\n");

        output.append("AI Score: ")
              .append(finalResult.getFinalPercentage())
              .append("%\n");

        output.append("Model Agreement: ")
              .append(finalResult.getAgreement())
              .append("\n");

        codeBox.setText(output.toString());
    }
    
    private void openDashboard() {
        if (results == null || results.isEmpty()) {

            codeBox.setText("Run analysis first.");
            return;
        }

        VisualizationDashboard dashboard =
                new VisualizationDashboard(
                        Display.getCurrent());

        dashboard.open(results);
    }
    
    private void highlightCode() {

        if (selectedCode == null || selectedCode.isBlank()) {

            codeBox.setText("Select code first.");
            return;
        }

        HighlightResult result =
                controller.detectAiLines(selectedCode);

        HighlightWindow window =
                new HighlightWindow(Display.getCurrent());

        window.open(selectedCode, result);
    }
    
    @Override
    public void setFocus() {
        codeBox.setFocus();
    }
}