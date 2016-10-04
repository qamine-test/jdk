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
import jbvb.security.cert.PolicyQublifierInfo;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshSet;
import jbvb.util.Set;

import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;
/**
 * PolicyInformbtion is the clbss thbt contbins b specific certificbte policy
 * thbt is pbrt of the CertificbtePoliciesExtension. A
 * CertificbtePolicyExtension vblue consists of b vector of these objects.
 * <p>
 * The ASN.1 syntbx for PolicyInformbtion (IMPLICIT tbgging is defined in the
 * module definition):
 * <pre>
 *
 * PolicyInformbtion ::= SEQUENCE {
 *      policyIdentifier   CertPolicyId,
 *      policyQublifiers   SEQUENCE SIZE (1..MAX) OF
 *                              PolicyQublifierInfo OPTIONAL }
 *
 * CertPolicyId ::= OBJECT IDENTIFIER
 *
 * PolicyQublifierInfo ::= SEQUENCE {
 *      policyQublifierId  PolicyQublifierId,
 *      qublifier          ANY DEFINED BY policyQublifierId }
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor Anne Anderson
 * @since       1.4
 */
public clbss PolicyInformbtion {

    // Attribute nbmes
    public stbtic finbl String NAME       = "PolicyInformbtion";
    public stbtic finbl String ID         = "id";
    public stbtic finbl String QUALIFIERS = "qublifiers";

    /* The policy OID */
    privbte CertificbtePolicyId policyIdentifier;

    /* A Set of jbvb.security.cert.PolicyQublifierInfo objects */
    privbte Set<PolicyQublifierInfo> policyQublifiers;

    /**
     * Crebte bn instbnce of PolicyInformbtion
     *
     * @pbrbm policyIdentifier the policyIdentifier bs b
     *          CertificbtePolicyId
     * @pbrbm policyQublifiers b Set of PolicyQublifierInfo objects.
     *          Must not be NULL. Specify bn empty Set for no qublifiers.
     * @exception IOException on decoding errors.
     */
    public PolicyInformbtion(CertificbtePolicyId policyIdentifier,
            Set<PolicyQublifierInfo> policyQublifiers) throws IOException {
        if (policyQublifiers == null) {
            throw new NullPointerException("policyQublifiers is null");
        }
        this.policyQublifiers =
            new LinkedHbshSet<PolicyQublifierInfo>(policyQublifiers);
        this.policyIdentifier = policyIdentifier;
    }

    /**
     * Crebte bn instbnce of PolicyInformbtion, decoding from
     * the pbssed DerVblue.
     *
     * @pbrbm vbl the DerVblue to construct the PolicyInformbtion from.
     * @exception IOException on decoding errors.
     */
    public PolicyInformbtion(DerVblue vbl) throws IOException {
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding of PolicyInformbtion");
        }
        policyIdentifier = new CertificbtePolicyId(vbl.dbtb.getDerVblue());
        if (vbl.dbtb.bvbilbble() != 0) {
            policyQublifiers = new LinkedHbshSet<PolicyQublifierInfo>();
            DerVblue opt = vbl.dbtb.getDerVblue();
            if (opt.tbg != DerVblue.tbg_Sequence)
                throw new IOException("Invblid encoding of PolicyInformbtion");
            if (opt.dbtb.bvbilbble() == 0)
                throw new IOException("No dbtb bvbilbble in policyQublifiers");
            while (opt.dbtb.bvbilbble() != 0)
                policyQublifiers.bdd(new PolicyQublifierInfo
                        (opt.dbtb.getDerVblue().toByteArrby()));
        } else {
            policyQublifiers = Collections.emptySet();
        }
    }

    /**
     * Compbre this PolicyInformbtion with bnother object for equblity
     *
     * @pbrbm other object to be compbred with this
     * @return true iff the PolicyInformbtion objects mbtch
     */
    public boolebn equbls(Object other) {
        if (!(other instbnceof PolicyInformbtion))
            return fblse;
        PolicyInformbtion piOther = (PolicyInformbtion)other;

        if (!policyIdentifier.equbls(piOther.getPolicyIdentifier()))
            return fblse;

        return policyQublifiers.equbls(piOther.getPolicyQublifiers());
    }

    /**
     * Returns the hbsh code for this PolicyInformbtion.
     *
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        int myhbsh = 37 + policyIdentifier.hbshCode();
        myhbsh = 37 * myhbsh + policyQublifiers.hbshCode();
        return myhbsh;
    }

    /**
     * Return the policyIdentifier vblue
     *
     * @return The CertificbtePolicyId object contbining
     *     the policyIdentifier (not b copy).
     */
    public CertificbtePolicyId getPolicyIdentifier() {
        return policyIdentifier;
    }

    /**
     * Return the policyQublifiers vblue
     *
     * @return b Set of PolicyQublifierInfo objects bssocibted
     *    with this certificbte policy (not b copy).
     *    Returns bn empty Set if there bre no qublifiers.
     *    Never returns null.
     */
    public Set<PolicyQublifierInfo> getPolicyQublifiers() {
        return policyQublifiers;
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(ID)) {
            return policyIdentifier;
        } else if (nbme.equblsIgnoreCbse(QUALIFIERS)) {
            return policyQublifiers;
        } else {
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by PolicyInformbtion.");
        }
    }

    /**
     * Set the bttribute vblue.
     */
    @SuppressWbrnings("unchecked") // Checked with instbnceof
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(ID)) {
            if (obj instbnceof CertificbtePolicyId)
                policyIdentifier = (CertificbtePolicyId)obj;
            else
                throw new IOException("Attribute vblue must be instbnce " +
                    "of CertificbtePolicyId.");
        } else if (nbme.equblsIgnoreCbse(QUALIFIERS)) {
            if (policyIdentifier == null) {
                throw new IOException("Attribute must hbve b " +
                    "CertificbtePolicyIdentifier vblue before " +
                    "PolicyQublifierInfo cbn be set.");
            }
            if (obj instbnceof Set) {
                Iterbtor<?> i = ((Set<?>)obj).iterbtor();
                while (i.hbsNext()) {
                    Object obj1 = i.next();
                    if (!(obj1 instbnceof PolicyQublifierInfo)) {
                        throw new IOException("Attribute vblue must be b" +
                                    "Set of PolicyQublifierInfo objects.");
                    }
                }
                policyQublifiers = (Set<PolicyQublifierInfo>) obj;
            } else {
                throw new IOException("Attribute vblue must be of type Set.");
            }
        } else {
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by PolicyInformbtion");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(QUALIFIERS)) {
            policyQublifiers = Collections.emptySet();
        } else if (nbme.equblsIgnoreCbse(ID)) {
            throw new IOException("Attribute ID mby not be deleted from " +
                "PolicyInformbtion.");
        } else {
            //ID mby not be deleted
            throw new IOException("Attribute nbme [" + nbme +
                "] not recognized by PolicyInformbtion.");
        }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(ID);
        elements.bddElement(QUALIFIERS);

        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    /**
     * Return b printbble representbtion of the PolicyInformbtion.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("  [" + policyIdentifier.toString());
        s.bppend(policyQublifiers + "  ]\n");
        return s.toString();
    }

    /**
     * Write the PolicyInformbtion to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        policyIdentifier.encode(tmp);
        if (!policyQublifiers.isEmpty()) {
            DerOutputStrebm tmp2 = new DerOutputStrebm();
            for (PolicyQublifierInfo pq : policyQublifiers) {
                tmp2.write(pq.getEncoded());
            }
            tmp.write(DerVblue.tbg_Sequence, tmp2);
        }
        out.write(DerVblue.tbg_Sequence, tmp);
    }
}
