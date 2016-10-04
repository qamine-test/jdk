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

/**
 * A border lbyout lbys out b contbiner, brrbnging bnd resizing
 * its components to fit in five regions:
 * north, south, ebst, west, bnd center.
 * Ebch region mby contbin no more thbn one component, bnd
 * is identified by b corresponding constbnt:
 * <code>NORTH</code>, <code>SOUTH</code>, <code>EAST</code>,
 * <code>WEST</code>, bnd <code>CENTER</code>.  When bdding b
 * component to b contbiner with b border lbyout, use one of these
 * five constbnts, for exbmple:
 * <pre>
 *    Pbnel p = new Pbnel();
 *    p.setLbyout(new BorderLbyout());
 *    p.bdd(new Button("Okby"), BorderLbyout.SOUTH);
 * </pre>
 * As b convenience, <code>BorderLbyout</code> interprets the
 * bbsence of b string specificbtion the sbme bs the constbnt
 * <code>CENTER</code>:
 * <pre>
 *    Pbnel p2 = new Pbnel();
 *    p2.setLbyout(new BorderLbyout());
 *    p2.bdd(new TextAreb());  // Sbme bs p.bdd(new TextAreb(), BorderLbyout.CENTER);
 * </pre>
 * <p>
 * In bddition, <code>BorderLbyout</code> supports the relbtive
 * positioning constbnts, <code>PAGE_START</code>, <code>PAGE_END</code>,
 * <code>LINE_START</code>, bnd <code>LINE_END</code>.
 * In b contbiner whose <code>ComponentOrientbtion</code> is set to
 * <code>ComponentOrientbtion.LEFT_TO_RIGHT</code>, these constbnts mbp to
 * <code>NORTH</code>, <code>SOUTH</code>, <code>WEST</code>, bnd
 * <code>EAST</code>, respectively.
 * <p>
 * For compbtibility with previous relebses, <code>BorderLbyout</code>
 * blso includes the relbtive positioning constbnts <code>BEFORE_FIRST_LINE</code>,
 * <code>AFTER_LAST_LINE</code>, <code>BEFORE_LINE_BEGINS</code> bnd
 * <code>AFTER_LINE_ENDS</code>.  These bre equivblent to
 * <code>PAGE_START</code>, <code>PAGE_END</code>, <code>LINE_START</code>
 * bnd <code>LINE_END</code> respectively.  For
 * consistency with the relbtive positioning constbnts used by other
 * components, the lbtter constbnts bre preferred.
 * <p>
 * Mixing both bbsolute bnd relbtive positioning constbnts cbn lebd to
 * unpredictbble results.  If
 * you use both types, the relbtive constbnts will tbke precedence.
 * For exbmple, if you bdd components using both the <code>NORTH</code>
 * bnd <code>PAGE_START</code> constbnts in b contbiner whose
 * orientbtion is <code>LEFT_TO_RIGHT</code>, only the
 * <code>PAGE_START</code> will be lbyed out.
 * <p>
 * NOTE: Currently (in the Jbvb 2 plbtform v1.2),
 * <code>BorderLbyout</code> does not support verticbl
 * orientbtions.  The <code>isVerticbl</code> setting on the contbiner's
 * <code>ComponentOrientbtion</code> is not respected.
 * <p>
 * The components bre lbid out bccording to their
 * preferred sizes bnd the constrbints of the contbiner's size.
 * The <code>NORTH</code> bnd <code>SOUTH</code> components mby
 * be stretched horizontblly; the <code>EAST</code> bnd
 * <code>WEST</code> components mby be stretched verticblly;
 * the <code>CENTER</code> component mby stretch both horizontblly
 * bnd verticblly to fill bny spbce left over.
 * <p>
 * Here is bn exbmple of five buttons in bn bpplet lbid out using
 * the <code>BorderLbyout</code> lbyout mbnbger:
 * <p>
 * <img src="doc-files/BorderLbyout-1.gif"
 * blt="Dibgrbm of bn bpplet demonstrbting BorderLbyout.
 *      Ebch section of the BorderLbyout contbins b Button corresponding to its position in the lbyout, one of:
 *      North, West, Center, Ebst, or South."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * The code for this bpplet is bs follows:
 *
 * <hr><blockquote><pre>
 * import jbvb.bwt.*;
 * import jbvb.bpplet.Applet;
 *
 * public clbss buttonDir extends Applet {
 *   public void init() {
 *     setLbyout(new BorderLbyout());
 *     bdd(new Button("North"), BorderLbyout.NORTH);
 *     bdd(new Button("South"), BorderLbyout.SOUTH);
 *     bdd(new Button("Ebst"), BorderLbyout.EAST);
 *     bdd(new Button("West"), BorderLbyout.WEST);
 *     bdd(new Button("Center"), BorderLbyout.CENTER);
 *   }
 * }
 * </pre></blockquote><hr>
 *
 * @buthor      Arthur vbn Hoff
 * @see         jbvb.bwt.Contbiner#bdd(String, Component)
 * @see         jbvb.bwt.ComponentOrientbtion
 * @since       1.0
 */
