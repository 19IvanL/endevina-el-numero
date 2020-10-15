package com.ivanl.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Collections;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Collections.sort(RankingCollection.rankingList);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tlMarksTable);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        // For every score...
        for (int i = 0; i < RankingCollection.rankingList.size(); i++) {
            // Creating new tablerows and textviews
            TableRow row = new TableRow(this);
            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);
            TextView txt3 = new TextView(this);
            // Setting the text
            txt1.setText(RankingCollection.rankingList.get(i).getUsername());
            txt2.setText(String.valueOf(RankingCollection.rankingList.get(i).getTries()));
            txt3.setText(String.valueOf(RankingCollection.rankingList.get(i).getTime()));
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

}