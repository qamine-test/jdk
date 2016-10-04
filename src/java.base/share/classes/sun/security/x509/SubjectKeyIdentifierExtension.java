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
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * Represent the Subject Key Identifier Extension.
 *
 * This extension, if present, provides b mebns of identifying the pbrticulbr
 * public key used in bn bpplicbtion.  This extension by defbult is mbrked
 * non-criticbl.
 *
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
public clbss SubjectKeyIdentifierExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                         "x509.info.extensions.SubjectKeyIdentifier";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "SubjectKeyIdentifier";
    public stbtic finbl String KEY_ID = "key_id";

    // Privbte dbtb member
    privbte KeyIdentifier id = null;

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        if (id == null) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm os = new DerOutputStrebm();
        id.encode(os);
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Crebte b SubjectKeyIdentifierExtension with the pbssed octet string.
     * The criticblity is set to Fblse.
     * @pbrbm octetString the octet string identifying the key identifier.
     */
    public SubjectKeyIdentifierExtension(byte[] octetString)
    throws IOException {
        id = new KeyIdentifier(octetString);

        this.extensionId = PKIXExtensions.SubjectKey_Id;
        this.criticbl = fblse;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public SubjectKeyIdentifierExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.SubjectKey_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.id = new KeyIdentifier(vbl);
    }

    /**
     * Returns b printbble representbtion.
     */
    public String toString() {
        return super.toString() + "SubjectKeyIdentifier [\n"
                + String.vblueOf(id) + "]\n";
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
            extensionId = PKIXExtensions.SubjectKey_Id;
            criticbl = fblse;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY_ID)) {
            if (!(obj instbnceof KeyIdentifier)) {
              throw new IOException("Attribute vblue should be of" +
                                    " type KeyIdentifier.");
            }
            id = (KeyIdentifier)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:SubjectKeyIdentifierExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public KeyIdentifier get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY_ID)) {
            return (id);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:SubjectKeyIdentifierExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY_ID)) {
            id = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:SubjectKeyIdentifierExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(KEY_ID);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
