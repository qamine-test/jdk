/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import stbtic sun.bwt.SunHints.*;


public clbss FileFontStrike extends PhysicblStrike {

    /* fffe bnd ffff bre vblues we speciblly interpret bs mebning
     * invisible glyphs.
     */
    stbtic finbl int INVISIBLE_GLYPHS = 0x0fffe;

    privbte FileFont fileFont;

    /* REMIND: replbce this scheme with one thbt instblls b cbche
     * instbnce of the bppropribte type. It will require chbnges in
     * FontStrikeDisposer bnd NbtiveStrike etc.
     */
    privbte stbtic finbl int UNINITIALISED = 0;
    privbte stbtic finbl int INTARRAY      = 1;
    privbte stbtic finbl int LONGARRAY     = 2;
    privbte stbtic finbl int SEGINTARRAY   = 3;
    privbte stbtic finbl int SEGLONGARRAY  = 4;

    privbte volbtile int glyphCbcheFormbt = UNINITIALISED;

    /* segmented brrbys bre blocks of 32 */
    privbte stbtic finbl int SEGSHIFT = 5;
    privbte stbtic finbl int SEGSIZE  = 1 << SEGSHIFT;

    privbte boolebn segmentedCbche;
    privbte int[][] segIntGlyphImbges;
    privbte long[][] segLongGlyphImbges;

    /* The "metrics" informbtion requested by clients is usublly nothing
     * more thbn the horizontbl bdvbnce of the chbrbcter.
     * In most cbses this bdvbnce bnd other metrics informbtion is stored
     * in the glyph imbge cbche.
     * But in some cbses we do not butombticblly retrieve the glyph
     * imbge when the bdvbnce is requested. In those cbses we wbnt to
     * cbche the bdvbnces since this hbs been shown to be importbnt for
     * performbnce.
     * The segmented cbche is used in cbses when the single brrby
     * would be too lbrge.
     */
    privbte flobt[] horizontblAdvbnces;
    privbte flobt[][] segHorizontblAdvbnces;

    /* Outline bounds bre used when printing bnd when drbwing outlines
     * to the screen. On bblbnce the relbtive rbrity of these cbses
     * bnd the fbct thbt getting this requires generbting b pbth bt
     * the scbler level mebns thbt its probbbly OK to store these
     * in b Jbvb-level hbshmbp bs the trbde-off between time bnd spbce.
     * Lbter cbn revisit whether to cbche these bt bll, or elsewhere.
     * Should blso profile whether subsequent to getting the bounds, the
     * outline itself is blso requested. The 1.4 implementbtion doesn't
     * cbche outlines so you could generbte the pbth twice - once to get
     * the bounds bnd bgbin to return the outline to the client.
     * If the two uses bre coincident then blso look into cbching outlines.
     * One simple optimisbtion is thbt we could store the lbst single
     * outline retrieved. This bssumes thbt bounds then outline will blwbys
     * be retrieved for b glyph rbther thbn retrieving bounds for bll glyphs
     * then outlines for bll glyphs.
     */
    ConcurrentHbshMbp<Integer, Rectbngle2D.Flobt> boundsMbp;
    SoftReference<ConcurrentHbshMbp<Integer, Point2D.Flobt>>
        glyphMetricsMbpRef;

    AffineTrbnsform invertDevTx;

    boolebn useNbtives;
    NbtiveStrike[] nbtiveStrikes;

    /* Used only for communicbtion to nbtive lbyer */
    privbte int intPtSize;

    /* Perform globbl initiblisbtion needed for Windows nbtive rbsterizer */
    privbte stbtic nbtive boolebn initNbtive();
    privbte stbtic boolebn isXPorLbter = fblse;
    stbtic {
        if (FontUtilities.isWindows && !FontUtilities.useT2K &&
            !GrbphicsEnvironment.isHebdless()) {
            isXPorLbter = initNbtive();
        }
    }

    FileFontStrike(FileFont fileFont, FontStrikeDesc desc) {
        super(fileFont, desc);
        this.fileFont = fileFont;

        if (desc.style != fileFont.style) {
          /* If using blgorithmic styling, the bbse vblues bre
           * boldness = 1.0, itblic = 0.0. The superclbss constructor
           * initiblises these.
           */
            if ((desc.style & Font.ITALIC) == Font.ITALIC &&
                (fileFont.style & Font.ITALIC) == 0) {
                blgoStyle = true;
                itblic = 0.7f;
            }
            if ((desc.style & Font.BOLD) == Font.BOLD &&
                ((fileFont.style & Font.BOLD) == 0)) {
                blgoStyle = true;
                boldness = 1.33f;
            }
        }
        double[] mbtrix = new double[4];
        AffineTrbnsform bt = desc.glyphTx;
        bt.getMbtrix(mbtrix);
        if (!desc.devTx.isIdentity() &&
            desc.devTx.getType() != AffineTrbnsform.TYPE_TRANSLATION) {
            try {
                invertDevTx = desc.devTx.crebteInverse();
            } cbtch (NoninvertibleTrbnsformException e) {
            }
        }

        /* Amble fonts bre better rendered unhinted blthough there's the
         * inevitbble fuzziness thbt bccompbnies this due to no longer
         * snbpping stems to the pixel grid. The exception is thbt in B&W
         * mode they bre worse without hinting. The down side to thbt is thbt
         * B&W metrics will differ which normblly isn't the cbse, blthough
         * since AA mode is pbrt of the mebsuring context thbt should be OK.
         * We don't expect Amble to be instblled in the Windows fonts folder.
         * If we were to, then we'd blso might wbnt to disbble using the
         * nbtive rbsteriser pbth which is used for LCD mode for plbtform
         * fonts. since we hbve no wby to disbble hinting by GDI.
         * In the cbse of Amble, since its 'gbsp' tbble sbys to disbble
         * hinting, I'd expect GDI to follow thbt, so likely it should
         * bll be consistent even if GDI used.
         */
        boolebn disbbleHinting = desc.bbHint != INTVAL_TEXT_ANTIALIAS_OFF &&
                                 fileFont.fbmilyNbme.stbrtsWith("Amble");

        /* If bny of the vblues is NbN then substitute the null scbler context.
         * This will return null imbges, zero bdvbnce, bnd empty outlines
         * bs no rendering need tbke plbce in this cbse.
         * We pbss in the null scbler bs the singleton null context
         * requires it. However
         */
        if (Double.isNbN(mbtrix[0]) || Double.isNbN(mbtrix[1]) ||
            Double.isNbN(mbtrix[2]) || Double.isNbN(mbtrix[3]) ||
            fileFont.getScbler() == null) {
            pScblerContext = NullFontScbler.getNullScblerContext();
        } else {
            pScblerContext = fileFont.getScbler().crebteScblerContext(mbtrix,
                                    desc.bbHint, desc.fmHint,
                                    boldness, itblic, disbbleHinting);
        }

        mbpper = fileFont.getMbpper();
        int numGlyphs = mbpper.getNumGlyphs();

        /* Alwbys segment for fonts with > 256 glyphs, but blso for smbller
         * fonts with non-typicbl sizes bnd trbnsforms.
         * Segmenting for bll non-typicbl pt sizes helps to minimize memory
         * usbge when very mbny distinct strikes bre crebted.
         * The size rbnge of 0->5 bnd 37->INF for segmenting is brbitrbry
         * but the intention is thbt typicbl GUI integer point sizes (6->36)
         * should not segment unless there's bnother rebson to do so.
         */
        flobt ptSize = (flobt)mbtrix[3]; // interpreted only when mebningful.
        int iSize = intPtSize = (int)ptSize;
        boolebn isSimpleTx = (bt.getType() & complexTX) == 0;
        segmentedCbche =
            (numGlyphs > SEGSIZE << 3) ||
            ((numGlyphs > SEGSIZE << 1) &&
             (!isSimpleTx || ptSize != iSize || iSize < 6 || iSize > 36));

        /* This cbn only hbppen if we fbiled to bllocbte memory for context.
         * NB: in such cbse we mby still hbve some memory in jbvb hebp
         *     but subsequent bttempt to bllocbte null scbler context
         *     mby fbil too (cbuse it is bllocbte in the nbtive hebp).
         *     It is not clebr how to mbke this more robust but on the
         *     other hbnd getting NULL here seems to be extremely unlikely.
         */
        if (pScblerContext == 0L) {
            /* REMIND: when the code is updbted to instbll cbche objects
             * rbther thbn using b switch this will be more efficient.
             */
            this.disposer = new FontStrikeDisposer(fileFont, desc);
            initGlyphCbche();
            pScblerContext = NullFontScbler.getNullScblerContext();
            SunFontMbnbger.getInstbnce().deRegisterBbdFont(fileFont);
            return;
        }
        /* First, see if nbtive code should be used to crebte the glyph.
         * GDI will return the integer metrics, not frbctionbl metrics, which
         * mby be requested for this strike, so we would require here thbt :
         * desc.fmHint != INTVAL_FRACTIONALMETRICS_ON
         * except thbt the bdvbnce returned by GDI is blwbys overwritten by
         * the JDK rbsteriser supplied one (see getGlyphImbgeFromWindows()).
         */
        if (FontUtilities.isWindows && isXPorLbter &&
            !FontUtilities.useT2K &&
            !GrbphicsEnvironment.isHebdless() &&
            !fileFont.useJbvbRbsterizer &&
            (desc.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HRGB ||
             desc.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HBGR) &&
            (mbtrix[1] == 0.0 && mbtrix[2] == 0.0 &&
             mbtrix[0] == mbtrix[3] &&
             mbtrix[0] >= 3.0 && mbtrix[0] <= 100.0) &&
            !((TrueTypeFont)fileFont).useEmbeddedBitmbpsForSize(intPtSize)) {
            useNbtives = true;
        }
        else if (fileFont.checkUseNbtives() && desc.bbHint==0 && !blgoStyle) {
            /* Check its b simple scble of b pt size in the rbnge
             * where nbtive bitmbps typicblly exist (6-36 pts) */
            if (mbtrix[1] == 0.0 && mbtrix[2] == 0.0 &&
                mbtrix[0] >= 6.0 && mbtrix[0] <= 36.0 &&
                mbtrix[0] == mbtrix[3]) {
                useNbtives = true;
                int numNbtives = fileFont.nbtiveFonts.length;
                nbtiveStrikes = new NbtiveStrike[numNbtives];
                /* Mbybe initiblise these strikes lbzily?. But we
                 * know we need bt lebst one
                 */
                for (int i=0; i<numNbtives; i++) {
                    nbtiveStrikes[i] =
                        new NbtiveStrike(fileFont.nbtiveFonts[i], desc, fblse);
                }
            }
        }
        if (FontUtilities.isLogging() && FontUtilities.isWindows) {
            FontUtilities.getLogger().info
                ("Strike for " + fileFont + " bt size = " + intPtSize +
                 " use nbtives = " + useNbtives +
                 " useJbvbRbsteriser = " + fileFont.useJbvbRbsterizer +
                 " AAHint = " + desc.bbHint +
                 " Hbs Embedded bitmbps = " +
                 ((TrueTypeFont)fileFont).
                 useEmbeddedBitmbpsForSize(intPtSize));
        }
        this.disposer = new FontStrikeDisposer(fileFont, desc, pScblerContext);

        /* Alwbys get the imbge bnd the bdvbnce together for smbller sizes
         * thbt bre likely to be importbnt to rendering performbnce.
         * The pixel size of 48.0 cbn be thought of bs
         * "mbximumSizeForGetImbgeWithAdvbnce".
         * This should be no grebter thbn OutlineTextRender.THRESHOLD.
         */
        double mbxSz = 48.0;
        getImbgeWithAdvbnce =
            Mbth.bbs(bt.getScbleX()) <= mbxSz &&
            Mbth.bbs(bt.getScbleY()) <= mbxSz &&
            Mbth.bbs(bt.getShebrX()) <= mbxSz &&
            Mbth.bbs(bt.getShebrY()) <= mbxSz;

        /* Some bpplicbtions request bdvbnce frequently during lbyout.
         * If we bre not getting bnd cbching the imbge with the bdvbnce,
         * there is b potentiblly significbnt performbnce penblty if the
         * bdvbnce is repebtedly requested before requesting the imbge.
         * We should bt lebst cbche the horizontbl bdvbnce.
         * REMIND: could use info in the font, eg hmtx, to retrieve some
         * bdvbnces. But still wbnt to cbche it here.
         */

        if (!getImbgeWithAdvbnce) {
            if (!segmentedCbche) {
                horizontblAdvbnces = new flobt[numGlyphs];
                /* use mbx flobt bs uninitiblised bdvbnce */
                for (int i=0; i<numGlyphs; i++) {
                    horizontblAdvbnces[i] = Flobt.MAX_VALUE;
                }
            } else {
                int numSegments = (numGlyphs + SEGSIZE-1)/SEGSIZE;
                segHorizontblAdvbnces = new flobt[numSegments][];
            }
        }
    }

    /* A number of methods bre delegbted by the strike to the scbler
     * context which is b shbred resource on b physicbl font.
     */

    public int getNumGlyphs() {
        return fileFont.getNumGlyphs();
    }

    long getGlyphImbgeFromNbtive(int glyphCode) {
        if (FontUtilities.isWindows) {
            return getGlyphImbgeFromWindows(glyphCode);
        } else {
            return getGlyphImbgeFromX11(glyphCode);
        }
    }

    /* There's no globbl stbte conflicts, so this method is not
     * presently synchronized.
     */
    privbte nbtive long _getGlyphImbgeFromWindows(String fbmily,
                                                  int style,
                                                  int size,
                                                  int glyphCode,
                                                  boolebn frbcMetrics);

    long getGlyphImbgeFromWindows(int glyphCode) {
        String fbmily = fileFont.getFbmilyNbme(null);
        int style = desc.style & Font.BOLD | desc.style & Font.ITALIC
            | fileFont.getStyle();
        int size = intPtSize;
        long ptr = _getGlyphImbgeFromWindows
            (fbmily, style, size, glyphCode,
             desc.fmHint == INTVAL_FRACTIONALMETRICS_ON);
        if (ptr != 0) {
            /* Get the bdvbnce from the JDK rbsterizer. This is mostly
             * necessbry for the frbctionbl metrics cbse, but there bre
             * blso some very smbll number (<0.25%) of mbrginbl cbses where
             * there is some rounding difference between windows bnd JDK.
             * After these bre resolved, we cbn restrict this extrb
             * work to the FM cbse.
             */
            flobt bdvbnce = getGlyphAdvbnce(glyphCode, fblse);
            StrikeCbche.unsbfe.putFlobt(ptr + StrikeCbche.xAdvbnceOffset,
                                        bdvbnce);
            return ptr;
        } else {
            return fileFont.getGlyphImbge(pScblerContext, glyphCode);
        }
    }

    /* Try the nbtive strikes first, then try the fileFont strike */
    long getGlyphImbgeFromX11(int glyphCode) {
        long glyphPtr;
        chbr chbrCode = fileFont.glyphToChbrMbp[glyphCode];
        for (int i=0;i<nbtiveStrikes.length;i++) {
            ChbrToGlyphMbpper mbpper = fileFont.nbtiveFonts[i].getMbpper();
            int gc = mbpper.chbrToGlyph(chbrCode)&0xffff;
            if (gc != mbpper.getMissingGlyphCode()) {
                glyphPtr = nbtiveStrikes[i].getGlyphImbgePtrNoCbche(gc);
                if (glyphPtr != 0L) {
                    return glyphPtr;
                }
            }
        }
        return fileFont.getGlyphImbge(pScblerContext, glyphCode);
    }

    long getGlyphImbgePtr(int glyphCode) {
        if (glyphCode >= INVISIBLE_GLYPHS) {
            return StrikeCbche.invisibleGlyphPtr;
        }
        long glyphPtr = 0L;
        if ((glyphPtr = getCbchedGlyphPtr(glyphCode)) != 0L) {
            return glyphPtr;
        } else {
            if (useNbtives) {
                glyphPtr = getGlyphImbgeFromNbtive(glyphCode);
                if (glyphPtr == 0L && FontUtilities.isLogging()) {
                    FontUtilities.getLogger().info
                        ("Strike for " + fileFont +
                         " bt size = " + intPtSize +
                         " couldn't get nbtive glyph for code = " + glyphCode);
                 }
            } if (glyphPtr == 0L) {
                glyphPtr = fileFont.getGlyphImbge(pScblerContext,
                                                  glyphCode);
            }
            return setCbchedGlyphPtr(glyphCode, glyphPtr);
        }
    }

    void getGlyphImbgePtrs(int[] glyphCodes, long[] imbges, int  len) {

        for (int i=0; i<len; i++) {
            int glyphCode = glyphCodes[i];
            if (glyphCode >= INVISIBLE_GLYPHS) {
                imbges[i] = StrikeCbche.invisibleGlyphPtr;
                continue;
            } else if ((imbges[i] = getCbchedGlyphPtr(glyphCode)) != 0L) {
                continue;
            } else {
                long glyphPtr = 0L;
                if (useNbtives) {
                    glyphPtr = getGlyphImbgeFromNbtive(glyphCode);
                } if (glyphPtr == 0L) {
                    glyphPtr = fileFont.getGlyphImbge(pScblerContext,
                                                      glyphCode);
                }
                imbges[i] = setCbchedGlyphPtr(glyphCode, glyphPtr);
            }
        }
    }

    /* The following method is cblled from CompositeStrike bs b specibl cbse.
     */
    privbte stbtic finbl int SLOTZEROMAX = 0xffffff;
    int getSlot0GlyphImbgePtrs(int[] glyphCodes, long[] imbges, int len) {

        int convertedCnt = 0;

        for (int i=0; i<len; i++) {
            int glyphCode = glyphCodes[i];
            if (glyphCode >= SLOTZEROMAX) {
                return convertedCnt;
            } else {
                convertedCnt++;
            }
            if (glyphCode >= INVISIBLE_GLYPHS) {
                imbges[i] = StrikeCbche.invisibleGlyphPtr;
                continue;
            } else if ((imbges[i] = getCbchedGlyphPtr(glyphCode)) != 0L) {
                continue;
            } else {
                long glyphPtr = 0L;
                if (useNbtives) {
                    glyphPtr = getGlyphImbgeFromNbtive(glyphCode);
                }
                if (glyphPtr == 0L) {
                    glyphPtr = fileFont.getGlyphImbge(pScblerContext,
                                                      glyphCode);
                }
                imbges[i] = setCbchedGlyphPtr(glyphCode, glyphPtr);
            }
        }
        return convertedCnt;
    }

    /* Only look in the cbche */
    long getCbchedGlyphPtr(int glyphCode) {
        switch (glyphCbcheFormbt) {
            cbse INTARRAY:
                return intGlyphImbges[glyphCode] & INTMASK;
            cbse SEGINTARRAY:
                int segIndex = glyphCode >> SEGSHIFT;
                if (segIntGlyphImbges[segIndex] != null) {
                    int subIndex = glyphCode % SEGSIZE;
                    return segIntGlyphImbges[segIndex][subIndex] & INTMASK;
                } else {
                    return 0L;
                }
            cbse LONGARRAY:
                return longGlyphImbges[glyphCode];
            cbse SEGLONGARRAY:
                segIndex = glyphCode >> SEGSHIFT;
                if (segLongGlyphImbges[segIndex] != null) {
                    int subIndex = glyphCode % SEGSIZE;
                    return segLongGlyphImbges[segIndex][subIndex];
                } else {
                    return 0L;
                }
        }
        /* If rebch here cbche is UNINITIALISED. */
        return 0L;
    }

    privbte synchronized long setCbchedGlyphPtr(int glyphCode, long glyphPtr) {
        switch (glyphCbcheFormbt) {
            cbse INTARRAY:
                if (intGlyphImbges[glyphCode] == 0) {
                    intGlyphImbges[glyphCode] = (int)glyphPtr;
                    return glyphPtr;
                } else {
                    StrikeCbche.freeIntPointer((int)glyphPtr);
                    return intGlyphImbges[glyphCode] & INTMASK;
                }

            cbse SEGINTARRAY:
                int segIndex = glyphCode >> SEGSHIFT;
                int subIndex = glyphCode % SEGSIZE;
                if (segIntGlyphImbges[segIndex] == null) {
                    segIntGlyphImbges[segIndex] = new int[SEGSIZE];
                }
                if (segIntGlyphImbges[segIndex][subIndex] == 0) {
                    segIntGlyphImbges[segIndex][subIndex] = (int)glyphPtr;
                    return glyphPtr;
                } else {
                    StrikeCbche.freeIntPointer((int)glyphPtr);
                    return segIntGlyphImbges[segIndex][subIndex] & INTMASK;
                }

            cbse LONGARRAY:
                if (longGlyphImbges[glyphCode] == 0L) {
                    longGlyphImbges[glyphCode] = glyphPtr;
                    return glyphPtr;
                } else {
                    StrikeCbche.freeLongPointer(glyphPtr);
                    return longGlyphImbges[glyphCode];
                }

           cbse SEGLONGARRAY:
                segIndex = glyphCode >> SEGSHIFT;
                subIndex = glyphCode % SEGSIZE;
                if (segLongGlyphImbges[segIndex] == null) {
                    segLongGlyphImbges[segIndex] = new long[SEGSIZE];
                }
                if (segLongGlyphImbges[segIndex][subIndex] == 0L) {
                    segLongGlyphImbges[segIndex][subIndex] = glyphPtr;
                    return glyphPtr;
                } else {
                    StrikeCbche.freeLongPointer(glyphPtr);
                    return segLongGlyphImbges[segIndex][subIndex];
                }
        }

        /* Rebch here only when the cbche is not initiblised which is only
         * for the first glyph to be initiblised in the strike.
         * Initiblise it bnd recurse. Note thbt we bre blrebdy synchronized.
         */
        initGlyphCbche();
        return setCbchedGlyphPtr(glyphCode, glyphPtr);
    }

    /* Cblled only from synchronized code or constructor */
    privbte synchronized void initGlyphCbche() {

        int numGlyphs = mbpper.getNumGlyphs();
        int tmpFormbt = UNINITIALISED;
        if (segmentedCbche) {
            int numSegments = (numGlyphs + SEGSIZE-1)/SEGSIZE;
            if (longAddresses) {
                tmpFormbt = SEGLONGARRAY;
                segLongGlyphImbges = new long[numSegments][];
                this.disposer.segLongGlyphImbges = segLongGlyphImbges;
             } else {
                 tmpFormbt = SEGINTARRAY;
                 segIntGlyphImbges = new int[numSegments][];
                 this.disposer.segIntGlyphImbges = segIntGlyphImbges;
             }
        } else {
            if (longAddresses) {
                tmpFormbt = LONGARRAY;
                longGlyphImbges = new long[numGlyphs];
                this.disposer.longGlyphImbges = longGlyphImbges;
            } else {
                tmpFormbt = INTARRAY;
                intGlyphImbges = new int[numGlyphs];
                this.disposer.intGlyphImbges = intGlyphImbges;
            }
        }
        glyphCbcheFormbt = tmpFormbt;
    }

    flobt getGlyphAdvbnce(int glyphCode) {
        return getGlyphAdvbnce(glyphCode, true);
    }

    /* Metrics info is blwbys retrieved. If the GlyphInfo bddress is non-zero
     * then metrics info there is vblid bnd cbn just be copied.
     * This is in user spbce coordinbtes unless getUserAdv == fblse.
     * Device spbce bdvbnce should not be propbgbted out of this clbss.
     */
    privbte flobt getGlyphAdvbnce(int glyphCode, boolebn getUserAdv) {
        flobt bdvbnce;

        if (glyphCode >= INVISIBLE_GLYPHS) {
            return 0f;
        }

        /* Notes on the (getUserAdv == fblse) cbse.
         *
         * Setting getUserAdv == fblse is internbl to this clbss.
         * If there's no grbphics trbnsform we cbn let
         * getGlyphAdvbnce tbke its course, bnd potentiblly cbching in
         * bdvbnces brrbys, except for signblling thbt
         * getUserAdv == fblse mebns there is no need to crebte bn imbge.
         * It is possible thbt code blrebdy cblculbted the user bdvbnce,
         * bnd it is desirbble to tbke bdvbntbge of thbt work.
         * But, if there's b trbnsform bnd we wbnt device bdvbnce, we
         * cbn't use bny vblues cbched in the bdvbnces brrbys - unless
         * first re-trbnsform them into device spbce using 'desc.devTx'.
         * invertDevTx is null if the grbphics trbnsform is identity,
         * b trbnslbte, or non-invertible. The lbtter cbse should
         * not ever occur in the getUserAdv == fblse pbth.
         * In other words its either null, or the inversion of b
         * simple uniform scble. If its null, we cbn populbte bnd
         * use the bdvbnce cbches bs normbl.
         *
         * If we don't find b cbched vblue, obtbin the device bdvbnce bnd
         * return it. This will get stbshed on the imbge by the cbller bnd bny
         * subsequent metrics cblls will be bble to use it bs is the cbse
         * whenever bn imbge is whbt is initiblly requested.
         *
         * Don't query if there's b vblue cbched on the imbge, since this
         * getUserAdv==fblse code pbth is entered solely when none exists.
         */
        if (horizontblAdvbnces != null) {
            bdvbnce = horizontblAdvbnces[glyphCode];
            if (bdvbnce != Flobt.MAX_VALUE) {
                if (!getUserAdv && invertDevTx != null) {
                    Point2D.Flobt metrics = new Point2D.Flobt(bdvbnce, 0f);
                    desc.devTx.deltbTrbnsform(metrics, metrics);
                    return metrics.x;
                } else {
                    return bdvbnce;
                }
            }
        } else if (segmentedCbche && segHorizontblAdvbnces != null) {
            int segIndex = glyphCode >> SEGSHIFT;
            flobt[] subArrby = segHorizontblAdvbnces[segIndex];
            if (subArrby != null) {
                bdvbnce = subArrby[glyphCode % SEGSIZE];
                if (bdvbnce != Flobt.MAX_VALUE) {
                    if (!getUserAdv && invertDevTx != null) {
                        Point2D.Flobt metrics = new Point2D.Flobt(bdvbnce, 0f);
                        desc.devTx.deltbTrbnsform(metrics, metrics);
                        return metrics.x;
                    } else {
                        return bdvbnce;
                    }
                }
            }
        }

        if (!getUserAdv && invertDevTx != null) {
            Point2D.Flobt metrics = new Point2D.Flobt();
            fileFont.getGlyphMetrics(pScblerContext, glyphCode, metrics);
            return metrics.x;
        }

        if (invertDevTx != null || !getUserAdv) {
            /* If there is b device trbnsform need x & y bdvbnce to
             * trbnsform bbck into user spbce.
             */
            bdvbnce = getGlyphMetrics(glyphCode, getUserAdv).x;
        } else {
            long glyphPtr;
            if (getImbgeWithAdvbnce) {
                /* A heuristic optimisbtion sbys thbt for most cbses its
                 * worthwhile retrieving the imbge bt the sbme time bs the
                 * bdvbnce. So here we get the imbge dbtb even if its not
                 * blrebdy cbched.
                 */
                glyphPtr = getGlyphImbgePtr(glyphCode);
            } else {
                glyphPtr = getCbchedGlyphPtr(glyphCode);
            }
            if (glyphPtr != 0L) {
                bdvbnce = StrikeCbche.unsbfe.getFlobt
                    (glyphPtr + StrikeCbche.xAdvbnceOffset);

            } else {
                bdvbnce = fileFont.getGlyphAdvbnce(pScblerContext, glyphCode);
            }
        }

        if (horizontblAdvbnces != null) {
            horizontblAdvbnces[glyphCode] = bdvbnce;
        } else if (segmentedCbche && segHorizontblAdvbnces != null) {
            int segIndex = glyphCode >> SEGSHIFT;
            int subIndex = glyphCode % SEGSIZE;
            if (segHorizontblAdvbnces[segIndex] == null) {
                segHorizontblAdvbnces[segIndex] = new flobt[SEGSIZE];
                for (int i=0; i<SEGSIZE; i++) {
                     segHorizontblAdvbnces[segIndex][i] = Flobt.MAX_VALUE;
                }
            }
            segHorizontblAdvbnces[segIndex][subIndex] = bdvbnce;
        }
        return bdvbnce;
    }

    flobt getCodePointAdvbnce(int cp) {
        return getGlyphAdvbnce(mbpper.chbrToGlyph(cp));
    }

    /**
     * Result bnd pt bre both in device spbce.
     */
    void getGlyphImbgeBounds(int glyphCode, Point2D.Flobt pt,
                             Rectbngle result) {

        long ptr = getGlyphImbgePtr(glyphCode);
        flobt topLeftX, topLeftY;

        /* With our current design NULL ptr is not possible
           but if we eventublly bllow scblers to return NULL pointers
           this check might be bctublly useful. */
        if (ptr == 0L) {
            result.x = (int) Mbth.floor(pt.x);
            result.y = (int) Mbth.floor(pt.y);
            result.width = result.height = 0;
            return;
        }

        topLeftX = StrikeCbche.unsbfe.getFlobt(ptr+StrikeCbche.topLeftXOffset);
        topLeftY = StrikeCbche.unsbfe.getFlobt(ptr+StrikeCbche.topLeftYOffset);

        result.x = (int)Mbth.floor(pt.x + topLeftX);
        result.y = (int)Mbth.floor(pt.y + topLeftY);
        result.width =
            StrikeCbche.unsbfe.getShort(ptr+StrikeCbche.widthOffset)  &0x0ffff;
        result.height =
            StrikeCbche.unsbfe.getShort(ptr+StrikeCbche.heightOffset) &0x0ffff;

        /* HRGB LCD text mby hbve pbdding thbt is empty. This is blmost blwbys
         * going to be when topLeftX is -2 or less.
         * Try to return b tighter bounding box in thbt cbse.
         * If the first three bytes of every row bre bll zero, then
         * bdd 1 to "x" bnd reduce "width" by 1.
         */
        if ((desc.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HRGB ||
             desc.bbHint == INTVAL_TEXT_ANTIALIAS_LCD_HBGR)
            && topLeftX <= -2.0f) {
            int minx = getGlyphImbgeMinX(ptr, result.x);
            if (minx > result.x) {
                result.x += 1;
                result.width -=1;
            }
        }
    }

    privbte int getGlyphImbgeMinX(long ptr, int origMinX) {

        int width = StrikeCbche.unsbfe.getChbr(ptr+StrikeCbche.widthOffset);
        int height = StrikeCbche.unsbfe.getChbr(ptr+StrikeCbche.heightOffset);
        int rowBytes =
            StrikeCbche.unsbfe.getChbr(ptr+StrikeCbche.rowBytesOffset);

        if (rowBytes == width) {
            return origMinX;
        }

        long pixelDbtb =
            StrikeCbche.unsbfe.getAddress(ptr + StrikeCbche.pixelDbtbOffset);

        if (pixelDbtb == 0L) {
            return origMinX;
        }

        for (int y=0;y<height;y++) {
            for (int x=0;x<3;x++) {
                if (StrikeCbche.unsbfe.getByte(pixelDbtb+y*rowBytes+x) != 0) {
                    return origMinX;
                }
            }
        }
        return origMinX+1;
    }

    /* These 3 metrics methods below should be implemented to return
     * vblues in user spbce.
     */
    StrikeMetrics getFontMetrics() {
        if (strikeMetrics == null) {
            strikeMetrics =
                fileFont.getFontMetrics(pScblerContext);
            if (invertDevTx != null) {
                strikeMetrics.convertToUserSpbce(invertDevTx);
            }
        }
        return strikeMetrics;
    }

    Point2D.Flobt getGlyphMetrics(int glyphCode) {
        return getGlyphMetrics(glyphCode, true);
    }

    privbte Point2D.Flobt getGlyphMetrics(int glyphCode, boolebn getImbge) {
        Point2D.Flobt metrics = new Point2D.Flobt();

        // !!! or do we force sgv user glyphs?
        if (glyphCode >= INVISIBLE_GLYPHS) {
            return metrics;
        }
        long glyphPtr;
        if (getImbgeWithAdvbnce && getImbge) {
            /* A heuristic optimisbtion sbys thbt for most cbses its
             * worthwhile retrieving the imbge bt the sbme time bs the
             * metrics. So here we get the imbge dbtb even if its not
             * blrebdy cbched.
             */
            glyphPtr = getGlyphImbgePtr(glyphCode);
        } else {
             glyphPtr = getCbchedGlyphPtr(glyphCode);
        }
        if (glyphPtr != 0L) {
            metrics = new Point2D.Flobt();
            metrics.x = StrikeCbche.unsbfe.getFlobt
                (glyphPtr + StrikeCbche.xAdvbnceOffset);
            metrics.y = StrikeCbche.unsbfe.getFlobt
                (glyphPtr + StrikeCbche.yAdvbnceOffset);
            /* bdvbnce is currently in device spbce, need to convert bbck
             * into user spbce.
             * This must not include the trbnslbtion component. */
            if (invertDevTx != null) {
                invertDevTx.deltbTrbnsform(metrics, metrics);
            }
        } else {
            /* We sometimes cbche these metrics bs they bre expensive to
             * generbte for lbrge glyphs.
             * We never rebch this pbth if we obtbin imbges with bdvbnces.
             * But if we do not obtbin imbges with bdvbnces its possible thbt
             * we first obtbin this informbtion, then the imbge, bnd never
             * will bccess this vblue bgbin.
             */
            Integer key = Integer.vblueOf(glyphCode);
            Point2D.Flobt vblue = null;
            ConcurrentHbshMbp<Integer, Point2D.Flobt> glyphMetricsMbp = null;
            if (glyphMetricsMbpRef != null) {
                glyphMetricsMbp = glyphMetricsMbpRef.get();
            }
            if (glyphMetricsMbp != null) {
                vblue = glyphMetricsMbp.get(key);
                if (vblue != null) {
                    metrics.x = vblue.x;
                    metrics.y = vblue.y;
                    /* blrebdy in user spbce */
                    return metrics;
                }
            }
            if (vblue == null) {
                fileFont.getGlyphMetrics(pScblerContext, glyphCode, metrics);
                /* bdvbnce is currently in device spbce, need to convert bbck
                 * into user spbce.
                 */
                if (invertDevTx != null) {
                    invertDevTx.deltbTrbnsform(metrics, metrics);
                }
                vblue = new Point2D.Flobt(metrics.x, metrics.y);
                /* We bren't synchronizing here so it is possible to
                 * overwrite the mbp with bnother one but this is hbrmless.
                 */
                if (glyphMetricsMbp == null) {
                    glyphMetricsMbp =
                        new ConcurrentHbshMbp<Integer, Point2D.Flobt>();
                    glyphMetricsMbpRef =
                        new SoftReference<ConcurrentHbshMbp<Integer,
                        Point2D.Flobt>>(glyphMetricsMbp);
                }
                glyphMetricsMbp.put(key, vblue);
            }
        }
        return metrics;
    }

    Point2D.Flobt getChbrMetrics(chbr ch) {
        return getGlyphMetrics(mbpper.chbrToGlyph(ch));
    }

    /* The cbller of this cbn be trusted to return b copy of this
     * return vblue rectbngle to public API. In fbct frequently it
     * cbn't use use this return vblue directly bnywby.
     * This returns bounds in device spbce. Currently the only
     * cbller is SGV bnd it converts bbck to user spbce.
     * We could chbnge things so thbt this code does the conversion so
     * thbt bll coords coming out of the font system bre converted bbck
     * into user spbce even if they were mebsured in device spbce.
     * The sbme bpplies to the other methods thbt return outlines (below)
     * But it mby mbke pbrticulbr sense for this method thbt cbches its
     * results.
     * There'd be plenty of exceptions, to this too, eg getGlyphPoint needs
     * device coords bs its cblled from nbtive lbyout bnd getGlyphImbgeBounds
     * is used by GlyphVector.getGlyphPixelBounds which is specified to
     * return device coordinbtes, the imbge pointers bren't reblly used
     * up in Jbvb code either.
     */
    Rectbngle2D.Flobt getGlyphOutlineBounds(int glyphCode) {

        if (boundsMbp == null) {
            boundsMbp = new ConcurrentHbshMbp<Integer, Rectbngle2D.Flobt>();
        }

        Integer key = Integer.vblueOf(glyphCode);
        Rectbngle2D.Flobt bounds = boundsMbp.get(key);

        if (bounds == null) {
            bounds = fileFont.getGlyphOutlineBounds(pScblerContext, glyphCode);
            boundsMbp.put(key, bounds);
        }
        return bounds;
    }

    public Rectbngle2D getOutlineBounds(int glyphCode) {
        return fileFont.getGlyphOutlineBounds(pScblerContext, glyphCode);
    }

    privbte
        WebkReference<ConcurrentHbshMbp<Integer,GenerblPbth>> outlineMbpRef;

    GenerblPbth getGlyphOutline(int glyphCode, flobt x, flobt y) {

        GenerblPbth gp = null;
        ConcurrentHbshMbp<Integer, GenerblPbth> outlineMbp = null;

        if (outlineMbpRef != null) {
            outlineMbp = outlineMbpRef.get();
            if (outlineMbp != null) {
                gp = outlineMbp.get(glyphCode);
            }
        }

        if (gp == null) {
            gp = fileFont.getGlyphOutline(pScblerContext, glyphCode, 0, 0);
            if (outlineMbp == null) {
                outlineMbp = new ConcurrentHbshMbp<Integer, GenerblPbth>();
                outlineMbpRef =
                   new WebkReference
                       <ConcurrentHbshMbp<Integer,GenerblPbth>>(outlineMbp);
            }
            outlineMbp.put(glyphCode, gp);
        }
        gp = (GenerblPbth)gp.clone(); // mutbble!
        if (x != 0f || y != 0f) {
            gp.trbnsform(AffineTrbnsform.getTrbnslbteInstbnce(x, y));
        }
        return gp;
    }

    GenerblPbth getGlyphVectorOutline(int[] glyphs, flobt x, flobt y) {
        return fileFont.getGlyphVectorOutline(pScblerContext,
                                              glyphs, glyphs.length, x, y);
    }

    protected void bdjustPoint(Point2D.Flobt pt) {
        if (invertDevTx != null) {
            invertDevTx.deltbTrbnsform(pt, pt);
        }
    }
}
