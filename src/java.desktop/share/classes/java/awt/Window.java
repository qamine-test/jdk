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
pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.*;
import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.im.InputContfxt;
import jbvb.bwt.imbgf.BufffrStrbtfgy;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.WindowPffr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.OptionblDbtbExdfption;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.EvfntListfnfr;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvb.util.Vfdtor;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvbx.bddfssibility.*;
import sun.bwt.AWTAddfssor;
import sun.bwt.AWTPfrmissions;
import sun.bwt.AppContfxt;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.SunToolkit;
import sun.bwt.util.IdfntityArrbyList;
import sun.jbvb2d.Disposfr;
import sun.jbvb2d.pipf.Rfgion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.util.logging.PlbtformLoggfr;

/**
 * A {@dodf Window} objfdt is b top-lfvfl window witi no bordfrs bnd no
 * mfnubbr.
 * Tif dffbult lbyout for b window is {@dodf BordfrLbyout}.
 * <p>
 * A window must ibvf fitifr b frbmf, diblog, or bnotifr window dffinfd bs its
 * ownfr wifn it's donstrudtfd.
 * <p>
 * In b multi-sdrffn fnvironmfnt, you dbn drfbtf b {@dodf Window}
 * on b difffrfnt sdrffn dfvidf by donstrudting tif {@dodf Window}
 * witi {@link #Window(Window, GrbpiidsConfigurbtion)}.  Tif
 * {@dodf GrbpiidsConfigurbtion} objfdt is onf of tif
 * {@dodf GrbpiidsConfigurbtion} objfdts of tif tbrgft sdrffn dfvidf.
 * <p>
 * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif dfsktop
 * brfb dould spbn multiplf piysidbl sdrffn dfvidfs, tif bounds of bll
 * donfigurbtions brf rflbtivf to tif virtubl dfvidf doordinbtf systfm.
 * Tif origin of tif virtubl-doordinbtf systfm is bt tif uppfr lfft-ibnd
 * dornfr of tif primbry piysidbl sdrffn.  Dfpfnding on tif lodbtion of
 * tif primbry sdrffn in tif virtubl dfvidf, nfgbtivf doordinbtfs brf
 * possiblf, bs siown in tif following figurf.
 * <p>
 * <img srd="dod-filfs/MultiSdrffn.gif"
 * blt="Dibgrbm siows virtubl dfvidf dontbining 4 piysidbl sdrffns. Primbry piysidbl sdrffn siows doords (0,0), otifr sdrffn siows (-80,-100)."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * In sudi bn fnvironmfnt, wifn dblling {@dodf sftLodbtion},
 * you must pbss b virtubl doordinbtf to tiis mftiod.  Similbrly,
 * dblling {@dodf gftLodbtionOnSdrffn} on b {@dodf Window} rfturns
 * virtubl dfvidf doordinbtfs.  Cbll tif {@dodf gftBounds} mftiod
 * of b {@dodf GrbpiidsConfigurbtion} to find its origin in tif virtubl
 * doordinbtf systfm.
 * <p>
 * Tif following dodf sfts tif lodbtion of b {@dodf Window}
 * bt (10, 10) rflbtivf to tif origin of tif piysidbl sdrffn
 * of tif dorrfsponding {@dodf GrbpiidsConfigurbtion}.  If tif
 * bounds of tif {@dodf GrbpiidsConfigurbtion} is not tbkfn
 * into bddount, tif {@dodf Window} lodbtion would bf sft
 * bt (10, 10) rflbtivf to tif virtubl-doordinbtf systfm bnd would bppfbr
 * on tif primbry piysidbl sdrffn, wiidi migit bf difffrfnt from tif
 * piysidbl sdrffn of tif spfdififd {@dodf GrbpiidsConfigurbtion}.
 *
 * <prf>
 *      Window w = nfw Window(Window ownfr, GrbpiidsConfigurbtion gd);
 *      Rfdtbnglf bounds = gd.gftBounds();
 *      w.sftLodbtion(10 + bounds.x, 10 + bounds.y);
 * </prf>
 *
 * <p>
 * Notf: tif lodbtion bnd sizf of top-lfvfl windows (indluding
 * {@dodf Window}s, {@dodf Frbmf}s, bnd {@dodf Diblog}s)
 * brf undfr tif dontrol of tif dfsktop's window mbnbgfmfnt systfm.
 * Cblls to {@dodf sftLodbtion}, {@dodf sftSizf}, bnd
 * {@dodf sftBounds} brf rfqufsts (not dirfdtivfs) wiidi brf
 * forwbrdfd to tif window mbnbgfmfnt systfm.  Evfry fffort will bf
 * mbdf to ionor sudi rfqufsts.  Howfvfr, in somf dbsfs tif window
 * mbnbgfmfnt systfm mby ignorf sudi rfqufsts, or modify tif rfqufstfd
 * gfomftry in ordfr to plbdf bnd sizf tif {@dodf Window} in b wby
 * tibt morf dlosfly mbtdifs tif dfsktop sfttings.
 * <p>
 * Duf to tif bsyndironous nbturf of nbtivf fvfnt ibndling, tif rfsults
 * rfturnfd by {@dodf gftBounds}, {@dodf gftLodbtion},
 * {@dodf gftLodbtionOnSdrffn}, bnd {@dodf gftSizf} migit not
 * rfflfdt tif bdtubl gfomftry of tif Window on sdrffn until tif lbst
 * rfqufst ibs bffn prodfssfd.  During tif prodfssing of subsfqufnt
 * rfqufsts tifsf vblufs migit dibngf bddordingly wiilf tif window
 * mbnbgfmfnt systfm fulfills tif rfqufsts.
 * <p>
 * An bpplidbtion mby sft tif sizf bnd lodbtion of bn invisiblf
 * {@dodf Window} brbitrbrily, but tif window mbnbgfmfnt systfm mby
 * subsfqufntly dibngf its sizf bnd/or lodbtion wifn tif
 * {@dodf Window} is mbdf visiblf. Onf or morf {@dodf ComponfntEvfnt}s
 * will bf gfnfrbtfd to indidbtf tif nfw gfomftry.
 * <p>
 * Windows brf dbpbblf of gfnfrbting tif following WindowEvfnts:
 * WindowOpfnfd, WindowClosfd, WindowGbinfdFodus, WindowLostFodus.
 *
 * @butior      Sbmi Sibio
 * @butior      Artiur vbn Hoff
 * @sff WindowEvfnt
 * @sff #bddWindowListfnfr
 * @sff jbvb.bwt.BordfrLbyout
 * @sindf       1.0
 */
