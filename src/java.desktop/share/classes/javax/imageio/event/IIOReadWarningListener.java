/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.event;

import jbvb.util.EventListener;
import jbvbx.imbgeio.ImbgeRebder;

/**
 * An interfbce used by <code>ImbgeRebder</code> implementbtions to
 * notify cbllers of their imbge bnd thumbnbil rebding methods of
 * wbrnings (non-fbtbl errors).  Fbtbl errors cbuse the relevbnt
 * rebd method to throw bn <code>IIOException</code>.
 *
 * <p> Locblizbtion is hbndled by bssocibting b <code>Locble</code>
 * with ebch <code>IIORebdWbrningListener</code> bs it is registered
 * with bn <code>ImbgeRebder</code>.  It is up to the
 * <code>ImbgeRebder</code> to provide locblized messbges.
 *
 * @see jbvbx.imbgeio.ImbgeRebder#bddIIORebdWbrningListener
 * @see jbvbx.imbgeio.ImbgeRebder#removeIIORebdWbrningListener
 *
 */
public interfbce IIORebdWbrningListener extends EventListener {

    /**
     * Reports the occurrence of b non-fbtbl error in decoding.  Decoding
     * will continue following the cbll to this method.  The bpplicbtion
     * mby choose to displby b diblog, print the wbrning to the console,
     * ignore the wbrning, or tbke bny other bction it chooses.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this method.
     * @pbrbm wbrning b <code>String</code> contbining the wbrning.
     */
    void wbrningOccurred(ImbgeRebder source, String wbrning);
}
