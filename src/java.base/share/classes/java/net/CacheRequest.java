/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/**
 * Represents chbnnels for storing resources in the
 * ResponseCbche. Instbnces of such b clbss provide bn
 * OutputStrebm object which is cblled by protocol hbndlers to
 * store the resource dbtb into the cbche, bnd blso bn bbort() method
 * which bllows b cbche store operbtion to be interrupted bnd
 * bbbndoned. If bn IOException is encountered while rebding the
 * response or writing to the cbche, the current cbche store operbtion
 * will be bborted.
 *
 * @buthor Yingxibn Wbng
 * @since 1.5
 */
public bbstrbct clbss CbcheRequest {

    /**
     * Returns bn OutputStrebm to which the response body cbn be
     * written.
     *
     * @return bn OutputStrebm to which the response body cbn
     *         be written
     * @throws IOException if bn I/O error occurs while
     *         writing the response body
     */
    public bbstrbct OutputStrebm getBody() throws IOException;

    /**
     * Aborts the bttempt to cbche the response. If bn IOException is
     * encountered while rebding the response or writing to the cbche,
     * the current cbche store operbtion will be bbbndoned.
     */
    public bbstrbct void bbort();
}
