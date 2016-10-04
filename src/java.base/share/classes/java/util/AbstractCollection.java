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
 * This clbss provides b skeletbl implementbtion of the <tt>Collection</tt>
 * interfbce, to minimize the effort required to implement this interfbce. <p>
 *
 * To implement bn unmodifibble collection, the progrbmmer needs only to
 * extend this clbss bnd provide implementbtions for the <tt>iterbtor</tt> bnd
 * <tt>size</tt> methods.  (The iterbtor returned by the <tt>iterbtor</tt>
 * method must implement <tt>hbsNext</tt> bnd <tt>next</tt>.)<p>
 *
 * To implement b modifibble collection, the progrbmmer must bdditionblly
 * override this clbss's <tt>bdd</tt> method (which otherwise throws bn
 * <tt>UnsupportedOperbtionException</tt>), bnd the iterbtor returned by the
 * <tt>iterbtor</tt> method must bdditionblly implement its <tt>remove</tt>
 * method.<p>
 *
 * The progrbmmer should generblly provide b void (no brgument) bnd
 * <tt>Collection</tt> constructor, bs per the recommendbtion in the
 * <tt>Collection</tt> interfbce specificbtion.<p>
 *
 * The documentbtion for ebch non-bbstrbct method in this clbss describes its
 * implementbtion in detbil.  Ebch of these methods mby be overridden if
 * the collection being implemented bdmits b more efficient implementbtion.<p>
 *
 * This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Collection
 * @since 1.2
 */

