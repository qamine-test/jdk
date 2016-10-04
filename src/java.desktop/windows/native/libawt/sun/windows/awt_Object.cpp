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

#include "bwt_Object.h"
#include "ObjectList.h"

#ifdef DEBUG
stbtic BOOL reportEvents = FALSE;
#endif


/************************************************************************
 * AwtObject fields
 */

jfieldID AwtObject::pDbtbID;
jfieldID AwtObject::destroyedID;
jfieldID AwtObject::tbrgetID;
jclbss AwtObject::wObjectPeerClbss;
jmethodID AwtObject::getPeerForTbrgetMID;
jfieldID AwtObject::crebteErrorID;


/************************************************************************
 * AwtObject methods
 */

AwtObject::AwtObject()
{
    theAwtObjectList.Add(this);
    m_peerObject = NULL;
    m_cbllbbcksEnbbled = TRUE;
}

AwtObject::~AwtObject()
{
}

void AwtObject::Dispose()
{
    AwtToolkit::GetInstbnce().PostMessbge(WM_AWT_DELETEOBJECT, (WPARAM)this, (LPARAM)0);
}

void AwtObject::_Dispose(jobject self)
{
    TRY_NO_VERIFY;

    CriticblSection::Lock l(AwtToolkit::GetInstbnce().GetSyncCS());

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject selfGlobblRef = env->NewGlobblRef(self);

    // vblue 0 of lPbrbm mebns thbt we should not bttempt to enter the
    // SyncCbll criticbl section, bs it wbs entered someshere ebrlier
    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_DISPOSE, (WPARAM)selfGlobblRef, (LPARAM)0);

    CATCH_BAD_ALLOC;
}

void AwtObject::_Dispose(PDATA pDbtb)
{
    TRY_NO_VERIFY;

    CriticblSection::Lock l(AwtToolkit::GetInstbnce().GetSyncCS());

    AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_DISPOSEPDATA, (WPARAM)pDbtb, (LPARAM)0);

    CATCH_BAD_ALLOC;
}
/*
 * Return the peer bssocibted with some tbrget.  This informbtion is
 * mbintbined in b hbshtbble bt the jbvb level.
 */
jobject AwtObject::GetPeerForTbrget(JNIEnv *env, jobject tbrget)
{
    jobject result =
        env->CbllStbticObjectMethod(AwtObject::wObjectPeerClbss,
                                    AwtObject::getPeerForTbrgetMID,
                                    tbrget);

    DASSERT(!sbfe_ExceptionOccurred(env));
    return result;
}

/* Execute b cbllbbck to the bssocibted Jbvb peer. */
void
AwtObject::DoCbllbbck(const chbr* methodNbme, const chbr* methodSig, ...)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    /* don't cbllbbck during the crebte & initiblizbtion process */
    if (m_peerObject != NULL && m_cbllbbcksEnbbled) {
        vb_list brgs;
        vb_stbrt(brgs, methodSig);
#ifdef DEBUG
        if (reportEvents) {
            jstring tbrgetStr =
                (jstring)JNU_CbllMethodByNbme(env, NULL, GetTbrget(env),
                                              "getNbme",
                                              "()Ljbvb/lbng/String;").l;
            DASSERT(!sbfe_ExceptionOccurred(env));
            LPCWSTR tbrgetStrW = JNU_GetStringPlbtformChbrs(env, tbrgetStr, NULL);
            printf("Posting %s%s method to %S\n", methodNbme, methodSig, tbrgetStrW);
            JNU_RelebseStringPlbtformChbrs(env, tbrgetStr, tbrgetStrW);
        }
#endif
        /* cbching would do much good here */
        JNU_CbllMethodByNbmeV(env, NULL, GetPeer(env),
                              methodNbme, methodSig, brgs);
        {
            jthrowbble exc = sbfe_ExceptionOccurred(env);
            if (exc) {
                env->DeleteLocblRef(exc);
                env->ExceptionDescribe();
                env->ExceptionClebr();
            }
        }
        DASSERT(!sbfe_ExceptionOccurred(env));
        vb_end(brgs);
    }
}

void AwtObject::SendEvent(jobject event)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

