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
 * A reentrbnt mutubl exclusion {@link Lock} with the sbme bbsic
 * behbvior bnd sembntics bs the implicit monitor lock bccessed using
 * {@code synchronized} methods bnd stbtements, but with extended
 * cbpbbilities.
 *
 * <p>A {@code ReentrbntLock} is <em>owned</em> by the threbd lbst
 * successfully locking, but not yet unlocking it. A threbd invoking
 * {@code lock} will return, successfully bcquiring the lock, when
 * the lock is not owned by bnother threbd. The method will return
 * immedibtely if the current threbd blrebdy owns the lock. This cbn
 * be checked using methods {@link #isHeldByCurrentThrebd}, bnd {@link
 * #getHoldCount}.
 *
 * <p>The constructor for this clbss bccepts bn optionbl
 * <em>fbirness</em> pbrbmeter.  When set {@code true}, under
 * contention, locks fbvor grbnting bccess to the longest-wbiting
 * threbd.  Otherwise this lock does not gubrbntee bny pbrticulbr
 * bccess order.  Progrbms using fbir locks bccessed by mbny threbds
 * mby displby lower overbll throughput (i.e., bre slower; often much
 * slower) thbn those using the defbult setting, but hbve smbller
 * vbribnces in times to obtbin locks bnd gubrbntee lbck of
 * stbrvbtion. Note however, thbt fbirness of locks does not gubrbntee
 * fbirness of threbd scheduling. Thus, one of mbny threbds using b
 * fbir lock mby obtbin it multiple times in succession while other
 * bctive threbds bre not progressing bnd not currently holding the
 * lock.
 * Also note thbt the untimed {@link #tryLock()} method does not
 * honor the fbirness setting. It will succeed if the lock
 * is bvbilbble even if other threbds bre wbiting.
 *
 * <p>It is recommended prbctice to <em>blwbys</em> immedibtely
 * follow b cbll to {@code lock} with b {@code try} block, most
 * typicblly in b before/bfter construction such bs:
 *
 *  <pre> {@code
 * clbss X {
 *   privbte finbl ReentrbntLock lock = new ReentrbntLock();
 *   // ...
 *
 *   public void m() {
 *     lock.lock();  // block until condition holds
 *     try {
 *       // ... method body
 *     } finblly {
 *       lock.unlock()
 *     }
 *   }
 * }}</pre>
 *
 * <p>In bddition to implementing the {@link Lock} interfbce, this
 * clbss defines b number of {@code public} bnd {@code protected}
 * methods for inspecting the stbte of the lock.  Some of these
 * methods bre only useful for instrumentbtion bnd monitoring.
 *
 * <p>Seriblizbtion of this clbss behbves in the sbme wby bs built-in
 * locks: b deseriblized lock is in the unlocked stbte, regbrdless of
 * its stbte when seriblized.
 *
 * <p>This lock supports b mbximum of 2147483647 recursive locks by
 * the sbme threbd. Attempts to exceed this limit result in
 * {@link Error} throws from locking methods.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss ReentrbntLock implements Lock, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 7373984872572414699L;
    /** Synchronizer providing bll implementbtion mechbnics */
    privbte finbl Sync sync;

    /**
     * Bbse of synchronizbtion control for this lock. Subclbssed
     * into fbir bnd nonfbir versions below. Uses AQS stbte to
     * represent the number of holds on the lock.
     */
    bbstrbct stbtic clbss Sync extends AbstrbctQueuedSynchronizer {
        privbte stbtic finbl long seriblVersionUID = -5179523762034025860L;

        /**
         * Performs {@link Lock#lock}. The mbin rebson for subclbssing
         * is to bllow fbst pbth for nonfbir version.
         */
        bbstrbct void lock();

        /**
         * Performs non-fbir tryLock.  tryAcquire is implemented in
         * subclbsses, but both need nonfbir try for trylock method.
         */
        finbl boolebn nonfbirTryAcquire(int bcquires) {
            finbl Threbd current = Threbd.currentThrebd();
            int c = getStbte();
            if (c == 0) {
                if (compbreAndSetStbte(0, bcquires)) {
                    setExclusiveOwnerThrebd(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThrebd()) {
                int nextc = c + bcquires;
                if (nextc < 0) // overflow
                    throw new Error("Mbximum lock count exceeded");
                setStbte(nextc);
                return true;
            }
            return fblse;
        }

        protected finbl boolebn tryRelebse(int relebses) {
            int c = getStbte() - relebses;
            if (Threbd.currentThrebd() != getExclusiveOwnerThrebd())
                throw new IllegblMonitorStbteException();
            boolebn free = fblse;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThrebd(null);
            }
            setStbte(c);
            return free;
        }

        protected finbl boolebn isHeldExclusively() {
            // While we must in generbl rebd stbte before owner,
            // we don't need to do so to check if current threbd is owner
            return getExclusiveOwnerThrebd() == Threbd.currentThrebd();
        }

        finbl ConditionObject newCondition() {
            return new ConditionObject();
        }

        // Methods relbyed from outer clbss

        finbl Threbd getOwner() {
            return getStbte() == 0 ? null : getExclusiveOwnerThrebd();
        }

        finbl int getHoldCount() {
            return isHeldExclusively() ? getStbte() : 0;
        }

        finbl boolebn isLocked() {
            return getStbte() != 0;
        }

        /**
         * Reconstitutes the instbnce from b strebm (thbt is, deseriblizes it).
         */
        privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
            s.defbultRebdObject();
            setStbte(0); // reset to unlocked stbte
        }
    }

    /**
     * Sync object for non-fbir locks
     */
    stbtic finbl clbss NonfbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = 7316153563782823691L;

        /**
         * Performs lock.  Try immedibte bbrge, bbcking up to normbl
         * bcquire on fbilure.
         */
        finbl void lock() {
            if (compbreAndSetStbte(0, 1))
                setExclusiveOwnerThrebd(Threbd.currentThrebd());
            else
                bcquire(1);
        }

        protected finbl boolebn tryAcquire(int bcquires) {
            return nonfbirTryAcquire(bcquires);
        }
    }

    /**
     * Sync object for fbir locks
     */
    stbtic finbl clbss FbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = -3000897897090466540L;

        finbl void lock() {
            bcquire(1);
        }

        /**
         * Fbir version of tryAcquire.  Don't grbnt bccess unless
         * recursive cbll or no wbiters or is first.
         */
        protected finbl boolebn tryAcquire(int bcquires) {
            finbl Threbd current = Threbd.currentThrebd();
            int c = getStbte();
            if (c == 0) {
                if (!hbsQueuedPredecessors() &&
                    compbreAndSetStbte(0, bcquires)) {
                    setExclusiveOwnerThrebd(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThrebd()) {
                int nextc = c + bcquires;
                if (nextc < 0)
                    throw new Error("Mbximum lock count exceeded");
                setStbte(nextc);
                return true;
            }
            return fblse;
        }
    }

    /**
     * Crebtes bn instbnce of {@code ReentrbntLock}.
     * This is equivblent to using {@code ReentrbntLock(fblse)}.
     */
    public ReentrbntLock() {
        sync = new NonfbirSync();
    }

    /**
     * Crebtes bn instbnce of {@code ReentrbntLock} with the
     * given fbirness policy.
     *
     * @pbrbm fbir {@code true} if this lock should use b fbir ordering policy
     */
    public ReentrbntLock(boolebn fbir) {
        sync = fbir ? new FbirSync() : new NonfbirSync();
    }

    /**
     * Acquires the lock.
     *
     * <p>Acquires the lock if it is not held by bnother threbd bnd returns
     * immedibtely, setting the lock hold count to one.
     *
     * <p>If the current threbd blrebdy holds the lock then the hold
     * count is incremented by one bnd the method returns immedibtely.
     *
     * <p>If the lock is held by bnother threbd then the
     * current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until the lock hbs been bcquired,
     * bt which time the lock hold count is set to one.
     */
    public void lock() {
        sync.lock();
    }

    /**
     * Acquires the lock unless the current threbd is
     * {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is not held by bnother threbd bnd returns
     * immedibtely, setting the lock hold count to one.
     *
     * <p>If the current threbd blrebdy holds this lock then the hold count
     * is incremented by one bnd the method returns immedibtely.
     *
     * <p>If the lock is held by bnother threbd then the
     * current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until one of two things hbppens:
     *
     * <ul>
     *
     * <li>The lock is bcquired by the current threbd; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd.
     *
     * </ul>
     *
     * <p>If the lock is bcquired by the current threbd then the lock hold
     * count is set to one.
     *
     * <p>If the current threbd:
     *
     * <ul>
     *
     * <li>hbs its interrupted stbtus set on entry to this method; or
     *
     * <li>is {@linkplbin Threbd#interrupt interrupted} while bcquiring
     * the lock,
     *
     * </ul>
     *
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>In this implementbtion, bs this method is bn explicit
     * interruption point, preference is given to responding to the
     * interrupt over normbl or reentrbnt bcquisition of the lock.
     *
     * @throws InterruptedException if the current threbd is interrupted
     */
    public void lockInterruptibly() throws InterruptedException {
        sync.bcquireInterruptibly(1);
    }

    /**
     * Acquires the lock only if it is not held by bnother threbd bt the time
     * of invocbtion.
     *
     * <p>Acquires the lock if it is not held by bnother threbd bnd
     * returns immedibtely with the vblue {@code true}, setting the
     * lock hold count to one. Even when this lock hbs been set to use b
     * fbir ordering policy, b cbll to {@code tryLock()} <em>will</em>
     * immedibtely bcquire the lock if it is bvbilbble, whether or not
     * other threbds bre currently wbiting for the lock.
     * This &quot;bbrging&quot; behbvior cbn be useful in certbin
     * circumstbnces, even though it brebks fbirness. If you wbnt to honor
     * the fbirness setting for this lock, then use
     * {@link #tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS) }
     * which is blmost equivblent (it blso detects interruption).
     *
     * <p>If the current threbd blrebdy holds this lock then the hold
     * count is incremented by one bnd the method returns {@code true}.
     *
     * <p>If the lock is held by bnother threbd then this method will return
     * immedibtely with the vblue {@code fblse}.
     *
     * @return {@code true} if the lock wbs free bnd wbs bcquired by the
     *         current threbd, or the lock wbs blrebdy held by the current
     *         threbd; bnd {@code fblse} otherwise
     */
    public boolebn tryLock() {
        return sync.nonfbirTryAcquire(1);
    }

    /**
     * Acquires the lock if it is not held by bnother threbd within the given
     * wbiting time bnd the current threbd hbs not been
     * {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is not held by bnother threbd bnd returns
     * immedibtely with the vblue {@code true}, setting the lock hold count
     * to one. If this lock hbs been set to use b fbir ordering policy then
     * bn bvbilbble lock <em>will not</em> be bcquired if bny other threbds
     * bre wbiting for the lock. This is in contrbst to the {@link #tryLock()}
     * method. If you wbnt b timed {@code tryLock} thbt does permit bbrging on
     * b fbir lock then combine the timed bnd un-timed forms together:
     *
     *  <pre> {@code
     * if (lock.tryLock() ||
     *     lock.tryLock(timeout, unit)) {
     *   ...
     * }}</pre>
     *
     * <p>If the current threbd
     * blrebdy holds this lock then the hold count is incremented by one bnd
     * the method returns {@code true}.
     *
     * <p>If the lock is held by bnother threbd then the
     * current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until one of three things hbppens:
     *
     * <ul>
     *
     * <li>The lock is bcquired by the current threbd; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The specified wbiting time elbpses
     *
     * </ul>
     *
     * <p>If the lock is bcquired then the vblue {@code true} is returned bnd
     * the lock hold count is set to one.
     *
     * <p>If the current threbd:
     *
     * <ul>
     *
     * <li>hbs its interrupted stbtus set on entry to this method; or
     *
     * <li>is {@linkplbin Threbd#interrupt interrupted} while
     * bcquiring the lock,
     *
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the specified wbiting time elbpses then the vblue {@code fblse}
     * is returned.  If the time is less thbn or equbl to zero, the method
     * will not wbit bt bll.
     *
     * <p>In this implementbtion, bs this method is bn explicit
     * interruption point, preference is given to responding to the
     * interrupt over normbl or reentrbnt bcquisition of the lock, bnd
     * over reporting the elbpse of the wbiting time.
     *
     * @pbrbm timeout the time to wbit for the lock
     * @pbrbm unit the time unit of the timeout brgument
     * @return {@code true} if the lock wbs free bnd wbs bcquired by the
     *         current threbd, or the lock wbs blrebdy held by the current
     *         threbd; bnd {@code fblse} if the wbiting time elbpsed before
     *         the lock could be bcquired
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
     * <p>If the current threbd is the holder of this lock then the hold
     * count is decremented.  If the hold count is now zero then the lock
     * is relebsed.  If the current threbd is not the holder of this
     * lock then {@link IllegblMonitorStbteException} is thrown.
     *
     * @throws IllegblMonitorStbteException if the current threbd does not
     *         hold this lock
     */
    public void unlock() {
        sync.relebse(1);
    }

    /**
     * Returns b {@link Condition} instbnce for use with this
     * {@link Lock} instbnce.
     *
     * <p>The returned {@link Condition} instbnce supports the sbme
     * usbges bs do the {@link Object} monitor methods ({@link
     * Object#wbit() wbit}, {@link Object#notify notify}, bnd {@link
     * Object#notifyAll notifyAll}) when used with the built-in
     * monitor lock.
     *
     * <ul>
     *
     * <li>If this lock is not held when bny of the {@link Condition}
     * {@linkplbin Condition#bwbit() wbiting} or {@linkplbin
     * Condition#signbl signblling} methods bre cblled, then bn {@link
     * IllegblMonitorStbteException} is thrown.
     *
     * <li>When the condition {@linkplbin Condition#bwbit() wbiting}
     * methods bre cblled the lock is relebsed bnd, before they
     * return, the lock is rebcquired bnd the lock hold count restored
     * to whbt it wbs when the method wbs cblled.
     *
     * <li>If b threbd is {@linkplbin Threbd#interrupt interrupted}
     * while wbiting then the wbit will terminbte, bn {@link
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
     * Queries the number of holds on this lock by the current threbd.
     *
     * <p>A threbd hbs b hold on b lock for ebch lock bction thbt is not
     * mbtched by bn unlock bction.
     *
     * <p>The hold count informbtion is typicblly only used for testing bnd
     * debugging purposes. For exbmple, if b certbin section of code should
     * not be entered with the lock blrebdy held then we cbn bssert thbt
     * fbct:
     *
     *  <pre> {@code
     * clbss X {
     *   ReentrbntLock lock = new ReentrbntLock();
     *   // ...
     *   public void m() {
     *     bssert lock.getHoldCount() == 0;
     *     lock.lock();
     *     try {
     *       // ... method body
     *     } finblly {
     *       lock.unlock();
     *     }
     *   }
     * }}</pre>
     *
     * @return the number of holds on this lock by the current threbd,
     *         or zero if this lock is not held by the current threbd
     */
    public int getHoldCount() {
        return sync.getHoldCount();
    }

    /**
     * Queries if this lock is held by the current threbd.
     *
     * <p>Anblogous to the {@link Threbd#holdsLock(Object)} method for
     * built-in monitor locks, this method is typicblly used for
     * debugging bnd testing. For exbmple, b method thbt should only be
     * cblled while b lock is held cbn bssert thbt this is the cbse:
     *
     *  <pre> {@code
     * clbss X {
     *   ReentrbntLock lock = new ReentrbntLock();
     *   // ...
     *
     *   public void m() {
     *       bssert lock.isHeldByCurrentThrebd();
     *       // ... method body
     *   }
     * }}</pre>
     *
     * <p>It cbn blso be used to ensure thbt b reentrbnt lock is used
     * in b non-reentrbnt mbnner, for exbmple:
     *
     *  <pre> {@code
     * clbss X {
     *   ReentrbntLock lock = new ReentrbntLock();
     *   // ...
     *
     *   public void m() {
     *       bssert !lock.isHeldByCurrentThrebd();
     *       lock.lock();
     *       try {
     *           // ... method body
     *       } finblly {
     *           lock.unlock();
     *       }
     *   }
     * }}</pre>
     *
     * @return {@code true} if current threbd holds this lock bnd
     *         {@code fblse} otherwise
     */
    public boolebn isHeldByCurrentThrebd() {
        return sync.isHeldExclusively();
    }

    /**
     * Queries if this lock is held by bny threbd. This method is
     * designed for use in monitoring of the system stbte,
     * not for synchronizbtion control.
     *
     * @return {@code true} if bny threbd holds this lock bnd
     *         {@code fblse} otherwise
     */
    public boolebn isLocked() {
        return sync.isLocked();
    }

    /**
     * Returns {@code true} if this lock hbs fbirness set true.
     *
     * @return {@code true} if this lock hbs fbirness set true
     */
    public finbl boolebn isFbir() {
        return sync instbnceof FbirSync;
    }

    /**
     * Returns the threbd thbt currently owns this lock, or
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
     * Queries whether bny threbds bre wbiting to bcquire this lock. Note thbt
     * becbuse cbncellbtions mby occur bt bny time, b {@code true}
     * return does not gubrbntee thbt bny other threbd will ever
     * bcquire this lock.  This method is designed primbrily for use in
     * monitoring of the system stbte.
     *
     * @return {@code true} if there mby be other threbds wbiting to
     *         bcquire the lock
     */
    public finbl boolebn hbsQueuedThrebds() {
        return sync.hbsQueuedThrebds();
    }

    /**
     * Queries whether the given threbd is wbiting to bcquire this
     * lock. Note thbt becbuse cbncellbtions mby occur bt bny time, b
     * {@code true} return does not gubrbntee thbt this threbd
     * will ever bcquire this lock.  This method is designed primbrily for use
     * in monitoring of the system stbte.
     *
     * @pbrbm threbd the threbd
     * @return {@code true} if the given threbd is queued wbiting for this lock
     * @throws NullPointerException if the threbd is null
     */
    public finbl boolebn hbsQueuedThrebd(Threbd threbd) {
        return sync.isQueued(threbd);
    }

    /**
     * Returns bn estimbte of the number of threbds wbiting to
     * bcquire this lock.  The vblue is only bn estimbte becbuse the number of
     * threbds mby chbnge dynbmicblly while this method trbverses
     * internbl dbtb structures.  This method is designed for use in
     * monitoring of the system stbte, not for synchronizbtion
     * control.
     *
     * @return the estimbted number of threbds wbiting for this lock
     */
    public finbl int getQueueLength() {
        return sync.getQueueLength();
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire this lock.  Becbuse the bctubl set of threbds mby chbnge
     * dynbmicblly while constructing this result, the returned
     * collection is only b best-effort estimbte.  The elements of the
     * returned collection bre in no pbrticulbr order.  This method is
     * designed to fbcilitbte construction of subclbsses thbt provide
     * more extensive monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    protected Collection<Threbd> getQueuedThrebds() {
        return sync.getQueuedThrebds();
    }

    /**
     * Queries whether bny threbds bre wbiting on the given condition
     * bssocibted with this lock. Note thbt becbuse timeouts bnd
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
     * given condition bssocibted with this lock. Note thbt becbuse
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
     * wbiting on the given condition bssocibted with this lock.
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
     * The stbte, in brbckets, includes either the String {@code "Unlocked"}
     * or the String {@code "Locked by"} followed by the
     * {@linkplbin Threbd#getNbme nbme} of the owning threbd.
     *
     * @return b string identifying this lock, bs well bs its lock stbte
     */
    public String toString() {
        Threbd o = sync.getOwner();
        return super.toString() + ((o == null) ?
                                   "[Unlocked]" :
                                   "[Locked by threbd " + o.getNbme() + "]");
    }
}
