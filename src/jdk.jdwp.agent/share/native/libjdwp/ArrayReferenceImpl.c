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
#include "ArrbyReferenceImpl.h"
#include "inStrebm.h"
#include "outStrebm.h"

stbtic jboolebn
length(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env = getEnv();
    jsize brrbyLength;

    jbrrby  brrby = inStrebm_rebdArrbyRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    brrbyLength = JNI_FUNC_PTR(env,GetArrbyLength)(env, brrby);
    (void)outStrebm_writeInt(out, brrbyLength);
    return JNI_TRUE;
}

stbtic void *
newComponents(PbcketOutputStrebm *out, jint length, size_t nbytes)
{
    void *ptr = NULL;

    if ( length > 0 ) {
        ptr = jvmtiAllocbte(length*((jint)nbytes));
        if ( ptr == NULL ) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } else {
            (void)memset(ptr, 0, length*nbytes);
        }
    }
    return ptr;
}

stbtic void
deleteComponents(void *ptr)
{
    jvmtiDebllocbte(ptr);
}

stbtic void
writeBoolebnComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jboolebn *components;

    components = newComponents(out, length, sizeof(jboolebn));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetBoolebnArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeBoolebn(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeByteComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jbyte *components;

    components = newComponents(out, length, sizeof(jbyte));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetByteArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeByte(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeChbrComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jchbr *components;

    components = newComponents(out, length, sizeof(jchbr));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetChbrArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeChbr(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeShortComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jshort *components;

    components = newComponents(out, length, sizeof(jshort));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetShortArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeShort(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeIntComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jint *components;

    components = newComponents(out, length, sizeof(jint));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetIntArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeInt(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeLongComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jlong *components;

    components = newComponents(out, length, sizeof(jlong));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetLongArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeLong(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeFlobtComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jflobt *components;

    components = newComponents(out, length, sizeof(jflobt));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetFlobtArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeFlobt(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeDoubleComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{
    jdouble *components;

    components = newComponents(out, length, sizeof(jdouble));
    if (components != NULL) {
        jint i;
        JNI_FUNC_PTR(env,GetDoubleArrbyRegion)(env, brrby, index, length, components);
        for (i = 0; i < length; i++) {
            (void)outStrebm_writeDouble(out, components[i]);
        }
        deleteComponents(components);
    }
}

stbtic void
writeObjectComponents(JNIEnv *env, PbcketOutputStrebm *out,
                    jbrrby brrby, jint index, jint length)
{

    WITH_LOCAL_REFS(env, length) {

        int i;
        jobject component;

        for (i = 0; i < length; i++) {
            component = JNI_FUNC_PTR(env,GetObjectArrbyElement)(env, brrby, index + i);
            if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
                /* clebred by cbller */
                brebk;
            }
            (void)outStrebm_writeByte(out, specificTypeKey(env, component));
            (void)outStrebm_writeObjectRef(env, out, component);
        }

    } END_WITH_LOCAL_REFS(env);
}

stbtic jboolebn
getVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env = getEnv();
    jint brrbyLength;
    jbrrby brrby;
    jint index;
    jint length;

    brrby = inStrebm_rebdArrbyRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    index = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    length = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    brrbyLength = JNI_FUNC_PTR(env,GetArrbyLength)(env, brrby);

    if (length == -1) {
        length = brrbyLength - index;
    }

    if ((index < 0) || (index > brrbyLength - 1)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_INDEX));
        return JNI_TRUE;
    }

    if ((length < 0) || (length + index > brrbyLength)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_LENGTH));
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1) {

        jclbss brrbyClbss;
        chbr *signbture = NULL;
        chbr *componentSignbture;
        jbyte typeKey;
        jvmtiError error;

        brrbyClbss = JNI_FUNC_PTR(env,GetObjectClbss)(env, brrby);
        error = clbssSignbture(brrbyClbss, &signbture, NULL);
        if (error != JVMTI_ERROR_NONE) {
            goto err;
        }
        componentSignbture = &signbture[1];
        typeKey = componentSignbture[0];

        (void)outStrebm_writeByte(out, typeKey);
        (void)outStrebm_writeInt(out, length);

        if (isObjectTbg(typeKey)) {
            writeObjectComponents(env, out, brrby, index, length);
        } else {
            switch (typeKey) {
                cbse JDWP_TAG(BYTE):
                    writeByteComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(CHAR):
                    writeChbrComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(FLOAT):
                    writeFlobtComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(DOUBLE):
                    writeDoubleComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(INT):
                    writeIntComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(LONG):
                    writeLongComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(SHORT):
                    writeShortComponents(env, out, brrby, index, length);
                    brebk;

                cbse JDWP_TAG(BOOLEAN):
                    writeBoolebnComponents(env, out, brrby, index, length);
                    brebk;

                defbult:
                    outStrebm_setError(out, JDWP_ERROR(INVALID_TAG));
                    brebk;
            }
        }

        jvmtiDebllocbte(signbture);

    err:;

    } END_WITH_LOCAL_REFS(env);

    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        outStrebm_setError(out, JDWP_ERROR(INTERNAL));
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
    }

    return JNI_TRUE;
}

