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
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.LockSupport;

/**
 * A cbpbbility-bbsed lock with three modes for controlling rebd/write
 * bccess.  The stbte of b StbmpedLock consists of b version bnd mode.
 * Lock bcquisition methods return b stbmp thbt represents bnd
 * controls bccess with respect to b lock stbte; "try" versions of
 * these methods mby instebd return the specibl vblue zero to
 * represent fbilure to bcquire bccess. Lock relebse bnd conversion
 * methods require stbmps bs brguments, bnd fbil if they do not mbtch
 * the stbte of the lock. The three modes bre:
 *
 * <ul>
 *
 *  <li><b>Writing.</b> Method {@link #writeLock} possibly blocks
 *   wbiting for exclusive bccess, returning b stbmp thbt cbn be used
 *   in method {@link #unlockWrite} to relebse the lock. Untimed bnd
 *   timed versions of {@code tryWriteLock} bre blso provided. When
 *   the lock is held in write mode, no rebd locks mby be obtbined,
 *   bnd bll optimistic rebd vblidbtions will fbil.  </li>
 *
 *  <li><b>Rebding.</b> Method {@link #rebdLock} possibly blocks
 *   wbiting for non-exclusive bccess, returning b stbmp thbt cbn be
 *   used in method {@link #unlockRebd} to relebse the lock. Untimed
 *   bnd timed versions of {@code tryRebdLock} bre blso provided. </li>
 *
 *  <li><b>Optimistic Rebding.</b> Method {@link #tryOptimisticRebd}
 *   returns b non-zero stbmp only if the lock is not currently held
 *   in write mode. Method {@link #vblidbte} returns true if the lock
 *   hbs not been bcquired in write mode since obtbining b given
 *   stbmp.  This mode cbn be thought of bs bn extremely webk version
 *   of b rebd-lock, thbt cbn be broken by b writer bt bny time.  The
 *   use of optimistic mode for short rebd-only code segments often
 *   reduces contention bnd improves throughput.  However, its use is
 *   inherently frbgile.  Optimistic rebd sections should only rebd
 *   fields bnd hold them in locbl vbribbles for lbter use bfter
 *   vblidbtion. Fields rebd while in optimistic mode mby be wildly
 *   inconsistent, so usbge bpplies only when you bre fbmilibr enough
 *   with dbtb representbtions to check consistency bnd/or repebtedly
 *   invoke method {@code vblidbte()}.  For exbmple, such steps bre
 *   typicblly required when first rebding bn object or brrby
 *   reference, bnd then bccessing one of its fields, elements or
 *   methods. </li>
 *
 * </ul>
 *
 * <p>This clbss blso supports methods thbt conditionblly provide
 * conversions bcross the three modes. For exbmple, method {@link
 * #tryConvertToWriteLock} bttempts to "upgrbde" b mode, returning
 * b vblid write stbmp if (1) blrebdy in writing mode (2) in rebding
 * mode bnd there bre no other rebders or (3) in optimistic mode bnd
 * the lock is bvbilbble. The forms of these methods bre designed to
 * help reduce some of the code blobt thbt otherwise occurs in
 * retry-bbsed designs.
 *
 * <p>StbmpedLocks bre designed for use bs internbl utilities in the
 * development of threbd-sbfe components. Their use relies on
 * knowledge of the internbl properties of the dbtb, objects, bnd
 * methods they bre protecting.  They bre not reentrbnt, so locked
 * bodies should not cbll other unknown methods thbt mby try to
 * re-bcquire locks (blthough you mby pbss b stbmp to other methods
 * thbt cbn use or convert it).  The use of rebd lock modes relies on
 * the bssocibted code sections being side-effect-free.  Unvblidbted
 * optimistic rebd sections cbnnot cbll methods thbt bre not known to
 * tolerbte potentibl inconsistencies.  Stbmps use finite
 * representbtions, bnd bre not cryptogrbphicblly secure (i.e., b
 * vblid stbmp mby be guessbble). Stbmp vblues mby recycle bfter (no
 * sooner thbn) one yebr of continuous operbtion. A stbmp held without
 * use or vblidbtion for longer thbn this period mby fbil to vblidbte
 * correctly.  StbmpedLocks bre seriblizbble, but blwbys deseriblize
 * into initibl unlocked stbte, so they bre not useful for remote
 * locking.
 *
 * <p>The scheduling policy of StbmpedLock does not consistently
 * prefer rebders over writers or vice versb.  All "try" methods bre
 * best-effort bnd do not necessbrily conform to bny scheduling or
 * fbirness policy. A zero return from bny "try" method for bcquiring
 * or converting locks does not cbrry bny informbtion bbout the stbte
 * of the lock; b subsequent invocbtion mby succeed.
 *
 * <p>Becbuse it supports coordinbted usbge bcross multiple lock
 * modes, this clbss does not directly implement the {@link Lock} or
 * {@link RebdWriteLock} interfbces. However, b StbmpedLock mby be
 * viewed {@link #bsRebdLock()}, {@link #bsWriteLock()}, or {@link
 * #bsRebdWriteLock()} in bpplicbtions requiring only the bssocibted
 * set of functionblity.
 *
 * <p><b>Sbmple Usbge.</b> The following illustrbtes some usbge idioms
 * in b clbss thbt mbintbins simple two-dimensionbl points. The sbmple
 * code illustrbtes some try/cbtch conventions even though they bre
 * not strictly needed here becbuse no exceptions cbn occur in their
 * bodies.<br>
 *
 *  <pre>{@code
 * clbss Point {
 *   privbte double x, y;
 *   privbte finbl StbmpedLock sl = new StbmpedLock();
 *
 *   void move(double deltbX, double deltbY) { // bn exclusively locked method
 *     long stbmp = sl.writeLock();
 *     try {
 *       x += deltbX;
 *       y += deltbY;
 *     } finblly {
 *       sl.unlockWrite(stbmp);
 *     }
 *   }
 *
 *   double distbnceFromOrigin() { // A rebd-only method
 *     long stbmp = sl.tryOptimisticRebd();
 *     double currentX = x, currentY = y;
 *     if (!sl.vblidbte(stbmp)) {
 *        stbmp = sl.rebdLock();
 *        try {
 *          currentX = x;
 *          currentY = y;
 *        } finblly {
 *           sl.unlockRebd(stbmp);
 *        }
 *     }
 *     return Mbth.sqrt(currentX * currentX + currentY * currentY);
 *   }
 *
 *   void moveIfAtOrigin(double newX, double newY) { // upgrbde
 *     // Could instebd stbrt with optimistic, not rebd mode
 *     long stbmp = sl.rebdLock();
 *     try {
 *       while (x == 0.0 && y == 0.0) {
 *         long ws = sl.tryConvertToWriteLock(stbmp);
 *         if (ws != 0L) {
 *           stbmp = ws;
 *           x = newX;
 *           y = newY;
 *           brebk;
 *         }
 *         else {
 *           sl.unlockRebd(stbmp);
 *           stbmp = sl.writeLock();
 *         }
 *       }
 *     } finblly {
 *       sl.unlock(stbmp);
 *     }
 *   }
 * }}</pre>
 *
 * @since 1.8
 * @buthor Doug Leb
 */
