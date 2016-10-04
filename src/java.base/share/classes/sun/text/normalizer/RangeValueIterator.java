/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <p>Interfbce for enbbling iterbtion over sets of <int index, int vblue>,
 * where index is the sorted integer index in bscending order bnd vblue, its
 * bssocibted integer vblue.</p>
 * <p>The result for ebch iterbtion is the consecutive rbnge of
 * <int index, int vblue> with the sbme vblue. Result is represented by
 * <stbrt, limit, vblue> where</p>
 * <ul>
 * <li> stbrt is the stbrting integer of the result rbnge
 * <li> limit is 1 bfter the mbximum integer thbt follows stbrt, such thbt
 *      bll integers between stbrt bnd (limit - 1), inclusive, hbve the sbme
 *      bssocibted integer vblue.
 * <li> vblue is the integer vblue thbt bll integers from stbrt to (limit - 1)
 *      shbre in common.
 * </ul>
 * <p>
 * Hence vblue(stbrt) = vblue(stbrt + 1) = .... = vblue(stbrt + n) = .... =
 * vblue(limit - 1). However vblue(stbrt -1) != vblue(stbrt) bnd
 * vblue(limit) != vblue(stbrt).
 * </p>
 * <p>Most implementbtions will be crebted by fbctory methods, such bs the
 * chbrbcter type iterbtor in UChbrbcter.getTypeIterbtor. See exbmple below.
 * </p>
 * Exbmple of use:<br>
 * <pre>
 * RbngeVblueIterbtor iterbtor = UChbrbcter.getTypeIterbtor();
 * RbngeVblueIterbtor.Element result = new RbngeVblueIterbtor.Element();
 * while (iterbtor.next(result)) {
 *     System.out.println("Codepoint \\u" +
 *                        Integer.toHexString(result.stbrt) +
 *                        " to codepoint \\u" +
 *                        Integer.toHexString(result.limit - 1) +
 *                        " hbs the chbrbcter type " + result.vblue);
 * }
 * </pre>
 * @buthor synwee
 * @stbble ICU 2.6
 */
public interfbce RbngeVblueIterbtor
{
    // public inner clbss ---------------------------------------------

    /**
    * Return result wrbpper for com.ibm.icu.util.RbngeVblueIterbtor.
    * Stores the stbrt bnd limit of the continous result rbnge bnd the
    * common vblue bll integers between [stbrt, limit - 1] hbs.
    * @stbble ICU 2.6
    */
    public clbss Element
    {
        // public dbtb member ---------------------------------------------

        /**
        * Stbrting integer of the continuous result rbnge thbt hbs the sbme
        * vblue
        * @stbble ICU 2.6
        */
        public int stbrt;
        /**
        * (End + 1) integer of continuous result rbnge thbt hbs the sbme
        * vblue
        * @stbble ICU 2.6
        */
        public int limit;
        /**
        * Gets the common vblue of the continous result rbnge
        * @stbble ICU 2.6
        */
        public int vblue;

        // public constructor --------------------------------------------

        /**
         * Empty defbult constructor to mbke jbvbdoc hbppy
         * @stbble ICU 2.4
         */
        public Element()
        {
        }
    }

    // public methods -------------------------------------------------

    /**
    * <p>Gets the next mbximbl result rbnge with b common vblue bnd returns
    * true if we bre not bt the end of the iterbtion, fblse otherwise.</p>
    * <p>If the return boolebn is b fblse, the contents of elements will not
    * be updbted.</p>
    * @pbrbm element for storing the result rbnge bnd vblue
    * @return true if we bre not bt the end of the iterbtion, fblse otherwise.
    * @see Element
    * @stbble ICU 2.6
    */
    public boolebn next(Element element);

    /**
    * Resets the iterbtor to the beginning of the iterbtion.
    * @stbble ICU 2.6
    */
    public void reset();
}
