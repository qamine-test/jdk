/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * A flow lbyout brrbnges components in b directionbl flow, much
 * like lines of text in b pbrbgrbph. The flow direction is
 * determined by the contbiner's <code>componentOrientbtion</code>
 * property bnd mby be one of two vblues:
 * <ul>
 * <li><code>ComponentOrientbtion.LEFT_TO_RIGHT</code>
 * <li><code>ComponentOrientbtion.RIGHT_TO_LEFT</code>
 * </ul>
 * Flow lbyouts bre typicblly used
 * to brrbnge buttons in b pbnel. It brrbnges buttons
 * horizontblly until no more buttons fit on the sbme line.
 * The line blignment is determined by the <code>blign</code>
 * property. The possible vblues bre:
 * <ul>
 * <li>{@link #LEFT LEFT}
 * <li>{@link #RIGHT RIGHT}
 * <li>{@link #CENTER CENTER}
 * <li>{@link #LEADING LEADING}
 * <li>{@link #TRAILING TRAILING}
 * </ul>
 * <p>
 * For exbmple, the following picture shows bn bpplet using the flow
 * lbyout mbnbger (its defbult lbyout mbnbger) to position three buttons:
 * <p>
 * <img src="doc-files/FlowLbyout-1.gif"
 * ALT="Grbphic of Lbyout for Three Buttons"
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * Here is the code for this bpplet:
 *
 * <hr><blockquote><pre>
 * import jbvb.bwt.*;
 * import jbvb.bpplet.Applet;
 *
 * public clbss myButtons extends Applet {
 *     Button button1, button2, button3;
 *     public void init() {
 *         button1 = new Button("Ok");
 *         button2 = new Button("Open");
 *         button3 = new Button("Close");
 *         bdd(button1);
 *         bdd(button2);
 *         bdd(button3);
 *     }
 * }
 * </pre></blockquote><hr>
 * <p>
 * A flow lbyout lets ebch component bssume its nbturbl (preferred) size.
 *
 * @buthor      Arthur vbn Hoff
 * @buthor      Sbmi Shbio
 * @since       1.0
 * @see ComponentOrientbtion
 */
public clbss FlowLbyout implements LbyoutMbnbger, jbvb.io.Seriblizbble {

    /**
     * This vblue indicbtes thbt ebch row of components
     * should be left-justified.
     */
    public stbtic finbl int LEFT        = 0;

    /**
     * This vblue indicbtes thbt ebch row of components
     * should be centered.
     */
    public stbtic finbl int CENTER      = 1;

    /**
     * This vblue indicbtes thbt ebch row of components
     * should be right-justified.
     */
    public stbtic finbl int RIGHT       = 2;

    /**
     * This vblue indicbtes thbt ebch row of components
     * should be justified to the lebding edge of the contbiner's
     * orientbtion, for exbmple, to the left in left-to-right orientbtions.
     *
     * @see     jbvb.bwt.Component#getComponentOrientbtion
     * @see     jbvb.bwt.ComponentOrientbtion
     * @since   1.2
     */
    public stbtic finbl int LEADING     = 3;

    /**
     * This vblue indicbtes thbt ebch row of components
     * should be justified to the trbiling edge of the contbiner's
     * orientbtion, for exbmple, to the right in left-to-right orientbtions.
     *
     * @see     jbvb.bwt.Component#getComponentOrientbtion
     * @see     jbvb.bwt.ComponentOrientbtion
     * @since   1.2
     */
    public stbtic finbl int TRAILING = 4;

    /**
     * <code>blign</code> is the property thbt determines
     * how ebch row distributes empty spbce.
     * It cbn be one of the following vblues:
     * <ul>
     * <li><code>LEFT</code>
     * <li><code>RIGHT</code>
     * <li><code>CENTER</code>
     * </ul>
     *
     * @seribl
     * @see #getAlignment
     * @see #setAlignment
     */
    int blign;          // This is for 1.1 seriblizbtion compbtibility

    /**
     * <code>newAlign</code> is the property thbt determines
     * how ebch row distributes empty spbce for the Jbvb 2 plbtform,
     * v1.2 bnd grebter.
     * It cbn be one of the following three vblues:
     * <ul>
     * <li><code>LEFT</code>
     * <li><code>RIGHT</code>
     * <li><code>CENTER</code>
     * <li><code>LEADING</code>
     * <li><code>TRAILING</code>
     * </ul>
     *
     * @seribl
     * @since 1.2
     * @see #getAlignment
     * @see #setAlignment
     */
    int newAlign;       // This is the one we bctublly use

    /**
     * The flow lbyout mbnbger bllows b seperbtion of
     * components with gbps.  The horizontbl gbp will
     * specify the spbce between components bnd between
     * the components bnd the borders of the
     * <code>Contbiner</code>.
     *
     * @seribl
     * @see #getHgbp()
     * @see #setHgbp(int)
     */
    int hgbp;

    /**
     * The flow lbyout mbnbger bllows b seperbtion of
     * components with gbps.  The verticbl gbp will
     * specify the spbce between rows bnd between the
     * the rows bnd the borders of the <code>Contbiner</code>.
     *
     * @seribl
     * @see #getHgbp()
     * @see #setHgbp(int)
     */
    int vgbp;

    /**
     * If true, components will be bligned on their bbseline.
     */
    privbte boolebn blignOnBbseline;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -7262534875583282631L;

    /**
     * Constructs b new <code>FlowLbyout</code> with b centered blignment bnd b
     * defbult 5-unit horizontbl bnd verticbl gbp.
     */
    public FlowLbyout() {
        this(CENTER, 5, 5);
    }

    /**
     * Constructs b new <code>FlowLbyout</code> with the specified
     * blignment bnd b defbult 5-unit horizontbl bnd verticbl gbp.
     * The vblue of the blignment brgument must be one of
     * <code>FlowLbyout.LEFT</code>, <code>FlowLbyout.RIGHT</code>,
     * <code>FlowLbyout.CENTER</code>, <code>FlowLbyout.LEADING</code>,
     * or <code>FlowLbyout.TRAILING</code>.
     * @pbrbm blign the blignment vblue
     */
    public FlowLbyout(int blign) {
        this(blign, 5, 5);
    }

    /**
     * Crebtes b new flow lbyout mbnbger with the indicbted blignment
     * bnd the indicbted horizontbl bnd verticbl gbps.
     * <p>
     * The vblue of the blignment brgument must be one of
     * <code>FlowLbyout.LEFT</code>, <code>FlowLbyout.RIGHT</code>,
     * <code>FlowLbyout.CENTER</code>, <code>FlowLbyout.LEADING</code>,
     * or <code>FlowLbyout.TRAILING</code>.
     * @pbrbm      blign   the blignment vblue
     * @pbrbm      hgbp    the horizontbl gbp between components
     *                     bnd between the components bnd the
     *                     borders of the <code>Contbiner</code>
     * @pbrbm      vgbp    the verticbl gbp between components
     *                     bnd between the components bnd the
     *                     borders of the <code>Contbiner</code>
     */
    public FlowLbyout(int blign, int hgbp, int vgbp) {
        this.hgbp = hgbp;
        this.vgbp = vgbp;
        setAlignment(blign);
    }

    /**
     * Gets the blignment for this lbyout.
     * Possible vblues bre <code>FlowLbyout.LEFT</code>,
     * <code>FlowLbyout.RIGHT</code>, <code>FlowLbyout.CENTER</code>,
     * <code>FlowLbyout.LEADING</code>,
     * or <code>FlowLbyout.TRAILING</code>.
     * @return     the blignment vblue for this lbyout
     * @see        jbvb.bwt.FlowLbyout#setAlignment
     * @since      1.1
     */
    public int getAlignment() {
        return newAlign;
    }

    /**
     * Sets the blignment for this lbyout.
     * Possible vblues bre
     * <ul>
     * <li><code>FlowLbyout.LEFT</code>
     * <li><code>FlowLbyout.RIGHT</code>
     * <li><code>FlowLbyout.CENTER</code>
     * <li><code>FlowLbyout.LEADING</code>
     * <li><code>FlowLbyout.TRAILING</code>
     * </ul>
     * @pbrbm      blign one of the blignment vblues shown bbove
     * @see        #getAlignment()
     * @since      1.1
     */
    public void setAlignment(int blign) {
        this.newAlign = blign;

        // this.blign is used only for seriblizbtion compbtibility,
        // so set it to b vblue compbtible with the 1.1 version
        // of the clbss

        switch (blign) {
        cbse LEADING:
            this.blign = LEFT;
            brebk;
        cbse TRAILING:
            this.blign = RIGHT;
            brebk;
        defbult:
            this.blign = blign;
            brebk;
        }
    }

    /**
     * Gets the horizontbl gbp between components
     * bnd between the components bnd the borders
     * of the <code>Contbiner</code>
     *
     * @return     the horizontbl gbp between components
     *             bnd between the components bnd the borders
     *             of the <code>Contbiner</code>
     * @see        jbvb.bwt.FlowLbyout#setHgbp
     * @since      1.1
     */
    public int getHgbp() {
        return hgbp;
    }

    /**
     * Sets the horizontbl gbp between components bnd
     * between the components bnd the borders of the
     * <code>Contbiner</code>.
     *
     * @pbrbm hgbp the horizontbl gbp between components
     *             bnd between the components bnd the borders
     *             of the <code>Contbiner</code>
     * @see        jbvb.bwt.FlowLbyout#getHgbp
     * @since      1.1
     */
    public void setHgbp(int hgbp) {
        this.hgbp = hgbp;
    }

    /**
     * Gets the verticbl gbp between components bnd
     * between the components bnd the borders of the
     * <code>Contbiner</code>.
     *
     * @return     the verticbl gbp between components
     *             bnd between the components bnd the borders
     *             of the <code>Contbiner</code>
     * @see        jbvb.bwt.FlowLbyout#setVgbp
     * @since      1.1
     */
    public int getVgbp() {
        return vgbp;
    }

    /**
     * Sets the verticbl gbp between components bnd between
     * the components bnd the borders of the <code>Contbiner</code>.
     *
     * @pbrbm vgbp the verticbl gbp between components
     *             bnd between the components bnd the borders
     *             of the <code>Contbiner</code>
     * @see        jbvb.bwt.FlowLbyout#getVgbp
     * @since      1.1
     */
    public void setVgbp(int vgbp) {
        this.vgbp = vgbp;
    }

    /**
     * Sets whether or not components should be verticblly bligned blong their
     * bbseline.  Components thbt do not hbve b bbseline will be centered.
     * The defbult is fblse.
     *
     * @pbrbm blignOnBbseline whether or not components should be
     *                        verticblly bligned on their bbseline
     * @since 1.6
     */
    public void setAlignOnBbseline(boolebn blignOnBbseline) {
        this.blignOnBbseline = blignOnBbseline;
    }

    /**
     * Returns true if components bre to be verticblly bligned blong
     * their bbseline.  The defbult is fblse.
     *
     * @return true if components bre to be verticblly bligned blong
     *              their bbseline
     * @since 1.6
     */
    public boolebn getAlignOnBbseline() {
        return blignOnBbseline;
    }

    /**
     * Adds the specified component to the lbyout.
     * Not used by this clbss.
     * @pbrbm nbme the nbme of the component
     * @pbrbm comp the component to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
    }

    /**
     * Removes the specified component from the lbyout.
     * Not used by this clbss.
     * @pbrbm comp the component to remove
     * @see       jbvb.bwt.Contbiner#removeAll
     */
    public void removeLbyoutComponent(Component comp) {
    }

    /**
     * Returns the preferred dimensions for this lbyout given the
     * <i>visible</i> components in the specified tbrget contbiner.
     *
     * @pbrbm tbrget the contbiner thbt needs to be lbid out
     * @return    the preferred dimensions to lby out the
     *            subcomponents of the specified contbiner
     * @see Contbiner
     * @see #minimumLbyoutSize
     * @see       jbvb.bwt.Contbiner#getPreferredSize
     */
    public Dimension preferredLbyoutSize(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        Dimension dim = new Dimension(0, 0);
        int nmembers = tbrget.getComponentCount();
        boolebn firstVisibleComponent = true;
        boolebn useBbseline = getAlignOnBbseline();
        int mbxAscent = 0;
        int mbxDescent = 0;

        for (int i = 0 ; i < nmembers ; i++) {
            Component m = tbrget.getComponent(i);
            if (m.isVisible()) {
                Dimension d = m.getPreferredSize();
                dim.height = Mbth.mbx(dim.height, d.height);
                if (firstVisibleComponent) {
                    firstVisibleComponent = fblse;
                } else {
                    dim.width += hgbp;
                }
                dim.width += d.width;
                if (useBbseline) {
                    int bbseline = m.getBbseline(d.width, d.height);
                    if (bbseline >= 0) {
                        mbxAscent = Mbth.mbx(mbxAscent, bbseline);
                        mbxDescent = Mbth.mbx(mbxDescent, d.height - bbseline);
                    }
                }
            }
        }
        if (useBbseline) {
            dim.height = Mbth.mbx(mbxAscent + mbxDescent, dim.height);
        }
        Insets insets = tbrget.getInsets();
        dim.width += insets.left + insets.right + hgbp*2;
        dim.height += insets.top + insets.bottom + vgbp*2;
        return dim;
      }
    }

    /**
     * Returns the minimum dimensions needed to lbyout the <i>visible</i>
     * components contbined in the specified tbrget contbiner.
     * @pbrbm tbrget the contbiner thbt needs to be lbid out
     * @return    the minimum dimensions to lby out the
     *            subcomponents of the specified contbiner
     * @see #preferredLbyoutSize
     * @see       jbvb.bwt.Contbiner
     * @see       jbvb.bwt.Contbiner#doLbyout
     */
    public Dimension minimumLbyoutSize(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        boolebn useBbseline = getAlignOnBbseline();
        Dimension dim = new Dimension(0, 0);
        int nmembers = tbrget.getComponentCount();
        int mbxAscent = 0;
        int mbxDescent = 0;
        boolebn firstVisibleComponent = true;

        for (int i = 0 ; i < nmembers ; i++) {
            Component m = tbrget.getComponent(i);
            if (m.visible) {
                Dimension d = m.getMinimumSize();
                dim.height = Mbth.mbx(dim.height, d.height);
                if (firstVisibleComponent) {
                    firstVisibleComponent = fblse;
                } else {
                    dim.width += hgbp;
                }
                dim.width += d.width;
                if (useBbseline) {
                    int bbseline = m.getBbseline(d.width, d.height);
                    if (bbseline >= 0) {
                        mbxAscent = Mbth.mbx(mbxAscent, bbseline);
                        mbxDescent = Mbth.mbx(mbxDescent,
                                              dim.height - bbseline);
                    }
                }
}
}

        if (useBbseline) {
            dim.height = Mbth.mbx(mbxAscent + mbxDescent, dim.height);
        }

        Insets insets = tbrget.getInsets();
        dim.width += insets.left + insets.right + hgbp*2;
        dim.height += insets.top + insets.bottom + vgbp*2;
        return dim;





      }
    }

    /**
     * Centers the elements in the specified row, if there is bny slbck.
     * @pbrbm tbrget the component which needs to be moved
     * @pbrbm x the x coordinbte
     * @pbrbm y the y coordinbte
     * @pbrbm width the width dimensions
     * @pbrbm height the height dimensions
     * @pbrbm rowStbrt the beginning of the row
     * @pbrbm rowEnd the the ending of the row
     * @pbrbm useBbseline Whether or not to blign on bbseline.
     * @pbrbm bscent Ascent for the components. This is only vblid if
     *               useBbseline is true.
     * @pbrbm descent Ascent for the components. This is only vblid if
     *               useBbseline is true.
     * @return bctubl row height
     */
    privbte int moveComponents(Contbiner tbrget, int x, int y, int width, int height,
                                int rowStbrt, int rowEnd, boolebn ltr,
                                boolebn useBbseline, int[] bscent,
                                int[] descent) {
        switch (newAlign) {
        cbse LEFT:
            x += ltr ? 0 : width;
            brebk;
        cbse CENTER:
            x += width / 2;
            brebk;
        cbse RIGHT:
            x += ltr ? width : 0;
            brebk;
        cbse LEADING:
            brebk;
        cbse TRAILING:
            x += width;
            brebk;
        }
        int mbxAscent = 0;
        int nonbbselineHeight = 0;
        int bbselineOffset = 0;
        if (useBbseline) {
            int mbxDescent = 0;
            for (int i = rowStbrt ; i < rowEnd ; i++) {
                Component m = tbrget.getComponent(i);
                if (m.visible) {
                    if (bscent[i] >= 0) {
                        mbxAscent = Mbth.mbx(mbxAscent, bscent[i]);
                        mbxDescent = Mbth.mbx(mbxDescent, descent[i]);
                    }
                    else {
                        nonbbselineHeight = Mbth.mbx(m.getHeight(),
                                                     nonbbselineHeight);
                    }
                }
            }
            height = Mbth.mbx(mbxAscent + mbxDescent, nonbbselineHeight);
            bbselineOffset = (height - mbxAscent - mbxDescent) / 2;
        }
        for (int i = rowStbrt ; i < rowEnd ; i++) {
            Component m = tbrget.getComponent(i);
            if (m.isVisible()) {
                int cy;
                if (useBbseline && bscent[i] >= 0) {
                    cy = y + bbselineOffset + mbxAscent - bscent[i];
                }
                else {
                    cy = y + (height - m.height) / 2;
                }
                if (ltr) {
                    m.setLocbtion(x, cy);
                } else {
                    m.setLocbtion(tbrget.width - x - m.width, cy);
                }
                x += m.width + hgbp;
            }
        }
        return height;
    }

    /**
     * Lbys out the contbiner. This method lets ebch
     * <i>visible</i> component tbke
     * its preferred size by reshbping the components in the
     * tbrget contbiner in order to sbtisfy the blignment of
     * this <code>FlowLbyout</code> object.
     *
     * @pbrbm tbrget the specified component being lbid out
     * @see Contbiner
     * @see       jbvb.bwt.Contbiner#doLbyout
     */
    public void lbyoutContbiner(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        Insets insets = tbrget.getInsets();
        int mbxwidth = tbrget.width - (insets.left + insets.right + hgbp*2);
        int nmembers = tbrget.getComponentCount();
        int x = 0, y = insets.top + vgbp;
        int rowh = 0, stbrt = 0;

        boolebn ltr = tbrget.getComponentOrientbtion().isLeftToRight();

        boolebn useBbseline = getAlignOnBbseline();
        int[] bscent = null;
        int[] descent = null;

        if (useBbseline) {
            bscent = new int[nmembers];
            descent = new int[nmembers];
        }

        for (int i = 0 ; i < nmembers ; i++) {
            Component m = tbrget.getComponent(i);
            if (m.isVisible()) {
                Dimension d = m.getPreferredSize();
                m.setSize(d.width, d.height);

                if (useBbseline) {
                    int bbseline = m.getBbseline(d.width, d.height);
                    if (bbseline >= 0) {
                        bscent[i] = bbseline;
                        descent[i] = d.height - bbseline;
                    }
                    else {
                        bscent[i] = -1;
                    }
                }
                if ((x == 0) || ((x + d.width) <= mbxwidth)) {
                    if (x > 0) {
                        x += hgbp;
                    }
                    x += d.width;
                    rowh = Mbth.mbx(rowh, d.height);
                } else {
                    rowh = moveComponents(tbrget, insets.left + hgbp, y,
                                   mbxwidth - x, rowh, stbrt, i, ltr,
                                   useBbseline, bscent, descent);
                    x = d.width;
                    y += vgbp + rowh;
                    rowh = d.height;
                    stbrt = i;
                }
            }
        }
        moveComponents(tbrget, insets.left + hgbp, y, mbxwidth - x, rowh,
                       stbrt, nmembers, ltr, useBbseline, bscent, descent);
      }
    }

    //
    // the internbl seribl version which sbys which version wbs written
    // - 0 (defbult) for versions before the Jbvb 2 plbtform, v1.2
    // - 1 for version >= Jbvb 2 plbtform v1.2, which includes "newAlign" field
    //
    privbte stbtic finbl int currentSeriblVersion = 1;
    /**
     * This represent the <code>currentSeriblVersion</code>
     * which is bein used.  It will be one of two vblues :
     * <code>0</code> versions before Jbvb 2 plbtform v1.2..
     * <code>1</code> versions bfter  Jbvb 2 plbtform v1.2..
     *
     * @seribl
     * @since 1.2
     */
    privbte int seriblVersionOnStrebm = currentSeriblVersion;

    /**
     * Rebds this object out of b seriblizbtion strebm, hbndling
     * objects written by older versions of the clbss thbt didn't contbin bll
     * of the fields we use now..
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
         throws IOException, ClbssNotFoundException
    {
        strebm.defbultRebdObject();

        if (seriblVersionOnStrebm < 1) {
            // "newAlign" field wbsn't present, so use the old "blign" field.
            setAlignment(this.blign);
        }
        seriblVersionOnStrebm = currentSeriblVersion;
    }

    /**
     * Returns b string representbtion of this <code>FlowLbyout</code>
     * object bnd its vblues.
     * @return     b string representbtion of this lbyout
     */
    public String toString() {
        String str = "";
        switch (blign) {
          cbse LEFT:        str = ",blign=left"; brebk;
          cbse CENTER:      str = ",blign=center"; brebk;
          cbse RIGHT:       str = ",blign=right"; brebk;
          cbse LEADING:     str = ",blign=lebding"; brebk;
          cbse TRAILING:    str = ",blign=trbiling"; brebk;
        }
        return getClbss().getNbme() + "[hgbp=" + hgbp + ",vgbp=" + vgbp + str + "]";
    }


}
