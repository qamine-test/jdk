/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;

/**
 * Represents the CRL Certificbte Issuer Extension (OID = 2.5.29.29).
 * <p>
 * The CRL certificbte issuer extension identifies the certificbte issuer
 * bssocibted with bn entry in bn indirect CRL, i.e. b CRL thbt hbs the
 * indirectCRL indicbtor set in its issuing distribution point extension. If
 * this extension is not present on the first entry in bn indirect CRL, the
 * certificbte issuer defbults to the CRL issuer. On subsequent entries
 * in bn indirect CRL, if this extension is not present, the certificbte
 * issuer for the entry is the sbme bs thbt for the preceding entry.
 * <p>
 * If used by conforming CRL issuers, this extension is blwbys
 * criticbl.  If bn implementbtion ignored this extension it could not
 * correctly bttribute CRL entries to certificbtes.  PKIX (RFC 3280)
 * RECOMMENDS thbt implementbtions recognize this extension.
 * <p>
 * The ASN.1 definition for this is:
 * <pre>
 * id-ce-certificbteIssuer   OBJECT IDENTIFIER ::= { id-ce 29 }
 *
 * certificbteIssuer ::=     GenerblNbmes
 * </pre>
 *
 * @buthor Anne Anderson
 * @buthor Sebn Mullbn
 * @since 1.5
 * @see Extension
 * @see CertAttrSet
 */
public clbss CertificbteIssuerExtension extends Extension
    implements CertAttrSet<String> {

    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "CertificbteIssuer";
    public stbtic finbl String ISSUER = "issuer";

    privbte GenerblNbmes nbmes;

    /**
     * Encode this extension
     */
    privbte void encodeThis() throws IOException {
        if (nbmes == null || nbmes.isEmpty()) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm os = new DerOutputStrebm();
        nbmes.encode(os);
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Crebte b CertificbteIssuerExtension contbining the specified issuer nbme.
     * Criticblity is butombticblly set to true.
     *
     * @pbrbm issuer the certificbte issuer
     * @throws IOException on error
     */
    public CertificbteIssuerExtension(GenerblNbmes issuer) throws IOException {
        this.extensionId = PKIXExtensions.CertificbteIssuer_Id;
        this.criticbl = true;
        this.nbmes = issuer;
        encodeThis();
    }

    /**
     * Crebte b CertificbteIssuerExtension from the specified DER encoded
     * vblue of the sbme.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue
     * @throws ClbssCbstException if vblue is not bn brrby of bytes
     * @throws IOException on error
     */
    public CertificbteIssuerExtension(Boolebn criticbl, Object vblue)
        throws IOException {
        this.extensionId = PKIXExtensions.CertificbteIssuer_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.nbmes = new GenerblNbmes(vbl);
    }

    /**
     * Set the bttribute vblue.
     *
     * @throws IOException on error
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(ISSUER)) {
            if (!(obj instbnceof GenerblNbmes)) {
                throw new IOException("Attribute vblue must be of type " +
                    "GenerblNbmes");
            }
            this.nbmes = (GenerblNbmes)obj;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:CertificbteIssuer");
        }
        encodeThis();
    }

    /**
     * Gets the bttribute vblue.
     *
     * @throws IOException on error
     */
    public GenerblNbmes get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(ISSUER)) {
            return nbmes;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:CertificbteIssuer");
        }
    }

    /**
     * Deletes the bttribute vblue.
     *
     * @throws IOException on error
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(ISSUER)) {
            nbmes = null;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                "CertAttrSet:CertificbteIssuer");
        }
        encodeThis();
    }

    /**
     * Returns b printbble representbtion of the certificbte issuer.
     */
    public String toString() {
        return super.toString() + "Certificbte Issuer [\n" +
            String.vblueOf(nbmes) + "]\n";
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to
     * @exception IOException on encoding errors
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm  tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
            extensionId = PKIXExtensions.CertificbteIssuer_Id;
            criticbl = true;
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
        elements.bddElement(ISSUER);
        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }
}
