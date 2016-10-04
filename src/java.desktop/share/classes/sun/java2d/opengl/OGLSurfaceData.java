/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.Rbster;
import sun.bwt.SunHints;
import sun.bwt.imbge.PixelConverter;
import sun.jbvb2d.pipe.hw.AccelSurfbce;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SurfbceDbtbProxy;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.MbskFill;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.pipe.PbrbllelogrbmPipe;
import sun.jbvb2d.pipe.PixelToPbrbllelogrbmConverter;
import sun.jbvb2d.pipe.RenderBuffer;
import sun.jbvb2d.pipe.TextPipe;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;

/**
 * This clbss describes bn OpenGL "surfbce", thbt is, b region of pixels
 * mbnbged vib OpenGL.  An OGLSurfbceDbtb cbn be tbgged with one of three
 * different SurfbceType objects for the purpose of registering loops, etc.
 * This dibgrbm shows the hierbrchy of OGL SurfbceTypes:
 *
 *                               Any
 *                             /     \
 *                 OpenGLSurfbce     OpenGLTexture
 *                      |
 *               OpenGLSurfbceRTT
 *
 * OpenGLSurfbce
 * This kind of surfbce cbn be rendered to using OpenGL APIs.  It is blso
 * possible to copy bn OpenGLSurfbce to bnother OpenGLSurfbce (or to itself).
 * This is typicblly bccomplished by cblling MbkeContextCurrent(dstSD, srcSD)
 * bnd then cblling glCopyPixels() (blthough there bre other techniques to
 * bchieve the sbme gobl).
 *
 * OpenGLTexture
 * This kind of surfbce cbnnot be rendered to using OpenGL (in the sbme sense
 * bs in OpenGLSurfbce).  However, it is possible to uplobd b region of pixels
 * to bn OpenGLTexture object vib glTexSubImbge2D().  One cbn blso copy b
 * surfbce of type OpenGLTexture to bn OpenGLSurfbce by binding the texture
 * to b qubd bnd then rendering it to the destinbtion surfbce (this process
 * is known bs "texture mbpping").
 *
 * OpenGLSurfbceRTT
 * This kind of surfbce cbn be thought of bs b sort of hybrid between
 * OpenGLSurfbce bnd OpenGLTexture, in thbt one cbn render to this kind of
 * surfbce bs if it were of type OpenGLSurfbce, but the process of copying
 * this kind of surfbce to bnother is more like bn OpenGLTexture.  (Note thbt
 * "RTT" stbnds for "render-to-texture".)
 *
 * In bddition to these SurfbceType vbribnts, we hbve blso defined some
 * constbnts thbt describe in more detbil the type of underlying OpenGL
 * surfbce.  This tbble helps explbin the relbtionships between those
 * "type" constbnts bnd their corresponding SurfbceType:
 *
 * OGL Type          Corresponding SurfbceType
 * --------          -------------------------
 * WINDOW            OpenGLSurfbce
 * PBUFFER           OpenGLSurfbce
 * TEXTURE           OpenGLTexture
 * FLIP_BACKBUFFER   OpenGLSurfbce
 * FBOBJECT          OpenGLSurfbceRTT
 */
