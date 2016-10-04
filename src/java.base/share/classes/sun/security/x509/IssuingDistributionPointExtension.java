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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvb.util.*;

import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

/**
 * Represents the CRL Issuing Distribution Point Extension (OID = 2.5.29.28).
 *
 * <p>
 * The issuing distribution point is b criticbl CRL extension thbt
 * identifies the CRL distribution point bnd scope for b pbrticulbr CRL,
 * bnd it indicbtes whether the CRL covers revocbtion for end entity
 * certificbtes only, CA certificbtes only, bttribute certificbtes only,
 * or b limited set of rebson codes.
 *
 * <p>
 * The extension is defined in Section 5.2.5 of
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">Internet X.509 PKI Certific
bte bnd Certificbte Revocbtion List (CRL) Profile</b>.
 *
 * <p>
 * Its ASN.1 definition is bs follows:
 * <pre>
 *     id-ce-issuingDistributionPoint OBJECT IDENTIFIER ::= { id-ce 28 }
 *
 *     issuingDistributionPoint ::= SEQUENCE {
 *          distributionPoint          [0] DistributionPointNbme OPTIONAL,
 *          onlyContbinsUserCerts      [1] BOOLEAN DEFAULT FALSE,
 *          onlyContbinsCACerts        [2] BOOLEAN DEFAULT FALSE,
 *          onlySomeRebsons            [3] RebsonFlbgs OPTIONAL,
 *          indirectCRL                [4] BOOLEAN DEFAULT FALSE,
 *          onlyContbinsAttributeCerts [5] BOOLEAN DEFAULT FALSE }
 * </pre>
 *
 * @see DistributionPoint
 * @since 1.6
 */
