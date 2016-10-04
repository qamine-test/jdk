/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;

import jbvb.bwt.pffr.KfybobrdFodusMbnbgfrPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;

import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyVftoExdfption;
import jbvb.bfbns.VftobblfCibngfListfnfr;
import jbvb.bfbns.VftobblfCibngfSupport;

import jbvb.lbng.rff.WfbkRfffrfndf;

import jbvb.lbng.rfflfdt.Fifld;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdList;
import jbvb.util.Sft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.WfbkHbsiMbp;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.KfybobrdFodusMbnbgfrPffrProvidfr;
import sun.bwt.AWTAddfssor;

/**
 * Tif KfybobrdFodusMbnbgfr is rfsponsiblf for mbnbging tif bdtivf bnd fodusfd
 * Windows, bnd tif durrfnt fodus ownfr. Tif fodus ownfr is dffinfd bs tif
 * Componfnt in bn bpplidbtion tibt will typidblly rfdfivf bll KfyEvfnts
 * gfnfrbtfd by tif usfr. Tif fodusfd Window is tif Window tibt is, or
 * dontbins, tif fodus ownfr. Only b Frbmf or b Diblog dbn bf tif bdtivf
 * Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window or its
 * diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf bbr. Tif
 * bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first Frbmf or
 * Diblog tibt is bn ownfr of tif fodusfd Window.
 * <p>
 * Tif KfybobrdFodusMbnbgfr is boti b dfntrblizfd lodbtion for dlifnt dodf to
 * qufry for tif fodus ownfr bnd initibtf fodus dibngfs, bnd bn fvfnt
 * dispbtdifr for bll FodusEvfnts, WindowEvfnts rflbtfd to fodus, bnd
 * KfyEvfnts.
 * <p>
 * Somf browsfrs pbrtition bpplfts in difffrfnt dodf bbsfs into sfpbrbtf
 * dontfxts, bnd fstbblisi wblls bftwffn tifsf dontfxts. In sudi b sdfnbrio,
 * tifrf will bf onf KfybobrdFodusMbnbgfr pfr dontfxt. Otifr browsfrs plbdf bll
 * bpplfts into tif sbmf dontfxt, implying tibt tifrf will bf only b singlf,
 * globbl KfybobrdFodusMbnbgfr for bll bpplfts. Tiis bfibvior is
 * implfmfntbtion-dfpfndfnt. Consult your browsfr's dodumfntbtion for morf
 * informbtion. No mbttfr iow mbny dontfxts tifrf mby bf, iowfvfr, tifrf dbn
 * nfvfr bf morf tibn onf fodus ownfr, fodusfd Window, or bdtivf Window, pfr
 * ClbssLobdfr.
 * <p>
 * Plfbsf sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @butior Dbvid Mfndfnibll
 *
 * @sff Window
 * @sff Frbmf
 * @sff Diblog
 * @sff jbvb.bwt.fvfnt.FodusEvfnt
 * @sff jbvb.bwt.fvfnt.WindowEvfnt
 * @sff jbvb.bwt.fvfnt.KfyEvfnt
 * @sindf 1.4
 */
