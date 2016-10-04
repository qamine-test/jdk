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
#include "MethodImpl.h"
#include "inStrebm.h"
#include "outStrebm.h"

stbtic jboolebn
lineTbble(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jint count = 0;
    jvmtiLineNumberEntry *tbble = NULL;
    jmethodID method;
    jlocbtion firstCodeIndex;
    jlocbtion lbstCodeIndex;
    jboolebn isNbtive;

    /* JVMDI needed the clbss, but JVMTI does not so we ignore it */
    (void)inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    method = inStrebm_rebdMethodID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * JVMTI behbvior for the cblls below is unspecified for nbtive
     * methods, so we must check explicitly.
     */
    isNbtive = isMethodNbtive(method);
    if (isNbtive) {
        outStrebm_setError(out, JDWP_ERROR(NATIVE_METHOD));
        return JNI_TRUE;
    }

    error = methodLocbtion(method, &firstCodeIndex, &lbstCodeIndex);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }
    (void)outStrebm_writeLocbtion(out, firstCodeIndex);
    (void)outStrebm_writeLocbtion(out, lbstCodeIndex);

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLineNumberTbble)
                (gdbtb->jvmti, method, &count, &tbble);
    if (error == JVMTI_ERROR_ABSENT_INFORMATION) {
        /*
         * Indicbte no line info with bn empty tbble. The code indices
         * bre still useful, so we don't wbnt to return bn error
         */
        (void)outStrebm_writeInt(out, 0);
    } else if (error == JVMTI_ERROR_NONE) {
        jint i;
        (void)outStrebm_writeInt(out, count);
        for (i = 0; (i < count) && !outStrebm_error(out); i++) {
            (void)outStrebm_writeLocbtion(out, tbble[i].stbrt_locbtion);
            (void)outStrebm_writeInt(out, tbble[i].line_number);
        }
        jvmtiDebllocbte(tbble);
    } else {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}


stbtic jboolebn
doVbribbleTbble(PbcketInputStrebm *in, PbcketOutputStrebm *out,
                int outputGenerics)
{
    jvmtiError error;
    jint count;
    jvmtiLocblVbribbleEntry *tbble;
    jmethodID method;
    jint brgsSize;
    jboolebn isNbtive;

    /* JVMDI needed the clbss, but JVMTI does not so we ignore it */
    (void)inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    method = inStrebm_rebdMethodID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * JVMTI behbvior for the cblls below is unspecified for nbtive
     * methods, so we must check explicitly.
     */
    isNbtive = isMethodNbtive(method);
    if (isNbtive) {
        outStrebm_setError(out, JDWP_ERROR(NATIVE_METHOD));
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetArgumentsSize)
                (gdbtb->jvmti, method, &brgsSize);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblVbribbleTbble)
                (gdbtb->jvmti, method, &count, &tbble);
    if (error == JVMTI_ERROR_NONE) {
        jint i;
        (void)outStrebm_writeInt(out, brgsSize);
        (void)outStrebm_writeInt(out, count);
        for (i = 0; (i < count) && !outStrebm_error(out); i++) {
            jvmtiLocblVbribbleEntry *entry = &tbble[i];
            (void)outStrebm_writeLocbtion(out, entry->stbrt_locbtion);
            (void)outStrebm_writeString(out, entry->nbme);
            (void)outStrebm_writeString(out, entry->signbture);
            if (outputGenerics == 1) {
                writeGenericSignbture(out, entry->generic_signbture);
            }
            (void)outStrebm_writeInt(out, entry->length);
            (void)outStrebm_writeInt(out, entry->slot);

            jvmtiDebllocbte(entry->nbme);
            jvmtiDebllocbte(entry->signbture);
            if (entry->generic_signbture != NULL) {
              jvmtiDebllocbte(entry->generic_signbture);
            }
        }

        jvmtiDebllocbte(tbble);
    } else {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}


stbtic jboolebn
vbribbleTbble(PbcketInputStrebm *in, PbcketOutputStrebm *out) {
    return doVbribbleTbble(in, out, 0);
}

stbtic jboolebn
vbribbleTbbleWithGenerics(PbcketInputStrebm *in, PbcketOutputStrebm *out) {
    return doVbribbleTbble(in, out, 1);
}


stbtic jboolebn
bytecodes(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    unsigned chbr * bcp;
    jint bytecodeCount;
    jmethodID method;

    /* JVMDI needed the clbss, but JVMTI does not so we ignore it */
    (void)inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    method = inStrebm_rebdMethodID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /* Initiblize bssuming no bytecodes bnd no error */
    error         = JVMTI_ERROR_NONE;
    bytecodeCount = 0;
    bcp           = NULL;

    /* Only non-nbtive methods hbve bytecodes, don't even bsk if nbtive. */
    if ( !isMethodNbtive(method) ) {
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetBytecodes)
                    (gdbtb->jvmti, method, &bytecodeCount, &bcp);
    }
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    } else {
        (void)outStrebm_writeByteArrby(out, bytecodeCount, (jbyte *)bcp);
        jvmtiDebllocbte(bcp);
    }

    return JNI_TRUE;
}

stbtic jboolebn
isObsolete(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jboolebn isObsolete;
    jmethodID method;

    /* JVMDI needed the clbss, but JVMTI does not so we ignore it */
    (void)inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    method = inStrebm_rebdMethodID(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    isObsolete = isMethodObsolete(method);
    (void)outStrebm_writeBoolebn(out, isObsolete);

    return JNI_TRUE;
}

void *Method_Cmds[] = { (void *)0x5
    ,(void *)lineTbble
    ,(void *)vbribbleTbble
    ,(void *)bytecodes
    ,(void *)isObsolete
    ,(void *)vbribbleTbbleWithGenerics
};
