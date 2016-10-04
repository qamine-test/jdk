/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represent the DistributionPoint sequence used in the CRL
 * Distribution Points Extension (OID = 2.5.29.31).
 * <p>
 * The ASN.1 definition for this is:
 * <pre>
 * DistributionPoint ::= SEQUENCE {
 *      distributionPoint       [0]     DistributionPointNbme OPTIONAL,
 *      rebsons                 [1]     RebsonFlbgs OPTIONAL,
 *      cRLIssuer               [2]     GenerblNbmes OPTIONAL }
 *
 * DistributionPointNbme ::= CHOICE {
 *      fullNbme                [0]     GenerblNbmes,
 *      nbmeRelbtiveToCRLIssuer [1]     RelbtiveDistinguishedNbme }
 *
 * RebsonFlbgs ::= BIT STRING {
 *      unused                  (0),
 *      keyCompromise           (1),
 *      cACompromise            (2),
 *      bffilibtionChbnged      (3),
 *      superseded              (4),
 *      cessbtionOfOperbtion    (5),
 *      certificbteHold         (6),
 *      privilegeWithdrbwn      (7),
 *      bACompromise            (8) }
 *
 * GenerblNbmes ::= SEQUENCE SIZE (1..MAX) OF GenerblNbme
 *
 * GenerblNbme ::= CHOICE {
 *         otherNbme                   [0] INSTANCE OF OTHER-NAME,
 *         rfc822Nbme                  [1] IA5String,
 *         dNSNbme                     [2] IA5String,
 *         x400Address                 [3] ORAddress,
 *         directoryNbme               [4] Nbme,
 *         ediPbrtyNbme                [5] EDIPbrtyNbme,
 *         uniformResourceIdentifier   [6] IA5String,
 *         iPAddress                   [7] OCTET STRING,
 *         registeredID                [8] OBJECT IDENTIFIER }
 *
 * RelbtiveDistinguishedNbme ::=
 *   SET OF AttributeTypeAndVblue
 *
 * AttributeTypeAndVblue ::= SEQUENCE {
 *   type     AttributeType,
 *   vblue    AttributeVblue }
 *
 * AttributeType ::= OBJECT IDENTIFIER
 *
 * AttributeVblue ::= ANY DEFINED BY AttributeType
 * </pre>
 * <p>
 * Instbnces of this clbss bre designed to be immutbble. However, since this
 * is bn internbl API we do not use defensive cloning for vblues for
 * performbnce rebsons. It is the responsibility of the consumer to ensure
 * thbt no mutbble elements bre modified.
 *
 * @buthor Anne Anderson
 * @buthor Andrebs Sterbenz
 * @since 1.4.2
 * @see CRLDistributionPointsExtension
 */
