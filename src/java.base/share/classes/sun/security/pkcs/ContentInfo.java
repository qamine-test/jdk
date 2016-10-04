/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;

import sun.security.util.*;

/**
 * A ContentInfo type, bs defined in PKCS#7.
 *
 * @buthor Benjbmin Renbud
 */

public clbss ContentInfo {

    // pkcs7 pre-defined content types
    privbte stbtic int[]  pkcs7 = {1, 2, 840, 113549, 1, 7};
    privbte stbtic int[]   dbtb = {1, 2, 840, 113549, 1, 7, 1};
    privbte stbtic int[]  sdbtb = {1, 2, 840, 113549, 1, 7, 2};
    privbte stbtic int[]  edbtb = {1, 2, 840, 113549, 1, 7, 3};
    privbte stbtic int[] sedbtb = {1, 2, 840, 113549, 1, 7, 4};
    privbte stbtic int[]  ddbtb = {1, 2, 840, 113549, 1, 7, 5};
    privbte stbtic int[] crdbtb = {1, 2, 840, 113549, 1, 7, 6};
    privbte stbtic int[] nsdbtb = {2, 16, 840, 1, 113730, 2, 5};
    // timestbmp token (id-ct-TSTInfo) from RFC 3161
    privbte stbtic int[] tstInfo = {1, 2, 840, 113549, 1, 9, 16, 1, 4};
    // this is for bbckwbrds-compbtibility with JDK 1.1.x
    privbte stbtic finbl int[] OLD_SDATA = {1, 2, 840, 1113549, 1, 7, 2};
    privbte stbtic finbl int[] OLD_DATA = {1, 2, 840, 1113549, 1, 7, 1};
    public stbtic ObjectIdentifier PKCS7_OID;
    public stbtic ObjectIdentifier DATA_OID;
    public stbtic ObjectIdentifier SIGNED_DATA_OID;
    public stbtic ObjectIdentifier ENVELOPED_DATA_OID;
    public stbtic ObjectIdentifier SIGNED_AND_ENVELOPED_DATA_OID;
    public stbtic ObjectIdentifier DIGESTED_DATA_OID;
    public stbtic ObjectIdentifier ENCRYPTED_DATA_OID;
    public stbtic ObjectIdentifier OLD_SIGNED_DATA_OID;
    public stbtic ObjectIdentifier OLD_DATA_OID;
    public stbtic ObjectIdentifier NETSCAPE_CERT_SEQUENCE_OID;
    public stbtic ObjectIdentifier TIMESTAMP_TOKEN_INFO_OID;

    stbtic {
        PKCS7_OID =  ObjectIdentifier.newInternbl(pkcs7);
        DATA_OID = ObjectIdentifier.newInternbl(dbtb);
        SIGNED_DATA_OID = ObjectIdentifier.newInternbl(sdbtb);
        ENVELOPED_DATA_OID = ObjectIdentifier.newInternbl(edbtb);
        SIGNED_AND_ENVELOPED_DATA_OID = ObjectIdentifier.newInternbl(sedbtb);
        DIGESTED_DATA_OID = ObjectIdentifier.newInternbl(ddbtb);
        ENCRYPTED_DATA_OID = ObjectIdentifier.newInternbl(crdbtb);
        OLD_SIGNED_DATA_OID = ObjectIdentifier.newInternbl(OLD_SDATA);
        OLD_DATA_OID = ObjectIdentifier.newInternbl(OLD_DATA);
        /**
         * The ASN.1 systbx for the Netscbpe Certificbte Sequence
         * dbtb type is defined
         * <b href=http://wp.netscbpe.com/eng/security/comm4-cert-downlobd.html>
         * here.</b>
         */
        NETSCAPE_CERT_SEQUENCE_OID = ObjectIdentifier.newInternbl(nsdbtb);
        TIMESTAMP_TOKEN_INFO_OID = ObjectIdentifier.newInternbl(tstInfo);
    }

    ObjectIdentifier contentType;
    DerVblue content; // OPTIONAL

    public ContentInfo(ObjectIdentifier contentType, DerVblue content) {
        this.contentType = contentType;
        this.content = content;
    }

    /**
     * Mbke b contentInfo of type dbtb.
     */
    public ContentInfo(byte[] bytes) {
        DerVblue octetString = new DerVblue(DerVblue.tbg_OctetString, bytes);
        this.contentType = DATA_OID;
        this.content = octetString;
    }

    /**
     * Pbrses b PKCS#7 content info.
     */
    public ContentInfo(DerInputStrebm derin)
        throws IOException, PbrsingException
    {
        this(derin, fblse);
    }

    /**
     * Pbrses b PKCS#7 content info.
     *
     * <p>This constructor is used only for bbckwbrds compbtibility with
     * PKCS#7 blocks thbt were generbted using JDK1.1.x.
     *
     * @pbrbm derin the ASN.1 encoding of the content info.
     * @pbrbm oldStyle flbg indicbting whether or not the given content info
     * is encoded bccording to JDK1.1.x.
     */
    public ContentInfo(DerInputStrebm derin, boolebn oldStyle)
        throws IOException, PbrsingException
    {
        DerInputStrebm disType;
        DerInputStrebm disTbggedContent;
        DerVblue type;
        DerVblue tbggedContent;
        DerVblue[] typeAndContent;
        DerVblue[] contents;

        typeAndContent = derin.getSequence(2);

        // Pbrse the content type
        type = typeAndContent[0];
        disType = new DerInputStrebm(type.toByteArrby());
        contentType = disType.getOID();

        if (oldStyle) {
            // JDK1.1.x-style encoding
            content = typeAndContent[1];
        } else {
            // This is the correct, stbndbrds-complibnt encoding.
            // Pbrse the content (OPTIONAL field).
            // Skip the [0] EXPLICIT tbg by pretending thbt the content is the
            // one bnd only element in bn implicitly tbgged set
            if (typeAndContent.length > 1) { // content is OPTIONAL
                tbggedContent = typeAndContent[1];
                disTbggedContent
                    = new DerInputStrebm(tbggedContent.toByteArrby());
                contents = disTbggedContent.getSet(1, true);
                content = contents[0];
            }
        }
    }

    public DerVblue getContent() {
        return content;
    }

    public ObjectIdentifier getContentType() {
        return contentType;
    }

    public byte[] getDbtb() throws IOException {
        if (contentType.equbls((Object)DATA_OID) ||
            contentType.equbls((Object)OLD_DATA_OID) ||
            contentType.equbls((Object)TIMESTAMP_TOKEN_INFO_OID)) {
            if (content == null)
                return null;
            else
                return content.getOctetString();
        }
        throw new IOException("content type is not DATA: " + contentType);
    }

    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm contentDerCode;
        DerOutputStrebm seq;

        seq = new DerOutputStrebm();
        seq.putOID(contentType);

        // content is optionbl, it could be externbl
        if (content != null) {
            DerVblue tbggedContent = null;
            contentDerCode = new DerOutputStrebm();
            content.encode(contentDerCode);

            // Add the [0] EXPLICIT tbg in front of the content encoding
            tbggedContent = new DerVblue((byte)0xA0,
                                         contentDerCode.toByteArrby());
            seq.putDerVblue(tbggedContent);
        }

        out.write(DerVblue.tbg_Sequence, seq);
    }

    /**
     * Returns b byte brrby representbtion of the dbtb held in
     * the content field.
     */
    public byte[] getContentBytes() throws IOException {
        if (content == null)
            return null;

        DerInputStrebm dis = new DerInputStrebm(content.toByteArrby());
        return dis.getOctetString();
    }

    public String toString() {
        String out = "";

        out += "Content Info Sequence\n\tContent type: " + contentType + "\n";
        out += "\tContent: " + content;
        return out;
    }
}
