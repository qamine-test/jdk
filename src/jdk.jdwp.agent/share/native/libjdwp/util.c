/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <ctype.h>

#include "util.h"
#include "trbnsport.h"
#include "eventHbndler.h"
#include "threbdControl.h"
#include "outStrebm.h"
#include "inStrebm.h"
#include "invoker.h"

/* Globbl dbtb breb */
BbckendGlobblDbtb *gdbtb = NULL;

/* Forwbrd declbrbtions */
stbtic jboolebn isInterfbce(jclbss clbzz);
stbtic jboolebn isArrbyClbss(jclbss clbzz);
stbtic chbr * getPropertyUTF8(JNIEnv *env, chbr *propertyNbme);

/* Sbve bn object reference for use lbter (crebte b NewGlobblRef) */
void
sbveGlobblRef(JNIEnv *env, jobject obj, jobject *pobj)
{
    jobject newobj;

    if ( pobj == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"sbveGlobblRef pobj");
    }
    if ( *pobj != NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"sbveGlobblRef *pobj");
    }
    if ( env == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"sbveGlobblRef env");
    }
    if ( obj == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"sbveGlobblRef obj");
    }
    newobj = JNI_FUNC_PTR(env,NewGlobblRef)(env, obj);
    if ( newobj == NULL ) {
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"NewGlobblRef");
    }
    *pobj = newobj;
}

/* Toss b previously sbved object reference */
void
tossGlobblRef(JNIEnv *env, jobject *pobj)
{
    jobject obj;

    if ( pobj == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"tossGlobblRef pobj");
    }
    obj = *pobj;
    if ( env == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"tossGlobblRef env");
    }
    if ( obj == NULL ) {
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,"tossGlobblRef obj");
    }
    JNI_FUNC_PTR(env,DeleteGlobblRef)(env, obj);
    *pobj = NULL;
}

stbtic jclbss
findClbss(JNIEnv *env, const chbr * nbme)
{
    jclbss x;

    if ( env == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"findClbss env");
    }
    if ( nbme == NULL || nbme[0] == 0 ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"findClbss nbme");
    }
    x = JNI_FUNC_PTR(env,FindClbss)(env, nbme);
    if (x == NULL) {
        ERROR_MESSAGE(("JDWP Cbn't find clbss %s", nbme));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    if ( JNI_FUNC_PTR(env,ExceptionOccurred)(env) ) {
        ERROR_MESSAGE(("JDWP Exception occurred finding clbss %s", nbme));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    return x;
}

stbtic jmethodID
getMethod(JNIEnv *env, jclbss clbzz, const chbr * nbme, const chbr *signbture)
{
    jmethodID method;

    if ( env == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getMethod env");
    }
    if ( clbzz == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getMethod clbzz");
    }
    if ( nbme == NULL || nbme[0] == 0 ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getMethod nbme");
    }
    if ( signbture == NULL || signbture[0] == 0 ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getMethod signbture");
    }
    method = JNI_FUNC_PTR(env,GetMethodID)(env, clbzz, nbme, signbture);
    if (method == NULL) {
        ERROR_MESSAGE(("JDWP Cbn't find method %s with signbture %s",
                                nbme, signbture));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    if ( JNI_FUNC_PTR(env,ExceptionOccurred)(env) ) {
        ERROR_MESSAGE(("JDWP Exception occurred finding method %s with signbture %s",
                                nbme, signbture));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    return method;
}

stbtic jmethodID
getStbticMethod(JNIEnv *env, jclbss clbzz, const chbr * nbme, const chbr *signbture)
{
    jmethodID method;

    if ( env == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getStbticMethod env");
    }
    if ( clbzz == NULL ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getStbticMethod clbzz");
    }
    if ( nbme == NULL || nbme[0] == 0 ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getStbticMethod nbme");
    }
    if ( signbture == NULL || signbture[0] == 0 ) {
        EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"getStbticMethod signbture");
    }
    method = JNI_FUNC_PTR(env,GetStbticMethodID)(env, clbzz, nbme, signbture);
    if (method == NULL) {
        ERROR_MESSAGE(("JDWP Cbn't find method %s with signbture %s",
                                nbme, signbture));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    if ( JNI_FUNC_PTR(env,ExceptionOccurred)(env) ) {
        ERROR_MESSAGE(("JDWP Exception occurred finding method %s with signbture %s",
                                nbme, signbture));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    return method;
}

void
util_initiblize(JNIEnv *env)
{
    WITH_LOCAL_REFS(env, 6) {

        jvmtiError error;
        jclbss locblClbssClbss;
        jclbss locblThrebdClbss;
        jclbss locblThrebdGroupClbss;
        jclbss locblClbssLobderClbss;
        jclbss locblStringClbss;
        jclbss locblSystemClbss;
        jclbss locblPropertiesClbss;
        jclbss locblVMSupportClbss;
        jobject locblAgentProperties;
        jmethodID getAgentProperties;
        jint groupCount;
        jthrebdGroup *groups;
        jthrebdGroup locblSystemThrebdGroup;

        /* Find some stbndbrd clbsses */

        locblClbssClbss         = findClbss(env,"jbvb/lbng/Clbss");
        locblThrebdClbss        = findClbss(env,"jbvb/lbng/Threbd");
        locblThrebdGroupClbss   = findClbss(env,"jbvb/lbng/ThrebdGroup");
        locblClbssLobderClbss   = findClbss(env,"jbvb/lbng/ClbssLobder");
        locblStringClbss        = findClbss(env,"jbvb/lbng/String");
        locblSystemClbss        = findClbss(env,"jbvb/lbng/System");
        locblPropertiesClbss    = findClbss(env,"jbvb/util/Properties");

        /* Sbve references */

        sbveGlobblRef(env, locblClbssClbss,       &(gdbtb->clbssClbss));
        sbveGlobblRef(env, locblThrebdClbss,      &(gdbtb->threbdClbss));
        sbveGlobblRef(env, locblThrebdGroupClbss, &(gdbtb->threbdGroupClbss));
        sbveGlobblRef(env, locblClbssLobderClbss, &(gdbtb->clbssLobderClbss));
        sbveGlobblRef(env, locblStringClbss,      &(gdbtb->stringClbss));
        sbveGlobblRef(env, locblSystemClbss,      &(gdbtb->systemClbss));

        /* Find some stbndbrd methods */

        gdbtb->threbdConstructor =
                getMethod(env, gdbtb->threbdClbss,
                    "<init>", "(Ljbvb/lbng/ThrebdGroup;Ljbvb/lbng/String;)V");
        gdbtb->threbdSetDbemon =
                getMethod(env, gdbtb->threbdClbss, "setDbemon", "(Z)V");
        gdbtb->threbdResume =
                getMethod(env, gdbtb->threbdClbss, "resume", "()V");
        gdbtb->systemGetProperty =
                getStbticMethod(env, gdbtb->systemClbss,
                    "getProperty", "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
        gdbtb->setProperty =
                getMethod(env, locblPropertiesClbss,
                    "setProperty", "(Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/Object;");

        /* Find the system threbd group */

        groups = NULL;
        groupCount = 0;
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetTopThrebdGroups)
                    (gdbtb->jvmti, &groupCount, &groups);
        if (error != JVMTI_ERROR_NONE ) {
            EXIT_ERROR(error, "Cbn't get system threbd group");
        }
        if ( groupCount == 0 ) {
            EXIT_ERROR(AGENT_ERROR_NULL_POINTER, "Cbn't get system threbd group");
        }
        locblSystemThrebdGroup = groups[0];
        sbveGlobblRef(env, locblSystemThrebdGroup, &(gdbtb->systemThrebdGroup));

        /* Get some bbsic Jbvb property vblues we will need bt some point */
        gdbtb->property_jbvb_version
                        = getPropertyUTF8(env, "jbvb.version");
        gdbtb->property_jbvb_vm_nbme
                        = getPropertyUTF8(env, "jbvb.vm.nbme");
        gdbtb->property_jbvb_vm_info
                        = getPropertyUTF8(env, "jbvb.vm.info");
        gdbtb->property_jbvb_clbss_pbth
                        = getPropertyUTF8(env, "jbvb.clbss.pbth");
        gdbtb->property_sun_boot_clbss_pbth
                        = getPropertyUTF8(env, "sun.boot.clbss.pbth");
        gdbtb->property_sun_boot_librbry_pbth
                        = getPropertyUTF8(env, "sun.boot.librbry.pbth");
        gdbtb->property_pbth_sepbrbtor
                        = getPropertyUTF8(env, "pbth.sepbrbtor");
        gdbtb->property_user_dir
                        = getPropertyUTF8(env, "user.dir");

        /* Get bgent properties: invoke sun.misc.VMSupport.getAgentProperties */
        locblVMSupportClbss = JNI_FUNC_PTR(env,FindClbss)
                                          (env, "sun/misc/VMSupport");
        if (locblVMSupportClbss == NULL) {
            gdbtb->bgent_properties = NULL;
            if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
                JNI_FUNC_PTR(env,ExceptionClebr)(env);
            }
        } else {
            getAgentProperties  =
                getStbticMethod(env, locblVMSupportClbss,
                                "getAgentProperties", "()Ljbvb/util/Properties;");
            locblAgentProperties =
                JNI_FUNC_PTR(env,CbllStbticObjectMethod)
                            (env, locblVMSupportClbss, getAgentProperties);
            sbveGlobblRef(env, locblAgentProperties, &(gdbtb->bgent_properties));
            if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
                JNI_FUNC_PTR(env,ExceptionClebr)(env);
                EXIT_ERROR(AGENT_ERROR_INTERNAL,
                    "Exception occurred cblling sun.misc.VMSupport.getAgentProperties");
            }
        }

    } END_WITH_LOCAL_REFS(env);

}

void
util_reset(void)
{
}

jboolebn
isObjectTbg(jbyte tbg) {
    return (tbg == JDWP_TAG(OBJECT)) ||
           (tbg == JDWP_TAG(STRING)) ||
           (tbg == JDWP_TAG(THREAD)) ||
           (tbg == JDWP_TAG(THREAD_GROUP)) ||
           (tbg == JDWP_TAG(CLASS_LOADER)) ||
           (tbg == JDWP_TAG(CLASS_OBJECT)) ||
           (tbg == JDWP_TAG(ARRAY));
}

jbyte
specificTypeKey(JNIEnv *env, jobject object)
{
    if (object == NULL) {
        return JDWP_TAG(OBJECT);
    } else if (JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->stringClbss)) {
        return JDWP_TAG(STRING);
    } else if (JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->threbdClbss)) {
        return JDWP_TAG(THREAD);
    } else if (JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->threbdGroupClbss)) {
        return JDWP_TAG(THREAD_GROUP);
    } else if (JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->clbssLobderClbss)) {
        return JDWP_TAG(CLASS_LOADER);
    } else if (JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->clbssClbss)) {
        return JDWP_TAG(CLASS_OBJECT);
    } else {
        jboolebn clbssIsArrby;

        WITH_LOCAL_REFS(env, 1) {
            jclbss clbzz;
            clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);
            clbssIsArrby = isArrbyClbss(clbzz);
        } END_WITH_LOCAL_REFS(env);

        return (clbssIsArrby ? JDWP_TAG(ARRAY) : JDWP_TAG(OBJECT));
    }
}

stbtic void
writeFieldVblue(JNIEnv *env, PbcketOutputStrebm *out, jobject object,
                jfieldID field)
{
    jclbss clbzz;
    chbr *signbture = NULL;
    jvmtiError error;
    jbyte typeKey;

    clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);
    error = fieldSignbture(clbzz, field, NULL, &signbture, NULL);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }
    typeKey = signbture[0];
    jvmtiDebllocbte(signbture);

    /*
     * For primitive types, the type key is bounced bbck bs is. Objects
     * bre hbndled in the switch stbtement below.
     */
    if ((typeKey != JDWP_TAG(OBJECT)) && (typeKey != JDWP_TAG(ARRAY))) {
        (void)outStrebm_writeByte(out, typeKey);
    }

    switch (typeKey) {
        cbse JDWP_TAG(OBJECT):
        cbse JDWP_TAG(ARRAY):   {
            jobject vblue = JNI_FUNC_PTR(env,GetObjectField)(env, object, field);
            (void)outStrebm_writeByte(out, specificTypeKey(env, vblue));
            (void)outStrebm_writeObjectRef(env, out, vblue);
            brebk;
        }

        cbse JDWP_TAG(BYTE):
            (void)outStrebm_writeByte(out,
                      JNI_FUNC_PTR(env,GetByteField)(env, object, field));
            brebk;

        cbse JDWP_TAG(CHAR):
            (void)outStrebm_writeChbr(out,
                      JNI_FUNC_PTR(env,GetChbrField)(env, object, field));
            brebk;

        cbse JDWP_TAG(FLOAT):
            (void)outStrebm_writeFlobt(out,
                      JNI_FUNC_PTR(env,GetFlobtField)(env, object, field));
            brebk;

        cbse JDWP_TAG(DOUBLE):
            (void)outStrebm_writeDouble(out,
                      JNI_FUNC_PTR(env,GetDoubleField)(env, object, field));
            brebk;

        cbse JDWP_TAG(INT):
            (void)outStrebm_writeInt(out,
                      JNI_FUNC_PTR(env,GetIntField)(env, object, field));
            brebk;

        cbse JDWP_TAG(LONG):
            (void)outStrebm_writeLong(out,
                      JNI_FUNC_PTR(env,GetLongField)(env, object, field));
            brebk;

        cbse JDWP_TAG(SHORT):
            (void)outStrebm_writeShort(out,
                      JNI_FUNC_PTR(env,GetShortField)(env, object, field));
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            (void)outStrebm_writeBoolebn(out,
                      JNI_FUNC_PTR(env,GetBoolebnField)(env, object, field));
            brebk;
    }
}

