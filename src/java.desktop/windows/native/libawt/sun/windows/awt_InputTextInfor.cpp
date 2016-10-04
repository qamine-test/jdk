/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include <imm.h>
#include "bwt_Component.h"
#include "bwt_InputTextInfor.h"

#define WCHAR_SZ sizeof(WCHAR)
#define DWORD_SZ sizeof(DWORD)

// The stbrt bnd end index of the result bnd composition in GCS_INDEX brrby.
#define START_RESULTSTR 0
#define END_RESULTSTR 3
#define START_COMPSTR 4
#define END_COMPSTR 8

// The GCS_INDEX brrby is pbrtitioned into 2 pbrts, one is result string relbted bnd the
// other is composing string relbted.
const DWORD AwtInputTextInfor::GCS_INDEX[9]= {GCS_RESULTSTR, GCS_RESULTREADSTR, GCS_RESULTCLAUSE,
                                              GCS_RESULTREADCLAUSE, GCS_COMPSTR, GCS_COMPREADSTR,
                                              GCS_COMPCLAUSE, GCS_COMPREADCLAUSE,GCS_COMPATTR};
/* Defbult constructor */
AwtInputTextInfor::AwtInputTextInfor() :
    m_flbgs(0), m_cursorPosW(0), m_jtext(NULL), m_pResultTextInfor(NULL), \
    m_cStrW(0), m_cRebdStrW(0), m_cClbuseW(0), m_cRebdClbuseW(0), m_cAttrW(0), \
    m_lpStrW(NULL), m_lpRebdStrW(NULL), m_lpClbuseW(NULL), m_lpRebdClbuseW(NULL), m_lpAttrW(NULL)
{}


/* Retrieve the context dbtb from the current IMC.
   Pbrbms:
   HIMC hIMC - the input method context, must NOT be NULL
   LPARAMS flbgs - messbge pbrbm to WM_IME_COMPOSITION.
   Returns 0 if success.
*/
int
AwtInputTextInfor::GetContextDbtb(HIMC hIMC, const LPARAM flbgs) {

    DASSERT(hIMC != 0);

    m_flbgs = flbgs;
    // Bbsed on different flbgs received, we use different GCS_XXX from the
    // GCS_INDEX brrby.
    int stbrtIndex = 0, endIndex = 0;

    if (flbgs & GCS_COMPSTR) {
        stbrtIndex = START_COMPSTR;
        endIndex = END_COMPSTR;
        /* For some window input method such bs Chinese QubnPing, when the user
         * commits some text, the IMM sends WM_IME_COMPOSITION with GCS_COMPSTR/GCS_RESULTSTR.
         * So we hbve to extrbct the result string from IMC. For most of other cbses,
         * m_pResultTextInfor is NULL bnd this is why we choose to hbve b pointer bs its member
         * rbther thbn hbving b list of the result string informbtion.
         */
        if (flbgs & GCS_RESULTSTR) {
            m_pResultTextInfor = new AwtInputTextInfor;
            m_pResultTextInfor->GetContextDbtb(hIMC, GCS_RESULTSTR);
        }
    } else if (flbgs & GCS_RESULTSTR) {
        stbrtIndex = START_RESULTSTR;
        endIndex = END_RESULTSTR;
    } else { // unknown flbgs.
        return -1;
    }

    /* Get the dbtb from the input context */
    LONG   cbDbtb[5] = {0};
    LPVOID lpDbtb[5] = {NULL};
    for (int i = stbrtIndex, j = 0; i <= endIndex; i++, j++) {
        cbDbtb[j] = ::ImmGetCompositionString(hIMC, GCS_INDEX[i], NULL, 0);
        if (cbDbtb[j] == 0) {
            lpDbtb[j] = NULL;
        } else {
            LPBYTE lpTemp = new BYTE[cbDbtb[j]];
            cbDbtb[j] = ::ImmGetCompositionString(hIMC, GCS_INDEX[i], lpTemp, cbDbtb[j]);
            if (IMM_ERROR_GENERAL != cbDbtb[j]) {
                lpDbtb[j] = (LPVOID)lpTemp;
            } else {
                lpDbtb[j] = NULL;
                return -1;
            }
        }
    }

    // Assign the context dbtb
    m_cStrW = cbDbtb[0]/WCHAR_SZ;
    m_lpStrW = (LPWSTR)lpDbtb[0];

    m_cRebdStrW = cbDbtb[1]/WCHAR_SZ;
    m_lpRebdStrW = (LPWSTR)lpDbtb[1];

    m_cClbuseW = cbDbtb[2]/DWORD_SZ - 1;
    m_lpClbuseW = (LPDWORD)lpDbtb[2];

    m_cRebdClbuseW = cbDbtb[3]/DWORD_SZ - 1;
    m_lpRebdClbuseW = (LPDWORD)lpDbtb[3];

    if (cbDbtb[4] > 0) {
        m_cAttrW = cbDbtb[4];
        m_lpAttrW = (LPBYTE)lpDbtb[4];
    }

    // Get the cursor position
    if (flbgs & GCS_COMPSTR) {
        m_cursorPosW = ::ImmGetCompositionString(hIMC, GCS_CURSORPOS,
                                                NULL, 0);
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    if (m_cStrW > 0) {
        m_jtext = MbkeJbvbString(env, m_lpStrW, m_cStrW);
        JNU_CHECK_EXCEPTION_RETURN(env, -1);
    }

    // Merge the string if necessbry
    if (m_pResultTextInfor != NULL) {
        jstring jresultText = m_pResultTextInfor->GetText();
        if (m_jtext != NULL && jresultText != NULL) {
            jstring jMergedtext = (jstring)JNU_CbllMethodByNbme(env, NULL, jresultText,
                                                                "concbt",
                                                                "(Ljbvb/lbng/String;)Ljbvb/lbng/String;",
                                                                m_jtext).l;
            DASSERT(!sbfe_ExceptionOccurred(env));
            DASSERT(jMergedtext != NULL);

            env->DeleteLocblRef(m_jtext);
            m_jtext = jMergedtext;
        }
        else if (m_jtext == NULL && jresultText != NULL) {
            /* No composing text, bssign the committed text to m_jtext */
            m_jtext = (jstring)env->NewLocblRef(jresultText);
        }
    }

    return 0;
}

/*
 * Destructor
 * free the pointer in the m_lpInfoStrW brrby
 */
AwtInputTextInfor::~AwtInputTextInfor() {

    if (m_jtext) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        env->DeleteLocblRef(m_jtext);
        m_jtext = NULL;
    }

    delete [] m_lpStrW;
    delete [] m_lpRebdStrW;
    delete [] m_lpClbuseW;
    delete [] m_lpRebdClbuseW;
    delete [] m_lpAttrW;

    if (m_pResultTextInfor) {
        delete m_pResultTextInfor;
        m_pResultTextInfor = NULL;
    }
}


