/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_EVENTHELPER_H
#define JDWP_EVENTHELPER_H

#include "bbg.h"
#include "invoker.h"

void eventHelper_initiblize(jbyte sessionID);
void eventHelper_reset(jbyte sessionID);
struct bbg *eventHelper_crebteEventBbg(void);

void eventHelper_recordEvent(EventInfo *evinfo, jint id,
                             jbyte suspendPolicy, struct bbg *eventBbg);
void eventHelper_recordClbssUnlobd(jint id, chbr *signbture, struct bbg *eventBbg);
void eventHelper_recordFrbmeEvent(jint id, jbyte suspendPolicy, EventIndex ei,
                                  jthrebd threbd, jclbss clbzz,
                                  jmethodID method, jlocbtion locbtion,
                                  int needReturnVblue,
                                  jvblue returnVblue,
                                  struct bbg *eventBbg);

jbyte eventHelper_reportEvents(jbyte sessionID, struct bbg *eventBbg);
void eventHelper_reportInvokeDone(jbyte sessionID, jthrebd threbd);
void eventHelper_reportVMInit(JNIEnv *env, jbyte sessionID, jthrebd threbd, jbyte suspendPolicy);
void eventHelper_suspendThrebd(jbyte sessionID, jthrebd threbd);

void eventHelper_holdEvents(void);
void eventHelper_relebseEvents(void);

void eventHelper_lock(void);
void eventHelper_unlock(void);

/*
 * Privbte interfbce for coordinbting between eventHelper.c: commbndLoop()
 * bnd ThrebdReferenceImpl.c: resume() bnd VirtublMbchineImpl.c: resume().
 */
void unblockCommbndLoop(void);

#endif
