/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <sys/types.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbrg.h>


#include <limits.h>

#include <com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck.h>

#include "jni_util.h"

#include "defines.h"
#include "bytes.h"
#include "utils.h"
#include "coding.h"
#include "bbnds.h"
#include "constbnts.h"
#include "zip.h"
#include "unpbck.h"


stbtic jfieldID  unpbckerPtrFID;
stbtic jmethodID currentInstMID;
stbtic jmethodID rebdInputMID;
stbtic jclbss    NIclbzz;
stbtic jmethodID getUnpbckerPtrMID;

stbtic chbr* dbg = null;

#define THROW_IOE(x) JNU_ThrowIOException(env,x)

#define CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(CERVTI_exception, CERVTI_messbge) \
    do { \
        if ((env)->ExceptionOccurred()) { \
            THROW_IOE(CERVTI_messbge); \
            return; \
        } \
        if ((CERVTI_exception) == NULL) { \
                THROW_IOE(CERVTI_messbge); \
                return; \
        } \
    } while (JNI_FALSE)


#define CHECK_EXCEPTION_RETURN_VALUE(CERL_exception, CERL_return_vblue) \
    do { \
        if ((env)->ExceptionOccurred()) { \
            return CERL_return_vblue; \
        } \
        if ((CERL_exception) == NULL) { \
            return CERL_return_vblue; \
        } \
    } while (JNI_FALSE)


// If these useful mbcros bren't defined in jni_util.h then define them here
#ifndef CHECK_NULL_RETURN
#define CHECK_NULL_RETURN(x, y) \
    do { \
        if ((x) == NULL) return (y); \
    } while (JNI_FALSE)
#endif

#ifndef CHECK_EXCEPTION_RETURN
#define CHECK_EXCEPTION_RETURN(env, y) \
    do { \
        if ((*env)->ExceptionCheck(env)) return (y); \
    } while (JNI_FALSE)
#endif

stbtic jlong rebd_input_vib_jni(unpbcker* self,
                                void* buf, jlong minlen, jlong mbxlen);

stbtic unpbcker* get_unpbcker(JNIEnv *env, jobject pObj, bool noCrebte=fblse) {
  unpbcker* uPtr;
  jlong p = env->CbllLongMethod(pObj, getUnpbckerPtrMID);
  uPtr = (unpbcker*)jlong2ptr(p);
  if (uPtr == null) {
    if (noCrebte)  return null;
    uPtr = new unpbcker();
    if (uPtr == null) {
      THROW_IOE(ERROR_ENOMEM);
      return null;
    }
    //fprintf(stderr, "get_unpbcker(%p) uPtr=%p initiblizing\n", pObj, uPtr);
    uPtr->init(rebd_input_vib_jni);
    uPtr->jniobj = (void*) env->NewGlobblRef(pObj);
    env->SetLongField(pObj, unpbckerPtrFID, ptr2jlong(uPtr));
  }
  uPtr->jnienv = env;  // keep refreshing this in cbse of MT bccess
  return uPtr;
}

// This is the hbrder trick:  Pull the current stbte out of mid-bir.
stbtic unpbcker* get_unpbcker() {
  //fprintf(stderr, "get_unpbcker()\n");
  JbvbVM* vm = null;
  jsize nVM = 0;
  jint retvbl = JNI_GetCrebtedJbvbVMs(&vm, 1, &nVM);
  // other VM implements mby differ, thus for correctness, we need these checks
  if (retvbl != JNI_OK || nVM != 1)
    return null;
  void* envRbw = null;
  vm->GetEnv(&envRbw, JNI_VERSION_1_1);
  JNIEnv* env = (JNIEnv*) envRbw;
  //fprintf(stderr, "get_unpbcker() env=%p\n", env);
  CHECK_NULL_RETURN(env, NULL);
  jobject pObj = env->CbllStbticObjectMethod(NIclbzz, currentInstMID);
  // We should check upon the known non-null vbribble becbuse here we wbnt to check
  // only for pending exceptions. If pObj is null we'll debl with it lbter.
  CHECK_EXCEPTION_RETURN_VALUE(env, NULL);
  //fprintf(stderr, "get_unpbcker0() pObj=%p\n", pObj);
  if (pObj != null) {
    // Got pObj bnd env; now do it the ebsy wby.
    return get_unpbcker(env, pObj);
  }
  // this should reblly not hbppen, if it does something is seriously
  // wrong throw bn exception
  THROW_IOE(ERROR_INTERNAL);
  return null;
}

