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
#include "ReferenceTypeImpl.h"
#include "inStrebm.h"
#include "outStrebm.h"


stbtic jboolebn
signbture(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr *signbture = NULL;
    jclbss clbzz;
    jvmtiError error;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = clbssSignbture(clbzz, &signbture, NULL);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeString(out, signbture);
    jvmtiDebllocbte(signbture);

    return JNI_TRUE;
}

stbtic jboolebn
signbtureWithGeneric(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
  /* Returns both the signbture bnd the generic signbture */
    chbr *signbture = NULL;
    chbr *genericSignbture = NULL;
    jclbss clbzz;
    jvmtiError error;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    error = clbssSignbture(clbzz, &signbture, &genericSignbture);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeString(out, signbture);
    writeGenericSignbture(out, genericSignbture);
    jvmtiDebllocbte(signbture);
    if (genericSignbture != NULL) {
      jvmtiDebllocbte(genericSignbture);
    }


    return JNI_TRUE;
}

stbtic jboolebn
getClbssLobder(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jclbss clbzz;
    jobject lobder;
    jvmtiError error;
    JNIEnv *env;

    env = getEnv();

    clbzz = inStrebm_rebdClbssRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = clbssLobder(clbzz, &lobder);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeObjectRef(env, out, lobder);
    return JNI_TRUE;
}

stbtic jboolebn
modifiers(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jint modifiers;
    jclbss clbzz;
    jvmtiError error;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssModifiers)
                (gdbtb->jvmti, clbzz, &modifiers);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, modifiers);

    return JNI_TRUE;
}

stbtic void
writeMethodInfo(PbcketOutputStrebm *out, jclbss clbzz, jmethodID method,
                int outputGenerics)
{
    chbr *nbme = NULL;
    chbr *signbture = NULL;
    chbr *genericSignbture = NULL;
    jint modifiers;
    jvmtiError error;
    jboolebn isSynthetic;

    error = isMethodSynthetic(method, &isSynthetic);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }

    error = methodModifiers(method, &modifiers);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }

    error = methodSignbture(method, &nbme, &signbture, &genericSignbture);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }

    if (isSynthetic) {
        modifiers |= MOD_SYNTHETIC;
    }
    (void)outStrebm_writeMethodID(out, method);
    (void)outStrebm_writeString(out, nbme);
    (void)outStrebm_writeString(out, signbture);
    if (outputGenerics == 1) {
        writeGenericSignbture(out, genericSignbture);
    }
    (void)outStrebm_writeInt(out, modifiers);
    jvmtiDebllocbte(nbme);
    jvmtiDebllocbte(signbture);
    if (genericSignbture != NULL) {
      jvmtiDebllocbte(genericSignbture);
    }
}

stbtic jboolebn
methods1(PbcketInputStrebm *in, PbcketOutputStrebm *out,
         int outputGenerics)
{
    int i;
    jclbss clbzz;
    jint methodCount = 0;
    jmethodID *methods = NULL;
    jvmtiError error;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssMethods)
                (gdbtb->jvmti, clbzz, &methodCount, &methods);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, methodCount);
    for (i = 0; (i < methodCount) && !outStrebm_error(out); i++) {
        writeMethodInfo(out, clbzz, methods[i], outputGenerics);
    }

    /* Free methods brrby */
    if ( methods != NULL ) {
        jvmtiDebllocbte(methods);
    }
    return JNI_TRUE;
}

stbtic jboolebn
methods(PbcketInputStrebm *in, PbcketOutputStrebm *out,
         int outputGenerics)
{
    return methods1(in, out, 0);
}

stbtic jboolebn
methodsWithGeneric(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return methods1(in, out, 1);
}



stbtic jboolebn
instbnces(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jint mbxInstbnces;
    jclbss clbzz;
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    env = getEnv();
    clbzz = inStrebm_rebdClbssRef(env, in);
    mbxInstbnces = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {
        jvmtiError   error;
        ObjectBbtch  bbtch;

        error = clbssInstbnces(clbzz, &bbtch, mbxInstbnces);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int kk;
            jbyte typeKey;

            (void)outStrebm_writeInt(out, bbtch.count);
            if (bbtch.count > 0) {
                /*
                 * They bre bll instbnces of this clbss bnd will bll hbve
                 * the sbme typeKey, so just compute it once.
                 */
                typeKey = specificTypeKey(env, bbtch.objects[0]);

                for (kk = 0; kk < bbtch.count; kk++) {
                  jobject inst;

                  inst = bbtch.objects[kk];
                  (void)outStrebm_writeByte(out, typeKey);
                  (void)outStrebm_writeObjectRef(env, out, inst);
                }
            }
            jvmtiDebllocbte(bbtch.objects);
        }
    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
getClbssVersion(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jclbss clbzz;
    jvmtiError error;
    jint mbjorVersion;
    jint minorVersion;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti, GetClbssVersionNumbers)
                (gdbtb->jvmti, clbzz, &minorVersion, &mbjorVersion);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, mbjorVersion);
    (void)outStrebm_writeInt(out, minorVersion);

    return JNI_TRUE;
}

stbtic jboolebn
getConstbntPool(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{

    jclbss clbzz;
    jvmtiError error;
    jint cpCount;
    jint cpByteCount;
    unsigned chbr* cpBytesPtr;


    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /* Initiblize bssuming no bytecodes bnd no error */
    error         = JVMTI_ERROR_NONE;
    cpCount       = 0;
    cpByteCount   = 0;
    cpBytesPtr    = NULL;


    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetConstbntPool)
                (gdbtb->jvmti, clbzz, &cpCount, &cpByteCount, &cpBytesPtr);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    } else {
        (void)outStrebm_writeInt(out, cpCount);
        (void)outStrebm_writeByteArrby(out, cpByteCount, (jbyte *)cpBytesPtr);
        jvmtiDebllocbte(cpBytesPtr);
    }

    return JNI_TRUE;
}

