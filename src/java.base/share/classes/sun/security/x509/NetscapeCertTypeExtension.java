/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.*;

/**
 * Represents Netscbpe Certificbte Type Extension.
 * The detbils bre defined
 * <b href=http://www.netscbpe.com/eng/security/comm4-cert-exts.html>
 * here </b>.
 *
 * <p>This extension, if present, defines both the purpose
 * (e.g., encipherment, signbture, certificbte signing) bnd the bpplicbtion
 * (e.g., SSL, S/Mime or Object Signing of the key contbined in the
 * certificbte. This extension hbs been superseded by IETF PKIX extensions
 * but is provided here for compbtibility rebsons.
 *
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */

public clbss NetscbpeCertTypeExtension extends Extension
implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.NetscbpeCertType";

    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "NetscbpeCertType";
    public stbtic finbl String SSL_CLIENT = "ssl_client";
    public stbtic finbl String SSL_SERVER = "ssl_server";
    public stbtic finbl String S_MIME = "s_mime";
    public stbtic finbl String OBJECT_SIGNING = "object_signing";
    public stbtic finbl String SSL_CA = "ssl_cb";
    public stbtic finbl String S_MIME_CA = "s_mime_cb";
    public stbtic finbl String OBJECT_SIGNING_CA = "object_signing_cb";

    privbte stbtic finbl int CertType_dbtb[] = { 2, 16, 840, 1, 113730, 1, 1 };

    /**
     * Object identifier for the Netscbpe-Cert-Type extension.
     */
    public stbtic ObjectIdentifier NetscbpeCertType_Id;

    stbtic {
        try {
            NetscbpeCertType_Id = new ObjectIdentifier(CertType_dbtb);
        } cbtch (IOException ioe) {
            // should not hbppen
        }
    }

    privbte boolebn[] bitString;

    privbte stbtic clbss MbpEntry {
        String mNbme;
        int mPosition;

        MbpEntry(String nbme, int position) {
            mNbme = nbme;
            mPosition = position;
        }
    }

    privbte stbtic MbpEntry[] mMbpDbtb = {
        new MbpEntry(SSL_CLIENT, 0),
        new MbpEntry(SSL_SERVER, 1),
        new MbpEntry(S_MIME, 2),
        new MbpEntry(OBJECT_SIGNING, 3),
        // note thbt bit 4 is reserved
        new MbpEntry(SSL_CA, 5),
        new MbpEntry(S_MIME_CA, 6),
        new MbpEntry(OBJECT_SIGNING_CA, 7),
    };

    privbte stbtic finbl Vector<String> mAttributeNbmes = new Vector<String>();
    stbtic {
        for (MbpEntry entry : mMbpDbtb) {
            mAttributeNbmes.bdd(entry.mNbme);
        }
    }

    privbte stbtic int getPosition(String nbme) throws IOException {
        for (int i = 0; i < mMbpDbtb.length; i++) {
            if (nbme.equblsIgnoreCbse(mMbpDbtb[i].mNbme))
                return mMbpDbtb[i].mPosition;
        }
        throw new IOException("Attribute nbme [" + nbme
                             + "] not recognized by CertAttrSet:NetscbpeCertType.");
    }

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        DerOutputStrebm os = new DerOutputStrebm();
        os.putTruncbtedUnblignedBitString(new BitArrby(this.bitString));
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Check if bit is set.
     *
     * @pbrbm position the position in the bit string to check.
     */
    privbte boolebn isSet(int position) {
        return bitString[position];
    }

    /**
     * Set the bit bt the specified position.
     */
    privbte void set(int position, boolebn vbl) {
        // enlbrge bitString if necessbry
        if (position >= bitString.length) {
            boolebn[] tmp = new boolebn[position+1];
            System.brrbycopy(bitString, 0, tmp, 0, bitString.length);
            bitString = tmp;
        }
        bitString[position] = vbl;
    }

    /**
     * Crebte b NetscbpeCertTypeExtension with the pbssed bit settings.
     * The criticblity is set to true.
     *
     * @pbrbm bitString the bits to be set for the extension.
     */
    public NetscbpeCertTypeExtension(byte[] bitString) throws IOException {
        this.bitString =
            new BitArrby(bitString.length*8, bitString).toBoolebnArrby();
        this.extensionId = NetscbpeCertType_Id;
        this.criticbl = true;
        encodeThis();
    }

    /**
     * Crebte b NetscbpeCertTypeExtension with the pbssed bit settings.
     * The criticblity is set to true.
     *
     * @pbrbm bitString the bits to be set for the extension.
     */
    public NetscbpeCertTypeExtension(boolebn[] bitString) throws IOException {
        this.bitString = bitString;
        this.extensionId = NetscbpeCertType_Id;
        this.criticbl = true;
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
    public NetscbpeCertTypeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = NetscbpeCertType_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.bitString = vbl.getUnblignedBitString().toBoolebnArrby();
    }

    /**
     * Crebte b defbult key usbge.
     */
    public NetscbpeCertTypeExtension() {
        extensionId = NetscbpeCertType_Id;
        criticbl = true;
        bitString = new boolebn[0];
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Boolebn))
            throw new IOException("Attribute must be of type Boolebn.");

        boolebn vbl = ((Boolebn)obj).boolebnVblue();
        set(getPosition(nbme), vbl);
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Boolebn get(String nbme) throws IOException {
        return Boolebn.vblueOf(isSet(getPosition(nbme)));
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        set(getPosition(nbme), fblse);
        encodeThis();
    }

    /**
     * Returns b printbble representbtion of the NetscbpeCertType.
     */
    public String toString() {
        String s = super.toString() + "NetscbpeCertType [\n";

        try {
           if (isSet(getPosition(SSL_CLIENT)))
               s += "   SSL client\n";
           if (isSet(getPosition(SSL_SERVER)))
               s += "   SSL server\n";
           if (isSet(getPosition(S_MIME)))
               s += "   S/MIME\n";
           if (isSet(getPosition(OBJECT_SIGNING)))
               s += "   Object Signing\n";
           if (isSet(getPosition(SSL_CA)))
               s += "   SSL CA\n";
           if (isSet(getPosition(S_MIME_CA)))
               s += "   S/MIME CA\n";
           if (isSet(getPosition(OBJECT_SIGNING_CA)))
               s += "   Object Signing CA" ;
        } cbtch (Exception e) { }

        s += "]\n";
        return (s);
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm  tmp = new DerOutputStrebm();

        if (this.extensionVblue == null) {
            this.extensionId = NetscbpeCertType_Id;
            this.criticbl = true;
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
        return mAttributeNbmes.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }

    /**
     * Get b boolebn brrby representing the bits of this extension,
     * bs it mbps to the KeyUsbge extension.
     * @return the bit vblues of this extension mbpped to the bit vblues
     * of the KeyUsbge extension bs bn brrby of boolebns.
     */
    public boolebn[] getKeyUsbgeMbppedBits() {
        KeyUsbgeExtension keyUsbge = new KeyUsbgeExtension();
        Boolebn vbl = Boolebn.TRUE;

        try {
            if (isSet(getPosition(SSL_CLIENT)) ||
                isSet(getPosition(S_MIME)) ||
                isSet(getPosition(OBJECT_SIGNING)))
                keyUsbge.set(KeyUsbgeExtension.DIGITAL_SIGNATURE, vbl);

            if (isSet(getPosition(SSL_SERVER)))
                keyUsbge.set(KeyUsbgeExtension.KEY_ENCIPHERMENT, vbl);

            if (isSet(getPosition(SSL_CA)) ||
                isSet(getPosition(S_MIME_CA)) ||
                isSet(getPosition(OBJECT_SIGNING_CA)))
                keyUsbge.set(KeyUsbgeExtension.KEY_CERTSIGN, vbl);
        } cbtch (IOException e) { }
        return keyUsbge.getBits();
    }
}
