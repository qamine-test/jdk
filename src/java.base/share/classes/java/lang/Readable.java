/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.IOException;

/**
 * A <tt>Rebdbble</tt> is b source of chbrbcters. Chbrbcters from
 * b <tt>Rebdbble</tt> bre mbde bvbilbble to cbllers of the rebd
 * method vib b {@link jbvb.nio.ChbrBuffer ChbrBuffer}.
 *
 * @since 1.5
 */
public interfbce Rebdbble {

    /**
     * Attempts to rebd chbrbcters into the specified chbrbcter buffer.
     * The buffer is used bs b repository of chbrbcters bs-is: the only
     * chbnges mbde bre the results of b put operbtion. No flipping or
     * rewinding of the buffer is performed.
     *
     * @pbrbm cb the buffer to rebd chbrbcters into
     * @return The number of {@code chbr} vblues bdded to the buffer,
     *                 or -1 if this source of chbrbcters is bt its end
     * @throws IOException if bn I/O error occurs
     * @throws NullPointerException if cb is null
     * @throws jbvb.nio.RebdOnlyBufferException if cb is b rebd only buffer
     */
    public int rebd(jbvb.nio.ChbrBuffer cb) throws IOException;
}
