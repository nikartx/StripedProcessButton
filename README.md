[![Download](https://api.bintray.com/packages/nikart/maven/StripedProcessButton/images/download.svg) ](https://bintray.com/nikart/maven/StripedProcessButton/_latestVersion) [![](https://jitpack.io/v/nikartm/StripedProcessButton.svg)](https://jitpack.io/#nikartm/StripedProcessButton) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-StripedProcessButton-green.svg?style=flat )]( https://android-arsenal.com/details/1/7623 ) [![Donate using PayPal](https://img.shields.io/badge/paypal-donate-blue.svg) ](https://www.paypal.me/ivodyasov)

# StripedProcessButton
Library for creating process loading button with stripes
## Download
Add to gradle root:
```
buildscript {
    repositories {
        jcenter()
    }
}
```
Download:
```
implementation 'com.github.nikartm:process-button:1.0.1'
```
AndroidX support:
```
implementation 'com.github.nikartm:process-button:2.0.0'
```

## Screenshots
![screenshots](https://raw.githubusercontent.com/nikartm/StripedProcessButton/master/screenshots/sct_ex.gif)
## How to use?
Adjust the xml view [More examples.](https://github.com/nikartm/StripedProcessButton/blob/master/app/src/main/res/layout/activity_main.xml):
```
<com.github.nikartm.support.StripedProcessButton
    android:id="@+id/btn_4"
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
        .start();
```

## License
Copyright 2018 Ivan Vodyasov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.