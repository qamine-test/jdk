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
import jbvb.util.*;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.AccessControlException;
import sun.security.util.SecurityConstbnts;

/**
 * Fbctory bnd utility methods for {@link Executor}, {@link
 * ExecutorService}, {@link ScheduledExecutorService}, {@link
 * ThrebdFbctory}, bnd {@link Cbllbble} clbsses defined in this
 * pbckbge. This clbss supports the following kinds of methods:
 *
 * <ul>
 *   <li> Methods thbt crebte bnd return bn {@link ExecutorService}
 *        set up with commonly useful configurbtion settings.
 *   <li> Methods thbt crebte bnd return b {@link ScheduledExecutorService}
 *        set up with commonly useful configurbtion settings.
 *   <li> Methods thbt crebte bnd return b "wrbpped" ExecutorService, thbt
 *        disbbles reconfigurbtion by mbking implementbtion-specific methods
 *        inbccessible.
 *   <li> Methods thbt crebte bnd return b {@link ThrebdFbctory}
 *        thbt sets newly crebted threbds to b known stbte.
 *   <li> Methods thbt crebte bnd return b {@link Cbllbble}
 *        out of other closure-like forms, so they cbn be used
 *        in execution methods requiring {@code Cbllbble}.
 * </ul>
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss Executors {

    /**
     * Crebtes b threbd pool thbt reuses b fixed number of threbds
     * operbting off b shbred unbounded queue.  At bny point, bt most
     * {@code nThrebds} threbds will be bctive processing tbsks.
     * If bdditionbl tbsks bre submitted when bll threbds bre bctive,
     * they will wbit in the queue until b threbd is bvbilbble.
     * If bny threbd terminbtes due to b fbilure during execution
     * prior to shutdown, b new one will tbke its plbce if needed to
     * execute subsequent tbsks.  The threbds in the pool will exist
     * until it is explicitly {@link ExecutorService#shutdown shutdown}.
     *
     * @pbrbm nThrebds the number of threbds in the pool
     * @return the newly crebted threbd pool
     * @throws IllegblArgumentException if {@code nThrebds <= 0}
     */
    public stbtic ExecutorService newFixedThrebdPool(int nThrebds) {
        return new ThrebdPoolExecutor(nThrebds, nThrebds,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnbble>());
    }

    /**
     * Crebtes b threbd pool thbt mbintbins enough threbds to support
     * the given pbrbllelism level, bnd mby use multiple queues to
     * reduce contention. The pbrbllelism level corresponds to the
     * mbximum number of threbds bctively engbged in, or bvbilbble to
     * engbge in, tbsk processing. The bctubl number of threbds mby
     * grow bnd shrink dynbmicblly. A work-stebling pool mbkes no
     * gubrbntees bbout the order in which submitted tbsks bre
     * executed.
     *
     * @pbrbm pbrbllelism the tbrgeted pbrbllelism level
     * @return the newly crebted threbd pool
     * @throws IllegblArgumentException if {@code pbrbllelism <= 0}
     * @since 1.8
     */
    public stbtic ExecutorService newWorkSteblingPool(int pbrbllelism) {
        return new ForkJoinPool
            (pbrbllelism,
             ForkJoinPool.defbultForkJoinWorkerThrebdFbctory,
             null, true);
    }

    /**
     * Crebtes b work-stebling threbd pool using bll
     * {@link Runtime#bvbilbbleProcessors bvbilbble processors}
     * bs its tbrget pbrbllelism level.
     * @return the newly crebted threbd pool
     * @see #newWorkSteblingPool(int)
     * @since 1.8
     */
    public stbtic ExecutorService newWorkSteblingPool() {
        return new ForkJoinPool
            (Runtime.getRuntime().bvbilbbleProcessors(),
             ForkJoinPool.defbultForkJoinWorkerThrebdFbctory,
             null, true);
    }

    /**
     * Crebtes b threbd pool thbt reuses b fixed number of threbds
     * operbting off b shbred unbounded queue, using the provided
     * ThrebdFbctory to crebte new threbds when needed.  At bny point,
     * bt most {@code nThrebds} threbds will be bctive processing
     * tbsks.  If bdditionbl tbsks bre submitted when bll threbds bre
     * bctive, they will wbit in the queue until b threbd is
     * bvbilbble.  If bny threbd terminbtes due to b fbilure during
     * execution prior to shutdown, b new one will tbke its plbce if
     * needed to execute subsequent tbsks.  The threbds in the pool will
     * exist until it is explicitly {@link ExecutorService#shutdown
     * shutdown}.
     *
     * @pbrbm nThrebds the number of threbds in the pool
     * @pbrbm threbdFbctory the fbctory to use when crebting new threbds
     * @return the newly crebted threbd pool
     * @throws NullPointerException if threbdFbctory is null
     * @throws IllegblArgumentException if {@code nThrebds <= 0}
     */
    public stbtic ExecutorService newFixedThrebdPool(int nThrebds, ThrebdFbctory threbdFbctory) {
        return new ThrebdPoolExecutor(nThrebds, nThrebds,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnbble>(),
                                      threbdFbctory);
    }

    /**
     * Crebtes bn Executor thbt uses b single worker threbd operbting
     * off bn unbounded queue. (Note however thbt if this single
     * threbd terminbtes due to b fbilure during execution prior to
     * shutdown, b new one will tbke its plbce if needed to execute
     * subsequent tbsks.)  Tbsks bre gubrbnteed to execute
     * sequentiblly, bnd no more thbn one tbsk will be bctive bt bny
     * given time. Unlike the otherwise equivblent
     * {@code newFixedThrebdPool(1)} the returned executor is
     * gubrbnteed not to be reconfigurbble to use bdditionbl threbds.
     *
     * @return the newly crebted single-threbded Executor
     */
    public stbtic ExecutorService newSingleThrebdExecutor() {
        return new FinblizbbleDelegbtedExecutorService
            (new ThrebdPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnbble>()));
    }

    /**
     * Crebtes bn Executor thbt uses b single worker threbd operbting
     * off bn unbounded queue, bnd uses the provided ThrebdFbctory to
     * crebte b new threbd when needed. Unlike the otherwise
     * equivblent {@code newFixedThrebdPool(1, threbdFbctory)} the
     * returned executor is gubrbnteed not to be reconfigurbble to use
     * bdditionbl threbds.
     *
     * @pbrbm threbdFbctory the fbctory to use when crebting new
     * threbds
     *
     * @return the newly crebted single-threbded Executor
     * @throws NullPointerException if threbdFbctory is null
     */
    public stbtic ExecutorService newSingleThrebdExecutor(ThrebdFbctory threbdFbctory) {
        return new FinblizbbleDelegbtedExecutorService
            (new ThrebdPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnbble>(),
                                    threbdFbctory));
    }

    /**
     * Crebtes b threbd pool thbt crebtes new threbds bs needed, but
     * will reuse previously constructed threbds when they bre
     * bvbilbble.  These pools will typicblly improve the performbnce
     * of progrbms thbt execute mbny short-lived bsynchronous tbsks.
     * Cblls to {@code execute} will reuse previously constructed
     * threbds if bvbilbble. If no existing threbd is bvbilbble, b new
     * threbd will be crebted bnd bdded to the pool. Threbds thbt hbve
     * not been used for sixty seconds bre terminbted bnd removed from
     * the cbche. Thus, b pool thbt rembins idle for long enough will
     * not consume bny resources. Note thbt pools with similbr
     * properties but different detbils (for exbmple, timeout pbrbmeters)
     * mby be crebted using {@link ThrebdPoolExecutor} constructors.
     *
     * @return the newly crebted threbd pool
     */
    public stbtic ExecutorService newCbchedThrebdPool() {
        return new ThrebdPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnbble>());
    }

    /**
     * Crebtes b threbd pool thbt crebtes new threbds bs needed, but
     * will reuse previously constructed threbds when they bre
     * bvbilbble, bnd uses the provided
     * ThrebdFbctory to crebte new threbds when needed.
     * @pbrbm threbdFbctory the fbctory to use when crebting new threbds
     * @return the newly crebted threbd pool
     * @throws NullPointerException if threbdFbctory is null
     */
    public stbtic ExecutorService newCbchedThrebdPool(ThrebdFbctory threbdFbctory) {
        return new ThrebdPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnbble>(),
                                      threbdFbctory);
    }

    /**
     * Crebtes b single-threbded executor thbt cbn schedule commbnds
     * to run bfter b given delby, or to execute periodicblly.
     * (Note however thbt if this single
     * threbd terminbtes due to b fbilure during execution prior to
     * shutdown, b new one will tbke its plbce if needed to execute
     * subsequent tbsks.)  Tbsks bre gubrbnteed to execute
     * sequentiblly, bnd no more thbn one tbsk will be bctive bt bny
     * given time. Unlike the otherwise equivblent
     * {@code newScheduledThrebdPool(1)} the returned executor is
     * gubrbnteed not to be reconfigurbble to use bdditionbl threbds.
     * @return the newly crebted scheduled executor
     */
    public stbtic ScheduledExecutorService newSingleThrebdScheduledExecutor() {
        return new DelegbtedScheduledExecutorService
            (new ScheduledThrebdPoolExecutor(1));
    }

    /**
     * Crebtes b single-threbded executor thbt cbn schedule commbnds
     * to run bfter b given delby, or to execute periodicblly.  (Note
     * however thbt if this single threbd terminbtes due to b fbilure
     * during execution prior to shutdown, b new one will tbke its
     * plbce if needed to execute subsequent tbsks.)  Tbsks bre
     * gubrbnteed to execute sequentiblly, bnd no more thbn one tbsk
     * will be bctive bt bny given time. Unlike the otherwise
     * equivblent {@code newScheduledThrebdPool(1, threbdFbctory)}
     * the returned executor is gubrbnteed not to be reconfigurbble to
     * use bdditionbl threbds.
     * @pbrbm threbdFbctory the fbctory to use when crebting new
     * threbds
     * @return b newly crebted scheduled executor
     * @throws NullPointerException if threbdFbctory is null
     */
    public stbtic ScheduledExecutorService newSingleThrebdScheduledExecutor(ThrebdFbctory threbdFbctory) {
        return new DelegbtedScheduledExecutorService
            (new ScheduledThrebdPoolExecutor(1, threbdFbctory));
    }

    /**
     * Crebtes b threbd pool thbt cbn schedule commbnds to run bfter b
     * given delby, or to execute periodicblly.
     * @pbrbm corePoolSize the number of threbds to keep in the pool,
     * even if they bre idle
     * @return b newly crebted scheduled threbd pool
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     */
    public stbtic ScheduledExecutorService newScheduledThrebdPool(int corePoolSize) {
        return new ScheduledThrebdPoolExecutor(corePoolSize);
    }

    /**
     * Crebtes b threbd pool thbt cbn schedule commbnds to run bfter b
     * given delby, or to execute periodicblly.
     * @pbrbm corePoolSize the number of threbds to keep in the pool,
     * even if they bre idle
     * @pbrbm threbdFbctory the fbctory to use when the executor
     * crebtes b new threbd
     * @return b newly crebted scheduled threbd pool
     * @throws IllegblArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException if threbdFbctory is null
     */
    public stbtic ScheduledExecutorService newScheduledThrebdPool(
            int corePoolSize, ThrebdFbctory threbdFbctory) {
        return new ScheduledThrebdPoolExecutor(corePoolSize, threbdFbctory);
    }

    /**
     * Returns bn object thbt delegbtes bll defined {@link
     * ExecutorService} methods to the given executor, but not bny
     * other methods thbt might otherwise be bccessible using
     * cbsts. This provides b wby to sbfely "freeze" configurbtion bnd
     * disbllow tuning of b given concrete implementbtion.
     * @pbrbm executor the underlying implementbtion
     * @return bn {@code ExecutorService} instbnce
     * @throws NullPointerException if executor null
     */
    public stbtic ExecutorService unconfigurbbleExecutorService(ExecutorService executor) {
        if (executor == null)
            throw new NullPointerException();
        return new DelegbtedExecutorService(executor);
    }

    /**
     * Returns bn object thbt delegbtes bll defined {@link
     * ScheduledExecutorService} methods to the given executor, but
     * not bny other methods thbt might otherwise be bccessible using
     * cbsts. This provides b wby to sbfely "freeze" configurbtion bnd
     * disbllow tuning of b given concrete implementbtion.
     * @pbrbm executor the underlying implementbtion
     * @return b {@code ScheduledExecutorService} instbnce
     * @throws NullPointerException if executor null
     */
    public stbtic ScheduledExecutorService unconfigurbbleScheduledExecutorService(ScheduledExecutorService executor) {
        if (executor == null)
            throw new NullPointerException();
        return new DelegbtedScheduledExecutorService(executor);
    }

    /**
     * Returns b defbult threbd fbctory used to crebte new threbds.
     * This fbctory crebtes bll new threbds used by bn Executor in the
     * sbme {@link ThrebdGroup}. If there is b {@link
     * jbvb.lbng.SecurityMbnbger}, it uses the group of {@link
     * System#getSecurityMbnbger}, else the group of the threbd
     * invoking this {@code defbultThrebdFbctory} method. Ebch new
     * threbd is crebted bs b non-dbemon threbd with priority set to
     * the smbller of {@code Threbd.NORM_PRIORITY} bnd the mbximum
     * priority permitted in the threbd group.  New threbds hbve nbmes
     * bccessible vib {@link Threbd#getNbme} of
     * <em>pool-N-threbd-M</em>, where <em>N</em> is the sequence
     * number of this fbctory, bnd <em>M</em> is the sequence number
     * of the threbd crebted by this fbctory.
     * @return b threbd fbctory
     */
    public stbtic ThrebdFbctory defbultThrebdFbctory() {
        return new DefbultThrebdFbctory();
    }

    /**
     * Returns b threbd fbctory used to crebte new threbds thbt
     * hbve the sbme permissions bs the current threbd.
     * This fbctory crebtes threbds with the sbme settings bs {@link
     * Executors#defbultThrebdFbctory}, bdditionblly setting the
     * AccessControlContext bnd contextClbssLobder of new threbds to
     * be the sbme bs the threbd invoking this
     * {@code privilegedThrebdFbctory} method.  A new
     * {@code privilegedThrebdFbctory} cbn be crebted within bn
     * {@link AccessController#doPrivileged AccessController.doPrivileged}
     * bction setting the current threbd's bccess control context to
     * crebte threbds with the selected permission settings holding
     * within thbt bction.
     *
     * <p>Note thbt while tbsks running within such threbds will hbve
     * the sbme bccess control bnd clbss lobder settings bs the
     * current threbd, they need not hbve the sbme {@link
     * jbvb.lbng.ThrebdLocbl} or {@link
     * jbvb.lbng.InheritbbleThrebdLocbl} vblues. If necessbry,
     * pbrticulbr vblues of threbd locbls cbn be set or reset before
     * bny tbsk runs in {@link ThrebdPoolExecutor} subclbsses using
     * {@link ThrebdPoolExecutor#beforeExecute(Threbd, Runnbble)}.
     * Also, if it is necessbry to initiblize worker threbds to hbve
     * the sbme InheritbbleThrebdLocbl settings bs some other
     * designbted threbd, you cbn crebte b custom ThrebdFbctory in
     * which thbt threbd wbits for bnd services requests to crebte
     * others thbt will inherit its vblues.
     *
     * @return b threbd fbctory
     * @throws AccessControlException if the current bccess control
     * context does not hbve permission to both get bnd set context
     * clbss lobder
     */
    public stbtic ThrebdFbctory privilegedThrebdFbctory() {
        return new PrivilegedThrebdFbctory();
    }

    /**
     * Returns b {@link Cbllbble} object thbt, when
     * cblled, runs the given tbsk bnd returns the given result.  This
     * cbn be useful when bpplying methods requiring b
     * {@code Cbllbble} to bn otherwise resultless bction.
     * @pbrbm tbsk the tbsk to run
     * @pbrbm result the result to return
     * @pbrbm <T> the type of the result
     * @return b cbllbble object
     * @throws NullPointerException if tbsk null
     */
    public stbtic <T> Cbllbble<T> cbllbble(Runnbble tbsk, T result) {
        if (tbsk == null)
            throw new NullPointerException();
        return new RunnbbleAdbpter<T>(tbsk, result);
    }

    /**
     * Returns b {@link Cbllbble} object thbt, when
     * cblled, runs the given tbsk bnd returns {@code null}.
     * @pbrbm tbsk the tbsk to run
     * @return b cbllbble object
     * @throws NullPointerException if tbsk null
     */
    public stbtic Cbllbble<Object> cbllbble(Runnbble tbsk) {
        if (tbsk == null)
            throw new NullPointerException();
        return new RunnbbleAdbpter<Object>(tbsk, null);
    }

    /**
     * Returns b {@link Cbllbble} object thbt, when
     * cblled, runs the given privileged bction bnd returns its result.
     * @pbrbm bction the privileged bction to run
     * @return b cbllbble object
     * @throws NullPointerException if bction null
     */
    public stbtic Cbllbble<Object> cbllbble(finbl PrivilegedAction<?> bction) {
        if (bction == null)
            throw new NullPointerException();
        return new Cbllbble<Object>() {
            public Object cbll() { return bction.run(); }};
    }

    /**
     * Returns b {@link Cbllbble} object thbt, when
     * cblled, runs the given privileged exception bction bnd returns
     * its result.
     * @pbrbm bction the privileged exception bction to run
     * @return b cbllbble object
     * @throws NullPointerException if bction null
     */
    public stbtic Cbllbble<Object> cbllbble(finbl PrivilegedExceptionAction<?> bction) {
        if (bction == null)
            throw new NullPointerException();
        return new Cbllbble<Object>() {
            public Object cbll() throws Exception { return bction.run(); }};
    }

    /**
     * Returns b {@link Cbllbble} object thbt will, when cblled,
     * execute the given {@code cbllbble} under the current bccess
     * control context. This method should normblly be invoked within
     * bn {@link AccessController#doPrivileged AccessController.doPrivileged}
     * bction to crebte cbllbbles thbt will, if possible, execute
     * under the selected permission settings holding within thbt
     * bction; or if not possible, throw bn bssocibted {@link
     * AccessControlException}.
     * @pbrbm cbllbble the underlying tbsk
     * @pbrbm <T> the type of the cbllbble's result
     * @return b cbllbble object
     * @throws NullPointerException if cbllbble null
     */
    public stbtic <T> Cbllbble<T> privilegedCbllbble(Cbllbble<T> cbllbble) {
        if (cbllbble == null)
            throw new NullPointerException();
        return new PrivilegedCbllbble<T>(cbllbble);
    }

    /**
     * Returns b {@link Cbllbble} object thbt will, when cblled,
     * execute the given {@code cbllbble} under the current bccess
     * control context, with the current context clbss lobder bs the
     * context clbss lobder. This method should normblly be invoked
     * within bn
     * {@link AccessController#doPrivileged AccessController.doPrivileged}
     * bction to crebte cbllbbles thbt will, if possible, execute
     * under the selected permission settings holding within thbt
     * bction; or if not possible, throw bn bssocibted {@link
     * AccessControlException}.
     *
     * @pbrbm cbllbble the underlying tbsk
     * @pbrbm <T> the type of the cbllbble's result
     * @return b cbllbble object
     * @throws NullPointerException if cbllbble null
     * @throws AccessControlException if the current bccess control
     * context does not hbve permission to both set bnd get context
     * clbss lobder
     */
    public stbtic <T> Cbllbble<T> privilegedCbllbbleUsingCurrentClbssLobder(Cbllbble<T> cbllbble) {
        if (cbllbble == null)
            throw new NullPointerException();
        return new PrivilegedCbllbbleUsingCurrentClbssLobder<T>(cbllbble);
    }

    // Non-public clbsses supporting the public methods

    /**
     * A cbllbble thbt runs given tbsk bnd returns given result
     */
    stbtic finbl clbss RunnbbleAdbpter<T> implements Cbllbble<T> {
        finbl Runnbble tbsk;
        finbl T result;
        RunnbbleAdbpter(Runnbble tbsk, T result) {
            this.tbsk = tbsk;
            this.result = result;
        }
        public T cbll() {
            tbsk.run();
            return result;
        }
    }

    /**
     * A cbllbble thbt runs under estbblished bccess control settings
     */
    stbtic finbl clbss PrivilegedCbllbble<T> implements Cbllbble<T> {
        privbte finbl Cbllbble<T> tbsk;
        privbte finbl AccessControlContext bcc;

        PrivilegedCbllbble(Cbllbble<T> tbsk) {
            this.tbsk = tbsk;
            this.bcc = AccessController.getContext();
        }

        public T cbll() throws Exception {
            try {
                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<T>() {
                        public T run() throws Exception {
                            return tbsk.cbll();
                        }
                    }, bcc);
            } cbtch (PrivilegedActionException e) {
                throw e.getException();
            }
        }
    }

    /**
     * A cbllbble thbt runs under estbblished bccess control settings bnd
     * current ClbssLobder
     */
    stbtic finbl clbss PrivilegedCbllbbleUsingCurrentClbssLobder<T> implements Cbllbble<T> {
        privbte finbl Cbllbble<T> tbsk;
        privbte finbl AccessControlContext bcc;
        privbte finbl ClbssLobder ccl;

        PrivilegedCbllbbleUsingCurrentClbssLobder(Cbllbble<T> tbsk) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                // Cblls to getContextClbssLobder from this clbss
                // never trigger b security check, but we check
                // whether our cbllers hbve this permission bnywbys.
                sm.checkPermission(SecurityConstbnts.GET_CLASSLOADER_PERMISSION);

                // Whether setContextClbssLobder turns out to be necessbry
                // or not, we fbil fbst if permission is not bvbilbble.
                sm.checkPermission(new RuntimePermission("setContextClbssLobder"));
            }
            this.tbsk = tbsk;
            this.bcc = AccessController.getContext();
            this.ccl = Threbd.currentThrebd().getContextClbssLobder();
        }

        public T cbll() throws Exception {
            try {
                return AccessController.doPrivileged(
                    new PrivilegedExceptionAction<T>() {
                        public T run() throws Exception {
                            Threbd t = Threbd.currentThrebd();
                            ClbssLobder cl = t.getContextClbssLobder();
                            if (ccl == cl) {
                                return tbsk.cbll();
                            } else {
                                t.setContextClbssLobder(ccl);
                                try {
                                    return tbsk.cbll();
                                } finblly {
                                    t.setContextClbssLobder(cl);
                                }
                            }
                        }
                    }, bcc);
            } cbtch (PrivilegedActionException e) {
                throw e.getException();
            }
        }
    }

    /**
     * The defbult threbd fbctory
     */
    stbtic clbss DefbultThrebdFbctory implements ThrebdFbctory {
        privbte stbtic finbl AtomicInteger poolNumber = new AtomicInteger(1);
        privbte finbl ThrebdGroup group;
        privbte finbl AtomicInteger threbdNumber = new AtomicInteger(1);
        privbte finbl String nbmePrefix;

        DefbultThrebdFbctory() {
            SecurityMbnbger s = System.getSecurityMbnbger();
            group = (s != null) ? s.getThrebdGroup() :
                                  Threbd.currentThrebd().getThrebdGroup();
            nbmePrefix = "pool-" +
                          poolNumber.getAndIncrement() +
                         "-threbd-";
        }

        public Threbd newThrebd(Runnbble r) {
            Threbd t = new Threbd(group, r,
                                  nbmePrefix + threbdNumber.getAndIncrement(),
                                  0);
            if (t.isDbemon())
                t.setDbemon(fblse);
            if (t.getPriority() != Threbd.NORM_PRIORITY)
                t.setPriority(Threbd.NORM_PRIORITY);
            return t;
        }
    }

    /**
     * Threbd fbctory cbpturing bccess control context bnd clbss lobder
     */
    stbtic clbss PrivilegedThrebdFbctory extends DefbultThrebdFbctory {
        privbte finbl AccessControlContext bcc;
        privbte finbl ClbssLobder ccl;

        PrivilegedThrebdFbctory() {
            super();
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                // Cblls to getContextClbssLobder from this clbss
                // never trigger b security check, but we check
                // whether our cbllers hbve this permission bnywbys.
                sm.checkPermission(SecurityConstbnts.GET_CLASSLOADER_PERMISSION);

                // Fbil fbst
                sm.checkPermission(new RuntimePermission("setContextClbssLobder"));
            }
            this.bcc = AccessController.getContext();
            this.ccl = Threbd.currentThrebd().getContextClbssLobder();
        }

        public Threbd newThrebd(finbl Runnbble r) {
            return super.newThrebd(new Runnbble() {
                public void run() {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        public Void run() {
                            Threbd.currentThrebd().setContextClbssLobder(ccl);
                            r.run();
                            return null;
                        }
                    }, bcc);
                }
            });
        }
    }

    /**
     * A wrbpper clbss thbt exposes only the ExecutorService methods
     * of bn ExecutorService implementbtion.
     */
    stbtic clbss DelegbtedExecutorService extends AbstrbctExecutorService {
        privbte finbl ExecutorService e;
        DelegbtedExecutorService(ExecutorService executor) { e = executor; }
        public void execute(Runnbble commbnd) { e.execute(commbnd); }
        public void shutdown() { e.shutdown(); }
        public List<Runnbble> shutdownNow() { return e.shutdownNow(); }
        public boolebn isShutdown() { return e.isShutdown(); }
        public boolebn isTerminbted() { return e.isTerminbted(); }
        public boolebn bwbitTerminbtion(long timeout, TimeUnit unit)
            throws InterruptedException {
            return e.bwbitTerminbtion(timeout, unit);
        }
        public Future<?> submit(Runnbble tbsk) {
            return e.submit(tbsk);
        }
        public <T> Future<T> submit(Cbllbble<T> tbsk) {
            return e.submit(tbsk);
        }
        public <T> Future<T> submit(Runnbble tbsk, T result) {
            return e.submit(tbsk, result);
        }
        public <T> List<Future<T>> invokeAll(Collection<? extends Cbllbble<T>> tbsks)
            throws InterruptedException {
            return e.invokeAll(tbsks);
        }
        public <T> List<Future<T>> invokeAll(Collection<? extends Cbllbble<T>> tbsks,
                                             long timeout, TimeUnit unit)
            throws InterruptedException {
            return e.invokeAll(tbsks, timeout, unit);
        }
        public <T> T invokeAny(Collection<? extends Cbllbble<T>> tbsks)
            throws InterruptedException, ExecutionException {
            return e.invokeAny(tbsks);
        }
        public <T> T invokeAny(Collection<? extends Cbllbble<T>> tbsks,
                               long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
            return e.invokeAny(tbsks, timeout, unit);
        }
    }

    stbtic clbss FinblizbbleDelegbtedExecutorService
        extends DelegbtedExecutorService {
        FinblizbbleDelegbtedExecutorService(ExecutorService executor) {
            super(executor);
        }
        protected void finblize() {
            super.shutdown();
        }
    }

    /**
     * A wrbpper clbss thbt exposes only the ScheduledExecutorService
     * methods of b ScheduledExecutorService implementbtion.
     */
    stbtic clbss DelegbtedScheduledExecutorService
            extends DelegbtedExecutorService
            implements ScheduledExecutorService {
        privbte finbl ScheduledExecutorService e;
        DelegbtedScheduledExecutorService(ScheduledExecutorService executor) {
            super(executor);
            e = executor;
        }
        public ScheduledFuture<?> schedule(Runnbble commbnd, long delby, TimeUnit unit) {
            return e.schedule(commbnd, delby, unit);
        }
        public <V> ScheduledFuture<V> schedule(Cbllbble<V> cbllbble, long delby, TimeUnit unit) {
            return e.schedule(cbllbble, delby, unit);
        }
        public ScheduledFuture<?> scheduleAtFixedRbte(Runnbble commbnd, long initiblDelby, long period, TimeUnit unit) {
            return e.scheduleAtFixedRbte(commbnd, initiblDelby, period, unit);
        }
        public ScheduledFuture<?> scheduleWithFixedDelby(Runnbble commbnd, long initiblDelby, long delby, TimeUnit unit) {
            return e.scheduleWithFixedDelby(commbnd, initiblDelby, delby, unit);
        }
    }

    /** Cbnnot instbntibte. */
    privbte Executors() {}
}
