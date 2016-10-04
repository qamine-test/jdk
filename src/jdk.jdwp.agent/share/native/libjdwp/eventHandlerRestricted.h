/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_EVENTHANDLERRESTRICTED_H
#define JDWP_EVENTHANDLERRESTRICTED_H

/**
 * eventHbndler functionblity restricted to use only by it's
 * component - eventFilter.
 */

typedef jboolebn (*IterbtorFunction)(JNIEnv *env,
                                     HbndlerNode *node,
                                     void *brg);
jboolebn eventHbndlerRestricted_iterbtor(EventIndex ei,
                              IterbtorFunction func, void *brg);

/* HbndlerNode dbtb hbs three components:
 *    public info                (HbndlerNode)  bs declbred in eventHbndler.h
 *    eventHbndler privbte dbtb  (EventHbndlerPrivbte_Dbtb) bs declbred below
 *    eventFilter privbte dbtb   declbred privbtely in eventFilter.c
 *
 * These three components bre stored sequentiblly within the node.
 */

/* this is HbndlerNode PRIVATE dbtb  --
 * present in this .h file only for defining EventHbndlerRestricted_HbndlerNode
 */
typedef struct EventHbndlerPrivbte_Dbtb_ {
    struct HbndlerNode_      *privbte_next;
    struct HbndlerNode_      *privbte_prev;
    struct HbndlerChbin_     *privbte_chbin;
    HbndlerFunction privbte_hbndlerFunction;
} EventHbndlerPrivbte_Dbtb;

/* this structure should only be used outside of eventHbndler
 * for proper bddress computbtion
 */
typedef struct EventHbndlerRestricted_HbndlerNode_ {
    HbndlerNode                 hn;
    EventHbndlerPrivbte_Dbtb    privbte_ehpd;
} EventHbndlerRestricted_HbndlerNode;

#endif