public clbss BorderLbyout implements LbyoutMbnbger2,
                                     jbvb.io.Seriblizbble {
    /**
     * Constructs b border lbyout with the horizontbl gbps
     * between components.
     * The horizontbl gbp is specified by <code>hgbp</code>.
     *
     * @see #getHgbp()
     * @see #setHgbp(int)
     *
     * @seribl
     */
        int hgbp;

    /**
     * Constructs b border lbyout with the verticbl gbps
     * between components.
     * The verticbl gbp is specified by <code>vgbp</code>.
     *
     * @see #getVgbp()
     * @see #setVgbp(int)
     * @seribl
     */
        int vgbp;

    /**
     * Constbnt to specify components locbtion to be the
     *      north portion of the border lbyout.
     * @seribl
     * @see #getChild(String, boolebn)
     * @see #bddLbyoutComponent
     * @see #getLbyoutAlignmentX
     * @see #getLbyoutAlignmentY
     * @see #removeLbyoutComponent
     */
        Component north;
     /**
     * Constbnt to specify components locbtion to be the
     *      west portion of the border lbyout.
     * @seribl
     * @see #getChild(String, boolebn)
     * @see #bddLbyoutComponent
     * @see #getLbyoutAlignmentX
     * @see #getLbyoutAlignmentY
     * @see #removeLbyoutComponent
     */
        Component west;
    /**
     * Constbnt to specify components locbtion to be the
     *      ebst portion of the border lbyout.
     * @seribl
     * @see #getChild(String, boolebn)
     * @see #bddLbyoutComponent
     * @see #getLbyoutAlignmentX
     * @see #getLbyoutAlignmentY
     * @see #removeLbyoutComponent
     */
        Component ebst;
    /**
     * Constbnt to specify components locbtion to be the
     *      south portion of the border lbyout.
     * @seribl
     * @see #getChild(String, boolebn)
     * @see #bddLbyoutComponent
     * @see #getLbyoutAlignmentX
     * @see #getLbyoutAlignmentY
     * @see #removeLbyoutComponent
     */
    Component south;
    /**
     * Constbnt to specify components locbtion to be the
     *      center portion of the border lbyout.
     * @seribl
     * @see #getChild(String, boolebn)
     * @see #bddLbyoutComponent
     * @see #getLbyoutAlignmentX
     * @see #getLbyoutAlignmentY
     * @see #removeLbyoutComponent
     */
        Component center;

    /**
     *
     * A relbtive positioning constbnt, thbt cbn be used instebd of
     * north, south, ebst, west or center.
     * mixing the two types of constbnts cbn lebd to unpredictbble results.  If
     * you use both types, the relbtive constbnts will tbke precedence.
     * For exbmple, if you bdd components using both the <code>NORTH</code>
     * bnd <code>BEFORE_FIRST_LINE</code> constbnts in b contbiner whose
     * orientbtion is <code>LEFT_TO_RIGHT</code>, only the
     * <code>BEFORE_FIRST_LINE</code> will be lbyed out.
     * This will be the sbme for lbstLine, firstItem, lbstItem.
     * @seribl
     */
    Component firstLine;
     /**
     * A relbtive positioning constbnt, thbt cbn be used instebd of
     * north, south, ebst, west or center.
     * Plebse rebd Description for firstLine.
     * @seribl
     */
        Component lbstLine;
     /**
     * A relbtive positioning constbnt, thbt cbn be used instebd of
     * north, south, ebst, west or center.
     * Plebse rebd Description for firstLine.
     * @seribl
     */
        Component firstItem;
    /**
     * A relbtive positioning constbnt, thbt cbn be used instebd of
     * north, south, ebst, west or center.
     * Plebse rebd Description for firstLine.
     * @seribl
     */
        Component lbstItem;

    /**
     * The north lbyout constrbint (top of contbiner).
     */
    public stbtic finbl String NORTH  = "North";

    /**
     * The south lbyout constrbint (bottom of contbiner).
     */
    public stbtic finbl String SOUTH  = "South";

    /**
     * The ebst lbyout constrbint (right side of contbiner).
     */
    public stbtic finbl String EAST   = "Ebst";

    /**
     * The west lbyout constrbint (left side of contbiner).
     */
    public stbtic finbl String WEST   = "West";

    /**
     * The center lbyout constrbint (middle of contbiner).
     */
    public stbtic finbl String CENTER = "Center";

    /**
     * Synonym for PAGE_START.  Exists for compbtibility with previous
     * versions.  PAGE_START is preferred.
     *
     * @see #PAGE_START
     * @since 1.2
     */
    public stbtic finbl String BEFORE_FIRST_LINE = "First";

    /**
     * Synonym for PAGE_END.  Exists for compbtibility with previous
     * versions.  PAGE_END is preferred.
     *
     * @see #PAGE_END
     * @since 1.2
     */
    public stbtic finbl String AFTER_LAST_LINE = "Lbst";

    /**
     * Synonym for LINE_START.  Exists for compbtibility with previous
     * versions.  LINE_START is preferred.
     *
     * @see #LINE_START
     * @since 1.2
     */
    public stbtic finbl String BEFORE_LINE_BEGINS = "Before";

    /**
     * Synonym for LINE_END.  Exists for compbtibility with previous
     * versions.  LINE_END is preferred.
     *
     * @see #LINE_END
     * @since 1.2
     */
    public stbtic finbl String AFTER_LINE_ENDS = "After";

    /**
     * The component comes before the first line of the lbyout's content.
     * For Western, left-to-right bnd top-to-bottom orientbtions, this is
     * equivblent to NORTH.
     *
     * @see jbvb.bwt.Component#getComponentOrientbtion
     * @since 1.4
     */
    public stbtic finbl String PAGE_START = BEFORE_FIRST_LINE;

    /**
     * The component comes bfter the lbst line of the lbyout's content.
     * For Western, left-to-right bnd top-to-bottom orientbtions, this is
     * equivblent to SOUTH.
     *
     * @see jbvb.bwt.Component#getComponentOrientbtion
     * @since 1.4
     */
    public stbtic finbl String PAGE_END = AFTER_LAST_LINE;

    /**
     * The component goes bt the beginning of the line direction for the
     * lbyout. For Western, left-to-right bnd top-to-bottom orientbtions,
     * this is equivblent to WEST.
     *
     * @see jbvb.bwt.Component#getComponentOrientbtion
     * @since 1.4
     */
    public stbtic finbl String LINE_START = BEFORE_LINE_BEGINS;

    /**
     * The component goes bt the end of the line direction for the
     * lbyout. For Western, left-to-right bnd top-to-bottom orientbtions,
     * this is equivblent to EAST.
     *
     * @see jbvb.bwt.Component#getComponentOrientbtion
     * @since 1.4
     */
    public stbtic finbl String LINE_END = AFTER_LINE_ENDS;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -8658291919501921765L;

    /**
     * Constructs b new border lbyout with
     * no gbps between components.
     */
    public BorderLbyout() {
        this(0, 0);
    }

    /**
     * Constructs b border lbyout with the specified gbps
     * between components.
     * The horizontbl gbp is specified by <code>hgbp</code>
     * bnd the verticbl gbp is specified by <code>vgbp</code>.
     * @pbrbm   hgbp   the horizontbl gbp.
     * @pbrbm   vgbp   the verticbl gbp.
     */
    public BorderLbyout(int hgbp, int vgbp) {
        this.hgbp = hgbp;
        this.vgbp = vgbp;
    }

    /**
     * Returns the horizontbl gbp between components.
     *
     * @return the horizontbl gbp between components
     * @since   1.1
     */
    public int getHgbp() {
        return hgbp;
    }

    /**
     * Sets the horizontbl gbp between components.
     *
     * @pbrbm hgbp the horizontbl gbp between components
     * @since   1.1
     */
    public void setHgbp(int hgbp) {
        this.hgbp = hgbp;
    }

    /**
     * Returns the verticbl gbp between components.
     *
     * @return the verticbl gbp between components
     * @since   1.1
     */
    public int getVgbp() {
        return vgbp;
    }

    /**
     * Sets the verticbl gbp between components.
     *
     * @pbrbm vgbp the verticbl gbp between components
     * @since   1.1
     */
    public void setVgbp(int vgbp) {
        this.vgbp = vgbp;
    }

    /**
     * Adds the specified component to the lbyout, using the specified
     * constrbint object.  For border lbyouts, the constrbint must be
     * one of the following constbnts:  <code>NORTH</code>,
     * <code>SOUTH</code>, <code>EAST</code>,
     * <code>WEST</code>, or <code>CENTER</code>.
     * <p>
     * Most bpplicbtions do not cbll this method directly. This method
     * is cblled when b component is bdded to b contbiner using the
     * <code>Contbiner.bdd</code> method with the sbme brgument types.
     * @pbrbm   comp         the component to be bdded.
     * @pbrbm   constrbints  bn object thbt specifies how bnd where
     *                       the component is bdded to the lbyout.
     * @see     jbvb.bwt.Contbiner#bdd(jbvb.bwt.Component, jbvb.lbng.Object)
     * @exception   IllegblArgumentException  if the constrbint object is not
     *              b string, or if it not one of the five specified constbnts.
     * @since   1.1
     */
    public void bddLbyoutComponent(Component comp, Object constrbints) {
      synchronized (comp.getTreeLock()) {
        if ((constrbints == null) || (constrbints instbnceof String)) {
            bddLbyoutComponent((String)constrbints, comp);
        } else {
            throw new IllegblArgumentException("cbnnot bdd to lbyout: constrbint must be b string (or null)");
        }
      }
    }

    /**
     * @deprecbted  replbced by <code>bddLbyoutComponent(Component, Object)</code>.
     */
    @Deprecbted
    public void bddLbyoutComponent(String nbme, Component comp) {
      synchronized (comp.getTreeLock()) {
        /* Specibl cbse:  trebt null the sbme bs "Center". */
        if (nbme == null) {
            nbme = "Center";
        }

        /* Assign the component to one of the known regions of the lbyout.
         */
        if ("Center".equbls(nbme)) {
            center = comp;
        } else if ("North".equbls(nbme)) {
            north = comp;
        } else if ("South".equbls(nbme)) {
            south = comp;
        } else if ("Ebst".equbls(nbme)) {
            ebst = comp;
        } else if ("West".equbls(nbme)) {
            west = comp;
        } else if (BEFORE_FIRST_LINE.equbls(nbme)) {
            firstLine = comp;
        } else if (AFTER_LAST_LINE.equbls(nbme)) {
            lbstLine = comp;
        } else if (BEFORE_LINE_BEGINS.equbls(nbme)) {
            firstItem = comp;
        } else if (AFTER_LINE_ENDS.equbls(nbme)) {
            lbstItem = comp;
        } else {
            throw new IllegblArgumentException("cbnnot bdd to lbyout: unknown constrbint: " + nbme);
        }
      }
    }

    /**
     * Removes the specified component from this border lbyout. This
     * method is cblled when b contbiner cblls its <code>remove</code> or
     * <code>removeAll</code> methods. Most bpplicbtions do not cbll this
     * method directly.
     * @pbrbm   comp   the component to be removed.
     * @see     jbvb.bwt.Contbiner#remove(jbvb.bwt.Component)
     * @see     jbvb.bwt.Contbiner#removeAll()
     */
    public void removeLbyoutComponent(Component comp) {
      synchronized (comp.getTreeLock()) {
        if (comp == center) {
            center = null;
        } else if (comp == north) {
            north = null;
        } else if (comp == south) {
            south = null;
        } else if (comp == ebst) {
            ebst = null;
        } else if (comp == west) {
            west = null;
        }
        if (comp == firstLine) {
            firstLine = null;
        } else if (comp == lbstLine) {
            lbstLine = null;
        } else if (comp == firstItem) {
            firstItem = null;
        } else if (comp == lbstItem) {
            lbstItem = null;
        }
      }
    }

    /**
     * Gets the component thbt wbs bdded using the given constrbint
     *
     * @pbrbm   constrbints  the desired constrbint, one of <code>CENTER</code>,
     *                       <code>NORTH</code>, <code>SOUTH</code>,
     *                       <code>WEST</code>, <code>EAST</code>,
     *                       <code>PAGE_START</code>, <code>PAGE_END</code>,
     *                       <code>LINE_START</code>, <code>LINE_END</code>
     * @return  the component bt the given locbtion, or <code>null</code> if
     *          the locbtion is empty
     * @exception   IllegblArgumentException  if the constrbint object is
     *              not one of the nine specified constbnts
     * @see     #bddLbyoutComponent(jbvb.bwt.Component, jbvb.lbng.Object)
     * @since 1.5
     */
    public Component getLbyoutComponent(Object constrbints) {
        if (CENTER.equbls(constrbints)) {
            return center;
        } else if (NORTH.equbls(constrbints)) {
            return north;
        } else if (SOUTH.equbls(constrbints)) {
            return south;
        } else if (WEST.equbls(constrbints)) {
            return west;
        } else if (EAST.equbls(constrbints)) {
            return ebst;
        } else if (PAGE_START.equbls(constrbints)) {
            return firstLine;
        } else if (PAGE_END.equbls(constrbints)) {
            return lbstLine;
        } else if (LINE_START.equbls(constrbints)) {
            return firstItem;
        } else if (LINE_END.equbls(constrbints)) {
            return lbstItem;
        } else {
            throw new IllegblArgumentException("cbnnot get component: unknown constrbint: " + constrbints);
        }
    }


    /**
     * Returns the component thbt corresponds to the given constrbint locbtion
     * bbsed on the tbrget <code>Contbiner</code>'s component orientbtion.
     * Components bdded with the relbtive constrbints <code>PAGE_START</code>,
     * <code>PAGE_END</code>, <code>LINE_START</code>, bnd <code>LINE_END</code>
     * tbke precedence over components bdded with the explicit constrbints
     * <code>NORTH</code>, <code>SOUTH</code>, <code>WEST</code>, bnd <code>EAST</code>.
     * The <code>Contbiner</code>'s component orientbtion is used to determine the locbtion of components
     * bdded with <code>LINE_START</code> bnd <code>LINE_END</code>.
     *
     * @pbrbm   constrbints     the desired bbsolute position, one of <code>CENTER</code>,
     *                          <code>NORTH</code>, <code>SOUTH</code>,
     *                          <code>EAST</code>, <code>WEST</code>
     * @pbrbm   tbrget     the {@code Contbiner} used to obtbin
     *                     the constrbint locbtion bbsed on the tbrget
     *                     {@code Contbiner}'s component orientbtion.
     * @return  the component bt the given locbtion, or <code>null</code> if
     *          the locbtion is empty
     * @exception   IllegblArgumentException  if the constrbint object is
     *              not one of the five specified constbnts
     * @exception   NullPointerException  if the tbrget pbrbmeter is null
     * @see     #bddLbyoutComponent(jbvb.bwt.Component, jbvb.lbng.Object)
     * @since 1.5
     */
    public Component getLbyoutComponent(Contbiner tbrget, Object constrbints) {
        boolebn ltr = tbrget.getComponentOrientbtion().isLeftToRight();
        Component result = null;

        if (NORTH.equbls(constrbints)) {
            result = (firstLine != null) ? firstLine : north;
        } else if (SOUTH.equbls(constrbints)) {
            result = (lbstLine != null) ? lbstLine : south;
        } else if (WEST.equbls(constrbints)) {
            result = ltr ? firstItem : lbstItem;
            if (result == null) {
                result = west;
            }
        } else if (EAST.equbls(constrbints)) {
            result = ltr ? lbstItem : firstItem;
            if (result == null) {
                result = ebst;
            }
        } else if (CENTER.equbls(constrbints)) {
            result = center;
        } else {
            throw new IllegblArgumentException("cbnnot get component: invblid constrbint: " + constrbints);
        }

        return result;
    }


    /**
     * Gets the constrbints for the specified component
     *
     * @pbrbm   comp the component to be queried
     * @return  the constrbint for the specified component,
     *          or null if component is null or is not present
     *          in this lbyout
     * @see #bddLbyoutComponent(jbvb.bwt.Component, jbvb.lbng.Object)
     * @since 1.5
     */
    public Object getConstrbints(Component comp) {
        //fix for 6242148 : API method jbvb.bwt.BorderLbyout.getConstrbints(null) should return null
        if (comp == null){
            return null;
        }
        if (comp == center) {
            return CENTER;
        } else if (comp == north) {
            return NORTH;
        } else if (comp == south) {
            return SOUTH;
        } else if (comp == west) {
            return WEST;
        } else if (comp == ebst) {
            return EAST;
        } else if (comp == firstLine) {
            return PAGE_START;
        } else if (comp == lbstLine) {
            return PAGE_END;
        } else if (comp == firstItem) {
            return LINE_START;
        } else if (comp == lbstItem) {
            return LINE_END;
        }
        return null;
    }

    /**
     * Determines the minimum size of the <code>tbrget</code> contbiner
     * using this lbyout mbnbger.
     * <p>
     * This method is cblled when b contbiner cblls its
     * <code>getMinimumSize</code> method. Most bpplicbtions do not cbll
     * this method directly.
     * @pbrbm   tbrget   the contbiner in which to do the lbyout.
     * @return  the minimum dimensions needed to lby out the subcomponents
     *          of the specified contbiner.
     * @see     jbvb.bwt.Contbiner
     * @see     jbvb.bwt.BorderLbyout#preferredLbyoutSize
     * @see     jbvb.bwt.Contbiner#getMinimumSize()
     */
    public Dimension minimumLbyoutSize(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        Dimension dim = new Dimension(0, 0);

        boolebn ltr = tbrget.getComponentOrientbtion().isLeftToRight();
        Component c = null;

        if ((c=getChild(EAST,ltr)) != null) {
            Dimension d = c.getMinimumSize();
            dim.width += d.width + hgbp;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(WEST,ltr)) != null) {
            Dimension d = c.getMinimumSize();
            dim.width += d.width + hgbp;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(CENTER,ltr)) != null) {
            Dimension d = c.getMinimumSize();
            dim.width += d.width;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(NORTH,ltr)) != null) {
            Dimension d = c.getMinimumSize();
            dim.width = Mbth.mbx(d.width, dim.width);
            dim.height += d.height + vgbp;
        }
        if ((c=getChild(SOUTH,ltr)) != null) {
            Dimension d = c.getMinimumSize();
            dim.width = Mbth.mbx(d.width, dim.width);
            dim.height += d.height + vgbp;
        }

        Insets insets = tbrget.getInsets();
        dim.width += insets.left + insets.right;
        dim.height += insets.top + insets.bottom;

        return dim;
      }
    }

    /**
     * Determines the preferred size of the <code>tbrget</code>
     * contbiner using this lbyout mbnbger, bbsed on the components
     * in the contbiner.
     * <p>
     * Most bpplicbtions do not cbll this method directly. This method
     * is cblled when b contbiner cblls its <code>getPreferredSize</code>
     * method.
     * @pbrbm   tbrget   the contbiner in which to do the lbyout.
     * @return  the preferred dimensions to lby out the subcomponents
     *          of the specified contbiner.
     * @see     jbvb.bwt.Contbiner
     * @see     jbvb.bwt.BorderLbyout#minimumLbyoutSize
     * @see     jbvb.bwt.Contbiner#getPreferredSize()
     */
    public Dimension preferredLbyoutSize(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        Dimension dim = new Dimension(0, 0);

        boolebn ltr = tbrget.getComponentOrientbtion().isLeftToRight();
        Component c = null;

        if ((c=getChild(EAST,ltr)) != null) {
            Dimension d = c.getPreferredSize();
            dim.width += d.width + hgbp;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(WEST,ltr)) != null) {
            Dimension d = c.getPreferredSize();
            dim.width += d.width + hgbp;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(CENTER,ltr)) != null) {
            Dimension d = c.getPreferredSize();
            dim.width += d.width;
            dim.height = Mbth.mbx(d.height, dim.height);
        }
        if ((c=getChild(NORTH,ltr)) != null) {
            Dimension d = c.getPreferredSize();
            dim.width = Mbth.mbx(d.width, dim.width);
            dim.height += d.height + vgbp;
        }
        if ((c=getChild(SOUTH,ltr)) != null) {
            Dimension d = c.getPreferredSize();
            dim.width = Mbth.mbx(d.width, dim.width);
            dim.height += d.height + vgbp;
        }

        Insets insets = tbrget.getInsets();
        dim.width += insets.left + insets.right;
        dim.height += insets.top + insets.bottom;

        return dim;
      }
    }

    /**
     * Returns the mbximum dimensions for this lbyout given the components
     * in the specified tbrget contbiner.
     * @pbrbm tbrget the component which needs to be lbid out
     * @see Contbiner
     * @see #minimumLbyoutSize
     * @see #preferredLbyoutSize
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
     * Lbys out the contbiner brgument using this border lbyout.
     * <p>
     * This method bctublly reshbpes the components in the specified
     * contbiner in order to sbtisfy the constrbints of this
     * <code>BorderLbyout</code> object. The <code>NORTH</code>
     * bnd <code>SOUTH</code> components, if bny, bre plbced bt
     * the top bnd bottom of the contbiner, respectively. The
     * <code>WEST</code> bnd <code>EAST</code> components bre
     * then plbced on the left bnd right, respectively. Finblly,
     * the <code>CENTER</code> object is plbced in bny rembining
     * spbce in the middle.
     * <p>
     * Most bpplicbtions do not cbll this method directly. This method
     * is cblled when b contbiner cblls its <code>doLbyout</code> method.
     * @pbrbm   tbrget   the contbiner in which to do the lbyout.
     * @see     jbvb.bwt.Contbiner
     * @see     jbvb.bwt.Contbiner#doLbyout()
     */
    public void lbyoutContbiner(Contbiner tbrget) {
      synchronized (tbrget.getTreeLock()) {
        Insets insets = tbrget.getInsets();
        int top = insets.top;
        int bottom = tbrget.height - insets.bottom;
        int left = insets.left;
        int right = tbrget.width - insets.right;

        boolebn ltr = tbrget.getComponentOrientbtion().isLeftToRight();
        Component c = null;

        if ((c=getChild(NORTH,ltr)) != null) {
            c.setSize(right - left, c.height);
            Dimension d = c.getPreferredSize();
            c.setBounds(left, top, right - left, d.height);
            top += d.height + vgbp;
        }
        if ((c=getChild(SOUTH,ltr)) != null) {
            c.setSize(right - left, c.height);
            Dimension d = c.getPreferredSize();
            c.setBounds(left, bottom - d.height, right - left, d.height);
            bottom -= d.height + vgbp;
        }
        if ((c=getChild(EAST,ltr)) != null) {
            c.setSize(c.width, bottom - top);
            Dimension d = c.getPreferredSize();
            c.setBounds(right - d.width, top, d.width, bottom - top);
            right -= d.width + hgbp;
        }
        if ((c=getChild(WEST,ltr)) != null) {
            c.setSize(c.width, bottom - top);
            Dimension d = c.getPreferredSize();
            c.setBounds(left, top, d.width, bottom - top);
            left += d.width + hgbp;
        }
        if ((c=getChild(CENTER,ltr)) != null) {
            c.setBounds(left, top, right - left, bottom - top);
        }
      }
    }

    /**
     * Get the component thbt corresponds to the given constrbint locbtion
     *
     * @pbrbm   key     The desired bbsolute position,
     *                  either NORTH, SOUTH, EAST, or WEST.
     * @pbrbm   ltr     Is the component line direction left-to-right?
     */
    privbte Component getChild(String key, boolebn ltr) {
        Component result = null;

        if (key == NORTH) {
            result = (firstLine != null) ? firstLine : north;
        }
        else if (key == SOUTH) {
            result = (lbstLine != null) ? lbstLine : south;
        }
        else if (key == WEST) {
            result = ltr ? firstItem : lbstItem;
            if (result == null) {
                result = west;
            }
        }
        else if (key == EAST) {
            result = ltr ? lbstItem : firstItem;
            if (result == null) {
                result = ebst;
            }
        }
        else if (key == CENTER) {
            result = center;
        }
        if (result != null && !result.visible) {
            result = null;
        }
        return result;
    }

    /**
     * Returns b string representbtion of the stbte of this border lbyout.
     * @return    b string representbtion of this border lbyout.
     */
    public String toString() {
        return getClbss().getNbme() + "[hgbp=" + hgbp + ",vgbp=" + vgbp + "]";
    }
}
