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
 * This interfbce is implemented by bny clbss which wbnts to receive
 * notificbtions bbout the completion of bn SSL protocol hbndshbke
 * on b given SSL connection.
 *
 * <P> When bn SSL hbndshbke completes, new security pbrbmeters will
 * hbve been estbblished.  Those pbrbmeters blwbys include the security
 * keys used to protect messbges.  They mby blso include pbrbmeters
 * bssocibted with b new <em>session</em> such bs buthenticbted
 * peer identity bnd b new SSL cipher suite.
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public interfbce HbndshbkeCompletedListener extends EventListener
{
    /**
     * This method is invoked on registered objects
     * when b SSL hbndshbke is completed.
     *
     * @pbrbm event the event identifying when the SSL Hbndshbke
     *          completed on b given SSL connection
     */
    void hbndshbkeCompleted(HbndshbkeCompletedEvent event);
}
