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
#include "StbckFrbmeImpl.h"
#include "inStrebm.h"
#include "outStrebm.h"
#include "threbdControl.h"
#include "FrbmeID.h"

stbtic jdwpError
vblidbteThrebdFrbme(jthrebd threbd, FrbmeID frbme)
{
    jvmtiError error;
    jdwpError  serror;
    jint count;
    error = threbdControl_suspendCount(threbd, &count);
    if ( error == JVMTI_ERROR_NONE ) {
        if ( count > 0 ) {
            serror = vblidbteFrbmeID(threbd, frbme);
        } else {
            serror = JDWP_ERROR(THREAD_NOT_SUSPENDED);
        }
    } else {
        serror =  mbp2jdwpError(error);
    }
    return serror;
}

stbtic jdwpError
writeVbribbleVblue(JNIEnv *env, PbcketOutputStrebm *out, jthrebd threbd,
                   FrbmeNumber fnum, jint slot, jbyte typeKey)
{
    jvmtiError error;
    jvblue vblue;

    if (isObjectTbg(typeKey)) {

        WITH_LOCAL_REFS(env, 1) {

            error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblObject)
                        (gdbtb->jvmti, threbd, fnum, slot, &vblue.l);

            if (error != JVMTI_ERROR_NONE) {
                outStrebm_setError(out, mbp2jdwpError(error));
            } else {
                (void)outStrebm_writeByte(out, specificTypeKey(env, vblue.l));
                (void)outStrebm_writeObjectRef(env, out, vblue.l);
            }

        } END_WITH_LOCAL_REFS(env);

    } else {
        /*
         * For primitive types, the type key is bounced bbck bs is.
         */
        (void)outStrebm_writeByte(out, typeKey);
        switch (typeKey) {
            cbse JDWP_TAG(BYTE): {
                    jint intVblue;
                    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, &intVblue);
                    (void)outStrebm_writeByte(out, (jbyte)intVblue);
                    brebk;
                }

            cbse JDWP_TAG(CHAR): {
                    jint intVblue;
                    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, &intVblue);
                    (void)outStrebm_writeChbr(out, (jchbr)intVblue);
                    brebk;
                }

            cbse JDWP_TAG(FLOAT):
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblFlobt)
                                (gdbtb->jvmti, threbd, fnum, slot, &vblue.f);
                (void)outStrebm_writeFlobt(out, vblue.f);
                brebk;

            cbse JDWP_TAG(DOUBLE):
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblDouble)
                                (gdbtb->jvmti, threbd, fnum, slot, &vblue.d);
                (void)outStrebm_writeDouble(out, vblue.d);
                brebk;

            cbse JDWP_TAG(INT):
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, &vblue.i);
                (void)outStrebm_writeInt(out, vblue.i);
                brebk;

            cbse JDWP_TAG(LONG):
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblLong)
                                (gdbtb->jvmti, threbd, fnum, slot, &vblue.j);
                (void)outStrebm_writeLong(out, vblue.j);
                brebk;

            cbse JDWP_TAG(SHORT): {
                jint intVblue;
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, &intVblue);
                (void)outStrebm_writeShort(out, (jshort)intVblue);
                brebk;
            }

            cbse JDWP_TAG(BOOLEAN):{
                jint intVblue;
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, &intVblue);
                (void)outStrebm_writeBoolebn(out, (jboolebn)intVblue);
                brebk;
            }

            defbult:
                return JDWP_ERROR(INVALID_TAG);
        }
    }

    return mbp2jdwpError(error);
}

stbtic jdwpError
rebdVbribbleVblue(JNIEnv *env, PbcketInputStrebm *in, jthrebd threbd,
                  FrbmeNumber fnum, jint slot, jbyte typeKey)
{
    jvmtiError error;
    jvblue vblue;

    if (isObjectTbg(typeKey)) {

        vblue.l = inStrebm_rebdObjectRef(env, in);

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblObject)
                        (gdbtb->jvmti, threbd, fnum, slot, vblue.l);

    } else {
        switch (typeKey) {
            cbse JDWP_TAG(BYTE):
                vblue.b = inStrebm_rebdByte(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.b);
                brebk;

            cbse JDWP_TAG(CHAR):
                vblue.c = inStrebm_rebdChbr(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.c);
                brebk;

            cbse JDWP_TAG(FLOAT):
                vblue.f = inStrebm_rebdFlobt(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblFlobt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.f);
                brebk;

            cbse JDWP_TAG(DOUBLE):
                vblue.d = inStrebm_rebdDouble(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblDouble)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.d);
                brebk;

            cbse JDWP_TAG(INT):
                vblue.i = inStrebm_rebdInt(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.i);
                brebk;

            cbse JDWP_TAG(LONG):
                vblue.j = inStrebm_rebdLong(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblLong)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.j);
                brebk;

            cbse JDWP_TAG(SHORT):
                vblue.s = inStrebm_rebdShort(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.s);
                brebk;

            cbse JDWP_TAG(BOOLEAN):
                vblue.z = inStrebm_rebdBoolebn(in);
                error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetLocblInt)
                                (gdbtb->jvmti, threbd, fnum, slot, vblue.z);
                brebk;

            defbult:
                return JDWP_ERROR(INVALID_TAG);
        }
    }

    return mbp2jdwpError(error);
}

