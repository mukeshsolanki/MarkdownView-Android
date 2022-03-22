<h1 align="center">Compose Markdown View</h1>
<p align="center">
  <a class="badge-align" href="https://www.codacy.com/app/mukeshsolanki/MarkdownView-Android?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mukeshsolanki/MarkdownView-Android&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/58e51bc418d349499b3eac9c3f6f3ef1"/></a>
  <a href="https://jitpack.io/#mukeshsolanki/MarkdownView-Android"><img src="https://jitpack.io/v/mukeshsolanki/MarkdownView-Android/month.svg"/></a>
  <a href="https://jitpack.io/#mukeshsolanki/MarkdownView-Android/"> <img src="https://jitpack.io/v/mukeshsolanki/MarkdownView-Android.svg" /></a>
  <a href="https://github.com/mukeshsolanki/MarkdownView-Android/actions"> <img src="https://github.com/mukeshsolanki/MarkdownView-Android/workflows/Build/badge.svg" /></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"/></a>
  <br /><br />
    MarkdownView is a composable library that helps you display Markdown text or files on Android as a html page just like Github.
</p>

![Demo](https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/master/Screenshots/demo.gif)

# Supporting Compose Markdown View

Compose Markdown View is an independent project with ongoing development and support made possible thanks to your donations.
- [Become a backer](https://www.paypal.me/mukeshsolanki)

## Getting started
Its really simple to integrate *Markdown* in android. All you need to do make the following change to you build gradle under the app module.

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```kotlin
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

Step 2. Add the dependency
```kotlin
dependencies {
    implementation 'com.github.mukeshsolanki:MarkdownView-Android:2.0.0'
}
```

## How to use Markdown
Its fairly simple and straight forward to use *Markdown* in you application.

- Using Compose

Just use `MarkDown` composable where you need to display the view like
```kotlin
MarkDown(
    url = URL("https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/main/README.md"),
    modifier = Modifier.fillMaxSize()
)
```

- Using Older View System (aka XML)

Add a compose view in your xml file like
```XML
<androidx.compose.ui.platform.ComposeView
    android:id="@+id/markdown"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

and reference it in your activity/fragment and assign the markdown text/file like wise.
```kotlin
val markdown = findViewById(R.id.markdown)
markdown.composeView.apply {
    // Dispose of the Composition when the view's LifecycleOwner is destroyed
    setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
    setContent {
       // In Compose world
       MaterialTheme {
            MarkDown(
                url = URL("https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/main/README.md"),
                modifier = Modifier.fillMaxSize()
            )
       }
    }
}
```
## Markdown Sources
You have 3 different sources from where markdown data can be read
- `Text` - You can pass the content and string to render the markdown
```kotlin
MarkDown(
    text = "# Test Markdown",
    modifier = Modifier.fillMaxSize()
)
```
- `File` - You can read from a file and display the markdown data.
```kotlin
MarkDown(
    file = file,
    modifier = Modifier.fillMaxSize()
)
```
- `URL` - You can also specify the url of the markdown file.
```kotlin
MarkDown(
    url = URL("https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/main/README.md"),
    modifier = Modifier.fillMaxSize()
)
```

## Author
Maintained by [Mukesh Solanki](https://www.github.com/mukeshsolanki)

## Contribution
[![GitHub contributors](https://img.shields.io/github/contributors/mukeshsolanki/MarkdownView-Android.svg)](https://github.com/mukeshsolanki/MarkdownView-Android/graphs/contributors)

* Bug reports and pull requests are welcome.

## License
```
Copyright (c) 2018 Mukesh Solanki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```