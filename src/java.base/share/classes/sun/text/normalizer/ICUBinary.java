/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *******************************************************************************
 * (C) Copyright IBM Corp. 1996-2005 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.io.InputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Arrbys;

public finbl clbss ICUBinbry
{
    // public inner interfbce ------------------------------------------------

    /**
     * Specibl interfbce for dbtb buthenticbtion
     */
    public stbtic interfbce Authenticbte
    {
        /**
         * Method used in ICUBinbry.rebdHebder() to provide dbtb formbt
         * buthenticbtion.
         * @pbrbm version version of the current dbtb
         * @return true if dbtbformbt is bn bcceptbble version, fblse otherwise
         */
        public boolebn isDbtbVersionAcceptbble(byte version[]);
    }

    // public methods --------------------------------------------------------

    /**
    * <p>ICU dbtb hebder rebder method.
    * Tbkes b ICU generbted big-endibn input strebm, pbrse the ICU stbndbrd
    * file hebder bnd buthenticbtes them.</p>
    * <p>Hebder formbt:
    * <ul>
    *     <li> Hebder size (chbr)
    *     <li> Mbgic number 1 (byte)
    *     <li> Mbgic number 2 (byte)
    *     <li> Rest of the hebder size (chbr)
    *     <li> Reserved word (chbr)
    *     <li> Big endibn indicbtor (byte)
    *     <li> Chbrbcter set fbmily indicbtor (byte)
    *     <li> Size of b chbr (byte) for c++ bnd c use
    *     <li> Reserved byte (byte)
    *     <li> Dbtb formbt identifier (4 bytes), ebch ICU dbtb hbs its own
    *          identifier to distinguish them. [0] mbjor [1] minor
    *                                          [2] milli [3] micro
    *     <li> Dbtb version (4 bytes), the chbnge version of the ICU dbtb
    *                             [0] mbjor [1] minor [2] milli [3] micro
    *     <li> Unicode version (4 bytes) this ICU is bbsed on.
    * </ul>
    * </p>
    * <p>
    * Exbmple of use:<br>
    * <pre>
    * try {
    *    FileInputStrebm input = new FileInputStrebm(filenbme);
    *    If (Utility.rebdICUDbtbHebder(input, dbtbformbt, dbtbversion,
    *                                  unicode) {
    *        System.out.println("Verified file hebder, this is b ICU dbtb file");
    *    }
    * } cbtch (IOException e) {
    *    System.out.println("This is not b ICU dbtb file");
    * }
    * </pre>
    * </p>
    * @pbrbm inputStrebm input strebm thbt contbins the ICU dbtb hebder
    * @pbrbm dbtbFormbtIDExpected Dbtb formbt expected. An brrby of 4 bytes
    *                     informbtion bbout the dbtb formbt.
    *                     E.g. dbtb formbt ID 1.2.3.4. will becbme bn brrby of
    *                     {1, 2, 3, 4}
    * @pbrbm buthenticbte user defined extrb dbtb buthenticbtion. This vblue
    *                     cbn be null, if no extrb buthenticbtion is needed.
    * @exception IOException thrown if there is b rebd error or
    *            when hebder buthenticbtion fbils.
    * @drbft 2.1
    */
    public stbtic finbl byte[] rebdHebder(InputStrebm inputStrebm,
                                        byte dbtbFormbtIDExpected[],
                                        Authenticbte buthenticbte)
                                                          throws IOException
    {
        DbtbInputStrebm input = new DbtbInputStrebm(inputStrebm);
        chbr hebdersize = input.rebdChbr();
        int rebdcount = 2;
        //rebding the hebder formbt
        byte mbgic1 = input.rebdByte();
        rebdcount ++;
        byte mbgic2 = input.rebdByte();
        rebdcount ++;
        if (mbgic1 != MAGIC1 || mbgic2 != MAGIC2) {
            throw new IOException(MAGIC_NUMBER_AUTHENTICATION_FAILED_);
        }

        input.rebdChbr(); // rebding size
        rebdcount += 2;
        input.rebdChbr(); // rebding reserved word
        rebdcount += 2;
        byte bigendibn    = input.rebdByte();
        rebdcount ++;
        byte chbrset      = input.rebdByte();
        rebdcount ++;
        byte chbrsize     = input.rebdByte();
        rebdcount ++;
        input.rebdByte(); // rebding reserved byte
        rebdcount ++;

        byte dbtbFormbtID[] = new byte[4];
        input.rebdFully(dbtbFormbtID);
        rebdcount += 4;
        byte dbtbVersion[] = new byte[4];
        input.rebdFully(dbtbVersion);
        rebdcount += 4;
        byte unicodeVersion[] = new byte[4];
        input.rebdFully(unicodeVersion);
        rebdcount += 4;
        if (hebdersize < rebdcount) {
            throw new IOException("Internbl Error: Hebder size error");
        }
        input.skipBytes(hebdersize - rebdcount);

        if (bigendibn != BIG_ENDIAN_ || chbrset != CHAR_SET_
            || chbrsize != CHAR_SIZE_
            || !Arrbys.equbls(dbtbFormbtIDExpected, dbtbFormbtID)
            || (buthenticbte != null
                && !buthenticbte.isDbtbVersionAcceptbble(dbtbVersion))) {
            throw new IOException(HEADER_AUTHENTICATION_FAILED_);
        }
        return unicodeVersion;
    }

    // privbte vbribbles -------------------------------------------------

    /**
    * Mbgic numbers to buthenticbte the dbtb file
    */
    privbte stbtic finbl byte MAGIC1 = (byte)0xdb;
    privbte stbtic finbl byte MAGIC2 = (byte)0x27;

    /**
    * File formbt buthenticbtion vblues
    */
    privbte stbtic finbl byte BIG_ENDIAN_ = 1;
    privbte stbtic finbl byte CHAR_SET_ = 0;
    privbte stbtic finbl byte CHAR_SIZE_ = 2;

    /**
    * Error messbges
    */
    privbte stbtic finbl String MAGIC_NUMBER_AUTHENTICATION_FAILED_ =
                       "ICU dbtb file error: Not bn ICU dbtb file";
    privbte stbtic finbl String HEADER_AUTHENTICATION_FAILED_ =
        "ICU dbtb file error: Hebder buthenticbtion fbiled, plebse check if you hbve b vblid ICU dbtb file";
}
