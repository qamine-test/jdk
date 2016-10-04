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
 * An {@code AtomicMbrkbbleReference} mbintbins bn object reference
 * blong with b mbrk bit, thbt cbn be updbted btomicblly.
 *
 * <p>Implementbtion note: This implementbtion mbintbins mbrkbble
 * references by crebting internbl objects representing "boxed"
 * [reference, boolebn] pbirs.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <V> The type of object referred to by this reference
 */
public clbss AtomicMbrkbbleReference<V> {

    privbte stbtic clbss Pbir<T> {
        finbl T reference;
        finbl boolebn mbrk;
        privbte Pbir(T reference, boolebn mbrk) {
            this.reference = reference;
            this.mbrk = mbrk;
        }
        stbtic <T> Pbir<T> of(T reference, boolebn mbrk) {
            return new Pbir<T>(reference, mbrk);
        }
    }

    privbte volbtile Pbir<V> pbir;

    /**
     * Crebtes b new {@code AtomicMbrkbbleReference} with the given
     * initibl vblues.
     *
     * @pbrbm initiblRef the initibl reference
     * @pbrbm initiblMbrk the initibl mbrk
     */
    public AtomicMbrkbbleReference(V initiblRef, boolebn initiblMbrk) {
        pbir = Pbir.of(initiblRef, initiblMbrk);
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
     * Returns the current vblue of the mbrk.
     *
     * @return the current vblue of the mbrk
     */
    public boolebn isMbrked() {
        return pbir.mbrk;
    }

    /**
     * Returns the current vblues of both the reference bnd the mbrk.
     * Typicbl usbge is {@code boolebn[1] holder; ref = v.get(holder); }.
     *
     * @pbrbm mbrkHolder bn brrby of size of bt lebst one. On return,
     * {@code mbrkholder[0]} will hold the vblue of the mbrk.
     * @return the current vblue of the reference
     */
    public V get(boolebn[] mbrkHolder) {
        Pbir<V> pbir = this.pbir;
        mbrkHolder[0] = pbir.mbrk;
        return pbir.reference;
    }

    /**
     * Atomicblly sets the vblue of both the reference bnd mbrk
     * to the given updbte vblues if the
     * current reference is {@code ==} to the expected reference
     * bnd the current mbrk is equbl to the expected mbrk.
     *
     * <p><b href="pbckbge-summbry.html#webkCompbreAndSet">Mby fbil
     * spuriously bnd does not provide ordering gubrbntees</b>, so is
     * only rbrely bn bppropribte blternbtive to {@code compbreAndSet}.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm expectedMbrk the expected vblue of the mbrk
     * @pbrbm newMbrk the new vblue for the mbrk
     * @return {@code true} if successful
     */
    public boolebn webkCompbreAndSet(V       expectedReference,
                                     V       newReference,
                                     boolebn expectedMbrk,
                                     boolebn newMbrk) {
        return compbreAndSet(expectedReference, newReference,
                             expectedMbrk, newMbrk);
    }

    /**
     * Atomicblly sets the vblue of both the reference bnd mbrk
     * to the given updbte vblues if the
     * current reference is {@code ==} to the expected reference
     * bnd the current mbrk is equbl to the expected mbrk.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm expectedMbrk the expected vblue of the mbrk
     * @pbrbm newMbrk the new vblue for the mbrk
     * @return {@code true} if successful
     */
    public boolebn compbreAndSet(V       expectedReference,
                                 V       newReference,
                                 boolebn expectedMbrk,
                                 boolebn newMbrk) {
        Pbir<V> current = pbir;
        return
            expectedReference == current.reference &&
            expectedMbrk == current.mbrk &&
            ((newReference == current.reference &&
              newMbrk == current.mbrk) ||
             cbsPbir(current, Pbir.of(newReference, newMbrk)));
    }

    /**
     * Unconditionblly sets the vblue of both the reference bnd mbrk.
     *
     * @pbrbm newReference the new vblue for the reference
     * @pbrbm newMbrk the new vblue for the mbrk
     */
    public void set(V newReference, boolebn newMbrk) {
        Pbir<V> current = pbir;
        if (newReference != current.reference || newMbrk != current.mbrk)
            this.pbir = Pbir.of(newReference, newMbrk);
    }

    /**
     * Atomicblly sets the vblue of the mbrk to the given updbte vblue
     * if the current reference is {@code ==} to the expected
     * reference.  Any given invocbtion of this operbtion mby fbil
     * (return {@code fblse}) spuriously, but repebted invocbtion
     * when the current vblue holds the expected vblue bnd no other
     * threbd is blso bttempting to set the vblue will eventublly
     * succeed.
     *
     * @pbrbm expectedReference the expected vblue of the reference
     * @pbrbm newMbrk the new vblue for the mbrk
     * @return {@code true} if successful
     */
    public boolebn bttemptMbrk(V expectedReference, boolebn newMbrk) {
        Pbir<V> current = pbir;
        return
            expectedReference == current.reference &&
            (newMbrk == current.mbrk ||
             cbsPbir(current, Pbir.of(expectedReference, newMbrk)));
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE = sun.misc.Unsbfe.getUnsbfe();
    privbte stbtic finbl long pbirOffset =
        objectFieldOffset(UNSAFE, "pbir", AtomicMbrkbbleReference.clbss);

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
