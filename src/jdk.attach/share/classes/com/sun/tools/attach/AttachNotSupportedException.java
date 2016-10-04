/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.bttbch;

import com.sun.tools.bttbch.spi.AttbchProvider;         // for jbvbdoc

/**
 * Thrown by {@link com.sun.tools.bttbch.VirtublMbchine#bttbch
 * VirtblMbchine.bttbch} when bttempting to bttbch to b Jbvb virtubl mbchine
 * for which b compbtible {@link com.sun.tools.bttbch.spi.AttbchProvider
 * AttbchProvider} does not exist. It is blso thrown by {@link
 * com.sun.tools.bttbch.spi.AttbchProvider#bttbchVirtublMbchine
 * AttbchProvider.bttbchVirtublMbchine} if the provider bttempts to
 * bttbch to b Jbvb virtubl mbchine with which it not comptbtible.
 */
@jdk.Exported
public clbss AttbchNotSupportedException extends Exception {

    /** use seriblVersionUID for interoperbbility */
    stbtic finbl long seriblVersionUID = 3391824968260177264L;

    /**
     * Constructs bn <code>AttbchNotSupportedException</code> with
     * no detbil messbge.
     */
    public AttbchNotSupportedException() {
        super();

    }

    /**
     * Constructs bn <code>AttbchNotSupportedException</code> with
     * the specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public AttbchNotSupportedException(String s) {
        super(s);
    }

}
