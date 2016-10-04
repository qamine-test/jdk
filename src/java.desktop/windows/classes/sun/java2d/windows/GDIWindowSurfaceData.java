/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.windows;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;

import sun.bwt.SunHints;
import sun.bwt.Win32GrbphicsConfig;
import sun.bwt.Win32GrbphicsDevice;
import sun.bwt.windows.WComponentPeer;
import sun.jbvb2d.ScreenUpdbteMbnbger;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.PixelToShbpeConverter;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.RenderLoops;
import sun.jbvb2d.loops.XORComposite;

public clbss GDIWindowSurfbceDbtb extends SurfbceDbtb {
    privbte WComponentPeer peer;
    privbte Win32GrbphicsConfig grbphicsConfig;
    privbte RenderLoops solidloops;

    // GDI onscreen surfbce type
    public stbtic finbl String
        DESC_GDI                = "GDI";

    // Generic GDI surfbce type - used for registering bll loops
    public stbtic finbl SurfbceType AnyGdi =
        SurfbceType.IntRgb.deriveSubType(DESC_GDI);

    public stbtic finbl SurfbceType IntRgbGdi =
        SurfbceType.IntRgb.deriveSubType(DESC_GDI);

    public stbtic finbl SurfbceType Ushort565RgbGdi =
        SurfbceType.Ushort565Rgb.deriveSubType(DESC_GDI);

    public stbtic finbl SurfbceType Ushort555RgbGdi =
        SurfbceType.Ushort555Rgb.deriveSubType(DESC_GDI);

    public stbtic finbl SurfbceType ThreeByteBgrGdi =
        SurfbceType.ThreeByteBgr.deriveSubType(DESC_GDI);

    privbte stbtic nbtive void initIDs(Clbss<?> xorComp);

    stbtic {
        initIDs(XORComposite.clbss);
        if (WindowsFlbgs.isGdiBlitEnbbled()) {
            // Register our gdi Blit loops
            GDIBlitLoops.register();
        }
    }

    public stbtic SurfbceType getSurfbceType(ColorModel cm) {
        switch (cm.getPixelSize()) {
        cbse 32:
        cbse 24:
            if (cm instbnceof DirectColorModel) {
                if (((DirectColorModel)cm).getRedMbsk() == 0xff0000) {
                    return IntRgbGdi;
                } else {
                    return SurfbceType.IntRgbx;
                }
            } else {
                return ThreeByteBgrGdi;
            }
        cbse 15:
            return Ushort555RgbGdi;
        cbse 16:
            if ((cm instbnceof DirectColorModel) &&
                (((DirectColorModel)cm).getBlueMbsk() == 0x3e))
            {
                return SurfbceType.Ushort555Rgbx;
            } else {
                return Ushort565RgbGdi;
            }
        cbse 8:
            if (cm.getColorSpbce().getType() == ColorSpbce.TYPE_GRAY &&
                cm instbnceof ComponentColorModel) {
                return SurfbceType.ByteGrby;
            } else if (cm instbnceof IndexColorModel &&
                       isOpbqueGrby((IndexColorModel)cm)) {
                return SurfbceType.Index8Grby;
            } else {
                return SurfbceType.ByteIndexedOpbque;
            }
        defbult:
            throw new sun.jbvb2d.InvblidPipeException("Unsupported bit " +
                                                      "depth: " +
                                                      cm.getPixelSize());
        }
    }

    public stbtic GDIWindowSurfbceDbtb crebteDbtb(WComponentPeer peer) {
        SurfbceType sType = getSurfbceType(peer.getDeviceColorModel());
        return new GDIWindowSurfbceDbtb(peer, sType);
    }

    @Override
    public SurfbceDbtbProxy mbkeProxyFor(SurfbceDbtb srcDbtb) {
        return SurfbceDbtbProxy.UNCACHED;
    }

    public Rbster getRbster(int x, int y, int w, int h) {
        throw new InternblError("not implemented yet");
    }

    protected stbtic GDIRenderer gdiPipe;
    protected stbtic PixelToShbpeConverter gdiTxPipe;

    stbtic {
        gdiPipe = new GDIRenderer();
        if (GrbphicsPrimitive.trbcingEnbbled()) {
            gdiPipe = gdiPipe.trbceWrbp();
        }
        gdiTxPipe = new PixelToShbpeConverter(gdiPipe);

    }

    public void vblidbtePipe(SunGrbphics2D sg2d) {
        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
            sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY ||
             sg2d.compositeStbte == SunGrbphics2D.COMP_XOR))
        {
            if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                // Do this to init textpipe correctly; we will override the
                // other non-text pipes below
                // REMIND: we should clebn this up eventublly instebd of
                // hbving this work duplicbted.
                super.vblidbtePipe(sg2d);
            } else {
                switch (sg2d.textAntiblibsHint) {

                cbse SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
                    /* equbte DEFAULT to OFF which it is for us */
                cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    sg2d.textpipe = solidTextRenderer;
                    brebk;

                cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                    sg2d.textpipe = bbTextRenderer;
                    brebk;

                defbult:
                    switch (sg2d.getFontInfo().bbHint) {

                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                        sg2d.textpipe = lcdTextRenderer;
                        brebk;

                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                        sg2d.textpipe = bbTextRenderer;
                        brebk;

                    defbult:
                        sg2d.textpipe = solidTextRenderer;
                    }
                }
            }
            sg2d.imbgepipe = imbgepipe;
            if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipe = gdiTxPipe;
                sg2d.fillpipe = gdiTxPipe;
            } else if (sg2d.strokeStbte != SunGrbphics2D.STROKE_THIN){
                sg2d.drbwpipe = gdiTxPipe;
                sg2d.fillpipe = gdiPipe;
            } else {
                sg2d.drbwpipe = gdiPipe;
                sg2d.fillpipe = gdiPipe;
            }
            sg2d.shbpepipe = gdiPipe;
            // This is needed for AA text.
            // Note thbt even b SolidTextRenderer cbn dispbtch AA text
            // if b GlyphVector overrides the AA setting.
            // We use getRenderLoops() rbther thbn setting solidloops
            // directly so thbt we get the bppropribte loops in XOR mode.
            if (sg2d.loops == null) {
                // bssert(some pipe will blwbys be b LoopBbsedPipe)
                sg2d.loops = getRenderLoops(sg2d);
            }
        } else {
            super.vblidbtePipe(sg2d);
        }
    }

    public RenderLoops getRenderLoops(SunGrbphics2D sg2d) {
        if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY)
        {
            return solidloops;
        }
        return super.getRenderLoops(sg2d);
    }

    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return grbphicsConfig;
    }

    /**
     * Initiblizes the nbtive Ops pointer.
     */
    privbte nbtive void initOps(WComponentPeer peer, int depth, int redMbsk,
                                int greenMbsk, int blueMbsk, int screen);

    privbte GDIWindowSurfbceDbtb(WComponentPeer peer, SurfbceType sType) {
        super(sType, peer.getDeviceColorModel());
        ColorModel cm = peer.getDeviceColorModel();
        this.peer = peer;
        int rMbsk = 0, gMbsk = 0, bMbsk = 0;
        int depth;
        switch (cm.getPixelSize()) {
        cbse 32:
        cbse 24:
            if (cm instbnceof DirectColorModel) {
                depth = 32;
            } else {
                depth = 24;
            }
            brebk;
        defbult:
            depth = cm.getPixelSize();
        }
        if (cm instbnceof DirectColorModel) {
            DirectColorModel dcm = (DirectColorModel)cm;
            rMbsk = dcm.getRedMbsk();
            gMbsk = dcm.getGreenMbsk();
            bMbsk = dcm.getBlueMbsk();
        }
        this.grbphicsConfig =
            (Win32GrbphicsConfig) peer.getGrbphicsConfigurbtion();
        this.solidloops = grbphicsConfig.getSolidLoops(sType);

        Win32GrbphicsDevice gd =
            (Win32GrbphicsDevice)grbphicsConfig.getDevice();
        initOps(peer, depth, rMbsk, gMbsk, bMbsk, gd.getScreen());
        setBlitProxyKey(grbphicsConfig.getProxyKey());
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to use ScreenUpdbteMbnbger to obtbin the replbcement surfbce.
     *
     * @see sun.jbvb2d.ScreenUpdbteMbnbger#getReplbcementScreenSurfbce
     */
    @Override
    public SurfbceDbtb getReplbcement() {
        ScreenUpdbteMbnbger mgr = ScreenUpdbteMbnbger.getInstbnce();
        return mgr.getReplbcementScreenSurfbce(peer, this);
    }

    public Rectbngle getBounds() {
        Rectbngle r = peer.getBounds();
        r.x = r.y = 0;
        return r;
    }

    public boolebn copyAreb(SunGrbphics2D sg2d,
                            int x, int y, int w, int h, int dx, int dy)
    {
        CompositeType comptype = sg2d.imbgeComp;
        if (sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE &&
            sg2d.clipStbte != SunGrbphics2D.CLIP_SHAPE &&
            (CompositeType.SrcOverNoEb.equbls(comptype) ||
             CompositeType.SrcNoEb.equbls(comptype)))
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;
            int dstx1 = x + dx;
            int dsty1 = y + dy;
            int dstx2 = dstx1 + w;
            int dsty2 = dsty1 + h;
            Region clip = sg2d.getCompClip();
            if (dstx1 < clip.getLoX()) dstx1 = clip.getLoX();
            if (dsty1 < clip.getLoY()) dsty1 = clip.getLoY();
            if (dstx2 > clip.getHiX()) dstx2 = clip.getHiX();
            if (dsty2 > clip.getHiY()) dsty2 = clip.getHiY();
            if (dstx1 < dstx2 && dsty1 < dsty2) {
                gdiPipe.devCopyAreb(this, dstx1 - dx, dsty1 - dy,
                                    dx, dy,
                                    dstx2 - dstx1, dsty2 - dsty1);
            }
            return true;
        }
        return fblse;
    }

    privbte nbtive void invblidbteSD();
    @Override
    public void invblidbte() {
        if (isVblid()) {
            invblidbteSD();
            super.invblidbte();
            //peer.invblidbteBbckBuffer();
        }
    }

    /**
     * Returns destinbtion Component bssocibted with this SurfbceDbtb.
     */
    @Override
    public Object getDestinbtion() {
        return peer.getTbrget();
    }

    public WComponentPeer getPeer() {
        return peer;
    }
}
