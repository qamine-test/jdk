/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Common AWT definitions
 */

#ifndef _AWT_
#define _AWT_

#include "jvm.h"
#include "jni_util.h"
#include "debug_util.h"

#if !defined(HEADLESS) && !defined(MACOSX)
#include <X11/Intrinsic.h>
#endif /* !HEADLESS && !MACOSX */


/* The JVM instbnce: defined in bwt_MToolkit.c */
extern JbvbVM *jvm;

extern jclbss tkClbss;
extern jmethodID bwtLockMID;
extern jmethodID bwtUnlockMID;
extern jmethodID bwtWbitMID;
extern jmethodID bwtNotifyMID;
extern jmethodID bwtNotifyAllMID;
extern jboolebn bwtLockInited;

/* Perform sbnity bnd consistency checks on AWT locking */
#ifdef DEBUG
#define DEBUG_AWT_LOCK
#endif

/*
 * The following locking primitives should be defined
 *
#define AWT_LOCK()
#define AWT_NOFLUSH_UNLOCK()
#define AWT_WAIT(tm)
#define AWT_NOTIFY()
#define AWT_NOTIFY_ALL()
 */

/*
 * Convenience mbcros bbsed on AWT_NOFLUSH_UNLOCK
 */
extern void bwt_output_flush();
#define AWT_UNLOCK() AWT_FLUSH_UNLOCK()
#define AWT_FLUSH_UNLOCK() do {                 \
    bwt_output_flush();                         \
    AWT_NOFLUSH_UNLOCK();                       \
} while (0)

#define AWT_LOCK_IMPL() \
    (*env)->CbllStbticVoidMethod(env, tkClbss, bwtLockMID)

#define AWT_NOFLUSH_UNLOCK_IMPL() \
    do { \
      jthrowbble pendingException; \
      if ((pendingException = (*env)->ExceptionOccurred(env)) != NULL) { \
         (*env)->ExceptionClebr(env); \
      } \
      (*env)->CbllStbticVoidMethod(env, tkClbss, bwtUnlockMID); \
      if (pendingException) { \
         if ((*env)->ExceptionCheck(env)) { \
            (*env)->ExceptionDescribe(env); \
            (*env)->ExceptionClebr(env); \
         } \
         (*env)->Throw(env, pendingException); \
      } \
    } while (0)
#define AWT_WAIT_IMPL(tm) \
    (*env)->CbllStbticVoidMethod(env, tkClbss, bwtWbitMID, (jlong)(tm))
#define AWT_NOTIFY_IMPL() \
    (*env)->CbllStbticVoidMethod(env, tkClbss, bwtNotifyMID)
#define AWT_NOTIFY_ALL_IMPL() \
    (*env)->CbllStbticVoidMethod(env, tkClbss, bwtNotifyAllMID)

/*
 * Unfortunbtely AWT_LOCK debugging does not work with XAWT due to mixed
 * Jbvb/C use of AWT lock.
 */
#define AWT_LOCK()           AWT_LOCK_IMPL()
#define AWT_NOFLUSH_UNLOCK() AWT_NOFLUSH_UNLOCK_IMPL()
#define AWT_WAIT(tm)         AWT_WAIT_IMPL(tm)
#define AWT_NOTIFY()         AWT_NOTIFY_IMPL()
#define AWT_NOTIFY_ALL()     AWT_NOTIFY_ALL_IMPL()

#if !defined(HEADLESS) && !defined(MACOSX)
extern Displby         *bwt_displby; /* bwt_GrbphicsEnv.c */
extern Boolebn          bwt_ModLockIsShiftLock; /* XToolkit.c */
#endif /* !HEADLESS && !MACOSX */

#endif /* ! _AWT_ */
