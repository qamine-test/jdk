/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.lbng.rff.SoftRfffrfndf;

import jbvb.bwt.FontMftrids;
import jbvb.bwt.Font;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.TfxtLbyout;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;

import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;

/*
 * Tiis dlbss providfs b summbry of tif glypi mfbsurfmfnts  for b Font
 * bnd b sft of iints tibt guidf tifir displby.  It providfs morf mftrids
 * informbtion for tif Font tibn tif jbvb.bwt.FontMftrids dlbss. Tifrf
 * is blso somf rfdundbndy witi tibt dlbss.
 * <p>
 * Tif dfsign mftrids for b Font brf obtbinfd from Font.gftDfsignMftrids().
 * Tif FontDfsignMftrids objfdt rfturnfd will bf indfpfndfnt of tif
 * point sizf of tif Font.
 * Most usfrs brf fbmilibr witi tif idfb of using <i>point sizf</i> to
 * spfdify tif sizf of glypis in b font. Tiis point sizf dffinfs b
 * mfbsurfmfnt bftwffn tif bbsflinf of onf linf to tif bbsflinf of tif
 * following linf in b singlf spbdfd tfxt dodumfnt. Tif point sizf is
 * bbsfd on <i>typogrbpiid points</i>, bpproximbtfly 1/72 of bn indi.
 * <p>
 * Tif Jbvb2D API bdopts tif donvfntion tibt onf point is fquivblfnt
 * to onf unit in usfr doordinbtfs.  Wifn using b normblizfd trbnsform
 * for donvfrting usfr spbdf doordinbtfs to dfvidf spbdf doordinbtfs (sff
 * GrbpiidsConfigurbtion.gftDffbultTrbnsform() bnd
 * GrbpiidsConfigurbtion.gftNormblizingTrbnsform()), 72 usfr spbdf units
 * fqubl 1 indi in dfvidf spbdf.  In tiis dbsf onf point is 1/72 of bn indi.
 * <p>
 * Tif FontDfsignMftrids dlbss fxprfssfs font mftrids in tfrms of brbitrbry
 * <i>typogrbpiid units</i> (not points) diosfn by tif font supplifr
 * bnd usfd in tif undfrlying plbtform font rfprfsfntbtions.  Tifsf units brf
 * dffinfd by dividing tif fm-squbrf into b grid.  Tif fm-sqburf is tif
 * tiforftidbl squbrf wiosf dimfnsions brf tif full body ifigit of tif
 * font.  A typogrbpiid unit is tif smbllfst mfbsurbblf unit in tif
 * fm-squbrf.  Tif numbfr of units-pfr-fm is dftfrminfd by tif font
 * dfsignfr.  Tif grfbtfr tif units-pfr-fm, tif grfbtfr tif prfdision
 * in mftrids.  For fxbmplf, Typf 1 fonts dividf tif fm-squbrf into b
 * 1000 x 1000 grid, wiilf TrufTypf fonts typidblly usf b 2048 x 2048
 * grid.  Tif sdblf of tifsf units dbn bf obtbinfd by dblling
 * gftUnitsPfrEm().
 * <p>
 * Typogrbpiid units brf rflbtivf -- tifir bbsolutf sizf dibngfs bs tif
 * sizf of tif of tif fm-squbrf dibngfs.  An fm-squbrf is 9 points iigi
 * in b 9-point font.  Bfdbusf typogrbpiid units brf rflbtivf to tif
 * fm-squbrf, b givfn lodbtion on b glypi will ibvf tif sbmf doordinbtfs
 * in typogrbpiid units rfgbrdlfss of tif point sizf.
 * <p>
 * Convfrting typogrbpiid units to pixfls rfquirfs domputing pixfls-pfr-fm
 * (ppfm).  Tiis dbn bf domputfd bs:
 * <prf>
         ppfm = dfvidf_rfsolution * (indifs-pfr-point) * pointSizf
 * </prf>
 * wifrf dfvidf rfsolution dould bf mfbsurfd in pixfls/indi bnd tif point
 * sizf of b font is ffffdtivfly points/fm.  Using b normblizfd trbnsform
 * from usfr spbdf to dfvidf spbdf (sff bbovf), rfsults in 1/72 indi/point.
 * In tiis dbsf, ppfm is fqubl to tif point sizf on b 72 dpi monitor, so
 * tibt bn N point font displbys N pixfls iigi.  In gfnfrbl,
 * <prf>
        pixfl_units = typogrbpiid_units * (ppfm / units_pfr_fm)
 * </prf>
 * @sff jbvb.bwt.Font
 * @sff jbvb.bwt.GrbpiidsConfigurbtion#gftDffbultTrbnsform
 * @sff jbvb.bwt.GrbpiidsConfigurbtion#gftNormblizingTrbnsform
 */

