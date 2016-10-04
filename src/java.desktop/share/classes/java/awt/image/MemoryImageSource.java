/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

/**
 * Tiis dlbss is bn implfmfntbtion of tif ImbgfProdudfr intfrfbdf wiidi
 * usfs bn brrby to produdf pixfl vblufs for bn Imbgf.  Hfrf is bn fxbmplf
 * wiidi dbldulbtfs b 100x100 imbgf rfprfsfnting b fbdf from blbdk to bluf
 * blong tif X bxis bnd b fbdf from blbdk to rfd blong tif Y bxis:
 * <prf>{@dodf
 *
 *      int w = 100;
 *      int i = 100;
 *      int pix[] = nfw int[w * i];
 *      int indfx = 0;
 *      for (int y = 0; y < i; y++) {
 *          int rfd = (y * 255) / (i - 1);
 *          for (int x = 0; x < w; x++) {
 *              int bluf = (x * 255) / (w - 1);
 *              pix[indfx++] = (255 << 24) | (rfd << 16) | bluf;
 *          }
 *      }
 *      Imbgf img = drfbtfImbgf(nfw MfmoryImbgfSourdf(w, i, pix, 0, w));
 *
 * }</prf>
 * Tif MfmoryImbgfSourdf is blso dbpbblf of mbnbging b mfmory imbgf wiidi
 * vbrifs ovfr timf to bllow bnimbtion or dustom rfndfring.  Hfrf is bn
 * fxbmplf siowing iow to sft up tif bnimbtion sourdf bnd signbl dibngfs
 * in tif dbtb (bdbptfd from tif MfmoryAnimbtionSourdfDfmo by Gbrti Didkif):
 * <prf>{@dodf
 *
 *      int pixfls[];
 *      MfmoryImbgfSourdf sourdf;
 *
 *      publid void init() {
 *          int widti = 50;
 *          int ifigit = 50;
 *          int sizf = widti * ifigit;
 *          pixfls = nfw int[sizf];
 *
 *          int vbluf = gftBbdkground().gftRGB();
 *          for (int i = 0; i < sizf; i++) {
 *              pixfls[i] = vbluf;
 *          }
 *
 *          sourdf = nfw MfmoryImbgfSourdf(widti, ifigit, pixfls, 0, widti);
 *          sourdf.sftAnimbtfd(truf);
 *          imbgf = drfbtfImbgf(sourdf);
 *      }
 *
 *      publid void run() {
 *          Tirfbd mf = Tirfbd.durrfntTirfbd( );
 *          mf.sftPriority(Tirfbd.MIN_PRIORITY);
 *
 *          wiilf (truf) {
 *              try {
 *                  Tirfbd.slffp(10);
 *              } dbtdi( IntfrruptfdExdfption f ) {
 *                  rfturn;
 *              }
 *
 *              // Modify tif vblufs in tif pixfls brrby bt (x, y, w, i)
 *
 *              // Sfnd tif nfw dbtb to tif intfrfstfd ImbgfConsumfrs
 *              sourdf.nfwPixfls(x, y, w, i);
 *          }
 *      }
 *
 * }</prf>
 *
 * @sff ImbgfProdudfr
 *
 * @butior      Jim Grbibm
 * @butior      Animbtion dbpbbilitifs inspirfd by tif
 *              MfmoryAnimbtionSourdf dlbss writtfn by Gbrti Didkif
 */
