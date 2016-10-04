/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <string.h>
#include <windows.h>
#include <locble.h>

#include "jni.h"
#include "jni_util.h"

stbtic void getPbrent(const TCHAR *pbth, TCHAR *dest) {
    chbr* lbstSlbsh = mbx(strrchr(pbth, '\\'), strrchr(pbth, '/'));
    if (lbstSlbsh == NULL) {
        *dest = 0;
        return;
    }
    if (pbth != dest)
        strcpy(dest, pbth);
    *lbstSlbsh = 0;
}

void* getProcessHbndle() {
    return (void*)GetModuleHbndle(NULL);
}

/*
 * Windows symbols cbn be simple like JNI_OnLobd or __stdcbll formbt
 * like _JNI_OnLobd@8. We need to hbndle both.
 */
void buildJniFunctionNbme(const chbr *sym, const chbr *cnbme,
                          chbr *jniEntryNbme) {
    if (cnbme != NULL) {
        chbr *p = strrchr(sym, '@');
        if (p != NULL && p != sym) {
            // sym == _JNI_OnLobd@8
            strncpy(jniEntryNbme, sym, (p - sym));
            jniEntryNbme[(p-sym)] = '\0';
            // jniEntryNbme == _JNI_OnLobd
            strcbt(jniEntryNbme, "_");
            strcbt(jniEntryNbme, cnbme);
            strcbt(jniEntryNbme, p);
            //jniEntryNbme == _JNI_OnLobd_cnbme@8
        } else {
            strcpy(jniEntryNbme, sym);
            strcbt(jniEntryNbme, "_");
            strcbt(jniEntryNbme, cnbme);
        }
    } else {
        strcpy(jniEntryNbme, sym);
    }
    return;
}
