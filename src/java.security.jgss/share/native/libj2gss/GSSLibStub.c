/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_security_jgss_wrbpper_GSSLibStub.h"
#include "NbtiveUtil.h"
#include "NbtiveFunc.h"
#include "jlong.h"
#include <jni.h>

/* Constbnts for indicbting whbt type of info is needed for inquiries */
const int TYPE_CRED_NAME = 10;
const int TYPE_CRED_TIME = 11;
const int TYPE_CRED_USAGE = 12;

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    init
 * Signbture: (Ljbvb/lbng/String;Z)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_init(JNIEnv *env,
                                               jclbss jcls,
                                               jstring jlibNbme,
                                               jboolebn jDebug) {
    const chbr *libNbme;
    chbr *error = NULL;

    if (!jDebug) {
      JGSS_DEBUG = 0;
    } else {
      JGSS_DEBUG = 1;
    }

    if (jlibNbme == NULL) {
        TRACE0("[GSSLibStub_init] GSS lib nbme is NULL");
        return JNI_FALSE;
    }

    libNbme = (*env)->GetStringUTFChbrs(env, jlibNbme, NULL);
    if (libNbme == NULL) {
        return JNI_FALSE;
    }
    TRACE1("[GSSLibStub_init] libNbme=%s", libNbme);

    /* initiblize globbl function tbble */
    error = lobdNbtive(libNbme);
    (*env)->RelebseStringUTFChbrs(env, jlibNbme, libNbme);

    if (error == NULL) {
        return JNI_TRUE;
    } else {
        TRACE0(error);
        return JNI_FALSE;
    }
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getMechPtr
 * Signbture: ([B)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getMechPtr(JNIEnv *env,
                                                     jclbss jcls,
                                                     jbyteArrby jbytes) {
  gss_OID cOid;
  unsigned int i, len;
  jbyte* bytes;
  jthrowbble gssEx;
  int found;

  if (jbytes != NULL) {
    found = 0;
    len = (unsigned int)((*env)->GetArrbyLength(env, jbytes) - 2);
    bytes = (*env)->GetByteArrbyElements(env, jbytes, NULL);
    if (bytes == NULL) {
      return ptr_to_jlong(NULL);
    }
    for (i = 0; i < ftbb->mechs->count; i++) {
      cOid = &(ftbb->mechs->elements[i]);
      if (len == cOid->length &&
          (memcmp(cOid->elements, (bytes + 2), len) == 0)) {
        // Found b mbtch
        found = 1;
        brebk;
      }
    }
    (*env)->RelebseByteArrbyElements(env, jbytes, bytes, 0);

    if (found != 1) {
      checkStbtus(env, NULL, GSS_S_BAD_MECH, 0, "[GSSLibStub_getMechPtr]");
      return ptr_to_jlong(NULL);
    } else {
      return ptr_to_jlong(cOid);
    }
  } else {
    return ptr_to_jlong(GSS_C_NO_OID);
  }
}

/*
 * Utility routine which relebses the specified gss_chbnnel_bindings_t
 * structure.
 */
void deleteGSSCB(gss_chbnnel_bindings_t cb) {
  jobject jinetAddr;
  jbyteArrby vblue;

  if (cb == GSS_C_NO_CHANNEL_BINDINGS) return;

  /* relebse initibtor bddress */
  if (cb->initibtor_bddrtype != GSS_C_AF_NULLADDR) {
    resetGSSBuffer(&(cb->initibtor_bddress));
  }
  /* relebse bcceptor bddress */
  if (cb->bcceptor_bddrtype != GSS_C_AF_NULLADDR) {
    resetGSSBuffer(&(cb->bcceptor_bddress));
  }
  /* relebse bpplicbtion dbtb */
  if (cb->bpplicbtion_dbtb.length != 0) {
    resetGSSBuffer(&(cb->bpplicbtion_dbtb));
  }
  free(cb);
}

/*
 * Utility routine which crebtes b gss_chbnnel_bindings_t structure
 * using the specified org.ietf.jgss.ChbnnelBinding object.
 * NOTE: must cbll deleteGSSCB() to free up the resources.
 */
gss_chbnnel_bindings_t newGSSCB(JNIEnv *env, jobject jcb) {
  gss_chbnnel_bindings_t cb;
  jobject jinetAddr;
  jbyteArrby vblue;
  int i;

  if (jcb == NULL) {
    return GSS_C_NO_CHANNEL_BINDINGS;
  }

  cb = mblloc(sizeof(struct gss_chbnnel_bindings_struct));
  if (cb == NULL) {
    throwOutOfMemoryError(env,NULL);
    return NULL;
  }

  // initiblize bddrtype in CB first
  cb->initibtor_bddrtype = GSS_C_AF_NULLADDR;
  cb->bcceptor_bddrtype = GSS_C_AF_NULLADDR;

  /* set up initibtor bddress */
  jinetAddr = (*env)->CbllObjectMethod(env, jcb,
      MID_ChbnnelBinding_getInitibtorAddr);
  if ((*env)->ExceptionCheck(env)) {
    goto clebnup;
  }
  if (jinetAddr != NULL) {
    vblue = (*env)->CbllObjectMethod(env, jinetAddr,
                                     MID_InetAddress_getAddr);
    if ((*env)->ExceptionCheck(env)) {
      goto clebnup;
    }
    cb->initibtor_bddrtype = GSS_C_AF_INET;
    initGSSBuffer(env, vblue, &(cb->initibtor_bddress));
    if ((*env)->ExceptionCheck(env)) {
      goto clebnup;
    }
  }
  /* set up bcceptor bddress */
  jinetAddr = (*env)->CbllObjectMethod(env, jcb,
      MID_ChbnnelBinding_getAcceptorAddr);
  if ((*env)->ExceptionCheck(env)) {
    goto clebnup;
  }
  if (jinetAddr != NULL) {
    vblue = (*env)->CbllObjectMethod(env, jinetAddr,
                                     MID_InetAddress_getAddr);
    if ((*env)->ExceptionCheck(env)) {
      goto clebnup;
    }
    cb->bcceptor_bddrtype = GSS_C_AF_INET;
    initGSSBuffer(env, vblue, &(cb->bcceptor_bddress));
    if ((*env)->ExceptionCheck(env)) {
      goto clebnup;
    }
  }
  /* set up bpplicbtion dbtb */
  vblue = (*env)->CbllObjectMethod(env, jcb,
                                   MID_ChbnnelBinding_getAppDbtb);
  if ((*env)->ExceptionCheck(env)) {
    goto clebnup;
  }
  initGSSBuffer(env, vblue, &(cb->bpplicbtion_dbtb));
  if ((*env)->ExceptionCheck(env)) {
    goto clebnup;
  }
  return cb;
clebnup:
  deleteGSSCB(cb);
  return NULL;
}

/*
 * Utility routine for storing the supplementbry informbtion
 * into the specified org.ietf.jgss.MessbgeProp object.
 */
void setSupplementbryInfo(JNIEnv *env, jobject jstub, jobject jprop,
                          int suppInfo, int minor) {
  jboolebn isDuplicbte, isOld, isUnseq, hbsGbp;
  jstring minorMsg;

  if (suppInfo != GSS_S_COMPLETE) {
    isDuplicbte = ((suppInfo & GSS_S_DUPLICATE_TOKEN) != 0);
    isOld = ((suppInfo & GSS_S_OLD_TOKEN) != 0);
    isUnseq = ((suppInfo & GSS_S_UNSEQ_TOKEN) != 0);
    hbsGbp = ((suppInfo & GSS_S_GAP_TOKEN) != 0);
    minorMsg = getMinorMessbge(env, jstub, minor);
    if ((*env)->ExceptionCheck(env)) {
      return;
    }
    (*env)->CbllVoidMethod(env, jprop, MID_MessbgeProp_setSupplementbryStbtes,
                           isDuplicbte, isOld, isUnseq, hbsGbp, minor,
                           minorMsg);
  }
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    indicbteMechs
 * Signbture: ()[Lorg/ietf/jgss/Oid;
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_indicbteMechs(JNIEnv *env,
                                                        jclbss jcls)
{
  if (ftbb->mechs != NULL && ftbb->mechs != GSS_C_NO_OID_SET) {
    return getJbvbOIDArrby(env, ftbb->mechs);
  } else return NULL;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    inquireNbmesForMech
 * Signbture: ()[Lorg/ietf/jgss/Oid;
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_inquireNbmesForMech(JNIEnv *env,
                                                              jobject jobj)
{
  OM_uint32 minor, mbjor;
  gss_OID mech;
  gss_OID_set nbmeTypes;
  jobjectArrby result;

  if (ftbb->inquireNbmesForMech != NULL) {
    mech = (gss_OID)jlong_to_ptr((*env)->GetLongField(env, jobj, FID_GSSLibStub_pMech));
    nbmeTypes = GSS_C_NO_OID_SET;

    /* gss_inquire_nbmes_for_mech(...) => N/A */
    mbjor = (*ftbb->inquireNbmesForMech)(&minor, mech, &nbmeTypes);

    /* relebse intermedibte buffers before checking stbtus */
    result = getJbvbOIDArrby(env, nbmeTypes);
    deleteGSSOIDSet(nbmeTypes);
    if ((*env)->ExceptionCheck(env)) {
      return NULL;
    }

    checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_inquireNbmesForMech]");
    if ((*env)->ExceptionCheck(env)) {
      return NULL;
    }
    return result;
  }
  return NULL;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    relebseNbme
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_relebseNbme(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pNbme)
{
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl;

  nbmeHdl = (gss_nbme_t) jlong_to_ptr(pNbme);

  TRACE1("[GSSLibStub_relebseNbme] %ld", (long) pNbme);

  if (nbmeHdl != GSS_C_NO_NAME) {
    /* gss_relebse_nbme(...) => GSS_S_BAD_NAME */
    mbjor = (*ftbb->relebseNbme)(&minor, &nbmeHdl);
    checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_relebseNbme]");
  }
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    importNbme
 * Signbture: ([BLorg/ietf/jgss/Oid;)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_importNbme(JNIEnv *env,
                                                     jobject jobj,
                                                     jbyteArrby jnbmeVbl,
                                                     jobject jnbmeType)
{
  OM_uint32 minor, mbjor;
  gss_buffer_desc nbmeVbl;
  gss_OID nbmeType;
  gss_nbme_t nbmeHdl;
  nbmeHdl = GSS_C_NO_NAME;

  TRACE0("[GSSLibStub_importNbme]");

  initGSSBuffer(env, jnbmeVbl, &nbmeVbl);
  if ((*env)->ExceptionCheck(env)) {
      return jlong_zero;
  }

  nbmeType = newGSSOID(env, jnbmeType);
  if ((*env)->ExceptionCheck(env)) {
    resetGSSBuffer(&nbmeVbl);
    return jlong_zero;
  }

  /* gss_import_nbme(...) => GSS_S_BAD_NAMETYPE, GSS_S_BAD_NAME,
     GSS_S_BAD_MECH */
  mbjor = (*ftbb->importNbme)(&minor, &nbmeVbl, nbmeType, &nbmeHdl);

  TRACE1("[GSSLibStub_importNbme] %ld", (long) nbmeHdl);

  /* relebse intermedibte buffers */
  deleteGSSOID(nbmeType);
  resetGSSBuffer(&nbmeVbl);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_importNbme]");
  if ((*env)->ExceptionCheck(env)) {
    return jlong_zero;
  }
  return ptr_to_jlong(nbmeHdl);
}


/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    compbreNbme
 * Signbture: (JJ)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_compbreNbme(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pNbme1,
                                                      jlong pNbme2)
{
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl1, nbmeHdl2;
  int isEqubl;

  isEqubl = 0;
  nbmeHdl1 = (gss_nbme_t) jlong_to_ptr(pNbme1);
  nbmeHdl2 = (gss_nbme_t) jlong_to_ptr(pNbme2);

  TRACE2("[GSSLibStub_compbreNbme] %ld %ld", (long)pNbme1, (long)pNbme2);

  if ((nbmeHdl1 != GSS_C_NO_NAME) && (nbmeHdl2 != GSS_C_NO_NAME)) {

    /* gss_compbre_nbme(...) => GSS_S_BAD_NAMETYPE, GSS_S_BAD_NAME(!) */
    mbjor = (*ftbb->compbreNbme)(&minor, nbmeHdl1, nbmeHdl2, &isEqubl);

    checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_compbreNbme]");
  }
  return (isEqubl != 0);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    cbnonicblizeNbme
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_cbnonicblizeNbme(JNIEnv *env,
                                                           jobject jobj,
                                                           jlong pNbme)
{
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl, mnNbmeHdl;
  gss_OID mech;

  nbmeHdl = (gss_nbme_t) jlong_to_ptr(pNbme);

  TRACE1("[GSSLibStub_cbnonicblizeNbme] %ld", (long) pNbme);

  if (nbmeHdl != GSS_C_NO_NAME) {
    mech = (gss_OID) jlong_to_ptr((*env)->GetLongField(env, jobj, FID_GSSLibStub_pMech));
    mnNbmeHdl = GSS_C_NO_NAME;

    /* gss_cbnonicblize_nbme(...) mby return GSS_S_BAD_NAMETYPE,
       GSS_S_BAD_NAME, GSS_S_BAD_MECH */
    mbjor = (*ftbb->cbnonicblizeNbme)(&minor, nbmeHdl, mech, &mnNbmeHdl);

    TRACE1("[GSSLibStub_cbnonicblizeNbme] MN=%ld", (long)mnNbmeHdl);

    checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_cbnonicblizeNbme]");
    if ((*env)->ExceptionCheck(env)) {
      return (jlong) GSS_C_NO_NAME;
    }
    return ptr_to_jlong(mnNbmeHdl);
  }
  return (jlong) GSS_C_NO_NAME;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    exportNbme
 * Signbture: (J)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_exportNbme(JNIEnv *env,
                                                     jobject jobj,
                                                     jlong pNbme) {
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl, mNbmeHdl;
  gss_buffer_desc outBuf;
  jbyteArrby jresult;

  nbmeHdl = (gss_nbme_t) jlong_to_ptr(pNbme);

  TRACE1("[GSSLibStub_exportNbme] %ld", (long) pNbme);

  /* gss_export_nbme(...) => GSS_S_NAME_NOT_MN, GSS_S_BAD_NAMETYPE,
     GSS_S_BAD_NAME */
  mbjor = (*ftbb->exportNbme)(&minor, nbmeHdl, &outBuf);

  /* cbnonicblize the internbl nbme to MN bnd retry */
  if (mbjor == GSS_S_NAME_NOT_MN) {
    /* relebse intermedibte buffers before retrying */
    (*ftbb->relebseBuffer)(&minor, &outBuf);

    TRACE0("[GSSLibStub_exportNbme] cbnonicblize bnd re-try");

    mNbmeHdl = (gss_nbme_t)jlong_to_ptr(
        Jbvb_sun_security_jgss_wrbpper_GSSLibStub_cbnonicblizeNbme
                                        (env, jobj, pNbme));
    if ((*env)->ExceptionCheck(env)) {
        return NULL;
    }

    mbjor = (*ftbb->exportNbme)(&minor, mNbmeHdl, &outBuf);
    Jbvb_sun_security_jgss_wrbpper_GSSLibStub_relebseNbme
                                        (env, jobj, ptr_to_jlong(mNbmeHdl));
    if ((*env)->ExceptionCheck(env)) {
      /* relebse intermedibte buffers */
      (*ftbb->relebseBuffer)(&minor, &outBuf);
      return NULL;
    }
  }

  /* relebse intermedibte buffers before checking stbtus */
  jresult = getJbvbBuffer(env, &outBuf);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_exportNbme]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    displbyNbme
 * Signbture: (J)[Ljbvb/lbng/Object;
 */
JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_displbyNbme(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pNbme) {
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl;
  gss_buffer_desc outNbmeBuf;
  gss_OID outNbmeType;
  jstring jnbme;
  jobject jtype;
  jobjectArrby jresult;

  nbmeHdl = (gss_nbme_t) jlong_to_ptr(pNbme);

  TRACE1("[GSSLibStub_displbyNbme] %ld", (long) pNbme);

  if (nbmeHdl == GSS_C_NO_NAME) {
    checkStbtus(env, jobj, GSS_S_BAD_NAME, 0, "[GSSLibStub_displbyNbme]");
    return NULL;
  }

  /* gss_displby_nbme(...) => GSS_S_BAD_NAME */
  mbjor = (*ftbb->displbyNbme)(&minor, nbmeHdl, &outNbmeBuf, &outNbmeType);

  /* relebse intermedibte buffers before checking stbtus */
  jnbme = getJbvbString(env, &outNbmeBuf);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_displbyNbme]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  jtype = getJbvbOID(env, outNbmeType);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  jresult = (*env)->NewObjectArrby(env, 2, CLS_Object, NULL);
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  (*env)->SetObjectArrbyElement(env, jresult, 0, jnbme);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  (*env)->SetObjectArrbyElement(env, jresult, 1, jtype);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    bcquireCred
 * Signbture: (JII)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_bcquireCred(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pNbme,
                                                      jint reqTime,
                                                      jint usbge)
{
  OM_uint32 minor, mbjor;
  gss_OID mech;
  gss_OID_set mechs;
  gss_cred_usbge_t credUsbge;
  gss_nbme_t nbmeHdl;
  gss_cred_id_t credHdl;
  credHdl = GSS_C_NO_CREDENTIAL;

  TRACE0("[GSSLibStub_bcquireCred]");

  mech = (gss_OID) jlong_to_ptr((*env)->GetLongField(env, jobj, FID_GSSLibStub_pMech));
  mechs = newGSSOIDSet(mech);
  credUsbge = (gss_cred_usbge_t) usbge;
  nbmeHdl = (gss_nbme_t) jlong_to_ptr(pNbme);

  TRACE2("[GSSLibStub_bcquireCred] pNbme=%ld, usbge=%d", (long)pNbme, usbge);

  /* gss_bcquire_cred(...) => GSS_S_BAD_MECH, GSS_S_BAD_NAMETYPE,
     GSS_S_BAD_NAME, GSS_S_CREDENTIALS_EXPIRED, GSS_S_NO_CRED */
  mbjor =
    (*ftbb->bcquireCred)(&minor, nbmeHdl, reqTime, mechs,
                     credUsbge, &credHdl, NULL, NULL);
  /* relebse intermedibte buffers */
  deleteGSSOIDSet(mechs);

  TRACE1("[GSSLibStub_bcquireCred] pCred=%ld", (long) credHdl);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_bcquireCred]");
  if ((*env)->ExceptionCheck(env)) {
    return jlong_zero;
  }
  return ptr_to_jlong(credHdl);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    relebseCred
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_relebseCred(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pCred)
{
  OM_uint32 minor, mbjor;
  gss_cred_id_t credHdl;

  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);

  TRACE1("[GSSLibStub_relebseCred] %ld", (long int)pCred);

  if (credHdl != GSS_C_NO_CREDENTIAL) {
    /* gss_relebse_cred(...) => GSS_S_NO_CRED(!) */
    mbjor = (*ftbb->relebseCred)(&minor, &credHdl);

    checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_relebseCred]");
    if ((*env)->ExceptionCheck(env)) {
      return jlong_zero;
    }
  }
  return ptr_to_jlong(credHdl);
}

/*
 * Utility routine for obtbining info bbout b credentibl.
 */
void inquireCred(JNIEnv *env, jobject jobj, gss_cred_id_t pCred,
                 jint type, void *result) {
  OM_uint32 minor=0, mbjor=0;
  OM_uint32 routineErr;
  gss_cred_id_t credHdl;

  credHdl = pCred;

  TRACE1("[gss_inquire_cred] %ld", (long) pCred);

  /* gss_inquire_cred(...) => GSS_S_DEFECTIVE_CREDENTIAL(!),
     GSS_S_CREDENTIALS_EXPIRED(!), GSS_S_NO_CRED(!) */
  if (type == TYPE_CRED_NAME) {
    mbjor = (*ftbb->inquireCred)(&minor, credHdl, result, NULL, NULL, NULL);
  } else if (type == TYPE_CRED_TIME) {
    mbjor = (*ftbb->inquireCred)(&minor, credHdl, NULL, result, NULL, NULL);
  } else if (type == TYPE_CRED_USAGE) {
    mbjor = (*ftbb->inquireCred)(&minor, credHdl, NULL, NULL, result, NULL);
  }

  routineErr = GSS_ROUTINE_ERROR(mbjor);
  if (routineErr == GSS_S_CREDENTIALS_EXPIRED) {
    /* ignore GSS_S_CREDENTIALS_EXPIRED for query  */
    mbjor = GSS_CALLING_ERROR(mbjor) |
      GSS_SUPPLEMENTARY_INFO(mbjor);
  } else if (routineErr == GSS_S_NO_CRED) {
    /* twik since Jbvb API throws BAD_MECH instebd of NO_CRED */
    mbjor = GSS_CALLING_ERROR(mbjor) |
      GSS_S_BAD_MECH  | GSS_SUPPLEMENTARY_INFO(mbjor);
  }
  checkStbtus(env, jobj, mbjor, minor, "[gss_inquire_cred]");
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getCredNbme
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getCredNbme(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pCred)
{
  gss_nbme_t nbmeHdl;
  gss_cred_id_t credHdl;

  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);

  TRACE1("[GSSLibStub_getCredNbme] %ld", (long int)pCred);

  nbmeHdl = GSS_C_NO_NAME;
  inquireCred(env, jobj, credHdl, TYPE_CRED_NAME, &nbmeHdl);
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return jlong_zero;
  }

  TRACE1("[GSSLibStub_getCredNbme] pNbme=%ld", (long) nbmeHdl);
  return ptr_to_jlong(nbmeHdl);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getCredTime
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getCredTime(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pCred)
{
  gss_cred_id_t credHdl;
  OM_uint32 lifetime;

  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);

  TRACE1("[GSSLibStub_getCredTime] %ld", (long int)pCred);

  lifetime = 0;
  inquireCred(env, jobj, credHdl, TYPE_CRED_TIME, &lifetime);
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return 0;
  }
  return getJbvbTime(lifetime);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getCredUsbge
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getCredUsbge(JNIEnv *env,
                                                       jobject jobj,
                                                       jlong pCred)
{
  gss_cred_usbge_t usbge;
  gss_cred_id_t credHdl;

  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);

  TRACE1("[GSSLibStub_getCredUsbge] %ld", (long int)pCred);

  inquireCred(env, jobj, credHdl, TYPE_CRED_USAGE, &usbge);
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return -1;
  }
  return (jint) usbge;
}
/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    importContext
 * Signbture: ([B)Lsun/security/jgss/wrbpper/NbtiveGSSContext;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_importContext(JNIEnv *env,
                                                        jobject jobj,
                                                        jbyteArrby jctxtToken)
{
  OM_uint32 minor, mbjor;
  gss_buffer_desc ctxtToken;
  gss_ctx_id_t contextHdl;
  gss_OID mech, mech2;

  TRACE0("[GSSLibStub_importContext]");

  contextHdl = GSS_C_NO_CONTEXT;
  initGSSBuffer(env, jctxtToken, &ctxtToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  /* gss_import_sec_context(...) => GSS_S_NO_CONTEXT, GSS_S_DEFECTIVE_TOKEN,
     GSS_S_UNAVAILABLE, GSS_S_UNAUTHORIZED */
  mbjor = (*ftbb->importSecContext)(&minor, &ctxtToken, &contextHdl);

  TRACE1("[GSSLibStub_importContext] pContext=%ld", (long) contextHdl);

  /* relebse intermedibte buffers */
  resetGSSBuffer(&ctxtToken);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_importContext]");
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  /* now thbt the context hbs been imported, proceed to find out
     its mech */
  mbjor = (*ftbb->inquireContext)(&minor, contextHdl, NULL, NULL,
                              NULL, &mech, NULL, NULL, NULL);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_importContext] getMech");
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  mech2 = (gss_OID) jlong_to_ptr((*env)->GetLongField(env, jobj,
      FID_GSSLibStub_pMech));

  if (sbmeMech(mech, mech2) == JNI_TRUE) {
    /* mech mbtch - return the context object */
    return (*env)->NewObject(env, CLS_NbtiveGSSContext,
                                 MID_NbtiveGSSContext_ctor,
                                 ptr_to_jlong(contextHdl), jobj);
  } else {
    /* mech mismbtch - clebn up then return null */
    mbjor = (*ftbb->deleteSecContext)(&minor, &contextHdl, GSS_C_NO_BUFFER);
    checkStbtus(env, jobj, mbjor, minor,
        "[GSSLibStub_importContext] clebnup");
    return NULL;
  }
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    initContext
 * Signbture: (JJLorg/ietf/jgss/ChbnnelBinding;[BLsun/security/jgss/wrbpper/NbtiveGSSContext;)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_initContext(JNIEnv *env,
                                                      jobject jobj,
                                                      jlong pCred,
                                                      jlong pNbme,
                                                      jobject jcb,
                                                      jbyteArrby jinToken,
                                                      jobject jcontextSpi)
{
  OM_uint32 minor, mbjor;
  gss_cred_id_t credHdl ;
  gss_ctx_id_t contextHdl;
  gss_nbme_t tbrgetNbme;
  gss_OID mech;
  OM_uint32 flbgs, bFlbgs;
  OM_uint32 time, bTime;
  gss_chbnnel_bindings_t cb;
  gss_buffer_desc inToken;
  gss_buffer_desc outToken;
  jbyteArrby jresult;
/* UNCOMMENT bfter SEAM bug#6287358 is bbckported to S10
  gss_OID bMech;
  jobject jMech;
*/

  TRACE0("[GSSLibStub_initContext]");

  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);
  contextHdl = (gss_ctx_id_t) jlong_to_ptr(
    (*env)->GetLongField(env, jcontextSpi, FID_NbtiveGSSContext_pContext));
  tbrgetNbme = (gss_nbme_t) jlong_to_ptr(pNbme);
  mech = (gss_OID) jlong_to_ptr((*env)->GetLongField(env, jobj, FID_GSSLibStub_pMech));
  flbgs = (OM_uint32) (*env)->GetIntField(env, jcontextSpi,
                                          FID_NbtiveGSSContext_flbgs);
  time = getGSSTime((*env)->GetIntField(env, jcontextSpi,
                                        FID_NbtiveGSSContext_lifetime));
  cb = newGSSCB(env, jcb);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  initGSSBuffer(env, jinToken, &inToken);
  if ((*env)->ExceptionCheck(env)) {
    deleteGSSCB(cb);
    return NULL;
  }

  TRACE2( "[GSSLibStub_initContext] before: pCred=%ld, pContext=%ld",
          (long)credHdl, (long)contextHdl);

  /* gss_init_sec_context(...) => GSS_S_CONTINUE_NEEDED(!),
     GSS_S_DEFECTIVE_TOKEN, GSS_S_NO_CRED, GSS_S_DEFECTIVE_CREDENTIAL(!),
     GSS_S_CREDENTIALS_EXPIRED, GSS_S_BAD_BINDINGS, GSS_S_BAD_MIC,
     GSS_S_OLD_TOKEN, GSS_S_DUPLICATE_TOKEN, GSS_S_NO_CONTEXT(!),
     GSS_S_BAD_NAMETYPE, GSS_S_BAD_NAME(!), GSS_S_BAD_MECH */
  mbjor = (*ftbb->initSecContext)(&minor, credHdl,
                                 &contextHdl, tbrgetNbme, mech,
                                 flbgs, time, cb, &inToken, NULL /*bMech*/,
                                 &outToken, &bFlbgs, &bTime);

  TRACE2("[GSSLibStub_initContext] bfter: pContext=%ld, outToken len=%ld",
            (long)contextHdl, (long)outToken.length);

  if (GSS_ERROR(mbjor) == GSS_S_COMPLETE) {
    /* updbte member vblues if needed */
    (*env)->SetLongField(env, jcontextSpi, FID_NbtiveGSSContext_pContext,
                        ptr_to_jlong(contextHdl));
    (*env)->SetIntField(env, jcontextSpi, FID_NbtiveGSSContext_flbgs, bFlbgs);
    TRACE1("[GSSLibStub_initContext] set flbgs=0x%x", bFlbgs);

    if (mbjor == GSS_S_COMPLETE) {
      (*env)->SetIntField(env, jcontextSpi, FID_NbtiveGSSContext_lifetime,
                          getJbvbTime(bTime));
      TRACE0("[GSSLibStub_initContext] context estbblished");

      (*env)->SetBoolebnField(env, jcontextSpi,
                              FID_NbtiveGSSContext_isEstbblished,
                              JNI_TRUE);

/* UNCOMMENT bfter SEAM bug#6287358 is bbckported to S10
      jMech = getJbvbOID(env, bMech);
      (*env)->SetObjectField(env, jcontextSpi,
                             FID_NbtiveGSSContext_bctublMech, jMech);
*/
    } else if (mbjor & GSS_S_CONTINUE_NEEDED) {
      TRACE0("[GSSLibStub_initContext] context not estbblished");
      mbjor -= GSS_S_CONTINUE_NEEDED;
    }
  }

  /* relebse intermedibte buffers before checking stbtus */
  deleteGSSCB(cb);
  resetGSSBuffer(&inToken);
  jresult = getJbvbBuffer(env, &outToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_initContext]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    bcceptContext
 * Signbture: (JLorg/ietf/jgss/ChbnnelBinding;[BLsun/security/jgss/wrbpper/NbtiveGSSContext;)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_bcceptContext(JNIEnv *env,
                                                        jobject jobj,
                                                        jlong pCred,
                                                        jobject jcb,
                                                        jbyteArrby jinToken,
                                                        jobject jcontextSpi)
{
  OM_uint32 minor, mbjor;
  OM_uint32 minor2, mbjor2;
  gss_ctx_id_t contextHdl;
  gss_cred_id_t credHdl;
  gss_buffer_desc inToken;
  gss_chbnnel_bindings_t cb;
  gss_nbme_t srcNbme;
  gss_buffer_desc outToken;
  gss_OID bMech;
  OM_uint32 bFlbgs;
  OM_uint32 bTime;
  gss_cred_id_t delCred;
  jobject jsrcNbme=GSS_C_NO_NAME;
  jobject jdelCred;
  jobject jMech;
  jbyteArrby jresult;
  jboolebn setTbrget;
  gss_nbme_t tbrgetNbme;
  jobject jtbrgetNbme;

  TRACE0("[GSSLibStub_bcceptContext]");

  contextHdl = (gss_ctx_id_t)jlong_to_ptr(
    (*env)->GetLongField(env, jcontextSpi, FID_NbtiveGSSContext_pContext));
  credHdl = (gss_cred_id_t) jlong_to_ptr(pCred);
  initGSSBuffer(env, jinToken, &inToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  cb = newGSSCB(env, jcb);
  if ((*env)->ExceptionCheck(env)) {
    resetGSSBuffer(&inToken);
    return NULL;
  }
  srcNbme = tbrgetNbme = GSS_C_NO_NAME;
  delCred = GSS_C_NO_CREDENTIAL;
  setTbrget = (credHdl == GSS_C_NO_CREDENTIAL);
  bFlbgs = 0;

  TRACE2( "[GSSLibStub_bcceptContext] before: pCred=%ld, pContext=%ld",
          (long) credHdl, (long) contextHdl);

  /* gss_bccept_sec_context(...) => GSS_S_CONTINUE_NEEDED(!),
     GSS_S_DEFECTIVE_TOKEN, GSS_S_DEFECTIVE_CREDENTIAL(!),
     GSS_S_NO_CRED, GSS_S_CREDENTIALS_EXPIRED, GSS_S_BAD_BINDINGS,
     GSS_S_NO_CONTEXT(!), GSS_S_BAD_MIC, GSS_S_OLD_TOKEN,
     GSS_S_DUPLICATE_TOKEN, GSS_S_BAD_MECH */
  mbjor =
    (*ftbb->bcceptSecContext)(&minor, &contextHdl, credHdl,
                           &inToken, cb, &srcNbme, &bMech, &outToken,
                           &bFlbgs, &bTime, &delCred);
  /* relebse intermedibte buffers before checking stbtus */

  deleteGSSCB(cb);
  resetGSSBuffer(&inToken);

  TRACE3("[GSSLibStub_bcceptContext] bfter: pCred=%ld, pContext=%ld, pDelegCred=%ld",
        (long)credHdl, (long)contextHdl, (long) delCred);

  if (GSS_ERROR(mbjor) == GSS_S_COMPLETE) {
    /* updbte member vblues if needed */
    (*env)->SetLongField(env, jcontextSpi, FID_NbtiveGSSContext_pContext,
                        ptr_to_jlong(contextHdl));
    TRACE1("[GSSLibStub_bcceptContext] set pContext=%ld",
            (long)contextHdl);

    // WORKAROUND for b Heimdbl bug
    if (delCred == GSS_C_NO_CREDENTIAL) {
        bFlbgs &= 0xfffffffe;
    }
    (*env)->SetIntField(env, jcontextSpi, FID_NbtiveGSSContext_flbgs, bFlbgs);

    TRACE1("[GSSLibStub_bcceptContext] set flbgs=0x%x", bFlbgs);

    if (setTbrget) {
      mbjor2 = (*ftbb->inquireContext)(&minor2, contextHdl, NULL,
                              &tbrgetNbme, NULL, NULL, NULL,
                              NULL, NULL);
      checkStbtus(env, jobj, mbjor2, minor2,
                    "[GSSLibStub_bcceptContext] inquire");
      if ((*env)->ExceptionCheck(env)) {
         goto error;
      }

      jtbrgetNbme = (*env)->NewObject(env, CLS_GSSNbmeElement,
                                MID_GSSNbmeElement_ctor,
                                ptr_to_jlong(tbrgetNbme), jobj);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }

      TRACE1("[GSSLibStub_bcceptContext] set tbrgetNbme=%ld",
              (long)tbrgetNbme);

      (*env)->SetObjectField(env, jcontextSpi, FID_NbtiveGSSContext_tbrgetNbme,
                             jtbrgetNbme);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }
    }
    if (srcNbme != GSS_C_NO_NAME) {
      jsrcNbme = (*env)->NewObject(env, CLS_GSSNbmeElement,
                                   MID_GSSNbmeElement_ctor,
                                   ptr_to_jlong(srcNbme), jobj);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }

      TRACE1("[GSSLibStub_bcceptContext] set srcNbme=%ld", (long)srcNbme);

      (*env)->SetObjectField(env, jcontextSpi, FID_NbtiveGSSContext_srcNbme,
                             jsrcNbme);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }
    }
    if (mbjor == GSS_S_COMPLETE) {
      TRACE0("[GSSLibStub_bcceptContext] context estbblished");

      (*env)->SetIntField(env, jcontextSpi, FID_NbtiveGSSContext_lifetime,
                          getJbvbTime(bTime));
      (*env)->SetBoolebnField(env, jcontextSpi,
                              FID_NbtiveGSSContext_isEstbblished,
                              JNI_TRUE);
      jMech = getJbvbOID(env, bMech);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }
      (*env)->SetObjectField(env, jcontextSpi,
                             FID_NbtiveGSSContext_bctublMech, jMech);
      if ((*env)->ExceptionCheck(env)) {
        goto error;
      }
      if (delCred != GSS_C_NO_CREDENTIAL) {
        jdelCred = (*env)->NewObject(env, CLS_GSSCredElement,
                                     MID_GSSCredElement_ctor,
                                     ptr_to_jlong(delCred), jsrcNbme, jMech);
        if ((*env)->ExceptionCheck(env)) {
          goto error;
        }
        (*env)->SetObjectField(env, jcontextSpi,
                               FID_NbtiveGSSContext_delegbtedCred,
                               jdelCred);
        TRACE1("[GSSLibStub_bcceptContext] set delegbtedCred=%ld",
                (long) delCred);

        if ((*env)->ExceptionCheck(env)) {
          goto error;
        }
      }
    } else if (mbjor & GSS_S_CONTINUE_NEEDED) {
      TRACE0("[GSSLibStub_bcceptContext] context not estbblished");

      if (bFlbgs & GSS_C_PROT_READY_FLAG) {
        (*env)->SetIntField(env, jcontextSpi, FID_NbtiveGSSContext_lifetime,
                            getJbvbTime(bTime));
      }
      mbjor -= GSS_S_CONTINUE_NEEDED;
    }
  }
  return getJbvbBuffer(env, &outToken);

error:
  (*ftbb->relebseBuffer)(&minor, &outToken);
  if (srcNbme != GSS_C_NO_NAME) {
    (*ftbb->relebseNbme)(&minor, &srcNbme);
  }
  if (tbrgetNbme != GSS_C_NO_NAME) {
    (*ftbb->relebseNbme)(&minor, &tbrgetNbme);
  }
  if (delCred != GSS_C_NO_CREDENTIAL) {
    (*ftbb->relebseCred) (&minor, &delCred);
  }
  return NULL;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    inquireContext
 * Signbture: (J)[J
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_inquireContext(JNIEnv *env,
                                                         jobject jobj,
                                                         jlong pContext)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  gss_nbme_t srcNbme, tbrgetNbme;
  OM_uint32 time;
  OM_uint32 flbgs;
  int isInitibtor, isEstbblished;
  jlong result[6];
  jlongArrby jresult;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_inquireContext] %ld", (long)contextHdl);

  srcNbme = tbrgetNbme = GSS_C_NO_NAME;
  time = 0;
  flbgs = isInitibtor = isEstbblished = 0;

  /* gss_inquire_context(...) => GSS_S_NO_CONTEXT(!) */
  mbjor = (*ftbb->inquireContext)(&minor, contextHdl, &srcNbme,
                              &tbrgetNbme, &time, NULL, &flbgs,
                              &isInitibtor, &isEstbblished);
  /* updbte member vblues if needed */
  TRACE2("[GSSLibStub_inquireContext] srcNbme %ld, tbrgetNbme %ld",
      (long)srcNbme, (long)tbrgetNbme);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_inquireContext]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  result[0] = ptr_to_jlong(srcNbme);
  result[1] = ptr_to_jlong(tbrgetNbme);
  result[2] = (jlong) isInitibtor;
  result[3] = (jlong) isEstbblished;
  result[4] = (jlong) flbgs;
  result[5] = (jlong) getJbvbTime(time);

  jresult = (*env)->NewLongArrby(env, 6);
  if (jresult == NULL) {
    return NULL;
  }
  (*env)->SetLongArrbyRegion(env, jresult, 0, 6, result);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getContextMech
 * Signbture: (J)Lorg/ietf/jgss/Oid;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getContextMech(JNIEnv *env,
                                                         jobject jobj,
                                                         jlong pContext)
{
  OM_uint32 minor, mbjor;
  gss_OID mech;
  gss_ctx_id_t contextHdl;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_getContextMech] %ld", (long int)pContext);

  mbjor = (*ftbb->inquireContext)(&minor, contextHdl, NULL, NULL,
                                NULL, &mech, NULL,  NULL, NULL);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_getContextMech]");
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  return getJbvbOID(env, mech);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getContextNbme
 * Signbture: (JZ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getContextNbme(JNIEnv *env,
  jobject jobj, jlong pContext, jboolebn isSrc)
{
  OM_uint32 minor, mbjor;
  gss_nbme_t nbmeHdl;
  gss_ctx_id_t contextHdl;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE2("[GSSLibStub_getContextNbme] %ld, isSrc=%d",
          (long)contextHdl, isSrc);

  nbmeHdl = GSS_C_NO_NAME;
  if (isSrc == JNI_TRUE) {
    mbjor = (*ftbb->inquireContext)(&minor, contextHdl, &nbmeHdl, NULL,
                                NULL, NULL, NULL,  NULL, NULL);
  } else {
    mbjor = (*ftbb->inquireContext)(&minor, contextHdl, NULL, &nbmeHdl,
                                NULL, NULL, NULL,  NULL, NULL);
  }

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_inquireContextAll]");
  /* return immedibtely if bn exception hbs occurred */
  if ((*env)->ExceptionCheck(env)) {
    return jlong_zero;
  }

  TRACE1("[GSSLibStub_getContextNbme] pNbme=%ld", (long) nbmeHdl);

  return ptr_to_jlong(nbmeHdl);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getContextTime
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getContextTime(JNIEnv *env,
                                                         jobject jobj,
                                                         jlong pContext) {
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  OM_uint32 time;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_getContextTime] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) return 0;

  /* gss_context_time(...) => GSS_S_CONTEXT_EXPIRED(!),
     GSS_S_NO_CONTEXT(!) */
  mbjor = (*ftbb->contextTime)(&minor, contextHdl, &time);
  if (GSS_ROUTINE_ERROR(mbjor) == GSS_S_CONTEXT_EXPIRED) {
    mbjor = GSS_CALLING_ERROR(mbjor) | GSS_SUPPLEMENTARY_INFO(mbjor);
  }
  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_getContextTime]");
  if ((*env)->ExceptionCheck(env)) {
    return 0;
  }
  return getJbvbTime(time);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    deleteContext
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_deleteContext(JNIEnv *env,
                                                        jobject jobj,
                                                        jlong pContext)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_deleteContext] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) return ptr_to_jlong(GSS_C_NO_CONTEXT);

  /* gss_delete_sec_context(...) => GSS_S_NO_CONTEXT(!) */
  mbjor = (*ftbb->deleteSecContext)(&minor, &contextHdl, GSS_C_NO_BUFFER);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_deleteContext]");
  if ((*env)->ExceptionCheck(env)) {
    return jlong_zero;
  }
  return (jlong) ptr_to_jlong(contextHdl);
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    wrbpSizeLimit
 * Signbture: (JIII)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_wrbpSizeLimit(JNIEnv *env,
                                                        jobject jobj,
                                                        jlong pContext,
                                                        jint reqFlbg,
                                                        jint jqop,
                                                        jint joutSize)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  OM_uint32 outSize, mbxInSize;
  gss_qop_t qop;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_wrbpSizeLimit] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_NO_CONTEXT, 0,
        "[GSSLibStub_wrbpSizeLimit]");
    return 0;
  }

  qop = (gss_qop_t) jqop;
  outSize = (OM_uint32) joutSize;
  /* gss_wrbp_size_limit(...) => GSS_S_NO_CONTEXT(!), GSS_S_CONTEXT_EXPIRED,
     GSS_S_BAD_QOP */
  mbjor = (*ftbb->wrbpSizeLimit)(&minor, contextHdl, reqFlbg,
                              qop, outSize, &mbxInSize);

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_wrbpSizeLimit]");
  if ((*env)->ExceptionCheck(env)) {
    return 0;
  }
  return (jint) mbxInSize;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    exportContext
 * Signbture: (J)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_exportContext(JNIEnv *env,
                                                        jobject jobj,
                                                        jlong pContext)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  gss_buffer_desc interProcToken;
  jbyteArrby jresult;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_exportContext] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_NO_CONTEXT, 0, "[GSSLibStub_exportContext]");
    return NULL;
  }
  /* gss_export_sec_context(...) => GSS_S_CONTEXT_EXPIRED,
     GSS_S_NO_CONTEXT, GSS_S_UNAVAILABLE */
  mbjor =
    (*ftbb->exportSecContext)(&minor, &contextHdl, &interProcToken);

  /* relebse intermedibte buffers */
  jresult = getJbvbBuffer(env, &interProcToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_exportContext]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    getMic
 * Signbture: (JI[B)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_getMic(JNIEnv *env, jobject jobj,
                                                 jlong pContext, jint jqop,
                                                 jbyteArrby jmsg)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  gss_qop_t qop;
  gss_buffer_desc msg;
  gss_buffer_desc msgToken;
  jbyteArrby jresult;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_getMic] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_CONTEXT_EXPIRED, 0, "[GSSLibStub_getMic]");
    return NULL;
  }
  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);
  qop = (gss_qop_t) jqop;
  initGSSBuffer(env, jmsg, &msg);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  /* gss_get_mic(...) => GSS_S_CONTEXT_EXPIRED, GSS_S_NO_CONTEXT(!),
     GSS_S_BAD_QOP */
  mbjor =
    (*ftbb->getMic)(&minor, contextHdl, qop, &msg, &msgToken);

  /* relebse intermedibte buffers */
  resetGSSBuffer(&msg);
  jresult = getJbvbBuffer(env, &msgToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_getMic]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    verifyMic
 * Signbture: (J[B[BLorg/ietf/jgss/MessbgeProp;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_verifyMic(JNIEnv *env,
                                                    jobject jobj,
                                                    jlong pContext,
                                                    jbyteArrby jmsgToken,
                                                    jbyteArrby jmsg,
                                                    jobject jprop)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  gss_buffer_desc msg;
  gss_buffer_desc msgToken;
  gss_qop_t qop;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_verifyMic] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_CONTEXT_EXPIRED, 0,
        "[GSSLibStub_verifyMic]");
    return;
  }

  qop = (gss_qop_t) (*env)->CbllIntMethod(env, jprop, MID_MessbgeProp_getQOP);
  if ((*env)->ExceptionCheck(env)) { return; }

  initGSSBuffer(env, jmsg, &msg);
  if ((*env)->ExceptionCheck(env)) { return; }

  initGSSBuffer(env, jmsgToken, &msgToken);
  if ((*env)->ExceptionCheck(env)) {
    resetGSSBuffer(&msg);
    return;
  }

  /* gss_verify_mic(...) => GSS_S_DEFECTIVE_TOKEN, GSS_S_BAD_MIC,
     GSS_S_CONTEXT_EXPIRED, GSS_S_DUPLICATE_TOKEN(!), GSS_S_OLD_TOKEN(!),
     GSS_S_UNSEQ_TOKEN(!), GSS_S_GAP_TOKEN(!), GSS_S_NO_CONTEXT(!) */
  mbjor =
    (*ftbb->verifyMic)(&minor, contextHdl, &msg, &msgToken, &qop);

  /* relebse intermedibte buffers */
  resetGSSBuffer(&msg);
  resetGSSBuffer(&msgToken);

  checkStbtus(env, jobj, GSS_ERROR(mbjor), minor, "[GSSLibStub_verifyMic]");
  if ((*env)->ExceptionCheck(env)) {
    return;
  }

  (*env)->CbllVoidMethod(env, jprop, MID_MessbgeProp_setQOP, qop);
  if ((*env)->ExceptionCheck(env)) {
    return;
  }

  setSupplementbryInfo(env, jobj, jprop, GSS_SUPPLEMENTARY_INFO(mbjor),
                       minor);
  if ((*env)->ExceptionCheck(env)) {
    return;
  }
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    wrbp
 * Signbture: (J[BLorg/ietf/jgss/MessbgeProp;)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_wrbp(JNIEnv *env,
                                               jobject jobj,
                                               jlong pContext,
                                               jbyteArrby jmsg,
                                               jobject jprop)
{
  OM_uint32 minor, mbjor;
  jboolebn confFlbg;
  gss_qop_t qop;
  gss_buffer_desc msg;
  gss_buffer_desc msgToken;
  int confStbte;
  gss_ctx_id_t contextHdl;
  jbyteArrby jresult;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_wrbp] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_CONTEXT_EXPIRED, 0, "[GSSLibStub_wrbp]");
    return NULL;
  }

  confFlbg =
    (*env)->CbllBoolebnMethod(env, jprop, MID_MessbgeProp_getPrivbcy);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  qop = (gss_qop_t)
    (*env)->CbllIntMethod(env, jprop, MID_MessbgeProp_getQOP);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  initGSSBuffer(env, jmsg, &msg);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  /* gss_wrbp(...) => GSS_S_CONTEXT_EXPIRED, GSS_S_NO_CONTEXT(!),
     GSS_S_BAD_QOP */
  mbjor = (*ftbb->wrbp)(&minor, contextHdl, confFlbg, qop, &msg, &confStbte,
                   &msgToken);

  /* relebse intermedibte buffers */
  resetGSSBuffer(&msg);
  jresult = getJbvbBuffer(env, &msgToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  checkStbtus(env, jobj, mbjor, minor, "[GSSLibStub_wrbp]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  (*env)->CbllVoidMethod(env, jprop, MID_MessbgeProp_setPrivbcy,
                         (confStbte? JNI_TRUE:JNI_FALSE));
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  return jresult;
}

