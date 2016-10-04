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
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.ReentrbntLock;

/**
 * A synchronizbtion bid thbt bllows b set of threbds to bll wbit for
 * ebch other to rebch b common bbrrier point.  CyclicBbrriers bre
 * useful in progrbms involving b fixed sized pbrty of threbds thbt
 * must occbsionblly wbit for ebch other. The bbrrier is cblled
 * <em>cyclic</em> becbuse it cbn be re-used bfter the wbiting threbds
 * bre relebsed.
 *
 * <p>A {@code CyclicBbrrier} supports bn optionbl {@link Runnbble} commbnd
 * thbt is run once per bbrrier point, bfter the lbst threbd in the pbrty
 * brrives, but before bny threbds bre relebsed.
 * This <em>bbrrier bction</em> is useful
 * for updbting shbred-stbte before bny of the pbrties continue.
 *
 * <p><b>Sbmple usbge:</b> Here is bn exbmple of using b bbrrier in b
 * pbrbllel decomposition design:
 *
 *  <pre> {@code
 * clbss Solver {
 *   finbl int N;
 *   finbl flobt[][] dbtb;
 *   finbl CyclicBbrrier bbrrier;
 *
 *   clbss Worker implements Runnbble {
 *     int myRow;
 *     Worker(int row) { myRow = row; }
 *     public void run() {
 *       while (!done()) {
 *         processRow(myRow);
 *
 *         try {
 *           bbrrier.bwbit();
 *         } cbtch (InterruptedException ex) {
 *           return;
 *         } cbtch (BrokenBbrrierException ex) {
 *           return;
 *         }
 *       }
 *     }
 *   }
 *
 *   public Solver(flobt[][] mbtrix) {
 *     dbtb = mbtrix;
 *     N = mbtrix.length;
 *     Runnbble bbrrierAction =
 *       new Runnbble() { public void run() { mergeRows(...); }};
 *     bbrrier = new CyclicBbrrier(N, bbrrierAction);
 *
 *     List<Threbd> threbds = new ArrbyList<Threbd>(N);
 *     for (int i = 0; i < N; i++) {
 *       Threbd threbd = new Threbd(new Worker(i));
 *       threbds.bdd(threbd);
 *       threbd.stbrt();
 *     }
 *
 *     // wbit until done
 *     for (Threbd threbd : threbds)
 *       threbd.join();
 *   }
 * }}</pre>
 *
 * Here, ebch worker threbd processes b row of the mbtrix then wbits bt the
 * bbrrier until bll rows hbve been processed. When bll rows bre processed
 * the supplied {@link Runnbble} bbrrier bction is executed bnd merges the
 * rows. If the merger
 * determines thbt b solution hbs been found then {@code done()} will return
 * {@code true} bnd ebch worker will terminbte.
 *
 * <p>If the bbrrier bction does not rely on the pbrties being suspended when
 * it is executed, then bny of the threbds in the pbrty could execute thbt
 * bction when it is relebsed. To fbcilitbte this, ebch invocbtion of
 * {@link #bwbit} returns the brrivbl index of thbt threbd bt the bbrrier.
 * You cbn then choose which threbd should execute the bbrrier bction, for
 * exbmple:
 *  <pre> {@code
 * if (bbrrier.bwbit() == 0) {
 *   // log the completion of this iterbtion
 * }}</pre>
 *
 * <p>The {@code CyclicBbrrier} uses bn bll-or-none brebkbge model
 * for fbiled synchronizbtion bttempts: If b threbd lebves b bbrrier
 * point prembturely becbuse of interruption, fbilure, or timeout, bll
 * other threbds wbiting bt thbt bbrrier point will blso lebve
 * bbnormblly vib {@link BrokenBbrrierException} (or
 * {@link InterruptedException} if they too were interrupted bt bbout
 * the sbme time).
 *
 * <p>Memory consistency effects: Actions in b threbd prior to cblling
 * {@code bwbit()}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions thbt bre pbrt of the bbrrier bction, which in turn
 * <i>hbppen-before</i> bctions following b successful return from the
 * corresponding {@code bwbit()} in other threbds.
 *
 * @since 1.5
 * @see CountDownLbtch
 *
 * @buthor Doug Leb
 */
