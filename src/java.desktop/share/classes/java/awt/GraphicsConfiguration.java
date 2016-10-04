/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;

import sun.bwt.imbge.SunVolbtileImbge;

/**
 * The <code>GrbphicsConfigurbtion</code> clbss describes the
 * chbrbcteristics of b grbphics destinbtion such bs b printer or monitor.
 * There cbn be mbny <code>GrbphicsConfigurbtion</code> objects bssocibted
 * with b single grbphics device, representing different drbwing modes or
 * cbpbbilities.  The corresponding nbtive structure will vbry from plbtform
 * to plbtform.  For exbmple, on X11 windowing systems,
 * ebch visubl is b different <code>GrbphicsConfigurbtion</code>.
 * On Microsoft Windows, <code>GrbphicsConfigurbtion</code>s represent
 * PixelFormbts bvbilbble in the current resolution bnd color depth.
 * <p>
 * In b virtubl device multi-screen environment in which the desktop
 * breb could spbn multiple physicbl screen devices, the bounds of the
 * <code>GrbphicsConfigurbtion</code> objects bre relbtive to the
 * virtubl coordinbte system.  When setting the locbtion of b
 * component, use {@link #getBounds() getBounds} to get the bounds of
 * the desired <code>GrbphicsConfigurbtion</code> bnd offset the locbtion
 * with the coordinbtes of the <code>GrbphicsConfigurbtion</code>,
 * bs the following code sbmple illustrbtes:
 * </p>
 *
 * <pre>
 *      Frbme f = new Frbme(gc);  // where gc is b GrbphicsConfigurbtion
 *      Rectbngle bounds = gc.getBounds();
 *      f.setLocbtion(10 + bounds.x, 10 + bounds.y); </pre>
 *
 * <p>
 * To determine if your environment is b virtubl device
 * environment, cbll <code>getBounds</code> on bll of the
 * <code>GrbphicsConfigurbtion</code> objects in your system.  If
 * bny of the origins of the returned bounds is not (0,&nbsp;0),
 * your environment is b virtubl device environment.
 *
 * <p>
 * You cbn blso use <code>getBounds</code> to determine the bounds
 * of the virtubl device.  To do this, first cbll <code>getBounds</code> on bll
 * of the <code>GrbphicsConfigurbtion</code> objects in your
 * system.  Then cblculbte the union of bll of the bounds returned
 * from the cblls to <code>getBounds</code>.  The union is the
 * bounds of the virtubl device.  The following code sbmple
 * cblculbtes the bounds of the virtubl device.
 *
 * <pre>{@code
 *      Rectbngle virtublBounds = new Rectbngle();
 *      GrbphicsEnvironment ge = GrbphicsEnvironment.
 *              getLocblGrbphicsEnvironment();
 *      GrbphicsDevice[] gs =
 *              ge.getScreenDevices();
 *      for (int j = 0; j < gs.length; j++) {
 *          GrbphicsDevice gd = gs[j];
 *          GrbphicsConfigurbtion[] gc =
 *              gd.getConfigurbtions();
 *          for (int i=0; i < gc.length; i++) {
 *              virtublBounds =
 *                  virtublBounds.union(gc[i].getBounds());
 *          }
 *      } }</pre>
 *
 * @see Window
 * @see Frbme
 * @see GrbphicsEnvironment
 * @see GrbphicsDevice
 */
/*
 * REMIND:  Whbt to do bbout cbpbbilities?
 * The
 * cbpbbilities of the device cbn be determined by enumerbting the possible
 * cbpbbilities bnd checking if the GrbphicsConfigurbtion
 * implements the interfbce for thbt cbpbbility.
 *
 */


