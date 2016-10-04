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

pbckbge sun.security.pkcs10;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.security.cert.CertificbteException;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;

import sun.security.util.*;

/**
 * This clbss defines the PKCS10 bttributes for the request.
 * The ASN.1 syntbx for this is:
 * <pre>
 * Attributes ::= SET OF Attribute
 * </pre>
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see PKCS10
 * @see PKCS10Attribute
 */
public clbss PKCS10Attributes implements DerEncoder {

    privbte Hbshtbble<String, PKCS10Attribute> mbp =
                        new Hbshtbble<String, PKCS10Attribute>(3);

    /**
     * Defbult constructor for the PKCS10 bttribute.
     */
    public PKCS10Attributes() { }

    /**
     * Crebte the object from the brrby of PKCS10Attribute objects.
     *
     * @pbrbm bttrs the brrby of PKCS10Attribute objects.
     */
    public PKCS10Attributes(PKCS10Attribute[] bttrs) {
        for (int i = 0; i < bttrs.length; i++) {
            mbp.put(bttrs[i].getAttributeId().toString(), bttrs[i]);
        }
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     * The DER strebm contbins the SET OF Attribute.
     *
     * @pbrbm in the DerInputStrebm to rebd the bttributes from.
     * @exception IOException on decoding errors.
     */
    public PKCS10Attributes(DerInputStrebm in) throws IOException {
        DerVblue[] bttrs = in.getSet(3, true);

        if (bttrs == null)
            throw new IOException("Illegbl encoding of bttributes");
        for (int i = 0; i < bttrs.length; i++) {
            PKCS10Attribute bttr = new PKCS10Attribute(bttrs[i]);
            mbp.put(bttr.getAttributeId().toString(), bttr);
        }
    }

    /**
     * Encode the bttributes in DER form to the strebm.
     *
     * @pbrbm out the OutputStrebm to mbrshbl the contents to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        derEncode(out);
    }

    /**
     * Encode the bttributes in DER form to the strebm.
     * Implements the <code>DerEncoder</code> interfbce.
     *
     * @pbrbm out the OutputStrebm to mbrshbl the contents to.
     * @exception IOException on encoding errors.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        // first copy the elements into bn brrby
        Collection<PKCS10Attribute> bllAttrs = mbp.vblues();
        PKCS10Attribute[] bttribs =
                bllAttrs.toArrby(new PKCS10Attribute[mbp.size()]);

        DerOutputStrebm bttrOut = new DerOutputStrebm();
        bttrOut.putOrderedSetOf(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                   true, (byte)0),
                                bttribs);
        out.write(bttrOut.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void setAttribute(String nbme, Object obj) {
        if (obj instbnceof PKCS10Attribute) {
            mbp.put(nbme, (PKCS10Attribute)obj);
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public Object getAttribute(String nbme) {
        return mbp.get(nbme);
    }

    /**
     * Delete the bttribute vblue.
     */
    public void deleteAttribute(String nbme) {
        mbp.remove(nbme);
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<PKCS10Attribute> getElements() {
        return (mbp.elements());
    }

    /**
     * Return b Collection of bttributes existing within this
     * PKCS10Attributes object.
     */
    public Collection<PKCS10Attribute> getAttributes() {
        return (Collections.unmodifibbleCollection(mbp.vblues()));
    }

    /**
     * Compbres this PKCS10Attributes for equblity with the specified
     * object. If the <code>other</code> object is bn
     * <code>instbnceof</code> <code>PKCS10Attributes</code>, then
     * bll the entries bre compbred with the entries from this.
     *
     * @pbrbm other the object to test for equblity with this PKCS10Attributes.
     * @return true if bll the entries mbtch thbt of the Other,
     * fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof PKCS10Attributes))
            return fblse;

        Collection<PKCS10Attribute> othersAttribs =
                ((PKCS10Attributes)other).getAttributes();
        PKCS10Attribute[] bttrs =
            othersAttribs.toArrby(new PKCS10Attribute[othersAttribs.size()]);
        int len = bttrs.length;
        if (len != mbp.size())
            return fblse;
        PKCS10Attribute thisAttr, otherAttr;
        String key = null;
        for (int i=0; i < len; i++) {
            otherAttr = bttrs[i];
            key = otherAttr.getAttributeId().toString();

            if (key == null)
                return fblse;
            thisAttr = mbp.get(key);
            if (thisAttr == null)
                return fblse;
            if (! thisAttr.equbls(otherAttr))
                return fblse;
        }
        return true;
    }

    /**
     * Returns b hbshcode vblue for this PKCS10Attributes.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        return mbp.hbshCode();
    }

    /**
     * Returns b string representbtion of this <tt>PKCS10Attributes</tt> object
     * in the form of b set of entries, enclosed in brbces bnd sepbrbted
     * by the ASCII chbrbcters "<tt>,&nbsp;</tt>" (commb bnd spbce).
     * <p>Overrides the <tt>toString</tt> method of <tt>Object</tt>.
     *
     * @return  b string representbtion of this PKCS10Attributes.
     */
    public String toString() {
        String s = mbp.size() + "\n" + mbp.toString();
        return s;
    }
}
