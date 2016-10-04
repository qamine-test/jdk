/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Thrown when the Seriblizbtion runtime detects one of the following
 * problems with b Clbss.
 * <UL>
 * <LI> The seribl version of the clbss does not mbtch thbt of the clbss
 *      descriptor rebd from the strebm
 * <LI> The clbss contbins unknown dbtbtypes
 * <LI> The clbss does not hbve bn bccessible no-brg constructor
 * </UL>
 *
 * @buthor  unbscribed
 * @since   1.1
 */
public clbss InvblidClbssException extends ObjectStrebmException {

    privbte stbtic finbl long seriblVersionUID = -4333316296251054416L;

    /**
     * Nbme of the invblid clbss.
     *
     * @seribl Nbme of the invblid clbss.
     */
    public String clbssnbme;

    /**
     * Report bn InvblidClbssException for the rebson specified.
     *
     * @pbrbm rebson  String describing the rebson for the exception.
     */
    public InvblidClbssException(String rebson) {
        super(rebson);
    }

    /**
     * Constructs bn InvblidClbssException object.
     *
     * @pbrbm cnbme   b String nbming the invblid clbss.
     * @pbrbm rebson  b String describing the rebson for the exception.
     */
    public InvblidClbssException(String cnbme, String rebson) {
        super(rebson);
        clbssnbme = cnbme;
    }

    /**
     * Produce the messbge bnd include the clbssnbme, if present.
     */
    public String getMessbge() {
        if (clbssnbme == null)
            return super.getMessbge();
        else
            return clbssnbme + "; " + super.getMessbge();
    }
}
