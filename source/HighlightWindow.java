package com.pdeplugin.mitfinalproject.views;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.pdeplugin.mitfinalproject.model.HighlightResult;

public class HighlightWindow {

    private final Display display;

    public HighlightWindow(Display display) {
        this.display = display;
    }

    public void open(String code, HighlightResult result) {

        Shell shell = new Shell(display);
        shell.setText("AI Code Highlight Detection");
        shell.setSize(800, 600);
        shell.setLayout(new FillLayout());

        StyledText styledText = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        styledText.setText(code);

        highlightLines(styledText, code, result.getAiLines());

        shell.open();
    }

    private void highlightLines(StyledText styledText, String code, List<Integer> lines) {

        String[] codeLines = code.split("\n");

        int offset = 0;

        for (int i = 0; i < codeLines.length; i++) {

            int lineNumber = i + 1;

            if (lines.contains(lineNumber)) {

                StyleRange style = new StyleRange();
                style.start = offset;
                style.length = codeLines[i].length();
                style.background = new Color(display, 255, 150, 150);

                styledText.setStyleRange(style);
            }

            offset += codeLines[i].length() + 1;
        }
    }
}
