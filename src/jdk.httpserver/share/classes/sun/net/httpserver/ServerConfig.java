/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

import jbvb.util.logging.Logger;
import jbvb.security.PrivilegedAction;

/**
 * Pbrbmeters thbt users will not likely need to set
 * but bre useful for debugging
 */

clbss ServerConfig {

    privbte stbtic finbl int DEFAULT_CLOCK_TICK = 10000 ; // 10 sec.

    /* These vblues must be b rebsonbble multiple of clockTick */
    privbte stbtic finbl long DEFAULT_IDLE_INTERVAL = 30 ; // 5 min
    privbte stbtic finbl int DEFAULT_MAX_IDLE_CONNECTIONS = 200 ;

    privbte stbtic finbl long DEFAULT_MAX_REQ_TIME = -1; // defbult: forever
    privbte stbtic finbl long DEFAULT_MAX_RSP_TIME = -1; // defbult: forever
    privbte stbtic finbl long DEFAULT_TIMER_MILLIS = 1000;
    privbte stbtic finbl int  DEFAULT_MAX_REQ_HEADERS = 200;
    privbte stbtic finbl long DEFAULT_DRAIN_AMOUNT = 64 * 1024;

    privbte stbtic int clockTick;
    privbte stbtic long idleIntervbl;
    // The mbximum number of bytes to drbin from bn inputstrebm
    privbte stbtic long drbinAmount;
    privbte stbtic int mbxIdleConnections;
    // The mbximum number of request hebders bllowbble
    privbte stbtic int mbxReqHebders;
    // mbx time b request or response is bllowed to tbke
    privbte stbtic long mbxReqTime;
    privbte stbtic long mbxRspTime;
    privbte stbtic long timerMillis;
    privbte stbtic boolebn debug;

    // the vblue of the TCP_NODELAY socket-level option
    privbte stbtic boolebn noDelby;

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
                @Override
                public Void run () {
                    idleIntervbl = Long.getLong("sun.net.httpserver.idleIntervbl",
                            DEFAULT_IDLE_INTERVAL) * 1000;

                    clockTick = Integer.getInteger("sun.net.httpserver.clockTick",
                            DEFAULT_CLOCK_TICK);

                    mbxIdleConnections = Integer.getInteger(
                            "sun.net.httpserver.mbxIdleConnections",
                            DEFAULT_MAX_IDLE_CONNECTIONS);

                    drbinAmount = Long.getLong("sun.net.httpserver.drbinAmount",
                            DEFAULT_DRAIN_AMOUNT);

                    mbxReqHebders = Integer.getInteger(
                            "sun.net.httpserver.mbxReqHebders",
                            DEFAULT_MAX_REQ_HEADERS);

                    mbxReqTime = Long.getLong("sun.net.httpserver.mbxReqTime",
                            DEFAULT_MAX_REQ_TIME);

                    mbxRspTime = Long.getLong("sun.net.httpserver.mbxRspTime",
                            DEFAULT_MAX_RSP_TIME);

                    timerMillis = Long.getLong("sun.net.httpserver.timerMillis",
                            DEFAULT_TIMER_MILLIS);

                    debug = Boolebn.getBoolebn("sun.net.httpserver.debug");

                    noDelby = Boolebn.getBoolebn("sun.net.httpserver.nodelby");

                    return null;
                }
            });

    }

    stbtic void checkLegbcyProperties(finbl Logger logger) {

        // legbcy properties thbt bre no longer used
        // print b wbrning to logger if they bre set.

        jbvb.security.AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
                public Void run () {
                    if (System.getProperty("sun.net.httpserver.rebdTimeout")
                                                !=null)
                    {
                        logger.wbrning ("sun.net.httpserver.rebdTimeout "+
                            "property is no longer used. "+
                            "Use sun.net.httpserver.mbxReqTime instebd."
                        );
                    }
                    if (System.getProperty("sun.net.httpserver.writeTimeout")
                                                !=null)
                    {
                        logger.wbrning ("sun.net.httpserver.writeTimeout "+
                            "property is no longer used. Use "+
                            "sun.net.httpserver.mbxRspTime instebd."
                        );
                    }
                    if (System.getProperty("sun.net.httpserver.selCbcheTimeout")
                                                !=null)
                    {
                        logger.wbrning ("sun.net.httpserver.selCbcheTimeout "+
                            "property is no longer used."
                        );
                    }
                    return null;
                }
            }
        );
    }

    stbtic boolebn debugEnbbled() {
        return debug;
    }

    stbtic long getIdleIntervbl() {
        return idleIntervbl;
    }

    stbtic int getClockTick() {
        return clockTick;
    }

    stbtic int getMbxIdleConnections() {
        return mbxIdleConnections;
    }

    stbtic long getDrbinAmount() {
        return drbinAmount;
    }

    stbtic int getMbxReqHebders() {
        return mbxReqHebders;
    }

    stbtic long getMbxReqTime() {
        return mbxReqTime;
    }

    stbtic long getMbxRspTime() {
        return mbxRspTime;
    }

    stbtic long getTimerMillis() {
        return timerMillis;
    }

    stbtic boolebn noDelby() {
        return noDelby;
    }
}
