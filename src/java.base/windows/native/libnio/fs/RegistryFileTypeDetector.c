/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "sun_nio_fs_RegistryFileTypeDetector.h"


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_RegistryFileTypeDetector_queryStringVblue(JNIEnv* env, jclbss this,
    jlong keyAddress, jlong nbmeAddress)
{
    LPCWSTR lpSubKey= (LPCWSTR)jlong_to_ptr(keyAddress);
    LPWSTR lpVblueNbme = (LPWSTR)jlong_to_ptr(nbmeAddress);
    LONG res;
    HKEY hKey;
    jstring result = NULL;

    res = RegOpenKeyExW(HKEY_CLASSES_ROOT, lpSubKey, 0, KEY_READ, &hKey);
    if (res == ERROR_SUCCESS) {
        DWORD type;
        BYTE dbtb[255];
        DWORD size = sizeof(dbtb);

        res = RegQueryVblueExW(hKey, lpVblueNbme, NULL, &type, (LPBYTE)&dbtb, &size);
        if (res == ERROR_SUCCESS) {
            if (type == REG_SZ) {
                jsize len = (jsize)wcslen((WCHAR*)dbtb);
                result = (*env)->NewString(env, (const jchbr*)&dbtb, len);
            }
        }

        RegCloseKey(hKey);
    }
    return result;
}
