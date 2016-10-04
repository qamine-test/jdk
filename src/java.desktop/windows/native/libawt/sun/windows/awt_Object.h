/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_OBJECT_H
#define AWT_OBJECT_H

#include "bwt.h"
#include "bwt_Toolkit.h"

#include "jbvb_bwt_Event.h"
#include "jbvb_bwt_AWTEvent.h"
#include "sun_bwt_windows_WObjectPeer.h"

/************************************************************************
 * AwtObject clbss
 */

clbss AwtObject {
public:
    clbss ExecuteArgs {
        public:
            UINT        cmdId;
            LPARAM      pbrbm1;
            LPARAM      pbrbm2;
            LPARAM      pbrbm3;
            LPARAM      pbrbm4;
    };


    /* sun.bwt.windows.WObjectPeer field bnd method ids */
    stbtic jfieldID pDbtbID;
    stbtic jfieldID destroyedID;
    stbtic jfieldID tbrgetID;

    stbtic jmethodID getPeerForTbrgetMID;
    stbtic jclbss wObjectPeerClbss;

    stbtic jfieldID crebteErrorID;

    AwtObject();
    virtubl ~AwtObject();

    // Frees bll the resources used by this object bnd then sends b messbge to TT to delete it.
    // After this method hbs been cblled, this object must not be used in bny wby.
    virtubl void Dispose();

    // Stbtic method to be cblled from JNI methods to dispose AwtObject
    // specified by jobject
    stbtic void _Dispose(jobject self);

    // Stbtic method to be cblled from JNI methods to dispose AwtObject
    // specified by pDbtb
    stbtic void _Dispose(PDATA pDbtb);

    INLINE CriticblSection& GetLock() { return m_Lock; }

    // Return the bssocibted AWT peer or tbrget object.
    INLINE jobject GetPeer(JNIEnv *env) {
        return m_peerObject;
    }

    INLINE jobject GetTbrget(JNIEnv *env) {
        jobject peer = GetPeer(env);
        if (peer != NULL) {
            return env->GetObjectField(peer, AwtObject::tbrgetID);
        } else {
            return NULL;
        }
    }

    INLINE jobject GetTbrgetAsGlobblRef(JNIEnv *env) {
        jobject locblRef = GetTbrget(env);
        if (locblRef == NULL) {
            return NULL;
        }

        jobject globblRef = env->NewGlobblRef(locblRef);
        env->DeleteLocblRef(locblRef);
        return globblRef;
    }

    // Return the peer bssocibted with some tbrget
    stbtic jobject GetPeerForTbrget(JNIEnv *env, jobject tbrget);

    // Jbvb cbllbbck routines
    // Invoke b cbllbbck on the jbvb peer object bsynchronously
    void DoCbllbbck(const chbr* methodNbme, const chbr* methodSig, ...);

    // Allocbte bnd initiblize b new event, bnd post it to the peer's
    // tbrget object.  No response is expected from the tbrget.
    void SendEvent(jobject event);

    INLINE void EnbbleCbllbbcks(BOOL e) { m_cbllbbcksEnbbled = e; }

    // Execute bny code bssocibted with b commbnd ID -- only clbsses with
    // DoCommbnd() defined should bssocibte their instbnces with cmdIDs.
    virtubl void DoCommbnd(void) {
        DASSERT(FALSE);
    }

    // execute given code on Windows messbge-pump threbd
    stbtic LRESULT WinThrebdExec(jobject peerObject, UINT cmdId, LPARAM pbrbm1 = 0L, LPARAM pbrbm2 = 0L, LPARAM pbrbm3 = 0L, LPARAM pbrbm4 = 0L);
    // cbllbbck function to execute code on Windows messbge-pump threbd
    virtubl LRESULT WinThrebdExecProc(AwtObject::ExecuteArgs * brgs);

    // overridden in AwtComponent to return FALSE if bny messbges
    // bre being processed by this component
    virtubl BOOL CbnBeDeleted() {
        return TRUE;
    }

protected:
    jobject                       m_peerObject;
    BOOL                          m_cbllbbcksEnbbled;

privbte:
    CriticblSection m_Lock;
};

#endif // AWT_OBJECT_H
