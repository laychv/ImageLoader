# ImageLoader

来自源码设计模式书中的代码

## Android源码设计模式

通过ImageLoader学习到设计原则

- SRP
- OCP

## 注意

module无法使用压缩

```
Resource shrinker cannot be used for libraries.
```

## Gradle加载不同资源

```

apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'

android {
    compileSdkVersion 26
    buildToolsVersion "26.0.2"
    defaultConfig {
        applicationId "com.persons"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary= true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    flavorDimensions "app"

    sourceSets {
        one {
            java.srcDir('src/one/java')
            res.srcDir('src/one/res')

            // assets.srcDir('src/one/assets')
            // manifest.srcFile('src/one/AndroidManifest.xml')
        }

        two {
            java.srcDir('src/two/java')
            res.srcDir('src/two/res')

            // assets.srcDir('src/two/assets')
            // manifest.srcFile('src/two/AndroidManifest.xml')
        }
    }

    signingConfigs {
        // 只是用来做演示，没有特地生成签名文件
        one {
            storeFile file("src/one/one.jks")
            storePassword "123456"
            keyAlias "one"
            keyPassword "123456"

            // 开启 V2 签名
            v2SigningEnabled true
        }

        two {
            storeFile file("src/two/liKeystore.jks")
            storePassword "123456"
            keyAlias "two"
            keyPassword "123456"
            v2SigningEnabled true
        }
    }

    productFlavors {
        one {
            applicationId "com.demo.one"
            versionCode 2
            dimension "app"
            versionName "1.0.2"

            signingConfig signingConfigs.li
        }

        two {
            applicationId "com.demo.two"
            versionCode 1
            dimension "app"
            versionName "1.0"

            signingConfig signingConfigs.two

            manifestPlaceholders = [
                    MOB_APPKEY   : "202c38fasdccb5b", // ShareSDK的key
                    MOB_APPSECRET: "ac23456d85f29333b20f643502979468", //ShareSDK
                    JPUSH_PKGNAME: applicationId
            ]
        }
    }
}

dependencies { }
```