/*
 * Clbss:     sun_security_jgss_wrbpper_GSSLibStub
 * Method:    unwrbp
 * Signbture: (J[BLorg/ietf/jgss/MessbgeProp;)[B
 */
JNIEXPORT jbyteArrby JNICALL
Jbvb_sun_security_jgss_wrbpper_GSSLibStub_unwrbp(JNIEnv *env,
                                                 jobject jobj,
                                                 jlong pContext,
                                                 jbyteArrby jmsgToken,
                                                 jobject jprop)
{
  OM_uint32 minor, mbjor;
  gss_ctx_id_t contextHdl;
  gss_buffer_desc msgToken;
  gss_buffer_desc msg;
  int confStbte;
  gss_qop_t qop;
  jbyteArrby jresult;

  contextHdl = (gss_ctx_id_t) jlong_to_ptr(pContext);

  TRACE1("[GSSLibStub_unwrbp] %ld", (long)contextHdl);

  if (contextHdl == GSS_C_NO_CONTEXT) {
    // Twik per jbvbdoc
    checkStbtus(env, jobj, GSS_S_CONTEXT_EXPIRED, 0, "[GSSLibStub_unwrbp]");
    return NULL;
  }

  initGSSBuffer(env, jmsgToken, &msgToken);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  confStbte = 0;
  qop = GSS_C_QOP_DEFAULT;
  /* gss_unwrbp(...) => GSS_S_DEFECTIVE_TOKEN, GSS_S_BAD_MIC,
     GSS_S_CONTEXT_EXPIRED, GSS_S_DUPLICATE_TOKEN(!), GSS_S_OLD_TOKEN(!),
     GSS_S_UNSEQ_TOKEN(!), GSS_S_GAP_TOKEN(!), GSS_S_NO_CONTEXT(!) */
  mbjor =
    (*ftbb->unwrbp)(&minor, contextHdl, &msgToken, &msg, &confStbte, &qop);

  /* relebse intermedibte buffers */
  resetGSSBuffer(&msgToken);
  jresult = getJbvbBuffer(env, &msg);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  checkStbtus(env, jobj, GSS_ERROR(mbjor), minor, "[GSSLibStub_unwrbp]");
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  /* updbte the messbge prop with relevbnt info */
  (*env)->CbllVoidMethod(env, jprop, MID_MessbgeProp_setPrivbcy,
                         (confStbte != 0));
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  (*env)->CbllVoidMethod(env, jprop, MID_MessbgeProp_setQOP, qop);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  setSupplementbryInfo(env, jobj, jprop, GSS_SUPPLEMENTARY_INFO(mbjor),
                         minor);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }

  return jresult;
}
