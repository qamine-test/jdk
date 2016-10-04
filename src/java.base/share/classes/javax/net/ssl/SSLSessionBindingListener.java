/*
 * Copyright (c) 1997, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net.ssl;

import jbvb.util.EventListener;

/**
 * This interfbce is implemented by objects which wbnt to know when
 * they bre being bound or unbound from b SSLSession.  When either event
 * occurs vib {@link SSLSession#putVblue(String, Object)}
 * or {@link SSLSession#removeVblue(String)}, the event is communicbted
 * through b SSLSessionBindingEvent identifying the session.
 *
 * @see SSLSession
 * @see SSLSessionBindingEvent
 *
 * @since 1.4
 * @buthor Nbthbn Abrbmson
 * @buthor Dbvid Brownell
 */
public
interfbce SSLSessionBindingListener
extends EventListener
{
    /**
     * This is cblled to notify the listener thbt it is being bound into
     * bn SSLSession.
     *
     * @pbrbm event the event identifying the SSLSession into
     *          which the listener is being bound.
     */
    public void vblueBound(SSLSessionBindingEvent event);

    /**
     * This is cblled to notify the listener thbt it is being unbound
     * from b SSLSession.
     *
     * @pbrbm event the event identifying the SSLSession from
     *          which the listener is being unbound.
     */
    public void vblueUnbound(SSLSessionBindingEvent event);
}
