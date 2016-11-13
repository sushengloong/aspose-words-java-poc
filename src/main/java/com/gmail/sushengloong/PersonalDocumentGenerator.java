package com.gmail.sushengloong;

import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ssl on 13/11/16.
 */
public class PersonalDocumentGenerator {
    private String name;
    private String date;
    private String showClause;

    private String inputPath;
    private String outputPath;

    private Document document;

    public PersonalDocumentGenerator(String name, Date date, boolean showClause) {
        this.name = name;
        this.date = new SimpleDateFormat("dd MMM yyyy").format(date);
        this.showClause = String.valueOf(showClause);

        this.inputPath = getInputPath();
        this.outputPath = getOutputPath();

        try {
            this.document = new Document(this.inputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void generateDocument() {
        try {
            ReportingEngine engine = new ReportingEngine();

            // java.lang.IllegalStateException: An error has been encountered at the end of expression 'showClause]>'. Can not get the value of member 'showClause' on type 'class java.lang.String'.
            // Crucial for debugging
            engine.buildReport(this.document,
                    new Object[]{this.name, this.date, this.showClause},
                    new String[]{"name", "date", "showClause"}
            );
            this.document.save(this.outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getInputPath() {
        String inputPath = App.class.getClassLoader().getResource("template.docx").getPath().toString();
//        System.out.println("Reading template from " + inputPath);
        return inputPath;
    }

    private String getOutputPath() {
        String outputPath = null;
        try {
            outputPath = new File("./target/document.pdf").getCanonicalPath();
//            System.out.println("Writing document to " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputPath;
    }
}