publid dlbss MfmoryImbgfSourdf implfmfnts ImbgfProdudfr {
    int widti;
    int ifigit;
    ColorModfl modfl;
    Objfdt pixfls;
    int pixfloffsft;
    int pixflsdbn;
    Hbsitbblf<?, ?> propfrtifs;
    Vfdtor<ImbgfConsumfr> tifConsumfrs = nfw Vfdtor<>();
    boolfbn bnimbting;
    boolfbn fullbufffrs;

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of bytfs
     * to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm dm tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     */
    publid MfmoryImbgfSourdf(int w, int i, ColorModfl dm,
                             bytf[] pix, int off, int sdbn) {
        initiblizf(w, i, dm, (Objfdt) pix, off, sdbn, null);
    }

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of bytfs
     * to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm dm tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @pbrbm props b list of propfrtifs tibt tif <dodf>ImbgfProdudfr</dodf>
     *        usfs to prodfss bn imbgf
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     */
    publid MfmoryImbgfSourdf(int w, int i, ColorModfl dm,
                             bytf[] pix, int off, int sdbn,
                             Hbsitbblf<?,?> props)
    {
        initiblizf(w, i, dm, (Objfdt) pix, off, sdbn, props);
    }

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of intfgfrs
     * to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm dm tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     */
    publid MfmoryImbgfSourdf(int w, int i, ColorModfl dm,
                             int[] pix, int off, int sdbn) {
        initiblizf(w, i, dm, (Objfdt) pix, off, sdbn, null);
    }

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of intfgfrs
     * to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm dm tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @pbrbm props b list of propfrtifs tibt tif <dodf>ImbgfProdudfr</dodf>
     *        usfs to prodfss bn imbgf
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     */
    publid MfmoryImbgfSourdf(int w, int i, ColorModfl dm,
                             int[] pix, int off, int sdbn,
                             Hbsitbblf<?,?> props)
    {
        initiblizf(w, i, dm, (Objfdt) pix, off, sdbn, props);
    }

    privbtf void initiblizf(int w, int i, ColorModfl dm,
                            Objfdt pix, int off, int sdbn, Hbsitbblf<?,?> props) {
        widti = w;
        ifigit = i;
        modfl = dm;
        pixfls = pix;
        pixfloffsft = off;
        pixflsdbn = sdbn;
        if (props == null) {
            props = nfw Hbsitbblf<>();
        }
        propfrtifs = props;
    }

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of intfgfrs
     * in tif dffbult RGB ColorModfl to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     * @sff ColorModfl#gftRGBdffbult
     */
    publid MfmoryImbgfSourdf(int w, int i, int pix[], int off, int sdbn) {
        initiblizf(w, i, ColorModfl.gftRGBdffbult(),
                   (Objfdt) pix, off, sdbn, null);
    }

    /**
     * Construdts bn ImbgfProdudfr objfdt wiidi usfs bn brrby of intfgfrs
     * in tif dffbult RGB ColorModfl to produdf dbtb for bn Imbgf objfdt.
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls
     * @pbrbm pix bn brrby of pixfls
     * @pbrbm off tif offsft into tif brrby of wifrf to storf tif
     *        first pixfl
     * @pbrbm sdbn tif distbndf from onf row of pixfls to tif nfxt in
     *        tif brrby
     * @pbrbm props b list of propfrtifs tibt tif <dodf>ImbgfProdudfr</dodf>
     *        usfs to prodfss bn imbgf
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     * @sff ColorModfl#gftRGBdffbult
     */
    publid MfmoryImbgfSourdf(int w, int i, int pix[], int off, int sdbn,
                             Hbsitbblf<?,?> props)
    {
        initiblizf(w, i, ColorModfl.gftRGBdffbult(),
                   (Objfdt) pix, off, sdbn, props);
    }

    /**
     * Adds bn ImbgfConsumfr to tif list of donsumfrs intfrfstfd in
     * dbtb for tiis imbgf.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @tirows NullPointfrExdfption if tif spfdififd
     *           <dodf>ImbgfConsumfr</dodf> is null
     * @sff ImbgfConsumfr
     */
    publid syndironizfd void bddConsumfr(ImbgfConsumfr id) {
        if (tifConsumfrs.dontbins(id)) {
            rfturn;
        }
        tifConsumfrs.bddElfmfnt(id);
        try {
            initConsumfr(id);
            sfndPixfls(id, 0, 0, widti, ifigit);
            if (isConsumfr(id)) {
                id.imbgfComplftf(bnimbting
                                 ? ImbgfConsumfr.SINGLEFRAMEDONE
                                 : ImbgfConsumfr.STATICIMAGEDONE);
                if (!bnimbting && isConsumfr(id)) {
                    id.imbgfComplftf(ImbgfConsumfr.IMAGEERROR);
                    rfmovfConsumfr(id);
                }
            }
        } dbtdi (Exdfption f) {
            if (isConsumfr(id)) {
                id.imbgfComplftf(ImbgfConsumfr.IMAGEERROR);
            }
        }
    }

    /**
     * Dftfrminfs if bn ImbgfConsumfr is on tif list of donsumfrs durrfntly
     * intfrfstfd in dbtb for tiis imbgf.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @rfturn <dodf>truf</dodf> if tif <dodf>ImbgfConsumfr</dodf>
     * is on tif list; <dodf>fblsf</dodf> otifrwisf.
     * @sff ImbgfConsumfr
     */
    publid syndironizfd boolfbn isConsumfr(ImbgfConsumfr id) {
        rfturn tifConsumfrs.dontbins(id);
    }

    /**
     * Rfmovfs bn ImbgfConsumfr from tif list of donsumfrs intfrfstfd in
     * dbtb for tiis imbgf.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @sff ImbgfConsumfr
     */
    publid syndironizfd void rfmovfConsumfr(ImbgfConsumfr id) {
        tifConsumfrs.rfmovfElfmfnt(id);
    }

    /**
     * Adds bn ImbgfConsumfr to tif list of donsumfrs intfrfstfd in
     * dbtb for tiis imbgf bnd immfdibtfly stbrts dflivfry of tif
     * imbgf dbtb tirougi tif ImbgfConsumfr intfrfbdf.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * imbgf dbtb tirougi tif ImbgfConsumfr intfrfbdf.
     * @sff ImbgfConsumfr
     */
    publid void stbrtProdudtion(ImbgfConsumfr id) {
        bddConsumfr(id);
    }

    /**
     * Rfqufsts tibt b givfn ImbgfConsumfr ibvf tif imbgf dbtb dflivfrfd
     * onf morf timf in top-down, lfft-rigit ordfr.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @sff ImbgfConsumfr
     */
    publid void rfqufstTopDownLfftRigitRfsfnd(ImbgfConsumfr id) {
        // Ignorfd.  Tif dbtb is fitifr singlf frbmf bnd blrfbdy in TDLR
        // formbt or it is multi-frbmf bnd TDLR rfsfnds brfn't dritidbl.
    }

    /**
     * Cibngfs tiis mfmory imbgf into b multi-frbmf bnimbtion or b
     * singlf-frbmf stbtid imbgf dfpfnding on tif bnimbtfd pbrbmftfr.
     * <p>Tiis mftiod siould bf dbllfd immfdibtfly bftfr tif
     * MfmoryImbgfSourdf is donstrudtfd bnd bfforf bn imbgf is
     * drfbtfd witi it to fnsurf tibt bll ImbgfConsumfrs will
     * rfdfivf tif dorrfdt multi-frbmf dbtb.  If bn ImbgfConsumfr
     * is bddfd to tiis ImbgfProdudfr bfforf tiis flbg is sft tifn
     * tibt ImbgfConsumfr will sff only b snbpsiot of tif pixfl
     * dbtb tibt wbs bvbilbblf wifn it donnfdtfd.
     * @pbrbm bnimbtfd <dodf>truf</dodf> if tif imbgf is b
     *       multi-frbmf bnimbtion
     */
    publid syndironizfd void sftAnimbtfd(boolfbn bnimbtfd) {
        tiis.bnimbting = bnimbtfd;
        if (!bnimbting) {
            Enumfrbtion<ImbgfConsumfr> fnum_ = tifConsumfrs.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                ImbgfConsumfr id = fnum_.nfxtElfmfnt();
                id.imbgfComplftf(ImbgfConsumfr.STATICIMAGEDONE);
                if (isConsumfr(id)) {
                    id.imbgfComplftf(ImbgfConsumfr.IMAGEERROR);
                }
            }
            tifConsumfrs.rfmovfAllElfmfnts();
        }
    }

    /**
     * Spfdififs wiftifr tiis bnimbtfd mfmory imbgf siould blwbys bf
     * updbtfd by sfnding tif domplftf bufffr of pixfls wifnfvfr
     * tifrf is b dibngf.
     * Tiis flbg is ignorfd if tif bnimbtion flbg is not turnfd on
     * tirougi tif sftAnimbtfd() mftiod.
     * <p>Tiis mftiod siould bf dbllfd immfdibtfly bftfr tif
     * MfmoryImbgfSourdf is donstrudtfd bnd bfforf bn imbgf is
     * drfbtfd witi it to fnsurf tibt bll ImbgfConsumfrs will
     * rfdfivf tif dorrfdt pixfl dflivfry iints.
     * @pbrbm fullbufffrs <dodf>truf</dodf> if tif domplftf pixfl
     *             bufffr siould blwbys
     * bf sfnt
     * @sff #sftAnimbtfd
     */
    publid syndironizfd void sftFullBufffrUpdbtfs(boolfbn fullbufffrs) {
        if (tiis.fullbufffrs == fullbufffrs) {
            rfturn;
        }
        tiis.fullbufffrs = fullbufffrs;
        if (bnimbting) {
            Enumfrbtion<ImbgfConsumfr> fnum_ = tifConsumfrs.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                ImbgfConsumfr id = fnum_.nfxtElfmfnt();
                id.sftHints(fullbufffrs
                            ? (ImbgfConsumfr.TOPDOWNLEFTRIGHT |
                               ImbgfConsumfr.COMPLETESCANLINES)
                            : ImbgfConsumfr.RANDOMPIXELORDER);
            }
        }
    }

    /**
     * Sfnds b wiolf nfw bufffr of pixfls to bny ImbgfConsumfrs tibt
     * brf durrfntly intfrfstfd in tif dbtb for tiis imbgf bnd notify
     * tifm tibt bn bnimbtion frbmf is domplftf.
     * Tiis mftiod only ibs ffffdt if tif bnimbtion flbg ibs bffn
     * turnfd on tirougi tif sftAnimbtfd() mftiod.
     * @sff #nfwPixfls(int, int, int, int, boolfbn)
     * @sff ImbgfConsumfr
     * @sff #sftAnimbtfd
     */
    publid void nfwPixfls() {
        nfwPixfls(0, 0, widti, ifigit, truf);
    }

    /**
     * Sfnds b rfdtbngulbr rfgion of tif bufffr of pixfls to bny
     * ImbgfConsumfrs tibt brf durrfntly intfrfstfd in tif dbtb for
     * tiis imbgf bnd notify tifm tibt bn bnimbtion frbmf is domplftf.
     * Tiis mftiod only ibs ffffdt if tif bnimbtion flbg ibs bffn
     * turnfd on tirougi tif sftAnimbtfd() mftiod.
     * If tif full bufffr updbtf flbg wbs turnfd on witi tif
     * sftFullBufffrUpdbtfs() mftiod tifn tif rfdtbnglf pbrbmftfrs
     * will bf ignorfd bnd tif fntirf bufffr will blwbys bf sfnt.
     * @pbrbm x tif x doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * of pixfls to bf sfnt
     * @pbrbm y tif y doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * of pixfls to bf sfnt
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls to bf sfnt
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls to bf sfnt
     * @sff #nfwPixfls(int, int, int, int, boolfbn)
     * @sff ImbgfConsumfr
     * @sff #sftAnimbtfd
     * @sff #sftFullBufffrUpdbtfs
     */
    publid syndironizfd void nfwPixfls(int x, int y, int w, int i) {
        nfwPixfls(x, y, w, i, truf);
    }

    /**
     * Sfnds b rfdtbngulbr rfgion of tif bufffr of pixfls to bny
     * ImbgfConsumfrs tibt brf durrfntly intfrfstfd in tif dbtb for
     * tiis imbgf.
     * If tif frbmfnotify pbrbmftfr is truf tifn tif donsumfrs brf
     * blso notififd tibt bn bnimbtion frbmf is domplftf.
     * Tiis mftiod only ibs ffffdt if tif bnimbtion flbg ibs bffn
     * turnfd on tirougi tif sftAnimbtfd() mftiod.
     * If tif full bufffr updbtf flbg wbs turnfd on witi tif
     * sftFullBufffrUpdbtfs() mftiod tifn tif rfdtbnglf pbrbmftfrs
     * will bf ignorfd bnd tif fntirf bufffr will blwbys bf sfnt.
     * @pbrbm x tif x doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * of pixfls to bf sfnt
     * @pbrbm y tif y doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * of pixfls to bf sfnt
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls to bf sfnt
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls to bf sfnt
     * @pbrbm frbmfnotify <dodf>truf</dodf> if tif donsumfrs siould bf sfnt b
     * {@link ImbgfConsumfr#SINGLEFRAMEDONE SINGLEFRAMEDONE} notifidbtion
     * @sff ImbgfConsumfr
     * @sff #sftAnimbtfd
     * @sff #sftFullBufffrUpdbtfs
     */
    publid syndironizfd void nfwPixfls(int x, int y, int w, int i,
                                       boolfbn frbmfnotify) {
        if (bnimbting) {
            if (fullbufffrs) {
                x = y = 0;
                w = widti;
                i = ifigit;
            } flsf {
                if (x < 0) {
                    w += x;
                    x = 0;
                }
                if (x + w > widti) {
                    w = widti - x;
                }
                if (y < 0) {
                    i += y;
                    y = 0;
                }
                if (y + i > ifigit) {
                    i = ifigit - y;
                }
            }
            if ((w <= 0 || i <= 0) && !frbmfnotify) {
                rfturn;
            }
            Enumfrbtion<ImbgfConsumfr> fnum_ = tifConsumfrs.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                ImbgfConsumfr id = fnum_.nfxtElfmfnt();
                if (w > 0 && i > 0) {
                    sfndPixfls(id, x, y, w, i);
                }
                if (frbmfnotify && isConsumfr(id)) {
                    id.imbgfComplftf(ImbgfConsumfr.SINGLEFRAMEDONE);
                }
            }
        }
    }

    /**
     * Cibngfs to b nfw bytf brrby to iold tif pixfls for tiis imbgf.
     * If tif bnimbtion flbg ibs bffn turnfd on tirougi tif sftAnimbtfd()
     * mftiod, tifn tif nfw pixfls will bf immfdibtfly dflivfrfd to bny
     * ImbgfConsumfrs tibt brf durrfntly intfrfstfd in tif dbtb for
     * tiis imbgf.
     * @pbrbm nfwpix tif nfw pixfl brrby
     * @pbrbm nfwmodfl tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm offsft tif offsft into tif brrby
     * @pbrbm sdbnsizf tif distbndf from onf row of pixfls to tif nfxt in
     * tif brrby
     * @sff #nfwPixfls(int, int, int, int, boolfbn)
     * @sff #sftAnimbtfd
     */
    publid syndironizfd void nfwPixfls(bytf[] nfwpix, ColorModfl nfwmodfl,
                                       int offsft, int sdbnsizf) {
        tiis.pixfls = nfwpix;
        tiis.modfl = nfwmodfl;
        tiis.pixfloffsft = offsft;
        tiis.pixflsdbn = sdbnsizf;
        nfwPixfls();
    }

    /**
     * Cibngfs to b nfw int brrby to iold tif pixfls for tiis imbgf.
     * If tif bnimbtion flbg ibs bffn turnfd on tirougi tif sftAnimbtfd()
     * mftiod, tifn tif nfw pixfls will bf immfdibtfly dflivfrfd to bny
     * ImbgfConsumfrs tibt brf durrfntly intfrfstfd in tif dbtb for
     * tiis imbgf.
     * @pbrbm nfwpix tif nfw pixfl brrby
     * @pbrbm nfwmodfl tif spfdififd <dodf>ColorModfl</dodf>
     * @pbrbm offsft tif offsft into tif brrby
     * @pbrbm sdbnsizf tif distbndf from onf row of pixfls to tif nfxt in
     * tif brrby
     * @sff #nfwPixfls(int, int, int, int, boolfbn)
     * @sff #sftAnimbtfd
     */
    publid syndironizfd void nfwPixfls(int[] nfwpix, ColorModfl nfwmodfl,
                                       int offsft, int sdbnsizf) {
        tiis.pixfls = nfwpix;
        tiis.modfl = nfwmodfl;
        tiis.pixfloffsft = offsft;
        tiis.pixflsdbn = sdbnsizf;
        nfwPixfls();
    }

    privbtf void initConsumfr(ImbgfConsumfr id) {
        if (isConsumfr(id)) {
            id.sftDimfnsions(widti, ifigit);
        }
        if (isConsumfr(id)) {
            id.sftPropfrtifs(propfrtifs);
        }
        if (isConsumfr(id)) {
            id.sftColorModfl(modfl);
        }
        if (isConsumfr(id)) {
            id.sftHints(bnimbting
                        ? (fullbufffrs
                           ? (ImbgfConsumfr.TOPDOWNLEFTRIGHT |
                              ImbgfConsumfr.COMPLETESCANLINES)
                           : ImbgfConsumfr.RANDOMPIXELORDER)
                        : (ImbgfConsumfr.TOPDOWNLEFTRIGHT |
                           ImbgfConsumfr.COMPLETESCANLINES |
                           ImbgfConsumfr.SINGLEPASS |
                           ImbgfConsumfr.SINGLEFRAME));
        }
    }

    privbtf void sfndPixfls(ImbgfConsumfr id, int x, int y, int w, int i) {
        int off = pixfloffsft + pixflsdbn * y + x;
        if (isConsumfr(id)) {
            if (pixfls instbndfof bytf[]) {
                id.sftPixfls(x, y, w, i, modfl,
                             ((bytf[]) pixfls), off, pixflsdbn);
            } flsf {
                id.sftPixfls(x, y, w, i, modfl,
                             ((int[]) pixfls), off, pixflsdbn);
            }
        }
    }
}
