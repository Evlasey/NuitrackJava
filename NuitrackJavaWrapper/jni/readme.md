# Building C/C++ JNI Wrapper

### Update headers
1. Open terminal
2. Move to project directory 
3. Use command:
    ```
    python jni\update_native_headers.py
    ```
   
### Setup environment
 1. Download and install [Visual Studio](https://visualstudio.microsoft.com/) (install C++ package)
 2. Download and install [CMake](https://cmake.org/download/) 
 3. Download and install [mingw-w64](https://gnutoolchains.com/mingw64/)
 4. Download and install [Python](https://www.python.org/downloads/windows/)
 5. Download and unpack [Nuitrack SDK](https://github.com/3DiVi/nuitrack-sdk/tree/master)
 6. Setup Environment Variables
    - Set up `JAVA_HOME` to your **JDK** (should be already installed for your **IntelliJ IDEA**)
    - Set up `MINGW64_ROOT` to `mingw-w64` (default should be `C:\SysGCC\mingw64`)
    - Set up `NUITRACK_SDK_ROOT` as `<nuitrack-sdk>/Nuitrack`

### Compile nuitrack_jni library
 1. Run `CMake` util
 2. Set up source dir as `NuitrackJavaWrapper/jni`
 3. Set up build dir as  `NuitrackJavaWrapper/jni/build`
 4. Press `Configure` button
 5. Choose `Specify toolchain file for cross-compiling` and click `Next` 
 6. Choose file `NuitrackJavaWrapper/jni/toolchain-mingw32-windows.cmake` and click `Finish`
 7. Press `Generate` button 
 8. Press `Open project` button to open VS C++ Project
 9. Setup `Release x64` configuration and build the project