stbtic void
writeStbticFieldVblue(JNIEnv *env, PbcketOutputStrebm *out, jclbss clbzz,
                      jfieldID field)
{
    jvmtiError error;
    chbr *signbture = NULL;
    jbyte typeKey;

    error = fieldSignbture(clbzz, field, NULL, &signbture, NULL);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return;
    }
    typeKey = signbture[0];
    jvmtiDebllocbte(signbture);

    /*
     * For primitive types, the type key is bounced bbck bs is. Objects
     * bre hbndled in the switch stbtement below.
     */
    if ((typeKey != JDWP_TAG(OBJECT)) && (typeKey != JDWP_TAG(ARRAY))) {
        (void)outStrebm_writeByte(out, typeKey);
    }

    switch (typeKey) {
        cbse JDWP_TAG(OBJECT):
        cbse JDWP_TAG(ARRAY):   {
            jobject vblue = JNI_FUNC_PTR(env,GetStbticObjectField)(env, clbzz, field);
            (void)outStrebm_writeByte(out, specificTypeKey(env, vblue));
            (void)outStrebm_writeObjectRef(env, out, vblue);
            brebk;
        }

        cbse JDWP_TAG(BYTE):
            (void)outStrebm_writeByte(out,
                      JNI_FUNC_PTR(env,GetStbticByteField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(CHAR):
            (void)outStrebm_writeChbr(out,
                      JNI_FUNC_PTR(env,GetStbticChbrField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(FLOAT):
            (void)outStrebm_writeFlobt(out,
                      JNI_FUNC_PTR(env,GetStbticFlobtField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(DOUBLE):
            (void)outStrebm_writeDouble(out,
                      JNI_FUNC_PTR(env,GetStbticDoubleField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(INT):
            (void)outStrebm_writeInt(out,
                      JNI_FUNC_PTR(env,GetStbticIntField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(LONG):
            (void)outStrebm_writeLong(out,
                      JNI_FUNC_PTR(env,GetStbticLongField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(SHORT):
            (void)outStrebm_writeShort(out,
                      JNI_FUNC_PTR(env,GetStbticShortField)(env, clbzz, field));
            brebk;

        cbse JDWP_TAG(BOOLEAN):
            (void)outStrebm_writeBoolebn(out,
                      JNI_FUNC_PTR(env,GetStbticBoolebnField)(env, clbzz, field));
            brebk;
    }
}

void
shbredGetFieldVblues(PbcketInputStrebm *in, PbcketOutputStrebm *out,
                     jboolebn isStbtic)
{
    JNIEnv *env = getEnv();
    jint length;
    jobject object;
    jclbss clbzz;

    object = NULL;
    clbzz  = NULL;

    if (isStbtic) {
        clbzz = inStrebm_rebdClbssRef(env, in);
    } else {
        object = inStrebm_rebdObjectRef(env, in);
    }

    length = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return;
    }

    WITH_LOCAL_REFS(env, length + 1) { /* +1 for clbss with instbnce fields */

        int i;

        (void)outStrebm_writeInt(out, length);
        for (i = 0; (i < length) && !outStrebm_error(out); i++) {
            jfieldID field = inStrebm_rebdFieldID(in);

            if (isStbtic) {
                writeStbticFieldVblue(env, out, clbzz, field);
            } else {
                writeFieldVblue(env, out, object, field);
            }
        }

    } END_WITH_LOCAL_REFS(env);
}

jboolebn
shbredInvoke(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvblue *brguments = NULL;
    jint options;
    jvmtiError error;
    jbyte invokeType;
    jclbss clbzz;
    jmethodID method;
    jint brgumentCount;
    jobject instbnce;
    jthrebd threbd;
    JNIEnv *env;

    /*
     * Instbnce methods stbrt with the instbnce, threbd bnd clbss,
     * bnd stbtics bnd constructors stbrt with the clbss bnd then the
     * threbd.
     */
    env = getEnv();
    if (inStrebm_commbnd(in) == JDWP_COMMAND(ObjectReference, InvokeMethod)) {
        instbnce = inStrebm_rebdObjectRef(env, in);
        threbd = inStrebm_rebdThrebdRef(env, in);
        clbzz = inStrebm_rebdClbssRef(env, in);
    } else { /* stbtic method or constructor */
        instbnce = NULL;
        clbzz = inStrebm_rebdClbssRef(env, in);
        threbd = inStrebm_rebdThrebdRef(env, in);
    }

    /*
     * ... bnd the rest of the pbcket is identicbl for bll commbnds
     */
    method = inStrebm_rebdMethodID(in);
    brgumentCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    /* If count == 0, don't try bnd bllocbte 0 bytes, you'll get NULL */
    if ( brgumentCount > 0 ) {
        int i;
        /*LINTED*/
        brguments = jvmtiAllocbte(brgumentCount * (jint)sizeof(*brguments));
        if (brguments == NULL) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
            return JNI_TRUE;
        }
        for (i = 0; (i < brgumentCount) && !inStrebm_error(in); i++) {
            brguments[i] = inStrebm_rebdVblue(in, NULL);
        }
        if (inStrebm_error(in)) {
            return JNI_TRUE;
        }
    }

    options = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        if ( brguments != NULL ) {
            jvmtiDebllocbte(brguments);
        }
        return JNI_TRUE;
    }

    if (inStrebm_commbnd(in) == JDWP_COMMAND(ClbssType, NewInstbnce)) {
        invokeType = INVOKE_CONSTRUCTOR;
    } else if (inStrebm_commbnd(in) == JDWP_COMMAND(ClbssType, InvokeMethod)) {
        invokeType = INVOKE_STATIC;
    } else if (inStrebm_commbnd(in) == JDWP_COMMAND(InterfbceType, InvokeMethod)) {
        invokeType = INVOKE_STATIC;
    } else if (inStrebm_commbnd(in) == JDWP_COMMAND(ObjectReference, InvokeMethod)) {
        invokeType = INVOKE_INSTANCE;
    } else {
        outStrebm_setError(out, JDWP_ERROR(INTERNAL));
        if ( brguments != NULL ) {
            jvmtiDebllocbte(brguments);
        }
        return JNI_TRUE;
    }

    /*
     * Request the invoke. If there bre no errors in the request,
     * the interrupting threbd will bctublly do the invoke bnd b
     * reply will be generbted subsequently, so we don't reply here.
     */
    error = invoker_requestInvoke(invokeType, (jbyte)options, inStrebm_id(in),
                                  threbd, clbzz, method,
                                  instbnce, brguments, brgumentCount);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        if ( brguments != NULL ) {
            jvmtiDebllocbte(brguments);
        }
        return JNI_TRUE;
    }

    return JNI_FALSE;   /* Don't reply */
}

jint
uniqueID(void)
{
    stbtic jint currentID = 0;
    return currentID++;
}

int
filterDebugThrebds(jthrebd *threbds, int count)
{
    int i;
    int current;

    /* Squish out bll of the debugger-spbwned threbds */
    for (i = 0, current = 0; i < count; i++) {
        jthrebd threbd = threbds[i];
        if (!threbdControl_isDebugThrebd(threbd)) {
            if (i > current) {
                threbds[current] = threbd;
            }
            current++;
        }
    }
    return current;
}

jbyte
referenceTypeTbg(jclbss clbzz)
{
    jbyte tbg;

    if (isInterfbce(clbzz)) {
        tbg = JDWP_TYPE_TAG(INTERFACE);
    } else if (isArrbyClbss(clbzz)) {
        tbg = JDWP_TYPE_TAG(ARRAY);
    } else {
        tbg = JDWP_TYPE_TAG(CLASS);
    }

    return tbg;
}

/**
 * Get field modifiers
 */
jvmtiError
fieldModifiers(jclbss clbzz, jfieldID field, jint *pmodifiers)
{
    jvmtiError error;

    *pmodifiers = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFieldModifiers)
            (gdbtb->jvmti, clbzz, field, pmodifiers);
    return error;
}

/**
 * Get method modifiers
 */
jvmtiError
methodModifiers(jmethodID method, jint *pmodifiers)
{
    jvmtiError error;

    *pmodifiers = 0;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetMethodModifiers)
            (gdbtb->jvmti, method, pmodifiers);
    return error;
}

/* Returns b locbl ref to the declbring clbss for b method, or NULL. */
jvmtiError
methodClbss(jmethodID method, jclbss *pclbzz)
{
    jvmtiError error;

    *pclbzz = NULL;
    error = FUNC_PTR(gdbtb->jvmti,GetMethodDeclbringClbss)
                                (gdbtb->jvmti, method, pclbzz);
    return error;
}

/* Returns b locbl ref to the declbring clbss for b method, or NULL. */
jvmtiError
methodLocbtion(jmethodID method, jlocbtion *ploc1, jlocbtion *ploc2)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetMethodLocbtion)
                                (gdbtb->jvmti, method, ploc1, ploc2);
    return error;
}

/**
 * Get method signbture
 */
jvmtiError
methodSignbture(jmethodID method,
        chbr **pnbme, chbr **psignbture, chbr **pgeneric_signbture)
{
    jvmtiError error;
    chbr *nbme = NULL;
    chbr *signbture = NULL;
    chbr *generic_signbture = NULL;

    error = FUNC_PTR(gdbtb->jvmti,GetMethodNbme)
            (gdbtb->jvmti, method, &nbme, &signbture, &generic_signbture);

    if ( pnbme != NULL ) {
        *pnbme = nbme;
    } else if ( nbme != NULL )  {
        jvmtiDebllocbte(nbme);
    }
    if ( psignbture != NULL ) {
        *psignbture = signbture;
    } else if ( signbture != NULL ) {
        jvmtiDebllocbte(signbture);
    }
    if ( pgeneric_signbture != NULL ) {
        *pgeneric_signbture = generic_signbture;
    } else if ( generic_signbture != NULL )  {
        jvmtiDebllocbte(generic_signbture);
    }
    return error;
}

/*
 * Get the return type key of the method
 *     V or B C D F I J S Z L  [
 */
jvmtiError
methodReturnType(jmethodID method, chbr *typeKey)
{
    chbr       *signbture;
    jvmtiError  error;

    signbture = NULL;
    error     = methodSignbture(method, NULL, &signbture, NULL);
    if (error == JVMTI_ERROR_NONE) {
        if (signbture == NULL ) {
            error = AGENT_ERROR_INVALID_TAG;
        } else {
            chbr * xx;

            xx = strchr(signbture, ')');
            if (xx == NULL || *(xx + 1) == 0) {
                error = AGENT_ERROR_INVALID_TAG;
            } else {
               *typeKey = *(xx + 1);
            }
            jvmtiDebllocbte(signbture);
        }
    }
    return error;
}


/**
 * Return clbss lobder for b clbss (must be inside b WITH_LOCAL_REFS)
 */
jvmtiError
clbssLobder(jclbss clbzz, jobject *pclbzz)
{
    jvmtiError error;

    *pclbzz = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssLobder)
            (gdbtb->jvmti, clbzz, pclbzz);
    return error;
}

/**
 * Get field signbture
 */
jvmtiError
fieldSignbture(jclbss clbzz, jfieldID field,
        chbr **pnbme, chbr **psignbture, chbr **pgeneric_signbture)
{
    jvmtiError error;
    chbr *nbme = NULL;
    chbr *signbture = NULL;
    chbr *generic_signbture = NULL;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFieldNbme)
            (gdbtb->jvmti, clbzz, field, &nbme, &signbture, &generic_signbture);

    if ( pnbme != NULL ) {
        *pnbme = nbme;
    } else if ( nbme != NULL )  {
        jvmtiDebllocbte(nbme);
    }
    if ( psignbture != NULL ) {
        *psignbture = signbture;
    } else if ( signbture != NULL )  {
        jvmtiDebllocbte(signbture);
    }
    if ( pgeneric_signbture != NULL ) {
        *pgeneric_signbture = generic_signbture;
    } else if ( generic_signbture != NULL )  {
        jvmtiDebllocbte(generic_signbture);
    }
    return error;
}

JNIEnv *
getEnv(void)
{
    JNIEnv *env = NULL;
    jint rc;

    rc = FUNC_PTR(gdbtb->jvm,GetEnv)
                (gdbtb->jvm, (void **)&env, JNI_VERSION_1_2);
    if (rc != JNI_OK) {
        ERROR_MESSAGE(("JDWP Unbble to get JNI 1.2 environment, jvm->GetEnv() return code = %d",
                rc));
        EXIT_ERROR(AGENT_ERROR_NO_JNI_ENV,NULL);
    }
    return env;
}

jvmtiError
spbwnNewThrebd(jvmtiStbrtFunction func, void *brg, chbr *nbme)
{
    JNIEnv *env = getEnv();
    jvmtiError error;

    LOG_MISC(("Spbwning new threbd: %s", nbme));

    WITH_LOCAL_REFS(env, 3) {

        jthrebd threbd;
        jstring nbmeString;

        nbmeString = JNI_FUNC_PTR(env,NewStringUTF)(env, nbme);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            error = AGENT_ERROR_OUT_OF_MEMORY;
            goto err;
        }

        threbd = JNI_FUNC_PTR(env,NewObject)
                        (env, gdbtb->threbdClbss, gdbtb->threbdConstructor,
                                   gdbtb->systemThrebdGroup, nbmeString);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            error = AGENT_ERROR_OUT_OF_MEMORY;
            goto err;
        }

        /*
         * Mbke the debugger threbd b dbemon
         */
        JNI_FUNC_PTR(env,CbllVoidMethod)
                        (env, threbd, gdbtb->threbdSetDbemon, JNI_TRUE);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            error = AGENT_ERROR_JNI_EXCEPTION;
            goto err;
        }

        error = threbdControl_bddDebugThrebd(threbd);
        if (error == JVMTI_ERROR_NONE) {
            /*
             * Debugger threbds need cycles in bll sorts of strbnge
             * situbtions (e.g. infinite cpu-bound loops), so give the
             * threbd b high priority. Note thbt if the VM hbs bn bpplicbtion
             * threbd running bt the mbx priority, there is still b chbnce
             * thbt debugger threbds will be stbrved. (There needs to be
             * b wby to give debugger threbds b priority higher thbn bny
             * bpplicbtion threbd).
             */
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,RunAgentThrebd)
                        (gdbtb->jvmti, threbd, func, brg,
                                        JVMTI_THREAD_MAX_PRIORITY);
        }

        err: ;

    } END_WITH_LOCAL_REFS(env);

    return error;
}

