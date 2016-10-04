/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.Seriblizbble;

/**
 * This interfbce represents bn X.509 extension.
 *
 * <p>
 * Extensions provide b mebns of bssocibting bdditionbl bttributes with users
 * or public keys bnd for mbnbging b certificbtion hierbrchy.  The extension
 * formbt blso bllows communities to define privbte extensions to cbrry
 * informbtion unique to those communities.
 *
 * <p>
 * Ebch extension contbins bn object identifier, b criticblity setting
 * indicbting whether it is b criticbl or b non-criticbl extension, bnd
 * bnd bn ASN.1 DER-encoded vblue. Its ASN.1 definition is:
 *
 * <pre>
 *
 *     Extension ::= SEQUENCE {
 *         extnId        OBJECT IDENTIFIER,
 *         criticbl      BOOLEAN DEFAULT FALSE,
 *         extnVblue     OCTET STRING
 *                 -- contbins b DER encoding of b vblue
 *                 -- of the type registered for use with
 *                 -- the extnId object identifier vblue
 *     }
 *
 * </pre>
 *
 * <p>
 * This interfbce is designed to provide bccess to b single extension,
 * unlike {@link jbvb.security.cert.X509Extension} which is more suitbble
 * for bccessing b set of extensions.
 *
 * @since 1.7
 */
public interfbce Extension {

    /**
     * Gets the extensions's object identifier.
     *
     * @return the object identifier bs b String
     */
    String getId();

    /**
     * Gets the extension's criticblity setting.
     *
     * @return true if this is b criticbl extension.
     */
    boolebn isCriticbl();

    /**
     * Gets the extensions's DER-encoded vblue. Note, this is the bytes
     * thbt bre encoded bs bn OCTET STRING. It does not include the OCTET
     * STRING tbg bnd length.
     *
     * @return b copy of the extension's vblue, or {@code null} if no
     *    extension vblue is present.
     */
    byte[] getVblue();

    /**
     * Generbtes the extension's DER encoding bnd writes it to the output
     * strebm.
     *
     * @pbrbm out the output strebm
     * @exception IOException on encoding or output error.
     * @exception NullPointerException if {@code out} is {@code null}.
     */
    void encode(OutputStrebm out) throws IOException;
}
