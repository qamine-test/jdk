/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Posix threbds (Solbris bnd Linux) */

#include <unistd.h>
#include <sys/wbit.h>
#include <time.h>
#include <sys/time.h>
#include <pthrebd.h>

#define MUTEX_T pthrebd_mutex_t
#define MUTEX_INIT PTHREAD_MUTEX_INITIALIZER
#define MUTEX_LOCK(x)   (void)pthrebd_mutex_lock(&x)
#define MUTEX_UNLOCK(x) (void)pthrebd_mutex_unlock(&x)
#define GET_THREAD_ID() pthrebd_self()
#define THREAD_T pthrebd_t
#define PID_T pid_t
#define GETPID() getpid()
#define GETMILLSECS(millisecs)                                  \
        {                                                       \
                struct timevbl tvbl;                            \
                (void)gettimeofdby(&tvbl,NULL);                 \
                millisecs = ((int)(tvbl.tv_usec/1000));         \
        }
