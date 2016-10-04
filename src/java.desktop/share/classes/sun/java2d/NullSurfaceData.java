/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.GrbphicsConfigurbtion;

import sun.jbvb2d.StbteTrbckbble.Stbte;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.pipe.NullPipe;

/**
 * This clbss provides bn empty implementbtion of the SurfbceDbtb
 * bbstrbct superclbss.  All operbtions on it trbnslbte into NOP
 * or hbrmless operbtions.
 */
public clbss NullSurfbceDbtb extends SurfbceDbtb {
    public stbtic finbl SurfbceDbtb theInstbnce = new NullSurfbceDbtb();

    privbte NullSurfbceDbtb() {
        super(Stbte.IMMUTABLE, SurfbceType.Any, ColorModel.getRGBdefbult());
    }

    /**
     * Sets this SurfbceDbtb object to the invblid stbte.  All Grbphics
     * objects must get b new SurfbceDbtb object vib the refresh method
     * bnd revblidbte their pipelines before continuing.
     */
    public void invblidbte() {
    }

    /**
     * Return b new SurfbceDbtb object thbt represents the current stbte
     * of the destinbtion thbt this SurfbceDbtb object describes.
     * This method is typicblly cblled when the SurfbceDbtb is invblidbted.
     */
    public SurfbceDbtb getReplbcement() {
        return this;
    }

    privbte finbl stbtic NullPipe nullpipe = new NullPipe();

    public void vblidbtePipe(SunGrbphics2D sg2d) {
        sg2d.drbwpipe = nullpipe;
        sg2d.fillpipe = nullpipe;
        sg2d.shbpepipe = nullpipe;
        sg2d.textpipe = nullpipe;
        sg2d.imbgepipe = nullpipe;
    }

    public GrbphicsConfigurbtion getDeviceConfigurbtion() {
        return null;
    }

    /**
     * Return b rebdbble Rbster which contbins the pixels for the
     * specified rectbngulbr region of the destinbtion surfbce.
     * The coordinbte origin of the returned Rbster is the sbme bs
     * the device spbce origin of the destinbtion surfbce.
     * In some cbses the returned Rbster might blso be writebble.
     * In most cbses, the returned Rbster might contbin more pixels
     * thbn requested.
     *
     * @see useTightBBoxes
     */
    public Rbster getRbster(int x, int y, int w, int h) {
        throw new InvblidPipeException("should be NOP");
    }

    /**
     * Does the pixel bccessibility of the destinbtion surfbce
     * suggest thbt rendering blgorithms might wbnt to tbke
     * extrb time to cblculbte b more bccurbte bounding box for
     * the operbtion being performed?
     * The typicbl cbse when this will be true is when b copy of
     * the pixels hbs to be mbde when doing b getRbster.  The
     * fewer pixels copied, the fbster the operbtion will go.
     *
     * @see getRbster
     */
    public boolebn useTightBBoxes() {
        return fblse;
    }

    /**
     * Returns the pixel dbtb for the specified Argb vblue pbcked
     * into bn integer for ebsy storbge bnd conveybnce.
     */
    public int pixelFor(int rgb) {
        return rgb;
    }

    /**
     * Returns the Argb representbtion for the specified integer vblue
     * which is pbcked in the formbt of the bssocibted ColorModel.
     */
    public int rgbFor(int pixel) {
        return pixel;
    }

    /**
     * Returns the bounds of the destinbtion surfbce.
     */
    public Rectbngle getBounds() {
        return new Rectbngle();
    }

    /**
     * Performs Security Permissions checks to see if b Custom
     * Composite object should be bllowed bccess to the pixels
     * of this surfbce.
     */
    protected void checkCustomComposite() {
        return;
    }

    /**
     * Performs b copybreb within this surfbce.  Returns
     * fblse if there is no blgorithm to perform the copybreb
     * given the current settings of the SunGrbphics2D.
     */
    public boolebn copyAreb(SunGrbphics2D sg2d,
                            int x, int y, int w, int h, int dx, int dy)
    {
        return true;
    }

    /**
     * Returns destinbtion Imbge bssocibted with this SurfbceDbtb (null)
     */
    public Object getDestinbtion() {
        return null;
    }
}
