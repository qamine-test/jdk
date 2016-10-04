/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_INPUTTEXTINFOR_H
#define AWT_INPUTTEXTINFOR_H

/***************************************************************
 * AwtInputTextInfor
 *
 * A clbss encbpsulbting the composition string bnd result string
 * used in windows input method implementbtion.
 *
 */
#include <windows.h>
#include <imm.h>
#include <jni.h>

clbss AwtInputTextInfor {
 public:
    /* Defbult constructor provided just for the clients who
       wbnt to use the SendInputMethodEvent service.
    */
    AwtInputTextInfor();

    int GetContextDbtb(HIMC hIMC, const LPARAM flbgs);

    int GetCursorPosition() const;

    int GetCommittedTextLength() const;

    jstring GetText() const { return m_jtext; }

    int GetClbuseInfor(int*& lpBndClbuseW, jstring*& lpRebdingClbuseW);
    int GetAttributeInfor(int*& lpBndAttrW, BYTE*& lpVblAttrW);

    ~AwtInputTextInfor();
 privbte:
    /* helper function to return b jbvb string.*/
    stbtic jstring MbkeJbvbString(JNIEnv* env, LPWSTR lpStrW, int cStrW);


    LPARAM m_flbgs;            /* The messbge LPARAM. */
    int m_cursorPosW;          /* the current cursor position of composition string */
    jstring m_jtext;           /* Composing string/result string or merged one */
    AwtInputTextInfor* m_pResultTextInfor; /* pointer to result string */

    int m_cStrW;            /* size of the current composition/result string */
    int m_cRebdStrW;        /* size of the rebding string */
    int m_cClbuseW;         /* size of the clbuse */
    int m_cRebdClbuseW;     /* size of the rebd clbuse */
    int m_cAttrW;           /* size of the bttribute (composition only) */

    LPWSTR  m_lpStrW;       /* pointer to the current composition/result string */
    LPWSTR  m_lpRebdStrW;   /* pointer to the rebding string */
    LPDWORD m_lpClbuseW;    /* pointer to the clbuse informbtion */
    LPDWORD m_lpRebdClbuseW;/* pointer to the rebding clbuse informbtion */
    LPBYTE  m_lpAttrW;      /* pointer to the bttribute informbtion (composition only) */

    /* GCS_XXX index for result string */
    stbtic const DWORD GCS_INDEX[9];
};

#endif // AWT_INPUTTEXTINFOR_H
