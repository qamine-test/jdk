/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "ThrebdReferenceImpl.h"
#include "eventHbndler.h"
#include "threbdControl.h"
#include "inStrebm.h"
#include "outStrebm.h"
#include "FrbmeID.h"

stbtic jboolebn
nbme(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiThrebdInfo info;
        jvmtiError error;

        (void)memset(&info, 0, sizeof(info));

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdInfo)
                                (gdbtb->jvmti, threbd, &info);

        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            (void)outStrebm_writeString(out, info.nbme);
        }

        if ( info.nbme != NULL )
            jvmtiDebllocbte(info.nbme);

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
suspend(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }
    error = threbdControl_suspendThrebd(threbd, JNI_FALSE);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
resume(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    /* true mebns it is okby to unblock the commbndLoop threbd */
    error = threbdControl_resumeThrebd(threbd, JNI_TRUE);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
stbtus(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jdwpThrebdStbtus threbdStbtus;
    jint stbtusFlbgs;
    jvmtiError error;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    error = threbdControl_bpplicbtionThrebdStbtus(threbd, &threbdStbtus,
                                                          &stbtusFlbgs);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }
    (void)outStrebm_writeInt(out, threbdStbtus);
    (void)outStrebm_writeInt(out, stbtusFlbgs);
    return JNI_TRUE;
}

stbtic jboolebn
threbdGroup(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiThrebdInfo info;
        jvmtiError error;

        (void)memset(&info, 0, sizeof(info));

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdInfo)
                                (gdbtb->jvmti, threbd, &info);

        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            (void)outStrebm_writeObjectRef(env, out, info.threbd_group);
        }

        if ( info.nbme!=NULL )
            jvmtiDebllocbte(info.nbme);

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
vblidbteSuspendedThrebd(PbcketOutputStrebm *out, jthrebd threbd)
{
    jvmtiError error;
    jint count;

    error = threbdControl_suspendCount(threbd, &count);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_FALSE;
    }

    if (count == 0) {
        outStrebm_setError(out, JDWP_ERROR(THREAD_NOT_SUSPENDED));
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

stbtic jboolebn
frbmes(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    FrbmeNumber fnum;
    jint count;
    JNIEnv *env;
    jthrebd threbd;
    jint stbrtIndex;
    jint length;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    stbrtIndex = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    length = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    if (!vblidbteSuspendedThrebd(out, threbd)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeCount)
                        (gdbtb->jvmti, threbd, &count);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    if (length == -1) {
        length = count - stbrtIndex;
    }

    if (length == 0) {
        (void)outStrebm_writeInt(out, 0);
        return JNI_TRUE;
    }

    if ((stbrtIndex < 0) || (stbrtIndex > count - 1)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_INDEX));
        return JNI_TRUE;
    }

    if ((length < 0) || (length + stbrtIndex > count)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_LENGTH));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, length);

    for(fnum = stbrtIndex ; fnum < stbrtIndex+length ; fnum++ ) {

        WITH_LOCAL_REFS(env, 1) {

            jclbss clbzz;
            jmethodID method;
            jlocbtion locbtion;

            /* Get locbtion info */
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, fnum, &method, &locbtion);
            if (error == JVMTI_ERROR_OPAQUE_FRAME) {
                clbzz = NULL;
                locbtion = -1L;
                error = JVMTI_ERROR_NONE;
            } else if ( error == JVMTI_ERROR_NONE ) {
                error = methodClbss(method, &clbzz);
                if ( error == JVMTI_ERROR_NONE ) {
                    FrbmeID frbme;
                    frbme = crebteFrbmeID(threbd, fnum);
                    (void)outStrebm_writeFrbmeID(out, frbme);
                    writeCodeLocbtion(out, clbzz, method, locbtion);
                }
            }

        } END_WITH_LOCAL_REFS(env);

        if (error != JVMTI_ERROR_NONE)
            brebk;

    }

    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
getFrbmeCount(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jint count;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    if (!vblidbteSuspendedThrebd(out, threbd)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeCount)
                        (gdbtb->jvmti, threbd, &count);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }
    (void)outStrebm_writeInt(out, count);

    return JNI_TRUE;
}

