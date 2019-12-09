package com.example.diagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DiagramView diagramView;
    TextView percents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diagramView = findViewById(R.id.diagram);
        percents = findViewById(R.id.percents);

        diagramView.setParent(this);
    }

    public void setPercents(double percents) {
        this.percents.setText(String.format("%.2f", percents) + "%");
    }
}
