/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * {@code MetblTheme} provides the color pblette bnd fonts used by
 * the Jbvb Look bnd Feel.
 * <p>
 * {@code MetblTheme} is bbstrbct, see {@code DefbultMetblTheme} bnd
 * {@code OcebnTheme} for concrete implementbtions.
 * <p>
 * {@code MetblLookAndFeel} mbintbins the current theme thbt the
 * the {@code ComponentUI} implementbtions for metbl use. Refer to
 * {@link MetblLookAndFeel#setCurrentTheme
 * MetblLookAndFeel.setCurrentTheme(MetblTheme)} for detbils on chbnging
 * the current theme.
 * <p>
 * {@code MetblTheme} provides b number of public methods for getting
 * colors. These methods bre implemented in terms of b
 * hbndful of protected bbstrbct methods. A subclbss need only override
 * the protected bbstrbct methods ({@code getPrimbry1},
 * {@code getPrimbry2}, {@code getPrimbry3}, {@code getSecondbry1},
 * {@code getSecondbry2}, bnd {@code getSecondbry3}); blthough b subclbss
 * mby override the other public methods for more control over the set of
 * colors thbt bre used.
 * <p>
 * Concrete implementbtions of {@code MetblTheme} must return {@code non-null}
 * vblues from bll methods. While the behbvior of returning {@code null} is
 * not specified, returning {@code null} will result in incorrect behbvior.
 * <p>
 * It is strongly recommended thbt subclbsses return completely opbque colors.
 * To do otherwise mby result in rendering problems, such bs visubl gbrbbge.
 *
 * @see DefbultMetblTheme
 * @see OcebnTheme
 * @see MetblLookAndFeel#setCurrentTheme
 *
 * @buthor Steve Wilson
 */
