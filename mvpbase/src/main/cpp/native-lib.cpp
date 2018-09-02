/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/9/2 09:19
 * Desc:
 * *****************************************************************
 */

#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>


//LOG宏定义
#define LOG_TAG  "JNI_SCRIPT"
#define LOG_E(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOG_I(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
const char *PACKAGE_NAME = "com.lcm.app"; //应用包名
const int RELEASH_SIGN_HSHACODE = -1468878085; //应用签名HashCode


//获取 ApplicationContext
extern "C" JNIEXPORT jobject JNICALL
getApplicationContext(JNIEnv *env, jobject clazz, jobject contextObj){
    jclass context_cls = env->GetObjectClass(contextObj);
    jmethodID applicationContextMethod = env->GetMethodID(context_cls,"getApplicationContext","()Landroid/content/Context;");
    jobject applicationContext = env->CallObjectMethod(contextObj,applicationContextMethod);
    return applicationContext;
}

//获取应用包名
extern "C"
JNIEXPORT jstring JNICALL
getPackagename(JNIEnv *env, jobject clazz, jobject contextObj) {
    jclass native_class = env->GetObjectClass(contextObj);
    jmethodID mId = env->GetMethodID(native_class, "getPackageName", "()Ljava/lang/String;");
    jstring packName = static_cast<jstring>(env->CallObjectMethod(contextObj, mId));
    return packName;
}

//获取应用签名 HashCode
extern "C"
JNIEXPORT jint JNICALL
getSignatureHash(JNIEnv *env, jobject clazz, jobject contextObj) {
    jclass native_class = env->GetObjectClass(contextObj);
    jmethodID pm_id = env->GetMethodID(native_class, "getPackageManager",
                                       "()Landroid/content/pm/PackageManager;");
    jobject pm_obj = env->CallObjectMethod(contextObj, pm_id);
    jclass pm_clazz = env->GetObjectClass(pm_obj);
// 得到 getPackageInfo 方法的 ID
    jmethodID package_info_id = env->GetMethodID(pm_clazz, "getPackageInfo",
                                                 "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jstring pkg_str = getPackagename(env, clazz, contextObj);
// 获得应用包的信息
    jobject pi_obj = env->CallObjectMethod(pm_obj, package_info_id, pkg_str, 64);
// 获得 PackageInfo 类
    jclass pi_clazz = env->GetObjectClass(pi_obj);
// 获得签名数组属性的 ID
    jfieldID signatures_fieldId = env->GetFieldID(pi_clazz, "signatures",
                                                  "[Landroid/content/pm/Signature;");
    jobject signatures_obj = env->GetObjectField(pi_obj, signatures_fieldId);
    jobjectArray signaturesArray = (jobjectArray) signatures_obj;
    jsize size = env->GetArrayLength(signaturesArray);
    jobject signature_obj = env->GetObjectArrayElement(signaturesArray, 0);
    jclass signature_clazz = env->GetObjectClass(signature_obj);

    // 获取签名字符串
//    jmethodID string_id = env->GetMethodID(signature_clazz, "toCharsString",
//    jstring str = static_cast<jstring>(env->CallObjectMethod(signature_obj, string_id));
//    char *c_msg = (char *) env->GetStringUTFChars(str, 0);
//    LOG_E("signsture: %s", c_msg);

    //获取应用签名hashcode
    jmethodID int_hashcode = env->GetMethodID(signature_clazz, "hashCode", "()I");
    jint hashCode = env->CallIntMethod(signature_obj, int_hashcode);

    return hashCode;
}

//验证jni
extern "C" JNIEXPORT jboolean JNICALL
verify(JNIEnv *env, jobject job, jobject contextObj) {

    //根据传入的context对象获取getApplicationContext()，防止java中获取其它已安装APK的Context对象
    jobject applicationContext = getApplicationContext(env,job,contextObj);
    if (applicationContext == NULL){
        LOG_E("context invalid!!");
        return JNI_FALSE;
    }

    //验证包名
    jstring pkgName = getPackagename(env,job,contextObj);
    char *pkg = const_cast<char *>(env->GetStringUTFChars(pkgName, NULL));
    if(strcmp(PACKAGE_NAME,pkg) != 0){
        LOG_E("package name invalid!!");
        return JNI_FALSE;
    }

    //验证签名hashcode
    jint sign_hashcode = getSignatureHash(env,job,contextObj);
    if(sign_hashcode != RELEASH_SIGN_HSHACODE){
        LOG_E("Signature Hashcode invalid!!");
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


extern "C" JNIEXPORT jstring
JNICALL
Java_com_lcm_android_local_Local_wechatParam(JNIEnv *env,jobject clazz, jobject contextObj) {
    jboolean is_valid = verify(env,clazz,contextObj);
    if(!is_valid) {
        return NULL;
    }
    const char *appKey = "wx04cbb7b7256cde38";
    const char *appSecret = "5f9ce65c4f6a4c9143c83629a8544e40";
    char *result = (char *) malloc(strlen(appKey) + strlen(appSecret) + strlen("|") + 1);

    strcpy(result, appKey);
    strcat(result, "|");
    strcat(result, appSecret);
    return env->NewStringUTF(result);
}

// qq配置参数
extern "C" JNIEXPORT jstring
JNICALL
Java_com_lcm_android_local_Local_qqParam(JNIEnv *env,jobject clazz, jobject contextObj) {
    jboolean is_valid = verify(env,clazz,contextObj);
    if(!is_valid) {
        return NULL;
    }
    const char *appKey = "1106901661";
    const char *appSecret = "0bsn3j4p7uYNlpDg";
    char *result = (char *) malloc(strlen(appKey) + strlen(appSecret) + strlen("|") + 1);

    strcpy(result, appKey);
    strcat(result, "|");
    strcat(result, appSecret);
    return env->NewStringUTF(result);
}

// 友盟pushSecret
extern "C" JNIEXPORT jstring
JNICALL
Java_com_lcm_android_local_Local_pushSecret(JNIEnv *env,jobject clazz, jobject contextObj) {
    jboolean is_valid = verify(env,clazz,contextObj);
    if(!is_valid) {
        return NULL;
    }
    const char *pushSecret = "ceb9cb85dc5da447db38ca8861b6afd7";
    return env->NewStringUTF(pushSecret);
}



