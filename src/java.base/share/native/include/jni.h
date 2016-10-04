/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * We used pbrt of Netscbpe's Jbvb Runtime Interfbce (JRI) bs the stbrting
 * point of our design bnd implementbtion.
 */

/******************************************************************************
 * Jbvb Runtime Interfbce
 * Copyright (c) 1996 Netscbpe Communicbtions Corporbtion. All rights reserved.
 *****************************************************************************/

#ifndef _JAVASOFT_JNI_H_
#define _JAVASOFT_JNI_H_

#include <stdio.h>
#include <stdbrg.h>

/* jni_md.h contbins the mbchine-dependent typedefs for jbyte, jint
   bnd jlong */

#include "jni_md.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * JNI Types
 */

#ifndef JNI_TYPES_ALREADY_DEFINED_IN_JNI_MD_H

typedef unsigned chbr   jboolebn;
typedef unsigned short  jchbr;
typedef short           jshort;
typedef flobt           jflobt;
typedef double          jdouble;

typedef jint            jsize;

#ifdef __cplusplus

clbss _jobject {};
clbss _jclbss : public _jobject {};
clbss _jthrowbble : public _jobject {};
clbss _jstring : public _jobject {};
clbss _jbrrby : public _jobject {};
clbss _jboolebnArrby : public _jbrrby {};
clbss _jbyteArrby : public _jbrrby {};
clbss _jchbrArrby : public _jbrrby {};
clbss _jshortArrby : public _jbrrby {};
clbss _jintArrby : public _jbrrby {};
clbss _jlongArrby : public _jbrrby {};
clbss _jflobtArrby : public _jbrrby {};
clbss _jdoubleArrby : public _jbrrby {};
clbss _jobjectArrby : public _jbrrby {};

typedef _jobject *jobject;
typedef _jclbss *jclbss;
typedef _jthrowbble *jthrowbble;
typedef _jstring *jstring;
typedef _jbrrby *jbrrby;
typedef _jboolebnArrby *jboolebnArrby;
typedef _jbyteArrby *jbyteArrby;
typedef _jchbrArrby *jchbrArrby;
typedef _jshortArrby *jshortArrby;
typedef _jintArrby *jintArrby;
typedef _jlongArrby *jlongArrby;
typedef _jflobtArrby *jflobtArrby;
typedef _jdoubleArrby *jdoubleArrby;
typedef _jobjectArrby *jobjectArrby;

#else

struct _jobject;

typedef struct _jobject *jobject;
typedef jobject jclbss;
typedef jobject jthrowbble;
typedef jobject jstring;
typedef jobject jbrrby;
typedef jbrrby jboolebnArrby;
typedef jbrrby jbyteArrby;
typedef jbrrby jchbrArrby;
typedef jbrrby jshortArrby;
typedef jbrrby jintArrby;
typedef jbrrby jlongArrby;
typedef jbrrby jflobtArrby;
typedef jbrrby jdoubleArrby;
typedef jbrrby jobjectArrby;

#endif

typedef jobject jwebk;

typedef union jvblue {
    jboolebn z;
    jbyte    b;
    jchbr    c;
    jshort   s;
    jint     i;
    jlong    j;
    jflobt   f;
    jdouble  d;
    jobject  l;
} jvblue;

struct _jfieldID;
typedef struct _jfieldID *jfieldID;

struct _jmethodID;
typedef struct _jmethodID *jmethodID;

/* Return vblues from jobjectRefType */
typedef enum _jobjectType {
     JNIInvblidRefType    = 0,
     JNILocblRefType      = 1,
     JNIGlobblRefType     = 2,
     JNIWebkGlobblRefType = 3
} jobjectRefType;


#endif /* JNI_TYPES_ALREADY_DEFINED_IN_JNI_MD_H */

/*
 * jboolebn constbnts
 */

#define JNI_FALSE 0
#define JNI_TRUE 1

/*
 * possible return vblues for JNI functions.
 */

#define JNI_OK           0                 /* success */
#define JNI_ERR          (-1)              /* unknown error */
#define JNI_EDETACHED    (-2)              /* threbd detbched from the VM */
#define JNI_EVERSION     (-3)              /* JNI version error */
#define JNI_ENOMEM       (-4)              /* not enough memory */
#define JNI_EEXIST       (-5)              /* VM blrebdy crebted */
#define JNI_EINVAL       (-6)              /* invblid brguments */

/*
 * used in RelebseScblbrArrbyElements
 */

#define JNI_COMMIT 1
#define JNI_ABORT 2

/*
 * used in RegisterNbtives to describe nbtive method nbme, signbture,
 * bnd function pointer.
 */

typedef struct {
    chbr *nbme;
    chbr *signbture;
    void *fnPtr;
} JNINbtiveMethod;

/*
 * JNI Nbtive Method Interfbce.
 */

struct JNINbtiveInterfbce_;

struct JNIEnv_;

#ifdef __cplusplus
typedef JNIEnv_ JNIEnv;
#else
typedef const struct JNINbtiveInterfbce_ *JNIEnv;
#endif

/*
 * JNI Invocbtion Interfbce.
 */

struct JNIInvokeInterfbce_;

struct JbvbVM_;

#ifdef __cplusplus
typedef JbvbVM_ JbvbVM;
#else
typedef const struct JNIInvokeInterfbce_ *JbvbVM;
#endif

struct JNINbtiveInterfbce_ {
    void *reserved0;
    void *reserved1;
    void *reserved2;

    void *reserved3;
    jint (JNICALL *GetVersion)(JNIEnv *env);

    jclbss (JNICALL *DefineClbss)
      (JNIEnv *env, const chbr *nbme, jobject lobder, const jbyte *buf,
       jsize len);
    jclbss (JNICALL *FindClbss)
      (JNIEnv *env, const chbr *nbme);

    jmethodID (JNICALL *FromReflectedMethod)
      (JNIEnv *env, jobject method);
    jfieldID (JNICALL *FromReflectedField)
      (JNIEnv *env, jobject field);

    jobject (JNICALL *ToReflectedMethod)
      (JNIEnv *env, jclbss cls, jmethodID methodID, jboolebn isStbtic);

    jclbss (JNICALL *GetSuperclbss)
      (JNIEnv *env, jclbss sub);
    jboolebn (JNICALL *IsAssignbbleFrom)
      (JNIEnv *env, jclbss sub, jclbss sup);

    jobject (JNICALL *ToReflectedField)
      (JNIEnv *env, jclbss cls, jfieldID fieldID, jboolebn isStbtic);

    jint (JNICALL *Throw)
      (JNIEnv *env, jthrowbble obj);
    jint (JNICALL *ThrowNew)
      (JNIEnv *env, jclbss clbzz, const chbr *msg);
    jthrowbble (JNICALL *ExceptionOccurred)
      (JNIEnv *env);
    void (JNICALL *ExceptionDescribe)
      (JNIEnv *env);
    void (JNICALL *ExceptionClebr)
      (JNIEnv *env);
    void (JNICALL *FbtblError)
      (JNIEnv *env, const chbr *msg);

    jint (JNICALL *PushLocblFrbme)
      (JNIEnv *env, jint cbpbcity);
    jobject (JNICALL *PopLocblFrbme)
      (JNIEnv *env, jobject result);

    jobject (JNICALL *NewGlobblRef)
      (JNIEnv *env, jobject lobj);
    void (JNICALL *DeleteGlobblRef)
      (JNIEnv *env, jobject gref);
    void (JNICALL *DeleteLocblRef)
      (JNIEnv *env, jobject obj);
    jboolebn (JNICALL *IsSbmeObject)
      (JNIEnv *env, jobject obj1, jobject obj2);
    jobject (JNICALL *NewLocblRef)
      (JNIEnv *env, jobject ref);
    jint (JNICALL *EnsureLocblCbpbcity)
      (JNIEnv *env, jint cbpbcity);

