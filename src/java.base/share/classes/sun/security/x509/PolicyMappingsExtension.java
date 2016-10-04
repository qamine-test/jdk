/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.*;

/**
 * Represent the Policy Mbppings Extension.
 *
 * This extension, if present, identifies the certificbte policies considered
 * identicbl between the issuing bnd the subject CA.
 * <p>Extensions bre bddiitonbl bttributes which cbn be inserted in b X509
 * v3 certificbte. For exbmple b "Driving License Certificbte" could hbve
 * the driving license number bs b extension.
 *
 * <p>Extensions bre represented bs b sequence of the extension identifier
 * (Object Identifier), b boolebn flbg stbting whether the extension is to
 * be trebted bs being criticbl bnd the extension vblue itself (this is bgbin
 * b DER encoding of the extension vblue).
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss PolicyMbppingsExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.PolicyMbppings";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "PolicyMbppings";
    public stbtic finbl String MAP = "mbp";

    // Privbte dbtb members
    privbte List<CertificbtePolicyMbp> mbps;

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        if (mbps == null || mbps.isEmpty()) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm os = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        for (CertificbtePolicyMbp mbp : mbps) {
            mbp.encode(tmp);
        }

        os.write(DerVblue.tbg_Sequence, tmp);
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Crebte b PolicyMbppings with the List of CertificbtePolicyMbp.
     *
     * @pbrbm mbps the List of CertificbtePolicyMbp.
     */
    public PolicyMbppingsExtension(List<CertificbtePolicyMbp> mbp)
            throws IOException {
        this.mbps = mbp;
        this.extensionId = PKIXExtensions.PolicyMbppings_Id;
        this.criticbl = fblse;
        encodeThis();
    }

    /**
     * Crebte b defbult PolicyMbppingsExtension.
     */
    public PolicyMbppingsExtension() {
        extensionId = PKIXExtensions.KeyUsbge_Id;
        criticbl = fblse;
        mbps = new ArrbyList<CertificbtePolicyMbp>();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbms criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbms vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public PolicyMbppingsExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.PolicyMbppings_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                  "PolicyMbppingsExtension.");
        }
        mbps = new ArrbyList<CertificbtePolicyMbp>();
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue seq = vbl.dbtb.getDerVblue();
            CertificbtePolicyMbp mbp = new CertificbtePolicyMbp(seq);
            mbps.bdd(mbp);
        }
    }

    /**
     * Returns b printbble representbtion of the policy mbp.
     */
    public String toString() {
        if (mbps == null) return "";
        String s = super.toString() + "PolicyMbppings [\n"
                 + mbps.toString() + "]\n";

        return (s);
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
            extensionId = PKIXExtensions.PolicyMbppings_Id;
            criticbl = fblse;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    @SuppressWbrnings("unchecked") // Checked with instbnceof
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(MAP)) {
            if (!(obj instbnceof List)) {
              throw new IOException("Attribute vblue should be of" +
                                    " type List.");
            }
            mbps = (List<CertificbtePolicyMbp>)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:PolicyMbppingsExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public List<CertificbtePolicyMbp> get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(MAP)) {
            return (mbps);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:PolicyMbppingsExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(MAP)) {
            mbps = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:PolicyMbppingsExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements () {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(MAP);

        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme () {
        return (NAME);
    }
}
