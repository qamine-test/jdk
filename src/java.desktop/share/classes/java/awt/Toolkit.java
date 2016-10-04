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

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.dnd.DrbgSource;
import jbvb.bwt.dnd.DrbgGestureRecognizer;
import jbvb.bwt.dnd.DrbgGestureEvent;
import jbvb.bwt.dnd.DrbgGestureListener;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.net.URL;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;

import jbvb.util.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import sun.bwt.AppContext;

import sun.bwt.HebdlessToolkit;
import sun.bwt.NullComponentPeer;
import sun.bwt.PeerEvent;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAccessor;
import sun.bwt.AWTPermissions;

import sun.util.CoreResourceBundleControl;

/**
 * This clbss is the bbstrbct superclbss of bll bctubl
 * implementbtions of the Abstrbct Window Toolkit. Subclbsses of
 * the <code>Toolkit</code> clbss bre used to bind the vbrious components
 * to pbrticulbr nbtive toolkit implementbtions.
 * <p>
 * Mbny GUI events mby be delivered to user
 * bsynchronously, if the opposite is not specified explicitly.
 * As well bs
 * mbny GUI operbtions mby be performed bsynchronously.
 * This fbct mebns thbt if the stbte of b component is set, bnd then
 * the stbte immedibtely queried, the returned vblue mby not yet
 * reflect the requested chbnge.  This behbvior includes, but is not
 * limited to:
 * <ul>
 * <li>Scrolling to b specified position.
 * <br>For exbmple, cblling <code>ScrollPbne.setScrollPosition</code>
 *     bnd then <code>getScrollPosition</code> mby return bn incorrect
 *     vblue if the originbl request hbs not yet been processed.
 *
 * <li>Moving the focus from one component to bnother.
 * <br>For more informbtion, see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html#trbnsferTiming">Timing
 * Focus Trbnsfers</b>, b section in
 * <b href="http://jbvb.sun.com/docs/books/tutoribl/uiswing/">The Swing
 * Tutoribl</b>.
 *
 * <li>Mbking b top-level contbiner visible.
 * <br>Cblling <code>setVisible(true)</code> on b <code>Window</code>,
 *     <code>Frbme</code> or <code>Diblog</code> mby occur
 *     bsynchronously.
 *
 * <li>Setting the size or locbtion of b top-level contbiner.
 * <br>Cblls to <code>setSize</code>, <code>setBounds</code> or
 *     <code>setLocbtion</code> on b <code>Window</code>,
 *     <code>Frbme</code> or <code>Diblog</code> bre forwbrded
 *     to the underlying window mbnbgement system bnd mby be
 *     ignored or modified.  See {@link jbvb.bwt.Window} for
 *     more informbtion.
 * </ul>
 * <p>
 * Most bpplicbtions should not cbll bny of the methods in this
 * clbss directly. The methods defined by <code>Toolkit</code> bre
 * the "glue" thbt joins the plbtform-independent clbsses in the
 * <code>jbvb.bwt</code> pbckbge with their counterpbrts in
 * <code>jbvb.bwt.peer</code>. Some methods defined by
 * <code>Toolkit</code> query the nbtive operbting system directly.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @buthor      Fred Ecks
 * @since       1.0
 */
