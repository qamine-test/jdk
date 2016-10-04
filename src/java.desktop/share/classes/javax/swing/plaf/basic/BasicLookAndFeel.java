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

import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.SystemColor;
import jbvb.bwt.event.*;
import jbvb.bwt.Insets;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.FocusTrbversblPolicy;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Point;
import jbvb.net.URL;
import jbvb.io.*;
import jbvb.bwt.Dimension;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.lbng.reflect.*;
import jbvbx.sound.sbmpled.*;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import sun.swing.SwingUtilities2;
import sun.swing.icon.SortArrowIcon;

import jbvbx.swing.LookAndFeel;
import jbvbx.swing.AbstrbctAction;
import jbvbx.swing.Action;
import jbvbx.swing.ActionMbp;
import jbvbx.swing.BorderFbctory;
import jbvbx.swing.JComponent;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.KeyStroke;
import jbvbx.swing.JTextField;
import jbvbx.swing.DefbultListCellRenderer;
import jbvbx.swing.FocusMbnbger;
import jbvbx.swing.LbyoutFocusTrbversblPolicy;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.MenuSelectionMbnbger;
import jbvbx.swing.MenuElement;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.text.DefbultEditorKit;
import jbvbx.swing.JInternblFrbme;
import stbtic jbvbx.swing.UIDefbults.LbzyVblue;
import jbvb.bebns.PropertyVetoException;
import jbvb.bwt.Window;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;


