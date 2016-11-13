package com.gmail.sushengloong;

import com.aspose.words.Document;
import com.aspose.words.TxtSaveOptions;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by ssl on 13/11/16.
 */
public class PersonalDocumentGeneratorTest {
    private String name = "Peter Parker";
    private Date date = new Date();
    private boolean showClause = false;

    private String generatedDocumentOutput() {
        PersonalDocumentGenerator generator = null;
        try {
            generator = new PersonalDocumentGenerator(this.name, this.date, this.showClause);
            generator.generateDocument();
            Document document = generator.getDocument();
            return document.toString(new TxtSaveOptions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // http://uldissturms.github.io/2016/02/07/compare-word-documents-using-aspose/
    @Test
    public void generateDocumentContainsPersonInformation() {
        String output = generatedDocumentOutput();

        assertThat(output, containsString("Peter Parker"));
        String expectedDateString = new SimpleDateFormat("dd MMM yyyy").format(new Date());
        assertThat(output, containsString(expectedDateString));
    }

    @Test
    public void generateDocumentShowsConditionalClause() throws Exception {
        this.showClause = true;
        String output = generatedDocumentOutput();

        assertThat(output, containsString("This is a conditional clause."));
    }

    @Test
    public void generateDocumentDoesNotShowConditionalClause() throws Exception {
        this.showClause = false;
        String output = generatedDocumentOutput();

        assertThat(output, not(containsString("This is a conditional clause.")));
    }
}
