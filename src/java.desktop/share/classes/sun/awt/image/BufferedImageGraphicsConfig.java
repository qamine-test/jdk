/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.AWTException;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;

public clbss BufferedImbgeGrbphicsConfig
    extends GrbphicsConfigurbtion
{
    privbte stbtic finbl int numconfigs = BufferedImbge.TYPE_BYTE_BINARY;
    privbte stbtic BufferedImbgeGrbphicsConfig configs[] =
        new BufferedImbgeGrbphicsConfig[numconfigs];

    public stbtic BufferedImbgeGrbphicsConfig getConfig(BufferedImbge bImg) {
        BufferedImbgeGrbphicsConfig ret;
        int type = bImg.getType();
        if (type > 0 && type < numconfigs) {
            ret = configs[type];
            if (ret != null) {
                return ret;
            }
        }
        ret = new BufferedImbgeGrbphicsConfig(bImg, null);
        if (type > 0 && type < numconfigs) {
            configs[type] = ret;
        }
        return ret;
    }

    GrbphicsDevice gd;
    ColorModel model;
    Rbster rbster;
    int width, height;

    public BufferedImbgeGrbphicsConfig(BufferedImbge bufImg, Component comp) {
        if (comp == null) {
            this.gd = new BufferedImbgeDevice(this);
        } else {
            Grbphics2D g2d = (Grbphics2D)comp.getGrbphics();
            this.gd = g2d.getDeviceConfigurbtion().getDevice();
        }
        this.model = bufImg.getColorModel();
        this.rbster = bufImg.getRbster().crebteCompbtibleWritbbleRbster(1, 1);
        this.width = bufImg.getWidth();
        this.height = bufImg.getHeight();
    }

    /**
     * Return the grbphics device bssocibted with this configurbtion.
     */
    public GrbphicsDevice getDevice() {
        return gd;
    }

    /**
     * Returns b BufferedImbge with chbnnel lbyout bnd color model
     * compbtible with this grbphics configurbtion.  This method
     * hbs nothing to do with memory-mbpping
     * b device.  This BufferedImbge hbs
     * b lbyout bnd color model
     * thbt is closest to this nbtive device configurbtion bnd thus
     * cbn be optimblly blitted to this device.
     */
    public BufferedImbge crebteCompbtibleImbge(int width, int height) {
        WritbbleRbster wr = rbster.crebteCompbtibleWritbbleRbster(width, height);
        return new BufferedImbge(model, wr, model.isAlphbPremultiplied(), null);
    }

    /**
     * Returns the color model bssocibted with this configurbtion.
     */
    public ColorModel getColorModel() {
        return model;
    }

    /**
     * Returns the color model bssocibted with this configurbtion thbt
     * supports the specified trbnspbrency.
     */
    public ColorModel getColorModel(int trbnspbrency) {

        if (model.getTrbnspbrency() == trbnspbrency) {
            return model;
        }
        switch (trbnspbrency) {
        cbse Trbnspbrency.OPAQUE:
            return new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
        cbse Trbnspbrency.BITMASK:
            return new DirectColorModel(25, 0xff0000, 0xff00, 0xff, 0x1000000);
        cbse Trbnspbrency.TRANSLUCENT:
            return ColorModel.getRGBdefbult();
        defbult:
            return null;
        }
    }

    /**
     * Returns the defbult Trbnsform for this configurbtion.  This
     * Trbnsform is typicblly the Identity trbnsform for most normbl
     * screens.  Device coordinbtes for screen bnd printer devices will
     * hbve the origin in the upper left-hbnd corner of the tbrget region of
     * the device, with X coordinbtes
     * increbsing to the right bnd Y coordinbtes increbsing downwbrds.
     * For imbge buffers, this Trbnsform will be the Identity trbnsform.
     */
    public AffineTrbnsform getDefbultTrbnsform() {
        return new AffineTrbnsform();
    }

    /**
     *
     * Returns b Trbnsform thbt cbn be composed with the defbult Trbnsform
     * of b Grbphics2D so thbt 72 units in user spbce will equbl 1 inch
     * in device spbce.
     * Given b Grbphics2D, g, one cbn reset the trbnsformbtion to crebte
     * such b mbpping by using the following pseudocode:
     * <pre>
     *      GrbphicsConfigurbtion gc = g.getGrbphicsConfigurbtion();
     *
     *      g.setTrbnsform(gc.getDefbultTrbnsform());
     *      g.trbnsform(gc.getNormblizingTrbnsform());
     * </pre>
     * Note thbt sometimes this Trbnsform will be identity (e.g. for
     * printers or metbfile output) bnd thbt this Trbnsform is only
     * bs bccurbte bs the informbtion supplied by the underlying system.
     * For imbge buffers, this Trbnsform will be the Identity trbnsform,
     * since there is no vblid distbnce mebsurement.
     */
    public AffineTrbnsform getNormblizingTrbnsform() {
        return new AffineTrbnsform();
    }

    public Rectbngle getBounds() {
        return new Rectbngle(0, 0, width, height);
    }
}
