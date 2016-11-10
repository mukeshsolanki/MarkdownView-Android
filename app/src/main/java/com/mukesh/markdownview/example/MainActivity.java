package com.mukesh.markdownview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mukesh.MarkdownView;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
    markdownView.setMarkDownText("# Hello World\nThis is a simple markdown\n"
        + "https://github.com/mukeshsolanki/MarkdownView-Android/");
    //markdownView.loadMarkdownFromAssets("README.md");
    markdownView.setOpenUrlInBrowser(true); // default false

  }
}
