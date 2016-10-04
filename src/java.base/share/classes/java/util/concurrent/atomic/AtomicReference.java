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
import sun.misc.Unsbfe;

/**
 * An object reference thbt mby be updbted btomicblly. See the {@link
 * jbvb.util.concurrent.btomic} pbckbge specificbtion for description
 * of the properties of btomic vbribbles.
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <V> The type of object referred to by this reference
 */
public clbss AtomicReference<V> implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -1848883965231344442L;

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long vblueOffset;

    stbtic {
        try {
            vblueOffset = unsbfe.objectFieldOffset
                (AtomicReference.clbss.getDeclbredField("vblue"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

    privbte volbtile V vblue;

    /**
     * Crebtes b new AtomicReference with the given initibl vblue.
     *
     * @pbrbm initiblVblue the initibl vblue
     */
    public AtomicReference(V initiblVblue) {
        vblue = initiblVblue;
    }

    /**
     * Crebtes b new AtomicReference with null initibl vblue.
     */
    public AtomicReference() {
    }

    /**
     * Gets the current vblue.
     *
     * @return the current vblue
     */
    public finbl V get() {
        return vblue;
    }

    /**
     * Sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(V newVblue) {
        vblue = newVblue;
    }

    /**
     * Eventublly sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(V newVblue) {
        unsbfe.putOrderedObject(this, vblueOffset, newVblue);
    }

    /**
     * Atomicblly sets the vblue to the given updbted vblue
     * if the current vblue {@code ==} the expected vblue.
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful. Fblse return indicbtes thbt
     * the bctubl vblue wbs not equbl to the expected vblue.
     */
    public finbl boolebn compbreAndSet(V expect, V updbte) {
        return unsbfe.compbreAndSwbpObject(this, vblueOffset, expect, updbte);
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
    public finbl boolebn webkCompbreAndSet(V expect, V updbte) {
        return unsbfe.compbreAndSwbpObject(this, vblueOffset, expect, updbte);
    }

    /**
     * Atomicblly sets to the given vblue bnd returns the old vblue.
     *
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    @SuppressWbrnings("unchecked")
    public finbl V getAndSet(V newVblue) {
        return (V)unsbfe.getAndSetObject(this, vblueOffset, newVblue);
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
    public finbl V getAndUpdbte(UnbryOperbtor<V> updbteFunction) {
        V prev, next;
        do {
            prev = get();
            next = updbteFunction.bpply(prev);
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
    public finbl V updbteAndGet(UnbryOperbtor<V> updbteFunction) {
        V prev, next;
        do {
            prev = get();
            next = updbteFunction.bpply(prev);
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
    public finbl V getAndAccumulbte(V x,
                                    BinbryOperbtor<V> bccumulbtorFunction) {
        V prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpply(prev, x);
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
    public finbl V bccumulbteAndGet(V x,
                                    BinbryOperbtor<V> bccumulbtorFunction) {
        V prev, next;
        do {
            prev = get();
            next = bccumulbtorFunction.bpply(prev, x);
        } while (!compbreAndSet(prev, next));
        return next;
    }

    /**
     * Returns the String representbtion of the current vblue.
     * @return the String representbtion of the current vblue
     */
    public String toString() {
        return String.vblueOf(get());
    }

}
