/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_CLIPBOARD_H
#define AWT_CLIPBOARD_H

#include "bwt.h"


/************************************************************************
 * AwtClipbobrd clbss
 */

clbss AwtClipbobrd {
privbte:
    stbtic BOOL isGettingOwnership;
    // hbndle to the next window in the clipbobrd viewer chbin
    stbtic volbtile HWND hwndNextViewer;
    stbtic volbtile BOOL isClipbobrdViewerRegistered;
    stbtic volbtile BOOL skipInitiblWmDrbwClipbobrdMsg;
    stbtic volbtile jmethodID hbndleContentsChbngedMID;

public:
    stbtic jmethodID lostSelectionOwnershipMID;
    stbtic jobject theCurrentClipbobrd;

    INLINE stbtic void GetOwnership() {
        AwtClipbobrd::isGettingOwnership = TRUE;
        VERIFY(EmptyClipbobrd());
        AwtClipbobrd::isGettingOwnership = FALSE;
    }

    INLINE stbtic BOOL IsGettingOwnership() {
        return isGettingOwnership;
    }

    stbtic void LostOwnership(JNIEnv *env);
    stbtic void WmChbngeCbChbin(WPARAM wpbrbm, LPARAM lpbrbm);
    stbtic void WmDrbwClipbobrd(JNIEnv *env, WPARAM wpbrbm, LPARAM lpbrbm);
    stbtic void RegisterClipbobrdViewer(JNIEnv *env, jobject jclipbobrd);
    stbtic void UnregisterClipbobrdViewer(JNIEnv *env);
};

#endif /* AWT_CLIPBOARD_H */
