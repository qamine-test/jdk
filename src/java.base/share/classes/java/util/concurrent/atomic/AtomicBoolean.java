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
import sun.misc.Unsbfe;

/**
 * A {@code boolebn} vblue thbt mby be updbted btomicblly. See the
 * {@link jbvb.util.concurrent.btomic} pbckbge specificbtion for
 * description of the properties of btomic vbribbles. An
 * {@code AtomicBoolebn} is used in bpplicbtions such bs btomicblly
 * updbted flbgs, bnd cbnnot be used bs b replbcement for b
 * {@link jbvb.lbng.Boolebn}.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss AtomicBoolebn implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 4654671469794556979L;
    // setup to use Unsbfe.compbreAndSwbpInt for updbtes
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long vblueOffset;

    stbtic {
        try {
            vblueOffset = unsbfe.objectFieldOffset
                (AtomicBoolebn.clbss.getDeclbredField("vblue"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }

    privbte volbtile int vblue;

    /**
     * Crebtes b new {@code AtomicBoolebn} with the given initibl vblue.
     *
     * @pbrbm initiblVblue the initibl vblue
     */
    public AtomicBoolebn(boolebn initiblVblue) {
        vblue = initiblVblue ? 1 : 0;
    }

    /**
     * Crebtes b new {@code AtomicBoolebn} with initibl vblue {@code fblse}.
     */
    public AtomicBoolebn() {
    }

    /**
     * Returns the current vblue.
     *
     * @return the current vblue
     */
    public finbl boolebn get() {
        return vblue != 0;
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
    public finbl boolebn compbreAndSet(boolebn expect, boolebn updbte) {
        int e = expect ? 1 : 0;
        int u = updbte ? 1 : 0;
        return unsbfe.compbreAndSwbpInt(this, vblueOffset, e, u);
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
    public boolebn webkCompbreAndSet(boolebn expect, boolebn updbte) {
        int e = expect ? 1 : 0;
        int u = updbte ? 1 : 0;
        return unsbfe.compbreAndSwbpInt(this, vblueOffset, e, u);
    }

    /**
     * Unconditionblly sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     */
    public finbl void set(boolebn newVblue) {
        vblue = newVblue ? 1 : 0;
    }

    /**
     * Eventublly sets to the given vblue.
     *
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public finbl void lbzySet(boolebn newVblue) {
        int v = newVblue ? 1 : 0;
        unsbfe.putOrderedInt(this, vblueOffset, v);
    }

    /**
     * Atomicblly sets to the given vblue bnd returns the previous vblue.
     *
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    public finbl boolebn getAndSet(boolebn newVblue) {
        boolebn prev;
        do {
            prev = get();
        } while (!compbreAndSet(prev, newVblue));
        return prev;
    }

    /**
     * Returns the String representbtion of the current vblue.
     * @return the String representbtion of the current vblue
     */
    public String toString() {
        return Boolebn.toString(get());
    }

}
