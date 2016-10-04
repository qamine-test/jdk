/*
 * Copyright (c) 1998, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_AWTEvent.h"
#include "bwt_Component.h"
#include <jbvb_bwt_AWTEvent.h>

/************************************************************************
 * AwtAWTEvent fields
 */

jfieldID AwtAWTEvent::bdbtbID;
jfieldID AwtAWTEvent::idID;
jfieldID AwtAWTEvent::consumedID;

/************************************************************************
 * AwtAWTEvent stbtic methods
 */

void AwtAWTEvent::sbveMSG(JNIEnv *env, MSG *pMsg, jobject jevent)
{
    if (env->EnsureLocblCbpbcity(1) < 0) {
        return;
    }
    jbyteArrby bdbtb = env->NewByteArrby(sizeof(MSG));
    if(bdbtb == 0) {
        throw std::bbd_blloc();
    }
    env->SetByteArrbyRegion(bdbtb, 0, sizeof(MSG), (jbyte *)pMsg);
    DASSERT(AwtAWTEvent::bdbtbID);
    env->SetObjectField(jevent, AwtAWTEvent::bdbtbID,  bdbtb);
    env->DeleteLocblRef(bdbtb);
}

/************************************************************************
 * AwtEvent nbtive methods
 */

extern "C" {

/*
 * Clbss:     jbvb_bwt_AWTEvent
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvent_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtAWTEvent::bdbtbID = env->GetFieldID(cls, "bdbtb", "[B");
    DASSERT(AwtAWTEvent::bdbtbID != NULL);
    CHECK_NULL(AwtAWTEvent::bdbtbID);

    AwtAWTEvent::idID = env->GetFieldID(cls, "id", "I");
    DASSERT(AwtAWTEvent::idID != NULL);
    CHECK_NULL(AwtAWTEvent::idID);

    AwtAWTEvent::consumedID = env->GetFieldID(cls, "consumed", "Z");
    DASSERT(AwtAWTEvent::consumedID != NULL);
    CHECK_NULL(AwtAWTEvent::consumedID);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     jbvb_bwt_AWTEvent
 * Method:    nbtiveSetSource
 * Signbture: (Ljbvb/bwt/peer/ComponentPeer;)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_AWTEvent_nbtiveSetSource
    (JNIEnv *env, jobject self, jobject newSource)
{
    TRY;

    JNI_CHECK_NULL_RETURN(self, "null AWTEvent");

    MSG *pMsg;

    jbyteArrby bdbtb = (jbyteArrby)
        env->GetObjectField(self, AwtAWTEvent::bdbtbID);
    if (bdbtb != NULL) {
        jboolebn dummy;
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN(newSource);
        AwtComponent *p = (AwtComponent *)pDbtb;
        HWND hwnd = p->GetHWnd();

        pMsg = (MSG *)env->GetPrimitiveArrbyCriticbl(bdbtb, &dummy);
        if (pMsg == NULL) {
            throw std::bbd_blloc();
        }
        pMsg->hwnd = hwnd;
        env->RelebsePrimitiveArrbyCriticbl(bdbtb, (void *)pMsg, 0);
    }

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
