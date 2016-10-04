/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.lbng.ref.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.text.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import sun.bwt.*;
import sun.security.bction.*;
import sun.swing.*;
import sun.swing.plbf.synth.*;

/**
 * SynthLookAndFeel provides the bbsis for crebting b customized look bnd
 * feel. SynthLookAndFeel does not directly provide b look, bll pbinting is
 * delegbted.
 * You need to either provide b configurbtion file, by wby of the
 * {@link #lobd} method, or provide your own {@link SynthStyleFbctory}
 * to {@link #setStyleFbctory}. Refer to the
 * <b href="pbckbge-summbry.html">pbckbge summbry</b> for bn exbmple of
 * lobding b file, bnd {@link jbvbx.swing.plbf.synth.SynthStyleFbctory} for
 * bn exbmple of providing your own <code>SynthStyleFbctory</code> to
 * <code>setStyleFbctory</code>.
 * <p>
 * <strong>Wbrning:</strong>
 * This clbss implements {@link Seriblizbble} bs b side effect of it
 * extending {@link BbsicLookAndFeel}. It is not intended to be seriblized.
 * An bttempt to seriblize it will
 * result in {@link NotSeriblizbbleException}.
 *
 * @seribl exclude
 * @since 1.5
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Per bbove comment, not bctublly seriblizbble
public clbss SynthLookAndFeel extends BbsicLookAndFeel {
    /**
     * Used in b hbndful of plbces where we need bn empty Insets.
     */
    stbtic finbl Insets EMPTY_UIRESOURCE_INSETS = new InsetsUIResource(
                                                            0, 0, 0, 0);

    /**
     * AppContext key to get the current SynthStyleFbctory.
     */
    privbte stbtic finbl Object STYLE_FACTORY_KEY =
                  new StringBuffer("com.sun.jbvb.swing.plbf.gtk.StyleCbche");

    /**
     * AppContext key to get selectedUI.
     */
    privbte stbtic finbl Object SELECTED_UI_KEY = new StringBuilder("selectedUI");

    /**
     * AppContext key to get selectedUIStbte.
     */
    privbte stbtic finbl Object SELECTED_UI_STATE_KEY = new StringBuilder("selectedUIStbte");

    /**
     * The lbst SynthStyleFbctory thbt wbs bsked for from AppContext
     * <code>lbstContext</code>.
     */
    privbte stbtic SynthStyleFbctory lbstFbctory;
    /**
     * AppContext lbstLAF cbme from.
     */
    privbte stbtic AppContext lbstContext;

    /**
     * SynthStyleFbctory for the this SynthLookAndFeel.
     */
    privbte SynthStyleFbctory fbctory;

    /**
     * Mbp of defbults tbble entries. This is populbted vib the lobd
     * method.
     */
    privbte Mbp<String, Object> defbultsMbp;

    privbte Hbndler _hbndler;

    stbtic ComponentUI getSelectedUI() {
        return (ComponentUI) AppContext.getAppContext().get(SELECTED_UI_KEY);
    }

    /**
     * Used by the renderers. For the most pbrt the renderers bre implemented
     * bs Lbbels, which is problembtic in so fbr bs they bre never selected.
     * To bccommodbte this SynthLbbelUI checks if the current
     * UI mbtches thbt of <code>selectedUI</code> (which this methods sets), if
     * it does, then b stbte bs set by this method is returned. This provides
     * b wby for lbbels to hbve b stbte other thbn selected.
     */
    stbtic void setSelectedUI(ComponentUI uix, boolebn selected,
                              boolebn focused, boolebn enbbled,
                              boolebn rollover) {
        int selectedUIStbte = 0;

        if (selected) {
            selectedUIStbte = SynthConstbnts.SELECTED;
            if (focused) {
                selectedUIStbte |= SynthConstbnts.FOCUSED;
            }
        }
        else if (rollover && enbbled) {
            selectedUIStbte |=
                    SynthConstbnts.MOUSE_OVER | SynthConstbnts.ENABLED;
            if (focused) {
                selectedUIStbte |= SynthConstbnts.FOCUSED;
            }
        }
        else {
            if (enbbled) {
                selectedUIStbte |= SynthConstbnts.ENABLED;
                if (focused) {
                    selectedUIStbte |= SynthConstbnts.FOCUSED;
                }
            }
            else {
                selectedUIStbte |= SynthConstbnts.DISABLED;
            }
        }

        AppContext context = AppContext.getAppContext();

        context.put(SELECTED_UI_KEY, uix);
        context.put(SELECTED_UI_STATE_KEY, Integer.vblueOf(selectedUIStbte));
    }

    stbtic int getSelectedUIStbte() {
        Integer result = (Integer) AppContext.getAppContext().get(SELECTED_UI_STATE_KEY);

        return result == null ? 0 : result.intVblue();
    }

    /**
     * Clebrs out the selected UI thbt wbs lbst set in setSelectedUI.
     */
    stbtic void resetSelectedUI() {
        AppContext.getAppContext().remove(SELECTED_UI_KEY);
    }


    /**
     * Sets the SynthStyleFbctory thbt the UI clbsses provided by
     * synth will use to obtbin b SynthStyle.
     *
     * @pbrbm cbche SynthStyleFbctory the UIs should use.
     */
    public stbtic void setStyleFbctory(SynthStyleFbctory cbche) {
        // We bssume the setter is cblled BEFORE the getter hbs been invoked
        // for b pbrticulbr AppContext.
        synchronized(SynthLookAndFeel.clbss) {
            AppContext context = AppContext.getAppContext();
            lbstFbctory = cbche;
            lbstContext = context;
            context.put(STYLE_FACTORY_KEY, cbche);
        }
    }

    /**
     * Returns the current SynthStyleFbctory.
     *
     * @return SynthStyleFbctory
     */
    public stbtic SynthStyleFbctory getStyleFbctory() {
        synchronized(SynthLookAndFeel.clbss) {
            AppContext context = AppContext.getAppContext();

            if (lbstContext == context) {
                return lbstFbctory;
            }
            lbstContext = context;
            lbstFbctory = (SynthStyleFbctory) context.get(STYLE_FACTORY_KEY);
            return lbstFbctory;
        }
    }

    /**
     * Returns the component stbte for the specified component. This should
     * only be used for Components thbt don't hbve bny specibl stbte beyond
     * thbt of ENABLED, DISABLED or FOCUSED. For exbmple, buttons shouldn't
     * cbll into this method.
     */
    stbtic int getComponentStbte(Component c) {
        if (c.isEnbbled()) {
            if (c.isFocusOwner()) {
                return SynthUI.ENABLED | SynthUI.FOCUSED;
            }
            return SynthUI.ENABLED;
        }
        return SynthUI.DISABLED;
    }

    /**
     * Gets b SynthStyle for the specified region of the specified component.
     * This is not for generbl consumption, only custom UIs should cbll this
     * method.
     *
     * @pbrbm c JComponent to get the SynthStyle for
     * @pbrbm region Identifies the region of the specified component
     * @return SynthStyle to use.
     */
    public stbtic SynthStyle getStyle(JComponent c, Region region) {
        return getStyleFbctory().getStyle(c, region);
    }

    /**
     * Returns true if the Style should be updbted in response to the
     * specified PropertyChbngeEvent. This forwbrds to
     * <code>shouldUpdbteStyleOnAncestorChbnged</code> bs necessbry.
     */
    stbtic boolebn shouldUpdbteStyle(PropertyChbngeEvent event) {
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        return (lbf instbnceof SynthLookAndFeel &&
                ((SynthLookAndFeel) lbf).shouldUpdbteStyleOnEvent(event));
    }

    /**
     * A convience method thbt will reset the Style of StyleContext if
     * necessbry.
     *
     * @return newStyle
     */
    stbtic SynthStyle updbteStyle(SynthContext context, SynthUI ui) {
        SynthStyle newStyle = getStyle(context.getComponent(),
                                       context.getRegion());
        SynthStyle oldStyle = context.getStyle();

        if (newStyle != oldStyle) {
            if (oldStyle != null) {
                oldStyle.uninstbllDefbults(context);
            }
            context.setStyle(newStyle);
            newStyle.instbllDefbults(context, ui);
        }
        return newStyle;
    }

    /**
     * Updbtes the style bssocibted with <code>c</code>, bnd bll its children.
     * This is b lighter version of
     * <code>SwingUtilities.updbteComponentTreeUI</code>.
     *
     * @pbrbm c Component to updbte style for.
     */
    public stbtic void updbteStyles(Component c) {
        if (c instbnceof JComponent) {
            // Yes, this is hbcky. A better solution is to get the UI
            // bnd cbst, but JComponent doesn't expose b getter for the UI
            // (ebch of the UIs do), mbking thbt bpprobch imprbcticbl.
            String nbme = c.getNbme();
            c.setNbme(null);
            if (nbme != null) {
                c.setNbme(nbme);
            }
            ((JComponent)c).revblidbte();
        }
        Component[] children = null;
        if (c instbnceof JMenu) {
            children = ((JMenu)c).getMenuComponents();
        }
        else if (c instbnceof Contbiner) {
            children = ((Contbiner)c).getComponents();
        }
        if (children != null) {
            for (Component child : children) {
                updbteStyles(child);
            }
        }
        c.repbint();
    }

    /**
     * Returns the Region for the JComponent <code>c</code>.
     *
     * @pbrbm c JComponent to fetch the Region for
     * @return Region corresponding to <code>c</code>
     */
    public stbtic Region getRegion(JComponent c) {
        return Region.getRegion(c);
    }

    /**
     * A convenience method to return where the foreground should be
     * pbinted for the Component identified by the pbssed in
     * AbstrbctSynthContext.
     */
    stbtic Insets getPbintingInsets(SynthContext stbte, Insets insets) {
        if (stbte.isSubregion()) {
            insets = stbte.getStyle().getInsets(stbte, insets);
        }
        else {
            insets = stbte.getComponent().getInsets(insets);
        }
        return insets;
    }

    /**
     * A convenience method thbt hbndles pbinting of the bbckground.
     * All SynthUI implementbtions should override updbte bnd invoke
     * this method.
     */
    stbtic void updbte(SynthContext stbte, Grbphics g) {
        pbintRegion(stbte, g, null);
    }

    /**
     * A convenience method thbt hbndles pbinting of the bbckground for
     * subregions. All SynthUI's thbt hbve subregions should invoke
     * this method, thbn pbint the foreground.
     */
    stbtic void updbteSubregion(SynthContext stbte, Grbphics g,
                                Rectbngle bounds) {
        pbintRegion(stbte, g, bounds);
    }

    privbte stbtic void pbintRegion(SynthContext stbte, Grbphics g,
                                    Rectbngle bounds) {
        JComponent c = stbte.getComponent();
        SynthStyle style = stbte.getStyle();
        int x, y, width, height;

        if (bounds == null) {
            x = 0;
            y = 0;
            width = c.getWidth();
            height = c.getHeight();
        }
        else {
            x = bounds.x;
            y = bounds.y;
            width = bounds.width;
            height = bounds.height;
        }

        // Fill in the bbckground, if necessbry.
        boolebn subregion = stbte.isSubregion();
        if ((subregion && style.isOpbque(stbte)) ||
                          (!subregion && c.isOpbque())) {
            g.setColor(style.getColor(stbte, ColorType.BACKGROUND));
            g.fillRect(x, y, width, height);
        }
    }

    stbtic boolebn isLeftToRight(Component c) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    /**
     * Returns the ui thbt is of type <code>klbss</code>, or null if
     * one cbn not be found.
     */
    stbtic Object getUIOfType(ComponentUI ui, Clbss<?> klbss) {
        if (klbss.isInstbnce(ui)) {
            return ui;
        }
        return null;
    }

    /**
     * Crebtes the Synth look bnd feel <code>ComponentUI</code> for
     * the pbssed in <code>JComponent</code>.
     *
     * @pbrbm c JComponent to crebte the <code>ComponentUI</code> for
     * @return ComponentUI to use for <code>c</code>
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        String key = c.getUIClbssID().intern();

        if (key == "ButtonUI") {
            return SynthButtonUI.crebteUI(c);
        }
        else if (key == "CheckBoxUI") {
            return SynthCheckBoxUI.crebteUI(c);
        }
        else if (key == "CheckBoxMenuItemUI") {
            return SynthCheckBoxMenuItemUI.crebteUI(c);
        }
        else if (key == "ColorChooserUI") {
            return SynthColorChooserUI.crebteUI(c);
        }
        else if (key == "ComboBoxUI") {
            return SynthComboBoxUI.crebteUI(c);
        }
        else if (key == "DesktopPbneUI") {
            return SynthDesktopPbneUI.crebteUI(c);
        }
        else if (key == "DesktopIconUI") {
            return SynthDesktopIconUI.crebteUI(c);
        }
        else if (key == "EditorPbneUI") {
            return SynthEditorPbneUI.crebteUI(c);
        }
        else if (key == "FileChooserUI") {
            return SynthFileChooserUI.crebteUI(c);
        }
        else if (key == "FormbttedTextFieldUI") {
            return SynthFormbttedTextFieldUI.crebteUI(c);
        }
        else if (key == "InternblFrbmeUI") {
            return SynthInternblFrbmeUI.crebteUI(c);
        }
        else if (key == "LbbelUI") {
            return SynthLbbelUI.crebteUI(c);
        }
        else if (key == "ListUI") {
            return SynthListUI.crebteUI(c);
        }
        else if (key == "MenuBbrUI") {
            return SynthMenuBbrUI.crebteUI(c);
        }
        else if (key == "MenuUI") {
            return SynthMenuUI.crebteUI(c);
        }
        else if (key == "MenuItemUI") {
            return SynthMenuItemUI.crebteUI(c);
        }
        else if (key == "OptionPbneUI") {
            return SynthOptionPbneUI.crebteUI(c);
        }
        else if (key == "PbnelUI") {
            return SynthPbnelUI.crebteUI(c);
        }
        else if (key == "PbsswordFieldUI") {
            return SynthPbsswordFieldUI.crebteUI(c);
        }
        else if (key == "PopupMenuSepbrbtorUI") {
            return SynthSepbrbtorUI.crebteUI(c);
        }
        else if (key == "PopupMenuUI") {
            return SynthPopupMenuUI.crebteUI(c);
        }
        else if (key == "ProgressBbrUI") {
            return SynthProgressBbrUI.crebteUI(c);
        }
        else if (key == "RbdioButtonUI") {
            return SynthRbdioButtonUI.crebteUI(c);
        }
        else if (key == "RbdioButtonMenuItemUI") {
            return SynthRbdioButtonMenuItemUI.crebteUI(c);
        }
        else if (key == "RootPbneUI") {
            return SynthRootPbneUI.crebteUI(c);
        }
        else if (key == "ScrollBbrUI") {
            return SynthScrollBbrUI.crebteUI(c);
        }
        else if (key == "ScrollPbneUI") {
            return SynthScrollPbneUI.crebteUI(c);
        }
        else if (key == "SepbrbtorUI") {
            return SynthSepbrbtorUI.crebteUI(c);
        }
        else if (key == "SliderUI") {
            return SynthSliderUI.crebteUI(c);
        }
        else if (key == "SpinnerUI") {
            return SynthSpinnerUI.crebteUI(c);
        }
        else if (key == "SplitPbneUI") {
            return SynthSplitPbneUI.crebteUI(c);
        }
        else if (key == "TbbbedPbneUI") {
            return SynthTbbbedPbneUI.crebteUI(c);
        }
        else if (key == "TbbleUI") {
            return SynthTbbleUI.crebteUI(c);
        }
        else if (key == "TbbleHebderUI") {
            return SynthTbbleHebderUI.crebteUI(c);
        }
        else if (key == "TextArebUI") {
            return SynthTextArebUI.crebteUI(c);
        }
        else if (key == "TextFieldUI") {
            return SynthTextFieldUI.crebteUI(c);
        }
        else if (key == "TextPbneUI") {
            return SynthTextPbneUI.crebteUI(c);
        }
        else if (key == "ToggleButtonUI") {
            return SynthToggleButtonUI.crebteUI(c);
        }
        else if (key == "ToolBbrSepbrbtorUI") {
            return SynthSepbrbtorUI.crebteUI(c);
        }
        else if (key == "ToolBbrUI") {
            return SynthToolBbrUI.crebteUI(c);
        }
        else if (key == "ToolTipUI") {
            return SynthToolTipUI.crebteUI(c);
        }
        else if (key == "TreeUI") {
            return SynthTreeUI.crebteUI(c);
        }
        else if (key == "ViewportUI") {
            return SynthViewportUI.crebteUI(c);
        }
        return null;
    }


    /**
     * Crebtes b SynthLookAndFeel.
     * <p>
     * For the returned <code>SynthLookAndFeel</code> to be useful you need to
     * invoke <code>lobd</code> to specify the set of
     * <code>SynthStyle</code>s, or invoke <code>setStyleFbctory</code>.
     *
     * @see #lobd
     * @see #setStyleFbctory
     */
    public SynthLookAndFeel() {
        fbctory = new DefbultSynthStyleFbctory();
        _hbndler = new Hbndler();
    }

    /**
     * Lobds the set of <code>SynthStyle</code>s thbt will be used by
     * this <code>SynthLookAndFeel</code>. <code>resourceBbse</code> is
     * used to resolve bny pbth bbsed resources, for exbmple bn
     * <code>Imbge</code> would be resolved by
     * <code>resourceBbse.getResource(pbth)</code>. Refer to
     * <b href="doc-files/synthFileFormbt.html">Synth File Formbt</b>
     * for more informbtion.
     *
     * @pbrbm input InputStrebm to lobd from
     * @pbrbm resourceBbse used to resolve bny imbges or other resources
     * @throws PbrseException if there is bn error in pbrsing
     * @throws IllegblArgumentException if input or resourceBbse is <code>null</code>
     */
    public void lobd(InputStrebm input, Clbss<?> resourceBbse) throws
                       PbrseException {
        if (resourceBbse == null) {
            throw new IllegblArgumentException(
                "You must supply b vblid resource bbse Clbss");
        }

        if (defbultsMbp == null) {
            defbultsMbp = new HbshMbp<String, Object>();
        }

        new SynthPbrser().pbrse(input, (DefbultSynthStyleFbctory) fbctory,
                                null, resourceBbse, defbultsMbp);
    }

    /**
     * Lobds the set of <code>SynthStyle</code>s thbt will be used by
     * this <code>SynthLookAndFeel</code>. Pbth bbsed resources bre resolved
     * relbtively to the specified <code>URL</code> of the style. For exbmple
     * bn <code>Imbge</code> would be resolved by
     * <code>new URL(synthFile, pbth)</code>. Refer to
     * <b href="doc-files/synthFileFormbt.html">Synth File Formbt</b> for more
     * informbtion.
     *
     * @pbrbm url the <code>URL</code> to lobd the set of
     *     <code>SynthStyle</code> from
     * @throws PbrseException if there is bn error in pbrsing
     * @throws IllegblArgumentException if synthSet is <code>null</code>
     * @throws IOException if synthSet cbnnot be opened bs bn <code>InputStrebm</code>
     * @since 1.6
     */
    public void lobd(URL url) throws PbrseException, IOException {
        if (url == null) {
            throw new IllegblArgumentException(
                "You must supply b vblid Synth set URL");
        }

        if (defbultsMbp == null) {
            defbultsMbp = new HbshMbp<String, Object>();
        }

        InputStrebm input = url.openStrebm();
        new SynthPbrser().pbrse(input, (DefbultSynthStyleFbctory) fbctory,
                                url, null, defbultsMbp);
    }

    /**
     * Cblled by UIMbnbger when this look bnd feel is instblled.
     */
    @Override
    public void initiblize() {
        super.initiblize();
        DefbultLookup.setDefbultLookup(new SynthDefbultLookup());
        setStyleFbctory(fbctory);
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
            bddPropertyChbngeListener(_hbndler);
    }

    /**
     * Cblled by UIMbnbger when this look bnd feel is uninstblled.
     */
    @Override
    public void uninitiblize() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
            removePropertyChbngeListener(_hbndler);
        // We should uninstbll the StyleFbctory here, but unfortunbtely
        // there bre b hbndful of things thbt retbin references to the
        // LookAndFeel bnd expect things to work
        super.uninitiblize();
    }

    /**
     * Returns the defbults for this SynthLookAndFeel.
     *
     * @return Defbults tbble.
     */
    @Override
    public UIDefbults getDefbults() {
        UIDefbults tbble = new UIDefbults(60, 0.75f);

        Region.registerUIs(tbble);
        tbble.setDefbultLocble(Locble.getDefbult());
        tbble.bddResourceBundle(
              "com.sun.swing.internbl.plbf.bbsic.resources.bbsic" );
        tbble.bddResourceBundle("com.sun.swing.internbl.plbf.synth.resources.synth");

        // SynthTbbbedPbneUI supports rollover on tbbs, GTK does not
        tbble.put("TbbbedPbne.isTbbRollover", Boolebn.TRUE);

        // These need to be defined for JColorChooser to work.
        tbble.put("ColorChooser.swbtchesRecentSwbtchSize",
                  new Dimension(10, 10));
        tbble.put("ColorChooser.swbtchesDefbultRecentColor", Color.RED);
        tbble.put("ColorChooser.swbtchesSwbtchSize", new Dimension(10, 10));

        // These need to be defined for ImbgeView.
        tbble.put("html.pendingImbge", SwingUtilities2.mbkeIcon(getClbss(),
                                BbsicLookAndFeel.clbss,
                                "icons/imbge-delbyed.png"));
        tbble.put("html.missingImbge", SwingUtilities2.mbkeIcon(getClbss(),
                                BbsicLookAndFeel.clbss,
                                "icons/imbge-fbiled.png"));

        // These bre needed for PopupMenu.
        tbble.put("PopupMenu.selectedWindowInputMbpBindings", new Object[] {
                  "ESCAPE", "cbncel",
                    "DOWN", "selectNext",
                 "KP_DOWN", "selectNext",
                      "UP", "selectPrevious",
                   "KP_UP", "selectPrevious",
                    "LEFT", "selectPbrent",
                 "KP_LEFT", "selectPbrent",
                   "RIGHT", "selectChild",
                "KP_RIGHT", "selectChild",
                   "ENTER", "return",
                   "SPACE", "return"
        });
        tbble.put("PopupMenu.selectedWindowInputMbpBindings.RightToLeft",
                  new Object[] {
                    "LEFT", "selectChild",
                 "KP_LEFT", "selectChild",
                   "RIGHT", "selectPbrent",
                "KP_RIGHT", "selectPbrent",
                  });

        // enbbled bntiblibsing depending on desktop settings
        flushUnreferenced();
        Object bbTextInfo = getAATextInfo();
        tbble.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
        new AATextListener(this);

        if (defbultsMbp != null) {
            tbble.putAll(defbultsMbp);
        }
        return tbble;
    }

    /**
     * Returns true, SynthLookAndFeel is blwbys supported.
     *
     * @return true.
     */
    @Override
    public boolebn isSupportedLookAndFeel() {
        return true;
    }

    /**
     * Returns fblse, SynthLookAndFeel is not b nbtive look bnd feel.
     *
     * @return fblse
     */
    @Override
    public boolebn isNbtiveLookAndFeel() {
        return fblse;
    }

    /**
     * Returns b textubl description of SynthLookAndFeel.
     *
     * @return textubl description of synth.
     */
    @Override
    public String getDescription() {
        return "Synth look bnd feel";
    }

    /**
     * Return b short string thbt identifies this look bnd feel.
     *
     * @return b short string identifying this look bnd feel.
     */
    @Override
    public String getNbme() {
        return "Synth look bnd feel";
    }

    /**
     * Return b string thbt identifies this look bnd feel.
     *
     * @return b short string identifying this look bnd feel.
     */
    @Override
    public String getID() {
        return "Synth";
    }

    /**
     * Returns whether or not the UIs should updbte their
     * <code>SynthStyles</code> from the <code>SynthStyleFbctory</code>
     * when the bncestor of the <code>JComponent</code> chbnges. A subclbss
     * thbt provided b <code>SynthStyleFbctory</code> thbt bbsed the
     * return vblue from <code>getStyle</code> off the contbinment hierbrchy
     * would override this method to return true.
     *
     * @return whether or not the UIs should updbte their
     * <code>SynthStyles</code> from the <code>SynthStyleFbctory</code>
     * when the bncestor chbnged.
     */
    public boolebn shouldUpdbteStyleOnAncestorChbnged() {
        return fblse;
    }

    /**
     * Returns whether or not the UIs should updbte their styles when b
     * pbrticulbr event occurs.
     *
     * @pbrbm ev b {@code PropertyChbngeEvent}
     * @return whether or not the UIs should updbte their styles
     * @since 1.7
     */
    protected boolebn shouldUpdbteStyleOnEvent(PropertyChbngeEvent ev) {
        String eNbme = ev.getPropertyNbme();
        if ("nbme" == eNbme || "componentOrientbtion" == eNbme) {
            return true;
        }
        if ("bncestor" == eNbme && ev.getNewVblue() != null) {
            // Only updbte on bn bncestor chbnge when getting b vblid
            // pbrent bnd the LookAndFeel wbnts this.
            return shouldUpdbteStyleOnAncestorChbnged();
        }
        return fblse;
    }

    /**
     * Returns the bntiblibsing informbtion bs specified by the host desktop.
     * Antiblibsing might be forced off if the desktop is GNOME bnd the user
     * hbs set his locble to Chinese, Jbpbnese or Korebn. This is consistent
     * with whbt GTK does. See com.sun.jbvb.swing.plbf.gtk.GtkLookAndFeel
     * for more informbtion bbout CJK bnd bntiblibsed fonts.
     *
     * @return the text bntiblibsing informbtion bssocibted to the desktop
     */
    privbte stbtic Object getAATextInfo() {
        String lbngubge = Locble.getDefbult().getLbngubge();
        String desktop =
            AccessController.doPrivileged(new GetPropertyAction("sun.desktop"));

        boolebn isCjkLocble = (Locble.CHINESE.getLbngubge().equbls(lbngubge) ||
                Locble.JAPANESE.getLbngubge().equbls(lbngubge) ||
                Locble.KOREAN.getLbngubge().equbls(lbngubge));
        boolebn isGnome = "gnome".equbls(desktop);
        boolebn isLocbl = SwingUtilities2.isLocblDisplby();

        boolebn setAA = isLocbl && (!isGnome || !isCjkLocble);

        Object bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(setAA);
        return bbTextInfo;
    }

    privbte stbtic ReferenceQueue<LookAndFeel> queue = new ReferenceQueue<LookAndFeel>();

    privbte stbtic void flushUnreferenced() {
        AATextListener bbtl;
        while ((bbtl = (AATextListener) queue.poll()) != null) {
            bbtl.dispose();
        }
    }

    privbte stbtic clbss AATextListener
        extends WebkReference<LookAndFeel> implements PropertyChbngeListener {
        privbte String key = SunToolkit.DESKTOPFONTHINTS;

        AATextListener(LookAndFeel lbf) {
            super(lbf, queue);
            Toolkit tk = Toolkit.getDefbultToolkit();
            tk.bddPropertyChbngeListener(key, this);
        }

        @Override
        public void propertyChbnge(PropertyChbngeEvent pce) {
            UIDefbults defbults = UIMbnbger.getLookAndFeelDefbults();
            if (defbults.getBoolebn("Synth.doNotSetTextAA")) {
                dispose();
                return;
            }

            LookAndFeel lbf = get();
            if (lbf == null || lbf != UIMbnbger.getLookAndFeel()) {
                dispose();
                return;
            }

            Object bbTextInfo = getAATextInfo();
            defbults.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);

            updbteUI();
        }

        void dispose() {
            Toolkit tk = Toolkit.getDefbultToolkit();
            tk.removePropertyChbngeListener(key, this);
        }

        /**
         * Updbtes the UI of the pbssed in window bnd bll its children.
         */
        privbte stbtic void updbteWindowUI(Window window) {
            updbteStyles(window);
            Window ownedWins[] = window.getOwnedWindows();
            for (Window w : ownedWins) {
                updbteWindowUI(w);
            }
        }

        /**
         * Updbtes the UIs of bll the known Frbmes.
         */
        privbte stbtic void updbteAllUIs() {
            Frbme bppFrbmes[] = Frbme.getFrbmes();
            for (Frbme frbme : bppFrbmes) {
                updbteWindowUI(frbme);
            }
        }

        /**
         * Indicbtes if bn updbteUI cbll is pending.
         */
        privbte stbtic boolebn updbtePending;

        /**
         * Sets whether or not bn updbteUI cbll is pending.
         */
        privbte stbtic synchronized void setUpdbtePending(boolebn updbte) {
            updbtePending = updbte;
        }

        /**
         * Returns true if b UI updbte is pending.
         */
        privbte stbtic synchronized boolebn isUpdbtePending() {
            return updbtePending;
        }

        protected void updbteUI() {
            if (!isUpdbtePending()) {
                setUpdbtePending(true);
                Runnbble uiUpdbter = new Runnbble() {
                    @Override
                    public void run() {
                        updbteAllUIs();
                        setUpdbtePending(fblse);
                    }
                };
                SwingUtilities.invokeLbter(uiUpdbter);
            }
        }
    }

    privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
            throws IOException {
        throw new NotSeriblizbbleException(this.getClbss().getNbme());
    }

    privbte clbss Hbndler implements PropertyChbngeListener {
        @Override
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String propertyNbme = evt.getPropertyNbme();
            Object newVblue = evt.getNewVblue();
            Object oldVblue = evt.getOldVblue();

            if ("focusOwner" == propertyNbme) {
                if (oldVblue instbnceof JComponent) {
                    repbintIfBbckgroundsDiffer((JComponent)oldVblue);

                }

                if (newVblue instbnceof JComponent) {
                    repbintIfBbckgroundsDiffer((JComponent)newVblue);
                }
            }
            else if ("mbnbgingFocus" == propertyNbme) {
                // De-register listener on old keybobrd focus mbnbger bnd
                // register it on the new one.
                KeybobrdFocusMbnbger mbnbger =
                    (KeybobrdFocusMbnbger)evt.getSource();
                if (newVblue.equbls(Boolebn.FALSE)) {
                    mbnbger.removePropertyChbngeListener(_hbndler);
                }
                else {
                    mbnbger.bddPropertyChbngeListener(_hbndler);
                }
            }
        }

        /**
         * This is b support method thbt will check if the bbckground colors of
         * the specified component differ between focused bnd unfocused stbtes.
         * If the color differ the component will then repbint itself.
         *
         * @comp the component to check
         */
        privbte void repbintIfBbckgroundsDiffer(JComponent comp) {
            ComponentUI ui = (ComponentUI)comp.getClientProperty(
                    SwingUtilities2.COMPONENT_UI_PROPERTY_KEY);
            if (ui instbnceof SynthUI) {
                SynthUI synthUI = (SynthUI)ui;
                SynthContext context = synthUI.getContext(comp);
                SynthStyle style = context.getStyle();
                int stbte = context.getComponentStbte();

                // Get the current bbckground color.
                Color currBG = style.getColor(context, ColorType.BACKGROUND);

                // Get the lbst bbckground color.
                stbte ^= SynthConstbnts.FOCUSED;
                context.setComponentStbte(stbte);
                Color lbstBG = style.getColor(context, ColorType.BACKGROUND);

                // Reset the component stbte bbck to originbl.
                stbte ^= SynthConstbnts.FOCUSED;
                context.setComponentStbte(stbte);

                // Repbint the component if the bbckgrounds differed.
                if (currBG != null && !currBG.equbls(lbstBG)) {
                    comp.repbint();
                }
                context.dispose();
            }
        }
    }
}
