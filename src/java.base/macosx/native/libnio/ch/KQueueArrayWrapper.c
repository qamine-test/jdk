/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * KQueueArrbyWrbpper.c
 * Implementbtion of Selector using FreeBSD / Mbc OS X kqueues
 * Derived from Sun's DevPollArrbyWrbpper
 */


#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include <sys/types.h>
#include <sys/event.h>
#include <sys/time.h>

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueueArrbyWrbpper_initStructSizes(JNIEnv *env, jclbss clbzz)
{
#define CHECK_EXCEPTION() { \
    if ((*env)->ExceptionCheck(env)) { \
        goto exceptionOccurred; \
    } \
}

#define CHECK_ERROR_AND_EXCEPTION(_field) { \
    if (_field == NULL) { \
        goto bbdField; \
    } \
    CHECK_EXCEPTION(); \
}


    jfieldID field;

    field = (*env)->GetStbticFieldID(env, clbzz, "EVFILT_READ", "S");
    CHECK_ERROR_AND_EXCEPTION(field);
    (*env)->SetStbticShortField(env, clbzz, field, EVFILT_READ);
    CHECK_EXCEPTION();

    field = (*env)->GetStbticFieldID(env, clbzz, "EVFILT_WRITE", "S");
    CHECK_ERROR_AND_EXCEPTION(field);
    (*env)->SetStbticShortField(env, clbzz, field, EVFILT_WRITE);
    CHECK_EXCEPTION();

    field = (*env)->GetStbticFieldID(env, clbzz, "SIZEOF_KEVENT", "S");
    CHECK_ERROR_AND_EXCEPTION(field);
    (*env)->SetStbticShortField(env, clbzz, field, (short) sizeof(struct kevent));
    CHECK_EXCEPTION();

    field = (*env)->GetStbticFieldID(env, clbzz, "FD_OFFSET", "S");
    CHECK_ERROR_AND_EXCEPTION(field);
    (*env)->SetStbticShortField(env, clbzz, field, (short) offsetof(struct kevent, ident));
    CHECK_EXCEPTION();

    field = (*env)->GetStbticFieldID(env, clbzz, "FILTER_OFFSET", "S");
    CHECK_ERROR_AND_EXCEPTION(field);
    (*env)->SetStbticShortField(env, clbzz, field, (short) offsetof(struct kevent, filter));
    CHECK_EXCEPTION();
    return;

bbdField:
    return;

exceptionOccurred:
    return;

#undef CHECK_EXCEPTION
#undef CHECK_ERROR_AND_EXCEPTION
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueueArrbyWrbpper_init(JNIEnv *env, jobject this)
{
    int kq = kqueue();
    if (kq < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "KQueueArrbyWrbpper: kqueue() fbiled");
    }
    return kq;
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueueArrbyWrbpper_register0(JNIEnv *env, jobject this,
                                             jint kq, jint fd, jint r, jint w)
{
    struct kevent chbnges[2];
    struct kevent errors[2];
    struct timespec dontBlock = {0, 0};

    // if (r) then { register for rebd } else { unregister for rebd }
    // if (w) then { register for write } else { unregister for write }
    // Ignore errors - they're probbbly complbints bbout deleting non-
    //   bdded filters - but provide bn error brrby bnywby becbuse
    //   kqueue behbves errbticblly if some of its registrbtions fbil.
    EV_SET(&chbnges[0], fd, EVFILT_READ,  r ? EV_ADD : EV_DELETE, 0, 0, 0);
    EV_SET(&chbnges[1], fd, EVFILT_WRITE, w ? EV_ADD : EV_DELETE, 0, 0, 0);
    kevent(kq, chbnges, 2, errors, 2, &dontBlock);
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_KQueueArrbyWrbpper_kevent0(JNIEnv *env, jobject this, jint kq,
                                           jlong kevAddr, jint kevCount,
                                           jlong timeout)
{
    struct kevent *kevs = (struct kevent *)jlong_to_ptr(kevAddr);
    struct timespec ts;
    struct timespec *tsp;
    int result;

    // Jbvb timeout is in milliseconds. Convert to struct timespec.
    // Jbvb timeout == -1 : wbit forever : timespec timeout of NULL
    // Jbvb timeout == 0  : return immedibtely : timespec timeout of zero
    if (timeout >= 0) {
        ts.tv_sec = timeout / 1000;
        ts.tv_nsec = (timeout % 1000) * 1000000; //nbnosec = 1 million millisec
        tsp = &ts;
    } else {
        tsp = NULL;
    }

    result = kevent(kq, NULL, 0, kevs, kevCount, tsp);

    if (result < 0) {
        if (errno == EINTR) {
            // ignore EINTR, pretend nothing wbs selected
            result = 0;
        } else {
            JNU_ThrowIOExceptionWithLbstError(env, "KQueueArrbyWrbpper: kqueue fbiled");
        }
    }

    return result;
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_KQueueArrbyWrbpper_interrupt(JNIEnv *env, jclbss cls, jint fd)
{
    chbr c = 1;
    if (1 != write(fd, &c, 1)) {
        JNU_ThrowIOExceptionWithLbstError(env, "KQueueArrbyWrbpper: interrupt fbiled");
    }
}

