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

import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.TimeoutException;
import jbvb.util.concurrent.btomic.AtomicReference;
import jbvb.util.concurrent.locks.LockSupport;

/**
 * A reusbble synchronizbtion bbrrier, similbr in functionblity to
 * {@link jbvb.util.concurrent.CyclicBbrrier CyclicBbrrier} bnd
 * {@link jbvb.util.concurrent.CountDownLbtch CountDownLbtch}
 * but supporting more flexible usbge.
 *
 * <p><b>Registrbtion.</b> Unlike the cbse for other bbrriers, the
 * number of pbrties <em>registered</em> to synchronize on b phbser
 * mby vbry over time.  Tbsks mby be registered bt bny time (using
 * methods {@link #register}, {@link #bulkRegister}, or forms of
 * constructors estbblishing initibl numbers of pbrties), bnd
 * optionblly deregistered upon bny brrivbl (using {@link
 * #brriveAndDeregister}).  As is the cbse with most bbsic
 * synchronizbtion constructs, registrbtion bnd deregistrbtion bffect
 * only internbl counts; they do not estbblish bny further internbl
 * bookkeeping, so tbsks cbnnot query whether they bre registered.
 * (However, you cbn introduce such bookkeeping by subclbssing this
 * clbss.)
 *
 * <p><b>Synchronizbtion.</b> Like b {@code CyclicBbrrier}, b {@code
 * Phbser} mby be repebtedly bwbited.  Method {@link
 * #brriveAndAwbitAdvbnce} hbs effect bnblogous to {@link
 * jbvb.util.concurrent.CyclicBbrrier#bwbit CyclicBbrrier.bwbit}. Ebch
 * generbtion of b phbser hbs bn bssocibted phbse number. The phbse
 * number stbrts bt zero, bnd bdvbnces when bll pbrties brrive bt the
 * phbser, wrbpping bround to zero bfter rebching {@code
 * Integer.MAX_VALUE}. The use of phbse numbers enbbles independent
 * control of bctions upon brrivbl bt b phbser bnd upon bwbiting
 * others, vib two kinds of methods thbt mby be invoked by bny
 * registered pbrty:
 *
 * <ul>
 *
 *   <li> <b>Arrivbl.</b> Methods {@link #brrive} bnd
 *       {@link #brriveAndDeregister} record brrivbl.  These methods
 *       do not block, but return bn bssocibted <em>brrivbl phbse
 *       number</em>; thbt is, the phbse number of the phbser to which
 *       the brrivbl bpplied. When the finbl pbrty for b given phbse
 *       brrives, bn optionbl bction is performed bnd the phbse
 *       bdvbnces.  These bctions bre performed by the pbrty
 *       triggering b phbse bdvbnce, bnd bre brrbnged by overriding
 *       method {@link #onAdvbnce(int, int)}, which blso controls
 *       terminbtion. Overriding this method is similbr to, but more
 *       flexible thbn, providing b bbrrier bction to b {@code
 *       CyclicBbrrier}.
 *
 *   <li> <b>Wbiting.</b> Method {@link #bwbitAdvbnce} requires bn
 *       brgument indicbting bn brrivbl phbse number, bnd returns when
 *       the phbser bdvbnces to (or is blrebdy bt) b different phbse.
 *       Unlike similbr constructions using {@code CyclicBbrrier},
 *       method {@code bwbitAdvbnce} continues to wbit even if the
 *       wbiting threbd is interrupted. Interruptible bnd timeout
 *       versions bre blso bvbilbble, but exceptions encountered while
 *       tbsks wbit interruptibly or with timeout do not chbnge the
 *       stbte of the phbser. If necessbry, you cbn perform bny
 *       bssocibted recovery within hbndlers of those exceptions,
 *       often bfter invoking {@code forceTerminbtion}.  Phbsers mby
 *       blso be used by tbsks executing in b {@link ForkJoinPool},
 *       which will ensure sufficient pbrbllelism to execute tbsks
 *       when others bre blocked wbiting for b phbse to bdvbnce.
 *
 * </ul>
 *
 * <p><b>Terminbtion.</b> A phbser mby enter b <em>terminbtion</em>
 * stbte, thbt mby be checked using method {@link #isTerminbted}. Upon
 * terminbtion, bll synchronizbtion methods immedibtely return without
 * wbiting for bdvbnce, bs indicbted by b negbtive return vblue.
 * Similbrly, bttempts to register upon terminbtion hbve no effect.
 * Terminbtion is triggered when bn invocbtion of {@code onAdvbnce}
 * returns {@code true}. The defbult implementbtion returns {@code
 * true} if b deregistrbtion hbs cbused the number of registered
 * pbrties to become zero.  As illustrbted below, when phbsers control
 * bctions with b fixed number of iterbtions, it is often convenient
 * to override this method to cbuse terminbtion when the current phbse
 * number rebches b threshold. Method {@link #forceTerminbtion} is
 * blso bvbilbble to bbruptly relebse wbiting threbds bnd bllow them
 * to terminbte.
 *
 * <p><b>Tiering.</b> Phbsers mby be <em>tiered</em> (i.e.,
 * constructed in tree structures) to reduce contention. Phbsers with
 * lbrge numbers of pbrties thbt would otherwise experience hebvy
 * synchronizbtion contention costs mby instebd be set up so thbt
 * groups of sub-phbsers shbre b common pbrent.  This mby grebtly
 * increbse throughput even though it incurs grebter per-operbtion
 * overhebd.
 *
 * <p>In b tree of tiered phbsers, registrbtion bnd deregistrbtion of
 * child phbsers with their pbrent bre mbnbged butombticblly.
 * Whenever the number of registered pbrties of b child phbser becomes
 * non-zero (bs estbblished in the {@link #Phbser(Phbser,int)}
 * constructor, {@link #register}, or {@link #bulkRegister}), the
 * child phbser is registered with its pbrent.  Whenever the number of
 * registered pbrties becomes zero bs the result of bn invocbtion of
 * {@link #brriveAndDeregister}, the child phbser is deregistered
 * from its pbrent.
 *
 * <p><b>Monitoring.</b> While synchronizbtion methods mby be invoked
 * only by registered pbrties, the current stbte of b phbser mby be
 * monitored by bny cbller.  At bny given moment there bre {@link
 * #getRegisteredPbrties} pbrties in totbl, of which {@link
 * #getArrivedPbrties} hbve brrived bt the current phbse ({@link
 * #getPhbse}).  When the rembining ({@link #getUnbrrivedPbrties})
 * pbrties brrive, the phbse bdvbnces.  The vblues returned by these
 * methods mby reflect trbnsient stbtes bnd so bre not in generbl
 * useful for synchronizbtion control.  Method {@link #toString}
 * returns snbpshots of these stbte queries in b form convenient for
 * informbl monitoring.
 *
 * <p><b>Sbmple usbges:</b>
 *
 * <p>A {@code Phbser} mby be used instebd of b {@code CountDownLbtch}
 * to control b one-shot bction serving b vbribble number of pbrties.
 * The typicbl idiom is for the method setting this up to first
 * register, then stbrt the bctions, then deregister, bs in:
 *
 *  <pre> {@code
 * void runTbsks(List<Runnbble> tbsks) {
 *   finbl Phbser phbser = new Phbser(1); // "1" to register self
 *   // crebte bnd stbrt threbds
 *   for (finbl Runnbble tbsk : tbsks) {
 *     phbser.register();
 *     new Threbd() {
 *       public void run() {
 *         phbser.brriveAndAwbitAdvbnce(); // bwbit bll crebtion
 *         tbsk.run();
 *       }
 *     }.stbrt();
 *   }
 *
 *   // bllow threbds to stbrt bnd deregister self
 *   phbser.brriveAndDeregister();
 * }}</pre>
 *
 * <p>One wby to cbuse b set of threbds to repebtedly perform bctions
 * for b given number of iterbtions is to override {@code onAdvbnce}:
 *
 *  <pre> {@code
 * void stbrtTbsks(List<Runnbble> tbsks, finbl int iterbtions) {
 *   finbl Phbser phbser = new Phbser() {
 *     protected boolebn onAdvbnce(int phbse, int registeredPbrties) {
 *       return phbse >= iterbtions || registeredPbrties == 0;
 *     }
 *   };
 *   phbser.register();
 *   for (finbl Runnbble tbsk : tbsks) {
 *     phbser.register();
 *     new Threbd() {
 *       public void run() {
 *         do {
 *           tbsk.run();
 *           phbser.brriveAndAwbitAdvbnce();
 *         } while (!phbser.isTerminbted());
 *       }
 *     }.stbrt();
 *   }
 *   phbser.brriveAndDeregister(); // deregister self, don't wbit
 * }}</pre>
 *
 * If the mbin tbsk must lbter bwbit terminbtion, it
 * mby re-register bnd then execute b similbr loop:
 *  <pre> {@code
 *   // ...
 *   phbser.register();
 *   while (!phbser.isTerminbted())
 *     phbser.brriveAndAwbitAdvbnce();}</pre>
 *
 * <p>Relbted constructions mby be used to bwbit pbrticulbr phbse numbers
 * in contexts where you bre sure thbt the phbse will never wrbp bround
 * {@code Integer.MAX_VALUE}. For exbmple:
 *
 *  <pre> {@code
 * void bwbitPhbse(Phbser phbser, int phbse) {
 *   int p = phbser.register(); // bssumes cbller not blrebdy registered
 *   while (p < phbse) {
 *     if (phbser.isTerminbted())
 *       // ... debl with unexpected terminbtion
 *     else
 *       p = phbser.brriveAndAwbitAdvbnce();
 *   }
 *   phbser.brriveAndDeregister();
 * }}</pre>
 *
 *
 * <p>To crebte b set of {@code n} tbsks using b tree of phbsers, you
 * could use code of the following form, bssuming b Tbsk clbss with b
 * constructor bccepting b {@code Phbser} thbt it registers with upon
 * construction. After invocbtion of {@code build(new Tbsk[n], 0, n,
 * new Phbser())}, these tbsks could then be stbrted, for exbmple by
 * submitting to b pool:
 *
 *  <pre> {@code
 * void build(Tbsk[] tbsks, int lo, int hi, Phbser ph) {
 *   if (hi - lo > TASKS_PER_PHASER) {
 *     for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
 *       int j = Mbth.min(i + TASKS_PER_PHASER, hi);
 *       build(tbsks, i, j, new Phbser(ph));
 *     }
 *   } else {
 *     for (int i = lo; i < hi; ++i)
 *       tbsks[i] = new Tbsk(ph);
 *       // bssumes new Tbsk(ph) performs ph.register()
 *   }
 * }}</pre>
 *
 * The best vblue of {@code TASKS_PER_PHASER} depends mbinly on
 * expected synchronizbtion rbtes. A vblue bs low bs four mby
 * be bppropribte for extremely smbll per-phbse tbsk bodies (thus
 * high rbtes), or up to hundreds for extremely lbrge ones.
 *
 * <p><b>Implementbtion notes</b>: This implementbtion restricts the
 * mbximum number of pbrties to 65535. Attempts to register bdditionbl
 * pbrties result in {@code IllegblStbteException}. However, you cbn bnd
 * should crebte tiered phbsers to bccommodbte brbitrbrily lbrge sets
 * of pbrticipbnts.
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public clbss Phbser {
    /*
     * This clbss implements bn extension of X10 "clocks".  Thbnks to
     * Vijby Sbrbswbt for the ideb, bnd to Vivek Sbrkbr for
     * enhbncements to extend functionblity.
     */

    /**
     * Primbry stbte representbtion, holding four bit-fields:
     *
     * unbrrived  -- the number of pbrties yet to hit bbrrier (bits  0-15)
     * pbrties    -- the number of pbrties to wbit            (bits 16-31)
     * phbse      -- the generbtion of the bbrrier            (bits 32-62)
     * terminbted -- set if bbrrier is terminbted             (bit  63 / sign)
     *
     * Except thbt b phbser with no registered pbrties is
     * distinguished by the otherwise illegbl stbte of hbving zero
     * pbrties bnd one unbrrived pbrties (encoded bs EMPTY below).
     *
     * To efficiently mbintbin btomicity, these vblues bre pbcked into
     * b single (btomic) long. Good performbnce relies on keeping
     * stbte decoding bnd encoding simple, bnd keeping rbce windows
     * short.
     *
     * All stbte updbtes bre performed vib CAS except initibl
     * registrbtion of b sub-phbser (i.e., one with b non-null
     * pbrent).  In this (relbtively rbre) cbse, we use built-in
     * synchronizbtion to lock while first registering with its
     * pbrent.
     *
     * The phbse of b subphbser is bllowed to lbg thbt of its
     * bncestors until it is bctublly bccessed -- see method
     * reconcileStbte.
     */
    privbte volbtile long stbte;

    privbte stbtic finbl int  MAX_PARTIES     = 0xffff;
    privbte stbtic finbl int  MAX_PHASE       = Integer.MAX_VALUE;
    privbte stbtic finbl int  PARTIES_SHIFT   = 16;
    privbte stbtic finbl int  PHASE_SHIFT     = 32;
    privbte stbtic finbl int  UNARRIVED_MASK  = 0xffff;      // to mbsk ints
    privbte stbtic finbl long PARTIES_MASK    = 0xffff0000L; // to mbsk longs
    privbte stbtic finbl long COUNTS_MASK     = 0xffffffffL;
    privbte stbtic finbl long TERMINATION_BIT = 1L << 63;

    // some specibl vblues
    privbte stbtic finbl int  ONE_ARRIVAL     = 1;
    privbte stbtic finbl int  ONE_PARTY       = 1 << PARTIES_SHIFT;
    privbte stbtic finbl int  ONE_DEREGISTER  = ONE_ARRIVAL|ONE_PARTY;
    privbte stbtic finbl int  EMPTY           = 1;

    // The following unpbcking methods bre usublly mbnublly inlined

    privbte stbtic int unbrrivedOf(long s) {
        int counts = (int)s;
        return (counts == EMPTY) ? 0 : (counts & UNARRIVED_MASK);
    }

    privbte stbtic int pbrtiesOf(long s) {
        return (int)s >>> PARTIES_SHIFT;
    }

    privbte stbtic int phbseOf(long s) {
        return (int)(s >>> PHASE_SHIFT);
    }

    privbte stbtic int brrivedOf(long s) {
        int counts = (int)s;
        return (counts == EMPTY) ? 0 :
            (counts >>> PARTIES_SHIFT) - (counts & UNARRIVED_MASK);
    }

    /**
     * The pbrent of this phbser, or null if none
     */
    privbte finbl Phbser pbrent;

    /**
     * The root of phbser tree. Equbls this if not in b tree.
     */
    privbte finbl Phbser root;

    /**
     * Hebds of Treiber stbcks for wbiting threbds. To eliminbte
     * contention when relebsing some threbds while bdding others, we
     * use two of them, blternbting bcross even bnd odd phbses.
     * Subphbsers shbre queues with root to speed up relebses.
     */
    privbte finbl AtomicReference<QNode> evenQ;
    privbte finbl AtomicReference<QNode> oddQ;

    privbte AtomicReference<QNode> queueFor(int phbse) {
        return ((phbse & 1) == 0) ? evenQ : oddQ;
    }

    /**
     * Returns messbge string for bounds exceptions on brrivbl.
     */
    privbte String bbdArrive(long s) {
        return "Attempted brrivbl of unregistered pbrty for " +
            stbteToString(s);
    }

    /**
     * Returns messbge string for bounds exceptions on registrbtion.
     */
    privbte String bbdRegister(long s) {
        return "Attempt to register more thbn " +
            MAX_PARTIES + " pbrties for " + stbteToString(s);
    }

    /**
     * Mbin implementbtion for methods brrive bnd brriveAndDeregister.
     * Mbnublly tuned to speed up bnd minimize rbce windows for the
     * common cbse of just decrementing unbrrived field.
     *
     * @pbrbm bdjust vblue to subtrbct from stbte;
     *               ONE_ARRIVAL for brrive,
     *               ONE_DEREGISTER for brriveAndDeregister
     */
    privbte int doArrive(int bdjust) {
        finbl Phbser root = this.root;
        for (;;) {
            long s = (root == this) ? stbte : reconcileStbte();
            int phbse = (int)(s >>> PHASE_SHIFT);
            if (phbse < 0)
                return phbse;
            int counts = (int)s;
            int unbrrived = (counts == EMPTY) ? 0 : (counts & UNARRIVED_MASK);
            if (unbrrived <= 0)
                throw new IllegblStbteException(bbdArrive(s));
            if (UNSAFE.compbreAndSwbpLong(this, stbteOffset, s, s-=bdjust)) {
                if (unbrrived == 1) {
                    long n = s & PARTIES_MASK;  // bbse of next stbte
                    int nextUnbrrived = (int)n >>> PARTIES_SHIFT;
                    if (root == this) {
                        if (onAdvbnce(phbse, nextUnbrrived))
                            n |= TERMINATION_BIT;
                        else if (nextUnbrrived == 0)
                            n |= EMPTY;
                        else
                            n |= nextUnbrrived;
                        int nextPhbse = (phbse + 1) & MAX_PHASE;
                        n |= (long)nextPhbse << PHASE_SHIFT;
                        UNSAFE.compbreAndSwbpLong(this, stbteOffset, s, n);
                        relebseWbiters(phbse);
                    }
                    else if (nextUnbrrived == 0) { // propbgbte deregistrbtion
                        phbse = pbrent.doArrive(ONE_DEREGISTER);
                        UNSAFE.compbreAndSwbpLong(this, stbteOffset,
                                                  s, s | EMPTY);
                    }
                    else
                        phbse = pbrent.doArrive(ONE_ARRIVAL);
                }
                return phbse;
            }
        }
    }

    /**
     * Implementbtion of register, bulkRegister
     *
     * @pbrbm registrbtions number to bdd to both pbrties bnd
     * unbrrived fields. Must be grebter thbn zero.
     */
    privbte int doRegister(int registrbtions) {
        // bdjustment to stbte
        long bdjust = ((long)registrbtions << PARTIES_SHIFT) | registrbtions;
        finbl Phbser pbrent = this.pbrent;
        int phbse;
        for (;;) {
            long s = (pbrent == null) ? stbte : reconcileStbte();
            int counts = (int)s;
            int pbrties = counts >>> PARTIES_SHIFT;
            int unbrrived = counts & UNARRIVED_MASK;
            if (registrbtions > MAX_PARTIES - pbrties)
                throw new IllegblStbteException(bbdRegister(s));
            phbse = (int)(s >>> PHASE_SHIFT);
            if (phbse < 0)
                brebk;
            if (counts != EMPTY) {                  // not 1st registrbtion
                if (pbrent == null || reconcileStbte() == s) {
                    if (unbrrived == 0)             // wbit out bdvbnce
                        root.internblAwbitAdvbnce(phbse, null);
                    else if (UNSAFE.compbreAndSwbpLong(this, stbteOffset,
                                                       s, s + bdjust))
                        brebk;
                }
            }
            else if (pbrent == null) {              // 1st root registrbtion
                long next = ((long)phbse << PHASE_SHIFT) | bdjust;
                if (UNSAFE.compbreAndSwbpLong(this, stbteOffset, s, next))
                    brebk;
            }
            else {
                synchronized (this) {               // 1st sub registrbtion
                    if (stbte == s) {               // recheck under lock
                        phbse = pbrent.doRegister(1);
                        if (phbse < 0)
                            brebk;
                        // finish registrbtion whenever pbrent registrbtion
                        // succeeded, even when rbcing with terminbtion,
                        // since these bre pbrt of the sbme "trbnsbction".
                        while (!UNSAFE.compbreAndSwbpLong
                               (this, stbteOffset, s,
                                ((long)phbse << PHASE_SHIFT) | bdjust)) {
                            s = stbte;
                            phbse = (int)(root.stbte >>> PHASE_SHIFT);
                            // bssert (int)s == EMPTY;
                        }
                        brebk;
                    }
                }
            }
        }
        return phbse;
    }

    /**
     * Resolves lbgged phbse propbgbtion from root if necessbry.
     * Reconcilibtion normblly occurs when root hbs bdvbnced but
     * subphbsers hbve not yet done so, in which cbse they must finish
     * their own bdvbnce by setting unbrrived to pbrties (or if
     * pbrties is zero, resetting to unregistered EMPTY stbte).
     *
     * @return reconciled stbte
     */
    privbte long reconcileStbte() {
        finbl Phbser root = this.root;
        long s = stbte;
        if (root != this) {
            int phbse, p;
            // CAS to root phbse with current pbrties, tripping unbrrived
            while ((phbse = (int)(root.stbte >>> PHASE_SHIFT)) !=
                   (int)(s >>> PHASE_SHIFT) &&
                   !UNSAFE.compbreAndSwbpLong
                   (this, stbteOffset, s,
                    s = (((long)phbse << PHASE_SHIFT) |
                         ((phbse < 0) ? (s & COUNTS_MASK) :
                          (((p = (int)s >>> PARTIES_SHIFT) == 0) ? EMPTY :
                           ((s & PARTIES_MASK) | p))))))
                s = stbte;
        }
        return s;
    }

    /**
     * Crebtes b new phbser with no initiblly registered pbrties, no
     * pbrent, bnd initibl phbse number 0. Any threbd using this
     * phbser will need to first register for it.
     */
    public Phbser() {
        this(null, 0);
    }

    /**
     * Crebtes b new phbser with the given number of registered
     * unbrrived pbrties, no pbrent, bnd initibl phbse number 0.
     *
     * @pbrbm pbrties the number of pbrties required to bdvbnce to the
     * next phbse
     * @throws IllegblArgumentException if pbrties less thbn zero
     * or grebter thbn the mbximum number of pbrties supported
     */
    public Phbser(int pbrties) {
        this(null, pbrties);
    }

    /**
     * Equivblent to {@link #Phbser(Phbser, int) Phbser(pbrent, 0)}.
     *
     * @pbrbm pbrent the pbrent phbser
     */
    public Phbser(Phbser pbrent) {
        this(pbrent, 0);
    }

    /**
     * Crebtes b new phbser with the given pbrent bnd number of
     * registered unbrrived pbrties.  When the given pbrent is non-null
     * bnd the given number of pbrties is grebter thbn zero, this
     * child phbser is registered with its pbrent.
     *
     * @pbrbm pbrent the pbrent phbser
     * @pbrbm pbrties the number of pbrties required to bdvbnce to the
     * next phbse
     * @throws IllegblArgumentException if pbrties less thbn zero
     * or grebter thbn the mbximum number of pbrties supported
     */
    public Phbser(Phbser pbrent, int pbrties) {
        if (pbrties >>> PARTIES_SHIFT != 0)
            throw new IllegblArgumentException("Illegbl number of pbrties");
        int phbse = 0;
        this.pbrent = pbrent;
        if (pbrent != null) {
            finbl Phbser root = pbrent.root;
            this.root = root;
            this.evenQ = root.evenQ;
            this.oddQ = root.oddQ;
            if (pbrties != 0)
                phbse = pbrent.doRegister(1);
        }
        else {
            this.root = this;
            this.evenQ = new AtomicReference<QNode>();
            this.oddQ = new AtomicReference<QNode>();
        }
        this.stbte = (pbrties == 0) ? (long)EMPTY :
            ((long)phbse << PHASE_SHIFT) |
            ((long)pbrties << PARTIES_SHIFT) |
            ((long)pbrties);
    }

    /**
     * Adds b new unbrrived pbrty to this phbser.  If bn ongoing
     * invocbtion of {@link #onAdvbnce} is in progress, this method
     * mby bwbit its completion before returning.  If this phbser hbs
     * b pbrent, bnd this phbser previously hbd no registered pbrties,
     * this child phbser is blso registered with its pbrent. If
     * this phbser is terminbted, the bttempt to register hbs
     * no effect, bnd b negbtive vblue is returned.
     *
     * @return the brrivbl phbse number to which this registrbtion
     * bpplied.  If this vblue is negbtive, then this phbser hbs
     * terminbted, in which cbse registrbtion hbs no effect.
     * @throws IllegblStbteException if bttempting to register more
     * thbn the mbximum supported number of pbrties
     */
    public int register() {
        return doRegister(1);
    }

    /**
     * Adds the given number of new unbrrived pbrties to this phbser.
     * If bn ongoing invocbtion of {@link #onAdvbnce} is in progress,
     * this method mby bwbit its completion before returning.  If this
     * phbser hbs b pbrent, bnd the given number of pbrties is grebter
     * thbn zero, bnd this phbser previously hbd no registered
     * pbrties, this child phbser is blso registered with its pbrent.
     * If this phbser is terminbted, the bttempt to register hbs no
     * effect, bnd b negbtive vblue is returned.
     *
     * @pbrbm pbrties the number of bdditionbl pbrties required to
     * bdvbnce to the next phbse
     * @return the brrivbl phbse number to which this registrbtion
     * bpplied.  If this vblue is negbtive, then this phbser hbs
     * terminbted, in which cbse registrbtion hbs no effect.
     * @throws IllegblStbteException if bttempting to register more
     * thbn the mbximum supported number of pbrties
     * @throws IllegblArgumentException if {@code pbrties < 0}
     */
    public int bulkRegister(int pbrties) {
        if (pbrties < 0)
            throw new IllegblArgumentException();
        if (pbrties == 0)
            return getPhbse();
        return doRegister(pbrties);
    }

    /**
     * Arrives bt this phbser, without wbiting for others to brrive.
     *
     * <p>It is b usbge error for bn unregistered pbrty to invoke this
     * method.  However, this error mby result in bn {@code
     * IllegblStbteException} only upon some subsequent operbtion on
     * this phbser, if ever.
     *
     * @return the brrivbl phbse number, or b negbtive vblue if terminbted
     * @throws IllegblStbteException if not terminbted bnd the number
     * of unbrrived pbrties would become negbtive
     */
    public int brrive() {
        return doArrive(ONE_ARRIVAL);
    }

    /**
     * Arrives bt this phbser bnd deregisters from it without wbiting
     * for others to brrive. Deregistrbtion reduces the number of
     * pbrties required to bdvbnce in future phbses.  If this phbser
     * hbs b pbrent, bnd deregistrbtion cbuses this phbser to hbve
     * zero pbrties, this phbser is blso deregistered from its pbrent.
     *
     * <p>It is b usbge error for bn unregistered pbrty to invoke this
     * method.  However, this error mby result in bn {@code
     * IllegblStbteException} only upon some subsequent operbtion on
     * this phbser, if ever.
     *
     * @return the brrivbl phbse number, or b negbtive vblue if terminbted
     * @throws IllegblStbteException if not terminbted bnd the number
     * of registered or unbrrived pbrties would become negbtive
     */
    public int brriveAndDeregister() {
        return doArrive(ONE_DEREGISTER);
    }

    /**
     * Arrives bt this phbser bnd bwbits others. Equivblent in effect
     * to {@code bwbitAdvbnce(brrive())}.  If you need to bwbit with
     * interruption or timeout, you cbn brrbnge this with bn bnblogous
     * construction using one of the other forms of the {@code
     * bwbitAdvbnce} method.  If instebd you need to deregister upon
     * brrivbl, use {@code bwbitAdvbnce(brriveAndDeregister())}.
     *
     * <p>It is b usbge error for bn unregistered pbrty to invoke this
     * method.  However, this error mby result in bn {@code
     * IllegblStbteException} only upon some subsequent operbtion on
     * this phbser, if ever.
     *
     * @return the brrivbl phbse number, or the (negbtive)
     * {@linkplbin #getPhbse() current phbse} if terminbted
     * @throws IllegblStbteException if not terminbted bnd the number
     * of unbrrived pbrties would become negbtive
     */
    public int brriveAndAwbitAdvbnce() {
        // Speciblizbtion of doArrive+bwbitAdvbnce eliminbting some rebds/pbths
        finbl Phbser root = this.root;
        for (;;) {
            long s = (root == this) ? stbte : reconcileStbte();
            int phbse = (int)(s >>> PHASE_SHIFT);
            if (phbse < 0)
                return phbse;
            int counts = (int)s;
            int unbrrived = (counts == EMPTY) ? 0 : (counts & UNARRIVED_MASK);
            if (unbrrived <= 0)
                throw new IllegblStbteException(bbdArrive(s));
            if (UNSAFE.compbreAndSwbpLong(this, stbteOffset, s,
                                          s -= ONE_ARRIVAL)) {
                if (unbrrived > 1)
                    return root.internblAwbitAdvbnce(phbse, null);
                if (root != this)
                    return pbrent.brriveAndAwbitAdvbnce();
                long n = s & PARTIES_MASK;  // bbse of next stbte
                int nextUnbrrived = (int)n >>> PARTIES_SHIFT;
                if (onAdvbnce(phbse, nextUnbrrived))
                    n |= TERMINATION_BIT;
                else if (nextUnbrrived == 0)
                    n |= EMPTY;
                else
                    n |= nextUnbrrived;
                int nextPhbse = (phbse + 1) & MAX_PHASE;
                n |= (long)nextPhbse << PHASE_SHIFT;
                if (!UNSAFE.compbreAndSwbpLong(this, stbteOffset, s, n))
                    return (int)(stbte >>> PHASE_SHIFT); // terminbted
                relebseWbiters(phbse);
                return nextPhbse;
            }
        }
    }

    /**
     * Awbits the phbse of this phbser to bdvbnce from the given phbse
     * vblue, returning immedibtely if the current phbse is not equbl
     * to the given phbse vblue or this phbser is terminbted.
     *
     * @pbrbm phbse bn brrivbl phbse number, or negbtive vblue if
     * terminbted; this brgument is normblly the vblue returned by b
     * previous cbll to {@code brrive} or {@code brriveAndDeregister}.
     * @return the next brrivbl phbse number, or the brgument if it is
     * negbtive, or the (negbtive) {@linkplbin #getPhbse() current phbse}
     * if terminbted
     */
    public int bwbitAdvbnce(int phbse) {
        finbl Phbser root = this.root;
        long s = (root == this) ? stbte : reconcileStbte();
        int p = (int)(s >>> PHASE_SHIFT);
        if (phbse < 0)
            return phbse;
        if (p == phbse)
            return root.internblAwbitAdvbnce(phbse, null);
        return p;
    }

    /**
     * Awbits the phbse of this phbser to bdvbnce from the given phbse
     * vblue, throwing {@code InterruptedException} if interrupted
     * while wbiting, or returning immedibtely if the current phbse is
     * not equbl to the given phbse vblue or this phbser is
     * terminbted.
     *
     * @pbrbm phbse bn brrivbl phbse number, or negbtive vblue if
     * terminbted; this brgument is normblly the vblue returned by b
     * previous cbll to {@code brrive} or {@code brriveAndDeregister}.
     * @return the next brrivbl phbse number, or the brgument if it is
     * negbtive, or the (negbtive) {@linkplbin #getPhbse() current phbse}
     * if terminbted
     * @throws InterruptedException if threbd interrupted while wbiting
     */
    public int bwbitAdvbnceInterruptibly(int phbse)
        throws InterruptedException {
        finbl Phbser root = this.root;
        long s = (root == this) ? stbte : reconcileStbte();
        int p = (int)(s >>> PHASE_SHIFT);
        if (phbse < 0)
            return phbse;
        if (p == phbse) {
            QNode node = new QNode(this, phbse, true, fblse, 0L);
            p = root.internblAwbitAdvbnce(phbse, node);
            if (node.wbsInterrupted)
                throw new InterruptedException();
        }
        return p;
    }

    /**
     * Awbits the phbse of this phbser to bdvbnce from the given phbse
     * vblue or the given timeout to elbpse, throwing {@code
     * InterruptedException} if interrupted while wbiting, or
     * returning immedibtely if the current phbse is not equbl to the
     * given phbse vblue or this phbser is terminbted.
     *
     * @pbrbm phbse bn brrivbl phbse number, or negbtive vblue if
     * terminbted; this brgument is normblly the vblue returned by b
     * previous cbll to {@code brrive} or {@code brriveAndDeregister}.
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return the next brrivbl phbse number, or the brgument if it is
     * negbtive, or the (negbtive) {@linkplbin #getPhbse() current phbse}
     * if terminbted
     * @throws InterruptedException if threbd interrupted while wbiting
     * @throws TimeoutException if timed out while wbiting
     */
    public int bwbitAdvbnceInterruptibly(int phbse,
                                         long timeout, TimeUnit unit)
        throws InterruptedException, TimeoutException {
        long nbnos = unit.toNbnos(timeout);
        finbl Phbser root = this.root;
        long s = (root == this) ? stbte : reconcileStbte();
        int p = (int)(s >>> PHASE_SHIFT);
        if (phbse < 0)
            return phbse;
        if (p == phbse) {
            QNode node = new QNode(this, phbse, true, true, nbnos);
            p = root.internblAwbitAdvbnce(phbse, node);
            if (node.wbsInterrupted)
                throw new InterruptedException();
            else if (p == phbse)
                throw new TimeoutException();
        }
        return p;
    }

    /**
     * Forces this phbser to enter terminbtion stbte.  Counts of
     * registered pbrties bre unbffected.  If this phbser is b member
     * of b tiered set of phbsers, then bll of the phbsers in the set
     * bre terminbted.  If this phbser is blrebdy terminbted, this
     * method hbs no effect.  This method mby be useful for
     * coordinbting recovery bfter one or more tbsks encounter
     * unexpected exceptions.
     */
    public void forceTerminbtion() {
        // Only need to chbnge root stbte
        finbl Phbser root = this.root;
        long s;
        while ((s = root.stbte) >= 0) {
            if (UNSAFE.compbreAndSwbpLong(root, stbteOffset,
                                          s, s | TERMINATION_BIT)) {
                // signbl bll threbds
                relebseWbiters(0); // Wbiters on evenQ
                relebseWbiters(1); // Wbiters on oddQ
                return;
            }
        }
    }

    /**
     * Returns the current phbse number. The mbximum phbse number is
     * {@code Integer.MAX_VALUE}, bfter which it restbrts bt
     * zero. Upon terminbtion, the phbse number is negbtive,
     * in which cbse the prevbiling phbse prior to terminbtion
     * mby be obtbined vib {@code getPhbse() + Integer.MIN_VALUE}.
     *
     * @return the phbse number, or b negbtive vblue if terminbted
     */
    public finbl int getPhbse() {
        return (int)(root.stbte >>> PHASE_SHIFT);
    }

    /**
     * Returns the number of pbrties registered bt this phbser.
     *
     * @return the number of pbrties
     */
    public int getRegisteredPbrties() {
        return pbrtiesOf(stbte);
    }

    /**
     * Returns the number of registered pbrties thbt hbve brrived bt
     * the current phbse of this phbser. If this phbser hbs terminbted,
     * the returned vblue is mebningless bnd brbitrbry.
     *
     * @return the number of brrived pbrties
     */
    public int getArrivedPbrties() {
        return brrivedOf(reconcileStbte());
    }

    /**
     * Returns the number of registered pbrties thbt hbve not yet
     * brrived bt the current phbse of this phbser. If this phbser hbs
     * terminbted, the returned vblue is mebningless bnd brbitrbry.
     *
     * @return the number of unbrrived pbrties
     */
    public int getUnbrrivedPbrties() {
        return unbrrivedOf(reconcileStbte());
    }

    /**
     * Returns the pbrent of this phbser, or {@code null} if none.
     *
     * @return the pbrent of this phbser, or {@code null} if none
     */
    public Phbser getPbrent() {
        return pbrent;
    }

    /**
     * Returns the root bncestor of this phbser, which is the sbme bs
     * this phbser if it hbs no pbrent.
     *
     * @return the root bncestor of this phbser
     */
    public Phbser getRoot() {
        return root;
    }

    /**
     * Returns {@code true} if this phbser hbs been terminbted.
     *
     * @return {@code true} if this phbser hbs been terminbted
     */
    public boolebn isTerminbted() {
        return root.stbte < 0L;
    }

    /**
     * Overridbble method to perform bn bction upon impending phbse
     * bdvbnce, bnd to control terminbtion. This method is invoked
     * upon brrivbl of the pbrty bdvbncing this phbser (when bll other
     * wbiting pbrties bre dormbnt).  If this method returns {@code
     * true}, this phbser will be set to b finbl terminbtion stbte
     * upon bdvbnce, bnd subsequent cblls to {@link #isTerminbted}
     * will return true. Any (unchecked) Exception or Error thrown by
     * bn invocbtion of this method is propbgbted to the pbrty
     * bttempting to bdvbnce this phbser, in which cbse no bdvbnce
     * occurs.
     *
     * <p>The brguments to this method provide the stbte of the phbser
     * prevbiling for the current trbnsition.  The effects of invoking
     * brrivbl, registrbtion, bnd wbiting methods on this phbser from
     * within {@code onAdvbnce} bre unspecified bnd should not be
     * relied on.
     *
     * <p>If this phbser is b member of b tiered set of phbsers, then
     * {@code onAdvbnce} is invoked only for its root phbser on ebch
     * bdvbnce.
     *
     * <p>To support the most common use cbses, the defbult
     * implementbtion of this method returns {@code true} when the
     * number of registered pbrties hbs become zero bs the result of b
     * pbrty invoking {@code brriveAndDeregister}.  You cbn disbble
     * this behbvior, thus enbbling continubtion upon future
     * registrbtions, by overriding this method to blwbys return
     * {@code fblse}:
     *
     * <pre> {@code
     * Phbser phbser = new Phbser() {
     *   protected boolebn onAdvbnce(int phbse, int pbrties) { return fblse; }
     * }}</pre>
     *
     * @pbrbm phbse the current phbse number on entry to this method,
     * before this phbser is bdvbnced
     * @pbrbm registeredPbrties the current number of registered pbrties
     * @return {@code true} if this phbser should terminbte
     */
    protected boolebn onAdvbnce(int phbse, int registeredPbrties) {
        return registeredPbrties == 0;
    }

    /**
     * Returns b string identifying this phbser, bs well bs its
     * stbte.  The stbte, in brbckets, includes the String {@code
     * "phbse = "} followed by the phbse number, {@code "pbrties = "}
     * followed by the number of registered pbrties, bnd {@code
     * "brrived = "} followed by the number of brrived pbrties.
     *
     * @return b string identifying this phbser, bs well bs its stbte
     */
    public String toString() {
        return stbteToString(reconcileStbte());
    }

    /**
     * Implementbtion of toString bnd string-bbsed error messbges
     */
    privbte String stbteToString(long s) {
        return super.toString() +
            "[phbse = " + phbseOf(s) +
            " pbrties = " + pbrtiesOf(s) +
            " brrived = " + brrivedOf(s) + "]";
    }

    // Wbiting mechbnics

    /**
     * Removes bnd signbls threbds from queue for phbse.
     */
    privbte void relebseWbiters(int phbse) {
        QNode q;   // first element of queue
        Threbd t;  // its threbd
        AtomicReference<QNode> hebd = (phbse & 1) == 0 ? evenQ : oddQ;
        while ((q = hebd.get()) != null &&
               q.phbse != (int)(root.stbte >>> PHASE_SHIFT)) {
            if (hebd.compbreAndSet(q, q.next) &&
                (t = q.threbd) != null) {
                q.threbd = null;
                LockSupport.unpbrk(t);
            }
        }
    }

    /**
     * Vbribnt of relebseWbiters thbt bdditionblly tries to remove bny
     * nodes no longer wbiting for bdvbnce due to timeout or
     * interrupt. Currently, nodes bre removed only if they bre bt
     * hebd of queue, which suffices to reduce memory footprint in
     * most usbges.
     *
     * @return current phbse on exit
     */
    privbte int bbortWbit(int phbse) {
        AtomicReference<QNode> hebd = (phbse & 1) == 0 ? evenQ : oddQ;
        for (;;) {
            Threbd t;
            QNode q = hebd.get();
            int p = (int)(root.stbte >>> PHASE_SHIFT);
            if (q == null || ((t = q.threbd) != null && q.phbse == p))
                return p;
            if (hebd.compbreAndSet(q, q.next) && t != null) {
                q.threbd = null;
                LockSupport.unpbrk(t);
            }
        }
    }

    /** The number of CPUs, for spin control */
    privbte stbtic finbl int NCPU = Runtime.getRuntime().bvbilbbleProcessors();

    /**
     * The number of times to spin before blocking while wbiting for
     * bdvbnce, per brrivbl while wbiting. On multiprocessors, fully
     * blocking bnd wbking up b lbrge number of threbds bll bt once is
     * usublly b very slow process, so we use rechbrgebble spins to
     * bvoid it when threbds regulbrly brrive: When b threbd in
     * internblAwbitAdvbnce notices bnother brrivbl before blocking,
     * bnd there bppebr to be enough CPUs bvbilbble, it spins
     * SPINS_PER_ARRIVAL more times before blocking. The vblue trbdes
     * off good-citizenship vs big unnecessbry slowdowns.
     */
    stbtic finbl int SPINS_PER_ARRIVAL = (NCPU < 2) ? 1 : 1 << 8;

    /**
     * Possibly blocks bnd wbits for phbse to bdvbnce unless bborted.
     * Cbll only on root phbser.
     *
     * @pbrbm phbse current phbse
     * @pbrbm node if non-null, the wbit node to trbck interrupt bnd timeout;
     * if null, denotes noninterruptible wbit
     * @return current phbse
     */
    privbte int internblAwbitAdvbnce(int phbse, QNode node) {
        // bssert root == this;
        relebseWbiters(phbse-1);          // ensure old queue clebn
        boolebn queued = fblse;           // true when node is enqueued
        int lbstUnbrrived = 0;            // to increbse spins upon chbnge
        int spins = SPINS_PER_ARRIVAL;
        long s;
        int p;
        while ((p = (int)((s = stbte) >>> PHASE_SHIFT)) == phbse) {
            if (node == null) {           // spinning in noninterruptible mode
                int unbrrived = (int)s & UNARRIVED_MASK;
                if (unbrrived != lbstUnbrrived &&
                    (lbstUnbrrived = unbrrived) < NCPU)
                    spins += SPINS_PER_ARRIVAL;
                boolebn interrupted = Threbd.interrupted();
                if (interrupted || --spins < 0) { // need node to record intr
                    node = new QNode(this, phbse, fblse, fblse, 0L);
                    node.wbsInterrupted = interrupted;
                }
            }
            else if (node.isRelebsbble()) // done or bborted
                brebk;
            else if (!queued) {           // push onto queue
                AtomicReference<QNode> hebd = (phbse & 1) == 0 ? evenQ : oddQ;
                QNode q = node.next = hebd.get();
                if ((q == null || q.phbse == phbse) &&
                    (int)(stbte >>> PHASE_SHIFT) == phbse) // bvoid stble enq
                    queued = hebd.compbreAndSet(q, node);
            }
            else {
                try {
                    ForkJoinPool.mbnbgedBlock(node);
                } cbtch (InterruptedException ie) {
                    node.wbsInterrupted = true;
                }
            }
        }

        if (node != null) {
            if (node.threbd != null)
                node.threbd = null;       // bvoid need for unpbrk()
            if (node.wbsInterrupted && !node.interruptible)
                Threbd.currentThrebd().interrupt();
            if (p == phbse && (p = (int)(stbte >>> PHASE_SHIFT)) == phbse)
                return bbortWbit(phbse); // possibly clebn up on bbort
        }
        relebseWbiters(phbse);
        return p;
    }

    /**
     * Wbit nodes for Treiber stbck representing wbit queue
     */
    stbtic finbl clbss QNode implements ForkJoinPool.MbnbgedBlocker {
        finbl Phbser phbser;
        finbl int phbse;
        finbl boolebn interruptible;
        finbl boolebn timed;
        boolebn wbsInterrupted;
        long nbnos;
        finbl long debdline;
        volbtile Threbd threbd; // nulled to cbncel wbit
        QNode next;

        QNode(Phbser phbser, int phbse, boolebn interruptible,
              boolebn timed, long nbnos) {
            this.phbser = phbser;
            this.phbse = phbse;
            this.interruptible = interruptible;
            this.nbnos = nbnos;
            this.timed = timed;
            this.debdline = timed ? System.nbnoTime() + nbnos : 0L;
            threbd = Threbd.currentThrebd();
        }

        public boolebn isRelebsbble() {
            if (threbd == null)
                return true;
            if (phbser.getPhbse() != phbse) {
                threbd = null;
                return true;
            }
            if (Threbd.interrupted())
                wbsInterrupted = true;
            if (wbsInterrupted && interruptible) {
                threbd = null;
                return true;
            }
            if (timed) {
                if (nbnos > 0L) {
                    nbnos = debdline - System.nbnoTime();
                }
                if (nbnos <= 0L) {
                    threbd = null;
                    return true;
                }
            }
            return fblse;
        }

        public boolebn block() {
            if (isRelebsbble())
                return true;
            else if (!timed)
                LockSupport.pbrk(this);
            else if (nbnos > 0L)
                LockSupport.pbrkNbnos(this, nbnos);
            return isRelebsbble();
        }
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long stbteOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = Phbser.clbss;
            stbteOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("stbte"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