/**
 * A bbse clbss to use in crebting b look bnd feel for Swing.
 * <p>
 * Ebch of the {@code ComponentUI}s provided by {@code
 * BbsicLookAndFeel} derives its behbvior from the defbults
 * tbble. Unless otherwise noted ebch of the {@code ComponentUI}
 * implementbtions in this pbckbge document the set of defbults they
 * use. Unless otherwise noted the defbults bre instblled bt the time
 * {@code instbllUI} is invoked, bnd follow the recommendbtions
 * outlined in {@code LookAndFeel} for instblling defbults.
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
 * @buthor unbttributed
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss BbsicLookAndFeel extends LookAndFeel implements Seriblizbble
{
    /**
     * Whether or not the developer hbs crebted b JPopupMenu.
     */
    stbtic boolebn needsEventHelper;

    /**
     * Lock used when mbnipulbting clipPlbying.
     */
    privbte trbnsient Object budioLock = new Object();
    /**
     * The Clip thbt is currently plbying (set in AudioAction).
     */
    privbte Clip clipPlbying;

    AWTEventHelper invocbtor = null;

    /*
     * Listen for our AppContext being disposed
     */
    privbte PropertyChbngeListener disposer = null;

    /**
     * Returns the look bnd feel defbults. The returned {@code UIDefbults}
     * is populbted by invoking, in order, {@code initClbssDefbults},
     * {@code initSystemColorDefbults} bnd {@code initComponentDefbults}.
     * <p>
     * While this method is public, it should only be invoked by the
     * {@code UIMbnbger} when the look bnd feel is set bs the current
     * look bnd feel bnd bfter {@code initiblize} hbs been invoked.
     *
     * @return the look bnd feel defbults
     *
     * @see #initClbssDefbults
     * @see #initSystemColorDefbults
     * @see #initComponentDefbults
     */
    public UIDefbults getDefbults() {
        UIDefbults tbble = new UIDefbults(610, 0.75f);

        initClbssDefbults(tbble);
        initSystemColorDefbults(tbble);
        initComponentDefbults(tbble);

        return tbble;
    }

    /**
     * {@inheritDoc}
     */
    public void initiblize() {
        if (needsEventHelper) {
            instbllAWTEventListener();
        }
    }

    void instbllAWTEventListener() {
        if (invocbtor == null) {
            invocbtor = new AWTEventHelper();
            needsEventHelper = true;

            // Add b PropertyChbngeListener to our AppContext so we're blerted
            // when the AppContext is disposed(), bt which time this lbf should
            // be uninitiblize()d.
            disposer = new PropertyChbngeListener() {
                public void propertyChbnge(PropertyChbngeEvent prpChg) {
                    uninitiblize();
                }
            };
            AppContext.getAppContext().bddPropertyChbngeListener(
                                                        AppContext.GUI_DISPOSED,
                                                        disposer);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void uninitiblize() {
        AppContext context = AppContext.getAppContext();
        synchronized (BbsicPopupMenuUI.MOUSE_GRABBER_KEY) {
            Object grbbber = context.get(BbsicPopupMenuUI.MOUSE_GRABBER_KEY);
            if (grbbber != null) {
                ((BbsicPopupMenuUI.MouseGrbbber)grbbber).uninstbll();
            }
        }
        synchronized (BbsicPopupMenuUI.MENU_KEYBOARD_HELPER_KEY) {
            Object helper =
                    context.get(BbsicPopupMenuUI.MENU_KEYBOARD_HELPER_KEY);
            if (helper != null) {
                ((BbsicPopupMenuUI.MenuKeybobrdHelper)helper).uninstbll();
            }
        }

        if(invocbtor != null) {
            AccessController.doPrivileged(invocbtor);
            invocbtor = null;
        }

        if (disposer != null) {
            // Note thbt we're likely cblling removePropertyChbngeListener()
            // during the course of AppContext.firePropertyChbnge().
            // However, EventListenerAggreggbte hbs code to sbfely modify
            // the list under such circumstbnces.
            context.removePropertyChbngeListener(AppContext.GUI_DISPOSED,
                                                 disposer);
            disposer = null;
        }
    }

    /**
     * Populbtes {@code tbble} with mbppings from {@code uiClbssID} to the
     * fully qublified nbme of the ui clbss. The vblue for b
     * pbrticulbr {@code uiClbssID} is {@code
     * "jbvbx.swing.plbf.bbsic.Bbsic + uiClbssID"}. For exbmple, the
     * vblue for the {@code uiClbssID} {@code TreeUI} is {@code
     * "jbvbx.swing.plbf.bbsic.BbsicTreeUI"}.
     *
     * @pbrbm tbble the {@code UIDefbults} instbnce the entries bre
     *        bdded to
     * @throws NullPointerException if {@code tbble} is {@code null}
     *
     * @see jbvbx.swing.LookAndFeel
     * @see #getDefbults
     */
    protected void initClbssDefbults(UIDefbults tbble)
    {
        finbl String bbsicPbckbgeNbme = "jbvbx.swing.plbf.bbsic.";
        Object[] uiDefbults = {
                   "ButtonUI", bbsicPbckbgeNbme + "BbsicButtonUI",
                 "CheckBoxUI", bbsicPbckbgeNbme + "BbsicCheckBoxUI",
             "ColorChooserUI", bbsicPbckbgeNbme + "BbsicColorChooserUI",
       "FormbttedTextFieldUI", bbsicPbckbgeNbme + "BbsicFormbttedTextFieldUI",
                  "MenuBbrUI", bbsicPbckbgeNbme + "BbsicMenuBbrUI",
                     "MenuUI", bbsicPbckbgeNbme + "BbsicMenuUI",
                 "MenuItemUI", bbsicPbckbgeNbme + "BbsicMenuItemUI",
         "CheckBoxMenuItemUI", bbsicPbckbgeNbme + "BbsicCheckBoxMenuItemUI",
      "RbdioButtonMenuItemUI", bbsicPbckbgeNbme + "BbsicRbdioButtonMenuItemUI",
              "RbdioButtonUI", bbsicPbckbgeNbme + "BbsicRbdioButtonUI",
             "ToggleButtonUI", bbsicPbckbgeNbme + "BbsicToggleButtonUI",
                "PopupMenuUI", bbsicPbckbgeNbme + "BbsicPopupMenuUI",
              "ProgressBbrUI", bbsicPbckbgeNbme + "BbsicProgressBbrUI",
                "ScrollBbrUI", bbsicPbckbgeNbme + "BbsicScrollBbrUI",
               "ScrollPbneUI", bbsicPbckbgeNbme + "BbsicScrollPbneUI",
                "SplitPbneUI", bbsicPbckbgeNbme + "BbsicSplitPbneUI",
                   "SliderUI", bbsicPbckbgeNbme + "BbsicSliderUI",
                "SepbrbtorUI", bbsicPbckbgeNbme + "BbsicSepbrbtorUI",
                  "SpinnerUI", bbsicPbckbgeNbme + "BbsicSpinnerUI",
         "ToolBbrSepbrbtorUI", bbsicPbckbgeNbme + "BbsicToolBbrSepbrbtorUI",
       "PopupMenuSepbrbtorUI", bbsicPbckbgeNbme + "BbsicPopupMenuSepbrbtorUI",
               "TbbbedPbneUI", bbsicPbckbgeNbme + "BbsicTbbbedPbneUI",
                 "TextArebUI", bbsicPbckbgeNbme + "BbsicTextArebUI",
                "TextFieldUI", bbsicPbckbgeNbme + "BbsicTextFieldUI",
            "PbsswordFieldUI", bbsicPbckbgeNbme + "BbsicPbsswordFieldUI",
                 "TextPbneUI", bbsicPbckbgeNbme + "BbsicTextPbneUI",
               "EditorPbneUI", bbsicPbckbgeNbme + "BbsicEditorPbneUI",
                     "TreeUI", bbsicPbckbgeNbme + "BbsicTreeUI",
                    "LbbelUI", bbsicPbckbgeNbme + "BbsicLbbelUI",
                     "ListUI", bbsicPbckbgeNbme + "BbsicListUI",
                  "ToolBbrUI", bbsicPbckbgeNbme + "BbsicToolBbrUI",
                  "ToolTipUI", bbsicPbckbgeNbme + "BbsicToolTipUI",
                 "ComboBoxUI", bbsicPbckbgeNbme + "BbsicComboBoxUI",
                    "TbbleUI", bbsicPbckbgeNbme + "BbsicTbbleUI",
              "TbbleHebderUI", bbsicPbckbgeNbme + "BbsicTbbleHebderUI",
            "InternblFrbmeUI", bbsicPbckbgeNbme + "BbsicInternblFrbmeUI",
              "DesktopPbneUI", bbsicPbckbgeNbme + "BbsicDesktopPbneUI",
              "DesktopIconUI", bbsicPbckbgeNbme + "BbsicDesktopIconUI",
              "FileChooserUI", bbsicPbckbgeNbme + "BbsicFileChooserUI",
               "OptionPbneUI", bbsicPbckbgeNbme + "BbsicOptionPbneUI",
                    "PbnelUI", bbsicPbckbgeNbme + "BbsicPbnelUI",
                 "ViewportUI", bbsicPbckbgeNbme + "BbsicViewportUI",
                 "RootPbneUI", bbsicPbckbgeNbme + "BbsicRootPbneUI",
        };

        tbble.putDefbults(uiDefbults);
    }

    /**
     * Populbtes {@code tbble} with system colors. This crebtes bn
     * brrby of {@code nbme-color} pbirs bnd invokes {@code
     * lobdSystemColors}.
     * <p>
     * The nbme is b {@code String} thbt corresponds to the nbme of
     * one of the stbtic {@code SystemColor} fields in the {@code
     * SystemColor} clbss.  A nbme-color pbir is crebted for every
     * such {@code SystemColor} field.
     * <p>
     * The {@code color} corresponds to b hex {@code String} bs
     * understood by {@code Color.decode}. For exbmple, one of the
     * {@code nbme-color} pbirs is {@code
     * "desktop"-"#005C5C"}. This corresponds to the {@code
     * SystemColor} field {@code desktop}, with b color vblue of
     * {@code new Color(0x005C5C)}.
     * <p>
     * The following shows two of the {@code nbme-color} pbirs:
     * <pre>
     *   String[] nbmeColorPbirs = new String[] {
     *          "desktop", "#005C5C",
     *    "bctiveCbption", "#000080" };
     *   lobdSystemColors(tbble, nbmeColorPbirs, isNbtiveLookAndFeel());
     * </pre>
     *
     * As previously stbted, this invokes {@code lobdSystemColors}
     * with the supplied {@code tbble} bnd {@code nbme-color} pbir
     * brrby. The lbst brgument to {@code lobdSystemColors} indicbtes
     * whether the vblue of the field in {@code SystemColor} should be
     * used. This method pbsses the vblue of {@code
     * isNbtiveLookAndFeel()} bs the lbst brgument to {@code lobdSystemColors}.
     *
     * @pbrbm tbble the {@code UIDefbults} object the vblues bre bdded to
     * @throws NullPointerException if {@code tbble} is {@code null}
     *
     * @see jbvb.bwt.SystemColor
     * @see #getDefbults
     * @see #lobdSystemColors
     */
    protected void initSystemColorDefbults(UIDefbults tbble)
    {
        String[] defbultSystemColors = {
                "desktop", "#005C5C", /* Color of the desktop bbckground */
          "bctiveCbption", "#000080", /* Color for cbptions (title bbrs) when they bre bctive. */
      "bctiveCbptionText", "#FFFFFF", /* Text color for text in cbptions (title bbrs). */
    "bctiveCbptionBorder", "#C0C0C0", /* Border color for cbption (title bbr) window borders. */
        "inbctiveCbption", "#808080", /* Color for cbptions (title bbrs) when not bctive. */
    "inbctiveCbptionText", "#C0C0C0", /* Text color for text in inbctive cbptions (title bbrs). */
  "inbctiveCbptionBorder", "#C0C0C0", /* Border color for inbctive cbption (title bbr) window borders. */
                 "window", "#FFFFFF", /* Defbult color for the interior of windows */
           "windowBorder", "#000000", /* ??? */
             "windowText", "#000000", /* ??? */
                   "menu", "#C0C0C0", /* Bbckground color for menus */
               "menuText", "#000000", /* Text color for menus  */
                   "text", "#C0C0C0", /* Text bbckground color */
               "textText", "#000000", /* Text foreground color */
          "textHighlight", "#000080", /* Text bbckground color when selected */
      "textHighlightText", "#FFFFFF", /* Text color when selected */
       "textInbctiveText", "#808080", /* Text color when disbbled */
                "control", "#C0C0C0", /* Defbult color for controls (buttons, sliders, etc) */
            "controlText", "#000000", /* Defbult color for text in controls */
       "controlHighlight", "#C0C0C0", /* Speculbr highlight (opposite of the shbdow) */
     "controlLtHighlight", "#FFFFFF", /* Highlight color for controls */
          "controlShbdow", "#808080", /* Shbdow color for controls */
        "controlDkShbdow", "#000000", /* Dbrk shbdow color for controls */
              "scrollbbr", "#E0E0E0", /* Scrollbbr bbckground (usublly the "trbck") */
                   "info", "#FFFFE1", /* ??? */
               "infoText", "#000000"  /* ??? */
        };

        lobdSystemColors(tbble, defbultSystemColors, isNbtiveLookAndFeel());
    }


    /**
     * Populbtes {@code tbble} with the {@code nbme-color} pbirs in
     * {@code systemColors}. Refer to
     * {@link #initSystemColorDefbults(UIDefbults)} for detbils on
     * the formbt of {@code systemColors}.
     * <p>
     * An entry is bdded to {@code tbble} for ebch of the {@code nbme-color}
     * pbirs in {@code systemColors}. The entry key is
     * the {@code nbme} of the {@code nbme-color} pbir.
     * <p>
     * The vblue of the entry corresponds to the {@code color} of the
     * {@code nbme-color} pbir.  The vblue of the entry is cblculbted
     * in one of two wbys. With either bpprobch the vblue is blwbys b
     * {@code ColorUIResource}.
     * <p>
     * If {@code useNbtive} is {@code fblse}, the {@code color} is
     * crebted by using {@code Color.decode} to convert the {@code
     * String} into b {@code Color}. If {@code decode} cbn not convert
     * the {@code String} into b {@code Color} ({@code
     * NumberFormbtException} is thrown) then b {@code
     * ColorUIResource} of blbck is used.
     * <p>
     * If {@code useNbtive} is {@code true}, the {@code color} is the
     * vblue of the field in {@code SystemColor} with the sbme nbme bs
     * the {@code nbme} of the {@code nbme-color} pbir. If the field
     * is not vblid, b {@code ColorUIResource} of blbck is used.
     *
     * @pbrbm tbble the {@code UIDefbults} object the vblues bre bdded to
     * @pbrbm systemColors brrby of {@code nbme-color} pbirs bs described
     *        in {@link #initSystemColorDefbults(UIDefbults)}
     * @pbrbm useNbtive whether the color is obtbined from {@code SystemColor}
     *        or {@code Color.decode}
     * @throws NullPointerException if {@code systemColors} is {@code null}; or
     *         {@code systemColors} is not empty, bnd {@code tbble} is
     *         {@code null}; or one of the
     *         nbmes of the {@code nbme-color} pbirs is {@code null}; or
     *         {@code useNbtive} is {@code fblse} bnd one of the
     *         {@code colors} of the {@code nbme-color} pbirs is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code useNbtive} is
     *         {@code fblse} bnd {@code systemColors.length} is odd
     *
     * @see #initSystemColorDefbults(jbvbx.swing.UIDefbults)
     * @see jbvb.bwt.SystemColor
     * @see jbvb.bwt.Color#decode(String)
     */
    protected void lobdSystemColors(UIDefbults tbble, String[] systemColors, boolebn useNbtive)
    {
        /* PENDING(hmuller) We don't lobd the system colors below becbuse
         * they're not relibble.  Hopefully we'll be bble to do better in
         * b future version of AWT.
         */
        if (useNbtive) {
            for(int i = 0; i < systemColors.length; i += 2) {
                Color color = Color.blbck;
                try {
                    String nbme = systemColors[i];
                    color = (Color)(SystemColor.clbss.getField(nbme).get(null));
                } cbtch (Exception e) {
                }
                tbble.put(systemColors[i], new ColorUIResource(color));
            }
        } else {
            for(int i = 0; i < systemColors.length; i += 2) {
                Color color = Color.blbck;
                try {
                    color = Color.decode(systemColors[i + 1]);
                }
                cbtch(NumberFormbtException e) {
                    e.printStbckTrbce();
                }
                tbble.put(systemColors[i], new ColorUIResource(color));
            }
        }
    }
    /**
     * Initiblize the defbults tbble with the nbme of the ResourceBundle
     * used for getting locblized defbults.  Also initiblize the defbult
     * locble used when no locble is pbssed into UIDefbults.get().  The
     * defbult locble should generblly not be relied upon. It is here for
     * compbtibility with relebses prior to 1.4.
     */
    privbte void initResourceBundle(UIDefbults tbble) {
        tbble.setDefbultLocble( Locble.getDefbult() );
        tbble.bddResourceBundle( "com.sun.swing.internbl.plbf.bbsic.resources.bbsic" );
    }

    /**
     * Populbtes {@code tbble} with the defbults for the bbsic look bnd
     * feel.
     *
     * @pbrbm tbble the {@code UIDefbults} to bdd the vblues to
     * @throws NullPointerException if {@code tbble} is {@code null}
     */
    protected void initComponentDefbults(UIDefbults tbble)
    {

        initResourceBundle(tbble);

        // *** Shbred Integers
        Integer fiveHundred = 500;

        // *** Shbred Longs
        Long oneThousbnd = 1000L;

        LbzyVblue diblogPlbin12 = t ->
            new FontUIResource(Font.DIALOG, Font.PLAIN, 12);
        LbzyVblue serifPlbin12 = t ->
            new FontUIResource(Font.SERIF, Font.PLAIN, 12);
        LbzyVblue sbnsSerifPlbin12 =  t ->
            new FontUIResource(Font.SANS_SERIF, Font.PLAIN, 12);
        LbzyVblue monospbcedPlbin12 = t ->
            new FontUIResource(Font.MONOSPACED, Font.PLAIN, 12);
        LbzyVblue diblogBold12 = t ->
            new FontUIResource(Font.DIALOG, Font.BOLD, 12);


        // *** Shbred Colors
        ColorUIResource red = new ColorUIResource(Color.red);
        ColorUIResource blbck = new ColorUIResource(Color.blbck);
        ColorUIResource white = new ColorUIResource(Color.white);
        ColorUIResource yellow = new ColorUIResource(Color.yellow);
        ColorUIResource grby = new ColorUIResource(Color.grby);
        ColorUIResource lightGrby = new ColorUIResource(Color.lightGrby);
        ColorUIResource dbrkGrby = new ColorUIResource(Color.dbrkGrby);
        ColorUIResource scrollBbrTrbck = new ColorUIResource(224, 224, 224);

        Color control = tbble.getColor("control");
        Color controlDkShbdow = tbble.getColor("controlDkShbdow");
        Color controlHighlight = tbble.getColor("controlHighlight");
        Color controlLtHighlight = tbble.getColor("controlLtHighlight");
        Color controlShbdow = tbble.getColor("controlShbdow");
        Color controlText = tbble.getColor("controlText");
        Color menu = tbble.getColor("menu");
        Color menuText = tbble.getColor("menuText");
        Color textHighlight = tbble.getColor("textHighlight");
        Color textHighlightText = tbble.getColor("textHighlightText");
        Color textInbctiveText = tbble.getColor("textInbctiveText");
        Color textText = tbble.getColor("textText");
        Color window = tbble.getColor("window");

        // *** Shbred Insets
        InsetsUIResource zeroInsets = new InsetsUIResource(0,0,0,0);
        InsetsUIResource twoInsets = new InsetsUIResource(2,2,2,2);
        InsetsUIResource threeInsets = new InsetsUIResource(3,3,3,3);

        // *** Shbred Borders
        LbzyVblue mbrginBorder = t -> new BbsicBorders.MbrginBorder();
        LbzyVblue etchedBorder = t ->
            BorderUIResource.getEtchedBorderUIResource();
        LbzyVblue loweredBevelBorder = t ->
            BorderUIResource.getLoweredBevelBorderUIResource();

        LbzyVblue popupMenuBorder = t -> BbsicBorders.getInternblFrbmeBorder();

        LbzyVblue blbckLineBorder = t ->
            BorderUIResource.getBlbckLineBorderUIResource();
        LbzyVblue focusCellHighlightBorder = t ->
            new BorderUIResource.LineBorderUIResource(yellow);

        Object noFocusBorder = new BorderUIResource.EmptyBorderUIResource(1,1,1,1);

        LbzyVblue tbbleHebderBorder = t ->
            new BorderUIResource.BevelBorderUIResource(
                    BevelBorder.RAISED,
                                         controlLtHighlight,
                                         control,
                                         controlDkShbdow,
                    controlShbdow);


        // *** Button vblue objects

        LbzyVblue buttonBorder =
            t -> BbsicBorders.getButtonBorder();

        LbzyVblue buttonToggleBorder =
            t -> BbsicBorders.getToggleButtonBorder();

        LbzyVblue rbdioButtonBorder =
            t -> BbsicBorders.getRbdioButtonBorder();

        // *** FileChooser / FileView vblue objects

        Object newFolderIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                        BbsicLookAndFeel.clbss,
                                                        "icons/NewFolder.gif");
        Object upFolderIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                       BbsicLookAndFeel.clbss,
                                                       "icons/UpFolder.gif");
        Object homeFolderIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                         BbsicLookAndFeel.clbss,
                                                         "icons/HomeFolder.gif");
        Object detbilsViewIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                          BbsicLookAndFeel.clbss,
                                                          "icons/DetbilsView.gif");
        Object listViewIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                       BbsicLookAndFeel.clbss,
                                                       "icons/ListView.gif");
        Object directoryIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                        BbsicLookAndFeel.clbss,
                                                        "icons/Directory.gif");
        Object fileIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                   BbsicLookAndFeel.clbss,
                                                   "icons/File.gif");
        Object computerIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                       BbsicLookAndFeel.clbss,
                                                       "icons/Computer.gif");
        Object hbrdDriveIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                        BbsicLookAndFeel.clbss,
                                                        "icons/HbrdDrive.gif");
        Object floppyDriveIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                          BbsicLookAndFeel.clbss,
                                                          "icons/FloppyDrive.gif");


        // *** InternblFrbme vblue objects

        LbzyVblue internblFrbmeBorder = t ->
            BbsicBorders.getInternblFrbmeBorder();

        // *** List vblue objects

        Object listCellRendererActiveVblue = new UIDefbults.ActiveVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return new DefbultListCellRenderer.UIResource();
            }
        };


        // *** Menus vblue objects

        LbzyVblue menuBbrBorder =
            t -> BbsicBorders.getMenuBbrBorder();

        LbzyVblue menuItemCheckIcon =
            t -> BbsicIconFbctory.getMenuItemCheckIcon();

        LbzyVblue menuItemArrowIcon =
            t -> BbsicIconFbctory.getMenuItemArrowIcon();


        LbzyVblue menuArrowIcon =
            t -> BbsicIconFbctory.getMenuArrowIcon();

        LbzyVblue checkBoxIcon =
            t -> BbsicIconFbctory.getCheckBoxIcon();

        LbzyVblue rbdioButtonIcon =
            t -> BbsicIconFbctory.getRbdioButtonIcon();

        LbzyVblue checkBoxMenuItemIcon =
            t -> BbsicIconFbctory.getCheckBoxMenuItemIcon();

        LbzyVblue rbdioButtonMenuItemIcon =
            t -> BbsicIconFbctory.getRbdioButtonMenuItemIcon();

        Object menuItemAccelerbtorDelimiter = "+";

        // *** OptionPbne vblue objects

        Object optionPbneMinimumSize = new DimensionUIResource(262, 90);

        int zero =  0;
        LbzyVblue zeroBorder = t ->
            new BorderUIResource.EmptyBorderUIResource(zero, zero, zero, zero);

        int ten = 10;
        LbzyVblue optionPbneBorder = t ->
            new BorderUIResource.EmptyBorderUIResource(ten, ten, 12, ten);

        LbzyVblue optionPbneButtonArebBorder = t ->
            new BorderUIResource.EmptyBorderUIResource(6, zero, zero, zero);


        // *** ProgessBbr vblue objects

        LbzyVblue progressBbrBorder =
            t -> BbsicBorders.getProgressBbrBorder();

        // ** ScrollBbr vblue objects

        Object minimumThumbSize = new DimensionUIResource(8,8);
        Object mbximumThumbSize = new DimensionUIResource(4096,4096);

        // ** Slider vblue objects

        Object sliderFocusInsets = twoInsets;

        Object toolBbrSepbrbtorSize = new DimensionUIResource( 10, 10 );


        // *** SplitPbne vblue objects

        LbzyVblue splitPbneBorder =
            t -> BbsicBorders.getSplitPbneBorder();
        LbzyVblue splitPbneDividerBorder =
            t -> BbsicBorders.getSplitPbneDividerBorder();

        // ** TbbbedBbne vblue objects

        Object tbbbedPbneTbbInsets = new InsetsUIResource(0, 4, 1, 4);

        Object tbbbedPbneTbbPbdInsets = new InsetsUIResource(2, 2, 2, 1);

        Object tbbbedPbneTbbArebInsets = new InsetsUIResource(3, 2, 0, 2);

        Object tbbbedPbneContentBorderInsets = new InsetsUIResource(2, 2, 3, 3);


        // *** Text vblue objects

        LbzyVblue textFieldBorder =
            t -> BbsicBorders.getTextFieldBorder();

        Object editorMbrgin = threeInsets;

        Object cbretBlinkRbte = fiveHundred;

        Object[] bllAuditoryCues = new Object[] {
                "CheckBoxMenuItem.commbndSound",
                "InternblFrbme.closeSound",
                "InternblFrbme.mbximizeSound",
                "InternblFrbme.minimizeSound",
                "InternblFrbme.restoreDownSound",
                "InternblFrbme.restoreUpSound",
                "MenuItem.commbndSound",
                "OptionPbne.errorSound",
                "OptionPbne.informbtionSound",
                "OptionPbne.questionSound",
                "OptionPbne.wbrningSound",
                "PopupMenu.popupSound",
                "RbdioButtonMenuItem.commbndSound"};

        Object[] noAuditoryCues = new Object[] {"mute"};

        // *** Component Defbults

        Object[] defbults = {
            // *** Auditory Feedbbck
            "AuditoryCues.cueList", bllAuditoryCues,
            "AuditoryCues.bllAuditoryCues", bllAuditoryCues,
            "AuditoryCues.noAuditoryCues", noAuditoryCues,
            // this key defines which of the vbrious cues to render.
            // L&Fs thbt wbnt buditory feedbbck NEED to override plbyList.
            "AuditoryCues.plbyList", null,

            // *** Buttons
            "Button.defbultButtonFollowsFocus", Boolebn.TRUE,
            "Button.font", diblogPlbin12,
            "Button.bbckground", control,
            "Button.foreground", controlText,
            "Button.shbdow", controlShbdow,
            "Button.dbrkShbdow", controlDkShbdow,
            "Button.light", controlHighlight,
            "Button.highlight", controlLtHighlight,
            "Button.border", buttonBorder,
            "Button.mbrgin", new InsetsUIResource(2, 14, 2, 14),
            "Button.textIconGbp", 4,
            "Button.textShiftOffset", zero,
            "Button.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                         "SPACE", "pressed",
                "relebsed SPACE", "relebsed",
                         "ENTER", "pressed",
                "relebsed ENTER", "relebsed"
              }),

            "ToggleButton.font", diblogPlbin12,
            "ToggleButton.bbckground", control,
            "ToggleButton.foreground", controlText,
            "ToggleButton.shbdow", controlShbdow,
            "ToggleButton.dbrkShbdow", controlDkShbdow,
            "ToggleButton.light", controlHighlight,
            "ToggleButton.highlight", controlLtHighlight,
            "ToggleButton.border", buttonToggleBorder,
            "ToggleButton.mbrgin", new InsetsUIResource(2, 14, 2, 14),
            "ToggleButton.textIconGbp", 4,
            "ToggleButton.textShiftOffset", zero,
            "ToggleButton.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                }),

            "RbdioButton.font", diblogPlbin12,
            "RbdioButton.bbckground", control,
            "RbdioButton.foreground", controlText,
            "RbdioButton.shbdow", controlShbdow,
            "RbdioButton.dbrkShbdow", controlDkShbdow,
            "RbdioButton.light", controlHighlight,
            "RbdioButton.highlight", controlLtHighlight,
            "RbdioButton.border", rbdioButtonBorder,
            "RbdioButton.mbrgin", twoInsets,
            "RbdioButton.textIconGbp", 4,
            "RbdioButton.textShiftOffset", zero,
            "RbdioButton.icon", rbdioButtonIcon,
            "RbdioButton.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed",
                         "RETURN", "pressed"
              }),

            "CheckBox.font", diblogPlbin12,
            "CheckBox.bbckground", control,
            "CheckBox.foreground", controlText,
            "CheckBox.border", rbdioButtonBorder,
            "CheckBox.mbrgin", twoInsets,
            "CheckBox.textIconGbp", 4,
            "CheckBox.textShiftOffset", zero,
            "CheckBox.icon", checkBoxIcon,
            "CheckBox.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                 }),
            "FileChooser.useSystemExtensionHiding", Boolebn.FALSE,

            // *** ColorChooser
            "ColorChooser.font", diblogPlbin12,
            "ColorChooser.bbckground", control,
            "ColorChooser.foreground", controlText,

            "ColorChooser.swbtchesSwbtchSize", new Dimension(10, 10),
            "ColorChooser.swbtchesRecentSwbtchSize", new Dimension(10, 10),
            "ColorChooser.swbtchesDefbultRecentColor", control,

            // *** ComboBox
            "ComboBox.font", sbnsSerifPlbin12,
            "ComboBox.bbckground", window,
            "ComboBox.foreground", textText,
            "ComboBox.buttonBbckground", control,
            "ComboBox.buttonShbdow", controlShbdow,
            "ComboBox.buttonDbrkShbdow", controlDkShbdow,
            "ComboBox.buttonHighlight", controlLtHighlight,
            "ComboBox.selectionBbckground", textHighlight,
            "ComboBox.selectionForeground", textHighlightText,
            "ComboBox.disbbledBbckground", control,
            "ComboBox.disbbledForeground", textInbctiveText,
            "ComboBox.timeFbctor", oneThousbnd,
            "ComboBox.isEnterSelectbblePopup", Boolebn.FALSE,
            "ComboBox.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                      "ESCAPE", "hidePopup",
                     "PAGE_UP", "pbgeUpPbssThrough",
                   "PAGE_DOWN", "pbgeDownPbssThrough",
                        "HOME", "homePbssThrough",
                         "END", "endPbssThrough",
                       "ENTER", "enterPressed"
                 }),
            "ComboBox.noActionOnKeyNbvigbtion", Boolebn.FALSE,

            // *** FileChooser

            "FileChooser.newFolderIcon", newFolderIcon,
            "FileChooser.upFolderIcon", upFolderIcon,
            "FileChooser.homeFolderIcon", homeFolderIcon,
            "FileChooser.detbilsViewIcon", detbilsViewIcon,
            "FileChooser.listViewIcon", listViewIcon,
            "FileChooser.rebdOnly", Boolebn.FALSE,
            "FileChooser.usesSingleFilePbne", Boolebn.FALSE,
            "FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection",
                     "F5", "refresh",
                 }),

            "FileView.directoryIcon", directoryIcon,
            "FileView.fileIcon", fileIcon,
            "FileView.computerIcon", computerIcon,
            "FileView.hbrdDriveIcon", hbrdDriveIcon,
            "FileView.floppyDriveIcon", floppyDriveIcon,

            // *** InternblFrbme
            "InternblFrbme.titleFont", diblogBold12,
            "InternblFrbme.borderColor", control,
            "InternblFrbme.borderShbdow", controlShbdow,
            "InternblFrbme.borderDbrkShbdow", controlDkShbdow,
            "InternblFrbme.borderHighlight", controlLtHighlight,
            "InternblFrbme.borderLight", controlHighlight,
            "InternblFrbme.border", internblFrbmeBorder,
            "InternblFrbme.icon",   SwingUtilities2.mbkeIcon(getClbss(),
                                                             BbsicLookAndFeel.clbss,
                                                             "icons/JbvbCup16.png"),

            /* Defbult frbme icons bre undefined for Bbsic. */
            "InternblFrbme.mbximizeIcon",
               (LbzyVblue) t -> BbsicIconFbctory.crebteEmptyFrbmeIcon(),
            "InternblFrbme.minimizeIcon",
               (LbzyVblue) t -> BbsicIconFbctory.crebteEmptyFrbmeIcon(),
            "InternblFrbme.iconifyIcon",
               (LbzyVblue) t -> BbsicIconFbctory.crebteEmptyFrbmeIcon(),
            "InternblFrbme.closeIcon",
               (LbzyVblue) t -> BbsicIconFbctory.crebteEmptyFrbmeIcon(),
            // InternblFrbme Auditory Cue Mbppings
            "InternblFrbme.closeSound", null,
            "InternblFrbme.mbximizeSound", null,
            "InternblFrbme.minimizeSound", null,
            "InternblFrbme.restoreDownSound", null,
            "InternblFrbme.restoreUpSound", null,

            "InternblFrbme.bctiveTitleBbckground", tbble.get("bctiveCbption"),
            "InternblFrbme.bctiveTitleForeground", tbble.get("bctiveCbptionText"),
            "InternblFrbme.inbctiveTitleBbckground", tbble.get("inbctiveCbption"),
            "InternblFrbme.inbctiveTitleForeground", tbble.get("inbctiveCbptionText"),
            "InternblFrbme.windowBindings", new Object[] {
              "shift ESCAPE", "showSystemMenu",
                "ctrl SPACE", "showSystemMenu",
                    "ESCAPE", "hideSystemMenu"},

            "InternblFrbmeTitlePbne.iconifyButtonOpbcity", Boolebn.TRUE,
            "InternblFrbmeTitlePbne.mbximizeButtonOpbcity", Boolebn.TRUE,
            "InternblFrbmeTitlePbne.closeButtonOpbcity", Boolebn.TRUE,

        "DesktopIcon.border", internblFrbmeBorder,

            "Desktop.minOnScreenInsets", threeInsets,
            "Desktop.bbckground", tbble.get("desktop"),
            "Desktop.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                 "ctrl F5", "restore",
                 "ctrl F4", "close",
                 "ctrl F7", "move",
                 "ctrl F8", "resize",
                   "RIGHT", "right",
                "KP_RIGHT", "right",
             "shift RIGHT", "shrinkRight",
          "shift KP_RIGHT", "shrinkRight",
                    "LEFT", "left",
                 "KP_LEFT", "left",
              "shift LEFT", "shrinkLeft",
           "shift KP_LEFT", "shrinkLeft",
                      "UP", "up",
                   "KP_UP", "up",
                "shift UP", "shrinkUp",
             "shift KP_UP", "shrinkUp",
                    "DOWN", "down",
                 "KP_DOWN", "down",
              "shift DOWN", "shrinkDown",
           "shift KP_DOWN", "shrinkDown",
                  "ESCAPE", "escbpe",
                 "ctrl F9", "minimize",
                "ctrl F10", "mbximize",
                 "ctrl F6", "selectNextFrbme",
                "ctrl TAB", "selectNextFrbme",
             "ctrl blt F6", "selectNextFrbme",
       "shift ctrl blt F6", "selectPreviousFrbme",
                "ctrl F12", "nbvigbteNext",
          "shift ctrl F12", "nbvigbtePrevious"
              }),

            // *** Lbbel
            "Lbbel.font", diblogPlbin12,
            "Lbbel.bbckground", control,
            "Lbbel.foreground", controlText,
            "Lbbel.disbbledForeground", white,
            "Lbbel.disbbledShbdow", controlShbdow,
            "Lbbel.border", null,

            // *** List
            "List.font", diblogPlbin12,
            "List.bbckground", window,
            "List.foreground", textText,
            "List.selectionBbckground", textHighlight,
            "List.selectionForeground", textHighlightText,
            "List.noFocusBorder", noFocusBorder,
            "List.focusCellHighlightBorder", focusCellHighlightBorder,
            "List.dropLineColor", controlShbdow,
            "List.border", null,
            "List.cellRenderer", listCellRendererActiveVblue,
            "List.timeFbctor", oneThousbnd,
            "List.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", "copy",
                           "ctrl V", "pbste",
                           "ctrl X", "cut",
                             "COPY", "copy",
                            "PASTE", "pbste",
                              "CUT", "cut",
                   "control INSERT", "copy",
                     "shift INSERT", "pbste",
                     "shift DELETE", "cut",
                               "UP", "selectPreviousRow",
                            "KP_UP", "selectPreviousRow",
                         "shift UP", "selectPreviousRowExtendSelection",
                      "shift KP_UP", "selectPreviousRowExtendSelection",
                    "ctrl shift UP", "selectPreviousRowExtendSelection",
                 "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                          "ctrl UP", "selectPreviousRowChbngeLebd",
                       "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                             "DOWN", "selectNextRow",
                          "KP_DOWN", "selectNextRow",
                       "shift DOWN", "selectNextRowExtendSelection",
                    "shift KP_DOWN", "selectNextRowExtendSelection",
                  "ctrl shift DOWN", "selectNextRowExtendSelection",
               "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChbngeLebd",
                     "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                             "LEFT", "selectPreviousColumn",
                          "KP_LEFT", "selectPreviousColumn",
                       "shift LEFT", "selectPreviousColumnExtendSelection",
                    "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                  "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                            "RIGHT", "selectNextColumn",
                         "KP_RIGHT", "selectNextColumn",
                      "shift RIGHT", "selectNextColumnExtendSelection",
                   "shift KP_RIGHT", "selectNextColumnExtendSelection",
                 "ctrl shift RIGHT", "selectNextColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                       "ctrl RIGHT", "selectNextColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                             "HOME", "selectFirstRow",
                       "shift HOME", "selectFirstRowExtendSelection",
                  "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRowChbngeLebd",
                              "END", "selectLbstRow",
                        "shift END", "selectLbstRowExtendSelection",
                   "ctrl shift END", "selectLbstRowExtendSelection",
                         "ctrl END", "selectLbstRowChbngeLebd",
                          "PAGE_UP", "scrollUp",
                    "shift PAGE_UP", "scrollUpExtendSelection",
               "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl PAGE_UP", "scrollUpChbngeLebd",
                        "PAGE_DOWN", "scrollDown",
                  "shift PAGE_DOWN", "scrollDownExtendSelection",
             "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                           "ctrl A", "selectAll",
                       "ctrl SLASH", "selectAll",
                  "ctrl BACK_SLASH", "clebrSelection",
                            "SPACE", "bddToSelection",
                       "ctrl SPACE", "toggleAndAnchor",
                      "shift SPACE", "extendTo",
                 "ctrl shift SPACE", "moveSelectionTo"
                 }),
            "List.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                             "LEFT", "selectNextColumn",
                          "KP_LEFT", "selectNextColumn",
                       "shift LEFT", "selectNextColumnExtendSelection",
                    "shift KP_LEFT", "selectNextColumnExtendSelection",
                  "ctrl shift LEFT", "selectNextColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectNextColumnExtendSelection",
                        "ctrl LEFT", "selectNextColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectNextColumnChbngeLebd",
                            "RIGHT", "selectPreviousColumn",
                         "KP_RIGHT", "selectPreviousColumn",
                      "shift RIGHT", "selectPreviousColumnExtendSelection",
                   "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                 "ctrl shift RIGHT", "selectPreviousColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                       "ctrl RIGHT", "selectPreviousColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectPreviousColumnChbngeLebd",
                 }),

            // *** Menus
            "MenuBbr.font", diblogPlbin12,
            "MenuBbr.bbckground", menu,
            "MenuBbr.foreground", menuText,
            "MenuBbr.shbdow", controlShbdow,
            "MenuBbr.highlight", controlLtHighlight,
            "MenuBbr.border", menuBbrBorder,
            "MenuBbr.windowBindings", new Object[] {
                "F10", "tbkeFocus" },

            "MenuItem.font", diblogPlbin12,
            "MenuItem.bccelerbtorFont", diblogPlbin12,
            "MenuItem.bbckground", menu,
            "MenuItem.foreground", menuText,
            "MenuItem.selectionForeground", textHighlightText,
            "MenuItem.selectionBbckground", textHighlight,
            "MenuItem.disbbledForeground", null,
            "MenuItem.bccelerbtorForeground", menuText,
            "MenuItem.bccelerbtorSelectionForeground", textHighlightText,
            "MenuItem.bccelerbtorDelimiter", menuItemAccelerbtorDelimiter,
            "MenuItem.border", mbrginBorder,
            "MenuItem.borderPbinted", Boolebn.FALSE,
            "MenuItem.mbrgin", twoInsets,
            "MenuItem.checkIcon", menuItemCheckIcon,
            "MenuItem.brrowIcon", menuItemArrowIcon,
            "MenuItem.commbndSound", null,

            "RbdioButtonMenuItem.font", diblogPlbin12,
            "RbdioButtonMenuItem.bccelerbtorFont", diblogPlbin12,
            "RbdioButtonMenuItem.bbckground", menu,
            "RbdioButtonMenuItem.foreground", menuText,
            "RbdioButtonMenuItem.selectionForeground", textHighlightText,
            "RbdioButtonMenuItem.selectionBbckground", textHighlight,
            "RbdioButtonMenuItem.disbbledForeground", null,
            "RbdioButtonMenuItem.bccelerbtorForeground", menuText,
            "RbdioButtonMenuItem.bccelerbtorSelectionForeground", textHighlightText,
            "RbdioButtonMenuItem.border", mbrginBorder,
            "RbdioButtonMenuItem.borderPbinted", Boolebn.FALSE,
            "RbdioButtonMenuItem.mbrgin", twoInsets,
            "RbdioButtonMenuItem.checkIcon", rbdioButtonMenuItemIcon,
            "RbdioButtonMenuItem.brrowIcon", menuItemArrowIcon,
            "RbdioButtonMenuItem.commbndSound", null,

            "CheckBoxMenuItem.font", diblogPlbin12,
            "CheckBoxMenuItem.bccelerbtorFont", diblogPlbin12,
            "CheckBoxMenuItem.bbckground", menu,
            "CheckBoxMenuItem.foreground", menuText,
            "CheckBoxMenuItem.selectionForeground", textHighlightText,
            "CheckBoxMenuItem.selectionBbckground", textHighlight,
            "CheckBoxMenuItem.disbbledForeground", null,
            "CheckBoxMenuItem.bccelerbtorForeground", menuText,
            "CheckBoxMenuItem.bccelerbtorSelectionForeground", textHighlightText,
            "CheckBoxMenuItem.border", mbrginBorder,
            "CheckBoxMenuItem.borderPbinted", Boolebn.FALSE,
            "CheckBoxMenuItem.mbrgin", twoInsets,
            "CheckBoxMenuItem.checkIcon", checkBoxMenuItemIcon,
            "CheckBoxMenuItem.brrowIcon", menuItemArrowIcon,
            "CheckBoxMenuItem.commbndSound", null,

            "Menu.font", diblogPlbin12,
            "Menu.bccelerbtorFont", diblogPlbin12,
            "Menu.bbckground", menu,
            "Menu.foreground", menuText,
            "Menu.selectionForeground", textHighlightText,
            "Menu.selectionBbckground", textHighlight,
            "Menu.disbbledForeground", null,
            "Menu.bccelerbtorForeground", menuText,
            "Menu.bccelerbtorSelectionForeground", textHighlightText,
            "Menu.border", mbrginBorder,
            "Menu.borderPbinted", Boolebn.FALSE,
            "Menu.mbrgin", twoInsets,
            "Menu.checkIcon", menuItemCheckIcon,
            "Menu.brrowIcon", menuArrowIcon,
            "Menu.menuPopupOffsetX", 0,
            "Menu.menuPopupOffsetY", 0,
            "Menu.submenuPopupOffsetX", 0,
            "Menu.submenuPopupOffsetY", 0,
            "Menu.shortcutKeys", new int[]{
                SwingUtilities2.getSystemMnemonicKeyMbsk()
            },
            "Menu.crossMenuMnemonic", Boolebn.TRUE,
            // Menu.cbncelMode bffects the cbncel menu bction behbviour;
            // currently supports:
            // "hideLbstSubmenu" (defbult)
            //     hides the lbst open submenu,
            //     bnd move selection one step bbck
            // "hideMenuTree"
            //     resets selection bnd
            //     hide the entire structure of open menu bnd its submenus
            "Menu.cbncelMode", "hideLbstSubmenu",

             // Menu.preserveTopLevelSelection bffects
             // the cbncel menu bction behbviour
             // if set to true then top level menu selection
             // will be preserved when the lbst popup wbs cbncelled;
             // the menu itself will be unselect with the next cbncel bction
             "Menu.preserveTopLevelSelection", Boolebn.FALSE,

            // PopupMenu
            "PopupMenu.font", diblogPlbin12,
            "PopupMenu.bbckground", menu,
            "PopupMenu.foreground", menuText,
            "PopupMenu.border", popupMenuBorder,
                 // Internbl Frbme Auditory Cue Mbppings
            "PopupMenu.popupSound", null,
            // These window InputMbp bindings bre used when the Menu is
            // selected.
            "PopupMenu.selectedWindowInputMbpBindings", new Object[] {
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
              "ctrl ENTER", "return",
                   "SPACE", "return"
            },
            "PopupMenu.selectedWindowInputMbpBindings.RightToLeft", new Object[] {
                    "LEFT", "selectChild",
                 "KP_LEFT", "selectChild",
                   "RIGHT", "selectPbrent",
                "KP_RIGHT", "selectPbrent",
            },
            "PopupMenu.consumeEventOnClose", Boolebn.FALSE,

            // *** OptionPbne
            // You cbn bdditionbly define OptionPbne.messbgeFont which will
            // dictbte the fonts used for the messbge, bnd
            // OptionPbne.buttonFont, which defines the font for the buttons.
            "OptionPbne.font", diblogPlbin12,
            "OptionPbne.bbckground", control,
            "OptionPbne.foreground", controlText,
            "OptionPbne.messbgeForeground", controlText,
            "OptionPbne.border", optionPbneBorder,
            "OptionPbne.messbgeArebBorder", zeroBorder,
            "OptionPbne.buttonArebBorder", optionPbneButtonArebBorder,
            "OptionPbne.minimumSize", optionPbneMinimumSize,
            "OptionPbne.errorIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                             BbsicLookAndFeel.clbss,
                                                             "icons/Error.gif"),
            "OptionPbne.informbtionIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                                   BbsicLookAndFeel.clbss,
                                                                   "icons/Inform.gif"),
            "OptionPbne.wbrningIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                               BbsicLookAndFeel.clbss,
                                                               "icons/Wbrn.gif"),
            "OptionPbne.questionIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                                BbsicLookAndFeel.clbss,
                                                                "icons/Question.gif"),
            "OptionPbne.windowBindings", new Object[] {
                "ESCAPE", "close" },
                 // OptionPbne Auditory Cue Mbppings
            "OptionPbne.errorSound", null,
            "OptionPbne.informbtionSound", null, // Info bnd Plbin
            "OptionPbne.questionSound", null,
            "OptionPbne.wbrningSound", null,
            "OptionPbne.buttonClickThreshhold", fiveHundred,

            // *** Pbnel
            "Pbnel.font", diblogPlbin12,
            "Pbnel.bbckground", control,
            "Pbnel.foreground", textText,

            // *** ProgressBbr
            "ProgressBbr.font", diblogPlbin12,
            "ProgressBbr.foreground",  textHighlight,
            "ProgressBbr.bbckground", control,
            "ProgressBbr.selectionForeground", control,
            "ProgressBbr.selectionBbckground", textHighlight,
            "ProgressBbr.border", progressBbrBorder,
            "ProgressBbr.cellLength", 1,
            "ProgressBbr.cellSpbcing", zero,
            "ProgressBbr.repbintIntervbl", 50,
            "ProgressBbr.cycleTime", 3000,
            "ProgressBbr.horizontblSize", new DimensionUIResource(146, 12),
            "ProgressBbr.verticblSize", new DimensionUIResource(12, 146),

           // *** Sepbrbtor
            "Sepbrbtor.shbdow", controlShbdow,          // DEPRECATED - DO NOT USE!
            "Sepbrbtor.highlight", controlLtHighlight,  // DEPRECATED - DO NOT USE!

            "Sepbrbtor.bbckground", controlLtHighlight,
            "Sepbrbtor.foreground", controlShbdow,

            // *** ScrollBbr/ScrollPbne/Viewport
            "ScrollBbr.bbckground", scrollBbrTrbck,
            "ScrollBbr.foreground", control,
            "ScrollBbr.trbck", tbble.get("scrollbbr"),
            "ScrollBbr.trbckHighlight", controlDkShbdow,
            "ScrollBbr.thumb", control,
            "ScrollBbr.thumbHighlight", controlLtHighlight,
            "ScrollBbr.thumbDbrkShbdow", controlDkShbdow,
            "ScrollBbr.thumbShbdow", controlShbdow,
            "ScrollBbr.border", null,
            "ScrollBbr.minimumThumbSize", minimumThumbSize,
            "ScrollBbr.mbximumThumbSize", mbximumThumbSize,
            "ScrollBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "negbtiveUnitIncrement",
                       "KP_UP", "negbtiveUnitIncrement",
                     "PAGE_UP", "negbtiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),
            "ScrollBbr.bncestorInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "negbtiveUnitIncrement",
                    "KP_RIGHT", "negbtiveUnitIncrement",
                        "LEFT", "positiveUnitIncrement",
                     "KP_LEFT", "positiveUnitIncrement",
                 }),
            "ScrollBbr.width", 16,

            "ScrollPbne.font", diblogPlbin12,
            "ScrollPbne.bbckground", control,
            "ScrollPbne.foreground", controlText,
            "ScrollPbne.border", textFieldBorder,
            "ScrollPbne.viewportBorder", null,
            "ScrollPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "RIGHT", "unitScrollRight",
                        "KP_RIGHT", "unitScrollRight",
                            "DOWN", "unitScrollDown",
                         "KP_DOWN", "unitScrollDown",
                            "LEFT", "unitScrollLeft",
                         "KP_LEFT", "unitScrollLeft",
                              "UP", "unitScrollUp",
                           "KP_UP", "unitScrollUp",
                         "PAGE_UP", "scrollUp",
                       "PAGE_DOWN", "scrollDown",
                    "ctrl PAGE_UP", "scrollLeft",
                  "ctrl PAGE_DOWN", "scrollRight",
                       "ctrl HOME", "scrollHome",
                        "ctrl END", "scrollEnd"
                 }),
            "ScrollPbne.bncestorInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                    "ctrl PAGE_UP", "scrollRight",
                  "ctrl PAGE_DOWN", "scrollLeft",
                 }),

            "Viewport.font", diblogPlbin12,
            "Viewport.bbckground", control,
            "Viewport.foreground", textText,

            // *** Slider
            "Slider.font", diblogPlbin12,
            "Slider.foreground", control,
            "Slider.bbckground", control,
            "Slider.highlight", controlLtHighlight,
            "Slider.tickColor", Color.blbck,
            "Slider.shbdow", controlShbdow,
            "Slider.focus", controlDkShbdow,
            "Slider.border", null,
            "Slider.horizontblSize", new Dimension(200, 21),
            "Slider.verticblSize", new Dimension(21, 200),
            "Slider.minimumHorizontblSize", new Dimension(36, 21),
            "Slider.minimumVerticblSize", new Dimension(21, 36),
            "Slider.focusInsets", sliderFocusInsets,
            "Slider.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negbtiveUnitIncrement",
                     "KP_DOWN", "negbtiveUnitIncrement",
                   "PAGE_DOWN", "negbtiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "positiveUnitIncrement",
                       "KP_UP", "positiveUnitIncrement",
                     "PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),
            "Slider.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "negbtiveUnitIncrement",
                    "KP_RIGHT", "negbtiveUnitIncrement",
                        "LEFT", "positiveUnitIncrement",
                     "KP_LEFT", "positiveUnitIncrement",
                 }),
            "Slider.onlyLeftMouseButtonDrbg", Boolebn.TRUE,

            // *** Spinner
            "Spinner.font", monospbcedPlbin12,
            "Spinner.bbckground", control,
            "Spinner.foreground", control,
            "Spinner.border", textFieldBorder,
            "Spinner.brrowButtonBorder", null,
            "Spinner.brrowButtonInsets", null,
            "Spinner.brrowButtonSize", new Dimension(16, 5),
            "Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),
            "Spinner.editorBorderPbinted", Boolebn.FALSE,
            "Spinner.editorAlignment", JTextField.TRAILING,

            // *** SplitPbne
            "SplitPbne.bbckground", control,
            "SplitPbne.highlight", controlLtHighlight,
            "SplitPbne.shbdow", controlShbdow,
            "SplitPbne.dbrkShbdow", controlDkShbdow,
            "SplitPbne.border", splitPbneBorder,
            "SplitPbne.dividerSize", 7,
            "SplitPbneDivider.border", splitPbneDividerBorder,
            "SplitPbneDivider.drbggingColor", dbrkGrby,
            "SplitPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "negbtiveIncrement",
                      "DOWN", "positiveIncrement",
                      "LEFT", "negbtiveIncrement",
                     "RIGHT", "positiveIncrement",
                     "KP_UP", "negbtiveIncrement",
                   "KP_DOWN", "positiveIncrement",
                   "KP_LEFT", "negbtiveIncrement",
                  "KP_RIGHT", "positiveIncrement",
                      "HOME", "selectMin",
                       "END", "selectMbx",
                        "F8", "stbrtResize",
                        "F6", "toggleFocus",
                  "ctrl TAB", "focusOutForwbrd",
            "ctrl shift TAB", "focusOutBbckwbrd"
                 }),

            // *** TbbbedPbne
            "TbbbedPbne.font", diblogPlbin12,
            "TbbbedPbne.bbckground", control,
            "TbbbedPbne.foreground", controlText,
            "TbbbedPbne.highlight", controlLtHighlight,
            "TbbbedPbne.light", controlHighlight,
            "TbbbedPbne.shbdow", controlShbdow,
            "TbbbedPbne.dbrkShbdow", controlDkShbdow,
            "TbbbedPbne.selected", null,
            "TbbbedPbne.focus", controlText,
            "TbbbedPbne.textIconGbp", 4,

            // Cbuses tbbs to be pbinted on top of the content breb border.
            // The bmount of overlbp is then controlled by tbbArebInsets.bottom,
            // which is zero by defbult
            "TbbbedPbne.tbbsOverlbpBorder", Boolebn.FALSE,
            "TbbbedPbne.selectionFollowsFocus", Boolebn.TRUE,

            "TbbbedPbne.lbbelShift", 1,
            "TbbbedPbne.selectedLbbelShift", -1,
            "TbbbedPbne.tbbInsets", tbbbedPbneTbbInsets,
            "TbbbedPbne.selectedTbbPbdInsets", tbbbedPbneTbbPbdInsets,
            "TbbbedPbne.tbbArebInsets", tbbbedPbneTbbArebInsets,
            "TbbbedPbne.contentBorderInsets", tbbbedPbneContentBorderInsets,
            "TbbbedPbne.tbbRunOverlby", 2,
            "TbbbedPbne.tbbsOpbque", Boolebn.TRUE,
            "TbbbedPbne.contentOpbque", Boolebn.TRUE,
            "TbbbedPbne.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                         "RIGHT", "nbvigbteRight",
                      "KP_RIGHT", "nbvigbteRight",
                          "LEFT", "nbvigbteLeft",
                       "KP_LEFT", "nbvigbteLeft",
                            "UP", "nbvigbteUp",
                         "KP_UP", "nbvigbteUp",
                          "DOWN", "nbvigbteDown",
                       "KP_DOWN", "nbvigbteDown",
                     "ctrl DOWN", "requestFocusForVisibleComponent",
                  "ctrl KP_DOWN", "requestFocusForVisibleComponent",
                }),
            "TbbbedPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                   "ctrl PAGE_DOWN", "nbvigbtePbgeDown",
                     "ctrl PAGE_UP", "nbvigbtePbgeUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
                 }),


            // *** Tbble
            "Tbble.font", diblogPlbin12,
            "Tbble.foreground", controlText,  // cell text color
            "Tbble.bbckground", window,  // cell bbckground color
            "Tbble.selectionForeground", textHighlightText,
            "Tbble.selectionBbckground", textHighlight,
            "Tbble.dropLineColor", controlShbdow,
            "Tbble.dropLineShortColor", blbck,
            "Tbble.gridColor", grby,  // grid line color
            "Tbble.focusCellBbckground", window,
            "Tbble.focusCellForeground", controlText,
            "Tbble.focusCellHighlightBorder", focusCellHighlightBorder,
            "Tbble.scrollPbneBorder", loweredBevelBorder,
            "Tbble.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "ctrl C", "copy",
                               "ctrl V", "pbste",
                               "ctrl X", "cut",
                                 "COPY", "copy",
                                "PASTE", "pbste",
                                  "CUT", "cut",
                       "control INSERT", "copy",
                         "shift INSERT", "pbste",
                         "shift DELETE", "cut",
                                "RIGHT", "selectNextColumn",
                             "KP_RIGHT", "selectNextColumn",
                          "shift RIGHT", "selectNextColumnExtendSelection",
                       "shift KP_RIGHT", "selectNextColumnExtendSelection",
                     "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                           "ctrl RIGHT", "selectNextColumnChbngeLebd",
                        "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                                 "LEFT", "selectPreviousColumn",
                              "KP_LEFT", "selectPreviousColumn",
                           "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                      "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                            "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                         "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                                 "DOWN", "selectNextRow",
                              "KP_DOWN", "selectNextRow",
                           "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                      "ctrl shift DOWN", "selectNextRowExtendSelection",
                   "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                            "ctrl DOWN", "selectNextRowChbngeLebd",
                         "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                                   "UP", "selectPreviousRow",
                                "KP_UP", "selectPreviousRow",
                             "shift UP", "selectPreviousRowExtendSelection",
                          "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                     "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                              "ctrl UP", "selectPreviousRowChbngeLebd",
                           "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                                 "HOME", "selectFirstColumn",
                           "shift HOME", "selectFirstColumnExtendSelection",
                      "ctrl shift HOME", "selectFirstRowExtendSelection",
                            "ctrl HOME", "selectFirstRow",
                                  "END", "selectLbstColumn",
                            "shift END", "selectLbstColumnExtendSelection",
                       "ctrl shift END", "selectLbstRowExtendSelection",
                             "ctrl END", "selectLbstRow",
                              "PAGE_UP", "scrollUpChbngeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                   "ctrl shift PAGE_UP", "scrollLeftExtendSelection",
                         "ctrl PAGE_UP", "scrollLeftChbngeSelection",
                            "PAGE_DOWN", "scrollDownChbngeSelection",
                      "shift PAGE_DOWN", "scrollDownExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollRightExtendSelection",
                       "ctrl PAGE_DOWN", "scrollRightChbngeSelection",
                                  "TAB", "selectNextColumnCell",
                            "shift TAB", "selectPreviousColumnCell",
                                "ENTER", "selectNextRowCell",
                          "shift ENTER", "selectPreviousRowCell",
                               "ctrl A", "selectAll",
                           "ctrl SLASH", "selectAll",
                      "ctrl BACK_SLASH", "clebrSelection",
                               "ESCAPE", "cbncel",
                                   "F2", "stbrtEditing",
                                "SPACE", "bddToSelection",
                           "ctrl SPACE", "toggleAndAnchor",
                          "shift SPACE", "extendTo",
                     "ctrl shift SPACE", "moveSelectionTo",
                                   "F8", "focusHebder"
                 }),
            "Tbble.bncestorInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                "RIGHT", "selectPreviousColumn",
                             "KP_RIGHT", "selectPreviousColumn",
                          "shift RIGHT", "selectPreviousColumnExtendSelection",
                       "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                     "ctrl shift RIGHT", "selectPreviousColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                           "ctrl RIGHT", "selectPreviousColumnChbngeLebd",
                        "ctrl KP_RIGHT", "selectPreviousColumnChbngeLebd",
                                 "LEFT", "selectNextColumn",
                              "KP_LEFT", "selectNextColumn",
                           "shift LEFT", "selectNextColumnExtendSelection",
                        "shift KP_LEFT", "selectNextColumnExtendSelection",
                      "ctrl shift LEFT", "selectNextColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectNextColumnExtendSelection",
                            "ctrl LEFT", "selectNextColumnChbngeLebd",
                         "ctrl KP_LEFT", "selectNextColumnChbngeLebd",
                         "ctrl PAGE_UP", "scrollRightChbngeSelection",
                       "ctrl PAGE_DOWN", "scrollLeftChbngeSelection",
                   "ctrl shift PAGE_UP", "scrollRightExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollLeftExtendSelection",
                 }),
            "Tbble.bscendingSortIcon", (LbzyVblue) t ->
                    new SortArrowIcon(true, "Tbble.sortIconColor"),
            "Tbble.descendingSortIcon", (LbzyVblue) t ->
                    new SortArrowIcon(fblse, "Tbble.sortIconColor"),
            "Tbble.sortIconColor", controlShbdow,

            "TbbleHebder.font", diblogPlbin12,
            "TbbleHebder.foreground", controlText, // hebder text color
            "TbbleHebder.bbckground", control, // hebder bbckground
            "TbbleHebder.cellBorder", tbbleHebderBorder,

            // Support for chbnging the bbckground/border of the currently
            // selected hebder column when the hebder hbs the keybobrd focus.
            "TbbleHebder.focusCellBbckground", tbble.getColor("text"), // like text component bg
            "TbbleHebder.focusCellForeground", null,
            "TbbleHebder.focusCellBorder", null,
            "TbbleHebder.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                "SPACE", "toggleSortOrder",
                                 "LEFT", "selectColumnToLeft",
                              "KP_LEFT", "selectColumnToLeft",
                                "RIGHT", "selectColumnToRight",
                             "KP_RIGHT", "selectColumnToRight",
                             "blt LEFT", "moveColumnLeft",
                          "blt KP_LEFT", "moveColumnLeft",
                            "blt RIGHT", "moveColumnRight",
                         "blt KP_RIGHT", "moveColumnRight",
                       "blt shift LEFT", "resizeLeft",
                    "blt shift KP_LEFT", "resizeLeft",
                      "blt shift RIGHT", "resizeRight",
                   "blt shift KP_RIGHT", "resizeRight",
                               "ESCAPE", "focusTbble",
               }),

            // *** Text
            "TextField.font", sbnsSerifPlbin12,
            "TextField.bbckground", window,
            "TextField.foreground", textText,
            "TextField.shbdow", controlShbdow,
            "TextField.dbrkShbdow", controlDkShbdow,
            "TextField.light", controlHighlight,
            "TextField.highlight", controlLtHighlight,
            "TextField.inbctiveForeground", textInbctiveText,
            "TextField.inbctiveBbckground", control,
            "TextField.selectionBbckground", textHighlight,
            "TextField.selectionForeground", textHighlightText,
            "TextField.cbretForeground", textText,
            "TextField.cbretBlinkRbte", cbretBlinkRbte,
            "TextField.border", textFieldBorder,
            "TextField.mbrgin", zeroInsets,

            "FormbttedTextField.font", sbnsSerifPlbin12,
            "FormbttedTextField.bbckground", window,
            "FormbttedTextField.foreground", textText,
            "FormbttedTextField.inbctiveForeground", textInbctiveText,
            "FormbttedTextField.inbctiveBbckground", control,
            "FormbttedTextField.selectionBbckground", textHighlight,
            "FormbttedTextField.selectionForeground", textHighlightText,
            "FormbttedTextField.cbretForeground", textText,
            "FormbttedTextField.cbretBlinkRbte", cbretBlinkRbte,
            "FormbttedTextField.border", textFieldBorder,
            "FormbttedTextField.mbrgin", zeroInsets,
            "FormbttedTextField.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                   "control INSERT", DefbultEditorKit.copyAction,
                     "shift INSERT", DefbultEditorKit.pbsteAction,
                     "shift DELETE", DefbultEditorKit.cutAction,
                       "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift KP_LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                      "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "shift KP_RIGHT", DefbultEditorKit.selectionForwbrdAction,
                        "ctrl LEFT", DefbultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefbultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefbultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefbultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefbultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefbultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefbultEditorKit.selectionNextWordAction,
                           "ctrl A", DefbultEditorKit.selectAllAction,
                             "HOME", DefbultEditorKit.beginLineAction,
                              "END", DefbultEditorKit.endLineAction,
                       "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                        "shift END", DefbultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                 "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                           "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                           "DELETE", DefbultEditorKit.deleteNextChbrAction,
                      "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                            "RIGHT", DefbultEditorKit.forwbrdAction,
                             "LEFT", DefbultEditorKit.bbckwbrdAction,
                         "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                          "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect",
                  "control shift O", "toggle-componentOrientbtion",
                           "ESCAPE", "reset-field-edit",
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
              }),

            "PbsswordField.font", monospbcedPlbin12,
            "PbsswordField.bbckground", window,
            "PbsswordField.foreground", textText,
            "PbsswordField.inbctiveForeground", textInbctiveText,
            "PbsswordField.inbctiveBbckground", control,
            "PbsswordField.selectionBbckground", textHighlight,
            "PbsswordField.selectionForeground", textHighlightText,
            "PbsswordField.cbretForeground", textText,
            "PbsswordField.cbretBlinkRbte", cbretBlinkRbte,
            "PbsswordField.border", textFieldBorder,
            "PbsswordField.mbrgin", zeroInsets,
            "PbsswordField.echoChbr", '*',

            "TextAreb.font", monospbcedPlbin12,
            "TextAreb.bbckground", window,
            "TextAreb.foreground", textText,
            "TextAreb.inbctiveForeground", textInbctiveText,
            "TextAreb.selectionBbckground", textHighlight,
            "TextAreb.selectionForeground", textHighlightText,
            "TextAreb.cbretForeground", textText,
            "TextAreb.cbretBlinkRbte", cbretBlinkRbte,
            "TextAreb.border", mbrginBorder,
            "TextAreb.mbrgin", zeroInsets,

            "TextPbne.font", serifPlbin12,
            "TextPbne.bbckground", white,
            "TextPbne.foreground", textText,
            "TextPbne.selectionBbckground", textHighlight,
            "TextPbne.selectionForeground", textHighlightText,
            "TextPbne.cbretForeground", textText,
            "TextPbne.cbretBlinkRbte", cbretBlinkRbte,
            "TextPbne.inbctiveForeground", textInbctiveText,
            "TextPbne.border", mbrginBorder,
            "TextPbne.mbrgin", editorMbrgin,

            "EditorPbne.font", serifPlbin12,
            "EditorPbne.bbckground", white,
            "EditorPbne.foreground", textText,
            "EditorPbne.selectionBbckground", textHighlight,
            "EditorPbne.selectionForeground", textHighlightText,
            "EditorPbne.cbretForeground", textText,
            "EditorPbne.cbretBlinkRbte", cbretBlinkRbte,
            "EditorPbne.inbctiveForeground", textInbctiveText,
            "EditorPbne.border", mbrginBorder,
            "EditorPbne.mbrgin", editorMbrgin,

            "html.pendingImbge", SwingUtilities2.mbkeIcon(getClbss(),
                                    BbsicLookAndFeel.clbss,
                                    "icons/imbge-delbyed.png"),
            "html.missingImbge", SwingUtilities2.mbkeIcon(getClbss(),
                                    BbsicLookAndFeel.clbss,
                                    "icons/imbge-fbiled.png"),
            // *** TitledBorder
            "TitledBorder.font", diblogPlbin12,
            "TitledBorder.titleColor", controlText,
            "TitledBorder.border", etchedBorder,

            // *** ToolBbr
            "ToolBbr.font", diblogPlbin12,
            "ToolBbr.bbckground", control,
            "ToolBbr.foreground", controlText,
            "ToolBbr.shbdow", controlShbdow,
            "ToolBbr.dbrkShbdow", controlDkShbdow,
            "ToolBbr.light", controlHighlight,
            "ToolBbr.highlight", controlLtHighlight,
            "ToolBbr.dockingBbckground", control,
            "ToolBbr.dockingForeground", red,
            "ToolBbr.flobtingBbckground", control,
            "ToolBbr.flobtingForeground", dbrkGrby,
            "ToolBbr.border", etchedBorder,
            "ToolBbr.sepbrbtorSize", toolBbrSepbrbtorSize,
            "ToolBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "nbvigbteUp",
                     "KP_UP", "nbvigbteUp",
                      "DOWN", "nbvigbteDown",
                   "KP_DOWN", "nbvigbteDown",
                      "LEFT", "nbvigbteLeft",
                   "KP_LEFT", "nbvigbteLeft",
                     "RIGHT", "nbvigbteRight",
                  "KP_RIGHT", "nbvigbteRight"
                 }),

            // *** ToolTips
            "ToolTip.font", sbnsSerifPlbin12,
            "ToolTip.bbckground", tbble.get("info"),
            "ToolTip.foreground", tbble.get("infoText"),
            "ToolTip.border", blbckLineBorder,
            // ToolTips blso support bbckgroundInbctive, borderInbctive,
            // bnd foregroundInbctive

        // *** ToolTipMbnbger
            // ToolTipMbnbger.enbbleToolTipMode currently supports:
            // "bllWindows" (defbult):
            //     enbbles tool tips for bll windows of bll jbvb bpplicbtions,
            //     whether the windows bre bctive or inbctive
            // "bctiveApplicbtion"
            //     enbbles tool tips for windows of bn bpplicbtion only when
            //     the bpplicbtion hbs bn bctive window
            "ToolTipMbnbger.enbbleToolTipMode", "bllWindows",

        // *** Tree
            "Tree.pbintLines", Boolebn.TRUE,
            "Tree.lineTypeDbshed", Boolebn.FALSE,
            "Tree.font", diblogPlbin12,
            "Tree.bbckground", window,
            "Tree.foreground", textText,
            "Tree.hbsh", grby,
            "Tree.textForeground", textText,
            "Tree.textBbckground", tbble.get("text"),
            "Tree.selectionForeground", textHighlightText,
            "Tree.selectionBbckground", textHighlight,
            "Tree.selectionBorderColor", blbck,
            "Tree.dropLineColor", controlShbdow,
            "Tree.editorBorder", blbckLineBorder,
            "Tree.leftChildIndent", 7,
            "Tree.rightChildIndent", 13,
            "Tree.rowHeight", 16,
            "Tree.scrollsOnExpbnd", Boolebn.TRUE,
            "Tree.openIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                      BbsicLookAndFeel.clbss,
                                                      "icons/TreeOpen.gif"),
            "Tree.closedIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                        BbsicLookAndFeel.clbss,
                                                        "icons/TreeClosed.gif"),
            "Tree.lebfIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                      BbsicLookAndFeel.clbss,
                                                      "icons/TreeLebf.gif"),
            "Tree.expbndedIcon", null,
            "Tree.collbpsedIcon", null,
            "Tree.chbngeSelectionWithFocus", Boolebn.TRUE,
            "Tree.drbwsFocusBorderAroundIcon", Boolebn.FALSE,
            "Tree.timeFbctor", oneThousbnd,
            "Tree.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                 "ctrl C", "copy",
                                 "ctrl V", "pbste",
                                 "ctrl X", "cut",
                                   "COPY", "copy",
                                  "PASTE", "pbste",
                                    "CUT", "cut",
                         "control INSERT", "copy",
                           "shift INSERT", "pbste",
                           "shift DELETE", "cut",
                                     "UP", "selectPrevious",
                                  "KP_UP", "selectPrevious",
                               "shift UP", "selectPreviousExtendSelection",
                            "shift KP_UP", "selectPreviousExtendSelection",
                          "ctrl shift UP", "selectPreviousExtendSelection",
                       "ctrl shift KP_UP", "selectPreviousExtendSelection",
                                "ctrl UP", "selectPreviousChbngeLebd",
                             "ctrl KP_UP", "selectPreviousChbngeLebd",
                                   "DOWN", "selectNext",
                                "KP_DOWN", "selectNext",
                             "shift DOWN", "selectNextExtendSelection",
                          "shift KP_DOWN", "selectNextExtendSelection",
                        "ctrl shift DOWN", "selectNextExtendSelection",
                     "ctrl shift KP_DOWN", "selectNextExtendSelection",
                              "ctrl DOWN", "selectNextChbngeLebd",
                           "ctrl KP_DOWN", "selectNextChbngeLebd",
                                  "RIGHT", "selectChild",
                               "KP_RIGHT", "selectChild",
                                   "LEFT", "selectPbrent",
                                "KP_LEFT", "selectPbrent",
                                "PAGE_UP", "scrollUpChbngeSelection",
                          "shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                           "ctrl PAGE_UP", "scrollUpChbngeLebd",
                              "PAGE_DOWN", "scrollDownChbngeSelection",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                         "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                                   "HOME", "selectFirst",
                             "shift HOME", "selectFirstExtendSelection",
                        "ctrl shift HOME", "selectFirstExtendSelection",
                              "ctrl HOME", "selectFirstChbngeLebd",
                                    "END", "selectLbst",
                              "shift END", "selectLbstExtendSelection",
                         "ctrl shift END", "selectLbstExtendSelection",
                               "ctrl END", "selectLbstChbngeLebd",
                                     "F2", "stbrtEditing",
                                 "ctrl A", "selectAll",
                             "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clebrSelection",
                              "ctrl LEFT", "scrollLeft",
                           "ctrl KP_LEFT", "scrollLeft",
                             "ctrl RIGHT", "scrollRight",
                          "ctrl KP_RIGHT", "scrollRight",
                                  "SPACE", "bddToSelection",
                             "ctrl SPACE", "toggleAndAnchor",
                            "shift SPACE", "extendTo",
                       "ctrl shift SPACE", "moveSelectionTo"
                 }),
            "Tree.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                  "RIGHT", "selectPbrent",
                               "KP_RIGHT", "selectPbrent",
                                   "LEFT", "selectChild",
                                "KP_LEFT", "selectChild",
                 }),
            "Tree.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncel"
                 }),
            // Bind specific keys thbt cbn invoke popup on currently
            // focused JComponent
            "RootPbne.bncestorInputMbp",
                new UIDefbults.LbzyInputMbp(new Object[] {
                     "shift F10", "postPopup",
                  "CONTEXT_MENU", "postPopup"
                  }),

            // These bindings bre only enbbled when there is b defbult
            // button set on the rootpbne.
            "RootPbne.defbultButtonWindowKeyBindings", new Object[] {
                             "ENTER", "press",
                    "relebsed ENTER", "relebse",
                        "ctrl ENTER", "press",
               "ctrl relebsed ENTER", "relebse"
              },
        };

        tbble.putDefbults(defbults);
    }

    stbtic int getFocusAccelerbtorKeyMbsk() {
        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof SunToolkit) {
            return ((SunToolkit)tk).getFocusAccelerbtorKeyMbsk();
        }
        return ActionEvent.ALT_MASK;
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

    // ********* Auditory Cue support methods bnd objects *********
    // blso see the "AuditoryCues" section of the defbults tbble

    /**
     * Returns bn <code>ActionMbp</code> contbining the budio bctions
     * for this look bnd feel.
     * <P>
     * The returned <code>ActionMbp</code> contbins <code>Actions</code> thbt
     * embody the bbility to render bn buditory cue. These buditory
     * cues mbp onto user bnd system bctivities thbt mby be useful
     * for bn end user to know bbout (such bs b diblog box bppebring).
     * <P>
     * At the bppropribte time,
     * the {@code ComponentUI} is responsible for obtbining bn
     * <code>Action</code> out of the <code>ActionMbp</code> bnd pbssing
     * it to <code>plbySound</code>.
     * <P>
     * This method first looks up the {@code ActionMbp} from the
     * defbults using the key {@code "AuditoryCues.bctionMbp"}.
     * <p>
     * If the vblue is {@code non-null}, it is returned. If the vblue
     * of the defbult {@code "AuditoryCues.bctionMbp"} is {@code null}
     * bnd the vblue of the defbult {@code "AuditoryCues.cueList"} is
     * {@code non-null}, bn {@code ActionMbpUIResource} is crebted bnd
     * populbted. Populbtion is done by iterbting over ebch of the
     * elements of the {@code "AuditoryCues.cueList"} brrby, bnd
     * invoking {@code crebteAudioAction()} to crebte bn {@code
     * Action} for ebch element.  The resulting {@code Action} is
     * plbced in the {@code ActionMbpUIResource}, using the brrby
     * element bs the key.  For exbmple, if the {@code
     * "AuditoryCues.cueList"} brrby contbins b single-element, {@code
     * "budioKey"}, the {@code ActionMbpUIResource} is crebted, then
     * populbted by wby of {@code bctionMbp.put(cueList[0],
     * crebteAudioAction(cueList[0]))}.
     * <p>
     * If the vblue of the defbult {@code "AuditoryCues.bctionMbp"} is
     * {@code null} bnd the vblue of the defbult
     * {@code "AuditoryCues.cueList"} is {@code null}, bn empty
     * {@code ActionMbpUIResource} is crebted.
     *
     *
     * @return      bn ActionMbp contbining {@code Actions}
     *              responsible for plbying buditory cues
     * @throws ClbssCbstException if the vblue of the
     *         defbult {@code "AuditoryCues.bctionMbp"} is not bn
     *         {@code ActionMbp}, or the vblue of the defbult
     *         {@code "AuditoryCues.cueList"} is not bn {@code Object[]}
     * @see #crebteAudioAction
     * @see #plbySound(Action)
     * @since 1.4
     */
    protected ActionMbp getAudioActionMbp() {
        ActionMbp budioActionMbp = (ActionMbp)UIMbnbger.get(
                                              "AuditoryCues.bctionMbp");
        if (budioActionMbp == null) {
            Object[] bcList = (Object[])UIMbnbger.get("AuditoryCues.cueList");
            if (bcList != null) {
                budioActionMbp = new ActionMbpUIResource();
                for(int counter = bcList.length-1; counter >= 0; counter--) {
                    budioActionMbp.put(bcList[counter],
                                       crebteAudioAction(bcList[counter]));
                }
            }
            UIMbnbger.getLookAndFeelDefbults().put("AuditoryCues.bctionMbp",
                                                   budioActionMbp);
        }
        return budioActionMbp;
    }

    /**
     * Crebtes bnd returns bn {@code Action} used to plby b sound.
     * <p>
     * If {@code key} is {@code non-null}, bn {@code Action} is crebted
     * using the vblue from the defbults with key {@code key}. The vblue
     * identifies the sound resource to lobd when
     * {@code bctionPerformed} is invoked on the {@code Action}. The
     * sound resource is lobded into b {@code byte[]} by wby of
     * {@code getClbss().getResourceAsStrebm()}.
     *
     * @pbrbm key the key identifying the budio bction
     * @return      bn {@code Action} used to plby the source, or {@code null}
     *              if {@code key} is {@code null}
     * @see #plbySound(Action)
     * @since 1.4
     */
    protected Action crebteAudioAction(Object key) {
        if (key != null) {
            String budioKey = (String)key;
            String budioVblue = (String)UIMbnbger.get(key);
            return new AudioAction(budioKey, budioVblue);
        } else {
            return null;
        }
    }

    /**
     * Pbss the nbme String to the super constructor. This is used
     * lbter to identify the Action bnd decide whether to plby it or
     * not. Store the resource String. I is used to get the budio
     * resource. In this cbse, the resource is bn budio file.
     *
     * @since 1.4
     */
    privbte clbss AudioAction extends AbstrbctAction implements LineListener {
        // We strive to only plby one sound bt b time (other plbtforms
        // bppebr to do this). This is done by mbintbining the field
        // clipPlbying. Every time b sound is to be plbyed,
        // cbncelCurrentSound is invoked to cbncel bny sound thbt mby be
        // plbying.
        privbte String budioResource;
        privbte byte[] budioBuffer;

        /**
         * The String is the nbme of the Action bnd
         * points to the budio resource.
         * The byte[] is b buffer of the budio bits.
         */
        public AudioAction(String nbme, String resource) {
            super(nbme);
            budioResource = resource;
        }

        public void bctionPerformed(ActionEvent e) {
            if (budioBuffer == null) {
                budioBuffer = lobdAudioDbtb(budioResource);
            }
            if (budioBuffer != null) {
                cbncelCurrentSound(null);
                try {
                    AudioInputStrebm soundStrebm =
                        AudioSystem.getAudioInputStrebm(
                            new ByteArrbyInputStrebm(budioBuffer));
                    DbtbLine.Info info =
                        new DbtbLine.Info(Clip.clbss, soundStrebm.getFormbt());
                    Clip clip = (Clip) AudioSystem.getLine(info);
                    clip.open(soundStrebm);
                    clip.bddLineListener(this);

                    synchronized(budioLock) {
                        clipPlbying = clip;
                    }

                    clip.stbrt();
                } cbtch (Exception ex) {}
            }
        }

        public void updbte(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                cbncelCurrentSound((Clip)event.getLine());
            }
        }

        /**
         * If the pbrbmeter is null, or equbl to the currently
         * plbying sound, then cbncel the currently plbying sound.
         */
        privbte void cbncelCurrentSound(Clip clip) {
            Clip lbstClip = null;

            synchronized(budioLock) {
                if (clip == null || clip == clipPlbying) {
                    lbstClip = clipPlbying;
                    clipPlbying = null;
                }
            }

            if (lbstClip != null) {
                lbstClip.removeLineListener(this);
                lbstClip.close();
            }
        }
    }

    /**
     * Utility method thbt lobds budio bits for the specified
     * <code>soundFile</code> filenbme. If this method is unbble to
     * build b vibble pbth nbme from the <code>bbseClbss</code> bnd
     * <code>soundFile</code> pbssed into this method, it will
     * return <code>null</code>.
     *
     * @pbrbm soundFile    the nbme of the budio file to be retrieved
     *                     from disk
     * @return             A byte[] with budio dbtb or null
     * @since 1.4
     */
    privbte byte[] lobdAudioDbtb(finbl String soundFile){
        if (soundFile == null) {
            return null;
        }
        /* Copy resource into b byte brrby.  This is
         * necessbry becbuse severbl browsers consider
         * Clbss.getResource b security risk since it
         * cbn be used to lobd bdditionbl clbsses.
         * Clbss.getResourceAsStrebm just returns rbw
         * bytes, which we cbn convert to b sound.
         */
        byte[] buffer = AccessController.doPrivileged(
                                                 new PrivilegedAction<byte[]>() {
                public byte[] run() {
                    try {
                        InputStrebm resource = BbsicLookAndFeel.this.
                            getClbss().getResourceAsStrebm(soundFile);
                        if (resource == null) {
                            return null;
                        }
                        BufferedInputStrebm in =
                            new BufferedInputStrebm(resource);
                        ByteArrbyOutputStrebm out =
                            new ByteArrbyOutputStrebm(1024);
                        byte[] buffer = new byte[1024];
                        int n;
                        while ((n = in.rebd(buffer)) > 0) {
                            out.write(buffer, 0, n);
                        }
                        in.close();
                        out.flush();
                        buffer = out.toByteArrby();
                        return buffer;
                    } cbtch (IOException ioe) {
                        System.err.println(ioe.toString());
                        return null;
                    }
                }
            });
        if (buffer == null) {
            System.err.println(getClbss().getNbme() + "/" +
                               soundFile + " not found.");
            return null;
        }
        if (buffer.length == 0) {
            System.err.println("wbrning: " + soundFile +
                               " is zero-length");
            return null;
        }
        return buffer;
    }

    /**
     * If necessbry, invokes {@code bctionPerformed} on
     * {@code budioAction} to plby b sound.
     * The {@code bctionPerformed} method is invoked if the vblue of
     * the {@code "AuditoryCues.plbyList"} defbult is b {@code
     * non-null} {@code Object[]} contbining b {@code String} entry
     * equbl to the nbme of the {@code budioAction}.
     *
     * @pbrbm budioAction bn Action thbt knows how to render the budio
     *                    bssocibted with the system or user bctivity
     *                    thbt is occurring; b vblue of {@code null}, is
     *                    ignored
     * @throws ClbssCbstException if {@code budioAction} is {@code non-null}
     *         bnd the vblue of the defbult {@code "AuditoryCues.plbyList"}
     *         is not bn {@code Object[]}
     * @since 1.4
     */
    protected void plbySound(Action budioAction) {
        if (budioAction != null) {
            Object[] budioStrings = (Object[])
                                    UIMbnbger.get("AuditoryCues.plbyList");
            if (budioStrings != null) {
                // crebte b HbshSet to help us decide to plby or not
                HbshSet<Object> budioCues = new HbshSet<Object>();
                for (Object budioString : budioStrings) {
                    budioCues.bdd(budioString);
                }
                // get the nbme of the Action
                String bctionNbme = (String)budioAction.getVblue(Action.NAME);
                // if the bctionNbme is in the budioCues HbshSet, plby it.
                if (budioCues.contbins(bctionNbme)) {
                    budioAction.bctionPerformed(new
                        ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                                    bctionNbme));
                }
            }
        }
    }


    /**
     * Sets the pbrent of the pbssed in ActionMbp to be the budio bction
     * mbp.
     */
    stbtic void instbllAudioActionMbp(ActionMbp mbp) {
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        if (lbf instbnceof BbsicLookAndFeel) {
            mbp.setPbrent(((BbsicLookAndFeel)lbf).getAudioActionMbp());
        }
    }


    /**
     * Helper method to plby b nbmed sound.
     *
     * @pbrbm c JComponent to plby the sound for.
     * @pbrbm bctionKey Key for the sound.
     */
    stbtic void plbySound(JComponent c, Object bctionKey) {
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        if (lbf instbnceof BbsicLookAndFeel) {
            ActionMbp mbp = c.getActionMbp();
            if (mbp != null) {
                Action budioAction = mbp.get(bctionKey);
                if (budioAction != null) {
                    // pbss off firing the Action to b utility method
                    ((BbsicLookAndFeel)lbf).plbySound(budioAction);
                }
            }
        }
    }

    /**
     * This clbss contbins listener thbt wbtches for bll the mouse
     * events thbt cbn possibly invoke popup on the component
     */
    clbss AWTEventHelper implements AWTEventListener,PrivilegedAction<Object> {
        AWTEventHelper() {
            super();
            AccessController.doPrivileged(this);
        }

        public Object run() {
            Toolkit tk = Toolkit.getDefbultToolkit();
            if(invocbtor == null) {
                tk.bddAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
            } else {
                tk.removeAWTEventListener(invocbtor);
            }
            // Return vblue not used.
            return null;
        }

        public void eventDispbtched(AWTEvent ev) {
            int eventID = ev.getID();
            if((eventID & AWTEvent.MOUSE_EVENT_MASK) != 0) {
                MouseEvent me = (MouseEvent) ev;
                if(me.isPopupTrigger()) {
                    MenuElement[] elems = MenuSelectionMbnbger
                            .defbultMbnbger()
                            .getSelectedPbth();
                    if(elems != null && elems.length != 0) {
                        return;
                        // We shbll not interfere with blrebdy opened menu
                    }
                    Object c = me.getSource();
                    JComponent src = null;
                    if(c instbnceof JComponent) {
                        src = (JComponent) c;
                    } else if(c instbnceof BbsicSplitPbneDivider) {
                        // Specibl cbse - if user clicks on divider we must
                        // invoke popup from the SplitPbne
                        src = (JComponent)
                            ((BbsicSplitPbneDivider)c).getPbrent();
                    }
                    if(src != null) {
                        if(src.getComponentPopupMenu() != null) {
                            Point pt = src.getPopupLocbtion(me);
                            if(pt == null) {
                                pt = me.getPoint();
                                pt = SwingUtilities.convertPoint((Component)c,
                                                                  pt, src);
                            }
                            src.getComponentPopupMenu().show(src, pt.x, pt.y);
                            me.consume();
                        }
                    }
                }
            }
            /* Activbte b JInternblFrbme if necessbry. */
            if (eventID == MouseEvent.MOUSE_PRESSED) {
                Object object = ev.getSource();
                if (!(object instbnceof Component)) {
                    return;
                }
                Component component = (Component)object;
                if (component != null) {
                    Component pbrent = component;
                    while (pbrent != null && !(pbrent instbnceof Window)) {
                        if (pbrent instbnceof JInternblFrbme) {
                            // Activbte the frbme.
                            try { ((JInternblFrbme)pbrent).setSelected(true); }
                            cbtch (PropertyVetoException e1) { }
                        }
                        pbrent = pbrent.getPbrent();
                    }
                }
            }
        }
    }
}
