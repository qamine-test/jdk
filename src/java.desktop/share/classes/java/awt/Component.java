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

import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.util.Objfdts;
import jbvb.util.Vfdtor;
import jbvb.util.Lodblf;
import jbvb.util.EvfntListfnfr;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.Collfdtions;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.ContbinfrPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;
import jbvb.bwt.imbgf.BufffrStrbtfgy;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import jbvb.bwt.fvfnt.*;
import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.Trbnsifnt;
import jbvb.bwt.im.InputContfxt;
import jbvb.bwt.im.InputMftiodRfqufsts;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvbx.bddfssibility.*;
import jbvb.bpplft.Applft;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.bwt.AppContfxt;
import sun.bwt.AWTAddfssor;
import sun.bwt.ConstrbinbblfGrbpiids;
import sun.bwt.SubRfgionSiowbblf;
import sun.bwt.SunToolkit;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.EmbfddfdFrbmf;
import sun.bwt.dnd.SunDropTbrgftEvfnt;
import sun.bwt.im.CompositionArfb;
import sun.font.FontMbnbgfr;
import sun.font.FontMbnbgfrFbdtory;
import sun.font.SunFontMbnbgfr;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.pipf.Rfgion;
import sun.bwt.imbgf.VSyndfdBSMbnbgfr;
import sun.jbvb2d.pipf.iw.ExtfndfdBufffrCbpbbilitifs;
import stbtid sun.jbvb2d.pipf.iw.ExtfndfdBufffrCbpbbilitifs.VSyndTypf.*;
import sun.bwt.RfqufstFodusControllfr;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.util.logging.PlbtformLoggfr;

/**
 * A <fm>domponfnt</fm> is bn objfdt ibving b grbpiidbl rfprfsfntbtion
 * tibt dbn bf displbyfd on tif sdrffn bnd tibt dbn intfrbdt witi tif
 * usfr. Exbmplfs of domponfnts brf tif buttons, difdkboxfs, bnd sdrollbbrs
 * of b typidbl grbpiidbl usfr intfrfbdf. <p>
 * Tif <dodf>Componfnt</dodf> dlbss is tif bbstrbdt supfrdlbss of
 * tif nonmfnu-rflbtfd Abstrbdt Window Toolkit domponfnts. Clbss
 * <dodf>Componfnt</dodf> dbn blso bf fxtfndfd dirfdtly to drfbtf b
 * ligitwfigit domponfnt. A ligitwfigit domponfnt is b domponfnt tibt is
 * not bssodibtfd witi b nbtivf window. On tif dontrbry, b ifbvywfigit
 * domponfnt is bssodibtfd witi b nbtivf window. Tif {@link #isLigitwfigit()}
 * mftiod mby bf usfd to distinguisi bftwffn tif two kinds of tif domponfnts.
 * <p>
 * Ligitwfigit bnd ifbvywfigit domponfnts mby bf mixfd in b singlf domponfnt
 * iifrbrdiy. Howfvfr, for dorrfdt opfrbting of sudi b mixfd iifrbrdiy of
 * domponfnts, tif wiolf iifrbrdiy must bf vblid. Wifn tif iifrbrdiy gfts
 * invblidbtfd, likf bftfr dibnging tif bounds of domponfnts, or
 * bdding/rfmoving domponfnts to/from dontbinfrs, tif wiolf iifrbrdiy must bf
 * vblidbtfd bftfrwbrds by mfbns of tif {@link Contbinfr#vblidbtf()} mftiod
 * invokfd on tif top-most invblid dontbinfr of tif iifrbrdiy.
 *
 * <i3>Sfriblizbtion</i3>
 * It is importbnt to notf tibt only AWT listfnfrs wiidi donform
 * to tif <dodf>Sfriblizbblf</dodf> protodol will bf sbvfd wifn
 * tif objfdt is storfd.  If bn AWT objfdt ibs listfnfrs tibt
 * brfn't mbrkfd sfriblizbblf, tify will bf droppfd bt
 * <dodf>writfObjfdt</dodf> timf.  Dfvflopfrs will nffd, bs blwbys,
 * to donsidfr tif implidbtions of mbking bn objfdt sfriblizbblf.
 * Onf situbtion to wbtdi out for is tiis:
 * <prf>
 *    import jbvb.bwt.*;
 *    import jbvb.bwt.fvfnt.*;
 *    import jbvb.io.Sfriblizbblf;
 *
 *    dlbss MyApp implfmfnts AdtionListfnfr, Sfriblizbblf
 *    {
 *        BigObjfdtTibtSiouldNotBfSfriblizfdWitiAButton bigOnf;
 *        Button bButton = nfw Button();
 *
 *        MyApp()
 *        {
 *            // Oops, now bButton ibs b listfnfr witi b rfffrfndf
 *            // to bigOnf!
 *            bButton.bddAdtionListfnfr(tiis);
 *        }
 *
 *        publid void bdtionPfrformfd(AdtionEvfnt f)
 *        {
 *            Systfm.out.println("Hfllo Tifrf");
 *        }
 *    }
 * </prf>
 * In tiis fxbmplf, sfriblizing <dodf>bButton</dodf> by itsflf
 * will dbusf <dodf>MyApp</dodf> bnd fvfrytiing it rfffrs to
 * to bf sfriblizfd bs wfll.  Tif problfm is tibt tif listfnfr
 * is sfriblizbblf by doindidfndf, not by dfsign.  To sfpbrbtf
 * tif dfdisions bbout <dodf>MyApp</dodf> bnd tif
 * <dodf>AdtionListfnfr</dodf> bfing sfriblizbblf onf dbn usf b
 * nfstfd dlbss, bs in tif following fxbmplf:
 * <prf>
 *    import jbvb.bwt.*;
 *    import jbvb.bwt.fvfnt.*;
 *    import jbvb.io.Sfriblizbblf;
 *
 *    dlbss MyApp implfmfnts jbvb.io.Sfriblizbblf
 *    {
 *         BigObjfdtTibtSiouldNotBfSfriblizfdWitiAButton bigOnf;
 *         Button bButton = nfw Button();
 *
 *         stbtid dlbss MyAdtionListfnfr implfmfnts AdtionListfnfr
 *         {
 *             publid void bdtionPfrformfd(AdtionEvfnt f)
 *             {
 *                 Systfm.out.println("Hfllo Tifrf");
 *             }
 *         }
 *
 *         MyApp()
 *         {
 *             bButton.bddAdtionListfnfr(nfw MyAdtionListfnfr());
 *         }
 *    }
 * </prf>
 * <p>
 * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
 * by AWT bnd Swing, indluding informbtion on iow to writf tif most
 * fffidifnt pbinting dodf, sff
 * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
 * <p>
 * For dftbils on tif fodus subsystfm, sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @butior      Artiur vbn Hoff
 * @butior      Sbmi Sibio
 */
publid bbstrbdt dlbss Componfnt implfmfnts ImbgfObsfrvfr, MfnuContbinfr,
                                           Sfriblizbblf
{

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.Componfnt");
    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.Componfnt");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fodus.Componfnt");
    privbtf stbtid finbl PlbtformLoggfr mixingLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.mixing.Componfnt");

    /**
     * Tif pffr of tif domponfnt. Tif pffr implfmfnts tif domponfnt's
     * bfibvior. Tif pffr is sft wifn tif <dodf>Componfnt</dodf> is
     * bddfd to b dontbinfr tibt blso is b pffr.
     * @sff #bddNotify
     * @sff #rfmovfNotify
     */
    trbnsifnt ComponfntPffr pffr;

    /**
     * Tif pbrfnt of tif objfdt. It mby bf <dodf>null</dodf>
     * for top-lfvfl domponfnts.
     * @sff #gftPbrfnt
     */
    trbnsifnt Contbinfr pbrfnt;

    /**
     * Tif <dodf>AppContfxt</dodf> of tif domponfnt. Applfts/Plugin mby
     * dibngf tif AppContfxt.
     */
    trbnsifnt AppContfxt bppContfxt;

    /**
     * Tif x position of tif domponfnt in tif pbrfnt's doordinbtf systfm.
     *
     * @sfribl
     * @sff #gftLodbtion
     */
    int x;

    /**
     * Tif y position of tif domponfnt in tif pbrfnt's doordinbtf systfm.
     *
     * @sfribl
     * @sff #gftLodbtion
     */
    int y;

    /**
     * Tif widti of tif domponfnt.
     *
     * @sfribl
     * @sff #gftSizf
     */
    int widti;

    /**
     * Tif ifigit of tif domponfnt.
     *
     * @sfribl
     * @sff #gftSizf
     */
    int ifigit;

    /**
     * Tif forfground dolor for tiis domponfnt.
     * <dodf>forfground</dodf> dbn bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftForfground
     * @sff #sftForfground
     */
    Color       forfground;

    /**
     * Tif bbdkground dolor for tiis domponfnt.
     * <dodf>bbdkground</dodf> dbn bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftBbdkground
     * @sff #sftBbdkground
     */
    Color       bbdkground;

    /**
     * Tif font usfd by tiis domponfnt.
     * Tif <dodf>font</dodf> dbn bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftFont
     * @sff #sftFont
     */
    volbtilf Font font;

    /**
     * Tif font wiidi tif pffr is durrfntly using.
     * (<dodf>null</dodf> if no pffr fxists.)
     */
    Font        pffrFont;

    /**
     * Tif dursor displbyfd wifn pointfr is ovfr tiis domponfnt.
     * Tiis vbluf dbn bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftCursor
     * @sff #sftCursor
     */
    Cursor      dursor;

    /**
     * Tif lodblf for tif domponfnt.
     *
     * @sfribl
     * @sff #gftLodblf
     * @sff #sftLodblf
     */
    Lodblf      lodblf;

    /**
     * A rfffrfndf to b <dodf>GrbpiidsConfigurbtion</dodf> objfdt
     * usfd to dfsdribf tif dibrbdtfristids of b grbpiids
     * dfstinbtion.
     * Tiis vbluf dbn bf <dodf>null</dodf>.
     *
     * @sindf 1.3
     * @sfribl
     * @sff GrbpiidsConfigurbtion
     * @sff #gftGrbpiidsConfigurbtion
     */
    privbtf trbnsifnt GrbpiidsConfigurbtion grbpiidsConfig = null;

    /**
     * A rfffrfndf to b <dodf>BufffrStrbtfgy</dodf> objfdt
     * usfd to mbnipulbtf tif bufffrs on tiis domponfnt.
     *
     * @sindf 1.4
     * @sff jbvb.bwt.imbgf.BufffrStrbtfgy
     * @sff #gftBufffrStrbtfgy()
     */
    trbnsifnt BufffrStrbtfgy bufffrStrbtfgy = null;

    /**
     * Truf wifn tif objfdt siould ignorf bll rfpbint fvfnts.
     *
     * @sindf 1.4
     * @sfribl
     * @sff #sftIgnorfRfpbint
     * @sff #gftIgnorfRfpbint
     */
    boolfbn ignorfRfpbint = fblsf;

    /**
     * Truf wifn tif objfdt is visiblf. An objfdt tibt is not
     * visiblf is not drbwn on tif sdrffn.
     *
     * @sfribl
     * @sff #isVisiblf
     * @sff #sftVisiblf
     */
    boolfbn visiblf = truf;

    /**
     * Truf wifn tif objfdt is fnbblfd. An objfdt tibt is not
     * fnbblfd dofs not intfrbdt witi tif usfr.
     *
     * @sfribl
     * @sff #isEnbblfd
     * @sff #sftEnbblfd
     */
    boolfbn fnbblfd = truf;

    /**
     * Truf wifn tif objfdt is vblid. An invblid objfdt nffds to
     * bf lbyfd out. Tiis flbg is sft to fblsf wifn tif objfdt
     * sizf is dibngfd.
     *
     * @sfribl
     * @sff #isVblid
     * @sff #vblidbtf
     * @sff #invblidbtf
     */
    privbtf volbtilf boolfbn vblid = fblsf;

    /**
     * Tif <dodf>DropTbrgft</dodf> bssodibtfd witi tiis domponfnt.
     *
     * @sindf 1.2
     * @sfribl
     * @sff #sftDropTbrgft
     * @sff #gftDropTbrgft
     */
    DropTbrgft dropTbrgft;

    /**
     * @sfribl
     * @sff #bdd
     */
    Vfdtor<PopupMfnu> popups;

    /**
     * A domponfnt's nbmf.
     * Tiis fifld dbn bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftNbmf
     * @sff #sftNbmf(String)
     */
    privbtf String nbmf;

    /**
     * A bool to dftfrminf wiftifr tif nbmf ibs
     * bffn sft fxpliditly. <dodf>nbmfExpliditlySft</dodf> will
     * bf fblsf if tif nbmf ibs not bffn sft bnd
     * truf if it ibs.
     *
     * @sfribl
     * @sff #gftNbmf
     * @sff #sftNbmf(String)
     */
    privbtf boolfbn nbmfExpliditlySft = fblsf;

    /**
     * Indidbtfs wiftifr tiis Componfnt dbn bf fodusfd.
     *
     * @sfribl
     * @sff #sftFodusbblf
     * @sff #isFodusbblf
     * @sindf 1.4
     */
    privbtf boolfbn fodusbblf = truf;

    privbtf stbtid finbl int FOCUS_TRAVERSABLE_UNKNOWN = 0;
    privbtf stbtid finbl int FOCUS_TRAVERSABLE_DEFAULT = 1;
    privbtf stbtid finbl int FOCUS_TRAVERSABLE_SET = 2;

    /**
     * Trbdks wiftifr tiis Componfnt is rflying on dffbult fodus trbvfsbbility.
     *
     * @sfribl
     * @sindf 1.4
     */
    privbtf int isFodusTrbvfrsbblfOvfrriddfn = FOCUS_TRAVERSABLE_UNKNOWN;

    /**
     * Tif fodus trbvfrsbl kfys. Tifsf kfys will gfnfrbtf fodus trbvfrsbl
     * bfibvior for Componfnts for wiidi fodus trbvfrsbl kfys brf fnbblfd. If b
     * vbluf of null is spfdififd for b trbvfrsbl kfy, tiis Componfnt inifrits
     * tibt trbvfrsbl kfy from its pbrfnt. If bll bndfstors of tiis Componfnt
     * ibvf null spfdififd for tibt trbvfrsbl kfy, tifn tif durrfnt
     * KfybobrdFodusMbnbgfr's dffbult trbvfrsbl kfy is usfd.
     *
     * @sfribl
     * @sff #sftFodusTrbvfrsblKfys
     * @sff #gftFodusTrbvfrsblKfys
     * @sindf 1.4
     */
    Sft<AWTKfyStrokf>[] fodusTrbvfrsblKfys;

    privbtf stbtid finbl String[] fodusTrbvfrsblKfyPropfrtyNbmfs = {
        "forwbrdFodusTrbvfrsblKfys",
        "bbdkwbrdFodusTrbvfrsblKfys",
        "upCydlfFodusTrbvfrsblKfys",
        "downCydlfFodusTrbvfrsblKfys"
    };

    /**
     * Indidbtfs wiftifr fodus trbvfrsbl kfys brf fnbblfd for tiis Componfnt.
     * Componfnts for wiidi fodus trbvfrsbl kfys brf disbblfd rfdfivf kfy
     * fvfnts for fodus trbvfrsbl kfys. Componfnts for wiidi fodus trbvfrsbl
     * kfys brf fnbblfd do not sff tifsf fvfnts; instfbd, tif fvfnts brf
     * butombtidblly donvfrtfd to trbvfrsbl opfrbtions.
     *
     * @sfribl
     * @sff #sftFodusTrbvfrsblKfysEnbblfd
     * @sff #gftFodusTrbvfrsblKfysEnbblfd
     * @sindf 1.4
     */
    privbtf boolfbn fodusTrbvfrsblKfysEnbblfd = truf;

    /**
     * Tif lodking objfdt for AWT domponfnt-trff bnd lbyout opfrbtions.
     *
     * @sff #gftTrffLodk
     */
    stbtid finbl Objfdt LOCK = nfw AWTTrffLodk();
    stbtid dlbss AWTTrffLodk {}

    /*
     * Tif domponfnt's AddfssControlContfxt.
     */
    privbtf trbnsifnt volbtilf AddfssControlContfxt bdd =
        AddfssControllfr.gftContfxt();

    /**
     * Minimum sizf.
     * (Tiis fifld pfribps siould ibvf bffn trbnsifnt).
     *
     * @sfribl
     */
    Dimfnsion minSizf;

    /**
     * Wiftifr or not sftMinimumSizf ibs bffn invokfd witi b non-null vbluf.
     */
    boolfbn minSizfSft;

    /**
     * Prfffrrfd sizf.
     * (Tiis fifld pfribps siould ibvf bffn trbnsifnt).
     *
     * @sfribl
     */
    Dimfnsion prffSizf;

    /**
     * Wiftifr or not sftPrfffrrfdSizf ibs bffn invokfd witi b non-null vbluf.
     */
    boolfbn prffSizfSft;

    /**
     * Mbximum sizf
     *
     * @sfribl
     */
    Dimfnsion mbxSizf;

    /**
     * Wiftifr or not sftMbximumSizf ibs bffn invokfd witi b non-null vbluf.
     */
    boolfbn mbxSizfSft;

    /**
     * Tif orifntbtion for tiis domponfnt.
     * @sff #gftComponfntOrifntbtion
     * @sff #sftComponfntOrifntbtion
     */
    trbnsifnt ComponfntOrifntbtion domponfntOrifntbtion
    = ComponfntOrifntbtion.UNKNOWN;

    /**
     * <dodf>nfwEvfntsOnly</dodf> will bf truf if tif fvfnt is
     * onf of tif fvfnt typfs fnbblfd for tif domponfnt.
     * It will tifn bllow for normbl prodfssing to
     * dontinuf.  If it is fblsf tif fvfnt is pbssfd
     * to tif domponfnt's pbrfnt bnd up tif bndfstor
     * trff until tif fvfnt ibs bffn donsumfd.
     *
     * @sfribl
     * @sff #dispbtdiEvfnt
     */
    boolfbn nfwEvfntsOnly = fblsf;
    trbnsifnt ComponfntListfnfr domponfntListfnfr;
    trbnsifnt FodusListfnfr fodusListfnfr;
    trbnsifnt HifrbrdiyListfnfr iifrbrdiyListfnfr;
    trbnsifnt HifrbrdiyBoundsListfnfr iifrbrdiyBoundsListfnfr;
    trbnsifnt KfyListfnfr kfyListfnfr;
    trbnsifnt MousfListfnfr mousfListfnfr;
    trbnsifnt MousfMotionListfnfr mousfMotionListfnfr;
    trbnsifnt MousfWifflListfnfr mousfWifflListfnfr;
    trbnsifnt InputMftiodListfnfr inputMftiodListfnfr;

    /** Intfrnbl, donstbnts for sfriblizbtion */
    finbl stbtid String bdtionListfnfrK = "bdtionL";
    finbl stbtid String bdjustmfntListfnfrK = "bdjustmfntL";
    finbl stbtid String domponfntListfnfrK = "domponfntL";
    finbl stbtid String dontbinfrListfnfrK = "dontbinfrL";
    finbl stbtid String fodusListfnfrK = "fodusL";
    finbl stbtid String itfmListfnfrK = "itfmL";
    finbl stbtid String kfyListfnfrK = "kfyL";
    finbl stbtid String mousfListfnfrK = "mousfL";
    finbl stbtid String mousfMotionListfnfrK = "mousfMotionL";
    finbl stbtid String mousfWifflListfnfrK = "mousfWifflL";
    finbl stbtid String tfxtListfnfrK = "tfxtL";
    finbl stbtid String ownfdWindowK = "ownfdL";
    finbl stbtid String windowListfnfrK = "windowL";
    finbl stbtid String inputMftiodListfnfrK = "inputMftiodL";
    finbl stbtid String iifrbrdiyListfnfrK = "iifrbrdiyL";
    finbl stbtid String iifrbrdiyBoundsListfnfrK = "iifrbrdiyBoundsL";
    finbl stbtid String windowStbtfListfnfrK = "windowStbtfL";
    finbl stbtid String windowFodusListfnfrK = "windowFodusL";

    /**
     * Tif <dodf>fvfntMbsk</dodf> is ONLY sft by subdlbssfs vib
     * <dodf>fnbblfEvfnts</dodf>.
     * Tif mbsk siould NOT bf sft wifn listfnfrs brf rfgistfrfd
     * so tibt wf dbn distinguisi tif difffrfndf bftwffn wifn
     * listfnfrs rfqufst fvfnts bnd subdlbssfs rfqufst tifm.
     * Onf bit is usfd to indidbtf wiftifr input mftiods brf
     * fnbblfd; tiis bit is sft by <dodf>fnbblfInputMftiods</dodf> bnd is
     * on by dffbult.
     *
     * @sfribl
     * @sff #fnbblfInputMftiods
     * @sff AWTEvfnt
     */
    long fvfntMbsk = AWTEvfnt.INPUT_METHODS_ENABLED_MASK;

    /**
     * Stbtid propfrtifs for indrfmfntbl drbwing.
     * @sff #imbgfUpdbtf
     */
    stbtid boolfbn isInd;
    stbtid int indRbtf;
    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        /* initiblizf JNI fifld bnd mftiod ids */
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        String s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                                               nfw GftPropfrtyAdtion("bwt.imbgf.indrfmfntbldrbw"));
        isInd = (s == null || s.fqubls("truf"));

        s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                                        nfw GftPropfrtyAdtion("bwt.imbgf.rfdrbwrbtf"));
        indRbtf = (s != null) ? Intfgfr.pbrsfInt(s) : 100;
    }

    /**
     * Ebsf-of-usf donstbnt for <dodf>gftAlignmfntY()</dodf>.
     * Spfdififs bn blignmfnt to tif top of tif domponfnt.
     * @sff     #gftAlignmfntY
     */
    publid stbtid finbl flobt TOP_ALIGNMENT = 0.0f;

    /**
     * Ebsf-of-usf donstbnt for <dodf>gftAlignmfntY</dodf> bnd
     * <dodf>gftAlignmfntX</dodf>. Spfdififs bn blignmfnt to
     * tif dfntfr of tif domponfnt
     * @sff     #gftAlignmfntX
     * @sff     #gftAlignmfntY
     */
    publid stbtid finbl flobt CENTER_ALIGNMENT = 0.5f;

    /**
     * Ebsf-of-usf donstbnt for <dodf>gftAlignmfntY</dodf>.
     * Spfdififs bn blignmfnt to tif bottom of tif domponfnt.
     * @sff     #gftAlignmfntY
     */
    publid stbtid finbl flobt BOTTOM_ALIGNMENT = 1.0f;

    /**
     * Ebsf-of-usf donstbnt for <dodf>gftAlignmfntX</dodf>.
     * Spfdififs bn blignmfnt to tif lfft sidf of tif domponfnt.
     * @sff     #gftAlignmfntX
     */
    publid stbtid finbl flobt LEFT_ALIGNMENT = 0.0f;

    /**
     * Ebsf-of-usf donstbnt for <dodf>gftAlignmfntX</dodf>.
     * Spfdififs bn blignmfnt to tif rigit sidf of tif domponfnt.
     * @sff     #gftAlignmfntX
     */
    publid stbtid finbl flobt RIGHT_ALIGNMENT = 1.0f;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -7644114512714619750L;

    /**
     * If bny <dodf>PropfrtyCibngfListfnfrs</dodf> ibvf bffn rfgistfrfd,
     * tif <dodf>dibngfSupport</dodf> fifld dfsdribfs tifm.
     *
     * @sfribl
     * @sindf 1.2
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #firfPropfrtyCibngf
     */
    privbtf PropfrtyCibngfSupport dibngfSupport;

    /*
     * In somf dbsfs using "tiis" bs bn objfdt to syndironizf by
     * dbn lfbd to b dfbdlodk if dlifnt dodf blso usfs syndironizbtion
     * by b domponfnt objfdt. For fvfry sudi situbtion rfvfblfd wf siould
     * donsidfr possibility of rfplbding "tiis" witi tif pbdkbgf privbtf
     * objfdtLodk objfdt introdudfd bflow. So fbr tifrf'rf 3 issufs known:
     * - CR 6708322 (tif gftNbmf/sftNbmf mftiods);
     * - CR 6608764 (tif PropfrtyCibngfListfnfr mbdiinfry);
     * - CR 7108598 (tif Contbinfr.pbint/KfybobrdFodusMbnbgfr.dlfbrMostRfdfntFodusOwnfr mftiods).
     *
     * Notf: tiis fifld is donsidfrfd finbl, tiougi rfbdObjfdt() proiibits
     * initiblizing finbl fiflds.
     */
    privbtf trbnsifnt Objfdt objfdtLodk = nfw Objfdt();
    Objfdt gftObjfdtLodk() {
        rfturn objfdtLodk;
    }

    /*
     * Rfturns tif bdd tiis domponfnt wbs donstrudtfd witi.
     */
    finbl AddfssControlContfxt gftAddfssControlContfxt() {
        if (bdd == null) {
            tirow nfw SfdurityExdfption("Componfnt is missing AddfssControlContfxt");
        }
        rfturn bdd;
    }

    boolfbn isPbdkfd = fblsf;

    /**
     * Psfudopbrbmftfr for dirfdt Gfomftry API (sftLodbtion, sftBounds sftSizf
     * to signbl sftBounds wibt's dibnging. Siould bf usfd undfr TrffLodk.
     * Tiis is only nffdfd duf to tif inbbility to dibngf tif dross-dblling
     * ordfr of publid bnd dfprfdbtfd mftiods.
     */
    privbtf int boundsOp = ComponfntPffr.DEFAULT_OPERATION;

    /**
     * Enumfrbtion of tif dommon wbys tif bbsflinf of b domponfnt dbn
     * dibngf bs tif sizf dibngfs.  Tif bbsflinf rfsizf bfibvior is
     * primbrily for lbyout mbnbgfrs tibt nffd to know iow tif
     * position of tif bbsflinf dibngfs bs tif domponfnt sizf dibngfs.
     * In gfnfrbl tif bbsflinf rfsizf bfibvior will bf vblid for sizfs
     * grfbtfr tibn or fqubl to tif minimum sizf (tif bdtubl minimum
     * sizf; not b dfvflopfr spfdififd minimum sizf).  For sizfs
     * smbllfr tibn tif minimum sizf tif bbsflinf mby dibngf in b wby
     * otifr tibn tif bbsflinf rfsizf bfibvior indidbtfs.  Similbrly,
     * bs tif sizf bpprobdifs <dodf>Intfgfr.MAX_VALUE</dodf> bnd/or
     * <dodf>Siort.MAX_VALUE</dodf> tif bbsflinf mby dibngf in b wby
     * otifr tibn tif bbsflinf rfsizf bfibvior indidbtfs.
     *
     * @sff #gftBbsflinfRfsizfBfibvior
     * @sff #gftBbsflinf(int,int)
     * @sindf 1.6
     */
    publid fnum BbsflinfRfsizfBfibvior {
        /**
         * Indidbtfs tif bbsflinf rfmbins fixfd rflbtivf to tif
         * y-origin.  Tibt is, <dodf>gftBbsflinf</dodf> rfturns
         * tif sbmf vbluf rfgbrdlfss of tif ifigit or widti.  For fxbmplf, b
         * <dodf>JLbbfl</dodf> dontbining non-fmpty tfxt witi b
         * vfrtidbl blignmfnt of <dodf>TOP</dodf> siould ibvf b
         * bbsflinf typf of <dodf>CONSTANT_ASCENT</dodf>.
         */
        CONSTANT_ASCENT,

        /**
         * Indidbtfs tif bbsflinf rfmbins fixfd rflbtivf to tif ifigit
         * bnd dofs not dibngf bs tif widti is vbrifd.  Tibt is, for
         * bny ifigit H tif difffrfndf bftwffn H bnd
         * <dodf>gftBbsflinf(w, H)</dodf> is tif sbmf.  For fxbmplf, b
         * <dodf>JLbbfl</dodf> dontbining non-fmpty tfxt witi b
         * vfrtidbl blignmfnt of <dodf>BOTTOM</dodf> siould ibvf b
         * bbsflinf typf of <dodf>CONSTANT_DESCENT</dodf>.
         */
        CONSTANT_DESCENT,

        /**
         * Indidbtfs tif bbsflinf rfmbins b fixfd distbndf from
         * tif dfntfr of tif domponfnt.  Tibt is, for bny ifigit H tif
         * difffrfndf bftwffn <dodf>gftBbsflinf(w, H)</dodf> bnd
         * <dodf>H / 2</dodf> is tif sbmf (plus or minus onf dfpfnding upon
         * rounding frror).
         * <p>
         * Bfdbusf of possiblf rounding frrors it is rfdommfndfd
         * you bsk for tif bbsflinf witi two donsfdutivf ifigits bnd usf
         * tif rfturn vbluf to dftfrminf if you nffd to pbd dbldulbtions
         * by 1.  Tif following siows iow to dbldulbtf tif bbsflinf for
         * bny ifigit:
         * <prf>
         *   Dimfnsion prfffrrfdSizf = domponfnt.gftPrfffrrfdSizf();
         *   int bbsflinf = gftBbsflinf(prfffrrfdSizf.widti,
         *                              prfffrrfdSizf.ifigit);
         *   int nfxtBbsflinf = gftBbsflinf(prfffrrfdSizf.widti,
         *                                  prfffrrfdSizf.ifigit + 1);
         *   // Amount to bdd to ifigit wifn dbldulbting wifrf bbsflinf
         *   // lbnds for b pbrtidulbr ifigit:
         *   int pbdding = 0;
         *   // Wifrf tif bbsflinf is rflbtivf to tif mid point
         *   int bbsflinfOffsft = bbsflinf - ifigit / 2;
         *   if (prfffrrfdSizf.ifigit % 2 == 0 &bmp;&bmp;
         *       bbsflinf != nfxtBbsflinf) {
         *       pbdding = 1;
         *   }
         *   flsf if (prfffrrfdSizf.ifigit % 2 == 1 &bmp;&bmp;
         *            bbsflinf == nfxtBbsflinf) {
         *       bbsflinfOffsft--;
         *       pbdding = 1;
         *   }
         *   // Tif following dbldulbtfs wifrf tif bbsflinf lbnds for
         *   // tif ifigit z:
         *   int dbldulbtfdBbsflinf = (z + pbdding) / 2 + bbsflinfOffsft;
         * </prf>
         */
        CENTER_OFFSET,

        /**
         * Indidbtfs tif bbsflinf rfsizf bfibvior dbn not bf fxprfssfd using
         * bny of tif otifr donstbnts.  Tiis mby blso indidbtf tif bbsflinf
         * vbrifs witi tif widti of tif domponfnt.  Tiis is blso rfturnfd
         * by domponfnts tibt do not ibvf b bbsflinf.
         */
        OTHER
    }

    /*
     * Tif sibpf sft witi tif bpplyCompoundSibpf() mftiod. It undludfs tif rfsult
     * of tif HW/LW mixing rflbtfd sibpf domputbtion. It mby blso indludf
     * tif usfr-spfdififd sibpf of tif domponfnt.
     * Tif 'null' vbluf mfbns tif domponfnt ibs normbl sibpf (or ibs no sibpf bt bll)
     * bnd bpplyCompoundSibpf() will skip tif following sibpf idfntidbl to normbl.
     */
    privbtf trbnsifnt Rfgion dompoundSibpf = null;

    /*
     * Rfprfsfnts tif sibpf of tiis ligitwfigit domponfnt to bf dut out from
     * ifbvywfigit domponfnts siould tify intfrsfdt. Possiblf vblufs:
     *    1. null - donsidfr tif sibpf rfdtbngulbr
     *    2. EMPTY_REGION - notiing gfts dut out (diildrfn still gft dut out)
     *    3. non-fmpty - tiis sibpf gfts dut out.
     */
    privbtf trbnsifnt Rfgion mixingCutoutRfgion = null;

    /*
     * Indidbtfs wiftifr bddNotify() is domplftf
     * (i.f. tif pffr is drfbtfd).
     */
    privbtf trbnsifnt boolfbn isAddNotifyComplftf = fblsf;

    /**
     * Siould only bf usfd in subdlbss gftBounds to difdk tibt pbrt of bounds
     * is bdtubly dibnging
     */
    int gftBoundsOp() {
        bssfrt Tirfbd.ioldsLodk(gftTrffLodk());
        rfturn boundsOp;
    }

    void sftBoundsOp(int op) {
        bssfrt Tirfbd.ioldsLodk(gftTrffLodk());
        if (op == ComponfntPffr.RESET_OPERATION) {
            boundsOp = ComponfntPffr.DEFAULT_OPERATION;
        } flsf
            if (boundsOp == ComponfntPffr.DEFAULT_OPERATION) {
                boundsOp = op;
            }
    }

    // Wiftifr tiis Componfnt ibs ibd tif bbdkground frbsf flbg
    // spfdififd vib SunToolkit.disbblfBbdkgroundErbsf(). Tiis is
    // nffdfd in ordfr to mbkf tiis fundtion work on X11 plbtforms,
    // wifrf durrfntly tifrf is no dibndf to intfrposf on tif drfbtion
    // of tif pffr bnd tifrfforf tif dbll to XSftBbdkground.
    trbnsifnt boolfbn bbdkgroundErbsfDisbblfd;

    stbtid {
        AWTAddfssor.sftComponfntAddfssor(nfw AWTAddfssor.ComponfntAddfssor() {
            publid void sftBbdkgroundErbsfDisbblfd(Componfnt domp, boolfbn disbblfd) {
                domp.bbdkgroundErbsfDisbblfd = disbblfd;
            }
            publid boolfbn gftBbdkgroundErbsfDisbblfd(Componfnt domp) {
                rfturn domp.bbdkgroundErbsfDisbblfd;
            }
            publid Rfdtbnglf gftBounds(Componfnt domp) {
                rfturn nfw Rfdtbnglf(domp.x, domp.y, domp.widti, domp.ifigit);
            }
            publid void sftMixingCutoutSibpf(Componfnt domp, Sibpf sibpf) {
                Rfgion rfgion = sibpf == null ?  null :
                    Rfgion.gftInstbndf(sibpf, null);

                syndironizfd (domp.gftTrffLodk()) {
                    boolfbn nffdSiowing = fblsf;
                    boolfbn nffdHiding = fblsf;

                    if (!domp.isNonOpbqufForMixing()) {
                        nffdHiding = truf;
                    }

                    domp.mixingCutoutRfgion = rfgion;

                    if (!domp.isNonOpbqufForMixing()) {
                        nffdSiowing = truf;
                    }

                    if (domp.isMixingNffdfd()) {
                        if (nffdHiding) {
                            domp.mixOnHiding(domp.isLigitwfigit());
                        }
                        if (nffdSiowing) {
                            domp.mixOnSiowing();
                        }
                    }
                }
            }

            publid void sftGrbpiidsConfigurbtion(Componfnt domp,
                    GrbpiidsConfigurbtion gd)
            {
                domp.sftGrbpiidsConfigurbtion(gd);
            }
            publid boolfbn rfqufstFodus(Componfnt domp, CbusfdFodusEvfnt.Cbusf dbusf) {
                rfturn domp.rfqufstFodus(dbusf);
            }
            publid boolfbn dbnBfFodusOwnfr(Componfnt domp) {
                rfturn domp.dbnBfFodusOwnfr();
            }

            publid boolfbn isVisiblf(Componfnt domp) {
                rfturn domp.isVisiblf_NoClifntCodf();
            }
            publid void sftRfqufstFodusControllfr
                (RfqufstFodusControllfr rfqufstControllfr)
            {
                 Componfnt.sftRfqufstFodusControllfr(rfqufstControllfr);
            }
            publid AppContfxt gftAppContfxt(Componfnt domp) {
                 rfturn domp.bppContfxt;
            }
            publid void sftAppContfxt(Componfnt domp, AppContfxt bppContfxt) {
                 domp.bppContfxt = bppContfxt;
            }
            publid Contbinfr gftPbrfnt(Componfnt domp) {
                rfturn domp.gftPbrfnt_NoClifntCodf();
            }
            publid void sftPbrfnt(Componfnt domp, Contbinfr pbrfnt) {
                domp.pbrfnt = pbrfnt;
            }
            publid void sftSizf(Componfnt domp, int widti, int ifigit) {
                domp.widti = widti;
                domp.ifigit = ifigit;
            }
            publid Point gftLodbtion(Componfnt domp) {
                rfturn domp.lodbtion_NoClifntCodf();
            }
            publid void sftLodbtion(Componfnt domp, int x, int y) {
                domp.x = x;
                domp.y = y;
            }
            publid boolfbn isEnbblfd(Componfnt domp) {
                rfturn domp.isEnbblfdImpl();
            }
            publid boolfbn isDisplbybblf(Componfnt domp) {
                rfturn domp.pffr != null;
            }
            publid Cursor gftCursor(Componfnt domp) {
                rfturn domp.gftCursor_NoClifntCodf();
            }
            publid ComponfntPffr gftPffr(Componfnt domp) {
                rfturn domp.pffr;
            }
            publid void sftPffr(Componfnt domp, ComponfntPffr pffr) {
                domp.pffr = pffr;
            }
            publid boolfbn isLigitwfigit(Componfnt domp) {
                rfturn (domp.pffr instbndfof LigitwfigitPffr);
            }
            publid boolfbn gftIgnorfRfpbint(Componfnt domp) {
                rfturn domp.ignorfRfpbint;
            }
            publid int gftWidti(Componfnt domp) {
                rfturn domp.widti;
            }
            publid int gftHfigit(Componfnt domp) {
                rfturn domp.ifigit;
            }
            publid int gftX(Componfnt domp) {
                rfturn domp.x;
            }
            publid int gftY(Componfnt domp) {
                rfturn domp.y;
            }
            publid Color gftForfground(Componfnt domp) {
                rfturn domp.forfground;
            }
            publid Color gftBbdkground(Componfnt domp) {
                rfturn domp.bbdkground;
            }
            publid void sftBbdkground(Componfnt domp, Color bbdkground) {
                domp.bbdkground = bbdkground;
            }
            publid Font gftFont(Componfnt domp) {
                rfturn domp.gftFont_NoClifntCodf();
            }
            publid void prodfssEvfnt(Componfnt domp, AWTEvfnt f) {
                domp.prodfssEvfnt(f);
            }

            publid AddfssControlContfxt gftAddfssControlContfxt(Componfnt domp) {
                rfturn domp.gftAddfssControlContfxt();
            }

            publid void rfvblidbtfSyndironously(Componfnt domp) {
                domp.rfvblidbtfSyndironously();
            }

            @Ovfrridf
            publid void drfbtfBufffrStrbtfgy(Componfnt domp, int numBufffrs,
                    BufffrCbpbbilitifs dbps) tirows AWTExdfption {
                domp.drfbtfBufffrStrbtfgy(numBufffrs, dbps);
            }

            @Ovfrridf
            publid BufffrStrbtfgy gftBufffrStrbtfgy(Componfnt domp) {
                rfturn domp.gftBufffrStrbtfgy();
            }
        });
    }

    /**
     * Construdts b nfw domponfnt. Clbss <dodf>Componfnt</dodf> dbn bf
     * fxtfndfd dirfdtly to drfbtf b ligitwfigit domponfnt tibt dofs not
     * utilizf bn opbquf nbtivf window. A ligitwfigit domponfnt must bf
     * iostfd by b nbtivf dontbinfr somfwifrf iigifr up in tif domponfnt
     * trff (for fxbmplf, by b <dodf>Frbmf</dodf> objfdt).
     */
    protfdtfd Componfnt() {
        bppContfxt = AppContfxt.gftAppContfxt();
    }

    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    void initiblizfFodusTrbvfrsblKfys() {
        fodusTrbvfrsblKfys = nfw Sft[3];
    }

    /**
     * Construdts b nbmf for tiis domponfnt.  Cbllfd by <dodf>gftNbmf</dodf>
     * wifn tif nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        rfturn null; // For stridt domplibndf witi prior plbtform vfrsions, b Componfnt
                     // tibt dofsn't sft its nbmf siould rfturn null from
                     // gftNbmf()
    }

    /**
     * Gfts tif nbmf of tif domponfnt.
     * @rfturn tiis domponfnt's nbmf
     * @sff    #sftNbmf
     * @sindf 1.1
     */
    publid String gftNbmf() {
        if (nbmf == null && !nbmfExpliditlySft) {
            syndironizfd(gftObjfdtLodk()) {
                if (nbmf == null && !nbmfExpliditlySft)
                    nbmf = donstrudtComponfntNbmf();
            }
        }
        rfturn nbmf;
    }

    /**
     * Sfts tif nbmf of tif domponfnt to tif spfdififd string.
     * @pbrbm nbmf  tif string tibt is to bf tiis
     *           domponfnt's nbmf
     * @sff #gftNbmf
     * @sindf 1.1
     */
    publid void sftNbmf(String nbmf) {
        String oldNbmf;
        syndironizfd(gftObjfdtLodk()) {
            oldNbmf = tiis.nbmf;
            tiis.nbmf = nbmf;
            nbmfExpliditlySft = truf;
        }
        firfPropfrtyCibngf("nbmf", oldNbmf, nbmf);
    }

    /**
     * Gfts tif pbrfnt of tiis domponfnt.
     * @rfturn tif pbrfnt dontbinfr of tiis domponfnt
     * @sindf 1.0
     */
    publid Contbinfr gftPbrfnt() {
        rfturn gftPbrfnt_NoClifntCodf();
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       Tiis fundtionblity is implfmfntfd in b pbdkbgf-privbtf mftiod
    //       to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Contbinfr gftPbrfnt_NoClifntCodf() {
        rfturn pbrfnt;
    }

    // Tiis mftiod is ovfrriddfn in tif Window dlbss to rfturn null,
    //    bfdbusf tif pbrfnt fifld of tif Window objfdt dontbins
    //    tif ownfr of tif window, not its pbrfnt.
    Contbinfr gftContbinfr() {
        rfturn gftPbrfnt_NoClifntCodf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * progrbms siould not dirfdtly mbnipulbtf pffrs;
     * rfplbdfd by <dodf>boolfbn isDisplbybblf()</dodf>.
     * @rfturn tif pffr for tiis domponfnt
     */
    @Dfprfdbtfd
    publid ComponfntPffr gftPffr() {
        rfturn pffr;
    }

    /**
     * Assodibtf b <dodf>DropTbrgft</dodf> witi tiis domponfnt.
     * Tif <dodf>Componfnt</dodf> will rfdfivf drops only if it
     * is fnbblfd.
     *
     * @sff #isEnbblfd
     * @pbrbm dt Tif DropTbrgft
     */

    publid syndironizfd void sftDropTbrgft(DropTbrgft dt) {
        if (dt == dropTbrgft || (dropTbrgft != null && dropTbrgft.fqubls(dt)))
            rfturn;

        DropTbrgft old;

        if ((old = dropTbrgft) != null) {
            if (pffr != null) dropTbrgft.rfmovfNotify(pffr);

            DropTbrgft t = dropTbrgft;

            dropTbrgft = null;

            try {
                t.sftComponfnt(null);
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                // ignorf it.
            }
        }

        // if wf ibvf b nfw onf, bnd wf ibvf b pffr, bdd it!

        if ((dropTbrgft = dt) != null) {
            try {
                dropTbrgft.sftComponfnt(tiis);
                if (pffr != null) dropTbrgft.bddNotify(pffr);
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                if (old != null) {
                    try {
                        old.sftComponfnt(tiis);
                        if (pffr != null) dropTbrgft.bddNotify(pffr);
                    } dbtdi (IllfgblArgumfntExdfption ibf1) {
                        // ignorf it!
                    }
                }
            }
        }
    }

    /**
     * Gfts tif <dodf>DropTbrgft</dodf> bssodibtfd witi tiis
     * <dodf>Componfnt</dodf>.
     *
     * @rfturn tif drop tbrgft
     */

    publid syndironizfd DropTbrgft gftDropTbrgft() { rfturn dropTbrgft; }

    /**
     * Gfts tif <dodf>GrbpiidsConfigurbtion</dodf> bssodibtfd witi tiis
     * <dodf>Componfnt</dodf>.
     * If tif <dodf>Componfnt</dodf> ibs not bffn bssignfd b spfdifid
     * <dodf>GrbpiidsConfigurbtion</dodf>,
     * tif <dodf>GrbpiidsConfigurbtion</dodf> of tif
     * <dodf>Componfnt</dodf> objfdt's top-lfvfl dontbinfr is
     * rfturnfd.
     * If tif <dodf>Componfnt</dodf> ibs bffn drfbtfd, but not yft bddfd
     * to b <dodf>Contbinfr</dodf>, tiis mftiod rfturns <dodf>null</dodf>.
     *
     * @rfturn tif <dodf>GrbpiidsConfigurbtion</dodf> usfd by tiis
     *          <dodf>Componfnt</dodf> or <dodf>null</dodf>
     * @sindf 1.3
     */
    publid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        syndironizfd(gftTrffLodk()) {
            rfturn gftGrbpiidsConfigurbtion_NoClifntCodf();
        }
    }

    finbl GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion_NoClifntCodf() {
        rfturn grbpiidsConfig;
    }

    void sftGrbpiidsConfigurbtion(GrbpiidsConfigurbtion gd) {
        syndironizfd(gftTrffLodk()) {
            if (updbtfGrbpiidsDbtb(gd)) {
                rfmovfNotify();
                bddNotify();
            }
        }
    }

    boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        difdkTrffLodk();

        if (grbpiidsConfig == gd) {
            rfturn fblsf;
        }

        grbpiidsConfig = gd;

        ComponfntPffr pffr = gftPffr();
        if (pffr != null) {
            rfturn pffr.updbtfGrbpiidsDbtb(gd);
        }
        rfturn fblsf;
    }

    /**
     * Cifdks tibt tiis domponfnt's <dodf>GrbpiidsDfvidf</dodf>
     * <dodf>idString</dodf> mbtdifs tif string brgumfnt.
     */
    void difdkGD(String stringID) {
        if (grbpiidsConfig != null) {
            if (!grbpiidsConfig.gftDfvidf().gftIDstring().fqubls(stringID)) {
                tirow nfw IllfgblArgumfntExdfption(
                                                   "bdding b dontbinfr to b dontbinfr on b difffrfnt GrbpiidsDfvidf");
            }
        }
    }

    /**
     * Gfts tiis domponfnt's lodking objfdt (tif objfdt tibt owns tif tirfbd
     * syndironizbtion monitor) for AWT domponfnt-trff bnd lbyout
     * opfrbtions.
     * @rfturn tiis domponfnt's lodking objfdt
     */
    publid finbl Objfdt gftTrffLodk() {
        rfturn LOCK;
    }

    finbl void difdkTrffLodk() {
        if (!Tirfbd.ioldsLodk(gftTrffLodk())) {
            tirow nfw IllfgblStbtfExdfption("Tiis fundtion siould bf dbllfd wiilf iolding trffLodk");
        }
    }

    /**
     * Gfts tif toolkit of tiis domponfnt. Notf tibt
     * tif frbmf tibt dontbins b domponfnt dontrols wiidi
     * toolkit is usfd by tibt domponfnt. Tifrfforf if tif domponfnt
     * is movfd from onf frbmf to bnotifr, tif toolkit it usfs mby dibngf.
     * @rfturn  tif toolkit of tiis domponfnt
     * @sindf 1.0
     */
    publid Toolkit gftToolkit() {
        rfturn gftToolkitImpl();
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl Toolkit gftToolkitImpl() {
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            rfturn pbrfnt.gftToolkitImpl();
        }
        rfturn Toolkit.gftDffbultToolkit();
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt is vblid. A domponfnt is vblid
     * wifn it is dorrfdtly sizfd bnd positionfd witiin its pbrfnt
     * dontbinfr bnd bll its diildrfn brf blso vblid.
     * In ordfr to bddount for pffrs' sizf rfquirfmfnts, domponfnts brf invblidbtfd
     * bfforf tify brf first siown on tif sdrffn. By tif timf tif pbrfnt dontbinfr
     * is fully rfblizfd, bll its domponfnts will bf vblid.
     * @rfturn <dodf>truf</dodf> if tif domponfnt is vblid, <dodf>fblsf</dodf>
     * otifrwisf
     * @sff #vblidbtf
     * @sff #invblidbtf
     * @sindf 1.0
     */
    publid boolfbn isVblid() {
        rfturn (pffr != null) && vblid;
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt is displbybblf. A domponfnt is
     * displbybblf wifn it is donnfdtfd to b nbtivf sdrffn rfsourdf.
     * <p>
     * A domponfnt is mbdf displbybblf fitifr wifn it is bddfd to
     * b displbybblf dontbinmfnt iifrbrdiy or wifn its dontbinmfnt
     * iifrbrdiy is mbdf displbybblf.
     * A dontbinmfnt iifrbrdiy is mbdf displbybblf wifn its bndfstor
     * window is fitifr pbdkfd or mbdf visiblf.
     * <p>
     * A domponfnt is mbdf undisplbybblf fitifr wifn it is rfmovfd from
     * b displbybblf dontbinmfnt iifrbrdiy or wifn its dontbinmfnt iifrbrdiy
     * is mbdf undisplbybblf.  A dontbinmfnt iifrbrdiy is mbdf
     * undisplbybblf wifn its bndfstor window is disposfd.
     *
     * @rfturn <dodf>truf</dodf> if tif domponfnt is displbybblf,
     * <dodf>fblsf</dodf> otifrwisf
     * @sff Contbinfr#bdd(Componfnt)
     * @sff Window#pbdk
     * @sff Window#siow
     * @sff Contbinfr#rfmovf(Componfnt)
     * @sff Window#disposf
     * @sindf 1.2
     */
    publid boolfbn isDisplbybblf() {
        rfturn gftPffr() != null;
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt siould bf visiblf wifn its
     * pbrfnt is visiblf. Componfnts brf
     * initiblly visiblf, witi tif fxdfption of top lfvfl domponfnts sudi
     * bs <dodf>Frbmf</dodf> objfdts.
     * @rfturn <dodf>truf</dodf> if tif domponfnt is visiblf,
     * <dodf>fblsf</dodf> otifrwisf
     * @sff #sftVisiblf
     * @sindf 1.0
     */
    @Trbnsifnt
    publid boolfbn isVisiblf() {
        rfturn isVisiblf_NoClifntCodf();
    }
    finbl boolfbn isVisiblf_NoClifntCodf() {
        rfturn visiblf;
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt will bf displbyfd on tif sdrffn.
     * @rfturn <dodf>truf</dodf> if tif domponfnt bnd bll of its bndfstors
     *          until b toplfvfl window or null pbrfnt brf visiblf,
     *          <dodf>fblsf</dodf> otifrwisf
     */
    boolfbn isRfdursivflyVisiblf() {
        rfturn visiblf && (pbrfnt == null || pbrfnt.isRfdursivflyVisiblf());
    }

    /**
     * Trbnslbtfs bbsolutf doordinbtfs into doordinbtfs in tif doordinbtf
     * spbdf of tiis domponfnt.
     */
    Point pointRflbtivfToComponfnt(Point bbsolutf) {
        Point dompCoords = gftLodbtionOnSdrffn();
        rfturn nfw Point(bbsolutf.x - dompCoords.x,
                         bbsolutf.y - dompCoords.y);
    }

    /**
     * Assuming tibt mousf lodbtion is storfd in PointfrInfo pbssfd
     * to tiis mftiod, it finds b Componfnt tibt is in tif sbmf
     * Window bs tiis Componfnt bnd is lodbtfd undfr tif mousf pointfr.
     * If no sudi Componfnt fxists, null is rfturnfd.
     * NOTE: tiis mftiod siould bf dbllfd undfr tif protfdtion of
     * trff lodk, bs it is donf in Componfnt.gftMousfPosition() bnd
     * Contbinfr.gftMousfPosition(boolfbn).
     */
    Componfnt findUndfrMousfInWindow(PointfrInfo pi) {
        if (!isSiowing()) {
            rfturn null;
        }
        Window win = gftContbiningWindow();
        if (!Toolkit.gftDffbultToolkit().gftMousfInfoPffr().isWindowUndfrMousf(win)) {
            rfturn null;
        }
        finbl boolfbn INCLUDE_DISABLED = truf;
        Point rflbtivfToWindow = win.pointRflbtivfToComponfnt(pi.gftLodbtion());
        Componfnt inTifSbmfWindow = win.findComponfntAt(rflbtivfToWindow.x,
                                                        rflbtivfToWindow.y,
                                                        INCLUDE_DISABLED);
        rfturn inTifSbmfWindow;
    }

    /**
     * Rfturns tif position of tif mousf pointfr in tiis <dodf>Componfnt</dodf>'s
     * doordinbtf spbdf if tif <dodf>Componfnt</dodf> is dirfdtly undfr tif mousf
     * pointfr, otifrwisf rfturns <dodf>null</dodf>.
     * If tif <dodf>Componfnt</dodf> is not siowing on tif sdrffn, tiis mftiod
     * rfturns <dodf>null</dodf> fvfn if tif mousf pointfr is bbovf tif brfb
     * wifrf tif <dodf>Componfnt</dodf> would bf displbyfd.
     * If tif <dodf>Componfnt</dodf> is pbrtiblly or fully obsdurfd by otifr
     * <dodf>Componfnt</dodf>s or nbtivf windows, tiis mftiod rfturns b non-null
     * vbluf only if tif mousf pointfr is lodbtfd bbovf tif unobsdurfd pbrt of tif
     * <dodf>Componfnt</dodf>.
     * <p>
     * For <dodf>Contbinfr</dodf>s it rfturns b non-null vbluf if tif mousf is
     * bbovf tif <dodf>Contbinfr</dodf> itsflf or bbovf bny of its dfsdfndbnts.
     * Usf {@link Contbinfr#gftMousfPosition(boolfbn)} if you nffd to fxdludf diildrfn.
     * <p>
     * Somftimfs tif fxbdt mousf doordinbtfs brf not importbnt, bnd tif only tiing
     * tibt mbttfrs is wiftifr b spfdifid <dodf>Componfnt</dodf> is undfr tif mousf
     * pointfr. If tif rfturn vbluf of tiis mftiod is <dodf>null</dodf>, mousf
     * pointfr is not dirfdtly bbovf tif <dodf>Componfnt</dodf>.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf
     * @sff       #isSiowing
     * @sff       Contbinfr#gftMousfPosition
     * @rfturn    mousf doordinbtfs rflbtivf to tiis <dodf>Componfnt</dodf>, or null
     * @sindf     1.5
     */
    publid Point gftMousfPosition() tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        PointfrInfo pi = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                                                     nfw jbvb.sfdurity.PrivilfgfdAdtion<PointfrInfo>() {
                                                                         publid PointfrInfo run() {
                                                                             rfturn MousfInfo.gftPointfrInfo();
                                                                         }
                                                                     }
                                                                     );

        syndironizfd (gftTrffLodk()) {
            Componfnt inTifSbmfWindow = findUndfrMousfInWindow(pi);
            if (!isSbmfOrAndfstorOf(inTifSbmfWindow, truf)) {
                rfturn null;
            }
            rfturn pointRflbtivfToComponfnt(pi.gftLodbtion());
        }
    }

    /**
     * Ovfrriddfn in Contbinfr. Must bf dbllfd undfr TrffLodk.
     */
    boolfbn isSbmfOrAndfstorOf(Componfnt domp, boolfbn bllowCiildrfn) {
        rfturn domp == tiis;
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt is siowing on sdrffn. Tiis mfbns
     * tibt tif domponfnt must bf visiblf, bnd it must bf in b dontbinfr
     * tibt is visiblf bnd siowing.
     * <p>
     * <strong>Notf:</strong> somftimfs tifrf is no wby to dftfdt wiftifr tif
     * {@dodf Componfnt} is bdtublly visiblf to tif usfr.  Tiis dbn ibppfn wifn:
     * <ul>
     * <li>tif domponfnt ibs bffn bddfd to b visiblf {@dodf SdrollPbnf} but
     * tif {@dodf Componfnt} is not durrfntly in tif sdroll pbnf's vifw port.
     * <li>tif {@dodf Componfnt} is obsdurfd by bnotifr {@dodf Componfnt} or
     * {@dodf Contbinfr}.
     * </ul>
     * @rfturn <dodf>truf</dodf> if tif domponfnt is siowing,
     *          <dodf>fblsf</dodf> otifrwisf
     * @sff #sftVisiblf
     * @sindf 1.0
     */
    publid boolfbn isSiowing() {
        if (visiblf && (pffr != null)) {
            Contbinfr pbrfnt = tiis.pbrfnt;
            rfturn (pbrfnt == null) || pbrfnt.isSiowing();
        }
        rfturn fblsf;
    }

    /**
     * Dftfrminfs wiftifr tiis domponfnt is fnbblfd. An fnbblfd domponfnt
     * dbn rfspond to usfr input bnd gfnfrbtf fvfnts. Componfnts brf
     * fnbblfd initiblly by dffbult. A domponfnt mby bf fnbblfd or disbblfd by
     * dblling its <dodf>sftEnbblfd</dodf> mftiod.
     * @rfturn <dodf>truf</dodf> if tif domponfnt is fnbblfd,
     *          <dodf>fblsf</dodf> otifrwisf
     * @sff #sftEnbblfd
     * @sindf 1.0
     */
    publid boolfbn isEnbblfd() {
        rfturn isEnbblfdImpl();
    }

    /*
     * Tiis is dbllfd by tif nbtivf dodf, so dlifnt dodf dbn't
     * bf dbllfd on tif toolkit tirfbd.
     */
    finbl boolfbn isEnbblfdImpl() {
        rfturn fnbblfd;
    }

    /**
     * Enbblfs or disbblfs tiis domponfnt, dfpfnding on tif vbluf of tif
     * pbrbmftfr <dodf>b</dodf>. An fnbblfd domponfnt dbn rfspond to usfr
     * input bnd gfnfrbtf fvfnts. Componfnts brf fnbblfd initiblly by dffbult.
     *
     * <p>Notf: Disbbling b ligitwfigit domponfnt dofs not prfvfnt it from
     * rfdfiving MousfEvfnts.
     * <p>Notf: Disbbling b ifbvywfigit dontbinfr prfvfnts bll domponfnts
     * in tiis dontbinfr from rfdfiving bny input fvfnts.  But disbbling b
     * ligitwfigit dontbinfr bfffdts only tiis dontbinfr.
     *
     * @pbrbm     b   If <dodf>truf</dodf>, tiis domponfnt is
     *            fnbblfd; otifrwisf tiis domponfnt is disbblfd
     * @sff #isEnbblfd
     * @sff #isLigitwfigit
     * @sindf 1.1
     */
    publid void sftEnbblfd(boolfbn b) {
        fnbblf(b);
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftEnbblfd(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void fnbblf() {
        if (!fnbblfd) {
            syndironizfd (gftTrffLodk()) {
                fnbblfd = truf;
                ComponfntPffr pffr = tiis.pffr;
                if (pffr != null) {
                    pffr.sftEnbblfd(truf);
                    if (visiblf) {
                        updbtfCursorImmfdibtfly();
                    }
                }
            }
            if (bddfssiblfContfxt != null) {
                bddfssiblfContfxt.firfPropfrtyCibngf(
                                                     AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                     null, AddfssiblfStbtf.ENABLED);
            }
        }
    }

    /**
     * Enbblfs or disbblfs tiis domponfnt.
     *
     * @pbrbm  b {@dodf truf} to fnbblf tiis domponfnt;
     *         otifrwisf {@dodf fblsf}
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftEnbblfd(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void fnbblf(boolfbn b) {
        if (b) {
            fnbblf();
        } flsf {
            disbblf();
        }
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftEnbblfd(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void disbblf() {
        if (fnbblfd) {
            KfybobrdFodusMbnbgfr.dlfbrMostRfdfntFodusOwnfr(tiis);
            syndironizfd (gftTrffLodk()) {
                fnbblfd = fblsf;
                // A disbblfd lw dontbinfr is bllowfd to dontbin b fodus ownfr.
                if ((isFodusOwnfr() || (dontbinsFodus() && !isLigitwfigit())) &&
                    KfybobrdFodusMbnbgfr.isAutoFodusTrbnsffrEnbblfd())
                {
                    // Don't dlfbr tif globbl fodus ownfr. If trbnsffrFodus
                    // fbils, wf wbnt tif fodus to stby on tif disbblfd
                    // Componfnt so tibt kfybobrd trbvfrsbl, ft. bl. still
                    // mbkfs sfnsf to tif usfr.
                    trbnsffrFodus(fblsf);
                }
                ComponfntPffr pffr = tiis.pffr;
                if (pffr != null) {
                    pffr.sftEnbblfd(fblsf);
                    if (visiblf) {
                        updbtfCursorImmfdibtfly();
                    }
                }
            }
            if (bddfssiblfContfxt != null) {
                bddfssiblfContfxt.firfPropfrtyCibngf(
                                                     AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                     null, AddfssiblfStbtf.ENABLED);
            }
        }
    }

    /**
     * Rfturns truf if tiis domponfnt is pbintfd to bn offsdrffn imbgf
     * ("bufffr") tibt's dopifd to tif sdrffn lbtfr.  Componfnt
     * subdlbssfs tibt support doublf bufffring siould ovfrridf tiis
     * mftiod to rfturn truf if doublf bufffring is fnbblfd.
     *
     * @rfturn fblsf by dffbult
     */
    publid boolfbn isDoublfBufffrfd() {
        rfturn fblsf;
    }

    /**
     * Enbblfs or disbblfs input mftiod support for tiis domponfnt. If input
     * mftiod support is fnbblfd bnd tif domponfnt blso prodfssfs kfy fvfnts,
     * indoming fvfnts brf offfrfd to
     * tif durrfnt input mftiod bnd will only bf prodfssfd by tif domponfnt or
     * dispbtdifd to its listfnfrs if tif input mftiod dofs not donsumf tifm.
     * By dffbult, input mftiod support is fnbblfd.
     *
     * @pbrbm fnbblf truf to fnbblf, fblsf to disbblf
     * @sff #prodfssKfyEvfnt
     * @sindf 1.2
     */
    publid void fnbblfInputMftiods(boolfbn fnbblf) {
        if (fnbblf) {
            if ((fvfntMbsk & AWTEvfnt.INPUT_METHODS_ENABLED_MASK) != 0)
                rfturn;

            // If tiis domponfnt blrfbdy ibs fodus, tifn bdtivbtf tif
            // input mftiod by dispbtdiing b syntifsizfd fodus gbinfd
            // fvfnt.
            if (isFodusOwnfr()) {
                InputContfxt inputContfxt = gftInputContfxt();
                if (inputContfxt != null) {
                    FodusEvfnt fodusGbinfdEvfnt =
                        nfw FodusEvfnt(tiis, FodusEvfnt.FOCUS_GAINED);
                    inputContfxt.dispbtdiEvfnt(fodusGbinfdEvfnt);
                }
            }

            fvfntMbsk |= AWTEvfnt.INPUT_METHODS_ENABLED_MASK;
        } flsf {
            if ((fvfntMbsk & AWTEvfnt.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContfxt inputContfxt = gftInputContfxt();
                if (inputContfxt != null) {
                    inputContfxt.fndComposition();
                    inputContfxt.rfmovfNotify(tiis);
                }
            }
            fvfntMbsk &= ~AWTEvfnt.INPUT_METHODS_ENABLED_MASK;
        }
    }

    /**
     * Siows or iidfs tiis domponfnt dfpfnding on tif vbluf of pbrbmftfr
     * <dodf>b</dodf>.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm b  if <dodf>truf</dodf>, siows tiis domponfnt;
     * otifrwisf, iidfs tiis domponfnt
     * @sff #isVisiblf
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftVisiblf(boolfbn b) {
        siow(b);
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftVisiblf(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void siow() {
        if (!visiblf) {
            syndironizfd (gftTrffLodk()) {
                visiblf = truf;
                mixOnSiowing();
                ComponfntPffr pffr = tiis.pffr;
                if (pffr != null) {
                    pffr.sftVisiblf(truf);
                    drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                          tiis, pbrfnt,
                                          HifrbrdiyEvfnt.SHOWING_CHANGED,
                                          Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
                    if (pffr instbndfof LigitwfigitPffr) {
                        rfpbint();
                    }
                    updbtfCursorImmfdibtfly();
                }

                if (domponfntListfnfr != null ||
                    (fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0 ||
                    Toolkit.fnbblfdOnToolkit(AWTEvfnt.COMPONENT_EVENT_MASK)) {
                    ComponfntEvfnt f = nfw ComponfntEvfnt(tiis,
                                                          ComponfntEvfnt.COMPONENT_SHOWN);
                    Toolkit.gftEvfntQufuf().postEvfnt(f);
                }
            }
            Contbinfr pbrfnt = tiis.pbrfnt;
            if (pbrfnt != null) {
                pbrfnt.invblidbtf();
            }
        }
    }

    /**
     * Mbkfs tiis domponfnt visiblf or invisiblf.
     *
     * @pbrbm  b {@dodf truf} to mbkf tiis domponfnt visiblf;
     *         otifrwisf {@dodf fblsf}
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftVisiblf(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void siow(boolfbn b) {
        if (b) {
            siow();
        } flsf {
            iidf();
        }
    }

    boolfbn dontbinsFodus() {
        rfturn isFodusOwnfr();
    }

    void dlfbrMostRfdfntFodusOwnfrOnHidf() {
        KfybobrdFodusMbnbgfr.dlfbrMostRfdfntFodusOwnfr(tiis);
    }

    void dlfbrCurrfntFodusCydlfRootOnHidf() {
        /* do notiing */
    }

    /*
     * Dflftf rfffrfndfs from LigitwfitiDispbtdifr of b ifbvywfigit pbrfnt
     */
    void dlfbrLigitwfigitDispbtdifrOnRfmovf(Componfnt rfmovfdComponfnt) {
        if (pbrfnt != null) {
            pbrfnt.dlfbrLigitwfigitDispbtdifrOnRfmovf(rfmovfdComponfnt);
        }
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftVisiblf(boolfbn)</dodf>.
     */
    @Dfprfdbtfd
    publid void iidf() {
        isPbdkfd = fblsf;

        if (visiblf) {
            dlfbrCurrfntFodusCydlfRootOnHidf();
            dlfbrMostRfdfntFodusOwnfrOnHidf();
            syndironizfd (gftTrffLodk()) {
                visiblf = fblsf;
                mixOnHiding(isLigitwfigit());
                if (dontbinsFodus() && KfybobrdFodusMbnbgfr.isAutoFodusTrbnsffrEnbblfd()) {
                    trbnsffrFodus(truf);
                }
                ComponfntPffr pffr = tiis.pffr;
                if (pffr != null) {
                    pffr.sftVisiblf(fblsf);
                    drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                          tiis, pbrfnt,
                                          HifrbrdiyEvfnt.SHOWING_CHANGED,
                                          Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
                    if (pffr instbndfof LigitwfigitPffr) {
                        rfpbint();
                    }
                    updbtfCursorImmfdibtfly();
                }
                if (domponfntListfnfr != null ||
                    (fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0 ||
                    Toolkit.fnbblfdOnToolkit(AWTEvfnt.COMPONENT_EVENT_MASK)) {
                    ComponfntEvfnt f = nfw ComponfntEvfnt(tiis,
                                                          ComponfntEvfnt.COMPONENT_HIDDEN);
                    Toolkit.gftEvfntQufuf().postEvfnt(f);
                }
            }
            Contbinfr pbrfnt = tiis.pbrfnt;
            if (pbrfnt != null) {
                pbrfnt.invblidbtf();
            }
        }
    }

    /**
     * Gfts tif forfground dolor of tiis domponfnt.
     * @rfturn tiis domponfnt's forfground dolor; if tiis domponfnt dofs
     * not ibvf b forfground dolor, tif forfground dolor of its pbrfnt
     * is rfturnfd
     * @sff #sftForfground
     * @sindf 1.0
     * @bfbninfo
     *       bound: truf
     */
    @Trbnsifnt
    publid Color gftForfground() {
        Color forfground = tiis.forfground;
        if (forfground != null) {
            rfturn forfground;
        }
        Contbinfr pbrfnt = tiis.pbrfnt;
        rfturn (pbrfnt != null) ? pbrfnt.gftForfground() : null;
    }

    /**
     * Sfts tif forfground dolor of tiis domponfnt.
     * @pbrbm d tif dolor to bfdomf tiis domponfnt's
     *          forfground dolor; if tiis pbrbmftfr is <dodf>null</dodf>
     *          tifn tiis domponfnt will inifrit
     *          tif forfground dolor of its pbrfnt
     * @sff #gftForfground
     * @sindf 1.0
     */
    publid void sftForfground(Color d) {
        Color oldColor = forfground;
        ComponfntPffr pffr = tiis.pffr;
        forfground = d;
        if (pffr != null) {
            d = gftForfground();
            if (d != null) {
                pffr.sftForfground(d);
            }
        }
        // Tiis is b bound propfrty, so rfport tif dibngf to
        // bny rfgistfrfd listfnfrs.  (Cifbp if tifrf brf nonf.)
        firfPropfrtyCibngf("forfground", oldColor, d);
    }

    /**
     * Rfturns wiftifr tif forfground dolor ibs bffn fxpliditly sft for tiis
     * Componfnt. If tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Componfnt is
     * inifriting its forfground dolor from bn bndfstor.
     *
     * @rfturn <dodf>truf</dodf> if tif forfground dolor ibs bffn fxpliditly
     *         sft for tiis Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isForfgroundSft() {
        rfturn (forfground != null);
    }

    /**
     * Gfts tif bbdkground dolor of tiis domponfnt.
     * @rfturn tiis domponfnt's bbdkground dolor; if tiis domponfnt dofs
     *          not ibvf b bbdkground dolor,
     *          tif bbdkground dolor of its pbrfnt is rfturnfd
     * @sff #sftBbdkground
     * @sindf 1.0
     */
    @Trbnsifnt
    publid Color gftBbdkground() {
        Color bbdkground = tiis.bbdkground;
        if (bbdkground != null) {
            rfturn bbdkground;
        }
        Contbinfr pbrfnt = tiis.pbrfnt;
        rfturn (pbrfnt != null) ? pbrfnt.gftBbdkground() : null;
    }

    /**
     * Sfts tif bbdkground dolor of tiis domponfnt.
     * <p>
     * Tif bbdkground dolor bfffdts fbdi domponfnt difffrfntly bnd tif
     * pbrts of tif domponfnt tibt brf bfffdtfd by tif bbdkground dolor
     * mby difffr bftwffn opfrbting systfms.
     *
     * @pbrbm d tif dolor to bfdomf tiis domponfnt's dolor;
     *          if tiis pbrbmftfr is <dodf>null</dodf>, tifn tiis
     *          domponfnt will inifrit tif bbdkground dolor of its pbrfnt
     * @sff #gftBbdkground
     * @sindf 1.0
     * @bfbninfo
     *       bound: truf
     */
    publid void sftBbdkground(Color d) {
        Color oldColor = bbdkground;
        ComponfntPffr pffr = tiis.pffr;
        bbdkground = d;
        if (pffr != null) {
            d = gftBbdkground();
            if (d != null) {
                pffr.sftBbdkground(d);
            }
        }
        // Tiis is b bound propfrty, so rfport tif dibngf to
        // bny rfgistfrfd listfnfrs.  (Cifbp if tifrf brf nonf.)
        firfPropfrtyCibngf("bbdkground", oldColor, d);
    }

    /**
     * Rfturns wiftifr tif bbdkground dolor ibs bffn fxpliditly sft for tiis
     * Componfnt. If tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Componfnt is
     * inifriting its bbdkground dolor from bn bndfstor.
     *
     * @rfturn <dodf>truf</dodf> if tif bbdkground dolor ibs bffn fxpliditly
     *         sft for tiis Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isBbdkgroundSft() {
        rfturn (bbdkground != null);
    }

    /**
     * Gfts tif font of tiis domponfnt.
     * @rfturn tiis domponfnt's font; if b font ibs not bffn sft
     * for tiis domponfnt, tif font of its pbrfnt is rfturnfd
     * @sff #sftFont
     * @sindf 1.0
     */
    @Trbnsifnt
    publid Font gftFont() {
        rfturn gftFont_NoClifntCodf();
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       Tiis fundtionblity is implfmfntfd in b pbdkbgf-privbtf mftiod
    //       to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Font gftFont_NoClifntCodf() {
        Font font = tiis.font;
        if (font != null) {
            rfturn font;
        }
        Contbinfr pbrfnt = tiis.pbrfnt;
        rfturn (pbrfnt != null) ? pbrfnt.gftFont_NoClifntCodf() : null;
    }

    /**
     * Sfts tif font of tiis domponfnt.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm f tif font to bfdomf tiis domponfnt's font;
     *          if tiis pbrbmftfr is <dodf>null</dodf> tifn tiis
     *          domponfnt will inifrit tif font of its pbrfnt
     * @sff #gftFont
     * @sff #invblidbtf
     * @sindf 1.0
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFont(Font f) {
        Font oldFont, nfwFont;
        syndironizfd(gftTrffLodk()) {
            oldFont = font;
            nfwFont = font = f;
            ComponfntPffr pffr = tiis.pffr;
            if (pffr != null) {
                f = gftFont();
                if (f != null) {
                    pffr.sftFont(f);
                    pffrFont = f;
                }
            }
        }
        // Tiis is b bound propfrty, so rfport tif dibngf to
        // bny rfgistfrfd listfnfrs.  (Cifbp if tifrf brf nonf.)
        firfPropfrtyCibngf("font", oldFont, nfwFont);

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        // Fix for 6213660. Siould dompbrf old bnd nfw fonts bnd do not
        // dbll invblidbtf() if tify brf fqubl.
        if (f != oldFont && (oldFont == null ||
                                      !oldFont.fqubls(f))) {
            invblidbtfIfVblid();
        }
    }

    /**
     * Rfturns wiftifr tif font ibs bffn fxpliditly sft for tiis Componfnt. If
     * tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Componfnt is inifriting its
     * font from bn bndfstor.
     *
     * @rfturn <dodf>truf</dodf> if tif font ibs bffn fxpliditly sft for tiis
     *         Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isFontSft() {
        rfturn (font != null);
    }

    /**
     * Gfts tif lodblf of tiis domponfnt.
     * @rfturn tiis domponfnt's lodblf; if tiis domponfnt dofs not
     *          ibvf b lodblf, tif lodblf of its pbrfnt is rfturnfd
     * @sff #sftLodblf
     * @fxdfption IllfgblComponfntStbtfExdfption if tif <dodf>Componfnt</dodf>
     *          dofs not ibvf its own lodblf bnd ibs not yft bffn bddfd to
     *          b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf dftfrminfd
     *          from tif dontbining pbrfnt
     * @sindf  1.1
     */
    publid Lodblf gftLodblf() {
        Lodblf lodblf = tiis.lodblf;
        if (lodblf != null) {
            rfturn lodblf;
        }
        Contbinfr pbrfnt = tiis.pbrfnt;

        if (pbrfnt == null) {
            tirow nfw IllfgblComponfntStbtfExdfption("Tiis domponfnt must ibvf b pbrfnt in ordfr to dftfrminf its lodblf");
        } flsf {
            rfturn pbrfnt.gftLodblf();
        }
    }

    /**
     * Sfts tif lodblf of tiis domponfnt.  Tiis is b bound propfrty.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm l tif lodblf to bfdomf tiis domponfnt's lodblf
     * @sff #gftLodblf
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftLodblf(Lodblf l) {
        Lodblf oldVbluf = lodblf;
        lodblf = l;

        // Tiis is b bound propfrty, so rfport tif dibngf to
        // bny rfgistfrfd listfnfrs.  (Cifbp if tifrf brf nonf.)
        firfPropfrtyCibngf("lodblf", oldVbluf, l);

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        invblidbtfIfVblid();
    }

    /**
     * Gfts tif instbndf of <dodf>ColorModfl</dodf> usfd to displby
     * tif domponfnt on tif output dfvidf.
     * @rfturn tif dolor modfl usfd by tiis domponfnt
     * @sff jbvb.bwt.imbgf.ColorModfl
     * @sff jbvb.bwt.pffr.ComponfntPffr#gftColorModfl()
     * @sff Toolkit#gftColorModfl()
     * @sindf 1.0
     */
    publid ColorModfl gftColorModfl() {
        ComponfntPffr pffr = tiis.pffr;
        if ((pffr != null) && ! (pffr instbndfof LigitwfigitPffr)) {
            rfturn pffr.gftColorModfl();
        } flsf if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn ColorModfl.gftRGBdffbult();
        } // flsf
        rfturn gftToolkit().gftColorModfl();
    }

    /**
     * Gfts tif lodbtion of tiis domponfnt in tif form of b
     * point spfdifying tif domponfnt's top-lfft dornfr.
     * Tif lodbtion will bf rflbtivf to tif pbrfnt's doordinbtf spbdf.
     * <p>
     * Duf to tif bsyndironous nbturf of nbtivf fvfnt ibndling, tiis
     * mftiod dbn rfturn outdbtfd vblufs (for instbndf, bftfr sfvfrbl dblls
     * of <dodf>sftLodbtion()</dodf> in rbpid suddfssion).  For tiis
     * rfbson, tif rfdommfndfd mftiod of obtbining b domponfnt's position is
     * witiin <dodf>jbvb.bwt.fvfnt.ComponfntListfnfr.domponfntMovfd()</dodf>,
     * wiidi is dbllfd bftfr tif opfrbting systfm ibs finisifd moving tif
     * domponfnt.
     * </p>
     * @rfturn bn instbndf of <dodf>Point</dodf> rfprfsfnting
     *          tif top-lfft dornfr of tif domponfnt's bounds in
     *          tif doordinbtf spbdf of tif domponfnt's pbrfnt
     * @sff #sftLodbtion
     * @sff #gftLodbtionOnSdrffn
     * @sindf 1.1
     */
    publid Point gftLodbtion() {
        rfturn lodbtion();
    }

    /**
     * Gfts tif lodbtion of tiis domponfnt in tif form of b point
     * spfdifying tif domponfnt's top-lfft dornfr in tif sdrffn's
     * doordinbtf spbdf.
     * @rfturn bn instbndf of <dodf>Point</dodf> rfprfsfnting
     *          tif top-lfft dornfr of tif domponfnt's bounds in tif
     *          doordinbtf spbdf of tif sdrffn
     * @tirows IllfgblComponfntStbtfExdfption if tif
     *          domponfnt is not siowing on tif sdrffn
     * @sff #sftLodbtion
     * @sff #gftLodbtion
     */
    publid Point gftLodbtionOnSdrffn() {
        syndironizfd (gftTrffLodk()) {
            rfturn gftLodbtionOnSdrffn_NoTrffLodk();
        }
    }

    /*
     * b pbdkbgf privbtf vfrsion of gftLodbtionOnSdrffn
     * usfd by GlobblCursormbnbgfr to updbtf dursor
     */
    finbl Point gftLodbtionOnSdrffn_NoTrffLodk() {

        if (pffr != null && isSiowing()) {
            if (pffr instbndfof LigitwfigitPffr) {
                // ligitwfigit domponfnt lodbtion nffds to bf trbnslbtfd
                // rflbtivf to b nbtivf domponfnt.
                Contbinfr iost = gftNbtivfContbinfr();
                Point pt = iost.pffr.gftLodbtionOnSdrffn();
                for(Componfnt d = tiis; d != iost; d = d.gftPbrfnt()) {
                    pt.x += d.x;
                    pt.y += d.y;
                }
                rfturn pt;
            } flsf {
                Point pt = pffr.gftLodbtionOnSdrffn();
                rfturn pt;
            }
        } flsf {
            tirow nfw IllfgblComponfntStbtfExdfption("domponfnt must bf siowing on tif sdrffn to dftfrminf its lodbtion");
        }
    }


    /**
     * Rfturns tif lodbtion of tiis domponfnt's top lfft dornfr.
     *
     * @rfturn tif lodbtion of tiis domponfnt's top lfft dornfr
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftLodbtion()</dodf>.
     */
    @Dfprfdbtfd
    publid Point lodbtion() {
        rfturn lodbtion_NoClifntCodf();
    }

    privbtf Point lodbtion_NoClifntCodf() {
        rfturn nfw Point(x, y);
    }

    /**
     * Movfs tiis domponfnt to b nfw lodbtion. Tif top-lfft dornfr of
     * tif nfw lodbtion is spfdififd by tif <dodf>x</dodf> bnd <dodf>y</dodf>
     * pbrbmftfrs in tif doordinbtf spbdf of tiis domponfnt's pbrfnt.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm x tif <i>x</i>-doordinbtf of tif nfw lodbtion's
     *          top-lfft dornfr in tif pbrfnt's doordinbtf spbdf
     * @pbrbm y tif <i>y</i>-doordinbtf of tif nfw lodbtion's
     *          top-lfft dornfr in tif pbrfnt's doordinbtf spbdf
     * @sff #gftLodbtion
     * @sff #sftBounds
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftLodbtion(int x, int y) {
        movf(x, y);
    }

    /**
     * Movfs tiis domponfnt to b nfw lodbtion.
     *
     * @pbrbm  x tif <i>x</i>-doordinbtf of tif nfw lodbtion's
     *           top-lfft dornfr in tif pbrfnt's doordinbtf spbdf
     * @pbrbm  y tif <i>y</i>-doordinbtf of tif nfw lodbtion's
     *           top-lfft dornfr in tif pbrfnt's doordinbtf spbdf
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftLodbtion(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void movf(int x, int y) {
        syndironizfd(gftTrffLodk()) {
            sftBoundsOp(ComponfntPffr.SET_LOCATION);
            sftBounds(x, y, widti, ifigit);
        }
    }

    /**
     * Movfs tiis domponfnt to b nfw lodbtion. Tif top-lfft dornfr of
     * tif nfw lodbtion is spfdififd by point <dodf>p</dodf>. Point
     * <dodf>p</dodf> is givfn in tif pbrfnt's doordinbtf spbdf.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm p tif point dffining tif top-lfft dornfr
     *          of tif nfw lodbtion, givfn in tif doordinbtf spbdf of tiis
     *          domponfnt's pbrfnt
     * @sff #gftLodbtion
     * @sff #sftBounds
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftLodbtion(Point p) {
        sftLodbtion(p.x, p.y);
    }

    /**
     * Rfturns tif sizf of tiis domponfnt in tif form of b
     * <dodf>Dimfnsion</dodf> objfdt. Tif <dodf>ifigit</dodf>
     * fifld of tif <dodf>Dimfnsion</dodf> objfdt dontbins
     * tiis domponfnt's ifigit, bnd tif <dodf>widti</dodf>
     * fifld of tif <dodf>Dimfnsion</dodf> objfdt dontbins
     * tiis domponfnt's widti.
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt tibt indidbtfs tif
     *          sizf of tiis domponfnt
     * @sff #sftSizf
     * @sindf 1.1
     */
    publid Dimfnsion gftSizf() {
        rfturn sizf();
    }

    /**
     * Rfturns tif sizf of tiis domponfnt in tif form of b
     * {@dodf Dimfnsion} objfdt.
     *
     * @rfturn tif {@dodf Dimfnsion} objfdt tibt indidbtfs tif
     *         sizf of tiis domponfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion sizf() {
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    /**
     * Rfsizfs tiis domponfnt so tibt it ibs widti <dodf>widti</dodf>
     * bnd ifigit <dodf>ifigit</dodf>.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm widti tif nfw widti of tiis domponfnt in pixfls
     * @pbrbm ifigit tif nfw ifigit of tiis domponfnt in pixfls
     * @sff #gftSizf
     * @sff #sftBounds
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftSizf(int widti, int ifigit) {
        rfsizf(widti, ifigit);
    }

    /**
     * Rfsizfs tiis domponfnt.
     *
     * @pbrbm  widti tif nfw widti of tif domponfnt
     * @pbrbm  ifigit tif nfw ifigit of tif domponfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftSizf(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void rfsizf(int widti, int ifigit) {
        syndironizfd(gftTrffLodk()) {
            sftBoundsOp(ComponfntPffr.SET_SIZE);
            sftBounds(x, y, widti, ifigit);
        }
    }

    /**
     * Rfsizfs tiis domponfnt so tibt it ibs widti <dodf>d.widti</dodf>
     * bnd ifigit <dodf>d.ifigit</dodf>.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm d tif dimfnsion spfdifying tif nfw sizf
     *          of tiis domponfnt
     * @tirows NullPointfrExdfption if {@dodf d} is {@dodf null}
     * @sff #sftSizf
     * @sff #sftBounds
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftSizf(Dimfnsion d) {
        rfsizf(d);
    }

    /**
     * Rfsizfs tiis domponfnt so tibt it ibs widti {@dodf d.widti}
     * bnd ifigit {@dodf d.ifigit}.
     *
     * @pbrbm  d tif nfw sizf of tiis domponfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftSizf(Dimfnsion)</dodf>.
     */
    @Dfprfdbtfd
    publid void rfsizf(Dimfnsion d) {
        sftSizf(d.widti, d.ifigit);
    }

    /**
     * Gfts tif bounds of tiis domponfnt in tif form of b
     * <dodf>Rfdtbnglf</dodf> objfdt. Tif bounds spfdify tiis
     * domponfnt's widti, ifigit, bnd lodbtion rflbtivf to
     * its pbrfnt.
     * @rfturn b rfdtbnglf indidbting tiis domponfnt's bounds
     * @sff #sftBounds
     * @sff #gftLodbtion
     * @sff #gftSizf
     */
    publid Rfdtbnglf gftBounds() {
        rfturn bounds();
    }

    /**
     * Rfturns tif bounding rfdtbnglf of tiis domponfnt.
     *
     * @rfturn tif bounding rfdtbnglf for tiis domponfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftBounds()</dodf>.
     */
    @Dfprfdbtfd
    publid Rfdtbnglf bounds() {
        rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
    }

    /**
     * Movfs bnd rfsizfs tiis domponfnt. Tif nfw lodbtion of tif top-lfft
     * dornfr is spfdififd by <dodf>x</dodf> bnd <dodf>y</dodf>, bnd tif
     * nfw sizf is spfdififd by <dodf>widti</dodf> bnd <dodf>ifigit</dodf>.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm x tif nfw <i>x</i>-doordinbtf of tiis domponfnt
     * @pbrbm y tif nfw <i>y</i>-doordinbtf of tiis domponfnt
     * @pbrbm widti tif nfw <dodf>widti</dodf> of tiis domponfnt
     * @pbrbm ifigit tif nfw <dodf>ifigit</dodf> of tiis
     *          domponfnt
     * @sff #gftBounds
     * @sff #sftLodbtion(int, int)
     * @sff #sftLodbtion(Point)
     * @sff #sftSizf(int, int)
     * @sff #sftSizf(Dimfnsion)
     * @sff #invblidbtf
     * @sindf 1.1
     */
    publid void sftBounds(int x, int y, int widti, int ifigit) {
        rfsibpf(x, y, widti, ifigit);
    }

    /**
     * Rfsibpfs tif bounding rfdtbnglf for tiis domponfnt.
     *
     * @pbrbm  x tif <i>x</i> doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * @pbrbm  y tif <i>y</i> doordinbtf of tif uppfr lfft dornfr of tif rfdtbnglf
     * @pbrbm  widti tif widti of tif rfdtbnglf
     * @pbrbm  ifigit tif ifigit of tif rfdtbnglf
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftBounds(int, int, int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        syndironizfd (gftTrffLodk()) {
            try {
                sftBoundsOp(ComponfntPffr.SET_BOUNDS);
                boolfbn rfsizfd = (tiis.widti != widti) || (tiis.ifigit != ifigit);
                boolfbn movfd = (tiis.x != x) || (tiis.y != y);
                if (!rfsizfd && !movfd) {
                    rfturn;
                }
                int oldX = tiis.x;
                int oldY = tiis.y;
                int oldWidti = tiis.widti;
                int oldHfigit = tiis.ifigit;
                tiis.x = x;
                tiis.y = y;
                tiis.widti = widti;
                tiis.ifigit = ifigit;

                if (rfsizfd) {
                    isPbdkfd = fblsf;
                }

                boolfbn nffdNotify = truf;
                mixOnRfsibping();
                if (pffr != null) {
                    // LigitwigitPffr is bn fmpty stub so dbn skip pffr.rfsibpf
                    if (!(pffr instbndfof LigitwfigitPffr)) {
                        rfsibpfNbtivfPffr(x, y, widti, ifigit, gftBoundsOp());
                        // Cifdk pffr bdtubly dibngfd doordinbtfs
                        rfsizfd = (oldWidti != tiis.widti) || (oldHfigit != tiis.ifigit);
                        movfd = (oldX != tiis.x) || (oldY != tiis.y);
                        // fix for 5025858: do not sfnd ComponfntEvfnts for toplfvfl
                        // windows ifrf bs it is donf from pffr or nbtivf dodf wifn
                        // tif window is rfblly rfsizfd or movfd, otifrwisf somf
                        // fvfnts mby bf sfnt twidf
                        if (tiis instbndfof Window) {
                            nffdNotify = fblsf;
                        }
                    }
                    if (rfsizfd) {
                        invblidbtf();
                    }
                    if (pbrfnt != null) {
                        pbrfnt.invblidbtfIfVblid();
                    }
                }
                if (nffdNotify) {
                    notifyNfwBounds(rfsizfd, movfd);
                }
                rfpbintPbrfntIfNffdfd(oldX, oldY, oldWidti, oldHfigit);
            } finblly {
                sftBoundsOp(ComponfntPffr.RESET_OPERATION);
            }
        }
    }

    privbtf void rfpbintPbrfntIfNffdfd(int oldX, int oldY, int oldWidti,
                                       int oldHfigit)
    {
        if (pbrfnt != null && pffr instbndfof LigitwfigitPffr && isSiowing()) {
            // Hbvf tif pbrfnt rfdrbw tif brfb tiis domponfnt oddupifd.
            pbrfnt.rfpbint(oldX, oldY, oldWidti, oldHfigit);
            // Hbvf tif pbrfnt rfdrbw tif brfb tiis domponfnt *now* oddupifs.
            rfpbint();
        }
    }

    privbtf void rfsibpfNbtivfPffr(int x, int y, int widti, int ifigit, int op) {
        // nbtivf pffr migit bf offsft by morf tibn dirfdt
        // pbrfnt sindf pbrfnt migit bf ligitwfigit.
        int nbtivfX = x;
        int nbtivfY = y;
        for (Componfnt d = pbrfnt;
             (d != null) && (d.pffr instbndfof LigitwfigitPffr);
             d = d.pbrfnt)
        {
            nbtivfX += d.x;
            nbtivfY += d.y;
        }
        pffr.sftBounds(nbtivfX, nbtivfY, widti, ifigit, op);
    }

    @SupprfssWbrnings("dfprfdbtion")
    privbtf void notifyNfwBounds(boolfbn rfsizfd, boolfbn movfd) {
        if (domponfntListfnfr != null
            || (fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0
            || Toolkit.fnbblfdOnToolkit(AWTEvfnt.COMPONENT_EVENT_MASK))
            {
                if (rfsizfd) {
                    ComponfntEvfnt f = nfw ComponfntEvfnt(tiis,
                                                          ComponfntEvfnt.COMPONENT_RESIZED);
                    Toolkit.gftEvfntQufuf().postEvfnt(f);
                }
                if (movfd) {
                    ComponfntEvfnt f = nfw ComponfntEvfnt(tiis,
                                                          ComponfntEvfnt.COMPONENT_MOVED);
                    Toolkit.gftEvfntQufuf().postEvfnt(f);
                }
            } flsf {
                if (tiis instbndfof Contbinfr && ((Contbinfr)tiis).dountComponfnts() > 0) {
                    boolfbn fnbblfdOnToolkit =
                        Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK);
                    if (rfsizfd) {

                        ((Contbinfr)tiis).drfbtfCiildHifrbrdiyEvfnts(
                                                                     HifrbrdiyEvfnt.ANCESTOR_RESIZED, 0, fnbblfdOnToolkit);
                    }
                    if (movfd) {
                        ((Contbinfr)tiis).drfbtfCiildHifrbrdiyEvfnts(
                                                                     HifrbrdiyEvfnt.ANCESTOR_MOVED, 0, fnbblfdOnToolkit);
                    }
                }
                }
    }

    /**
     * Movfs bnd rfsizfs tiis domponfnt to donform to tif nfw
     * bounding rfdtbnglf <dodf>r</dodf>. Tiis domponfnt's nfw
     * position is spfdififd by <dodf>r.x</dodf> bnd <dodf>r.y</dodf>,
     * bnd its nfw sizf is spfdififd by <dodf>r.widti</dodf> bnd
     * <dodf>r.ifigit</dodf>
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm r tif nfw bounding rfdtbnglf for tiis domponfnt
     * @tirows NullPointfrExdfption if {@dodf r} is {@dodf null}
     * @sff       #gftBounds
     * @sff       #sftLodbtion(int, int)
     * @sff       #sftLodbtion(Point)
     * @sff       #sftSizf(int, int)
     * @sff       #sftSizf(Dimfnsion)
     * @sff #invblidbtf
     * @sindf     1.1
     */
    publid void sftBounds(Rfdtbnglf r) {
        sftBounds(r.x, r.y, r.widti, r.ifigit);
    }


    /**
     * Rfturns tif durrfnt x doordinbtf of tif domponfnts origin.
     * Tiis mftiod is prfffrbblf to writing
     * <dodf>domponfnt.gftBounds().x</dodf>,
     * or <dodf>domponfnt.gftLodbtion().x</dodf> bfdbusf it dofsn't
     * dbusf bny ifbp bllodbtions.
     *
     * @rfturn tif durrfnt x doordinbtf of tif domponfnts origin
     * @sindf 1.2
     */
    publid int gftX() {
        rfturn x;
    }


    /**
     * Rfturns tif durrfnt y doordinbtf of tif domponfnts origin.
     * Tiis mftiod is prfffrbblf to writing
     * <dodf>domponfnt.gftBounds().y</dodf>,
     * or <dodf>domponfnt.gftLodbtion().y</dodf> bfdbusf it
     * dofsn't dbusf bny ifbp bllodbtions.
     *
     * @rfturn tif durrfnt y doordinbtf of tif domponfnts origin
     * @sindf 1.2
     */
    publid int gftY() {
        rfturn y;
    }


    /**
     * Rfturns tif durrfnt widti of tiis domponfnt.
     * Tiis mftiod is prfffrbblf to writing
     * <dodf>domponfnt.gftBounds().widti</dodf>,
     * or <dodf>domponfnt.gftSizf().widti</dodf> bfdbusf it
     * dofsn't dbusf bny ifbp bllodbtions.
     *
     * @rfturn tif durrfnt widti of tiis domponfnt
     * @sindf 1.2
     */
    publid int gftWidti() {
        rfturn widti;
    }


    /**
     * Rfturns tif durrfnt ifigit of tiis domponfnt.
     * Tiis mftiod is prfffrbblf to writing
     * <dodf>domponfnt.gftBounds().ifigit</dodf>,
     * or <dodf>domponfnt.gftSizf().ifigit</dodf> bfdbusf it
     * dofsn't dbusf bny ifbp bllodbtions.
     *
     * @rfturn tif durrfnt ifigit of tiis domponfnt
     * @sindf 1.2
     */
    publid int gftHfigit() {
        rfturn ifigit;
    }

    /**
     * Storfs tif bounds of tiis domponfnt into "rfturn vbluf" <b>rv</b> bnd
     * rfturn <b>rv</b>.  If rv is <dodf>null</dodf> b nfw
     * <dodf>Rfdtbnglf</dodf> is bllodbtfd.
     * Tiis vfrsion of <dodf>gftBounds</dodf> is usfful if tif dbllfr
     * wbnts to bvoid bllodbting b nfw <dodf>Rfdtbnglf</dodf> objfdt
     * on tif ifbp.
     *
     * @pbrbm rv tif rfturn vbluf, modififd to tif domponfnts bounds
     * @rfturn rv
     */
    publid Rfdtbnglf gftBounds(Rfdtbnglf rv) {
        if (rv == null) {
            rfturn nfw Rfdtbnglf(gftX(), gftY(), gftWidti(), gftHfigit());
        }
        flsf {
            rv.sftBounds(gftX(), gftY(), gftWidti(), gftHfigit());
            rfturn rv;
        }
    }

    /**
     * Storfs tif widti/ifigit of tiis domponfnt into "rfturn vbluf" <b>rv</b>
     * bnd rfturn <b>rv</b>.   If rv is <dodf>null</dodf> b nfw
     * <dodf>Dimfnsion</dodf> objfdt is bllodbtfd.  Tiis vfrsion of
     * <dodf>gftSizf</dodf> is usfful if tif dbllfr wbnts to bvoid
     * bllodbting b nfw <dodf>Dimfnsion</dodf> objfdt on tif ifbp.
     *
     * @pbrbm rv tif rfturn vbluf, modififd to tif domponfnts sizf
     * @rfturn rv
     */
    publid Dimfnsion gftSizf(Dimfnsion rv) {
        if (rv == null) {
            rfturn nfw Dimfnsion(gftWidti(), gftHfigit());
        }
        flsf {
            rv.sftSizf(gftWidti(), gftHfigit());
            rfturn rv;
        }
    }

    /**
     * Storfs tif x,y origin of tiis domponfnt into "rfturn vbluf" <b>rv</b>
     * bnd rfturn <b>rv</b>.   If rv is <dodf>null</dodf> b nfw
     * <dodf>Point</dodf> is bllodbtfd.
     * Tiis vfrsion of <dodf>gftLodbtion</dodf> is usfful if tif
     * dbllfr wbnts to bvoid bllodbting b nfw <dodf>Point</dodf>
     * objfdt on tif ifbp.
     *
     * @pbrbm rv tif rfturn vbluf, modififd to tif domponfnts lodbtion
     * @rfturn rv
     */
    publid Point gftLodbtion(Point rv) {
        if (rv == null) {
            rfturn nfw Point(gftX(), gftY());
        }
        flsf {
            rv.sftLodbtion(gftX(), gftY());
            rfturn rv;
        }
    }

    /**
     * Rfturns truf if tiis domponfnt is domplftfly opbquf, rfturns
     * fblsf by dffbult.
     * <p>
     * An opbquf domponfnt pbints fvfry pixfl witiin its
     * rfdtbngulbr rfgion. A non-opbquf domponfnt pbints only somf of
     * its pixfls, bllowing tif pixfls undfrnfbti it to "siow tirougi".
     * A domponfnt tibt dofs not fully pbint its pixfls tifrfforf
     * providfs b dfgrff of trbnspbrfndy.
     * <p>
     * Subdlbssfs tibt gubrbntff to blwbys domplftfly pbint tifir
     * dontfnts siould ovfrridf tiis mftiod bnd rfturn truf.
     *
     * @rfturn truf if tiis domponfnt is domplftfly opbquf
     * @sff #isLigitwfigit
     * @sindf 1.2
     */
    publid boolfbn isOpbquf() {
        if (gftPffr() == null) {
            rfturn fblsf;
        }
        flsf {
            rfturn !isLigitwfigit();
        }
    }


    /**
     * A ligitwfigit domponfnt dofsn't ibvf b nbtivf toolkit pffr.
     * Subdlbssfs of <dodf>Componfnt</dodf> bnd <dodf>Contbinfr</dodf>,
     * otifr tibn tif onfs dffinfd in tiis pbdkbgf likf <dodf>Button</dodf>
     * or <dodf>Sdrollbbr</dodf>, brf ligitwfigit.
     * All of tif Swing domponfnts brf ligitwfigits.
     * <p>
     * Tiis mftiod will blwbys rfturn <dodf>fblsf</dodf> if tiis domponfnt
     * is not displbybblf bfdbusf it is impossiblf to dftfrminf tif
     * wfigit of bn undisplbybblf domponfnt.
     *
     * @rfturn truf if tiis domponfnt ibs b ligitwfigit pffr; fblsf if
     *         it ibs b nbtivf pffr or no pffr
     * @sff #isDisplbybblf
     * @sindf 1.2
     */
    publid boolfbn isLigitwfigit() {
        rfturn gftPffr() instbndfof LigitwfigitPffr;
    }


    /**
     * Sfts tif prfffrrfd sizf of tiis domponfnt to b donstbnt
     * vbluf.  Subsfqufnt dblls to <dodf>gftPrfffrrfdSizf</dodf> will blwbys
     * rfturn tiis vbluf.  Sftting tif prfffrrfd sizf to <dodf>null</dodf>
     * rfstorfs tif dffbult bfibvior.
     *
     * @pbrbm prfffrrfdSizf Tif nfw prfffrrfd sizf, or null
     * @sff #gftPrfffrrfdSizf
     * @sff #isPrfffrrfdSizfSft
     * @sindf 1.5
     */
    publid void sftPrfffrrfdSizf(Dimfnsion prfffrrfdSizf) {
        Dimfnsion old;
        // If tif prfffrrfd sizf wbs sft, usf it bs tif old vbluf, otifrwisf
        // usf null to indidbtf wf didn't prfviously ibvf b sft prfffrrfd
        // sizf.
        if (prffSizfSft) {
            old = tiis.prffSizf;
        }
        flsf {
            old = null;
        }
        tiis.prffSizf = prfffrrfdSizf;
        prffSizfSft = (prfffrrfdSizf != null);
        firfPropfrtyCibngf("prfffrrfdSizf", old, prfffrrfdSizf);
    }


    /**
     * Rfturns truf if tif prfffrrfd sizf ibs bffn sft to b
     * non-<dodf>null</dodf> vbluf otifrwisf rfturns fblsf.
     *
     * @rfturn truf if <dodf>sftPrfffrrfdSizf</dodf> ibs bffn invokfd
     *         witi b non-null vbluf.
     * @sindf 1.5
     */
    publid boolfbn isPrfffrrfdSizfSft() {
        rfturn prffSizfSft;
    }


    /**
     * Gfts tif prfffrrfd sizf of tiis domponfnt.
     * @rfturn b dimfnsion objfdt indidbting tiis domponfnt's prfffrrfd sizf
     * @sff #gftMinimumSizf
     * @sff LbyoutMbnbgfr
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn prfffrrfdSizf();
    }


    /**
     * Rfturns tif domponfnt's prfffrrfd sizf.
     *
     * @rfturn tif domponfnt's prfffrrfd sizf
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf() {
        /* Avoid grbbbing tif lodk if b rfbsonbblf dbdifd sizf vbluf
         * is bvbilbblf.
         */
        Dimfnsion dim = prffSizf;
        if (dim == null || !(isPrfffrrfdSizfSft() || isVblid())) {
            syndironizfd (gftTrffLodk()) {
                prffSizf = (pffr != null) ?
                    pffr.gftPrfffrrfdSizf() :
                    gftMinimumSizf();
                dim = prffSizf;
            }
        }
        rfturn nfw Dimfnsion(dim);
    }

    /**
     * Sfts tif minimum sizf of tiis domponfnt to b donstbnt
     * vbluf.  Subsfqufnt dblls to <dodf>gftMinimumSizf</dodf> will blwbys
     * rfturn tiis vbluf.  Sftting tif minimum sizf to <dodf>null</dodf>
     * rfstorfs tif dffbult bfibvior.
     *
     * @pbrbm minimumSizf tif nfw minimum sizf of tiis domponfnt
     * @sff #gftMinimumSizf
     * @sff #isMinimumSizfSft
     * @sindf 1.5
     */
    publid void sftMinimumSizf(Dimfnsion minimumSizf) {
        Dimfnsion old;
        // If tif minimum sizf wbs sft, usf it bs tif old vbluf, otifrwisf
        // usf null to indidbtf wf didn't prfviously ibvf b sft minimum
        // sizf.
        if (minSizfSft) {
            old = tiis.minSizf;
        }
        flsf {
            old = null;
        }
        tiis.minSizf = minimumSizf;
        minSizfSft = (minimumSizf != null);
        firfPropfrtyCibngf("minimumSizf", old, minimumSizf);
    }

    /**
     * Rfturns wiftifr or not <dodf>sftMinimumSizf</dodf> ibs bffn
     * invokfd witi b non-null vbluf.
     *
     * @rfturn truf if <dodf>sftMinimumSizf</dodf> ibs bffn invokfd witi b
     *              non-null vbluf.
     * @sindf 1.5
     */
    publid boolfbn isMinimumSizfSft() {
        rfturn minSizfSft;
    }

    /**
     * Gfts tif minimum sizf of tiis domponfnt.
     * @rfturn b dimfnsion objfdt indidbting tiis domponfnt's minimum sizf
     * @sff #gftPrfffrrfdSizf
     * @sff LbyoutMbnbgfr
     */
    publid Dimfnsion gftMinimumSizf() {
        rfturn minimumSizf();
    }

    /**
     * Rfturns tif minimum sizf of tiis domponfnt.
     *
     * @rfturn tif minimum sizf of tiis domponfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf() {
        /* Avoid grbbbing tif lodk if b rfbsonbblf dbdifd sizf vbluf
         * is bvbilbblf.
         */
        Dimfnsion dim = minSizf;
        if (dim == null || !(isMinimumSizfSft() || isVblid())) {
            syndironizfd (gftTrffLodk()) {
                minSizf = (pffr != null) ?
                    pffr.gftMinimumSizf() :
                    sizf();
                dim = minSizf;
            }
        }
        rfturn nfw Dimfnsion(dim);
    }

    /**
     * Sfts tif mbximum sizf of tiis domponfnt to b donstbnt
     * vbluf.  Subsfqufnt dblls to <dodf>gftMbximumSizf</dodf> will blwbys
     * rfturn tiis vbluf.  Sftting tif mbximum sizf to <dodf>null</dodf>
     * rfstorfs tif dffbult bfibvior.
     *
     * @pbrbm mbximumSizf b <dodf>Dimfnsion</dodf> dontbining tif
     *          dfsirfd mbximum bllowbblf sizf
     * @sff #gftMbximumSizf
     * @sff #isMbximumSizfSft
     * @sindf 1.5
     */
    publid void sftMbximumSizf(Dimfnsion mbximumSizf) {
        // If tif mbximum sizf wbs sft, usf it bs tif old vbluf, otifrwisf
        // usf null to indidbtf wf didn't prfviously ibvf b sft mbximum
        // sizf.
        Dimfnsion old;
        if (mbxSizfSft) {
            old = tiis.mbxSizf;
        }
        flsf {
            old = null;
        }
        tiis.mbxSizf = mbximumSizf;
        mbxSizfSft = (mbximumSizf != null);
        firfPropfrtyCibngf("mbximumSizf", old, mbximumSizf);
    }

    /**
     * Rfturns truf if tif mbximum sizf ibs bffn sft to b non-<dodf>null</dodf>
     * vbluf otifrwisf rfturns fblsf.
     *
     * @rfturn truf if <dodf>mbximumSizf</dodf> is non-<dodf>null</dodf>,
     *          fblsf otifrwisf
     * @sindf 1.5
     */
    publid boolfbn isMbximumSizfSft() {
        rfturn mbxSizfSft;
    }

    /**
     * Gfts tif mbximum sizf of tiis domponfnt.
     * @rfturn b dimfnsion objfdt indidbting tiis domponfnt's mbximum sizf
     * @sff #gftMinimumSizf
     * @sff #gftPrfffrrfdSizf
     * @sff LbyoutMbnbgfr
     */
    publid Dimfnsion gftMbximumSizf() {
        if (isMbximumSizfSft()) {
            rfturn nfw Dimfnsion(mbxSizf);
        }
        rfturn nfw Dimfnsion(Siort.MAX_VALUE, Siort.MAX_VALUE);
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @rfturn tif iorizontbl blignmfnt of tiis domponfnt
     */
    publid flobt gftAlignmfntX() {
        rfturn CENTER_ALIGNMENT;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     *
     * @rfturn tif vfrtidbl blignmfnt of tiis domponfnt
     */
    publid flobt gftAlignmfntY() {
        rfturn CENTER_ALIGNMENT;
    }

    /**
     * Rfturns tif bbsflinf.  Tif bbsflinf is mfbsurfd from tif top of
     * tif domponfnt.  Tiis mftiod is primbrily mfbnt for
     * <dodf>LbyoutMbnbgfr</dodf>s to blign domponfnts blong tifir
     * bbsflinf.  A rfturn vbluf lfss tibn 0 indidbtfs tiis domponfnt
     * dofs not ibvf b rfbsonbblf bbsflinf bnd tibt
     * <dodf>LbyoutMbnbgfr</dodf>s siould not blign tiis domponfnt on
     * its bbsflinf.
     * <p>
     * Tif dffbult implfmfntbtion rfturns -1.  Subdlbssfs tibt support
     * bbsflinf siould ovfrridf bppropribtfly.  If b vbluf &gt;= 0 is
     * rfturnfd, tifn tif domponfnt ibs b vblid bbsflinf for bny
     * sizf &gt;= tif minimum sizf bnd <dodf>gftBbsflinfRfsizfBfibvior</dodf>
     * dbn bf usfd to dftfrminf iow tif bbsflinf dibngfs witi sizf.
     *
     * @pbrbm widti tif widti to gft tif bbsflinf for
     * @pbrbm ifigit tif ifigit to gft tif bbsflinf for
     * @rfturn tif bbsflinf or &lt; 0 indidbting tifrf is no rfbsonbblf
     *         bbsflinf
     * @tirows IllfgblArgumfntExdfption if widti or ifigit is &lt; 0
     * @sff #gftBbsflinfRfsizfBfibvior
     * @sff jbvb.bwt.FontMftrids
     * @sindf 1.6
     */
    publid int gftBbsflinf(int widti, int ifigit) {
        if (widti < 0 || ifigit < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Widti bnd ifigit must bf >= 0");
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.  Tiis mftiod is primbrily mfbnt for
     * lbyout mbnbgfrs bnd GUI buildfrs.
     * <p>
     * Tif dffbult implfmfntbtion rfturns
     * <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf>.  Subdlbssfs tibt ibvf b
     * bbsflinf siould ovfrridf bppropribtfly.  Subdlbssfs siould
     * nfvfr rfturn <dodf>null</dodf>; if tif bbsflinf dbn not bf
     * dbldulbtfd rfturn <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf>.  Cbllfrs
     * siould first bsk for tif bbsflinf using
     * <dodf>gftBbsflinf</dodf> bnd if b vbluf &gt;= 0 is rfturnfd usf
     * tiis mftiod.  It is bddfptbblf for tiis mftiod to rfturn b
     * vbluf otifr tibn <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf> fvfn if
     * <dodf>gftBbsflinf</dodf> rfturns b vbluf lfss tibn 0.
     *
     * @rfturn bn fnum indidbting iow tif bbsflinf dibngfs bs tif domponfnt
     *         sizf dibngfs
     * @sff #gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior() {
        rfturn BbsflinfRfsizfBfibvior.OTHER;
    }

    /**
     * Prompts tif lbyout mbnbgfr to lby out tiis domponfnt. Tiis is
     * usublly dbllfd wifn tif domponfnt (morf spfdifidblly, dontbinfr)
     * is vblidbtfd.
     * @sff #vblidbtf
     * @sff LbyoutMbnbgfr
     */
    publid void doLbyout() {
        lbyout();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>doLbyout()</dodf>.
     */
    @Dfprfdbtfd
    publid void lbyout() {
    }

    /**
     * Vblidbtfs tiis domponfnt.
     * <p>
     * Tif mfbning of tif tfrm <i>vblidbting</i> is dffinfd by tif bndfstors of
     * tiis dlbss. Sff {@link Contbinfr#vblidbtf} for morf dftbils.
     *
     * @sff       #invblidbtf
     * @sff       #doLbyout()
     * @sff       LbyoutMbnbgfr
     * @sff       Contbinfr#vblidbtf
     * @sindf     1.0
     */
    publid void vblidbtf() {
        syndironizfd (gftTrffLodk()) {
            ComponfntPffr pffr = tiis.pffr;
            boolfbn wbsVblid = isVblid();
            if (!wbsVblid && pffr != null) {
                Font nfwfont = gftFont();
                Font oldfont = pffrFont;
                if (nfwfont != oldfont && (oldfont == null
                                           || !oldfont.fqubls(nfwfont))) {
                    pffr.sftFont(nfwfont);
                    pffrFont = nfwfont;
                }
                pffr.lbyout();
            }
            vblid = truf;
            if (!wbsVblid) {
                mixOnVblidbting();
            }
        }
    }

    /**
     * Invblidbtfs tiis domponfnt bnd its bndfstors.
     * <p>
     * By dffbult, bll tif bndfstors of tif domponfnt up to tif top-most
     * dontbinfr of tif iifrbrdiy brf mbrkfd invblid. If tif {@dodf
     * jbvb.bwt.smbrtInvblidbtf} systfm propfrty is sft to {@dodf truf},
     * invblidbtion stops on tif nfbrfst vblidbtf root of tiis domponfnt.
     * Mbrking b dontbinfr <i>invblid</i> indidbtfs tibt tif dontbinfr nffds to
     * bf lbid out.
     * <p>
     * Tiis mftiod is dbllfd butombtidblly wifn bny lbyout-rflbtfd informbtion
     * dibngfs (f.g. sftting tif bounds of tif domponfnt, or bdding tif
     * domponfnt to b dontbinfr).
     * <p>
     * Tiis mftiod migit bf dbllfd oftfn, so it siould work fbst.
     *
     * @sff       #vblidbtf
     * @sff       #doLbyout
     * @sff       LbyoutMbnbgfr
     * @sff       jbvb.bwt.Contbinfr#isVblidbtfRoot
     * @sindf     1.0
     */
    publid void invblidbtf() {
        syndironizfd (gftTrffLodk()) {
            /* Nullify dbdifd lbyout bnd sizf informbtion.
             * For fffidifndy, propbgbtf invblidbtf() upwbrds only if
             * somf otifr domponfnt ibsn't blrfbdy donf so first.
             */
            vblid = fblsf;
            if (!isPrfffrrfdSizfSft()) {
                prffSizf = null;
            }
            if (!isMinimumSizfSft()) {
                minSizf = null;
            }
            if (!isMbximumSizfSft()) {
                mbxSizf = null;
            }
            invblidbtfPbrfnt();
        }
    }

    /**
     * Invblidbtfs tif pbrfnt of tiis domponfnt if bny.
     *
     * Tiis mftiod MUST BE invokfd undfr tif TrffLodk.
     */
    void invblidbtfPbrfnt() {
        if (pbrfnt != null) {
            pbrfnt.invblidbtfIfVblid();
        }
    }

    /** Invblidbtfs tif domponfnt unlfss it is blrfbdy invblid.
     */
    finbl void invblidbtfIfVblid() {
        if (isVblid()) {
            invblidbtf();
        }
    }

    /**
     * Rfvblidbtfs tif domponfnt iifrbrdiy up to tif nfbrfst vblidbtf root.
     * <p>
     * Tiis mftiod first invblidbtfs tif domponfnt iifrbrdiy stbrting from tiis
     * domponfnt up to tif nfbrfst vblidbtf root. Aftfrwbrds, tif domponfnt
     * iifrbrdiy is vblidbtfd stbrting from tif nfbrfst vblidbtf root.
     * <p>
     * Tiis is b donvfnifndf mftiod supposfd to iflp bpplidbtion dfvflopfrs
     * bvoid looking for vblidbtf roots mbnublly. Bbsidblly, it's fquivblfnt to
     * first dblling tif {@link #invblidbtf()} mftiod on tiis domponfnt, bnd
     * tifn dblling tif {@link #vblidbtf()} mftiod on tif nfbrfst vblidbtf
     * root.
     *
     * @sff Contbinfr#isVblidbtfRoot
     * @sindf 1.7
     */
    publid void rfvblidbtf() {
        rfvblidbtfSyndironously();
    }

    /**
     * Rfvblidbtfs tif domponfnt syndironously.
     */
    finbl void rfvblidbtfSyndironously() {
        syndironizfd (gftTrffLodk()) {
            invblidbtf();

            Contbinfr root = gftContbinfr();
            if (root == null) {
                // Tifrf's no pbrfnts. Just vblidbtf itsflf.
                vblidbtf();
            } flsf {
                wiilf (!root.isVblidbtfRoot()) {
                    if (root.gftContbinfr() == null) {
                        // If tifrf's no vblidbtf roots, wf'll vblidbtf tif
                        // topmost dontbinfr
                        brfbk;
                    }

                    root = root.gftContbinfr();
                }

                root.vblidbtf();
            }
        }
    }

    /**
     * Crfbtfs b grbpiids dontfxt for tiis domponfnt. Tiis mftiod will
     * rfturn <dodf>null</dodf> if tiis domponfnt is durrfntly not
     * displbybblf.
     * @rfturn b grbpiids dontfxt for tiis domponfnt, or <dodf>null</dodf>
     *             if it ibs nonf
     * @sff       #pbint
     * @sindf     1.0
     */
    publid Grbpiids gftGrbpiids() {
        if (pffr instbndfof LigitwfigitPffr) {
            // Tiis is for b ligitwfigit domponfnt, nffd to
            // trbnslbtf doordinbtf spbdfs bnd dlip rflbtivf
            // to tif pbrfnt.
            if (pbrfnt == null) rfturn null;
            Grbpiids g = pbrfnt.gftGrbpiids();
            if (g == null) rfturn null;
            if (g instbndfof ConstrbinbblfGrbpiids) {
                ((ConstrbinbblfGrbpiids) g).donstrbin(x, y, widti, ifigit);
            } flsf {
                g.trbnslbtf(x,y);
                g.sftClip(0, 0, widti, ifigit);
            }
            g.sftFont(gftFont());
            rfturn g;
        } flsf {
            ComponfntPffr pffr = tiis.pffr;
            rfturn (pffr != null) ? pffr.gftGrbpiids() : null;
        }
    }

    finbl Grbpiids gftGrbpiids_NoClifntCodf() {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof LigitwfigitPffr) {
            // Tiis is for b ligitwfigit domponfnt, nffd to
            // trbnslbtf doordinbtf spbdfs bnd dlip rflbtivf
            // to tif pbrfnt.
            Contbinfr pbrfnt = tiis.pbrfnt;
            if (pbrfnt == null) rfturn null;
            Grbpiids g = pbrfnt.gftGrbpiids_NoClifntCodf();
            if (g == null) rfturn null;
            if (g instbndfof ConstrbinbblfGrbpiids) {
                ((ConstrbinbblfGrbpiids) g).donstrbin(x, y, widti, ifigit);
            } flsf {
                g.trbnslbtf(x,y);
                g.sftClip(0, 0, widti, ifigit);
            }
            g.sftFont(gftFont_NoClifntCodf());
            rfturn g;
        } flsf {
            rfturn (pffr != null) ? pffr.gftGrbpiids() : null;
        }
    }

    /**
     * Gfts tif font mftrids for tif spfdififd font.
     * Wbrning: Sindf Font mftrids brf bfffdtfd by tif
     * {@link jbvb.bwt.font.FontRfndfrContfxt FontRfndfrContfxt} bnd
     * tiis mftiod dofs not providf onf, it dbn rfturn only mftrids for
     * tif dffbult rfndfr dontfxt wiidi mby not mbtdi tibt usfd wifn
     * rfndfring on tif Componfnt if {@link Grbpiids2D} fundtionblity is bfing
     * usfd. Instfbd mftrids dbn bf obtbinfd bt rfndfring timf by dblling
     * {@link Grbpiids#gftFontMftrids()} or tfxt mfbsurfmfnt APIs on tif
     * {@link Font Font} dlbss.
     * @pbrbm font tif font for wiidi font mftrids is to bf
     *          obtbinfd
     * @rfturn tif font mftrids for <dodf>font</dodf>
     * @sff       #gftFont
     * @sff       #gftPffr
     * @sff       jbvb.bwt.pffr.ComponfntPffr#gftFontMftrids(Font)
     * @sff       Toolkit#gftFontMftrids(Font)
     * @sindf     1.0
     */
    publid FontMftrids gftFontMftrids(Font font) {
        // Tiis is bn unsupportfd ibdk, but lfft in for b dustomfr.
        // Do not rfmovf.
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        if (fm instbndfof SunFontMbnbgfr
            && ((SunFontMbnbgfr) fm).usfPlbtformFontMftrids()) {

            if (pffr != null &&
                !(pffr instbndfof LigitwfigitPffr)) {
                rfturn pffr.gftFontMftrids(font);
            }
        }
        rfturn sun.font.FontDfsignMftrids.gftMftrids(font);
    }

    /**
     * Sfts tif dursor imbgf to tif spfdififd dursor.  Tiis dursor
     * imbgf is displbyfd wifn tif <dodf>dontbins</dodf> mftiod for
     * tiis domponfnt rfturns truf for tif durrfnt dursor lodbtion, bnd
     * tiis Componfnt is visiblf, displbybblf, bnd fnbblfd. Sftting tif
     * dursor of b <dodf>Contbinfr</dodf> dbusfs tibt dursor to bf displbyfd
     * witiin bll of tif dontbinfr's subdomponfnts, fxdfpt for tiosf
     * tibt ibvf b non-<dodf>null</dodf> dursor.
     * <p>
     * Tif mftiod mby ibvf no visubl ffffdt if tif Jbvb plbtform
     * implfmfntbtion bnd/or tif nbtivf systfm do not support
     * dibnging tif mousf dursor sibpf.
     * @pbrbm dursor Onf of tif donstbnts dffinfd
     *          by tif <dodf>Cursor</dodf> dlbss;
     *          if tiis pbrbmftfr is <dodf>null</dodf>
     *          tifn tiis domponfnt will inifrit
     *          tif dursor of its pbrfnt
     * @sff       #isEnbblfd
     * @sff       #isSiowing
     * @sff       #gftCursor
     * @sff       #dontbins
     * @sff       Toolkit#drfbtfCustomCursor
     * @sff       Cursor
     * @sindf     1.1
     */
    publid void sftCursor(Cursor dursor) {
        tiis.dursor = dursor;
        updbtfCursorImmfdibtfly();
    }

    /**
     * Updbtfs tif dursor.  Mby not bf invokfd from tif nbtivf
     * mfssbgf pump.
     */
    finbl void updbtfCursorImmfdibtfly() {
        if (pffr instbndfof LigitwfigitPffr) {
            Contbinfr nbtivfContbinfr = gftNbtivfContbinfr();

            if (nbtivfContbinfr == null) rfturn;

            ComponfntPffr dPffr = nbtivfContbinfr.gftPffr();

            if (dPffr != null) {
                dPffr.updbtfCursorImmfdibtfly();
            }
        } flsf if (pffr != null) {
            pffr.updbtfCursorImmfdibtfly();
        }
    }

    /**
     * Gfts tif dursor sft in tif domponfnt. If tif domponfnt dofs
     * not ibvf b dursor sft, tif dursor of its pbrfnt is rfturnfd.
     * If no dursor is sft in tif fntirf iifrbrdiy,
     * <dodf>Cursor.DEFAULT_CURSOR</dodf> is rfturnfd.
     *
     * @rfturn tif dursor for tiis domponfnt
     * @sff #sftCursor
     * @sindf 1.1
     */
    publid Cursor gftCursor() {
        rfturn gftCursor_NoClifntCodf();
    }

    finbl Cursor gftCursor_NoClifntCodf() {
        Cursor dursor = tiis.dursor;
        if (dursor != null) {
            rfturn dursor;
        }
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            rfturn pbrfnt.gftCursor_NoClifntCodf();
        } flsf {
            rfturn Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    /**
     * Rfturns wiftifr tif dursor ibs bffn fxpliditly sft for tiis Componfnt.
     * If tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Componfnt is inifriting
     * its dursor from bn bndfstor.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor ibs bffn fxpliditly sft for tiis
     *         Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isCursorSft() {
        rfturn (dursor != null);
    }

    /**
     * Pbints tiis domponfnt.
     * <p>
     * Tiis mftiod is dbllfd wifn tif dontfnts of tif domponfnt siould
     * bf pbintfd; sudi bs wifn tif domponfnt is first bfing siown or
     * is dbmbgfd bnd in nffd of rfpbir.  Tif dlip rfdtbnglf in tif
     * <dodf>Grbpiids</dodf> pbrbmftfr is sft to tif brfb
     * wiidi nffds to bf pbintfd.
     * Subdlbssfs of <dodf>Componfnt</dodf> tibt ovfrridf tiis
     * mftiod nffd not dbll <dodf>supfr.pbint(g)</dodf>.
     * <p>
     * For pfrformbndf rfbsons, <dodf>Componfnt</dodf>s witi zfro widti
     * or ifigit brfn't donsidfrfd to nffd pbinting wifn tify brf first siown,
     * bnd blso brfn't donsidfrfd to nffd rfpbir.
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm g tif grbpiids dontfxt to usf for pbinting
     * @sff       #updbtf
     * @sindf     1.0
     */
    publid void pbint(Grbpiids g) {
    }

    /**
     * Updbtfs tiis domponfnt.
     * <p>
     * If tiis domponfnt is not b ligitwfigit domponfnt, tif
     * AWT dblls tif <dodf>updbtf</dodf> mftiod in rfsponsf to
     * b dbll to <dodf>rfpbint</dodf>.  You dbn bssumf tibt
     * tif bbdkground is not dlfbrfd.
     * <p>
     * Tif <dodf>updbtf</dodf> mftiod of <dodf>Componfnt</dodf>
     * dblls tiis domponfnt's <dodf>pbint</dodf> mftiod to rfdrbw
     * tiis domponfnt.  Tiis mftiod is dommonly ovfrriddfn by subdlbssfs
     * wiidi nffd to do bdditionbl work in rfsponsf to b dbll to
     * <dodf>rfpbint</dodf>.
     * Subdlbssfs of Componfnt tibt ovfrridf tiis mftiod siould fitifr
     * dbll <dodf>supfr.updbtf(g)</dodf>, or dbll <dodf>pbint(g)</dodf>
     * dirfdtly from tifir <dodf>updbtf</dodf> mftiod.
     * <p>
     * Tif origin of tif grbpiids dontfxt, its
     * (<dodf>0</dodf>,&nbsp;<dodf>0</dodf>) doordinbtf point, is tif
     * top-lfft dornfr of tiis domponfnt. Tif dlipping rfgion of tif
     * grbpiids dontfxt is tif bounding rfdtbnglf of tiis domponfnt.
     *
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm g tif spfdififd dontfxt to usf for updbting
     * @sff       #pbint
     * @sff       #rfpbint()
     * @sindf     1.0
     */
    publid void updbtf(Grbpiids g) {
        pbint(g);
    }

    /**
     * Pbints tiis domponfnt bnd bll of its subdomponfnts.
     * <p>
     * Tif origin of tif grbpiids dontfxt, its
     * (<dodf>0</dodf>,&nbsp;<dodf>0</dodf>) doordinbtf point, is tif
     * top-lfft dornfr of tiis domponfnt. Tif dlipping rfgion of tif
     * grbpiids dontfxt is tif bounding rfdtbnglf of tiis domponfnt.
     *
     * @pbrbm     g   tif grbpiids dontfxt to usf for pbinting
     * @sff       #pbint
     * @sindf     1.0
     */
    publid void pbintAll(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PffrPbintCbllbbdk.gftInstbndf().
                runOnfComponfnt(tiis, nfw Rfdtbnglf(0, 0, widti, ifigit),
                                g, g.gftClip(),
                                GrbpiidsCbllbbdk.LIGHTWEIGHTS |
                                GrbpiidsCbllbbdk.HEAVYWEIGHTS);
        }
    }

    /**
     * Simulbtfs tif pffr dbllbbdks into jbvb.bwt for pbinting of
     * ligitwfigit Componfnts.
     * @pbrbm     g   tif grbpiids dontfxt to usf for pbinting
     * @sff       #pbintAll
     */
    void ligitwfigitPbint(Grbpiids g) {
        pbint(g);
    }

    /**
     * Pbints bll tif ifbvywfigit subdomponfnts.
     */
    void pbintHfbvywfigitComponfnts(Grbpiids g) {
    }

    /**
     * Rfpbints tiis domponfnt.
     * <p>
     * If tiis domponfnt is b ligitwfigit domponfnt, tiis mftiod
     * dbusfs b dbll to tiis domponfnt's <dodf>pbint</dodf>
     * mftiod bs soon bs possiblf.  Otifrwisf, tiis mftiod dbusfs
     * b dbll to tiis domponfnt's <dodf>updbtf</dodf> mftiod bs soon
     * bs possiblf.
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.

     *
     * @sff       #updbtf(Grbpiids)
     * @sindf     1.0
     */
    publid void rfpbint() {
        rfpbint(0, 0, 0, widti, ifigit);
    }

    /**
     * Rfpbints tif domponfnt.  If tiis domponfnt is b ligitwfigit
     * domponfnt, tiis rfsults in b dbll to <dodf>pbint</dodf>
     * witiin <dodf>tm</dodf> millisfdonds.
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm tm mbximum timf in millisfdonds bfforf updbtf
     * @sff #pbint
     * @sff #updbtf(Grbpiids)
     * @sindf 1.0
     */
    publid void rfpbint(long tm) {
        rfpbint(tm, 0, 0, widti, ifigit);
    }

    /**
     * Rfpbints tif spfdififd rfdtbnglf of tiis domponfnt.
     * <p>
     * If tiis domponfnt is b ligitwfigit domponfnt, tiis mftiod
     * dbusfs b dbll to tiis domponfnt's <dodf>pbint</dodf> mftiod
     * bs soon bs possiblf.  Otifrwisf, tiis mftiod dbusfs b dbll to
     * tiis domponfnt's <dodf>updbtf</dodf> mftiod bs soon bs possiblf.
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm     x   tif <i>x</i> doordinbtf
     * @pbrbm     y   tif <i>y</i> doordinbtf
     * @pbrbm     widti   tif widti
     * @pbrbm     ifigit  tif ifigit
     * @sff       #updbtf(Grbpiids)
     * @sindf     1.0
     */
    publid void rfpbint(int x, int y, int widti, int ifigit) {
        rfpbint(0, x, y, widti, ifigit);
    }

    /**
     * Rfpbints tif spfdififd rfdtbnglf of tiis domponfnt witiin
     * <dodf>tm</dodf> millisfdonds.
     * <p>
     * If tiis domponfnt is b ligitwfigit domponfnt, tiis mftiod dbusfs
     * b dbll to tiis domponfnt's <dodf>pbint</dodf> mftiod.
     * Otifrwisf, tiis mftiod dbusfs b dbll to tiis domponfnt's
     * <dodf>updbtf</dodf> mftiod.
     * <p>
     * <b>Notf</b>: For morf informbtion on tif pbint mfdibnisms utilitizfd
     * by AWT bnd Swing, indluding informbtion on iow to writf tif most
     * fffidifnt pbinting dodf, sff
     * <b irff="ittp://www.orbdlf.dom/tfdinftwork/jbvb/pbinting-140037.itml">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm     tm   mbximum timf in millisfdonds bfforf updbtf
     * @pbrbm     x    tif <i>x</i> doordinbtf
     * @pbrbm     y    tif <i>y</i> doordinbtf
     * @pbrbm     widti    tif widti
     * @pbrbm     ifigit   tif ifigit
     * @sff       #updbtf(Grbpiids)
     * @sindf     1.0
     */
    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {
        if (tiis.pffr instbndfof LigitwfigitPffr) {
            // Nffds to bf trbnslbtfd to pbrfnt doordinbtfs sindf
            // b pbrfnt nbtivf dontbinfr providfs tif bdtubl rfpbint
            // sfrvidfs.  Additionblly, tif rfqufst is rfstridtfd to
            // tif bounds of tif domponfnt.
            if (pbrfnt != null) {
                if (x < 0) {
                    widti += x;
                    x = 0;
                }
                if (y < 0) {
                    ifigit += y;
                    y = 0;
                }

                int pwidti = (widti > tiis.widti) ? tiis.widti : widti;
                int pifigit = (ifigit > tiis.ifigit) ? tiis.ifigit : ifigit;

                if (pwidti <= 0 || pifigit <= 0) {
                    rfturn;
                }

                int px = tiis.x + x;
                int py = tiis.y + y;
                pbrfnt.rfpbint(tm, px, py, pwidti, pifigit);
            }
        } flsf {
            if (isVisiblf() && (tiis.pffr != null) &&
                (widti > 0) && (ifigit > 0)) {
                PbintEvfnt f = nfw PbintEvfnt(tiis, PbintEvfnt.UPDATE,
                                              nfw Rfdtbnglf(x, y, widti, ifigit));
                SunToolkit.postEvfnt(SunToolkit.tbrgftToAppContfxt(tiis), f);
            }
        }
    }

    /**
     * Prints tiis domponfnt. Applidbtions siould ovfrridf tiis mftiod
     * for domponfnts tibt must do spfdibl prodfssing bfforf bfing
     * printfd or siould bf printfd difffrfntly tibn tify brf pbintfd.
     * <p>
     * Tif dffbult implfmfntbtion of tiis mftiod dblls tif
     * <dodf>pbint</dodf> mftiod.
     * <p>
     * Tif origin of tif grbpiids dontfxt, its
     * (<dodf>0</dodf>,&nbsp;<dodf>0</dodf>) doordinbtf point, is tif
     * top-lfft dornfr of tiis domponfnt. Tif dlipping rfgion of tif
     * grbpiids dontfxt is tif bounding rfdtbnglf of tiis domponfnt.
     * @pbrbm     g   tif grbpiids dontfxt to usf for printing
     * @sff       #pbint(Grbpiids)
     * @sindf     1.0
     */
    publid void print(Grbpiids g) {
        pbint(g);
    }

    /**
     * Prints tiis domponfnt bnd bll of its subdomponfnts.
     * <p>
     * Tif origin of tif grbpiids dontfxt, its
     * (<dodf>0</dodf>,&nbsp;<dodf>0</dodf>) doordinbtf point, is tif
     * top-lfft dornfr of tiis domponfnt. Tif dlipping rfgion of tif
     * grbpiids dontfxt is tif bounding rfdtbnglf of tiis domponfnt.
     * @pbrbm     g   tif grbpiids dontfxt to usf for printing
     * @sff       #print(Grbpiids)
     * @sindf     1.0
     */
    publid void printAll(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PffrPrintCbllbbdk.gftInstbndf().
                runOnfComponfnt(tiis, nfw Rfdtbnglf(0, 0, widti, ifigit),
                                g, g.gftClip(),
                                GrbpiidsCbllbbdk.LIGHTWEIGHTS |
                                GrbpiidsCbllbbdk.HEAVYWEIGHTS);
        }
    }

    /**
     * Simulbtfs tif pffr dbllbbdks into jbvb.bwt for printing of
     * ligitwfigit Componfnts.
     * @pbrbm     g   tif grbpiids dontfxt to usf for printing
     * @sff       #printAll
     */
    void ligitwfigitPrint(Grbpiids g) {
        print(g);
    }

    /**
     * Prints bll tif ifbvywfigit subdomponfnts.
     */
    void printHfbvywfigitComponfnts(Grbpiids g) {
    }

    privbtf Insfts gftInsfts_NoClifntCodf() {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof ContbinfrPffr) {
            rfturn (Insfts)((ContbinfrPffr)pffr).gftInsfts().dlonf();
        }
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    /**
     * Rfpbints tif domponfnt wifn tif imbgf ibs dibngfd.
     * Tiis <dodf>imbgfUpdbtf</dodf> mftiod of bn <dodf>ImbgfObsfrvfr</dodf>
     * is dbllfd wifn morf informbtion bbout bn
     * imbgf wiidi ibd bffn prfviously rfqufstfd using bn bsyndironous
     * routinf sudi bs tif <dodf>drbwImbgf</dodf> mftiod of
     * <dodf>Grbpiids</dodf> bfdomfs bvbilbblf.
     * Sff tif dffinition of <dodf>imbgfUpdbtf</dodf> for
     * morf informbtion on tiis mftiod bnd its brgumfnts.
     * <p>
     * Tif <dodf>imbgfUpdbtf</dodf> mftiod of <dodf>Componfnt</dodf>
     * indrfmfntblly drbws bn imbgf on tif domponfnt bs morf of tif bits
     * of tif imbgf brf bvbilbblf.
     * <p>
     * If tif systfm propfrty <dodf>bwt.imbgf.indrfmfntbldrbw</dodf>
     * is missing or ibs tif vbluf <dodf>truf</dodf>, tif imbgf is
     * indrfmfntblly drbwn. If tif systfm propfrty ibs bny otifr vbluf,
     * tifn tif imbgf is not drbwn until it ibs bffn domplftfly lobdfd.
     * <p>
     * Also, if indrfmfntbl drbwing is in ffffdt, tif vbluf of tif
     * systfm propfrty <dodf>bwt.imbgf.rfdrbwrbtf</dodf> is intfrprftfd
     * bs bn intfgfr to givf tif mbximum rfdrbw rbtf, in millisfdonds. If
     * tif systfm propfrty is missing or dbnnot bf intfrprftfd bs bn
     * intfgfr, tif rfdrbw rbtf is ondf fvfry 100ms.
     * <p>
     * Tif intfrprftbtion of tif <dodf>x</dodf>, <dodf>y</dodf>,
     * <dodf>widti</dodf>, bnd <dodf>ifigit</dodf> brgumfnts dfpfnds on
     * tif vbluf of tif <dodf>infoflbgs</dodf> brgumfnt.
     *
     * @pbrbm     img   tif imbgf bfing obsfrvfd
     * @pbrbm     infoflbgs   sff <dodf>imbgfUpdbtf</dodf> for morf informbtion
     * @pbrbm     x   tif <i>x</i> doordinbtf
     * @pbrbm     y   tif <i>y</i> doordinbtf
     * @pbrbm     w   tif widti
     * @pbrbm     i   tif ifigit
     * @rfturn    <dodf>fblsf</dodf> if tif infoflbgs indidbtf tibt tif
     *            imbgf is domplftfly lobdfd; <dodf>truf</dodf> otifrwisf.
     *
     * @sff     jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sff     Grbpiids#drbwImbgf(Imbgf, int, int, Color, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff     Grbpiids#drbwImbgf(Imbgf, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff     Grbpiids#drbwImbgf(Imbgf, int, int, int, int, Color, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff     Grbpiids#drbwImbgf(Imbgf, int, int, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff     jbvb.bwt.imbgf.ImbgfObsfrvfr#imbgfUpdbtf(jbvb.bwt.Imbgf, int, int, int, int, int)
     * @sindf   1.0
     */
    publid boolfbn imbgfUpdbtf(Imbgf img, int infoflbgs,
                               int x, int y, int w, int i) {
        int rbtf = -1;
        if ((infoflbgs & (FRAMEBITS|ALLBITS)) != 0) {
            rbtf = 0;
        } flsf if ((infoflbgs & SOMEBITS) != 0) {
            if (isInd) {
                rbtf = indRbtf;
                if (rbtf < 0) {
                    rbtf = 0;
                }
            }
        }
        if (rbtf >= 0) {
            rfpbint(rbtf, 0, 0, widti, ifigit);
        }
        rfturn (infoflbgs & (ALLBITS|ABORT)) == 0;
    }

    /**
     * Crfbtfs bn imbgf from tif spfdififd imbgf produdfr.
     * @pbrbm     produdfr  tif imbgf produdfr
     * @rfturn    tif imbgf produdfd
     * @sindf     1.0
     */
    publid Imbgf drfbtfImbgf(ImbgfProdudfr produdfr) {
        ComponfntPffr pffr = tiis.pffr;
        if ((pffr != null) && ! (pffr instbndfof LigitwfigitPffr)) {
            rfturn pffr.drfbtfImbgf(produdfr);
        }
        rfturn gftToolkit().drfbtfImbgf(produdfr);
    }

    /**
     * Crfbtfs bn off-sdrffn drbwbblf imbgf
     *     to bf usfd for doublf bufffring.
     * @pbrbm     widti tif spfdififd widti
     * @pbrbm     ifigit tif spfdififd ifigit
     * @rfturn    bn off-sdrffn drbwbblf imbgf, wiidi dbn bf usfd for doublf
     *    bufffring.  Tif rfturn vbluf mby bf <dodf>null</dodf> if tif
     *    domponfnt is not displbybblf.  Tiis will blwbys ibppfn if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     *    <dodf>truf</dodf>.
     * @sff #isDisplbybblf
     * @sff GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.0
     */
    publid Imbgf drfbtfImbgf(int widti, int ifigit) {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof LigitwfigitPffr) {
            if (pbrfnt != null) { rfturn pbrfnt.drfbtfImbgf(widti, ifigit); }
            flsf { rfturn null;}
        } flsf {
            rfturn (pffr != null) ? pffr.drfbtfImbgf(widti, ifigit) : null;
        }
    }

    /**
     * Crfbtfs b volbtilf off-sdrffn drbwbblf imbgf
     *     to bf usfd for doublf bufffring.
     * @pbrbm     widti tif spfdififd widti.
     * @pbrbm     ifigit tif spfdififd ifigit.
     * @rfturn    bn off-sdrffn drbwbblf imbgf, wiidi dbn bf usfd for doublf
     *    bufffring.  Tif rfturn vbluf mby bf <dodf>null</dodf> if tif
     *    domponfnt is not displbybblf.  Tiis will blwbys ibppfn if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     *    <dodf>truf</dodf>.
     * @sff jbvb.bwt.imbgf.VolbtilfImbgf
     * @sff #isDisplbybblf
     * @sff GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     */
    publid VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit) {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof LigitwfigitPffr) {
            if (pbrfnt != null) {
                rfturn pbrfnt.drfbtfVolbtilfImbgf(widti, ifigit);
            }
            flsf { rfturn null;}
        } flsf {
            rfturn (pffr != null) ?
                pffr.drfbtfVolbtilfImbgf(widti, ifigit) : null;
        }
    }

    /**
     * Crfbtfs b volbtilf off-sdrffn drbwbblf imbgf, witi tif givfn dbpbbilitifs.
     * Tif dontfnts of tiis imbgf mby bf lost bt bny timf duf
     * to opfrbting systfm issufs, so tif imbgf must bf mbnbgfd
     * vib tif <dodf>VolbtilfImbgf</dodf> intfrfbdf.
     * @pbrbm widti tif spfdififd widti.
     * @pbrbm ifigit tif spfdififd ifigit.
     * @pbrbm dbps tif imbgf dbpbbilitifs
     * @fxdfption AWTExdfption if bn imbgf witi tif spfdififd dbpbbilitifs dbnnot
     * bf drfbtfd
     * @rfturn b VolbtilfImbgf objfdt, wiidi dbn bf usfd
     * to mbnbgf surfbdf dontfnts loss bnd dbpbbilitifs.
     * @sff jbvb.bwt.imbgf.VolbtilfImbgf
     * @sindf 1.4
     */
    publid VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit,
                                             ImbgfCbpbbilitifs dbps) tirows AWTExdfption {
        // REMIND : difdk dbps
        rfturn drfbtfVolbtilfImbgf(widti, ifigit);
    }

    /**
     * Prfpbrfs bn imbgf for rfndfring on tiis domponfnt.  Tif imbgf
     * dbtb is downlobdfd bsyndironously in bnotifr tirfbd bnd tif
     * bppropribtf sdrffn rfprfsfntbtion of tif imbgf is gfnfrbtfd.
     * @pbrbm     imbgf   tif <dodf>Imbgf</dodf> for wiidi to
     *                    prfpbrf b sdrffn rfprfsfntbtion
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf> objfdt
     *                       to bf notififd bs tif imbgf is bfing prfpbrfd
     * @rfturn    <dodf>truf</dodf> if tif imbgf ibs blrfbdy bffn fully
     *           prfpbrfd; <dodf>fblsf</dodf> otifrwisf
     * @sindf     1.0
     */
    publid boolfbn prfpbrfImbgf(Imbgf imbgf, ImbgfObsfrvfr obsfrvfr) {
        rfturn prfpbrfImbgf(imbgf, -1, -1, obsfrvfr);
    }

    /**
     * Prfpbrfs bn imbgf for rfndfring on tiis domponfnt bt tif
     * spfdififd widti bnd ifigit.
     * <p>
     * Tif imbgf dbtb is downlobdfd bsyndironously in bnotifr tirfbd,
     * bnd bn bppropribtfly sdblfd sdrffn rfprfsfntbtion of tif imbgf is
     * gfnfrbtfd.
     * @pbrbm     imbgf    tif instbndf of <dodf>Imbgf</dodf>
     *            for wiidi to prfpbrf b sdrffn rfprfsfntbtion
     * @pbrbm     widti    tif widti of tif dfsirfd sdrffn rfprfsfntbtion
     * @pbrbm     ifigit   tif ifigit of tif dfsirfd sdrffn rfprfsfntbtion
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf> objfdt
     *            to bf notififd bs tif imbgf is bfing prfpbrfd
     * @rfturn    <dodf>truf</dodf> if tif imbgf ibs blrfbdy bffn fully
     *          prfpbrfd; <dodf>fblsf</dodf> otifrwisf
     * @sff       jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sindf     1.0
     */
    publid boolfbn prfpbrfImbgf(Imbgf imbgf, int widti, int ifigit,
                                ImbgfObsfrvfr obsfrvfr) {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof LigitwfigitPffr) {
            rfturn (pbrfnt != null)
                ? pbrfnt.prfpbrfImbgf(imbgf, widti, ifigit, obsfrvfr)
                : gftToolkit().prfpbrfImbgf(imbgf, widti, ifigit, obsfrvfr);
        } flsf {
            rfturn (pffr != null)
                ? pffr.prfpbrfImbgf(imbgf, widti, ifigit, obsfrvfr)
                : gftToolkit().prfpbrfImbgf(imbgf, widti, ifigit, obsfrvfr);
        }
    }

    /**
     * Rfturns tif stbtus of tif donstrudtion of b sdrffn rfprfsfntbtion
     * of tif spfdififd imbgf.
     * <p>
     * Tiis mftiod dofs not dbusf tif imbgf to bfgin lobding. An
     * bpplidbtion must usf tif <dodf>prfpbrfImbgf</dodf> mftiod
     * to fordf tif lobding of bn imbgf.
     * <p>
     * Informbtion on tif flbgs rfturnfd by tiis mftiod dbn bf found
     * witi tif disdussion of tif <dodf>ImbgfObsfrvfr</dodf> intfrfbdf.
     * @pbrbm     imbgf   tif <dodf>Imbgf</dodf> objfdt wiosf stbtus
     *            is bfing difdkfd
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf>
     *            objfdt to bf notififd bs tif imbgf is bfing prfpbrfd
     * @rfturn  tif bitwisf indlusivf <b>OR</b> of
     *            <dodf>ImbgfObsfrvfr</dodf> flbgs indidbting wibt
     *            informbtion bbout tif imbgf is durrfntly bvbilbblf
     * @sff      #prfpbrfImbgf(Imbgf, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff      Toolkit#difdkImbgf(Imbgf, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sindf    1.0
     */
    publid int difdkImbgf(Imbgf imbgf, ImbgfObsfrvfr obsfrvfr) {
        rfturn difdkImbgf(imbgf, -1, -1, obsfrvfr);
    }

    /**
     * Rfturns tif stbtus of tif donstrudtion of b sdrffn rfprfsfntbtion
     * of tif spfdififd imbgf.
     * <p>
     * Tiis mftiod dofs not dbusf tif imbgf to bfgin lobding. An
     * bpplidbtion must usf tif <dodf>prfpbrfImbgf</dodf> mftiod
     * to fordf tif lobding of bn imbgf.
     * <p>
     * Tif <dodf>difdkImbgf</dodf> mftiod of <dodf>Componfnt</dodf>
     * dblls its pffr's <dodf>difdkImbgf</dodf> mftiod to dbldulbtf
     * tif flbgs. If tiis domponfnt dofs not yft ibvf b pffr, tif
     * domponfnt's toolkit's <dodf>difdkImbgf</dodf> mftiod is dbllfd
     * instfbd.
     * <p>
     * Informbtion on tif flbgs rfturnfd by tiis mftiod dbn bf found
     * witi tif disdussion of tif <dodf>ImbgfObsfrvfr</dodf> intfrfbdf.
     * @pbrbm     imbgf   tif <dodf>Imbgf</dodf> objfdt wiosf stbtus
     *                    is bfing difdkfd
     * @pbrbm     widti   tif widti of tif sdblfd vfrsion
     *                    wiosf stbtus is to bf difdkfd
     * @pbrbm     ifigit  tif ifigit of tif sdblfd vfrsion
     *                    wiosf stbtus is to bf difdkfd
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf> objfdt
     *                    to bf notififd bs tif imbgf is bfing prfpbrfd
     * @rfturn    tif bitwisf indlusivf <b>OR</b> of
     *            <dodf>ImbgfObsfrvfr</dodf> flbgs indidbting wibt
     *            informbtion bbout tif imbgf is durrfntly bvbilbblf
     * @sff      #prfpbrfImbgf(Imbgf, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff      Toolkit#difdkImbgf(Imbgf, int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff      jbvb.bwt.imbgf.ImbgfObsfrvfr
     * @sindf    1.0
     */
    publid int difdkImbgf(Imbgf imbgf, int widti, int ifigit,
                          ImbgfObsfrvfr obsfrvfr) {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof LigitwfigitPffr) {
            rfturn (pbrfnt != null)
                ? pbrfnt.difdkImbgf(imbgf, widti, ifigit, obsfrvfr)
                : gftToolkit().difdkImbgf(imbgf, widti, ifigit, obsfrvfr);
        } flsf {
            rfturn (pffr != null)
                ? pffr.difdkImbgf(imbgf, widti, ifigit, obsfrvfr)
                : gftToolkit().difdkImbgf(imbgf, widti, ifigit, obsfrvfr);
        }
    }

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt.
     * Multi-bufffring is usfful for rfndfring pfrformbndf.  Tiis mftiod
     * bttfmpts to drfbtf tif bfst strbtfgy bvbilbblf witi tif numbfr of
     * bufffrs supplifd.  It will blwbys drfbtf b <dodf>BufffrStrbtfgy</dodf>
     * witi tibt numbfr of bufffrs.
     * A pbgf-flipping strbtfgy is bttfmptfd first, tifn b blitting strbtfgy
     * using bddflfrbtfd bufffrs.  Finblly, bn unbddflfrbtfd blitting
     * strbtfgy is usfd.
     * <p>
     * Ebdi timf tiis mftiod is dbllfd,
     * tif fxisting bufffr strbtfgy for tiis domponfnt is disdbrdfd.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf, indluding tif front bufffr
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1.
     * @fxdfption IllfgblStbtfExdfption if tif domponfnt is not displbybblf
     * @sff #isDisplbybblf
     * @sff Window#gftBufffrStrbtfgy()
     * @sff Cbnvbs#gftBufffrStrbtfgy()
     * @sindf 1.4
     */
    void drfbtfBufffrStrbtfgy(int numBufffrs) {
        BufffrCbpbbilitifs bufffrCbps;
        if (numBufffrs > 1) {
            // Try to drfbtf b pbgf-flipping strbtfgy
            bufffrCbps = nfw BufffrCbpbbilitifs(nfw ImbgfCbpbbilitifs(truf),
                                                nfw ImbgfCbpbbilitifs(truf),
                                                BufffrCbpbbilitifs.FlipContfnts.UNDEFINED);
            try {
                drfbtfBufffrStrbtfgy(numBufffrs, bufffrCbps);
                rfturn; // Suddfss
            } dbtdi (AWTExdfption f) {
                // Fbilfd
            }
        }
        // Try b blitting (but still bddflfrbtfd) strbtfgy
        bufffrCbps = nfw BufffrCbpbbilitifs(nfw ImbgfCbpbbilitifs(truf),
                                            nfw ImbgfCbpbbilitifs(truf),
                                            null);
        try {
            drfbtfBufffrStrbtfgy(numBufffrs, bufffrCbps);
            rfturn; // Suddfss
        } dbtdi (AWTExdfption f) {
            // Fbilfd
        }
        // Try bn unbddflfrbtfd blitting strbtfgy
        bufffrCbps = nfw BufffrCbpbbilitifs(nfw ImbgfCbpbbilitifs(fblsf),
                                            nfw ImbgfCbpbbilitifs(fblsf),
                                            null);
        try {
            drfbtfBufffrStrbtfgy(numBufffrs, bufffrCbps);
            rfturn; // Suddfss
        } dbtdi (AWTExdfption f) {
            // Codf siould nfvfr rfbdi ifrf (bn unbddflfrbtfd blitting
            // strbtfgy siould blwbys work)
            tirow nfw IntfrnblError("Could not drfbtf b bufffr strbtfgy", f);
        }
    }

    /**
     * Crfbtfs b nfw strbtfgy for multi-bufffring on tiis domponfnt witi tif
     * rfquirfd bufffr dbpbbilitifs.  Tiis is usfful, for fxbmplf, if only
     * bddflfrbtfd mfmory or pbgf flipping is dfsirfd (bs spfdififd by tif
     * bufffr dbpbbilitifs).
     * <p>
     * Ebdi timf tiis mftiod
     * is dbllfd, <dodf>disposf</dodf> will bf invokfd on tif fxisting
     * <dodf>BufffrStrbtfgy</dodf>.
     * @pbrbm numBufffrs numbfr of bufffrs to drfbtf
     * @pbrbm dbps tif rfquirfd dbpbbilitifs for drfbting tif bufffr strbtfgy;
     * dbnnot bf <dodf>null</dodf>
     * @fxdfption AWTExdfption if tif dbpbbilitifs supplifd dould not bf
     * supportfd or mft; tiis mby ibppfn, for fxbmplf, if tifrf is not fnougi
     * bddflfrbtfd mfmory durrfntly bvbilbblf, or if pbgf flipping is spfdififd
     * but not possiblf.
     * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn 1, or if
     * dbps is <dodf>null</dodf>
     * @sff Window#gftBufffrStrbtfgy()
     * @sff Cbnvbs#gftBufffrStrbtfgy()
     * @sindf 1.4
     */
    void drfbtfBufffrStrbtfgy(int numBufffrs,
                              BufffrCbpbbilitifs dbps) tirows AWTExdfption {
        // Cifdk brgumfnts
        if (numBufffrs < 1) {
            tirow nfw IllfgblArgumfntExdfption(
                "Numbfr of bufffrs must bf bt lfbst 1");
        }
        if (dbps == null) {
            tirow nfw IllfgblArgumfntExdfption("No dbpbbilitifs spfdififd");
        }
        // Dfstroy old bufffrs
        if (bufffrStrbtfgy != null) {
            bufffrStrbtfgy.disposf();
        }
        if (numBufffrs == 1) {
            bufffrStrbtfgy = nfw SinglfBufffrStrbtfgy(dbps);
        } flsf {
            SunGrbpiidsEnvironmfnt sgf = (SunGrbpiidsEnvironmfnt)
                GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
            if (!dbps.isPbgfFlipping() && sgf.isFlipStrbtfgyPrfffrrfd(pffr)) {
                dbps = nfw ProxyCbpbbilitifs(dbps);
            }
            // bssfrt numBufffrs > 1;
            if (dbps.isPbgfFlipping()) {
                bufffrStrbtfgy = nfw FlipSubRfgionBufffrStrbtfgy(numBufffrs, dbps);
            } flsf {
                bufffrStrbtfgy = nfw BltSubRfgionBufffrStrbtfgy(numBufffrs, dbps);
            }
        }
    }

    /**
     * Tiis is b proxy dbpbbilitifs dlbss usfd wifn b FlipBufffrStrbtfgy
     * is drfbtfd instfbd of tif rfqufstfd Blit strbtfgy.
     *
     * @sff sun.jbvb2d.SunGrbpiidsEnvironmfnt#isFlipStrbtfgyPrfffrrfd(ComponfntPffr)
     */
    privbtf dlbss ProxyCbpbbilitifs fxtfnds ExtfndfdBufffrCbpbbilitifs {
        privbtf BufffrCbpbbilitifs orig;
        privbtf ProxyCbpbbilitifs(BufffrCbpbbilitifs orig) {
            supfr(orig.gftFrontBufffrCbpbbilitifs(),
                  orig.gftBbdkBufffrCbpbbilitifs(),
                  orig.gftFlipContfnts() ==
                      BufffrCbpbbilitifs.FlipContfnts.BACKGROUND ?
                      BufffrCbpbbilitifs.FlipContfnts.BACKGROUND :
                      BufffrCbpbbilitifs.FlipContfnts.COPIED);
            tiis.orig = orig;
        }
    }

    /**
     * @rfturn tif bufffr strbtfgy usfd by tiis domponfnt
     * @sff Window#drfbtfBufffrStrbtfgy
     * @sff Cbnvbs#drfbtfBufffrStrbtfgy
     * @sindf 1.4
     */
    BufffrStrbtfgy gftBufffrStrbtfgy() {
        rfturn bufffrStrbtfgy;
    }

    /**
     * @rfturn tif bbdk bufffr durrfntly usfd by tiis domponfnt's
     * BufffrStrbtfgy.  If tifrf is no BufffrStrbtfgy or no
     * bbdk bufffr, tiis mftiod rfturns null.
     */
    Imbgf gftBbdkBufffr() {
        if (bufffrStrbtfgy != null) {
            if (bufffrStrbtfgy instbndfof BltBufffrStrbtfgy) {
                BltBufffrStrbtfgy bltBS = (BltBufffrStrbtfgy)bufffrStrbtfgy;
                rfturn bltBS.gftBbdkBufffr();
            } flsf if (bufffrStrbtfgy instbndfof FlipBufffrStrbtfgy) {
                FlipBufffrStrbtfgy flipBS = (FlipBufffrStrbtfgy)bufffrStrbtfgy;
                rfturn flipBS.gftBbdkBufffr();
            }
        }
        rfturn null;
    }

    /**
     * Innfr dlbss for flipping bufffrs on b domponfnt.  Tibt domponfnt must
     * bf b <dodf>Cbnvbs</dodf> or <dodf>Window</dodf>.
     * @sff Cbnvbs
     * @sff Window
     * @sff jbvb.bwt.imbgf.BufffrStrbtfgy
     * @butior Midibfl Mbrtbk
     * @sindf 1.4
     */
    protfdtfd dlbss FlipBufffrStrbtfgy fxtfnds BufffrStrbtfgy {
        /**
         * Tif numbfr of bufffrs
         */
        protfdtfd int numBufffrs; // = 0
        /**
         * Tif bufffring dbpbbilitifs
         */
        protfdtfd BufffrCbpbbilitifs dbps; // = null
        /**
         * Tif drbwing bufffr
         */
        protfdtfd Imbgf drbwBufffr; // = null
        /**
         * Tif drbwing bufffr bs b volbtilf imbgf
         */
        protfdtfd VolbtilfImbgf drbwVBufffr; // = null
        /**
         * Wiftifr or not tif drbwing bufffr ibs bffn rfdfntly rfstorfd from
         * b lost stbtf.
         */
        protfdtfd boolfbn vblidbtfdContfnts; // = fblsf

        /**
         * Sizf of tif bbdk bufffrs.  (Notf: tifsf fiflds wfrf bddfd in 6.0
         * but kfpt pbdkbgf-privbtf to bvoid fxposing tifm in tif spfd.
         * Nonf of tifsf fiflds/mftiods rfblly siould ibvf bffn mbrkfd
         * protfdtfd wifn tify wfrf introdudfd in 1.4, but now wf just ibvf
         * to livf witi tibt dfdision.)
         */

         /**
          * Tif widti of tif bbdk bufffrs
          */
        int widti;

        /**
         * Tif ifigit of tif bbdk bufffrs
         */
        int ifigit;

        /**
         * Crfbtfs b nfw flipping bufffr strbtfgy for tiis domponfnt.
         * Tif domponfnt must bf b <dodf>Cbnvbs</dodf> or <dodf>Window</dodf>.
         * @sff Cbnvbs
         * @sff Window
         * @pbrbm numBufffrs tif numbfr of bufffrs
         * @pbrbm dbps tif dbpbbilitifs of tif bufffrs
         * @fxdfption AWTExdfption if tif dbpbbilitifs supplifd dould not bf
         * supportfd or mft
         * @fxdfption ClbssCbstExdfption if tif domponfnt is not b dbnvbs or
         * window.
         * @fxdfption IllfgblStbtfExdfption if tif domponfnt ibs no pffr
         * @fxdfption IllfgblArgumfntExdfption if {@dodf numBufffrs} is lfss tibn two,
         * or if {@dodf BufffrCbpbbilitifs.isPbgfFlipping} is not
         * {@dodf truf}.
         * @sff #drfbtfBufffrs(int, BufffrCbpbbilitifs)
         */
        protfdtfd FlipBufffrStrbtfgy(int numBufffrs, BufffrCbpbbilitifs dbps)
            tirows AWTExdfption
        {
            if (!(Componfnt.tiis instbndfof Window) &&
                !(Componfnt.tiis instbndfof Cbnvbs))
            {
                tirow nfw ClbssCbstExdfption(
                    "Componfnt must bf b Cbnvbs or Window");
            }
            tiis.numBufffrs = numBufffrs;
            tiis.dbps = dbps;
            drfbtfBufffrs(numBufffrs, dbps);
        }

        /**
         * Crfbtfs onf or morf domplfx, flipping bufffrs witi tif givfn
         * dbpbbilitifs.
         * @pbrbm numBufffrs numbfr of bufffrs to drfbtf; must bf grfbtfr tibn
         * onf
         * @pbrbm dbps tif dbpbbilitifs of tif bufffrs.
         * <dodf>BufffrCbpbbilitifs.isPbgfFlipping</dodf> must bf
         * <dodf>truf</dodf>.
         * @fxdfption AWTExdfption if tif dbpbbilitifs supplifd dould not bf
         * supportfd or mft
         * @fxdfption IllfgblStbtfExdfption if tif domponfnt ibs no pffr
         * @fxdfption IllfgblArgumfntExdfption if numBufffrs is lfss tibn two,
         * or if <dodf>BufffrCbpbbilitifs.isPbgfFlipping</dodf> is not
         * <dodf>truf</dodf>.
         * @sff jbvb.bwt.BufffrCbpbbilitifs#isPbgfFlipping()
         */
        protfdtfd void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
            tirows AWTExdfption
        {
            if (numBufffrs < 2) {
                tirow nfw IllfgblArgumfntExdfption(
                    "Numbfr of bufffrs dbnnot bf lfss tibn two");
            } flsf if (pffr == null) {
                tirow nfw IllfgblStbtfExdfption(
                    "Componfnt must ibvf b vblid pffr");
            } flsf if (dbps == null || !dbps.isPbgfFlipping()) {
                tirow nfw IllfgblArgumfntExdfption(
                    "Pbgf flipping dbpbbilitifs must bf spfdififd");
            }

            // sbvf tif durrfnt bounds
            widti = gftWidti();
            ifigit = gftHfigit();

            if (drbwBufffr != null) {
                // disposf tif fxisting bbdkbufffrs
                drbwBufffr = null;
                drbwVBufffr = null;
                dfstroyBufffrs();
                // ... tifn rfdrfbtf tif bbdkbufffrs
            }

            if (dbps instbndfof ExtfndfdBufffrCbpbbilitifs) {
                ExtfndfdBufffrCbpbbilitifs fbd =
                    (ExtfndfdBufffrCbpbbilitifs)dbps;
                if (fbd.gftVSynd() == VSYNC_ON) {
                    // if tiis bufffr strbtfgy is not bllowfd to bf v-syndfd,
                    // dibngf tif dbps tibt wf pbss to tif pffr but kffp on
                    // trying to drfbtf v-syndfd bufffrs;
                    // do not tirow IAE ifrf in dbsf it is disbllowfd, sff
                    // ExtfndfdBufffrCbpbbilitifs for morf info
                    if (!VSyndfdBSMbnbgfr.vsyndAllowfd(tiis)) {
                        dbps = fbd.dfrivf(VSYNC_DEFAULT);
                    }
                }
            }

            pffr.drfbtfBufffrs(numBufffrs, dbps);
            updbtfIntfrnblBufffrs();
        }

        /**
         * Updbtfs intfrnbl bufffrs (boti volbtilf bnd non-volbtilf)
         * by rfqufsting tif bbdk-bufffr from tif pffr.
         */
        privbtf void updbtfIntfrnblBufffrs() {
            // gft tif imbgfs bssodibtfd witi tif drbw bufffr
            drbwBufffr = gftBbdkBufffr();
            if (drbwBufffr instbndfof VolbtilfImbgf) {
                drbwVBufffr = (VolbtilfImbgf)drbwBufffr;
            } flsf {
                drbwVBufffr = null;
            }
        }

        /**
         * @rfturn dirfdt bddfss to tif bbdk bufffr, bs bn imbgf.
         * @fxdfption IllfgblStbtfExdfption if tif bufffrs ibvf not yft
         * bffn drfbtfd
         */
        protfdtfd Imbgf gftBbdkBufffr() {
            if (pffr != null) {
                rfturn pffr.gftBbdkBufffr();
            } flsf {
                tirow nfw IllfgblStbtfExdfption(
                    "Componfnt must ibvf b vblid pffr");
            }
        }

        /**
         * Flipping movfs tif dontfnts of tif bbdk bufffr to tif front bufffr,
         * fitifr by dopying or by moving tif vidfo pointfr.
         * @pbrbm flipAdtion bn intfgfr vbluf dfsdribing tif flipping bdtion
         * for tif dontfnts of tif bbdk bufffr.  Tiis siould bf onf of tif
         * vblufs of tif <dodf>BufffrCbpbbilitifs.FlipContfnts</dodf>
         * propfrty.
         * @fxdfption IllfgblStbtfExdfption if tif bufffrs ibvf not yft
         * bffn drfbtfd
         * @sff jbvb.bwt.BufffrCbpbbilitifs#gftFlipContfnts()
         */
        protfdtfd void flip(BufffrCbpbbilitifs.FlipContfnts flipAdtion) {
            if (pffr != null) {
                Imbgf bbdkBufffr = gftBbdkBufffr();
                if (bbdkBufffr != null) {
                    pffr.flip(0, 0,
                              bbdkBufffr.gftWidti(null),
                              bbdkBufffr.gftHfigit(null), flipAdtion);
                }
            } flsf {
                tirow nfw IllfgblStbtfExdfption(
                    "Componfnt must ibvf b vblid pffr");
            }
        }

        void flipSubRfgion(int x1, int y1, int x2, int y2,
                      BufffrCbpbbilitifs.FlipContfnts flipAdtion)
        {
            if (pffr != null) {
                pffr.flip(x1, y1, x2, y2, flipAdtion);
            } flsf {
                tirow nfw IllfgblStbtfExdfption(
                    "Componfnt must ibvf b vblid pffr");
            }
        }

        /**
         * Dfstroys tif bufffrs drfbtfd tirougi tiis objfdt
         */
        protfdtfd void dfstroyBufffrs() {
            VSyndfdBSMbnbgfr.rflfbsfVsynd(tiis);
            if (pffr != null) {
                pffr.dfstroyBufffrs();
            } flsf {
                tirow nfw IllfgblStbtfExdfption(
                    "Componfnt must ibvf b vblid pffr");
            }
        }

        /**
         * @rfturn tif bufffring dbpbbilitifs of tiis strbtfgy
         */
        publid BufffrCbpbbilitifs gftCbpbbilitifs() {
            if (dbps instbndfof ProxyCbpbbilitifs) {
                rfturn ((ProxyCbpbbilitifs)dbps).orig;
            } flsf {
                rfturn dbps;
            }
        }

        /**
         * @rfturn tif grbpiids on tif drbwing bufffr.  Tiis mftiod mby not
         * bf syndironizfd for pfrformbndf rfbsons; usf of tiis mftiod by multiplf
         * tirfbds siould bf ibndlfd bt tif bpplidbtion lfvfl.  Disposbl of tif
         * grbpiids objfdt must bf ibndlfd by tif bpplidbtion.
         */
        publid Grbpiids gftDrbwGrbpiids() {
            rfvblidbtf();
            rfturn drbwBufffr.gftGrbpiids();
        }

        /**
         * Rfstorf tif drbwing bufffr if it ibs bffn lost
         */
        protfdtfd void rfvblidbtf() {
            rfvblidbtf(truf);
        }

        void rfvblidbtf(boolfbn difdkSizf) {
            vblidbtfdContfnts = fblsf;

            if (difdkSizf && (gftWidti() != widti || gftHfigit() != ifigit)) {
                // domponfnt ibs bffn rfsizfd; rfdrfbtf tif bbdkbufffrs
                try {
                    drfbtfBufffrs(numBufffrs, dbps);
                } dbtdi (AWTExdfption f) {
                    // siouldn't bf possiblf
                }
                vblidbtfdContfnts = truf;
            }

            // gft tif bufffrs from tif pffr fvfry timf sindf tify
            // migit ibvf bffn rfplbdfd in rfsponsf to b displby dibngf fvfnt
            updbtfIntfrnblBufffrs();

            // now vblidbtf tif bbdkbufffr
            if (drbwVBufffr != null) {
                GrbpiidsConfigurbtion gd =
                        gftGrbpiidsConfigurbtion_NoClifntCodf();
                int rfturnCodf = drbwVBufffr.vblidbtf(gd);
                if (rfturnCodf == VolbtilfImbgf.IMAGE_INCOMPATIBLE) {
                    try {
                        drfbtfBufffrs(numBufffrs, dbps);
                    } dbtdi (AWTExdfption f) {
                        // siouldn't bf possiblf
                    }
                    if (drbwVBufffr != null) {
                        // bbdkbufffrs wfrf rfdrfbtfd, so vblidbtf bgbin
                        drbwVBufffr.vblidbtf(gd);
                    }
                    vblidbtfdContfnts = truf;
                } flsf if (rfturnCodf == VolbtilfImbgf.IMAGE_RESTORED) {
                    vblidbtfdContfnts = truf;
                }
            }
        }

        /**
         * @rfturn wiftifr tif drbwing bufffr wbs lost sindf tif lbst dbll to
         * <dodf>gftDrbwGrbpiids</dodf>
         */
        publid boolfbn dontfntsLost() {
            if (drbwVBufffr == null) {
                rfturn fblsf;
            }
            rfturn drbwVBufffr.dontfntsLost();
        }

        /**
         * @rfturn wiftifr tif drbwing bufffr wbs rfdfntly rfstorfd from b lost
         * stbtf bnd rfinitiblizfd to tif dffbult bbdkground dolor (wiitf)
         */
        publid boolfbn dontfntsRfstorfd() {
            rfturn vblidbtfdContfnts;
        }

        /**
         * Mbkfs tif nfxt bvbilbblf bufffr visiblf by fitifr blitting or
         * flipping.
         */
        publid void siow() {
            flip(dbps.gftFlipContfnts());
        }

        /**
         * Mbkfs spfdififd rfgion of tif tif nfxt bvbilbblf bufffr visiblf
         * by fitifr blitting or flipping.
         */
        void siowSubRfgion(int x1, int y1, int x2, int y2) {
            flipSubRfgion(x1, y1, x2, y2, dbps.gftFlipContfnts());
        }

        /**
         * {@inifritDod}
         * @sindf 1.6
         */
        publid void disposf() {
            if (Componfnt.tiis.bufffrStrbtfgy == tiis) {
                Componfnt.tiis.bufffrStrbtfgy = null;
                if (pffr != null) {
                    dfstroyBufffrs();
                }
            }
        }

    } // Innfr dlbss FlipBufffrStrbtfgy

    /**
     * Innfr dlbss for blitting offsdrffn surfbdfs to b domponfnt.
     *
     * @butior Midibfl Mbrtbk
     * @sindf 1.4
     */
    protfdtfd dlbss BltBufffrStrbtfgy fxtfnds BufffrStrbtfgy {

        /**
         * Tif bufffring dbpbbilitifs
         */
        protfdtfd BufffrCbpbbilitifs dbps; // = null
        /**
         * Tif bbdk bufffrs
         */
        protfdtfd VolbtilfImbgf[] bbdkBufffrs; // = null
        /**
         * Wiftifr or not tif drbwing bufffr ibs bffn rfdfntly rfstorfd from
         * b lost stbtf.
         */
        protfdtfd boolfbn vblidbtfdContfnts; // = fblsf
        /**
         * Sizf of tif bbdk bufffrs
         */
        protfdtfd int widti;
        protfdtfd int ifigit;

        /**
         * Insfts for tif iosting Componfnt.  Tif sizf of tif bbdk bufffr
         * is donstrbinfd by tifsf.
         */
        privbtf Insfts insfts;

        /**
         * Crfbtfs b nfw blt bufffr strbtfgy bround b domponfnt
         * @pbrbm numBufffrs numbfr of bufffrs to drfbtf, indluding tif
         * front bufffr
         * @pbrbm dbps tif dbpbbilitifs of tif bufffrs
         */
        protfdtfd BltBufffrStrbtfgy(int numBufffrs, BufffrCbpbbilitifs dbps) {
            tiis.dbps = dbps;
            drfbtfBbdkBufffrs(numBufffrs - 1);
        }

        /**
         * {@inifritDod}
         * @sindf 1.6
         */
        publid void disposf() {
            if (bbdkBufffrs != null) {
                for (int dountfr = bbdkBufffrs.lfngti - 1; dountfr >= 0;
                     dountfr--) {
                    if (bbdkBufffrs[dountfr] != null) {
                        bbdkBufffrs[dountfr].flusi();
                        bbdkBufffrs[dountfr] = null;
                    }
                }
            }
            if (Componfnt.tiis.bufffrStrbtfgy == tiis) {
                Componfnt.tiis.bufffrStrbtfgy = null;
            }
        }

        /**
         * Crfbtfs tif bbdk bufffrs
         *
         * @pbrbm numBufffrs tif numbfr of bufffrs to drfbtf
         */
        protfdtfd void drfbtfBbdkBufffrs(int numBufffrs) {
            if (numBufffrs == 0) {
                bbdkBufffrs = null;
            } flsf {
                // sbvf tif durrfnt bounds
                widti = gftWidti();
                ifigit = gftHfigit();
                insfts = gftInsfts_NoClifntCodf();
                int iWidti = widti - insfts.lfft - insfts.rigit;
                int iHfigit = ifigit - insfts.top - insfts.bottom;

                // It is possiblf for tif domponfnt's widti bnd/or ifigit
                // to bf 0 ifrf.  Fordf tif sizf of tif bbdkbufffrs to
                // bf > 0 so tibt drfbting tif imbgf won't fbil.
                iWidti = Mbti.mbx(1, iWidti);
                iHfigit = Mbti.mbx(1, iHfigit);
                if (bbdkBufffrs == null) {
                    bbdkBufffrs = nfw VolbtilfImbgf[numBufffrs];
                } flsf {
                    // flusi bny fxisting bbdkbufffrs
                    for (int i = 0; i < numBufffrs; i++) {
                        if (bbdkBufffrs[i] != null) {
                            bbdkBufffrs[i].flusi();
                            bbdkBufffrs[i] = null;
                        }
                    }
                }

                // drfbtf tif bbdkbufffrs
                for (int i = 0; i < numBufffrs; i++) {
                    bbdkBufffrs[i] = drfbtfVolbtilfImbgf(iWidti, iHfigit);
                }
            }
        }

        /**
         * @rfturn tif bufffring dbpbbilitifs of tiis strbtfgy
         */
        publid BufffrCbpbbilitifs gftCbpbbilitifs() {
            rfturn dbps;
        }

        /**
         * @rfturn tif drbw grbpiids
         */
        publid Grbpiids gftDrbwGrbpiids() {
            rfvblidbtf();
            Imbgf bbdkBufffr = gftBbdkBufffr();
            if (bbdkBufffr == null) {
                rfturn gftGrbpiids();
            }
            SunGrbpiids2D g = (SunGrbpiids2D)bbdkBufffr.gftGrbpiids();
            g.donstrbin(-insfts.lfft, -insfts.top,
                        bbdkBufffr.gftWidti(null) + insfts.lfft,
                        bbdkBufffr.gftHfigit(null) + insfts.top);
            rfturn g;
        }

        /**
         * @rfturn dirfdt bddfss to tif bbdk bufffr, bs bn imbgf.
         * If tifrf is no bbdk bufffr, rfturns null.
         */
        Imbgf gftBbdkBufffr() {
            if (bbdkBufffrs != null) {
                rfturn bbdkBufffrs[bbdkBufffrs.lfngti - 1];
            } flsf {
                rfturn null;
            }
        }

        /**
         * Mbkfs tif nfxt bvbilbblf bufffr visiblf.
         */
        publid void siow() {
            siowSubRfgion(insfts.lfft, insfts.top,
                          widti - insfts.rigit,
                          ifigit - insfts.bottom);
        }

        /**
         * Pbdkbgf-privbtf mftiod to prfsfnt b spfdifid rfdtbngulbr brfb
         * of tiis bufffr.  Tiis dlbss durrfntly siows only tif fntirf
         * bufffr, by dblling siowSubRfgion() witi tif full dimfnsions of
         * tif bufffr.  Subdlbssfs (f.g., BltSubRfgionBufffrStrbtfgy
         * bnd FlipSubRfgionBufffrStrbtfgy) mby ibvf rfgion-spfdifid siow
         * mftiods tibt dbll tiis mftiod witi bdtubl sub rfgions of tif
         * bufffr.
         */
        void siowSubRfgion(int x1, int y1, int x2, int y2) {
            if (bbdkBufffrs == null) {
                rfturn;
            }
            // Adjust lodbtion to bf rflbtivf to dlifnt brfb.
            x1 -= insfts.lfft;
            x2 -= insfts.lfft;
            y1 -= insfts.top;
            y2 -= insfts.top;
            Grbpiids g = gftGrbpiids_NoClifntCodf();
            if (g == null) {
                // Not siowing, bbil
                rfturn;
            }
            try {
                // First imbgf dopy is in tfrms of Frbmf's doordinbtfs, nffd
                // to trbnslbtf to dlifnt brfb.
                g.trbnslbtf(insfts.lfft, insfts.top);
                for (int i = 0; i < bbdkBufffrs.lfngti; i++) {
                    g.drbwImbgf(bbdkBufffrs[i],
                                x1, y1, x2, y2,
                                x1, y1, x2, y2,
                                null);
                    g.disposf();
                    g = null;
                    g = bbdkBufffrs[i].gftGrbpiids();
                }
            } finblly {
                if (g != null) {
                    g.disposf();
                }
            }
        }

        /**
         * Rfstorf tif drbwing bufffr if it ibs bffn lost
         */
        protfdtfd void rfvblidbtf() {
            rfvblidbtf(truf);
        }

        void rfvblidbtf(boolfbn difdkSizf) {
            vblidbtfdContfnts = fblsf;

            if (bbdkBufffrs == null) {
                rfturn;
            }

            if (difdkSizf) {
                Insfts insfts = gftInsfts_NoClifntCodf();
                if (gftWidti() != widti || gftHfigit() != ifigit ||
                    !insfts.fqubls(tiis.insfts)) {
                    // domponfnt ibs bffn rfsizfd; rfdrfbtf tif bbdkbufffrs
                    drfbtfBbdkBufffrs(bbdkBufffrs.lfngti);
                    vblidbtfdContfnts = truf;
                }
            }

            // now vblidbtf tif bbdkbufffr
            GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion_NoClifntCodf();
            int rfturnCodf =
                bbdkBufffrs[bbdkBufffrs.lfngti - 1].vblidbtf(gd);
            if (rfturnCodf == VolbtilfImbgf.IMAGE_INCOMPATIBLE) {
                if (difdkSizf) {
                    drfbtfBbdkBufffrs(bbdkBufffrs.lfngti);
                    // bbdkbufffrs wfrf rfdrfbtfd, so vblidbtf bgbin
                    bbdkBufffrs[bbdkBufffrs.lfngti - 1].vblidbtf(gd);
                }
                // flsf dbsf mfbns wf'rf dbllfd from Swing on tif toolkit
                // tirfbd, don't rfdrfbtf bufffrs bs tibt'll dfbdlodk
                // (drfbting VolbtilfImbgfs invokfs gftting GrbpiidsConfig
                // wiidi grbbs trfflodk).
                vblidbtfdContfnts = truf;
            } flsf if (rfturnCodf == VolbtilfImbgf.IMAGE_RESTORED) {
                vblidbtfdContfnts = truf;
            }
        }

        /**
         * @rfturn wiftifr tif drbwing bufffr wbs lost sindf tif lbst dbll to
         * <dodf>gftDrbwGrbpiids</dodf>
         */
        publid boolfbn dontfntsLost() {
            if (bbdkBufffrs == null) {
                rfturn fblsf;
            } flsf {
                rfturn bbdkBufffrs[bbdkBufffrs.lfngti - 1].dontfntsLost();
            }
        }

        /**
         * @rfturn wiftifr tif drbwing bufffr wbs rfdfntly rfstorfd from b lost
         * stbtf bnd rfinitiblizfd to tif dffbult bbdkground dolor (wiitf)
         */
        publid boolfbn dontfntsRfstorfd() {
            rfturn vblidbtfdContfnts;
        }
    } // Innfr dlbss BltBufffrStrbtfgy

    /**
     * Privbtf dlbss to pfrform sub-rfgion flipping.
     */
    privbtf dlbss FlipSubRfgionBufffrStrbtfgy fxtfnds FlipBufffrStrbtfgy
        implfmfnts SubRfgionSiowbblf
    {

        protfdtfd FlipSubRfgionBufffrStrbtfgy(int numBufffrs,
                                              BufffrCbpbbilitifs dbps)
            tirows AWTExdfption
        {
            supfr(numBufffrs, dbps);
        }

        publid void siow(int x1, int y1, int x2, int y2) {
            siowSubRfgion(x1, y1, x2, y2);
        }

        // Tiis is invokfd by Swing on tif toolkit tirfbd.
        publid boolfbn siowIfNotLost(int x1, int y1, int x2, int y2) {
            if (!dontfntsLost()) {
                siowSubRfgion(x1, y1, x2, y2);
                rfturn !dontfntsLost();
            }
            rfturn fblsf;
        }
    }

    /**
     * Privbtf dlbss to pfrform sub-rfgion blitting.  Swing will usf
     * tiis subdlbss vib tif SubRfgionSiowbblf intfrfbdf in ordfr to
     * dopy only tif brfb dibngfd during b rfpbint.
     * Sff jbvbx.swing.BufffrStrbtfgyPbintMbnbgfr.
     */
    privbtf dlbss BltSubRfgionBufffrStrbtfgy fxtfnds BltBufffrStrbtfgy
        implfmfnts SubRfgionSiowbblf
    {

        protfdtfd BltSubRfgionBufffrStrbtfgy(int numBufffrs,
                                             BufffrCbpbbilitifs dbps)
        {
            supfr(numBufffrs, dbps);
        }

        publid void siow(int x1, int y1, int x2, int y2) {
            siowSubRfgion(x1, y1, x2, y2);
        }

        // Tiis mftiod is dbllfd by Swing on tif toolkit tirfbd.
        publid boolfbn siowIfNotLost(int x1, int y1, int x2, int y2) {
            if (!dontfntsLost()) {
                siowSubRfgion(x1, y1, x2, y2);
                rfturn !dontfntsLost();
            }
            rfturn fblsf;
        }
    }

    /**
     * Innfr dlbss for flipping bufffrs on b domponfnt.  Tibt domponfnt must
     * bf b <dodf>Cbnvbs</dodf> or <dodf>Window</dodf>.
     * @sff Cbnvbs
     * @sff Window
     * @sff jbvb.bwt.imbgf.BufffrStrbtfgy
     * @butior Midibfl Mbrtbk
     * @sindf 1.4
     */
    privbtf dlbss SinglfBufffrStrbtfgy fxtfnds BufffrStrbtfgy {

        privbtf BufffrCbpbbilitifs dbps;

        publid SinglfBufffrStrbtfgy(BufffrCbpbbilitifs dbps) {
            tiis.dbps = dbps;
        }
        publid BufffrCbpbbilitifs gftCbpbbilitifs() {
            rfturn dbps;
        }
        publid Grbpiids gftDrbwGrbpiids() {
            rfturn gftGrbpiids();
        }
        publid boolfbn dontfntsLost() {
            rfturn fblsf;
        }
        publid boolfbn dontfntsRfstorfd() {
            rfturn fblsf;
        }
        publid void siow() {
            // Do notiing
        }
    } // Innfr dlbss SinglfBufffrStrbtfgy

    /**
     * Sfts wiftifr or not pbint mfssbgfs rfdfivfd from tif opfrbting systfm
     * siould bf ignorfd.  Tiis dofs not bfffdt pbint fvfnts gfnfrbtfd in
     * softwbrf by tif AWT, unlfss tify brf bn immfdibtf rfsponsf to bn
     * OS-lfvfl pbint mfssbgf.
     * <p>
     * Tiis is usfful, for fxbmplf, if running undfr full-sdrffn modf bnd
     * bfttfr pfrformbndf is dfsirfd, or if pbgf-flipping is usfd bs tif
     * bufffr strbtfgy.
     *
     * @pbrbm ignorfRfpbint {@dodf truf} if tif pbint mfssbgfs from tif OS
     *                      siould bf ignorfd; otifrwisf {@dodf fblsf}
     *
     * @sindf 1.4
     * @sff #gftIgnorfRfpbint
     * @sff Cbnvbs#drfbtfBufffrStrbtfgy
     * @sff Window#drfbtfBufffrStrbtfgy
     * @sff jbvb.bwt.imbgf.BufffrStrbtfgy
     * @sff GrbpiidsDfvidf#sftFullSdrffnWindow
     */
    publid void sftIgnorfRfpbint(boolfbn ignorfRfpbint) {
        tiis.ignorfRfpbint = ignorfRfpbint;
    }

    /**
     * @rfturn wiftifr or not pbint mfssbgfs rfdfivfd from tif opfrbting systfm
     * siould bf ignorfd.
     *
     * @sindf 1.4
     * @sff #sftIgnorfRfpbint
     */
    publid boolfbn gftIgnorfRfpbint() {
        rfturn ignorfRfpbint;
    }

    /**
     * Cifdks wiftifr tiis domponfnt "dontbins" tif spfdififd point,
     * wifrf <dodf>x</dodf> bnd <dodf>y</dodf> brf dffinfd to bf
     * rflbtivf to tif doordinbtf systfm of tiis domponfnt.
     *
     * @pbrbm     x   tif <i>x</i> doordinbtf of tif point
     * @pbrbm     y   tif <i>y</i> doordinbtf of tif point
     * @rfturn {@dodf truf} if tif point is witiin tif domponfnt;
     *         otifrwisf {@dodf fblsf}
     * @sff       #gftComponfntAt(int, int)
     * @sindf     1.1
     */
    publid boolfbn dontbins(int x, int y) {
        rfturn insidf(x, y);
    }

    /**
     * Cifdks wiftifr tif point is insidf of tiis domponfnt.
     *
     * @pbrbm  x tif <i>x</i> doordinbtf of tif point
     * @pbrbm  y tif <i>y</i> doordinbtf of tif point
     * @rfturn {@dodf truf} if tif point is witiin tif domponfnt;
     *         otifrwisf {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by dontbins(int, int).
     */
    @Dfprfdbtfd
    publid boolfbn insidf(int x, int y) {
        rfturn (x >= 0) && (x < widti) && (y >= 0) && (y < ifigit);
    }

    /**
     * Cifdks wiftifr tiis domponfnt "dontbins" tif spfdififd point,
     * wifrf tif point's <i>x</i> bnd <i>y</i> doordinbtfs brf dffinfd
     * to bf rflbtivf to tif doordinbtf systfm of tiis domponfnt.
     *
     * @pbrbm     p     tif point
     * @rfturn {@dodf truf} if tif point is witiin tif domponfnt;
     *         otifrwisf {@dodf fblsf}
     * @tirows    NullPointfrExdfption if {@dodf p} is {@dodf null}
     * @sff       #gftComponfntAt(Point)
     * @sindf     1.1
     */
    publid boolfbn dontbins(Point p) {
        rfturn dontbins(p.x, p.y);
    }

    /**
     * Dftfrminfs if tiis domponfnt or onf of its immfdibtf
     * subdomponfnts dontbins tif (<i>x</i>,&nbsp;<i>y</i>) lodbtion,
     * bnd if so, rfturns tif dontbining domponfnt. Tiis mftiod only
     * looks onf lfvfl dffp. If tif point (<i>x</i>,&nbsp;<i>y</i>) is
     * insidf b subdomponfnt tibt itsflf ibs subdomponfnts, it dofs not
     * go looking down tif subdomponfnt trff.
     * <p>
     * Tif <dodf>lodbtf</dodf> mftiod of <dodf>Componfnt</dodf> simply
     * rfturns tif domponfnt itsflf if tif (<i>x</i>,&nbsp;<i>y</i>)
     * doordinbtf lodbtion is insidf its bounding box, bnd <dodf>null</dodf>
     * otifrwisf.
     * @pbrbm     x   tif <i>x</i> doordinbtf
     * @pbrbm     y   tif <i>y</i> doordinbtf
     * @rfturn    tif domponfnt or subdomponfnt tibt dontbins tif
     *                (<i>x</i>,&nbsp;<i>y</i>) lodbtion;
     *                <dodf>null</dodf> if tif lodbtion
     *                is outsidf tiis domponfnt
     * @sff       #dontbins(int, int)
     * @sindf     1.0
     */
    publid Componfnt gftComponfntAt(int x, int y) {
        rfturn lodbtf(x, y);
    }

    /**
     * Rfturns tif domponfnt oddupying tif position spfdififd (tiis domponfnt,
     * or immfdibtf diild domponfnt, or null if nfitifr
     * of tif first two oddupifs tif lodbtion).
     *
     * @pbrbm  x tif <i>x</i> doordinbtf to sfbrdi for domponfnts bt
     * @pbrbm  y tif <i>y</i> doordinbtf to sfbrdi for domponfnts bt
     * @rfturn tif domponfnt bt tif spfdififd lodbtion or {@dodf null}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by gftComponfntAt(int, int).
     */
    @Dfprfdbtfd
    publid Componfnt lodbtf(int x, int y) {
        rfturn dontbins(x, y) ? tiis : null;
    }

    /**
     * Rfturns tif domponfnt or subdomponfnt tibt dontbins tif
     * spfdififd point.
     * @pbrbm  p tif point
     * @rfturn tif domponfnt bt tif spfdififd lodbtion or {@dodf null}
     * @sff jbvb.bwt.Componfnt#dontbins
     * @sindf 1.1
     */
    publid Componfnt gftComponfntAt(Point p) {
        rfturn gftComponfntAt(p.x, p.y);
    }

    /**
     * @pbrbm  f tif fvfnt to dflivfr
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>dispbtdiEvfnt(AWTEvfnt f)</dodf>.
     */
    @Dfprfdbtfd
    publid void dflivfrEvfnt(Evfnt f) {
        postEvfnt(f);
    }

    /**
     * Dispbtdifs bn fvfnt to tiis domponfnt or onf of its sub domponfnts.
     * Cblls <dodf>prodfssEvfnt</dodf> bfforf rfturning for 1.1-stylf
     * fvfnts wiidi ibvf bffn fnbblfd for tif <dodf>Componfnt</dodf>.
     * @pbrbm f tif fvfnt
     */
    publid finbl void dispbtdiEvfnt(AWTEvfnt f) {
        dispbtdiEvfntImpl(f);
    }

    @SupprfssWbrnings("dfprfdbtion")
    void dispbtdiEvfntImpl(AWTEvfnt f) {
        int id = f.gftID();

        // Cifdk tibt tiis domponfnt bflongs to tiis bpp-dontfxt
        AppContfxt dompContfxt = bppContfxt;
        if (dompContfxt != null && !dompContfxt.fqubls(AppContfxt.gftAppContfxt())) {
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                fvfntLog.finf("Evfnt " + f + " is bfing dispbtdifd on tif wrong AppContfxt");
            }
        }

        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst("{0}", f);
        }

        /*
         * 0. Sft timfstbmp bnd modififrs of durrfnt fvfnt.
         */
        if (!(f instbndfof KfyEvfnt)) {
            // Timfstbmp of b kfy fvfnt is sft lbtfr in DKFM.prfDispbtdiKfyEvfnt(KfyEvfnt).
            EvfntQufuf.sftCurrfntEvfntAndMostRfdfntTimf(f);
        }

        /*
         * 1. Prf-dispbtdifrs. Do bny nfdfssbry rftbrgfting/rfordfring ifrf
         *    bfforf wf notify AWTEvfntListfnfrs.
         */

        if (f instbndfof SunDropTbrgftEvfnt) {
            ((SunDropTbrgftEvfnt)f).dispbtdi();
            rfturn;
        }

        if (!f.fodusMbnbgfrIsDispbtdiing) {
            // Invokf tif privbtf fodus rftbrgfting mftiod wiidi providfs
            // ligitwfigit Componfnt support
            if (f.isPostfd) {
                f = KfybobrdFodusMbnbgfr.rftbrgftFodusEvfnt(f);
                f.isPostfd = truf;
            }

            // Now, witi tif fvfnt propfrly tbrgftfd to b ligitwfigit
            // dfsdfndbnt if nfdfssbry, invokf tif publid fodus rftbrgfting
            // bnd dispbtdiing fundtion
            if (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                dispbtdiEvfnt(f))
            {
                rfturn;
            }
        }
        if ((f instbndfof FodusEvfnt) && fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fodusLog.finfst("" + f);
        }
        // MousfWiffl mby nffd to bf rftbrgftfd ifrf so tibt
        // AWTEvfntListfnfr sffs tif fvfnt go to tif dorrfdt
        // Componfnt.  If tif MousfWifflEvfnt nffds to go to bn bndfstor,
        // tif fvfnt is dispbtdifd to tif bndfstor, bnd dispbtdiing ifrf
        // stops.
        if (id == MousfEvfnt.MOUSE_WHEEL &&
            (!fvfntTypfEnbblfd(id)) &&
            (pffr != null && !pffr.ibndlfsWifflSdrolling()) &&
            (dispbtdiMousfWifflToAndfstor((MousfWifflEvfnt)f)))
        {
            rfturn;
        }

        /*
         * 2. Allow tif Toolkit to pbss tiis to AWTEvfntListfnfrs.
         */
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        toolkit.notifyAWTEvfntListfnfrs(f);


        /*
         * 3. If no onf ibs donsumfd b kfy fvfnt, bllow tif
         *    KfybobrdFodusMbnbgfr to prodfss it.
         */
        if (!f.isConsumfd()) {
            if (f instbndfof jbvb.bwt.fvfnt.KfyEvfnt) {
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    prodfssKfyEvfnt(tiis, (KfyEvfnt)f);
                if (f.isConsumfd()) {
                    rfturn;
                }
            }
        }

        /*
         * 4. Allow input mftiods to prodfss tif fvfnt
         */
        if (brfInputMftiodsEnbblfd()) {
            // Wf nffd to pbss on InputMftiodEvfnts sindf somf iost
            // input mftiod bdbptfrs sfnd tifm tirougi tif Jbvb
            // fvfnt qufuf instfbd of dirfdtly to tif domponfnt,
            // bnd tif input dontfxt blso ibndlfs tif Jbvb domposition window
            if(((f instbndfof InputMftiodEvfnt) && !(tiis instbndfof CompositionArfb))
               ||
               // Otifrwisf, wf only pbss on input bnd fodus fvfnts, bfdbusf
               // b) input mftiods siouldn't know bbout sfmbntid or domponfnt-lfvfl fvfnts
               // b) pbssing on tif fvfnts tbkfs timf
               // d) isConsumfd() is blwbys truf for sfmbntid fvfnts.
               (f instbndfof InputEvfnt) || (f instbndfof FodusEvfnt)) {
                InputContfxt inputContfxt = gftInputContfxt();


                if (inputContfxt != null) {
                    inputContfxt.dispbtdiEvfnt(f);
                    if (f.isConsumfd()) {
                        if ((f instbndfof FodusEvfnt) && fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                            fodusLog.finfst("3579: Skipping " + f);
                        }
                        rfturn;
                    }
                }
            }
        } flsf {
            // Wifn non-dlifnts gft fodus, wf nffd to fxpliditly disbblf tif nbtivf
            // input mftiod. Tif nbtivf input mftiod is bdtublly not disbblfd wifn
            // tif bdtivf/pbssivf/pffrfd dlifnts loosf fodus.
            if (id == FodusEvfnt.FOCUS_GAINED) {
                InputContfxt inputContfxt = gftInputContfxt();
                if (inputContfxt != null && inputContfxt instbndfof sun.bwt.im.InputContfxt) {
                    ((sun.bwt.im.InputContfxt)inputContfxt).disbblfNbtivfIM();
                }
            }
        }


        /*
         * 5. Prf-prodfss bny spfdibl fvfnts bfforf dflivfry
         */
        switdi(id) {
            // Hbndling of tif PAINT bnd UPDATE fvfnts is now donf in tif
            // pffr's ibndlfEvfnt() mftiod so tif bbdkground dbn bf dlfbrfd
            // sflfdtivfly for non-nbtivf domponfnts on Windows only.
            // - Frfd.Edks@Eng.sun.dom, 5-8-98

          dbsf KfyEvfnt.KEY_PRESSED:
          dbsf KfyEvfnt.KEY_RELEASED:
              Contbinfr p = (Contbinfr)((tiis instbndfof Contbinfr) ? tiis : pbrfnt);
              if (p != null) {
                  p.prfProdfssKfyEvfnt((KfyEvfnt)f);
                  if (f.isConsumfd()) {
                        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                            fodusLog.finfst("Prf-prodfss donsumfd fvfnt");
                        }
                      rfturn;
                  }
              }
              brfbk;

          dffbult:
              brfbk;
        }

        /*
         * 6. Dflivfr fvfnt for normbl prodfssing
         */
        if (nfwEvfntsOnly) {
            // Filtfring nffds to rfblly bf movfd to ibppfn bt b lowfr
            // lfvfl in ordfr to gft mbximum pfrformbndf gbin;  it is
            // ifrf tfmporbrily to fnsurf tif API spfd is ionorfd.
            //
            if (fvfntEnbblfd(f)) {
                prodfssEvfnt(f);
            }
        } flsf if (id == MousfEvfnt.MOUSE_WHEEL) {
            // nfwEvfntsOnly will bf fblsf for b listfnfrlfss SdrollPbnf, but
            // MousfWifflEvfnts still nffd to bf dispbtdifd to it so sdrolling
            // dbn bf donf.
            butoProdfssMousfWiffl((MousfWifflEvfnt)f);
        } flsf if (!(f instbndfof MousfEvfnt && !postsOldMousfEvfnts())) {
            //
            // bbdkwbrd dompbtibility
            //
            Evfnt oldf = f.donvfrtToOld();
            if (oldf != null) {
                int kfy = oldf.kfy;
                int modififrs = oldf.modififrs;

                postEvfnt(oldf);
                if (oldf.isConsumfd()) {
                    f.donsumf();
                }
                // if tbrgft dibngfd kfy or modififr vblufs, dopy tifm
                // bbdk to originbl fvfnt
                //
                switdi(oldf.id) {
                  dbsf Evfnt.KEY_PRESS:
                  dbsf Evfnt.KEY_RELEASE:
                  dbsf Evfnt.KEY_ACTION:
                  dbsf Evfnt.KEY_ACTION_RELEASE:
                      if (oldf.kfy != kfy) {
                          ((KfyEvfnt)f).sftKfyCibr(oldf.gftKfyEvfntCibr());
                      }
                      if (oldf.modififrs != modififrs) {
                          ((KfyEvfnt)f).sftModififrs(oldf.modififrs);
                      }
                      brfbk;
                  dffbult:
                      brfbk;
                }
            }
        }

        /*
         * 9. Allow tif pffr to prodfss tif fvfnt.
         * Exdfpt KfyEvfnts, tify will bf prodfssfd by pffr bftfr
         * bll KfyEvfntPostProdfssors
         * (sff DffbultKfybobrdFodusMbnbgfr.dispbtdiKfyEvfnt())
         */
        if (!(f instbndfof KfyEvfnt)) {
            ComponfntPffr tpffr = pffr;
            if (f instbndfof FodusEvfnt && (tpffr == null || tpffr instbndfof LigitwfigitPffr)) {
                // if fodus ownfr is ligitwfigit tifn its nbtivf dontbinfr
                // prodfssfs fvfnt
                Componfnt sourdf = (Componfnt)f.gftSourdf();
                if (sourdf != null) {
                    Contbinfr tbrgft = sourdf.gftNbtivfContbinfr();
                    if (tbrgft != null) {
                        tpffr = tbrgft.gftPffr();
                    }
                }
            }
            if (tpffr != null) {
                tpffr.ibndlfEvfnt(f);
            }
        }
    } // dispbtdiEvfntImpl()

    /*
     * If nfwEvfntsOnly is fblsf, mftiod is dbllfd so tibt SdrollPbnf dbn
     * ovfrridf it bnd ibndlf dommon-dbsf mousf wiffl sdrolling.  NOP
     * for Componfnt.
     */
    void butoProdfssMousfWiffl(MousfWifflEvfnt f) {}

    /*
     * Dispbtdi givfn MousfWifflEvfnt to tif first bndfstor for wiidi
     * MousfWifflEvfnts brf fnbblfd.
     *
     * Rfturns wiftifr or not fvfnt wbs dispbtdifd to bn bndfstor
     */
    boolfbn dispbtdiMousfWifflToAndfstor(MousfWifflEvfnt f) {
        int nfwX, nfwY;
        nfwX = f.gftX() + gftX(); // Coordinbtfs tbkf into bddount bt lfbst
        nfwY = f.gftY() + gftY(); // tif dursor's position rflbtivf to tiis
                                  // Componfnt (f.gftX()), bnd tiis Componfnt's
                                  // position rflbtivf to its pbrfnt.
        MousfWifflEvfnt nfwMWE;

        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fvfntLog.finfst("dispbtdiMousfWifflToAndfstor");
            fvfntLog.finfst("orig fvfnt srd is of " + f.gftSourdf().gftClbss());
        }

        /* pbrfnt fifld for Window rfffrs to tif owning Window.
         * MousfWifflEvfnts siould NOT bf propbgbtfd into owning Windows
         */
        syndironizfd (gftTrffLodk()) {
            Contbinfr bnd = gftPbrfnt();
            wiilf (bnd != null && !bnd.fvfntEnbblfd(f)) {
                // fix doordinbtfs to bf rflbtivf to nfw fvfnt sourdf
                nfwX += bnd.gftX();
                nfwY += bnd.gftY();

                if (!(bnd instbndfof Window)) {
                    bnd = bnd.gftPbrfnt();
                }
                flsf {
                    brfbk;
                }
            }

            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fvfntLog.finfst("nfw fvfnt srd is " + bnd.gftClbss());
            }

            if (bnd != null && bnd.fvfntEnbblfd(f)) {
                // Cibngf fvfnt to bf from nfw sourdf, witi nfw x,y
                // For now, just drfbtf b nfw fvfnt - yudky

                nfwMWE = nfw MousfWifflEvfnt(bnd, // nfw sourdf
                                             f.gftID(),
                                             f.gftWifn(),
                                             f.gftModififrs(),
                                             nfwX, // x rflbtivf to nfw sourdf
                                             nfwY, // y rflbtivf to nfw sourdf
                                             f.gftXOnSdrffn(),
                                             f.gftYOnSdrffn(),
                                             f.gftClidkCount(),
                                             f.isPopupTriggfr(),
                                             f.gftSdrollTypf(),
                                             f.gftSdrollAmount(),
                                             f.gftWifflRotbtion(),
                                             f.gftPrfdisfWifflRotbtion());
                ((AWTEvfnt)f).dopyPrivbtfDbtbInto(nfwMWE);
                // Wifn dispbtdiing b wiffl fvfnt to
                // bndfstor, tifrf is no nffd trying to find dfsdfndbnt
                // ligitwfigits to dispbtdi fvfnt to.
                // If wf dispbtdi tif fvfnt to toplfvfl bndfstor,
                // tiis dould fndolsf tif loop: 6480024.
                bnd.dispbtdiEvfntToSflf(nfwMWE);
                if (nfwMWE.isConsumfd()) {
                    f.donsumf();
                }
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    boolfbn brfInputMftiodsEnbblfd() {
        // in 1.2, wf bssumf input mftiod support is rfquirfd for bll
        // domponfnts tibt ibndlf kfy fvfnts, but domponfnts dbn turn off
        // input mftiods by dblling fnbblfInputMftiods(fblsf).
        rfturn ((fvfntMbsk & AWTEvfnt.INPUT_METHODS_ENABLED_MASK) != 0) &&
            ((fvfntMbsk & AWTEvfnt.KEY_EVENT_MASK) != 0 || kfyListfnfr != null);
    }

    // REMIND: rfmovf wifn filtfring is ibndlfd bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        rfturn fvfntTypfEnbblfd(f.id);
    }

    boolfbn fvfntTypfEnbblfd(int typf) {
        switdi(typf) {
          dbsf ComponfntEvfnt.COMPONENT_MOVED:
          dbsf ComponfntEvfnt.COMPONENT_RESIZED:
          dbsf ComponfntEvfnt.COMPONENT_SHOWN:
          dbsf ComponfntEvfnt.COMPONENT_HIDDEN:
              if ((fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0 ||
                  domponfntListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf FodusEvfnt.FOCUS_GAINED:
          dbsf FodusEvfnt.FOCUS_LOST:
              if ((fvfntMbsk & AWTEvfnt.FOCUS_EVENT_MASK) != 0 ||
                  fodusListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf KfyEvfnt.KEY_PRESSED:
          dbsf KfyEvfnt.KEY_RELEASED:
          dbsf KfyEvfnt.KEY_TYPED:
              if ((fvfntMbsk & AWTEvfnt.KEY_EVENT_MASK) != 0 ||
                  kfyListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf MousfEvfnt.MOUSE_PRESSED:
          dbsf MousfEvfnt.MOUSE_RELEASED:
          dbsf MousfEvfnt.MOUSE_ENTERED:
          dbsf MousfEvfnt.MOUSE_EXITED:
          dbsf MousfEvfnt.MOUSE_CLICKED:
              if ((fvfntMbsk & AWTEvfnt.MOUSE_EVENT_MASK) != 0 ||
                  mousfListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf MousfEvfnt.MOUSE_MOVED:
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              if ((fvfntMbsk & AWTEvfnt.MOUSE_MOTION_EVENT_MASK) != 0 ||
                  mousfMotionListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf MousfEvfnt.MOUSE_WHEEL:
              if ((fvfntMbsk & AWTEvfnt.MOUSE_WHEEL_EVENT_MASK) != 0 ||
                  mousfWifflListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED:
          dbsf InputMftiodEvfnt.CARET_POSITION_CHANGED:
              if ((fvfntMbsk & AWTEvfnt.INPUT_METHOD_EVENT_MASK) != 0 ||
                  inputMftiodListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf HifrbrdiyEvfnt.HIERARCHY_CHANGED:
              if ((fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 ||
                  iifrbrdiyListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf HifrbrdiyEvfnt.ANCESTOR_MOVED:
          dbsf HifrbrdiyEvfnt.ANCESTOR_RESIZED:
              if ((fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0 ||
                  iifrbrdiyBoundsListfnfr != null) {
                  rfturn truf;
              }
              brfbk;
          dbsf AdtionEvfnt.ACTION_PERFORMED:
              if ((fvfntMbsk & AWTEvfnt.ACTION_EVENT_MASK) != 0) {
                  rfturn truf;
              }
              brfbk;
          dbsf TfxtEvfnt.TEXT_VALUE_CHANGED:
              if ((fvfntMbsk & AWTEvfnt.TEXT_EVENT_MASK) != 0) {
                  rfturn truf;
              }
              brfbk;
          dbsf ItfmEvfnt.ITEM_STATE_CHANGED:
              if ((fvfntMbsk & AWTEvfnt.ITEM_EVENT_MASK) != 0) {
                  rfturn truf;
              }
              brfbk;
          dbsf AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED:
              if ((fvfntMbsk & AWTEvfnt.ADJUSTMENT_EVENT_MASK) != 0) {
                  rfturn truf;
              }
              brfbk;
          dffbult:
              brfbk;
        }
        //
        // Alwbys pbss on fvfnts dffinfd by fxtfrnbl progrbms.
        //
        if (typf > AWTEvfnt.RESERVED_ID_MAX) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by dispbtdiEvfnt(AWTEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn postEvfnt(Evfnt f) {
        ComponfntPffr pffr = tiis.pffr;

        if (ibndlfEvfnt(f)) {
            f.donsumf();
            rfturn truf;
        }

        Componfnt pbrfnt = tiis.pbrfnt;
        int fvfntx = f.x;
        int fvfnty = f.y;
        if (pbrfnt != null) {
            f.trbnslbtf(x, y);
            if (pbrfnt.postEvfnt(f)) {
                f.donsumf();
                rfturn truf;
            }
            // rfstorf doords
            f.x = fvfntx;
            f.y = fvfnty;
        }
        rfturn fblsf;
    }

    // Evfnt sourdf intfrfbdfs

    /**
     * Adds tif spfdififd domponfnt listfnfr to rfdfivf domponfnt fvfnts from
     * tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif domponfnt listfnfr
     * @sff      jbvb.bwt.fvfnt.ComponfntEvfnt
     * @sff      jbvb.bwt.fvfnt.ComponfntListfnfr
     * @sff      #rfmovfComponfntListfnfr
     * @sff      #gftComponfntListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void bddComponfntListfnfr(ComponfntListfnfr l) {
        if (l == null) {
            rfturn;
        }
        domponfntListfnfr = AWTEvfntMultidbstfr.bdd(domponfntListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd domponfnt listfnfr so tibt it no longfr
     * rfdfivfs domponfnt fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     * @pbrbm    l   tif domponfnt listfnfr
     * @sff      jbvb.bwt.fvfnt.ComponfntEvfnt
     * @sff      jbvb.bwt.fvfnt.ComponfntListfnfr
     * @sff      #bddComponfntListfnfr
     * @sff      #gftComponfntListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void rfmovfComponfntListfnfr(ComponfntListfnfr l) {
        if (l == null) {
            rfturn;
        }
        domponfntListfnfr = AWTEvfntMultidbstfr.rfmovf(domponfntListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif domponfnt listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll <dodf>ComponfntListfnfr</dodf>s of tiis domponfnt
     *         or bn fmpty brrby if no domponfnt
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddComponfntListfnfr
     * @sff #rfmovfComponfntListfnfr
     * @sindf 1.4
     */
    publid syndironizfd ComponfntListfnfr[] gftComponfntListfnfrs() {
        rfturn gftListfnfrs(ComponfntListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from
     * tiis domponfnt wifn tiis domponfnt gbins input fodus.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif fodus listfnfr
     * @sff      jbvb.bwt.fvfnt.FodusEvfnt
     * @sff      jbvb.bwt.fvfnt.FodusListfnfr
     * @sff      #rfmovfFodusListfnfr
     * @sff      #gftFodusListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void bddFodusListfnfr(FodusListfnfr l) {
        if (l == null) {
            rfturn;
        }
        fodusListfnfr = AWTEvfntMultidbstfr.bdd(fodusListfnfr, l);
        nfwEvfntsOnly = truf;

        // if tiis is b ligitwfigit domponfnt, fnbblf fodus fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(AWTEvfnt.FOCUS_EVENT_MASK);
        }
    }

    /**
     * Rfmovfs tif spfdififd fodus listfnfr so tibt it no longfr
     * rfdfivfs fodus fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif fodus listfnfr
     * @sff      jbvb.bwt.fvfnt.FodusEvfnt
     * @sff      jbvb.bwt.fvfnt.FodusListfnfr
     * @sff      #bddFodusListfnfr
     * @sff      #gftFodusListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void rfmovfFodusListfnfr(FodusListfnfr l) {
        if (l == null) {
            rfturn;
        }
        fodusListfnfr = AWTEvfntMultidbstfr.rfmovf(fodusListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif fodus listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>FodusListfnfr</dodf>s
     *         or bn fmpty brrby if no domponfnt
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddFodusListfnfr
     * @sff #rfmovfFodusListfnfr
     * @sindf 1.4
     */
    publid syndironizfd FodusListfnfr[] gftFodusListfnfrs() {
        rfturn gftListfnfrs(FodusListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd iifrbrdiy listfnfr to rfdfivf iifrbrdiy dibngfd
     * fvfnts from tiis domponfnt wifn tif iifrbrdiy to wiidi tiis dontbinfr
     * bflongs dibngfs.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif iifrbrdiy listfnfr
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyListfnfr
     * @sff      #rfmovfHifrbrdiyListfnfr
     * @sff      #gftHifrbrdiyListfnfrs
     * @sindf    1.3
     */
    publid void bddHifrbrdiyListfnfr(HifrbrdiyListfnfr l) {
        if (l == null) {
            rfturn;
        }
        boolfbn notifyAndfstors;
        syndironizfd (tiis) {
            notifyAndfstors =
                (iifrbrdiyListfnfr == null &&
                 (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) == 0);
            iifrbrdiyListfnfr = AWTEvfntMultidbstfr.bdd(iifrbrdiyListfnfr, l);
            notifyAndfstors = (notifyAndfstors && iifrbrdiyListfnfr != null);
            nfwEvfntsOnly = truf;
        }
        if (notifyAndfstors) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                                1);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd iifrbrdiy listfnfr so tibt it no longfr
     * rfdfivfs iifrbrdiy dibngfd fvfnts from tiis domponfnt. Tiis mftiod
     * pfrforms no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif iifrbrdiy listfnfr
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyListfnfr
     * @sff      #bddHifrbrdiyListfnfr
     * @sff      #gftHifrbrdiyListfnfrs
     * @sindf    1.3
     */
    publid void rfmovfHifrbrdiyListfnfr(HifrbrdiyListfnfr l) {
        if (l == null) {
            rfturn;
        }
        boolfbn notifyAndfstors;
        syndironizfd (tiis) {
            notifyAndfstors =
                (iifrbrdiyListfnfr != null &&
                 (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) == 0);
            iifrbrdiyListfnfr =
                AWTEvfntMultidbstfr.rfmovf(iifrbrdiyListfnfr, l);
            notifyAndfstors = (notifyAndfstors && iifrbrdiyListfnfr == null);
        }
        if (notifyAndfstors) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                                -1);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif iifrbrdiy listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>HifrbrdiyListfnfr</dodf>s
     *         or bn fmpty brrby if no iifrbrdiy
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddHifrbrdiyListfnfr
     * @sff      #rfmovfHifrbrdiyListfnfr
     * @sindf    1.4
     */
    publid syndironizfd HifrbrdiyListfnfr[] gftHifrbrdiyListfnfrs() {
        rfturn gftListfnfrs(HifrbrdiyListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd iifrbrdiy bounds listfnfr to rfdfivf iifrbrdiy
     * bounds fvfnts from tiis domponfnt wifn tif iifrbrdiy to wiidi tiis
     * dontbinfr bflongs dibngfs.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif iifrbrdiy bounds listfnfr
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyBoundsListfnfr
     * @sff      #rfmovfHifrbrdiyBoundsListfnfr
     * @sff      #gftHifrbrdiyBoundsListfnfrs
     * @sindf    1.3
     */
    publid void bddHifrbrdiyBoundsListfnfr(HifrbrdiyBoundsListfnfr l) {
        if (l == null) {
            rfturn;
        }
        boolfbn notifyAndfstors;
        syndironizfd (tiis) {
            notifyAndfstors =
                (iifrbrdiyBoundsListfnfr == null &&
                 (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            iifrbrdiyBoundsListfnfr =
                AWTEvfntMultidbstfr.bdd(iifrbrdiyBoundsListfnfr, l);
            notifyAndfstors = (notifyAndfstors &&
                               iifrbrdiyBoundsListfnfr != null);
            nfwEvfntsOnly = truf;
        }
        if (notifyAndfstors) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(
                                                AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK, 1);
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd iifrbrdiy bounds listfnfr so tibt it no longfr
     * rfdfivfs iifrbrdiy bounds fvfnts from tiis domponfnt. Tiis mftiod
     * pfrforms no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif iifrbrdiy bounds listfnfr
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff      jbvb.bwt.fvfnt.HifrbrdiyBoundsListfnfr
     * @sff      #bddHifrbrdiyBoundsListfnfr
     * @sff      #gftHifrbrdiyBoundsListfnfrs
     * @sindf    1.3
     */
    publid void rfmovfHifrbrdiyBoundsListfnfr(HifrbrdiyBoundsListfnfr l) {
        if (l == null) {
            rfturn;
        }
        boolfbn notifyAndfstors;
        syndironizfd (tiis) {
            notifyAndfstors =
                (iifrbrdiyBoundsListfnfr != null &&
                 (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            iifrbrdiyBoundsListfnfr =
                AWTEvfntMultidbstfr.rfmovf(iifrbrdiyBoundsListfnfr, l);
            notifyAndfstors = (notifyAndfstors &&
                               iifrbrdiyBoundsListfnfr == null);
        }
        if (notifyAndfstors) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(
                                                AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK, -1);
            }
        }
    }

    // Siould only bf dbllfd wiilf iolding tif trff lodk
    int numListfning(long mbsk) {
        // Onf mbsk or tif otifr, but not nfitifr or boti.
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if ((mbsk != AWTEvfnt.HIERARCHY_EVENT_MASK) &&
                (mbsk != AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK))
            {
                fvfntLog.finf("Assfrtion fbilfd");
            }
        }
        if ((mbsk == AWTEvfnt.HIERARCHY_EVENT_MASK &&
             (iifrbrdiyListfnfr != null ||
              (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0)) ||
            (mbsk == AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK &&
             (iifrbrdiyBoundsListfnfr != null ||
              (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0))) {
            rfturn 1;
        } flsf {
            rfturn 0;
        }
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    int dountHifrbrdiyMfmbfrs() {
        rfturn 1;
    }
    // Siould only bf dbllfd wiilf iolding tif trff lodk
    int drfbtfHifrbrdiyEvfnts(int id, Componfnt dibngfd,
                              Contbinfr dibngfdPbrfnt, long dibngfFlbgs,
                              boolfbn fnbblfdOnToolkit) {
        switdi (id) {
          dbsf HifrbrdiyEvfnt.HIERARCHY_CHANGED:
              if (iifrbrdiyListfnfr != null ||
                  (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 ||
                  fnbblfdOnToolkit) {
                  HifrbrdiyEvfnt f = nfw HifrbrdiyEvfnt(tiis, id, dibngfd,
                                                        dibngfdPbrfnt,
                                                        dibngfFlbgs);
                  dispbtdiEvfnt(f);
                  rfturn 1;
              }
              brfbk;
          dbsf HifrbrdiyEvfnt.ANCESTOR_MOVED:
          dbsf HifrbrdiyEvfnt.ANCESTOR_RESIZED:
              if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                  if (dibngfFlbgs != 0) {
                      fvfntLog.finf("Assfrtion (dibngfFlbgs == 0) fbilfd");
                  }
              }
              if (iifrbrdiyBoundsListfnfr != null ||
                  (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0 ||
                  fnbblfdOnToolkit) {
                  HifrbrdiyEvfnt f = nfw HifrbrdiyEvfnt(tiis, id, dibngfd,
                                                        dibngfdPbrfnt);
                  dispbtdiEvfnt(f);
                  rfturn 1;
              }
              brfbk;
          dffbult:
              // bssfrt fblsf
              if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                  fvfntLog.finf("Tiis dodf must nfvfr bf rfbdifd");
              }
              brfbk;
        }
        rfturn 0;
    }

    /**
     * Rfturns bn brrby of bll tif iifrbrdiy bounds listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>HifrbrdiyBoundsListfnfr</dodf>s
     *         or bn fmpty brrby if no iifrbrdiy bounds
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddHifrbrdiyBoundsListfnfr
     * @sff      #rfmovfHifrbrdiyBoundsListfnfr
     * @sindf    1.4
     */
    publid syndironizfd HifrbrdiyBoundsListfnfr[] gftHifrbrdiyBoundsListfnfrs() {
        rfturn gftListfnfrs(HifrbrdiyBoundsListfnfr.dlbss);
    }

    /*
     * Siould only bf dbllfd wiilf iolding tif trff lodk.
     * It's bddfd only for ovfrriding in jbvb.bwt.Window
     * bfdbusf pbrfnt in Window is ownfr.
     */
    void bdjustListfningCiildrfnOnPbrfnt(long mbsk, int num) {
        if (pbrfnt != null) {
            pbrfnt.bdjustListfningCiildrfn(mbsk, num);
        }
    }

    /**
     * Adds tif spfdififd kfy listfnfr to rfdfivf kfy fvfnts from
     * tiis domponfnt.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif kfy listfnfr.
     * @sff      jbvb.bwt.fvfnt.KfyEvfnt
     * @sff      jbvb.bwt.fvfnt.KfyListfnfr
     * @sff      #rfmovfKfyListfnfr
     * @sff      #gftKfyListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void bddKfyListfnfr(KfyListfnfr l) {
        if (l == null) {
            rfturn;
        }
        kfyListfnfr = AWTEvfntMultidbstfr.bdd(kfyListfnfr, l);
        nfwEvfntsOnly = truf;

        // if tiis is b ligitwfigit domponfnt, fnbblf kfy fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK);
        }
    }

    /**
     * Rfmovfs tif spfdififd kfy listfnfr so tibt it no longfr
     * rfdfivfs kfy fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif kfy listfnfr
     * @sff      jbvb.bwt.fvfnt.KfyEvfnt
     * @sff      jbvb.bwt.fvfnt.KfyListfnfr
     * @sff      #bddKfyListfnfr
     * @sff      #gftKfyListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void rfmovfKfyListfnfr(KfyListfnfr l) {
        if (l == null) {
            rfturn;
        }
        kfyListfnfr = AWTEvfntMultidbstfr.rfmovf(kfyListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif kfy listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>KfyListfnfr</dodf>s
     *         or bn fmpty brrby if no kfy
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddKfyListfnfr
     * @sff      #rfmovfKfyListfnfr
     * @sindf    1.4
     */
    publid syndironizfd KfyListfnfr[] gftKfyListfnfrs() {
        rfturn gftListfnfrs(KfyListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd mousf listfnfr to rfdfivf mousf fvfnts from
     * tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf listfnfr
     * @sff      jbvb.bwt.fvfnt.MousfEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfListfnfr
     * @sff      #rfmovfMousfListfnfr
     * @sff      #gftMousfListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void bddMousfListfnfr(MousfListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfListfnfr = AWTEvfntMultidbstfr.bdd(mousfListfnfr,l);
        nfwEvfntsOnly = truf;

        // if tiis is b ligitwfigit domponfnt, fnbblf mousf fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(AWTEvfnt.MOUSE_EVENT_MASK);
        }
    }

    /**
     * Rfmovfs tif spfdififd mousf listfnfr so tibt it no longfr
     * rfdfivfs mousf fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf listfnfr
     * @sff      jbvb.bwt.fvfnt.MousfEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfListfnfr
     * @sff      #bddMousfListfnfr
     * @sff      #gftMousfListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void rfmovfMousfListfnfr(MousfListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfListfnfr = AWTEvfntMultidbstfr.rfmovf(mousfListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif mousf listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>MousfListfnfr</dodf>s
     *         or bn fmpty brrby if no mousf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddMousfListfnfr
     * @sff      #rfmovfMousfListfnfr
     * @sindf    1.4
     */
    publid syndironizfd MousfListfnfr[] gftMousfListfnfrs() {
        rfturn gftListfnfrs(MousfListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd mousf motion listfnfr to rfdfivf mousf motion
     * fvfnts from tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf motion listfnfr
     * @sff      jbvb.bwt.fvfnt.MousfEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfMotionListfnfr
     * @sff      #rfmovfMousfMotionListfnfr
     * @sff      #gftMousfMotionListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void bddMousfMotionListfnfr(MousfMotionListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfMotionListfnfr = AWTEvfntMultidbstfr.bdd(mousfMotionListfnfr,l);
        nfwEvfntsOnly = truf;

        // if tiis is b ligitwfigit domponfnt, fnbblf mousf fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(AWTEvfnt.MOUSE_MOTION_EVENT_MASK);
        }
    }

    /**
     * Rfmovfs tif spfdififd mousf motion listfnfr so tibt it no longfr
     * rfdfivfs mousf motion fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf motion listfnfr
     * @sff      jbvb.bwt.fvfnt.MousfEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfMotionListfnfr
     * @sff      #bddMousfMotionListfnfr
     * @sff      #gftMousfMotionListfnfrs
     * @sindf    1.1
     */
    publid syndironizfd void rfmovfMousfMotionListfnfr(MousfMotionListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfMotionListfnfr = AWTEvfntMultidbstfr.rfmovf(mousfMotionListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif mousf motion listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>MousfMotionListfnfr</dodf>s
     *         or bn fmpty brrby if no mousf motion
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddMousfMotionListfnfr
     * @sff      #rfmovfMousfMotionListfnfr
     * @sindf    1.4
     */
    publid syndironizfd MousfMotionListfnfr[] gftMousfMotionListfnfrs() {
        rfturn gftListfnfrs(MousfMotionListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd mousf wiffl listfnfr to rfdfivf mousf wiffl fvfnts
     * from tiis domponfnt.  Contbinfrs blso rfdfivf mousf wiffl fvfnts from
     * sub-domponfnts.
     * <p>
     * For informbtion on iow mousf wiffl fvfnts brf dispbtdifd, sff
     * tif dlbss dfsdription for {@link MousfWifflEvfnt}.
     * <p>
     * If l is <dodf>null</dodf>, no fxdfption is tirown bnd no
     * bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf wiffl listfnfr
     * @sff      jbvb.bwt.fvfnt.MousfWifflEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfWifflListfnfr
     * @sff      #rfmovfMousfWifflListfnfr
     * @sff      #gftMousfWifflListfnfrs
     * @sindf    1.4
     */
    publid syndironizfd void bddMousfWifflListfnfr(MousfWifflListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfWifflListfnfr = AWTEvfntMultidbstfr.bdd(mousfWifflListfnfr,l);
        nfwEvfntsOnly = truf;

        // if tiis is b ligitwfigit domponfnt, fnbblf mousf fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(AWTEvfnt.MOUSE_WHEEL_EVENT_MASK);
        }
    }

    /**
     * Rfmovfs tif spfdififd mousf wiffl listfnfr so tibt it no longfr
     * rfdfivfs mousf wiffl fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif mousf wiffl listfnfr.
     * @sff      jbvb.bwt.fvfnt.MousfWifflEvfnt
     * @sff      jbvb.bwt.fvfnt.MousfWifflListfnfr
     * @sff      #bddMousfWifflListfnfr
     * @sff      #gftMousfWifflListfnfrs
     * @sindf    1.4
     */
    publid syndironizfd void rfmovfMousfWifflListfnfr(MousfWifflListfnfr l) {
        if (l == null) {
            rfturn;
        }
        mousfWifflListfnfr = AWTEvfntMultidbstfr.rfmovf(mousfWifflListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif mousf wiffl listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>MousfWifflListfnfr</dodf>s
     *         or bn fmpty brrby if no mousf wiffl
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddMousfWifflListfnfr
     * @sff      #rfmovfMousfWifflListfnfr
     * @sindf    1.4
     */
    publid syndironizfd MousfWifflListfnfr[] gftMousfWifflListfnfrs() {
        rfturn gftListfnfrs(MousfWifflListfnfr.dlbss);
    }

    /**
     * Adds tif spfdififd input mftiod listfnfr to rfdfivf
     * input mftiod fvfnts from tiis domponfnt. A domponfnt will
     * only rfdfivf input mftiod fvfnts from input mftiods
     * if it blso ovfrridfs <dodf>gftInputMftiodRfqufsts</dodf> to rfturn bn
     * <dodf>InputMftiodRfqufsts</dodf> instbndf.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="{@dodRoot}/jbvb/bwt/dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif input mftiod listfnfr
     * @sff      jbvb.bwt.fvfnt.InputMftiodEvfnt
     * @sff      jbvb.bwt.fvfnt.InputMftiodListfnfr
     * @sff      #rfmovfInputMftiodListfnfr
     * @sff      #gftInputMftiodListfnfrs
     * @sff      #gftInputMftiodRfqufsts
     * @sindf    1.2
     */
    publid syndironizfd void bddInputMftiodListfnfr(InputMftiodListfnfr l) {
        if (l == null) {
            rfturn;
        }
        inputMftiodListfnfr = AWTEvfntMultidbstfr.bdd(inputMftiodListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd input mftiod listfnfr so tibt it no longfr
     * rfdfivfs input mftiod fvfnts from tiis domponfnt. Tiis mftiod pfrforms
     * no fundtion, nor dofs it tirow bn fxdfption, if tif listfnfr
     * spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis domponfnt.
     * If listfnfr <dodf>l</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif input mftiod listfnfr
     * @sff      jbvb.bwt.fvfnt.InputMftiodEvfnt
     * @sff      jbvb.bwt.fvfnt.InputMftiodListfnfr
     * @sff      #bddInputMftiodListfnfr
     * @sff      #gftInputMftiodListfnfrs
     * @sindf    1.2
     */
    publid syndironizfd void rfmovfInputMftiodListfnfr(InputMftiodListfnfr l) {
        if (l == null) {
            rfturn;
        }
        inputMftiodListfnfr = AWTEvfntMultidbstfr.rfmovf(inputMftiodListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif input mftiod listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>InputMftiodListfnfr</dodf>s
     *         or bn fmpty brrby if no input mftiod
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddInputMftiodListfnfr
     * @sff      #rfmovfInputMftiodListfnfr
     * @sindf    1.4
     */
    publid syndironizfd InputMftiodListfnfr[] gftInputMftiodListfnfrs() {
        rfturn gftListfnfrs(InputMftiodListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>Componfnt</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>Componfnt</dodf> <dodf>d</dodf>
     * for its mousf listfnfrs witi tif following dodf:
     *
     * <prf>MousfListfnfr[] mls = (MousfListfnfr[])(d.gftListfnfrs(MousfListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis domponfnt,
     *          or bn fmpty brrby if no sudi listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @tirows NullPointfrExdfption if {@dodf listfnfrTypf} is {@dodf null}
     * @sff #gftComponfntListfnfrs
     * @sff #gftFodusListfnfrs
     * @sff #gftHifrbrdiyListfnfrs
     * @sff #gftHifrbrdiyBoundsListfnfrs
     * @sff #gftKfyListfnfrs
     * @sff #gftMousfListfnfrs
     * @sff #gftMousfMotionListfnfrs
     * @sff #gftMousfWifflListfnfrs
     * @sff #gftInputMftiodListfnfrs
     * @sff #gftPropfrtyCibngfListfnfrs
     *
     * @sindf 1.3
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == ComponfntListfnfr.dlbss) {
            l = domponfntListfnfr;
        } flsf if (listfnfrTypf == FodusListfnfr.dlbss) {
            l = fodusListfnfr;
        } flsf if (listfnfrTypf == HifrbrdiyListfnfr.dlbss) {
            l = iifrbrdiyListfnfr;
        } flsf if (listfnfrTypf == HifrbrdiyBoundsListfnfr.dlbss) {
            l = iifrbrdiyBoundsListfnfr;
        } flsf if (listfnfrTypf == KfyListfnfr.dlbss) {
            l = kfyListfnfr;
        } flsf if (listfnfrTypf == MousfListfnfr.dlbss) {
            l = mousfListfnfr;
        } flsf if (listfnfrTypf == MousfMotionListfnfr.dlbss) {
            l = mousfMotionListfnfr;
        } flsf if (listfnfrTypf == MousfWifflListfnfr.dlbss) {
            l = mousfWifflListfnfr;
        } flsf if (listfnfrTypf == InputMftiodListfnfr.dlbss) {
            l = inputMftiodListfnfr;
        } flsf if (listfnfrTypf == PropfrtyCibngfListfnfr.dlbss) {
            rfturn (T[])gftPropfrtyCibngfListfnfrs();
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    /**
     * Gfts tif input mftiod rfqufst ibndlfr wiidi supports
     * rfqufsts from input mftiods for tiis domponfnt. A domponfnt
     * tibt supports on-tif-spot tfxt input must ovfrridf tiis
     * mftiod to rfturn bn <dodf>InputMftiodRfqufsts</dodf> instbndf.
     * At tif sbmf timf, it blso ibs to ibndlf input mftiod fvfnts.
     *
     * @rfturn tif input mftiod rfqufst ibndlfr for tiis domponfnt,
     *          <dodf>null</dodf> by dffbult
     * @sff #bddInputMftiodListfnfr
     * @sindf 1.2
     */
    publid InputMftiodRfqufsts gftInputMftiodRfqufsts() {
        rfturn null;
    }

    /**
     * Gfts tif input dontfxt usfd by tiis domponfnt for ibndling
     * tif dommunidbtion witi input mftiods wifn tfxt is fntfrfd
     * in tiis domponfnt. By dffbult, tif input dontfxt usfd for
     * tif pbrfnt domponfnt is rfturnfd. Componfnts mby
     * ovfrridf tiis to rfturn b privbtf input dontfxt.
     *
     * @rfturn tif input dontfxt usfd by tiis domponfnt;
     *          <dodf>null</dodf> if no dontfxt dbn bf dftfrminfd
     * @sindf 1.2
     */
    publid InputContfxt gftInputContfxt() {
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt == null) {
            rfturn null;
        } flsf {
            rfturn pbrfnt.gftInputContfxt();
        }
    }

    /**
     * Enbblfs tif fvfnts dffinfd by tif spfdififd fvfnt mbsk pbrbmftfr
     * to bf dflivfrfd to tiis domponfnt.
     * <p>
     * Evfnt typfs brf butombtidblly fnbblfd wifn b listfnfr for
     * tibt fvfnt typf is bddfd to tif domponfnt.
     * <p>
     * Tiis mftiod only nffds to bf invokfd by subdlbssfs of
     * <dodf>Componfnt</dodf> wiidi dfsirf to ibvf tif spfdififd fvfnt
     * typfs dflivfrfd to <dodf>prodfssEvfnt</dodf> rfgbrdlfss of wiftifr
     * or not b listfnfr is rfgistfrfd.
     * @pbrbm      fvfntsToEnbblf   tif fvfnt mbsk dffining tif fvfnt typfs
     * @sff        #prodfssEvfnt
     * @sff        #disbblfEvfnts
     * @sff        AWTEvfnt
     * @sindf      1.1
     */
    protfdtfd finbl void fnbblfEvfnts(long fvfntsToEnbblf) {
        long notifyAndfstors = 0;
        syndironizfd (tiis) {
            if ((fvfntsToEnbblf & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 &&
                iifrbrdiyListfnfr == null &&
                (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) == 0) {
                notifyAndfstors |= AWTEvfnt.HIERARCHY_EVENT_MASK;
            }
            if ((fvfntsToEnbblf & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                iifrbrdiyBoundsListfnfr == null &&
                (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) == 0) {
                notifyAndfstors |= AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK;
            }
            fvfntMbsk |= fvfntsToEnbblf;
            nfwEvfntsOnly = truf;
        }

        // if tiis is b ligitwfigit domponfnt, fnbblf mousf fvfnts
        // in tif nbtivf dontbinfr.
        if (pffr instbndfof LigitwfigitPffr) {
            pbrfnt.proxyEnbblfEvfnts(fvfntMbsk);
        }
        if (notifyAndfstors != 0) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(notifyAndfstors, 1);
            }
        }
    }

    /**
     * Disbblfs tif fvfnts dffinfd by tif spfdififd fvfnt mbsk pbrbmftfr
     * from bfing dflivfrfd to tiis domponfnt.
     * @pbrbm      fvfntsToDisbblf   tif fvfnt mbsk dffining tif fvfnt typfs
     * @sff        #fnbblfEvfnts
     * @sindf      1.1
     */
    protfdtfd finbl void disbblfEvfnts(long fvfntsToDisbblf) {
        long notifyAndfstors = 0;
        syndironizfd (tiis) {
            if ((fvfntsToDisbblf & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 &&
                iifrbrdiyListfnfr == null &&
                (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0) {
                notifyAndfstors |= AWTEvfnt.HIERARCHY_EVENT_MASK;
            }
            if ((fvfntsToDisbblf & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK)!=0 &&
                iifrbrdiyBoundsListfnfr == null &&
                (fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0) {
                notifyAndfstors |= AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK;
            }
            fvfntMbsk &= ~fvfntsToDisbblf;
        }
        if (notifyAndfstors != 0) {
            syndironizfd (gftTrffLodk()) {
                bdjustListfningCiildrfnOnPbrfnt(notifyAndfstors, -1);
            }
        }
    }

    trbnsifnt sun.bwt.EvfntQufufItfm[] fvfntCbdif;

    /**
     * @sff #isCoblfsdingEnbblfd
     * @sff #difdkCoblfsding
     */
    trbnsifnt privbtf boolfbn doblfsdingEnbblfd = difdkCoblfsding();

    /**
     * Wfbk mbp of known doblfsdfEvfnt ovfrridfrs.
     * Vbluf indidbtfs wiftifr ovfrridfn.
     * Bootstrbp dlbssfs brf not indludfd.
     */
    privbtf stbtid finbl Mbp<Clbss<?>, Boolfbn> doblfsdfMbp =
        nfw jbvb.util.WfbkHbsiMbp<Clbss<?>, Boolfbn>();

    /**
     * Indidbtfs wiftifr tiis dlbss ovfrridfs doblfsdfEvfnts.
     * It is bssumfd tibt bll dlbssfs tibt brf lobdfd from tif bootstrbp
     *   do not.
     * Tif boostrbp dlbss lobdfr is bssumfd to bf rfprfsfntfd by null.
     * Wf do not difdk tibt tif mftiod rfblly ovfrridfs
     *   (it migit bf stbtid, privbtf or pbdkbgf privbtf).
     */
     privbtf boolfbn difdkCoblfsding() {
         if (gftClbss().gftClbssLobdfr()==null) {
             rfturn fblsf;
         }
         finbl Clbss<? fxtfnds Componfnt> dlbzz = gftClbss();
         syndironizfd (doblfsdfMbp) {
             // Cifdk dbdif.
             Boolfbn vbluf = doblfsdfMbp.gft(dlbzz);
             if (vbluf != null) {
                 rfturn vbluf;
             }

             // Nffd to difdk non-bootstrbps.
             Boolfbn fnbblfd = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                 nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
                     publid Boolfbn run() {
                         rfturn isCoblfsdfEvfntsOvfrridfn(dlbzz);
                     }
                 }
                 );
             doblfsdfMbp.put(dlbzz, fnbblfd);
             rfturn fnbblfd;
         }
     }

    /**
     * Pbrbmftfr typfs of doblfsdfEvfnts(AWTEvfnt,AWTEVfnt).
     */
    privbtf stbtid finbl Clbss<?>[] doblfsdfEvfntsPbrbms = {
        AWTEvfnt.dlbss, AWTEvfnt.dlbss
    };

    /**
     * Indidbtfs wiftifr b dlbss or its supfrdlbssfs ovfrridf doblfsdfEvfnts.
     * Must bf dbllfd witi lodk on doblfsdfMbp bnd privilfgfd.
     * @sff difdkCoblsfding
     */
    privbtf stbtid boolfbn isCoblfsdfEvfntsOvfrridfn(Clbss<?> dlbzz) {
        bssfrt Tirfbd.ioldsLodk(doblfsdfMbp);

        // First difdk supfrdlbss - wf mby not nffd to botifr oursflvfs.
        Clbss<?> supfrdlbss = dlbzz.gftSupfrdlbss();
        if (supfrdlbss == null) {
            // Only oddurs on implfmfntbtions tibt
            //   do not usf null to rfprfsfnt tif bootsrbp dlbss lobdfr.
            rfturn fblsf;
        }
        if (supfrdlbss.gftClbssLobdfr() != null) {
            Boolfbn vbluf = doblfsdfMbp.gft(supfrdlbss);
            if (vbluf == null) {
                // Not donf blrfbdy - rfdursf.
                if (isCoblfsdfEvfntsOvfrridfn(supfrdlbss)) {
                    doblfsdfMbp.put(supfrdlbss, truf);
                    rfturn truf;
                }
            } flsf if (vbluf) {
                rfturn truf;
            }
        }

        try {
            // Tirows if not ovfrridfn.
            dlbzz.gftDfdlbrfdMftiod(
                "doblfsdfEvfnts", doblfsdfEvfntsPbrbms
                );
            rfturn truf;
        } dbtdi (NoSudiMftiodExdfption f) {
            // Not prfsfnt in tiis dlbss.
            rfturn fblsf;
        }
    }

    /**
     * Indidbtfs wiftifr doblfsdfEvfnts mby do somftiing.
     */
    finbl boolfbn isCoblfsdingEnbblfd() {
        rfturn doblfsdingEnbblfd;
     }


    /**
     * Potfntiblly doblfsdf bn fvfnt bfing postfd witi bn fxisting
     * fvfnt.  Tiis mftiod is dbllfd by <dodf>EvfntQufuf.postEvfnt</dodf>
     * if bn fvfnt witi tif sbmf ID bs tif fvfnt to bf postfd is found in
     * tif qufuf (boti fvfnts must ibvf tiis domponfnt bs tifir sourdf).
     * Tiis mftiod fitifr rfturns b doblfsdfd fvfnt wiidi rfplbdfs
     * tif fxisting fvfnt (bnd tif nfw fvfnt is tifn disdbrdfd), or
     * <dodf>null</dodf> to indidbtf tibt no dombining siould bf donf
     * (bdd tif sfdond fvfnt to tif fnd of tif qufuf).  Eitifr fvfnt
     * pbrbmftfr mby bf modififd bnd rfturnfd, bs tif otifr onf is disdbrdfd
     * unlfss <dodf>null</dodf> is rfturnfd.
     * <p>
     * Tiis implfmfntbtion of <dodf>doblfsdfEvfnts</dodf> doblfsdfs
     * two fvfnt typfs: mousf movf (bnd drbg) fvfnts,
     * bnd pbint (bnd updbtf) fvfnts.
     * For mousf movf fvfnts tif lbst fvfnt is blwbys rfturnfd, dbusing
     * intfrmfdibtf movfs to bf disdbrdfd.  For pbint fvfnts, tif nfw
     * fvfnt is doblfsdfd into b domplfx <dodf>RfpbintArfb</dodf> in tif pffr.
     * Tif nfw <dodf>AWTEvfnt</dodf> is blwbys rfturnfd.
     *
     * @pbrbm  fxistingEvfnt  tif fvfnt blrfbdy on tif <dodf>EvfntQufuf</dodf>
     * @pbrbm  nfwEvfnt       tif fvfnt bfing postfd to tif
     *          <dodf>EvfntQufuf</dodf>
     * @rfturn b doblfsdfd fvfnt, or <dodf>null</dodf> indidbting tibt no
     *          doblfsding wbs donf
     */
    protfdtfd AWTEvfnt doblfsdfEvfnts(AWTEvfnt fxistingEvfnt,
                                      AWTEvfnt nfwEvfnt) {
        rfturn null;
    }

    /**
     * Prodfssfs fvfnts oddurring on tiis domponfnt. By dffbult tiis
     * mftiod dblls tif bppropribtf
     * <dodf>prodfss&lt;fvfnt&nbsp;typf&gt;Evfnt</dodf>
     * mftiod for tif givfn dlbss of fvfnt.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm     f tif fvfnt
     * @sff       #prodfssComponfntEvfnt
     * @sff       #prodfssFodusEvfnt
     * @sff       #prodfssKfyEvfnt
     * @sff       #prodfssMousfEvfnt
     * @sff       #prodfssMousfMotionEvfnt
     * @sff       #prodfssInputMftiodEvfnt
     * @sff       #prodfssHifrbrdiyEvfnt
     * @sff       #prodfssMousfWifflEvfnt
     * @sindf     1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof FodusEvfnt) {
            prodfssFodusEvfnt((FodusEvfnt)f);

        } flsf if (f instbndfof MousfEvfnt) {
            switdi(f.gftID()) {
              dbsf MousfEvfnt.MOUSE_PRESSED:
              dbsf MousfEvfnt.MOUSE_RELEASED:
              dbsf MousfEvfnt.MOUSE_CLICKED:
              dbsf MousfEvfnt.MOUSE_ENTERED:
              dbsf MousfEvfnt.MOUSE_EXITED:
                  prodfssMousfEvfnt((MousfEvfnt)f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_MOVED:
              dbsf MousfEvfnt.MOUSE_DRAGGED:
                  prodfssMousfMotionEvfnt((MousfEvfnt)f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_WHEEL:
                  prodfssMousfWifflEvfnt((MousfWifflEvfnt)f);
                  brfbk;
            }

        } flsf if (f instbndfof KfyEvfnt) {
            prodfssKfyEvfnt((KfyEvfnt)f);

        } flsf if (f instbndfof ComponfntEvfnt) {
            prodfssComponfntEvfnt((ComponfntEvfnt)f);
        } flsf if (f instbndfof InputMftiodEvfnt) {
            prodfssInputMftiodEvfnt((InputMftiodEvfnt)f);
        } flsf if (f instbndfof HifrbrdiyEvfnt) {
            switdi (f.gftID()) {
              dbsf HifrbrdiyEvfnt.HIERARCHY_CHANGED:
                  prodfssHifrbrdiyEvfnt((HifrbrdiyEvfnt)f);
                  brfbk;
              dbsf HifrbrdiyEvfnt.ANCESTOR_MOVED:
              dbsf HifrbrdiyEvfnt.ANCESTOR_RESIZED:
                  prodfssHifrbrdiyBoundsEvfnt((HifrbrdiyEvfnt)f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs domponfnt fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>ComponfntListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss domponfnt fvfnts brf
     * fnbblfd for tiis domponfnt. Componfnt fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>ComponfntListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddComponfntListfnfr</dodf>.
     * <li>Componfnt fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif domponfnt fvfnt
     * @sff         jbvb.bwt.fvfnt.ComponfntEvfnt
     * @sff         jbvb.bwt.fvfnt.ComponfntListfnfr
     * @sff         #bddComponfntListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssComponfntEvfnt(ComponfntEvfnt f) {
        ComponfntListfnfr listfnfr = domponfntListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf ComponfntEvfnt.COMPONENT_RESIZED:
                  listfnfr.domponfntRfsizfd(f);
                  brfbk;
              dbsf ComponfntEvfnt.COMPONENT_MOVED:
                  listfnfr.domponfntMovfd(f);
                  brfbk;
              dbsf ComponfntEvfnt.COMPONENT_SHOWN:
                  listfnfr.domponfntSiown(f);
                  brfbk;
              dbsf ComponfntEvfnt.COMPONENT_HIDDEN:
                  listfnfr.domponfntHiddfn(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs fodus fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>FodusListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss fodus fvfnts brf
     * fnbblfd for tiis domponfnt. Fodus fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>FodusListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddFodusListfnfr</dodf>.
     * <li>Fodus fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>
     * If fodus fvfnts brf fnbblfd for b <dodf>Componfnt</dodf>,
     * tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> dftfrminfs
     * wiftifr or not b fodus fvfnt siould bf dispbtdifd to
     * rfgistfrfd <dodf>FodusListfnfr</dodf> objfdts.  If tif
     * fvfnts brf to bf dispbtdifd, tif <dodf>KfybobrdFodusMbnbgfr</dodf>
     * dblls tif <dodf>Componfnt</dodf>'s <dodf>dispbtdiEvfnt</dodf>
     * mftiod, wiidi rfsults in b dbll to tif <dodf>Componfnt</dodf>'s
     * <dodf>prodfssFodusEvfnt</dodf> mftiod.
     * <p>
     * If fodus fvfnts brf fnbblfd for b <dodf>Componfnt</dodf>, dblling
     * tif <dodf>Componfnt</dodf>'s <dodf>dispbtdiEvfnt</dodf> mftiod
     * witi b <dodf>FodusEvfnt</dodf> bs tif brgumfnt will rfsult in b
     * dbll to tif <dodf>Componfnt</dodf>'s <dodf>prodfssFodusEvfnt</dodf>
     * mftiod rfgbrdlfss of tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf>.
     *
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif fodus fvfnt
     * @sff         jbvb.bwt.fvfnt.FodusEvfnt
     * @sff         jbvb.bwt.fvfnt.FodusListfnfr
     * @sff         jbvb.bwt.KfybobrdFodusMbnbgfr
     * @sff         #bddFodusListfnfr
     * @sff         #fnbblfEvfnts
     * @sff         #dispbtdiEvfnt
     * @sindf       1.1
     */
    protfdtfd void prodfssFodusEvfnt(FodusEvfnt f) {
        FodusListfnfr listfnfr = fodusListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf FodusEvfnt.FOCUS_GAINED:
                  listfnfr.fodusGbinfd(f);
                  brfbk;
              dbsf FodusEvfnt.FOCUS_LOST:
                  listfnfr.fodusLost(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs kfy fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>KfyListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss kfy fvfnts brf
     * fnbblfd for tiis domponfnt. Kfy fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>KfyListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddKfyListfnfr</dodf>.
     * <li>Kfy fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     *
     * <p>
     * If kfy fvfnts brf fnbblfd for b <dodf>Componfnt</dodf>,
     * tif durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> dftfrminfs
     * wiftifr or not b kfy fvfnt siould bf dispbtdifd to
     * rfgistfrfd <dodf>KfyListfnfr</dodf> objfdts.  Tif
     * <dodf>DffbultKfybobrdFodusMbnbgfr</dodf> will not dispbtdi
     * kfy fvfnts to b <dodf>Componfnt</dodf> tibt is not tif fodus
     * ownfr or is not siowing.
     * <p>
     * As of J2SE 1.4, <dodf>KfyEvfnt</dodf>s brf rfdirfdtfd to
     * tif fodus ownfr. Plfbsf sff tif
     * <b irff="dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
     * for furtifr informbtion.
     * <p>
     * Cblling b <dodf>Componfnt</dodf>'s <dodf>dispbtdiEvfnt</dodf>
     * mftiod witi b <dodf>KfyEvfnt</dodf> bs tif brgumfnt will
     * rfsult in b dbll to tif <dodf>Componfnt</dodf>'s
     * <dodf>prodfssKfyEvfnt</dodf> mftiod rfgbrdlfss of tif
     * durrfnt <dodf>KfybobrdFodusMbnbgfr</dodf> bs long bs tif
     * domponfnt is siowing, fodusfd, bnd fnbblfd, bnd kfy fvfnts
     * brf fnbblfd on it.
     * <p>If tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif kfy fvfnt
     * @sff         jbvb.bwt.fvfnt.KfyEvfnt
     * @sff         jbvb.bwt.fvfnt.KfyListfnfr
     * @sff         jbvb.bwt.KfybobrdFodusMbnbgfr
     * @sff         jbvb.bwt.DffbultKfybobrdFodusMbnbgfr
     * @sff         #prodfssEvfnt
     * @sff         #dispbtdiEvfnt
     * @sff         #bddKfyListfnfr
     * @sff         #fnbblfEvfnts
     * @sff         #isSiowing
     * @sindf       1.1
     */
    protfdtfd void prodfssKfyEvfnt(KfyEvfnt f) {
        KfyListfnfr listfnfr = kfyListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf KfyEvfnt.KEY_TYPED:
                  listfnfr.kfyTypfd(f);
                  brfbk;
              dbsf KfyEvfnt.KEY_PRESSED:
                  listfnfr.kfyPrfssfd(f);
                  brfbk;
              dbsf KfyEvfnt.KEY_RELEASED:
                  listfnfr.kfyRflfbsfd(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs mousf fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>MousfListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss mousf fvfnts brf
     * fnbblfd for tiis domponfnt. Mousf fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>MousfListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddMousfListfnfr</dodf>.
     * <li>Mousf fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif mousf fvfnt
     * @sff         jbvb.bwt.fvfnt.MousfEvfnt
     * @sff         jbvb.bwt.fvfnt.MousfListfnfr
     * @sff         #bddMousfListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssMousfEvfnt(MousfEvfnt f) {
        MousfListfnfr listfnfr = mousfListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf MousfEvfnt.MOUSE_PRESSED:
                  listfnfr.mousfPrfssfd(f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_RELEASED:
                  listfnfr.mousfRflfbsfd(f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_CLICKED:
                  listfnfr.mousfClidkfd(f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_EXITED:
                  listfnfr.mousfExitfd(f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_ENTERED:
                  listfnfr.mousfEntfrfd(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs mousf motion fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>MousfMotionListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss mousf motion fvfnts brf
     * fnbblfd for tiis domponfnt. Mousf motion fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>MousfMotionListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddMousfMotionListfnfr</dodf>.
     * <li>Mousf motion fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif mousf motion fvfnt
     * @sff         jbvb.bwt.fvfnt.MousfEvfnt
     * @sff         jbvb.bwt.fvfnt.MousfMotionListfnfr
     * @sff         #bddMousfMotionListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssMousfMotionEvfnt(MousfEvfnt f) {
        MousfMotionListfnfr listfnfr = mousfMotionListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf MousfEvfnt.MOUSE_MOVED:
                  listfnfr.mousfMovfd(f);
                  brfbk;
              dbsf MousfEvfnt.MOUSE_DRAGGED:
                  listfnfr.mousfDrbggfd(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs mousf wiffl fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>MousfWifflListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss mousf wiffl fvfnts brf
     * fnbblfd for tiis domponfnt. Mousf wiffl fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>A <dodf>MousfWifflListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddMousfWifflListfnfr</dodf>.
     * <li>Mousf wiffl fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>
     * For informbtion on iow mousf wiffl fvfnts brf dispbtdifd, sff
     * tif dlbss dfsdription for {@link MousfWifflEvfnt}.
     * <p>
     * Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif mousf wiffl fvfnt
     * @sff         jbvb.bwt.fvfnt.MousfWifflEvfnt
     * @sff         jbvb.bwt.fvfnt.MousfWifflListfnfr
     * @sff         #bddMousfWifflListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.4
     */
    protfdtfd void prodfssMousfWifflEvfnt(MousfWifflEvfnt f) {
        MousfWifflListfnfr listfnfr = mousfWifflListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi(id) {
              dbsf MousfEvfnt.MOUSE_WHEEL:
                  listfnfr.mousfWifflMovfd(f);
                  brfbk;
            }
        }
    }

    boolfbn postsOldMousfEvfnts() {
        rfturn fblsf;
    }

    /**
     * Prodfssfs input mftiod fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>InputMftiodListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss input mftiod fvfnts
     * brf fnbblfd for tiis domponfnt. Input mftiod fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>InputMftiodListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddInputMftiodListfnfr</dodf>.
     * <li>Input mftiod fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif input mftiod fvfnt
     * @sff         jbvb.bwt.fvfnt.InputMftiodEvfnt
     * @sff         jbvb.bwt.fvfnt.InputMftiodListfnfr
     * @sff         #bddInputMftiodListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.2
     */
    protfdtfd void prodfssInputMftiodEvfnt(InputMftiodEvfnt f) {
        InputMftiodListfnfr listfnfr = inputMftiodListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi (id) {
              dbsf InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED:
                  listfnfr.inputMftiodTfxtCibngfd(f);
                  brfbk;
              dbsf InputMftiodEvfnt.CARET_POSITION_CHANGED:
                  listfnfr.dbrftPositionCibngfd(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs iifrbrdiy fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>HifrbrdiyListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss iifrbrdiy fvfnts
     * brf fnbblfd for tiis domponfnt. Hifrbrdiy fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>HifrbrdiyListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddHifrbrdiyListfnfr</dodf>.
     * <li>Hifrbrdiy fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif iifrbrdiy fvfnt
     * @sff         jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff         jbvb.bwt.fvfnt.HifrbrdiyListfnfr
     * @sff         #bddHifrbrdiyListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.3
     */
    protfdtfd void prodfssHifrbrdiyEvfnt(HifrbrdiyEvfnt f) {
        HifrbrdiyListfnfr listfnfr = iifrbrdiyListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi (id) {
              dbsf HifrbrdiyEvfnt.HIERARCHY_CHANGED:
                  listfnfr.iifrbrdiyCibngfd(f);
                  brfbk;
            }
        }
    }

    /**
     * Prodfssfs iifrbrdiy bounds fvfnts oddurring on tiis domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd
     * <dodf>HifrbrdiyBoundsListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss iifrbrdiy bounds fvfnts
     * brf fnbblfd for tiis domponfnt. Hifrbrdiy bounds fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>HifrbrdiyBoundsListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddHifrbrdiyBoundsListfnfr</dodf>.
     * <li>Hifrbrdiy bounds fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif iifrbrdiy fvfnt
     * @sff         jbvb.bwt.fvfnt.HifrbrdiyEvfnt
     * @sff         jbvb.bwt.fvfnt.HifrbrdiyBoundsListfnfr
     * @sff         #bddHifrbrdiyBoundsListfnfr
     * @sff         #fnbblfEvfnts
     * @sindf       1.3
     */
    protfdtfd void prodfssHifrbrdiyBoundsEvfnt(HifrbrdiyEvfnt f) {
        HifrbrdiyBoundsListfnfr listfnfr = iifrbrdiyBoundsListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi (id) {
              dbsf HifrbrdiyEvfnt.ANCESTOR_MOVED:
                  listfnfr.bndfstorMovfd(f);
                  brfbk;
              dbsf HifrbrdiyEvfnt.ANCESTOR_RESIZED:
                  listfnfr.bndfstorRfsizfd(f);
                  brfbk;
            }
        }
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @rfturn {@dodf truf} if tif fvfnt wbs ibndlfd, {@dodf fblsf} otifrwisf
     * @dfprfdbtfd As of JDK vfrsion 1.1
     * rfplbdfd by prodfssEvfnt(AWTEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn ibndlfEvfnt(Evfnt fvt) {
        switdi (fvt.id) {
          dbsf Evfnt.MOUSE_ENTER:
              rfturn mousfEntfr(fvt, fvt.x, fvt.y);

          dbsf Evfnt.MOUSE_EXIT:
              rfturn mousfExit(fvt, fvt.x, fvt.y);

          dbsf Evfnt.MOUSE_MOVE:
              rfturn mousfMovf(fvt, fvt.x, fvt.y);

          dbsf Evfnt.MOUSE_DOWN:
              rfturn mousfDown(fvt, fvt.x, fvt.y);

          dbsf Evfnt.MOUSE_DRAG:
              rfturn mousfDrbg(fvt, fvt.x, fvt.y);

          dbsf Evfnt.MOUSE_UP:
              rfturn mousfUp(fvt, fvt.x, fvt.y);

          dbsf Evfnt.KEY_PRESS:
          dbsf Evfnt.KEY_ACTION:
              rfturn kfyDown(fvt, fvt.kfy);

          dbsf Evfnt.KEY_RELEASE:
          dbsf Evfnt.KEY_ACTION_RELEASE:
              rfturn kfyUp(fvt, fvt.kfy);

          dbsf Evfnt.ACTION_EVENT:
              rfturn bdtion(fvt, fvt.brg);
          dbsf Evfnt.GOT_FOCUS:
              rfturn gotFodus(fvt, fvt.brg);
          dbsf Evfnt.LOST_FOCUS:
              rfturn lostFodus(fvt, fvt.brg);
        }
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfDown(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfMotionEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfDrbg(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfUp(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfMotionEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfMovf(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfEntfr(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  x tif x doordinbtf
     * @pbrbm  y tif y doordinbtf
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssMousfEvfnt(MousfEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn mousfExit(Evfnt fvt, int x, int y) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  kfy tif kfy prfssfd
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssKfyEvfnt(KfyEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn kfyDown(Evfnt fvt, int kfy) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  kfy tif kfy prfssfd
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssKfyEvfnt(KfyEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn kfyUp(Evfnt fvt, int kfy) {
        rfturn fblsf;
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  wibt tif objfdt bdtfd on
     * @rfturn {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * siould rfgistfr tiis domponfnt bs AdtionListfnfr on domponfnt
     * wiidi firfs bdtion fvfnts.
     */
    @Dfprfdbtfd
    publid boolfbn bdtion(Evfnt fvt, Objfdt wibt) {
        rfturn fblsf;
    }

    /**
     * Mbkfs tiis <dodf>Componfnt</dodf> displbybblf by donnfdting it to b
     * nbtivf sdrffn rfsourdf.
     * Tiis mftiod is dbllfd intfrnblly by tif toolkit bnd siould
     * not bf dbllfd dirfdtly by progrbms.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @sff       #isDisplbybblf
     * @sff       #rfmovfNotify
     * @sff #invblidbtf
     * @sindf 1.0
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            ComponfntPffr pffr = tiis.pffr;
            if (pffr == null || pffr instbndfof LigitwfigitPffr){
                if (pffr == null) {
                    // Updbtf boti tif Componfnt's pffr vbribblf bnd tif lodbl
                    // vbribblf wf usf for tirfbd sbffty.
                    tiis.pffr = pffr = gftToolkit().drfbtfComponfnt(tiis);
                }

                // Tiis is b ligitwfigit domponfnt wiidi mfbns it won't bf
                // bblf to gft window-rflbtfd fvfnts by itsflf.  If bny
                // ibvf bffn fnbblfd, tifn tif nfbrfst nbtivf dontbinfr must
                // bf fnbblfd.
                if (pbrfnt != null) {
                    long mbsk = 0;
                    if ((mousfListfnfr != null) || ((fvfntMbsk & AWTEvfnt.MOUSE_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvfnt.MOUSE_EVENT_MASK;
                    }
                    if ((mousfMotionListfnfr != null) ||
                        ((fvfntMbsk & AWTEvfnt.MOUSE_MOTION_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvfnt.MOUSE_MOTION_EVENT_MASK;
                    }
                    if ((mousfWifflListfnfr != null ) ||
                        ((fvfntMbsk & AWTEvfnt.MOUSE_WHEEL_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvfnt.MOUSE_WHEEL_EVENT_MASK;
                    }
                    if (fodusListfnfr != null || (fvfntMbsk & AWTEvfnt.FOCUS_EVENT_MASK) != 0) {
                        mbsk |= AWTEvfnt.FOCUS_EVENT_MASK;
                    }
                    if (kfyListfnfr != null || (fvfntMbsk & AWTEvfnt.KEY_EVENT_MASK) != 0) {
                        mbsk |= AWTEvfnt.KEY_EVENT_MASK;
                    }
                    if (mbsk != 0) {
                        pbrfnt.proxyEnbblfEvfnts(mbsk);
                    }
                }
            } flsf {
                // It's nbtivf. If tif pbrfnt is ligitwfigit it will nffd somf
                // iflp.
                Contbinfr pbrfnt = gftContbinfr();
                if (pbrfnt != null && pbrfnt.isLigitwfigit()) {
                    rflodbtfComponfnt();
                    if (!pbrfnt.isRfdursivflyVisiblfUpToHfbvywfigitContbinfr())
                    {
                        pffr.sftVisiblf(fblsf);
                    }
                }
            }
            invblidbtf();

            int npopups = (popups != null? popups.sizf() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMfnu popup = popups.flfmfntAt(i);
                popup.bddNotify();
            }

            if (dropTbrgft != null) dropTbrgft.bddNotify(pffr);

            pffrFont = gftFont();

            if (gftContbinfr() != null && !isAddNotifyComplftf) {
                gftContbinfr().indrfbsfComponfntCount(tiis);
            }


            // Updbtf stbdking ordfr
            updbtfZOrdfr();

            if (!isAddNotifyComplftf) {
                mixOnSiowing();
            }

            isAddNotifyComplftf = truf;

            if (iifrbrdiyListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK)) {
                HifrbrdiyEvfnt f =
                    nfw HifrbrdiyEvfnt(tiis, HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                       tiis, pbrfnt,
                                       HifrbrdiyEvfnt.DISPLAYABILITY_CHANGED |
                                       ((isRfdursivflyVisiblf())
                                        ? HifrbrdiyEvfnt.SHOWING_CHANGED
                                        : 0));
                dispbtdiEvfnt(f);
            }
        }
    }

    /**
     * Mbkfs tiis <dodf>Componfnt</dodf> undisplbybblf by dfstroying it nbtivf
     * sdrffn rfsourdf.
     * <p>
     * Tiis mftiod is dbllfd by tif toolkit intfrnblly bnd siould
     * not bf dbllfd dirfdtly by progrbms. Codf ovfrriding
     * tiis mftiod siould dbll <dodf>supfr.rfmovfNotify</dodf> bs
     * tif first linf of tif ovfrriding mftiod.
     *
     * @sff       #isDisplbybblf
     * @sff       #bddNotify
     * @sindf 1.0
     */
    publid void rfmovfNotify() {
        KfybobrdFodusMbnbgfr.dlfbrMostRfdfntFodusOwnfr(tiis);
        if (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
            gftPfrmbnfntFodusOwnfr() == tiis)
        {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                sftGlobblPfrmbnfntFodusOwnfr(null);
        }

        syndironizfd (gftTrffLodk()) {
            dlfbrLigitwfigitDispbtdifrOnRfmovf(tiis);

            if (isFodusOwnfr() && KfybobrdFodusMbnbgfr.isAutoFodusTrbnsffrEnbblfdFor(tiis)) {
                trbnsffrFodus(truf);
            }

            if (gftContbinfr() != null && isAddNotifyComplftf) {
                gftContbinfr().dfdrfbsfComponfntCount(tiis);
            }

            int npopups = (popups != null? popups.sizf() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMfnu popup = popups.flfmfntAt(i);
                popup.rfmovfNotify();
            }
            // If tifrf is bny input dontfxt for tiis domponfnt, notify
            // tibt tiis domponfnt is bfing rfmovfd. (Tiis ibs to bf donf
            // bfforf iiding pffr.)
            if ((fvfntMbsk & AWTEvfnt.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContfxt inputContfxt = gftInputContfxt();
                if (inputContfxt != null) {
                    inputContfxt.rfmovfNotify(tiis);
                }
            }

            ComponfntPffr p = pffr;
            if (p != null) {
                boolfbn isLigitwfigit = isLigitwfigit();

                if (bufffrStrbtfgy instbndfof FlipBufffrStrbtfgy) {
                    ((FlipBufffrStrbtfgy)bufffrStrbtfgy).dfstroyBufffrs();
                }

                if (dropTbrgft != null) dropTbrgft.rfmovfNotify(pffr);

                // Hidf pffr first to stop systfm fvfnts sudi bs dursor movfs.
                if (visiblf) {
                    p.sftVisiblf(fblsf);
                }

                pffr = null; // Stop pffr updbtfs.
                pffrFont = null;

                Toolkit.gftEvfntQufuf().rfmovfSourdfEvfnts(tiis, fblsf);
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    disdbrdKfyEvfnts(tiis);

                p.disposf();

                mixOnHiding(isLigitwfigit);

                isAddNotifyComplftf = fblsf;
                // Nullifying dompoundSibpf mfbns tibt tif domponfnt ibs normbl sibpf
                // (or ibs no sibpf bt bll).
                tiis.dompoundSibpf = null;
            }

            if (iifrbrdiyListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK)) {
                HifrbrdiyEvfnt f =
                    nfw HifrbrdiyEvfnt(tiis, HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                       tiis, pbrfnt,
                                       HifrbrdiyEvfnt.DISPLAYABILITY_CHANGED |
                                       ((isRfdursivflyVisiblf())
                                        ? HifrbrdiyEvfnt.SHOWING_CHANGED
                                        : 0));
                dispbtdiEvfnt(f);
            }
        }
    }

    /**
     * @pbrbm  fvt tif fvfnt to ibndlf
     * @pbrbm  wibt tif objfdt fodusfd
     * @rfturn  {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssFodusEvfnt(FodusEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn gotFodus(Evfnt fvt, Objfdt wibt) {
        rfturn fblsf;
    }

    /**
     * @pbrbm fvt  tif fvfnt to ibndlf
     * @pbrbm wibt tif objfdt fodusfd
     * @rfturn  {@dodf fblsf}
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by prodfssFodusEvfnt(FodusEvfnt).
     */
    @Dfprfdbtfd
    publid boolfbn lostFodus(Evfnt fvt, Objfdt wibt) {
        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr tiis <dodf>Componfnt</dodf> dbn bfdomf tif fodus
     * ownfr.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>Componfnt</dodf> is
     * fodusbblf; <dodf>fblsf</dodf> otifrwisf
     * @sff #sftFodusbblf
     * @sindf 1.1
     * @dfprfdbtfd As of 1.4, rfplbdfd by <dodf>isFodusbblf()</dodf>.
     */
    @Dfprfdbtfd
    publid boolfbn isFodusTrbvfrsbblf() {
        if (isFodusTrbvfrsbblfOvfrriddfn == FOCUS_TRAVERSABLE_UNKNOWN) {
            isFodusTrbvfrsbblfOvfrriddfn = FOCUS_TRAVERSABLE_DEFAULT;
        }
        rfturn fodusbblf;
    }

    /**
     * Rfturns wiftifr tiis Componfnt dbn bf fodusfd.
     *
     * @rfturn <dodf>truf</dodf> if tiis Componfnt is fodusbblf;
     *         <dodf>fblsf</dodf> otifrwisf.
     * @sff #sftFodusbblf
     * @sindf 1.4
     */
    publid boolfbn isFodusbblf() {
        rfturn isFodusTrbvfrsbblf();
    }

    /**
     * Sfts tif fodusbblf stbtf of tiis Componfnt to tif spfdififd vbluf. Tiis
     * vbluf ovfrridfs tif Componfnt's dffbult fodusbbility.
     *
     * @pbrbm fodusbblf indidbtfs wiftifr tiis Componfnt is fodusbblf
     * @sff #isFodusbblf
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusbblf(boolfbn fodusbblf) {
        boolfbn oldFodusbblf;
        syndironizfd (tiis) {
            oldFodusbblf = tiis.fodusbblf;
            tiis.fodusbblf = fodusbblf;
        }
        isFodusTrbvfrsbblfOvfrriddfn = FOCUS_TRAVERSABLE_SET;

        firfPropfrtyCibngf("fodusbblf", oldFodusbblf, fodusbblf);
        if (oldFodusbblf && !fodusbblf) {
            if (isFodusOwnfr() && KfybobrdFodusMbnbgfr.isAutoFodusTrbnsffrEnbblfd()) {
                trbnsffrFodus(truf);
            }
            KfybobrdFodusMbnbgfr.dlfbrMostRfdfntFodusOwnfr(tiis);
        }
    }

    finbl boolfbn isFodusTrbvfrsbblfOvfrriddfn() {
        rfturn (isFodusTrbvfrsbblfOvfrriddfn != FOCUS_TRAVERSABLE_DEFAULT);
    }

    /**
     * Sfts tif fodus trbvfrsbl kfys for b givfn trbvfrsbl opfrbtion for tiis
     * Componfnt.
     * <p>
     * Tif dffbult vblufs for b Componfnt's fodus trbvfrsbl kfys brf
     * implfmfntbtion-dfpfndfnt. Sun rfdommfnds tibt bll implfmfntbtions for b
     * pbrtidulbr nbtivf plbtform usf tif sbmf dffbult vblufs. Tif
     * rfdommfndbtions for Windows bnd Unix brf listfd bflow. Tifsf
     * rfdommfndbtions brf usfd in tif Sun AWT implfmfntbtions.
     *
     * <tbblf bordfr=1 summbry="Rfdommfndfd dffbult vblufs for b Componfnt's fodus trbvfrsbl kfys">
     * <tr>
     *    <ti>Idfntififr</ti>
     *    <ti>Mfbning</ti>
     *    <ti>Dffbult</ti>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl forwbrd kfybobrd trbvfrsbl</td>
     *    <td>TAB on KEY_PRESSED, CTRL-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl rfvfrsf kfybobrd trbvfrsbl</td>
     *    <td>SHIFT-TAB on KEY_PRESSED, CTRL-SHIFT-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS</td>
     *    <td>Go up onf fodus trbvfrsbl dydlf</td>
     *    <td>nonf</td>
     * </tr>
     * </tbblf>
     *
     * To disbblf b trbvfrsbl kfy, usf bn fmpty Sft; Collfdtions.EMPTY_SET is
     * rfdommfndfd.
     * <p>
     * Using tif AWTKfyStrokf API, dlifnt dodf dbn spfdify on wiidi of two
     * spfdifid KfyEvfnts, KEY_PRESSED or KEY_RELEASED, tif fodus trbvfrsbl
     * opfrbtion will oddur. Rfgbrdlfss of wiidi KfyEvfnt is spfdififd,
     * iowfvfr, bll KfyEvfnts rflbtfd to tif fodus trbvfrsbl kfy, indluding tif
     * bssodibtfd KEY_TYPED fvfnt, will bf donsumfd, bnd will not bf dispbtdifd
     * to bny Componfnt. It is b runtimf frror to spfdify b KEY_TYPED fvfnt bs
     * mbpping to b fodus trbvfrsbl opfrbtion, or to mbp tif sbmf fvfnt to
     * multiplf dffbult fodus trbvfrsbl opfrbtions.
     * <p>
     * If b vbluf of null is spfdififd for tif Sft, tiis Componfnt inifrits tif
     * Sft from its pbrfnt. If bll bndfstors of tiis Componfnt ibvf null
     * spfdififd for tif Sft, tifn tif durrfnt KfybobrdFodusMbnbgfr's dffbult
     * Sft is usfd.
     * <p>
     * Tiis mftiod mby tirow b {@dodf ClbssCbstExdfption} if bny {@dodf Objfdt}
     * in {@dodf kfystrokfs} is not bn {@dodf AWTKfyStrokf}.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS
     * @pbrbm kfystrokfs tif Sft of AWTKfyStrokf for tif spfdififd opfrbtion
     * @sff #gftFodusTrbvfrsblKfys
     * @sff KfybobrdFodusMbnbgfr#FORWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#BACKWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#UP_CYCLE_TRAVERSAL_KEYS
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or if kfystrokfs
     *         dontbins null, or if bny kfystrokf rfprfsfnts b KEY_TYPED fvfnt,
     *         or if bny kfystrokf blrfbdy mbps to bnotifr fodus trbvfrsbl
     *         opfrbtion for tiis Componfnt
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusTrbvfrsblKfys(int id,
                                      Sft<? fxtfnds AWTKfyStrokf> kfystrokfs)
    {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH - 1) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        sftFodusTrbvfrsblKfys_NoIDCifdk(id, kfystrokfs);
    }

    /**
     * Rfturns tif Sft of fodus trbvfrsbl kfys for b givfn trbvfrsbl opfrbtion
     * for tiis Componfnt. (Sff
     * <dodf>sftFodusTrbvfrsblKfys</dodf> for b full dfsdription of fbdi kfy.)
     * <p>
     * If b Sft of trbvfrsbl kfys ibs not bffn fxpliditly dffinfd for tiis
     * Componfnt, tifn tiis Componfnt's pbrfnt's Sft is rfturnfd. If no Sft
     * ibs bffn fxpliditly dffinfd for bny of tiis Componfnt's bndfstors, tifn
     * tif durrfnt KfybobrdFodusMbnbgfr's dffbult Sft is rfturnfd.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS
     * @rfturn tif Sft of AWTKfyStrokfs for tif spfdififd opfrbtion. Tif Sft
     *         will bf unmodifibblf, bnd mby bf fmpty. null will nfvfr bf
     *         rfturnfd.
     * @sff #sftFodusTrbvfrsblKfys
     * @sff KfybobrdFodusMbnbgfr#FORWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#BACKWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#UP_CYCLE_TRAVERSAL_KEYS
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS
     * @sindf 1.4
     */
    publid Sft<AWTKfyStrokf> gftFodusTrbvfrsblKfys(int id) {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH - 1) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        rfturn gftFodusTrbvfrsblKfys_NoIDCifdk(id);
    }

    // Wf dffinf tifsf mftiods so tibt Contbinfr dofs not nffd to rfpfbt tiis
    // dodf. Contbinfr dbnnot dbll supfr.<mftiod> bfdbusf Contbinfr bllows
    // DOWN_CYCLE_TRAVERSAL_KEY wiilf Componfnt dofs not. Tif Componfnt mftiod
    // would frronfously gfnfrbtf bn IllfgblArgumfntExdfption for
    // DOWN_CYCLE_TRAVERSAL_KEY.
    finbl void sftFodusTrbvfrsblKfys_NoIDCifdk(int id, Sft<? fxtfnds AWTKfyStrokf> kfystrokfs) {
        Sft<AWTKfyStrokf> oldKfys;

        syndironizfd (tiis) {
            if (fodusTrbvfrsblKfys == null) {
                initiblizfFodusTrbvfrsblKfys();
            }

            if (kfystrokfs != null) {
                for (AWTKfyStrokf kfystrokf : kfystrokfs ) {

                    if (kfystrokf == null) {
                        tirow nfw IllfgblArgumfntExdfption("dbnnot sft null fodus trbvfrsbl kfy");
                    }

                    if (kfystrokf.gftKfyCibr() != KfyEvfnt.CHAR_UNDEFINED) {
                        tirow nfw IllfgblArgumfntExdfption("fodus trbvfrsbl kfys dbnnot mbp to KEY_TYPED fvfnts");
                    }

                    for (int i = 0; i < fodusTrbvfrsblKfys.lfngti; i++) {
                        if (i == id) {
                            dontinuf;
                        }

                        if (gftFodusTrbvfrsblKfys_NoIDCifdk(i).dontbins(kfystrokf))
                        {
                            tirow nfw IllfgblArgumfntExdfption("fodus trbvfrsbl kfys must bf uniquf for b Componfnt");
                        }
                    }
                }
            }

            oldKfys = fodusTrbvfrsblKfys[id];
            fodusTrbvfrsblKfys[id] = (kfystrokfs != null)
                ? Collfdtions.unmodifibblfSft(nfw HbsiSft<AWTKfyStrokf>(kfystrokfs))
                : null;
        }

        firfPropfrtyCibngf(fodusTrbvfrsblKfyPropfrtyNbmfs[id], oldKfys,
                           kfystrokfs);
    }
    finbl Sft<AWTKfyStrokf> gftFodusTrbvfrsblKfys_NoIDCifdk(int id) {
        // Okby to rfturn Sft dirfdtly bfdbusf it is bn unmodifibblf vifw
        @SupprfssWbrnings("undifdkfd")
        Sft<AWTKfyStrokf> kfystrokfs = (fodusTrbvfrsblKfys != null)
            ? fodusTrbvfrsblKfys[id]
            : null;

        if (kfystrokfs != null) {
            rfturn kfystrokfs;
        } flsf {
            Contbinfr pbrfnt = tiis.pbrfnt;
            if (pbrfnt != null) {
                rfturn pbrfnt.gftFodusTrbvfrsblKfys(id);
            } flsf {
                rfturn KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    gftDffbultFodusTrbvfrsblKfys(id);
            }
        }
    }

    /**
     * Rfturns wiftifr tif Sft of fodus trbvfrsbl kfys for tif givfn fodus
     * trbvfrsbl opfrbtion ibs bffn fxpliditly dffinfd for tiis Componfnt. If
     * tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Componfnt is inifriting tif
     * Sft from bn bndfstor, or from tif durrfnt KfybobrdFodusMbnbgfr.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS
     * @rfturn <dodf>truf</dodf> if tif tif Sft of fodus trbvfrsbl kfys for tif
     *         givfn fodus trbvfrsbl opfrbtion ibs bffn fxpliditly dffinfd for
     *         tiis Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS
     * @sindf 1.4
     */
    publid boolfbn brfFodusTrbvfrsblKfysSft(int id) {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH - 1) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        rfturn (fodusTrbvfrsblKfys != null && fodusTrbvfrsblKfys[id] != null);
    }

    /**
     * Sfts wiftifr fodus trbvfrsbl kfys brf fnbblfd for tiis Componfnt.
     * Componfnts for wiidi fodus trbvfrsbl kfys brf disbblfd rfdfivf kfy
     * fvfnts for fodus trbvfrsbl kfys. Componfnts for wiidi fodus trbvfrsbl
     * kfys brf fnbblfd do not sff tifsf fvfnts; instfbd, tif fvfnts brf
     * butombtidblly donvfrtfd to trbvfrsbl opfrbtions.
     *
     * @pbrbm fodusTrbvfrsblKfysEnbblfd wiftifr fodus trbvfrsbl kfys brf
     *        fnbblfd for tiis Componfnt
     * @sff #gftFodusTrbvfrsblKfysEnbblfd
     * @sff #sftFodusTrbvfrsblKfys
     * @sff #gftFodusTrbvfrsblKfys
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusTrbvfrsblKfysEnbblfd(boolfbn
                                             fodusTrbvfrsblKfysEnbblfd) {
        boolfbn oldFodusTrbvfrsblKfysEnbblfd;
        syndironizfd (tiis) {
            oldFodusTrbvfrsblKfysEnbblfd = tiis.fodusTrbvfrsblKfysEnbblfd;
            tiis.fodusTrbvfrsblKfysEnbblfd = fodusTrbvfrsblKfysEnbblfd;
        }
        firfPropfrtyCibngf("fodusTrbvfrsblKfysEnbblfd",
                           oldFodusTrbvfrsblKfysEnbblfd,
                           fodusTrbvfrsblKfysEnbblfd);
    }

    /**
     * Rfturns wiftifr fodus trbvfrsbl kfys brf fnbblfd for tiis Componfnt.
     * Componfnts for wiidi fodus trbvfrsbl kfys brf disbblfd rfdfivf kfy
     * fvfnts for fodus trbvfrsbl kfys. Componfnts for wiidi fodus trbvfrsbl
     * kfys brf fnbblfd do not sff tifsf fvfnts; instfbd, tif fvfnts brf
     * butombtidblly donvfrtfd to trbvfrsbl opfrbtions.
     *
     * @rfturn wiftifr fodus trbvfrsbl kfys brf fnbblfd for tiis Componfnt
     * @sff #sftFodusTrbvfrsblKfysEnbblfd
     * @sff #sftFodusTrbvfrsblKfys
     * @sff #gftFodusTrbvfrsblKfys
     * @sindf 1.4
     */
    publid boolfbn gftFodusTrbvfrsblKfysEnbblfd() {
        rfturn fodusTrbvfrsblKfysEnbblfd;
    }

    /**
     * Rfqufsts tibt tiis Componfnt gft tif input fodus, bnd tibt tiis
     * Componfnt's top-lfvfl bndfstor bfdomf tif fodusfd Window. Tiis
     * domponfnt must bf displbybblf, fodusbblf, visiblf bnd bll of
     * its bndfstors (witi tif fxdfption of tif top-lfvfl Window) must
     * bf visiblf for tif rfqufst to bf grbntfd. Evfry fffort will bf
     * mbdf to ionor tif rfqufst; iowfvfr, in somf dbsfs it mby bf
     * impossiblf to do so. Dfvflopfrs must nfvfr bssumf tibt tiis
     * Componfnt is tif fodus ownfr until tiis Componfnt rfdfivfs b
     * FOCUS_GAINED fvfnt. If tiis rfqufst is dfnifd bfdbusf tiis
     * Componfnt's top-lfvfl Window dbnnot bfdomf tif fodusfd Window,
     * tif rfqufst will bf rfmfmbfrfd bnd will bf grbntfd wifn tif
     * Window is lbtfr fodusfd by tif usfr.
     * <p>
     * Tiis mftiod dbnnot bf usfd to sft tif fodus ownfr to no Componfnt bt
     * bll. Usf <dodf>KfybobrdFodusMbnbgfr.dlfbrGlobblFodusOwnfr()</dodf>
     * instfbd.
     * <p>
     * Bfdbusf tif fodus bfibvior of tiis mftiod is plbtform-dfpfndfnt,
     * dfvflopfrs brf strongly fndourbgfd to usf
     * <dodf>rfqufstFodusInWindow</dodf> wifn possiblf.
     *
     * <p>Notf: Not bll fodus trbnsffrs rfsult from invoking tiis mftiod. As
     * sudi, b domponfnt mby rfdfivf fodus witiout tiis or bny of tif otifr
     * {@dodf rfqufstFodus} mftiods of {@dodf Componfnt} bfing invokfd.
     *
     * @sff #rfqufstFodusInWindow
     * @sff jbvb.bwt.fvfnt.FodusEvfnt
     * @sff #bddFodusListfnfr
     * @sff #isFodusbblf
     * @sff #isDisplbybblf
     * @sff KfybobrdFodusMbnbgfr#dlfbrGlobblFodusOwnfr
     * @sindf 1.0
     */
    publid void rfqufstFodus() {
        rfqufstFodusHflpfr(fblsf, truf);
    }

    boolfbn rfqufstFodus(CbusfdFodusEvfnt.Cbusf dbusf) {
        rfturn rfqufstFodusHflpfr(fblsf, truf, dbusf);
    }

    /**
     * Rfqufsts tibt tiis <dodf>Componfnt</dodf> gft tif input fodus,
     * bnd tibt tiis <dodf>Componfnt</dodf>'s top-lfvfl bndfstor
     * bfdomf tif fodusfd <dodf>Window</dodf>. Tiis domponfnt must bf
     * displbybblf, fodusbblf, visiblf bnd bll of its bndfstors (witi
     * tif fxdfption of tif top-lfvfl Window) must bf visiblf for tif
     * rfqufst to bf grbntfd. Evfry fffort will bf mbdf to ionor tif
     * rfqufst; iowfvfr, in somf dbsfs it mby bf impossiblf to do
     * so. Dfvflopfrs must nfvfr bssumf tibt tiis domponfnt is tif
     * fodus ownfr until tiis domponfnt rfdfivfs b FOCUS_GAINED
     * fvfnt. If tiis rfqufst is dfnifd bfdbusf tiis domponfnt's
     * top-lfvfl window dbnnot bfdomf tif fodusfd window, tif rfqufst
     * will bf rfmfmbfrfd bnd will bf grbntfd wifn tif window is lbtfr
     * fodusfd by tif usfr.
     * <p>
     * Tiis mftiod rfturns b boolfbn vbluf. If <dodf>fblsf</dodf> is rfturnfd,
     * tif rfqufst is <b>gubrbntffd to fbil</b>. If <dodf>truf</dodf> is
     * rfturnfd, tif rfqufst will suddffd <b>unlfss</b> it is vftofd, or bn
     * fxtrbordinbry fvfnt, sudi bs disposbl of tif domponfnt's pffr, oddurs
     * bfforf tif rfqufst dbn bf grbntfd by tif nbtivf windowing systfm. Agbin,
     * wiilf b rfturn vbluf of <dodf>truf</dodf> indidbtfs tibt tif rfqufst is
     * likfly to suddffd, dfvflopfrs must nfvfr bssumf tibt tiis domponfnt is
     * tif fodus ownfr until tiis domponfnt rfdfivfs b FOCUS_GAINED fvfnt.
     * <p>
     * Tiis mftiod dbnnot bf usfd to sft tif fodus ownfr to no domponfnt bt
     * bll. Usf <dodf>KfybobrdFodusMbnbgfr.dlfbrGlobblFodusOwnfr</dodf>
     * instfbd.
     * <p>
     * Bfdbusf tif fodus bfibvior of tiis mftiod is plbtform-dfpfndfnt,
     * dfvflopfrs brf strongly fndourbgfd to usf
     * <dodf>rfqufstFodusInWindow</dodf> wifn possiblf.
     * <p>
     * Evfry fffort will bf mbdf to fnsurf tibt <dodf>FodusEvfnt</dodf>s
     * gfnfrbtfd bs b
     * rfsult of tiis rfqufst will ibvf tif spfdififd tfmporbry vbluf. Howfvfr,
     * bfdbusf spfdifying bn brbitrbry tfmporbry stbtf mby not bf implfmfntbblf
     * on bll nbtivf windowing systfms, dorrfdt bfibvior for tiis mftiod dbn bf
     * gubrbntffd only for ligitwfigit <dodf>Componfnt</dodf>s.
     * Tiis mftiod is not intfndfd
     * for gfnfrbl usf, but fxists instfbd bs b iook for ligitwfigit domponfnt
     * librbrifs, sudi bs Swing.
     *
     * <p>Notf: Not bll fodus trbnsffrs rfsult from invoking tiis mftiod. As
     * sudi, b domponfnt mby rfdfivf fodus witiout tiis or bny of tif otifr
     * {@dodf rfqufstFodus} mftiods of {@dodf Componfnt} bfing invokfd.
     *
     * @pbrbm tfmporbry truf if tif fodus dibngf is tfmporbry,
     *        sudi bs wifn tif window losfs tif fodus; for
     *        morf informbtion on tfmporbry fodus dibngfs sff tif
     *<b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
     * @rfturn <dodf>fblsf</dodf> if tif fodus dibngf rfqufst is gubrbntffd to
     *         fbil; <dodf>truf</dodf> if it is likfly to suddffd
     * @sff jbvb.bwt.fvfnt.FodusEvfnt
     * @sff #bddFodusListfnfr
     * @sff #isFodusbblf
     * @sff #isDisplbybblf
     * @sff KfybobrdFodusMbnbgfr#dlfbrGlobblFodusOwnfr
     * @sindf 1.4
     */
    protfdtfd boolfbn rfqufstFodus(boolfbn tfmporbry) {
        rfturn rfqufstFodusHflpfr(tfmporbry, truf);
    }

    boolfbn rfqufstFodus(boolfbn tfmporbry, CbusfdFodusEvfnt.Cbusf dbusf) {
        rfturn rfqufstFodusHflpfr(tfmporbry, truf, dbusf);
    }
    /**
     * Rfqufsts tibt tiis Componfnt gft tif input fodus, if tiis
     * Componfnt's top-lfvfl bndfstor is blrfbdy tif fodusfd
     * Window. Tiis domponfnt must bf displbybblf, fodusbblf, visiblf
     * bnd bll of its bndfstors (witi tif fxdfption of tif top-lfvfl
     * Window) must bf visiblf for tif rfqufst to bf grbntfd. Evfry
     * fffort will bf mbdf to ionor tif rfqufst; iowfvfr, in somf
     * dbsfs it mby bf impossiblf to do so. Dfvflopfrs must nfvfr
     * bssumf tibt tiis Componfnt is tif fodus ownfr until tiis
     * Componfnt rfdfivfs b FOCUS_GAINED fvfnt.
     * <p>
     * Tiis mftiod rfturns b boolfbn vbluf. If <dodf>fblsf</dodf> is rfturnfd,
     * tif rfqufst is <b>gubrbntffd to fbil</b>. If <dodf>truf</dodf> is
     * rfturnfd, tif rfqufst will suddffd <b>unlfss</b> it is vftofd, or bn
     * fxtrbordinbry fvfnt, sudi bs disposbl of tif Componfnt's pffr, oddurs
     * bfforf tif rfqufst dbn bf grbntfd by tif nbtivf windowing systfm. Agbin,
     * wiilf b rfturn vbluf of <dodf>truf</dodf> indidbtfs tibt tif rfqufst is
     * likfly to suddffd, dfvflopfrs must nfvfr bssumf tibt tiis Componfnt is
     * tif fodus ownfr until tiis Componfnt rfdfivfs b FOCUS_GAINED fvfnt.
     * <p>
     * Tiis mftiod dbnnot bf usfd to sft tif fodus ownfr to no Componfnt bt
     * bll. Usf <dodf>KfybobrdFodusMbnbgfr.dlfbrGlobblFodusOwnfr()</dodf>
     * instfbd.
     * <p>
     * Tif fodus bfibvior of tiis mftiod dbn bf implfmfntfd uniformly bdross
     * plbtforms, bnd tius dfvflopfrs brf strongly fndourbgfd to usf tiis
     * mftiod ovfr <dodf>rfqufstFodus</dodf> wifn possiblf. Codf wiidi rflifs
     * on <dodf>rfqufstFodus</dodf> mby fxiibit difffrfnt fodus bfibvior on
     * difffrfnt plbtforms.
     *
     * <p>Notf: Not bll fodus trbnsffrs rfsult from invoking tiis mftiod. As
     * sudi, b domponfnt mby rfdfivf fodus witiout tiis or bny of tif otifr
     * {@dodf rfqufstFodus} mftiods of {@dodf Componfnt} bfing invokfd.
     *
     * @rfturn <dodf>fblsf</dodf> if tif fodus dibngf rfqufst is gubrbntffd to
     *         fbil; <dodf>truf</dodf> if it is likfly to suddffd
     * @sff #rfqufstFodus
     * @sff jbvb.bwt.fvfnt.FodusEvfnt
     * @sff #bddFodusListfnfr
     * @sff #isFodusbblf
     * @sff #isDisplbybblf
     * @sff KfybobrdFodusMbnbgfr#dlfbrGlobblFodusOwnfr
     * @sindf 1.4
     */
    publid boolfbn rfqufstFodusInWindow() {
        rfturn rfqufstFodusHflpfr(fblsf, fblsf);
    }

    boolfbn rfqufstFodusInWindow(CbusfdFodusEvfnt.Cbusf dbusf) {
        rfturn rfqufstFodusHflpfr(fblsf, fblsf, dbusf);
    }

    /**
     * Rfqufsts tibt tiis <dodf>Componfnt</dodf> gft tif input fodus,
     * if tiis <dodf>Componfnt</dodf>'s top-lfvfl bndfstor is blrfbdy
     * tif fodusfd <dodf>Window</dodf>.  Tiis domponfnt must bf
     * displbybblf, fodusbblf, visiblf bnd bll of its bndfstors (witi
     * tif fxdfption of tif top-lfvfl Window) must bf visiblf for tif
     * rfqufst to bf grbntfd. Evfry fffort will bf mbdf to ionor tif
     * rfqufst; iowfvfr, in somf dbsfs it mby bf impossiblf to do
     * so. Dfvflopfrs must nfvfr bssumf tibt tiis domponfnt is tif
     * fodus ownfr until tiis domponfnt rfdfivfs b FOCUS_GAINED fvfnt.
     * <p>
     * Tiis mftiod rfturns b boolfbn vbluf. If <dodf>fblsf</dodf> is rfturnfd,
     * tif rfqufst is <b>gubrbntffd to fbil</b>. If <dodf>truf</dodf> is
     * rfturnfd, tif rfqufst will suddffd <b>unlfss</b> it is vftofd, or bn
     * fxtrbordinbry fvfnt, sudi bs disposbl of tif domponfnt's pffr, oddurs
     * bfforf tif rfqufst dbn bf grbntfd by tif nbtivf windowing systfm. Agbin,
     * wiilf b rfturn vbluf of <dodf>truf</dodf> indidbtfs tibt tif rfqufst is
     * likfly to suddffd, dfvflopfrs must nfvfr bssumf tibt tiis domponfnt is
     * tif fodus ownfr until tiis domponfnt rfdfivfs b FOCUS_GAINED fvfnt.
     * <p>
     * Tiis mftiod dbnnot bf usfd to sft tif fodus ownfr to no domponfnt bt
     * bll. Usf <dodf>KfybobrdFodusMbnbgfr.dlfbrGlobblFodusOwnfr</dodf>
     * instfbd.
     * <p>
     * Tif fodus bfibvior of tiis mftiod dbn bf implfmfntfd uniformly bdross
     * plbtforms, bnd tius dfvflopfrs brf strongly fndourbgfd to usf tiis
     * mftiod ovfr <dodf>rfqufstFodus</dodf> wifn possiblf. Codf wiidi rflifs
     * on <dodf>rfqufstFodus</dodf> mby fxiibit difffrfnt fodus bfibvior on
     * difffrfnt plbtforms.
     * <p>
     * Evfry fffort will bf mbdf to fnsurf tibt <dodf>FodusEvfnt</dodf>s
     * gfnfrbtfd bs b
     * rfsult of tiis rfqufst will ibvf tif spfdififd tfmporbry vbluf. Howfvfr,
     * bfdbusf spfdifying bn brbitrbry tfmporbry stbtf mby not bf implfmfntbblf
     * on bll nbtivf windowing systfms, dorrfdt bfibvior for tiis mftiod dbn bf
     * gubrbntffd only for ligitwfigit domponfnts. Tiis mftiod is not intfndfd
     * for gfnfrbl usf, but fxists instfbd bs b iook for ligitwfigit domponfnt
     * librbrifs, sudi bs Swing.
     *
     * <p>Notf: Not bll fodus trbnsffrs rfsult from invoking tiis mftiod. As
     * sudi, b domponfnt mby rfdfivf fodus witiout tiis or bny of tif otifr
     * {@dodf rfqufstFodus} mftiods of {@dodf Componfnt} bfing invokfd.
     *
     * @pbrbm tfmporbry truf if tif fodus dibngf is tfmporbry,
     *        sudi bs wifn tif window losfs tif fodus; for
     *        morf informbtion on tfmporbry fodus dibngfs sff tif
     *<b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
     * @rfturn <dodf>fblsf</dodf> if tif fodus dibngf rfqufst is gubrbntffd to
     *         fbil; <dodf>truf</dodf> if it is likfly to suddffd
     * @sff #rfqufstFodus
     * @sff jbvb.bwt.fvfnt.FodusEvfnt
     * @sff #bddFodusListfnfr
     * @sff #isFodusbblf
     * @sff #isDisplbybblf
     * @sff KfybobrdFodusMbnbgfr#dlfbrGlobblFodusOwnfr
     * @sindf 1.4
     */
    protfdtfd boolfbn rfqufstFodusInWindow(boolfbn tfmporbry) {
        rfturn rfqufstFodusHflpfr(tfmporbry, fblsf);
    }

    boolfbn rfqufstFodusInWindow(boolfbn tfmporbry, CbusfdFodusEvfnt.Cbusf dbusf) {
        rfturn rfqufstFodusHflpfr(tfmporbry, fblsf, dbusf);
    }

    finbl boolfbn rfqufstFodusHflpfr(boolfbn tfmporbry,
                                     boolfbn fodusfdWindowCibngfAllowfd) {
        rfturn rfqufstFodusHflpfr(tfmporbry, fodusfdWindowCibngfAllowfd, CbusfdFodusEvfnt.Cbusf.UNKNOWN);
    }

    finbl boolfbn rfqufstFodusHflpfr(boolfbn tfmporbry,
                                     boolfbn fodusfdWindowCibngfAllowfd,
                                     CbusfdFodusEvfnt.Cbusf dbusf)
    {
        // 1) Cifdk if tif fvfnt bfing dispbtdifd is b systfm-gfnfrbtfd mousf fvfnt.
        AWTEvfnt durrfntEvfnt = EvfntQufuf.gftCurrfntEvfnt();
        if (durrfntEvfnt instbndfof MousfEvfnt &&
            SunToolkit.isSystfmGfnfrbtfd(durrfntEvfnt))
        {
            // 2) Sbnity difdk: if tif mousf fvfnt domponfnt sourdf bflongs to tif sbmf dontbining window.
            Componfnt sourdf = ((MousfEvfnt)durrfntEvfnt).gftComponfnt();
            if (sourdf == null || sourdf.gftContbiningWindow() == gftContbiningWindow()) {
                fodusLog.finfst("rfqufsting fodus by mousf fvfnt \"in window\"");

                // If boti tif donditions brf fulfillfd tif fodus rfqufst siould bf stridtly
                // boundfd by tif toplfvfl window. It's bssumfd tibt tif mousf fvfnt bdtivbtfs
                // tif window (if it wbsn't bdtivf) bnd tiis mbkfs it possiblf for b fodus
                // rfqufst witi b strong in-window rfquirfmfnt to dibngf fodus in tif bounds
                // of tif toplfvfl. If, by bny mfbns, duf to bsyndironous nbturf of tif fvfnt
                // dispbtdiing mfdibnism, tif window ibppfns to bf nbtivfly inbdtivf by tif timf
                // tiis fodus rfqufst is fvfntublly ibndlfd, it siould not rf-bdtivbtf tif
                // toplfvfl. Otifrwisf tif rfsult mby not mfft usfr fxpfdtbtions. Sff 6981400.
                fodusfdWindowCibngfAllowfd = fblsf;
            }
        }
        if (!isRfqufstFodusAddfptfd(tfmporbry, fodusfdWindowCibngfAllowfd, dbusf)) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("rfqufstFodus is not bddfptfd");
            }
            rfturn fblsf;
        }
        // Updbtf most-rfdfnt mbp
        KfybobrdFodusMbnbgfr.sftMostRfdfntFodusOwnfr(tiis);

        Componfnt window = tiis;
        wiilf ( (window != null) && !(window instbndfof Window)) {
            if (!window.isVisiblf()) {
                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    fodusLog.finfst("domponfnt is rfdurivfly invisiblf");
                }
                rfturn fblsf;
            }
            window = window.pbrfnt;
        }

        ComponfntPffr pffr = tiis.pffr;
        Componfnt ifbvywfigit = (pffr instbndfof LigitwfigitPffr)
            ? gftNbtivfContbinfr() : tiis;
        if (ifbvywfigit == null || !ifbvywfigit.isVisiblf()) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Componfnt is not b pbrt of visiblf iifrbrdiy");
            }
            rfturn fblsf;
        }
        pffr = ifbvywfigit.pffr;
        if (pffr == null) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Pffr is null");
            }
            rfturn fblsf;
        }

        // Fodus tiis Componfnt
        long timf = 0;
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            timf = Toolkit.gftEvfntQufuf().gftMostRfdfntKfyEvfntTimf();
        } flsf {
            // A fodus rfqufst mbdf from outsidf EDT siould not bf bssodibtfd witi bny fvfnt
            // bnd so its timf stbmp is simply sft to tif durrfnt timf.
            timf = Systfm.durrfntTimfMillis();
        }

        boolfbn suddfss = pffr.rfqufstFodus
            (tiis, tfmporbry, fodusfdWindowCibngfAllowfd, timf, dbusf);
        if (!suddfss) {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr
                (bppContfxt).dfqufufKfyEvfnts(timf, tiis);
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Pffr rfqufst fbilfd");
            }
        } flsf {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Pbss for " + tiis);
            }
        }
        rfturn suddfss;
    }

    privbtf boolfbn isRfqufstFodusAddfptfd(boolfbn tfmporbry,
                                           boolfbn fodusfdWindowCibngfAllowfd,
                                           CbusfdFodusEvfnt.Cbusf dbusf)
    {
        if (!isFodusbblf() || !isVisiblf()) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Not fodusbblf or not visiblf");
            }
            rfturn fblsf;
        }

        ComponfntPffr pffr = tiis.pffr;
        if (pffr == null) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("pffr is null");
            }
            rfturn fblsf;
        }

        Window window = gftContbiningWindow();
        if (window == null || !window.isFodusbblfWindow()) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("Componfnt dofsn't ibvf toplfvfl");
            }
            rfturn fblsf;
        }

        // Wf ibvf pbssfd bll rfgulbr difdks for fodus rfqufst,
        // now lft's dbll RfqufstFodusControllfr bnd sff wibt it sbys.
        Componfnt fodusOwnfr = KfybobrdFodusMbnbgfr.gftMostRfdfntFodusOwnfr(window);
        if (fodusOwnfr == null) {
            // somftimfs most rfdfnt fodus ownfr mby bf null, but fodus ownfr is not
            // f.g. wf rfsft most rfdfnt fodus ownfr if usfr rfmovfs fodus ownfr
            fodusOwnfr = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
            if (fodusOwnfr != null && fodusOwnfr.gftContbiningWindow() != window) {
                fodusOwnfr = null;
            }
        }

        if (fodusOwnfr == tiis || fodusOwnfr == null) {
            // Controllfr is supposfd to vfrify fodus trbnsffrs bnd for tiis it
            // siould know boti from bnd to domponfnts.  And it siouldn't vfrify
            // trbnsffrs from wifn tifsf domponfnts brf fqubl.
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("fodus ownfr is null or tiis");
            }
            rfturn truf;
        }

        if (CbusfdFodusEvfnt.Cbusf.ACTIVATION == dbusf) {
            // wf siouldn't dbll RfqufstFodusControllfr in dbsf wf brf
            // in bdtivbtion.  Wf do rfqufst fodus on domponfnt wiidi
            // ibs got tfmporbry fodus lost bnd tifn on domponfnt wiidi is
            // most rfdfnt fodus ownfr.  But most rfdfnt fodus ownfr dbn bf
            // dibngfd by rfqufstFodsuXXX() dbll only, so tiis trbnsffr ibs
            // bffn blrfbdy bpprovfd.
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                fodusLog.finfst("dbusf is bdtivbtion");
            }
            rfturn truf;
        }

        boolfbn rft = Componfnt.rfqufstFodusControllfr.bddfptRfqufstFodus(fodusOwnfr,
                                                                          tiis,
                                                                          tfmporbry,
                                                                          fodusfdWindowCibngfAllowfd,
                                                                          dbusf);
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fodusLog.finfst("RfqufstFodusControllfr rfturns {0}", rft);
        }

        rfturn rft;
    }

    privbtf stbtid RfqufstFodusControllfr rfqufstFodusControllfr = nfw DummyRfqufstFodusControllfr();

    // Swing bddfss tiis mftiod tirougi rfflfdtion to implfmfnt InputVfrififr's fundtionblity.
    // Pfribps, wf siould mbkf tiis mftiod publid (lbtfr ;)
    privbtf stbtid dlbss DummyRfqufstFodusControllfr implfmfnts RfqufstFodusControllfr {
        publid boolfbn bddfptRfqufstFodus(Componfnt from, Componfnt to,
                                          boolfbn tfmporbry, boolfbn fodusfdWindowCibngfAllowfd,
                                          CbusfdFodusEvfnt.Cbusf dbusf)
        {
            rfturn truf;
        }
    };

    syndironizfd stbtid void sftRfqufstFodusControllfr(RfqufstFodusControllfr rfqufstControllfr)
    {
        if (rfqufstControllfr == null) {
            rfqufstFodusControllfr = nfw DummyRfqufstFodusControllfr();
        } flsf {
            rfqufstFodusControllfr = rfqufstControllfr;
        }
    }

    /**
     * Rfturns tif Contbinfr wiidi is tif fodus dydlf root of tiis Componfnt's
     * fodus trbvfrsbl dydlf. Ebdi fodus trbvfrsbl dydlf ibs only b singlf
     * fodus dydlf root bnd fbdi Componfnt wiidi is not b Contbinfr bflongs to
     * only b singlf fodus trbvfrsbl dydlf. Contbinfrs wiidi brf fodus dydlf
     * roots bflong to two dydlfs: onf rootfd bt tif Contbinfr itsflf, bnd onf
     * rootfd bt tif Contbinfr's nfbrfst fodus-dydlf-root bndfstor. For sudi
     * Contbinfrs, tiis mftiod will rfturn tif Contbinfr's nfbrfst fodus-dydlf-
     * root bndfstor.
     *
     * @rfturn tiis Componfnt's nfbrfst fodus-dydlf-root bndfstor
     * @sff Contbinfr#isFodusCydlfRoot()
     * @sindf 1.4
     */
    publid Contbinfr gftFodusCydlfRootAndfstor() {
        Contbinfr rootAndfstor = tiis.pbrfnt;
        wiilf (rootAndfstor != null && !rootAndfstor.isFodusCydlfRoot()) {
            rootAndfstor = rootAndfstor.pbrfnt;
        }
        rfturn rootAndfstor;
    }

    /**
     * Rfturns wiftifr tif spfdififd Contbinfr is tif fodus dydlf root of tiis
     * Componfnt's fodus trbvfrsbl dydlf. Ebdi fodus trbvfrsbl dydlf ibs only
     * b singlf fodus dydlf root bnd fbdi Componfnt wiidi is not b Contbinfr
     * bflongs to only b singlf fodus trbvfrsbl dydlf.
     *
     * @pbrbm dontbinfr tif Contbinfr to bf tfstfd
     * @rfturn <dodf>truf</dodf> if tif spfdififd Contbinfr is b fodus-dydlf-
     *         root of tiis Componfnt; <dodf>fblsf</dodf> otifrwisf
     * @sff Contbinfr#isFodusCydlfRoot()
     * @sindf 1.4
     */
    publid boolfbn isFodusCydlfRoot(Contbinfr dontbinfr) {
        Contbinfr rootAndfstor = gftFodusCydlfRootAndfstor();
        rfturn (rootAndfstor == dontbinfr);
    }

    Contbinfr gftTrbvfrsblRoot() {
        rfturn gftFodusCydlfRootAndfstor();
    }

    /**
     * Trbnsffrs tif fodus to tif nfxt domponfnt, bs tiougi tiis Componfnt wfrf
     * tif fodus ownfr.
     * @sff       #rfqufstFodus()
     * @sindf     1.1
     */
    publid void trbnsffrFodus() {
        nfxtFodus();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by trbnsffrFodus().
     */
    @Dfprfdbtfd
    publid void nfxtFodus() {
        trbnsffrFodus(fblsf);
    }

    boolfbn trbnsffrFodus(boolfbn dlfbrOnFbilurf) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("dlfbrOnFbilurf = " + dlfbrOnFbilurf);
        }
        Componfnt toFodus = gftNfxtFodusCbndidbtf();
        boolfbn rfs = fblsf;
        if (toFodus != null && !toFodus.isFodusOwnfr() && toFodus != tiis) {
            rfs = toFodus.rfqufstFodusInWindow(CbusfdFodusEvfnt.Cbusf.TRAVERSAL_FORWARD);
        }
        if (dlfbrOnFbilurf && !rfs) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("dlfbr globbl fodus ownfr");
            }
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().dlfbrGlobblFodusOwnfrPriv();
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("rfturning rfsult: " + rfs);
        }
        rfturn rfs;
    }

    finbl Componfnt gftNfxtFodusCbndidbtf() {
        Contbinfr rootAndfstor = gftTrbvfrsblRoot();
        Componfnt domp = tiis;
        wiilf (rootAndfstor != null &&
               !(rootAndfstor.isSiowing() && rootAndfstor.dbnBfFodusOwnfr()))
        {
            domp = rootAndfstor;
            rootAndfstor = domp.gftFodusCydlfRootAndfstor();
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("domp = " + domp + ", root = " + rootAndfstor);
        }
        Componfnt dbndidbtf = null;
        if (rootAndfstor != null) {
            FodusTrbvfrsblPolidy polidy = rootAndfstor.gftFodusTrbvfrsblPolidy();
            Componfnt toFodus = polidy.gftComponfntAftfr(rootAndfstor, domp);
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("domponfnt bftfr is " + toFodus);
            }
            if (toFodus == null) {
                toFodus = polidy.gftDffbultComponfnt(rootAndfstor);
                if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    fodusLog.finfr("dffbult domponfnt is " + toFodus);
                }
            }
            if (toFodus == null) {
                Applft bpplft = EmbfddfdFrbmf.gftApplftIfAndfstorOf(tiis);
                if (bpplft != null) {
                    toFodus = bpplft;
                }
            }
            dbndidbtf = toFodus;
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("Fodus trbnsffr dbndidbtf: " + dbndidbtf);
        }
        rfturn dbndidbtf;
    }

    /**
     * Trbnsffrs tif fodus to tif prfvious domponfnt, bs tiougi tiis Componfnt
     * wfrf tif fodus ownfr.
     * @sff       #rfqufstFodus()
     * @sindf     1.4
     */
    publid void trbnsffrFodusBbdkwbrd() {
        trbnsffrFodusBbdkwbrd(fblsf);
    }

    boolfbn trbnsffrFodusBbdkwbrd(boolfbn dlfbrOnFbilurf) {
        Contbinfr rootAndfstor = gftTrbvfrsblRoot();
        Componfnt domp = tiis;
        wiilf (rootAndfstor != null &&
               !(rootAndfstor.isSiowing() && rootAndfstor.dbnBfFodusOwnfr()))
        {
            domp = rootAndfstor;
            rootAndfstor = domp.gftFodusCydlfRootAndfstor();
        }
        boolfbn rfs = fblsf;
        if (rootAndfstor != null) {
            FodusTrbvfrsblPolidy polidy = rootAndfstor.gftFodusTrbvfrsblPolidy();
            Componfnt toFodus = polidy.gftComponfntBfforf(rootAndfstor, domp);
            if (toFodus == null) {
                toFodus = polidy.gftDffbultComponfnt(rootAndfstor);
            }
            if (toFodus != null) {
                rfs = toFodus.rfqufstFodusInWindow(CbusfdFodusEvfnt.Cbusf.TRAVERSAL_BACKWARD);
            }
        }
        if (dlfbrOnFbilurf && !rfs) {
            if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                fodusLog.finfr("dlfbr globbl fodus ownfr");
            }
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().dlfbrGlobblFodusOwnfrPriv();
        }
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr("rfturning rfsult: " + rfs);
        }
        rfturn rfs;
    }

    /**
     * Trbnsffrs tif fodus up onf fodus trbvfrsbl dydlf. Typidblly, tif fodus
     * ownfr is sft to tiis Componfnt's fodus dydlf root, bnd tif durrfnt fodus
     * dydlf root is sft to tif nfw fodus ownfr's fodus dydlf root. If,
     * iowfvfr, tiis Componfnt's fodus dydlf root is b Window, tifn tif fodus
     * ownfr is sft to tif fodus dydlf root's dffbult Componfnt to fodus, bnd
     * tif durrfnt fodus dydlf root is undibngfd.
     *
     * @sff       #rfqufstFodus()
     * @sff       Contbinfr#isFodusCydlfRoot()
     * @sff       Contbinfr#sftFodusCydlfRoot(boolfbn)
     * @sindf     1.4
     */
    publid void trbnsffrFodusUpCydlf() {
        Contbinfr rootAndfstor;
        for (rootAndfstor = gftFodusCydlfRootAndfstor();
             rootAndfstor != null && !(rootAndfstor.isSiowing() &&
                                       rootAndfstor.isFodusbblf() &&
                                       rootAndfstor.isEnbblfd());
             rootAndfstor = rootAndfstor.gftFodusCydlfRootAndfstor()) {
        }

        if (rootAndfstor != null) {
            Contbinfr rootAndfstorRootAndfstor =
                rootAndfstor.gftFodusCydlfRootAndfstor();
            Contbinfr fdr = (rootAndfstorRootAndfstor != null) ?
                rootAndfstorRootAndfstor : rootAndfstor;

            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                sftGlobblCurrfntFodusCydlfRootPriv(fdr);
            rootAndfstor.rfqufstFodus(CbusfdFodusEvfnt.Cbusf.TRAVERSAL_UP);
        } flsf {
            Window window = gftContbiningWindow();

            if (window != null) {
                Componfnt toFodus = window.gftFodusTrbvfrsblPolidy().
                    gftDffbultComponfnt(window);
                if (toFodus != null) {
                    KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                        sftGlobblCurrfntFodusCydlfRootPriv(window);
                    toFodus.rfqufstFodus(CbusfdFodusEvfnt.Cbusf.TRAVERSAL_UP);
                }
            }
        }
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>Componfnt</dodf> is tif
     * fodus ownfr.  Tiis mftiod is obsolftf, bnd ibs bffn rfplbdfd by
     * <dodf>isFodusOwnfr()</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>Componfnt</dodf> is tif
     *         fodus ownfr; <dodf>fblsf</dodf> otifrwisf
     * @sindf 1.2
     */
    publid boolfbn ibsFodus() {
        rfturn (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftFodusOwnfr() == tiis);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>Componfnt</dodf> is tif
     *    fodus ownfr.
     *
     * @rfturn <dodf>truf</dodf> if tiis <dodf>Componfnt</dodf> is tif
     *     fodus ownfr; <dodf>fblsf</dodf> otifrwisf
     * @sindf 1.4
     */
    publid boolfbn isFodusOwnfr() {
        rfturn ibsFodus();
    }

    /*
     * Usfd to disbllow buto-fodus-trbnsffr on disposbl of tif fodus ownfr
     * in tif prodfss of disposing its pbrfnt dontbinfr.
     */
    privbtf boolfbn butoFodusTrbnsffrOnDisposbl = truf;

    void sftAutoFodusTrbnsffrOnDisposbl(boolfbn vbluf) {
        butoFodusTrbnsffrOnDisposbl = vbluf;
    }

    boolfbn isAutoFodusTrbnsffrOnDisposbl() {
        rfturn butoFodusTrbnsffrOnDisposbl;
    }

    /**
     * Adds tif spfdififd popup mfnu to tif domponfnt.
     * @pbrbm     popup tif popup mfnu to bf bddfd to tif domponfnt.
     * @sff       #rfmovf(MfnuComponfnt)
     * @fxdfption NullPointfrExdfption if {@dodf popup} is {@dodf null}
     * @sindf     1.1
     */
    publid void bdd(PopupMfnu popup) {
        syndironizfd (gftTrffLodk()) {
            if (popup.pbrfnt != null) {
                popup.pbrfnt.rfmovf(popup);
            }
            if (popups == null) {
                popups = nfw Vfdtor<PopupMfnu>();
            }
            popups.bddElfmfnt(popup);
            popup.pbrfnt = tiis;

            if (pffr != null) {
                if (popup.pffr == null) {
                    popup.bddNotify();
                }
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd popup mfnu from tif domponfnt.
     * @pbrbm     popup tif popup mfnu to bf rfmovfd
     * @sff       #bdd(PopupMfnu)
     * @sindf     1.1
     */
    @SupprfssWbrnings("undifdkfd")
    publid void rfmovf(MfnuComponfnt popup) {
        syndironizfd (gftTrffLodk()) {
            if (popups == null) {
                rfturn;
            }
            int indfx = popups.indfxOf(popup);
            if (indfx >= 0) {
                PopupMfnu pmfnu = (PopupMfnu)popup;
                if (pmfnu.pffr != null) {
                    pmfnu.rfmovfNotify();
                }
                pmfnu.pbrfnt = null;
                popups.rfmovfElfmfntAt(indfx);
                if (popups.sizf() == 0) {
                    popups = null;
                }
            }
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis domponfnt. Tiis
     * mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis domponfnt's stbtf
     * @sindf     1.0
     */
    protfdtfd String pbrbmString() {
        finbl String tiisNbmf = Objfdts.toString(gftNbmf(), "");
        finbl String invblid = isVblid() ? "" : ",invblid";
        finbl String iiddfn = visiblf ? "" : ",iiddfn";
        finbl String disbblfd = fnbblfd ? "" : ",disbblfd";
        rfturn tiisNbmf + ',' + x + ',' + y + ',' + widti + 'x' + ifigit
                + invblid + iiddfn + disbblfd;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis domponfnt bnd its vblufs.
     * @rfturn    b string rfprfsfntbtion of tiis domponfnt
     * @sindf     1.0
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + '[' + pbrbmString() + ']';
    }

    /**
     * Prints b listing of tiis domponfnt to tif stbndbrd systfm output
     * strfbm <dodf>Systfm.out</dodf>.
     * @sff       jbvb.lbng.Systfm#out
     * @sindf     1.0
     */
    publid void list() {
        list(Systfm.out, 0);
    }

    /**
     * Prints b listing of tiis domponfnt to tif spfdififd output
     * strfbm.
     * @pbrbm    out   b print strfbm
     * @tirows   NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sindf    1.0
     */
    publid void list(PrintStrfbm out) {
        list(out, 0);
    }

    /**
     * Prints out b list, stbrting bt tif spfdififd indfntbtion, to tif
     * spfdififd print strfbm.
     * @pbrbm     out      b print strfbm
     * @pbrbm     indfnt   numbfr of spbdfs to indfnt
     * @sff       jbvb.io.PrintStrfbm#println(jbvb.lbng.Objfdt)
     * @tirows    NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sindf     1.0
     */
    publid void list(PrintStrfbm out, int indfnt) {
        for (int i = 0 ; i < indfnt ; i++) {
            out.print(" ");
        }
        out.println(tiis);
    }

    /**
     * Prints b listing to tif spfdififd print writfr.
     * @pbrbm  out  tif print writfr to print to
     * @tirows NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sindf 1.1
     */
    publid void list(PrintWritfr out) {
        list(out, 0);
    }

    /**
     * Prints out b list, stbrting bt tif spfdififd indfntbtion, to
     * tif spfdififd print writfr.
     * @pbrbm out tif print writfr to print to
     * @pbrbm indfnt tif numbfr of spbdfs to indfnt
     * @tirows NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sff       jbvb.io.PrintStrfbm#println(jbvb.lbng.Objfdt)
     * @sindf 1.1
     */
    publid void list(PrintWritfr out, int indfnt) {
        for (int i = 0 ; i < indfnt ; i++) {
            out.print(" ");
        }
        out.println(tiis);
    }

    /*
     * Fftdifs tif nbtivf dontbinfr somfwifrf iigifr up in tif domponfnt
     * trff tibt dontbins tiis domponfnt.
     */
    finbl Contbinfr gftNbtivfContbinfr() {
        Contbinfr p = gftContbinfr();
        wiilf (p != null && p.pffr instbndfof LigitwfigitPffr) {
            p = p.gftContbinfr();
        }
        rfturn p;
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss, indluding tif
     * following:
     * <ul>
     *    <li>tiis Componfnt's font ("font")</li>
     *    <li>tiis Componfnt's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Componfnt's forfground dolor ("forfground")</li>
     *    <li>tiis Componfnt's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Componfnt's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Componfnt's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Componfnt's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Componfnt's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Componfnt's prfffrrfd sizf ("prfffrrfdSizf")</li>
     *    <li>tiis Componfnt's minimum sizf ("minimumSizf")</li>
     *    <li>tiis Componfnt's mbximum sizf ("mbximumSizf")</li>
     *    <li>tiis Componfnt's nbmf ("nbmf")</li>
     * </ul>
     * Notf tibt if tiis <dodf>Componfnt</dodf> is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If <dodf>listfnfr</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr  tif propfrty dibngf listfnfr to bf bddfd
     *
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void bddPropfrtyCibngfListfnfr(
                                                       PropfrtyCibngfListfnfr listfnfr) {
        syndironizfd (gftObjfdtLodk()) {
            if (listfnfr == null) {
                rfturn;
            }
            if (dibngfSupport == null) {
                dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
            }
            dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
        }
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list. Tiis mftiod
     * siould bf usfd to rfmovf PropfrtyCibngfListfnfrs tibt wfrf rfgistfrfd
     * for bll bound propfrtifs of tiis dlbss.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void rfmovfPropfrtyCibngfListfnfr(
                                                          PropfrtyCibngfListfnfr listfnfr) {
        syndironizfd (gftObjfdtLodk()) {
            if (listfnfr == null || dibngfSupport == null) {
                rfturn;
            }
            dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>PropfrtyCibngfListfnfr</dodf>s
     *         or bn fmpty brrby if no propfrty dibngf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddPropfrtyCibngfListfnfr
     * @sff      #rfmovfPropfrtyCibngfListfnfr
     * @sff      #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff      jbvb.bfbns.PropfrtyCibngfSupport#gftPropfrtyCibngfListfnfrs
     * @sindf    1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        syndironizfd (gftObjfdtLodk()) {
            if (dibngfSupport == null) {
                rfturn nfw PropfrtyCibngfListfnfr[0];
            }
            rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
        }
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf usfr-dffinfd, or onf of tif
     * following:
     * <ul>
     *    <li>tiis Componfnt's font ("font")</li>
     *    <li>tiis Componfnt's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Componfnt's forfground dolor ("forfground")</li>
     *    <li>tiis Componfnt's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Componfnt's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Componfnt's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Componfnt's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Componfnt's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     * </ul>
     * Notf tibt if tiis <dodf>Componfnt</dodf> is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If <dodf>propfrtyNbmf</dodf> or <dodf>listfnfr</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif propfrty dibngf listfnfr to bf bddfd
     *
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void bddPropfrtyCibngfListfnfr(
                                                       String propfrtyNbmf,
                                                       PropfrtyCibngfListfnfr listfnfr) {
        syndironizfd (gftObjfdtLodk()) {
            if (listfnfr == null) {
                rfturn;
            }
            if (dibngfSupport == null) {
                dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
            }
            dibngfSupport.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
        }
    }

    /**
     * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tif listfnfr
     * list for b spfdifid propfrty. Tiis mftiod siould bf usfd to rfmovf
     * <dodf>PropfrtyCibngfListfnfr</dodf>s
     * tibt wfrf rfgistfrfd for b spfdifid bound propfrty.
     * <p>
     * If <dodf>propfrtyNbmf</dodf> or <dodf>listfnfr</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm propfrtyNbmf b vblid propfrty nbmf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void rfmovfPropfrtyCibngfListfnfr(
                                                          String propfrtyNbmf,
                                                          PropfrtyCibngfListfnfr listfnfr) {
        syndironizfd (gftObjfdtLodk()) {
            if (listfnfr == null || dibngfSupport == null) {
                rfturn;
            }
            dibngfSupport.rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif listfnfrs wiidi ibvf bffn bssodibtfd
     * witi tif nbmfd propfrty.
     *
     * @pbrbm  propfrtyNbmf tif propfrty nbmf
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfr</dodf>s bssodibtfd witi
     *         tif nbmfd propfrty; if no sudi listfnfrs ibvf bffn bddfd or
     *         if <dodf>propfrtyNbmf</dodf> is <dodf>null</dodf>, bn fmpty
     *         brrby is rfturnfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf) {
        syndironizfd (gftObjfdtLodk()) {
            if (dibngfSupport == null) {
                rfturn nfw PropfrtyCibngfListfnfr[0];
            }
            rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
        }
    }

    /**
     * Support for rfporting bound propfrty dibngfs for Objfdt propfrtifs.
     * Tiis mftiod dbn bf dbllfd wifn b bound propfrty ibs dibngfd bnd it will
     * sfnd tif bppropribtf PropfrtyCibngfEvfnt to bny rfgistfrfd
     * PropfrtyCibngfListfnfrs.
     *
     * @pbrbm propfrtyNbmf tif propfrty wiosf vbluf ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf,
                                      Objfdt oldVbluf, Objfdt nfwVbluf) {
        PropfrtyCibngfSupport dibngfSupport;
        syndironizfd (gftObjfdtLodk()) {
            dibngfSupport = tiis.dibngfSupport;
        }
        if (dibngfSupport == null ||
            (oldVbluf != null && nfwVbluf != null && oldVbluf.fqubls(nfwVbluf))) {
            rfturn;
        }
        dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
    }

    /**
     * Support for rfporting bound propfrty dibngfs for boolfbn propfrtifs.
     * Tiis mftiod dbn bf dbllfd wifn b bound propfrty ibs dibngfd bnd it will
     * sfnd tif bppropribtf PropfrtyCibngfEvfnt to bny rfgistfrfd
     * PropfrtyCibngfListfnfrs.
     *
     * @pbrbm propfrtyNbmf tif propfrty wiosf vbluf ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     * @sindf 1.4
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf,
                                      boolfbn oldVbluf, boolfbn nfwVbluf) {
        PropfrtyCibngfSupport dibngfSupport = tiis.dibngfSupport;
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
    }

    /**
     * Support for rfporting bound propfrty dibngfs for intfgfr propfrtifs.
     * Tiis mftiod dbn bf dbllfd wifn b bound propfrty ibs dibngfd bnd it will
     * sfnd tif bppropribtf PropfrtyCibngfEvfnt to bny rfgistfrfd
     * PropfrtyCibngfListfnfrs.
     *
     * @pbrbm propfrtyNbmf tif propfrty wiosf vbluf ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     * @sindf 1.4
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf,
                                      int oldVbluf, int nfwVbluf) {
        PropfrtyCibngfSupport dibngfSupport = tiis.dibngfSupport;
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
    }

    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b bytf)
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty (bs b bytf)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, bytf oldVbluf, bytf nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Bytf.vblufOf(oldVbluf), Bytf.vblufOf(nfwVbluf));
    }

    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b dibr)
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty (bs b dibr)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, dibr oldVbluf, dibr nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Cibrbdtfr.vblufOf(oldVbluf), Cibrbdtfr.vblufOf(nfwVbluf));
    }

    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b siort)
     * @pbrbm nfwVbluf tif old vbluf of tif propfrty (bs b siort)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, siort oldVbluf, siort nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Siort.vblufOf(oldVbluf), Siort.vblufOf(nfwVbluf));
    }


    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b long)
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty (bs b long)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, long oldVbluf, long nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Long.vblufOf(oldVbluf), Long.vblufOf(nfwVbluf));
    }

    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b flobt)
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty (bs b flobt)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, flobt oldVbluf, flobt nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Flobt.vblufOf(oldVbluf), Flobt.vblufOf(nfwVbluf));
    }

    /**
     * Rfports b bound propfrty dibngf.
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty
     *          tibt wbs dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty (bs b doublf)
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty (bs b doublf)
     * @sff #firfPropfrtyCibngf(jbvb.lbng.String, jbvb.lbng.Objfdt,
     *          jbvb.lbng.Objfdt)
     * @sindf 1.5
     */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, doublf oldVbluf, doublf nfwVbluf) {
        if (dibngfSupport == null || oldVbluf == nfwVbluf) {
            rfturn;
        }
        firfPropfrtyCibngf(propfrtyNbmf, Doublf.vblufOf(oldVbluf), Doublf.vblufOf(nfwVbluf));
    }


    // Sfriblizbtion support.

    /**
     * Componfnt Sfriblizfd Dbtb Vfrsion.
     *
     * @sfribl
     */
    privbtf int domponfntSfriblizfdDbtbVfrsion = 4;

    /**
     * Tiis ibdk is for Swing sfriblizbtion. It will invokf
     * tif Swing pbdkbgf privbtf mftiod <dodf>dompWritfObjfdtNotify</dodf>.
     */
    privbtf void doSwingSfriblizbtion() {
        Pbdkbgf swingPbdkbgf = Pbdkbgf.gftPbdkbgf("jbvbx.swing");
        // For Swing sfriblizbtion to dorrfdtly work Swing nffds to
        // bf notififd bfforf Componfnt dofs it's sfriblizbtion.  Tiis
        // ibdk bddomodbtfs tiis.
        //
        // Swing dlbssfs MUST bf lobdfd by tif bootstrbp dlbss lobdfr,
        // otifrwisf wf don't donsidfr tifm.
        for (Clbss<?> klbss = Componfnt.tiis.gftClbss(); klbss != null;
                   klbss = klbss.gftSupfrdlbss()) {
            if (klbss.gftPbdkbgf() == swingPbdkbgf &&
                      klbss.gftClbssLobdfr() == null) {
                finbl Clbss<?> swingClbss = klbss;
                // Find tif first ovfrridf of tif dompWritfObjfdtNotify mftiod
                Mftiod[] mftiods = AddfssControllfr.doPrivilfgfd(
                                                                 nfw PrivilfgfdAdtion<Mftiod[]>() {
                                                                     publid Mftiod[] run() {
                                                                         rfturn swingClbss.gftDfdlbrfdMftiods();
                                                                     }
                                                                 });
                for (int dountfr = mftiods.lfngti - 1; dountfr >= 0;
                     dountfr--) {
                    finbl Mftiod mftiod = mftiods[dountfr];
                    if (mftiod.gftNbmf().fqubls("dompWritfObjfdtNotify")){
                        // Wf found it, usf doPrivilfgfd to mbkf it bddfssiblf
                        // to usf.
                        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                                publid Void run() {
                                    mftiod.sftAddfssiblf(truf);
                                    rfturn null;
                                }
                            });
                        // Invokf tif mftiod
                        try {
                            mftiod.invokf(tiis, (Objfdt[]) null);
                        } dbtdi (IllfgblAddfssExdfption ibf) {
                        } dbtdi (InvodbtionTbrgftExdfption itf) {
                        }
                        // Wf'rf donf, bbil.
                        rfturn;
                    }
                }
            }
        }
    }

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b vbrifty of sfriblizbblf listfnfrs bs optionbl dbtb.
     * Tif non-sfriblizbblf listfnfrs brf dftfdtfd bnd
     * no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sfriblDbtb <dodf>null</dodf> tfrminbtfd sfqufndf of
     *   0 or morf pbirs; tif pbir donsists of b <dodf>String</dodf>
     *   bnd bn <dodf>Objfdt</dodf>; tif <dodf>String</dodf> indidbtfs
     *   tif typf of objfdt bnd is onf of tif following (bs of 1.4):
     *   <dodf>domponfntListfnfrK</dodf> indidbting bn
     *     <dodf>ComponfntListfnfr</dodf> objfdt;
     *   <dodf>fodusListfnfrK</dodf> indidbting bn
     *     <dodf>FodusListfnfr</dodf> objfdt;
     *   <dodf>kfyListfnfrK</dodf> indidbting bn
     *     <dodf>KfyListfnfr</dodf> objfdt;
     *   <dodf>mousfListfnfrK</dodf> indidbting bn
     *     <dodf>MousfListfnfr</dodf> objfdt;
     *   <dodf>mousfMotionListfnfrK</dodf> indidbting bn
     *     <dodf>MousfMotionListfnfr</dodf> objfdt;
     *   <dodf>inputMftiodListfnfrK</dodf> indidbting bn
     *     <dodf>InputMftiodListfnfr</dodf> objfdt;
     *   <dodf>iifrbrdiyListfnfrK</dodf> indidbting bn
     *     <dodf>HifrbrdiyListfnfr</dodf> objfdt;
     *   <dodf>iifrbrdiyBoundsListfnfrK</dodf> indidbting bn
     *     <dodf>HifrbrdiyBoundsListfnfr</dodf> objfdt;
     *   <dodf>mousfWifflListfnfrK</dodf> indidbting bn
     *     <dodf>MousfWifflListfnfr</dodf> objfdt
     * @sfriblDbtb bn optionbl <dodf>ComponfntOrifntbtion</dodf>
     *    (bftfr <dodf>inputMftiodListfnfr</dodf>, bs of 1.2)
     *
     * @sff AWTEvfntMultidbstfr#sbvf(jbvb.io.ObjfdtOutputStrfbm, jbvb.lbng.String, jbvb.util.EvfntListfnfr)
     * @sff #domponfntListfnfrK
     * @sff #fodusListfnfrK
     * @sff #kfyListfnfrK
     * @sff #mousfListfnfrK
     * @sff #mousfMotionListfnfrK
     * @sff #inputMftiodListfnfrK
     * @sff #iifrbrdiyListfnfrK
     * @sff #iifrbrdiyBoundsListfnfrK
     * @sff #mousfWifflListfnfrK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
      tirows IOExdfption
    {
        doSwingSfriblizbtion();

        s.dffbultWritfObjfdt();

        AWTEvfntMultidbstfr.sbvf(s, domponfntListfnfrK, domponfntListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, fodusListfnfrK, fodusListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, kfyListfnfrK, kfyListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, mousfListfnfrK, mousfListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, mousfMotionListfnfrK, mousfMotionListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, inputMftiodListfnfrK, inputMftiodListfnfr);

        s.writfObjfdt(null);
        s.writfObjfdt(domponfntOrifntbtion);

        AWTEvfntMultidbstfr.sbvf(s, iifrbrdiyListfnfrK, iifrbrdiyListfnfr);
        AWTEvfntMultidbstfr.sbvf(s, iifrbrdiyBoundsListfnfrK,
                                 iifrbrdiyBoundsListfnfr);
        s.writfObjfdt(null);

        AWTEvfntMultidbstfr.sbvf(s, mousfWifflListfnfrK, mousfWifflListfnfr);
        s.writfObjfdt(null);

    }

    /**
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf> bnd if it isn't
     * <dodf>null</dodf> bdds b listfnfr to rfdfivf b vbrifty
     * of fvfnts firfd by tif domponfnt.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @sff #writfObjfdt(ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption
    {
        objfdtLodk = nfw Objfdt();

        bdd = AddfssControllfr.gftContfxt();

        s.dffbultRfbdObjfdt();

        bppContfxt = AppContfxt.gftAppContfxt();
        doblfsdingEnbblfd = difdkCoblfsding();
        if (domponfntSfriblizfdDbtbVfrsion < 4) {
            // Tifsf fiflds brf non-trbnsifnt bnd rfly on dffbult
            // sfriblizbtion. Howfvfr, tif dffbult vblufs brf insuffidifnt,
            // so wf nffd to sft tifm fxpliditly for objfdt dbtb strfbms prior
            // to 1.4.
            fodusbblf = truf;
            isFodusTrbvfrsbblfOvfrriddfn = FOCUS_TRAVERSABLE_UNKNOWN;
            initiblizfFodusTrbvfrsblKfys();
            fodusTrbvfrsblKfysEnbblfd = truf;
        }

        Objfdt kfyOrNull;
        wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (domponfntListfnfrK == kfy)
                bddComponfntListfnfr((ComponfntListfnfr)(s.rfbdObjfdt()));

            flsf if (fodusListfnfrK == kfy)
                bddFodusListfnfr((FodusListfnfr)(s.rfbdObjfdt()));

            flsf if (kfyListfnfrK == kfy)
                bddKfyListfnfr((KfyListfnfr)(s.rfbdObjfdt()));

            flsf if (mousfListfnfrK == kfy)
                bddMousfListfnfr((MousfListfnfr)(s.rfbdObjfdt()));

            flsf if (mousfMotionListfnfrK == kfy)
                bddMousfMotionListfnfr((MousfMotionListfnfr)(s.rfbdObjfdt()));

            flsf if (inputMftiodListfnfrK == kfy)
                bddInputMftiodListfnfr((InputMftiodListfnfr)(s.rfbdObjfdt()));

            flsf // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();

        }

        // Rfbd tif domponfnt's orifntbtion if it's prfsfnt
        Objfdt orifnt = null;

        try {
            orifnt = s.rfbdObjfdt();
        } dbtdi (jbvb.io.OptionblDbtbExdfption f) {
            // JDK 1.1 instbndfs will not ibvf tiis optionbl dbtb.
            // f.fof will bf truf to indidbtf tibt tifrf is no morf
            // dbtb bvbilbblf for tiis objfdt.
            // If f.fof is not truf, tirow tif fxdfption bs it
            // migit ibvf bffn dbusfd by rfbsons unrflbtfd to
            // domponfntOrifntbtion.

            if (!f.fof)  {
                tirow (f);
            }
        }

        if (orifnt != null) {
            domponfntOrifntbtion = (ComponfntOrifntbtion)orifnt;
        } flsf {
            domponfntOrifntbtion = ComponfntOrifntbtion.UNKNOWN;
        }

        try {
            wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
                String kfy = ((String)kfyOrNull).intfrn();

                if (iifrbrdiyListfnfrK == kfy) {
                    bddHifrbrdiyListfnfr((HifrbrdiyListfnfr)(s.rfbdObjfdt()));
                }
                flsf if (iifrbrdiyBoundsListfnfrK == kfy) {
                    bddHifrbrdiyBoundsListfnfr((HifrbrdiyBoundsListfnfr)
                                               (s.rfbdObjfdt()));
                }
                flsf {
                    // skip vbluf for unrfdognizfd kfy
                    s.rfbdObjfdt();
                }
            }
        } dbtdi (jbvb.io.OptionblDbtbExdfption f) {
            // JDK 1.1/1.2 instbndfs will not ibvf tiis optionbl dbtb.
            // f.fof will bf truf to indidbtf tibt tifrf is no morf
            // dbtb bvbilbblf for tiis objfdt.
            // If f.fof is not truf, tirow tif fxdfption bs it
            // migit ibvf bffn dbusfd by rfbsons unrflbtfd to
            // iifrbrdiy bnd iifrbrdiyBounds listfnfrs.

            if (!f.fof)  {
                tirow (f);
            }
        }

        try {
            wiilf (null != (kfyOrNull = s.rfbdObjfdt())) {
                String kfy = ((String)kfyOrNull).intfrn();

                if (mousfWifflListfnfrK == kfy) {
                    bddMousfWifflListfnfr((MousfWifflListfnfr)(s.rfbdObjfdt()));
                }
                flsf {
                    // skip vbluf for unrfdognizfd kfy
                    s.rfbdObjfdt();
                }
            }
        } dbtdi (jbvb.io.OptionblDbtbExdfption f) {
            // prf-1.3 instbndfs will not ibvf tiis optionbl dbtb.
            // f.fof will bf truf to indidbtf tibt tifrf is no morf
            // dbtb bvbilbblf for tiis objfdt.
            // If f.fof is not truf, tirow tif fxdfption bs it
            // migit ibvf bffn dbusfd by rfbsons unrflbtfd to
            // mousf wiffl listfnfrs

            if (!f.fof)  {
                tirow (f);
            }
        }

        if (popups != null) {
            int npopups = popups.sizf();
            for (int i = 0 ; i < npopups ; i++) {
                PopupMfnu popup = popups.flfmfntAt(i);
                popup.pbrfnt = tiis;
            }
        }
    }

    /**
     * Sfts tif lbngubgf-sfnsitivf orifntbtion tibt is to bf usfd to ordfr
     * tif flfmfnts or tfxt witiin tiis domponfnt.  Lbngubgf-sfnsitivf
     * <dodf>LbyoutMbnbgfr</dodf> bnd <dodf>Componfnt</dodf>
     * subdlbssfs will usf tiis propfrty to
     * dftfrminf iow to lby out bnd drbw domponfnts.
     * <p>
     * At donstrudtion timf, b domponfnt's orifntbtion is sft to
     * <dodf>ComponfntOrifntbtion.UNKNOWN</dodf>,
     * indidbting tibt it ibs not bffn spfdififd
     * fxpliditly.  Tif UNKNOWN orifntbtion bfibvfs tif sbmf bs
     * <dodf>ComponfntOrifntbtion.LEFT_TO_RIGHT</dodf>.
     * <p>
     * To sft tif orifntbtion of b singlf domponfnt, usf tiis mftiod.
     * To sft tif orifntbtion of bn fntirf domponfnt
     * iifrbrdiy, usf
     * {@link #bpplyComponfntOrifntbtion bpplyComponfntOrifntbtion}.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm  o tif orifntbtion to bf sft
     *
     * @sff ComponfntOrifntbtion
     * @sff #invblidbtf
     *
     * @butior Lburb Wfrnfr, IBM
     * @bfbninfo
     *       bound: truf
     */
    publid void sftComponfntOrifntbtion(ComponfntOrifntbtion o) {
        ComponfntOrifntbtion oldVbluf = domponfntOrifntbtion;
        domponfntOrifntbtion = o;

        // Tiis is b bound propfrty, so rfport tif dibngf to
        // bny rfgistfrfd listfnfrs.  (Cifbp if tifrf brf nonf.)
        firfPropfrtyCibngf("domponfntOrifntbtion", oldVbluf, o);

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        invblidbtfIfVblid();
    }

    /**
     * Rftrifvfs tif lbngubgf-sfnsitivf orifntbtion tibt is to bf usfd to ordfr
     * tif flfmfnts or tfxt witiin tiis domponfnt.  <dodf>LbyoutMbnbgfr</dodf>
     * bnd <dodf>Componfnt</dodf>
     * subdlbssfs tibt wisi to rfspfdt orifntbtion siould dbll tiis mftiod to
     * gft tif domponfnt's orifntbtion bfforf pfrforming lbyout or drbwing.
     *
     * @rfturn tif orifntbtion to ordfr tif flfmfnts or tfxt
     * @sff ComponfntOrifntbtion
     *
     * @butior Lburb Wfrnfr, IBM
     */
    publid ComponfntOrifntbtion gftComponfntOrifntbtion() {
        rfturn domponfntOrifntbtion;
    }

    /**
     * Sfts tif <dodf>ComponfntOrifntbtion</dodf> propfrty of tiis domponfnt
     * bnd bll domponfnts dontbinfd witiin it.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     *
     * @pbrbm orifntbtion tif nfw domponfnt orifntbtion of tiis domponfnt bnd
     *        tif domponfnts dontbinfd witiin it.
     * @fxdfption NullPointfrExdfption if <dodf>orifntbtion</dodf> is null.
     * @sff #sftComponfntOrifntbtion
     * @sff #gftComponfntOrifntbtion
     * @sff #invblidbtf
     * @sindf 1.4
     */
    publid void bpplyComponfntOrifntbtion(ComponfntOrifntbtion orifntbtion) {
        if (orifntbtion == null) {
            tirow nfw NullPointfrExdfption();
        }
        sftComponfntOrifntbtion(orifntbtion);
    }

    finbl boolfbn dbnBfFodusOwnfr() {
        // It is fnbblfd, visiblf, fodusbblf.
        if (isEnbblfd() && isDisplbybblf() && isVisiblf() && isFodusbblf()) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Cifdks tibt tiis domponfnt mffts tif prfrfqufsitfs to bf fodus ownfr:
     * - it is fnbblfd, visiblf, fodusbblf
     * - it's pbrfnts brf bll fnbblfd bnd siowing
     * - top-lfvfl window is fodusbblf
     * - if fodus dydlf root ibs DffbultFodusTrbvfrsblPolidy tifn it blso difdks tibt tiis polidy bddfpts
     * tiis domponfnt bs fodus ownfr
     * @sindf 1.5
     */
    finbl boolfbn dbnBfFodusOwnfrRfdursivfly() {
        // - it is fnbblfd, visiblf, fodusbblf
        if (!dbnBfFodusOwnfr()) {
            rfturn fblsf;
        }

        // - it's pbrfnts brf bll fnbblfd bnd siowing
        syndironizfd(gftTrffLodk()) {
            if (pbrfnt != null) {
                rfturn pbrfnt.dbnContbinFodusOwnfr(tiis);
            }
        }
        rfturn truf;
    }

    /**
     * Fix tif lodbtion of tif HW domponfnt in b LW dontbinfr iifrbrdiy.
     */
    finbl void rflodbtfComponfnt() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null) {
                rfturn;
            }
            int nbtivfX = x;
            int nbtivfY = y;
            for (Componfnt dont = gftContbinfr();
                    dont != null && dont.isLigitwfigit();
                    dont = dont.gftContbinfr())
            {
                nbtivfX += dont.x;
                nbtivfY += dont.y;
            }
            pffr.sftBounds(nbtivfX, nbtivfY, widti, ifigit,
                    ComponfntPffr.SET_LOCATION);
        }
    }

    /**
     * Rfturns tif <dodf>Window</dodf> bndfstor of tif domponfnt.
     * @rfturn Window bndfstor of tif domponfnt or domponfnt by itsflf if it is Window;
     *         null, if domponfnt is not b pbrt of window iifrbrdiy
     */
    Window gftContbiningWindow() {
        rfturn SunToolkit.gftContbiningWindow(tiis);
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    /*
     * --- Addfssibility Support ---
     *
     *  Componfnt will dontbin bll of tif mftiods in intfrfbdf Addfssiblf,
     *  tiougi it won't bdtublly implfmfnt tif intfrfbdf - tibt will bf up
     *  to tif individubl objfdts wiidi fxtfnd Componfnt.
     */

    /**
     * Tif {@dodf AddfssiblfContfxt} bssodibtfd witi tiis {@dodf Componfnt}.
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd
     * witi tiis <dodf>Componfnt</dodf>.
     * Tif mftiod implfmfntfd by tiis bbsf
     * dlbss rfturns null.  Clbssfs tibt fxtfnd <dodf>Componfnt</dodf>
     * siould implfmfnt tiis mftiod to rfturn tif
     * <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tif subdlbss.
     *
     *
     * @rfturn tif <dodf>AddfssiblfContfxt</dodf> of tiis
     *    <dodf>Componfnt</dodf>
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        rfturn bddfssiblfContfxt;
    }

    /**
     * Innfr dlbss of Componfnt usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by domponfnt dfvflopfrs.
     * <p>
     * Tif dlbss usfd to obtbin tif bddfssiblf rolf for tiis objfdt.
     * @sindf 1.3
     */
    protfdtfd bbstrbdt dlbss AddfssiblfAWTComponfnt fxtfnds AddfssiblfContfxt
        implfmfnts Sfriblizbblf, AddfssiblfComponfnt {

        privbtf stbtid finbl long sfriblVfrsionUID = 642321655757800191L;

        /**
         * Tiougi tif dlbss is bbstrbdt, tiis siould bf dbllfd by
         * bll sub-dlbssfs.
         */
        protfdtfd AddfssiblfAWTComponfnt() {
        }

        /**
         * Numbfr of PropfrtyCibngfListfnfr objfdts rfgistfrfd. It's usfd
         * to bdd/rfmovf ComponfntListfnfr bnd FodusListfnfr to trbdk
         * tbrgft Componfnt's stbtf.
         */
        privbtf volbtilf trbnsifnt int propfrtyListfnfrsCount = 0;

        /**
         * A domponfnt listfnfr to trbdk siow/iidf/rfsizf fvfnts
         * bnd donvfrt tifm to PropfrtyCibngf fvfnts.
         */
        protfdtfd ComponfntListfnfr bddfssiblfAWTComponfntHbndlfr = null;

        /**
         * A listfnfr to trbdk fodus fvfnts
         * bnd donvfrt tifm to PropfrtyCibngf fvfnts.
         */
        protfdtfd FodusListfnfr bddfssiblfAWTFodusHbndlfr = null;

        /**
         * Firf PropfrtyCibngf listfnfr, if onf is rfgistfrfd,
         * wifn siown/iiddfn..
         * @sindf 1.3
         */
        protfdtfd dlbss AddfssiblfAWTComponfntHbndlfr implfmfnts ComponfntListfnfr {
            publid void domponfntHiddfn(ComponfntEvfnt f)  {
                if (bddfssiblfContfxt != null) {
                    bddfssiblfContfxt.firfPropfrtyCibngf(
                                                         AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                         AddfssiblfStbtf.VISIBLE, null);
                }
            }

            publid void domponfntSiown(ComponfntEvfnt f)  {
                if (bddfssiblfContfxt != null) {
                    bddfssiblfContfxt.firfPropfrtyCibngf(
                                                         AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                         null, AddfssiblfStbtf.VISIBLE);
                }
            }

            publid void domponfntMovfd(ComponfntEvfnt f)  {
            }

            publid void domponfntRfsizfd(ComponfntEvfnt f)  {
            }
        } // innfr dlbss AddfssiblfAWTComponfntHbndlfr


        /**
         * Firf PropfrtyCibngf listfnfr, if onf is rfgistfrfd,
         * wifn fodus fvfnts ibppfn
         * @sindf 1.3
         */
        protfdtfd dlbss AddfssiblfAWTFodusHbndlfr implfmfnts FodusListfnfr {
            publid void fodusGbinfd(FodusEvfnt fvfnt) {
                if (bddfssiblfContfxt != null) {
                    bddfssiblfContfxt.firfPropfrtyCibngf(
                                                         AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                         null, AddfssiblfStbtf.FOCUSED);
                }
            }
            publid void fodusLost(FodusEvfnt fvfnt) {
                if (bddfssiblfContfxt != null) {
                    bddfssiblfContfxt.firfPropfrtyCibngf(
                                                         AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                         AddfssiblfStbtf.FOCUSED, null);
                }
            }
        }  // innfr dlbss AddfssiblfAWTFodusHbndlfr


        /**
         * Adds b <dodf>PropfrtyCibngfListfnfr</dodf> to tif listfnfr list.
         *
         * @pbrbm listfnfr  tif propfrty dibngf listfnfr to bf bddfd
         */
        publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            if (bddfssiblfAWTComponfntHbndlfr == null) {
                bddfssiblfAWTComponfntHbndlfr = nfw AddfssiblfAWTComponfntHbndlfr();
            }
            if (bddfssiblfAWTFodusHbndlfr == null) {
                bddfssiblfAWTFodusHbndlfr = nfw AddfssiblfAWTFodusHbndlfr();
            }
            if (propfrtyListfnfrsCount++ == 0) {
                Componfnt.tiis.bddComponfntListfnfr(bddfssiblfAWTComponfntHbndlfr);
                Componfnt.tiis.bddFodusListfnfr(bddfssiblfAWTFodusHbndlfr);
            }
            supfr.bddPropfrtyCibngfListfnfr(listfnfr);
        }

        /**
         * Rfmovf b PropfrtyCibngfListfnfr from tif listfnfr list.
         * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
         * for bll propfrtifs.
         *
         * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf rfmovfd
         */
        publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            if (--propfrtyListfnfrsCount == 0) {
                Componfnt.tiis.rfmovfComponfntListfnfr(bddfssiblfAWTComponfntHbndlfr);
                Componfnt.tiis.rfmovfFodusListfnfr(bddfssiblfAWTFodusHbndlfr);
            }
            supfr.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }

        // AddfssiblfContfxt mftiods
        //
        /**
         * Gfts tif bddfssiblf nbmf of tiis objfdt.  Tiis siould blmost nfvfr
         * rfturn <dodf>jbvb.bwt.Componfnt.gftNbmf()</dodf>,
         * bs tibt gfnfrblly isn't b lodblizfd nbmf,
         * bnd dofsn't ibvf mfbning for tif usfr.  If tif
         * objfdt is fundbmfntblly b tfxt objfdt (f.g. b mfnu itfm), tif
         * bddfssiblf nbmf siould bf tif tfxt of tif objfdt (f.g. "sbvf").
         * If tif objfdt ibs b tooltip, tif tooltip tfxt mby blso bf bn
         * bppropribtf String to rfturn.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf
         *         <dodf>null</dodf> if tiis
         *         objfdt dofs not ibvf b nbmf
         * @sff jbvbx.bddfssibility.AddfssiblfContfxt#sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            rfturn bddfssiblfNbmf;
        }

        /**
         * Gfts tif bddfssiblf dfsdription of tiis objfdt.  Tiis siould bf
         * b dondisf, lodblizfd dfsdription of wibt tiis objfdt is - wibt
         * is its mfbning to tif usfr.  If tif objfdt ibs b tooltip, tif
         * tooltip tfxt mby bf bn bppropribtf string to rfturn, bssuming
         * it dontbins b dondisf dfsdription of tif objfdt (instfbd of just
         * tif nbmf of tif objfdt - f.g. b "Sbvf" idon on b toolbbr tibt
         * ibd "sbvf" bs tif tooltip tfxt siouldn't rfturn tif tooltip
         * tfxt bs tif dfsdription, but somftiing likf "Sbvfs tif durrfnt
         * tfxt dodumfnt" instfbd).
         *
         * @rfturn tif lodblizfd dfsdription of tif objfdt -- dbn bf
         *        <dodf>null</dodf> if tiis objfdt dofs not ibvf b dfsdription
         * @sff jbvbx.bddfssibility.AddfssiblfContfxt#sftAddfssiblfDfsdription
         */
        publid String gftAddfssiblfDfsdription() {
            rfturn bddfssiblfDfsdription;
        }

        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfRolf</dodf>
         *      dfsdribing tif rolf of tif objfdt
         * @sff jbvbx.bddfssibility.AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.AWT_COMPONENT;
        }

        /**
         * Gfts tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
         *       dontbining tif durrfnt stbtf sft of tif objfdt
         * @sff jbvbx.bddfssibility.AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            rfturn Componfnt.tiis.gftAddfssiblfStbtfSft();
        }

        /**
         * Gfts tif <dodf>Addfssiblf</dodf> pbrfnt of tiis objfdt.
         * If tif pbrfnt of tiis objfdt implfmfnts <dodf>Addfssiblf</dodf>,
         * tiis mftiod siould simply rfturn <dodf>gftPbrfnt</dodf>.
         *
         * @rfturn tif <dodf>Addfssiblf</dodf> pbrfnt of tiis
         *      objfdt -- dbn bf <dodf>null</dodf> if tiis
         *      objfdt dofs not ibvf bn <dodf>Addfssiblf</dodf> pbrfnt
         */
        publid Addfssiblf gftAddfssiblfPbrfnt() {
            if (bddfssiblfPbrfnt != null) {
                rfturn bddfssiblfPbrfnt;
            } flsf {
                Contbinfr pbrfnt = gftPbrfnt();
                if (pbrfnt instbndfof Addfssiblf) {
                    rfturn (Addfssiblf) pbrfnt;
                }
            }
            rfturn null;
        }

        /**
         * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
         *
         * @rfturn tif indfx of tiis objfdt in its pbrfnt; or -1 if tiis
         *    objfdt dofs not ibvf bn bddfssiblf pbrfnt
         * @sff #gftAddfssiblfPbrfnt
         */
        publid int gftAddfssiblfIndfxInPbrfnt() {
            rfturn Componfnt.tiis.gftAddfssiblfIndfxInPbrfnt();
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt <dodf>Addfssiblf</dodf>,
         * tifn tiis mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn 0; // Componfnts don't ibvf diildrfn
        }

        /**
         * Rfturns tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            rfturn null; // Componfnts don't ibvf diildrfn
        }

        /**
         * Rfturns tif lodblf of tiis objfdt.
         *
         * @rfturn tif lodblf of tiis objfdt
         */
        publid Lodblf gftLodblf() {
            rfturn Componfnt.tiis.gftLodblf();
        }

        /**
         * Gfts tif <dodf>AddfssiblfComponfnt</dodf> bssodibtfd
         * witi tiis objfdt if onf fxists.
         * Otifrwisf rfturn <dodf>null</dodf>.
         *
         * @rfturn tif domponfnt
         */
        publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
            rfturn tiis;
        }


        // AddfssiblfComponfnt mftiods
        //
        /**
         * Gfts tif bbdkground dolor of tiis objfdt.
         *
         * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
         *      otifrwisf, <dodf>null</dodf>
         */
        publid Color gftBbdkground() {
            rfturn Componfnt.tiis.gftBbdkground();
        }

        /**
         * Sfts tif bbdkground dolor of tiis objfdt.
         * (For trbnspbrfndy, sff <dodf>isOpbquf</dodf>.)
         *
         * @pbrbm d tif nfw <dodf>Color</dodf> for tif bbdkground
         * @sff Componfnt#isOpbquf
         */
        publid void sftBbdkground(Color d) {
            Componfnt.tiis.sftBbdkground(d);
        }

        /**
         * Gfts tif forfground dolor of tiis objfdt.
         *
         * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
         *     otifrwisf, <dodf>null</dodf>
         */
        publid Color gftForfground() {
            rfturn Componfnt.tiis.gftForfground();
        }

        /**
         * Sfts tif forfground dolor of tiis objfdt.
         *
         * @pbrbm d tif nfw <dodf>Color</dodf> for tif forfground
         */
        publid void sftForfground(Color d) {
            Componfnt.tiis.sftForfground(d);
        }

        /**
         * Gfts tif <dodf>Cursor</dodf> of tiis objfdt.
         *
         * @rfturn tif <dodf>Cursor</dodf>, if supportfd,
         *     of tif objfdt; otifrwisf, <dodf>null</dodf>
         */
        publid Cursor gftCursor() {
            rfturn Componfnt.tiis.gftCursor();
        }

        /**
         * Sfts tif <dodf>Cursor</dodf> of tiis objfdt.
         * <p>
         * Tif mftiod mby ibvf no visubl ffffdt if tif Jbvb plbtform
         * implfmfntbtion bnd/or tif nbtivf systfm do not support
         * dibnging tif mousf dursor sibpf.
         * @pbrbm dursor tif nfw <dodf>Cursor</dodf> for tif objfdt
         */
        publid void sftCursor(Cursor dursor) {
            Componfnt.tiis.sftCursor(dursor);
        }

        /**
         * Gfts tif <dodf>Font</dodf> of tiis objfdt.
         *
         * @rfturn tif <dodf>Font</dodf>, if supportfd,
         *    for tif objfdt; otifrwisf, <dodf>null</dodf>
         */
        publid Font gftFont() {
            rfturn Componfnt.tiis.gftFont();
        }

        /**
         * Sfts tif <dodf>Font</dodf> of tiis objfdt.
         *
         * @pbrbm f tif nfw <dodf>Font</dodf> for tif objfdt
         */
        publid void sftFont(Font f) {
            Componfnt.tiis.sftFont(f);
        }

        /**
         * Gfts tif <dodf>FontMftrids</dodf> of tiis objfdt.
         *
         * @pbrbm f tif <dodf>Font</dodf>
         * @rfturn tif <dodf>FontMftrids</dodf>, if supportfd,
         *     tif objfdt; otifrwisf, <dodf>null</dodf>
         * @sff #gftFont
         */
        publid FontMftrids gftFontMftrids(Font f) {
            if (f == null) {
                rfturn null;
            } flsf {
                rfturn Componfnt.tiis.gftFontMftrids(f);
            }
        }

        /**
         * Dftfrminfs if tif objfdt is fnbblfd.
         *
         * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
         */
        publid boolfbn isEnbblfd() {
            rfturn Componfnt.tiis.isEnbblfd();
        }

        /**
         * Sfts tif fnbblfd stbtf of tif objfdt.
         *
         * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
         */
        publid void sftEnbblfd(boolfbn b) {
            boolfbn old = Componfnt.tiis.isEnbblfd();
            Componfnt.tiis.sftEnbblfd(b);
            if (b != old) {
                if (bddfssiblfContfxt != null) {
                    if (b) {
                        bddfssiblfContfxt.firfPropfrtyCibngf(
                                                             AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AddfssiblfStbtf.ENABLED);
                    } flsf {
                        bddfssiblfContfxt.firfPropfrtyCibngf(
                                                             AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                             AddfssiblfStbtf.ENABLED, null);
                    }
                }
            }
        }

        /**
         * Dftfrminfs if tif objfdt is visiblf.  Notf: tiis mfbns tibt tif
         * objfdt intfnds to bf visiblf; iowfvfr, it mby not in fbdt bf
         * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
         * is dontbinfd by is not visiblf.  To dftfrminf if bn objfdt is
         * siowing on tif sdrffn, usf <dodf>isSiowing</dodf>.
         *
         * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
         */
        publid boolfbn isVisiblf() {
            rfturn Componfnt.tiis.isVisiblf();
        }

        /**
         * Sfts tif visiblf stbtf of tif objfdt.
         *
         * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
         */
        publid void sftVisiblf(boolfbn b) {
            boolfbn old = Componfnt.tiis.isVisiblf();
            Componfnt.tiis.sftVisiblf(b);
            if (b != old) {
                if (bddfssiblfContfxt != null) {
                    if (b) {
                        bddfssiblfContfxt.firfPropfrtyCibngf(
                                                             AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AddfssiblfStbtf.VISIBLE);
                    } flsf {
                        bddfssiblfContfxt.firfPropfrtyCibngf(
                                                             AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                                             AddfssiblfStbtf.VISIBLE, null);
                    }
                }
            }
        }

        /**
         * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd by difdking
         * tif visibility of tif objfdt bnd bndfstors of tif objfdt.  Notf:
         * tiis will rfturn truf fvfn if tif objfdt is obsdurfd by bnotifr
         * (for fxbmplf, it ibppfns to bf undfrnfbti b mfnu tibt wbs pullfd
         * down).
         *
         * @rfturn truf if objfdt is siowing; otifrwisf, fblsf
         */
        publid boolfbn isSiowing() {
            rfturn Componfnt.tiis.isSiowing();
        }

        /**
         * Cifdks wiftifr tif spfdififd point is witiin tiis objfdt's bounds,
         * wifrf tif point's x bnd y doordinbtfs brf dffinfd to bf rflbtivf to
         * tif doordinbtf systfm of tif objfdt.
         *
         * @pbrbm p tif <dodf>Point</dodf> rflbtivf to tif
         *     doordinbtf systfm of tif objfdt
         * @rfturn truf if objfdt dontbins <dodf>Point</dodf>; otifrwisf fblsf
         */
        publid boolfbn dontbins(Point p) {
            rfturn Componfnt.tiis.dontbins(p);
        }

        /**
         * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
         *
         * @rfturn lodbtion of objfdt on sdrffn -- dbn bf
         *    <dodf>null</dodf> if tiis objfdt is not on tif sdrffn
         */
        publid Point gftLodbtionOnSdrffn() {
            syndironizfd (Componfnt.tiis.gftTrffLodk()) {
                if (Componfnt.tiis.isSiowing()) {
                    rfturn Componfnt.tiis.gftLodbtionOnSdrffn();
                } flsf {
                    rfturn null;
                }
            }
        }

        /**
         * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt in tif form
         * of b point spfdifying tif objfdt's top-lfft dornfr in tif sdrffn's
         * doordinbtf spbdf.
         *
         * @rfturn bn instbndf of Point rfprfsfnting tif top-lfft dornfr of
         * tif objfdt's bounds in tif doordinbtf spbdf of tif sdrffn;
         * <dodf>null</dodf> if tiis objfdt or its pbrfnt brf not on tif sdrffn
         */
        publid Point gftLodbtion() {
            rfturn Componfnt.tiis.gftLodbtion();
        }

        /**
         * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
         * @pbrbm p  tif doordinbtfs of tif objfdt
         */
        publid void sftLodbtion(Point p) {
            Componfnt.tiis.sftLodbtion(p);
        }

        /**
         * Gfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @rfturn b rfdtbnglf indidbting tiis domponfnt's bounds;
         *   <dodf>null</dodf> if tiis objfdt is not on tif sdrffn
         */
        publid Rfdtbnglf gftBounds() {
            rfturn Componfnt.tiis.gftBounds();
        }

        /**
         * Sfts tif bounds of tiis objfdt in tif form of b
         * <dodf>Rfdtbnglf</dodf> objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @pbrbm r b rfdtbnglf indidbting tiis domponfnt's bounds
         */
        publid void sftBounds(Rfdtbnglf r) {
            Componfnt.tiis.sftBounds(r);
        }

        /**
         * Rfturns tif sizf of tiis objfdt in tif form of b
         * <dodf>Dimfnsion</dodf> objfdt. Tif ifigit fifld of tif
         * <dodf>Dimfnsion</dodf> objfdt dontbins tiis objfdts's
         * ifigit, bnd tif widti fifld of tif <dodf>Dimfnsion</dodf>
         * objfdt dontbins tiis objfdt's widti.
         *
         * @rfturn b <dodf>Dimfnsion</dodf> objfdt tibt indidbtfs
         *     tif sizf of tiis domponfnt; <dodf>null</dodf> if
         *     tiis objfdt is not on tif sdrffn
         */
        publid Dimfnsion gftSizf() {
            rfturn Componfnt.tiis.gftSizf();
        }

        /**
         * Rfsizfs tiis objfdt so tibt it ibs widti bnd ifigit.
         *
         * @pbrbm d - tif dimfnsion spfdifying tif nfw sizf of tif objfdt
         */
        publid void sftSizf(Dimfnsion d) {
            Componfnt.tiis.sftSizf(d);
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild,
         * if onf fxists, dontbinfd bt tif lodbl
         * doordinbtf <dodf>Point</dodf>.  Otifrwisf rfturns
         * <dodf>null</dodf>.
         *
         * @pbrbm p tif point dffining tif top-lfft dornfr of
         *      tif <dodf>Addfssiblf</dodf>, givfn in tif
         *      doordinbtf spbdf of tif objfdt's pbrfnt
         * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
         *      bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            rfturn null; // Componfnts don't ibvf diildrfn
        }

        /**
         * Rfturns wiftifr tiis objfdt dbn bddfpt fodus or not.
         *
         * @rfturn truf if objfdt dbn bddfpt fodus; otifrwisf fblsf
         */
        publid boolfbn isFodusTrbvfrsbblf() {
            rfturn Componfnt.tiis.isFodusTrbvfrsbblf();
        }

        /**
         * Rfqufsts fodus for tiis objfdt.
         */
        publid void rfqufstFodus() {
            Componfnt.tiis.rfqufstFodus();
        }

        /**
         * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from tiis
         * domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         */
        publid void bddFodusListfnfr(FodusListfnfr l) {
            Componfnt.tiis.bddFodusListfnfr(l);
        }

        /**
         * Rfmovfs tif spfdififd fodus listfnfr so it no longfr rfdfivfs fodus
         * fvfnts from tiis domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         */
        publid void rfmovfFodusListfnfr(FodusListfnfr l) {
            Componfnt.tiis.rfmovfFodusListfnfr(l);
        }

    } // innfr dlbss AddfssiblfAWTComponfnt


    /**
     * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
     * If tiis objfdt dofs not ibvf bn bddfssiblf pbrfnt, rfturns
     * -1.
     *
     * @rfturn tif indfx of tiis objfdt in its bddfssiblf pbrfnt
     */
    int gftAddfssiblfIndfxInPbrfnt() {
        syndironizfd (gftTrffLodk()) {
            int indfx = -1;
            Contbinfr pbrfnt = tiis.gftPbrfnt();
            if (pbrfnt != null && pbrfnt instbndfof Addfssiblf) {
                Componfnt db[] = pbrfnt.gftComponfnts();
                for (int i = 0; i < db.lfngti; i++) {
                    if (db[i] instbndfof Addfssiblf) {
                        indfx++;
                    }
                    if (tiis.fqubls(db[i])) {
                        rfturn indfx;
                    }
                }
            }
            rfturn -1;
        }
    }

    /**
     * Gfts tif durrfnt stbtf sft of tiis objfdt.
     *
     * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
     *    dontbining tif durrfnt stbtf sft of tif objfdt
     * @sff AddfssiblfStbtf
     */
    AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
        syndironizfd (gftTrffLodk()) {
            AddfssiblfStbtfSft stbtfs = nfw AddfssiblfStbtfSft();
            if (tiis.isEnbblfd()) {
                stbtfs.bdd(AddfssiblfStbtf.ENABLED);
            }
            if (tiis.isFodusTrbvfrsbblf()) {
                stbtfs.bdd(AddfssiblfStbtf.FOCUSABLE);
            }
            if (tiis.isVisiblf()) {
                stbtfs.bdd(AddfssiblfStbtf.VISIBLE);
            }
            if (tiis.isSiowing()) {
                stbtfs.bdd(AddfssiblfStbtf.SHOWING);
            }
            if (tiis.isFodusOwnfr()) {
                stbtfs.bdd(AddfssiblfStbtf.FOCUSED);
            }
            if (tiis instbndfof Addfssiblf) {
                AddfssiblfContfxt bd = ((Addfssiblf) tiis).gftAddfssiblfContfxt();
                if (bd != null) {
                    Addfssiblf bp = bd.gftAddfssiblfPbrfnt();
                    if (bp != null) {
                        AddfssiblfContfxt pbd = bp.gftAddfssiblfContfxt();
                        if (pbd != null) {
                            AddfssiblfSflfdtion bs = pbd.gftAddfssiblfSflfdtion();
                            if (bs != null) {
                                stbtfs.bdd(AddfssiblfStbtf.SELECTABLE);
                                int i = bd.gftAddfssiblfIndfxInPbrfnt();
                                if (i >= 0) {
                                    if (bs.isAddfssiblfCiildSflfdtfd(i)) {
                                        stbtfs.bdd(AddfssiblfStbtf.SELECTED);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (Componfnt.isInstbndfOf(tiis, "jbvbx.swing.JComponfnt")) {
                if (((jbvbx.swing.JComponfnt) tiis).isOpbquf()) {
                    stbtfs.bdd(AddfssiblfStbtf.OPAQUE);
                }
            }
            rfturn stbtfs;
        }
    }

    /**
     * Cifdks tibt tif givfn objfdt is instbndf of tif givfn dlbss.
     * @pbrbm obj Objfdt to bf difdkfd
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss. Must bf fully-qublififd dlbss nbmf.
     * @rfturn truf, if tiis objfdt is instbndfof givfn dlbss,
     *         fblsf, otifrwisf, or if obj or dlbssNbmf is null
     */
    stbtid boolfbn isInstbndfOf(Objfdt obj, String dlbssNbmf) {
        if (obj == null) rfturn fblsf;
        if (dlbssNbmf == null) rfturn fblsf;

        Clbss<?> dls = obj.gftClbss();
        wiilf (dls != null) {
            if (dls.gftNbmf().fqubls(dlbssNbmf)) {
                rfturn truf;
            }
            dls = dls.gftSupfrdlbss();
        }
        rfturn fblsf;
    }


    // ************************** MIXING CODE *******************************

    /**
     * Cifdk wiftifr wf dbn trust tif durrfnt bounds of tif domponfnt.
     * Tif rfturn vbluf of fblsf indidbtfs tibt tif dontbinfr of tif
     * domponfnt is invblid, bnd tifrfforf nffds to bf lbyfd out, wiidi would
     * probbbly mfbn dibnging tif bounds of its diildrfn.
     * Null-lbyout of tif dontbinfr or bbsfndf of tif dontbinfr mfbn
     * tif bounds of tif domponfnt brf finbl bnd dbn bf trustfd.
     */
    finbl boolfbn brfBoundsVblid() {
        Contbinfr dont = gftContbinfr();
        rfturn dont == null || dont.isVblid() || dont.gftLbyout() == null;
    }

    /**
     * Applifs tif sibpf to tif domponfnt
     * @pbrbm sibpf Sibpf to bf bpplifd to tif domponfnt
     */
    void bpplyCompoundSibpf(Rfgion sibpf) {
        difdkTrffLodk();

        if (!brfBoundsVblid()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis + "; brfBoundsVblid = " + brfBoundsVblid());
            }
            rfturn;
        }

        if (!isLigitwfigit()) {
            ComponfntPffr pffr = gftPffr();
            if (pffr != null) {
                // Tif Rfgion dlbss ibs somf optimizbtions. Tibt's wiy
                // wf siould mbnublly difdk wiftifr it's fmpty bnd
                // substitutf tif objfdt oursflvfs. Otifrwisf wf fnd up
                // witi somf indorrfdt Rfgion objfdt witi loX bfing
                // grfbtfr tibn tif iiX for instbndf.
                if (sibpf.isEmpty()) {
                    sibpf = Rfgion.EMPTY_REGION;
                }


                // Notf: tif sibpf is not rfblly dopifd/dlonfd. Wf drfbtf
                // tif Rfgion objfdt oursflvfs, so tifrf's no bny possibility
                // to modify tif objfdt outsidf of tif mixing dodf.
                // Nullifying dompoundSibpf mfbns tibt tif domponfnt ibs normbl sibpf
                // (or ibs no sibpf bt bll).
                if (sibpf.fqubls(gftNormblSibpf())) {
                    if (tiis.dompoundSibpf == null) {
                        rfturn;
                    }
                    tiis.dompoundSibpf = null;
                    pffr.bpplySibpf(null);
                } flsf {
                    if (sibpf.fqubls(gftApplifdSibpf())) {
                        rfturn;
                    }
                    tiis.dompoundSibpf = sibpf;
                    Point dompAbsolutf = gftLodbtionOnWindow();
                    if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                        mixingLog.finf("tiis = " + tiis +
                                "; dompAbsolutf=" + dompAbsolutf + "; sibpf=" + sibpf);
                    }
                    pffr.bpplySibpf(sibpf.gftTrbnslbtfdRfgion(-dompAbsolutf.x, -dompAbsolutf.y));
                }
            }
        }
    }

    /**
     * Rfturns tif sibpf prfviously sft witi bpplyCompoundSibpf().
     * If tif domponfnt is LW or no sibpf wbs bpplifd yft,
     * tif mftiod rfturns tif normbl sibpf.
     */
    privbtf Rfgion gftApplifdSibpf() {
        difdkTrffLodk();
        //XXX: if wf bllow LW domponfnts to ibvf b sibpf, tiis must bf dibngfd
        rfturn (tiis.dompoundSibpf == null || isLigitwfigit()) ? gftNormblSibpf() : tiis.dompoundSibpf;
    }

    Point gftLodbtionOnWindow() {
        difdkTrffLodk();
        Point durLodbtion = gftLodbtion();

        for (Contbinfr pbrfnt = gftContbinfr();
                pbrfnt != null && !(pbrfnt instbndfof Window);
                pbrfnt = pbrfnt.gftContbinfr())
        {
            durLodbtion.x += pbrfnt.gftX();
            durLodbtion.y += pbrfnt.gftY();
        }

        rfturn durLodbtion;
    }

    /**
     * Rfturns tif full sibpf of tif domponfnt lodbtfd in window doordinbtfs
     */
    finbl Rfgion gftNormblSibpf() {
        difdkTrffLodk();
        //XXX: wf mby tbkf into bddount b usfr-spfdififd sibpf for tiis domponfnt
        Point dompAbsolutf = gftLodbtionOnWindow();
        rfturn
            Rfgion.gftInstbndfXYWH(
                    dompAbsolutf.x,
                    dompAbsolutf.y,
                    gftWidti(),
                    gftHfigit()
            );
    }

    /**
     * Rfturns tif "opbquf sibpf" of tif domponfnt.
     *
     * Tif opbquf sibpf of b ligitwfigit domponfnts is tif bdtubl sibpf tibt
     * nffds to bf dut off of tif ifbvywfigit domponfnts in ordfr to mix tiis
     * ligitwfigit domponfnt dorrfdtly witi tifm.
     *
     * Tif mftiod is ovfrridfn in tif jbvb.bwt.Contbinfr to ibndlf non-opbquf
     * dontbinfrs dontbining opbquf diildrfn.
     *
     * Sff 6637655 for dftbils.
     */
    Rfgion gftOpbqufSibpf() {
        difdkTrffLodk();
        if (mixingCutoutRfgion != null) {
            rfturn mixingCutoutRfgion;
        } flsf {
            rfturn gftNormblSibpf();
        }
    }

    finbl int gftSiblingIndfxAbovf() {
        difdkTrffLodk();
        Contbinfr pbrfnt = gftContbinfr();
        if (pbrfnt == null) {
            rfturn -1;
        }

        int nfxtAbovf = pbrfnt.gftComponfntZOrdfr(tiis) - 1;

        rfturn nfxtAbovf < 0 ? -1 : nfxtAbovf;
    }

    finbl ComponfntPffr gftHWPffrAbovfMf() {
        difdkTrffLodk();

        Contbinfr dont = gftContbinfr();
        int indfxAbovf = gftSiblingIndfxAbovf();

        wiilf (dont != null) {
            for (int i = indfxAbovf; i > -1; i--) {
                Componfnt domp = dont.gftComponfnt(i);
                if (domp != null && domp.isDisplbybblf() && !domp.isLigitwfigit()) {
                    rfturn domp.gftPffr();
                }
            }
            // trbvfrsing tif iifrbrdiy up to tif dlosfst HW dontbinfr;
            // furtifr trbvfrsing mby rfturn b domponfnt tibt is not bdtublly
            // b nbtivf sibling of tiis domponfnt bnd tiis kind of z-ordfr
            // rfqufst mby not bf bllowfd by tif undfrlying systfm (6852051).
            if (!dont.isLigitwfigit()) {
                brfbk;
            }

            indfxAbovf = dont.gftSiblingIndfxAbovf();
            dont = dont.gftContbinfr();
        }

        rfturn null;
    }

    finbl int gftSiblingIndfxBflow() {
        difdkTrffLodk();
        Contbinfr pbrfnt = gftContbinfr();
        if (pbrfnt == null) {
            rfturn -1;
        }

        int nfxtBflow = pbrfnt.gftComponfntZOrdfr(tiis) + 1;

        rfturn nfxtBflow >= pbrfnt.gftComponfntCount() ? -1 : nfxtBflow;
    }

    finbl boolfbn isNonOpbqufForMixing() {
        rfturn mixingCutoutRfgion != null &&
            mixingCutoutRfgion.isEmpty();
    }

    privbtf Rfgion dbldulbtfCurrfntSibpf() {
        difdkTrffLodk();
        Rfgion s = gftNormblSibpf();

        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("tiis = " + tiis + "; normblSibpf=" + s);
        }

        if (gftContbinfr() != null) {
            Componfnt domp = tiis;
            Contbinfr dont = domp.gftContbinfr();

            wiilf (dont != null) {
                for (int indfx = domp.gftSiblingIndfxAbovf(); indfx != -1; --indfx) {
                    /* It is bssumfd tibt:
                     *
                     *    gftComponfnt(gftContbinfr().gftComponfntZOrdfr(domp)) == domp
                     *
                     * Tif bssumption ibs bffn mbdf bddording to tif durrfnt
                     * implfmfntbtion of tif Contbinfr dlbss.
                     */
                    Componfnt d = dont.gftComponfnt(indfx);
                    if (d.isLigitwfigit() && d.isSiowing()) {
                        s = s.gftDifffrfndf(d.gftOpbqufSibpf());
                    }
                }

                if (dont.isLigitwfigit()) {
                    s = s.gftIntfrsfdtion(dont.gftNormblSibpf());
                } flsf {
                    brfbk;
                }

                domp = dont;
                dont = dont.gftContbinfr();
            }
        }

        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("durrfntSibpf=" + s);
        }

        rfturn s;
    }

    void bpplyCurrfntSibpf() {
        difdkTrffLodk();
        if (!brfBoundsVblid()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis + "; brfBoundsVblid = " + brfBoundsVblid());
            }
            rfturn; // Bfdbusf bpplyCompoundSibpf() ignorfs sudi domponfnts bnywby
        }
        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("tiis = " + tiis);
        }
        bpplyCompoundSibpf(dbldulbtfCurrfntSibpf());
    }

    finbl void subtrbdtAndApplySibpf(Rfgion s) {
        difdkTrffLodk();

        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("tiis = " + tiis + "; s=" + s);
        }

        bpplyCompoundSibpf(gftApplifdSibpf().gftDifffrfndf(s));
    }

    privbtf finbl void bpplyCurrfntSibpfBflowMf() {
        difdkTrffLodk();
        Contbinfr pbrfnt = gftContbinfr();
        if (pbrfnt != null && pbrfnt.isSiowing()) {
            // First, rfbpply sibpfs of my siblings
            pbrfnt.rfdursivfApplyCurrfntSibpf(gftSiblingIndfxBflow());

            // Sfdond, if my dontbinfr is non-opbquf, rfbpply sibpfs of siblings of my dontbinfr
            Contbinfr pbrfnt2 = pbrfnt.gftContbinfr();
            wiilf (!pbrfnt.isOpbquf() && pbrfnt2 != null) {
                pbrfnt2.rfdursivfApplyCurrfntSibpf(pbrfnt.gftSiblingIndfxBflow());

                pbrfnt = pbrfnt2;
                pbrfnt2 = pbrfnt.gftContbinfr();
            }
        }
    }

    finbl void subtrbdtAndApplySibpfBflowMf() {
        difdkTrffLodk();
        Contbinfr pbrfnt = gftContbinfr();
        if (pbrfnt != null && isSiowing()) {
            Rfgion opbqufSibpf = gftOpbqufSibpf();

            // First, dut my siblings
            pbrfnt.rfdursivfSubtrbdtAndApplySibpf(opbqufSibpf, gftSiblingIndfxBflow());

            // Sfdond, if my dontbinfr is non-opbquf, dut siblings of my dontbinfr
            Contbinfr pbrfnt2 = pbrfnt.gftContbinfr();
            wiilf (!pbrfnt.isOpbquf() && pbrfnt2 != null) {
                pbrfnt2.rfdursivfSubtrbdtAndApplySibpf(opbqufSibpf, pbrfnt.gftSiblingIndfxBflow());

                pbrfnt = pbrfnt2;
                pbrfnt2 = pbrfnt.gftContbinfr();
            }
        }
    }

    void mixOnSiowing() {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis);
            }
            if (!isMixingNffdfd()) {
                rfturn;
            }
            if (isLigitwfigit()) {
                subtrbdtAndApplySibpfBflowMf();
            } flsf {
                bpplyCurrfntSibpf();
            }
        }
    }

    void mixOnHiding(boolfbn isLigitwfigit) {
        // Wf dbnnot bf surf tibt tif pffr fxists bt tiis point, so wf nffd tif brgumfnt
        //    to find out wiftifr tif iiding domponfnt is (wfll, bdtublly wbs) b LW or b HW.
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis + "; isLigitwfigit = " + isLigitwfigit);
            }
            if (!isMixingNffdfd()) {
                rfturn;
            }
            if (isLigitwfigit) {
                bpplyCurrfntSibpfBflowMf();
            }
        }
    }

    void mixOnRfsibping() {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis);
            }
            if (!isMixingNffdfd()) {
                rfturn;
            }
            if (isLigitwfigit()) {
                bpplyCurrfntSibpfBflowMf();
            } flsf {
                bpplyCurrfntSibpf();
            }
        }
    }

    void mixOnZOrdfrCibnging(int oldZordfr, int nfwZordfr) {
        syndironizfd (gftTrffLodk()) {
            boolfbn bfdbmfHigifr = nfwZordfr < oldZordfr;
            Contbinfr pbrfnt = gftContbinfr();

            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis +
                    "; oldZordfr=" + oldZordfr + "; nfwZordfr=" + nfwZordfr + "; pbrfnt=" + pbrfnt);
            }
            if (!isMixingNffdfd()) {
                rfturn;
            }
            if (isLigitwfigit()) {
                if (bfdbmfHigifr) {
                    if (pbrfnt != null && isSiowing()) {
                        pbrfnt.rfdursivfSubtrbdtAndApplySibpf(gftOpbqufSibpf(), gftSiblingIndfxBflow(), oldZordfr);
                    }
                } flsf {
                    if (pbrfnt != null) {
                        pbrfnt.rfdursivfApplyCurrfntSibpf(oldZordfr, nfwZordfr);
                    }
                }
            } flsf {
                if (bfdbmfHigifr) {
                    bpplyCurrfntSibpf();
                } flsf {
                    if (pbrfnt != null) {
                        Rfgion sibpf = gftApplifdSibpf();

                        for (int indfx = oldZordfr; indfx < nfwZordfr; indfx++) {
                            Componfnt d = pbrfnt.gftComponfnt(indfx);
                            if (d.isLigitwfigit() && d.isSiowing()) {
                                sibpf = sibpf.gftDifffrfndf(d.gftOpbqufSibpf());
                            }
                        }
                        bpplyCompoundSibpf(sibpf);
                    }
                }
            }
        }
    }

    void mixOnVblidbting() {
        // Tiis mftiod gfts ovfrridfn in tif Contbinfr. Obviously, b plbin
        // non-dontbinfr domponfnts don't nffd to ibndlf vblidbtion.
    }

    finbl boolfbn isMixingNffdfd() {
        if (SunToolkit.gftSunAwtDisbblfMixing()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                mixingLog.finfst("tiis = " + tiis + "; Mixing disbblfd vib sun.bwt.disbblfMixing");
            }
            rfturn fblsf;
        }
        if (!brfBoundsVblid()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis + "; brfBoundsVblid = " + brfBoundsVblid());
            }
            rfturn fblsf;
        }
        Window window = gftContbiningWindow();
        if (window != null) {
            if (!window.ibsHfbvywfigitDfsdfndbnts() || !window.ibsLigitwfigitDfsdfndbnts() || window.isDisposing()) {
                if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    mixingLog.finf("dontbining window = " + window +
                            "; ibs i/w dfsdfndbnts = " + window.ibsHfbvywfigitDfsdfndbnts() +
                            "; ibs l/w dfsdfndbnts = " + window.ibsLigitwfigitDfsdfndbnts() +
                            "; disposing = " + window.isDisposing());
                }
                rfturn fblsf;
            }
        } flsf {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis + "; dontbining window is null");
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    // ****************** END OF MIXING CODE ********************************

    // Notf tibt tif mftiod is ovfrridfn in tif Window dlbss,
    // b window dofsn't nffd to bf updbtfd in tif Z-ordfr.
    void updbtfZOrdfr() {
        pffr.sftZOrdfr(gftHWPffrAbovfMf());
    }

}
