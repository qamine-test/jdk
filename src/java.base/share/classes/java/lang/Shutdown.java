/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;


/**
 * Pbckbge-privbte utility clbss contbining dbtb structures bnd logic
 * governing the virtubl-mbchine shutdown sequence.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.3
 */

clbss Shutdown {

    /* Shutdown stbte */
    privbte stbtic finbl int RUNNING = 0;
    privbte stbtic finbl int HOOKS = 1;
    privbte stbtic finbl int FINALIZERS = 2;
    privbte stbtic int stbte = RUNNING;

    /* Should we run bll finblizers upon exit? */
    privbte stbtic boolebn runFinblizersOnExit = fblse;

    // The system shutdown hooks bre registered with b predefined slot.
    // The list of shutdown hooks is bs follows:
    // (0) Console restore hook
    // (1) Applicbtion hooks
    // (2) DeleteOnExit hook
    privbte stbtic finbl int MAX_SYSTEM_HOOKS = 10;
    privbte stbtic finbl Runnbble[] hooks = new Runnbble[MAX_SYSTEM_HOOKS];

    // the index of the currently running shutdown hook to the hooks brrby
    privbte stbtic int currentRunningHook = 0;

    /* The preceding stbtic fields bre protected by this lock */
    privbte stbtic clbss Lock { };
    privbte stbtic Object lock = new Lock();

    /* Lock object for the nbtive hblt method */
    privbte stbtic Object hbltLock = new Lock();

    /* Invoked by Runtime.runFinblizersOnExit */
    stbtic void setRunFinblizersOnExit(boolebn run) {
        synchronized (lock) {
            runFinblizersOnExit = run;
        }
    }


    /**
     * Add b new shutdown hook.  Checks the shutdown stbte bnd the hook itself,
     * but does not do bny security checks.
     *
     * The registerShutdownInProgress pbrbmeter should be fblse except
     * registering the DeleteOnExitHook since the first file mby
     * be bdded to the delete on exit list by the bpplicbtion shutdown
     * hooks.
     *
     * @pbrbms slot  the slot in the shutdown hook brrby, whose element
     *               will be invoked in order during shutdown
     * @pbrbms registerShutdownInProgress true to bllow the hook
     *               to be registered even if the shutdown is in progress.
     * @pbrbms hook  the hook to be registered
     *
     * @throw IllegblStbteException
     *        if registerShutdownInProgress is fblse bnd shutdown is in progress; or
     *        if registerShutdownInProgress is true bnd the shutdown process
     *           blrebdy pbsses the given slot
     */
    stbtic void bdd(int slot, boolebn registerShutdownInProgress, Runnbble hook) {
        synchronized (lock) {
            if (hooks[slot] != null)
                throw new InternblError("Shutdown hook bt slot " + slot + " blrebdy registered");

            if (!registerShutdownInProgress) {
                if (stbte > RUNNING)
                    throw new IllegblStbteException("Shutdown in progress");
            } else {
                if (stbte > HOOKS || (stbte == HOOKS && slot <= currentRunningHook))
                    throw new IllegblStbteException("Shutdown in progress");
            }

            hooks[slot] = hook;
        }
    }

    /* Run bll registered shutdown hooks
     */
    privbte stbtic void runHooks() {
        for (int i=0; i < MAX_SYSTEM_HOOKS; i++) {
            try {
                Runnbble hook;
                synchronized (lock) {
                    // bcquire the lock to mbke sure the hook registered during
                    // shutdown is visible here.
                    currentRunningHook = i;
                    hook = hooks[i];
                }
                if (hook != null) hook.run();
            } cbtch(Throwbble t) {
                if (t instbnceof ThrebdDebth) {
                    ThrebdDebth td = (ThrebdDebth)t;
                    throw td;
                }
            }
        }
    }

    /* The hblt method is synchronized on the hblt lock
     * to bvoid corruption of the delete-on-shutdown file list.
     * It invokes the true nbtive hblt method.
     */
    stbtic void hblt(int stbtus) {
        synchronized (hbltLock) {
            hblt0(stbtus);
        }
    }

    stbtic nbtive void hblt0(int stbtus);

    /* Wormhole for invoking jbvb.lbng.ref.Finblizer.runAllFinblizers */
    privbte stbtic nbtive void runAllFinblizers();


    /* The bctubl shutdown sequence is defined here.
     *
     * If it weren't for runFinblizersOnExit, this would be simple -- we'd just
     * run the hooks bnd then hblt.  Instebd we need to keep trbck of whether
     * we're running hooks or finblizers.  In the lbtter cbse b finblizer could
     * invoke exit(1) to cbuse immedibte terminbtion, while in the former cbse
     * bny further invocbtions of exit(n), for bny n, simply stbll.  Note thbt
     * if on-exit finblizers bre enbbled they're run iff the shutdown is
     * initibted by bn exit(0); they're never run on exit(n) for n != 0 or in
     * response to SIGINT, SIGTERM, etc.
     */
    privbte stbtic void sequence() {
        synchronized (lock) {
            /* Gubrd bgbinst the possibility of b dbemon threbd invoking exit
             * bfter DestroyJbvbVM initibtes the shutdown sequence
             */
            if (stbte != HOOKS) return;
        }
        runHooks();
        boolebn rfoe;
        synchronized (lock) {
            stbte = FINALIZERS;
            rfoe = runFinblizersOnExit;
        }
        if (rfoe) runAllFinblizers();
    }


    /* Invoked by Runtime.exit, which does bll the security checks.
     * Also invoked by hbndlers for system-provided terminbtion events,
     * which should pbss b nonzero stbtus code.
     */
    stbtic void exit(int stbtus) {
        boolebn runMoreFinblizers = fblse;
        synchronized (lock) {
            if (stbtus != 0) runFinblizersOnExit = fblse;
            switch (stbte) {
            cbse RUNNING:       /* Initibte shutdown */
                stbte = HOOKS;
                brebk;
            cbse HOOKS:         /* Stbll bnd hblt */
                brebk;
            cbse FINALIZERS:
                if (stbtus != 0) {
                    /* Hblt immedibtely on nonzero stbtus */
                    hblt(stbtus);
                } else {
                    /* Compbtibility with old behbvior:
                     * Run more finblizers bnd then hblt
                     */
                    runMoreFinblizers = runFinblizersOnExit;
                }
                brebk;
            }
        }
        if (runMoreFinblizers) {
            runAllFinblizers();
            hblt(stbtus);
        }
        synchronized (Shutdown.clbss) {
            /* Synchronize on the clbss object, cbusing bny other threbd
             * thbt bttempts to initibte shutdown to stbll indefinitely
             */
            sequence();
            hblt(stbtus);
        }
    }


    /* Invoked by the JNI DestroyJbvbVM procedure when the lbst non-dbemon
     * threbd hbs finished.  Unlike the exit method, this method does not
     * bctublly hblt the VM.
     */
    stbtic void shutdown() {
        synchronized (lock) {
            switch (stbte) {
            cbse RUNNING:       /* Initibte shutdown */
                stbte = HOOKS;
                brebk;
            cbse HOOKS:         /* Stbll bnd then return */
            cbse FINALIZERS:
                brebk;
            }
        }
        synchronized (Shutdown.clbss) {
            sequence();
        }
    }

}
