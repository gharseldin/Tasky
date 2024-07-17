plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.jvm.ktor)
}

android {
    namespace = "com.gharseldin.core.data"
}

dependencies {

    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}