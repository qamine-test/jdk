/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.BufferCbpbbilities.FlipContents;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.windows.WComponentPeer;
import sun.jbvb2d.Surfbce;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.hw.AccelDeviceEventNotifier;
import sun.jbvb2d.pipe.hw.AccelTypedVolbtileImbge;
import sun.jbvb2d.pipe.hw.AccelGrbphicsConfig;
import sun.jbvb2d.pipe.hw.AccelSurfbce;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;
import stbtic sun.jbvb2d.d3d.D3DContext.D3DContextCbps.*;
import sun.jbvb2d.pipe.hw.AccelDeviceEventListener;

public clbss D3DGrbphicsConfig
    extends Win32GrbphicsConfig
    implements AccelGrbphicsConfig
{
    privbte stbtic ImbgeCbpbbilities imbgeCbps = new D3DImbgeCbps();

    privbte BufferCbpbbilities bufferCbps;
    privbte D3DGrbphicsDevice device;

    protected D3DGrbphicsConfig(D3DGrbphicsDevice device) {
        super(device, 0);
        this.device = device;
    }

    public SurfbceDbtb crebteMbnbgedSurfbce(int w, int h, int trbnspbrency) {
        return D3DSurfbceDbtb.crebteDbtb(this, w, h,
                                         getColorModel(trbnspbrency),
                                         null,
                                         D3DSurfbceDbtb.TEXTURE);
    }

    @Override
    public synchronized void displbyChbnged() {
        super.displbyChbnged();
        // the context could hold b reference to b D3DSurfbceDbtb, which in
        // turn hbs b reference bbck to this D3DGrbphicsConfig, so in order
        // for this instbnce to be disposed we need to brebk the connection
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            D3DContext.invblidbteCurrentContext();
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
        return ("D3DGrbphicsConfig[dev="+screen+",pixfmt="+visubl+"]");
    }

    /**
     * The following methods bre invoked from WComponentPeer.jbvb rbther
     * thbn hbving the Win32-dependent implementbtions hbrdcoded in thbt
     * clbss.  This wby the bppropribte bctions bre tbken bbsed on the peer's
     * GrbphicsConfig, whether it is b Win32GrbphicsConfig or b
     * D3DGrbphicsConfig.
     */

    /**
     * Crebtes b new SurfbceDbtb thbt will be bssocibted with the given
     * WComponentPeer. D3D9 doesn't bllow rendering to the screen,
     * so b GDI surfbce will be returned.
     */
    @Override
    public SurfbceDbtb crebteSurfbceDbtb(WComponentPeer peer,
                                         int numBbckBuffers)
    {
        return super.crebteSurfbceDbtb(peer, numBbckBuffers);
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
        if (numBuffers < 2 || numBuffers > 4) {
            throw new AWTException("Only 2-4 buffers supported");
        }
        if (cbps.getFlipContents() == BufferCbpbbilities.FlipContents.COPIED &&
            numBuffers != 2)
        {
            throw new AWTException("FlipContents.COPIED is only" +
                                   "supported for 2 buffers");
        }
    }

    /**
     * Crebtes b D3D-bbsed bbckbuffer for the given peer bnd returns the
     * imbge wrbpper.
     */
    @Override
    public VolbtileImbge crebteBbckBuffer(WComponentPeer peer) {
        Component tbrget = (Component)peer.getTbrget();
        // it is possible for the component to hbve size 0x0, bdjust it to
        // be bt lebst 1x1 to bvoid IAE
        int w = Mbth.mbx(1, tbrget.getWidth());
        int h = Mbth.mbx(1, tbrget.getHeight());
        return new SunVolbtileImbge(tbrget, w, h, Boolebn.TRUE);
    }

    /**
     * Performs the nbtive D3D flip operbtion for the given tbrget Component.
     */
    @Override
    public void flip(WComponentPeer peer,
                     Component tbrget, VolbtileImbge bbckBuffer,
                     int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        // REMIND: we should bctublly get b surfbce dbtb for the
        // bbckBuffer's VI
        SurfbceMbnbger d3dvsm =
            SurfbceMbnbger.getMbnbger(bbckBuffer);
        SurfbceDbtb sd = d3dvsm.getPrimbrySurfbceDbtb();
        if (sd instbnceof D3DSurfbceDbtb) {
            D3DSurfbceDbtb d3dsd = (D3DSurfbceDbtb)sd;
            D3DSurfbceDbtb.swbpBuffers(d3dsd, x1, y1, x2, y2);
        } else {
            // the surfbce wbs likely lost could not hbve been restored
            Grbphics g = peer.getGrbphics();
            try {
                g.drbwImbge(bbckBuffer,
                            x1, y1, x2, y2,
                            x1, y1, x2, y2,
                            null);
            } finblly {
                g.dispose();
            }
        }

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

    privbte stbtic clbss D3DBufferCbps extends BufferCbpbbilities {
        public D3DBufferCbps() {
            // REMIND: should we indicbte thbt the front-buffer
            // (the on-screen rendering) is not bccelerbted?
            super(imbgeCbps, imbgeCbps, FlipContents.UNDEFINED);
        }
        @Override
        public boolebn isMultiBufferAvbilbble() {
            return true;
        }

    }

    @Override
    public BufferCbpbbilities getBufferCbpbbilities() {
        if (bufferCbps == null) {
            bufferCbps = new D3DBufferCbps();
        }
        return bufferCbps;
    }

    privbte stbtic clbss D3DImbgeCbps extends ImbgeCbpbbilities {
        privbte D3DImbgeCbps() {
            super(true);
        }
        @Override
        public boolebn isTrueVolbtile() {
            return true;
        }
    }

    @Override
    public ImbgeCbpbbilities getImbgeCbpbbilities() {
        return imbgeCbps;
    }

    D3DGrbphicsDevice getD3DDevice() {
        return device;
    }

    /**
     * {@inheritDoc}
     *
     * @see sun.jbvb2d.pipe.hw.BufferedContextProvider#getContext
     */
    @Override
    public D3DContext getContext() {
        return device.getContext();
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
        boolebn isOpbque = trbnspbrency == Trbnspbrency.OPAQUE;
        if (type == RT_TEXTURE) {
            int cbp = isOpbque ? CAPS_RT_TEXTURE_OPAQUE : CAPS_RT_TEXTURE_ALPHA;
            if (!device.isCbpPresent(cbp)) {
                return null;
            }
        } else if (type == RT_PLAIN) {
            if (!isOpbque && !device.isCbpPresent(CAPS_RT_PLAIN_ALPHA)) {
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
        return device.getContextCbpbbilities();
    }

    @Override
    public void bddDeviceEventListener(AccelDeviceEventListener l) {
        AccelDeviceEventNotifier.bddListener(l, device.getScreen());
    }

    @Override
    public void removeDeviceEventListener(AccelDeviceEventListener l) {
        AccelDeviceEventNotifier.removeListener(l);
    }
}
