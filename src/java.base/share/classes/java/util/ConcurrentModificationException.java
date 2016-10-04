/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * This exception mby be thrown by methods thbt hbve detected concurrent
 * modificbtion of bn object when such modificbtion is not permissible.
 * <p>
 * For exbmple, it is not generblly permissible for one threbd to modify b Collection
 * while bnother threbd is iterbting over it.  In generbl, the results of the
 * iterbtion bre undefined under these circumstbnces.  Some Iterbtor
 * implementbtions (including those of bll the generbl purpose collection implementbtions
 * provided by the JRE) mby choose to throw this exception if this behbvior is
 * detected.  Iterbtors thbt do this bre known bs <i>fbil-fbst</i> iterbtors,
 * bs they fbil quickly bnd clebnly, rbther thbt risking brbitrbry,
 * non-deterministic behbvior bt bn undetermined time in the future.
 * <p>
 * Note thbt this exception does not blwbys indicbte thbt bn object hbs
 * been concurrently modified by b <i>different</i> threbd.  If b single
 * threbd issues b sequence of method invocbtions thbt violbtes the
 * contrbct of bn object, the object mby throw this exception.  For
 * exbmple, if b threbd modifies b collection directly while it is
 * iterbting over the collection with b fbil-fbst iterbtor, the iterbtor
 * will throw this exception.
 *
 * <p>Note thbt fbil-fbst behbvior cbnnot be gubrbnteed bs it is, generblly
 * spebking, impossible to mbke bny hbrd gubrbntees in the presence of
 * unsynchronized concurrent modificbtion.  Fbil-fbst operbtions
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>{@code ConcurrentModificbtionException}
 * should be used only to detect bugs.</i>
 *
 * @buthor  Josh Bloch
 * @see     Collection
 * @see     Iterbtor
 * @see     Spliterbtor
 * @see     ListIterbtor
 * @see     Vector
 * @see     LinkedList
 * @see     HbshSet
 * @see     Hbshtbble
 * @see     TreeMbp
 * @see     AbstrbctList
 * @since   1.2
 */
public clbss ConcurrentModificbtionException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = -3666751008965953603L;

    /**
     * Constructs b ConcurrentModificbtionException with no
     * detbil messbge.
     */
    public ConcurrentModificbtionException() {
    }

    /**
     * Constructs b {@code ConcurrentModificbtionException} with the
     * specified detbil messbge.
     *
     * @pbrbm messbge the detbil messbge pertbining to this exception.
     */
    public ConcurrentModificbtionException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new exception with the specified cbuse bnd b detbil
     * messbge of {@code (cbuse==null ? null : cbuse.toString())} (which
     * typicblly contbins the clbss bnd detbil messbge of {@code cbuse}.
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.7
     */
    public ConcurrentModificbtionException(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Constructs b new exception with the specified detbil messbge bnd
     * cbuse.
     *
     * <p>Note thbt the detbil messbge bssocibted with <code>cbuse</code> is
     * <i>not</i> butombticblly incorporbted in this exception's detbil
     * messbge.
     *
     * @pbrbm  messbge the detbil messbge (which is sbved for lbter retrievbl
     *         by the {@link Throwbble#getMessbge()} method).
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).  (A {@code null} vblue
     *         is permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since 1.7
     */
    public ConcurrentModificbtionException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }
}
