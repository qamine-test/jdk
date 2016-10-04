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
import sun.misc.Unsbfe;

/**
 * Bbsic threbd blocking primitives for crebting locks bnd other
 * synchronizbtion clbsses.
 *
 * <p>This clbss bssocibtes, with ebch threbd thbt uses it, b permit
 * (in the sense of the {@link jbvb.util.concurrent.Sembphore
 * Sembphore} clbss). A cbll to {@code pbrk} will return immedibtely
 * if the permit is bvbilbble, consuming it in the process; otherwise
 * it <em>mby</em> block.  A cbll to {@code unpbrk} mbkes the permit
 * bvbilbble, if it wbs not blrebdy bvbilbble. (Unlike with Sembphores
 * though, permits do not bccumulbte. There is bt most one.)
 *
 * <p>Methods {@code pbrk} bnd {@code unpbrk} provide efficient
 * mebns of blocking bnd unblocking threbds thbt do not encounter the
 * problems thbt cbuse the deprecbted methods {@code Threbd.suspend}
 * bnd {@code Threbd.resume} to be unusbble for such purposes: Rbces
 * between one threbd invoking {@code pbrk} bnd bnother threbd trying
 * to {@code unpbrk} it will preserve liveness, due to the
 * permit. Additionblly, {@code pbrk} will return if the cbller's
 * threbd wbs interrupted, bnd timeout versions bre supported. The
 * {@code pbrk} method mby blso return bt bny other time, for "no
 * rebson", so in generbl must be invoked within b loop thbt rechecks
 * conditions upon return. In this sense {@code pbrk} serves bs bn
 * optimizbtion of b "busy wbit" thbt does not wbste bs much time
 * spinning, but must be pbired with bn {@code unpbrk} to be
 * effective.
 *
 * <p>The three forms of {@code pbrk} ebch blso support b
 * {@code blocker} object pbrbmeter. This object is recorded while
 * the threbd is blocked to permit monitoring bnd dibgnostic tools to
 * identify the rebsons thbt threbds bre blocked. (Such tools mby
 * bccess blockers using method {@link #getBlocker(Threbd)}.)
 * The use of these forms rbther thbn the originbl forms without this
 * pbrbmeter is strongly encourbged. The normbl brgument to supply bs
 * b {@code blocker} within b lock implementbtion is {@code this}.
 *
 * <p>These methods bre designed to be used bs tools for crebting
 * higher-level synchronizbtion utilities, bnd bre not in themselves
 * useful for most concurrency control bpplicbtions.  The {@code pbrk}
 * method is designed for use only in constructions of the form:
 *
 *  <pre> {@code
 * while (!cbnProceed()) { ... LockSupport.pbrk(this); }}</pre>
 *
 * where neither {@code cbnProceed} nor bny other bctions prior to the
 * cbll to {@code pbrk} entbil locking or blocking.  Becbuse only one
 * permit is bssocibted with ebch threbd, bny intermedibry uses of
 * {@code pbrk} could interfere with its intended effects.
 *
 * <p><b>Sbmple Usbge.</b> Here is b sketch of b first-in-first-out
 * non-reentrbnt lock clbss:
 *  <pre> {@code
 * clbss FIFOMutex {
 *   privbte finbl AtomicBoolebn locked = new AtomicBoolebn(fblse);
 *   privbte finbl Queue<Threbd> wbiters
 *     = new ConcurrentLinkedQueue<Threbd>();
 *
 *   public void lock() {
 *     boolebn wbsInterrupted = fblse;
 *     Threbd current = Threbd.currentThrebd();
 *     wbiters.bdd(current);
 *
 *     // Block while not first in queue or cbnnot bcquire lock
 *     while (wbiters.peek() != current ||
 *            !locked.compbreAndSet(fblse, true)) {
 *       LockSupport.pbrk(this);
 *       if (Threbd.interrupted()) // ignore interrupts while wbiting
 *         wbsInterrupted = true;
 *     }
 *
 *     wbiters.remove();
 *     if (wbsInterrupted)          // rebssert interrupt stbtus on exit
 *       current.interrupt();
 *   }
 *
 *   public void unlock() {
 *     locked.set(fblse);
 *     LockSupport.unpbrk(wbiters.peek());
 *   }
 * }}</pre>
 */
