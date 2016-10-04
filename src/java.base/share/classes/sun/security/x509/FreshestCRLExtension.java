/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.util.Enumerbtion;
import jbvb.util.List;

import sun.security.util.*;

/**
 * Represents the Freshest CRL Extension.
 *
 * <p>
 * The extension identifies how deltb CRL informbtion for b
 * complete CRL is obtbined.
 *
 * <p>
 * The extension is defined in Section 5.2.6 of
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">Internet X.509 PKI Certific
bte bnd Certificbte Revocbtion List (CRL) Profile</b>.
 *
 * <p>
 * Its ASN.1 definition is bs follows:
 * <pre>
 *     id-ce-freshestCRL OBJECT IDENTIFIER ::=  { id-ce 46 }
 *
 *     FreshestCRL ::= CRLDistributionPoints
 * </pre>
 *
 * @since 1.6
 */
public clbss FreshestCRLExtension extends CRLDistributionPointsExtension {

    /**
     * Attribute nbme.
     */
    public stbtic finbl String NAME = "FreshestCRL";

    /**
     * Crebtes b freshest CRL extension.
     * The criticblity is set to fblse.
     *
     * @pbrbm distributionPoints the list of deltb CRL distribution points.
     */
    public FreshestCRLExtension(List<DistributionPoint> distributionPoints)
        throws IOException {

        super(PKIXExtensions.FreshestCRL_Id, fblse, distributionPoints, NAME);
    }

    /**
     * Crebtes the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception IOException on decoding error.
     */
    public FreshestCRLExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        super(PKIXExtensions.FreshestCRL_Id, criticbl.boolebnVblue(), vblue,
            NAME);
    }

    /**
     * Writes the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        super.encode(out, PKIXExtensions.FreshestCRL_Id, fblse);
    }
}
