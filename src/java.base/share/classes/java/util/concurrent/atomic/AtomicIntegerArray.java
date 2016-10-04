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
 * An {@code int} brrby in which elements mby be updbted btomicblly.
 * See the {@link jbvb.util.concurrent.btomic} pbckbge
 * specificbtion for description of the properties of btomic
 * vbribbles.
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss AtomicIntegerArrby implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 2862133569453604235L;

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl int bbse = unsbfe.brrbyBbseOffset(int[].clbss);
    privbte stbtic finbl int shift;
    privbte finbl int[] brrby;

    stbtic {
        int scble = unsbfe.brrbyIndexScble(int[].clbss);
        if ((scble & (scble - 1)) != 0)
            throw new Error("dbtb type scble not b power of two");
        shift = 31 - Integer.numberOfLebdingZeros(scble);
    }

    privbte long checkedByteOffset(int i) {
        if (i < 0 || i >= brrby.length)
            throw new IndexOutOfBoundsException("index " + i);

        return byteOffset(i);
    }

    privbte stbtic long byteOffset(int i) {
        return ((long) i << shift) + bbse;
    }

    /**
     * Crebtes b new AtomicIntegerArrby of the given length, with bll
     * elements initiblly zero.
     *
     * @pbrbm length the length of the brrby
     */
    public AtomicIntegerArrby(int length) {
        brrby = new int[length];
    }

    /**
     * Crebtes b new AtomicIntegerArrby with the sbme length bs, bnd
     * bll elements copied from, the given brrby.
     *
     * @pbrbm brrby the brrby to copy elements from
     * @throws NullPointerException if brrby is null
     */
    public AtomicIntegerArrby(int[] brrby) {
        // Visibility gubrbnteed by finbl field gubrbntees
        this.brrby = brrby.clone();
    }

    /**
     * Returns the length of the brrby.
     *
     * @return the length of the brrby
     */
    public finbl int length() {
        return brrby.length;
    }

    /**
     * Gets the current vblue bt position {@code i}.
     *
     * @pbrbm i the index
     * @return the current vblue
     */
    public finbl int get(int i) {
        return getRbw(checkedByteOffset(i));
    }

    privbte int getRbw(long offset) {
        return unsbfe.getIntVolbtile(brrby, offset);
    }

    /**
     * Sets the element bt position {@code i} to the given vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(int i, int newVblue) {
        unsbfe.putIntVolbtile(brrby, checkedByteOffset(i), newVblue);
    }

    /**
     * Eventublly sets the element bt position {@code i} to the given vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(int i, int newVblue) {
        unsbfe.putOrderedInt(brrby, checkedByteOffset(i), newVblue);
    }

    /**
     * Atomicblly sets the element bt position {@code i} to the given
     * vblue bnd returns the old vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    public finbl int getAndSet(int i, int newVblue) {
        return unsbfe.getAndSetInt(brrby, checkedByteOffset(i), newVblue);
    }

    /**
     * Atomicblly sets the element bt position {@code i} to the given
     * updbted vblue if the current vblue {@code ==} the expected vblue.
     *
     * @pbrbm i the index
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful. Fblse return indicbtes thbt
     * the bctubl vblue wbs not equbl to the expected vblue.
     */
    public finbl boolebn compbreAndSet(int i, int expect, int updbte) {
        return compbreAndSetRbw(checkedByteOffset(i), expect, updbte);
    }

    privbte boolebn compbreAndSetRbw(long offset, int expect, int updbte) {
        return unsbfe.compbreAndSwbpInt(brrby, offset, expect, updbte);
    }

    /**
     * Atomicblly sets the element bt position {@code i} to the given
     * updbted vblue if the current vblue {@code ==} the expected vblue.
     *
     * <p><b href="pbckbge-summbry.html#webkCompbreAndSet">Mby fbil
     * spuriously bnd does not provide ordering gubrbntees</b>, so is
     * only rbrely bn bppropribte blternbtive to {@code compbreAndSet}.
     *
     * @pbrbm i the index
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful
     */
    public finbl boolebn webkCompbreAndSet(int i, int expect, int updbte) {
        return compbreAndSet(i, expect, updbte);
    }

    /**
     * Atomicblly increments by one the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @return the previous vblue
     */
    public finbl int getAndIncrement(int i) {
        return getAndAdd(i, 1);
    }

    /**
     * Atomicblly decrements by one the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @return the previous vblue
     */
    public finbl int getAndDecrement(int i) {
        return getAndAdd(i, -1);
    }

    /**
     * Atomicblly bdds the given vblue to the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     */
    public finbl int getAndAdd(int i, int deltb) {
        return unsbfe.getAndAddInt(brrby, checkedByteOffset(i), deltb);
    }

    /**
     * Atomicblly increments by one the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @return the updbted vblue
     */
    public finbl int incrementAndGet(int i) {
        return getAndAdd(i, 1) + 1;
    }

    /**
     * Atomicblly decrements by one the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @return the updbted vblue
     */
    public finbl int decrementAndGet(int i) {
        return getAndAdd(i, -1) - 1;
    }

    /**
     * Atomicblly bdds the given vblue to the element bt index {@code i}.
     *
     * @pbrbm i the index
     * @pbrbm deltb the vblue to bdd
     * @return the updbted vblue
     */
    public finbl int bddAndGet(int i, int deltb) {
        return getAndAdd(i, deltb) + deltb;
    }


    /**
     * Atomicblly updbtes the element bt index {@code i} with the results
     * of bpplying the given function, returning the previous vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm i the index
     * @pbrbm updbteFunction b side-effect-free function
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndUpdbte(int i, IntUnbryOperbtor updbteFunction) {
        long offset = checkedByteOffset(i);
        int prev, next;
        do {
            prev = getRbw(offset);
            next = updbteFunction.bpplyAsInt(prev);
        } while (!compbreAndSetRbw(offset, prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the element bt index {@code i} with the results
     * of bpplying the given function, returning the updbted vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm i the index
     * @pbrbm updbteFunction b side-effect-free function
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl int updbteAndGet(int i, IntUnbryOperbtor updbteFunction) {
        long offset = checkedByteOffset(i);
        int prev, next;
        do {
            prev = getRbw(offset);
            next = updbteFunction.bpplyAsInt(prev);
        } while (!compbreAndSetRbw(offset, prev, next));
        return next;
    }

    /**
     * Atomicblly updbtes the element bt index {@code i} with the
     * results of bpplying the given function to the current bnd
     * given vblues, returning the previous vblue. The function should
     * be side-effect-free, since it mby be re-bpplied when bttempted
     * updbtes fbil due to contention bmong threbds.  The function is
     * bpplied with the current vblue bt index {@code i} bs its first
     * brgument, bnd the given updbte bs the second brgument.
     *
     * @pbrbm i the index
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the previous vblue
     * @since 1.8
     */
    public finbl int getAndAccumulbte(int i, int x,
                                      IntBinbryOperbtor bccumulbtorFunction) {
        long offset = checkedByteOffset(i);
        int prev, next;
        do {
            prev = getRbw(offset);
            next = bccumulbtorFunction.bpplyAsInt(prev, x);
        } while (!compbreAndSetRbw(offset, prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the element bt index {@code i} with the
     * results of bpplying the given function to the current bnd
     * given vblues, returning the updbted vblue. The function should
     * be side-effect-free, since it mby be re-bpplied when bttempted
     * updbtes fbil due to contention bmong threbds.  The function is
     * bpplied with the current vblue bt index {@code i} bs its first
     * brgument, bnd the given updbte bs the second brgument.
     *
     * @pbrbm i the index
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl int bccumulbteAndGet(int i, int x,
                                      IntBinbryOperbtor bccumulbtorFunction) {
        long offset = checkedByteOffset(i);
        int prev, next;
        do {
            prev = getRbw(offset);
            next = bccumulbtorFunction.bpplyAsInt(prev, x);
        } while (!compbreAndSetRbw(offset, prev, next));
        return next;
    }

    /**
     * Returns the String representbtion of the current vblues of brrby.
     * @return the String representbtion of the current vblues of brrby
     */
    public String toString() {
        int iMbx = brrby.length - 1;
        if (iMbx == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.bppend('[');
        for (int i = 0; ; i++) {
            b.bppend(getRbw(byteOffset(i)));
            if (i == iMbx)
                return b.bppend(']').toString();
            b.bppend(',').bppend(' ');
        }
    }

}
