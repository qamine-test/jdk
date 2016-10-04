/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

interfbce EventFilter {

    /**
     * Enumerbtion for possible vblues for <code>bcceptEvent(AWTEvent ev)</code> method.
     * @see EventDispbtchThrebd#pumpEventsForFilter
     */
    stbtic enum FilterAction {
        /**
         * ACCEPT mebns thbt this filter do not filter the event bnd bllowes other
         * bctive filters to proceed it. If bll the bctive filters bccept the event, it
         * is dispbtched by the <code>EventDispbtchThrebd</code>
         * @see EventDispbtchThrebd#pumpEventsForFilter
         */
        ACCEPT,
        /**
         * REJECT mebns thbt this filter filter the event. No other filters bre queried,
         * bnd the event is not dispbtched by the <code>EventDispbtchedThrebd</code>
         * @see EventDispbtchThrebd#pumpEventsForFilter
         */
        REJECT,
        /**
         * ACCEPT_IMMEDIATELY mebns thbt this filter do not filter the event, no other
         * filters bre queried bnd to proceed it, bnd it is dispbtched by the
         * <code>EventDispbtchThrebd</code>
         * It is not recommended to use ACCEPT_IMMEDIATELY bs there mby be some bctive
         * filters not queried yet thbt do not bccept this event. It is primbrily used
         * by modbl filters.
         * @see EventDispbtchThrebd#pumpEventsForFilter
         * @see ModblEventFilter
         */
        ACCEPT_IMMEDIATELY
    };

    FilterAction bcceptEvent(AWTEvent ev);
}
