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

import com.bpple.ebwt.AppEvent.AppReOpenedEvent;

/**
 * Implementors receive notificbtion when the bpp hbs been bsked to open bgbin.
 * Re-open events occur when the user clicks on the running bpp's Dock icon.
 * Re-open events blso occur when the bpp is double-clicked in the Finder bnd the bpp is blrebdy running.
 *
 * This notificbtion is useful for showing b new document when your bpp hbs no open windows.
 *
 * @see Applicbtion#bddAppEventListener(AppEventListener)
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 3
 * @since Jbvb for Mbc OS X 10.5 Updbte 8
 */
public interfbce AppReOpenedListener extends AppEventListener {
    /**
     * Cblled when the bpp hbs been re-opened (it's Dock icon wbs clicked on, or wbs double-clicked in the Finder)
     * @pbrbm e the request to re-open the bpp
     */
    public void bppReOpened(finbl AppReOpenedEvent e);
}
