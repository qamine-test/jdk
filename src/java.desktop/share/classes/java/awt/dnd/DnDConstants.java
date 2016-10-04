/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * This clbss contbins constbnt vblues representing
 * the type of bction(s) to be performed by b Drbg bnd Drop operbtion.
 * @since 1.2
 */
public finbl clbss DnDConstbnts {

    privbte DnDConstbnts() {} // define null privbte constructor.

    /**
     * An <code>int</code> representing no bction.
     */
    @Nbtive public stbtic finbl int ACTION_NONE         = 0x0;

    /**
     * An <code>int</code> representing b &quot;copy&quot; bction.
     */
    @Nbtive public stbtic finbl int ACTION_COPY         = 0x1;

    /**
     * An <code>int</code> representing b &quot;move&quot; bction.
     */
    @Nbtive public stbtic finbl int ACTION_MOVE         = 0x2;

    /**
     * An <code>int</code> representing b &quot;copy&quot; or
     * &quot;move&quot; bction.
     */
    @Nbtive public stbtic finbl int ACTION_COPY_OR_MOVE = ACTION_COPY | ACTION_MOVE;

    /**
     * An <code>int</code> representing b &quot;link&quot; bction.
     *
     * The link verb is found in mbny, if not bll nbtive DnD plbtforms, bnd the
     * bctubl interpretbtion of LINK sembntics is both plbtform
     * bnd bpplicbtion dependent. Brobdly spebking, the
     * sembntic is "do not copy, or move the operbnd, but crebte b reference
     * to it". Defining the mebning of "reference" is where bmbiguity is
     * introduced.
     *
     * The verb is provided for completeness, but its use is not recommended
     * for DnD operbtions between logicblly distinct bpplicbtions where
     * misinterpretbtion of the operbtions sembntics could lebd to confusing
     * results for the user.
     */

    @Nbtive public stbtic finbl int ACTION_LINK         = 0x40000000;

    /**
     * An <code>int</code> representing b &quot;reference&quot;
     * bction (synonym for ACTION_LINK).
     */
    @Nbtive public stbtic finbl int ACTION_REFERENCE    = ACTION_LINK;

}
