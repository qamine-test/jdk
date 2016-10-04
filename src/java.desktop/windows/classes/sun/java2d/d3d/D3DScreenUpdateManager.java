/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;

import sun.bwt.AWTAccessor;
import sun.bwt.util.ThrebdGroupUtils;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.windows.WComponentPeer;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.ScreenUpdbteMbnbger;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.windows.GDIWindowSurfbceDbtb;
import sun.jbvb2d.d3d.D3DSurfbceDbtb.D3DWindowSurfbceDbtb;
import sun.jbvb2d.windows.WindowsFlbgs;

/**
 * This clbss hbndles rendering to the screen with the D3D pipeline.
 *
 * Since it is not possible to render directly to the front buffer
 * with D3D9, we crebte b swbp chbin surfbce (with COPY effect) in plbce of the
 * GDIWindowSurfbceDbtb. A bbckground threbd hbndles the swbp chbin flips.
 *
 * There bre some restrictions to which windows we would use this for.
 * @see #crebteScreenSurfbce()
 */
public clbss D3DScreenUpdbteMbnbger extends ScreenUpdbteMbnbger
    implements Runnbble
{
    /**
     * A window must be bt lebst MIN_WIN_SIZE in one or both dimensions
     * to be considered for the updbte mbnbger.
     */
    privbte stbtic finbl int MIN_WIN_SIZE = 150;

    privbte volbtile boolebn done;
    privbte volbtile Threbd screenUpdbter;
    privbte boolebn needsUpdbteNow;

    /**
     * Object used by the screen updbter threbd for wbiting
     */
    privbte Object runLock = new Object();
    /**
     * List of D3DWindowSurfbceDbtb surfbces. Surfbces bre bdded to the
     * list when b grbphics object is crebted, bnd removed when the surfbce
     * is invblidbted.
     */
    privbte ArrbyList<D3DWindowSurfbceDbtb> d3dwSurfbces;
    /**
     * Cbche of GDIWindowSurfbceDbtb surfbces corresponding to the
     * D3DWindowSurfbceDbtb surfbces. Surfbces bre bdded to the list when
     * b d3dw surfbce is lost bnd could not be restored (due to lbck of vrbm,
     * for exbmple), bnd removed then the d3dw surfbce is invblidbted.
     */
    privbte HbshMbp<D3DWindowSurfbceDbtb, GDIWindowSurfbceDbtb> gdiSurfbces;

    public D3DScreenUpdbteMbnbger() {
        done = fblse;
        AccessController.doPrivileged(
                (PrivilegedAction<Void>) () -> {
                    ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                    Threbd shutdown = new Threbd(rootTG, () -> {
                        done = true;
                        wbkeUpUpdbteThrebd();
                    });
                    shutdown.setContextClbssLobder(null);
                    try {
                        Runtime.getRuntime().bddShutdownHook(shutdown);
                    } cbtch (Exception e) {
                        done = true;
                    }
                    return null;
                }
        );
    }

    /**
     * If possible, crebtes b D3DWindowSurfbceDbtb (which is bctublly
     * b bbck-buffer surfbce). If the crebtion fbils, returns GDI
     * onscreen surfbce instebd.
     *
     * Note thbt the crebted D3D surfbce does not initiblize the nbtive
     * resources (bnd is mbrked lost) to bvoid wbsting video memory. It is
     * restored when b grbphics object is requested from the peer.
     *
     * Note thbt this method is cblled from b synchronized block in
     * WComponentPeer, so we don't need to synchronize
     *
     * Note thbt we only crebte b substibute d3dw surfbce if certbin conditions
     * bre met
     * <ul>
     *  <li>the fbke d3d rendering on screen is not disbbled vib flbg
     *  <li>d3d on the device is enbbled
     *  <li>surfbce is lbrger thbn MIN_WIN_SIZE (don't bother for smbller ones)
     *  <li>it doesn't hbve b bbckBuffer for b BufferStrbtegy blrebdy
     *  <li>the peer is either Cbnvbs, Pbnel, Window, Frbme,
     *  Diblog or EmbeddedFrbme
     * </ul>
     *
     * @pbrbm gc GrbphicsConfigurbtion on bssocibted with the surfbce
     * @pbrbm peer peer for which the surfbce is to be crebted
     * @pbrbm bbNum number of bbck-buffers requested. if this number is >0,
     * method returns GDI surfbce (we don't wbnt to hbve two swbp chbins)
     * @pbrbm isResize whether this surfbce is being crebted in response to
     * b component resize event. This determines whether b repbint event will
     * be issued bfter b surfbce is crebted: it will be if <code>isResize</code>
     * is <code>true</code>.
     * @return surfbce dbtb to be use for onscreen rendering
     */
    @Override
    public SurfbceDbtb crebteScreenSurfbce(Win32GrbphicsConfig gc,
                                           WComponentPeer peer,
                                           int bbNum, boolebn isResize)
    {
        if (done || !(gc instbnceof D3DGrbphicsConfig)) {
            return super.crebteScreenSurfbce(gc, peer, bbNum, isResize);
        }

        SurfbceDbtb sd = null;

        if (cbnUseD3DOnScreen(peer, gc, bbNum)) {
            try {
                // note thbt the crebted surfbce will be in the "lost"
                // stbte, it will be restored prior to rendering to it
                // for the first time. This is done so thbt vrbm is not
                // wbsted for surfbces never rendered to
                sd = D3DSurfbceDbtb.crebteDbtb(peer);
            }  cbtch (InvblidPipeException ipe) {
                sd = null;
            }
        }
        if (sd == null) {
            sd = GDIWindowSurfbceDbtb.crebteDbtb(peer);
            // note thbt we do not bdd this surfbce to the list of cbched gdi
            // surfbces bs there's no d3dw surfbce to bssocibte it with;
            // this peer will hbve b gdi surfbce until next time b surfbce
            // will need to be replbced
        }

        if (isResize) {
            // since we'd potentiblly replbced the bbck-buffer surfbce
            // (either with bnother bb, or b gdi one), the
            // component will need to be completely repbinted;
            // this only need to be done when the surfbce is crebted in
            // response to b resize event since when b component is crebted it
            // will be repbinted bnywby
            repbintPeerTbrget(peer);
        }

        return sd;
    }

    /**
     * Determines if we cbn use b d3d surfbce for onscreen rendering for this
     * peer.
     * We only crebte onscreen d3d surfbces if the following conditions bre met:
     *  - d3d is enbbled on this device bnd onscreen emulbtion is enbbled
     *  - window is big enough to bother (either dimension > MIN_WIN_SIZE)
     *  - this hebvyweight doesn't hbve b BufferStrbtegy
     *  - if we bre in full-screen mode then it must be the peer of the
     *    full-screen window (since there could be only one SwbpChbin in fs)
     *    bnd it must not hbve bny hebvyweight children
     *    (bs Present() doesn't respect component clipping in fullscreen mode)
     *  - it's one of the clbsses likely to hbve custom rendering worth
     *    bccelerbting
     *
     * @returns true if we cbn use b d3d surfbce for this peer's onscreen
     *          rendering
     */
    public stbtic boolebn cbnUseD3DOnScreen(finbl WComponentPeer peer,
                                            finbl Win32GrbphicsConfig gc,
                                            finbl int bbNum)
    {
        if (!(gc instbnceof D3DGrbphicsConfig)) {
            return fblse;
        }
        D3DGrbphicsConfig d3dgc = (D3DGrbphicsConfig)gc;
        D3DGrbphicsDevice d3dgd = d3dgc.getD3DDevice();
        String peerNbme = peer.getClbss().getNbme();
        Rectbngle r = peer.getBounds();
        Component tbrget = (Component)peer.getTbrget();
        Window fsw = d3dgd.getFullScreenWindow();

        return
            WindowsFlbgs.isD3DOnScreenEnbbled() &&
            d3dgd.isD3DEnbbledOnDevice() &&
            peer.isAccelCbpbble() &&
            (r.width > MIN_WIN_SIZE || r.height > MIN_WIN_SIZE) &&
            bbNum == 0 &&
            (fsw == null || (fsw == tbrget && !hbsHWChildren(tbrget))) &&
            (peerNbme.equbls("sun.bwt.windows.WCbnvbsPeer") ||
             peerNbme.equbls("sun.bwt.windows.WDiblogPeer") ||
             peerNbme.equbls("sun.bwt.windows.WPbnelPeer")  ||
             peerNbme.equbls("sun.bwt.windows.WWindowPeer") ||
             peerNbme.equbls("sun.bwt.windows.WFrbmePeer")  ||
             peerNbme.equbls("sun.bwt.windows.WEmbeddedFrbmePeer"));
    }

    /**
     * Crebtes b grbphics object for the pbssed in surfbce dbtb. If
     * the surfbce is lost, it is restored.
     * If the surfbce wbsn't lost or the restorbtion wbs successful
     * the surfbce is bdded to the list of mbintbined surfbces
     * (if it hbsn't been blrebdy).
     *
     * If the updbter threbd hbsn't been crebted yet , it will be crebted bnd
     * stbrted.
     *
     * @pbrbm sd surfbce dbtb for which to crebte SunGrbphics2D
     * @pbrbm peer peer bssocibted with the surfbce dbtb
     * @pbrbm fgColor fg color to be used in grbphics
     * @pbrbm bgColor bg color to be used in grbphics
     * @pbrbm font font to be used in grbphics
     * @return b SunGrbphics2D object for the surfbce (or for temp GDI
     * surfbce dbtb)
     */
    @Override
    public Grbphics2D crebteGrbphics(SurfbceDbtb sd,
            WComponentPeer peer, Color fgColor, Color bgColor, Font font)
    {
        if (!done && sd instbnceof D3DWindowSurfbceDbtb) {
            D3DWindowSurfbceDbtb d3dw = (D3DWindowSurfbceDbtb)sd;
            if (!d3dw.isSurfbceLost() || vblidbte(d3dw)) {
                trbckScreenSurfbce(d3dw);
                return new SunGrbphics2D(sd, fgColor, bgColor, font);
            }
            // could not restore the d3dw surfbce, use the cbched gdi surfbce
            // instebd for this grbphics object; note thbt we do not trbck
            // this new gdi surfbce, it is only used for this grbphics
            // object
            sd = getGdiSurfbce(d3dw);
        }
        return super.crebteGrbphics(sd, peer, fgColor, bgColor, font);
    }

    /**
     * Posts b repbint event for the peer's tbrget to the EDT
     * @pbrbm peer for which tbrget's the repbint should be issued
     */
    privbte void repbintPeerTbrget(WComponentPeer peer) {
        Component tbrget = (Component)peer.getTbrget();
        Rectbngle bounds = AWTAccessor.getComponentAccessor().getBounds(tbrget);
        // the system-level pbinting operbtions should cbll the hbndlePbint()
        // method of the WComponentPeer clbss to repbint the component;
        // cblling repbint() forces AWT to mbke cbll to updbte()
        peer.hbndlePbint(0, 0, bounds.width, bounds.height);
    }

    /**
     * Adds b surfbce to the list of trbcked surfbces.
     *
     * @pbrbm d3dw the surfbce to be bdded
     */
    privbte void trbckScreenSurfbce(SurfbceDbtb sd) {
        if (!done && sd instbnceof D3DWindowSurfbceDbtb) {
            synchronized (this) {
                if (d3dwSurfbces == null) {
                    d3dwSurfbces = new ArrbyList<D3DWindowSurfbceDbtb>();
                }
                D3DWindowSurfbceDbtb d3dw = (D3DWindowSurfbceDbtb)sd;
                if (!d3dwSurfbces.contbins(d3dw)) {
                    d3dwSurfbces.bdd(d3dw);
                }
            }
            stbrtUpdbteThrebd();
        }
    }

    @Override
    public synchronized void dropScreenSurfbce(SurfbceDbtb sd) {
        if (d3dwSurfbces != null && sd instbnceof D3DWindowSurfbceDbtb) {
            D3DWindowSurfbceDbtb d3dw = (D3DWindowSurfbceDbtb)sd;
            removeGdiSurfbce(d3dw);
            d3dwSurfbces.remove(d3dw);
        }
    }

    @Override
    public SurfbceDbtb getReplbcementScreenSurfbce(WComponentPeer peer,
                                                   SurfbceDbtb sd)
    {
        SurfbceDbtb newSurfbce = super.getReplbcementScreenSurfbce(peer, sd);
        // if some outstbnding grbphics context wbnts to get b replbcement we
        // need to mbke sure thbt the new surfbce (if it is bccelerbted) is
        // being trbcked
        trbckScreenSurfbce(newSurfbce);
        return newSurfbce;
    }

    /**
     * Remove the gdi surfbce corresponding to the pbssed d3dw surfbce
     * from list of the cbched gdi surfbces.
     *
     * @pbrbm d3dw surfbce for which bssocibted gdi surfbce is to be removed
     */
    privbte void removeGdiSurfbce(finbl D3DWindowSurfbceDbtb d3dw) {
        if (gdiSurfbces != null) {
            GDIWindowSurfbceDbtb gdisd = gdiSurfbces.get(d3dw);
            if (gdisd != null) {
                gdisd.invblidbte();
                gdiSurfbces.remove(d3dw);
            }
        }
    }

    /**
     * If the updbte threbd hbsn't yet been crebted, it will be;
     * otherwise it is bwbken
     */
    privbte synchronized void stbrtUpdbteThrebd() {
        if (screenUpdbter == null) {
            screenUpdbter = AccessController.doPrivileged(
                    (PrivilegedAction<Threbd>) () -> {
                        ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                        Threbd t = new Threbd(rootTG,
                                D3DScreenUpdbteMbnbger.this,
                                "D3D Screen Updbter");
                        // REMIND: should it be higher?
                        t.setPriority(Threbd.NORM_PRIORITY + 2);
                        t.setDbemon(true);
                        return t;
                    });
            screenUpdbter.stbrt();
        } else {
            wbkeUpUpdbteThrebd();
        }
    }

    /**
     * Wbkes up the screen updbter threbd.
     *
     * This method is not synchronous, it doesn't wbit
     * for the updbter threbd to complete the updbtes.
     *
     * It should be used when it is not necessbry to wbit for the
     * completion, for exbmple, when b new surfbce hbd been bdded
     * to the list of trbcked surfbces (which mebns thbt it's bbout
     * to be rendered to).
     */
    public void wbkeUpUpdbteThrebd() {
        synchronized (runLock) {
            runLock.notifyAll();
        }
    }

    /**
     * Wbkes up the screen updbter threbd bnd wbits for the completion
     * of the updbte.
     *
     * This method is cblled from Toolkit.sync() or
     * when there wbs b copy from b VI to the screen
     * so thbt swing bpplicbtions would not bppebr to be
     * sluggish.
     */
    public void runUpdbteNow() {
        synchronized (this) {
            // nothing to do if the updbter threbd hbdn't been stbrted or if
            // there bre no trbcked surfbces
            if (done || screenUpdbter == null ||
                d3dwSurfbces  == null || d3dwSurfbces.size() == 0)
            {
                return;
            }
        }
        synchronized (runLock) {
            needsUpdbteNow = true;
            runLock.notifyAll();
            while (needsUpdbteNow) {
                try {
                    runLock.wbit();
                } cbtch (InterruptedException e) {}
            }
        }
    }

    public void run() {
        while (!done) {
            synchronized (runLock) {
                // If the list is empty, suspend the threbd until b
                // new surfbce is bdded. Note thbt we hbve to check before
                // wbit() (bnd inside the runLock), otherwise we could miss b
                // notify() when b new surfbce is bdded bnd sleep forever.
                long timeout = d3dwSurfbces.size() > 0 ? 100 : 0;

                // don't go to sleep if there's b threbd wbiting for bn updbte
                if (!needsUpdbteNow) {
                    try { runLock.wbit(timeout); }
                        cbtch (InterruptedException e) {}
                }
                // if we were woken up, there bre probbbly surfbces in the list,
                // no need to check if the list is empty
            }

            // mbke b copy to bvoid synchronizbtion during the loop
            D3DWindowSurfbceDbtb surfbces[] = new D3DWindowSurfbceDbtb[] {};
            synchronized (this) {
                surfbces = d3dwSurfbces.toArrby(surfbces);
            }
            for (D3DWindowSurfbceDbtb sd : surfbces) {
                // skip invblid surfbces (they could hbve become invblid
                // bfter we mbde b copy of the list) - just b precbution
                if (sd.isVblid() && (sd.isDirty() || sd.isSurfbceLost())) {
                    if (!sd.isSurfbceLost()) {
                        // the flip bnd the clebring of the dirty stbte
                        // must be done under the lock, otherwise it's
                        // possible to miss bn updbte to the surfbce
                        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
                        rq.lock();
                        try {
                            Rectbngle r = sd.getBounds();
                            D3DSurfbceDbtb.swbpBuffers(sd, 0, 0,
                                                       r.width, r.height);
                            sd.mbrkClebn();
                        } finblly {
                            rq.unlock();
                        }
                    } else if (!vblidbte(sd)) {
                        // it is possible thbt the vblidbtion mby never
                        // succeed, we need to detect this bnd replbce
                        // the d3dw surfbce with gdi; the replbcement of
                        // the surfbce will blso trigger b repbint
                        sd.getPeer().replbceSurfbceDbtbLbter();
                    }
                }
            }
            synchronized (runLock) {
                needsUpdbteNow = fblse;
                runLock.notifyAll();
            }
        }
    }

    /**
     * Restores the pbssed surfbce if it wbs lost, resets the lost stbtus.
     * @pbrbm sd surfbce to be vblidbted
     * @return true if surfbce wbsn't lost or if restorbtion wbs successful,
     * fblse otherwise
     */
    privbte boolebn vblidbte(D3DWindowSurfbceDbtb sd) {
        if (sd.isSurfbceLost()) {
            try {
                sd.restoreSurfbce();
                // if succeeded, first fill the surfbce with bg color
                // note: use the non-synch method to bvoid incorrect lock order
                Color bg = sd.getPeer().getBbckgroundNoSync();
                SunGrbphics2D sg2d = new SunGrbphics2D(sd, bg, bg, null);
                sg2d.fillRect(0, 0, sd.getBounds().width, sd.getBounds().height);
                sg2d.dispose();
                // now clebn the dirty stbtus so thbt we don't flip it
                // next time before it gets repbinted; it is sbfe
                // to do without the lock becbuse we will issue b
                // repbint bnywby so we will not lose bny rendering
                sd.mbrkClebn();
                // since the surfbce wbs successfully restored we need to
                // repbint whole window to repopulbte the bbck-buffer
                repbintPeerTbrget(sd.getPeer());
            } cbtch (InvblidPipeException ipe) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Crebtes (or returns b cbched one) gdi surfbce for the sbme peer bs
     * the pbssed d3dw surfbce hbs.
     *
     * @pbrbm d3dw surfbce used bs key into the cbche
     * @return gdi window surfbce bssocibted with the d3d window surfbces' peer
     */
    privbte synchronized SurfbceDbtb getGdiSurfbce(D3DWindowSurfbceDbtb d3dw) {
        if (gdiSurfbces == null) {
            gdiSurfbces =
                new HbshMbp<D3DWindowSurfbceDbtb, GDIWindowSurfbceDbtb>();
        }
        GDIWindowSurfbceDbtb gdisd = gdiSurfbces.get(d3dw);
        if (gdisd == null) {
            gdisd = GDIWindowSurfbceDbtb.crebteDbtb(d3dw.getPeer());
            gdiSurfbces.put(d3dw, gdisd);
        }
        return gdisd;
    }

    /**
     * Returns true if the component hbs hebvyweight children.
     *
     * @pbrbm comp component to check for hw children
     * @return true if Component hbs hebvyweight children
     */
    privbte stbtic boolebn hbsHWChildren(Component comp) {
        if (comp instbnceof Contbiner) {
            for (Component c : ((Contbiner)comp).getComponents()) {
                if (c.getPeer() instbnceof WComponentPeer || hbsHWChildren(c)) {
                    return true;
                }
            }
        }
        return fblse;
    }
}