public bbstrbct clbss GrbphicsConfigurbtion {

    privbte stbtic BufferCbpbbilities defbultBufferCbps;
    privbte stbtic ImbgeCbpbbilities defbultImbgeCbps;

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Instbnces must be obtbined from b suitbble fbctory or query method.
     *
     * @see GrbphicsDevice#getConfigurbtions
     * @see GrbphicsDevice#getDefbultConfigurbtion
     * @see GrbphicsDevice#getBestConfigurbtion
     * @see Grbphics2D#getDeviceConfigurbtion
     */
    protected GrbphicsConfigurbtion() {
    }

    /**
     * Returns the {@link GrbphicsDevice} bssocibted with this
     * <code>GrbphicsConfigurbtion</code>.
     * @return b <code>GrbphicsDevice</code> object thbt is
     * bssocibted with this <code>GrbphicsConfigurbtion</code>.
     */
    public bbstrbct GrbphicsDevice getDevice();

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
        ColorModel model = getColorModel();
        WritbbleRbster rbster =
            model.crebteCompbtibleWritbbleRbster(width, height);
        return new BufferedImbge(model, rbster,
                                 model.isAlphbPremultiplied(), null);
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
     * @throws IllegblArgumentException if the trbnspbrency is not b vblid vblue
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     */
    public BufferedImbge crebteCompbtibleImbge(int width, int height,
                                               int trbnspbrency)
    {
        if (getColorModel().getTrbnspbrency() == trbnspbrency) {
            return crebteCompbtibleImbge(width, height);
        }

        ColorModel cm = getColorModel(trbnspbrency);
        if (cm == null) {
            throw new IllegblArgumentException("Unknown trbnspbrency: " +
                                               trbnspbrency);
        }
        WritbbleRbster wr = cm.crebteCompbtibleWritbbleRbster(width, height);
        return new BufferedImbge(cm, wr, cm.isAlphbPremultiplied(), null);
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
     * @since 1.4
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height) {
        VolbtileImbge vi = null;
        try {
            vi = crebteCompbtibleVolbtileImbge(width, height,
                                               null, Trbnspbrency.OPAQUE);
        } cbtch (AWTException e) {
            // shouldn't hbppen: we're pbssing in null cbps
            bssert fblse;
        }
        return vi;
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
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @return b <code>VolbtileImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>.
     * @throws IllegblArgumentException if the trbnspbrency is not b vblid vblue
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     * @see Component#crebteVolbtileImbge(int, int)
     * @since 1.5
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height,
                                                       int trbnspbrency)
    {
        VolbtileImbge vi = null;
        try {
            vi = crebteCompbtibleVolbtileImbge(width, height, null, trbnspbrency);
        } cbtch (AWTException e) {
            // shouldn't hbppen: we're pbssing in null cbps
            bssert fblse;
        }
        return vi;
    }

    /**
     * Returns b {@link VolbtileImbge} with b dbtb lbyout bnd color model
     * compbtible with this <code>GrbphicsConfigurbtion</code>, using
     * the specified imbge cbpbbilities.
     * If the <code>cbps</code> pbrbmeter is null, it is effectively ignored
     * bnd this method will crebte b VolbtileImbge without regbrd to
     * <code>ImbgeCbpbbilities</code> constrbints.
     *
     * The returned <code>VolbtileImbge</code> hbs
     * b lbyout bnd color model thbt is closest to this nbtive device
     * configurbtion bnd cbn therefore be optimblly blitted to this
     * device.
     * @return b <code>VolbtileImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>.
     * @pbrbm width the width of the returned <code>VolbtileImbge</code>
     * @pbrbm height the height of the returned <code>VolbtileImbge</code>
     * @pbrbm cbps the imbge cbpbbilities
     * @exception AWTException if the supplied imbge cbpbbilities could not
     * be met by this grbphics configurbtion
     * @since 1.4
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height,
        ImbgeCbpbbilities cbps) throws AWTException
    {
        return crebteCompbtibleVolbtileImbge(width, height, cbps,
                                             Trbnspbrency.OPAQUE);
    }

    /**
     * Returns b {@link VolbtileImbge} with b dbtb lbyout bnd color model
     * compbtible with this <code>GrbphicsConfigurbtion</code>, using
     * the specified imbge cbpbbilities bnd trbnspbrency vblue.
     * If the <code>cbps</code> pbrbmeter is null, it is effectively ignored
     * bnd this method will crebte b VolbtileImbge without regbrd to
     * <code>ImbgeCbpbbilities</code> constrbints.
     *
     * The returned <code>VolbtileImbge</code> hbs
     * b lbyout bnd color model thbt is closest to this nbtive device
     * configurbtion bnd cbn therefore be optimblly blitted to this
     * device.
     * @pbrbm width the width of the returned <code>VolbtileImbge</code>
     * @pbrbm height the height of the returned <code>VolbtileImbge</code>
     * @pbrbm cbps the imbge cbpbbilities
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @return b <code>VolbtileImbge</code> whose dbtb lbyout bnd color
     * model is compbtible with this <code>GrbphicsConfigurbtion</code>.
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     * @throws IllegblArgumentException if the trbnspbrency is not b vblid vblue
     * @exception AWTException if the supplied imbge cbpbbilities could not
     * be met by this grbphics configurbtion
     * @see Component#crebteVolbtileImbge(int, int)
     * @since 1.5
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height,
        ImbgeCbpbbilities cbps, int trbnspbrency) throws AWTException
    {
        VolbtileImbge vi =
            new SunVolbtileImbge(this, width, height, trbnspbrency, cbps);
        if (cbps != null && cbps.isAccelerbted() &&
            !vi.getCbpbbilities().isAccelerbted())
        {
            throw new AWTException("Supplied imbge cbpbbilities could not " +
                                   "be met by this grbphics configurbtion.");
        }
        return vi;
    }

    /**
     * Returns the {@link ColorModel} bssocibted with this
     * <code>GrbphicsConfigurbtion</code>.
     * @return b <code>ColorModel</code> object thbt is bssocibted with
     * this <code>GrbphicsConfigurbtion</code>.
     */
    public bbstrbct ColorModel getColorModel();

    /**
     * Returns the <code>ColorModel</code> bssocibted with this
     * <code>GrbphicsConfigurbtion</code> thbt supports the specified
     * trbnspbrency.
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @return b <code>ColorModel</code> object thbt is bssocibted with
     * this <code>GrbphicsConfigurbtion</code> bnd supports the
     * specified trbnspbrency or null if the trbnspbrency is not b vblid
     * vblue.
     * @see Trbnspbrency#OPAQUE
     * @see Trbnspbrency#BITMASK
     * @see Trbnspbrency#TRANSLUCENT
     */
    public bbstrbct ColorModel getColorModel(int trbnspbrency);

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
    public bbstrbct AffineTrbnsform getDefbultTrbnsform();

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
     *      GrbphicsConfigurbtion gc = g.getDeviceConfigurbtion();
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
    public bbstrbct AffineTrbnsform getNormblizingTrbnsform();

    /**
     * Returns the bounds of the <code>GrbphicsConfigurbtion</code>
     * in the device coordinbtes. In b multi-screen environment
     * with b virtubl device, the bounds cbn hbve negbtive X
     * or Y origins.
     * @return the bounds of the breb covered by this
     * <code>GrbphicsConfigurbtion</code>.
     * @since 1.3
     */
    public bbstrbct Rectbngle getBounds();

    privbte stbtic clbss DefbultBufferCbpbbilities extends BufferCbpbbilities {
        public DefbultBufferCbpbbilities(ImbgeCbpbbilities imbgeCbps) {
            super(imbgeCbps, imbgeCbps, null);
        }
    }

    /**
     * Returns the buffering cbpbbilities of this
     * <code>GrbphicsConfigurbtion</code>.
     * @return the buffering cbpbbilities of this grbphics
     * configurbtion object
     * @since 1.4
     */
    public BufferCbpbbilities getBufferCbpbbilities() {
        if (defbultBufferCbps == null) {
            defbultBufferCbps = new DefbultBufferCbpbbilities(
                getImbgeCbpbbilities());
        }
        return defbultBufferCbps;
    }

    /**
     * Returns the imbge cbpbbilities of this
     * <code>GrbphicsConfigurbtion</code>.
     * @return the imbge cbpbbilities of this grbphics
     * configurbtion object
     * @since 1.4
     */
    public ImbgeCbpbbilities getImbgeCbpbbilities() {
        if (defbultImbgeCbps == null) {
            defbultImbgeCbps = new ImbgeCbpbbilities(fblse);
        }
        return defbultImbgeCbps;
    }

    /**
     * Returns whether this {@code GrbphicsConfigurbtion} supports
     * the {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     * PERPIXEL_TRANSLUCENT} kind of trbnslucency.
     *
     * @return whether the given GrbphicsConfigurbtion supports
     *         the trbnslucency effects.
     *
     * @see Window#setBbckground(Color)
     *
     * @since 1.7
     */
    public boolebn isTrbnslucencyCbpbble() {
        // Overridden in subclbsses
        return fblse;
    }
}
