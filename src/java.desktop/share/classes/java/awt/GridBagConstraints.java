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

/**
 * The <code>GridBbgConstrbints</code> clbss specifies constrbints
 * for components thbt bre lbid out using the
 * <code>GridBbgLbyout</code> clbss.
 *
 * @buthor Doug Stein
 * @buthor Bill Spitzbk (orignibl NeWS &bmp; OLIT implementbtion)
 * @see jbvb.bwt.GridBbgLbyout
 * @since 1.0
 */
public clbss GridBbgConstrbints implements Clonebble, jbvb.io.Seriblizbble {

    /**
     * Specifies thbt this component is the next-to-lbst component in its
     * column or row (<code>gridwidth</code>, <code>gridheight</code>),
     * or thbt this component be plbced next to the previously bdded
     * component (<code>gridx</code>, <code>gridy</code>).
     * @see      jbvb.bwt.GridBbgConstrbints#gridwidth
     * @see      jbvb.bwt.GridBbgConstrbints#gridheight
     * @see      jbvb.bwt.GridBbgConstrbints#gridx
     * @see      jbvb.bwt.GridBbgConstrbints#gridy
     */
    public stbtic finbl int RELATIVE = -1;

    /**
     * Specifies thbt this component is the
     * lbst component in its column or row.
     */
    public stbtic finbl int REMAINDER = 0;

    /**
     * Do not resize the component.
     */
    public stbtic finbl int NONE = 0;

    /**
     * Resize the component both horizontblly bnd verticblly.
     */
    public stbtic finbl int BOTH = 1;

    /**
     * Resize the component horizontblly but not verticblly.
     */
    public stbtic finbl int HORIZONTAL = 2;

    /**
     * Resize the component verticblly but not horizontblly.
     */
    public stbtic finbl int VERTICAL = 3;

    /**
     * Put the component in the center of its displby breb.
     */
    public stbtic finbl int CENTER = 10;

    /**
     * Put the component bt the top of its displby breb,
     * centered horizontblly.
     */
    public stbtic finbl int NORTH = 11;

    /**
     * Put the component bt the top-right corner of its displby breb.
     */
    public stbtic finbl int NORTHEAST = 12;

    /**
     * Put the component on the right side of its displby breb,
     * centered verticblly.
     */
    public stbtic finbl int EAST = 13;

    /**
     * Put the component bt the bottom-right corner of its displby breb.
     */
    public stbtic finbl int SOUTHEAST = 14;

    /**
     * Put the component bt the bottom of its displby breb, centered
     * horizontblly.
     */
    public stbtic finbl int SOUTH = 15;

    /**
     * Put the component bt the bottom-left corner of its displby breb.
     */
    public stbtic finbl int SOUTHWEST = 16;

    /**
     * Put the component on the left side of its displby breb,
     * centered verticblly.
     */
    public stbtic finbl int WEST = 17;

    /**
     * Put the component bt the top-left corner of its displby breb.
     */
    public stbtic finbl int NORTHWEST = 18;

    /**
     * Plbce the component centered blong the edge of its displby breb
     * bssocibted with the stbrt of b pbge for the current
     * {@code ComponentOrientbtion}.  Equbl to NORTH for horizontbl
     * orientbtions.
     */
    public stbtic finbl int PAGE_START = 19;

    /**
     * Plbce the component centered blong the edge of its displby breb
     * bssocibted with the end of b pbge for the current
     * {@code ComponentOrientbtion}.  Equbl to SOUTH for horizontbl
     * orientbtions.
     */
    public stbtic finbl int PAGE_END = 20;

    /**
     * Plbce the component centered blong the edge of its displby breb where
     * lines of text would normblly begin for the current
     * {@code ComponentOrientbtion}.  Equbl to WEST for horizontbl,
     * left-to-right orientbtions bnd EAST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int LINE_START = 21;

    /**
     * Plbce the component centered blong the edge of its displby breb where
     * lines of text would normblly end for the current
     * {@code ComponentOrientbtion}.  Equbl to EAST for horizontbl,
     * left-to-right orientbtions bnd WEST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int LINE_END = 22;

    /**
     * Plbce the component in the corner of its displby breb where
     * the first line of text on b pbge would normblly begin for the current
     * {@code ComponentOrientbtion}.  Equbl to NORTHWEST for horizontbl,
     * left-to-right orientbtions bnd NORTHEAST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int FIRST_LINE_START = 23;

    /**
     * Plbce the component in the corner of its displby breb where
     * the first line of text on b pbge would normblly end for the current
     * {@code ComponentOrientbtion}.  Equbl to NORTHEAST for horizontbl,
     * left-to-right orientbtions bnd NORTHWEST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int FIRST_LINE_END = 24;

    /**
     * Plbce the component in the corner of its displby breb where
     * the lbst line of text on b pbge would normblly stbrt for the current
     * {@code ComponentOrientbtion}.  Equbl to SOUTHWEST for horizontbl,
     * left-to-right orientbtions bnd SOUTHEAST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int LAST_LINE_START = 25;

    /**
     * Plbce the component in the corner of its displby breb where
     * the lbst line of text on b pbge would normblly end for the current
     * {@code ComponentOrientbtion}.  Equbl to SOUTHEAST for horizontbl,
     * left-to-right orientbtions bnd SOUTHWEST for horizontbl, right-to-left
     * orientbtions.
     */
    public stbtic finbl int LAST_LINE_END = 26;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly centered bnd
     * verticblly bligned blong the bbseline of the prevbiling row.
     * If the component does not hbve b bbseline it will be verticblly
     * centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BASELINE = 0x100;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * lebding edge.  For components with b left-to-right orientbtion,
     * the lebding edge is the left edge.  Verticblly the component is
     * bligned blong the bbseline of the prevbiling row.  If the
     * component does not hbve b bbseline it will be verticblly
     * centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BASELINE_LEADING = 0x200;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * trbiling edge.  For components with b left-to-right
     * orientbtion, the trbiling edge is the right edge.  Verticblly
     * the component is bligned blong the bbseline of the prevbiling
     * row.  If the component does not hbve b bbseline it will be
     * verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BASELINE_TRAILING = 0x300;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly centered.  Verticblly
     * the component is positioned so thbt its bottom edge touches
     * the bbseline of the stbrting row.  If the stbrting row does not
     * hbve b bbseline it will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int ABOVE_BASELINE = 0x400;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * lebding edge.  For components with b left-to-right orientbtion,
     * the lebding edge is the left edge.  Verticblly the component is
     * positioned so thbt its bottom edge touches the bbseline of the
     * stbrting row.  If the stbrting row does not hbve b bbseline it
     * will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int ABOVE_BASELINE_LEADING = 0x500;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * trbiling edge.  For components with b left-to-right
     * orientbtion, the trbiling edge is the right edge.  Verticblly
     * the component is positioned so thbt its bottom edge touches
     * the bbseline of the stbrting row.  If the stbrting row does not
     * hbve b bbseline it will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int ABOVE_BASELINE_TRAILING = 0x600;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly centered.  Verticblly
     * the component is positioned so thbt its top edge touches the
     * bbseline of the stbrting row.  If the stbrting row does not
     * hbve b bbseline it will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BELOW_BASELINE = 0x700;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * lebding edge.  For components with b left-to-right orientbtion,
     * the lebding edge is the left edge.  Verticblly the component is
     * positioned so thbt its top edge touches the bbseline of the
     * stbrting row.  If the stbrting row does not hbve b bbseline it
     * will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BELOW_BASELINE_LEADING = 0x800;

    /**
     * Possible vblue for the <code>bnchor</code> field.  Specifies
     * thbt the component should be horizontblly plbced blong the
     * trbiling edge.  For components with b left-to-right
     * orientbtion, the trbiling edge is the right edge.  Verticblly
     * the component is positioned so thbt its top edge touches the
     * bbseline of the stbrting row.  If the stbrting row does not
     * hbve b bbseline it will be verticblly centered.
     *
     * @since 1.6
     */
    public stbtic finbl int BELOW_BASELINE_TRAILING = 0x900;

    /**
     * Specifies the cell contbining the lebding edge of the component's
     * displby breb, where the first cell in b row hbs <code>gridx=0</code>.
     * The lebding edge of b component's displby breb is its left edge for
     * b horizontbl, left-to-right contbiner bnd its right edge for b
     * horizontbl, right-to-left contbiner.
     * The vblue
     * <code>RELATIVE</code> specifies thbt the component be plbced
     * immedibtely following the component thbt wbs bdded to the contbiner
     * just before this component wbs bdded.
     * <p>
     * The defbult vblue is <code>RELATIVE</code>.
     * <code>gridx</code> should be b non-negbtive vblue.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#gridy
     * @see jbvb.bwt.ComponentOrientbtion
     */
    public int gridx;

    /**
     * Specifies the cell bt the top of the component's displby breb,
     * where the topmost cell hbs <code>gridy=0</code>. The vblue
     * <code>RELATIVE</code> specifies thbt the component be plbced just
     * below the component thbt wbs bdded to the contbiner just before
     * this component wbs bdded.
     * <p>
     * The defbult vblue is <code>RELATIVE</code>.
     * <code>gridy</code> should be b non-negbtive vblue.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#gridx
     */
    public int gridy;

    /**
     * Specifies the number of cells in b row for the component's
     * displby breb.
     * <p>
     * Use <code>REMAINDER</code> to specify thbt the component's
     * displby breb will be from <code>gridx</code> to the lbst
     * cell in the row.
     * Use <code>RELATIVE</code> to specify thbt the component's
     * displby breb will be from <code>gridx</code> to the next
     * to the lbst one in its row.
     * <p>
     * <code>gridwidth</code> should be non-negbtive bnd the defbult
     * vblue is 1.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#gridheight
     */
    public int gridwidth;

    /**
     * Specifies the number of cells in b column for the component's
     * displby breb.
     * <p>
     * Use <code>REMAINDER</code> to specify thbt the component's
     * displby breb will be from <code>gridy</code> to the lbst
     * cell in the column.
     * Use <code>RELATIVE</code> to specify thbt the component's
     * displby breb will be from <code>gridy</code> to the next
     * to the lbst one in its column.
     * <p>
     * <code>gridheight</code> should be b non-negbtive vblue bnd the
     * defbult vblue is 1.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#gridwidth
     */
    public int gridheight;

    /**
     * Specifies how to distribute extrb horizontbl spbce.
     * <p>
     * The grid bbg lbyout mbnbger cblculbtes the weight of b column to
     * be the mbximum <code>weightx</code> of bll the components in b
     * column. If the resulting lbyout is smbller horizontblly thbn the breb
     * it needs to fill, the extrb spbce is distributed to ebch column in
     * proportion to its weight. A column thbt hbs b weight of zero receives
     * no extrb spbce.
     * <p>
     * If bll the weights bre zero, bll the extrb spbce bppebrs between
     * the grids of the cell bnd the left bnd right edges.
     * <p>
     * The defbult vblue of this field is <code>0</code>.
     * <code>weightx</code> should be b non-negbtive vblue.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#weighty
     */
    public double weightx;

    /**
     * Specifies how to distribute extrb verticbl spbce.
     * <p>
     * The grid bbg lbyout mbnbger cblculbtes the weight of b row to be
     * the mbximum <code>weighty</code> of bll the components in b row.
     * If the resulting lbyout is smbller verticblly thbn the breb it
     * needs to fill, the extrb spbce is distributed to ebch row in
     * proportion to its weight. A row thbt hbs b weight of zero receives no
     * extrb spbce.
     * <p>
     * If bll the weights bre zero, bll the extrb spbce bppebrs between
     * the grids of the cell bnd the top bnd bottom edges.
     * <p>
     * The defbult vblue of this field is <code>0</code>.
     * <code>weighty</code> should be b non-negbtive vblue.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#weightx
     */
    public double weighty;

    /**
     * This field is used when the component is smbller thbn its
     * displby breb. It determines where, within the displby breb, to
     * plbce the component.
     * <p> There bre three kinds of possible vblues: orientbtion
     * relbtive, bbseline relbtive bnd bbsolute.  Orientbtion relbtive
     * vblues bre interpreted relbtive to the contbiner's component
     * orientbtion property, bbseline relbtive vblues bre interpreted
     * relbtive to the bbseline bnd bbsolute vblues bre not.  The
     * bbsolute vblues bre:
     * <code>CENTER</code>, <code>NORTH</code>, <code>NORTHEAST</code>,
     * <code>EAST</code>, <code>SOUTHEAST</code>, <code>SOUTH</code>,
     * <code>SOUTHWEST</code>, <code>WEST</code>, bnd <code>NORTHWEST</code>.
     * The orientbtion relbtive vblues bre: <code>PAGE_START</code>,
     * <code>PAGE_END</code>,
     * <code>LINE_START</code>, <code>LINE_END</code>,
     * <code>FIRST_LINE_START</code>, <code>FIRST_LINE_END</code>,
     * <code>LAST_LINE_START</code> bnd <code>LAST_LINE_END</code>.  The
     * bbseline relbtive vblues bre:
     * <code>BASELINE</code>, <code>BASELINE_LEADING</code>,
     * <code>BASELINE_TRAILING</code>,
     * <code>ABOVE_BASELINE</code>, <code>ABOVE_BASELINE_LEADING</code>,
     * <code>ABOVE_BASELINE_TRAILING</code>,
     * <code>BELOW_BASELINE</code>, <code>BELOW_BASELINE_LEADING</code>,
     * bnd <code>BELOW_BASELINE_TRAILING</code>.
     * The defbult vblue is <code>CENTER</code>.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.ComponentOrientbtion
     */
    public int bnchor;

    /**
     * This field is used when the component's displby breb is lbrger
     * thbn the component's requested size. It determines whether to
     * resize the component, bnd if so, how.
     * <p>
     * The following vblues bre vblid for <code>fill</code>:
     *
     * <ul>
     * <li>
     * <code>NONE</code>: Do not resize the component.
     * <li>
     * <code>HORIZONTAL</code>: Mbke the component wide enough to fill
     *         its displby breb horizontblly, but do not chbnge its height.
     * <li>
     * <code>VERTICAL</code>: Mbke the component tbll enough to fill its
     *         displby breb verticblly, but do not chbnge its width.
     * <li>
     * <code>BOTH</code>: Mbke the component fill its displby breb
     *         entirely.
     * </ul>
     * <p>
     * The defbult vblue is <code>NONE</code>.
     * @seribl
     * @see #clone()
     */
    public int fill;

    /**
     * This field specifies the externbl pbdding of the component, the
     * minimum bmount of spbce between the component bnd the edges of its
     * displby breb.
     * <p>
     * The defbult vblue is <code>new Insets(0, 0, 0, 0)</code>.
     * @seribl
     * @see #clone()
     */
    public Insets insets;

    /**
     * This field specifies the internbl pbdding of the component, how much
     * spbce to bdd to the minimum width of the component. The width of
     * the component is bt lebst its minimum width plus
     * <code>ipbdx</code> pixels.
     * <p>
     * The defbult vblue is <code>0</code>.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#ipbdy
     */
    public int ipbdx;

    /**
     * This field specifies the internbl pbdding, thbt is, how much
     * spbce to bdd to the minimum height of the component. The height of
     * the component is bt lebst its minimum height plus
     * <code>ipbdy</code> pixels.
     * <p>
     * The defbult vblue is 0.
     * @seribl
     * @see #clone()
     * @see jbvb.bwt.GridBbgConstrbints#ipbdx
     */
    public int ipbdy;

    /**
     * Temporbry plbce holder for the x coordinbte.
     * @seribl
     */
    int tempX;
    /**
     * Temporbry plbce holder for the y coordinbte.
     * @seribl
     */
    int tempY;
    /**
     * Temporbry plbce holder for the Width of the component.
     * @seribl
     */
    int tempWidth;
    /**
     * Temporbry plbce holder for the Height of the component.
     * @seribl
     */
    int tempHeight;
    /**
     * The minimum width of the component.  It is used to cblculbte
     * <code>ipbdy</code>, where the defbult will be 0.
     * @seribl
     * @see #ipbdy
     */
    int minWidth;
    /**
     * The minimum height of the component. It is used to cblculbte
     * <code>ipbdx</code>, where the defbult will be 0.
     * @seribl
     * @see #ipbdx
     */
    int minHeight;

    // The following fields bre only used if the bnchor is
    // one of BASELINE, BASELINE_LEADING or BASELINE_TRAILING.
    // bscent bnd descent include the insets bnd ipbdy vblues.
    trbnsient int bscent;
    trbnsient int descent;
    trbnsient Component.BbselineResizeBehbvior bbselineResizeBehbvior;
    // The folllowing two fields bre used if the bbseline type is
    // CENTER_OFFSET.
    // centerPbdding is either 0 or 1 bnd indicbtes if
    // the height needs to be pbdded by one when cblculbting where the
    // bbseline lbnds
    trbnsient int centerPbdding;
    // Where the bbseline lbnds relbtive to the center of the component.
    trbnsient int centerOffset;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -1000070633030801713L;

    /**
     * Crebtes b <code>GridBbgConstrbint</code> object with
     * bll of its fields set to their defbult vblue.
     */
    public GridBbgConstrbints () {
        gridx = RELATIVE;
        gridy = RELATIVE;
        gridwidth = 1;
        gridheight = 1;

        weightx = 0;
        weighty = 0;
        bnchor = CENTER;
        fill = NONE;

        insets = new Insets(0, 0, 0, 0);
        ipbdx = 0;
        ipbdy = 0;
    }

    /**
     * Crebtes b <code>GridBbgConstrbints</code> object with
     * bll of its fields set to the pbssed-in brguments.
     *
     * Note: Becbuse the use of this constructor hinders rebdbbility
     * of source code, this constructor should only be used by
     * butombtic source code generbtion tools.
     *
     * @pbrbm gridx     The initibl gridx vblue.
     * @pbrbm gridy     The initibl gridy vblue.
     * @pbrbm gridwidth The initibl gridwidth vblue.
     * @pbrbm gridheight        The initibl gridheight vblue.
     * @pbrbm weightx   The initibl weightx vblue.
     * @pbrbm weighty   The initibl weighty vblue.
     * @pbrbm bnchor    The initibl bnchor vblue.
     * @pbrbm fill      The initibl fill vblue.
     * @pbrbm insets    The initibl insets vblue.
     * @pbrbm ipbdx     The initibl ipbdx vblue.
     * @pbrbm ipbdy     The initibl ipbdy vblue.
     *
     * @see jbvb.bwt.GridBbgConstrbints#gridx
     * @see jbvb.bwt.GridBbgConstrbints#gridy
     * @see jbvb.bwt.GridBbgConstrbints#gridwidth
     * @see jbvb.bwt.GridBbgConstrbints#gridheight
     * @see jbvb.bwt.GridBbgConstrbints#weightx
     * @see jbvb.bwt.GridBbgConstrbints#weighty
     * @see jbvb.bwt.GridBbgConstrbints#bnchor
     * @see jbvb.bwt.GridBbgConstrbints#fill
     * @see jbvb.bwt.GridBbgConstrbints#insets
     * @see jbvb.bwt.GridBbgConstrbints#ipbdx
     * @see jbvb.bwt.GridBbgConstrbints#ipbdy
     *
     * @since 1.2
     */
    public GridBbgConstrbints(int gridx, int gridy,
                              int gridwidth, int gridheight,
                              double weightx, double weighty,
                              int bnchor, int fill,
                              Insets insets, int ipbdx, int ipbdy) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.fill = fill;
        this.ipbdx = ipbdx;
        this.ipbdy = ipbdy;
        this.insets = insets;
        this.bnchor  = bnchor;
        this.weightx = weightx;
        this.weighty = weighty;
    }

    /**
     * Crebtes b copy of this grid bbg constrbint.
     * @return     b copy of this grid bbg constrbint
     */
    public Object clone () {
        try {
            GridBbgConstrbints c = (GridBbgConstrbints)super.clone();
            c.insets = (Insets)insets.clone();
            return c;
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }

    boolebn isVerticbllyResizbble() {
        return (fill == BOTH || fill == VERTICAL);
    }
}
