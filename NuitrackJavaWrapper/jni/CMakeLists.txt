cmake_minimum_required(VERSION 3.1)

set(PROJECT_NAME nuitrack_jni)
project(${PROJECT_NAME})

set(CMAKE_CONFIGURATION_TYPES Release)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)

if(NOT DEFINED ENV{NUITRACK_SDK_ROOT})
    message(FATAL_ERROR "Failed to find NuitrackSDK. Set up NUITRACK_SDK_ROOT environment variable.")
endif()

if(DEFINED ENV{NUITRACK_HOME})
    set(CMAKE_INSTALL_PREFIX $ENV{NUITRACK_HOME}/bin/)
endif()

include_directories(include)
include_directories($ENV{NUITRACK_SDK_ROOT}/Nuitrack/include)
link_directories($ENV{NUITRACK_SDK_ROOT}/Nuitrack/lib/win64)

file(GLOB SOURCES src/*)

add_library(${PROJECT_NAME} SHARED ${SOURCES})

target_link_libraries(${PROJECT_NAME} nuitrack)

if(DEFINED ENV{NUITRACK_HOME})
    install(TARGETS ${PROJECT_NAME} DESTINATION .)
endif()