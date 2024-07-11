plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.android.room)
}

android {
    namespace = "com.gharseldin.core.database"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}