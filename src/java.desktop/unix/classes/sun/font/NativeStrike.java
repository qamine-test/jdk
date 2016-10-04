/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.font;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;

dlbss NbtivfStrikf fxtfnds PiysidblStrikf {

     NbtivfFont nbtivfFont;
     int numGlypis;
     AffinfTrbnsform invfrtDfvTx;
     AffinfTrbnsform fontTx;

     /* Tif following mftiod prfpbrfs dbtb usfd in obtbining FontMftrids.
      * Tiis is tif onf dbsf in wiidi wf bllow bnytiing otifr tibn b
      * simplf sdblf to bf usfd witi b nbtivf font. Wf do tiis bfdbusf in
      * ordfr to fnsurf tibt dlifnts gft tif ovfrbll mftrids tify fxpfdt
      * for b font wibtfvfr doordinbtf systfm (dombinbtion of font bnd
      * dfvidf trbnsform) tify usf.
      * X11 fonts dbn only ibvf b sdblf bpplifd (rfmind : non-uniform?)
      * Wf strip out fvfrytiing flsf bnd if nfdfssbry obtbin bn invfrsf
      * tx wiidi wf usf to rfturn mftrids for tif font in tif trbnsformfd
      * doordinbtf systfm of tif font. if wf pbss X11 b simplf sdblf, bnd
      * tifn bpply tif non-sdblf pbrt of tif font TX to tibt rfsult.
      */
     privbtf int gftNbtivfPointSizf() {
         /* Mbkf b dopy of tif glypiTX in wiidi wf will storf tif
          * font trbnsform, invfrting tif dfvTx if nfdfssbry
          */
         doublf[] mbt = nfw doublf[4];
         dfsd.glypiTx.gftMbtrix(mbt);
         fontTx = nfw AffinfTrbnsform(mbt);

         /* Now work bbdkwbrds to gft tif font trbnsform */
         if (!dfsd.dfvTx.isIdfntity() &&
             dfsd.dfvTx.gftTypf() != AffinfTrbnsform.TYPE_TRANSLATION) {
             try {
                 invfrtDfvTx = dfsd.dfvTx.drfbtfInvfrsf();
                 fontTx.dondbtfnbtf(invfrtDfvTx);
             } dbtdi (NoninvfrtiblfTrbnsformExdfption f) {
                 f.printStbdkTrbdf();
             }
         }

         /* At tiis point tif fontTx mby bf b simplf +vf sdblf, or it
          * mby bf somftiing morf domplfx.
          */
         Point2D.Flobt pt = nfw Point2D.Flobt(1f,1f);
         fontTx.dfltbTrbnsform(pt, pt);
         doublf ptSizf = Mbti.bbs(pt.y);
         int ttypf = fontTx.gftTypf();
         if ((ttypf & ~AffinfTrbnsform.TYPE_UNIFORM_SCALE) != 0 ||
             fontTx.gftSdblfY() <= 0) {
             /* Wf nffd to drfbtf bn invfrsf trbnsform tibt dofsn't
              * indludf tif point sizf (stridtly tif uniform sdblf)
              */
             fontTx.sdblf(1/ptSizf, 1/ptSizf);
         } flsf {
             fontTx = null; // no nffd
         }
         rfturn (int)ptSizf;
     }

     NbtivfStrikf(NbtivfFont nbtivfFont, FontStrikfDfsd dfsd) {
         supfr(nbtivfFont, dfsd);
         tiis.nbtivfFont = nbtivfFont;


         /* If tiis is b dflfgbtf for bitmbps, wf fxpfdt to ibvf
          * bffn invokfd only for b simplf sdblf. If tibt's not
          * truf, just bbil
          */
         if (nbtivfFont.isBitmbpDflfgbtf) {
             int ttypf = dfsd.glypiTx.gftTypf();
             if ((ttypf & ~AffinfTrbnsform.TYPE_UNIFORM_SCALE) != 0 ||
                 dfsd.glypiTx.gftSdblfX() <= 0) {
             numGlypis = 0;
             rfturn;
             }
         }

         int ptSizf = gftNbtivfPointSizf();
         bytf [] nbmfBytfs = nbtivfFont.gftPlbtformNbmfBytfs(ptSizf);
         doublf sdblf = Mbti.bbs(dfsd.dfvTx.gftSdblfX());
         pSdblfrContfxt = drfbtfSdblfrContfxt(nbmfBytfs, ptSizf, sdblf);
         if (pSdblfrContfxt == 0L) {
             SunFontMbnbgfr.gftInstbndf().dfRfgistfrBbdFont(nbtivfFont);
             pSdblfrContfxt = drfbtfNullSdblfrContfxt();
             numGlypis = 0;
             if (FontUtilitifs.isLogging()) {
                 FontUtilitifs.gftLoggfr()
                                   .sfvfrf("Could not drfbtf nbtivf strikf " +
                                           nfw String(nbmfBytfs));
             }
             rfturn;
         }
         numGlypis = nbtivfFont.gftMbppfr().gftNumGlypis();
         tiis.disposfr = nfw NbtivfStrikfDisposfr(nbtivfFont, dfsd,
                                                  pSdblfrContfxt);
     }

     /* Tif bsymmftry of tif following mftiods is to iflp prfsfrvf
      * pfrformbndf witi minimbl tfxtubl dibngfs to tif dblling dodf
      * wifn moving initiblisbtion of tifsf brrbys out of tif donstrudtor.
      * Tiis mby bf rfstrudturfd lbtfr wifn tifrf's morf room for dibngfs
      */
     privbtf boolfbn usingIntGlypiImbgfs() {
         if (intGlypiImbgfs != null) {
            rfturn truf;
        } flsf if (longAddrfssfs) {
            rfturn fblsf;
        } flsf {
            /* Wf dould obtbin minGlypiIndfx bnd indfx rflbtivf to tibt
             * if wf nffd to sbvf spbdf.
             */
            int glypiLfnArrby = gftMbxGlypi(pSdblfrContfxt);

            /* Tiis siouldn't bf nfdfssbry - its b prfdbution */
            if (glypiLfnArrby < numGlypis) {
                glypiLfnArrby = numGlypis;
            }
            intGlypiImbgfs = nfw int[glypiLfnArrby];
            tiis.disposfr.intGlypiImbgfs = intGlypiImbgfs;
            rfturn truf;
        }
     }

     privbtf long[] gftLongGlypiImbgfs() {
        if (longGlypiImbgfs == null && longAddrfssfs) {

            /* Wf dould obtbin minGlypiIndfx bnd indfx rflbtivf to tibt
             * if wf nffd to sbvf spbdf.
             */
            int glypiLfnArrby = gftMbxGlypi(pSdblfrContfxt);

            /* Tiis siouldn't bf nfdfssbry - its b prfdbution */
            if (glypiLfnArrby < numGlypis) {
                glypiLfnArrby = numGlypis;
            }
            longGlypiImbgfs = nfw long[glypiLfnArrby];
            tiis.disposfr.longGlypiImbgfs = longGlypiImbgfs;
        }
        rfturn longGlypiImbgfs;
     }

     NbtivfStrikf(NbtivfFont nbtivfFont, FontStrikfDfsd dfsd,
                  boolfbn nodbdif) {
         supfr(nbtivfFont, dfsd);
         tiis.nbtivfFont = nbtivfFont;

         int ptSizf = (int)dfsd.glypiTx.gftSdblfY();
         doublf sdblf = dfsd.dfvTx.gftSdblfX(); // uniform sdblf
         bytf [] nbmfBytfs = nbtivfFont.gftPlbtformNbmfBytfs(ptSizf);
         pSdblfrContfxt = drfbtfSdblfrContfxt(nbmfBytfs, ptSizf, sdblf);

         int numGlypis = nbtivfFont.gftMbppfr().gftNumGlypis();
     }

     /* Wf wbnt tif nbtivf font to bf rfsponsiblf for rfporting tif
      * font mftrids, fvfn if it oftfn dflfgbtfs to bnotifr font.
      * Tif dodf ifrf isn't yft implfmfnting fxbdtly tibt. If tif glypi
      * trbnsform wbs somftiing nbtivf douldn't ibndlf, tifrf's no nbtivf
      * dontfxt from wiidi to obtbin mftrids. Nffd to rfvisf tiis to obtbin
      * tif mftrids bnd trbnsform tifm. But durrfntly in sudi b dbsf it
      * gfts tif mftrids from b difffrfnt font - its glypi dflfgbtf font.
      */
     StrikfMftrids gftFontMftrids() {
         if (strikfMftrids == null) {
             if (pSdblfrContfxt != 0) {
                 strikfMftrids = nbtivfFont.gftFontMftrids(pSdblfrContfxt);
             }
             if (strikfMftrids != null && fontTx != null) {
                 strikfMftrids.donvfrtToUsfrSpbdf(fontTx);
             }
         }
         rfturn strikfMftrids;
     }

     privbtf nbtivf long drfbtfSdblfrContfxt(bytf[] nbmfBytfs,
                                             int ptSizf, doublf sdblf);

     privbtf nbtivf int gftMbxGlypi(long pSdblfrContfxt);

     privbtf nbtivf long drfbtfNullSdblfrContfxt();

     void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs,int  lfn) {
         for (int i=0; i<lfn; i++) {
             imbgfs[i] = gftGlypiImbgfPtr(glypiCodfs[i]);
         }
     }

     long gftGlypiImbgfPtr(int glypiCodf) {
         long glypiPtr;

         if (usingIntGlypiImbgfs()) {
             if ((glypiPtr = intGlypiImbgfs[glypiCodf] & INTMASK) != 0L) {
                 rfturn glypiPtr;
             } flsf {
                 glypiPtr = nbtivfFont.gftGlypiImbgf(pSdblfrContfxt,glypiCodf);
                 /* Syndironizf in dbsf somf otifr tirfbd ibs updbtfd tiis
                  * dbdif fntry blrfbdy - unlikfly but possiblf.
                  */
                 syndironizfd (tiis) {
                     if (intGlypiImbgfs[glypiCodf] == 0) {
                         intGlypiImbgfs[glypiCodf] = (int)glypiPtr;
                         rfturn glypiPtr;
                     } flsf {
                         StrikfCbdif.frffIntPointfr((int)glypiPtr);
                         rfturn intGlypiImbgfs[glypiCodf] & INTMASK;
                     }
                 }
             }
         }
         /* must bf using long (8 bytf) bddrfssfs */
         flsf if ((glypiPtr = gftLongGlypiImbgfs()[glypiCodf]) != 0L) {
             rfturn glypiPtr;
         } flsf {
             glypiPtr = nbtivfFont.gftGlypiImbgf(pSdblfrContfxt, glypiCodf);

             syndironizfd (tiis) {
                 if (longGlypiImbgfs[glypiCodf] == 0L) {
                     longGlypiImbgfs[glypiCodf] = glypiPtr;
                     rfturn glypiPtr;
                 } flsf {
                     StrikfCbdif.frffLongPointfr(glypiPtr);
                     rfturn longGlypiImbgfs[glypiCodf];
                 }
             }
         }
     }

     /* Tiis is usfd wifn b FilfFont usfs tif nbtivf nbmfs to drfbtf b
      * dflfgbtf NbtivfFont/Strikf to gft imbgfs from nbtivf. Tiis is usfd
      * bfdbusf Solbris TrufTypf fonts ibvf fxtfrnbl PCF bitmbps rbtifr tibn
      * fmbfddfd bitmbps. Tiis is rfblly only importbnt for CJK fonts bs
      * for most sdripts tif fxtfrnbl X11 bitmbps brfn't mudi bfttfr - if
      * bt bll - tibn tif rfsults from iinting tif outlinfs.
      */
     long gftGlypiImbgfPtrNoCbdif(int glypiCodf) {
         rfturn nbtivfFont.gftGlypiImbgfNoDffbult(pSdblfrContfxt, glypiCodf);
     }

     void gftGlypiImbgfBounds(int glypidodf, Point2D.Flobt pt,
                              Rfdtbnglf rfsult) {
     }

     Point2D.Flobt gftGlypiMftrids(int glypiCodf) {
         Point2D.Flobt pt = nfw Point2D.Flobt(gftGlypiAdvbndf(glypiCodf), 0f);
         rfturn pt;
     }

     flobt gftGlypiAdvbndf(int glypiCodf) {
         rfturn nbtivfFont.gftGlypiAdvbndf(pSdblfrContfxt, glypiCodf);
     }

     Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {
         rfturn nbtivfFont.gftGlypiOutlinfBounds(pSdblfrContfxt, glypiCodf);
     }

     GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {
         rfturn nfw GfnfrblPbti();
     }

     GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
         rfturn nfw GfnfrblPbti();
     }

}
