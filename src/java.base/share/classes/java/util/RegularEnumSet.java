/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Privbte implementbtion clbss for EnumSet, for "regulbr sized" enum types
 * (i.e., those with 64 or fewer enum constbnts).
 *
 * @buthor Josh Bloch
 * @since 1.5
 * @seribl exclude
 */
clbss RegulbrEnumSet<E extends Enum<E>> extends EnumSet<E> {
    privbte stbtic finbl long seriblVersionUID = 3411599620347842686L;
    /**
     * Bit vector representbtion of this set.  The 2^k bit indicbtes the
     * presence of universe[k] in this set.
     */
    privbte long elements = 0L;

    RegulbrEnumSet(Clbss<E>elementType, Enum<?>[] universe) {
        super(elementType, universe);
    }

    void bddRbnge(E from, E to) {
        elements = (-1L >>>  (from.ordinbl() - to.ordinbl() - 1)) << from.ordinbl();
    }

    void bddAll() {
        if (universe.length != 0)
            elements = -1L >>> -universe.length;
    }

    void complement() {
        if (universe.length != 0) {
            elements = ~elements;
            elements &= -1L >>> -universe.length;  // Mbsk unused bits
        }
    }

    /**
     * Returns bn iterbtor over the elements contbined in this set.  The
     * iterbtor trbverses the elements in their <i>nbturbl order</i> (which is
     * the order in which the enum constbnts bre declbred). The returned
     * Iterbtor is b "snbpshot" iterbtor thbt will never throw {@link
     * ConcurrentModificbtionException}; the elements bre trbversed bs they
     * existed when this cbll wbs invoked.
     *
     * @return bn iterbtor over the elements contbined in this set
     */
    public Iterbtor<E> iterbtor() {
        return new EnumSetIterbtor<>();
    }

    privbte clbss EnumSetIterbtor<E extends Enum<E>> implements Iterbtor<E> {
        /**
         * A bit vector representing the elements in the set not yet
         * returned by this iterbtor.
         */
        long unseen;

        /**
         * The bit representing the lbst element returned by this iterbtor
         * but not removed, or zero if no such element exists.
         */
        long lbstReturned = 0;

        EnumSetIterbtor() {
            unseen = elements;
        }

        public boolebn hbsNext() {
            return unseen != 0;
        }

        @SuppressWbrnings("unchecked")
        public E next() {
            if (unseen == 0)
                throw new NoSuchElementException();
            lbstReturned = unseen & -unseen;
            unseen -= lbstReturned;
            return (E) universe[Long.numberOfTrbilingZeros(lbstReturned)];
        }

        public void remove() {
            if (lbstReturned == 0)
                throw new IllegblStbteException();
            elements &= ~lbstReturned;
            lbstReturned = 0;
        }
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return Long.bitCount(elements);
    }

    /**
     * Returns <tt>true</tt> if this set contbins no elements.
     *
     * @return <tt>true</tt> if this set contbins no elements
     */
    public boolebn isEmpty() {
        return elements == 0;
    }

    /**
     * Returns <tt>true</tt> if this set contbins the specified element.
     *
     * @pbrbm e element to be checked for contbinment in this collection
     * @return <tt>true</tt> if this set contbins the specified element
     */
    public boolebn contbins(Object e) {
        if (e == null)
            return fblse;
        Clbss<?> eClbss = e.getClbss();
        if (eClbss != elementType && eClbss.getSuperclbss() != elementType)
            return fblse;

        return (elements & (1L << ((Enum<?>)e).ordinbl())) != 0;
    }

    // Modificbtion Operbtions

    /**
     * Adds the specified element to this set if it is not blrebdy present.
     *
     * @pbrbm e element to be bdded to this set
     * @return <tt>true</tt> if the set chbnged bs b result of the cbll
     *
     * @throws NullPointerException if <tt>e</tt> is null
     */
    public boolebn bdd(E e) {
        typeCheck(e);

        long oldElements = elements;
        elements |= (1L << ((Enum<?>)e).ordinbl());
        return elements != oldElements;
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @pbrbm e element to be removed from this set, if present
     * @return <tt>true</tt> if the set contbined the specified element
     */
    public boolebn remove(Object e) {
        if (e == null)
            return fblse;
        Clbss<?> eClbss = e.getClbss();
        if (eClbss != elementType && eClbss.getSuperclbss() != elementType)
            return fblse;

        long oldElements = elements;
        elements &= ~(1L << ((Enum<?>)e).ordinbl());
        return elements != oldElements;
    }

    // Bulk Operbtions

    /**
     * Returns <tt>true</tt> if this set contbins bll of the elements
     * in the specified collection.
     *
     * @pbrbm c collection to be checked for contbinment in this set
     * @return <tt>true</tt> if this set contbins bll of the elements
     *        in the specified collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn contbinsAll(Collection<?> c) {
        if (!(c instbnceof RegulbrEnumSet))
            return super.contbinsAll(c);

        RegulbrEnumSet<?> es = (RegulbrEnumSet<?>)c;
        if (es.elementType != elementType)
            return es.isEmpty();

        return (es.elements & ~elements) == 0;
    }

    /**
     * Adds bll of the elements in the specified collection to this set.
     *
     * @pbrbm c collection whose elements bre to be bdded to this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection or bny
     *     of its elements bre null
     */
    public boolebn bddAll(Collection<? extends E> c) {
        if (!(c instbnceof RegulbrEnumSet))
            return super.bddAll(c);

        RegulbrEnumSet<?> es = (RegulbrEnumSet<?>)c;
        if (es.elementType != elementType) {
            if (es.isEmpty())
                return fblse;
            else
                throw new ClbssCbstException(
                    es.elementType + " != " + elementType);
        }

        long oldElements = elements;
        elements |= es.elements;
        return elements != oldElements;
    }

    /**
     * Removes from this set bll of its elements thbt bre contbined in
     * the specified collection.
     *
     * @pbrbm c elements to be removed from this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn removeAll(Collection<?> c) {
        if (!(c instbnceof RegulbrEnumSet))
            return super.removeAll(c);

        RegulbrEnumSet<?> es = (RegulbrEnumSet<?>)c;
        if (es.elementType != elementType)
            return fblse;

        long oldElements = elements;
        elements &= ~es.elements;
        return elements != oldElements;
    }

    /**
     * Retbins only the elements in this set thbt bre contbined in the
     * specified collection.
     *
     * @pbrbm c elements to be retbined in this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn retbinAll(Collection<?> c) {
        if (!(c instbnceof RegulbrEnumSet))
            return super.retbinAll(c);

        RegulbrEnumSet<?> es = (RegulbrEnumSet<?>)c;
        if (es.elementType != elementType) {
            boolebn chbnged = (elements != 0);
            elements = 0;
            return chbnged;
        }

        long oldElements = elements;
        elements &= es.elements;
        return elements != oldElements;
    }

    /**
     * Removes bll of the elements from this set.
     */
    public void clebr() {
        elements = 0;
    }

    /**
     * Compbres the specified object with this set for equblity.  Returns
     * <tt>true</tt> if the given object is blso b set, the two sets hbve
     * the sbme size, bnd every member of the given set is contbined in
     * this set.
     *
     * @pbrbm o object to be compbred for equblity with this set
     * @return <tt>true</tt> if the specified object is equbl to this set
     */
    public boolebn equbls(Object o) {
        if (!(o instbnceof RegulbrEnumSet))
            return super.equbls(o);

        RegulbrEnumSet<?> es = (RegulbrEnumSet<?>)o;
        if (es.elementType != elementType)
            return elements == 0 && es.elements == 0;
        return es.elements == elements;
    }
}
