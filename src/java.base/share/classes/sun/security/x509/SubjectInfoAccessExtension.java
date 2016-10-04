/*
 * Copyright (c) 2009, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

/**
 * The Subject Informbtion Access Extension (OID = 1.3.6.1.5.5.7.1.11).
 * <p>
 * The subject informbtion bccess extension indicbtes how to bccess
 * informbtion bnd services for the subject of the certificbte in which
 * the extension bppebrs.  When the subject is b CA, informbtion bnd
 * services mby include certificbte vblidbtion services bnd CA policy
 * dbtb.  When the subject is bn end entity, the informbtion describes
 * the type of services offered bnd how to bccess them.  In this cbse,
 * the contents of this extension bre defined in the protocol
 * specificbtions for the supported services.  This extension mby be
 * included in end entity or CA certificbtes.  Conforming CAs MUST mbrk
 * this extension bs non-criticbl.
 * <p>
 * This extension is defined in <b href="http://www.ietf.org/rfc/rfc3280.txt">
 * Internet X.509 PKI Certificbte bnd Certificbte Revocbtion List
 * (CRL) Profile</b>. The profile permits
 * the extension to be included in end-entity or CA certificbtes,
 * bnd it must be mbrked bs non-criticbl. Its ASN.1 definition is bs follows:
 * <pre>
 *   id-pe-subjectInfoAccess OBJECT IDENTIFIER ::= { id-pe 11 }
 *
 *   SubjectInfoAccessSyntbx  ::=
 *          SEQUENCE SIZE (1..MAX) OF AccessDescription
 *
 *   AccessDescription  ::=  SEQUENCE {
 *          bccessMethod          OBJECT IDENTIFIER,
 *          bccessLocbtion        GenerblNbme  }
 * </pre>
 * <p>
 * @see Extension
 * @see CertAttrSet
 */

public clbss SubjectInfoAccessExtension extends Extension
        implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                                "x509.info.extensions.SubjectInfoAccess";

    /**
     * Attribute nbme.
     */
    public stbtic finbl String NAME = "SubjectInfoAccess";
    public stbtic finbl String DESCRIPTIONS = "descriptions";

    /**
     * The List of AccessDescription objects.
     */
    privbte List<AccessDescription> bccessDescriptions;

    /**
     * Crebte bn SubjectInfoAccessExtension from b List of
     * AccessDescription; the criticblity is set to fblse.
     *
     * @pbrbm bccessDescriptions the List of AccessDescription
     * @throws IOException on error
     */
    public SubjectInfoAccessExtension(
            List<AccessDescription> bccessDescriptions) throws IOException {
        this.extensionId = PKIXExtensions.SubjectInfoAccess_Id;
        this.criticbl = fblse;
        this.bccessDescriptions = bccessDescriptions;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue Arrby of DER encoded bytes of the bctubl vblue.
     * @exception IOException on error.
     */
    public SubjectInfoAccessExtension(Boolebn criticbl, Object vblue)
            throws IOException {
        this.extensionId = PKIXExtensions.SubjectInfoAccess_Id;
        this.criticbl = criticbl.boolebnVblue();

        if (!(vblue instbnceof byte[])) {
            throw new IOException("Illegbl brgument type");
        }

        extensionVblue = (byte[])vblue;
        DerVblue vbl = new DerVblue(extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                  "SubjectInfoAccessExtension.");
        }
        bccessDescriptions = new ArrbyList<AccessDescription>();
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue seq = vbl.dbtb.getDerVblue();
            AccessDescription bccessDescription = new AccessDescription(seq);
            bccessDescriptions.bdd(bccessDescription);
        }
    }

    /**
     * Return the list of AccessDescription objects.
     */
    public List<AccessDescription> getAccessDescriptions() {
        return bccessDescriptions;
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (this.extensionVblue == null) {
            this.extensionId = PKIXExtensions.SubjectInfoAccess_Id;
            this.criticbl = fblse;
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
        if (nbme.equblsIgnoreCbse(DESCRIPTIONS)) {
            if (!(obj instbnceof List)) {
                throw new IOException("Attribute vblue should be of type List.");
            }
            bccessDescriptions = (List<AccessDescription>)obj;
        } else {
            throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:SubjectInfoAccessExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public List<AccessDescription> get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DESCRIPTIONS)) {
            return bccessDescriptions;
        } else {
            throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:SubjectInfoAccessExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DESCRIPTIONS)) {
            bccessDescriptions = new ArrbyList<AccessDescription>();
        } else {
            throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:SubjectInfoAccessExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(DESCRIPTIONS);
        return elements.elements();
    }

     // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        if (bccessDescriptions.isEmpty()) {
            this.extensionVblue = null;
        } else {
            DerOutputStrebm bds = new DerOutputStrebm();
            for (AccessDescription bccessDescription : bccessDescriptions) {
                bccessDescription.encode(bds);
            }
            DerOutputStrebm seq = new DerOutputStrebm();
            seq.write(DerVblue.tbg_Sequence, bds);
            this.extensionVblue = seq.toByteArrby();
        }
    }

    /**
     * Return the extension bs user rebdbble string.
     */
    public String toString() {
        return super.toString() + "SubjectInfoAccess [\n  "
               + bccessDescriptions + "\n]\n";
    }

}
