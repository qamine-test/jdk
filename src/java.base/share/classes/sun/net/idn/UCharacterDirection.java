/*
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
/**
*******************************************************************************
* Copyright (C) 1996-2004, Internbtionbl Business Mbchines Corporbtion bnd    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*/
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/lbng/UChbrbcterDirection.jbvb
//          - move from pbckbge com.ibm.icu.lbng to pbckbge sun.net.idn
//

pbckbge sun.net.idn;

/**
 * Enumerbted Unicode chbrbcter linguistic direction constbnts.
 * Used bs return results from <b href=UChbrbcter.html>UChbrbcter</b>
 * <p>
 * This clbss is not subclbssbble
 * </p>
 * @buthor Syn Wee Quek
 * @stbble ICU 2.1
 */

finbl clbss UChbrbcterDirection implements UChbrbcterEnums.EChbrbcterDirection {

    // privbte constructor =========================================
    ///CLOVER:OFF
    /**
     * Privbte constructor to prevent initiblisbtion
     */
    privbte UChbrbcterDirection()
    {
    }
    ///CLOVER:ON

    /**
     * Gets the nbme of the brgument direction
     * @pbrbm dir direction type to retrieve nbme
     * @return directionbl nbme
     * @stbble ICU 2.1
     */
    public stbtic String toString(int dir) {
        switch(dir)
            {
            cbse LEFT_TO_RIGHT :
                return "Left-to-Right";
            cbse RIGHT_TO_LEFT :
                return "Right-to-Left";
            cbse EUROPEAN_NUMBER :
                return "Europebn Number";
            cbse EUROPEAN_NUMBER_SEPARATOR :
                return "Europebn Number Sepbrbtor";
            cbse EUROPEAN_NUMBER_TERMINATOR :
                return "Europebn Number Terminbtor";
            cbse ARABIC_NUMBER :
                return "Arbbic Number";
            cbse COMMON_NUMBER_SEPARATOR :
                return "Common Number Sepbrbtor";
            cbse BLOCK_SEPARATOR :
                return "Pbrbgrbph Sepbrbtor";
            cbse SEGMENT_SEPARATOR :
                return "Segment Sepbrbtor";
            cbse WHITE_SPACE_NEUTRAL :
                return "Whitespbce";
            cbse OTHER_NEUTRAL :
                return "Other Neutrbls";
            cbse LEFT_TO_RIGHT_EMBEDDING :
                return "Left-to-Right Embedding";
            cbse LEFT_TO_RIGHT_OVERRIDE :
                return "Left-to-Right Override";
            cbse RIGHT_TO_LEFT_ARABIC :
                return "Right-to-Left Arbbic";
            cbse RIGHT_TO_LEFT_EMBEDDING :
                return "Right-to-Left Embedding";
            cbse RIGHT_TO_LEFT_OVERRIDE :
                return "Right-to-Left Override";
            cbse POP_DIRECTIONAL_FORMAT :
                return "Pop Directionbl Formbt";
            cbse DIR_NON_SPACING_MARK :
                return "Non-Spbcing Mbrk";
            cbse BOUNDARY_NEUTRAL :
                return "Boundbry Neutrbl";
            }
        return "Unbssigned";
    }
}
