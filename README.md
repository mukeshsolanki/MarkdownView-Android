<h1 align="center">MarkdownView</h1>
<p align="center">
  <a class="badge-align" href="https://www.codacy.com/app/mukeshsolanki/MarkdownView-Android?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mukeshsolanki/MarkdownView-Android&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/58e51bc418d349499b3eac9c3f6f3ef1"/></a>
  <a href="https://jitpack.io/#mukeshsolanki/MarkdownView-Android/"> <img src="https://jitpack.io/v/mukeshsolanki/MarkdownView-Android.svg" /></a>
  <a href="https://circleci.com/gh/mukeshsolanki/MarkdownView-Android/tree/master"> <img src="https://circleci.com/gh/mukeshsolanki/MarkdownView-Android/tree/master.svg?style=shield" /></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <br /><br />
    MarkdownView is a simple library that helps you display Markdown text or files on Android as a html page just like Github.
</p>


![Demo](https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/master/Screenshots/demo.gif)

# Supporting MarkdownView

MarkdownView is an independent project with ongoing development and support made possible thanks to donations made by [these awesome backers](BACKERS.md#sponsors). If you'd like to join them, please consider:

- [Become a backer or sponsor on Patreon](https://www.patreon.com/mukeshsolanki).
- [One-time donation via PayPal](https://www.paypal.me/mukeshsolanki)

<a href="https://www.patreon.com/bePatron?c=935498" alt="Become a Patron"><img src="https://c5.patreon.com/external/logo/become_a_patron_button.png" /></a>

## Getting started
Its really simple to integrate *MarkdownView* in android. All you need to do make the following change to you build gradle under the app module.

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

Step 2. Add the dependency
```java
dependencies {
    implementation 'com.github.mukeshsolanki:MarkdownView-Android:<latest-version>'
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

## Author
Maintained by [Mukesh Solanki](https://www.github.com/mukeshsolanki)

## Contribution
[![GitHub contributors](https://img.shields.io/github/contributors/mukeshsolanki/MarkdownView-Android.svg)](https://github.com/mukeshsolanki/MarkdownView-Android/graphs/contributors)

* Bug reports and pull requests are welcome.
* Make sure you use [square/java-code-styles](https://github.com/square/java-code-styles) to format your code.

## License
```
Copyright 2018 Mukesh Solanki

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```