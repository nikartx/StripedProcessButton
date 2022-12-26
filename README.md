[![Maven Central](https://img.shields.io/maven-central/v/io.github.nikartm/process-button.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.nikartm%22%20AND%20a:%22process-button%22) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-StripedProcessButton-green.svg?style=flat )]( https://android-arsenal.com/details/1/7623 )

# StripedProcessButton
Library for creating process loading button with stripes

## Download
Add to gradle root:
```
allprojects {
  repositories {
    mavenCentral()
  }
}
```

#### After migrating to MavenCentral, use Groove:
```
implementation 'io.github.nikartm:process-button:$LAST_VERSION'
```
Or Kotlin DSL:
```
implementation("io.github.nikartm:process-button:$LAST_VERSION")
```

## Screenshots
![screenshots](https://raw.githubusercontent.com/nikartm/StripedProcessButton/master/screenshots/sct_ex.gif)
## How to use?
Adjust the xml view [More examples.](https://github.com/nikartm/StripedProcessButton/blob/master/app/src/main/res/layout/activity_main.xml):
```
<com.github.nikartm.support.StripedProcessButton
    android:id="@+id/stripedButton"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="8dp"
    android:layout_marginEnd="16dp"
    android:layout_marginStart="16dp"
    android:layout_marginTop="16dp"
    android:clickable="true"
    android:focusable="true"
    android:text="Start process"
    tools:spb_showStripes="true"
    app:spb_loadingText="Loading..."
    app:spb_stripeGradient="false"
    app:spb_stripeWidth="30dp"
    app:spb_stripeTilt="35"
    app:spb_background="#888888"
    app:spb_mainStripColor="#212121"
    app:spb_subStripeColor="#ffffff"
    app:spb_stripeDuration="120"
    android:textColor="#ffffff" />
```
Adjust programmatically (shortly):
```
stripedButton.setCornerRadius(50)
        .setStripeAlpha(0.7f)
        .setStripeRevert(true)
        .setStripeGradient(false)
        .setTilt(15)
        .start()
```

## License
Copyright 2022 Ivan Vodyasov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.