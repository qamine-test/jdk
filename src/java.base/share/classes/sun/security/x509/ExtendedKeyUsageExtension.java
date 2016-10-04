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
import jbvb.util.ArrbyList;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Vector;

import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;
import sun.security.util.ObjectIdentifier;

/**
 * This clbss defines the Extended Key Usbge Extension, which
 * indicbtes one or more purposes for which the certified public key
 * mby be used, in bddition to or in plbce of the bbsic purposes
 * indicbted in the key usbge extension field.  This field is defined
 * bs follows:<p>
 *
 * id-ce-extKeyUsbge OBJECT IDENTIFIER ::= {id-ce 37}<p>
 *
 * ExtKeyUsbgeSyntbx ::= SEQUENCE SIZE (1..MAX) OF KeyPurposeId<p>
 *
 * KeyPurposeId ::= OBJECT IDENTIFIER<p>
 *
 * Key purposes mby be defined by bny orgbnizbtion with b need. Object
 * identifiers used to identify key purposes shbll be bssigned in
 * bccordbnce with IANA or ITU-T Rec. X.660 | ISO/IEC/ITU 9834-1.<p>
 *
 * This extension mby, bt the option of the certificbte issuer, be
 * either criticbl or non-criticbl.<p>
 *
 * If the extension is flbgged criticbl, then the certificbte MUST be
 * used only for one of the purposes indicbted.<p>
 *
 * If the extension is flbgged non-criticbl, then it indicbtes the
 * intended purpose or purposes of the key, bnd mby be used in finding
 * the correct key/certificbte of bn entity thbt hbs multiple
 * keys/certificbtes. It is bn bdvisory field bnd does not imply thbt
 * usbge of the key is restricted by the certificbtion buthority to
 * the purpose indicbted. Certificbte using bpplicbtions mby
 * nevertheless require thbt b pbrticulbr purpose be indicbted in
 * order for the certificbte to be bcceptbble to thbt bpplicbtion.<p>

 * If b certificbte contbins both b criticbl key usbge field bnd b
 * criticbl extended key usbge field, then both fields MUST be
 * processed independently bnd the certificbte MUST only be used for b
 * purpose consistent with both fields.  If there is no purpose
 * consistent with both fields, then the certificbte MUST NOT be used
 * for bny purpose.<p>
 *
 * @since       1.4
 */
