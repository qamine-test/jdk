/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.util.EventListener;

/**
 * The listener interfbce for receiving displby chbnge events.
 * The clbss thbt is interested in processing b displby chbnge event
 * implements this interfbce (bnd bll the methods it
 * contbins).
 *
 * For Motif, this interfbce is only used for drbgging windows between Xinerbmb
 * screens.
 *
 * For win32, the listener object crebted from thbt clbss is then registered
 * with the WToolkit object using its <code>bddDisplbyChbngeListener</code>
 * method. When the displby resolution is chbnged (which occurs,
 * in Windows, either by the user chbnging the properties of the
 * displby through the control pbnel or other utility or by
 * some other bpplicbtion which hbs gotten fullscreen-exclusive
 * control of the displby), the listener is notified through its
 * displbyChbnged() or pbletteChbnged() methods.
 *
 * @buthor Chet Hbbse
 * @buthor Brent Christibn
 * @since 1.4
 */
public interfbce DisplbyChbngedListener extends EventListener {
    /**
     * Invoked when the displby mode hbs chbnged.
     */
    public void displbyChbnged();

    /**
     * Invoked when the pblette hbs chbnged.
     */
    public void pbletteChbnged();

}
