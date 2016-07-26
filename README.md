[ ![Download](https://api.bintray.com/packages/mukeshsolanki/maven/markdownview/images/download.svg) ](https://bintray.com/mukeshsolanki/maven/markdownview/_latestVersion)

# MarkdownView-Android
MarkdownView is a simple library that helps you display Markdown text or files on Android as a html page just like Github.

![Demo](/Screenshots/demo.gif)

## Getting started

Its really simple to integrate *MarkdownView* in android. All you need to do make the following change to you build gradle under the app module.
```Java
dependencies { 
    compile 'com.mukesh:markdownview:1.0.0'
}
```

## How to use MarkdownView

Its fairly simple and straight forward to use *MarkdownView* in you application. Just add the following in your layout where you want to display the markdown file/text.

```XML
<com.mukesh.MarkdownView
    android:id="@+id/markdown_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```

and reference it in your activity/fragment and assign the markdown text/file like wise.
```Java
MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
markdownView.setMarkDownText("# Hello World\nThis is a simple markdown"); //Displays markdown text
...
markdownView.loadMarkdownFromAssets("README.md"); //Loads the markdown file from the assets folder
...
File markdownFile=new File("filePath");
markdownView.loadMarkdownFromFile(markdownFile); //Loads the markdown file.
```