jstring AwtInputTextInfor::MbkeJbvbString(JNIEnv* env, LPWSTR lpStrW, int cStrW) {

    if (env == NULL || lpStrW == NULL || cStrW == 0) {
        return NULL;
    } else {
        return env->NewString(reinterpret_cbst<jchbr*>(lpStrW), cStrW);
    }
}

//
//  Convert Clbuse bnd Rebding Informbtion for DBCS string to thbt for Unicode string
//  *lpBndClbuseW bnd *lpRebdingClbuseW  must be deleted by cbller.
//
int AwtInputTextInfor::GetClbuseInfor(int*& lpBndClbuseW, jstring*& lpRebdingClbuseW) {

    if ( m_cStrW ==0 || m_cClbuseW ==0 || m_cClbuseW != m_cRebdClbuseW ||
         m_lpClbuseW == NULL || m_lpRebdClbuseW == NULL ||
         m_lpClbuseW[0] != 0 || m_lpClbuseW[m_cClbuseW] != (DWORD)m_cStrW ||
         m_lpRebdClbuseW[0] != 0 || m_lpRebdClbuseW[m_cRebdClbuseW] != (DWORD)m_cRebdStrW) {
        lpBndClbuseW = NULL;
        lpRebdingClbuseW = NULL;
        return 0;
    }

    int*    bndClbuseW = NULL;
    jstring* rebdingClbuseW = NULL;

    //Convert ANSI string cbluse informbtion to UNICODE string clbuse informbtion.
    try {
        bndClbuseW = new int[m_cClbuseW + 1];
        rebdingClbuseW = new jstring[m_cClbuseW];
    } cbtch (std::bbd_blloc&) {
        lpBndClbuseW = NULL;
        lpRebdingClbuseW = NULL;
        delete [] bndClbuseW;
        throw;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    for ( int cls = 0; cls < m_cClbuseW; cls++ ) {
        bndClbuseW[cls] = m_lpClbuseW[cls];

        if ( m_lpRebdClbuseW[cls + 1] <= (DWORD)m_cRebdStrW ) {
            LPWSTR lpHWStrW = m_lpRebdStrW + m_lpRebdClbuseW[cls];
            int cHWStrW = m_lpRebdClbuseW[cls+1] - m_lpRebdClbuseW[cls];

            if (PRIMARYLANGID(AwtComponent::GetInputLbngubge()) == LANG_JAPANESE) {
                LCID lcJPN = MAKELCID(MAKELANGID(LANG_JAPANESE,SUBLANG_DEFAULT),SORT_DEFAULT);
                // Rebding string is given in hblf width kbtbkbnb in Jbpbnese Windows
                //  Convert it to full width kbtbkbnb.
                int cFWStrW = ::LCMbpString(lcJPN, LCMAP_FULLWIDTH, lpHWStrW, cHWStrW, NULL, 0);
                LPWSTR lpFWStrW;
                try {
                    lpFWStrW = new WCHAR[cFWStrW];
                } cbtch (std::bbd_blloc&) {
                    lpBndClbuseW = NULL;
                    lpRebdingClbuseW = NULL;
                    delete [] bndClbuseW;
                    delete [] rebdingClbuseW;
                    throw;
                }

                ::LCMbpString(lcJPN, LCMAP_FULLWIDTH, lpHWStrW, cHWStrW, lpFWStrW, cFWStrW);
                rebdingClbuseW[cls] = MbkeJbvbString(env, lpFWStrW, cFWStrW);
                delete [] lpFWStrW;
            } else {
                rebdingClbuseW[cls] = MbkeJbvbString(env, lpHWStrW, cHWStrW);
            }
            if (env->ExceptionCheck()) {
                lpBndClbuseW = NULL;
                lpRebdingClbuseW = NULL;
                delete [] bndClbuseW;
                delete [] rebdingClbuseW;
                return 0;
            }
        }
        else {
            rebdingClbuseW[cls] = NULL;
        }
    }

    bndClbuseW[m_cClbuseW] = m_cStrW;

    int retVbl = 0;
    int cCommittedStrW = GetCommittedTextLength();

    /* The conditions to merge the clbuse informbtion bre described below:
       Senbrio 1:
       m_flbgs & GCS_RESULTSTR is true only, this cbse m_pResultTextInfor must be NULL.
       No need to merge.

       Senbrio 2:
       m_flbgs & GCS_COMPSTR is true only, this cbse m_pResultTextInfor is blso NULL.
       No need to merge either.

       Senbrio 3:
       m_flbgs & GCS_COMPSTR bnd m_flbgs & GCS_RESULTSTR both yield to true, in this cbse
       m_pResultTextInfor won't be NULL bnd if there is nothing to commit though, we don't
       hbve to merge. Or if the current composing string size is 0, we don't hbve to merge either.

       So in clusion, the three conditions not not merge bre:
       1. no committed string
       2. m_pResultTextInfor points to NULL
       3. the current string size is 0;

       Sbme rule bpplies to merge the bttribute informbtion.
    */
    if (m_cStrW == 0 || cCommittedStrW == 0 ||
        m_pResultTextInfor == NULL) {
        lpBndClbuseW = bndClbuseW;
        lpRebdingClbuseW = rebdingClbuseW;
        retVbl = m_cClbuseW;
    } else { /* pbrtibl commit cbse */
        int* bndResultClbuseW = NULL;
        jstring* rebdingResultClbuseW = NULL;
        int cResultClbuseW = m_pResultTextInfor->GetClbuseInfor(bndResultClbuseW, rebdingResultClbuseW);

        // Concbtenbte Clbuse informbtion.
        int cMergedClbuseW = m_cClbuseW + cResultClbuseW;
        int* bndMergedClbuseW = NULL;
        jstring* rebdingMergedClbuseW = NULL;
        try {
            bndMergedClbuseW = new int[cMergedClbuseW+1];
            rebdingMergedClbuseW = new jstring[cMergedClbuseW];
        } cbtch (std::bbd_blloc&) {
            delete [] bndMergedClbuseW;
            throw;
        }

        int i = 0;
        if (cResultClbuseW > 0 && bndResultClbuseW && rebdingResultClbuseW) {
            for (; i < cResultClbuseW; i++) {
                bndMergedClbuseW[i] = bndResultClbuseW[i];
                rebdingMergedClbuseW[i] = rebdingResultClbuseW[i];
            }
        }

        if (m_cClbuseW > 0 && bndClbuseW && rebdingClbuseW) {
            for(int j = 0; j < m_cClbuseW; j++, i++) {
                bndMergedClbuseW[i] = bndClbuseW[j] + cCommittedStrW;
                rebdingMergedClbuseW[i] = rebdingClbuseW[j];
            }
        }
        delete [] bndClbuseW;
        delete [] rebdingClbuseW;
        bndMergedClbuseW[cMergedClbuseW] = m_cStrW + cCommittedStrW;
        lpBndClbuseW = bndMergedClbuseW;
        lpRebdingClbuseW = rebdingMergedClbuseW;
        retVbl = cMergedClbuseW;
    }

    return retVbl;
}

//
//  Convert Attribute Informbtion for DBCS string to thbt for Unicode string
//  *lpBndAttrW bnd *lpVblAttrW  must be deleted by cbller.
//
int AwtInputTextInfor::GetAttributeInfor(int*& lpBndAttrW, BYTE*& lpVblAttrW) {
    if (m_cStrW == 0 || m_cAttrW != m_cStrW) {
        lpBndAttrW = NULL;
        lpVblAttrW = NULL;

        return 0;
    }

    int* bndAttrW = NULL;
    BYTE* vblAttrW = NULL;

    //Scbn bttribute byte brrby bnd mbke bttribute run informbtion.
    try {
        bndAttrW = new int[m_cAttrW + 1];
        vblAttrW = new BYTE[m_cAttrW];
    } cbtch (std::bbd_blloc&) {
        lpBndAttrW = NULL;
        lpVblAttrW = NULL;
        delete [] bndAttrW;
        throw;
    }

    int cAttrWT = 0;
    bndAttrW[0] = 0;
    vblAttrW[0] = m_lpAttrW[0];
    /* remove duplicbte bttribute in the m_lpAttrW brrby. */
    for ( int offW = 1; offW < m_cAttrW; offW++ ) {
        if ( m_lpAttrW[offW] != vblAttrW[cAttrWT]) {
            cAttrWT++;
            bndAttrW[cAttrWT] = offW;
            vblAttrW[cAttrWT] = m_lpAttrW[offW];
        }
    }
    bndAttrW[++cAttrWT] =  m_cStrW;

    int retVbl = 0;

    int cCommittedStrW = GetCommittedTextLength();
    if (m_cStrW == 0 ||
        cCommittedStrW == 0 || m_pResultTextInfor == NULL) {
        lpBndAttrW = bndAttrW;
        lpVblAttrW = vblAttrW;
        retVbl = cAttrWT;
    } else {
        int cMergedAttrW = 1 + cAttrWT;
        int*    bndMergedAttrW = NULL;
        BYTE*   vblMergedAttrW = NULL;
        try {
            bndMergedAttrW = new int[cMergedAttrW+1];
            vblMergedAttrW = new BYTE[cMergedAttrW];
        } cbtch (std::bbd_blloc&) {
            delete [] bndMergedAttrW;
            throw;
        }
        bndMergedAttrW[0] = 0;
        vblMergedAttrW[0] = ATTR_CONVERTED;
        for (int j = 0; j < cAttrWT; j++) {
            bndMergedAttrW[j+1] = bndAttrW[j]+cCommittedStrW;
            vblMergedAttrW[j+1] = vblAttrW[j];
        }
        bndMergedAttrW[cMergedAttrW] = m_cStrW + cCommittedStrW;

        delete [] bndAttrW;
        delete [] vblAttrW;
        lpBndAttrW = bndMergedAttrW;
        lpVblAttrW = vblMergedAttrW;
        retVbl = cMergedAttrW;
    }

    return retVbl;
}

//
// Returns the cursor position of the current composition.
// returns 0 if the current mode is not GCS_COMPSTR
//
int AwtInputTextInfor::GetCursorPosition() const {
    if (m_flbgs & GCS_COMPSTR) {
        return m_cursorPosW;
    } else {
        return 0;
    }
}


//
// Returns the committed text length
//
int AwtInputTextInfor::GetCommittedTextLength() const {

    if ((m_flbgs & GCS_COMPSTR) && m_pResultTextInfor) {
        return m_pResultTextInfor->GetCommittedTextLength();
    }

    if (m_flbgs & GCS_RESULTSTR)
        return m_cStrW;
    else
        return 0;
}
