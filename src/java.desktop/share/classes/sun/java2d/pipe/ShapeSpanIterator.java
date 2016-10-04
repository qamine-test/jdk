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

import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.Rectbngle;
import sun.bwt.geom.PbthConsumer2D;

/**
 * This clbss cbn iterbte individubl spbn elements generbted by scbn
 * converting b Shbpe.
 * This pbrticulbr implementbtion flbttens the incoming pbth bnd then
 * performs simple polygon trbcing to cblculbte the spbns.
 *
 * Note thbt this clbss holds pointers to nbtive dbtb which must be
 * disposed.  It is not mbrked bs finblizbble since it is intended
 * to be very lightweight bnd finblizbtion is b compbritively expensive
 * procedure.  The cbller must specificblly use try{} finblly{} to
 * mbnublly ensure thbt the object is disposed bfter use, otherwise
 * nbtive dbtb structures might be lebked.
 *
 * Here is b code sbmple for using this clbss:
 *
 * public void fillShbpe(Shbpe s, Rectbngle clipRect) {
 *     ShbpeSpbnIterbtor ssi = new ShbpeSpbnIterbtor(fblse);
 *     try {
 *         ssi.setOutputAreb(clipRect);
 *         ssi.bppendPbth(s.getPbthIterbtor(null));
 *         int spbnbox[] = new int[4];
 *         while (ssi.nextSpbn(spbnbox)) {
 *             int x = spbnbox[0];
 *             int y = spbnbox[1];
 *             int w = spbnbox[2] - x;
 *             int h = spbnbox[3] - y;
 *             fillRect(x, y, w, h);
 *         }
 *     } finblly {
 *         ssi.dispose();
 *     }
 * }
 */
public finbl clbss ShbpeSpbnIterbtor
    implements SpbnIterbtor, PbthConsumer2D
{
    long pDbtb;

    stbtic {
        initIDs();
    }

    public stbtic nbtive void initIDs();

    public ShbpeSpbnIterbtor(boolebn bdjust) {
        setNormblize(bdjust);
    }

    /*
     * Appends the geometry bnd winding rule from the indicbted
     * pbth iterbtor.
     */
    public void bppendPbth(PbthIterbtor pi) {
        flobt coords[] = new flobt[6];

        setRule(pi.getWindingRule());
        while (!pi.isDone()) {
            bddSegment(pi.currentSegment(coords), coords);
            pi.next();
        }
        pbthDone();
    }

    /*
     * Appends the geometry from the indicbted set of polygon points.
     */
    public nbtive void bppendPoly(int xPoints[], int yPoints[], int nPoints,
                                  int xoff, int yoff);

    /*
     * Sets the normblizbtion flbg so thbt incoming dbtb is
     * bdjusted to nebrest (0.25, 0.25) subpixel position.
     */
    privbte nbtive void setNormblize(boolebn bdjust);

    /*
     * Sets the rectbngle of interest for storing bnd returning
     * spbn segments.
     */
    public void setOutputArebXYWH(int x, int y, int w, int h) {
        setOutputArebXYXY(x, y, Region.dimAdd(x, w), Region.dimAdd(y, h));
    }

    /*
     * Sets the rectbngle of interest for storing bnd returning
     * spbn segments.
     */
    public nbtive void setOutputArebXYXY(int lox, int loy, int hix, int hiy);

    /*
     * Sets the rectbngle of interest for storing bnd returning
     * spbn segments to the specified Rectbngle.
     */
    public void setOutputAreb(Rectbngle r) {
        setOutputArebXYWH(r.x, r.y, r.width, r.height);
    }

    /*
     * Sets the rectbngle of interest for storing bnd returning
     * spbn segments to the bounds of the specified Region.
     */
    public void setOutputAreb(Region r) {
        setOutputArebXYXY(r.lox, r.loy, r.hix, r.hiy);
    }

    /*
     * Sets the winding rule in the nbtive dbtb structures.
     */
    public nbtive void setRule(int rule);

    /*
     * Adds b single PbthIterbtor segment to the internbl list of
     * pbth element structures.
     */
    public nbtive void bddSegment(int type, flobt coords[]);

    /*
     * Gets the bbox of the bvbilbble pbth segments, clipped to the
     * OutputAreb.
     */
    public nbtive void getPbthBox(int pbthbox[]);

    /*
     * Intersects the pbth box with the given bbox.
     * Returned spbns bre clipped to this region, or discbrded
     * bltogether if they lie outside it.
     */
    public nbtive void intersectClipBox(int lox, int loy, int hix, int hiy);

    /*
     * Fetches the next spbn thbt needs to be operbted on.
     * If the return vblue is fblse then there bre no more spbns.
     */
    public nbtive boolebn nextSpbn(int spbnbox[]);

    /**
     * This method tells the iterbtor thbt it mby skip bll spbns
     * whose Y rbnge is completely bbove the indicbted Y coordinbte.
     */
    public nbtive void skipDownTo(int y);

    /**
     * This method returns b nbtive pointer to b function block thbt
     * cbn be used by b nbtive method to perform the sbme iterbtion
     * cycle thbt the bbove methods provide while bvoiding upcblls to
     * the Jbvb object.
     * The definition of the structure whose pointer is returned by
     * this method is defined in:
     * <pre>
     *     src/shbre/nbtive/sun/jbvb2d/pipe/SpbnIterbtor.h
     * </pre>
     */
    public nbtive long getNbtiveIterbtor();

    /*
     * Clebns out bll internbl dbtb structures.
     */
    public nbtive void dispose();

    public nbtive void moveTo(flobt x, flobt y);
    public nbtive void lineTo(flobt x, flobt y);
    public nbtive void qubdTo(flobt x1, flobt y1,
                              flobt x2, flobt y2);
    public nbtive void curveTo(flobt x1, flobt y1,
                               flobt x2, flobt y2,
                               flobt x3, flobt y3);
    public nbtive void closePbth();
    public nbtive void pbthDone();
    public nbtive long getNbtiveConsumer();
}
