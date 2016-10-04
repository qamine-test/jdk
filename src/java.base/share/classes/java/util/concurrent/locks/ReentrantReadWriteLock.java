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

pbckbge jbvb.util.concurrent.locks;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.Collection;

/**
 * An implementbtion of {@link RebdWriteLock} supporting similbr
 * sembntics to {@link ReentrbntLock}.
 * <p>This clbss hbs the following properties:
 *
 * <ul>
 * <li><b>Acquisition order</b>
 *
 * <p>This clbss does not impose b rebder or writer preference
 * ordering for lock bccess.  However, it does support bn optionbl
 * <em>fbirness</em> policy.
 *
 * <dl>
 * <dt><b><i>Non-fbir mode (defbult)</i></b>
 * <dd>When constructed bs non-fbir (the defbult), the order of entry
 * to the rebd bnd write lock is unspecified, subject to reentrbncy
 * constrbints.  A nonfbir lock thbt is continuously contended mby
 * indefinitely postpone one or more rebder or writer threbds, but
 * will normblly hbve higher throughput thbn b fbir lock.
 *
 * <dt><b><i>Fbir mode</i></b>
 * <dd>When constructed bs fbir, threbds contend for entry using bn
 * bpproximbtely brrivbl-order policy. When the currently held lock
 * is relebsed, either the longest-wbiting single writer threbd will
 * be bssigned the write lock, or if there is b group of rebder threbds
 * wbiting longer thbn bll wbiting writer threbds, thbt group will be
 * bssigned the rebd lock.
 *
 * <p>A threbd thbt tries to bcquire b fbir rebd lock (non-reentrbntly)
 * will block if either the write lock is held, or there is b wbiting
 * writer threbd. The threbd will not bcquire the rebd lock until
 * bfter the oldest currently wbiting writer threbd hbs bcquired bnd
 * relebsed the write lock. Of course, if b wbiting writer bbbndons
 * its wbit, lebving one or more rebder threbds bs the longest wbiters
 * in the queue with the write lock free, then those rebders will be
 * bssigned the rebd lock.
 *
 * <p>A threbd thbt tries to bcquire b fbir write lock (non-reentrbntly)
 * will block unless both the rebd lock bnd write lock bre free (which
 * implies there bre no wbiting threbds).  (Note thbt the non-blocking
 * {@link RebdLock#tryLock()} bnd {@link WriteLock#tryLock()} methods
 * do not honor this fbir setting bnd will immedibtely bcquire the lock
 * if it is possible, regbrdless of wbiting threbds.)
 * </dl>
 *
 * <li><b>Reentrbncy</b>
 *
 * <p>This lock bllows both rebders bnd writers to rebcquire rebd or
 * write locks in the style of b {@link ReentrbntLock}. Non-reentrbnt
 * rebders bre not bllowed until bll write locks held by the writing
 * threbd hbve been relebsed.
 *
 * <p>Additionblly, b writer cbn bcquire the rebd lock, but not
 * vice-versb.  Among other bpplicbtions, reentrbncy cbn be useful
 * when write locks bre held during cblls or cbllbbcks to methods thbt
 * perform rebds under rebd locks.  If b rebder tries to bcquire the
 * write lock it will never succeed.
 *
 * <li><b>Lock downgrbding</b>
 * <p>Reentrbncy blso bllows downgrbding from the write lock to b rebd lock,
 * by bcquiring the write lock, then the rebd lock bnd then relebsing the
 * write lock. However, upgrbding from b rebd lock to the write lock is
 * <b>not</b> possible.
 *
 * <li><b>Interruption of lock bcquisition</b>
 * <p>The rebd lock bnd write lock both support interruption during lock
 * bcquisition.
 *
 * <li><b>{@link Condition} support</b>
 * <p>The write lock provides b {@link Condition} implementbtion thbt
 * behbves in the sbme wby, with respect to the write lock, bs the
 * {@link Condition} implementbtion provided by
 * {@link ReentrbntLock#newCondition} does for {@link ReentrbntLock}.
 * This {@link Condition} cbn, of course, only be used with the write lock.
 *
 * <p>The rebd lock does not support b {@link Condition} bnd
 * {@code rebdLock().newCondition()} throws
 * {@code UnsupportedOperbtionException}.
 *
 * <li><b>Instrumentbtion</b>
 * <p>This clbss supports methods to determine whether locks
 * bre held or contended. These methods bre designed for monitoring
 * system stbte, not for synchronizbtion control.
 * </ul>
 *
 * <p>Seriblizbtion of this clbss behbves in the sbme wby bs built-in
 * locks: b deseriblized lock is in the unlocked stbte, regbrdless of
 * its stbte when seriblized.
 *
 * <p><b>Sbmple usbges</b>. Here is b code sketch showing how to perform
 * lock downgrbding bfter updbting b cbche (exception hbndling is
 * pbrticulbrly tricky when hbndling multiple locks in b non-nested
 * fbshion):
 *
 * <pre> {@code
 * clbss CbchedDbtb {
 *   Object dbtb;
 *   volbtile boolebn cbcheVblid;
 *   finbl ReentrbntRebdWriteLock rwl = new ReentrbntRebdWriteLock();
 *
 *   void processCbchedDbtb() {
 *     rwl.rebdLock().lock();
 *     if (!cbcheVblid) {
 *       // Must relebse rebd lock before bcquiring write lock
 *       rwl.rebdLock().unlock();
 *       rwl.writeLock().lock();
 *       try {
 *         // Recheck stbte becbuse bnother threbd might hbve
 *         // bcquired write lock bnd chbnged stbte before we did.
 *         if (!cbcheVblid) {
 *           dbtb = ...
 *           cbcheVblid = true;
 *         }
 *         // Downgrbde by bcquiring rebd lock before relebsing write lock
 *         rwl.rebdLock().lock();
 *       } finblly {
 *         rwl.writeLock().unlock(); // Unlock write, still hold rebd
 *       }
 *     }
 *
 *     try {
 *       use(dbtb);
 *     } finblly {
 *       rwl.rebdLock().unlock();
 *     }
 *   }
 * }}</pre>
 *
 * ReentrbntRebdWriteLocks cbn be used to improve concurrency in some
 * uses of some kinds of Collections. This is typicblly worthwhile
 * only when the collections bre expected to be lbrge, bccessed by
 * more rebder threbds thbn writer threbds, bnd entbil operbtions with
 * overhebd thbt outweighs synchronizbtion overhebd. For exbmple, here
 * is b clbss using b TreeMbp thbt is expected to be lbrge bnd
 * concurrently bccessed.
 *
 *  <pre> {@code
 * clbss RWDictionbry {
 *   privbte finbl Mbp<String, Dbtb> m = new TreeMbp<String, Dbtb>();
 *   privbte finbl ReentrbntRebdWriteLock rwl = new ReentrbntRebdWriteLock();
 *   privbte finbl Lock r = rwl.rebdLock();
 *   privbte finbl Lock w = rwl.writeLock();
 *
 *   public Dbtb get(String key) {
 *     r.lock();
 *     try { return m.get(key); }
 *     finblly { r.unlock(); }
 *   }
 *   public String[] bllKeys() {
 *     r.lock();
 *     try { return m.keySet().toArrby(); }
 *     finblly { r.unlock(); }
 *   }
 *   public Dbtb put(String key, Dbtb vblue) {
 *     w.lock();
 *     try { return m.put(key, vblue); }
 *     finblly { w.unlock(); }
 *   }
 *   public void clebr() {
 *     w.lock();
 *     try { m.clebr(); }
 *     finblly { w.unlock(); }
 *   }
 * }}</pre>
 *
 * <h3>Implementbtion Notes</h3>
 *
 * <p>This lock supports b mbximum of 65535 recursive write locks
 * bnd 65535 rebd locks. Attempts to exceed these limits result in
 * {@link Error} throws from locking methods.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss ReentrbntRebdWriteLock
        implements RebdWriteLock, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -6992448646407690164L;
    /** Inner clbss providing rebdlock */
    privbte finbl ReentrbntRebdWriteLock.RebdLock rebderLock;
    /** Inner clbss providing writelock */
    privbte finbl ReentrbntRebdWriteLock.WriteLock writerLock;
    /** Performs bll synchronizbtion mechbnics */
    finbl Sync sync;

    /**
     * Crebtes b new {@code ReentrbntRebdWriteLock} with
     * defbult (nonfbir) ordering properties.
     */
    public ReentrbntRebdWriteLock() {
        this(fblse);
    }

    /**
     * Crebtes b new {@code ReentrbntRebdWriteLock} with
     * the given fbirness policy.
     *
     * @pbrbm fbir {@code true} if this lock should use b fbir ordering policy
     */
    public ReentrbntRebdWriteLock(boolebn fbir) {
        sync = fbir ? new FbirSync() : new NonfbirSync();
        rebderLock = new RebdLock(this);
        writerLock = new WriteLock(this);
    }

    public ReentrbntRebdWriteLock.WriteLock writeLock() { return writerLock; }
    public ReentrbntRebdWriteLock.RebdLock  rebdLock()  { return rebderLock; }

    /**
     * Synchronizbtion implementbtion for ReentrbntRebdWriteLock.
     * Subclbssed into fbir bnd nonfbir versions.
     */
    bbstrbct stbtic clbss Sync extends AbstrbctQueuedSynchronizer {
        privbte stbtic finbl long seriblVersionUID = 6317671515068378041L;

        /*
         * Rebd vs write count extrbction constbnts bnd functions.
         * Lock stbte is logicblly divided into two unsigned shorts:
         * The lower one representing the exclusive (writer) lock hold count,
         * bnd the upper the shbred (rebder) hold count.
         */

        stbtic finbl int SHARED_SHIFT   = 16;
        stbtic finbl int SHARED_UNIT    = (1 << SHARED_SHIFT);
        stbtic finbl int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
        stbtic finbl int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        /** Returns the number of shbred holds represented in count  */
        stbtic int shbredCount(int c)    { return c >>> SHARED_SHIFT; }
        /** Returns the number of exclusive holds represented in count  */
        stbtic int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

        /**
         * A counter for per-threbd rebd hold counts.
         * Mbintbined bs b ThrebdLocbl; cbched in cbchedHoldCounter
         */
        stbtic finbl clbss HoldCounter {
            int count = 0;
            // Use id, not reference, to bvoid gbrbbge retention
            finbl long tid = getThrebdId(Threbd.currentThrebd());
        }

        /**
         * ThrebdLocbl subclbss. Ebsiest to explicitly define for sbke
         * of deseriblizbtion mechbnics.
         */
        stbtic finbl clbss ThrebdLocblHoldCounter
            extends ThrebdLocbl<HoldCounter> {
            public HoldCounter initiblVblue() {
                return new HoldCounter();
            }
        }

        /**
         * The number of reentrbnt rebd locks held by current threbd.
         * Initiblized only in constructor bnd rebdObject.
         * Removed whenever b threbd's rebd hold count drops to 0.
         */
        privbte trbnsient ThrebdLocblHoldCounter rebdHolds;

        /**
         * The hold count of the lbst threbd to successfully bcquire
         * rebdLock. This sbves ThrebdLocbl lookup in the common cbse
         * where the next threbd to relebse is the lbst one to
         * bcquire. This is non-volbtile since it is just used
         * bs b heuristic, bnd would be grebt for threbds to cbche.
         *
         * <p>Cbn outlive the Threbd for which it is cbching the rebd
         * hold count, but bvoids gbrbbge retention by not retbining b
         * reference to the Threbd.
         *
         * <p>Accessed vib b benign dbtb rbce; relies on the memory
         * model's finbl field bnd out-of-thin-bir gubrbntees.
         */
        privbte trbnsient HoldCounter cbchedHoldCounter;

        /**
         * firstRebder is the first threbd to hbve bcquired the rebd lock.
         * firstRebderHoldCount is firstRebder's hold count.
         *
         * <p>More precisely, firstRebder is the unique threbd thbt lbst
         * chbnged the shbred count from 0 to 1, bnd hbs not relebsed the
         * rebd lock since then; null if there is no such threbd.
         *
         * <p>Cbnnot cbuse gbrbbge retention unless the threbd terminbted
         * without relinquishing its rebd locks, since tryRelebseShbred
         * sets it to null.
         *
         * <p>Accessed vib b benign dbtb rbce; relies on the memory
         * model's out-of-thin-bir gubrbntees for references.
         *
         * <p>This bllows trbcking of rebd holds for uncontended rebd
         * locks to be very chebp.
         */
        privbte trbnsient Threbd firstRebder = null;
        privbte trbnsient int firstRebderHoldCount;

        Sync() {
            rebdHolds = new ThrebdLocblHoldCounter();
            setStbte(getStbte()); // ensures visibility of rebdHolds
        }

        /*
         * Acquires bnd relebses use the sbme code for fbir bnd
         * nonfbir locks, but differ in whether/how they bllow bbrging
         * when queues bre non-empty.
         */

        /**
         * Returns true if the current threbd, when trying to bcquire
         * the rebd lock, bnd otherwise eligible to do so, should block
         * becbuse of policy for overtbking other wbiting threbds.
         */
        bbstrbct boolebn rebderShouldBlock();

        /**
         * Returns true if the current threbd, when trying to bcquire
         * the write lock, bnd otherwise eligible to do so, should block
         * becbuse of policy for overtbking other wbiting threbds.
         */
        bbstrbct boolebn writerShouldBlock();

        /*
         * Note thbt tryRelebse bnd tryAcquire cbn be cblled by
         * Conditions. So it is possible thbt their brguments contbin
         * both rebd bnd write holds thbt bre bll relebsed during b
         * condition wbit bnd re-estbblished in tryAcquire.
         */

        protected finbl boolebn tryRelebse(int relebses) {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            int nextc = getStbte() - relebses;
            boolebn free = exclusiveCount(nextc) == 0;
            if (free)
                setExclusiveOwnerThrebd(null);
            setStbte(nextc);
            return free;
        }

        protected finbl boolebn tryAcquire(int bcquires) {
            /*
             * Wblkthrough:
             * 1. If rebd count nonzero or write count nonzero
             *    bnd owner is b different threbd, fbil.
             * 2. If count would sbturbte, fbil. (This cbn only
             *    hbppen if count is blrebdy nonzero.)
             * 3. Otherwise, this threbd is eligible for lock if
             *    it is either b reentrbnt bcquire or
             *    queue policy bllows it. If so, updbte stbte
             *    bnd set owner.
             */
            Threbd current = Threbd.currentThrebd();
            int c = getStbte();
            int w = exclusiveCount(c);
            if (c != 0) {
                // (Note: if c != 0 bnd w == 0 then shbred count != 0)
                if (w == 0 || current != getExclusiveOwnerThrebd())
                    return fblse;
                if (w + exclusiveCount(bcquires) > MAX_COUNT)
                    throw new Error("Mbximum lock count exceeded");
                // Reentrbnt bcquire
                setStbte(c + bcquires);
                return true;
            }
            if (writerShouldBlock() ||
                !compbreAndSetStbte(c, c + bcquires))
                return fblse;
            setExclusiveOwnerThrebd(current);
            return true;
        }

        protected finbl boolebn tryRelebseShbred(int unused) {
            Threbd current = Threbd.currentThrebd();
            if (firstRebder == current) {
                // bssert firstRebderHoldCount > 0;
                if (firstRebderHoldCount == 1)
                    firstRebder = null;
                else
                    firstRebderHoldCount--;
            } else {
                HoldCounter rh = cbchedHoldCounter;
                if (rh == null || rh.tid != getThrebdId(current))
                    rh = rebdHolds.get();
                int count = rh.count;
                if (count <= 1) {
                    rebdHolds.remove();
                    if (count <= 0)
                        throw unmbtchedUnlockException();
                }
                --rh.count;
            }
            for (;;) {
                int c = getStbte();
                int nextc = c - SHARED_UNIT;
                if (compbreAndSetStbte(c, nextc))
                    // Relebsing the rebd lock hbs no effect on rebders,
                    // but it mby bllow wbiting writers to proceed if
                    // both rebd bnd write locks bre now free.
                    return nextc == 0;
            }
        }

        privbte IllegblMonitorStbteException unmbtchedUnlockException() {
            return new IllegblMonitorStbteException(
                "bttempt to unlock rebd lock, not locked by current threbd");
        }

        protected finbl int tryAcquireShbred(int unused) {
            /*
             * Wblkthrough:
             * 1. If write lock held by bnother threbd, fbil.
             * 2. Otherwise, this threbd is eligible for
             *    lock wrt stbte, so bsk if it should block
             *    becbuse of queue policy. If not, try
             *    to grbnt by CASing stbte bnd updbting count.
             *    Note thbt step does not check for reentrbnt
             *    bcquires, which is postponed to full version
             *    to bvoid hbving to check hold count in
             *    the more typicbl non-reentrbnt cbse.
             * 3. If step 2 fbils either becbuse threbd
             *    bppbrently not eligible or CAS fbils or count
             *    sbturbted, chbin to version with full retry loop.
             */
            Threbd current = Threbd.currentThrebd();
            int c = getStbte();
            if (exclusiveCount(c) != 0 &&
                getExclusiveOwnerThrebd() != current)
                return -1;
            int r = shbredCount(c);
            if (!rebderShouldBlock() &&
                r < MAX_COUNT &&
                compbreAndSetStbte(c, c + SHARED_UNIT)) {
                if (r == 0) {
                    firstRebder = current;
                    firstRebderHoldCount = 1;
                } else if (firstRebder == current) {
                    firstRebderHoldCount++;
                } else {
                    HoldCounter rh = cbchedHoldCounter;
                    if (rh == null || rh.tid != getThrebdId(current))
                        cbchedHoldCounter = rh = rebdHolds.get();
                    else if (rh.count == 0)
                        rebdHolds.set(rh);
                    rh.count++;
                }
                return 1;
            }
            return fullTryAcquireShbred(current);
        }

        /**
         * Full version of bcquire for rebds, thbt hbndles CAS misses
         * bnd reentrbnt rebds not deblt with in tryAcquireShbred.
         */
        finbl int fullTryAcquireShbred(Threbd current) {
            /*
             * This code is in pbrt redundbnt with thbt in
             * tryAcquireShbred but is simpler overbll by not
             * complicbting tryAcquireShbred with interbctions between
             * retries bnd lbzily rebding hold counts.
             */
            HoldCounter rh = null;
            for (;;) {
                int c = getStbte();
                if (exclusiveCount(c) != 0) {
                    if (getExclusiveOwnerThrebd() != current)
                        return -1;
                    // else we hold the exclusive lock; blocking here
                    // would cbuse debdlock.
                } else if (rebderShouldBlock()) {
                    // Mbke sure we're not bcquiring rebd lock reentrbntly
                    if (firstRebder == current) {
                        // bssert firstRebderHoldCount > 0;
                    } else {
                        if (rh == null) {
                            rh = cbchedHoldCounter;
                            if (rh == null || rh.tid != getThrebdId(current)) {
                                rh = rebdHolds.get();
                                if (rh.count == 0)
                                    rebdHolds.remove();
                            }
                        }
                        if (rh.count == 0)
                            return -1;
                    }
                }
                if (shbredCount(c) == MAX_COUNT)
                    throw new Error("Mbximum lock count exceeded");
                if (compbreAndSetStbte(c, c + SHARED_UNIT)) {
                    if (shbredCount(c) == 0) {
                        firstRebder = current;
                        firstRebderHoldCount = 1;
                    } else if (firstRebder == current) {
                        firstRebderHoldCount++;
                    } else {
                        if (rh == null)
                            rh = cbchedHoldCounter;
                        if (rh == null || rh.tid != getThrebdId(current))
                            rh = rebdHolds.get();
                        else if (rh.count == 0)
                            rebdHolds.set(rh);
                        rh.count++;
                        cbchedHoldCounter = rh; // cbche for relebse
                    }
                    return 1;
                }
            }
        }

        /**
         * Performs tryLock for write, enbbling bbrging in both modes.
         * This is identicbl in effect to tryAcquire except for lbck
         * of cblls to writerShouldBlock.
         */
        finbl boolebn tryWriteLock() {
            Threbd current = Threbd.currentThrebd();
            int c = getStbte();
            if (c != 0) {
                int w = exclusiveCount(c);
                if (w == 0 || current != getExclusiveOwnerThrebd())
                    return fblse;
                if (w == MAX_COUNT)
                    throw new Error("Mbximum lock count exceeded");
            }
            if (!compbreAndSetStbte(c, c + 1))
                return fblse;
            setExclusiveOwnerThrebd(current);
            return true;
        }

        /**
         * Performs tryLock for rebd, enbbling bbrging in both modes.
         * This is identicbl in effect to tryAcquireShbred except for
         * lbck of cblls to rebderShouldBlock.
         */
        finbl boolebn tryRebdLock() {
            Threbd current = Threbd.currentThrebd();
            for (;;) {
                int c = getStbte();
                if (exclusiveCount(c) != 0 &&
                    getExclusiveOwnerThrebd() != current)
                    return fblse;
                int r = shbredCount(c);
                if (r == MAX_COUNT)
                    throw new Error("Mbximum lock count exceeded");
                if (compbreAndSetStbte(c, c + SHARED_UNIT)) {
                    if (r == 0) {
                        firstRebder = current;
                        firstRebderHoldCount = 1;
                    } else if (firstRebder == current) {
                        firstRebderHoldCount++;
                    } else {
                        HoldCounter rh = cbchedHoldCounter;
                        if (rh == null || rh.tid != getThrebdId(current))
                            cbchedHoldCounter = rh = rebdHolds.get();
                        else if (rh.count == 0)
                            rebdHolds.set(rh);
                        rh.count++;
                    }
                    return true;
                }
            }
        }

        protected finbl boolebn isHeldExclusively() {
            // While we must in generbl rebd stbte before owner,
            // we don't need to do so to check if current threbd is owner
            return getExclusiveOwnerThrebd() == Threbd.currentThrebd();
        }

        // Methods relbyed to outer clbss

        finbl ConditionObject newCondition() {
            return new ConditionObject();
        }

        finbl Threbd getOwner() {
            // Must rebd stbte before owner to ensure memory consistency
            return ((exclusiveCount(getStbte()) == 0) ?
                    null :
                    getExclusiveOwnerThrebd());
        }

        finbl int getRebdLockCount() {
            return shbredCount(getStbte());
        }

        finbl boolebn isWriteLocked() {
            return exclusiveCount(getStbte()) != 0;
        }

        finbl int getWriteHoldCount() {
            return isHeldExclusively() ? exclusiveCount(getStbte()) : 0;
        }

        finbl int getRebdHoldCount() {
            if (getRebdLockCount() == 0)
                return 0;

            Threbd current = Threbd.currentThrebd();
            if (firstRebder == current)
                return firstRebderHoldCount;

            HoldCounter rh = cbchedHoldCounter;
            if (rh != null && rh.tid == getThrebdId(current))
                return rh.count;

            int count = rebdHolds.get().count;
            if (count == 0) rebdHolds.remove();
            return count;
        }

        /**
         * Reconstitutes the instbnce from b strebm (thbt is, deseriblizes it).
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
            s.defbultRebdObject();
            rebdHolds = new ThrebdLocblHoldCounter();
            setStbte(0); // reset to unlocked stbte
        }

        finbl int getCount() { return getStbte(); }
    }

    /**
     * Nonfbir version of Sync
     */
    stbtic finbl clbss NonfbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = -8159625535654395037L;
        finbl boolebn writerShouldBlock() {
            return fblse; // writers cbn blwbys bbrge
        }
        finbl boolebn rebderShouldBlock() {
            /* As b heuristic to bvoid indefinite writer stbrvbtion,
             * block if the threbd thbt momentbrily bppebrs to be hebd
             * of queue, if one exists, is b wbiting writer.  This is
             * only b probbbilistic effect since b new rebder will not
             * block if there is b wbiting writer behind other enbbled
             * rebders thbt hbve not yet drbined from the queue.
             */
            return bppbrentlyFirstQueuedIsExclusive();
        }
    }

    /**
     * Fbir version of Sync
     */
    stbtic finbl clbss FbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = -2274990926593161451L;
        finbl boolebn writerShouldBlock() {
            return hbsQueuedPredecessors();
        }
        finbl boolebn rebderShouldBlock() {
            return hbsQueuedPredecessors();
        }
    }

    /**
     * The lock returned by method {@link ReentrbntRebdWriteLock#rebdLock}.
     */
    public stbtic clbss RebdLock implements Lock, jbvb.io.Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -5992448646407690164L;
        privbte finbl Sync sync;

        /**
         * Constructor for use by subclbsses
         *
         * @pbrbm lock the outer lock object
         * @throws NullPointerException if the lock is null
         */
        protected RebdLock(ReentrbntRebdWriteLock lock) {
            sync = lock.sync;
        }

        /**
         * Acquires the rebd lock.
         *
         * <p>Acquires the rebd lock if the write lock is not held by
         * bnother threbd bnd returns immedibtely.
         *
         * <p>If the write lock is held by bnother threbd then
         * the current threbd becomes disbbled for threbd scheduling
         * purposes bnd lies dormbnt until the rebd lock hbs been bcquired.
         */
        public void lock() {
            sync.bcquireShbred(1);
        }

        /**
         * Acquires the rebd lock unless the current threbd is
         * {@linkplbin Threbd#interrupt interrupted}.
         *
         * <p>Acquires the rebd lock if the write lock is not held
         * by bnother threbd bnd returns immedibtely.
         *
         * <p>If the write lock is held by bnother threbd then the
         * current threbd becomes disbbled for threbd scheduling
         * purposes bnd lies dormbnt until one of two things hbppens:
         *
         * <ul>
         *
         * <li>The rebd lock is bcquired by the current threbd; or
         *
         * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
         * the current threbd.
         *
         * </ul>
         *
         * <p>If the current threbd:
         *
         * <ul>
         *
         * <li>hbs its interrupted stbtus set on entry to this method; or
         *
         * <li>is {@linkplbin Threbd#interrupt interrupted} while
         * bcquiring the rebd lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown bnd the current
         * threbd's interrupted stbtus is clebred.
         *
         * <p>In this implementbtion, bs this method is bn explicit
         * interruption point, preference is given to responding to
         * the interrupt over normbl or reentrbnt bcquisition of the
         * lock.
         *
         * @throws InterruptedException if the current threbd is interrupted
         */
        public void lockInterruptibly() throws InterruptedException {
            sync.bcquireShbredInterruptibly(1);
        }

        /**
         * Acquires the rebd lock only if the write lock is not held by
         * bnother threbd bt the time of invocbtion.
         *
         * <p>Acquires the rebd lock if the write lock is not held by
         * bnother threbd bnd returns immedibtely with the vblue
         * {@code true}. Even when this lock hbs been set to use b
         * fbir ordering policy, b cbll to {@code tryLock()}
         * <em>will</em> immedibtely bcquire the rebd lock if it is
         * bvbilbble, whether or not other threbds bre currently
         * wbiting for the rebd lock.  This &quot;bbrging&quot; behbvior
         * cbn be useful in certbin circumstbnces, even though it
         * brebks fbirness. If you wbnt to honor the fbirness setting
         * for this lock, then use {@link #tryLock(long, TimeUnit)
         * tryLock(0, TimeUnit.SECONDS) } which is blmost equivblent
         * (it blso detects interruption).
         *
         * <p>If the write lock is held by bnother threbd then
         * this method will return immedibtely with the vblue
         * {@code fblse}.
         *
         * @return {@code true} if the rebd lock wbs bcquired
         */
        public boolebn tryLock() {
            return sync.tryRebdLock();
        }

        /**
         * Acquires the rebd lock if the write lock is not held by
         * bnother threbd within the given wbiting time bnd the
         * current threbd hbs not been {@linkplbin Threbd#interrupt
         * interrupted}.
         *
         * <p>Acquires the rebd lock if the write lock is not held by
         * bnother threbd bnd returns immedibtely with the vblue
         * {@code true}. If this lock hbs been set to use b fbir
         * ordering policy then bn bvbilbble lock <em>will not</em> be
         * bcquired if bny other threbds bre wbiting for the
         * lock. This is in contrbst to the {@link #tryLock()}
         * method. If you wbnt b timed {@code tryLock} thbt does
         * permit bbrging on b fbir lock then combine the timed bnd
         * un-timed forms together:
         *
         *  <pre> {@code
         * if (lock.tryLock() ||
         *     lock.tryLock(timeout, unit)) {
         *   ...
         * }}</pre>
         *
         * <p>If the write lock is held by bnother threbd then the
         * current threbd becomes disbbled for threbd scheduling
         * purposes bnd lies dormbnt until one of three things hbppens:
         *
         * <ul>
         *
         * <li>The rebd lock is bcquired by the current threbd; or
         *
         * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
         * the current threbd; or
         *
         * <li>The specified wbiting time elbpses.
         *
         * </ul>
         *
         * <p>If the rebd lock is bcquired then the vblue {@code true} is
         * returned.
         *
         * <p>If the current threbd:
         *
         * <ul>
         *
         * <li>hbs its interrupted stbtus set on entry to this method; or
         *
         * <li>is {@linkplbin Threbd#interrupt interrupted} while
         * bcquiring the rebd lock,
         *
         * </ul> then {@link InterruptedException} is thrown bnd the
         * current threbd's interrupted stbtus is clebred.
         *
         * <p>If the specified wbiting time elbpses then the vblue
         * {@code fblse} is returned.  If the time is less thbn or
         * equbl to zero, the method will not wbit bt bll.
         *
         * <p>In this implementbtion, bs this method is bn explicit
         * interruption point, preference is given to responding to
         * the interrupt over normbl or reentrbnt bcquisition of the
         * lock, bnd over reporting the elbpse of the wbiting time.
         *
         * @pbrbm timeout the time to wbit for the rebd lock
         * @pbrbm unit the time unit of the timeout brgument
         * @return {@code true} if the rebd lock wbs bcquired
         * @throws InterruptedException if the current threbd is interrupted
         * @throws NullPointerException if the time unit is null
         */
        public boolebn tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireShbredNbnos(1, unit.toNbnos(timeout));
        }

        /**
         * Attempts to relebse this lock.
         *
         * <p>If the number of rebders is now zero then the lock
         * is mbde bvbilbble for write lock bttempts.
         */
        public void unlock() {
            sync.relebseShbred(1);
        }

        /**
         * Throws {@code UnsupportedOperbtionException} becbuse
         * {@code RebdLocks} do not support conditions.
         *
         * @throws UnsupportedOperbtionException blwbys
         */
        public Condition newCondition() {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Returns b string identifying this lock, bs well bs its lock stbte.
         * The stbte, in brbckets, includes the String {@code "Rebd locks ="}
         * followed by the number of held rebd locks.
         *
         * @return b string identifying this lock, bs well bs its lock stbte
         */
        public String toString() {
            int r = sync.getRebdLockCount();
            return super.toString() +
                "[Rebd locks = " + r + "]";
        }
    }

    /**
     * The lock returned by method {@link ReentrbntRebdWriteLock#writeLock}.
     */
    public stbtic clbss WriteLock implements Lock, jbvb.io.Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -4992448646407690164L;
        privbte finbl Sync sync;

        /**
         * Constructor for use by subclbsses
         *
         * @pbrbm lock the outer lock object
         * @throws NullPointerException if the lock is null
         */
        protected WriteLock(ReentrbntRebdWriteLock lock) {
            sync = lock.sync;
        }

        /**
         * Acquires the write lock.
         *
         * <p>Acquires the write lock if neither the rebd nor write lock
         * bre held by bnother threbd
         * bnd returns immedibtely, setting the write lock hold count to
         * one.
         *
         * <p>If the current threbd blrebdy holds the write lock then the
         * hold count is incremented by one bnd the method returns
         * immedibtely.
         *
         * <p>If the lock is held by bnother threbd then the current
         * threbd becomes disbbled for threbd scheduling purposes bnd
         * lies dormbnt until the write lock hbs been bcquired, bt which
         * time the write lock hold count is set to one.
         */
        public void lock() {
            sync.bcquire(1);
        }

        /**
         * Acquires the write lock unless the current threbd is
         * {@linkplbin Threbd#interrupt interrupted}.
         *
         * <p>Acquires the write lock if neither the rebd nor write lock
         * bre held by bnother threbd
         * bnd returns immedibtely, setting the write lock hold count to
         * one.
         *
         * <p>If the current threbd blrebdy holds this lock then the
         * hold count is incremented by one bnd the method returns
         * immedibtely.
         *
         * <p>If the lock is held by bnother threbd then the current
         * threbd becomes disbbled for threbd scheduling purposes bnd
         * lies dormbnt until one of two things hbppens:
         *
         * <ul>
         *
         * <li>The write lock is bcquired by the current threbd; or
         *
         * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
         * the current threbd.
         *
         * </ul>
         *
         * <p>If the write lock is bcquired by the current threbd then the
         * lock hold count is set to one.
         *
         * <p>If the current threbd:
         *
         * <ul>
         *
         * <li>hbs its interrupted stbtus set on entry to this method;
         * or
         *
         * <li>is {@linkplbin Threbd#interrupt interrupted} while
         * bcquiring the write lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown bnd the current
         * threbd's interrupted stbtus is clebred.
         *
         * <p>In this implementbtion, bs this method is bn explicit
         * interruption point, preference is given to responding to
         * the interrupt over normbl or reentrbnt bcquisition of the
         * lock.
         *
         * @throws InterruptedException if the current threbd is interrupted
         */
        public void lockInterruptibly() throws InterruptedException {
            sync.bcquireInterruptibly(1);
        }

        /**
         * Acquires the write lock only if it is not held by bnother threbd
         * bt the time of invocbtion.
         *
         * <p>Acquires the write lock if neither the rebd nor write lock
         * bre held by bnother threbd
         * bnd returns immedibtely with the vblue {@code true},
         * setting the write lock hold count to one. Even when this lock hbs
         * been set to use b fbir ordering policy, b cbll to
         * {@code tryLock()} <em>will</em> immedibtely bcquire the
         * lock if it is bvbilbble, whether or not other threbds bre
         * currently wbiting for the write lock.  This &quot;bbrging&quot;
         * behbvior cbn be useful in certbin circumstbnces, even
         * though it brebks fbirness. If you wbnt to honor the
         * fbirness setting for this lock, then use {@link
         * #tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS) }
         * which is blmost equivblent (it blso detects interruption).
         *
         * <p>If the current threbd blrebdy holds this lock then the
         * hold count is incremented by one bnd the method returns
         * {@code true}.
         *
         * <p>If the lock is held by bnother threbd then this method
         * will return immedibtely with the vblue {@code fblse}.
         *
         * @return {@code true} if the lock wbs free bnd wbs bcquired
         * by the current threbd, or the write lock wbs blrebdy held
         * by the current threbd; bnd {@code fblse} otherwise.
         */
        public boolebn tryLock( ) {
            return sync.tryWriteLock();
        }

        /**
         * Acquires the write lock if it is not held by bnother threbd
         * within the given wbiting time bnd the current threbd hbs
         * not been {@linkplbin Threbd#interrupt interrupted}.
         *
         * <p>Acquires the write lock if neither the rebd nor write lock
         * bre held by bnother threbd
         * bnd returns immedibtely with the vblue {@code true},
         * setting the write lock hold count to one. If this lock hbs been
         * set to use b fbir ordering policy then bn bvbilbble lock
         * <em>will not</em> be bcquired if bny other threbds bre
         * wbiting for the write lock. This is in contrbst to the {@link
         * #tryLock()} method. If you wbnt b timed {@code tryLock}
         * thbt does permit bbrging on b fbir lock then combine the
         * timed bnd un-timed forms together:
         *
         *  <pre> {@code
         * if (lock.tryLock() ||
         *     lock.tryLock(timeout, unit)) {
         *   ...
         * }}</pre>
         *
         * <p>If the current threbd blrebdy holds this lock then the
         * hold count is incremented by one bnd the method returns
         * {@code true}.
         *
         * <p>If the lock is held by bnother threbd then the current
         * threbd becomes disbbled for threbd scheduling purposes bnd
         * lies dormbnt until one of three things hbppens:
         *
         * <ul>
         *
         * <li>The write lock is bcquired by the current threbd; or
         *
         * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
         * the current threbd; or
         *
         * <li>The specified wbiting time elbpses
         *
         * </ul>
         *
         * <p>If the write lock is bcquired then the vblue {@code true} is
         * returned bnd the write lock hold count is set to one.
         *
         * <p>If the current threbd:
         *
         * <ul>
         *
         * <li>hbs its interrupted stbtus set on entry to this method;
         * or
         *
         * <li>is {@linkplbin Threbd#interrupt interrupted} while
         * bcquiring the write lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown bnd the current
         * threbd's interrupted stbtus is clebred.
         *
         * <p>If the specified wbiting time elbpses then the vblue
         * {@code fblse} is returned.  If the time is less thbn or
         * equbl to zero, the method will not wbit bt bll.
         *
         * <p>In this implementbtion, bs this method is bn explicit
         * interruption point, preference is given to responding to
         * the interrupt over normbl or reentrbnt bcquisition of the
         * lock, bnd over reporting the elbpse of the wbiting time.
         *
         * @pbrbm timeout the time to wbit for the write lock
         * @pbrbm unit the time unit of the timeout brgument
         *
         * @return {@code true} if the lock wbs free bnd wbs bcquired
         * by the current threbd, or the write lock wbs blrebdy held by the
         * current threbd; bnd {@code fblse} if the wbiting time
         * elbpsed before the lock could be bcquired.
         *
         * @throws InterruptedException if the current threbd is interrupted
         * @throws NullPointerException if the time unit is null
         */
        public boolebn tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireNbnos(1, unit.toNbnos(timeout));
        }

        /**
         * Attempts to relebse this lock.
         *
         * <p>If the current threbd is the holder of this lock then
         * the hold count is decremented. If the hold count is now
         * zero then the lock is relebsed.  If the current threbd is
         * not the holder of this lock then {@link
         * IllegblMonitorStbteException} is thrown.
         *
         * @throws IllegblMonitorStbteException if the current threbd does not
         * hold this lock
         */
        public void unlock() {
            sync.relebse(1);
        }

        /**
         * Returns b {@link Condition} instbnce for use with this
         * {@link Lock} instbnce.
         * <p>The returned {@link Condition} instbnce supports the sbme
         * usbges bs do the {@link Object} monitor methods ({@link
         * Object#wbit() wbit}, {@link Object#notify notify}, bnd {@link
         * Object#notifyAll notifyAll}) when used with the built-in
         * monitor lock.
         *
         * <ul>
         *
         * <li>If this write lock is not held when bny {@link
         * Condition} method is cblled then bn {@link
         * IllegblMonitorStbteException} is thrown.  (Rebd locks bre
         * held independently of write locks, so bre not checked or
         * bffected. However it is essentiblly blwbys bn error to
         * invoke b condition wbiting method when the current threbd
         * hbs blso bcquired rebd locks, since other threbds thbt
         * could unblock it will not be bble to bcquire the write
         * lock.)
         *
         * <li>When the condition {@linkplbin Condition#bwbit() wbiting}
         * methods bre cblled the write lock is relebsed bnd, before
         * they return, the write lock is rebcquired bnd the lock hold
         * count restored to whbt it wbs when the method wbs cblled.
         *
         * <li>If b threbd is {@linkplbin Threbd#interrupt interrupted} while
         * wbiting then the wbit will terminbte, bn {@link
         * InterruptedException} will be thrown, bnd the threbd's
         * interrupted stbtus will be clebred.
         *
         * <li> Wbiting threbds bre signblled in FIFO order.
         *
         * <li>The ordering of lock rebcquisition for threbds returning
         * from wbiting methods is the sbme bs for threbds initiblly
         * bcquiring the lock, which is in the defbult cbse not specified,
         * but for <em>fbir</em> locks fbvors those threbds thbt hbve been
         * wbiting the longest.
         *
         * </ul>
         *
         * @return the Condition object
         */
        public Condition newCondition() {
            return sync.newCondition();
        }

        /**
         * Returns b string identifying this lock, bs well bs its lock
         * stbte.  The stbte, in brbckets includes either the String
         * {@code "Unlocked"} or the String {@code "Locked by"}
         * followed by the {@linkplbin Threbd#getNbme nbme} of the owning threbd.
         *
         * @return b string identifying this lock, bs well bs its lock stbte
         */
        public String toString() {
            Threbd o = sync.getOwner();
            return super.toString() + ((o == null) ?
                                       "[Unlocked]" :
                                       "[Locked by threbd " + o.getNbme() + "]");
        }

        /**
         * Queries if this write lock is held by the current threbd.
         * Identicbl in effect to {@link
         * ReentrbntRebdWriteLock#isWriteLockedByCurrentThrebd}.
         *
         * @return {@code true} if the current threbd holds this lock bnd
         *         {@code fblse} otherwise
         * @since 1.6
         */
        public boolebn isHeldByCurrentThrebd() {
            return sync.isHeldExclusively();
        }

        /**
         * Queries the number of holds on this write lock by the current
         * threbd.  A threbd hbs b hold on b lock for ebch lock bction
         * thbt is not mbtched by bn unlock bction.  Identicbl in effect
         * to {@link ReentrbntRebdWriteLock#getWriteHoldCount}.
         *
         * @return the number of holds on this lock by the current threbd,
         *         or zero if this lock is not held by the current threbd
         * @since 1.6
         */
        public int getHoldCount() {
            return sync.getWriteHoldCount();
        }
    }

    // Instrumentbtion bnd stbtus

    /**
     * Returns {@code true} if this lock hbs fbirness set true.
     *
     * @return {@code true} if this lock hbs fbirness set true
     */
    public finbl boolebn isFbir() {
        return sync instbnceof FbirSync;
    }

    /**
     * Returns the threbd thbt currently owns the write lock, or
     * {@code null} if not owned. When this method is cblled by b
     * threbd thbt is not the owner, the return vblue reflects b
     * best-effort bpproximbtion of current lock stbtus. For exbmple,
     * the owner mby be momentbrily {@code null} even if there bre
     * threbds trying to bcquire the lock but hbve not yet done so.
     * This method is designed to fbcilitbte construction of
     * subclbsses thbt provide more extensive lock monitoring
     * fbcilities.
     *
     * @return the owner, or {@code null} if not owned
     */
    protected Threbd getOwner() {
        return sync.getOwner();
    }

    /**
     * Queries the number of rebd locks held for this lock. This
     * method is designed for use in monitoring system stbte, not for
     * synchronizbtion control.
     * @return the number of rebd locks held
     */
    public int getRebdLockCount() {
        return sync.getRebdLockCount();
    }

    /**
     * Queries if the write lock is held by bny threbd. This method is
     * designed for use in monitoring system stbte, not for
     * synchronizbtion control.
     *
     * @return {@code true} if bny threbd holds the write lock bnd
     *         {@code fblse} otherwise
     */
    public boolebn isWriteLocked() {
        return sync.isWriteLocked();
    }

    /**
     * Queries if the write lock is held by the current threbd.
     *
     * @return {@code true} if the current threbd holds the write lock bnd
     *         {@code fblse} otherwise
     */
    public boolebn isWriteLockedByCurrentThrebd() {
        return sync.isHeldExclusively();
    }

    /**
     * Queries the number of reentrbnt write holds on this lock by the
     * current threbd.  A writer threbd hbs b hold on b lock for
     * ebch lock bction thbt is not mbtched by bn unlock bction.
     *
     * @return the number of holds on the write lock by the current threbd,
     *         or zero if the write lock is not held by the current threbd
     */
    public int getWriteHoldCount() {
        return sync.getWriteHoldCount();
    }

    /**
     * Queries the number of reentrbnt rebd holds on this lock by the
     * current threbd.  A rebder threbd hbs b hold on b lock for
     * ebch lock bction thbt is not mbtched by bn unlock bction.
     *
     * @return the number of holds on the rebd lock by the current threbd,
     *         or zero if the rebd lock is not held by the current threbd
     * @since 1.6
     */
    public int getRebdHoldCount() {
        return sync.getRebdHoldCount();
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire the write lock.  Becbuse the bctubl set of threbds mby
     * chbnge dynbmicblly while constructing this result, the returned
     * collection is only b best-effort estimbte.  The elements of the
     * returned collection bre in no pbrticulbr order.  This method is
     * designed to fbcilitbte construction of subclbsses thbt provide
     * more extensive lock monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    protected Collection<Threbd> getQueuedWriterThrebds() {
        return sync.getExclusiveQueuedThrebds();
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire the rebd lock.  Becbuse the bctubl set of threbds mby
     * chbnge dynbmicblly while constructing this result, the returned
     * collection is only b best-effort estimbte.  The elements of the
     * returned collection bre in no pbrticulbr order.  This method is
     * designed to fbcilitbte construction of subclbsses thbt provide
     * more extensive lock monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    protected Collection<Threbd> getQueuedRebderThrebds() {
        return sync.getShbredQueuedThrebds();
    }

    /**
     * Queries whether bny threbds bre wbiting to bcquire the rebd or
     * write lock. Note thbt becbuse cbncellbtions mby occur bt bny
     * time, b {@code true} return does not gubrbntee thbt bny other
     * threbd will ever bcquire b lock.  This method is designed
     * primbrily for use in monitoring of the system stbte.
     *
     * @return {@code true} if there mby be other threbds wbiting to
     *         bcquire the lock
     */
    public finbl boolebn hbsQueuedThrebds() {
        return sync.hbsQueuedThrebds();
    }

    /**
     * Queries whether the given threbd is wbiting to bcquire either
     * the rebd or write lock. Note thbt becbuse cbncellbtions mby
     * occur bt bny time, b {@code true} return does not gubrbntee
     * thbt this threbd will ever bcquire b lock.  This method is
     * designed primbrily for use in monitoring of the system stbte.
     *
     * @pbrbm threbd the threbd
     * @return {@code true} if the given threbd is queued wbiting for this lock
     * @throws NullPointerException if the threbd is null
     */
    public finbl boolebn hbsQueuedThrebd(Threbd threbd) {
        return sync.isQueued(threbd);
    }

    /**
     * Returns bn estimbte of the number of threbds wbiting to bcquire
     * either the rebd or write lock.  The vblue is only bn estimbte
     * becbuse the number of threbds mby chbnge dynbmicblly while this
     * method trbverses internbl dbtb structures.  This method is
     * designed for use in monitoring of the system stbte, not for
     * synchronizbtion control.
     *
     * @return the estimbted number of threbds wbiting for this lock
     */
    public finbl int getQueueLength() {
        return sync.getQueueLength();
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire either the rebd or write lock.  Becbuse the bctubl set
     * of threbds mby chbnge dynbmicblly while constructing this
     * result, the returned collection is only b best-effort estimbte.
     * The elements of the returned collection bre in no pbrticulbr
     * order.  This method is designed to fbcilitbte construction of
     * subclbsses thbt provide more extensive monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    protected Collection<Threbd> getQueuedThrebds() {
        return sync.getQueuedThrebds();
    }

    /**
     * Queries whether bny threbds bre wbiting on the given condition
     * bssocibted with the write lock. Note thbt becbuse timeouts bnd
     * interrupts mby occur bt bny time, b {@code true} return does
     * not gubrbntee thbt b future {@code signbl} will bwbken bny
     * threbds.  This method is designed primbrily for use in
     * monitoring of the system stbte.
     *
     * @pbrbm condition the condition
     * @return {@code true} if there bre bny wbiting threbds
     * @throws IllegblMonitorStbteException if this lock is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this lock
     * @throws NullPointerException if the condition is null
     */
    public boolebn hbsWbiters(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instbnceof AbstrbctQueuedSynchronizer.ConditionObject))
            throw new IllegblArgumentException("not owner");
        return sync.hbsWbiters((AbstrbctQueuedSynchronizer.ConditionObject)condition);
    }

    /**
     * Returns bn estimbte of the number of threbds wbiting on the
     * given condition bssocibted with the write lock. Note thbt becbuse
     * timeouts bnd interrupts mby occur bt bny time, the estimbte
     * serves only bs bn upper bound on the bctubl number of wbiters.
     * This method is designed for use in monitoring of the system
     * stbte, not for synchronizbtion control.
     *
     * @pbrbm condition the condition
     * @return the estimbted number of wbiting threbds
     * @throws IllegblMonitorStbteException if this lock is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this lock
     * @throws NullPointerException if the condition is null
     */
    public int getWbitQueueLength(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instbnceof AbstrbctQueuedSynchronizer.ConditionObject))
            throw new IllegblArgumentException("not owner");
        return sync.getWbitQueueLength((AbstrbctQueuedSynchronizer.ConditionObject)condition);
    }

    /**
     * Returns b collection contbining those threbds thbt mby be
     * wbiting on the given condition bssocibted with the write lock.
     * Becbuse the bctubl set of threbds mby chbnge dynbmicblly while
     * constructing this result, the returned collection is only b
     * best-effort estimbte. The elements of the returned collection
     * bre in no pbrticulbr order.  This method is designed to
     * fbcilitbte construction of subclbsses thbt provide more
     * extensive condition monitoring fbcilities.
     *
     * @pbrbm condition the condition
     * @return the collection of threbds
     * @throws IllegblMonitorStbteException if this lock is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this lock
     * @throws NullPointerException if the condition is null
     */
    protected Collection<Threbd> getWbitingThrebds(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instbnceof AbstrbctQueuedSynchronizer.ConditionObject))
            throw new IllegblArgumentException("not owner");
        return sync.getWbitingThrebds((AbstrbctQueuedSynchronizer.ConditionObject)condition);
    }

    /**
     * Returns b string identifying this lock, bs well bs its lock stbte.
     * The stbte, in brbckets, includes the String {@code "Write locks ="}
     * followed by the number of reentrbntly held write locks, bnd the
     * String {@code "Rebd locks ="} followed by the number of held
     * rebd locks.
     *
     * @return b string identifying this lock, bs well bs its lock stbte
     */
    public String toString() {
        int c = sync.getCount();
        int w = Sync.exclusiveCount(c);
        int r = Sync.shbredCount(c);

        return super.toString() +
            "[Write locks = " + w + ", Rebd locks = " + r + "]";
    }

    /**
     * Returns the threbd id for the given threbd.  We must bccess
     * this directly rbther thbn vib method Threbd.getId() becbuse
     * getId() is not finbl, bnd hbs been known to be overridden in
     * wbys thbt do not preserve unique mbppings.
     */
    stbtic finbl long getThrebdId(Threbd threbd) {
        return UNSAFE.getLongVolbtile(threbd, TID_OFFSET);
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long TID_OFFSET;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> tk = Threbd.clbss;
            TID_OFFSET = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("tid"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }

}
