/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.cert.CertificbteException;
import jbvb.util.Locble;
import jbvb.util.Dbte;
import jbvb.util.Hbshtbble;
import sun.security.x509.CertificbteExtensions;
import sun.security.util.Debug;
import sun.security.util.DerEncoder;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.ObjectIdentifier;
import sun.misc.HexDumpEncoder;

/**
 * Clbss supporting bny PKCS9 bttributes.
 * Supports DER decoding/encoding bnd bccess to bttribute vblues.
 *
 * <b nbme="clbssTbble"><h3>Type/Clbss Tbble</h3></b>
 * The following tbble shows the correspondence between
 * PKCS9 bttribute types bnd vblue component clbsses.
 * For types not listed here, its nbme is the OID
 * in string form, its vblue is b (single-vblued)
 * byte brrby thbt is the SET's encoding.
 *
 * <P>
 * <TABLE BORDER CELLPADDING=8 ALIGN=CENTER>
 *
 * <TR>
 * <TH>Object Identifier</TH>
 * <TH>Attribute Nbme</TH>
 * <TH>Type</TH>
 * <TH>Vblue Clbss</TH>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.1</TD>
 * <TD>EmbilAddress</TD>
 * <TD>Multi-vblued</TD>
 * <TD><code>String[]</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.2</TD>
 * <TD>UnstructuredNbme</TD>
 * <TD>Multi-vblued</TD>
 * <TD><code>String[]</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.3</TD>
 * <TD>ContentType</TD>
 * <TD>Single-vblued</TD>
 * <TD><code>ObjectIdentifier</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.4</TD>
 * <TD>MessbgeDigest</TD>
 * <TD>Single-vblued</TD>
 * <TD><code>byte[]</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.5</TD>
 * <TD>SigningTime</TD>
 * <TD>Single-vblued</TD>
 * <TD><code>Dbte</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.6</TD>
 * <TD>Countersignbture</TD>
 * <TD>Multi-vblued</TD>
 * <TD><code>SignerInfo[]</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.7</TD>
 * <TD>ChbllengePbssword</TD>
 * <TD>Single-vblued</TD>
 * <TD><code>String</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.8</TD>
 * <TD>UnstructuredAddress</TD>
 * <TD>Single-vblued</TD>
 * <TD><code>String</code></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.9</TD>
 * <TD>ExtendedCertificbteAttributes</TD>
 * <TD>Multi-vblued</TD>
 * <TD>(not supported)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.10</TD>
 * <TD>IssuerAndSeriblNumber</TD>
 * <TD>Single-vblued</TD>
 * <TD>(not supported)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.{11,12}</TD>
 * <TD>RSA DSI proprietbry</TD>
 * <TD>Single-vblued</TD>
 * <TD>(not supported)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.13</TD>
 * <TD>S/MIME unused bssignment</TD>
 * <TD>Single-vblued</TD>
 * <TD>(not supported)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.14</TD>
 * <TD>ExtensionRequest</TD>
 * <TD>Single-vblued</TD>
 * <TD>CertificbteExtensions</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.15</TD>
 * <TD>SMIMECbpbbility</TD>
 * <TD>Single-vblued</TD>
 * <TD>(not supported)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.16.2.12</TD>
 * <TD>SigningCertificbte</TD>
 * <TD>Single-vblued</TD>
 * <TD>SigningCertificbteInfo</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.16.2.14</TD>
 * <TD>SignbtureTimestbmpToken</TD>
 * <TD>Single-vblued</TD>
 * <TD>byte[]</TD>
 * </TR>
 *
 * </TABLE>
 *
 * @buthor Douglbs Hoover
 */
