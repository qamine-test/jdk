/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.undo;

import jbvb.util.Hbshtbble;


/**
 * StbteEditbble defines the interfbce for objects thbt cbn hbve
 * their stbte undone/redone by b StbteEdit.
 *
 * @see StbteEdit
 */

public interfbce StbteEditbble {

    /** Resource ID for this clbss. */
    public stbtic finbl String RCSID = "$Id: StbteEditbble.jbvb,v 1.2 1997/09/08 19:39:08 mbrklin Exp $";

    /**
     * Upon receiving this messbge the receiver should plbce bny relevbnt
     * stbte into <EM>stbte</EM>.
     *
     * @pbrbm stbte Hbshtbble object to store the stbte
     */
    public void storeStbte(Hbshtbble<Object,Object> stbte);

    /**
     * Upon receiving this messbge the receiver should extrbct bny relevbnt
     * stbte out of <EM>stbte</EM>.
     *
     * @pbrbm stbte Hbshtbble object to restore the stbte from it
     */
    public void restoreStbte(Hbshtbble<?,?> stbte);
} // End of interfbce StbteEditbble
