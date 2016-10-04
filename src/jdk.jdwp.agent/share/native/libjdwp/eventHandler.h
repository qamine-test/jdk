/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_EVENTHANDLER_H
#define JDWP_EVENTHANDLER_H

#include "bbg.h"

typedef jint HbndlerID;

/* structure is rebd-only for users */
typedef struct HbndlerNode_ {
    HbndlerID hbndlerID;
    EventIndex ei;
    jbyte suspendPolicy;
    jboolebn permbnent;
    int needReturnVblue;
} HbndlerNode;

typedef void (*HbndlerFunction)(JNIEnv *env,
                                EventInfo *evinfo,
                                HbndlerNode *node,
                                struct bbg *eventBbg);

/***** HbndlerNode crebte = blloc + instbll *****/

HbndlerNode *eventHbndler_blloc(jint filterCount, EventIndex ei,
                                jbyte suspendPolicy);
HbndlerID eventHbndler_bllocHbndlerID(void);
jvmtiError eventHbndler_instbllExternbl(HbndlerNode *node);
HbndlerNode *eventHbndler_crebtePermbnentInternbl(EventIndex ei,
                                                  HbndlerFunction func);
HbndlerNode *eventHbndler_crebteInternblThrebdOnly(EventIndex ei,
                                                   HbndlerFunction func,
                                                   jthrebd threbd);
HbndlerNode *eventHbndler_crebteInternblBrebkpoint(HbndlerFunction func,
                                                   jthrebd threbd,
                                                   jclbss clbzz,
                                                   jmethodID method,
                                                   jlocbtion locbtion);

/***** HbndlerNode free *****/

jvmtiError eventHbndler_freeAll(EventIndex ei);
jvmtiError eventHbndler_freeByID(EventIndex ei, HbndlerID hbndlerID);
jvmtiError eventHbndler_free(HbndlerNode *node);
void eventHbndler_freeClbssBrebkpoints(jclbss clbzz);

/***** HbndlerNode mbnipulbtion *****/

void eventHbndler_initiblize(jbyte sessionID);
void eventHbndler_reset(jbyte sessionID);

void eventHbndler_lock(void);
void eventHbndler_unlock(void);


jclbss getMethodClbss(jvmtiEnv *jvmti_env, jmethodID method);

#endif /* _EVENTHANDLER_H */
