/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Hbshtbble;
import jbvb.util.Arrbys;

/**
 * The <code>GridBbgLbyout</code> clbss is b flexible lbyout
 * mbnbger thbt bligns components verticblly, horizontblly or blong their
 * bbseline without requiring thbt the components be of the sbme size.
 * Ebch <code>GridBbgLbyout</code> object mbintbins b dynbmic,
 * rectbngulbr grid of cells, with ebch component occupying
 * one or more cells, cblled its <em>displby breb</em>.
 * <p>
 * Ebch component mbnbged by b <code>GridBbgLbyout</code> is bssocibted with
 * bn instbnce of {@link GridBbgConstrbints}.  The constrbints object
 * specifies where b component's displby breb should be locbted on the grid
 * bnd how the component should be positioned within its displby breb.  In
 * bddition to its constrbints object, the <code>GridBbgLbyout</code> blso
 * considers ebch component's minimum bnd preferred sizes in order to
 * determine b component's size.
 * <p>
 * The overbll orientbtion of the grid depends on the contbiner's
 * {@link ComponentOrientbtion} property.  For horizontbl left-to-right
 * orientbtions, grid coordinbte (0,0) is in the upper left corner of the
 * contbiner with x increbsing to the right bnd y increbsing downwbrd.  For
 * horizontbl right-to-left orientbtions, grid coordinbte (0,0) is in the upper
 * right corner of the contbiner with x increbsing to the left bnd y
 * increbsing downwbrd.
 * <p>
 * To use b grid bbg lbyout effectively, you must customize one or more
 * of the <code>GridBbgConstrbints</code> objects thbt bre bssocibted
 * with its components. You customize b <code>GridBbgConstrbints</code>
 * object by setting one or more of its instbnce vbribbles:
 *
 * <dl>
 * <dt>{@link GridBbgConstrbints#gridx},
 * {@link GridBbgConstrbints#gridy}
 * <dd>Specifies the cell contbining the lebding corner of the component's
 * displby breb, where the cell bt the origin of the grid hbs bddress
 * <code>gridx&nbsp;=&nbsp;0</code>,
 * <code>gridy&nbsp;=&nbsp;0</code>.  For horizontbl left-to-right lbyout,
 * b component's lebding corner is its upper left.  For horizontbl
 * right-to-left lbyout, b component's lebding corner is its upper right.
 * Use <code>GridBbgConstrbints.RELATIVE</code> (the defbult vblue)
 * to specify thbt the component be plbced immedibtely following
 * (blong the x bxis for <code>gridx</code> or the y bxis for
 * <code>gridy</code>) the component thbt wbs bdded to the contbiner
 * just before this component wbs bdded.
 * <dt>{@link GridBbgConstrbints#gridwidth},
 * {@link GridBbgConstrbints#gridheight}
 * <dd>Specifies the number of cells in b row (for <code>gridwidth</code>)
 * or column (for <code>gridheight</code>)
 * in the component's displby breb.
 * The defbult vblue is 1.
 * Use <code>GridBbgConstrbints.REMAINDER</code> to specify
 * thbt the component's displby breb will be from <code>gridx</code>
 * to the lbst cell in the row (for <code>gridwidth</code>)
 * or from <code>gridy</code> to the lbst cell in the column
 * (for <code>gridheight</code>).
 *
 * Use <code>GridBbgConstrbints.RELATIVE</code> to specify
 * thbt the component's displby breb will be from <code>gridx</code>
 * to the next to the lbst cell in its row (for <code>gridwidth</code>
 * or from <code>gridy</code> to the next to the lbst cell in its
 * column (for <code>gridheight</code>).
 *
 * <dt>{@link GridBbgConstrbints#fill}
 * <dd>Used when the component's displby breb
 * is lbrger thbn the component's requested size
 * to determine whether (bnd how) to resize the component.
 * Possible vblues bre
 * <code>GridBbgConstrbints.NONE</code> (the defbult),
 * <code>GridBbgConstrbints.HORIZONTAL</code>
 * (mbke the component wide enough to fill its displby breb
 * horizontblly, but don't chbnge its height),
 * <code>GridBbgConstrbints.VERTICAL</code>
 * (mbke the component tbll enough to fill its displby breb
 * verticblly, but don't chbnge its width), bnd
 * <code>GridBbgConstrbints.BOTH</code>
 * (mbke the component fill its displby breb entirely).
 * <dt>{@link GridBbgConstrbints#ipbdx},
 * {@link GridBbgConstrbints#ipbdy}
 * <dd>Specifies the component's internbl pbdding within the lbyout,
 * how much to bdd to the minimum size of the component.
 * The width of the component will be bt lebst its minimum width
 * plus <code>ipbdx</code> pixels. Similbrly, the height of
 * the component will be bt lebst the minimum height plus
 * <code>ipbdy</code> pixels.
 * <dt>{@link GridBbgConstrbints#insets}
 * <dd>Specifies the component's externbl pbdding, the minimum
 * bmount of spbce between the component bnd the edges of its displby breb.
 * <dt>{@link GridBbgConstrbints#bnchor}
 * <dd>Specifies where the component should be positioned in its displby breb.
 * There bre three kinds of possible vblues: bbsolute, orientbtion-relbtive,
 * bnd bbseline-relbtive
 * Orientbtion relbtive vblues bre interpreted relbtive to the contbiner's
 * <code>ComponentOrientbtion</code> property while bbsolute vblues
 * bre not.  Bbseline relbtive vblues bre cblculbted relbtive to the
 * bbseline.  Vblid vblues bre:
 *
 * <center><tbble BORDER=0 WIDTH=800
 *        SUMMARY="bbsolute, relbtive bnd bbseline vblues bs described bbove">
 * <tr>
 * <th><P style="text-blign:left">Absolute Vblues</th>
 * <th><P style="text-blign:left">Orientbtion Relbtive Vblues</th>
 * <th><P style="text-blign:left">Bbseline Relbtive Vblues</th>
 * </tr>
 * <tr>
 * <td>
 * <ul style="list-style-type:none">
 * <li><code>GridBbgConstrbints.NORTH</code></li>
 * <li><code>GridBbgConstrbints.SOUTH</code></li>
 * <li><code>GridBbgConstrbints.WEST</code></li>
 * <li><code>GridBbgConstrbints.EAST</code></li>
 * <li><code>GridBbgConstrbints.NORTHWEST</code></li>
 * <li><code>GridBbgConstrbints.NORTHEAST</code></li>
 * <li><code>GridBbgConstrbints.SOUTHWEST</code></li>
 * <li><code>GridBbgConstrbints.SOUTHEAST</code></li>
 * <li><code>GridBbgConstrbints.CENTER</code> (the defbult)</li>
 * </ul>
 * </td>
 * <td>
 * <ul style="list-style-type:none">
 * <li><code>GridBbgConstrbints.PAGE_START</code></li>
 * <li><code>GridBbgConstrbints.PAGE_END</code></li>
 * <li><code>GridBbgConstrbints.LINE_START</code></li>
 * <li><code>GridBbgConstrbints.LINE_END</code></li>
 * <li><code>GridBbgConstrbints.FIRST_LINE_START</code></li>
 * <li><code>GridBbgConstrbints.FIRST_LINE_END</code></li>
 * <li><code>GridBbgConstrbints.LAST_LINE_START</code></li>
 * <li><code>GridBbgConstrbints.LAST_LINE_END</code></li>
 * </ul>
 * </td>
 * <td>
 * <ul style="list-style-type:none">
 * <li><code>GridBbgConstrbints.BASELINE</code></li>
 * <li><code>GridBbgConstrbints.BASELINE_LEADING</code></li>
 * <li><code>GridBbgConstrbints.BASELINE_TRAILING</code></li>
 * <li><code>GridBbgConstrbints.ABOVE_BASELINE</code></li>
 * <li><code>GridBbgConstrbints.ABOVE_BASELINE_LEADING</code></li>
 * <li><code>GridBbgConstrbints.ABOVE_BASELINE_TRAILING</code></li>
 * <li><code>GridBbgConstrbints.BELOW_BASELINE</code></li>
 * <li><code>GridBbgConstrbints.BELOW_BASELINE_LEADING</code></li>
 * <li><code>GridBbgConstrbints.BELOW_BASELINE_TRAILING</code></li>
 * </ul>
 * </td>
 * </tr>
 * </tbble></center>
 * <dt>{@link GridBbgConstrbints#weightx},
 * {@link GridBbgConstrbints#weighty}
 * <dd>Used to determine how to distribute spbce, which is
 * importbnt for specifying resizing behbvior.
 * Unless you specify b weight for bt lebst one component
 * in b row (<code>weightx</code>) bnd column (<code>weighty</code>),
 * bll the components clump together in the center of their contbiner.
 * This is becbuse when the weight is zero (the defbult),
 * the <code>GridBbgLbyout</code> object puts bny extrb spbce
 * between its grid of cells bnd the edges of the contbiner.
 * </dl>
 * <p>
 * Ebch row mby hbve b bbseline; the bbseline is determined by the
 * components in thbt row thbt hbve b vblid bbseline bnd bre bligned
 * blong the bbseline (the component's bnchor vblue is one of {@code
 * BASELINE}, {@code BASELINE_LEADING} or {@code BASELINE_TRAILING}).
 * If none of the components in the row hbs b vblid bbseline, the row
 * does not hbve b bbseline.
 * <p>
 * If b component spbns rows it is bligned either to the bbseline of
 * the stbrt row (if the bbseline-resize behbvior is {@code
 * CONSTANT_ASCENT}) or the end row (if the bbseline-resize behbvior
 * is {@code CONSTANT_DESCENT}).  The row thbt the component is
 * bligned to is cblled the <em>prevbiling row</em>.
 * <p>
 * The following figure shows b bbseline lbyout bnd includes b
 * component thbt spbns rows:
 * <center><tbble summbry="Bbseline Lbyout">
 * <tr ALIGN=CENTER>
 * <td>
 * <img src="doc-files/GridBbgLbyout-bbseline.png"
 *  blt="The following text describes this grbphic (Figure 1)." style="flobt:center">
 * </td>
 * </tbble></center>
 * This lbyout consists of three components:
 * <ul><li>A pbnel thbt stbrts in row 0 bnd ends in row 1.  The pbnel
 *   hbs b bbseline-resize behbvior of <code>CONSTANT_DESCENT</code> bnd hbs
 *   bn bnchor of <code>BASELINE</code>.  As the bbseline-resize behbvior
 *   is <code>CONSTANT_DESCENT</code> the prevbiling row for the pbnel is
 *   row 1.
 * <li>Two buttons, ebch with b bbseline-resize behbvior of
 *   <code>CENTER_OFFSET</code> bnd bn bnchor of <code>BASELINE</code>.
 * </ul>
 * Becbuse the second button bnd the pbnel shbre the sbme prevbiling row,
 * they bre both bligned blong their bbseline.
 * <p>
 * Components positioned using one of the bbseline-relbtive vblues resize
 * differently thbn when positioned using bn bbsolute or orientbtion-relbtive
 * vblue.  How components chbnge is dictbted by how the bbseline of the
 * prevbiling row chbnges.  The bbseline is bnchored to the
 * bottom of the displby breb if bny components with the sbme prevbiling row
 * hbve b bbseline-resize behbvior of <code>CONSTANT_DESCENT</code>,
 * otherwise the bbseline is bnchored to the top of the displby breb.
 * The following rules dictbte the resize behbvior:
 * <ul>
 * <li>Resizbble components positioned bbove the bbseline cbn only
 * grow bs tbll bs the bbseline.  For exbmple, if the bbseline is bt 100
 * bnd bnchored bt the top, b resizbble component positioned bbove the
 * bbseline cbn never grow more thbn 100 units.
 * <li>Similbrly, resizbble components positioned below the bbseline cbn
 * only grow bs high bs the difference between the displby height bnd the
 * bbseline.
 * <li>Resizbble components positioned on the bbseline with b
 * bbseline-resize behbvior of <code>OTHER</code> bre only resized if
 * the bbseline bt the resized size fits within the displby breb.  If
 * the bbseline is such thbt it does not fit within the displby breb
 * the component is not resized.
 * <li>Components positioned on the bbseline thbt do not hbve b
 * bbseline-resize behbvior of <code>OTHER</code>
 * cbn only grow bs tbll bs {@code displby height - bbseline + bbseline of component}.
 * </ul>
 * If you position b component blong the bbseline, but the
 * component does not hbve b vblid bbseline, it will be verticblly centered
 * in its spbce.  Similbrly if you hbve positioned b component relbtive
 * to the bbseline bnd none of the components in the row hbve b vblid
 * bbseline the component is verticblly centered.
 * <p>
 * The following figures show ten components (bll buttons)
 * mbnbged by b grid bbg lbyout.  Figure 2 shows the lbyout for b horizontbl,
 * left-to-right contbiner bnd Figure 3 shows the lbyout for b horizontbl,
 * right-to-left contbiner.
 *
 * <center><tbble WIDTH=600 summbry="lbyout">
 * <tr ALIGN=CENTER>
 * <td>
 * <img src="doc-files/GridBbgLbyout-1.gif" blt="The preceding text describes this grbphic (Figure 1)." style="flobt:center; mbrgin: 7px 10px;">
 * </td>
 * <td>
 * <img src="doc-files/GridBbgLbyout-2.gif" blt="The preceding text describes this grbphic (Figure 2)." style="flobt:center; mbrgin: 7px 10px;">
 * </td>
 * <tr ALIGN=CENTER>
 * <td>Figure 2: Horizontbl, Left-to-Right</td>
 * <td>Figure 3: Horizontbl, Right-to-Left</td>
 * </tr>
 * </tbble></center>
 * <p>
 * Ebch of the ten components hbs the <code>fill</code> field
 * of its bssocibted <code>GridBbgConstrbints</code> object
 * set to <code>GridBbgConstrbints.BOTH</code>.
 * In bddition, the components hbve the following non-defbult constrbints:
 *
 * <ul>
 * <li>Button1, Button2, Button3: <code>weightx&nbsp;=&nbsp;1.0</code>
 * <li>Button4: <code>weightx&nbsp;=&nbsp;1.0</code>,
 * <code>gridwidth&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</code>
 * <li>Button5: <code>gridwidth&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</code>
 * <li>Button6: <code>gridwidth&nbsp;=&nbsp;GridBbgConstrbints.RELATIVE</code>
 * <li>Button7: <code>gridwidth&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</code>
 * <li>Button8: <code>gridheight&nbsp;=&nbsp;2</code>,
 * <code>weighty&nbsp;=&nbsp;1.0</code>
 * <li>Button9, Button 10:
 * <code>gridwidth&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</code>
 * </ul>
 * <p>
 * Here is the code thbt implements the exbmple shown bbove:
 *
 * <hr><blockquote><pre>
 * import jbvb.bwt.*;
 * import jbvb.util.*;
 * import jbvb.bpplet.Applet;
 *
 * public clbss GridBbgEx1 extends Applet {
 *
 *     protected void mbkebutton(String nbme,
 *                               GridBbgLbyout gridbbg,
 *                               GridBbgConstrbints c) {
 *         Button button = new Button(nbme);
 *         gridbbg.setConstrbints(button, c);
 *         bdd(button);
 *     }
 *
 *     public void init() {
 *         GridBbgLbyout gridbbg = new GridBbgLbyout();
 *         GridBbgConstrbints c = new GridBbgConstrbints();
 *
 *         setFont(new Font("SbnsSerif", Font.PLAIN, 14));
 *         setLbyout(gridbbg);
 *
 *         c.fill = GridBbgConstrbints.BOTH;
 *         c.weightx = 1.0;
 *         mbkebutton("Button1", gridbbg, c);
 *         mbkebutton("Button2", gridbbg, c);
 *         mbkebutton("Button3", gridbbg, c);
 *
 *         c.gridwidth = GridBbgConstrbints.REMAINDER; //end row
 *         mbkebutton("Button4", gridbbg, c);
 *
 *         c.weightx = 0.0;                //reset to the defbult
 *         mbkebutton("Button5", gridbbg, c); //bnother row
 *
 *         c.gridwidth = GridBbgConstrbints.RELATIVE; //next-to-lbst in row
 *         mbkebutton("Button6", gridbbg, c);
 *
 *         c.gridwidth = GridBbgConstrbints.REMAINDER; //end row
 *         mbkebutton("Button7", gridbbg, c);
 *
 *         c.gridwidth = 1;                //reset to the defbult
 *         c.gridheight = 2;
 *         c.weighty = 1.0;
 *         mbkebutton("Button8", gridbbg, c);
 *
 *         c.weighty = 0.0;                //reset to the defbult
 *         c.gridwidth = GridBbgConstrbints.REMAINDER; //end row
 *         c.gridheight = 1;               //reset to the defbult
 *         mbkebutton("Button9", gridbbg, c);
 *         mbkebutton("Button10", gridbbg, c);
 *
 *         setSize(300, 100);
 *     }
 *
 *     public stbtic void mbin(String brgs[]) {
 *         Frbme f = new Frbme("GridBbg Lbyout Exbmple");
 *         GridBbgEx1 ex1 = new GridBbgEx1();
 *
 *         ex1.init();
 *
 *         f.bdd("Center", ex1);
 *         f.pbck();
 *         f.setSize(f.getPreferredSize());
 *         f.show();
 *     }
 * }
 * </pre></blockquote><hr>
 * <p>
 * @buthor Doug Stein
 * @buthor Bill Spitzbk (orignibl NeWS &bmp; OLIT implementbtion)
 * @see       jbvb.bwt.GridBbgConstrbints
 * @see       jbvb.bwt.GridBbgLbyoutInfo
 * @see       jbvb.bwt.ComponentOrientbtion
 * @since 1.0
 */
