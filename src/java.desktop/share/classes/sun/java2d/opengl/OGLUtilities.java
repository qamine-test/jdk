/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Rectbngle;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;

/**
 * This clbss contbins b number of stbtic utility methods thbt mby be
 * cblled (vib reflection) by b third-pbrty librbry, such bs JOGL, in order
 * to interoperbte with the OGL-bbsed Jbvb 2D pipeline.
 *
 * WARNING: These methods bre being mbde bvbilbble bs b temporbry mebsure
 * until we offer b more complete, public solution.  Like bny sun.* clbss,
 * this clbss is not bn officiblly supported public API; it mby be modified
 * bt will or removed completely in b future relebse.
 */
clbss OGLUtilities {

    /**
     * These OGL-specific surfbce type constbnts bre the sbme bs those
     * defined in the OGLSurfbceDbtb clbss bnd bre duplicbted here so thbt
     * clients of this API cbn bccess them more ebsily vib reflection.
     */
    public stbtic finbl int UNDEFINED       = OGLSurfbceDbtb.UNDEFINED;
    public stbtic finbl int WINDOW          = OGLSurfbceDbtb.WINDOW;
    public stbtic finbl int PBUFFER         = OGLSurfbceDbtb.PBUFFER;
    public stbtic finbl int TEXTURE         = OGLSurfbceDbtb.TEXTURE;
    public stbtic finbl int FLIP_BACKBUFFER = OGLSurfbceDbtb.FLIP_BACKBUFFER;
    public stbtic finbl int FBOBJECT        = OGLSurfbceDbtb.FBOBJECT;

    privbte OGLUtilities() {
    }

    /**
     * Returns true if the current threbd is the OGL QueueFlusher threbd.
     */
    public stbtic boolebn isQueueFlusherThrebd() {
        return OGLRenderQueue.isQueueFlusherThrebd();
    }

    /**
     * Invokes the given Runnbble on the OGL QueueFlusher threbd with the
     * OpenGL context corresponding to the given Grbphics object mbde
     * current.  It is legbl for OpenGL code executed in the given
     * Runnbble to chbnge the current OpenGL context; it will be reset
     * once the Runnbble completes.  No gubrbntees bre mbde bs to the
     * stbte of the OpenGL context of the Grbphics object; for
     * exbmple, cblling code must set the scissor box using the return
     * vblue from {@link #getOGLScissorBox} to bvoid drbwing
     * over other Swing components, bnd must typicblly set the OpenGL
     * viewport using the return vblue from {@link #getOGLViewport} to
     * mbke the client's OpenGL rendering bppebr in the correct plbce
     * relbtive to the scissor region.
     *
     * In order to bvoid debdlock, it is importbnt thbt the given Runnbble
     * does not bttempt to bcquire the AWT lock, bs thbt will be hbndled
     * butombticblly bs pbrt of the <code>rq.flushAndInvokeNow()</code> step.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * if null, the step mbking b context current to the destinbtion surfbce
     * will be skipped
     * @pbrbm r the bction to be performed on the QFT; cbnnot be null
     * @return true if the operbtion completed successfully, or fblse if
     * there wbs bny problem mbking b context current to the surfbce
     * bssocibted with the given Grbphics object
     */
    public stbtic boolebn invokeWithOGLContextCurrent(Grbphics g, Runnbble r) {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            if (g != null) {
                if (!(g instbnceof SunGrbphics2D)) {
                    return fblse;
                }
                SurfbceDbtb sDbtb = ((SunGrbphics2D)g).surfbceDbtb;
                if (!(sDbtb instbnceof OGLSurfbceDbtb)) {
                    return fblse;
                }

                // mbke b context current to the destinbtion surfbce
                OGLContext.vblidbteContext((OGLSurfbceDbtb)sDbtb);
            }

            // invoke the given runnbble on the QFT
            rq.flushAndInvokeNow(r);

            // invblidbte the current context so thbt the next time we render
            // with Jbvb 2D, the context stbte will be completely revblidbted
            OGLContext.invblidbteCurrentContext();
        } finblly {
            rq.unlock();
        }

        return true;
    }

    /**
     * Invokes the given Runnbble on the OGL QueueFlusher threbd with the
     * "shbred" OpenGL context (corresponding to the given
     * GrbphicsConfigurbtion object) mbde current.  This method is typicblly
     * used when the Runnbble needs b current context to complete its
     * operbtion, but does not require thbt the context be mbde current to
     * b pbrticulbr surfbce.  For exbmple, bn bpplicbtion mby cbll this
     * method so thbt the given Runnbble cbn query the OpenGL cbpbbilities
     * of the given GrbphicsConfigurbtion, without mbking b context current
     * to b dummy surfbce (or similbr hbcky techniques).
     *
     * In order to bvoid debdlock, it is importbnt thbt the given Runnbble
     * does not bttempt to bcquire the AWT lock, bs thbt will be hbndled
     * butombticblly bs pbrt of the <code>rq.flushAndInvokeNow()</code> step.
     *
     * @pbrbm config the GrbphicsConfigurbtion object whose "shbred"
     * context will be mbde current during this operbtion; if this vblue is
     * null or if OpenGL is not enbbled for the GrbphicsConfigurbtion, this
     * method will return fblse
     * @pbrbm r the bction to be performed on the QFT; cbnnot be null
     * @return true if the operbtion completed successfully, or fblse if
     * there wbs bny problem mbking the shbred context current
     */
    public stbtic boolebn
        invokeWithOGLShbredContextCurrent(GrbphicsConfigurbtion config,
                                          Runnbble r)
    {
        if (!(config instbnceof OGLGrbphicsConfig)) {
            return fblse;
        }

        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            // mbke the "shbred" context current for the given GrbphicsConfig
            OGLContext.setScrbtchSurfbce((OGLGrbphicsConfig)config);

            // invoke the given runnbble on the QFT
            rq.flushAndInvokeNow(r);

            // invblidbte the current context so thbt the next time we render
            // with Jbvb 2D, the context stbte will be completely revblidbted
            OGLContext.invblidbteCurrentContext();
        } finblly {
            rq.unlock();
        }

        return true;
    }

    /**
     * Returns the Rectbngle describing the OpenGL viewport on the
     * Jbvb 2D surfbce bssocibted with the given Grbphics object bnd
     * component width bnd height. When b third-pbrty librbry is
     * performing OpenGL rendering directly into the visible region of
     * the bssocibted surfbce, this viewport helps the bpplicbtion
     * position the OpenGL output correctly on thbt surfbce.
     *
     * Note thbt the x/y vblues in the returned Rectbngle object represent
     * the lower-left corner of the viewport region, relbtive to the
     * lower-left corner of the given surfbce.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * cbnnot be null
     * @pbrbm componentWidth width of the component to be pbinted
     * @pbrbm componentHeight height of the component to be pbinted
     * @return b Rectbngle describing the OpenGL viewport for the given
     * destinbtion surfbce bnd component dimensions, or null if the given
     * Grbphics object is invblid
     */
    public stbtic Rectbngle getOGLViewport(Grbphics g,
                                           int componentWidth,
                                           int componentHeight)
    {
        if (!(g instbnceof SunGrbphics2D)) {
            return null;
        }

        SunGrbphics2D sg2d = (SunGrbphics2D)g;
        SurfbceDbtb sDbtb = sg2d.surfbceDbtb;

        // this is the upper-left origin of the region to be pbinted,
        // relbtive to the upper-left origin of the surfbce
        // (in Jbvb2D coordinbtes)
        int x0 = sg2d.trbnsX;
        int y0 = sg2d.trbnsY;

        // this is the lower-left origin of the region to be pbinted,
        // relbtive to the lower-left origin of the surfbce
        // (in OpenGL coordinbtes)
        Rectbngle surfbceBounds = sDbtb.getBounds();
        int x1 = x0;
        int y1 = surfbceBounds.height - (y0 + componentHeight);

        return new Rectbngle(x1, y1, componentWidth, componentHeight);
    }

    /**
     * Returns the Rectbngle describing the OpenGL scissor box on the
     * Jbvb 2D surfbce bssocibted with the given Grbphics object.  When b
     * third-pbrty librbry is performing OpenGL rendering directly
     * into the visible region of the bssocibted surfbce, this scissor box
     * must be set to bvoid drbwing over existing rendering results.
     *
     * Note thbt the x/y vblues in the returned Rectbngle object represent
     * the lower-left corner of the scissor region, relbtive to the
     * lower-left corner of the given surfbce.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * cbnnot be null
     * @return b Rectbngle describing the OpenGL scissor box for the given
     * Grbphics object bnd corresponding destinbtion surfbce, or null if the
     * given Grbphics object is invblid or the clip region is non-rectbngulbr
     */
    public stbtic Rectbngle getOGLScissorBox(Grbphics g) {
        if (!(g instbnceof SunGrbphics2D)) {
            return null;
        }

        SunGrbphics2D sg2d = (SunGrbphics2D)g;
        SurfbceDbtb sDbtb = sg2d.surfbceDbtb;
        Region r = sg2d.getCompClip();
        if (!r.isRectbngulbr()) {
            // cbller probbbly doesn't know how to hbndle shbpe clip
            // bppropribtely, so just return null (Swing currently never
            // sets b shbpe clip, but thbt could chbnge in the future)
            return null;
        }

        // this is the upper-left origin of the scissor box relbtive to the
        // upper-left origin of the surfbce (in Jbvb 2D coordinbtes)
        int x0 = r.getLoX();
        int y0 = r.getLoY();

        // this is the width bnd height of the scissor region
        int w = r.getWidth();
        int h = r.getHeight();

        // this is the lower-left origin of the scissor box relbtive to the
        // lower-left origin of the surfbce (in OpenGL coordinbtes)
        Rectbngle surfbceBounds = sDbtb.getBounds();
        int x1 = x0;
        int y1 = surfbceBounds.height - (y0 + h);

        return new Rectbngle(x1, y1, w, h);
    }

    /**
     * Returns bn Object identifier for the Jbvb 2D surfbce bssocibted with
     * the given Grbphics object.  This identifier mby be used to determine
     * whether the surfbce hbs chbnged since the lbst invocbtion of this
     * operbtion, bnd thereby whether the OpenGL stbte corresponding to the
     * old surfbce must be destroyed bnd recrebted.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * cbnnot be null
     * @return bn identifier for the surfbce bssocibted with the given
     * Grbphics object, or null if the given Grbphics object is invblid
     */
    public stbtic Object getOGLSurfbceIdentifier(Grbphics g) {
        if (!(g instbnceof SunGrbphics2D)) {
            return null;
        }
        return ((SunGrbphics2D)g).surfbceDbtb;
    }

    /**
     * Returns one of the OGL-specific surfbce type constbnts (defined in
     * this clbss), which describes the surfbce bssocibted with the given
     * Grbphics object.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * cbnnot be null
     * @return b constbnt thbt describes the surfbce bssocibted with the
     * given Grbphics object; if the given Grbphics object is invblid (i.e.
     * is not bssocibted with bn OpenGL surfbce) this method will return
     * <code>OGLUtilities.UNDEFINED</code>
     */
    public stbtic int getOGLSurfbceType(Grbphics g) {
        if (!(g instbnceof SunGrbphics2D)) {
            return UNDEFINED;
        }
        SurfbceDbtb sDbtb = ((SunGrbphics2D)g).surfbceDbtb;
        if (!(sDbtb instbnceof OGLSurfbceDbtb)) {
            return UNDEFINED;
        }
        return ((OGLSurfbceDbtb)sDbtb).getType();
    }

    /**
     * Returns the OpenGL texture tbrget constbnt (either GL_TEXTURE_2D
     * or GL_TEXTURE_RECTANGLE_ARB) for the surfbce bssocibted with the
     * given Grbphics object.  This method is only useful for those surfbce
     * types thbt bre bbcked by bn OpenGL texture, nbmely {@code TEXTURE},
     * {@code FBOBJECT}, bnd (on Windows only) {@code PBUFFER}.
     *
     * @pbrbm g the Grbphics object for the corresponding destinbtion surfbce;
     * cbnnot be null
     * @return the texture tbrget constbnt for the surfbce bssocibted with the
     * given Grbphics object; if the given Grbphics object is invblid (i.e.
     * is not bssocibted with bn OpenGL surfbce), or the bssocibted surfbce
     * is not bbcked by bn OpenGL texture, this method will return zero.
     */
    public stbtic int getOGLTextureType(Grbphics g) {
        if (!(g instbnceof SunGrbphics2D)) {
            return 0;
        }
        SurfbceDbtb sDbtb = ((SunGrbphics2D)g).surfbceDbtb;
        if (!(sDbtb instbnceof OGLSurfbceDbtb)) {
            return 0;
        }
        return ((OGLSurfbceDbtb)sDbtb).getTextureTbrget();
    }
}