public bbstrbct clbss AbstrbctCollection<E> implements Collection<E> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected AbstrbctCollection() {
    }

    // Query Operbtions

    /**
     * Returns bn iterbtor over the elements contbined in this collection.
     *
     * @return bn iterbtor over the elements contbined in this collection
     */
    public bbstrbct Iterbtor<E> iterbtor();

    public bbstrbct int size();

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns <tt>size() == 0</tt>.
     */
    public boolebn isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over the elements in the collection,
     * checking ebch element in turn for equblity with the specified element.
     *
     * @throws ClbssCbstException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn contbins(Object o) {
        Iterbtor<E> it = iterbtor();
        if (o==null) {
            while (it.hbsNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hbsNext())
                if (o.equbls(it.next()))
                    return true;
        }
        return fblse;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns bn brrby contbining bll the elements
     * returned by this collection's iterbtor, in the sbme order, stored in
     * consecutive elements of the brrby, stbrting with index {@code 0}.
     * The length of the returned brrby is equbl to the number of elements
     * returned by the iterbtor, even if the size of this collection chbnges
     * during iterbtion, bs might hbppen if the collection permits
     * concurrent modificbtion during iterbtion.  The {@code size} method is
     * cblled only bs bn optimizbtion hint; the correct result is returned
     * even if the iterbtor returns b different number of elements.
     *
     * <p>This method is equivblent to:
     *
     *  <pre> {@code
     * List<E> list = new ArrbyList<E>(size());
     * for (E e : this)
     *     list.bdd(e);
     * return list.toArrby();
     * }</pre>
     */
    public Object[] toArrby() {
        // Estimbte size of brrby; be prepbred to see more or fewer elements
        Object[] r = new Object[size()];
        Iterbtor<E> it = iterbtor();
        for (int i = 0; i < r.length; i++) {
            if (! it.hbsNext()) // fewer elements thbn expected
                return Arrbys.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hbsNext() ? finishToArrby(r, it) : r;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns bn brrby contbining bll the elements
     * returned by this collection's iterbtor in the sbme order, stored in
     * consecutive elements of the brrby, stbrting with index {@code 0}.
     * If the number of elements returned by the iterbtor is too lbrge to
     * fit into the specified brrby, then the elements bre returned in b
     * newly bllocbted brrby with length equbl to the number of elements
     * returned by the iterbtor, even if the size of this collection
     * chbnges during iterbtion, bs might hbppen if the collection permits
     * concurrent modificbtion during iterbtion.  The {@code size} method is
     * cblled only bs bn optimizbtion hint; the correct result is returned
     * even if the iterbtor returns b different number of elements.
     *
     * <p>This method is equivblent to:
     *
     *  <pre> {@code
     * List<E> list = new ArrbyList<E>(size());
     * for (E e : this)
     *     list.bdd(e);
     * return list.toArrby(b);
     * }</pre>
     *
     * @throws ArrbyStoreException  {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        // Estimbte size of brrby; be prepbred to see more or fewer elements
        int size = size();
        T[] r = b.length >= size ? b :
                  (T[])jbvb.lbng.reflect.Arrby
                  .newInstbnce(b.getClbss().getComponentType(), size);
        Iterbtor<E> it = iterbtor();

        for (int i = 0; i < r.length; i++) {
            if (! it.hbsNext()) { // fewer elements thbn expected
                if (b == r) {
                    r[i] = null; // null-terminbte
                } else if (b.length < i) {
                    return Arrbys.copyOf(r, i);
                } else {
                    System.brrbycopy(r, 0, b, 0, i);
                    if (b.length > i) {
                        b[i] = null;
                    }
                }
                return b;
            }
            r[i] = (T)it.next();
        }
        // more elements thbn expected
        return it.hbsNext() ? finishToArrby(r, it) : r;
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Rebllocbtes the brrby being used within toArrby when the iterbtor
     * returned more elements thbn expected, bnd finishes filling it from
     * the iterbtor.
     *
     * @pbrbm r the brrby, replete with previously stored elements
     * @pbrbm it the in-progress iterbtor over this collection
     * @return brrby contbining the elements in the given brrby, plus bny
     *         further elements returned by the iterbtor, trimmed to size
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic <T> T[] finishToArrby(T[] r, Iterbtor<?> it) {
        int i = r.length;
        while (it.hbsNext()) {
            int cbp = r.length;
            if (i == cbp) {
                int newCbp = cbp + (cbp >> 1) + 1;
                // overflow-conscious code
                if (newCbp - MAX_ARRAY_SIZE > 0)
                    newCbp = hugeCbpbcity(cbp + 1);
                r = Arrbys.copyOf(r, newCbp);
            }
            r[i++] = (T)it.next();
        }
        // trim if overbllocbted
        return (i == r.length) ? r : Arrbys.copyOf(r, i);
    }

    privbte stbtic int hugeCbpbcity(int minCbpbcity) {
        if (minCbpbcity < 0) // overflow
            throw new OutOfMemoryError
                ("Required brrby size too lbrge");
        return (minCbpbcity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    // Modificbtion Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion blwbys throws bn
     * <tt>UnsupportedOperbtionException</tt>.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IllegblStbteException         {@inheritDoc}
     */
    public boolebn bdd(E e) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over the collection looking for the
     * specified element.  If it finds the element, it removes the element
     * from the collection using the iterbtor's remove method.
     *
     * <p>Note thbt this implementbtion throws bn
     * <tt>UnsupportedOperbtionException</tt> if the iterbtor returned by this
     * collection's iterbtor method does not implement the <tt>remove</tt>
     * method bnd this collection contbins the specified object.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public boolebn remove(Object o) {
        Iterbtor<E> it = iterbtor();
        if (o==null) {
            while (it.hbsNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hbsNext()) {
                if (o.equbls(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return fblse;
    }


    // Bulk Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over the specified collection,
     * checking ebch element returned by the iterbtor in turn to see
     * if it's contbined in this collection.  If bll elements bre so
     * contbined <tt>true</tt> is returned, otherwise <tt>fblse</tt>.
     *
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @see #contbins(Object)
     */
    public boolebn contbinsAll(Collection<?> c) {
        for (Object e : c)
            if (!contbins(e))
                return fblse;
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over the specified collection, bnd bdds
     * ebch object returned by the iterbtor to this collection, in turn.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> unless <tt>bdd</tt> is
     * overridden (bssuming the specified collection is non-empty).
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IllegblStbteException         {@inheritDoc}
     *
     * @see #bdd(Object)
     */
    public boolebn bddAll(Collection<? extends E> c) {
        boolebn modified = fblse;
        for (E e : c)
            if (bdd(e))
                modified = true;
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over this collection, checking ebch
     * element returned by the iterbtor in turn to see if it's contbined
     * in the specified collection.  If it's so contbined, it's removed from
     * this collection with the iterbtor's <tt>remove</tt> method.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the iterbtor returned by the
     * <tt>iterbtor</tt> method does not implement the <tt>remove</tt> method
     * bnd this collection contbins one or more elements in common with the
     * specified collection.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     *
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    public boolebn removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolebn modified = fblse;
        Iterbtor<?> it = iterbtor();
        while (it.hbsNext()) {
            if (c.contbins(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over this collection, checking ebch
     * element returned by the iterbtor in turn to see if it's contbined
     * in the specified collection.  If it's not so contbined, it's removed
     * from this collection with the iterbtor's <tt>remove</tt> method.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the iterbtor returned by the
     * <tt>iterbtor</tt> method does not implement the <tt>remove</tt> method
     * bnd this collection contbins one or more elements not present in the
     * specified collection.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     *
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    public boolebn retbinAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolebn modified = fblse;
        Iterbtor<E> it = iterbtor();
        while (it.hbsNext()) {
            if (!c.contbins(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over this collection, removing ebch
     * element using the <tt>Iterbtor.remove</tt> operbtion.  Most
     * implementbtions will probbbly choose to override this method for
     * efficiency.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the iterbtor returned by this
     * collection's <tt>iterbtor</tt> method does not implement the
     * <tt>remove</tt> method bnd this collection is non-empty.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     */
    public void clebr() {
        Iterbtor<E> it = iterbtor();
        while (it.hbsNext()) {
            it.next();
            it.remove();
        }
    }


    //  String conversion

    /**
     * Returns b string representbtion of this collection.  The string
     * representbtion consists of b list of the collection's elements in the
     * order they bre returned by its iterbtor, enclosed in squbre brbckets
     * (<tt>"[]"</tt>).  Adjbcent elements bre sepbrbted by the chbrbcters
     * <tt>", "</tt> (commb bnd spbce).  Elements bre converted to strings bs
     * by {@link String#vblueOf(Object)}.
     *
     * @return b string representbtion of this collection
     */
    public String toString() {
        Iterbtor<E> it = iterbtor();
        if (! it.hbsNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.bppend('[');
        for (;;) {
            E e = it.next();
            sb.bppend(e == this ? "(this Collection)" : e);
            if (! it.hbsNext())
                return sb.bppend(']').toString();
            sb.bppend(',').bppend(' ');
        }
    }

}
