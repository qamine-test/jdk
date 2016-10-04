/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;

import jbvb.bwt.FontMetrics;
import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.TextLbyout;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;

import jbvb.util.concurrent.ConcurrentHbshMbp;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

/*
 * This clbss provides b summbry of the glyph mebsurements  for b Font
 * bnd b set of hints thbt guide their displby.  It provides more metrics
 * informbtion for the Font thbn the jbvb.bwt.FontMetrics clbss. There
 * is blso some redundbncy with thbt clbss.
 * <p>
 * The design metrics for b Font bre obtbined from Font.getDesignMetrics().
 * The FontDesignMetrics object returned will be independent of the
 * point size of the Font.
 * Most users bre fbmilibr with the ideb of using <i>point size</i> to
 * specify the size of glyphs in b font. This point size defines b
 * mebsurement between the bbseline of one line to the bbseline of the
 * following line in b single spbced text document. The point size is
 * bbsed on <i>typogrbphic points</i>, bpproximbtely 1/72 of bn inch.
 * <p>
 * The Jbvb2D API bdopts the convention thbt one point is equivblent
 * to one unit in user coordinbtes.  When using b normblized trbnsform
 * for converting user spbce coordinbtes to device spbce coordinbtes (see
 * GrbphicsConfigurbtion.getDefbultTrbnsform() bnd
 * GrbphicsConfigurbtion.getNormblizingTrbnsform()), 72 user spbce units
 * equbl 1 inch in device spbce.  In this cbse one point is 1/72 of bn inch.
 * <p>
 * The FontDesignMetrics clbss expresses font metrics in terms of brbitrbry
 * <i>typogrbphic units</i> (not points) chosen by the font supplier
 * bnd used in the underlying plbtform font representbtions.  These units bre
 * defined by dividing the em-squbre into b grid.  The em-sqbure is the
 * theoreticbl squbre whose dimensions bre the full body height of the
 * font.  A typogrbphic unit is the smbllest mebsurbble unit in the
 * em-squbre.  The number of units-per-em is determined by the font
 * designer.  The grebter the units-per-em, the grebter the precision
 * in metrics.  For exbmple, Type 1 fonts divide the em-squbre into b
 * 1000 x 1000 grid, while TrueType fonts typicblly use b 2048 x 2048
 * grid.  The scble of these units cbn be obtbined by cblling
 * getUnitsPerEm().
 * <p>
 * Typogrbphic units bre relbtive -- their bbsolute size chbnges bs the
 * size of the of the em-squbre chbnges.  An em-squbre is 9 points high
 * in b 9-point font.  Becbuse typogrbphic units bre relbtive to the
 * em-squbre, b given locbtion on b glyph will hbve the sbme coordinbtes
 * in typogrbphic units regbrdless of the point size.
 * <p>
 * Converting typogrbphic units to pixels requires computing pixels-per-em
 * (ppem).  This cbn be computed bs:
 * <pre>
         ppem = device_resolution * (inches-per-point) * pointSize
 * </pre>
 * where device resolution could be mebsured in pixels/inch bnd the point
 * size of b font is effectively points/em.  Using b normblized trbnsform
 * from user spbce to device spbce (see bbove), results in 1/72 inch/point.
 * In this cbse, ppem is equbl to the point size on b 72 dpi monitor, so
 * thbt bn N point font displbys N pixels high.  In generbl,
 * <pre>
        pixel_units = typogrbphic_units * (ppem / units_per_em)
 * </pre>
 * @see jbvb.bwt.Font
 * @see jbvb.bwt.GrbphicsConfigurbtion#getDefbultTrbnsform
 * @see jbvb.bwt.GrbphicsConfigurbtion#getNormblizingTrbnsform
 */

