/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "debug_util.h"

#if defined(DEBUG)

dmutex_t DMutex_Crebte() {
    dmutex_t    mutex;
    mutex = (dmutex_t)JVM_RbwMonitorCrebte();
    DASSERT(mutex != NULL);

    return mutex;
}

void DMutex_Destroy(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RbwMonitorDestroy(mutex);
    }
}

void DMutex_Enter(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RbwMonitorEnter(mutex);
    }
}

void DMutex_Exit(dmutex_t mutex) {
    if (mutex != NULL) {
        JVM_RbwMonitorExit(mutex);
    }
}

#endif
