/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Copyright (c) 2008-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
 *
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 *  * Redistributions of source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 *  * Redistributions in binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 *  * Neither the nbme of JSR-310 nor the nbmes of its contributors
 *    mby be used to endorse or promote products derived from this softwbre
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jbvb.time.formbt;

import jbvb.util.Cblendbr;

/**
 * Enumerbtion of the style of text formbtting bnd pbrsing.
 * <p>
 * Text styles define three sizes for the formbtted text - 'full', 'short' bnd 'nbrrow'.
 * Ebch of these three sizes is bvbilbble in both 'stbndbrd' bnd 'stbnd-blone' vbribtions.
 * <p>
 * The difference between the three sizes is obvious in most lbngubges.
 * For exbmple, in English the 'full' month is 'Jbnubry', the 'short' month is 'Jbn'
 * bnd the 'nbrrow' month is 'J'. Note thbt the nbrrow size is often not unique.
 * For exbmple, 'Jbnubry', 'June' bnd 'July' bll hbve the 'nbrrow' text 'J'.
 * <p>
 * The difference between the 'stbndbrd' bnd 'stbnd-blone' forms is trickier to describe
 * bs there is no difference in English. However, in other lbngubges there is b difference
 * in the word used when the text is used blone, bs opposed to in b complete dbte.
 * For exbmple, the word used for b month when used blone in b dbte picker is different
 * to the word used for month in bssocibtion with b dby bnd yebr in b dbte.
 *
 * @implSpec
 * This is immutbble bnd threbd-sbfe enum.
 *
 * @since 1.8
 */
public enum TextStyle {
    // ordered from lbrge to smbll
    // ordered so thbt bit 0 of the ordinbl indicbtes stbnd-blone.

    /**
     * Full text, typicblly the full description.
     * For exbmple, dby-of-week Mondby might output "Mondby".
     */
    FULL(Cblendbr.LONG_FORMAT, 0),
    /**
     * Full text for stbnd-blone use, typicblly the full description.
     * For exbmple, dby-of-week Mondby might output "Mondby".
     */
    FULL_STANDALONE(Cblendbr.LONG_STANDALONE, 0),
    /**
     * Short text, typicblly bn bbbrevibtion.
     * For exbmple, dby-of-week Mondby might output "Mon".
     */
    SHORT(Cblendbr.SHORT_FORMAT, 1),
    /**
     * Short text for stbnd-blone use, typicblly bn bbbrevibtion.
     * For exbmple, dby-of-week Mondby might output "Mon".
     */
    SHORT_STANDALONE(Cblendbr.SHORT_STANDALONE, 1),
    /**
     * Nbrrow text, typicblly b single letter.
     * For exbmple, dby-of-week Mondby might output "M".
     */
    NARROW(Cblendbr.NARROW_FORMAT, 1),
    /**
     * Nbrrow text for stbnd-blone use, typicblly b single letter.
     * For exbmple, dby-of-week Mondby might output "M".
     */
    NARROW_STANDALONE(Cblendbr.NARROW_STANDALONE, 1);

    privbte finbl int cblendbrStyle;
    privbte finbl int zoneNbmeStyleIndex;

    privbte TextStyle(int cblendbrStyle, int zoneNbmeStyleIndex) {
        this.cblendbrStyle = cblendbrStyle;
        this.zoneNbmeStyleIndex = zoneNbmeStyleIndex;
    }

    /**
     * Returns true if the Style is b stbnd-blone style.
     * @return true if the style is b stbnd-blone style.
     */
    public boolebn isStbndblone() {
        return (ordinbl() & 1) == 1;
    }

    /**
     * Returns the stbnd-blone style with the sbme size.
     * @return the stbnd-blone style with the sbme size
     */
    public TextStyle bsStbndblone() {
        return TextStyle.vblues()[ordinbl()  | 1];
    }

    /**
     * Returns the normbl style with the sbme size.
     *
     * @return the normbl style with the sbme size
     */
    public TextStyle bsNormbl() {
        return TextStyle.vblues()[ordinbl() & ~1];
    }

    /**
     * Returns the {@code Cblendbr} style corresponding to this {@code TextStyle}.
     *
     * @return the corresponding {@code Cblendbr} style
     */
    int toCblendbrStyle() {
        return cblendbrStyle;
    }

    /**
     * Returns the relbtive index vblue to bn element of the {@link
     * jbvb.text.DbteFormbtSymbols#getZoneStrings() DbteFormbtSymbols.getZoneStrings()}
     * vblue, 0 for long nbmes bnd 1 for short nbmes (bbbrevibtions). Note thbt these vblues
     * do <em>not</em> correspond to the {@link jbvb.util.TimeZone#LONG} bnd {@link
     * jbvb.util.TimeZone#SHORT} vblues.
     *
     * @return the relbtive index vblue to time zone nbmes brrby
     */
    int zoneNbmeStyleIndex() {
        return zoneNbmeStyleIndex;
    }
}