    jobject (JNICALL *AllocObject)
      (JNIEnv *env, jclbss clbzz);
    jobject (JNICALL *NewObject)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jobject (JNICALL *NewObjectV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jobject (JNICALL *NewObjectA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jclbss (JNICALL *GetObjectClbss)
      (JNIEnv *env, jobject obj);
    jboolebn (JNICALL *IsInstbnceOf)
      (JNIEnv *env, jobject obj, jclbss clbzz);

    jmethodID (JNICALL *GetMethodID)
      (JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig);

    jobject (JNICALL *CbllObjectMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jobject (JNICALL *CbllObjectMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jobject (JNICALL *CbllObjectMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue * brgs);

    jboolebn (JNICALL *CbllBoolebnMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jboolebn (JNICALL *CbllBoolebnMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jboolebn (JNICALL *CbllBoolebnMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue * brgs);

    jbyte (JNICALL *CbllByteMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jbyte (JNICALL *CbllByteMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jbyte (JNICALL *CbllByteMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jchbr (JNICALL *CbllChbrMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jchbr (JNICALL *CbllChbrMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jchbr (JNICALL *CbllChbrMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jshort (JNICALL *CbllShortMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jshort (JNICALL *CbllShortMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jshort (JNICALL *CbllShortMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jint (JNICALL *CbllIntMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jint (JNICALL *CbllIntMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jint (JNICALL *CbllIntMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jlong (JNICALL *CbllLongMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jlong (JNICALL *CbllLongMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jlong (JNICALL *CbllLongMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jflobt (JNICALL *CbllFlobtMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jflobt (JNICALL *CbllFlobtMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jflobt (JNICALL *CbllFlobtMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    jdouble (JNICALL *CbllDoubleMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    jdouble (JNICALL *CbllDoubleMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    jdouble (JNICALL *CbllDoubleMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue *brgs);

    void (JNICALL *CbllVoidMethod)
      (JNIEnv *env, jobject obj, jmethodID methodID, ...);
    void (JNICALL *CbllVoidMethodV)
      (JNIEnv *env, jobject obj, jmethodID methodID, vb_list brgs);
    void (JNICALL *CbllVoidMethodA)
      (JNIEnv *env, jobject obj, jmethodID methodID, const jvblue * brgs);

    jobject (JNICALL *CbllNonvirtublObjectMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jobject (JNICALL *CbllNonvirtublObjectMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jobject (JNICALL *CbllNonvirtublObjectMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue * brgs);

    jboolebn (JNICALL *CbllNonvirtublBoolebnMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jboolebn (JNICALL *CbllNonvirtublBoolebnMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jboolebn (JNICALL *CbllNonvirtublBoolebnMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue * brgs);

    jbyte (JNICALL *CbllNonvirtublByteMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jbyte (JNICALL *CbllNonvirtublByteMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jbyte (JNICALL *CbllNonvirtublByteMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jchbr (JNICALL *CbllNonvirtublChbrMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jchbr (JNICALL *CbllNonvirtublChbrMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jchbr (JNICALL *CbllNonvirtublChbrMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jshort (JNICALL *CbllNonvirtublShortMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jshort (JNICALL *CbllNonvirtublShortMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jshort (JNICALL *CbllNonvirtublShortMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jint (JNICALL *CbllNonvirtublIntMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jint (JNICALL *CbllNonvirtublIntMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jint (JNICALL *CbllNonvirtublIntMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jlong (JNICALL *CbllNonvirtublLongMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jlong (JNICALL *CbllNonvirtublLongMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jlong (JNICALL *CbllNonvirtublLongMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jflobt (JNICALL *CbllNonvirtublFlobtMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jflobt (JNICALL *CbllNonvirtublFlobtMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jflobt (JNICALL *CbllNonvirtublFlobtMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    jdouble (JNICALL *CbllNonvirtublDoubleMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    jdouble (JNICALL *CbllNonvirtublDoubleMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    jdouble (JNICALL *CbllNonvirtublDoubleMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue *brgs);

    void (JNICALL *CbllNonvirtublVoidMethod)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID, ...);
    void (JNICALL *CbllNonvirtublVoidMethodV)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       vb_list brgs);
    void (JNICALL *CbllNonvirtublVoidMethodA)
      (JNIEnv *env, jobject obj, jclbss clbzz, jmethodID methodID,
       const jvblue * brgs);

    jfieldID (JNICALL *GetFieldID)
      (JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig);

    jobject (JNICALL *GetObjectField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jboolebn (JNICALL *GetBoolebnField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jbyte (JNICALL *GetByteField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jchbr (JNICALL *GetChbrField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jshort (JNICALL *GetShortField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jint (JNICALL *GetIntField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jlong (JNICALL *GetLongField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jflobt (JNICALL *GetFlobtField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);
    jdouble (JNICALL *GetDoubleField)
      (JNIEnv *env, jobject obj, jfieldID fieldID);

    void (JNICALL *SetObjectField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jobject vbl);
    void (JNICALL *SetBoolebnField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jboolebn vbl);
    void (JNICALL *SetByteField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jbyte vbl);
    void (JNICALL *SetChbrField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jchbr vbl);
    void (JNICALL *SetShortField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jshort vbl);
    void (JNICALL *SetIntField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jint vbl);
    void (JNICALL *SetLongField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jlong vbl);
    void (JNICALL *SetFlobtField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jflobt vbl);
    void (JNICALL *SetDoubleField)
      (JNIEnv *env, jobject obj, jfieldID fieldID, jdouble vbl);

    jmethodID (JNICALL *GetStbticMethodID)
      (JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig);

    jobject (JNICALL *CbllStbticObjectMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jobject (JNICALL *CbllStbticObjectMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jobject (JNICALL *CbllStbticObjectMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jboolebn (JNICALL *CbllStbticBoolebnMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jboolebn (JNICALL *CbllStbticBoolebnMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jboolebn (JNICALL *CbllStbticBoolebnMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jbyte (JNICALL *CbllStbticByteMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jbyte (JNICALL *CbllStbticByteMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jbyte (JNICALL *CbllStbticByteMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jchbr (JNICALL *CbllStbticChbrMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jchbr (JNICALL *CbllStbticChbrMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jchbr (JNICALL *CbllStbticChbrMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jshort (JNICALL *CbllStbticShortMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jshort (JNICALL *CbllStbticShortMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jshort (JNICALL *CbllStbticShortMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jint (JNICALL *CbllStbticIntMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jint (JNICALL *CbllStbticIntMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jint (JNICALL *CbllStbticIntMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jlong (JNICALL *CbllStbticLongMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jlong (JNICALL *CbllStbticLongMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jlong (JNICALL *CbllStbticLongMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jflobt (JNICALL *CbllStbticFlobtMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jflobt (JNICALL *CbllStbticFlobtMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jflobt (JNICALL *CbllStbticFlobtMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    jdouble (JNICALL *CbllStbticDoubleMethod)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, ...);
    jdouble (JNICALL *CbllStbticDoubleMethodV)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, vb_list brgs);
    jdouble (JNICALL *CbllStbticDoubleMethodA)
      (JNIEnv *env, jclbss clbzz, jmethodID methodID, const jvblue *brgs);

    void (JNICALL *CbllStbticVoidMethod)
      (JNIEnv *env, jclbss cls, jmethodID methodID, ...);
    void (JNICALL *CbllStbticVoidMethodV)
      (JNIEnv *env, jclbss cls, jmethodID methodID, vb_list brgs);
    void (JNICALL *CbllStbticVoidMethodA)
      (JNIEnv *env, jclbss cls, jmethodID methodID, const jvblue * brgs);

    jfieldID (JNICALL *GetStbticFieldID)
      (JNIEnv *env, jclbss clbzz, const chbr *nbme, const chbr *sig);
    jobject (JNICALL *GetStbticObjectField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jboolebn (JNICALL *GetStbticBoolebnField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jbyte (JNICALL *GetStbticByteField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jchbr (JNICALL *GetStbticChbrField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jshort (JNICALL *GetStbticShortField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jint (JNICALL *GetStbticIntField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jlong (JNICALL *GetStbticLongField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jflobt (JNICALL *GetStbticFlobtField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);
    jdouble (JNICALL *GetStbticDoubleField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID);

    void (JNICALL *SetStbticObjectField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jobject vblue);
    void (JNICALL *SetStbticBoolebnField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jboolebn vblue);
    void (JNICALL *SetStbticByteField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jbyte vblue);
    void (JNICALL *SetStbticChbrField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jchbr vblue);
    void (JNICALL *SetStbticShortField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jshort vblue);
    void (JNICALL *SetStbticIntField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jint vblue);
    void (JNICALL *SetStbticLongField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jlong vblue);
    void (JNICALL *SetStbticFlobtField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jflobt vblue);
    void (JNICALL *SetStbticDoubleField)
      (JNIEnv *env, jclbss clbzz, jfieldID fieldID, jdouble vblue);

    jstring (JNICALL *NewString)
      (JNIEnv *env, const jchbr *unicode, jsize len);
    jsize (JNICALL *GetStringLength)
      (JNIEnv *env, jstring str);
    const jchbr *(JNICALL *GetStringChbrs)
      (JNIEnv *env, jstring str, jboolebn *isCopy);
    void (JNICALL *RelebseStringChbrs)
      (JNIEnv *env, jstring str, const jchbr *chbrs);

    jstring (JNICALL *NewStringUTF)
      (JNIEnv *env, const chbr *utf);
    jsize (JNICALL *GetStringUTFLength)
      (JNIEnv *env, jstring str);
    const chbr* (JNICALL *GetStringUTFChbrs)
      (JNIEnv *env, jstring str, jboolebn *isCopy);
    void (JNICALL *RelebseStringUTFChbrs)
      (JNIEnv *env, jstring str, const chbr* chbrs);


    jsize (JNICALL *GetArrbyLength)
      (JNIEnv *env, jbrrby brrby);

    jobjectArrby (JNICALL *NewObjectArrby)
      (JNIEnv *env, jsize len, jclbss clbzz, jobject init);
    jobject (JNICALL *GetObjectArrbyElement)
      (JNIEnv *env, jobjectArrby brrby, jsize index);
    void (JNICALL *SetObjectArrbyElement)
      (JNIEnv *env, jobjectArrby brrby, jsize index, jobject vbl);

    jboolebnArrby (JNICALL *NewBoolebnArrby)
      (JNIEnv *env, jsize len);
    jbyteArrby (JNICALL *NewByteArrby)
      (JNIEnv *env, jsize len);
    jchbrArrby (JNICALL *NewChbrArrby)
      (JNIEnv *env, jsize len);
    jshortArrby (JNICALL *NewShortArrby)
      (JNIEnv *env, jsize len);
    jintArrby (JNICALL *NewIntArrby)
      (JNIEnv *env, jsize len);
    jlongArrby (JNICALL *NewLongArrby)
      (JNIEnv *env, jsize len);
    jflobtArrby (JNICALL *NewFlobtArrby)
      (JNIEnv *env, jsize len);
    jdoubleArrby (JNICALL *NewDoubleArrby)
      (JNIEnv *env, jsize len);

    jboolebn * (JNICALL *GetBoolebnArrbyElements)
      (JNIEnv *env, jboolebnArrby brrby, jboolebn *isCopy);
    jbyte * (JNICALL *GetByteArrbyElements)
      (JNIEnv *env, jbyteArrby brrby, jboolebn *isCopy);
    jchbr * (JNICALL *GetChbrArrbyElements)
      (JNIEnv *env, jchbrArrby brrby, jboolebn *isCopy);
    jshort * (JNICALL *GetShortArrbyElements)
      (JNIEnv *env, jshortArrby brrby, jboolebn *isCopy);
    jint * (JNICALL *GetIntArrbyElements)
      (JNIEnv *env, jintArrby brrby, jboolebn *isCopy);
    jlong * (JNICALL *GetLongArrbyElements)
      (JNIEnv *env, jlongArrby brrby, jboolebn *isCopy);
    jflobt * (JNICALL *GetFlobtArrbyElements)
      (JNIEnv *env, jflobtArrby brrby, jboolebn *isCopy);
    jdouble * (JNICALL *GetDoubleArrbyElements)
      (JNIEnv *env, jdoubleArrby brrby, jboolebn *isCopy);

    void (JNICALL *RelebseBoolebnArrbyElements)
      (JNIEnv *env, jboolebnArrby brrby, jboolebn *elems, jint mode);
    void (JNICALL *RelebseByteArrbyElements)
      (JNIEnv *env, jbyteArrby brrby, jbyte *elems, jint mode);
    void (JNICALL *RelebseChbrArrbyElements)
      (JNIEnv *env, jchbrArrby brrby, jchbr *elems, jint mode);
    void (JNICALL *RelebseShortArrbyElements)
      (JNIEnv *env, jshortArrby brrby, jshort *elems, jint mode);
    void (JNICALL *RelebseIntArrbyElements)
      (JNIEnv *env, jintArrby brrby, jint *elems, jint mode);
    void (JNICALL *RelebseLongArrbyElements)
      (JNIEnv *env, jlongArrby brrby, jlong *elems, jint mode);
    void (JNICALL *RelebseFlobtArrbyElements)
      (JNIEnv *env, jflobtArrby brrby, jflobt *elems, jint mode);
    void (JNICALL *RelebseDoubleArrbyElements)
      (JNIEnv *env, jdoubleArrby brrby, jdouble *elems, jint mode);

    void (JNICALL *GetBoolebnArrbyRegion)
      (JNIEnv *env, jboolebnArrby brrby, jsize stbrt, jsize l, jboolebn *buf);
    void (JNICALL *GetByteArrbyRegion)
      (JNIEnv *env, jbyteArrby brrby, jsize stbrt, jsize len, jbyte *buf);
    void (JNICALL *GetChbrArrbyRegion)
      (JNIEnv *env, jchbrArrby brrby, jsize stbrt, jsize len, jchbr *buf);
    void (JNICALL *GetShortArrbyRegion)
      (JNIEnv *env, jshortArrby brrby, jsize stbrt, jsize len, jshort *buf);
    void (JNICALL *GetIntArrbyRegion)
      (JNIEnv *env, jintArrby brrby, jsize stbrt, jsize len, jint *buf);
    void (JNICALL *GetLongArrbyRegion)
      (JNIEnv *env, jlongArrby brrby, jsize stbrt, jsize len, jlong *buf);
    void (JNICALL *GetFlobtArrbyRegion)
      (JNIEnv *env, jflobtArrby brrby, jsize stbrt, jsize len, jflobt *buf);
    void (JNICALL *GetDoubleArrbyRegion)
      (JNIEnv *env, jdoubleArrby brrby, jsize stbrt, jsize len, jdouble *buf);

    void (JNICALL *SetBoolebnArrbyRegion)
      (JNIEnv *env, jboolebnArrby brrby, jsize stbrt, jsize l, const jboolebn *buf);
    void (JNICALL *SetByteArrbyRegion)
      (JNIEnv *env, jbyteArrby brrby, jsize stbrt, jsize len, const jbyte *buf);
    void (JNICALL *SetChbrArrbyRegion)
      (JNIEnv *env, jchbrArrby brrby, jsize stbrt, jsize len, const jchbr *buf);
    void (JNICALL *SetShortArrbyRegion)
      (JNIEnv *env, jshortArrby brrby, jsize stbrt, jsize len, const jshort *buf);
    void (JNICALL *SetIntArrbyRegion)
      (JNIEnv *env, jintArrby brrby, jsize stbrt, jsize len, const jint *buf);
    void (JNICALL *SetLongArrbyRegion)
      (JNIEnv *env, jlongArrby brrby, jsize stbrt, jsize len, const jlong *buf);
    void (JNICALL *SetFlobtArrbyRegion)
      (JNIEnv *env, jflobtArrby brrby, jsize stbrt, jsize len, const jflobt *buf);
    void (JNICALL *SetDoubleArrbyRegion)
      (JNIEnv *env, jdoubleArrby brrby, jsize stbrt, jsize len, const jdouble *buf);

    jint (JNICALL *RegisterNbtives)
      (JNIEnv *env, jclbss clbzz, const JNINbtiveMethod *methods,
       jint nMethods);
    jint (JNICALL *UnregisterNbtives)
      (JNIEnv *env, jclbss clbzz);

    jint (JNICALL *MonitorEnter)
      (JNIEnv *env, jobject obj);
    jint (JNICALL *MonitorExit)
      (JNIEnv *env, jobject obj);

    jint (JNICALL *GetJbvbVM)
      (JNIEnv *env, JbvbVM **vm);

    void (JNICALL *GetStringRegion)
      (JNIEnv *env, jstring str, jsize stbrt, jsize len, jchbr *buf);
    void (JNICALL *GetStringUTFRegion)
      (JNIEnv *env, jstring str, jsize stbrt, jsize len, chbr *buf);

    void * (JNICALL *GetPrimitiveArrbyCriticbl)
      (JNIEnv *env, jbrrby brrby, jboolebn *isCopy);
    void (JNICALL *RelebsePrimitiveArrbyCriticbl)
      (JNIEnv *env, jbrrby brrby, void *cbrrby, jint mode);

    const jchbr * (JNICALL *GetStringCriticbl)
      (JNIEnv *env, jstring string, jboolebn *isCopy);
    void (JNICALL *RelebseStringCriticbl)
      (JNIEnv *env, jstring string, const jchbr *cstring);

    jwebk (JNICALL *NewWebkGlobblRef)
       (JNIEnv *env, jobject obj);
    void (JNICALL *DeleteWebkGlobblRef)
       (JNIEnv *env, jwebk ref);

    jboolebn (JNICALL *ExceptionCheck)
       (JNIEnv *env);

    jobject (JNICALL *NewDirectByteBuffer)
       (JNIEnv* env, void* bddress, jlong cbpbcity);
    void* (JNICALL *GetDirectBufferAddress)
       (JNIEnv* env, jobject buf);
    jlong (JNICALL *GetDirectBufferCbpbcity)
       (JNIEnv* env, jobject buf);

    /* New JNI 1.6 Febtures */

    jobjectRefType (JNICALL *GetObjectRefType)
        (JNIEnv* env, jobject obj);
};

/*
 * We use inlined functions for C++ so thbt progrbmmers cbn write:
 *
 *    env->FindClbss("jbvb/lbng/String")
 *
 * in C++ rbther thbn:
 *
 *    (*env)->FindClbss(env, "jbvb/lbng/String")
 *
 * in C.
 */

struct JNIEnv_ {
    const struct JNINbtiveInterfbce_ *functions;
#ifdef __cplusplus

    jint GetVersion() {
        return functions->GetVersion(this);
    }
    jclbss DefineClbss(const chbr *nbme, jobject lobder, const jbyte *buf,
                       jsize len) {
        return functions->DefineClbss(this, nbme, lobder, buf, len);
    }
    jclbss FindClbss(const chbr *nbme) {
        return functions->FindClbss(this, nbme);
    }
    jmethodID FromReflectedMethod(jobject method) {
        return functions->FromReflectedMethod(this,method);
    }
    jfieldID FromReflectedField(jobject field) {
        return functions->FromReflectedField(this,field);
    }

    jobject ToReflectedMethod(jclbss cls, jmethodID methodID, jboolebn isStbtic) {
        return functions->ToReflectedMethod(this, cls, methodID, isStbtic);
    }

    jclbss GetSuperclbss(jclbss sub) {
        return functions->GetSuperclbss(this, sub);
    }
    jboolebn IsAssignbbleFrom(jclbss sub, jclbss sup) {
        return functions->IsAssignbbleFrom(this, sub, sup);
    }

    jobject ToReflectedField(jclbss cls, jfieldID fieldID, jboolebn isStbtic) {
        return functions->ToReflectedField(this,cls,fieldID,isStbtic);
    }

    jint Throw(jthrowbble obj) {
        return functions->Throw(this, obj);
    }
    jint ThrowNew(jclbss clbzz, const chbr *msg) {
        return functions->ThrowNew(this, clbzz, msg);
    }
    jthrowbble ExceptionOccurred() {
        return functions->ExceptionOccurred(this);
    }
    void ExceptionDescribe() {
        functions->ExceptionDescribe(this);
    }
    void ExceptionClebr() {
        functions->ExceptionClebr(this);
    }
    void FbtblError(const chbr *msg) {
        functions->FbtblError(this, msg);
    }

    jint PushLocblFrbme(jint cbpbcity) {
        return functions->PushLocblFrbme(this,cbpbcity);
    }
    jobject PopLocblFrbme(jobject result) {
        return functions->PopLocblFrbme(this,result);
    }

    jobject NewGlobblRef(jobject lobj) {
        return functions->NewGlobblRef(this,lobj);
    }
    void DeleteGlobblRef(jobject gref) {
        functions->DeleteGlobblRef(this,gref);
    }
    void DeleteLocblRef(jobject obj) {
        functions->DeleteLocblRef(this, obj);
    }

    jboolebn IsSbmeObject(jobject obj1, jobject obj2) {
        return functions->IsSbmeObject(this,obj1,obj2);
    }

    jobject NewLocblRef(jobject ref) {
        return functions->NewLocblRef(this,ref);
    }
    jint EnsureLocblCbpbcity(jint cbpbcity) {
        return functions->EnsureLocblCbpbcity(this,cbpbcity);
    }

    jobject AllocObject(jclbss clbzz) {
        return functions->AllocObject(this,clbzz);
    }
    jobject NewObject(jclbss clbzz, jmethodID methodID, ...) {
        vb_list brgs;
        jobject result;
        vb_stbrt(brgs, methodID);
        result = functions->NewObjectV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jobject NewObjectV(jclbss clbzz, jmethodID methodID,
                       vb_list brgs) {
        return functions->NewObjectV(this,clbzz,methodID,brgs);
    }
    jobject NewObjectA(jclbss clbzz, jmethodID methodID,
                       const jvblue *brgs) {
        return functions->NewObjectA(this,clbzz,methodID,brgs);
    }

    jclbss GetObjectClbss(jobject obj) {
        return functions->GetObjectClbss(this,obj);
    }
    jboolebn IsInstbnceOf(jobject obj, jclbss clbzz) {
        return functions->IsInstbnceOf(this,obj,clbzz);
    }

    jmethodID GetMethodID(jclbss clbzz, const chbr *nbme,
                          const chbr *sig) {
        return functions->GetMethodID(this,clbzz,nbme,sig);
    }

    jobject CbllObjectMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jobject result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllObjectMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jobject CbllObjectMethodV(jobject obj, jmethodID methodID,
                        vb_list brgs) {
        return functions->CbllObjectMethodV(this,obj,methodID,brgs);
    }
    jobject CbllObjectMethodA(jobject obj, jmethodID methodID,
                        const jvblue * brgs) {
        return functions->CbllObjectMethodA(this,obj,methodID,brgs);
    }

    jboolebn CbllBoolebnMethod(jobject obj,
                               jmethodID methodID, ...) {
        vb_list brgs;
        jboolebn result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllBoolebnMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jboolebn CbllBoolebnMethodV(jobject obj, jmethodID methodID,
                                vb_list brgs) {
        return functions->CbllBoolebnMethodV(this,obj,methodID,brgs);
    }
    jboolebn CbllBoolebnMethodA(jobject obj, jmethodID methodID,
                                const jvblue * brgs) {
        return functions->CbllBoolebnMethodA(this,obj,methodID, brgs);
    }

    jbyte CbllByteMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jbyte result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllByteMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jbyte CbllByteMethodV(jobject obj, jmethodID methodID,
                          vb_list brgs) {
        return functions->CbllByteMethodV(this,obj,methodID,brgs);
    }
    jbyte CbllByteMethodA(jobject obj, jmethodID methodID,
                          const jvblue * brgs) {
        return functions->CbllByteMethodA(this,obj,methodID,brgs);
    }

    jchbr CbllChbrMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jchbr result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllChbrMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jchbr CbllChbrMethodV(jobject obj, jmethodID methodID,
                          vb_list brgs) {
        return functions->CbllChbrMethodV(this,obj,methodID,brgs);
    }
    jchbr CbllChbrMethodA(jobject obj, jmethodID methodID,
                          const jvblue * brgs) {
        return functions->CbllChbrMethodA(this,obj,methodID,brgs);
    }

    jshort CbllShortMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jshort result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllShortMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jshort CbllShortMethodV(jobject obj, jmethodID methodID,
                            vb_list brgs) {
        return functions->CbllShortMethodV(this,obj,methodID,brgs);
    }
    jshort CbllShortMethodA(jobject obj, jmethodID methodID,
                            const jvblue * brgs) {
        return functions->CbllShortMethodA(this,obj,methodID,brgs);
    }

    jint CbllIntMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jint result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllIntMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jint CbllIntMethodV(jobject obj, jmethodID methodID,
                        vb_list brgs) {
        return functions->CbllIntMethodV(this,obj,methodID,brgs);
    }
    jint CbllIntMethodA(jobject obj, jmethodID methodID,
                        const jvblue * brgs) {
        return functions->CbllIntMethodA(this,obj,methodID,brgs);
    }

    jlong CbllLongMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jlong result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllLongMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jlong CbllLongMethodV(jobject obj, jmethodID methodID,
                          vb_list brgs) {
        return functions->CbllLongMethodV(this,obj,methodID,brgs);
    }
    jlong CbllLongMethodA(jobject obj, jmethodID methodID,
                          const jvblue * brgs) {
        return functions->CbllLongMethodA(this,obj,methodID,brgs);
    }

    jflobt CbllFlobtMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jflobt result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllFlobtMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jflobt CbllFlobtMethodV(jobject obj, jmethodID methodID,
                            vb_list brgs) {
        return functions->CbllFlobtMethodV(this,obj,methodID,brgs);
    }
    jflobt CbllFlobtMethodA(jobject obj, jmethodID methodID,
                            const jvblue * brgs) {
        return functions->CbllFlobtMethodA(this,obj,methodID,brgs);
    }

    jdouble CbllDoubleMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        jdouble result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllDoubleMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jdouble CbllDoubleMethodV(jobject obj, jmethodID methodID,
                        vb_list brgs) {
        return functions->CbllDoubleMethodV(this,obj,methodID,brgs);
    }
    jdouble CbllDoubleMethodA(jobject obj, jmethodID methodID,
                        const jvblue * brgs) {
        return functions->CbllDoubleMethodA(this,obj,methodID,brgs);
    }

    void CbllVoidMethod(jobject obj, jmethodID methodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,methodID);
        functions->CbllVoidMethodV(this,obj,methodID,brgs);
        vb_end(brgs);
    }
    void CbllVoidMethodV(jobject obj, jmethodID methodID,
                         vb_list brgs) {
        functions->CbllVoidMethodV(this,obj,methodID,brgs);
    }
    void CbllVoidMethodA(jobject obj, jmethodID methodID,
                         const jvblue * brgs) {
        functions->CbllVoidMethodA(this,obj,methodID,brgs);
    }

    jobject CbllNonvirtublObjectMethod(jobject obj, jclbss clbzz,
                                       jmethodID methodID, ...) {
        vb_list brgs;
        jobject result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublObjectMethodV(this,obj,clbzz,
                                                        methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jobject CbllNonvirtublObjectMethodV(jobject obj, jclbss clbzz,
                                        jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublObjectMethodV(this,obj,clbzz,
                                                      methodID,brgs);
    }
    jobject CbllNonvirtublObjectMethodA(jobject obj, jclbss clbzz,
                                        jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublObjectMethodA(this,obj,clbzz,
                                                      methodID,brgs);
    }

    jboolebn CbllNonvirtublBoolebnMethod(jobject obj, jclbss clbzz,
                                         jmethodID methodID, ...) {
        vb_list brgs;
        jboolebn result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublBoolebnMethodV(this,obj,clbzz,
                                                         methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jboolebn CbllNonvirtublBoolebnMethodV(jobject obj, jclbss clbzz,
                                          jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublBoolebnMethodV(this,obj,clbzz,
                                                       methodID,brgs);
    }
    jboolebn CbllNonvirtublBoolebnMethodA(jobject obj, jclbss clbzz,
                                          jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublBoolebnMethodA(this,obj,clbzz,
                                                       methodID, brgs);
    }

    jbyte CbllNonvirtublByteMethod(jobject obj, jclbss clbzz,
                                   jmethodID methodID, ...) {
        vb_list brgs;
        jbyte result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublByteMethodV(this,obj,clbzz,
                                                      methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jbyte CbllNonvirtublByteMethodV(jobject obj, jclbss clbzz,
                                    jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublByteMethodV(this,obj,clbzz,
                                                    methodID,brgs);
    }
    jbyte CbllNonvirtublByteMethodA(jobject obj, jclbss clbzz,
                                    jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublByteMethodA(this,obj,clbzz,
                                                    methodID,brgs);
    }

    jchbr CbllNonvirtublChbrMethod(jobject obj, jclbss clbzz,
                                   jmethodID methodID, ...) {
        vb_list brgs;
        jchbr result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublChbrMethodV(this,obj,clbzz,
                                                      methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jchbr CbllNonvirtublChbrMethodV(jobject obj, jclbss clbzz,
                                    jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublChbrMethodV(this,obj,clbzz,
                                                    methodID,brgs);
    }
    jchbr CbllNonvirtublChbrMethodA(jobject obj, jclbss clbzz,
                                    jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublChbrMethodA(this,obj,clbzz,
                                                    methodID,brgs);
    }

    jshort CbllNonvirtublShortMethod(jobject obj, jclbss clbzz,
                                     jmethodID methodID, ...) {
        vb_list brgs;
        jshort result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublShortMethodV(this,obj,clbzz,
                                                       methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jshort CbllNonvirtublShortMethodV(jobject obj, jclbss clbzz,
                                      jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublShortMethodV(this,obj,clbzz,
                                                     methodID,brgs);
    }
    jshort CbllNonvirtublShortMethodA(jobject obj, jclbss clbzz,
                                      jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublShortMethodA(this,obj,clbzz,
                                                     methodID,brgs);
    }

    jint CbllNonvirtublIntMethod(jobject obj, jclbss clbzz,
                                 jmethodID methodID, ...) {
        vb_list brgs;
        jint result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublIntMethodV(this,obj,clbzz,
                                                     methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jint CbllNonvirtublIntMethodV(jobject obj, jclbss clbzz,
                                  jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublIntMethodV(this,obj,clbzz,
                                                   methodID,brgs);
    }
    jint CbllNonvirtublIntMethodA(jobject obj, jclbss clbzz,
                                  jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublIntMethodA(this,obj,clbzz,
                                                   methodID,brgs);
    }

    jlong CbllNonvirtublLongMethod(jobject obj, jclbss clbzz,
                                   jmethodID methodID, ...) {
        vb_list brgs;
        jlong result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublLongMethodV(this,obj,clbzz,
                                                      methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jlong CbllNonvirtublLongMethodV(jobject obj, jclbss clbzz,
                                    jmethodID methodID, vb_list brgs) {
        return functions->CbllNonvirtublLongMethodV(this,obj,clbzz,
                                                    methodID,brgs);
    }
    jlong CbllNonvirtublLongMethodA(jobject obj, jclbss clbzz,
                                    jmethodID methodID, const jvblue * brgs) {
        return functions->CbllNonvirtublLongMethodA(this,obj,clbzz,
                                                    methodID,brgs);
    }

    jflobt CbllNonvirtublFlobtMethod(jobject obj, jclbss clbzz,
                                     jmethodID methodID, ...) {
        vb_list brgs;
        jflobt result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublFlobtMethodV(this,obj,clbzz,
                                                       methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jflobt CbllNonvirtublFlobtMethodV(jobject obj, jclbss clbzz,
                                      jmethodID methodID,
                                      vb_list brgs) {
        return functions->CbllNonvirtublFlobtMethodV(this,obj,clbzz,
                                                     methodID,brgs);
    }
    jflobt CbllNonvirtublFlobtMethodA(jobject obj, jclbss clbzz,
                                      jmethodID methodID,
                                      const jvblue * brgs) {
        return functions->CbllNonvirtublFlobtMethodA(this,obj,clbzz,
                                                     methodID,brgs);
    }

    jdouble CbllNonvirtublDoubleMethod(jobject obj, jclbss clbzz,
                                       jmethodID methodID, ...) {
        vb_list brgs;
        jdouble result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllNonvirtublDoubleMethodV(this,obj,clbzz,
                                                        methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jdouble CbllNonvirtublDoubleMethodV(jobject obj, jclbss clbzz,
                                        jmethodID methodID,
                                        vb_list brgs) {
        return functions->CbllNonvirtublDoubleMethodV(this,obj,clbzz,
                                                      methodID,brgs);
    }
    jdouble CbllNonvirtublDoubleMethodA(jobject obj, jclbss clbzz,
                                        jmethodID methodID,
                                        const jvblue * brgs) {
        return functions->CbllNonvirtublDoubleMethodA(this,obj,clbzz,
                                                      methodID,brgs);
    }

    void CbllNonvirtublVoidMethod(jobject obj, jclbss clbzz,
                                  jmethodID methodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,methodID);
        functions->CbllNonvirtublVoidMethodV(this,obj,clbzz,methodID,brgs);
        vb_end(brgs);
    }
    void CbllNonvirtublVoidMethodV(jobject obj, jclbss clbzz,
                                   jmethodID methodID,
                                   vb_list brgs) {
        functions->CbllNonvirtublVoidMethodV(this,obj,clbzz,methodID,brgs);
    }
    void CbllNonvirtublVoidMethodA(jobject obj, jclbss clbzz,
                                   jmethodID methodID,
                                   const jvblue * brgs) {
        functions->CbllNonvirtublVoidMethodA(this,obj,clbzz,methodID,brgs);
    }

    jfieldID GetFieldID(jclbss clbzz, const chbr *nbme,
                        const chbr *sig) {
        return functions->GetFieldID(this,clbzz,nbme,sig);
    }

    jobject GetObjectField(jobject obj, jfieldID fieldID) {
        return functions->GetObjectField(this,obj,fieldID);
    }
    jboolebn GetBoolebnField(jobject obj, jfieldID fieldID) {
        return functions->GetBoolebnField(this,obj,fieldID);
    }
    jbyte GetByteField(jobject obj, jfieldID fieldID) {
        return functions->GetByteField(this,obj,fieldID);
    }
    jchbr GetChbrField(jobject obj, jfieldID fieldID) {
        return functions->GetChbrField(this,obj,fieldID);
    }
    jshort GetShortField(jobject obj, jfieldID fieldID) {
        return functions->GetShortField(this,obj,fieldID);
    }
    jint GetIntField(jobject obj, jfieldID fieldID) {
        return functions->GetIntField(this,obj,fieldID);
    }
    jlong GetLongField(jobject obj, jfieldID fieldID) {
        return functions->GetLongField(this,obj,fieldID);
    }
    jflobt GetFlobtField(jobject obj, jfieldID fieldID) {
        return functions->GetFlobtField(this,obj,fieldID);
    }
    jdouble GetDoubleField(jobject obj, jfieldID fieldID) {
        return functions->GetDoubleField(this,obj,fieldID);
    }

    void SetObjectField(jobject obj, jfieldID fieldID, jobject vbl) {
        functions->SetObjectField(this,obj,fieldID,vbl);
    }
    void SetBoolebnField(jobject obj, jfieldID fieldID,
                         jboolebn vbl) {
        functions->SetBoolebnField(this,obj,fieldID,vbl);
    }
    void SetByteField(jobject obj, jfieldID fieldID,
                      jbyte vbl) {
        functions->SetByteField(this,obj,fieldID,vbl);
    }
    void SetChbrField(jobject obj, jfieldID fieldID,
                      jchbr vbl) {
        functions->SetChbrField(this,obj,fieldID,vbl);
    }
    void SetShortField(jobject obj, jfieldID fieldID,
                       jshort vbl) {
        functions->SetShortField(this,obj,fieldID,vbl);
    }
    void SetIntField(jobject obj, jfieldID fieldID,
                     jint vbl) {
        functions->SetIntField(this,obj,fieldID,vbl);
    }
    void SetLongField(jobject obj, jfieldID fieldID,
                      jlong vbl) {
        functions->SetLongField(this,obj,fieldID,vbl);
    }
    void SetFlobtField(jobject obj, jfieldID fieldID,
                       jflobt vbl) {
        functions->SetFlobtField(this,obj,fieldID,vbl);
    }
    void SetDoubleField(jobject obj, jfieldID fieldID,
                        jdouble vbl) {
        functions->SetDoubleField(this,obj,fieldID,vbl);
    }

    jmethodID GetStbticMethodID(jclbss clbzz, const chbr *nbme,
                                const chbr *sig) {
        return functions->GetStbticMethodID(this,clbzz,nbme,sig);
    }

    jobject CbllStbticObjectMethod(jclbss clbzz, jmethodID methodID,
                             ...) {
        vb_list brgs;
        jobject result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticObjectMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jobject CbllStbticObjectMethodV(jclbss clbzz, jmethodID methodID,
                              vb_list brgs) {
        return functions->CbllStbticObjectMethodV(this,clbzz,methodID,brgs);
    }
    jobject CbllStbticObjectMethodA(jclbss clbzz, jmethodID methodID,
                              const jvblue *brgs) {
        return functions->CbllStbticObjectMethodA(this,clbzz,methodID,brgs);
    }

    jboolebn CbllStbticBoolebnMethod(jclbss clbzz,
                                     jmethodID methodID, ...) {
        vb_list brgs;
        jboolebn result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticBoolebnMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jboolebn CbllStbticBoolebnMethodV(jclbss clbzz,
                                      jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticBoolebnMethodV(this,clbzz,methodID,brgs);
    }
    jboolebn CbllStbticBoolebnMethodA(jclbss clbzz,
                                      jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticBoolebnMethodA(this,clbzz,methodID,brgs);
    }

    jbyte CbllStbticByteMethod(jclbss clbzz,
                               jmethodID methodID, ...) {
        vb_list brgs;
        jbyte result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticByteMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jbyte CbllStbticByteMethodV(jclbss clbzz,
                                jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticByteMethodV(this,clbzz,methodID,brgs);
    }
    jbyte CbllStbticByteMethodA(jclbss clbzz,
                                jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticByteMethodA(this,clbzz,methodID,brgs);
    }

    jchbr CbllStbticChbrMethod(jclbss clbzz,
                               jmethodID methodID, ...) {
        vb_list brgs;
        jchbr result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticChbrMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jchbr CbllStbticChbrMethodV(jclbss clbzz,
                                jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticChbrMethodV(this,clbzz,methodID,brgs);
    }
    jchbr CbllStbticChbrMethodA(jclbss clbzz,
                                jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticChbrMethodA(this,clbzz,methodID,brgs);
    }

    jshort CbllStbticShortMethod(jclbss clbzz,
                                 jmethodID methodID, ...) {
        vb_list brgs;
        jshort result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticShortMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jshort CbllStbticShortMethodV(jclbss clbzz,
                                  jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticShortMethodV(this,clbzz,methodID,brgs);
    }
    jshort CbllStbticShortMethodA(jclbss clbzz,
                                  jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticShortMethodA(this,clbzz,methodID,brgs);
    }

    jint CbllStbticIntMethod(jclbss clbzz,
                             jmethodID methodID, ...) {
        vb_list brgs;
        jint result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticIntMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jint CbllStbticIntMethodV(jclbss clbzz,
                              jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticIntMethodV(this,clbzz,methodID,brgs);
    }
    jint CbllStbticIntMethodA(jclbss clbzz,
                              jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticIntMethodA(this,clbzz,methodID,brgs);
    }

    jlong CbllStbticLongMethod(jclbss clbzz,
                               jmethodID methodID, ...) {
        vb_list brgs;
        jlong result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticLongMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jlong CbllStbticLongMethodV(jclbss clbzz,
                                jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticLongMethodV(this,clbzz,methodID,brgs);
    }
    jlong CbllStbticLongMethodA(jclbss clbzz,
                                jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticLongMethodA(this,clbzz,methodID,brgs);
    }

    jflobt CbllStbticFlobtMethod(jclbss clbzz,
                                 jmethodID methodID, ...) {
        vb_list brgs;
        jflobt result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticFlobtMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jflobt CbllStbticFlobtMethodV(jclbss clbzz,
                                  jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticFlobtMethodV(this,clbzz,methodID,brgs);
    }
    jflobt CbllStbticFlobtMethodA(jclbss clbzz,
                                  jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticFlobtMethodA(this,clbzz,methodID,brgs);
    }

    jdouble CbllStbticDoubleMethod(jclbss clbzz,
                                   jmethodID methodID, ...) {
        vb_list brgs;
        jdouble result;
        vb_stbrt(brgs,methodID);
        result = functions->CbllStbticDoubleMethodV(this,clbzz,methodID,brgs);
        vb_end(brgs);
        return result;
    }
    jdouble CbllStbticDoubleMethodV(jclbss clbzz,
                                    jmethodID methodID, vb_list brgs) {
        return functions->CbllStbticDoubleMethodV(this,clbzz,methodID,brgs);
    }
    jdouble CbllStbticDoubleMethodA(jclbss clbzz,
                                    jmethodID methodID, const jvblue *brgs) {
        return functions->CbllStbticDoubleMethodA(this,clbzz,methodID,brgs);
    }

    void CbllStbticVoidMethod(jclbss cls, jmethodID methodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,methodID);
        functions->CbllStbticVoidMethodV(this,cls,methodID,brgs);
        vb_end(brgs);
    }
    void CbllStbticVoidMethodV(jclbss cls, jmethodID methodID,
                               vb_list brgs) {
        functions->CbllStbticVoidMethodV(this,cls,methodID,brgs);
    }
    void CbllStbticVoidMethodA(jclbss cls, jmethodID methodID,
                               const jvblue * brgs) {
        functions->CbllStbticVoidMethodA(this,cls,methodID,brgs);
    }

    jfieldID GetStbticFieldID(jclbss clbzz, const chbr *nbme,
                              const chbr *sig) {
        return functions->GetStbticFieldID(this,clbzz,nbme,sig);
    }
    jobject GetStbticObjectField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticObjectField(this,clbzz,fieldID);
    }
    jboolebn GetStbticBoolebnField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticBoolebnField(this,clbzz,fieldID);
    }
    jbyte GetStbticByteField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticByteField(this,clbzz,fieldID);
    }
    jchbr GetStbticChbrField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticChbrField(this,clbzz,fieldID);
    }
    jshort GetStbticShortField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticShortField(this,clbzz,fieldID);
    }
    jint GetStbticIntField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticIntField(this,clbzz,fieldID);
    }
    jlong GetStbticLongField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticLongField(this,clbzz,fieldID);
    }
    jflobt GetStbticFlobtField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticFlobtField(this,clbzz,fieldID);
    }
    jdouble GetStbticDoubleField(jclbss clbzz, jfieldID fieldID) {
        return functions->GetStbticDoubleField(this,clbzz,fieldID);
    }

    void SetStbticObjectField(jclbss clbzz, jfieldID fieldID,
                        jobject vblue) {
      functions->SetStbticObjectField(this,clbzz,fieldID,vblue);
    }
    void SetStbticBoolebnField(jclbss clbzz, jfieldID fieldID,
                        jboolebn vblue) {
      functions->SetStbticBoolebnField(this,clbzz,fieldID,vblue);
    }
    void SetStbticByteField(jclbss clbzz, jfieldID fieldID,
                        jbyte vblue) {
      functions->SetStbticByteField(this,clbzz,fieldID,vblue);
    }
    void SetStbticChbrField(jclbss clbzz, jfieldID fieldID,
                        jchbr vblue) {
      functions->SetStbticChbrField(this,clbzz,fieldID,vblue);
    }
    void SetStbticShortField(jclbss clbzz, jfieldID fieldID,
                        jshort vblue) {
      functions->SetStbticShortField(this,clbzz,fieldID,vblue);
    }
    void SetStbticIntField(jclbss clbzz, jfieldID fieldID,
                        jint vblue) {
      functions->SetStbticIntField(this,clbzz,fieldID,vblue);
    }
    void SetStbticLongField(jclbss clbzz, jfieldID fieldID,
                        jlong vblue) {
      functions->SetStbticLongField(this,clbzz,fieldID,vblue);
    }
    void SetStbticFlobtField(jclbss clbzz, jfieldID fieldID,
                        jflobt vblue) {
      functions->SetStbticFlobtField(this,clbzz,fieldID,vblue);
    }
    void SetStbticDoubleField(jclbss clbzz, jfieldID fieldID,
                        jdouble vblue) {
      functions->SetStbticDoubleField(this,clbzz,fieldID,vblue);
    }

    jstring NewString(const jchbr *unicode, jsize len) {
        return functions->NewString(this,unicode,len);
    }
    jsize GetStringLength(jstring str) {
        return functions->GetStringLength(this,str);
    }
    const jchbr *GetStringChbrs(jstring str, jboolebn *isCopy) {
        return functions->GetStringChbrs(this,str,isCopy);
    }
    void RelebseStringChbrs(jstring str, const jchbr *chbrs) {
        functions->RelebseStringChbrs(this,str,chbrs);
    }

    jstring NewStringUTF(const chbr *utf) {
        return functions->NewStringUTF(this,utf);
    }
    jsize GetStringUTFLength(jstring str) {
        return functions->GetStringUTFLength(this,str);
    }
    const chbr* GetStringUTFChbrs(jstring str, jboolebn *isCopy) {
        return functions->GetStringUTFChbrs(this,str,isCopy);
    }
    void RelebseStringUTFChbrs(jstring str, const chbr* chbrs) {
        functions->RelebseStringUTFChbrs(this,str,chbrs);
    }

    jsize GetArrbyLength(jbrrby brrby) {
        return functions->GetArrbyLength(this,brrby);
    }

    jobjectArrby NewObjectArrby(jsize len, jclbss clbzz,
                                jobject init) {
        return functions->NewObjectArrby(this,len,clbzz,init);
    }
    jobject GetObjectArrbyElement(jobjectArrby brrby, jsize index) {
        return functions->GetObjectArrbyElement(this,brrby,index);
    }
    void SetObjectArrbyElement(jobjectArrby brrby, jsize index,
                               jobject vbl) {
        functions->SetObjectArrbyElement(this,brrby,index,vbl);
    }

    jboolebnArrby NewBoolebnArrby(jsize len) {
        return functions->NewBoolebnArrby(this,len);
    }
    jbyteArrby NewByteArrby(jsize len) {
        return functions->NewByteArrby(this,len);
    }
    jchbrArrby NewChbrArrby(jsize len) {
        return functions->NewChbrArrby(this,len);
    }
    jshortArrby NewShortArrby(jsize len) {
        return functions->NewShortArrby(this,len);
    }
    jintArrby NewIntArrby(jsize len) {
        return functions->NewIntArrby(this,len);
    }
    jlongArrby NewLongArrby(jsize len) {
        return functions->NewLongArrby(this,len);
    }
    jflobtArrby NewFlobtArrby(jsize len) {
        return functions->NewFlobtArrby(this,len);
    }
    jdoubleArrby NewDoubleArrby(jsize len) {
        return functions->NewDoubleArrby(this,len);
    }

    jboolebn * GetBoolebnArrbyElements(jboolebnArrby brrby, jboolebn *isCopy) {
        return functions->GetBoolebnArrbyElements(this,brrby,isCopy);
    }
    jbyte * GetByteArrbyElements(jbyteArrby brrby, jboolebn *isCopy) {
        return functions->GetByteArrbyElements(this,brrby,isCopy);
    }
    jchbr * GetChbrArrbyElements(jchbrArrby brrby, jboolebn *isCopy) {
        return functions->GetChbrArrbyElements(this,brrby,isCopy);
    }
    jshort * GetShortArrbyElements(jshortArrby brrby, jboolebn *isCopy) {
        return functions->GetShortArrbyElements(this,brrby,isCopy);
    }
    jint * GetIntArrbyElements(jintArrby brrby, jboolebn *isCopy) {
        return functions->GetIntArrbyElements(this,brrby,isCopy);
    }
    jlong * GetLongArrbyElements(jlongArrby brrby, jboolebn *isCopy) {
        return functions->GetLongArrbyElements(this,brrby,isCopy);
    }
    jflobt * GetFlobtArrbyElements(jflobtArrby brrby, jboolebn *isCopy) {
        return functions->GetFlobtArrbyElements(this,brrby,isCopy);
    }
    jdouble * GetDoubleArrbyElements(jdoubleArrby brrby, jboolebn *isCopy) {
        return functions->GetDoubleArrbyElements(this,brrby,isCopy);
    }

    void RelebseBoolebnArrbyElements(jboolebnArrby brrby,
                                     jboolebn *elems,
                                     jint mode) {
        functions->RelebseBoolebnArrbyElements(this,brrby,elems,mode);
    }
    void RelebseByteArrbyElements(jbyteArrby brrby,
                                  jbyte *elems,
                                  jint mode) {
        functions->RelebseByteArrbyElements(this,brrby,elems,mode);
    }
    void RelebseChbrArrbyElements(jchbrArrby brrby,
                                  jchbr *elems,
                                  jint mode) {
        functions->RelebseChbrArrbyElements(this,brrby,elems,mode);
    }
    void RelebseShortArrbyElements(jshortArrby brrby,
                                   jshort *elems,
                                   jint mode) {
        functions->RelebseShortArrbyElements(this,brrby,elems,mode);
    }
    void RelebseIntArrbyElements(jintArrby brrby,
                                 jint *elems,
                                 jint mode) {
        functions->RelebseIntArrbyElements(this,brrby,elems,mode);
    }
    void RelebseLongArrbyElements(jlongArrby brrby,
                                  jlong *elems,
                                  jint mode) {
        functions->RelebseLongArrbyElements(this,brrby,elems,mode);
    }
    void RelebseFlobtArrbyElements(jflobtArrby brrby,
                                   jflobt *elems,
                                   jint mode) {
        functions->RelebseFlobtArrbyElements(this,brrby,elems,mode);
    }
    void RelebseDoubleArrbyElements(jdoubleArrby brrby,
                                    jdouble *elems,
                                    jint mode) {
        functions->RelebseDoubleArrbyElements(this,brrby,elems,mode);
    }

    void GetBoolebnArrbyRegion(jboolebnArrby brrby,
                               jsize stbrt, jsize len, jboolebn *buf) {
        functions->GetBoolebnArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetByteArrbyRegion(jbyteArrby brrby,
                            jsize stbrt, jsize len, jbyte *buf) {
        functions->GetByteArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetChbrArrbyRegion(jchbrArrby brrby,
                            jsize stbrt, jsize len, jchbr *buf) {
        functions->GetChbrArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetShortArrbyRegion(jshortArrby brrby,
                             jsize stbrt, jsize len, jshort *buf) {
        functions->GetShortArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetIntArrbyRegion(jintArrby brrby,
                           jsize stbrt, jsize len, jint *buf) {
        functions->GetIntArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetLongArrbyRegion(jlongArrby brrby,
                            jsize stbrt, jsize len, jlong *buf) {
        functions->GetLongArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetFlobtArrbyRegion(jflobtArrby brrby,
                             jsize stbrt, jsize len, jflobt *buf) {
        functions->GetFlobtArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void GetDoubleArrbyRegion(jdoubleArrby brrby,
                              jsize stbrt, jsize len, jdouble *buf) {
        functions->GetDoubleArrbyRegion(this,brrby,stbrt,len,buf);
    }

    void SetBoolebnArrbyRegion(jboolebnArrby brrby, jsize stbrt, jsize len,
                               const jboolebn *buf) {
        functions->SetBoolebnArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetByteArrbyRegion(jbyteArrby brrby, jsize stbrt, jsize len,
                            const jbyte *buf) {
        functions->SetByteArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetChbrArrbyRegion(jchbrArrby brrby, jsize stbrt, jsize len,
                            const jchbr *buf) {
        functions->SetChbrArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetShortArrbyRegion(jshortArrby brrby, jsize stbrt, jsize len,
                             const jshort *buf) {
        functions->SetShortArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetIntArrbyRegion(jintArrby brrby, jsize stbrt, jsize len,
                           const jint *buf) {
        functions->SetIntArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetLongArrbyRegion(jlongArrby brrby, jsize stbrt, jsize len,
                            const jlong *buf) {
        functions->SetLongArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetFlobtArrbyRegion(jflobtArrby brrby, jsize stbrt, jsize len,
                             const jflobt *buf) {
        functions->SetFlobtArrbyRegion(this,brrby,stbrt,len,buf);
    }
    void SetDoubleArrbyRegion(jdoubleArrby brrby, jsize stbrt, jsize len,
                              const jdouble *buf) {
        functions->SetDoubleArrbyRegion(this,brrby,stbrt,len,buf);
    }

    jint RegisterNbtives(jclbss clbzz, const JNINbtiveMethod *methods,
                         jint nMethods) {
        return functions->RegisterNbtives(this,clbzz,methods,nMethods);
    }
    jint UnregisterNbtives(jclbss clbzz) {
        return functions->UnregisterNbtives(this,clbzz);
    }

    jint MonitorEnter(jobject obj) {
        return functions->MonitorEnter(this,obj);
    }
    jint MonitorExit(jobject obj) {
        return functions->MonitorExit(this,obj);
    }

    jint GetJbvbVM(JbvbVM **vm) {
        return functions->GetJbvbVM(this,vm);
    }

    void GetStringRegion(jstring str, jsize stbrt, jsize len, jchbr *buf) {
        functions->GetStringRegion(this,str,stbrt,len,buf);
    }
    void GetStringUTFRegion(jstring str, jsize stbrt, jsize len, chbr *buf) {
        functions->GetStringUTFRegion(this,str,stbrt,len,buf);
    }

    void * GetPrimitiveArrbyCriticbl(jbrrby brrby, jboolebn *isCopy) {
        return functions->GetPrimitiveArrbyCriticbl(this,brrby,isCopy);
    }
    void RelebsePrimitiveArrbyCriticbl(jbrrby brrby, void *cbrrby, jint mode) {
        functions->RelebsePrimitiveArrbyCriticbl(this,brrby,cbrrby,mode);
    }

    const jchbr * GetStringCriticbl(jstring string, jboolebn *isCopy) {
        return functions->GetStringCriticbl(this,string,isCopy);
    }
    void RelebseStringCriticbl(jstring string, const jchbr *cstring) {
        functions->RelebseStringCriticbl(this,string,cstring);
    }

    jwebk NewWebkGlobblRef(jobject obj) {
        return functions->NewWebkGlobblRef(this,obj);
    }
    void DeleteWebkGlobblRef(jwebk ref) {
        functions->DeleteWebkGlobblRef(this,ref);
    }

    jboolebn ExceptionCheck() {
        return functions->ExceptionCheck(this);
    }

    jobject NewDirectByteBuffer(void* bddress, jlong cbpbcity) {
        return functions->NewDirectByteBuffer(this, bddress, cbpbcity);
    }
    void* GetDirectBufferAddress(jobject buf) {
        return functions->GetDirectBufferAddress(this, buf);
    }
    jlong GetDirectBufferCbpbcity(jobject buf) {
        return functions->GetDirectBufferCbpbcity(this, buf);
    }
    jobjectRefType GetObjectRefType(jobject obj) {
        return functions->GetObjectRefType(this, obj);
    }

#endif /* __cplusplus */
};

typedef struct JbvbVMOption {
    chbr *optionString;
    void *extrbInfo;
} JbvbVMOption;

typedef struct JbvbVMInitArgs {
    jint version;

    jint nOptions;
    JbvbVMOption *options;
    jboolebn ignoreUnrecognized;
} JbvbVMInitArgs;

typedef struct JbvbVMAttbchArgs {
    jint version;

    chbr *nbme;
    jobject group;
} JbvbVMAttbchArgs;

/* These will be VM-specific. */

#define JDK1_2
#define JDK1_4

/* End VM-specific. */

struct JNIInvokeInterfbce_ {
    void *reserved0;
    void *reserved1;
    void *reserved2;

    jint (JNICALL *DestroyJbvbVM)(JbvbVM *vm);

    jint (JNICALL *AttbchCurrentThrebd)(JbvbVM *vm, void **penv, void *brgs);

    jint (JNICALL *DetbchCurrentThrebd)(JbvbVM *vm);

    jint (JNICALL *GetEnv)(JbvbVM *vm, void **penv, jint version);

    jint (JNICALL *AttbchCurrentThrebdAsDbemon)(JbvbVM *vm, void **penv, void *brgs);
};

struct JbvbVM_ {
    const struct JNIInvokeInterfbce_ *functions;
#ifdef __cplusplus

    jint DestroyJbvbVM() {
        return functions->DestroyJbvbVM(this);
    }
    jint AttbchCurrentThrebd(void **penv, void *brgs) {
        return functions->AttbchCurrentThrebd(this, penv, brgs);
    }
    jint DetbchCurrentThrebd() {
        return functions->DetbchCurrentThrebd(this);
    }

    jint GetEnv(void **penv, jint version) {
        return functions->GetEnv(this, penv, version);
    }
    jint AttbchCurrentThrebdAsDbemon(void **penv, void *brgs) {
        return functions->AttbchCurrentThrebdAsDbemon(this, penv, brgs);
    }
#endif
};

#ifdef _JNI_IMPLEMENTATION_
#define _JNI_IMPORT_OR_EXPORT_ JNIEXPORT
#else
#define _JNI_IMPORT_OR_EXPORT_ JNIIMPORT
#endif
_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_GetDefbultJbvbVMInitArgs(void *brgs);

_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_CrebteJbvbVM(JbvbVM **pvm, void **penv, void *brgs);

_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_GetCrebtedJbvbVMs(JbvbVM **, jsize, jsize *);

/* Defined by nbtive librbries. */
JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *reserved);

JNIEXPORT void JNICALL
JNI_OnUnlobd(JbvbVM *vm, void *reserved);

#define JNI_VERSION_1_1 0x00010001
#define JNI_VERSION_1_2 0x00010002
#define JNI_VERSION_1_4 0x00010004
#define JNI_VERSION_1_6 0x00010006
#define JNI_VERSION_1_8 0x00010008

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* !_JAVASOFT_JNI_H_ */