stbtic jdwpError
rebdBoolebnComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jboolebn component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdBoolebn(in);
        JNI_FUNC_PTR(env,SetBoolebnArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdByteComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jbyte component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdByte(in);
        JNI_FUNC_PTR(env,SetByteArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdChbrComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jchbr component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdChbr(in);
        JNI_FUNC_PTR(env,SetChbrArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdShortComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jshort component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdShort(in);
        JNI_FUNC_PTR(env,SetShortArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdIntComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jint component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdInt(in);
        JNI_FUNC_PTR(env,SetIntArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdLongComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jlong component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdLong(in);
        JNI_FUNC_PTR(env,SetLongArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdFlobtComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jflobt component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdFlobt(in);
        JNI_FUNC_PTR(env,SetFlobtArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}

stbtic jdwpError
rebdDoubleComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
{
    int i;
    jdouble component;

    for (i = 0; (i < length) && !inStrebm_error(in); i++) {
        component = inStrebm_rebdDouble(in);
        JNI_FUNC_PTR(env,SetDoubleArrbyRegion)(env, brrby, index + i, 1, &component);
    }
    return inStrebm_error(in);
}


stbtic jdwpError
rebdObjectComponents(JNIEnv *env, PbcketInputStrebm *in,
                   jbrrby brrby, int index, int length)
                   /* chbr *componentSignbture) */
{
    int i;

    for (i = 0; i < length; i++) {
        jobject object = inStrebm_rebdObjectRef(env, in);

        JNI_FUNC_PTR(env,SetObjectArrbyElement)(env, brrby, index + i, object);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            /* cbller will clebr */
            brebk;
        }
    }

    return JDWP_ERROR(NONE);
}


stbtic jboolebn
setVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env = getEnv();
    jdwpError serror = JDWP_ERROR(NONE);
    int brrbyLength;
    jbrrby brrby;
    jint index;
    jint length;

    brrby = inStrebm_rebdArrbyRef(env, in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    index = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    length = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    brrbyLength = JNI_FUNC_PTR(env,GetArrbyLength)(env, brrby);

    if ((index < 0) || (index > brrbyLength - 1)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_INDEX));
        return JNI_TRUE;
    }

    if ((length < 0) || (length + index > brrbyLength)) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_LENGTH));
        return JNI_TRUE;
    }

    WITH_LOCAL_REFS(env, 1)  {

        jclbss brrbyClbss;
        chbr *signbture = NULL;
        chbr *componentSignbture;
        jvmtiError error;

        brrbyClbss = JNI_FUNC_PTR(env,GetObjectClbss)(env, brrby);
        error = clbssSignbture(brrbyClbss, &signbture, NULL);
        if (error != JVMTI_ERROR_NONE) {
            goto err;
        }
        componentSignbture = &signbture[1];

        switch (componentSignbture[0]) {
            cbse JDWP_TAG(OBJECT):
            cbse JDWP_TAG(ARRAY):
                serror = rebdObjectComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(BYTE):
                serror = rebdByteComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(CHAR):
                serror = rebdChbrComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(FLOAT):
                serror = rebdFlobtComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(DOUBLE):
                serror = rebdDoubleComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(INT):
                serror = rebdIntComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(LONG):
                serror = rebdLongComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(SHORT):
                serror = rebdShortComponents(env, in, brrby, index, length);
                brebk;

            cbse JDWP_TAG(BOOLEAN):
                serror = rebdBoolebnComponents(env, in, brrby, index, length);
                brebk;

            defbult:
                {
                    ERROR_MESSAGE(("Invblid brrby component signbture: %s",
                                        componentSignbture));
                    EXIT_ERROR(AGENT_ERROR_INVALID_OBJECT,NULL);
                }
                brebk;
        }

        jvmtiDebllocbte(signbture);

    err:;

    } END_WITH_LOCAL_REFS(env);

    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        /*
         * TO DO: Check exception type
         */
        serror = JDWP_ERROR(TYPE_MISMATCH);
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
    }

    outStrebm_setError(out, serror);
    return JNI_TRUE;
}


void *ArrbyReference_Cmds[] = { (void *)0x3
    ,(void *)length
    ,(void *)getVblues
    ,(void *)setVblues};