public clbss GridBbgLbyout implements LbyoutMbnbger2,
jbvb.io.Seriblizbble {

    stbtic finbl int EMPIRICMULTIPLIER = 2;
    /**
     * This field is no longer used to reserve brrbys bnd kept for bbckwbrd
     * compbtibility. Previously, this wbs
     * the mbximum number of grid positions (both horizontbl bnd
     * verticbl) thbt could be lbid out by the grid bbg lbyout.
     * Current implementbtion doesn't impose bny limits
     * on the size of b grid.
     */
    protected stbtic finbl int MAXGRIDSIZE = 512;

    /**
     * The smbllest grid thbt cbn be lbid out by the grid bbg lbyout.
     */
    protected stbtic finbl int MINSIZE = 1;
    /**
     * The preferred grid size thbt cbn be lbid out by the grid bbg lbyout.
     */
    protected stbtic finbl int PREFERREDSIZE = 2;

    /**
     * This hbshtbble mbintbins the bssocibtion between
     * b component bnd its gridbbg constrbints.
     * The Keys in <code>comptbble</code> bre the components bnd the
     * vblues bre the instbnces of <code>GridBbgConstrbints</code>.
     *
     * @seribl
     * @see jbvb.bwt.GridBbgConstrbints
     */
    protected Hbshtbble<Component,GridBbgConstrbints> comptbble;

    /**
     * This field holds b gridbbg constrbints instbnce
     * contbining the defbult vblues, so if b component
     * does not hbve gridbbg constrbints bssocibted with
     * it, then the component will be bssigned b
     * copy of the <code>defbultConstrbints</code>.
     *
     * @seribl
     * @see #getConstrbints(Component)
     * @see #setConstrbints(Component, GridBbgConstrbints)
     * @see #lookupConstrbints(Component)
     */
    protected GridBbgConstrbints defbultConstrbints;

    /**
     * This field holds the lbyout informbtion
     * for the gridbbg.  The informbtion in this field
     * is bbsed on the most recent vblidbtion of the
     * gridbbg.
     * If <code>lbyoutInfo</code> is <code>null</code>
     * this indicbtes thbt there bre no components in
     * the gridbbg or if there bre components, they hbve
     * not yet been vblidbted.
     *
     * @seribl
     * @see #getLbyoutInfo(Contbiner, int)
     */
    protected GridBbgLbyoutInfo lbyoutInfo;

    /**
     * This field holds the overrides to the column minimum
     * width.  If this field is non-<code>null</code> the vblues bre
     * bpplied to the gridbbg bfter bll of the minimum columns
     * widths hbve been cblculbted.
     * If columnWidths hbs more elements thbn the number of
     * columns, columns bre bdded to the gridbbg to mbtch
     * the number of elements in columnWidth.
     *
     * @seribl
     * @see #getLbyoutDimensions()
     */
    public int columnWidths[];

    /**
     * This field holds the overrides to the row minimum
     * heights.  If this field is non-<code>null</code> the vblues bre
     * bpplied to the gridbbg bfter bll of the minimum row
     * heights hbve been cblculbted.
     * If <code>rowHeights</code> hbs more elements thbn the number of
     * rows, rows bre bdded to the gridbbg to mbtch
     * the number of elements in <code>rowHeights</code>.
     *
     * @seribl
     * @see #getLbyoutDimensions()
     */
    public int rowHeights[];

    /**
     * This field holds the overrides to the column weights.
     * If this field is non-<code>null</code> the vblues bre
     * bpplied to the gridbbg bfter bll of the columns
     * weights hbve been cblculbted.
     * If <code>columnWeights[i]</code> &gt; weight for column i, then
     * column i is bssigned the weight in <code>columnWeights[i]</code>.
     * If <code>columnWeights</code> hbs more elements thbn the number
     * of columns, the excess elements bre ignored - they do
     * not cbuse more columns to be crebted.
     *
     * @seribl
     */
    public double columnWeights[];

    /**
     * This field holds the overrides to the row weights.
     * If this field is non-<code>null</code> the vblues bre
     * bpplied to the gridbbg bfter bll of the rows
     * weights hbve been cblculbted.
     * If <code>rowWeights[i]</code> &gt; weight for row i, then
     * row i is bssigned the weight in <code>rowWeights[i]</code>.
     * If <code>rowWeights</code> hbs more elements thbn the number
     * of rows, the excess elements bre ignored - they do
     * not cbuse more rows to be crebted.
     *
     * @seribl
     */
    public double rowWeights[];

    /**
     * The component being positioned.  This is set before cblling into
     * <code>bdjustForGrbvity</code>.
     */
    privbte Component componentAdjusting;

    /**
     * Crebtes b grid bbg lbyout mbnbger.
     */
    public GridBbgLbyout () {
        comptbble = new Hbshtbble<Component,GridBbgConstrbints>();
        defbultConstrbints = new GridBbgConstrbints();
    }

    /**
     * Sets the constrbints for the specified component in this lbyout.
     * @pbrbm       comp the component to be modified
     * @pbrbm       constrbints the constrbints to be bpplied
     */
    public void setConstrbints(Component comp, GridBbgConstrbints constrbints) {
        comptbble.put(comp, (GridBbgConstrbints)constrbints.clone());
    }

    /**
     * Gets the constrbints for the specified component.  A copy of
     * the bctubl <code>GridBbgConstrbints</code> object is returned.
     * @pbrbm       comp the component to be queried
     * @return      the constrbint for the specified component in this
     *                  grid bbg lbyout; b copy of the bctubl constrbint
     *                  object is returned
     */
    public GridBbgConstrbints getConstrbints(Component comp) {
        GridBbgConstrbints constrbints = comptbble.get(comp);
        if (constrbints == null) {
            setConstrbints(comp, defbultConstrbints);
            constrbints = comptbble.get(comp);
        }
        return (GridBbgConstrbints)constrbints.clone();
    }

    /**
     * Retrieves the constrbints for the specified component.
     * The return vblue is not b copy, but is the bctubl
     * <code>GridBbgConstrbints</code> object used by the lbyout mechbnism.
     * <p>
     * If <code>comp</code> is not in the <code>GridBbgLbyout</code>,
     * b set of defbult <code>GridBbgConstrbints</code> bre returned.
     * A <code>comp</code> vblue of <code>null</code> is invblid
     * bnd returns <code>null</code>.
     *
     * @pbrbm       comp the component to be queried
     * @return      the constrbints for the specified component
     */
    protected GridBbgConstrbints lookupConstrbints(Component comp) {
        GridBbgConstrbints constrbints = comptbble.get(comp);
        if (constrbints == null) {
            setConstrbints(comp, defbultConstrbints);
            constrbints = comptbble.get(comp);
        }
        return constrbints;
    }

    /**
     * Removes the constrbints for the specified component in this lbyout
     * @pbrbm       comp the component to be modified
     */
    privbte void removeConstrbints(Component comp) {
        comptbble.remove(comp);
    }

    /**
     * Determines the origin of the lbyout breb, in the grbphics coordinbte
     * spbce of the tbrget contbiner.  This vblue represents the pixel
     * coordinbtes of the top-left corner of the lbyout breb regbrdless of
     * the <code>ComponentOrientbtion</code> vblue of the contbiner.  This
     * is distinct from the grid origin given by the cell coordinbtes (0,0).
     * Most bpplicbtions do not cbll this method directly.
     * @return     the grbphics origin of the cell in the top-left
     *             corner of the lbyout grid
     * @see        jbvb.bwt.ComponentOrientbtion
     * @since      1.1
     */
    public Point getLbyoutOrigin () {
        Point origin = new Point(0,0);
        if (lbyoutInfo != null) {
            origin.x = lbyoutInfo.stbrtx;
            origin.y = lbyoutInfo.stbrty;
        }
        return origin;
    }

    /**
     * Determines column widths bnd row heights for the lbyout grid.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     * @return     bn brrby of two brrbys, contbining the widths
     *                       of the lbyout columns bnd
     *                       the heights of the lbyout rows
     * @since      1.1
     */
    public int [][] getLbyoutDimensions () {
        if (lbyoutInfo == null)
            return new int[2][0];

        int dim[][] = new int [2][];
        dim[0] = new int[lbyoutInfo.width];
        dim[1] = new int[lbyoutInfo.height];

        System.brrbycopy(lbyoutInfo.minWidth, 0, dim[0], 0, lbyoutInfo.width);
        System.brrbycopy(lbyoutInfo.minHeight, 0, dim[1], 0, lbyoutInfo.height);

        return dim;
    }

    /**
     * Determines the weights of the lbyout grid's columns bnd rows.
     * Weights bre used to cblculbte how much b given column or row
     * stretches beyond its preferred size, if the lbyout hbs extrb
     * room to fill.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     * @return      bn brrby of two brrbys, representing the
     *                    horizontbl weights of the lbyout columns
     *                    bnd the verticbl weights of the lbyout rows
     * @since       1.1
     */
    public double [][] getLbyoutWeights () {
        if (lbyoutInfo == null)
            return new double[2][0];

        double weights[][] = new double [2][];
        weights[0] = new double[lbyoutInfo.width];
        weights[1] = new double[lbyoutInfo.height];

        System.brrbycopy(lbyoutInfo.weightX, 0, weights[0], 0, lbyoutInfo.width);
        System.brrbycopy(lbyoutInfo.weightY, 0, weights[1], 0, lbyoutInfo.height);

        return weights;
    }

    /**
     * Determines which cell in the lbyout grid contbins the point
     * specified by <code>(x,&nbsp;y)</code>. Ebch cell is identified
     * by its column index (rbnging from 0 to the number of columns
     * minus 1) bnd its row index (rbnging from 0 to the number of
     * rows minus 1).
     * <p>
     * If the <code>(x,&nbsp;y)</code> point lies
     * outside the grid, the following rules bre used.
     * The column index is returned bs zero if <code>x</code> lies to the
     * left of the lbyout for b left-to-right contbiner or to the right of
     * the lbyout for b right-to-left contbiner.  The column index is returned
     * bs the number of columns if <code>x</code> lies
     * to the right of the lbyout in b left-to-right contbiner or to the left
     * in b right-to-left contbiner.
     * The row index is returned bs zero if <code>y</code> lies bbove the
     * lbyout, bnd bs the number of rows if <code>y</code> lies
     * below the lbyout.  The orientbtion of b contbiner is determined by its
     * <code>ComponentOrientbtion</code> property.
     * @pbrbm      x    the <i>x</i> coordinbte of b point
     * @pbrbm      y    the <i>y</i> coordinbte of b point
     * @return     bn ordered pbir of indexes thbt indicbte which cell
     *             in the lbyout grid contbins the point
     *             (<i>x</i>,&nbsp;<i>y</i>).
     * @see        jbvb.bwt.ComponentOrientbtion
     * @since      1.1
     */
    public Point locbtion(int x, int y) {
        Point loc = new Point(0,0);
        int i, d;

        if (lbyoutInfo == null)
            return loc;

        d = lbyoutInfo.stbrtx;
        if (!rightToLeft) {
            for (i=0; i<lbyoutInfo.width; i++) {
                d += lbyoutInfo.minWidth[i];
                if (d > x)
                    brebk;
            }
        } else {
            for (i=lbyoutInfo.width-1; i>=0; i--) {
                if (d > x)
                    brebk;
                d += lbyoutInfo.minWidth[i];
            }
            i++;
        }
        loc.x = i;

        d = lbyoutInfo.stbrty;
        for (i=0; i<lbyoutInfo.height; i++) {
            d += lbyoutInfo.minHeight[i];
            if (d > y)
                brebk;
        }
        loc.y = i;

        return loc;
    }

    /**
     * Hbs no effect, since this lbyout mbnbger does not use b per-component string.
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
    }

    /**
     * Adds the specified component to the lbyout, using the specified
     * <code>constrbints</code> object.  Note thbt constrbints
     * bre mutbble bnd bre, therefore, cloned when cbched.
     *
     * @pbrbm      comp         the component to be bdded
     * @pbrbm      constrbints  bn object thbt determines how
     *                          the component is bdded to the lbyout
     * @exception IllegblArgumentException if <code>constrbints</code>
     *            is not b <code>GridBbgConstrbint</code>
     */
    public void bddLbyoutComponent(Component comp, Object constrbints) {
        if (constrbints instbnceof GridBbgConstrbints) {
            setConstrbints(comp, (GridBbgConstrbints)constrbints);
        } else if (constrbints != null) {
            throw new IllegblArgumentException("cbnnot bdd to lbyout: constrbints must be b GridBbgConstrbint");
        }
    }

    /**
     * Removes the specified component from this lbyout.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     * @pbrbm    comp   the component to be removed.
     * @see      jbvb.bwt.Contbiner#remove(jbvb.bwt.Component)
     * @see      jbvb.bwt.Contbiner#removeAll()
     */
    public void removeLbyoutComponent(Component comp) {
        removeConstrbints(comp);
    }

    /**
     * Determines the preferred size of the <code>pbrent</code>
     * contbiner using this grid bbg lbyout.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     *
     * @pbrbm     pbrent   the contbiner in which to do the lbyout
     * @see       jbvb.bwt.Contbiner#getPreferredSize
     * @return the preferred size of the <code>pbrent</code>
     *  contbiner
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        GridBbgLbyoutInfo info = getLbyoutInfo(pbrent, PREFERREDSIZE);
        return getMinSize(pbrent, info);
    }

    /**
     * Determines the minimum size of the <code>pbrent</code> contbiner
     * using this grid bbg lbyout.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     * @pbrbm     pbrent   the contbiner in which to do the lbyout
     * @see       jbvb.bwt.Contbiner#doLbyout
     * @return the minimum size of the <code>pbrent</code> contbiner
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        GridBbgLbyoutInfo info = getLbyoutInfo(pbrent, MINSIZE);
        return getMinSize(pbrent, info);
    }

    /**
     * Returns the mbximum dimensions for this lbyout given the components
     * in the specified tbrget contbiner.
     * @pbrbm tbrget the contbiner which needs to be lbid out
     * @see Contbiner
     * @see #minimumLbyoutSize(Contbiner)
     * @see #preferredLbyoutSize(Contbiner)
     * @return the mbximum dimensions for this lbyout
     */
    public Dimension mbximumLbyoutSize(Contbiner tbrget) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Returns the blignment blong the x bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     * <p>
     * @return the vblue <code>0.5f</code> to indicbte centered
     */
    public flobt getLbyoutAlignmentX(Contbiner pbrent) {
        return 0.5f;
    }

    /**
     * Returns the blignment blong the y bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     * <p>
     * @return the vblue <code>0.5f</code> to indicbte centered
     */
    public flobt getLbyoutAlignmentY(Contbiner pbrent) {
        return 0.5f;
    }

    /**
     * Invblidbtes the lbyout, indicbting thbt if the lbyout mbnbger
     * hbs cbched informbtion it should be discbrded.
     */
    public void invblidbteLbyout(Contbiner tbrget) {
    }

    /**
     * Lbys out the specified contbiner using this grid bbg lbyout.
     * This method reshbpes components in the specified contbiner in
     * order to sbtisfy the constrbints of this <code>GridBbgLbyout</code>
     * object.
     * <p>
     * Most bpplicbtions do not cbll this method directly.
     * @pbrbm pbrent the contbiner in which to do the lbyout
     * @see jbvb.bwt.Contbiner
     * @see jbvb.bwt.Contbiner#doLbyout
     */
    public void lbyoutContbiner(Contbiner pbrent) {
        brrbngeGrid(pbrent);
    }

    /**
     * Returns b string representbtion of this grid bbg lbyout's vblues.
     * @return     b string representbtion of this grid bbg lbyout.
     */
    public String toString() {
        return getClbss().getNbme();
    }

    /**
     * Print the lbyout informbtion.  Useful for debugging.
     */

    /* DEBUG
     *
     *  protected void dumpLbyoutInfo(GridBbgLbyoutInfo s) {
     *    int x;
     *
     *    System.out.println("Col\tWidth\tWeight");
     *    for (x=0; x<s.width; x++) {
     *      System.out.println(x + "\t" +
     *                   s.minWidth[x] + "\t" +
     *                   s.weightX[x]);
     *    }
     *    System.out.println("Row\tHeight\tWeight");
     *    for (x=0; x<s.height; x++) {
     *      System.out.println(x + "\t" +
     *                   s.minHeight[x] + "\t" +
     *                   s.weightY[x]);
     *    }
     *  }
     */

    /**
     * Print the lbyout constrbints.  Useful for debugging.
     */

    /* DEBUG
     *
     *  protected void dumpConstrbints(GridBbgConstrbints constrbints) {
     *    System.out.println(
     *                 "wt " +
     *                 constrbints.weightx +
     *                 " " +
     *                 constrbints.weighty +
     *                 ", " +
     *
     *                 "box " +
     *                 constrbints.gridx +
     *                 " " +
     *                 constrbints.gridy +
     *                 " " +
     *                 constrbints.gridwidth +
     *                 " " +
     *                 constrbints.gridheight +
     *                 ", " +
     *
     *                 "min " +
     *                 constrbints.minWidth +
     *                 " " +
     *                 constrbints.minHeight +
     *                 ", " +
     *
     *                 "pbd " +
     *                 constrbints.insets.bottom +
     *                 " " +
     *                 constrbints.insets.left +
     *                 " " +
     *                 constrbints.insets.right +
     *                 " " +
     *                 constrbints.insets.top +
     *                 " " +
     *                 constrbints.ipbdx +
     *                 " " +
     *                 constrbints.ipbdy);
     *  }
     */

    /**
     * Fills in bn instbnce of <code>GridBbgLbyoutInfo</code> for the
     * current set of mbnbged children. This requires three pbsses through the
     * set of children:
     *
     * <ol>
     * <li>Figure out the dimensions of the lbyout grid.
     * <li>Determine which cells the components occupy.
     * <li>Distribute the weights bnd min sizes bmong the rows/columns.
     * </ol>
     *
     * This blso cbches the minsizes for bll the children when they bre
     * first encountered (so subsequent loops don't need to bsk bgbin).
     * <p>
     * This method should only be used internblly by
     * <code>GridBbgLbyout</code>.
     *
     * @pbrbm pbrent  the lbyout contbiner
     * @pbrbm sizeflbg either <code>PREFERREDSIZE</code> or
     *   <code>MINSIZE</code>
     * @return the <code>GridBbgLbyoutInfo</code> for the set of children
     * @since 1.4
     */
    protected GridBbgLbyoutInfo getLbyoutInfo(Contbiner pbrent, int sizeflbg) {
        return GetLbyoutInfo(pbrent, sizeflbg);
    }

    /*
     * Cblculbte mbximum brrby sizes to bllocbte brrbys without ensureCbpbcity
     * we mby use preCblculbted sizes in whole clbss becbuse of upper estimbtion of
     * mbximumArrbyXIndex bnd mbximumArrbyYIndex.
     */

    privbte long[]  preInitMbximumArrbySizes(Contbiner pbrent){
        Component components[] = pbrent.getComponents();
        Component comp;
        GridBbgConstrbints constrbints;
        int curX, curY;
        int curWidth, curHeight;
        int preMbximumArrbyXIndex = 0;
        int preMbximumArrbyYIndex = 0;
        long [] returnArrby = new long[2];

        for (int compId = 0 ; compId < components.length ; compId++) {
            comp = components[compId];
            if (!comp.isVisible()) {
                continue;
            }

            constrbints = lookupConstrbints(comp);
            curX = constrbints.gridx;
            curY = constrbints.gridy;
            curWidth = constrbints.gridwidth;
            curHeight = constrbints.gridheight;

            // -1==RELATIVE, mebns thbt column|row equbls to previously bdded component,
            // since ebch next Component with gridx|gridy == RELATIVE stbrts from
            // previous position, so we should stbrt from previous component which
            // blrebdy used in mbximumArrby[X|Y]Index cblculbtion. We could just increbse
            // mbximum by 1 to hbndle situbtion when component with gridx=-1 wbs bdded.
            if (curX < 0){
                curX = ++preMbximumArrbyYIndex;
            }
            if (curY < 0){
                curY = ++preMbximumArrbyXIndex;
            }
            // gridwidth|gridheight mby be equbl to RELATIVE (-1) or REMAINDER (0)
            // in bny cbse using 1 instebd of 0 or -1 should be sufficient to for
            // correct mbximumArrbySizes cblculbtion
            if (curWidth <= 0){
                curWidth = 1;
            }
            if (curHeight <= 0){
                curHeight = 1;
            }

            preMbximumArrbyXIndex = Mbth.mbx(curY + curHeight, preMbximumArrbyXIndex);
            preMbximumArrbyYIndex = Mbth.mbx(curX + curWidth, preMbximumArrbyYIndex);
        } //for (components) loop
        // Must specify index++ to bllocbte well-working brrbys.
        /* fix for 4623196.
         * now return long brrby instebd of Point
         */
        returnArrby[0] = preMbximumArrbyXIndex;
        returnArrby[1] = preMbximumArrbyYIndex;
        return returnArrby;
    } //PreInitMbximumSizes

    /**
     * This method is obsolete bnd supplied for bbckwbrds
     * compbtibility only; new code should cbll {@link
     * #getLbyoutInfo(jbvb.bwt.Contbiner, int) getLbyoutInfo} instebd.
     *
     * Fills in bn instbnce of {@code GridBbgLbyoutInfo} for the
     * current set of mbnbged children. This method is the sbme
     * bs {@code getLbyoutInfo}; refer to {@code getLbyoutInfo}
     * description for detbils.
     *
     * @pbrbm  pbrent the lbyout contbiner
     * @pbrbm  sizeflbg either {@code PREFERREDSIZE} or {@code MINSIZE}
     * @return the {@code GridBbgLbyoutInfo} for the set of children
     */
    protected GridBbgLbyoutInfo GetLbyoutInfo(Contbiner pbrent, int sizeflbg) {
        synchronized (pbrent.getTreeLock()) {
            GridBbgLbyoutInfo r;
            Component comp;
            GridBbgConstrbints constrbints;
            Dimension d;
            Component components[] = pbrent.getComponents();
            // Code below will bddress index curX+curWidth in the cbse of yMbxArrby, weightY
            // ( respectively curY+curHeight for xMbxArrby, weightX ) where
            //  curX in 0 to preInitMbximumArrbySizes.y
            // Thus, the mbximum index thbt could
            // be cblculbted in the following code is curX+curX.
            // EmpericMultier equbls 2 becbuse of this.

            int lbyoutWidth, lbyoutHeight;
            int []xMbxArrby;
            int []yMbxArrby;
            int compindex, i, k, px, py, pixels_diff, nextSize;
            int curX = 0; // constrbints.gridx
            int curY = 0; // constrbints.gridy
            int curWidth = 1;  // constrbints.gridwidth
            int curHeight = 1;  // constrbints.gridheight
            int curRow, curCol;
            double weight_diff, weight;
            int mbximumArrbyXIndex = 0;
            int mbximumArrbyYIndex = 0;
            int bnchor;

            /*
             * Pbss #1
             *
             * Figure out the dimensions of the lbyout grid (use b vblue of 1 for
             * zero or negbtive widths bnd heights).
             */

            lbyoutWidth = lbyoutHeight = 0;
            curRow = curCol = -1;
            long [] brrbySizes = preInitMbximumArrbySizes(pbrent);

            /* fix for 4623196.
             * If user try to crebte b very big grid we cbn
             * get NegbtiveArrbySizeException becbuse of integer vblue
             * overflow (EMPIRICMULTIPLIER*gridSize might be more then Integer.MAX_VALUE).
             * We need to detect this situbtion bnd try to crebte b
             * grid with Integer.MAX_VALUE size instebd.
             */
            mbximumArrbyXIndex = (EMPIRICMULTIPLIER * brrbySizes[0] > Integer.MAX_VALUE )? Integer.MAX_VALUE : EMPIRICMULTIPLIER*(int)brrbySizes[0];
            mbximumArrbyYIndex = (EMPIRICMULTIPLIER * brrbySizes[1] > Integer.MAX_VALUE )? Integer.MAX_VALUE : EMPIRICMULTIPLIER*(int)brrbySizes[1];

            if (rowHeights != null){
                mbximumArrbyXIndex = Mbth.mbx(mbximumArrbyXIndex, rowHeights.length);
            }
            if (columnWidths != null){
                mbximumArrbyYIndex = Mbth.mbx(mbximumArrbyYIndex, columnWidths.length);
            }

            xMbxArrby = new int[mbximumArrbyXIndex];
            yMbxArrby = new int[mbximumArrbyYIndex];

            boolebn hbsBbseline = fblse;
            for (compindex = 0 ; compindex < components.length ; compindex++) {
                comp = components[compindex];
                if (!comp.isVisible())
                    continue;
                constrbints = lookupConstrbints(comp);

                curX = constrbints.gridx;
                curY = constrbints.gridy;
                curWidth = constrbints.gridwidth;
                if (curWidth <= 0)
                    curWidth = 1;
                curHeight = constrbints.gridheight;
                if (curHeight <= 0)
                    curHeight = 1;

                /* If x or y is negbtive, then use relbtive positioning: */
                if (curX < 0 && curY < 0) {
                    if (curRow >= 0)
                        curY = curRow;
                    else if (curCol >= 0)
                        curX = curCol;
                    else
                        curY = 0;
                }
                if (curX < 0) {
                    px = 0;
                    for (i = curY; i < (curY + curHeight); i++) {
                        px = Mbth.mbx(px, xMbxArrby[i]);
                    }

                    curX = px - curX - 1;
                    if(curX < 0)
                        curX = 0;
                }
                else if (curY < 0) {
                    py = 0;
                    for (i = curX; i < (curX + curWidth); i++) {
                        py = Mbth.mbx(py, yMbxArrby[i]);
                    }
                    curY = py - curY - 1;
                    if(curY < 0)
                        curY = 0;
                }

                /* Adjust the grid width bnd height
                 *  fix for 5005945: unneccessbry loops removed
                 */
                px = curX + curWidth;
                if (lbyoutWidth < px) {
                    lbyoutWidth = px;
                }
                py = curY + curHeight;
                if (lbyoutHeight < py) {
                    lbyoutHeight = py;
                }

                /* Adjust xMbxArrby bnd yMbxArrby */
                for (i = curX; i < (curX + curWidth); i++) {
                    yMbxArrby[i] =py;
                }
                for (i = curY; i < (curY + curHeight); i++) {
                    xMbxArrby[i] = px;
                }


                /* Cbche the current slbve's size. */
                if (sizeflbg == PREFERREDSIZE)
                    d = comp.getPreferredSize();
                else
                    d = comp.getMinimumSize();
                constrbints.minWidth = d.width;
                constrbints.minHeight = d.height;
                if (cblculbteBbseline(comp, constrbints, d)) {
                    hbsBbseline = true;
                }

                /* Zero width bnd height must mebn thbt this is the lbst item (or
                 * else something is wrong). */
                if (constrbints.gridheight == 0 && constrbints.gridwidth == 0)
                    curRow = curCol = -1;

                /* Zero width stbrts b new row */
                if (constrbints.gridheight == 0 && curRow < 0)
                    curCol = curX + curWidth;

                /* Zero height stbrts b new column */
                else if (constrbints.gridwidth == 0 && curCol < 0)
                    curRow = curY + curHeight;
            } //for (components) loop


            /*
             * Apply minimum row/column dimensions
             */
            if (columnWidths != null && lbyoutWidth < columnWidths.length)
                lbyoutWidth = columnWidths.length;
            if (rowHeights != null && lbyoutHeight < rowHeights.length)
                lbyoutHeight = rowHeights.length;

            r = new GridBbgLbyoutInfo(lbyoutWidth, lbyoutHeight);

            /*
             * Pbss #2
             *
             * Negbtive vblues for gridX bre filled in with the current x vblue.
             * Negbtive vblues for gridY bre filled in with the current y vblue.
             * Negbtive or zero vblues for gridWidth bnd gridHeight end the current
             *  row or column, respectively.
             */

            curRow = curCol = -1;

            Arrbys.fill(xMbxArrby, 0);
            Arrbys.fill(yMbxArrby, 0);

            int[] mbxAscent = null;
            int[] mbxDescent = null;
            short[] bbselineType = null;

            if (hbsBbseline) {
                r.mbxAscent = mbxAscent = new int[lbyoutHeight];
                r.mbxDescent = mbxDescent = new int[lbyoutHeight];
                r.bbselineType = bbselineType = new short[lbyoutHeight];
                r.hbsBbseline = true;
            }


            for (compindex = 0 ; compindex < components.length ; compindex++) {
                comp = components[compindex];
                if (!comp.isVisible())
                    continue;
                constrbints = lookupConstrbints(comp);

                curX = constrbints.gridx;
                curY = constrbints.gridy;
                curWidth = constrbints.gridwidth;
                curHeight = constrbints.gridheight;

                /* If x or y is negbtive, then use relbtive positioning: */
                if (curX < 0 && curY < 0) {
                    if(curRow >= 0)
                        curY = curRow;
                    else if(curCol >= 0)
                        curX = curCol;
                    else
                        curY = 0;
                }

                if (curX < 0) {
                    if (curHeight <= 0) {
                        curHeight += r.height - curY;
                        if (curHeight < 1)
                            curHeight = 1;
                    }

                    px = 0;
                    for (i = curY; i < (curY + curHeight); i++)
                        px = Mbth.mbx(px, xMbxArrby[i]);

                    curX = px - curX - 1;
                    if(curX < 0)
                        curX = 0;
                }
                else if (curY < 0) {
                    if (curWidth <= 0) {
                        curWidth += r.width - curX;
                        if (curWidth < 1)
                            curWidth = 1;
                    }

                    py = 0;
                    for (i = curX; i < (curX + curWidth); i++){
                        py = Mbth.mbx(py, yMbxArrby[i]);
                    }

                    curY = py - curY - 1;
                    if(curY < 0)
                        curY = 0;
                }

                if (curWidth <= 0) {
                    curWidth += r.width - curX;
                    if (curWidth < 1)
                        curWidth = 1;
                }

                if (curHeight <= 0) {
                    curHeight += r.height - curY;
                    if (curHeight < 1)
                        curHeight = 1;
                }

                px = curX + curWidth;
                py = curY + curHeight;

                for (i = curX; i < (curX + curWidth); i++) { yMbxArrby[i] = py; }
                for (i = curY; i < (curY + curHeight); i++) { xMbxArrby[i] = px; }

                /* Mbke negbtive sizes stbrt b new row/column */
                if (constrbints.gridheight == 0 && constrbints.gridwidth == 0)
                    curRow = curCol = -1;
                if (constrbints.gridheight == 0 && curRow < 0)
                    curCol = curX + curWidth;
                else if (constrbints.gridwidth == 0 && curCol < 0)
                    curRow = curY + curHeight;

                /* Assign the new vblues to the gridbbg slbve */
                constrbints.tempX = curX;
                constrbints.tempY = curY;
                constrbints.tempWidth = curWidth;
                constrbints.tempHeight = curHeight;

                bnchor = constrbints.bnchor;
                if (hbsBbseline) {
                    switch(bnchor) {
                    cbse GridBbgConstrbints.BASELINE:
                    cbse GridBbgConstrbints.BASELINE_LEADING:
                    cbse GridBbgConstrbints.BASELINE_TRAILING:
                        if (constrbints.bscent >= 0) {
                            if (curHeight == 1) {
                                mbxAscent[curY] =
                                        Mbth.mbx(mbxAscent[curY],
                                                 constrbints.bscent);
                                mbxDescent[curY] =
                                        Mbth.mbx(mbxDescent[curY],
                                                 constrbints.descent);
                            }
                            else {
                                if (constrbints.bbselineResizeBehbvior ==
                                        Component.BbselineResizeBehbvior.
                                        CONSTANT_DESCENT) {
                                    mbxDescent[curY + curHeight - 1] =
                                        Mbth.mbx(mbxDescent[curY + curHeight
                                                            - 1],
                                                 constrbints.descent);
                                }
                                else {
                                    mbxAscent[curY] = Mbth.mbx(mbxAscent[curY],
                                                           constrbints.bscent);
                                }
                            }
                            if (constrbints.bbselineResizeBehbvior ==
                                    Component.BbselineResizeBehbvior.CONSTANT_DESCENT) {
                                bbselineType[curY + curHeight - 1] |=
                                        (1 << constrbints.
                                         bbselineResizeBehbvior.ordinbl());
                            }
                            else {
                                bbselineType[curY] |= (1 << constrbints.
                                             bbselineResizeBehbvior.ordinbl());
                            }
                        }
                        brebk;
                    cbse GridBbgConstrbints.ABOVE_BASELINE:
                    cbse GridBbgConstrbints.ABOVE_BASELINE_LEADING:
                    cbse GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
                        // Component positioned bbove the bbseline.
                        // To mbke the bottom edge of the component bligned
                        // with the bbseline the bottom inset is
                        // bdded to the descent, the rest to the bscent.
                        pixels_diff = constrbints.minHeight +
                                constrbints.insets.top +
                                constrbints.ipbdy;
                        mbxAscent[curY] = Mbth.mbx(mbxAscent[curY],
                                                   pixels_diff);
                        mbxDescent[curY] = Mbth.mbx(mbxDescent[curY],
                                                    constrbints.insets.bottom);
                        brebk;
                    cbse GridBbgConstrbints.BELOW_BASELINE:
                    cbse GridBbgConstrbints.BELOW_BASELINE_LEADING:
                    cbse GridBbgConstrbints.BELOW_BASELINE_TRAILING:
                        // Component positioned below the bbseline.
                        // To mbke the top edge of the component bligned
                        // with the bbseline the top inset is
                        // bdded to the bscent, the rest to the descent.
                        pixels_diff = constrbints.minHeight +
                                constrbints.insets.bottom + constrbints.ipbdy;
                        mbxDescent[curY] = Mbth.mbx(mbxDescent[curY],
                                                    pixels_diff);
                        mbxAscent[curY] = Mbth.mbx(mbxAscent[curY],
                                                   constrbints.insets.top);
                        brebk;
                    }
                }
            }

            r.weightX = new double[mbximumArrbyYIndex];
            r.weightY = new double[mbximumArrbyXIndex];
            r.minWidth = new int[mbximumArrbyYIndex];
            r.minHeight = new int[mbximumArrbyXIndex];


            /*
             * Apply minimum row/column dimensions bnd weights
             */
            if (columnWidths != null)
                System.brrbycopy(columnWidths, 0, r.minWidth, 0, columnWidths.length);
            if (rowHeights != null)
                System.brrbycopy(rowHeights, 0, r.minHeight, 0, rowHeights.length);
            if (columnWeights != null)
                System.brrbycopy(columnWeights, 0, r.weightX, 0,  Mbth.min(r.weightX.length, columnWeights.length));
            if (rowWeights != null)
                System.brrbycopy(rowWeights, 0, r.weightY, 0,  Mbth.min(r.weightY.length, rowWeights.length));

            /*
             * Pbss #3
             *
             * Distribute the minimun widths bnd weights:
             */

            nextSize = Integer.MAX_VALUE;

            for (i = 1;
                 i != Integer.MAX_VALUE;
                 i = nextSize, nextSize = Integer.MAX_VALUE) {
                for (compindex = 0 ; compindex < components.length ; compindex++) {
                    comp = components[compindex];
                    if (!comp.isVisible())
                        continue;
                    constrbints = lookupConstrbints(comp);

                    if (constrbints.tempWidth == i) {
                        px = constrbints.tempX + constrbints.tempWidth; /* right column */

                        /*
                         * Figure out if we should use this slbve\'s weight.  If the weight
                         * is less thbn the totbl weight spbnned by the width of the cell,
                         * then discbrd the weight.  Otherwise split the difference
                         * bccording to the existing weights.
                         */

                        weight_diff = constrbints.weightx;
                        for (k = constrbints.tempX; k < px; k++)
                            weight_diff -= r.weightX[k];
                        if (weight_diff > 0.0) {
                            weight = 0.0;
                            for (k = constrbints.tempX; k < px; k++)
                                weight += r.weightX[k];
                            for (k = constrbints.tempX; weight > 0.0 && k < px; k++) {
                                double wt = r.weightX[k];
                                double dx = (wt * weight_diff) / weight;
                                r.weightX[k] += dx;
                                weight_diff -= dx;
                                weight -= wt;
                            }
                            /* Assign the rembinder to the rightmost cell */
                            r.weightX[px-1] += weight_diff;
                        }

                        /*
                         * Cblculbte the minWidth brrby vblues.
                         * First, figure out how wide the current slbve needs to be.
                         * Then, see if it will fit within the current minWidth vblues.
                         * If it will not fit, bdd the difference bccording to the
                         * weightX brrby.
                         */

                        pixels_diff =
                            constrbints.minWidth + constrbints.ipbdx +
                            constrbints.insets.left + constrbints.insets.right;

                        for (k = constrbints.tempX; k < px; k++)
                            pixels_diff -= r.minWidth[k];
                        if (pixels_diff > 0) {
                            weight = 0.0;
                            for (k = constrbints.tempX; k < px; k++)
                                weight += r.weightX[k];
                            for (k = constrbints.tempX; weight > 0.0 && k < px; k++) {
                                double wt = r.weightX[k];
                                int dx = (int)((wt * ((double)pixels_diff)) / weight);
                                r.minWidth[k] += dx;
                                pixels_diff -= dx;
                                weight -= wt;
                            }
                            /* Any leftovers go into the rightmost cell */
                            r.minWidth[px-1] += pixels_diff;
                        }
                    }
                    else if (constrbints.tempWidth > i && constrbints.tempWidth < nextSize)
                        nextSize = constrbints.tempWidth;


                    if (constrbints.tempHeight == i) {
                        py = constrbints.tempY + constrbints.tempHeight; /* bottom row */

                        /*
                         * Figure out if we should use this slbve's weight.  If the weight
                         * is less thbn the totbl weight spbnned by the height of the cell,
                         * then discbrd the weight.  Otherwise split it the difference
                         * bccording to the existing weights.
                         */

                        weight_diff = constrbints.weighty;
                        for (k = constrbints.tempY; k < py; k++)
                            weight_diff -= r.weightY[k];
                        if (weight_diff > 0.0) {
                            weight = 0.0;
                            for (k = constrbints.tempY; k < py; k++)
                                weight += r.weightY[k];
                            for (k = constrbints.tempY; weight > 0.0 && k < py; k++) {
                                double wt = r.weightY[k];
                                double dy = (wt * weight_diff) / weight;
                                r.weightY[k] += dy;
                                weight_diff -= dy;
                                weight -= wt;
                            }
                            /* Assign the rembinder to the bottom cell */
                            r.weightY[py-1] += weight_diff;
                        }

                        /*
                         * Cblculbte the minHeight brrby vblues.
                         * First, figure out how tbll the current slbve needs to be.
                         * Then, see if it will fit within the current minHeight vblues.
                         * If it will not fit, bdd the difference bccording to the
                         * weightY brrby.
                         */

                        pixels_diff = -1;
                        if (hbsBbseline) {
                            switch(constrbints.bnchor) {
                            cbse GridBbgConstrbints.BASELINE:
                            cbse GridBbgConstrbints.BASELINE_LEADING:
                            cbse GridBbgConstrbints.BASELINE_TRAILING:
                                if (constrbints.bscent >= 0) {
                                    if (constrbints.tempHeight == 1) {
                                        pixels_diff =
                                            mbxAscent[constrbints.tempY] +
                                            mbxDescent[constrbints.tempY];
                                    }
                                    else if (constrbints.bbselineResizeBehbvior !=
                                             Component.BbselineResizeBehbvior.
                                             CONSTANT_DESCENT) {
                                        pixels_diff =
                                                mbxAscent[constrbints.tempY] +
                                                constrbints.descent;
                                    }
                                    else {
                                        pixels_diff = constrbints.bscent +
                                                mbxDescent[constrbints.tempY +
                                                   constrbints.tempHeight - 1];
                                    }
                                }
                                brebk;
                            cbse GridBbgConstrbints.ABOVE_BASELINE:
                            cbse GridBbgConstrbints.ABOVE_BASELINE_LEADING:
                            cbse GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
                                pixels_diff = constrbints.insets.top +
                                        constrbints.minHeight +
                                        constrbints.ipbdy +
                                        mbxDescent[constrbints.tempY];
                                brebk;
                            cbse GridBbgConstrbints.BELOW_BASELINE:
                            cbse GridBbgConstrbints.BELOW_BASELINE_LEADING:
                            cbse GridBbgConstrbints.BELOW_BASELINE_TRAILING:
                                pixels_diff = mbxAscent[constrbints.tempY] +
                                        constrbints.minHeight +
                                        constrbints.insets.bottom +
                                        constrbints.ipbdy;
                                brebk;
                            }
                        }
                        if (pixels_diff == -1) {
                            pixels_diff =
                                constrbints.minHeight + constrbints.ipbdy +
                                constrbints.insets.top +
                                constrbints.insets.bottom;
                        }
                        for (k = constrbints.tempY; k < py; k++)
                            pixels_diff -= r.minHeight[k];
                        if (pixels_diff > 0) {
                            weight = 0.0;
                            for (k = constrbints.tempY; k < py; k++)
                                weight += r.weightY[k];
                            for (k = constrbints.tempY; weight > 0.0 && k < py; k++) {
                                double wt = r.weightY[k];
                                int dy = (int)((wt * ((double)pixels_diff)) / weight);
                                r.minHeight[k] += dy;
                                pixels_diff -= dy;
                                weight -= wt;
                            }
                            /* Any leftovers go into the bottom cell */
                            r.minHeight[py-1] += pixels_diff;
                        }
                    }
                    else if (constrbints.tempHeight > i &&
                             constrbints.tempHeight < nextSize)
                        nextSize = constrbints.tempHeight;
                }
            }
            return r;
        }
    } //getLbyoutInfo()

    /**
     * Cblculbte the bbseline for the specified component.
     * If {@code c} is positioned blong it's bbseline, the bbseline is
     * obtbined bnd the {@code constrbints} bscent, descent bnd
     * bbseline resize behbvior bre set from the component; bnd true is
     * returned. Otherwise fblse is returned.
     */
    privbte boolebn cblculbteBbseline(Component c,
                                      GridBbgConstrbints constrbints,
                                      Dimension size) {
        int bnchor = constrbints.bnchor;
        if (bnchor == GridBbgConstrbints.BASELINE ||
                bnchor == GridBbgConstrbints.BASELINE_LEADING ||
                bnchor == GridBbgConstrbints.BASELINE_TRAILING) {
            // Apply the pbdding to the component, then bsk for the bbseline.
            int w = size.width + constrbints.ipbdx;
            int h = size.height + constrbints.ipbdy;
            constrbints.bscent = c.getBbseline(w, h);
            if (constrbints.bscent >= 0) {
                // Component hbs b bbseline
                int bbseline = constrbints.bscent;
                // Adjust the bscent bnd descent to include the insets.
                constrbints.descent = h - constrbints.bscent +
                            constrbints.insets.bottom;
                constrbints.bscent += constrbints.insets.top;
                constrbints.bbselineResizeBehbvior =
                        c.getBbselineResizeBehbvior();
                constrbints.centerPbdding = 0;
                if (constrbints.bbselineResizeBehbvior == Component.
                        BbselineResizeBehbvior.CENTER_OFFSET) {
                    // Component hbs b bbseline resize behbvior of
                    // CENTER_OFFSET, cblculbte centerPbdding bnd
                    // centerOffset (see the description of
                    // CENTER_OFFSET in the enum for detbis on this
                    // blgorithm).
                    int nextBbseline = c.getBbseline(w, h + 1);
                    constrbints.centerOffset = bbseline - h / 2;
                    if (h % 2 == 0) {
                        if (bbseline != nextBbseline) {
                            constrbints.centerPbdding = 1;
                        }
                    }
                    else if (bbseline == nextBbseline){
                        constrbints.centerOffset--;
                        constrbints.centerPbdding = 1;
                    }
                }
            }
            return true;
        }
        else {
            constrbints.bscent = -1;
            return fblse;
        }
    }

    /**
     * Adjusts the x, y, width, bnd height fields to the correct
     * vblues depending on the constrbint geometry bnd pbds.
     * This method should only be used internblly by
     * <code>GridBbgLbyout</code>.
     *
     * @pbrbm constrbints the constrbints to be bpplied
     * @pbrbm r the <code>Rectbngle</code> to be bdjusted
     * @since 1.4
     */
    protected void bdjustForGrbvity(GridBbgConstrbints constrbints,
                                    Rectbngle r) {
        AdjustForGrbvity(constrbints, r);
    }

    /**
     * Adjusts the x, y, width, bnd height fields to the correct
     * vblues depending on the constrbint geometry bnd pbds.
     * <p>
     * This method is obsolete bnd supplied for bbckwbrds
     * compbtibility only; new code should cbll {@link
     * #bdjustForGrbvity(jbvb.bwt.GridBbgConstrbints, jbvb.bwt.Rectbngle)
     * bdjustForGrbvity} instebd.
     * This method is the sbme bs <code>bdjustForGrbvity</code>
     *
     * @pbrbm  constrbints the constrbints to be bpplied
     * @pbrbm  r the {@code Rectbngle} to be bdjusted
     */
    protected void AdjustForGrbvity(GridBbgConstrbints constrbints,
                                    Rectbngle r) {
        int diffx, diffy;
        int cellY = r.y;
        int cellHeight = r.height;

        if (!rightToLeft) {
            r.x += constrbints.insets.left;
        } else {
            r.x -= r.width - constrbints.insets.right;
        }
        r.width -= (constrbints.insets.left + constrbints.insets.right);
        r.y += constrbints.insets.top;
        r.height -= (constrbints.insets.top + constrbints.insets.bottom);

        diffx = 0;
        if ((constrbints.fill != GridBbgConstrbints.HORIZONTAL &&
             constrbints.fill != GridBbgConstrbints.BOTH)
            && (r.width > (constrbints.minWidth + constrbints.ipbdx))) {
            diffx = r.width - (constrbints.minWidth + constrbints.ipbdx);
            r.width = constrbints.minWidth + constrbints.ipbdx;
        }

        diffy = 0;
        if ((constrbints.fill != GridBbgConstrbints.VERTICAL &&
             constrbints.fill != GridBbgConstrbints.BOTH)
            && (r.height > (constrbints.minHeight + constrbints.ipbdy))) {
            diffy = r.height - (constrbints.minHeight + constrbints.ipbdy);
            r.height = constrbints.minHeight + constrbints.ipbdy;
        }

        switch (constrbints.bnchor) {
          cbse GridBbgConstrbints.BASELINE:
              r.x += diffx/2;
              blignOnBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.BASELINE_LEADING:
              if (rightToLeft) {
                  r.x += diffx;
              }
              blignOnBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.BASELINE_TRAILING:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              blignOnBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.ABOVE_BASELINE:
              r.x += diffx/2;
              blignAboveBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.ABOVE_BASELINE_LEADING:
              if (rightToLeft) {
                  r.x += diffx;
              }
              blignAboveBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              blignAboveBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.BELOW_BASELINE:
              r.x += diffx/2;
              blignBelowBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.BELOW_BASELINE_LEADING:
              if (rightToLeft) {
                  r.x += diffx;
              }
              blignBelowBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.BELOW_BASELINE_TRAILING:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              blignBelowBbseline(constrbints, r, cellY, cellHeight);
              brebk;
          cbse GridBbgConstrbints.CENTER:
              r.x += diffx/2;
              r.y += diffy/2;
              brebk;
          cbse GridBbgConstrbints.PAGE_START:
          cbse GridBbgConstrbints.NORTH:
              r.x += diffx/2;
              brebk;
          cbse GridBbgConstrbints.NORTHEAST:
              r.x += diffx;
              brebk;
          cbse GridBbgConstrbints.EAST:
              r.x += diffx;
              r.y += diffy/2;
              brebk;
          cbse GridBbgConstrbints.SOUTHEAST:
              r.x += diffx;
              r.y += diffy;
              brebk;
          cbse GridBbgConstrbints.PAGE_END:
          cbse GridBbgConstrbints.SOUTH:
              r.x += diffx/2;
              r.y += diffy;
              brebk;
          cbse GridBbgConstrbints.SOUTHWEST:
              r.y += diffy;
              brebk;
          cbse GridBbgConstrbints.WEST:
              r.y += diffy/2;
              brebk;
          cbse GridBbgConstrbints.NORTHWEST:
              brebk;
          cbse GridBbgConstrbints.LINE_START:
              if (rightToLeft) {
                  r.x += diffx;
              }
              r.y += diffy/2;
              brebk;
          cbse GridBbgConstrbints.LINE_END:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              r.y += diffy/2;
              brebk;
          cbse GridBbgConstrbints.FIRST_LINE_START:
              if (rightToLeft) {
                  r.x += diffx;
              }
              brebk;
          cbse GridBbgConstrbints.FIRST_LINE_END:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              brebk;
          cbse GridBbgConstrbints.LAST_LINE_START:
              if (rightToLeft) {
                  r.x += diffx;
              }
              r.y += diffy;
              brebk;
          cbse GridBbgConstrbints.LAST_LINE_END:
              if (!rightToLeft) {
                  r.x += diffx;
              }
              r.y += diffy;
              brebk;
          defbult:
              throw new IllegblArgumentException("illegbl bnchor vblue");
        }
    }

    /**
     * Positions on the bbseline.
     *
     * @pbrbm cellY the locbtion of the row, does not include insets
     * @pbrbm cellHeight the height of the row, does not tbke into bccount
     *        insets
     * @pbrbm r bvbilbble bounds for the component, is pbdded by insets bnd
     *        ipbdy
     */
    privbte void blignOnBbseline(GridBbgConstrbints cons, Rectbngle r,
                                 int cellY, int cellHeight) {
        if (cons.bscent >= 0) {
            if (cons.bbselineResizeBehbvior == Component.
                    BbselineResizeBehbvior.CONSTANT_DESCENT) {
                // Anchor to the bottom.
                // Bbseline is bt (cellY + cellHeight - mbxDescent).
                // Bottom of component (mbxY) is bt bbseline + descent
                // of component. We need to subtrbct the bottom inset here
                // bs the descent in the constrbints object includes the
                // bottom inset.
                int mbxY = cellY + cellHeight -
                      lbyoutInfo.mbxDescent[cons.tempY + cons.tempHeight - 1] +
                      cons.descent - cons.insets.bottom;
                if (!cons.isVerticbllyResizbble()) {
                    // Component not resizbble, cblculbte y locbtion
                    // from mbxY - height.
                    r.y = mbxY - cons.minHeight;
                    r.height = cons.minHeight;
                } else {
                    // Component is resizbble. As brb is constbnt descent,
                    // cbn expbnd component to fill region bbove bbseline.
                    // Subtrbct out the top inset so thbt components insets
                    // bre honored.
                    r.height = mbxY - cellY - cons.insets.top;
                }
            }
            else {
                // BRB is not constbnt_descent
                int bbseline; // bbseline for the row, relbtive to cellY
                // Component bbseline, includes insets.top
                int bscent = cons.bscent;
                if (lbyoutInfo.hbsConstbntDescent(cons.tempY)) {
                    // Mixed bscent/descent in sbme row, cblculbte position
                    // off mbxDescent
                    bbseline = cellHeight - lbyoutInfo.mbxDescent[cons.tempY];
                }
                else {
                    // Only bscents/unknown in this row, bnchor to top
                    bbseline = lbyoutInfo.mbxAscent[cons.tempY];
                }
                if (cons.bbselineResizeBehbvior == Component.
                        BbselineResizeBehbvior.OTHER) {
                    // BRB is other, which mebns we cbn only determine
                    // the bbseline by bsking for it bgbin giving the
                    // size we plbn on using for the component.
                    boolebn fits = fblse;
                    bscent = componentAdjusting.getBbseline(r.width, r.height);
                    if (bscent >= 0) {
                        // Component hbs b bbseline, pbd with top inset
                        // (this follows from cblculbteBbseline which
                        // does the sbme).
                        bscent += cons.insets.top;
                    }
                    if (bscent >= 0 && bscent <= bbseline) {
                        // Components bbseline fits within rows bbseline.
                        // Mbke sure the descent fits within the spbce bs well.
                        if (bbseline + (r.height - bscent - cons.insets.top) <=
                                cellHeight - cons.insets.bottom) {
                            // It fits, we're good.
                            fits = true;
                        }
                        else if (cons.isVerticbllyResizbble()) {
                            // Doesn't fit, but it's resizbble.  Try
                            // bgbin bssuming we'll get bscent bgbin.
                            int bscent2 = componentAdjusting.getBbseline(
                                    r.width, cellHeight - cons.insets.bottom -
                                    bbseline + bscent);
                            if (bscent2 >= 0) {
                                bscent2 += cons.insets.top;
                            }
                            if (bscent2 >= 0 && bscent2 <= bscent) {
                                // It'll fit
                                r.height = cellHeight - cons.insets.bottom -
                                        bbseline + bscent;
                                bscent = bscent2;
                                fits = true;
                            }
                        }
                    }
                    if (!fits) {
                        // Doesn't fit, use min size bnd originbl bscent
                        bscent = cons.bscent;
                        r.width = cons.minWidth;
                        r.height = cons.minHeight;
                    }
                }
                // Reset the components y locbtion bbsed on
                // components bscent bnd bbseline for row. Becbuse bscent
                // includes the bbseline
                r.y = cellY + bbseline - bscent + cons.insets.top;
                if (cons.isVerticbllyResizbble()) {
                    switch(cons.bbselineResizeBehbvior) {
                    cbse CONSTANT_ASCENT:
                        r.height = Mbth.mbx(cons.minHeight,cellY + cellHeight -
                                            r.y - cons.insets.bottom);
                        brebk;
                    cbse CENTER_OFFSET:
                        {
                            int upper = r.y - cellY - cons.insets.top;
                            int lower = cellY + cellHeight - r.y -
                                cons.minHeight - cons.insets.bottom;
                            int deltb = Mbth.min(upper, lower);
                            deltb += deltb;
                            if (deltb > 0 &&
                                (cons.minHeight + cons.centerPbdding +
                                 deltb) / 2 + cons.centerOffset != bbseline) {
                                // Off by 1
                                deltb--;
                            }
                            r.height = cons.minHeight + deltb;
                            r.y = cellY + bbseline -
                                (r.height + cons.centerPbdding) / 2 -
                                cons.centerOffset;
                        }
                        brebk;
                    cbse OTHER:
                        // Hbndled bbove
                        brebk;
                    defbult:
                        brebk;
                    }
                }
            }
        }
        else {
            centerVerticblly(cons, r, cellHeight);
        }
    }

    /**
     * Positions the specified component bbove the bbseline. Thbt is
     * the bottom edge of the component will be bligned blong the bbseline.
     * If the row does not hbve b bbseline, this centers the component.
     */
    privbte void blignAboveBbseline(GridBbgConstrbints cons, Rectbngle r,
                                    int cellY, int cellHeight) {
        if (lbyoutInfo.hbsBbseline(cons.tempY)) {
            int mbxY; // Bbseline for the row
            if (lbyoutInfo.hbsConstbntDescent(cons.tempY)) {
                // Prefer descent
                mbxY = cellY + cellHeight - lbyoutInfo.mbxDescent[cons.tempY];
            }
            else {
                // Prefer bscent
                mbxY = cellY + lbyoutInfo.mbxAscent[cons.tempY];
            }
            if (cons.isVerticbllyResizbble()) {
                // Component is resizbble. Top edge is offset by top
                // inset, bottom edge on bbseline.
                r.y = cellY + cons.insets.top;
                r.height = mbxY - r.y;
            }
            else {
                // Not resizbble.
                r.height = cons.minHeight + cons.ipbdy;
                r.y = mbxY - r.height;
            }
        }
        else {
            centerVerticblly(cons, r, cellHeight);
        }
    }

    /**
     * Positions below the bbseline.
     */
    privbte void blignBelowBbseline(GridBbgConstrbints cons, Rectbngle r,
                                    int cellY, int cellHeight) {
        if (lbyoutInfo.hbsBbseline(cons.tempY)) {
            if (lbyoutInfo.hbsConstbntDescent(cons.tempY)) {
                // Prefer descent
                r.y = cellY + cellHeight - lbyoutInfo.mbxDescent[cons.tempY];
            }
            else {
                // Prefer bscent
                r.y = cellY + lbyoutInfo.mbxAscent[cons.tempY];
            }
            if (cons.isVerticbllyResizbble()) {
                r.height = cellY + cellHeight - r.y - cons.insets.bottom;
            }
        }
        else {
            centerVerticblly(cons, r, cellHeight);
        }
    }

    privbte void centerVerticblly(GridBbgConstrbints cons, Rectbngle r,
                                  int cellHeight) {
        if (!cons.isVerticbllyResizbble()) {
            r.y += Mbth.mbx(0, (cellHeight - cons.insets.top -
                                cons.insets.bottom - cons.minHeight -
                                cons.ipbdy) / 2);
        }
    }

    /**
     * Figures out the minimum size of the
     * mbster bbsed on the informbtion from <code>getLbyoutInfo</code>.
     * This method should only be used internblly by
     * <code>GridBbgLbyout</code>.
     *
     * @pbrbm pbrent the lbyout contbiner
     * @pbrbm info the lbyout info for this pbrent
     * @return b <code>Dimension</code> object contbining the
     *   minimum size
     * @since 1.4
     */
    protected Dimension getMinSize(Contbiner pbrent, GridBbgLbyoutInfo info) {
        return GetMinSize(pbrent, info);
    }

    /**
     * This method is obsolete bnd supplied for bbckwbrds
     * compbtibility only; new code should cbll {@link
     * #getMinSize(jbvb.bwt.Contbiner, GridBbgLbyoutInfo) getMinSize} instebd.
     * This method is the sbme bs <code>getMinSize</code>
     *
     * @pbrbm  pbrent the lbyout contbiner
     * @pbrbm  info the lbyout info for this pbrent
     * @return b <code>Dimension</code> object contbining the
     *         minimum size
     */
    protected Dimension GetMinSize(Contbiner pbrent, GridBbgLbyoutInfo info) {
        Dimension d = new Dimension();
        int i, t;
        Insets insets = pbrent.getInsets();

        t = 0;
        for(i = 0; i < info.width; i++)
            t += info.minWidth[i];
        d.width = t + insets.left + insets.right;

        t = 0;
        for(i = 0; i < info.height; i++)
            t += info.minHeight[i];
        d.height = t + insets.top + insets.bottom;

        return d;
    }

    trbnsient boolebn rightToLeft = fblse;

    /**
     * Lbys out the grid.
     * This method should only be used internblly by
     * <code>GridBbgLbyout</code>.
     *
     * @pbrbm pbrent the lbyout contbiner
     * @since 1.4
     */
    protected void brrbngeGrid(Contbiner pbrent) {
        ArrbngeGrid(pbrent);
    }

    /**
     * This method is obsolete bnd supplied for bbckwbrds
     * compbtibility only; new code should cbll {@link
     * #brrbngeGrid(Contbiner) brrbngeGrid} instebd.
     * This method is the sbme bs <code>brrbngeGrid</code>
     *
     * @pbrbm  pbrent the lbyout contbiner
     */
    protected void ArrbngeGrid(Contbiner pbrent) {
        Component comp;
        int compindex;
        GridBbgConstrbints constrbints;
        Insets insets = pbrent.getInsets();
        Component components[] = pbrent.getComponents();
        Dimension d;
        Rectbngle r = new Rectbngle();
        int i, diffw, diffh;
        double weight;
        GridBbgLbyoutInfo info;

        rightToLeft = !pbrent.getComponentOrientbtion().isLeftToRight();

        /*
         * If the pbrent hbs no slbves bnymore, then don't do bnything
         * bt bll:  just lebve the pbrent's size bs-is.
         */
        if (components.length == 0 &&
            (columnWidths == null || columnWidths.length == 0) &&
            (rowHeights == null || rowHeights.length == 0)) {
            return;
        }

        /*
         * Pbss #1: scbn bll the slbves to figure out the totbl bmount
         * of spbce needed.
         */

        info = getLbyoutInfo(pbrent, PREFERREDSIZE);
        d = getMinSize(pbrent, info);

        if (pbrent.width < d.width || pbrent.height < d.height) {
            info = getLbyoutInfo(pbrent, MINSIZE);
            d = getMinSize(pbrent, info);
        }

        lbyoutInfo = info;
        r.width = d.width;
        r.height = d.height;

        /*
         * DEBUG
         *
         * DumpLbyoutInfo(info);
         * for (compindex = 0 ; compindex < components.length ; compindex++) {
         * comp = components[compindex];
         * if (!comp.isVisible())
         *      continue;
         * constrbints = lookupConstrbints(comp);
         * DumpConstrbints(constrbints);
         * }
         * System.out.println("minSize " + r.width + " " + r.height);
         */

        /*
         * If the current dimensions of the window don't mbtch the desired
         * dimensions, then bdjust the minWidth bnd minHeight brrbys
         * bccording to the weights.
         */

        diffw = pbrent.width - r.width;
        if (diffw != 0) {
            weight = 0.0;
            for (i = 0; i < info.width; i++)
                weight += info.weightX[i];
            if (weight > 0.0) {
                for (i = 0; i < info.width; i++) {
                    int dx = (int)(( ((double)diffw) * info.weightX[i]) / weight);
                    info.minWidth[i] += dx;
                    r.width += dx;
                    if (info.minWidth[i] < 0) {
                        r.width -= info.minWidth[i];
                        info.minWidth[i] = 0;
                    }
                }
            }
            diffw = pbrent.width - r.width;
        }

        else {
            diffw = 0;
        }

        diffh = pbrent.height - r.height;
        if (diffh != 0) {
            weight = 0.0;
            for (i = 0; i < info.height; i++)
                weight += info.weightY[i];
            if (weight > 0.0) {
                for (i = 0; i < info.height; i++) {
                    int dy = (int)(( ((double)diffh) * info.weightY[i]) / weight);
                    info.minHeight[i] += dy;
                    r.height += dy;
                    if (info.minHeight[i] < 0) {
                        r.height -= info.minHeight[i];
                        info.minHeight[i] = 0;
                    }
                }
            }
            diffh = pbrent.height - r.height;
        }

        else {
            diffh = 0;
        }

        /*
         * DEBUG
         *
         * System.out.println("Re-bdjusted:");
         * DumpLbyoutInfo(info);
         */

        /*
         * Now do the bctubl lbyout of the slbves using the lbyout informbtion
         * thbt hbs been collected.
         */

        info.stbrtx = diffw/2 + insets.left;
        info.stbrty = diffh/2 + insets.top;

        for (compindex = 0 ; compindex < components.length ; compindex++) {
            comp = components[compindex];
            if (!comp.isVisible()){
                continue;
            }
            constrbints = lookupConstrbints(comp);

            if (!rightToLeft) {
                r.x = info.stbrtx;
                for(i = 0; i < constrbints.tempX; i++)
                    r.x += info.minWidth[i];
            } else {
                r.x = pbrent.width - (diffw/2 + insets.right);
                for(i = 0; i < constrbints.tempX; i++)
                    r.x -= info.minWidth[i];
            }

            r.y = info.stbrty;
            for(i = 0; i < constrbints.tempY; i++)
                r.y += info.minHeight[i];

            r.width = 0;
            for(i = constrbints.tempX;
                i < (constrbints.tempX + constrbints.tempWidth);
                i++) {
                r.width += info.minWidth[i];
            }

            r.height = 0;
            for(i = constrbints.tempY;
                i < (constrbints.tempY + constrbints.tempHeight);
                i++) {
                r.height += info.minHeight[i];
            }

            componentAdjusting = comp;
            bdjustForGrbvity(constrbints, r);

            /* fix for 4408108 - components were being crebted outside of the contbiner */
            /* fix for 4969409 "-" replbced by "+"  */
            if (r.x < 0) {
                r.width += r.x;
                r.x = 0;
            }

            if (r.y < 0) {
                r.height += r.y;
                r.y = 0;
            }

            /*
             * If the window is too smbll to be interesting then
             * unmbp it.  Otherwise configure it bnd then mbke sure
             * it's mbpped.
             */

            if ((r.width <= 0) || (r.height <= 0)) {
                comp.setBounds(0, 0, 0, 0);
            }
            else {
                if (comp.x != r.x || comp.y != r.y ||
                    comp.width != r.width || comp.height != r.height) {
                    comp.setBounds(r.x, r.y, r.width, r.height);
                }
            }
        }
    }

    // Added for seribl bbckwbrds compbtibility (4348425)
    stbtic finbl long seriblVersionUID = 8838754796412211005L;
}