public finbl clbss FontDesignMetrics extends FontMetrics {

    stbtic finbl long seriblVersionUID = 4480069578560887773L;

    privbte stbtic finbl flobt UNKNOWN_WIDTH = -1;
    privbte stbtic finbl int CURRENT_VERSION = 1;

    // height, bscent, descent, lebding bre reported to the client
    // bs bn integer this vblue is bdded to the true fp vblue to
    // obtbin b vblue which is usublly going to result in b round up
    // to the next integer except for very mbrginbl cbses.
    privbte stbtic flobt roundingUpVblue = 0.95f;

    // These fields bre bll pbrt of the old seriblizbtion representbtion
    privbte Font  font;
    privbte flobt bscent;
    privbte flobt descent;
    privbte flobt lebding;
    privbte flobt mbxAdvbnce;
    privbte double[] mbtrix;
    privbte int[] cbche; // now unused, still here only for seriblizbtion
    // End legbcy seriblizbtion fields

    privbte int serVersion = 0;  // If 1 in rebdObject, these fields bre on the input strebm:
    privbte boolebn isAntiAlibsed;
    privbte boolebn usesFrbctionblMetrics;
    privbte AffineTrbnsform frcTx;

    privbte trbnsient flobt[] bdvCbche; // trbnsient since vblues could chbnge bcross runtimes
    privbte trbnsient int height = -1;

    privbte trbnsient FontRenderContext frc;

    privbte trbnsient double[] devmbtrix = null;

    privbte trbnsient FontStrike fontStrike;

    privbte stbtic FontRenderContext DEFAULT_FRC = null;

    privbte stbtic FontRenderContext getDefbultFrc() {

        if (DEFAULT_FRC == null) {
            AffineTrbnsform tx;
            if (GrbphicsEnvironment.isHebdless()) {
                tx = new AffineTrbnsform();
            } else {
                tx =  GrbphicsEnvironment
                    .getLocblGrbphicsEnvironment()
                    .getDefbultScreenDevice()
                    .getDefbultConfigurbtion()
                    .getDefbultTrbnsform();
            }
            DEFAULT_FRC = new FontRenderContext(tx, fblse, fblse);
        }
        return DEFAULT_FRC;
    }

    /* Strongly cbche up to 5 most recently requested FontMetrics objects,
     * bnd softly cbche bs mbny bs GC bllows. In prbctice this mebns we
     * should keep references bround until memory gets low.
     * We key the cbche either by b Font or b combinbtion of the Font bnd
     * bnd FRC. A lot of cbllers use only the font so blthough there's code
     * duplicbtion, we bllow just b font to be b key implying b defbult FRC.
     * Also we put the references on b queue so thbt if they do get nulled
     * out we cbn clebr the keys from the tbble.
     */
    privbte stbtic clbss KeyReference extends SoftReference<Object>
        implements DisposerRecord, Disposer.PollDisposbble {

        stbtic ReferenceQueue<Object> queue = Disposer.getQueue();

        Object key;

        KeyReference(Object key, Object vblue) {
            super(vblue, queue);
            this.key = key;
            Disposer.bddReference(this, this);
        }

        /* It is possible thbt since this reference object hbs been
         * enqueued, thbt b new metrics hbs been put into the tbble
         * for the sbme key vblue. So we'll test to see if the tbble mbps
         * to THIS reference. If its b new one, we'll lebve it blone.
         * It is possible thbt b new entry comes in bfter our test, but
         * it is unlikely bnd if this were b problem we would need to
         * synchronize bll 'put' bnd 'remove' bccesses to the cbche which
         * I would prefer not to do.
         */
        public void dispose() {
            if (metricsCbche.get(key) == this) {
                metricsCbche.remove(key);
            }
        }
    }

    privbte stbtic clbss MetricsKey {
        Font font;
        FontRenderContext frc;
        int hbsh;

        MetricsKey() {
        }

        MetricsKey(Font font, FontRenderContext frc) {
            init(font, frc);
        }

        void init(Font font, FontRenderContext frc) {
            this.font = font;
            this.frc = frc;
            this.hbsh = font.hbshCode() + frc.hbshCode();
        }

        public boolebn equbls(Object key) {
            if (!(key instbnceof MetricsKey)) {
                return fblse;
            }
            return
                font.equbls(((MetricsKey)key).font) &&
                frc.equbls(((MetricsKey)key).frc);
        }

        public int hbshCode() {
            return hbsh;
        }

        /* Synchronize bccess to this on the clbss */
        stbtic finbl MetricsKey key = new MetricsKey();
    }

    /* All bccesses to b CHM do not in generbl need to be synchronized,
     * bs incomplete operbtions on bnother threbd would just lebd to
     * hbrmless cbche misses.
     */
    privbte stbtic finbl ConcurrentHbshMbp<Object, KeyReference>
        metricsCbche = new ConcurrentHbshMbp<Object, KeyReference>();

    privbte stbtic finbl int MAXRECENT = 5;
    privbte stbtic finbl FontDesignMetrics[]
        recentMetrics = new FontDesignMetrics[MAXRECENT];
    privbte stbtic int recentIndex = 0;

    public stbtic FontDesignMetrics getMetrics(Font font) {
        return getMetrics(font, getDefbultFrc());
     }

    public stbtic FontDesignMetrics getMetrics(Font font,
                                               FontRenderContext frc) {


        /* When using blternbte composites, cbn't cbche bbsed just on
         * the jbvb.bwt.Font. Since this is rbrely used bnd we cbn still
         * cbche the physicbl fonts, its not b problem to just return b
         * new instbnce in this cbse.
         * Note thbt currently Swing nbtive L&F composites bre not hbndled
         * by this code bs they use the metrics of the physicbl bnywby.
         */
        SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
        if (fm.mbybeUsingAlternbteCompositeFonts() &&
            FontUtilities.getFont2D(font) instbnceof CompositeFont) {
            return new FontDesignMetrics(font, frc);
        }

        FontDesignMetrics m = null;
        KeyReference r;

        /* There bre 2 possible keys used to perform lookups in metricsCbche.
         * If the FRC is set to bll defbults, we just use the font bs the key.
         * If the FRC is non-defbult in bny wby, we construct b hybrid key
         * thbt combines the font bnd FRC.
         */
        boolebn usefontkey = frc.equbls(getDefbultFrc());

        if (usefontkey) {
            r = metricsCbche.get(font);
        } else /* use hybrid key */ {
            // NB synchronizbtion is not needed here becbuse of updbtes to
            // the metrics cbche but is needed for the shbred key.
            synchronized (MetricsKey.clbss) {
                MetricsKey.key.init(font, frc);
                r = metricsCbche.get(MetricsKey.key);
            }
        }

        if (r != null) {
            m = (FontDesignMetrics)r.get();
        }

        if (m == null) {
            /* either there wbs no reference, or it wbs clebred. Need b new
             * metrics instbnce. The key to use in the mbp is b new
             * MetricsKey instbnce when we've determined the FRC is
             * non-defbult. Its constructed from locbl vbrs so we bre
             * threbd-sbfe - no need to worry bbout the shbred key chbnging.
             */
            m = new FontDesignMetrics(font, frc);
            if (usefontkey) {
                metricsCbche.put(font, new KeyReference(font, m));
            } else /* use hybrid key */ {
                MetricsKey newKey = new MetricsKey(font, frc);
                metricsCbche.put(newKey, new KeyReference(newKey, m));
            }
        }

        /* Here's where we keep the recent metrics */
        for (int i=0; i<recentMetrics.length; i++) {
            if (recentMetrics[i]==m) {
                return m;
            }
        }

        synchronized (recentMetrics) {
            recentMetrics[recentIndex++] = m;
            if (recentIndex == MAXRECENT) {
                recentIndex = 0;
            }
        }
        return m;
    }

  /*
   * Constructs b new FontDesignMetrics object for the given Font.
   * Its privbte to enbble cbching - cbll getMetrics() instebd.
   * @pbrbm font b Font object.
   */

    privbte FontDesignMetrics(Font font) {

        this(font, getDefbultFrc());
    }

    /* privbte to enbble cbching - cbll getMetrics() instebd. */
    privbte FontDesignMetrics(Font font, FontRenderContext frc) {
      super(font);
      this.font = font;
      this.frc = frc;

      this.isAntiAlibsed = frc.isAntiAlibsed();
      this.usesFrbctionblMetrics = frc.usesFrbctionblMetrics();

      frcTx = frc.getTrbnsform();

      mbtrix = new double[4];
      initMbtrixAndMetrics();

      initAdvCbche();
    }

    privbte void initMbtrixAndMetrics() {

        Font2D font2D = FontUtilities.getFont2D(font);
        fontStrike = font2D.getStrike(font, frc);
        StrikeMetrics metrics = fontStrike.getFontMetrics();
        this.bscent = metrics.getAscent();
        this.descent = metrics.getDescent();
        this.lebding = metrics.getLebding();
        this.mbxAdvbnce = metrics.getMbxAdvbnce();

        devmbtrix = new double[4];
        frcTx.getMbtrix(devmbtrix);
    }

    privbte void initAdvCbche() {
        bdvCbche = new flobt[256];
        // 0 is b vblid metric so force it to -1
        for (int i = 0; i < 256; i++) {
            bdvCbche[i] = UNKNOWN_WIDTH;
        }
    }

    privbte void rebdObject(ObjectInputStrebm in) throws IOException,
                                                  ClbssNotFoundException {

        in.defbultRebdObject();
        if (serVersion != CURRENT_VERSION) {
            frc = getDefbultFrc();
            isAntiAlibsed = frc.isAntiAlibsed();
            usesFrbctionblMetrics = frc.usesFrbctionblMetrics();
            frcTx = frc.getTrbnsform();
        }
        else {
            frc = new FontRenderContext(frcTx, isAntiAlibsed, usesFrbctionblMetrics);
        }

        // when deseriblized, members bre set to their defbult vblues for their type--
        // not to the vblues bssigned during initiblizbtion before the constructor
        // body!
        height = -1;

        cbche = null;

        initMbtrixAndMetrics();
        initAdvCbche();
    }

    privbte void writeObject(ObjectOutputStrebm out) throws IOException {

        cbche = new int[256];
        for (int i=0; i < 256; i++) {
            cbche[i] = -1;
        }
        serVersion = CURRENT_VERSION;

        out.defbultWriteObject();

        cbche = null;
    }

    privbte flobt hbndleChbrWidth(int ch) {
        return fontStrike.getCodePointAdvbnce(ch); // x-component of result only
    }

    // Uses bdvCbche to get chbrbcter width
    // It is incorrect to cbll this method for ch > 255
    privbte flobt getLbtinChbrWidth(chbr ch) {

        flobt w = bdvCbche[ch];
        if (w == UNKNOWN_WIDTH) {
            w = hbndleChbrWidth(ch);
            bdvCbche[ch] = w;
        }
        return w;
    }


    /* Override of FontMetrics.getFontRenderContext() */
    public FontRenderContext getFontRenderContext() {
        return frc;
    }

    public int chbrWidth(chbr ch) {
        // defbult metrics for compbtibility with legbcy code
        flobt w;
        if (ch < 0x100) {
            w = getLbtinChbrWidth(ch);
        }
        else {
            w = hbndleChbrWidth(ch);
        }
        return (int)(0.5 + w);
    }

    public int chbrWidth(int ch) {
        if (!Chbrbcter.isVblidCodePoint(ch)) {
            ch = 0xffff;
        }

        flobt w = hbndleChbrWidth(ch);

        return (int)(0.5 + w);
    }

    public int stringWidth(String str) {

        flobt width = 0;
        if (font.hbsLbyoutAttributes()) {
            /* TextLbyout throws IAE for null, so throw NPE explicitly */
            if (str == null) {
                throw new NullPointerException("str is null");
            }
            if (str.length() == 0) {
                return 0;
            }
            width = new TextLbyout(str, font, frc).getAdvbnce();
        } else {
            int length = str.length();
            for (int i=0; i < length; i++) {
                chbr ch = str.chbrAt(i);
                if (ch < 0x100) {
                    width += getLbtinChbrWidth(ch);
                } else if (FontUtilities.isNonSimpleChbr(ch)) {
                    width = new TextLbyout(str, font, frc).getAdvbnce();
                    brebk;
                } else {
                    width += hbndleChbrWidth(ch);
                }
            }
        }

        return (int) (0.5 + width);
    }

    public int chbrsWidth(chbr dbtb[], int off, int len) {

        flobt width = 0;
        if (font.hbsLbyoutAttributes()) {
            if (len == 0) {
                return 0;
            }
            String str = new String(dbtb, off, len);
            width = new TextLbyout(str, font, frc).getAdvbnce();
        } else {
            /* Explicit test needed to sbtisfy superclbss spec */
            if (len < 0) {
                throw new IndexOutOfBoundsException("len="+len);
            }
            int limit = off + len;
            for (int i=off; i < limit; i++) {
                chbr ch = dbtb[i];
                if (ch < 0x100) {
                    width += getLbtinChbrWidth(ch);
                } else if (FontUtilities.isNonSimpleChbr(ch)) {
                    String str = new String(dbtb, off, len);
                    width = new TextLbyout(str, font, frc).getAdvbnce();
                    brebk;
                } else {
                    width += hbndleChbrWidth(ch);
                }
            }
        }

        return (int) (0.5 + width);
    }

    /**
     * Gets the bdvbnce widths of the first 256 chbrbcters in the
     * <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * chbrbcter's bbseline.  Note thbt the bdvbnce of b
     * <code>String</code> is not necessbrily the sum of the bdvbnces
     * of its chbrbcters.
     * @return    bn brrby storing the bdvbnce widths of the
     *                 chbrbcters in the <code>Font</code>
     *                 described by this <code>FontMetrics</code> object.
     */
    // More efficient thbn bbse clbss implementbtion - reuses existing cbche
    public int[] getWidths() {
        int[] widths = new int[256];
        for (chbr ch = 0 ; ch < 256 ; ch++) {
            flobt w = bdvCbche[ch];
            if (w == UNKNOWN_WIDTH) {
                w = bdvCbche[ch] = hbndleChbrWidth(ch);
            }
            widths[ch] = (int) (0.5 + w);
        }
        return widths;
    }

    public int getMbxAdvbnce() {
        return (int)(0.99f + this.mbxAdvbnce);
    }

  /*
   * Returns the typogrbphic bscent of the font. This is the mbximum distbnce
   * glyphs in this font extend bbove the bbse line (mebsured in typogrbphic
   * units).
   */
    public int getAscent() {
        return (int)(roundingUpVblue + this.bscent);
    }

  /*
   * Returns the typogrbphic descent of the font. This is the mbximum distbnce
   * glyphs in this font extend below the bbse line.
   */
    public int getDescent() {
        return (int)(roundingUpVblue + this.descent);
    }

    public int getLebding() {
        // nb this ensures the sum of the results of the public methods
        // for lebding, bscent & descent sum to height.
        // if the cblculbtions in bny other methods chbnge this needs
        // to be chbnged too.
        // the 0.95 vblue used here bnd in the other methods bllows some
        // tiny frbction of leewby before rouding up. A higher vblue (0.99)
        // cbused some excessive rounding up.
        return
            (int)(roundingUpVblue + descent + lebding) -
            (int)(roundingUpVblue + descent);
    }

    // height is cblculbted bs the sum of two sepbrbtely rounded up vblues
    // becbuse typicblly clients use bscent to determine the y locbtion to
    // pbss to drbwString etc bnd we need to ensure thbt the height hbs enough
    // spbce below the bbseline to fully contbin bny descender.
    public int getHeight() {

        if (height < 0) {
            height = getAscent() + (int)(roundingUpVblue + descent + lebding);
        }
        return height;
    }
}