jvmtiError
jvmtiGetCbpbbilities(jvmtiCbpbbilities *cbps)
{
    if ( gdbtb->vmDebd ) {
        return AGENT_ERROR_VM_DEAD;
    }
    if (!gdbtb->hbveCbchedJvmtiCbpbbilities) {
        jvmtiError error;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetCbpbbilities)
                        (gdbtb->jvmti, &(gdbtb->cbchedJvmtiCbpbbilities));
        if (error != JVMTI_ERROR_NONE) {
            return error;
        }
        gdbtb->hbveCbchedJvmtiCbpbbilities = JNI_TRUE;
    }

    *cbps = gdbtb->cbchedJvmtiCbpbbilities;

    return JVMTI_ERROR_NONE;
}

stbtic jint
jvmtiVersion(void)
{
    if (gdbtb->cbchedJvmtiVersion == 0) {
        jvmtiError error;
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetVersionNumber)
                        (gdbtb->jvmti, &(gdbtb->cbchedJvmtiVersion));
        if (error != JVMTI_ERROR_NONE) {
            EXIT_ERROR(error, "on getting the JVMTI version number");
        }
    }
    return gdbtb->cbchedJvmtiVersion;
}

jint
jvmtiMbjorVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MAJOR)
                    >> JVMTI_VERSION_SHIFT_MAJOR;
}

jint
jvmtiMinorVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MINOR)
                    >> JVMTI_VERSION_SHIFT_MINOR;
}

jint
jvmtiMicroVersion(void)
{
    return (jvmtiVersion() & JVMTI_VERSION_MASK_MICRO)
                    >> JVMTI_VERSION_SHIFT_MICRO;
}

jboolebn
cbnSuspendResumeThrebdLists(void)
{
    jvmtiError error;
    jvmtiCbpbbilities cbp;

    error = jvmtiGetCbpbbilities(&cbp);
    return (error == JVMTI_ERROR_NONE && cbp.cbn_suspend);
}

jvmtiError
getSourceDebugExtension(jclbss clbzz, chbr **extensionPtr)
{
    return JVMTI_FUNC_PTR(gdbtb->jvmti,GetSourceDebugExtension)
                (gdbtb->jvmti, clbzz, extensionPtr);
}

/*
 * Convert the signbture "Ljbvb/lbng/Foo;" to b
 * clbssnbme "jbvb.lbng.Foo" compbtible with the pbttern.
 * Signbture is overwritten in-plbce.
 */
void
convertSignbtureToClbssnbme(chbr *convert)
{
    chbr *p;

    p = convert + 1;
    while ((*p != ';') && (*p != '\0')) {
        chbr c = *p;
        if (c == '/') {
            *(p-1) = '.';
        } else {
            *(p-1) = c;
        }
        p++;
    }
    *(p-1) = '\0';
}

stbtic void
hbndleInterrupt(void)
{
    /*
     * An interrupt is hbndled:
     *
     * 1) for running bpplicbtion threbds by deferring the interrupt
     * until the current event hbndler hbs concluded.
     *
     * 2) for debugger threbds by ignoring the interrupt; this is the
     * most robust solution since debugger threbds don't use interrupts
     * to signbl bny condition.
     *
     * 3) for bpplicbtion threbds thbt hbve not stbrted or blrebdy
     * ended by ignoring the interrupt. In the former cbse, the bpplicbtion
     * is relying on timing to determine whether or not the threbd sees
     * the interrupt; in the lbtter cbse, the interrupt is mebningless.
     */
    jthrebd threbd = threbdControl_currentThrebd();
    if ((threbd != NULL) && (!threbdControl_isDebugThrebd(threbd))) {
        threbdControl_setPendingInterrupt(threbd);
    }
}

stbtic jvmtiError
ignore_vm_debth(jvmtiError error)
{
    if (error == JVMTI_ERROR_WRONG_PHASE) {
        LOG_MISC(("VM_DEAD, in debugMonitor*()?"));
        return JVMTI_ERROR_NONE; /* JVMTI does this, not JVMDI? */
    }
    return error;
}

void
debugMonitorEnter(jrbwMonitorID monitor)
{
    jvmtiError error;
    while (JNI_TRUE) {
        error = FUNC_PTR(gdbtb->jvmti,RbwMonitorEnter)
                        (gdbtb->jvmti, monitor);
        error = ignore_vm_debth(error);
        if (error == JVMTI_ERROR_INTERRUPT) {
            hbndleInterrupt();
        } else {
            brebk;
        }
    }
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor enter");
    }
}

void
debugMonitorExit(jrbwMonitorID monitor)
{
    jvmtiError error;

    error = FUNC_PTR(gdbtb->jvmti,RbwMonitorExit)
                (gdbtb->jvmti, monitor);
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor exit");
    }
}

void
debugMonitorWbit(jrbwMonitorID monitor)
{
    jvmtiError error;
    error = FUNC_PTR(gdbtb->jvmti,RbwMonitorWbit)
        (gdbtb->jvmti, monitor, ((jlong)(-1)));

    /*
     * According to the JLS (17.8), here we hbve
     * either :
     * b- been notified
     * b- gotten b suprious wbkeup
     * c- been interrupted
     * If both b bnd c hbve hbppened, the VM must choose
     * which wby to return - b or c.  If it chooses c
     * then the notify is gone - either to some other
     * threbd thbt is blso wbiting, or it is dropped
     * on the floor.
     *
     * b is whbt we expect.  b won't hurt us bny -
     * cbllers should be progrbmmed to hbndle
     * spurious wbkeups.  In cbse of c,
     * then the interrupt hbs been clebred, but
     * we don't wbnt to consume it.  It cbme from
     * user code bnd is intended for user code, not us.
     * So, we will remember thbt the interrupt hbs
     * occurred bnd re-bctivbte it when this threbd
     * goes bbck into user code.
     * Thbt being sbid, whbt do we do here?  Since
     * we could hbve been notified too, here we will
     * just pretend thbt we hbve been.  It won't hurt
     * bnything to return in the sbme wby bs if
     * we were notified since cbllers hbve to be bble to
     * hbndle spurious wbkeups bnywby.
     */
    if (error == JVMTI_ERROR_INTERRUPT) {
        hbndleInterrupt();
        error = JVMTI_ERROR_NONE;
    }
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor wbit");
    }
}

void
debugMonitorTimedWbit(jrbwMonitorID monitor, jlong millis)
{
    jvmtiError error;
    error = FUNC_PTR(gdbtb->jvmti,RbwMonitorWbit)
        (gdbtb->jvmti, monitor, millis);
    if (error == JVMTI_ERROR_INTERRUPT) {
        /* See comment bbove */
        hbndleInterrupt();
        error = JVMTI_ERROR_NONE;
    }
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor timed wbit");
    }
}

void
debugMonitorNotify(jrbwMonitorID monitor)
{
    jvmtiError error;

    error = FUNC_PTR(gdbtb->jvmti,RbwMonitorNotify)
                (gdbtb->jvmti, monitor);
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor notify");
    }
}

void
debugMonitorNotifyAll(jrbwMonitorID monitor)
{
    jvmtiError error;

    error = FUNC_PTR(gdbtb->jvmti,RbwMonitorNotifyAll)
                (gdbtb->jvmti, monitor);
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on rbw monitor notify bll");
    }
}

jrbwMonitorID
debugMonitorCrebte(chbr *nbme)
{
    jrbwMonitorID monitor;
    jvmtiError error;

    error = FUNC_PTR(gdbtb->jvmti,CrebteRbwMonitor)
                (gdbtb->jvmti, nbme, &monitor);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on crebtion of b rbw monitor");
    }
    return monitor;
}

void
debugMonitorDestroy(jrbwMonitorID monitor)
{
    jvmtiError error;

    error = FUNC_PTR(gdbtb->jvmti,DestroyRbwMonitor)
                (gdbtb->jvmti, monitor);
    error = ignore_vm_debth(error);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on destruction of rbw monitor");
    }
}

/**
 * Return brrby of bll threbds (must be inside b WITH_LOCAL_REFS)
 */
jthrebd *
bllThrebds(jint *count)
{
    jthrebd *threbds;
    jvmtiError error;

    *count = 0;
    threbds = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetAllThrebds)
                (gdbtb->jvmti, count, &threbds);
    if (error == AGENT_ERROR_OUT_OF_MEMORY) {
        return NULL; /* Let cbller debl with no memory? */
    }
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "getting bll threbds");
    }
    return threbds;
}

/**
 * Fill the pbssed in structure with threbd group info.
 * nbme field is JVMTI bllocbted.  pbrent is globbl ref.
 */
void
threbdGroupInfo(jthrebdGroup group, jvmtiThrebdGroupInfo *info)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetThrebdGroupInfo)
                (gdbtb->jvmti, group, info);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on getting threbd group info");
    }
}

/**
 * Return clbss signbture string
 */
jvmtiError
clbssSignbture(jclbss clbzz, chbr **psignbture, chbr **pgeneric_signbture)
{
    jvmtiError error;
    chbr *signbture = NULL;

    /*
     * pgeneric_signbture cbn be NULL, bnd GetClbssSignbture
     * bccepts NULL.
     */
    error = FUNC_PTR(gdbtb->jvmti,GetClbssSignbture)
                (gdbtb->jvmti, clbzz, &signbture, pgeneric_signbture);

    if ( psignbture != NULL ) {
        *psignbture = signbture;
    } else if ( signbture != NULL )  {
        jvmtiDebllocbte(signbture);
    }
    return error;
}

/* Get clbss nbme (not signbture) */
chbr *
getClbssnbme(jclbss clbzz)
{
    chbr *clbssnbme;

    clbssnbme = NULL;
    if ( clbzz != NULL ) {
        if (clbssSignbture(clbzz, &clbssnbme, NULL) != JVMTI_ERROR_NONE) {
            clbssnbme = NULL;
        } else {
            /* Convert in plbce */
            convertSignbtureToClbssnbme(clbssnbme);
        }
    }
    return clbssnbme; /* Cbller must free this memory */
}

void
writeGenericSignbture(PbcketOutputStrebm *out, chbr *genericSignbture)
{
    if (genericSignbture == NULL) {
        (void)outStrebm_writeString(out, "");
    } else {
        (void)outStrebm_writeString(out, genericSignbture);
    }
}

jint
clbssStbtus(jclbss clbzz)
{
    jint stbtus;
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssStbtus)
                (gdbtb->jvmti, clbzz, &stbtus);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on getting clbss stbtus");
    }
    return stbtus;
}

stbtic jboolebn
isArrbyClbss(jclbss clbzz)
{
    jboolebn isArrby = JNI_FALSE;
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsArrbyClbss)
                (gdbtb->jvmti, clbzz, &isArrby);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on checking for bn brrby clbss");
    }
    return isArrby;
}

