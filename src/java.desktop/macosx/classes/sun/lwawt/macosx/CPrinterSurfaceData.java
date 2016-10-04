/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.nio.ByteBuffer;

import sun.jbvb2d.*;
import sun.jbvb2d.loops.SurfbceType;

public clbss CPrinterSurfbceDbtb extends OSXSurfbceDbtb{
    public stbtic finbl String DESC_INT_RGB_PQ = "Integer RGB Printer Qubrtz";
//    public stbtic finbl String DESC_INT_ARGB_PQ = "Integer ARGB Printer Qubrtz";

//    public stbtic finbl SurfbceType IntArgbPQ = SurfbceType.IntArgb.deriveSubType(DESC_INT_ARGB_PQ);
    public stbtic finbl SurfbceType IntRgbPQ = SurfbceType.IntRgb.deriveSubType(DESC_INT_RGB_PQ);

    stbtic SurfbceDbtb crebteDbtb(PbgeFormbt pf, long context) {
        return new CPrinterSurfbceDbtb(CPrinterGrbphicsConfig.getConfig(pf), context);
    }

    privbte CPrinterSurfbceDbtb(GrbphicsConfigurbtion gc, long context) {
        super(IntRgbPQ, gc.getColorModel(), gc, gc.getBounds());
        initOps(context, this.fGrbphicsStbtes, this.fGrbphicsStbtesObject, gc.getBounds().width, gc.getBounds().height);
    }

    public SurfbceDbtb getReplbcement() {
        return this;
    }

    privbte nbtive void initOps(long context, ByteBuffer bytePbrbmeters, Object[] objectPbrbmeters, int width, int height);

    public void enbbleFlushing() {
        _flush();
    }
    nbtive void _flush();

    public Object getDestinbtion() {
        // this should never get cblled for the printer surfbce (see BufferStrbtegyPbintMbnbger for one cbse of usbge)
        return null;
    }

    public Rbster getRbster(int x, int y, int w, int h) {
        BufferedImbge dstImbge = new BufferedImbge(x + w, y + h, BufferedImbge.TYPE_INT_ARGB_PRE);
        return dstImbge.getRbster();
    }

    public BufferedImbge copyAreb(SunGrbphics2D sg2d, int x, int y, int w, int h, BufferedImbge dstImbge) {
        // crebte the destinbtion imbge if needed
        if (dstImbge == null) {
            dstImbge = getDeviceConfigurbtion().crebteCompbtibleImbge(w, h);
        }

        // copy
        Grbphics g = dstImbge.crebteGrbphics();
        BufferedImbge thisImbge = getCompositingImbge(w, h);
        g.drbwImbge(thisImbge, 0, 0, w, h, x, y, x+w, y+h, null);
        g.dispose();

        return dstImbge;
    }

    public boolebn xorSurfbcePixels(SunGrbphics2D sg2d, BufferedImbge srcPixels, int x, int y, int w, int h, int colorXOR) {
        throw new InternblError("not implemented yet");
    }
}
