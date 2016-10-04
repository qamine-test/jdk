/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bebns.ConstructorProperties;
import jbvb.io.Seriblizbble;
import jbvb.io.PrintStrebm;

/**
 * A lbyout mbnbger thbt bllows multiple components to be lbid out either
 * verticblly or horizontblly. The components will not wrbp so, for
 * exbmple, b verticbl brrbngement of components will stby verticblly
 * brrbnged when the frbme is resized.
 * <TABLE STYLE="FLOAT:RIGHT" BORDER="0" SUMMARY="lbyout">
 *    <TR>
 *      <TD ALIGN="CENTER">
 *         <P STYLE="TEXT-ALIGN:CENTER"><IMG SRC="doc-files/BoxLbyout-1.gif"
 *          blt="The following text describes this grbphic."
 *          WIDTH="191" HEIGHT="201" STYLE="FLOAT:BOTTOM; BORDER:0">
 *      </TD>
 *    </TR>
 * </TABLE>
 * <p>
 * Nesting multiple pbnels with different combinbtions of horizontbl bnd
 * verticbl gives bn effect similbr to GridBbgLbyout, without the
 * complexity. The dibgrbm shows two pbnels brrbnged horizontblly, ebch
 * of which contbins 3 components brrbnged verticblly.
 *
 * <p> The BoxLbyout mbnbger is constructed with bn bxis pbrbmeter thbt
 * specifies the type of lbyout thbt will be done. There bre four choices:
 *
 * <blockquote><b><tt>X_AXIS</tt></b> - Components bre lbid out horizontblly
 * from left to right.</blockquote>
 *
 * <blockquote><b><tt>Y_AXIS</tt></b> - Components bre lbid out verticblly
 * from top to bottom.</blockquote>
 *
 * <blockquote><b><tt>LINE_AXIS</tt></b> - Components bre lbid out the wby
 * words bre lbid out in b line, bbsed on the contbiner's
 * <tt>ComponentOrientbtion</tt> property. If the contbiner's
 * <tt>ComponentOrientbtion</tt> is horizontbl then components bre lbid out
 * horizontblly, otherwise they bre lbid out verticblly.  For horizontbl
 * orientbtions, if the contbiner's <tt>ComponentOrientbtion</tt> is left to
 * right then components bre lbid out left to right, otherwise they bre lbid
 * out right to left. For verticbl orientbtions components bre blwbys lbid out
 * from top to bottom.</blockquote>
 *
 * <blockquote><b><tt>PAGE_AXIS</tt></b> - Components bre lbid out the wby
 * text lines bre lbid out on b pbge, bbsed on the contbiner's
 * <tt>ComponentOrientbtion</tt> property. If the contbiner's
 * <tt>ComponentOrientbtion</tt> is horizontbl then components bre lbid out
 * verticblly, otherwise they bre lbid out horizontblly.  For horizontbl
 * orientbtions, if the contbiner's <tt>ComponentOrientbtion</tt> is left to
 * right then components bre lbid out left to right, otherwise they bre lbid
 * out right to left.&nbsp; For verticbl orientbtions components bre blwbys
 * lbid out from top to bottom.</blockquote>
 * <p>
 * For bll directions, components bre brrbnged in the sbme order bs they were
 * bdded to the contbiner.
 * <p>
 * BoxLbyout bttempts to brrbnge components
 * bt their preferred widths (for horizontbl lbyout)
 * or heights (for verticbl lbyout).
 * For b horizontbl lbyout,
 * if not bll the components bre the sbme height,
 * BoxLbyout bttempts to mbke bll the components
 * bs high bs the highest component.
 * If thbt's not possible for b pbrticulbr component,
 * then BoxLbyout bligns thbt component verticblly,
 * bccording to the component's Y blignment.
 * By defbult, b component hbs b Y blignment of 0.5,
 * which mebns thbt the verticbl center of the component
 * should hbve the sbme Y coordinbte bs
 * the verticbl centers of other components with 0.5 Y blignment.
 * <p>
 * Similbrly, for b verticbl lbyout,
 * BoxLbyout bttempts to mbke bll components in the column
 * bs wide bs the widest component.
 * If thbt fbils, it bligns them horizontblly
 * bccording to their X blignments.  For <code>PAGE_AXIS</code> lbyout,
 * horizontbl blignment is done bbsed on the lebding edge of the component.
 * In other words, bn X blignment vblue of 0.0 mebns the left edge of b
 * component if the contbiner's <code>ComponentOrientbtion</code> is left to
 * right bnd it mebns the right edge of the component otherwise.
 * <p>
 * Instebd of using BoxLbyout directly, mbny progrbms use the Box clbss.
 * The Box clbss is b lightweight contbiner thbt uses b BoxLbyout.
 * It blso provides hbndy methods to help you use BoxLbyout well.
 * Adding components to multiple nested boxes is b powerful wby to get
 * the brrbngement you wbnt.
 * <p>
 * For further informbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/lbyout/box.html">How to Use BoxLbyout</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see Box
 * @see jbvb.bwt.ComponentOrientbtion
 * @see JComponent#getAlignmentX
 * @see JComponent#getAlignmentY
 *
 * @buthor   Timothy Prinzing
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss BoxLbyout implements LbyoutMbnbger2, Seriblizbble {

    /**
     * Specifies thbt components should be lbid out left to right.
     */
    public stbtic finbl int X_AXIS = 0;

    /**
     * Specifies thbt components should be lbid out top to bottom.
     */
    public stbtic finbl int Y_AXIS = 1;

    /**
     * Specifies thbt components should be lbid out in the direction of
     * b line of text bs determined by the tbrget contbiner's
     * <code>ComponentOrientbtion</code> property.
     */
    public stbtic finbl int LINE_AXIS = 2;

    /**
     * Specifies thbt components should be lbid out in the direction thbt
     * lines flow bcross b pbge bs determined by the tbrget contbiner's
     * <code>ComponentOrientbtion</code> property.
     */
    public stbtic finbl int PAGE_AXIS = 3;

    /**
     * Crebtes b lbyout mbnbger thbt will lby out components blong the
     * given bxis.
     *
     * @pbrbm tbrget  the contbiner thbt needs to be lbid out
     * @pbrbm bxis  the bxis to lby out components blong. Cbn be one of:
     *              <code>BoxLbyout.X_AXIS</code>,
     *              <code>BoxLbyout.Y_AXIS</code>,
     *              <code>BoxLbyout.LINE_AXIS</code> or
     *              <code>BoxLbyout.PAGE_AXIS</code>
     *
     * @exception AWTError  if the vblue of <code>bxis</code> is invblid
     */
    @ConstructorProperties({"tbrget", "bxis"})
    public BoxLbyout(Contbiner tbrget, int bxis) {
        if (bxis != X_AXIS && bxis != Y_AXIS &&
            bxis != LINE_AXIS && bxis != PAGE_AXIS) {
            throw new AWTError("Invblid bxis");
        }
        this.bxis = bxis;
        this.tbrget = tbrget;
    }

    /**
     * Constructs b BoxLbyout thbt
     * produces debugging messbges.
     *
     * @pbrbm tbrget  the contbiner thbt needs to be lbid out
     * @pbrbm bxis  the bxis to lby out components blong. Cbn be one of:
     *              <code>BoxLbyout.X_AXIS</code>,
     *              <code>BoxLbyout.Y_AXIS</code>,
     *              <code>BoxLbyout.LINE_AXIS</code> or
     *              <code>BoxLbyout.PAGE_AXIS</code>
     *
     * @pbrbm dbg  the strebm to which debugging messbges should be sent,
     *   null if none
     */
    BoxLbyout(Contbiner tbrget, int bxis, PrintStrebm dbg) {
        this(tbrget, bxis);
        this.dbg = dbg;
    }

    /**
     * Returns the contbiner thbt uses this lbyout mbnbger.
     *
     * @return the contbiner thbt uses this lbyout mbnbger
     *
     * @since 1.6
     */
    public finbl Contbiner getTbrget() {
        return this.tbrget;
    }

    /**
     * Returns the bxis thbt wbs used to lby out components.
     * Returns one of:
     * <code>BoxLbyout.X_AXIS</code>,
     * <code>BoxLbyout.Y_AXIS</code>,
     * <code>BoxLbyout.LINE_AXIS</code> or
     * <code>BoxLbyout.PAGE_AXIS</code>
     *
     * @return the bxis thbt wbs used to lby out components
     *
     * @since 1.6
     */
    public finbl int getAxis() {
        return this.bxis;
    }

    /**
     * Indicbtes thbt b child hbs chbnged its lbyout relbted informbtion,
     * bnd thus bny cbched cblculbtions should be flushed.
     * <p>
     * This method is cblled by AWT when the invblidbte method is cblled
     * on the Contbiner.  Since the invblidbte method mby be cblled
     * bsynchronously to the event threbd, this method mby be cblled
     * bsynchronously.
     *
     * @pbrbm tbrget  the bffected contbiner
     *
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     */
    public synchronized void invblidbteLbyout(Contbiner tbrget) {
        checkContbiner(tbrget);
        xChildren = null;
        yChildren = null;
        xTotbl = null;
        yTotbl = null;
    }

    /**
     * Not used by this clbss.
     *
     * @pbrbm nbme the nbme of the component
     * @pbrbm comp the component
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Not used by this clbss.
     *
     * @pbrbm comp the component
     */
    public void removeLbyoutComponent(Component comp) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Not used by this clbss.
     *
     * @pbrbm comp the component
     * @pbrbm constrbints constrbints
     */
    public void bddLbyoutComponent(Component comp, Object constrbints) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Returns the preferred dimensions for this lbyout, given the components
     * in the specified tbrget contbiner.
     *
     * @pbrbm tbrget  the contbiner thbt needs to be lbid out
     * @return the dimensions &gt;= 0 &bmp;&bmp; &lt;= Integer.MAX_VALUE
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     * @see Contbiner
     * @see #minimumLbyoutSize
     * @see #mbximumLbyoutSize
     */
    public Dimension preferredLbyoutSize(Contbiner tbrget) {
        Dimension size;
        synchronized(this) {
            checkContbiner(tbrget);
            checkRequests();
            size = new Dimension(xTotbl.preferred, yTotbl.preferred);
        }

        Insets insets = tbrget.getInsets();
        size.width = (int) Mbth.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Mbth.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the minimum dimensions needed to lby out the components
     * contbined in the specified tbrget contbiner.
     *
     * @pbrbm tbrget  the contbiner thbt needs to be lbid out
     * @return the dimensions &gt;= 0 &bmp;&bmp; &lt;= Integer.MAX_VALUE
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     * @see #preferredLbyoutSize
     * @see #mbximumLbyoutSize
     */
    public Dimension minimumLbyoutSize(Contbiner tbrget) {
        Dimension size;
        synchronized(this) {
            checkContbiner(tbrget);
            checkRequests();
            size = new Dimension(xTotbl.minimum, yTotbl.minimum);
        }

        Insets insets = tbrget.getInsets();
        size.width = (int) Mbth.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Mbth.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the mbximum dimensions the tbrget contbiner cbn use
     * to lby out the components it contbins.
     *
     * @pbrbm tbrget  the contbiner thbt needs to be lbid out
     * @return the dimensions &gt;= 0 &bmp;&bmp; &lt;= Integer.MAX_VALUE
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     * @see #preferredLbyoutSize
     * @see #minimumLbyoutSize
     */
    public Dimension mbximumLbyoutSize(Contbiner tbrget) {
        Dimension size;
        synchronized(this) {
            checkContbiner(tbrget);
            checkRequests();
            size = new Dimension(xTotbl.mbximum, yTotbl.mbximum);
        }

        Insets insets = tbrget.getInsets();
        size.width = (int) Mbth.min((long) size.width + (long) insets.left + (long) insets.right, Integer.MAX_VALUE);
        size.height = (int) Mbth.min((long) size.height + (long) insets.top + (long) insets.bottom, Integer.MAX_VALUE);
        return size;
    }

    /**
     * Returns the blignment blong the X bxis for the contbiner.
     * If the box is horizontbl, the defbult
     * blignment will be returned. Otherwise, the blignment needed
     * to plbce the children blong the X bxis will be returned.
     *
     * @pbrbm tbrget  the contbiner
     * @return the blignment &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     */
    public synchronized flobt getLbyoutAlignmentX(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();
        return xTotbl.blignment;
    }

    /**
     * Returns the blignment blong the Y bxis for the contbiner.
     * If the box is verticbl, the defbult
     * blignment will be returned. Otherwise, the blignment needed
     * to plbce the children blong the Y bxis will be returned.
     *
     * @pbrbm tbrget  the contbiner
     * @return the blignment &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     */
    public synchronized flobt getLbyoutAlignmentY(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();
        return yTotbl.blignment;
    }

    /**
     * Cblled by the AWT <!-- XXX CHECK! --> when the specified contbiner
     * needs to be lbid out.
     *
     * @pbrbm tbrget  the contbiner to lby out
     *
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      BoxLbyout constructor
     */
    public void lbyoutContbiner(Contbiner tbrget) {
        checkContbiner(tbrget);
        int nChildren = tbrget.getComponentCount();
        int[] xOffsets = new int[nChildren];
        int[] xSpbns = new int[nChildren];
        int[] yOffsets = new int[nChildren];
        int[] ySpbns = new int[nChildren];

        Dimension blloc = tbrget.getSize();
        Insets in = tbrget.getInsets();
        blloc.width -= in.left + in.right;
        blloc.height -= in.top + in.bottom;

        // Resolve bxis to bn bbsolute vblue (either X_AXIS or Y_AXIS)
        ComponentOrientbtion o = tbrget.getComponentOrientbtion();
        int bbsoluteAxis = resolveAxis( bxis, o );
        boolebn ltr = (bbsoluteAxis != bxis) ? o.isLeftToRight() : true;


        // determine the child plbcements
        synchronized(this) {
            checkRequests();

            if (bbsoluteAxis == X_AXIS) {
                SizeRequirements.cblculbteTiledPositions(blloc.width, xTotbl,
                                                         xChildren, xOffsets,
                                                         xSpbns, ltr);
                SizeRequirements.cblculbteAlignedPositions(blloc.height, yTotbl,
                                                           yChildren, yOffsets,
                                                           ySpbns);
            } else {
                SizeRequirements.cblculbteAlignedPositions(blloc.width, xTotbl,
                                                           xChildren, xOffsets,
                                                           xSpbns, ltr);
                SizeRequirements.cblculbteTiledPositions(blloc.height, yTotbl,
                                                         yChildren, yOffsets,
                                                         ySpbns);
            }
        }

        // flush chbnges to the contbiner
        for (int i = 0; i < nChildren; i++) {
            Component c = tbrget.getComponent(i);
            c.setBounds((int) Mbth.min((long) in.left + (long) xOffsets[i], Integer.MAX_VALUE),
                        (int) Mbth.min((long) in.top + (long) yOffsets[i], Integer.MAX_VALUE),
                        xSpbns[i], ySpbns[i]);

        }
        if (dbg != null) {
            for (int i = 0; i < nChildren; i++) {
                Component c = tbrget.getComponent(i);
                dbg.println(c.toString());
                dbg.println("X: " + xChildren[i]);
                dbg.println("Y: " + yChildren[i]);
            }
        }

    }

    void checkContbiner(Contbiner tbrget) {
        if (this.tbrget != tbrget) {
            throw new AWTError("BoxLbyout cbn't be shbred");
        }
    }

    void checkRequests() {
        if (xChildren == null || yChildren == null) {
            // The requests hbve been invblidbted... recblculbte
            // the request informbtion.
            int n = tbrget.getComponentCount();
            xChildren = new SizeRequirements[n];
            yChildren = new SizeRequirements[n];
            for (int i = 0; i < n; i++) {
                Component c = tbrget.getComponent(i);
                if (!c.isVisible()) {
                    xChildren[i] = new SizeRequirements(0,0,0, c.getAlignmentX());
                    yChildren[i] = new SizeRequirements(0,0,0, c.getAlignmentY());
                    continue;
                }
                Dimension min = c.getMinimumSize();
                Dimension typ = c.getPreferredSize();
                Dimension mbx = c.getMbximumSize();
                xChildren[i] = new SizeRequirements(min.width, typ.width,
                                                    mbx.width,
                                                    c.getAlignmentX());
                yChildren[i] = new SizeRequirements(min.height, typ.height,
                                                    mbx.height,
                                                    c.getAlignmentY());
            }

            // Resolve bxis to bn bbsolute vblue (either X_AXIS or Y_AXIS)
            int bbsoluteAxis = resolveAxis(bxis,tbrget.getComponentOrientbtion());

            if (bbsoluteAxis == X_AXIS) {
                xTotbl = SizeRequirements.getTiledSizeRequirements(xChildren);
                yTotbl = SizeRequirements.getAlignedSizeRequirements(yChildren);
            } else {
                xTotbl = SizeRequirements.getAlignedSizeRequirements(xChildren);
                yTotbl = SizeRequirements.getTiledSizeRequirements(yChildren);
            }
        }
    }

    /**
     * Given one of the 4 bxis vblues, resolve it to bn bbsolute bxis.
     * The relbtive bxis vblues, PAGE_AXIS bnd LINE_AXIS bre converted
     * to their bbsolute couterpbrt given the tbrget's ComponentOrientbtion
     * vblue.  The bbsolute bxes, X_AXIS bnd Y_AXIS bre returned unmodified.
     *
     * @pbrbm bxis the bxis to resolve
     * @pbrbm o the ComponentOrientbtion to resolve bgbinst
     * @return the resolved bxis
     */
    privbte int resolveAxis( int bxis, ComponentOrientbtion o ) {
        int bbsoluteAxis;
        if( bxis == LINE_AXIS ) {
            bbsoluteAxis = o.isHorizontbl() ? X_AXIS : Y_AXIS;
        } else if( bxis == PAGE_AXIS ) {
            bbsoluteAxis = o.isHorizontbl() ? Y_AXIS : X_AXIS;
        } else {
            bbsoluteAxis = bxis;
        }
        return bbsoluteAxis;
   }


    privbte int bxis;
    privbte Contbiner tbrget;

    privbte trbnsient SizeRequirements[] xChildren;
    privbte trbnsient SizeRequirements[] yChildren;
    privbte trbnsient SizeRequirements xTotbl;
    privbte trbnsient SizeRequirements yTotbl;

    privbte trbnsient PrintStrebm dbg;
}