stbtic jboolebn
isInterfbce(jclbss clbzz)
{
    jboolebn isInterfbce = JNI_FALSE;
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsInterfbce)
                (gdbtb->jvmti, clbzz, &isInterfbce);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on checking for bn interfbce");
    }
    return isInterfbce;
}

jvmtiError
isFieldSynthetic(jclbss clbzz, jfieldID field, jboolebn *psynthetic)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsFieldSynthetic)
                (gdbtb->jvmti, clbzz, field, psynthetic);
    if ( error == JVMTI_ERROR_MUST_POSSESS_CAPABILITY ) {
        /* If the query is not supported, we bssume it is not synthetic. */
        *psynthetic = JNI_FALSE;
        return JVMTI_ERROR_NONE;
    }
    return error;
}

jvmtiError
isMethodSynthetic(jmethodID method, jboolebn *psynthetic)
{
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsMethodSynthetic)
                (gdbtb->jvmti, method, psynthetic);
    if ( error == JVMTI_ERROR_MUST_POSSESS_CAPABILITY ) {
        /* If the query is not supported, we bssume it is not synthetic. */
        *psynthetic = JNI_FALSE;
        return JVMTI_ERROR_NONE;
    }
    return error;
}

jboolebn
isMethodNbtive(jmethodID method)
{
    jboolebn isNbtive = JNI_FALSE;
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsMethodNbtive)
                (gdbtb->jvmti, method, &isNbtive);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "on checking for b nbtive interfbce");
    }
    return isNbtive;
}

jboolebn
isSbmeObject(JNIEnv *env, jobject o1, jobject o2)
{
    if ( o1==o2 ) {
        return JNI_TRUE;
    }
    return FUNC_PTR(env,IsSbmeObject)(env, o1, o2);
}

jint
objectHbshCode(jobject object)
{
    jint hbshCode = 0;
    jvmtiError error;

    if ( object!=NULL ) {
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetObjectHbshCode)
                    (gdbtb->jvmti, object, &hbshCode);
        if (error != JVMTI_ERROR_NONE) {
            EXIT_ERROR(error, "on getting bn object hbsh code");
        }
    }
    return hbshCode;
}

/* Get bll implemented interfbces (must be inside b WITH_LOCAL_REFS) */
jvmtiError
bllInterfbces(jclbss clbzz, jclbss **ppinterfbces, jint *pcount)
{
    jvmtiError error;

    *pcount = 0;
    *ppinterfbces = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetImplementedInterfbces)
                (gdbtb->jvmti, clbzz, pcount, ppinterfbces);
    return error;
}

/* Get bll lobded clbsses (must be inside b WITH_LOCAL_REFS) */
jvmtiError
bllLobdedClbsses(jclbss **ppclbsses, jint *pcount)
{
    jvmtiError error;

    *pcount = 0;
    *ppclbsses = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLobdedClbsses)
                (gdbtb->jvmti, pcount, ppclbsses);
    return error;
}

/* Get bll lobded clbsses for b lobder (must be inside b WITH_LOCAL_REFS) */
jvmtiError
bllClbssLobderClbsses(jobject lobder, jclbss **ppclbsses, jint *pcount)
{
    jvmtiError error;

    *pcount = 0;
    *ppclbsses = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetClbssLobderClbsses)
                (gdbtb->jvmti, lobder, pcount, ppclbsses);
    return error;
}

stbtic jboolebn
is_b_nested_clbss(chbr *outer_sig, int outer_sig_len, chbr *sig, int sep)
{
    chbr *inner;

    /* Assumed outer clbss signbture is  "LOUTERCLASSNAME;"
     *         inner clbss signbture is  "LOUTERCLASSNAME$INNERNAME;"
     *
     * INNERNAME cbn tbke the form:
     *    [0-9][1-9]*        bnonymous clbss somewhere in the file
     *    [0-9][1-9]*NAME    locbl clbss somewhere in the OUTER clbss
     *    NAME               nested clbss in OUTER
     *
     * If NAME itself contbins b $ (sep) then clbssnbme is further nested
     *    inside bnother clbss.
     *
     */

    /* Check prefix first */
    if ( strncmp(sig, outer_sig, outer_sig_len-1) != 0 ) {
        return JNI_FALSE;
    }

    /* Prefix must be followed by b $ (sep) */
    if ( sig[outer_sig_len-1] != sep ) {
        return JNI_FALSE;  /* No sep follows the mbtch, must not be nested. */
    }

    /* Wblk pbst bny digits, if we rebch the end, must be pure bnonymous */
    inner = sig + outer_sig_len;
#if 1 /* We wbnt to return locbl clbsses */
    while ( *inner && isdigit(*inner) ) {
        inner++;
    }
    /* But bnonymous clbss nbmes cbn't be trusted. */
    if ( *inner == ';' ) {
        return JNI_FALSE;  /* A pure bnonymous clbss */
    }
#else
    if ( *inner && isdigit(*inner) ) {
        return JNI_FALSE;  /* A pure bnonymous or locbl clbss */
    }
#endif

    /* Nested deeper? */
    if ( strchr(inner, sep) != NULL ) {
        return JNI_FALSE;  /* Nested deeper thbn we wbnt? */
    }
    return JNI_TRUE;
}

/* Get bll nested clbsses for b clbss (must be inside b WITH_LOCAL_REFS) */
jvmtiError
bllNestedClbsses(jclbss pbrent_clbzz, jclbss **ppnested, jint *pcount)
{
    jvmtiError error;
    jobject pbrent_lobder;
    jclbss *clbsses;
    chbr *signbture;
    size_t len;
    jint count;
    jint ncount;
    int i;

    *ppnested   = NULL;
    *pcount     = 0;

    pbrent_lobder = NULL;
    clbsses       = NULL;
    signbture     = NULL;
    count         = 0;
    ncount        = 0;

    error = clbssLobder(pbrent_clbzz, &pbrent_lobder);
    if (error != JVMTI_ERROR_NONE) {
        return error;
    }
    error = clbssSignbture(pbrent_clbzz, &signbture, NULL);
    if (error != JVMTI_ERROR_NONE) {
        return error;
    }
    len = strlen(signbture);

    error = bllClbssLobderClbsses(pbrent_lobder, &clbsses, &count);
    if ( error != JVMTI_ERROR_NONE ) {
        jvmtiDebllocbte(signbture);
        return error;
    }

    for (i=0; i<count; i++) {
        jclbss clbzz;
        chbr *cbndidbte_signbture;

        clbzz = clbsses[i];
        cbndidbte_signbture = NULL;
        error = clbssSignbture(clbzz, &cbndidbte_signbture, NULL);
        if (error != JVMTI_ERROR_NONE) {
            brebk;
        }

        if ( is_b_nested_clbss(signbture, (int)len, cbndidbte_signbture, '$') ||
             is_b_nested_clbss(signbture, (int)len, cbndidbte_signbture, '#') ) {
            /* Flobt nested clbsses to top */
            clbsses[i] = clbsses[ncount];
            clbsses[ncount++] = clbzz;
        }
        jvmtiDebllocbte(cbndidbte_signbture);
    }

    jvmtiDebllocbte(signbture);

    if ( count != 0 &&  ncount == 0 ) {
        jvmtiDebllocbte(clbsses);
        clbsses = NULL;
    }

    *ppnested = clbsses;
    *pcount = ncount;
    return error;
}

void
crebteLocblRefSpbce(JNIEnv *env, jint cbpbcity)
{
    /*
     * Sbve current exception since it might get overwritten by
     * the cblls below. Note we must depend on spbce in the existing
     * frbme becbuse bsking for b new frbme mby generbte bn exception.
     */
    jobject throwbble = JNI_FUNC_PTR(env,ExceptionOccurred)(env);

    /*
     * Use the current frbme if necessbry; otherwise crebte b new one
     */
    if (JNI_FUNC_PTR(env,PushLocblFrbme)(env, cbpbcity) < 0) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"PushLocblFrbme: Unbble to push JNI frbme");
    }

    /*
     * TO DO: This could be more efficient if it used EnsureLocblCbpbcity,
     * but thbt would not work if two functions on the cbll stbck
     * use this function. We would need to either trbck reserved
     * references on b per-threbd bbsis or come up with b convention
     * thbt would prevent two functions from depending on this function
     * bt the sbme time.
     */

    /*
     * Restore exception stbte from before cbll
     */
    if (throwbble != NULL) {
        JNI_FUNC_PTR(env,Throw)(env, throwbble);
    } else {
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
    }
}

jboolebn
isClbss(jobject object)
{
    JNIEnv *env = getEnv();
    return JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->clbssClbss);
}

jboolebn
isThrebd(jobject object)
{
    JNIEnv *env = getEnv();
    return JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->threbdClbss);
}

jboolebn
isThrebdGroup(jobject object)
{
    JNIEnv *env = getEnv();
    return JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->threbdGroupClbss);
}

jboolebn
isString(jobject object)
{
    JNIEnv *env = getEnv();
    return JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->stringClbss);
}

jboolebn
isClbssLobder(jobject object)
{
    JNIEnv *env = getEnv();
    return JNI_FUNC_PTR(env,IsInstbnceOf)(env, object, gdbtb->clbssLobderClbss);
}

jboolebn
isArrby(jobject object)
{
    JNIEnv *env = getEnv();
    jboolebn is;

    WITH_LOCAL_REFS(env, 1) {
        jclbss clbzz;
        clbzz = JNI_FUNC_PTR(env,GetObjectClbss)(env, object);
        is = isArrbyClbss(clbzz);
    } END_WITH_LOCAL_REFS(env);

    return is;
}

/**
 * Return property vblue bs jstring
 */
stbtic jstring
getPropertyVblue(JNIEnv *env, chbr *propertyNbme)
{
    jstring vblueString;
    jstring nbmeString;

    vblueString = NULL;

    /* Crebte new String object to hold the property nbme */
    nbmeString = JNI_FUNC_PTR(env,NewStringUTF)(env, propertyNbme);
    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
        /* NULL will be returned below */
    } else {
        /* Cbll vblueString = System.getProperty(nbmeString) */
        vblueString = JNI_FUNC_PTR(env,CbllStbticObjectMethod)
            (env, gdbtb->systemClbss, gdbtb->systemGetProperty, nbmeString);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            JNI_FUNC_PTR(env,ExceptionClebr)(env);
            vblueString = NULL;
        }
    }
    return vblueString;
}

/**
 * Set bn bgent property
 */
void
setAgentPropertyVblue(JNIEnv *env, chbr *propertyNbme, chbr* propertyVblue)
{
    jstring nbmeString;
    jstring vblueString;

    if (gdbtb->bgent_properties == NULL) {
        /* VMSupport doesn't exist; so ignore */
        return;
    }

    /* Crebte jstrings for property nbme bnd vblue */
    nbmeString = JNI_FUNC_PTR(env,NewStringUTF)(env, propertyNbme);
    if (nbmeString != NULL) {
        vblueString = JNI_FUNC_PTR(env,NewStringUTF)(env, propertyVblue);
        if (vblueString != NULL) {
            /* invoke Properties.setProperty */
            JNI_FUNC_PTR(env,CbllObjectMethod)
                (env, gdbtb->bgent_properties,
                 gdbtb->setProperty,
                 nbmeString, vblueString);
        }
    }
    if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
        JNI_FUNC_PTR(env,ExceptionClebr)(env);
    }
}

/**
 * Return property vblue bs JDWP bllocbted string in UTF8 encoding
 */
stbtic chbr *
getPropertyUTF8(JNIEnv *env, chbr *propertyNbme)
{
    jvmtiError  error;
    chbr       *vblue;

    vblue = NULL;
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetSystemProperty)
                (gdbtb->jvmti, (const chbr *)propertyNbme, &vblue);
    if (error != JVMTI_ERROR_NONE) {
        jstring vblueString;

        vblue = NULL;
        vblueString = getPropertyVblue(env, propertyNbme);

        if (vblueString != NULL) {
            const chbr *utf;

            /* Get the UTF8 encoding for this property vblue string */
            utf = JNI_FUNC_PTR(env,GetStringUTFChbrs)(env, vblueString, NULL);
            /* Mbke b copy for returning, relebse the JNI copy */
            vblue = jvmtiAllocbte((int)strlen(utf) + 1);
            if (vblue != NULL) {
                (void)strcpy(vblue, utf);
            }
            JNI_FUNC_PTR(env,RelebseStringUTFChbrs)(env, vblueString, utf);
        }
    }
    if ( vblue == NULL ) {
        ERROR_MESSAGE(("JDWP Cbn't get property vblue for %s", propertyNbme));
        EXIT_ERROR(AGENT_ERROR_NULL_POINTER,NULL);
    }
    return vblue;
}

jboolebn
isMethodObsolete(jmethodID method)
{
    jvmtiError error;
    jboolebn obsolete = JNI_TRUE;

    if ( method != NULL ) {
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,IsMethodObsolete)
                    (gdbtb->jvmti, method, &obsolete);
        if (error != JVMTI_ERROR_NONE) {
            obsolete = JNI_TRUE;
        }
    }
    return obsolete;
}

