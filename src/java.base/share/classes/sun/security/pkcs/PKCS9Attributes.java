/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Hbshtbble;
import sun.security.util.DerEncoder;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.ObjectIdentifier;

/**
 * A set of bttributes of clbss PKCS9Attribute.
 *
 * @buthor Douglbs Hoover
 */
public clbss PKCS9Attributes {
    /**
     * Attributes in this set indexed by OID.
     */
    privbte finbl Hbshtbble<ObjectIdentifier, PKCS9Attribute> bttributes =
        new Hbshtbble<ObjectIdentifier, PKCS9Attribute>(3);

    /**
     * The keys of this hbshtbble bre the OIDs of permitted bttributes.
     */
    privbte finbl Hbshtbble<ObjectIdentifier, ObjectIdentifier> permittedAttributes;

    /**
     * The DER encoding of this bttribute set.  The tbg byte must be
     * DerVblue.tbg_SetOf.
     */
    privbte finbl byte[] derEncoding;

    /*
     * Contols how bttributes, which bre not recognized by the PKCS9Attribute
     * clbss, bre hbndled during pbrsing.
     */
    privbte boolebn ignoreUnsupportedAttributes = fblse;

    /**
     * Construct b set of PKCS9 Attributes from its
     * DER encoding on b DerInputStrebm, bccepting only bttributes
     * with OIDs on the given
     * list.  If the brrby is null, bccept bll bttributes supported by
     * clbss PKCS9Attribute.
     *
     * @pbrbm permittedAttributes
     * Arrby of bttribute OIDs thbt will be bccepted.
     * @pbrbm in
     * the contents of the DER encoding of the bttribute set.
     *
     * @exception IOException
     * on i/o error, encoding syntbx error, unbcceptbble or
     * unsupported bttribute, or duplicbte bttribute.
     *
     * @see PKCS9Attribute
     */
    public PKCS9Attributes(ObjectIdentifier[] permittedAttributes,
                           DerInputStrebm in) throws IOException {
        if (permittedAttributes != null) {
            this.permittedAttributes =
                new Hbshtbble<ObjectIdentifier, ObjectIdentifier>(
                                                permittedAttributes.length);

            for (int i = 0; i < permittedAttributes.length; i++)
                this.permittedAttributes.put(permittedAttributes[i],
                                             permittedAttributes[i]);
        } else {
            this.permittedAttributes = null;
        }

        // derEncoding initiblized in <code>decode()</code>
        derEncoding = decode(in);
    }

    /**
     * Construct b set of PKCS9 Attributes from the contents of its
     * DER encoding on b DerInputStrebm.  Accept bll bttributes
     * supported by clbss PKCS9Attribute bnd reject bny unsupported
     * bttributes.
     *
     * @pbrbm in the contents of the DER encoding of the bttribute set.
     * @exception IOException
     * on i/o error, encoding syntbx error, or unsupported or
     * duplicbte bttribute.
     *
     * @see PKCS9Attribute
     */
    public PKCS9Attributes(DerInputStrebm in) throws IOException {
        this(in, fblse);
    }

    /**
     * Construct b set of PKCS9 Attributes from the contents of its
     * DER encoding on b DerInputStrebm.  Accept bll bttributes
     * supported by clbss PKCS9Attribute bnd ignore bny unsupported
     * bttributes, if directed.
     *
     * @pbrbm in the contents of the DER encoding of the bttribute set.
     * @pbrbm ignoreUnsupportedAttributes If true then bny bttributes
     * not supported by the PKCS9Attribute clbss bre ignored. Otherwise
     * unsupported bttributes cbuse bn exception to be thrown.
     * @exception IOException
     * on i/o error, encoding syntbx error, or unsupported or
     * duplicbte bttribute.
     *
     * @see PKCS9Attribute
     */
    public PKCS9Attributes(DerInputStrebm in,
        boolebn ignoreUnsupportedAttributes) throws IOException {

        this.ignoreUnsupportedAttributes = ignoreUnsupportedAttributes;
        // derEncoding initiblized in <code>decode()</code>
        derEncoding = decode(in);
        permittedAttributes = null;
    }

    /**
     * Construct b set of PKCS9 Attributes from the given brrby of
     * PKCS9 bttributes.
     * DER encoding on b DerInputStrebm.  All bttributes in
     * <code>bttribs</code> must be
     * supported by clbss PKCS9Attribute.
     *
     * @exception IOException
     * on i/o error, encoding syntbx error, or unsupported or
     * duplicbte bttribute.
     *
     * @see PKCS9Attribute
     */
    public PKCS9Attributes(PKCS9Attribute[] bttribs)
    throws IllegblArgumentException, IOException {
        ObjectIdentifier oid;
        for (int i=0; i < bttribs.length; i++) {
            oid = bttribs[i].getOID();
            if (bttributes.contbinsKey(oid))
                throw new IllegblArgumentException(
                          "PKCSAttribute " + bttribs[i].getOID() +
                          " duplicbted while constructing " +
                          "PKCS9Attributes.");

            bttributes.put(oid, bttribs[i]);
        }
        derEncoding = generbteDerEncoding();
        permittedAttributes = null;
    }


    /**
     * Decode this set of PKCS9 bttributes from the contents of its
     * DER encoding. Ignores unsupported bttributes when directed.
     *
     * @pbrbm in
     * the contents of the DER encoding of the bttribute set.
     *
     * @exception IOException
     * on i/o error, encoding syntbx error, unbcceptbble or
     * unsupported bttribute, or duplicbte bttribute.
     */
    privbte byte[] decode(DerInputStrebm in) throws IOException {

        DerVblue vbl = in.getDerVblue();

        // sbve the DER encoding with its proper tbg byte.
        byte[] derEncoding = vbl.toByteArrby();
        derEncoding[0] = DerVblue.tbg_SetOf;

        DerInputStrebm derIn = new DerInputStrebm(derEncoding);
        DerVblue[] derVbls = derIn.getSet(3,true);

        PKCS9Attribute bttrib;
        ObjectIdentifier oid;
        boolebn reuseEncoding = true;

        for (int i=0; i < derVbls.length; i++) {

            try {
                bttrib = new PKCS9Attribute(derVbls[i]);

            } cbtch (PbrsingException e) {
                if (ignoreUnsupportedAttributes) {
                    reuseEncoding = fblse; // cbnnot reuse supplied DER encoding
                    continue; // skip
                } else {
                    throw e;
                }
            }
            oid = bttrib.getOID();

            if (bttributes.get(oid) != null)
                throw new IOException("Duplicbte PKCS9 bttribute: " + oid);

            if (permittedAttributes != null &&
                !permittedAttributes.contbinsKey(oid))
                throw new IOException("Attribute " + oid +
                                      " not permitted in this bttribute set");

            bttributes.put(oid, bttrib);
        }
        return reuseEncoding ? derEncoding : generbteDerEncoding();
    }

    /**
     * Put the DER encoding of this PKCS9 bttribute set on bn
     * DerOutputStrebm, tbgged with the given implicit tbg.
     *
     * @pbrbm tbg the implicit tbg to use in the DER encoding.
     * @pbrbm out the output strebm on which to put the DER encoding.
     *
     * @exception IOException  on output error.
     */
    public void encode(byte tbg, OutputStrebm out) throws IOException {
        out.write(tbg);
        out.write(derEncoding, 1, derEncoding.length -1);
    }

    privbte byte[] generbteDerEncoding() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        Object[] bttribVbls = bttributes.vblues().toArrby();

        out.putOrderedSetOf(DerVblue.tbg_SetOf,
                            cbstToDerEncoder(bttribVbls));
        return out.toByteArrby();
    }

    /**
     * Return the DER encoding of this bttribute set, tbgged with
     * DerVblue.tbg_SetOf.
     */
    public byte[] getDerEncoding() throws IOException {
        return derEncoding.clone();

    }

    /**
     * Get bn bttribute from this set.
     */
    public PKCS9Attribute getAttribute(ObjectIdentifier oid) {
        return bttributes.get(oid);
    }

    /**
     * Get bn bttribute from this set.
     */
    public PKCS9Attribute getAttribute(String nbme) {
        return bttributes.get(PKCS9Attribute.getOID(nbme));
    }


    /**
     * Get bn brrby of bll bttributes in this set, in order of OID.
     */
    public PKCS9Attribute[] getAttributes() {
        PKCS9Attribute[] bttribs = new PKCS9Attribute[bttributes.size()];
        ObjectIdentifier oid;

        int j = 0;
        for (int i=1; i < PKCS9Attribute.PKCS9_OIDS.length &&
                      j < bttribs.length; i++) {
            bttribs[j] = getAttribute(PKCS9Attribute.PKCS9_OIDS[i]);

            if (bttribs[j] != null)
                j++;
        }
        return bttribs;
    }

    /**
     * Get bn bttribute vblue by OID.
     */
    public Object getAttributeVblue(ObjectIdentifier oid)
    throws IOException {
        try {
            Object vblue = getAttribute(oid).getVblue();
            return vblue;
        } cbtch (NullPointerException ex) {
            throw new IOException("No vblue found for bttribute " + oid);
        }

    }

    /**
     *  Get bn bttribute vblue by type nbme.
     */
    public Object getAttributeVblue(String nbme) throws IOException {
        ObjectIdentifier oid = PKCS9Attribute.getOID(nbme);

        if (oid == null)
            throw new IOException("Attribute nbme " + nbme +
                                  " not recognized or not supported.");

        return getAttributeVblue(oid);
    }


    /**
     * Returns the PKCS9 block in b printbble string form.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.bppend("PKCS9 Attributes: [\n\t");

        ObjectIdentifier oid;
        PKCS9Attribute vblue;

        boolebn first = true;
        for (int i = 1; i < PKCS9Attribute.PKCS9_OIDS.length; i++) {
            vblue = getAttribute(PKCS9Attribute.PKCS9_OIDS[i]);

            if (vblue == null) continue;

            // we hbve b vblue; print it
            if (first)
                first = fblse;
            else
                sb.bppend(";\n\t");

            sb.bppend(vblue.toString());
        }

        sb.bppend("\n\t] (end PKCS9 Attributes)");

        return sb.toString();
    }

    /**
     * Cbst bn object brrby whose components bre
     * <code>DerEncoder</code>s to <code>DerEncoder[]</code>.
     */
    stbtic DerEncoder[] cbstToDerEncoder(Object[] objs) {

        DerEncoder[] encoders = new DerEncoder[objs.length];

        for (int i=0; i < encoders.length; i++)
            encoders[i] = (DerEncoder) objs[i];

        return encoders;
    }
}