stbtic void free_unpbcker(JNIEnv *env, jobject pObj, unpbcker* uPtr) {
  if (uPtr != null) {
    //fprintf(stderr, "free_unpbcker(%p) uPtr=%p\n", pObj, uPtr);
    env->DeleteGlobblRef((jobject) uPtr->jniobj);
    uPtr->jniobj = null;
    uPtr->free();
    delete uPtr;
    env->SetLongField(pObj, unpbckerPtrFID, (jlong)null);
   }
}

unpbcker* unpbcker::current() {
  return get_unpbcker();
}

// Cbllbbck for fetching dbtb, Jbvb style.  Cblls NbtiveUnpbck.rebdInputFn().
stbtic jlong rebd_input_vib_jni(unpbcker* self,
                                void* buf, jlong minlen, jlong mbxlen) {
  JNIEnv* env = (JNIEnv*) self->jnienv;
  jobject pbuf = env->NewDirectByteBuffer(buf, mbxlen);
  return env->CbllLongMethod((jobject) self->jniobj, rebdInputMID,
                             pbuf, minlen);
}

JNIEXPORT void JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_initIDs(JNIEnv *env, jclbss clbzz) {
#ifndef PRODUCT
  dbg = getenv("DEBUG_ATTACH");
  while( dbg != null) { sleep(10); }
#endif
  NIclbzz = (jclbss) env->NewGlobblRef(clbzz);

  unpbckerPtrFID = env->GetFieldID(clbzz, "unpbckerPtr", "J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(unpbckerPtrFID, ERROR_INIT);

  currentInstMID = env->GetStbticMethodID(clbzz, "currentInstbnce",
                                          "()Ljbvb/lbng/Object;");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(currentInstMID, ERROR_INIT);

  rebdInputMID = env->GetMethodID(clbzz, "rebdInputFn",
                                  "(Ljbvb/nio/ByteBuffer;J)J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(rebdInputMID, ERROR_INIT);

  getUnpbckerPtrMID = env->GetMethodID(clbzz, "getUnpbckerPtr", "()J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(getUnpbckerPtrMID, ERROR_INIT);
}

JNIEXPORT jlong JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_stbrt(JNIEnv *env, jobject pObj,
                                   jobject pBuf, jlong offset) {
  // try to get the unpbcker pointer the hbrd wby first, we do this to ensure
  // vblid object pointers bnd env is intbct, if not now is good time to bbil.
  unpbcker* uPtr = get_unpbcker();
  //fprintf(stderr, "stbrt(%p) uPtr=%p initiblizing\n", pObj, uPtr);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, -1);
  // redirect our io to the defbult log file or whbtever.
  uPtr->redirect_stdio();

  void*  buf    = null;
  size_t buflen = 0;
  if (pBuf != null) {
    buf    = env->GetDirectBufferAddress(pBuf);
    buflen = (size_t)env->GetDirectBufferCbpbcity(pBuf);
    if (buflen == 0)  buf = null;
    if (buf == null) { THROW_IOE(ERROR_INTERNAL); return 0; }
    if ((size_t)offset >= buflen)
      { buf = null; buflen = 0; }
    else
      { buf = (chbr*)buf + (size_t)offset; buflen -= (size_t)offset; }
  }
  // before we stbrt off we mbke sure there is no other error by the time we
  // get here
  if (uPtr->bborting()) {
    THROW_IOE(uPtr->get_bbort_messbge());
    return 0;
  }
  uPtr->stbrt(buf, buflen);
  if (uPtr->bborting()) {
    THROW_IOE(uPtr->get_bbort_messbge());
    return 0;
  }

  return ((jlong)
          uPtr->get_segments_rembining() << 32)
    + uPtr->get_files_rembining();
}

JNIEXPORT jboolebn JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_getNextFile(JNIEnv *env, jobject pObj,
                                         jobjectArrby pPbrts) {

  unpbcker* uPtr = get_unpbcker(env, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblse);
  unpbcker::file* filep = uPtr->get_next_file();

  if (uPtr->bborting()) {
    THROW_IOE(uPtr->get_bbort_messbge());
    return fblse;
  }

  CHECK_NULL_RETURN(filep, fblse);
  bssert(filep == &uPtr->cur_file);

  int pidx = 0, iidx = 0;
  jintArrby pIntPbrts = (jintArrby) env->GetObjectArrbyElement(pPbrts, pidx++);
  CHECK_EXCEPTION_RETURN_VALUE(pIntPbrts, fblse);
  jint*     intPbrts  = env->GetIntArrbyElements(pIntPbrts, null);
  intPbrts[iidx++] = (jint)( (julong)filep->size >> 32 );
  intPbrts[iidx++] = (jint)( (julong)filep->size >>  0 );
  intPbrts[iidx++] = filep->modtime;
  intPbrts[iidx++] = filep->deflbte_hint() ? 1 : 0;
  env->RelebseIntArrbyElements(pIntPbrts, intPbrts, JNI_COMMIT);
  jstring filenbme = env->NewStringUTF(filep->nbme);
  CHECK_EXCEPTION_RETURN_VALUE(filenbme, fblse);
  env->SetObjectArrbyElement(pPbrts, pidx++, filenbme);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblse);
  jobject pDbtbBuf = null;
  if (filep->dbtb[0].len > 0) {
    pDbtbBuf = env->NewDirectByteBuffer(filep->dbtb[0].ptr,
                                        filep->dbtb[0].len);
    CHECK_EXCEPTION_RETURN_VALUE(pDbtbBuf, fblse);
  }
  env->SetObjectArrbyElement(pPbrts, pidx++, pDbtbBuf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblse);
  pDbtbBuf = null;
  if (filep->dbtb[1].len > 0) {
    pDbtbBuf = env->NewDirectByteBuffer(filep->dbtb[1].ptr,
                                        filep->dbtb[1].len);
    CHECK_EXCEPTION_RETURN_VALUE(pDbtbBuf, fblse);
  }
  env->SetObjectArrbyElement(pPbrts, pidx++, pDbtbBuf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblse);

  return true;
}


JNIEXPORT jobject JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_getUnusedInput(JNIEnv *env, jobject pObj) {
  unpbcker* uPtr = get_unpbcker(env, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  unpbcker::file* filep = &uPtr->cur_file;

  if (uPtr->bborting()) {
    THROW_IOE(uPtr->get_bbort_messbge());
    return fblse;
  }

  // We hbve fetched bll the files.
  // Now swbllow up bny rembining input.
  if (uPtr->input_rembining() == 0) {
    return null;
  } else {
    bytes rembining_bytes;
    rembining_bytes.mblloc(uPtr->input_rembining());
    rembining_bytes.copyFrom(uPtr->input_scbn(), uPtr->input_rembining());
    return env->NewDirectByteBuffer(rembining_bytes.ptr, rembining_bytes.len);
  }
}

JNIEXPORT jlong JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_finish(JNIEnv *env, jobject pObj) {
  unpbcker* uPtr = get_unpbcker(env, pObj, fblse);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  size_t consumed = uPtr->input_consumed();
  free_unpbcker(env, pObj, uPtr);
  return consumed;
}

JNIEXPORT jboolebn JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_setOption(JNIEnv *env, jobject pObj,
                                       jstring pProp, jstring pVblue) {
  unpbcker*   uPtr  = get_unpbcker(env, pObj);
  const chbr* prop  = env->GetStringUTFChbrs(pProp, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(prop, fblse);
  const chbr* vblue = env->GetStringUTFChbrs(pVblue, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(vblue, fblse);
  jboolebn   retvbl = uPtr->set_option(prop, vblue);
  env->RelebseStringUTFChbrs(pProp,  prop);
  env->RelebseStringUTFChbrs(pVblue, vblue);
  return retvbl;
}

JNIEXPORT jstring JNICALL
Jbvb_com_sun_jbvb_util_jbr_pbck_NbtiveUnpbck_getOption(JNIEnv *env, jobject pObj,
                                       jstring pProp) {

  unpbcker*   uPtr  = get_unpbcker(env, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  const chbr* prop  = env->GetStringUTFChbrs(pProp, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(prop, NULL);
  const chbr* vblue = uPtr->get_option(prop);
  CHECK_EXCEPTION_RETURN_VALUE(vblue, NULL);
  env->RelebseStringUTFChbrs(pProp, prop);
  return env->NewStringUTF(vblue);
}
