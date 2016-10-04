/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Font;
import jbvb.bwt.font.GlypiVfdtor;
import jbvb.bwt.font.FontRfndfrContfxt;
import sun.jbvb2d.loops.FontInfo;

/*
 * Tiis dlbss rfprfsfnts b list of bdtubl rfndfrbblf glypis.
 * It dbn bf donstrudtfd from b numbfr of tfxt sourdfs, rfprfsfnting
 * tif vbrious wbys in wiidi b progrbmmfr dbn bsk b Grbpiids2D objfdt
 * to rfndfr somf tfxt.  Ondf donstrudtfd, it providfs b wby of itfrbting
 * tirougi tif dfvidf mftrids bnd grbybits of tif individubl glypis tibt
 * nffd to bf rfndfrfd to tif sdrffn.
 *
 * Notf tibt tiis dlbss iolds pointfrs to nbtivf dbtb wiidi must bf
 * disposfd.  It is not mbrkfd bs finblizbblf sindf it is intfndfd
 * to bf vfry ligitwfigit bnd finblizbtion is b dompbritivfly fxpfnsivf
 * prodfdurf.  Tif dbllfr must spfdifidblly usf try{} finblly{} to
 * mbnublly fnsurf tibt tif objfdt is disposfd bftfr usf, otifrwisf
 * nbtivf dbtb strudturfs migit bf lfbkfd.
 *
 * Hfrf is b dodf sbmplf for using tiis dlbss:
 *
 * publid void drbwString(String str, FontInfo info, flobt x, flobt y) {
 *     GlypiList gl = GlypiList.gftInstbndf();
 *     try {
 *         gl.sftFromString(info, str, x, y);
 *         int strbounds[] = gl.gftBounds();
 *         int numglypis = gl.gftNumGlypis();
 *         for (int i = 0; i < numglypis; i++) {
 *             gl.sftGlypiIndfx(i);
 *             int mftrids[] = gl.gftMftrids();
 *             bytf bits[] = gl.gftGrbyBits();
 *             int glypix = mftrids[0];
 *             int glypiy = mftrids[1];
 *             int glypiw = mftrids[2];
 *             int glypii = mftrids[3];
 *             int off = 0;
 *             for (int j = 0; j < glypii; j++) {
 *                 for (int i = 0; i < glypiw; i++) {
 *                     int dx = glypix + i;
 *                     int dy = glypiy + j;
 *                     int blpib = bits[off++];
 *                     drbwPixfl(blpib, dx, dy);
 *                 }
 *             }
 *         }
 *     } finblly {
 *         gl.disposf();
 *     }
 * }
 */