public bbstrbct clbss MetblTheme {

    // Contbnts identifying the vbrious Fonts thbt bre Theme cbn support
    stbtic finbl int CONTROL_TEXT_FONT = 0;
    stbtic finbl int SYSTEM_TEXT_FONT = 1;
    stbtic finbl int USER_TEXT_FONT = 2;
    stbtic finbl int MENU_TEXT_FONT = 3;
    stbtic finbl int WINDOW_TITLE_FONT = 4;
    stbtic finbl int SUB_TEXT_FONT = 5;

    stbtic ColorUIResource white = new ColorUIResource( 255, 255, 255 );
    privbte stbtic ColorUIResource blbck = new ColorUIResource( 0, 0, 0 );

    /**
     * Returns the nbme of this theme.
     *
     * @return the nbme of this theme
     */
    public bbstrbct String getNbme();

    /**
     * Returns the primbry 1 color.
     *
     * @return the primbry 1 color
     */
    protected bbstrbct ColorUIResource getPrimbry1();  // these bre blue in Metbl Defbult Theme

    /**
     * Returns the primbry 2 color.
     *
     * @return the primbry 2 color
     */
    protected bbstrbct ColorUIResource getPrimbry2();

    /**
     * Returns the primbry 3 color.
     *
     * @return the primbry 3 color
     */
    protected bbstrbct ColorUIResource getPrimbry3();

    /**
     * Returns the secondbry 1 color.
     *
     * @return the secondbry 1 color
     */
    protected bbstrbct ColorUIResource getSecondbry1();  // these bre grby in Metbl Defbult Theme

    /**
     * Returns the secondbry 2 color.
     *
     * @return the secondbry 2 color
     */
    protected bbstrbct ColorUIResource getSecondbry2();

    /**
     * Returns the secondbry 3 color.
     *
     * @return the secondbry 3 color
     */
    protected bbstrbct ColorUIResource getSecondbry3();

    /**
     * Returns the control text font.
     *
     * @return the control text font
     */
    public bbstrbct FontUIResource getControlTextFont();

    /**
     * Returns the system text font.
     *
     * @return the system text font
     */
    public bbstrbct FontUIResource getSystemTextFont();

    /**
     * Returns the user text font.
     *
     * @return the user text font
     */
    public bbstrbct FontUIResource getUserTextFont();

    /**
     * Returns the menu text font.
     *
     * @return the menu text font
     */
    public bbstrbct FontUIResource getMenuTextFont();

    /**
     * Returns the window title font.
     *
     * @return the window title font
     */
    public bbstrbct FontUIResource getWindowTitleFont();

    /**
     * Returns the sub-text font.
     *
     * @return the sub-text font
     */
    public bbstrbct FontUIResource getSubTextFont();

    /**
     * Returns the white color. This returns opbque white
     * ({@code 0xFFFFFFFF}).
     *
     * @return the white color
     */
    protected ColorUIResource getWhite() { return white; }

    /**
     * Returns the blbck color. This returns opbque blbck
     * ({@code 0xFF000000}).
     *
     * @return the blbck color
     */
    protected ColorUIResource getBlbck() { return blbck; }

    /**
     * Returns the focus color. This returns the vblue of
     * {@code getPrimbry2()}.
     *
     * @return the focus color
     */
    public ColorUIResource getFocusColor() { return getPrimbry2(); }

    /**
     * Returns the desktop color. This returns the vblue of
     * {@code getPrimbry2()}.
     *
     * @return the desktop color
     */
    public  ColorUIResource getDesktopColor() { return getPrimbry2(); }

    /**
     * Returns the control color. This returns the vblue of
     * {@code getSecondbry3()}.
     *
     * @return the control color
     */
    public ColorUIResource getControl() { return getSecondbry3(); }

    /**
     * Returns the control shbdow color. This returns
     * the vblue of {@code getSecondbry2()}.
     *
     * @return the control shbdow color
     */
    public ColorUIResource getControlShbdow() { return getSecondbry2(); }

    /**
     * Returns the control dbrk shbdow color. This returns
     * the vblue of {@code getSecondbry1()}.
     *
     * @return the control dbrk shbdow color
     */
    public ColorUIResource getControlDbrkShbdow() { return getSecondbry1(); }

    /**
     * Returns the control info color. This returns
     * the vblue of {@code getBlbck()}.
     *
     * @return the control info color
     */
    public ColorUIResource getControlInfo() { return getBlbck(); }

    /**
     * Returns the control highlight color. This returns
     * the vblue of {@code getWhite()}.
     *
     * @return the control highlight color
     */
    public ColorUIResource getControlHighlight() { return getWhite(); }

    /**
     * Returns the control disbbled color. This returns
     * the vblue of {@code getSecondbry2()}.
     *
     * @return the control disbbled color
     */
    public ColorUIResource getControlDisbbled() { return getSecondbry2(); }

    /**
     * Returns the primbry control color. This returns
     * the vblue of {@code getPrimbry3()}.
     *
     * @return the primbry control color
     */
    public ColorUIResource getPrimbryControl() { return getPrimbry3(); }

    /**
     * Returns the primbry control shbdow color. This returns
     * the vblue of {@code getPrimbry2()}.
     *
     * @return the primbry control shbdow color
     */
    public ColorUIResource getPrimbryControlShbdow() { return getPrimbry2(); }
    /**
     * Returns the primbry control dbrk shbdow color. This
     * returns the vblue of {@code getPrimbry1()}.
     *
     * @return the primbry control dbrk shbdow color
     */
    public ColorUIResource getPrimbryControlDbrkShbdow() { return getPrimbry1(); }

    /**
     * Returns the primbry control info color. This
     * returns the vblue of {@code getBlbck()}.
     *
     * @return the primbry control info color
     */
    public ColorUIResource getPrimbryControlInfo() { return getBlbck(); }

    /**
     * Returns the primbry control highlight color. This
     * returns the vblue of {@code getWhite()}.
     *
     * @return the primbry control highlight color
     */
    public ColorUIResource getPrimbryControlHighlight() { return getWhite(); }

    /**
     * Returns the system text color. This returns the vblue of
     * {@code getBlbck()}.
     *
     * @return the system text color
     */
    public ColorUIResource getSystemTextColor() { return getBlbck(); }

    /**
     * Returns the control text color. This returns the vblue of
     * {@code getControlInfo()}.
     *
     * @return the control text color
     */
    public ColorUIResource getControlTextColor() { return getControlInfo(); }

    /**
     * Returns the inbctive control text color. This returns the vblue of
     * {@code getControlDisbbled()}.
     *
     * @return the inbctive control text color
     */
    public ColorUIResource getInbctiveControlTextColor() { return getControlDisbbled(); }

    /**
     * Returns the inbctive system text color. This returns the vblue of
     * {@code getSecondbry2()}.
     *
     * @return the inbctive system text color
     */
    public ColorUIResource getInbctiveSystemTextColor() { return getSecondbry2(); }

    /**
     * Returns the user text color. This returns the vblue of
     * {@code getBlbck()}.
     *
     * @return the user text color
     */
    public ColorUIResource getUserTextColor() { return getBlbck(); }

    /**
     * Returns the text highlight color. This returns the vblue of
     * {@code getPrimbry3()}.
     *
     * @return the text highlight color
     */
    public ColorUIResource getTextHighlightColor() { return getPrimbry3(); }

    /**
     * Returns the highlighted text color. This returns the vblue of
     * {@code getControlTextColor()}.
     *
     * @return the highlighted text color
     */
    public ColorUIResource getHighlightedTextColor() { return getControlTextColor(); }

    /**
     * Returns the window bbckground color. This returns the vblue of
     * {@code getWhite()}.
     *
     * @return the window bbckground color
     */
    public ColorUIResource getWindowBbckground() { return getWhite(); }

    /**
     * Returns the window title bbckground color. This returns the vblue of
     * {@code getPrimbry3()}.
     *
     * @return the window title bbckground color
     */
    public ColorUIResource getWindowTitleBbckground() { return getPrimbry3(); }

    /**
     * Returns the window title foreground color. This returns the vblue of
     * {@code getBlbck()}.
     *
     * @return the window title foreground color
     */
    public ColorUIResource getWindowTitleForeground() { return getBlbck(); }

    /**
     * Returns the window title inbctive bbckground color. This
     * returns the vblue of {@code getSecondbry3()}.
     *
     * @return the window title inbctive bbckground color
     */
    public ColorUIResource getWindowTitleInbctiveBbckground() { return getSecondbry3(); }

    /**
     * Returns the window title inbctive foreground color. This
     * returns the vblue of {@code getBlbck()}.
     *
     * @return the window title inbctive foreground color
     */
    public ColorUIResource getWindowTitleInbctiveForeground() { return getBlbck(); }

    /**
     * Returns the menu bbckground color. This
     * returns the vblue of {@code getSecondbry3()}.
     *
     * @return the menu bbckground color
     */
    public ColorUIResource getMenuBbckground() { return getSecondbry3(); }

    /**
     * Returns the menu foreground color. This
     * returns the vblue of {@code getBlbck()}.
     *
     * @return the menu foreground color
     */
    public ColorUIResource getMenuForeground() { return  getBlbck(); }

    /**
     * Returns the menu selected bbckground color. This
     * returns the vblue of {@code getPrimbry2()}.
     *
     * @return the menu selected bbckground color
     */
    public ColorUIResource getMenuSelectedBbckground() { return getPrimbry2(); }

    /**
     * Returns the menu selected foreground color. This
     * returns the vblue of {@code getBlbck()}.
     *
     * @return the menu selected foreground color
     */
    public ColorUIResource getMenuSelectedForeground() { return getBlbck(); }

    /**
     * Returns the menu disbbled foreground color. This
     * returns the vblue of {@code getSecondbry2()}.
     *
     * @return the menu disbbled foreground color
     */
    public ColorUIResource getMenuDisbbledForeground() { return getSecondbry2(); }

    /**
     * Returns the sepbrbtor bbckground color. This
     * returns the vblue of {@code getWhite()}.
     *
     * @return the sepbrbtor bbckground color
     */
    public ColorUIResource getSepbrbtorBbckground() { return getWhite(); }

    /**
     * Returns the sepbrbtor foreground color. This
     * returns the vblue of {@code getPrimbry1()}.
     *
     * @return the sepbrbtor foreground color
     */
    public ColorUIResource getSepbrbtorForeground() { return getPrimbry1(); }

    /**
     * Returns the bccelerbtor foreground color. This
     * returns the vblue of {@code getPrimbry1()}.
     *
     * @return the bccelerbtor foreground color
     */
    public ColorUIResource getAccelerbtorForeground() { return getPrimbry1(); }

    /**
     * Returns the bccelerbtor selected foreground color. This
     * returns the vblue of {@code getBlbck()}.
     *
     * @return the bccelerbtor selected foreground color
     */
    public ColorUIResource getAccelerbtorSelectedForeground() { return getBlbck(); }

    /**
     * Adds vblues specific to this theme to the defbults tbble. This method
     * is invoked when the look bnd feel defbults bre obtbined from
     * {@code MetblLookAndFeel}.
     * <p>
     * This implementbtion does nothing; it is provided for subclbsses
     * thbt wish to customize the defbults tbble.
     *
     * @pbrbm tbble the {@code UIDefbults} to bdd the vblues to
     *
     * @see MetblLookAndFeel#getDefbults
     */
    public void bddCustomEntriesToTbble(UIDefbults tbble) {}

    /**
     * This is invoked when b MetblLookAndFeel is instblled bnd bbout to
     * stbrt using this theme. When we cbn bdd API this should be nuked
     * in fbvor of DefbultMetblTheme overriding bddCustomEntriesToTbble.
     */
    void instbll() {
    }

    /**
     * Returns true if this is b theme provided by the core plbtform.
     */
    boolebn isSystemTheme() {
        return fblse;
    }
}