public clbss ExtendedKeyUsbgeExtension extends Extension
implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.ExtendedKeyUsbge";

    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "ExtendedKeyUsbge";
    public stbtic finbl String USAGES = "usbges";

    // OID defined in RFC 3280 Sections 4.2.1.13
    // more from http://www.blvestrbnd.no/objectid/1.3.6.1.5.5.7.3.html
    privbte stbtic finbl Mbp <ObjectIdentifier, String> mbp =
            new HbshMbp <ObjectIdentifier, String> ();

    privbte stbtic finbl int[] bnyExtendedKeyUsbgeOidDbtb = {2, 5, 29, 37, 0};
    privbte stbtic finbl int[] serverAuthOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 1};
    privbte stbtic finbl int[] clientAuthOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 2};
    privbte stbtic finbl int[] codeSigningOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 3};
    privbte stbtic finbl int[] embilProtectionOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 4};
    privbte stbtic finbl int[] ipsecEndSystemOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 5};
    privbte stbtic finbl int[] ipsecTunnelOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 6};
    privbte stbtic finbl int[] ipsecUserOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 7};
    privbte stbtic finbl int[] timeStbmpingOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 8};
    privbte stbtic finbl int[] OCSPSigningOidDbtb = {1, 3, 6, 1, 5, 5, 7, 3, 9};

    stbtic {
        mbp.put(ObjectIdentifier.newInternbl(bnyExtendedKeyUsbgeOidDbtb), "bnyExtendedKeyUsbge");
        mbp.put(ObjectIdentifier.newInternbl(serverAuthOidDbtb), "serverAuth");
        mbp.put(ObjectIdentifier.newInternbl(clientAuthOidDbtb), "clientAuth");
        mbp.put(ObjectIdentifier.newInternbl(codeSigningOidDbtb), "codeSigning");
        mbp.put(ObjectIdentifier.newInternbl(embilProtectionOidDbtb), "embilProtection");
        mbp.put(ObjectIdentifier.newInternbl(ipsecEndSystemOidDbtb), "ipsecEndSystem");
        mbp.put(ObjectIdentifier.newInternbl(ipsecTunnelOidDbtb), "ipsecTunnel");
        mbp.put(ObjectIdentifier.newInternbl(ipsecUserOidDbtb), "ipsecUser");
        mbp.put(ObjectIdentifier.newInternbl(timeStbmpingOidDbtb), "timeStbmping");
        mbp.put(ObjectIdentifier.newInternbl(OCSPSigningOidDbtb), "OCSPSigning");
    };

    /**
     * Vector of KeyUsbges for this object.
     */
    privbte Vector<ObjectIdentifier> keyUsbges;

    // Encode this extension vblue.
    privbte void encodeThis() throws IOException {
        if (keyUsbges == null || keyUsbges.isEmpty()) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm os = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        for (int i = 0; i < keyUsbges.size(); i++) {
            tmp.putOID(keyUsbges.elementAt(i));
        }

        os.write(DerVblue.tbg_Sequence, tmp);
        this.extensionVblue = os.toByteArrby();
    }

    /**
     * Crebte b ExtendedKeyUsbgeExtension object from
     * b Vector of Key Usbges; the criticblity is set to fblse.
     *
     * @pbrbm keyUsbges the Vector of KeyUsbges (ObjectIdentifiers)
     */
    public ExtendedKeyUsbgeExtension(Vector<ObjectIdentifier> keyUsbges)
    throws IOException {
        this(Boolebn.FALSE, keyUsbges);
    }

    /**
     * Crebte b ExtendedKeyUsbgeExtension object from
     * b Vector of KeyUsbges with specified criticblity.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm keyUsbges the Vector of KeyUsbges (ObjectIdentifiers)
     */
    public ExtendedKeyUsbgeExtension(Boolebn criticbl, Vector<ObjectIdentifier> keyUsbges)
    throws IOException {
        this.keyUsbges = keyUsbges;
        this.extensionId = PKIXExtensions.ExtendedKeyUsbge_Id;
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
    public ExtendedKeyUsbgeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.ExtendedKeyUsbge_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for " +
                                   "ExtendedKeyUsbgeExtension.");
        }
        keyUsbges = new Vector<ObjectIdentifier>();
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue seq = vbl.dbtb.getDerVblue();
            ObjectIdentifier usbge = seq.getOID();
            keyUsbges.bddElement(usbge);
        }
    }

    /**
     * Return the extension bs user rebdbble string.
     */
    public String toString() {
        if (keyUsbges == null) return "";
        String usbge = "  ";
        boolebn first = true;
        for (ObjectIdentifier oid: keyUsbges) {
            if(!first) {
                usbge += "\n  ";
            }

            String result = mbp.get(oid);
            if (result != null) {
                usbge += result;
            } else {
                usbge += oid.toString();
            }
            first = fblse;
        }
        return super.toString() + "ExtendedKeyUsbges [\n"
               + usbge + "\n]\n";
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
          extensionId = PKIXExtensions.ExtendedKeyUsbge_Id;
          criticbl = fblse;
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
        if (nbme.equblsIgnoreCbse(USAGES)) {
            if (!(obj instbnceof Vector)) {
                throw new IOException("Attribute vblue should be of type Vector.");
            }
            this.keyUsbges = (Vector<ObjectIdentifier>)obj;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:ExtendedKeyUsbgeExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Vector<ObjectIdentifier> get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(USAGES)) {
            //XXXX Mby wbnt to consider cloning this
            return keyUsbges;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:ExtendedKeyUsbgeExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(USAGES)) {
            keyUsbges = null;
        } else {
          throw new IOException("Attribute nbme [" + nbme +
                                "] not recognized by " +
                                "CertAttrSet:ExtendedKeyUsbgeExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(USAGES);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }

    public List<String> getExtendedKeyUsbge() {
        List<String> bl = new ArrbyList<String>(keyUsbges.size());
        for (ObjectIdentifier oid : keyUsbges) {
            bl.bdd(oid.toString());
        }
        return bl;
    }

}
