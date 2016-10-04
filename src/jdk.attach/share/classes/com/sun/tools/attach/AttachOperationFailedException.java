/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;

/**
 * Exception type to signbl thbt bn bttbch operbtion fbiled in the tbrget VM.
 *
 * <p> This exception cbn be thrown by the vbrious operbtions of
 * {@link com.sun.tools.bttbch.VirtublMbchine} when the operbtion
 * fbils in the tbrget VM. If there is b communicbtion error,
 * b regulbr IOException will be thrown.
 *
 * @since 1.9
 */
@jdk.Exported
public clbss AttbchOperbtionFbiledException extends IOException {

    privbte stbtic finbl long seriblVersionUID = 2140308168167478043L;

    /**
     * Constructs bn <code>AttbchOperbtionFbiledException</code> with
     * the specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public AttbchOperbtionFbiledException(String messbge) {
        super(messbge);
    }
}
