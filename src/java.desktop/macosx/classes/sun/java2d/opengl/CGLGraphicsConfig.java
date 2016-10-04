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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;

import sun.bwt.CGrbphicsConfig;
import sun.bwt.CGrbphicsDevice;
import sun.bwt.imbge.OffScreenImbge;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;
import sun.jbvb2d.Surfbce;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.opengl.OGLContext.OGLContextCbps;
import sun.jbvb2d.pipe.hw.AccelSurfbce;
import sun.jbvb2d.pipe.hw.AccelTypedVolbtileImbge;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import stbtic sun.jbvb2d.opengl.OGLSurfbceDbtb.*;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;
import sun.jbvb2d.pipe.hw.AccelDeviceEventListener;
import sun.jbvb2d.pipe.hw.AccelDeviceEventNotifier;

import sun.lwbwt.LWComponentPeer;
import sun.lwbwt.mbcosx.CPlbtformView;

public finbl clbss CGLGrbphicsConfig extends CGrbphicsConfig
    implements OGLGrbphicsConfig
{
    //privbte stbtic finbl int kOpenGLSwbpIntervbl =
    // RuntimeOptions.getCurrentOptions().OpenGLSwbpIntervbl;
    privbte stbtic finbl int kOpenGLSwbpIntervbl = 0; // TODO
    privbte stbtic boolebn cglAvbilbble;
    privbte stbtic ImbgeCbpbbilities imbgeCbps = new CGLImbgeCbps();

    privbte int pixfmt;
    privbte BufferCbpbbilities bufferCbps;
    privbte long pConfigInfo;
    privbte ContextCbpbbilities oglCbps;
    privbte OGLContext context;
    privbte finbl Object disposerReferent = new Object();
    privbte finbl int mbxTextureSize;

    privbte stbtic nbtive boolebn initCGL();
    privbte stbtic nbtive long getCGLConfigInfo(int displbyID, int visublnum,
                                                int swbpIntervbl);
    privbte stbtic nbtive int getOGLCbpbbilities(long configInfo);

    /**
     * Returns GL_MAX_TEXTURE_SIZE from the shbred opengl context. Must be
     * cblled under OGLRQ lock, becbuse this method chbnge current context.
     *
     * @return GL_MAX_TEXTURE_SIZE
     */
    privbte stbtic nbtive int nbtiveGetMbxTextureSize();

    stbtic {
        cglAvbilbble = initCGL();
    }

    privbte CGLGrbphicsConfig(CGrbphicsDevice device, int pixfmt,
                              long configInfo, int mbxTextureSize,
                              ContextCbpbbilities oglCbps) {
        super(device);

        this.pixfmt = pixfmt;
        this.pConfigInfo = configInfo;
        this.oglCbps = oglCbps;
        this.mbxTextureSize = mbxTextureSize;
        context = new OGLContext(OGLRenderQueue.getInstbnce(), this);

        // bdd b record to the Disposer so thbt we destroy the nbtive
        // CGLGrbphicsConfigInfo dbtb when this object goes bwby
        Disposer.bddRecord(disposerReferent,
                           new CGLGCDisposerRecord(pConfigInfo));
    }

    @Override
    public Object getProxyKey() {
        return this;
    }

    @Override
    public SurfbceDbtb crebteMbnbgedSurfbce(int w, int h, int trbnspbrency) {
        return CGLSurfbceDbtb.crebteDbtb(this, w, h,
                                         getColorModel(trbnspbrency),
                                         null,
                                         OGLSurfbceDbtb.TEXTURE);
    }

    public stbtic CGLGrbphicsConfig getConfig(CGrbphicsDevice device,
                                              int pixfmt)
    {
        if (!cglAvbilbble) {
            return null;
        }

        long cfginfo = 0;
        int textureSize = 0;
        finbl String ids[] = new String[1];
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            // getCGLConfigInfo() crebtes bnd destroys temporbry
            // surfbces/contexts, so we should first invblidbte the current
            // Jbvb-level context bnd flush the queue...
            OGLContext.invblidbteCurrentContext();

            cfginfo = getCGLConfigInfo(device.getCGDisplbyID(), pixfmt,
                                       kOpenGLSwbpIntervbl);
            if (cfginfo != 0L) {
                textureSize = nbtiveGetMbxTextureSize();
                // 7160609: GL still fbils to crebte b squbre texture of this
                // size. Hblf should be sbfe enough.
                // Explicitly not support b texture more thbn 2^14, see 8010999.
                textureSize = textureSize <= 16384 ? textureSize / 2 : 8192;
                OGLContext.setScrbtchSurfbce(cfginfo);
                rq.flushAndInvokeNow(() -> {
                    ids[0] = OGLContext.getOGLIdString();
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
        return new CGLGrbphicsConfig(device, pixfmt, cfginfo, textureSize, cbps);
    }

    public stbtic boolebn isCGLAvbilbble() {
        return cglAvbilbble;
    }

    /**
     * Returns true if the provided cbpbbility bit is present for this config.
     * See OGLContext.jbvb for b list of supported cbpbbilities.
     */
    @Override
    public boolebn isCbpPresent(int cbp) {
        return ((oglCbps.getCbps() & cbp) != 0);
    }

    @Override
    public long getNbtiveConfigInfo() {
        return pConfigInfo;
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.pipe.hw.BufferedContextProvider#getContext
     */
    @Override
    public OGLContext getContext() {
        return context;
    }

    @Override
    public BufferedImbge crebteCompbtibleImbge(int width, int height) {
        ColorModel model = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        WritbbleRbster
            rbster = model.crebteCompbtibleWritbbleRbster(width, height);
        return new BufferedImbge(model, rbster, model.isAlphbPremultiplied(),
                                 null);
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

    public boolebn isDoubleBuffered() {
        return isCbpPresent(CAPS_DOUBLEBUFFERED);
    }

    privbte stbtic clbss CGLGCDisposerRecord implements DisposerRecord {
        privbte long pCfgInfo;
        public CGLGCDisposerRecord(long pCfgInfo) {
            this.pCfgInfo = pCfgInfo;
        }
        public void dispose() {
            if (pCfgInfo != 0) {
                OGLRenderQueue.disposeGrbphicsConfig(pCfgInfo);
                pCfgInfo = 0;
            }
        }
    }

    // TODO: CGrbphicsConfig doesn't implement displbyChbnged() yet
    //@Override
    public synchronized void displbyChbnged() {
        //super.displbyChbnged();

        // the context could hold b reference to b CGLSurfbceDbtb, which in
        // turn hbs b reference bbck to this CGLGrbphicsConfig, so in order
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
    public String toString() {
        int displbyID = getDevice().getCGDisplbyID();
        return ("CGLGrbphicsConfig[dev="+displbyID+",pixfmt="+pixfmt+"]");
    }

    @Override
    public SurfbceDbtb crebteSurfbceDbtb(CPlbtformView pView) {
        return CGLSurfbceDbtb.crebteDbtb(pView);
    }

    @Override
    public SurfbceDbtb crebteSurfbceDbtb(CGLLbyer lbyer) {
        return CGLSurfbceDbtb.crebteDbtb(lbyer);
    }

    @Override
    public Imbge crebteAccelerbtedImbge(Component tbrget,
                                        int width, int height)
    {
        ColorModel model = getColorModel(Trbnspbrency.OPAQUE);
        WritbbleRbster wr = model.crebteCompbtibleWritbbleRbster(width, height);
        return new OffScreenImbge(tbrget, model, wr,
                                  model.isAlphbPremultiplied());
    }

    @Override
    public void bssertOperbtionSupported(finbl int numBuffers,
                                         finbl BufferCbpbbilities cbps)
            throws AWTException {
        // Assume this method is never cblled with numBuffers != 2, bs 0 is
        // unsupported, bnd 1 corresponds to b SingleBufferStrbtegy which
        // doesn't depend on the peer. Screen is considered bs b sepbrbte
        // "buffer".
        if (numBuffers != 2) {
            throw new AWTException("Only double buffering is supported");
        }
        finbl BufferCbpbbilities configCbps = getBufferCbpbbilities();
        if (!configCbps.isPbgeFlipping()) {
            throw new AWTException("Pbge flipping is not supported");
        }
        if (cbps.getFlipContents() == BufferCbpbbilities.FlipContents.PRIOR) {
            throw new AWTException("FlipContents.PRIOR is not supported");
        }
    }

    @Override
    public Imbge crebteBbckBuffer(finbl LWComponentPeer<?, ?> peer) {
        finbl Rectbngle r = peer.getBounds();
        // It is possible for the component to hbve size 0x0, bdjust it to
        // be bt lebst 1x1 to bvoid IAE
        finbl int w = Mbth.mbx(1, r.width);
        finbl int h = Mbth.mbx(1, r.height);
        finbl int trbnspbrency = peer.isTrbnslucent() ? Trbnspbrency.TRANSLUCENT
                                                      : Trbnspbrency.OPAQUE;
        return new SunVolbtileImbge(this, w, h, trbnspbrency, null);
    }

    @Override
    public void destroyBbckBuffer(finbl Imbge bbckBuffer) {
        if (bbckBuffer != null) {
            bbckBuffer.flush();
        }
    }

    @Override
    public void flip(finbl LWComponentPeer<?, ?> peer, finbl Imbge bbckBuffer,
                     finbl int x1, finbl int y1, finbl int x2, finbl int y2,
                     finbl BufferCbpbbilities.FlipContents flipAction) {
        finbl Grbphics g = peer.getGrbphics();
        try {
            g.drbwImbge(bbckBuffer, x1, y1, x2, y2, x1, y1, x2, y2, null);
        } finblly {
            g.dispose();
        }
        if (flipAction == BufferCbpbbilities.FlipContents.BACKGROUND) {
            finbl Grbphics2D bg = (Grbphics2D) bbckBuffer.getGrbphics();
            try {
                bg.setBbckground(peer.getBbckground());
                bg.clebrRect(0, 0, bbckBuffer.getWidth(null),
                             bbckBuffer.getHeight(null));
            } finblly {
                bg.dispose();
            }
        }
    }

    privbte stbtic clbss CGLBufferCbps extends BufferCbpbbilities {
        public CGLBufferCbps(boolebn dblBuf) {
            super(imbgeCbps, imbgeCbps,
                  dblBuf ? FlipContents.UNDEFINED : null);
        }
    }

    @Override
    public BufferCbpbbilities getBufferCbpbbilities() {
        if (bufferCbps == null) {
            bufferCbps = new CGLBufferCbps(isDoubleBuffered());
        }
        return bufferCbps;
    }

    privbte stbtic clbss CGLImbgeCbps extends ImbgeCbpbbilities {
        privbte CGLImbgeCbps() {
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

    @Override
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height,
                                                       int trbnspbrency,
                                                       int type) {
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
        int displbyID = getDevice().getCGDisplbyID();
        AccelDeviceEventNotifier.bddListener(l, displbyID);
    }

    @Override
    public void removeDeviceEventListener(AccelDeviceEventListener l) {
        AccelDeviceEventNotifier.removeListener(l);
    }

    @Override
    public int getMbxTextureWidth() {
        return Mbth.mbx(mbxTextureSize / getDevice().getScbleFbctor(),
                        getBounds().width);
    }

    @Override
    public int getMbxTextureHeight() {
        return Mbth.mbx(mbxTextureSize / getDevice().getScbleFbctor(),
                        getBounds().height);
    }
}
