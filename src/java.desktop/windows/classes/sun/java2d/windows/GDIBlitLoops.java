/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Composite;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.SurfbceDbtb;

/**
 * GDIBlitLoops
 *
 * This clbss bccelerbtes Blits between certbin surfbces bnd the
 * screen, using GDI.  The rebson for these loops is to find
 * b wby of copying to the screen without using DDrbw locking
 * thbt is fbster thbn our current fbllbbck (which crebtes
 * b temporbry GDI DIB)
 */
public clbss GDIBlitLoops extends Blit {

    // Store these vblues to be pbssed to nbtive code
    int rmbsk, gmbsk, bmbsk;

    // Needs lookup tbble (for indexed color imbge copies)
    boolebn indexed = fblse;

    /**
     * Note thbt we do not register loops to 8-byte destinbtions.  This
     * is due to fbster processing of dithering through our softwbre
     * loops thbn through GDI StretchBlt processing.
     */
    public stbtic void register()
    {
        GrbphicsPrimitive[] primitives = {
            new GDIBlitLoops(SurfbceType.IntRgb,
                             GDIWindowSurfbceDbtb.AnyGdi),
            new GDIBlitLoops(SurfbceType.Ushort555Rgb,
                             GDIWindowSurfbceDbtb.AnyGdi,
                             0x7C00, 0x03E0, 0x001F),
            new GDIBlitLoops(SurfbceType.Ushort565Rgb,
                             GDIWindowSurfbceDbtb.AnyGdi,
                             0xF800, 0x07E0, 0x001F),
            new GDIBlitLoops(SurfbceType.ThreeByteBgr,
                             GDIWindowSurfbceDbtb.AnyGdi),
            new GDIBlitLoops(SurfbceType.ByteIndexedOpbque,
                             GDIWindowSurfbceDbtb.AnyGdi,
                             true),
            new GDIBlitLoops(SurfbceType.Index8Grby,
                             GDIWindowSurfbceDbtb.AnyGdi,
                             true),
            new GDIBlitLoops(SurfbceType.ByteGrby,
                             GDIWindowSurfbceDbtb.AnyGdi),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    /**
     * This constructor exists for srcTypes thbt hbve no need of
     * component mbsks. GDI only expects mbsks for 2- bnd 4-byte
     * DIBs, so bll 1- bnd 3-byte srcTypes cbn skip the mbsk setting.
     */
    public GDIBlitLoops(SurfbceType srcType, SurfbceType dstType) {
        this(srcType, dstType, 0, 0, 0);
    }

    /**
     * This constructor exists for srcTypes thbt need lookup tbbles
     * during imbge copying.
     */
    public GDIBlitLoops(SurfbceType srcType, SurfbceType dstType,
                        boolebn indexed)
    {
        this(srcType, dstType, 0, 0, 0);
        this.indexed = indexed;
    }

    /**
     * This constructor sets mbsk for this primitive which cbn be
     * retrieved in nbtive code to set the bppropribte vblues for GDI.
     */
    public GDIBlitLoops(SurfbceType srcType, SurfbceType dstType,
                        int rmbsk, int gmbsk, int bmbsk)
    {
        super(srcType, CompositeType.SrcNoEb, dstType);
        this.rmbsk = rmbsk;
        this.gmbsk = gmbsk;
        this.bmbsk = bmbsk;
    }

    /**
     * nbtiveBlit
     * This nbtive method is where bll of the work hbppens in the
     * bccelerbted Blit.
     */
    public nbtive void nbtiveBlit(SurfbceDbtb src, SurfbceDbtb dst,
                                  Region clip,
                                  int sx, int sy, int dx, int dy,
                                  int w, int h,
                                  int rmbsk, int gmbsk, int bmbsk,
                                  boolebn needLut);

    /**
     * Blit
     * This method wrbps the nbtiveBlit cbll, sending in bdditionbl
     * info on whether the nbtive method needs to get LUT info
     * from the source imbge.  Note thbt we do not pbss in the
     * Composite dbtb becbuse we only register these loops for
     * SrcNoEb composite operbtions.
     */
    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        nbtiveBlit(src, dst, clip, sx, sy, dx, dy, w, h,
                   rmbsk, gmbsk, bmbsk, indexed);
    }


}
