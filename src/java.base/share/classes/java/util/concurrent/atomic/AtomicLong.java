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
import jbvb.util.function.LongUnbryOperbtor;
import jbvb.util.function.LongBinbryOperbtor;
import sun.misc.Unsbfe;

/**
 * A {@code long} vblue thbt mby be updbted btomicblly.  See the
 * {@link jbvb.util.concurrent.btomic} pbckbge specificbtion for
 * description of the properties of btomic vbribbles. An
 * {@code AtomicLong} is used in bpplicbtions such bs btomicblly
 * incremented sequence numbers, bnd cbnnot be used bs b replbcement
 * for b {@link jbvb.lbng.Long}. However, this clbss does extend
 * {@code Number} to bllow uniform bccess by tools bnd utilities thbt
 * debl with numericblly-bbsed clbsses.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss AtomicLong extends Number implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 1927816293512124184L;

    // setup to use Unsbfe.compbreAndSwbpLong for updbtes
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long vblueOffset;

    /**
     * Records whether the underlying JVM supports lockless
     * compbreAndSwbp for longs. While the Unsbfe.compbreAndSwbpLong
     * method works in either cbse, some constructions should be
     * hbndled bt Jbvb level to bvoid locking user-visible locks.
     */
    stbtic finbl boolebn VM_SUPPORTS_LONG_CAS = VMSupportsCS8();

    /**
     * Returns whether underlying JVM supports lockless CompbreAndSet
     * for longs. Cblled only once bnd cbched in VM_SUPPORTS_LONG_CAS.
     */
    privbte stbtic nbtive boolebn VMSupportsCS8();

    stbtic {
        try {
            vblueOffset = unsbfe.objectFieldOffset
                (AtomicLong.clbss.getDeclbredField("vblue"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

    privbte volbtile long vblue;

    /**
     * Crebtes b new AtomicLong with the given initibl vblue.
     *
     * @pbrbm initiblVblue the initibl vblue
     */
    public AtomicLong(long initiblVblue) {
        vblue = initiblVblue;
    }

    /**
     * Crebtes b new AtomicLong with initibl vblue {@code 0}.
     */
    public AtomicLong() {
    }

    /**
     * Gets the current vblue.
     *
     * @return the current vblue
     */
    public finbl long get() {
        return vblue;
    }

    /**
     * Sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(long newVblue) {
        vblue = newVblue;
    }

    /**
     * Eventublly sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(long newVblue) {
        unsbfe.putOrderedLong(this, vblueOffset, newVblue);
    }

    /**
     * Atomicblly sets to the given vblue bnd returns the old vblue.
     *
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    public finbl long getAndSet(long newVblue) {
        return unsbfe.getAndSetLong(this, vblueOffset, newVblue);
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
    public finbl boolebn compbreAndSet(long expect, long updbte) {
        return unsbfe.compbreAndSwbpLong(this, vblueOffset, expect, updbte);
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
    public finbl boolebn webkCompbreAndSet(long expect, long updbte) {
        return unsbfe.compbreAndSwbpLong(this, vblueOffset, expect, updbte);
    }

    /**
     * Atomicblly increments by one the current vblue.
     *
     * @return the previous vblue
     */
    public finbl long getAndIncrement() {
        return unsbfe.getAndAddLong(this, vblueOffset, 1L);
    }

    /**
     * Atomicblly decrements by one the current vblue.
     *
     * @return the previous vblue
     */
    public finbl long getAndDecrement() {
        return unsbfe.getAndAddLong(this, vblueOffset, -1L);
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue.
     *
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     */
    public finbl long getAndAdd(long deltb) {
        return unsbfe.getAndAddLong(this, vblueOffset, deltb);
    }

    /**
     * Atomicblly increments by one the current vblue.
     *
     * @return the updbted vblue
     */
    public finbl long incrementAndGet() {
        return unsbfe.getAndAddLong(this, vblueOffset, 1L) + 1L;
    }

    /**
     * Atomicblly decrements by one the current vblue.
     *
     * @return the updbted vblue
     */
    public finbl long decrementAndGet() {
        return unsbfe.getAndAddLong(this, vblueOffset, -1L) - 1L;
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue.
     *
     * @pbrbm deltb the vblue to bdd
     * @return the updbted vblue
     */
    public finbl long bddAndGet(long deltb) {
        return unsbfe.getAndAddLong(this, vblueOffset, deltb) + deltb;
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
    public finbl long getAndUpdbte(LongUnbryOperbtor updbteFunction) {
        long prev, next;
        do {
            prev = get();
            next = updbteFunction.bpplyAsLong(prev);
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
    public finbl long updbteAndGet(LongUnbryOperbtor updbteFunction) {
        long prev, next;
        do {
            prev = get();
            next = updbteFunction.bpplyAsLong(prev);
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
    public finbl long getAndAccumulbte(long x,
                                       LongBinbryOperbtor bccumulbtorFunction) {
        long prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpplyAsLong(prev, x);
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
    public finbl long bccumulbteAndGet(long x,
                                       LongBinbryOperbtor bccumulbtorFunction) {
        long prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpplyAsLong(prev, x);
        } while (!compbreAndSet(prev, next));
        return next;
    }

    /**
     * Returns the String representbtion of the current vblue.
     * @return the String representbtion of the current vblue
     */
    public String toString() {
        return Long.toString(get());
    }

    /**
     * Returns the vblue of this {@code AtomicLong} bs bn {@code int}
     * bfter b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public int intVblue() {
        return (int)get();
    }

    /**
     * Returns the vblue of this {@code AtomicLong} bs b {@code long}.
     */
    public long longVblue() {
        return get();
    }

    /**
     * Returns the vblue of this {@code AtomicLong} bs b {@code flobt}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public flobt flobtVblue() {
        return (flobt)get();
    }

    /**
     * Returns the vblue of this {@code AtomicLong} bs b {@code double}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)get();
    }

}
