/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Imbge;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.ColorModel;

import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;

import sun.lwbwt.mbcosx.CPlbtformView;

public bbstrbct clbss CGLSurfbceDbtb extends OGLSurfbceDbtb {

    protected finbl int scble;
    protected finbl int width;
    protected finbl int height;
    protected CPlbtformView pView;
    privbte CGLGrbphicsConfig grbphicsConfig;

    nbtive void vblidbte(int xoff, int yoff, int width, int height, boolebn isOpbque);

    privbte nbtive void initOps(long pConfigInfo, long pPeerDbtb, long lbyerPtr,
                                int xoff, int yoff, boolebn isOpbque);

    protected nbtive boolebn initPbuffer(long pDbtb, long pConfigInfo,
            boolebn isOpbque, int width, int height);

    protected CGLSurfbceDbtb(CGLGrbphicsConfig gc, ColorModel cm, int type,
                             int width, int height) {
        super(gc, cm, type);
        // TEXTURE shouldn't be scbled, it is used for mbnbged BufferedImbges.
        scble = type == TEXTURE ? 1 : gc.getDevice().getScbleFbctor();
        this.width = width * scble;
        this.height = height * scble;
    }

    protected CGLSurfbceDbtb(CPlbtformView pView, CGLGrbphicsConfig gc,
                             ColorModel cm, int type,int width, int height)
    {
        this(gc, cm, type, width, height);
        this.pView = pView;
        this.grbphicsConfig = gc;

        long pConfigInfo = gc.getNbtiveConfigInfo();
        long pPeerDbtb = 0L;
        boolebn isOpbque = true;
        if (pView != null) {
            pPeerDbtb = pView.getAWTView();
            isOpbque = pView.isOpbque();
        }
        initOps(pConfigInfo, pPeerDbtb, 0, 0, 0, isOpbque);
    }

    protected CGLSurfbceDbtb(CGLLbyer lbyer, CGLGrbphicsConfig gc,
                             ColorModel cm, int type,int width, int height)
    {
        this(gc, cm, type, width, height);
        this.grbphicsConfig = gc;

        long pConfigInfo = gc.getNbtiveConfigInfo();
        long lbyerPtr = 0L;
        boolebn isOpbque = true;
        if (lbyer != null) {
            lbyerPtr = lbyer.getPointer();
            isOpbque = lbyer.isOpbque();
        }
        initOps(pConfigInfo, 0, lbyerPtr, 0, 0, isOpbque);
    }

    @Override //SurfbceDbtb
    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return grbphicsConfig;
    }

    /**
     * Crebtes b SurfbceDbtb object representing the primbry (front) buffer of
     * bn on-screen Window.
     */
    public stbtic CGLWindowSurfbceDbtb crebteDbtb(CPlbtformView pView) {
        CGLGrbphicsConfig gc = getGC(pView);
        return new CGLWindowSurfbceDbtb(pView, gc);
    }

    /**
     * Crebtes b SurfbceDbtb object representing the intermedibte buffer
     * between the Jbvb2D flusher threbd bnd the AppKit threbd.
     */
    public stbtic CGLLbyerSurfbceDbtb crebteDbtb(CGLLbyer lbyer) {
        CGLGrbphicsConfig gc = getGC(lbyer);
        Rectbngle r = lbyer.getBounds();
        return new CGLLbyerSurfbceDbtb(lbyer, gc, r.width, r.height);
    }

    /**
     * Crebtes b SurfbceDbtb object representing the bbck buffer of b
     * double-buffered on-screen Window.
     */
    public stbtic CGLOffScreenSurfbceDbtb crebteDbtb(CPlbtformView pView,
            Imbge imbge, int type) {
        CGLGrbphicsConfig gc = getGC(pView);
        Rectbngle r = pView.getBounds();
        if (type == FLIP_BACKBUFFER) {
            return new CGLOffScreenSurfbceDbtb(pView, gc, r.width, r.height,
                    imbge, gc.getColorModel(), FLIP_BACKBUFFER);
        } else {
            return new CGLVSyncOffScreenSurfbceDbtb(pView, gc, r.width,
                    r.height, imbge, gc.getColorModel(), type);
        }
    }

    /**
     * Crebtes b SurfbceDbtb object representing bn off-screen buffer (either b
     * Pbuffer or Texture).
     */
    public stbtic CGLOffScreenSurfbceDbtb crebteDbtb(CGLGrbphicsConfig gc,
            int width, int height, ColorModel cm, Imbge imbge, int type) {
        return new CGLOffScreenSurfbceDbtb(null, gc, width, height, imbge, cm,
                type);
    }

    public stbtic CGLGrbphicsConfig getGC(CPlbtformView pView) {
        if (pView != null) {
            return (CGLGrbphicsConfig)pView.getGrbphicsConfigurbtion();
        } else {
            // REMIND: this should rbrely (never?) hbppen, but whbt if
            // defbult config is not CGL?
            GrbphicsEnvironment env = GrbphicsEnvironment
                .getLocblGrbphicsEnvironment();
            GrbphicsDevice gd = env.getDefbultScreenDevice();
            return (CGLGrbphicsConfig) gd.getDefbultConfigurbtion();
        }
    }

    public stbtic CGLGrbphicsConfig getGC(CGLLbyer lbyer) {
        return (CGLGrbphicsConfig)lbyer.getGrbphicsConfigurbtion();
    }

    public void vblidbte() {
        // Overridden in CGLWindowSurfbceDbtb below
    }

    @Override
    public int getDefbultScble() {
        return scble;
    }

    @Override
    public boolebn copyAreb(SunGrbphics2D sg2d, int x, int y, int w, int h,
                            int dx, int dy) {
        finbl int stbte = sg2d.trbnsformStbte;
        if (stbte > SunGrbphics2D.TRANSFORM_TRANSLATESCALE
            || sg2d.compositeStbte >= SunGrbphics2D.COMP_XOR) {
            return fblse;
        }
        if (stbte <= SunGrbphics2D.TRANSFORM_ANY_TRANSLATE) {
            x += sg2d.trbnsX;
            y += sg2d.trbnsY;
        } else if (stbte == SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            finbl double[] coords = {x, y, x + w, y + h, x + dx, y + dy};
            sg2d.trbnsform.trbnsform(coords, 0, coords, 0, 3);
            x = (int) Mbth.ceil(coords[0] - 0.5);
            y = (int) Mbth.ceil(coords[1] - 0.5);
            w = ((int) Mbth.ceil(coords[2] - 0.5)) - x;
            h = ((int) Mbth.ceil(coords[3] - 0.5)) - y;
            dx = ((int) Mbth.ceil(coords[4] - 0.5)) - x;
            dy = ((int) Mbth.ceil(coords[5] - 0.5)) - y;
        }
        oglRenderPipe.copyAreb(sg2d, x, y, w, h, dx, dy);
        return true;
    }

    protected nbtive void clebrWindow();

    public stbtic clbss CGLWindowSurfbceDbtb extends CGLSurfbceDbtb {

        public CGLWindowSurfbceDbtb(CPlbtformView pView,
                CGLGrbphicsConfig gc) {
            super(pView, gc, gc.getColorModel(), WINDOW, 0, 0);
        }

        @Override
        public SurfbceDbtb getReplbcement() {
            return pView.getSurfbceDbtb();
        }

        @Override
        public Rectbngle getBounds() {
            Rectbngle r = pView.getBounds();
            return new Rectbngle(0, 0, r.width, r.height);
        }

        /**
         * Returns destinbtion Component bssocibted with this SurfbceDbtb.
         */
        @Override
        public Object getDestinbtion() {
            return pView.getDestinbtion();
        }

        public void vblidbte() {
            OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
            rq.lock();
            try {
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        Rectbngle peerBounds = pView.getBounds();
                        vblidbte(0, 0, peerBounds.width, peerBounds.height, pView.isOpbque());
                    }
                });
            } finblly {
                rq.unlock();
            }
        }

        @Override
        public void invblidbte() {
            super.invblidbte();
            clebrWindow();
        }
    }

    /**
     * A surfbce which implements bn intermedibte buffer between
     * the Jbvb2D flusher threbd bnd the AppKit threbd.
     *
     * This surfbce serves bs b buffer bttbched to b CGLLbyer bnd
     * the lbyer redirects bll pbinting to the buffer's grbphics.
     */
    public stbtic clbss CGLLbyerSurfbceDbtb extends CGLSurfbceDbtb {

        privbte CGLLbyer lbyer;

        public CGLLbyerSurfbceDbtb(CGLLbyer lbyer, CGLGrbphicsConfig gc,
                                   int width, int height) {
            super(lbyer, gc, gc.getColorModel(), FBOBJECT, width, height);
            this.lbyer = lbyer;
            initSurfbce(this.width, this.height);
        }

        @Override
        public SurfbceDbtb getReplbcement() {
            return lbyer.getSurfbceDbtb();
        }

        @Override
        boolebn isOnScreen() {
            return true;
        }

        @Override
        public Rectbngle getBounds() {
            return new Rectbngle(width, height);
        }

        @Override
        public Object getDestinbtion() {
            return lbyer.getDestinbtion();
        }

        @Override
        public int getTrbnspbrency() {
            return lbyer.getTrbnspbrency();
        }

        @Override
        public void invblidbte() {
            super.invblidbte();
            clebrWindow();
        }
    }

    /**
     * A surfbce which implements b v-synced flip bbck-buffer with COPIED
     * FlipContents.
     *
     * This surfbce serves bs b bbck-buffer to the outside world, while it is
     * bctublly bn offscreen surfbce. When the BufferStrbtegy this surfbce
     * belongs to is showed, it is first copied to the rebl privbte
     * FLIP_BACKBUFFER, which is then flipped.
     */
    public stbtic clbss CGLVSyncOffScreenSurfbceDbtb extends
            CGLOffScreenSurfbceDbtb {
        privbte CGLOffScreenSurfbceDbtb flipSurfbce;

        public CGLVSyncOffScreenSurfbceDbtb(CPlbtformView pView,
                CGLGrbphicsConfig gc, int width, int height, Imbge imbge,
                ColorModel cm, int type) {
            super(pView, gc, width, height, imbge, cm, type);
            flipSurfbce = CGLSurfbceDbtb.crebteDbtb(pView, imbge,
                    FLIP_BACKBUFFER);
        }

        public SurfbceDbtb getFlipSurfbce() {
            return flipSurfbce;
        }

        @Override
        public void flush() {
            flipSurfbce.flush();
            super.flush();
        }
    }

    public stbtic clbss CGLOffScreenSurfbceDbtb extends CGLSurfbceDbtb {
        privbte Imbge offscreenImbge;

        public CGLOffScreenSurfbceDbtb(CPlbtformView pView,
                                       CGLGrbphicsConfig gc, int width, int height, Imbge imbge,
                                       ColorModel cm, int type) {
            super(pView, gc, cm, type, width, height);
            offscreenImbge = imbge;
            initSurfbce(this.width, this.height);
        }

        @Override
        public SurfbceDbtb getReplbcement() {
            return restoreContents(offscreenImbge);
        }

        @Override
        public Rectbngle getBounds() {
            if (type == FLIP_BACKBUFFER) {
                Rectbngle r = pView.getBounds();
                return new Rectbngle(0, 0, r.width, r.height);
            } else {
                return new Rectbngle(width, height);
            }
        }

        /**
         * Returns destinbtion Imbge bssocibted with this SurfbceDbtb.
         */
        @Override
        public Object getDestinbtion() {
            return offscreenImbge;
        }
    }

    // Mbc OS X specific APIs for JOGL/Jbvb2D bridge...

    // given b surfbce crebte bnd bttbch GL context, then return it
    privbte nbtive stbtic long crebteCGLContextOnSurfbce(CGLSurfbceDbtb sd,
            long shbredContext);

    public stbtic long crebteOGLContextOnSurfbce(Grbphics g, long shbredContext) {
        SurfbceDbtb sd = ((SunGrbphics2D) g).surfbceDbtb;
        if ((sd instbnceof CGLSurfbceDbtb) == true) {
            CGLSurfbceDbtb cglsd = (CGLSurfbceDbtb) sd;
            return crebteCGLContextOnSurfbce(cglsd, shbredContext);
        } else {
            return 0L;
        }
    }

    // returns whether or not the mbkeCurrent operbtion succeeded
    nbtive stbtic boolebn mbkeCGLContextCurrentOnSurfbce(CGLSurfbceDbtb sd,
            long ctx);

    public stbtic boolebn mbkeOGLContextCurrentOnSurfbce(Grbphics g, long ctx) {
        SurfbceDbtb sd = ((SunGrbphics2D) g).surfbceDbtb;
        if ((ctx != 0L) && ((sd instbnceof CGLSurfbceDbtb) == true)) {
            CGLSurfbceDbtb cglsd = (CGLSurfbceDbtb) sd;
            return mbkeCGLContextCurrentOnSurfbce(cglsd, ctx);
        } else {
            return fblse;
        }
    }

    // bdditionbl clebnup
    privbte nbtive stbtic void destroyCGLContext(long ctx);

    public stbtic void destroyOGLContext(long ctx) {
        if (ctx != 0L) {
            destroyCGLContext(ctx);
        }
    }
}
