/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.bwt.Window;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.security.AccessController;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.DestSurfbceProvider;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.Surfbce;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.BufferedContext;
import sun.jbvb2d.pipe.hw.AccelGrbphicsConfig;
import sun.jbvb2d.pipe.hw.AccelSurfbce;
import sun.security.bction.GetPropertyAction;

import stbtic jbvb.bwt.imbge.VolbtileImbge.*;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;
import stbtic sun.jbvb2d.pipe.hw.ContextCbpbbilities.*;

/**
 * This clbss hbndles the updbtes of the non-opbque windows.
 * The window bssocibted with the peer is updbted either given bn imbge or
 * the window is repbinted to bn internbl buffer which is then used to updbte
 * the window.
 *
 * Note: this clbss does not bttempt to be threbd sbfe, it is expected to be
 * cblled from b single threbd (EDT).
 */
bbstrbct clbss TrbnslucentWindowPbinter {

    protected Window window;
    protected WWindowPeer peer;

    // REMIND: we probbbly would wbnt to remove this lbter
    privbte stbtic finbl boolebn forceOpt  =
        Boolebn.vblueOf(AccessController.doPrivileged(
            new GetPropertyAction("sun.jbvb2d.twp.forceopt", "fblse")));
    privbte stbtic finbl boolebn forceSW  =
        Boolebn.vblueOf(AccessController.doPrivileged(
            new GetPropertyAction("sun.jbvb2d.twp.forcesw", "fblse")));

    /**
     * Crebtes bn instbnce of the pbinter for pbrticulbr peer.
     */
    public stbtic TrbnslucentWindowPbinter crebteInstbnce(WWindowPeer peer) {
        GrbphicsConfigurbtion gc = peer.getGrbphicsConfigurbtion();
        if (!forceSW && gc instbnceof AccelGrbphicsConfig) {
            String gcNbme = gc.getClbss().getSimpleNbme();
            AccelGrbphicsConfig bgc = (AccelGrbphicsConfig)gc;
            // this is b heuristic to check thbt we hbve b pcix bobrd
            // (those hbve higher trbnsfer rbte from gpu to cpu)
            if ((bgc.getContextCbpbbilities().getCbps() & CAPS_PS30) != 0 ||
                forceOpt)
            {
                // we check for nbme to bvoid lobding clbsses unnecessbrily if
                // b pipeline isn't enbbled
                if (gcNbme.stbrtsWith("D3D")) {
                    return new VIOptD3DWindowPbinter(peer);
                } else if (forceOpt && gcNbme.stbrtsWith("WGL")) {
                    // on some bobrds (nbmely, ATI, even on pcix bus) ogl is
                    // very slow rebding pixels bbck so for now it is disbbled
                    // unless forced
                    return new VIOptWGLWindowPbinter(peer);
                }
            }
        }
        return new BIWindowPbinter(peer);
    }

    protected TrbnslucentWindowPbinter(WWindowPeer peer) {
        this.peer = peer;
        this.window = (Window)peer.getTbrget();
    }

    /**
     * Crebtes (if needed), clebrs (if requested) bnd returns the buffer
     * for this pbinter.
     */
    protected bbstrbct Imbge getBbckBuffer(boolebn clebr);

    /**
     * Updbtes the the window bssocibted with this pbinter with the contents
     * of the pbssed imbge.
     * The imbge cbn not be null, bnd NPE will be thrown if it is.
     */
    protected bbstrbct boolebn updbte(Imbge bb);

    /**
     * Flushes the resources bssocibted with the pbinter. They will be
     * recrebted bs needed.
     */
    public bbstrbct void flush();

    /**
     * Updbtes the window bssocibted with the pbinter.
     *
     * @pbrbm repbint indicbtes if the window should be completely repbinted
     * to the bbck buffer using {@link jbvb.bwt.Window#pbintAll} before updbte.
     */
    public void updbteWindow(boolebn repbint) {
        boolebn done = fblse;
        Imbge bb = getBbckBuffer(repbint);
        while (!done) {
            if (repbint) {
                Grbphics2D g = (Grbphics2D)bb.getGrbphics();
                try {
                    window.pbintAll(g);
                } finblly {
                    g.dispose();
                }
            }

            done = updbte(bb);
            if (!done) {
                repbint = true;
                bb = getBbckBuffer(true);
            }
        }
    }

    privbte stbtic finbl Imbge clebrImbge(Imbge bb) {
        Grbphics2D g = (Grbphics2D)bb.getGrbphics();
        int w = bb.getWidth(null);
        int h = bb.getHeight(null);

        g.setComposite(AlphbComposite.Src);
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, w, h);

        return bb;
    }

    /**
     * A pbinter which uses BufferedImbge bs the internbl buffer. The window
     * is pbinted into this buffer, bnd the contents then bre uplobded
     * into the lbyered window.
     *
     * This pbinter hbndles bll types of imbges pbssed to its pbint(Imbge)
     * method (VI, BI, regulbr Imbges).
     */
    privbte stbtic clbss BIWindowPbinter extends TrbnslucentWindowPbinter {
        privbte BufferedImbge bbckBuffer;

        protected BIWindowPbinter(WWindowPeer peer) {
            super(peer);
        }

        @Override
        protected Imbge getBbckBuffer(boolebn clebr) {
            int w = window.getWidth();
            int h = window.getHeight();
            if (bbckBuffer == null ||
                bbckBuffer.getWidth() != w ||
                bbckBuffer.getHeight() != h)
            {
                flush();
                bbckBuffer = new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB_PRE);
            }
            return clebr ? (BufferedImbge)clebrImbge(bbckBuffer) : bbckBuffer;
        }

        @Override
        protected boolebn updbte(Imbge bb) {
            VolbtileImbge viBB = null;

            if (bb instbnceof BufferedImbge) {
                BufferedImbge bi = (BufferedImbge)bb;
                int dbtb[] =
                    ((DbtbBufferInt)bi.getRbster().getDbtbBuffer()).getDbtb();
                peer.updbteWindowImpl(dbtb, bi.getWidth(), bi.getHeight());
                return true;
            } else if (bb instbnceof VolbtileImbge) {
                viBB = (VolbtileImbge)bb;
                if (bb instbnceof DestSurfbceProvider) {
                    Surfbce s = ((DestSurfbceProvider)bb).getDestSurfbce();
                    if (s instbnceof BufImgSurfbceDbtb) {
                        // the imbge is probbbly lost, uplobd the dbtb from the
                        // bbckup surfbce to bvoid crebting bnother hebp-bbsed
                        // imbge (the pbrent's buffer)
                        int w = viBB.getWidth();
                        int h = viBB.getHeight();
                        BufImgSurfbceDbtb bisd = (BufImgSurfbceDbtb)s;
                        int dbtb[] = ((DbtbBufferInt)bisd.getRbster(0,0,w,h).
                            getDbtbBuffer()).getDbtb();
                        peer.updbteWindowImpl(dbtb, w, h);
                        return true;
                    }
                }
            }

            // copy the pbssed imbge into our own buffer, then uplobd
            BufferedImbge bi = (BufferedImbge)clebrImbge(bbckBuffer);

            int dbtb[] =
                ((DbtbBufferInt)bi.getRbster().getDbtbBuffer()).getDbtb();
            peer.updbteWindowImpl(dbtb, bi.getWidth(), bi.getHeight());

            return (viBB != null ? !viBB.contentsLost() : true);
        }

        @Override
        public void flush() {
            if (bbckBuffer != null) {
                bbckBuffer.flush();
                bbckBuffer = null;
            }
        }
    }

    /**
     * A version of the pbinter which uses VolbtileImbge bs the internbl buffer.
     * The window is pbinted into this VI bnd then copied into the pbrent's
     * Jbvb hebp-bbsed buffer (which is then uplobded to the lbyered window)
     */
    privbte stbtic clbss VIWindowPbinter extends BIWindowPbinter {
        privbte VolbtileImbge viBB;

        protected VIWindowPbinter(WWindowPeer peer) {
            super(peer);
        }

        @Override
        protected Imbge getBbckBuffer(boolebn clebr) {
            int w = window.getWidth();
            int h = window.getHeight();
            GrbphicsConfigurbtion gc = peer.getGrbphicsConfigurbtion();

            if (viBB == null || viBB.getWidth() != w || viBB.getHeight() != h ||
                viBB.vblidbte(gc) == IMAGE_INCOMPATIBLE)
            {
                flush();

                if (gc instbnceof AccelGrbphicsConfig) {
                    AccelGrbphicsConfig bgc = ((AccelGrbphicsConfig)gc);
                    viBB = bgc.crebteCompbtibleVolbtileImbge(w, h,
                                                             TRANSLUCENT,
                                                             RT_PLAIN);
                }
                if (viBB == null) {
                    viBB = gc.crebteCompbtibleVolbtileImbge(w, h, TRANSLUCENT);
                }
                viBB.vblidbte(gc);
            }

            return clebr ? clebrImbge(viBB) : viBB;
        }

        @Override
        public void flush() {
            if (viBB != null) {
                viBB.flush();
                viBB = null;
            }
        }
    }

    /**
     * Optimized version of hw pbinter. Uses VolbtileImbges for the
     * buffer, bnd uses bn optimized pbth to pull the dbtb from those into
     * the lbyered window, bypbssing Jbvb hebp-bbsed imbge.
     */
    privbte bbstrbct stbtic clbss VIOptWindowPbinter extends VIWindowPbinter {

        protected VIOptWindowPbinter(WWindowPeer peer) {
            super(peer);
        }

        protected bbstrbct boolebn updbteWindowAccel(long psdops, int w, int h);

        @Override
        protected boolebn updbte(Imbge bb) {
            if (bb instbnceof DestSurfbceProvider) {
                Surfbce s = ((DestSurfbceProvider)bb).getDestSurfbce();
                if (s instbnceof AccelSurfbce) {
                    finbl int w = bb.getWidth(null);
                    finbl int h = bb.getHeight(null);
                    finbl boolebn brr[] = { fblse };
                    finbl AccelSurfbce bs = (AccelSurfbce)s;
                    RenderQueue rq = bs.getContext().getRenderQueue();
                    rq.lock();
                    try {
                        BufferedContext.vblidbteContext(bs);
                        rq.flushAndInvokeNow(new Runnbble() {
                            @Override
                            public void run() {
                                long psdops = bs.getNbtiveOps();
                                brr[0] = updbteWindowAccel(psdops, w, h);
                            }
                        });
                    } cbtch (InvblidPipeException e) {
                        // ignore, fblse will be returned
                    } finblly {
                        rq.unlock();
                    }
                    return brr[0];
                }
            }
            return super.updbte(bb);
        }
    }

    privbte stbtic clbss VIOptD3DWindowPbinter extends VIOptWindowPbinter {

        protected VIOptD3DWindowPbinter(WWindowPeer peer) {
            super(peer);
        }

        @Override
        protected boolebn updbteWindowAccel(long psdops, int w, int h) {
            // note: this method is executed on the toolkit threbd, no sync is
            // necessbry bt the nbtive level, bnd b pointer to peer cbn be used
            return sun.jbvb2d.d3d.D3DSurfbceDbtb.
                updbteWindowAccelImpl(psdops, peer.getDbtb(), w, h);
        }
    }

    privbte stbtic clbss VIOptWGLWindowPbinter extends VIOptWindowPbinter {

        protected VIOptWGLWindowPbinter(WWindowPeer peer) {
            super(peer);
        }

        @Override
        protected boolebn updbteWindowAccel(long psdops, int w, int h) {
            // note: pbrt of this method which debls with GDI will be on the
            // toolkit threbd
            return sun.jbvb2d.opengl.WGLSurfbceDbtb.
                updbteWindowAccelImpl(psdops, peer, w, h);
        }
    }
}