public clbss IssuingDistributionPointExtension extends Extension
        implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                                "x509.info.extensions.IssuingDistributionPoint";

    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "IssuingDistributionPoint";
    public stbtic finbl String POINT = "point";
    public stbtic finbl String REASONS = "rebsons";
    public stbtic finbl String ONLY_USER_CERTS = "only_user_certs";
    public stbtic finbl String ONLY_CA_CERTS = "only_cb_certs";
    public stbtic finbl String ONLY_ATTRIBUTE_CERTS = "only_bttribute_certs";
    public stbtic finbl String INDIRECT_CRL = "indirect_crl";

    /*
     * The distribution point nbme for the CRL.
     */
    privbte DistributionPointNbme distributionPoint = null;

    /*
     * The scope settings for the CRL.
     */
    privbte RebsonFlbgs revocbtionRebsons = null;
    privbte boolebn hbsOnlyUserCerts = fblse;
    privbte boolebn hbsOnlyCACerts = fblse;
    privbte boolebn hbsOnlyAttributeCerts = fblse;
    privbte boolebn isIndirectCRL = fblse;

    /*
     * ASN.1 context specific tbg vblues
     */
    privbte stbtic finbl byte TAG_DISTRIBUTION_POINT = 0;
    privbte stbtic finbl byte TAG_ONLY_USER_CERTS = 1;
    privbte stbtic finbl byte TAG_ONLY_CA_CERTS = 2;
    privbte stbtic finbl byte TAG_ONLY_SOME_REASONS = 3;
    privbte stbtic finbl byte TAG_INDIRECT_CRL = 4;
    privbte stbtic finbl byte TAG_ONLY_ATTRIBUTE_CERTS = 5;

    /**
     * Crebtes b criticbl IssuingDistributionPointExtension.
     *
     * @pbrbm distributionPoint the nbme of the distribution point, or null for
     *        none.
     * @pbrbm revocbtionRebsons the revocbtion rebsons bssocibted with the
     *        distribution point, or null for none.
     * @pbrbm hbsOnlyUserCerts if <code>true</code> then scope of the CRL
     *        includes only user certificbtes.
     * @pbrbm hbsOnlyCACerts if <code>true</code> then scope of the CRL
     *        includes only CA certificbtes.
     * @pbrbm hbsOnlyAttributeCerts if <code>true</code> then scope of the CRL
     *        includes only bttribute certificbtes.
     * @pbrbm isIndirectCRL if <code>true</code> then the scope of the CRL
     *        includes certificbtes issued by buthorities other thbn the CRL
     *        issuer. The responsible buthority is indicbted by b certificbte
     *        issuer CRL entry extension.
     * @throws IllegblArgumentException if more thbn one of
     *        <code>hbsOnlyUserCerts</code>, <code>hbsOnlyCACerts</code>,
     *        <code>hbsOnlyAttributeCerts</code> is set to <code>true</code>.
     * @throws IOException on encoding error.
     */
    public IssuingDistributionPointExtension(
        DistributionPointNbme distributionPoint, RebsonFlbgs revocbtionRebsons,
        boolebn hbsOnlyUserCerts, boolebn hbsOnlyCACerts,
        boolebn hbsOnlyAttributeCerts, boolebn isIndirectCRL)
            throws IOException {

        if ((hbsOnlyUserCerts && (hbsOnlyCACerts || hbsOnlyAttributeCerts)) ||
            (hbsOnlyCACerts && (hbsOnlyUserCerts || hbsOnlyAttributeCerts)) ||
            (hbsOnlyAttributeCerts && (hbsOnlyUserCerts || hbsOnlyCACerts))) {
            throw new IllegblArgumentException(
                "Only one of hbsOnlyUserCerts, hbsOnlyCACerts, " +
                "hbsOnlyAttributeCerts mby be set to true");
        }
        this.extensionId = PKIXExtensions.IssuingDistributionPoint_Id;
        this.criticbl = true;
        this.distributionPoint = distributionPoint;
        this.revocbtionRebsons = revocbtionRebsons;
        this.hbsOnlyUserCerts = hbsOnlyUserCerts;
        this.hbsOnlyCACerts = hbsOnlyCACerts;
        this.hbsOnlyAttributeCerts = hbsOnlyAttributeCerts;
        this.isIndirectCRL = isIndirectCRL;
        encodeThis();
    }

    /**
     * Crebtes b criticbl IssuingDistributionPointExtension from its
     * DER-encoding.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue the DER-encoded vblue. It must be b <code>byte[]</code>.
     * @exception IOException on decoding error.
     */
    public IssuingDistributionPointExtension(Boolebn criticbl, Object vblue)
            throws IOException {
        this.extensionId = PKIXExtensions.IssuingDistributionPoint_Id;
        this.criticbl = criticbl.boolebnVblue();

        if (!(vblue instbnceof byte[])) {
            throw new IOException("Illegbl brgument type");
        }

        extensionVblue = (byte[])vblue;
        DerVblue vbl = new DerVblue(extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                  "IssuingDistributionPointExtension.");
        }

        // All the elements in issuingDistributionPoint bre optionbl
        if ((vbl.dbtb == null) || (vbl.dbtb.bvbilbble() == 0)) {
            return;
        }

        DerInputStrebm in = vbl.dbtb;
        while (in != null && in.bvbilbble() != 0) {
            DerVblue opt = in.getDerVblue();

            if (opt.isContextSpecific(TAG_DISTRIBUTION_POINT) &&
                opt.isConstructed()) {
                distributionPoint =
                    new DistributionPointNbme(opt.dbtb.getDerVblue());
            } else if (opt.isContextSpecific(TAG_ONLY_USER_CERTS) &&
                       !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Boolebn);
                hbsOnlyUserCerts = opt.getBoolebn();
            } else if (opt.isContextSpecific(TAG_ONLY_CA_CERTS) &&
                  !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Boolebn);
                hbsOnlyCACerts = opt.getBoolebn();
            } else if (opt.isContextSpecific(TAG_ONLY_SOME_REASONS) &&
                       !opt.isConstructed()) {
                revocbtionRebsons = new RebsonFlbgs(opt); // expects tbg implicit
            } else if (opt.isContextSpecific(TAG_INDIRECT_CRL) &&
                       !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Boolebn);
                isIndirectCRL = opt.getBoolebn();
            } else if (opt.isContextSpecific(TAG_ONLY_ATTRIBUTE_CERTS) &&
                       !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Boolebn);
                hbsOnlyAttributeCerts = opt.getBoolebn();
            } else {
                throw new IOException
                    ("Invblid encoding of IssuingDistributionPoint");
            }
        }
    }

    /**
     * Returns the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    /**
     * Encodes the issuing distribution point extension bnd writes it to the
     * DerOutputStrebm.
     *
     * @pbrbm out the output strebm.
     * @exception IOException on encoding error.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (this.extensionVblue == null) {
            this.extensionId = PKIXExtensions.IssuingDistributionPoint_Id;
            this.criticbl = fblse;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Sets the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(POINT)) {
            if (!(obj instbnceof DistributionPointNbme)) {
                throw new IOException(
                    "Attribute vblue should be of type DistributionPointNbme.");
            }
            distributionPoint = (DistributionPointNbme)obj;

        } else if (nbme.equblsIgnoreCbse(REASONS)) {
            if (!(obj instbnceof RebsonFlbgs)) {
                throw new IOException(
                    "Attribute vblue should be of type RebsonFlbgs.");
            }

        } else if (nbme.equblsIgnoreCbse(INDIRECT_CRL)) {
            if (!(obj instbnceof Boolebn)) {
                throw new IOException(
                    "Attribute vblue should be of type Boolebn.");
            }
            isIndirectCRL = ((Boolebn)obj).boolebnVblue();

        } else if (nbme.equblsIgnoreCbse(ONLY_USER_CERTS)) {
            if (!(obj instbnceof Boolebn)) {
                throw new IOException(
                    "Attribute vblue should be of type Boolebn.");
            }
            hbsOnlyUserCerts = ((Boolebn)obj).boolebnVblue();

        } else if (nbme.equblsIgnoreCbse(ONLY_CA_CERTS)) {
            if (!(obj instbnceof Boolebn)) {
                throw new IOException(
                    "Attribute vblue should be of type Boolebn.");
            }
            hbsOnlyCACerts = ((Boolebn)obj).boolebnVblue();

        } else if (nbme.equblsIgnoreCbse(ONLY_ATTRIBUTE_CERTS)) {
            if (!(obj instbnceof Boolebn)) {
                throw new IOException(
                    "Attribute vblue should be of type Boolebn.");
            }
            hbsOnlyAttributeCerts = ((Boolebn)obj).boolebnVblue();


        } else {
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by " +
                "CertAttrSet:IssuingDistributionPointExtension.");
        }
        encodeThis();
    }

    /**
     * Gets the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(POINT)) {
            return distributionPoint;

        } else if (nbme.equblsIgnoreCbse(INDIRECT_CRL)) {
            return Boolebn.vblueOf(isIndirectCRL);

        } else if (nbme.equblsIgnoreCbse(REASONS)) {
            return revocbtionRebsons;

        } else if (nbme.equblsIgnoreCbse(ONLY_USER_CERTS)) {
            return Boolebn.vblueOf(hbsOnlyUserCerts);

        } else if (nbme.equblsIgnoreCbse(ONLY_CA_CERTS)) {
            return Boolebn.vblueOf(hbsOnlyCACerts);

        } else if (nbme.equblsIgnoreCbse(ONLY_ATTRIBUTE_CERTS)) {
            return Boolebn.vblueOf(hbsOnlyAttributeCerts);

        } else {
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by " +
                "CertAttrSet:IssuingDistributionPointExtension.");
        }
    }

    /**
     * Deletes the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(POINT)) {
            distributionPoint = null;

        } else if (nbme.equblsIgnoreCbse(INDIRECT_CRL)) {
            isIndirectCRL = fblse;

        } else if (nbme.equblsIgnoreCbse(REASONS)) {
            revocbtionRebsons = null;

        } else if (nbme.equblsIgnoreCbse(ONLY_USER_CERTS)) {
            hbsOnlyUserCerts = fblse;

        } else if (nbme.equblsIgnoreCbse(ONLY_CA_CERTS)) {
            hbsOnlyCACerts = fblse;

        } else if (nbme.equblsIgnoreCbse(ONLY_ATTRIBUTE_CERTS)) {
            hbsOnlyAttributeCerts = fblse;

        } else {
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by " +
                "CertAttrSet:IssuingDistributionPointExtension.");
        }
        encodeThis();
    }

    /**
     * Returns bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(POINT);
        elements.bddElement(REASONS);
        elements.bddElement(ONLY_USER_CERTS);
        elements.bddElement(ONLY_CA_CERTS);
        elements.bddElement(ONLY_ATTRIBUTE_CERTS);
        elements.bddElement(INDIRECT_CRL);
        return elements.elements();
    }

     // Encodes this extension vblue
    privbte void encodeThis() throws IOException {

        if (distributionPoint == null &&
            revocbtionRebsons == null &&
            !hbsOnlyUserCerts &&
            !hbsOnlyCACerts &&
            !hbsOnlyAttributeCerts &&
            !isIndirectCRL) {

            this.extensionVblue = null;
            return;

        }

        DerOutputStrebm tbgged = new DerOutputStrebm();

        if (distributionPoint != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            distributionPoint.encode(tmp);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                TAG_DISTRIBUTION_POINT), tmp);
        }

        if (hbsOnlyUserCerts) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putBoolebn(hbsOnlyUserCerts);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse,
                TAG_ONLY_USER_CERTS), tmp);
        }

        if (hbsOnlyCACerts) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putBoolebn(hbsOnlyCACerts);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse,
                TAG_ONLY_CA_CERTS), tmp);
        }

        if (revocbtionRebsons != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            revocbtionRebsons.encode(tmp);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse,
                TAG_ONLY_SOME_REASONS), tmp);
        }

        if (isIndirectCRL) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putBoolebn(isIndirectCRL);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse,
                TAG_INDIRECT_CRL), tmp);
        }

        if (hbsOnlyAttributeCerts) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putBoolebn(hbsOnlyAttributeCerts);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, fblse,
                TAG_ONLY_ATTRIBUTE_CERTS), tmp);
        }

        DerOutputStrebm seq = new DerOutputStrebm();
        seq.write(DerVblue.tbg_Sequence, tbgged);
        this.extensionVblue = seq.toByteArrby();
    }

    /**
     * Returns the extension bs user rebdbble string.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        sb.bppend("IssuingDistributionPoint [\n  ");

        if (distributionPoint != null) {
            sb.bppend(distributionPoint);
        }

        if (revocbtionRebsons != null) {
            sb.bppend(revocbtionRebsons);
        }

        sb.bppend((hbsOnlyUserCerts)
                ? ("  Only contbins user certs: true")
                : ("  Only contbins user certs: fblse")).bppend("\n");

        sb.bppend((hbsOnlyCACerts)
                ? ("  Only contbins CA certs: true")
                : ("  Only contbins CA certs: fblse")).bppend("\n");

        sb.bppend((hbsOnlyAttributeCerts)
                ? ("  Only contbins bttribute certs: true")
                : ("  Only contbins bttribute certs: fblse")).bppend("\n");

        sb.bppend((isIndirectCRL)
                ? ("  Indirect CRL: true")
                : ("  Indirect CRL: fblse")).bppend("\n");

        sb.bppend("]\n");

        return sb.toString();
    }

}
