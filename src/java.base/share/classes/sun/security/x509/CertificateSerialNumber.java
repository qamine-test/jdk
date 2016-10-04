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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss defines the SeriblNumber bttribute for the Certificbte.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteSeriblNumber implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.seriblNumber";

    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "seriblNumber";
    public stbtic finbl String NUMBER = "number";

    privbte SeriblNumber        seribl;

    /**
     * Defbult constructor for the certificbte bttribute.
     *
     * @pbrbm seribl the seribl number for the certificbte.
     */
    public CertificbteSeriblNumber(BigInteger num) {
      this.seribl = new SeriblNumber(num);
    }

    /**
     * Defbult constructor for the certificbte bttribute.
     *
     * @pbrbm seribl the seribl number for the certificbte.
     */
    public CertificbteSeriblNumber(int num) {
      this.seribl = new SeriblNumber(num);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the seribl number from.
     * @exception IOException on decoding errors.
     */
    public CertificbteSeriblNumber(DerInputStrebm in) throws IOException {
        seribl = new SeriblNumber(in);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed strebm.
     *
     * @pbrbm in the InputStrebm to rebd the seribl number from.
     * @exception IOException on decoding errors.
     */
    public CertificbteSeriblNumber(InputStrebm in) throws IOException {
        seribl = new SeriblNumber(in);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DerVblue.
     *
     * @pbrbm vbl the DER encoded vblue.
     * @exception IOException on decoding errors.
     */
    public CertificbteSeriblNumber(DerVblue vbl) throws IOException {
        seribl = new SeriblNumber(vbl);
    }

    /**
     * Return the seribl number bs user rebdbble string.
     */
    public String toString() {
        if (seribl == null) return "";
        return (seribl.toString());
    }

    /**
     * Encode the seribl number in DER form to the strebm.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        seribl.encode(tmp);

        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof SeriblNumber)) {
            throw new IOException("Attribute must be of type SeriblNumber.");
        }
        if (nbme.equblsIgnoreCbse(NUMBER)) {
            seribl = (SeriblNumber)obj;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:CertificbteSeriblNumber.");
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public SeriblNumber get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(NUMBER)) {
            return (seribl);
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:CertificbteSeriblNumber.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(NUMBER)) {
            seribl = null;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:CertificbteSeriblNumber.");
        }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(NUMBER);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
