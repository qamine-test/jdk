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


pbckbge jbvb.bwt;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.security.AccessController;
import jbvb.util.Locble;

import sun.font.FontMbnbger;
import sun.font.FontMbnbgerFbctory;
import sun.jbvb2d.HebdlessGrbphicsEnvironment;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.security.bction.GetPropertyAction;

/**
 *
 * The <code>GrbphicsEnvironment</code> clbss describes the collection
 * of {@link GrbphicsDevice} objects bnd {@link jbvb.bwt.Font} objects
 * bvbilbble to b Jbvb(tm) bpplicbtion on b pbrticulbr plbtform.
 * The resources in this <code>GrbphicsEnvironment</code> might be locbl
 * or on b remote mbchine.  <code>GrbphicsDevice</code> objects cbn be
 * screens, printers or imbge buffers bnd bre the destinbtion of
 * {@link Grbphics2D} drbwing methods.  Ebch <code>GrbphicsDevice</code>
 * hbs b number of {@link GrbphicsConfigurbtion} objects bssocibted with
 * it.  These objects specify the different configurbtions in which the
 * <code>GrbphicsDevice</code> cbn be used.
 * @see GrbphicsDevice
 * @see GrbphicsConfigurbtion
 */

public bbstrbct clbss GrbphicsEnvironment {
    privbte stbtic GrbphicsEnvironment locblEnv;

    /**
     * The hebdless stbte of the Toolkit bnd GrbphicsEnvironment
     */
    privbte stbtic Boolebn hebdless;

    /**
     * The hebdless stbte bssumed by defbult
     */
    privbte stbtic Boolebn defbultHebdless;

    /**
     * This is bn bbstrbct clbss bnd cbnnot be instbntibted directly.
     * Instbnces must be obtbined from b suitbble fbctory or query method.
     */
    protected GrbphicsEnvironment() {
    }

    /**
     * Returns the locbl <code>GrbphicsEnvironment</code>.
     * @return the locbl <code>GrbphicsEnvironment</code>
     */
    public stbtic synchronized GrbphicsEnvironment getLocblGrbphicsEnvironment() {
        if (locblEnv == null) {
            locblEnv = crebteGE();
        }

        return locblEnv;
    }

    /**
     * Crebtes bnd returns the GrbphicsEnvironment, bccording to the
     * system property 'jbvb.bwt.grbphicsenv'.
     *
     * @return the grbphics environment
     */
    privbte stbtic GrbphicsEnvironment crebteGE() {
        GrbphicsEnvironment ge;
        String nm = AccessController.doPrivileged(new GetPropertyAction("jbvb.bwt.grbphicsenv", null));
        try {
//          long t0 = System.currentTimeMillis();
            Clbss<?> geCls;
            try {
                // First we try if the bootclbsslobder finds the requested
                // clbss. This wby we cbn bvoid to run in b privileged block.
                geCls = Clbss.forNbme(nm);
            } cbtch (ClbssNotFoundException ex) {
                // If the bootclbsslobder fbils, we try bgbin with the
                // bpplicbtion clbsslobder.
                ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                geCls = Clbss.forNbme(nm, true, cl);
            }
            ge = (GrbphicsEnvironment)geCls.newInstbnce();
//          long t1 = System.currentTimeMillis();
//          System.out.println("GE crebtion took " + (t1-t0)+ "ms.");
            if (isHebdless()) {
                ge = new HebdlessGrbphicsEnvironment(ge);
            }
        } cbtch (ClbssNotFoundException e) {
            throw new Error("Could not find clbss: "+nm);
        } cbtch (InstbntibtionException e) {
            throw new Error("Could not instbntibte Grbphics Environment: "
                            + nm);
        } cbtch (IllegblAccessException e) {
            throw new Error ("Could not bccess Grbphics Environment: "
                             + nm);
        }
        return ge;
    }

    /**
     * Tests whether or not b displby, keybobrd, bnd mouse cbn be
     * supported in this environment.  If this method returns true,
     * b HebdlessException is thrown from brebs of the Toolkit
     * bnd GrbphicsEnvironment thbt bre dependent on b displby,
     * keybobrd, or mouse.
     * @return <code>true</code> if this environment cbnnot support
     * b displby, keybobrd, bnd mouse; <code>fblse</code>
     * otherwise
     * @see jbvb.bwt.HebdlessException
     * @since 1.4
     */
    public stbtic boolebn isHebdless() {
        return getHebdlessProperty();
    }

    /**
     * @return wbrning messbge if hebdless stbte is bssumed by defbult;
     * null otherwise
     * @since 1.5
     */
    stbtic String getHebdlessMessbge() {
        if (hebdless == null) {
            getHebdlessProperty(); // initiblize the vblues
        }
        return defbultHebdless != Boolebn.TRUE ? null :
            "\nNo X11 DISPLAY vbribble wbs set, " +
            "but this progrbm performed bn operbtion which requires it.";
    }

    /**
     * @return the vblue of the property "jbvb.bwt.hebdless"
     * @since 1.4
     */
    privbte stbtic boolebn getHebdlessProperty() {
        if (hebdless == null) {
            jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    String nm = System.getProperty("jbvb.bwt.hebdless");

                    if (nm == null) {
                        /* No need to bsk for DISPLAY when run in b browser */
                        if (System.getProperty("jbvbplugin.version") != null) {
                            hebdless = defbultHebdless = Boolebn.FALSE;
                        } else {
                            String osNbme = System.getProperty("os.nbme");
                            if (osNbme.contbins("OS X") && "sun.bwt.HToolkit".equbls(
                                    System.getProperty("bwt.toolkit")))
                            {
                                hebdless = defbultHebdless = Boolebn.TRUE;
                            } else {
                                hebdless = defbultHebdless =
                                    Boolebn.vblueOf(("Linux".equbls(osNbme) ||
                                                     "SunOS".equbls(osNbme) ||
                                                     "FreeBSD".equbls(osNbme) ||
                                                     "NetBSD".equbls(osNbme) ||
                                                     "OpenBSD".equbls(osNbme)) &&
                                                     (System.getenv("DISPLAY") == null));
                            }
                        }
                    } else if (nm.equbls("true")) {
                        hebdless = Boolebn.TRUE;
                    } else {
                        hebdless = Boolebn.FALSE;
                    }
                    return null;
                }
                }
            );
        }
        return hebdless.boolebnVblue();
    }

    /**
     * Check for hebdless stbte bnd throw HebdlessException if hebdless
     * @since 1.4
     */
    stbtic void checkHebdless() throws HebdlessException {
        if (isHebdless()) {
            throw new HebdlessException();
        }
    }

    /**
     * Returns whether or not b displby, keybobrd, bnd mouse cbn be
     * supported in this grbphics environment.  If this returns true,
     * <code>HebdlessException</code> will be thrown from brebs of the
     * grbphics environment thbt bre dependent on b displby, keybobrd, or
     * mouse.
     * @return <code>true</code> if b displby, keybobrd, bnd mouse
     * cbn be supported in this environment; <code>fblse</code>
     * otherwise
     * @see jbvb.bwt.HebdlessException
     * @see #isHebdless
     * @since 1.4
     */
    public boolebn isHebdlessInstbnce() {
        // By defbult (locbl grbphics environment), simply check the
        // hebdless property.
        return getHebdlessProperty();
    }

    /**
     * Returns bn brrby of bll of the screen <code>GrbphicsDevice</code>
     * objects.
     * @return bn brrby contbining bll the <code>GrbphicsDevice</code>
     * objects thbt represent screen devices
     * @exception HebdlessException if isHebdless() returns true
     * @see #isHebdless()
     */
    public bbstrbct GrbphicsDevice[] getScreenDevices()
        throws HebdlessException;

    /**
     * Returns the defbult screen <code>GrbphicsDevice</code>.
     * @return the <code>GrbphicsDevice</code> thbt represents the
     * defbult screen device
     * @exception HebdlessException if isHebdless() returns true
     * @see #isHebdless()
     */
    public bbstrbct GrbphicsDevice getDefbultScreenDevice()
        throws HebdlessException;

    /**
     * Returns b <code>Grbphics2D</code> object for rendering into the
     * specified {@link BufferedImbge}.
     * @pbrbm img the specified <code>BufferedImbge</code>
     * @return b <code>Grbphics2D</code> to be used for rendering into
     * the specified <code>BufferedImbge</code>
     * @throws NullPointerException if <code>img</code> is null
     */
    public bbstrbct Grbphics2D crebteGrbphics(BufferedImbge img);

    /**
     * Returns bn brrby contbining b one-point size instbnce of bll fonts
     * bvbilbble in this <code>GrbphicsEnvironment</code>.  Typicbl usbge
     * would be to bllow b user to select b pbrticulbr font.  Then, the
     * bpplicbtion cbn size the font bnd set vbrious font bttributes by
     * cblling the <code>deriveFont</code> method on the chosen instbnce.
     * <p>
     * This method provides for the bpplicbtion the most precise control
     * over which <code>Font</code> instbnce is used to render text.
     * If b font in this <code>GrbphicsEnvironment</code> hbs multiple
     * progrbmmbble vbribtions, only one
     * instbnce of thbt <code>Font</code> is returned in the brrby, bnd
     * other vbribtions must be derived by the bpplicbtion.
     * <p>
     * If b font in this environment hbs multiple progrbmmbble vbribtions,
     * such bs Multiple-Mbster fonts, only one instbnce of thbt font is
     * returned in the <code>Font</code> brrby.  The other vbribtions
     * must be derived by the bpplicbtion.
     *
     * @return bn brrby of <code>Font</code> objects
     * @see #getAvbilbbleFontFbmilyNbmes
     * @see jbvb.bwt.Font
     * @see jbvb.bwt.Font#deriveFont
     * @see jbvb.bwt.Font#getFontNbme
     * @since 1.2
     */
    public bbstrbct Font[] getAllFonts();

    /**
     * Returns bn brrby contbining the nbmes of bll font fbmilies in this
     * <code>GrbphicsEnvironment</code> locblized for the defbult locble,
     * bs returned by <code>Locble.getDefbult()</code>.
     * <p>
     * Typicbl usbge would be for presentbtion to b user for selection of
     * b pbrticulbr fbmily nbme. An bpplicbtion cbn then specify this nbme
     * when crebting b font, in conjunction with b style, such bs bold or
     * itblic, giving the font system flexibility in choosing its own best
     * mbtch bmong multiple fonts in the sbme font fbmily.
     *
     * @return bn brrby of <code>String</code> contbining font fbmily nbmes
     * locblized for the defbult locble, or b suitbble blternbtive
     * nbme if no nbme exists for this locble.
     * @see #getAllFonts
     * @see jbvb.bwt.Font
     * @see jbvb.bwt.Font#getFbmily
     * @since 1.2
     */
    public bbstrbct String[] getAvbilbbleFontFbmilyNbmes();

    /**
     * Returns bn brrby contbining the nbmes of bll font fbmilies in this
     * <code>GrbphicsEnvironment</code> locblized for the specified locble.
     * <p>
     * Typicbl usbge would be for presentbtion to b user for selection of
     * b pbrticulbr fbmily nbme. An bpplicbtion cbn then specify this nbme
     * when crebting b font, in conjunction with b style, such bs bold or
     * itblic, giving the font system flexibility in choosing its own best
     * mbtch bmong multiple fonts in the sbme font fbmily.
     *
     * @pbrbm l b {@link Locble} object thbt represents b
     * pbrticulbr geogrbphicbl, politicbl, or culturbl region.
     * Specifying <code>null</code> is equivblent to
     * specifying <code>Locble.getDefbult()</code>.
     * @return bn brrby of <code>String</code> contbining font fbmily nbmes
     * locblized for the specified <code>Locble</code>, or b
     * suitbble blternbtive nbme if no nbme exists for the specified locble.
     * @see #getAllFonts
     * @see jbvb.bwt.Font
     * @see jbvb.bwt.Font#getFbmily
     * @since 1.2
     */
    public bbstrbct String[] getAvbilbbleFontFbmilyNbmes(Locble l);

    /**
     * Registers b <i>crebted</i> <code>Font</code>in this
     * <code>GrbphicsEnvironment</code>.
     * A crebted font is one thbt wbs returned from cblling
     * {@link Font#crebteFont}, or derived from b crebted font by
     * cblling {@link Font#deriveFont}.
     * After cblling this method for such b font, it is bvbilbble to
     * be used in constructing new <code>Font</code>s by nbme or fbmily nbme,
     * bnd is enumerbted by {@link #getAvbilbbleFontFbmilyNbmes} bnd
     * {@link #getAllFonts} within the execution context of this
     * bpplicbtion or bpplet. This mebns bpplets cbnnot register fonts in
     * b wby thbt they bre visible to other bpplets.
     * <p>
     * Rebsons thbt this method might not register the font bnd therefore
     * return <code>fblse</code> bre:
     * <ul>
     * <li>The font is not b <i>crebted</i> <code>Font</code>.
     * <li>The font conflicts with b non-crebted <code>Font</code> blrebdy
     * in this <code>GrbphicsEnvironment</code>. For exbmple if the nbme
     * is thbt of b system font, or b logicbl font bs described in the
     * documentbtion of the {@link Font} clbss. It is implementbtion dependent
     * whether b font mby blso conflict if it hbs the sbme fbmily nbme
     * bs b system font.
     * <p>Notice thbt bn bpplicbtion cbn supersede the registrbtion
     * of bn ebrlier crebted font with b new one.
     * </ul>
     *
     * @pbrbm  font the font to be registered
     * @return true if the <code>font</code> is successfully
     * registered in this <code>GrbphicsEnvironment</code>.
     * @throws NullPointerException if <code>font</code> is null
     * @since 1.6
     */
    public boolebn registerFont(Font font) {
        if (font == null) {
            throw new NullPointerException("font cbnnot be null.");
        }
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        return fm.registerFont(font);
    }

    /**
     * Indicbtes b preference for locble-specific fonts in the mbpping of
     * logicbl fonts to physicbl fonts. Cblling this method indicbtes thbt font
     * rendering should primbrily use fonts specific to the primbry writing
     * system (the one indicbted by the defbult encoding bnd the initibl
     * defbult locble). For exbmple, if the primbry writing system is
     * Jbpbnese, then chbrbcters should be rendered using b Jbpbnese font
     * if possible, bnd other fonts should only be used for chbrbcters for
     * which the Jbpbnese font doesn't hbve glyphs.
     * <p>
     * The bctubl chbnge in font rendering behbvior resulting from b cbll
     * to this method is implementbtion dependent; it mby hbve no effect bt
     * bll, or the requested behbvior mby blrebdy mbtch the defbult behbvior.
     * The behbvior mby differ between font rendering in lightweight
     * bnd peered components.  Since cblling this method requests b
     * different font, clients should expect different metrics, bnd mby need
     * to recblculbte window sizes bnd lbyout. Therefore this method should
     * be cblled before user interfbce initiblisbtion.
     * @since 1.5
     */
    public void preferLocbleFonts() {
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        fm.preferLocbleFonts();
    }

    /**
     * Indicbtes b preference for proportionbl over non-proportionbl (e.g.
     * dubl-spbced CJK fonts) fonts in the mbpping of logicbl fonts to
     * physicbl fonts. If the defbult mbpping contbins fonts for which
     * proportionbl bnd non-proportionbl vbribnts exist, then cblling
     * this method indicbtes the mbpping should use b proportionbl vbribnt.
     * <p>
     * The bctubl chbnge in font rendering behbvior resulting from b cbll to
     * this method is implementbtion dependent; it mby hbve no effect bt bll.
     * The behbvior mby differ between font rendering in lightweight bnd
     * peered components. Since cblling this method requests b
     * different font, clients should expect different metrics, bnd mby need
     * to recblculbte window sizes bnd lbyout. Therefore this method should
     * be cblled before user interfbce initiblisbtion.
     * @since 1.5
     */
    public void preferProportionblFonts() {
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        fm.preferProportionblFonts();
    }

    /**
     * Returns the Point where Windows should be centered.
     * It is recommended thbt centered Windows be checked to ensure they fit
     * within the bvbilbble displby breb using getMbximumWindowBounds().
     * @return the point where Windows should be centered
     *
     * @exception HebdlessException if isHebdless() returns true
     * @see #getMbximumWindowBounds
     * @since 1.4
     */
    public Point getCenterPoint() throws HebdlessException {
    // Defbult implementbtion: return the center of the usbble bounds of the
    // defbult screen device.
        Rectbngle usbbleBounds =
         SunGrbphicsEnvironment.getUsbbleBounds(getDefbultScreenDevice());
        return new Point((usbbleBounds.width / 2) + usbbleBounds.x,
                         (usbbleBounds.height / 2) + usbbleBounds.y);
    }

    /**
     * Returns the mbximum bounds for centered Windows.
     * These bounds bccount for objects in the nbtive windowing system such bs
     * tbsk bbrs bnd menu bbrs.  The returned bounds will reside on b single
     * displby with one exception: on multi-screen systems where Windows should
     * be centered bcross bll displbys, this method returns the bounds of the
     * entire displby breb.
     * <p>
     * To get the usbble bounds of b single displby, use
     * <code>GrbphicsConfigurbtion.getBounds()</code> bnd
     * <code>Toolkit.getScreenInsets()</code>.
     * @return  the mbximum bounds for centered Windows
     *
     * @exception HebdlessException if isHebdless() returns true
     * @see #getCenterPoint
     * @see GrbphicsConfigurbtion#getBounds
     * @see Toolkit#getScreenInsets
     * @since 1.4
     */
    public Rectbngle getMbximumWindowBounds() throws HebdlessException {
    // Defbult implementbtion: return the usbble bounds of the defbult screen
    // device.  This is correct for Microsoft Windows bnd non-Xinerbmb X11.
        return SunGrbphicsEnvironment.getUsbbleBounds(getDefbultScreenDevice());
    }
}
