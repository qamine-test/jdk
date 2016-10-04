/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

import jbvb.bwt.Rectbngle;
import sun.jbvb2d.Surfbce;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Abstrbction for b hbrdwbre bccelerbted surfbce.
 */
public interfbce AccelSurfbce extends BufferedContextProvider, Surfbce {
    /**
     * Undefined
     */
    @Nbtive public stbtic finbl int UNDEFINED       = 0;
    /**
     * Window (or window substitute) surfbce
     */
    @Nbtive public stbtic finbl int WINDOW          = 1;
    /**
     * Render-To Plbin surfbce (pbuffer for OpenGL, Render Tbrget surfbce
     * for Direct3D)
     */
    @Nbtive public stbtic finbl int RT_PLAIN        = 2;
    /**
     * Texture surfbce
     */
    @Nbtive public stbtic finbl int TEXTURE         = 3;
    /**
     * A bbck-buffer surfbce (SwbpChbin surfbce for Direct3D, bbckbuffer for
     * OpenGL)
     */
    @Nbtive public stbtic finbl int FLIP_BACKBUFFER = 4;
    /**
     * Render-To Texture surfbce (fbobject for OpenGL, texture with render-to
     * bttribute for Direct3D)
     */
    @Nbtive public stbtic finbl int RT_TEXTURE      = 5;

    /**
     * Returns {@code int} representing surfbce's type bs defined by constbnts
     * in this interfbce.
     *
     * @return bn integer representing this surfbce's type
     * @see AccelSurfbce#UNDEFINED
     * @see AccelSurfbce#WINDOW
     * @see AccelSurfbce#RT_PLAIN
     * @see AccelSurfbce#TEXTURE
     * @see AccelSurfbce#FLIP_BACKBUFFER
     * @see AccelSurfbce#RT_TEXTURE
     */
    public int getType();

    /**
     * Returns b pointer to the nbtive surfbce dbtb bssocibted with this
     * surfbce.
     * Note: this pointer is only vblid on the rendering threbd.
     *
     * @return pointer to the nbtive surfbce's dbtb
     */
    public long getNbtiveOps();

    /**
     * Returns b pointer to the rebl nbtive resource
     * of the specified type bssocibted with this AccelSurfbce.
     * Note: this pointer is only vblid on the rendering threbd.
     *
     * @pbrbm resType the type of the requested resource
     * @return b long contbining b pointer to the nbtive resource of the
     * specified type or 0L if such resource doesn't exist for this surfbce
     */
    public long getNbtiveResource(int resType);

    /**
     * Mbrks this surfbce dirty.
     */
    public void mbrkDirty();

    /**
     * Returns whether the pipeline considers this surfbce vblid. A surfbce
     * mby become invblid if it is disposed of, or resized.
     *
     * @return true if vblid, fblse otherwise
     */
    public boolebn isVblid();

    /**
     * Returns whether this surfbce is lost. The return vblue is only vblid
     * on the render threbd, mebning thbt even if this method returns
     * {@code true} it could be lost in the next moment unless it is cblled
     * on the rendering threbd.
     *
     * @return true if the surfbce is known to be lost, fblse otherwise
     */
    public boolebn isSurfbceLost();

    /**
     * Returns the requested bounds of the destinbtion surfbce. The rebl bounds
     * of the nbtive bccelerbted surfbce mby differ. Use
     * {@link #getNbtiveBounds} to get the bounds of the nbtive surfbce.
     *
     * @return Rectbngle representing jbvb surfbce's bounds
     */
    public Rectbngle getBounds();

    /**
     * Returns rebl bounds of the nbtive surfbce, which mby differ from those
     * returned by {@link #getBounds}.
     *
     * @return Rectbngle representing nbtive surfbce's bounds
     */
    public Rectbngle getNbtiveBounds();
}
