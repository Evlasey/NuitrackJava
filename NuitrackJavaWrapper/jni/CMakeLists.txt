cmake_minimum_required(VERSION 3.1)

set(PROJECT_NAME nuitrack_jni)
project(${PROJECT_NAME})

if(NOT DEFINED ENV{NUITRACK_SDK_ROOT})
    message(FATAL_ERROR "Failed to find NuitrackSDK. Set up NUITRACK_SDK_ROOT environment variable.")
else()
    include_directories($ENV{NUITRACK_SDK_ROOT}/Nuitrack/include)
    link_directories($ENV{NUITRACK_SDK_ROOT}/Nuitrack/lib/win64)
endif()

include_directories(include)

file(GLOB SOURCES src/*)

add_library(${PROJECT_NAME} SHARED ${SOURCES})

target_link_libraries(${PROJECT_NAME} nuitrack)