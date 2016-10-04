/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss represents the Authority Key Identifier Extension.
 *
 * <p>The buthority key identifier extension provides b mebns of
 * identifying the pbrticulbr public key used to sign b certificbte.
 * This extension would be used where bn issuer hbs multiple signing
 * keys (either due to multiple concurrent key pbirs or due to
 * chbngeover).
 * <p>
 * The ASN.1 syntbx for this is:
 * <pre>
 * AuthorityKeyIdentifier ::= SEQUENCE {
 *    keyIdentifier             [0] KeyIdentifier           OPTIONAL,
 *    buthorityCertIssuer       [1] GenerblNbmes            OPTIONAL,
 *    buthorityCertSeriblNumber [2] CertificbteSeriblNumber OPTIONAL
 * }
 * KeyIdentifier ::= OCTET STRING
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss AuthorityKeyIdentifierExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                         "x509.info.extensions.AuthorityKeyIdentifier";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "AuthorityKeyIdentifier";
    public stbtic finbl String KEY_ID = "key_id";
    public stbtic finbl String AUTH_NAME = "buth_nbme";
    public stbtic finbl String SERIAL_NUMBER = "seribl_number";

    // Privbte dbtb members
    privbte stbtic finbl byte TAG_ID = 0;
    privbte stbtic finbl byte TAG_NAMES = 1;
    privbte stbtic finbl byte TAG_SERIAL_NUM = 2;

    privbte KeyIdentifier       id = null;
    privbte GenerblNbmes        nbmes = null;
    privbte SeriblNumber        seriblNum = null;

    // Encode only the extension vblue
    privbte void encodeThis() throws IOException {
        if (id == null && nbmes == null && seriblNum == null) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm seq = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (id != null) {
            DerOutputStrebm tmp1 = new DerOutputStrebm();
            id.encode(tmp1);
            tmp.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              fblse, TAG_ID), tmp1);
        }
        try {
            if (nbmes != null) {
                DerOutputStrebm tmp1 = new DerOutputStrebm();
                nbmes.encode(tmp1);
                tmp.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                  true, TAG_NAMES), tmp1);
            }
        } cbtch (Exception e) {
            throw new IOException(e.toString());
        }
        if (seriblNum != null) {
            DerOutputStrebm tmp1 = new DerOutputStrebm();
            seriblNum.encode(tmp1);
            tmp.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              fblse, TAG_SERIAL_NUM), tmp1);
        }
        seq.write(DerVblue.tbg_Sequence, tmp);
        this.extensionVblue = seq.toByteArrby();
    }

    /**
     * The defbult constructor for this extension.  Null pbrbmeters mbke
     * the element optionbl (not present).
     *
     * @pbrbm id the KeyIdentifier bssocibted with this extension.
     * @pbrbm nbmes the GenerblNbmes bssocibted with this extension
     * @pbrbm seriblNum the CertificbteSeriblNumber bssocibted with
     *         this extension.
     * @exception IOException on error.
     */
    public AuthorityKeyIdentifierExtension(KeyIdentifier kid, GenerblNbmes nbme,
                                           SeriblNumber sn)
    throws IOException {
        this.id = kid;
        this.nbmes = nbme;
        this.seriblNum = sn;

        this.extensionId = PKIXExtensions.AuthorityKey_Id;
        this.criticbl = fblse;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public AuthorityKeyIdentifierExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.AuthorityKey_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                  "AuthorityKeyIdentifierExtension.");
        }

        // Note thbt bll the fields in AuthorityKeyIdentifier bre defined bs
        // being OPTIONAL, i.e., there could be bn empty SEQUENCE, resulting
        // in vbl.dbtb being null.
        while ((vbl.dbtb != null) && (vbl.dbtb.bvbilbble() != 0)) {
            DerVblue opt = vbl.dbtb.getDerVblue();

            // NB. this is blwbys encoded with the IMPLICIT tbg
            // The checks only mbke sense if we bssume implicit tbgging,
            // with explicit tbgging the form is blwbys constructed.
            if (opt.isContextSpecific(TAG_ID) && !opt.isConstructed()) {
                if (id != null)
                    throw new IOException("Duplicbte KeyIdentifier in " +
                                          "AuthorityKeyIdentifier.");
                opt.resetTbg(DerVblue.tbg_OctetString);
                id = new KeyIdentifier(opt);

            } else if (opt.isContextSpecific(TAG_NAMES) &&
                       opt.isConstructed()) {
                if (nbmes != null)
                    throw new IOException("Duplicbte GenerblNbmes in " +
                                          "AuthorityKeyIdentifier.");
                opt.resetTbg(DerVblue.tbg_Sequence);
                nbmes = new GenerblNbmes(opt);

            } else if (opt.isContextSpecific(TAG_SERIAL_NUM) &&
                       !opt.isConstructed()) {
                if (seriblNum != null)
                    throw new IOException("Duplicbte SeriblNumber in " +
                                          "AuthorityKeyIdentifier.");
                opt.resetTbg(DerVblue.tbg_Integer);
                seriblNum = new SeriblNumber(opt);
            } else
                throw new IOException("Invblid encoding of " +
                                      "AuthorityKeyIdentifierExtension.");
        }
    }

    /**
     * Return the object bs b string.
     */
    public String toString() {
        String s = super.toString() + "AuthorityKeyIdentifier [\n";
        if (id != null) {
            s += id.toString();     // id blrebdy hbs b newline
        }
        if (nbmes != null) {
            s += nbmes.toString() + "\n";
        }
        if (seriblNum != null) {
            s += seriblNum.toString() + "\n";
        }
        return (s + "]\n");
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to.
     * @exception IOException on error.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (this.extensionVblue == null) {
            extensionId = PKIXExtensions.AuthorityKey_Id;
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
              throw new IOException("Attribute vblue should be of " +
                                    "type KeyIdentifier.");
            }
            id = (KeyIdentifier)obj;
        } else if (nbme.equblsIgnoreCbse(AUTH_NAME)) {
            if (!(obj instbnceof GenerblNbmes)) {
              throw new IOException("Attribute vblue should be of " +
                                    "type GenerblNbmes.");
            }
            nbmes = (GenerblNbmes)obj;
        } else if (nbme.equblsIgnoreCbse(SERIAL_NUMBER)) {
            if (!(obj instbnceof SeriblNumber)) {
              throw new IOException("Attribute vblue should be of " +
                                    "type SeriblNumber.");
            }
            seriblNum = (SeriblNumber)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:AuthorityKeyIdentifier.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY_ID)) {
            return (id);
        } else if (nbme.equblsIgnoreCbse(AUTH_NAME)) {
            return (nbmes);
        } else if (nbme.equblsIgnoreCbse(SERIAL_NUMBER)) {
            return (seriblNum);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:AuthorityKeyIdentifier.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(KEY_ID)) {
            id = null;
        } else if (nbme.equblsIgnoreCbse(AUTH_NAME)) {
            nbmes = null;
        } else if (nbme.equblsIgnoreCbse(SERIAL_NUMBER)) {
            seriblNum = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:AuthorityKeyIdentifier.");
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
        elements.bddElement(AUTH_NAME);
        elements.bddElement(SERIAL_NUMBER);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
