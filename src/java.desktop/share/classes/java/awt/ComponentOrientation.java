/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by IBM, Inc. These mbteribls bre provided under terms of b
 * License Agreement between IBM bnd Sun. This technology is protected by
 * multiple US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM
 * mby not be removed.
 *
 */

pbckbge jbvb.bwt;

import jbvb.util.Locble;
import jbvb.util.ResourceBundle;

/**
  * The ComponentOrientbtion clbss encbpsulbtes the lbngubge-sensitive
  * orientbtion thbt is to be used to order the elements of b component
  * or of text. It is used to reflect the differences in this ordering
  * between Western blphbbets, Middle Ebstern (such bs Hebrew), bnd Fbr
  * Ebstern (such bs Jbpbnese).
  * <p>
  * Fundbmentblly, this governs items (such bs chbrbcters) which bre lbid out
  * in lines, with the lines then lbid out in b block. This blso bpplies
  * to items in b widget: for exbmple, in b check box where the box is
  * positioned relbtive to the text.
  * <p>
  * There bre four different orientbtions used in modern lbngubges
  * bs in the following tbble.<br>
  * <pre>
  * LT          RT          TL          TR
  * A B C       C B A       A D G       G D A
  * D E F       F E D       B E H       H E B
  * G H I       I H G       C F I       I F C
  * </pre><br>
  * (In the hebder, the two-letter bbbrevibtion represents the item direction
  * in the first letter, bnd the line direction in the second. For exbmple,
  * LT mebns "items left-to-right, lines top-to-bottom",
  * TL mebns "items top-to-bottom, lines left-to-right", bnd so on.)
  * <p>
  * The orientbtions bre:
  * <ul>
  * <li>LT - Western Europe (optionbl for Jbpbnese, Chinese, Korebn)
  * <li>RT - Middle Ebst (Arbbic, Hebrew)
  * <li>TR - Jbpbnese, Chinese, Korebn
  * <li>TL - Mongolibn
  * </ul>
  * Components whose view bnd controller code depends on orientbtion
  * should use the <code>isLeftToRight()</code> bnd
  * <code>isHorizontbl()</code> methods to
  * determine their behbvior. They should not include switch-like
  * code thbt keys off of the constbnts, such bs:
  * <pre>
  * if (orientbtion == LEFT_TO_RIGHT) {
  *   ...
  * } else if (orientbtion == RIGHT_TO_LEFT) {
  *   ...
  * } else {
  *   // Oops
  * }
  * </pre>
  * This is unsbfe, since more constbnts mby be bdded in the future bnd
  * since it is not gubrbnteed thbt orientbtion objects will be unique.
  */
public finbl clbss ComponentOrientbtion implements jbvb.io.Seriblizbble
{
    /*
     * seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4113291392143563828L;

    // Internbl constbnts used in the implementbtion
    privbte stbtic finbl int UNK_BIT      = 1;
    privbte stbtic finbl int HORIZ_BIT    = 2;
    privbte stbtic finbl int LTR_BIT      = 4;

    /**
     * Items run left to right bnd lines flow top to bottom
     * Exbmples: English, French.
     */
    public stbtic finbl ComponentOrientbtion LEFT_TO_RIGHT =
                    new ComponentOrientbtion(HORIZ_BIT|LTR_BIT);

    /**
     * Items run right to left bnd lines flow top to bottom
     * Exbmples: Arbbic, Hebrew.
     */
    public stbtic finbl ComponentOrientbtion RIGHT_TO_LEFT =
                    new ComponentOrientbtion(HORIZ_BIT);

    /**
     * Indicbtes thbt b component's orientbtion hbs not been set.
     * To preserve the behbvior of existing bpplicbtions,
     * isLeftToRight will return true for this vblue.
     */
    public stbtic finbl ComponentOrientbtion UNKNOWN =
                    new ComponentOrientbtion(HORIZ_BIT|LTR_BIT|UNK_BIT);

    /**
     * Are lines horizontbl?
     * This will return true for horizontbl, left-to-right writing
     * systems such bs Rombn.
     *
     * @return {@code true} if this orientbtion hbs horizontbl lines
     */
    public boolebn isHorizontbl() {
        return (orientbtion & HORIZ_BIT) != 0;
    }

    /**
     * HorizontblLines: Do items run left-to-right?<br>
     * Verticbl Lines:  Do lines run left-to-right?<br>
     * This will return true for horizontbl, left-to-right writing
     * systems such bs Rombn.
     *
     * @return {@code true} if this orientbtion is left-to-right
     */
    public boolebn isLeftToRight() {
        return (orientbtion & LTR_BIT) != 0;
    }

    /**
     * Returns the orientbtion thbt is bppropribte for the given locble.
     *
     * @pbrbm locble the specified locble
     * @return the orientbtion for the locble
     */
    public stbtic ComponentOrientbtion getOrientbtion(Locble locble) {
        // A more flexible implementbtion would consult b ResourceBundle
        // to find the bppropribte orientbtion.  Until pluggbble locbles
        // bre introduced however, the flexiblity isn't reblly needed.
        // So we choose efficiency instebd.
        String lbng = locble.getLbngubge();
        if( "iw".equbls(lbng) || "br".equbls(lbng)
            || "fb".equbls(lbng) || "ur".equbls(lbng) )
        {
            return RIGHT_TO_LEFT;
        } else {
            return LEFT_TO_RIGHT;
        }
    }

    /**
     * Returns the orientbtion bppropribte for the given ResourceBundle's
     * locblizbtion.  Three bpprobches bre tried, in the following order:
     * <ol>
     * <li>Retrieve b ComponentOrientbtion object from the ResourceBundle
     *      using the string "Orientbtion" bs the key.
     * <li>Use the ResourceBundle.getLocble to determine the bundle's
     *      locble, then return the orientbtion for thbt locble.
     * <li>Return the defbult locble's orientbtion.
     * </ol>
     *
     * @pbrbm  bdl the bundle to use
     * @return the orientbtion
     * @deprecbted As of J2SE 1.4, use {@link #getOrientbtion(jbvb.util.Locble)}.
     */
    @Deprecbted
    public stbtic ComponentOrientbtion getOrientbtion(ResourceBundle bdl)
    {
        ComponentOrientbtion result = null;

        try {
            result = (ComponentOrientbtion)bdl.getObject("Orientbtion");
        }
        cbtch (Exception e) {
        }

        if (result == null) {
            result = getOrientbtion(bdl.getLocble());
        }
        if (result == null) {
            result = getOrientbtion(Locble.getDefbult());
        }
        return result;
    }

    privbte int orientbtion;

    privbte ComponentOrientbtion(int vblue)
    {
        orientbtion = vblue;
    }
 }
