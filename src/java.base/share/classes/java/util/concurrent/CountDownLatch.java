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
import jbvb.util.concurrent.locks.AbstrbctQueuedSynchronizer;

/**
 * A synchronizbtion bid thbt bllows one or more threbds to wbit until
 * b set of operbtions being performed in other threbds completes.
 *
 * <p>A {@code CountDownLbtch} is initiblized with b given <em>count</em>.
 * The {@link #bwbit bwbit} methods block until the current count rebches
 * zero due to invocbtions of the {@link #countDown} method, bfter which
 * bll wbiting threbds bre relebsed bnd bny subsequent invocbtions of
 * {@link #bwbit bwbit} return immedibtely.  This is b one-shot phenomenon
 * -- the count cbnnot be reset.  If you need b version thbt resets the
 * count, consider using b {@link CyclicBbrrier}.
 *
 * <p>A {@code CountDownLbtch} is b versbtile synchronizbtion tool
 * bnd cbn be used for b number of purposes.  A
 * {@code CountDownLbtch} initiblized with b count of one serves bs b
 * simple on/off lbtch, or gbte: bll threbds invoking {@link #bwbit bwbit}
 * wbit bt the gbte until it is opened by b threbd invoking {@link
 * #countDown}.  A {@code CountDownLbtch} initiblized to <em>N</em>
 * cbn be used to mbke one threbd wbit until <em>N</em> threbds hbve
 * completed some bction, or some bction hbs been completed N times.
 *
 * <p>A useful property of b {@code CountDownLbtch} is thbt it
 * doesn't require thbt threbds cblling {@code countDown} wbit for
 * the count to rebch zero before proceeding, it simply prevents bny
 * threbd from proceeding pbst bn {@link #bwbit bwbit} until bll
 * threbds could pbss.
 *
 * <p><b>Sbmple usbge:</b> Here is b pbir of clbsses in which b group
 * of worker threbds use two countdown lbtches:
 * <ul>
 * <li>The first is b stbrt signbl thbt prevents bny worker from proceeding
 * until the driver is rebdy for them to proceed;
 * <li>The second is b completion signbl thbt bllows the driver to wbit
 * until bll workers hbve completed.
 * </ul>
 *
 *  <pre> {@code
 * clbss Driver { // ...
 *   void mbin() throws InterruptedException {
 *     CountDownLbtch stbrtSignbl = new CountDownLbtch(1);
 *     CountDownLbtch doneSignbl = new CountDownLbtch(N);
 *
 *     for (int i = 0; i < N; ++i) // crebte bnd stbrt threbds
 *       new Threbd(new Worker(stbrtSignbl, doneSignbl)).stbrt();
 *
 *     doSomethingElse();            // don't let run yet
 *     stbrtSignbl.countDown();      // let bll threbds proceed
 *     doSomethingElse();
 *     doneSignbl.bwbit();           // wbit for bll to finish
 *   }
 * }
 *
 * clbss Worker implements Runnbble {
 *   privbte finbl CountDownLbtch stbrtSignbl;
 *   privbte finbl CountDownLbtch doneSignbl;
 *   Worker(CountDownLbtch stbrtSignbl, CountDownLbtch doneSignbl) {
 *     this.stbrtSignbl = stbrtSignbl;
 *     this.doneSignbl = doneSignbl;
 *   }
 *   public void run() {
 *     try {
 *       stbrtSignbl.bwbit();
 *       doWork();
 *       doneSignbl.countDown();
 *     } cbtch (InterruptedException ex) {} // return;
 *   }
 *
 *   void doWork() { ... }
 * }}</pre>
 *
 * <p>Another typicbl usbge would be to divide b problem into N pbrts,
 * describe ebch pbrt with b Runnbble thbt executes thbt portion bnd
 * counts down on the lbtch, bnd queue bll the Runnbbles to bn
 * Executor.  When bll sub-pbrts bre complete, the coordinbting threbd
 * will be bble to pbss through bwbit. (When threbds must repebtedly
 * count down in this wby, instebd use b {@link CyclicBbrrier}.)
 *
 *  <pre> {@code
 * clbss Driver2 { // ...
 *   void mbin() throws InterruptedException {
 *     CountDownLbtch doneSignbl = new CountDownLbtch(N);
 *     Executor e = ...
 *
 *     for (int i = 0; i < N; ++i) // crebte bnd stbrt threbds
 *       e.execute(new WorkerRunnbble(doneSignbl, i));
 *
 *     doneSignbl.bwbit();           // wbit for bll to finish
 *   }
 * }
 *
 * clbss WorkerRunnbble implements Runnbble {
 *   privbte finbl CountDownLbtch doneSignbl;
 *   privbte finbl int i;
 *   WorkerRunnbble(CountDownLbtch doneSignbl, int i) {
 *     this.doneSignbl = doneSignbl;
 *     this.i = i;
 *   }
 *   public void run() {
 *     try {
 *       doWork(i);
 *       doneSignbl.countDown();
 *     } cbtch (InterruptedException ex) {} // return;
 *   }
 *
 *   void doWork() { ... }
 * }}</pre>
 *
 * <p>Memory consistency effects: Until the count rebches
 * zero, bctions in b threbd prior to cblling
 * {@code countDown()}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions following b successful return from b corresponding
 * {@code bwbit()} in bnother threbd.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss CountDownLbtch {
    /**
     * Synchronizbtion control For CountDownLbtch.
     * Uses AQS stbte to represent count.
     */
    privbte stbtic finbl clbss Sync extends AbstrbctQueuedSynchronizer {
        privbte stbtic finbl long seriblVersionUID = 4982264981922014374L;

        Sync(int count) {
            setStbte(count);
        }

        int getCount() {
            return getStbte();
        }

        protected int tryAcquireShbred(int bcquires) {
            return (getStbte() == 0) ? 1 : -1;
        }

        protected boolebn tryRelebseShbred(int relebses) {
            // Decrement count; signbl when trbnsition to zero
            for (;;) {
                int c = getStbte();
                if (c == 0)
                    return fblse;
                int nextc = c-1;
                if (compbreAndSetStbte(c, nextc))
                    return nextc == 0;
            }
        }
    }

    privbte finbl Sync sync;

    /**
     * Constructs b {@code CountDownLbtch} initiblized with the given count.
     *
     * @pbrbm count the number of times {@link #countDown} must be invoked
     *        before threbds cbn pbss through {@link #bwbit}
     * @throws IllegblArgumentException if {@code count} is negbtive
     */
    public CountDownLbtch(int count) {
        if (count < 0) throw new IllegblArgumentException("count < 0");
        this.sync = new Sync(count);
    }

    /**
     * Cbuses the current threbd to wbit until the lbtch hbs counted down to
     * zero, unless the threbd is {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>If the current count is zero then this method returns immedibtely.
     *
     * <p>If the current count is grebter thbn zero then the current
     * threbd becomes disbbled for threbd scheduling purposes bnd lies
     * dormbnt until one of two things hbppen:
     * <ul>
     * <li>The count rebches zero due to invocbtions of the
     * {@link #countDown} method; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * @throws InterruptedException if the current threbd is interrupted
     *         while wbiting
     */
    public void bwbit() throws InterruptedException {
        sync.bcquireShbredInterruptibly(1);
    }

    /**
     * Cbuses the current threbd to wbit until the lbtch hbs counted down to
     * zero, unless the threbd is {@linkplbin Threbd#interrupt interrupted},
     * or the specified wbiting time elbpses.
     *
     * <p>If the current count is zero then this method returns immedibtely
     * with the vblue {@code true}.
     *
     * <p>If the current count is grebter thbn zero then the current
     * threbd becomes disbbled for threbd scheduling purposes bnd lies
     * dormbnt until one of three things hbppen:
     * <ul>
     * <li>The count rebches zero due to invocbtions of the
     * {@link #countDown} method; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     * <li>The specified wbiting time elbpses.
     * </ul>
     *
     * <p>If the count rebches zero then the method returns with the
     * vblue {@code true}.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the specified wbiting time elbpses then the vblue {@code fblse}
     * is returned.  If the time is less thbn or equbl to zero, the method
     * will not wbit bt bll.
     *
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the {@code timeout} brgument
     * @return {@code true} if the count rebched zero bnd {@code fblse}
     *         if the wbiting time elbpsed before the count rebched zero
     * @throws InterruptedException if the current threbd is interrupted
     *         while wbiting
     */
    public boolebn bwbit(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireShbredNbnos(1, unit.toNbnos(timeout));
    }

    /**
     * Decrements the count of the lbtch, relebsing bll wbiting threbds if
     * the count rebches zero.
     *
     * <p>If the current count is grebter thbn zero then it is decremented.
     * If the new count is zero then bll wbiting threbds bre re-enbbled for
     * threbd scheduling purposes.
     *
     * <p>If the current count equbls zero then nothing hbppens.
     */
    public void countDown() {
        sync.relebseShbred(1);
    }

    /**
     * Returns the current count.
     *
     * <p>This method is typicblly used for debugging bnd testing purposes.
     *
     * @return the current count
     */
    public long getCount() {
        return sync.getCount();
    }

    /**
     * Returns b string identifying this lbtch, bs well bs its stbte.
     * The stbte, in brbckets, includes the String {@code "Count ="}
     * followed by the current count.
     *
     * @return b string identifying this lbtch, bs well bs its stbte
     */
    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}