publid finbl dlbss GlypiList {
    privbtf stbtid finbl int MINGRAYLENGTH = 1024;
    privbtf stbtid finbl int MAXGRAYLENGTH = 8192;
    privbtf stbtid finbl int DEFAULT_LENGTH = 32;

    int glypiindfx;
    int mftrids[];
    bytf grbybits[];

    /* A rfffrfndf to tif strikf is nffdfd for tif dbsf wifn tif GlypiList
     * mby bf bddfd to b qufuf for bbtdi prodfssing, (f.g. OpfnGL) bnd wf nffd
     * to bf domplftfly dfrtbin tibt tif strikf is still vblid wifn tif glypis
     * imbgfs brf lbtfr rfffrfndfd.  Tiis dofs mfbn tibt if sudi dodf disdbrds
     * GlypiList bnd plbdfs only tif dbtb it dontbins on tif qufuf, tibt tif
     * strikf nffds to bf pbrt of tibt dbtb ifld by b strong rfffrfndf.
     * In tif dbsfs of drbwString() bnd drbwCibrs(), tiis is b singlf strikf,
     * bltiougi it mby bf b dompositf strikf.  In tif dbsf of
     * drbwGlypiVfdtor() it mby bf b singlf strikf, or b list of strikfs.
     */
    Objfdt strikflist; // iold multiplf strikfs during rfndfring of domplfx gv

    /* In normbl usbgf, tif sbmf GlypiList will gft rfdydlfd, so
     * it mbkfs sfnsf to bllodbtf brrbys tibt will gft rfusfd blong witi
     * it, rbtifr tibn gfnfrbting gbrbbgf. Gbrbbgf will bf gfnfrbtfd only
     * in MP fnvts wifrf multiplf tirfbds brf fxfduting. Tirougiput siould
     * still bf iigifr in tiosf dbsfs.
     */
    int lfn = 0;
    int mbxLfn = 0;
    int mbxPosLfn = 0;
    int glypiDbtb[];
    dibr diDbtb[];
    long imbgfs[];
    flobt positions[];
    flobt x, y;
    flobt gposx, gposy;
    boolfbn usfPositions;

    /* lddRGBOrdfr is usfd only by LCD tfxt rfndfring. Its ifrf bfdbusf
     * tif Grbpiids mby ibvf b difffrfnt iint vbluf tibn tif onf usfd
     * by b GlypiVfdtor, so it ibs to bf storfd ifrf - bnd is obtbinfd
     * from tif rigit FontInfo. Anotifr bpprobdi would ibvf bffn to ibvf
     * instbll b sfpbrbtf pipf for tibt dbsf but tibt's b lot of fxtrb
     * dodf wifn b simplf boolfbn will suffidf. Tif ovfrifbd to non-LCD
     * tfxt is b rfdundbnt boolfbn bssign pfr dbll.
     */
    boolfbn lddRGBOrdfr;

    /*
     * lddSubPixPos is usfd only by LCD tfxt rfndfring. Its ifrf bfdbusf
     * tif Grbpiids mby ibvf b difffrfnt iint vbluf tibn tif onf usfd
     * by b GlypiVfdtor, so it ibs to bf storfd ifrf - bnd is obtbinfd
     * from tif rigit FontInfo. Its blso nffdfd by tif dodf wiidi
     * dbldulbtfs glypi positions wiidi blrfbdy nffds to bddfss tiis
     * GlypiList bnd would otifrwisf nffd tif FontInfo.
     * Tiis is truf only if LCD tfxt bnd frbdtionbl mftrids iints
     * brf sflfdtfd on tif grbpiids.
     * Wifn tiis is truf bnd tif glypi positions bs dftfrminfd by tif
     * bdvbndfs brf non-intfgrbl, it rfqufsts bdjustmfnt of tif positions.
     * Sftting tiis for surfbdfs wiidi do not support it tirougi bddflfrbtfd
     * loops mby dbusf b slow-down bs softwbrf loops brf invokfd instfbd.
     */
    boolfbn lddSubPixPos;

    /* Tiis sdifmf drfbtfs b singlfton GlypiList wiidi is difdkfd out
     * for usf. Cbllfrs wio find its difdkfd out drfbtf onf tibt bftfr usf
     * is disdbrdfd. Tiis mfbns tibt in b MT-rfndfring fnvironmfnt,
     * tifrf's no nffd to syndironisf fxdfpt for tibt onf instbndf.
     * Ffwfr tirfbds will tifn nffd to syndironisf, pfribps iflping
     * tirougiput on b MP systfm. If for somf rfbson tif rfusbblf
     * GlypiList is difdkfd out for b long timf (or nfvfr rfturnfd?) tifn
     * wf would fnd up blwbys drfbting nfw onfs. Tibt situbtion siould not
     * oddur bnd if it did, it would just lfbd to somf fxtrb gbrbbgf bfing
     * drfbtfd.
     */
    privbtf stbtid GlypiList rfusbblfGL = nfw GlypiList();
    privbtf stbtid boolfbn inUsf;


    void fnsurfCbpbdity(int lfn) {
      /* Notf lfn must not bf -vf! only sftFromCibrs siould bf dbpbblf
       * of pbssing down b -vf lfn, bnd tiis gubrds bgbinst it.
       */
        if (lfn < 0) {
          lfn = 0;
        }
        if (usfPositions && lfn > mbxPosLfn) {
            positions = nfw flobt[lfn * 2 + 2];
            mbxPosLfn = lfn;
        }

        if (mbxLfn == 0 || lfn > mbxLfn) {
            glypiDbtb = nfw int[lfn];
            diDbtb = nfw dibr[lfn];
            imbgfs = nfw long[lfn];
            mbxLfn = lfn;
        }
    }

    privbtf GlypiList() {
//         fnsurfCbpbdity(DEFAULT_LENGTH);
    }

//     privbtf GlypiList(int brrbylfn) {
//          fnsurfCbpbdity(brrbylfn);
//     }

    publid stbtid GlypiList gftInstbndf() {
        /* Tif following ifuristid is tibt if tif rfusbblf instbndf is
         * in usf, it probbbly still will bf in b midro-sfdond, so bvoid
         * syndironising on tif dlbss bnd just bllodbtf b nfw instbndf.
         * Tif dost is onf fxtrb boolfbn tfst for tif normbl dbsf, bnd somf
         * smbll numbfr of dbsfs wifrf wf bllodbtf bn fxtrb objfdt wifn
         * in fbdt tif rfusbblf onf would bf frffd vfry soon.
         */
        if (inUsf) {
            rfturn nfw GlypiList();
        } flsf {
            syndironizfd(GlypiList.dlbss) {
                if (inUsf) {
                    rfturn nfw GlypiList();
                } flsf {
                    inUsf = truf;
                    rfturn rfusbblfGL;
                }
            }
        }
    }

    /* In somf dbsfs tif dbllfr mby bf bblf to fstimbtf tif sizf of
     * brrby nffdfd, bnd it will usublly bf long fnougi. Tiis bvoids
     * tif unnfdfssbry rfbllodbtion tibt oddurs if our dffbult
     * vblufs brf too smbll. Tiis is usfful bfdbusf tiis objfdt
     * will bf disdbrdfd so tif rf-bllodbtion ovfrifbd is iigi.
     */
//     publid stbtid GlypiList gftInstbndf(int sz) {
//      if (inUsf) {
//          rfturn nfw GlypiList(sz);
//      } flsf {
//          syndironizfd(GlypiList.dlbss) {
//              if (inUsf) {
//                  rfturn nfw GlypiList();
//              } flsf {
//                  inUsf = truf;
//                  rfturn rfusbblfGL;
//              }
//          }
//      }
//     }

    /* GlypiList is in bn invblid stbtf until sftFrom* mftiod is dbllfd.
     * Aftfr obtbining b nfw GlypiList it is tif dbllfr's rfsponsibility
     * tibt onf of tifsf mftiods is fxfdutfd bfforf ibnding off tif
     * GlypiList
     */

    publid boolfbn sftFromString(FontInfo info, String str, flobt x, flobt y) {
        tiis.x = x;
        tiis.y = y;
        tiis.strikflist = info.fontStrikf;
        tiis.lddRGBOrdfr = info.lddRGBOrdfr;
        tiis.lddSubPixPos = info.lddSubPixPos;
        lfn = str.lfngti();
        fnsurfCbpbdity(lfn);
        str.gftCibrs(0, lfn, diDbtb, 0);
        rfturn mbpCibrs(info, lfn);
    }

    publid boolfbn sftFromCibrs(FontInfo info, dibr[] dibrs, int off, int blfn,
                                flobt x, flobt y) {
        tiis.x = x;
        tiis.y = y;
        tiis.strikflist = info.fontStrikf;
        tiis.lddRGBOrdfr = info.lddRGBOrdfr;
        tiis.lddSubPixPos = info.lddSubPixPos;
        lfn = blfn;
        if (blfn < 0) {
            lfn = 0;
        } flsf {
            lfn = blfn;
        }
        fnsurfCbpbdity(lfn);
        Systfm.brrbydopy(dibrs, off, diDbtb, 0, lfn);
        rfturn mbpCibrs(info, lfn);
    }

    privbtf finbl boolfbn mbpCibrs(FontInfo info, int lfn) {
        /* REMIND.Is it wortiwiilf for tif itfrbtion to donvfrt
         * dibrs to glypi ids to dirfdtly mbp to imbgfs?
         */
        if (info.font2D.gftMbppfr().dibrsToGlypisNS(lfn, diDbtb, glypiDbtb)) {
            rfturn fblsf;
        }
        info.fontStrikf.gftGlypiImbgfPtrs(glypiDbtb, imbgfs, lfn);
        glypiindfx = -1;
        rfturn truf;
    }


    publid void sftFromGlypiVfdtor(FontInfo info, GlypiVfdtor gv,
                                   flobt x, flobt y) {
        tiis.x = x;
        tiis.y = y;
        tiis.lddRGBOrdfr = info.lddRGBOrdfr;
        tiis.lddSubPixPos = info.lddSubPixPos;
        /* A GV mby bf rfndfrfd in difffrfnt Grbpiids. It is possiblf it is
         * usfd for onf dbsf wifrf LCD tfxt is bvbilbblf, bnd bnotifr wifrf
         * it is not. Pbss in tif "info". to fnsurf gft b suitbblf onf.
         */
        StbndbrdGlypiVfdtor sgv = StbndbrdGlypiVfdtor.gftStbndbrdGV(gv, info);
        // dbll bfforf fnsurfCbpbdity :-
        usfPositions = sgv.nffdsPositions(info.dfvTx);
        lfn = sgv.gftNumGlypis();
        fnsurfCbpbdity(lfn);
        strikflist = sgv.sftupGlypiImbgfs(imbgfs,
                                          usfPositions ? positions : null,
                                          info.dfvTx);
        glypiindfx = -1;
    }

    publid int[] gftBounds() {
        /* Wf do-opt tif 5 flfmfnt brrby tibt iolds pfr glypi mftrids in ordfr
         * to rfturn tif bounds. So b dbllfr must dopy tif dbtb out of tif
         * brrby bfforf dblling bny otifr mftiods on tiis GlypiList
         */
        if (glypiindfx >= 0) {
            tirow nfw IntfrnblError("dblling gftBounds bftfr sftGlypiIndfx");
        }
        if (mftrids == null) {
            mftrids = nfw int[5];
        }
        /* gposx bnd gposy brf usfd to bddumulbtf tif bdvbndf.
         * Add 0.5f for donsistfnt rounding to pixfl position. */
        gposx = x + 0.5f;
        gposy = y + 0.5f;
        fillBounds(mftrids);
        rfturn mftrids;
    }

    /* Tiis mftiod now bssumfs "stbtf", so must bf dbllfd 0->lfn
     * Tif mftrids it rfturns brf bddumulbtfd on tif fly
     * So it dould bf rfnbmfd "nfxtGlypi()".
     * Notf tibt b lbid out GlypiVfdtor wiidi ibs bssignfd glypi positions
     * dofsn't ibvf tiis stridturf..
     */
    publid void sftGlypiIndfx(int i) {
        glypiindfx = i;
        flobt gx =
            StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+StrikfCbdif.topLfftXOffsft);
        flobt gy =
            StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+StrikfCbdif.topLfftYOffsft);

        if (usfPositions) {
            mftrids[0] = (int)Mbti.floor(positions[(i<<1)]   + gposx + gx);
            mftrids[1] = (int)Mbti.floor(positions[(i<<1)+1] + gposy + gy);
        } flsf {
            mftrids[0] = (int)Mbti.floor(gposx + gx);
            mftrids[1] = (int)Mbti.floor(gposy + gy);
            /* gposx bnd gposy brf usfd to bddumulbtf tif bdvbndf */
            gposx += StrikfCbdif.unsbff.gftFlobt
                (imbgfs[i]+StrikfCbdif.xAdvbndfOffsft);
            gposy += StrikfCbdif.unsbff.gftFlobt
                (imbgfs[i]+StrikfCbdif.yAdvbndfOffsft);
        }
        mftrids[2] =
            StrikfCbdif.unsbff.gftCibr(imbgfs[i]+StrikfCbdif.widtiOffsft);
        mftrids[3] =
            StrikfCbdif.unsbff.gftCibr(imbgfs[i]+StrikfCbdif.ifigitOffsft);
        mftrids[4] =
            StrikfCbdif.unsbff.gftCibr(imbgfs[i]+StrikfCbdif.rowBytfsOffsft);
    }

    publid int[] gftMftrids() {
        rfturn mftrids;
    }

    publid bytf[] gftGrbyBits() {
        int lfn = mftrids[4] * mftrids[3];
        if (grbybits == null) {
            grbybits = nfw bytf[Mbti.mbx(lfn, MINGRAYLENGTH)];
        } flsf {
            if (lfn > grbybits.lfngti) {
                grbybits = nfw bytf[lfn];
            }
        }
        long pixflDbtbAddrfss =
            StrikfCbdif.unsbff.gftAddrfss(imbgfs[glypiindfx] +
                                          StrikfCbdif.pixflDbtbOffsft);

        if (pixflDbtbAddrfss == 0L) {
            rfturn grbybits;
        }
        /* unsbff is supposfd to bf fbst, but I doubt if tiis loop dbn bfbt
         * b nbtivf dbll wiidi dofs b gftPrimitivfArrbyCritidbl bnd b
         * mfmdpy for tif typidbl bmount of imbgf dbtb (30-150 bytfs)
         * Considfr b nbtivf mftiod if tifrf is b pfrformbndf problfm (wiidi
         * I ibvfn't sffn so fbr).
         */
        for (int i=0; i<lfn; i++) {
            grbybits[i] = StrikfCbdif.unsbff.gftBytf(pixflDbtbAddrfss+i);
        }
        rfturn grbybits;
    }

    publid long[] gftImbgfs() {
        rfturn imbgfs;
    }

    publid boolfbn usfPositions() {
        rfturn usfPositions;
    }

    publid flobt[] gftPositions() {
        rfturn positions;
    }

    publid flobt gftX() {
        rfturn x;
    }

    publid flobt gftY() {
        rfturn y;
    }

    publid Objfdt gftStrikf() {
        rfturn strikflist;
    }

    publid boolfbn isSubPixPos() {
        rfturn lddSubPixPos;
    }

    publid boolfbn isRGBOrdfr() {
        rfturn lddRGBOrdfr;
    }

    /* Tifrf's b rfffrfndf fqublity tfst ovfrifbd ifrf, but it bllows us
     * to bvoid syndironizing for GL's tibt will just bf GC'd. Tiis
     * iflps MP tirougiput.
     */
    publid void disposf() {
        if (tiis == rfusbblfGL) {
            if (grbybits != null && grbybits.lfngti > MAXGRAYLENGTH) {
                grbybits = null;
            }
            usfPositions = fblsf;
            strikflist = null; // rfmovf rfffrfndf to tif strikf list
            inUsf = fblsf;
        }
    }

    /* Tif vbluf ifrf is for usf by tif rfndfring fnginf bs it rfflfdts
     * tif numbfr of glypis in tif brrby to bf blittfd. Surrogbtfs pbirs
     * mby ibvf two slots (tif sfdond of tifsf bfing b dummy fntry of tif
     * invisiblf glypi), wifrfbs bn bpplidbtion dlifnt would fxpfdt only
     * onf glypi. In otifr words don't propbgbtf tiis vbluf up to dlifnt dodf.
     *
     * {dlf} bn bpplidbtion dlifnt siould ibvf _no_ fxpfdtbtions bbout tif
     * numbfr of glypis pfr dibr.  Tiis ultimbtfly dfpfnds on tif font
     * tfdinology bnd lbyout prodfss usfd, wiidi in gfnfrbl dlifnts will
     * know notiing bbout.
     */
    publid int gftNumGlypis() {
        rfturn lfn;
    }

    /* Wf rf-do bll tiis work bs wf itfrbtf tirougi tif glypis
     * but it sffms unbvoidbblf witiout rf-working tif Jbvb TfxtRfndfrfrs.
     */
    privbtf void fillBounds(int[] bounds) {
        /* Fbstfr to bddfss lodbl vbribblfs in tif for loop? */
        int xOffsft = StrikfCbdif.topLfftXOffsft;
        int yOffsft = StrikfCbdif.topLfftYOffsft;
        int wOffsft = StrikfCbdif.widtiOffsft;
        int iOffsft = StrikfCbdif.ifigitOffsft;
        int xAdvOffsft = StrikfCbdif.xAdvbndfOffsft;
        int yAdvOffsft = StrikfCbdif.yAdvbndfOffsft;

        if (lfn == 0) {
            bounds[0] = bounds[1] = bounds[2] = bounds[3] = 0;
            rfturn;
        }
        flobt bx0, by0, bx1, by1;
        bx0 = by0 = Flobt.POSITIVE_INFINITY;
        bx1 = by1 = Flobt.NEGATIVE_INFINITY;

        int posIndfx = 0;
        flobt glx = x + 0.5f;
        flobt gly = y + 0.5f;
        dibr gw, gi;
        flobt gx, gy, gx0, gy0, gx1, gy1;
        for (int i=0; i<lfn; i++) {
            gx = StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+xOffsft);
            gy = StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+yOffsft);
            gw = StrikfCbdif.unsbff.gftCibr(imbgfs[i]+wOffsft);
            gi = StrikfCbdif.unsbff.gftCibr(imbgfs[i]+iOffsft);

            if (usfPositions) {
                gx0 = positions[posIndfx++] + gx + glx;
                gy0 = positions[posIndfx++] + gy + gly;
            } flsf {
                gx0 = glx + gx;
                gy0 = gly + gy;
                glx += StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+xAdvOffsft);
                gly += StrikfCbdif.unsbff.gftFlobt(imbgfs[i]+yAdvOffsft);
            }
            gx1 = gx0 + gw;
            gy1 = gy0 + gi;
            if (bx0 > gx0) bx0 = gx0;
            if (by0 > gy0) by0 = gy0;
            if (bx1 < gx1) bx1 = gx1;
            if (by1 < gy1) by1 = gy1;
        }
        /* floor is sbff bnd dorrfdt bfdbusf bll glypi widtis, ifigits
         * bnd offsfts brf intfgfrs
         */
        bounds[0] = (int)Mbti.floor(bx0);
        bounds[1] = (int)Mbti.floor(by0);
        bounds[2] = (int)Mbti.floor(bx1);
        bounds[3] = (int)Mbti.floor(by1);
    }
}