stbtic jboolebn
getVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    int i;
    jdwpError serror;
    jthrebd threbd;
    FrbmeID frbme;
    jint vbribbleCount;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    frbme = inStrebm_rebdFrbmeID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    vbribbleCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * Vblidbte the frbme id
     */
    serror = vblidbteThrebdFrbme(threbd, frbme);
    if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, vbribbleCount);
    for (i = 0; (i < vbribbleCount) && !outStrebm_error(out); i++) {
        jint slot;
        jbyte typeKey;
        FrbmeNumber fnum;

        slot = inStrebm_rebdInt(in);
        if (inStrebm_error(in))
            brebk;
        typeKey = inStrebm_rebdByte(in);
        if (inStrebm_error(in))
            brebk;

        fnum = getFrbmeNumber(frbme);
        serror = writeVbribbleVblue(env, out, threbd, fnum, slot, typeKey);
        if (serror != JDWP_ERROR(NONE)) {
            outStrebm_setError(out, serror);
            brebk;
        }
    }

    return JNI_TRUE;
}

stbtic jboolebn
setVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jint i;
    jdwpError serror;
    jthrebd threbd;
    FrbmeID frbme;
    jint vbribbleCount;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    frbme = inStrebm_rebdFrbmeID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    vbribbleCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * Vblidbte the frbme id
     */
    serror = vblidbteThrebdFrbme(threbd, frbme);
    if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
        return JNI_TRUE;
    }

    for (i = 0; (i < vbribbleCount) && !inStrebm_error(in); i++) {

        jint slot;
        jbyte typeKey;
        FrbmeNumber fnum;

        slot = inStrebm_rebdInt(in);
        if (inStrebm_error(in)) {
            return JNI_TRUE;
        }
        typeKey = inStrebm_rebdByte(in);
        if (inStrebm_error(in)) {
            return JNI_TRUE;
        }

        fnum = getFrbmeNumber(frbme);
        serror = rebdVbribbleVblue(env, in, threbd, fnum, slot, typeKey);
        if (serror != JDWP_ERROR(NONE))
            brebk;
    }

    if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
    }

    return JNI_TRUE;
}

stbtic jboolebn
thisObject(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jdwpError serror;
    jthrebd threbd;
    FrbmeID frbme;

    env = getEnv();

    threbd = inStrebm_rebdThrebdRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    frbme = inStrebm_rebdFrbmeID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * Vblidbte the frbme id
     */
    serror = vblidbteThrebdFrbme(threbd, frbme);
    if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 2) {

        jvmtiError error;
        jmethodID method;
        jlocbtion locbtion;
        FrbmeNumber fnum;

        /*
         * Find out if the given frbme is for b stbtic or nbtive method.
         */
        fnum = getFrbmeNumber(frbme);
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                (gdbtb->jvmti, threbd, fnum, &method, &locbtion);
        if (error == JVMTI_ERROR_NONE) {

            jint modifiers;

            error = methodModifiers(method, &modifiers);
            if (error == JVMTI_ERROR_NONE) {

                jobject this_object;

                /*
                 * Return null for stbtic or nbtive methods; otherwise, the JVM
                 * spec gubrbntees thbt "this" is in slot 0
                 */
                if (modifiers & (MOD_STATIC | MOD_NATIVE)) {
                    this_object = NULL;
                    (void)outStrebm_writeByte(out, specificTypeKey(env, this_object));
                    (void)outStrebm_writeObjectRef(env, out, this_object);
                } else {
                    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblObject)
                                (gdbtb->jvmti, threbd, fnum, 0, &this_object);
                    if (error == JVMTI_ERROR_NONE) {
                        (void)outStrebm_writeByte(out, specificTypeKey(env, this_object));
                        (void)outStrebm_writeObjectRef(env, out, this_object);
                    }
                }

            }
        }
        serror = mbp2jdwpError(error);

    } END_WITH_LOCAL_REFS(env);

    if (serror != JDWP_ERROR(NONE))
        outStrebm_setError(out, serror);

    return JNI_TRUE;
}

stbtic jboolebn
popFrbmes(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jdwpError serror;
    jthrebd threbd;
    FrbmeID frbme;
    FrbmeNumber fnum;

    threbd = inStrebm_rebdThrebdRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    frbme = inStrebm_rebdFrbmeID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * Vblidbte the frbme id
     */
    serror = vblidbteThrebdFrbme(threbd, frbme);
    if (serror != JDWP_ERROR(NONE)) {
        outStrebm_setError(out, serror);
        return JNI_TRUE;
    }

    if (threbdControl_isDebugThrebd(threbd)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_THREAD));
        return JNI_TRUE;
    }

    fnum = getFrbmeNumber(frbme);
    error = threbdControl_popFrbmes(threbd, fnum);
    if (error != JVMTI_ERROR_NONE) {
        serror = mbp2jdwpError(error);
        outStrebm_setError(out, serror);
    }
    return JNI_TRUE;
}

void *StbckFrbme_Cmds[] = { (void *)0x4
    ,(void *)getVblues
    ,(void *)setVblues
    ,(void *)thisObject
    ,(void *)popFrbmes
};
