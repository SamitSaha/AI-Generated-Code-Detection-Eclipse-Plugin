# AI-Generated-Code-Detection-Eclipse-Plugin
AI-Generated Code Detection Eclipse Plugin is an Eclipse IDE extension that analyses selected source code using multiple LLMs to estimate whether it is AI-generated or human-written. The plugin features ensemble scoring, code highlighting, and interactive visualisations to support code authorship analysis and AI detection research.
# AI Code Detection and Visualization Eclipse Plug-in

## Project Overview

The **AI Code Detection and Visualization Eclipse Plug-in** is an intelligent Eclipse IDE extension developed to assist programmers, researchers, educators, and software engineering students in identifying potentially AI-generated source code. The plug-in integrates Large Language Models (LLMs) through the Hugging Face Inference API and performs multi-model code analysis directly within the Eclipse development environment.

The primary objective of this project is to investigate whether modern AI models can detect AI-generated code and to provide a visual and explainable assessment of the detection results. The plug-in allows users to select source code in the Eclipse editor, analyse it with multiple AI models, compare model predictions, generate ensemble-based decisions, visualise results with charts and graphs, and highlight suspicious AI-generated code segments.

---

## Problem Statement

With the rapid advancement of Generative AI systems such as ChatGPT, GitHub Copilot, Gemini, Claude, and other code generation tools, distinguishing between human-written and AI-generated code has become increasingly challenging.

This creates several concerns:

- Academic integrity in programming education
- Authenticity verification in software development
- Research on AI-generated content detection
- Understanding coding patterns produced by LLMs
- Evaluating the reliability of AI code detection systems

Currently, there is a lack of integrated tools within IDE environments that can analyse source code, compare multiple AI models, and provide explainable visual insights into AI-generated code.

This project addresses these challenges by developing an Eclipse-based intelligent code analysis platform.

---

## Project Objectives

The main objectives of this project are:

- Detect whether selected code is likely AI-generated or human-written.
- Compare predictions from multiple Large Language Models.
- Calculate AI-likeness scores and confidence values.
- Generate ensemble-based final decisions.
- Visualize model outputs using charts and dashboards.
- Highlight potentially AI-generated code segments.
- Provide explainable AI analysis directly inside the Eclipse IDE.

---

## Key Features

### Code Selection

Users can select any source code directly from the Eclipse editor using the **Select Code** button.

### Multi-Model AI Analysis

The plug-in sends the selected code to multiple LLMs through the Hugging Face API and collects independent predictions.

Current models include:

- Meta-Llama-3-70B-Instruct
- Meta-Llama-3-8B-Instruct

Each model evaluates the code and returns:

- Prediction
- AI Match Percentage
- Confidence Score
- AI Probability
- Human Probability
- Detailed Reasoning

---

### Ensemble Decision System

Instead of relying on a single model, the plug-in combines outputs from multiple models to produce a more reliable final prediction.

The ensemble scoring system calculates:

- Average AI probability
- Model agreement rate
- Final AI score
- Final classification

Possible outcomes:

- AI Generated
- Human Written

---

### Visualization Dashboard

The project includes an interactive dashboard for graphical result analysis.

The dashboard provides:

#### 1. Model AI Percentage Comparison

A bar chart comparing AI-likeness percentages predicted by different models.

#### 2. Confidence vs AI Probability Graph

A scatter/line chart displaying relationships between confidence levels and AI probabilities.

#### 3. AI vs Human Probability Radar Chart

A radar chart visualizing:

- AI Probability
- Human Probability
- Confidence

for each participating model.

These visualizations help users understand differences between model decisions and identify trends in the detection results.

---

### AI Line Highlighting

The plug-in includes a code highlighting feature.

After analysis:

- Users can open the Highlight Window.
- The selected source code is displayed.
- AI-suspected lines are highlighted in red.
- Highlighting is performed using model-generated line predictions.

This enables developers to quickly identify which sections of code may require further review or modification.

---

## System Architecture

The project follows a layered architecture to ensure maintainability, scalability, and separation of concerns.

### View Layer

Responsible for user interaction and UI components.

Examples:

- SampleView
- Visualization Dashboard
- Highlight Window

### Controller Layer

Acts as an intermediary between UI and business logic.

Examples:

- AnalysisController

### Service Layer

Handles application logic and processing.

Examples:

- AIAnalysisService
- EnsembleScoringService
- FeatureExtractionService

### LLM Layer

Manages communication with external AI models.

Examples:

- HuggingFaceClient

### Model Layer

Stores structured data objects.

Examples:

- ModelResponse
- EnsembleResult

### Utility Layer

Provides helper methods and JSON processing utilities.

Examples:

- JsonUtils

---

## Technology Stack

### Programming Language

- Java 21

### IDE Framework

- Eclipse PDE (Plug-in Development Environment)

### User Interface

- SWT (Standard Widget Toolkit)
- Eclipse ViewPart

### Visualization

- SWTChart

### AI Integration

- Hugging Face Router API

### Models

- Meta-Llama-3-70B-Instruct
- Meta-Llama-3-8B-Instruct

### Development Tools

- Eclipse IDE
- Git
- GitHub

---

## Workflow

1. User selects source code from the Eclipse editor.
2. The code is extracted by the plug-in.
3. A forensic AI detection prompt is generated.
4. The prompt is sent to multiple LLMs.
5. Model outputs are collected.
6. AI probabilities and confidence values are extracted.
7. Ensemble scoring computes the final prediction.
8. Results are displayed in the output panel.
9. Visualization dashboard generates charts.
10. Highlight module marks suspected AI-generated code segments.

---

## Research Contribution

This project contributes to the growing field of AI-generated code detection by:

- Evaluating multiple LLMs for code authorship analysis.
- Comparing model behavior through ensemble techniques.
- Providing explainable AI outputs.
- Integrating AI detection directly into a software development environment.
- Supporting future research in software forensics and AI-generated content analysis.

---

## Future Enhancements

Future versions of the project may include:

- Additional LLM providers (OpenAI, Gemini, Claude, DeepSeek)
- Local model execution using Ollama
- Advanced explainable AI metrics
- Real-time code monitoring
- Exportable PDF reports
- Machine learning-based ensemble weighting
- Historical analysis database
- Detection of AI-generated comments and documentation

---

## Conclusion

The AI Code Detection and Visualization Eclipse Plug-in demonstrates how Large Language Models can be integrated into modern development environments to analyze and identify potentially AI-generated code. By combining multi-model analysis, ensemble decision-making, visualization dashboards, and code highlighting techniques, the system provides a comprehensive framework for studying AI-generated software artifacts and improving transparency in software development workflows.
