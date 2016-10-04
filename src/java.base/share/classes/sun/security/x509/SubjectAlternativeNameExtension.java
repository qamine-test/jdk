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
 * This represents the Subject Alternbtive Nbme Extension.
 *
 * This extension, if present, bllows the subject to specify multiple
 * blternbtive nbmes.
 *
 * <p>Extensions bre represented bs b sequence of the extension identifier
 * (Object Identifier), b boolebn flbg stbting whether the extension is to
 * be trebted bs being criticbl bnd the extension vblue itself (this is bgbin
 * b DER encoding of the extension vblue).
 * <p>
 * The ASN.1 syntbx for this is:
 * <pre>
 * SubjectAltNbme ::= GenerblNbmes
 * GenerblNbmes ::= SEQUENCE SIZE (1..MAX) OF GenerblNbme
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss SubjectAlternbtiveNbmeExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                         "x509.info.extensions.SubjectAlternbtiveNbme";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "SubjectAlternbtiveNbme";
    public stbtic finbl String SUBJECT_NAME = "subject_nbme";

    // privbte dbtb members
    GenerblNbmes        nbmes = null;

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
     * Crebte b SubjectAlternbtiveNbmeExtension with the pbssed GenerblNbmes.
     * The extension is mbrked non-criticbl.
     *
     * @pbrbm nbmes the GenerblNbmes for the subject.
     * @exception IOException on error.
     */
    public SubjectAlternbtiveNbmeExtension(GenerblNbmes nbmes)
    throws IOException {
        this(Boolebn.FALSE, nbmes);
    }

    /**
     * Crebte b SubjectAlternbtiveNbmeExtension with the specified
     * criticblity bnd GenerblNbmes.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm nbmes the GenerblNbmes for the subject.
     * @exception IOException on error.
     */
    public SubjectAlternbtiveNbmeExtension(Boolebn criticbl, GenerblNbmes nbmes)
    throws IOException {
        this.nbmes = nbmes;
        this.extensionId = PKIXExtensions.SubjectAlternbtiveNbme_Id;
        this.criticbl = criticbl.boolebnVblue();
        encodeThis();
    }

    /**
     * Crebte b defbult SubjectAlternbtiveNbmeExtension. The extension
     * is mbrked non-criticbl.
     */
    public SubjectAlternbtiveNbmeExtension() {
        extensionId = PKIXExtensions.SubjectAlternbtiveNbme_Id;
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
    public SubjectAlternbtiveNbmeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.SubjectAlternbtiveNbme_Id;
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
     * Returns b printbble representbtion of the SubjectAlternbtiveNbme.
     */
    public String toString() {

        String result = super.toString() + "SubjectAlternbtiveNbme [\n";
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
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
            extensionId = PKIXExtensions.SubjectAlternbtiveNbme_Id;
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
        if (nbme.equblsIgnoreCbse(SUBJECT_NAME)) {
            if (!(obj instbnceof GenerblNbmes)) {
              throw new IOException("Attribute vblue should be of " +
                                    "type GenerblNbmes.");
            }
            nbmes = (GenerblNbmes)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:SubjectAlternbtiveNbme.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public GenerblNbmes get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(SUBJECT_NAME)) {
            return (nbmes);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:SubjectAlternbtiveNbme.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(SUBJECT_NAME)) {
            nbmes = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:SubjectAlternbtiveNbme.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(SUBJECT_NAME);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
