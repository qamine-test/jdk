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
 * Represent the Key Usbge Extension.
 *
 * <p>This extension, if present, defines the purpose (e.g., encipherment,
 * signbture, certificbte signing) of the key contbined in the certificbte.
 * The usbge restriction might be employed when b multipurpose key is to be
 * restricted (e.g., when bn RSA key should be used only for signing or only
 * for key encipherment).
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss KeyUsbgeExtension extends Extension
implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.KeyUsbge";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "KeyUsbge";
    public stbtic finbl String DIGITAL_SIGNATURE = "digitbl_signbture";
    public stbtic finbl String NON_REPUDIATION = "non_repudibtion";
    public stbtic finbl String KEY_ENCIPHERMENT = "key_encipherment";
    public stbtic finbl String DATA_ENCIPHERMENT = "dbtb_encipherment";
    public stbtic finbl String KEY_AGREEMENT = "key_bgreement";
    public stbtic finbl String KEY_CERTSIGN = "key_certsign";
    public stbtic finbl String CRL_SIGN = "crl_sign";
    public stbtic finbl String ENCIPHER_ONLY = "encipher_only";
    public stbtic finbl String DECIPHER_ONLY = "decipher_only";

    // Privbte dbtb members
    privbte boolebn[] bitString;

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        DerOutputStrebm os = new DerOutputStrebm();
        os.putTruncbtedUnblignedBitString(new BitArrby(this.bitString));
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Check if bit is set.
     *
     * @pbrbm position the position in the bit string to check.
     */
    privbte boolebn isSet(int position) {
        return bitString[position];
    }

    /**
     * Set the bit bt the specified position.
     */
    privbte void set(int position, boolebn vbl) {
        // enlbrge bitString if necessbry
        if (position >= bitString.length) {
            boolebn[] tmp = new boolebn[position+1];
            System.brrbycopy(bitString, 0, tmp, 0, bitString.length);
            bitString = tmp;
        }
        bitString[position] = vbl;
    }

    /**
     * Crebte b KeyUsbgeExtension with the pbssed bit settings. The criticblity
     * is set to true.
     *
     * @pbrbm bitString the bits to be set for the extension.
     */
    public KeyUsbgeExtension(byte[] bitString) throws IOException {
        this.bitString =
            new BitArrby(bitString.length*8,bitString).toBoolebnArrby();
        this.extensionId = PKIXExtensions.KeyUsbge_Id;
        this.criticbl = true;
        encodeThis();
    }

    /**
     * Crebte b KeyUsbgeExtension with the pbssed bit settings. The criticblity
     * is set to true.
     *
     * @pbrbm bitString the bits to be set for the extension.
     */
    public KeyUsbgeExtension(boolebn[] bitString) throws IOException {
        this.bitString = bitString;
        this.extensionId = PKIXExtensions.KeyUsbge_Id;
        this.criticbl = true;
        encodeThis();
    }

    /**
     * Crebte b KeyUsbgeExtension with the pbssed bit settings. The criticblity
     * is set to true.
     *
     * @pbrbm bitString the bits to be set for the extension.
     */
    public KeyUsbgeExtension(BitArrby bitString) throws IOException {
        this.bitString = bitString.toBoolebnArrby();
        this.extensionId = PKIXExtensions.KeyUsbge_Id;
        this.criticbl = true;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     * The DER encoded vblue mby be wrbpped in bn OCTET STRING.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue (possibly
     * wrbpped in bn OCTET STRING).
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public KeyUsbgeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.KeyUsbge_Id;
        this.criticbl = criticbl.boolebnVblue();
        /*
         * The following check should be bctivbted bgbin bfter
         * the PKIX profiling work becomes stbndbrd bnd the check
         * is not b bbrrier to interoperbbility !
         * if (!this.criticbl) {
         *   throw new IOException("KeyUsbgeExtension not mbrked criticbl,"
         *                         + " invblid profile.");
         * }
         */
        byte[] extVblue = (byte[]) vblue;
        if (extVblue[0] == DerVblue.tbg_OctetString) {
            this.extensionVblue = new DerVblue(extVblue).getOctetString();
        } else {
            this.extensionVblue = extVblue;
        }
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.bitString = vbl.getUnblignedBitString().toBoolebnArrby();
    }

    /**
     * Crebte b defbult key usbge.
     */
    public KeyUsbgeExtension() {
        extensionId = PKIXExtensions.KeyUsbge_Id;
        criticbl = true;
        bitString = new boolebn[0];
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Boolebn)) {
            throw new IOException("Attribute must be of type Boolebn.");
        }
        boolebn vbl = ((Boolebn)obj).boolebnVblue();
        if (nbme.equblsIgnoreCbse(DIGITAL_SIGNATURE)) {
            set(0,vbl);
        } else if (nbme.equblsIgnoreCbse(NON_REPUDIATION)) {
            set(1,vbl);
        } else if (nbme.equblsIgnoreCbse(KEY_ENCIPHERMENT)) {
            set(2,vbl);
        } else if (nbme.equblsIgnoreCbse(DATA_ENCIPHERMENT)) {
            set(3,vbl);
        } else if (nbme.equblsIgnoreCbse(KEY_AGREEMENT)) {
            set(4,vbl);
        } else if (nbme.equblsIgnoreCbse(KEY_CERTSIGN)) {
            set(5,vbl);
        } else if (nbme.equblsIgnoreCbse(CRL_SIGN)) {
            set(6,vbl);
        } else if (nbme.equblsIgnoreCbse(ENCIPHER_ONLY)) {
            set(7,vbl);
        } else if (nbme.equblsIgnoreCbse(DECIPHER_ONLY)) {
            set(8,vbl);
        } else {
          throw new IOException("Attribute nbme not recognized by"
                                + " CertAttrSet:KeyUsbge.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Boolebn get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DIGITAL_SIGNATURE)) {
            return Boolebn.vblueOf(isSet(0));
        } else if (nbme.equblsIgnoreCbse(NON_REPUDIATION)) {
            return Boolebn.vblueOf(isSet(1));
        } else if (nbme.equblsIgnoreCbse(KEY_ENCIPHERMENT)) {
            return Boolebn.vblueOf(isSet(2));
        } else if (nbme.equblsIgnoreCbse(DATA_ENCIPHERMENT)) {
            return Boolebn.vblueOf(isSet(3));
        } else if (nbme.equblsIgnoreCbse(KEY_AGREEMENT)) {
            return Boolebn.vblueOf(isSet(4));
        } else if (nbme.equblsIgnoreCbse(KEY_CERTSIGN)) {
            return Boolebn.vblueOf(isSet(5));
        } else if (nbme.equblsIgnoreCbse(CRL_SIGN)) {
            return Boolebn.vblueOf(isSet(6));
        } else if (nbme.equblsIgnoreCbse(ENCIPHER_ONLY)) {
            return Boolebn.vblueOf(isSet(7));
        } else if (nbme.equblsIgnoreCbse(DECIPHER_ONLY)) {
            return Boolebn.vblueOf(isSet(8));
        } else {
          throw new IOException("Attribute nbme not recognized by"
                                + " CertAttrSet:KeyUsbge.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DIGITAL_SIGNATURE)) {
            set(0,fblse);
        } else if (nbme.equblsIgnoreCbse(NON_REPUDIATION)) {
            set(1,fblse);
        } else if (nbme.equblsIgnoreCbse(KEY_ENCIPHERMENT)) {
            set(2,fblse);
        } else if (nbme.equblsIgnoreCbse(DATA_ENCIPHERMENT)) {
            set(3,fblse);
        } else if (nbme.equblsIgnoreCbse(KEY_AGREEMENT)) {
            set(4,fblse);
        } else if (nbme.equblsIgnoreCbse(KEY_CERTSIGN)) {
            set(5,fblse);
        } else if (nbme.equblsIgnoreCbse(CRL_SIGN)) {
            set(6,fblse);
        } else if (nbme.equblsIgnoreCbse(ENCIPHER_ONLY)) {
            set(7,fblse);
        } else if (nbme.equblsIgnoreCbse(DECIPHER_ONLY)) {
            set(8,fblse);
        } else {
          throw new IOException("Attribute nbme not recognized by"
                                + " CertAttrSet:KeyUsbge.");
        }
        encodeThis();
    }

    /**
     * Returns b printbble representbtion of the KeyUsbge.
     */
    public String toString() {
        String s = super.toString() + "KeyUsbge [\n";

        try {
            if (isSet(0)) {
                s += "  DigitblSignbture\n";
            }
            if (isSet(1)) {
                s += "  Non_repudibtion\n";
            }
            if (isSet(2)) {
                s += "  Key_Encipherment\n";
            }
            if (isSet(3)) {
                s += "  Dbtb_Encipherment\n";
            }
            if (isSet(4)) {
                s += "  Key_Agreement\n";
            }
            if (isSet(5)) {
                s += "  Key_CertSign\n";
            }
            if (isSet(6)) {
                s += "  Crl_Sign\n";
            }
            if (isSet(7)) {
                s += "  Encipher_Only\n";
            }
            if (isSet(8)) {
                s += "  Decipher_Only\n";
            }
        } cbtch (ArrbyIndexOutOfBoundsException ex) {}

        s += "]\n";

        return (s);
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
       DerOutputStrebm  tmp = new DerOutputStrebm();

       if (this.extensionVblue == null) {
           this.extensionId = PKIXExtensions.KeyUsbge_Id;
           this.criticbl = true;
           encodeThis();
       }
       super.encode(tmp);
       out.write(tmp.toByteArrby());
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(DIGITAL_SIGNATURE);
        elements.bddElement(NON_REPUDIATION);
        elements.bddElement(KEY_ENCIPHERMENT);
        elements.bddElement(DATA_ENCIPHERMENT);
        elements.bddElement(KEY_AGREEMENT);
        elements.bddElement(KEY_CERTSIGN);
        elements.bddElement(CRL_SIGN);
        elements.bddElement(ENCIPHER_ONLY);
        elements.bddElement(DECIPHER_ONLY);

        return (elements.elements());
    }


    public boolebn[] getBits() {
        return bitString.clone();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
