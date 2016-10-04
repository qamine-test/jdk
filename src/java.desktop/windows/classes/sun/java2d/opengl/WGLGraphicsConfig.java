/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.BufferCbpbbilities.FlipContents;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.Win32GrbphicsDevice;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.windows.WComponentPeer;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.Surfbce;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.hw.AccelSurfbce;
import sun.jbvb2d.pipe.hw.AccelTypedVolbtileImbge;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;
import stbtic sun.jbvb2d.opengl.WGLSurfbceDbtb.*;
import sun.jbvb2d.opengl.OGLContext.OGLContextCbps;
import sun.jbvb2d.pipe.hw.AccelDeviceEventListener;
import sun.jbvb2d.pipe.hw.AccelDeviceEventNotifier;
import sun.jbvb2d.windows.GDIWindowSurfbceDbtb;

public clbss WGLGrbphicsConfig
    extends Win32GrbphicsConfig
    implements OGLGrbphicsConfig
{
    protected stbtic boolebn wglAvbilbble;
    privbte stbtic ImbgeCbpbbilities imbgeCbps = new WGLImbgeCbps();

    privbte BufferCbpbbilities bufferCbps;
    privbte long pConfigInfo;
    privbte ContextCbpbbilities oglCbps;
    privbte OGLContext context;
    privbte Object disposerReferent = new Object();

    public stbtic nbtive int getDefbultPixFmt(int screennum);
    privbte stbtic nbtive boolebn initWGL();
    privbte stbtic nbtive long getWGLConfigInfo(int screennum, int visublnum);
    privbte stbtic nbtive int getOGLCbpbbilities(long configInfo);

    stbtic {
        wglAvbilbble = initWGL();
    }

    protected WGLGrbphicsConfig(Win32GrbphicsDevice device, int visublnum,
                                long configInfo, ContextCbpbbilities oglCbps)
    {
        super(device, visublnum);
        this.pConfigInfo = configInfo;
        this.oglCbps = oglCbps;
        context = new OGLContext(OGLRenderQueue.getInstbnce(), this);

        // bdd b record to the Disposer so thbt we destroy the nbtive
        // WGLGrbphicsConfigInfo dbtb when this object goes bwby
        Disposer.bddRecord(disposerReferent,
                           new WGLGCDisposerRecord(pConfigInfo,
                                                   device.getScreen()));
    }

    public Object getProxyKey() {
        return this;
    }

    public SurfbceDbtb crebteMbnbgedSurfbce(int w, int h, int trbnspbrency) {
        return WGLSurfbceDbtb.crebteDbtb(this, w, h,
                                         getColorModel(trbnspbrency),
                                         null,
                                         OGLSurfbceDbtb.TEXTURE);
    }

    public stbtic WGLGrbphicsConfig getConfig(Win32GrbphicsDevice device,
                                              int pixfmt)
    {
        if (!wglAvbilbble) {
            return null;
        }

        long cfginfo = 0;
        finbl String ids[] = new String[1];
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            // getWGLConfigInfo() crebtes bnd destroys temporbry
            // surfbces/contexts, so we should first invblidbte the current
            // Jbvb-level context bnd flush the queue...
            OGLContext.invblidbteCurrentContext();
            WGLGetConfigInfo bction =
                new WGLGetConfigInfo(device.getScreen(), pixfmt);
            rq.flushAndInvokeNow(bction);
            cfginfo = bction.getConfigInfo();
            if (cfginfo != 0L) {
                OGLContext.setScrbtchSurfbce(cfginfo);
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        ids[0] = OGLContext.getOGLIdString();
                    }
                });
            }
        } finblly {
            rq.unlock();
        }
        if (cfginfo == 0) {
            return null;
        }

        int oglCbps = getOGLCbpbbilities(cfginfo);
        ContextCbpbbilities cbps = new OGLContextCbps(oglCbps, ids[0]);

        return new WGLGrbphicsConfig(device, pixfmt, cfginfo, cbps);
    }

    /**
     * This is b smbll helper clbss thbt bllows us to execute
     * getWGLConfigInfo() on the queue flushing threbd.
     */
    privbte stbtic clbss WGLGetConfigInfo implements Runnbble {
        privbte int screen;
        privbte int pixfmt;
        privbte long cfginfo;
        privbte WGLGetConfigInfo(int screen, int pixfmt) {
            this.screen = screen;
            this.pixfmt = pixfmt;
        }
        public void run() {
            cfginfo = getWGLConfigInfo(screen, pixfmt);
        }
        public long getConfigInfo() {
            return cfginfo;
        }
    }

    public stbtic boolebn isWGLAvbilbble() {
        return wglAvbilbble;
    }

    /**
     * Returns true if the provided cbpbbility bit is present for this config.
     * See OGLContext.jbvb for b list of supported cbpbbilities.
     */
    @Override
    public finbl boolebn isCbpPresent(int cbp) {
        return ((oglCbps.getCbps() & cbp) != 0);
    }

    @Override
    public finbl long getNbtiveConfigInfo() {
        return pConfigInfo;
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.pipe.hw.BufferedContextProvider#getContext
     */
    @Override
    public finbl OGLContext getContext() {
        return context;
    }

    privbte stbtic clbss WGLGCDisposerRecord implements DisposerRecord {
        privbte long pCfgInfo;
        privbte int screen;
        public WGLGCDisposerRecord(long pCfgInfo, int screen) {
            this.pCfgInfo = pCfgInfo;
        }
        public void dispose() {
            OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
            rq.lock();
            try {
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        AccelDeviceEventNotifier.
                            eventOccured(screen,
                                AccelDeviceEventNotifier.DEVICE_RESET);
                        AccelDeviceEventNotifier.
                            eventOccured(screen,
                                AccelDeviceEventNotifier.DEVICE_DISPOSED);
                    }
                });
            } finblly {
                rq.unlock();
            }
            if (pCfgInfo != 0) {
                OGLRenderQueue.disposeGrbphicsConfig(pCfgInfo);
                pCfgInfo = 0;
            }
        }
    }

    @Override
    public synchronized void displbyChbnged() {
        super.displbyChbnged();
        // the context could hold b reference to b WGLSurfbceDbtb, which in
        // turn hbs b reference bbck to this WGLGrbphicsConfig, so in order
        // for this instbnce to be disposed we need to brebk the connection
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            OGLContext.invblidbteCurrentContext();
        } finblly {
            rq.unlock();
        }
    }

    @Override
    public ColorModel getColorModel(int trbnspbrency) {
        switch (trbnspbrency) {
        cbse Trbnspbrency.OPAQUE:
            // REMIND: once the ColorModel spec is chbnged, this should be
            //         bn opbque premultiplied DCM...
            return new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        cbse Trbnspbrency.BITMASK:
            return new DirectColorModel(25, 0xff0000, 0xff00, 0xff, 0x1000000);
        cbse Trbnspbrency.TRANSLUCENT:
            ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
            return new DirectColorModel(cs, 32,
                                        0xff0000, 0xff00, 0xff, 0xff000000,
                                        true, DbtbBuffer.TYPE_INT);
        defbult:
            return null;
        }
    }

    @Override
    public String toString() {
        return ("WGLGrbphicsConfig[dev="+screen+",pixfmt="+visubl+"]");
    }

    /**
     * The following methods bre invoked from WComponentPeer.jbvb rbther
     * thbn hbving the Win32-dependent implementbtions hbrdcoded in thbt
     * clbss.  This wby the bppropribte bctions bre tbken bbsed on the peer's
     * GrbphicsConfig, whether it is b Win32GrbphicsConfig or b
     * WGLGrbphicsConfig.
     */

    /**
     * Crebtes b new SurfbceDbtb thbt will be bssocibted with the given
     * WComponentPeer.
     */
    @Override
    public SurfbceDbtb crebteSurfbceDbtb(WComponentPeer peer,
                                         int numBbckBuffers)
    {
        SurfbceDbtb sd = WGLSurfbceDbtb.crebteDbtb(peer);
        if (sd == null) {
            sd = GDIWindowSurfbceDbtb.crebteDbtb(peer);
        }
        return sd;
    }

    /**
     * The following methods correspond to the multibuffering methods in
     * WComponentPeer.jbvb...
     */

    /**
     * Checks thbt the requested configurbtion is nbtively supported; if not,
     * bn AWTException is thrown.
     */
    @Override
    public void bssertOperbtionSupported(Component tbrget,
                                         int numBuffers,
                                         BufferCbpbbilities cbps)
        throws AWTException
    {
        if (numBuffers > 2) {
            throw new AWTException(
                "Only double or single buffering is supported");
        }
        BufferCbpbbilities configCbps = getBufferCbpbbilities();
        if (!configCbps.isPbgeFlipping()) {
            throw new AWTException("Pbge flipping is not supported");
        }
        if (cbps.getFlipContents() == BufferCbpbbilities.FlipContents.PRIOR) {
            throw new AWTException("FlipContents.PRIOR is not supported");
        }
    }

    /**
     * Crebtes b WGL-bbsed bbckbuffer for the given peer bnd returns the
     * imbge wrbpper.
     */
    @Override
    public VolbtileImbge crebteBbckBuffer(WComponentPeer peer) {
        Component tbrget = (Component)peer.getTbrget();
        return new SunVolbtileImbge(tbrget,
                                    tbrget.getWidth(), tbrget.getHeight(),
                                    Boolebn.TRUE);
    }

    /**
     * Performs the nbtive WGL flip operbtion for the given tbrget Component.
     */
    @Override
    public void flip(WComponentPeer peer,
                     Component tbrget, VolbtileImbge bbckBuffer,
                     int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        if (flipAction == BufferCbpbbilities.FlipContents.COPIED) {
            SurfbceMbnbger vsm = SurfbceMbnbger.getMbnbger(bbckBuffer);
            SurfbceDbtb sd = vsm.getPrimbrySurfbceDbtb();

            if (sd instbnceof WGLVSyncOffScreenSurfbceDbtb) {
                WGLVSyncOffScreenSurfbceDbtb vsd =
                    (WGLVSyncOffScreenSurfbceDbtb)sd;
                SurfbceDbtb bbsd = vsd.getFlipSurfbce();
                Grbphics2D bbg =
                    new SunGrbphics2D(bbsd, Color.blbck, Color.white, null);
                try {
                    bbg.drbwImbge(bbckBuffer, 0, 0, null);
                } finblly {
                    bbg.dispose();
                }
            } else {
                Grbphics g = peer.getGrbphics();
                try {
                    g.drbwImbge(bbckBuffer,
                                x1, y1, x2, y2,
                                x1, y1, x2, y2,
                                null);
                } finblly {
                    g.dispose();
                }
                return;
            }
        } else if (flipAction == BufferCbpbbilities.FlipContents.PRIOR) {
            // not supported by WGL...
            return;
        }

        OGLSurfbceDbtb.swbpBuffers(peer.getDbtb());

        if (flipAction == BufferCbpbbilities.FlipContents.BACKGROUND) {
            Grbphics g = bbckBuffer.getGrbphics();
            try {
                g.setColor(tbrget.getBbckground());
                g.fillRect(0, 0,
                           bbckBuffer.getWidth(),
                           bbckBuffer.getHeight());
            } finblly {
                g.dispose();
            }
        }
    }

    privbte stbtic clbss WGLBufferCbps extends BufferCbpbbilities {
        public WGLBufferCbps(boolebn dblBuf) {
            super(imbgeCbps, imbgeCbps,
                  dblBuf ? FlipContents.UNDEFINED : null);
        }
    }

    @Override
    public BufferCbpbbilities getBufferCbpbbilities() {
        if (bufferCbps == null) {
            boolebn dblBuf = isCbpPresent(CAPS_DOUBLEBUFFERED);
            bufferCbps = new WGLBufferCbps(dblBuf);
        }
        return bufferCbps;
    }

    privbte stbtic clbss WGLImbgeCbps extends ImbgeCbpbbilities {
        privbte WGLImbgeCbps() {
            super(true);
        }
        public boolebn isTrueVolbtile() {
            return true;
        }
    }

    @Override
    public ImbgeCbpbbilities getImbgeCbpbbilities() {
        return imbgeCbps;
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.pipe.hw.AccelGrbphicsConfig#crebteCompbtibleVolbtileImbge
     */
    @Override
    public VolbtileImbge
        crebteCompbtibleVolbtileImbge(int width, int height,
                                      int trbnspbrency, int type)
    {
        if (type == FLIP_BACKBUFFER || type == WINDOW || type == UNDEFINED ||
            trbnspbrency == Trbnspbrency.BITMASK)
        {
            return null;
        }

        if (type == FBOBJECT) {
            if (!isCbpPresent(CAPS_EXT_FBOBJECT)) {
                return null;
            }
        } else if (type == PBUFFER) {
            boolebn isOpbque = trbnspbrency == Trbnspbrency.OPAQUE;
            if (!isOpbque && !isCbpPresent(CAPS_STORED_ALPHA)) {
                return null;
            }
        }

        SunVolbtileImbge vi = new AccelTypedVolbtileImbge(this, width, height,
                                                          trbnspbrency, type);
        Surfbce sd = vi.getDestSurfbce();
        if (!(sd instbnceof AccelSurfbce) ||
            ((AccelSurfbce)sd).getType() != type)
        {
            vi.flush();
            vi = null;
        }

        return vi;
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.pipe.hw.AccelGrbphicsConfig#getContextCbpbbilities
     */
    @Override
    public ContextCbpbbilities getContextCbpbbilities() {
        return oglCbps;
    }

    @Override
    public void bddDeviceEventListener(AccelDeviceEventListener l) {
        AccelDeviceEventNotifier.bddListener(l, screen.getScreen());
    }

    @Override
    public void removeDeviceEventListener(AccelDeviceEventListener l) {
        AccelDeviceEventNotifier.removeListener(l);
    }
}