stbtic jboolebn
ownedMonitors(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    if (!vblidbteSuspendedThrebd(out, threbd)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jint count = 0;
        jobject *monitors = NULL;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetOwnedMonitorInfo)
                                (gdbtb->jvmti, threbd, &count, &monitors);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;
            (void)outStrebm_writeInt(out, count);
            for (i = 0; i < count; i++) {
                jobject monitor = monitors[i];
                (void)outStrebm_writeByte(out, specificTypeKey(env, monitor));
                (void)outStrebm_writeObjectRef(env, out, monitor);
            }
        }
        if (monitors != NULL)
            jvmtiDebllocbte(monitors);

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
currentContendedMonitor(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbd == NULL || threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    if (!vblidbteSuspendedThrebd(out, threbd)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jobject monitor;
        jvmtiError error;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetCurrentContendedMonitor)
                                (gdbtb->jvmti, threbd, &monitor);

        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            (void)outStrebm_writeByte(out, specificTypeKey(env, monitor));
            (void)outStrebm_writeObjectRef(env, out, monitor);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
stop(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jthrebd threbd;
    jobject throwbble;
    JNIEnv *env;

    env = getEnv();
    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    throwbble = inStrebm_rebdObjectRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    error = threbdControl_stop(threbd, throwbble);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
interrupt(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    error = threbdControl_interrupt(threbd);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
suspendCount(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jint count;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    error = threbdControl_suspendCount(threbd, &count);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, count);
    return JNI_TRUE;
}

stbtic jboolebn
ownedMonitorsWithStbckDepth(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbd == NULL || threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    if (!vblidbteSuspendedThrebd(out, threbd)) {
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error = JVMTI_ERROR_NONE;
        jint count = 0;
        jvmtiMonitorStbckDepthInfo *monitors=NULL;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetOwnedMonitorStbckDepthInfo)
                                (gdbtb->jvmti, threbd, &count, &monitors);

        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;
            (void)outStrebm_writeInt(out, count);
            for (i = 0; i < count; i++) {
                jobject monitor = monitors[i].monitor;
                (void)outStrebm_writeByte(out, specificTypeKey(env, monitor));
                (void)outStrebm_writeObjectRef(getEnv(), out, monitor);
                (void)outStrebm_writeInt(out,monitors[i].stbck_depth);
            }
        }
        if (monitors != NULL) {
            jvmtiDebllocbte(monitors);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
forceEbrlyReturn(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jthrebd threbd;
    jvblue vblue;
    jbyte typeKey;
    jvmtiError error;

    env = getEnv();
    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    typeKey = inStrebm_rebdByte(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (isObjectTbg(typeKey)) {
        vblue.l = inStrebm_rebdObjectRef(env, in);
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnObject)
                        (gdbtb->jvmti, threbd, vblue.l);
    } else {
        switch (typeKey) {
            cbse JDWP_TAG(VOID):
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnVoid)
                                (gdbtb->jvmti, threbd);
                brebk;
            cbse JDWP_TAG(BYTE):
                vblue.b = inStrebm_rebdByte(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnInt)
                                (gdbtb->jvmti, threbd, vblue.b);
                brebk;

            cbse JDWP_TAG(CHAR):
                vblue.c = inStrebm_rebdChbr(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnInt)
                                (gdbtb->jvmti, threbd, vblue.c);
                brebk;

            cbse JDWP_TAG(FLOAT):
                vblue.f = inStrebm_rebdFlobt(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnFlobt)
                                (gdbtb->jvmti, threbd, vblue.f);
                brebk;

            cbse JDWP_TAG(DOUBLE):
                vblue.d = inStrebm_rebdDouble(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnDouble)
                                (gdbtb->jvmti, threbd, vblue.d);
                brebk;

            cbse JDWP_TAG(INT):
                vblue.i = inStrebm_rebdInt(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnInt)
                                (gdbtb->jvmti, threbd, vblue.i);
                brebk;

            cbse JDWP_TAG(LONG):
                vblue.j = inStrebm_rebdLong(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnLong)
                                (gdbtb->jvmti, threbd, vblue.j);
                brebk;

            cbse JDWP_TAG(SHORT):
                vblue.s = inStrebm_rebdShort(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnInt)
                                (gdbtb->jvmti, threbd, vblue.s);
                brebk;

            cbse JDWP_TAG(BOOLEAN):
                vblue.z = inStrebm_rebdBoolebn(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,ForceEbrlyReturnInt)
                                (gdbtb->jvmti, threbd, vblue.z);
                brebk;

            defbult:
                error =  AGENT_ERROR_INVALID_TAG;
                brebk;
        }
    }
    {
      jdwpError serror = mbp2jdwpError(error);
      if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
      }
    }
    return JNI_TRUE;
}


void *ThrebdReference_Cmds[] = { (void *)14,
    (void *)nbme,
    (void *)suspend,
    (void *)resume,
    (void *)stbtus,
    (void *)threbdGroup,
    (void *)frbmes,
    (void *)getFrbmeCount,
    (void *)ownedMonitors,
    (void *)currentContendedMonitor,
    (void *)stop,
    (void *)interrupt,
    (void *)suspendCount,
    (void *)ownedMonitorsWithStbckDepth,
    (void *)forceEbrlyReturn
    };