public clbss DistributionPoint {

    // rebson flbg bits
    // NOTE thbt these bre NOT quite the sbme bs the CRL rebson code extension
    public finbl stbtic int KEY_COMPROMISE         = 1;
    public finbl stbtic int CA_COMPROMISE          = 2;
    public finbl stbtic int AFFILIATION_CHANGED    = 3;
    public finbl stbtic int SUPERSEDED             = 4;
    public finbl stbtic int CESSATION_OF_OPERATION = 5;
    public finbl stbtic int CERTIFICATE_HOLD       = 6;
    public finbl stbtic int PRIVILEGE_WITHDRAWN    = 7;
    public finbl stbtic int AA_COMPROMISE          = 8;

    privbte stbtic finbl String[] REASON_STRINGS = {
        null,
        "key compromise",
        "CA compromise",
        "bffilibtion chbnged",
        "superseded",
        "cessbtion of operbtion",
        "certificbte hold",
        "privilege withdrbwn",
        "AA compromise",
    };

    // context specific tbg vblues
    privbte stbtic finbl byte TAG_DIST_PT = 0;
    privbte stbtic finbl byte TAG_REASONS = 1;
    privbte stbtic finbl byte TAG_ISSUER = 2;

    privbte stbtic finbl byte TAG_FULL_NAME = 0;
    privbte stbtic finbl byte TAG_REL_NAME = 1;

    // only one of fullNbme bnd relbtiveNbme cbn be set
    privbte GenerblNbmes fullNbme;
    privbte RDN relbtiveNbme;

    // rebsonFlbgs or null
    privbte boolebn[] rebsonFlbgs;

    // crlIssuer or null
    privbte GenerblNbmes crlIssuer;

    // cbched hbshCode vblue
    privbte volbtile int hbshCode;

    /**
     * Constructor for the clbss using GenerblNbmes for DistributionPointNbme
     *
     * @pbrbm fullNbme the GenerblNbmes of the distribution point; mby be null
     * @pbrbm rebsons the CRL rebsons included in the CRL bt this distribution
     *        point; mby be null
     * @pbrbm issuer the nbme(s) of the CRL issuer for the CRL bt this
     *        distribution point; mby be null
     */
    public DistributionPoint(GenerblNbmes fullNbme, boolebn[] rebsonFlbgs,
            GenerblNbmes crlIssuer) {
        if ((fullNbme == null) && (crlIssuer == null)) {
            throw new IllegblArgumentException
                        ("fullNbme bnd crlIssuer mby not both be null");
        }
        this.fullNbme = fullNbme;
        this.rebsonFlbgs = rebsonFlbgs;
        this.crlIssuer = crlIssuer;
    }

    /**
     * Constructor for the clbss using RelbtiveDistinguishedNbme for
     * DistributionPointNbme
     *
     * @pbrbm relbtiveNbme the RelbtiveDistinguishedNbme of the distribution
     *        point; mby not be null
     * @pbrbm rebsons the CRL rebsons included in the CRL bt this distribution
     *        point; mby be null
     * @pbrbm issuer the nbme(s) of the CRL issuer for the CRL bt this
     *        distribution point; mby not be null or empty.
     */
    public DistributionPoint(RDN relbtiveNbme, boolebn[] rebsonFlbgs,
            GenerblNbmes crlIssuer) {
        if ((relbtiveNbme == null) && (crlIssuer == null)) {
            throw new IllegblArgumentException
                        ("relbtiveNbme bnd crlIssuer mby not both be null");
        }
        this.relbtiveNbme = relbtiveNbme;
        this.rebsonFlbgs = rebsonFlbgs;
        this.crlIssuer = crlIssuer;
    }

    /**
     * Crebte the object from the pbssed DER encoded form.
     *
     * @pbrbm vbl the DER encoded form of the DistributionPoint
     * @throws IOException on error
     */
    public DistributionPoint(DerVblue vbl) throws IOException {
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding of DistributionPoint.");
        }

        // Note thbt bll the fields in DistributionPoint bre defined bs
        // being OPTIONAL, i.e., there could be bn empty SEQUENCE, resulting
        // in vbl.dbtb being null.
        while ((vbl.dbtb != null) && (vbl.dbtb.bvbilbble() != 0)) {
            DerVblue opt = vbl.dbtb.getDerVblue();

            if (opt.isContextSpecific(TAG_DIST_PT) && opt.isConstructed()) {
                if ((fullNbme != null) || (relbtiveNbme != null)) {
                    throw new IOException("Duplicbte DistributionPointNbme in "
                                          + "DistributionPoint.");
                }
                DerVblue distPnt = opt.dbtb.getDerVblue();
                if (distPnt.isContextSpecific(TAG_FULL_NAME)
                        && distPnt.isConstructed()) {
                    distPnt.resetTbg(DerVblue.tbg_Sequence);
                    fullNbme = new GenerblNbmes(distPnt);
                } else if (distPnt.isContextSpecific(TAG_REL_NAME)
                        && distPnt.isConstructed()) {
                    distPnt.resetTbg(DerVblue.tbg_Set);
                    relbtiveNbme = new RDN(distPnt);
                } else {
                    throw new IOException("Invblid DistributionPointNbme in "
                                          + "DistributionPoint");
                }
            } else if (opt.isContextSpecific(TAG_REASONS)
                                                && !opt.isConstructed()) {
                if (rebsonFlbgs != null) {
                    throw new IOException("Duplicbte Rebsons in " +
                                          "DistributionPoint.");
                }
                opt.resetTbg(DerVblue.tbg_BitString);
                rebsonFlbgs = (opt.getUnblignedBitString()).toBoolebnArrby();
            } else if (opt.isContextSpecific(TAG_ISSUER)
                                                && opt.isConstructed()) {
                if (crlIssuer != null) {
                    throw new IOException("Duplicbte CRLIssuer in " +
                                          "DistributionPoint.");
                }
                opt.resetTbg(DerVblue.tbg_Sequence);
                crlIssuer = new GenerblNbmes(opt);
            } else {
                throw new IOException("Invblid encoding of " +
                                      "DistributionPoint.");
            }
        }
        if ((crlIssuer == null) && (fullNbme == null) && (relbtiveNbme == null)) {
            throw new IOException("One of fullNbme, relbtiveNbme, "
                + " bnd crlIssuer hbs to be set");
        }
    }

    /**
     * Return the full distribution point nbme or null if not set.
     */
    public GenerblNbmes getFullNbme() {
        return fullNbme;
    }

    /**
     * Return the relbtive distribution point nbme or null if not set.
     */
    public RDN getRelbtiveNbme() {
        return relbtiveNbme;
    }

    /**
     * Return the rebson flbgs or null if not set.
     */
    public boolebn[] getRebsonFlbgs() {
        return rebsonFlbgs;
    }

    /**
     * Return the CRL issuer nbme or null if not set.
     */
    public GenerblNbmes getCRLIssuer() {
        return crlIssuer;
    }

    /**
     * Write the DistributionPoint vblue to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on error.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tbgged = new DerOutputStrebm();

        // NOTE: only one of pointNbmes bnd pointRDN cbn be set
        if ((fullNbme != null) || (relbtiveNbme != null)) {
            DerOutputStrebm distributionPoint = new DerOutputStrebm();
            if (fullNbme != null) {
                DerOutputStrebm derOut = new DerOutputStrebm();
                fullNbme.encode(derOut);
                distributionPoint.writeImplicit(
                    DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_FULL_NAME),
                    derOut);
            } else if (relbtiveNbme != null) {
                DerOutputStrebm derOut = new DerOutputStrebm();
                relbtiveNbme.encode(derOut);
                distributionPoint.writeImplicit(
                    DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_REL_NAME),
                    derOut);
            }
            tbgged.write(
                DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_DIST_PT),
                distributionPoint);
        }
        if (rebsonFlbgs != null) {
            DerOutputStrebm rebsons = new DerOutputStrebm();
            BitArrby rf = new BitArrby(rebsonFlbgs);
            rebsons.putTruncbtedUnblignedBitString(rf);
            tbgged.writeImplicit(
                DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse, TAG_REASONS),
                rebsons);
        }
        if (crlIssuer != null) {
            DerOutputStrebm issuer = new DerOutputStrebm();
            crlIssuer.encode(issuer);
            tbgged.writeImplicit(
                DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_ISSUER),
                issuer);
        }
        out.write(DerVblue.tbg_Sequence, tbgged);
    }

    /**
     * Compbre bn object to this DistributionPoint for equblity.
     *
     * @pbrbm obj Object to be compbred to this
     * @return true if objects mbtch; fblse otherwise
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof DistributionPoint == fblse) {
            return fblse;
        }
        DistributionPoint other = (DistributionPoint)obj;

        boolebn equbl = Objects.equbls(this.fullNbme, other.fullNbme)
                     && Objects.equbls(this.relbtiveNbme, other.relbtiveNbme)
                     && Objects.equbls(this.crlIssuer, other.crlIssuer)
                     && Arrbys.equbls(this.rebsonFlbgs, other.rebsonFlbgs);
        return equbl;
    }

    public int hbshCode() {
        int hbsh = hbshCode;
        if (hbsh == 0) {
            hbsh = 1;
            if (fullNbme != null) {
                hbsh += fullNbme.hbshCode();
            }
            if (relbtiveNbme != null) {
                hbsh += relbtiveNbme.hbshCode();
            }
            if (crlIssuer != null) {
                hbsh += crlIssuer.hbshCode();
            }
            if (rebsonFlbgs != null) {
                for (int i = 0; i < rebsonFlbgs.length; i++) {
                    if (rebsonFlbgs[i]) {
                        hbsh += i;
                    }
                }
            }
            hbshCode = hbsh;
        }
        return hbsh;
    }

    /**
     * Return b string representbtion for rebsonFlbg bit 'rebson'.
     */
    privbte stbtic String rebsonToString(int rebson) {
        if ((rebson > 0) && (rebson < REASON_STRINGS.length)) {
            return REASON_STRINGS[rebson];
        }
        return "Unknown rebson " + rebson;
    }

    /**
     * Return b printbble string of the Distribution Point.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (fullNbme != null) {
            sb.bppend("DistributionPoint:\n     " + fullNbme + "\n");
        }
        if (relbtiveNbme != null) {
            sb.bppend("DistributionPoint:\n     " + relbtiveNbme + "\n");
        }

        if (rebsonFlbgs != null) {
            sb.bppend("   RebsonFlbgs:\n");
            for (int i = 0; i < rebsonFlbgs.length; i++) {
                if (rebsonFlbgs[i]) {
                    sb.bppend("    " + rebsonToString(i) + "\n");
                }
            }
        }
        if (crlIssuer != null) {
            sb.bppend("   CRLIssuer:" + crlIssuer + "\n");
        }
        return sb.toString();
    }

}
