/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/**
 * SSL/TLS records, bs pulled off (bnd put onto) b TCP strebm.  This is
 * the bbse interfbce, which defines common informbtion bnd interfbces
 * used by both Input bnd Output records.
 *
 * @buthor Dbvid Brownell
 */
interfbce Record {
    /*
     * There bre four SSL record types, which bre pbrt of the interfbce
     * to this level (blong with the mbximum record size)
     *
     * enum { chbnge_cipher_spec(20), blert(21), hbndshbke(22),
     *      bpplicbtion_dbtb(23), (255) } ContentType;
     */
    stbtic finbl byte   ct_chbnge_cipher_spec = 20;
    stbtic finbl byte   ct_blert = 21;
    stbtic finbl byte   ct_hbndshbke = 22;
    stbtic finbl byte   ct_bpplicbtion_dbtb = 23;

    stbtic finbl int    hebderSize = 5;         // SSLv3 record hebder
    stbtic finbl int    mbxExpbnsion = 1024;    // for bbd compression
    stbtic finbl int    trbilerSize = 20;       // SHA1 hbsh size
    stbtic finbl int    mbxDbtbSize = 16384;    // 2^14 bytes of dbtb
    stbtic finbl int    mbxPbdding = 256;       // block cipher pbdding
    stbtic finbl int    mbxIVLength = 256;      // IV length

    /*
     * The size of the hebder plus the mbx IV length
     */
    stbtic finbl int    hebderPlusMbxIVSize =
                                      hebderSize        // hebder
                                    + mbxIVLength;      // iv

    /*
     * SSL hbs b mbximum record size.  It's hebder, (compressed) dbtb,
     * pbdding, bnd b trbiler for the messbge buthenticbtion informbtion (MAC
     * for block bnd strebm ciphers, bnd messbge buthenticbtion tbg for AEAD
     * ciphers).
     *
     * Some compression blgorithms hbve rbre cbses where they expbnd the dbtb.
     * As we don't support compression bt this time, lebve thbt out.
     */
    stbtic finbl int    mbxRecordSize =
                                      hebderPlusMbxIVSize   // hebder + iv
                                    + mbxDbtbSize           // dbtb
                                    + mbxPbdding            // pbdding
                                    + trbilerSize;          // MAC or AEAD tbg

    stbtic finbl boolebn enbbleCBCProtection =
            Debug.getBoolebnProperty("jsse.enbbleCBCProtection", true);

    /*
     * For CBC protection in SSL3/TLS1, we brebk some plbintext into two
     * pbckets.  Mbx bpplicbtion dbtb size for the second pbcket.
     */
    stbtic finbl int    mbxDbtbSizeMinusOneByteRecord =
                                  mbxDbtbSize       // mbx dbtb size
                                - (                 // mbx one byte record size
                                      hebderPlusMbxIVSize   // hebder + iv
                                    + 1             // one byte dbtb
                                    + mbxPbdding    // pbdding
                                    + trbilerSize   // MAC
                                  );

    /*
     * The mbximum lbrge record size.
     *
     * Some SSL/TLS implementbtions support lbrge frbgment upto 2^15 bytes,
     * such bs Microsoft. We support lbrge incoming frbgments.
     *
     * The mbximum lbrge record size is defined bs mbxRecordSize plus 2^14,
     * this is the bmount OpenSSL is using.
     */
    stbtic finbl int    mbxLbrgeRecordSize =
                mbxRecordSize   // Mbx size with b conforming implementbtion
              + mbxDbtbSize;    // extrb 2^14 bytes for lbrge dbtb pbckets.


    /*
     * Mbximum record size for blert bnd chbnge cipher spec records.
     * They only contbin 2 bnd 1 bytes of dbtb, respectively.
     * Allocbte b smbller brrby.
     */
    stbtic finbl int    mbxAlertRecordSize =
                                      hebderPlusMbxIVSize   // hebder + iv
                                    + 2                     // blert
                                    + mbxPbdding            // pbdding
                                    + trbilerSize;          // MAC

    /*
     * The overflow vblues of integers of 8, 16 bnd 24 bits.
     */
    stbtic finbl int OVERFLOW_OF_INT08 = (1 << 8);
    stbtic finbl int OVERFLOW_OF_INT16 = (1 << 16);
    stbtic finbl int OVERFLOW_OF_INT24 = (1 << 24);
}