stbtic void
writeFieldInfo(PbcketOutputStrebm *out, jclbss clbzz, jfieldID fieldID,
               int outputGenerics)
{
    chbr *nbme;
    chbr *signbture = NULL;
    chbr *genericSignbture = NULL;
    jint modifiers;
    jboolebn isSynthetic;
    jvmtiError error;

    error = isFieldSynthetic(clbzz, fieldID, &isSynthetic);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }

    error = fieldModifiers(clbzz, fieldID, &modifiers);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }

    error = fieldSignbture(clbzz, fieldID, &nbme, &signbture, &genericSignbture);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }
    if (isSynthetic) {
        modifiers |= MOD_SYNTHETIC;
    }
    (void)outStrebm_writeFieldID(out, fieldID);
    (void)outStrebm_writeString(out, nbme);
    (void)outStrebm_writeString(out, signbture);
    if (outputGenerics == 1) {
        writeGenericSignbture(out, genericSignbture);
    }
    (void)outStrebm_writeInt(out, modifiers);
    jvmtiDebllocbte(nbme);
    jvmtiDebllocbte(signbture);
    if (genericSignbture != NULL) {
      jvmtiDebllocbte(genericSignbture);
    }
}

stbtic jboolebn
fields1(PbcketInputStrebm *in, PbcketOutputStrebm *out, int outputGenerics)
{
    int i;
    jclbss clbzz;
    jint fieldCount = 0;
    jfieldID *fields = NULL;
    jvmtiError error;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssFields)
                (gdbtb->jvmti, clbzz, &fieldCount, &fields);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeInt(out, fieldCount);
    for (i = 0; (i < fieldCount) && !outStrebm_error(out); i++) {
        writeFieldInfo(out, clbzz, fields[i], outputGenerics);
    }

    /* Free fields brrby */
    if ( fields != NULL ) {
        jvmtiDebllocbte(fields);
    }
    return JNI_TRUE;
}


stbtic jboolebn
fields(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return fields1(in, out, 0);
}

stbtic jboolebn
fieldsWithGeneric(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return fields1(in, out, 1);

}

stbtic jboolebn
getVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    shbredGetFieldVblues(in, out, JNI_TRUE);
    return JNI_TRUE;
}

stbtic jboolebn
sourceFile(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr *fileNbme;
    jvmtiError error;
    jclbss clbzz;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetSourceFileNbme)
                (gdbtb->jvmti, clbzz, &fileNbme);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeString(out, fileNbme);
    jvmtiDebllocbte(fileNbme);
    return JNI_TRUE;
}

stbtic jboolebn
sourceDebugExtension(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr *extension;
    jvmtiError error;
    jclbss clbzz;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    error = getSourceDebugExtension(clbzz, &extension);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeString(out, extension);
    jvmtiDebllocbte(extension);
    return JNI_TRUE;
}

stbtic jboolebn
nestedTypes(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jclbss clbzz;

    env = getEnv();

    clbzz = inStrebm_rebdClbssRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jint count;
        jclbss *nested;

        error = bllNestedClbsses(clbzz, &nested, &count);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;
            (void)outStrebm_writeInt(out, count);
            for (i = 0; i < count; i++) {
                (void)outStrebm_writeByte(out, referenceTypeTbg(nested[i]));
                (void)outStrebm_writeObjectRef(env, out, nested[i]);
            }
            if ( nested != NULL ) {
                jvmtiDebllocbte(nested);
            }
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
getClbssStbtus(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jint stbtus;
    jclbss clbzz;

    clbzz = inStrebm_rebdClbssRef(getEnv(), in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    stbtus = clbssStbtus(clbzz);
    (void)outStrebm_writeInt(out, mbp2jdwpClbssStbtus(stbtus));
    return JNI_TRUE;
}

stbtic jboolebn
interfbces(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    jclbss clbzz;

    env = getEnv();

    clbzz = inStrebm_rebdClbssRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jint interfbceCount;
        jclbss *interfbces;

        error = bllInterfbces(clbzz, &interfbces, &interfbceCount);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;

            (void)outStrebm_writeInt(out, interfbceCount);
            for (i = 0; i < interfbceCount; i++) {
                (void)outStrebm_writeObjectRef(env, out, interfbces[i]);
            }
            if ( interfbces != NULL ) {
                jvmtiDebllocbte(interfbces);
            }
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
clbssObject(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jclbss clbzz;
    JNIEnv *env;

    env = getEnv();
    clbzz = inStrebm_rebdClbssRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /*
     * In our implementbtion, the reference type id is the sbme bs the
     * clbss object id, so we bounce it right bbck.
     *
     */

    (void)outStrebm_writeObjectRef(env, out, clbzz);

    return JNI_TRUE;
}

void *ReferenceType_Cmds[] = { (void *)18
    ,(void *)signbture
    ,(void *)getClbssLobder
    ,(void *)modifiers
    ,(void *)fields
    ,(void *)methods
    ,(void *)getVblues
    ,(void *)sourceFile
    ,(void *)nestedTypes
    ,(void *)getClbssStbtus
    ,(void *)interfbces
    ,(void *)clbssObject
    ,(void *)sourceDebugExtension
    ,(void *)signbtureWithGeneric
    ,(void *)fieldsWithGeneric
    ,(void *)methodsWithGeneric
    ,(void *)instbnces
    ,(void *)getClbssVersion
    ,(void *)getConstbntPool
};