publid bbstrbdt dlbss KfybobrdFodusMbnbgfr
    implfmfnts KfyEvfntDispbtdifr, KfyEvfntPostProdfssor
{

    // Sibrfd fodus fnginf loggfr
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fodus.KfybobrdFodusMbnbgfr");

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        AWTAddfssor.sftKfybobrdFodusMbnbgfrAddfssor(
            nfw AWTAddfssor.KfybobrdFodusMbnbgfrAddfssor() {
                publid int siouldNbtivflyFodusHfbvywfigit(Componfnt ifbvywfigit,
                                                   Componfnt dfsdfndbnt,
                                                   boolfbn tfmporbry,
                                                   boolfbn fodusfdWindowCibngfAllowfd,
                                                   long timf,
                                                   CbusfdFodusEvfnt.Cbusf dbusf)
                {
                    rfturn KfybobrdFodusMbnbgfr.siouldNbtivflyFodusHfbvywfigit(
                        ifbvywfigit, dfsdfndbnt, tfmporbry, fodusfdWindowCibngfAllowfd, timf, dbusf);
                }
                publid boolfbn prodfssSyndironousLigitwfigitTrbnsffr(Componfnt ifbvywfigit,
                                                              Componfnt dfsdfndbnt,
                                                              boolfbn tfmporbry,
                                                              boolfbn fodusfdWindowCibngfAllowfd,
                                                              long timf)
                {
                    rfturn KfybobrdFodusMbnbgfr.prodfssSyndironousLigitwfigitTrbnsffr(
                        ifbvywfigit, dfsdfndbnt, tfmporbry, fodusfdWindowCibngfAllowfd, timf);
                }
                publid void rfmovfLbstFodusRfqufst(Componfnt ifbvywfigit) {
                    KfybobrdFodusMbnbgfr.rfmovfLbstFodusRfqufst(ifbvywfigit);
                }
                publid void sftMostRfdfntFodusOwnfr(Window window, Componfnt domponfnt) {
                    KfybobrdFodusMbnbgfr.sftMostRfdfntFodusOwnfr(window, domponfnt);
                }
                publid KfybobrdFodusMbnbgfr gftCurrfntKfybobrdFodusMbnbgfr(AppContfxt dtx) {
                    rfturn KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr(dtx);
                }
                publid Contbinfr gftCurrfntFodusCydlfRoot() {
                    rfturn KfybobrdFodusMbnbgfr.durrfntFodusCydlfRoot;
                }
            }
        );
    }

    trbnsifnt KfybobrdFodusMbnbgfrPffr pffr;

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.KfybobrdFodusMbnbgfr");

    /**
     * Tif idfntififr for tif Forwbrd fodus trbvfrsbl kfys.
     *
     * @sff #sftDffbultFodusTrbvfrsblKfys
     * @sff #gftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     */
    publid stbtid finbl int FORWARD_TRAVERSAL_KEYS = 0;

    /**
     * Tif idfntififr for tif Bbdkwbrd fodus trbvfrsbl kfys.
     *
     * @sff #sftDffbultFodusTrbvfrsblKfys
     * @sff #gftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     */
    publid stbtid finbl int BACKWARD_TRAVERSAL_KEYS = 1;

    /**
     * Tif idfntififr for tif Up Cydlf fodus trbvfrsbl kfys.
     *
     * @sff #sftDffbultFodusTrbvfrsblKfys
     * @sff #gftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     */
    publid stbtid finbl int UP_CYCLE_TRAVERSAL_KEYS = 2;

    /**
     * Tif idfntififr for tif Down Cydlf fodus trbvfrsbl kfys.
     *
     * @sff #sftDffbultFodusTrbvfrsblKfys
     * @sff #gftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     */
    publid stbtid finbl int DOWN_CYCLE_TRAVERSAL_KEYS = 3;

    stbtid finbl int TRAVERSAL_KEY_LENGTH = DOWN_CYCLE_TRAVERSAL_KEYS + 1;

    /**
     * Rfturns tif durrfnt KfybobrdFodusMbnbgfr instbndf for tif dblling
     * tirfbd's dontfxt.
     *
     * @rfturn tiis tirfbd's dontfxt's KfybobrdFodusMbnbgfr
     * @sff #sftCurrfntKfybobrdFodusMbnbgfr
     */
    publid stbtid KfybobrdFodusMbnbgfr gftCurrfntKfybobrdFodusMbnbgfr() {
        rfturn gftCurrfntKfybobrdFodusMbnbgfr(AppContfxt.gftAppContfxt());
    }

    syndironizfd stbtid KfybobrdFodusMbnbgfr
        gftCurrfntKfybobrdFodusMbnbgfr(AppContfxt bppdontfxt)
    {
        KfybobrdFodusMbnbgfr mbnbgfr = (KfybobrdFodusMbnbgfr)
            bppdontfxt.gft(KfybobrdFodusMbnbgfr.dlbss);
        if (mbnbgfr == null) {
            mbnbgfr = nfw DffbultKfybobrdFodusMbnbgfr();
            bppdontfxt.put(KfybobrdFodusMbnbgfr.dlbss, mbnbgfr);
        }
        rfturn mbnbgfr;
    }

    /**
     * Sfts tif durrfnt KfybobrdFodusMbnbgfr instbndf for tif dblling tirfbd's
     * dontfxt. If null is spfdififd, tifn tif durrfnt KfybobrdFodusMbnbgfr
     * is rfplbdfd witi b nfw instbndf of DffbultKfybobrdFodusMbnbgfr.
     * <p>
     * If b SfdurityMbnbgfr is instbllfd, tif dblling tirfbd must bf grbntfd
     * tif AWTPfrmission "rfplbdfKfybobrdFodusMbnbgfr" in ordfr to rfplbdf tif
     * tif durrfnt KfybobrdFodusMbnbgfr. If tiis pfrmission is not grbntfd,
     * tiis mftiod will tirow b SfdurityExdfption, bnd tif durrfnt
     * KfybobrdFodusMbnbgfr will bf undibngfd.
     *
     * @pbrbm nfwMbnbgfr tif nfw KfybobrdFodusMbnbgfr for tiis tirfbd's dontfxt
     * @sff #gftCurrfntKfybobrdFodusMbnbgfr
     * @sff DffbultKfybobrdFodusMbnbgfr
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf pfrmission
     *         to rfplbdf tif durrfnt KfybobrdFodusMbnbgfr
     */
    publid stbtid void sftCurrfntKfybobrdFodusMbnbgfr(
        KfybobrdFodusMbnbgfr nfwMbnbgfr) tirows SfdurityExdfption
    {
        difdkRfplbdfKFMPfrmission();

        KfybobrdFodusMbnbgfr oldMbnbgfr = null;

        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            AppContfxt bppdontfxt = AppContfxt.gftAppContfxt();

            if (nfwMbnbgfr != null) {
                oldMbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr(bppdontfxt);

                bppdontfxt.put(KfybobrdFodusMbnbgfr.dlbss, nfwMbnbgfr);
            } flsf {
                oldMbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr(bppdontfxt);
                bppdontfxt.rfmovf(KfybobrdFodusMbnbgfr.dlbss);
            }
        }

        if (oldMbnbgfr != null) {
            oldMbnbgfr.firfPropfrtyCibngf("mbnbgingFodus",
                                          Boolfbn.TRUE,
                                          Boolfbn.FALSE);
        }
        if (nfwMbnbgfr != null) {
            nfwMbnbgfr.firfPropfrtyCibngf("mbnbgingFodus",
                                          Boolfbn.FALSE,
                                          Boolfbn.TRUE);
        }
    }

    /**
     * Tif Componfnt in bn bpplidbtion tibt will typidblly rfdfivf bll
     * KfyEvfnts gfnfrbtfd by tif usfr.
     */
    privbtf stbtid Componfnt fodusOwnfr;

    /**
     * Tif Componfnt in bn bpplidbtion tibt will rfgbin fodus wifn bn
     * outstbnding tfmporbry fodus trbnsffr ibs domplftfd, or tif fodus ownfr,
     * if no outstbnding tfmporbry trbnsffr fxists.
     */
    privbtf stbtid Componfnt pfrmbnfntFodusOwnfr;

    /**
     * Tif Window wiidi is, or dontbins, tif fodus ownfr.
     */
    privbtf stbtid Window fodusfdWindow;

    /**
     * Only b Frbmf or b Diblog dbn bf tif bdtivf Window. Tif nbtivf windowing
     * systfm mby dfnotf tif bdtivf Window witi b spfdibl dfdorbtion, sudi bs b
     * iigiligitfd titlf bbr. Tif bdtivf Window is blwbys fitifr tif fodusfd
     * Window, or tif first Frbmf or Diblog wiidi is bn ownfr of tif fodusfd
     * Window.
     */
    privbtf stbtid Window bdtivfWindow;

    /**
     * Tif dffbult FodusTrbvfrsblPolidy for bll Windows tibt ibvf no polidy of
     * tifir own sft. If tiosf Windows ibvf fodus-dydlf-root diildrfn tibt ibvf
     * no kfybobrd-trbvfrsbl polidy of tifir own, tifn tiosf diildrfn will blso
     * inifrit tiis polidy (bs will, rfdursivfly, tifir fodus-dydlf-root
     * diildrfn).
     */
    privbtf FodusTrbvfrsblPolidy dffbultPolidy =
        nfw DffbultFodusTrbvfrsblPolidy();

    /**
     * Tif bound propfrty nbmfs of fbdi fodus trbvfrsbl kfy.
     */
    privbtf stbtid finbl String[] dffbultFodusTrbvfrsblKfyPropfrtyNbmfs = {
        "forwbrdDffbultFodusTrbvfrsblKfys",
        "bbdkwbrdDffbultFodusTrbvfrsblKfys",
        "upCydlfDffbultFodusTrbvfrsblKfys",
        "downCydlfDffbultFodusTrbvfrsblKfys"
    };

    /**
     * Tif dffbult strokfs for initiblizing tif dffbult fodus trbvfrsbl kfys.
     */
    privbtf stbtid finbl AWTKfyStrokf[][] dffbultFodusTrbvfrsblKfyStrokfs = {
        {
            AWTKfyStrokf.gftAWTKfyStrokf(KfyEvfnt.VK_TAB, 0, fblsf),
            AWTKfyStrokf.gftAWTKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.CTRL_DOWN_MASK | InputEvfnt.CTRL_MASK, fblsf),
        },
        {
            AWTKfyStrokf.gftAWTKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.SHIFT_DOWN_MASK | InputEvfnt.SHIFT_MASK, fblsf),
            AWTKfyStrokf.gftAWTKfyStrokf(KfyEvfnt.VK_TAB,
                                         InputEvfnt.SHIFT_DOWN_MASK | InputEvfnt.SHIFT_MASK | InputEvfnt.CTRL_DOWN_MASK | InputEvfnt.CTRL_MASK,
                                         fblsf),
        },
        {},
        {},
      };
    /**
     * Tif dffbult fodus trbvfrsbl kfys. Ebdi brrby of trbvfrsbl kfys will bf
     * in ffffdt on bll Windows tibt ibvf no sudi brrby of tifir own fxpliditly
     * sft. Ebdi brrby will blso bf inifritfd, rfdursivfly, by bny diild
     * Componfnt of tiosf Windows tibt ibs no sudi brrby of its own fxpliditly
     * sft.
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf Sft<AWTKfyStrokf>[] dffbultFodusTrbvfrsblKfys = nfw Sft[4];

    /**
     * Tif durrfnt fodus dydlf root. If tif fodus ownfr is itsflf b fodus dydlf
     * root, tifn it mby bf bmbiguous bs to wiidi Componfnts rfprfsfnt tif nfxt
     * bnd prfvious Componfnts to fodus during normbl fodus trbvfrsbl. In tibt
     * dbsf, tif durrfnt fodus dydlf root is usfd to difffrfntibtf bmong tif
     * possibilitifs.
     */
    privbtf stbtid Contbinfr durrfntFodusCydlfRoot;

    /**
     * A dfsdription of bny VftobblfCibngfListfnfrs wiidi ibvf bffn rfgistfrfd.
     */
    privbtf VftobblfCibngfSupport vftobblfSupport;

    /**
     * A dfsdription of bny PropfrtyCibngfListfnfrs wiidi ibvf bffn rfgistfrfd.
     */
    privbtf PropfrtyCibngfSupport dibngfSupport;

    /**
     * Tiis KfybobrdFodusMbnbgfr's KfyEvfntDispbtdifr dibin. Tif List dofs not
     * indludf tiis KfybobrdFodusMbnbgfr unlfss it wbs fxpliditly rf-rfgistfrfd
     * vib b dbll to <dodf>bddKfyEvfntDispbtdifr</dodf>. If no otifr
     * KfyEvfntDispbtdifrs brf rfgistfrfd, tiis fifld mby bf null or rfffr to
     * b List of lfngti 0.
     */
    privbtf jbvb.util.LinkfdList<KfyEvfntDispbtdifr> kfyEvfntDispbtdifrs;

    /**
     * Tiis KfybobrdFodusMbnbgfr's KfyEvfntPostProdfssor dibin. Tif List dofs
     * not indludf tiis KfybobrdFodusMbnbgfr unlfss it wbs fxpliditly
     * rf-rfgistfrfd vib b dbll to <dodf>bddKfyEvfntPostProdfssor</dodf>.
     * If no otifr KfyEvfntPostProdfssors brf rfgistfrfd, tiis fifld mby bf
     * null or rfffr to b List of lfngti 0.
     */
    privbtf jbvb.util.LinkfdList<KfyEvfntPostProdfssor> kfyEvfntPostProdfssors;

    /**
     * Mbps Windows to tiosf Windows' most rfdfnt fodus ownfrs.
     */
    privbtf stbtid jbvb.util.Mbp<Window, WfbkRfffrfndf<Componfnt>> mostRfdfntFodusOwnfrs = nfw WfbkHbsiMbp<>();

    /**
     * Wf dbdif tif pfrmission usfd to vfrify tibt tif dblling tirfbd is
     * pfrmittfd to bddfss tif globbl fodus stbtf.
     */
    privbtf stbtid AWTPfrmission rfplbdfKfybobrdFodusMbnbgfrPfrmission;

    /*
     * SfqufndfdEvfnt wiidi is durrfntly dispbtdifd in AppContfxt.
     */
    trbnsifnt SfqufndfdEvfnt durrfntSfqufndfdEvfnt = null;

    finbl void sftCurrfntSfqufndfdEvfnt(SfqufndfdEvfnt durrfnt) {
        syndironizfd (SfqufndfdEvfnt.dlbss) {
            bssfrt(durrfnt == null || durrfntSfqufndfdEvfnt == null);
            durrfntSfqufndfdEvfnt = durrfnt;
        }
    }

    finbl SfqufndfdEvfnt gftCurrfntSfqufndfdEvfnt() {
        syndironizfd (SfqufndfdEvfnt.dlbss) {
            rfturn durrfntSfqufndfdEvfnt;
        }
    }

    stbtid Sft<AWTKfyStrokf> initFodusTrbvfrsblKfysSft(String vbluf, Sft<AWTKfyStrokf> tbrgftSft) {
        StringTokfnizfr tokfns = nfw StringTokfnizfr(vbluf, ",");
        wiilf (tokfns.ibsMorfTokfns()) {
            tbrgftSft.bdd(AWTKfyStrokf.gftAWTKfyStrokf(tokfns.nfxtTokfn()));
        }
        rfturn (tbrgftSft.isEmpty())
            ? Collfdtions.fmptySft()
            : Collfdtions.unmodifibblfSft(tbrgftSft);
    }

    /**
     * Initiblizfs b KfybobrdFodusMbnbgfr.
     */
    publid KfybobrdFodusMbnbgfr() {
        for (int i = 0; i < TRAVERSAL_KEY_LENGTH; i++) {
            Sft<AWTKfyStrokf> work_sft = nfw HbsiSft<>();
            for (int j = 0; j < dffbultFodusTrbvfrsblKfyStrokfs[i].lfngti; j++) {
                work_sft.bdd(dffbultFodusTrbvfrsblKfyStrokfs[i][j]);
            }
            dffbultFodusTrbvfrsblKfys[i] = (work_sft.isEmpty())
                ? Collfdtions.fmptySft()
                : Collfdtions.unmodifibblfSft(work_sft);
        }
        initPffr();
    }

    privbtf void initPffr() {
        Toolkit tk = Toolkit.gftDffbultToolkit();
        KfybobrdFodusMbnbgfrPffrProvidfr pffrProvidfr = (KfybobrdFodusMbnbgfrPffrProvidfr)tk;
        pffr = pffrProvidfr.gftKfybobrdFodusMbnbgfrPffr();
    }

    /**
     * Rfturns tif fodus ownfr, if tif fodus ownfr is in tif sbmf dontfxt bs
     * tif dblling tirfbd. Tif fodus ownfr is dffinfd bs tif Componfnt in bn
     * bpplidbtion tibt will typidblly rfdfivf bll KfyEvfnts gfnfrbtfd by tif
     * usfr. KfyEvfnts wiidi mbp to tif fodus ownfr's fodus trbvfrsbl kfys will
     * not bf dflivfrfd if fodus trbvfrsbl kfys brf fnbblfd for tif fodus
     * ownfr. In bddition, KfyEvfntDispbtdifrs mby rftbrgft or donsumf
     * KfyEvfnts bfforf tify rfbdi tif fodus ownfr.
     *
     * @rfturn tif fodus ownfr, or null if tif fodus ownfr is not b mfmbfr of
     *         tif dblling tirfbd's dontfxt
     * @sff #gftGlobblFodusOwnfr
     * @sff #sftGlobblFodusOwnfr
     */
    publid Componfnt gftFodusOwnfr() {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if (fodusOwnfr == null) {
                rfturn null;
            }

            rfturn (fodusOwnfr.bppContfxt == AppContfxt.gftAppContfxt())
                ? fodusOwnfr
                : null;
        }
    }

    /**
     * Rfturns tif fodus ownfr, fvfn if tif dblling tirfbd is in b difffrfnt
     * dontfxt tibn tif fodus ownfr. Tif fodus ownfr is dffinfd bs tif
     * Componfnt in bn bpplidbtion tibt will typidblly rfdfivf bll KfyEvfnts
     * gfnfrbtfd by tif usfr. KfyEvfnts wiidi mbp to tif fodus ownfr's fodus
     * trbvfrsbl kfys will not bf dflivfrfd if fodus trbvfrsbl kfys brf fnbblfd
     * for tif fodus ownfr. In bddition, KfyEvfntDispbtdifrs mby rftbrgft or
     * donsumf KfyEvfnts bfforf tify rfbdi tif fodus ownfr.
     * <p>
     * Tiis mftiod will tirow b SfdurityExdfption if tiis KfybobrdFodusMbnbgfr
     * is not tif durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's
     * dontfxt.
     *
     * @rfturn tif fodus ownfr
     * @sff #gftFodusOwnfr
     * @sff #sftGlobblFodusOwnfr
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     */
    protfdtfd Componfnt gftGlobblFodusOwnfr() tirows SfdurityExdfption {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();
            rfturn fodusOwnfr;
        }
    }

    /**
     * Sfts tif fodus ownfr. Tif opfrbtion will bf dbndfllfd if tif Componfnt
     * is not fodusbblf. Tif fodus ownfr is dffinfd bs tif Componfnt in bn
     * bpplidbtion tibt will typidblly rfdfivf bll KfyEvfnts gfnfrbtfd by tif
     * usfr. KfyEvfnts wiidi mbp to tif fodus ownfr's fodus trbvfrsbl kfys will
     * not bf dflivfrfd if fodus trbvfrsbl kfys brf fnbblfd for tif fodus
     * ownfr. In bddition, KfyEvfntDispbtdifrs mby rftbrgft or donsumf
     * KfyEvfnts bfforf tify rfbdi tif fodus ownfr.
     * <p>
     * Tiis mftiod dofs not bdtublly sft tif fodus to tif spfdififd Componfnt.
     * It mfrfly storfs tif vbluf to bf subsfqufntly rfturnfd by
     * <dodf>gftFodusOwnfr()</dodf>. Usf <dodf>Componfnt.rfqufstFodus()</dodf>
     * or <dodf>Componfnt.rfqufstFodusInWindow()</dodf> to dibngf tif fodus
     * ownfr, subjfdt to plbtform limitbtions.
     *
     * @pbrbm fodusOwnfr tif fodus ownfr
     * @sff #gftFodusOwnfr
     * @sff #gftGlobblFodusOwnfr
     * @sff Componfnt#rfqufstFodus()
     * @sff Componfnt#rfqufstFodusInWindow()
     * @sff Componfnt#isFodusbblf
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     * @bfbninfo
     *       bound: truf
     */
    protfdtfd void sftGlobblFodusOwnfr(Componfnt fodusOwnfr)
        tirows SfdurityExdfption
    {
        Componfnt oldFodusOwnfr = null;
        boolfbn siouldFirf = fblsf;

        if (fodusOwnfr == null || fodusOwnfr.isFodusbblf()) {
            syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
                difdkKFMSfdurity();

                oldFodusOwnfr = gftFodusOwnfr();

                try {
                    firfVftobblfCibngf("fodusOwnfr", oldFodusOwnfr,
                                       fodusOwnfr);
                } dbtdi (PropfrtyVftoExdfption f) {
                    // rfjfdtfd
                    rfturn;
                }

                KfybobrdFodusMbnbgfr.fodusOwnfr = fodusOwnfr;

                if (fodusOwnfr != null &&
                    (gftCurrfntFodusCydlfRoot() == null ||
                     !fodusOwnfr.isFodusCydlfRoot(gftCurrfntFodusCydlfRoot())))
                {
                    Contbinfr rootAndfstor =
                        fodusOwnfr.gftFodusCydlfRootAndfstor();
                    if (rootAndfstor == null && (fodusOwnfr instbndfof Window))
                    {
                        rootAndfstor = (Contbinfr)fodusOwnfr;
                    }
                    if (rootAndfstor != null) {
                        sftGlobblCurrfntFodusCydlfRootPriv(rootAndfstor);
                    }
                }

                siouldFirf = truf;
            }
        }

        if (siouldFirf) {
            firfPropfrtyCibngf("fodusOwnfr", oldFodusOwnfr, fodusOwnfr);
        }
    }

    /**
     * Clfbrs tif fodus ownfr bt boti tif Jbvb bnd nbtivf lfvfls if tif
     * fodus ownfr fxists bnd rfsidfs in tif sbmf dontfxt bs tif dblling tirfbd,
     * otifrwisf tif mftiod rfturns silfntly.
     * <p>
     * Tif fodus ownfr domponfnt will rfdfivf b pfrmbnfnt FOCUS_LOST fvfnt.
     * Aftfr tiis opfrbtion domplftfs, tif nbtivf windowing systfm will disdbrd
     * bll usfr-gfnfrbtfd KfyEvfnts until tif usfr sflfdts b nfw Componfnt to
     * rfdfivf fodus, or b Componfnt is givfn fodus fxpliditly vib b dbll to
     * {@dodf rfqufstFodus()}. Tiis opfrbtion dofs not dibngf tif fodusfd or
     * bdtivf Windows.
     *
     * @sff Componfnt#rfqufstFodus()
     * @sff jbvb.bwt.fvfnt.FodusEvfnt#FOCUS_LOST
     * @sindf 1.8
     */
    publid void dlfbrFodusOwnfr() {
        if (gftFodusOwnfr() != null) {
            dlfbrGlobblFodusOwnfr();
        }
    }

    /**
     * Clfbrs tif globbl fodus ownfr bt boti tif Jbvb bnd nbtivf lfvfls. If
     * tifrf fxists b fodus ownfr, tibt Componfnt will rfdfivf b pfrmbnfnt
     * FOCUS_LOST fvfnt. Aftfr tiis opfrbtion domplftfs, tif nbtivf windowing
     * systfm will disdbrd bll usfr-gfnfrbtfd KfyEvfnts until tif usfr sflfdts
     * b nfw Componfnt to rfdfivf fodus, or b Componfnt is givfn fodus
     * fxpliditly vib b dbll to <dodf>rfqufstFodus()</dodf>. Tiis opfrbtion
     * dofs not dibngf tif fodusfd or bdtivf Windows.
     * <p>
     * If b SfdurityMbnbgfr is instbllfd, tif dblling tirfbd must bf grbntfd
     * tif "rfplbdfKfybobrdFodusMbnbgfr" AWTPfrmission. If tiis pfrmission is
     * not grbntfd, tiis mftiod will tirow b SfdurityExdfption, bnd tif durrfnt
     * fodus ownfr will not bf dlfbrfd.
     * <p>
     * Tiis mftiod is intfndfd to bf usfd only by KfybobrdFodusMbnbgfr sft bs
     * durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt. It is not
     * for gfnfrbl dlifnt usf.
     *
     * @sff KfybobrdFodusMbnbgfr#dlfbrFodusOwnfr
     * @sff Componfnt#rfqufstFodus()
     * @sff jbvb.bwt.fvfnt.FodusEvfnt#FOCUS_LOST
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *         "rfplbdfKfybobrdFodusMbnbgfr" pfrmission
     */
    publid void dlfbrGlobblFodusOwnfr()
        tirows SfdurityExdfption
    {
        difdkRfplbdfKFMPfrmission();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            // Toolkit must bf fully initiblizfd, otifrwisf
            // _dlfbrGlobblFodusOwnfr will drbsi or tirow bn fxdfption
            Toolkit.gftDffbultToolkit();

            _dlfbrGlobblFodusOwnfr();
        }
    }
    privbtf void _dlfbrGlobblFodusOwnfr() {
        Window bdtivfWindow = mbrkClfbrGlobblFodusOwnfr();
        pffr.dlfbrGlobblFodusOwnfr(bdtivfWindow);
    }

    void dlfbrGlobblFodusOwnfrPriv() {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                dlfbrGlobblFodusOwnfr();
                rfturn null;
            }
        });
    }

    Componfnt gftNbtivfFodusOwnfr() {
        rfturn pffr.gftCurrfntFodusOwnfr();
    }

    void sftNbtivfFodusOwnfr(Componfnt domp) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fodusLog.finfst("Cblling pffr {0} sftCurrfntFodusOwnfr for {1}",
                            String.vblufOf(pffr), String.vblufOf(domp));
        }
        pffr.sftCurrfntFodusOwnfr(domp);
    }

    Window gftNbtivfFodusfdWindow() {
        rfturn pffr.gftCurrfntFodusfdWindow();
    }

    /**
     * Rfturns tif pfrmbnfnt fodus ownfr, if tif pfrmbnfnt fodus ownfr is in
     * tif sbmf dontfxt bs tif dblling tirfbd. Tif pfrmbnfnt fodus ownfr is
     * dffinfd bs tif lbst Componfnt in bn bpplidbtion to rfdfivf b pfrmbnfnt
     * FOCUS_GAINED fvfnt. Tif fodus ownfr bnd pfrmbnfnt fodus ownfr brf
     * fquivblfnt unlfss b tfmporbry fodus dibngf is durrfntly in ffffdt. In
     * sudi b situbtion, tif pfrmbnfnt fodus ownfr will bgbin bf tif fodus
     * ownfr wifn tif tfmporbry fodus dibngf fnds.
     *
     * @rfturn tif pfrmbnfnt fodus ownfr, or null if tif pfrmbnfnt fodus ownfr
     *         is not b mfmbfr of tif dblling tirfbd's dontfxt
     * @sff #gftGlobblPfrmbnfntFodusOwnfr
     * @sff #sftGlobblPfrmbnfntFodusOwnfr
     */
    publid Componfnt gftPfrmbnfntFodusOwnfr() {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if (pfrmbnfntFodusOwnfr == null) {
                rfturn null;
            }

            rfturn (pfrmbnfntFodusOwnfr.bppContfxt ==
                    AppContfxt.gftAppContfxt())
                ? pfrmbnfntFodusOwnfr
                : null;
        }
    }

    /**
     * Rfturns tif pfrmbnfnt fodus ownfr, fvfn if tif dblling tirfbd is in b
     * difffrfnt dontfxt tibn tif pfrmbnfnt fodus ownfr. Tif pfrmbnfnt fodus
     * ownfr is dffinfd bs tif lbst Componfnt in bn bpplidbtion to rfdfivf b
     * pfrmbnfnt FOCUS_GAINED fvfnt. Tif fodus ownfr bnd pfrmbnfnt fodus ownfr
     * brf fquivblfnt unlfss b tfmporbry fodus dibngf is durrfntly in ffffdt.
     * In sudi b situbtion, tif pfrmbnfnt fodus ownfr will bgbin bf tif fodus
     * ownfr wifn tif tfmporbry fodus dibngf fnds.
     *
     * @rfturn tif pfrmbnfnt fodus ownfr
     * @sff #gftPfrmbnfntFodusOwnfr
     * @sff #sftGlobblPfrmbnfntFodusOwnfr
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     */
    protfdtfd Componfnt gftGlobblPfrmbnfntFodusOwnfr()
        tirows SfdurityExdfption
    {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();
            rfturn pfrmbnfntFodusOwnfr;
        }
    }

    /**
     * Sfts tif pfrmbnfnt fodus ownfr. Tif opfrbtion will bf dbndfllfd if tif
     * Componfnt is not fodusbblf. Tif pfrmbnfnt fodus ownfr is dffinfd bs tif
     * lbst Componfnt in bn bpplidbtion to rfdfivf b pfrmbnfnt FOCUS_GAINED
     * fvfnt. Tif fodus ownfr bnd pfrmbnfnt fodus ownfr brf fquivblfnt unlfss
     * b tfmporbry fodus dibngf is durrfntly in ffffdt. In sudi b situbtion,
     * tif pfrmbnfnt fodus ownfr will bgbin bf tif fodus ownfr wifn tif
     * tfmporbry fodus dibngf fnds.
     * <p>
     * Tiis mftiod dofs not bdtublly sft tif fodus to tif spfdififd Componfnt.
     * It mfrfly storfs tif vbluf to bf subsfqufntly rfturnfd by
     * <dodf>gftPfrmbnfntFodusOwnfr()</dodf>. Usf
     * <dodf>Componfnt.rfqufstFodus()</dodf> or
     * <dodf>Componfnt.rfqufstFodusInWindow()</dodf> to dibngf tif fodus ownfr,
     * subjfdt to plbtform limitbtions.
     *
     * @pbrbm pfrmbnfntFodusOwnfr tif pfrmbnfnt fodus ownfr
     * @sff #gftPfrmbnfntFodusOwnfr
     * @sff #gftGlobblPfrmbnfntFodusOwnfr
     * @sff Componfnt#rfqufstFodus()
     * @sff Componfnt#rfqufstFodusInWindow()
     * @sff Componfnt#isFodusbblf
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     * @bfbninfo
     *       bound: truf
     */
    protfdtfd void sftGlobblPfrmbnfntFodusOwnfr(Componfnt pfrmbnfntFodusOwnfr)
        tirows SfdurityExdfption
    {
        Componfnt oldPfrmbnfntFodusOwnfr = null;
        boolfbn siouldFirf = fblsf;

        if (pfrmbnfntFodusOwnfr == null || pfrmbnfntFodusOwnfr.isFodusbblf()) {
            syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
                difdkKFMSfdurity();

                oldPfrmbnfntFodusOwnfr = gftPfrmbnfntFodusOwnfr();

                try {
                    firfVftobblfCibngf("pfrmbnfntFodusOwnfr",
                                       oldPfrmbnfntFodusOwnfr,
                                       pfrmbnfntFodusOwnfr);
                } dbtdi (PropfrtyVftoExdfption f) {
                    // rfjfdtfd
                    rfturn;
                }

                KfybobrdFodusMbnbgfr.pfrmbnfntFodusOwnfr = pfrmbnfntFodusOwnfr;

                KfybobrdFodusMbnbgfr.
                    sftMostRfdfntFodusOwnfr(pfrmbnfntFodusOwnfr);

                siouldFirf = truf;
            }
        }

        if (siouldFirf) {
            firfPropfrtyCibngf("pfrmbnfntFodusOwnfr", oldPfrmbnfntFodusOwnfr,
                               pfrmbnfntFodusOwnfr);
        }
    }

    /**
     * Rfturns tif fodusfd Window, if tif fodusfd Window is in tif sbmf dontfxt
     * bs tif dblling tirfbd. Tif fodusfd Window is tif Window tibt is or
     * dontbins tif fodus ownfr.
     *
     * @rfturn tif fodusfd Window, or null if tif fodusfd Window is not b
     *         mfmbfr of tif dblling tirfbd's dontfxt
     * @sff #gftGlobblFodusfdWindow
     * @sff #sftGlobblFodusfdWindow
     */
    publid Window gftFodusfdWindow() {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if (fodusfdWindow == null) {
                rfturn null;
            }

            rfturn (fodusfdWindow.bppContfxt == AppContfxt.gftAppContfxt())
                ? fodusfdWindow
                : null;
        }
    }

    /**
     * Rfturns tif fodusfd Window, fvfn if tif dblling tirfbd is in b difffrfnt
     * dontfxt tibn tif fodusfd Window. Tif fodusfd Window is tif Window tibt
     * is or dontbins tif fodus ownfr.
     *
     * @rfturn tif fodusfd Window
     * @sff #gftFodusfdWindow
     * @sff #sftGlobblFodusfdWindow
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     */
    protfdtfd Window gftGlobblFodusfdWindow() tirows SfdurityExdfption {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();
            rfturn fodusfdWindow;
        }
    }

    /**
     * Sfts tif fodusfd Window. Tif fodusfd Window is tif Window tibt is or
     * dontbins tif fodus ownfr. Tif opfrbtion will bf dbndfllfd if tif
     * spfdififd Window to fodus is not b fodusbblf Window.
     * <p>
     * Tiis mftiod dofs not bdtublly dibngf tif fodusfd Window bs fbr bs tif
     * nbtivf windowing systfm is dondfrnfd. It mfrfly storfs tif vbluf to bf
     * subsfqufntly rfturnfd by <dodf>gftFodusfdWindow()</dodf>. Usf
     * <dodf>Componfnt.rfqufstFodus()</dodf> or
     * <dodf>Componfnt.rfqufstFodusInWindow()</dodf> to dibngf tif fodusfd
     * Window, subjfdt to plbtform limitbtions.
     *
     * @pbrbm fodusfdWindow tif fodusfd Window
     * @sff #gftFodusfdWindow
     * @sff #gftGlobblFodusfdWindow
     * @sff Componfnt#rfqufstFodus()
     * @sff Componfnt#rfqufstFodusInWindow()
     * @sff Window#isFodusbblfWindow
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     * @bfbninfo
     *       bound: truf
     */
    protfdtfd void sftGlobblFodusfdWindow(Window fodusfdWindow)
        tirows SfdurityExdfption
    {
        Window oldFodusfdWindow = null;
        boolfbn siouldFirf = fblsf;

        if (fodusfdWindow == null || fodusfdWindow.isFodusbblfWindow()) {
            syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
                difdkKFMSfdurity();

                oldFodusfdWindow = gftFodusfdWindow();

                try {
                    firfVftobblfCibngf("fodusfdWindow", oldFodusfdWindow,
                                       fodusfdWindow);
                } dbtdi (PropfrtyVftoExdfption f) {
                    // rfjfdtfd
                    rfturn;
                }

                KfybobrdFodusMbnbgfr.fodusfdWindow = fodusfdWindow;
                siouldFirf = truf;
            }
        }

        if (siouldFirf) {
            firfPropfrtyCibngf("fodusfdWindow", oldFodusfdWindow,
                               fodusfdWindow);
        }
    }

    /**
     * Rfturns tif bdtivf Window, if tif bdtivf Window is in tif sbmf dontfxt
     * bs tif dblling tirfbd. Only b Frbmf or b Diblog dbn bf tif bdtivf
     * Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window or its
     * diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf bbr.
     * Tif bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first
     * Frbmf or Diblog tibt is bn ownfr of tif fodusfd Window.
     *
     * @rfturn tif bdtivf Window, or null if tif bdtivf Window is not b mfmbfr
     *         of tif dblling tirfbd's dontfxt
     * @sff #gftGlobblAdtivfWindow
     * @sff #sftGlobblAdtivfWindow
     */
    publid Window gftAdtivfWindow() {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if (bdtivfWindow == null) {
                rfturn null;
            }

            rfturn (bdtivfWindow.bppContfxt == AppContfxt.gftAppContfxt())
                ? bdtivfWindow
                : null;
        }
    }

    /**
     * Rfturns tif bdtivf Window, fvfn if tif dblling tirfbd is in b difffrfnt
     * dontfxt tibn tif bdtivf Window. Only b Frbmf or b Diblog dbn bf tif
     * bdtivf Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window
     * or its diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf
     * bbr. Tif bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first
     * Frbmf or Diblog tibt is bn ownfr of tif fodusfd Window.
     *
     * @rfturn tif bdtivf Window
     * @sff #gftAdtivfWindow
     * @sff #sftGlobblAdtivfWindow
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     */
    protfdtfd Window gftGlobblAdtivfWindow() tirows SfdurityExdfption {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();
            rfturn bdtivfWindow;
        }
    }

    /**
     * Sfts tif bdtivf Window. Only b Frbmf or b Diblog dbn bf tif bdtivf
     * Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window or its
     * diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf bbr. Tif
     * bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first Frbmf or
     * Diblog tibt is bn ownfr of tif fodusfd Window.
     * <p>
     * Tiis mftiod dofs not bdtublly dibngf tif bdtivf Window bs fbr bs tif
     * nbtivf windowing systfm is dondfrnfd. It mfrfly storfs tif vbluf to bf
     * subsfqufntly rfturnfd by <dodf>gftAdtivfWindow()</dodf>. Usf
     * <dodf>Componfnt.rfqufstFodus()</dodf> or
     * <dodf>Componfnt.rfqufstFodusInWindow()</dodf>to dibngf tif bdtivf
     * Window, subjfdt to plbtform limitbtions.
     *
     * @pbrbm bdtivfWindow tif bdtivf Window
     * @sff #gftAdtivfWindow
     * @sff #gftGlobblAdtivfWindow
     * @sff Componfnt#rfqufstFodus()
     * @sff Componfnt#rfqufstFodusInWindow()
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     * @bfbninfo
     *       bound: truf
     */
    protfdtfd void sftGlobblAdtivfWindow(Window bdtivfWindow)
        tirows SfdurityExdfption
    {
        Window oldAdtivfWindow;
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();

            oldAdtivfWindow = gftAdtivfWindow();
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("Sftting globbl bdtivf window to " + bdtivfWindow + ", old bdtivf " + oldAdtivfWindow);
            }

            try {
                firfVftobblfCibngf("bdtivfWindow", oldAdtivfWindow,
                                   bdtivfWindow);
            } dbtdi (PropfrtyVftoExdfption f) {
                // rfjfdtfd
                rfturn;
            }

            KfybobrdFodusMbnbgfr.bdtivfWindow = bdtivfWindow;
        }

        firfPropfrtyCibngf("bdtivfWindow", oldAdtivfWindow, bdtivfWindow);
    }

    /**
     * Rfturns tif dffbult FodusTrbvfrsblPolidy. Top-lfvfl domponfnts
     * usf tiis vbluf on tifir drfbtion to initiblizf tifir own fodus trbvfrsbl
     * polidy by fxplidit dbll to Contbinfr.sftFodusTrbvfrsblPolidy.
     *
     * @rfturn tif dffbult FodusTrbvfrsblPolidy. null will nfvfr bf rfturnfd.
     * @sff #sftDffbultFodusTrbvfrsblPolidy
     * @sff Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff Contbinfr#gftFodusTrbvfrsblPolidy
     */
    publid syndironizfd FodusTrbvfrsblPolidy gftDffbultFodusTrbvfrsblPolidy() {
        rfturn dffbultPolidy;
    }

    /**
     * Sfts tif dffbult FodusTrbvfrsblPolidy. Top-lfvfl domponfnts
     * usf tiis vbluf on tifir drfbtion to initiblizf tifir own fodus trbvfrsbl
     * polidy by fxplidit dbll to Contbinfr.sftFodusTrbvfrsblPolidy.
     * Notf: tiis dbll dofsn't bfffdt blrfbdy drfbtfd domponfnts bs tify ibvf
     * tifir polidy initiblizfd. Only nfw domponfnts will usf tiis polidy bs
     * tifir dffbult polidy.
     *
     * @pbrbm dffbultPolidy tif nfw, dffbult FodusTrbvfrsblPolidy
     * @sff #gftDffbultFodusTrbvfrsblPolidy
     * @sff Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff Contbinfr#gftFodusTrbvfrsblPolidy
     * @tirows IllfgblArgumfntExdfption if dffbultPolidy is null
     * @bfbninfo
     *       bound: truf
     */
    publid void sftDffbultFodusTrbvfrsblPolidy(FodusTrbvfrsblPolidy
                                               dffbultPolidy) {
        if (dffbultPolidy == null) {
            tirow nfw IllfgblArgumfntExdfption("dffbult fodus trbvfrsbl polidy dbnnot bf null");
        }

        FodusTrbvfrsblPolidy oldPolidy;

        syndironizfd (tiis) {
            oldPolidy = tiis.dffbultPolidy;
            tiis.dffbultPolidy = dffbultPolidy;
        }

        firfPropfrtyCibngf("dffbultFodusTrbvfrsblPolidy", oldPolidy,
                           dffbultPolidy);
    }

    /**
     * Sfts tif dffbult fodus trbvfrsbl kfys for b givfn trbvfrsbl opfrbtion.
     * Tiis trbvfrsbl kfy {@dodf Sft} will bf in ffffdt on bll
     * {@dodf Window}s tibt ibvf no sudi {@dodf Sft} of
     * tifir own fxpliditly dffinfd. Tiis {@dodf Sft} will blso bf
     * inifritfd, rfdursivfly, by bny diild {@dodf Componfnt} of
     * tiosf {@dodf Windows} tibt ibs
     * no sudi {@dodf Sft} of its own fxpliditly dffinfd.
     * <p>
     * Tif dffbult vblufs for tif dffbult fodus trbvfrsbl kfys brf
     * implfmfntbtion-dfpfndfnt. Sun rfdommfnds tibt bll implfmfntbtions for b
     * pbrtidulbr nbtivf plbtform usf tif sbmf dffbult vblufs. Tif
     * rfdommfndbtions for Windows bnd Unix brf listfd bflow. Tifsf
     * rfdommfndbtions brf usfd in tif Sun AWT implfmfntbtions.
     *
     * <tbblf bordfr=1 summbry="Rfdommfndfd dffbult vblufs for fodus trbvfrsbl kfys">
     * <tr>
     *    <ti>Idfntififr</ti>
     *    <ti>Mfbning</ti>
     *    <ti>Dffbult</ti>
     * </tr>
     * <tr>
     *    <td>{@dodf KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS}</td>
     *    <td>Normbl forwbrd kfybobrd trbvfrsbl</td>
     *    <td>{@dodf TAB} on {@dodf KEY_PRESSED},
     *        {@dodf CTRL-TAB} on {@dodf KEY_PRESSED}</td>
     * </tr>
     * <tr>
     *    <td>{@dodf KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS}</td>
     *    <td>Normbl rfvfrsf kfybobrd trbvfrsbl</td>
     *    <td>{@dodf SHIFT-TAB} on {@dodf KEY_PRESSED},
     *        {@dodf CTRL-SHIFT-TAB} on {@dodf KEY_PRESSED}</td>
     * </tr>
     * <tr>
     *    <td>{@dodf KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS}</td>
     *    <td>Go up onf fodus trbvfrsbl dydlf</td>
     *    <td>nonf</td>
     * </tr>
     * <tr>
     *    <td>{@dodf KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS}</td>
     *    <td>Go down onf fodus trbvfrsbl dydlf</td>
     *    <td>nonf</td>
     * </tr>
     * </tbblf>
     *
     * To disbblf b trbvfrsbl kfy, usf bn fmpty {@dodf Sft};
     * {@dodf Collfdtions.EMPTY_SET} is rfdommfndfd.
     * <p>
     * Using tif {@dodf AWTKfyStrokf} API, dlifnt dodf dbn
     * spfdify on wiidi of two
     * spfdifid {@dodf KfyEvfnt}s, {@dodf KEY_PRESSED} or
     * {@dodf KEY_RELEASED}, tif fodus trbvfrsbl opfrbtion will
     * oddur. Rfgbrdlfss of wiidi {@dodf KfyEvfnt} is spfdififd,
     * iowfvfr, bll {@dodf KfyEvfnt}s rflbtfd to tif fodus
     * trbvfrsbl kfy, indluding tif bssodibtfd {@dodf KEY_TYPED}
     * fvfnt, will bf donsumfd, bnd will not bf dispbtdifd
     * to bny {@dodf Componfnt}. It is b runtimf frror to
     * spfdify b {@dodf KEY_TYPED} fvfnt bs
     * mbpping to b fodus trbvfrsbl opfrbtion, or to mbp tif sbmf fvfnt to
     * multiplf dffbult fodus trbvfrsbl opfrbtions.
     * <p>
     * Tiis mftiod mby tirow b {@dodf ClbssCbstExdfption} if bny {@dodf Objfdt}
     * in {@dodf kfystrokfs} is not bn {@dodf AWTKfyStrokf}.
     *
     * @pbrbm id onf of
     *        {@dodf KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS},
     *        {@dodf KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS},
     *        {@dodf KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS}, or
     *        {@dodf KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS}
     * @pbrbm kfystrokfs tif Sft of {@dodf AWTKfyStrokf}s for tif
     *        spfdififd opfrbtion
     * @sff #gftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         {@dodf KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS},
     *         {@dodf KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS},
     *         {@dodf KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS}, or
     *         {@dodf KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS},
     *         or if kfystrokfs is {@dodf null},
     *         or if kfystrokfs dontbins {@dodf null},
     *         or if bny kfystrokf
     *         rfprfsfnts b {@dodf KEY_TYPED} fvfnt,
     *         or if bny kfystrokf blrfbdy mbps
     *         to bnotifr dffbult fodus trbvfrsbl opfrbtion
     * @bfbninfo
     *       bound: truf
     */
    publid void
        sftDffbultFodusTrbvfrsblKfys(int id,
                                     Sft<? fxtfnds AWTKfyStrokf> kfystrokfs)
    {
        if (id < 0 || id >= TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }
        if (kfystrokfs == null) {
            tirow nfw IllfgblArgumfntExdfption("dbnnot sft null Sft of dffbult fodus trbvfrsbl kfys");
        }

        Sft<AWTKfyStrokf> oldKfys;

        syndironizfd (tiis) {
            for (AWTKfyStrokf kfystrokf : kfystrokfs) {

                if (kfystrokf == null) {
                    tirow nfw IllfgblArgumfntExdfption("dbnnot sft null fodus trbvfrsbl kfy");
                }

                if (kfystrokf.gftKfyCibr() != KfyEvfnt.CHAR_UNDEFINED) {
                    tirow nfw IllfgblArgumfntExdfption("fodus trbvfrsbl kfys dbnnot mbp to KEY_TYPED fvfnts");
                }

                // Cifdk to sff if kfy blrfbdy mbps to bnotifr trbvfrsbl
                // opfrbtion
                for (int i = 0; i < TRAVERSAL_KEY_LENGTH; i++) {
                    if (i == id) {
                        dontinuf;
                    }

                    if (dffbultFodusTrbvfrsblKfys[i].dontbins(kfystrokf)) {
                        tirow nfw IllfgblArgumfntExdfption("fodus trbvfrsbl kfys must bf uniquf for b Componfnt");
                    }
                }
            }

            oldKfys = dffbultFodusTrbvfrsblKfys[id];
            dffbultFodusTrbvfrsblKfys[id] =
                Collfdtions.unmodifibblfSft(nfw HbsiSft<>(kfystrokfs));
        }

        firfPropfrtyCibngf(dffbultFodusTrbvfrsblKfyPropfrtyNbmfs[id],
                           oldKfys, kfystrokfs);
    }

    /**
     * Rfturns b Sft of dffbult fodus trbvfrsbl kfys for b givfn trbvfrsbl
     * opfrbtion. Tiis trbvfrsbl kfy Sft will bf in ffffdt on bll Windows tibt
     * ibvf no sudi Sft of tifir own fxpliditly dffinfd. Tiis Sft will blso bf
     * inifritfd, rfdursivfly, by bny diild Componfnt of tiosf Windows tibt ibs
     * no sudi Sft of its own fxpliditly dffinfd. (Sff
     * <dodf>sftDffbultFodusTrbvfrsblKfys</dodf> for b full dfsdription of fbdi
     * opfrbtion.)
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @rfturn tif <dodf>Sft</dodf> of <dodf>AWTKfyStrokf</dodf>s
     *         for tif spfdififd opfrbtion; tif <dodf>Sft</dodf>
     *         will bf unmodifibblf, bnd mby bf fmpty; <dodf>null</dodf>
     *         will nfvfr bf rfturnfd
     * @sff #sftDffbultFodusTrbvfrsblKfys
     * @sff Componfnt#sftFodusTrbvfrsblKfys
     * @sff Componfnt#gftFodusTrbvfrsblKfys
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     */
    publid Sft<AWTKfyStrokf> gftDffbultFodusTrbvfrsblKfys(int id) {
        if (id < 0 || id >= TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        // Okby to rfturn Sft dirfdtly bfdbusf it is bn unmodifibblf vifw
        rfturn dffbultFodusTrbvfrsblKfys[id];
    }

    /**
     * Rfturns tif durrfnt fodus dydlf root, if tif durrfnt fodus dydlf root is
     * in tif sbmf dontfxt bs tif dblling tirfbd. If tif fodus ownfr is itsflf
     * b fodus dydlf root, tifn it mby bf bmbiguous bs to wiidi Componfnts
     * rfprfsfnt tif nfxt bnd prfvious Componfnts to fodus during normbl fodus
     * trbvfrsbl. In tibt dbsf, tif durrfnt fodus dydlf root is usfd to
     * difffrfntibtf bmong tif possibilitifs.
     * <p>
     * Tiis mftiod is intfndfd to bf usfd only by KfybobrdFodusMbnbgfrs bnd
     * fodus implfmfntbtions. It is not for gfnfrbl dlifnt usf.
     *
     * @rfturn tif durrfnt fodus dydlf root, or null if tif durrfnt fodus dydlf
     *         root is not b mfmbfr of tif dblling tirfbd's dontfxt
     * @sff #gftGlobblCurrfntFodusCydlfRoot
     * @sff #sftGlobblCurrfntFodusCydlfRoot
     */
    publid Contbinfr gftCurrfntFodusCydlfRoot() {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if (durrfntFodusCydlfRoot == null) {
                rfturn null;
            }

            rfturn (durrfntFodusCydlfRoot.bppContfxt ==
                    AppContfxt.gftAppContfxt())
                ? durrfntFodusCydlfRoot
                : null;
        }
    }

    /**
     * Rfturns tif durrfnt fodus dydlf root, fvfn if tif dblling tirfbd is in b
     * difffrfnt dontfxt tibn tif durrfnt fodus dydlf root. If tif fodus ownfr
     * is itsflf b fodus dydlf root, tifn it mby bf bmbiguous bs to wiidi
     * Componfnts rfprfsfnt tif nfxt bnd prfvious Componfnts to fodus during
     * normbl fodus trbvfrsbl. In tibt dbsf, tif durrfnt fodus dydlf root is
     * usfd to difffrfntibtf bmong tif possibilitifs.
     *
     * @rfturn tif durrfnt fodus dydlf root, or null if tif durrfnt fodus dydlf
     *         root is not b mfmbfr of tif dblling tirfbd's dontfxt
     * @sff #gftCurrfntFodusCydlfRoot
     * @sff #sftGlobblCurrfntFodusCydlfRoot
     * @tirows SfdurityExdfption if tiis KfybobrdFodusMbnbgfr is not tif
     *         durrfnt KfybobrdFodusMbnbgfr for tif dblling tirfbd's dontfxt
     *         bnd if tif dblling tirfbd dofs not ibvf "rfplbdfKfybobrdFodusMbnbgfr"
     *         pfrmission
     */
    protfdtfd Contbinfr gftGlobblCurrfntFodusCydlfRoot()
        tirows SfdurityExdfption
    {
        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            difdkKFMSfdurity();
            rfturn durrfntFodusCydlfRoot;
        }
    }

    /**
     * Sfts tif durrfnt fodus dydlf root. If tif fodus ownfr is itsflf b fodus
     * dydlf root, tifn it mby bf bmbiguous bs to wiidi Componfnts rfprfsfnt
     * tif nfxt bnd prfvious Componfnts to fodus during normbl fodus trbvfrsbl.
     * In tibt dbsf, tif durrfnt fodus dydlf root is usfd to difffrfntibtf
     * bmong tif possibilitifs.
     * <p>
     * If b SfdurityMbnbgfr is instbllfd, tif dblling tirfbd must bf grbntfd
     * tif "rfplbdfKfybobrdFodusMbnbgfr" AWTPfrmission. If tiis pfrmission is
     * not grbntfd, tiis mftiod will tirow b SfdurityExdfption, bnd tif durrfnt
     * fodus dydlf root will not bf dibngfd.
     * <p>
     * Tiis mftiod is intfndfd to bf usfd only by KfybobrdFodusMbnbgfrs bnd
     * fodus implfmfntbtions. It is not for gfnfrbl dlifnt usf.
     *
     * @pbrbm nfwFodusCydlfRoot tif nfw fodus dydlf root
     * @sff #gftCurrfntFodusCydlfRoot
     * @sff #gftGlobblCurrfntFodusCydlfRoot
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *         "rfplbdfKfybobrdFodusMbnbgfr" pfrmission
     * @bfbninfo
     *       bound: truf
     */
    publid void sftGlobblCurrfntFodusCydlfRoot(Contbinfr nfwFodusCydlfRoot)
        tirows SfdurityExdfption
    {
        difdkRfplbdfKFMPfrmission();

        Contbinfr oldFodusCydlfRoot;

        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            oldFodusCydlfRoot  = gftCurrfntFodusCydlfRoot();
            durrfntFodusCydlfRoot = nfwFodusCydlfRoot;
        }

        firfPropfrtyCibngf("durrfntFodusCydlfRoot", oldFodusCydlfRoot,
                           nfwFodusCydlfRoot);
    }

    void sftGlobblCurrfntFodusCydlfRootPriv(finbl Contbinfr nfwFodusCydlfRoot) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                sftGlobblCurrfntFodusCydlfRoot(nfwFodusCydlfRoot);
                rfturn null;
            }
        });
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss, indluding tif
     * following:
     * <ul>
     *    <li>wiftifr tif KfybobrdFodusMbnbgfr is durrfntly mbnbging fodus
     *        for tiis bpplidbtion or bpplft's browsfr dontfxt
     *        ("mbnbgingFodus")</li>
     *    <li>tif fodus ownfr ("fodusOwnfr")</li>
     *    <li>tif pfrmbnfnt fodus ownfr ("pfrmbnfntFodusOwnfr")</li>
     *    <li>tif fodusfd Window ("fodusfdWindow")</li>
     *    <li>tif bdtivf Window ("bdtivfWindow")</li>
     *    <li>tif dffbult fodus trbvfrsbl polidy
     *        ("dffbultFodusTrbvfrsblPolidy")</li>
     *    <li>tif Sft of dffbult FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif durrfnt fodus dydlf root ("durrfntFodusCydlfRoot")</li>
     * </ul>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf bddfd
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (dibngfSupport == null) {
                    dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
                }
                dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
            }
        }
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list. Tiis mftiod
     * siould bf usfd to rfmovf tif PropfrtyCibngfListfnfrs tibt wfrf
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (dibngfSupport != null) {
                    dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
                }
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis kfybobrd fodus mbnbgfr.
     *
     * @rfturn bll of tiis kfybobrd fodus mbnbgfr's
     *         <dodf>PropfrtyCibngfListfnfr</dodf>s
     *         or bn fmpty brrby if no propfrty dibngf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sindf 1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf usfr-dffinfd, or onf of tif
     * following:
     * <ul>
     *    <li>wiftifr tif KfybobrdFodusMbnbgfr is durrfntly mbnbging fodus
     *        for tiis bpplidbtion or bpplft's browsfr dontfxt
     *        ("mbnbgingFodus")</li>
     *    <li>tif fodus ownfr ("fodusOwnfr")</li>
     *    <li>tif pfrmbnfnt fodus ownfr ("pfrmbnfntFodusOwnfr")</li>
     *    <li>tif fodusfd Window ("fodusfdWindow")</li>
     *    <li>tif bdtivf Window ("bdtivfWindow")</li>
     *    <li>tif dffbult fodus trbvfrsbl polidy
     *        ("dffbultFodusTrbvfrsblPolidy")</li>
     *    <li>tif Sft of dffbult FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif Sft of dffbult DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfDffbultFodusTrbvfrsblKfys")</li>
     *    <li>tif durrfnt fodus dydlf root ("durrfntFodusCydlfRoot")</li>
     * </ul>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf bddfd
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     */
    publid void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                          PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (dibngfSupport == null) {
                    dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
                }
                dibngfSupport.bddPropfrtyCibngfListfnfr(propfrtyNbmf,
                                                        listfnfr);
            }
        }
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list for b spfdifid
     * propfrty. Tiis mftiod siould bf usfd to rfmovf PropfrtyCibngfListfnfrs
     * tibt wfrf rfgistfrfd for b spfdifid bound propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf b vblid propfrty nbmf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void rfmovfPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                             PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (dibngfSupport != null) {
                    dibngfSupport.rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf,
                                                               listfnfr);
                }
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>PropfrtyCibngfListfnfr</dodf>s
     * bssodibtfd witi tif nbmfd propfrty.
     *
     * @pbrbm  propfrtyNbmf tif propfrty nbmf
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfr</dodf>s bssodibtfd witi
     *         tif nbmfd propfrty or bn fmpty brrby if no sudi listfnfrs ibvf
     *         bffn bddfd.
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sindf 1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf) {
        if (dibngfSupport == null) {
            dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
    }

    /**
     * Firfs b PropfrtyCibngfEvfnt in rfsponsf to b dibngf in b bound propfrty.
     * Tif fvfnt will bf dflivfrfd to bll rfgistfrfd PropfrtyCibngfListfnfrs.
     * No fvfnt will bf dflivfrfd if oldVbluf bnd nfwVbluf brf tif sbmf.
     *
     * @pbrbm propfrtyNbmf tif nbmf of tif propfrty tibt ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf,
                                      Objfdt nfwVbluf)
    {
        if (oldVbluf == nfwVbluf) {
            rfturn;
        }
        PropfrtyCibngfSupport dibngfSupport = tiis.dibngfSupport;
        if (dibngfSupport != null) {
            dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        }
    }

    /**
     * Adds b VftobblfCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll vftobblf propfrtifs of tiis dlbss, indluding tif
     * following:
     * <ul>
     *    <li>tif fodus ownfr ("fodusOwnfr")</li>
     *    <li>tif pfrmbnfnt fodus ownfr ("pfrmbnfntFodusOwnfr")</li>
     *    <li>tif fodusfd Window ("fodusfdWindow")</li>
     *    <li>tif bdtivf Window ("bdtivfWindow")</li>
     * </ul>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif VftobblfCibngfListfnfr to bf bddfd
     * @sff #rfmovfVftobblfCibngfListfnfr
     * @sff #gftVftobblfCibngfListfnfrs
     * @sff #bddVftobblfCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.VftobblfCibngfListfnfr)
     */
    publid void bddVftobblfCibngfListfnfr(VftobblfCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (vftobblfSupport == null) {
                    vftobblfSupport =
                        nfw VftobblfCibngfSupport(tiis);
                }
                vftobblfSupport.bddVftobblfCibngfListfnfr(listfnfr);
            }
        }
    }

    /**
     * Rfmovfs b VftobblfCibngfListfnfr from tif listfnfr list. Tiis mftiod
     * siould bf usfd to rfmovf tif VftobblfCibngfListfnfrs tibt wfrf
     * rfgistfrfd for bll vftobblf propfrtifs of tiis dlbss.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif VftobblfCibngfListfnfr to bf rfmovfd
     * @sff #bddVftobblfCibngfListfnfr
     * @sff #gftVftobblfCibngfListfnfrs
     * @sff #rfmovfVftobblfCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.VftobblfCibngfListfnfr)
     */
    publid void rfmovfVftobblfCibngfListfnfr(VftobblfCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (vftobblfSupport != null) {
                    vftobblfSupport.rfmovfVftobblfCibngfListfnfr(listfnfr);
                }
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif vftobblf dibngf listfnfrs
     * rfgistfrfd on tiis kfybobrd fodus mbnbgfr.
     *
     * @rfturn bll of tiis kfybobrd fodus mbnbgfr's
     *         <dodf>VftobblfCibngfListfnfr</dodf>s
     *         or bn fmpty brrby if no vftobblf dibngf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddVftobblfCibngfListfnfr
     * @sff #rfmovfVftobblfCibngfListfnfr
     * @sff #gftVftobblfCibngfListfnfrs(jbvb.lbng.String)
     * @sindf 1.4
     */
    publid syndironizfd VftobblfCibngfListfnfr[] gftVftobblfCibngfListfnfrs() {
        if (vftobblfSupport == null) {
            vftobblfSupport = nfw VftobblfCibngfSupport(tiis);
        }
        rfturn vftobblfSupport.gftVftobblfCibngfListfnfrs();
    }

    /**
     * Adds b VftobblfCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf usfr-dffinfd, or onf of tif
     * following:
     * <ul>
     *    <li>tif fodus ownfr ("fodusOwnfr")</li>
     *    <li>tif pfrmbnfnt fodus ownfr ("pfrmbnfntFodusOwnfr")</li>
     *    <li>tif fodusfd Window ("fodusfdWindow")</li>
     *    <li>tif bdtivf Window ("bdtivfWindow")</li>
     * </ul>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif VftobblfCibngfListfnfr to bf bddfd
     * @sff #bddVftobblfCibngfListfnfr(jbvb.bfbns.VftobblfCibngfListfnfr)
     * @sff #rfmovfVftobblfCibngfListfnfr
     * @sff #gftVftobblfCibngfListfnfrs
     */
    publid void bddVftobblfCibngfListfnfr(String propfrtyNbmf,
                                          VftobblfCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (vftobblfSupport == null) {
                    vftobblfSupport =
                        nfw VftobblfCibngfSupport(tiis);
                }
                vftobblfSupport.bddVftobblfCibngfListfnfr(propfrtyNbmf,
                                                          listfnfr);
            }
        }
    }

    /**
     * Rfmovfs b VftobblfCibngfListfnfr from tif listfnfr list for b spfdifid
     * propfrty. Tiis mftiod siould bf usfd to rfmovf VftobblfCibngfListfnfrs
     * tibt wfrf rfgistfrfd for b spfdifid bound propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf b vblid propfrty nbmf
     * @pbrbm listfnfr tif VftobblfCibngfListfnfr to bf rfmovfd
     * @sff #bddVftobblfCibngfListfnfr
     * @sff #gftVftobblfCibngfListfnfrs
     * @sff #rfmovfVftobblfCibngfListfnfr(jbvb.bfbns.VftobblfCibngfListfnfr)
     */
    publid void rfmovfVftobblfCibngfListfnfr(String propfrtyNbmf,
                                             VftobblfCibngfListfnfr listfnfr) {
        if (listfnfr != null) {
            syndironizfd (tiis) {
                if (vftobblfSupport != null) {
                    vftobblfSupport.rfmovfVftobblfCibngfListfnfr(propfrtyNbmf,
                                                                 listfnfr);
                }
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>VftobblfCibngfListfnfr</dodf>s
     * bssodibtfd witi tif nbmfd propfrty.
     *
     * @pbrbm  propfrtyNbmf tif propfrty nbmf
     * @rfturn bll of tif <dodf>VftobblfCibngfListfnfr</dodf>s bssodibtfd witi
     *         tif nbmfd propfrty or bn fmpty brrby if no sudi listfnfrs ibvf
     *         bffn bddfd.
     *
     * @sff #bddVftobblfCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.VftobblfCibngfListfnfr)
     * @sff #rfmovfVftobblfCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.VftobblfCibngfListfnfr)
     * @sff #gftVftobblfCibngfListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd VftobblfCibngfListfnfr[] gftVftobblfCibngfListfnfrs(String propfrtyNbmf) {
        if (vftobblfSupport == null) {
            vftobblfSupport = nfw VftobblfCibngfSupport(tiis);
        }
        rfturn vftobblfSupport.gftVftobblfCibngfListfnfrs(propfrtyNbmf);
    }

    /**
     * Firfs b PropfrtyCibngfEvfnt in rfsponsf to b dibngf in b vftobblf
     * propfrty. Tif fvfnt will bf dflivfrfd to bll rfgistfrfd
     * VftobblfCibngfListfnfrs. If b VftobblfCibngfListfnfr tirows b
     * PropfrtyVftoExdfption, b nfw fvfnt is firfd rfvfrting bll
     * VftobblfCibngfListfnfrs to tif old vbluf bnd tif fxdfption is tifn
     * rftirown. No fvfnt will bf dflivfrfd if oldVbluf bnd nfwVbluf brf tif
     * sbmf.
     *
     * @pbrbm propfrtyNbmf tif nbmf of tif propfrty tibt ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     * @tirows jbvb.bfbns.PropfrtyVftoExdfption if b
     *         <dodf>VftobblfCibngfListfnfr</dodf> tirfw
     *         <dodf>PropfrtyVftoExdfption</dodf>
     */
    protfdtfd void firfVftobblfCibngf(String propfrtyNbmf, Objfdt oldVbluf,
                                      Objfdt nfwVbluf)
        tirows PropfrtyVftoExdfption
    {
        if (oldVbluf == nfwVbluf) {
            rfturn;
        }
        VftobblfCibngfSupport vftobblfSupport =
            tiis.vftobblfSupport;
        if (vftobblfSupport != null) {
            vftobblfSupport.firfVftobblfCibngf(propfrtyNbmf, oldVbluf,
                                               nfwVbluf);
        }
    }

    /**
     * Adds b KfyEvfntDispbtdifr to tiis KfybobrdFodusMbnbgfr's dispbtdifr
     * dibin. Tiis KfybobrdFodusMbnbgfr will rfqufst tibt fbdi
     * KfyEvfntDispbtdifr dispbtdi KfyEvfnts gfnfrbtfd by tif usfr bfforf
     * finblly dispbtdiing tif KfyEvfnt itsflf. KfyEvfntDispbtdifrs will bf
     * notififd in tif ordfr in wiidi tify wfrf bddfd. Notifidbtions will iblt
     * bs soon bs onf KfyEvfntDispbtdifr rfturns <dodf>truf</dodf> from its
     * <dodf>dispbtdiKfyEvfnt</dodf> mftiod. Tifrf is no limit to tif totbl
     * numbfr of KfyEvfntDispbtdifrs wiidi dbn bf bddfd, nor to tif numbfr of
     * timfs wiidi b pbrtidulbr KfyEvfntDispbtdifr instbndf dbn bf bddfd.
     * <p>
     * If b null dispbtdifr is spfdififd, no bdtion is tbkfn bnd no fxdfption
     * is tirown.
     * <p>
     * In b multitirfbdfd bpplidbtion, {@link KfyEvfntDispbtdifr} bfibvfs
     * tif sbmf bs otifr AWT listfnfrs.  Sff
     * <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for morf dftbils.
     *
     * @pbrbm dispbtdifr tif KfyEvfntDispbtdifr to bdd to tif dispbtdifr dibin
     * @sff #rfmovfKfyEvfntDispbtdifr
     */
    publid void bddKfyEvfntDispbtdifr(KfyEvfntDispbtdifr dispbtdifr) {
        if (dispbtdifr != null) {
            syndironizfd (tiis) {
                if (kfyEvfntDispbtdifrs == null) {
                    kfyEvfntDispbtdifrs = nfw jbvb.util.LinkfdList<>();
                }
                kfyEvfntDispbtdifrs.bdd(dispbtdifr);
            }
        }
    }

    /**
     * Rfmovfs b KfyEvfntDispbtdifr wiidi wbs prfviously bddfd to tiis
     * KfybobrdFodusMbnbgfr's dispbtdifr dibin. Tiis KfybobrdFodusMbnbgfr
     * dbnnot itsflf bf rfmovfd, unlfss it wbs fxpliditly rf-rfgistfrfd vib b
     * dbll to <dodf>bddKfyEvfntDispbtdifr</dodf>.
     * <p>
     * If b null dispbtdifr is spfdififd, if tif spfdififd dispbtdifr is not
     * in tif dispbtdifr dibin, or if tiis KfybobrdFodusMbnbgfr is spfdififd
     * witiout ibving bffn fxpliditly rf-rfgistfrfd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     * <p>
     * In b multitirfbdfd bpplidbtion, {@link KfyEvfntDispbtdifr} bfibvfs
     * tif sbmf bs otifr AWT listfnfrs.  Sff
     * <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for morf dftbils.
     *
     * @pbrbm dispbtdifr tif KfyEvfntDispbtdifr to rfmovf from tif dispbtdifr
     *        dibin
     * @sff #bddKfyEvfntDispbtdifr
     */
    publid void rfmovfKfyEvfntDispbtdifr(KfyEvfntDispbtdifr dispbtdifr) {
        if (dispbtdifr != null) {
            syndironizfd (tiis) {
                if (kfyEvfntDispbtdifrs != null) {
                    kfyEvfntDispbtdifrs.rfmovf(dispbtdifr);
                }
            }
        }
    }

    /**
     * Rfturns tiis KfybobrdFodusMbnbgfr's KfyEvfntDispbtdifr dibin bs b List.
     * Tif List will not indludf tiis KfybobrdFodusMbnbgfr unlfss it wbs
     * fxpliditly rf-rfgistfrfd vib b dbll to
     * <dodf>bddKfyEvfntDispbtdifr</dodf>. If no otifr KfyEvfntDispbtdifrs brf
     * rfgistfrfd, implfmfntbtions brf frff to rfturn null or b List of lfngti
     * 0. Clifnt dodf siould not bssumf onf bfibvior ovfr bnotifr, nor siould
     * it bssumf tibt tif bfibvior, ondf fstbblisifd, will not dibngf.
     *
     * @rfturn b possibly null or fmpty List of KfyEvfntDispbtdifrs
     * @sff #bddKfyEvfntDispbtdifr
     * @sff #rfmovfKfyEvfntDispbtdifr
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    protfdtfd syndironizfd jbvb.util.List<KfyEvfntDispbtdifr>
        gftKfyEvfntDispbtdifrs()
    {
        rfturn (kfyEvfntDispbtdifrs != null)
            ? (jbvb.util.List<KfyEvfntDispbtdifr>)kfyEvfntDispbtdifrs.dlonf()
            : null;
    }

    /**
     * Adds b KfyEvfntPostProdfssor to tiis KfybobrdFodusMbnbgfr's post-
     * prodfssor dibin. Aftfr b KfyEvfnt ibs bffn dispbtdifd to bnd ibndlfd by
     * its tbrgft, KfybobrdFodusMbnbgfr will rfqufst tibt fbdi
     * KfyEvfntPostProdfssor pfrform bny nfdfssbry post-prodfssing bs pbrt
     * of tif KfyEvfnt's finbl rfsolution. KfyEvfntPostProdfssors
     * will bf notififd in tif ordfr in wiidi tify wfrf bddfd; tif durrfnt
     * KfybobrdFodusMbnbgfr will bf notififd lbst. Notifidbtions will iblt
     * bs soon bs onf KfyEvfntPostProdfssor rfturns <dodf>truf</dodf> from its
     * <dodf>postProdfssKfyEvfnt</dodf> mftiod. Tifrf is no limit to tif tif
     * totbl numbfr of KfyEvfntPostProdfssors tibt dbn bf bddfd, nor to tif
     * numbfr of timfs tibt b pbrtidulbr KfyEvfntPostProdfssor instbndf dbn bf
     * bddfd.
     * <p>
     * If b null post-prodfssor is spfdififd, no bdtion is tbkfn bnd no
     * fxdfption is tirown.
     * <p>
     * In b multitirfbdfd bpplidbtion, {@link KfyEvfntPostProdfssor} bfibvfs
     * tif sbmf bs otifr AWT listfnfrs.  Sff
     * <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for morf dftbils.
     *
     * @pbrbm prodfssor tif KfyEvfntPostProdfssor to bdd to tif post-prodfssor
     *        dibin
     * @sff #rfmovfKfyEvfntPostProdfssor
     */
    publid void bddKfyEvfntPostProdfssor(KfyEvfntPostProdfssor prodfssor) {
        if (prodfssor != null) {
            syndironizfd (tiis) {
                if (kfyEvfntPostProdfssors == null) {
                    kfyEvfntPostProdfssors = nfw jbvb.util.LinkfdList<>();
                }
                kfyEvfntPostProdfssors.bdd(prodfssor);
            }
        }
    }


    /**
     * Rfmovfs b prfviously bddfd KfyEvfntPostProdfssor from tiis
     * KfybobrdFodusMbnbgfr's post-prodfssor dibin. Tiis KfybobrdFodusMbnbgfr
     * dbnnot itsflf bf fntirfly rfmovfd from tif dibin. Only bdditionbl
     * rfffrfndfs bddfd vib <dodf>bddKfyEvfntPostProdfssor</dodf> dbn bf
     * rfmovfd.
     * <p>
     * If b null post-prodfssor is spfdififd, if tif spfdififd post-prodfssor
     * is not in tif post-prodfssor dibin, or if tiis KfybobrdFodusMbnbgfr is
     * spfdififd witiout ibving bffn fxpliditly bddfd, no bdtion is tbkfn bnd
     * no fxdfption is tirown.
     * <p>
     * In b multitirfbdfd bpplidbtion, {@link KfyEvfntPostProdfssor} bfibvfs
     * tif sbmf bs otifr AWT listfnfrs.  Sff
     * <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for morf dftbils.
     *
     * @pbrbm prodfssor tif KfyEvfntPostProdfssor to rfmovf from tif post-
     *        prodfssor dibin
     * @sff #bddKfyEvfntPostProdfssor
     */
    publid void rfmovfKfyEvfntPostProdfssor(KfyEvfntPostProdfssor prodfssor) {
        if (prodfssor != null) {
            syndironizfd (tiis) {
                if (kfyEvfntPostProdfssors != null) {
                    kfyEvfntPostProdfssors.rfmovf(prodfssor);
                }
            }
        }
    }


    /**
     * Rfturns tiis KfybobrdFodusMbnbgfr's KfyEvfntPostProdfssor dibin bs b
     * List. Tif List will not indludf tiis KfybobrdFodusMbnbgfr unlfss it wbs
     * fxpliditly bddfd vib b dbll to <dodf>bddKfyEvfntPostProdfssor</dodf>. If
     * no KfyEvfntPostProdfssors brf rfgistfrfd, implfmfntbtions brf frff to
     * rfturn null or b List of lfngti 0. Clifnt dodf siould not bssumf onf
     * bfibvior ovfr bnotifr, nor siould it bssumf tibt tif bfibvior, ondf
     * fstbblisifd, will not dibngf.
     *
     * @rfturn b possibly null or fmpty List of KfyEvfntPostProdfssors
     * @sff #bddKfyEvfntPostProdfssor
     * @sff #rfmovfKfyEvfntPostProdfssor
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    protfdtfd jbvb.util.List<KfyEvfntPostProdfssor>
        gftKfyEvfntPostProdfssors()
    {
        rfturn (kfyEvfntPostProdfssors != null)
            ? (jbvb.util.List<KfyEvfntPostProdfssor>)kfyEvfntPostProdfssors.dlonf()
            : null;
    }



    stbtid void sftMostRfdfntFodusOwnfr(Componfnt domponfnt) {
        Componfnt window = domponfnt;
        wiilf (window != null && !(window instbndfof Window)) {
            window = window.pbrfnt;
        }
        if (window != null) {
            sftMostRfdfntFodusOwnfr((Window)window, domponfnt);
        }
    }
    stbtid syndironizfd void sftMostRfdfntFodusOwnfr(Window window,
                                                     Componfnt domponfnt) {
        // ATTN: domponfnt ibs b strong rfffrfndf to window vib dibin
        // of Componfnt.pbrfnt fiflds.  Sindf WfbkHbsMbp rfffrs to its
        // vblufs strongly, wf nffd to brfbk tif strong link from tif
        // vbluf (domponfnt) bbdk to its kfy (window).
        WfbkRfffrfndf<Componfnt> wfbkVbluf = null;
        if (domponfnt != null) {
            wfbkVbluf = nfw WfbkRfffrfndf<>(domponfnt);
        }
        mostRfdfntFodusOwnfrs.put(window, wfbkVbluf);
    }
    stbtid void dlfbrMostRfdfntFodusOwnfr(Componfnt domp) {
        Contbinfr window;

        if (domp == null) {
            rfturn;
        }

        syndironizfd (domp.gftTrffLodk()) {
            window = domp.gftPbrfnt();
            wiilf (window != null && !(window instbndfof Window)) {
                window = window.gftPbrfnt();
            }
        }

        syndironizfd (KfybobrdFodusMbnbgfr.dlbss) {
            if ((window != null)
                && (gftMostRfdfntFodusOwnfr((Window)window) == domp))
            {
                sftMostRfdfntFodusOwnfr((Window)window, null);
            }
            // Also dlfbr tfmporbry lost domponfnt storfd in Window
            if (window != null) {
                Window rfblWindow = (Window)window;
                if (rfblWindow.gftTfmporbryLostComponfnt() == domp) {
                    rfblWindow.sftTfmporbryLostComponfnt(null);
                }
            }
        }
    }

    /*
     * Plfbsf bf dbrfful dibnging tiis mftiod! It is dbllfd from
     * jbvbx.swing.JComponfnt.runInputVfrififr() using rfflfdtion.
     */
    stbtid syndironizfd Componfnt gftMostRfdfntFodusOwnfr(Window window) {
        WfbkRfffrfndf<Componfnt> wfbkVbluf = mostRfdfntFodusOwnfrs.gft(window);
        rfturn wfbkVbluf == null ? null : wfbkVbluf.gft();
    }

    /**
     * Tiis mftiod is dbllfd by tif AWT fvfnt dispbtdifr rfqufsting tibt tif
     * durrfnt KfybobrdFodusMbnbgfr dispbtdi tif spfdififd fvfnt on its bfiblf.
     * It is fxpfdtfd tibt bll KfybobrdFodusMbnbgfrs will dispbtdi bll
     * FodusEvfnts, bll WindowEvfnts rflbtfd to fodus, bnd bll KfyEvfnts.
     * Tifsf fvfnts siould bf dispbtdifd bbsfd on tif KfybobrdFodusMbnbgfr's
     * notion of tif fodus ownfr bnd tif fodusfd bnd bdtivf Windows, somftimfs
     * ovfrriding tif sourdf of tif spfdififd AWTEvfnt. Dispbtdiing must bf
     * donf using <dodf>rfdispbtdiEvfnt</dodf> to prfvfnt tif AWT fvfnt
     * dispbtdifr from rfdursivfly rfqufsting tibt tif KfybobrdFodusMbnbgfr
     * dispbtdi tif fvfnt bgbin. If tiis mftiod rfturns <dodf>fblsf</dodf>,
     * tifn tif AWT fvfnt dispbtdifr will bttfmpt to dispbtdi tif fvfnt itsflf.
     *
     * @pbrbm f tif AWTEvfnt to bf dispbtdifd
     * @rfturn <dodf>truf</dodf> if tiis mftiod dispbtdifd tif fvfnt;
     *         <dodf>fblsf</dodf> otifrwisf
     * @sff #rfdispbtdiEvfnt
     * @sff #dispbtdiKfyEvfnt
     */
    publid bbstrbdt boolfbn dispbtdiEvfnt(AWTEvfnt f);

    /**
     * Rfdispbtdifs bn AWTEvfnt in sudi b wby tibt tif AWT fvfnt dispbtdifr
     * will not rfdursivfly rfqufst tibt tif KfybobrdFodusMbnbgfr, or bny
     * instbllfd KfyEvfntDispbtdifrs, dispbtdi tif fvfnt bgbin. Clifnt
     * implfmfntbtions of <dodf>dispbtdiEvfnt</dodf> bnd dlifnt-dffinfd
     * KfyEvfntDispbtdifrs must dbll <dodf>rfdispbtdiEvfnt(tbrgft, f)</dodf>
     * instfbd of <dodf>tbrgft.dispbtdiEvfnt(f)</dodf> to dispbtdi bn fvfnt.
     * <p>
     * Tiis mftiod is intfndfd to bf usfd only by KfybobrdFodusMbnbgfrs bnd
     * KfyEvfntDispbtdifrs. It is not for gfnfrbl dlifnt usf.
     *
     * @pbrbm tbrgft tif Componfnt to wiidi tif fvfnt siould bf dispbtdifd
     * @pbrbm f tif fvfnt to dispbtdi
     * @sff #dispbtdiEvfnt
     * @sff KfyEvfntDispbtdifr
     */
    publid finbl void rfdispbtdiEvfnt(Componfnt tbrgft, AWTEvfnt f) {
        f.fodusMbnbgfrIsDispbtdiing = truf;
        tbrgft.dispbtdiEvfnt(f);
        f.fodusMbnbgfrIsDispbtdiing = fblsf;
    }

    /**
     * Typidblly tiis mftiod will bf dbllfd by <dodf>dispbtdiEvfnt</dodf> if no
     * otifr KfyEvfntDispbtdifr in tif dispbtdifr dibin dispbtdifd tif
     * KfyEvfnt, or if no otifr KfyEvfntDispbtdifrs brf rfgistfrfd. If bn
     * implfmfntbtion of tiis mftiod rfturns <dodf>fblsf</dodf>,
     * <dodf>dispbtdiEvfnt</dodf> mby try to dispbtdi tif KfyEvfnt itsflf, or
     * mby simply rfturn <dodf>fblsf</dodf>. If <dodf>truf</dodf> is rfturnfd,
     * <dodf>dispbtdiEvfnt</dodf> siould rfturn <dodf>truf</dodf> bs wfll.
     *
     * @pbrbm f tif KfyEvfnt wiidi tif durrfnt KfybobrdFodusMbnbgfr ibs
     *        rfqufstfd tibt tiis KfyEvfntDispbtdifr dispbtdi
     * @rfturn <dodf>truf</dodf> if tif KfyEvfnt wbs dispbtdifd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @sff #dispbtdiEvfnt
     */
    publid bbstrbdt boolfbn dispbtdiKfyEvfnt(KfyEvfnt f);

    /**
     * Tiis mftiod will bf dbllfd by <dodf>dispbtdiKfyEvfnt</dodf>.
     * By dffbult, tiis mftiod will ibndlf bny undonsumfd KfyEvfnts tibt
     * mbp to bn AWT <dodf>MfnuSiortdut</dodf> by donsuming tif fvfnt
     * bnd bdtivbting tif siortdut.
     *
     * @pbrbm f tif KfyEvfnt to post-prodfss
     * @rfturn <dodf>truf</dodf> to indidbtf tibt no otifr
     *         KfyEvfntPostProdfssor will bf notififd of tif KfyEvfnt.
     * @sff #dispbtdiKfyEvfnt
     * @sff MfnuSiortdut
     */
    publid bbstrbdt boolfbn postProdfssKfyEvfnt(KfyEvfnt f);

    /**
     * Tiis mftiod initibtfs b fodus trbvfrsbl opfrbtion if bnd only if tif
     * KfyEvfnt rfprfsfnts b fodus trbvfrsbl kfy for tif spfdififd
     * fodusfdComponfnt. It is fxpfdtfd tibt fodusfdComponfnt is tif durrfnt
     * fodus ownfr, bltiougi tiis nffd not bf tif dbsf. If it is not,
     * fodus trbvfrsbl will nfvfrtiflfss prodffd bs if fodusfdComponfnt
     * wfrf tif durrfnt fodus ownfr.
     *
     * @pbrbm fodusfdComponfnt tif Componfnt tibt will bf tif bbsis for b fodus
     *        trbvfrsbl opfrbtion if tif spfdififd fvfnt rfprfsfnts b fodus
     *        trbvfrsbl kfy for tif Componfnt
     * @pbrbm f tif fvfnt tibt mby rfprfsfnt b fodus trbvfrsbl kfy
     */
    publid bbstrbdt void prodfssKfyEvfnt(Componfnt fodusfdComponfnt,
                                         KfyEvfnt f);

    /**
     * Cbllfd by tif AWT to notify tif KfybobrdFodusMbnbgfr tibt it siould
     * dflby dispbtdiing of KfyEvfnts until tif spfdififd Componfnt bfdomfs
     * tif fodus ownfr. If dlifnt dodf rfqufsts b fodus dibngf, bnd tif AWT
     * dftfrminfs tibt tiis rfqufst migit bf grbntfd by tif nbtivf windowing
     * systfm, tifn tif AWT will dbll tiis mftiod. It is tif rfsponsibility of
     * tif KfybobrdFodusMbnbgfr to dflby dispbtdiing of KfyEvfnts witi
     * timfstbmps lbtfr tibn tif spfdififd timf stbmp until tif spfdififd
     * Componfnt rfdfivfs b FOCUS_GAINED fvfnt, or tif AWT dbndfls tif dflby
     * rfqufst by invoking <dodf>dfqufufKfyEvfnts</dodf> or
     * <dodf>disdbrdKfyEvfnts</dodf>.
     *
     * @pbrbm bftfr timfstbmp of durrfnt fvfnt, or tif durrfnt, systfm timf if
     *        tif durrfnt fvfnt ibs no timfstbmp, or tif AWT dbnnot dftfrminf
     *        wiidi fvfnt is durrfntly bfing ibndlfd
     * @pbrbm untilFodusfd Componfnt wiidi siould rfdfivf b FOCUS_GAINED fvfnt
     *        bfforf bny pfnding KfyEvfnts
     * @sff #dfqufufKfyEvfnts
     * @sff #disdbrdKfyEvfnts
     */
    protfdtfd bbstrbdt void fnqufufKfyEvfnts(long bftfr,
                                             Componfnt untilFodusfd);

    /**
     * Cbllfd by tif AWT to notify tif KfybobrdFodusMbnbgfr tibt it siould
     * dbndfl dflbyfd dispbtdiing of KfyEvfnts. All KfyEvfnts wiidi wfrf
     * fnqufufd bfdbusf of b dbll to <dodf>fnqufufKfyEvfnts</dodf> witi tif
     * sbmf timfstbmp bnd Componfnt siould bf rflfbsfd for normbl dispbtdiing
     * to tif durrfnt fodus ownfr. If tif givfn timfstbmp is lfss tibn zfro,
     * tif outstbnding fnqufuf rfqufst for tif givfn Componfnt witi tif <b>
     * oldfst</b> timfstbmp (if bny) siould bf dbndfllfd.
     *
     * @pbrbm bftfr tif timfstbmp spfdififd in tif dbll to
     *        <dodf>fnqufufKfyEvfnts</dodf>, or bny vbluf &lt; 0
     * @pbrbm untilFodusfd tif Componfnt spfdififd in tif dbll to
     *        <dodf>fnqufufKfyEvfnts</dodf>
     * @sff #fnqufufKfyEvfnts
     * @sff #disdbrdKfyEvfnts
     */
    protfdtfd bbstrbdt void dfqufufKfyEvfnts(long bftfr,
                                             Componfnt untilFodusfd);

    /**
     * Cbllfd by tif AWT to notify tif KfybobrdFodusMbnbgfr tibt it siould
     * dbndfl dflbyfd dispbtdiing of KfyEvfnts. All KfyEvfnts wiidi wfrf
     * fnqufufd bfdbusf of onf or morf dblls to <dodf>fnqufufKfyEvfnts</dodf>
     * witi tif sbmf Componfnt siould bf disdbrdfd.
     *
     * @pbrbm domp tif Componfnt spfdififd in onf or morf dblls to
     *        <dodf>fnqufufKfyEvfnts</dodf>
     * @sff #fnqufufKfyEvfnts
     * @sff #dfqufufKfyEvfnts
     */
    protfdtfd bbstrbdt void disdbrdKfyEvfnts(Componfnt domp);

    /**
     * Fodusfs tif Componfnt bftfr bComponfnt, typidblly bbsfd on b
     * FodusTrbvfrsblPolidy.
     *
     * @pbrbm bComponfnt tif Componfnt tibt is tif bbsis for tif fodus
     *        trbvfrsbl opfrbtion
     * @sff FodusTrbvfrsblPolidy
     */
    publid bbstrbdt void fodusNfxtComponfnt(Componfnt bComponfnt);

    /**
     * Fodusfs tif Componfnt bfforf bComponfnt, typidblly bbsfd on b
     * FodusTrbvfrsblPolidy.
     *
     * @pbrbm bComponfnt tif Componfnt tibt is tif bbsis for tif fodus
     *        trbvfrsbl opfrbtion
     * @sff FodusTrbvfrsblPolidy
     */
    publid bbstrbdt void fodusPrfviousComponfnt(Componfnt bComponfnt);

    /**
     * Movfs tif fodus up onf fodus trbvfrsbl dydlf. Typidblly, tif fodus ownfr
     * is sft to bComponfnt's fodus dydlf root, bnd tif durrfnt fodus dydlf
     * root is sft to tif nfw fodus ownfr's fodus dydlf root. If, iowfvfr,
     * bComponfnt's fodus dydlf root is b Window, tifn typidblly tif fodus
     * ownfr is sft to tif Window's dffbult Componfnt to fodus, bnd tif durrfnt
     * fodus dydlf root is undibngfd.
     *
     * @pbrbm bComponfnt tif Componfnt tibt is tif bbsis for tif fodus
     *        trbvfrsbl opfrbtion
     */
    publid bbstrbdt void upFodusCydlf(Componfnt bComponfnt);

    /**
     * Movfs tif fodus down onf fodus trbvfrsbl dydlf. Typidblly, if
     * bContbinfr is b fodus dydlf root, tifn tif fodus ownfr is sft to
     * bContbinfr's dffbult Componfnt to fodus, bnd tif durrfnt fodus dydlf
     * root is sft to bContbinfr. If bContbinfr is not b fodus dydlf root, tifn
     * no fodus trbvfrsbl opfrbtion oddurs.
     *
     * @pbrbm bContbinfr tif Contbinfr tibt is tif bbsis for tif fodus
     *        trbvfrsbl opfrbtion
     */
    publid bbstrbdt void downFodusCydlf(Contbinfr bContbinfr);

    /**
     * Fodusfs tif Componfnt bftfr tif durrfnt fodus ownfr.
     */
    publid finbl void fodusNfxtComponfnt() {
        Componfnt fodusOwnfr = gftFodusOwnfr();
        if (fodusOwnfr != null) {
            fodusNfxtComponfnt(fodusOwnfr);
        }
    }

    /**
     * Fodusfs tif Componfnt bfforf tif durrfnt fodus ownfr.
     */
    publid finbl void fodusPrfviousComponfnt() {
        Componfnt fodusOwnfr = gftFodusOwnfr();
        if (fodusOwnfr != null) {
            fodusPrfviousComponfnt(fodusOwnfr);
        }
    }

    /**
     * Movfs tif fodus up onf fodus trbvfrsbl dydlf from tif durrfnt fodus
     * ownfr. Typidblly, tif nfw fodus ownfr is sft to tif durrfnt fodus
     * ownfr's fodus dydlf root, bnd tif durrfnt fodus dydlf root is sft to tif
     * nfw fodus ownfr's fodus dydlf root. If, iowfvfr, tif durrfnt fodus
     * ownfr's fodus dydlf root is b Window, tifn typidblly tif fodus ownfr is
     * sft to tif fodus dydlf root's dffbult Componfnt to fodus, bnd tif
     * durrfnt fodus dydlf root is undibngfd.
     */
    publid finbl void upFodusCydlf() {
        Componfnt fodusOwnfr = gftFodusOwnfr();
        if (fodusOwnfr != null) {
            upFodusCydlf(fodusOwnfr);
        }
    }

    /**
     * Movfs tif fodus down onf fodus trbvfrsbl dydlf from tif durrfnt fodus
     * ownfr, if bnd only if tif durrfnt fodus ownfr is b Contbinfr tibt is b
     * fodus dydlf root. Typidblly, tif fodus ownfr is sft to tif durrfnt fodus
     * ownfr's dffbult Componfnt to fodus, bnd tif durrfnt fodus dydlf root is
     * sft to tif durrfnt fodus ownfr. If tif durrfnt fodus ownfr is not b
     * Contbinfr tibt is b fodus dydlf root, tifn no fodus trbvfrsbl opfrbtion
     * oddurs.
     */
    publid finbl void downFodusCydlf() {
        Componfnt fodusOwnfr = gftFodusOwnfr();
        if (fodusOwnfr instbndfof Contbinfr) {
            downFodusCydlf((Contbinfr)fodusOwnfr);
        }
    }

    /**
     * Dumps tif list of fodus rfqufsts to stdfrr
     */
    void dumpRfqufsts() {
        Systfm.frr.println(">>> Rfqufsts dump, timf: " + Systfm.durrfntTimfMillis());
        syndironizfd (ifbvywfigitRfqufsts) {
            for (HfbvywfigitFodusRfqufst rfq : ifbvywfigitRfqufsts) {
                Systfm.frr.println(">>> Rfq: " + rfq);
            }
        }
        Systfm.frr.println("");
    }

    privbtf stbtid finbl dlbss LigitwfigitFodusRfqufst {
        finbl Componfnt domponfnt;
        finbl boolfbn tfmporbry;
        finbl CbusfdFodusEvfnt.Cbusf dbusf;

        LigitwfigitFodusRfqufst(Componfnt domponfnt, boolfbn tfmporbry, CbusfdFodusEvfnt.Cbusf dbusf) {
            tiis.domponfnt = domponfnt;
            tiis.tfmporbry = tfmporbry;
            tiis.dbusf = dbusf;
        }
        publid String toString() {
            rfturn "LigitwfigitFodusRfqufst[domponfnt=" + domponfnt +
                ",tfmporbry=" + tfmporbry + ", dbusf=" + dbusf + "]";
        }
    }

    privbtf stbtid finbl dlbss HfbvywfigitFodusRfqufst {
        finbl Componfnt ifbvywfigit;
        finbl LinkfdList<LigitwfigitFodusRfqufst> ligitwfigitRfqufsts;

        stbtid finbl HfbvywfigitFodusRfqufst CLEAR_GLOBAL_FOCUS_OWNER =
            nfw HfbvywfigitFodusRfqufst();

        privbtf HfbvywfigitFodusRfqufst() {
            ifbvywfigit = null;
            ligitwfigitRfqufsts = null;
        }

        HfbvywfigitFodusRfqufst(Componfnt ifbvywfigit, Componfnt dfsdfndbnt,
                                boolfbn tfmporbry, CbusfdFodusEvfnt.Cbusf dbusf) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                if (ifbvywfigit == null) {
                    log.finf("Assfrtion (ifbvywfigit != null) fbilfd");
                }
            }

            tiis.ifbvywfigit = ifbvywfigit;
            tiis.ligitwfigitRfqufsts = nfw LinkfdList<LigitwfigitFodusRfqufst>();
            bddLigitwfigitRfqufst(dfsdfndbnt, tfmporbry, dbusf);
        }
        boolfbn bddLigitwfigitRfqufst(Componfnt dfsdfndbnt,
                                      boolfbn tfmporbry, CbusfdFodusEvfnt.Cbusf dbusf) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                if (tiis == HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER) {
                    log.finf("Assfrtion (tiis != HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER) fbilfd");
                }
                if (dfsdfndbnt == null) {
                    log.finf("Assfrtion (dfsdfndbnt != null) fbilfd");
                }
            }

            Componfnt lbstDfsdfndbnt = ((ligitwfigitRfqufsts.sizf() > 0)
                ? ligitwfigitRfqufsts.gftLbst().domponfnt
                : null);

            if (dfsdfndbnt != lbstDfsdfndbnt) {
                // Not b duplidbtf rfqufst
                ligitwfigitRfqufsts.bdd
                    (nfw LigitwfigitFodusRfqufst(dfsdfndbnt, tfmporbry, dbusf));
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }

        LigitwfigitFodusRfqufst gftFirstLigitwfigitRfqufst() {
            if (tiis == CLEAR_GLOBAL_FOCUS_OWNER) {
                rfturn null;
            }
            rfturn ligitwfigitRfqufsts.gftFirst();
        }
        publid String toString() {
            boolfbn first = truf;
            String str = "HfbvywfigitFodusRfqufst[ifbvwfigit=" + ifbvywfigit +
                ",ligitwfigitRfqufsts=";
            if (ligitwfigitRfqufsts == null) {
                str += null;
            } flsf {
                str += "[";

                for (LigitwfigitFodusRfqufst lwRfqufst : ligitwfigitRfqufsts) {
                    if (first) {
                        first = fblsf;
                    } flsf {
                        str += ",";
                    }
                    str += lwRfqufst;
                }
                str += "]";
            }
            str += "]";
            rfturn str;
        }
    }

    /*
     * ifbvywfigitRfqufsts is usfd bs b monitor for syndironizfd dibngfs of
     * durrfntLigitwfigitRfqufsts, dlfbringCurrfntLigitwfigitRfqufsts bnd
     * nfwFodusOwnfr.
     */
    privbtf stbtid LinkfdList<HfbvywfigitFodusRfqufst> ifbvywfigitRfqufsts =
        nfw LinkfdList<HfbvywfigitFodusRfqufst>();
    privbtf stbtid LinkfdList<LigitwfigitFodusRfqufst> durrfntLigitwfigitRfqufsts;
    privbtf stbtid boolfbn dlfbringCurrfntLigitwfigitRfqufsts;
    privbtf stbtid boolfbn bllowSyndFodusRfqufsts = truf;
    privbtf stbtid Componfnt nfwFodusOwnfr = null;
    privbtf stbtid volbtilf boolfbn disbblfRfstorfFodus;

    stbtid finbl int SNFH_FAILURE = 0;
    stbtid finbl int SNFH_SUCCESS_HANDLED = 1;
    stbtid finbl int SNFH_SUCCESS_PROCEED = 2;

    stbtid boolfbn prodfssSyndironousLigitwfigitTrbnsffr(Componfnt ifbvywfigit, Componfnt dfsdfndbnt,
                                                  boolfbn tfmporbry, boolfbn fodusfdWindowCibngfAllowfd,
                                                  long timf)
    {
        Window pbrfntWindow = SunToolkit.gftContbiningWindow(ifbvywfigit);
        if (pbrfntWindow == null || !pbrfntWindow.syndLWRfqufsts) {
            rfturn fblsf;
        }
        if (dfsdfndbnt == null) {
            // Fodus trbnsffrs from b ligitwfigit diild bbdk to tif
            // ifbvywfigit Contbinfr siould bf trfbtfd likf ligitwfigit
            // fodus trbnsffrs.
            dfsdfndbnt = ifbvywfigit;
        }

        KfybobrdFodusMbnbgfr mbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr(SunToolkit.tbrgftToAppContfxt(dfsdfndbnt));

        FodusEvfnt durrfntFodusOwnfrEvfnt = null;
        FodusEvfnt nfwFodusOwnfrEvfnt = null;
        Componfnt durrfntFodusOwnfr = mbnbgfr.gftGlobblFodusOwnfr();

        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftLbstHWRfqufst();
            if (iwFodusRfqufst == null &&
                ifbvywfigit == mbnbgfr.gftNbtivfFodusOwnfr() &&
                bllowSyndFodusRfqufsts)
            {

                if (dfsdfndbnt == durrfntFodusOwnfr) {
                    // Rfdundbnt rfqufst.
                    rfturn truf;
                }

                // 'ifbvywfigit' owns tif nbtivf fodus bnd tifrf brf no pfnding
                // rfqufsts. 'ifbvywfigit' must bf b Contbinfr bnd
                // 'dfsdfndbnt' must not bf tif fodus ownfr. Otifrwisf,
                // wf would nfvfr ibvf gottfn tiis fbr.
                mbnbgfr.fnqufufKfyEvfnts(timf, dfsdfndbnt);

                iwFodusRfqufst =
                    nfw HfbvywfigitFodusRfqufst(ifbvywfigit, dfsdfndbnt,
                                                tfmporbry, CbusfdFodusEvfnt.Cbusf.UNKNOWN);
                ifbvywfigitRfqufsts.bdd(iwFodusRfqufst);

                if (durrfntFodusOwnfr != null) {
                    durrfntFodusOwnfrEvfnt =
                        nfw FodusEvfnt(durrfntFodusOwnfr,
                                       FodusEvfnt.FOCUS_LOST,
                                       tfmporbry, dfsdfndbnt);
                }
                nfwFodusOwnfrEvfnt =
                    nfw FodusEvfnt(dfsdfndbnt, FodusEvfnt.FOCUS_GAINED,
                                   tfmporbry, durrfntFodusOwnfr);
            }
        }
        boolfbn rfsult = fblsf;
        finbl boolfbn dlfbring = dlfbringCurrfntLigitwfigitRfqufsts;

        Tirowbblf dbugitEx = null;
        try {
            dlfbringCurrfntLigitwfigitRfqufsts = fblsf;
            syndironizfd(Componfnt.LOCK) {

                if (durrfntFodusOwnfrEvfnt != null && durrfntFodusOwnfr != null) {
                    ((AWTEvfnt) durrfntFodusOwnfrEvfnt).isPostfd = truf;
                    dbugitEx = dispbtdiAndCbtdiExdfption(dbugitEx, durrfntFodusOwnfr, durrfntFodusOwnfrEvfnt);
                    rfsult = truf;
                }

                if (nfwFodusOwnfrEvfnt != null && dfsdfndbnt != null) {
                    ((AWTEvfnt) nfwFodusOwnfrEvfnt).isPostfd = truf;
                    dbugitEx = dispbtdiAndCbtdiExdfption(dbugitEx, dfsdfndbnt, nfwFodusOwnfrEvfnt);
                    rfsult = truf;
                }
            }
        } finblly {
            dlfbringCurrfntLigitwfigitRfqufsts = dlfbring;
        }
        if (dbugitEx instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)dbugitEx;
        } flsf if (dbugitEx instbndfof Error) {
            tirow (Error)dbugitEx;
        }
        rfturn rfsult;
    }

    /**
     * Indidbtfs wiftifr tif nbtivf implfmfntbtion siould prodffd witi b
     * pfnding, nbtivf fodus rfqufst. Bfforf dibnging tif fodus bt tif nbtivf
     * lfvfl, tif AWT implfmfntbtion siould blwbys dbll tiis fundtion for
     * pfrmission. Tiis fundtion will rfjfdt tif rfqufst if b duplidbtf rfqufst
     * prfdfdfd it, or if tif spfdififd ifbvywfigit Componfnt blrfbdy owns tif
     * fodus bnd no nbtivf fodus dibngfs brf pfnding. Otifrwisf, tif rfqufst
     * will bf bpprovfd bnd tif fodus rfqufst list will bf updbtfd so tibt,
     * if nfdfssbry, tif propfr dfsdfndbnt will bf fodusfd wifn tif
     * dorrfsponding FOCUS_GAINED fvfnt on tif ifbvywfigit is rfdfivfd.
     *
     * An implfmfntbtion must fnsurf tibt dblls to tiis mftiod bnd nbtivf
     * fodus dibngfs brf btomid. If tiis is not gubrbntffd, tifn tif ordfring
     * of tif fodus rfqufst list mby bf indorrfdt, lfbding to frrors in tif
     * typf-bifbd mfdibnism. Typidblly tiis is bddomplisifd by only dblling
     * tiis fundtion from tif nbtivf fvfnt pumping tirfbd, or by iolding b
     * globbl, nbtivf lodk during invodbtion.
     */
    stbtid int siouldNbtivflyFodusHfbvywfigit
        (Componfnt ifbvywfigit, Componfnt dfsdfndbnt, boolfbn tfmporbry,
         boolfbn fodusfdWindowCibngfAllowfd, long timf, CbusfdFodusEvfnt.Cbusf dbusf)
    {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if (ifbvywfigit == null) {
                log.finf("Assfrtion (ifbvywfigit != null) fbilfd");
            }
            if (timf == 0) {
                log.finf("Assfrtion (timf != 0) fbilfd");
            }
        }

        if (dfsdfndbnt == null) {
            // Fodus trbnsffrs from b ligitwfigit diild bbdk to tif
            // ifbvywfigit Contbinfr siould bf trfbtfd likf ligitwfigit
            // fodus trbnsffrs.
            dfsdfndbnt = ifbvywfigit;
        }

        KfybobrdFodusMbnbgfr mbnbgfr =
            gftCurrfntKfybobrdFodusMbnbgfr(SunToolkit.tbrgftToAppContfxt(dfsdfndbnt));
        KfybobrdFodusMbnbgfr tiisMbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr();
        Componfnt durrfntFodusOwnfr = tiisMbnbgfr.gftGlobblFodusOwnfr();
        Componfnt nbtivfFodusOwnfr = tiisMbnbgfr.gftNbtivfFodusOwnfr();
        Window nbtivfFodusfdWindow = tiisMbnbgfr.gftNbtivfFodusfdWindow();
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("SNFH for {0} in {1}",
                       String.vblufOf(dfsdfndbnt), String.vblufOf(ifbvywfigit));
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fodusLog.finfst("0. Currfnt fodus ownfr {0}",
                            String.vblufOf(durrfntFodusOwnfr));
            fodusLog.finfst("0. Nbtivf fodus ownfr {0}",
                            String.vblufOf(nbtivfFodusOwnfr));
            fodusLog.finfst("0. Nbtivf fodusfd window {0}",
                            String.vblufOf(nbtivfFodusfdWindow));
        }
        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftLbstHWRfqufst();
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Rfqufst {0}", String.vblufOf(iwFodusRfqufst));
            }
            if (iwFodusRfqufst == null &&
                ifbvywfigit == nbtivfFodusOwnfr &&
                ifbvywfigit.gftContbiningWindow() == nbtivfFodusfdWindow)
            {
                if (dfsdfndbnt == durrfntFodusOwnfr) {
                    // Rfdundbnt rfqufst.
                    if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST))
                        fodusLog.finfst("1. SNFH_FAILURE for {0}",
                                        String.vblufOf(dfsdfndbnt));
                    rfturn SNFH_FAILURE;
                }

                // 'ifbvywfigit' owns tif nbtivf fodus bnd tifrf brf no pfnding
                // rfqufsts. 'ifbvywfigit' must bf b Contbinfr bnd
                // 'dfsdfndbnt' must not bf tif fodus ownfr. Otifrwisf,
                // wf would nfvfr ibvf gottfn tiis fbr.
                mbnbgfr.fnqufufKfyEvfnts(timf, dfsdfndbnt);

                iwFodusRfqufst =
                    nfw HfbvywfigitFodusRfqufst(ifbvywfigit, dfsdfndbnt,
                                                tfmporbry, dbusf);
                ifbvywfigitRfqufsts.bdd(iwFodusRfqufst);

                if (durrfntFodusOwnfr != null) {
                    FodusEvfnt durrfntFodusOwnfrEvfnt =
                        nfw CbusfdFodusEvfnt(durrfntFodusOwnfr,
                                       FodusEvfnt.FOCUS_LOST,
                                       tfmporbry, dfsdfndbnt, dbusf);
                    // Fix 5028014. Rollfd out.
                    // SunToolkit.postPriorityEvfnt(durrfntFodusOwnfrEvfnt);
                    SunToolkit.postEvfnt(durrfntFodusOwnfr.bppContfxt,
                                         durrfntFodusOwnfrEvfnt);
                }
                FodusEvfnt nfwFodusOwnfrEvfnt =
                    nfw CbusfdFodusEvfnt(dfsdfndbnt, FodusEvfnt.FOCUS_GAINED,
                                   tfmporbry, durrfntFodusOwnfr, dbusf);
                // Fix 5028014. Rollfd out.
                // SunToolkit.postPriorityEvfnt(nfwFodusOwnfrEvfnt);
                SunToolkit.postEvfnt(dfsdfndbnt.bppContfxt, nfwFodusOwnfrEvfnt);

                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST))
                    fodusLog.finfst("2. SNFH_HANDLED for {0}", String.vblufOf(dfsdfndbnt));
                rfturn SNFH_SUCCESS_HANDLED;
            } flsf if (iwFodusRfqufst != null &&
                       iwFodusRfqufst.ifbvywfigit == ifbvywfigit) {
                // 'ifbvywfigit' dofsn't ibvf tif nbtivf fodus rigit now, but
                // if bll pfnding rfqufsts wfrf domplftfd, it would. Add
                // dfsdfndbnt to tif ifbvywfigit's list of pfnding
                // ligitwfigit fodus trbnsffrs.
                if (iwFodusRfqufst.bddLigitwfigitRfqufst(dfsdfndbnt,
                                                         tfmporbry, dbusf)) {
                    mbnbgfr.fnqufufKfyEvfnts(timf, dfsdfndbnt);
                }

                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    fodusLog.finfst("3. SNFH_HANDLED for ligitwfigit" +
                                    dfsdfndbnt + " in " + ifbvywfigit);
                }
                rfturn SNFH_SUCCESS_HANDLED;
            } flsf {
                if (!fodusfdWindowCibngfAllowfd) {
                    // For purposfs of domputing oldFodusfdWindow, wf siould look bt
                    // tif sfdond to lbst HfbvywfigitFodusRfqufst on tif qufuf iff tif
                    // lbst HfbvywfigitFodusRfqufst is CLEAR_GLOBAL_FOCUS_OWNER. If
                    // tifrf is no sfdond to lbst HfbvywfigitFodusRfqufst, null is bn
                    // bddfptbblf vbluf.
                    if (iwFodusRfqufst ==
                        HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER)
                    {
                        int sizf = ifbvywfigitRfqufsts.sizf();
                        iwFodusRfqufst = (sizf >= 2)
                            ? ifbvywfigitRfqufsts.gft(sizf - 2)
                            : null;
                    }
                    if (fodusfdWindowCibngfd(ifbvywfigit,
                                             (iwFodusRfqufst != null)
                                             ? iwFodusRfqufst.ifbvywfigit
                                             : nbtivfFodusfdWindow)) {
                        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                            fodusLog.finfst("4. SNFH_FAILURE for " + dfsdfndbnt);
                        }
                        rfturn SNFH_FAILURE;
                    }
                }

                mbnbgfr.fnqufufKfyEvfnts(timf, dfsdfndbnt);
                ifbvywfigitRfqufsts.bdd
                    (nfw HfbvywfigitFodusRfqufst(ifbvywfigit, dfsdfndbnt,
                                                 tfmporbry, dbusf));
                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    fodusLog.finfst("5. SNFH_PROCEED for " + dfsdfndbnt);
                }
                rfturn SNFH_SUCCESS_PROCEED;
            }
        }
    }

    /**
     * Rfturns tif Window wiidi will bf bdtivf bftfr prodfssing tiis rfqufst,
     * or null if tiis is b duplidbtf rfqufst. Tif bdtivf Window is usfful
     * bfdbusf somf nbtivf plbtforms do not support sftting tif nbtivf fodus
     * ownfr to null. On tifsf plbtforms, tif obvious dioidf is to sft tif
     * fodus ownfr to tif fodus proxy of tif bdtivf Window.
     */
    stbtid Window mbrkClfbrGlobblFodusOwnfr() {
        // nffd to dbll tiis out of syndironizfd blodk to bvoid possiblf dfbdlodk
        // sff 6454631.
        finbl Componfnt nbtivfFodusfdWindow =
                gftCurrfntKfybobrdFodusMbnbgfr().gftNbtivfFodusfdWindow();

        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftLbstHWRfqufst();
            if (iwFodusRfqufst ==
                HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                // duplidbtf rfqufst
                rfturn null;
            }

            ifbvywfigitRfqufsts.bdd
                (HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER);

            Componfnt bdtivfWindow = ((iwFodusRfqufst != null)
                ? SunToolkit.gftContbiningWindow(iwFodusRfqufst.ifbvywfigit)
                : nbtivfFodusfdWindow);
            wiilf (bdtivfWindow != null &&
                   !((bdtivfWindow instbndfof Frbmf) ||
                     (bdtivfWindow instbndfof Diblog)))
            {
                bdtivfWindow = bdtivfWindow.gftPbrfnt_NoClifntCodf();
            }

            rfturn (Window) bdtivfWindow;
        }
    }
    Componfnt gftCurrfntWbitingRfqufst(Componfnt pbrfnt) {
        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftFirstHWRfqufst();
            if (iwFodusRfqufst != null) {
                if (iwFodusRfqufst.ifbvywfigit == pbrfnt) {
                    LigitwfigitFodusRfqufst lwFodusRfqufst =
                        iwFodusRfqufst.ligitwfigitRfqufsts.gftFirst();
                    if (lwFodusRfqufst != null) {
                        rfturn lwFodusRfqufst.domponfnt;
                    }
                }
            }
        }
        rfturn null;
    }

    stbtid boolfbn isAutoFodusTrbnsffrEnbblfd() {
        syndironizfd (ifbvywfigitRfqufsts) {
            rfturn (ifbvywfigitRfqufsts.sizf() == 0)
                    && !disbblfRfstorfFodus
                    && (null == durrfntLigitwfigitRfqufsts);
        }
    }

    stbtid boolfbn isAutoFodusTrbnsffrEnbblfdFor(Componfnt domp) {
        rfturn isAutoFodusTrbnsffrEnbblfd() && domp.isAutoFodusTrbnsffrOnDisposbl();
    }

    /*
     * Usfd to prodfss fxdfptions in dispbtdiing fodus fvfnt (in fodusLost/fodusGbinfd dbllbbdks).
     * @pbrbm fx prfviously dbugit fxdfption tibt mby bf prodfssfd rigit ifrf, or null
     * @pbrbm domp tif domponfnt to dispbtdi tif fvfnt to
     * @pbrbm fvfnt tif fvfnt to dispbtdi to tif domponfnt
     */
    stbtid privbtf Tirowbblf dispbtdiAndCbtdiExdfption(Tirowbblf fx, Componfnt domp, FodusEvfnt fvfnt) {
        Tirowbblf rftEx = null;
        try {
            domp.dispbtdiEvfnt(fvfnt);
        } dbtdi (RuntimfExdfption rf) {
            rftEx = rf;
        } dbtdi (Error fr) {
            rftEx = fr;
        }
        if (rftEx != null) {
            if (fx != null) {
                ibndlfExdfption(fx);
            }
            rfturn rftEx;
        }
        rfturn fx;
    }

    stbtid privbtf void ibndlfExdfption(Tirowbblf fx) {
        fx.printStbdkTrbdf();
    }

    stbtid void prodfssCurrfntLigitwfigitRfqufsts() {
        KfybobrdFodusMbnbgfr mbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr();
        LinkfdList<LigitwfigitFodusRfqufst> lodblLigitwfigitRfqufsts = null;

        Componfnt globblFodusOwnfr = mbnbgfr.gftGlobblFodusOwnfr();
        if ((globblFodusOwnfr != null) &&
            (globblFodusOwnfr.bppContfxt != AppContfxt.gftAppContfxt()))
        {
            // Tif durrfnt bpp dontfxt difffrs from tif bpp dontfxt of b fodus
            // ownfr (bnd bll pfnding ligitwfigit rfqufsts), so wf do notiing
            // now bnd wbit for b nfxt fvfnt.
            rfturn;
        }

        syndironizfd(ifbvywfigitRfqufsts) {
            if (durrfntLigitwfigitRfqufsts != null) {
                dlfbringCurrfntLigitwfigitRfqufsts = truf;
                disbblfRfstorfFodus = truf;
                lodblLigitwfigitRfqufsts = durrfntLigitwfigitRfqufsts;
                bllowSyndFodusRfqufsts = (lodblLigitwfigitRfqufsts.sizf() < 2);
                durrfntLigitwfigitRfqufsts = null;
            } flsf {
                // do notiing
                rfturn;
            }
        }

        Tirowbblf dbugitEx = null;
        try {
            if (lodblLigitwfigitRfqufsts != null) {
                Componfnt lbstFodusOwnfr = null;
                Componfnt durrfntFodusOwnfr = null;

                for (Itfrbtor<KfybobrdFodusMbnbgfr.LigitwfigitFodusRfqufst> itfr = lodblLigitwfigitRfqufsts.itfrbtor(); itfr.ibsNfxt(); ) {

                    durrfntFodusOwnfr = mbnbgfr.gftGlobblFodusOwnfr();
                    LigitwfigitFodusRfqufst lwFodusRfqufst =
                        itfr.nfxt();

                    /*
                     * WARNING: Tiis is bbsfd on DKFM's logid solfly!
                     *
                     * Wf bllow to triggfr rfstorfFodus() in tif dispbtdiing prodfss
                     * only if wf ibvf tif lbst rfqufst to dispbtdi. If tif lbst rfqufst
                     * fbils, fodus will bf rfstorfd to fitifr tif domponfnt of tif lbst
                     * prfviously suddfddfd rfqufst, or to to tif fodus ownfr tibt wbs
                     * bfforf tiis dlfbring prodfss.
                     */
                    if (!itfr.ibsNfxt()) {
                        disbblfRfstorfFodus = fblsf;
                    }

                    FodusEvfnt durrfntFodusOwnfrEvfnt = null;
                    /*
                     * Wf'rf not dispbtdiing FOCUS_LOST wiilf tif durrfnt fodus ownfr is null.
                     * But rfgbrdlfss of wiftifr it's null or not, wf'rf dlfbring ALL tif lodbl
                     * lw rfqufsts.
                     */
                    if (durrfntFodusOwnfr != null) {
                        durrfntFodusOwnfrEvfnt = nfw CbusfdFodusEvfnt(durrfntFodusOwnfr,
                                       FodusEvfnt.FOCUS_LOST,
                                       lwFodusRfqufst.tfmporbry,
                                       lwFodusRfqufst.domponfnt, lwFodusRfqufst.dbusf);
                    }
                    FodusEvfnt nfwFodusOwnfrEvfnt =
                        nfw CbusfdFodusEvfnt(lwFodusRfqufst.domponfnt,
                                       FodusEvfnt.FOCUS_GAINED,
                                       lwFodusRfqufst.tfmporbry,
                                       durrfntFodusOwnfr == null ? lbstFodusOwnfr : durrfntFodusOwnfr,
                                       lwFodusRfqufst.dbusf);

                    if (durrfntFodusOwnfr != null) {
                        ((AWTEvfnt) durrfntFodusOwnfrEvfnt).isPostfd = truf;
                        dbugitEx = dispbtdiAndCbtdiExdfption(dbugitEx, durrfntFodusOwnfr, durrfntFodusOwnfrEvfnt);
                    }

                    ((AWTEvfnt) nfwFodusOwnfrEvfnt).isPostfd = truf;
                    dbugitEx = dispbtdiAndCbtdiExdfption(dbugitEx, lwFodusRfqufst.domponfnt, nfwFodusOwnfrEvfnt);

                    if (mbnbgfr.gftGlobblFodusOwnfr() == lwFodusRfqufst.domponfnt) {
                        lbstFodusOwnfr = lwFodusRfqufst.domponfnt;
                    }
                }
            }
        } finblly {
            dlfbringCurrfntLigitwfigitRfqufsts = fblsf;
            disbblfRfstorfFodus = fblsf;
            lodblLigitwfigitRfqufsts = null;
            bllowSyndFodusRfqufsts = truf;
        }
        if (dbugitEx instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)dbugitEx;
        } flsf if (dbugitEx instbndfof Error) {
            tirow (Error)dbugitEx;
        }
    }

    stbtid FodusEvfnt rftbrgftUnfxpfdtfdFodusEvfnt(FodusEvfnt ff) {
        syndironizfd (ifbvywfigitRfqufsts) {
            // Any otifr dbsf rfprfsfnts b fbilurf dondition wiidi wf did
            // not fxpfdt. Wf nffd to dlfbrFodusRfqufstList() bnd pbtdi up
            // tif fvfnt bs bfst bs possiblf.

            if (rfmovfFirstRfqufst()) {
                rfturn (FodusEvfnt)rftbrgftFodusEvfnt(ff);
            }

            Componfnt sourdf = ff.gftComponfnt();
            Componfnt oppositf = ff.gftOppositfComponfnt();
            boolfbn tfmporbry = fblsf;
            if (ff.gftID() == FodusEvfnt.FOCUS_LOST &&
                (oppositf == null || isTfmporbry(oppositf, sourdf)))
            {
                tfmporbry = truf;
            }
            rfturn nfw CbusfdFodusEvfnt(sourdf, ff.gftID(), tfmporbry, oppositf,
                                        CbusfdFodusEvfnt.Cbusf.NATIVE_SYSTEM);
        }
    }

    stbtid FodusEvfnt rftbrgftFodusGbinfd(FodusEvfnt ff) {
        bssfrt (ff.gftID() == FodusEvfnt.FOCUS_GAINED);

        Componfnt durrfntFodusOwnfr = gftCurrfntKfybobrdFodusMbnbgfr().
            gftGlobblFodusOwnfr();
        Componfnt sourdf = ff.gftComponfnt();
        Componfnt oppositf = ff.gftOppositfComponfnt();
        Componfnt nbtivfSourdf = gftHfbvywfigit(sourdf);

        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftFirstHWRfqufst();

            if (iwFodusRfqufst == HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                rfturn rftbrgftUnfxpfdtfdFodusEvfnt(ff);
            }

            if (sourdf != null && nbtivfSourdf == null && iwFodusRfqufst != null) {
                // if sourdf w/o pffr bnd
                // if sourdf is fqubl to first ligitwfigit
                // tifn wf siould dorrfdt sourdf bnd nbtivfSourdf
                if (sourdf == iwFodusRfqufst.gftFirstLigitwfigitRfqufst().domponfnt)
                {
                    sourdf = iwFodusRfqufst.ifbvywfigit;
                    nbtivfSourdf = sourdf; // sourdf is ifbvuwfigit itsflf
                }
            }
            if (iwFodusRfqufst != null &&
                nbtivfSourdf == iwFodusRfqufst.ifbvywfigit)
            {
                // Fodus dibngf bs b rfsult of b known dbll to rfqufstFodus(),
                // or known dlidk on b pffr fodusbblf ifbvywfigit Componfnt.

                ifbvywfigitRfqufsts.rfmovfFirst();

                LigitwfigitFodusRfqufst lwFodusRfqufst =
                    iwFodusRfqufst.ligitwfigitRfqufsts.rfmovfFirst();

                Componfnt nfwSourdf = lwFodusRfqufst.domponfnt;
                if (durrfntFodusOwnfr != null) {
                    /*
                     * Sindf wf rfdfivf FOCUS_GAINED wifn durrfnt fodus
                     * ownfr is not null, dorrfdponding FOCUS_LOST is supposfd
                     * to bf lost.  And so,  wf kffp nfw fodus ownfr
                     * to dftfrminf syntiftid FOCUS_LOST fvfnt wiidi will bf
                     * gfnfrbtfd by KfybobrdFodusMbnbgfr for tiis FOCUS_GAINED.
                     *
                     * Tiis dodf bbsfd on knowlfdgf of
                     * DffbultKfybobrdFodusMbnbgfr's implfmfntbtion bnd migit
                     * bf not bpplidbblf for bnotifr KfybobrdFodusMbnbgfr.
                     */
                    nfwFodusOwnfr = nfwSourdf;
                }

                boolfbn tfmporbry = (oppositf == null ||
                                     isTfmporbry(nfwSourdf, oppositf))
                        ? fblsf
                        : lwFodusRfqufst.tfmporbry;

                if (iwFodusRfqufst.ligitwfigitRfqufsts.sizf() > 0) {
                    durrfntLigitwfigitRfqufsts =
                        iwFodusRfqufst.ligitwfigitRfqufsts;
                    EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
                            publid void run() {
                                prodfssCurrfntLigitwfigitRfqufsts();
                            }
                        });
                }

                // 'oppositf' will bf fixfd by
                // DffbultKfybobrdFodusMbnbgfr.rfblOppositfComponfnt
                rfturn nfw CbusfdFodusEvfnt(nfwSourdf,
                                      FodusEvfnt.FOCUS_GAINED, tfmporbry,
                                      oppositf, lwFodusRfqufst.dbusf);
            }

            if (durrfntFodusOwnfr != null
                && durrfntFodusOwnfr.gftContbiningWindow() == sourdf
                && (iwFodusRfqufst == null || sourdf != iwFodusRfqufst.ifbvywfigit))
            {
                // Spfdibl dbsf for FOCUS_GAINED in top-lfvfls
                // If it brrivfs bs tif rfsult of bdtivbtion wf siould skip it
                // Tiis fvfnt will not ibvf bppropribtf rfqufst rfdord bnd
                // on brrivbl tifrf will bf blrfbdy somf fodus ownfr sft.
                rfturn nfw CbusfdFodusEvfnt(durrfntFodusOwnfr, FodusEvfnt.FOCUS_GAINED, fblsf,
                                            null, CbusfdFodusEvfnt.Cbusf.ACTIVATION);
            }

            rfturn rftbrgftUnfxpfdtfdFodusEvfnt(ff);
        } // fnd syndironizfd(ifbvywfigitRfqufsts)
    }

    stbtid FodusEvfnt rftbrgftFodusLost(FodusEvfnt ff) {
        bssfrt (ff.gftID() == FodusEvfnt.FOCUS_LOST);

        Componfnt durrfntFodusOwnfr = gftCurrfntKfybobrdFodusMbnbgfr().
            gftGlobblFodusOwnfr();
        Componfnt oppositf = ff.gftOppositfComponfnt();
        Componfnt nbtivfOppositf = gftHfbvywfigit(oppositf);

        syndironizfd (ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftFirstHWRfqufst();

            if (iwFodusRfqufst == HfbvywfigitFodusRfqufst.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                if (durrfntFodusOwnfr != null) {
                    // Cbll to KfybobrdFodusMbnbgfr.dlfbrGlobblFodusOwnfr()
                    ifbvywfigitRfqufsts.rfmovfFirst();
                    rfturn nfw CbusfdFodusEvfnt(durrfntFodusOwnfr,
                                                FodusEvfnt.FOCUS_LOST, fblsf, null,
                                                CbusfdFodusEvfnt.Cbusf.CLEAR_GLOBAL_FOCUS_OWNER);
                }

                // Otifrwisf, fbll tirougi to fbilurf dbsf bflow

            } flsf if (oppositf == null)
            {
                // Fodus lfbving bpplidbtion
                if (durrfntFodusOwnfr != null) {
                    rfturn nfw CbusfdFodusEvfnt(durrfntFodusOwnfr,
                                                FodusEvfnt.FOCUS_LOST,
                                                truf, null, CbusfdFodusEvfnt.Cbusf.ACTIVATION);
                } flsf {
                    rfturn ff;
                }
            } flsf if (iwFodusRfqufst != null &&
                       (nbtivfOppositf == iwFodusRfqufst.ifbvywfigit ||
                        nbtivfOppositf == null &&
                        oppositf == iwFodusRfqufst.gftFirstLigitwfigitRfqufst().domponfnt))
            {
                if (durrfntFodusOwnfr == null) {
                    rfturn ff;
                }
                // Fodus dibngf bs b rfsult of b known dbll to rfqufstFodus(),
                // or dlidk on b pffr fodusbblf ifbvywfigit Componfnt.
                // If b fodus trbnsffr is mbdf bdross top-lfvfls, tifn tif
                // FOCUS_LOST fvfnt is blwbys tfmporbry, bnd tif FOCUS_GAINED
                // fvfnt is blwbys pfrmbnfnt. Otifrwisf, tif storfd tfmporbry
                // vbluf is ionorfd.

                LigitwfigitFodusRfqufst lwFodusRfqufst =
                    iwFodusRfqufst.ligitwfigitRfqufsts.gftFirst();

                boolfbn tfmporbry = isTfmporbry(oppositf, durrfntFodusOwnfr)
                    ? truf
                    : lwFodusRfqufst.tfmporbry;

                rfturn nfw CbusfdFodusEvfnt(durrfntFodusOwnfr, FodusEvfnt.FOCUS_LOST,
                                            tfmporbry, lwFodusRfqufst.domponfnt, lwFodusRfqufst.dbusf);
            } flsf if (fodusfdWindowCibngfd(oppositf, durrfntFodusOwnfr)) {
                // If top-lfvfl dibngfd tifrf migit bf no fodus rfqufst in b list
                // But wf know tif oppositf, wf now it is tfmporbry - dispbtdi tif fvfnt.
                if (!ff.isTfmporbry() && durrfntFodusOwnfr != null) {
                    // Crfbtf dopy of tif fvfnt witi only difffrfndf in tfmporbry pbrbmftfr.
                    ff = nfw CbusfdFodusEvfnt(durrfntFodusOwnfr, FodusEvfnt.FOCUS_LOST,
                                              truf, oppositf, CbusfdFodusEvfnt.Cbusf.ACTIVATION);
                }
                rfturn ff;
            }

            rfturn rftbrgftUnfxpfdtfdFodusEvfnt(ff);
        }  // fnd syndironizfd(ifbvywfigitRfqufsts)
    }

    stbtid AWTEvfnt rftbrgftFodusEvfnt(AWTEvfnt fvfnt) {
        if (dlfbringCurrfntLigitwfigitRfqufsts) {
            rfturn fvfnt;
        }

        KfybobrdFodusMbnbgfr mbnbgfr = gftCurrfntKfybobrdFodusMbnbgfr();
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            if (fvfnt instbndfof FodusEvfnt || fvfnt instbndfof WindowEvfnt) {
                fodusLog.finfr(">>> {0}", String.vblufOf(fvfnt));
            }
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER) && fvfnt instbndfof KfyEvfnt) {
                fodusLog.finfr("    fodus ownfr is {0}",
                               String.vblufOf(mbnbgfr.gftGlobblFodusOwnfr()));
                fodusLog.finfr(">>> {0}", String.vblufOf(fvfnt));
            }
        }

        syndironizfd(ifbvywfigitRfqufsts) {
            /*
             * Tiis dodf ibndlfs FOCUS_LOST fvfnt wiidi is gfnfrbtfd by
             * DffbultKfybobrdFodusMbnbgfr for FOCUS_GAINED.
             *
             * Tiis dodf bbsfd on knowlfdgf of DffbultKfybobrdFodusMbnbgfr's
             * implfmfntbtion bnd migit bf not bpplidbblf for bnotifr
             * KfybobrdFodusMbnbgfr.
             *
             * Fix for 4472032
             */
            if (nfwFodusOwnfr != null &&
                fvfnt.gftID() == FodusEvfnt.FOCUS_LOST)
            {
                FodusEvfnt ff = (FodusEvfnt)fvfnt;

                if (mbnbgfr.gftGlobblFodusOwnfr() == ff.gftComponfnt() &&
                    ff.gftOppositfComponfnt() == nfwFodusOwnfr)
                {
                    nfwFodusOwnfr = null;
                    rfturn fvfnt;
                }
            }
        }

        prodfssCurrfntLigitwfigitRfqufsts();

        switdi (fvfnt.gftID()) {
            dbsf FodusEvfnt.FOCUS_GAINED: {
                fvfnt = rftbrgftFodusGbinfd((FodusEvfnt)fvfnt);
                brfbk;
            }
            dbsf FodusEvfnt.FOCUS_LOST: {
                fvfnt = rftbrgftFodusLost((FodusEvfnt)fvfnt);
                brfbk;
            }
            dffbult:
                /* do notiing */
        }
        rfturn fvfnt;
    }

    /**
     * Clfbrs mbrkfrs qufuf
     * Tiis mftiod is not intfndfd to bf ovfrriddfn by KFM's.
     * Only DffbultKfybobrdFodusMbnbgfr dbn implfmfnt it.
     * @sindf 1.5
     */
    void dlfbrMbrkfrs() {
    }

    stbtid boolfbn rfmovfFirstRfqufst() {
        KfybobrdFodusMbnbgfr mbnbgfr =
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();

        syndironizfd(ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftFirstHWRfqufst();

            if (iwFodusRfqufst != null) {
                ifbvywfigitRfqufsts.rfmovfFirst();
                if (iwFodusRfqufst.ligitwfigitRfqufsts != null) {
                    for (Itfrbtor<KfybobrdFodusMbnbgfr.LigitwfigitFodusRfqufst> lwItfr = iwFodusRfqufst.ligitwfigitRfqufsts.
                             itfrbtor();
                         lwItfr.ibsNfxt(); )
                    {
                        mbnbgfr.dfqufufKfyEvfnts
                            (-1, lwItfr.nfxt().
                             domponfnt);
                    }
                }
            }
            // Fix for 4799136 - dlfbr typf-bifbd mbrkfrs if rfqufsts qufuf is fmpty
            // Wf do it ifrf bfdbusf tiis mftiod is dbllfd only wifn problfms ibppfn
            if (ifbvywfigitRfqufsts.sizf() == 0) {
                mbnbgfr.dlfbrMbrkfrs();
            }
            rfturn (ifbvywfigitRfqufsts.sizf() > 0);
        }
    }
    stbtid void rfmovfLbstFodusRfqufst(Componfnt ifbvywfigit) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if (ifbvywfigit == null) {
                log.finf("Assfrtion (ifbvywfigit != null) fbilfd");
            }
        }

        KfybobrdFodusMbnbgfr mbnbgfr =
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
        syndironizfd(ifbvywfigitRfqufsts) {
            HfbvywfigitFodusRfqufst iwFodusRfqufst = gftLbstHWRfqufst();
            if (iwFodusRfqufst != null &&
                iwFodusRfqufst.ifbvywfigit == ifbvywfigit) {
                ifbvywfigitRfqufsts.rfmovfLbst();
            }
            // Fix for 4799136 - dlfbr typf-bifbd mbrkfrs if rfqufsts qufuf is fmpty
            // Wf do it ifrf bfdbusf tiis mftiod is dbllfd only wifn problfms ibppfn
            if (ifbvywfigitRfqufsts.sizf() == 0) {
                mbnbgfr.dlfbrMbrkfrs();
            }
        }
    }

    privbtf stbtid boolfbn fodusfdWindowCibngfd(Componfnt to, Componfnt from) {
        Window wto = SunToolkit.gftContbiningWindow(to);
        Window wfrom = SunToolkit.gftContbiningWindow(from);
        if (wto == null && wfrom == null) {
            rfturn truf;
        }
        if (wto == null) {
            rfturn truf;
        }
        if (wfrom == null) {
            rfturn truf;
        }
        rfturn (wto != wfrom);
    }

    privbtf stbtid boolfbn isTfmporbry(Componfnt to, Componfnt from) {
        Window wto = SunToolkit.gftContbiningWindow(to);
        Window wfrom = SunToolkit.gftContbiningWindow(from);
        if (wto == null && wfrom == null) {
            rfturn fblsf;
        }
        if (wto == null) {
            rfturn truf;
        }
        if (wfrom == null) {
            rfturn fblsf;
        }
        rfturn (wto != wfrom);
    }

    stbtid Componfnt gftHfbvywfigit(Componfnt domp) {
        if (domp == null || domp.gftPffr() == null) {
            rfturn null;
        } flsf if (domp.gftPffr() instbndfof LigitwfigitPffr) {
            rfturn domp.gftNbtivfContbinfr();
        } flsf {
            rfturn domp;
        }
    }

    stbtid Fifld proxyAdtivf;
    // Addfssor to privbtf fifld isProxyAdtivf of KfyEvfnt
    privbtf stbtid boolfbn isProxyAdtivfImpl(KfyEvfnt f) {
        if (proxyAdtivf == null) {
            proxyAdtivf =  AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Fifld>() {
                    publid Fifld run() {
                        Fifld fifld = null;
                        try {
                            fifld = KfyEvfnt.dlbss.gftDfdlbrfdFifld("isProxyAdtivf");
                            if (fifld != null) {
                                fifld.sftAddfssiblf(truf);
                            }
                        } dbtdi (NoSudiFifldExdfption nsf) {
                            bssfrt(fblsf);
                        }
                        rfturn fifld;
                    }
                });
        }

        try {
            rfturn proxyAdtivf.gftBoolfbn(f);
        } dbtdi (IllfgblAddfssExdfption ibf) {
            bssfrt(fblsf);
        }
        rfturn fblsf;
    }

    // Rfturns tif vbluf of tiis KfyEvfnt's fifld isProxyAdtivf
    stbtid boolfbn isProxyAdtivf(KfyEvfnt f) {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn isProxyAdtivfImpl(f);
        } flsf {
            rfturn fblsf;
        }
    }

    privbtf stbtid HfbvywfigitFodusRfqufst gftLbstHWRfqufst() {
        syndironizfd(ifbvywfigitRfqufsts) {
            rfturn (ifbvywfigitRfqufsts.sizf() > 0)
                ? ifbvywfigitRfqufsts.gftLbst()
                : null;
        }
    }

    privbtf stbtid HfbvywfigitFodusRfqufst gftFirstHWRfqufst() {
        syndironizfd(ifbvywfigitRfqufsts) {
            rfturn (ifbvywfigitRfqufsts.sizf() > 0)
                ? ifbvywfigitRfqufsts.gftFirst()
                : null;
        }
    }

    privbtf stbtid void difdkRfplbdfKFMPfrmission()
        tirows SfdurityExdfption
    {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            if (rfplbdfKfybobrdFodusMbnbgfrPfrmission == null) {
                rfplbdfKfybobrdFodusMbnbgfrPfrmission =
                    nfw AWTPfrmission("rfplbdfKfybobrdFodusMbnbgfr");
            }
            sfdurity.
                difdkPfrmission(rfplbdfKfybobrdFodusMbnbgfrPfrmission);
        }
    }

    // Cifdks if tiis KfybobrdFodusMbnbgfr instbndf is tif durrfnt KFM,
    // or otifrwisf difdks if tif dblling tirfbd ibs "rfplbdfKfybobrdFodusMbnbgfr"
    // pfrmission. Hfrf's tif rfbsoning to do so:
    //
    // A systfm KFM instbndf (wiidi is tif durrfnt KFM by dffbult) mby ibvf no
    // "rfplbdfKFM" pfrmission wifn b dlifnt dodf is on tif dbll stbdk bfnfbti,
    // but still it siould bf bblf to fxfdutf tif mftiods protfdtfd by tiis difdk
    // duf to tif systfm KFM is trustfd (bnd so it dofs likf "privilfgfd").
    //
    // If tiis KFM instbndf is not tif durrfnt KFM but tif dlifnt dodf ibs bll
    // pfrmissions wf dbn't tirow SfdurityExdfption bfdbusf it would dontrbdidt
    // tif sfdurity dondfpts. In tiis dbsf tif trustfd dlifnt dodf is rfsponsiblf
    // for dblling tif sfdurfd mftiods from KFM instbndf wiidi is not durrfnt.
    privbtf void difdkKFMSfdurity()
        tirows SfdurityExdfption
    {
        if (tiis != gftCurrfntKfybobrdFodusMbnbgfr()) {
            difdkRfplbdfKFMPfrmission();
        }
    }
}
