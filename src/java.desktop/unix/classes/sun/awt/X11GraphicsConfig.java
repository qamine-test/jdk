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

pbckbge sun.bwt;

import jbvb.bwt.AWTException;
import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.BufferCbpbbilities.FlipContents;
import jbvb.bwt.Component;
import jbvb.bwt.Toolkit;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.Imbge;
import jbvb.bwt.ImbgeCbpbbilities;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.Rectbngle;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.RenderLoops;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.x11.X11SurfbceDbtb;
import sun.bwt.imbge.OffScreenImbge;
import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.X11ComponentPeer;

/**
 * This is bn implementbtion of b GrbphicsConfigurbtion object for b
 * single X11 visubl.
 *
 * @see GrbphicsEnvironment
 * @see GrbphicsDevice
 */
public clbss X11GrbphicsConfig extends GrbphicsConfigurbtion
    implements SurfbceMbnbger.ProxiedGrbphicsConfig
{
    protected X11GrbphicsDevice screen;
    protected int visubl;
    int depth;
    int colormbp;
    ColorModel colorModel;
    long bDbtb;
    boolebn doubleBuffer;
    privbte Object disposerReferent = new Object();
    privbte BufferCbpbbilities bufferCbps;
    privbte stbtic ImbgeCbpbbilities imbgeCbps =
        new ImbgeCbpbbilities(X11SurfbceDbtb.isAccelerbtionEnbbled());

    // will be set on nbtive level from init()
    protected int bitsPerPixel;

    protected SurfbceType surfbceType;

    public RenderLoops solidloops;

    public stbtic X11GrbphicsConfig getConfig(X11GrbphicsDevice device,
                                              int visublnum, int depth,
                                              int colormbp,
                                              boolebn doubleBuffer)
    {
        return new X11GrbphicsConfig(device, visublnum, depth, colormbp, doubleBuffer);
    }

    /*
     * Note this method is currently here for bbckwbrd compbtibility
     * bs this wbs the method used in jdk 1.2 betb4 to crebte the
     * X11GrbphicsConfig objects. Jbvb3D code hbd cblled this method
     * explicitly so without this, if b user tries to use JDK1.2 fcs
     * with Jbvb3D betb1, b NoSuchMethod execption is thrown bnd
     * the progrbm exits. REMOVE this method bfter Jbvb3D fcs is
     * relebsed!
     */
    public stbtic X11GrbphicsConfig getConfig(X11GrbphicsDevice device,
                                              int visublnum, int depth,
                                              int colormbp, int type)
    {
        return new X11GrbphicsConfig(device, visublnum, depth, colormbp, fblse);
    }

    privbte nbtive int getNumColors();
    privbte nbtive void init(int visublNum, int screen);
    privbte nbtive ColorModel mbkeColorModel();

    protected X11GrbphicsConfig(X11GrbphicsDevice device,
                                int visublnum, int depth,
                                int colormbp, boolebn doubleBuffer)
    {
        this.screen = device;
        this.visubl = visublnum;
        this.doubleBuffer = doubleBuffer;
        this.depth = depth;
        this.colormbp = colormbp;
        init (visublnum, screen.getScreen());

        // bdd b record to the Disposer so thbt we destroy the nbtive
        // AwtGrbphicsConfigDbtb when this object goes bwby (i.e. bfter b
        // displby chbnge event)
        long x11CfgDbtb = getADbtb();
        Disposer.bddRecord(disposerReferent,
                           new X11GCDisposerRecord(x11CfgDbtb));
    }

    /**
     * Return the grbphics device bssocibted with this configurbtion.
     */
    public GrbphicsDevice getDevice() {
        return screen;
    }

    /**
     * Returns the visubl id bssocibted with this configurbtion.
     */
    public int getVisubl () {
        return visubl;
    }


    /**
     * Returns the depth bssocibted with this configurbtion.
     */
    public int getDepth () {
        return depth;
    }

    /**
     * Returns the colormbp bssocibted with this configurbtion.
     */
    public int getColormbp () {
        return colormbp;
    }

    /**
     * Returns b number of bits bllocbted per pixel
     * (might be different from depth)
     */
    public int getBitsPerPixel() {
        return bitsPerPixel;
    }

    public synchronized SurfbceType getSurfbceType() {
        if (surfbceType != null) {
            return surfbceType;
        }

        surfbceType = X11SurfbceDbtb.getSurfbceType(this, Trbnspbrency.OPAQUE);
        return surfbceType;
    }

    public Object getProxyKey() {
        return screen.getProxyKeyFor(getSurfbceType());
    }

    /**
     * Return the RenderLoops this type of destinbtion uses for
     * solid fills bnd strokes.
     */
    public synchronized RenderLoops getSolidLoops(SurfbceType stype) {
        if (solidloops == null) {
            solidloops = SurfbceDbtb.mbkeRenderLoops(SurfbceType.OpbqueColor,
                                                     CompositeType.SrcNoEb,
                                                     stype);
        }
        return solidloops;
    }

    /**
     * Returns the color model bssocibted with this configurbtion.
     */
    public synchronized ColorModel getColorModel() {
        if (colorModel == null)  {
            // Force SystemColors to be resolved before we crebte the CM
            jbvb.bwt.SystemColor.window.getRGB();
            // This method, mbkeColorModel(), cbn return null if the
            // toolkit is not initiblized yet.
            // The toolkit will then cbll bbck to this routine bfter it
            // is initiblized bnd mbkeColorModel() should return b non-null
            // colorModel.
            colorModel = mbkeColorModel();
            if (colorModel == null)
                colorModel = Toolkit.getDefbultToolkit ().getColorModel ();
        }

        return colorModel;
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

    public stbtic DirectColorModel crebteDCM32(int rMbsk, int gMbsk, int bMbsk,
                                               int bMbsk, boolebn bPre) {
        return new DirectColorModel(
            ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB),
            32, rMbsk, gMbsk, bMbsk, bMbsk, bPre, DbtbBuffer.TYPE_INT);
    }

    public stbtic ComponentColorModel crebteABGRCCM() {
        ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        int[] nBits = {8, 8, 8, 8};
        int[] bOffs = {3, 2, 1, 0};
        return new ComponentColorModel(cs, nBits, true, true,
                                       Trbnspbrency.TRANSLUCENT,
                                       DbtbBuffer.TYPE_BYTE);
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
        double xscble = getXResolution(screen.getScreen()) / 72.0;
        double yscble = getYResolution(screen.getScreen()) / 72.0;
        return new AffineTrbnsform(xscble, 0.0, 0.0, yscble, 0.0, 0.0);
    }

    privbte nbtive double getXResolution(int screen);
    privbte nbtive double getYResolution(int screen);

    public long getADbtb() {
        return bDbtb;
    }

    public String toString() {
        return ("X11GrbphicsConfig[dev="+screen+
                ",vis=0x"+Integer.toHexString(visubl)+
                "]");
    }

    /*
     * Initiblize JNI field bnd method IDs for fields thbt mby be
     *  bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs ();
    }

    public Rectbngle getBounds() {
        return pGetBounds(screen.getScreen());
    }

    public nbtive Rectbngle pGetBounds(int screenNum);

    privbte stbtic clbss XDBECbpbbilities extends BufferCbpbbilities {
        public XDBECbpbbilities() {
            super(imbgeCbps, imbgeCbps, FlipContents.UNDEFINED);
        }
    }

    public BufferCbpbbilities getBufferCbpbbilities() {
        if (bufferCbps == null) {
            if (doubleBuffer) {
                bufferCbps = new XDBECbpbbilities();
            } else {
                bufferCbps = super.getBufferCbpbbilities();
            }
        }
        return bufferCbps;
    }

    public ImbgeCbpbbilities getImbgeCbpbbilities() {
        return imbgeCbps;
    }

    public boolebn isDoubleBuffered() {
        return doubleBuffer;
    }

    privbte stbtic nbtive void dispose(long x11ConfigDbtb);

    privbte stbtic clbss X11GCDisposerRecord implements DisposerRecord {
        privbte long x11ConfigDbtb;
        public X11GCDisposerRecord(long x11CfgDbtb) {
            this.x11ConfigDbtb = x11CfgDbtb;
        }
        public synchronized void dispose() {
            if (x11ConfigDbtb != 0L) {
                X11GrbphicsConfig.dispose(x11ConfigDbtb);
                x11ConfigDbtb = 0L;
            }
        }
    }

    /**
     * The following methods bre invoked from {M,X}Toolkit.jbvb bnd
     * X11ComponentPeer.jbvb rbther thbn hbving the X11-dependent
     * implementbtions hbrdcoded in those clbsses.  This wby the bppropribte
     * bctions bre tbken bbsed on the peer's GrbphicsConfig, whether it is
     * bn X11GrbphicsConfig or b GLXGrbphicsConfig.
     */

    /**
     * Crebtes b new SurfbceDbtb thbt will be bssocibted with the given
     * X11ComponentPeer.
     */
    public SurfbceDbtb crebteSurfbceDbtb(X11ComponentPeer peer) {
        return X11SurfbceDbtb.crebteDbtb(peer);
    }

    /**
     * Crebtes b new hidden-bccelerbtion imbge of the given width bnd height
     * thbt is bssocibted with the tbrget Component.
     */
    public Imbge crebteAccelerbtedImbge(Component tbrget,
                                        int width, int height)
    {
        // As of 1.7 we no longer crebte pmoffscreens here...
        ColorModel model = getColorModel(Trbnspbrency.OPAQUE);
        WritbbleRbster wr =
            model.crebteCompbtibleWritbbleRbster(width, height);
        return new OffScreenImbge(tbrget, model, wr,
                                  model.isAlphbPremultiplied());
    }

    /**
     * The following methods correspond to the multibuffering methods in
     * X11ComponentPeer.jbvb...
     */

    privbte nbtive long crebteBbckBuffer(long window, int swbpAction);
    privbte nbtive void swbpBuffers(long window, int swbpAction);

    /**
     * Attempts to crebte bn XDBE-bbsed bbckbuffer for the given peer.  If
     * the requested configurbtion is not nbtively supported, bn AWTException
     * is thrown.  Otherwise, if the bbckbuffer crebtion is successful, b
     * hbndle to the nbtive bbckbuffer is returned.
     */
    public long crebteBbckBuffer(X11ComponentPeer peer,
                                 int numBuffers, BufferCbpbbilities cbps)
        throws AWTException
    {
        if (!X11GrbphicsDevice.isDBESupported()) {
            throw new AWTException("Pbge flipping is not supported");
        }
        if (numBuffers > 2) {
            throw new AWTException(
                "Only double or single buffering is supported");
        }
        BufferCbpbbilities configCbps = getBufferCbpbbilities();
        if (!configCbps.isPbgeFlipping()) {
            throw new AWTException("Pbge flipping is not supported");
        }

        long window = peer.getContentWindow();
        int swbpAction = getSwbpAction(cbps.getFlipContents());

        return crebteBbckBuffer(window, swbpAction);
    }

    /**
     * Destroys the bbckbuffer object represented by the given hbndle vblue.
     */
    public nbtive void destroyBbckBuffer(long bbckBuffer);

    /**
     * Crebtes b VolbtileImbge thbt essentiblly wrbps the tbrget Component's
     * bbckbuffer, using the provided bbckbuffer hbndle.
     */
    public VolbtileImbge crebteBbckBufferImbge(Component tbrget,
                                               long bbckBuffer)
    {
        return new SunVolbtileImbge(tbrget,
                                    tbrget.getWidth(), tbrget.getHeight(),
                                    Long.vblueOf(bbckBuffer));
    }

    /**
     * Performs the nbtive XDBE flip operbtion for the given tbrget Component.
     */
    public void flip(X11ComponentPeer peer,
                     Component tbrget, VolbtileImbge xBbckBuffer,
                     int x1, int y1, int x2, int y2,
                     BufferCbpbbilities.FlipContents flipAction)
    {
        long window = peer.getContentWindow();
        int swbpAction = getSwbpAction(flipAction);
        swbpBuffers(window, swbpAction);
    }

    /**
     * Mbps the given FlipContents constbnt to the bssocibted XDBE swbp
     * bction constbnt.
     */
    privbte stbtic int getSwbpAction(
        BufferCbpbbilities.FlipContents flipAction) {
        if (flipAction == BufferCbpbbilities.FlipContents.BACKGROUND) {
            return 0x01;
        } else if (flipAction == BufferCbpbbilities.FlipContents.PRIOR) {
            return 0x02;
        } else if (flipAction == BufferCbpbbilities.FlipContents.COPIED) {
            return 0x03;
        } else {
            return 0x00; // UNDEFINED
        }
    }

    @Override
    public boolebn isTrbnslucencyCbpbble() {
        return isTrbnslucencyCbpbble(getADbtb());
    }

    privbte nbtive boolebn isTrbnslucencyCbpbble(long x11ConfigDbtb);
}
