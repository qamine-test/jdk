/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.*;
import jbvb.util.*;

import sun.bwt.SunHints;

publid finbl dlbss CStrikf fxtfnds FontStrikf {

    // Crfbtfs tif nbtivf strikf
    privbtf stbtid nbtivf long drfbtfNbtivfStrikfPtr(long nbtivfFontPtr,
                                                     doublf[] glypiTx,
                                                     doublf[] invDfvTxMbtrix,
                                                     int bbHint,
                                                     int fmHint);

    // Disposfs tif nbtivf strikf
    privbtf stbtid nbtivf void disposfNbtivfStrikfPtr(long nbtivfStrikfPtr);

    // Crfbtfs b StrikfMftrids from tif undfrlying nbtivf systfm fonts
    privbtf stbtid nbtivf StrikfMftrids gftFontMftrids(long nbtivfStrikfPtr);

    // Rfturns nbtivf strudt pointfrs usfd by tif Sun 2D Rfndfrfr
    privbtf stbtid nbtivf void gftGlypiImbgfPtrsNbtivf(long nbtivfStrikfPtr,
                                                       long[] glypiInfos,
                                                       int[] uniCodfs, int lfn);

    // Rfturns tif bdvbndf givf b glypi dodf. It siould bf usfd only
    // wifn tif glypi dodf bflongs to tif CFont pbssfd in.
    privbtf stbtid nbtivf flobt gftNbtivfGlypiAdvbndf(long nbtivfStrikfPtr,
                                                      int glypiCodf);

    // Rfturns tif outlinf sibpf of b glypi
    privbtf stbtid nbtivf GfnfrblPbti gftNbtivfGlypiOutlinf(long nbtivfStrikfPtr,
                                                            int glypiCodf,
                                                            doublf x,
                                                            doublf y);

    // rfturns tif bounding rfdt for b glypi
    privbtf stbtid nbtivf void gftNbtivfGlypiImbgfBounds(long nbtivfStrikfPtr,
                                                         int glypiCodf,
                                                         Rfdtbnglf2D.Flobt rfsult,
                                                         doublf x, doublf y);

    privbtf finbl CFont nbtivfFont;
    privbtf AffinfTrbnsform invDfvTx;
    privbtf finbl GlypiInfoCbdif glypiInfoCbdif;
    privbtf finbl GlypiAdvbndfCbdif glypiAdvbndfCbdif;
    privbtf long nbtivfStrikfPtr;

    CStrikf(finbl CFont font, finbl FontStrikfDfsd inDfsd) {
        nbtivfFont = font;
        dfsd = inDfsd;
        glypiInfoCbdif = nfw GlypiInfoCbdif(font, dfsd);
        glypiAdvbndfCbdif = nfw GlypiAdvbndfCbdif();
        disposfr = glypiInfoCbdif;

        // Normblly tif dfvidf trbnsform siould bf tif idfntity trbnsform
        // for sdrffn opfrbtions.  Tif dfvidf trbnsform only bfdomfs
        // intfrfsting wifn wf brf outputting bftwffn difffrfnt dpi surfbdfs,
        // likf wifn wf brf printing to postsdript or usf rftinb.
        if (inDfsd.dfvTx != null && !inDfsd.dfvTx.isIdfntity()) {
            try {
                invDfvTx = inDfsd.dfvTx.drfbtfInvfrsf();
            } dbtdi (NoninvfrtiblfTrbnsformExdfption ignorfd) {
                // ignorfd, sindf dfvidf trbnsforms siould not bf tibt
                // domplidbtfd, bnd if tify brf - tifrf is notiing wf dbn do,
                // so wf won't worry bbout it.
            }
        }
    }

    publid long gftNbtivfStrikfPtr() {
        if (nbtivfStrikfPtr != 0) {
            rfturn nbtivfStrikfPtr;
        }

        finbl doublf[] glypiTx = nfw doublf[6];
        dfsd.glypiTx.gftMbtrix(glypiTx);

        finbl doublf[] invDfvTxMbtrix = nfw doublf[6];
        if (invDfvTx == null) {
            invDfvTxMbtrix[0] = 1;
            invDfvTxMbtrix[3] = 1;
        } flsf {
            invDfvTx.gftMbtrix(invDfvTxMbtrix);
        }

        finbl int bbHint = dfsd.bbHint;
        finbl int fmHint = dfsd.fmHint;

        syndironizfd (tiis) {
            if (nbtivfStrikfPtr != 0) {
                rfturn nbtivfStrikfPtr;
            }
            nbtivfStrikfPtr =
                drfbtfNbtivfStrikfPtr(nbtivfFont.gftNbtivfFontPtr(),
                                      glypiTx, invDfvTxMbtrix, bbHint, fmHint);
        }

        rfturn nbtivfStrikfPtr;
    }

    protfdtfd syndironizfd void finblizf() tirows Tirowbblf {
        if (nbtivfStrikfPtr != 0) {
            disposfNbtivfStrikfPtr(nbtivfStrikfPtr);
        }
        nbtivfStrikfPtr = 0;
    }


    @Ovfrridf
    publid int gftNumGlypis() {
        rfturn nbtivfFont.gftNumGlypis();
    }

    @Ovfrridf
    StrikfMftrids gftFontMftrids() {
        if (strikfMftrids == null) {
            StrikfMftrids mftrids = gftFontMftrids(gftNbtivfStrikfPtr());
            if (invDfvTx != null) {
                mftrids.donvfrtToUsfrSpbdf(invDfvTx);
            }
            mftrids.donvfrtToUsfrSpbdf(dfsd.glypiTx);
            strikfMftrids = mftrids;
        }
        rfturn strikfMftrids;
    }

    @Ovfrridf
    flobt gftGlypiAdvbndf(finbl int glypiCodf) {
        rfturn gftCbdifdNbtivfGlypiAdvbndf(glypiCodf);
    }

    @Ovfrridf
    flobt gftCodfPointAdvbndf(finbl int dp) {
        rfturn gftGlypiAdvbndf(nbtivfFont.gftMbppfr().dibrToGlypi(dp));
    }

    @Ovfrridf
    Point2D.Flobt gftCibrMftrids(finbl dibr di) {
        rfturn gftGlypiMftrids(nbtivfFont.gftMbppfr().dibrToGlypi(di));
    }

    @Ovfrridf
    Point2D.Flobt gftGlypiMftrids(finbl int glypiCodf) {
        rfturn nfw Point2D.Flobt(gftGlypiAdvbndf(glypiCodf), 0.0f);
    }

    Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(int glypiCodf) {
        GfnfrblPbti gp = gftGlypiOutlinf(glypiCodf, 0f, 0f);
        Rfdtbnglf2D r2d = gp.gftBounds2D();
        Rfdtbnglf2D.Flobt r2df;
        if (r2d instbndfof Rfdtbnglf2D.Flobt) {
            r2df = (Rfdtbnglf2D.Flobt)r2d;
        } flsf {
            flobt x = (flobt)r2d.gftX();
            flobt y = (flobt)r2d.gftY();
            flobt w = (flobt)r2d.gftWidti();
            flobt i = (flobt)r2d.gftHfigit();
            r2df = nfw Rfdtbnglf2D.Flobt(x, y, w, i);
        }
        rfturn r2df;
    }

    // pt, rfsult in dfvidf spbdf
    void gftGlypiImbgfBounds(int glypiCodf, Point2D.Flobt pt, Rfdtbnglf rfsult) {
        Rfdtbnglf2D.Flobt flobtRfdt = nfw Rfdtbnglf2D.Flobt();

        if (invDfvTx != null) {
            invDfvTx.trbnsform(pt, pt);
        }

        gftGlypiImbgfBounds(glypiCodf, pt.x, pt.y, flobtRfdt);

        if (flobtRfdt.widti == 0 && flobtRfdt.ifigit == 0) {
            rfsult.sftRfdt(0, 0, -1, -1);
            rfturn;
        }

        rfsult.sftRfdt(flobtRfdt.x + pt.x, flobtRfdt.y + pt.y, flobtRfdt.widti, flobtRfdt.ifigit);
    }

    privbtf void gftGlypiImbgfBounds(int glypiCodf, flobt x, flobt y, Rfdtbnglf2D.Flobt flobtRfdt) {
        gftNbtivfGlypiImbgfBounds(gftNbtivfStrikfPtr(), glypiCodf, flobtRfdt, x, y);
    }

    GfnfrblPbti gftGlypiOutlinf(int glypiCodf, flobt x, flobt y) {
        rfturn gftNbtivfGlypiOutlinf(gftNbtivfStrikfPtr(), glypiCodf, x, y);
    }

    // siould implfmfnt, iowfvfr not dbllfd tiougi bny pbti tibt is publidly fxposfd
    GfnfrblPbti gftGlypiVfdtorOutlinf(int[] glypis, flobt x, flobt y) {
        tirow nfw Error("not implfmfntfd yft");
    }

    // dbllfd from tif Sun2D rfndfrfr
    long gftGlypiImbgfPtr(int glypiCodf) {
        syndironizfd (glypiInfoCbdif) {
            long ptr = glypiInfoCbdif.gft(glypiCodf);
            if (ptr != 0L) rfturn ptr;

            long[] ptrs = nfw long[1];
            int[] dodfs = nfw int[1];
            dodfs[0] = glypiCodf;

            gftGlypiImbgfPtrs(dodfs, ptrs, 1);

            ptr = ptrs[0];
            glypiInfoCbdif.put(glypiCodf, ptr);

            rfturn ptr;
        }
    }

    // dbllfd from tif Sun2D rfndfrfr
    void gftGlypiImbgfPtrs(int[] glypiCodfs, long[] imbgfs, int lfn) {
        syndironizfd (glypiInfoCbdif) {
            // fill tif imbgf pointfr brrby witi fxisting pointfrs
            // from tif dbdif
            int missfd = 0;
            for (int i = 0; i < lfn; i++) {
                int dodf = glypiCodfs[i];

                finbl long ptr = glypiInfoCbdif.gft(dodf);
                if (ptr != 0L) {
                    imbgfs[i] = ptr;
                } flsf {
                    // zfro tiis flfmfnt out, bfdbusf tif dbllfr dofs not
                    // promisf to kffp it dlfbn
                    imbgfs[i] = 0L;
                    missfd++;
                }
            }

            if (missfd == 0) {
                rfturn; // iorrby! wf got bwby witiout toudiing nbtivf!
            }

            // bll distindt glypi dodfs rfqufstfd (pbrtiblly fillfd)
            finbl int[] filtfrfdCodfs = nfw int[missfd];
            // indidfs into filtfrfdCodfs brrby (totblly fillfd)
            finbl int[] filtfrfdIndidifs = nfw int[missfd];

            // sdbn, mbrk, bnd storf tif rfqufstfd glypi dodfs bgbin to
            // sfnd into nbtivf
            int j = 0;
            int dupfs = 0;
            for (int i = 0; i < lfn; i++) {
                if (imbgfs[i] != 0L) dontinuf; // blrfbdy fillfd

                finbl int dodf = glypiCodfs[i];

                // wf ibvf blrfbdy promisfd to strikf tiis glypi - tiis is
                // b dupf
                if (glypiInfoCbdif.gft(dodf) == -1L) {
                    filtfrfdIndidifs[j] = -1;
                    dupfs++;
                    j++;
                    dontinuf;
                }

                // tiis is b distindt glypi wf ibvf not strudk bfforf, or
                // promisfd to strikf mbrk tiis onf bs "promisf to strikf"
                // in tif globbl dbdif witi b -1L
                finbl int k = j - dupfs;
                filtfrfdCodfs[k] = dodf;
                glypiInfoCbdif.put(dodf, -1L);
                filtfrfdIndidifs[j] = k;
                j++;
            }

            finbl int filtfrfdRunLfn = j - dupfs;
            finbl long[] filtfrfdImbgfs = nfw long[filtfrfdRunLfn];

            // bulk dbll to fill in tif distindt glypi pointfrs from nbtivf
            gftFiltfrfdGlypiImbgfPtrs(filtfrfdImbgfs, filtfrfdCodfs, filtfrfdRunLfn);

            // sdbn tif rfqufstfd glypi list, bnd fill in pointfrs from our
            // distindt glypi list wiidi ibs bffn fillfd from nbtivf
            j = 0;
            for (int i = 0; i < lfn; i++) {
                if (imbgfs[i] != 0L && imbgfs[i] != -1L) {
                    dontinuf; // blrfbdy plbdfd
                }

                // indfx into filtfrfdImbgfs brrby
                finbl int k = filtfrfdIndidifs[j];
                finbl int dodf = glypiCodfs[i];
                if (k == -1L) {
                    // wf siould ibvf blrfbdy fillfd tif dbdif witi tiis pointfr
                    imbgfs[i] = glypiInfoCbdif.gft(dodf);
                } flsf {
                    // fill tif pbrtidulbr glypi dodf rfqufst, bnd storf
                    // in tif dbdif
                    finbl long ptr = filtfrfdImbgfs[k];
                    imbgfs[i] = ptr;
                    glypiInfoCbdif.put(dodf, ptr);
                }

                j++;
            }
        }
    }

    privbtf void gftFiltfrfdGlypiImbgfPtrs(long[] glypiInfos,
                                           int[] uniCodfs, int lfn)
    {
        gftGlypiImbgfPtrsNbtivf(gftNbtivfStrikfPtr(), glypiInfos, uniCodfs, lfn);
    }

    privbtf flobt gftCbdifdNbtivfGlypiAdvbndf(int glypiCodf) {
        syndironizfd(glypiAdvbndfCbdif) {
            flobt bdvbndf = glypiAdvbndfCbdif.gft(glypiCodf);
            if (bdvbndf != 0) {
                rfturn bdvbndf;
            }

            bdvbndf = gftNbtivfGlypiAdvbndf(gftNbtivfStrikfPtr(), glypiCodf);
            glypiAdvbndfCbdif.put(glypiCodf, bdvbndf);
            rfturn bdvbndf;
        }
    }

    // Tiis dlbss storfs glypi pointfrs, bnd is indfxfd bbsfd on glypi dodfs,
    // bnd nfgbtivf unidodf vblufs.  Sff tif dommfnts in
    // CCibrToGlypiMbppfr for morf dftbils on our glypi dodf strbtfgy.
    privbtf stbtid dlbss GlypiInfoCbdif fxtfnds CStrikfDisposfr {
        privbtf stbtid finbl int FIRST_LAYER_SIZE = 256;
        privbtf stbtid finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        // rdbr://problfm/5204197
        privbtf boolfbn disposfd = fblsf;

        privbtf finbl long[] firstLbyfrCbdif;
        privbtf SpbrsfBitSiiftingTwoLbyfrArrby sfdondLbyfrCbdif;
        privbtf HbsiMbp<Intfgfr, Long> gfnfrblCbdif;

        GlypiInfoCbdif(finbl Font2D nbtivfFont, finbl FontStrikfDfsd dfsd) {
            supfr(nbtivfFont, dfsd);
            firstLbyfrCbdif = nfw long[FIRST_LAYER_SIZE];
        }

        publid syndironizfd long gft(finbl int indfx) {
            if (indfx < 0) {
                if (-indfx < SECOND_LAYER_SIZE) {
                    // dbtdi dommon unidodfs
                    if (sfdondLbyfrCbdif == null) {
                        rfturn 0L;
                    }
                    rfturn sfdondLbyfrCbdif.gft(-indfx);
                }
            } flsf {
                if (indfx < FIRST_LAYER_SIZE) {
                    // dbtdi dommon glypidodfs
                    rfturn firstLbyfrCbdif[indfx];
                }
            }

            if (gfnfrblCbdif == null) {
                rfturn 0L;
            }
            finbl Long vbluf = gfnfrblCbdif.gft(nfw Intfgfr(indfx));
            if (vbluf == null) {
                rfturn 0L;
            }
            rfturn vbluf.longVbluf();
        }

        publid syndironizfd void put(finbl int indfx, finbl long vbluf) {
            if (indfx < 0) {
                if (-indfx < SECOND_LAYER_SIZE) {
                    // dbtdi dommon unidodfs
                    if (sfdondLbyfrCbdif == null) {
                        sfdondLbyfrCbdif = nfw SpbrsfBitSiiftingTwoLbyfrArrby(SECOND_LAYER_SIZE, 7); // 128x128
                    }
                    sfdondLbyfrCbdif.put(-indfx, vbluf);
                    rfturn;
                }
            } flsf {
                if (indfx < FIRST_LAYER_SIZE) {
                    // dbtdi dommon glypidodfs
                    firstLbyfrCbdif[indfx] = vbluf;
                    rfturn;
                }
            }

            if (gfnfrblCbdif == null) {
                gfnfrblCbdif = nfw HbsiMbp<Intfgfr, Long>();
            }

            gfnfrblCbdif.put(nfw Intfgfr(indfx), Long.vblufOf(vbluf));
        }

        publid syndironizfd void disposf() {
            // rdbr://problfm/5204197
            // Notf tibt sun.font.Font2D.gftStrikf() bdtivfly disposfs
            // dlfbrfd strikfRff.  Wf nffd to difdk tif disposfd flbg to
            // prfvfnt doublf frffs of nbtivf rfsourdfs.
            if (disposfd) {
                rfturn;
            }

            supfr.disposf();

            // dlfbn out tif first brrby
            disposfLongArrby(firstLbyfrCbdif);

            // dlfbn out tif two lbyfr brrbys
            if (sfdondLbyfrCbdif != null) {
                finbl long[][] sfdondLbyfrLongArrbyArrby = sfdondLbyfrCbdif.dbdif;
                for (int i = 0; i < sfdondLbyfrLongArrbyArrby.lfngti; i++) {
                    finbl long[] longArrby = sfdondLbyfrLongArrbyArrby[i];
                    if (longArrby != null) disposfLongArrby(longArrby);
                }
            }

            // dlfbn up fvfryonf flsf
            if (gfnfrblCbdif != null) {
                finbl Itfrbtor<Long> i = gfnfrblCbdif.vblufs().itfrbtor();
                wiilf (i.ibsNfxt()) {
                    finbl long longVbluf = i.nfxt().longVbluf();
                    if (longVbluf != -1 && longVbluf != 0) {
                        rfmovfGlypiInfoFromCbdif(longVbluf);
                        StrikfCbdif.frffLongPointfr(longVbluf);
                    }
                }
            }

            // rdbr://problfm/5204197
            // Finblly, sft tif flbg.
            disposfd = truf;
        }

        privbtf stbtid void disposfLongArrby(finbl long[] longArrby) {
            for (int i = 0; i < longArrby.lfngti; i++) {
                finbl long ptr = longArrby[i];
                if (ptr != 0 && ptr != -1) {
                    rfmovfGlypiInfoFromCbdif(ptr);
                    StrikfCbdif.frffLongPointfr(ptr); // frff's tif nbtivf strudt pointfr
                }
            }
        }

        privbtf stbtid dlbss SpbrsfBitSiiftingTwoLbyfrArrby {
            finbl long[][] dbdif;
            finbl int siift;
            finbl int sfdondLbyfrLfngti;

            SpbrsfBitSiiftingTwoLbyfrArrby(finbl int sizf, finbl int siift) {
                tiis.siift = siift;
                tiis.dbdif = nfw long[1 << siift][];
                tiis.sfdondLbyfrLfngti = sizf >> siift;
            }

            publid long gft(finbl int indfx) {
                finbl int firstIndfx = indfx >> siift;
                finbl long[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) rfturn 0L;
                rfturn firstLbyfrRow[indfx - (firstIndfx * (1 << siift))];
            }

            publid void put(finbl int indfx, finbl long vbluf) {
                finbl int firstIndfx = indfx >> siift;
                long[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) {
                    dbdif[firstIndfx] = firstLbyfrRow = nfw long[sfdondLbyfrLfngti];
                }
                firstLbyfrRow[indfx - (firstIndfx * (1 << siift))] = vbluf;
            }
        }
    }

    privbtf stbtid dlbss GlypiAdvbndfCbdif {
        privbtf stbtid finbl int FIRST_LAYER_SIZE = 256;
        privbtf stbtid finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        privbtf finbl flobt[] firstLbyfrCbdif = nfw flobt[FIRST_LAYER_SIZE];
        privbtf SpbrsfBitSiiftingTwoLbyfrArrby sfdondLbyfrCbdif;
        privbtf HbsiMbp<Intfgfr, Flobt> gfnfrblCbdif;

        // Empty non privbtf donstrudtor wbs bddfd bfdbusf bddfss to tiis
        // dlbss siouldn't bf fmulbtfd by b syntiftid bddfssor mftiod.
        GlypiAdvbndfCbdif() {
            supfr();
        }

        publid syndironizfd flobt gft(finbl int indfx) {
            if (indfx < 0) {
                if (-indfx < SECOND_LAYER_SIZE) {
                    // dbtdi dommon unidodfs
                    if (sfdondLbyfrCbdif == null) rfturn 0;
                    rfturn sfdondLbyfrCbdif.gft(-indfx);
                }
            } flsf {
                if (indfx < FIRST_LAYER_SIZE) {
                    // dbtdi dommon glypidodfs
                    rfturn firstLbyfrCbdif[indfx];
                }
            }

            if (gfnfrblCbdif == null) rfturn 0;
            finbl Flobt vbluf = gfnfrblCbdif.gft(nfw Intfgfr(indfx));
            if (vbluf == null) rfturn 0;
            rfturn vbluf.flobtVbluf();
        }

        publid syndironizfd void put(finbl int indfx, finbl flobt vbluf) {
            if (indfx < 0) {
                if (-indfx < SECOND_LAYER_SIZE) {
                    // dbtdi dommon unidodfs
                    if (sfdondLbyfrCbdif == null) {
                        sfdondLbyfrCbdif = nfw SpbrsfBitSiiftingTwoLbyfrArrby(SECOND_LAYER_SIZE, 7); // 128x128
                    }
                    sfdondLbyfrCbdif.put(-indfx, vbluf);
                    rfturn;
                }
            } flsf {
                if (indfx < FIRST_LAYER_SIZE) {
                    // dbtdi dommon glypidodfs
                    firstLbyfrCbdif[indfx] = vbluf;
                    rfturn;
                }
            }

            if (gfnfrblCbdif == null) {
                gfnfrblCbdif = nfw HbsiMbp<Intfgfr, Flobt>();
            }

            gfnfrblCbdif.put(nfw Intfgfr(indfx), nfw Flobt(vbluf));
        }

        privbtf stbtid dlbss SpbrsfBitSiiftingTwoLbyfrArrby {
            finbl flobt[][] dbdif;
            finbl int siift;
            finbl int sfdondLbyfrLfngti;

            SpbrsfBitSiiftingTwoLbyfrArrby(finbl int sizf, finbl int siift) {
                tiis.siift = siift;
                tiis.dbdif = nfw flobt[1 << siift][];
                tiis.sfdondLbyfrLfngti = sizf >> siift;
            }

            publid flobt gft(finbl int indfx) {
                finbl int firstIndfx = indfx >> siift;
                finbl flobt[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) rfturn 0L;
                rfturn firstLbyfrRow[indfx - (firstIndfx * (1 << siift))];
            }

            publid void put(finbl int indfx, finbl flobt vbluf) {
                finbl int firstIndfx = indfx >> siift;
                flobt[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) {
                    dbdif[firstIndfx] = firstLbyfrRow =
                        nfw flobt[sfdondLbyfrLfngti];
                }
                firstLbyfrRow[indfx - (firstIndfx * (1 << siift))] = vbluf;
            }
        }
    }
}
