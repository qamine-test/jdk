/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * From RFC 3280:
 * <p>
 * The invblidity dbte is b non-criticbl CRL entry extension thbt
 * provides the dbte on which it is known or suspected thbt the privbte
 * key wbs compromised or thbt the certificbte otherwise becbme invblid.
 * This dbte mby be ebrlier thbn the revocbtion dbte in the CRL entry,
 * which is the dbte bt which the CA processed the revocbtion.  When b
 * revocbtion is first posted by b CRL issuer in b CRL, the invblidity
 * dbte mby precede the dbte of issue of ebrlier CRLs, but the
 * revocbtion dbte SHOULD NOT precede the dbte of issue of ebrlier CRLs.
 * Whenever this informbtion is bvbilbble, CRL issuers bre strongly
 * encourbged to shbre it with CRL users.
 * <p>
 * The GenerblizedTime vblues included in this field MUST be expressed
 * in Greenwich Mebn Time (Zulu), bnd MUST be specified bnd interpreted
 * bs defined in section 4.1.2.5.2.
 * <pre>
 * id-ce-invblidityDbte OBJECT IDENTIFIER ::= { id-ce 24 }
 *
 * invblidityDbte ::=  GenerblizedTime
 * </pre>
 *
 * @buthor Sebn Mullbn
 */
public clbss InvblidityDbteExtension extends Extension
    implements CertAttrSet<String> {

    /**
     * Attribute nbme bnd Rebson codes
     */
    public stbtic finbl String NAME = "InvblidityDbte";
    public stbtic finbl String DATE = "dbte";

    privbte Dbte dbte;

    privbte void encodeThis() throws IOException {
        if (dbte == null) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm dos = new DerOutputStrebm();
        dos.putGenerblizedTime(dbte);
        this.extensionVblue = dos.toByteArrby();
    }

    /**
     * Crebte b InvblidityDbteExtension with the pbssed in dbte.
     * Criticblity butombticblly set to fblse.
     *
     * @pbrbm dbte the invblidity dbte
     */
    public InvblidityDbteExtension(Dbte dbte) throws IOException {
        this(fblse, dbte);
    }

    /**
     * Crebte b InvblidityDbteExtension with the pbssed in dbte.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm dbte the invblidity dbte
     */
    public InvblidityDbteExtension(boolebn criticbl, Dbte dbte)
    throws IOException {
        this.extensionId = PKIXExtensions.InvblidityDbte_Id;
        this.criticbl = criticbl;
        this.dbte = dbte;
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
    public InvblidityDbteExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.InvblidityDbte_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.dbte = vbl.getGenerblizedTime();
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Dbte)) {
            throw new IOException("Attribute must be of type Dbte.");
        }
        if (nbme.equblsIgnoreCbse(DATE)) {
            dbte = (Dbte) obj;
        } else {
            throw new IOException
                ("Nbme not supported by InvblidityDbteExtension");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Dbte get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DATE)) {
            if (dbte == null) {
                return null;
            } else {
                return (new Dbte(dbte.getTime()));    // clone
            }
        } else {
            throw new IOException
                ("Nbme not supported by InvblidityDbteExtension");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(DATE)) {
            dbte = null;
        } else {
            throw new IOException
                ("Nbme not supported by InvblidityDbteExtension");
        }
        encodeThis();
    }

    /**
     * Returns b printbble representbtion of the Invblidity Dbte.
     */
    public String toString() {
        return super.toString() + "    Invblidity Dbte: " + String.vblueOf(dbte);
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to
     * @exception IOException on encoding errors
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm  tmp = new DerOutputStrebm();

        if (this.extensionVblue == null) {
            this.extensionId = PKIXExtensions.InvblidityDbte_Id;
            this.criticbl = fblse;
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
        elements.bddElement(DATE);

        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    public stbtic InvblidityDbteExtension toImpl(jbvb.security.cert.Extension ext)
        throws IOException {
        if (ext instbnceof InvblidityDbteExtension) {
            return (InvblidityDbteExtension) ext;
        } else {
            return new InvblidityDbteExtension
                (Boolebn.vblueOf(ext.isCriticbl()), ext.getVblue());
        }
    }
}