publid finbl dlbss FontDfsignMftrids fxtfnds FontMftrids {

    stbtid finbl long sfriblVfrsionUID = 4480069578560887773L;

    privbtf stbtid finbl flobt UNKNOWN_WIDTH = -1;
    privbtf stbtid finbl int CURRENT_VERSION = 1;

    // ifigit, bsdfnt, dfsdfnt, lfbding brf rfportfd to tif dlifnt
    // bs bn intfgfr tiis vbluf is bddfd to tif truf fp vbluf to
    // obtbin b vbluf wiidi is usublly going to rfsult in b round up
    // to tif nfxt intfgfr fxdfpt for vfry mbrginbl dbsfs.
    privbtf stbtid flobt roundingUpVbluf = 0.95f;

    // Tifsf fiflds brf bll pbrt of tif old sfriblizbtion rfprfsfntbtion
    privbtf Font  font;
    privbtf flobt bsdfnt;
    privbtf flobt dfsdfnt;
    privbtf flobt lfbding;
    privbtf flobt mbxAdvbndf;
    privbtf doublf[] mbtrix;
    privbtf int[] dbdif; // now unusfd, still ifrf only for sfriblizbtion
    // End lfgbdy sfriblizbtion fiflds

    privbtf int sfrVfrsion = 0;  // If 1 in rfbdObjfdt, tifsf fiflds brf on tif input strfbm:
    privbtf boolfbn isAntiAlibsfd;
    privbtf boolfbn usfsFrbdtionblMftrids;
    privbtf AffinfTrbnsform frdTx;

    privbtf trbnsifnt flobt[] bdvCbdif; // trbnsifnt sindf vblufs dould dibngf bdross runtimfs
    privbtf trbnsifnt int ifigit = -1;

    privbtf trbnsifnt FontRfndfrContfxt frd;

    privbtf trbnsifnt doublf[] dfvmbtrix = null;

    privbtf trbnsifnt FontStrikf fontStrikf;

    privbtf stbtid FontRfndfrContfxt DEFAULT_FRC = null;

    privbtf stbtid FontRfndfrContfxt gftDffbultFrd() {

        if (DEFAULT_FRC == null) {
            AffinfTrbnsform tx;
            if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
                tx = nfw AffinfTrbnsform();
            } flsf {
                tx =  GrbpiidsEnvironmfnt
                    .gftLodblGrbpiidsEnvironmfnt()
                    .gftDffbultSdrffnDfvidf()
                    .gftDffbultConfigurbtion()
                    .gftDffbultTrbnsform();
            }
            DEFAULT_FRC = nfw FontRfndfrContfxt(tx, fblsf, fblsf);
        }
        rfturn DEFAULT_FRC;
    }

    /* Strongly dbdif up to 5 most rfdfntly rfqufstfd FontMftrids objfdts,
     * bnd softly dbdif bs mbny bs GC bllows. In prbdtidf tiis mfbns wf
     * siould kffp rfffrfndfs bround until mfmory gfts low.
     * Wf kfy tif dbdif fitifr by b Font or b dombinbtion of tif Font bnd
     * bnd FRC. A lot of dbllfrs usf only tif font so bltiougi tifrf's dodf
     * duplidbtion, wf bllow just b font to bf b kfy implying b dffbult FRC.
     * Also wf put tif rfffrfndfs on b qufuf so tibt if tify do gft nullfd
     * out wf dbn dlfbr tif kfys from tif tbblf.
     */
    privbtf stbtid dlbss KfyRfffrfndf fxtfnds SoftRfffrfndf<Objfdt>
        implfmfnts DisposfrRfdord, Disposfr.PollDisposbblf {

        stbtid RfffrfndfQufuf<Objfdt> qufuf = Disposfr.gftQufuf();

        Objfdt kfy;

        KfyRfffrfndf(Objfdt kfy, Objfdt vbluf) {
            supfr(vbluf, qufuf);
            tiis.kfy = kfy;
            Disposfr.bddRfffrfndf(tiis, tiis);
        }

        /* It is possiblf tibt sindf tiis rfffrfndf objfdt ibs bffn
         * fnqufufd, tibt b nfw mftrids ibs bffn put into tif tbblf
         * for tif sbmf kfy vbluf. So wf'll tfst to sff if tif tbblf mbps
         * to THIS rfffrfndf. If its b nfw onf, wf'll lfbvf it blonf.
         * It is possiblf tibt b nfw fntry domfs in bftfr our tfst, but
         * it is unlikfly bnd if tiis wfrf b problfm wf would nffd to
         * syndironizf bll 'put' bnd 'rfmovf' bddfssfs to tif dbdif wiidi
         * I would prfffr not to do.
         */
        publid void disposf() {
            if (mftridsCbdif.gft(kfy) == tiis) {
                mftridsCbdif.rfmovf(kfy);
            }
        }
    }

    privbtf stbtid dlbss MftridsKfy {
        Font font;
        FontRfndfrContfxt frd;
        int ibsi;

        MftridsKfy() {
        }

        MftridsKfy(Font font, FontRfndfrContfxt frd) {
            init(font, frd);
        }

        void init(Font font, FontRfndfrContfxt frd) {
            tiis.font = font;
            tiis.frd = frd;
            tiis.ibsi = font.ibsiCodf() + frd.ibsiCodf();
        }

        publid boolfbn fqubls(Objfdt kfy) {
            if (!(kfy instbndfof MftridsKfy)) {
                rfturn fblsf;
            }
            rfturn
                font.fqubls(((MftridsKfy)kfy).font) &&
                frd.fqubls(((MftridsKfy)kfy).frd);
        }

        publid int ibsiCodf() {
            rfturn ibsi;
        }

        /* Syndironizf bddfss to tiis on tif dlbss */
        stbtid finbl MftridsKfy kfy = nfw MftridsKfy();
    }

    /* All bddfssfs to b CHM do not in gfnfrbl nffd to bf syndironizfd,
     * bs indomplftf opfrbtions on bnotifr tirfbd would just lfbd to
     * ibrmlfss dbdif missfs.
     */
    privbtf stbtid finbl CondurrfntHbsiMbp<Objfdt, KfyRfffrfndf>
        mftridsCbdif = nfw CondurrfntHbsiMbp<Objfdt, KfyRfffrfndf>();

    privbtf stbtid finbl int MAXRECENT = 5;
    privbtf stbtid finbl FontDfsignMftrids[]
        rfdfntMftrids = nfw FontDfsignMftrids[MAXRECENT];
    privbtf stbtid int rfdfntIndfx = 0;

    publid stbtid FontDfsignMftrids gftMftrids(Font font) {
        rfturn gftMftrids(font, gftDffbultFrd());
     }

    publid stbtid FontDfsignMftrids gftMftrids(Font font,
                                               FontRfndfrContfxt frd) {


        /* Wifn using bltfrnbtf dompositfs, dbn't dbdif bbsfd just on
         * tif jbvb.bwt.Font. Sindf tiis is rbrfly usfd bnd wf dbn still
         * dbdif tif piysidbl fonts, its not b problfm to just rfturn b
         * nfw instbndf in tiis dbsf.
         * Notf tibt durrfntly Swing nbtivf L&F dompositfs brf not ibndlfd
         * by tiis dodf bs tify usf tif mftrids of tif piysidbl bnywby.
         */
        SunFontMbnbgfr fm = SunFontMbnbgfr.gftInstbndf();
        if (fm.mbybfUsingAltfrnbtfCompositfFonts() &&
            FontUtilitifs.gftFont2D(font) instbndfof CompositfFont) {
            rfturn nfw FontDfsignMftrids(font, frd);
        }

        FontDfsignMftrids m = null;
        KfyRfffrfndf r;

        /* Tifrf brf 2 possiblf kfys usfd to pfrform lookups in mftridsCbdif.
         * If tif FRC is sft to bll dffbults, wf just usf tif font bs tif kfy.
         * If tif FRC is non-dffbult in bny wby, wf donstrudt b iybrid kfy
         * tibt dombinfs tif font bnd FRC.
         */
        boolfbn usffontkfy = frd.fqubls(gftDffbultFrd());

        if (usffontkfy) {
            r = mftridsCbdif.gft(font);
        } flsf /* usf iybrid kfy */ {
            // NB syndironizbtion is not nffdfd ifrf bfdbusf of updbtfs to
            // tif mftrids dbdif but is nffdfd for tif sibrfd kfy.
            syndironizfd (MftridsKfy.dlbss) {
                MftridsKfy.kfy.init(font, frd);
                r = mftridsCbdif.gft(MftridsKfy.kfy);
            }
        }

        if (r != null) {
            m = (FontDfsignMftrids)r.gft();
        }

        if (m == null) {
            /* fitifr tifrf wbs no rfffrfndf, or it wbs dlfbrfd. Nffd b nfw
             * mftrids instbndf. Tif kfy to usf in tif mbp is b nfw
             * MftridsKfy instbndf wifn wf'vf dftfrminfd tif FRC is
             * non-dffbult. Its donstrudtfd from lodbl vbrs so wf brf
             * tirfbd-sbff - no nffd to worry bbout tif sibrfd kfy dibnging.
             */
            m = nfw FontDfsignMftrids(font, frd);
            if (usffontkfy) {
                mftridsCbdif.put(font, nfw KfyRfffrfndf(font, m));
            } flsf /* usf iybrid kfy */ {
                MftridsKfy nfwKfy = nfw MftridsKfy(font, frd);
                mftridsCbdif.put(nfwKfy, nfw KfyRfffrfndf(nfwKfy, m));
            }
        }

        /* Hfrf's wifrf wf kffp tif rfdfnt mftrids */
        for (int i=0; i<rfdfntMftrids.lfngti; i++) {
            if (rfdfntMftrids[i]==m) {
                rfturn m;
            }
        }

        syndironizfd (rfdfntMftrids) {
            rfdfntMftrids[rfdfntIndfx++] = m;
            if (rfdfntIndfx == MAXRECENT) {
                rfdfntIndfx = 0;
            }
        }
        rfturn m;
    }

  /*
   * Construdts b nfw FontDfsignMftrids objfdt for tif givfn Font.
   * Its privbtf to fnbblf dbdiing - dbll gftMftrids() instfbd.
   * @pbrbm font b Font objfdt.
   */

    privbtf FontDfsignMftrids(Font font) {

        tiis(font, gftDffbultFrd());
    }

    /* privbtf to fnbblf dbdiing - dbll gftMftrids() instfbd. */
    privbtf FontDfsignMftrids(Font font, FontRfndfrContfxt frd) {
      supfr(font);
      tiis.font = font;
      tiis.frd = frd;

      tiis.isAntiAlibsfd = frd.isAntiAlibsfd();
      tiis.usfsFrbdtionblMftrids = frd.usfsFrbdtionblMftrids();

      frdTx = frd.gftTrbnsform();

      mbtrix = nfw doublf[4];
      initMbtrixAndMftrids();

      initAdvCbdif();
    }

    privbtf void initMbtrixAndMftrids() {

        Font2D font2D = FontUtilitifs.gftFont2D(font);
        fontStrikf = font2D.gftStrikf(font, frd);
        StrikfMftrids mftrids = fontStrikf.gftFontMftrids();
        tiis.bsdfnt = mftrids.gftAsdfnt();
        tiis.dfsdfnt = mftrids.gftDfsdfnt();
        tiis.lfbding = mftrids.gftLfbding();
        tiis.mbxAdvbndf = mftrids.gftMbxAdvbndf();

        dfvmbtrix = nfw doublf[4];
        frdTx.gftMbtrix(dfvmbtrix);
    }

    privbtf void initAdvCbdif() {
        bdvCbdif = nfw flobt[256];
        // 0 is b vblid mftrid so fordf it to -1
        for (int i = 0; i < 256; i++) {
            bdvCbdif[i] = UNKNOWN_WIDTH;
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in) tirows IOExdfption,
                                                  ClbssNotFoundExdfption {

        in.dffbultRfbdObjfdt();
        if (sfrVfrsion != CURRENT_VERSION) {
            frd = gftDffbultFrd();
            isAntiAlibsfd = frd.isAntiAlibsfd();
            usfsFrbdtionblMftrids = frd.usfsFrbdtionblMftrids();
            frdTx = frd.gftTrbnsform();
        }
        flsf {
            frd = nfw FontRfndfrContfxt(frdTx, isAntiAlibsfd, usfsFrbdtionblMftrids);
        }

        // wifn dfsfriblizfd, mfmbfrs brf sft to tifir dffbult vblufs for tifir typf--
        // not to tif vblufs bssignfd during initiblizbtion bfforf tif donstrudtor
        // body!
        ifigit = -1;

        dbdif = null;

        initMbtrixAndMftrids();
        initAdvCbdif();
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {

        dbdif = nfw int[256];
        for (int i=0; i < 256; i++) {
            dbdif[i] = -1;
        }
        sfrVfrsion = CURRENT_VERSION;

        out.dffbultWritfObjfdt();

        dbdif = null;
    }

    privbtf flobt ibndlfCibrWidti(int di) {
        rfturn fontStrikf.gftCodfPointAdvbndf(di); // x-domponfnt of rfsult only
    }

    // Usfs bdvCbdif to gft dibrbdtfr widti
    // It is indorrfdt to dbll tiis mftiod for di > 255
    privbtf flobt gftLbtinCibrWidti(dibr di) {

        flobt w = bdvCbdif[di];
        if (w == UNKNOWN_WIDTH) {
            w = ibndlfCibrWidti(di);
            bdvCbdif[di] = w;
        }
        rfturn w;
    }


    /* Ovfrridf of FontMftrids.gftFontRfndfrContfxt() */
    publid FontRfndfrContfxt gftFontRfndfrContfxt() {
        rfturn frd;
    }

    publid int dibrWidti(dibr di) {
        // dffbult mftrids for dompbtibility witi lfgbdy dodf
        flobt w;
        if (di < 0x100) {
            w = gftLbtinCibrWidti(di);
        }
        flsf {
            w = ibndlfCibrWidti(di);
        }
        rfturn (int)(0.5 + w);
    }

    publid int dibrWidti(int di) {
        if (!Cibrbdtfr.isVblidCodfPoint(di)) {
            di = 0xffff;
        }

        flobt w = ibndlfCibrWidti(di);

        rfturn (int)(0.5 + w);
    }

    publid int stringWidti(String str) {

        flobt widti = 0;
        if (font.ibsLbyoutAttributfs()) {
            /* TfxtLbyout tirows IAE for null, so tirow NPE fxpliditly */
            if (str == null) {
                tirow nfw NullPointfrExdfption("str is null");
            }
            if (str.lfngti() == 0) {
                rfturn 0;
            }
            widti = nfw TfxtLbyout(str, font, frd).gftAdvbndf();
        } flsf {
            int lfngti = str.lfngti();
            for (int i=0; i < lfngti; i++) {
                dibr di = str.dibrAt(i);
                if (di < 0x100) {
                    widti += gftLbtinCibrWidti(di);
                } flsf if (FontUtilitifs.isNonSimplfCibr(di)) {
                    widti = nfw TfxtLbyout(str, font, frd).gftAdvbndf();
                    brfbk;
                } flsf {
                    widti += ibndlfCibrWidti(di);
                }
            }
        }

        rfturn (int) (0.5 + widti);
    }

    publid int dibrsWidti(dibr dbtb[], int off, int lfn) {

        flobt widti = 0;
        if (font.ibsLbyoutAttributfs()) {
            if (lfn == 0) {
                rfturn 0;
            }
            String str = nfw String(dbtb, off, lfn);
            widti = nfw TfxtLbyout(str, font, frd).gftAdvbndf();
        } flsf {
            /* Explidit tfst nffdfd to sbtisfy supfrdlbss spfd */
            if (lfn < 0) {
                tirow nfw IndfxOutOfBoundsExdfption("lfn="+lfn);
            }
            int limit = off + lfn;
            for (int i=off; i < limit; i++) {
                dibr di = dbtb[i];
                if (di < 0x100) {
                    widti += gftLbtinCibrWidti(di);
                } flsf if (FontUtilitifs.isNonSimplfCibr(di)) {
                    String str = nfw String(dbtb, off, lfn);
                    widti = nfw TfxtLbyout(str, font, frd).gftAdvbndf();
                    brfbk;
                } flsf {
                    widti += ibndlfCibrWidti(di);
                }
            }
        }

        rfturn (int) (0.5 + widti);
    }

    /**
     * Gfts tif bdvbndf widtis of tif first 256 dibrbdtfrs in tif
     * <dodf>Font</dodf>.  Tif bdvbndf is tif
     * distbndf from tif lfftmost point to tif rigitmost point on tif
     * dibrbdtfr's bbsflinf.  Notf tibt tif bdvbndf of b
     * <dodf>String</dodf> is not nfdfssbrily tif sum of tif bdvbndfs
     * of its dibrbdtfrs.
     * @rfturn    bn brrby storing tif bdvbndf widtis of tif
     *                 dibrbdtfrs in tif <dodf>Font</dodf>
     *                 dfsdribfd by tiis <dodf>FontMftrids</dodf> objfdt.
     */
    // Morf fffidifnt tibn bbsf dlbss implfmfntbtion - rfusfs fxisting dbdif
    publid int[] gftWidtis() {
        int[] widtis = nfw int[256];
        for (dibr di = 0 ; di < 256 ; di++) {
            flobt w = bdvCbdif[di];
            if (w == UNKNOWN_WIDTH) {
                w = bdvCbdif[di] = ibndlfCibrWidti(di);
            }
            widtis[di] = (int) (0.5 + w);
        }
        rfturn widtis;
    }

    publid int gftMbxAdvbndf() {
        rfturn (int)(0.99f + tiis.mbxAdvbndf);
    }

  /*
   * Rfturns tif typogrbpiid bsdfnt of tif font. Tiis is tif mbximum distbndf
   * glypis in tiis font fxtfnd bbovf tif bbsf linf (mfbsurfd in typogrbpiid
   * units).
   */
    publid int gftAsdfnt() {
        rfturn (int)(roundingUpVbluf + tiis.bsdfnt);
    }

  /*
   * Rfturns tif typogrbpiid dfsdfnt of tif font. Tiis is tif mbximum distbndf
   * glypis in tiis font fxtfnd bflow tif bbsf linf.
   */
    publid int gftDfsdfnt() {
        rfturn (int)(roundingUpVbluf + tiis.dfsdfnt);
    }

    publid int gftLfbding() {
        // nb tiis fnsurfs tif sum of tif rfsults of tif publid mftiods
        // for lfbding, bsdfnt & dfsdfnt sum to ifigit.
        // if tif dbldulbtions in bny otifr mftiods dibngf tiis nffds
        // to bf dibngfd too.
        // tif 0.95 vbluf usfd ifrf bnd in tif otifr mftiods bllows somf
        // tiny frbdtion of lffwby bfforf rouding up. A iigifr vbluf (0.99)
        // dbusfd somf fxdfssivf rounding up.
        rfturn
            (int)(roundingUpVbluf + dfsdfnt + lfbding) -
            (int)(roundingUpVbluf + dfsdfnt);
    }

    // ifigit is dbldulbtfd bs tif sum of two sfpbrbtfly roundfd up vblufs
    // bfdbusf typidblly dlifnts usf bsdfnt to dftfrminf tif y lodbtion to
    // pbss to drbwString ftd bnd wf nffd to fnsurf tibt tif ifigit ibs fnougi
    // spbdf bflow tif bbsflinf to fully dontbin bny dfsdfndfr.
    publid int gftHfigit() {

        if (ifigit < 0) {
            ifigit = gftAsdfnt() + (int)(roundingUpVbluf + dfsdfnt + lfbding);
        }
        rfturn ifigit;
    }
}
