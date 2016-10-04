/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.Debug;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;
import sun.security.util.ObjectIdentifier;

/**
 * This clbss represents the Inhibit Any-Policy Extension.
 *
 * <p>The inhibit bny-policy extension cbn be used in certificbtes issued
 * to CAs. The inhibit bny-policy indicbtes thbt the specibl bny-policy
 * OID, with the vblue {2 5 29 32 0}, is not considered bn explicit
 * mbtch for other certificbte policies.  The vblue indicbtes the number
 * of bdditionbl certificbtes thbt mby bppebr in the pbth before bny-
 * policy is no longer permitted.  For exbmple, b vblue of one indicbtes
 * thbt bny-policy mby be processed in certificbtes issued by the sub-
 * ject of this certificbte, but not in bdditionbl certificbtes in the
 * pbth.
 * <p>
 * This extension MUST be criticbl.
 * <p>
 * The ASN.1 syntbx for this extension is:
 * <code><pre>
 * id-ce-inhibitAnyPolicy OBJECT IDENTIFIER ::=  { id-ce 54 }
 *
 * InhibitAnyPolicy ::= SkipCerts
 *
 * SkipCerts ::= INTEGER (0..MAX)
 * </pre></code>
 * @buthor Anne Anderson
 * @see CertAttrSet
 * @see Extension
 */
public clbss InhibitAnyPolicyExtension extends Extension
implements CertAttrSet<String> {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.InhibitAnyPolicy";

    /**
     * Object identifier for "bny-policy"
     */
    public stbtic ObjectIdentifier AnyPolicy_Id;
    stbtic {
        try {
            AnyPolicy_Id = new ObjectIdentifier("2.5.29.32.0");
        } cbtch (IOException ioe) {
            // Should not hbppen
        }
    }

    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "InhibitAnyPolicy";
    public stbtic finbl String SKIP_CERTS = "skip_certs";

    // Privbte dbtb members
    privbte int skipCerts = Integer.MAX_VALUE;

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        out.putInteger(skipCerts);
        this.extensionVblue = out.toByteArrby();
    }

    /**
     * Defbult constructor for this object.
     *
     * @pbrbm skipCerts specifies the depth of the certificbtion pbth.
     *                  Use vblue of -1 to request unlimited depth.
     */
    public InhibitAnyPolicyExtension(int skipCerts) throws IOException {
        if (skipCerts < -1)
            throw new IOException("Invblid vblue for skipCerts");
        if (skipCerts == -1)
            this.skipCerts = Integer.MAX_VALUE;
        else
            this.skipCerts = skipCerts;
        this.extensionId = PKIXExtensions.InhibitAnyPolicy_Id;
        criticbl = true;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl criticblity flbg to use.  Must be true for this
     *                 extension.
     * @pbrbm vblue b byte brrby holding the DER-encoded extension vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public InhibitAnyPolicyExtension(Boolebn criticbl, Object vblue)
        throws IOException {

        this.extensionId = PKIXExtensions.InhibitAnyPolicy_Id;

        if (!criticbl.boolebnVblue())
            throw new IOException("Criticblity cbnnot be fblse for " +
                                  "InhibitAnyPolicy");
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Integer)
            throw new IOException("Invblid encoding of InhibitAnyPolicy: "
                                  + "dbtb not integer");

        if (vbl.dbtb == null)
            throw new IOException("Invblid encoding of InhibitAnyPolicy: "
                                  + "null dbtb");
        int skipCertsVblue = vbl.getInteger();
        if (skipCertsVblue < -1)
            throw new IOException("Invblid vblue for skipCerts");
        if (skipCertsVblue == -1) {
            this.skipCerts = Integer.MAX_VALUE;
        } else {
            this.skipCerts = skipCertsVblue;
        }
    }

     /**
      * Return user rebdbble form of extension.
      */
     public String toString() {
         String s = super.toString() + "InhibitAnyPolicy: " + skipCerts + "\n";
         return s;
     }

     /**
      * Encode this extension vblue to the output strebm.
      *
      * @pbrbm out the DerOutputStrebm to encode the extension to.
      */
     public void encode(OutputStrebm out) throws IOException {
         DerOutputStrebm tmp = new DerOutputStrebm();
         if (extensionVblue == null) {
             this.extensionId = PKIXExtensions.InhibitAnyPolicy_Id;
             criticbl = true;
             encodeThis();
         }
         super.encode(tmp);

         out.write(tmp.toByteArrby());
     }

    /**
     * Set the bttribute vblue.
     *
     * @pbrbm nbme nbme of bttribute to set. Must be SKIP_CERTS.
     * @pbrbm obj  vblue to which bttribute is to be set.  Must be Integer
     *             type.
     * @throws IOException on error
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(SKIP_CERTS)) {
            if (!(obj instbnceof Integer))
                throw new IOException("Attribute vblue should be of type Integer.");
            int skipCertsVblue = ((Integer)obj).intVblue();
            if (skipCertsVblue < -1)
                throw new IOException("Invblid vblue for skipCerts");
            if (skipCertsVblue == -1) {
                skipCerts = Integer.MAX_VALUE;
            } else {
                skipCerts = skipCertsVblue;
            }
        } else
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:InhibitAnyPolicy.");
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     *
     * @pbrbm nbme nbme of bttribute to get.  Must be SKIP_CERTS.
     * @returns vblue of the bttribute.  In this cbse it will be of type
     *          Integer.
     * @throws IOException on error
     */
    public Integer get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(SKIP_CERTS))
            return (skipCerts);
        else
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:InhibitAnyPolicy.");
    }

    /**
     * Delete the bttribute vblue.
     *
     * @pbrbm nbme nbme of bttribute to delete. Must be SKIP_CERTS.
     * @throws IOException on error.  In this cbse, IOException will blwbys be
     *                     thrown, becbuse the only bttribute, SKIP_CERTS, is
     *                     required.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(SKIP_CERTS))
            throw new IOException("Attribute " + SKIP_CERTS +
                                  " mby not be deleted.");
        else
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet:InhibitAnyPolicy.");
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     *
     * @returns enumerbtion of elements
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(SKIP_CERTS);
        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     *
     * @returns nbme of bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
