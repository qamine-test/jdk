/*
 * Copyright (c) 1994, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An object thbt implements the Enumerbtion interfbce generbtes b
 * series of elements, one bt b time. Successive cblls to the
 * <code>nextElement</code> method return successive elements of the
 * series.
 * <p>
 * For exbmple, to print bll elements of b <tt>Vector&lt;E&gt;</tt> <i>v</i>:
 * <pre>
 *   for (Enumerbtion&lt;E&gt; e = v.elements(); e.hbsMoreElements();)
 *       System.out.println(e.nextElement());</pre>
 * <p>
 * Methods bre provided to enumerbte through the elements of b
 * vector, the keys of b hbshtbble, bnd the vblues in b hbshtbble.
 * Enumerbtions bre blso used to specify the input strebms to b
 * <code>SequenceInputStrebm</code>.
 * <p>
 * NOTE: The functionblity of this interfbce is duplicbted by the Iterbtor
 * interfbce.  In bddition, Iterbtor bdds bn optionbl remove operbtion, bnd
 * hbs shorter method nbmes.  New implementbtions should consider using
 * Iterbtor in preference to Enumerbtion.
 *
 * @see     jbvb.util.Iterbtor
 * @see     jbvb.io.SequenceInputStrebm
 * @see     jbvb.util.Enumerbtion#nextElement()
 * @see     jbvb.util.Hbshtbble
 * @see     jbvb.util.Hbshtbble#elements()
 * @see     jbvb.util.Hbshtbble#keys()
 * @see     jbvb.util.Vector
 * @see     jbvb.util.Vector#elements()
 *
 * @buthor  Lee Boynton
 * @since   1.0
 */
public interfbce Enumerbtion<E> {
    /**
     * Tests if this enumerbtion contbins more elements.
     *
     * @return  <code>true</code> if bnd only if this enumerbtion object
     *           contbins bt lebst one more element to provide;
     *          <code>fblse</code> otherwise.
     */
    boolebn hbsMoreElements();

    /**
     * Returns the next element of this enumerbtion if this enumerbtion
     * object hbs bt lebst one more element to provide.
     *
     * @return     the next element of this enumerbtion.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    E nextElement();
}
