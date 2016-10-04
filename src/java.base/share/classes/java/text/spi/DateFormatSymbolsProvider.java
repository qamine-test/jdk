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

import jbvb.text.DbteFormbtSymbols;
import jbvb.util.Locble;
import jbvb.util.spi.LocbleServiceProvider;

/**
 * An bbstrbct clbss for service providers thbt
 * provide instbnces of the
 * {@link jbvb.text.DbteFormbtSymbols DbteFormbtSymbols} clbss.
 *
 * @since        1.6
 */
public bbstrbct clbss DbteFormbtSymbolsProvider extends LocbleServiceProvider {

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected DbteFormbtSymbolsProvider() {
    }

    /**
     * Returns b new <code>DbteFormbtSymbols</code> instbnce for the
     * specified locble.
     *
     * @pbrbm locble the desired locble
     * @exception NullPointerException if <code>locble</code> is null
     * @exception IllegblArgumentException if <code>locble</code> isn't
     *     one of the locbles returned from
     *     {@link jbvb.util.spi.LocbleServiceProvider#getAvbilbbleLocbles()
     *     getAvbilbbleLocbles()}.
     * @return b <code>DbteFormbtSymbols</code> instbnce.
     * @see jbvb.text.DbteFormbtSymbols#getInstbnce(jbvb.util.Locble)
     */
    public bbstrbct DbteFormbtSymbols getInstbnce(Locble locble);
}
