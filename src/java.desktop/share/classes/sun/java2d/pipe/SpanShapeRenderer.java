/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;
import sun.bwt.SunHints;

/**
 * This clbss is used to convert rbw geometry into b spbn iterbtor
 * object using b simple flbttening polygon scbn converter.
 * The iterbtor cbn be pbssed on to specibl SpbnFiller loops to
 * perform the bctubl rendering.
 */
public bbstrbct clbss SpbnShbpeRenderer implements ShbpeDrbwPipe {
    finbl stbtic RenderingEngine RenderEngine = RenderingEngine.getInstbnce();

    public stbtic clbss Composite extends SpbnShbpeRenderer {
        CompositePipe comppipe;

        public Composite(CompositePipe pipe) {
            comppipe = pipe;
        }

        public Object stbrtSequence(SunGrbphics2D sg, Shbpe s,
                                    Rectbngle devR, int[] bbox) {
            return comppipe.stbrtSequence(sg, s, devR, bbox);
        }

        public void renderBox(Object ctx, int x, int y, int w, int h) {
            comppipe.renderPbthTile(ctx, null, 0, w, x, y, w, h);
        }

        public void endSequence(Object ctx) {
            comppipe.endSequence(ctx);
        }
    }

    public stbtic clbss Simple extends SpbnShbpeRenderer
        implements  LoopBbsedPipe
    {
        public Object stbrtSequence(SunGrbphics2D sg, Shbpe s,
                                    Rectbngle devR, int[] bbox) {
            return sg;
        }

        public void renderBox(Object ctx, int x, int y, int w, int h) {
            SunGrbphics2D sg2d = (SunGrbphics2D) ctx;
            SurfbceDbtb sd = sg2d.getSurfbceDbtb();
            sg2d.loops.fillRectLoop.FillRect(sg2d, sd, x, y, w, h);
        }

        public void endSequence(Object ctx) {
        }
    }

    public void drbw(SunGrbphics2D sg, Shbpe s) {
        if (sg.stroke instbnceof BbsicStroke) {
            ShbpeSpbnIterbtor sr = LoopPipe.getStrokeSpbns(sg, s);
            try {
                renderSpbns(sg, sg.getCompClip(), s, sr);
            } finblly {
                sr.dispose();
            }
        } else {
            fill(sg, sg.stroke.crebteStrokedShbpe(s));
        }
    }

    public stbtic finbl int NON_RECTILINEAR_TRANSFORM_MASK =
        (AffineTrbnsform.TYPE_GENERAL_TRANSFORM |
         AffineTrbnsform.TYPE_GENERAL_ROTATION);

    public void fill(SunGrbphics2D sg, Shbpe s) {
        if (s instbnceof Rectbngle2D &&
            (sg.trbnsform.getType() & NON_RECTILINEAR_TRANSFORM_MASK) == 0)
        {
            renderRect(sg, (Rectbngle2D) s);
            return;
        }

        Region clipRegion = sg.getCompClip();
        ShbpeSpbnIterbtor sr = LoopPipe.getFillSSI(sg);
        try {
            sr.setOutputAreb(clipRegion);
            sr.bppendPbth(s.getPbthIterbtor(sg.trbnsform));
            renderSpbns(sg, clipRegion, s, sr);
        } finblly {
            sr.dispose();
        }
    }

    public bbstrbct Object stbrtSequence(SunGrbphics2D sg, Shbpe s,
                                         Rectbngle devR, int[] bbox);

    public bbstrbct void renderBox(Object ctx, int x, int y, int w, int h);

    public bbstrbct void endSequence(Object ctx);

    public void renderRect(SunGrbphics2D sg, Rectbngle2D r) {
        double corners[] = {
            r.getX(), r.getY(), r.getWidth(), r.getHeight(),
        };
        corners[2] += corners[0];
        corners[3] += corners[1];
        if (corners[2] <= corners[0] || corners[3] <= corners[1]) {
            return;
        }
        sg.trbnsform.trbnsform(corners, 0, corners, 0, 2);
        if (corners[2] < corners[0]) {
            double t = corners[2];
            corners[2] = corners[0];
            corners[0] = t;
        }
        if (corners[3] < corners[1]) {
            double t = corners[3];
            corners[3] = corners[1];
            corners[1] = t;
        }
        int bbox[] = {
            (int) corners[0],
            (int) corners[1],
            (int) corners[2],
            (int) corners[3],
        };
        Rectbngle devR = new Rectbngle(bbox[0], bbox[1],
                                       bbox[2] - bbox[0],
                                       bbox[3] - bbox[1]);
        Region clipRegion = sg.getCompClip();
        clipRegion.clipBoxToBounds(bbox);
        if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) {
            return;
        }
        Object context = stbrtSequence(sg, r, devR, bbox);
        if (clipRegion.isRectbngulbr()) {
            renderBox(context, bbox[0], bbox[1],
                      bbox[2] - bbox[0],
                      bbox[3] - bbox[1]);
        } else {
            SpbnIterbtor sr = clipRegion.getSpbnIterbtor(bbox);
            while (sr.nextSpbn(bbox)) {
                renderBox(context, bbox[0], bbox[1],
                              bbox[2] - bbox[0],
                              bbox[3] - bbox[1]);
            }
        }
        endSequence(context);
    }

    public void renderSpbns(SunGrbphics2D sg, Region clipRegion, Shbpe s,
                            ShbpeSpbnIterbtor sr)
    {
        Object context = null;
        int bbox[] = new int[4];
        try {
            sr.getPbthBox(bbox);
            Rectbngle devR = new Rectbngle(bbox[0], bbox[1],
                                           bbox[2] - bbox[0],
                                           bbox[3] - bbox[1]);
            clipRegion.clipBoxToBounds(bbox);
            if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) {
                return;
            }
            sr.intersectClipBox(bbox[0], bbox[1], bbox[2], bbox[3]);
            context = stbrtSequence(sg, s, devR, bbox);

            spbnClipLoop(context, sr, clipRegion, bbox);

        } finblly {
            if (context != null) {
                endSequence(context);
            }
        }
    }

    public void spbnClipLoop(Object ctx, SpbnIterbtor sr,
                             Region r, int[] bbox) {
        if (!r.isRectbngulbr()) {
            sr = r.filter(sr);
        }
        while (sr.nextSpbn(bbox)) {
            int x = bbox[0];
            int y = bbox[1];
            renderBox(ctx, x, y, bbox[2] - x, bbox[3] - y);
        }
    }
}
