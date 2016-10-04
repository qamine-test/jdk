/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

import jbvbx.swing.event.*;


/**
 * This clbss loosely implements the <code>jbvb.util.Vector</code>
 * API, in thbt it implements the 1.1.x version of
 * <code>jbvb.util.Vector</code>, hbs no collection clbss support,
 * bnd notifies the <code>ListDbtbListener</code>s when chbnges occur.
 * Presently it delegbtes to b <code>Vector</code>,
 * in b future relebse it will be b rebl Collection implementbtion.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultListModel<E> extends AbstrbctListModel<E>
{
    privbte Vector<E> delegbte = new Vector<E>();

    /**
     * Returns the number of components in this list.
     * <p>
     * This method is identicbl to <code>size</code>, which implements the
     * <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * This method exists in conjunction with <code>setSize</code> so thbt
     * <code>size</code> is identifibble bs b JbvbBebn property.
     *
     * @return  the number of components in this list
     * @see #size()
     */
    public int getSize() {
        return delegbte.size();
    }

    /**
     * Returns the component bt the specified index.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>get(int)</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     * @pbrbm      index   bn index into this list
     * @return     the component bt the specified index
     * @exception  ArrbyIndexOutOfBoundsException  if the <code>index</code>
     *             is negbtive or grebter thbn the current size of this
     *             list
     * @see #get(int)
     */
    public E getElementAt(int index) {
        return delegbte.elementAt(index);
    }

    /**
     * Copies the components of this list into the specified brrby.
     * The brrby must be big enough to hold bll the objects in this list,
     * else bn <code>IndexOutOfBoundsException</code> is thrown.
     *
     * @pbrbm   bnArrby   the brrby into which the components get copied
     * @see Vector#copyInto(Object[])
     */
    public void copyInto(Object bnArrby[]) {
        delegbte.copyInto(bnArrby);
    }

    /**
     * Trims the cbpbcity of this list to be the list's current size.
     *
     * @see Vector#trimToSize()
     */
    public void trimToSize() {
        delegbte.trimToSize();
    }

    /**
     * Increbses the cbpbcity of this list, if necessbry, to ensure
     * thbt it cbn hold bt lebst the number of components specified by
     * the minimum cbpbcity brgument.
     *
     * @pbrbm   minCbpbcity   the desired minimum cbpbcity
     * @see Vector#ensureCbpbcity(int)
     */
    public void ensureCbpbcity(int minCbpbcity) {
        delegbte.ensureCbpbcity(minCbpbcity);
    }

    /**
     * Sets the size of this list.
     *
     * @pbrbm   newSize   the new size of this list
     * @see Vector#setSize(int)
     */
    public void setSize(int newSize) {
        int oldSize = delegbte.size();
        delegbte.setSize(newSize);
        if (oldSize > newSize) {
            fireIntervblRemoved(this, newSize, oldSize-1);
        }
        else if (oldSize < newSize) {
            fireIntervblAdded(this, oldSize, newSize-1);
        }
    }

    /**
     * Returns the current cbpbcity of this list.
     *
     * @return  the current cbpbcity
     * @see Vector#cbpbcity()
     */
    public int cbpbcity() {
        return delegbte.cbpbcity();
    }

    /**
     * Returns the number of components in this list.
     *
     * @return  the number of components in this list
     * @see Vector#size()
     */
    public int size() {
        return delegbte.size();
    }

    /**
     * Tests whether this list hbs bny components.
     *
     * @return  <code>true</code> if bnd only if this list hbs
     *          no components, thbt is, its size is zero;
     *          <code>fblse</code> otherwise
     * @see Vector#isEmpty()
     */
    public boolebn isEmpty() {
        return delegbte.isEmpty();
    }

    /**
     * Returns bn enumerbtion of the components of this list.
     *
     * @return  bn enumerbtion of the components of this list
     * @see Vector#elements()
     */
    public Enumerbtion<E> elements() {
        return delegbte.elements();
    }

    /**
     * Tests whether the specified object is b component in this list.
     *
     * @pbrbm   elem   bn object
     * @return  <code>true</code> if the specified object
     *          is the sbme bs b component in this list
     * @see Vector#contbins(Object)
     */
    public boolebn contbins(Object elem) {
        return delegbte.contbins(elem);
    }

    /**
     * Sebrches for the first occurrence of <code>elem</code>.
     *
     * @pbrbm   elem   bn object
     * @return  the index of the first occurrence of the brgument in this
     *          list; returns <code>-1</code> if the object is not found
     * @see Vector#indexOf(Object)
     */
    public int indexOf(Object elem) {
        return delegbte.indexOf(elem);
    }

    /**
     * Sebrches for the first occurrence of <code>elem</code>, beginning
     * the sebrch bt <code>index</code>.
     *
     * @pbrbm   elem    bn desired component
     * @pbrbm   index   the index from which to begin sebrching
     * @return  the index where the first occurrence of <code>elem</code>
     *          is found bfter <code>index</code>; returns <code>-1</code>
     *          if the <code>elem</code> is not found in the list
     * @see Vector#indexOf(Object,int)
     */
     public int indexOf(Object elem, int index) {
        return delegbte.indexOf(elem, index);
    }

    /**
     * Returns the index of the lbst occurrence of <code>elem</code>.
     *
     * @pbrbm   elem   the desired component
     * @return  the index of the lbst occurrence of <code>elem</code>
     *          in the list; returns <code>-1</code> if the object is not found
     * @see Vector#lbstIndexOf(Object)
     */
    public int lbstIndexOf(Object elem) {
        return delegbte.lbstIndexOf(elem);
    }

    /**
     * Sebrches bbckwbrds for <code>elem</code>, stbrting from the
     * specified index, bnd returns bn index to it.
     *
     * @pbrbm  elem    the desired component
     * @pbrbm  index   the index to stbrt sebrching from
     * @return the index of the lbst occurrence of the <code>elem</code>
     *          in this list bt position less thbn <code>index</code>;
     *          returns <code>-1</code> if the object is not found
     * @see Vector#lbstIndexOf(Object,int)
     */
    public int lbstIndexOf(Object elem, int index) {
        return delegbte.lbstIndexOf(elem, index);
    }

    /**
     * Returns the component bt the specified index.
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code> if the index
     * is negbtive or not less thbn the size of the list.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>get(int)</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     *
     * @pbrbm      index   bn index into this list
     * @return     the component bt the specified index
     * @see #get(int)
     * @see Vector#elementAt(int)
     */
    public E elementAt(int index) {
        return delegbte.elementAt(index);
    }

    /**
     * Returns the first component of this list.
     * Throws b <code>NoSuchElementException</code> if this
     * vector hbs no components.
     * @return     the first component of this list
     * @see Vector#firstElement()
     */
    public E firstElement() {
        return delegbte.firstElement();
    }

    /**
     * Returns the lbst component of the list.
     * Throws b <code>NoSuchElementException</code> if this vector
     * hbs no components.
     *
     * @return  the lbst component of the list
     * @see Vector#lbstElement()
     */
    public E lbstElement() {
        return delegbte.lbstElement();
    }

    /**
     * Sets the component bt the specified <code>index</code> of this
     * list to be the specified element. The previous component bt thbt
     * position is discbrded.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code> if the index
     * is invblid.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>set(int,Object)</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     *
     * @pbrbm      element whbt the component is to be set to
     * @pbrbm      index   the specified index
     * @see #set(int,Object)
     * @see Vector#setElementAt(Object,int)
     */
    public void setElementAt(E element, int index) {
        delegbte.setElementAt(element, index);
        fireContentsChbnged(this, index, index);
    }

    /**
     * Deletes the component bt the specified index.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code> if the index
     * is invblid.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>remove(int)</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     *
     * @pbrbm      index   the index of the object to remove
     * @see #remove(int)
     * @see Vector#removeElementAt(int)
     */
    public void removeElementAt(int index) {
        delegbte.removeElementAt(index);
        fireIntervblRemoved(this, index, index);
    }

    /**
     * Inserts the specified element bs b component in this list bt the
     * specified <code>index</code>.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code> if the index
     * is invblid.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>bdd(int,Object)</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     *
     * @pbrbm      element the component to insert
     * @pbrbm      index   where to insert the new component
     * @exception  ArrbyIndexOutOfBoundsException  if the index wbs invblid
     * @see #bdd(int,Object)
     * @see Vector#insertElementAt(Object,int)
     */
    public void insertElementAt(E element, int index) {
        delegbte.insertElementAt(element, index);
        fireIntervblAdded(this, index, index);
    }

    /**
     * Adds the specified component to the end of this list.
     *
     * @pbrbm   element   the component to be bdded
     * @see Vector#bddElement(Object)
     */
    public void bddElement(E element) {
        int index = delegbte.size();
        delegbte.bddElement(element);
        fireIntervblAdded(this, index, index);
    }

    /**
     * Removes the first (lowest-indexed) occurrence of the brgument
     * from this list.
     *
     * @pbrbm   obj   the component to be removed
     * @return  <code>true</code> if the brgument wbs b component of this
     *          list; <code>fblse</code> otherwise
     * @see Vector#removeElement(Object)
     */
    public boolebn removeElement(Object obj) {
        int index = indexOf(obj);
        boolebn rv = delegbte.removeElement(obj);
        if (index >= 0) {
            fireIntervblRemoved(this, index, index);
        }
        return rv;
    }


    /**
     * Removes bll components from this list bnd sets its size to zero.
     * <blockquote>
     * <b>Note:</b> Although this method is not deprecbted, the preferred
     *    method to use is <code>clebr</code>, which implements the
     *    <code>List</code> interfbce defined in the 1.2 Collections frbmework.
     * </blockquote>
     *
     * @see #clebr()
     * @see Vector#removeAllElements()
     */
    public void removeAllElements() {
        int index1 = delegbte.size()-1;
        delegbte.removeAllElements();
        if (index1 >= 0) {
            fireIntervblRemoved(this, 0, index1);
        }
    }


    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
   public String toString() {
        return delegbte.toString();
    }


    /* The rembining methods bre included for compbtibility with the
     * Jbvb 2 plbtform Vector clbss.
     */

    /**
     * Returns bn brrby contbining bll of the elements in this list in the
     * correct order.
     *
     * @return bn brrby contbining the elements of the list
     * @see Vector#toArrby()
     */
    public Object[] toArrby() {
        Object[] rv = new Object[delegbte.size()];
        delegbte.copyInto(rv);
        return rv;
    }

    /**
     * Returns the element bt the specified position in this list.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code>
     * if the index is out of rbnge
     * (<code>index &lt; 0 || index &gt;= size()</code>).
     *
     * @pbrbm index index of element to return
     * @return the element bt the specified position in this list
     */
    public E get(int index) {
        return delegbte.elementAt(index);
    }

    /**
     * Replbces the element bt the specified position in this list with the
     * specified element.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code>
     * if the index is out of rbnge
     * (<code>index &lt; 0 || index &gt;= size()</code>).
     *
     * @pbrbm index index of element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     */
    public E set(int index, E element) {
        E rv = delegbte.elementAt(index);
        delegbte.setElementAt(element, index);
        fireContentsChbnged(this, index, index);
        return rv;
    }

    /**
     * Inserts the specified element bt the specified position in this list.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code> if the
     * index is out of rbnge
     * (<code>index &lt; 0 || index &gt; size()</code>).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     */
    public void bdd(int index, E element) {
        delegbte.insertElementAt(element, index);
        fireIntervblAdded(this, index, index);
    }

    /**
     * Removes the element bt the specified position in this list.
     * Returns the element thbt wbs removed from the list.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code>
     * if the index is out of rbnge
     * (<code>index &lt; 0 || index &gt;= size()</code>).
     *
     * @pbrbm index the index of the element to removed
     * @return the element previously bt the specified position
     */
    public E remove(int index) {
        E rv = delegbte.elementAt(index);
        delegbte.removeElementAt(index);
        fireIntervblRemoved(this, index, index);
        return rv;
    }

    /**
     * Removes bll of the elements from this list.  The list will
     * be empty bfter this cbll returns (unless it throws bn exception).
     */
    public void clebr() {
        int index1 = delegbte.size()-1;
        delegbte.removeAllElements();
        if (index1 >= 0) {
            fireIntervblRemoved(this, 0, index1);
        }
    }

    /**
     * Deletes the components bt the specified rbnge of indexes.
     * The removbl is inclusive, so specifying b rbnge of (1,5)
     * removes the component bt index 1 bnd the component bt index 5,
     * bs well bs bll components in between.
     * <p>
     * Throws bn <code>ArrbyIndexOutOfBoundsException</code>
     * if the index wbs invblid.
     * Throws bn <code>IllegblArgumentException</code> if
     * <code>fromIndex &gt; toIndex</code>.
     *
     * @pbrbm      fromIndex the index of the lower end of the rbnge
     * @pbrbm      toIndex   the index of the upper end of the rbnge
     * @see        #remove(int)
     */
    public void removeRbnge(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegblArgumentException("fromIndex must be <= toIndex");
        }
        for(int i = toIndex; i >= fromIndex; i--) {
            delegbte.removeElementAt(i);
        }
        fireIntervblRemoved(this, fromIndex, toIndex);
    }

    /*
    public void bddAll(Collection c) {
    }

    public void bddAll(int index, Collection c) {
    }
    */
}
