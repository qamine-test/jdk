/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.*;
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import sun.bwt.*;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.jules.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;
import sun.jbvb2d.x11.*;
import sun.font.FontMbnbgerNbtiveLibrbry;

public bbstrbct clbss XRSurfbceDbtb extends XSurfbceDbtb {
    X11ComponentPeer peer;
    XRGrbphicsConfig grbphicsConfig;
    XRBbckend renderQueue;

    privbte RenderLoops solidloops;

    protected int depth;

    privbte stbtic nbtive void initIDs();

    protected nbtive void XRInitSurfbce(int depth, int width, int height,
                                        long drbwbble, int pictFormbt);

    nbtive void initXRPicture(long xsdo, int pictForm);

    nbtive void freeXSDOPicture(long xsdo);

    public stbtic finbl String DESC_BYTE_A8_X11 = "Byte A8 Pixmbp";
    public stbtic finbl String DESC_INT_RGB_X11 = "Integer RGB Pixmbp";
    public stbtic finbl String DESC_INT_ARGB_X11 = "Integer ARGB-Pre Pixmbp";

    public stbtic finbl SurfbceType
        ByteA8X11 = SurfbceType.ByteGrby.deriveSubType(DESC_BYTE_A8_X11);
    public stbtic finbl SurfbceType
        IntRgbX11 = SurfbceType.IntRgb.deriveSubType(DESC_INT_RGB_X11);
    public stbtic finbl SurfbceType
        IntArgbPreX11 = SurfbceType.IntArgbPre.deriveSubType(DESC_INT_ARGB_X11);

    public Rbster getRbster(int x, int y, int w, int h) {
        throw new InternblError("not implemented yet");
    }

    protected XRRenderer xrpipe;
    protected PixelToShbpeConverter xrtxpipe;
    protected TextPipe xrtextpipe;
    protected XRDrbwImbge xrDrbwImbge;

    protected ShbpeDrbwPipe bbShbpePipe;
    protected PixelToShbpeConverter bbPixelToShbpeConv;

    public stbtic void initXRSurfbceDbtb() {
        if (!isX11SurfbceDbtbInitiblized()) {
            FontMbnbgerNbtiveLibrbry.lobd();
            initIDs();
            XRPMBlitLoops.register();
            XRMbskFill.register();
            XRMbskBlit.register();

            setX11SurfbceDbtbInitiblized();
        }
    }

    /**
     * Synchronized bccessor method for isDrbwbbleVblid.
     */
    protected boolebn isXRDrbwbbleVblid() {
        try {
            SunToolkit.bwtLock();
            return isDrbwbbleVblid();
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    @Override
    public SurfbceDbtbProxy mbkeProxyFor(SurfbceDbtb srcDbtb) {
        return XRSurfbceDbtbProxy.crebteProxy(srcDbtb, grbphicsConfig);
    }

    @Override
    public void vblidbtePipe(SunGrbphics2D sg2d) {
        TextPipe textpipe;
        boolebn vblidbted = fblse;

        /*
         * The textpipe for now cbn't hbndle TexturePbint when extrb-blphb is
         * specified nore XOR mode
         */
        if ((textpipe = getTextPipe(sg2d)) == null)
        {
            super.vblidbtePipe(sg2d);
            textpipe = sg2d.textpipe;
            vblidbted = true;
        }

        PixelToShbpeConverter txPipe = null;
        XRRenderer nonTxPipe = null;

        /*
         * TODO: Cbn we rely on the GC for ARGB32 surfbces?
         */
        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) {
            if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR) {
                if (sg2d.compositeStbte <= SunGrbphics2D.COMP_XOR) {
                    txPipe = xrtxpipe;
                    nonTxPipe = xrpipe;
                }
            } else if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA) {
                if (XRPbints.isVblid(sg2d)) {
                    txPipe = xrtxpipe;
                    nonTxPipe = xrpipe;
                }
                // custom pbints hbndled by super.vblidbtePipe() below
            }
        }

        if (sg2d.bntiblibsHint == SunHints.INTVAL_ANTIALIAS_ON &&
            JulesPbthBuf.isCbiroAvbilbble())
        {
            sg2d.shbpepipe = bbShbpePipe;
            sg2d.drbwpipe = bbPixelToShbpeConv;
            sg2d.fillpipe = bbPixelToShbpeConv;
        } else {
            if (txPipe != null) {
                if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                    sg2d.drbwpipe = txPipe;
                    sg2d.fillpipe = txPipe;
                } else if (sg2d.strokeStbte != SunGrbphics2D.STROKE_THIN) {
                    sg2d.drbwpipe = txPipe;
                    sg2d.fillpipe = nonTxPipe;
                } else {
                    sg2d.drbwpipe = nonTxPipe;
                    sg2d.fillpipe = nonTxPipe;
                }
                sg2d.shbpepipe = nonTxPipe;
            } else {
                if (!vblidbted) {
                    super.vblidbtePipe(sg2d);
                }
            }
        }

        // instbll the text pipe bbsed on our ebrlier decision
        sg2d.textpipe = textpipe;

        // blwbys override the imbge pipe with the speciblized XRender pipe
        sg2d.imbgepipe = xrDrbwImbge;
    }

    protected TextPipe getTextPipe(SunGrbphics2D sg2d) {
        boolebn supportedPbint = sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA
                && (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR || sg2d.composite == null);

        boolebn supportedCompOp = fblse;
        if (sg2d.composite instbnceof AlphbComposite) {
            int compRule = ((AlphbComposite) sg2d.composite).getRule();
            supportedCompOp = XRUtils.isMbskEvblubted(XRUtils.j2dAlphbCompToXR(compRule))
                    || (compRule == AlphbComposite.SRC
                                && sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR);
        }

        return (supportedPbint && supportedCompOp) ? xrtextpipe : null;
    }

    protected MbskFill getMbskFill(SunGrbphics2D sg2d) {
        AlphbComposite bComp = null;
        if(sg2d.composite != null
                && sg2d.composite instbnceof AlphbComposite) {
            bComp = (AlphbComposite) sg2d.composite;
        }

        boolebn supportedPbint = sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR
                || XRPbints.isVblid(sg2d);

        boolebn supportedCompOp = fblse;
        if(bComp != null) {
            int rule = bComp.getRule();
            supportedCompOp = XRUtils.isMbskEvblubted(XRUtils.j2dAlphbCompToXR(rule));
        }

        return (supportedPbint && supportedCompOp) ?  super.getMbskFill(sg2d) : null;
    }

    public RenderLoops getRenderLoops(SunGrbphics2D sg2d) {
        if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA)
        {
            return solidloops;
        }

        return super.getRenderLoops(sg2d);
    }

    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return grbphicsConfig;
    }

    /**
     * Method for instbntibting b Window SurfbceDbtb
     */
    public stbtic XRWindowSurfbceDbtb crebteDbtb(X11ComponentPeer peer) {
        XRGrbphicsConfig gc = getGC(peer);
        return new XRWindowSurfbceDbtb(peer, gc, gc.getSurfbceType());
    }

    /**
     * Method for instbntibting b Pixmbp SurfbceDbtb (offscreen).
     * If the surfbce * is opbque b 24-bit/RGB surfbce is chosen,
     * otherwise b 32-bit ARGB surfbce.
     */
    public stbtic XRPixmbpSurfbceDbtb crebteDbtb(XRGrbphicsConfig gc,
                                                 int width, int height,
                                                 ColorModel cm, Imbge imbge,
                                                 long drbwbble,
                                                 int trbnspbrency) {
        int depth;
        // If we hbve b 32 bit color model for the window it needs
        // blphb to support trbnslucency of the window so we need
        //  to upgrbde whbt wbs requested for the surfbce.
        if (gc.getColorModel().getPixelSize() == 32) {
           depth = 32;
           trbnspbrency = Trbnspbrency.TRANSLUCENT;
        } else {
            depth = trbnspbrency > Trbnspbrency.OPAQUE ? 32 : 24;
        }

        if (depth == 24) {
            cm = new DirectColorModel(depth,
                                      0x00FF0000, 0x0000FF00, 0x000000FF);
        } else {
            cm = new DirectColorModel(depth, 0x00FF0000, 0x0000FF00,
                                      0x000000FF, 0xFF000000);
        }

        return new XRPixmbpSurfbceDbtb
            (gc, width, height, imbge, getSurfbceType(gc, trbnspbrency),
             cm, drbwbble, trbnspbrency,
             XRUtils.getPictureFormbtForTrbnspbrency(trbnspbrency), depth);
    }

    protected XRSurfbceDbtb(X11ComponentPeer peer, XRGrbphicsConfig gc,
        SurfbceType sType, ColorModel cm, int depth, int trbnspbrency)
    {
        super(sType, cm);
        this.peer = peer;
        this.grbphicsConfig = gc;
        this.solidloops = grbphicsConfig.getSolidLoops(sType);
        this.depth = depth;
        initOps(peer, grbphicsConfig, depth);

        setBlitProxyKey(gc.getProxyKey());
    }

    protected XRSurfbceDbtb(XRBbckend renderQueue) {
        super(XRSurfbceDbtb.IntRgbX11,
              new DirectColorModel(24, 0x00FF0000, 0x0000FF00, 0x000000FF));
        this.renderQueue = renderQueue;
    }

    /**
     * Inits the XRender-dbtb-structures which belong to the XRSurfbceDbtb.
     *
     * @pbrbm pictureFormbt
     */
    public void initXRender(int pictureFormbt) {
        try {
            SunToolkit.bwtLock();
            initXRPicture(getNbtiveOps(), pictureFormbt);
            renderQueue = XRCompositeMbnbger.getInstbnce(this).getBbckend();
            mbskBuffer = XRCompositeMbnbger.getInstbnce(this);
        } cbtch (Throwbble ex) {
            ex.printStbckTrbce();
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public stbtic XRGrbphicsConfig getGC(X11ComponentPeer peer) {
        if (peer != null) {
            return (XRGrbphicsConfig) peer.getGrbphicsConfigurbtion();
        } else {
            GrbphicsEnvironment env =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            GrbphicsDevice gd = env.getDefbultScreenDevice();
            return (XRGrbphicsConfig) gd.getDefbultConfigurbtion();
        }
    }

    /**
     * Returns b boolebn indicbting whether or not b copyAreb from the given
     * rectbngle source coordinbtes might be incomplete bnd result in X11
     * GrbphicsExposure events being generbted from XCopyAreb. This method
     * bllows the SurfbceDbtb copyAreb method to determine if it needs to set
     * the GrbphicsExposures bttribute of the X11 GC to True or Fblse to receive
     * or bvoid the events.
     *
     * @return true if there is bny chbnce thbt bn XCopyAreb from the given
     *         source coordinbtes could produce bny X11 Exposure events.
     */
    public bbstrbct boolebn cbnSourceSendExposures(int x, int y, int w, int h);

    /**
     * CopyAreb is implemented using the "old" X11 GC, therefor clip bnd
     * needExposures hbve to be vblidbted bgbinst thbt GC. Pictures bnd GCs
     * don't shbre stbte.
     */
    public void vblidbteCopyArebGC(Region gcClip, boolebn needExposures) {
        if (vblidbtedGCClip != gcClip) {
            if (gcClip != null)
                renderQueue.setGCClipRectbngles(xgc, gcClip);
            vblidbtedGCClip = gcClip;
        }

        if (vblidbtedExposures != needExposures) {
            vblidbtedExposures = needExposures;
            renderQueue.setGCExposures(xgc, needExposures);
        }

        if (vblidbtedXorComp != null) {
            renderQueue.setGCMode(xgc, true);
            renderQueue.setGCForeground(xgc, vblidbtedGCForegroundPixel);
            vblidbtedXorComp = null;
        }
    }

    public boolebn copyAreb(SunGrbphics2D sg2d, int x, int y, int w, int h,
                            int dx, int dy) {
        if (xrpipe == null) {
            if (!isXRDrbwbbleVblid()) {
                return true;
            }
            mbkePipes();
        }
        CompositeType comptype = sg2d.imbgeComp;
        if (sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE &&
            (CompositeType.SrcOverNoEb.equbls(comptype) ||
             CompositeType.SrcNoEb.equbls(comptype)))
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;
            try {
                SunToolkit.bwtLock();
                boolebn needExposures = cbnSourceSendExposures(x, y, w, h);
                vblidbteCopyArebGC(sg2d.getCompClip(), needExposures);
                renderQueue.copyAreb(xid, xid, xgc, x, y, w, h, x + dx, y + dy);
            } finblly {
                SunToolkit.bwtUnlock();
            }
            return true;
        }
        return fblse;
    }

    /**
     * Returns the XRender SurfbceType which is bble to fullfill the specified
     * trbnspbrency requirement.
     */
    public stbtic SurfbceType getSurfbceType(XRGrbphicsConfig gc,
                                             int trbnspbrency) {
        SurfbceType sType = null;

        switch (trbnspbrency) {
        cbse Trbnspbrency.OPAQUE:
            sType = XRSurfbceDbtb.IntRgbX11;
            brebk;

        cbse Trbnspbrency.BITMASK:
        cbse Trbnspbrency.TRANSLUCENT:
            sType = XRSurfbceDbtb.IntArgbPreX11;
            brebk;
        }

        return sType;
    }

    public void invblidbte() {
        if (isVblid()) {
            setInvblid();
            super.invblidbte();
        }
    }

    privbte long xgc; // GC is still used for copyAreb
    privbte int vblidbtedGCForegroundPixel = 0;
    privbte XORComposite vblidbtedXorComp;
    privbte int xid;
    public int picture;
    public XRCompositeMbnbger mbskBuffer;

    privbte Region vblidbtedClip;
    privbte Region vblidbtedGCClip;
    privbte boolebn vblidbtedExposures = true;

    boolebn trbnsformInUse = fblse;
    AffineTrbnsform vblidbtedSourceTrbnsform = new AffineTrbnsform();
    AffineTrbnsform stbticSrcTx = null;
    int vblidbtedRepebt = XRUtils.RepebtNone;
    int vblidbtedFilter = XRUtils.FAST;

    /**
     * Vblidbtes bn XRSurfbceDbtb when used bs source. Note thbt the clip is
     * bpplied when used bs source bs well bs destinbtion.
     */
    void vblidbteAsSource(AffineTrbnsform sxForm, int repebt, int filter) {

        if (vblidbtedClip != null) {
            vblidbtedClip = null;
            renderQueue.setClipRectbngles(picture, null);
        }

        if (vblidbtedRepebt != repebt && repebt != -1) {
            vblidbtedRepebt = repebt;
            renderQueue.setPictureRepebt(picture, repebt);
        }

        if (sxForm == null) {
            if (trbnsformInUse) {
                vblidbtedSourceTrbnsform.setToIdentity();
                renderQueue.setPictureTrbnsform(picture,
                                                vblidbtedSourceTrbnsform);
                trbnsformInUse = fblse;
            }
        } else if (!trbnsformInUse ||
                   (trbnsformInUse && !sxForm.equbls(vblidbtedSourceTrbnsform))) {

            vblidbtedSourceTrbnsform.setTrbnsform(sxForm.getScbleX(),
                                                  sxForm.getShebrY(),
                                                  sxForm.getShebrX(),
                                                  sxForm.getScbleY(),
                                                  sxForm.getTrbnslbteX(),
                                                  sxForm.getTrbnslbteY());

            AffineTrbnsform srcTrbnsform = vblidbtedSourceTrbnsform;
            if(stbticSrcTx != null) {
                // Apply stbtic trbnsform set when used bs texture or grbdient.
                // Crebte b copy to not modify vblidbtedSourceTrbnsform bs
                // this would confuse the vblidbtion logic.
                srcTrbnsform = new AffineTrbnsform(vblidbtedSourceTrbnsform);
                srcTrbnsform.preConcbtenbte(stbticSrcTx);
            }

            renderQueue.setPictureTrbnsform(picture, srcTrbnsform);
            trbnsformInUse = true;
        }

        if (filter != vblidbtedFilter && filter != -1) {
            renderQueue.setFilter(picture, filter);
            vblidbtedFilter = filter;
        }
    }

    /**
     * Vblidbtes the Surfbce when used bs destinbtion.
     */
    public void vblidbteAsDestinbtion(SunGrbphics2D sg2d, Region clip) {
        if (!isVblid()) {
            throw new InvblidPipeException("bounds chbnged");
        }

        boolebn updbteGCClip = fblse;
        if (clip != vblidbtedClip) {
            renderQueue.setClipRectbngles(picture, clip);
            vblidbtedClip = clip;
            updbteGCClip = true;
        }

        if (sg2d != null && sg2d.compositeStbte == SunGrbphics2D.COMP_XOR) {
            if (vblidbtedXorComp != sg2d.getComposite()) {
                vblidbtedXorComp = (XORComposite) sg2d.getComposite();
                renderQueue.setGCMode(xgc, fblse);
            }

            // vblidbte pixel
            int pixel = sg2d.pixel;
            if (vblidbtedGCForegroundPixel != pixel) {
                int xorpixelmod = vblidbtedXorComp.getXorPixel();
                renderQueue.setGCForeground(xgc, pixel ^ xorpixelmod);
                vblidbtedGCForegroundPixel = pixel;
            }

            if (updbteGCClip) {
                renderQueue.setGCClipRectbngles(xgc, clip);
            }
        }
    }

    public synchronized void mbkePipes() { /*
                                            * TODO: Why is this synchronized,
                                            * but bccess not?
                                            */
        if (xrpipe == null) {
            try {
                SunToolkit.bwtLock();
                xgc = XCrebteGC(getNbtiveOps());

                xrpipe = new XRRenderer(mbskBuffer.getMbskBuffer());
                xrtxpipe = new PixelToShbpeConverter(xrpipe);
                xrtextpipe = mbskBuffer.getTextRenderer();
                xrDrbwImbge = new XRDrbwImbge();

                if (JulesPbthBuf.isCbiroAvbilbble()) {
                    bbShbpePipe =
                       new JulesShbpePipe(XRCompositeMbnbger.getInstbnce(this));
                    bbPixelToShbpeConv = new PixelToShbpeConverter(bbShbpePipe);
                }
            } finblly {
                SunToolkit.bwtUnlock();
            }
        }
    }

    public stbtic clbss XRWindowSurfbceDbtb extends XRSurfbceDbtb {
        public XRWindowSurfbceDbtb(X11ComponentPeer peer,
                                   XRGrbphicsConfig gc, SurfbceType sType) {
            super(peer, gc, sType, peer.getColorModel(),
                  peer.getColorModel().getPixelSize(), Trbnspbrency.OPAQUE);

            if (isXRDrbwbbleVblid()) {
                initXRender(XRUtils.
                    getPictureFormbtForTrbnspbrency(Trbnspbrency.OPAQUE));
                mbkePipes();
            }
        }

        public SurfbceDbtb getReplbcement() {
            return peer.getSurfbceDbtb();
        }

        public Rectbngle getBounds() {
            Rectbngle r = peer.getBounds();
            r.x = r.y = 0;
            return r;
        }

        @Override
        public boolebn cbnSourceSendExposures(int x, int y, int w, int h) {
            return true;
        }

        /**
         * Returns destinbtion Component bssocibted with this SurfbceDbtb.
         */
        public Object getDestinbtion() {
            return peer.getTbrget();
        }

       public void invblidbte() {
           try {
               SunToolkit.bwtLock();
               freeXSDOPicture(getNbtiveOps());
           }finblly {
               SunToolkit.bwtUnlock();
           }

           super.invblidbte();
       }
    }

    public stbtic clbss XRInternblSurfbceDbtb extends XRSurfbceDbtb {
        public XRInternblSurfbceDbtb(XRBbckend renderQueue, int pictXid) {
          super(renderQueue);
          this.picture = pictXid;
          this.trbnsformInUse = fblse;
        }

        public boolebn cbnSourceSendExposures(int x, int y, int w, int h) {
            return fblse;
        }

        public Rectbngle getBounds() {
            return null;
        }

        public Object getDestinbtion() {
            return null;
        }

        public SurfbceDbtb getReplbcement() {
            return null;
        }
    }

    public stbtic clbss XRPixmbpSurfbceDbtb extends XRSurfbceDbtb {
        Imbge offscreenImbge;
        int width;
        int height;
        int trbnspbrency;

        public XRPixmbpSurfbceDbtb(XRGrbphicsConfig gc, int width, int height,
                                   Imbge imbge, SurfbceType sType,
                                   ColorModel cm, long drbwbble,
                                   int trbnspbrency, int pictFormbt,
                                   int depth) {
            super(null, gc, sType, cm, depth, trbnspbrency);
            this.width = width;
            this.height = height;
            offscreenImbge = imbge;
            this.trbnspbrency = trbnspbrency;
            initSurfbce(depth, width, height, drbwbble, pictFormbt);

            initXRender(pictFormbt);
            mbkePipes();
        }

        public void initSurfbce(int depth, int width, int height,
                                long drbwbble, int pictFormbt) {
            try {
                SunToolkit.bwtLock();
                XRInitSurfbce(depth, width, height, drbwbble, pictFormbt);
            } finblly {
                SunToolkit.bwtUnlock();
            }
        }

        public SurfbceDbtb getReplbcement() {
            return restoreContents(offscreenImbge);
        }

        /**
         * Need this since the surfbce dbtb is crebted with the color model of
         * the tbrget GC, which is blwbys opbque. But in SunGrbphics2D.blitSD we
         * choose loops bbsed on the trbnspbrency on the source SD, so it could
         * choose wrong loop (blit instebd of blitbg, for exbmple).
         */
        public int getTrbnspbrency() {
            return trbnspbrency;
        }

        public Rectbngle getBounds() {
            return new Rectbngle(width, height);
        }

        @Override
        public boolebn cbnSourceSendExposures(int x, int y, int w, int h) {
            return (x < 0 || y < 0 || (x + w) > width || (y + h) > height);
        }

        public void flush() {
            /*
             * We need to invblidbte the surfbce before disposing the nbtive
             * Drbwbble bnd Picture. This wby if bn bpplicbtion tries to render
             * to bn blrebdy flushed XRSurfbceDbtb, we will notice in the
             * vblidbte() method bbove thbt it hbs been invblidbted, bnd we will
             * bvoid using those nbtive resources thbt hbve blrebdy been
             * disposed.
             */
            invblidbte();
            flushNbtiveSurfbce();
        }

        /**
         * Returns destinbtion Imbge bssocibted with this SurfbceDbtb.
         */
        public Object getDestinbtion() {
            return offscreenImbge;
        }
    }

    public long getGC() {
        return xgc;
    }

    public stbtic clbss LbzyPipe extends VblidbtePipe {
        public boolebn vblidbte(SunGrbphics2D sg2d) {
            XRSurfbceDbtb xsd = (XRSurfbceDbtb) sg2d.surfbceDbtb;
            if (!xsd.isXRDrbwbbleVblid()) {
                return fblse;
            }
            xsd.mbkePipes();
            return super.vblidbte(sg2d);
        }
    }

    public int getPicture() {
        return picture;
    }

    public int getXid() {
        return xid;
    }

    public XRGrbphicsConfig getGrbphicsConfig() {
        return grbphicsConfig;
    }

    public void setStbticSrcTx(AffineTrbnsform stbticSrcTx) {
        this.stbticSrcTx = stbticSrcTx;
    }
}
