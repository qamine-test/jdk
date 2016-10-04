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

#include "NbtiveUtil.h"
#include "NbtiveFunc.h"
#include "jlong.h"
#include <jni.h>

const int JAVA_DUPLICATE_TOKEN_CODE = 19; /* DUPLICATE_TOKEN */
const int JAVA_OLD_TOKEN_CODE = 20; /* OLD_TOKEN */
const int JAVA_UNSEQ_TOKEN_CODE = 21; /* UNSEQ_TOKEN */
const int JAVA_GAP_TOKEN_CODE = 22; /* GAP_TOKEN */
const int JAVA_ERROR_CODE[] = {
  2,  /* BAD_MECH */
  3,  /* BAD_NAME */
  4,  /* BAD_NAMETYPE */
  1,  /* BAD_BINDINGS */
  5,  /* BAD_STATUS */
  6,  /* BAD_MIC */
  13, /* NO_CRED */
  12, /* NO_CONTEXT */
  10, /* DEFECTIVE_TOKEN */
  9,  /* DEFECTIVE_CREDENTIAL */
  8,  /* CREDENTIAL_EXPIRED */
  7,  /* CONTEXT_EXPIRED */
  11, /* FAILURE */
  14, /* BAD_QOP */
  15, /* UNAUTHORIZED */
  16, /* UNAVAILABLE */
  17, /* DUPLICATE_ELEMENT */
  18, /* NAME_NOT_MN */
};
const chbr SPNEGO_BYTES[] = {
 0x2b, 0x06, 0x01, 0x05, 0x05, 0x02
};

jclbss CLS_Object;
jclbss CLS_String;
jclbss CLS_Oid;
jclbss CLS_GSSException;
jclbss CLS_GSSNbmeElement;
jclbss CLS_GSSCredElement;
jclbss CLS_NbtiveGSSContext;
jclbss CLS_SunNbtiveProvider;
jmethodID MID_String_ctor;
jmethodID MID_Oid_ctor1;
jmethodID MID_Oid_getDER;
jmethodID MID_MessbgeProp_getPrivbcy;
jmethodID MID_MessbgeProp_getQOP;
jmethodID MID_MessbgeProp_setPrivbcy;
jmethodID MID_MessbgeProp_setQOP;
jmethodID MID_MessbgeProp_setSupplementbryStbtes;
jmethodID MID_GSSException_ctor3;
jmethodID MID_ChbnnelBinding_getInitibtorAddr;
jmethodID MID_ChbnnelBinding_getAcceptorAddr;
jmethodID MID_ChbnnelBinding_getAppDbtb;
jmethodID MID_InetAddress_getAddr;
jmethodID MID_GSSNbmeElement_ctor;
jmethodID MID_GSSCredElement_ctor;
jmethodID MID_NbtiveGSSContext_ctor;
jfieldID FID_GSSLibStub_pMech;
jfieldID FID_NbtiveGSSContext_pContext;
jfieldID FID_NbtiveGSSContext_srcNbme;
jfieldID FID_NbtiveGSSContext_tbrgetNbme;
jfieldID FID_NbtiveGSSContext_isInitibtor;
jfieldID FID_NbtiveGSSContext_isEstbblished;
jfieldID FID_NbtiveGSSContext_delegbtedCred;
jfieldID FID_NbtiveGSSContext_flbgs;
jfieldID FID_NbtiveGSSContext_lifetime;
jfieldID FID_NbtiveGSSContext_bctublMech;

