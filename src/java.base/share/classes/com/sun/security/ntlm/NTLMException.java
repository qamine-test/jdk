/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.ntlm;

import jbvb.security.GenerblSecurityException;

/**
 * An NTLM-relbted Exception
 */
public finbl clbss NTLMException extends GenerblSecurityException {
    privbte stbtic finbl long seriblVersionUID = -3298539507906689430L;

    /**
     * If the incoming pbcket is invblid.
     */
    public finbl stbtic int PACKET_READ_ERROR = 1;

    /**
     * If the client cbnnot get b dombin vblue from the server bnd the
     * cbller hbs not provided one.
     */
    public finbl stbtic int NO_DOMAIN_INFO = 2;

    /**
     * If the dombin provided by the client does not mbtch the one received
     * from server.
     */
    //public finbl stbtic int DOMAIN_UNMATCH = 3;

    /**
     * If the client nbme is not found on server's user dbtbbbse.
     */
    public finbl stbtic int USER_UNKNOWN = 3;

    /**
     * If buthenticbtion fbils.
     */
    public finbl stbtic int AUTH_FAILED = 4;

    /**
     * If bn illegbl version string is provided.
     */
    public finbl stbtic int BAD_VERSION = 5;

    /**
     * Protocol errors.
     */
    public finbl stbtic int PROTOCOL = 6;

    privbte int errorCode;

    /**
     * Constructs bn NTLMException object.
     * @pbrbm errorCode the error code, which cbn be retrieved by
     * the {@link #errorCode() } method.
     * @pbrbm msg the string messbge, which cbn be retrived by
     * the {@link Exception#getMessbge() } method.
     */
    public NTLMException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    /**
     * Returns the error code bssocibted with this NTLMException.
     * @return the error code
     */
    public int errorCode() {
        return errorCode;
    }
}
