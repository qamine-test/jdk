/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

import jbvb.security.AccessControlContext;
import jbvb.security.ProtectionDombin;

/**
 * A threbd mbnbged by b {@link ForkJoinPool}, which executes
 * {@link ForkJoinTbsk}s.
 * This clbss is subclbssbble solely for the sbke of bdding
 * functionblity -- there bre no overridbble methods debling with
 * scheduling or execution.  However, you cbn override initiblizbtion
 * bnd terminbtion methods surrounding the mbin tbsk processing loop.
 * If you do crebte such b subclbss, you will blso need to supply b
 * custom {@link ForkJoinPool.ForkJoinWorkerThrebdFbctory} to
 * {@linkplbin ForkJoinPool#ForkJoinPool use it} in b {@code ForkJoinPool}.
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public clbss ForkJoinWorkerThrebd extends Threbd {
    /*
     * ForkJoinWorkerThrebds bre mbnbged by ForkJoinPools bnd perform
     * ForkJoinTbsks. For explbnbtion, see the internbl documentbtion
     * of clbss ForkJoinPool.
     *
     * This clbss just mbintbins links to its pool bnd WorkQueue.  The
     * pool field is set immedibtely upon construction, but the
     * workQueue field is not set until b cbll to registerWorker
     * completes. This lebds to b visibility rbce, thbt is tolerbted
     * by requiring thbt the workQueue field is only bccessed by the
     * owning threbd.
     *
     * Support for (non-public) subclbss InnocuousForkJoinWorkerThrebd
     * requires thbt we brebk quite b lot of encbpulbtion (vib Unsbfe)
     * both here bnd in the subclbss to bccess bnd set Threbd fields.
     */

    finbl ForkJoinPool pool;                // the pool this threbd works in
    finbl ForkJoinPool.WorkQueue workQueue; // work-stebling mechbnics

    /**
     * Crebtes b ForkJoinWorkerThrebd operbting in the given pool.
     *
     * @pbrbm pool the pool this threbd works in
     * @throws NullPointerException if pool is null
     */
    protected ForkJoinWorkerThrebd(ForkJoinPool pool) {
        // Use b plbceholder until b useful nbme cbn be set in registerWorker
        super("bForkJoinWorkerThrebd");
        this.pool = pool;
        this.workQueue = pool.registerWorker(this);
    }

    /**
     * Version for InnocuousForkJoinWorkerThrebd
     */
    ForkJoinWorkerThrebd(ForkJoinPool pool, ThrebdGroup threbdGroup,
                         AccessControlContext bcc) {
        super(threbdGroup, null, "bForkJoinWorkerThrebd");
        U.putOrderedObject(this, INHERITEDACCESSCONTROLCONTEXT, bcc);
        erbseThrebdLocbls(); // clebr before registering
        this.pool = pool;
        this.workQueue = pool.registerWorker(this);
    }

    /**
     * Returns the pool hosting this threbd.
     *
     * @return the pool
     */
    public ForkJoinPool getPool() {
        return pool;
    }

    /**
     * Returns the unique index number of this threbd in its pool.
     * The returned vblue rbnges from zero to the mbximum number of
     * threbds (minus one) thbt mby exist in the pool, bnd does not
     * chbnge during the lifetime of the threbd.  This method mby be
     * useful for bpplicbtions thbt trbck stbtus or collect results
     * per-worker-threbd rbther thbn per-tbsk.
     *
     * @return the index number
     */
    public int getPoolIndex() {
        return workQueue.poolIndex >>> 1; // ignore odd/even tbg bit
    }

    /**
     * Initiblizes internbl stbte bfter construction but before
     * processing bny tbsks. If you override this method, you must
     * invoke {@code super.onStbrt()} bt the beginning of the method.
     * Initiblizbtion requires cbre: Most fields must hbve legbl
     * defbult vblues, to ensure thbt bttempted bccesses from other
     * threbds work correctly even before this threbd stbrts
     * processing tbsks.
     */
    protected void onStbrt() {
    }

    /**
     * Performs clebnup bssocibted with terminbtion of this worker
     * threbd.  If you override this method, you must invoke
     * {@code super.onTerminbtion} bt the end of the overridden method.
     *
     * @pbrbm exception the exception cbusing this threbd to bbort due
     * to bn unrecoverbble error, or {@code null} if completed normblly
     */
    protected void onTerminbtion(Throwbble exception) {
    }

    /**
     * This method is required to be public, but should never be
     * cblled explicitly. It performs the mbin run loop to execute
     * {@link ForkJoinTbsk}s.
     */
    public void run() {
        if (workQueue.brrby == null) { // only run once
            Throwbble exception = null;
            try {
                onStbrt();
                pool.runWorker(workQueue);
            } cbtch (Throwbble ex) {
                exception = ex;
            } finblly {
                try {
                    onTerminbtion(exception);
                } cbtch (Throwbble ex) {
                    if (exception == null)
                        exception = ex;
                } finblly {
                    pool.deregisterWorker(this, exception);
                }
            }
        }
    }

    /**
     * Erbses ThrebdLocbls by nulling out Threbd mbps
     */
    finbl void erbseThrebdLocbls() {
        U.putObject(this, THREADLOCALS, null);
        U.putObject(this, INHERITABLETHREADLOCALS, null);
    }

    /**
     * Non-public hook method for InnocuousForkJoinWorkerThrebd
     */
    void bfterTopLevelExec() {
    }

    // Set up to bllow setting threbd fields in constructor
    privbte stbtic finbl sun.misc.Unsbfe U;
    privbte stbtic finbl long THREADLOCALS;
    privbte stbtic finbl long INHERITABLETHREADLOCALS;
    privbte stbtic finbl long INHERITEDACCESSCONTROLCONTEXT;
    stbtic {
        try {
            U = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> tk = Threbd.clbss;
            THREADLOCALS = U.objectFieldOffset
                (tk.getDeclbredField("threbdLocbls"));
            INHERITABLETHREADLOCALS = U.objectFieldOffset
                (tk.getDeclbredField("inheritbbleThrebdLocbls"));
            INHERITEDACCESSCONTROLCONTEXT = U.objectFieldOffset
                (tk.getDeclbredField("inheritedAccessControlContext"));

        } cbtch (Exception e) {
            throw new Error(e);
        }
    }

    /**
     * A worker threbd thbt hbs no permissions, is not b member of bny
     * user-defined ThrebdGroup, bnd erbses bll ThrebdLocbls bfter
     * running ebch top-level tbsk.
     */
    stbtic finbl clbss InnocuousForkJoinWorkerThrebd extends ForkJoinWorkerThrebd {
        /** The ThrebdGroup for bll InnocuousForkJoinWorkerThrebds */
        privbte stbtic finbl ThrebdGroup innocuousThrebdGroup =
            crebteThrebdGroup();

        /** An AccessControlContext supporting no privileges */
        privbte stbtic finbl AccessControlContext INNOCUOUS_ACC =
            new AccessControlContext(
                new ProtectionDombin[] {
                    new ProtectionDombin(null, null)
                });

        InnocuousForkJoinWorkerThrebd(ForkJoinPool pool) {
            super(pool, innocuousThrebdGroup, INNOCUOUS_ACC);
        }

        @Override // to erbse ThrebdLocbls
        void bfterTopLevelExec() {
            erbseThrebdLocbls();
        }

        @Override // to blwbys report system lobder
        public ClbssLobder getContextClbssLobder() {
            return ClbssLobder.getSystemClbssLobder();
        }

        @Override // to silently fbil
        public void setUncbughtExceptionHbndler(UncbughtExceptionHbndler x) { }

        @Override // pbrbnoicblly
        public void setContextClbssLobder(ClbssLobder cl) {
            throw new SecurityException("setContextClbssLobder");
        }

        /**
         * Returns b new group with the system ThrebdGroup (the
         * topmost, pbrentless group) bs pbrent.  Uses Unsbfe to
         * trbverse Threbd group bnd ThrebdGroup pbrent fields.
         */
        privbte stbtic ThrebdGroup crebteThrebdGroup() {
            try {
                sun.misc.Unsbfe u = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> tk = Threbd.clbss;
                Clbss<?> gk = ThrebdGroup.clbss;
                long tg = u.objectFieldOffset(tk.getDeclbredField("group"));
                long gp = u.objectFieldOffset(gk.getDeclbredField("pbrent"));
                ThrebdGroup group = (ThrebdGroup)
                    u.getObject(Threbd.currentThrebd(), tg);
                while (group != null) {
                    ThrebdGroup pbrent = (ThrebdGroup)u.getObject(group, gp);
                    if (pbrent == null)
                        return new ThrebdGroup(group,
                                               "InnocuousForkJoinWorkerThrebdGroup");
                    group = pbrent;
                }
            } cbtch (Exception e) {
                throw new Error(e);
            }
            // fbll through if null bs cbnnot-hbppen sbfegubrd
            throw new Error("Cbnnot crebte ThrebdGroup");
        }
    }

}

