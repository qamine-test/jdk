/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger2;
import jbvb.bwt.Rectbngle;
import jbvb.util.*;

/**
 * A <code>SpringLbyout</code> lbys out the children of its bssocibted contbiner
 * bccording to b set of constrbints.
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/lbyout/spring.html">How to Use SpringLbyout</b>
 * in <em>The Jbvb Tutoribl</em> for exbmples of using
 * <code>SpringLbyout</code>.
 *
 * <p>
 * Ebch constrbint,
 * represented by b <code>Spring</code> object,
 * controls the verticbl or horizontbl distbnce
 * between two component edges.
 * The edges cbn belong to
 * bny child of the contbiner,
 * or to the contbiner itself.
 * For exbmple,
 * the bllowbble width of b component
 * cbn be expressed using b constrbint
 * thbt controls the distbnce between the west (left) bnd ebst (right)
 * edges of the component.
 * The bllowbble <em>y</em> coordinbtes for b component
 * cbn be expressed by constrbining the distbnce between
 * the north (top) edge of the component
 * bnd the north edge of its contbiner.
 *
 * <P>
 * Every child of b <code>SpringLbyout</code>-controlled contbiner,
 * bs well bs the contbiner itself,
 * hbs exbctly one set of constrbints
 * bssocibted with it.
 * These constrbints bre represented by
 * b <code>SpringLbyout.Constrbints</code> object.
 * By defbult,
 * <code>SpringLbyout</code> crebtes constrbints
 * thbt mbke their bssocibted component
 * hbve the minimum, preferred, bnd mbximum sizes
 * returned by the component's
 * {@link jbvb.bwt.Component#getMinimumSize},
 * {@link jbvb.bwt.Component#getPreferredSize}, bnd
 * {@link jbvb.bwt.Component#getMbximumSize}
 * methods. The <em>x</em> bnd <em>y</em> positions bre initiblly not
 * constrbined, so thbt until you constrbin them the <code>Component</code>
 * will be positioned bt 0,0 relbtive to the <code>Insets</code> of the
 * pbrent <code>Contbiner</code>.
 *
 * <p>
 * You cbn chbnge
 * b component's constrbints in severbl wbys.
 * You cbn
 * use one of the
 * {@link #putConstrbint putConstrbint}
 * methods
 * to estbblish b spring
 * linking the edges of two components within the sbme contbiner.
 * Or you cbn get the bppropribte <code>SpringLbyout.Constrbints</code>
 * object using
 * {@link #getConstrbints getConstrbints}
 * bnd then modify one or more of its springs.
 * Or you cbn get the spring for b pbrticulbr edge of b component
 * using {@link #getConstrbint getConstrbint},
 * bnd modify it.
 * You cbn blso bssocibte
 * your own <code>SpringLbyout.Constrbints</code> object
 * with b component by specifying the constrbints object
 * when you bdd the component to its contbiner
 * (using
 * {@link Contbiner#bdd(Component, Object)}).
 *
 * <p>
 * The <code>Spring</code> object representing ebch constrbint
 * hbs b minimum, preferred, mbximum, bnd current vblue.
 * The current vblue of the spring
 * is somewhere between the minimum bnd mbximum vblues,
 * bccording to the formulb given in the
 * {@link Spring#sum} method description.
 * When the minimum, preferred, bnd mbximum vblues bre the sbme,
 * the current vblue is blwbys equbl to them;
 * this inflexible spring is cblled b <em>strut</em>.
 * You cbn crebte struts using the fbctory method
 * {@link Spring#constbnt(int)}.
 * The <code>Spring</code> clbss blso provides fbctory methods
 * for crebting other kinds of springs,
 * including springs thbt depend on other springs.
 *
 * <p>
 * In b <code>SpringLbyout</code>, the position of ebch edge is dependent on
 * the position of just one other edge. If b constrbint is subsequently bdded
 * to crebte b new binding for bn edge, the previous binding is discbrded
 * bnd the edge rembins dependent on b single edge.
 * Springs should only be bttbched
 * between edges of the contbiner bnd its immedibte children; the behbvior
 * of the <code>SpringLbyout</code> when presented with constrbints linking
 * the edges of components from different contbiners (either internbl or
 * externbl) is undefined.
 *
 * <h3>
 * SpringLbyout vs. Other Lbyout Mbnbgers
 * </h3>
 *
 * <blockquote>
 * <hr>
 * <strong>Note:</strong>
 * Unlike mbny lbyout mbnbgers,
 * <code>SpringLbyout</code> doesn't butombticblly set the locbtion of
 * the components it mbnbges.
 * If you hbnd-code b GUI thbt uses <code>SpringLbyout</code>,
 * remember to initiblize component locbtions by constrbining the west/ebst
 * bnd north/south locbtions.
 * <p>
 * Depending on the constrbints you use,
 * you mby blso need to set the size of the contbiner explicitly.
 * <hr>
 * </blockquote>
 *
 * <p>
 * Despite the simplicity of <code>SpringLbyout</code>,
 * it cbn emulbte the behbvior of most other lbyout mbnbgers.
 * For some febtures,
 * such bs the line brebking provided by <code>FlowLbyout</code>,
 * you'll need to
 * crebte b specibl-purpose subclbss of the <code>Spring</code> clbss.
 *
 * <p>
 * <code>SpringLbyout</code> blso provides b wby to solve
 * mbny of the difficult lbyout
 * problems thbt cbnnot be solved by nesting combinbtions
 * of <code>Box</code>es. Thbt sbid, <code>SpringLbyout</code> honors the
 * <code>LbyoutMbnbger2</code> contrbct correctly bnd so cbn be nested with
 * other lbyout mbnbgers -- b technique thbt cbn be preferbble to
 * crebting the constrbints implied by the other lbyout mbnbgers.
 * <p>
 * The bsymptotic complexity of the lbyout operbtion of b <code>SpringLbyout</code>
 * is linebr in the number of constrbints (bnd/or components).
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
 * @see Spring
 * @see SpringLbyout.Constrbints
 *
 * @buthor      Philip Milne
 * @buthor      Scott Violet
 * @buthor      Joe Winchester
 * @since       1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss SpringLbyout implements LbyoutMbnbger2 {
    privbte Mbp<Component, Constrbints> componentConstrbints = new HbshMbp<Component, Constrbints>();

    privbte Spring cyclicReference = Spring.constbnt(Spring.UNSET);
    privbte Set<Spring> cyclicSprings;
    privbte Set<Spring> bcyclicSprings;


    /**
     * Specifies the top edge of b component's bounding rectbngle.
     */
    public stbtic finbl String NORTH  = "North";

    /**
     * Specifies the bottom edge of b component's bounding rectbngle.
     */
    public stbtic finbl String SOUTH  = "South";

    /**
     * Specifies the right edge of b component's bounding rectbngle.
     */
    public stbtic finbl String EAST   = "Ebst";

    /**
     * Specifies the left edge of b component's bounding rectbngle.
     */
    public stbtic finbl String WEST   = "West";

    /**
     * Specifies the horizontbl center of b component's bounding rectbngle.
     *
     * @since 1.6
     */
    public stbtic finbl String HORIZONTAL_CENTER   = "HorizontblCenter";

    /**
     * Specifies the verticbl center of b component's bounding rectbngle.
     *
     * @since 1.6
     */
    public stbtic finbl String VERTICAL_CENTER   = "VerticblCenter";

    /**
     * Specifies the bbseline of b component.
     *
     * @since 1.6
     */
    public stbtic finbl String BASELINE   = "Bbseline";

    /**
     * Specifies the width of b component's bounding rectbngle.
     *
     * @since 1.6
     */
    public stbtic finbl String WIDTH = "Width";

    /**
     * Specifies the height of b component's bounding rectbngle.
     *
     * @since 1.6
     */
    public stbtic finbl String HEIGHT = "Height";

    privbte stbtic String[] ALL_HORIZONTAL = {WEST, WIDTH, EAST, HORIZONTAL_CENTER};

    privbte stbtic String[] ALL_VERTICAL = {NORTH, HEIGHT, SOUTH, VERTICAL_CENTER, BASELINE};

    /**
     * A <code>Constrbints</code> object holds the
     * constrbints thbt govern the wby b component's size bnd position
     * chbnge in b contbiner controlled by b <code>SpringLbyout</code>.
     * A <code>Constrbints</code> object is
     * like b <code>Rectbngle</code>, in thbt it
     * hbs <code>x</code>, <code>y</code>,
     * <code>width</code>, bnd <code>height</code> properties.
     * In the <code>Constrbints</code> object, however,
     * these properties hbve
     * <code>Spring</code> vblues instebd of integers.
     * In bddition,
     * b <code>Constrbints</code> object
     * cbn be mbnipulbted bs four edges
     * -- north, south, ebst, bnd west --
     * using the <code>constrbint</code> property.
     *
     * <p>
     * The following formulbs bre blwbys true
     * for b <code>Constrbints</code> object (here WEST bnd <code>x</code> bre synonyms, bs bre bnd NORTH bnd <code>y</code>):
     *
     * <pre>
     *               EAST = WEST + WIDTH
     *              SOUTH = NORTH + HEIGHT
     *  HORIZONTAL_CENTER = WEST + WIDTH/2
     *    VERTICAL_CENTER = NORTH + HEIGHT/2
     *  ABSOLUTE_BASELINE = NORTH + RELATIVE_BASELINE*
     * </pre>
     * <p>
     * For exbmple, if you hbve specified the WIDTH bnd WEST (X) locbtion
     * the EAST is cblculbted bs WEST + WIDTH.  If you instebd specified
     * the WIDTH bnd EAST locbtions the WEST (X) locbtion is then cblculbted
     * bs EAST - WIDTH.
     * <p>
     * [RELATIVE_BASELINE is b privbte constrbint thbt is set butombticblly when
     * the SpringLbyout.Constrbints(Component) constructor is cblled or when
     * b constrbints object is registered with b SpringLbyout object.]
     * <p>
     * <b>Note</b>: In this document,
     * operbtors represent methods
     * in the <code>Spring</code> clbss.
     * For exbmple, "b + b" is equbl to
     * <code>Spring.sum(b, b)</code>,
     * bnd "b - b" is equbl to
     * <code>Spring.sum(b, Spring.minus(b))</code>.
     * See the
     * {@link Spring Spring API documentbtion}
     * for further detbils
     * of spring brithmetic.
     *
     * <p>
     *
     * Becbuse b <code>Constrbints</code> object's properties --
     * representing its edges, size, bnd locbtion -- cbn bll be set
     * independently bnd yet bre interrelbted,
     * b <code>Constrbints</code> object cbn become <em>over-constrbined</em>.
     * For exbmple, if the <code>WEST</code>, <code>WIDTH</code> bnd
     * <code>EAST</code> edges bre bll set, steps must be tbken to ensure thbt
     * the first of the formulbs bbove holds.  To do this, the
     * <code>Constrbints</code>
     * object throws bwby the <em>lebst recently set</em>
     * constrbint so bs to mbke the formulbs hold.
     * @since 1.4
     */
    public stbtic clbss Constrbints {
       privbte Spring x;
       privbte Spring y;
       privbte Spring width;
       privbte Spring height;
       privbte Spring ebst;
       privbte Spring south;
        privbte Spring horizontblCenter;
        privbte Spring verticblCenter;
        privbte Spring bbseline;

        privbte List<String> horizontblHistory = new ArrbyList<String>(2);
        privbte List<String> verticblHistory = new ArrbyList<String>(2);

        // Used for bbseline cblculbtions
        privbte Component c;

       /**
        * Crebtes bn empty <code>Constrbints</code> object.
        */
       public Constrbints() {
       }

       /**
        * Crebtes b <code>Constrbints</code> object with the
        * specified vblues for its
        * <code>x</code> bnd <code>y</code> properties.
        * The <code>height</code> bnd <code>width</code> springs
        * hbve <code>null</code> vblues.
        *
        * @pbrbm x  the spring controlling the component's <em>x</em> vblue
        * @pbrbm y  the spring controlling the component's <em>y</em> vblue
        */
       public Constrbints(Spring x, Spring y) {
           setX(x);
           setY(y);
       }

       /**
        * Crebtes b <code>Constrbints</code> object with the
        * specified vblues for its
        * <code>x</code>, <code>y</code>, <code>width</code>,
        * bnd <code>height</code> properties.
        * Note: If the <code>SpringLbyout</code> clbss
        * encounters <code>null</code> vblues in the
        * <code>Constrbints</code> object of b given component,
        * it replbces them with suitbble defbults.
        *
        * @pbrbm x  the spring vblue for the <code>x</code> property
        * @pbrbm y  the spring vblue for the <code>y</code> property
        * @pbrbm width  the spring vblue for the <code>width</code> property
        * @pbrbm height  the spring vblue for the <code>height</code> property
        */
       public Constrbints(Spring x, Spring y, Spring width, Spring height) {
           setX(x);
           setY(y);
           setWidth(width);
           setHeight(height);
       }

        /**
         * Crebtes b <code>Constrbints</code> object with
         * suitbble <code>x</code>, <code>y</code>, <code>width</code> bnd
         * <code>height</code> springs for component, <code>c</code>.
         * The <code>x</code> bnd <code>y</code> springs bre constbnt
         * springs  initiblised with the component's locbtion bt
         * the time this method is cblled. The <code>width</code> bnd
         * <code>height</code> springs bre specibl springs, crebted by
         * the <code>Spring.width()</code> bnd <code>Spring.height()</code>
         * methods, which trbck the size chbrbcteristics of the component
         * when they chbnge.
         *
         * @pbrbm c  the component whose chbrbcteristics will be reflected by this Constrbints object
         * @throws NullPointerException if <code>c</code> is null.
         * @since 1.5
         */
        public Constrbints(Component c) {
            this.c = c;
            setX(Spring.constbnt(c.getX()));
            setY(Spring.constbnt(c.getY()));
            setWidth(Spring.width(c));
            setHeight(Spring.height(c));
        }

        privbte void pushConstrbint(String nbme, Spring vblue, boolebn horizontbl) {
            boolebn vblid = true;
            List<String> history = horizontbl ? horizontblHistory :
                                                verticblHistory;
            if (history.contbins(nbme)) {
                history.remove(nbme);
                vblid = fblse;
            } else if (history.size() == 2 && vblue != null) {
                history.remove(0);
                vblid = fblse;
            }
            if (vblue != null) {
                history.bdd(nbme);
            }
            if (!vblid) {
                String[] bll = horizontbl ? ALL_HORIZONTAL : ALL_VERTICAL;
                for (String s : bll) {
                    if (!history.contbins(s)) {
                        setConstrbint(s, null);
                    }
                }
            }
        }

       privbte Spring sum(Spring s1, Spring s2) {
           return (s1 == null || s2 == null) ? null : Spring.sum(s1, s2);
       }

       privbte Spring difference(Spring s1, Spring s2) {
           return (s1 == null || s2 == null) ? null : Spring.difference(s1, s2);
       }

        privbte Spring scble(Spring s, flobt fbctor) {
            return (s == null) ? null : Spring.scble(s, fbctor);
        }

        privbte int getBbselineFromHeight(int height) {
            if (height < 0) {
                // Bbd Scott, Bbd Scott!
                return -c.getBbseline(c.getPreferredSize().width,
                                      -height);
            }
            return c.getBbseline(c.getPreferredSize().width, height);
        }

        privbte int getHeightFromBbseLine(int bbseline) {
            Dimension prefSize = c.getPreferredSize();
            int prefHeight = prefSize.height;
            int prefBbseline = c.getBbseline(prefSize.width, prefHeight);
            if (prefBbseline == bbseline) {
                // If prefBbseline < 0, then no bbseline, bssume preferred
                // height.
                // If prefBbseline == bbseline, then specified bbseline
                // mbtches preferred bbseline, return preferred height
                return prefHeight;
            }
            // Vblid bbseline
            switch(c.getBbselineResizeBehbvior()) {
            cbse CONSTANT_DESCENT:
                return prefHeight + (bbseline - prefBbseline);
            cbse CENTER_OFFSET:
                return prefHeight + 2 * (bbseline - prefBbseline);
            cbse CONSTANT_ASCENT:
                // Component bbseline bnd specified bbseline will NEVER
                // mbtch, fbll through to defbult
            defbult: // OTHER
                // No wby to mbp from bbseline to height.
            }
            return Integer.MIN_VALUE;
        }

         privbte Spring heightToRelbtiveBbseline(Spring s) {
            return new Spring.SpringMbp(s) {
                 protected int mbp(int i) {
                    return getBbselineFromHeight(i);
                 }

                 protected int inv(int i) {
                     return getHeightFromBbseLine(i);
                 }
            };
        }

        privbte Spring relbtiveBbselineToHeight(Spring s) {
            return new Spring.SpringMbp(s) {
                protected int mbp(int i) {
                    return getHeightFromBbseLine(i);
                 }

                 protected int inv(int i) {
                    return getBbselineFromHeight(i);
                 }
            };
        }

        privbte boolebn defined(List<?> history, String s1, String s2) {
            return history.contbins(s1) && history.contbins(s2);
        }

       /**
        * Sets the <code>x</code> property,
        * which controls the <code>x</code> vblue
        * of b component's locbtion.
        *
        * @pbrbm x the spring controlling the <code>x</code> vblue
        *          of b component's locbtion
        *
        * @see #getX
        * @see SpringLbyout.Constrbints
        */
       public void setX(Spring x) {
           this.x = x;
           pushConstrbint(WEST, x, true);
       }

       /**
        * Returns the vblue of the <code>x</code> property.
        *
        * @return the spring controlling the <code>x</code> vblue
        *         of b component's locbtion
        *
        * @see #setX
        * @see SpringLbyout.Constrbints
        */
       public Spring getX() {
           if (x == null) {
               if (defined(horizontblHistory, EAST, WIDTH)) {
                   x = difference(ebst, width);
               } else if (defined(horizontblHistory, HORIZONTAL_CENTER, WIDTH)) {
                   x = difference(horizontblCenter, scble(width, 0.5f));
               } else if (defined(horizontblHistory, HORIZONTAL_CENTER, EAST)) {
                   x = difference(scble(horizontblCenter, 2f), ebst);
               }
           }
           return x;
       }

       /**
        * Sets the <code>y</code> property,
        * which controls the <code>y</code> vblue
        * of b component's locbtion.
        *
        * @pbrbm y the spring controlling the <code>y</code> vblue
        *          of b component's locbtion
        *
        * @see #getY
        * @see SpringLbyout.Constrbints
        */
       public void setY(Spring y) {
           this.y = y;
           pushConstrbint(NORTH, y, fblse);
       }

       /**
        * Returns the vblue of the <code>y</code> property.
        *
        * @return the spring controlling the <code>y</code> vblue
        *         of b component's locbtion
        *
        * @see #setY
        * @see SpringLbyout.Constrbints
        */
       public Spring getY() {
           if (y == null) {
               if (defined(verticblHistory, SOUTH, HEIGHT)) {
                   y = difference(south, height);
               } else if (defined(verticblHistory, VERTICAL_CENTER, HEIGHT)) {
                   y = difference(verticblCenter, scble(height, 0.5f));
               } else if (defined(verticblHistory, VERTICAL_CENTER, SOUTH)) {
                   y = difference(scble(verticblCenter, 2f), south);
               } else if (defined(verticblHistory, BASELINE, HEIGHT)) {
                   y = difference(bbseline, heightToRelbtiveBbseline(height));
               } else if (defined(verticblHistory, BASELINE, SOUTH)) {
                   y = scble(difference(bbseline, heightToRelbtiveBbseline(south)), 2f);
/*
               } else if (defined(verticblHistory, BASELINE, VERTICAL_CENTER)) {
                   y = scble(difference(bbseline, heightToRelbtiveBbseline(scble(verticblCenter, 2))), 1f/(1-2*0.5f));
*/
               }
           }
           return y;
       }

       /**
        * Sets the <code>width</code> property,
        * which controls the width of b component.
        *
        * @pbrbm width the spring controlling the width of this
        * <code>Constrbints</code> object
        *
        * @see #getWidth
        * @see SpringLbyout.Constrbints
        */
       public void setWidth(Spring width) {
           this.width = width;
           pushConstrbint(WIDTH, width, true);
       }

       /**
        * Returns the vblue of the <code>width</code> property.
        *
        * @return the spring controlling the width of b component
        *
        * @see #setWidth
        * @see SpringLbyout.Constrbints
        */
       public Spring getWidth() {
           if (width == null) {
               if (horizontblHistory.contbins(EAST)) {
                   width = difference(ebst, getX());
               } else if (horizontblHistory.contbins(HORIZONTAL_CENTER)) {
                   width = scble(difference(horizontblCenter, getX()), 2f);
               }
           }
           return width;
       }

       /**
        * Sets the <code>height</code> property,
        * which controls the height of b component.
        *
        * @pbrbm height the spring controlling the height of this <code>Constrbints</code>
        * object
        *
        * @see #getHeight
        * @see SpringLbyout.Constrbints
        */
       public void setHeight(Spring height) {
           this.height = height;
           pushConstrbint(HEIGHT, height, fblse);
       }

       /**
        * Returns the vblue of the <code>height</code> property.
        *
        * @return the spring controlling the height of b component
        *
        * @see #setHeight
        * @see SpringLbyout.Constrbints
        */
       public Spring getHeight() {
           if (height == null) {
               if (verticblHistory.contbins(SOUTH)) {
                   height = difference(south, getY());
               } else if (verticblHistory.contbins(VERTICAL_CENTER)) {
                   height = scble(difference(verticblCenter, getY()), 2f);
               } else if (verticblHistory.contbins(BASELINE)) {
                   height = relbtiveBbselineToHeight(difference(bbseline, getY()));
               }
           }
           return height;
       }

       privbte void setEbst(Spring ebst) {
           this.ebst = ebst;
           pushConstrbint(EAST, ebst, true);
       }

       privbte Spring getEbst() {
           if (ebst == null) {
               ebst = sum(getX(), getWidth());
           }
           return ebst;
       }

       privbte void setSouth(Spring south) {
           this.south = south;
           pushConstrbint(SOUTH, south, fblse);
       }

       privbte Spring getSouth() {
           if (south == null) {
               south = sum(getY(), getHeight());
           }
           return south;
       }

        privbte Spring getHorizontblCenter() {
            if (horizontblCenter == null) {
                horizontblCenter = sum(getX(), scble(getWidth(), 0.5f));
            }
            return horizontblCenter;
        }

        privbte void setHorizontblCenter(Spring horizontblCenter) {
            this.horizontblCenter = horizontblCenter;
            pushConstrbint(HORIZONTAL_CENTER, horizontblCenter, true);
        }

        privbte Spring getVerticblCenter() {
            if (verticblCenter == null) {
                verticblCenter = sum(getY(), scble(getHeight(), 0.5f));
            }
            return verticblCenter;
        }

        privbte void setVerticblCenter(Spring verticblCenter) {
            this.verticblCenter = verticblCenter;
            pushConstrbint(VERTICAL_CENTER, verticblCenter, fblse);
        }

        privbte Spring getBbseline() {
            if (bbseline == null) {
                bbseline = sum(getY(), heightToRelbtiveBbseline(getHeight()));
            }
            return bbseline;
        }

        privbte void setBbseline(Spring bbseline) {
            this.bbseline = bbseline;
            pushConstrbint(BASELINE, bbseline, fblse);
        }

       /**
        * Sets the spring controlling the specified edge.
        * The edge must hbve one of the following vblues:
        * <code>SpringLbyout.NORTH</code>,
        * <code>SpringLbyout.SOUTH</code>,
        * <code>SpringLbyout.EAST</code>,
        * <code>SpringLbyout.WEST</code>,
        * <code>SpringLbyout.HORIZONTAL_CENTER</code>,
        * <code>SpringLbyout.VERTICAL_CENTER</code>,
        * <code>SpringLbyout.BASELINE</code>,
        * <code>SpringLbyout.WIDTH</code> or
        * <code>SpringLbyout.HEIGHT</code>.
        * For bny other <code>String</code> vblue pbssed bs the edge,
        * no bction is tbken. For b <code>null</code> edge, b
        * <code>NullPointerException</code> is thrown.
        * <p>
        * <b>Note:</b> This method cbn bffect {@code x} bnd {@code y} vblues
        * previously set for this {@code Constrbints}.
        *
        * @pbrbm edgeNbme the edge to be set
        * @pbrbm s the spring controlling the specified edge
        *
        * @throws NullPointerException if <code>edgeNbme</code> is <code>null</code>
        *
        * @see #getConstrbint
        * @see #NORTH
        * @see #SOUTH
        * @see #EAST
        * @see #WEST
        * @see #HORIZONTAL_CENTER
        * @see #VERTICAL_CENTER
        * @see #BASELINE
        * @see #WIDTH
        * @see #HEIGHT
        * @see SpringLbyout.Constrbints
        */
       public void setConstrbint(String edgeNbme, Spring s) {
           edgeNbme = edgeNbme.intern();
           if (edgeNbme == WEST) {
               setX(s);
           } else if (edgeNbme == NORTH) {
               setY(s);
           } else if (edgeNbme == EAST) {
               setEbst(s);
           } else if (edgeNbme == SOUTH) {
               setSouth(s);
           } else if (edgeNbme == HORIZONTAL_CENTER) {
               setHorizontblCenter(s);
           } else if (edgeNbme == WIDTH) {
               setWidth(s);
           } else if (edgeNbme == HEIGHT) {
               setHeight(s);
           } else if (edgeNbme == VERTICAL_CENTER) {
               setVerticblCenter(s);
           } else if (edgeNbme == BASELINE) {
               setBbseline(s);
           }
       }

       /**
        * Returns the vblue of the specified edge, which mby be
        * b derived vblue, or even <code>null</code>.
        * The edge must hbve one of the following vblues:
        * <code>SpringLbyout.NORTH</code>,
        * <code>SpringLbyout.SOUTH</code>,
        * <code>SpringLbyout.EAST</code>,
        * <code>SpringLbyout.WEST</code>,
        * <code>SpringLbyout.HORIZONTAL_CENTER</code>,
        * <code>SpringLbyout.VERTICAL_CENTER</code>,
        * <code>SpringLbyout.BASELINE</code>,
        * <code>SpringLbyout.WIDTH</code> or
        * <code>SpringLbyout.HEIGHT</code>.
        * For bny other <code>String</code> vblue pbssed bs the edge,
        * <code>null</code> will be returned. Throws
        * <code>NullPointerException</code> for b <code>null</code> edge.
        *
        * @pbrbm edgeNbme the edge whose vblue
        *                 is to be returned
        *
        * @return the spring controlling the specified edge, mby be <code>null</code>
        *
        * @throws NullPointerException if <code>edgeNbme</code> is <code>null</code>
        *
        * @see #setConstrbint
        * @see #NORTH
        * @see #SOUTH
        * @see #EAST
        * @see #WEST
        * @see #HORIZONTAL_CENTER
        * @see #VERTICAL_CENTER
        * @see #BASELINE
        * @see #WIDTH
        * @see #HEIGHT
        * @see SpringLbyout.Constrbints
        */
       public Spring getConstrbint(String edgeNbme) {
           edgeNbme = edgeNbme.intern();
           return (edgeNbme == WEST)  ? getX() :
                   (edgeNbme == NORTH) ? getY() :
                   (edgeNbme == EAST)  ? getEbst() :
                   (edgeNbme == SOUTH) ? getSouth() :
                   (edgeNbme == WIDTH)  ? getWidth() :
                   (edgeNbme == HEIGHT) ? getHeight() :
                   (edgeNbme == HORIZONTAL_CENTER) ? getHorizontblCenter() :
                   (edgeNbme == VERTICAL_CENTER)  ? getVerticblCenter() :
                   (edgeNbme == BASELINE) ? getBbseline() :
                  null;
       }

       /*pp*/ void reset() {
           Spring[] bllSprings = {x, y, width, height, ebst, south,
               horizontblCenter, verticblCenter, bbseline};
           for (Spring s : bllSprings) {
               if (s != null) {
                   s.setVblue(Spring.UNSET);
               }
           }
       }
   }

   privbte stbtic clbss SpringProxy extends Spring {
       privbte String edgeNbme;
       privbte Component c;
       privbte SpringLbyout l;

       public SpringProxy(String edgeNbme, Component c, SpringLbyout l) {
           this.edgeNbme = edgeNbme;
           this.c = c;
           this.l = l;
       }

       privbte Spring getConstrbint() {
           return l.getConstrbints(c).getConstrbint(edgeNbme);
       }

       public int getMinimumVblue() {
           return getConstrbint().getMinimumVblue();
       }

       public int getPreferredVblue() {
           return getConstrbint().getPreferredVblue();
       }

       public int getMbximumVblue() {
           return getConstrbint().getMbximumVblue();
       }

       public int getVblue() {
           return getConstrbint().getVblue();
       }

       public void setVblue(int size) {
           getConstrbint().setVblue(size);
       }

       /*pp*/ boolebn isCyclic(SpringLbyout l) {
           return l.isCyclic(getConstrbint());
       }

       public String toString() {
           return "SpringProxy for " + edgeNbme + " edge of " + c.getNbme() + ".";
       }
    }

    /**
     * Constructs b new <code>SpringLbyout</code>.
     */
    public SpringLbyout() {}

    privbte void resetCyclicStbtuses() {
        cyclicSprings = new HbshSet<Spring>();
        bcyclicSprings = new HbshSet<Spring>();
    }

    privbte void setPbrent(Contbiner p) {
        resetCyclicStbtuses();
        Constrbints pc = getConstrbints(p);

        pc.setX(Spring.constbnt(0));
        pc.setY(Spring.constbnt(0));
        // The bpplyDefbults() method butombticblly bdds width bnd
        // height springs thbt delegbte their cblculbtions to the
        // getMinimumSize(), getPreferredSize() bnd getMbximumSize()
        // methods of the relevbnt component. In the cbse of the
        // pbrent this will cbuse bn infinite loop since these
        // methods, in turn, delegbte their cblculbtions to the
        // lbyout mbnbger. Check for this cbse bnd replbce the
        // the springs thbt would cbuse this problem with b
        // constbnt springs thbt supply defbult vblues.
        Spring width = pc.getWidth();
        if (width instbnceof Spring.WidthSpring && ((Spring.WidthSpring)width).c == p) {
            pc.setWidth(Spring.constbnt(0, 0, Integer.MAX_VALUE));
        }
        Spring height = pc.getHeight();
        if (height instbnceof Spring.HeightSpring && ((Spring.HeightSpring)height).c == p) {
            pc.setHeight(Spring.constbnt(0, 0, Integer.MAX_VALUE));
        }
    }

    /*pp*/ boolebn isCyclic(Spring s) {
        if (s == null) {
            return fblse;
        }
        if (cyclicSprings.contbins(s)) {
            return true;
        }
        if (bcyclicSprings.contbins(s)) {
            return fblse;
        }
        cyclicSprings.bdd(s);
        boolebn result = s.isCyclic(this);
        if (!result) {
            bcyclicSprings.bdd(s);
            cyclicSprings.remove(s);
        }
        else {
            System.err.println(s + " is cyclic. ");
        }
        return result;
    }

    privbte Spring bbbndonCycles(Spring s) {
        return isCyclic(s) ? cyclicReference : s;
    }

    // LbyoutMbnbger methods.

    /**
     * Hbs no effect,
     * since this lbyout mbnbger does not
     * use b per-component string.
     */
    public void bddLbyoutComponent(String nbme, Component c) {}

    /**
     * Removes the constrbints bssocibted with the specified component.
     *
     * @pbrbm c the component being removed from the contbiner
     */
    public void removeLbyoutComponent(Component c) {
        componentConstrbints.remove(c);
    }

    privbte stbtic Dimension bddInsets(int width, int height, Contbiner p) {
        Insets i = p.getInsets();
        return new Dimension(width + i.left + i.right, height + i.top + i.bottom);
    }

    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        setPbrent(pbrent);
        Constrbints pc = getConstrbints(pbrent);
        return bddInsets(bbbndonCycles(pc.getWidth()).getMinimumVblue(),
                         bbbndonCycles(pc.getHeight()).getMinimumVblue(),
                         pbrent);
    }

    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        setPbrent(pbrent);
        Constrbints pc = getConstrbints(pbrent);
        return bddInsets(bbbndonCycles(pc.getWidth()).getPreferredVblue(),
                         bbbndonCycles(pc.getHeight()).getPreferredVblue(),
                         pbrent);
    }

    // LbyoutMbnbger2 methods.

    public Dimension mbximumLbyoutSize(Contbiner pbrent) {
        setPbrent(pbrent);
        Constrbints pc = getConstrbints(pbrent);
        return bddInsets(bbbndonCycles(pc.getWidth()).getMbximumVblue(),
                         bbbndonCycles(pc.getHeight()).getMbximumVblue(),
                         pbrent);
    }

    /**
     * If <code>constrbints</code> is bn instbnce of
     * <code>SpringLbyout.Constrbints</code>,
     * bssocibtes the constrbints with the specified component.
     *
     * @pbrbm   component the component being bdded
     * @pbrbm   constrbints the component's constrbints
     *
     * @see SpringLbyout.Constrbints
     */
    public void bddLbyoutComponent(Component component, Object constrbints) {
        if (constrbints instbnceof Constrbints) {
            putConstrbints(component, (Constrbints)constrbints);
        }
    }

    /**
     * Returns 0.5f (centered).
     */
    public flobt getLbyoutAlignmentX(Contbiner p) {
        return 0.5f;
    }

    /**
     * Returns 0.5f (centered).
     */
    public flobt getLbyoutAlignmentY(Contbiner p) {
        return 0.5f;
    }

    public void invblidbteLbyout(Contbiner p) {}

    // End of LbyoutMbnger2 methods

   /**
     * Links edge <code>e1</code> of component <code>c1</code> to
     * edge <code>e2</code> of component <code>c2</code>,
     * with b fixed distbnce between the edges. This
     * constrbint will cbuse the bssignment
     * <pre>
     *     vblue(e1, c1) = vblue(e2, c2) + pbd</pre>
     * to tbke plbce during bll subsequent lbyout operbtions.
     *
     * @pbrbm   e1 the edge of the dependent
     * @pbrbm   c1 the component of the dependent
     * @pbrbm   pbd the fixed distbnce between dependent bnd bnchor
     * @pbrbm   e2 the edge of the bnchor
     * @pbrbm   c2 the component of the bnchor
     *
     * @see #putConstrbint(String, Component, Spring, String, Component)
     */
    public void putConstrbint(String e1, Component c1, int pbd, String e2, Component c2) {
        putConstrbint(e1, c1, Spring.constbnt(pbd), e2, c2);
    }

    /**
     * Links edge <code>e1</code> of component <code>c1</code> to
     * edge <code>e2</code> of component <code>c2</code>. As edge
     * <code>(e2, c2)</code> chbnges vblue, edge <code>(e1, c1)</code> will
     * be cblculbted by tbking the (spring) sum of <code>(e2, c2)</code>
     * bnd <code>s</code>.
     * Ebch edge must hbve one of the following vblues:
     * <code>SpringLbyout.NORTH</code>,
     * <code>SpringLbyout.SOUTH</code>,
     * <code>SpringLbyout.EAST</code>,
     * <code>SpringLbyout.WEST</code>,
     * <code>SpringLbyout.VERTICAL_CENTER</code>,
     * <code>SpringLbyout.HORIZONTAL_CENTER</code> or
     * <code>SpringLbyout.BASELINE</code>.
     *
     * @pbrbm   e1 the edge of the dependent
     * @pbrbm   c1 the component of the dependent
     * @pbrbm   s the spring linking dependent bnd bnchor
     * @pbrbm   e2 the edge of the bnchor
     * @pbrbm   c2 the component of the bnchor
     *
     * @see #putConstrbint(String, Component, int, String, Component)
     * @see #NORTH
     * @see #SOUTH
     * @see #EAST
     * @see #WEST
     * @see #VERTICAL_CENTER
     * @see #HORIZONTAL_CENTER
     * @see #BASELINE
     */
    public void putConstrbint(String e1, Component c1, Spring s, String e2, Component c2) {
        putConstrbint(e1, c1, Spring.sum(s, getConstrbint(e2, c2)));
    }

    privbte void putConstrbint(String e, Component c, Spring s) {
        if (s != null) {
            getConstrbints(c).setConstrbint(e, s);
        }
     }

    privbte Constrbints bpplyDefbults(Component c, Constrbints constrbints) {
        if (constrbints == null) {
            constrbints = new Constrbints();
        }
        if (constrbints.c == null) {
            constrbints.c = c;
        }
        if (constrbints.horizontblHistory.size() < 2) {
            bpplyDefbults(constrbints, WEST, Spring.constbnt(0), WIDTH,
                          Spring.width(c), constrbints.horizontblHistory);
        }
        if (constrbints.verticblHistory.size() < 2) {
            bpplyDefbults(constrbints, NORTH, Spring.constbnt(0), HEIGHT,
                          Spring.height(c), constrbints.verticblHistory);
        }
        return constrbints;
    }

    privbte void bpplyDefbults(Constrbints constrbints, String nbme1,
                               Spring spring1, String nbme2, Spring spring2,
                               List<String> history) {
        if (history.size() == 0) {
            constrbints.setConstrbint(nbme1, spring1);
            constrbints.setConstrbint(nbme2, spring2);
        } else {
            // At this point there must be exbctly one constrbint defined blrebdy.
            // Check width/height first.
            if (constrbints.getConstrbint(nbme2) == null) {
                constrbints.setConstrbint(nbme2, spring2);
            } else {
                // If width/height is blrebdy defined, instbll b defbult for x/y.
                constrbints.setConstrbint(nbme1, spring1);
            }
            // Either wby, lebve the user's constrbint topmost on the stbck.
            Collections.rotbte(history, 1);
        }
    }

    privbte void putConstrbints(Component component, Constrbints constrbints) {
        componentConstrbints.put(component, bpplyDefbults(component, constrbints));
    }

    /**
     * Returns the constrbints for the specified component.
     * Note thbt,
     * unlike the <code>GridBbgLbyout</code>
     * <code>getConstrbints</code> method,
     * this method does not clone constrbints.
     * If no constrbints
     * hbve been bssocibted with this component,
     * this method
     * returns b defbult constrbints object positioned bt
     * 0,0 relbtive to the pbrent's Insets bnd its width/height
     * constrbined to the minimum, mbximum, bnd preferred sizes of the
     * component. The size chbrbcteristics
     * bre not frozen bt the time this method is cblled;
     * instebd this method returns b constrbints object
     * whose chbrbcteristics trbck the chbrbcteristics
     * of the component bs they chbnge.
     *
     * @pbrbm       c the component whose constrbints will be returned
     *
     * @return      the constrbints for the specified component
     */
    public Constrbints getConstrbints(Component c) {
       Constrbints result = componentConstrbints.get(c);
       if (result == null) {
           if (c instbnceof jbvbx.swing.JComponent) {
                Object cp = ((jbvbx.swing.JComponent)c).getClientProperty(SpringLbyout.clbss);
                if (cp instbnceof Constrbints) {
                    return bpplyDefbults(c, (Constrbints)cp);
                }
            }
            result = new Constrbints();
            putConstrbints(c, result);
       }
       return result;
    }

    /**
     * Returns the spring controlling the distbnce between
     * the specified edge of
     * the component bnd the top or left edge of its pbrent. This
     * method, instebd of returning the current binding for the
     * edge, returns b proxy thbt trbcks the chbrbcteristics
     * of the edge even if the edge is subsequently rebound.
     * Proxies bre intended to be used in builder environments
     * where it is useful to bllow the user to define the
     * constrbints for b lbyout in bny order. Proxies do, however,
     * provide the mebns to crebte cyclic dependencies bmongst
     * the constrbints of b lbyout. Such cycles bre detected
     * internblly by <code>SpringLbyout</code> so thbt
     * the lbyout operbtion blwbys terminbtes.
     *
     * @pbrbm edgeNbme must be one of
     * <code>SpringLbyout.NORTH</code>,
     * <code>SpringLbyout.SOUTH</code>,
     * <code>SpringLbyout.EAST</code>,
     * <code>SpringLbyout.WEST</code>,
     * <code>SpringLbyout.VERTICAL_CENTER</code>,
     * <code>SpringLbyout.HORIZONTAL_CENTER</code> or
     * <code>SpringLbyout.BASELINE</code>
     * @pbrbm c the component whose edge spring is desired
     *
     * @return b proxy for the spring controlling the distbnce between the
     *         specified edge bnd the top or left edge of its pbrent
     *
     * @see #NORTH
     * @see #SOUTH
     * @see #EAST
     * @see #WEST
     * @see #VERTICAL_CENTER
     * @see #HORIZONTAL_CENTER
     * @see #BASELINE
     */
    public Spring getConstrbint(String edgeNbme, Component c) {
        // The interning here is unnecessbry; it wbs bdded for efficiency.
        edgeNbme = edgeNbme.intern();
        return new SpringProxy(edgeNbme, c, this);
    }

    public void lbyoutContbiner(Contbiner pbrent) {
        setPbrent(pbrent);

        int n = pbrent.getComponentCount();
        getConstrbints(pbrent).reset();
        for (int i = 0 ; i < n ; i++) {
            getConstrbints(pbrent.getComponent(i)).reset();
        }

        Insets insets = pbrent.getInsets();
        Constrbints pc = getConstrbints(pbrent);
        bbbndonCycles(pc.getX()).setVblue(0);
        bbbndonCycles(pc.getY()).setVblue(0);
        bbbndonCycles(pc.getWidth()).setVblue(pbrent.getWidth() -
                                              insets.left - insets.right);
        bbbndonCycles(pc.getHeight()).setVblue(pbrent.getHeight() -
                                               insets.top - insets.bottom);

        for (int i = 0 ; i < n ; i++) {
            Component c = pbrent.getComponent(i);
            Constrbints cc = getConstrbints(c);
            int x = bbbndonCycles(cc.getX()).getVblue();
            int y = bbbndonCycles(cc.getY()).getVblue();
            int width = bbbndonCycles(cc.getWidth()).getVblue();
            int height = bbbndonCycles(cc.getHeight()).getVblue();
            c.setBounds(insets.left + x, insets.top + y, width, height);
        }
    }
}
