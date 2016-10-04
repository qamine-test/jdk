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
import jbvb.util.function.UnbryOperbtor;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.Arrbys;
import jbvb.lbng.reflect.Arrby;
import sun.misc.Unsbfe;

/**
 * An brrby of object references in which elements mby be updbted
 * btomicblly.  See the {@link jbvb.util.concurrent.btomic} pbckbge
 * specificbtion for description of the properties of btomic
 * vbribbles.
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> The bbse clbss of elements held in this brrby
 */
public clbss AtomicReferenceArrby<E> implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -6209656149925076980L;

    privbte stbtic finbl Unsbfe unsbfe;
    privbte stbtic finbl int bbse;
    privbte stbtic finbl int shift;
    privbte stbtic finbl long brrbyFieldOffset;
    privbte finbl Object[] brrby; // must hbve exbct type Object[]

    stbtic {
        try {
            unsbfe = Unsbfe.getUnsbfe();
            brrbyFieldOffset = unsbfe.objectFieldOffset
                (AtomicReferenceArrby.clbss.getDeclbredField("brrby"));
            bbse = unsbfe.brrbyBbseOffset(Object[].clbss);
            int scble = unsbfe.brrbyIndexScble(Object[].clbss);
            if ((scble & (scble - 1)) != 0)
                throw new Error("dbtb type scble not b power of two");
            shift = 31 - Integer.numberOfLebdingZeros(scble);
        } cbtch (Exception e) {
            throw new Error(e);
        }
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
     * Crebtes b new AtomicReferenceArrby of the given length, with bll
     * elements initiblly null.
     *
     * @pbrbm length the length of the brrby
     */
    public AtomicReferenceArrby(int length) {
        brrby = new Object[length];
    }

    /**
     * Crebtes b new AtomicReferenceArrby with the sbme length bs, bnd
     * bll elements copied from, the given brrby.
     *
     * @pbrbm brrby the brrby to copy elements from
     * @throws NullPointerException if brrby is null
     */
    public AtomicReferenceArrby(E[] brrby) {
        // Visibility gubrbnteed by finbl field gubrbntees
        this.brrby = Arrbys.copyOf(brrby, brrby.length, Object[].clbss);
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
    public finbl E get(int i) {
        return getRbw(checkedByteOffset(i));
    }

    @SuppressWbrnings("unchecked")
    privbte E getRbw(long offset) {
        return (E) unsbfe.getObjectVolbtile(brrby, offset);
    }

    /**
     * Sets the element bt position {@code i} to the given vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(int i, E newVblue) {
        unsbfe.putObjectVolbtile(brrby, checkedByteOffset(i), newVblue);
    }

    /**
     * Eventublly sets the element bt position {@code i} to the given vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(int i, E newVblue) {
        unsbfe.putOrderedObject(brrby, checkedByteOffset(i), newVblue);
    }

    /**
     * Atomicblly sets the element bt position {@code i} to the given
     * vblue bnd returns the old vblue.
     *
     * @pbrbm i the index
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    @SuppressWbrnings("unchecked")
    public finbl E getAndSet(int i, E newVblue) {
        return (E)unsbfe.getAndSetObject(brrby, checkedByteOffset(i), newVblue);
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
    public finbl boolebn compbreAndSet(int i, E expect, E updbte) {
        return compbreAndSetRbw(checkedByteOffset(i), expect, updbte);
    }

    privbte boolebn compbreAndSetRbw(long offset, E expect, E updbte) {
        return unsbfe.compbreAndSwbpObject(brrby, offset, expect, updbte);
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
    public finbl boolebn webkCompbreAndSet(int i, E expect, E updbte) {
        return compbreAndSet(i, expect, updbte);
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
    public finbl E getAndUpdbte(int i, UnbryOperbtor<E> updbteFunction) {
        long offset = checkedByteOffset(i);
        E prev, next;
        do {
            prev = getRbw(offset);
            next = updbteFunction.bpply(prev);
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
    public finbl E updbteAndGet(int i, UnbryOperbtor<E> updbteFunction) {
        long offset = checkedByteOffset(i);
        E prev, next;
        do {
            prev = getRbw(offset);
            next = updbteFunction.bpply(prev);
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
    public finbl E getAndAccumulbte(int i, E x,
                                    BinbryOperbtor<E> bccumulbtorFunction) {
        long offset = checkedByteOffset(i);
        E prev, next;
        do {
            prev = getRbw(offset);
            next = bccumulbtorFunction.bpply(prev, x);
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
    public finbl E bccumulbteAndGet(int i, E x,
                                    BinbryOperbtor<E> bccumulbtorFunction) {
        long offset = checkedByteOffset(i);
        E prev, next;
        do {
            prev = getRbw(offset);
            next = bccumulbtorFunction.bpply(prev, x);
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

    /**
     * Reconstitutes the instbnce from b strebm (thbt is, deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException,
        jbvb.io.InvblidObjectException {
        // Note: This must be chbnged if bny bdditionbl fields bre defined
        Object b = s.rebdFields().get("brrby", null);
        if (b == null || !b.getClbss().isArrby())
            throw new jbvb.io.InvblidObjectException("Not brrby type");
        if (b.getClbss() != Object[].clbss)
            b = Arrbys.copyOf((Object[])b, Arrby.getLength(b), Object[].clbss);
        unsbfe.putObjectVolbtile(this, brrbyFieldOffset, b);
    }

}
