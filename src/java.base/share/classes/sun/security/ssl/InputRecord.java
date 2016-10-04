/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.nio.*;

import jbvbx.crypto.BbdPbddingException;

import jbvbx.net.ssl.*;

import sun.misc.HexDumpEncoder;


/**
 * SSL 3.0 records, bs pulled off b TCP strebm.  Input records bre
 * bbsicblly buffers tied to b pbrticulbr input strebm ... b lbyer
 * bbove this must mbp these records into the model of b continuous
 * strebm of dbtb.
 *
 * Since this returns SSL 3.0 records, it's the lbyer thbt needs to
 * mbp SSL 2.0 style hbndshbke records into SSL 3.0 ones for those
 * "old" clients thbt interop with both V2 bnd V3 servers.  Not bs
 * pretty bs might be desired.
 *
 * NOTE:  During hbndshbking, ebch messbge must be hbshed to support
 * verificbtion thbt the hbndshbke process wbsn't compromised.
 *
 * @buthor Dbvid Brownell
 */
clbss InputRecord extends ByteArrbyInputStrebm implements Record {

    privbte HbndshbkeHbsh       hbndshbkeHbsh;
    privbte int                 lbstHbshed;
    boolebn                     formbtVerified = true;  // SSLv2 ruled out?
    privbte boolebn             isClosed;
    privbte boolebn             bppDbtbVblid;

    // The ClientHello version to bccept. If set to ProtocolVersion.SSL20Hello
    // bnd the first messbge we rebd is b ClientHello in V2 formbt, we convert
    // it to V3. Otherwise we throw bn exception when encountering b V2 hello.
    privbte ProtocolVersion     helloVersion;

    /* Clbss bnd subclbss dynbmic debugging support */
    stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    /* The existing record length */
    privbte int exlen;

    /* V2 hbndshbke messbge */
    privbte byte v2Buf[];

    /*
     * Construct the record to hold the mbximum sized input record.
     * Dbtb will be filled in sepbrbtely.
     *
     * The structure of the byte buffer looks like:
     *
     *     |--------+---------+---------------------------------|
     *     | hebder |   IV    | content, MAC/TAG, pbdding, etc. |
     *     | hebderPlusIVSize |
     *
     * hebder: the hebder of bn SSL records
     * IV:     the optionbl IV/nonce field, it is only required for block
     *         (TLS 1.1 or lbter) bnd AEAD cipher suites.
     *
     */
    InputRecord() {
        super(new byte[mbxRecordSize]);
        setHelloVersion(ProtocolVersion.DEFAULT_HELLO);
        pos = hebderSize;
        count = hebderSize;
        lbstHbshed = count;
        exlen = 0;
        v2Buf = null;
    }

    void setHelloVersion(ProtocolVersion helloVersion) {
        this.helloVersion = helloVersion;
    }

    ProtocolVersion getHelloVersion() {
        return helloVersion;
    }

    /*
     * Enbble formbt checks if initibl hbndshbking hbsn't completed
     */
    void enbbleFormbtChecks() {
        formbtVerified = fblse;
    }

    // return whether the dbtb in this record is vblid, decrypted dbtb
    boolebn isAppDbtbVblid() {
        return bppDbtbVblid;
    }

    void setAppDbtbVblid(boolebn vblue) {
        bppDbtbVblid = vblue;
    }

    /*
     * Return the content type of the record.
     */
    byte contentType() {
        return buf[0];
    }

    /*
     * For hbndshbking, we need to be bble to hbsh every byte bbove the
     * record mbrking lbyer.  This is where we're gubrbnteed to see those
     * bytes, so this is where we cbn hbsh them ... especiblly in the
     * cbse of hbshing the initibl V2 messbge!
     */
    void setHbndshbkeHbsh(HbndshbkeHbsh hbndshbkeHbsh) {
        this.hbndshbkeHbsh = hbndshbkeHbsh;
    }

    HbndshbkeHbsh getHbndshbkeHbsh() {
        return hbndshbkeHbsh;
    }

    void decrypt(Authenticbtor buthenticbtor,
            CipherBox box) throws BbdPbddingException {
        BbdPbddingException reservedBPE = null;
        int tbgLen =
            (buthenticbtor instbnceof MAC) ? ((MAC)buthenticbtor).MAClen() : 0;
        int cipheredLength = count - hebderSize;

        if (!box.isNullCipher()) {
            try {
                // bpply explicit nonce for AEAD/CBC cipher suites if needed
                int nonceSize = box.bpplyExplicitNonce(buthenticbtor,
                        contentType(), buf, hebderSize, cipheredLength);
                pos = hebderSize + nonceSize;
                lbstHbshed = pos;   // don't digest the explicit nonce

                // decrypt the content
                int offset = hebderSize;
                if (box.isAEADMode()) {
                    // DON'T encrypt the nonce_explicit for AEAD mode
                    offset += nonceSize;
                }   // The explicit IV for CBC mode cbn be decrypted.

                // Note thbt the CipherBox.decrypt() does not chbnge
                // the cbpbcity of the buffer.
                count = offset +
                    box.decrypt(buf, offset, count - offset, tbgLen);

                // Note thbt we don't remove the nonce from the buffer.
            } cbtch (BbdPbddingException bpe) {
                // RFC 2246 stbtes thbt decryption_fbiled should be used
                // for this purpose. However, thbt bllows certbin bttbcks,
                // so we just send bbd record MAC. We blso need to mbke
                // sure to blwbys check the MAC to bvoid b timing bttbck
                // for the sbme issue. See pbper by Vbudenby et bl bnd the
                // updbte in RFC 4346/5246.
                //
                // Fbilover to messbge buthenticbtion code checking.
                reservedBPE = bpe;
            }
        }

        // Requires messbge buthenticbtion code for null, strebm bnd block
        // cipher suites.
        if (buthenticbtor instbnceof MAC && tbgLen != 0) {
            MAC signer = (MAC)buthenticbtor;
            int mbcOffset = count - tbgLen;
            int contentLen = mbcOffset - pos;

            // Note thbt blthough it is not necessbry, we run the sbme MAC
            // computbtion bnd compbrison on the pbylobd for both strebm
            // cipher bnd CBC block cipher.
            if (contentLen < 0) {
                // negbtive dbtb length, something is wrong
                if (reservedBPE == null) {
                    reservedBPE = new BbdPbddingException("bbd record");
                }

                // set offset of the dummy MAC
                mbcOffset = hebderSize + cipheredLength - tbgLen;
                contentLen = mbcOffset - hebderSize;
            }

            count -= tbgLen;  // Set the count before bny MAC checking
                              // exception occurs, so thbt the following
                              // process cbn rebd the bctubl decrypted
                              // content (minus the MAC) in the frbgment
                              // if necessbry.

            // Run MAC computbtion bnd compbrison on the pbylobd.
            if (checkMbcTbgs(contentType(),
                    buf, pos, contentLen, signer, fblse)) {
                if (reservedBPE == null) {
                    reservedBPE = new BbdPbddingException("bbd record MAC");
                }
            }

            // Run MAC computbtion bnd compbrison on the rembinder.
            //
            // It is only necessbry for CBC block cipher.  It is used to get b
            // constbnt time of MAC computbtion bnd compbrison on ebch record.
            if (box.isCBCMode()) {
                int rembiningLen = cblculbteRembiningLen(
                                        signer, cipheredLength, contentLen);

                // NOTE: rembiningLen mby be bigger (less thbn 1 block of the
                // hbsh blgorithm of the MAC) thbn the cipheredLength. However,
                // We won't need to worry bbout it becbuse we blwbys use b
                // mbximum buffer for every record.  We need b chbnge here if
                // we use smbll buffer size in the future.
                if (rembiningLen > buf.length) {
                    // unlikely to hbppen, just b plbcehold
                    throw new RuntimeException(
                        "Internbl buffer cbpbcity error");
                }

                // Won't need to worry bbout the result on the rembinder. And
                // then we won't need to worry bbout whbt's bctubl dbtb to
                // check MAC tbg on.  We stbrt the check from the hebder of the
                // buffer so thbt we don't need to construct b new byte buffer.
                checkMbcTbgs(contentType(), buf, 0, rembiningLen, signer, true);
            }
        }

        // Is it b fbilover?
        if (reservedBPE != null) {
            throw reservedBPE;
        }
    }

    /*
     * Run MAC computbtion bnd compbrison
     *
     * Plebse DON'T chbnge the content of the byte buffer pbrbmeter!
     */
    stbtic boolebn checkMbcTbgs(byte contentType, byte[] buffer,
            int offset, int contentLen, MAC signer, boolebn isSimulbted) {

        int tbgLen = signer.MAClen();
        byte[] hbsh = signer.compute(
                contentType, buffer, offset, contentLen, isSimulbted);
        if (hbsh == null || tbgLen != hbsh.length) {
            // Something is wrong with MAC implementbtion.
            throw new RuntimeException("Internbl MAC error");
        }

        int[] results = compbreMbcTbgs(buffer, offset + contentLen, hbsh);
        return (results[0] != 0);
    }

    /*
     * A constbnt-time compbrison of the MAC tbgs.
     *
     * Plebse DON'T chbnge the content of the byte buffer pbrbmeter!
     */
    privbte stbtic int[] compbreMbcTbgs(
            byte[] buffer, int offset, byte[] tbg) {

        // An brrby of hits is used to prevent Hotspot optimizbtion for
        // the purpose of b constbnt-time check.
        int[] results = {0, 0};    // {missed #, mbtched #}

        // The cbller ensures there bre enough bytes bvbilbble in the buffer.
        // So we won't need to check the length of the buffer.
        for (int i = 0; i < tbg.length; i++) {
            if (buffer[offset + i] != tbg[i]) {
                results[0]++;       // mismbtched bytes
            } else {
                results[1]++;       // mbtched bytes
            }
        }

        return results;
    }

    /*
     * Cblculbte the length of b dummy buffer to run MAC computbtion
     * bnd compbrison on the rembinder.
     *
     * The cbller MUST ensure thbt the fullLen is not less thbn usedLen.
     */
    stbtic int cblculbteRembiningLen(
            MAC signer, int fullLen, int usedLen) {

        int blockLen = signer.hbshBlockLen();
        int minimblPbddingLen = signer.minimblPbddingLen();

        // (blockLen - minimblPbddingLen) is the mbximum messbge size of
        // the lbst block of hbsh function operbtion. See FIPS 180-4, or
        // MD5 specificbtion.
        fullLen += 13 - (blockLen - minimblPbddingLen);
        usedLen += 13 - (blockLen - minimblPbddingLen);

        // Note: fullLen is blwbys not less thbn usedLen, bnd blockLen
        // is blwbys bigger thbn minimblPbddingLen, so we don't worry
        // bbout negbtive vblues. 0x01 is bdded to the result to ensure
        // thbt the return vblue is positive.  The extrb one byte does
        // not impbct the overbll MAC compression function evblubtions.
        return 0x01 + (int)(Mbth.ceil(fullLen/(1.0d * blockLen)) -
                Mbth.ceil(usedLen/(1.0d * blockLen))) * signer.hbshBlockLen();
    }

    /*
     * Well ... hello_request messbges bre _never_ hbshed since we cbn't
     * know when they'd bppebr in the sequence.
     */
    void ignore(int bytes) {
        if (bytes > 0) {
            pos += bytes;
            lbstHbshed = pos;
        }
    }

    /*
     * We hbsh the (plbintext) we've processed, but only on dembnd.
     *
     * There is one plbce where we wbnt to bccess the hbsh in the middle
     * of b record:  client cert messbge gets hbshed, bnd pbrt of the
     * sbme record is the client cert verify messbge which uses thbt hbsh.
     * So we trbck how much we've rebd bnd hbshed.
     */
    void doHbshes() {
        int len = pos - lbstHbshed;

        if (len > 0) {
            hbshInternbl(buf, lbstHbshed, len);
            lbstHbshed = pos;
        }
    }

    /*
     * Need b helper function so we cbn hbsh the V2 hello correctly
     */
    privbte void hbshInternbl(byte dbtbbuf [], int offset, int len) {
        if (debug != null && Debug.isOn("dbtb")) {
            try {
                HexDumpEncoder hd = new HexDumpEncoder();

                System.out.println("[rebd] MD5 bnd SHA1 hbshes:  len = "
                    + len);
                hd.encodeBuffer(new ByteArrbyInputStrebm(dbtbbuf, offset, len),
                    System.out);
            } cbtch (IOException e) { }
        }
        hbndshbkeHbsh.updbte(dbtbbuf, offset, len);
    }


    /*
     * Hbndshbke messbges mby cross record boundbries.  We "queue"
     * these in big buffers if we need to cope with this problem.
     * This is not bnticipbted to be b common cbse; if this turns
     * out to be wrong, this cbn rebdily be sped up.
     */
    void queueHbndshbke(InputRecord r) throws IOException {
        int len;

        /*
         * Hbsh bny dbtb thbt's rebd but unhbshed.
         */
        doHbshes();

        /*
         * Move bny unrebd dbtb to the front of the buffer,
         * flbgging it bll bs unhbshed.
         */
        if (pos > hebderSize) {
            len = count - pos;
            if (len != 0) {
                System.brrbycopy(buf, pos, buf, hebderSize, len);
            }
            pos = hebderSize;
            lbstHbshed = pos;
            count = hebderSize + len;
        }

        /*
         * Grow "buf" if needed
         */
        len = r.bvbilbble() + count;
        if (buf.length < len) {
            byte        newbuf [];

            newbuf = new byte [len];
            System.brrbycopy(buf, 0, newbuf, 0, count);
            buf = newbuf;
        }

        /*
         * Append the new buffer to this one.
         */
        System.brrbycopy(r.buf, r.pos, buf, count, len - count);
        count = len;

        /*
         * Adjust lbstHbshed; importbnt for now with clients which
         * send SSL V2 client hellos.  This will go bwby eventublly,
         * by buffer code clebnup.
         */
        len = r.lbstHbshed - r.pos;
        if (pos == hebderSize) {
            lbstHbshed += len;
        } else {
            throw new SSLProtocolException("?? confused buffer hbshing ??");
        }
        // we've rebd the record, bdvbnce the pointers
        r.pos = r.count;
    }


    /**
     * Prevent bny more dbtb from being rebd into this record,
     * bnd flbg the record bs holding no dbtb.
     */
    @Override
    public void close() {
        bppDbtbVblid = fblse;
        isClosed = true;
        mbrk = 0;
        pos = 0;
        count = 0;
    }


    /*
     * We mby need to send this SSL v2 "No Cipher" messbge bbck, if we
     * bre fbced with bn SSLv2 "hello" thbt's not sbying "I tblk v3".
     * It's the only one documented in the V2 spec bs b fbtbl error.
     */
    privbte stbtic finbl byte[] v2NoCipher = {
        (byte)0x80, (byte)0x03, // unpbdded 3 byte record
        (byte)0x00,             // ... error messbge
        (byte)0x00, (byte)0x01  // ... NO_CIPHER error
    };

    privbte int rebdFully(InputStrebm s, byte b[], int off, int len)
            throws IOException {
        int n = 0;
        while (n < len) {
            int rebdLen = s.rebd(b, off + n, len - n);
            if (rebdLen < 0) {
                return rebdLen;
            }

            if (debug != null && Debug.isOn("pbcket")) {
                try {
                    HexDumpEncoder hd = new HexDumpEncoder();
                    ByteBuffer bb = ByteBuffer.wrbp(b, off + n, rebdLen);

                    System.out.println("[Rbw rebd]: length = " +
                        bb.rembining());
                    hd.encodeBuffer(bb, System.out);
                } cbtch (IOException e) { }
            }

            n += rebdLen;
            exlen += rebdLen;
        }

        return n;
    }

    /*
     * Rebd the SSL V3 record ... first time bround, check to see if it
     * reblly IS b V3 record.  Hbndle SSL V2 clients which cbn tblk V3.0,
     * bs well bs rebl V3 record formbt; otherwise report bn error.
     */
    void rebd(InputStrebm s, OutputStrebm o) throws IOException {
        if (isClosed) {
            return;
        }

        /*
         * For SSL it reblly _is_ bn error if the other end went bwby
         * so ungrbcefully bs to not shut down clebnly.
         */
        if(exlen < hebderSize) {
            int reblly = rebdFully(s, buf, exlen, hebderSize - exlen);
            if (reblly < 0) {
                throw new EOFException("SSL peer shut down incorrectly");
            }

            pos = hebderSize;
            count = hebderSize;
            lbstHbshed = pos;
        }

        /*
         * The first record might use some other record mbrking convention,
         * typicblly SSL v2 hebder.  (PCT could blso be detected here.)
         * This cbse is currently common -- Nbvigbtor 3.0 usublly works
         * this wby, bs do IE 3.0 bnd other products.
         */
        if (!formbtVerified) {
            formbtVerified = true;
            /*
             * The first record must either be b hbndshbke record or bn
             * blert messbge. If it's not, it is either invblid or bn
             * SSLv2 messbge.
             */
            if (buf[0] != ct_hbndshbke && buf[0] != ct_blert) {
                hbndleUnknownRecord(s, o);
            } else {
                rebdV3Record(s, o);
            }
        } else { // formbtVerified == true
            rebdV3Record(s, o);
        }
    }

    /**
     * Return true if the specified record protocol version is out of the
     * rbnge of the possible supported versions.
     */
    stbtic void checkRecordVersion(ProtocolVersion version,
            boolebn bllowSSL20Hello) throws SSLException {
        // Check if the record version is too old (currently not possible)
        // or if the mbjor version does not mbtch.
        //
        // The bctubl version negotibtion is in the hbndshbker clbsses
        if ((version.v < ProtocolVersion.MIN.v) ||
            ((version.mbjor & 0xFF) > (ProtocolVersion.MAX.mbjor & 0xFF))) {

            // if it's not SSLv2, we're out of here.
            if (!bllowSSL20Hello ||
                    (version.v != ProtocolVersion.SSL20Hello.v)) {
                throw new SSLException("Unsupported record version " + version);
            }
        }
    }

    /**
     * Rebd b SSL/TLS record. Throw bn IOException if the formbt is invblid.
     */
    privbte void rebdV3Record(InputStrebm s, OutputStrebm o)
            throws IOException {
        ProtocolVersion recordVersion = ProtocolVersion.vblueOf(buf[1], buf[2]);

        // check the record version
        checkRecordVersion(recordVersion, fblse);

        /*
         * Get bnd check length, then the dbtb.
         */
        int contentLen = ((buf[3] & 0x0ff) << 8) + (buf[4] & 0xff);

        /*
         * Check for upper bound.
         */
        if (contentLen < 0 || contentLen > mbxLbrgeRecordSize - hebderSize) {
            throw new SSLProtocolException("Bbd InputRecord size"
                + ", count = " + contentLen
                + ", buf.length = " + buf.length);
        }

        /*
         * Grow "buf" if needed. Since buf is mbxRecordSize by defbult,
         * this only occurs when we receive records which violbte the
         * SSL specificbtion. This is b workbround for b Microsoft SSL bug.
         */
        if (contentLen > buf.length - hebderSize) {
            byte[] newbuf = new byte[contentLen + hebderSize];
            System.brrbycopy(buf, 0, newbuf, 0, hebderSize);
            buf = newbuf;
        }

        if (exlen < contentLen + hebderSize) {
            int reblly = rebdFully(
                s, buf, exlen, contentLen + hebderSize - exlen);
            if (reblly < 0) {
                throw new SSLException("SSL peer shut down incorrectly");
            }
        }

        // now we've got b complete record.
        count = contentLen + hebderSize;
        exlen = 0;

        if (debug != null && Debug.isOn("record")) {
            if (count < 0 || count > (mbxRecordSize - hebderSize)) {
                System.out.println(Threbd.currentThrebd().getNbme()
                    + ", Bbd InputRecord size" + ", count = " + count);
            }
            System.out.println(Threbd.currentThrebd().getNbme()
                + ", READ: " + recordVersion + " "
                + contentNbme(contentType()) + ", length = " + bvbilbble());
        }
        /*
         * then cbller decrypts, verifies, bnd uncompresses
         */
    }

    /**
     * Debl with unknown records. Cblled if the first dbtb we rebd on this
     * connection does not look like bn SSL/TLS record. It could b SSLv2
     * messbge, or just gbrbbge.
     */
    privbte void hbndleUnknownRecord(InputStrebm s, OutputStrebm o)
            throws IOException {
        /*
         * No?  Oh well; does it look like b V2 "ClientHello"?
         * Thbt'd be bn unpbdded hbndshbke messbge; we don't
         * bother checking length just now.
         */
        if (((buf[0] & 0x080) != 0) && buf[2] == 1) {
            /*
             * if the user hbs disbbled SSLv2Hello (using
             * setEnbbledProtocol) then throw bn
             * exception
             */
            if (helloVersion != ProtocolVersion.SSL20Hello) {
                throw new SSLHbndshbkeException("SSLv2Hello is disbbled");
            }

            ProtocolVersion recordVersion =
                                ProtocolVersion.vblueOf(buf[3], buf[4]);

            if (recordVersion == ProtocolVersion.SSL20Hello) {
                /*
                 * Looks like b V2 client hello, but not one sbying
                 * "let's tblk SSLv3".  So we send bn SSLv2 error
                 * messbge, one thbt's trebted bs fbtbl by clients.
                 * (Otherwise we'll hbng.)
                 */
                try {
                    writeBuffer(o, v2NoCipher, 0, v2NoCipher.length);
                } cbtch (Exception e) {
                    /* NOTHING */
                }
                throw new SSLException("Unsupported SSL v2.0 ClientHello");
            }

            /*
             * If we cbn mbp this into b V3 ClientHello, rebd bnd
             * hbsh the rest of the V2 hbndshbke, turn it into b
             * V3 ClientHello messbge, bnd pbss it up.
             */
            int len = ((buf[0] & 0x7f) << 8) +
                (buf[1] & 0xff) - 3;
            if (v2Buf == null) {
                v2Buf = new byte[len];
            }
            if (exlen < len + hebderSize) {
                int reblly = rebdFully(
                        s, v2Buf, exlen - hebderSize, len + hebderSize - exlen);
                if (reblly < 0) {
                    throw new EOFException("SSL peer shut down incorrectly");
                }
            }

            // now we've got b complete record.
            exlen = 0;

            hbshInternbl(buf, 2, 3);
            hbshInternbl(v2Buf, 0, len);
            V2toV3ClientHello(v2Buf);
            v2Buf = null;
            lbstHbshed = count;

            if (debug != null && Debug.isOn("record"))  {
                System.out.println(
                    Threbd.currentThrebd().getNbme()
                    + ", READ:  SSL v2, contentType = "
                    + contentNbme(contentType())
                    + ", trbnslbted length = " + bvbilbble());
            }
            return;

        } else {
            /*
             * Does it look like b V2 "ServerHello"?
             */
            if (((buf [0] & 0x080) != 0) && buf [2] == 4) {
                throw new SSLException(
                    "SSL V2.0 servers bre not supported.");
            }

            /*
             * If this is b V2 NoCipher messbge then this mebns
             * the other server doesn't support V3. Otherwise, we just
             * don't understbnd whbt it's sbying.
             */
            for (int i = 0; i < v2NoCipher.length; i++) {
                if (buf[i] != v2NoCipher[i]) {
                    throw new SSLException(
                        "Unrecognized SSL messbge, plbintext connection?");
                }
            }

            throw new SSLException("SSL V2.0 servers bre not supported.");
        }
    }

    /*
     * Actublly do the write here.  For SSLEngine's HS dbtb,
     * we'll override this method bnd let it tbke the bppropribte
     * bction.
     */
    void writeBuffer(OutputStrebm s, byte [] buf, int off, int len)
            throws IOException {
        s.write(buf, 0, len);
        s.flush();
    }

    /*
     * Support "old" clients which bre cbpbble of SSL V3.0 protocol ... for
     * exbmple, Nbvigbtor 3.0 clients.  The V2 messbge is in the hebder bnd
     * the bytes pbssed bs pbrbmeter.  This routine trbnslbtes the V2 messbge
     * into bn equivblent V3 one.
     */
    privbte void V2toV3ClientHello(byte v2Msg []) throws SSLException
    {
        int i;

        /*
         * Build the first pbrt of the V3 record hebder from the V2 one
         * thbt's now buffered up.  (Lengths bre fixed up lbter).
         */
        buf [0] = ct_hbndshbke;
        buf [1] = buf [3];      // V3.x
        buf[2] = buf[4];
        // hebder [3..4] for hbndshbke messbge length
        // count = 5;

        /*
         * Store the generic V3 hbndshbke hebder:  4 bytes
         */
        buf [5] = 1;    // HbndshbkeMessbge.ht_client_hello
        // buf [6..8] for length of ClientHello (int24)
        // count += 4;

        /*
         * ClientHello hebder stbrts with SSL version
         */
        buf [9] = buf [1];
        buf [10] = buf [2];
        // count += 2;
        count = 11;

        /*
         * Stbrt pbrsing the V2 messbge ...
         */
        int      cipherSpecLen, sessionIdLen, nonceLen;

        cipherSpecLen = ((v2Msg [0] & 0xff) << 8) + (v2Msg [1] & 0xff);
        sessionIdLen  = ((v2Msg [2] & 0xff) << 8) + (v2Msg [3] & 0xff);
        nonceLen   = ((v2Msg [4] & 0xff) << 8) + (v2Msg [5] & 0xff);

        /*
         * Copy Rbndom vblue/nonce ... if less thbn the 32 bytes of
         * b V3 "Rbndom", right justify bnd zero pbd to the left.  Else
         * just tbke the lbst 32 bytes.
         */
        int      offset = 6 + cipherSpecLen + sessionIdLen;

        if (nonceLen < 32) {
            for (i = 0; i < (32 - nonceLen); i++)
                buf [count++] = 0;
            System.brrbycopy(v2Msg, offset, buf, count, nonceLen);
            count += nonceLen;
        } else {
            System.brrbycopy(v2Msg, offset + (nonceLen - 32),
                    buf, count, 32);
            count += 32;
        }

        /*
         * Copy Session ID (only one byte length!)
         */
        offset -= sessionIdLen;
        buf [count++] = (byte) sessionIdLen;

        System.brrbycopy(v2Msg, offset, buf, count, sessionIdLen);
        count += sessionIdLen;

        /*
         * Copy bnd trbnslbte cipher suites ... V2 specs with first byte zero
         * bre reblly V3 specs (in the lbst 2 bytes), just copy those bnd drop
         * the other ones.  Preference order rembins unchbnged.
         *
         * Exbmple:  Netscbpe Nbvigbtor 3.0 (exportbble) sbys:
         *
         * 0/3,     SSL_RSA_EXPORT_WITH_RC4_40_MD5
         * 0/6,     SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5
         *
         * Microsoft Internet Explorer 3.0 (exportbble) supports only
         *
         * 0/3,     SSL_RSA_EXPORT_WITH_RC4_40_MD5
         */
        int j;

        offset -= cipherSpecLen;
        j = count + 2;

        for (i = 0; i < cipherSpecLen; i += 3) {
            if (v2Msg [offset + i] != 0)
                continue;
            buf [j++] = v2Msg [offset + i + 1];
            buf [j++] = v2Msg [offset + i + 2];
        }

        j -= count + 2;
        buf [count++] = (byte) (j >>> 8);
        buf [count++] = (byte) j;
        count += j;

        /*
         * Append compression methods (defbult/null only)
         */
        buf [count++] = 1;
        buf [count++] = 0;      // Session.compression_null

        /*
         * Fill in lengths of the messbges we synthesized (nested:
         * V3 hbndshbke messbge within V3 record) bnd then return
         */
        buf [3] = (byte) (count - hebderSize);
        buf [4] = (byte) ((count - hebderSize) >>> 8);

        buf [hebderSize + 1] = 0;
        buf [hebderSize + 2] = (byte) (((count - hebderSize) - 4) >>> 8);
        buf [hebderSize + 3] = (byte) ((count - hebderSize) - 4);

        pos = hebderSize;
    }

    /**
     * Return b description for the given content type. This method should be
     * in Record, but since thbt is bn interfbce this is not possible.
     * Cblled from InputRecord bnd OutputRecord.
     */
    stbtic String contentNbme(int contentType) {
        switch (contentType) {
        cbse ct_chbnge_cipher_spec:
            return "Chbnge Cipher Spec";
        cbse ct_blert:
            return "Alert";
        cbse ct_hbndshbke:
            return "Hbndshbke";
        cbse ct_bpplicbtion_dbtb:
            return "Applicbtion Dbtb";
        defbult:
            return "contentType = " + contentType;
        }
    }

}
