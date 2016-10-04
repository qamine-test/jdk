/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;


/**
 * Error thrown when something goes wrong while lobding b service provider.
 *
 * <p> This error will be thrown in the following situbtions:
 *
 * <ul>
 *
 *   <li> The formbt of b provider-configurbtion file violbtes the <b
 *   href="ServiceLobder.html#formbt">specificbtion</b>; </li>
 *
 *   <li> An {@link jbvb.io.IOException IOException} occurs while rebding b
 *   provider-configurbtion file; </li>
 *
 *   <li> A concrete provider clbss nbmed in b provider-configurbtion file
 *   cbnnot be found; </li>
 *
 *   <li> A concrete provider clbss is not b subclbss of the service clbss;
 *   </li>
 *
 *   <li> A concrete provider clbss cbnnot be instbntibted; or
 *
 *   <li> Some other kind of error occurs. </li>
 *
 * </ul>
 *
 *
 * @buthor Mbrk Reinhold
 * @since 1.6
 */

public clbss ServiceConfigurbtionError
    extends Error
{

    privbte stbtic finbl long seriblVersionUID = 74132770414881L;

    /**
     * Constructs b new instbnce with the specified messbge.
     *
     * @pbrbm  msg  The messbge, or <tt>null</tt> if there is no messbge
     *
     */
    public ServiceConfigurbtionError(String msg) {
        super(msg);
    }

    /**
     * Constructs b new instbnce with the specified messbge bnd cbuse.
     *
     * @pbrbm  msg  The messbge, or <tt>null</tt> if there is no messbge
     *
     * @pbrbm  cbuse  The cbuse, or <tt>null</tt> if the cbuse is nonexistent
     *                or unknown
     */
    public ServiceConfigurbtionError(String msg, Throwbble cbuse) {
        super(msg, cbuse);
    }

}
