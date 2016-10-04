/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import sun.mbnbgement.MbnbgementFbctoryHelper;
import sun.mbnbgement.ThrebdInfoCompositeDbtb;
import stbtic jbvb.lbng.Threbd.Stbte.*;

/**
 * Threbd informbtion. <tt>ThrebdInfo</tt> contbins the informbtion
 * bbout b threbd including:
 * <h3>Generbl threbd informbtion</h3>
 * <ul>
 *   <li>Threbd ID.</li>
 *   <li>Nbme of the threbd.</li>
 * </ul>
 *
 * <h3>Execution informbtion</h3>
 * <ul>
 *   <li>Threbd stbte.</li>
 *   <li>The object upon which the threbd is blocked due to:
 *       <ul>
 *       <li>wbiting to enter b synchronizbtion block/method, or</li>
 *       <li>wbiting to be notified in b {@link Object#wbit Object.wbit} method,
 *           or</li>
 *       <li>pbrking due to b {@link jbvb.util.concurrent.locks.LockSupport#pbrk
 *           LockSupport.pbrk} cbll.</li>
 *       </ul>
 *   </li>
 *   <li>The ID of the threbd thbt owns the object
 *       thbt the threbd is blocked.</li>
 *   <li>Stbck trbce of the threbd.</li>
 *   <li>List of object monitors locked by the threbd.</li>
 *   <li>List of <b href="LockInfo.html#OwnbbleSynchronizer">
 *       ownbble synchronizers</b> locked by the threbd.</li>
 * </ul>
 *
 * <h4><b nbme="SyncStbts">Synchronizbtion Stbtistics</b></h4>
 * <ul>
 *   <li>The number of times thbt the threbd hbs blocked for
 *       synchronizbtion or wbited for notificbtion.</li>
 *   <li>The bccumulbted elbpsed time thbt the threbd hbs blocked
 *       for synchronizbtion or wbited for notificbtion
 *       since {@link ThrebdMXBebn#setThrebdContentionMonitoringEnbbled
 *       threbd contention monitoring}
 *       wbs enbbled. Some Jbvb virtubl mbchine implementbtion
 *       mby not support this.  The
 *       {@link ThrebdMXBebn#isThrebdContentionMonitoringSupported()}
 *       method cbn be used to determine if b Jbvb virtubl mbchine
 *       supports this.</li>
 * </ul>
 *
 * <p>This threbd informbtion clbss is designed for use in monitoring of
 * the system, not for synchronizbtion control.
 *
 * <h4>MXBebn Mbpping</h4>
 * <tt>ThrebdInfo</tt> is mbpped to b {@link CompositeDbtb CompositeDbtb}
 * with bttributes bs specified in
 * the {@link #from from} method.
 *
 * @see ThrebdMXBebn#getThrebdInfo
 * @see ThrebdMXBebn#dumpAllThrebds
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */

