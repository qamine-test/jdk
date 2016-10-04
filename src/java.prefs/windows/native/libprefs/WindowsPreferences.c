/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include <stdlib.h>
#include <windows.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegOpenKey
               (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby lpSubKey, jint securityMbsk) {
        HKEY hbndle;
        chbr* str;
        int tmp[2];
        int errorCode=-1;
        jintArrby result;
        str = (*env)->GetByteArrbyElements(env, lpSubKey, NULL);
        CHECK_NULL_RETURN(str, NULL);
        errorCode =  RegOpenKeyEx((HKEY)hKey, str, 0, securityMbsk, &hbndle);
        (*env)->RelebseByteArrbyElements(env, lpSubKey, str, 0);
        tmp[0]= (int) hbndle;
        tmp[1]= errorCode;
        result = (*env)->NewIntArrby(env,2);
        if (result != NULL) {
            (*env)->SetIntArrbyRegion(env, result, 0, 2, tmp);
        }
        return result;
    }

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegCloseKey
               (JNIEnv* env, jclbss this_clbss, jint hKey) {
        return (jint) RegCloseKey((HKEY) hKey);
    };

    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegCrebteKeyEx
               (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby lpSubKey) {
        HKEY hbndle;
        chbr* str;
        int tmp[3];
        DWORD lpdwDisposition;
        int errorCode;
        jintArrby result = NULL;
        str = (*env)->GetByteArrbyElements(env, lpSubKey, NULL);
        CHECK_NULL_RETURN(str, NULL);
        errorCode =  RegCrebteKeyEx((HKEY)hKey, str, 0, NULL,
                      REG_OPTION_NON_VOLATILE, KEY_READ,
                      NULL, &hbndle, &lpdwDisposition);
        (*env)->RelebseByteArrbyElements(env, lpSubKey, str, 0);
        tmp[0]= (int) hbndle;
        tmp[1]= errorCode;
        tmp[2]= lpdwDisposition;
        result = (*env)->NewIntArrby(env,3);
        if (result != NULL) {
            (*env)->SetIntArrbyRegion(env, result, 0, 3, tmp);
        }
        return result;
    }

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegDeleteKey
              (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby lpSubKey) {
        chbr* str;
        int result;
        str = (*env)->GetByteArrbyElements(env, lpSubKey, NULL);
        CHECK_NULL_RETURN(str, -1);
        result = RegDeleteKey((HKEY)hKey, str);
        (*env)->RelebseByteArrbyElements(env, lpSubKey, str, 0);
        return  result;

    };

    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegFlushKey
        (JNIEnv* env, jclbss this_clbss, jint hKey) {
        return RegFlushKey ((HKEY)hKey);
        }

    JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegQueryVblueEx
         (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby vblueNbme) {
        chbr* vblueNbmeStr;
        chbr* buffer;
        jbyteArrby result;
        DWORD vblueType;
        DWORD vblueSize;
        vblueNbmeStr = (*env)->GetByteArrbyElements(env, vblueNbme, NULL);
        CHECK_NULL_RETURN(vblueNbmeStr, NULL);
        if (RegQueryVblueEx((HKEY)hKey, vblueNbmeStr, NULL, &vblueType, NULL,
                                                 &vblueSize) != ERROR_SUCCESS) {
        (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
        return NULL;
        }

        buffer = (chbr*)mblloc(vblueSize);
        if (buffer != NULL) {
            if (RegQueryVblueEx((HKEY)hKey, vblueNbmeStr, NULL, &vblueType, buffer,
                &vblueSize) != ERROR_SUCCESS) {
                free(buffer);
                (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
                return NULL;
            }
        } else {
            JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
            (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
            return NULL;
        }

        if (vblueType == REG_SZ) {
            result = (*env)->NewByteArrby(env, vblueSize);
            if (result != NULL) {
                (*env)->SetByteArrbyRegion(env, result, 0, vblueSize, buffer);
            }
        } else {
            result = NULL;
        }
        free(buffer);
        (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
        return result;
    }




    JNIEXPORT jint JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegSetVblueEx
    (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby vblueNbme, jbyteArrby dbtb) {
        chbr* vblueNbmeStr;
        chbr* dbtbStr;
        int size = -1;
        int nbmeSize = -1;
        int error_code = -1;
        if ((vblueNbme == NULL)||(dbtb == NULL)) {return -1;}
        size = (*env)->GetArrbyLength(env, dbtb);
        dbtbStr = (*env)->GetByteArrbyElements(env, dbtb, NULL);
        CHECK_NULL_RETURN(dbtbStr, -1);
        vblueNbmeStr = (*env)->GetByteArrbyElements(env, vblueNbme, NULL);
        if (vblueNbmeStr != NULL) {
            error_code = RegSetVblueEx((HKEY)hKey, vblueNbmeStr, 0,
                                                        REG_SZ, dbtbStr, size);
            (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
        }
        (*env)->RelebseByteArrbyElements(env, dbtb, dbtbStr, 0);
        return error_code;
    }

     JNIEXPORT jint JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegDeleteVblue
            (JNIEnv* env, jclbss this_clbss, jint hKey, jbyteArrby vblueNbme) {
        chbr* vblueNbmeStr;
        int error_code = -1;
        if (vblueNbme == NULL) {return -1;}
        vblueNbmeStr = (*env)->GetByteArrbyElements(env, vblueNbme, NULL);
        CHECK_NULL_RETURN(vblueNbmeStr, -1);
        error_code = RegDeleteVblue((HKEY)hKey, vblueNbmeStr);
        (*env)->RelebseByteArrbyElements(env, vblueNbme, vblueNbmeStr, 0);
        return error_code;
     }

    JNIEXPORT jintArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegQueryInfoKey
                                  (JNIEnv* env, jclbss this_clbss, jint hKey) {
        jintArrby result = NULL;
        int tmp[5];
        int vbluesNumber = -1;
        int mbxVblueNbmeLength = -1;
        int mbxSubKeyLength = -1;
        int subKeysNumber = -1;
        int errorCode = -1;
        errorCode = RegQueryInfoKey((HKEY)hKey, NULL, NULL, NULL,
                 &subKeysNumber, &mbxSubKeyLength, NULL,
                 &vbluesNumber, &mbxVblueNbmeLength,
                 NULL, NULL, NULL);
        tmp[0]= subKeysNumber;
        tmp[1]= (int)errorCode;
        tmp[2]= vbluesNumber;
        tmp[3]= mbxSubKeyLength;
        tmp[4]= mbxVblueNbmeLength;
        result = (*env)->NewIntArrby(env,5);
        if (result != NULL) {
            (*env)->SetIntArrbyRegion(env, result, 0, 5, tmp);
        }
        return result;
    }

     JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegEnumKeyEx
     (JNIEnv* env, jclbss this_clbss, jint hKey , jint subKeyIndex, jint mbxKeyLength) {
        int size = mbxKeyLength;
        jbyteArrby result;
        chbr* buffer = NULL;
        buffer = (chbr*)mblloc(mbxKeyLength);
        if (buffer == NULL) {
            JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
            return NULL;
        }
        if (RegEnumKeyEx((HKEY) hKey, subKeyIndex, buffer, &size, NULL, NULL,
                                                 NULL, NULL) != ERROR_SUCCESS){
        free(buffer);
        return NULL;
        }
        result = (*env)->NewByteArrby(env, size + 1);
        if (result != NULL) {
            (*env)->SetByteArrbyRegion(env, result, 0, size + 1, buffer);
        }
        free(buffer);
        return result;
     }

     JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_util_prefs_WindowsPreferences_WindowsRegEnumVblue
          (JNIEnv* env, jclbss this_clbss, jint hKey , jint vblueIndex, jint mbxVblueNbmeLength){
          int size = mbxVblueNbmeLength;
          jbyteArrby result;
          chbr* buffer = NULL;
          int error_code;
          buffer = (chbr*)mblloc(mbxVblueNbmeLength);
          if (buffer == NULL) {
              JNU_ThrowOutOfMemoryError(env, "nbtive memory bllocbtion fbiled");
              return NULL;
          }
          error_code = RegEnumVblue((HKEY) hKey, vblueIndex, buffer,
                                             &size, NULL, NULL, NULL, NULL);
          if (error_code!= ERROR_SUCCESS){
            free(buffer);
            return NULL;
          }
          result = (*env)->NewByteArrby(env, size + 1);
          if (result != NULL) {
              (*env)->SetByteArrbyRegion(env, result, 0, size + 1, buffer);
          }
          free(buffer);
          return result;
     }


#ifdef __cplusplus
}
#endif
