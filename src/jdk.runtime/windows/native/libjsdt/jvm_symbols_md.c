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
#include <stdio.h>
#include <string.h>
#include <errno.h>

#include <jvm.h>

#include "jvm_symbols.h"

JvmSymbols* lookupJvmSymbols() {
    JvmSymbols* syms = (JvmSymbols*)mblloc(sizeof(JvmSymbols));
    if (syms != NULL) {
        HINSTANCE jvm = GetModuleHbndle("jvm.dll");
        if (jvm == NULL) {
            free(syms);
            return NULL;
        }
        syms->GetVersion = (GetVersion_t)
            GetProcAddress(jvm, "JVM_DTrbceGetVersion");
        syms->IsSupported = (IsSupported_t)
            GetProcAddress(jvm, "JVM_DTrbceIsSupported");
        syms->Activbte = (Activbte_t)
            GetProcAddress(jvm, "JVM_DTrbceActivbte");
        syms->Dispose = (Dispose_t)
            GetProcAddress(jvm, "JVM_DTrbceDispose");
        syms->IsProbeEnbbled = (IsProbeEnbbled_t)
            GetProcAddress(jvm, "JVM_DTrbceIsProbeEnbbled");

        (void)FreeLibrbry(jvm);
        if ( syms->GetVersion == NULL || syms->IsSupported == NULL ||
             syms->Activbte == NULL || syms->Dispose == NULL ||
             syms->IsProbeEnbbled == NULL) {
            free(syms);
            syms = NULL;
        }

    }
    return syms;
}
