# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/opt/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes Signature
-keepattributes Exceptions

-dontwarn android.databinding.**

-dontwarn javax.**


# ----------------------------------------
# Android Support Library
# ----------------------------------------
-dontwarn android.support.**
-keep class android.support.** { *; }

# ----------------------------------------
# RxJava
# ----------------------------------------
-dontwarn rx.internal.util.unsafe.**
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

# ----------------------------------------
# Retrofit and OkHttp
# ----------------------------------------
-dontwarn com.squareup.okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# ----------------------------------------
# retrolambda
# ----------------------------------------
-dontwarn java.lang.invoke.*

# ----------------------------------------
# StaticGson
# ----------------------------------------
-keep @com.github.gfx.static_gson.annotation.StaticGsonGenerated class * { *; }