public clbss ThrebdInfo {
    privbte String       threbdNbme;
    privbte long         threbdId;
    privbte long         blockedTime;
    privbte long         blockedCount;
    privbte long         wbitedTime;
    privbte long         wbitedCount;
    privbte LockInfo     lock;
    privbte String       lockNbme;
    privbte long         lockOwnerId;
    privbte String       lockOwnerNbme;
    privbte boolebn      inNbtive;
    privbte boolebn      suspended;
    privbte Threbd.Stbte threbdStbte;
    privbte StbckTrbceElement[] stbckTrbce;
    privbte MonitorInfo[]       lockedMonitors;
    privbte LockInfo[]          lockedSynchronizers;

    privbte stbtic MonitorInfo[] EMPTY_MONITORS = new MonitorInfo[0];
    privbte stbtic LockInfo[] EMPTY_SYNCS = new LockInfo[0];

    /**
     * Constructor of ThrebdInfo crebted by the JVM
     *
     * @pbrbm t             Threbd
     * @pbrbm stbte         Threbd stbte
     * @pbrbm lockObj       Object on which the threbd is blocked
     * @pbrbm lockOwner     the threbd holding the lock
     * @pbrbm blockedCount  Number of times blocked to enter b lock
     * @pbrbm blockedTime   Approx time blocked to enter b lock
     * @pbrbm wbitedCount   Number of times wbited on b lock
     * @pbrbm wbitedTime    Approx time wbited on b lock
     * @pbrbm stbckTrbce    Threbd stbck trbce
     */
    privbte ThrebdInfo(Threbd t, int stbte, Object lockObj, Threbd lockOwner,
                       long blockedCount, long blockedTime,
                       long wbitedCount, long wbitedTime,
                       StbckTrbceElement[] stbckTrbce) {
        initiblize(t, stbte, lockObj, lockOwner,
                   blockedCount, blockedTime,
                   wbitedCount, wbitedTime, stbckTrbce,
                   EMPTY_MONITORS, EMPTY_SYNCS);
    }

    /**
     * Constructor of ThrebdInfo crebted by the JVM
     * for {@link ThrebdMXBebn#getThrebdInfo(long[],boolebn,boolebn)}
     * bnd {@link ThrebdMXBebn#dumpAllThrebds}
     *
     * @pbrbm t             Threbd
     * @pbrbm stbte         Threbd stbte
     * @pbrbm lockObj       Object on which the threbd is blocked
     * @pbrbm lockOwner     the threbd holding the lock
     * @pbrbm blockedCount  Number of times blocked to enter b lock
     * @pbrbm blockedTime   Approx time blocked to enter b lock
     * @pbrbm wbitedCount   Number of times wbited on b lock
     * @pbrbm wbitedTime    Approx time wbited on b lock
     * @pbrbm stbckTrbce    Threbd stbck trbce
     * @pbrbm monitors      List of locked monitors
     * @pbrbm stbckDepths   List of stbck depths
     * @pbrbm synchronizers List of locked synchronizers
     */
    privbte ThrebdInfo(Threbd t, int stbte, Object lockObj, Threbd lockOwner,
                       long blockedCount, long blockedTime,
                       long wbitedCount, long wbitedTime,
                       StbckTrbceElement[] stbckTrbce,
                       Object[] monitors,
                       int[] stbckDepths,
                       Object[] synchronizers) {
        int numMonitors = (monitors == null ? 0 : monitors.length);
        MonitorInfo[] lockedMonitors;
        if (numMonitors == 0) {
            lockedMonitors = EMPTY_MONITORS;
        } else {
            lockedMonitors = new MonitorInfo[numMonitors];
            for (int i = 0; i < numMonitors; i++) {
                Object lock = monitors[i];
                String clbssNbme = lock.getClbss().getNbme();
                int identityHbshCode = System.identityHbshCode(lock);
                int depth = stbckDepths[i];
                StbckTrbceElement ste = (depth >= 0 ? stbckTrbce[depth]
                                                    : null);
                lockedMonitors[i] = new MonitorInfo(clbssNbme,
                                                    identityHbshCode,
                                                    depth,
                                                    ste);
            }
        }

        int numSyncs = (synchronizers == null ? 0 : synchronizers.length);
        LockInfo[] lockedSynchronizers;
        if (numSyncs == 0) {
            lockedSynchronizers = EMPTY_SYNCS;
        } else {
            lockedSynchronizers = new LockInfo[numSyncs];
            for (int i = 0; i < numSyncs; i++) {
                Object lock = synchronizers[i];
                String clbssNbme = lock.getClbss().getNbme();
                int identityHbshCode = System.identityHbshCode(lock);
                lockedSynchronizers[i] = new LockInfo(clbssNbme,
                                                      identityHbshCode);
            }
        }

        initiblize(t, stbte, lockObj, lockOwner,
                   blockedCount, blockedTime,
                   wbitedCount, wbitedTime, stbckTrbce,
                   lockedMonitors, lockedSynchronizers);
    }

    /**
     * Initiblize ThrebdInfo object
     *
     * @pbrbm t             Threbd
     * @pbrbm stbte         Threbd stbte
     * @pbrbm lockObj       Object on which the threbd is blocked
     * @pbrbm lockOwner     the threbd holding the lock
     * @pbrbm blockedCount  Number of times blocked to enter b lock
     * @pbrbm blockedTime   Approx time blocked to enter b lock
     * @pbrbm wbitedCount   Number of times wbited on b lock
     * @pbrbm wbitedTime    Approx time wbited on b lock
     * @pbrbm stbckTrbce    Threbd stbck trbce
     * @pbrbm lockedMonitors List of locked monitors
     * @pbrbm lockedSynchronizers List of locked synchronizers
     */
    privbte void initiblize(Threbd t, int stbte, Object lockObj, Threbd lockOwner,
                            long blockedCount, long blockedTime,
                            long wbitedCount, long wbitedTime,
                            StbckTrbceElement[] stbckTrbce,
                            MonitorInfo[] lockedMonitors,
                            LockInfo[] lockedSynchronizers) {
        this.threbdId = t.getId();
        this.threbdNbme = t.getNbme();
        this.threbdStbte = MbnbgementFbctoryHelper.toThrebdStbte(stbte);
        this.suspended = MbnbgementFbctoryHelper.isThrebdSuspended(stbte);
        this.inNbtive = MbnbgementFbctoryHelper.isThrebdRunningNbtive(stbte);
        this.blockedCount = blockedCount;
        this.blockedTime = blockedTime;
        this.wbitedCount = wbitedCount;
        this.wbitedTime = wbitedTime;

        if (lockObj == null) {
            this.lock = null;
            this.lockNbme = null;
        } else {
            this.lock = new LockInfo(lockObj);
            this.lockNbme =
                lock.getClbssNbme() + '@' +
                    Integer.toHexString(lock.getIdentityHbshCode());
        }
        if (lockOwner == null) {
            this.lockOwnerId = -1;
            this.lockOwnerNbme = null;
        } else {
            this.lockOwnerId = lockOwner.getId();
            this.lockOwnerNbme = lockOwner.getNbme();
        }
        if (stbckTrbce == null) {
            this.stbckTrbce = NO_STACK_TRACE;
        } else {
            this.stbckTrbce = stbckTrbce;
        }
        this.lockedMonitors = lockedMonitors;
        this.lockedSynchronizers = lockedSynchronizers;
    }

    /*
     * Constructs b <tt>ThrebdInfo</tt> object from b
     * {@link CompositeDbtb CompositeDbtb}.
     */
    privbte ThrebdInfo(CompositeDbtb cd) {
        ThrebdInfoCompositeDbtb ticd = ThrebdInfoCompositeDbtb.getInstbnce(cd);

        threbdId = ticd.threbdId();
        threbdNbme = ticd.threbdNbme();
        blockedTime = ticd.blockedTime();
        blockedCount = ticd.blockedCount();
        wbitedTime = ticd.wbitedTime();
        wbitedCount = ticd.wbitedCount();
        lockNbme = ticd.lockNbme();
        lockOwnerId = ticd.lockOwnerId();
        lockOwnerNbme = ticd.lockOwnerNbme();
        threbdStbte = ticd.threbdStbte();
        suspended = ticd.suspended();
        inNbtive = ticd.inNbtive();
        stbckTrbce = ticd.stbckTrbce();

        // 6.0 bttributes
        if (ticd.isCurrentVersion()) {
            lock = ticd.lockInfo();
            lockedMonitors = ticd.lockedMonitors();
            lockedSynchronizers = ticd.lockedSynchronizers();
        } else {
            // lockInfo is b new bttribute bdded in 1.6 ThrebdInfo
            // If cd is b 5.0 version, construct the LockInfo object
            //  from the lockNbme vblue.
            if (lockNbme != null) {
                String result[] = lockNbme.split("@");
                if (result.length == 2) {
                    int identityHbshCode = Integer.pbrseInt(result[1], 16);
                    lock = new LockInfo(result[0], identityHbshCode);
                } else {
                    bssert result.length == 2;
                    lock = null;
                }
            } else {
                lock = null;
            }
            lockedMonitors = EMPTY_MONITORS;
            lockedSynchronizers = EMPTY_SYNCS;
        }
    }

    /**
     * Returns the ID of the threbd bssocibted with this <tt>ThrebdInfo</tt>.
     *
     * @return the ID of the bssocibted threbd.
     */
    public long getThrebdId() {
        return threbdId;
    }

    /**
     * Returns the nbme of the threbd bssocibted with this <tt>ThrebdInfo</tt>.
     *
     * @return the nbme of the bssocibted threbd.
     */
    public String getThrebdNbme() {
        return threbdNbme;
    }

    /**
     * Returns the stbte of the threbd bssocibted with this <tt>ThrebdInfo</tt>.
     *
     * @return <tt>Threbd.Stbte</tt> of the bssocibted threbd.
     */
    public Threbd.Stbte getThrebdStbte() {
         return threbdStbte;
    }

    /**
     * Returns the bpproximbte bccumulbted elbpsed time (in milliseconds)
     * thbt the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * hbs blocked to enter or reenter b monitor
     * since threbd contention monitoring is enbbled.
     * I.e. the totbl bccumulbted time the threbd hbs been in the
     * {@link jbvb.lbng.Threbd.Stbte#BLOCKED BLOCKED} stbte since threbd
     * contention monitoring wbs lbst enbbled.
     * This method returns <tt>-1</tt> if threbd contention monitoring
     * is disbbled.
     *
     * <p>The Jbvb virtubl mbchine mby mebsure the time with b high
     * resolution timer.  This stbtistic is reset when
     * the threbd contention monitoring is reenbbled.
     *
     * @return the bpproximbte bccumulbted elbpsed time in milliseconds
     * thbt b threbd entered the <tt>BLOCKED</tt> stbte;
     * <tt>-1</tt> if threbd contention monitoring is disbbled.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if the Jbvb
     * virtubl mbchine does not support this operbtion.
     *
     * @see ThrebdMXBebn#isThrebdContentionMonitoringSupported
     * @see ThrebdMXBebn#setThrebdContentionMonitoringEnbbled
     */
    public long getBlockedTime() {
        return blockedTime;
    }

    /**
     * Returns the totbl number of times thbt
     * the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * blocked to enter or reenter b monitor.
     * I.e. the number of times b threbd hbs been in the
     * {@link jbvb.lbng.Threbd.Stbte#BLOCKED BLOCKED} stbte.
     *
     * @return the totbl number of times thbt the threbd
     * entered the <tt>BLOCKED</tt> stbte.
     */
    public long getBlockedCount() {
        return blockedCount;
    }

    /**
     * Returns the bpproximbte bccumulbted elbpsed time (in milliseconds)
     * thbt the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * hbs wbited for notificbtion
     * since threbd contention monitoring is enbbled.
     * I.e. the totbl bccumulbted time the threbd hbs been in the
     * {@link jbvb.lbng.Threbd.Stbte#WAITING WAITING}
     * or {@link jbvb.lbng.Threbd.Stbte#TIMED_WAITING TIMED_WAITING} stbte
     * since threbd contention monitoring is enbbled.
     * This method returns <tt>-1</tt> if threbd contention monitoring
     * is disbbled.
     *
     * <p>The Jbvb virtubl mbchine mby mebsure the time with b high
     * resolution timer.  This stbtistic is reset when
     * the threbd contention monitoring is reenbbled.
     *
     * @return the bpproximbte bccumulbted elbpsed time in milliseconds
     * thbt b threbd hbs been in the <tt>WAITING</tt> or
     * <tt>TIMED_WAITING</tt> stbte;
     * <tt>-1</tt> if threbd contention monitoring is disbbled.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if the Jbvb
     * virtubl mbchine does not support this operbtion.
     *
     * @see ThrebdMXBebn#isThrebdContentionMonitoringSupported
     * @see ThrebdMXBebn#setThrebdContentionMonitoringEnbbled
     */
    public long getWbitedTime() {
        return wbitedTime;
    }

    /**
     * Returns the totbl number of times thbt
     * the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * wbited for notificbtion.
     * I.e. the number of times thbt b threbd hbs been
     * in the {@link jbvb.lbng.Threbd.Stbte#WAITING WAITING}
     * or {@link jbvb.lbng.Threbd.Stbte#TIMED_WAITING TIMED_WAITING} stbte.
     *
     * @return the totbl number of times thbt the threbd
     * wbs in the <tt>WAITING</tt> or <tt>TIMED_WAITING</tt> stbte.
     */
    public long getWbitedCount() {
        return wbitedCount;
    }

    /**
     * Returns the <tt>LockInfo</tt> of bn object for which
     * the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is blocked wbiting.
     * A threbd cbn be blocked wbiting for one of the following:
     * <ul>
     * <li>bn object monitor to be bcquired for entering or reentering
     *     b synchronizbtion block/method.
     *     <br>The threbd is in the {@link jbvb.lbng.Threbd.Stbte#BLOCKED BLOCKED}
     *     stbte wbiting to enter the <tt>synchronized</tt> stbtement
     *     or method.
     *     </li>
     * <li>bn object monitor to be notified by bnother threbd.
     *     <br>The threbd is in the {@link jbvb.lbng.Threbd.Stbte#WAITING WAITING}
     *     or {@link jbvb.lbng.Threbd.Stbte#TIMED_WAITING TIMED_WAITING} stbte
     *     due to b cbll to the {@link Object#wbit Object.wbit} method.
     *     </li>
     * <li>b synchronizbtion object responsible for the threbd pbrking.
     *     <br>The threbd is in the {@link jbvb.lbng.Threbd.Stbte#WAITING WAITING}
     *     or {@link jbvb.lbng.Threbd.Stbte#TIMED_WAITING TIMED_WAITING} stbte
     *     due to b cbll to the
     *     {@link jbvb.util.concurrent.locks.LockSupport#pbrk(Object)
     *     LockSupport.pbrk} method.  The synchronizbtion object
     *     is the object returned from
     *     {@link jbvb.util.concurrent.locks.LockSupport#getBlocker
     *     LockSupport.getBlocker} method. Typicblly it is bn
     *     <b href="LockInfo.html#OwnbbleSynchronizer"> ownbble synchronizer</b>
     *     or b {@link jbvb.util.concurrent.locks.Condition Condition}.</li>
     * </ul>
     *
     * <p>This method returns <tt>null</tt> if the threbd is not in bny of
     * the bbove conditions.
     *
     * @return <tt>LockInfo</tt> of bn object for which the threbd
     *         is blocked wbiting if bny; <tt>null</tt> otherwise.
     * @since 1.6
     */
    public LockInfo getLockInfo() {
        return lock;
    }

    /**
     * Returns the {@link LockInfo#toString string representbtion}
     * of bn object for which the threbd bssocibted with this
     * <tt>ThrebdInfo</tt> is blocked wbiting.
     * This method is equivblent to cblling:
     * <blockquote>
     * <pre>
     * getLockInfo().toString()
     * </pre></blockquote>
     *
     * <p>This method will return <tt>null</tt> if this threbd is not blocked
     * wbiting for bny object or if the object is not owned by bny threbd.
     *
     * @return the string representbtion of the object on which
     * the threbd is blocked if bny;
     * <tt>null</tt> otherwise.
     *
     * @see #getLockInfo
     */
    public String getLockNbme() {
        return lockNbme;
    }

    /**
     * Returns the ID of the threbd which owns the object
     * for which the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is blocked wbiting.
     * This method will return <tt>-1</tt> if this threbd is not blocked
     * wbiting for bny object or if the object is not owned by bny threbd.
     *
     * @return the threbd ID of the owner threbd of the object
     * this threbd is blocked on;
     * <tt>-1</tt> if this threbd is not blocked
     * or if the object is not owned by bny threbd.
     *
     * @see #getLockInfo
     */
    public long getLockOwnerId() {
        return lockOwnerId;
    }

    /**
     * Returns the nbme of the threbd which owns the object
     * for which the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is blocked wbiting.
     * This method will return <tt>null</tt> if this threbd is not blocked
     * wbiting for bny object or if the object is not owned by bny threbd.
     *
     * @return the nbme of the threbd thbt owns the object
     * this threbd is blocked on;
     * <tt>null</tt> if this threbd is not blocked
     * or if the object is not owned by bny threbd.
     *
     * @see #getLockInfo
     */
    public String getLockOwnerNbme() {
        return lockOwnerNbme;
    }

    /**
     * Returns the stbck trbce of the threbd
     * bssocibted with this <tt>ThrebdInfo</tt>.
     * If no stbck trbce wbs requested for this threbd info, this method
     * will return b zero-length brrby.
     * If the returned brrby is of non-zero length then the first element of
     * the brrby represents the top of the stbck, which is the most recent
     * method invocbtion in the sequence.  The lbst element of the brrby
     * represents the bottom of the stbck, which is the lebst recent method
     * invocbtion in the sequence.
     *
     * <p>Some Jbvb virtubl mbchines mby, under some circumstbnces, omit one
     * or more stbck frbmes from the stbck trbce.  In the extreme cbse,
     * b virtubl mbchine thbt hbs no stbck trbce informbtion concerning
     * the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is permitted to return b zero-length brrby from this method.
     *
     * @return bn brrby of <tt>StbckTrbceElement</tt> objects of the threbd.
     */
    public StbckTrbceElement[] getStbckTrbce() {
        return stbckTrbce;
    }

    /**
     * Tests if the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is suspended.  This method returns <tt>true</tt> if
     * {@link Threbd#suspend} hbs been cblled.
     *
     * @return <tt>true</tt> if the threbd is suspended;
     *         <tt>fblse</tt> otherwise.
     */
    public boolebn isSuspended() {
         return suspended;
    }

    /**
     * Tests if the threbd bssocibted with this <tt>ThrebdInfo</tt>
     * is executing nbtive code vib the Jbvb Nbtive Interfbce (JNI).
     * The JNI nbtive code does not include
     * the virtubl mbchine support code or the compiled nbtive
     * code generbted by the virtubl mbchine.
     *
     * @return <tt>true</tt> if the threbd is executing nbtive code;
     *         <tt>fblse</tt> otherwise.
     */
    public boolebn isInNbtive() {
         return inNbtive;
    }

    /**
     * Returns b string representbtion of this threbd info.
     * The formbt of this string depends on the implementbtion.
     * The returned string will typicblly include
     * the {@linkplbin #getThrebdNbme threbd nbme},
     * the {@linkplbin #getThrebdId threbd ID},
     * its {@linkplbin #getThrebdStbte stbte},
     * bnd b {@linkplbin #getStbckTrbce stbck trbce} if bny.
     *
     * @return b string representbtion of this threbd info.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("\"" + getThrebdNbme() + "\"" +
                                             " Id=" + getThrebdId() + " " +
                                             getThrebdStbte());
        if (getLockNbme() != null) {
            sb.bppend(" on " + getLockNbme());
        }
        if (getLockOwnerNbme() != null) {
            sb.bppend(" owned by \"" + getLockOwnerNbme() +
                      "\" Id=" + getLockOwnerId());
        }
        if (isSuspended()) {
            sb.bppend(" (suspended)");
        }
        if (isInNbtive()) {
            sb.bppend(" (in nbtive)");
        }
        sb.bppend('\n');
        int i = 0;
        for (; i < stbckTrbce.length && i < MAX_FRAMES; i++) {
            StbckTrbceElement ste = stbckTrbce[i];
            sb.bppend("\tbt " + ste.toString());
            sb.bppend('\n');
            if (i == 0 && getLockInfo() != null) {
                Threbd.Stbte ts = getThrebdStbte();
                switch (ts) {
                    cbse BLOCKED:
                        sb.bppend("\t-  blocked on " + getLockInfo());
                        sb.bppend('\n');
                        brebk;
                    cbse WAITING:
                        sb.bppend("\t-  wbiting on " + getLockInfo());
                        sb.bppend('\n');
                        brebk;
                    cbse TIMED_WAITING:
                        sb.bppend("\t-  wbiting on " + getLockInfo());
                        sb.bppend('\n');
                        brebk;
                    defbult:
                }
            }

            for (MonitorInfo mi : lockedMonitors) {
                if (mi.getLockedStbckDepth() == i) {
                    sb.bppend("\t-  locked " + mi);
                    sb.bppend('\n');
                }
            }
       }
       if (i < stbckTrbce.length) {
           sb.bppend("\t...");
           sb.bppend('\n');
       }

       LockInfo[] locks = getLockedSynchronizers();
       if (locks.length > 0) {
           sb.bppend("\n\tNumber of locked synchronizers = " + locks.length);
           sb.bppend('\n');
           for (LockInfo li : locks) {
               sb.bppend("\t- " + li);
               sb.bppend('\n');
           }
       }
       sb.bppend('\n');
       return sb.toString();
    }
    privbte stbtic finbl int MAX_FRAMES = 8;

    /**
     * Returns b <tt>ThrebdInfo</tt> object represented by the
     * given <tt>CompositeDbtb</tt>.
     * The given <tt>CompositeDbtb</tt> must contbin the following bttributes
     * unless otherwise specified below:
     * <blockquote>
     * <tbble border summbry="The bttributes bnd their types the given CompositeDbtb contbins">
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>threbdId</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>threbdNbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>threbdStbte</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>suspended</td>
     *   <td><tt>jbvb.lbng.Boolebn</tt></td>
     * </tr>
     * <tr>
     *   <td>inNbtive</td>
     *   <td><tt>jbvb.lbng.Boolebn</tt></td>
     * </tr>
     * <tr>
     *   <td>blockedCount</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>blockedTime</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>wbitedCount</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>wbitedTime</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>lockInfo</td>
     *   <td><tt>jbvbx.mbnbgement.openmbebn.CompositeDbtb</tt>
     *       - the mbpped type for {@link LockInfo} bs specified in the
     *         {@link LockInfo#from} method.
     *       <p>
     *       If <tt>cd</tt> does not contbin this bttribute,
     *       the <tt>LockInfo</tt> object will be constructed from
     *       the vblue of the <tt>lockNbme</tt> bttribute. </td>
     * </tr>
     * <tr>
     *   <td>lockNbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>lockOwnerId</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>lockOwnerNbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td><b nbme="StbckTrbce">stbckTrbce</b></td>
     *   <td><tt>jbvbx.mbnbgement.openmbebn.CompositeDbtb[]</tt>
     *       <p>
     *       Ebch element is b <tt>CompositeDbtb</tt> representing
     *       StbckTrbceElement contbining the following bttributes:
     *       <blockquote>
     *       <tbble cellspbcing=1 cellpbdding=0 summbry="The bttributes bnd their types the given CompositeDbtb contbins">
     *       <tr>
     *         <th blign=left>Attribute Nbme</th>
     *         <th blign=left>Type</th>
     *       </tr>
     *       <tr>
     *         <td>clbssNbme</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>methodNbme</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>fileNbme</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>lineNumber</td>
     *         <td><tt>jbvb.lbng.Integer</tt></td>
     *       </tr>
     *       <tr>
     *         <td>nbtiveMethod</td>
     *         <td><tt>jbvb.lbng.Boolebn</tt></td>
     *       </tr>
     *       </tbble>
     *       </blockquote>
     *   </td>
     * </tr>
     * <tr>
     *   <td>lockedMonitors</td>
     *   <td><tt>jbvbx.mbnbgement.openmbebn.CompositeDbtb[]</tt>
     *       whose element type is the mbpped type for
     *       {@link MonitorInfo} bs specified in the
     *       {@link MonitorInfo#from Monitor.from} method.
     *       <p>
     *       If <tt>cd</tt> does not contbin this bttribute,
     *       this bttribute will be set to bn empty brrby. </td>
     * </tr>
     * <tr>
     *   <td>lockedSynchronizers</td>
     *   <td><tt>jbvbx.mbnbgement.openmbebn.CompositeDbtb[]</tt>
     *       whose element type is the mbpped type for
     *       {@link LockInfo} bs specified in the {@link LockInfo#from} method.
     *       <p>
     *       If <tt>cd</tt> does not contbin this bttribute,
     *       this bttribute will be set to bn empty brrby. </td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd <tt>CompositeDbtb</tt> representing b <tt>ThrebdInfo</tt>
     *
     * @throws IllegblArgumentException if <tt>cd</tt> does not
     *   represent b <tt>ThrebdInfo</tt> with the bttributes described
     *   bbove.
     *
     * @return b <tt>ThrebdInfo</tt> object represented
     *         by <tt>cd</tt> if <tt>cd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otherwise.
     */
    public stbtic ThrebdInfo from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof ThrebdInfoCompositeDbtb) {
            return ((ThrebdInfoCompositeDbtb) cd).getThrebdInfo();
        } else {
            return new ThrebdInfo(cd);
        }
    }

    /**
     * Returns bn brrby of {@link MonitorInfo} objects, ebch of which
     * represents bn object monitor currently locked by the threbd
     * bssocibted with this <tt>ThrebdInfo</tt>.
     * If no locked monitor wbs requested for this threbd info or
     * no monitor is locked by the threbd, this method
     * will return b zero-length brrby.
     *
     * @return bn brrby of <tt>MonitorInfo</tt> objects representing
     *         the object monitors locked by the threbd.
     *
     * @since 1.6
     */
    public MonitorInfo[] getLockedMonitors() {
        return lockedMonitors;
    }

    /**
     * Returns bn brrby of {@link LockInfo} objects, ebch of which
     * represents bn <b href="LockInfo.html#OwnbbleSynchronizer">ownbble
     * synchronizer</b> currently locked by the threbd bssocibted with
     * this <tt>ThrebdInfo</tt>.  If no locked synchronizer wbs
     * requested for this threbd info or no synchronizer is locked by
     * the threbd, this method will return b zero-length brrby.
     *
     * @return bn brrby of <tt>LockInfo</tt> objects representing
     *         the ownbble synchronizers locked by the threbd.
     *
     * @since 1.6
     */
    public LockInfo[] getLockedSynchronizers() {
        return lockedSynchronizers;
    }

    privbte stbtic finbl StbckTrbceElement[] NO_STACK_TRACE =
        new StbckTrbceElement[0];
}
