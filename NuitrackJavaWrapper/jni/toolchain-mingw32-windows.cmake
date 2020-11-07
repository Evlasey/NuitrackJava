# the name of the target operating system
SET(CMAKE_SYSTEM_NAME Windows)

# Choose an appropriate compiler prefix

# for classical mingw32
# see http://www.mingw.org/
#set(COMPILER_PREFIX "i586-mingw32msvc")

# for 32 or 64 bits mingw-w64
# see http://mingw-w64.sourceforge.net/
#set(COMPILER_PREFIX "i686-w64-mingw32")
set(COMPILER_PREFIX "x86_64-w64-mingw32")

#setup mingw64 root and compillers
message("MinGW root: $ENV{MINGW64_ROOT}")
if(DEFINED ENV{MINGW64_ROOT})
	set(CMAKE_SYSROOT $ENV{MINGW64_ROOT})
	set(CMAKE_C_COMPILER $ENV{MINGW64_ROOT}/bin/${COMPILER_PREFIX}-gcc)
	set(CMAKE_CXX_COMPILER $ENV{MINGW64_ROOT}/bin/${COMPILER_PREFIX}-g++)
else()
	message(FATAL_ERROR "Failed to find mingw64. Set up MINGW64_ROOT environment variable.")
endif()


#include java dirs
if(DEFINED ENV{JAVA_HOME})
  include_directories(SYSTEM $ENV{JAVA_HOME}/include)
  include_directories(SYSTEM $ENV{JAVA_HOME}/include/win32)
else()
  message(FATAL_ERROR "Failed to find jdk. Set up JAVA_HOME environment variable.")
endif()


# here is the target environment located
#SET(USER_ROOT_PATH /home/erk/erk-win32-dev)
#SET(USER_ROOT_PATH %ENV{MINGW64})
#SET(CMAKE_FIND_ROOT_PATH  /usr/${COMPILER_PREFIX} ${USER_ROOT_PATH})

# adjust the default behaviour of the FIND_XXX() commands:
# search headers and libraries in the target environment, search 
# programs in the host environment
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)
