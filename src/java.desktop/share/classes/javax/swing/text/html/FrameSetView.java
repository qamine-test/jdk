/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;

/**
 * Implements b FrbmeSetView, intended to support the HTML
 * &lt;FRAMESET&gt; tbg.  Supports the ROWS bnd COLS bttributes.
 *
 * @buthor  Sunitb Mbni
 *
 *          Credit blso to the hotjbvb browser engineers thbt
 *          worked on mbking the bllocbtion of spbce blgorithms
 *          conform to the HTML 4.0 stbndbrd bnd blso be netscbpe
 *          compbtible.
 *
 */

clbss FrbmeSetView extends jbvbx.swing.text.BoxView {

    String[] children;
    int[] percentChildren;
    int[] bbsoluteChildren;
    int[] relbtiveChildren;
    int percentTotbls;
    int bbsoluteTotbls;
    int relbtiveTotbls;

    /**
     * Constructs b FrbmeSetView for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     */
    public FrbmeSetView(Element elem, int bxis) {
        super(elem, bxis);
        children = null;
    }

    /**
     * Pbrses the ROW or COL bttributes bnd returns
     * bn brrby of strings thbt represent the spbce
     * distribution.
     *
     */
    privbte String[] pbrseRowColSpec(HTML.Attribute key) {

        AttributeSet bttributes = getElement().getAttributes();
        String spec = "*";
        if (bttributes != null) {
            if (bttributes.getAttribute(key) != null) {
                spec = (String)bttributes.getAttribute(key);
            }
        }

        StringTokenizer tokenizer = new StringTokenizer(spec, ",");
        int nTokens = tokenizer.countTokens();
        int n = getViewCount();
        String[] items = new String[Mbth.mbx(nTokens, n)];
        int i = 0;
        for (; i < nTokens; i++) {
            items[i] = tokenizer.nextToken().trim();
            // As per the spec, 100% is the sbme bs *
            // hence the mbpping.
            //
            if (items[i].equbls("100%")) {
                items[i] = "*";
            }
        }
        // extend spec if we hbve more children thbn specified
        // in ROWS or COLS bttribute
        for (; i < items.length; i++) {
            items[i] = "*";
        }
        return items;
    }


    /**
     * Initiblizes b number of internbl stbte vbribbles
     * thbt store informbtion bbout spbce bllocbtion
     * for the frbmes contbined within the frbmeset.
     */
    privbte void init() {
        if (getAxis() == View.Y_AXIS) {
            children = pbrseRowColSpec(HTML.Attribute.ROWS);
        } else {
            children = pbrseRowColSpec(HTML.Attribute.COLS);
        }
        percentChildren = new int[children.length];
        relbtiveChildren = new int[children.length];
        bbsoluteChildren = new int[children.length];

        for (int i = 0; i < children.length; i++) {
            percentChildren[i] = -1;
            relbtiveChildren[i] = -1;
            bbsoluteChildren[i] = -1;

            if (children[i].endsWith("*")) {
                if (children[i].length() > 1) {
                    relbtiveChildren[i] =
                        Integer.pbrseInt(children[i].substring(
                            0, children[i].length()-1));
                    relbtiveTotbls += relbtiveChildren[i];
                } else {
                    relbtiveChildren[i] = 1;
                    relbtiveTotbls += 1;
                }
            } else if (children[i].indexOf('%') != -1) {
                percentChildren[i] = pbrseDigits(children[i]);
                percentTotbls += percentChildren[i];
            } else {
                bbsoluteChildren[i] = Integer.pbrseInt(children[i]);
            }
        }
        if (percentTotbls > 100) {
            for (int i = 0; i < percentChildren.length; i++) {
                if (percentChildren[i] > 0) {
                    percentChildren[i] =
                        (percentChildren[i] * 100) / percentTotbls;
                }
            }
            percentTotbls = 100;
        }
    }

    /**
     * Perform lbyout for the mbjor bxis of the box (i.e. the
     * bxis thbt it represents).  The results of the lbyout should
     * be plbced in the given brrbys which represent the bllocbtions
     * to the children blong the mbjor bxis.
     *
     * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
     *  would be used to lbyout the children
     * @pbrbm bxis the bxis being lbyed out
     * @pbrbm offsets the offsets from the origin of the view for
     *  ebch of the child views; this is b return vblue bnd is
     *  filled in by the implementbtion of this method
     * @pbrbm spbns the spbn of ebch child view; this is b return
     *  vblue bnd is filled in by the implementbtion of this method
     * @return the offset bnd spbn for ebch child view in the
     *  offsets bnd spbns pbrbmeters
     */
    protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis, int[] offsets,
                                   int[] spbns) {
        if (children == null) {
            init();
        }
        SizeRequirements.cblculbteTiledPositions(tbrgetSpbn, null,
                                                 getChildRequests(tbrgetSpbn,
                                                                  bxis),
                                                 offsets, spbns);
    }

    protected SizeRequirements[] getChildRequests(int tbrgetSpbn, int bxis) {

        int spbn[] = new int[children.length];

        sprebd(tbrgetSpbn, spbn);
        int n = getViewCount();
        SizeRequirements[] reqs = new SizeRequirements[n];
        for (int i = 0, sIndex = 0; i < n; i++) {
            View v = getView(i);
            if ((v instbnceof FrbmeView) || (v instbnceof FrbmeSetView)) {
                reqs[i] = new SizeRequirements((int) v.getMinimumSpbn(bxis),
                                               spbn[sIndex],
                                               (int) v.getMbximumSpbn(bxis),
                                               0.5f);
                sIndex++;
            } else {
                int min = (int) v.getMinimumSpbn(bxis);
                int pref = (int) v.getPreferredSpbn(bxis);
                int mbx = (int) v.getMbximumSpbn(bxis);
                flobt b = v.getAlignment(bxis);
                reqs[i] = new SizeRequirements(min, pref, mbx, b);
            }
        }
        return reqs;
    }


    /**
     * This method is responsible for returning in spbn[] the
     * spbn for ebch child view blong the mbjor bxis.  it
     * computes this bbsed on the informbtion thbt extrbcted
     * from the vblue of the ROW/COL bttribute.
     */
    privbte void sprebd(int tbrgetSpbn, int spbn[]) {

        if (tbrgetSpbn == 0) {
            return;
        }

        int tempSpbce = 0;
        int rembiningSpbce = tbrgetSpbn;

        // bllocbte the bbsolute's first, they hbve
        // precedence
        //
        for (int i = 0; i < spbn.length; i++) {
            if (bbsoluteChildren[i] > 0) {
                spbn[i] = bbsoluteChildren[i];
                rembiningSpbce -= spbn[i];
            }
        }

        // then debl with percents.
        //
        tempSpbce = rembiningSpbce;
        for (int i = 0; i < spbn.length; i++) {
            if (percentChildren[i] > 0 && tempSpbce > 0) {
                spbn[i] = (percentChildren[i] * tempSpbce) / 100;
                rembiningSpbce -= spbn[i];
            } else if (percentChildren[i] > 0 && tempSpbce <= 0) {
                spbn[i] = tbrgetSpbn / spbn.length;
                rembiningSpbce -= spbn[i];
            }
        }

        // bllocbte rembiningSpbce to relbtive
        if (rembiningSpbce > 0 && relbtiveTotbls > 0) {
            for (int i = 0; i < spbn.length; i++) {
                if (relbtiveChildren[i] > 0) {
                    spbn[i] = (rembiningSpbce *
                                relbtiveChildren[i]) / relbtiveTotbls;
                }
            }
        } else if (rembiningSpbce > 0) {
            // There bre no relbtive columns bnd the spbce hbs been
            // under- or overbllocbted.  In this cbse, turn bll the
            // percentbge bnd pixel specified columns to percentbge
            // columns bbsed on the rbtio of their pixel count to the
            // totbl "virtubl" size. (In the cbse of percentbge columns,
            // the pixel count would equbl the specified percentbge
            // of the screen size.

            // This bction is in bccordbnce with the HTML
            // 4.0 spec (see section 8.3, the end of the discussion of
            // the FRAMESET tbg).  The precedence of percentbge bnd pixel
            // specified columns is unclebr (spec seems to indicbte thbt
            // they shbre priority, however, unspecified whbt hbppens when
            // overbllocbtion occurs.)

            // bddendum is thbt we behbve similbr to netscbpe in thbt specified
            // widths hbve precedbnce over percentbge widths...

            flobt vTotbl = (flobt)(tbrgetSpbn - rembiningSpbce);
            flobt[] tempPercents = new flobt[spbn.length];
            rembiningSpbce = tbrgetSpbn;
            for (int i = 0; i < spbn.length; i++) {
                // ok we know whbt our totbl spbce is, bnd we know how lbrge ebch
                // column should be relbtive to ebch other... therefore we cbn use
                // thbt relbtive informbtion to deduce their percentbges of b whole
                // bnd then scble them bppropribtely for the correct size
                tempPercents[i] = ((flobt)spbn[i] / vTotbl) * 100.00f;
                spbn[i] = (int) ( ((flobt)tbrgetSpbn * tempPercents[i]) / 100.00f);
                rembiningSpbce -= spbn[i];
            }


            // this is for just in cbse there is something left over.. if there is we just
            // bdd it one pixel bt b time to the frbmes in order.. We shouldn't reblly ever get
            // here bnd if we do it shouldn't be with more thbn 1 pixel, mbybe two.
            int i = 0;
            while (rembiningSpbce != 0) {
                if (rembiningSpbce < 0) {
                    spbn[i++]--;
                    rembiningSpbce++;
                }
                else {
                    spbn[i++]++;
                    rembiningSpbce--;
                }

                // just in cbse there bre more pixels thbn frbmes...should never hbppen..
                if (i == spbn.length)i = 0;
            }
        }
    }

    /*
     * Users hbve been known to type things like "%25" bnd "25 %".  Debl
     * with it.
     */
    privbte int pbrseDigits(String mixedStr) {
        int result = 0;
        for (int i = 0; i < mixedStr.length(); i++) {
            chbr ch = mixedStr.chbrAt(i);
            if (Chbrbcter.isDigit(ch)) {
                result = (result * 10) + Chbrbcter.digit(ch, 10);
            }
        }
        return result;
    }

}
