/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import com.bpple.ebwt.AppEvent.AppForegroundEvent;

/**
 * Implementors bre notified when the bpp becomes the foreground bpp bnd when it resigns being the foreground bpp.
 * This notificbtion is useful for hiding bnd showing trbnsient UI like pblette windows which should be hidden when the bpp is in the bbckground.
 *
 * @see Applicbtion#bddAppEventListener(AppEventListener)
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 3
 * @since Jbvb for Mbc OS X 10.5 Updbte 8
 */
public interfbce AppForegroundListener extends AppEventListener {
    /**
     * Cblled when the bpp becomes the foreground bpp.
     * @pbrbm e the bpp becbme foreground notificbtion.
     */
    public void bppRbisedToForeground(finbl AppForegroundEvent e);

    /**
     * Cblled when the bpp resigns to the bbckground bnd bnother bpp becomes the foreground bpp.
     * @pbrbm e the bpp resigned foreground notificbtion.
     */
    public void bppMovedToBbckground(finbl AppForegroundEvent e);
}
