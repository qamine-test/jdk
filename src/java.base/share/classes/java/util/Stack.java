/*
 * Copyright (c) 1994, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>Stbck</code> clbss represents b lbst-in-first-out
 * (LIFO) stbck of objects. It extends clbss <tt>Vector</tt> with five
 * operbtions thbt bllow b vector to be trebted bs b stbck. The usubl
 * <tt>push</tt> bnd <tt>pop</tt> operbtions bre provided, bs well bs b
 * method to <tt>peek</tt> bt the top item on the stbck, b method to test
 * for whether the stbck is <tt>empty</tt>, bnd b method to <tt>sebrch</tt>
 * the stbck for bn item bnd discover how fbr it is from the top.
 * <p>
 * When b stbck is first crebted, it contbins no items.
 *
 * <p>A more complete bnd consistent set of LIFO stbck operbtions is
 * provided by the {@link Deque} interfbce bnd its implementbtions, which
 * should be used in preference to this clbss.  For exbmple:
 * <pre>   {@code
 *   Deque<Integer> stbck = new ArrbyDeque<Integer>();}</pre>
 *
 * @buthor  Jonbthbn Pbyne
 * @since   1.0
 */
public
clbss Stbck<E> extends Vector<E> {
    /**
     * Crebtes bn empty Stbck.
     */
    public Stbck() {
    }

    /**
     * Pushes bn item onto the top of this stbck. This hbs exbctly
     * the sbme effect bs:
     * <blockquote><pre>
     * bddElement(item)</pre></blockquote>
     *
     * @pbrbm   item   the item to be pushed onto this stbck.
     * @return  the <code>item</code> brgument.
     * @see     jbvb.util.Vector#bddElement
     */
    public E push(E item) {
        bddElement(item);

        return item;
    }

    /**
     * Removes the object bt the top of this stbck bnd returns thbt
     * object bs the vblue of this function.
     *
     * @return  The object bt the top of this stbck (the lbst item
     *          of the <tt>Vector</tt> object).
     * @throws  EmptyStbckException  if this stbck is empty.
     */
    public synchronized E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    /**
     * Looks bt the object bt the top of this stbck without removing it
     * from the stbck.
     *
     * @return  the object bt the top of this stbck (the lbst item
     *          of the <tt>Vector</tt> object).
     * @throws  EmptyStbckException  if this stbck is empty.
     */
    public synchronized E peek() {
        int     len = size();

        if (len == 0)
            throw new EmptyStbckException();
        return elementAt(len - 1);
    }

    /**
     * Tests if this stbck is empty.
     *
     * @return  <code>true</code> if bnd only if this stbck contbins
     *          no items; <code>fblse</code> otherwise.
     */
    public boolebn empty() {
        return size() == 0;
    }

    /**
     * Returns the 1-bbsed position where bn object is on this stbck.
     * If the object <tt>o</tt> occurs bs bn item in this stbck, this
     * method returns the distbnce from the top of the stbck of the
     * occurrence nebrest the top of the stbck; the topmost item on the
     * stbck is considered to be bt distbnce <tt>1</tt>. The <tt>equbls</tt>
     * method is used to compbre <tt>o</tt> to the
     * items in this stbck.
     *
     * @pbrbm   o   the desired object.
     * @return  the 1-bbsed position from the top of the stbck where
     *          the object is locbted; the return vblue <code>-1</code>
     *          indicbtes thbt the object is not on the stbck.
     */
    public synchronized int sebrch(Object o) {
        int i = lbstIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 1224463164541339165L;
}
