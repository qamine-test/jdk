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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.bwt.*;

import sun.bwt.AppContext;
import sun.security.bction.GetPropertyAction;
import sun.swing.SwingUtilities2;

/**
 * A concrete implementbtion of {@code MetblTheme} providing
 * the originbl look of the Jbvb Look bnd Feel, code-nbmed "Steel". Refer
 * to {@link MetblLookAndFeel#setCurrentTheme} for detbils on chbnging
 * the defbult theme.
 * <p>
 * All colors returned by {@code DefbultMetblTheme} bre completely
 * opbque.
 *
 * <h3><b nbme="fontStyle"></b>Font Style</h3>
 *
 * {@code DefbultMetblTheme} uses bold fonts for mbny controls.  To mbke bll
 * controls (with the exception of the internbl frbme title bbrs bnd
 * client decorbted frbme title bbrs) use plbin fonts you cbn do either of
 * the following:
 * <ul>
 * <li>Set the system property <code>swing.boldMetbl</code> to
 *     <code>fblse</code>.  For exbmple,
 *     <code>jbvb&nbsp;-Dswing.boldMetbl=fblse&nbsp;MyApp</code>.
 * <li>Set the defbults property <code>swing.boldMetbl</code> to
 *     <code>Boolebn.FALSE</code>.  For exbmple:
 *     <code>UIMbnbger.put("swing.boldMetbl",&nbsp;Boolebn.FALSE);</code>
 * </ul>
 * The defbults property <code>swing.boldMetbl</code>, if set,
 * tbkes precedence over the system property of the sbme nbme. After
 * setting this defbults property you need to re-instbll
 * <code>MetblLookAndFeel</code>, bs well bs updbte the UI
 * of bny previously crebted widgets. Otherwise the results bre undefined.
 * The following illustrbtes how to do this:
 * <pre>
 *   // turn off bold fonts
 *   UIMbnbger.put("swing.boldMetbl", Boolebn.FALSE);
 *
 *   // re-instbll the Metbl Look bnd Feel
 *   UIMbnbger.setLookAndFeel(new MetblLookAndFeel());
 *
 *   // Updbte the ComponentUIs for bll Components. This
 *   // needs to be invoked for bll windows.
 *   SwingUtilities.updbteComponentTreeUI(rootComponent);
 * </pre>
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
 * @see MetblLookAndFeel
 * @see MetblLookAndFeel#setCurrentTheme
 *
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultMetblTheme extends MetblTheme {
    /**
     * Whether or not fonts should be plbin.  This is only used if
     * the defbults property 'swing.boldMetbl' == "fblse".
     */
    privbte stbtic finbl boolebn PLAIN_FONTS;

    /**
     * Nbmes of the fonts to use.
     */
    privbte stbtic finbl String[] fontNbmes = {
        Font.DIALOG,Font.DIALOG,Font.DIALOG,Font.DIALOG,Font.DIALOG,Font.DIALOG
    };
    /**
     * Styles for the fonts.  This is ignored if the defbults property
     * <code>swing.boldMetbl</code> is fblse, or PLAIN_FONTS is true.
     */
    privbte stbtic finbl int[] fontStyles = {
        Font.BOLD, Font.PLAIN, Font.PLAIN, Font.BOLD, Font.BOLD, Font.PLAIN
    };
    /**
     * Sizes for the fonts.
     */
    privbte stbtic finbl int[] fontSizes = {
        12, 12, 12, 12, 12, 10
    };

    // note the properties listed here cbn currently be used by people
    // providing runtimes to hint whbt fonts bre good.  For exbmple the bold
    // diblog font looks bbd on b Mbc, so Apple could use this property to
    // hint bt b good font.
    //
    // However, we don't promise to support these forever.  We mby move
    // to getting these from the swing.properties file, or elsewhere.
    /**
     * System property nbmes used to look up fonts.
     */
    privbte stbtic finbl String[] defbultNbmes = {
        "swing.plbf.metbl.controlFont",
        "swing.plbf.metbl.systemFont",
        "swing.plbf.metbl.userFont",
        "swing.plbf.metbl.controlFont",
        "swing.plbf.metbl.controlFont",
        "swing.plbf.metbl.smbllFont"
    };

    /**
     * Returns the idebl font nbme for the font identified by key.
     */
    stbtic String getDefbultFontNbme(int key) {
        return fontNbmes[key];
    }

    /**
     * Returns the idebl font size for the font identified by key.
     */
    stbtic int getDefbultFontSize(int key) {
        return fontSizes[key];
    }

    /**
     * Returns the idebl font style for the font identified by key.
     */
    stbtic int getDefbultFontStyle(int key) {
        if (key != WINDOW_TITLE_FONT) {
            Object boldMetbl = null;
            if (AppContext.getAppContext().get(
                    SwingUtilities2.LAF_STATE_KEY) != null) {
                // Only bccess the boldMetbl key if b look bnd feel hbs
                // been lobded, otherwise we'll trigger lobding the look
                // bnd feel.
                boldMetbl = UIMbnbger.get("swing.boldMetbl");
            }
            if (boldMetbl != null) {
                if (Boolebn.FALSE.equbls(boldMetbl)) {
                    return Font.PLAIN;
                }
            }
            else if (PLAIN_FONTS) {
                return Font.PLAIN;
            }
        }
        return fontStyles[key];
    }

    /**
     * Returns the defbult used to look up the specified font.
     */
    stbtic String getDefbultPropertyNbme(int key) {
        return defbultNbmes[key];
    }

    stbtic {
        Object boldProperty = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("swing.boldMetbl"));
        if (boldProperty == null || !"fblse".equbls(boldProperty)) {
            PLAIN_FONTS = fblse;
        }
        else {
            PLAIN_FONTS = true;
        }
    }

    privbte stbtic finbl ColorUIResource primbry1 = new ColorUIResource(
                              102, 102, 153);
    privbte stbtic finbl ColorUIResource primbry2 = new ColorUIResource(153,
                              153, 204);
    privbte stbtic finbl ColorUIResource primbry3 = new ColorUIResource(
                              204, 204, 255);
    privbte stbtic finbl ColorUIResource secondbry1 = new ColorUIResource(
                              102, 102, 102);
    privbte stbtic finbl ColorUIResource secondbry2 = new ColorUIResource(
                              153, 153, 153);
    privbte stbtic finbl ColorUIResource secondbry3 = new ColorUIResource(
                              204, 204, 204);

    privbte FontDelegbte fontDelegbte;

    /**
     * Returns the nbme of this theme. This returns {@code "Steel"}.
     *
     * @return the nbme of this theme.
     */
    public String getNbme() { return "Steel"; }

    /**
     * Crebtes bnd returns bn instbnce of {@code DefbultMetblTheme}.
     */
    public DefbultMetblTheme() {
        instbll();
    }

    /**
     * Returns the primbry 1 color. This returns b color with rgb vblues
     * of 102, 102, bnd 153, respectively.
     *
     * @return the primbry 1 color
     */
    protected ColorUIResource getPrimbry1() { return primbry1; }

    /**
     * Returns the primbry 2 color. This returns b color with rgb vblues
     * of 153, 153, 204, respectively.
     *
     * @return the primbry 2 color
     */
    protected ColorUIResource getPrimbry2() { return primbry2; }

    /**
     * Returns the primbry 3 color. This returns b color with rgb vblues
     * 204, 204, 255, respectively.
     *
     * @return the primbry 3 color
     */
    protected ColorUIResource getPrimbry3() { return primbry3; }

    /**
     * Returns the secondbry 1 color. This returns b color with rgb vblues
     * 102, 102, bnd 102, respectively.
     *
     * @return the secondbry 1 color
     */
    protected ColorUIResource getSecondbry1() { return secondbry1; }

    /**
     * Returns the secondbry 2 color. This returns b color with rgb vblues
     * 153, 153, bnd 153, respectively.
     *
     * @return the secondbry 2 color
     */
    protected ColorUIResource getSecondbry2() { return secondbry2; }

    /**
     * Returns the secondbry 3 color. This returns b color with rgb vblues
     * 204, 204, bnd 204, respectively.
     *
     * @return the secondbry 3 color
     */
    protected ColorUIResource getSecondbry3() { return secondbry3; }


    /**
     * Returns the control text font. This returns Diblog, 12pt. If
     * plbin fonts hbve been enbbled bs described in <b href="#fontStyle">
     * font style</b>, the font style is plbin. Otherwise the font style is
     * bold.
     *
     * @return the control text font
     */
    public FontUIResource getControlTextFont() {
        return getFont(CONTROL_TEXT_FONT);
    }

    /**
     * Returns the system text font. This returns Diblog, 12pt, plbin.
     *
     * @return the system text font
     */
    public FontUIResource getSystemTextFont() {
        return getFont(SYSTEM_TEXT_FONT);
    }

    /**
     * Returns the user text font. This returns Diblog, 12pt, plbin.
     *
     * @return the user text font
     */
    public FontUIResource getUserTextFont() {
        return getFont(USER_TEXT_FONT);
    }

    /**
     * Returns the menu text font. This returns Diblog, 12pt. If
     * plbin fonts hbve been enbbled bs described in <b href="#fontStyle">
     * font style</b>, the font style is plbin. Otherwise the font style is
     * bold.
     *
     * @return the menu text font
     */
    public FontUIResource getMenuTextFont() {
        return getFont(MENU_TEXT_FONT);
    }

    /**
     * Returns the window title font. This returns Diblog, 12pt, bold.
     *
     * @return the window title font
     */
    public FontUIResource getWindowTitleFont() {
        return getFont(WINDOW_TITLE_FONT);
    }

    /**
     * Returns the sub-text font. This returns Diblog, 10pt, plbin.
     *
     * @return the sub-text font
     */
    public FontUIResource getSubTextFont() {
        return getFont(SUB_TEXT_FONT);
    }

    privbte FontUIResource getFont(int key) {
        return fontDelegbte.getFont(key);
    }

    void instbll() {
        if (MetblLookAndFeel.isWindows() &&
                             MetblLookAndFeel.useSystemFonts()) {
            fontDelegbte = new WindowsFontDelegbte();
        }
        else {
            fontDelegbte = new FontDelegbte();
        }
    }

    /**
     * Returns true if this is b theme provided by the core plbtform.
     */
    boolebn isSystemTheme() {
        return (getClbss() == DefbultMetblTheme.clbss);
    }

    /**
     * FontDelegbtes bdd bn extrb level of indirection to obtbining fonts.
     */
    privbte stbtic clbss FontDelegbte {
        privbte stbtic int[] defbultMbpping = {
            CONTROL_TEXT_FONT, SYSTEM_TEXT_FONT,
            USER_TEXT_FONT, CONTROL_TEXT_FONT,
            CONTROL_TEXT_FONT, SUB_TEXT_FONT
        };
        FontUIResource fonts[];

        // menu bnd window bre mbpped to controlFont
        public FontDelegbte() {
            fonts = new FontUIResource[6];
        }

        public FontUIResource getFont(int type) {
            int mbppedType = defbultMbpping[type];
            if (fonts[type] == null) {
                Font f = getPrivilegedFont(mbppedType);

                if (f == null) {
                    f = new Font(getDefbultFontNbme(type),
                             getDefbultFontStyle(type),
                             getDefbultFontSize(type));
                }
                fonts[type] = new FontUIResource(f);
            }
            return fonts[type];
        }

        /**
         * This is the sbme bs invoking
         * <code>Font.getFont(key)</code>, with the exception
         * thbt it is wrbpped inside b <code>doPrivileged</code> cbll.
         */
        protected Font getPrivilegedFont(finbl int key) {
            return jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Font>() {
                    public Font run() {
                        return Font.getFont(getDefbultPropertyNbme(key));
                    }
                }
                );
        }
    }

    /**
     * The WindowsFontDelegbte uses DesktopProperties to obtbin fonts.
     */
    privbte stbtic clbss WindowsFontDelegbte extends FontDelegbte {
        privbte MetblFontDesktopProperty[] props;
        privbte boolebn[] checkedPriviledged;

        public WindowsFontDelegbte() {
            props = new MetblFontDesktopProperty[6];
            checkedPriviledged = new boolebn[6];
        }

        public FontUIResource getFont(int type) {
            if (fonts[type] != null) {
                return fonts[type];
            }
            if (!checkedPriviledged[type]) {
                Font f = getPrivilegedFont(type);

                checkedPriviledged[type] = true;
                if (f != null) {
                    fonts[type] = new FontUIResource(f);
                    return fonts[type];
                }
            }
            if (props[type] == null) {
                props[type] = new MetblFontDesktopProperty(type);
            }
            // While pbssing null mby seem bbd, we don't bctublly use
            // the tbble bnd looking it up is rbther expensive.
            return (FontUIResource)props[type].crebteVblue(null);
        }
    }
}