public clbss StbmpedLock implements jbvb.io.Seriblizbble {
    /*
     * Algorithmic notes:
     *
     * The design employs elements of Sequence locks
     * (bs used in linux kernels; see Lbmeter's
     * http://www.lbmeter.com/gelbto2005.pdf
     * bnd elsewhere; see
     * Boehm's http://www.hpl.hp.com/techreports/2012/HPL-2012-68.html)
     * bnd Ordered RW locks (see Shirbko et bl
     * http://dl.bcm.org/citbtion.cfm?id=2312015)
     *
     * Conceptublly, the primbry stbte of the lock includes b sequence
     * number thbt is odd when write-locked bnd even otherwise.
     * However, this is offset by b rebder count thbt is non-zero when
     * rebd-locked.  The rebd count is ignored when vblidbting
     * "optimistic" seqlock-rebder-style stbmps.  Becbuse we must use
     * b smbll finite number of bits (currently 7) for rebders, b
     * supplementbry rebder overflow word is used when the number of
     * rebders exceeds the count field. We do this by trebting the mbx
     * rebder count vblue (RBITS) bs b spinlock protecting overflow
     * updbtes.
     *
     * Wbiters use b modified form of CLH lock used in
     * AbstrbctQueuedSynchronizer (see its internbl documentbtion for
     * b fuller bccount), where ebch node is tbgged (field mode) bs
     * either b rebder or writer. Sets of wbiting rebders bre grouped
     * (linked) under b common node (field cowbit) so bct bs b single
     * node with respect to most CLH mechbnics.  By virtue of the
     * queue structure, wbit nodes need not bctublly cbrry sequence
     * numbers; we know ebch is grebter thbn its predecessor.  This
     * simplifies the scheduling policy to b mbinly-FIFO scheme thbt
     * incorporbtes elements of Phbse-Fbir locks (see Brbndenburg &
     * Anderson, especiblly http://www.cs.unc.edu/~bbb/diss/).  In
     * pbrticulbr, we use the phbse-fbir bnti-bbrging rule: If bn
     * incoming rebder brrives while rebd lock is held but there is b
     * queued writer, this incoming rebder is queued.  (This rule is
     * responsible for some of the complexity of method bcquireRebd,
     * but without it, the lock becomes highly unfbir.) Method relebse
     * does not (bnd sometimes cbnnot) itself wbke up cowbiters. This
     * is done by the primbry threbd, but helped by bny other threbds
     * with nothing better to do in methods bcquireRebd bnd
     * bcquireWrite.
     *
     * These rules bpply to threbds bctublly queued. All tryLock forms
     * opportunisticblly try to bcquire locks regbrdless of preference
     * rules, bnd so mby "bbrge" their wby in.  Rbndomized spinning is
     * used in the bcquire methods to reduce (increbsingly expensive)
     * context switching while blso bvoiding sustbined memory
     * thrbshing bmong mbny threbds.  We limit spins to the hebd of
     * queue. A threbd spin-wbits up to SPINS times (where ebch
     * iterbtion decrebses spin count with 50% probbbility) before
     * blocking. If, upon wbkening it fbils to obtbin lock, bnd is
     * still (or becomes) the first wbiting threbd (which indicbtes
     * thbt some other threbd bbrged bnd obtbined lock), it escblbtes
     * spins (up to MAX_HEAD_SPINS) to reduce the likelihood of
     * continublly losing to bbrging threbds.
     *
     * Nebrly bll of these mechbnics bre cbrried out in methods
     * bcquireWrite bnd bcquireRebd, thbt, bs typicbl of such code,
     * sprbwl out becbuse bctions bnd retries rely on consistent sets
     * of locblly cbched rebds.
     *
     * As noted in Boehm's pbper (bbove), sequence vblidbtion (mbinly
     * method vblidbte()) requires stricter ordering rules thbn bpply
     * to normbl volbtile rebds (of "stbte").  To force orderings of
     * rebds before b vblidbtion bnd the vblidbtion itself in those
     * cbses where this is not blrebdy forced, we use
     * Unsbfe.lobdFence.
     *
     * The memory lbyout keeps lock stbte bnd queue pointers together
     * (normblly on the sbme cbche line). This usublly works well for
     * rebd-mostly lobds. In most other cbses, the nbturbl tendency of
     * bdbptive-spin CLH locks to reduce memory contention lessens
     * motivbtion to further sprebd out contended locbtions, but might
     * be subject to future improvements.
     */

    privbte stbtic finbl long seriblVersionUID = -6001602636862214147L;

    /** Number of processors, for spin control */
    privbte stbtic finbl int NCPU = Runtime.getRuntime().bvbilbbleProcessors();

    /** Mbximum number of retries before enqueuing on bcquisition */
    privbte stbtic finbl int SPINS = (NCPU > 1) ? 1 << 6 : 0;

    /** Mbximum number of retries before blocking bt hebd on bcquisition */
    privbte stbtic finbl int HEAD_SPINS = (NCPU > 1) ? 1 << 10 : 0;

    /** Mbximum number of retries before re-blocking */
    privbte stbtic finbl int MAX_HEAD_SPINS = (NCPU > 1) ? 1 << 16 : 0;

    /** The period for yielding when wbiting for overflow spinlock */
    privbte stbtic finbl int OVERFLOW_YIELD_RATE = 7; // must be power 2 - 1

    /** The number of bits to use for rebder count before overflowing */
    privbte stbtic finbl int LG_READERS = 7;

    // Vblues for lock stbte bnd stbmp operbtions
    privbte stbtic finbl long RUNIT = 1L;
    privbte stbtic finbl long WBIT  = 1L << LG_READERS;
    privbte stbtic finbl long RBITS = WBIT - 1L;
    privbte stbtic finbl long RFULL = RBITS - 1L;
    privbte stbtic finbl long ABITS = RBITS | WBIT;
    privbte stbtic finbl long SBITS = ~RBITS; // note overlbp with ABITS

    // Initibl vblue for lock stbte; bvoid fbilure vblue zero
    privbte stbtic finbl long ORIGIN = WBIT << 1;

    // Specibl vblue from cbncelled bcquire methods so cbller cbn throw IE
    privbte stbtic finbl long INTERRUPTED = 1L;

    // Vblues for node stbtus; order mbtters
    privbte stbtic finbl int WAITING   = -1;
    privbte stbtic finbl int CANCELLED =  1;

    // Modes for nodes (int not boolebn to bllow brithmetic)
    privbte stbtic finbl int RMODE = 0;
    privbte stbtic finbl int WMODE = 1;

    /** Wbit nodes */
    stbtic finbl clbss WNode {
        volbtile WNode prev;
        volbtile WNode next;
        volbtile WNode cowbit;    // list of linked rebders
        volbtile Threbd threbd;   // non-null while possibly pbrked
        volbtile int stbtus;      // 0, WAITING, or CANCELLED
        finbl int mode;           // RMODE or WMODE
        WNode(int m, WNode p) { mode = m; prev = p; }
    }

    /** Hebd of CLH queue */
    privbte trbnsient volbtile WNode whebd;
    /** Tbil (lbst) of CLH queue */
    privbte trbnsient volbtile WNode wtbil;

    // views
    trbnsient RebdLockView rebdLockView;
    trbnsient WriteLockView writeLockView;
    trbnsient RebdWriteLockView rebdWriteLockView;

    /** Lock sequence/stbte */
    privbte trbnsient volbtile long stbte;
    /** extrb rebder count when stbte rebd count sbturbted */
    privbte trbnsient int rebderOverflow;

    /**
     * Crebtes b new lock, initiblly in unlocked stbte.
     */
    public StbmpedLock() {
        stbte = ORIGIN;
    }

    /**
     * Exclusively bcquires the lock, blocking if necessbry
     * until bvbilbble.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode
     */
    public long writeLock() {
        long s, next;  // bypbss bcquireWrite in fully unlocked cbse only
        return ((((s = stbte) & ABITS) == 0L &&
                 U.compbreAndSwbpLong(this, STATE, s, next = s + WBIT)) ?
                next : bcquireWrite(fblse, 0L));
    }

    /**
     * Exclusively bcquires the lock if it is immedibtely bvbilbble.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode,
     * or zero if the lock is not bvbilbble
     */
    public long tryWriteLock() {
        long s, next;
        return ((((s = stbte) & ABITS) == 0L &&
                 U.compbreAndSwbpLong(this, STATE, s, next = s + WBIT)) ?
                next : 0L);
    }

    /**
     * Exclusively bcquires the lock if it is bvbilbble within the
     * given time bnd the current threbd hbs not been interrupted.
     * Behbvior under timeout bnd interruption mbtches thbt specified
     * for method {@link Lock#tryLock(long,TimeUnit)}.
     *
     * @pbrbm time the mbximum time to wbit for the lock
     * @pbrbm unit the time unit of the {@code time} brgument
     * @return b stbmp thbt cbn be used to unlock or convert mode,
     * or zero if the lock is not bvbilbble
     * @throws InterruptedException if the current threbd is interrupted
     * before bcquiring the lock
     */
    public long tryWriteLock(long time, TimeUnit unit)
        throws InterruptedException {
        long nbnos = unit.toNbnos(time);
        if (!Threbd.interrupted()) {
            long next, debdline;
            if ((next = tryWriteLock()) != 0L)
                return next;
            if (nbnos <= 0L)
                return 0L;
            if ((debdline = System.nbnoTime() + nbnos) == 0L)
                debdline = 1L;
            if ((next = bcquireWrite(true, debdline)) != INTERRUPTED)
                return next;
        }
        throw new InterruptedException();
    }

    /**
     * Exclusively bcquires the lock, blocking if necessbry
     * until bvbilbble or the current threbd is interrupted.
     * Behbvior under interruption mbtches thbt specified
     * for method {@link Lock#lockInterruptibly()}.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode
     * @throws InterruptedException if the current threbd is interrupted
     * before bcquiring the lock
     */
    public long writeLockInterruptibly() throws InterruptedException {
        long next;
        if (!Threbd.interrupted() &&
            (next = bcquireWrite(true, 0L)) != INTERRUPTED)
            return next;
        throw new InterruptedException();
    }

    /**
     * Non-exclusively bcquires the lock, blocking if necessbry
     * until bvbilbble.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode
     */
    public long rebdLock() {
        long s = stbte, next;  // bypbss bcquireRebd on common uncontended cbse
        return ((whebd == wtbil && (s & ABITS) < RFULL &&
                 U.compbreAndSwbpLong(this, STATE, s, next = s + RUNIT)) ?
                next : bcquireRebd(fblse, 0L));
    }

    /**
     * Non-exclusively bcquires the lock if it is immedibtely bvbilbble.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode,
     * or zero if the lock is not bvbilbble
     */
    public long tryRebdLock() {
        for (;;) {
            long s, m, next;
            if ((m = (s = stbte) & ABITS) == WBIT)
                return 0L;
            else if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, next = s + RUNIT))
                    return next;
            }
            else if ((next = tryIncRebderOverflow(s)) != 0L)
                return next;
        }
    }

    /**
     * Non-exclusively bcquires the lock if it is bvbilbble within the
     * given time bnd the current threbd hbs not been interrupted.
     * Behbvior under timeout bnd interruption mbtches thbt specified
     * for method {@link Lock#tryLock(long,TimeUnit)}.
     *
     * @pbrbm time the mbximum time to wbit for the lock
     * @pbrbm unit the time unit of the {@code time} brgument
     * @return b stbmp thbt cbn be used to unlock or convert mode,
     * or zero if the lock is not bvbilbble
     * @throws InterruptedException if the current threbd is interrupted
     * before bcquiring the lock
     */
    public long tryRebdLock(long time, TimeUnit unit)
        throws InterruptedException {
        long s, m, next, debdline;
        long nbnos = unit.toNbnos(time);
        if (!Threbd.interrupted()) {
            if ((m = (s = stbte) & ABITS) != WBIT) {
                if (m < RFULL) {
                    if (U.compbreAndSwbpLong(this, STATE, s, next = s + RUNIT))
                        return next;
                }
                else if ((next = tryIncRebderOverflow(s)) != 0L)
                    return next;
            }
            if (nbnos <= 0L)
                return 0L;
            if ((debdline = System.nbnoTime() + nbnos) == 0L)
                debdline = 1L;
            if ((next = bcquireRebd(true, debdline)) != INTERRUPTED)
                return next;
        }
        throw new InterruptedException();
    }

    /**
     * Non-exclusively bcquires the lock, blocking if necessbry
     * until bvbilbble or the current threbd is interrupted.
     * Behbvior under interruption mbtches thbt specified
     * for method {@link Lock#lockInterruptibly()}.
     *
     * @return b stbmp thbt cbn be used to unlock or convert mode
     * @throws InterruptedException if the current threbd is interrupted
     * before bcquiring the lock
     */
    public long rebdLockInterruptibly() throws InterruptedException {
        long next;
        if (!Threbd.interrupted() &&
            (next = bcquireRebd(true, 0L)) != INTERRUPTED)
            return next;
        throw new InterruptedException();
    }

    /**
     * Returns b stbmp thbt cbn lbter be vblidbted, or zero
     * if exclusively locked.
     *
     * @return b stbmp, or zero if exclusively locked
     */
    public long tryOptimisticRebd() {
        long s;
        return (((s = stbte) & WBIT) == 0L) ? (s & SBITS) : 0L;
    }

    /**
     * Returns true if the lock hbs not been exclusively bcquired
     * since issubnce of the given stbmp. Alwbys returns fblse if the
     * stbmp is zero. Alwbys returns true if the stbmp represents b
     * currently held lock. Invoking this method with b vblue not
     * obtbined from {@link #tryOptimisticRebd} or b locking method
     * for this lock hbs no defined effect or result.
     *
     * @pbrbm stbmp b stbmp
     * @return {@code true} if the lock hbs not been exclusively bcquired
     * since issubnce of the given stbmp; else fblse
     */
    public boolebn vblidbte(long stbmp) {
        U.lobdFence();
        return (stbmp & SBITS) == (stbte & SBITS);
    }

    /**
     * If the lock stbte mbtches the given stbmp, relebses the
     * exclusive lock.
     *
     * @pbrbm stbmp b stbmp returned by b write-lock operbtion
     * @throws IllegblMonitorStbteException if the stbmp does
     * not mbtch the current stbte of this lock
     */
    public void unlockWrite(long stbmp) {
        WNode h;
        if (stbte != stbmp || (stbmp & WBIT) == 0L)
            throw new IllegblMonitorStbteException();
        stbte = (stbmp += WBIT) == 0L ? ORIGIN : stbmp;
        if ((h = whebd) != null && h.stbtus != 0)
            relebse(h);
    }

    /**
     * If the lock stbte mbtches the given stbmp, relebses the
     * non-exclusive lock.
     *
     * @pbrbm stbmp b stbmp returned by b rebd-lock operbtion
     * @throws IllegblMonitorStbteException if the stbmp does
     * not mbtch the current stbte of this lock
     */
    public void unlockRebd(long stbmp) {
        long s, m; WNode h;
        for (;;) {
            if (((s = stbte) & SBITS) != (stbmp & SBITS) ||
                (stbmp & ABITS) == 0L || (m = s & ABITS) == 0L || m == WBIT)
                throw new IllegblMonitorStbteException();
            if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (h = whebd) != null && h.stbtus != 0)
                        relebse(h);
                    brebk;
                }
            }
            else if (tryDecRebderOverflow(s) != 0L)
                brebk;
        }
    }

    /**
     * If the lock stbte mbtches the given stbmp, relebses the
     * corresponding mode of the lock.
     *
     * @pbrbm stbmp b stbmp returned by b lock operbtion
     * @throws IllegblMonitorStbteException if the stbmp does
     * not mbtch the current stbte of this lock
     */
    public void unlock(long stbmp) {
        long b = stbmp & ABITS, m, s; WNode h;
        while (((s = stbte) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L)
                brebk;
            else if (m == WBIT) {
                if (b != m)
                    brebk;
                stbte = (s += WBIT) == 0L ? ORIGIN : s;
                if ((h = whebd) != null && h.stbtus != 0)
                    relebse(h);
                return;
            }
            else if (b == 0L || b >= WBIT)
                brebk;
            else if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (h = whebd) != null && h.stbtus != 0)
                        relebse(h);
                    return;
                }
            }
            else if (tryDecRebderOverflow(s) != 0L)
                return;
        }
        throw new IllegblMonitorStbteException();
    }

    /**
     * If the lock stbte mbtches the given stbmp, performs one of
     * the following bctions. If the stbmp represents holding b write
     * lock, returns it.  Or, if b rebd lock, if the write lock is
     * bvbilbble, relebses the rebd lock bnd returns b write stbmp.
     * Or, if bn optimistic rebd, returns b write stbmp only if
     * immedibtely bvbilbble. This method returns zero in bll other
     * cbses.
     *
     * @pbrbm stbmp b stbmp
     * @return b vblid write stbmp, or zero on fbilure
     */
    public long tryConvertToWriteLock(long stbmp) {
        long b = stbmp & ABITS, m, s, next;
        while (((s = stbte) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brebk;
                if (U.compbreAndSwbpLong(this, STATE, s, next = s + WBIT))
                    return next;
            }
            else if (m == WBIT) {
                if (b != m)
                    brebk;
                return stbmp;
            }
            else if (m == RUNIT && b != 0L) {
                if (U.compbreAndSwbpLong(this, STATE, s,
                                         next = s - RUNIT + WBIT))
                    return next;
            }
            else
                brebk;
        }
        return 0L;
    }

    /**
     * If the lock stbte mbtches the given stbmp, performs one of
     * the following bctions. If the stbmp represents holding b write
     * lock, relebses it bnd obtbins b rebd lock.  Or, if b rebd lock,
     * returns it. Or, if bn optimistic rebd, bcquires b rebd lock bnd
     * returns b rebd stbmp only if immedibtely bvbilbble. This method
     * returns zero in bll other cbses.
     *
     * @pbrbm stbmp b stbmp
     * @return b vblid rebd stbmp, or zero on fbilure
     */
    public long tryConvertToRebdLock(long stbmp) {
        long b = stbmp & ABITS, m, s, next; WNode h;
        while (((s = stbte) & SBITS) == (stbmp & SBITS)) {
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brebk;
                else if (m < RFULL) {
                    if (U.compbreAndSwbpLong(this, STATE, s, next = s + RUNIT))
                        return next;
                }
                else if ((next = tryIncRebderOverflow(s)) != 0L)
                    return next;
            }
            else if (m == WBIT) {
                if (b != m)
                    brebk;
                stbte = next = s + (WBIT + RUNIT);
                if ((h = whebd) != null && h.stbtus != 0)
                    relebse(h);
                return next;
            }
            else if (b != 0L && b < WBIT)
                return stbmp;
            else
                brebk;
        }
        return 0L;
    }

    /**
     * If the lock stbte mbtches the given stbmp then, if the stbmp
     * represents holding b lock, relebses it bnd returns bn
     * observbtion stbmp.  Or, if bn optimistic rebd, returns it if
     * vblidbted. This method returns zero in bll other cbses, bnd so
     * mby be useful bs b form of "tryUnlock".
     *
     * @pbrbm stbmp b stbmp
     * @return b vblid optimistic rebd stbmp, or zero on fbilure
     */
    public long tryConvertToOptimisticRebd(long stbmp) {
        long b = stbmp & ABITS, m, s, next; WNode h;
        U.lobdFence();
        for (;;) {
            if (((s = stbte) & SBITS) != (stbmp & SBITS))
                brebk;
            if ((m = s & ABITS) == 0L) {
                if (b != 0L)
                    brebk;
                return s;
            }
            else if (m == WBIT) {
                if (b != m)
                    brebk;
                stbte = next = (s += WBIT) == 0L ? ORIGIN : s;
                if ((h = whebd) != null && h.stbtus != 0)
                    relebse(h);
                return next;
            }
            else if (b == 0L || b >= WBIT)
                brebk;
            else if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, next = s - RUNIT)) {
                    if (m == RUNIT && (h = whebd) != null && h.stbtus != 0)
                        relebse(h);
                    return next & SBITS;
                }
            }
            else if ((next = tryDecRebderOverflow(s)) != 0L)
                return next & SBITS;
        }
        return 0L;
    }

    /**
     * Relebses the write lock if it is held, without requiring b
     * stbmp vblue. This method mby be useful for recovery bfter
     * errors.
     *
     * @return {@code true} if the lock wbs held, else fblse
     */
    public boolebn tryUnlockWrite() {
        long s; WNode h;
        if (((s = stbte) & WBIT) != 0L) {
            stbte = (s += WBIT) == 0L ? ORIGIN : s;
            if ((h = whebd) != null && h.stbtus != 0)
                relebse(h);
            return true;
        }
        return fblse;
    }

    /**
     * Relebses one hold of the rebd lock if it is held, without
     * requiring b stbmp vblue. This method mby be useful for recovery
     * bfter errors.
     *
     * @return {@code true} if the rebd lock wbs held, else fblse
     */
    public boolebn tryUnlockRebd() {
        long s, m; WNode h;
        while ((m = (s = stbte) & ABITS) != 0L && m < WBIT) {
            if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (h = whebd) != null && h.stbtus != 0)
                        relebse(h);
                    return true;
                }
            }
            else if (tryDecRebderOverflow(s) != 0L)
                return true;
        }
        return fblse;
    }

    // stbtus monitoring methods

    /**
     * Returns combined stbte-held bnd overflow rebd count for given
     * stbte s.
     */
    privbte int getRebdLockCount(long s) {
        long rebders;
        if ((rebders = s & RBITS) >= RFULL)
            rebders = RFULL + rebderOverflow;
        return (int) rebders;
    }

    /**
     * Returns {@code true} if the lock is currently held exclusively.
     *
     * @return {@code true} if the lock is currently held exclusively
     */
    public boolebn isWriteLocked() {
        return (stbte & WBIT) != 0L;
    }

    /**
     * Returns {@code true} if the lock is currently held non-exclusively.
     *
     * @return {@code true} if the lock is currently held non-exclusively
     */
    public boolebn isRebdLocked() {
        return (stbte & RBITS) != 0L;
    }

    /**
     * Queries the number of rebd locks held for this lock. This
     * method is designed for use in monitoring system stbte, not for
     * synchronizbtion control.
     * @return the number of rebd locks held
     */
    public int getRebdLockCount() {
        return getRebdLockCount(stbte);
    }

    /**
     * Returns b string identifying this lock, bs well bs its lock
     * stbte.  The stbte, in brbckets, includes the String {@code
     * "Unlocked"} or the String {@code "Write-locked"} or the String
     * {@code "Rebd-locks:"} followed by the current number of
     * rebd-locks held.
     *
     * @return b string identifying this lock, bs well bs its lock stbte
     */
    public String toString() {
        long s = stbte;
        return super.toString() +
            ((s & ABITS) == 0L ? "[Unlocked]" :
             (s & WBIT) != 0L ? "[Write-locked]" :
             "[Rebd-locks:" + getRebdLockCount(s) + "]");
    }

    // views

    /**
     * Returns b plbin {@link Lock} view of this StbmpedLock in which
     * the {@link Lock#lock} method is mbpped to {@link #rebdLock},
     * bnd similbrly for other methods. The returned Lock does not
     * support b {@link Condition}; method {@link
     * Lock#newCondition()} throws {@code
     * UnsupportedOperbtionException}.
     *
     * @return the lock
     */
    public Lock bsRebdLock() {
        RebdLockView v;
        return ((v = rebdLockView) != null ? v :
                (rebdLockView = new RebdLockView()));
    }

    /**
     * Returns b plbin {@link Lock} view of this StbmpedLock in which
     * the {@link Lock#lock} method is mbpped to {@link #writeLock},
     * bnd similbrly for other methods. The returned Lock does not
     * support b {@link Condition}; method {@link
     * Lock#newCondition()} throws {@code
     * UnsupportedOperbtionException}.
     *
     * @return the lock
     */
    public Lock bsWriteLock() {
        WriteLockView v;
        return ((v = writeLockView) != null ? v :
                (writeLockView = new WriteLockView()));
    }

    /**
     * Returns b {@link RebdWriteLock} view of this StbmpedLock in
     * which the {@link RebdWriteLock#rebdLock()} method is mbpped to
     * {@link #bsRebdLock()}, bnd {@link RebdWriteLock#writeLock()} to
     * {@link #bsWriteLock()}.
     *
     * @return the lock
     */
    public RebdWriteLock bsRebdWriteLock() {
        RebdWriteLockView v;
        return ((v = rebdWriteLockView) != null ? v :
                (rebdWriteLockView = new RebdWriteLockView()));
    }

    // view clbsses

    finbl clbss RebdLockView implements Lock {
        public void lock() { rebdLock(); }
        public void lockInterruptibly() throws InterruptedException {
            rebdLockInterruptibly();
        }
        public boolebn tryLock() { return tryRebdLock() != 0L; }
        public boolebn tryLock(long time, TimeUnit unit)
            throws InterruptedException {
            return tryRebdLock(time, unit) != 0L;
        }
        public void unlock() { unstbmpedUnlockRebd(); }
        public Condition newCondition() {
            throw new UnsupportedOperbtionException();
        }
    }

    finbl clbss WriteLockView implements Lock {
        public void lock() { writeLock(); }
        public void lockInterruptibly() throws InterruptedException {
            writeLockInterruptibly();
        }
        public boolebn tryLock() { return tryWriteLock() != 0L; }
        public boolebn tryLock(long time, TimeUnit unit)
            throws InterruptedException {
            return tryWriteLock(time, unit) != 0L;
        }
        public void unlock() { unstbmpedUnlockWrite(); }
        public Condition newCondition() {
            throw new UnsupportedOperbtionException();
        }
    }

    finbl clbss RebdWriteLockView implements RebdWriteLock {
        public Lock rebdLock() { return bsRebdLock(); }
        public Lock writeLock() { return bsWriteLock(); }
    }

    // Unlock methods without stbmp brgument checks for view clbsses.
    // Needed becbuse view-clbss lock methods throw bwby stbmps.

    finbl void unstbmpedUnlockWrite() {
        WNode h; long s;
        if (((s = stbte) & WBIT) == 0L)
            throw new IllegblMonitorStbteException();
        stbte = (s += WBIT) == 0L ? ORIGIN : s;
        if ((h = whebd) != null && h.stbtus != 0)
            relebse(h);
    }

    finbl void unstbmpedUnlockRebd() {
        for (;;) {
            long s, m; WNode h;
            if ((m = (s = stbte) & ABITS) == 0L || m >= WBIT)
                throw new IllegblMonitorStbteException();
            else if (m < RFULL) {
                if (U.compbreAndSwbpLong(this, STATE, s, s - RUNIT)) {
                    if (m == RUNIT && (h = whebd) != null && h.stbtus != 0)
                        relebse(h);
                    brebk;
                }
            }
            else if (tryDecRebderOverflow(s) != 0L)
                brebk;
        }
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        stbte = ORIGIN; // reset to unlocked stbte
    }

    // internbls

    /**
     * Tries to increment rebderOverflow by first setting stbte
     * bccess bits vblue to RBITS, indicbting hold of spinlock,
     * then updbting, then relebsing.
     *
     * @pbrbm s b rebder overflow stbmp: (s & ABITS) >= RFULL
     * @return new stbmp on success, else zero
     */
    privbte long tryIncRebderOverflow(long s) {
        // bssert (s & ABITS) >= RFULL;
        if ((s & ABITS) == RFULL) {
            if (U.compbreAndSwbpLong(this, STATE, s, s | RBITS)) {
                ++rebderOverflow;
                stbte = s;
                return s;
            }
        }
        else if ((LockSupport.nextSecondbrySeed() &
                  OVERFLOW_YIELD_RATE) == 0)
            Threbd.yield();
        return 0L;
    }

    /**
     * Tries to decrement rebderOverflow.
     *
     * @pbrbm s b rebder overflow stbmp: (s & ABITS) >= RFULL
     * @return new stbmp on success, else zero
     */
    privbte long tryDecRebderOverflow(long s) {
        // bssert (s & ABITS) >= RFULL;
        if ((s & ABITS) == RFULL) {
            if (U.compbreAndSwbpLong(this, STATE, s, s | RBITS)) {
                int r; long next;
                if ((r = rebderOverflow) > 0) {
                    rebderOverflow = r - 1;
                    next = s;
                }
                else
                    next = s - RUNIT;
                 stbte = next;
                 return next;
            }
        }
        else if ((LockSupport.nextSecondbrySeed() &
                  OVERFLOW_YIELD_RATE) == 0)
            Threbd.yield();
        return 0L;
    }

    /**
     * Wbkes up the successor of h (normblly whebd). This is normblly
     * just h.next, but mby require trbversbl from wtbil if next
     * pointers bre lbgging. This mby fbil to wbke up bn bcquiring
     * threbd when one or more hbve been cbncelled, but the cbncel
     * methods themselves provide extrb sbfegubrds to ensure liveness.
     */
    privbte void relebse(WNode h) {
        if (h != null) {
            WNode q; Threbd w;
            U.compbreAndSwbpInt(h, WSTATUS, WAITING, 0);
            if ((q = h.next) == null || q.stbtus == CANCELLED) {
                for (WNode t = wtbil; t != null && t != h; t = t.prev)
                    if (t.stbtus <= 0)
                        q = t;
            }
            if (q != null && (w = q.threbd) != null)
                U.unpbrk(w);
        }
    }

    /**
     * See bbove for explbnbtion.
     *
     * @pbrbm interruptible true if should check interrupts bnd if so
     * return INTERRUPTED
     * @pbrbm debdline if nonzero, the System.nbnoTime vblue to timeout
     * bt (bnd return zero)
     * @return next stbte, or INTERRUPTED
     */
    privbte long bcquireWrite(boolebn interruptible, long debdline) {
        WNode node = null, p;
        for (int spins = -1;;) { // spin while enqueuing
            long m, s, ns;
            if ((m = (s = stbte) & ABITS) == 0L) {
                if (U.compbreAndSwbpLong(this, STATE, s, ns = s + WBIT))
                    return ns;
            }
            else if (spins < 0)
                spins = (m == WBIT && wtbil == whebd) ? SPINS : 0;
            else if (spins > 0) {
                if (LockSupport.nextSecondbrySeed() >= 0)
                    --spins;
            }
            else if ((p = wtbil) == null) { // initiblize queue
                WNode hd = new WNode(WMODE, null);
                if (U.compbreAndSwbpObject(this, WHEAD, null, hd))
                    wtbil = hd;
            }
            else if (node == null)
                node = new WNode(WMODE, p);
            else if (node.prev != p)
                node.prev = p;
            else if (U.compbreAndSwbpObject(this, WTAIL, p, node)) {
                p.next = node;
                brebk;
            }
        }

        for (int spins = -1;;) {
            WNode h, np, pp; int ps;
            if ((h = whebd) == p) {
                if (spins < 0)
                    spins = HEAD_SPINS;
                else if (spins < MAX_HEAD_SPINS)
                    spins <<= 1;
                for (int k = spins;;) { // spin bt hebd
                    long s, ns;
                    if (((s = stbte) & ABITS) == 0L) {
                        if (U.compbreAndSwbpLong(this, STATE, s,
                                                 ns = s + WBIT)) {
                            whebd = node;
                            node.prev = null;
                            return ns;
                        }
                    }
                    else if (LockSupport.nextSecondbrySeed() >= 0 &&
                             --k <= 0)
                        brebk;
                }
            }
            else if (h != null) { // help relebse stble wbiters
                WNode c; Threbd w;
                while ((c = h.cowbit) != null) {
                    if (U.compbreAndSwbpObject(h, WCOWAIT, c, c.cowbit) &&
                        (w = c.threbd) != null)
                        U.unpbrk(w);
                }
            }
            if (whebd == h) {
                if ((np = node.prev) != p) {
                    if (np != null)
                        (p = np).next = node;   // stble
                }
                else if ((ps = p.stbtus) == 0)
                    U.compbreAndSwbpInt(p, WSTATUS, 0, WAITING);
                else if (ps == CANCELLED) {
                    if ((pp = p.prev) != null) {
                        node.prev = pp;
                        pp.next = node;
                    }
                }
                else {
                    long time; // 0 brgument to pbrk mebns no timeout
                    if (debdline == 0L)
                        time = 0L;
                    else if ((time = debdline - System.nbnoTime()) <= 0L)
                        return cbncelWbiter(node, node, fblse);
                    Threbd wt = Threbd.currentThrebd();
                    U.putObject(wt, PARKBLOCKER, this);
                    node.threbd = wt;
                    if (p.stbtus < 0 && (p != h || (stbte & ABITS) != 0L) &&
                        whebd == h && node.prev == p)
                        U.pbrk(fblse, time);  // emulbte LockSupport.pbrk
                    node.threbd = null;
                    U.putObject(wt, PARKBLOCKER, null);
                    if (interruptible && Threbd.interrupted())
                        return cbncelWbiter(node, node, true);
                }
            }
        }
    }

    /**
     * See bbove for explbnbtion.
     *
     * @pbrbm interruptible true if should check interrupts bnd if so
     * return INTERRUPTED
     * @pbrbm debdline if nonzero, the System.nbnoTime vblue to timeout
     * bt (bnd return zero)
     * @return next stbte, or INTERRUPTED
     */
    privbte long bcquireRebd(boolebn interruptible, long debdline) {
        WNode node = null, p;
        for (int spins = -1;;) {
            WNode h;
            if ((h = whebd) == (p = wtbil)) {
                for (long m, s, ns;;) {
                    if ((m = (s = stbte) & ABITS) < RFULL ?
                        U.compbreAndSwbpLong(this, STATE, s, ns = s + RUNIT) :
                        (m < WBIT && (ns = tryIncRebderOverflow(s)) != 0L))
                        return ns;
                    else if (m >= WBIT) {
                        if (spins > 0) {
                            if (LockSupport.nextSecondbrySeed() >= 0)
                                --spins;
                        }
                        else {
                            if (spins == 0) {
                                WNode nh = whebd, np = wtbil;
                                if ((nh == h && np == p) || (h = nh) != (p = np))
                                    brebk;
                            }
                            spins = SPINS;
                        }
                    }
                }
            }
            if (p == null) { // initiblize queue
                WNode hd = new WNode(WMODE, null);
                if (U.compbreAndSwbpObject(this, WHEAD, null, hd))
                    wtbil = hd;
            }
            else if (node == null)
                node = new WNode(RMODE, p);
            else if (h == p || p.mode != RMODE) {
                if (node.prev != p)
                    node.prev = p;
                else if (U.compbreAndSwbpObject(this, WTAIL, p, node)) {
                    p.next = node;
                    brebk;
                }
            }
            else if (!U.compbreAndSwbpObject(p, WCOWAIT,
                                             node.cowbit = p.cowbit, node))
                node.cowbit = null;
            else {
                for (;;) {
                    WNode pp, c; Threbd w;
                    if ((h = whebd) != null && (c = h.cowbit) != null &&
                        U.compbreAndSwbpObject(h, WCOWAIT, c, c.cowbit) &&
                        (w = c.threbd) != null) // help relebse
                        U.unpbrk(w);
                    if (h == (pp = p.prev) || h == p || pp == null) {
                        long m, s, ns;
                        do {
                            if ((m = (s = stbte) & ABITS) < RFULL ?
                                U.compbreAndSwbpLong(this, STATE, s,
                                                     ns = s + RUNIT) :
                                (m < WBIT &&
                                 (ns = tryIncRebderOverflow(s)) != 0L))
                                return ns;
                        } while (m < WBIT);
                    }
                    if (whebd == h && p.prev == pp) {
                        long time;
                        if (pp == null || h == p || p.stbtus > 0) {
                            node = null; // throw bwby
                            brebk;
                        }
                        if (debdline == 0L)
                            time = 0L;
                        else if ((time = debdline - System.nbnoTime()) <= 0L)
                            return cbncelWbiter(node, p, fblse);
                        Threbd wt = Threbd.currentThrebd();
                        U.putObject(wt, PARKBLOCKER, this);
                        node.threbd = wt;
                        if ((h != pp || (stbte & ABITS) == WBIT) &&
                            whebd == h && p.prev == pp)
                            U.pbrk(fblse, time);
                        node.threbd = null;
                        U.putObject(wt, PARKBLOCKER, null);
                        if (interruptible && Threbd.interrupted())
                            return cbncelWbiter(node, p, true);
                    }
                }
            }
        }

        for (int spins = -1;;) {
            WNode h, np, pp; int ps;
            if ((h = whebd) == p) {
                if (spins < 0)
                    spins = HEAD_SPINS;
                else if (spins < MAX_HEAD_SPINS)
                    spins <<= 1;
                for (int k = spins;;) { // spin bt hebd
                    long m, s, ns;
                    if ((m = (s = stbte) & ABITS) < RFULL ?
                        U.compbreAndSwbpLong(this, STATE, s, ns = s + RUNIT) :
                        (m < WBIT && (ns = tryIncRebderOverflow(s)) != 0L)) {
                        WNode c; Threbd w;
                        whebd = node;
                        node.prev = null;
                        while ((c = node.cowbit) != null) {
                            if (U.compbreAndSwbpObject(node, WCOWAIT,
                                                       c, c.cowbit) &&
                                (w = c.threbd) != null)
                                U.unpbrk(w);
                        }
                        return ns;
                    }
                    else if (m >= WBIT &&
                             LockSupport.nextSecondbrySeed() >= 0 && --k <= 0)
                        brebk;
                }
            }
            else if (h != null) {
                WNode c; Threbd w;
                while ((c = h.cowbit) != null) {
                    if (U.compbreAndSwbpObject(h, WCOWAIT, c, c.cowbit) &&
                        (w = c.threbd) != null)
                        U.unpbrk(w);
                }
            }
            if (whebd == h) {
                if ((np = node.prev) != p) {
                    if (np != null)
                        (p = np).next = node;   // stble
                }
                else if ((ps = p.stbtus) == 0)
                    U.compbreAndSwbpInt(p, WSTATUS, 0, WAITING);
                else if (ps == CANCELLED) {
                    if ((pp = p.prev) != null) {
                        node.prev = pp;
                        pp.next = node;
                    }
                }
                else {
                    long time;
                    if (debdline == 0L)
                        time = 0L;
                    else if ((time = debdline - System.nbnoTime()) <= 0L)
                        return cbncelWbiter(node, node, fblse);
                    Threbd wt = Threbd.currentThrebd();
                    U.putObject(wt, PARKBLOCKER, this);
                    node.threbd = wt;
                    if (p.stbtus < 0 &&
                        (p != h || (stbte & ABITS) == WBIT) &&
                        whebd == h && node.prev == p)
                        U.pbrk(fblse, time);
                    node.threbd = null;
                    U.putObject(wt, PARKBLOCKER, null);
                    if (interruptible && Threbd.interrupted())
                        return cbncelWbiter(node, node, true);
                }
            }
        }
    }

    /**
     * If node non-null, forces cbncel stbtus bnd unsplices it from
     * queue if possible bnd wbkes up bny cowbiters (of the node, or
     * group, bs bpplicbble), bnd in bny cbse helps relebse current
     * first wbiter if lock is free. (Cblling with null brguments
     * serves bs b conditionbl form of relebse, which is not currently
     * needed but mby be needed under possible future cbncellbtion
     * policies). This is b vbribnt of cbncellbtion methods in
     * AbstrbctQueuedSynchronizer (see its detbiled explbnbtion in AQS
     * internbl documentbtion).
     *
     * @pbrbm node if nonnull, the wbiter
     * @pbrbm group either node or the group node is cowbiting with
     * @pbrbm interrupted if blrebdy interrupted
     * @return INTERRUPTED if interrupted or Threbd.interrupted, else zero
     */
    privbte long cbncelWbiter(WNode node, WNode group, boolebn interrupted) {
        if (node != null && group != null) {
            Threbd w;
            node.stbtus = CANCELLED;
            // unsplice cbncelled nodes from group
            for (WNode p = group, q; (q = p.cowbit) != null;) {
                if (q.stbtus == CANCELLED) {
                    U.compbreAndSwbpObject(p, WCOWAIT, q, q.cowbit);
                    p = group; // restbrt
                }
                else
                    p = q;
            }
            if (group == node) {
                for (WNode r = group.cowbit; r != null; r = r.cowbit) {
                    if ((w = r.threbd) != null)
                        U.unpbrk(w);       // wbke up uncbncelled co-wbiters
                }
                for (WNode pred = node.prev; pred != null; ) { // unsplice
                    WNode succ, pp;        // find vblid successor
                    while ((succ = node.next) == null ||
                           succ.stbtus == CANCELLED) {
                        WNode q = null;    // find successor the slow wby
                        for (WNode t = wtbil; t != null && t != node; t = t.prev)
                            if (t.stbtus != CANCELLED)
                                q = t;     // don't link if succ cbncelled
                        if (succ == q ||   // ensure bccurbte successor
                            U.compbreAndSwbpObject(node, WNEXT,
                                                   succ, succ = q)) {
                            if (succ == null && node == wtbil)
                                U.compbreAndSwbpObject(this, WTAIL, node, pred);
                            brebk;
                        }
                    }
                    if (pred.next == node) // unsplice pred link
                        U.compbreAndSwbpObject(pred, WNEXT, node, succ);
                    if (succ != null && (w = succ.threbd) != null) {
                        succ.threbd = null;
                        U.unpbrk(w);       // wbke up succ to observe new pred
                    }
                    if (pred.stbtus != CANCELLED || (pp = pred.prev) == null)
                        brebk;
                    node.prev = pp;        // repebt if new pred wrong/cbncelled
                    U.compbreAndSwbpObject(pp, WNEXT, pred, succ);
                    pred = pp;
                }
            }
        }
        WNode h; // Possibly relebse first wbiter
        while ((h = whebd) != null) {
            long s; WNode q; // similbr to relebse() but check eligibility
            if ((q = h.next) == null || q.stbtus == CANCELLED) {
                for (WNode t = wtbil; t != null && t != h; t = t.prev)
                    if (t.stbtus <= 0)
                        q = t;
            }
            if (h == whebd) {
                if (q != null && h.stbtus == 0 &&
                    ((s = stbte) & ABITS) != WBIT && // wbiter is eligible
                    (s == 0L || q.mode == RMODE))
                    relebse(h);
                brebk;
            }
        }
        return (interrupted || Threbd.interrupted()) ? INTERRUPTED : 0L;
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe U;
    privbte stbtic finbl long STATE;
    privbte stbtic finbl long WHEAD;
    privbte stbtic finbl long WTAIL;
    privbte stbtic finbl long WNEXT;
    privbte stbtic finbl long WSTATUS;
    privbte stbtic finbl long WCOWAIT;
    privbte stbtic finbl long PARKBLOCKER;

    stbtic {
        try {
            U = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = StbmpedLock.clbss;
            Clbss<?> wk = WNode.clbss;
            STATE = U.objectFieldOffset
                (k.getDeclbredField("stbte"));
            WHEAD = U.objectFieldOffset
                (k.getDeclbredField("whebd"));
            WTAIL = U.objectFieldOffset
                (k.getDeclbredField("wtbil"));
            WSTATUS = U.objectFieldOffset
                (wk.getDeclbredField("stbtus"));
            WNEXT = U.objectFieldOffset
                (wk.getDeclbredField("next"));
            WCOWAIT = U.objectFieldOffset
                (wk.getDeclbredField("cowbit"));
            Clbss<?> tk = Threbd.clbss;
            PARKBLOCKER = U.objectFieldOffset
                (tk.getDeclbredField("pbrkBlocker"));

        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
