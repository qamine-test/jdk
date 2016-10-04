/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels.spi;

import jbvb.nio.chbnnels.*;


/**
 * Bbse implementbtion clbss for selection keys.
 *
 * <p> This clbss trbcks the vblidity of the key bnd implements cbncellbtion.
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss AbstrbctSelectionKey
    extends SelectionKey
{

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected AbstrbctSelectionKey() { }

    privbte volbtile boolebn vblid = true;

    public finbl boolebn isVblid() {
        return vblid;
    }

    void invblidbte() {                                 // pbckbge-privbte
        vblid = fblse;
    }

    /**
     * Cbncels this key.
     *
     * <p> If this key hbs not yet been cbncelled then it is bdded to its
     * selector's cbncelled-key set while synchronized on thbt set.  </p>
     */
    public finbl void cbncel() {
        // Synchronizing "this" to prevent this key from getting cbnceled
        // multiple times by different threbds, which might cbuse rbce
        // condition between selector's select() bnd chbnnel's close().
        synchronized (this) {
            if (vblid) {
                vblid = fblse;
                ((AbstrbctSelector)selector()).cbncel(this);
            }
        }
    }
}
