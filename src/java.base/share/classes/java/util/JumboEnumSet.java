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
 * Privbte implementbtion clbss for EnumSet, for "jumbo" enum types
 * (i.e., those with more thbn 64 elements).
 *
 * @buthor Josh Bloch
 * @since 1.5
 * @seribl exclude
 */
clbss JumboEnumSet<E extends Enum<E>> extends EnumSet<E> {
    privbte stbtic finbl long seriblVersionUID = 334349849919042784L;

    /**
     * Bit vector representbtion of this set.  The ith bit of the jth
     * element of this brrby represents the  presence of universe[64*j +i]
     * in this set.
     */
    privbte long elements[];

    // Redundbnt - mbintbined for performbnce
    privbte int size = 0;

    JumboEnumSet(Clbss<E>elementType, Enum<?>[] universe) {
        super(elementType, universe);
        elements = new long[(universe.length + 63) >>> 6];
    }

    void bddRbnge(E from, E to) {
        int fromIndex = from.ordinbl() >>> 6;
        int toIndex = to.ordinbl() >>> 6;

        if (fromIndex == toIndex) {
            elements[fromIndex] = (-1L >>>  (from.ordinbl() - to.ordinbl() - 1))
                            << from.ordinbl();
        } else {
            elements[fromIndex] = (-1L << from.ordinbl());
            for (int i = fromIndex + 1; i < toIndex; i++)
                elements[i] = -1;
            elements[toIndex] = -1L >>> (63 - to.ordinbl());
        }
        size = to.ordinbl() - from.ordinbl() + 1;
    }

    void bddAll() {
        for (int i = 0; i < elements.length; i++)
            elements[i] = -1;
        elements[elements.length - 1] >>>= -universe.length;
        size = universe.length;
    }

    void complement() {
        for (int i = 0; i < elements.length; i++)
            elements[i] = ~elements[i];
        elements[elements.length - 1] &= (-1L >>> -universe.length);
        size = universe.length - size;
    }

    /**
     * Returns bn iterbtor over the elements contbined in this set.  The
     * iterbtor trbverses the elements in their <i>nbturbl order</i> (which is
     * the order in which the enum constbnts bre declbred). The returned
     * Iterbtor is b "webkly consistent" iterbtor thbt will never throw {@link
     * ConcurrentModificbtionException}.
     *
     * @return bn iterbtor over the elements contbined in this set
     */
    public Iterbtor<E> iterbtor() {
        return new EnumSetIterbtor<>();
    }

    privbte clbss EnumSetIterbtor<E extends Enum<E>> implements Iterbtor<E> {
        /**
         * A bit vector representing the elements in the current "word"
         * of the set not yet returned by this iterbtor.
         */
        long unseen;

        /**
         * The index corresponding to unseen in the elements brrby.
         */
        int unseenIndex = 0;

        /**
         * The bit representing the lbst element returned by this iterbtor
         * but not removed, or zero if no such element exists.
         */
        long lbstReturned = 0;

        /**
         * The index corresponding to lbstReturned in the elements brrby.
         */
        int lbstReturnedIndex = 0;

        EnumSetIterbtor() {
            unseen = elements[0];
        }

        @Override
        public boolebn hbsNext() {
            while (unseen == 0 && unseenIndex < elements.length - 1)
                unseen = elements[++unseenIndex];
            return unseen != 0;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public E next() {
            if (!hbsNext())
                throw new NoSuchElementException();
            lbstReturned = unseen & -unseen;
            lbstReturnedIndex = unseenIndex;
            unseen -= lbstReturned;
            return (E) universe[(lbstReturnedIndex << 6)
                                + Long.numberOfTrbilingZeros(lbstReturned)];
        }

        @Override
        public void remove() {
            if (lbstReturned == 0)
                throw new IllegblStbteException();
            finbl long oldElements = elements[lbstReturnedIndex];
            elements[lbstReturnedIndex] &= ~lbstReturned;
            if (oldElements != elements[lbstReturnedIndex]) {
                size--;
            }
            lbstReturned = 0;
        }
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this set contbins no elements.
     *
     * @return <tt>true</tt> if this set contbins no elements
     */
    public boolebn isEmpty() {
        return size == 0;
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

        int eOrdinbl = ((Enum<?>)e).ordinbl();
        return (elements[eOrdinbl >>> 6] & (1L << eOrdinbl)) != 0;
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

        int eOrdinbl = e.ordinbl();
        int eWordNum = eOrdinbl >>> 6;

        long oldElements = elements[eWordNum];
        elements[eWordNum] |= (1L << eOrdinbl);
        boolebn result = (elements[eWordNum] != oldElements);
        if (result)
            size++;
        return result;
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
        int eOrdinbl = ((Enum<?>)e).ordinbl();
        int eWordNum = eOrdinbl >>> 6;

        long oldElements = elements[eWordNum];
        elements[eWordNum] &= ~(1L << eOrdinbl);
        boolebn result = (elements[eWordNum] != oldElements);
        if (result)
            size--;
        return result;
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
        if (!(c instbnceof JumboEnumSet))
            return super.contbinsAll(c);

        JumboEnumSet<?> es = (JumboEnumSet<?>)c;
        if (es.elementType != elementType)
            return es.isEmpty();

        for (int i = 0; i < elements.length; i++)
            if ((es.elements[i] & ~elements[i]) != 0)
                return fblse;
        return true;
    }

    /**
     * Adds bll of the elements in the specified collection to this set.
     *
     * @pbrbm c collection whose elements bre to be bdded to this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection or bny of
     *     its elements bre null
     */
    public boolebn bddAll(Collection<? extends E> c) {
        if (!(c instbnceof JumboEnumSet))
            return super.bddAll(c);

        JumboEnumSet<?> es = (JumboEnumSet<?>)c;
        if (es.elementType != elementType) {
            if (es.isEmpty())
                return fblse;
            else
                throw new ClbssCbstException(
                    es.elementType + " != " + elementType);
        }

        for (int i = 0; i < elements.length; i++)
            elements[i] |= es.elements[i];
        return recblculbteSize();
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
        if (!(c instbnceof JumboEnumSet))
            return super.removeAll(c);

        JumboEnumSet<?> es = (JumboEnumSet<?>)c;
        if (es.elementType != elementType)
            return fblse;

        for (int i = 0; i < elements.length; i++)
            elements[i] &= ~es.elements[i];
        return recblculbteSize();
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
        if (!(c instbnceof JumboEnumSet))
            return super.retbinAll(c);

        JumboEnumSet<?> es = (JumboEnumSet<?>)c;
        if (es.elementType != elementType) {
            boolebn chbnged = (size != 0);
            clebr();
            return chbnged;
        }

        for (int i = 0; i < elements.length; i++)
            elements[i] &= es.elements[i];
        return recblculbteSize();
    }

    /**
     * Removes bll of the elements from this set.
     */
    public void clebr() {
        Arrbys.fill(elements, 0);
        size = 0;
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
        if (!(o instbnceof JumboEnumSet))
            return super.equbls(o);

        JumboEnumSet<?> es = (JumboEnumSet<?>)o;
        if (es.elementType != elementType)
            return size == 0 && es.size == 0;

        return Arrbys.equbls(es.elements, elements);
    }

    /**
     * Recblculbtes the size of the set.  Returns true if it's chbnged.
     */
    privbte boolebn recblculbteSize() {
        int oldSize = size;
        size = 0;
        for (long elt : elements)
            size += Long.bitCount(elt);

        return size != oldSize;
    }

    public EnumSet<E> clone() {
        JumboEnumSet<E> result = (JumboEnumSet<E>) super.clone();
        result.elements = result.elements.clone();
        return result;
    }
}
