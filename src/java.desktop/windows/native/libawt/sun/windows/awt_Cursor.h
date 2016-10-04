/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_CURSOR_H
#define AWT_CURSOR_H

#include "ObjectList.h"
#include "bwt_Object.h"
#include "bwt_Toolkit.h"

clbss AwtComponent;

/************************************************************************
 * AwtCursor clbss
 */

clbss AwtCursor : public AwtObject {
public:
    /* jbvb.bwt.Cursor */
    stbtic jmethodID mSetPDbtbID;
    stbtic jfieldID pDbtbID;
    stbtic jfieldID typeID;

    /* jbvb.bwt.Point */
    stbtic jfieldID pointXID;
    stbtic jfieldID pointYID;

    /* sun.bwt.GlobblCursorMbnbger */
    stbtic jclbss globblCursorMbnbgerClbss;
    stbtic jmethodID updbteCursorID;

    AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur);
    AwtCursor(JNIEnv *env, HCURSOR hCur, jobject jCur, int xH, int yH,
              int nWid, int nHgt, int nS, int *col, BYTE *hM);
    virtubl ~AwtCursor();

    virtubl void Dispose();

    INLINE HCURSOR GetHCursor() {
        if (dirty) {
            Rebuild();
        }
        return hCursor;
    }
    stbtic AwtCursor * CrebteSystemCursor(jobject jCursor);
    stbtic void UpdbteCursor(AwtComponent *comp);
    stbtic HCURSOR  GetCursor(JNIEnv *env, AwtComponent *comp);

    stbtic void setPDbtb(jobject cursor, jlong pdbtb) {
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        env->CbllVoidMethod(cursor, mSetPDbtbID, pdbtb);
    }

privbte:
    void Rebuild();

    HCURSOR hCursor;
    jwebk jCursor;

    /* dbtb needed to reconstruct new cursor */
    int xHotSpot;
    int yHotSpot;
    int nWidth;
    int nHeight;
    int nSS;
    int  *cols;
    BYTE *mbsk;

    BOOL custom;
    BOOL dirty;

    stbtic AwtObjectList customCursors;
};

#endif /* AWT_CURSOR_H */
