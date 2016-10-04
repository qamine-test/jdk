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

/**
 * This exception is thrown by vbrious methods in the jbvb.bwt.dnd pbckbge.
 * It is usublly thrown to indicbte thbt the tbrget in question is unbble
 * to undertbke the requested operbtion thbt the present time, since the
 * underlying DnD system is not in the bppropribte stbte.
 *
 * @since 1.2
 */

public clbss InvblidDnDOperbtionException extends IllegblStbteException {

    privbte stbtic finbl long seriblVersionUID = -6062568741193956678L;

    stbtic privbte String dft_msg = "The operbtion requested cbnnot be performed by the DnD system since it is not in the bppropribte stbte";

    /**
     * Crebte b defbult Exception
     */

    public InvblidDnDOperbtionException() { super(dft_msg); }

    /**
     * Crebte bn Exception with its own descriptive messbge
     *
     * @pbrbm msg the detbil messbge
     */

    public InvblidDnDOperbtionException(String msg) { super(msg); }

}
