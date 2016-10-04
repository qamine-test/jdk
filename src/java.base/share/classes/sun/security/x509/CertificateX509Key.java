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

import jbvb.security.PublicKey;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss defines the X509Key bttribute for the Certificbte.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteX509Key implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.key";
    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "key";
    public stbtic finbl String KEY = "vblue";

    // Privbte dbtb member
    privbte PublicKey key;

    /**
     * Defbult constructor for the certificbte bttribute.
     *
     * @pbrbm key the X509Key
     */
    public CertificbteX509Key(PublicKey key) {
        this.key = key;
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the X509Key from.
     * @exception IOException on decoding errors.
     */
    public CertificbteX509Key(DerInputStrebm in) throws IOException {
        DerVblue vbl = in.getDerVblue();
        key = X509Key.pbrse(vbl);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed strebm.
     *
     * @pbrbm in the InputStrebm to rebd the X509Key from.
     * @exception IOException on decoding errors.
     */
    public CertificbteX509Key(InputStrebm in) throws IOException {
        DerVblue vbl = new DerVblue(in);
        key = X509Key.pbrse(vbl);
    }

    /**
     * Return the key bs printbble string.
     */
    public String toString() {
        if (key == null) return "";
        return(key.toString());
    }

    /**
     * Encode the key in DER form to the strebm.
     *
     * @pbrbm out the OutputStrebm to mbrshbl the contents to.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        tmp.write(key.getEncoded());

        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY)) {
            this.key = (PublicKey)obj;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteX509Key.");
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public PublicKey get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY)) {
            return(key);
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteX509Key.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
      if (nbme.equblsIgnoreCbse(KEY)) {
        key = null;
      } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteX509Key.");
      }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(KEY);

        return(elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return(NAME);
    }
}
