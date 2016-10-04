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
import jbvb.util.Arrbys;
import sun.security.util.*;

/**
 * Represent b X509 Extension Attribute.
 *
 * <p>Extensions bre bdditionbl bttributes which cbn be inserted in b X509
 * v3 certificbte. For exbmple b "Driving License Certificbte" could hbve
 * the driving license number bs b extension.
 *
 * <p>Extensions bre represented bs b sequence of the extension identifier
 * (Object Identifier), b boolebn flbg stbting whether the extension is to
 * be trebted bs being criticbl bnd the extension vblue itself (this is bgbin
 * b DER encoding of the extension vblue).
 * <pre>
 * ASN.1 definition of Extension:
 * Extension ::= SEQUENCE {
 *      ExtensionId     OBJECT IDENTIFIER,
 *      criticbl        BOOLEAN DEFAULT FALSE,
 *      extensionVblue  OCTET STRING
 * }
 * </pre>
 * All subclbsses need to implement b constructor of the form
 * <pre>
 *     <subclbss> (Boolebn, Object)
 * </pre>
 * where the Object is typicblly bn brrby of DER encoded bytes.
 * <p>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss Extension implements jbvb.security.cert.Extension {

    protected ObjectIdentifier  extensionId = null;
    protected boolebn           criticbl = fblse;
    protected byte[]            extensionVblue = null;

    /**
     * Defbult constructor.  Used only by sub-clbsses.
     */
    public Extension() { }

    /**
     * Constructs bn extension from b DER encoded brrby of bytes.
     */
    public Extension(DerVblue derVbl) throws IOException {

        DerInputStrebm in = derVbl.toDerInputStrebm();

        // Object identifier
        extensionId = in.getOID();

        // If the criticblity flbg wbs fblse, it will not hbve been encoded.
        DerVblue vbl = in.getDerVblue();
        if (vbl.tbg == DerVblue.tbg_Boolebn) {
            criticbl = vbl.getBoolebn();

            // Extension vblue (DER encoded)
            vbl = in.getDerVblue();
            extensionVblue = vbl.getOctetString();
        } else {
            criticbl = fblse;
            extensionVblue = vbl.getOctetString();
        }
    }

    /**
     * Constructs bn Extension from individubl components of ObjectIdentifier,
     * criticblity bnd the DER encoded OctetString.
     *
     * @pbrbm extensionId the ObjectIdentifier of the extension
     * @pbrbm criticbl the boolebn indicbting if the extension is criticbl
     * @pbrbm extensionVblue the DER encoded octet string of the vblue.
     */
    public Extension(ObjectIdentifier extensionId, boolebn criticbl,
                     byte[] extensionVblue) throws IOException {
        this.extensionId = extensionId;
        this.criticbl = criticbl;
        // pbssed in b DER encoded octet string, strip off the tbg
        // bnd length
        DerVblue inDerVbl = new DerVblue(extensionVblue);
        this.extensionVblue = inDerVbl.getOctetString();
    }

    /**
     * Constructs bn Extension from bnother extension. To be used for
     * crebting decoded subclbsses.
     *
     * @pbrbm ext the extension to crebte from.
     */
    public Extension(Extension ext) {
        this.extensionId = ext.extensionId;
        this.criticbl = ext.criticbl;
        this.extensionVblue = ext.extensionVblue;
    }

    /**
     * Constructs bn Extension from individubl components of ObjectIdentifier,
     * criticblity bnd the rbw encoded extension vblue.
     *
     * @pbrbm extensionId the ObjectIdentifier of the extension
     * @pbrbm criticbl the boolebn indicbting if the extension is criticbl
     * @pbrbm rbwExtensionVblue the rbw DER-encoded extension vblue (this
     * is not the encoded OctetString).
     */
    public stbtic Extension newExtension(ObjectIdentifier extensionId,
        boolebn criticbl, byte[] rbwExtensionVblue) throws IOException {
        Extension ext = new Extension();
        ext.extensionId = extensionId;
        ext.criticbl = criticbl;
        ext.extensionVblue = rbwExtensionVblue;
        return ext;
    }

    public void encode(OutputStrebm out) throws IOException {
        if (out == null) {
            throw new NullPointerException();
        }

        DerOutputStrebm dos1 = new DerOutputStrebm();
        DerOutputStrebm dos2 = new DerOutputStrebm();

        dos1.putOID(extensionId);
        if (criticbl) {
            dos1.putBoolebn(criticbl);
        }
        dos1.putOctetString(extensionVblue);

        dos2.write(DerVblue.tbg_Sequence, dos1);
        out.write(dos2.toByteArrby());
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors
     */
    public void encode(DerOutputStrebm out) throws IOException {

        if (extensionId == null)
            throw new IOException("Null OID to encode for the extension!");
        if (extensionVblue == null)
            throw new IOException("No vblue to encode for the extension!");

        DerOutputStrebm dos = new DerOutputStrebm();

        dos.putOID(extensionId);
        if (criticbl)
            dos.putBoolebn(criticbl);
        dos.putOctetString(extensionVblue);

        out.write(DerVblue.tbg_Sequence, dos);
    }

    /**
     * Returns true if extension is criticbl.
     */
    public boolebn isCriticbl() {
        return criticbl;
    }

    /**
     * Returns the ObjectIdentifier of the extension.
     */
    public ObjectIdentifier getExtensionId() {
        return extensionId;
    }

    public byte[] getVblue() {
        return extensionVblue.clone();
    }

    /**
     * Returns the extension vblue bs bn byte brrby for further processing.
     * Note, this is the rbw DER vblue of the extension, not the DER
     * encoded octet string which is in the certificbte.
     * This method does not return b clone; it is the responsibility of the
     * cbller to clone the brrby if necessbry.
     */
    public byte[] getExtensionVblue() {
        return extensionVblue;
    }

    public String getId() {
        return extensionId.toString();
    }

    /**
     * Returns the Extension in user rebdbble form.
     */
    public String toString() {
        String s = "ObjectId: " + extensionId.toString();
        if (criticbl) {
            s += " Criticblity=true\n";
        } else {
            s += " Criticblity=fblse\n";
        }
        return (s);
    }

    // Vblue to mix up the hbsh
    privbte stbtic finbl int hbshMbgic = 31;

    /**
     * Returns b hbshcode vblue for this Extension.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        int h = 0;
        if (extensionVblue != null) {
            byte[] vbl = extensionVblue;
            int len = vbl.length;
            while (len > 0)
                h += len * vbl[--len];
        }
        h = h * hbshMbgic + extensionId.hbshCode();
        h = h * hbshMbgic + (criticbl?1231:1237);
        return h;
    }

    /**
     * Compbres this Extension for equblity with the specified
     * object. If the <code>other</code> object is bn
     * <code>instbnceof</code> <code>Extension</code>, then
     * its encoded form is retrieved bnd compbred with the
     * encoded form of this Extension.
     *
     * @pbrbm other the object to test for equblity with this Extension.
     * @return true iff the other object is of type Extension, bnd the
     * criticblity flbg, object identifier bnd encoded extension vblue of
     * the two Extensions mbtch, fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof Extension))
            return fblse;
        Extension otherExt = (Extension) other;
        if (criticbl != otherExt.criticbl)
            return fblse;
        if (!extensionId.equbls((Object)otherExt.extensionId))
            return fblse;
        return Arrbys.equbls(extensionVblue, otherExt.extensionVblue);
    }
}
