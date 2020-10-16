package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Collections;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ranking");
        setContentView(R.layout.activity_ranking);
        Collections.sort(RankingCollection.rankingList);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tlMarksTable);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        headerRowCreation(tableLayout, params1, params2);
        scoreRowsCreation(tableLayout, params1, params2);
    }

    private void scoreRowsCreation(TableLayout tableLayout, TableRow.LayoutParams params1, TableRow.LayoutParams params2) {
        for (int i = 0; i < RankingCollection.rankingList.size(); i++) {
            // Creating new tablerows and textviews
            TableRow row = new TableRow(this);
            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);
            TextView txt3 = new TextView(this);
            // Setting the text
            txt1.setText(RankingCollection.rankingList.get(i).getUsername());
            txt2.setText(String.valueOf(RankingCollection.rankingList.get(i).getTries()));
            txt3.setText(String.valueOf(RankingCollection.rankingList.get(i).getTime()) + " s");
            txt1.setLayoutParams(params1);
            txt2.setLayoutParams(params1);
            txt3.setLayoutParams(params1);
            // The textviews have to be added to the row created
            row.addView(txt1);
            row.addView(txt2);
            row.addView(txt3);
            row.setLayoutParams(params2);
            tableLayout.addView(row);
        }
    }

    private void headerRowCreation(TableLayout tableLayout, TableRow.LayoutParams params1, TableRow.LayoutParams params2) {
        // Creating new tablerows and textviews
        TableRow tableHeaderRow = new TableRow(this);
        TextView header1 = new TextView(this);
        TextView header2 = new TextView(this);
        TextView header3 = new TextView(this);
        // Setting the text
        header1.setText("Usuario");
        header2.setText("Intentos");
        header3.setText("Tiempo");
        header1.setLayoutParams(params1);
        header2.setLayoutParams(params1);
        header3.setLayoutParams(params1);
        // Additionally, we set the text as bold
        header1.setTypeface(null, Typeface.BOLD);
        header2.setTypeface(null, Typeface.BOLD);
        header3.setTypeface(null, Typeface.BOLD);
        // The textviews have to be added to the row created
        tableHeaderRow.addView(header1);
        tableHeaderRow.addView(header2);
        tableHeaderRow.addView(header3);
        tableHeaderRow.setLayoutParams(params2);
        tableLayout.addView(tableHeaderRow);
    }

}