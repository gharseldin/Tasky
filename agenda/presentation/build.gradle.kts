plugins {
    alias(libs.plugins.tasky.android.feature.ui)
}

android {
    namespace = "com.gharseldin.agenda.presentation"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.agenda.domain)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
}