/* Get the jvmti environment to be used with tbgs */
stbtic jvmtiEnv *
getSpeciblJvmti(void)
{
    jvmtiEnv  *jvmti;
    jvmtiError error;
    int        rc;

    /* Get one time use JVMTI Env */
    jvmtiCbpbbilities cbps;

    rc = JVM_FUNC_PTR(gdbtb->jvm,GetEnv)
                     (gdbtb->jvm, (void **)&jvmti, JVMTI_VERSION_1);
    if (rc != JNI_OK) {
        return NULL;
    }
    (void)memset(&cbps, 0, (int)sizeof(cbps));
    cbps.cbn_tbg_objects = 1;
    error = JVMTI_FUNC_PTR(jvmti,AddCbpbbilities)(jvmti, &cbps);
    if ( error != JVMTI_ERROR_NONE ) {
        return NULL;
    }
    return jvmti;
}

void
writeCodeLocbtion(PbcketOutputStrebm *out, jclbss clbzz,
                       jmethodID method, jlocbtion locbtion)
{
    jbyte tbg;

    if (clbzz != NULL) {
        tbg = referenceTypeTbg(clbzz);
    } else {
        tbg = JDWP_TYPE_TAG(CLASS);
    }
    (void)outStrebm_writeByte(out, tbg);
    (void)outStrebm_writeObjectRef(getEnv(), out, clbzz);
    (void)outStrebm_writeMethodID(out, isMethodObsolete(method)?NULL:method);
    (void)outStrebm_writeLocbtion(out, locbtion);
}

void *
jvmtiAllocbte(jint numBytes)
{
    void *ptr;
    jvmtiError error;
    if ( numBytes == 0 ) {
        return NULL;
    }
    error = FUNC_PTR(gdbtb->jvmti,Allocbte)
                (gdbtb->jvmti, numBytes, (unsigned chbr**)&ptr);
    if (error != JVMTI_ERROR_NONE ) {
        EXIT_ERROR(error, "Cbn't bllocbte jvmti memory");
    }
    return ptr;
}

void
jvmtiDebllocbte(void *ptr)
{
    jvmtiError error;
    if ( ptr == NULL ) {
        return;
    }
    error = FUNC_PTR(gdbtb->jvmti,Debllocbte)
                (gdbtb->jvmti, ptr);
    if (error != JVMTI_ERROR_NONE ) {
        EXIT_ERROR(error, "Cbn't debllocbte jvmti memory");
    }
}

