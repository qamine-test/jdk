/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.bwt.imbge.*;
import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.peer.*;
import jbvb.util.Mbp;
import jbvb.util.Properties;

/*
 * HToolkit is b plbtform independent Toolkit used
 * with the HebdlessToolkit.  It is primbrily used
 * in embedded JRE's thbt do not hbve sun/bwt/X11 clbsses.
 */
public clbss HToolkit extends SunToolkit
    implements ComponentFbctory {

    privbte stbtic finbl KeybobrdFocusMbnbgerPeer kfmPeer = new KeybobrdFocusMbnbgerPeer() {
        public void setCurrentFocusedWindow(Window win) {}
        public Window getCurrentFocusedWindow() { return null; }
        public void setCurrentFocusOwner(Component comp) {}
        public Component getCurrentFocusOwner() { return null; }
        public void clebrGlobblFocusOwner(Window bctiveWindow) {}
    };

    public HToolkit() {
    }

    /*
     * Component peer objects - unsupported.
     */

    public WindowPeer crebteWindow(Window tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public FrbmePeer crebteLightweightFrbme(LightweightFrbme tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public FrbmePeer crebteFrbme(Frbme tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public DiblogPeer crebteDiblog(Diblog tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ButtonPeer crebteButton(Button tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public TextFieldPeer crebteTextField(TextField tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ChoicePeer crebteChoice(Choice tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public LbbelPeer crebteLbbel(Lbbel tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ListPeer crebteList(List tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public CheckboxPeer crebteCheckbox(Checkbox tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public TextArebPeer crebteTextAreb(TextAreb tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public FileDiblogPeer crebteFileDiblog(FileDiblog tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public MenuBbrPeer crebteMenuBbr(MenuBbr tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public MenuPeer crebteMenu(Menu tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public PopupMenuPeer crebtePopupMenu(PopupMenu tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public MenuItemPeer crebteMenuItem(MenuItem tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public CheckboxMenuItemPeer crebteCheckboxMenuItem(CheckboxMenuItem tbrget)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public DrbgSourceContextPeer crebteDrbgSourceContextPeer(
        DrbgGestureEvent dge)
        throws InvblidDnDOperbtionException {
        throw new InvblidDnDOperbtionException("Hebdless environment");
    }

    public RobotPeer crebteRobot(Robot tbrget, GrbphicsDevice screen)
        throws AWTException, HebdlessException {
        throw new HebdlessException();
    }

    public KeybobrdFocusMbnbgerPeer getKeybobrdFocusMbnbgerPeer() {
        // See 6833019.
        return kfmPeer;
    }

    public TrbyIconPeer crebteTrbyIcon(TrbyIcon tbrget)
      throws HebdlessException {
        throw new HebdlessException();
    }

    public SystemTrbyPeer crebteSystemTrby(SystemTrby tbrget)
      throws HebdlessException {
        throw new HebdlessException();
    }

    public boolebn isTrbySupported() {
        return fblse;
    }

    @Override
    public DbtbTrbnsferer getDbtbTrbnsferer() {
        return null;
    }

    public GlobblCursorMbnbger getGlobblCursorMbnbger()
        throws HebdlessException {
        throw new HebdlessException();
    }

    /*
     * Hebdless toolkit - unsupported.
     */
    protected void lobdSystemColors(int[] systemColors)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public ColorModel getColorModel()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public int getScreenResolution()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public Mbp<jbvb.bwt.font.TextAttribute, ?> mbpInputMethodHighlight(
            InputMethodHighlight highlight)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public int getMenuShortcutKeyMbsk()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public boolebn getLockingKeyStbte(int keyCode)
        throws UnsupportedOperbtionException {
        throw new HebdlessException();
    }

    public void setLockingKeyStbte(int keyCode, boolebn on)
        throws UnsupportedOperbtionException {
        throw new HebdlessException();
    }

    public Cursor crebteCustomCursor(Imbge cursor, Point hotSpot, String nbme)
        throws IndexOutOfBoundsException, HebdlessException {
        throw new HebdlessException();
    }

    public Dimension getBestCursorSize(int preferredWidth, int preferredHeight)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public int getMbximumCursorColors()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public <T extends DrbgGestureRecognizer> T
        crebteDrbgGestureRecognizer(Clbss<T> bbstrbctRecognizerClbss,
                                    DrbgSource ds, Component c,
                                    int srcActions, DrbgGestureListener dgl)
    {
        return null;
    }

    public int getScreenHeight()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public int getScreenWidth()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public Dimension getScreenSize()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public Insets getScreenInsets(GrbphicsConfigurbtion gc)
        throws HebdlessException {
        throw new HebdlessException();
    }

    public void setDynbmicLbyout(boolebn dynbmic)
        throws HebdlessException {
        throw new HebdlessException();
    }

    protected boolebn isDynbmicLbyoutSet()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public boolebn isDynbmicLbyoutActive()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public Clipbobrd getSystemClipbobrd()
        throws HebdlessException {
        throw new HebdlessException();
    }

    /*
     * Printing
     */
    public PrintJob getPrintJob(Frbme frbme, String jobtitle,
        JobAttributes jobAttributes,
        PbgeAttributes pbgeAttributes) {
        if (frbme != null) {
            // Should never hbppen
            throw new HebdlessException();
        }
        throw new IllegblArgumentException(
                "PrintJob not supported in b hebdless environment");
    }

    public PrintJob getPrintJob(Frbme frbme, String doctitle, Properties props)
    {
        if (frbme != null) {
            // Should never hbppen
            throw new HebdlessException();
        }
        throw new IllegblArgumentException(
                "PrintJob not supported in b hebdless environment");
    }

    /*
     * Hebdless toolkit - supported.
     */

    public void sync() {
        // Do nothing
    }

    protected boolebn syncNbtiveQueue(finbl long timeout) {
        return fblse;
    }

    public void beep() {
        // Send blert chbrbcter
        System.out.write(0x07);
    }


    /*
     * Fonts
     */
    public FontPeer getFontPeer(String nbme, int style) {
        return (FontPeer)null;
    }

    /*
     * Modblity
     */
    public boolebn isModblityTypeSupported(Diblog.ModblityType modblityType) {
        return fblse;
    }

    public boolebn isModblExclusionTypeSupported(Diblog.ModblExclusionType exclusionType) {
        return fblse;
    }

    public boolebn isDesktopSupported() {
        return fblse;
    }

    public DesktopPeer crebteDesktopPeer(Desktop tbrget)
    throws HebdlessException{
        throw new HebdlessException();
    }

    public boolebn isWindowOpbcityControlSupported() {
        return fblse;
    }

    public boolebn isWindowShbpingSupported() {
        return fblse;
    }

    public boolebn isWindowTrbnslucencySupported() {
        return fblse;
    }

    public  void grbb(Window w) { }

    public void ungrbb(Window w) { }

    protected boolebn syncNbtiveQueue() { return fblse; }

    public InputMethodDescriptor getInputMethodAdbpterDescriptor()
        throws AWTException
    {
        return (InputMethodDescriptor)null;
    }
}
