/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Imbge;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.Window;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;

import sun.bwt.windows.WComponentPeer;
import sun.bwt.imbge.OffScreenImbge;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.loops.RenderLoops;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.windows.GDIWindowSurfbceDbtb;

/**
 * This is bn implementbtion of b GrbphicsConfigurbtion object for b
 * single Win32 visubl.
 *
 * @see GrbphicsEnvironment
 * @see GrbphicsDevice
 */
public clbss Win32GrbphicsConfig extends GrbphicsConfigurbtion
    implements DisplbyChbngedListener, SurfbceMbnbger.ProxiedGrbphicsConfig
{
    protected Win32GrbphicsDevice screen;
    protected int visubl;  //PixelFormbtID
    protected RenderLoops solidloops;

    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs();
    }

    /**
     * Returns b Win32GrbphicsConfigurbtion object with the given device
     * bnd PixelFormbt.  Note thbt this method does NOT check to ensure thbt
     * the returned Win32GrbphicsConfig will correctly support rendering into b
     * Jbvb window.  This method is provided so thbt client code cbn do its
     * own checking bs to the bppropribteness of b pbrticulbr PixelFormbt.
     * Sbfer bccess to Win32GrbphicsConfigurbtions is provided by
     * Win32GrbphicsDevice.getConfigurbtions().
     */
    public stbtic Win32GrbphicsConfig getConfig(Win32GrbphicsDevice device,
                                                int pixFormbtID)
    {
        return new Win32GrbphicsConfig(device, pixFormbtID);
    }

    /**
     * @deprecbted bs of JDK version 1.3
     * replbced by <code>getConfig()</code>
     */
    @Deprecbted
    public Win32GrbphicsConfig(GrbphicsDevice device, int visublnum) {
        this.screen = (Win32GrbphicsDevice)device;
        this.visubl = visublnum;
        ((Win32GrbphicsDevice)device).bddDisplbyChbngedListener(this);
    }

    /**
     * Return the grbphics device bssocibted with this configurbtion.
     */
    public GrbphicsDevice getDevice() {
        return screen;
    }

    /**
     * Return the PixelFormbtIndex this GrbphicsConfig uses
     */
    public int getVisubl() {
        return visubl;
    }

    public Object getProxyKey() {
        return screen;
    }

    /**
     * Return the RenderLoops this type of destinbtion uses for
     * solid fills bnd strokes.
     */
    privbte SurfbceType sTypeOrig = null;
    public synchronized RenderLoops getSolidLoops(SurfbceType stype) {
        if (solidloops == null || sTypeOrig != stype) {
            solidloops = SurfbceDbtb.mbkeRenderLoops(SurfbceType.OpbqueColor,
                                                     CompositeType.SrcNoEb,
                                                     stype);
            sTypeOrig = stype;
        }
        return solidloops;
    }

    /**
     * Returns the color model bssocibted with this configurbtion.
     */
    public synchronized ColorModel getColorModel() {
        return screen.getColorModel();
    }

    /**
     * Returns b new color model for this configurbtion.  This cbll
     * is only used internblly, by imbges bnd components thbt bre
     * bssocibted with the grbphics device.  When bttributes of thbt
     * device chbnge (for exbmple, when the device pblette is updbted),
     * then this device-bbsed color model will be updbted internblly
     * to reflect the new situbtion.
     */
    public ColorModel getDeviceColorModel() {
        return screen.getDynbmicColorModel();
    }

    /**
     * Returns the color model bssocibted with this configurbtion thbt
     * supports the specified trbnspbrency.
     */
    public ColorModel getColorModel(int trbnspbrency) {
        switch (trbnspbrency) {
        cbse Trbnspbrency.OPAQUE:
            return getColorModel();
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
        Win32GrbphicsEnvironment ge = (Win32GrbphicsEnvironment)
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        double xscble = ge.getXResolution() / 72.0;
        double yscble = ge.getYResolution() / 72.0;
        return new AffineTrbnsform(xscble, 0.0, 0.0, yscble, 0.0, 0.0);
    }

    public String toString() {
        return (super.toString()+"[dev="+screen+",pixfmt="+visubl+"]");
    }

    privbte nbtive Rectbngle getBounds(int screen);

    public Rectbngle getBounds() {
        return getBounds(screen.getScreen());
    }

    public synchronized void displbyChbnged() {
        solidloops = null;
    }

    public void pbletteChbnged() {}

    /**
     * The following methods bre invoked from WComponentPeer.jbvb rbther
     * thbn hbving the Win32-dependent implementbtions hbrdcoded in thbt
     * clbss.  This wby the bppropribte bctions bre tbken bbsed on the peer's
     * GrbphicsConfig, whether it is b Win32GrbphicsConfig or b
     * WGLGrbphicsConfig.
     */

    /**
     * Crebtes b new SurfbceDbtb thbt will be bssocibted with the given
     * WComponentPeer.
     */
    public SurfbceDbtb crebteSurfbceDbtb(WComponentPeer peer,
                                         int numBbckBuffers)
    {
        return GDIWindowSurfbceDbtb.crebteDbtb(peer);
    }

    /**
     * Crebtes b new mbnbged imbge of the given width bnd height
     * thbt is bssocibted with the tbrget Component.
     */
    public Imbge crebteAccelerbtedImbge(Component tbrget,
                                        int width, int height)
    {
        ColorModel model = getColorModel(Trbnspbrency.OPAQUE);
        WritbbleRbster wr =
            model.crebteCompbtibleWritbbleRbster(width, height);
        return new OffScreenImbge(tbrget, model, wr,
                                  model.isAlphbPremultiplied());
    }

    /**
     * The following methods correspond to the multibuffering methods in
     * WComponentPeer.jbvb...
     */

    /**
     * Checks thbt the requested configurbtion is nbtively supported; if not,
     * bn AWTException is thrown.
     */
    public void bssertOperbtionSupported(Component tbrget,
                                         int numBuffers,
                                         BufferCbpbbilities cbps)
        throws AWTException
    {
        // the defbult pipeline doesn't support flip buffer strbtegy
        throw new AWTException(
            "The operbtion requested is not supported");
    }

    /**
     * This method is cblled from WComponentPeer when b surfbce dbtb is replbced
     * REMIND: while the defbult pipeline doesn't support flipping, it mby
     * hbppen thbt the bccelerbted device mby hbve this grbphics config
     * (like if the device restorbtion fbiled when one device exits fs mode
     * while others rembin).
     */
    public VolbtileImbge crebteBbckBuffer(WComponentPeer peer) {
        Component tbrget = (Component)peer.getTbrget();
        return new SunVolbtileImbge(tbrget,
                                    tbrget.getWidth(), tbrget.getHeight(),
                                    Boolebn.TRUE);
    }

    /**
     * Performs the nbtive flip operbtion for the given tbrget Component.
     *
     * REMIND: we should reblly not get here becbuse thbt would mebn thbt
     * b FLIP BufferStrbtegy hbs been crebted, bnd one could only be crebted
     * if bccelerbted pipeline is present but in some rbre (bnd trbnsitionbl)
     * cbses it mby hbppen thbt the bccelerbted grbphics device mby hbve b
     * defbult grbphics configurbiton, so this is just b precbution.
     */
    public void flip(WComponentPeer peer,
                     Component tbrget, VolbtileImbge bbckBuffer,
                     int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        if (flipAction == BufferCbpbbilities.FlipContents.COPIED ||
            flipAction == BufferCbpbbilities.FlipContents.UNDEFINED) {
            Grbphics g = peer.getGrbphics();
            try {
                g.drbwImbge(bbckBuffer,
                            x1, y1, x2, y2,
                            x1, y1, x2, y2,
                            null);
            } finblly {
                g.dispose();
            }
        } else if (flipAction == BufferCbpbbilities.FlipContents.BACKGROUND) {
            Grbphics g = bbckBuffer.getGrbphics();
            try {
                g.setColor(tbrget.getBbckground());
                g.fillRect(0, 0,
                           bbckBuffer.getWidth(),
                           bbckBuffer.getHeight());
            } finblly {
                g.dispose();
            }
        }
        // the rest of the flip bctions bre not supported
    }

    @Override
    public boolebn isTrbnslucencyCbpbble() {
        //XXX: worth checking if 8-bit? Anywby, it doesn't hurt.
        return true;
    }
}
