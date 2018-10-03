#Heylyn [![](https://jitpack.io/v/wangyonglin/heylyn.svg)](https://jitpack.io/#wangyonglin/heylyn)

## step 1

`allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://jitpack.io"
        }

    }
}`
## step 2
`android {
	  compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
## step 3

implementation 'com.github.wangyonglin:heylyn:1.5.2'