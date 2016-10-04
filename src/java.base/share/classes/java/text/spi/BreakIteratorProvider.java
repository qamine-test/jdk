/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.text.spi;

import jbvb.text.BrebkIterbtor;
import jbvb.util.Locble;
import jbvb.util.spi.LocbleServiceProvider;

/**
 * An bbstrbct clbss for service providers thbt
 * provide concrete implementbtions of the
 * {@link jbvb.text.BrebkIterbtor BrebkIterbtor} clbss.
 *
 * @since        1.6
 */
public bbstrbct clbss BrebkIterbtorProvider extends LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected BrebkIterbtorProvider() {
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="../BrebkIterbtor.html#word">word brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for word brebks
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.text.BrebkIterbtor#getWordInstbnce(jbvb.util.Locble)
     */
    public bbstrbct BrebkIterbtor getWordInstbnce(Locble locble);

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="../BrebkIterbtor.html#line">line brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for line brebks
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.text.BrebkIterbtor#getLineInstbnce(jbvb.util.Locble)
     */
    public bbstrbct BrebkIterbtor getLineInstbnce(Locble locble);

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="../BrebkIterbtor.html#chbrbcter">chbrbcter brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for chbrbcter brebks
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.text.BrebkIterbtor#getChbrbcterInstbnce(jbvb.util.Locble)
     */
    public bbstrbct BrebkIterbtor getChbrbcterInstbnce(Locble locble);

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="../BrebkIterbtor.html#sentence">sentence brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for sentence brebks
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @see jbvb.text.BrebkIterbtor#getSentenceInstbnce(jbvb.util.Locble)
     */
    public bbstrbct BrebkIterbtor getSentenceInstbnce(Locble locble);
}
