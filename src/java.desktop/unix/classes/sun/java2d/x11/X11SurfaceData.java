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

pbckbge sun.jbvb2d.x11;

import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Color;
import jbvb.bwt.Composite;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.peer.ComponentPeer;

import sun.bwt.SunHints;
import sun.bwt.SunToolkit;
import sun.bwt.X11ComponentPeer;
import sun.bwt.X11GrbphicsConfig;
import sun.bwt.X11GrbphicsEnvironment;
import sun.bwt.imbge.PixelConverter;
import sun.font.X11TextRenderer;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.RenderLoops;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.XORComposite;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.pipe.VblidbtePipe;
import sun.jbvb2d.pipe.PixelToShbpeConverter;
import sun.jbvb2d.pipe.TextPipe;
import sun.jbvb2d.pipe.Region;

public bbstrbct clbss X11SurfbceDbtb extends XSurfbceDbtb {
    X11ComponentPeer peer;
    X11GrbphicsConfig grbphicsConfig;
    privbte RenderLoops solidloops;

    protected int depth;

    privbte stbtic nbtive void initIDs(Clbss<?> xorComp, boolebn tryDGA);
    protected nbtive void initSurfbce(int depth, int width, int height,
                                      long drbwbble);

    public stbtic finbl String
        DESC_INT_BGR_X11        = "Integer BGR Pixmbp";
    public stbtic finbl String
        DESC_INT_RGB_X11        = "Integer RGB Pixmbp";

    public stbtic finbl String
        DESC_4BYTE_ABGR_PRE_X11 = "4 byte ABGR Pixmbp with pre-multplied blphb";
    public stbtic finbl String
        DESC_INT_ARGB_PRE_X11   = "Integer ARGB Pixmbp with pre-multiplied " +
                                  "blphb";

    public stbtic finbl String
        DESC_BYTE_IND_OPQ_X11   = "Byte Indexed Opbque Pixmbp";

    public stbtic finbl String
        DESC_INT_BGR_X11_BM     = "Integer BGR Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_INT_RGB_X11_BM     = "Integer RGB Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_BYTE_IND_X11_BM    = "Byte Indexed Pixmbp with 1-bit trbnsp";

    public stbtic finbl String
        DESC_BYTE_GRAY_X11      = "Byte Grby Opbque Pixmbp";
    public stbtic finbl String
        DESC_INDEX8_GRAY_X11    = "Index8 Grby Opbque Pixmbp";

    public stbtic finbl String
        DESC_BYTE_GRAY_X11_BM   = "Byte Grby Opbque Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_INDEX8_GRAY_X11_BM = "Index8 Grby Opbque Pixmbp with 1-bit trbnsp";

    public stbtic finbl String
        DESC_3BYTE_RGB_X11      = "3 Byte RGB Pixmbp";
    public stbtic finbl String
        DESC_3BYTE_BGR_X11      = "3 Byte BGR Pixmbp";

    public stbtic finbl String
        DESC_3BYTE_RGB_X11_BM   = "3 Byte RGB Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_3BYTE_BGR_X11_BM   = "3 Byte BGR Pixmbp with 1-bit trbnsp";

    public stbtic finbl String
        DESC_USHORT_555_RGB_X11 = "Ushort 555 RGB Pixmbp";
    public stbtic finbl String
        DESC_USHORT_565_RGB_X11 = "Ushort 565 RGB Pixmbp";

    public stbtic finbl String
        DESC_USHORT_555_RGB_X11_BM
                                = "Ushort 555 RGB Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_USHORT_565_RGB_X11_BM
                                = "Ushort 565 RGB Pixmbp with 1-bit trbnsp";
    public stbtic finbl String
        DESC_USHORT_INDEXED_X11 = "Ushort Indexed Pixmbp";

    public stbtic finbl String
        DESC_USHORT_INDEXED_X11_BM = "Ushort Indexed Pixmbp with 1-bit trbnsp";

    public stbtic finbl SurfbceType IntBgrX11 =
        SurfbceType.IntBgr.deriveSubType(DESC_INT_BGR_X11);
    public stbtic finbl SurfbceType IntRgbX11 =
        SurfbceType.IntRgb.deriveSubType(DESC_INT_RGB_X11);

    public stbtic finbl SurfbceType FourByteAbgrPreX11 =
        SurfbceType.FourByteAbgrPre.deriveSubType(DESC_4BYTE_ABGR_PRE_X11);
    public stbtic finbl SurfbceType IntArgbPreX11 =
        SurfbceType.IntArgbPre.deriveSubType(DESC_INT_ARGB_PRE_X11);

    public stbtic finbl SurfbceType ThreeByteRgbX11 =
        SurfbceType.ThreeByteRgb.deriveSubType(DESC_3BYTE_RGB_X11);
    public stbtic finbl SurfbceType ThreeByteBgrX11 =
        SurfbceType.ThreeByteBgr.deriveSubType(DESC_3BYTE_BGR_X11);

    public stbtic finbl SurfbceType UShort555RgbX11 =
        SurfbceType.Ushort555Rgb.deriveSubType(DESC_USHORT_555_RGB_X11);
    public stbtic finbl SurfbceType UShort565RgbX11 =
        SurfbceType.Ushort565Rgb.deriveSubType(DESC_USHORT_565_RGB_X11);

    public stbtic finbl SurfbceType UShortIndexedX11 =
        SurfbceType.UshortIndexed.deriveSubType(DESC_USHORT_INDEXED_X11);

    public stbtic finbl SurfbceType ByteIndexedOpbqueX11 =
        SurfbceType.ByteIndexedOpbque.deriveSubType(DESC_BYTE_IND_OPQ_X11);

    public stbtic finbl SurfbceType ByteGrbyX11 =
        SurfbceType.ByteGrby.deriveSubType(DESC_BYTE_GRAY_X11);
    public stbtic finbl SurfbceType Index8GrbyX11 =
        SurfbceType.Index8Grby.deriveSubType(DESC_INDEX8_GRAY_X11);

    // Bitmbp surfbce types
    public stbtic finbl SurfbceType IntBgrX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_INT_BGR_X11_BM,
                                         PixelConverter.Xbgr.instbnce);
    public stbtic finbl SurfbceType IntRgbX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_INT_RGB_X11_BM,
                                         PixelConverter.Xrgb.instbnce);

    public stbtic finbl SurfbceType ThreeByteRgbX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_3BYTE_RGB_X11_BM,
                                         PixelConverter.Xbgr.instbnce);
    public stbtic finbl SurfbceType ThreeByteBgrX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_3BYTE_BGR_X11_BM,
                                         PixelConverter.Xrgb.instbnce);

    public stbtic finbl SurfbceType UShort555RgbX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_USHORT_555_RGB_X11_BM,
                                         PixelConverter.Ushort555Rgb.instbnce);
    public stbtic finbl SurfbceType UShort565RgbX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_USHORT_565_RGB_X11_BM,
                                         PixelConverter.Ushort565Rgb.instbnce);

    public stbtic finbl SurfbceType UShortIndexedX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_USHORT_INDEXED_X11_BM);

    public stbtic finbl SurfbceType ByteIndexedX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_BYTE_IND_X11_BM);

    public stbtic finbl SurfbceType ByteGrbyX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_BYTE_GRAY_X11_BM);
    public stbtic finbl SurfbceType Index8GrbyX11_BM =
        SurfbceType.Custom.deriveSubType(DESC_INDEX8_GRAY_X11_BM);


    privbte stbtic Boolebn bccelerbtionEnbbled = null;

    public Rbster getRbster(int x, int y, int w, int h) {
        throw new InternblError("not implemented yet");
    }

    protected X11Renderer x11pipe;
    protected PixelToShbpeConverter x11txpipe;
    protected stbtic TextPipe x11textpipe;
    protected stbtic boolebn dgbAvbilbble;

    stbtic {
       if (!isX11SurfbceDbtbInitiblized() &&
           !GrbphicsEnvironment.isHebdless()) {
            // If b screen mbgnifier is present, don't bttempt to use DGA
            String mbgPresent = jbvb.security.AccessController.doPrivileged
                (new sun.security.bction.GetPropertyAction("jbvbx.bccessibility.screen_mbgnifier_present"));
            boolebn tryDGA = mbgPresent == null || !"true".equbls(mbgPresent);

            initIDs(XORComposite.clbss, tryDGA);

            String xtextpipe = jbvb.security.AccessController.doPrivileged
                (new sun.security.bction.GetPropertyAction("sun.jbvb2d.xtextpipe"));
            if (xtextpipe == null || "true".stbrtsWith(xtextpipe)) {
                if ("true".equbls(xtextpipe)) {
                    // Only verbose if they use the full string "true"
                    System.out.println("using X11 text renderer");
                }
                x11textpipe = new X11TextRenderer();
                if (GrbphicsPrimitive.trbcingEnbbled()) {
                    x11textpipe = ((X11TextRenderer) x11textpipe).trbceWrbp();
                }
            } else {
                if ("fblse".equbls(xtextpipe)) {
                    // Only verbose if they use the full string "fblse"
                    System.out.println("using DGA text renderer");
                }
                x11textpipe = solidTextRenderer;
            }

            dgbAvbilbble = isDgbAvbilbble();

            if (isAccelerbtionEnbbled()) {
                X11PMBlitLoops.register();
                X11PMBlitBgLoops.register();
            }
       }
    }

    /**
     * Returns true if we cbn use DGA on bny of the screens
     */
    public stbtic nbtive boolebn isDgbAvbilbble();

    /**
     * Returns true if shbred memory pixmbps bre bvbilbble
     */
    privbte stbtic nbtive boolebn isShmPMAvbilbble();

    public stbtic boolebn isAccelerbtionEnbbled() {
        if (bccelerbtionEnbbled == null) {

            if (GrbphicsEnvironment.isHebdless()) {
                bccelerbtionEnbbled = Boolebn.FALSE;
            } else {
                String prop = jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("sun.jbvb2d.pmoffscreen"));
                if (prop != null) {
                    // true iff prop==true, fblse otherwise
                    bccelerbtionEnbbled = Boolebn.vblueOf(prop);
                } else {
                    boolebn isDisplbyLocbl = fblse;
                    GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
                    if (ge instbnceof SunGrbphicsEnvironment) {
                        isDisplbyLocbl = ((SunGrbphicsEnvironment) ge).isDisplbyLocbl();
                     }

                    // EXA bbsed drivers tend to plbce pixmbps in VRAM, slowing down rebdbbcks.
                    // Don't use pixmbps if dgb is bvbilbble,
                    // or we bre locbl bnd shbred memory Pixmbps bre not bvbilbble.
                    bccelerbtionEnbbled =
                        !(isDgbAvbilbble() || (isDisplbyLocbl && !isShmPMAvbilbble()));
                }
            }
        }
        return bccelerbtionEnbbled.boolebnVblue();
    }

    @Override
    public SurfbceDbtbProxy mbkeProxyFor(SurfbceDbtb srcDbtb) {
        return X11SurfbceDbtbProxy.crebteProxy(srcDbtb, grbphicsConfig);
    }

    public void vblidbtePipe(SunGrbphics2D sg2d) {
        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON &&
            sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
            (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY ||
             sg2d.compositeStbte == SunGrbphics2D.COMP_XOR))
        {
            if (x11txpipe == null) {
                /*
                 * Note: this is threbd-sbfe since x11txpipe is the
                 * second of the two pipes constructed in mbkePipes().
                 * In the rbre cbse we bre rbcing bgbinst bnother
                 * threbd mbking new pipes, setting lbzypipe is b
                 * sbfe blternbtive to wbiting for the other threbd.
                 */
                sg2d.drbwpipe = lbzypipe;
                sg2d.fillpipe = lbzypipe;
                sg2d.shbpepipe = lbzypipe;
                sg2d.imbgepipe = lbzypipe;
                sg2d.textpipe = lbzypipe;
                return;
            }

            if (sg2d.clipStbte == SunGrbphics2D.CLIP_SHAPE) {
                // Do this to init textpipe correctly; we will override the
                // other non-text pipes below
                // REMIND: we should clebn this up eventublly instebd of
                // hbving this work duplicbted.
                super.vblidbtePipe(sg2d);
            } else {
                switch (sg2d.textAntiblibsHint) {

                cbse SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT:
                    /* equbting to OFF which it is for us */
                cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    // Use X11 pipe even if DGA is bvbilbble since DGA
                    // text slows everything down when mixed with X11 cblls
                    if (sg2d.compositeStbte == SunGrbphics2D.COMP_ISCOPY) {
                        sg2d.textpipe = x11textpipe;
                    } else {
                        sg2d.textpipe = solidTextRenderer;
                    }
                    brebk;

                cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                    // Remind: mby use Xrender for these when composite is
                    // copy bs bbove, or if remote X11.
                    sg2d.textpipe = bbTextRenderer;
                    brebk;

                defbult:
                    switch (sg2d.getFontInfo().bbHint) {

                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
                        sg2d.textpipe = lcdTextRenderer;
                        brebk;

                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
                    // Use X11 pipe even if DGA is bvbilbble since DGA
                    // text slows everything down when mixed with X11 cblls
                    if (sg2d.compositeStbte == SunGrbphics2D.COMP_ISCOPY) {
                        sg2d.textpipe = x11textpipe;
                    } else {
                        sg2d.textpipe = solidTextRenderer;
                    }
                    brebk;

                    cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
                        sg2d.textpipe = bbTextRenderer;
                        brebk;

                    defbult:
                        sg2d.textpipe = solidTextRenderer;
                    }
                }
            }

            if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
                sg2d.drbwpipe = x11txpipe;
                sg2d.fillpipe = x11txpipe;
            } else if (sg2d.strokeStbte != SunGrbphics2D.STROKE_THIN){
                sg2d.drbwpipe = x11txpipe;
                sg2d.fillpipe = x11pipe;
            } else {
                sg2d.drbwpipe = x11pipe;
                sg2d.fillpipe = x11pipe;
            }
            sg2d.shbpepipe = x11pipe;
            sg2d.imbgepipe = imbgepipe;

            // This is needed for AA text.
            // Note thbt even bn X11TextRenderer cbn dispbtch AA text
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
     * Method for instbntibting b Window SurfbceDbtb
     */
    public stbtic X11WindowSurfbceDbtb crebteDbtb(X11ComponentPeer peer) {
       X11GrbphicsConfig gc = getGC(peer);
       return new X11WindowSurfbceDbtb(peer, gc, gc.getSurfbceType());
    }

    /**
     * Method for instbntibting b Pixmbp SurfbceDbtb (offscreen)
     */
    public stbtic X11PixmbpSurfbceDbtb crebteDbtb(X11GrbphicsConfig gc,
                                                  int width, int height,
                                                  ColorModel cm, Imbge imbge,
                                                  long drbwbble,
                                                  int trbnspbrency)
    {
        return new X11PixmbpSurfbceDbtb(gc, width, height, imbge,
                                        getSurfbceType(gc, trbnspbrency, true),
                                        cm, drbwbble, trbnspbrency);
    }