publid dlbss Window fxtfnds Contbinfr implfmfnts Addfssiblf {

    /**
     * Enumfrbtion of bvbilbblf <i>window typfs</i>.
     *
     * A window typf dffinfs tif gfnfrid visubl bppfbrbndf bnd bfibvior of b
     * top-lfvfl window. For fxbmplf, tif typf mby bfffdt tif kind of
     * dfdorbtions of b dfdorbtfd {@dodf Frbmf} or {@dodf Diblog} instbndf.
     * <p>
     * Somf plbtforms mby not fully support b dfrtbin window typf. Dfpfnding on
     * tif lfvfl of support, somf propfrtifs of tif window typf mby bf
     * disobfyfd.
     *
     * @sff   #gftTypf
     * @sff   #sftTypf
     * @sindf 1.7
     */
    publid stbtid fnum Typf {
        /**
         * Rfprfsfnts b <i>normbl</i> window.
         *
         * Tiis is tif dffbult typf for objfdts of tif {@dodf Window} dlbss or
         * its dfsdfndbnts. Usf tiis typf for rfgulbr top-lfvfl windows.
         */
        NORMAL,

        /**
         * Rfprfsfnts b <i>utility</i> window.
         *
         * A utility window is usublly b smbll window sudi bs b toolbbr or b
         * pblfttf. Tif nbtivf systfm mby rfndfr tif window witi smbllfr
         * titlf-bbr if tif window is fitifr b {@dodf Frbmf} or b {@dodf
         * Diblog} objfdt, bnd if it ibs its dfdorbtions fnbblfd.
         */
        UTILITY,

        /**
         * Rfprfsfnts b <i>popup</i> window.
         *
         * A popup window is b tfmporbry window sudi bs b drop-down mfnu or b
         * tooltip. On somf plbtforms, windows of tibt typf mby bf fordibly
         * mbdf undfdorbtfd fvfn if tify brf instbndfs of tif {@dodf Frbmf} or
         * {@dodf Diblog} dlbss, bnd ibvf dfdorbtions fnbblfd.
         */
        POPUP
    }

    /**
     * Tiis rfprfsfnts tif wbrning mfssbgf tibt is
     * to bf displbyfd in b non sfdurf window. if :
     * b window tibt ibs b sfdurity mbnbgfr instbllfd tibt dfnifs
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * Tiis mfssbgf dbn bf displbyfd bnywifrf in tif window.
     *
     * @sfribl
     * @sff #gftWbrningString
     */
    String      wbrningString;

    /**
     * {@dodf idons} is tif grbpiidbl wby wf dbn
     * rfprfsfnt tif frbmfs bnd diblogs.
     * {@dodf Window} dbn't displby idon but it's
     * bfing inifritfd by ownfd {@dodf Diblog}s.
     *
     * @sfribl
     * @sff #gftIdonImbgfs
     * @sff #sftIdonImbgfs
     */
    trbnsifnt jbvb.util.List<Imbgf> idons;

    /**
     * Holds tif rfffrfndf to tif domponfnt wiidi lbst ibd fodus in tiis window
     * bfforf it lost fodus.
     */
    privbtf trbnsifnt Componfnt tfmporbryLostComponfnt;

    stbtid boolfbn systfmSyndLWRfqufsts = fblsf;
    boolfbn     syndLWRfqufsts = fblsf;
    trbnsifnt boolfbn bfforfFirstSiow = truf;
    privbtf trbnsifnt boolfbn disposing = fblsf;
    trbnsifnt WindowDisposfrRfdord disposfrRfdord = null;

    stbtid finbl int OPENED = 0x01;

    /**
     * An Intfgfr vbluf rfprfsfnting tif Window Stbtf.
     *
     * @sfribl
     * @sindf 1.2
     * @sff #siow
     */
    int stbtf;

    /**
     * A boolfbn vbluf rfprfsfnting Window blwbys-on-top stbtf
     * @sindf 1.5
     * @sfribl
     * @sff #sftAlwbysOnTop
     * @sff #isAlwbysOnTop
     */
    privbtf boolfbn blwbysOnTop;

    /**
     * Contbins bll tif windows tibt ibvf b pffr objfdt bssodibtfd,
     * i. f. bftwffn bddNotify() bnd rfmovfNotify() dblls. Tif list
     * of bll Window instbndfs dbn bf obtbinfd from AppContfxt objfdt.
     *
     * @sindf 1.6
     */
    privbtf stbtid finbl IdfntityArrbyList<Window> bllWindows = nfw IdfntityArrbyList<Window>();

    /**
     * A vfdtor dontbining bll tif windows tiis
     * window durrfntly owns.
     * @sindf 1.2
     * @sff #gftOwnfdWindows
     */
    trbnsifnt Vfdtor<WfbkRfffrfndf<Window>> ownfdWindowList =
                                            nfw Vfdtor<WfbkRfffrfndf<Window>>();

    /*
     * Wf insfrt b wfbk rfffrfndf into tif Vfdtor of bll Windows in AppContfxt
     * instfbd of 'tiis' so tibt gbrbbgf dollfdtion dbn still tbkf plbdf
     * dorrfdtly.
     */
    privbtf trbnsifnt WfbkRfffrfndf<Window> wfbkTiis;

    trbnsifnt boolfbn siowWitiPbrfnt;

    /**
     * Contbins tif modbl diblog tibt blodks tiis window, or null
     * if tif window is unblodkfd.
     *
     * @sindf 1.6
     */
    trbnsifnt Diblog modblBlodkfr;

    /**
     * @sfribl
     *
     * @sff jbvb.bwt.Diblog.ModblExdlusionTypf
     * @sff #gftModblExdlusionTypf
     * @sff #sftModblExdlusionTypf
     *
     * @sindf 1.6
     */
    Diblog.ModblExdlusionTypf modblExdlusionTypf;

    trbnsifnt WindowListfnfr windowListfnfr;
    trbnsifnt WindowStbtfListfnfr windowStbtfListfnfr;
    trbnsifnt WindowFodusListfnfr windowFodusListfnfr;

    trbnsifnt InputContfxt inputContfxt;
    privbtf trbnsifnt Objfdt inputContfxtLodk = nfw Objfdt();

    /**
     * Unusfd. Mbintbinfd for sfriblizbtion bbdkwbrd-dompbtibility.
     *
     * @sfribl
     * @sindf 1.2
     */
    privbtf FodusMbnbgfr fodusMgr;

    /**
     * Indidbtfs wiftifr tiis Window dbn bfdomf tif fodusfd Window.
     *
     * @sfribl
     * @sff #gftFodusbblfWindowStbtf
     * @sff #sftFodusbblfWindowStbtf
     * @sindf 1.4
     */
    privbtf boolfbn fodusbblfWindowStbtf = truf;

    /**
     * Indidbtfs wiftifr tiis window siould rfdfivf fodus on
     * subsfqufntly bfing siown (witi b dbll to {@dodf sftVisiblf(truf)}), or
     * bfing movfd to tif front (witi b dbll to {@dodf toFront()}).
     *
     * @sfribl
     * @sff #sftAutoRfqufstFodus
     * @sff #isAutoRfqufstFodus
     * @sindf 1.7
     */
    privbtf volbtilf boolfbn butoRfqufstFodus = truf;

    /*
     * Indidbtfs tibt tiis window is bfing siown. Tiis flbg is sft to truf bt
     * tif bfginning of siow() bnd to fblsf bt tif fnd of siow().
     *
     * @sff #siow()
     * @sff Diblog#siouldBlodk
     */
    trbnsifnt boolfbn isInSiow = fblsf;

    /**
     * Tif opbdity lfvfl of tif window
     *
     * @sfribl
     * @sff #sftOpbdity(flobt)
     * @sff #gftOpbdity()
     * @sindf 1.7
     */
    privbtf flobt opbdity = 1.0f;

    /**
     * Tif sibpf bssignfd to tiis window. Tiis fifld is sft to {@dodf null} if
     * no sibpf is sft (rfdtbngulbr window).
     *
     * @sfribl
     * @sff #gftSibpf()
     * @sff #sftSibpf(Sibpf)
     * @sindf 1.7
     */
    privbtf Sibpf sibpf = null;

    privbtf stbtid finbl String bbsf = "win";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4497834738069338734L;

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.Window");

    privbtf stbtid finbl boolfbn lodbtionByPlbtformProp;

    trbnsifnt boolfbn isTrbyIdonWindow = fblsf;

    /**
     * Tifsf fiflds brf initiblizfd in tif nbtivf pffr dodf
     * or vib AWTAddfssor's WindowAddfssor.
     */
    privbtf trbnsifnt volbtilf int sfdurityWbrningWidti = 0;
    privbtf trbnsifnt volbtilf int sfdurityWbrningHfigit = 0;

    /**
     * Tifsf fiflds rfprfsfnt tif dfsirfd lodbtion for tif sfdurity
     * wbrning if tiis window is untrustfd.
     * Sff dom.sun.bwt.SfdurityWbrning for morf dftbils.
     */
    privbtf trbnsifnt doublf sfdurityWbrningPointX = 2.0;
    privbtf trbnsifnt doublf sfdurityWbrningPointY = 0.0;
    privbtf trbnsifnt flobt sfdurityWbrningAlignmfntX = RIGHT_ALIGNMENT;
    privbtf trbnsifnt flobt sfdurityWbrningAlignmfntY = TOP_ALIGNMENT;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        String s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("jbvb.bwt.syndLWRfqufsts"));
        systfmSyndLWRfqufsts = (s != null && s.fqubls("truf"));
        s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("jbvb.bwt.Window.lodbtionByPlbtform"));
        lodbtionByPlbtformProp = (s != null && s.fqubls("truf"));
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
       bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Construdts b nfw, initiblly invisiblf window in dffbult sizf witi tif
     * spfdififd {@dodf GrbpiidsConfigurbtion}.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, tifn it is invokfd to difdk
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}
     * to dftfrminf wiftifr or not tif window must bf displbyfd witi
     * b wbrning bbnnfr.
     *
     * @pbrbm gd tif {@dodf GrbpiidsConfigurbtion} of tif tbrgft sdrffn
     *     dfvidf. If {@dodf gd} is {@dodf null}, tif systfm dffbult
     *     {@dodf GrbpiidsConfigurbtion} is bssumfd
     * @fxdfption IllfgblArgumfntExdfption if {@dodf gd}
     *    is not from b sdrffn dfvidf
     * @fxdfption HfbdlfssExdfption wifn
     *     {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    Window(GrbpiidsConfigurbtion gd) {
        init(gd);
    }

    trbnsifnt Objfdt bndior = nfw Objfdt();
    stbtid dlbss WindowDisposfrRfdord implfmfnts sun.jbvb2d.DisposfrRfdord {
        WfbkRfffrfndf<Window> ownfr;
        finbl WfbkRfffrfndf<Window> wfbkTiis;
        finbl WfbkRfffrfndf<AppContfxt> dontfxt;

        WindowDisposfrRfdord(AppContfxt dontfxt, Window vidtim) {
            wfbkTiis = vidtim.wfbkTiis;
            tiis.dontfxt = nfw WfbkRfffrfndf<AppContfxt>(dontfxt);
        }

        publid void updbtfOwnfr() {
            Window vidtim = wfbkTiis.gft();
            ownfr = (vidtim == null)
                    ? null
                    : nfw WfbkRfffrfndf<Window>(vidtim.gftOwnfr());
        }

        publid void disposf() {
            if (ownfr != null) {
                Window pbrfnt = ownfr.gft();
                if (pbrfnt != null) {
                    pbrfnt.rfmovfOwnfdWindow(wfbkTiis);
                }
            }
            AppContfxt bd = dontfxt.gft();
            if (null != bd) {
                Window.rfmovfFromWindowList(bd, wfbkTiis);
            }
        }
    }

    privbtf GrbpiidsConfigurbtion initGC(GrbpiidsConfigurbtion gd) {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (gd == null) {
            gd = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
                gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
        }
        sftGrbpiidsConfigurbtion(gd);

        rfturn gd;
    }

    privbtf void init(GrbpiidsConfigurbtion gd) {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        syndLWRfqufsts = systfmSyndLWRfqufsts;

        wfbkTiis = nfw WfbkRfffrfndf<Window>(tiis);
        bddToWindowList();

        sftWbrningString();
        tiis.dursor = Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
        tiis.visiblf = fblsf;

        gd = initGC(gd);

        if (gd.gftDfvidf().gftTypf() !=
            GrbpiidsDfvidf.TYPE_RASTER_SCREEN) {
            tirow nfw IllfgblArgumfntExdfption("not b sdrffn dfvidf");
        }
        sftLbyout(nfw BordfrLbyout());

        /* offsft tif initibl lodbtion witi tif originbl of tif sdrffn */
        /* bnd bny insfts                                              */
        Rfdtbnglf sdrffnBounds = gd.gftBounds();
        Insfts sdrffnInsfts = gftToolkit().gftSdrffnInsfts(gd);
        int x = gftX() + sdrffnBounds.x + sdrffnInsfts.lfft;
        int y = gftY() + sdrffnBounds.y + sdrffnInsfts.top;
        if (x != tiis.x || y != tiis.y) {
            sftLodbtion(x, y);
            /* rfsft bftfr sftLodbtion */
            sftLodbtionByPlbtform(lodbtionByPlbtformProp);
        }

        modblExdlusionTypf = Diblog.ModblExdlusionTypf.NO_EXCLUDE;
        disposfrRfdord = nfw WindowDisposfrRfdord(bppContfxt, tiis);
        sun.jbvb2d.Disposfr.bddRfdord(bndior, disposfrRfdord);

        SunToolkit.difdkAndSftPolidy(tiis);
    }

    /**
     * Construdts b nfw, initiblly invisiblf window in tif dffbult sizf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, it is invokfd to difdk
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * If tibt difdk fbils witi b {@dodf SfdurityExdfption} tifn b wbrning
     * bbnnfr is drfbtfd.
     *
     * @fxdfption HfbdlfssExdfption wifn
     *     {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns {@dodf truf}
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    Window() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        init((GrbpiidsConfigurbtion)null);
    }

    /**
     * Construdts b nfw, initiblly invisiblf window witi tif spfdififd
     * {@dodf Frbmf} bs its ownfr. Tif window will not bf fodusbblf
     * unlfss its ownfr is siowing on tif sdrffn.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, it is invokfd to difdk
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * If tibt difdk fbils witi b {@dodf SfdurityExdfption} tifn b wbrning
     * bbnnfr is drfbtfd.
     *
     * @pbrbm ownfr tif {@dodf Frbmf} to bdt bs ownfr or {@dodf null}
     *    if tiis window ibs no ownfr
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf ownfr}'s
     *    {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @fxdfption HfbdlfssExdfption wifn
     *    {@dodf GrbpiidsEnvironmfnt.isHfbdlfss} rfturns {@dodf truf}
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #isSiowing
     */
    publid Window(Frbmf ownfr) {
        tiis(ownfr == null ? (GrbpiidsConfigurbtion)null :
            ownfr.gftGrbpiidsConfigurbtion());
        ownfdInit(ownfr);
    }

    /**
     * Construdts b nfw, initiblly invisiblf window witi tif spfdififd
     * {@dodf Window} bs its ownfr. Tiis window will not bf fodusbblf
     * unlfss its nfbrfst owning {@dodf Frbmf} or {@dodf Diblog}
     * is siowing on tif sdrffn.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, it is invokfd to difdk
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * If tibt difdk fbils witi b {@dodf SfdurityExdfption} tifn b
     * wbrning bbnnfr is drfbtfd.
     *
     * @pbrbm ownfr tif {@dodf Window} to bdt bs ownfr or
     *     {@dodf null} if tiis window ibs no ownfr
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf ownfr}'s
     *     {@dodf GrbpiidsConfigurbtion} is not from b sdrffn dfvidf
     * @fxdfption HfbdlfssExdfption wifn
     *     {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns
     *     {@dodf truf}
     *
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       #isSiowing
     *
     * @sindf     1.2
     */
    publid Window(Window ownfr) {
        tiis(ownfr == null ? (GrbpiidsConfigurbtion)null :
            ownfr.gftGrbpiidsConfigurbtion());
        ownfdInit(ownfr);
    }

    /**
     * Construdts b nfw, initiblly invisiblf window witi tif spfdififd ownfr
     * {@dodf Window} bnd b {@dodf GrbpiidsConfigurbtion}
     * of b sdrffn dfvidf. Tif Window will not bf fodusbblf unlfss
     * its nfbrfst owning {@dodf Frbmf} or {@dodf Diblog}
     * is siowing on tif sdrffn.
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, it is invokfd to difdk
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}. If tibt
     * difdk fbils witi b {@dodf SfdurityExdfption} tifn b wbrning bbnnfr
     * is drfbtfd.
     *
     * @pbrbm ownfr tif window to bdt bs ownfr or {@dodf null}
     *     if tiis window ibs no ownfr
     * @pbrbm gd tif {@dodf GrbpiidsConfigurbtion} of tif tbrgft
     *     sdrffn dfvidf; if {@dodf gd} is {@dodf null},
     *     tif systfm dffbult {@dodf GrbpiidsConfigurbtion} is bssumfd
     * @fxdfption IllfgblArgumfntExdfption if {@dodf gd}
     *     is not from b sdrffn dfvidf
     * @fxdfption HfbdlfssExdfption wifn
     *     {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()} rfturns
     *     {@dodf truf}
     *
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       GrbpiidsConfigurbtion#gftBounds
     * @sff       #isSiowing
     * @sindf     1.3
     */
    publid Window(Window ownfr, GrbpiidsConfigurbtion gd) {
        tiis(gd);
        ownfdInit(ownfr);
    }

    privbtf void ownfdInit(Window ownfr) {
        tiis.pbrfnt = ownfr;
        if (ownfr != null) {
            ownfr.bddOwnfdWindow(wfbkTiis);
            if (ownfr.isAlwbysOnTop()) {
                try {
                    sftAlwbysOnTop(truf);
                } dbtdi (SfdurityExdfption ignorf) {
                }
            }
        }

        // WindowDisposfrRfdord rfquirfs b propfr vbluf of pbrfnt fifld.
        disposfrRfdord.updbtfOwnfr();
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Window.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Rfturns tif sfqufndf of imbgfs to bf displbyfd bs tif idon for tiis window.
     * <p>
     * Tiis mftiod rfturns b dopy of tif intfrnblly storfd list, so bll opfrbtions
     * on tif rfturnfd objfdt will not bfffdt tif window's bfibvior.
     *
     * @rfturn    tif dopy of idon imbgfs' list for tiis window, or
     *            fmpty list if tiis window dofsn't ibvf idon imbgfs.
     * @sff       #sftIdonImbgfs
     * @sff       #sftIdonImbgf(Imbgf)
     * @sindf     1.6
     */
    publid jbvb.util.List<Imbgf> gftIdonImbgfs() {
        jbvb.util.List<Imbgf> idons = tiis.idons;
        if (idons == null || idons.sizf() == 0) {
            rfturn nfw ArrbyList<Imbgf>();
        }
        rfturn nfw ArrbyList<Imbgf>(idons);
    }

    /**
     * Sfts tif sfqufndf of imbgfs to bf displbyfd bs tif idon
     * for tiis window. Subsfqufnt dblls to {@dodf gftIdonImbgfs} will
     * blwbys rfturn b dopy of tif {@dodf idons} list.
     * <p>
     * Dfpfnding on tif plbtform dbpbbilitifs onf or sfvfrbl imbgfs
     * of difffrfnt dimfnsions will bf usfd bs tif window's idon.
     * <p>
     * Tif {@dodf idons} list is sdbnnfd for tif imbgfs of most
     * bppropribtf dimfnsions from tif bfginning. If tif list dontbins
     * sfvfrbl imbgfs of tif sbmf sizf, tif first will bf usfd.
     * <p>
     * Ownfrlfss windows witi no idon spfdififd usf plbtfrom-dffbult idon.
     * Tif idon of bn ownfd window mby bf inifritfd from tif ownfr
     * unlfss fxpliditly ovfrriddfn.
     * Sftting tif idon to {@dodf null} or fmpty list rfstorfs
     * tif dffbult bfibvior.
     * <p>
     * Notf : Nbtivf windowing systfms mby usf difffrfnt imbgfs of difffring
     * dimfnsions to rfprfsfnt b window, dfpfnding on tif dontfxt (f.g.
     * window dfdorbtion, window list, tbskbbr, ftd.). Tify dould blso usf
     * just b singlf imbgf for bll dontfxts or no imbgf bt bll.
     *
     * @pbrbm     idons tif list of idon imbgfs to bf displbyfd.
     * @sff       #gftIdonImbgfs()
     * @sff       #sftIdonImbgf(Imbgf)
     * @sindf     1.6
     */
    publid syndironizfd void sftIdonImbgfs(jbvb.util.List<? fxtfnds Imbgf> idons) {
        tiis.idons = (idons == null) ? nfw ArrbyList<Imbgf>() :
            nfw ArrbyList<Imbgf>(idons);
        WindowPffr pffr = (WindowPffr)tiis.pffr;
        if (pffr != null) {
            pffr.updbtfIdonImbgfs();
        }
        // Alwbys sfnd b propfrty dibngf fvfnt
        firfPropfrtyCibngf("idonImbgf", null, null);
    }

    /**
     * Sfts tif imbgf to bf displbyfd bs tif idon for tiis window.
     * <p>
     * Tiis mftiod dbn bf usfd instfbd of {@link #sftIdonImbgfs sftIdonImbgfs()}
     * to spfdify b singlf imbgf bs b window's idon.
     * <p>
     * Tif following stbtfmfnt:
     * <prf>
     *     sftIdonImbgf(imbgf);
     * </prf>
     * is fquivblfnt to:
     * <prf>
     *     ArrbyList&lt;Imbgf&gt; imbgfList = nfw ArrbyList&lt;Imbgf&gt;();
     *     imbgfList.bdd(imbgf);
     *     sftIdonImbgfs(imbgfList);
     * </prf>
     * <p>
     * Notf : Nbtivf windowing systfms mby usf difffrfnt imbgfs of difffring
     * dimfnsions to rfprfsfnt b window, dfpfnding on tif dontfxt (f.g.
     * window dfdorbtion, window list, tbskbbr, ftd.). Tify dould blso usf
     * just b singlf imbgf for bll dontfxts or no imbgf bt bll.
     *
     * @pbrbm     imbgf tif idon imbgf to bf displbyfd.
     * @sff       #sftIdonImbgfs
     * @sff       #gftIdonImbgfs()
     * @sindf     1.6
     */
    publid void sftIdonImbgf(Imbgf imbgf) {
        ArrbyList<Imbgf> imbgfList = nfw ArrbyList<Imbgf>();
        if (imbgf != null) {
            imbgfList.bdd(imbgf);
        }
        sftIdonImbgfs(imbgfList);
    }

    /**
     * Mbkfs tiis Window displbybblf by drfbting tif donnfdtion to its
     * nbtivf sdrffn rfsourdf.
     * Tiis mftiod is dbllfd intfrnblly by tif toolkit bnd siould
     * not bf dbllfd dirfdtly by progrbms.
     * @sff Componfnt#isDisplbybblf
     * @sff Contbinfr#rfmovfNotify
     * @sindf 1.0
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            Contbinfr pbrfnt = tiis.pbrfnt;
            if (pbrfnt != null && pbrfnt.gftPffr() == null) {
                pbrfnt.bddNotify();
            }
            if (pffr == null) {
                pffr = gftToolkit().drfbtfWindow(tiis);
            }
            syndironizfd (bllWindows) {
                bllWindows.bdd(tiis);
            }
            supfr.bddNotify();
        }
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            syndironizfd (bllWindows) {
                bllWindows.rfmovf(tiis);
            }
            supfr.rfmovfNotify();
        }
    }

    /**
     * Cbusfs tiis Window to bf sizfd to fit tif prfffrrfd sizf
     * bnd lbyouts of its subdomponfnts. Tif rfsulting widti bnd
     * ifigit of tif window brf butombtidblly fnlbrgfd if fitifr
     * of dimfnsions is lfss tibn tif minimum sizf bs spfdififd
     * by tif prfvious dbll to tif {@dodf sftMinimumSizf} mftiod.
     * <p>
     * If tif window bnd/or its ownfr brf not displbybblf yft,
     * boti of tifm brf mbdf displbybblf bfforf dbldulbting
     * tif prfffrrfd sizf. Tif Window is vblidbtfd bftfr its
     * sizf is bfing dbldulbtfd.
     *
     * @sff Componfnt#isDisplbybblf
     * @sff #sftMinimumSizf
     */
    publid void pbdk() {
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null && pbrfnt.gftPffr() == null) {
            pbrfnt.bddNotify();
        }
        if (pffr == null) {
            bddNotify();
        }
        Dimfnsion nfwSizf = gftPrfffrrfdSizf();
        if (pffr != null) {
            sftClifntSizf(nfwSizf.widti, nfwSizf.ifigit);
        }

        if(bfforfFirstSiow) {
            isPbdkfd = truf;
        }

        vblidbtfUndonditionblly();
    }

    /**
     * Sfts tif minimum sizf of tiis window to b donstbnt
     * vbluf.  Subsfqufnt dblls to {@dodf gftMinimumSizf}
     * will blwbys rfturn tiis vbluf. If durrfnt window's
     * sizf is lfss tibn {@dodf minimumSizf} tif sizf of tif
     * window is butombtidblly fnlbrgfd to ionor tif minimum sizf.
     * <p>
     * If tif {@dodf sftSizf} or {@dodf sftBounds} mftiods
     * brf dbllfd bftfrwbrds witi b widti or ifigit lfss tibn
     * tibt wbs spfdififd by tif {@dodf sftMinimumSizf} mftiod
     * tif window is butombtidblly fnlbrgfd to mfft
     * tif {@dodf minimumSizf} vbluf. Tif {@dodf minimumSizf}
     * vbluf blso bfffdts tif bfibviour of tif {@dodf pbdk} mftiod.
     * <p>
     * Tif dffbult bfibvior is rfstorfd by sftting tif minimum sizf
     * pbrbmftfr to tif {@dodf null} vbluf.
     * <p>
     * Rfsizing opfrbtion mby bf rfstridtfd if tif usfr trifs
     * to rfsizf window bflow tif {@dodf minimumSizf} vbluf.
     * Tiis bfibviour is plbtform-dfpfndfnt.
     *
     * @pbrbm minimumSizf tif nfw minimum sizf of tiis window
     * @sff Componfnt#sftMinimumSizf
     * @sff #gftMinimumSizf
     * @sff #isMinimumSizfSft
     * @sff #sftSizf(Dimfnsion)
     * @sff #pbdk
     * @sindf 1.6
     */
    publid void sftMinimumSizf(Dimfnsion minimumSizf) {
        syndironizfd (gftTrffLodk()) {
            supfr.sftMinimumSizf(minimumSizf);
            Dimfnsion sizf = gftSizf();
            if (isMinimumSizfSft()) {
                if (sizf.widti < minimumSizf.widti || sizf.ifigit < minimumSizf.ifigit) {
                    int nw = Mbti.mbx(widti, minimumSizf.widti);
                    int ni = Mbti.mbx(ifigit, minimumSizf.ifigit);
                    sftSizf(nw, ni);
                }
            }
            if (pffr != null) {
                ((WindowPffr)pffr).updbtfMinimumSizf();
            }
        }
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif {@dodf d.widti} bnd {@dodf d.ifigit} vblufs
     * brf butombtidblly fnlbrgfd if fitifr is lfss tibn
     * tif minimum sizf bs spfdififd by prfvious dbll to
     * {@dodf sftMinimumSizf}.
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     *
     * @sff #gftSizf
     * @sff #sftBounds
     * @sff #sftMinimumSizf
     * @sindf 1.6
     */
    publid void sftSizf(Dimfnsion d) {
        supfr.sftSizf(d);
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif {@dodf widti} bnd {@dodf ifigit} vblufs
     * brf butombtidblly fnlbrgfd if fitifr is lfss tibn
     * tif minimum sizf bs spfdififd by prfvious dbll to
     * {@dodf sftMinimumSizf}.
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     *
     * @sff #gftSizf
     * @sff #sftBounds
     * @sff #sftMinimumSizf
     * @sindf 1.6
     */
    publid void sftSizf(int widti, int ifigit) {
        supfr.sftSizf(widti, ifigit);
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     */
    @Ovfrridf
    publid void sftLodbtion(int x, int y) {
        supfr.sftLodbtion(x, y);
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     */
    @Ovfrridf
    publid void sftLodbtion(Point p) {
        supfr.sftLodbtion(p);
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by {@dodf sftBounds(int, int, int, int)}.
     */
    @Dfprfdbtfd
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        if (isMinimumSizfSft()) {
            Dimfnsion minSizf = gftMinimumSizf();
            if (widti < minSizf.widti) {
                widti = minSizf.widti;
            }
            if (ifigit < minSizf.ifigit) {
                ifigit = minSizf.ifigit;
            }
        }
        supfr.rfsibpf(x, y, widti, ifigit);
    }

    void sftClifntSizf(int w, int i) {
        syndironizfd (gftTrffLodk()) {
            sftBoundsOp(ComponfntPffr.SET_CLIENT_SIZE);
            sftBounds(x, y, w, i);
        }
    }

    stbtid privbtf finbl AtomidBoolfbn
        bfforfFirstWindowSiown = nfw AtomidBoolfbn(truf);

    finbl void dlosfSplbsiSdrffn() {
        if (isTrbyIdonWindow) {
            rfturn;
        }
        if (bfforfFirstWindowSiown.gftAndSft(fblsf)) {
            // Wf don't usf SplbsiSdrffn.gftSplbsiSdrffn() to bvoid instbntibting
            // tif objfdt if it ibsn't bffn rfqufstfd by usfr dodf fxpliditly
            SunToolkit.dlosfSplbsiSdrffn();
            SplbsiSdrffn.mbrkClosfd();
        }
    }

    /**
     * Siows or iidfs tiis {@dodf Window} dfpfnding on tif vbluf of pbrbmftfr
     * {@dodf b}.
     * <p>
     * If tif mftiod siows tif window tifn tif window is blso mbdf
     * fodusfd undfr tif following donditions:
     * <ul>
     * <li> Tif {@dodf Window} mffts tif rfquirfmfnts outlinfd in tif
     *      {@link #isFodusbblfWindow} mftiod.
     * <li> Tif {@dodf Window}'s {@dodf butoRfqufstFodus} propfrty is of tif {@dodf truf} vbluf.
     * <li> Nbtivf windowing systfm bllows tif {@dodf Window} to gft fodusfd.
     * </ul>
     * Tifrf is bn fxdfption for tif sfdond dondition (tif vbluf of tif
     * {@dodf butoRfqufstFodus} propfrty). Tif propfrty is not tbkfn into bddount if tif
     * window is b modbl diblog, wiidi blodks tif durrfntly fodusfd window.
     * <p>
     * Dfvflopfrs must nfvfr bssumf tibt tif window is tif fodusfd or bdtivf window
     * until it rfdfivfs b WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED fvfnt.
     * @pbrbm b  if {@dodf truf}, mbkfs tif {@dodf Window} visiblf,
     * otifrwisf iidfs tif {@dodf Window}.
     * If tif {@dodf Window} bnd/or its ownfr
     * brf not yft displbybblf, boti brf mbdf displbybblf.  Tif
     * {@dodf Window} will bf vblidbtfd prior to bfing mbdf visiblf.
     * If tif {@dodf Window} is blrfbdy visiblf, tiis will bring tif
     * {@dodf Window} to tif front.<p>
     * If {@dodf fblsf}, iidfs tiis {@dodf Window}, its subdomponfnts, bnd bll
     * of its ownfd diildrfn.
     * Tif {@dodf Window} bnd its subdomponfnts dbn bf mbdf visiblf bgbin
     * witi b dbll to {@dodf #sftVisiblf(truf)}.
     * @sff jbvb.bwt.Componfnt#isDisplbybblf
     * @sff jbvb.bwt.Componfnt#sftVisiblf
     * @sff jbvb.bwt.Window#toFront
     * @sff jbvb.bwt.Window#disposf
     * @sff jbvb.bwt.Window#sftAutoRfqufstFodus
     * @sff jbvb.bwt.Window#isFodusbblfWindow
     */
    publid void sftVisiblf(boolfbn b) {
        supfr.sftVisiblf(b);
    }

    /**
     * Mbkfs tif Window visiblf. If tif Window bnd/or its ownfr
     * brf not yft displbybblf, boti brf mbdf displbybblf.  Tif
     * Window will bf vblidbtfd prior to bfing mbdf visiblf.
     * If tif Window is blrfbdy visiblf, tiis will bring tif Window
     * to tif front.
     * @sff       Componfnt#isDisplbybblf
     * @sff       #toFront
     * @dfprfdbtfd As of JDK vfrsion 1.5, rfplbdfd by
     * {@link #sftVisiblf(boolfbn)}.
     */
    @Dfprfdbtfd
    publid void siow() {
        if (pffr == null) {
            bddNotify();
        }
        vblidbtfUndonditionblly();

        isInSiow = truf;
        if (visiblf) {
            toFront();
        } flsf {
            bfforfFirstSiow = fblsf;
            dlosfSplbsiSdrffn();
            Diblog.difdkSiouldBfBlodkfd(tiis);
            supfr.siow();
            syndironizfd (gftTrffLodk()) {
                tiis.lodbtionByPlbtform = fblsf;
            }
            for (int i = 0; i < ownfdWindowList.sizf(); i++) {
                Window diild = ownfdWindowList.flfmfntAt(i).gft();
                if ((diild != null) && diild.siowWitiPbrfnt) {
                    diild.siow();
                    diild.siowWitiPbrfnt = fblsf;
                }       // fndif
            }   // fndfor
            if (!isModblBlodkfd()) {
                updbtfCiildrfnBlodking();
            } flsf {
                // fix for 6532736: bftfr tiis window is siown, its blodkfr
                // siould bf rbisfd to front
                modblBlodkfr.toFront_NoClifntCodf();
            }
            if (tiis instbndfof Frbmf || tiis instbndfof Diblog) {
                updbtfCiildFodusbblfWindowStbtf(tiis);
            }
        }
        isInSiow = fblsf;

        // If first timf siown, gfnfrbtf WindowOpfnfd fvfnt
        if ((stbtf & OPENED) == 0) {
            postWindowEvfnt(WindowEvfnt.WINDOW_OPENED);
            stbtf |= OPENED;
        }
    }

    stbtid void updbtfCiildFodusbblfWindowStbtf(Window w) {
        if (w.gftPffr() != null && w.isSiowing()) {
            ((WindowPffr)w.gftPffr()).updbtfFodusbblfWindowStbtf();
        }
        for (int i = 0; i < w.ownfdWindowList.sizf(); i++) {
            Window diild = w.ownfdWindowList.flfmfntAt(i).gft();
            if (diild != null) {
                updbtfCiildFodusbblfWindowStbtf(diild);
            }
        }
    }

    syndironizfd void postWindowEvfnt(int id) {
        if (windowListfnfr != null
            || (fvfntMbsk & AWTEvfnt.WINDOW_EVENT_MASK) != 0
            ||  Toolkit.fnbblfdOnToolkit(AWTEvfnt.WINDOW_EVENT_MASK)) {
            WindowEvfnt f = nfw WindowEvfnt(tiis, id);
            Toolkit.gftEvfntQufuf().postEvfnt(f);
        }
    }

    /**
     * Hidf tiis Window, its subdomponfnts, bnd bll of its ownfd diildrfn.
     * Tif Window bnd its subdomponfnts dbn bf mbdf visiblf bgbin
     * witi b dbll to {@dodf siow}.
     * @sff #siow
     * @sff #disposf
     * @dfprfdbtfd As of JDK vfrsion 1.5, rfplbdfd by
     * {@link #sftVisiblf(boolfbn)}.
     */
    @Dfprfdbtfd
    publid void iidf() {
        syndironizfd(ownfdWindowList) {
            for (int i = 0; i < ownfdWindowList.sizf(); i++) {
                Window diild = ownfdWindowList.flfmfntAt(i).gft();
                if ((diild != null) && diild.visiblf) {
                    diild.iidf();
                    diild.siowWitiPbrfnt = truf;
                }
            }
        }
        if (isModblBlodkfd()) {
            modblBlodkfr.unblodkWindow(tiis);
        }
        supfr.iidf();
        syndironizfd (gftTrffLodk()) {
            tiis.lodbtionByPlbtform = fblsf;
        }
    }

    finbl void dlfbrMostRfdfntFodusOwnfrOnHidf() {
        /* do notiing */
    }

    /**
     * Rflfbsfs bll of tif nbtivf sdrffn rfsourdfs usfd by tiis
     * {@dodf Window}, its subdomponfnts, bnd bll of its ownfd
     * diildrfn. Tibt is, tif rfsourdfs for tifsf {@dodf Componfnt}s
     * will bf dfstroyfd, bny mfmory tify donsumf will bf rfturnfd to tif
     * OS, bnd tify will bf mbrkfd bs undisplbybblf.
     * <p>
     * Tif {@dodf Window} bnd its subdomponfnts dbn bf mbdf displbybblf
     * bgbin by rfbuilding tif nbtivf rfsourdfs witi b subsfqufnt dbll to
     * {@dodf pbdk} or {@dodf siow}. Tif stbtfs of tif rfdrfbtfd
     * {@dodf Window} bnd its subdomponfnts will bf idfntidbl to tif
     * stbtfs of tifsf objfdts bt tif point wifrf tif {@dodf Window}
     * wbs disposfd (not bddounting for bdditionbl modifidbtions bftwffn
     * tiosf bdtions).
     * <p>
     * <b>Notf</b>: Wifn tif lbst displbybblf window
     * witiin tif Jbvb virtubl mbdiinf (VM) is disposfd of, tif VM mby
     * tfrminbtf.  Sff <b irff="dod-filfs/AWTTirfbdIssufs.itml#Autosiutdown">
     * AWT Tirfbding Issufs</b> for morf informbtion.
     * @sff Componfnt#isDisplbybblf
     * @sff #pbdk
     * @sff #siow
     */
    publid void disposf() {
        doDisposf();
    }

    /*
     * Fix for 4872170.
     * If disposf() is dbllfd on pbrfnt tifn its diildrfn ibvf to bf disposfd bs wfll
     * bs rfportfd in jbvbdod. So wf nffd to implfmfnt tiis fundtionblity fvfn if b
     * diild ovfrridfs disposf() in b wrong wby witiout dblling supfr.disposf().
     */
    void disposfImpl() {
        disposf();
        if (gftPffr() != null) {
            doDisposf();
        }
    }

    void doDisposf() {
    dlbss DisposfAdtion implfmfnts Runnbblf {
        publid void run() {
            disposing = truf;
            try {
                // Cifdk if tiis window is tif fullsdrffn window for tif
                // dfvidf. Exit tif fullsdrffn modf prior to disposing
                // of tif window if tibt's tif dbsf.
                GrbpiidsDfvidf gd = gftGrbpiidsConfigurbtion().gftDfvidf();
                if (gd.gftFullSdrffnWindow() == Window.tiis) {
                    gd.sftFullSdrffnWindow(null);
                }

                Objfdt[] ownfdWindowArrby;
                syndironizfd(ownfdWindowList) {
                    ownfdWindowArrby = nfw Objfdt[ownfdWindowList.sizf()];
                    ownfdWindowList.dopyInto(ownfdWindowArrby);
                }
                for (int i = 0; i < ownfdWindowArrby.lfngti; i++) {
                    Window diild = (Window) (((WfbkRfffrfndf)
                                   (ownfdWindowArrby[i])).gft());
                    if (diild != null) {
                        diild.disposfImpl();
                    }
                }
                iidf();
                bfforfFirstSiow = truf;
                rfmovfNotify();
                syndironizfd (inputContfxtLodk) {
                    if (inputContfxt != null) {
                        inputContfxt.disposf();
                        inputContfxt = null;
                    }
                }
                dlfbrCurrfntFodusCydlfRootOnHidf();
            } finblly {
                disposing = fblsf;
            }
        }
    }
        boolfbn firfWindowClosfdEvfnt = isDisplbybblf();
        DisposfAdtion bdtion = nfw DisposfAdtion();
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            bdtion.run();
        }
        flsf {
            try {
                EvfntQufuf.invokfAndWbit(tiis, bdtion);
            }
            dbtdi (IntfrruptfdExdfption f) {
                Systfm.frr.println("Disposbl wbs intfrruptfd:");
                f.printStbdkTrbdf();
            }
            dbtdi (InvodbtionTbrgftExdfption f) {
                Systfm.frr.println("Exdfption during disposbl:");
                f.printStbdkTrbdf();
            }
        }
        // Exfdutf outsidf tif Runnbblf bfdbusf postWindowEvfnt is
        // syndironizfd on (tiis). Wf don't nffd to syndironizf tif dbll
        // on tif EvfntQufuf bnywbys.
        if (firfWindowClosfdEvfnt) {
            postWindowEvfnt(WindowEvfnt.WINDOW_CLOSED);
        }
    }

    /*
     * Siould only bf dbllfd wiilf iolding tif trff lodk.
     * It's ovfrriddfn ifrf bfdbusf pbrfnt == ownfr in Window,
     * bnd wf siouldn't bdjust dountfr on ownfr
     */
    void bdjustListfningCiildrfnOnPbrfnt(long mbsk, int num) {
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    void bdjustDfdfndbntsOnPbrfnt(int num) {
        // do notiing sindf pbrfnt == ownfr bnd wf siouldn't
        // bjust dountfr on ownfr
    }

    /**
     * If tiis Window is visiblf, brings tiis Window to tif front bnd mby mbkf
     * it tif fodusfd Window.
     * <p>
     * Plbdfs tiis Window bt tif top of tif stbdking ordfr bnd siows it in
     * front of bny otifr Windows in tiis VM. No bdtion will tbkf plbdf if tiis
     * Window is not visiblf. Somf plbtforms do not bllow Windows wiidi own
     * otifr Windows to bppfbr on top of tiosf ownfd Windows. Somf plbtforms
     * mby not pfrmit tiis VM to plbdf its Windows bbovf windows of nbtivf
     * bpplidbtions, or Windows of otifr VMs. Tiis pfrmission mby dfpfnd on
     * wiftifr b Window in tiis VM is blrfbdy fodusfd. Evfry bttfmpt will bf
     * mbdf to movf tiis Window bs iigi bs possiblf in tif stbdking ordfr;
     * iowfvfr, dfvflopfrs siould not bssumf tibt tiis mftiod will movf tiis
     * Window bbovf bll otifr windows in fvfry situbtion.
     * <p>
     * Dfvflopfrs must nfvfr bssumf tibt tiis Window is tif fodusfd or bdtivf
     * Window until tiis Window rfdfivfs b WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED
     * fvfnt. On plbtforms wifrf tif top-most window is tif fodusfd window, tiis
     * mftiod will <b>probbbly</b> fodus tiis Window (if it is not blrfbdy fodusfd)
     * undfr tif following donditions:
     * <ul>
     * <li> Tif window mffts tif rfquirfmfnts outlinfd in tif
     *      {@link #isFodusbblfWindow} mftiod.
     * <li> Tif window's propfrty {@dodf butoRfqufstFodus} is of tif
     *      {@dodf truf} vbluf.
     * <li> Nbtivf windowing systfm bllows tif window to gft fodusfd.
     * </ul>
     * On plbtforms wifrf tif stbdking ordfr dofs not typidblly bfffdt tif fodusfd
     * window, tiis mftiod will <b>probbbly</b> lfbvf tif fodusfd bnd bdtivf
     * Windows undibngfd.
     * <p>
     * If tiis mftiod dbusfs tiis Window to bf fodusfd, bnd tiis Window is b
     * Frbmf or b Diblog, it will blso bfdomf bdtivbtfd. If tiis Window is
     * fodusfd, but it is not b Frbmf or b Diblog, tifn tif first Frbmf or
     * Diblog tibt is bn ownfr of tiis Window will bf bdtivbtfd.
     * <p>
     * If tiis window is blodkfd by modbl diblog, tifn tif blodking diblog
     * is brougit to tif front bnd rfmbins bbovf tif blodkfd window.
     *
     * @sff       #toBbdk
     * @sff       #sftAutoRfqufstFodus
     * @sff       #isFodusbblfWindow
     */
    publid void toFront() {
        toFront_NoClifntCodf();
    }

    // Tiis fundtionblity is implfmfntfd in b finbl pbdkbgf-privbtf mftiod
    // to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    finbl void toFront_NoClifntCodf() {
        if (visiblf) {
            WindowPffr pffr = (WindowPffr)tiis.pffr;
            if (pffr != null) {
                pffr.toFront();
            }
            if (isModblBlodkfd()) {
                modblBlodkfr.toFront_NoClifntCodf();
            }
        }
    }

    /**
     * If tiis Window is visiblf, sfnds tiis Window to tif bbdk bnd mby dbusf
     * it to losf fodus or bdtivbtion if it is tif fodusfd or bdtivf Window.
     * <p>
     * Plbdfs tiis Window bt tif bottom of tif stbdking ordfr bnd siows it
     * bfiind bny otifr Windows in tiis VM. No bdtion will tbkf plbdf is tiis
     * Window is not visiblf. Somf plbtforms do not bllow Windows wiidi brf
     * ownfd by otifr Windows to bppfbr bflow tifir ownfrs. Evfry bttfmpt will
     * bf mbdf to movf tiis Window bs low bs possiblf in tif stbdking ordfr;
     * iowfvfr, dfvflopfrs siould not bssumf tibt tiis mftiod will movf tiis
     * Window bflow bll otifr windows in fvfry situbtion.
     * <p>
     * Bfdbusf of vbribtions in nbtivf windowing systfms, no gubrbntffs bbout
     * dibngfs to tif fodusfd bnd bdtivf Windows dbn bf mbdf. Dfvflopfrs must
     * nfvfr bssumf tibt tiis Window is no longfr tif fodusfd or bdtivf Window
     * until tiis Window rfdfivfs b WINDOW_LOST_FOCUS or WINDOW_DEACTIVATED
     * fvfnt. On plbtforms wifrf tif top-most window is tif fodusfd window,
     * tiis mftiod will <b>probbbly</b> dbusf tiis Window to losf fodus. In
     * tibt dbsf, tif nfxt iigifst, fodusbblf Window in tiis VM will rfdfivf
     * fodus. On plbtforms wifrf tif stbdking ordfr dofs not typidblly bfffdt
     * tif fodusfd window, tiis mftiod will <b>probbbly</b> lfbvf tif fodusfd
     * bnd bdtivf Windows undibngfd.
     *
     * @sff       #toFront
     */
    publid void toBbdk() {
        toBbdk_NoClifntCodf();
    }

    // Tiis fundtionblity is implfmfntfd in b finbl pbdkbgf-privbtf mftiod
    // to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    finbl void toBbdk_NoClifntCodf() {
        if(isAlwbysOnTop()) {
            try {
                sftAlwbysOnTop(fblsf);
            }dbtdi(SfdurityExdfption f) {
            }
        }
        if (visiblf) {
            WindowPffr pffr = (WindowPffr)tiis.pffr;
            if (pffr != null) {
                pffr.toBbdk();
            }
        }
    }

    /**
     * Rfturns tif toolkit of tiis frbmf.
     * @rfturn    tif toolkit of tiis window.
     * @sff       Toolkit
     * @sff       Toolkit#gftDffbultToolkit
     * @sff       Componfnt#gftToolkit
     */
    publid Toolkit gftToolkit() {
        rfturn Toolkit.gftDffbultToolkit();
    }

    /**
     * Gfts tif wbrning string tibt is displbyfd witi tiis window.
     * If tiis window is insfdurf, tif wbrning string is displbyfd
     * somfwifrf in tif visiblf brfb of tif window. A window is
     * insfdurf if tifrf is b sfdurity mbnbgfr bnd tif sfdurity
     * mbnbgfr dfnifs
     * {@dodf AWTPfrmission("siowWindowWitioutWbrningBbnnfr")}.
     * <p>
     * If tif window is sfdurf, tifn {@dodf gftWbrningString}
     * rfturns {@dodf null}. If tif window is insfdurf, tiis
     * mftiod difdks for tif systfm propfrty
     * {@dodf bwt.bpplftWbrning}
     * bnd rfturns tif string vbluf of tibt propfrty.
     * @rfturn    tif wbrning string for tiis window.
     */
    publid finbl String gftWbrningString() {
        rfturn wbrningString;
    }

    privbtf void sftWbrningString() {
        wbrningString = null;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            try {
                sm.difdkPfrmission(AWTPfrmissions.TOPLEVEL_WINDOW_PERMISSION);
            } dbtdi (SfdurityExdfption sf) {
                // mbkf surf tif privilfgfd bdtion is only
                // for gftting tif propfrty! Wf don't wbnt tif
                // bbovf difdkPfrmission dbll to blwbys suddffd!
                wbrningString = AddfssControllfr.doPrivilfgfd(
                      nfw GftPropfrtyAdtion("bwt.bpplftWbrning",
                                            "Jbvb Applft Window"));
            }
        }
    }

    /**
     * Gfts tif {@dodf Lodblf} objfdt tibt is bssodibtfd
     * witi tiis window, if tif lodblf ibs bffn sft.
     * If no lodblf ibs bffn sft, tifn tif dffbult lodblf
     * is rfturnfd.
     * @rfturn    tif lodblf tibt is sft for tiis window.
     * @sff       jbvb.util.Lodblf
     * @sindf     1.1
     */
    publid Lodblf gftLodblf() {
      if (tiis.lodblf == null) {
        rfturn Lodblf.gftDffbult();
      }
      rfturn tiis.lodblf;
    }

    /**
     * Gfts tif input dontfxt for tiis window. A window blwbys ibs bn input dontfxt,
     * wiidi is sibrfd by subdomponfnts unlfss tify drfbtf bnd sft tifir own.
     * @sff Componfnt#gftInputContfxt
     * @sindf 1.2
     */
    publid InputContfxt gftInputContfxt() {
        syndironizfd (inputContfxtLodk) {
            if (inputContfxt == null) {
                inputContfxt = InputContfxt.gftInstbndf();
            }
        }
        rfturn inputContfxt;
    }

    /**
     * Sft tif dursor imbgf to b spfdififd dursor.
     * <p>
     * Tif mftiod mby ibvf no visubl ffffdt if tif Jbvb plbtform
     * implfmfntbtion bnd/or tif nbtivf systfm do not support
     * dibnging tif mousf dursor sibpf.
     * @pbrbm     dursor Onf of tif donstbnts dffinfd
     *            by tif {@dodf Cursor} dlbss. If tiis pbrbmftfr is null
     *            tifn tif dursor for tiis window will bf sft to tif typf
     *            Cursor.DEFAULT_CURSOR.
     * @sff       Componfnt#gftCursor
     * @sff       Cursor
     * @sindf     1.1
     */
    publid void sftCursor(Cursor dursor) {
        if (dursor == null) {
            dursor = Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
        }
        supfr.sftCursor(dursor);
    }

    /**
     * Rfturns tif ownfr of tiis window.
     *
     * @rfturn tif ownfr of tiis window
     * @sindf 1.2
     */
    publid Window gftOwnfr() {
        rfturn gftOwnfr_NoClifntCodf();
    }
    finbl Window gftOwnfr_NoClifntCodf() {
        rfturn (Window)pbrfnt;
    }

    /**
     * Rfturn bn brrby dontbining bll tif windows tiis
     * window durrfntly owns.
     *
     * @rfturn tif brrby of bll tif ownfd windows
     * @sindf 1.2
     */
    publid Window[] gftOwnfdWindows() {
        rfturn gftOwnfdWindows_NoClifntCodf();
    }
    finbl Window[] gftOwnfdWindows_NoClifntCodf() {
        Window rfblCopy[];

        syndironizfd(ownfdWindowList) {
            // Rfdbll tibt ownfdWindowList is bdtublly b Vfdtor of
            // WfbkRfffrfndfs bnd dblling gft() on onf of tifsf rfffrfndfs
            // mby rfturn null. Mbkf two brrbys-- onf tif sizf of tif
            // Vfdtor (fullCopy witi sizf fullSizf), bnd onf tif sizf of
            // bll non-null gft()s (rfblCopy witi sizf rfblSizf).
            int fullSizf = ownfdWindowList.sizf();
            int rfblSizf = 0;
            Window fullCopy[] = nfw Window[fullSizf];

            for (int i = 0; i < fullSizf; i++) {
                fullCopy[rfblSizf] = ownfdWindowList.flfmfntAt(i).gft();

                if (fullCopy[rfblSizf] != null) {
                    rfblSizf++;
                }
            }

            if (fullSizf != rfblSizf) {
                rfblCopy = Arrbys.dopyOf(fullCopy, rfblSizf);
            } flsf {
                rfblCopy = fullCopy;
            }
        }

        rfturn rfblCopy;
    }

    boolfbn isModblBlodkfd() {
        rfturn modblBlodkfr != null;
    }

    void sftModblBlodkfd(Diblog blodkfr, boolfbn blodkfd, boolfbn pffrCbll) {
        tiis.modblBlodkfr = blodkfd ? blodkfr : null;
        if (pffrCbll) {
            WindowPffr pffr = (WindowPffr)tiis.pffr;
            if (pffr != null) {
                pffr.sftModblBlodkfd(blodkfr, blodkfd);
            }
        }
    }

    Diblog gftModblBlodkfr() {
        rfturn modblBlodkfr;
    }

    /*
     * Rfturns b list of bll displbybblf Windows, i. f. bll tif
     * Windows wiidi pffr is not null.
     *
     * @sff #bddNotify
     * @sff #rfmovfNotify
     */
    stbtid IdfntityArrbyList<Window> gftAllWindows() {
        syndironizfd (bllWindows) {
            IdfntityArrbyList<Window> v = nfw IdfntityArrbyList<Window>();
            v.bddAll(bllWindows);
            rfturn v;
        }
    }

    stbtid IdfntityArrbyList<Window> gftAllUnblodkfdWindows() {
        syndironizfd (bllWindows) {
            IdfntityArrbyList<Window> unblodkfd = nfw IdfntityArrbyList<Window>();
            for (int i = 0; i < bllWindows.sizf(); i++) {
                Window w = bllWindows.gft(i);
                if (!w.isModblBlodkfd()) {
                    unblodkfd.bdd(w);
                }
            }
            rfturn unblodkfd;
        }
    }

    privbtf stbtid Window[] gftWindows(AppContfxt bppContfxt) {
        syndironizfd (Window.dlbss) {
            Window rfblCopy[];
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<WfbkRfffrfndf<Window>> windowList =
                (Vfdtor<WfbkRfffrfndf<Window>>)bppContfxt.gft(Window.dlbss);
            if (windowList != null) {
                int fullSizf = windowList.sizf();
                int rfblSizf = 0;
                Window fullCopy[] = nfw Window[fullSizf];
                for (int i = 0; i < fullSizf; i++) {
                    Window w = windowList.gft(i).gft();
                    if (w != null) {
                        fullCopy[rfblSizf++] = w;
                    }
                }
                if (fullSizf != rfblSizf) {
                    rfblCopy = Arrbys.dopyOf(fullCopy, rfblSizf);
                } flsf {
                    rfblCopy = fullCopy;
                }
            } flsf {
                rfblCopy = nfw Window[0];
            }
            rfturn rfblCopy;
        }
    }

    /**
     * Rfturns bn brrby of bll {@dodf Window}s, boti ownfd bnd ownfrlfss,
     * drfbtfd by tiis bpplidbtion.
     * If dbllfd from bn bpplft, tif brrby indludfs only tif {@dodf Window}s
     * bddfssiblf by tibt bpplft.
     * <p>
     * <b>Wbrning:</b> tiis mftiod mby rfturn systfm drfbtfd windows, sudi
     * bs b print diblog. Applidbtions siould not bssumf tif fxistfndf of
     * tifsf diblogs, nor siould bn bpplidbtion bssumf bnytiing bbout tifsf
     * diblogs sudi bs domponfnt positions, {@dodf LbyoutMbnbgfr}s
     * or sfriblizbtion.
     *
     * @rfturn tif brrby of bll tif {@dodf Window}s drfbtfd by tif bpplidbtion
     * @sff Frbmf#gftFrbmfs
     * @sff Window#gftOwnfrlfssWindows
     *
     * @sindf 1.6
     */
    publid stbtid Window[] gftWindows() {
        rfturn gftWindows(AppContfxt.gftAppContfxt());
    }

    /**
     * Rfturns bn brrby of bll {@dodf Window}s drfbtfd by tiis bpplidbtion
     * tibt ibvf no ownfr. Tify indludf {@dodf Frbmf}s bnd ownfrlfss
     * {@dodf Diblog}s bnd {@dodf Window}s.
     * If dbllfd from bn bpplft, tif brrby indludfs only tif {@dodf Window}s
     * bddfssiblf by tibt bpplft.
     * <p>
     * <b>Wbrning:</b> tiis mftiod mby rfturn systfm drfbtfd windows, sudi
     * bs b print diblog. Applidbtions siould not bssumf tif fxistfndf of
     * tifsf diblogs, nor siould bn bpplidbtion bssumf bnytiing bbout tifsf
     * diblogs sudi bs domponfnt positions, {@dodf LbyoutMbnbgfr}s
     * or sfriblizbtion.
     *
     * @rfturn tif brrby of bll tif ownfrlfss {@dodf Window}s
     *         drfbtfd by tiis bpplidbtion
     * @sff Frbmf#gftFrbmfs
     * @sff Window#gftWindows()
     *
     * @sindf 1.6
     */
    publid stbtid Window[] gftOwnfrlfssWindows() {
        Window[] bllWindows = Window.gftWindows();

        int ownfrlfssCount = 0;
        for (Window w : bllWindows) {
            if (w.gftOwnfr() == null) {
                ownfrlfssCount++;
            }
        }

        Window[] ownfrlfss = nfw Window[ownfrlfssCount];
        int d = 0;
        for (Window w : bllWindows) {
            if (w.gftOwnfr() == null) {
                ownfrlfss[d++] = w;
            }
        }

        rfturn ownfrlfss;
    }

    Window gftDodumfntRoot() {
        syndironizfd (gftTrffLodk()) {
            Window w = tiis;
            wiilf (w.gftOwnfr() != null) {
                w = w.gftOwnfr();
            }
            rfturn w;
        }
    }

    /**
     * Spfdififs tif modbl fxdlusion typf for tiis window. If b window is modbl
     * fxdludfd, it is not blodkfd by somf modbl diblogs. Sff {@link
     * jbvb.bwt.Diblog.ModblExdlusionTypf Diblog.ModblExdlusionTypf} for
     * possiblf modbl fxdlusion typfs.
     * <p>
     * If tif givfn typf is not supportfd, {@dodf NO_EXCLUDE} is usfd.
     * <p>
     * Notf: dibnging tif modbl fxdlusion typf for b visiblf window mby ibvf no
     * ffffdt until it is iiddfn bnd tifn siown bgbin.
     *
     * @pbrbm fxdlusionTypf tif modbl fxdlusion typf for tiis window; b {@dodf null}
     *     vbluf is fquivblfnt to {@link Diblog.ModblExdlusionTypf#NO_EXCLUDE
     *     NO_EXCLUDE}
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf pfrmission
     *     to sft tif modbl fxdlusion propfrty to tif window witi tif givfn
     *     {@dodf fxdlusionTypf}
     * @sff jbvb.bwt.Diblog.ModblExdlusionTypf
     * @sff jbvb.bwt.Window#gftModblExdlusionTypf
     * @sff jbvb.bwt.Toolkit#isModblExdlusionTypfSupportfd
     *
     * @sindf 1.6
     */
    publid void sftModblExdlusionTypf(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        if (fxdlusionTypf == null) {
            fxdlusionTypf = Diblog.ModblExdlusionTypf.NO_EXCLUDE;
        }
        if (!Toolkit.gftDffbultToolkit().isModblExdlusionTypfSupportfd(fxdlusionTypf)) {
            fxdlusionTypf = Diblog.ModblExdlusionTypf.NO_EXCLUDE;
        }
        if (modblExdlusionTypf == fxdlusionTypf) {
            rfturn;
        }
        if (fxdlusionTypf == Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkPfrmission(AWTPfrmissions.TOOLKIT_MODALITY_PERMISSION);
            }
        }
        modblExdlusionTypf = fxdlusionTypf;

        // if wf wbnt on-fly dibngfs, wf nffd to undommfnt tif linfs bflow
        //   bnd ovfrridf tif mftiod in Diblog to usf modblSiow() instfbd
        //   of updbtfCiildrfnBlodking()
 /*
        if (isModblBlodkfd()) {
            modblBlodkfr.unblodkWindow(tiis);
        }
        Diblog.difdkSiouldBfBlodkfd(tiis);
        updbtfCiildrfnBlodking();
 */
    }

    /**
     * Rfturns tif modbl fxdlusion typf of tiis window.
     *
     * @rfturn tif modbl fxdlusion typf of tiis window
     *
     * @sff jbvb.bwt.Diblog.ModblExdlusionTypf
     * @sff jbvb.bwt.Window#sftModblExdlusionTypf
     *
     * @sindf 1.6
     */
    publid Diblog.ModblExdlusionTypf gftModblExdlusionTypf() {
        rfturn modblExdlusionTypf;
    }

    boolfbn isModblExdludfd(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        if ((modblExdlusionTypf != null) &&
            modblExdlusionTypf.dompbrfTo(fxdlusionTypf) >= 0)
        {
            rfturn truf;
        }
        Window ownfr = gftOwnfr_NoClifntCodf();
        rfturn (ownfr != null) && ownfr.isModblExdludfd(fxdlusionTypf);
    }

    void updbtfCiildrfnBlodking() {
        Vfdtor<Window> diildHifrbrdiy = nfw Vfdtor<Window>();
        Window[] ownfdWindows = gftOwnfdWindows();
        for (int i = 0; i < ownfdWindows.lfngti; i++) {
            diildHifrbrdiy.bdd(ownfdWindows[i]);
        }
        int k = 0;
        wiilf (k < diildHifrbrdiy.sizf()) {
            Window w = diildHifrbrdiy.gft(k);
            if (w.isVisiblf()) {
                if (w.isModblBlodkfd()) {
                    Diblog blodkfr = w.gftModblBlodkfr();
                    blodkfr.unblodkWindow(w);
                }
                Diblog.difdkSiouldBfBlodkfd(w);
                Window[] wOwnfd = w.gftOwnfdWindows();
                for (int j = 0; j < wOwnfd.lfngti; j++) {
                    diildHifrbrdiy.bdd(wOwnfd[j]);
                }
            }
            k++;
        }
    }

    /**
     * Adds tif spfdififd window listfnfr to rfdfivf window fvfnts from
     * tiis window.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window listfnfr
     * @sff #rfmovfWindowListfnfr
     * @sff #gftWindowListfnfrs
     */
    publid syndironizfd void bddWindowListfnfr(WindowListfnfr l) {
        if (l == null) {
            rfturn;
        }
        nfwEvfntsOnly = truf;
        windowListfnfr = AWTEvfntMultidbstfr.bdd(windowListfnfr, l);
    }

    /**
     * Adds tif spfdififd window stbtf listfnfr to rfdfivf window
     * fvfnts from tiis window.  If {@dodf l} is {@dodf null},
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window stbtf listfnfr
     * @sff #rfmovfWindowStbtfListfnfr
     * @sff #gftWindowStbtfListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd void bddWindowStbtfListfnfr(WindowStbtfListfnfr l) {
        if (l == null) {
            rfturn;
        }
        windowStbtfListfnfr = AWTEvfntMultidbstfr.bdd(windowStbtfListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Adds tif spfdififd window fodus listfnfr to rfdfivf window fvfnts
     * from tiis window.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window fodus listfnfr
     * @sff #rfmovfWindowFodusListfnfr
     * @sff #gftWindowFodusListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd void bddWindowFodusListfnfr(WindowFodusListfnfr l) {
        if (l == null) {
            rfturn;
        }
        windowFodusListfnfr = AWTEvfntMultidbstfr.bdd(windowFodusListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd window listfnfr so tibt it no longfr
     * rfdfivfs window fvfnts from tiis window.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window listfnfr
     * @sff #bddWindowListfnfr
     * @sff #gftWindowListfnfrs
     */
    publid syndironizfd void rfmovfWindowListfnfr(WindowListfnfr l) {
        if (l == null) {
            rfturn;
        }
        windowListfnfr = AWTEvfntMultidbstfr.rfmovf(windowListfnfr, l);
    }

    /**
     * Rfmovfs tif spfdififd window stbtf listfnfr so tibt it no
     * longfr rfdfivfs window fvfnts from tiis window.  If
     * {@dodf l} is {@dodf null}, no fxdfption is tirown bnd
     * no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window stbtf listfnfr
     * @sff #bddWindowStbtfListfnfr
     * @sff #gftWindowStbtfListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd void rfmovfWindowStbtfListfnfr(WindowStbtfListfnfr l) {
        if (l == null) {
            rfturn;
        }
        windowStbtfListfnfr = AWTEvfntMultidbstfr.rfmovf(windowStbtfListfnfr, l);
    }

    /**
     * Rfmovfs tif spfdififd window fodus listfnfr so tibt it no longfr
     * rfdfivfs window fvfnts from tiis window.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif window fodus listfnfr
     * @sff #bddWindowFodusListfnfr
     * @sff #gftWindowFodusListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd void rfmovfWindowFodusListfnfr(WindowFodusListfnfr l) {
        if (l == null) {
            rfturn;
        }
        windowFodusListfnfr = AWTEvfntMultidbstfr.rfmovf(windowFodusListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif window listfnfrs
     * rfgistfrfd on tiis window.
     *
     * @rfturn bll of tiis window's {@dodf WindowListfnfr}s
     *         or bn fmpty brrby if no window
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddWindowListfnfr
     * @sff #rfmovfWindowListfnfr
     * @sindf 1.4
     */
    publid syndironizfd WindowListfnfr[] gftWindowListfnfrs() {
        rfturn gftListfnfrs(WindowListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif window fodus listfnfrs
     * rfgistfrfd on tiis window.
     *
     * @rfturn bll of tiis window's {@dodf WindowFodusListfnfr}s
     *         or bn fmpty brrby if no window fodus
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddWindowFodusListfnfr
     * @sff #rfmovfWindowFodusListfnfr
     * @sindf 1.4
     */
    publid syndironizfd WindowFodusListfnfr[] gftWindowFodusListfnfrs() {
        rfturn gftListfnfrs(WindowFodusListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif window stbtf listfnfrs
     * rfgistfrfd on tiis window.
     *
     * @rfturn bll of tiis window's {@dodf WindowStbtfListfnfr}s
     *         or bn fmpty brrby if no window stbtf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddWindowStbtfListfnfr
     * @sff #rfmovfWindowStbtfListfnfr
     * @sindf 1.4
     */
    publid syndironizfd WindowStbtfListfnfr[] gftWindowStbtfListfnfrs() {
        rfturn gftListfnfrs(WindowStbtfListfnfr.dlbss);
    }


    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis {@dodf Window}.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     *
     * You dbn spfdify tif {@dodf listfnfrTypf} brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * {@dodf Window} {@dodf w}
     * for its window listfnfrs witi tif following dodf:
     *
     * <prf>WindowListfnfr[] wls = (WindowListfnfr[])(w.gftListfnfrs(WindowListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          {@dodf jbvb.util.EvfntListfnfr}
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis window,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if {@dodf listfnfrTypf}
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          {@dodf jbvb.util.EvfntListfnfr}
     * @fxdfption NullPointfrExdfption if {@dodf listfnfrTypf} is {@dodf null}
     *
     * @sff #gftWindowListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if (listfnfrTypf == WindowFodusListfnfr.dlbss) {
            l = windowFodusListfnfr;
        } flsf if (listfnfrTypf == WindowStbtfListfnfr.dlbss) {
            l = windowStbtfListfnfr;
        } flsf if (listfnfrTypf == WindowListfnfr.dlbss) {
            l = windowListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is ibndlfd bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        switdi(f.id) {
          dbsf WindowEvfnt.WINDOW_OPENED:
          dbsf WindowEvfnt.WINDOW_CLOSING:
          dbsf WindowEvfnt.WINDOW_CLOSED:
          dbsf WindowEvfnt.WINDOW_ICONIFIED:
          dbsf WindowEvfnt.WINDOW_DEICONIFIED:
          dbsf WindowEvfnt.WINDOW_ACTIVATED:
          dbsf WindowEvfnt.WINDOW_DEACTIVATED:
            if ((fvfntMbsk & AWTEvfnt.WINDOW_EVENT_MASK) != 0 ||
                windowListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
          dbsf WindowEvfnt.WINDOW_GAINED_FOCUS:
          dbsf WindowEvfnt.WINDOW_LOST_FOCUS:
            if ((fvfntMbsk & AWTEvfnt.WINDOW_FOCUS_EVENT_MASK) != 0 ||
                windowFodusListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
          dbsf WindowEvfnt.WINDOW_STATE_CHANGED:
            if ((fvfntMbsk & AWTEvfnt.WINDOW_STATE_EVENT_MASK) != 0 ||
                windowStbtfListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
          dffbult:
            brfbk;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis window. If tif fvfnt is bn
     * {@dodf WindowEvfnt}, it invokfs tif
     * {@dodf prodfssWindowEvfnt} mftiod, flsf it invokfs its
     * supfrdlbss's {@dodf prodfssEvfnt}.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is {@dodf null}
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif fvfnt
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof WindowEvfnt) {
            switdi (f.gftID()) {
                dbsf WindowEvfnt.WINDOW_OPENED:
                dbsf WindowEvfnt.WINDOW_CLOSING:
                dbsf WindowEvfnt.WINDOW_CLOSED:
                dbsf WindowEvfnt.WINDOW_ICONIFIED:
                dbsf WindowEvfnt.WINDOW_DEICONIFIED:
                dbsf WindowEvfnt.WINDOW_ACTIVATED:
                dbsf WindowEvfnt.WINDOW_DEACTIVATED:
                    prodfssWindowEvfnt((WindowEvfnt)f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_GAINED_FOCUS:
                dbsf WindowEvfnt.WINDOW_LOST_FOCUS:
                    prodfssWindowFodusEvfnt((WindowEvfnt)f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_STATE_CHANGED:
                    prodfssWindowStbtfEvfnt((WindowEvfnt)f);
                    brfbk;
            }
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs window fvfnts oddurring on tiis window by
     * dispbtdiing tifm to bny rfgistfrfd WindowListfnfr objfdts.
     * NOTE: Tiis mftiod will not bf dbllfd unlfss window fvfnts
     * brf fnbblfd for tiis domponfnt; tiis ibppfns wifn onf of tif
     * following oddurs:
     * <ul>
     * <li>A WindowListfnfr objfdt is rfgistfrfd vib
     *     {@dodf bddWindowListfnfr}
     * <li>Window fvfnts brf fnbblfd vib {@dodf fnbblfEvfnts}
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is {@dodf null}
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif window fvfnt
     * @sff Componfnt#fnbblfEvfnts
     */
    protfdtfd void prodfssWindowEvfnt(WindowEvfnt f) {
        WindowListfnfr listfnfr = windowListfnfr;
        if (listfnfr != null) {
            switdi(f.gftID()) {
                dbsf WindowEvfnt.WINDOW_OPENED:
                    listfnfr.windowOpfnfd(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_CLOSING:
                    listfnfr.windowClosing(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_CLOSED:
                    listfnfr.windowClosfd(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_ICONIFIED:
                    listfnfr.windowIdonififd(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_DEICONIFIED:
                    listfnfr.windowDfidonififd(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_ACTIVATED:
                    listfnfr.windowAdtivbtfd(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_DEACTIVATED:
                    listfnfr.windowDfbdtivbtfd(f);
                    brfbk;
                dffbult:
                    brfbk;
            }
        }
    }

    /**
     * Prodfssfs window fodus fvfnt oddurring on tiis window by
     * dispbtdiing tifm to bny rfgistfrfd WindowFodusListfnfr objfdts.
     * NOTE: tiis mftiod will not bf dbllfd unlfss window fodus fvfnts
     * brf fnbblfd for tiis window. Tiis ibppfns wifn onf of tif
     * following oddurs:
     * <ul>
     * <li>b WindowFodusListfnfr is rfgistfrfd vib
     *     {@dodf bddWindowFodusListfnfr}
     * <li>Window fodus fvfnts brf fnbblfd vib {@dodf fnbblfEvfnts}
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is {@dodf null}
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif window fodus fvfnt
     * @sff Componfnt#fnbblfEvfnts
     * @sindf 1.4
     */
    protfdtfd void prodfssWindowFodusEvfnt(WindowEvfnt f) {
        WindowFodusListfnfr listfnfr = windowFodusListfnfr;
        if (listfnfr != null) {
            switdi (f.gftID()) {
                dbsf WindowEvfnt.WINDOW_GAINED_FOCUS:
                    listfnfr.windowGbinfdFodus(f);
                    brfbk;
                dbsf WindowEvfnt.WINDOW_LOST_FOCUS:
                    listfnfr.windowLostFodus(f);
                    brfbk;
                dffbult:
                    brfbk;
            }
        }
    }

    /**
     * Prodfssfs window stbtf fvfnt oddurring on tiis window by
     * dispbtdiing tifm to bny rfgistfrfd {@dodf WindowStbtfListfnfr}
     * objfdts.
     * NOTE: tiis mftiod will not bf dbllfd unlfss window stbtf fvfnts
     * brf fnbblfd for tiis window.  Tiis ibppfns wifn onf of tif
     * following oddurs:
     * <ul>
     * <li>b {@dodf WindowStbtfListfnfr} is rfgistfrfd vib
     *    {@dodf bddWindowStbtfListfnfr}
     * <li>window stbtf fvfnts brf fnbblfd vib {@dodf fnbblfEvfnts}
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is {@dodf null}
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif window stbtf fvfnt
     * @sff jbvb.bwt.Componfnt#fnbblfEvfnts
     * @sindf 1.4
     */
    protfdtfd void prodfssWindowStbtfEvfnt(WindowEvfnt f) {
        WindowStbtfListfnfr listfnfr = windowStbtfListfnfr;
        if (listfnfr != null) {
            switdi (f.gftID()) {
                dbsf WindowEvfnt.WINDOW_STATE_CHANGED:
                    listfnfr.windowStbtfCibngfd(f);
                    brfbk;
                dffbult:
                    brfbk;
            }
        }
    }

    /**
     * Implfmfnts b dfbugging iook -- difdks to sff if
     * tif usfr ibs typfd <i>dontrol-siift-F1</i>.  If so,
     * tif list of diild windows is dumpfd to {@dodf Systfm.out}.
     * @pbrbm f  tif kfybobrd fvfnt
     */
    void prfProdfssKfyEvfnt(KfyEvfnt f) {
        // Dump tif list of diild windows to Systfm.out.
        if (f.isAdtionKfy() && f.gftKfyCodf() == KfyEvfnt.VK_F1 &&
            f.isControlDown() && f.isSiiftDown() &&
            f.gftID() == KfyEvfnt.KEY_PRESSED) {
            list(Systfm.out, 0);
        }
    }

    void postProdfssKfyEvfnt(KfyEvfnt f) {
        // Do notiing
    }


    /**
     * Sfts wiftifr tiis window siould blwbys bf bbovf otifr windows.  If
     * tifrf brf multiplf blwbys-on-top windows, tifir rflbtivf ordfr is
     * unspfdififd bnd plbtform dfpfndfnt.
     * <p>
     * If somf otifr window is blrfbdy blwbys-on-top tifn tif
     * rflbtivf ordfr bftwffn tifsf windows is unspfdififd (dfpfnds on
     * plbtform).  No window dbn bf brougit to bf ovfr tif blwbys-on-top
     * window fxdfpt mbybf bnotifr blwbys-on-top window.
     * <p>
     * All windows ownfd by bn blwbys-on-top window inifrit tiis stbtf bnd
     * butombtidblly bfdomf blwbys-on-top.  If b window dfbsfs to bf
     * blwbys-on-top, tif windows tibt it owns will no longfr bf
     * blwbys-on-top.  Wifn bn blwbys-on-top window is sfnt {@link #toBbdk
     * toBbdk}, its blwbys-on-top stbtf is sft to {@dodf fblsf}.
     *
     * <p> Wifn tiis mftiod is dbllfd on b window witi b vbluf of
     * {@dodf truf}, bnd tif window is visiblf bnd tif plbtform
     * supports blwbys-on-top for tiis window, tif window is immfdibtfly
     * brougit forwbrd, "stidking" it in tif top-most position. If tif
     * window isn`t durrfntly visiblf, tiis mftiod sfts tif blwbys-on-top
     * stbtf to {@dodf truf} but dofs not bring tif window forwbrd.
     * Wifn tif window is lbtfr siown, it will bf blwbys-on-top.
     *
     * <p> Wifn tiis mftiod is dbllfd on b window witi b vbluf of
     * {@dodf fblsf} tif blwbys-on-top stbtf is sft to normbl. It mby blso
     * dbusf bn unspfdififd, plbtform-dfpfndfnt dibngf in tif z-ordfr of
     * top-lfvfl windows, but otifr blwbys-on-top windows will rfmbin in
     * top-most position. Cblling tiis mftiod witi b vbluf of {@dodf fblsf}
     * on b window tibt ibs b normbl stbtf ibs no ffffdt.
     *
     * <p><b>Notf</b>: somf plbtforms migit not support blwbys-on-top
     * windows.  To dftfdt if blwbys-on-top windows brf supportfd by tif
     * durrfnt plbtform, usf {@link Toolkit#isAlwbysOnTopSupportfd()} bnd
     * {@link Window#isAlwbysOnTopSupportfd()}.  If blwbys-on-top modf
     * isn't supportfd for tiis window or tiis window's toolkit dofs not
     * support blwbys-on-top windows, dblling tiis mftiod ibs no ffffdt.
     * <p>
     * If b SfdurityMbnbgfr is instbllfd, tif dblling tirfbd must bf
     * grbntfd tif AWTPfrmission "sftWindowAlwbysOnTop" in
     * ordfr to sft tif vbluf of tiis propfrty. If tiis
     * pfrmission is not grbntfd, tiis mftiod will tirow b
     * SfdurityExdfption, bnd tif durrfnt vbluf of tif propfrty will
     * bf lfft undibngfd.
     *
     * @pbrbm blwbysOnTop truf if tif window siould blwbys bf bbovf otifr
     *        windows
     * @tirows SfdurityExdfption if tif dblling tirfbd dofs not ibvf
     *         pfrmission to sft tif vbluf of blwbys-on-top propfrty
     *
     * @sff #isAlwbysOnTop
     * @sff #toFront
     * @sff #toBbdk
     * @sff AWTPfrmission
     * @sff #isAlwbysOnTopSupportfd
     * @sff #gftToolkit
     * @sff Toolkit#isAlwbysOnTopSupportfd
     * @sindf 1.5
     */
    publid finbl void sftAlwbysOnTop(boolfbn blwbysOnTop) tirows SfdurityExdfption {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.SET_WINDOW_ALWAYS_ON_TOP_PERMISSION);
        }

        boolfbn oldAlwbysOnTop;
        syndironizfd(tiis) {
            oldAlwbysOnTop = tiis.blwbysOnTop;
            tiis.blwbysOnTop = blwbysOnTop;
        }
        if (oldAlwbysOnTop != blwbysOnTop ) {
            if (isAlwbysOnTopSupportfd()) {
                WindowPffr pffr = (WindowPffr)tiis.pffr;
                syndironizfd(gftTrffLodk()) {
                    if (pffr != null) {
                        pffr.updbtfAlwbysOnTopStbtf();
                    }
                }
            }
            firfPropfrtyCibngf("blwbysOnTop", oldAlwbysOnTop, blwbysOnTop);
        }
        for (WfbkRfffrfndf<Window> rff : ownfdWindowList) {
            Window window = rff.gft();
            if (window != null) {
                try {
                    window.sftAlwbysOnTop(blwbysOnTop);
                } dbtdi (SfdurityExdfption ignorf) {
                }
            }
        }
    }

    /**
     * Rfturns wiftifr tif blwbys-on-top modf is supportfd for tiis
     * window. Somf plbtforms mby not support blwbys-on-top windows, somf
     * mby support only somf kinds of top-lfvfl windows; for fxbmplf,
     * b plbtform mby not support blwbys-on-top modbl diblogs.
     *
     * @rfturn {@dodf truf}, if tif blwbys-on-top modf is supportfd for
     *         tiis window bnd tiis window's toolkit supports blwbys-on-top windows,
     *         {@dodf fblsf} otifrwisf
     *
     * @sff #sftAlwbysOnTop(boolfbn)
     * @sff #gftToolkit
     * @sff Toolkit#isAlwbysOnTopSupportfd
     * @sindf 1.6
     */
    publid boolfbn isAlwbysOnTopSupportfd() {
        rfturn Toolkit.gftDffbultToolkit().isAlwbysOnTopSupportfd();
    }


    /**
     * Rfturns wiftifr tiis window is bn blwbys-on-top window.
     * @rfturn {@dodf truf}, if tif window is in blwbys-on-top stbtf,
     *         {@dodf fblsf} otifrwisf
     * @sff #sftAlwbysOnTop
     * @sindf 1.5
     */
    publid finbl boolfbn isAlwbysOnTop() {
        rfturn blwbysOnTop;
    }


    /**
     * Rfturns tif diild Componfnt of tiis Window tibt ibs fodus if tiis Window
     * is fodusfd; rfturns null otifrwisf.
     *
     * @rfturn tif diild Componfnt witi fodus, or null if tiis Window is not
     *         fodusfd
     * @sff #gftMostRfdfntFodusOwnfr
     * @sff #isFodusfd
     */
    publid Componfnt gftFodusOwnfr() {
        rfturn (isFodusfd())
            ? KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                  gftFodusOwnfr()
            : null;
    }

    /**
     * Rfturns tif diild Componfnt of tiis Window tibt will rfdfivf tif fodus
     * wifn tiis Window is fodusfd. If tiis Window is durrfntly fodusfd, tiis
     * mftiod rfturns tif sbmf Componfnt bs {@dodf gftFodusOwnfr()}. If
     * tiis Window is not fodusfd, tifn tif diild Componfnt tibt most rfdfntly
     * rfqufstfd fodus will bf rfturnfd. If no diild Componfnt ibs fvfr
     * rfqufstfd fodus, bnd tiis is b fodusbblf Window, tifn tiis Window's
     * initibl fodusbblf Componfnt is rfturnfd. If no diild Componfnt ibs fvfr
     * rfqufstfd fodus, bnd tiis is b non-fodusbblf Window, null is rfturnfd.
     *
     * @rfturn tif diild Componfnt tibt will rfdfivf fodus wifn tiis Window is
     *         fodusfd
     * @sff #gftFodusOwnfr
     * @sff #isFodusfd
     * @sff #isFodusbblfWindow
     * @sindf 1.4
     */
    publid Componfnt gftMostRfdfntFodusOwnfr() {
        if (isFodusfd()) {
            rfturn gftFodusOwnfr();
        } flsf {
            Componfnt mostRfdfnt =
                KfybobrdFodusMbnbgfr.gftMostRfdfntFodusOwnfr(tiis);
            if (mostRfdfnt != null) {
                rfturn mostRfdfnt;
            } flsf {
                rfturn (isFodusbblfWindow())
                    ? gftFodusTrbvfrsblPolidy().gftInitiblComponfnt(tiis)
                    : null;
            }
        }
    }

    /**
     * Rfturns wiftifr tiis Window is bdtivf. Only b Frbmf or b Diblog mby bf
     * bdtivf. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window or its
     * diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf bbr. Tif
     * bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first Frbmf or
     * Diblog tibt is bn ownfr of tif fodusfd Window.
     *
     * @rfturn wiftifr tiis is tif bdtivf Window.
     * @sff #isFodusfd
     * @sindf 1.4
     */
    publid boolfbn isAdtivf() {
        rfturn (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftAdtivfWindow() == tiis);
    }

    /**
     * Rfturns wiftifr tiis Window is fodusfd. If tifrf fxists b fodus ownfr,
     * tif fodusfd Window is tif Window tibt is, or dontbins, tibt fodus ownfr.
     * If tifrf is no fodus ownfr, tifn no Window is fodusfd.
     * <p>
     * If tif fodusfd Window is b Frbmf or b Diblog it is blso tif bdtivf
     * Window. Otifrwisf, tif bdtivf Window is tif first Frbmf or Diblog tibt
     * is bn ownfr of tif fodusfd Window.
     *
     * @rfturn wiftifr tiis is tif fodusfd Window.
     * @sff #isAdtivf
     * @sindf 1.4
     */
    publid boolfbn isFodusfd() {
        rfturn (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftGlobblFodusfdWindow() == tiis);
    }

    /**
     * Gfts b fodus trbvfrsbl kfy for tiis Window. (Sff {@dodf
     * sftFodusTrbvfrsblKfys} for b full dfsdription of fbdi kfy.)
     * <p>
     * If tif trbvfrsbl kfy ibs not bffn fxpliditly sft for tiis Window,
     * tifn tiis Window's pbrfnt's trbvfrsbl kfy is rfturnfd. If tif
     * trbvfrsbl kfy ibs not bffn fxpliditly sft for bny of tiis Window's
     * bndfstors, tifn tif durrfnt KfybobrdFodusMbnbgfr's dffbult trbvfrsbl kfy
     * is rfturnfd.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @rfturn tif AWTKfyStrokf for tif spfdififd kfy
     * @sff Contbinfr#sftFodusTrbvfrsblKfys
     * @sff KfybobrdFodusMbnbgfr#FORWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#BACKWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#UP_CYCLE_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#DOWN_CYCLE_TRAVERSAL_KEYS
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @sindf 1.4
     */
    @SupprfssWbrnings("undifdkfd")
    publid Sft<AWTKfyStrokf> gftFodusTrbvfrsblKfys(int id) {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        // Okby to rfturn Sft dirfdtly bfdbusf it is bn unmodifibblf vifw
        @SupprfssWbrnings("rbwtypfs")
        Sft kfystrokfs = (fodusTrbvfrsblKfys != null)
            ? fodusTrbvfrsblKfys[id]
            : null;

        if (kfystrokfs != null) {
            rfturn kfystrokfs;
        } flsf {
            rfturn KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftDffbultFodusTrbvfrsblKfys(id);
        }
    }

    /**
     * Dofs notiing bfdbusf Windows must blwbys bf roots of b fodus trbvfrsbl
     * dydlf. Tif pbssfd-in vbluf is ignorfd.
     *
     * @pbrbm fodusCydlfRoot tiis vbluf is ignorfd
     * @sff #isFodusCydlfRoot
     * @sff Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff Contbinfr#gftFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    publid finbl void sftFodusCydlfRoot(boolfbn fodusCydlfRoot) {
    }

    /**
     * Alwbys rfturns {@dodf truf} bfdbusf bll Windows must bf roots of b
     * fodus trbvfrsbl dydlf.
     *
     * @rfturn {@dodf truf}
     * @sff #sftFodusCydlfRoot
     * @sff Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff Contbinfr#gftFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    publid finbl boolfbn isFodusCydlfRoot() {
        rfturn truf;
    }

    /**
     * Alwbys rfturns {@dodf null} bfdbusf Windows ibvf no bndfstors; tify
     * rfprfsfnt tif top of tif Componfnt iifrbrdiy.
     *
     * @rfturn {@dodf null}
     * @sff Contbinfr#isFodusCydlfRoot()
     * @sindf 1.4
     */
    publid finbl Contbinfr gftFodusCydlfRootAndfstor() {
        rfturn null;
    }

    /**
     * Rfturns wiftifr tiis Window dbn bfdomf tif fodusfd Window, tibt is,
     * wiftifr tiis Window or bny of its subdomponfnts dbn bfdomf tif fodus
     * ownfr. For b Frbmf or Diblog to bf fodusbblf, its fodusbblf Window stbtf
     * must bf sft to {@dodf truf}. For b Window wiidi is not b Frbmf or
     * Diblog to bf fodusbblf, its fodusbblf Window stbtf must bf sft to
     * {@dodf truf}, its nfbrfst owning Frbmf or Diblog must bf
     * siowing on tif sdrffn, bnd it must dontbin bt lfbst onf Componfnt in
     * its fodus trbvfrsbl dydlf. If bny of tifsf donditions is not mft, tifn
     * nfitifr tiis Window nor bny of its subdomponfnts dbn bfdomf tif fodus
     * ownfr.
     *
     * @rfturn {@dodf truf} if tiis Window dbn bf tif fodusfd Window;
     *         {@dodf fblsf} otifrwisf
     * @sff #gftFodusbblfWindowStbtf
     * @sff #sftFodusbblfWindowStbtf
     * @sff #isSiowing
     * @sff Componfnt#isFodusbblf
     * @sindf 1.4
     */
    publid finbl boolfbn isFodusbblfWindow() {
        // If b Window/Frbmf/Diblog wbs mbdf non-fodusbblf, tifn it is blwbys
        // non-fodusbblf.
        if (!gftFodusbblfWindowStbtf()) {
            rfturn fblsf;
        }

        // All otifr tfsts bpply only to Windows.
        if (tiis instbndfof Frbmf || tiis instbndfof Diblog) {
            rfturn truf;
        }

        // A Window must ibvf bt lfbst onf Componfnt in its root fodus
        // trbvfrsbl dydlf to bf fodusbblf.
        if (gftFodusTrbvfrsblPolidy().gftDffbultComponfnt(tiis) == null) {
            rfturn fblsf;
        }

        // A Window's nfbrfst owning Frbmf or Diblog must bf siowing on tif
        // sdrffn.
        for (Window ownfr = gftOwnfr(); ownfr != null;
             ownfr = ownfr.gftOwnfr())
        {
            if (ownfr instbndfof Frbmf || ownfr instbndfof Diblog) {
                rfturn ownfr.isSiowing();
            }
        }

        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr tiis Window dbn bfdomf tif fodusfd Window if it mffts
     * tif otifr rfquirfmfnts outlinfd in {@dodf isFodusbblfWindow}. If
     * tiis mftiod rfturns {@dodf fblsf}, tifn
     * {@dodf isFodusbblfWindow} will rfturn {@dodf fblsf} bs wfll.
     * If tiis mftiod rfturns {@dodf truf}, tifn
     * {@dodf isFodusbblfWindow} mby rfturn {@dodf truf} or
     * {@dodf fblsf} dfpfnding upon tif otifr rfquirfmfnts wiidi must bf
     * mft in ordfr for b Window to bf fodusbblf.
     * <p>
     * By dffbult, bll Windows ibvf b fodusbblf Window stbtf of
     * {@dodf truf}.
     *
     * @rfturn wiftifr tiis Window dbn bf tif fodusfd Window
     * @sff #isFodusbblfWindow
     * @sff #sftFodusbblfWindowStbtf
     * @sff #isSiowing
     * @sff Componfnt#sftFodusbblf
     * @sindf 1.4
     */
    publid boolfbn gftFodusbblfWindowStbtf() {
        rfturn fodusbblfWindowStbtf;
    }

    /**
     * Sfts wiftifr tiis Window dbn bfdomf tif fodusfd Window if it mffts
     * tif otifr rfquirfmfnts outlinfd in {@dodf isFodusbblfWindow}. If
     * tiis Window's fodusbblf Window stbtf is sft to {@dodf fblsf}, tifn
     * {@dodf isFodusbblfWindow} will rfturn {@dodf fblsf}. If tiis
     * Window's fodusbblf Window stbtf is sft to {@dodf truf}, tifn
     * {@dodf isFodusbblfWindow} mby rfturn {@dodf truf} or
     * {@dodf fblsf} dfpfnding upon tif otifr rfquirfmfnts wiidi must bf
     * mft in ordfr for b Window to bf fodusbblf.
     * <p>
     * Sftting b Window's fodusbbility stbtf to {@dodf fblsf} is tif
     * stbndbrd mfdibnism for bn bpplidbtion to idfntify to tif AWT b Window
     * wiidi will bf usfd bs b flobting pblfttf or toolbbr, bnd tius siould bf
     * b non-fodusbblf Window.
     *
     * Sftting tif fodusbbility stbtf on b visiblf {@dodf Window}
     * dbn ibvf b dflbyfd ffffdt on somf plbtforms &#8212; tif bdtubl
     * dibngf mby ibppfn only wifn tif {@dodf Window} bfdomfs
     * iiddfn bnd tifn visiblf bgbin.  To fnsurf donsistfnt bfibvior
     * bdross plbtforms, sft tif {@dodf Window}'s fodusbblf stbtf
     * wifn tif {@dodf Window} is invisiblf bnd tifn siow it.
     *
     * @pbrbm fodusbblfWindowStbtf wiftifr tiis Window dbn bf tif fodusfd
     *        Window
     * @sff #isFodusbblfWindow
     * @sff #gftFodusbblfWindowStbtf
     * @sff #isSiowing
     * @sff Componfnt#sftFodusbblf
     * @sindf 1.4
     */
    publid void sftFodusbblfWindowStbtf(boolfbn fodusbblfWindowStbtf) {
        boolfbn oldFodusbblfWindowStbtf;
        syndironizfd (tiis) {
            oldFodusbblfWindowStbtf = tiis.fodusbblfWindowStbtf;
            tiis.fodusbblfWindowStbtf = fodusbblfWindowStbtf;
        }
        WindowPffr pffr = (WindowPffr)tiis.pffr;
        if (pffr != null) {
            pffr.updbtfFodusbblfWindowStbtf();
        }
        firfPropfrtyCibngf("fodusbblfWindowStbtf", oldFodusbblfWindowStbtf,
                           fodusbblfWindowStbtf);
        if (oldFodusbblfWindowStbtf && !fodusbblfWindowStbtf && isFodusfd()) {
            for (Window ownfr = gftOwnfr();
                 ownfr != null;
                 ownfr = ownfr.gftOwnfr())
                {
                    Componfnt toFodus =
                        KfybobrdFodusMbnbgfr.gftMostRfdfntFodusOwnfr(ownfr);
                    if (toFodus != null && toFodus.rfqufstFodus(fblsf, CbusfdFodusEvfnt.Cbusf.ACTIVATION)) {
                        rfturn;
                    }
                }
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                dlfbrGlobblFodusOwnfrPriv();
        }
    }

    /**
     * Sfts wiftifr tiis window siould rfdfivf fodus on
     * subsfqufntly bfing siown (witi b dbll to {@link #sftVisiblf sftVisiblf(truf)}),
     * or bfing movfd to tif front (witi b dbll to {@link #toFront}).
     * <p>
     * Notf tibt {@link #sftVisiblf sftVisiblf(truf)} mby bf dbllfd indirfdtly
     * (f.g. wifn siowing bn ownfr of tif window mbkfs tif window to bf siown).
     * {@link #toFront} mby blso bf dbllfd indirfdtly (f.g. wifn
     * {@link #sftVisiblf sftVisiblf(truf)} is dbllfd on blrfbdy visiblf window).
     * In bll sudi dbsfs tiis propfrty tbkfs ffffdt bs wfll.
     * <p>
     * Tif vbluf of tif propfrty is not inifritfd by ownfd windows.
     *
     * @pbrbm butoRfqufstFodus wiftifr tiis window siould bf fodusfd on
     *        subsfqufntly bfing siown or bfing movfd to tif front
     * @sff #isAutoRfqufstFodus
     * @sff #isFodusbblfWindow
     * @sff #sftVisiblf
     * @sff #toFront
     * @sindf 1.7
     */
    publid void sftAutoRfqufstFodus(boolfbn butoRfqufstFodus) {
        tiis.butoRfqufstFodus = butoRfqufstFodus;
    }

    /**
     * Rfturns wiftifr tiis window siould rfdfivf fodus on subsfqufntly bfing siown
     * (witi b dbll to {@link #sftVisiblf sftVisiblf(truf)}), or bfing movfd to tif front
     * (witi b dbll to {@link #toFront}).
     * <p>
     * By dffbult, tif window ibs {@dodf butoRfqufstFodus} vbluf of {@dodf truf}.
     *
     * @rfturn {@dodf butoRfqufstFodus} vbluf
     * @sff #sftAutoRfqufstFodus
     * @sindf 1.7
     */
    publid boolfbn isAutoRfqufstFodus() {
        rfturn butoRfqufstFodus;
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss, indluding tif
     * following:
     * <ul>
     *    <li>tiis Window's font ("font")</li>
     *    <li>tiis Window's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Window's forfground dolor ("forfground")</li>
     *    <li>tiis Window's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Window's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Window's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's fodus trbvfrsbl polidy ("fodusTrbvfrsblPolidy")
     *        </li>
     *    <li>tiis Window's fodusbblf Window stbtf ("fodusbblfWindowStbtf")
     *        </li>
     *    <li>tiis Window's blwbys-on-top stbtf("blwbysOnTop")</li>
     * </ul>
     * Notf tibt if tiis Window is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr  tif PropfrtyCibngfListfnfr to bf bddfd
     *
     * @sff Componfnt#rfmovfPropfrtyCibngfListfnfr
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        supfr.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf usfr-dffinfd, or onf of tif
     * following:
     * <ul>
     *    <li>tiis Window's font ("font")</li>
     *    <li>tiis Window's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Window's forfground dolor ("forfground")</li>
     *    <li>tiis Window's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Window's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Window's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's Sft of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Window's fodus trbvfrsbl polidy ("fodusTrbvfrsblPolidy")
     *        </li>
     *    <li>tiis Window's fodusbblf Window stbtf ("fodusbblfWindowStbtf")
     *        </li>
     *    <li>tiis Window's blwbys-on-top stbtf("blwbysOnTop")</li>
     * </ul>
     * Notf tibt if tiis Window is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf bddfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff Componfnt#rfmovfPropfrtyCibngfListfnfr
     */
    publid void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                          PropfrtyCibngfListfnfr listfnfr) {
        supfr.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    /**
     * Indidbtfs if tiis dontbinfr is b vblidbtf root.
     * <p>
     * {@dodf Window} objfdts brf tif vblidbtf roots, bnd, tifrfforf, tify
     * ovfrridf tiis mftiod to rfturn {@dodf truf}.
     *
     * @rfturn {@dodf truf}
     * @sindf 1.7
     * @sff jbvb.bwt.Contbinfr#isVblidbtfRoot
     */
    @Ovfrridf
    publid boolfbn isVblidbtfRoot() {
        rfturn truf;
    }

    /**
     * Dispbtdifs bn fvfnt to tiis window or onf of its sub domponfnts.
     * @pbrbm f tif fvfnt
     */
    void dispbtdiEvfntImpl(AWTEvfnt f) {
        if (f.gftID() == ComponfntEvfnt.COMPONENT_RESIZED) {
            invblidbtf();
            vblidbtf();
        }
        supfr.dispbtdiEvfntImpl(f);
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1
     * rfplbdfd by {@dodf dispbtdiEvfnt(AWTEvfnt)}.
     */
    @Dfprfdbtfd
    publid boolfbn postEvfnt(Evfnt f) {
        if (ibndlfEvfnt(f)) {
            f.donsumf();
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Cifdks if tiis Window is siowing on sdrffn.
     * @sff Componfnt#sftVisiblf
    */
    publid boolfbn isSiowing() {
        rfturn visiblf;
    }

    boolfbn isDisposing() {
        rfturn disposing;
    }

    /**
     * @dfprfdbtfd As of J2SE 1.4, rfplbdfd by
     * {@link Componfnt#bpplyComponfntOrifntbtion Componfnt.bpplyComponfntOrifntbtion}.
     */
    @Dfprfdbtfd
    publid void bpplyRfsourdfBundlf(RfsourdfBundlf rb) {
        bpplyComponfntOrifntbtion(ComponfntOrifntbtion.gftOrifntbtion(rb));
    }

    /**
     * @dfprfdbtfd As of J2SE 1.4, rfplbdfd by
     * {@link Componfnt#bpplyComponfntOrifntbtion Componfnt.bpplyComponfntOrifntbtion}.
     */
    @Dfprfdbtfd
    publid void bpplyRfsourdfBundlf(String rbNbmf) {
        bpplyRfsourdfBundlf(RfsourdfBundlf.gftBundlf(rbNbmf));
    }

   /*
    * Support for trbdking bll windows ownfd by tiis window
    */
    void bddOwnfdWindow(WfbkRfffrfndf<Window> wfbkWindow) {
        if (wfbkWindow != null) {
            syndironizfd(ownfdWindowList) {
                // tiis if stbtfmfnt siould rfblly bf bn bssfrt, but wf don't
                // ibvf bssfrts...
                if (!ownfdWindowList.dontbins(wfbkWindow)) {
                    ownfdWindowList.bddElfmfnt(wfbkWindow);
                }
            }
        }
    }

    void rfmovfOwnfdWindow(WfbkRfffrfndf<Window> wfbkWindow) {
        if (wfbkWindow != null) {
            // syndironizfd blodk not rfquirfd sindf rfmovfElfmfnt is
            // blrfbdy syndironizfd
            ownfdWindowList.rfmovfElfmfnt(wfbkWindow);
        }
    }

    void donnfdtOwnfdWindow(Window diild) {
        diild.pbrfnt = tiis;
        bddOwnfdWindow(diild.wfbkTiis);
        diild.disposfrRfdord.updbtfOwnfr();
    }

    privbtf void bddToWindowList() {
        syndironizfd (Window.dlbss) {
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<WfbkRfffrfndf<Window>> windowList = (Vfdtor<WfbkRfffrfndf<Window>>)bppContfxt.gft(Window.dlbss);
            if (windowList == null) {
                windowList = nfw Vfdtor<WfbkRfffrfndf<Window>>();
                bppContfxt.put(Window.dlbss, windowList);
            }
            windowList.bdd(wfbkTiis);
        }
    }

    privbtf stbtid void rfmovfFromWindowList(AppContfxt dontfxt, WfbkRfffrfndf<Window> wfbkTiis) {
        syndironizfd (Window.dlbss) {
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<WfbkRfffrfndf<Window>> windowList = (Vfdtor<WfbkRfffrfndf<Window>>)dontfxt.gft(Window.dlbss);
            if (windowList != null) {
                windowList.rfmovf(wfbkTiis);
            }
        }
    }

    privbtf void rfmovfFromWindowList() {
        rfmovfFromWindowList(bppContfxt, wfbkTiis);
    }

    /**
     * Window typf.
     *
     * Syndironizbtion: ObjfdtLodk
     */
    privbtf Typf typf = Typf.NORMAL;

    /**
     * Sfts tif typf of tif window.
     *
     * Tiis mftiod dbn only bf dbllfd wiilf tif window is not displbybblf.
     *
     * @pbrbm  typf tif window typf
     * @tirows IllfgblComponfntStbtfExdfption if tif window
     *         is displbybblf.
     * @tirows IllfgblArgumfntExdfption if tif typf is {@dodf null}
     * @sff    Componfnt#isDisplbybblf
     * @sff    #gftTypf
     * @sindf 1.7
     */
    publid void sftTypf(Typf typf) {
        if (typf == null) {
            tirow nfw IllfgblArgumfntExdfption("typf siould not bf null.");
        }
        syndironizfd (gftTrffLodk()) {
            if (isDisplbybblf()) {
                tirow nfw IllfgblComponfntStbtfExdfption(
                        "Tif window is displbybblf.");
            }
            syndironizfd (gftObjfdtLodk()) {
                tiis.typf = typf;
            }
        }
    }

    /**
     * Rfturns tif typf of tif window.
     *
     * @rfturn tif typf of tif window
     * @sff   #sftTypf
     * @sindf 1.7
     */
    publid Typf gftTypf() {
        syndironizfd (gftObjfdtLodk()) {
            rfturn typf;
        }
    }

    /**
     * Tif window sfriblizfd dbtb vfrsion.
     *
     * @sfribl
     */
    privbtf int windowSfriblizfdDbtbVfrsion = 2;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf {@dodf WindowListfnfr}s bnd
     * {@dodf WindowFodusListfnfr}s bs optionbl dbtb.
     * Writfs b list of diild windows bs optionbl dbtb.
     * Writfs b list of idon imbgfs bs optionbl dbtb
     *
     * @pbrbm s tif {@dodf ObjfdtOutputStrfbm} to writf
     * @sfriblDbtb {@dodf null} tfrminbtfd sfqufndf of
     *    0 or morf pbirs; tif pbir donsists of b {@dodf String}
     *    bnd {@dodf Objfdt}; tif {@dodf String}
     *    indidbtfs tif typf of objfdt bnd is onf of tif following:
     *    {@dodf windowListfnfrK} indidbting b
     *      {@dodf WindowListfnfr} objfdt;
     *    {@dodf windowFodusWindowK} indidbting b
     *      {@dodf WindowFodusListfnfr} objfdt;
     *    {@dodf ownfdWindowK} indidbting b diild
     *      {@dodf Window} objfdt
     *
     * @sff AWTEvfntMultidbstfr#sbvf(jbvb.io.ObjfdtOutputStrfbm, jbvb.lbng.String, jbvb.util.EvfntListfnfr)
     * @sff Componfnt#windowListfnfrK
     * @sff Componfnt#windowFodusListfnfrK
     * @sff Componfnt#ownfdWindowK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        syndironizfd (tiis) {
            // Updbtf old fodusMgr fiflds so tibt our objfdt strfbm dbn bf rfbd
            // by prfvious rflfbsfs
            fodusMgr = nfw FodusMbnbgfr();
            fodusMgr.fodusRoot = tiis;
            fodusMgr.fodusOwnfr = gftMostRfdfntFodusOwnfr();

            s.dffbultWritfObjfdt();

            // Clfbr fiflds so tibt wf don't kffp fxtrb rfffrfndfs bround
            fodusMgr = null;

            AWTEvfntMultidbstfr.sbvf(s, windowListfnfrK, windowListfnfr);
            AWTEvfntMultidbstfr.sbvf(s, windowFodusListfnfrK, windowFodusListfnfr);
            AWTEvfntMultidbstfr.sbvf(s, windowStbtfListfnfrK, windowStbtfListfnfr);
        }

        s.writfObjfdt(null);

        syndironizfd (ownfdWindowList) {
            for (int i = 0; i < ownfdWindowList.sizf(); i++) {
                Window diild = ownfdWindowList.flfmfntAt(i).gft();
                if (diild != null) {
                    s.writfObjfdt(ownfdWindowK);
                    s.writfObjfdt(diild);
                }
            }
        }
        s.writfObjfdt(null);

        //writf idon brrby
        if (idons != null) {
            for (Imbgf i : idons) {
                if (i instbndfof Sfriblizbblf) {
                    s.writfObjfdt(i);
                }
            }
        }
        s.writfObjfdt(null);
    }

    //
    // Pbrt of dfsfriblizbtion prodfdurf to bf dbllfd bfforf
    // usfr's dodf.
    //
    privbtf void initDfsfriblizfdWindow() {
        sftWbrningString();
        inputContfxtLodk = nfw Objfdt();

        // Dfsfriblizfd Windows brf not yft visiblf.
        visiblf = fblsf;

        wfbkTiis = nfw WfbkRfffrfndf<>(tiis);

        bndior = nfw Objfdt();
        disposfrRfdord = nfw WindowDisposfrRfdord(bppContfxt, tiis);
        sun.jbvb2d.Disposfr.bddRfdord(bndior, disposfrRfdord);

        bddToWindowList();
        initGC(null);
        ownfdWindowList = nfw Vfdtor<>();
    }

    privbtf void dfsfriblizfRfsourdfs(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption {

            if (windowSfriblizfdDbtbVfrsion < 2) {
                // Trbnslbtf old-stylf fodus trbdking to nfw modfl. For 1.4 bnd
                // lbtfr rflfbsfs, wf'll rfly on tif Window's initibl fodusbblf
                // Componfnt.
                if (fodusMgr != null) {
                    if (fodusMgr.fodusOwnfr != null) {
                        KfybobrdFodusMbnbgfr.
                            sftMostRfdfntFodusOwnfr(tiis, fodusMgr.fodusOwnfr);
                    }
                }

                // Tiis fifld is non-trbnsifnt bnd rflifs on dffbult sfriblizbtion.
                // Howfvfr, tif dffbult vbluf is insuffidifnt, so wf nffd to sft
                // it fxpliditly for objfdt dbtb strfbms prior to 1.4.
                fodusbblfWindowStbtf = truf;


            }

        Objfdt kfyOrNull;
        wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (windowListfnfrK == kfy) {
                bddWindowListfnfr((WindowListfnfr)(s.rfbdObjfdt()));
            } flsf if (windowFodusListfnfrK == kfy) {
                bddWindowFodusListfnfr((WindowFodusListfnfr)(s.rfbdObjfdt()));
            } flsf if (windowStbtfListfnfrK == kfy) {
                bddWindowStbtfListfnfr((WindowStbtfListfnfr)(s.rfbdObjfdt()));
            } flsf // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();
        }

        try {
            wiilf (null != (kfyOrNull = s.rfbdObjfdt())) {
                String kfy = ((String)kfyOrNull).intfrn();

                if (ownfdWindowK == kfy)
                    donnfdtOwnfdWindow((Window) s.rfbdObjfdt());

                flsf // skip vbluf for unrfdognizfd kfy
                    s.rfbdObjfdt();
            }

            //rfbd idons
            Objfdt obj = s.rfbdObjfdt(); //Tirows OptionblDbtbExdfption
                                         //for prf1.6 objfdts.
            idons = nfw ArrbyList<Imbgf>(); //Frbmf.rfbdObjfdt() bssumfs
                                            //prf1.6 vfrsion if idons is null.
            wiilf (obj != null) {
                if (obj instbndfof Imbgf) {
                    idons.bdd((Imbgf)obj);
                }
                obj = s.rfbdObjfdt();
            }
        }
        dbtdi (OptionblDbtbExdfption f) {
            // 1.1 sfriblizfd form
            // ownfdWindowList will bf updbtfd by Frbmf.rfbdObjfdt
        }

    }

    /**
     * Rfbds tif {@dodf ObjfdtInputStrfbm} bnd bn optionbl
     * list of listfnfrs to rfdfivf vbrious fvfnts firfd by
     * tif domponfnt; blso rfbds b list of
     * (possibly {@dodf null}) diild windows.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif {@dodf ObjfdtInputStrfbm} to rfbd
     * @fxdfption HfbdlfssExdfption if
     *   {@dodf GrbpiidsEnvironmfnt.isHfbdlfss} rfturns
     *   {@dodf truf}
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #writfObjfdt
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
         GrbpiidsEnvironmfnt.difdkHfbdlfss();
         initDfsfriblizfdWindow();
         ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

         syndLWRfqufsts = f.gft("syndLWRfqufsts", systfmSyndLWRfqufsts);
         stbtf = f.gft("stbtf", 0);
         fodusbblfWindowStbtf = f.gft("fodusbblfWindowStbtf", truf);
         windowSfriblizfdDbtbVfrsion = f.gft("windowSfriblizfdDbtbVfrsion", 1);
         lodbtionByPlbtform = f.gft("lodbtionByPlbtform", lodbtionByPlbtformProp);
         // Notf: 1.4 (or lbtfr) dofsn't usf fodusMgr
         fodusMgr = (FodusMbnbgfr)f.gft("fodusMgr", null);
         Diblog.ModblExdlusionTypf ft = (Diblog.ModblExdlusionTypf)
             f.gft("modblExdlusionTypf", Diblog.ModblExdlusionTypf.NO_EXCLUDE);
         sftModblExdlusionTypf(ft); // sindf 6.0
         boolfbn bot = f.gft("blwbysOnTop", fblsf);
         if(bot) {
             sftAlwbysOnTop(bot); // sindf 1.5; subjfdt to pfrmission difdk
         }
         sibpf = (Sibpf)f.gft("sibpf", null);
         opbdity = (Flobt)f.gft("opbdity", 1.0f);

         tiis.sfdurityWbrningWidti = 0;
         tiis.sfdurityWbrningHfigit = 0;
         tiis.sfdurityWbrningPointX = 2.0;
         tiis.sfdurityWbrningPointY = 0.0;
         tiis.sfdurityWbrningAlignmfntX = RIGHT_ALIGNMENT;
         tiis.sfdurityWbrningAlignmfntY = TOP_ALIGNMENT;

         dfsfriblizfRfsourdfs(s);
    }

    /*
     * --- Addfssibility Support ---
     *
     */

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Window.
     * For windows, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTWindow.
     * A nfw AddfssiblfAWTWindow instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTWindow tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Window
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTWindow();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * {@dodf Window} dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to window usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTWindow fxtfnds AddfssiblfAWTContbinfr
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 4215068635060671780L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff jbvbx.bddfssibility.AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.WINDOW;
        }

        /**
         * Gft tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif durrfnt
         * stbtf sft of tif objfdt
         * @sff jbvbx.bddfssibility.AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (gftFodusOwnfr() != null) {
                stbtfs.bdd(AddfssiblfStbtf.ACTIVE);
            }
            rfturn stbtfs;
        }

    } // innfr dlbss AddfssiblfAWTWindow

    @Ovfrridf
    void sftGrbpiidsConfigurbtion(GrbpiidsConfigurbtion gd) {
        if (gd == null) {
            gd = GrbpiidsEnvironmfnt.
                    gftLodblGrbpiidsEnvironmfnt().
                    gftDffbultSdrffnDfvidf().
                    gftDffbultConfigurbtion();
        }
        syndironizfd (gftTrffLodk()) {
            supfr.sftGrbpiidsConfigurbtion(gd);
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                log.finfr("+ Window.sftGrbpiidsConfigurbtion(): nfw GC is \n+ " + gftGrbpiidsConfigurbtion_NoClifntCodf() + "\n+ tiis is " + tiis);
            }
        }
    }

    /**
     * Sfts tif lodbtion of tif window rflbtivf to tif spfdififd
     * domponfnt bddording to tif following sdfnbrios.
     * <p>
     * Tif tbrgft sdrffn mfntionfd bflow is b sdrffn to wiidi
     * tif window siould bf plbdfd bftfr tif sftLodbtionRflbtivfTo
     * mftiod is dbllfd.
     * <ul>
     * <li>If tif domponfnt is {@dodf null}, or tif {@dodf
     * GrbpiidsConfigurbtion} bssodibtfd witi tiis domponfnt is
     * {@dodf null}, tif window is plbdfd in tif dfntfr of tif
     * sdrffn. Tif dfntfr point dbn bf obtbinfd witi tif {@link
     * GrbpiidsEnvironmfnt#gftCfntfrPoint
     * GrbpiidsEnvironmfnt.gftCfntfrPoint} mftiod.
     * <li>If tif domponfnt is not {@dodf null}, but it is not
     * durrfntly siowing, tif window is plbdfd in tif dfntfr of
     * tif tbrgft sdrffn dffinfd by tif {@dodf
     * GrbpiidsConfigurbtion} bssodibtfd witi tiis domponfnt.
     * <li>If tif domponfnt is not {@dodf null} bnd is siown on
     * tif sdrffn, tifn tif window is lodbtfd in sudi b wby tibt
     * tif dfntfr of tif window doindidfs witi tif dfntfr of tif
     * domponfnt.
     * </ul>
     * <p>
     * If tif sdrffns donfigurbtion dofs not bllow tif window to
     * bf movfd from onf sdrffn to bnotifr, tifn tif window is
     * only plbdfd bt tif lodbtion dftfrminfd bddording to tif
     * bbovf donditions bnd its {@dodf GrbpiidsConfigurbtion} is
     * not dibngfd.
     * <p>
     * <b>Notf</b>: If tif lowfr fdgf of tif window is out of tif sdrffn,
     * tifn tif window is plbdfd to tif sidf of tif {@dodf Componfnt}
     * tibt is dlosfst to tif dfntfr of tif sdrffn. So if tif
     * domponfnt is on tif rigit pbrt of tif sdrffn, tif window
     * is plbdfd to its lfft, bnd vidf vfrsb.
     * <p>
     * If bftfr tif window lodbtion ibs bffn dbldulbtfd, tif uppfr,
     * lfft, or rigit fdgf of tif window is out of tif sdrffn,
     * tifn tif window is lodbtfd in sudi b wby tibt tif uppfr,
     * lfft, or rigit fdgf of tif window doindidfs witi tif
     * dorrfsponding fdgf of tif sdrffn. If boti lfft bnd rigit
     * fdgfs of tif window brf out of tif sdrffn, tif window is
     * plbdfd bt tif lfft sidf of tif sdrffn. Tif similbr plbdfmfnt
     * will oddur if boti top bnd bottom fdgfs brf out of tif sdrffn.
     * In tibt dbsf, tif window is plbdfd bt tif top sidf of tif sdrffn.
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     *
     * @pbrbm d  tif domponfnt in rflbtion to wiidi tif window's lodbtion
     *           is dftfrminfd
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#gftCfntfrPoint
     * @sindf 1.4
     */
    publid void sftLodbtionRflbtivfTo(Componfnt d) {
        // tbrgft lodbtion
        int dx = 0, dy = 0;
        // tbrgft GC
        GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion_NoClifntCodf();
        Rfdtbnglf gdBounds = gd.gftBounds();

        Dimfnsion windowSizf = gftSizf();

        // sfbrdi b top-lfvfl of d
        Window domponfntWindow = SunToolkit.gftContbiningWindow(d);
        if ((d == null) || (domponfntWindow == null)) {
            GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            gd = gf.gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
            gdBounds = gd.gftBounds();
            Point dfntfrPoint = gf.gftCfntfrPoint();
            dx = dfntfrPoint.x - windowSizf.widti / 2;
            dy = dfntfrPoint.y - windowSizf.ifigit / 2;
        } flsf if (!d.isSiowing()) {
            gd = domponfntWindow.gftGrbpiidsConfigurbtion();
            gdBounds = gd.gftBounds();
            dx = gdBounds.x + (gdBounds.widti - windowSizf.widti) / 2;
            dy = gdBounds.y + (gdBounds.ifigit - windowSizf.ifigit) / 2;
        } flsf {
            gd = domponfntWindow.gftGrbpiidsConfigurbtion();
            gdBounds = gd.gftBounds();
            Dimfnsion dompSizf = d.gftSizf();
            Point dompLodbtion = d.gftLodbtionOnSdrffn();
            dx = dompLodbtion.x + ((dompSizf.widti - windowSizf.widti) / 2);
            dy = dompLodbtion.y + ((dompSizf.ifigit - windowSizf.ifigit) / 2);

            // Adjust for bottom fdgf bfing offsdrffn
            if (dy + windowSizf.ifigit > gdBounds.y + gdBounds.ifigit) {
                dy = gdBounds.y + gdBounds.ifigit - windowSizf.ifigit;
                if (dompLodbtion.x - gdBounds.x + dompSizf.widti / 2 < gdBounds.widti / 2) {
                    dx = dompLodbtion.x + dompSizf.widti;
                } flsf {
                    dx = dompLodbtion.x - windowSizf.widti;
                }
            }
        }

        // Avoid bfing plbdfd off tif fdgf of tif sdrffn:
        // bottom
        if (dy + windowSizf.ifigit > gdBounds.y + gdBounds.ifigit) {
            dy = gdBounds.y + gdBounds.ifigit - windowSizf.ifigit;
        }
        // top
        if (dy < gdBounds.y) {
            dy = gdBounds.y;
        }
        // rigit
        if (dx + windowSizf.widti > gdBounds.x + gdBounds.widti) {
            dx = gdBounds.x + gdBounds.widti - windowSizf.widti;
        }
        // lfft
        if (dx < gdBounds.x) {
            dx = gdBounds.x;
        }

        sftLodbtion(dx, dy);
    }

    /**
     * Ovfrriddfn from Componfnt.  Top-lfvfl Windows siould not propbgbtf b
     * MousfWifflEvfnt bfyond tifmsflvfs into tifir owning Windows.
     */
    void dflivfrMousfWifflToAndfstor(MousfWifflEvfnt f) {}

    /**
     * Ovfrriddfn from Componfnt.  Top-lfvfl Windows don't dispbtdi to bndfstors
     */
    boolfbn dispbtdiMousfWifflToAndfstor(MousfWifflEvfnt f) {rfturn fblsf;}

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt.
     * Multi-bufffring is usfful for rfndfring pfrformbndf.  Tiis mftiod
     * bttfmpts to drfbtf tif bfst strbtfgy bvbilbblf witi tif numbfr of
     * bufffrs supplifd.  It will blwbys drfbtf b {@dodf BufffrStrbtfgy}
     * witi tibt numbfr of bufffrs.
     * A pbgf-flipping strbtfgy is bttfmptfd first, tifn b blitting strbtfgy
     * using bddflfrbtfd bufffrs.  Finblly, bn unbddflfrbtfd blitting
     * strbtfgy is usfd.
     * <p>
     * Ebdi timf tiis mftiod is dbllfd,
     * tif fxisting bufffr strbtfgy for tiis domponfnt is disdbrdfd.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1.
     * @fxdfption IllfgblStbtfExdfption if tif domponfnt is not displbybblf
     * @sff #isDisplbybblf
     * @sff #gftBufffrStrbtfgy
     * @sindf 1.4
     */
    publid void drfbtfBufffrStrbtfgy(int numBufffrs) {
        supfr.drfbtfBufffrStrbtfgy(numBufffrs);
    }

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt witi tif
     * rfquirfd bufffr dbpbbilitifs.  Tiis is usfful, for fxbmplf, if only
     * bddflfrbtfd mfmory or pbgf flipping is dfsirfd (bs spfdififd by tif
     * bufffr dbpbbilitifs).
     * <p>
     * Ebdi timf tiis mftiod
     * is dbllfd, tif fxisting bufffr strbtfgy for tiis domponfnt is disdbrdfd.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf, indluding tif front bufffr
     * @pbrbm dbps tif rfquirfd dbpbbilitifs for drfbting tif bufffr strbtfgy;
     * dbnnot bf {@dodf null}
     * @fxdfption AWTExdfption if tif dbpbbilitifs supplifd dould not bf
     * supportfd or mft; tiis mby ibppfn, for fxbmplf, if tifrf is not fnougi
     * bddflfrbtfd mfmory durrfntly bvbilbblf, or if pbgf flipping is spfdififd
     * but not possiblf.
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1, or if
     * dbps is {@dodf null}
     * @sff #gftBufffrStrbtfgy
     * @sindf 1.4
     */
    publid void drfbtfBufffrStrbtfgy(int numBufffrs,
        BufffrCbpbbilitifs dbps) tirows AWTExdfption {
        supfr.drfbtfBufffrStrbtfgy(numBufffrs, dbps);
    }

    /**
     * Rfturns tif {@dodf BufffrStrbtfgy} usfd by tiis domponfnt.  Tiis
     * mftiod will rfturn null if b {@dodf BufffrStrbtfgy} ibs not yft
     * bffn drfbtfd or ibs bffn disposfd.
     *
     * @rfturn tif bufffr strbtfgy usfd by tiis domponfnt
     * @sff #drfbtfBufffrStrbtfgy
     * @sindf 1.4
     */
    publid BufffrStrbtfgy gftBufffrStrbtfgy() {
        rfturn supfr.gftBufffrStrbtfgy();
    }

    Componfnt gftTfmporbryLostComponfnt() {
        rfturn tfmporbryLostComponfnt;
    }
    Componfnt sftTfmporbryLostComponfnt(Componfnt domponfnt) {
        Componfnt prfviousComp = tfmporbryLostComponfnt;
        // Cifdk tibt "domponfnt" is bn bddfptbblf fodus ownfr bnd don't storf it otifrwisf
        // - or lbtfr wf will ibvf problfms witi oppositf wiilf ibndling  WINDOW_GAINED_FOCUS
        if (domponfnt == null || domponfnt.dbnBfFodusOwnfr()) {
            tfmporbryLostComponfnt = domponfnt;
        } flsf {
            tfmporbryLostComponfnt = null;
        }
        rfturn prfviousComp;
    }

    /**
     * Cifdks wiftifr tiis window dbn dontbin fodus ownfr.
     * Vfrififs tibt it is fodusbblf bnd bs dontbinfr it dbn dontbinfr fodus ownfr.
     * @sindf 1.5
     */
    boolfbn dbnContbinFodusOwnfr(Componfnt fodusOwnfrCbndidbtf) {
        rfturn supfr.dbnContbinFodusOwnfr(fodusOwnfrCbndidbtf) && isFodusbblfWindow();
    }

    privbtf boolfbn lodbtionByPlbtform = lodbtionByPlbtformProp;


    /**
     * Sfts wiftifr tiis Window siould bppfbr bt tif dffbult lodbtion for tif
     * nbtivf windowing systfm or bt tif durrfnt lodbtion (rfturnfd by
     * {@dodf gftLodbtion}) tif nfxt timf tif Window is mbdf visiblf.
     * Tiis bfibvior rfsfmblfs b nbtivf window siown witiout progrbmmbtidblly
     * sftting its lodbtion.  Most windowing systfms dbsdbdf windows if tifir
     * lodbtions brf not fxpliditly sft. Tif bdtubl lodbtion is dftfrminfd ondf tif
     * window is siown on tif sdrffn.
     * <p>
     * Tiis bfibvior dbn blso bf fnbblfd by sftting tif Systfm Propfrty
     * "jbvb.bwt.Window.lodbtionByPlbtform" to "truf", tiougi dblls to tiis mftiod
     * tbkf prfdfdfndf.
     * <p>
     * Cblls to {@dodf sftVisiblf}, {@dodf sftLodbtion} bnd
     * {@dodf sftBounds} bftfr dblling {@dodf sftLodbtionByPlbtform} dlfbr
     * tiis propfrty of tif Window.
     * <p>
     * For fxbmplf, bftfr tif following dodf is fxfdutfd:
     * <prf>
     * sftLodbtionByPlbtform(truf);
     * sftVisiblf(truf);
     * boolfbn flbg = isLodbtionByPlbtform();
     * </prf>
     * Tif window will bf siown bt plbtform's dffbult lodbtion bnd
     * {@dodf flbg} will bf {@dodf fblsf}.
     * <p>
     * In tif following sbmplf:
     * <prf>
     * sftLodbtionByPlbtform(truf);
     * sftLodbtion(10, 10);
     * boolfbn flbg = isLodbtionByPlbtform();
     * sftVisiblf(truf);
     * </prf>
     * Tif window will bf siown bt (10, 10) bnd {@dodf flbg} will bf
     * {@dodf fblsf}.
     *
     * @pbrbm lodbtionByPlbtform {@dodf truf} if tiis Window siould bppfbr
     *        bt tif dffbult lodbtion, {@dodf fblsf} if bt tif durrfnt lodbtion
     * @tirows IllfgblComponfntStbtfExdfption if tif window
     *         is siowing on sdrffn bnd lodbtionByPlbtform is {@dodf truf}.
     * @sff #sftLodbtion
     * @sff #isSiowing
     * @sff #sftVisiblf
     * @sff #isLodbtionByPlbtform
     * @sff jbvb.lbng.Systfm#gftPropfrty(String)
     * @sindf 1.5
     */
    publid void sftLodbtionByPlbtform(boolfbn lodbtionByPlbtform) {
        syndironizfd (gftTrffLodk()) {
            if (lodbtionByPlbtform && isSiowing()) {
                tirow nfw IllfgblComponfntStbtfExdfption("Tif window is siowing on sdrffn.");
            }
            tiis.lodbtionByPlbtform = lodbtionByPlbtform;
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis Window will bppfbr bt tif dffbult lodbtion
     * for tif nbtivf windowing systfm tif nfxt timf tiis Window is mbdf visiblf.
     * Tiis mftiod blwbys rfturns {@dodf fblsf} if tif Window is siowing on tif
     * sdrffn.
     *
     * @rfturn wiftifr tiis Window will bppfbr bt tif dffbult lodbtion
     * @sff #sftLodbtionByPlbtform
     * @sff #isSiowing
     * @sindf 1.5
     */
    publid boolfbn isLodbtionByPlbtform() {
        syndironizfd (gftTrffLodk()) {
            rfturn lodbtionByPlbtform;
        }
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif {@dodf widti} or {@dodf ifigit} vblufs
     * brf butombtidblly fnlbrgfd if fitifr is lfss tibn
     * tif minimum sizf bs spfdififd by prfvious dbll to
     * {@dodf sftMinimumSizf}.
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     *
     * @sff #gftBounds
     * @sff #sftLodbtion(int, int)
     * @sff #sftLodbtion(Point)
     * @sff #sftSizf(int, int)
     * @sff #sftSizf(Dimfnsion)
     * @sff #sftMinimumSizf
     * @sff #sftLodbtionByPlbtform
     * @sff #isLodbtionByPlbtform
     * @sindf 1.6
     */
    publid void sftBounds(int x, int y, int widti, int ifigit) {
        syndironizfd (gftTrffLodk()) {
            if (gftBoundsOp() == ComponfntPffr.SET_LOCATION ||
                gftBoundsOp() == ComponfntPffr.SET_BOUNDS)
            {
                lodbtionByPlbtform = fblsf;
            }
            supfr.sftBounds(x, y, widti, ifigit);
        }
    }

    /**
     * {@inifritDod}
     * <p>
     * Tif {@dodf r.widti} or {@dodf r.ifigit} vblufs
     * will bf butombtidblly fnlbrgfd if fitifr is lfss tibn
     * tif minimum sizf bs spfdififd by prfvious dbll to
     * {@dodf sftMinimumSizf}.
     * <p>
     * Tif mftiod dibngfs tif gfomftry-rflbtfd dbtb. Tifrfforf,
     * tif nbtivf windowing systfm mby ignorf sudi rfqufsts, or it mby modify
     * tif rfqufstfd dbtb, so tibt tif {@dodf Window} objfdt is plbdfd bnd sizfd
     * in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     *
     * @sff #gftBounds
     * @sff #sftLodbtion(int, int)
     * @sff #sftLodbtion(Point)
     * @sff #sftSizf(int, int)
     * @sff #sftSizf(Dimfnsion)
     * @sff #sftMinimumSizf
     * @sff #sftLodbtionByPlbtform
     * @sff #isLodbtionByPlbtform
     * @sindf 1.6
     */
    publid void sftBounds(Rfdtbnglf r) {
        sftBounds(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt will bf displbyfd on tif sdrffn.
     * @rfturn {@dodf truf} if tif domponfnt bnd bll of its bndfstors
     *          until b toplfvfl window brf visiblf, {@dodf fblsf} otifrwisf
     */
    boolfbn isRfdursivflyVisiblf() {
        // 5079694 fix: for b toplfvfl to bf displbyfd, its pbrfnt dofsn't ibvf to bf visiblf.
        // Wf'rf ovfrriding isRfdursivflyVisiblf to implfmfnt tiis polidy.
        rfturn visiblf;
    }


    // ******************** SHAPES & TRANSPARENCY CODE ********************

    /**
     * Rfturns tif opbdity of tif window.
     *
     * @rfturn tif opbdity of tif window
     *
     * @sff Window#sftOpbdity(flobt)
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     *
     * @sindf 1.7
     */
    publid flobt gftOpbdity() {
        syndironizfd (gftTrffLodk()) {
            rfturn opbdity;
        }
    }

    /**
     * Sfts tif opbdity of tif window.
     * <p>
     * Tif opbdity vbluf is in tif rbngf [0..1]. Notf tibt sftting tif opbdity
     * lfvfl of 0 mby or mby not disbblf tif mousf fvfnt ibndling on tiis
     * window. Tiis is b plbtform-dfpfndfnt bfibvior.
     * <p>
     * Tif following donditions must bf mft in ordfr to sft tif opbdity vbluf
     * lfss tibn {@dodf 1.0f}:
     * <ul>
     * <li>Tif {@link GrbpiidsDfvidf.WindowTrbnsludfndy#TRANSLUCENT TRANSLUCENT}
     * trbnsludfndy must bf supportfd by tif undfrlying systfm
     * <li>Tif window must bf undfdorbtfd (sff {@link Frbmf#sftUndfdorbtfd}
     * bnd {@link Diblog#sftUndfdorbtfd})
     * <li>Tif window must not bf in full-sdrffn modf (sff {@link
     * GrbpiidsDfvidf#sftFullSdrffnWindow(Window)})
     * </ul>
     * <p>
     * If tif rfqufstfd opbdity vbluf is lfss tibn {@dodf 1.0f}, bnd bny of tif
     * bbovf donditions brf not mft, tif window opbdity will not dibngf,
     * bnd tif {@dodf IllfgblComponfntStbtfExdfption} will bf tirown.
     * <p>
     * Tif trbnsludfndy lfvfls of individubl pixfls mby blso bf ffffdtfd by tif
     * blpib domponfnt of tifir dolor (sff {@link Window#sftBbdkground(Color)}) bnd tif
     * durrfnt sibpf of tiis window (sff {@link #sftSibpf(Sibpf)}).
     *
     * @pbrbm opbdity tif opbdity lfvfl to sft to tif window
     *
     * @tirows IllfgblArgumfntExdfption if tif opbdity is out of tif rbngf
     *     [0..1]
     * @tirows IllfgblComponfntStbtfExdfption if tif window is dfdorbtfd bnd
     *     tif opbdity is lfss tibn {@dodf 1.0f}
     * @tirows IllfgblComponfntStbtfExdfption if tif window is in full sdrffn
     *     modf, bnd tif opbdity is lfss tibn {@dodf 1.0f}
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf
     *     GrbpiidsDfvidf.WindowTrbnsludfndy#TRANSLUCENT TRANSLUCENT}
     *     trbnsludfndy is not supportfd bnd tif opbdity is lfss tibn
     *     {@dodf 1.0f}
     *
     * @sff Window#gftOpbdity
     * @sff Window#sftBbdkground(Color)
     * @sff Window#sftSibpf(Sibpf)
     * @sff Frbmf#isUndfdorbtfd
     * @sff Diblog#isUndfdorbtfd
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     * @sff GrbpiidsDfvidf#isWindowTrbnsludfndySupportfd(GrbpiidsDfvidf.WindowTrbnsludfndy)
     *
     * @sindf 1.7
     */
    publid void sftOpbdity(flobt opbdity) {
        syndironizfd (gftTrffLodk()) {
            if (opbdity < 0.0f || opbdity > 1.0f) {
                tirow nfw IllfgblArgumfntExdfption(
                    "Tif vbluf of opbdity siould bf in tif rbngf [0.0f .. 1.0f].");
            }
            if (opbdity < 1.0f) {
                GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
                GrbpiidsDfvidf gd = gd.gftDfvidf();
                if (gd.gftDfvidf().gftFullSdrffnWindow() == tiis) {
                    tirow nfw IllfgblComponfntStbtfExdfption(
                        "Sftting opbdity for full-sdrffn window is not supportfd.");
                }
                if (!gd.isWindowTrbnsludfndySupportfd(
                    GrbpiidsDfvidf.WindowTrbnsludfndy.TRANSLUCENT))
                {
                    tirow nfw UnsupportfdOpfrbtionExdfption(
                        "TRANSLUCENT trbnsludfndy is not supportfd.");
                }
            }
            tiis.opbdity = opbdity;
            WindowPffr pffr = (WindowPffr)gftPffr();
            if (pffr != null) {
                pffr.sftOpbdity(opbdity);
            }
        }
    }

    /**
     * Rfturns tif sibpf of tif window.
     *
     * Tif vbluf rfturnfd by tiis mftiod mby not bf tif sbmf bs
     * prfviously sft witi {@dodf sftSibpf(sibpf)}, but it is gubrbntffd
     * to rfprfsfnt tif sbmf sibpf.
     *
     * @rfturn tif sibpf of tif window or {@dodf null} if no
     *     sibpf is spfdififd for tif window
     *
     * @sff Window#sftSibpf(Sibpf)
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     *
     * @sindf 1.7
     */
    publid Sibpf gftSibpf() {
        syndironizfd (gftTrffLodk()) {
            rfturn sibpf == null ? null : nfw Pbti2D.Flobt(sibpf);
        }
    }

    /**
     * Sfts tif sibpf of tif window.
     * <p>
     * Sftting b sibpf duts off somf pbrts of tif window. Only tif pbrts tibt
     * bflong to tif givfn {@link Sibpf} rfmbin visiblf bnd dlidkbblf. If
     * tif sibpf brgumfnt is {@dodf null}, tiis mftiod rfstorfs tif dffbult
     * sibpf, mbking tif window rfdtbngulbr on most plbtforms.
     * <p>
     * Tif following donditions must bf mft to sft b non-null sibpf:
     * <ul>
     * <li>Tif {@link GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSPARENT
     * PERPIXEL_TRANSPARENT} trbnsludfndy must bf supportfd by tif
     * undfrlying systfm
     * <li>Tif window must bf undfdorbtfd (sff {@link Frbmf#sftUndfdorbtfd}
     * bnd {@link Diblog#sftUndfdorbtfd})
     * <li>Tif window must not bf in full-sdrffn modf (sff {@link
     * GrbpiidsDfvidf#sftFullSdrffnWindow(Window)})
     * </ul>
     * <p>
     * If tif rfqufstfd sibpf is not {@dodf null}, bnd bny of tif bbovf
     * donditions brf not mft, tif sibpf of tiis window will not dibngf,
     * bnd fitifr tif {@dodf UnsupportfdOpfrbtionExdfption} or {@dodf
     * IllfgblComponfntStbtfExdfption} will bf tirown.
     * <p>
     * Tif trbnsludfndy lfvfls of individubl pixfls mby blso bf ffffdtfd by tif
     * blpib domponfnt of tifir dolor (sff {@link Window#sftBbdkground(Color)}) bnd tif
     * opbdity vbluf (sff {@link #sftOpbdity(flobt)}). Sff {@link
     * GrbpiidsDfvidf.WindowTrbnsludfndy} for morf dftbils.
     *
     * @pbrbm sibpf tif sibpf to sft to tif window
     *
     * @tirows IllfgblComponfntStbtfExdfption if tif sibpf is not {@dodf
     *     null} bnd tif window is dfdorbtfd
     * @tirows IllfgblComponfntStbtfExdfption if tif sibpf is not {@dodf
     *     null} bnd tif window is in full-sdrffn modf
     * @tirows UnsupportfdOpfrbtionExdfption if tif sibpf is not {@dodf
     *     null} bnd {@link GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSPARENT
     *     PERPIXEL_TRANSPARENT} trbnsludfndy is not supportfd
     *
     * @sff Window#gftSibpf()
     * @sff Window#sftBbdkground(Color)
     * @sff Window#sftOpbdity(flobt)
     * @sff Frbmf#isUndfdorbtfd
     * @sff Diblog#isUndfdorbtfd
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     * @sff GrbpiidsDfvidf#isWindowTrbnsludfndySupportfd(GrbpiidsDfvidf.WindowTrbnsludfndy)
     *
     * @sindf 1.7
     */
    publid void sftSibpf(Sibpf sibpf) {
        syndironizfd (gftTrffLodk()) {
            if (sibpf != null) {
                GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
                GrbpiidsDfvidf gd = gd.gftDfvidf();
                if (gd.gftDfvidf().gftFullSdrffnWindow() == tiis) {
                    tirow nfw IllfgblComponfntStbtfExdfption(
                        "Sftting sibpf for full-sdrffn window is not supportfd.");
                }
                if (!gd.isWindowTrbnsludfndySupportfd(
                        GrbpiidsDfvidf.WindowTrbnsludfndy.PERPIXEL_TRANSPARENT))
                {
                    tirow nfw UnsupportfdOpfrbtionExdfption(
                        "PERPIXEL_TRANSPARENT trbnsludfndy is not supportfd.");
                }
            }
            tiis.sibpf = (sibpf == null) ? null : nfw Pbti2D.Flobt(sibpf);
            WindowPffr pffr = (WindowPffr)gftPffr();
            if (pffr != null) {
                pffr.bpplySibpf(sibpf == null ? null : Rfgion.gftInstbndf(sibpf, null));
            }
        }
    }

    /**
     * Gfts tif bbdkground dolor of tiis window.
     * <p>
     * Notf tibt tif blpib domponfnt of tif rfturnfd dolor indidbtfs wiftifr
     * tif window is in tif non-opbquf (pfr-pixfl trbnsludfnt) modf.
     *
     * @rfturn tiis domponfnt's bbdkground dolor
     *
     * @sff Window#sftBbdkground(Color)
     * @sff Window#isOpbquf
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     */
    @Ovfrridf
    publid Color gftBbdkground() {
        rfturn supfr.gftBbdkground();
    }

    /**
     * Sfts tif bbdkground dolor of tiis window.
     * <p>
     * If tif windowing systfm supports tif {@link
     * GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT PERPIXEL_TRANSLUCENT}
     * trbnsludfndy, tif blpib domponfnt of tif givfn bbdkground dolor
     * mby ffffdt tif modf of opfrbtion for tiis window: it indidbtfs wiftifr
     * tiis window must bf opbquf (blpib fqubls {@dodf 1.0f}) or pfr-pixfl trbnsludfnt
     * (blpib is lfss tibn {@dodf 1.0f}). If tif givfn bbdkground dolor is
     * {@dodf null}, tif window is donsidfrfd domplftfly opbquf.
     * <p>
     * All tif following donditions must bf mft to fnbblf tif pfr-pixfl
     * trbnspbrfndy modf for tiis window:
     * <ul>
     * <li>Tif {@link GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT
     * PERPIXEL_TRANSLUCENT} trbnsludfndy must bf supportfd by tif grbpiids
     * dfvidf wifrf tiis window is lodbtfd
     * <li>Tif window must bf undfdorbtfd (sff {@link Frbmf#sftUndfdorbtfd}
     * bnd {@link Diblog#sftUndfdorbtfd})
     * <li>Tif window must not bf in full-sdrffn modf (sff {@link
     * GrbpiidsDfvidf#sftFullSdrffnWindow(Window)})
     * </ul>
     * <p>
     * If tif blpib domponfnt of tif rfqufstfd bbdkground dolor is lfss tibn
     * {@dodf 1.0f}, bnd bny of tif bbovf donditions brf not mft, tif bbdkground
     * dolor of tiis window will not dibngf, tif blpib domponfnt of tif givfn
     * bbdkground dolor will not bfffdt tif modf of opfrbtion for tiis window,
     * bnd fitifr tif {@dodf UnsupportfdOpfrbtionExdfption} or {@dodf
     * IllfgblComponfntStbtfExdfption} will bf tirown.
     * <p>
     * Wifn tif window is pfr-pixfl trbnsludfnt, tif drbwing sub-systfm
     * rfspfdts tif blpib vbluf of fbdi individubl pixfl. If b pixfl gfts
     * pbintfd witi tif blpib dolor domponfnt fqubl to zfro, it bfdomfs
     * visublly trbnspbrfnt. If tif blpib of tif pixfl is fqubl to 1.0f, tif
     * pixfl is fully opbquf. Intfrim vblufs of tif blpib dolor domponfnt mbkf
     * tif pixfl sfmi-trbnspbrfnt. In tiis modf, tif bbdkground of tif window
     * gfts pbintfd witi tif blpib vbluf of tif givfn bbdkground dolor. If tif
     * blpib vbluf of tif brgumfnt of tiis mftiod is fqubl to {@dodf 0}, tif
     * bbdkground is not pbintfd bt bll.
     * <p>
     * Tif bdtubl lfvfl of trbnsludfndy of b givfn pixfl blso dfpfnds on window
     * opbdity (sff {@link #sftOpbdity(flobt)}), bs wfll bs tif durrfnt sibpf of
     * tiis window (sff {@link #sftSibpf(Sibpf)}).
     * <p>
     * Notf tibt pbinting b pixfl witi tif blpib vbluf of {@dodf 0} mby or mby
     * not disbblf tif mousf fvfnt ibndling on tiis pixfl. Tiis is b
     * plbtform-dfpfndfnt bfibvior. To mbkf surf tif mousf fvfnts do not gft
     * dispbtdifd to b pbrtidulbr pixfl, tif pixfl must bf fxdludfd from tif
     * sibpf of tif window.
     * <p>
     * Enbbling tif pfr-pixfl trbnsludfndy modf mby dibngf tif grbpiids
     * donfigurbtion of tiis window duf to tif nbtivf plbtform rfquirfmfnts.
     *
     * @pbrbm bgColor tif dolor to bfdomf tiis window's bbdkground dolor.
     *
     * @tirows IllfgblComponfntStbtfExdfption if tif blpib vbluf of tif givfn
     *     bbdkground dolor is lfss tibn {@dodf 1.0f} bnd tif window is dfdorbtfd
     * @tirows IllfgblComponfntStbtfExdfption if tif blpib vbluf of tif givfn
     *     bbdkground dolor is lfss tibn {@dodf 1.0f} bnd tif window is in
     *     full-sdrffn modf
     * @tirows UnsupportfdOpfrbtionExdfption if tif blpib vbluf of tif givfn
     *     bbdkground dolor is lfss tibn {@dodf 1.0f} bnd {@link
     *     GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT
     *     PERPIXEL_TRANSLUCENT} trbnsludfndy is not supportfd
     *
     * @sff Window#gftBbdkground
     * @sff Window#isOpbquf
     * @sff Window#sftOpbdity(flobt)
     * @sff Window#sftSibpf(Sibpf)
     * @sff Frbmf#isUndfdorbtfd
     * @sff Diblog#isUndfdorbtfd
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy
     * @sff GrbpiidsDfvidf#isWindowTrbnsludfndySupportfd(GrbpiidsDfvidf.WindowTrbnsludfndy)
     * @sff GrbpiidsConfigurbtion#isTrbnsludfndyCbpbblf()
     */
    @Ovfrridf
    publid void sftBbdkground(Color bgColor) {
        Color oldBg = gftBbdkground();
        supfr.sftBbdkground(bgColor);
        if (oldBg != null && oldBg.fqubls(bgColor)) {
            rfturn;
        }
        int oldAlpib = oldBg != null ? oldBg.gftAlpib() : 255;
        int blpib = bgColor != null ? bgColor.gftAlpib() : 255;
        if ((oldAlpib == 255) && (blpib < 255)) { // non-opbquf window
            GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
            GrbpiidsDfvidf gd = gd.gftDfvidf();
            if (gd.gftDfvidf().gftFullSdrffnWindow() == tiis) {
                tirow nfw IllfgblComponfntStbtfExdfption(
                    "Mbking full-sdrffn window non opbquf is not supportfd.");
            }
            if (!gd.isTrbnsludfndyCbpbblf()) {
                GrbpiidsConfigurbtion dbpbblfGC = gd.gftTrbnsludfndyCbpbblfGC();
                if (dbpbblfGC == null) {
                    tirow nfw UnsupportfdOpfrbtionExdfption(
                        "PERPIXEL_TRANSLUCENT trbnsludfndy is not supportfd");
                }
                sftGrbpiidsConfigurbtion(dbpbblfGC);
            }
            sftLbyfrsOpbquf(tiis, fblsf);
        } flsf if ((oldAlpib < 255) && (blpib == 255)) {
            sftLbyfrsOpbquf(tiis, truf);
        }
        WindowPffr pffr = (WindowPffr)gftPffr();
        if (pffr != null) {
            pffr.sftOpbquf(blpib == 255);
        }
    }

    /**
     * Indidbtfs if tif window is durrfntly opbquf.
     * <p>
     * Tif mftiod rfturns {@dodf fblsf} if tif bbdkground dolor of tif window
     * is not {@dodf null} bnd tif blpib domponfnt of tif dolor is lfss tibn
     * {@dodf 1.0f}. Tif mftiod rfturns {@dodf truf} otifrwisf.
     *
     * @rfturn {@dodf truf} if tif window is opbquf, {@dodf fblsf} otifrwisf
     *
     * @sff Window#gftBbdkground
     * @sff Window#sftBbdkground(Color)
     * @sindf 1.7
     */
    @Ovfrridf
    publid boolfbn isOpbquf() {
        Color bg = gftBbdkground();
        rfturn bg != null ? bg.gftAlpib() == 255 : truf;
    }

    privbtf void updbtfWindow() {
        syndironizfd (gftTrffLodk()) {
            WindowPffr pffr = (WindowPffr)gftPffr();
            if (pffr != null) {
                pffr.updbtfWindow();
            }
        }
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.7
     */
    @Ovfrridf
    publid void pbint(Grbpiids g) {
        if (!isOpbquf()) {
            Grbpiids gg = g.drfbtf();
            try {
                if (gg instbndfof Grbpiids2D) {
                    gg.sftColor(gftBbdkground());
                    ((Grbpiids2D)gg).sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC));
                    gg.fillRfdt(0, 0, gftWidti(), gftHfigit());
                }
            } finblly {
                gg.disposf();
            }
        }
        supfr.pbint(g);
    }

    privbtf stbtid void sftLbyfrsOpbquf(Componfnt domponfnt, boolfbn isOpbquf) {
        // Siouldn't usf instbndfof to bvoid lobding Swing dlbssfs
        //    if it's b purf AWT bpplidbtion.
        if (SunToolkit.isInstbndfOf(domponfnt, "jbvbx.swing.RootPbnfContbinfr")) {
            jbvbx.swing.RootPbnfContbinfr rpd = (jbvbx.swing.RootPbnfContbinfr)domponfnt;
            jbvbx.swing.JRootPbnf root = rpd.gftRootPbnf();
            jbvbx.swing.JLbyfrfdPbnf lp = root.gftLbyfrfdPbnf();
            Contbinfr d = root.gftContfntPbnf();
            jbvbx.swing.JComponfnt dontfnt =
                (d instbndfof jbvbx.swing.JComponfnt) ? (jbvbx.swing.JComponfnt)d : null;
            lp.sftOpbquf(isOpbquf);
            root.sftOpbquf(isOpbquf);
            if (dontfnt != null) {
                dontfnt.sftOpbquf(isOpbquf);

                // Itfrbtf down onf lfvfl to sff wiftifr wf ibvf b JApplft
                // (wiidi is blso b RootPbnfContbinfr) wiidi rfquirfs prodfssing
                int numCiildrfn = dontfnt.gftComponfntCount();
                if (numCiildrfn > 0) {
                    Componfnt diild = dontfnt.gftComponfnt(0);
                    // It's OK to usf instbndfof ifrf bfdbusf wf'vf
                    // blrfbdy lobdfd tif RootPbnfContbinfr dlbss by now
                    if (diild instbndfof jbvbx.swing.RootPbnfContbinfr) {
                        sftLbyfrsOpbquf(diild, isOpbquf);
                    }
                }
            }
        }
    }


    // ************************** MIXING CODE *******************************

    // A window ibs bn ownfr, but it dofs NOT ibvf b dontbinfr
    @Ovfrridf
    finbl Contbinfr gftContbinfr() {
        rfturn null;
    }

    /**
     * Applifs tif sibpf to tif domponfnt
     * @pbrbm sibpf Sibpf to bf bpplifd to tif domponfnt
     */
    @Ovfrridf
    finbl void bpplyCompoundSibpf(Rfgion sibpf) {
        // Tif sibpf dbldulbtfd by mixing dodf is not intfndfd to bf bpplifd
        // to windows or frbmfs
    }

    @Ovfrridf
    finbl void bpplyCurrfntSibpf() {
        // Tif sibpf dbldulbtfd by mixing dodf is not intfndfd to bf bpplifd
        // to windows or frbmfs
    }

    @Ovfrridf
    finbl void mixOnRfsibping() {
        // Tif sibpf dbldulbtfd by mixing dodf is not intfndfd to bf bpplifd
        // to windows or frbmfs
    }

    @Ovfrridf
    finbl Point gftLodbtionOnWindow() {
        rfturn nfw Point(0, 0);
    }

    // ****************** END OF MIXING CODE ********************************

    /**
     * Limit tif givfn doublf vbluf witi tif givfn rbngf.
     */
    privbtf stbtid doublf limit(doublf vbluf, doublf min, doublf mbx) {
        vbluf = Mbti.mbx(vbluf, min);
        vbluf = Mbti.min(vbluf, mbx);
        rfturn vbluf;
    }

    /**
     * Cbldulbtf tif position of tif sfdurity wbrning.
     *
     * Tiis mftiod gfts tif window lodbtion/sizf bs rfportfd by tif nbtivf
     * systfm sindf tif lodblly dbdifd vblufs mby rfprfsfnt outdbtfd dbtb.
     *
     * Tif mftiod is usfd from tif nbtivf dodf, or vib AWTAddfssor.
     *
     * NOTE: tiis mftiod is invokfd on tif toolkit tirfbd, bnd tifrfforf is not
     * supposfd to bfdomf publid/usfr-ovfrridbblf.
     */
    privbtf Point2D dbldulbtfSfdurityWbrningPosition(doublf x, doublf y,
            doublf w, doublf i)
    {
        // Tif position bddording to tif spfd of SfdurityWbrning.sftPosition()
        doublf wx = x + w * sfdurityWbrningAlignmfntX + sfdurityWbrningPointX;
        doublf wy = y + i * sfdurityWbrningAlignmfntY + sfdurityWbrningPointY;

        // First, mbkf surf tif wbrning is not too fbr from tif window bounds
        wx = Window.limit(wx,
                x - sfdurityWbrningWidti - 2,
                x + w + 2);
        wy = Window.limit(wy,
                y - sfdurityWbrningHfigit - 2,
                y + i + 2);

        // Now mbkf surf tif wbrning window is visiblf on tif sdrffn
        GrbpiidsConfigurbtion grbpiidsConfig =
            gftGrbpiidsConfigurbtion_NoClifntCodf();
        Rfdtbnglf sdrffnBounds = grbpiidsConfig.gftBounds();
        Insfts sdrffnInsfts =
            Toolkit.gftDffbultToolkit().gftSdrffnInsfts(grbpiidsConfig);

        wx = Window.limit(wx,
                sdrffnBounds.x + sdrffnInsfts.lfft,
                sdrffnBounds.x + sdrffnBounds.widti - sdrffnInsfts.rigit
                - sfdurityWbrningWidti);
        wy = Window.limit(wy,
                sdrffnBounds.y + sdrffnInsfts.top,
                sdrffnBounds.y + sdrffnBounds.ifigit - sdrffnInsfts.bottom
                - sfdurityWbrningHfigit);

        rfturn nfw Point2D.Doublf(wx, wy);
    }

    stbtid {
        AWTAddfssor.sftWindowAddfssor(nfw AWTAddfssor.WindowAddfssor() {
            publid flobt gftOpbdity(Window window) {
                rfturn window.opbdity;
            }
            publid void sftOpbdity(Window window, flobt opbdity) {
                window.sftOpbdity(opbdity);
            }
            publid Sibpf gftSibpf(Window window) {
                rfturn window.gftSibpf();
            }
            publid void sftSibpf(Window window, Sibpf sibpf) {
                window.sftSibpf(sibpf);
            }
            publid void sftOpbquf(Window window, boolfbn opbquf) {
                Color bg = window.gftBbdkground();
                if (bg == null) {
                    bg = nfw Color(0, 0, 0, 0);
                }
                window.sftBbdkground(nfw Color(bg.gftRfd(), bg.gftGrffn(), bg.gftBluf(),
                                               opbquf ? 255 : 0));
            }
            publid void updbtfWindow(Window window) {
                window.updbtfWindow();
            }

            publid Dimfnsion gftSfdurityWbrningSizf(Window window) {
                rfturn nfw Dimfnsion(window.sfdurityWbrningWidti,
                        window.sfdurityWbrningHfigit);
            }

            publid void sftSfdurityWbrningSizf(Window window, int widti, int ifigit)
            {
                window.sfdurityWbrningWidti = widti;
                window.sfdurityWbrningHfigit = ifigit;
            }

            publid void sftSfdurityWbrningPosition(Window window,
                    Point2D point, flobt blignmfntX, flobt blignmfntY)
            {
                window.sfdurityWbrningPointX = point.gftX();
                window.sfdurityWbrningPointY = point.gftY();
                window.sfdurityWbrningAlignmfntX = blignmfntX;
                window.sfdurityWbrningAlignmfntY = blignmfntY;

                syndironizfd (window.gftTrffLodk()) {
                    WindowPffr pffr = (WindowPffr)window.gftPffr();
                    if (pffr != null) {
                        pffr.rfpositionSfdurityWbrning();
                    }
                }
            }

            publid Point2D dbldulbtfSfdurityWbrningPosition(Window window,
                    doublf x, doublf y, doublf w, doublf i)
            {
                rfturn window.dbldulbtfSfdurityWbrningPosition(x, y, w, i);
            }

            publid void sftLWRfqufstStbtus(Window dibngfd, boolfbn stbtus) {
                dibngfd.syndLWRfqufsts = stbtus;
            }

            publid boolfbn isAutoRfqufstFodus(Window w) {
                rfturn w.butoRfqufstFodus;
            }

            publid boolfbn isTrbyIdonWindow(Window w) {
                rfturn w.isTrbyIdonWindow;
            }

            publid void sftTrbyIdonWindow(Window w, boolfbn isTrbyIdonWindow) {
                w.isTrbyIdonWindow = isTrbyIdonWindow;
            }
        }); // WindowAddfssor
    } // stbtid

    // b window dofsn't nffd to bf updbtfd in tif Z-ordfr.
    @Ovfrridf
    void updbtfZOrdfr() {}

} // dlbss Window


/**
 * Tiis dlbss is no longfr usfd, but is mbintbinfd for Sfriblizbtion
 * bbdkwbrd-dompbtibility.
 */
dlbss FodusMbnbgfr implfmfnts jbvb.io.Sfriblizbblf {
    Contbinfr fodusRoot;
    Componfnt fodusOwnfr;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    stbtid finbl long sfriblVfrsionUID = 2491878825643557906L;
}
