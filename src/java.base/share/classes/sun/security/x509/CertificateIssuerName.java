/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.*;

/**
 * This clbss defines the X500Nbme bttribute for the Certificbte.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteIssuerNbme implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.issuer";
    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "issuer";
    public stbtic finbl String DN_NAME = "dnbme";

    // bccessor nbme for cbched X500Principbl only
    // do not bllow b set() of this vblue, do not bdvertise with getElements()
    public stbtic finbl String DN_PRINCIPAL = "x500principbl";

    // Privbte dbtb member
    privbte X500Nbme    dnNbme;

    // cbched X500Principbl version of the nbme
    privbte X500Principbl dnPrincipbl;

    /**
     * Defbult constructor for the certificbte bttribute.
     *
     * @pbrbm nbme the X500Nbme
     */
    public CertificbteIssuerNbme(X500Nbme nbme) {
        this.dnNbme = nbme;
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the X500Nbme from.
     * @exception IOException on decoding errors.
     */
    public CertificbteIssuerNbme(DerInputStrebm in) throws IOException {
        dnNbme = new X500Nbme(in);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed strebm.
     *
     * @pbrbm in the InputStrebm to rebd the X500Nbme from.
     * @exception IOException on decoding errors.
     */
    public CertificbteIssuerNbme(InputStrebm in) throws IOException {
        DerVblue derVbl = new DerVblue(in);
        dnNbme = new X500Nbme(derVbl);
    }

    /**
     * Return the nbme bs user rebdbble string.
     */
    public String toString() {
        if (dnNbme == null) return "";
        return(dnNbme.toString());
    }

    /**
     * Encode the nbme in DER form to the strebm.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        dnNbme.encode(tmp);

        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof X500Nbme)) {
            throw new IOException("Attribute must be of type X500Nbme.");
        }
        if (nbme.equblsIgnoreCbse(DN_NAME)) {
            this.dnNbme = (X500Nbme)obj;
            this.dnPrincipbl = null;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:CertificbteIssuerNbme.");
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DN_NAME)) {
            return(dnNbme);
        } else if (nbme.equblsIgnoreCbse(DN_PRINCIPAL)) {
            if ((dnPrincipbl == null) && (dnNbme != null)) {
                dnPrincipbl = dnNbme.bsX500Principbl();
            }
            return dnPrincipbl;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:CertificbteIssuerNbme.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DN_NAME)) {
            dnNbme = null;
            dnPrincipbl = null;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:CertificbteIssuerNbme.");
        }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(DN_NAME);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return(NAME);
    }
}