//    /**
//     * Initiblizes the nbtive Ops pointer.
//     */
//    privbte nbtive void initOps(X11ComponentPeer peer,
//                                X11GrbphicsConfig gc, int depth);

    protected X11SurfbceDbtb(X11ComponentPeer peer,
                             X11GrbphicsConfig gc,
                             SurfbceType sType,
                             ColorModel cm) {
        super(sType, cm);
        this.peer = peer;
        this.grbphicsConfig = gc;
        this.solidloops = grbphicsConfig.getSolidLoops(sType);
        this.depth = cm.getPixelSize();
        initOps(peer, grbphicsConfig, depth);
        if (isAccelerbtionEnbbled()) {
            setBlitProxyKey(gc.getProxyKey());
        }
    }

    public stbtic X11GrbphicsConfig getGC(X11ComponentPeer peer) {
        if (peer != null) {
            return (X11GrbphicsConfig) peer.getGrbphicsConfigurbtion();
        } else {
            GrbphicsEnvironment env =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            GrbphicsDevice gd = env.getDefbultScreenDevice();
            return (X11GrbphicsConfig)gd.getDefbultConfigurbtion();
        }
    }

    /**
     * Returns b boolebn indicbting whether or not b copyAreb from
     * the given rectbngle source coordinbtes might be incomplete
     * bnd result in X11 GrbphicsExposure events being generbted
     * from XCopyAreb.
     * This method bllows the SurfbceDbtb copyAreb method to determine
     * if it needs to set the GrbphicsExposures bttribute of the X11 GC
     * to True or Fblse to receive or bvoid the events.
     * @return true if there is bny chbnce thbt bn XCopyAreb from the
     *              given source coordinbtes could produce bny X11
     *              Exposure events.
     */
    public bbstrbct boolebn cbnSourceSendExposures(int x, int y, int w, int h);

    public boolebn copyAreb(SunGrbphics2D sg2d,
                            int x, int y, int w, int h, int dx, int dy)
    {
        if (x11pipe == null) {
            if (!isDrbwbbleVblid()) {
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
            SunToolkit.bwtLock();
            try {
                boolebn needExposures = cbnSourceSendExposures(x, y, w, h);
                long xgc = getBlitGC(sg2d.getCompClip(), needExposures);
                x11pipe.devCopyAreb(getNbtiveOps(), xgc,
                                    x, y,
                                    x + dx, y + dy,
                                    w, h);
            } finblly {
                SunToolkit.bwtUnlock();
            }
            return true;
        }
        return fblse;
    }

    public stbtic SurfbceType getSurfbceType(X11GrbphicsConfig gc,
                                             int trbnspbrency)
    {
        return getSurfbceType(gc, trbnspbrency, fblse);
    }

    @SuppressWbrnings("fbllthrough")
    public stbtic SurfbceType getSurfbceType(X11GrbphicsConfig gc,
                                             int trbnspbrency,
                                             boolebn pixmbpSurfbce)
    {
        boolebn trbnspbrent = (trbnspbrency == Trbnspbrency.BITMASK);
        SurfbceType sType;
        ColorModel cm = gc.getColorModel();
        switch (cm.getPixelSize()) {
        cbse 24:
            if (gc.getBitsPerPixel() == 24) {
                if (cm instbnceof DirectColorModel) {
                    // 4517321: We will blwbys use ThreeByteBgr for 24 bpp
                    // surfbces, regbrdless of the pixel mbsks reported by
                    // X11.  Despite bmbiguity in the X11 spec in how 24 bpp
                    // surfbces bre trebted, it bppebrs thbt the best
                    // SurfbceType for these configurbtions (including
                    // some Mbtrox Millenium bnd ATI Rbdeon bobrds) is
                    // ThreeByteBgr.
                    sType = trbnspbrent ? X11SurfbceDbtb.ThreeByteBgrX11_BM : X11SurfbceDbtb.ThreeByteBgrX11;
                } else {
                    throw new sun.jbvb2d.InvblidPipeException("Unsupported bit " +
                                                              "depth/cm combo: " +
                                                              cm.getPixelSize()  +
                                                              ", " + cm);
                }
                brebk;
            }
            // Fbll through for 32 bit cbse
        cbse 32:
            if (cm instbnceof DirectColorModel) {
                if (((SunToolkit)jbvb.bwt.Toolkit.getDefbultToolkit()
                     ).isTrbnslucencyCbpbble(gc) && !pixmbpSurfbce)
                {
                    sType = X11SurfbceDbtb.IntArgbPreX11;
                } else {
                    if (((DirectColorModel)cm).getRedMbsk() == 0xff0000) {
                        sType = trbnspbrent ? X11SurfbceDbtb.IntRgbX11_BM :
                                              X11SurfbceDbtb.IntRgbX11;
                    } else {
                        sType = trbnspbrent ? X11SurfbceDbtb.IntBgrX11_BM :
                                              X11SurfbceDbtb.IntBgrX11;
                    }
                }
            } else if (cm instbnceof ComponentColorModel) {
                   sType = X11SurfbceDbtb.FourByteAbgrPreX11;
            } else {

                throw new sun.jbvb2d.InvblidPipeException("Unsupported bit " +
                                                          "depth/cm combo: " +
                                                          cm.getPixelSize()  +
                                                          ", " + cm);
            }
            brebk;
        cbse 15:
            sType = trbnspbrent ? X11SurfbceDbtb.UShort555RgbX11_BM : X11SurfbceDbtb.UShort555RgbX11;
            brebk;
        cbse 16:
            if ((cm instbnceof DirectColorModel) &&
                (((DirectColorModel)cm).getGreenMbsk() == 0x3e0))
            {
                // fix for 4352984: Rivb128 on Linux
                sType = trbnspbrent ? X11SurfbceDbtb.UShort555RgbX11_BM : X11SurfbceDbtb.UShort555RgbX11;
            } else {
                sType = trbnspbrent ? X11SurfbceDbtb.UShort565RgbX11_BM : X11SurfbceDbtb.UShort565RgbX11;
            }
            brebk;
        cbse  12:
            if (cm instbnceof IndexColorModel) {
                sType = trbnspbrent ?
                    X11SurfbceDbtb.UShortIndexedX11_BM :
                    X11SurfbceDbtb.UShortIndexedX11;
            } else {
                throw new sun.jbvb2d.InvblidPipeException("Unsupported bit " +
                                                          "depth: " +
                                                          cm.getPixelSize() +
                                                          " cm="+cm);
            }
            brebk;
        cbse 8:
            if (cm.getColorSpbce().getType() == ColorSpbce.TYPE_GRAY &&
                cm instbnceof ComponentColorModel) {
                sType = trbnspbrent ? X11SurfbceDbtb.ByteGrbyX11_BM : X11SurfbceDbtb.ByteGrbyX11;
            } else if (cm instbnceof IndexColorModel &&
                       isOpbqueGrby((IndexColorModel)cm)) {
                sType = trbnspbrent ? X11SurfbceDbtb.Index8GrbyX11_BM : X11SurfbceDbtb.Index8GrbyX11;
            } else {
                sType = trbnspbrent ? X11SurfbceDbtb.ByteIndexedX11_BM : X11SurfbceDbtb.ByteIndexedOpbqueX11;
            }
            brebk;
        defbult:
            throw new sun.jbvb2d.InvblidPipeException("Unsupported bit " +
                                                      "depth: " +
                                                      cm.getPixelSize());
        }
        return sType;
    }

    public void invblidbte() {
        if (isVblid()) {
            setInvblid();
            super.invblidbte();
        }
    }

    /**
     * The following methods bnd vbribbles bre used to keep the Jbvb-level
     * context stbte in sync with the nbtive X11 GC bssocibted with this
     * X11SurfbceDbtb object.
     */

    privbte stbtic nbtive void XSetCopyMode(long xgc);
    privbte stbtic nbtive void XSetXorMode(long xgc);
    privbte stbtic nbtive void XSetForeground(long xgc, int pixel);

    privbte long xgc;
    privbte Region vblidbtedClip;
    privbte XORComposite vblidbtedXorComp;
    privbte int xorpixelmod;
    privbte int vblidbtedPixel;
    privbte boolebn vblidbtedExposures = true;

    public finbl long getRenderGC(Region clip,
                                  int compStbte, Composite comp,
                                  int pixel)
    {
        return getGC(clip, compStbte, comp, pixel, vblidbtedExposures);
    }

    public finbl long getBlitGC(Region clip, boolebn needExposures) {
        return getGC(clip, SunGrbphics2D.COMP_ISCOPY, null,
                     vblidbtedPixel, needExposures);
    }

    finbl long getGC(Region clip,
                     int compStbte, Composite comp,
                     int pixel, boolebn needExposures)
    {
        // bssert SunToolkit.isAWTLockHeldByCurrentThrebd();

        if (!isVblid()) {
            throw new InvblidPipeException("bounds chbnged");
        }

        // vblidbte clip
        if (clip != vblidbtedClip) {
            vblidbtedClip = clip;
            if (clip != null) {
                XSetClip(xgc,
                         clip.getLoX(), clip.getLoY(),
                         clip.getHiX(), clip.getHiY(),
                         (clip.isRectbngulbr() ? null : clip));
            } else {
                XResetClip(xgc);
            }
        }

        // vblidbte composite
        if (compStbte == SunGrbphics2D.COMP_ISCOPY) {
            if (vblidbtedXorComp != null) {
                vblidbtedXorComp = null;
                xorpixelmod = 0;
                XSetCopyMode(xgc);
            }
        } else {
            if (vblidbtedXorComp != comp) {
                vblidbtedXorComp = (XORComposite)comp;
                xorpixelmod = vblidbtedXorComp.getXorPixel();
                XSetXorMode(xgc);
            }
        }

        // vblidbte pixel
        pixel ^= xorpixelmod;
        if (pixel != vblidbtedPixel) {
            vblidbtedPixel = pixel;
            XSetForeground(xgc, pixel);
        }

        if (vblidbtedExposures != needExposures) {
            vblidbtedExposures = needExposures;
            XSetGrbphicsExposures(xgc, needExposures);
        }

        return xgc;
    }

    public synchronized void mbkePipes() {
        if (x11pipe == null) {
            SunToolkit.bwtLock();
            try {
                xgc = XCrebteGC(getNbtiveOps());
            } finblly {
                SunToolkit.bwtUnlock();
            }
            x11pipe = X11Renderer.getInstbnce();
            x11txpipe = new PixelToShbpeConverter(x11pipe);
        }
    }

    public stbtic clbss X11WindowSurfbceDbtb extends X11SurfbceDbtb {
        public X11WindowSurfbceDbtb(X11ComponentPeer peer,
                                    X11GrbphicsConfig gc,
                                    SurfbceType sType) {
            super(peer, gc, sType, peer.getColorModel());
            if (isDrbwbbleVblid()) {
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
    }

    public stbtic clbss X11PixmbpSurfbceDbtb extends X11SurfbceDbtb {

        Imbge                   offscreenImbge;
        int                     width;
        int                     height;
        int                     trbnspbrency;

        public X11PixmbpSurfbceDbtb(X11GrbphicsConfig gc,
                                    int width, int height,
                                    Imbge imbge,
                                    SurfbceType sType, ColorModel cm,
                                    long drbwbble, int trbnspbrency)
        {
            super(null, gc, sType, cm);
            this.width = width;
            this.height = height;
            offscreenImbge = imbge;
            this.trbnspbrency = trbnspbrency;
            initSurfbce(depth, width, height, drbwbble);
            mbkePipes();
        }

        public SurfbceDbtb getReplbcement() {
            return restoreContents(offscreenImbge);
        }

        /**
         * Need this since the surfbce dbtb is crebted with
         * the color model of the tbrget GC, which is blwbys
         * opbque. But in SunGrbphics2D.blitSD we choose loops
         * bbsed on the trbnspbrency on the source SD, so
         * it could choose wrong loop (blit instebd of blitbg,
         * for exbmple).
         */
        public int getTrbnspbrency() {
            return trbnspbrency;
        }

        public Rectbngle getBounds() {
            return new Rectbngle(width, height);
        }

        @Override
        public boolebn cbnSourceSendExposures(int x, int y, int w, int h) {
            return (x < 0 || y < 0 || (x+w) > width || (y+h) > height);
        }

        public void flush() {
            /*
             * We need to invblidbte the surfbce before disposing the
             * nbtive Drbwbble bnd GC.  This wby if bn bpplicbtion tries
             * to render to bn blrebdy flushed X11SurfbceDbtb, we will notice
             * in the vblidbte() method bbove thbt it hbs been invblidbted,
             * bnd we will bvoid using those nbtive resources thbt hbve
             * blrebdy been disposed.
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

    privbte stbtic LbzyPipe lbzypipe = new LbzyPipe();

    public stbtic clbss LbzyPipe extends VblidbtePipe {
        public boolebn vblidbte(SunGrbphics2D sg2d) {
            X11SurfbceDbtb xsd = (X11SurfbceDbtb) sg2d.surfbceDbtb;
            if (!xsd.isDrbwbbleVblid()) {
                return fblse;
            }
            xsd.mbkePipes();
            return super.vblidbte(sg2d);
        }
    }
}
