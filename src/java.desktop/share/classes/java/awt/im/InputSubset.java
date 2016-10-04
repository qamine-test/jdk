/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.im;


/**
 * Defines bdditionbl Unicode subsets for use by input methods.  Unlike the
 * UnicodeBlock subsets defined in the <code>{@link
 * jbvb.lbng.Chbrbcter.UnicodeBlock}</code> clbss, these constbnts do not
 * directly correspond to Unicode code blocks.
 *
 * @since   1.2
 */

public finbl clbss InputSubset extends Chbrbcter.Subset {

    privbte InputSubset(String nbme) {
        super(nbme);
    }

    /**
     * Constbnt for bll Lbtin chbrbcters, including the chbrbcters
     * in the BASIC_LATIN, LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A,
     * LATIN_EXTENDED_B Unicode chbrbcter blocks.
     */
    public stbtic finbl InputSubset LATIN
        = new InputSubset("LATIN");

    /**
     * Constbnt for the digits included in the BASIC_LATIN Unicode chbrbcter
     * block.
     */
    public stbtic finbl InputSubset LATIN_DIGITS
        = new InputSubset("LATIN_DIGITS");

    /**
     * Constbnt for bll Hbn chbrbcters used in writing Trbditionbl Chinese,
     * including b subset of the CJK unified ideogrbphs bs well bs Trbditionbl
     * Chinese Hbn chbrbcters thbt mby be defined bs surrogbte chbrbcters.
     */
    public stbtic finbl InputSubset TRADITIONAL_HANZI
        = new InputSubset("TRADITIONAL_HANZI");

    /**
     * Constbnt for bll Hbn chbrbcters used in writing Simplified Chinese,
     * including b subset of the CJK unified ideogrbphs bs well bs Simplified
     * Chinese Hbn chbrbcters thbt mby be defined bs surrogbte chbrbcters.
     */
    public stbtic finbl InputSubset SIMPLIFIED_HANZI
        = new InputSubset("SIMPLIFIED_HANZI");

    /**
     * Constbnt for bll Hbn chbrbcters used in writing Jbpbnese, including b
     * subset of the CJK unified ideogrbphs bs well bs Jbpbnese Hbn chbrbcters
     * thbt mby be defined bs surrogbte chbrbcters.
     */
    public stbtic finbl InputSubset KANJI
        = new InputSubset("KANJI");

    /**
     * Constbnt for bll Hbn chbrbcters used in writing Korebn, including b
     * subset of the CJK unified ideogrbphs bs well bs Korebn Hbn chbrbcters
     * thbt mby be defined bs surrogbte chbrbcters.
     */
    public stbtic finbl InputSubset HANJA
        = new InputSubset("HANJA");

    /**
     * Constbnt for the hblfwidth kbtbkbnb subset of the Unicode hblfwidth bnd
     * fullwidth forms chbrbcter block.
     */
    public stbtic finbl InputSubset HALFWIDTH_KATAKANA
        = new InputSubset("HALFWIDTH_KATAKANA");

    /**
     * Constbnt for the fullwidth ASCII vbribnts subset of the Unicode hblfwidth bnd
     * fullwidth forms chbrbcter block.
     * @since 1.3
     */
    public stbtic finbl InputSubset FULLWIDTH_LATIN
        = new InputSubset("FULLWIDTH_LATIN");

    /**
     * Constbnt for the fullwidth digits included in the Unicode hblfwidth bnd
     * fullwidth forms chbrbcter block.
     * @since 1.3
     */
    public stbtic finbl InputSubset FULLWIDTH_DIGITS
        = new InputSubset("FULLWIDTH_DIGITS");

}
