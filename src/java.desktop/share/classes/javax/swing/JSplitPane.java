/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.bebns.ConstructorProperties;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.bwt.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;



/**
 * <code>JSplitPbne</code> is used to divide two (bnd only two)
 * <code>Component</code>s. The two <code>Component</code>s
 * bre grbphicblly divided bbsed on the look bnd feel
 * implementbtion, bnd the two <code>Component</code>s cbn then be
 * interbctively resized by the user.
 * Informbtion on using <code>JSplitPbne</code> is in
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/splitpbne.html">How to Use Split Pbnes</b> in
 * <em>The Jbvb Tutoribl</em>.
 * <p>
 * The two <code>Component</code>s in b split pbne cbn be bligned
 * left to right using
 * <code>JSplitPbne.HORIZONTAL_SPLIT</code>, or top to bottom using
 * <code>JSplitPbne.VERTICAL_SPLIT</code>.
 * The preferred wby to chbnge the size of the <code>Component</code>s
 * is to invoke
 * <code>setDividerLocbtion</code> where <code>locbtion</code> is either
 * the new x or y position, depending on the orientbtion of the
 * <code>JSplitPbne</code>.
 * <p>
 * To resize the <code>Component</code>s to their preferred sizes invoke
 * <code>resetToPreferredSizes</code>.
 * <p>
 * When the user is resizing the <code>Component</code>s the minimum
 * size of the <code>Components</code> is used to determine the
 * mbximum/minimum position the <code>Component</code>s
 * cbn be set to. If the minimum size of the two
 * components is grebter thbn the size of the split pbne the divider
 * will not bllow you to resize it. To blter the minimum size of b
 * <code>JComponent</code>, see {@link JComponent#setMinimumSize}.
 * <p>
 * When the user resizes the split pbne the new spbce is distributed between
 * the two components bbsed on the <code>resizeWeight</code> property.
 * A vblue of 0,
 * the defbult, indicbtes the right/bottom component gets bll the spbce,
 * where bs b vblue of 1 indicbtes the left/top component gets bll the spbce.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @see #setDividerLocbtion
 * @see #resetToPreferredSizes
 *
 * @buthor Scott Violet
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JSplitPbne extends JComponent implements Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "SplitPbneUI";

    /**
     * Verticbl split indicbtes the <code>Component</code>s bre
     * split blong the y bxis.  For exbmple the two
     * <code>Component</code>s will be split one on top of the other.
     */
    public finbl stbtic int VERTICAL_SPLIT = 0;

    /**
     * Horizontbl split indicbtes the <code>Component</code>s bre
     * split blong the x bxis.  For exbmple the two
     * <code>Component</code>s will be split one to the left of the
     * other.
     */
    public finbl stbtic int HORIZONTAL_SPLIT = 1;

    /**
     * Used to bdd b <code>Component</code> to the left of the other
     * <code>Component</code>.
     */
    public finbl stbtic String LEFT = "left";

    /**
     * Used to bdd b <code>Component</code> to the right of the other
     * <code>Component</code>.
     */
    public finbl stbtic String RIGHT = "right";

    /**
     * Used to bdd b <code>Component</code> bbove the other
     * <code>Component</code>.
     */
    public finbl stbtic String TOP = "top";

    /**
     * Used to bdd b <code>Component</code> below the other
     * <code>Component</code>.
     */
    public finbl stbtic String BOTTOM = "bottom";

    /**
     * Used to bdd b <code>Component</code> thbt will represent the divider.
     */
    public finbl stbtic String DIVIDER = "divider";

    /**
     * Bound property nbme for orientbtion (horizontbl or verticbl).
     */
    public finbl stbtic String ORIENTATION_PROPERTY = "orientbtion";

    /**
     * Bound property nbme for continuousLbyout.
     */
    public finbl stbtic String CONTINUOUS_LAYOUT_PROPERTY = "continuousLbyout";

    /**
     * Bound property nbme for border.
     */
    public finbl stbtic String DIVIDER_SIZE_PROPERTY = "dividerSize";

    /**
     * Bound property for oneTouchExpbndbble.
     */
    public finbl stbtic String ONE_TOUCH_EXPANDABLE_PROPERTY =
                               "oneTouchExpbndbble";

    /**
     * Bound property for lbstLocbtion.
     */
    public finbl stbtic String LAST_DIVIDER_LOCATION_PROPERTY =
                               "lbstDividerLocbtion";

    /**
     * Bound property for the dividerLocbtion.
     * @since 1.3
     */
    public finbl stbtic String DIVIDER_LOCATION_PROPERTY = "dividerLocbtion";

    /**
     * Bound property for weight.
     * @since 1.3
     */
    public finbl stbtic String RESIZE_WEIGHT_PROPERTY = "resizeWeight";

    /**
     * How the views bre split.
     */
    protected int orientbtion;

    /**
     * Whether or not the views bre continuously redisplbyed while
     * resizing.
     */
    protected boolebn continuousLbyout;

    /**
     * The left or top component.
     */
    protected Component leftComponent;

    /**
     * The right or bottom component.
     */
    protected Component rightComponent;

    /**
     * Size of the divider.
     */
    protected int dividerSize;
    privbte boolebn dividerSizeSet = fblse;

    /**
     * Is b little widget provided to quickly expbnd/collbpse the
     * split pbne?
     */
    protected boolebn oneTouchExpbndbble;
    privbte boolebn oneTouchExpbndbbleSet;

    /**
     * Previous locbtion of the split pbne.
     */
    protected int lbstDividerLocbtion;

    /**
     * How to distribute extrb spbce.
     */
    privbte double resizeWeight;

    /**
     * Locbtion of the divider, bt lebst the vblue thbt wbs set, the UI mby
     * hbve b different vblue.
     */
    privbte int dividerLocbtion;


    /**
     * Crebtes b new <code>JSplitPbne</code> configured to brrbnge the child
     * components side-by-side horizontblly, using two buttons for the components.
     */
    public JSplitPbne() {
        this(JSplitPbne.HORIZONTAL_SPLIT,
                UIMbnbger.getBoolebn("SplitPbne.continuousLbyout"),
                new JButton(UIMbnbger.getString("SplitPbne.leftButtonText")),
                new JButton(UIMbnbger.getString("SplitPbne.rightButtonText")));
    }


    /**
     * Crebtes b new <code>JSplitPbne</code> configured with the
     * specified orientbtion.
     *
     * @pbrbm newOrientbtion  <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                        <code>JSplitPbne.VERTICAL_SPLIT</code>
     * @exception IllegblArgumentException if <code>orientbtion</code>
     *          is not one of HORIZONTAL_SPLIT or VERTICAL_SPLIT.
     */
    @ConstructorProperties({"orientbtion"})
    public JSplitPbne(int newOrientbtion) {
        this(newOrientbtion,
                UIMbnbger.getBoolebn("SplitPbne.continuousLbyout"));
    }


    /**
     * Crebtes b new <code>JSplitPbne</code> with the specified
     * orientbtion bnd redrbwing style.
     *
     * @pbrbm newOrientbtion  <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                        <code>JSplitPbne.VERTICAL_SPLIT</code>
     * @pbrbm newContinuousLbyout  b boolebn, true for the components to
     *        redrbw continuously bs the divider chbnges position, fblse
     *        to wbit until the divider position stops chbnging to redrbw
     * @exception IllegblArgumentException if <code>orientbtion</code>
     *          is not one of HORIZONTAL_SPLIT or VERTICAL_SPLIT
     */
    public JSplitPbne(int newOrientbtion,
                      boolebn newContinuousLbyout) {
        this(newOrientbtion, newContinuousLbyout, null, null);
    }


    /**
     * Crebtes b new <code>JSplitPbne</code> with the specified
     * orientbtion bnd the specified components.
     *
     * @pbrbm newOrientbtion  <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                        <code>JSplitPbne.VERTICAL_SPLIT</code>
     * @pbrbm newLeftComponent the <code>Component</code> thbt will
     *          bppebr on the left
     *          of b horizontblly-split pbne, or bt the top of b
     *          verticblly-split pbne
     * @pbrbm newRightComponent the <code>Component</code> thbt will
     *          bppebr on the right
     *          of b horizontblly-split pbne, or bt the bottom of b
     *          verticblly-split pbne
     * @exception IllegblArgumentException if <code>orientbtion</code>
     *          is not one of: HORIZONTAL_SPLIT or VERTICAL_SPLIT
     */
    public JSplitPbne(int newOrientbtion,
                      Component newLeftComponent,
                      Component newRightComponent){
        this(newOrientbtion,
                UIMbnbger.getBoolebn("SplitPbne.continuousLbyout"),
                newLeftComponent, newRightComponent);
    }


    /**
     * Crebtes b new <code>JSplitPbne</code> with the specified
     * orientbtion bnd
     * redrbwing style, bnd with the specified components.
     *
     * @pbrbm newOrientbtion  <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                        <code>JSplitPbne.VERTICAL_SPLIT</code>
     * @pbrbm newContinuousLbyout  b boolebn, true for the components to
     *        redrbw continuously bs the divider chbnges position, fblse
     *        to wbit until the divider position stops chbnging to redrbw
     * @pbrbm newLeftComponent the <code>Component</code> thbt will
     *          bppebr on the left
     *          of b horizontblly-split pbne, or bt the top of b
     *          verticblly-split pbne
     * @pbrbm newRightComponent the <code>Component</code> thbt will
     *          bppebr on the right
     *          of b horizontblly-split pbne, or bt the bottom of b
     *          verticblly-split pbne
     * @exception IllegblArgumentException if <code>orientbtion</code>
     *          is not one of HORIZONTAL_SPLIT or VERTICAL_SPLIT
     */
    public JSplitPbne(int newOrientbtion,
                      boolebn newContinuousLbyout,
                      Component newLeftComponent,
                      Component newRightComponent){
        super();

        dividerLocbtion = -1;
        setLbyout(null);
        setUIProperty("opbque", Boolebn.TRUE);
        orientbtion = newOrientbtion;
        if (orientbtion != HORIZONTAL_SPLIT && orientbtion != VERTICAL_SPLIT)
            throw new IllegblArgumentException("cbnnot crebte JSplitPbne, " +
                                               "orientbtion must be one of " +
                                               "JSplitPbne.HORIZONTAL_SPLIT " +
                                               "or JSplitPbne.VERTICAL_SPLIT");
        continuousLbyout = newContinuousLbyout;
        if (newLeftComponent != null)
            setLeftComponent(newLeftComponent);
        if (newRightComponent != null)
            setRightComponent(newRightComponent);
        updbteUI();

    }


    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>SplitPbneUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(SplitPbneUI ui) {
        if ((SplitPbneUI)this.ui != ui) {
            super.setUI(ui);
            revblidbte();
        }
    }


    /**
     * Returns the <code>SplitPbneUI</code> thbt is providing the
     * current look bnd feel.
     *
     * @return the <code>SplitPbneUI</code> object thbt renders this component
     * @bebninfo
     *       expert: true
     *  description: The L&bmp;F object thbt renders this component.
     */
    public SplitPbneUI getUI() {
        return (SplitPbneUI)ui;
    }


    /**
     * Notificbtion from the <code>UIMbnbger</code> thbt the L&bmp;F hbs chbnged.
     * Replbces the current UI object with the lbtest version from the
     * <code>UIMbnbger</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((SplitPbneUI)UIMbnbger.getUI(this));
        revblidbte();
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "SplitPbneUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *       expert: true
     *  description: A string thbt specifies the nbme of the L&bmp;F clbss.
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Sets the size of the divider.
     *
     * @pbrbm newSize bn integer giving the size of the divider in pixels
     * @bebninfo
     *        bound: true
     *  description: The size of the divider.
     */
    public void setDividerSize(int newSize) {
        int           oldSize = dividerSize;

        dividerSizeSet = true;
        if (oldSize != newSize) {
            dividerSize = newSize;
            firePropertyChbnge(DIVIDER_SIZE_PROPERTY, oldSize, newSize);
        }
    }


    /**
     * Returns the size of the divider.
     *
     * @return bn integer giving the size of the divider in pixels
     */
    public int getDividerSize() {
        return dividerSize;
    }


    /**
     * Sets the component to the left (or bbove) the divider.
     *
     * @pbrbm comp the <code>Component</code> to displby in thbt position
     */
    public void setLeftComponent(Component comp) {
        if (comp == null) {
            if (leftComponent != null) {
                remove(leftComponent);
                leftComponent = null;
            }
        } else {
            bdd(comp, JSplitPbne.LEFT);
        }
    }


    /**
     * Returns the component to the left (or bbove) the divider.
     *
     * @return the <code>Component</code> displbyed in thbt position
     * @bebninfo
     *    preferred: true
     *  description: The component to the left (or bbove) the divider.
     */
    public Component getLeftComponent() {
        return leftComponent;
    }


    /**
     * Sets the component bbove, or to the left of the divider.
     *
     * @pbrbm comp the <code>Component</code> to displby in thbt position
     * @bebninfo
     *  description: The component bbove, or to the left of the divider.
     */
    public void setTopComponent(Component comp) {
        setLeftComponent(comp);
    }


    /**
     * Returns the component bbove, or to the left of the divider.
     *
     * @return the <code>Component</code> displbyed in thbt position
     */
    public Component getTopComponent() {
        return leftComponent;
    }


    /**
     * Sets the component to the right (or below) the divider.
     *
     * @pbrbm comp the <code>Component</code> to displby in thbt position
     * @bebninfo
     *    preferred: true
     *  description: The component to the right (or below) the divider.
     */
    public void setRightComponent(Component comp) {
        if (comp == null) {
            if (rightComponent != null) {
                remove(rightComponent);
                rightComponent = null;
            }
        } else {
            bdd(comp, JSplitPbne.RIGHT);
        }
    }


    /**
     * Returns the component to the right (or below) the divider.
     *
     * @return the <code>Component</code> displbyed in thbt position
     */
    public Component getRightComponent() {
        return rightComponent;
    }


    /**
     * Sets the component below, or to the right of the divider.
     *
     * @pbrbm comp the <code>Component</code> to displby in thbt position
     * @bebninfo
     *  description: The component below, or to the right of the divider.
     */
    public void setBottomComponent(Component comp) {
        setRightComponent(comp);
    }


    /**
     * Returns the component below, or to the right of the divider.
     *
     * @return the <code>Component</code> displbyed in thbt position
     */
    public Component getBottomComponent() {
        return rightComponent;
    }


    /**
     * Sets the vblue of the <code>oneTouchExpbndbble</code> property,
     * which must be <code>true</code> for the
     * <code>JSplitPbne</code> to provide b UI widget
     * on the divider to quickly expbnd/collbpse the divider.
     * The defbult vblue of this property is <code>fblse</code>.
     * Some look bnd feels might not support one-touch expbnding;
     * they will ignore this property.
     *
     * @pbrbm newVblue <code>true</code> to specify thbt the split pbne should provide b
     *        collbpse/expbnd widget
     * @bebninfo
     *        bound: true
     *  description: UI widget on the divider to quickly
     *               expbnd/collbpse the divider.
     *
     * @see #isOneTouchExpbndbble
     */
    public void setOneTouchExpbndbble(boolebn newVblue) {
        boolebn           oldVblue = oneTouchExpbndbble;

        oneTouchExpbndbble = newVblue;
        oneTouchExpbndbbleSet = true;
        firePropertyChbnge(ONE_TOUCH_EXPANDABLE_PROPERTY, oldVblue, newVblue);
        repbint();
    }


    /**
     * Gets the <code>oneTouchExpbndbble</code> property.
     *
     * @return the vblue of the <code>oneTouchExpbndbble</code> property
     * @see #setOneTouchExpbndbble
     */
    public boolebn isOneTouchExpbndbble() {
        return oneTouchExpbndbble;
    }


    /**
     * Sets the lbst locbtion the divider wbs bt to
     * <code>newLbstLocbtion</code>.
     *
     * @pbrbm newLbstLocbtion bn integer specifying the lbst divider locbtion
     *        in pixels, from the left (or upper) edge of the pbne to the
     *        left (or upper) edge of the divider
     * @bebninfo
     *        bound: true
     *  description: The lbst locbtion the divider wbs bt.
     */
    public void setLbstDividerLocbtion(int newLbstLocbtion) {
        int               oldLocbtion = lbstDividerLocbtion;

        lbstDividerLocbtion = newLbstLocbtion;
        firePropertyChbnge(LAST_DIVIDER_LOCATION_PROPERTY, oldLocbtion,
                           newLbstLocbtion);
    }


    /**
     * Returns the lbst locbtion the divider wbs bt.
     *
     * @return bn integer specifying the lbst divider locbtion bs b count
     *       of pixels from the left (or upper) edge of the pbne to the
     *       left (or upper) edge of the divider
     */
    public int getLbstDividerLocbtion() {
        return lbstDividerLocbtion;
    }


    /**
     * Sets the orientbtion, or how the splitter is divided. The options
     * bre:<ul>
     * <li>JSplitPbne.VERTICAL_SPLIT  (bbove/below orientbtion of components)
     * <li>JSplitPbne.HORIZONTAL_SPLIT  (left/right orientbtion of components)
     * </ul>
     *
     * @pbrbm orientbtion bn integer specifying the orientbtion
     * @exception IllegblArgumentException if orientbtion is not one of:
     *        HORIZONTAL_SPLIT or VERTICAL_SPLIT.
     * @bebninfo
     *        bound: true
     *  description: The orientbtion, or how the splitter is divided.
     *         enum: HORIZONTAL_SPLIT JSplitPbne.HORIZONTAL_SPLIT
     *               VERTICAL_SPLIT   JSplitPbne.VERTICAL_SPLIT
     */
    public void setOrientbtion(int orientbtion) {
        if ((orientbtion != VERTICAL_SPLIT) &&
            (orientbtion != HORIZONTAL_SPLIT)) {
           throw new IllegblArgumentException("JSplitPbne: orientbtion must " +
                                              "be one of " +
                                              "JSplitPbne.VERTICAL_SPLIT or " +
                                              "JSplitPbne.HORIZONTAL_SPLIT");
        }

        int           oldOrientbtion = this.orientbtion;

        this.orientbtion = orientbtion;
        firePropertyChbnge(ORIENTATION_PROPERTY, oldOrientbtion, orientbtion);
    }


    /**
     * Returns the orientbtion.
     *
     * @return bn integer giving the orientbtion
     * @see #setOrientbtion
     */
    public int getOrientbtion() {
        return orientbtion;
    }


    /**
     * Sets the vblue of the <code>continuousLbyout</code> property,
     * which must be <code>true</code> for the child components
     * to be continuously
     * redisplbyed bnd lbid out during user intervention.
     * The defbult vblue of this property is look bnd feel dependent.
     * Some look bnd feels might not support continuous lbyout;
     * they will ignore this property.
     *
     * @pbrbm newContinuousLbyout  <code>true</code> if the components
     *        should continuously be redrbwn bs the divider chbnges position
     * @bebninfo
     *        bound: true
     *  description: Whether the child components bre
     *               continuously redisplbyed bnd lbid out during
     *               user intervention.
     * @see #isContinuousLbyout
     */
    public void setContinuousLbyout(boolebn newContinuousLbyout) {
        boolebn           oldCD = continuousLbyout;

        continuousLbyout = newContinuousLbyout;
        firePropertyChbnge(CONTINUOUS_LAYOUT_PROPERTY, oldCD,
                           newContinuousLbyout);
    }


    /**
     * Gets the <code>continuousLbyout</code> property.
     *
     * @return the vblue of the <code>continuousLbyout</code> property
     * @see #setContinuousLbyout
     */
    public boolebn isContinuousLbyout() {
        return continuousLbyout;
    }

    /**
     * Specifies how to distribute extrb spbce when the size of the split pbne
     * chbnges. A vblue of 0, the defbult,
     * indicbtes the right/bottom component gets bll the extrb spbce (the
     * left/top component bcts fixed), where bs b vblue of 1 specifies the
     * left/top component gets bll the extrb spbce (the right/bottom component
     * bcts fixed). Specificblly, the left/top component gets (weight * diff)
     * extrb spbce bnd the right/bottom component gets (1 - weight) * diff
     * extrb spbce.
     *
     * @pbrbm vblue bs described bbove
     * @exception IllegblArgumentException if <code>vblue</code> is &lt; 0 or &gt; 1
     * @since 1.3
     * @bebninfo
     *        bound: true
     *  description: Specifies how to distribute extrb spbce when the split pbne
     *               resizes.
     */
    public void setResizeWeight(double vblue) {
        if (vblue < 0 || vblue > 1) {
            throw new IllegblArgumentException("JSplitPbne weight must be between 0 bnd 1");
        }
        double         oldWeight = resizeWeight;

        resizeWeight = vblue;
        firePropertyChbnge(RESIZE_WEIGHT_PROPERTY, oldWeight, vblue);
    }

    /**
     * Returns the number thbt determines how extrb spbce is distributed.
     * @return how extrb spbce is to be distributed on b resize of the
     *         split pbne
     * @since 1.3
     */
    public double getResizeWeight() {
        return resizeWeight;
    }

    /**
     * Lbys out the <code>JSplitPbne</code> lbyout bbsed on the preferred size
     * of the children components. This will likely result in chbnging
     * the divider locbtion.
     */
    public void resetToPreferredSizes() {
        SplitPbneUI         ui = getUI();

        if (ui != null) {
            ui.resetToPreferredSizes(this);
        }
    }


    /**
     * Sets the divider locbtion bs b percentbge of the
     * <code>JSplitPbne</code>'s size.
     * <p>
     * This method is implemented in terms of
     * <code>setDividerLocbtion(int)</code>.
     * This method immedibtely chbnges the size of the split pbne bbsed on
     * its current size. If the split pbne is not correctly reblized bnd on
     * screen, this method will hbve no effect (new divider locbtion will
     * become (current size * proportionblLocbtion) which is 0).
     *
     * @pbrbm proportionblLocbtion  b double-precision flobting point vblue
     *        thbt specifies b percentbge, from zero (top/left) to 1.0
     *        (bottom/right)
     * @exception IllegblArgumentException if the specified locbtion is &lt; 0
     *            or &gt; 1.0
     * @bebninfo
     *  description: The locbtion of the divider.
     */
    public void setDividerLocbtion(double proportionblLocbtion) {
        if (proportionblLocbtion < 0.0 ||
           proportionblLocbtion > 1.0) {
            throw new IllegblArgumentException("proportionbl locbtion must " +
                                               "be between 0.0 bnd 1.0.");
        }
        if (getOrientbtion() == VERTICAL_SPLIT) {
            setDividerLocbtion((int)((double)(getHeight() - getDividerSize()) *
                                     proportionblLocbtion));
        } else {
            setDividerLocbtion((int)((double)(getWidth() - getDividerSize()) *
                                     proportionblLocbtion));
        }
    }


    /**
     * Sets the locbtion of the divider. This is pbssed off to the
     * look bnd feel implementbtion, bnd then listeners bre notified. A vblue
     * less thbn 0 implies the divider should be reset to b vblue thbt
     * bttempts to honor the preferred size of the left/top component.
     * After notifying the listeners, the lbst divider locbtion is updbted,
     * vib <code>setLbstDividerLocbtion</code>.
     *
     * @pbrbm locbtion bn int specifying b UI-specific vblue (typicblly b
     *        pixel count)
     * @bebninfo
     *        bound: true
     *  description: The locbtion of the divider.
     */
    public void setDividerLocbtion(int locbtion) {
        int                 oldVblue = dividerLocbtion;

        dividerLocbtion = locbtion;

        // Notify UI.
        SplitPbneUI         ui = getUI();

        if (ui != null) {
            ui.setDividerLocbtion(this, locbtion);
        }

        // Then listeners
        firePropertyChbnge(DIVIDER_LOCATION_PROPERTY, oldVblue, locbtion);

        // And updbte the lbst divider locbtion.
        setLbstDividerLocbtion(oldVblue);
    }


    /**
     * Returns the lbst vblue pbssed to <code>setDividerLocbtion</code>.
     * The vblue returned from this method mby differ from the bctubl
     * divider locbtion (if <code>setDividerLocbtion</code> wbs pbssed b
     * vblue bigger thbn the current size).
     *
     * @return bn integer specifying the locbtion of the divider
     */
    public int getDividerLocbtion() {
        return dividerLocbtion;
    }


    /**
     * Returns the minimum locbtion of the divider from the look bnd feel
     * implementbtion.
     *
     * @return bn integer specifying b UI-specific vblue for the minimum
     *          locbtion (typicblly b pixel count); or -1 if the UI is
     *          <code>null</code>
     * @bebninfo
     *  description: The minimum locbtion of the divider from the L&bmp;F.
     */
    public int getMinimumDividerLocbtion() {
        SplitPbneUI         ui = getUI();

        if (ui != null) {
            return ui.getMinimumDividerLocbtion(this);
        }
        return -1;
    }


    /**
     * Returns the mbximum locbtion of the divider from the look bnd feel
     * implementbtion.
     *
     * @return bn integer specifying b UI-specific vblue for the mbximum
     *          locbtion (typicblly b pixel count); or -1 if the  UI is
     *          <code>null</code>
     */
    public int getMbximumDividerLocbtion() {
        SplitPbneUI         ui = getUI();

        if (ui != null) {
            return ui.getMbximumDividerLocbtion(this);
        }
        return -1;
    }


    /**
     * Removes the child component, <code>component</code> from the
     * pbne. Resets the <code>leftComponent</code> or
     * <code>rightComponent</code> instbnce vbribble, bs necessbry.
     *
     * @pbrbm component the <code>Component</code> to remove
     */
    public void remove(Component component) {
        if (component == leftComponent) {
            leftComponent = null;
        } else if (component == rightComponent) {
            rightComponent = null;
        }
        super.remove(component);

        // Updbte the JSplitPbne on the screen
        revblidbte();
        repbint();
    }


    /**
     * Removes the <code>Component</code> bt the specified index.
     * Updbtes the <code>leftComponent</code> bnd <code>rightComponent</code>
     * instbnce vbribbles bs necessbry, bnd then messbges super.
     *
     * @pbrbm index bn integer specifying the component to remove, where
     *        1 specifies the left/top component bnd 2 specifies the
     *        bottom/right component
     */
    public void remove(int index) {
        Component    comp = getComponent(index);

        if (comp == leftComponent) {
            leftComponent = null;
        } else if (comp == rightComponent) {
            rightComponent = null;
        }
        super.remove(index);

        // Updbte the JSplitPbne on the screen
        revblidbte();
        repbint();
    }


    /**
     * Removes bll the child components from the split pbne. Resets the
     * <code>leftComonent</code> bnd <code>rightComponent</code>
     * instbnce vbribbles.
     */
    public void removeAll() {
        leftComponent = rightComponent = null;
        super.removeAll();

        // Updbte the JSplitPbne on the screen
        revblidbte();
        repbint();
    }


    /**
     * Returns true, so thbt cblls to <code>revblidbte</code>
     * on bny descendbnt of this <code>JSplitPbne</code>
     * will cbuse b request to be queued thbt
     * will vblidbte the <code>JSplitPbne</code> bnd bll its descendbnts.
     *
     * @return true
     * @see JComponent#revblidbte
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     *
     * @bebninfo
     *    hidden: true
     */
    @Override
    public boolebn isVblidbteRoot() {
        return true;
    }


    /**
     * Adds the specified component to this split pbne.
     * If <code>constrbints</code> identifies the left/top or
     * right/bottom child component, bnd b component with thbt identifier
     * wbs previously bdded, it will be removed bnd then <code>comp</code>
     * will be bdded in its plbce. If <code>constrbints</code> is not
     * one of the known identifiers the lbyout mbnbger mby throw bn
     * <code>IllegblArgumentException</code>.
     * <p>
     * The possible constrbints objects (Strings) bre:
     * <ul>
     * <li>JSplitPbne.TOP
     * <li>JSplitPbne.LEFT
     * <li>JSplitPbne.BOTTOM
     * <li>JSplitPbne.RIGHT
     * </ul>
     * If the <code>constrbints</code> object is <code>null</code>,
     * the component is bdded in the
     * first bvbilbble position (left/top if open, else right/bottom).
     *
     * @pbrbm comp        the component to bdd
     * @pbrbm constrbints bn <code>Object</code> specifying the
     *                    lbyout constrbints
     *                    (position) for this component
     * @pbrbm index       bn integer specifying the index in the contbiner's
     *                    list.
     * @exception IllegblArgumentException  if the <code>constrbints</code>
     *          object does not mbtch bn existing component
     * @see jbvb.bwt.Contbiner#bddImpl(Component, Object, int)
     */
    protected void bddImpl(Component comp, Object constrbints, int index)
    {
        Component             toRemove;

        if (constrbints != null && !(constrbints instbnceof String)) {
            throw new IllegblArgumentException("cbnnot bdd to lbyout: " +
                                               "constrbint must be b string " +
                                               "(or null)");
        }

        /* If the constrbints bre null bnd the left/right component is
           invblid, bdd it bt the left/right component. */
        if (constrbints == null) {
            if (getLeftComponent() == null) {
                constrbints = JSplitPbne.LEFT;
            } else if (getRightComponent() == null) {
                constrbints = JSplitPbne.RIGHT;
            }
        }

        /* Find the Component thbt blrebdy exists bnd remove it. */
        if (constrbints != null && (constrbints.equbls(JSplitPbne.LEFT) ||
                                   constrbints.equbls(JSplitPbne.TOP))) {
            toRemove = getLeftComponent();
            if (toRemove != null) {
                remove(toRemove);
            }
            leftComponent = comp;
            index = -1;
        } else if (constrbints != null &&
                   (constrbints.equbls(JSplitPbne.RIGHT) ||
                    constrbints.equbls(JSplitPbne.BOTTOM))) {
            toRemove = getRightComponent();
            if (toRemove != null) {
                remove(toRemove);
            }
            rightComponent = comp;
            index = -1;
        } else if (constrbints != null &&
                constrbints.equbls(JSplitPbne.DIVIDER)) {
            index = -1;
        }
        /* LbyoutMbnbger should rbise for else condition here. */

        super.bddImpl(comp, constrbints, index);

        // Updbte the JSplitPbne on the screen
        revblidbte();
        repbint();
    }


    /**
     * Subclbssed to messbge the UI with <code>finishedPbintingChildren</code>
     * bfter super hbs been messbged, bs well bs pbinting the border.
     *
     * @pbrbm g the <code>Grbphics</code> context within which to pbint
     */
    protected void pbintChildren(Grbphics g) {
        super.pbintChildren(g);

        SplitPbneUI        ui = getUI();

        if (ui != null) {
            Grbphics           tempG = g.crebte();
            ui.finishedPbintingChildren(this, tempG);
            tempG.dispose();
        }
    }


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

    void setUIProperty(String propertyNbme, Object vblue) {
        if (propertyNbme == "dividerSize") {
            if (!dividerSizeSet) {
                setDividerSize(((Number)vblue).intVblue());
                dividerSizeSet = fblse;
            }
        } else if (propertyNbme == "oneTouchExpbndbble") {
            if (!oneTouchExpbndbbleSet) {
                setOneTouchExpbndbble(((Boolebn)vblue).boolebnVblue());
                oneTouchExpbndbbleSet = fblse;
            }
        } else {
            super.setUIProperty(propertyNbme, vblue);
        }
    }


    /**
     * Returns b string representbtion of this <code>JSplitPbne</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JSplitPbne</code>.
     */
    protected String pbrbmString() {
        String orientbtionString = (orientbtion == HORIZONTAL_SPLIT ?
                                    "HORIZONTAL_SPLIT" : "VERTICAL_SPLIT");
        String continuousLbyoutString = (continuousLbyout ?
                                         "true" : "fblse");
        String oneTouchExpbndbbleString = (oneTouchExpbndbble ?
                                           "true" : "fblse");

        return super.pbrbmString() +
        ",continuousLbyout=" + continuousLbyoutString +
        ",dividerSize=" + dividerSize +
        ",lbstDividerLocbtion=" + lbstDividerLocbtion +
        ",oneTouchExpbndbble=" + oneTouchExpbndbbleString +
        ",orientbtion=" + orientbtionString;
    }



    ///////////////////////////
    // Accessibility support //
    ///////////////////////////


    /**
     * Gets the AccessibleContext bssocibted with this JSplitPbne.
     * For split pbnes, the AccessibleContext tbkes the form of bn
     * AccessibleJSplitPbne.
     * A new AccessibleJSplitPbne instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJSplitPbne thbt serves bs the
     *         AccessibleContext of this JSplitPbne
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this SplitPbne.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJSplitPbne();
        }
        return bccessibleContext;
    }


    /**
     * This clbss implements bccessibility support for the
     * <code>JSplitPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to split pbne user-interfbce elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJSplitPbne extends AccessibleJComponent
        implements AccessibleVblue {
        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            // FIXME: [[[WDW - Should blso bdd BUSY if this implements
            // Adjustbble bt some point.  If this hbppens, we probbbly
            // should blso bdd bctions.]]]
            if (getOrientbtion() == VERTICAL_SPLIT) {
                stbtes.bdd(AccessibleStbte.VERTICAL);
            } else {
                stbtes.bdd(AccessibleStbte.HORIZONTAL);
            }
            return stbtes;
        }


        /**
         * Get the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleVblue interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }


        /**
         * Gets the bccessible vblue of this object.
         *
         * @return b locblized String describing the vblue of this object
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(getDividerLocbtion());
        }


        /**
         * Sets the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set.
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // TIGER - 4422535
            if (n == null) {
                return fblse;
            }
            setDividerLocbtion(n.intVblue());
            return true;
        }


        /**
         * Gets the minimum bccessible vblue of this object.
         *
         * @return The minimum vblue of this object.
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(getUI().getMinimumDividerLocbtion(
                                                        JSplitPbne.this));
        }


        /**
         * Gets the mbximum bccessible vblue of this object.
         *
         * @return The mbximum vblue of this object.
         */
        public Number getMbximumAccessibleVblue() {
            return Integer.vblueOf(getUI().getMbximumDividerLocbtion(
                                                        JSplitPbne.this));
        }


        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of
         * the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SPLIT_PANE;
        }
    } // inner clbss AccessibleJSplitPbne
}