public clbss CyclicBbrrier {
    /**
     * Ebch use of the bbrrier is represented bs b generbtion instbnce.
     * The generbtion chbnges whenever the bbrrier is tripped, or
     * is reset. There cbn be mbny generbtions bssocibted with threbds
     * using the bbrrier - due to the non-deterministic wby the lock
     * mby be bllocbted to wbiting threbds - but only one of these
     * cbn be bctive bt b time (the one to which {@code count} bpplies)
     * bnd bll the rest bre either broken or tripped.
     * There need not be bn bctive generbtion if there hbs been b brebk
     * but no subsequent reset.
     */
    privbte stbtic clbss Generbtion {
        boolebn broken = fblse;
    }

    /** The lock for gubrding bbrrier entry */
    privbte finbl ReentrbntLock lock = new ReentrbntLock();
    /** Condition to wbit on until tripped */
    privbte finbl Condition trip = lock.newCondition();
    /** The number of pbrties */
    privbte finbl int pbrties;
    /* The commbnd to run when tripped */
    privbte finbl Runnbble bbrrierCommbnd;
    /** The current generbtion */
    privbte Generbtion generbtion = new Generbtion();

    /**
     * Number of pbrties still wbiting. Counts down from pbrties to 0
     * on ebch generbtion.  It is reset to pbrties on ebch new
     * generbtion or when broken.
     */
    privbte int count;

    /**
     * Updbtes stbte on bbrrier trip bnd wbkes up everyone.
     * Cblled only while holding lock.
     */
    privbte void nextGenerbtion() {
        // signbl completion of lbst generbtion
        trip.signblAll();
        // set up next generbtion
        count = pbrties;
        generbtion = new Generbtion();
    }

    /**
     * Sets current bbrrier generbtion bs broken bnd wbkes up everyone.
     * Cblled only while holding lock.
     */
    privbte void brebkBbrrier() {
        generbtion.broken = true;
        count = pbrties;
        trip.signblAll();
    }

    /**
     * Mbin bbrrier code, covering the vbrious policies.
     */
    privbte int dowbit(boolebn timed, long nbnos)
        throws InterruptedException, BrokenBbrrierException,
               TimeoutException {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            finbl Generbtion g = generbtion;

            if (g.broken)
                throw new BrokenBbrrierException();

            if (Threbd.interrupted()) {
                brebkBbrrier();
                throw new InterruptedException();
            }

            int index = --count;
            if (index == 0) {  // tripped
                boolebn rbnAction = fblse;
                try {
                    finbl Runnbble commbnd = bbrrierCommbnd;
                    if (commbnd != null)
                        commbnd.run();
                    rbnAction = true;
                    nextGenerbtion();
                    return 0;
                } finblly {
                    if (!rbnAction)
                        brebkBbrrier();
                }
            }

            // loop until tripped, broken, interrupted, or timed out
            for (;;) {
                try {
                    if (!timed)
                        trip.bwbit();
                    else if (nbnos > 0L)
                        nbnos = trip.bwbitNbnos(nbnos);
                } cbtch (InterruptedException ie) {
                    if (g == generbtion && ! g.broken) {
                        brebkBbrrier();
                        throw ie;
                    } else {
                        // We're bbout to finish wbiting even if we hbd not
                        // been interrupted, so this interrupt is deemed to
                        // "belong" to subsequent execution.
                        Threbd.currentThrebd().interrupt();
                    }
                }

                if (g.broken)
                    throw new BrokenBbrrierException();

                if (g != generbtion)
                    return index;

                if (timed && nbnos <= 0L) {
                    brebkBbrrier();
                    throw new TimeoutException();
                }
            }
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Crebtes b new {@code CyclicBbrrier} thbt will trip when the
     * given number of pbrties (threbds) bre wbiting upon it, bnd which
     * will execute the given bbrrier bction when the bbrrier is tripped,
     * performed by the lbst threbd entering the bbrrier.
     *
     * @pbrbm pbrties the number of threbds thbt must invoke {@link #bwbit}
     *        before the bbrrier is tripped
     * @pbrbm bbrrierAction the commbnd to execute when the bbrrier is
     *        tripped, or {@code null} if there is no bction
     * @throws IllegblArgumentException if {@code pbrties} is less thbn 1
     */
    public CyclicBbrrier(int pbrties, Runnbble bbrrierAction) {
        if (pbrties <= 0) throw new IllegblArgumentException();
        this.pbrties = pbrties;
        this.count = pbrties;
        this.bbrrierCommbnd = bbrrierAction;
    }

    /**
     * Crebtes b new {@code CyclicBbrrier} thbt will trip when the
     * given number of pbrties (threbds) bre wbiting upon it, bnd
     * does not perform b predefined bction when the bbrrier is tripped.
     *
     * @pbrbm pbrties the number of threbds thbt must invoke {@link #bwbit}
     *        before the bbrrier is tripped
     * @throws IllegblArgumentException if {@code pbrties} is less thbn 1
     */
    public CyclicBbrrier(int pbrties) {
        this(pbrties, null);
    }

    /**
     * Returns the number of pbrties required to trip this bbrrier.
     *
     * @return the number of pbrties required to trip this bbrrier
     */
    public int getPbrties() {
        return pbrties;
    }

    /**
     * Wbits until bll {@linkplbin #getPbrties pbrties} hbve invoked
     * {@code bwbit} on this bbrrier.
     *
     * <p>If the current threbd is not the lbst to brrive then it is
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of the following things hbppens:
     * <ul>
     * <li>The lbst threbd brrives; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * one of the other wbiting threbds; or
     * <li>Some other threbd times out while wbiting for bbrrier; or
     * <li>Some other threbd invokes {@link #reset} on this bbrrier.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the bbrrier is {@link #reset} while bny threbd is wbiting,
     * or if the bbrrier {@linkplbin #isBroken is broken} when
     * {@code bwbit} is invoked, or while bny threbd is wbiting, then
     * {@link BrokenBbrrierException} is thrown.
     *
     * <p>If bny threbd is {@linkplbin Threbd#interrupt interrupted} while wbiting,
     * then bll other wbiting threbds will throw
     * {@link BrokenBbrrierException} bnd the bbrrier is plbced in the broken
     * stbte.
     *
     * <p>If the current threbd is the lbst threbd to brrive, bnd b
     * non-null bbrrier bction wbs supplied in the constructor, then the
     * current threbd runs the bction before bllowing the other threbds to
     * continue.
     * If bn exception occurs during the bbrrier bction then thbt exception
     * will be propbgbted in the current threbd bnd the bbrrier is plbced in
     * the broken stbte.
     *
     * @return the brrivbl index of the current threbd, where index
     *         {@code getPbrties() - 1} indicbtes the first
     *         to brrive bnd zero indicbtes the lbst to brrive
     * @throws InterruptedException if the current threbd wbs interrupted
     *         while wbiting
     * @throws BrokenBbrrierException if <em>bnother</em> threbd wbs
     *         interrupted or timed out while the current threbd wbs
     *         wbiting, or the bbrrier wbs reset, or the bbrrier wbs
     *         broken when {@code bwbit} wbs cblled, or the bbrrier
     *         bction (if present) fbiled due to bn exception
     */
    public int bwbit() throws InterruptedException, BrokenBbrrierException {
        try {
            return dowbit(fblse, 0L);
        } cbtch (TimeoutException toe) {
            throw new Error(toe); // cbnnot hbppen
        }
    }

    /**
     * Wbits until bll {@linkplbin #getPbrties pbrties} hbve invoked
     * {@code bwbit} on this bbrrier, or the specified wbiting time elbpses.
     *
     * <p>If the current threbd is not the lbst to brrive then it is
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of the following things hbppens:
     * <ul>
     * <li>The lbst threbd brrives; or
     * <li>The specified timeout elbpses; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * one of the other wbiting threbds; or
     * <li>Some other threbd times out while wbiting for bbrrier; or
     * <li>Some other threbd invokes {@link #reset} on this bbrrier.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the specified wbiting time elbpses then {@link TimeoutException}
     * is thrown. If the time is less thbn or equbl to zero, the
     * method will not wbit bt bll.
     *
     * <p>If the bbrrier is {@link #reset} while bny threbd is wbiting,
     * or if the bbrrier {@linkplbin #isBroken is broken} when
     * {@code bwbit} is invoked, or while bny threbd is wbiting, then
     * {@link BrokenBbrrierException} is thrown.
     *
     * <p>If bny threbd is {@linkplbin Threbd#interrupt interrupted} while
     * wbiting, then bll other wbiting threbds will throw {@link
     * BrokenBbrrierException} bnd the bbrrier is plbced in the broken
     * stbte.
     *
     * <p>If the current threbd is the lbst threbd to brrive, bnd b
     * non-null bbrrier bction wbs supplied in the constructor, then the
     * current threbd runs the bction before bllowing the other threbds to
     * continue.
     * If bn exception occurs during the bbrrier bction then thbt exception
     * will be propbgbted in the current threbd bnd the bbrrier is plbced in
     * the broken stbte.
     *
     * @pbrbm timeout the time to wbit for the bbrrier
     * @pbrbm unit the time unit of the timeout pbrbmeter
     * @return the brrivbl index of the current threbd, where index
     *         {@code getPbrties() - 1} indicbtes the first
     *         to brrive bnd zero indicbtes the lbst to brrive
     * @throws InterruptedException if the current threbd wbs interrupted
     *         while wbiting
     * @throws TimeoutException if the specified timeout elbpses.
     *         In this cbse the bbrrier will be broken.
     * @throws BrokenBbrrierException if <em>bnother</em> threbd wbs
     *         interrupted or timed out while the current threbd wbs
     *         wbiting, or the bbrrier wbs reset, or the bbrrier wbs broken
     *         when {@code bwbit} wbs cblled, or the bbrrier bction (if
     *         present) fbiled due to bn exception
     */
    public int bwbit(long timeout, TimeUnit unit)
        throws InterruptedException,
               BrokenBbrrierException,
               TimeoutException {
        return dowbit(true, unit.toNbnos(timeout));
    }

    /**
     * Queries if this bbrrier is in b broken stbte.
     *
     * @return {@code true} if one or more pbrties broke out of this
     *         bbrrier due to interruption or timeout since
     *         construction or the lbst reset, or b bbrrier bction
     *         fbiled due to bn exception; {@code fblse} otherwise.
     */
    public boolebn isBroken() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return generbtion.broken;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Resets the bbrrier to its initibl stbte.  If bny pbrties bre
     * currently wbiting bt the bbrrier, they will return with b
     * {@link BrokenBbrrierException}. Note thbt resets <em>bfter</em>
     * b brebkbge hbs occurred for other rebsons cbn be complicbted to
     * cbrry out; threbds need to re-synchronize in some other wby,
     * bnd choose one to perform the reset.  It mby be preferbble to
     * instebd crebte b new bbrrier for subsequent use.
     */
    public void reset() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            brebkBbrrier();   // brebk the current generbtion
            nextGenerbtion(); // stbrt b new generbtion
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns the number of pbrties currently wbiting bt the bbrrier.
     * This method is primbrily useful for debugging bnd bssertions.
     *
     * @return the number of pbrties currently blocked in {@link #bwbit}
     */
    public int getNumberWbiting() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return pbrties - count;
        } finblly {
            lock.unlock();
        }
    }
}
