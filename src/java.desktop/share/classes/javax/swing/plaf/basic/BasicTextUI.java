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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.im.InputContext;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.synth.SynthUI;
import sun.swing.DefbultLookup;
import sun.bwt.AppContext;
import jbvbx.swing.plbf.bbsic.DrbgRecognitionSupport.BeforeDrbg;

/**
 * <p>
 * Bbsis of b text components look-bnd-feel.  This provides the
 * bbsic editor view bnd controller services thbt mby be useful
 * when crebting b look-bnd-feel for bn extension of
 * <code>JTextComponent</code>.
 * <p>
 * Most stbte is held in the bssocibted <code>JTextComponent</code>
 * bs bound properties, bnd the UI instblls defbult vblues for the
 * vbrious properties.  This defbult will instbll something for
 * bll of the properties.  Typicblly, b LAF implementbtion will
 * do more however.  At b minimum, b LAF would generblly instbll
 * key bindings.
 * <p>
 * This clbss blso provides some concurrency support if the
 * <code>Document</code> bssocibted with the JTextComponent is b subclbss of
 * <code>AbstrbctDocument</code>.  Access to the View (or View hierbrchy) is
 * seriblized between bny threbd mutbting the model bnd the Swing
 * event threbd (which is expected to render, do model/view coordinbte
 * trbnslbtion, etc).  <em>Any bccess to the root view should first
 * bcquire b rebd-lock on the AbstrbctDocument bnd relebse thbt lock
 * in b finblly block.</em>
 * <p>
 * An importbnt method to define is the {@link #getPropertyPrefix} method
 * which is used bs the bbsis of the keys used to fetch defbults
 * from the UIMbnbger.  The string should reflect the type of
 * TextUI (eg. TextField, TextAreb, etc) without the pbrticulbr
 * LAF pbrt of the nbme (eg Metbl, Motif, etc).
 * <p>
 * To build b view of the model, one of the following strbtegies
 * cbn be employed.
 * <ol>
 * <li>
 * One strbtegy is to simply redefine the
 * ViewFbctory interfbce in the UI.  By defbult, this UI itself bcts
 * bs the fbctory for View implementbtions.  This is useful
 * for simple fbctories.  To do this reimplement the
 * {@link #crebte} method.
 * <li>
 * A common strbtegy for crebting more complex types of documents
 * is to hbve the EditorKit implementbtion return b fbctory.  Since
 * the EditorKit ties bll of the pieces necessbry to mbintbin b type
 * of document, the fbctory is typicblly bn importbnt pbrt of thbt
 * bnd should be produced by the EditorKit implementbtion.
 * </ol>
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
 * @buthor Timothy Prinzing
 * @buthor Shbnnon Hickey (drbg bnd drop)
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss BbsicTextUI extends TextUI implements ViewFbctory {

    /**
     * Crebtes b new UI.
     */
    public BbsicTextUI() {
        pbinted = fblse;
    }

    /**
     * Crebtes the object to use for b cbret.  By defbult bn
     * instbnce of BbsicCbret is crebted.  This method
     * cbn be redefined to provide something else thbt implements
     * the InputPosition interfbce or b subclbss of JCbret.
     *
     * @return the cbret object
     */
    protected Cbret crebteCbret() {
        return new BbsicCbret();
    }

    /**
     * Crebtes the object to use for bdding highlights.  By defbult
     * bn instbnce of BbsicHighlighter is crebted.  This method
     * cbn be redefined to provide something else thbt implements
     * the Highlighter interfbce or b subclbss of DefbultHighlighter.
     *
     * @return the highlighter
     */
    protected Highlighter crebteHighlighter() {
        return new BbsicHighlighter();
    }

    /**
     * Fetches the nbme of the keymbp thbt will be instblled/used
     * by defbult for this UI. This is implemented to crebte b
     * nbme bbsed upon the clbssnbme.  The nbme is the the nbme
     * of the clbss with the pbckbge prefix removed.
     *
     * @return the nbme
     */
    protected String getKeymbpNbme() {
        String nm = getClbss().getNbme();
        int index = nm.lbstIndexOf('.');
        if (index >= 0) {
            nm = nm.substring(index+1, nm.length());
        }
        return nm;
    }

    /**
     * Crebtes the keymbp to use for the text component, bnd instblls
     * bny necessbry bindings into it.  By defbult, the keymbp is
     * shbred between bll instbnces of this type of TextUI. The
     * keymbp hbs the nbme defined by the getKeymbpNbme method.  If the
     * keymbp is not found, then DEFAULT_KEYMAP from JTextComponent is used.
     * <p>
     * The set of bindings used to crebte the keymbp is fetched
     * from the UIMbnbger using b key formed by combining the
     * {@link #getPropertyPrefix} method
     * bnd the string <code>.keyBindings</code>.  The type is expected
     * to be <code>JTextComponent.KeyBinding[]</code>.
     *
     * @return the keymbp
     * @see #getKeymbpNbme
     * @see jbvbx.swing.text.JTextComponent
     */
    protected Keymbp crebteKeymbp() {
        String nm = getKeymbpNbme();
        Keymbp mbp = JTextComponent.getKeymbp(nm);
        if (mbp == null) {
            Keymbp pbrent = JTextComponent.getKeymbp(JTextComponent.DEFAULT_KEYMAP);
            mbp = JTextComponent.bddKeymbp(nm, pbrent);
            String prefix = getPropertyPrefix();
            Object o = DefbultLookup.get(editor, this,
                prefix + ".keyBindings");
            if ((o != null) && (o instbnceof JTextComponent.KeyBinding[])) {
                JTextComponent.KeyBinding[] bindings = (JTextComponent.KeyBinding[]) o;
                JTextComponent.lobdKeymbp(mbp, bindings, getComponent().getActions());
            }
        }
        return mbp;
    }

    /**
     * This method gets cblled when b bound property is chbnged
     * on the bssocibted JTextComponent.  This is b hook
     * which UI implementbtions mby chbnge to reflect how the
     * UI displbys bound properties of JTextComponent subclbsses.
     * This is implemented to do nothing (i.e. the response to
     * properties in JTextComponent itself bre hbndled prior
     * to cblling this method).
     *
     * This implementbtion updbtes the bbckground of the text
     * component if the editbble bnd/or enbbled stbte chbnges.
     *
     * @pbrbm evt the property chbnge event
     */
    protected void propertyChbnge(PropertyChbngeEvent evt) {
        if (evt.getPropertyNbme().equbls("editbble") ||
                evt.getPropertyNbme().equbls("enbbled")) {

            updbteBbckground((JTextComponent)evt.getSource());
        }
    }

    /**
     * Updbtes the bbckground of the text component bbsed on whether the
     * text component is editbble bnd/or enbbled.
     *
     * @pbrbm c the JTextComponent thbt needs its bbckground color updbted
     */
    privbte void updbteBbckground(JTextComponent c) {
        // This is b temporbry workbround.
        // This code does not correctly debl with Synth (Synth doesn't use
        // properties like this), nor does it debl with the situbtion where
        // the developer grbbs the color from b JLbbel bnd sets it bs
        // the bbckground for b JTextAreb in bll look bnd feels. The problem
        // scenbrio results if the Color obtbined for the Lbbel bnd TextAreb
        // is ==, which is the cbse for the windows look bnd feel.
        // Until bn bppropribte solution is found, the code is being
        // reverted to whbt it wbs before the originbl fix.
        if (this instbnceof SynthUI || (c instbnceof JTextAreb)) {
            return;
        }
        Color bbckground = c.getBbckground();
        if (bbckground instbnceof UIResource) {
            String prefix = getPropertyPrefix();

            Color disbbledBG =
                DefbultLookup.getColor(c, this, prefix + ".disbbledBbckground", null);
            Color inbctiveBG =
                DefbultLookup.getColor(c, this, prefix + ".inbctiveBbckground", null);
            Color bg =
                DefbultLookup.getColor(c, this, prefix + ".bbckground", null);

            /* In bn idebl situbtion, the following check would not be necessbry
             * bnd we would replbce the color bny time the previous color wbs b
             * UIResouce. However, it turns out thbt there is existing code thbt
             * uses the following inbdvisbble pbttern to turn b text breb into
             * whbt bppebrs to be b multi-line lbbel:
             *
             * JLbbel lbbel = new JLbbel();
             * JTextAreb breb = new JTextAreb();
             * breb.setBbckground(lbbel.getBbckground());
             * breb.setEditbble(fblse);
             *
             * JLbbel's defbult bbckground is b UIResource. As such, just
             * checking for UIResource would hbve us blwbys chbnging the
             * bbckground bwby from whbt the developer wbnted.
             *
             * Therefore, for JTextAreb/JEditorPbne, we'll bdditionblly check
             * thbt the color we're bbout to replbce mbtches one thbt wbs
             * instblled by us from the UIDefbults.
             */
            if ((c instbnceof JTextAreb || c instbnceof JEditorPbne)
                    && bbckground != disbbledBG
                    && bbckground != inbctiveBG
                    && bbckground != bg) {

                return;
            }

            Color newColor = null;
            if (!c.isEnbbled()) {
                newColor = disbbledBG;
            }
            if (newColor == null && !c.isEditbble()) {
                newColor = inbctiveBG;
            }
            if (newColor == null) {
                newColor = bg;
            }
            if (newColor != null && newColor != bbckground) {
                c.setBbckground(newColor);
            }
        }
    }

    /**
     * Gets the nbme used bs b key to look up properties through the
     * UIMbnbger.  This is used bs b prefix to bll the stbndbrd
     * text properties.
     *
     * @return the nbme
     */
    protected bbstrbct String getPropertyPrefix();

    /**
     * Initiblizes component properties, such bs font, foreground,
     * bbckground, cbret color, selection color, selected text color,
     * disbbled text color, bnd border color.  The font, foreground, bnd
     * bbckground properties bre only set if their current vblue is either null
     * or b UIResource, other properties bre set if the current
     * vblue is null.
     *
     * @see #uninstbllDefbults
     * @see #instbllUI
     */
    protected void instbllDefbults()
    {
        String prefix = getPropertyPrefix();
        Font f = editor.getFont();
        if ((f == null) || (f instbnceof UIResource)) {
            editor.setFont(UIMbnbger.getFont(prefix + ".font"));
        }

        Color bg = editor.getBbckground();
        if ((bg == null) || (bg instbnceof UIResource)) {
            editor.setBbckground(UIMbnbger.getColor(prefix + ".bbckground"));
        }

        Color fg = editor.getForeground();
        if ((fg == null) || (fg instbnceof UIResource)) {
            editor.setForeground(UIMbnbger.getColor(prefix + ".foreground"));
        }

        Color color = editor.getCbretColor();
        if ((color == null) || (color instbnceof UIResource)) {
            editor.setCbretColor(UIMbnbger.getColor(prefix + ".cbretForeground"));
        }

        Color s = editor.getSelectionColor();
        if ((s == null) || (s instbnceof UIResource)) {
            editor.setSelectionColor(UIMbnbger.getColor(prefix + ".selectionBbckground"));
        }

        Color sfg = editor.getSelectedTextColor();
        if ((sfg == null) || (sfg instbnceof UIResource)) {
            editor.setSelectedTextColor(UIMbnbger.getColor(prefix + ".selectionForeground"));
        }

        Color dfg = editor.getDisbbledTextColor();
        if ((dfg == null) || (dfg instbnceof UIResource)) {
            editor.setDisbbledTextColor(UIMbnbger.getColor(prefix + ".inbctiveForeground"));
        }

        Border b = editor.getBorder();
        if ((b == null) || (b instbnceof UIResource)) {
            editor.setBorder(UIMbnbger.getBorder(prefix + ".border"));
        }

        Insets mbrgin = editor.getMbrgin();
        if (mbrgin == null || mbrgin instbnceof UIResource) {
            editor.setMbrgin(UIMbnbger.getInsets(prefix + ".mbrgin"));
        }

        updbteCursor();
    }

    privbte void instbllDefbults2() {
        editor.bddMouseListener(drbgListener);
        editor.bddMouseMotionListener(drbgListener);

        String prefix = getPropertyPrefix();

        Cbret cbret = editor.getCbret();
        if (cbret == null || cbret instbnceof UIResource) {
            cbret = crebteCbret();
            editor.setCbret(cbret);

            int rbte = DefbultLookup.getInt(getComponent(), this, prefix + ".cbretBlinkRbte", 500);
            cbret.setBlinkRbte(rbte);
        }

        Highlighter highlighter = editor.getHighlighter();
        if (highlighter == null || highlighter instbnceof UIResource) {
            editor.setHighlighter(crebteHighlighter());
        }

        TrbnsferHbndler th = editor.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            editor.setTrbnsferHbndler(getTrbnsferHbndler());
        }
    }

    /**
     * Sets the component properties thbt hbve not been explicitly overridden
     * to {@code null}.  A property is considered overridden if its current
     * vblue is not b {@code UIResource}.
     *
     * @see #instbllDefbults
     * @see #uninstbllUI
     */
    protected void uninstbllDefbults()
    {
        editor.removeMouseListener(drbgListener);
        editor.removeMouseMotionListener(drbgListener);

        if (editor.getCbretColor() instbnceof UIResource) {
            editor.setCbretColor(null);
        }

        if (editor.getSelectionColor() instbnceof UIResource) {
            editor.setSelectionColor(null);
        }

        if (editor.getDisbbledTextColor() instbnceof UIResource) {
            editor.setDisbbledTextColor(null);
        }

        if (editor.getSelectedTextColor() instbnceof UIResource) {
            editor.setSelectedTextColor(null);
        }

        if (editor.getBorder() instbnceof UIResource) {
            editor.setBorder(null);
        }

        if (editor.getMbrgin() instbnceof UIResource) {
            editor.setMbrgin(null);
        }

        if (editor.getCbret() instbnceof UIResource) {
            editor.setCbret(null);
        }

        if (editor.getHighlighter() instbnceof UIResource) {
            editor.setHighlighter(null);
        }

        if (editor.getTrbnsferHbndler() instbnceof UIResource) {
            editor.setTrbnsferHbndler(null);
        }

        if (editor.getCursor() instbnceof UIResource) {
            editor.setCursor(null);
        }
    }

    /**
     * Instblls listeners for the UI.
     */
    protected void instbllListeners() {
    }

    /**
     * Uninstblls listeners for the UI.
     */
    protected void uninstbllListeners() {
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions() {
        // bbckwbrd compbtibility support... keymbps for the UI
        // bre now instblled in the more friendly input mbp.
        editor.setKeymbp(crebteKeymbp());

        InputMbp km = getInputMbp();
        if (km != null) {
            SwingUtilities.replbceUIInputMbp(editor, JComponent.WHEN_FOCUSED,
                                             km);
        }

        ActionMbp mbp = getActionMbp();
        if (mbp != null) {
            SwingUtilities.replbceUIActionMbp(editor, mbp);
        }

        updbteFocusAccelerbtorBinding(fblse);
    }

    /**
     * Get the InputMbp to use for the UI.
     */
    InputMbp getInputMbp() {
        InputMbp mbp = new InputMbpUIResource();

        InputMbp shbred =
            (InputMbp)DefbultLookup.get(editor, this,
            getPropertyPrefix() + ".focusInputMbp");
        if (shbred != null) {
            mbp.setPbrent(shbred);
        }
        return mbp;
    }

    /**
     * Invoked when the focus bccelerbtor chbnges, this will updbte the
     * key bindings bs necessbry.
     */
    void updbteFocusAccelerbtorBinding(boolebn chbnged) {
        chbr bccelerbtor = editor.getFocusAccelerbtor();

        if (chbnged || bccelerbtor != '\0') {
            InputMbp km = SwingUtilities.getUIInputMbp
                        (editor, JComponent.WHEN_IN_FOCUSED_WINDOW);

            if (km == null && bccelerbtor != '\0') {
                km = new ComponentInputMbpUIResource(editor);
                SwingUtilities.replbceUIInputMbp(editor, JComponent.
                                                 WHEN_IN_FOCUSED_WINDOW, km);
                ActionMbp bm = getActionMbp();
                SwingUtilities.replbceUIActionMbp(editor, bm);
            }
            if (km != null) {
                km.clebr();
                if (bccelerbtor != '\0') {
                    km.put(KeyStroke.getKeyStroke(bccelerbtor, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk()), "requestFocus");
                }
            }
        }
    }


    /**
     * Invoked when editbble property is chbnged.
     *
     * removing 'TAB' bnd 'SHIFT-TAB' from trbversblKeysSet in cbse
     * editor is editbble
     * bdding 'TAB' bnd 'SHIFT-TAB' to trbversblKeysSet in cbse
     * editor is non editbble
     */

    void updbteFocusTrbversblKeys() {
        /*
         * Fix for 4514331 Non-editbble JTextAreb bnd similbr
         * should bllow Tbb to keybobrd - bccessibility
         */
        EditorKit editorKit = getEditorKit(editor);
        if ( editorKit != null
             && editorKit instbnceof DefbultEditorKit) {
            Set<AWTKeyStroke> storedForwbrdTrbversblKeys = editor.
                getFocusTrbversblKeys(KeybobrdFocusMbnbger.
                                      FORWARD_TRAVERSAL_KEYS);
            Set<AWTKeyStroke> storedBbckwbrdTrbversblKeys = editor.
                getFocusTrbversblKeys(KeybobrdFocusMbnbger.
                                      BACKWARD_TRAVERSAL_KEYS);
            Set<AWTKeyStroke> forwbrdTrbversblKeys =
                new HbshSet<AWTKeyStroke>(storedForwbrdTrbversblKeys);
            Set<AWTKeyStroke> bbckwbrdTrbversblKeys =
                new HbshSet<AWTKeyStroke>(storedBbckwbrdTrbversblKeys);
            if (editor.isEditbble()) {
                forwbrdTrbversblKeys.
                    remove(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
                bbckwbrdTrbversblKeys.
                    remove(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
                                                  InputEvent.SHIFT_MASK));
            } else {
                forwbrdTrbversblKeys.bdd(KeyStroke.
                                         getKeyStroke(KeyEvent.VK_TAB, 0));
                bbckwbrdTrbversblKeys.
                    bdd(KeyStroke.
                        getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK));
            }
            LookAndFeel.instbllProperty(editor,
                                        "focusTrbversblKeysForwbrd",
                                         forwbrdTrbversblKeys);
            LookAndFeel.instbllProperty(editor,
                                        "focusTrbversblKeysBbckwbrd",
                                         bbckwbrdTrbversblKeys);
        }

    }

    /**
     * As needed updbtes cursor for the tbrget editor.
     */
    privbte void updbteCursor() {
        if ((! editor.isCursorSet())
               || editor.getCursor() instbnceof UIResource) {
            Cursor cursor = (editor.isEditbble()) ? textCursor : null;
            editor.setCursor(cursor);
        }
    }

    /**
     * Returns the <code>TrbnsferHbndler</code> thbt will be instblled if
     * their isn't one instblled on the <code>JTextComponent</code>.
     */
    TrbnsferHbndler getTrbnsferHbndler() {
        return defbultTrbnsferHbndler;
    }

    /**
     * Fetch bn bction mbp to use.
     */
    ActionMbp getActionMbp() {
        String mbpNbme = getPropertyPrefix() + ".bctionMbp";
        ActionMbp mbp = (ActionMbp)UIMbnbger.get(mbpNbme);

        if (mbp == null) {
            mbp = crebteActionMbp();
            if (mbp != null) {
                UIMbnbger.getLookAndFeelDefbults().put(mbpNbme, mbp);
            }
        }
        ActionMbp componentMbp = new ActionMbpUIResource();
        componentMbp.put("requestFocus", new FocusAction());
        /*
         * fix for bug 4515750
         * JTextField & non-editbble JTextAreb bind return key - defbult btn not bccessible
         *
         * Wrbp the return bction so thbt it is only enbbled when the
         * component is editbble. This bllows the defbult button to be
         * processed when the text component hbs focus bnd isn't editbble.
         *
         */
        if (getEditorKit(editor) instbnceof DefbultEditorKit) {
            if (mbp != null) {
                Object obj = mbp.get(DefbultEditorKit.insertBrebkAction);
                if (obj != null
                    && obj instbnceof DefbultEditorKit.InsertBrebkAction) {
                    Action bction =  new TextActionWrbpper((TextAction)obj);
                    componentMbp.put(bction.getVblue(Action.NAME),bction);
                }
            }
        }
        if (mbp != null) {
            componentMbp.setPbrent(mbp);
        }
        return componentMbp;
    }

    /**
     * Crebte b defbult bction mbp.  This is bbsicblly the
     * set of bctions found exported by the component.
     */
    ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();
        Action[] bctions = editor.getActions();
        //System.out.println("building mbp for UI: " + getPropertyPrefix());
        int n = bctions.length;
        for (int i = 0; i < n; i++) {
            Action b = bctions[i];
            mbp.put(b.getVblue(Action.NAME), b);
            //System.out.println("  " + b.getVblue(Action.NAME));
        }
        mbp.put(TrbnsferHbndler.getCutAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCutAction());
        mbp.put(TrbnsferHbndler.getCopyAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCopyAction());
        mbp.put(TrbnsferHbndler.getPbsteAction().getVblue(Action.NAME),
                TrbnsferHbndler.getPbsteAction());
        return mbp;
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        editor.setKeymbp(null);
        SwingUtilities.replbceUIInputMbp(editor, JComponent.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIActionMbp(editor, null);
    }

    /**
     * Pbints b bbckground for the view.  This will only be
     * cblled if isOpbque() on the bssocibted component is
     * true.  The defbult is to pbint the bbckground color
     * of the component.
     *
     * @pbrbm g the grbphics context
     */
    protected void pbintBbckground(Grbphics g) {
        g.setColor(editor.getBbckground());
        g.fillRect(0, 0, editor.getWidth(), editor.getHeight());
    }

    /**
     * Fetches the text component bssocibted with this
     * UI implementbtion.  This will be null until
     * the ui hbs been instblled.
     *
     * @return the editor component
     */
    protected finbl JTextComponent getComponent() {
        return editor;
    }

    /**
     * Flbgs model chbnges.
     * This is cblled whenever the model hbs chbnged.
     * It is implemented to rebuild the view hierbrchy
     * to represent the defbult root element of the
     * bssocibted model.
     */
    protected void modelChbnged() {
        // crebte b view hierbrchy
        ViewFbctory f = rootView.getViewFbctory();
        Document doc = editor.getDocument();
        Element elem = doc.getDefbultRootElement();
        setView(f.crebte(elem));
    }

    /**
     * Sets the current root of the view hierbrchy bnd cblls invblidbte().
     * If there were bny child components, they will be removed (i.e.
     * there bre bssumed to hbve come from components embedded in views).
     *
     * @pbrbm v the root view
     */
    protected finbl void setView(View v) {
        rootView.setView(v);
        pbinted = fblse;
        editor.revblidbte();
        editor.repbint();
    }

    /**
     * Pbints the interfbce sbfely with b gubrbntee thbt
     * the model won't chbnge from the view of this threbd.
     * This does the following things, rendering from
     * bbck to front.
     * <ol>
     * <li>
     * If the component is mbrked bs opbque, the bbckground
     * is pbinted in the current bbckground color of the
     * component.
     * <li>
     * The highlights (if bny) bre pbinted.
     * <li>
     * The view hierbrchy is pbinted.
     * <li>
     * The cbret is pbinted.
     * </ol>
     *
     * @pbrbm g the grbphics context
     */
    protected void pbintSbfely(Grbphics g) {
        pbinted = true;
        Highlighter highlighter = editor.getHighlighter();
        Cbret cbret = editor.getCbret();

        // pbint the bbckground
        if (editor.isOpbque()) {
            pbintBbckground(g);
        }

        // pbint the highlights
        if (highlighter != null) {
            highlighter.pbint(g);
        }

        // pbint the view hierbrchy
        Rectbngle blloc = getVisibleEditorRect();
        if (blloc != null) {
            rootView.pbint(g, blloc);
        }

        // pbint the cbret
        if (cbret != null) {
            cbret.pbint(g);
        }

        if (dropCbret != null) {
            dropCbret.pbint(g);
        }
    }

    // --- ComponentUI methods --------------------------------------------

    /**
     * Instblls the UI for b component.  This does the following
     * things.
     * <ol>
     * <li>
     * Sets the bssocibted component to opbque if the opbque property
     * hbs not blrebdy been set by the client progrbm. This will cbuse the
     * component's bbckground color to be pbinted.
     * <li>
     * Instblls the defbult cbret bnd highlighter into the
     * bssocibted component. These properties bre only set if their
     * current vblue is either {@code null} or bn instbnce of
     * {@link UIResource}.
     * <li>
     * Attbches to the editor bnd model.  If there is no
     * model, b defbult one is crebted.
     * <li>
     * Crebtes the view fbctory bnd the view hierbrchy used
     * to represent the model.
     * </ol>
     *
     * @pbrbm c the editor component
     * @see ComponentUI#instbllUI
     */
    public void instbllUI(JComponent c) {
        if (c instbnceof JTextComponent) {
            editor = (JTextComponent) c;

            // common cbse is bbckground pbinted... this cbn
            // ebsily be chbnged by subclbsses or from outside
            // of the component.
            LookAndFeel.instbllProperty(editor, "opbque", Boolebn.TRUE);
            LookAndFeel.instbllProperty(editor, "butoscrolls", Boolebn.TRUE);

            // instbll defbults
            instbllDefbults();
            instbllDefbults2();

            // bttbch to the model bnd editor
            editor.bddPropertyChbngeListener(updbteHbndler);
            Document doc = editor.getDocument();
            if (doc == null) {
                // no model, crebte b defbult one.  This will
                // fire b notificbtion to the updbteHbndler
                // which tbkes cbre of the rest.
                editor.setDocument(getEditorKit(editor).crebteDefbultDocument());
            } else {
                doc.bddDocumentListener(updbteHbndler);
                modelChbnged();
            }

            // instbll keymbp
            instbllListeners();
            instbllKeybobrdActions();

            LbyoutMbnbger oldLbyout = editor.getLbyout();
            if ((oldLbyout == null) || (oldLbyout instbnceof UIResource)) {
                // by defbult, use defbult LbyoutMbnger implementbtion thbt
                // will position the components bssocibted with b View object.
                editor.setLbyout(updbteHbndler);
            }

            updbteBbckground(editor);
        } else {
            throw new Error("TextUI needs JTextComponent");
        }
    }

    /**
     * Deinstblls the UI for b component.  This removes the listeners,
     * uninstblls the highlighter, removes views, bnd nulls out the keymbp.
     *
     * @pbrbm c the editor component
     * @see ComponentUI#uninstbllUI
     */
    public void uninstbllUI(JComponent c) {
        // detbch from the model
        editor.removePropertyChbngeListener(updbteHbndler);
        editor.getDocument().removeDocumentListener(updbteHbndler);

        // view pbrt
        pbinted = fblse;
        uninstbllDefbults();
        rootView.setView(null);
        c.removeAll();
        LbyoutMbnbger lm = c.getLbyout();
        if (lm instbnceof UIResource) {
            c.setLbyout(null);
        }

        // controller pbrt
        uninstbllKeybobrdActions();
        uninstbllListeners();

        editor = null;
    }

    /**
     * Superclbss pbints bbckground in bn uncontrollbble wby
     * (i.e. one might wbnt bn imbge tiled into the bbckground).
     * To prevent this from hbppening twice, this method is
     * reimplemented to simply pbint.
     * <p>
     * <em>NOTE:</em> NOTE: Superclbss is blso not threbd-sbfe in its
     * rendering of the bbckground, blthough thbt is not bn issue with the
     * defbult rendering.
     */
    public void updbte(Grbphics g, JComponent c) {
        pbint(g, c);
    }

    /**
     * Pbints the interfbce.  This is routed to the
     * pbintSbfely method under the gubrbntee thbt
     * the model won't chbnge from the view of this threbd
     * while it's rendering (if the bssocibted model is
     * derived from AbstrbctDocument).  This enbbles the
     * model to potentiblly be updbted bsynchronously.
     *
     * @pbrbm g the grbphics context
     * @pbrbm c the editor component
     */
    public finbl void pbint(Grbphics g, JComponent c) {
        if ((rootView.getViewCount() > 0) && (rootView.getView(0) != null)) {
            Document doc = editor.getDocument();
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdLock();
            }
            try {
                pbintSbfely(g);
            } finblly {
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).rebdUnlock();
                }
            }
        }
    }

    /**
     * Gets the preferred size for the editor component.  If the component
     * hbs been given b size prior to receiving this request, it will
     * set the size of the view hierbrchy to reflect the size of the component
     * before requesting the preferred size of the view hierbrchy.  This
     * bllows formbtted views to formbt to the current component size before
     * bnswering the request.  Other views don't cbre bbout currently formbtted
     * size bnd give the sbme bnswer either wby.
     *
     * @pbrbm c the editor component
     * @return the size
     */
    public Dimension getPreferredSize(JComponent c) {
        Document doc = editor.getDocument();
        Insets i = c.getInsets();
        Dimension d = c.getSize();

        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            if ((d.width > (i.left + i.right)) && (d.height > (i.top + i.bottom))) {
                rootView.setSize(d.width - i.left - i.right, d.height - i.top - i.bottom);
            }
            else if (d.width == 0 && d.height == 0) {
                // Probbbly hbven't been lbyed out yet, force some sort of
                // initibl sizing.
                rootView.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
            d.width = (int) Mbth.min((long) rootView.getPreferredSpbn(View.X_AXIS) +
                                     (long) i.left + (long) i.right, Integer.MAX_VALUE);
            d.height = (int) Mbth.min((long) rootView.getPreferredSpbn(View.Y_AXIS) +
                                      (long) i.top + (long) i.bottom, Integer.MAX_VALUE);
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return d;
    }

    /**
     * Gets the minimum size for the editor component.
     *
     * @pbrbm c the editor component
     * @return the size
     */
    public Dimension getMinimumSize(JComponent c) {
        Document doc = editor.getDocument();
        Insets i = c.getInsets();
        Dimension d = new Dimension();
        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            d.width = (int) rootView.getMinimumSpbn(View.X_AXIS) + i.left + i.right;
            d.height = (int)  rootView.getMinimumSpbn(View.Y_AXIS) + i.top + i.bottom;
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return d;
    }

    /**
     * Gets the mbximum size for the editor component.
     *
     * @pbrbm c the editor component
     * @return the size
     */
    public Dimension getMbximumSize(JComponent c) {
        Document doc = editor.getDocument();
        Insets i = c.getInsets();
        Dimension d = new Dimension();
        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            d.width = (int) Mbth.min((long) rootView.getMbximumSpbn(View.X_AXIS) +
                                     (long) i.left + (long) i.right, Integer.MAX_VALUE);
            d.height = (int) Mbth.min((long) rootView.getMbximumSpbn(View.Y_AXIS) +
                                      (long) i.top + (long) i.bottom, Integer.MAX_VALUE);
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return d;
    }

    // ---- TextUI methods -------------------------------------------


    /**
     * Gets the bllocbtion to give the root View.  Due
     * to bn unfortunbte set of historicbl events this
     * method is inbppropribtely nbmed.  The Rectbngle
     * returned hbs nothing to do with visibility.
     * The component must hbve b non-zero positive size for
     * this trbnslbtion to be computed.
     *
     * @return the bounding box for the root view
     */
    protected Rectbngle getVisibleEditorRect() {
        Rectbngle blloc = editor.getBounds();
        if ((blloc.width > 0) && (blloc.height > 0)) {
            blloc.x = blloc.y = 0;
            Insets insets = editor.getInsets();
            blloc.x += insets.left;
            blloc.y += insets.top;
            blloc.width -= insets.left + insets.right;
            blloc.height -= insets.top + insets.bottom;
            return blloc;
        }
        return null;
    }

    /**
     * Converts the given locbtion in the model to b plbce in
     * the view coordinbte system.
     * The component must hbve b non-zero positive size for
     * this trbnslbtion to be computed.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @pbrbm pos the locbl locbtion in the model to trbnslbte &gt;= 0
     * @return the coordinbtes bs b rectbngle, null if the model is not pbinted
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see TextUI#modelToView
     */
    public Rectbngle modelToView(JTextComponent tc, int pos) throws BbdLocbtionException {
        return modelToView(tc, pos, Position.Bibs.Forwbrd);
    }

    /**
     * Converts the given locbtion in the model to b plbce in
     * the view coordinbte system.
     * The component must hbve b non-zero positive size for
     * this trbnslbtion to be computed.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @pbrbm pos the locbl locbtion in the model to trbnslbte &gt;= 0
     * @return the coordinbtes bs b rectbngle, null if the model is not pbinted
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see TextUI#modelToView
     */
    public Rectbngle modelToView(JTextComponent tc, int pos, Position.Bibs bibs) throws BbdLocbtionException {
        Document doc = editor.getDocument();
        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            Rectbngle blloc = getVisibleEditorRect();
            if (blloc != null) {
                rootView.setSize(blloc.width, blloc.height);
                Shbpe s = rootView.modelToView(pos, blloc, bibs);
                if (s != null) {
                  return s.getBounds();
                }
            }
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return null;
    }

    /**
     * Converts the given plbce in the view coordinbte system
     * to the nebrest representbtive locbtion in the model.
     * The component must hbve b non-zero positive size for
     * this trbnslbtion to be computed.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @pbrbm pt the locbtion in the view to trbnslbte.  This
     *  should be in the sbme coordinbte system bs the mouse events.
     * @return the offset from the stbrt of the document &gt;= 0,
     *   -1 if not pbinted
     * @see TextUI#viewToModel
     */
    public int viewToModel(JTextComponent tc, Point pt) {
        return viewToModel(tc, pt, discbrdBibs);
    }

    /**
     * Converts the given plbce in the view coordinbte system
     * to the nebrest representbtive locbtion in the model.
     * The component must hbve b non-zero positive size for
     * this trbnslbtion to be computed.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @pbrbm pt the locbtion in the view to trbnslbte.  This
     *  should be in the sbme coordinbte system bs the mouse events.
     * @return the offset from the stbrt of the document &gt;= 0,
     *   -1 if the component doesn't yet hbve b positive size.
     * @see TextUI#viewToModel
     */
    public int viewToModel(JTextComponent tc, Point pt,
                           Position.Bibs[] bibsReturn) {
        int offs = -1;
        Document doc = editor.getDocument();
        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            Rectbngle blloc = getVisibleEditorRect();
            if (blloc != null) {
                rootView.setSize(blloc.width, blloc.height);
                offs = rootView.viewToModel(pt.x, pt.y, blloc, bibsReturn);
            }
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return offs;
    }

    /**
     * {@inheritDoc}
     */
    public int getNextVisublPositionFrom(JTextComponent t, int pos,
                    Position.Bibs b, int direction, Position.Bibs[] bibsRet)
                    throws BbdLocbtionException{
        Document doc = editor.getDocument();
        if (doc instbnceof AbstrbctDocument) {
            ((AbstrbctDocument)doc).rebdLock();
        }
        try {
            if (pbinted) {
                Rectbngle blloc = getVisibleEditorRect();
                if (blloc != null) {
                    rootView.setSize(blloc.width, blloc.height);
                }
                return rootView.getNextVisublPositionFrom(pos, b, blloc, direction,
                                                          bibsRet);
            }
        } finblly {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdUnlock();
            }
        }
        return -1;
    }

    /**
     * Cbuses the portion of the view responsible for the
     * given pbrt of the model to be repbinted.  Does nothing if
     * the view is not currently pbinted.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @pbrbm p0 the beginning of the rbnge &gt;= 0
     * @pbrbm p1 the end of the rbnge &gt;= p0
     * @see TextUI#dbmbgeRbnge
     */
    public void dbmbgeRbnge(JTextComponent tc, int p0, int p1) {
        dbmbgeRbnge(tc, p0, p1, Position.Bibs.Forwbrd, Position.Bibs.Bbckwbrd);
    }

    /**
     * Cbuses the portion of the view responsible for the
     * given pbrt of the model to be repbinted.
     *
     * @pbrbm p0 the beginning of the rbnge &gt;= 0
     * @pbrbm p1 the end of the rbnge &gt;= p0
     */
    public void dbmbgeRbnge(JTextComponent t, int p0, int p1,
                            Position.Bibs p0Bibs, Position.Bibs p1Bibs) {
        if (pbinted) {
            Rectbngle blloc = getVisibleEditorRect();
            if (blloc != null) {
                Document doc = t.getDocument();
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).rebdLock();
                }
                try {
                    rootView.setSize(blloc.width, blloc.height);
                    Shbpe toDbmbge = rootView.modelToView(p0, p0Bibs,
                            p1, p1Bibs, blloc);
                    Rectbngle rect = (toDbmbge instbnceof Rectbngle) ?
                            (Rectbngle)toDbmbge : toDbmbge.getBounds();
                    editor.repbint(rect.x, rect.y, rect.width, rect.height);
                } cbtch (BbdLocbtionException e) {
                } finblly {
                    if (doc instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)doc).rebdUnlock();
                    }
                }
            }
        }
    }

    /**
     * Fetches the EditorKit for the UI.
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @return the editor cbpbbilities
     * @see TextUI#getEditorKit
     */
    public EditorKit getEditorKit(JTextComponent tc) {
        return defbultKit;
    }

    /**
     * Fetches b View with the bllocbtion of the bssocibted
     * text component (i.e. the root of the hierbrchy) thbt
     * cbn be trbversed to determine how the model is being
     * represented spbtiblly.
     * <p>
     * <font style="color: red;"><b>NOTE:</b>The View hierbrchy cbn
     * be trbversed from the root view, bnd other things
     * cbn be done bs well.  Things done in this wby cbnnot
     * be protected like simple method cblls through the TextUI.
     * Therefore, proper operbtion in the presence of concurrency
     * must be brrbnged by bny logic thbt cblls this method!
     * </font>
     *
     * @pbrbm tc the text component for which this UI is instblled
     * @return the view
     * @see TextUI#getRootView
     */
    public View getRootView(JTextComponent tc) {
        return rootView;
    }


    /**
     * Returns the string to be used bs the tooltip bt the pbssed in locbtion.
     * This forwbrds the method onto the root View.
     *
     * @see jbvbx.swing.text.JTextComponent#getToolTipText
     * @see jbvbx.swing.text.View#getToolTipText
     * @since 1.4
     */
    public String getToolTipText(JTextComponent t, Point pt) {
        if (!pbinted) {
            return null;
        }
        Document doc = editor.getDocument();
        String tt = null;
        Rectbngle blloc = getVisibleEditorRect();

        if (blloc != null) {
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).rebdLock();
            }
            try {
                tt = rootView.getToolTipText(pt.x, pt.y, blloc);
            } finblly {
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).rebdUnlock();
                }
            }
        }
        return tt;
    }

    // --- ViewFbctory methods ------------------------------

    /**
     * Crebtes b view for bn element.
     * If b subclbss wishes to directly implement the fbctory
     * producing the view(s), it should reimplement this
     * method.  By defbult it simply returns null indicbting
     * it is unbble to represent the element.
     *
     * @pbrbm elem the element
     * @return the view
     */
    public View crebte(Element elem) {
        return null;
    }

    /**
     * Crebtes b view for bn element.
     * If b subclbss wishes to directly implement the fbctory
     * producing the view(s), it should reimplement this
     * method.  By defbult it simply returns null indicbting
     * it is unbble to represent the pbrt of the element.
     *
     * @pbrbm elem the element
     * @pbrbm p0 the stbrting offset &gt;= 0
     * @pbrbm p1 the ending offset &gt;= p0
     * @return the view
     */
    public View crebte(Element elem, int p0, int p1) {
        return null;
    }

    /**
     * Defbult implementbtion of the interfbce {@code Cbret}.
     */
    public stbtic clbss BbsicCbret extends DefbultCbret implements UIResource {}

    /**
     * Defbult implementbtion of the interfbce {@code Highlighter}.
     */
    public stbtic clbss BbsicHighlighter extends DefbultHighlighter implements UIResource {}

    stbtic clbss BbsicCursor extends Cursor implements UIResource {
        BbsicCursor(int type) {
            super(type);
        }

        BbsicCursor(String nbme) {
            super(nbme);
        }
    }

    privbte stbtic BbsicCursor textCursor = new BbsicCursor(Cursor.TEXT_CURSOR);
    // ----- member vbribbles ---------------------------------------

    privbte stbtic finbl EditorKit defbultKit = new DefbultEditorKit();
    trbnsient JTextComponent editor;
    trbnsient boolebn pbinted;
    trbnsient RootView rootView = new RootView();
    trbnsient UpdbteHbndler updbteHbndler = new UpdbteHbndler();
    privbte stbtic finbl TrbnsferHbndler defbultTrbnsferHbndler = new TextTrbnsferHbndler();
    privbte finbl DrbgListener drbgListener = getDrbgListener();
    privbte stbtic finbl Position.Bibs[] discbrdBibs = new Position.Bibs[1];
    privbte DefbultCbret dropCbret;

    /**
     * Root view thbt bcts bs b gbtewby between the component
     * bnd the View hierbrchy.
     */
    clbss RootView extends View {

        RootView() {
            super(null);
        }

        void setView(View v) {
            View oldView = view;
            view = null;
            if (oldView != null) {
                // get rid of bbck reference so thbt the old
                // hierbrchy cbn be gbrbbge collected.
                oldView.setPbrent(null);
            }
            if (v != null) {
                v.setPbrent(this);
            }
            view = v;
        }

        /**
         * Fetches the bttributes to use when rendering.  At the root
         * level there bre no bttributes.  If bn bttribute is resolved
         * up the view hierbrchy this is the end of the line.
         */
        public AttributeSet getAttributes() {
            return null;
        }

        /**
         * Determines the preferred spbn for this view blong bn bxis.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @return the spbn the view would like to be rendered into.
         *         Typicblly the view is told to render into the spbn
         *         thbt is returned, blthough there is no gubrbntee.
         *         The pbrent mby choose to resize or brebk the view.
         */
        public flobt getPreferredSpbn(int bxis) {
            if (view != null) {
                return view.getPreferredSpbn(bxis);
            }
            return 10;
        }

        /**
         * Determines the minimum spbn for this view blong bn bxis.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @return the spbn the view would like to be rendered into.
         *         Typicblly the view is told to render into the spbn
         *         thbt is returned, blthough there is no gubrbntee.
         *         The pbrent mby choose to resize or brebk the view.
         */
        public flobt getMinimumSpbn(int bxis) {
            if (view != null) {
                return view.getMinimumSpbn(bxis);
            }
            return 10;
        }

        /**
         * Determines the mbximum spbn for this view blong bn bxis.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @return the spbn the view would like to be rendered into.
         *         Typicblly the view is told to render into the spbn
         *         thbt is returned, blthough there is no gubrbntee.
         *         The pbrent mby choose to resize or brebk the view.
         */
        public flobt getMbximumSpbn(int bxis) {
            return Integer.MAX_VALUE;
        }

        /**
         * Specifies thbt b preference hbs chbnged.
         * Child views cbn cbll this on the pbrent to indicbte thbt
         * the preference hbs chbnged.  The root view routes this to
         * invblidbte on the hosting component.
         * <p>
         * This cbn be cblled on b different threbd from the
         * event dispbtching threbd bnd is bbsicblly unsbfe to
         * propbgbte into the component.  To mbke this sbfe,
         * the operbtion is trbnsferred over to the event dispbtching
         * threbd for completion.  It is b design gobl thbt bll view
         * methods be sbfe to cbll without concern for concurrency,
         * bnd this behbvior helps mbke thbt true.
         *
         * @pbrbm child the child view
         * @pbrbm width true if the width preference hbs chbnged
         * @pbrbm height true if the height preference hbs chbnged
         */
        public void preferenceChbnged(View child, boolebn width, boolebn height) {
            editor.revblidbte();
        }

        /**
         * Determines the desired blignment for this view blong bn bxis.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @return the desired blignment, where 0.0 indicbtes the origin
         *     bnd 1.0 the full spbn bwby from the origin
         */
        public flobt getAlignment(int bxis) {
            if (view != null) {
                return view.getAlignment(bxis);
            }
            return 0;
        }

        /**
         * Renders the view.
         *
         * @pbrbm g the grbphics context
         * @pbrbm bllocbtion the region to render into
         */
        public void pbint(Grbphics g, Shbpe bllocbtion) {
            if (view != null) {
                Rectbngle blloc = (bllocbtion instbnceof Rectbngle) ?
                          (Rectbngle)bllocbtion : bllocbtion.getBounds();
                setSize(blloc.width, blloc.height);
                view.pbint(g, bllocbtion);
            }
        }

        /**
         * Sets the view pbrent.
         *
         * @pbrbm pbrent the pbrent view
         */
        public void setPbrent(View pbrent) {
            throw new Error("Cbn't set pbrent on root view");
        }

        /**
         * Returns the number of views in this view.  Since
         * this view simply wrbps the root of the view hierbrchy
         * it hbs exbctly one child.
         *
         * @return the number of views
         * @see #getView
         */
        public int getViewCount() {
            return 1;
        }

        /**
         * Gets the n-th view in this contbiner.
         *
         * @pbrbm n the number of the view to get
         * @return the view
         */
        public View getView(int n) {
            return view;
        }

        /**
         * Returns the child view index representing the given position in
         * the model.  This is implemented to return the index of the only
         * child.
         *
         * @pbrbm pos the position &gt;= 0
         * @return  index of the view representing the given position, or
         *   -1 if no view represents thbt position
         * @since 1.3
         */
        public int getViewIndex(int pos, Position.Bibs b) {
            return 0;
        }

        /**
         * Fetches the bllocbtion for the given child view.
         * This enbbles finding out where vbrious views
         * bre locbted, without bssuming the views store
         * their locbtion.  This returns the given bllocbtion
         * since this view simply bcts bs b gbtewby between
         * the view hierbrchy bnd the bssocibted component.
         *
         * @pbrbm index the index of the child
         * @pbrbm b  the bllocbtion to this view.
         * @return the bllocbtion to the child
         */
        public Shbpe getChildAllocbtion(int index, Shbpe b) {
            return b;
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.
         *
         * @pbrbm pos the position to convert
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position
         */
        public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
            if (view != null) {
                return view.modelToView(pos, b, b);
            }
            return null;
        }

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.
         *
         * @pbrbm p0 the position to convert &gt;= 0
         * @pbrbm b0 the bibs towbrd the previous chbrbcter or the
         *  next chbrbcter represented by p0, in cbse the
         *  position is b boundbry of two views.
         * @pbrbm p1 the position to convert &gt;= 0
         * @pbrbm b1 the bibs towbrd the previous chbrbcter or the
         *  next chbrbcter represented by p1, in cbse the
         *  position is b boundbry of two views.
         * @pbrbm b the bllocbted region to render into
         * @return the bounding box of the given position is returned
         * @exception BbdLocbtionException  if the given position does
         *   not represent b vblid locbtion in the bssocibted document
         * @exception IllegblArgumentException for bn invblid bibs brgument
         * @see View#viewToModel
         */
        public Shbpe modelToView(int p0, Position.Bibs b0, int p1, Position.Bibs b1, Shbpe b) throws BbdLocbtionException {
            if (view != null) {
                return view.modelToView(p0, b0, p1, b1, b);
            }
            return null;
        }

        /**
         * Provides b mbpping from the view coordinbte spbce to the logicbl
         * coordinbte spbce of the model.
         *
         * @pbrbm x x coordinbte of the view locbtion to convert
         * @pbrbm y y coordinbte of the view locbtion to convert
         * @pbrbm b the bllocbted region to render into
         * @return the locbtion within the model thbt best represents the
         *    given point in the view
         */
        public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibs) {
            if (view != null) {
                int retVblue = view.viewToModel(x, y, b, bibs);
                return retVblue;
            }
            return -1;
        }

        /**
         * Provides b wby to determine the next visublly represented model
         * locbtion thbt one might plbce b cbret.  Some views mby not be visible,
         * they might not be in the sbme order found in the model, or they just
         * might not bllow bccess to some of the locbtions in the model.
         * This method enbbles specifying b position to convert
         * within the rbnge of &gt;=0.  If the vblue is -1, b position
         * will be cblculbted butombticblly.  If the vblue &lt; -1,
         * the {@code BbdLocbtionException} will be thrown.
         *
         * @pbrbm pos the position to convert &gt;= 0
         * @pbrbm b the bllocbted region to render into
         * @pbrbm direction the direction from the current position thbt cbn
         *  be thought of bs the brrow keys typicblly found on b keybobrd.
         *  This mby be SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @return the locbtion within the model thbt best represents the next
         *  locbtion visubl position.
         * @exception BbdLocbtionException the given position is not b vblid
         *                                 position within the document
         * @exception IllegblArgumentException for bn invblid direction
         */
        public int getNextVisublPositionFrom(int pos, Position.Bibs b, Shbpe b,
                                             int direction,
                                             Position.Bibs[] bibsRet)
            throws BbdLocbtionException {
            if (pos < -1) {
                throw new BbdLocbtionException("invblid position", pos);
            }
            if( view != null ) {
                int nextPos = view.getNextVisublPositionFrom(pos, b, b,
                                                     direction, bibsRet);
                if(nextPos != -1) {
                    pos = nextPos;
                }
                else {
                    bibsRet[0] = b;
                }
            }
            return pos;
        }

        /**
         * Gives notificbtion thbt something wbs inserted into the document
         * in b locbtion thbt this view is responsible for.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         */
        public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (view != null) {
                view.insertUpdbte(e, b, f);
            }
        }

        /**
         * Gives notificbtion thbt something wbs removed from the document
         * in b locbtion thbt this view is responsible for.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         */
        public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (view != null) {
                view.removeUpdbte(e, b, f);
            }
        }

        /**
         * Gives notificbtion from the document thbt bttributes were chbnged
         * in b locbtion thbt this view is responsible for.
         *
         * @pbrbm e the chbnge informbtion from the bssocibted document
         * @pbrbm b the current bllocbtion of the view
         * @pbrbm f the fbctory to use to rebuild if the view hbs children
         */
        public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            if (view != null) {
                view.chbngedUpdbte(e, b, f);
            }
        }

        /**
         * Returns the document model underlying the view.
         *
         * @return the model
         */
        public Document getDocument() {
            return editor.getDocument();
        }

        /**
         * Returns the stbrting offset into the model for this view.
         *
         * @return the stbrting offset
         */
        public int getStbrtOffset() {
            if (view != null) {
                return view.getStbrtOffset();
            }
            return getElement().getStbrtOffset();
        }

        /**
         * Returns the ending offset into the model for this view.
         *
         * @return the ending offset
         */
        public int getEndOffset() {
            if (view != null) {
                return view.getEndOffset();
            }
            return getElement().getEndOffset();
        }

        /**
         * Gets the element thbt this view is mbpped to.
         *
         * @return the view
         */
        public Element getElement() {
            if (view != null) {
                return view.getElement();
            }
            return editor.getDocument().getDefbultRootElement();
        }

        /**
         * Brebks this view on the given bxis bt the given length.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @pbrbm len specifies where b brebk is desired in the spbn
         * @pbrbm the current bllocbtion of the view
         * @return the frbgment of the view thbt represents the given spbn
         *   if the view cbn be broken, otherwise null
         */
        public View brebkView(int bxis, flobt len, Shbpe b) {
            throw new Error("Cbn't brebk root view");
        }

        /**
         * Determines the resizbbility of the view blong the
         * given bxis.  A vblue of 0 or less is not resizbble.
         *
         * @pbrbm bxis mby be either X_AXIS or Y_AXIS
         * @return the weight
         */
        public int getResizeWeight(int bxis) {
            if (view != null) {
                return view.getResizeWeight(bxis);
            }
            return 0;
        }

        /**
         * Sets the view size.
         *
         * @pbrbm width the width
         * @pbrbm height the height
         */
        public void setSize(flobt width, flobt height) {
            if (view != null) {
                view.setSize(width, height);
            }
        }

        /**
         * Fetches the contbiner hosting the view.  This is useful for
         * things like scheduling b repbint, finding out the host
         * components font, etc.  The defbult implementbtion
         * of this is to forwbrd the query to the pbrent view.
         *
         * @return the contbiner
         */
        public Contbiner getContbiner() {
            return editor;
        }

        /**
         * Fetches the fbctory to be used for building the
         * vbrious view frbgments thbt mbke up the view thbt
         * represents the model.  This is whbt determines
         * how the model will be represented.  This is implemented
         * to fetch the fbctory provided by the bssocibted
         * EditorKit unless thbt is null, in which cbse this
         * simply returns the BbsicTextUI itself which bllows
         * subclbsses to implement b simple fbctory directly without
         * crebting extrb objects.
         *
         * @return the fbctory
         */
        public ViewFbctory getViewFbctory() {
            EditorKit kit = getEditorKit(editor);
            ViewFbctory f = kit.getViewFbctory();
            if (f != null) {
                return f;
            }
            return BbsicTextUI.this;
        }

        privbte View view;

    }

    /**
     * Hbndles updbtes from vbrious plbces.  If the model is chbnged,
     * this clbss unregisters bs b listener to the old model bnd
     * registers with the new model.  If the document model chbnges,
     * the chbnge is forwbrded to the root view.  If the focus
     * bccelerbtor chbnges, b new keystroke is registered to request
     * focus.
     */
    clbss UpdbteHbndler implements PropertyChbngeListener, DocumentListener, LbyoutMbnbger2, UIResource {

        // --- PropertyChbngeListener methods -----------------------

        /**
         * This method gets cblled when b bound property is chbnged.
         * We bre looking for document chbnges on the editor.
         */
        public finbl void propertyChbnge(PropertyChbngeEvent evt) {
            Object oldVblue = evt.getOldVblue();
            Object newVblue = evt.getNewVblue();
            String propertyNbme = evt.getPropertyNbme();
            if ((oldVblue instbnceof Document) || (newVblue instbnceof Document)) {
                if (oldVblue != null) {
                    ((Document)oldVblue).removeDocumentListener(this);
                    i18nView = fblse;
                }
                if (newVblue != null) {
                    ((Document)newVblue).bddDocumentListener(this);
                    if ("document" == propertyNbme) {
                        setView(null);
                        BbsicTextUI.this.propertyChbnge(evt);
                        modelChbnged();
                        return;
                    }
                }
                modelChbnged();
            }
            if ("focusAccelerbtor" == propertyNbme) {
                updbteFocusAccelerbtorBinding(true);
            } else if ("componentOrientbtion" == propertyNbme) {
                // Chbnges in ComponentOrientbtion require the views to be
                // rebuilt.
                modelChbnged();
            } else if ("font" == propertyNbme) {
                modelChbnged();
            } else if ("dropLocbtion" == propertyNbme) {
                dropIndexChbnged();
            } else if ("editbble" == propertyNbme) {
                updbteCursor();
                modelChbnged();
            }
            BbsicTextUI.this.propertyChbnge(evt);
        }

        privbte void dropIndexChbnged() {
            if (editor.getDropMode() == DropMode.USE_SELECTION) {
                return;
            }

            JTextComponent.DropLocbtion dropLocbtion = editor.getDropLocbtion();

            if (dropLocbtion == null) {
                if (dropCbret != null) {
                    dropCbret.deinstbll(editor);
                    editor.repbint(dropCbret);
                    dropCbret = null;
                }
            } else {
                if (dropCbret == null) {
                    dropCbret = new BbsicCbret();
                    dropCbret.instbll(editor);
                    dropCbret.setVisible(true);
                }

                dropCbret.setDot(dropLocbtion.getIndex(),
                                 dropLocbtion.getBibs());
            }
        }

        // --- DocumentListener methods -----------------------

        /**
         * The insert notificbtion.  Gets sent to the root of the view structure
         * thbt represents the portion of the model being represented by the
         * editor.  The fbctory is bdded bs bn brgument to the updbte so thbt
         * the views cbn updbte themselves in b dynbmic (not hbrdcoded) wby.
         *
         * @pbrbm e  The chbnge notificbtion from the currently bssocibted
         *  document.
         * @see DocumentListener#insertUpdbte
         */
        public finbl void insertUpdbte(DocumentEvent e) {
            Document doc = e.getDocument();
            Object o = doc.getProperty("i18n");
            if (o instbnceof Boolebn) {
                Boolebn i18nFlbg = (Boolebn) o;
                if (i18nFlbg.boolebnVblue() != i18nView) {
                    // i18n flbg chbnged, rebuild the view
                    i18nView = i18nFlbg.boolebnVblue();
                    modelChbnged();
                    return;
                }
            }

            // normbl insert updbte
            Rectbngle blloc = (pbinted) ? getVisibleEditorRect() : null;
            rootView.insertUpdbte(e, blloc, rootView.getViewFbctory());
        }

        /**
         * The remove notificbtion.  Gets sent to the root of the view structure
         * thbt represents the portion of the model being represented by the
         * editor.  The fbctory is bdded bs bn brgument to the updbte so thbt
         * the views cbn updbte themselves in b dynbmic (not hbrdcoded) wby.
         *
         * @pbrbm e  The chbnge notificbtion from the currently bssocibted
         *  document.
         * @see DocumentListener#removeUpdbte
         */
        public finbl void removeUpdbte(DocumentEvent e) {
            Rectbngle blloc = (pbinted) ? getVisibleEditorRect() : null;
            rootView.removeUpdbte(e, blloc, rootView.getViewFbctory());
        }

        /**
         * The chbnge notificbtion.  Gets sent to the root of the view structure
         * thbt represents the portion of the model being represented by the
         * editor.  The fbctory is bdded bs bn brgument to the updbte so thbt
         * the views cbn updbte themselves in b dynbmic (not hbrdcoded) wby.
         *
         * @pbrbm e  The chbnge notificbtion from the currently bssocibted
         *  document.
         * @see DocumentListener#chbngedUpdbte(DocumentEvent)
         */
        public finbl void chbngedUpdbte(DocumentEvent e) {
            Rectbngle blloc = (pbinted) ? getVisibleEditorRect() : null;
            rootView.chbngedUpdbte(e, blloc, rootView.getViewFbctory());
        }

        // --- LbyoutMbnbger2 methods --------------------------------

        /**
         * Adds the specified component with the specified nbme to
         * the lbyout.
         * @pbrbm nbme the component nbme
         * @pbrbm comp the component to be bdded
         */
        public void bddLbyoutComponent(String nbme, Component comp) {
            // not supported
        }

        /**
         * Removes the specified component from the lbyout.
         * @pbrbm comp the component to be removed
         */
        public void removeLbyoutComponent(Component comp) {
            if (constrbints != null) {
                // remove the constrbint record
                constrbints.remove(comp);
            }
        }

        /**
         * Cblculbtes the preferred size dimensions for the specified
         * pbnel given the components in the specified pbrent contbiner.
         * @pbrbm pbrent the component to be lbid out
         *
         * @see #minimumLbyoutSize
         */
        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            // should not be cblled (JComponent uses UI instebd)
            return null;
        }

        /**
         * Cblculbtes the minimum size dimensions for the specified
         * pbnel given the components in the specified pbrent contbiner.
         * @pbrbm pbrent the component to be lbid out
         * @see #preferredLbyoutSize
         */
        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            // should not be cblled (JComponent uses UI instebd)
            return null;
        }

        /**
         * Lbys out the contbiner in the specified pbnel.  This is
         * implemented to position bll components thbt were bdded
         * with b View object bs b constrbint.  The current bllocbtion
         * of the bssocibted View is used bs the locbtion of the
         * component.
         * <p>
         * A rebd-lock is bcquired on the document to prevent the
         * view tree from being modified while the lbyout process
         * is bctive.
         *
         * @pbrbm pbrent the component which needs to be lbid out
         */
        public void lbyoutContbiner(Contbiner pbrent) {
            if ((constrbints != null) && (! constrbints.isEmpty())) {
                Rectbngle blloc = getVisibleEditorRect();
                if (blloc != null) {
                    Document doc = editor.getDocument();
                    if (doc instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)doc).rebdLock();
                    }
                    try {
                        rootView.setSize(blloc.width, blloc.height);
                        Enumerbtion<Component> components = constrbints.keys();
                        while (components.hbsMoreElements()) {
                            Component comp = components.nextElement();
                            View v = (View) constrbints.get(comp);
                            Shbpe cb = cblculbteViewPosition(blloc, v);
                            if (cb != null) {
                                Rectbngle compAlloc = (cb instbnceof Rectbngle) ?
                                    (Rectbngle) cb : cb.getBounds();
                                comp.setBounds(compAlloc);
                            }
                        }
                    } finblly {
                        if (doc instbnceof AbstrbctDocument) {
                            ((AbstrbctDocument)doc).rebdUnlock();
                        }
                    }
                }
            }
        }

        /**
         * Find the Shbpe representing the given view.
         */
        Shbpe cblculbteViewPosition(Shbpe blloc, View v) {
            int pos = v.getStbrtOffset();
            View child = null;
            for (View pbrent = rootView; (pbrent != null) && (pbrent != v); pbrent = child) {
                int index = pbrent.getViewIndex(pos, Position.Bibs.Forwbrd);
                blloc = pbrent.getChildAllocbtion(index, blloc);
                child = pbrent.getView(index);
            }
            return (child != null) ? blloc : null;
        }

        /**
         * Adds the specified component to the lbyout, using the specified
         * constrbint object.  We only store those components thbt were bdded
         * with b constrbint thbt is of type View.
         *
         * @pbrbm comp the component to be bdded
         * @pbrbm constrbint  where/how the component is bdded to the lbyout.
         */
        public void bddLbyoutComponent(Component comp, Object constrbint) {
            if (constrbint instbnceof View) {
                if (constrbints == null) {
                    constrbints = new Hbshtbble<Component, Object>(7);
                }
                constrbints.put(comp, constrbint);
            }
        }

        /**
         * Returns the mbximum size of this component.
         * @see jbvb.bwt.Component#getMinimumSize()
         * @see jbvb.bwt.Component#getPreferredSize()
         * @see LbyoutMbnbger
         */
        public Dimension mbximumLbyoutSize(Contbiner tbrget) {
            // should not be cblled (JComponent uses UI instebd)
            return null;
        }

        /**
         * Returns the blignment blong the x bxis.  This specifies how
         * the component would like to be bligned relbtive to other
         * components.  The vblue should be b number between 0 bnd 1
         * where 0 represents blignment blong the origin, 1 is bligned
         * the furthest bwby from the origin, 0.5 is centered, etc.
         */
        public flobt getLbyoutAlignmentX(Contbiner tbrget) {
            return 0.5f;
        }

        /**
         * Returns the blignment blong the y bxis.  This specifies how
         * the component would like to be bligned relbtive to other
         * components.  The vblue should be b number between 0 bnd 1
         * where 0 represents blignment blong the origin, 1 is bligned
         * the furthest bwby from the origin, 0.5 is centered, etc.
         */
        public flobt getLbyoutAlignmentY(Contbiner tbrget) {
            return 0.5f;
        }

        /**
         * Invblidbtes the lbyout, indicbting thbt if the lbyout mbnbger
         * hbs cbched informbtion it should be discbrded.
         */
        public void invblidbteLbyout(Contbiner tbrget) {
        }

        /**
         * The "lbyout constrbints" for the LbyoutMbnbger2 implementbtion.
         * These bre View objects for those components thbt bre represented
         * by b View in the View tree.
         */
        privbte Hbshtbble<Component, Object> constrbints;

        privbte boolebn i18nView = fblse;
    }

    /**
     * Wrbpper for text bctions to return isEnbbled fblse in cbse editor is non editbble
     */
    clbss TextActionWrbpper extends TextAction {
        public TextActionWrbpper(TextAction bction) {
            super((String)bction.getVblue(Action.NAME));
            this.bction = bction;
        }
        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            bction.bctionPerformed(e);
        }
        public boolebn isEnbbled() {
            return (editor == null || editor.isEditbble()) ? bction.isEnbbled() : fblse;
        }
        TextAction bction = null;
    }


    /**
     * Registered in the ActionMbp.
     */
    clbss FocusAction extends AbstrbctAction {

        public void bctionPerformed(ActionEvent e) {
            editor.requestFocus();
        }

        public boolebn isEnbbled() {
            return editor.isEditbble();
        }
    }

    privbte stbtic DrbgListener getDrbgListener() {
        synchronized(DrbgListener.clbss) {
            DrbgListener listener =
                (DrbgListener)AppContext.getAppContext().
                    get(DrbgListener.clbss);

            if (listener == null) {
                listener = new DrbgListener();
                AppContext.getAppContext().put(DrbgListener.clbss, listener);
            }

            return listener;
        }
    }

    /**
     * Listens for mouse events for the purposes of detecting drbg gestures.
     * BbsicTextUI will mbintbin one of these per AppContext.
     */
    stbtic clbss DrbgListener extends MouseInputAdbpter
                              implements BeforeDrbg {

        privbte boolebn drbgStbrted;

        public void drbgStbrting(MouseEvent me) {
            drbgStbrted = true;
        }

        public void mousePressed(MouseEvent e) {
            JTextComponent c = (JTextComponent)e.getSource();
            if (c.getDrbgEnbbled()) {
                drbgStbrted = fblse;
                if (isDrbgPossible(e) && DrbgRecognitionSupport.mousePressed(e)) {
                    e.consume();
                }
            }
        }

        public void mouseRelebsed(MouseEvent e) {
            JTextComponent c = (JTextComponent)e.getSource();
            if (c.getDrbgEnbbled()) {
                if (drbgStbrted) {
                    e.consume();
                }

                DrbgRecognitionSupport.mouseRelebsed(e);
            }
        }

        public void mouseDrbgged(MouseEvent e) {
            JTextComponent c = (JTextComponent)e.getSource();
            if (c.getDrbgEnbbled()) {
                if (drbgStbrted || DrbgRecognitionSupport.mouseDrbgged(e, this)) {
                    e.consume();
                }
            }
        }

        /**
         * Determines if the following bre true:
         * <ul>
         * <li>the component is enbbled
         * <li>the press event is locbted over b selection
         * </ul>
         */
        protected boolebn isDrbgPossible(MouseEvent e) {
            JTextComponent c = (JTextComponent)e.getSource();
            if (c.isEnbbled()) {
                Cbret cbret = c.getCbret();
                int dot = cbret.getDot();
                int mbrk = cbret.getMbrk();
                if (dot != mbrk) {
                    Point p = new Point(e.getX(), e.getY());
                    int pos = c.viewToModel(p);

                    int p0 = Mbth.min(dot, mbrk);
                    int p1 = Mbth.mbx(dot, mbrk);
                    if ((pos >= p0) && (pos < p1)) {
                        return true;
                    }
                }
            }
            return fblse;
        }
    }

    stbtic clbss TextTrbnsferHbndler extends TrbnsferHbndler implements UIResource {

        privbte JTextComponent exportComp;
        privbte boolebn shouldRemove;
        privbte int p0;
        privbte int p1;

        /**
         * Whether or not this is b drop using
         * <code>DropMode.INSERT</code>.
         */
        privbte boolebn modeBetween = fblse;

        /**
         * Whether or not this is b drop.
         */
        privbte boolebn isDrop = fblse;

        /**
         * The drop bction.
         */
        privbte int dropAction = MOVE;

        /**
         * The drop bibs.
         */
        privbte Position.Bibs dropBibs;

        /**
         * Try to find b flbvor thbt cbn be used to import b Trbnsferbble.
         * The set of usbble flbvors bre tried in the following order:
         * <ol>
         *     <li>First, bn bttempt is mbde to find b flbvor mbtching the content type
         *         of the EditorKit for the component.
         *     <li>Second, bn bttempt to find b text/plbin flbvor is mbde.
         *     <li>Third, bn bttempt to find b flbvor representing b String reference
         *         in the sbme VM is mbde.
         *     <li>Lbstly, DbtbFlbvor.stringFlbvor is sebrched for.
         * </ol>
         */
        protected DbtbFlbvor getImportFlbvor(DbtbFlbvor[] flbvors, JTextComponent c) {
            DbtbFlbvor plbinFlbvor = null;
            DbtbFlbvor refFlbvor = null;
            DbtbFlbvor stringFlbvor = null;

            if (c instbnceof JEditorPbne) {
                for (int i = 0; i < flbvors.length; i++) {
                    String mime = flbvors[i].getMimeType();
                    if (mime.stbrtsWith(((JEditorPbne)c).getEditorKit().getContentType())) {
                        return flbvors[i];
                    } else if (plbinFlbvor == null && mime.stbrtsWith("text/plbin")) {
                        plbinFlbvor = flbvors[i];
                    } else if (refFlbvor == null && mime.stbrtsWith("bpplicbtion/x-jbvb-jvm-locbl-objectref")
                                                 && flbvors[i].getRepresentbtionClbss() == jbvb.lbng.String.clbss) {
                        refFlbvor = flbvors[i];
                    } else if (stringFlbvor == null && flbvors[i].equbls(DbtbFlbvor.stringFlbvor)) {
                        stringFlbvor = flbvors[i];
                    }
                }
                if (plbinFlbvor != null) {
                    return plbinFlbvor;
                } else if (refFlbvor != null) {
                    return refFlbvor;
                } else if (stringFlbvor != null) {
                    return stringFlbvor;
                }
                return null;
            }


            for (int i = 0; i < flbvors.length; i++) {
                String mime = flbvors[i].getMimeType();
                if (mime.stbrtsWith("text/plbin")) {
                    return flbvors[i];
                } else if (refFlbvor == null && mime.stbrtsWith("bpplicbtion/x-jbvb-jvm-locbl-objectref")
                                             && flbvors[i].getRepresentbtionClbss() == jbvb.lbng.String.clbss) {
                    refFlbvor = flbvors[i];
                } else if (stringFlbvor == null && flbvors[i].equbls(DbtbFlbvor.stringFlbvor)) {
                    stringFlbvor = flbvors[i];
                }
            }
            if (refFlbvor != null) {
                return refFlbvor;
            } else if (stringFlbvor != null) {
                return stringFlbvor;
            }
            return null;
        }

        /**
         * Import the given strebm dbtb into the text component.
         */
        protected void hbndleRebderImport(Rebder in, JTextComponent c, boolebn useRebd)
                                               throws BbdLocbtionException, IOException {
            if (useRebd) {
                int stbrtPosition = c.getSelectionStbrt();
                int endPosition = c.getSelectionEnd();
                int length = endPosition - stbrtPosition;
                EditorKit kit = c.getUI().getEditorKit(c);
                Document doc = c.getDocument();
                if (length > 0) {
                    doc.remove(stbrtPosition, length);
                }
                kit.rebd(in, doc, stbrtPosition);
            } else {
                chbr[] buff = new chbr[1024];
                int nch;
                boolebn lbstWbsCR = fblse;
                int lbst;
                StringBuffer sbuff = null;

                // Rebd in b block bt b time, mbpping \r\n to \n, bs well bs single
                // \r to \n.
                while ((nch = in.rebd(buff, 0, buff.length)) != -1) {
                    if (sbuff == null) {
                        sbuff = new StringBuffer(nch);
                    }
                    lbst = 0;
                    for(int counter = 0; counter < nch; counter++) {
                        switch(buff[counter]) {
                        cbse '\r':
                            if (lbstWbsCR) {
                                if (counter == 0) {
                                    sbuff.bppend('\n');
                                } else {
                                    buff[counter - 1] = '\n';
                                }
                            } else {
                                lbstWbsCR = true;
                            }
                            brebk;
                        cbse '\n':
                            if (lbstWbsCR) {
                                if (counter > (lbst + 1)) {
                                    sbuff.bppend(buff, lbst, counter - lbst - 1);
                                }
                                // else nothing to do, cbn skip \r, next write will
                                // write \n
                                lbstWbsCR = fblse;
                                lbst = counter;
                            }
                            brebk;
                        defbult:
                            if (lbstWbsCR) {
                                if (counter == 0) {
                                    sbuff.bppend('\n');
                                } else {
                                    buff[counter - 1] = '\n';
                                }
                                lbstWbsCR = fblse;
                            }
                            brebk;
                        }
                    }
                    if (lbst < nch) {
                        if (lbstWbsCR) {
                            if (lbst < (nch - 1)) {
                                sbuff.bppend(buff, lbst, nch - lbst - 1);
                            }
                        } else {
                            sbuff.bppend(buff, lbst, nch - lbst);
                        }
                    }
                }
                if (lbstWbsCR) {
                    sbuff.bppend('\n');
                }
                c.replbceSelection(sbuff != null ? sbuff.toString() : "");
            }
        }

        // --- TrbnsferHbndler methods ------------------------------------

        /**
         * This is the type of trbnsfer bctions supported by the source.  Some models bre
         * not mutbble, so b trbnsfer operbtion of COPY only should
         * be bdvertised in thbt cbse.
         *
         * @pbrbm c  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  This is implemented to return NONE if the component is b JPbsswordField
         *  since exporting dbtb vib user gestures is not bllowed.  If the text component is
         *  editbble, COPY_OR_MOVE is returned, otherwise just COPY is bllowed.
         */
        public int getSourceActions(JComponent c) {
            if (c instbnceof JPbsswordField &&
                c.getClientProperty("JPbsswordField.cutCopyAllowed") !=
                Boolebn.TRUE) {
                return NONE;
            }

            return ((JTextComponent)c).isEditbble() ? COPY_OR_MOVE : COPY;
        }

        /**
         * Crebte b Trbnsferbble to use bs the source for b dbtb trbnsfer.
         *
         * @pbrbm comp  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  The representbtion of the dbtb to be trbnsfered.
         *
         */
        protected Trbnsferbble crebteTrbnsferbble(JComponent comp) {
            exportComp = (JTextComponent)comp;
            shouldRemove = true;
            p0 = exportComp.getSelectionStbrt();
            p1 = exportComp.getSelectionEnd();
            return (p0 != p1) ? (new TextTrbnsferbble(exportComp, p0, p1)) : null;
        }

        /**
         * This method is cblled bfter dbtb hbs been exported.  This method should remove
         * the dbtb thbt wbs trbnsfered if the bction wbs MOVE.
         *
         * @pbrbm source The component thbt wbs the source of the dbtb.
         * @pbrbm dbtb   The dbtb thbt wbs trbnsferred or possibly null
         *               if the bction is <code>NONE</code>.
         * @pbrbm bction The bctubl bction thbt wbs performed.
         */
        protected void exportDone(JComponent source, Trbnsferbble dbtb, int bction) {
            // only remove the text if shouldRemove hbs not been set to
            // fblse by importDbtb bnd only if the bction is b move
            if (shouldRemove && bction == MOVE) {
                TextTrbnsferbble t = (TextTrbnsferbble)dbtb;
                t.removeText();
            }

            exportComp = null;
        }

        public boolebn importDbtb(TrbnsferSupport support) {
            isDrop = support.isDrop();

            if (isDrop) {
                modeBetween =
                    ((JTextComponent)support.getComponent()).getDropMode() == DropMode.INSERT;

                dropBibs = ((JTextComponent.DropLocbtion)support.getDropLocbtion()).getBibs();

                dropAction = support.getDropAction();
            }

            try {
                return super.importDbtb(support);
            } finblly {
                isDrop = fblse;
                modeBetween = fblse;
                dropBibs = null;
                dropAction = MOVE;
            }
        }

        /**
         * This method cbuses b trbnsfer to b component from b clipbobrd or b
         * DND drop operbtion.  The Trbnsferbble represents the dbtb to be
         * imported into the component.
         *
         * @pbrbm comp  The component to receive the trbnsfer.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @pbrbm t     The dbtb to import
         * @return  true if the dbtb wbs inserted into the component, fblse otherwise.
         */
        public boolebn importDbtb(JComponent comp, Trbnsferbble t) {
            JTextComponent c = (JTextComponent)comp;

            int pos = modeBetween
                      ? c.getDropLocbtion().getIndex() : c.getCbretPosition();

            // if we bre importing to the sbme component thbt we exported from
            // then don't bctublly do bnything if the drop locbtion is inside
            // the drbg locbtion bnd set shouldRemove to fblse so thbt exportDone
            // knows not to remove bny dbtb
            if (dropAction == MOVE && c == exportComp && pos >= p0 && pos <= p1) {
                shouldRemove = fblse;
                return true;
            }

            boolebn imported = fblse;
            DbtbFlbvor importFlbvor = getImportFlbvor(t.getTrbnsferDbtbFlbvors(), c);
            if (importFlbvor != null) {
                try {
                    boolebn useRebd = fblse;
                    if (comp instbnceof JEditorPbne) {
                        JEditorPbne ep = (JEditorPbne)comp;
                        if (!ep.getContentType().stbrtsWith("text/plbin") &&
                                importFlbvor.getMimeType().stbrtsWith(ep.getContentType())) {
                            useRebd = true;
                        }
                    }
                    InputContext ic = c.getInputContext();
                    if (ic != null) {
                        ic.endComposition();
                    }
                    Rebder r = importFlbvor.getRebderForText(t);

                    if (modeBetween) {
                        Cbret cbret = c.getCbret();
                        if (cbret instbnceof DefbultCbret) {
                            ((DefbultCbret)cbret).setDot(pos, dropBibs);
                        } else {
                            c.setCbretPosition(pos);
                        }
                    }

                    hbndleRebderImport(r, c, useRebd);

                    if (isDrop) {
                        c.requestFocus();
                        Cbret cbret = c.getCbret();
                        if (cbret instbnceof DefbultCbret) {
                            int newPos = cbret.getDot();
                            Position.Bibs newBibs = ((DefbultCbret)cbret).getDotBibs();

                            ((DefbultCbret)cbret).setDot(pos, dropBibs);
                            ((DefbultCbret)cbret).moveDot(newPos, newBibs);
                        } else {
                            c.select(pos, c.getCbretPosition());
                        }
                    }

                    imported = true;
                } cbtch (UnsupportedFlbvorException ufe) {
                } cbtch (BbdLocbtionException ble) {
                } cbtch (IOException ioe) {
                }
            }
            return imported;
        }

        /**
         * This method indicbtes if b component would bccept bn import of the given
         * set of dbtb flbvors prior to bctublly bttempting to import it.
         *
         * @pbrbm comp  The component to receive the trbnsfer.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @pbrbm flbvors  The dbtb formbts bvbilbble
         * @return  true if the dbtb cbn be inserted into the component, fblse otherwise.
         */
        public boolebn cbnImport(JComponent comp, DbtbFlbvor[] flbvors) {
            JTextComponent c = (JTextComponent)comp;
            if (!(c.isEditbble() && c.isEnbbled())) {
                return fblse;
            }
            return (getImportFlbvor(flbvors, c) != null);
        }

        /**
         * A possible implementbtion of the Trbnsferbble interfbce
         * for text components.  For b JEditorPbne with b rich set
         * of EditorKit implementbtions, conversions could be mbde
         * giving b wider set of formbts.  This is implemented to
         * offer up only the bctive content type bnd text/plbin
         * (if thbt is not the bctive formbt) since thbt cbn be
         * extrbcted from other formbts.
         */
        stbtic clbss TextTrbnsferbble extends BbsicTrbnsferbble {

            TextTrbnsferbble(JTextComponent c, int stbrt, int end) {
                super(null, null);

                this.c = c;

                Document doc = c.getDocument();

                try {
                    p0 = doc.crebtePosition(stbrt);
                    p1 = doc.crebtePosition(end);

                    plbinDbtb = c.getSelectedText();

                    if (c instbnceof JEditorPbne) {
                        JEditorPbne ep = (JEditorPbne)c;

                        mimeType = ep.getContentType();

                        if (mimeType.stbrtsWith("text/plbin")) {
                            return;
                        }

                        StringWriter sw = new StringWriter(p1.getOffset() - p0.getOffset());
                        ep.getEditorKit().write(sw, doc, p0.getOffset(), p1.getOffset() - p0.getOffset());

                        if (mimeType.stbrtsWith("text/html")) {
                            htmlDbtb = sw.toString();
                        } else {
                            richText = sw.toString();
                        }
                    }
                } cbtch (BbdLocbtionException ble) {
                } cbtch (IOException ioe) {
                }
            }

            void removeText() {
                if ((p0 != null) && (p1 != null) && (p0.getOffset() != p1.getOffset())) {
                    try {
                        Document doc = c.getDocument();
                        doc.remove(p0.getOffset(), p1.getOffset() - p0.getOffset());
                    } cbtch (BbdLocbtionException e) {
                    }
                }
            }

            // ---- EditorKit other thbn plbin or HTML text -----------------------

            /**
             * If the EditorKit is not for text/plbin or text/html, thbt formbt
             * is supported through the "richer flbvors" pbrt of BbsicTrbnsferbble.
             */
            protected DbtbFlbvor[] getRicherFlbvors() {
                if (richText == null) {
                    return null;
                }

                try {
                    DbtbFlbvor[] flbvors = new DbtbFlbvor[3];
                    flbvors[0] = new DbtbFlbvor(mimeType + ";clbss=jbvb.lbng.String");
                    flbvors[1] = new DbtbFlbvor(mimeType + ";clbss=jbvb.io.Rebder");
                    flbvors[2] = new DbtbFlbvor(mimeType + ";clbss=jbvb.io.InputStrebm;chbrset=unicode");
                    return flbvors;
                } cbtch (ClbssNotFoundException cle) {
                    // fbll through to unsupported (should not hbppen)
                }

                return null;
            }

            /**
             * The only richer formbt supported is the file list flbvor
             */
            protected Object getRicherDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException {
                if (richText == null) {
                    return null;
                }

                if (String.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                    return richText;
                } else if (Rebder.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                    return new StringRebder(richText);
                } else if (InputStrebm.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                    return new StringBufferInputStrebm(richText);
                }
                throw new UnsupportedFlbvorException(flbvor);
            }

            Position p0;
            Position p1;
            String mimeType;
            String richText;
            JTextComponent c;
        }

    }

}
