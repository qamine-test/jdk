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

pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.colorchooser.*;
import jbvbx.swing.plbf.ColorChooserUI;
import jbvbx.bccessibility.*;

import sun.swing.SwingUtilities2;


/**
 * <code>JColorChooser</code> provides b pbne of controls designed to bllow
 * b user to mbnipulbte bnd select b color.
 * For informbtion bbout using color choosers, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/colorchooser.html">How to Use Color Choosers</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <p>
 *
 * This clbss provides three levels of API:
 * <ol>
 * <li>A stbtic convenience method which shows b modbl color-chooser
 * diblog bnd returns the color selected by the user.
 * <li>A stbtic convenience method for crebting b color-chooser diblog
 * where <code>ActionListeners</code> cbn be specified to be invoked when
 * the user presses one of the diblog buttons.
 * <li>The bbility to crebte instbnces of <code>JColorChooser</code> pbnes
 * directly (within bny contbiner). <code>PropertyChbnge</code> listeners
 * cbn be bdded to detect when the current "color" property chbnges.
 * </ol>
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
 *
 * @bebninfo
 *      bttribute: isContbiner fblse
 *    description: A component thbt supports selecting b Color.
 *
 *
 * @buthor Jbmes Gosling
 * @buthor Amy Fowler
 * @buthor Steve Wilson
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JColorChooser extends JComponent implements Accessible {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ColorChooserUI";

    privbte ColorSelectionModel selectionModel;

    privbte JComponent previewPbnel = ColorChooserComponentFbctory.getPreviewPbnel();

    privbte AbstrbctColorChooserPbnel[] chooserPbnels = new AbstrbctColorChooserPbnel[0];

    privbte boolebn drbgEnbbled;

    /**
     * The selection model property nbme.
     */
    public stbtic finbl String      SELECTION_MODEL_PROPERTY = "selectionModel";

    /**
     * The preview pbnel property nbme.
     */
    public stbtic finbl String      PREVIEW_PANEL_PROPERTY = "previewPbnel";

    /**
     * The chooserPbnel brrby property nbme.
     */
    public stbtic finbl String      CHOOSER_PANELS_PROPERTY = "chooserPbnels";


    /**
     * Shows b modbl color-chooser diblog bnd blocks until the
     * diblog is hidden.  If the user presses the "OK" button, then
     * this method hides/disposes the diblog bnd returns the selected color.
     * If the user presses the "Cbncel" button or closes the diblog without
     * pressing "OK", then this method hides/disposes the diblog bnd returns
     * <code>null</code>.
     *
     * @pbrbm component    the pbrent <code>Component</code> for the diblog
     * @pbrbm title        the String contbining the diblog's title
     * @pbrbm initiblColor the initibl Color set when the color-chooser is shown
     * @return the selected color or <code>null</code> if the user opted out
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic Color showDiblog(Component component,
        String title, Color initiblColor) throws HebdlessException {

        finbl JColorChooser pbne = new JColorChooser(initiblColor != null?
                                               initiblColor : Color.white);

        ColorTrbcker ok = new ColorTrbcker(pbne);
        JDiblog diblog = crebteDiblog(component, title, true, pbne, ok, null);

        diblog.bddComponentListener(new ColorChooserDiblog.DisposeOnClose());

        diblog.show(); // blocks until user brings diblog down...

        return ok.getColor();
    }


    /**
     * Crebtes bnd returns b new diblog contbining the specified
     * <code>ColorChooser</code> pbne blong with "OK", "Cbncel", bnd "Reset"
     * buttons. If the "OK" or "Cbncel" buttons bre pressed, the diblog is
     * butombticblly hidden (but not disposed).  If the "Reset"
     * button is pressed, the color-chooser's color will be reset to the
     * color which wbs set the lbst time <code>show</code> wbs invoked on the
     * diblog bnd the diblog will rembin showing.
     *
     * @pbrbm c              the pbrent component for the diblog
     * @pbrbm title          the title for the diblog
     * @pbrbm modbl          b boolebn. When true, the rembinder of the progrbm
     *                       is inbctive until the diblog is closed.
     * @pbrbm chooserPbne    the color-chooser to be plbced inside the diblog
     * @pbrbm okListener     the ActionListener invoked when "OK" is pressed
     * @pbrbm cbncelListener the ActionListener invoked when "Cbncel" is pressed
     * @return b new diblog contbining the color-chooser pbne
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public stbtic JDiblog crebteDiblog(Component c, String title, boolebn modbl,
        JColorChooser chooserPbne, ActionListener okListener,
        ActionListener cbncelListener) throws HebdlessException {

        Window window = JOptionPbne.getWindowForComponent(c);
        ColorChooserDiblog diblog;
        if (window instbnceof Frbme) {
            diblog = new ColorChooserDiblog((Frbme)window, title, modbl, c, chooserPbne,
                                            okListener, cbncelListener);
        } else {
            diblog = new ColorChooserDiblog((Diblog)window, title, modbl, c, chooserPbne,
                                            okListener, cbncelListener);
        }
        diblog.getAccessibleContext().setAccessibleDescription(title);
        return diblog;
    }

    /**
     * Crebtes b color chooser pbne with bn initibl color of white.
     */
    public JColorChooser() {
        this(Color.white);
    }

    /**
     * Crebtes b color chooser pbne with the specified initibl color.
     *
     * @pbrbm initiblColor the initibl color set in the chooser
     */
    public JColorChooser(Color initiblColor) {
        this( new DefbultColorSelectionModel(initiblColor) );

    }

    /**
     * Crebtes b color chooser pbne with the specified
     * <code>ColorSelectionModel</code>.
     *
     * @pbrbm model the <code>ColorSelectionModel</code> to be used
     */
    public JColorChooser(ColorSelectionModel model) {
        selectionModel = model;
        updbteUI();
        drbgEnbbled = fblse;
    }

    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return the <code>ColorChooserUI</code> object thbt renders
     *          this component
     */
    public ColorChooserUI getUI() {
        return (ColorChooserUI)ui;
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>ColorChooserUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     *
     * @bebninfo
     *       bound: true
     *      hidden: true
     * description: The UI object thbt implements the color chooser's LookAndFeel.
     */
    public void setUI(ColorChooserUI ui) {
        super.setUI(ui);
    }

    /**
     * Notificbtion from the <code>UIMbnbger</code> thbt the L&bmp;F hbs chbnged.
     * Replbces the current UI object with the lbtest version from the
     * <code>UIMbnbger</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ColorChooserUI)UIMbnbger.getUI(this));
    }

    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "ColorChooserUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
     * Gets the current color vblue from the color chooser.
     * By defbult, this delegbtes to the model.
     *
     * @return the current color vblue of the color chooser
     */
    public Color getColor() {
        return selectionModel.getSelectedColor();
    }

    /**
     * Sets the current color of the color chooser to the specified color.
     * The <code>ColorSelectionModel</code> will fire b <code>ChbngeEvent</code>
     * @pbrbm color the color to be set in the color chooser
     * @see JComponent#bddPropertyChbngeListener
     *
     * @bebninfo
     *       bound: fblse
     *      hidden: fblse
     * description: The current color the chooser is to displby.
     */
    public void setColor(Color color) {
        selectionModel.setSelectedColor(color);

    }

    /**
     * Sets the current color of the color chooser to the
     * specified RGB color.  Note thbt the vblues of red, green,
     * bnd blue should be between the numbers 0 bnd 255, inclusive.
     *
     * @pbrbm r   bn int specifying the bmount of Red
     * @pbrbm g   bn int specifying the bmount of Green
     * @pbrbm b   bn int specifying the bmount of Blue
     * @exception IllegblArgumentException if r,g,b vblues bre out of rbnge
     * @see jbvb.bwt.Color
     */
    public void setColor(int r, int g, int b) {
        setColor(new Color(r,g,b));
    }

    /**
     * Sets the current color of the color chooser to the
     * specified color.
     *
     * @pbrbm c bn integer vblue thbt sets the current color in the chooser
     *          where the low-order 8 bits specify the Blue vblue,
     *          the next 8 bits specify the Green vblue, bnd the 8 bits
     *          bbove thbt specify the Red vblue.
     */
    public void setColor(int c) {
        setColor((c >> 16) & 0xFF, (c >> 8) & 0xFF, c & 0xFF);
    }

    /**
     * Sets the <code>drbgEnbbled</code> property,
     * which must be <code>true</code> to enbble
     * butombtic drbg hbndling (the first pbrt of drbg bnd drop)
     * on this component.
     * The <code>trbnsferHbndler</code> property needs to be set
     * to b non-<code>null</code> vblue for the drbg to do
     * bnything.  The defbult vblue of the <code>drbgEnbbled</code>
     * property
     * is <code>fblse</code>.
     *
     * <p>
     *
     * When butombtic drbg hbndling is enbbled,
     * most look bnd feels begin b drbg-bnd-drop operbtion
     * when the user presses the mouse button over the preview pbnel.
     * Some look bnd feels might not support butombtic drbg bnd drop;
     * they will ignore this property.  You cbn work bround such
     * look bnd feels by modifying the component
     * to directly cbll the <code>exportAsDrbg</code> method of b
     * <code>TrbnsferHbndler</code>.
     *
     * @pbrbm b the vblue to set the <code>drbgEnbbled</code> property to
     * @exception HebdlessException if
     *            <code>b</code> is <code>true</code> bnd
     *            <code>GrbphicsEnvironment.isHebdless()</code>
     *            returns <code>true</code>
     *
     * @since 1.4
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #getDrbgEnbbled
     * @see #setTrbnsferHbndler
     * @see TrbnsferHbndler
     *
     * @bebninfo
     *  description: Determines whether butombtic drbg hbndling is enbbled.
     *        bound: fblse
     */
    public void setDrbgEnbbled(boolebn b) {
        if (b && GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        drbgEnbbled = b;
    }

    /**
     * Gets the vblue of the <code>drbgEnbbled</code> property.
     *
     * @return  the vblue of the <code>drbgEnbbled</code> property
     * @see #setDrbgEnbbled
     * @since 1.4
     */
    public boolebn getDrbgEnbbled() {
        return drbgEnbbled;
    }

    /**
     * Sets the current preview pbnel.
     * This will fire b <code>PropertyChbngeEvent</code> for the property
     * nbmed "previewPbnel".
     *
     * @pbrbm preview the <code>JComponent</code> which displbys the current color
     * @see JComponent#bddPropertyChbngeListener
     *
     * @bebninfo
     *       bound: true
     *      hidden: true
     * description: The UI component which displbys the current color.
     */
    public void setPreviewPbnel(JComponent preview) {

        if (previewPbnel != preview) {
            JComponent oldPreview = previewPbnel;
            previewPbnel = preview;
            firePropertyChbnge(JColorChooser.PREVIEW_PANEL_PROPERTY, oldPreview, preview);
        }
    }

    /**
     * Returns the preview pbnel thbt shows b chosen color.
     *
     * @return b <code>JComponent</code> object -- the preview pbnel
     */
    public JComponent getPreviewPbnel() {
        return previewPbnel;
    }

    /**
     * Adds b color chooser pbnel to the color chooser.
     *
     * @pbrbm pbnel the <code>AbstrbctColorChooserPbnel</code> to be bdded
     */
    public void bddChooserPbnel( AbstrbctColorChooserPbnel pbnel ) {
        AbstrbctColorChooserPbnel[] oldPbnels = getChooserPbnels();
        AbstrbctColorChooserPbnel[] newPbnels = new AbstrbctColorChooserPbnel[oldPbnels.length+1];
        System.brrbycopy(oldPbnels, 0, newPbnels, 0, oldPbnels.length);
        newPbnels[newPbnels.length-1] = pbnel;
        setChooserPbnels(newPbnels);
    }

    /**
     * Removes the Color Pbnel specified.
     *
     * @pbrbm pbnel   b string thbt specifies the pbnel to be removed
     * @return the color pbnel
     * @exception IllegblArgumentException if pbnel is not in list of
     *                  known chooser pbnels
     */
    public AbstrbctColorChooserPbnel removeChooserPbnel( AbstrbctColorChooserPbnel pbnel ) {


        int contbinedAt = -1;

        for (int i = 0; i < chooserPbnels.length; i++) {
            if (chooserPbnels[i] == pbnel) {
                contbinedAt = i;
                brebk;
            }
        }
        if (contbinedAt == -1) {
            throw new IllegblArgumentException("chooser pbnel not in this chooser");
        }

        AbstrbctColorChooserPbnel[] newArrby = new AbstrbctColorChooserPbnel[chooserPbnels.length-1];

        if (contbinedAt == chooserPbnels.length-1) {  // bt end
            System.brrbycopy(chooserPbnels, 0, newArrby, 0, newArrby.length);
        }
        else if (contbinedAt == 0) {  // bt stbrt
            System.brrbycopy(chooserPbnels, 1, newArrby, 0, newArrby.length);
        }
        else {  // in middle
            System.brrbycopy(chooserPbnels, 0, newArrby, 0, contbinedAt);
            System.brrbycopy(chooserPbnels, contbinedAt+1,
                             newArrby, contbinedAt, (chooserPbnels.length - contbinedAt - 1));
        }

        setChooserPbnels(newArrby);

        return pbnel;
    }


    /**
     * Specifies the Color Pbnels used to choose b color vblue.
     *
     * @pbrbm pbnels  bn brrby of <code>AbstrbctColorChooserPbnel</code>
     *          objects
     *
     * @bebninfo
     *       bound: true
     *      hidden: true
     * description: An brrby of different chooser types.
     */
    public void setChooserPbnels( AbstrbctColorChooserPbnel[] pbnels) {
        AbstrbctColorChooserPbnel[] oldVblue = chooserPbnels;
        chooserPbnels = pbnels;
        firePropertyChbnge(CHOOSER_PANELS_PROPERTY, oldVblue, pbnels);
    }

    /**
     * Returns the specified color pbnels.
     *
     * @return bn brrby of <code>AbstrbctColorChooserPbnel</code> objects
     */
    public AbstrbctColorChooserPbnel[] getChooserPbnels() {
        return chooserPbnels;
    }

    /**
     * Returns the dbtb model thbt hbndles color selections.
     *
     * @return b <code>ColorSelectionModel</code> object
     */
    public ColorSelectionModel getSelectionModel() {
        return selectionModel;
    }


    /**
     * Sets the model contbining the selected color.
     *
     * @pbrbm newModel   the new <code>ColorSelectionModel</code> object
     *
     * @bebninfo
     *       bound: true
     *      hidden: true
     * description: The model which contbins the currently selected color.
     */
    public void setSelectionModel(ColorSelectionModel newModel ) {
        ColorSelectionModel oldModel = selectionModel;
        selectionModel = newModel;
        firePropertyChbnge(JColorChooser.SELECTION_MODEL_PROPERTY, oldModel, newModel);
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


    /**
     * Returns b string representbtion of this <code>JColorChooser</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JColorChooser</code>
     */
    protected String pbrbmString() {
        StringBuilder chooserPbnelsString = new StringBuilder("");
        for (int i=0; i<chooserPbnels.length; i++) {
            chooserPbnelsString.bppend("[" + chooserPbnels[i].toString()
                                       + "]");
        }
        String previewPbnelString = (previewPbnel != null ?
                                     previewPbnel.toString() : "");

        return super.pbrbmString() +
        ",chooserPbnels=" + chooserPbnelsString.toString() +
        ",previewPbnel=" + previewPbnelString;
    }

/////////////////
// Accessibility support
////////////////

    protected AccessibleContext bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this JColorChooser.
     * For color choosers, the AccessibleContext tbkes the form of bn
     * AccessibleJColorChooser.
     * A new AccessibleJColorChooser instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJColorChooser thbt serves bs the
     *         AccessibleContext of this JColorChooser
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJColorChooser();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JColorChooser</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to color chooser user-interfbce
     * elements.
     */
    protected clbss AccessibleJColorChooser extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.COLOR_CHOOSER;
        }

    } // inner clbss AccessibleJColorChooser
}


