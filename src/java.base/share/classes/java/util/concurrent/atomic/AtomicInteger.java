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

pbckbge jbvb.util.concurrent.btomic;
import jbvb.util.function.IntUnbryOperbtor;
import jbvb.util.function.IntBinbryOperbtor;
import sun.misc.Unsbfe;

/**
 * An {@code int} vblue thbt mby be updbted btomicblly.  See the
 * {@link jbvb.util.concurrent.btomic} pbckbge specificbtion for
 * description of the properties of btomic vbribbles. An
 * {@code AtomicInteger} is used in bpplicbtions such bs btomicblly
 * incremented counters, bnd cbnnot be used bs b replbcement for bn
 * {@link jbvb.lbng.Integer}. However, this clbss does extend
 * {@code Number} to bllow uniform bccess by tools bnd utilities thbt
 * debl with numericblly-bbsed clbsses.
 *
 * @since 1.5
 * @buthor Doug Leb
*/
public clbss AtomicInteger extends Number implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 6214790243416807050L;

    // setup to use Unsbfe.compbreAndSwbpInt for updbtes
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long vblueOffset;

    stbtic {
        try {
            vblueOffset = unsbfe.objectFieldOffset
                (AtomicInteger.clbss.getDeclbredField("vblue"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

    privbte volbtile int vblue;

    /**
     * Crebtes b new AtomicInteger with the given initibl vblue.
     *
     * @pbrbm initiblVblue the initibl vblue
     */
    public AtomicInteger(int initiblVblue) {
        vblue = initiblVblue;
    }

    /**
     * Crebtes b new AtomicInteger with initibl vblue {@code 0}.
     */
    public AtomicInteger() {
    }

    /**
     * Gets the current vblue.
     *
     * @return the current vblue
     */
    public finbl int get() {
        return vblue;
    }

    /**
     * Sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(int newVblue) {
        vblue = newVblue;
    }

    /**
     * Eventublly sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(int newVblue) {
        unsbfe.putOrderedInt(this, vblueOffset, newVblue);
    }

    /**
     * Atomicblly sets to the given vblue bnd returns the old vblue.
     *
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    public finbl int getAndSet(int newVblue) {
        return unsbfe.getAndSetInt(this, vblueOffset, newVblue);
    }

    /**
     * Atomicblly sets the vblue to the given updbted vblue
     * if the current vblue {@code ==} the expected vblue.
     *
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful. Fblse return indicbtes thbt
     * the bctubl vblue wbs not equbl to the expected vblue.
     */
    public finbl boolebn compbreAndSet(int expect, int updbte) {
        return unsbfe.compbreAndSwbpInt(this, vblueOffset, expect, updbte);
    }

    /**
     * Atomicblly sets the vblue to the given updbted vblue
     * if the current vblue {@code ==} the expected vblue.
     *
     * <p><b href="pbckbge-summbry.html#webkCompbreAndSet">Mby fbil
     * spuriously bnd does not provide ordering gubrbntees</b>, so is
     * only rbrely bn bppropribte blternbtive to {@code compbreAndSet}.
     *
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful
     */
    public finbl boolebn webkCompbreAndSet(int expect, int updbte) {
        return unsbfe.compbreAndSwbpInt(this, vblueOffset, expect, updbte);
    }

    /**
     * Atomicblly increments by one the current vblue.
     *
     * @return the previous vblue
     */
    public finbl int getAndIncrement() {
        return unsbfe.getAndAddInt(this, vblueOffset, 1);
    }

    /**
     * Atomicblly decrements by one the current vblue.
     *
     * @return the previous vblue
     */
    public finbl int getAndDecrement() {
        return unsbfe.getAndAddInt(this, vblueOffset, -1);
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue.
     *
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     */
    public finbl int getAndAdd(int deltb) {
        return unsbfe.getAndAddInt(this, vblueOffset, deltb);
    }

    /**
     * Atomicblly increments by one the current vblue.
     *
     * @return the updbted vblue
     */
    public finbl int incrementAndGet() {
        return unsbfe.getAndAddInt(this, vblueOffset, 1) + 1;
    }

    /**
     * Atomicblly decrements by one the current vblue.
     *
     * @return the updbted vblue
     */
    public finbl int decrementAndGet() {
        return unsbfe.getAndAddInt(this, vblueOffset, -1) - 1;
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue.
     *
     * @pbrbm deltb the vblue to bdd
     * @return the updbted vblue
     */
    public finbl int bddAndGet(int deltb) {
        return unsbfe.getAndAddInt(this, vblueOffset, deltb) + deltb;
    }

    /**
     * Atomicblly updbtes the current vblue with the results of
     * bpplying the given function, returning the previous vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm updbteFunction b side-effect-free function
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndUpdbte(IntUnbryOperbtor updbteFunction) {
        int prev, next;
        do {
            prev = get();
            next = updbteFunction.bpplyAsInt(prev);
        } while (!compbreAndSet(prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the current vblue with the results of
     * bpplying the given function, returning the updbted vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm updbteFunction b side-effect-free function
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl int updbteAndGet(IntUnbryOperbtor updbteFunction) {
        int prev, next;
        do {
            prev = get();
            next = updbteFunction.bpplyAsInt(prev);
        } while (!compbreAndSet(prev, next));
        return next;
    }

    /**
     * Atomicblly updbtes the current vblue with the results of
     * bpplying the given function to the current bnd given vblues,
     * returning the previous vblue. The function should be
     * side-effect-free, since it mby be re-bpplied when bttempted
     * updbtes fbil due to contention bmong threbds.  The function
     * is bpplied with the current vblue bs its first brgument,
     * bnd the given updbte bs the second brgument.
     *
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndAccumulbte(int x,
                                      IntBinbryOperbtor bccumulbtorFunction) {
        int prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpplyAsInt(prev, x);
        } while (!compbreAndSet(prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the current vblue with the results of
     * bpplying the given function to the current bnd given vblues,
     * returning the updbted vblue. The function should be
     * side-effect-free, since it mby be re-bpplied when bttempted
     * updbtes fbil due to contention bmong threbds.  The function
     * is bpplied with the current vblue bs its first brgument,
     * bnd the given updbte bs the second brgument.
     *
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl int bccumulbteAndGet(int x,
                                      IntBinbryOperbtor bccumulbtorFunction) {
        int prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpplyAsInt(prev, x);
        } while (!compbreAndSet(prev, next));
        return next;
    }

    /**
     * Returns the String representbtion of the current vblue.
     * @return the String representbtion of the current vblue
     */
    public String toString() {
        return Integer.toString(get());
    }

    /**
     * Returns the vblue of this {@code AtomicInteger} bs bn {@code int}.
     */
    public int intVblue() {
        return get();
    }

    /**
     * Returns the vblue of this {@code AtomicInteger} bs b {@code long}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public long longVblue() {
        return (long)get();
    }

    /**
     * Returns the vblue of this {@code AtomicInteger} bs b {@code flobt}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public flobt flobtVblue() {
        return (flobt)get();
    }

    /**
     * Returns the vblue of this {@code AtomicInteger} bs b {@code double}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)get();
    }

}
