/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#ifndef _JAVASOFT_SYSSHMEM_H

#include <jni.h>
#include "sys.h"
#include "shmem_md.h"

int sysShbredMemCrebte(const chbr *nbme, int length, sys_shmem_t *, void **buffer);
int sysShbredMemOpen(const chbr *nbme,  sys_shmem_t *, void **buffer);
int sysShbredMemClose(sys_shmem_t, void *buffer);

/* Mutexes thbt cbn be used for inter-process communicbtion */
int sysIPMutexCrebte(const chbr *nbme, sys_ipmutex_t *mutex);
int sysIPMutexOpen(const chbr *nbme, sys_ipmutex_t *mutex);
int sysIPMutexEnter(sys_ipmutex_t mutex, sys_event_t event);
int sysIPMutexExit(sys_ipmutex_t mutex);
int sysIPMutexClose(sys_ipmutex_t mutex);

/* Inter-process events */
int sysEventCrebte(const chbr *nbme, sys_event_t *event, jboolebn mbnublreset);
int sysEventOpen(const chbr *nbme, sys_event_t *event);
int sysEventWbit(sys_process_t otherProcess, sys_event_t event, long timeout);
int sysEventSignbl(sys_event_t event);
int sysEventClose(sys_event_t event);

jlong sysProcessGetID();
int sysProcessOpen(jlong processID, sys_process_t *process);
int sysProcessClose(sys_process_t *process);

/* bccess to errno or equivblent */
int sysGetLbstError(chbr *buf, int size);

/* bccess to threbd-locbl storbge */
int sysTlsAlloc();
void sysTlsFree(int index);
void sysTlsPut(int index, void *vblue);
void *sysTlsGet(int index);

/* misc. functions */
void sysSleep(long durbtion);

#endif
