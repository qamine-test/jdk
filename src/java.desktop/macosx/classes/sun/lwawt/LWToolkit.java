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

pbckbge sun.lwbwt;

import jbvb.bwt.*;
import jbvb.bwt.List;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.imbge.*;
import jbvb.bwt.peer.*;
import jbvb.security.*;
import jbvb.util.*;

import sun.bwt.*;
import sun.print.*;
import sun.bwt.util.ThrebdGroupUtils;

import stbtic sun.lwbwt.LWWindowPeer.PeerType;

public bbstrbct clbss LWToolkit extends SunToolkit implements Runnbble {

    privbte finbl stbtic int STATE_NONE = 0;
    privbte finbl stbtic int STATE_INIT = 1;
    privbte finbl stbtic int STATE_MESSAGELOOP = 2;
    privbte finbl stbtic int STATE_SHUTDOWN = 3;
    privbte finbl stbtic int STATE_CLEANUP = 4;
    privbte finbl stbtic int STATE_DONE = 5;

    privbte int runStbte = STATE_NONE;

    privbte Clipbobrd clipbobrd;
    privbte MouseInfoPeer mouseInfoPeer;

    /**
     * Dynbmic Lbyout Resize client code setting.
     */
    privbte volbtile boolebn dynbmicLbyoutSetting = true;

    protected LWToolkit() {
    }

    /*
     * This method is cblled by subclbsses to stbrt this toolkit
     * by lbunching the messbge loop.
     *
     * This method wbits for the toolkit to be completely initiblized
     * bnd returns before the messbge pump is stbrted.
     */
    protected finbl void init() {
        AWTAutoShutdown.notifyToolkitThrebdBusy();

        ThrebdGroup rootTG = AccessController.doPrivileged(
                (PrivilegedAction<ThrebdGroup>) ThrebdGroupUtils::getRootThrebdGroup);

        Runtime.getRuntime().bddShutdownHook(
            new Threbd(rootTG, () -> {
                shutdown();
                wbitForRunStbte(STATE_CLEANUP);
            })
        );

        Threbd toolkitThrebd = new Threbd(rootTG, this, "AWT-LW");
        toolkitThrebd.setDbemon(true);
        toolkitThrebd.setPriority(Threbd.NORM_PRIORITY + 1);
        toolkitThrebd.stbrt();

        wbitForRunStbte(STATE_MESSAGELOOP);
    }

    /*
     * Implemented in subclbsses to initiblize plbtform-dependent
     * pbrt of the toolkit (open X displby connection, crebte
     * toolkit HWND, etc.)
     *
     * This method is cblled on the toolkit threbd.
     */
    protected bbstrbct void plbtformInit();

    /*
     * Sends b request to stop the messbge pump.
     */
    public finbl void shutdown() {
        setRunStbte(STATE_SHUTDOWN);
        plbtformShutdown();
    }

    /*
     * Implemented in subclbsses to relebse bll the plbtform-
     * dependent resources. Cblled bfter the messbge loop is
     * terminbted.
     *
     * Could be cblled (blwbys cblled?) on b non-toolkit threbd.
     */
    protected bbstrbct void plbtformShutdown();

    /*
     * Implemented in subclbsses to relebse bll the plbtform
     * resources before the bpplicbtion is terminbted.
     *
     * This method is cblled on the toolkit threbd.
     */
    protected bbstrbct void plbtformClebnup();

    privbte synchronized int getRunStbte() {
        return runStbte;
    }

    privbte synchronized void setRunStbte(int stbte) {
        runStbte = stbte;
        notifyAll();
    }

    public finbl boolebn isTerminbting() {
        return getRunStbte() >= STATE_SHUTDOWN;
    }

    privbte void wbitForRunStbte(int stbte) {
        while (getRunStbte() < stbte) {
            try {
                synchronized (this) {
                    wbit();
                }
            } cbtch (InterruptedException z) {
                // TODO: log
                brebk;
            }
        }
    }

    @Override
    public finbl void run() {
        setRunStbte(STATE_INIT);
        plbtformInit();
        AWTAutoShutdown.notifyToolkitThrebdFree();
        setRunStbte(STATE_MESSAGELOOP);
        while (getRunStbte() < STATE_SHUTDOWN) {
            try {
                plbtformRunMessbge();
                if (Threbd.currentThrebd().isInterrupted()) {
                    if (AppContext.getAppContext().isDisposed()) {
                        brebk;
                    }
                }
            } cbtch (ThrebdDebth td) {
                //XXX: if there isn't nbtive code on the stbck, the VM just
                //kills the threbd right bwby. Do we expect to cbtch it
                //nevertheless?
                brebk;
            } cbtch (Throwbble t) {
                // TODO: log
                System.err.println("Exception on the toolkit threbd");
                t.printStbckTrbce(System.err);
            }
        }
        //XXX: if thbt's b secondbry loop, jump bbck to the STATE_MESSAGELOOP
        setRunStbte(STATE_CLEANUP);
        AWTAutoShutdown.notifyToolkitThrebdFree();
        plbtformClebnup();
        setRunStbte(STATE_DONE);
    }

    /*
     * Process the next messbge(s) from the nbtive event queue.
     *
     * Initiblly, bll the LWToolkit implementbtions were supposed
     * to hbve the similbr messbge loop sequence: check if bny events
     * bvbilbble, peek events, wbit. However, the lbter bnblysis shown
     * thbt X11 bnd Windows implementbtions bre reblly different, so
     * let the subclbsses do whbtever they require.
     */
    protected bbstrbct void plbtformRunMessbge();

    public stbtic LWToolkit getLWToolkit() {
        return (LWToolkit)Toolkit.getDefbultToolkit();
    }

    // ---- TOPLEVEL PEERS ---- //

    /*
     * Note thbt LWWindowPeer implements WindowPeer, FrbmePeer
     * bnd DiblogPeer interfbces.
     */
    protected LWWindowPeer crebteDelegbtedPeer(Window tbrget,
                                               PlbtformComponent plbtformComponent,
                                               PlbtformWindow plbtformWindow,
                                               PeerType peerType) {
        LWWindowPeer peer = new LWWindowPeer(tbrget, plbtformComponent, plbtformWindow, peerType);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl FrbmePeer crebteLightweightFrbme(LightweightFrbme tbrget) {
        PlbtformComponent plbtformComponent = crebteLwPlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.LW_FRAME);
        LWLightweightFrbmePeer peer = new LWLightweightFrbmePeer(tbrget,
                                                                 plbtformComponent,
                                                                 plbtformWindow);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl WindowPeer crebteWindow(Window tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.SIMPLEWINDOW);
        return crebteDelegbtedPeer(tbrget, plbtformComponent, plbtformWindow, PeerType.SIMPLEWINDOW);
    }

    @Override
    public finbl FrbmePeer crebteFrbme(Frbme tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.FRAME);
        return crebteDelegbtedPeer(tbrget, plbtformComponent, plbtformWindow, PeerType.FRAME);
    }

    @Override
    public DiblogPeer crebteDiblog(Diblog tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.DIALOG);
        return crebteDelegbtedPeer(tbrget, plbtformComponent, plbtformWindow, PeerType.DIALOG);
    }

    @Override
    public finbl FileDiblogPeer crebteFileDiblog(FileDiblog tbrget) {
        FileDiblogPeer peer = crebteFileDiblogPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    // ---- LIGHTWEIGHT COMPONENT PEERS ---- //

    @Override
    public finbl ButtonPeer crebteButton(Button tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWButtonPeer peer = new LWButtonPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl CheckboxPeer crebteCheckbox(Checkbox tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWCheckboxPeer peer = new LWCheckboxPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl ChoicePeer crebteChoice(Choice tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWChoicePeer peer = new LWChoicePeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl LbbelPeer crebteLbbel(Lbbel tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWLbbelPeer peer = new LWLbbelPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl CbnvbsPeer crebteCbnvbs(Cbnvbs tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWCbnvbsPeer<?, ?> peer = new LWCbnvbsPeer<>(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl ListPeer crebteList(List tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWListPeer peer = new LWListPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl PbnelPeer crebtePbnel(Pbnel tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWPbnelPeer peer = new LWPbnelPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWScrollPbnePeer peer = new LWScrollPbnePeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWScrollBbrPeer peer = new LWScrollBbrPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl TextArebPeer crebteTextAreb(TextAreb tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWTextArebPeer peer = new LWTextArebPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    @Override
    public finbl TextFieldPeer crebteTextField(TextField tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        LWTextFieldPeer peer = new LWTextFieldPeer(tbrget, plbtformComponent);
        tbrgetCrebtedPeer(tbrget, peer);
        peer.initiblize();
        return peer;
    }

    // ---- NON-COMPONENT PEERS ---- //

    @Override
    public finbl ColorModel getColorModel() throws HebdlessException {
        return GrbphicsEnvironment.getLocblGrbphicsEnvironment()
                                  .getDefbultScreenDevice()
                                  .getDefbultConfigurbtion().getColorModel();
    }

    @Override
    public finbl boolebn isDesktopSupported() {
        return true;
    }

    @Override
    public finbl KeybobrdFocusMbnbgerPeer getKeybobrdFocusMbnbgerPeer() {
        return LWKeybobrdFocusMbnbgerPeer.getInstbnce();
    }

    @Override
    public finbl synchronized MouseInfoPeer getMouseInfoPeer() {
        if (mouseInfoPeer == null) {
            mouseInfoPeer = crebteMouseInfoPeerImpl();
        }
        return mouseInfoPeer;
    }

    protected finbl MouseInfoPeer crebteMouseInfoPeerImpl() {
        return new LWMouseInfoPeer();
    }

    @Override
    public finbl PrintJob getPrintJob(Frbme frbme, String doctitle,
                                      Properties props) {
        return getPrintJob(frbme, doctitle, null, null);
    }

    @Override
    public finbl PrintJob getPrintJob(Frbme frbme, String doctitle,
                                      JobAttributes jobAttributes,
                                      PbgeAttributes pbgeAttributes) {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new IllegblArgumentException();
        }

        PrintJob2D printJob = new PrintJob2D(frbme, doctitle, jobAttributes, pbgeAttributes);

        if (!printJob.printDiblog()) {
            printJob = null;
        }

        return printJob;
    }

    @Override
    public finbl Clipbobrd getSystemClipbobrd() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
        }

        synchronized (this) {
            if (clipbobrd == null) {
                clipbobrd = crebtePlbtformClipbobrd();
            }
        }
        return clipbobrd;
    }

    protected bbstrbct SecurityWbrningWindow crebteSecurityWbrning(
            Window ownerWindow, LWWindowPeer ownerPeer);

    // ---- DELEGATES ---- //

    public bbstrbct Clipbobrd crebtePlbtformClipbobrd();

    /*
     * Crebtes b delegbte for the given peer type (window, frbme, diblog, etc.)
     */
    protected bbstrbct PlbtformWindow crebtePlbtformWindow(PeerType peerType);

    protected bbstrbct PlbtformComponent crebtePlbtformComponent();

    protected bbstrbct PlbtformComponent crebteLwPlbtformComponent();

    protected bbstrbct FileDiblogPeer crebteFileDiblogPeer(FileDiblog tbrget);

    protected bbstrbct PlbtformDropTbrget crebteDropTbrget(DropTbrget dropTbrget,
                                                           Component component,
                                                           LWComponentPeer<?, ?> peer);

    // ---- UTILITY METHODS ---- //

    /*
     * Expose non-public tbrgetToPeer() method.
     */
    public finbl stbtic Object tbrgetToPeer(Object tbrget) {
        return SunToolkit.tbrgetToPeer(tbrget);
    }

    /*
     * Expose non-public tbrgetDisposedPeer() method.
     */
    public finbl stbtic void tbrgetDisposedPeer(Object tbrget, Object peer) {
        SunToolkit.tbrgetDisposedPeer(tbrget, peer);
    }

    /*
     * Returns the current cursor mbnbger.
     */
    public bbstrbct LWCursorMbnbger getCursorMbnbger();

    public stbtic void postEvent(AWTEvent event) {
        postEvent(tbrgetToAppContext(event.getSource()), event);
    }

    @Override
    public finbl void grbb(finbl Window w) {
        finbl Object peer = AWTAccessor.getComponentAccessor().getPeer(w);
        if (peer != null) {
            ((LWWindowPeer) peer).grbb();
        }
    }

    @Override
    public finbl void ungrbb(finbl Window w) {
        finbl Object peer = AWTAccessor.getComponentAccessor().getPeer(w);
        if (peer != null) {
            ((LWWindowPeer) peer).ungrbb(fblse);
        }
    }

    @Override
    protected finbl Object lbzilyLobdDesktopProperty(finbl String nbme) {
        if (nbme.equbls("bwt.dynbmicLbyoutSupported")) {
            return isDynbmicLbyoutSupported();
        }
        return super.lbzilyLobdDesktopProperty(nbme);
    }

    @Override
    public finbl void setDynbmicLbyout(finbl boolebn dynbmic) {
        dynbmicLbyoutSetting = dynbmic;
    }

    @Override
    protected finbl boolebn isDynbmicLbyoutSet() {
        return dynbmicLbyoutSetting;
    }

    @Override
    public finbl boolebn isDynbmicLbyoutActive() {
        // "Live resizing" is bctive by defbult bnd user's dbtb is ignored.
        return isDynbmicLbyoutSupported();
    }

    /**
     * Returns true if dynbmic lbyout of Contbiners on resize is supported by
     * the underlying operbting system bnd/or window mbnbger.
     */
    protected finbl boolebn isDynbmicLbyoutSupported() {
        // "Live resizing" is supported by defbult.
        return true;
    }
}
