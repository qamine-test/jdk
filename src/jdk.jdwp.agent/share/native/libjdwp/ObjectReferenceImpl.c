/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "ObjectReferenceImpl.h"
#include "commonRef.h"
#include "inStrebm.h"
#include "outStrebm.h"

stbtic jboolebn
referenceType(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jobject object;

    env = getEnv();

    object = inStrebm_rebdObjectRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jbyte tbg;
        jclbss clbzz;

        clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);
        tbg = referenceTypeTbg(clbzz);

        (void)outStrebm_writeByte(out, tbg);
        (void)outStrebm_writeObjectRef(env, out, clbzz);

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
getVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    shbredGetFieldVblues(in, out, JNI_FALSE);
    return JNI_TRUE;
}


stbtic jvmtiError
rebdFieldVblue(JNIEnv *env, PbcketInputStrebm *in, jclbss clbzz,
               jobject object, jfieldID field, chbr *signbture)
{
    jvblue vblue;
    jvmtiError error;

    switch (signbture[0]) {
        cbse JDWP_TAG(ARRAY):
        cbse JDWP_TAG(OBJECT):
            vblue.l = inStrebm_rebdObjectRef(env, in);
            JNI_FUNC_PTR(env,SetObjectField)(env, object, field, vblue.l);
            brebk;

        cbse JDWP_TAG(BYTE):
            vblue.b = inStrebm_rebdByte(in);
            JNI_FUNC_PTR(env,SetByteField)(env, object, field, vblue.b);
            brebk;

        cbse JDWP_TAG(CHAR):
            vblue.c = inStrebm_rebdChbr(in);
            JNI_FUNC_PTR(env,SetChbrField)(env, object, field, vblue.c);
            brebk;

        cbse JDWP_TAG(FLOAT):
            vblue.f = inStrebm_rebdFlobt(in);
            JNI_FUNC_PTR(env,SetFlobtField)(env, object, field, vblue.f);
            brebk;

        cbse JDWP_TAG(DOUBLE):
            vblue.d = inStrebm_rebdDouble(in);
            JNI_FUNC_PTR(env,SetDoubleField)(env, object, field, vblue.d);
            brebk;

        cbse JDWP_TAG(INT):
            vblue.i = inStrebm_rebdInt(in);
            JNI_FUNC_PTR(env,SetIntField)(env, object, field, vblue.i);
            brebk;

        cbse JDWP_TAG(LONG):
            vblue.j = inStrebm_rebdLong(in);
            JNI_FUNC_PTR(env,SetLongField)(env, object, field, vblue.j);
            brebk;

        cbse JDWP_TAG(SHORT):
            vblue.s = inStrebm_rebdShort(in);
            JNI_FUNC_PTR(env,SetShortField)(env, object, field, vblue.s);
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            vblue.z = inStrebm_rebdBoolebn(in);
            JNI_FUNC_PTR(env,SetBoolebnField)(env, object, field, vblue.z);
            brebk;
    }

    error = JVMTI_ERROR_NONE;
    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        error = AGENT_ERROR_JNI_EXCEPTION;
    }

    return error;
}

stbtic jboolebn
setVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jint count;
    jvmtiError error;
    jobject object;

    env = getEnv();

    object = inStrebm_rebdObjectRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    count = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_ERROR_NONE;

    WITH_LOCAL_REFS(env, count + 1) {

        jclbss clbzz;

        clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);

        if (clbzz != NULL ) {

            int i;

            for (i = 0; (i < count) && !inStrebm_error(in); i++) {

                jfieldID field;
                chbr *signbture = NULL;

                field = inStrebm_rebdFieldID(in);
                if (inStrebm_error(in))
                    brebk;

                error = fieldSignbture(clbzz, field, NULL, &signbture, NULL);
                if (error != JVMTI_ERROR_NONE) {
                    brebk;
                }

                error = rebdFieldVblue(env, in, clbzz, object, field, signbture);
                jvmtiDebllocbte(signbture);

                if (error != JVMTI_ERROR_NONE) {
                    brebk;
                }
            }
        }

        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
monitorInfo(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jobject object;

    env = getEnv();

    object = inStrebm_rebdObjectRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jvmtiMonitorUsbge info;

        (void)memset(&info, 0, sizeof(info));
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetObjectMonitorUsbge)
                        (gdbtb->jvmti, object, &info);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;
            (void)outStrebm_writeObjectRef(env, out, info.owner);
            (void)outStrebm_writeInt(out, info.entry_count);
            (void)outStrebm_writeInt(out, info.wbiter_count);
            for (i = 0; i < info.wbiter_count; i++) {
                (void)outStrebm_writeObjectRef(env, out, info.wbiters[i]);
            }
        }

        if (info.wbiters != NULL )
            jvmtiDebllocbte(info.wbiters);

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
invokeInstbnce(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return shbredInvoke(in, out);
}

stbtic jboolebn
disbbleCollection(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jlong id;
    jvmtiError error;

    id = inStrebm_rebdObjectID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = commonRef_pin(id);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }

    return JNI_TRUE;
}

stbtic jboolebn
enbbleCollection(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jlong id;

    id = inStrebm_rebdObjectID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = commonRef_unpin(id);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }

    return JNI_TRUE;
}

stbtic jboolebn
isCollected(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jobject ref;
    jlong id;
    JNIEnv *env;

    env = getEnv();
    id = inStrebm_rebdObjectID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    if (id == NULL_OBJECT_ID) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_OBJECT));
        return JNI_TRUE;
    }

    ref = commonRef_idToRef(env, id);
    (void)outStrebm_writeBoolebn(out, (jboolebn)(ref == NULL));

    commonRef_idToRef_delete(env, ref);

    return JNI_TRUE;
}


stbtic jboolebn
referringObjects(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jobject object;
    jint    mbxReferrers;
    JNIEnv *env;

    env = getEnv();

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    object = inStrebm_rebdObjectRef(env,in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    mbxReferrers = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {
        jvmtiError   error;
        ObjectBbtch  referrerBbtch;

        error = objectReferrers(object, &referrerBbtch, mbxReferrers);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int kk;

            (void)outStrebm_writeInt(out, referrerBbtch.count);
            for (kk = 0; kk < referrerBbtch.count; kk++) {
                jobject ref;

                ref = referrerBbtch.objects[kk];
                (void)outStrebm_writeByte(out, specificTypeKey(env, ref));
                (void)outStrebm_writeObjectRef(env, out, ref);
            }
            jvmtiDebllocbte(referrerBbtch.objects);
        }
    } END_WITH_LOCAL_REFS(env);
    return JNI_TRUE;
}

void *ObjectReference_Cmds[] = { (void *)10
    ,(void *)referenceType
    ,(void *)getVblues
    ,(void *)setVblues
    ,(void *)NULL      /* no longer used */
    ,(void *)monitorInfo
    ,(void *)invokeInstbnce
    ,(void *)disbbleCollection
    ,(void *)enbbleCollection
    ,(void *)isCollected
    ,(void *)referringObjects
    };