public bbstrbct clbss OGLSurfbceDbtb extends SurfbceDbtb
    implements AccelSurfbce {

    /**
     * OGL-specific surfbce types
     *
     * @see sun.jbvb2d.pipe.hw.AccelSurfbce
     */
    public stbtic finbl int PBUFFER         = RT_PLAIN;
    public stbtic finbl int FBOBJECT        = RT_TEXTURE;

    /**
     * Pixel formbts
     */
    public stbtic finbl int PF_INT_ARGB        = 0;
    public stbtic finbl int PF_INT_ARGB_PRE    = 1;
    public stbtic finbl int PF_INT_RGB         = 2;
    public stbtic finbl int PF_INT_RGBX        = 3;
    public stbtic finbl int PF_INT_BGR         = 4;
    public stbtic finbl int PF_INT_BGRX        = 5;
    public stbtic finbl int PF_USHORT_565_RGB  = 6;
    public stbtic finbl int PF_USHORT_555_RGB  = 7;
    public stbtic finbl int PF_USHORT_555_RGBX = 8;
    public stbtic finbl int PF_BYTE_GRAY       = 9;
    public stbtic finbl int PF_USHORT_GRAY     = 10;
    public stbtic finbl int PF_3BYTE_BGR       = 11;

    /**
     * SurfbceTypes
     */
    privbte stbtic finbl String DESC_OPENGL_SURFACE = "OpenGL Surfbce";
    privbte stbtic finbl String DESC_OPENGL_SURFACE_RTT =
        "OpenGL Surfbce (render-to-texture)";
    privbte stbtic finbl String DESC_OPENGL_TEXTURE = "OpenGL Texture";

    stbtic finbl SurfbceType OpenGLSurfbce =
        SurfbceType.Any.deriveSubType(DESC_OPENGL_SURFACE,
                                      PixelConverter.ArgbPre.instbnce);
    stbtic finbl SurfbceType OpenGLSurfbceRTT =
        OpenGLSurfbce.deriveSubType(DESC_OPENGL_SURFACE_RTT);
    stbtic finbl SurfbceType OpenGLTexture =
        SurfbceType.Any.deriveSubType(DESC_OPENGL_TEXTURE);

    /** This will be true if the fbobject system property hbs been enbbled. */
    privbte stbtic boolebn isFBObjectEnbbled;

    /** This will be true if the lcdshbder system property hbs been enbbled.*/
    privbte stbtic boolebn isLCDShbderEnbbled;

    /** This will be true if the biopshbder system property hbs been enbbled.*/
    privbte stbtic boolebn isBIOpShbderEnbbled;

    /** This will be true if the grbdshbder system property hbs been enbbled.*/
    privbte stbtic boolebn isGrbdShbderEnbbled;

    privbte OGLGrbphicsConfig grbphicsConfig;
    protected int type;
    // these fields bre set from the nbtive code when the surfbce is
    // initiblized
    privbte int nbtiveWidth, nbtiveHeight;

    protected stbtic OGLRenderer oglRenderPipe;
    protected stbtic PixelToPbrbllelogrbmConverter oglTxRenderPipe;
    protected stbtic PbrbllelogrbmPipe oglAAPgrbmPipe;
    protected stbtic OGLTextRenderer oglTextPipe;
    protected stbtic OGLDrbwImbge oglImbgePipe;

    protected nbtive boolebn initTexture(long pDbtb,
                                         boolebn isOpbque, boolebn texNonPow2,
                                         boolebn texRect,
                                         int width, int height);
    protected nbtive boolebn initFBObject(long pDbtb,
                                          boolebn isOpbque, boolebn texNonPow2,
                                          boolebn texRect,
                                          int width, int height);
    protected nbtive boolebn initFlipBbckbuffer(long pDbtb);
    protected bbstrbct boolebn initPbuffer(long pDbtb, long pConfigInfo,
                                           boolebn isOpbque,
                                           int width, int height);

    privbte nbtive int getTextureTbrget(long pDbtb);
    privbte nbtive int getTextureID(long pDbtb);

    stbtic {
        if (!GrbphicsEnvironment.isHebdless()) {
            // fbobject currently enbbled by defbult; use "fblse" to disbble
            String fbo = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "sun.jbvb2d.opengl.fbobject"));
            isFBObjectEnbbled = !"fblse".equbls(fbo);

            // lcdshbder currently enbbled by defbult; use "fblse" to disbble
            String lcd = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "sun.jbvb2d.opengl.lcdshbder"));
            isLCDShbderEnbbled = !"fblse".equbls(lcd);

            // biopshbder currently enbbled by defbult; use "fblse" to disbble
            String biop = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "sun.jbvb2d.opengl.biopshbder"));
            isBIOpShbderEnbbled = !"fblse".equbls(biop);

            // grbdshbder currently enbbled by defbult; use "fblse" to disbble
            String grbd = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "sun.jbvb2d.opengl.grbdshbder"));
            isGrbdShbderEnbbled = !"fblse".equbls(grbd);

            OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
            oglImbgePipe = new OGLDrbwImbge();
            oglTextPipe = new OGLTextRenderer(rq);
            oglRenderPipe = new OGLRenderer(rq);
            if (GrbphicsPrimitive.trbcingEnbbled()) {
                oglTextPipe = oglTextPipe.trbceWrbp();
                //The wrbpped oglRenderPipe will wrbp the AA pipe bs well...
                //oglAAPgrbmPipe = oglRenderPipe.trbceWrbp();
            }
            oglAAPgrbmPipe = oglRenderPipe.getAAPbrbllelogrbmPipe();
            oglTxRenderPipe =
                new PixelToPbrbllelogrbmConverter(oglRenderPipe,
                                                  oglRenderPipe,
                                                  1.0, 0.25, true);

            OGLBlitLoops.register();
            OGLMbskFill.register();
            OGLMbskBlit.register();
        }
    }

    protected OGLSurfbceDbtb(OGLGrbphicsConfig gc,
                             ColorModel cm, int type)
    {
        super(getCustomSurfbceType(type), cm);
        this.grbphicsConfig = gc;
        this.type = type;
        setBlitProxyKey(gc.getProxyKey());
    }

    @Override
    public SurfbceDbtbProxy mbkeProxyFor(SurfbceDbtb srcDbtb) {
        return OGLSurfbceDbtbProxy.crebteProxy(srcDbtb, grbphicsConfig);
    }

    /**
     * Returns the bppropribte SurfbceType corresponding to the given OpenGL
     * surfbce type constbnt (e.g. TEXTURE -> OpenGLTexture).
     */
    privbte stbtic SurfbceType getCustomSurfbceType(int oglType) {
        switch (oglType) {
        cbse TEXTURE:
            return OpenGLTexture;
        cbse FBOBJECT:
            return OpenGLSurfbceRTT;
        cbse PBUFFER:
        defbult:
            return OpenGLSurfbce;
        }
    }

    /**
     * Note: This should only be cblled from the QFT under the AWT lock.
     * This method is kept sepbrbte from the initSurfbce() method below just
     * to keep the code b bit clebner.
     */
    privbte void initSurfbceNow(int width, int height) {
        boolebn isOpbque = (getTrbnspbrency() == Trbnspbrency.OPAQUE);
        boolebn success = fblse;

        switch (type) {
        cbse PBUFFER:
            success = initPbuffer(getNbtiveOps(),
                                  grbphicsConfig.getNbtiveConfigInfo(),
                                  isOpbque,
                                  width, height);
            brebk;

        cbse TEXTURE:
            success = initTexture(getNbtiveOps(),
                                  isOpbque, isTexNonPow2Avbilbble(),
                                  isTexRectAvbilbble(),
                                  width, height);
            brebk;

        cbse FBOBJECT:
            success = initFBObject(getNbtiveOps(),
                                   isOpbque, isTexNonPow2Avbilbble(),
                                   isTexRectAvbilbble(),
                                   width, height);
            brebk;

        cbse FLIP_BACKBUFFER:
            success = initFlipBbckbuffer(getNbtiveOps());
            brebk;

        defbult:
            brebk;
        }

        if (!success) {
            throw new OutOfMemoryError("cbn't crebte offscreen surfbce");
        }
    }

    /**
     * Initiblizes the bppropribte OpenGL offscreen surfbce bbsed on the vblue
     * of the type pbrbmeter.  If the surfbce crebtion fbils for bny rebson,
     * bn OutOfMemoryError will be thrown.
     */
    protected void initSurfbce(finbl int width, finbl int height) {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            switch (type) {
            cbse TEXTURE:
            cbse PBUFFER:
            cbse FBOBJECT:
                // need to mbke sure the context is current before
                // crebting the texture (or pbuffer, or fbobject)
                OGLContext.setScrbtchSurfbce(grbphicsConfig);
                brebk;
            defbult:
                brebk;
            }
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    initSurfbceNow(width, height);
                }
            });
        } finblly {
            rq.unlock();
        }
    }

    /**
     * Returns the OGLContext for the GrbphicsConfig bssocibted with this
     * surfbce.
     */
    public finbl OGLContext getContext() {
        return grbphicsConfig.getContext();
    }

    /**
     * Returns the OGLGrbphicsConfig bssocibted with this surfbce.
     */
    finbl OGLGrbphicsConfig getOGLGrbphicsConfig() {
        return grbphicsConfig;
    }

    /**
     * Returns one of the surfbce type constbnts defined bbove.
     */
    public finbl int getType() {
        return type;
    }

    /**
     * If this surfbce is bbcked by b texture object, returns the tbrget
     * for thbt texture (either GL_TEXTURE_2D or GL_TEXTURE_RECTANGLE_ARB).
     * Otherwise, this method will return zero.
     */
    public finbl int getTextureTbrget() {
        return getTextureTbrget(getNbtiveOps());
    }

    /**
     * If this surfbce is bbcked by b texture object, returns the texture ID
     * for thbt texture.
     * Otherwise, this method will return zero.
     */
    public finbl int getTextureID() {
        return getTextureID(getNbtiveOps());
    }

    /**
     * Returns nbtive resource of specified {@code resType} bssocibted with
     * this surfbce.
     *
     * Specificblly, for {@code OGLSurfbceDbtb} this method returns the
     * the following:
     * <pre>
     * TEXTURE              - texture id
     * </pre>
     *
     * Note: the resource returned by this method is only vblid on the rendering
     * threbd.
     *
     * @return nbtive resource of specified type or 0L if
     * such resource doesn't exist or cbn not be retrieved.
     * @see sun.jbvb2d.pipe.hw.AccelSurfbce#getNbtiveResource
     */
    public long getNbtiveResource(int resType) {
        if (resType == TEXTURE) {
            return getTextureID();
        }
        return 0L;
    }

    public Rbster getRbster(int x, int y, int w, int h) {
        throw new InternblError("not implemented yet");
    }

    /**
     * For now, we cbn only render LCD text if:
     *   - the frbgment shbder extension is bvbilbble, bnd
     *   - blending is disbbled, bnd
     *   - the source color is opbque
     *   - bnd the destinbtion is opbque
     *
     * Eventublly, we could enhbnce the nbtive OGL text rendering code
     * bnd remove the bbove restrictions, but thbt would require significbntly
     * more code just to support b few uncommon cbses.
     */
    public boolebn cbnRenderLCDText(SunGrbphics2D sg2d) {
        return
            grbphicsConfig.isCbpPresent(CAPS_EXT_LCD_SHADER) &&
            sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY &&
            sg2d.pbintStbte <= SunGrbphics2D.PAINT_OPAQUECOLOR &&
            sg2d.surfbceDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE;
    }

    public void vblidbtePipe(SunGrbphics2D sg2d) {
        TextPipe textpipe;
        boolebn vblidbted = fblse;

        // OGLTextRenderer hbndles both AA bnd non-AA text, but
        // only works with the following modes:
        // (Note: For LCD text we only enter this code pbth if
        // cbnRenderLCDText() hbs blrebdy vblidbted thbt the mode is
        // CompositeType.SrcNoEb (opbque color), which will be subsumed
        // by the CompositeType.SrcNoEb (bny color) test below.)

        if (/* CompositeType.SrcNoEb (bny color) */
            (sg2d.compositeStbte <= SunGrbphics2D.COMP_ISCOPY &&
             sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR)         ||

            /* CompositeType.SrcOver (bny color) */
            (sg2d.compositeStbte == SunGrbphics2D.COMP_ALPHA   &&
             sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR &&
             (((AlphbComposite)sg2d.composite).getRule() ==
              AlphbComposite.SRC_OVER))                                 ||

            /* CompositeType.Xor (bny color) */
            (sg2d.compositeStbte == SunGrbphics2D.COMP_XOR &&
             sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR))
        {
            textpipe = oglTextPipe;
        } else {
            // do this to initiblize textpipe correctly; we will bttempt
            // to override the non-text pipes below
            super.vblidbtePipe(sg2d);
            textpipe = sg2d.textpipe;
            vblidbted = true;
        }

        PixelToPbrbllelogrbmConverter txPipe = null;
        OGLRenderer nonTxPipe = null;

        if (sg2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) {
            if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR) {
                if (sg2d.compositeStbte <= SunGrbphics2D.COMP_XOR) {
                    txPipe = oglTxRenderPipe;
                    nonTxPipe = oglRenderPipe;
                }
            } else if (sg2d.compositeStbte <= SunGrbphics2D.COMP_ALPHA) {
                if (OGLPbints.isVblid(sg2d)) {
                    txPipe = oglTxRenderPipe;
                    nonTxPipe = oglRenderPipe;
                }
                // custom pbints hbndled by super.vblidbtePipe() below
            }
        } else {
            if (sg2d.pbintStbte <= SunGrbphics2D.PAINT_ALPHACOLOR) {
                if (grbphicsConfig.isCbpPresent(CAPS_PS30) &&
                    (sg2d.imbgeComp == CompositeType.SrcOverNoEb ||
                     sg2d.imbgeComp == CompositeType.SrcOver))
                {
                    if (!vblidbted) {
                        super.vblidbtePipe(sg2d);
                        vblidbted = true;
                    }
                    PixelToPbrbllelogrbmConverter bbConverter =
                        new PixelToPbrbllelogrbmConverter(sg2d.shbpepipe,
                                                          oglAAPgrbmPipe,
                                                          1.0/8.0, 0.499,
                                                          fblse);
                    sg2d.drbwpipe = bbConverter;
                    sg2d.fillpipe = bbConverter;
                    sg2d.shbpepipe = bbConverter;
                } else if (sg2d.compositeStbte == SunGrbphics2D.COMP_XOR) {
                    // instbll the solid pipes when AA bnd XOR bre both enbbled
                    txPipe = oglTxRenderPipe;
                    nonTxPipe = oglRenderPipe;
                }
            }
            // other cbses hbndled by super.vblidbtePipe() below
        }

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
            // Note thbt we use the trbnsforming pipe here becbuse it
            // will exbmine the shbpe bnd possibly perform bn optimized
            // operbtion if it cbn be simplified.  The simplificbtions
            // will be vblid for bll STROKE bnd TRANSFORM types.
            sg2d.shbpepipe = txPipe;
        } else {
            if (!vblidbted) {
                super.vblidbtePipe(sg2d);
            }
        }

        // instbll the text pipe bbsed on our ebrlier decision
        sg2d.textpipe = textpipe;

        // blwbys override the imbge pipe with the speciblized OGL pipe
        sg2d.imbgepipe = oglImbgePipe;
    }

    @Override
    protected MbskFill getMbskFill(SunGrbphics2D sg2d) {
        if (sg2d.pbintStbte > SunGrbphics2D.PAINT_ALPHACOLOR) {
            /*
             * We cbn only bccelerbte non-Color MbskFill operbtions if
             * bll of the following conditions hold true:
             *   - there is bn implementbtion for the given pbintStbte
             *   - the current Pbint cbn be bccelerbted for this destinbtion
             *   - multitexturing is bvbilbble (since we need to modulbte
             *     the blphb mbsk texture with the pbint texture)
             *
             * In bll other cbses, we return null, in which cbse the
             * vblidbtion code will choose b more generbl softwbre-bbsed loop.
             */
            if (!OGLPbints.isVblid(sg2d) ||
                !grbphicsConfig.isCbpPresent(CAPS_MULTITEXTURE))
            {
                return null;
            }
        }
        return super.getMbskFill(sg2d);
    }

    public boolebn copyAreb(SunGrbphics2D sg2d,
                            int x, int y, int w, int h, int dx, int dy)
    {
        if (sg2d.trbnsformStbte < SunGrbphics2D.TRANSFORM_TRANSLATESCALE &&
            sg2d.compositeStbte < SunGrbphics2D.COMP_XOR)
        {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;

            oglRenderPipe.copyAreb(sg2d, x, y, w, h, dx, dy);

            return true;
        }
        return fblse;
    }

    public void flush() {
        invblidbte();
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            // mbke sure we hbve b current context before
            // disposing the nbtive resources (e.g. texture object)
            OGLContext.setScrbtchSurfbce(grbphicsConfig);

            RenderBuffer buf = rq.getBuffer();
            rq.ensureCbpbcityAndAlignment(12, 4);
            buf.putInt(FLUSH_SURFACE);
            buf.putLong(getNbtiveOps());

            // this cbll is expected to complete synchronously, so flush now
            rq.flushNow();
        } finblly {
            rq.unlock();
        }
    }

    /**
     * Disposes the nbtive resources bssocibted with the given OGLSurfbceDbtb
     * (referenced by the pDbtb pbrbmeter).  This method is invoked from
     * the nbtive Dispose() method from the Disposer threbd when the
     * Jbvb-level OGLSurfbceDbtb object is bbout to go bwby.  Note thbt we
     * blso pbss b reference to the nbtive GLX/WGLGrbphicsConfigInfo
     * (pConfigInfo) for the purposes of mbking b context current.
     */
    stbtic void dispose(long pDbtb, long pConfigInfo) {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            // mbke sure we hbve b current context before
            // disposing the nbtive resources (e.g. texture object)
            OGLContext.setScrbtchSurfbce(pConfigInfo);

            RenderBuffer buf = rq.getBuffer();
            rq.ensureCbpbcityAndAlignment(12, 4);
            buf.putInt(DISPOSE_SURFACE);
            buf.putLong(pDbtb);

            // this cbll is expected to complete synchronously, so flush now
            rq.flushNow();
        } finblly {
            rq.unlock();
        }
    }

    stbtic void swbpBuffers(long window) {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            RenderBuffer buf = rq.getBuffer();
            rq.ensureCbpbcityAndAlignment(12, 4);
            buf.putInt(SWAP_BUFFERS);
            buf.putLong(window);
            rq.flushNow();
        } finblly {
            rq.unlock();
        }
    }

    /**
     * Returns true if OpenGL textures cbn hbve non-power-of-two dimensions
     * when using the bbsic GL_TEXTURE_2D tbrget.
     */
    boolebn isTexNonPow2Avbilbble() {
        return grbphicsConfig.isCbpPresent(CAPS_TEXNONPOW2);
    }

    /**
     * Returns true if OpenGL textures cbn hbve non-power-of-two dimensions
     * when using the GL_TEXTURE_RECTANGLE_ARB tbrget (only bvbilbble when the
     * GL_ARB_texture_rectbngle extension is present).
     */
    boolebn isTexRectAvbilbble() {
        return grbphicsConfig.isCbpPresent(CAPS_EXT_TEXRECT);
    }

    public Rectbngle getNbtiveBounds() {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            return new Rectbngle(nbtiveWidth, nbtiveHeight);
        } finblly {
            rq.unlock();
        }
    }

    /**
     * Returns true if the surfbce is bn on-screen window surfbce or
     * b FBO texture bttbched to bn on-screen CALbyer.
     *
     * Needed by Mbc OS X port.
     */
    boolebn isOnScreen() {
        return getType() == WINDOW;
    }
}
