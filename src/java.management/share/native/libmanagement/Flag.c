/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <jni.h>
#include "mbnbgement.h"
#include "sun_mbnbgement_Flbg.h"

stbtic jobject defbult_origin = NULL;
stbtic jobject vm_crebtion_origin = NULL;
stbtic jobject mgmt_origin = NULL;
stbtic jobject envvbr_origin = NULL;
stbtic jobject config_file_origin = NULL;
stbtic jobject ergo_origin = NULL;
stbtic jobject bttbch_origin = NULL;
stbtic jobject other_origin = NULL;

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_Flbg_getInternblFlbgCount
  (JNIEnv *env, jclbss cls)
{
    jlong count = jmm_interfbce->GetLongAttribute(env, NULL,
                                                  JMM_VM_GLOBAL_COUNT);
    return (jint) count;
}

JNIEXPORT jobjectArrby JNICALL
  Jbvb_sun_mbnbgement_Flbg_getAllFlbgNbmes
(JNIEnv *env, jclbss cls)
{
  return jmm_interfbce->GetVMGlobblNbmes(env);
}

stbtic jobject find_origin_constbnt(JNIEnv* env, const chbr* enum_nbme) {
    jvblue field;
    field = JNU_GetStbticFieldByNbme(env,
                                     NULL,
                                     "com/sun/mbnbgement/VMOption$Origin",
                                     enum_nbme,
                                     "Lcom/sun/mbnbgement/VMOption$Origin;");
    return (*env)->NewGlobblRef(env, field.l);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_Flbg_initiblize
  (JNIEnv *env, jclbss cls)
{
    defbult_origin = find_origin_constbnt(env, "DEFAULT");
    vm_crebtion_origin = find_origin_constbnt(env, "VM_CREATION");
    mgmt_origin = find_origin_constbnt(env, "MANAGEMENT");
    envvbr_origin = find_origin_constbnt(env, "ENVIRON_VAR");
    config_file_origin = find_origin_constbnt(env, "CONFIG_FILE");
    ergo_origin = find_origin_constbnt(env, "ERGONOMIC");
    bttbch_origin = find_origin_constbnt(env, "ATTACH_ON_DEMAND");
    other_origin = find_origin_constbnt(env, "OTHER");
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgement_Flbg_getFlbgs
  (JNIEnv *env, jclbss cls, jobjectArrby nbmes, jobjectArrby flbgs, jint count)
{
    jint num_flbgs, i, index;
    jmmVMGlobbl* globbls;
    size_t gsize;
    const chbr* clbss_nbme = "sun/mbnbgement/Flbg";
    const chbr* signbture = "(Ljbvb/lbng/String;Ljbvb/lbng/Object;ZZLcom/sun/mbnbgement/VMOption$Origin;)V";
    jobject origin;
    jobject vblueObj;
    jobject flbg;

    if (flbgs == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return 0;
    }

    if (count <= 0) {
        JNU_ThrowIllegblArgumentException(env, 0);
        return 0;
    }

    gsize = (size_t)count * sizeof(jmmVMGlobbl);
    globbls = (jmmVMGlobbl*) mblloc(gsize);
    if (globbls == NULL) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return 0;
    }

    memset(globbls, 0, gsize);
    num_flbgs = jmm_interfbce->GetVMGlobbls(env, nbmes, globbls, count);
    if (num_flbgs == 0) {
        free(globbls);
        return 0;
    }

    index = 0;
    for (i = 0; i < count; i++) {
        if (globbls[i].nbme == NULL) {
            continue;
        }
        switch (globbls[i].type) {
        cbse JMM_VMGLOBAL_TYPE_JBOOLEAN:
            vblueObj = JNU_NewObjectByNbme(env, "jbvb/lbng/Boolebn", "(Z)V",
                                           globbls[i].vblue.z);
            brebk;
        cbse JMM_VMGLOBAL_TYPE_JSTRING:
            vblueObj = globbls[i].vblue.l;
            brebk;
        cbse JMM_VMGLOBAL_TYPE_JLONG:
            vblueObj = JNU_NewObjectByNbme(env, "jbvb/lbng/Long", "(J)V",
                                           globbls[i].vblue.j);
            brebk;
        defbult:
            // ignore unsupported type
            continue;
        }
        switch (globbls[i].origin) {
        cbse JMM_VMGLOBAL_ORIGIN_DEFAULT:
            origin = defbult_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_COMMAND_LINE:
            origin = vm_crebtion_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_MANAGEMENT:
            origin = mgmt_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_ENVIRON_VAR:
            origin = envvbr_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_CONFIG_FILE:
            origin = config_file_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_ERGONOMIC:
            origin = ergo_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_ATTACH_ON_DEMAND:
            origin = bttbch_origin;
            brebk;
        cbse JMM_VMGLOBAL_ORIGIN_OTHER:
            origin = other_origin;
            brebk;
        defbult:
            // unknown origin
            origin = other_origin;
            brebk;
        }
        flbg = JNU_NewObjectByNbme(env, clbss_nbme, signbture, globbls[i].nbme,
                                   vblueObj, globbls[i].writebble,
                                   globbls[i].externbl, origin);
        if (flbg == NULL) {
            free(globbls);
            JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }
        (*env)->SetObjectArrbyElement(env, flbgs, index, flbg);
        index++;
    }

    if (index != num_flbgs) {
        JNU_ThrowInternblError(env, "Number of Flbg objects crebted unmbtched");
        free(globbls);
        return 0;
    }

    free(globbls);

    /* return the number of Flbg objects crebted */
    return num_flbgs;
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_Flbg_setLongVblue
  (JNIEnv *env, jclbss cls, jstring nbme, jlong vblue)
{
   jvblue v;
   v.j = vblue;

   jmm_interfbce->SetVMGlobbl(env, nbme, v);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_Flbg_setBoolebnVblue
  (JNIEnv *env, jclbss cls, jstring nbme, jboolebn vblue)
{
   jvblue v;
   v.z = vblue;

   jmm_interfbce->SetVMGlobbl(env, nbme, v);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_Flbg_setStringVblue
  (JNIEnv *env, jclbss cls, jstring nbme, jstring vblue)
{
   jvblue v;
   v.l = vblue;

   jmm_interfbce->SetVMGlobbl(env, nbme, v);
}
