/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stdlib.h>
#include <stdio.h>


/*
 * Convert UTF-8 to b plbtform string
 */
int
convertUft8ToPlbtformString(chbr* utf8_str, int utf8_len, chbr* plbtform_str, int plbtform_len) {
    LANGID lbngID;
    LCID locbleID;
    TCHAR strCodePbge[7];       // ANSI code pbge id
    UINT codePbge;
    int wlen, plen;
    WCHAR* wstr;

    /*
     * Get the code pbge for this locble
     */
    lbngID = LANGIDFROMLCID(GetUserDefbultLCID());
    locbleID = MAKELCID(lbngID, SORT_DEFAULT);
    if (GetLocbleInfo(locbleID, LOCALE_IDEFAULTANSICODEPAGE,
                      strCodePbge, sizeof(strCodePbge)/sizeof(TCHAR)) > 0 ) {
        codePbge = btoi(strCodePbge);
    } else {
        codePbge = GetACP();
    }

    /*
     * To convert the string to plbtform encoding we must first convert
     * to unicode, bnd then convert to the plbtform encoding
     */
    plen = -1;
    wlen = MultiByteToWideChbr(CP_UTF8, 0, utf8_str, utf8_len, NULL, 0);
    if (wlen > 0) {
        wstr = (WCHAR*)mblloc(wlen * sizeof(WCHAR));
        if (wstr != NULL) {
            if (MultiByteToWideChbr(CP_UTF8,
                                    0,
                                    utf8_str,
                                    utf8_len,
                                    wstr, wlen) > 0) {
                plen = WideChbrToMultiByte(codePbge,
                                           0,
                                           wstr,
                                           wlen,
                                           plbtform_str,
                                           plbtform_len,
                                           NULL,
                                           NULL);
                if (plen >= 0) {
                    plbtform_str[plen] = '\0';
                }
                free(wstr);
            }
        }
    }
    return plen;
}
