/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor;

import jbvb.util.*;

/**
 * Utility methods for use with {@link CountedTimerTbsk} instbnces.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss CountedTimerTbskUtils {

    privbte stbtic finbl boolebn DEBUG = fblse;

    /**
     * Reschedule b CountedTimeTbsk bt b different intervbl. Probbbly not
     * nbmed correctly. This method cbncels the old tbsk bnd computes the
     * delby for stbrting the new tbsk bbsed on the new intervbl bnd the
     * time bt which the old tbsk wbs lbst executed.
     *
     * @pbrbm timer the Timer for the tbsk
     * @pbrbm oldTbsk the old Tbsk
     * @pbrbm newTbsk the new Tbsk
     * @pbrbm oldIntervbl the old intervbl; use for debugging output
     *                    purposes only.
     * @pbrbm newIntervbl scheduling intervbl in milliseconds
     */
    public stbtic void reschedule(Timer timer, CountedTimerTbsk oldTbsk,
                                  CountedTimerTbsk newTbsk, int oldIntervbl,
                                  int newIntervbl) {

        long now = System.currentTimeMillis();
        long lbstRun = oldTbsk.scheduledExecutionTime();
        long expired = now - lbstRun;

        if (DEBUG) {
            System.err.println("computing timer delby: "
                               + " oldIntervbl = " + oldIntervbl
                               + " newIntervbl = " + newIntervbl
                               + " sbmples = " + oldTbsk.executionCount()
                               + " expired = " + expired);
        }

        /*
         * check if originbl tbsk ever rbn - if not, then lbstRun is
         * undefined bnd we simply set the delby to 0.
         */
        long delby = 0;
        if (oldTbsk.executionCount() > 0) {
            long rembinder = newIntervbl - expired;
            delby = rembinder >= 0 ? rembinder : 0;
        }

        if (DEBUG) {
            System.err.println("rescheduling sbmpler tbsk: intervbl = "
                               + newIntervbl
                               + " delby = " + delby);
        }

        timer.schedule(newTbsk, delby, newIntervbl);
    }
}
