plugins {
    alias(libs.plugins.tasky.android.library.compose)
}

android {
    namespace = "com.gharseldin.core.presentation.ui"
}

dependencies {

    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.lifecycle.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}