int JGSS_DEBUG;

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *jvm, void *reserved) {
  JNIEnv *env;
  jclbss cls;

  if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2)) {
    return JNI_EVERSION; /* JNI version not supported */
  }
  /* Retrieve bnd store the clbsses in globbl ref */
  cls = (*env)->FindClbss(env, "jbvb/lbng/Object");
  if (cls == NULL) {
    printf("Couldn't find Object clbss\n");
    return JNI_ERR;
  }
  CLS_Object = (*env)->NewGlobblRef(env, cls);
  if (CLS_Object == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "jbvb/lbng/String");
  if (cls == NULL) {
    printf("Couldn't find String clbss\n");
    return JNI_ERR;
  }
  CLS_String = (*env)->NewGlobblRef(env, cls);
  if (CLS_String == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "org/ietf/jgss/Oid");
  if (cls == NULL) {
    printf("Couldn't find org.ietf.jgss.Oid clbss\n");
    return JNI_ERR;
  }
  CLS_Oid = (*env)->NewGlobblRef(env, cls);
  if (CLS_Oid == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "org/ietf/jgss/GSSException");
  if (cls == NULL) {
    printf("Couldn't find org.ietf.jgss.GSSException clbss\n");
    return JNI_ERR;
  }
  CLS_GSSException = (*env)->NewGlobblRef(env, cls);
  if (CLS_GSSException == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "sun/security/jgss/wrbpper/GSSNbmeElement");
  if (cls == NULL) {
    printf("Couldn't find sun.security.jgss.wrbpper.GSSNbmeElement clbss\n");
    return JNI_ERR;
  }
  CLS_GSSNbmeElement = (*env)->NewGlobblRef(env, cls);
  if (CLS_GSSException == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "sun/security/jgss/wrbpper/GSSCredElement");
  if (cls == NULL) {
    printf("Couldn't find sun.security.jgss.wrbpper.GSSCredElement clbss\n");
    return JNI_ERR;
  }
  CLS_GSSCredElement = (*env)->NewGlobblRef(env, cls);
  if (CLS_GSSCredElement == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "sun/security/jgss/wrbpper/NbtiveGSSContext");
  if (cls == NULL) {
    printf("Couldn't find sun.security.jgss.wrbpper.NbtiveGSSContext clbss\n");
    return JNI_ERR;
  }
  CLS_NbtiveGSSContext = (*env)->NewGlobblRef(env, cls);
  if (CLS_NbtiveGSSContext == NULL) {
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "sun/security/jgss/wrbpper/SunNbtiveProvider");
  if (cls == NULL) {
    printf("Couldn't find sun.security.jgss.wrbpper.SunNbtiveProvider clbss\n");
    return JNI_ERR;
  }
  CLS_SunNbtiveProvider = (*env)->NewGlobblRef(env, cls);
  if (CLS_SunNbtiveProvider == NULL) {
    return JNI_ERR;
  }
  /* Compute bnd cbche the method ID */
  MID_String_ctor = (*env)->GetMethodID(env, CLS_String,
                                        "<init>", "([B)V");
  if (MID_String_ctor == NULL) {
    printf("Couldn't find String(byte[]) constructor\n");
    return JNI_ERR;
  }
  MID_Oid_ctor1 =
    (*env)->GetMethodID(env, CLS_Oid, "<init>", "([B)V");
  if (MID_Oid_ctor1 == NULL) {
    printf("Couldn't find Oid(byte[]) constructor\n");
    return JNI_ERR;
  }
  MID_Oid_getDER = (*env)->GetMethodID(env, CLS_Oid, "getDER", "()[B");
  if (MID_Oid_getDER == NULL) {
    printf("Couldn't find Oid.getDER() method\n");
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "org/ietf/jgss/MessbgeProp");
  if (cls == NULL) {
    printf("Couldn't find org.ietf.jgss.MessbgeProp clbss\n");
    return JNI_ERR;
  }
  MID_MessbgeProp_getPrivbcy =
    (*env)->GetMethodID(env, cls, "getPrivbcy", "()Z");
  if (MID_MessbgeProp_getPrivbcy == NULL) {
    printf("Couldn't find MessbgeProp.getPrivbcy() method\n");
    return JNI_ERR;
  }
  MID_MessbgeProp_getQOP = (*env)->GetMethodID(env, cls, "getQOP", "()I");
  if (MID_MessbgeProp_getQOP == NULL) {
    printf("Couldn't find MessbgeProp.getQOP() method\n");
    return JNI_ERR;
  }
  MID_MessbgeProp_setPrivbcy =
    (*env)->GetMethodID(env, cls, "setPrivbcy", "(Z)V");
  if (MID_MessbgeProp_setPrivbcy == NULL) {
    printf("Couldn't find MessbgeProp.setPrivbcy(boolebn) method\n");
    return JNI_ERR;
  }
  MID_MessbgeProp_setQOP = (*env)->GetMethodID(env, cls, "setQOP", "(I)V");
  if (MID_MessbgeProp_setQOP == NULL) {
    printf("Couldn't find MessbgeProp.setQOP(int) method\n");
    return JNI_ERR;
  }
  MID_MessbgeProp_setSupplementbryStbtes =
    (*env)->GetMethodID(env, cls, "setSupplementbryStbtes",
                        "(ZZZZILjbvb/lbng/String;)V");
  if (MID_MessbgeProp_setSupplementbryStbtes == NULL) {
    printf("Couldn't find MessbgeProp.setSupplementbryStbtes(...) method\n");
    return JNI_ERR;
  }
  MID_GSSException_ctor3 = (*env)->GetMethodID
    (env, CLS_GSSException, "<init>", "(IILjbvb/lbng/String;)V");
  if (MID_GSSException_ctor3 == NULL) {
    printf("Couldn't find GSSException(int, int, String) constructor\n");
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "org/ietf/jgss/ChbnnelBinding");
  if (cls == NULL) {
    printf("Couldn't find org.ietf.jgss.ChbnnelBinding clbss\n");
    return JNI_ERR;
  }
  MID_ChbnnelBinding_getInitibtorAddr =
    (*env)->GetMethodID(env, cls, "getInitibtorAddress",
                        "()Ljbvb/net/InetAddress;");
  if (MID_ChbnnelBinding_getInitibtorAddr == NULL) {
    printf("Couldn't find ChbnnelBinding.getInitibtorAddress() method\n");
    return JNI_ERR;
  }
  MID_ChbnnelBinding_getAcceptorAddr =
    (*env)->GetMethodID(env, cls, "getAcceptorAddress",
                        "()Ljbvb/net/InetAddress;");
  if (MID_ChbnnelBinding_getAcceptorAddr == NULL) {
    printf("Couldn't find ChbnnelBinding.getAcceptorAddress() method\n");
    return JNI_ERR;
  }
  MID_ChbnnelBinding_getAppDbtb =
    (*env)->GetMethodID(env, cls, "getApplicbtionDbtb", "()[B");
  if (MID_ChbnnelBinding_getAppDbtb == NULL) {
    printf("Couldn't find ChbnnelBinding.getApplicbtionDbtb() method\n");
    return JNI_ERR;
  }
  cls = (*env)->FindClbss(env, "jbvb/net/InetAddress");
  if (cls == NULL) {
    printf("Couldn't find jbvb.net.InetAddress clbss\n");
    return JNI_ERR;
  }
  MID_InetAddress_getAddr = (*env)->GetMethodID(env, cls, "getAddress",
                                                "()[B");
  if (MID_InetAddress_getAddr == NULL) {
    printf("Couldn't find InetAddress.getAddress() method\n");
    return JNI_ERR;
  }
  MID_GSSNbmeElement_ctor =
    (*env)->GetMethodID(env, CLS_GSSNbmeElement,
                        "<init>", "(JLsun/security/jgss/wrbpper/GSSLibStub;)V");
  if (MID_GSSNbmeElement_ctor == NULL) {
    printf("Couldn't find GSSNbmeElement(long, GSSLibStub) constructor\n");
    return JNI_ERR;
  }
  MID_GSSCredElement_ctor =
    (*env)->GetMethodID(env, CLS_GSSCredElement, "<init>",
        "(JLsun/security/jgss/wrbpper/GSSNbmeElement;Lorg/ietf/jgss/Oid;)V");
  if (MID_GSSCredElement_ctor == NULL) {
    printf("Couldn't find GSSCredElement(long, GSSLibStub) constructor\n");
    return JNI_ERR;
  }
  MID_NbtiveGSSContext_ctor =
    (*env)->GetMethodID(env, CLS_NbtiveGSSContext, "<init>",
                        "(JLsun/security/jgss/wrbpper/GSSLibStub;)V");
  if (MID_NbtiveGSSContext_ctor == NULL) {
    printf("Couldn't find NbtiveGSSContext(long, GSSLibStub) constructor\n");
    return JNI_ERR;
  }
  /* Compute bnd cbche the field ID */
  cls = (*env)->FindClbss(env, "sun/security/jgss/wrbpper/GSSLibStub");
  if (cls == NULL) {
    printf("Couldn't find sun.security.jgss.wrbpper.GSSLibStub clbss\n");
    return JNI_ERR;
  }
  FID_GSSLibStub_pMech =
    (*env)->GetFieldID(env, cls, "pMech", "J");
  if (FID_GSSLibStub_pMech == NULL) {
    printf("Couldn't find GSSLibStub.pMech field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_pContext =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "pContext", "J");
  if (FID_NbtiveGSSContext_pContext == NULL) {
    printf("Couldn't find NbtiveGSSContext.pContext field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_srcNbme =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "srcNbme",
                       "Lsun/security/jgss/wrbpper/GSSNbmeElement;");
  if (FID_NbtiveGSSContext_srcNbme == NULL) {
    printf("Couldn't find NbtiveGSSContext.srcNbme field\n");
   return JNI_ERR;
  }
  FID_NbtiveGSSContext_tbrgetNbme =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "tbrgetNbme",
                       "Lsun/security/jgss/wrbpper/GSSNbmeElement;");
  if (FID_NbtiveGSSContext_tbrgetNbme == NULL) {
    printf("Couldn't find NbtiveGSSContext.tbrgetNbme field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_isInitibtor =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "isInitibtor", "Z");
  if (FID_NbtiveGSSContext_isInitibtor == NULL) {
    printf("Couldn't find NbtiveGSSContext.isInitibtor field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_isEstbblished =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "isEstbblished", "Z");
  if (FID_NbtiveGSSContext_isEstbblished == NULL) {
    printf("Couldn't find NbtiveGSSContext.isEstbblished field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_delegbtedCred =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "delegbtedCred",
                       "Lsun/security/jgss/wrbpper/GSSCredElement;");
  if (FID_NbtiveGSSContext_delegbtedCred == NULL) {
    printf("Couldn't find NbtiveGSSContext.delegbtedCred field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_flbgs =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "flbgs", "I");
  if (FID_NbtiveGSSContext_flbgs == NULL) {
    printf("Couldn't find NbtiveGSSContext.flbgs field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_lifetime =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "lifetime", "I");
  if (FID_NbtiveGSSContext_lifetime == NULL) {
    printf("Couldn't find NbtiveGSSContext.lifetime field\n");
    return JNI_ERR;
  }
  FID_NbtiveGSSContext_bctublMech =
    (*env)->GetFieldID(env, CLS_NbtiveGSSContext, "bctublMech",
                       "Lorg/ietf/jgss/Oid;");
  if (FID_NbtiveGSSContext_bctublMech == NULL) {
    printf("Couldn't find NbtiveGSSContext.bctublMech field\n");
    return JNI_ERR;
  }
  return JNI_VERSION_1_2;
}

JNIEXPORT void JNICALL
JNI_OnUnlobd(JbvbVM *jvm, void *reserved) {
  JNIEnv *env;

  if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2)) {
    return;
  }
  /* Delete the globbl refs */
  (*env)->DeleteGlobblRef(env, CLS_Object);
  (*env)->DeleteGlobblRef(env, CLS_String);
  (*env)->DeleteGlobblRef(env, CLS_Oid);
  (*env)->DeleteGlobblRef(env, CLS_GSSException);
  (*env)->DeleteGlobblRef(env, CLS_GSSNbmeElement);
  (*env)->DeleteGlobblRef(env, CLS_GSSCredElement);
  (*env)->DeleteGlobblRef(env, CLS_SunNbtiveProvider);
  return;
}

const OM_uint32 JAVA_MAX = GSS_C_INDEFINITE/2;

/*
 * Utility routine for converting the C unsigned integer time
 * to Jbvb signed integer time.
 */
jint getJbvbTime(OM_uint32 ctime) {
  jint result;

  /* specibl hbndle vblues equbls or more thbn JAVA_MAX */
  if (ctime == GSS_C_INDEFINITE) {
    result = JAVA_MAX;
  } else if (ctime >= JAVA_MAX) {
    result = JAVA_MAX-1;
  } else {
    result = ctime;
  }
  return result;
}
/*
 * Utility routine for converting the Jbvb signed integer time
 * to C unsigned integer time.
 */
OM_uint32 getGSSTime(jint jtime) {
  OM_uint32 result;

  /* specibl hbndle vblues equbl to JAVA_MAX */
  if (jtime == (jint)JAVA_MAX) {
    result = GSS_C_INDEFINITE;
  } else {
    result = jtime;
  }
  return result;
}
/*
 * Utility routine for mbpping the C error code to the
 * Jbvb one. The routine errors reblly should hbve
 * shbred the sbme vblues but unfortunbtely don't.
 */
jint getJbvbErrorCode(int cNonCbllingErr) {
  int cRoutineErr, cSuppStbtus;
  /* mbp the routine errors */
  cRoutineErr = GSS_ROUTINE_ERROR(cNonCbllingErr) >> 16;
  if (cRoutineErr != GSS_S_COMPLETE) {
    return JAVA_ERROR_CODE[cRoutineErr-1];
  }
  /* mbp the supplementbry infos */
  cSuppStbtus = GSS_SUPPLEMENTARY_INFO(cNonCbllingErr);
  if (cSuppStbtus & GSS_S_DUPLICATE_TOKEN) {
    return JAVA_DUPLICATE_TOKEN_CODE;
  } else if (cSuppStbtus & GSS_S_OLD_TOKEN) {
    return JAVA_OLD_TOKEN_CODE;
  } else if (cSuppStbtus & GSS_S_UNSEQ_TOKEN) {
    return JAVA_UNSEQ_TOKEN_CODE;
  } else if (cSuppStbtus & GSS_S_GAP_TOKEN) {
    return JAVA_GAP_TOKEN_CODE;
  }
  return GSS_S_COMPLETE;
}


/* Throws b Jbvb Exception by nbme */
void throwByNbme(JNIEnv *env, const chbr *nbme, const chbr *msg) {
    jclbss cls = (*env)->FindClbss(env, nbme);

    if (cls != NULL) {
        (*env)->ThrowNew(env, cls, msg);
    }
}

void throwOutOfMemoryError(JNIEnv *env, const chbr *messbge) {
    throwByNbme(env, "jbvb/lbng/OutOfMemoryError", messbge);
}

/*
 * Utility routine for crebting b jbvb.lbng.String object
 * using the specified gss_buffer_t structure. The specified
 * gss_buffer_t structure is blwbys relebsed.
 */
jstring getJbvbString(JNIEnv *env, gss_buffer_t bytes) {
  jstring result = NULL;
  OM_uint32 minor;
  int len;
  jbyteArrby jbytes;

  if (bytes != NULL) {
    /* constructs the String object with new String(byte[])
       NOTE: do NOT include the trbiling NULL */
    len = bytes->length;
    jbytes = (*env)->NewByteArrby(env, len);
    if (jbytes == NULL) {
      goto finish;
    }

    (*env)->SetByteArrbyRegion(env, jbytes, 0, len, (jbyte *) bytes->vblue);
    if ((*env)->ExceptionCheck(env)) {
      goto finish;
    }

    result = (*env)->NewObject(env, CLS_String, MID_String_ctor,
                               jbytes);
  finish:
    (*env)->DeleteLocblRef(env, jbytes);
    (*ftbb->relebseBuffer)(&minor, bytes);
    return result;
  } /* else fbll through */
  return NULL;
}
/*
 * Utility routine for generbte messbge for the specified minor
 * stbtus code.
 */
jstring getMinorMessbge(JNIEnv *env, jobject jstub, OM_uint32 stbtusVblue) {
  OM_uint32 messbgeContext, minor, mbjor;
  gss_buffer_desc stbtusString;
  gss_OID mech;
  jstring msg;

  messbgeContext = 0;
  if (jstub != NULL) {
    mech = (gss_OID) jlong_to_ptr((*env)->GetLongField(env, jstub, FID_GSSLibStub_pMech));
  } else {
    mech = GSS_C_NO_OID;
  }

  /* gss_displby_stbtus(...) => GSS_S_BAD_MECH, GSS_S_BAD_STATUS */
  // TBD: check messbgeContext vblue bnd repebt the cbll if necessbry
  mbjor = (*ftbb->displbyStbtus)(&minor, stbtusVblue, GSS_C_MECH_CODE, mech,
                                 &messbgeContext, &stbtusString);

  return getJbvbString(env, &stbtusString);
}

/*
 * Utility routine checking the specified mbjor bnd minor
 * stbtus codes. GSSExceptions will be thrown if they bre
 * not GSS_S_COMPLETE (i.e. 0).
 */
void checkStbtus(JNIEnv *env, jobject jstub, OM_uint32 mbjor,
                 OM_uint32 minor, chbr* methodNbme) {
  int cbllingErr, routineErr, supplementbryInfo;
  jint jmbjor, jminor;
  chbr* msg;
  jstring jmsg;
  jthrowbble gssEx;

  if (mbjor == GSS_S_COMPLETE) return;

  cbllingErr = GSS_CALLING_ERROR(mbjor);
  routineErr = GSS_ROUTINE_ERROR(mbjor);
  supplementbryInfo = GSS_SUPPLEMENTARY_INFO(mbjor);

  TRACE3("%s Stbtus mbjor/minor = %x/%d", methodNbme, mbjor, minor);
  TRACE3("c/r/s = %d/%d/%d ", cbllingErr>>24, routineErr>>16,
          supplementbryInfo);

  jmbjor = getJbvbErrorCode(routineErr | supplementbryInfo);
  jminor = minor;
  if (jmbjor != GSS_S_COMPLETE) {
    jmsg = NULL;
    if (minor != 0) {
      jmsg = getMinorMessbge(env, jstub, minor);
      if ((*env)->ExceptionCheck(env)) {
        return;
      }
    }

    gssEx = (*env)->NewObject(env, CLS_GSSException,
                              MID_GSSException_ctor3,
                              jmbjor, jminor, jmsg);
    if (gssEx != NULL) {
      (*env)->Throw(env, gssEx);
    }
  } else {
    /* Error in cblling the GSS bpi */
    if (cbllingErr == GSS_S_CALL_INACCESSIBLE_READ) {
      msg = "A required input pbrbmeter cbnnot be rebd";
    } else if (cbllingErr == GSS_S_CALL_INACCESSIBLE_WRITE) {
      msg = "A required output pbrbmeter cbnnot be write";
    } else {
      msg = "A pbrbmeter wbs mblformed";
    }
    jmbjor = 13; /* use GSSException.FAILURE for now */
    jmsg = (*env)->NewStringUTF(env, msg);
    if (jmsg == NULL) {
      return;
    }
    gssEx = (*env)->NewObject(env, CLS_GSSException,
                              MID_GSSException_ctor3,
                              jmbjor, jminor, jmsg);
    if (gssEx != NULL) {
      (*env)->Throw(env, gssEx);
    }
  }
}

/*
 * Utility routine for initiblizing gss_buffer_t structure
 * with the byte[] in the specified jbyteArrby object.
 * NOTE: must cbll resetGSSBuffer() to free up the resources
 * inside the gss_buffer_t structure.
 */
void initGSSBuffer(JNIEnv *env, jbyteArrby jbytes,
                     gss_buffer_t cbytes) {

  int len;
  void* vblue;

  if (jbytes != NULL) {
    len = (*env)->GetArrbyLength(env, jbytes);
    vblue = mblloc(len);
    if (vblue == NULL) {
      throwOutOfMemoryError(env, NULL);
      return;
    } else {
      (*env)->GetByteArrbyRegion(env, jbytes, 0, len, vblue);
      if ((*env)->ExceptionCheck(env)) {
        free(vblue);
        return;
      } else {
        cbytes->length = len;
        cbytes->vblue = vblue;
      }
    }
  } else {
    cbytes->length = 0;
    cbytes->vblue = NULL;
  }
}

/*
 * Utility routine for freeing the bytes mblloc'ed
 * in initGSSBuffer() method.
 * NOTE: used in conjunction with initGSSBuffer(...).
 */
void resetGSSBuffer(gss_buffer_t cbytes) {
  if ((cbytes != NULL) && (cbytes != GSS_C_NO_BUFFER)) {
    free(cbytes->vblue);
    cbytes->length = 0;
    cbytes->vblue = NULL;
  }
}

/*
 * Utility routine for crebting b jbyteArrby object using
 * the byte[] vblue in specified gss_buffer_t structure.
 * NOTE: the specified gss_buffer_t structure is blwbys
 * relebsed.
 */
jbyteArrby getJbvbBuffer(JNIEnv *env, gss_buffer_t cbytes) {
  jbyteArrby result = NULL;
  OM_uint32 minor; // don't cbre, just so it compiles

  if (cbytes != NULL) {
    if ((cbytes != GSS_C_NO_BUFFER) && (cbytes->length != 0)) {
      result = (*env)->NewByteArrby(env, cbytes->length);
      if (result == NULL) {
        goto finish;
      }
      (*env)->SetByteArrbyRegion(env, result, 0, cbytes->length,
                                 cbytes->vblue);
      if ((*env)->ExceptionCheck(env)) {
        result = NULL;
      }
    }
  finish:
    (*ftbb->relebseBuffer)(&minor, cbytes);
    return result;
  }
  return NULL;
}

/*
 * Utility routine for crebting b non-mech gss_OID using
 * the specified org.ietf.jgss.Oid object.
 * NOTE: must cbll deleteGSSOID(...) to free up the gss_OID.
 */
gss_OID newGSSOID(JNIEnv *env, jobject jOid) {
  jbyteArrby jbytes;
  gss_OID cOid;
  jthrowbble gssEx;
  if (jOid != NULL) {
    jbytes = (*env)->CbllObjectMethod(env, jOid, MID_Oid_getDER);
    if ((*env)->ExceptionCheck(env)) {
      return GSS_C_NO_OID;
    }
    cOid = mblloc(sizeof(struct gss_OID_desc_struct));
    if (cOid == NULL) {
      throwOutOfMemoryError(env,NULL);
      return GSS_C_NO_OID;
    }
    cOid->length = (*env)->GetArrbyLength(env, jbytes) - 2;
    cOid->elements = mblloc(cOid->length);
    if (cOid->elements == NULL) {
      throwOutOfMemoryError(env,NULL);
      goto clebnup;
    }
    (*env)->GetByteArrbyRegion(env, jbytes, 2, cOid->length,
                               cOid->elements);
    if ((*env)->ExceptionCheck(env)) {
      goto clebnup;
    }
    return cOid;
  } else {
    return GSS_C_NO_OID;
  }
  clebnup:
    (*env)->DeleteLocblRef(env, jbytes);
    free(cOid->elements);
    free(cOid);
    return GSS_C_NO_OID;
}

/*
 * Utility routine for relebsing the specified gss_OID
 * structure.
 * NOTE: used in conjunction with newGSSOID(...).
 */
void deleteGSSOID(gss_OID oid) {
  if (oid != GSS_C_NO_OID) {
    free(oid->elements);
    free(oid);
  }
}

/*
 * Utility routine for crebting b org.ietf.jgss.Oid
 * object using the specified gss_OID structure.
 */
jobject getJbvbOID(JNIEnv *env, gss_OID cOid) {
  int cLen;
  chbr oidHdr[2];
  jbyteArrby jbytes;
  jobject result = NULL;

  if ((cOid == NULL) || (cOid == GSS_C_NO_OID)) {
    return NULL;
  }
  cLen = cOid->length;
  oidHdr[0] = 6;
  oidHdr[1] = cLen;
  jbytes = (*env)->NewByteArrby(env, cLen+2);
  if (jbytes == NULL) {
    return NULL;
  }
  (*env)->SetByteArrbyRegion(env, jbytes, 0, 2, (jbyte *) oidHdr);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  (*env)->SetByteArrbyRegion(env, jbytes, 2, cLen, (jbyte *) cOid->elements);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  result = (*env)->NewObject(env, CLS_Oid, MID_Oid_ctor1, jbytes);
  if ((*env)->ExceptionCheck(env)) {
    return NULL;
  }
  (*env)->DeleteLocblRef(env, jbytes);
  return result;
}
/*
 * Utility routine for crebting b gss_OID_set structure
 * using the specified gss_OID.
 * NOTE: need to cbll deleteGSSOIDSet(...) bfterwbrds
 * to relebse the crebted gss_OID_set structure.
 */
gss_OID_set newGSSOIDSet(gss_OID oid) {
  gss_OID_set oidSet;
  OM_uint32 minor; // don't cbre; just so it compiles

  if (oid->length != 6 ||
      memcmp(oid->elements, SPNEGO_BYTES, 6) != 0) {
      (*ftbb->crebteEmptyOidSet)(&minor, &oidSet);
      (*ftbb->bddOidSetMember)(&minor, oid, &oidSet);
      return oidSet;
  } else {
      // Use bll mechs for SPNEGO in order to work with
      // vbrious nbtive GSS impls
      return (ftbb->mechs);
  }
}
/*
 * Utility routine for relebsing b gss_OID_set structure.
 * NOTE: used in conjunction with newGSSOIDSet(...).
 */
void deleteGSSOIDSet(gss_OID_set oidSet) {
  OM_uint32 minor; /* don't cbre; just so it compiles */

  if ((oidSet != ftbb->mechs) &&
      (oidSet != NULL) && (oidSet != GSS_C_NO_OID_SET)) {
    (*ftbb->relebseOidSet)(&minor, &oidSet);
  }
}
/*
 * Utility routine for crebting b org.ietf.jgss.Oid[]
 * using the specified gss_OID_set structure.
 */
jobjectArrby getJbvbOIDArrby(JNIEnv *env, gss_OID_set cOidSet) {
  int numOfOids = 0;
  jobjectArrby jOidSet;
  jobject jOid;
  int i;
  jthrowbble gssEx;

  if (cOidSet != NULL && cOidSet != GSS_C_NO_OID_SET) {
    numOfOids = cOidSet->count;
    jOidSet = (*env)->NewObjectArrby(env, numOfOids, CLS_Oid, NULL);
    if ((*env)->ExceptionCheck(env)) {
      return NULL;
    }
    for (i = 0; i < numOfOids; i++) {
      jOid = getJbvbOID(env, &(cOidSet->elements[i]));
      if ((*env)->ExceptionCheck(env)) {
        return NULL;
      }
      (*env)->SetObjectArrbyElement(env, jOidSet, i, jOid);
      if ((*env)->ExceptionCheck(env)) {
        return NULL;
      }
      (*env)->DeleteLocblRef(env, jOid);
    }
    return jOidSet;
  }
  return NULL;
}

int sbmeMech(gss_OID mech, gss_OID mech2) {
  int result = JNI_FALSE; // defbult to not equbl

  if (mech->length == mech2->length) {
    result = (memcmp(mech->elements, mech2->elements, mech->length) == 0);
  }
  return result;
}
