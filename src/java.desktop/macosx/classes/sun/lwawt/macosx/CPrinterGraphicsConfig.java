/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.print.*;

public clbss CPrinterGrbphicsConfig extends GrbphicsConfigurbtion {
    public stbtic CPrinterGrbphicsConfig getConfig(PbgeFormbt pf) {
        return new CPrinterGrbphicsConfig(pf);
    }

    GrbphicsDevice gd;
    PbgeFormbt pf;

    public CPrinterGrbphicsConfig(PbgeFormbt pf) {
        this.gd = new CPrinterDevice(this);
        this.pf = pf;
    }

    public PbgeFormbt getPbgeFormbt() {
        return pf;
    }

    /**
     * Returns the {@link GrbphicsDevice} bssocibted with this
     * <code>GrbphicsConfigurbtion</code>.
     * @return b <code>GrbphicsDevice</code> object thbt is
     * bssocibted with this <code>GrbphicsConfigurbtion</code>.
     */
    public GrbphicsDevice getDevice() {
        return gd;
    }

    /**
     * Returns b {@link BufferedImbge} with b dbtb lbyout bnd color model
     * compbtible with this <code>GrbphicsConfigurbtion</code>.  This
     * method hbs nothing to do with memory-mbpping
     * b device.  The returned <code>BufferedImbge</code> hbs
     * b lbyout bnd color model thbt is closest to this nbtive device
     * configurbtion bnd cbn therefore be optimblly blitted to this
     * device.
     * @pbrbm width the width of the returned <code>BufferedImbge</code>
     * @pbrbm height the height of the returned <code>BufferedImbge</code>
     * @return b <code>BufferedImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>.
     */
    public BufferedImbge crebteCompbtibleImbge(int width, int height) {
        return crebteCompbtibleImbge(width, height, Trbnspbrency.OPAQUE);
    }

    /**
     * Returns b {@link VolbtileImbge} with b dbtb lbyout bnd color model
     * compbtible with this <code>GrbphicsConfigurbtion</code>.
     * The returned <code>VolbtileImbge</code>
     * mby hbve dbtb thbt is stored optimblly for the underlying grbphics
     * device bnd mby therefore benefit from plbtform-specific rendering
     * bccelerbtion.
     * @pbrbm width the width of the returned <code>VolbtileImbge</code>
     * @pbrbm height the height of the returned <code>VolbtileImbge</code>
     * @return b <code>VolbtileImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>.
     * @see Component#crebteVolbtileImbge(int, int)
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height) {
        return crebteCompbtibleVolbtileImbge(width, height, Trbnspbrency.OPAQUE);
    }

    // empty implementbtion (this should not be cblled)
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height, int trbnspbrency) {
        return null;
    }

    /**
     * Returns b <code>BufferedImbge</code> thbt supports the specified
     * trbnspbrency bnd hbs b dbtb lbyout bnd color model
     * compbtible with this <code>GrbphicsConfigurbtion</code>.  This
     * method hbs nothing to do with memory-mbpping
     * b device. The returned <code>BufferedImbge</code> hbs b lbyout bnd
     * color model thbt cbn be optimblly blitted to b device
     * with this <code>GrbphicsConfigurbtion</code>.
     * @pbrbm width the width of the returned <code>BufferedImbge</code>
     * @pbrbm height the height of the returned <code>BufferedImbge</code>
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @return b <code>BufferedImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>
     * bnd blso supports the specified trbnspbrency.
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     */
    public BufferedImbge crebteCompbtibleImbge(int width, int height, int trbnspbrency) {
        //+++gdb whbt to do?
        return null;
    }

    /**
     * Returns the {@link ColorModel} bssocibted with this
     * <code>GrbphicsConfigurbtion</code>.
     * @return b <code>ColorModel</code> object thbt is bssocibted with
     * this <code>GrbphicsConfigurbtion</code>.
     */
    public ColorModel getColorModel() {
        return getColorModel(Trbnspbrency.OPAQUE);
    }

    /**
     * Returns the <code>ColorModel</code> bssocibted with this
     * <code>GrbphicsConfigurbtion</code> thbt supports the specified
     * trbnspbrency.
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @return b <code>ColorModel</code> object thbt is bssocibted with
     * this <code>GrbphicsConfigurbtion</code> bnd supports the
     * specified trbnspbrency.
     */
    public ColorModel getColorModel(int trbnspbrency) {
        return ColorModel.getRGBdefbult();
    }

    /**
     * Returns the defbult {@link AffineTrbnsform} for this
     * <code>GrbphicsConfigurbtion</code>. This
     * <code>AffineTrbnsform</code> is typicblly the Identity trbnsform
     * for most normbl screens.  The defbult <code>AffineTrbnsform</code>
     * mbps coordinbtes onto the device such thbt 72 user spbce
     * coordinbte units mebsure bpproximbtely 1 inch in device
     * spbce.  The normblizing trbnsform cbn be used to mbke
     * this mbpping more exbct.  Coordinbtes in the coordinbte spbce
     * defined by the defbult <code>AffineTrbnsform</code> for screen bnd
     * printer devices hbve the origin in the upper left-hbnd corner of
     * the tbrget region of the device, with X coordinbtes
     * increbsing to the right bnd Y coordinbtes increbsing downwbrds.
     * For imbge buffers not bssocibted with b device, such bs those not
     * crebted by <code>crebteCompbtibleImbge</code>,
     * this <code>AffineTrbnsform</code> is the Identity trbnsform.
     * @return the defbult <code>AffineTrbnsform</code> for this
     * <code>GrbphicsConfigurbtion</code>.
     */
    public AffineTrbnsform getDefbultTrbnsform() {
        return new AffineTrbnsform();
    }

    /**
     *
     * Returns b <code>AffineTrbnsform</code> thbt cbn be concbtenbted
     * with the defbult <code>AffineTrbnsform</code>
     * of b <code>GrbphicsConfigurbtion</code> so thbt 72 units in user
     * spbce equbls 1 inch in device spbce.
     * <p>
     * For b pbrticulbr {@link Grbphics2D}, g, one
     * cbn reset the trbnsformbtion to crebte
     * such b mbpping by using the following pseudocode:
     * <pre>
     *      GrbphicsConfigurbtion gc = g.getGrbphicsConfigurbtion();
     *
     *      g.setTrbnsform(gc.getDefbultTrbnsform());
     *      g.trbnsform(gc.getNormblizingTrbnsform());
     * </pre>
     * Note thbt sometimes this <code>AffineTrbnsform</code> is identity,
     * such bs for printers or metbfile output, bnd thbt this
     * <code>AffineTrbnsform</code> is only bs bccurbte bs the informbtion
     * supplied by the underlying system.  For imbge buffers not
     * bssocibted with b device, such bs those not crebted by
     * <code>crebteCompbtibleImbge</code>, this
     * <code>AffineTrbnsform</code> is the Identity trbnsform
     * since there is no vblid distbnce mebsurement.
     * @return bn <code>AffineTrbnsform</code> to concbtenbte to the
     * defbult <code>AffineTrbnsform</code> so thbt 72 units in user
     * spbce is mbpped to 1 inch in device spbce.
     */
    public AffineTrbnsform getNormblizingTrbnsform() {
        return new AffineTrbnsform();
    }

    /**
     * Returns the bounds of the <code>GrbphicsConfigurbtion</code>
     * in the device coordinbtes. In b multi-screen environment
     * with b virtubl device, the bounds cbn hbve negbtive X
     * or Y origins.
     * @return the bounds of the breb covered by this
     * <code>GrbphicsConfigurbtion</code>.
     * @since 1.3
     */
    public Rectbngle getBounds() {
        return new Rectbngle(0, 0, (int)pf.getWidth(), (int)pf.getHeight());
    }
}
