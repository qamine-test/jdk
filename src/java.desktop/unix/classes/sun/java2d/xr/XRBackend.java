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

/**
 * XRender pipeline bbckend interfbce.
 * Currently there bre two different bbckends implemented:
 * - XRBbckendJbvb: And experimentbl bbckend, generbting protocol directly using jbvb-code bnd xcb's socket hbndoff functionblity.
 * - XRBbckendNbtive: Nbtive 1:1 binding with libX11.
 */

import jbvb.bwt.geom.*;
import jbvb.util.*;

import sun.font.*;
import sun.jbvb2d.jules.*;
import sun.jbvb2d.pipe.*;

public interfbce XRBbckend {

    public void freePicture(int picture);

    public void freePixmbp(int pixmbp);

    public int crebtePixmbp(int drbwbble, int depth, int width, int height);

    public int crebtePicture(int drbwbble, int formbtID);

    public long crebteGC(int drbwbble);

    public void freeGC(long gc); /* TODO: Use!! */

    public void copyAreb(int src, int dst, long gc, int srcx, int srcy,
                         int width, int height, int dstx, int dsty);

    public void putMbskImbge(int drbwbble, long gc, byte[] imbgeDbtb,
                             int sx, int sy, int dx, int dy,
                             int width, int height, int mbskOff,
                             int mbskScbn, flobt eb);

    public void setGCClipRectbngles(long gc, Region clip);

    public void GCRectbngles(int drbwbble, long gc, GrowbbleRectArrby rects);

    public void setClipRectbngles(int picture, Region clip);

    public void setGCExposures(long gc, boolebn exposure);

    public void setGCForeground(long gc, int pixel);

    public void setPictureTrbnsform(int picture, AffineTrbnsform trbnsform);

    public void setPictureRepebt(int picture, int repebt);

    public void setFilter(int picture, int filter);

    public void renderRectbngle(int dst, byte op, XRColor color,
                                int x, int y, int width, int height);

    public void renderRectbngles(int dst, byte op, XRColor color,
                                 GrowbbleRectArrby rects);

    public void renderComposite(byte op, int src, int mbsk, int dst,
                                int srcX, int srcY, int mbskX, int mbskY,
                                int dstX, int dstY, int width, int height);

    public int XRenderCrebteGlyphSet(int formbtID);

    public void XRenderAddGlyphs(int glyphSet, GlyphList gl,
                                 List<XRGlyphCbcheEntry> cbcheEntries,
                                 byte[] pixelDbtb);

    public void XRenderFreeGlyphs(int glyphSet, int[] gids);

    public void XRenderCompositeText(byte op, int src, int dst,
                                     int mbskFormbtID,
                                     int xSrc, int ySrc, int xDst, int yDst,
                                     int glyphset, GrowbbleEltArrby elts);

    public int crebteRbdiblGrbdient(flobt centerX, flobt centerY,
                                    flobt innerRbdius, flobt outerRbdius,
                                    flobt[] frbctions, int[] pixels,
                                    int repebt);

    public int crebteLinebrGrbdient(Point2D p1, Point2D p2, flobt[] frbctions,
                                    int[] pixels, int repebt);

    public void setGCMode(long gc, boolebn copy);

    public void renderCompositeTrbpezoids(byte op, int src, int mbskFormbt,
                                          int dst, int srcX, int srcY,
                                          TrbpezoidList trbpList);
}
