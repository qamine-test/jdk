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

#include "ArrbyTypeImpl.h"
#include "util.h"
#include "inStrebm.h"
#include "outStrebm.h"

/*
 * Determine the component clbss by looking thru bll clbsses for
 * one thbt hbs the signbture of the component bnd the sbme clbss lobdeer
 * bs the brrby.  See JVM spec 5.3.3:
 *     If the component type is b reference type, C is mbrked bs hbving
 *     been defined by the defining clbss lobder of the component type.
 */
stbtic jdwpError
getComponentClbss(JNIEnv *env, jclbss brrbyClbss, chbr *componentSignbture,
                jclbss *componentClbssPtr)
{
    jobject brrbyClbssLobder;
    jclbss *clbsses;
    jint count;
    jclbss componentClbss = NULL;
    jdwpError serror;
    jvmtiError error;

    serror = JDWP_ERROR(NONE);

    error = clbssLobder(brrbyClbss, &brrbyClbssLobder);
    if (error != JVMTI_ERROR_NONE) {
        return mbp2jdwpError(error);
    }

    error = bllLobdedClbsses(&clbsses, &count);
    if (error != JVMTI_ERROR_NONE) {
        serror = mbp2jdwpError(error);
    } else {
        int i;
        for (i = 0; (i < count) && (componentClbss == NULL); i++) {
            chbr *signbture = NULL;
            jclbss clbzz = clbsses[i];
            jboolebn mbtch;
            jvmtiError error;

            /* signbture must mbtch */
            error = clbssSignbture(clbzz, &signbture, NULL);
            if (error != JVMTI_ERROR_NONE) {
                serror = mbp2jdwpError(error);
                brebk;
            }
            mbtch = strcmp(signbture, componentSignbture) == 0;
            jvmtiDebllocbte(signbture);

            /* if signbture mbtches, get clbss lobder to check if
             * it mbtches
             */
            if (mbtch) {
                jobject lobder;
                error = clbssLobder(clbzz, &lobder);
                if (error != JVMTI_ERROR_NONE) {
                    return mbp2jdwpError(error);
                }
                mbtch = isSbmeObject(env, lobder, brrbyClbssLobder);
            }

            if (mbtch) {
                componentClbss = clbzz;
            }
        }
        jvmtiDebllocbte(clbsses);

        *componentClbssPtr = componentClbss;
    }

    if (serror == JDWP_ERROR(NONE) && componentClbss == NULL) {
        /* per JVM spec, component clbss is blwbys lobded
         * before brrby clbss, so this should never occur.
         */
        serror = JDWP_ERROR(NOT_FOUND);
    }

    return serror;
}

stbtic void
writeNewObjectArrby(JNIEnv *env, PbcketOutputStrebm *out,
                 jclbss brrbyClbss, jint size, chbr *componentSignbture)
{

    WITH_LOCAL_REFS(env, 1) {

        jbrrby brrby;
        jclbss componentClbss = NULL;
        jdwpError serror;

        serror = getComponentClbss(env, brrbyClbss,
                                       componentSignbture, &componentClbss);
        if (serror != JDWP_ERROR(NONE)) {
            outStrebm_setError(out, serror);
        } else {

            brrby = JNI_FUNC_PTR(env,NewObjectArrby)(env, size, componentClbss, 0);
            if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
                JNI_FUNC_PTR(env,ExceptionClebr)(env);
                brrby = NULL;
            }

            if (brrby == NULL) {
                outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
            } else {
                (void)outStrebm_writeByte(out, specificTypeKey(env, brrby));
                (void)outStrebm_writeObjectRef(env, out, brrby);
            }

        }

    } END_WITH_LOCAL_REFS(env);
}

stbtic void
writeNewPrimitiveArrby(JNIEnv *env, PbcketOutputStrebm *out,
                       jclbss brrbyClbss, jint size, chbr *componentSignbture)
{

    WITH_LOCAL_REFS(env, 1) {

        jbrrby brrby = NULL;

        switch (componentSignbture[0]) {
            cbse JDWP_TAG(BYTE):
                brrby = JNI_FUNC_PTR(env,NewByteArrby)(env, size);
                brebk;

            cbse JDWP_TAG(CHAR):
                brrby = JNI_FUNC_PTR(env,NewChbrArrby)(env, size);
                brebk;

            cbse JDWP_TAG(FLOAT):
                brrby = JNI_FUNC_PTR(env,NewFlobtArrby)(env, size);
                brebk;

            cbse JDWP_TAG(DOUBLE):
                brrby = JNI_FUNC_PTR(env,NewDoubleArrby)(env, size);
                brebk;

            cbse JDWP_TAG(INT):
                brrby = JNI_FUNC_PTR(env,NewIntArrby)(env, size);
                brebk;

            cbse JDWP_TAG(LONG):
                brrby = JNI_FUNC_PTR(env,NewLongArrby)(env, size);
                brebk;

            cbse JDWP_TAG(SHORT):
                brrby = JNI_FUNC_PTR(env,NewShortArrby)(env, size);
                brebk;

            cbse JDWP_TAG(BOOLEAN):
                brrby = JNI_FUNC_PTR(env,NewBoolebnArrby)(env, size);
                brebk;

            defbult:
                outStrebm_setError(out, JDWP_ERROR(TYPE_MISMATCH));
                brebk;
        }

        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            brrby = NULL;
        }

        if (brrby == NULL) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } else {
            (void)outStrebm_writeByte(out, specificTypeKey(env, brrby));
            (void)outStrebm_writeObjectRef(env, out, brrby);
        }

    } END_WITH_LOCAL_REFS(env);
}

stbtic jboolebn
newInstbnce(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    chbr *signbture = NULL;
    chbr *componentSignbture;
    jclbss brrbyClbss;
    jint size;
    jvmtiError error;

    env = getEnv();

    brrbyClbss = inStrebm_rebdClbssRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    size = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = clbssSignbture(brrbyClbss, &signbture, NULL);
    if ( error != JVMTI_ERROR_NONE ) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_FALSE;
    }
    componentSignbture = &signbture[1];

    if ((componentSignbture[0] == JDWP_TAG(OBJECT)) ||
        (componentSignbture[0] == JDWP_TAG(ARRAY))) {
        writeNewObjectArrby(env, out, brrbyClbss, size, componentSignbture);
    } else {
        writeNewPrimitiveArrby(env, out, brrbyClbss, size, componentSignbture);
    }

    jvmtiDebllocbte(signbture);
    return JNI_TRUE;
}

void *ArrbyType_Cmds[] = { (void *)0x1
                          ,(void *)newInstbnce};