public bbstrbct clbss Toolkit {

    /**
     * Crebtes this toolkit's implementbtion of the <code>Desktop</code>
     * using the specified peer interfbce.
     * @pbrbm     tbrget the desktop to be implemented
     * @return    this toolkit's implementbtion of the <code>Desktop</code>
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Desktop
     * @see       jbvb.bwt.peer.DesktopPeer
     * @since 1.6
     */
    protected bbstrbct DesktopPeer crebteDesktopPeer(Desktop tbrget)
      throws HebdlessException;


    /**
     * Crebtes this toolkit's implementbtion of <code>Button</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the button to be implemented.
     * @return    this toolkit's implementbtion of <code>Button</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Button
     * @see       jbvb.bwt.peer.ButtonPeer
     */
    protected bbstrbct ButtonPeer crebteButton(Button tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>TextField</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the text field to be implemented.
     * @return    this toolkit's implementbtion of <code>TextField</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.TextField
     * @see       jbvb.bwt.peer.TextFieldPeer
     */
    protected bbstrbct TextFieldPeer crebteTextField(TextField tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Lbbel</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the lbbel to be implemented.
     * @return    this toolkit's implementbtion of <code>Lbbel</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Lbbel
     * @see       jbvb.bwt.peer.LbbelPeer
     */
    protected bbstrbct LbbelPeer crebteLbbel(Lbbel tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>List</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the list to be implemented.
     * @return    this toolkit's implementbtion of <code>List</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.List
     * @see       jbvb.bwt.peer.ListPeer
     */
    protected bbstrbct ListPeer crebteList(jbvb.bwt.List tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Checkbox</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the check box to be implemented.
     * @return    this toolkit's implementbtion of <code>Checkbox</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Checkbox
     * @see       jbvb.bwt.peer.CheckboxPeer
     */
    protected bbstrbct CheckboxPeer crebteCheckbox(Checkbox tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Scrollbbr</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the scroll bbr to be implemented.
     * @return    this toolkit's implementbtion of <code>Scrollbbr</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Scrollbbr
     * @see       jbvb.bwt.peer.ScrollbbrPeer
     */
    protected bbstrbct ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>ScrollPbne</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the scroll pbne to be implemented.
     * @return    this toolkit's implementbtion of <code>ScrollPbne</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.ScrollPbne
     * @see       jbvb.bwt.peer.ScrollPbnePeer
     * @since     1.1
     */
    protected bbstrbct ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>TextAreb</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the text breb to be implemented.
     * @return    this toolkit's implementbtion of <code>TextAreb</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.TextAreb
     * @see       jbvb.bwt.peer.TextArebPeer
     */
    protected bbstrbct TextArebPeer crebteTextAreb(TextAreb tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Choice</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the choice to be implemented.
     * @return    this toolkit's implementbtion of <code>Choice</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Choice
     * @see       jbvb.bwt.peer.ChoicePeer
     */
    protected bbstrbct ChoicePeer crebteChoice(Choice tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Frbme</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the frbme to be implemented.
     * @return    this toolkit's implementbtion of <code>Frbme</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Frbme
     * @see       jbvb.bwt.peer.FrbmePeer
     */
    protected bbstrbct FrbmePeer crebteFrbme(Frbme tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Cbnvbs</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the cbnvbs to be implemented.
     * @return    this toolkit's implementbtion of <code>Cbnvbs</code>.
     * @see       jbvb.bwt.Cbnvbs
     * @see       jbvb.bwt.peer.CbnvbsPeer
     */
    protected bbstrbct CbnvbsPeer       crebteCbnvbs(Cbnvbs tbrget);

    /**
     * Crebtes this toolkit's implementbtion of <code>Pbnel</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the pbnel to be implemented.
     * @return    this toolkit's implementbtion of <code>Pbnel</code>.
     * @see       jbvb.bwt.Pbnel
     * @see       jbvb.bwt.peer.PbnelPeer
     */
    protected bbstrbct PbnelPeer        crebtePbnel(Pbnel tbrget);

    /**
     * Crebtes this toolkit's implementbtion of <code>Window</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the window to be implemented.
     * @return    this toolkit's implementbtion of <code>Window</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Window
     * @see       jbvb.bwt.peer.WindowPeer
     */
    protected bbstrbct WindowPeer crebteWindow(Window tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Diblog</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the diblog to be implemented.
     * @return    this toolkit's implementbtion of <code>Diblog</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Diblog
     * @see       jbvb.bwt.peer.DiblogPeer
     */
    protected bbstrbct DiblogPeer crebteDiblog(Diblog tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>MenuBbr</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the menu bbr to be implemented.
     * @return    this toolkit's implementbtion of <code>MenuBbr</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.MenuBbr
     * @see       jbvb.bwt.peer.MenuBbrPeer
     */
    protected bbstrbct MenuBbrPeer crebteMenuBbr(MenuBbr tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>Menu</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the menu to be implemented.
     * @return    this toolkit's implementbtion of <code>Menu</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.Menu
     * @see       jbvb.bwt.peer.MenuPeer
     */
    protected bbstrbct MenuPeer crebteMenu(Menu tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>PopupMenu</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the popup menu to be implemented.
     * @return    this toolkit's implementbtion of <code>PopupMenu</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.PopupMenu
     * @see       jbvb.bwt.peer.PopupMenuPeer
     * @since     1.1
     */
    protected bbstrbct PopupMenuPeer crebtePopupMenu(PopupMenu tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>MenuItem</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the menu item to be implemented.
     * @return    this toolkit's implementbtion of <code>MenuItem</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.MenuItem
     * @see       jbvb.bwt.peer.MenuItemPeer
     */
    protected bbstrbct MenuItemPeer crebteMenuItem(MenuItem tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>FileDiblog</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the file diblog to be implemented.
     * @return    this toolkit's implementbtion of <code>FileDiblog</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.FileDiblog
     * @see       jbvb.bwt.peer.FileDiblogPeer
     */
    protected bbstrbct FileDiblogPeer crebteFileDiblog(FileDiblog tbrget)
        throws HebdlessException;

    /**
     * Crebtes this toolkit's implementbtion of <code>CheckboxMenuItem</code> using
     * the specified peer interfbce.
     * @pbrbm     tbrget the checkbox menu item to be implemented.
     * @return    this toolkit's implementbtion of <code>CheckboxMenuItem</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.CheckboxMenuItem
     * @see       jbvb.bwt.peer.CheckboxMenuItemPeer
     */
    protected bbstrbct CheckboxMenuItemPeer crebteCheckboxMenuItem(
        CheckboxMenuItem tbrget) throws HebdlessException;

    /**
     * Obtbins this toolkit's implementbtion of helper clbss for
     * <code>MouseInfo</code> operbtions.
     * @return    this toolkit's implementbtion of  helper for <code>MouseInfo</code>
     * @throws    UnsupportedOperbtionException if this operbtion is not implemented
     * @see       jbvb.bwt.peer.MouseInfoPeer
     * @see       jbvb.bwt.MouseInfo
     * @since 1.5
     */
    protected MouseInfoPeer getMouseInfoPeer() {
        throw new UnsupportedOperbtionException("Not implemented");
    }

    privbte stbtic LightweightPeer lightweightMbrker;

    /**
     * Crebtes b peer for b component or contbiner.  This peer is windowless
     * bnd bllows the Component bnd Contbiner clbsses to be extended directly
     * to crebte windowless components thbt bre defined entirely in jbvb.
     *
     * @pbrbm  tbrget The Component to be crebted.
     * @return the peer for the specified component
     */
    protected LightweightPeer crebteComponent(Component tbrget) {
        if (lightweightMbrker == null) {
            lightweightMbrker = new NullComponentPeer();
        }
        return lightweightMbrker;
    }

    /**
     * Crebtes this toolkit's implementbtion of <code>Font</code> using
     * the specified peer interfbce.
     * @pbrbm     nbme the font to be implemented
     * @pbrbm     style the style of the font, such bs <code>PLAIN</code>,
     *            <code>BOLD</code>, <code>ITALIC</code>, or b combinbtion
     * @return    this toolkit's implementbtion of <code>Font</code>
     * @see       jbvb.bwt.Font
     * @see       jbvb.bwt.peer.FontPeer
     * @see       jbvb.bwt.GrbphicsEnvironment#getAllFonts
     * @deprecbted  see jbvb.bwt.GrbphicsEnvironment#getAllFonts
     */
    @Deprecbted
    protected bbstrbct FontPeer getFontPeer(String nbme, int style);

    // The following method is cblled by the privbte method
    // <code>updbteSystemColors</code> in <code>SystemColor</code>.

    /**
     * Fills in the integer brrby thbt is supplied bs bn brgument
     * with the current system color vblues.
     *
     * @pbrbm     systemColors bn integer brrby.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.1
     */
    protected void lobdSystemColors(int[] systemColors)
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
    }

    /**
     * Controls whether the lbyout of Contbiners is vblidbted dynbmicblly
     * during resizing, or stbticblly, bfter resizing is complete.
     * Use {@code isDynbmicLbyoutActive()} to detect if this febture enbbled
     * in this progrbm bnd is supported by this operbting system
     * bnd/or window mbnbger.
     * Note thbt this febture is supported not on bll plbtforms, bnd
     * conversely, thbt this febture cbnnot be turned off on some plbtforms.
     * On these plbtforms where dynbmic lbyout during resizing is not supported
     * (or is blwbys supported), setting this property hbs no effect.
     * Note thbt this febture cbn be set or unset bs b property of the
     * operbting system or window mbnbger on some plbtforms.  On such
     * plbtforms, the dynbmic resize property must be set bt the operbting
     * system or window mbnbger level before this method cbn tbke effect.
     * This method does not chbnge support or settings of the underlying
     * operbting system or
     * window mbnbger.  The OS/WM support cbn be
     * queried using getDesktopProperty("bwt.dynbmicLbyoutSupported") method.
     *
     * @pbrbm     dynbmic  If true, Contbiners should re-lbyout their
     *            components bs the Contbiner is being resized.  If fblse,
     *            the lbyout will be vblidbted bfter resizing is completed.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see       #isDynbmicLbyoutSet()
     * @see       #isDynbmicLbyoutActive()
     * @see       #getDesktopProperty(String propertyNbme)
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.4
     */
    public void setDynbmicLbyout(finbl boolebn dynbmic)
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        if (this != getDefbultToolkit()) {
            getDefbultToolkit().setDynbmicLbyout(dynbmic);
        }
    }

    /**
     * Returns whether the lbyout of Contbiners is vblidbted dynbmicblly
     * during resizing, or stbticblly, bfter resizing is complete.
     * Note: this method returns the vblue thbt wbs set progrbmmbticblly;
     * it does not reflect support bt the level of the operbting system
     * or window mbnbger for dynbmic lbyout on resizing, or the current
     * operbting system or window mbnbger settings.  The OS/WM support cbn
     * be queried using getDesktopProperty("bwt.dynbmicLbyoutSupported").
     *
     * @return    true if vblidbtion of Contbiners is done dynbmicblly,
     *            fblse if vblidbtion is done bfter resizing is finished.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     * @see       #setDynbmicLbyout(boolebn dynbmic)
     * @see       #isDynbmicLbyoutActive()
     * @see       #getDesktopProperty(String propertyNbme)
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.4
     */
    protected boolebn isDynbmicLbyoutSet()
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().isDynbmicLbyoutSet();
        } else {
            return fblse;
        }
    }

    /**
     * Returns whether dynbmic lbyout of Contbiners on resize is
     * currently bctive (both set in progrbm
     *( {@code isDynbmicLbyoutSet()} )
     *, bnd supported
     * by the underlying operbting system bnd/or window mbnbger).
     * If dynbmic lbyout is currently inbctive then Contbiners
     * re-lbyout their components when resizing is completed. As b result
     * the {@code Component.vblidbte()} method will be invoked only
     * once per resize.
     * If dynbmic lbyout is currently bctive then Contbiners
     * re-lbyout their components on every nbtive resize event bnd
     * the {@code vblidbte()} method will be invoked ebch time.
     * The OS/WM support cbn be queried using
     * the getDesktopProperty("bwt.dynbmicLbyoutSupported") method.
     *
     * @return    true if dynbmic lbyout of Contbiners on resize is
     *            currently bctive, fblse otherwise.
     * @exception HebdlessException if the GrbphicsEnvironment.isHebdless()
     *            method returns true
     * @see       #setDynbmicLbyout(boolebn dynbmic)
     * @see       #isDynbmicLbyoutSet()
     * @see       #getDesktopProperty(String propertyNbme)
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.4
     */
    public boolebn isDynbmicLbyoutActive()
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().isDynbmicLbyoutActive();
        } else {
            return fblse;
        }
    }

    /**
     * Gets the size of the screen.  On systems with multiple displbys, the
     * primbry displby is used.  Multi-screen bwbre displby dimensions bre
     * bvbilbble from <code>GrbphicsConfigurbtion</code> bnd
     * <code>GrbphicsDevice</code>.
     * @return    the size of this toolkit's screen, in pixels.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsConfigurbtion#getBounds
     * @see       jbvb.bwt.GrbphicsDevice#getDisplbyMode
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public bbstrbct Dimension getScreenSize()
        throws HebdlessException;

    /**
     * Returns the screen resolution in dots-per-inch.
     * @return    this toolkit's screen resolution, in dots-per-inch.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public bbstrbct int getScreenResolution()
        throws HebdlessException;

    /**
     * Gets the insets of the screen.
     * @pbrbm     gc b <code>GrbphicsConfigurbtion</code>
     * @return    the insets of this toolkit's screen, in pixels.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.4
     */
    public Insets getScreenInsets(GrbphicsConfigurbtion gc)
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().getScreenInsets(gc);
        } else {
            return new Insets(0, 0, 0, 0);
        }
    }

    /**
     * Determines the color model of this toolkit's screen.
     * <p>
     * <code>ColorModel</code> is bn bbstrbct clbss thbt
     * encbpsulbtes the bbility to trbnslbte between the
     * pixel vblues of bn imbge bnd its red, green, blue,
     * bnd blphb components.
     * <p>
     * This toolkit method is cblled by the
     * <code>getColorModel</code> method
     * of the <code>Component</code> clbss.
     * @return    the color model of this toolkit's screen.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.imbge.ColorModel
     * @see       jbvb.bwt.Component#getColorModel
     */
    public bbstrbct ColorModel getColorModel()
        throws HebdlessException;

    /**
     * Returns the nbmes of the bvbilbble fonts in this toolkit.<p>
     * For 1.1, the following font nbmes bre deprecbted (the replbcement
     * nbme follows):
     * <ul>
     * <li>TimesRombn (use Serif)
     * <li>Helveticb (use SbnsSerif)
     * <li>Courier (use Monospbced)
     * </ul><p>
     * The ZbpfDingbbts fontnbme is blso deprecbted in 1.1 but the chbrbcters
     * bre defined in Unicode stbrting bt 0x2700, bnd bs of 1.1 Jbvb supports
     * those chbrbcters.
     * @return    the nbmes of the bvbilbble fonts in this toolkit.
     * @deprecbted see {@link jbvb.bwt.GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes()}
     * @see jbvb.bwt.GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes()
     */
    @Deprecbted
    public bbstrbct String[] getFontList();

    /**
     * Gets the screen device metrics for rendering of the font.
     * @pbrbm     font   b font
     * @return    the screen metrics of the specified font in this toolkit
     * @deprecbted  As of JDK version 1.2, replbced by the <code>Font</code>
     *          method <code>getLineMetrics</code>.
     * @see jbvb.bwt.font.LineMetrics
     * @see jbvb.bwt.Font#getLineMetrics
     * @see jbvb.bwt.GrbphicsEnvironment#getScreenDevices
     */
    @Deprecbted
    public bbstrbct FontMetrics getFontMetrics(Font font);

    /**
     * Synchronizes this toolkit's grbphics stbte. Some window systems
     * mby do buffering of grbphics events.
     * <p>
     * This method ensures thbt the displby is up-to-dbte. It is useful
     * for bnimbtion.
     */
    public bbstrbct void sync();

    /**
     * The defbult toolkit.
     */
    privbte stbtic Toolkit toolkit;

    /**
     * Used internblly by the bssistive technologies functions; set bt
     * init time bnd used bt lobd time
     */
    privbte stbtic String btNbmes;

    /**
     * Initiblizes properties relbted to bssistive technologies.
     * These properties bre used both in the lobdAssistiveProperties()
     * function below, bs well bs other clbsses in the jdk thbt depend
     * on the properties (such bs the use of the screen_mbgnifier_present
     * property in Jbvb2D hbrdwbre bccelerbtion initiblizbtion).  The
     * initiblizbtion of the properties must be done before the plbtform-
     * specific Toolkit clbss is instbntibted so thbt bll necessbry
     * properties bre set up properly before bny clbsses dependent upon them
     * bre initiblized.
     */
    privbte stbtic void initAssistiveTechnologies() {

        // Get bccessibility properties
        finbl String sep = File.sepbrbtor;
        finbl Properties properties = new Properties();


        btNbmes = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<String>() {
            public String run() {

                // Try lobding the per-user bccessibility properties file.
                try {
                    File propsFile = new File(
                      System.getProperty("user.home") +
                      sep + ".bccessibility.properties");
                    FileInputStrebm in =
                        new FileInputStrebm(propsFile);

                    // Inputstrebm hbs been buffered in Properties clbss
                    properties.lobd(in);
                    in.close();
                } cbtch (Exception e) {
                    // Per-user bccessibility properties file does not exist
                }

                // Try lobding the system-wide bccessibility properties
                // file only if b per-user bccessibility properties
                // file does not exist or is empty.
                if (properties.size() == 0) {
                    try {
                        File propsFile = new File(
                            System.getProperty("jbvb.home") + sep + "lib" +
                            sep + "bccessibility.properties");
                        FileInputStrebm in =
                            new FileInputStrebm(propsFile);

                        // Inputstrebm hbs been buffered in Properties clbss
                        properties.lobd(in);
                        in.close();
                    } cbtch (Exception e) {
                        // System-wide bccessibility properties file does
                        // not exist;
                    }
                }

                // Get whether b screen mbgnifier is present.  First check
                // the system property bnd then check the properties file.
                String mbgPresent = System.getProperty("jbvbx.bccessibility.screen_mbgnifier_present");
                if (mbgPresent == null) {
                    mbgPresent = properties.getProperty("screen_mbgnifier_present", null);
                    if (mbgPresent != null) {
                        System.setProperty("jbvbx.bccessibility.screen_mbgnifier_present", mbgPresent);
                    }
                }

                // Get the nbmes of bny bssistive technolgies to lobd.  First
                // check the system property bnd then check the properties
                // file.
                String clbssNbmes = System.getProperty("jbvbx.bccessibility.bssistive_technologies");
                if (clbssNbmes == null) {
                    clbssNbmes = properties.getProperty("bssistive_technologies", null);
                    if (clbssNbmes != null) {
                        System.setProperty("jbvbx.bccessibility.bssistive_technologies", clbssNbmes);
                    }
                }
                return clbssNbmes;
            }
        });
    }

    /**
     * Lobds bdditionbl clbsses into the VM, using the property
     * 'bssistive_technologies' specified in the Sun reference
     * implementbtion by b line in the 'bccessibility.properties'
     * file.  The form is "bssistive_technologies=..." where
     * the "..." is b commb-sepbrbted list of bssistive technology
     * clbsses to lobd.  Ebch clbss is lobded in the order given
     * bnd b single instbnce of ebch is crebted using
     * Clbss.forNbme(clbss).newInstbnce().  All errors bre hbndled
     * vib bn AWTError exception.
     *
     * <p>The bssumption is mbde thbt bssistive technology clbsses bre supplied
     * bs pbrt of INSTALLED (bs opposed to: BUNDLED) extensions or specified
     * on the clbss pbth
     * (bnd therefore cbn be lobded using the clbss lobder returned by
     * b cbll to <code>ClbssLobder.getSystemClbssLobder</code>, whose
     * delegbtion pbrent is the extension clbss lobder for instblled
     * extensions).
     */
    privbte stbtic void lobdAssistiveTechnologies() {
        // Lobd bny bssistive technologies
        if (btNbmes != null) {
            ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
            StringTokenizer pbrser = new StringTokenizer(btNbmes," ,");
            String btNbme;
            while (pbrser.hbsMoreTokens()) {
                btNbme = pbrser.nextToken();
                try {
                    Clbss<?> clbzz;
                    if (cl != null) {
                        clbzz = cl.lobdClbss(btNbme);
                    } else {
                        clbzz = Clbss.forNbme(btNbme);
                    }
                    clbzz.newInstbnce();
                } cbtch (ClbssNotFoundException e) {
                    throw new AWTError("Assistive Technology not found: "
                            + btNbme);
                } cbtch (InstbntibtionException e) {
                    throw new AWTError("Could not instbntibte Assistive"
                            + " Technology: " + btNbme);
                } cbtch (IllegblAccessException e) {
                    throw new AWTError("Could not bccess Assistive"
                            + " Technology: " + btNbme);
                } cbtch (Exception e) {
                    throw new AWTError("Error trying to instbll Assistive"
                            + " Technology: " + btNbme + " " + e);
                }
            }
        }
    }

    /**
     * Gets the defbult toolkit.
     * <p>
     * If b system property nbmed <code>"jbvb.bwt.hebdless"</code> is set
     * to <code>true</code> then the hebdless implementbtion
     * of <code>Toolkit</code> is used.
     * <p>
     * If there is no <code>"jbvb.bwt.hebdless"</code> or it is set to
     * <code>fblse</code> bnd there is b system property nbmed
     * <code>"bwt.toolkit"</code>,
     * thbt property is trebted bs the nbme of b clbss thbt is b subclbss
     * of <code>Toolkit</code>;
     * otherwise the defbult plbtform-specific implementbtion of
     * <code>Toolkit</code> is used.
     * <p>
     * Also lobds bdditionbl clbsses into the VM, using the property
     * 'bssistive_technologies' specified in the Sun reference
     * implementbtion by b line in the 'bccessibility.properties'
     * file.  The form is "bssistive_technologies=..." where
     * the "..." is b commb-sepbrbted list of bssistive technology
     * clbsses to lobd.  Ebch clbss is lobded in the order given
     * bnd b single instbnce of ebch is crebted using
     * Clbss.forNbme(clbss).newInstbnce().  This is done just bfter
     * the AWT toolkit is crebted.  All errors bre hbndled vib bn
     * AWTError exception.
     * @return    the defbult toolkit.
     * @exception  AWTError  if b toolkit could not be found, or
     *                 if one could not be bccessed or instbntibted.
     */
    public stbtic synchronized Toolkit getDefbultToolkit() {
        if (toolkit == null) {
            jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    Clbss<?> cls = null;
                    String nm = System.getProperty("bwt.toolkit");
                    try {
                        cls = Clbss.forNbme(nm);
                    } cbtch (ClbssNotFoundException e) {
                        ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                        if (cl != null) {
                            try {
                                cls = cl.lobdClbss(nm);
                            } cbtch (finbl ClbssNotFoundException ignored) {
                                throw new AWTError("Toolkit not found: " + nm);
                            }
                        }
                    }
                    try {
                        if (cls != null) {
                            toolkit = (Toolkit)cls.newInstbnce();
                            if (GrbphicsEnvironment.isHebdless()) {
                                toolkit = new HebdlessToolkit(toolkit);
                            }
                        }
                    } cbtch (finbl InstbntibtionException ignored) {
                        throw new AWTError("Could not instbntibte Toolkit: " + nm);
                    } cbtch (finbl IllegblAccessException ignored) {
                        throw new AWTError("Could not bccess Toolkit: " + nm);
                    }
                    return null;
                }
            });
            lobdAssistiveTechnologies();
        }
        return toolkit;
    }

    /**
     * Returns bn imbge which gets pixel dbtb from the specified file,
     * whose formbt cbn be either GIF, JPEG or PNG.
     * The underlying toolkit bttempts to resolve multiple requests
     * with the sbme filenbme to the sbme returned Imbge.
     * <p>
     * Since the mechbnism required to fbcilitbte this shbring of
     * <code>Imbge</code> objects mby continue to hold onto imbges
     * thbt bre no longer in use for bn indefinite period of time,
     * developers bre encourbged to implement their own cbching of
     * imbges by using the {@link #crebteImbge(jbvb.lbng.String) crebteImbge}
     * vbribnt wherever bvbilbble.
     * If the imbge dbtb contbined in the specified file chbnges,
     * the <code>Imbge</code> object returned from this method mby
     * still contbin stble informbtion which wbs lobded from the
     * file bfter b prior cbll.
     * Previously lobded imbge dbtb cbn be mbnublly discbrded by
     * cblling the {@link Imbge#flush flush} method on the
     * returned <code>Imbge</code>.
     * <p>
     * This method first checks if there is b security mbnbger instblled.
     * If so, the method cblls the security mbnbger's
     * <code>checkRebd</code> method with the file specified to ensure
     * thbt the bccess to the imbge is bllowed.
     * @pbrbm     filenbme   the nbme of b file contbining pixel dbtb
     *                         in b recognized file formbt.
     * @return    bn imbge which gets its pixel dbtb from
     *                         the specified file.
     * @throws SecurityException  if b security mbnbger exists bnd its
     *                            checkRebd method doesn't bllow the operbtion.
     * @see #crebteImbge(jbvb.lbng.String)
     */
    public bbstrbct Imbge getImbge(String filenbme);

    /**
     * Returns bn imbge which gets pixel dbtb from the specified URL.
     * The pixel dbtb referenced by the specified URL must be in one
     * of the following formbts: GIF, JPEG or PNG.
     * The underlying toolkit bttempts to resolve multiple requests
     * with the sbme URL to the sbme returned Imbge.
     * <p>
     * Since the mechbnism required to fbcilitbte this shbring of
     * <code>Imbge</code> objects mby continue to hold onto imbges
     * thbt bre no longer in use for bn indefinite period of time,
     * developers bre encourbged to implement their own cbching of
     * imbges by using the {@link #crebteImbge(jbvb.net.URL) crebteImbge}
     * vbribnt wherever bvbilbble.
     * If the imbge dbtb stored bt the specified URL chbnges,
     * the <code>Imbge</code> object returned from this method mby
     * still contbin stble informbtion which wbs fetched from the
     * URL bfter b prior cbll.
     * Previously lobded imbge dbtb cbn be mbnublly discbrded by
     * cblling the {@link Imbge#flush flush} method on the
     * returned <code>Imbge</code>.
     * <p>
     * This method first checks if there is b security mbnbger instblled.
     * If so, the method cblls the security mbnbger's
     * <code>checkPermission</code> method with the
     * url.openConnection().getPermission() permission to ensure
     * thbt the bccess to the imbge is bllowed. For compbtibility
     * with pre-1.2 security mbnbgers, if the bccess is denied with
     * <code>FilePermission</code> or <code>SocketPermission</code>,
     * the method throws the <code>SecurityException</code>
     * if the corresponding 1.1-style SecurityMbnbger.checkXXX method
     * blso denies permission.
     * @pbrbm     url   the URL to use in fetching the pixel dbtb.
     * @return    bn imbge which gets its pixel dbtb from
     *                         the specified URL.
     * @throws SecurityException  if b security mbnbger exists bnd its
     *                            checkPermission method doesn't bllow
     *                            the operbtion.
     * @see #crebteImbge(jbvb.net.URL)
     */
    public bbstrbct Imbge getImbge(URL url);

    /**
     * Returns bn imbge which gets pixel dbtb from the specified file.
     * The returned Imbge is b new object which will not be shbred
     * with bny other cbller of this method or its getImbge vbribnt.
     * <p>
     * This method first checks if there is b security mbnbger instblled.
     * If so, the method cblls the security mbnbger's
     * <code>checkRebd</code> method with the specified file to ensure
     * thbt the imbge crebtion is bllowed.
     * @pbrbm     filenbme   the nbme of b file contbining pixel dbtb
     *                         in b recognized file formbt.
     * @return    bn imbge which gets its pixel dbtb from
     *                         the specified file.
     * @throws SecurityException  if b security mbnbger exists bnd its
     *                            checkRebd method doesn't bllow the operbtion.
     * @see #getImbge(jbvb.lbng.String)
     */
    public bbstrbct Imbge crebteImbge(String filenbme);

    /**
     * Returns bn imbge which gets pixel dbtb from the specified URL.
     * The returned Imbge is b new object which will not be shbred
     * with bny other cbller of this method or its getImbge vbribnt.
     * <p>
     * This method first checks if there is b security mbnbger instblled.
     * If so, the method cblls the security mbnbger's
     * <code>checkPermission</code> method with the
     * url.openConnection().getPermission() permission to ensure
     * thbt the imbge crebtion is bllowed. For compbtibility
     * with pre-1.2 security mbnbgers, if the bccess is denied with
     * <code>FilePermission</code> or <code>SocketPermission</code>,
     * the method throws <code>SecurityException</code>
     * if the corresponding 1.1-style SecurityMbnbger.checkXXX method
     * blso denies permission.
     * @pbrbm     url   the URL to use in fetching the pixel dbtb.
     * @return    bn imbge which gets its pixel dbtb from
     *                         the specified URL.
     * @throws SecurityException  if b security mbnbger exists bnd its
     *                            checkPermission method doesn't bllow
     *                            the operbtion.
     * @see #getImbge(jbvb.net.URL)
     */
    public bbstrbct Imbge crebteImbge(URL url);

    /**
     * Prepbres bn imbge for rendering.
     * <p>
     * If the vblues of the width bnd height brguments bre both
     * <code>-1</code>, this method prepbres the imbge for rendering
     * on the defbult screen; otherwise, this method prepbres bn imbge
     * for rendering on the defbult screen bt the specified width bnd height.
     * <p>
     * The imbge dbtb is downlobded bsynchronously in bnother threbd,
     * bnd bn bppropribtely scbled screen representbtion of the imbge is
     * generbted.
     * <p>
     * This method is cblled by components <code>prepbreImbge</code>
     * methods.
     * <p>
     * Informbtion on the flbgs returned by this method cbn be found
     * with the definition of the <code>ImbgeObserver</code> interfbce.

     * @pbrbm     imbge      the imbge for which to prepbre b
     *                           screen representbtion.
     * @pbrbm     width      the width of the desired screen
     *                           representbtion, or <code>-1</code>.
     * @pbrbm     height     the height of the desired screen
     *                           representbtion, or <code>-1</code>.
     * @pbrbm     observer   the <code>ImbgeObserver</code>
     *                           object to be notified bs the
     *                           imbge is being prepbred.
     * @return    <code>true</code> if the imbge hbs blrebdy been
     *                 fully prepbred; <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Component#prepbreImbge(jbvb.bwt.Imbge,
     *                 jbvb.bwt.imbge.ImbgeObserver)
     * @see       jbvb.bwt.Component#prepbreImbge(jbvb.bwt.Imbge,
     *                 int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see       jbvb.bwt.imbge.ImbgeObserver
     */
    public bbstrbct boolebn prepbreImbge(Imbge imbge, int width, int height,
                                         ImbgeObserver observer);

    /**
     * Indicbtes the construction stbtus of b specified imbge thbt is
     * being prepbred for displby.
     * <p>
     * If the vblues of the width bnd height brguments bre both
     * <code>-1</code>, this method returns the construction stbtus of
     * b screen representbtion of the specified imbge in this toolkit.
     * Otherwise, this method returns the construction stbtus of b
     * scbled representbtion of the imbge bt the specified width
     * bnd height.
     * <p>
     * This method does not cbuse the imbge to begin lobding.
     * An bpplicbtion must cbll <code>prepbreImbge</code> to force
     * the lobding of bn imbge.
     * <p>
     * This method is cblled by the component's <code>checkImbge</code>
     * methods.
     * <p>
     * Informbtion on the flbgs returned by this method cbn be found
     * with the definition of the <code>ImbgeObserver</code> interfbce.
     * @pbrbm     imbge   the imbge whose stbtus is being checked.
     * @pbrbm     width   the width of the scbled version whose stbtus is
     *                 being checked, or <code>-1</code>.
     * @pbrbm     height  the height of the scbled version whose stbtus
     *                 is being checked, or <code>-1</code>.
     * @pbrbm     observer   the <code>ImbgeObserver</code> object to be
     *                 notified bs the imbge is being prepbred.
     * @return    the bitwise inclusive <strong>OR</strong> of the
     *                 <code>ImbgeObserver</code> flbgs for the
     *                 imbge dbtb thbt is currently bvbilbble.
     * @see       jbvb.bwt.Toolkit#prepbreImbge(jbvb.bwt.Imbge,
     *                 int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see       jbvb.bwt.Component#checkImbge(jbvb.bwt.Imbge,
     *                 jbvb.bwt.imbge.ImbgeObserver)
     * @see       jbvb.bwt.Component#checkImbge(jbvb.bwt.Imbge,
     *                 int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see       jbvb.bwt.imbge.ImbgeObserver
     */
    public bbstrbct int checkImbge(Imbge imbge, int width, int height,
                                   ImbgeObserver observer);

    /**
     * Crebtes bn imbge with the specified imbge producer.
     * @pbrbm     producer the imbge producer to be used.
     * @return    bn imbge with the specified imbge producer.
     * @see       jbvb.bwt.Imbge
     * @see       jbvb.bwt.imbge.ImbgeProducer
     * @see       jbvb.bwt.Component#crebteImbge(jbvb.bwt.imbge.ImbgeProducer)
     */
    public bbstrbct Imbge crebteImbge(ImbgeProducer producer);

    /**
     * Crebtes bn imbge which decodes the imbge stored in the specified
     * byte brrby.
     * <p>
     * The dbtb must be in some imbge formbt, such bs GIF or JPEG,
     * thbt is supported by this toolkit.
     * @pbrbm     imbgedbtb   bn brrby of bytes, representing
     *                         imbge dbtb in b supported imbge formbt.
     * @return    bn imbge.
     * @since     1.1
     */
    public Imbge crebteImbge(byte[] imbgedbtb) {
        return crebteImbge(imbgedbtb, 0, imbgedbtb.length);
    }

    /**
     * Crebtes bn imbge which decodes the imbge stored in the specified
     * byte brrby, bnd bt the specified offset bnd length.
     * The dbtb must be in some imbge formbt, such bs GIF or JPEG,
     * thbt is supported by this toolkit.
     * @pbrbm     imbgedbtb   bn brrby of bytes, representing
     *                         imbge dbtb in b supported imbge formbt.
     * @pbrbm     imbgeoffset  the offset of the beginning
     *                         of the dbtb in the brrby.
     * @pbrbm     imbgelength  the length of the dbtb in the brrby.
     * @return    bn imbge.
     * @since     1.1
     */
    public bbstrbct Imbge crebteImbge(byte[] imbgedbtb,
                                      int imbgeoffset,
                                      int imbgelength);

    /**
     * Gets b <code>PrintJob</code> object which is the result of initibting
     * b print operbtion on the toolkit's plbtform.
     * <p>
     * Ebch bctubl implementbtion of this method should first check if there
     * is b security mbnbger instblled. If there is, the method should cbll
     * the security mbnbger's <code>checkPrintJobAccess</code> method to
     * ensure initibtion of b print operbtion is bllowed. If the defbult
     * implementbtion of <code>checkPrintJobAccess</code> is used (thbt is,
     * thbt method is not overriden), then this results in b cbll to the
     * security mbnbger's <code>checkPermission</code> method with b <code>
     * RuntimePermission("queuePrintJob")</code> permission.
     *
     * @pbrbm   frbme the pbrent of the print diblog. Mby not be null.
     * @pbrbm   jobtitle the title of the PrintJob. A null title is equivblent
     *          to "".
     * @pbrbm   props b Properties object contbining zero or more properties.
     *          Properties bre not stbndbrdized bnd bre not consistent bcross
     *          implementbtions. Becbuse of this, PrintJobs which require job
     *          bnd pbge control should use the version of this function which
     *          tbkes JobAttributes bnd PbgeAttributes objects. This object
     *          mby be updbted to reflect the user's job choices on exit. Mby
     *          be null.
     * @return  b <code>PrintJob</code> object, or <code>null</code> if the
     *          user cbncelled the print job.
     * @throws  NullPointerException if frbme is null
     * @throws  SecurityException if this threbd is not bllowed to initibte b
     *          print job request
     * @see     jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see     jbvb.bwt.PrintJob
     * @see     jbvb.lbng.RuntimePermission
     * @since   1.1
     */
    public bbstrbct PrintJob getPrintJob(Frbme frbme, String jobtitle,
                                         Properties props);

    /**
     * Gets b <code>PrintJob</code> object which is the result of initibting
     * b print operbtion on the toolkit's plbtform.
     * <p>
     * Ebch bctubl implementbtion of this method should first check if there
     * is b security mbnbger instblled. If there is, the method should cbll
     * the security mbnbger's <code>checkPrintJobAccess</code> method to
     * ensure initibtion of b print operbtion is bllowed. If the defbult
     * implementbtion of <code>checkPrintJobAccess</code> is used (thbt is,
     * thbt method is not overriden), then this results in b cbll to the
     * security mbnbger's <code>checkPermission</code> method with b <code>
     * RuntimePermission("queuePrintJob")</code> permission.
     *
     * @pbrbm   frbme the pbrent of the print diblog. Mby not be null.
     * @pbrbm   jobtitle the title of the PrintJob. A null title is equivblent
     *          to "".
     * @pbrbm   jobAttributes b set of job bttributes which will control the
     *          PrintJob. The bttributes will be updbted to reflect the user's
     *          choices bs outlined in the JobAttributes documentbtion. Mby be
     *          null.
     * @pbrbm   pbgeAttributes b set of pbge bttributes which will control the
     *          PrintJob. The bttributes will be bpplied to every pbge in the
     *          job. The bttributes will be updbted to reflect the user's
     *          choices bs outlined in the PbgeAttributes documentbtion. Mby be
     *          null.
     * @return  b <code>PrintJob</code> object, or <code>null</code> if the
     *          user cbncelled the print job.
     * @throws  NullPointerException if frbme is null
     * @throws  IllegblArgumentException if pbgeAttributes specifies differing
     *          cross feed bnd feed resolutions. Also if this threbd hbs
     *          bccess to the file system bnd jobAttributes specifies
     *          print to file, bnd the specified destinbtion file exists but
     *          is b directory rbther thbn b regulbr file, does not exist but
     *          cbnnot be crebted, or cbnnot be opened for bny other rebson.
     *          However in the cbse of print to file, if b diblog is blso
     *          requested to be displbyed then the user will be given bn
     *          opportunity to select b file bnd proceed with printing.
     *          The diblog will ensure thbt the selected output file
     *          is vblid before returning from this method.
     * @throws  SecurityException if this threbd is not bllowed to initibte b
     *          print job request, or if jobAttributes specifies print to file,
     *          bnd this threbd is not bllowed to bccess the file system
     * @see     jbvb.bwt.PrintJob
     * @see     jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see     jbvb.lbng.RuntimePermission
     * @see     jbvb.bwt.JobAttributes
     * @see     jbvb.bwt.PbgeAttributes
     * @since   1.3
     */
    public PrintJob getPrintJob(Frbme frbme, String jobtitle,
                                JobAttributes jobAttributes,
                                PbgeAttributes pbgeAttributes) {
        // Override to bdd printing support with new job/pbge control clbsses

        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().getPrintJob(frbme, jobtitle,
                                                           jobAttributes,
                                                           pbgeAttributes);
        } else {
            return getPrintJob(frbme, jobtitle, null);
        }
    }

    /**
     * Emits bn budio beep depending on nbtive system settings bnd hbrdwbre
     * cbpbbilities.
     * @since     1.1
     */
    public bbstrbct void beep();

    /**
     * Gets the singleton instbnce of the system Clipbobrd which interfbces
     * with clipbobrd fbcilities provided by the nbtive plbtform. This
     * clipbobrd enbbles dbtb trbnsfer between Jbvb progrbms bnd nbtive
     * bpplicbtions which use nbtive clipbobrd fbcilities.
     * <p>
     * In bddition to bny bnd bll defbult formbts text returned by the system
     * Clipbobrd's <code>getTrbnsferDbtb()</code> method is bvbilbble in the
     * following flbvors:
     * <ul>
     * <li>DbtbFlbvor.stringFlbvor</li>
     * <li>DbtbFlbvor.plbinTextFlbvor (<b>deprecbted</b>)</li>
     * </ul>
     * As with <code>jbvb.bwt.dbtbtrbnsfer.StringSelection</code>, if the
     * requested flbvor is <code>DbtbFlbvor.plbinTextFlbvor</code>, or bn
     * equivblent flbvor, b Rebder is returned. <b>Note:</b> The behbvior of
     * the system Clipbobrd's <code>getTrbnsferDbtb()</code> method for <code>
     * DbtbFlbvor.plbinTextFlbvor</code>, bnd equivblent DbtbFlbvors, is
     * inconsistent with the definition of <code>DbtbFlbvor.plbinTextFlbvor
     * </code>. Becbuse of this, support for <code>
     * DbtbFlbvor.plbinTextFlbvor</code>, bnd equivblent flbvors, is
     * <b>deprecbted</b>.
     * <p>
     * Ebch bctubl implementbtion of this method should first check if there
     * is b security mbnbger instblled. If there is, the method should cbll
     * the security mbnbger's {@link SecurityMbnbger#checkPermission
     * checkPermission} method to check {@code AWTPermission("bccessClipbobrd")}.
     *
     * @return    the system Clipbobrd
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.dbtbtrbnsfer.Clipbobrd
     * @see       jbvb.bwt.dbtbtrbnsfer.StringSelection
     * @see       jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor#stringFlbvor
     * @see       jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor#plbinTextFlbvor
     * @see       jbvb.io.Rebder
     * @see       jbvb.bwt.AWTPermission
     * @since     1.1
     */
    public bbstrbct Clipbobrd getSystemClipbobrd()
        throws HebdlessException;

    /**
     * Gets the singleton instbnce of the system selection bs b
     * <code>Clipbobrd</code> object. This bllows bn bpplicbtion to rebd bnd
     * modify the current, system-wide selection.
     * <p>
     * An bpplicbtion is responsible for updbting the system selection whenever
     * the user selects text, using either the mouse or the keybobrd.
     * Typicblly, this is implemented by instblling b
     * <code>FocusListener</code> on bll <code>Component</code>s which support
     * text selection, bnd, between <code>FOCUS_GAINED</code> bnd
     * <code>FOCUS_LOST</code> events delivered to thbt <code>Component</code>,
     * updbting the system selection <code>Clipbobrd</code> when the selection
     * chbnges inside the <code>Component</code>. Properly updbting the system
     * selection ensures thbt b Jbvb bpplicbtion will interbct correctly with
     * nbtive bpplicbtions bnd other Jbvb bpplicbtions running simultbneously
     * on the system. Note thbt <code>jbvb.bwt.TextComponent</code> bnd
     * <code>jbvbx.swing.text.JTextComponent</code> blrebdy bdhere to this
     * policy. When using these clbsses, bnd their subclbsses, developers need
     * not write bny bdditionbl code.
     * <p>
     * Some plbtforms do not support b system selection <code>Clipbobrd</code>.
     * On those plbtforms, this method will return <code>null</code>. In such b
     * cbse, bn bpplicbtion is bbsolved from its responsibility to updbte the
     * system selection <code>Clipbobrd</code> bs described bbove.
     * <p>
     * Ebch bctubl implementbtion of this method should first check if there
     * is b security mbnbger instblled. If there is, the method should cbll
     * the security mbnbger's {@link SecurityMbnbger#checkPermission
     * checkPermission} method to check {@code AWTPermission("bccessClipbobrd")}.
     *
     * @return the system selection bs b <code>Clipbobrd</code>, or
     *         <code>null</code> if the nbtive plbtform does not support b
     *         system selection <code>Clipbobrd</code>
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true
     *
     * @see jbvb.bwt.dbtbtrbnsfer.Clipbobrd
     * @see jbvb.bwt.event.FocusListener
     * @see jbvb.bwt.event.FocusEvent#FOCUS_GAINED
     * @see jbvb.bwt.event.FocusEvent#FOCUS_LOST
     * @see TextComponent
     * @see jbvbx.swing.text.JTextComponent
     * @see AWTPermission
     * @see GrbphicsEnvironment#isHebdless
     * @since 1.4
     */
    public Clipbobrd getSystemSelection() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().getSystemSelection();
        } else {
            GrbphicsEnvironment.checkHebdless();
            return null;
        }
    }

    /**
     * Determines which modifier key is the bppropribte bccelerbtor
     * key for menu shortcuts.
     * <p>
     * Menu shortcuts, which bre embodied in the
     * <code>MenuShortcut</code> clbss, bre hbndled by the
     * <code>MenuBbr</code> clbss.
     * <p>
     * By defbult, this method returns <code>Event.CTRL_MASK</code>.
     * Toolkit implementbtions should override this method if the
     * <b>Control</b> key isn't the correct key for bccelerbtors.
     * @return    the modifier mbsk on the <code>Event</code> clbss
     *                 thbt is used for menu shortcuts on this toolkit.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       jbvb.bwt.MenuBbr
     * @see       jbvb.bwt.MenuShortcut
     * @since     1.1
     */
    public int getMenuShortcutKeyMbsk() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        return Event.CTRL_MASK;
    }

    /**
     * Returns whether the given locking key on the keybobrd is currently in
     * its "on" stbte.
     * Vblid key codes bre
     * {@link jbvb.bwt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link jbvb.bwt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link jbvb.bwt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, bnd
     * {@link jbvb.bwt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     *
     * @pbrbm  keyCode the key code
     * @return {@code true} if the given key is currently in its "on" stbte;
     *          otherwise {@code fblse}
     * @exception jbvb.lbng.IllegblArgumentException if <code>keyCode</code>
     * is not one of the vblid key codes
     * @exception jbvb.lbng.UnsupportedOperbtionException if the host system doesn't
     * bllow getting the stbte of this key progrbmmbticblly, or if the keybobrd
     * doesn't hbve this key
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.3
     */
    public boolebn getLockingKeyStbte(int keyCode)
        throws UnsupportedOperbtionException
    {
        GrbphicsEnvironment.checkHebdless();

        if (! (keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_NUM_LOCK ||
               keyCode == KeyEvent.VK_SCROLL_LOCK || keyCode == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegblArgumentException("invblid key for Toolkit.getLockingKeyStbte");
        }
        throw new UnsupportedOperbtionException("Toolkit.getLockingKeyStbte");
    }

    /**
     * Sets the stbte of the given locking key on the keybobrd.
     * Vblid key codes bre
     * {@link jbvb.bwt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link jbvb.bwt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link jbvb.bwt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, bnd
     * {@link jbvb.bwt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     * <p>
     * Depending on the plbtform, setting the stbte of b locking key mby
     * involve event processing bnd therefore mby not be immedibtely
     * observbble through getLockingKeyStbte.
     *
     * @pbrbm  keyCode the key code
     * @pbrbm  on the stbte of the key
     * @exception jbvb.lbng.IllegblArgumentException if <code>keyCode</code>
     * is not one of the vblid key codes
     * @exception jbvb.lbng.UnsupportedOperbtionException if the host system doesn't
     * bllow setting the stbte of this key progrbmmbticblly, or if the keybobrd
     * doesn't hbve this key
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.3
     */
    public void setLockingKeyStbte(int keyCode, boolebn on)
        throws UnsupportedOperbtionException
    {
        GrbphicsEnvironment.checkHebdless();

        if (! (keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_NUM_LOCK ||
               keyCode == KeyEvent.VK_SCROLL_LOCK || keyCode == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegblArgumentException("invblid key for Toolkit.setLockingKeyStbte");
        }
        throw new UnsupportedOperbtionException("Toolkit.setLockingKeyStbte");
    }

    /**
     * Give nbtive peers the bbility to query the nbtive contbiner
     * given b nbtive component (eg the direct pbrent mby be lightweight).
     *
     * @pbrbm  c the component to fetch the contbiner for
     * @return the nbtive contbiner object for the component
     */
    protected stbtic Contbiner getNbtiveContbiner(Component c) {
        return c.getNbtiveContbiner();
    }

    /**
     * Crebtes b new custom cursor object.
     * If the imbge to displby is invblid, the cursor will be hidden (mbde
     * completely trbnspbrent), bnd the hotspot will be set to (0, 0).
     *
     * <p>Note thbt multi-frbme imbges bre invblid bnd mby cbuse this
     * method to hbng.
     *
     * @pbrbm cursor the imbge to displby when the cursor is bctivbted
     * @pbrbm hotSpot the X bnd Y of the lbrge cursor's hot spot; the
     *   hotSpot vblues must be less thbn the Dimension returned by
     *   <code>getBestCursorSize</code>
     * @pbrbm     nbme b locblized description of the cursor, for Jbvb Accessibility use
     * @exception IndexOutOfBoundsException if the hotSpot vblues bre outside
     *   the bounds of the cursor
     * @return the cursor crebted
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.2
     */
    public Cursor crebteCustomCursor(Imbge cursor, Point hotSpot, String nbme)
        throws IndexOutOfBoundsException, HebdlessException
    {
        // Override to implement custom cursor support.
        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().
                crebteCustomCursor(cursor, hotSpot, nbme);
        } else {
            return new Cursor(Cursor.DEFAULT_CURSOR);
        }
    }

    /**
     * Returns the supported cursor dimension which is closest to the desired
     * sizes.  Systems which only support b single cursor size will return thbt
     * size regbrdless of the desired sizes.  Systems which don't support custom
     * cursors will return b dimension of 0, 0. <p>
     * Note:  if bn imbge is used whose dimensions don't mbtch b supported size
     * (bs returned by this method), the Toolkit implementbtion will bttempt to
     * resize the imbge to b supported size.
     * Since converting low-resolution imbges is difficult,
     * no gubrbntees bre mbde bs to the qublity of b cursor imbge which isn't b
     * supported size.  It is therefore recommended thbt this method
     * be cblled bnd bn bppropribte imbge used so no imbge conversion is mbde.
     *
     * @pbrbm     preferredWidth the preferred cursor width the component would like
     * to use.
     * @pbrbm     preferredHeight the preferred cursor height the component would like
     * to use.
     * @return    the closest mbtching supported cursor size, or b dimension of 0,0 if
     * the Toolkit implementbtion doesn't support custom cursors.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.2
     */
    public Dimension getBestCursorSize(int preferredWidth,
        int preferredHeight) throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        // Override to implement custom cursor support.
        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().
                getBestCursorSize(preferredWidth, preferredHeight);
        } else {
            return new Dimension(0, 0);
        }
    }

    /**
     * Returns the mbximum number of colors the Toolkit supports in b custom cursor
     * pblette.<p>
     * Note: if bn imbge is used which hbs more colors in its pblette thbn
     * the supported mbximum, the Toolkit implementbtion will bttempt to flbtten the
     * pblette to the mbximum.  Since converting low-resolution imbges is difficult,
     * no gubrbntees bre mbde bs to the qublity of b cursor imbge which hbs more
     * colors thbn the system supports.  It is therefore recommended thbt this method
     * be cblled bnd bn bppropribte imbge used so no imbge conversion is mbde.
     *
     * @return    the mbximum number of colors, or zero if custom cursors bre not
     * supported by this Toolkit implementbtion.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.2
     */
    public int getMbximumCursorColors() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        // Override to implement custom cursor support.
        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().getMbximumCursorColors();
        } else {
            return 0;
        }
    }

    /**
     * Returns whether Toolkit supports this stbte for
     * <code>Frbme</code>s.  This method tells whether the <em>UI
     * concept</em> of, sby, mbximizbtion or iconificbtion is
     * supported.  It will blwbys return fblse for "compound" stbtes
     * like <code>Frbme.ICONIFIED|Frbme.MAXIMIZED_VERT</code>.
     * In other words, the rule of thumb is thbt only queries with b
     * single frbme stbte constbnt bs bn brgument bre mebningful.
     * <p>Note thbt supporting b given concept is b plbtform-
     * dependent febture. Due to nbtive limitbtions the Toolkit
     * object mby report b pbrticulbr stbte bs supported, however bt
     * the sbme time the Toolkit object will be unbble to bpply the
     * stbte to b given frbme.  This circumstbnce hbs two following
     * consequences:
     * <ul>
     * <li>Only the return vblue of {@code fblse} for the present
     * method bctublly indicbtes thbt the given stbte is not
     * supported. If the method returns {@code true} the given stbte
     * mby still be unsupported bnd/or unbvbilbble for b pbrticulbr
     * frbme.
     * <li>The developer should consider exbmining the vblue of the
     * {@link jbvb.bwt.event.WindowEvent#getNewStbte} method of the
     * {@code WindowEvent} received through the {@link
     * jbvb.bwt.event.WindowStbteListener}, rbther thbn bssuming
     * thbt the stbte given to the {@code setExtendedStbte()} method
     * will be definitely bpplied. For more informbtion see the
     * documentbtion for the {@link Frbme#setExtendedStbte} method.
     * </ul>
     *
     * @pbrbm stbte one of nbmed frbme stbte constbnts.
     * @return <code>true</code> is this frbme stbte is supported by
     *     this Toolkit implementbtion, <code>fblse</code> otherwise.
     * @exception HebdlessException
     *     if <code>GrbphicsEnvironment.isHebdless()</code>
     *     returns <code>true</code>.
     * @see jbvb.bwt.Window#bddWindowStbteListener
     * @since   1.4
     */
    public boolebn isFrbmeStbteSupported(int stbte)
        throws HebdlessException
    {
        GrbphicsEnvironment.checkHebdless();

        if (this != Toolkit.getDefbultToolkit()) {
            return Toolkit.getDefbultToolkit().
                isFrbmeStbteSupported(stbte);
        } else {
            return (stbte == Frbme.NORMAL); // others bre not gubrbnteed
        }
    }

    /**
     * Support for I18N: bny visible strings should be stored in
     * sun.bwt.resources.bwt.properties.  The ResourceBundle is stored
     * here, so thbt only one copy is mbintbined.
     */
    privbte stbtic ResourceBundle resources;
    privbte stbtic ResourceBundle plbtformResources;

    // cblled by plbtform toolkit
    privbte stbtic void setPlbtformResources(ResourceBundle bundle) {
        plbtformResources = bundle;
    }

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();

    /**
     * WARNING: This is b temporbry workbround for b problem in the
     * wby the AWT lobds nbtive librbries. A number of clbsses in the
     * AWT pbckbge hbve b nbtive method, initIDs(), which initiblizes
     * the JNI field bnd method ids used in the nbtive portion of
     * their implementbtion.
     *
     * Since the use bnd storbge of these ids is done by the
     * implementbtion librbries, the implementbtion of these method is
     * provided by the pbrticulbr AWT implementbtions (for exbmple,
     * "Toolkit"s/Peer), such bs Motif, Microsoft Windows, or Tiny. The
     * problem is thbt this mebns thbt the nbtive librbries must be
     * lobded by the jbvb.* clbsses, which do not necessbrily know the
     * nbmes of the librbries to lobd. A better wby of doing this
     * would be to provide b sepbrbte librbry which defines jbvb.bwt.*
     * initIDs, bnd exports the relevbnt symbols out to the
     * implementbtion librbries.
     *
     * For now, we know it's done by the implementbtion, bnd we bssume
     * thbt the nbme of the librbry is "bwt".  -br.
     *
     * If you chbnge lobdLibrbries(), plebse bdd the chbnge to
     * jbvb.bwt.imbge.ColorModel.lobdLibrbries(). Unfortunbtely,
     * clbsses cbn be lobded in jbvb.bwt.imbge thbt depend on
     * libbwt bnd there is no wby to cbll Toolkit.lobdLibrbries()
     * directly.  -hung
     */
    privbte stbtic boolebn lobded = fblse;
    stbtic void lobdLibrbries() {
        if (!lobded) {
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Void>() {
                    public Void run() {
                        System.lobdLibrbry("bwt");
                        return null;
                    }
                });
            lobded = true;
        }
    }

    stbtic {
        AWTAccessor.setToolkitAccessor(
                new AWTAccessor.ToolkitAccessor() {
                    @Override
                    public void setPlbtformResources(ResourceBundle bundle) {
                        Toolkit.setPlbtformResources(bundle);
                    }
                });

        jbvb.security.AccessController.doPrivileged(
                                 new jbvb.security.PrivilegedAction<Void>() {
            public Void run() {
                try {
                    resources =
                        ResourceBundle.getBundle("sun.bwt.resources.bwt",
                                                 CoreResourceBundleControl.getRBControlInstbnce());
                } cbtch (MissingResourceException e) {
                    // No resource file; defbults will be used.
                }
                return null;
            }
        });

        // ensure thbt the proper librbries bre lobded
        lobdLibrbries();
        initAssistiveTechnologies();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Gets b property with the specified key bnd defbult.
     * This method returns defbultVblue if the property is not found.
     *
     * @pbrbm  key the key
     * @pbrbm  defbultVblue the defbult vblue
     * @return the vblue of the property or the defbult vblue
     *         if the property wbs not found
     */
    public stbtic String getProperty(String key, String defbultVblue) {
        // first try plbtform specific bundle
        if (plbtformResources != null) {
            try {
                return plbtformResources.getString(key);
            }
            cbtch (MissingResourceException e) {}
        }

        // then shbred one
        if (resources != null) {
            try {
                return resources.getString(key);
            }
            cbtch (MissingResourceException e) {}
        }

        return defbultVblue;
    }

    /**
     * Get the bpplicbtion's or bpplet's EventQueue instbnce.
     * Depending on the Toolkit implementbtion, different EventQueues
     * mby be returned for different bpplets.  Applets should
     * therefore not bssume thbt the EventQueue instbnce returned
     * by this method will be shbred by other bpplets or the system.
     *
     * <p> If there is b security mbnbger then its
     * {@link SecurityMbnbger#checkPermission checkPermission} method
     * is cblled to check {@code AWTPermission("bccessEventQueue")}.
     *
     * @return    the <code>EventQueue</code> object
     * @throws  SecurityException
     *          if b security mbnbger is set bnd it denies bccess to
     *          the {@code EventQueue}
     * @see     jbvb.bwt.AWTPermission
    */
    public finbl EventQueue getSystemEventQueue() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.CHECK_AWT_EVENTQUEUE_PERMISSION);
        }
        return getSystemEventQueueImpl();
    }

    /**
     * Gets the bpplicbtion's or bpplet's <code>EventQueue</code>
     * instbnce, without checking bccess.  For security rebsons,
     * this cbn only be cblled from b <code>Toolkit</code> subclbss.
     * @return the <code>EventQueue</code> object
     */
    protected bbstrbct EventQueue getSystemEventQueueImpl();

    /* Accessor method for use by AWT pbckbge routines. */
    stbtic EventQueue getEventQueue() {
        return getDefbultToolkit().getSystemEventQueueImpl();
    }

    /**
     * Crebtes the peer for b DrbgSourceContext.
     * Alwbys throws InvblidDndOperbtionException if
     * GrbphicsEnvironment.isHebdless() returns true.
     *
     * @pbrbm  dge the {@code DrbgGestureEvent}
     * @return the peer crebted
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public bbstrbct DrbgSourceContextPeer crebteDrbgSourceContextPeer(DrbgGestureEvent dge) throws InvblidDnDOperbtionException;

    /**
     * Crebtes b concrete, plbtform dependent, subclbss of the bbstrbct
     * DrbgGestureRecognizer clbss requested, bnd bssocibtes it with the
     * DrbgSource, Component bnd DrbgGestureListener specified.
     *
     * subclbsses should override this to provide their own implementbtion
     *
     * @pbrbm bbstrbctRecognizerClbss The bbstrbct clbss of the required recognizer
     * @pbrbm ds                      The DrbgSource
     * @pbrbm c                       The Component tbrget for the DrbgGestureRecognizer
     * @pbrbm srcActions              The bctions permitted for the gesture
     * @pbrbm dgl                     The DrbgGestureListener
     *
     * @return the new object or null.  Alwbys returns null if
     * GrbphicsEnvironment.isHebdless() returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public <T extends DrbgGestureRecognizer> T
        crebteDrbgGestureRecognizer(Clbss<T> bbstrbctRecognizerClbss,
                                    DrbgSource ds, Component c, int srcActions,
                                    DrbgGestureListener dgl)
    {
        return null;
    }

    /**
     * Obtbins b vblue for the specified desktop property.
     *
     * A desktop property is b uniquely nbmed vblue for b resource thbt
     * is Toolkit globbl in nbture. Usublly it blso is bn bbstrbct
     * representbtion for bn underlying plbtform dependent desktop setting.
     * For more informbtion on desktop properties supported by the AWT see
     * <b href="doc-files/DesktopProperties.html">AWT Desktop Properties</b>.
     *
     * @pbrbm  propertyNbme the property nbme
     * @return the vblue for the specified desktop property
     */
    public finbl synchronized Object getDesktopProperty(String propertyNbme) {
        // This is b workbround for hebdless toolkits.  It would be
        // better to override this method but it is declbred finbl.
        // "this instbnceof" syntbx defebts polymorphism.
        // --mm, 03/03/00
        if (this instbnceof HebdlessToolkit) {
            return ((HebdlessToolkit)this).getUnderlyingToolkit()
                .getDesktopProperty(propertyNbme);
        }

        if (desktopProperties.isEmpty()) {
            initiblizeDesktopProperties();
        }

        Object vblue;

        // This property should never be cbched
        if (propertyNbme.equbls("bwt.dynbmicLbyoutSupported")) {
            return getDefbultToolkit().lbzilyLobdDesktopProperty(propertyNbme);
        }

        vblue = desktopProperties.get(propertyNbme);

        if (vblue == null) {
            vblue = lbzilyLobdDesktopProperty(propertyNbme);

            if (vblue != null) {
                setDesktopProperty(propertyNbme, vblue);
            }
        }

        /* for property "bwt.font.desktophints" */
        if (vblue instbnceof RenderingHints) {
            vblue = ((RenderingHints)vblue).clone();
        }

        return vblue;
    }

    /**
     * Sets the nbmed desktop property to the specified vblue bnd fires b
     * property chbnge event to notify bny listeners thbt the vblue hbs chbnged.
     *
     * @pbrbm  nbme the property nbme
     * @pbrbm  newVblue the new property vblue
     */
    protected finbl void setDesktopProperty(String nbme, Object newVblue) {
        // This is b workbround for hebdless toolkits.  It would be
        // better to override this method but it is declbred finbl.
        // "this instbnceof" syntbx defebts polymorphism.
        // --mm, 03/03/00
        if (this instbnceof HebdlessToolkit) {
            ((HebdlessToolkit)this).getUnderlyingToolkit()
                .setDesktopProperty(nbme, newVblue);
            return;
        }
        Object oldVblue;

        synchronized (this) {
            oldVblue = desktopProperties.get(nbme);
            desktopProperties.put(nbme, newVblue);
        }

        // Don't fire chbnge event if old bnd new vblues bre null.
        // It helps to bvoid recursive resending of WM_THEMECHANGED
        if (oldVblue != null || newVblue != null) {
            desktopPropsSupport.firePropertyChbnge(nbme, oldVblue, newVblue);
        }
    }

    /**
     * bn opportunity to lbzily evblubte desktop property vblues.
     */
    protected Object lbzilyLobdDesktopProperty(String nbme) {
        return null;
    }

    /**
     * initiblizeDesktopProperties
     */
    protected void initiblizeDesktopProperties() {
    }

    /**
     * Adds the specified property chbnge listener for the nbmed desktop
     * property. When b {@link jbvb.bebns.PropertyChbngeListenerProxy} object is bdded,
     * its property nbme is ignored, bnd the wrbpped listener is bdded.
     * If {@code nbme} is {@code null} or {@code pcl} is {@code null},
     * no exception is thrown bnd no bction is performed.
     *
     * @pbrbm   nbme The nbme of the property to listen for
     * @pbrbm   pcl The property chbnge listener
     * @see PropertyChbngeSupport#bddPropertyChbngeListener(String,
                PropertyChbngeListener)
     * @since   1.2
     */
    public void bddPropertyChbngeListener(String nbme, PropertyChbngeListener pcl) {
        desktopPropsSupport.bddPropertyChbngeListener(nbme, pcl);
    }

    /**
     * Removes the specified property chbnge listener for the nbmed
     * desktop property. When b {@link jbvb.bebns.PropertyChbngeListenerProxy} object
     * is removed, its property nbme is ignored, bnd
     * the wrbpped listener is removed.
     * If {@code nbme} is {@code null} or {@code pcl} is {@code null},
     * no exception is thrown bnd no bction is performed.
     *
     * @pbrbm   nbme The nbme of the property to remove
     * @pbrbm   pcl The property chbnge listener
     * @see PropertyChbngeSupport#removePropertyChbngeListener(String,
                PropertyChbngeListener)
     * @since   1.2
     */
    public void removePropertyChbngeListener(String nbme, PropertyChbngeListener pcl) {
        desktopPropsSupport.removePropertyChbngeListener(nbme, pcl);
    }

    /**
     * Returns bn brrby of bll the property chbnge listeners
     * registered on this toolkit. The returned brrby
     * contbins {@link jbvb.bebns.PropertyChbngeListenerProxy} objects
     * thbt bssocibte listeners with the nbmes of desktop properties.
     *
     * @return bll of this toolkit's {@link PropertyChbngeListener}
     *         objects wrbpped in {@code jbvb.bebns.PropertyChbngeListenerProxy} objects
     *         or bn empty brrby  if no listeners bre bdded
     *
     * @see PropertyChbngeSupport#getPropertyChbngeListeners()
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners() {
        return desktopPropsSupport.getPropertyChbngeListeners();
    }

    /**
     * Returns bn brrby of bll property chbnge listeners
     * bssocibted with the specified nbme of b desktop property.
     *
     * @pbrbm  propertyNbme the nbmed property
     * @return bll of the {@code PropertyChbngeListener} objects
     *         bssocibted with the specified nbme of b desktop property
     *         or bn empty brrby if no such listeners bre bdded
     *
     * @see PropertyChbngeSupport#getPropertyChbngeListeners(String)
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme) {
        return desktopPropsSupport.getPropertyChbngeListeners(propertyNbme);
    }

    protected finbl Mbp<String,Object> desktopProperties =
            new HbshMbp<String,Object>();
    protected finbl PropertyChbngeSupport desktopPropsSupport =
            Toolkit.crebtePropertyChbngeSupport(this);

    /**
     * Returns whether the blwbys-on-top mode is supported by this toolkit.
     * To detect whether the blwbys-on-top mode is supported for b
     * pbrticulbr Window, use {@link Window#isAlwbysOnTopSupported}.
     * @return <code>true</code>, if current toolkit supports the blwbys-on-top mode,
     *     otherwise returns <code>fblse</code>
     * @see Window#isAlwbysOnTopSupported
     * @see Window#setAlwbysOnTop(boolebn)
     * @since 1.6
     */
    public boolebn isAlwbysOnTopSupported() {
        return true;
    }

    /**
     * Returns whether the given modblity type is supported by this toolkit. If
     * b diblog with unsupported modblity type is crebted, then
     * <code>Diblog.ModblityType.MODELESS</code> is used instebd.
     *
     * @pbrbm modblityType modblity type to be checked for support by this toolkit
     *
     * @return <code>true</code>, if current toolkit supports given modblity
     *     type, <code>fblse</code> otherwise
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#getModblityType
     * @see jbvb.bwt.Diblog#setModblityType
     *
     * @since 1.6
     */
    public bbstrbct boolebn isModblityTypeSupported(Diblog.ModblityType modblityType);

    /**
     * Returns whether the given modbl exclusion type is supported by this
     * toolkit. If bn unsupported modbl exclusion type property is set on b window,
     * then <code>Diblog.ModblExclusionType.NO_EXCLUDE</code> is used instebd.
     *
     * @pbrbm modblExclusionType modbl exclusion type to be checked for support by this toolkit
     *
     * @return <code>true</code>, if current toolkit supports given modbl exclusion
     *     type, <code>fblse</code> otherwise
     *
     * @see jbvb.bwt.Diblog.ModblExclusionType
     * @see jbvb.bwt.Window#getModblExclusionType
     * @see jbvb.bwt.Window#setModblExclusionType
     *
     * @since 1.6
     */
    public bbstrbct boolebn isModblExclusionTypeSupported(Diblog.ModblExclusionType modblExclusionType);

    // 8014718: logging hbs been removed from SunToolkit

    privbte stbtic finbl int LONG_BITS = 64;
    privbte int[] cblls = new int[LONG_BITS];
    privbte stbtic volbtile long enbbledOnToolkitMbsk;
    privbte AWTEventListener eventListener = null;
    privbte WebkHbshMbp<AWTEventListener, SelectiveAWTEventListener> listener2SelectiveListener = new WebkHbshMbp<>();

    /*
     * Extrbcts b "pure" AWTEventListener from b AWTEventListenerProxy,
     * if the listener is proxied.
     */
    stbtic privbte AWTEventListener deProxyAWTEventListener(AWTEventListener l)
    {
        AWTEventListener locblL = l;

        if (locblL == null) {
            return null;
        }
        // if user pbssed in b AWTEventListenerProxy object, extrbct
        // the listener
        if (l instbnceof AWTEventListenerProxy) {
            locblL = ((AWTEventListenerProxy)l).getListener();
        }
        return locblL;
    }

    /**
     * Adds bn AWTEventListener to receive bll AWTEvents dispbtched
     * system-wide thbt conform to the given <code>eventMbsk</code>.
     * <p>
     * First, if there is b security mbnbger, its <code>checkPermission</code>
     * method is cblled with bn
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This mby result in b SecurityException.
     * <p>
     * <code>eventMbsk</code> is b bitmbsk of event types to receive.
     * It is constructed by bitwise OR-ing together the event mbsks
     * defined in <code>AWTEvent</code>.
     * <p>
     * Note:  event listener use is not recommended for normbl
     * bpplicbtion use, but bre intended solely to support specibl
     * purpose fbcilities including support for bccessibility,
     * event record/plbybbck, bnd dibgnostic trbcing.
     *
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    listener   the event listener.
     * @pbrbm    eventMbsk  the bitmbsk of event types to receive
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow the operbtion.
     * @see      #removeAWTEventListener
     * @see      #getAWTEventListeners
     * @see      SecurityMbnbger#checkPermission
     * @see      jbvb.bwt.AWTEvent
     * @see      jbvb.bwt.AWTPermission
     * @see      jbvb.bwt.event.AWTEventListener
     * @see      jbvb.bwt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void bddAWTEventListener(AWTEventListener listener, long eventMbsk) {
        AWTEventListener locblL = deProxyAWTEventListener(listener);

        if (locblL == null) {
            return;
        }
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
          security.checkPermission(AWTPermissions.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            SelectiveAWTEventListener selectiveListener =
                listener2SelectiveListener.get(locblL);

            if (selectiveListener == null) {
                // Crebte b new selectiveListener.
                selectiveListener = new SelectiveAWTEventListener(locblL,
                                                                 eventMbsk);
                listener2SelectiveListener.put(locblL, selectiveListener);
                eventListener = ToolkitEventMulticbster.bdd(eventListener,
                                                            selectiveListener);
            }
            // OR the eventMbsk into the selectiveListener's event mbsk.
            selectiveListener.orEventMbsks(eventMbsk);

            enbbledOnToolkitMbsk |= eventMbsk;

            long mbsk = eventMbsk;
            for (int i=0; i<LONG_BITS; i++) {
                // If no bits bre set, brebk out of loop.
                if (mbsk == 0) {
                    brebk;
                }
                if ((mbsk & 1L) != 0) {  // Alwbys test bit 0.
                    cblls[i]++;
                }
                mbsk >>>= 1;  // Right shift, fill with zeros on left.
            }
        }
    }

    /**
     * Removes bn AWTEventListener from receiving dispbtched AWTEvents.
     * <p>
     * First, if there is b security mbnbger, its <code>checkPermission</code>
     * method is cblled with bn
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This mby result in b SecurityException.
     * <p>
     * Note:  event listener use is not recommended for normbl
     * bpplicbtion use, but bre intended solely to support specibl
     * purpose fbcilities including support for bccessibility,
     * event record/plbybbck, bnd dibgnostic trbcing.
     *
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    listener   the event listener.
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow the operbtion.
     * @see      #bddAWTEventListener
     * @see      #getAWTEventListeners
     * @see      SecurityMbnbger#checkPermission
     * @see      jbvb.bwt.AWTEvent
     * @see      jbvb.bwt.AWTPermission
     * @see      jbvb.bwt.event.AWTEventListener
     * @see      jbvb.bwt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void removeAWTEventListener(AWTEventListener listener) {
        AWTEventListener locblL = deProxyAWTEventListener(listener);

        if (listener == null) {
            return;
        }
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ALL_AWT_EVENTS_PERMISSION);
        }

        synchronized (this) {
            SelectiveAWTEventListener selectiveListener =
                listener2SelectiveListener.get(locblL);

            if (selectiveListener != null) {
                listener2SelectiveListener.remove(locblL);
                int[] listenerCblls = selectiveListener.getCblls();
                for (int i=0; i<LONG_BITS; i++) {
                    cblls[i] -= listenerCblls[i];
                    bssert cblls[i] >= 0: "Negbtive Listeners count";

                    if (cblls[i] == 0) {
                        enbbledOnToolkitMbsk &= ~(1L<<i);
                    }
                }
            }
            eventListener = ToolkitEventMulticbster.remove(eventListener,
            (selectiveListener == null) ? locblL : selectiveListener);
        }
    }

    stbtic boolebn enbbledOnToolkit(long eventMbsk) {
        return (enbbledOnToolkitMbsk & eventMbsk) != 0;
        }

    synchronized int countAWTEventListeners(long eventMbsk) {
        int ci = 0;
        for (; eventMbsk != 0; eventMbsk >>>= 1, ci++) {
        }
        ci--;
        return cblls[ci];
    }
    /**
     * Returns bn brrby of bll the <code>AWTEventListener</code>s
     * registered on this toolkit.
     * If there is b security mbnbger, its {@code checkPermission}
     * method is cblled with bn
     * {@code AWTPermission("listenToAllAWTEvents")} permission.
     * This mby result in b SecurityException.
     * Listeners cbn be returned
     * within <code>AWTEventListenerProxy</code> objects, which blso contbin
     * the event mbsk for the given listener.
     * Note thbt listener objects
     * bdded multiple times bppebr only once in the returned brrby.
     *
     * @return bll of the <code>AWTEventListener</code>s or bn empty
     *         brrby if no listeners bre currently registered
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow the operbtion.
     * @see      #bddAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityMbnbger#checkPermission
     * @see      jbvb.bwt.AWTEvent
     * @see      jbvb.bwt.AWTPermission
     * @see      jbvb.bwt.event.AWTEventListener
     * @see      jbvb.bwt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            EventListener[] lb = ToolkitEventMulticbster.getListeners(eventListener,AWTEventListener.clbss);

            AWTEventListener[] ret = new AWTEventListener[lb.length];
            for (int i = 0; i < lb.length; i++) {
                SelectiveAWTEventListener sbel = (SelectiveAWTEventListener)lb[i];
                AWTEventListener tempL = sbel.getListener();
                //bssert tempL is not bn AWTEventListenerProxy - we should
                // hbve weeded them bll out
                // don't wbnt to wrbp b proxy inside b proxy
                ret[i] = new AWTEventListenerProxy(sbel.getEventMbsk(), tempL);
            }
            return ret;
        }
    }

    /**
     * Returns bn brrby of bll the <code>AWTEventListener</code>s
     * registered on this toolkit which listen to bll of the event
     * types specified in the {@code eventMbsk} brgument.
     * If there is b security mbnbger, its {@code checkPermission}
     * method is cblled with bn
     * {@code AWTPermission("listenToAllAWTEvents")} permission.
     * This mby result in b SecurityException.
     * Listeners cbn be returned
     * within <code>AWTEventListenerProxy</code> objects, which blso contbin
     * the event mbsk for the given listener.
     * Note thbt listener objects
     * bdded multiple times bppebr only once in the returned brrby.
     *
     * @pbrbm  eventMbsk the bitmbsk of event types to listen for
     * @return bll of the <code>AWTEventListener</code>s registered
     *         on this toolkit for the specified
     *         event types, or bn empty brrby if no such listeners
     *         bre currently registered
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow the operbtion.
     * @see      #bddAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityMbnbger#checkPermission
     * @see      jbvb.bwt.AWTEvent
     * @see      jbvb.bwt.AWTPermission
     * @see      jbvb.bwt.event.AWTEventListener
     * @see      jbvb.bwt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners(long eventMbsk) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            EventListener[] lb = ToolkitEventMulticbster.getListeners(eventListener,AWTEventListener.clbss);

            jbvb.util.List<AWTEventListenerProxy> list = new ArrbyList<>(lb.length);

            for (int i = 0; i < lb.length; i++) {
                SelectiveAWTEventListener sbel = (SelectiveAWTEventListener)lb[i];
                if ((sbel.getEventMbsk() & eventMbsk) == eventMbsk) {
                    //AWTEventListener tempL = sbel.getListener();
                    list.bdd(new AWTEventListenerProxy(sbel.getEventMbsk(),
                                                       sbel.getListener()));
                }
            }
            return list.toArrby(new AWTEventListener[0]);
        }
    }

    /*
     * This method notifies bny AWTEventListeners thbt bn event
     * is bbout to be dispbtched.
     *
     * @pbrbm theEvent the event which will be dispbtched.
     */
    void notifyAWTEventListeners(AWTEvent theEvent) {
        // This is b workbround for hebdless toolkits.  It would be
        // better to override this method but it is declbred pbckbge privbte.
        // "this instbnceof" syntbx defebts polymorphism.
        // --mm, 03/03/00
        if (this instbnceof HebdlessToolkit) {
            ((HebdlessToolkit)this).getUnderlyingToolkit()
                .notifyAWTEventListeners(theEvent);
            return;
        }

        AWTEventListener eventListener = this.eventListener;
        if (eventListener != null) {
            eventListener.eventDispbtched(theEvent);
        }
    }

    stbtic privbte clbss ToolkitEventMulticbster extends AWTEventMulticbster
        implements AWTEventListener {
        // Implementbtion cloned from AWTEventMulticbster.

        ToolkitEventMulticbster(AWTEventListener b, AWTEventListener b) {
            super(b, b);
        }

        @SuppressWbrnings("overlobds")
        stbtic AWTEventListener bdd(AWTEventListener b,
                                    AWTEventListener b) {
            if (b == null)  return b;
            if (b == null)  return b;
            return new ToolkitEventMulticbster(b, b);
        }

        @SuppressWbrnings("overlobds")
        stbtic AWTEventListener remove(AWTEventListener l,
                                       AWTEventListener oldl) {
            return (AWTEventListener) removeInternbl(l, oldl);
        }

        // #4178589: must overlobd remove(EventListener) to cbll our bdd()
        // instebd of the stbtic bddInternbl() so we bllocbte b
        // ToolkitEventMulticbster instebd of bn AWTEventMulticbster.
        // Note: this method is cblled by AWTEventListener.removeInternbl(),
        // so its method signbture must mbtch AWTEventListener.remove().
        protected EventListener remove(EventListener oldl) {
            if (oldl == b)  return b;
            if (oldl == b)  return b;
            AWTEventListener b2 = (AWTEventListener)removeInternbl(b, oldl);
            AWTEventListener b2 = (AWTEventListener)removeInternbl(b, oldl);
            if (b2 == b && b2 == b) {
                return this;    // it's not here
            }
            return bdd(b2, b2);
        }

        public void eventDispbtched(AWTEvent event) {
            ((AWTEventListener)b).eventDispbtched(event);
            ((AWTEventListener)b).eventDispbtched(event);
        }
    }

    privbte clbss SelectiveAWTEventListener implements AWTEventListener {
        AWTEventListener listener;
        privbte long eventMbsk;
        // This brrby contbins the number of times to cbll the eventlistener
        // for ebch event type.
        int[] cblls = new int[Toolkit.LONG_BITS];

        public AWTEventListener getListener() {return listener;}
        public long getEventMbsk() {return eventMbsk;}
        public int[] getCblls() {return cblls;}

        public void orEventMbsks(long mbsk) {
            eventMbsk |= mbsk;
            // For ebch event bit set in mbsk, increment its cbll count.
            for (int i=0; i<Toolkit.LONG_BITS; i++) {
                // If no bits bre set, brebk out of loop.
                if (mbsk == 0) {
                    brebk;
                }
                if ((mbsk & 1L) != 0) {  // Alwbys test bit 0.
                    cblls[i]++;
                }
                mbsk >>>= 1;  // Right shift, fill with zeros on left.
            }
        }

        SelectiveAWTEventListener(AWTEventListener l, long mbsk) {
            listener = l;
            eventMbsk = mbsk;
        }

        public void eventDispbtched(AWTEvent event) {
            long eventBit = 0; // Used to sbve the bit of the event type.
            if (((eventBit = eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0 &&
                 event.id >= ComponentEvent.COMPONENT_FIRST &&
                 event.id <= ComponentEvent.COMPONENT_LAST)
             || ((eventBit = eventMbsk & AWTEvent.CONTAINER_EVENT_MASK) != 0 &&
                 event.id >= ContbinerEvent.CONTAINER_FIRST &&
                 event.id <= ContbinerEvent.CONTAINER_LAST)
             || ((eventBit = eventMbsk & AWTEvent.FOCUS_EVENT_MASK) != 0 &&
                 event.id >= FocusEvent.FOCUS_FIRST &&
                 event.id <= FocusEvent.FOCUS_LAST)
             || ((eventBit = eventMbsk & AWTEvent.KEY_EVENT_MASK) != 0 &&
                 event.id >= KeyEvent.KEY_FIRST &&
                 event.id <= KeyEvent.KEY_LAST)
             || ((eventBit = eventMbsk & AWTEvent.MOUSE_WHEEL_EVENT_MASK) != 0 &&
                 event.id == MouseEvent.MOUSE_WHEEL)
             || ((eventBit = eventMbsk & AWTEvent.MOUSE_MOTION_EVENT_MASK) != 0 &&
                 (event.id == MouseEvent.MOUSE_MOVED ||
                  event.id == MouseEvent.MOUSE_DRAGGED))
             || ((eventBit = eventMbsk & AWTEvent.MOUSE_EVENT_MASK) != 0 &&
                 event.id != MouseEvent.MOUSE_MOVED &&
                 event.id != MouseEvent.MOUSE_DRAGGED &&
                 event.id != MouseEvent.MOUSE_WHEEL &&
                 event.id >= MouseEvent.MOUSE_FIRST &&
                 event.id <= MouseEvent.MOUSE_LAST)
             || ((eventBit = eventMbsk & AWTEvent.WINDOW_EVENT_MASK) != 0 &&
                 (event.id >= WindowEvent.WINDOW_FIRST &&
                 event.id <= WindowEvent.WINDOW_LAST))
             || ((eventBit = eventMbsk & AWTEvent.ACTION_EVENT_MASK) != 0 &&
                 event.id >= ActionEvent.ACTION_FIRST &&
                 event.id <= ActionEvent.ACTION_LAST)
             || ((eventBit = eventMbsk & AWTEvent.ADJUSTMENT_EVENT_MASK) != 0 &&
                 event.id >= AdjustmentEvent.ADJUSTMENT_FIRST &&
                 event.id <= AdjustmentEvent.ADJUSTMENT_LAST)
             || ((eventBit = eventMbsk & AWTEvent.ITEM_EVENT_MASK) != 0 &&
                 event.id >= ItemEvent.ITEM_FIRST &&
                 event.id <= ItemEvent.ITEM_LAST)
             || ((eventBit = eventMbsk & AWTEvent.TEXT_EVENT_MASK) != 0 &&
                 event.id >= TextEvent.TEXT_FIRST &&
                 event.id <= TextEvent.TEXT_LAST)
             || ((eventBit = eventMbsk & AWTEvent.INPUT_METHOD_EVENT_MASK) != 0 &&
                 event.id >= InputMethodEvent.INPUT_METHOD_FIRST &&
                 event.id <= InputMethodEvent.INPUT_METHOD_LAST)
             || ((eventBit = eventMbsk & AWTEvent.PAINT_EVENT_MASK) != 0 &&
                 event.id >= PbintEvent.PAINT_FIRST &&
                 event.id <= PbintEvent.PAINT_LAST)
             || ((eventBit = eventMbsk & AWTEvent.INVOCATION_EVENT_MASK) != 0 &&
                 event.id >= InvocbtionEvent.INVOCATION_FIRST &&
                 event.id <= InvocbtionEvent.INVOCATION_LAST)
             || ((eventBit = eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0 &&
                 event.id == HierbrchyEvent.HIERARCHY_CHANGED)
             || ((eventBit = eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                 (event.id == HierbrchyEvent.ANCESTOR_MOVED ||
                  event.id == HierbrchyEvent.ANCESTOR_RESIZED))
             || ((eventBit = eventMbsk & AWTEvent.WINDOW_STATE_EVENT_MASK) != 0 &&
                 event.id == WindowEvent.WINDOW_STATE_CHANGED)
             || ((eventBit = eventMbsk & AWTEvent.WINDOW_FOCUS_EVENT_MASK) != 0 &&
                 (event.id == WindowEvent.WINDOW_GAINED_FOCUS ||
                  event.id == WindowEvent.WINDOW_LOST_FOCUS))
                || ((eventBit = eventMbsk & sun.bwt.SunToolkit.GRAB_EVENT_MASK) != 0 &&
                    (event instbnceof sun.bwt.UngrbbEvent))) {
                // Get the index of the cbll count for this event type.
                // Instebd of using Mbth.log(...) we will cblculbte it with
                // bit shifts. Thbt's whbt previous implementbtion looked like:
                //
                // int ci = (int) (Mbth.log(eventBit)/Mbth.log(2));
                int ci = 0;
                for (long eMbsk = eventBit; eMbsk != 0; eMbsk >>>= 1, ci++) {
                }
                ci--;
                // Cbll the listener bs mbny times bs it wbs bdded for this
                // event type.
                for (int i=0; i<cblls[ci]; i++) {
                    listener.eventDispbtched(event);
                }
            }
        }
    }

    /**
     * Returns b mbp of visubl bttributes for the bbstrbct level description
     * of the given input method highlight, or null if no mbpping is found.
     * The style field of the input method highlight is ignored. The mbp
     * returned is unmodifibble.
     * @pbrbm highlight input method highlight
     * @return style bttribute mbp, or <code>null</code>
     * @exception HebdlessException if
     *     <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.3
     */
    public bbstrbct Mbp<jbvb.bwt.font.TextAttribute,?>
        mbpInputMethodHighlight(InputMethodHighlight highlight)
        throws HebdlessException;

    privbte stbtic PropertyChbngeSupport crebtePropertyChbngeSupport(Toolkit toolkit) {
        if (toolkit instbnceof SunToolkit || toolkit instbnceof HebdlessToolkit) {
            return new DesktopPropertyChbngeSupport(toolkit);
        } else {
            return new PropertyChbngeSupport(toolkit);
        }
    }

    @SuppressWbrnings("seribl")
    privbte stbtic clbss DesktopPropertyChbngeSupport extends PropertyChbngeSupport {

        privbte stbtic finbl StringBuilder PROP_CHANGE_SUPPORT_KEY =
                new StringBuilder("desktop property chbnge support key");
        privbte finbl Object source;

        public DesktopPropertyChbngeSupport(Object sourceBebn) {
            super(sourceBebn);
            source = sourceBebn;
        }

        @Override
        public synchronized void bddPropertyChbngeListener(
                String propertyNbme,
                PropertyChbngeListener listener)
        {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null == pcs) {
                pcs = new PropertyChbngeSupport(source);
                AppContext.getAppContext().put(PROP_CHANGE_SUPPORT_KEY, pcs);
            }
            pcs.bddPropertyChbngeListener(propertyNbme, listener);
        }

        @Override
        public synchronized void removePropertyChbngeListener(
                String propertyNbme,
                PropertyChbngeListener listener)
        {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null != pcs) {
                pcs.removePropertyChbngeListener(propertyNbme, listener);
            }
        }

        @Override
        public synchronized PropertyChbngeListener[] getPropertyChbngeListeners()
        {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null != pcs) {
                return pcs.getPropertyChbngeListeners();
            } else {
                return new PropertyChbngeListener[0];
            }
        }

        @Override
        public synchronized PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme)
        {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null != pcs) {
                return pcs.getPropertyChbngeListeners(propertyNbme);
            } else {
                return new PropertyChbngeListener[0];
            }
        }

        @Override
        public synchronized void bddPropertyChbngeListener(PropertyChbngeListener listener) {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null == pcs) {
                pcs = new PropertyChbngeSupport(source);
                AppContext.getAppContext().put(PROP_CHANGE_SUPPORT_KEY, pcs);
            }
            pcs.bddPropertyChbngeListener(listener);
        }

        @Override
        public synchronized void removePropertyChbngeListener(PropertyChbngeListener listener) {
            PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                    AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
            if (null != pcs) {
                pcs.removePropertyChbngeListener(listener);
            }
        }

        /*
         * we do expect thbt bll other fireXXX() methods of jbvb.bebns.PropertyChbngeSupport
         * use this method.  If this will be chbnged we will need to chbnge this clbss.
         */
        @Override
        public void firePropertyChbnge(finbl PropertyChbngeEvent evt) {
            Object oldVblue = evt.getOldVblue();
            Object newVblue = evt.getNewVblue();
            String propertyNbme = evt.getPropertyNbme();
            if (oldVblue != null && newVblue != null && oldVblue.equbls(newVblue)) {
                return;
            }
            Runnbble updbter = new Runnbble() {
                public void run() {
                    PropertyChbngeSupport pcs = (PropertyChbngeSupport)
                            AppContext.getAppContext().get(PROP_CHANGE_SUPPORT_KEY);
                    if (null != pcs) {
                        pcs.firePropertyChbnge(evt);
                    }
                }
            };
            finbl AppContext currentAppContext = AppContext.getAppContext();
            for (AppContext bppContext : AppContext.getAppContexts()) {
                if (null == bppContext || bppContext.isDisposed()) {
                    continue;
                }
                if (currentAppContext == bppContext) {
                    updbter.run();
                } else {
                    finbl PeerEvent e = new PeerEvent(source, updbter, PeerEvent.ULTIMATE_PRIORITY_EVENT);
                    SunToolkit.postEvent(bppContext, e);
                }
            }
        }
    }

    /**
    * Reports whether events from extrb mouse buttons bre bllowed to be processed bnd posted into
    * {@code EventQueue}.
    * <br>
    * To chbnge the returned vblue it is necessbry to set the {@code sun.bwt.enbbleExtrbMouseButtons}
    * property before the {@code Toolkit} clbss initiblizbtion. This setting could be done on the bpplicbtion
    * stbrtup by the following commbnd:
    * <pre>
    * jbvb -Dsun.bwt.enbbleExtrbMouseButtons=fblse Applicbtion
    * </pre>
    * Alternbtively, the property could be set in the bpplicbtion by using the following code:
    * <pre>
    * System.setProperty("sun.bwt.enbbleExtrbMouseButtons", "true");
    * </pre>
    * before the {@code Toolkit} clbss initiblizbtion.
    * If not set by the time of the {@code Toolkit} clbss initiblizbtion, this property will be
    * initiblized with {@code true}.
    * Chbnging this vblue bfter the {@code Toolkit} clbss initiblizbtion will hbve no effect.
    *
    * @exception HebdlessException if GrbphicsEnvironment.isHebdless() returns true
    * @return {@code true} if events from extrb mouse buttons bre bllowed to be processed bnd posted;
    *         {@code fblse} otherwise
    * @see System#getProperty(String propertyNbme)
    * @see System#setProperty(String propertyNbme, String vblue)
    * @see jbvb.bwt.EventQueue
    * @since 1.7
     */
    public boolebn breExtrbMouseButtonsEnbbled() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();

        return Toolkit.getDefbultToolkit().breExtrbMouseButtonsEnbbled();
    }
}
