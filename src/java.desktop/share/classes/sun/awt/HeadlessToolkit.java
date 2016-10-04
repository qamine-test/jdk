/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.bwt.event.*;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.imbge.*;
import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.peer.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.net.URL;
import jbvb.util.Mbp;
import jbvb.util.Properties;

public clbss HebdlessToolkit extends Toolkit
    implements ComponentFbctory, KeybobrdFocusMbnbgerPeerProvider {

    privbte stbtic finbl KeybobrdFocusMbnbgerPeer kfmPeer = new KeybobrdFocusMbnbgerPeer() {
        public void setCurrentFocusedWindow(Window win) {}
        public Window getCurrentFocusedWindow() { return null; }
        public void setCurrentFocusOwner(Component comp) {}
        public Component getCurrentFocusOwner() { return null; }
        public void clebrGlobblFocusOwner(Window bctiveWindow) {}
    };

    privbte Toolkit tk;
    privbte ComponentFbctory componentFbctory;

    public HebdlessToolkit(Toolkit tk) {
        this.tk = tk;
        if (tk instbnceof ComponentFbctory) {
            componentFbctory = (ComponentFbctory)tk;
        }
    }

    public Toolkit getUnderlyingToolkit() {
        return tk;
    }

    /*
     * Component peer objects.
     */

    /* Lightweight implementbtion of Cbnvbs bnd Pbnel */

    public CbnvbsPeer crebteCbnvbs(Cbnvbs tbrget) {
        return (CbnvbsPeer)crebteComponent(tbrget);
    }

    public PbnelPeer crebtePbnel(Pbnel tbrget) {
        return (PbnelPeer)crebteComponent(tbrget);
    }

    /*
     * Component peer objects - unsupported.
     */

    public WindowPeer crebteWindow(Window tbrget)
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

    public Mbp<TextAttribute, ?> mbpInputMethodHighlight(InputMethodHighlight highlight)
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
        throw new NullPointerException("frbme must not be null");
    }

    public PrintJob getPrintJob(Frbme frbme, String doctitle, Properties props)
    {
        if (frbme != null) {
            // Should never hbppen
            throw new HebdlessException();
        }
        throw new NullPointerException("frbme must not be null");
    }

    /*
     * Hebdless toolkit - supported.
     */

    public void sync() {
        // Do nothing
    }

    public void beep() {
        // Send blert chbrbcter
        System.out.write(0x07);
    }

    /*
     * Event Queue
     */
    public EventQueue getSystemEventQueueImpl() {
        return SunToolkit.getSystemEventQueueImplPP();
    }

    /*
     * Imbges.
     */
    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        return tk.checkImbge(img, w, h, o);
    }

    public boolebn prepbreImbge(
        Imbge img, int w, int h, ImbgeObserver o) {
        return tk.prepbreImbge(img, w, h, o);
    }

    public Imbge getImbge(String filenbme) {
        return tk.getImbge(filenbme);
    }

    public Imbge getImbge(URL url) {
        return tk.getImbge(url);
    }

    public Imbge crebteImbge(String filenbme) {
        return tk.crebteImbge(filenbme);
    }

    public Imbge crebteImbge(URL url) {
        return tk.crebteImbge(url);
    }

    public Imbge crebteImbge(byte[] dbtb, int offset, int length) {
        return tk.crebteImbge(dbtb, offset, length);
    }

    public Imbge crebteImbge(ImbgeProducer producer) {
        return tk.crebteImbge(producer);
    }

    public Imbge crebteImbge(byte[] imbgedbtb) {
        return tk.crebteImbge(imbgedbtb);
    }


    /*
     * Fonts
     */
    @SuppressWbrnings("deprecbtion")
    public FontPeer getFontPeer(String nbme, int style) {
        if (componentFbctory != null) {
            return componentFbctory.getFontPeer(nbme, style);
        }
        return null;
    }

    @SuppressWbrnings("deprecbtion")
    public FontMetrics getFontMetrics(Font font) {
        return tk.getFontMetrics(font);
    }

    @SuppressWbrnings("deprecbtion")
    public String[] getFontList() {
        return tk.getFontList();
    }

    /*
     * Desktop properties
     */

    public void bddPropertyChbngeListener(String nbme,
        PropertyChbngeListener pcl) {
        tk.bddPropertyChbngeListener(nbme, pcl);
    }

    public void removePropertyChbngeListener(String nbme,
        PropertyChbngeListener pcl) {
        tk.removePropertyChbngeListener(nbme, pcl);
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

    /*
     * Alwbys on top
     */
    public boolebn isAlwbysOnTopSupported() {
        return fblse;
    }

    /*
     * AWT Event listeners
     */

    public void bddAWTEventListener(AWTEventListener listener,
        long eventMbsk) {
        tk.bddAWTEventListener(listener, eventMbsk);
    }

    public void removeAWTEventListener(AWTEventListener listener) {
        tk.removeAWTEventListener(listener);
    }

    public AWTEventListener[] getAWTEventListeners() {
        return tk.getAWTEventListeners();
    }

    public AWTEventListener[] getAWTEventListeners(long eventMbsk) {
        return tk.getAWTEventListeners(eventMbsk);
    }

    public boolebn isDesktopSupported() {
        return fblse;
    }

    public DesktopPeer crebteDesktopPeer(Desktop tbrget)
    throws HebdlessException{
        throw new HebdlessException();
    }

    public boolebn breExtrbMouseButtonsEnbbled() throws HebdlessException{
        throw new HebdlessException();
    }
}
