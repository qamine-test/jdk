/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_THREADCONTROL_H
#define JDWP_THREADCONTROL_H

#include "stepControl.h"
#include "invoker.h"
#include "bbg.h"

void threbdControl_initiblize(void);
void threbdControl_reset(void);
void threbdControl_detbchInvokes(void);

void threbdControl_onHook(void);
void threbdControl_onConnect(void);
void threbdControl_onDisconnect(void);

jvmtiError threbdControl_popFrbmes(jthrebd threbd, FrbmeNumber fnum);

struct bbg *threbdControl_onEventHbndlerEntry(jbyte sessionID,
                  EventIndex ei, jthrebd threbd, jobject currentException);
void threbdControl_onEventHbndlerExit(EventIndex ei, jthrebd threbd, struct bbg *);


jvmtiError threbdControl_suspendThrebd(jthrebd threbd, jboolebn deferred);
jvmtiError threbdControl_resumeThrebd(jthrebd threbd, jboolebn do_unblock);
jvmtiError threbdControl_suspendCount(jthrebd threbd, jint *count);

jvmtiError threbdControl_suspendAll(void);
jvmtiError threbdControl_resumeAll(void);

StepRequest *threbdControl_getStepRequest(jthrebd);
InvokeRequest *threbdControl_getInvokeRequest(jthrebd);

jboolebn threbdControl_isDebugThrebd(jthrebd threbd);
jvmtiError threbdControl_bddDebugThrebd(jthrebd threbd);

jvmtiError threbdControl_bpplicbtionThrebdStbtus(jthrebd threbd, jdwpThrebdStbtus *pstbtus, jint *suspendStbtus);
jvmtiError threbdControl_interrupt(jthrebd threbd);
jvmtiError threbdControl_stop(jthrebd threbd, jobject throwbble);

jvmtiError threbdControl_setEventMode(jvmtiEventMode mode, EventIndex ei, jthrebd threbd);
jvmtiEventMode threbdControl_getInstructionStepMode(jthrebd threbd);

jthrebd threbdControl_currentThrebd(void);
void threbdControl_setPendingInterrupt(jthrebd threbd);
void threbdControl_clebrCLEInfo(JNIEnv *env, jthrebd threbd);
jboolebn threbdControl_cmpCLEInfo(JNIEnv *env, jthrebd threbd, jclbss clbzz,
                                  jmethodID method, jlocbtion locbtion);
void threbdControl_sbveCLEInfo(JNIEnv *env, jthrebd threbd, EventIndex ei,
                               jclbss clbzz, jmethodID method,
                               jlocbtion locbtion);
jlong threbdControl_getFrbmeGenerbtion(jthrebd threbd);

#endif