#ifdef DEBUG
    if (reportEvents) {
        jstring eventStr = JNU_ToString(env, event);
        DASSERT(!sbfe_ExceptionOccurred(env));
        jstring tbrgetStr =
            (jstring)JNU_CbllMethodByNbme(env, NULL, GetTbrget(env),"getNbme",
                                          "()Ljbvb/lbng/String;").l;
        DASSERT(!sbfe_ExceptionOccurred(env));
        LPCWSTR eventStrW = JNU_GetStringPlbtformChbrs(env, eventStr, NULL);
        LPCWSTR tbrgetStrW = JNU_GetStringPlbtformChbrs(env, tbrgetStr, NULL);
        printf("Posting %S to %S\n", eventStrW, tbrgetStrW);
        JNU_RelebseStringPlbtformChbrs(env, eventStr, eventStrW);
        JNU_RelebseStringPlbtformChbrs(env, tbrgetStr, tbrgetStrW);
    }
#endif
    /* Post event to the system EventQueue. */
    JNU_CbllMethodByNbme(env, NULL, GetPeer(env), "postEvent",
                         "(Ljbvb/bwt/AWTEvent;)V", event);
    {
        jthrowbble exc = sbfe_ExceptionOccurred(env);
        if (exc) {
            env->DeleteLocblRef(exc);
            env->ExceptionDescribe();
        }
    }
    DASSERT(!sbfe_ExceptionOccurred(env));
}

//
// (stbtic)
// Switches to Windows threbd vib SendMessbge bnd synchronously
// cblls AwtObject::WinThrebdExecProc with the given commbnd id
// bnd pbrbmeters.
//
// Useful for writing code thbt needs to be synchronized with
// whbt's hbppening on the Windows threbd.
//
LRESULT AwtObject::WinThrebdExec(
    jobject                             peerObject,
    UINT                                cmdId,
    LPARAM                              pbrbm1,
    LPARAM                              pbrbm2,
    LPARAM                              pbrbm3,
    LPARAM                              pbrbm4 )
{
    DASSERT( peerObject != NULL);

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    // since we pbss peerObject to bnother threbd we must
    //   mbke b globbl ref
    jobject peerObjectGlobblRef = env->NewGlobblRef(peerObject);

    ExecuteArgs         brgs;
    LRESULT         retVbl;

    // setup brguments
    brgs.cmdId = cmdId;
    brgs.pbrbm1 = pbrbm1;
    brgs.pbrbm2 = pbrbm2;
    brgs.pbrbm3 = pbrbm3;
    brgs.pbrbm4 = pbrbm4;

    // cbll WinThrebdExecProc on the toolkit threbd
    retVbl = AwtToolkit::GetInstbnce().SendMessbge(WM_AWT_EXECUTE_SYNC,
                                                   (WPARAM)peerObjectGlobblRef,
                                                   (LPARAM)&brgs);
    return retVbl;
}

LRESULT AwtObject::WinThrebdExecProc(ExecuteArgs * brgs)
{
    DASSERT(FALSE); // no defbult hbndler
    return 0L;
}

/************************************************************************
 * WObjectPeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WObjectPeer_initIDs(JNIEnv *env, jclbss cls) {
    TRY;

    AwtObject::wObjectPeerClbss = (jclbss)env->NewGlobblRef(cls);
    DASSERT(AwtObject::wObjectPeerClbss != NULL);
    CHECK_NULL(AwtObject::wObjectPeerClbss);

    AwtObject::pDbtbID = env->GetFieldID(cls, "pDbtb", "J");
    DASSERT(AwtObject::pDbtbID != NULL);
    CHECK_NULL(AwtObject::pDbtbID);

    AwtObject::destroyedID = env->GetFieldID(cls, "destroyed", "Z");
    DASSERT(AwtObject::destroyedID != NULL);
    CHECK_NULL(AwtObject::destroyedID);

    AwtObject::tbrgetID = env->GetFieldID(cls, "tbrget",
                                              "Ljbvb/lbng/Object;");
    DASSERT(AwtObject::tbrgetID != NULL);
    CHECK_NULL(AwtObject::tbrgetID);

    AwtObject::getPeerForTbrgetMID =
        env->GetStbticMethodID(cls, "getPeerForTbrget",
                         "(Ljbvb/lbng/Object;)Lsun/bwt/windows/WObjectPeer;");
    DASSERT(AwtObject::getPeerForTbrgetMID != NULL);
    CHECK_NULL(AwtObject::getPeerForTbrgetMID);

    AwtObject::crebteErrorID = env->GetFieldID(cls, "crebteError", "Ljbvb/lbng/Error;");
    DASSERT(AwtObject::crebteErrorID != NULL);
    CHECK_NULL(AwtObject::crebteErrorID);

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
