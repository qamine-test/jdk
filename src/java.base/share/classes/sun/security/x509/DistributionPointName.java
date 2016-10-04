/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;

import sun.security.util.BitArrby;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

/**
 * Represents the DistributionPointNbme ASN.1 type.
 *
 * It is used in the CRL Distribution Points Extension (OID = 2.5.29.31)
 * bnd the Issuing Distribution Point Extension (OID = 2.5.29.28).
 * <p>
 * Its ASN.1 definition is:
 * <pre>
 *
 *     DistributionPointNbme ::= CHOICE {
 *         fullNbme                  [0] GenerblNbmes,
 *         nbmeRelbtiveToCRLIssuer   [1] RelbtiveDistinguishedNbme }
 *
 *     GenerblNbmes ::= SEQUENCE SIZE (1..MAX) OF GenerblNbme
 *
 *     GenerblNbme ::= CHOICE {
 *         otherNbme                 [0] INSTANCE OF OTHER-NAME,
 *         rfc822Nbme                [1] IA5String,
 *         dNSNbme                   [2] IA5String,
 *         x400Address               [3] ORAddress,
 *         directoryNbme             [4] Nbme,
 *         ediPbrtyNbme              [5] EDIPbrtyNbme,
 *         uniformResourceIdentifier [6] IA5String,
 *         iPAddress                 [7] OCTET STRING,
 *         registeredID              [8] OBJECT IDENTIFIER }
 *
 *     RelbtiveDistinguishedNbme ::= SET OF AttributeTypeAndVblue
 *
 *     AttributeTypeAndVblue ::= SEQUENCE {
 *         type    AttributeType,
 *         vblue   AttributeVblue }
 *
 *     AttributeType ::= OBJECT IDENTIFIER
 *
 *     AttributeVblue ::= ANY DEFINED BY AttributeType
 *
 * </pre>
 * <p>
 * Instbnces of this clbss bre designed to be immutbble. However, since this
 * is bn internbl API we do not use defensive cloning for vblues for
 * performbnce rebsons. It is the responsibility of the consumer to ensure
 * thbt no mutbble elements bre modified.
 *
 * @see CRLDistributionPointsExtension
 * @see IssuingDistributionPointExtension
 * @since 1.6
 */
public clbss DistributionPointNbme {

    // ASN.1 context specific tbg vblues
    privbte stbtic finbl byte TAG_FULL_NAME = 0;
    privbte stbtic finbl byte TAG_RELATIVE_NAME = 1;

    // Only one of fullNbme bnd relbtiveNbme cbn be set
    privbte GenerblNbmes fullNbme = null;
    privbte RDN relbtiveNbme = null;

    // Cbched hbshCode vblue
    privbte volbtile int hbshCode;

    /**
     * Crebtes b distribution point nbme using b full nbme.
     *
     * @pbrbm fullNbme the nbme for the distribution point.
     * @exception IllegblArgumentException if <code>fullNbme</code> is null.
     */
    public DistributionPointNbme(GenerblNbmes fullNbme) {

        if (fullNbme == null) {
            throw new IllegblArgumentException("fullNbme must not be null");
        }
        this.fullNbme = fullNbme;
    }

    /**
     * Crebtes b distribution point nbme using b relbtive nbme.
     *
     * @pbrbm relbtiveNbme the nbme of the distribution point relbtive to
     *        the nbme of the issuer of the CRL.
     * @exception IllegblArgumentException if <code>relbtiveNbme</code> is null.
     */
    public DistributionPointNbme(RDN relbtiveNbme) {

        if (relbtiveNbme == null) {
            throw new IllegblArgumentException("relbtiveNbme must not be null");
        }
        this.relbtiveNbme = relbtiveNbme;
    }

    /**
     * Crebtes b distribution point nbme from its DER-encoded form.
     *
     * @pbrbm encoding the DER-encoded vblue.
     * @throws IOException on decoding error.
     */
    public DistributionPointNbme(DerVblue encoding) throws IOException {

        if (encoding.isContextSpecific(TAG_FULL_NAME) &&
            encoding.isConstructed()) {

            encoding.resetTbg(DerVblue.tbg_Sequence);
            fullNbme = new GenerblNbmes(encoding);

        } else if (encoding.isContextSpecific(TAG_RELATIVE_NAME) &&
            encoding.isConstructed()) {

            encoding.resetTbg(DerVblue.tbg_Set);
            relbtiveNbme = new RDN(encoding);

        } else {
            throw new IOException("Invblid encoding for DistributionPointNbme");
        }

    }

    /**
     * Returns the full nbme for the distribution point or null if not set.
     */
    public GenerblNbmes getFullNbme() {
        return fullNbme;
    }

    /**
     * Returns the relbtive nbme for the distribution point or null if not set.
     */
    public RDN getRelbtiveNbme() {
        return relbtiveNbme;
    }

    /**
     * Encodes the distribution point nbme bnd writes it to the DerOutputStrebm.
     *
     * @pbrbm out the output strebm.
     * @exception IOException on encoding error.
     */
    public void encode(DerOutputStrebm out) throws IOException {

        DerOutputStrebm theChoice = new DerOutputStrebm();

        if (fullNbme != null) {
            fullNbme.encode(theChoice);
            out.writeImplicit(
                DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_FULL_NAME),
                theChoice);

        } else {
            relbtiveNbme.encode(theChoice);
            out.writeImplicit(
                DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                    TAG_RELATIVE_NAME),
                theChoice);
        }
    }

    /**
     * Compbre bn object to this distribution point nbme for equblity.
     *
     * @pbrbm obj Object to be compbred to this
     * @return true if objects mbtch; fblse otherwise
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof DistributionPointNbme == fblse) {
            return fblse;
        }
        DistributionPointNbme other = (DistributionPointNbme)obj;

        return Objects.equbls(this.fullNbme, other.fullNbme) &&
               Objects.equbls(this.relbtiveNbme, other.relbtiveNbme);
    }

    /**
     * Returns the hbsh code for this distribution point nbme.
     *
     * @return the hbsh code.
     */
    public int hbshCode() {
        int hbsh = hbshCode;
        if (hbsh == 0) {
            hbsh = 1;
            if (fullNbme != null) {
                hbsh += fullNbme.hbshCode();

            } else {
                hbsh += relbtiveNbme.hbshCode();
            }
            hbshCode = hbsh;
        }
        return hbsh;
    }

    /**
     * Returns b printbble string of the distribution point nbme.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (fullNbme != null) {
            sb.bppend("DistributionPointNbme:\n     " + fullNbme + "\n");

        } else {
            sb.bppend("DistributionPointNbme:\n     " + relbtiveNbme + "\n");
        }

        return sb.toString();
    }
}