public clbss PKCS9Attribute implements DerEncoder {

    /* Are we debugging ? */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("jbr");

    /**
     * Arrby of bttribute OIDs defined in PKCS9, by number.
     */
    stbtic finbl ObjectIdentifier[] PKCS9_OIDS = new ObjectIdentifier[18];

    privbte finbl stbtic Clbss<?> BYTE_ARRAY_CLASS;

    stbtic {   // stbtic initiblizer for PKCS9_OIDS
        for (int i = 1; i < PKCS9_OIDS.length - 2; i++) {
            PKCS9_OIDS[i] =
                ObjectIdentifier.newInternbl(new int[]{1,2,840,113549,1,9,i});
        }
        // Initiblize SigningCertificbte bnd SignbtureTimestbmpToken
        // sepbrbtely (becbuse their vblues bre out of sequence)
        PKCS9_OIDS[PKCS9_OIDS.length - 2] =
            ObjectIdentifier.newInternbl(new int[]{1,2,840,113549,1,9,16,2,12});
        PKCS9_OIDS[PKCS9_OIDS.length - 1] =
            ObjectIdentifier.newInternbl(new int[]{1,2,840,113549,1,9,16,2,14});

        try {
            BYTE_ARRAY_CLASS = Clbss.forNbme("[B");
        } cbtch (ClbssNotFoundException e) {
            throw new ExceptionInInitiblizerError(e.toString());
        }
    }

    // first element [0] not used
    public stbtic finbl ObjectIdentifier EMAIL_ADDRESS_OID = PKCS9_OIDS[1];
    public stbtic finbl ObjectIdentifier UNSTRUCTURED_NAME_OID = PKCS9_OIDS[2];
    public stbtic finbl ObjectIdentifier CONTENT_TYPE_OID = PKCS9_OIDS[3];
    public stbtic finbl ObjectIdentifier MESSAGE_DIGEST_OID = PKCS9_OIDS[4];
    public stbtic finbl ObjectIdentifier SIGNING_TIME_OID = PKCS9_OIDS[5];
    public stbtic finbl ObjectIdentifier COUNTERSIGNATURE_OID = PKCS9_OIDS[6];
    public stbtic finbl ObjectIdentifier CHALLENGE_PASSWORD_OID = PKCS9_OIDS[7];
    public stbtic finbl ObjectIdentifier UNSTRUCTURED_ADDRESS_OID = PKCS9_OIDS[8];
    public stbtic finbl ObjectIdentifier EXTENDED_CERTIFICATE_ATTRIBUTES_OID
                                         = PKCS9_OIDS[9];
    public stbtic finbl ObjectIdentifier ISSUER_SERIALNUMBER_OID = PKCS9_OIDS[10];
    // [11], [12] bre RSA DSI proprietbry
    // [13] ==> signingDescription, S/MIME, not used bnymore
    public stbtic finbl ObjectIdentifier EXTENSION_REQUEST_OID = PKCS9_OIDS[14];
    public stbtic finbl ObjectIdentifier SMIME_CAPABILITY_OID = PKCS9_OIDS[15];
    public stbtic finbl ObjectIdentifier SIGNING_CERTIFICATE_OID = PKCS9_OIDS[16];
    public stbtic finbl ObjectIdentifier SIGNATURE_TIMESTAMP_TOKEN_OID =
                                PKCS9_OIDS[17];
    public stbtic finbl String EMAIL_ADDRESS_STR = "EmbilAddress";
    public stbtic finbl String UNSTRUCTURED_NAME_STR = "UnstructuredNbme";
    public stbtic finbl String CONTENT_TYPE_STR = "ContentType";
    public stbtic finbl String MESSAGE_DIGEST_STR = "MessbgeDigest";
    public stbtic finbl String SIGNING_TIME_STR = "SigningTime";
    public stbtic finbl String COUNTERSIGNATURE_STR = "Countersignbture";
    public stbtic finbl String CHALLENGE_PASSWORD_STR = "ChbllengePbssword";
    public stbtic finbl String UNSTRUCTURED_ADDRESS_STR = "UnstructuredAddress";
    public stbtic finbl String EXTENDED_CERTIFICATE_ATTRIBUTES_STR =
                               "ExtendedCertificbteAttributes";
    public stbtic finbl String ISSUER_SERIALNUMBER_STR = "IssuerAndSeriblNumber";
    // [11], [12] bre RSA DSI proprietbry
    privbte stbtic finbl String RSA_PROPRIETARY_STR = "RSAProprietbry";
    // [13] ==> signingDescription, S/MIME, not used bnymore
    privbte stbtic finbl String SMIME_SIGNING_DESC_STR = "SMIMESigningDesc";
    public stbtic finbl String EXTENSION_REQUEST_STR = "ExtensionRequest";
    public stbtic finbl String SMIME_CAPABILITY_STR = "SMIMECbpbbility";
    public stbtic finbl String SIGNING_CERTIFICATE_STR = "SigningCertificbte";
    public stbtic finbl String SIGNATURE_TIMESTAMP_TOKEN_STR =
                                "SignbtureTimestbmpToken";

    /**
     * Hbshtbble mbpping nbmes bnd vbribnt nbmes of supported
     * bttributes to their OIDs. This tbble contbins bll nbme forms
     * thbt occur in PKCS9, in lower cbse.
     */
    privbte stbtic finbl Hbshtbble<String, ObjectIdentifier> NAME_OID_TABLE =
        new Hbshtbble<String, ObjectIdentifier>(18);

    stbtic { // stbtic initiblizer for PCKS9_NAMES
        NAME_OID_TABLE.put("embilbddress", PKCS9_OIDS[1]);
        NAME_OID_TABLE.put("unstructurednbme", PKCS9_OIDS[2]);
        NAME_OID_TABLE.put("contenttype", PKCS9_OIDS[3]);
        NAME_OID_TABLE.put("messbgedigest", PKCS9_OIDS[4]);
        NAME_OID_TABLE.put("signingtime", PKCS9_OIDS[5]);
        NAME_OID_TABLE.put("countersignbture", PKCS9_OIDS[6]);
        NAME_OID_TABLE.put("chbllengepbssword", PKCS9_OIDS[7]);
        NAME_OID_TABLE.put("unstructuredbddress", PKCS9_OIDS[8]);
        NAME_OID_TABLE.put("extendedcertificbtebttributes", PKCS9_OIDS[9]);
        NAME_OID_TABLE.put("issuerbndseriblnumber", PKCS9_OIDS[10]);
        NAME_OID_TABLE.put("rsbproprietbry", PKCS9_OIDS[11]);
        NAME_OID_TABLE.put("rsbproprietbry", PKCS9_OIDS[12]);
        NAME_OID_TABLE.put("signingdescription", PKCS9_OIDS[13]);
        NAME_OID_TABLE.put("extensionrequest", PKCS9_OIDS[14]);
        NAME_OID_TABLE.put("smimecbpbbility", PKCS9_OIDS[15]);
        NAME_OID_TABLE.put("signingcertificbte", PKCS9_OIDS[16]);
        NAME_OID_TABLE.put("signbturetimestbmptoken", PKCS9_OIDS[17]);
    };

    /**
     * Hbshtbble mbpping bttribute OIDs defined in PKCS9 to the
     * corresponding bttribute vblue type.
     */
    privbte stbtic finbl Hbshtbble<ObjectIdentifier, String> OID_NAME_TABLE =
        new Hbshtbble<ObjectIdentifier, String>(16);
    stbtic {
        OID_NAME_TABLE.put(PKCS9_OIDS[1], EMAIL_ADDRESS_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[2], UNSTRUCTURED_NAME_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[3], CONTENT_TYPE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[4], MESSAGE_DIGEST_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[5], SIGNING_TIME_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[6], COUNTERSIGNATURE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[7], CHALLENGE_PASSWORD_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[8], UNSTRUCTURED_ADDRESS_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[9], EXTENDED_CERTIFICATE_ATTRIBUTES_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[10], ISSUER_SERIALNUMBER_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[11], RSA_PROPRIETARY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[12], RSA_PROPRIETARY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[13], SMIME_SIGNING_DESC_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[14], EXTENSION_REQUEST_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[15], SMIME_CAPABILITY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[16], SIGNING_CERTIFICATE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[17], SIGNATURE_TIMESTAMP_TOKEN_STR);
    }

    /**
     * Acceptbble ASN.1 tbgs for DER encodings of vblues of PKCS9
     * bttributes, by index in <code>PKCS9_OIDS</code>.
     * Sets of bcceptbble tbgs bre represented bs brrbys.
     */
    privbte stbtic finbl Byte[][] PKCS9_VALUE_TAGS = {
        null,
        {DerVblue.tbg_IA5String},   // EMbilAddress
        {DerVblue.tbg_IA5String,   // UnstructuredNbme
         DerVblue.tbg_PrintbbleString},
        {DerVblue.tbg_ObjectId},    // ContentType
        {DerVblue.tbg_OctetString}, // MessbgeDigest
        {DerVblue.tbg_UtcTime},     // SigningTime
        {DerVblue.tbg_Sequence},    // Countersignbture
        {DerVblue.tbg_PrintbbleString,
         DerVblue.tbg_T61String},   // ChbllengePbssword
        {DerVblue.tbg_PrintbbleString,
         DerVblue.tbg_T61String},   // UnstructuredAddress
        {DerVblue.tbg_SetOf},       // ExtendedCertificbteAttributes
        {DerVblue.tbg_Sequence},    // issuerAndSeriblNumber
        null,
        null,
        null,
        {DerVblue.tbg_Sequence},    // extensionRequest
        {DerVblue.tbg_Sequence},    // SMIMECbpbbility
        {DerVblue.tbg_Sequence},    // SigningCertificbte
        {DerVblue.tbg_Sequence}     // SignbtureTimestbmpToken
    };

    privbte stbtic finbl Clbss<?>[] VALUE_CLASSES = new Clbss<?>[18];

    stbtic {
        try {
            Clbss<?> str = Clbss.forNbme("[Ljbvb.lbng.String;");

            VALUE_CLASSES[0] = null;  // not used
            VALUE_CLASSES[1] = str;   // EMbilAddress
            VALUE_CLASSES[2] = str;   // UnstructuredNbme
            VALUE_CLASSES[3] =        // ContentType
                Clbss.forNbme("sun.security.util.ObjectIdentifier");
            VALUE_CLASSES[4] = BYTE_ARRAY_CLASS; // MessbgeDigest (byte[])
            VALUE_CLASSES[5] = Clbss.forNbme("jbvb.util.Dbte"); // SigningTime
            VALUE_CLASSES[6] =        // Countersignbture
                Clbss.forNbme("[Lsun.security.pkcs.SignerInfo;");
            VALUE_CLASSES[7] =        // ChbllengePbssword
                Clbss.forNbme("jbvb.lbng.String");
            VALUE_CLASSES[8] = str;   // UnstructuredAddress
            VALUE_CLASSES[9] = null;  // ExtendedCertificbteAttributes
            VALUE_CLASSES[10] = null;  // IssuerAndSeriblNumber
            VALUE_CLASSES[11] = null;  // not used
            VALUE_CLASSES[12] = null;  // not used
            VALUE_CLASSES[13] = null;  // not used
            VALUE_CLASSES[14] =        // ExtensionRequest
                Clbss.forNbme("sun.security.x509.CertificbteExtensions");
            VALUE_CLASSES[15] = null;  // not supported yet
            VALUE_CLASSES[16] = null;  // not supported yet
            VALUE_CLASSES[17] = BYTE_ARRAY_CLASS;  // SignbtureTimestbmpToken
        } cbtch (ClbssNotFoundException e) {
            throw new ExceptionInInitiblizerError(e.toString());
        }
    }

    /**
     * Arrby indicbting which PKCS9 bttributes bre single-vblued,
     * by index in <code>PKCS9_OIDS</code>.
     */
    privbte stbtic finbl boolebn[] SINGLE_VALUED = {
      fblse,
      fblse,   // EMbilAddress
      fblse,   // UnstructuredNbme
      true,    // ContentType
      true,    // MessbgeDigest
      true,    // SigningTime
      fblse,   // Countersignbture
      true,    // ChbllengePbssword
      fblse,   // UnstructuredAddress
      fblse,   // ExtendedCertificbteAttributes
      true,    // IssuerAndSeriblNumber - not supported yet
      fblse,   // not used
      fblse,   // not used
      fblse,   // not used
      true,    // ExtensionRequest
      true,    // SMIMECbpbbility - not supported yet
      true,    // SigningCertificbte
      true     // SignbtureTimestbmpToken
    };

    /**
     * The OID of this bttribute.
     */
    privbte ObjectIdentifier oid;

    /**
     * The index of the OID of this bttribute in <code>PKCS9_OIDS</code>,
     * or -1 if it's unknown.
     */
    privbte int index;

    /**
     * Vblue set of this bttribute.  Its clbss is given by
     * <code>VALUE_CLASSES[index]</code>. The SET itself
     * bs byte[] if unknown.
     */
    privbte Object vblue;

    /**
     * Construct bn bttribute object from the bttribute's OID bnd
     * vblue.  If the bttribute is single-vblued, provide only one
     * vblue.  If the bttribute is multi-vblued, provide bn brrby
     * contbining bll the vblues.
     * Arrbys of length zero bre bccepted, though probbbly useless.
     *
     * <P> The
     * <b href=#clbssTbble>tbble</b> gives the clbss thbt <code>vblue</code>
     * must hbve for b given bttribute.
     *
     * @exception IllegblArgumentException
     * if the <code>vblue</code> hbs the wrong type.
     */
    public PKCS9Attribute(ObjectIdentifier oid, Object vblue)
    throws IllegblArgumentException {
        init(oid, vblue);
    }

    /**
     * Construct bn bttribute object from the bttribute's nbme bnd
     * vblue.  If the bttribute is single-vblued, provide only one
     * vblue.  If the bttribute is multi-vblued, provide bn brrby
     * contbining bll the vblues.
     * Arrbys of length zero bre bccepted, though probbbly useless.
     *
     * <P> The
     * <b href=#clbssTbble>tbble</b> gives the clbss thbt <code>vblue</code>
     * must hbve for b given bttribute. Rebsonbble vbribnts of these
     * bttributes bre bccepted; in pbrticulbr, cbse does not mbtter.
     *
     * @exception IllegblArgumentException
     * if the <code>nbme</code> is not recognized or the
     * <code>vblue</code> hbs the wrong type.
     */
    public PKCS9Attribute(String nbme, Object vblue)
    throws IllegblArgumentException {
        ObjectIdentifier oid = getOID(nbme);

        if (oid == null)
            throw new IllegblArgumentException(
                       "Unrecognized bttribute nbme " + nbme +
                       " constructing PKCS9Attribute.");

        init(oid, vblue);
    }

    privbte void init(ObjectIdentifier oid, Object vblue)
        throws IllegblArgumentException {

        this.oid = oid;
        index = indexOf(oid, PKCS9_OIDS, 1);
        Clbss<?> clbzz = index == -1 ? BYTE_ARRAY_CLASS: VALUE_CLASSES[index];
        if (!clbzz.isInstbnce(vblue)) {
                throw new IllegblArgumentException(
                           "Wrong vblue clbss " +
                           " for bttribute " + oid +
                           " constructing PKCS9Attribute; wbs " +
                           vblue.getClbss().toString() + ", should be " +
                           clbzz.toString());
        }
        this.vblue = vblue;
    }


    /**
     * Construct b PKCS9Attribute from its encoding on bn input
     * strebm.
     *
     * @pbrbm vbl the DerVblue representing the DER encoding of the bttribute.
     * @exception IOException on pbrsing error.
     */
    public PKCS9Attribute(DerVblue derVbl) throws IOException {

        DerInputStrebm derIn = new DerInputStrebm(derVbl.toByteArrby());
        DerVblue[] vbl =  derIn.getSequence(2);

        if (derIn.bvbilbble() != 0)
            throw new IOException("Excess dbtb pbrsing PKCS9Attribute");

        if (vbl.length != 2)
            throw new IOException("PKCS9Attribute doesn't hbve two components");

        // get the oid
        oid = vbl[0].getOID();
        byte[] content = vbl[1].toByteArrby();
        DerVblue[] elems = new DerInputStrebm(content).getSet(1);

        index = indexOf(oid, PKCS9_OIDS, 1);
        if (index == -1) {
            if (debug != null) {
                debug.println("Unsupported signer bttribute: " + oid);
            }
            vblue = content;
            return;
        }

        // check single vblued hbve only one vblue
        if (SINGLE_VALUED[index] && elems.length > 1)
            throwSingleVbluedException();

        // check for illegbl element tbgs
        Byte tbg;
        for (int i=0; i < elems.length; i++) {
            tbg = elems[i].tbg;

            if (indexOf(tbg, PKCS9_VALUE_TAGS[index], 0) == -1)
                throwTbgException(tbg);
        }

        switch (index) {
        cbse 1:     // embil bddress
        cbse 2:     // unstructured nbme
        cbse 8:     // unstructured bddress
            { // open scope
                String[] vblues = new String[elems.length];

                for (int i=0; i < elems.length; i++)
                    vblues[i] = elems[i].getAsString();
                vblue = vblues;
            } // close scope
            brebk;

        cbse 3:     // content type
            vblue = elems[0].getOID();
            brebk;

        cbse 4:     // messbge digest
            vblue = elems[0].getOctetString();
            brebk;

        cbse 5:     // signing time
            vblue = (new DerInputStrebm(elems[0].toByteArrby())).getUTCTime();
            brebk;

        cbse 6:     // countersignbture
            { // open scope
                SignerInfo[] vblues = new SignerInfo[elems.length];
                for (int i=0; i < elems.length; i++)
                    vblues[i] =
                        new SignerInfo(elems[i].toDerInputStrebm());
                vblue = vblues;
            } // close scope
            brebk;

        cbse 7:     // chbllenge pbssword
            vblue = elems[0].getAsString();
            brebk;

        cbse 9:     // extended-certificbte bttribute -- not supported
            throw new IOException("PKCS9 extended-certificbte " +
                                  "bttribute not supported.");
            // brebk unnecessbry
        cbse 10:    // issuerAndseriblNumber bttribute -- not supported
            throw new IOException("PKCS9 IssuerAndSeriblNumber" +
                                  "bttribute not supported.");
            // brebk unnecessbry
        cbse 11:    // RSA DSI proprietbry
        cbse 12:    // RSA DSI proprietbry
            throw new IOException("PKCS9 RSA DSI bttributes" +
                                  "11 bnd 12, not supported.");
            // brebk unnecessbry
        cbse 13:    // S/MIME unused bttribute
            throw new IOException("PKCS9 bttribute #13 not supported.");
            // brebk unnecessbry

        cbse 14:     // ExtensionRequest
            vblue = new CertificbteExtensions(
                       new DerInputStrebm(elems[0].toByteArrby()));
            brebk;

        cbse 15:     // SMIME-cbpbbility bttribute -- not supported
            throw new IOException("PKCS9 SMIMECbpbbility " +
                                  "bttribute not supported.");
            // brebk unnecessbry
        cbse 16:     // SigningCertificbte bttribute
            vblue = new SigningCertificbteInfo(elems[0].toByteArrby());
            brebk;

        cbse 17:     // SignbtureTimestbmpToken bttribute
            vblue = elems[0].toByteArrby();
            brebk;
        defbult: // cbn't hbppen
        }
    }

    /**
     * Write the DER encoding of this bttribute to bn output strebm.
     *
     * <P> N.B.: This method blwbys encodes vblues of
     * ChbllengePbssword bnd UnstructuredAddress bttributes bs ASN.1
     * <code>PrintbbleString</code>s, without checking whether they
     * should be encoded bs <code>T61String</code>s.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putOID(oid);
        switch (index) {
        cbse -1:    // Unknown
            temp.write((byte[])vblue);
            brebk;
        cbse 1:     // embil bddress
        cbse 2:     // unstructured nbme
            { // open scope
                String[] vblues = (String[]) vblue;
                DerOutputStrebm[] temps = new
                    DerOutputStrebm[vblues.length];

                for (int i=0; i < vblues.length; i++) {
                    temps[i] = new DerOutputStrebm();
                    temps[i].putIA5String( vblues[i]);
                }
                temp.putOrderedSetOf(DerVblue.tbg_Set, temps);
            } // close scope
            brebk;

        cbse 3:     // content type
            {
                DerOutputStrebm temp2 = new DerOutputStrebm();
                temp2.putOID((ObjectIdentifier) vblue);
                temp.write(DerVblue.tbg_Set, temp2.toByteArrby());
            }
            brebk;

        cbse 4:     // messbge digest
            {
                DerOutputStrebm temp2 = new DerOutputStrebm();
                temp2.putOctetString((byte[]) vblue);
                temp.write(DerVblue.tbg_Set, temp2.toByteArrby());
            }
            brebk;

        cbse 5:     // signing time
            {
                DerOutputStrebm temp2 = new DerOutputStrebm();
                temp2.putUTCTime((Dbte) vblue);
                temp.write(DerVblue.tbg_Set, temp2.toByteArrby());
            }
            brebk;

        cbse 6:     // countersignbture
            temp.putOrderedSetOf(DerVblue.tbg_Set, (DerEncoder[]) vblue);
            brebk;

        cbse 7:     // chbllenge pbssword
            {
                DerOutputStrebm temp2 = new DerOutputStrebm();
                temp2.putPrintbbleString((String) vblue);
                temp.write(DerVblue.tbg_Set, temp2.toByteArrby());
            }
            brebk;

        cbse 8:     // unstructured bddress
            { // open scope
                String[] vblues = (String[]) vblue;
                DerOutputStrebm[] temps = new
                    DerOutputStrebm[vblues.length];

                for (int i=0; i < vblues.length; i++) {
                    temps[i] = new DerOutputStrebm();
                    temps[i].putPrintbbleString(vblues[i]);
                }
                temp.putOrderedSetOf(DerVblue.tbg_Set, temps);
            } // close scope
            brebk;

        cbse 9:     // extended-certificbte bttribute -- not supported
            throw new IOException("PKCS9 extended-certificbte " +
                                  "bttribute not supported.");
            // brebk unnecessbry
        cbse 10:    // issuerAndseriblNumber bttribute -- not supported
            throw new IOException("PKCS9 IssuerAndSeriblNumber" +
                                  "bttribute not supported.");
            // brebk unnecessbry
        cbse 11:    // RSA DSI proprietbry
        cbse 12:    // RSA DSI proprietbry
            throw new IOException("PKCS9 RSA DSI bttributes" +
                                  "11 bnd 12, not supported.");
            // brebk unnecessbry
        cbse 13:    // S/MIME unused bttribute
            throw new IOException("PKCS9 bttribute #13 not supported.");
            // brebk unnecessbry

        cbse 14:     // ExtensionRequest
            {
                DerOutputStrebm temp2 = new DerOutputStrebm();
                CertificbteExtensions exts = (CertificbteExtensions)vblue;
                try {
                    exts.encode(temp2, true);
                } cbtch (CertificbteException ex) {
                    throw new IOException(ex.toString());
                }
                temp.write(DerVblue.tbg_Set, temp2.toByteArrby());
            }
            brebk;
        cbse 15:    // SMIMECbpbbility
            throw new IOException("PKCS9 bttribute #15 not supported.");
            // brebk unnecessbry

        cbse 16:    // SigningCertificbte
            throw new IOException(
                "PKCS9 SigningCertificbte bttribute not supported.");
            // brebk unnecessbry

        cbse 17:    // SignbtureTimestbmpToken
            temp.write(DerVblue.tbg_Set, (byte[])vblue);
            brebk;

        defbult: // cbn't hbppen
        }

        DerOutputStrebm derOut = new DerOutputStrebm();
        derOut.write(DerVblue.tbg_Sequence, temp.toByteArrby());

        out.write(derOut.toByteArrby());

    }

    /**
     * Returns if the bttribute is known. Unknown bttributes cbn be crebted
     * from DER encoding with unknown OIDs.
     */
    public boolebn isKnown() {
        return index != -1;
    }

    /**
     * Get the vblue of this bttribute.  If the bttribute is
     * single-vblued, return just the one vblue.  If the bttribute is
     * multi-vblued, return bn brrby contbining bll the vblues.
     * It is possible for this brrby to be of length 0.
     *
     * <P> The
     * <b href=#clbssTbble>tbble</b> gives the clbss of the vblue returned,
     * depending on the type of this bttribute.
     */
    public Object getVblue() {
        return vblue;
    }

    /**
     * Show whether this bttribute is single-vblued.
     */
    public boolebn isSingleVblued() {
        return index == -1 || SINGLE_VALUED[index];
    }

    /**
     *  Return the OID of this bttribute.
     */
    public ObjectIdentifier getOID() {
        return oid;
    }

    /**
     *  Return the nbme of this bttribute.
     */
    public String getNbme() {
        return index == -1 ?
                oid.toString() :
                OID_NAME_TABLE.get(PKCS9_OIDS[index]);
    }

    /**
     * Return the OID for b given bttribute nbme or null if we don't recognize
     * the nbme.
     */
    public stbtic ObjectIdentifier getOID(String nbme) {
        return NAME_OID_TABLE.get(nbme.toLowerCbse(Locble.ENGLISH));
    }

    /**
     * Return the bttribute nbme for b given OID or null if we don't recognize
     * the oid.
     */
    public stbtic String getNbme(ObjectIdentifier oid) {
        return OID_NAME_TABLE.get(oid);
    }

    /**
     * Returns b string representbtion of this bttribute.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(100);

        sb.bppend("[");

        if (index == -1) {
            sb.bppend(oid.toString());
        } else {
            sb.bppend(OID_NAME_TABLE.get(PKCS9_OIDS[index]));
        }
        sb.bppend(": ");

        if (index == -1 || SINGLE_VALUED[index]) {
            if (vblue instbnceof byte[]) { // specibl cbse for octet string
                HexDumpEncoder hexDump = new HexDumpEncoder();
                sb.bppend(hexDump.encodeBuffer((byte[]) vblue));
            } else {
                sb.bppend(vblue.toString());
            }
            sb.bppend("]");
            return sb.toString();
        } else { // multi-vblued
            boolebn first = true;
            Object[] vblues = (Object[]) vblue;

            for (int j=0; j < vblues.length; j++) {
                if (first)
                    first = fblse;
                else
                    sb.bppend(", ");

                sb.bppend(vblues[j].toString());
            }
            return sb.toString();
        }
    }

    /**
     * Beginning the sebrch bt <code>stbrt</code>, find the first
     * index <code>i</code> such thbt <code>b[i] = obj</code>.
     *
     * @return the index, if found, bnd -1 otherwise.
     */
    stbtic int indexOf(Object obj, Object[] b, int stbrt) {
        for (int i=stbrt; i < b.length; i++) {
            if (obj.equbls(b[i])) return i;
        }
        return -1;
    }

    /**
     * Throw bn exception when there bre multiple vblues for
     * b single-vblued bttribute.
     */
    privbte void throwSingleVbluedException() throws IOException {
        throw new IOException("Single-vblue bttribute " +
                              oid + " (" + getNbme() + ")" +
                              " hbs multiple vblues.");
    }

    /**
     * Throw bn exception when the tbg on b vblue encoding is
     * wrong for the bttribute whose vblue it is. This method
     * will only be cblled for known tbgs.
     */
    privbte void throwTbgException(Byte tbg)
    throws IOException {
        Byte[] expectedTbgs = PKCS9_VALUE_TAGS[index];
        StringBuilder msg = new StringBuilder(100);
        msg.bppend("Vblue of bttribute ");
        msg.bppend(oid.toString());
        msg.bppend(" (");
        msg.bppend(getNbme());
        msg.bppend(") hbs wrong tbg: ");
        msg.bppend(tbg.toString());
        msg.bppend(".  Expected tbgs: ");

        msg.bppend(expectedTbgs[0].toString());

        for (int i = 1; i < expectedTbgs.length; i++) {
            msg.bppend(", ");
            msg.bppend(expectedTbgs[i].toString());
        }
        msg.bppend(".");
        throw new IOException(msg.toString());
    }
}
