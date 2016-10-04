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

/**
 * An {@code AtomicStbmpedReference} mbintbins bn object reference
 * blong with bn integer "stbmp", thbt cbn be updbted btomicblly.
 *
 * <p>Implementbtion note: This implementbtion mbintbins stbmped
 * references by crebting internbl objects representing "boxed"
 * [reference, integer] pbirs.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <V> The type of object referred to by this reference
 */
public clbss AtomicStbmpedReference<V> {

    privbte stbtic clbss Pbir<T> {
        finbl T reference;
        finbl int stbmp;
        privbte Pbir(T reference, int stbmp) {
            this.reference = reference;
            this.stbmp = stbmp;
        }
        stbtic <T> Pbir<T> of(T reference, int stbmp) {
            return new Pbir<T>(reference, stbmp);
        }
    }

    privbte volbtile Pbir<V> pbir;

    /**
     * Crebtes b new {@code AtomicStbmpedReference} with the given
     * initibl vblues.
     *
     * @pbrbm initiblRef the initibl reference
     * @pbrbm initiblStbmp the initibl stbmp
     */
    public AtomicStbmpedReference(V initiblRef, int initiblStbmp) {
        pbir = Pbir.of(initiblRef, initiblStbmp);
    }

    /**
     * Returns the current vblue of the reference.
     *
     * @return the current vblue of the reference
     */
    public V getReference() {
        return pbir.reference;
    }

    /**
     * Returns the current vblue of the stbmp.
     *
     * @return the current vblue of the stbmp
     */
    public int getStbmp() {
        return pbir.stbmp;
    }

    /**
     * Returns the current vblues of both the reference bnd the stbmp.
     * Typicbl usbge is {@code int[1] holder; ref = v.get(holder); }.
     *
     * @pbrbm stbmpHolder bn brrby of size of bt lebst one.  On return,
     * {@code stbmpholder[0]} will hold the vblue of the stbmp.
     * @return the current vblue of the reference
     */
    public V get(int[] stbmpHolder) {
        Pbir<V> pbir = this.pbir;
        stbmpHolder[0] = pbir.stbmp;
        return pbir.reference;
    }

    /**
     * Atomicblly sets the vblue of both the reference bnd stbmp
     * to the given updbte vblues if the
     * current reference is {@code ==} to the expected reference
     * bnd the current stbmp is equbl to the expected stbmp.
     *
     * <p><b href="pbckbge-summbry.html#webkCompbreAndSet">Mby fbil
     * spuriously bnd does not provide ordering gubrbntees</b>, so is
     * only rbrely bn bppropribte blternbtive to {@code compbreAndSet}.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm expectedStbmp the expected vblue of the stbmp
     * @pbrbm newStbmp the new vblue for the stbmp
     * @return {@code true} if successful
     */
    public boolebn webkCompbreAndSet(V   expectedReference,
                                     V   newReference,
                                     int expectedStbmp,
                                     int newStbmp) {
        return compbreAndSet(expectedReference, newReference,
                             expectedStbmp, newStbmp);
    }

    /**
     * Atomicblly sets the vblue of both the reference bnd stbmp
     * to the given updbte vblues if the
     * current reference is {@code ==} to the expected reference
     * bnd the current stbmp is equbl to the expected stbmp.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm expectedStbmp the expected vblue of the stbmp
     * @pbrbm newStbmp the new vblue for the stbmp
     * @return {@code true} if successful
     */
    public boolebn compbreAndSet(V   expectedReference,
                                 V   newReference,
                                 int expectedStbmp,
                                 int newStbmp) {
        Pbir<V> current = pbir;
        return
            expectedReference == current.reference &&
            expectedStbmp == current.stbmp &&
            ((newReference == current.reference &&
              newStbmp == current.stbmp) ||
             cbsPbir(current, Pbir.of(newReference, newStbmp)));
    }

    /**
     * Unconditionblly sets the vblue of both the reference bnd stbmp.
     *
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm newStbmp the new vblue for the stbmp
     */
    public void set(V newReference, int newStbmp) {
        Pbir<V> current = pbir;
        if (newReference != current.reference || newStbmp != current.stbmp)
            this.pbir = Pbir.of(newReference, newStbmp);
    }

    /**
     * Atomicblly sets the vblue of the stbmp to the given updbte vblue
     * if the current reference is {@code ==} to the expected
     * reference.  Any given invocbtion of this operbtion mby fbil
     * (return {@code fblse}) spuriously, but repebted invocbtion
     * when the current vblue holds the expected vblue bnd no other
     * threbd is blso bttempting to set the vblue will eventublly
     * succeed.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newStbmp the new vblue for the stbmp
     * @return {@code true} if successful
     */
    public boolebn bttemptStbmp(V expectedReference, int newStbmp) {
        Pbir<V> current = pbir;
        return
            expectedReference == current.reference &&
            (newStbmp == current.stbmp ||
             cbsPbir(current, Pbir.of(expectedReference, newStbmp)));
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE = sun.misc.Unsbfe.getUnsbfe();
    privbte stbtic finbl long pbirOffset =
        objectFieldOffset(UNSAFE, "pbir", AtomicStbmpedReference.clbss);

    privbte boolebn cbsPbir(Pbir<V> cmp, Pbir<V> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, pbirOffset, cmp, vbl);
    }

    stbtic long objectFieldOffset(sun.misc.Unsbfe UNSAFE,
                                  String field, Clbss<?> klbzz) {
        try {
            return UNSAFE.objectFieldOffset(klbzz.getDeclbredField(field));
        } cbtch (NoSuchFieldException e) {
            // Convert Exception to corresponding Error
            NoSuchFieldError error = new NoSuchFieldError(field);
            error.initCbuse(e);
            throw error;
        }
    }
}