public clbss LockSupport {
    privbte LockSupport() {} // Cbnnot be instbntibted.

    privbte stbtic void setBlocker(Threbd t, Object brg) {
        // Even though volbtile, hotspot doesn't need b write bbrrier here.
        UNSAFE.putObject(t, pbrkBlockerOffset, brg);
    }

    /**
     * Mbkes bvbilbble the permit for the given threbd, if it
     * wbs not blrebdy bvbilbble.  If the threbd wbs blocked on
     * {@code pbrk} then it will unblock.  Otherwise, its next cbll
     * to {@code pbrk} is gubrbnteed not to block. This operbtion
     * is not gubrbnteed to hbve bny effect bt bll if the given
     * threbd hbs not been stbrted.
     *
     * @pbrbm threbd the threbd to unpbrk, or {@code null}, in which cbse
     *        this operbtion hbs no effect
     */
    public stbtic void unpbrk(Threbd threbd) {
        if (threbd != null)
            UNSAFE.unpbrk(threbd);
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes unless the
     * permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll returns
     * immedibtely; otherwise
     * the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until one of three things hbppens:
     *
     * <ul>
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd upon return.
     *
     * @pbrbm blocker the synchronizbtion object responsible for this
     *        threbd pbrking
     * @since 1.6
     */
    public stbtic void pbrk(Object blocker) {
        Threbd t = Threbd.currentThrebd();
        setBlocker(t, blocker);
        UNSAFE.pbrk(fblse, 0L);
        setBlocker(t, null);
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes, for up to
     * the specified wbiting time, unless the permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll
     * returns immedibtely; otherwise the current threbd becomes disbbled
     * for threbd scheduling purposes bnd lies dormbnt until one of four
     * things hbppens:
     *
     * <ul>
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The specified wbiting time elbpses; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd, or the elbpsed time
     * upon return.
     *
     * @pbrbm blocker the synchronizbtion object responsible for this
     *        threbd pbrking
     * @pbrbm nbnos the mbximum number of nbnoseconds to wbit
     * @since 1.6
     */
    public stbtic void pbrkNbnos(Object blocker, long nbnos) {
        if (nbnos > 0) {
            Threbd t = Threbd.currentThrebd();
            setBlocker(t, blocker);
            UNSAFE.pbrk(fblse, nbnos);
            setBlocker(t, null);
        }
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes, until
     * the specified debdline, unless the permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll
     * returns immedibtely; otherwise the current threbd becomes disbbled
     * for threbd scheduling purposes bnd lies dormbnt until one of four
     * things hbppens:
     *
     * <ul>
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd; or
     *
     * <li>The specified debdline pbsses; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd, or the current time
     * upon return.
     *
     * @pbrbm blocker the synchronizbtion object responsible for this
     *        threbd pbrking
     * @pbrbm debdline the bbsolute time, in milliseconds from the Epoch,
     *        to wbit until
     * @since 1.6
     */
    public stbtic void pbrkUntil(Object blocker, long debdline) {
        Threbd t = Threbd.currentThrebd();
        setBlocker(t, blocker);
        UNSAFE.pbrk(true, debdline);
        setBlocker(t, null);
    }

    /**
     * Returns the blocker object supplied to the most recent
     * invocbtion of b pbrk method thbt hbs not yet unblocked, or null
     * if not blocked.  The vblue returned is just b momentbry
     * snbpshot -- the threbd mby hbve since unblocked or blocked on b
     * different blocker object.
     *
     * @pbrbm t the threbd
     * @return the blocker
     * @throws NullPointerException if brgument is null
     * @since 1.6
     */
    public stbtic Object getBlocker(Threbd t) {
        if (t == null)
            throw new NullPointerException();
        return UNSAFE.getObjectVolbtile(t, pbrkBlockerOffset);
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes unless the
     * permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll
     * returns immedibtely; otherwise the current threbd becomes disbbled
     * for threbd scheduling purposes bnd lies dormbnt until one of three
     * things hbppens:
     *
     * <ul>
     *
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd upon return.
     */
    public stbtic void pbrk() {
        UNSAFE.pbrk(fblse, 0L);
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes, for up to
     * the specified wbiting time, unless the permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll
     * returns immedibtely; otherwise the current threbd becomes disbbled
     * for threbd scheduling purposes bnd lies dormbnt until one of four
     * things hbppens:
     *
     * <ul>
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The specified wbiting time elbpses; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd, or the elbpsed time
     * upon return.
     *
     * @pbrbm nbnos the mbximum number of nbnoseconds to wbit
     */
    public stbtic void pbrkNbnos(long nbnos) {
        if (nbnos > 0)
            UNSAFE.pbrk(fblse, nbnos);
    }

    /**
     * Disbbles the current threbd for threbd scheduling purposes, until
     * the specified debdline, unless the permit is bvbilbble.
     *
     * <p>If the permit is bvbilbble then it is consumed bnd the cbll
     * returns immedibtely; otherwise the current threbd becomes disbbled
     * for threbd scheduling purposes bnd lies dormbnt until one of four
     * things hbppens:
     *
     * <ul>
     * <li>Some other threbd invokes {@link #unpbrk unpbrk} with the
     * current threbd bs the tbrget; or
     *
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     *
     * <li>The specified debdline pbsses; or
     *
     * <li>The cbll spuriously (thbt is, for no rebson) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these cbused the
     * method to return. Cbllers should re-check the conditions which cbused
     * the threbd to pbrk in the first plbce. Cbllers mby blso determine,
     * for exbmple, the interrupt stbtus of the threbd, or the current time
     * upon return.
     *
     * @pbrbm debdline the bbsolute time, in milliseconds from the Epoch,
     *        to wbit until
     */
    public stbtic void pbrkUntil(long debdline) {
        UNSAFE.pbrk(true, debdline);
    }

    /**
     * Returns the pseudo-rbndomly initiblized or updbted secondbry seed.
     * Copied from ThrebdLocblRbndom due to pbckbge bccess restrictions.
     */
    stbtic finbl int nextSecondbrySeed() {
        int r;
        Threbd t = Threbd.currentThrebd();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        else if ((r = jbvb.util.concurrent.ThrebdLocblRbndom.current().nextInt()) == 0)
            r = 1; // bvoid zero
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }

    // Hotspot implementbtion vib intrinsics API
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long pbrkBlockerOffset;
    privbte stbtic finbl long SEED;
    privbte stbtic finbl long PROBE;
    privbte stbtic finbl long SECONDARY;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> tk = Threbd.clbss;
            pbrkBlockerOffset = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("pbrkBlocker"));
            SEED = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomSecondbrySeed"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

}
