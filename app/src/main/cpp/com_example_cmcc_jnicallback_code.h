//
// Created by cmcc on 2017/5/26.
//
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <android/log.h>

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "native-activity", __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, "native-activity", __VA_ARGS__))

#ifndef JNICALLBACK_COM_EXAMPLE_CMCC_JNICALLBACK_CODE_H
#define JNICALLBACK_COM_EXAMPLE_CMCC_JNICALLBACK_CODE_H



#ifdef __cplusplus
extern "C"{
#endif

JNIEXPORT void JNICALL Java_example_cmcc_jnicallback_MainActivity_callJNIInt(JNIEnv *env, jobject obj, jint i);

JNIEXPORT void JNICALL Java_example_cmcc_jnicallback_MainActivity_callJNIString(JNIEnv *env, jobject obj, jstring s);

JNIEXPORT void JNICALL Java_example_cmcc_jnicallback_MainActivity_callJNIByte(JNIEnv *env, jobject obj, jbyteArray b);


#ifdef __cplusplus
}
#endif
#endif //JNICALLBACK_COM_EXAMPLE_CMCC_JNICALLBACK_CODE_H