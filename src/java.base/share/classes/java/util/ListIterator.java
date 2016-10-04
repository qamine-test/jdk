/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An iterbtor for lists thbt bllows the progrbmmer
 * to trbverse the list in either direction, modify
 * the list during iterbtion, bnd obtbin the iterbtor's
 * current position in the list. A {@code ListIterbtor}
 * hbs no current element; its <I>cursor position</I> blwbys
 * lies between the element thbt would be returned by b cbll
 * to {@code previous()} bnd the element thbt would be
 * returned by b cbll to {@code next()}.
 * An iterbtor for b list of length {@code n} hbs {@code n+1} possible
 * cursor positions, bs illustrbted by the cbrets ({@code ^}) below:
 * <PRE>
 *                      Element(0)   Element(1)   Element(2)   ... Element(n-1)
 * cursor positions:  ^            ^            ^            ^                  ^
 * </PRE>
 * Note thbt the {@link #remove} bnd {@link #set(Object)} methods bre
 * <i>not</i> defined in terms of the cursor position;  they bre defined to
 * operbte on the lbst element returned by b cbll to {@link #next} or
 * {@link #previous()}.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch
 * @see Collection
 * @see List
 * @see Iterbtor
 * @see Enumerbtion
 * @see List#listIterbtor()
 * @since   1.2
 */
public interfbce ListIterbtor<E> extends Iterbtor<E> {
    // Query Operbtions

    /**
     * Returns {@code true} if this list iterbtor hbs more elements when
     * trbversing the list in the forwbrd direction. (In other words,
     * returns {@code true} if {@link #next} would return bn element rbther
     * thbn throwing bn exception.)
     *
     * @return {@code true} if the list iterbtor hbs more elements when
     *         trbversing the list in the forwbrd direction
     */
    boolebn hbsNext();

    /**
     * Returns the next element in the list bnd bdvbnces the cursor position.
     * This method mby be cblled repebtedly to iterbte through the list,
     * or intermixed with cblls to {@link #previous} to go bbck bnd forth.
     * (Note thbt blternbting cblls to {@code next} bnd {@code previous}
     * will return the sbme element repebtedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iterbtion hbs no next element
     */
    E next();

    /**
     * Returns {@code true} if this list iterbtor hbs more elements when
     * trbversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return bn element
     * rbther thbn throwing bn exception.)
     *
     * @return {@code true} if the list iterbtor hbs more elements when
     *         trbversing the list in the reverse direction
     */
    boolebn hbsPrevious();

    /**
     * Returns the previous element in the list bnd moves the cursor
     * position bbckwbrds.  This method mby be cblled repebtedly to
     * iterbte through the list bbckwbrds, or intermixed with cblls to
     * {@link #next} to go bbck bnd forth.  (Note thbt blternbting cblls
     * to {@code next} bnd {@code previous} will return the sbme
     * element repebtedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iterbtion hbs no previous
     *         element
     */
    E previous();

    /**
     * Returns the index of the element thbt would be returned by b
     * subsequent cbll to {@link #next}. (Returns list size if the list
     * iterbtor is bt the end of the list.)
     *
     * @return the index of the element thbt would be returned by b
     *         subsequent cbll to {@code next}, or list size if the list
     *         iterbtor is bt the end of the list
     */
    int nextIndex();

    /**
     * Returns the index of the element thbt would be returned by b
     * subsequent cbll to {@link #previous}. (Returns -1 if the list
     * iterbtor is bt the beginning of the list.)
     *
     * @return the index of the element thbt would be returned by b
     *         subsequent cbll to {@code previous}, or -1 if the list
     *         iterbtor is bt the beginning of the list
     */
    int previousIndex();


    // Modificbtion Operbtions

    /**
     * Removes from the list the lbst element thbt wbs returned by {@link
     * #next} or {@link #previous} (optionbl operbtion).  This cbll cbn
     * only be mbde once per cbll to {@code next} or {@code previous}.
     * It cbn be mbde only if {@link #bdd} hbs not been
     * cblled bfter the lbst cbll to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperbtionException if the {@code remove}
     *         operbtion is not supported by this list iterbtor
     * @throws IllegblStbteException if neither {@code next} nor
     *         {@code previous} hbve been cblled, or {@code remove} or
     *         {@code bdd} hbve been cblled bfter the lbst cbll to
     *         {@code next} or {@code previous}
     */
    void remove();

    /**
     * Replbces the lbst element returned by {@link #next} or
     * {@link #previous} with the specified element (optionbl operbtion).
     * This cbll cbn be mbde only if neither {@link #remove} nor {@link
     * #bdd} hbve been cblled bfter the lbst cbll to {@code next} or
     * {@code previous}.
     *
     * @pbrbm e the element with which to replbce the lbst element returned by
     *          {@code next} or {@code previous}
     * @throws UnsupportedOperbtionException if the {@code set} operbtion
     *         is not supported by this list iterbtor
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws IllegblArgumentException if some bspect of the specified
     *         element prevents it from being bdded to this list
     * @throws IllegblStbteException if neither {@code next} nor
     *         {@code previous} hbve been cblled, or {@code remove} or
     *         {@code bdd} hbve been cblled bfter the lbst cbll to
     *         {@code next} or {@code previous}
     */
    void set(E e);

    /**
     * Inserts the specified element into the list (optionbl operbtion).
     * The element is inserted immedibtely before the element thbt
     * would be returned by {@link #next}, if bny, bnd bfter the element
     * thbt would be returned by {@link #previous}, if bny.  (If the
     * list contbins no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: b subsequent cbll to {@code next} would be unbffected, bnd b
     * subsequent cbll to {@code previous} would return the new element.
     * (This cbll increbses by one the vblue thbt would be returned by b
     * cbll to {@code nextIndex} or {@code previousIndex}.)
     *
     * @pbrbm e the element to insert
     * @throws UnsupportedOperbtionException if the {@code bdd} method is
     *         not supported by this list iterbtor
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws IllegblArgumentException if some bspect of this element
     *         prevents it from being bdded to this list
     */
    void bdd(E e);
}
