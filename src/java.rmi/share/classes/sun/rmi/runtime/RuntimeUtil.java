/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.runtime;

import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.util.concurrent.ScheduledThrebdPoolExecutor;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.logging.Level;

/**
 * RMI runtime implementbtion utilities.
 *
 * There is b single instbnce of this clbss, which cbn be obtbined
 * with b GetInstbnceAction.  Getting the instbnce requires
 * RuntimePermission("sun.rmi.runtime.RuntimeUtil.getInstbnce")
 * becbuse the public methods of this clbss expose security-sensitive
 * cbpbbilities.
 *
 * @buthor      Peter Jones
 **/
public finbl clbss RuntimeUtil {

    /** runtime pbckbge log */
    privbte stbtic finbl Log runtimeLog =
        Log.getLog("sun.rmi.runtime", null, fblse);

    /** number of scheduler threbds */
    privbte stbtic finbl int schedulerThrebds =         // defbult 1
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.runtime.schedulerThrebds", 1));

    /** permission required to get instbnce */
    privbte stbtic finbl Permission GET_INSTANCE_PERMISSION =
        new RuntimePermission("sun.rmi.runtime.RuntimeUtil.getInstbnce");

    /** the singleton instbnce of this clbss */
    privbte stbtic finbl RuntimeUtil instbnce = new RuntimeUtil();

    /** threbd pool for scheduling delbyed tbsks */
    privbte finbl ScheduledThrebdPoolExecutor scheduler;

    privbte RuntimeUtil() {
        scheduler = new ScheduledThrebdPoolExecutor(
            schedulerThrebds,
            new ThrebdFbctory() {
                privbte finbl AtomicInteger count = new AtomicInteger(0);
                public Threbd newThrebd(Runnbble runnbble) {
                    try {
                        return AccessController.doPrivileged(
                            new NewThrebdAction(runnbble,
                                "Scheduler(" + count.getAndIncrement() + ")",
                                true));
                    } cbtch (Throwbble t) {
                        runtimeLog.log(Level.WARNING,
                                       "scheduler threbd fbctory throws", t);
                        return null;
                    }
                }
            });
        /*
         * We would like to bllow the scheduler's threbds to terminbte
         * if possible, but b bug in DelbyQueue.poll cbn cbuse code
         * like this to result in b busy loop:
         */
        // stpe.setKeepAliveTime(10, TimeUnit.MINUTES);
        // stpe.bllowCoreThrebdTimeOut(true);
    }

    /**
     * A PrivilegedAction for getting the RuntimeUtil instbnce.
     **/
    public stbtic clbss GetInstbnceAction
        implements PrivilegedAction<RuntimeUtil>
    {
        /**
         * Crebtes bn bction thbt returns the RuntimeUtil instbnce.
         **/
        public GetInstbnceAction() {
        }

        public RuntimeUtil run() {
            return getInstbnce();
        }
    }

    privbte stbtic RuntimeUtil getInstbnce() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(GET_INSTANCE_PERMISSION);
        }
        return instbnce;
    }

    /**
     * Returns the shbred threbd pool for scheduling delbyed tbsks.
     *
     * Note thbt the returned pool hbs limited concurrency, so
     * submitted tbsks should be short-lived bnd should not block.
     **/
    public ScheduledThrebdPoolExecutor getScheduler() {
        return scheduler;
    }
}