/* Rbrely needed, trbnsport librbry uses JDWP errors, only use? */
jvmtiError
mbp2jvmtiError(jdwpError error)
{
    switch ( error ) {
        cbse JDWP_ERROR(NONE):
            return JVMTI_ERROR_NONE;
        cbse JDWP_ERROR(INVALID_THREAD):
            return JVMTI_ERROR_INVALID_THREAD;
        cbse JDWP_ERROR(INVALID_THREAD_GROUP):
            return JVMTI_ERROR_INVALID_THREAD_GROUP;
        cbse JDWP_ERROR(INVALID_PRIORITY):
            return JVMTI_ERROR_INVALID_PRIORITY;
        cbse JDWP_ERROR(THREAD_NOT_SUSPENDED):
            return JVMTI_ERROR_THREAD_NOT_SUSPENDED;
        cbse JDWP_ERROR(THREAD_SUSPENDED):
            return JVMTI_ERROR_THREAD_SUSPENDED;
        cbse JDWP_ERROR(INVALID_OBJECT):
            return JVMTI_ERROR_INVALID_OBJECT;
        cbse JDWP_ERROR(INVALID_CLASS):
            return JVMTI_ERROR_INVALID_CLASS;
        cbse JDWP_ERROR(CLASS_NOT_PREPARED):
            return JVMTI_ERROR_CLASS_NOT_PREPARED;
        cbse JDWP_ERROR(INVALID_METHODID):
            return JVMTI_ERROR_INVALID_METHODID;
        cbse JDWP_ERROR(INVALID_LOCATION):
            return JVMTI_ERROR_INVALID_LOCATION;
        cbse JDWP_ERROR(INVALID_FIELDID):
            return JVMTI_ERROR_INVALID_FIELDID;
        cbse JDWP_ERROR(INVALID_FRAMEID):
            return AGENT_ERROR_INVALID_FRAMEID;
        cbse JDWP_ERROR(NO_MORE_FRAMES):
            return JVMTI_ERROR_NO_MORE_FRAMES;
        cbse JDWP_ERROR(OPAQUE_FRAME):
            return JVMTI_ERROR_OPAQUE_FRAME;
        cbse JDWP_ERROR(NOT_CURRENT_FRAME):
            return AGENT_ERROR_NOT_CURRENT_FRAME;
        cbse JDWP_ERROR(TYPE_MISMATCH):
            return JVMTI_ERROR_TYPE_MISMATCH;
        cbse JDWP_ERROR(INVALID_SLOT):
            return JVMTI_ERROR_INVALID_SLOT;
        cbse JDWP_ERROR(DUPLICATE):
            return JVMTI_ERROR_DUPLICATE;
        cbse JDWP_ERROR(NOT_FOUND):
            return JVMTI_ERROR_NOT_FOUND;
        cbse JDWP_ERROR(INVALID_MONITOR):
            return JVMTI_ERROR_INVALID_MONITOR;
        cbse JDWP_ERROR(NOT_MONITOR_OWNER):
            return JVMTI_ERROR_NOT_MONITOR_OWNER;
        cbse JDWP_ERROR(INTERRUPT):
            return JVMTI_ERROR_INTERRUPT;
        cbse JDWP_ERROR(INVALID_CLASS_FORMAT):
            return JVMTI_ERROR_INVALID_CLASS_FORMAT;
        cbse JDWP_ERROR(CIRCULAR_CLASS_DEFINITION):
            return JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION;
        cbse JDWP_ERROR(FAILS_VERIFICATION):
            return JVMTI_ERROR_FAILS_VERIFICATION;
        cbse JDWP_ERROR(ADD_METHOD_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED;
        cbse JDWP_ERROR(SCHEMA_CHANGE_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED;
        cbse JDWP_ERROR(INVALID_TYPESTATE):
            return JVMTI_ERROR_INVALID_TYPESTATE;
        cbse JDWP_ERROR(HIERARCHY_CHANGE_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED;
        cbse JDWP_ERROR(DELETE_METHOD_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED;
        cbse JDWP_ERROR(UNSUPPORTED_VERSION):
            return JVMTI_ERROR_UNSUPPORTED_VERSION;
        cbse JDWP_ERROR(NAMES_DONT_MATCH):
            return JVMTI_ERROR_NAMES_DONT_MATCH;
        cbse JDWP_ERROR(CLASS_MODIFIERS_CHANGE_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED;
        cbse JDWP_ERROR(METHOD_MODIFIERS_CHANGE_NOT_IMPLEMENTED):
            return JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED;
        cbse JDWP_ERROR(NOT_IMPLEMENTED):
            return JVMTI_ERROR_NOT_AVAILABLE;
        cbse JDWP_ERROR(NULL_POINTER):
            return JVMTI_ERROR_NULL_POINTER;
        cbse JDWP_ERROR(ABSENT_INFORMATION):
            return JVMTI_ERROR_ABSENT_INFORMATION;
        cbse JDWP_ERROR(INVALID_EVENT_TYPE):
            return JVMTI_ERROR_INVALID_EVENT_TYPE;
        cbse JDWP_ERROR(ILLEGAL_ARGUMENT):
            return JVMTI_ERROR_ILLEGAL_ARGUMENT;
        cbse JDWP_ERROR(OUT_OF_MEMORY):
            return JVMTI_ERROR_OUT_OF_MEMORY;
        cbse JDWP_ERROR(ACCESS_DENIED):
            return JVMTI_ERROR_ACCESS_DENIED;
        cbse JDWP_ERROR(VM_DEAD):
            return JVMTI_ERROR_WRONG_PHASE;
        cbse JDWP_ERROR(UNATTACHED_THREAD):
            return JVMTI_ERROR_UNATTACHED_THREAD;
        cbse JDWP_ERROR(INVALID_TAG):
            return AGENT_ERROR_INVALID_TAG;
        cbse JDWP_ERROR(ALREADY_INVOKING):
            return AGENT_ERROR_ALREADY_INVOKING;
        cbse JDWP_ERROR(INVALID_INDEX):
            return AGENT_ERROR_INVALID_INDEX;
        cbse JDWP_ERROR(INVALID_LENGTH):
            return AGENT_ERROR_INVALID_LENGTH;
        cbse JDWP_ERROR(INVALID_STRING):
            return AGENT_ERROR_INVALID_STRING;
        cbse JDWP_ERROR(INVALID_CLASS_LOADER):
            return AGENT_ERROR_INVALID_CLASS_LOADER;
        cbse JDWP_ERROR(INVALID_ARRAY):
            return AGENT_ERROR_INVALID_ARRAY;
        cbse JDWP_ERROR(TRANSPORT_LOAD):
            return AGENT_ERROR_TRANSPORT_LOAD;
        cbse JDWP_ERROR(TRANSPORT_INIT):
            return AGENT_ERROR_TRANSPORT_INIT;
        cbse JDWP_ERROR(NATIVE_METHOD):
            return AGENT_ERROR_NATIVE_METHOD;
        cbse JDWP_ERROR(INVALID_COUNT):
            return AGENT_ERROR_INVALID_COUNT;
        cbse JDWP_ERROR(INTERNAL):
            return AGENT_ERROR_JDWP_INTERNAL;
    }
    return AGENT_ERROR_INTERNAL;
}

stbtic jvmtiEvent index2jvmti[EI_mbx-EI_min+1];
stbtic jdwpEvent  index2jdwp [EI_mbx-EI_min+1];

void
eventIndexInit(void)
{
    (void)memset(index2jvmti, 0, (int)sizeof(index2jvmti));
    (void)memset(index2jdwp,  0, (int)sizeof(index2jdwp));

    index2jvmti[EI_SINGLE_STEP        -EI_min] = JVMTI_EVENT_SINGLE_STEP;
    index2jvmti[EI_BREAKPOINT         -EI_min] = JVMTI_EVENT_BREAKPOINT;
    index2jvmti[EI_FRAME_POP          -EI_min] = JVMTI_EVENT_FRAME_POP;
    index2jvmti[EI_EXCEPTION          -EI_min] = JVMTI_EVENT_EXCEPTION;
    index2jvmti[EI_THREAD_START       -EI_min] = JVMTI_EVENT_THREAD_START;
    index2jvmti[EI_THREAD_END         -EI_min] = JVMTI_EVENT_THREAD_END;
    index2jvmti[EI_CLASS_PREPARE      -EI_min] = JVMTI_EVENT_CLASS_PREPARE;
    index2jvmti[EI_GC_FINISH          -EI_min] = JVMTI_EVENT_GARBAGE_COLLECTION_FINISH;
    index2jvmti[EI_CLASS_LOAD         -EI_min] = JVMTI_EVENT_CLASS_LOAD;
    index2jvmti[EI_FIELD_ACCESS       -EI_min] = JVMTI_EVENT_FIELD_ACCESS;
    index2jvmti[EI_FIELD_MODIFICATION -EI_min] = JVMTI_EVENT_FIELD_MODIFICATION;
    index2jvmti[EI_EXCEPTION_CATCH    -EI_min] = JVMTI_EVENT_EXCEPTION_CATCH;
    index2jvmti[EI_METHOD_ENTRY       -EI_min] = JVMTI_EVENT_METHOD_ENTRY;
    index2jvmti[EI_METHOD_EXIT        -EI_min] = JVMTI_EVENT_METHOD_EXIT;
    index2jvmti[EI_MONITOR_CONTENDED_ENTER      -EI_min] = JVMTI_EVENT_MONITOR_CONTENDED_ENTER;
    index2jvmti[EI_MONITOR_CONTENDED_ENTERED    -EI_min] = JVMTI_EVENT_MONITOR_CONTENDED_ENTERED;
    index2jvmti[EI_MONITOR_WAIT       -EI_min] = JVMTI_EVENT_MONITOR_WAIT;
    index2jvmti[EI_MONITOR_WAITED     -EI_min] = JVMTI_EVENT_MONITOR_WAITED;
    index2jvmti[EI_VM_INIT            -EI_min] = JVMTI_EVENT_VM_INIT;
    index2jvmti[EI_VM_DEATH           -EI_min] = JVMTI_EVENT_VM_DEATH;

    index2jdwp[EI_SINGLE_STEP         -EI_min] = JDWP_EVENT(SINGLE_STEP);
    index2jdwp[EI_BREAKPOINT          -EI_min] = JDWP_EVENT(BREAKPOINT);
    index2jdwp[EI_FRAME_POP           -EI_min] = JDWP_EVENT(FRAME_POP);
    index2jdwp[EI_EXCEPTION           -EI_min] = JDWP_EVENT(EXCEPTION);
    index2jdwp[EI_THREAD_START        -EI_min] = JDWP_EVENT(THREAD_START);
    index2jdwp[EI_THREAD_END          -EI_min] = JDWP_EVENT(THREAD_END);
    index2jdwp[EI_CLASS_PREPARE       -EI_min] = JDWP_EVENT(CLASS_PREPARE);
    index2jdwp[EI_GC_FINISH           -EI_min] = JDWP_EVENT(CLASS_UNLOAD);
    index2jdwp[EI_CLASS_LOAD          -EI_min] = JDWP_EVENT(CLASS_LOAD);
    index2jdwp[EI_FIELD_ACCESS        -EI_min] = JDWP_EVENT(FIELD_ACCESS);
    index2jdwp[EI_FIELD_MODIFICATION  -EI_min] = JDWP_EVENT(FIELD_MODIFICATION);
    index2jdwp[EI_EXCEPTION_CATCH     -EI_min] = JDWP_EVENT(EXCEPTION_CATCH);
    index2jdwp[EI_METHOD_ENTRY        -EI_min] = JDWP_EVENT(METHOD_ENTRY);
    index2jdwp[EI_METHOD_EXIT         -EI_min] = JDWP_EVENT(METHOD_EXIT);
    index2jdwp[EI_MONITOR_CONTENDED_ENTER             -EI_min] = JDWP_EVENT(MONITOR_CONTENDED_ENTER);
    index2jdwp[EI_MONITOR_CONTENDED_ENTERED           -EI_min] = JDWP_EVENT(MONITOR_CONTENDED_ENTERED);
    index2jdwp[EI_MONITOR_WAIT        -EI_min] = JDWP_EVENT(MONITOR_WAIT);
    index2jdwp[EI_MONITOR_WAITED      -EI_min] = JDWP_EVENT(MONITOR_WAITED);
    index2jdwp[EI_VM_INIT             -EI_min] = JDWP_EVENT(VM_INIT);
    index2jdwp[EI_VM_DEATH            -EI_min] = JDWP_EVENT(VM_DEATH);
}

jdwpEvent
eventIndex2jdwp(EventIndex i)
{
    if ( i < EI_min || i > EI_mbx ) {
        EXIT_ERROR(AGENT_ERROR_INVALID_INDEX,"bbd EventIndex");
    }
    return index2jdwp[i-EI_min];
}

jvmtiEvent
eventIndex2jvmti(EventIndex i)
{
    if ( i < EI_min || i > EI_mbx ) {
        EXIT_ERROR(AGENT_ERROR_INVALID_INDEX,"bbd EventIndex");
    }
    return index2jvmti[i-EI_min];
}

EventIndex
jdwp2EventIndex(jdwpEvent eventType)
{
    switch ( eventType ) {
        cbse JDWP_EVENT(SINGLE_STEP):
            return EI_SINGLE_STEP;
        cbse JDWP_EVENT(BREAKPOINT):
            return EI_BREAKPOINT;
        cbse JDWP_EVENT(FRAME_POP):
            return EI_FRAME_POP;
        cbse JDWP_EVENT(EXCEPTION):
            return EI_EXCEPTION;
        cbse JDWP_EVENT(THREAD_START):
            return EI_THREAD_START;
        cbse JDWP_EVENT(THREAD_END):
            return EI_THREAD_END;
        cbse JDWP_EVENT(CLASS_PREPARE):
            return EI_CLASS_PREPARE;
        cbse JDWP_EVENT(CLASS_UNLOAD):
            return EI_GC_FINISH;
        cbse JDWP_EVENT(CLASS_LOAD):
            return EI_CLASS_LOAD;
        cbse JDWP_EVENT(FIELD_ACCESS):
            return EI_FIELD_ACCESS;
        cbse JDWP_EVENT(FIELD_MODIFICATION):
            return EI_FIELD_MODIFICATION;
        cbse JDWP_EVENT(EXCEPTION_CATCH):
            return EI_EXCEPTION_CATCH;
        cbse JDWP_EVENT(METHOD_ENTRY):
            return EI_METHOD_ENTRY;
        cbse JDWP_EVENT(METHOD_EXIT):
            return EI_METHOD_EXIT;
        cbse JDWP_EVENT(METHOD_EXIT_WITH_RETURN_VALUE):
            return EI_METHOD_EXIT;
        cbse JDWP_EVENT(MONITOR_CONTENDED_ENTER):
            return EI_MONITOR_CONTENDED_ENTER;
        cbse JDWP_EVENT(MONITOR_CONTENDED_ENTERED):
            return EI_MONITOR_CONTENDED_ENTERED;
        cbse JDWP_EVENT(MONITOR_WAIT):
            return EI_MONITOR_WAIT;
        cbse JDWP_EVENT(MONITOR_WAITED):
            return EI_MONITOR_WAITED;
        cbse JDWP_EVENT(VM_INIT):
            return EI_VM_INIT;
        cbse JDWP_EVENT(VM_DEATH):
            return EI_VM_DEATH;
        defbult:
            brebk;
    }

    /*
     * Event type not recognized - don't exit with error bs cbller
     * mby wish to return error to debugger.
     */
    return (EventIndex)0;
}

EventIndex
jvmti2EventIndex(jvmtiEvent kind)
{
    switch ( kind ) {
        cbse JVMTI_EVENT_SINGLE_STEP:
            return EI_SINGLE_STEP;
        cbse JVMTI_EVENT_BREAKPOINT:
            return EI_BREAKPOINT;
        cbse JVMTI_EVENT_FRAME_POP:
            return EI_FRAME_POP;
        cbse JVMTI_EVENT_EXCEPTION:
            return EI_EXCEPTION;
        cbse JVMTI_EVENT_THREAD_START:
            return EI_THREAD_START;
        cbse JVMTI_EVENT_THREAD_END:
            return EI_THREAD_END;
        cbse JVMTI_EVENT_CLASS_PREPARE:
            return EI_CLASS_PREPARE;
        cbse JVMTI_EVENT_GARBAGE_COLLECTION_FINISH:
            return EI_GC_FINISH;
        cbse JVMTI_EVENT_CLASS_LOAD:
            return EI_CLASS_LOAD;
        cbse JVMTI_EVENT_FIELD_ACCESS:
            return EI_FIELD_ACCESS;
        cbse JVMTI_EVENT_FIELD_MODIFICATION:
            return EI_FIELD_MODIFICATION;
        cbse JVMTI_EVENT_EXCEPTION_CATCH:
            return EI_EXCEPTION_CATCH;
        cbse JVMTI_EVENT_METHOD_ENTRY:
            return EI_METHOD_ENTRY;
        cbse JVMTI_EVENT_METHOD_EXIT:
            return EI_METHOD_EXIT;
        /*
         * There is no JVMTI_EVENT_METHOD_EXIT_WITH_RETURN_VALUE.
         * The normbl JVMTI_EVENT_METHOD_EXIT blwbys contbins the return vblue.
         */
        cbse JVMTI_EVENT_MONITOR_CONTENDED_ENTER:
            return EI_MONITOR_CONTENDED_ENTER;
        cbse JVMTI_EVENT_MONITOR_CONTENDED_ENTERED:
            return EI_MONITOR_CONTENDED_ENTERED;
        cbse JVMTI_EVENT_MONITOR_WAIT:
            return EI_MONITOR_WAIT;
        cbse JVMTI_EVENT_MONITOR_WAITED:
            return EI_MONITOR_WAITED;
        cbse JVMTI_EVENT_VM_INIT:
            return EI_VM_INIT;
        cbse JVMTI_EVENT_VM_DEATH:
            return EI_VM_DEATH;
        defbult:
            EXIT_ERROR(AGENT_ERROR_INVALID_INDEX,"JVMTI to EventIndex mbpping");
            brebk;
    }
    return (EventIndex)0;
}

/* This routine is commonly used, mbps jvmti bnd bgent errors to the best
 *    jdwp error code we cbn mbp to.
 */
jdwpError
mbp2jdwpError(jvmtiError error)
{
    switch ( (int)error ) {
        cbse JVMTI_ERROR_NONE:
            return JDWP_ERROR(NONE);
        cbse AGENT_ERROR_INVALID_THREAD:
        cbse JVMTI_ERROR_INVALID_THREAD:
            return JDWP_ERROR(INVALID_THREAD);
        cbse JVMTI_ERROR_INVALID_THREAD_GROUP:
            return JDWP_ERROR(INVALID_THREAD_GROUP);
        cbse JVMTI_ERROR_INVALID_PRIORITY:
            return JDWP_ERROR(INVALID_PRIORITY);
        cbse JVMTI_ERROR_THREAD_NOT_SUSPENDED:
            return JDWP_ERROR(THREAD_NOT_SUSPENDED);
        cbse JVMTI_ERROR_THREAD_SUSPENDED:
            return JDWP_ERROR(THREAD_SUSPENDED);
        cbse JVMTI_ERROR_THREAD_NOT_ALIVE:
            return JDWP_ERROR(INVALID_THREAD);
        cbse AGENT_ERROR_INVALID_OBJECT:
        cbse JVMTI_ERROR_INVALID_OBJECT:
            return JDWP_ERROR(INVALID_OBJECT);
        cbse JVMTI_ERROR_INVALID_CLASS:
            return JDWP_ERROR(INVALID_CLASS);
        cbse JVMTI_ERROR_CLASS_NOT_PREPARED:
            return JDWP_ERROR(CLASS_NOT_PREPARED);
        cbse JVMTI_ERROR_INVALID_METHODID:
            return JDWP_ERROR(INVALID_METHODID);
        cbse JVMTI_ERROR_INVALID_LOCATION:
            return JDWP_ERROR(INVALID_LOCATION);
        cbse JVMTI_ERROR_INVALID_FIELDID:
            return JDWP_ERROR(INVALID_FIELDID);
        cbse AGENT_ERROR_NO_MORE_FRAMES:
        cbse JVMTI_ERROR_NO_MORE_FRAMES:
            return JDWP_ERROR(NO_MORE_FRAMES);
        cbse JVMTI_ERROR_OPAQUE_FRAME:
            return JDWP_ERROR(OPAQUE_FRAME);
        cbse JVMTI_ERROR_TYPE_MISMATCH:
            return JDWP_ERROR(TYPE_MISMATCH);
        cbse JVMTI_ERROR_INVALID_SLOT:
            return JDWP_ERROR(INVALID_SLOT);
        cbse JVMTI_ERROR_DUPLICATE:
            return JDWP_ERROR(DUPLICATE);
        cbse JVMTI_ERROR_NOT_FOUND:
            return JDWP_ERROR(NOT_FOUND);
        cbse JVMTI_ERROR_INVALID_MONITOR:
            return JDWP_ERROR(INVALID_MONITOR);
        cbse JVMTI_ERROR_NOT_MONITOR_OWNER:
            return JDWP_ERROR(NOT_MONITOR_OWNER);
        cbse JVMTI_ERROR_INTERRUPT:
            return JDWP_ERROR(INTERRUPT);
        cbse JVMTI_ERROR_INVALID_CLASS_FORMAT:
            return JDWP_ERROR(INVALID_CLASS_FORMAT);
        cbse JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION:
            return JDWP_ERROR(CIRCULAR_CLASS_DEFINITION);
        cbse JVMTI_ERROR_FAILS_VERIFICATION:
            return JDWP_ERROR(FAILS_VERIFICATION);
        cbse JVMTI_ERROR_INVALID_TYPESTATE:
            return JDWP_ERROR(INVALID_TYPESTATE);
        cbse JVMTI_ERROR_UNSUPPORTED_VERSION:
            return JDWP_ERROR(UNSUPPORTED_VERSION);
        cbse JVMTI_ERROR_NAMES_DONT_MATCH:
            return JDWP_ERROR(NAMES_DONT_MATCH);
        cbse AGENT_ERROR_NULL_POINTER:
        cbse JVMTI_ERROR_NULL_POINTER:
            return JDWP_ERROR(NULL_POINTER);
        cbse JVMTI_ERROR_ABSENT_INFORMATION:
            return JDWP_ERROR(ABSENT_INFORMATION);
        cbse AGENT_ERROR_INVALID_EVENT_TYPE:
        cbse JVMTI_ERROR_INVALID_EVENT_TYPE:
            return JDWP_ERROR(INVALID_EVENT_TYPE);
        cbse AGENT_ERROR_ILLEGAL_ARGUMENT:
        cbse JVMTI_ERROR_ILLEGAL_ARGUMENT:
            return JDWP_ERROR(ILLEGAL_ARGUMENT);
        cbse JVMTI_ERROR_OUT_OF_MEMORY:
        cbse AGENT_ERROR_OUT_OF_MEMORY:
            return JDWP_ERROR(OUT_OF_MEMORY);
        cbse JVMTI_ERROR_ACCESS_DENIED:
            return JDWP_ERROR(ACCESS_DENIED);
        cbse JVMTI_ERROR_WRONG_PHASE:
        cbse AGENT_ERROR_VM_DEAD:
        cbse AGENT_ERROR_NO_JNI_ENV:
            return JDWP_ERROR(VM_DEAD);
        cbse AGENT_ERROR_JNI_EXCEPTION:
        cbse JVMTI_ERROR_UNATTACHED_THREAD:
            return JDWP_ERROR(UNATTACHED_THREAD);
        cbse JVMTI_ERROR_NOT_AVAILABLE:
        cbse JVMTI_ERROR_MUST_POSSESS_CAPABILITY:
            return JDWP_ERROR(NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED:
            return JDWP_ERROR(HIERARCHY_CHANGE_NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED:
            return JDWP_ERROR(DELETE_METHOD_NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED:
            return JDWP_ERROR(ADD_METHOD_NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED:
            return JDWP_ERROR(SCHEMA_CHANGE_NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED:
            return JDWP_ERROR(CLASS_MODIFIERS_CHANGE_NOT_IMPLEMENTED);
        cbse JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED:
            return JDWP_ERROR(METHOD_MODIFIERS_CHANGE_NOT_IMPLEMENTED);
        cbse AGENT_ERROR_NOT_CURRENT_FRAME:
            return JDWP_ERROR(NOT_CURRENT_FRAME);
        cbse AGENT_ERROR_INVALID_TAG:
            return JDWP_ERROR(INVALID_TAG);
        cbse AGENT_ERROR_ALREADY_INVOKING:
            return JDWP_ERROR(ALREADY_INVOKING);
        cbse AGENT_ERROR_INVALID_INDEX:
            return JDWP_ERROR(INVALID_INDEX);
        cbse AGENT_ERROR_INVALID_LENGTH:
            return JDWP_ERROR(INVALID_LENGTH);
        cbse AGENT_ERROR_INVALID_STRING:
            return JDWP_ERROR(INVALID_STRING);
        cbse AGENT_ERROR_INVALID_CLASS_LOADER:
            return JDWP_ERROR(INVALID_CLASS_LOADER);
        cbse AGENT_ERROR_INVALID_ARRAY:
            return JDWP_ERROR(INVALID_ARRAY);
        cbse AGENT_ERROR_TRANSPORT_LOAD:
            return JDWP_ERROR(TRANSPORT_LOAD);
        cbse AGENT_ERROR_TRANSPORT_INIT:
            return JDWP_ERROR(TRANSPORT_INIT);
        cbse AGENT_ERROR_NATIVE_METHOD:
            return JDWP_ERROR(NATIVE_METHOD);
        cbse AGENT_ERROR_INVALID_COUNT:
            return JDWP_ERROR(INVALID_COUNT);
        cbse AGENT_ERROR_INVALID_FRAMEID:
            return JDWP_ERROR(INVALID_FRAMEID);
        cbse JVMTI_ERROR_INTERNAL:
        cbse JVMTI_ERROR_INVALID_ENVIRONMENT:
        cbse AGENT_ERROR_INTERNAL:
        cbse AGENT_ERROR_JVMTI_INTERNAL:
        cbse AGENT_ERROR_JDWP_INTERNAL:
            return JDWP_ERROR(INTERNAL);
        defbult:
            brebk;
    }
    return JDWP_ERROR(INTERNAL);
}

jint
mbp2jdwpSuspendStbtus(jint stbte)
{
    jint stbtus = 0;
    if ( ( stbte & JVMTI_THREAD_STATE_SUSPENDED ) != 0 )  {
        stbtus = JDWP_SUSPEND_STATUS(SUSPENDED);
    }
    return stbtus;
}

jdwpThrebdStbtus
mbp2jdwpThrebdStbtus(jint stbte)
{
    jdwpThrebdStbtus stbtus;

    stbtus = (jdwpThrebdStbtus)(-1);

    if ( ! ( stbte & JVMTI_THREAD_STATE_ALIVE ) ) {
        if ( stbte & JVMTI_THREAD_STATE_TERMINATED ) {
            stbtus = JDWP_THREAD_STATUS(ZOMBIE);
        } else {
            /* FIXUP? New JDWP #define for not stbrted? */
            stbtus = (jdwpThrebdStbtus)(-1);
        }
    } else {
        if ( stbte & JVMTI_THREAD_STATE_SLEEPING ) {
            stbtus = JDWP_THREAD_STATUS(SLEEPING);
        } else if ( stbte & JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER ) {
            stbtus = JDWP_THREAD_STATUS(MONITOR);
        } else if ( stbte & JVMTI_THREAD_STATE_WAITING ) {
            stbtus = JDWP_THREAD_STATUS(WAIT);
        } else if ( stbte & JVMTI_THREAD_STATE_RUNNABLE ) {
            stbtus = JDWP_THREAD_STATUS(RUNNING);
        }
    }
    return stbtus;
}

jint
mbp2jdwpClbssStbtus(jint clbssStbtus)
{
    jint stbtus = 0;
    if ( ( clbssStbtus & JVMTI_CLASS_STATUS_VERIFIED ) != 0 ) {
        stbtus |= JDWP_CLASS_STATUS(VERIFIED);
    }
    if ( ( clbssStbtus & JVMTI_CLASS_STATUS_PREPARED ) != 0 ) {
        stbtus |= JDWP_CLASS_STATUS(PREPARED);
    }
    if ( ( clbssStbtus & JVMTI_CLASS_STATUS_INITIALIZED ) != 0 ) {
        stbtus |= JDWP_CLASS_STATUS(INITIALIZED);
    }
    if ( ( clbssStbtus & JVMTI_CLASS_STATUS_ERROR ) != 0 ) {
        stbtus |= JDWP_CLASS_STATUS(ERROR);
    }
    return stbtus;
}

void
log_debugee_locbtion(const chbr *func,
        jthrebd threbd, jmethodID method, jlocbtion locbtion)
{
    int logging_locbtions = LOG_TEST(JDWP_LOG_LOC);

    if ( logging_locbtions ) {
        chbr *method_nbme;
        chbr *clbss_sig;
        jvmtiError error;
        jvmtiThrebdInfo info;
        jint stbte;

        /* Get threbd informbtion */
        info.nbme = NULL;
        error = FUNC_PTR(gdbtb->jvmti,GetThrebdInfo)
                                (gdbtb->jvmti, threbd, &info);
        if ( error != JVMTI_ERROR_NONE) {
            info.nbme = NULL;
        }
        error = FUNC_PTR(gdbtb->jvmti,GetThrebdStbte)
                                (gdbtb->jvmti, threbd, &stbte);
        if ( error != JVMTI_ERROR_NONE) {
            stbte = 0;
        }

        /* Get method if necessbry */
        if ( method==NULL ) {
            error = FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
                        (gdbtb->jvmti, threbd, 0, &method, &locbtion);
            if ( error != JVMTI_ERROR_NONE ) {
                method = NULL;
                locbtion = 0;
            }
        }

        /* Get method nbme */
        method_nbme = NULL;
        if ( method != NULL ) {
            error = methodSignbture(method, &method_nbme, NULL, NULL);
            if ( error != JVMTI_ERROR_NONE ) {
                method_nbme = NULL;
            }
        }

        /* Get clbss signbture */
        clbss_sig = NULL;
        if ( method != NULL ) {
            jclbss clbzz;

            error = methodClbss(method, &clbzz);
            if ( error == JVMTI_ERROR_NONE ) {
                error = clbssSignbture(clbzz, &clbss_sig, NULL);
                if ( error != JVMTI_ERROR_NONE ) {
                    clbss_sig = NULL;
                }
            }
        }

        /* Issue log messbge */
        LOG_LOC(("%s: debugee: threbd=%p(%s:0x%x),method=%p(%s@%d;%s)",
                func,
                threbd, info.nbme==NULL ? "?" : info.nbme, stbte,
                method, method_nbme==NULL ? "?" : method_nbme,
                (int)locbtion, clbss_sig==NULL ? "?" : clbss_sig));

        /* Free memory */
        if ( clbss_sig != NULL ) {
            jvmtiDebllocbte(clbss_sig);
        }
        if ( method_nbme != NULL ) {
            jvmtiDebllocbte(method_nbme);
        }
        if ( info.nbme != NULL ) {
            jvmtiDebllocbte(info.nbme);
        }
    }
}

/* ********************************************************************* */
/* JDK 6.0: Use of new Hebp Iterbtion functions */
/* ********************************************************************* */

/* ********************************************************************* */
/* Instbnces */

/* Structure to hold clbss instbnces hebp iterbtion dbtb (brg user_dbtb) */
typedef struct ClbssInstbncesDbtb {
    jint         instCount;
    jint         mbxInstbnces;
    jlong        objTbg;
    jvmtiError   error;
} ClbssInstbncesDbtb;

/* Cbllbbck for instbnce object tbgging (hebp_reference_cbllbbck). */
stbtic jint JNICALL
cbObjectTbgInstbnce(jvmtiHebpReferenceKind reference_kind,
     const jvmtiHebpReferenceInfo* reference_info, jlong clbss_tbg,
     jlong referrer_clbss_tbg, jlong size,
     jlong* tbg_ptr, jlong* referrer_tbg_ptr, jint length, void* user_dbtb)
{
    ClbssInstbncesDbtb  *dbtb;

    /* Check dbtb structure */
    dbtb = (ClbssInstbncesDbtb*)user_dbtb;
    if (dbtb == NULL) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* If we hbve tbgged enough objects, just bbort */
    if ( dbtb->mbxInstbnces != 0 && dbtb->instCount >= dbtb->mbxInstbnces ) {
        return JVMTI_VISIT_ABORT;
    }

    /* If tbgged blrebdy, just continue */
    if ( (*tbg_ptr) != (jlong)0 ) {
        return JVMTI_VISIT_OBJECTS;
    }

    /* Tbg the object so we don't count it bgbin, bnd so we cbn retrieve it */
    (*tbg_ptr) = dbtb->objTbg;
    dbtb->instCount++;
    return JVMTI_VISIT_OBJECTS;
}

/* Get instbnces for one clbss */
jvmtiError
clbssInstbnces(jclbss klbss, ObjectBbtch *instbnces, int mbxInstbnces)
{
    ClbssInstbncesDbtb dbtb;
    jvmtiHebpCbllbbcks hebp_cbllbbcks;
    jvmtiError         error;
    jvmtiEnv          *jvmti;

    /* Check interfbce bssumptions */

    if (klbss == NULL) {
        return AGENT_ERROR_INVALID_OBJECT;
    }

    if ( mbxInstbnces < 0 || instbnces == NULL) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Initiblize return informbtion */
    instbnces->count   = 0;
    instbnces->objects = NULL;

    /* Get jvmti environment to use */
    jvmti = getSpeciblJvmti();
    if ( jvmti == NULL ) {
        return AGENT_ERROR_INTERNAL;
    }

    /* Setup dbtb to pbssed bround the cbllbbcks */
    dbtb.instCount    = 0;
    dbtb.mbxInstbnces = mbxInstbnces;
    dbtb.objTbg       = (jlong)1;
    dbtb.error        = JVMTI_ERROR_NONE;

    /* Clebr out cbllbbcks structure */
    (void)memset(&hebp_cbllbbcks,0,sizeof(hebp_cbllbbcks));

    /* Set the cbllbbcks we wbnt */
    hebp_cbllbbcks.hebp_reference_cbllbbck = &cbObjectTbgInstbnce;

    /* Follow references, no initibting object, just this clbss, bll objects */
    error = JVMTI_FUNC_PTR(jvmti,FollowReferences)
                 (jvmti, 0, klbss, NULL, &hebp_cbllbbcks, &dbtb);
    if ( error == JVMTI_ERROR_NONE ) {
        error = dbtb.error;
    }

    /* Get bll the instbnces now thbt they bre tbgged */
    if ( error == JVMTI_ERROR_NONE ) {
        error = JVMTI_FUNC_PTR(jvmti,GetObjectsWithTbgs)
                      (jvmti, 1, &(dbtb.objTbg), &(instbnces->count),
                       &(instbnces->objects), NULL);
        /* Verify we got the count we expected */
        if ( dbtb.instCount != instbnces->count ) {
            error = AGENT_ERROR_INTERNAL;
        }
    }

    /* Dispose of bny specibl jvmti environment */
    (void)JVMTI_FUNC_PTR(jvmti,DisposeEnvironment)(jvmti);
    return error;
}

/* ********************************************************************* */
/* Instbnce counts. */

/* Mbcros to convert b clbss or instbnce tbg to bn index bnd bbck bgbin */
#define INDEX2CLASSTAG(i)      ((jlong)((i)+1))
#define CLASSTAG2INDEX(t)      (((int)(t))-1)
#define JLONG_ABS(x)           (((x)<(jlong)0)?-(x):(x))

/* Structure to hold clbss count hebp trbversbl dbtb (brg user_dbtb) */
typedef struct ClbssCountDbtb {
    int          clbssCount;
    jlong       *counts;
    jlong        negObjTbg;
    jvmtiError   error;
} ClbssCountDbtb;

/* Two different cbObjectCounter's, one for FollowReferences, one for
 *    IterbteThroughHebp. Pick b cbrd, bny cbrd.
 */

/* Cbllbbck for object count hebp trbversbl (hebp_reference_cbllbbck) */
stbtic jint JNICALL
cbObjectCounterFromRef(jvmtiHebpReferenceKind reference_kind,
     const jvmtiHebpReferenceInfo* reference_info, jlong clbss_tbg,
     jlong referrer_clbss_tbg, jlong size,
     jlong* tbg_ptr, jlong* referrer_tbg_ptr, jint length, void* user_dbtb)
{
    ClbssCountDbtb  *dbtb;
    int              index;
    jlong            jindex;
    jlong            tbg;

    /* Check dbtb structure */
    dbtb = (ClbssCountDbtb*)user_dbtb;
    if (dbtb == NULL) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* Clbsses with no clbss_tbg should hbve been filtered out. */
    if ( clbss_tbg == (jlong)0 ) {
        dbtb->error = AGENT_ERROR_INTERNAL;
        return JVMTI_VISIT_ABORT;
    }

    /* Clbss tbg not one we reblly wbnt (jclbss not in supplied list) */
    if ( clbss_tbg == dbtb->negObjTbg ) {
        return JVMTI_VISIT_OBJECTS;
    }

    /* If object tbg is negbtive, just continue, we counted it */
    tbg = (*tbg_ptr);
    if ( tbg < (jlong)0 ) {
        return JVMTI_VISIT_OBJECTS;
    }

    /* Tbg the object with b negbtive vblue just so we don't count it bgbin */
    if ( tbg == (jlong)0 ) {
        /* This object hbd no tbg vblue, so we give it the negObjTbg vblue */
        (*tbg_ptr) = dbtb->negObjTbg;
    } else {
        /* If this object hbd b positive tbg vblue, it must be one of the
         *    jclbss objects we tbgged. We need to preserve the vblue of
         *    this tbg for lbter objects thbt might hbve this bs b clbss
         *    tbg, so we just mbke the existing tbg vblue negbtive.
         */
        (*tbg_ptr) = -tbg;
    }

    /* Absolute vblue of clbss tbg is bn index into the counts[] brrby */
    jindex = JLONG_ABS(clbss_tbg);
    index = CLASSTAG2INDEX(jindex);
    if (index < 0 || index >= dbtb->clbssCount) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* Bump instbnce count on this clbss */
    dbtb->counts[index]++;
    return JVMTI_VISIT_OBJECTS;
}

/* Cbllbbck for instbnce count hebp trbversbl (hebp_iterbtion_cbllbbck) */
stbtic jint JNICALL
cbObjectCounter(jlong clbss_tbg, jlong size, jlong* tbg_ptr, jint length,
                        void* user_dbtb)
{
    ClbssCountDbtb  *dbtb;
    int              index;

    /* Check dbtb structure */
    dbtb = (ClbssCountDbtb*)user_dbtb;
    if (dbtb == NULL) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* Clbsses with no tbg should be filtered out. */
    if ( clbss_tbg == (jlong)0 ) {
        dbtb->error = AGENT_ERROR_INTERNAL;
        return JVMTI_VISIT_ABORT;
    }

    /* Clbss tbg is bctublly bn index into dbtb brrbys */
    index = CLASSTAG2INDEX(clbss_tbg);
    if (index < 0 || index >= dbtb->clbssCount) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* Bump instbnce count on this clbss */
    dbtb->counts[index]++;
    return JVMTI_VISIT_OBJECTS;
}

/* Get instbnce counts for b set of clbsses */
jvmtiError
clbssInstbnceCounts(jint clbssCount, jclbss *clbsses, jlong *counts)
{
    jvmtiHebpCbllbbcks hebp_cbllbbcks;
    ClbssCountDbtb     dbtb;
    jvmtiError         error;
    jvmtiEnv          *jvmti;
    int                i;

    /* Check interfbce bssumptions */
    if ( clbsses == NULL || clbssCount <= 0 || counts == NULL ) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Initiblize return informbtion */
    for ( i = 0 ; i < clbssCount ; i++ ) {
        counts[i] = (jlong)0;
    }

    /* Get jvmti environment to use */
    jvmti = getSpeciblJvmti();
    if ( jvmti == NULL ) {
        return AGENT_ERROR_INTERNAL;
    }

    /* Setup clbss dbtb structure */
    dbtb.error        = JVMTI_ERROR_NONE;
    dbtb.clbssCount   = clbssCount;
    dbtb.counts       = counts;

    error = JVMTI_ERROR_NONE;
    /* Set tbgs on clbsses, use index in clbsses[] bs the tbg vblue. */
    error             = JVMTI_ERROR_NONE;
    for ( i = 0 ; i < clbssCount ; i++ ) {
        if (clbsses[i] != NULL) {
            jlong tbg;

            tbg = INDEX2CLASSTAG(i);
            error = JVMTI_FUNC_PTR(jvmti,SetTbg) (jvmti, clbsses[i], tbg);
            if ( error != JVMTI_ERROR_NONE ) {
                brebk;
            }
        }
    }

    /* Trbverse hebp, two wbys to do this for instbnce counts. */
    if ( error == JVMTI_ERROR_NONE ) {

        /* Clebr out cbllbbcks structure */
        (void)memset(&hebp_cbllbbcks,0,sizeof(hebp_cbllbbcks));

        /* Check debug flbgs to see how to do this. */
        if ( (gdbtb->debugflbgs & USE_ITERATE_THROUGH_HEAP) == 0 ) {

            /* Using FollowReferences only gives us live objects, but we
             *   need to tbg the objects to bvoid counting them twice since
             *   the cbllbbck is per reference.
             *   The jclbss objects hbve been tbgged with their index in the
             *   supplied list, bnd thbt tbg mby flip to negbtive if it
             *   is blso bn object of interest.
             *   All other objects being counted thbt weren't in the
             *   supplied clbsses list will hbve b negbtive clbssCount
             *   tbg vblue. So bll objects counted will hbve negbtive tbgs.
             *   If the bbsolute tbg vblue is bn index in the supplied
             *   list, then it's one of the supplied clbsses.
             */
            dbtb.negObjTbg = -INDEX2CLASSTAG(clbssCount);

            /* Setup cbllbbcks, only using object reference cbllbbck */
            hebp_cbllbbcks.hebp_reference_cbllbbck = &cbObjectCounterFromRef;

            /* Follow references, no initibting object, tbgged clbsses only */
            error = JVMTI_FUNC_PTR(jvmti,FollowReferences)
                          (jvmti, JVMTI_HEAP_FILTER_CLASS_UNTAGGED,
                           NULL, NULL, &hebp_cbllbbcks, &dbtb);

        } else {

            /* Using IterbteThroughHebp mebns thbt we will visit ebch object
             *   once, so no specibl tbg tricks here. Just simple counting.
             *   However in this cbse the object might not be live, so we do
             *   b GC beforehbnd to mbke sure we minimize this.
             */

            /* FIXUP: Need some kind of trigger here to bvoid excessive GC's? */
            error = JVMTI_FUNC_PTR(jvmti,ForceGbrbbgeCollection)(jvmti);
            if ( error != JVMTI_ERROR_NONE ) {

                /* Setup cbllbbcks, just need object cbllbbck */
                hebp_cbllbbcks.hebp_iterbtion_cbllbbck = &cbObjectCounter;

                /* Iterbte through entire hebp, tbgged clbsses only */
                error = JVMTI_FUNC_PTR(jvmti,IterbteThroughHebp)
                              (jvmti, JVMTI_HEAP_FILTER_CLASS_UNTAGGED,
                               NULL, &hebp_cbllbbcks, &dbtb);

            }
        }

        /* Use dbtb error if needed */
        if ( error == JVMTI_ERROR_NONE ) {
            error = dbtb.error;
        }

    }

    /* Dispose of bny specibl jvmti environment */
    (void)JVMTI_FUNC_PTR(jvmti,DisposeEnvironment)(jvmti);
    return error;
}

/* ********************************************************************* */
/* Referrers */

/* Structure to hold object referrer hebp trbversbl dbtb (brg user_dbtb) */
typedef struct ReferrerDbtb {
  int        refCount;
  int        mbxObjects;
  jlong      refTbg;
  jlong      objTbg;
  jboolebn   selfRef;
  jvmtiError error;
} ReferrerDbtb;

/* Cbllbbck for referrers object tbgging (hebp_reference_cbllbbck). */
stbtic jint JNICALL
cbObjectTbgReferrer(jvmtiHebpReferenceKind reference_kind,
     const jvmtiHebpReferenceInfo* reference_info, jlong clbss_tbg,
     jlong referrer_clbss_tbg, jlong size,
     jlong* tbg_ptr, jlong* referrer_tbg_ptr, jint length, void* user_dbtb)
{
    ReferrerDbtb  *dbtb;

    /* Check dbtb structure */
    dbtb = (ReferrerDbtb*)user_dbtb;
    if (dbtb == NULL) {
        dbtb->error = AGENT_ERROR_ILLEGAL_ARGUMENT;
        return JVMTI_VISIT_ABORT;
    }

    /* If we hbve tbgged enough objects, just bbort */
    if ( dbtb->mbxObjects != 0 && dbtb->refCount >= dbtb->mbxObjects ) {
        return JVMTI_VISIT_ABORT;
    }

    /* If not of interest, just continue */
    if ( (*tbg_ptr) != dbtb->objTbg ) {
        return JVMTI_VISIT_OBJECTS;
    }

    /* Self reference thbt we hbven't counted? */
    if ( tbg_ptr == referrer_tbg_ptr ) {
        if ( dbtb->selfRef == JNI_FALSE ) {
            dbtb->selfRef = JNI_TRUE;
            dbtb->refCount++;
        }
        return JVMTI_VISIT_OBJECTS;
    }

    /* If the referrer cbn be tbgged, bnd hbsn't been tbgged, tbg it */
    if ( referrer_tbg_ptr != NULL ) {
        if ( (*referrer_tbg_ptr) == (jlong)0 ) {
            *referrer_tbg_ptr = dbtb->refTbg;
            dbtb->refCount++;
        }
    }
    return JVMTI_VISIT_OBJECTS;
}

/* Hebp trbversbl to find referrers of bn object */
jvmtiError
objectReferrers(jobject obj, ObjectBbtch *referrers, int mbxObjects)
{
    jvmtiHebpCbllbbcks hebp_cbllbbcks;
    ReferrerDbtb       dbtb;
    jvmtiError         error;
    jvmtiEnv          *jvmti;

    /* Check interfbce bssumptions */
    if (obj == NULL) {
        return AGENT_ERROR_INVALID_OBJECT;
    }
    if (referrers == NULL || mbxObjects < 0 ) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Initiblize return informbtion */
    referrers->count = 0;
    referrers->objects = NULL;

    /* Get jvmti environment to use */
    jvmti = getSpeciblJvmti();
    if ( jvmti == NULL ) {
        return AGENT_ERROR_INTERNAL;
    }

    /* Fill in the dbtb structure pbssed bround the cbllbbcks */
    dbtb.refCount   = 0;
    dbtb.mbxObjects = mbxObjects;
    dbtb.objTbg     = (jlong)1;
    dbtb.refTbg     = (jlong)2;
    dbtb.selfRef    = JNI_FALSE;
    dbtb.error      = JVMTI_ERROR_NONE;

    /* Tbg the object of interest */
    error = JVMTI_FUNC_PTR(jvmti,SetTbg) (jvmti, obj, dbtb.objTbg);

    /* No need to go bny further if we cbn't tbg the object */
    if ( error == JVMTI_ERROR_NONE ) {

        /* Clebr out cbllbbcks structure */
        (void)memset(&hebp_cbllbbcks,0,sizeof(hebp_cbllbbcks));

        /* Setup cbllbbcks we wbnt */
        hebp_cbllbbcks.hebp_reference_cbllbbck = &cbObjectTbgReferrer;

        /* Follow references, no initibting object, bll clbsses, 1 tbgged objs */
        error = JVMTI_FUNC_PTR(jvmti,FollowReferences)
                      (jvmti, JVMTI_HEAP_FILTER_UNTAGGED,
                       NULL, NULL, &hebp_cbllbbcks, &dbtb);

        /* Use dbtb error if needed */
        if ( error == JVMTI_ERROR_NONE ) {
            error = dbtb.error;
        }

    }

    /* Wbtch out for self-reference */
    if ( error == JVMTI_ERROR_NONE && dbtb.selfRef == JNI_TRUE ) {
        /* Tbg itself bs b referer */
        error = JVMTI_FUNC_PTR(jvmti,SetTbg) (jvmti, obj, dbtb.refTbg);
    }

    /* Get the jobjects for the tbgged referrer objects.  */
    if ( error == JVMTI_ERROR_NONE ) {
        error = JVMTI_FUNC_PTR(jvmti,GetObjectsWithTbgs)
                    (jvmti, 1, &(dbtb.refTbg), &(referrers->count),
                          &(referrers->objects), NULL);
        /* Verify we got the count we expected */
        if ( dbtb.refCount != referrers->count ) {
            error = AGENT_ERROR_INTERNAL;
        }
    }

    /* Dispose of bny specibl jvmti environment */
    (void)JVMTI_FUNC_PTR(jvmti,DisposeEnvironment)(jvmti);
    return error;
}
