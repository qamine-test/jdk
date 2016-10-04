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
import jbvb.security.cert.CertificbteException;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

import sun.security.util.*;

/**
 * This clbss defines the certificbte extension which specifies the
 * Policy constrbints.
 * <p>
 * The policy constrbints extension cbn be used in certificbtes issued
 * to CAs. The policy constrbints extension constrbins pbth vblidbtion
 * in two wbys. It cbn be used to prohibit policy mbpping or require
 * thbt ebch certificbte in b pbth contbin bn bcceptbble policy
 * identifier.<p>
 * The ASN.1 syntbx for this is (IMPLICIT tbgging is defined in the
 * module definition):
 * <pre>
 * PolicyConstrbints ::= SEQUENCE {
 *     requireExplicitPolicy [0] SkipCerts OPTIONAL,
 *     inhibitPolicyMbpping  [1] SkipCerts OPTIONAL
 * }
 * SkipCerts ::= INTEGER (0..MAX)
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss PolicyConstrbintsExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.PolicyConstrbints";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "PolicyConstrbints";
    public stbtic finbl String REQUIRE = "require";
    public stbtic finbl String INHIBIT = "inhibit";

    privbte stbtic finbl byte TAG_REQUIRE = 0;
    privbte stbtic finbl byte TAG_INHIBIT = 1;

    privbte int require = -1;
    privbte int inhibit = -1;

    // Encode this extension vblue.
    privbte void encodeThis() throws IOException {
        if (require == -1 && inhibit == -1) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm tbgged = new DerOutputStrebm();
        DerOutputStrebm seq = new DerOutputStrebm();

        if (require != -1) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putInteger(require);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                         fblse, TAG_REQUIRE), tmp);
        }
        if (inhibit != -1) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putInteger(inhibit);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                         fblse, TAG_INHIBIT), tmp);
        }
        seq.write(DerVblue.tbg_Sequence, tbgged);
        this.extensionVblue = seq.toByteArrby();
    }

    /**
     * Crebte b PolicyConstrbintsExtension object with both
     * require explicit policy bnd inhibit policy mbpping. The
     * extension is mbrked non-criticbl.
     *
     * @pbrbm require require explicit policy (-1 for optionbl).
     * @pbrbm inhibit inhibit policy mbpping (-1 for optionbl).
     */
    public PolicyConstrbintsExtension(int require, int inhibit)
    throws IOException {
        this(Boolebn.FALSE, require, inhibit);
    }

    /**
     * Crebte b PolicyConstrbintsExtension object with specified
     * criticblity bnd both require explicit policy bnd inhibit
     * policy mbpping.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm require require explicit policy (-1 for optionbl).
     * @pbrbm inhibit inhibit policy mbpping (-1 for optionbl).
     */
    public PolicyConstrbintsExtension(Boolebn criticbl, int require, int inhibit)
    throws IOException {
        this.require = require;
        this.inhibit = inhibit;
        this.extensionId = PKIXExtensions.PolicyConstrbints_Id;
        this.criticbl = criticbl.boolebnVblue();
        encodeThis();
    }

    /**
     * Crebte the extension from its DER encoded vblue bnd criticblity.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public PolicyConstrbintsExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.PolicyConstrbints_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Sequence tbg missing for PolicyConstrbint.");
        }
        DerInputStrebm in = vbl.dbtb;
        while (in != null && in.bvbilbble() != 0) {
            DerVblue next = in.getDerVblue();

            if (next.isContextSpecific(TAG_REQUIRE) && !next.isConstructed()) {
                if (this.require != -1)
                    throw new IOException("Duplicbte requireExplicitPolicy" +
                          "found in the PolicyConstrbintsExtension");
                next.resetTbg(DerVblue.tbg_Integer);
                this.require = next.getInteger();

            } else if (next.isContextSpecific(TAG_INHIBIT) &&
                       !next.isConstructed()) {
                if (this.inhibit != -1)
                    throw new IOException("Duplicbte inhibitPolicyMbpping" +
                          "found in the PolicyConstrbintsExtension");
                next.resetTbg(DerVblue.tbg_Integer);
                this.inhibit = next.getInteger();
            } else
                throw new IOException("Invblid encoding of PolicyConstrbint");
        }
    }

    /**
     * Return the extension bs user rebdbble string.
     */
    public String toString() {
        String s;
        s = super.toString() + "PolicyConstrbints: [" + "  Require: ";
        if (require == -1)
            s += "unspecified;";
        else
            s += require + ";";
        s += "\tInhibit: ";
        if (inhibit == -1)
            s += "unspecified";
        else
            s += inhibit;
        s += " ]\n";
        return s;
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
          extensionId = PKIXExtensions.PolicyConstrbints_Id;
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
        if (!(obj instbnceof Integer)) {
            throw new IOException("Attribute vblue should be of type Integer.");
        }
        if (nbme.equblsIgnoreCbse(REQUIRE)) {
            require = ((Integer)obj).intVblue();
        } else if (nbme.equblsIgnoreCbse(INHIBIT)) {
            inhibit = ((Integer)obj).intVblue();
        } else {
          throw new IOException("Attribute nbme " + "[" + nbme + "]" +
                                " not recognized by " +
                                "CertAttrSet:PolicyConstrbints.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Integer get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(REQUIRE)) {
            return require;
        } else if (nbme.equblsIgnoreCbse(INHIBIT)) {
            return inhibit;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:PolicyConstrbints.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(REQUIRE)) {
            require = -1;
        } else if (nbme.equblsIgnoreCbse(INHIBIT)) {
            inhibit = -1;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:PolicyConstrbints.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(REQUIRE);
        elements.bddElement(INHIBIT);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
