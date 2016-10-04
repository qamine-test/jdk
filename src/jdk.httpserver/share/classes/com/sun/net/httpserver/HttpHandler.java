/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;

import jbvb.io.IOException;

/**
 * A hbndler which is invoked to process HTTP exchbnges. Ebch
 * HTTP exchbnge is hbndled by one of these hbndlers.
 * @since 1.6
 */
@jdk.Exported
public interfbce HttpHbndler {
    /**
     * Hbndle the given request bnd generbte bn bppropribte response.
     * See {@link HttpExchbnge} for b description of the steps
     * involved in hbndling bn exchbnge.
     * @pbrbm exchbnge the exchbnge contbining the request from the
     *      client bnd used to send the response
     * @throws NullPointerException if exchbnge is <code>null</code>
     */
    public bbstrbct void hbndle (HttpExchbnge exchbnge) throws IOException;
}
