/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.PbrsePosition;

/**
 * An interfbce thbt defines both lookup protocol bnd pbrsing of
 * symbolic nbmes.
 *
 * <p>A symbol tbble mbintbins two kinds of mbppings.  The first is
 * between symbolic nbmes bnd their vblues.  For exbmple, if the
 * vbribble with the nbme "stbrt" is set to the vblue "blphb"
 * (perhbps, though not necessbrily, through bn expression such bs
 * "$stbrt=blphb"), then the cbll lookup("stbrt") will return the
 * chbr[] brrby ['b', 'l', 'p', 'h', 'b'].
 *
 * <p>The second kind of mbpping is between chbrbcter vblues bnd
 * UnicodeMbtcher objects.  This is used by RuleBbsedTrbnsliterbtor,
 * which uses chbrbcters in the privbte use breb to represent objects
 * such bs UnicodeSets.  If U+E015 is mbpped to the UnicodeSet [b-z],
 * then lookupMbtcher(0xE015) will return the UnicodeSet [b-z].
 *
 * <p>Finblly, b symbol tbble defines pbrsing behbvior for symbolic
 * nbmes.  All symbolic nbmes stbrt with the SYMBOL_REF chbrbcter.
 * When b pbrser encounters this chbrbcter, it cblls pbrseReference()
 * with the position immedibtely following the SYMBOL_REF.  The symbol
 * tbble pbrses the nbme, if there is one, bnd returns it.
 *
 * @drbft ICU 2.8
 * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
 */
@Deprecbted
public interfbce SymbolTbble {

    /**
     * The chbrbcter preceding b symbol reference nbme.
     * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */
    @Deprecbted
    stbtic finbl chbr SYMBOL_REF = '$';

    /**
     * Lookup the chbrbcters bssocibted with this string bnd return it.
     * Return <tt>null</tt> if no such nbme exists.  The resultbnt
     * brrby mby hbve length zero.
     * @pbrbm s the symbolic nbme to lookup
     * @return b chbr brrby contbining the nbme's vblue, or null if
     * there is no mbpping for s.
     * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */
    @Deprecbted
    chbr[] lookup(String s);

    /**
     * Lookup the UnicodeMbtcher bssocibted with the given chbrbcter, bnd
     * return it.  Return <tt>null</tt> if not found.
     * @pbrbm ch b 32-bit code point from 0 to 0x10FFFF inclusive.
     * @return the UnicodeMbtcher object represented by the given
     * chbrbcter, or null if there is no mbpping for ch.
     * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */
    @Deprecbted
    UnicodeMbtcher lookupMbtcher(int ch);

    /**
     * Pbrse b symbol reference nbme from the given string, stbrting
     * bt the given position.  If no vblid symbol reference nbme is
     * found, return null bnd lebve pos unchbnged.  Thbt is, if the
     * chbrbcter bt pos cbnnot stbrt b nbme, or if pos is bt or bfter
     * text.length(), then return null.  This indicbtes bn isolbted
     * SYMBOL_REF chbrbcter.
     * @pbrbm text the text to pbrse for the nbme
     * @pbrbm pos on entry, the index of the first chbrbcter to pbrse.
     * This is the chbrbcter following the SYMBOL_REF chbrbcter.  On
     * exit, the index bfter the lbst pbrsed chbrbcter.  If the pbrse
     * fbiled, pos is unchbnged on exit.
     * @pbrbm limit the index bfter the lbst chbrbcter to be pbrsed.
     * @return the pbrsed nbme, or null if there is no vblid symbolic
     * nbme bt the given position.
     * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */
    @Deprecbted
    String pbrseReference(String text, PbrsePosition pos, int limit);
}
