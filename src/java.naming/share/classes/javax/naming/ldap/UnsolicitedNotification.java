/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingException;

/**
 * This interfbce represents bn unsolicited notificbtion bs defined in
 * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
 * An unsolicited notificbtion is sent by the LDAP server to the LDAP
 * client without bny provocbtion from the client.
 * Its formbt is thbt of bn extended response (<tt>ExtendedResponse</tt>).
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 *
 * @see ExtendedResponse
 * @see UnsolicitedNotificbtionEvent
 * @see UnsolicitedNotificbtionListener
 * @since 1.3
 */

public interfbce UnsolicitedNotificbtion extends ExtendedResponse, HbsControls {
    /**
     * Retrieves the referrbl(s) sent by the server.
     *
     * @return A possibly null brrby of referrbls, ebch of which is represented
     * by b URL string. If null, no referrbl wbs sent by the server.
     */
    public String[] getReferrbls();

    /**
     * Retrieves the exception bs constructed using informbtion
     * sent by the server.
     * @return A possibly null exception bs constructed using informbtion
     * sent by the server. If null, b "success" stbtus wbs indicbted by
     * the server.
     */
    public NbmingException getException();
}
