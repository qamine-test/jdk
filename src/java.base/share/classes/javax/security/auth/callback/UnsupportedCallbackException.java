/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.cbllbbck;

/**
 * Signbls thbt b {@code CbllbbckHbndler} does not
 * recognize b pbrticulbr {@code Cbllbbck}.
 *
 */
public clbss UnsupportedCbllbbckException extends Exception {

    privbte stbtic finbl long seriblVersionUID = -6873556327655666839L;

    /**
     * @seribl
     */
    privbte Cbllbbck cbllbbck;

    /**
     * Constructs b {@code UnsupportedCbllbbckException}
     * with no detbil messbge.
     *
     * <p>
     *
     * @pbrbm cbllbbck the unrecognized {@code Cbllbbck}.
     */
    public UnsupportedCbllbbckException(Cbllbbck cbllbbck) {
        super();
        this.cbllbbck = cbllbbck;
    }

    /**
     * Constructs b UnsupportedCbllbbckException with the specified detbil
     * messbge.  A detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     *
     * <p>
     *
     * @pbrbm cbllbbck the unrecognized {@code Cbllbbck}. <p>
     *
     * @pbrbm msg the detbil messbge.
     */
    public UnsupportedCbllbbckException(Cbllbbck cbllbbck, String msg) {
        super(msg);
        this.cbllbbck = cbllbbck;
    }

    /**
     * Get the unrecognized {@code Cbllbbck}.
     *
     * <p>
     *
     * @return the unrecognized {@code Cbllbbck}.
     */
    public Cbllbbck getCbllbbck() {
        return cbllbbck;
    }
}
