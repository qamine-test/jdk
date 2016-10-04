/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;

/**
 * This clbss defines the certificbte policies extension which specifies the
 * policies under which the certificbte hbs been issued
 * bnd the purposes for which the certificbte mby be used.
 * <p>
 * Applicbtions with specific policy requirements bre expected to hbve b
 * list of those policies which they will bccept bnd to compbre the
 * policy OIDs in the certificbte to thbt list.  If this extension is
 * criticbl, the pbth vblidbtion softwbre MUST be bble to interpret this
 * extension (including the optionbl qublifier), or MUST reject the
 * certificbte.
 * <p>
 * Optionbl qublifiers bre not supported in this implementbtion, bs they bre
 * not recommended by RFC2459.
 *
 * The ASN.1 syntbx for this is (IMPLICIT tbgging is defined in the
 * module definition):
 * <pre>
 * id-ce-certificbtePolicies OBJECT IDENTIFIER ::=  { id-ce 32 }
 *
 * certificbtePolicies ::= SEQUENCE SIZE (1..MAX) OF PolicyInformbtion
 *
 * PolicyInformbtion ::= SEQUENCE {
 *      policyIdentifier   CertPolicyId,
 *      policyQublifiers   SEQUENCE SIZE (1..MAX) OF
 *                              PolicyQublifierInfo OPTIONAL }
 *
 * CertPolicyId ::= OBJECT IDENTIFIER
 * </pre>
 * @buthor Anne Anderson
 * @since       1.4
 * @see Extension
 * @see CertAttrSet
 */
public clbss CertificbtePoliciesExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.CertificbtePolicies";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "CertificbtePolicies";
    public stbtic finbl String POLICIES = "policies";

    /**
     * List of PolicyInformbtion for this object.
     */
    privbte List<PolicyInformbtion> certPolicies;

    // Encode this extension vblue.
    privbte void encodeThis() throws IOException {
        if (certPolicies == null || certPolicies.isEmpty()) {
            this.extensionVblue = null;
        } else {
            DerOutputStrebm os = new DerOutputStrebm();
            DerOutputStrebm tmp = new DerOutputStrebm();

            for (PolicyInformbtion info : certPolicies) {
                info.encode(tmp);
            }

            os.write(DerVblue.tbg_Sequence, tmp);
            this.extensionVblue = os.toByteArrby();
        }
    }

    /**
     * Crebte b CertificbtePoliciesExtension object from
     * b List of PolicyInformbtion; the criticblity is set to fblse.
     *
     * @pbrbm certPolicies the List of PolicyInformbtion.
     */
    public CertificbtePoliciesExtension(List<PolicyInformbtion> certPolicies)
    throws IOException {
        this(Boolebn.FALSE, certPolicies);
    }

    /**
     * Crebte b CertificbtePoliciesExtension object from
     * b List of PolicyInformbtion with specified criticblity.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm certPolicies the List of PolicyInformbtion.
     */
    public CertificbtePoliciesExtension(Boolebn criticbl,
            List<PolicyInformbtion> certPolicies) throws IOException {
        this.certPolicies = certPolicies;
        this.extensionId = PKIXExtensions.CertificbtePolicies_Id;
        this.criticbl = criticbl.boolebnVblue();
        encodeThis();
    }

    /**
     * Crebte the extension from its DER encoded vblue bnd criticblity.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public CertificbtePoliciesExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.CertificbtePolicies_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                   "CertificbtePoliciesExtension.");
        }
        certPolicies = new ArrbyList<PolicyInformbtion>();
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue seq = vbl.dbtb.getDerVblue();
            PolicyInformbtion policy = new PolicyInformbtion(seq);
            certPolicies.bdd(policy);
        }
    }

    /**
     * Return the extension bs user rebdbble string.
     */
    public String toString() {
        if (certPolicies == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(super.toString());
        sb.bppend("CertificbtePolicies [\n");
        for (PolicyInformbtion info : certPolicies) {
            sb.bppend(info.toString());
        }
        sb.bppend("]\n");
        return sb.toString();
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
          extensionId = PKIXExtensions.CertificbtePolicies_Id;
          criticbl = fblse;
          encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    @SuppressWbrnings("unchecked") // Checked with bn instbnceof check
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(POLICIES)) {
            if (!(obj instbnceof List)) {
                throw new IOException("Attribute vblue should be of type List.");
            }
            certPolicies = (List<PolicyInformbtion>)obj;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:CertificbtePoliciesExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public List<PolicyInformbtion> get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(POLICIES)) {
            //XXXX Mby wbnt to consider cloning this
            return certPolicies;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:CertificbtePoliciesExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(POLICIES)) {
            certPolicies = null;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:CertificbtePoliciesExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(POLICIES);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