/*
 * Clbss which builds b color chooser diblog consisting of
 * b JColorChooser with "Ok", "Cbncel", bnd "Reset" buttons.
 *
 * Note: This needs to be fixed to debl with locblizbtion!
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss ColorChooserDiblog extends JDiblog {
    privbte Color initiblColor;
    privbte JColorChooser chooserPbne;
    privbte JButton cbncelButton;

    public ColorChooserDiblog(Diblog owner, String title, boolebn modbl,
        Component c, JColorChooser chooserPbne,
        ActionListener okListener, ActionListener cbncelListener)
        throws HebdlessException {
        super(owner, title, modbl);
        initColorChooserDiblog(c, chooserPbne, okListener, cbncelListener);
    }

    public ColorChooserDiblog(Frbme owner, String title, boolebn modbl,
        Component c, JColorChooser chooserPbne,
        ActionListener okListener, ActionListener cbncelListener)
        throws HebdlessException {
        super(owner, title, modbl);
        initColorChooserDiblog(c, chooserPbne, okListener, cbncelListener);
    }

    protected void initColorChooserDiblog(Component c, JColorChooser chooserPbne,
        ActionListener okListener, ActionListener cbncelListener) {
        //setResizbble(fblse);

        this.chooserPbne = chooserPbne;

        Locble locble = getLocble();
        String okString = UIMbnbger.getString("ColorChooser.okText", locble);
        String cbncelString = UIMbnbger.getString("ColorChooser.cbncelText", locble);
        String resetString = UIMbnbger.getString("ColorChooser.resetText", locble);

        Contbiner contentPbne = getContentPbne();
        contentPbne.setLbyout(new BorderLbyout());
        contentPbne.bdd(chooserPbne, BorderLbyout.CENTER);

        /*
         * Crebte Lower button pbnel
         */
        JPbnel buttonPbne = new JPbnel();
        buttonPbne.setLbyout(new FlowLbyout(FlowLbyout.CENTER));
        JButton okButton = new JButton(okString);
        getRootPbne().setDefbultButton(okButton);
        okButton.getAccessibleContext().setAccessibleDescription(okString);
        okButton.setActionCommbnd("OK");
        okButton.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                hide();
            }
        });
        if (okListener != null) {
            okButton.bddActionListener(okListener);
        }
        buttonPbne.bdd(okButton);

        cbncelButton = new JButton(cbncelString);
        cbncelButton.getAccessibleContext().setAccessibleDescription(cbncelString);

        // The following few lines bre used to register esc to close the diblog
        @SuppressWbrnings("seribl") // bnonymous clbss
        Action cbncelKeyAction = new AbstrbctAction() {
            public void bctionPerformed(ActionEvent e) {
                ((AbstrbctButton)e.getSource()).fireActionPerformed(e);
            }
        };
        KeyStroke cbncelKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMbp inputMbp = cbncelButton.getInputMbp(JComponent.
                                                     WHEN_IN_FOCUSED_WINDOW);
        ActionMbp bctionMbp = cbncelButton.getActionMbp();
        if (inputMbp != null && bctionMbp != null) {
            inputMbp.put(cbncelKeyStroke, "cbncel");
            bctionMbp.put("cbncel", cbncelKeyAction);
        }
        // end esc hbndling

        cbncelButton.setActionCommbnd("cbncel");
        cbncelButton.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                hide();
            }
        });
        if (cbncelListener != null) {
            cbncelButton.bddActionListener(cbncelListener);
        }
        buttonPbne.bdd(cbncelButton);

        JButton resetButton = new JButton(resetString);
        resetButton.getAccessibleContext().setAccessibleDescription(resetString);
        resetButton.bddActionListener(new ActionListener() {
           public void bctionPerformed(ActionEvent e) {
               reset();
           }
        });
        int mnemonic = SwingUtilities2.getUIDefbultsInt("ColorChooser.resetMnemonic", locble, -1);
        if (mnemonic != -1) {
            resetButton.setMnemonic(mnemonic);
        }
        buttonPbne.bdd(resetButton);
        contentPbne.bdd(buttonPbne, BorderLbyout.SOUTH);

        if (JDiblog.isDefbultLookAndFeelDecorbted()) {
            boolebn supportsWindowDecorbtions =
            UIMbnbger.getLookAndFeel().getSupportsWindowDecorbtions();
            if (supportsWindowDecorbtions) {
                getRootPbne().setWindowDecorbtionStyle(JRootPbne.COLOR_CHOOSER_DIALOG);
            }
        }
        bpplyComponentOrientbtion(((c == null) ? getRootPbne() : c).getComponentOrientbtion());

        pbck();
        setLocbtionRelbtiveTo(c);

        this.bddWindowListener(new Closer());
    }

    public void show() {
        initiblColor = chooserPbne.getColor();
        super.show();
    }

    public void reset() {
        chooserPbne.setColor(initiblColor);
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    clbss Closer extends WindowAdbpter implements Seriblizbble{
        public void windowClosing(WindowEvent e) {
            cbncelButton.doClick(0);
            Window w = e.getWindow();
            w.hide();
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss DisposeOnClose extends ComponentAdbpter implements Seriblizbble{
        public void componentHidden(ComponentEvent e) {
            Window w = (Window)e.getComponent();
            w.dispose();
        }
    }

}

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
clbss ColorTrbcker implements ActionListener, Seriblizbble {
    JColorChooser chooser;
    Color color;

    public ColorTrbcker(JColorChooser c) {
        chooser = c;
    }

    public void bctionPerformed(ActionEvent e) {
        color = chooser.getColor();
    }

    public Color getColor() {
        return color;
    }
}
