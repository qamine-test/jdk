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
 * This represents the Issuer Alternbtive Nbme Extension.
 *
 * This extension, if present, bllows the issuer to specify multiple
 * blternbtive nbmes.
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
public clbss IssuerAlternbtiveNbmeExtension
extends Extension implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                         "x509.info.extensions.IssuerAlternbtiveNbme";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "IssuerAlternbtiveNbme";
    public stbtic finbl String ISSUER_NAME = "issuer_nbme";

    // privbte dbtb members
    GenerblNbmes nbmes = null;

    // Encode this extension
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
     * Crebte b IssuerAlternbtiveNbmeExtension with the pbssed GenerblNbmes.
     *
     * @pbrbm nbmes the GenerblNbmes for the issuer.
     * @exception IOException on error.
     */
    public IssuerAlternbtiveNbmeExtension(GenerblNbmes nbmes)
    throws IOException {
        this.nbmes = nbmes;
        this.extensionId = PKIXExtensions.IssuerAlternbtiveNbme_Id;
        this.criticbl = fblse;
        encodeThis();
    }

    /**
     * Crebte b IssuerAlternbtiveNbmeExtension with the pbssed criticblity
     * bnd GenerblNbmes.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm nbmes the GenerblNbmes for the issuer.
     * @exception IOException on error.
     */
    public IssuerAlternbtiveNbmeExtension(Boolebn criticbl, GenerblNbmes nbmes)
    throws IOException {
        this.nbmes = nbmes;
        this.extensionId = PKIXExtensions.IssuerAlternbtiveNbme_Id;
        this.criticbl = criticbl.boolebnVblue();
        encodeThis();
    }

    /**
     * Crebte b defbult IssuerAlternbtiveNbmeExtension.
     */
    public IssuerAlternbtiveNbmeExtension() {
        extensionId = PKIXExtensions.IssuerAlternbtiveNbme_Id;
        criticbl = fblse;
        nbmes = new GenerblNbmes();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public IssuerAlternbtiveNbmeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.IssuerAlternbtiveNbme_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.dbtb == null) {
            nbmes = new GenerblNbmes();
            return;
        }

        nbmes = new GenerblNbmes(vbl);
    }

    /**
     * Returns b printbble representbtion of the IssuerAlternbtiveNbme.
     */
    public String toString() {

        String result = super.toString() + "IssuerAlternbtiveNbme [\n";
        if(nbmes == null) {
            result += "  null\n";
        } else {
            for(GenerblNbme nbme: nbmes.nbmes()) {
                result += "  "+nbme+"\n";
            }
        }
        result += "]\n";
        return result;
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to.
     * @exception IOException on encoding error.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
            extensionId = PKIXExtensions.IssuerAlternbtiveNbme_Id;
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
        if (nbme.equblsIgnoreCbse(ISSUER_NAME)) {
            if (!(obj instbnceof GenerblNbmes)) {
              throw new IOException("Attribute vblue should be of" +
                                    " type GenerblNbmes.");
            }
            nbmes = (GenerblNbmes)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:IssuerAlternbtiveNbme.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public GenerblNbmes get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(ISSUER_NAME)) {
            return (nbmes);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:IssuerAlternbtiveNbme.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(ISSUER_NAME)) {
            nbmes = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:IssuerAlternbtiveNbme.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(ISSUER_NAME);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
