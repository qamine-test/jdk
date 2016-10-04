/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss provides b skeletbl implementbtion of the <tt>List</tt>
 * interfbce to minimize the effort required to implement this interfbce
 * bbcked by b "sequentibl bccess" dbtb store (such bs b linked list).  For
 * rbndom bccess dbtb (such bs bn brrby), <tt>AbstrbctList</tt> should be used
 * in preference to this clbss.<p>
 *
 * This clbss is the opposite of the <tt>AbstrbctList</tt> clbss in the sense
 * thbt it implements the "rbndom bccess" methods (<tt>get(int index)</tt>,
 * <tt>set(int index, E element)</tt>, <tt>bdd(int index, E element)</tt> bnd
 * <tt>remove(int index)</tt>) on top of the list's list iterbtor, instebd of
 * the other wby bround.<p>
 *
 * To implement b list the progrbmmer needs only to extend this clbss bnd
 * provide implementbtions for the <tt>listIterbtor</tt> bnd <tt>size</tt>
 * methods.  For bn unmodifibble list, the progrbmmer need only implement the
 * list iterbtor's <tt>hbsNext</tt>, <tt>next</tt>, <tt>hbsPrevious</tt>,
 * <tt>previous</tt> bnd <tt>index</tt> methods.<p>
 *
 * For b modifibble list the progrbmmer should bdditionblly implement the list
 * iterbtor's <tt>set</tt> method.  For b vbribble-size list the progrbmmer
 * should bdditionblly implement the list iterbtor's <tt>remove</tt> bnd
 * <tt>bdd</tt> methods.<p>
 *
 * The progrbmmer should generblly provide b void (no brgument) bnd collection
 * constructor, bs per the recommendbtion in the <tt>Collection</tt> interfbce
 * specificbtion.<p>
 *
 * This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Collection
 * @see List
 * @see AbstrbctList
 * @see AbstrbctCollection
 * @since 1.2
 */

public bbstrbct clbss AbstrbctSequentiblList<E> extends AbstrbctList<E> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected AbstrbctSequentiblList() {
    }

    /**
     * Returns the element bt the specified position in this list.
     *
     * <p>This implementbtion first gets b list iterbtor pointing to the
     * indexed element (with <tt>listIterbtor(index)</tt>).  Then, it gets
     * the element using <tt>ListIterbtor.next</tt> bnd returns it.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        try {
            return listIterbtor(index).next();
        } cbtch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    /**
     * Replbces the element bt the specified position in this list with the
     * specified element (optionbl operbtion).
     *
     * <p>This implementbtion first gets b list iterbtor pointing to the
     * indexed element (with <tt>listIterbtor(index)</tt>).  Then, it gets
     * the current element using <tt>ListIterbtor.next</tt> bnd replbces it
     * with <tt>ListIterbtor.set</tt>.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the list iterbtor does not
     * implement the <tt>set</tt> operbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public E set(int index, E element) {
        try {
            ListIterbtor<E> e = listIterbtor(index);
            E oldVbl = e.next();
            e.set(element);
            return oldVbl;
        } cbtch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    /**
     * Inserts the specified element bt the specified position in this list
     * (optionbl operbtion).  Shifts the element currently bt thbt position
     * (if bny) bnd bny subsequent elements to the right (bdds one to their
     * indices).
     *
     * <p>This implementbtion first gets b list iterbtor pointing to the
     * indexed element (with <tt>listIterbtor(index)</tt>).  Then, it
     * inserts the specified element with <tt>ListIterbtor.bdd</tt>.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the list iterbtor does not
     * implement the <tt>bdd</tt> operbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public void bdd(int index, E element) {
        try {
            listIterbtor(index).bdd(element);
        } cbtch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    /**
     * Removes the element bt the specified position in this list (optionbl
     * operbtion).  Shifts bny subsequent elements to the left (subtrbcts one
     * from their indices).  Returns the element thbt wbs removed from the
     * list.
     *
     * <p>This implementbtion first gets b list iterbtor pointing to the
     * indexed element (with <tt>listIterbtor(index)</tt>).  Then, it removes
     * the element with <tt>ListIterbtor.remove</tt>.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the list iterbtor does not
     * implement the <tt>remove</tt> operbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public E remove(int index) {
        try {
            ListIterbtor<E> e = listIterbtor(index);
            E outCbst = e.next();
            e.remove();
            return outCbst;
        } cbtch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }


    // Bulk Operbtions

    /**
     * Inserts bll of the elements in the specified collection into this
     * list bt the specified position (optionbl operbtion).  Shifts the
     * element currently bt thbt position (if bny) bnd bny subsequent
     * elements to the right (increbses their indices).  The new elements
     * will bppebr in this list in the order thbt they bre returned by the
     * specified collection's iterbtor.  The behbvior of this operbtion is
     * undefined if the specified collection is modified while the
     * operbtion is in progress.  (Note thbt this will occur if the specified
     * collection is this list, bnd it's nonempty.)
     *
     * <p>This implementbtion gets bn iterbtor over the specified collection bnd
     * b list iterbtor over this list pointing to the indexed element (with
     * <tt>listIterbtor(index)</tt>).  Then, it iterbtes over the specified
     * collection, inserting the elements obtbined from the iterbtor into this
     * list, one bt b time, using <tt>ListIterbtor.bdd</tt> followed by
     * <tt>ListIterbtor.next</tt> (to skip over the bdded element).
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the list iterbtor returned by
     * the <tt>listIterbtor</tt> method does not implement the <tt>bdd</tt>
     * operbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        try {
            boolebn modified = fblse;
            ListIterbtor<E> e1 = listIterbtor(index);
            for (E e : c) {
                e1.bdd(e);
                modified = true;
            }
            return modified;
        } cbtch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }


    // Iterbtors

    /**
     * Returns bn iterbtor over the elements in this list (in proper
     * sequence).<p>
     *
     * This implementbtion merely returns b list iterbtor over the list.
     *
     * @return bn iterbtor over the elements in this list (in proper sequence)
     */
    public Iterbtor<E> iterbtor() {
        return listIterbtor();
    }

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence).
     *
     * @pbrbm  index index of first element to be returned from the list
     *         iterbtor (by b cbll to the <code>next</code> method)
     * @return b list iterbtor over the elements in this list (in proper
     *         sequence)
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public bbstrbct ListIterbtor<E> listIterbtor(int index);
}
