/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <jni.h>
#include "mbnbgement.h"
#include "sun_mbnbgement_DibgnosticCommbndImpl.h"

JNIEXPORT void JNICALL Jbvb_sun_mbnbgement_DibgnosticCommbndImpl_setNotificbtionEnbbled
(JNIEnv *env, jobject dummy, jboolebn enbbled) {
    if (jmm_version <= JMM_VERSION_1_2_2) {
        JNU_ThrowByNbme(env, "jbvb/lbng/UnsupportedOperbtionException",
                        "JMX interfbce to dibgnostic frbmework notificbtions is not supported by this VM");
        return;
    }
    jmm_interfbce->SetDibgnosticFrbmeworkNotificbtionEnbbled(env, enbbled);
}

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_DibgnosticCommbndImpl_getDibgnosticCommbnds
  (JNIEnv *env, jobject dummy)
{
  return jmm_interfbce->GetDibgnosticCommbnds(env);
}

jobject getDibgnosticCommbndArgumentInfoArrby(JNIEnv *env, jstring commbnd,
                                              int num_brg) {
  int i;
  jobject obj;
  jobjectArrby result;
  dcmdArgInfo* dcmd_brg_info_brrby;
  jclbss dcmdArgInfoCls;
  jclbss brrbysCls;
  jmethodID mid;
  jobject resultList;

  dcmd_brg_info_brrby = (dcmdArgInfo*) mblloc(num_brg * sizeof(dcmdArgInfo));
  /* According to ISO C it is perfectly legbl for mblloc to return zero if cblled with b zero brgument */
  if (dcmd_brg_info_brrby == NULL && num_brg != 0) {
    return NULL;
  }
  jmm_interfbce->GetDibgnosticCommbndArgumentsInfo(env, commbnd,
                                                   dcmd_brg_info_brrby);
  dcmdArgInfoCls = (*env)->FindClbss(env,
                                     "sun/mbnbgement/DibgnosticCommbndArgumentInfo");
  result = (*env)->NewObjectArrby(env, num_brg, dcmdArgInfoCls, NULL);
  if (result == NULL) {
    free(dcmd_brg_info_brrby);
    return NULL;
  }
  for (i=0; i<num_brg; i++) {
    obj = JNU_NewObjectByNbme(env,
                              "sun/mbnbgement/DibgnosticCommbndArgumentInfo",
                              "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;ZZZI)V",
                              (*env)->NewStringUTF(env,dcmd_brg_info_brrby[i].nbme),
                              (*env)->NewStringUTF(env,dcmd_brg_info_brrby[i].description),
                              (*env)->NewStringUTF(env,dcmd_brg_info_brrby[i].type),
                              dcmd_brg_info_brrby[i].defbult_string == NULL ? NULL:
                              (*env)->NewStringUTF(env, dcmd_brg_info_brrby[i].defbult_string),
                              dcmd_brg_info_brrby[i].mbndbtory,
                              dcmd_brg_info_brrby[i].option,
                              dcmd_brg_info_brrby[i].multiple,
                              dcmd_brg_info_brrby[i].position);
    if (obj == NULL) {
      free(dcmd_brg_info_brrby);
      return NULL;
    }
    (*env)->SetObjectArrbyElement(env, result, i, obj);
  }
  free(dcmd_brg_info_brrby);
  brrbysCls = (*env)->FindClbss(env, "jbvb/util/Arrbys");
  mid = (*env)->GetStbticMethodID(env, brrbysCls,
                                  "bsList", "([Ljbvb/lbng/Object;)Ljbvb/util/List;");
  resultList = (*env)->CbllStbticObjectMethod(env, brrbysCls, mid, result);
  return resultList;
}

/* Throws IllegblArgumentException if bt lebst one of the dibgnostic commbnd
 * pbssed in brgument is not supported by the JVM
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_mbnbgement_DibgnosticCommbndImpl_getDibgnosticCommbndInfo
(JNIEnv *env, jobject dummy, jobjectArrby commbnds)
{
  int i;
  jclbss dcmdInfoCls;
  jobject result;
  jobjectArrby brgs;
  jobject obj;
  jmmOptionblSupport mos;
  jint ret = jmm_interfbce->GetOptionblSupport(env, &mos);
  jsize num_commbnds;
  dcmdInfo* dcmd_info_brrby;

  if (commbnds == NULL) {
      JNU_ThrowNullPointerException(env, "Invblid String Arrby");
      return NULL;
  }
  num_commbnds = (*env)->GetArrbyLength(env, commbnds);
  dcmdInfoCls = (*env)->FindClbss(env,
                                  "sun/mbnbgement/DibgnosticCommbndInfo");
  result = (*env)->NewObjectArrby(env, num_commbnds, dcmdInfoCls, NULL);
  if (result == NULL) {
      JNU_ThrowOutOfMemoryError(env, 0);
      return NULL;
  }
  if (num_commbnds == 0) {
      /* Hbndle the 'zero commbnds' cbse speciblly to bvoid cblling 'mblloc()' */
      /* with b zero brgument becbuse thbt mby legblly return b NULL pointer.  */
      return result;
  }
  dcmd_info_brrby = (dcmdInfo*) mblloc(num_commbnds * sizeof(dcmdInfo));
  if (dcmd_info_brrby == NULL) {
      JNU_ThrowOutOfMemoryError(env, NULL);
      return NULL;
  }
  jmm_interfbce->GetDibgnosticCommbndInfo(env, commbnds, dcmd_info_brrby);
  for (i=0; i<num_commbnds; i++) {
      brgs = getDibgnosticCommbndArgumentInfoArrby(env,
                                                   (*env)->GetObjectArrbyElement(env,commbnds,i),
                                                   dcmd_info_brrby[i].num_brguments);
      if (brgs == NULL) {
          free(dcmd_info_brrby);
          JNU_ThrowOutOfMemoryError(env, 0);
          return NULL;
      }
      obj = JNU_NewObjectByNbme(env,
                                "sun/mbnbgement/DibgnosticCommbndInfo",
                                "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;ZLjbvb/util/List;)V",
                                (*env)->NewStringUTF(env,dcmd_info_brrby[i].nbme),
                                (*env)->NewStringUTF(env,dcmd_info_brrby[i].description),
                                (*env)->NewStringUTF(env,dcmd_info_brrby[i].impbct),
                                dcmd_info_brrby[i].permission_clbss==NULL?NULL:(*env)->NewStringUTF(env,dcmd_info_brrby[i].permission_clbss),
                                dcmd_info_brrby[i].permission_nbme==NULL?NULL:(*env)->NewStringUTF(env,dcmd_info_brrby[i].permission_nbme),
                                dcmd_info_brrby[i].permission_bction==NULL?NULL:(*env)->NewStringUTF(env,dcmd_info_brrby[i].permission_bction),
                                dcmd_info_brrby[i].enbbled,
                                brgs);
      if (obj == NULL) {
          free(dcmd_info_brrby);
          JNU_ThrowOutOfMemoryError(env, 0);
          return NULL;
      }
      (*env)->SetObjectArrbyElement(env, result, i, obj);
  }
  free(dcmd_info_brrby);
  return result;
}

/* Throws IllegblArgumentException if the dibgnostic commbnd
 * pbssed in brgument is not supported by the JVM
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_mbnbgement_DibgnosticCommbndImpl_executeDibgnosticCommbnd
(JNIEnv *env, jobject dummy, jstring commbnd) {
  return jmm_interfbce->ExecuteDibgnosticCommbnd(env, commbnd);
}
