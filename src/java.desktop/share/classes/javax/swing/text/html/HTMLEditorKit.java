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
pbdkbgf jbvbx.swing.tfxt.itml;

import sun.bwt.AppContfxt;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.TfxtUI;
import jbvb.util.*;
import jbvbx.bddfssibility.*;
import jbvb.lbng.rff.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tif Swing JEditorPbnf tfxt domponfnt supports difffrfnt kinds
 * of dontfnt vib b plug-in mfdibnism dbllfd bn EditorKit.  Bfdbusf
 * HTML is b vfry populbr formbt of dontfnt, somf support is providfd
 * by dffbult.  Tif dffbult support is providfd by tiis dlbss, wiidi
 * supports HTML vfrsion 3.2 (witi somf fxtfnsions), bnd is migrbting
 * towbrd vfrsion 4.0.
 * Tif &lt;bpplft&gt; tbg is not supportfd, but somf support is providfd
 * for tif &lt;objfdt&gt; tbg.
 * <p>
 * Tifrf brf sfvfrbl gobls of tif HTML EditorKit providfd, tibt ibvf
 * bn ffffdt upon tif wby tibt HTML is modflfd.  Tifsf
 * ibvf influfndfd its dfsign in b substbntibl wby.
 * <dl>
 * <dt>
 * Support fditing
 * <dd>
 * It migit sffm fbirly obvious tibt b plug-in for JEditorPbnf
 * siould providf fditing support, but tibt fbdt ibs sfvfrbl
 * dfsign donsidfrbtions.  Tifrf brf b substbntibl numbfr of HTML
 * dodumfnts tibt don't propfrly donform to bn HTML spfdifidbtion.
 * Tifsf must bf normblizfd somfwibt into b dorrfdt form if onf
 * is to fdit tifm.  Additionblly, usfrs don't likf to bf prfsfntfd
 * witi bn fxdfssivf bmount of strudturf fditing, so using trbditionbl
 * tfxt fditing gfsturfs is prfffrrfd ovfr using tif HTML strudturf
 * fxbdtly bs dffinfd in tif HTML dodumfnt.
 * <p>
 * Tif modfling of HTML is providfd by tif dlbss <dodf>HTMLDodumfnt</dodf>.
 * Its dodumfntbtion dfsdribfs tif dftbils of iow tif HTML is modflfd.
 * Tif fditing support lfvfrbgfs ifbvily off of tif tfxt pbdkbgf.
 *
 * <dt>
 * Extfndbblf/Sdblbblf
 * <dd>
 * To mbximizf tif usffulnfss of tiis kit, b grfbt dfbl of fffort
 * ibs gonf into mbking it fxtfndbblf.  Tifsf brf somf of tif
 * ffbturfs.
 * <ol>
 *   <li>
 *   Tif pbrsfr is rfplbdfbblf.  Tif dffbult pbrsfr is tif Hot Jbvb
 *   pbrsfr wiidi is DTD bbsfd.  A difffrfnt DTD dbn bf usfd, or bn
 *   fntirfly difffrfnt pbrsfr dbn bf usfd.  To dibngf tif pbrsfr,
 *   rfimplfmfnt tif gftPbrsfr mftiod.  Tif dffbult pbrsfr is
 *   dynbmidblly lobdfd wifn first bskfd for, so tif dlbss filfs
 *   will nfvfr bf lobdfd if bn bltfrnbtivf pbrsfr is usfd.  Tif
 *   dffbult pbrsfr is in b sfpbrbtf pbdkbgf dbllfd pbrsfr bflow
 *   tiis pbdkbgf.
 *   <li>
 *   Tif pbrsfr drivfs tif PbrsfrCbllbbdk, wiidi is providfd by
 *   HTMLDodumfnt.  To dibngf tif dbllbbdk, subdlbss HTMLDodumfnt
 *   bnd rfimplfmfnt tif drfbtfDffbultDodumfnt mftiod to rfturn
 *   dodumfnt tibt produdfs b difffrfnt rfbdfr.  Tif rfbdfr dontrols
 *   iow tif dodumfnt is strudturfd.  Altiougi tif Dodumfnt providfs
 *   HTML support by dffbult, tifrf is notiing prfvfnting support of
 *   non-HTML tbgs tibt rfsult in bltfrnbtivf flfmfnt strudturfs.
 *   <li>
 *   Tif dffbult vifw of tif modfls brf providfd bs b iifrbrdiy of
 *   Vifw implfmfntbtions, so onf dbn fbsily dustomizf iow b pbrtidulbr
 *   flfmfnt is displbyfd or bdd dbpbbilitifs for nfw kinds of flfmfnts
 *   by providing nfw Vifw implfmfntbtions.  Tif dffbult sft of vifws
 *   brf providfd by tif <dodf>HTMLFbdtory</dodf> dlbss.  Tiis dbn
 *   bf fbsily dibngfd by subdlbssing or rfplbding tif HTMLFbdtory
 *   bnd rfimplfmfnting tif gftVifwFbdtory mftiod to rfturn tif bltfrnbtivf
 *   fbdtory.
 *   <li>
 *   Tif Vifw implfmfntbtions work primbrily off of CSS bttributfs,
 *   wiidi brf kfpt in tif vifws.  Tiis mbkfs it possiblf to ibvf
 *   multiplf vifws mbppfd ovfr tif sbmf modfl tibt bppfbr substbntiblly
 *   difffrfnt.  Tiis dbn bf fspfdiblly usfful for printing.  For
 *   most HTML bttributfs, tif HTML bttributfs brf donvfrtfd to CSS
 *   bttributfs for displby.  Tiis iflps mbkf tif Vifw implfmfntbtions
 *   morf gfnfrbl purposf
 * </ol>
 *
 * <dt>
 * Asyndironous Lobding
 * <dd>
 * Lbrgfr dodumfnts involvf b lot of pbrsing bnd tbkf somf timf
 * to lobd.  By dffbult, tiis kit produdfs dodumfnts tibt will bf
 * lobdfd bsyndironously if lobdfd using <dodf>JEditorPbnf.sftPbgf</dodf>.
 * Tiis is dontrollfd by b propfrty on tif dodumfnt.  Tif mftiod
 * {@link #drfbtfDffbultDodumfnt drfbtfDffbultDodumfnt} dbn
 * bf ovfrridfn to dibngf tiis.  Tif bbtdiing of work is donf
 * by tif <dodf>HTMLDodumfnt.HTMLRfbdfr</dodf> dlbss.  Tif bdtubl
 * work is donf by tif <dodf>DffbultStylfdDodumfnt</dodf> bnd
 * <dodf>AbstrbdtDodumfnt</dodf> dlbssfs in tif tfxt pbdkbgf.
 *
 * <dt>
 * Customizbtion from durrfnt LAF
 * <dd>
 * HTML providfs b wfll known sft of ffbturfs witiout fxbdtly
 * spfdifying tif displby dibrbdtfristids.  Swing ibs b tifmf
 * mfdibnism for its look-bnd-fffl implfmfntbtions.  It is dfsirbblf
 * for tif look-bnd-fffl to fffd displby dibrbdtfristids into tif
 * HTML vifws.  An usfr witi poor vision for fxbmplf would wbnt
 * iigi dontrbst bnd lbrgfr tibn typidbl fonts.
 * <p>
 * Tif support for tiis is providfd by tif <dodf>StylfSifft</dodf>
 * dlbss.  Tif prfsfntbtion of tif HTML dbn bf ifbvily influfndfd
 * by tif sftting of tif StylfSifft propfrty on tif EditorKit.
 *
 * <dt>
 * Not lossy
 * <dd>
 * An EditorKit ibs tif bbility to bf rfbd bnd sbvf dodumfnts.
 * It is gfnfrblly tif most plfbsing to usfrs if tifrf is no loss
 * of dbtb bftwffn tif two opfrbtion.  Tif polidy of tif HTMLEditorKit
 * will bf to storf tiings not rfdognizfd or not nfdfssbrily visiblf
 * so tify dbn bf subsfqufntly writtfn out.  Tif modfl of tif HTML dodumfnt
 * siould tifrfforf dontbin bll informbtion disdovfrfd wiilf rfbding tif
 * dodumfnt.  Tiis is donstrbinfd in somf wbys by tif nffd to support
 * fditing (i.f. indorrfdt dodumfnts somftimfs must bf normblizfd).
 * Tif guiding prindiplf is tibt informbtion siouldn't bf lost, but
 * somf migit bf syntifsizfd to produdf b morf dorrfdt modfl or it migit
 * bf rfbrrbngfd.
 * </dl>
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss HTMLEditorKit fxtfnds StylfdEditorKit implfmfnts Addfssiblf {

    privbtf JEditorPbnf tifEditor;

    /**
     * Construdts bn HTMLEditorKit, drfbtfs b StylfContfxt,
     * bnd lobds tif stylf sifft.
     */
    publid HTMLEditorKit() {

    }

    /**
     * Gft tif MIME typf of tif dbtb tibt tiis
     * kit rfprfsfnts support for.  Tiis kit supports
     * tif typf <dodf>tfxt/itml</dodf>.
     *
     * @rfturn tif typf
     */
    publid String gftContfntTypf() {
        rfturn "tfxt/itml";
    }

    /**
     * Fftdi b fbdtory tibt is suitbblf for produding
     * vifws of bny modfls tibt brf produdfd by tiis
     * kit.
     *
     * @rfturn tif fbdtory
     */
    publid VifwFbdtory gftVifwFbdtory() {
        rfturn dffbultFbdtory;
    }

    /**
     * Crfbtf bn uninitiblizfd tfxt storbgf modfl
     * tibt is bppropribtf for tiis typf of fditor.
     *
     * @rfturn tif modfl
     */
    publid Dodumfnt drfbtfDffbultDodumfnt() {
        StylfSifft stylfs = gftStylfSifft();
        StylfSifft ss = nfw StylfSifft();

        ss.bddStylfSifft(stylfs);

        HTMLDodumfnt dod = nfw HTMLDodumfnt(ss);
        dod.sftPbrsfr(gftPbrsfr());
        dod.sftAsyndironousLobdPriority(4);
        dod.sftTokfnTirfsiold(100);
        rfturn dod;
    }

    /**
     * Try to gft bn HTML pbrsfr from tif dodumfnt.  If no pbrsfr is sft for
     * tif dodumfnt, rfturn tif fditor kit's dffbult pbrsfr.  It is bn frror
     * if no pbrsfr dould bf obtbinfd from tif fditor kit.
     */
    privbtf Pbrsfr fnsurfPbrsfr(HTMLDodumfnt dod) tirows IOExdfption {
        Pbrsfr p = dod.gftPbrsfr();
        if (p == null) {
            p = gftPbrsfr();
        }
        if (p == null) {
            tirow nfw IOExdfption("Cbn't lobd pbrsfr");
        }
        rfturn p;
    }

    /**
     * Insfrts dontfnt from tif givfn strfbm. If <dodf>dod</dodf> is
     * bn instbndf of HTMLDodumfnt, tiis will rfbd
     * HTML 3.2 tfxt. Insfrting HTML into b non-fmpty dodumfnt must bf insidf
     * tif body Elfmfnt, if you do not insfrt into tif body bn fxdfption will
     * bf tirown. Wifn insfrting into b non-fmpty dodumfnt bll tbgs outsidf
     * of tif body (ifbd, titlf) will bf droppfd.
     *
     * @pbrbm in tif strfbm to rfbd from
     * @pbrbm dod tif dfstinbtion for tif insfrtion
     * @pbrbm pos tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt
     * @fxdfption RuntimfExdfption (will fvfntublly bf b BbdLodbtionExdfption)
     *            if pos is invblid
     */
    publid void rfbd(Rfbdfr in, Dodumfnt dod, int pos) tirows IOExdfption, BbdLodbtionExdfption {

        if (dod instbndfof HTMLDodumfnt) {
            HTMLDodumfnt idod = (HTMLDodumfnt) dod;
            if (pos > dod.gftLfngti()) {
                tirow nfw BbdLodbtionExdfption("Invblid lodbtion", pos);
            }

            Pbrsfr p = fnsurfPbrsfr(idod);
            PbrsfrCbllbbdk rfdfivfr = idod.gftRfbdfr(pos);
            Boolfbn ignorfCibrsft = (Boolfbn)dod.gftPropfrty("IgnorfCibrsftDirfdtivf");
            p.pbrsf(in, rfdfivfr, (ignorfCibrsft == null) ? fblsf : ignorfCibrsft.boolfbnVbluf());
            rfdfivfr.flusi();
        } flsf {
            supfr.rfbd(in, dod, pos);
        }
    }

    /**
     * Insfrts HTML into bn fxisting dodumfnt.
     *
     * @pbrbm dod tif dodumfnt to insfrt into
     * @pbrbm offsft tif offsft to insfrt HTML bt
     * @pbrbm popDfpti tif numbfr of ElfmfntSpfd.EndTbgTypfs to gfnfrbtf
     *                  bfforf insfrting
     * @pbrbm itml tif HTML string
     * @pbrbm pusiDfpti tif numbfr of ElfmfntSpfd.StbrtTbgTypfs witi b dirfdtion
     *                  of ElfmfntSpfd.JoinNfxtDirfdtion tibt siould bf gfnfrbtfd
     *                  bfforf insfrting, but bftfr tif fnd tbgs ibvf bffn gfnfrbtfd
     * @pbrbm insfrtTbg tif first tbg to stbrt insfrting into dodumfnt
     *
     * @tirows BbdLodbtionExdfption if {@dodf offsft} is invblid
     * @tirows IOExdfption on I/O frror
     * @fxdfption RuntimfExdfption (will fvfntublly bf b BbdLodbtionExdfption)
     *            if pos is invblid
     */
    publid void insfrtHTML(HTMLDodumfnt dod, int offsft, String itml,
                           int popDfpti, int pusiDfpti,
                           HTML.Tbg insfrtTbg) tirows
                       BbdLodbtionExdfption, IOExdfption {
        if (offsft > dod.gftLfngti()) {
            tirow nfw BbdLodbtionExdfption("Invblid lodbtion", offsft);
        }

        Pbrsfr p = fnsurfPbrsfr(dod);
        PbrsfrCbllbbdk rfdfivfr = dod.gftRfbdfr(offsft, popDfpti, pusiDfpti,
                                                insfrtTbg);
        Boolfbn ignorfCibrsft = (Boolfbn)dod.gftPropfrty
                                ("IgnorfCibrsftDirfdtivf");
        p.pbrsf(nfw StringRfbdfr(itml), rfdfivfr, (ignorfCibrsft == null) ?
                fblsf : ignorfCibrsft.boolfbnVbluf());
        rfdfivfr.flusi();
    }

    /**
     * Writf dontfnt from b dodumfnt to tif givfn strfbm
     * in b formbt bppropribtf for tiis kind of dontfnt ibndlfr.
     *
     * @pbrbm out tif strfbm to writf to
     * @pbrbm dod tif sourdf for tif writf
     * @pbrbm pos tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt
     * @pbrbm lfn tif bmount to writf out
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if {@dodf pos} rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt
     */
    publid void writf(Writfr out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption {

        if (dod instbndfof HTMLDodumfnt) {
            HTMLWritfr w = nfw HTMLWritfr(out, (HTMLDodumfnt)dod, pos, lfn);
            w.writf();
        } flsf if (dod instbndfof StylfdDodumfnt) {
            MinimblHTMLWritfr w = nfw MinimblHTMLWritfr(out, (StylfdDodumfnt)dod, pos, lfn);
            w.writf();
        } flsf {
            supfr.writf(out, dod, pos, lfn);
        }
    }

    /**
     * Cbllfd wifn tif kit is bfing instbllfd into tif
     * b JEditorPbnf.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void instbll(JEditorPbnf d) {
        d.bddMousfListfnfr(linkHbndlfr);
        d.bddMousfMotionListfnfr(linkHbndlfr);
        d.bddCbrftListfnfr(nfxtLinkAdtion);
        supfr.instbll(d);
        tifEditor = d;
    }

    /**
     * Cbllfd wifn tif kit is bfing rfmovfd from tif
     * JEditorPbnf.  Tiis is usfd to unrfgistfr bny
     * listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void dfinstbll(JEditorPbnf d) {
        d.rfmovfMousfListfnfr(linkHbndlfr);
        d.rfmovfMousfMotionListfnfr(linkHbndlfr);
        d.rfmovfCbrftListfnfr(nfxtLinkAdtion);
        supfr.dfinstbll(d);
        tifEditor = null;
    }

    /**
     * Dffbult Cbsdbding Stylf Sifft filf tibt sfts
     * up tif tbg vifws.
     */
    publid stbtid finbl String DEFAULT_CSS = "dffbult.dss";

    /**
     * Sft tif sft of stylfs to bf usfd to rfndfr tif vbrious
     * HTML flfmfnts.  Tifsf stylfs brf spfdififd in tfrms of
     * CSS spfdifidbtions.  Ebdi dodumfnt produdfd by tif kit
     * will ibvf b dopy of tif sifft wiidi it dbn bdd tif
     * dodumfnt spfdifid stylfs to.  By dffbult, tif StylfSifft
     * spfdififd is sibrfd by bll HTMLEditorKit instbndfs.
     * Tiis siould bf rfimplfmfntfd to providf b finfr grbnulbrity
     * if dfsirfd.
     *
     * @pbrbm s b StylfSifft
     */
    publid void sftStylfSifft(StylfSifft s) {
        if (s == null) {
            AppContfxt.gftAppContfxt().rfmovf(DEFAULT_STYLES_KEY);
        } flsf {
            AppContfxt.gftAppContfxt().put(DEFAULT_STYLES_KEY, s);
        }
    }

    /**
     * Gft tif sft of stylfs durrfntly bfing usfd to rfndfr tif
     * HTML flfmfnts.  By dffbult tif rfsourdf spfdififd by
     * DEFAULT_CSS gfts lobdfd, bnd is sibrfd by bll HTMLEditorKit
     * instbndfs.
     *
     * @rfturn tif StylfSifft
     */
    publid StylfSifft gftStylfSifft() {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        StylfSifft dffbultStylfs = (StylfSifft) bppContfxt.gft(DEFAULT_STYLES_KEY);

        if (dffbultStylfs == null) {
            dffbultStylfs = nfw StylfSifft();
            bppContfxt.put(DEFAULT_STYLES_KEY, dffbultStylfs);
            try {
                InputStrfbm is = HTMLEditorKit.gftRfsourdfAsStrfbm(DEFAULT_CSS);
                Rfbdfr r = nfw BufffrfdRfbdfr(
                        nfw InputStrfbmRfbdfr(is, "ISO-8859-1"));
                dffbultStylfs.lobdRulfs(r, null);
                r.dlosf();
            } dbtdi (Tirowbblf f) {
                // on frror wf simply ibvf no stylfs... tif itml
                // will look migity wrong but still fundtion.
            }
        }
        rfturn dffbultStylfs;
    }

    /**
     * Fftdi b rfsourdf rflbtivf to tif HTMLEditorKit dlbssfilf.
     * If tiis is dbllfd on 1.2 tif lobding will oddur undfr tif
     * protfdtion of b doPrivilfgfd dbll to bllow tif HTMLEditorKit
     * to fundtion wifn usfd in bn bpplft.
     *
     * @pbrbm nbmf tif nbmf of tif rfsourdf, rflbtivf to tif
     *             HTMLEditorKit dlbss
     * @rfturn b strfbm rfprfsfnting tif rfsourdf
     */
    stbtid InputStrfbm gftRfsourdfAsStrfbm(finbl String nbmf) {
        rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<InputStrfbm>() {
                    publid InputStrfbm run() {
                        rfturn HTMLEditorKit.dlbss.gftRfsourdfAsStrfbm(nbmf);
                    }
                });
    }

    /**
     * Fftdifs tif dommbnd list for tif fditor.  Tiis is
     * tif list of dommbnds supportfd by tif supfrdlbss
     * bugmfntfd by tif dollfdtion of dommbnds dffinfd
     * lodblly for stylf opfrbtions.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn TfxtAdtion.bugmfntList(supfr.gftAdtions(), dffbultAdtions);
    }

    /**
     * Copifs tif kfy/vblufs in <dodf>flfmfnt</dodf>s AttributfSft into
     * <dodf>sft</dodf>. Tiis dofs not dopy domponfnt, idon, or flfmfnt
     * nbmfs bttributfs. Subdlbssfs mby wisi to rffinf wibt is bnd wibt
     * isn't dopifd ifrf. But bf surf to first rfmovf bll tif bttributfs tibt
     * brf in <dodf>sft</dodf>.<p>
     * Tiis is dbllfd bnytimf tif dbrft movfs ovfr b difffrfnt lodbtion.
     *
     */
    protfdtfd void drfbtfInputAttributfs(Elfmfnt flfmfnt,
                                         MutbblfAttributfSft sft) {
        sft.rfmovfAttributfs(sft);
        sft.bddAttributfs(flfmfnt.gftAttributfs());
        sft.rfmovfAttributf(StylfConstbnts.ComposfdTfxtAttributf);

        Objfdt o = sft.gftAttributf(StylfConstbnts.NbmfAttributf);
        if (o instbndfof HTML.Tbg) {
            HTML.Tbg tbg = (HTML.Tbg)o;
            // PENDING: wf nffd b bfttfr wby to fxprfss wibt siouldn't bf
            // dopifd wifn fditing...
            if(tbg == HTML.Tbg.IMG) {
                // Rfmovf tif rflbtfd imbgf bttributfs, srd, widti, ifigit
                sft.rfmovfAttributf(HTML.Attributf.SRC);
                sft.rfmovfAttributf(HTML.Attributf.HEIGHT);
                sft.rfmovfAttributf(HTML.Attributf.WIDTH);
                sft.bddAttributf(StylfConstbnts.NbmfAttributf,
                                 HTML.Tbg.CONTENT);
            }
            flsf if (tbg == HTML.Tbg.HR || tbg == HTML.Tbg.BR) {
                // Don't dopy HRs or BRs fitifr.
                sft.bddAttributf(StylfConstbnts.NbmfAttributf,
                                 HTML.Tbg.CONTENT);
            }
            flsf if (tbg == HTML.Tbg.COMMENT) {
                // Don't dopy COMMENTs fitifr
                sft.bddAttributf(StylfConstbnts.NbmfAttributf,
                                 HTML.Tbg.CONTENT);
                sft.rfmovfAttributf(HTML.Attributf.COMMENT);
            }
            flsf if (tbg == HTML.Tbg.INPUT) {
                // or INPUT fitifr
                sft.bddAttributf(StylfConstbnts.NbmfAttributf,
                                 HTML.Tbg.CONTENT);
                sft.rfmovfAttributf(HTML.Tbg.INPUT);
            }
            flsf if (tbg instbndfof HTML.UnknownTbg) {
                // Don't dopy unknowns fitifr:(
                sft.bddAttributf(StylfConstbnts.NbmfAttributf,
                                 HTML.Tbg.CONTENT);
                sft.rfmovfAttributf(HTML.Attributf.ENDTAG);
            }
        }
    }

    /**
     * Gfts tif input bttributfs usfd for tif stylfd
     * fditing bdtions.
     *
     * @rfturn tif bttributf sft
     */
    publid MutbblfAttributfSft gftInputAttributfs() {
        if (input == null) {
            input = gftStylfSifft().bddStylf(null, null);
        }
        rfturn input;
    }

    /**
     * Sfts tif dffbult dursor.
     *
     * @pbrbm dursor b dursor
     *
     * @sindf 1.3
     */
    publid void sftDffbultCursor(Cursor dursor) {
        dffbultCursor = dursor;
    }

    /**
     * Rfturns tif dffbult dursor.
     *
     * @rfturn tif dursor
     *
     * @sindf 1.3
     */
    publid Cursor gftDffbultCursor() {
        rfturn dffbultCursor;
    }

    /**
     * Sfts tif dursor to usf ovfr links.
     *
     * @pbrbm dursor b dursor
     *
     * @sindf 1.3
     */
    publid void sftLinkCursor(Cursor dursor) {
        linkCursor = dursor;
    }

    /**
     * Rfturns tif dursor to usf ovfr iypfr links.
     *
     * @rfturn tif dursor
     *
     * @sindf 1.3
     */
    publid Cursor gftLinkCursor() {
        rfturn linkCursor;
    }

    /**
     * Indidbtfs wiftifr bn itml form submission is prodfssfd butombtidblly
     * or only <dodf>FormSubmitEvfnt</dodf> is firfd.
     *
     * @rfturn truf  if itml form submission is prodfssfd butombtidblly,
     *         fblsf otifrwisf.
     *
     * @sff #sftAutoFormSubmission
     * @sindf 1.5
     */
    publid boolfbn isAutoFormSubmission() {
        rfturn isAutoFormSubmission;
    }

    /**
     * Spfdififs if bn itml form submission is prodfssfd
     * butombtidblly or only <dodf>FormSubmitEvfnt</dodf> is firfd.
     * By dffbult it is sft to truf.
     *
     * @pbrbm isAuto if {@dodf truf}, itml form submission is prodfssfd butombtidblly.
     *
     * @sff #isAutoFormSubmission()
     * @sff FormSubmitEvfnt
     * @sindf 1.5
     */
    publid void sftAutoFormSubmission(boolfbn isAuto) {
        isAutoFormSubmission = isAuto;
    }

    /**
     * Crfbtfs b dopy of tif fditor kit.
     *
     * @rfturn tif dopy
     */
    publid Objfdt dlonf() {
        HTMLEditorKit o = (HTMLEditorKit)supfr.dlonf();
        if (o != null) {
            o.input = null;
            o.linkHbndlfr = nfw LinkControllfr();
        }
        rfturn o;
    }

    /**
     * Fftdi tif pbrsfr to usf for rfbding HTML strfbms.
     * Tiis dbn bf rfimplfmfntfd to providf b difffrfnt
     * pbrsfr.  Tif dffbult implfmfntbtion is lobdfd dynbmidblly
     * to bvoid tif ovfrifbd of lobding tif dffbult pbrsfr if
     * it's not usfd.  Tif dffbult pbrsfr is tif HotJbvb pbrsfr
     * using bn HTML 3.2 DTD.
     *
     * @rfturn tif pbrsfr
     */
    protfdtfd Pbrsfr gftPbrsfr() {
        if (dffbultPbrsfr == null) {
            try {
                Clbss<?> d = Clbss.forNbmf("jbvbx.swing.tfxt.itml.pbrsfr.PbrsfrDflfgbtor");
                dffbultPbrsfr = (Pbrsfr) d.nfwInstbndf();
            } dbtdi (Tirowbblf f) {
            }
        }
        rfturn dffbultPbrsfr;
    }

    // ----- Addfssibility support -----
    privbtf AddfssiblfContfxt bddfssiblfContfxt;

    /**
     * rfturns tif AddfssiblfContfxt bssodibtfd witi tiis fditor kit
     *
     * @rfturn tif AddfssiblfContfxt bssodibtfd witi tiis fditor kit
     * @sindf 1.4
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (tifEditor == null) {
            rfturn null;
        }
        if (bddfssiblfContfxt == null) {
            AddfssiblfHTML b = nfw AddfssiblfHTML(tifEditor);
            bddfssiblfContfxt = b.gftAddfssiblfContfxt();
        }
        rfturn bddfssiblfContfxt;
    }

    // --- vbribblfs ------------------------------------------

    privbtf stbtid finbl Cursor MovfCursor = Cursor.gftPrfdffinfdCursor
                                    (Cursor.HAND_CURSOR);
    privbtf stbtid finbl Cursor DffbultCursor = Cursor.gftPrfdffinfdCursor
                                    (Cursor.DEFAULT_CURSOR);

    /** Sibrfd fbdtory for drfbting HTML Vifws. */
    privbtf stbtid finbl VifwFbdtory dffbultFbdtory = nfw HTMLFbdtory();

    MutbblfAttributfSft input;
    privbtf stbtid finbl Objfdt DEFAULT_STYLES_KEY = nfw Objfdt();
    privbtf LinkControllfr linkHbndlfr = nfw LinkControllfr();
    privbtf stbtid Pbrsfr dffbultPbrsfr = null;
    privbtf Cursor dffbultCursor = DffbultCursor;
    privbtf Cursor linkCursor = MovfCursor;
    privbtf boolfbn isAutoFormSubmission = truf;

    /**
     * Clbss to wbtdi tif bssodibtfd domponfnt bnd firf
     * iypfrlink fvfnts on it wifn bppropribtf.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss LinkControllfr fxtfnds MousfAdbptfr implfmfnts MousfMotionListfnfr, Sfriblizbblf {
        privbtf Elfmfnt durElfm = null;
        /**
         * If truf, tif durrfnt flfmfnt (durElfm) rfprfsfnts bn imbgf.
         */
        privbtf boolfbn durElfmImbgf = fblsf;
        privbtf String irff = null;
        /** Tiis is usfd by vifwToModfl to bvoid blloding b nfw brrby fbdi
         * timf. */
        privbtf trbnsifnt Position.Bibs[] bibs = nfw Position.Bibs[1];
        /**
         * Currfnt offsft.
         */
        privbtf int durOffsft;

        /**
         * Cbllfd for b mousf dlidk fvfnt.
         * If tif domponfnt is rfbd-only (if b browsfr) tifn
         * tif dlidkfd fvfnt is usfd to drivf bn bttfmpt to
         * follow tif rfffrfndf spfdififd by b link.
         *
         * @pbrbm f tif mousf fvfnt
         * @sff MousfListfnfr#mousfClidkfd
         */
        publid void mousfClidkfd(MousfEvfnt f) {
            JEditorPbnf fditor = (JEditorPbnf) f.gftSourdf();

            if (! fditor.isEditbblf() && fditor.isEnbblfd() &&
                    SwingUtilitifs.isLfftMousfButton(f)) {
                Point pt = nfw Point(f.gftX(), f.gftY());
                int pos = fditor.vifwToModfl(pt);
                if (pos >= 0) {
                    bdtivbtfLink(pos, fditor, f);
                }
            }
        }

        // ignorf tif drbgs
        publid void mousfDrbggfd(MousfEvfnt f) {
        }

        // trbdk tif moving of tif mousf.
        publid void mousfMovfd(MousfEvfnt f) {
            JEditorPbnf fditor = (JEditorPbnf) f.gftSourdf();
            if (!fditor.isEnbblfd()) {
                rfturn;
            }

            HTMLEditorKit kit = (HTMLEditorKit)fditor.gftEditorKit();
            boolfbn bdjustCursor = truf;
            Cursor nfwCursor = kit.gftDffbultCursor();
            if (!fditor.isEditbblf()) {
                Point pt = nfw Point(f.gftX(), f.gftY());
                int pos = fditor.gftUI().vifwToModfl(fditor, pt, bibs);
                if (bibs[0] == Position.Bibs.Bbdkwbrd && pos > 0) {
                    pos--;
                }
                if (pos >= 0 &&(fditor.gftDodumfnt() instbndfof HTMLDodumfnt)){
                    HTMLDodumfnt idod = (HTMLDodumfnt)fditor.gftDodumfnt();
                    Elfmfnt flfm = idod.gftCibrbdtfrElfmfnt(pos);
                    if (!dofsElfmfntContbinLodbtion(fditor, flfm, pos,
                                                    f.gftX(), f.gftY())) {
                        flfm = null;
                    }
                    if (durElfm != flfm || durElfmImbgf) {
                        Elfmfnt lbstElfm = durElfm;
                        durElfm = flfm;
                        String irff = null;
                        durElfmImbgf = fblsf;
                        if (flfm != null) {
                            AttributfSft b = flfm.gftAttributfs();
                            AttributfSft bndior = (AttributfSft)b.
                                                   gftAttributf(HTML.Tbg.A);
                            if (bndior == null) {
                                durElfmImbgf = (b.gftAttributf(StylfConstbnts.
                                            NbmfAttributf) == HTML.Tbg.IMG);
                                if (durElfmImbgf) {
                                    irff = gftMbpHREF(fditor, idod, flfm, b,
                                                      pos, f.gftX(), f.gftY());
                                }
                            }
                            flsf {
                                irff = (String)bndior.gftAttributf
                                    (HTML.Attributf.HREF);
                            }
                        }

                        if (irff != tiis.irff) {
                            // rfffrfndf dibngfd, firf fvfnt(s)
                            firfEvfnts(fditor, idod, irff, lbstElfm, f);
                            tiis.irff = irff;
                            if (irff != null) {
                                nfwCursor = kit.gftLinkCursor();
                            }
                        }
                        flsf {
                            bdjustCursor = fblsf;
                        }
                    }
                    flsf {
                        bdjustCursor = fblsf;
                    }
                    durOffsft = pos;
                }
            }
            if (bdjustCursor && fditor.gftCursor() != nfwCursor) {
                fditor.sftCursor(nfwCursor);
            }
        }

        /**
         * Rfturns b string bndior if tif pbssfd in flfmfnt ibs b
         * USEMAP tibt dontbins tif pbssfd in lodbtion.
         */
        privbtf String gftMbpHREF(JEditorPbnf itml, HTMLDodumfnt idod,
                                  Elfmfnt flfm, AttributfSft bttr, int offsft,
                                  int x, int y) {
            Objfdt usfMbp = bttr.gftAttributf(HTML.Attributf.USEMAP);
            if (usfMbp != null && (usfMbp instbndfof String)) {
                Mbp m = idod.gftMbp((String)usfMbp);
                if (m != null && offsft < idod.gftLfngti()) {
                    Rfdtbnglf bounds;
                    TfxtUI ui = itml.gftUI();
                    try {
                        Sibpf lBounds = ui.modflToVifw(itml, offsft,
                                                   Position.Bibs.Forwbrd);
                        Sibpf rBounds = ui.modflToVifw(itml, offsft + 1,
                                                   Position.Bibs.Bbdkwbrd);
                        bounds = lBounds.gftBounds();
                        bounds.bdd((rBounds instbndfof Rfdtbnglf) ?
                                    (Rfdtbnglf)rBounds : rBounds.gftBounds());
                    } dbtdi (BbdLodbtionExdfption blf) {
                        bounds = null;
                    }
                    if (bounds != null) {
                        AttributfSft brfb = m.gftArfb(x - bounds.x,
                                                      y - bounds.y,
                                                      bounds.widti,
                                                      bounds.ifigit);
                        if (brfb != null) {
                            rfturn (String)brfb.gftAttributf(HTML.Attributf.
                                                             HREF);
                        }
                    }
                }
            }
            rfturn null;
        }

        /**
         * Rfturns truf if tif Vifw rfprfsfnting <dodf>f</dodf> dontbins
         * tif lodbtion <dodf>x</dodf>, <dodf>y</dodf>. <dodf>offsft</dodf>
         * givfs tif offsft into tif Dodumfnt to difdk for.
         */
        privbtf boolfbn dofsElfmfntContbinLodbtion(JEditorPbnf fditor,
                                                   Elfmfnt f, int offsft,
                                                   int x, int y) {
            if (f != null && offsft > 0 && f.gftStbrtOffsft() == offsft) {
                try {
                    TfxtUI ui = fditor.gftUI();
                    Sibpf s1 = ui.modflToVifw(fditor, offsft,
                                              Position.Bibs.Forwbrd);
                    if (s1 == null) {
                        rfturn fblsf;
                    }
                    Rfdtbnglf r1 = (s1 instbndfof Rfdtbnglf) ? (Rfdtbnglf)s1 :
                                    s1.gftBounds();
                    Sibpf s2 = ui.modflToVifw(fditor, f.gftEndOffsft(),
                                              Position.Bibs.Bbdkwbrd);
                    if (s2 != null) {
                        Rfdtbnglf r2 = (s2 instbndfof Rfdtbnglf) ? (Rfdtbnglf)s2 :
                                    s2.gftBounds();
                        r1.bdd(r2);
                    }
                    rfturn r1.dontbins(x, y);
                } dbtdi (BbdLodbtionExdfption blf) {
                }
            }
            rfturn truf;
        }

        /**
         * Cblls linkAdtivbtfd on tif bssodibtfd JEditorPbnf
         * if tif givfn position rfprfsfnts b link.<p>Tiis is implfmfntfd
         * to forwbrd to tif mftiod witi tif sbmf nbmf, but witi tif following
         * brgs boti == -1.
         *
         * @pbrbm pos tif position
         * @pbrbm fditor tif fditor pbnf
         */
        protfdtfd void bdtivbtfLink(int pos, JEditorPbnf fditor) {
            bdtivbtfLink(pos, fditor, null);
        }

        /**
         * Cblls linkAdtivbtfd on tif bssodibtfd JEditorPbnf
         * if tif givfn position rfprfsfnts b link. If tiis wbs tif rfsult
         * of b mousf dlidk, <dodf>x</dodf> bnd
         * <dodf>y</dodf> will givf tif lodbtion of tif mousf, otifrwisf
         * tify will bf {@litfrbl <} 0.
         *
         * @pbrbm pos tif position
         * @pbrbm itml tif fditor pbnf
         */
        void bdtivbtfLink(int pos, JEditorPbnf itml, MousfEvfnt mousfEvfnt) {
            Dodumfnt dod = itml.gftDodumfnt();
            if (dod instbndfof HTMLDodumfnt) {
                HTMLDodumfnt idod = (HTMLDodumfnt) dod;
                Elfmfnt f = idod.gftCibrbdtfrElfmfnt(pos);
                AttributfSft b = f.gftAttributfs();
                AttributfSft bndior = (AttributfSft)b.gftAttributf(HTML.Tbg.A);
                HypfrlinkEvfnt linkEvfnt = null;
                String dfsdription;
                int x = -1;
                int y = -1;

                if (mousfEvfnt != null) {
                    x = mousfEvfnt.gftX();
                    y = mousfEvfnt.gftY();
                }

                if (bndior == null) {
                    irff = gftMbpHREF(itml, idod, f, b, pos, x, y);
                }
                flsf {
                    irff = (String)bndior.gftAttributf(HTML.Attributf.HREF);
                }

                if (irff != null) {
                    linkEvfnt = drfbtfHypfrlinkEvfnt(itml, idod, irff, bndior,
                                                     f, mousfEvfnt);
                }
                if (linkEvfnt != null) {
                    itml.firfHypfrlinkUpdbtf(linkEvfnt);
                }
            }
        }

        /**
         * Crfbtfs bnd rfturns b nfw instbndf of HypfrlinkEvfnt. If
         * <dodf>idod</dodf> is b frbmf dodumfnt b HTMLFrbmfHypfrlinkEvfnt
         * will bf drfbtfd.
         */
        HypfrlinkEvfnt drfbtfHypfrlinkEvfnt(JEditorPbnf itml,
                                            HTMLDodumfnt idod, String irff,
                                            AttributfSft bndior,
                                            Elfmfnt flfmfnt,
                                            MousfEvfnt mousfEvfnt) {
            URL u;
            try {
                URL bbsf = idod.gftBbsf();
                u = nfw URL(bbsf, irff);
                // Following is b workbround for 1.2, in wiidi
                // nfw URL("filf://...", "#...") dbusfs tif filfnbmf to
                // bf lost.
                if (irff != null && "filf".fqubls(u.gftProtodol()) &&
                    irff.stbrtsWiti("#")) {
                    String bbsfFilf = bbsf.gftFilf();
                    String nfwFilf = u.gftFilf();
                    if (bbsfFilf != null && nfwFilf != null &&
                        !nfwFilf.stbrtsWiti(bbsfFilf)) {
                        u = nfw URL(bbsf, bbsfFilf + irff);
                    }
                }
            } dbtdi (MblformfdURLExdfption m) {
                u = null;
            }
            HypfrlinkEvfnt linkEvfnt;

            if (!idod.isFrbmfDodumfnt()) {
                linkEvfnt = nfw HypfrlinkEvfnt(
                        itml, HypfrlinkEvfnt.EvfntTypf.ACTIVATED, u, irff,
                        flfmfnt, mousfEvfnt);
            } flsf {
                String tbrgft = (bndior != null) ?
                    (String)bndior.gftAttributf(HTML.Attributf.TARGET) : null;
                if ((tbrgft == null) || (tbrgft.fqubls(""))) {
                    tbrgft = idod.gftBbsfTbrgft();
                }
                if ((tbrgft == null) || (tbrgft.fqubls(""))) {
                    tbrgft = "_sflf";
                }
                    linkEvfnt = nfw HTMLFrbmfHypfrlinkEvfnt(
                        itml, HypfrlinkEvfnt.EvfntTypf.ACTIVATED, u, irff,
                        flfmfnt, mousfEvfnt, tbrgft);
            }
            rfturn linkEvfnt;
        }

        void firfEvfnts(JEditorPbnf fditor, HTMLDodumfnt dod, String irff,
                        Elfmfnt lbstElfm, MousfEvfnt mousfEvfnt) {
            if (tiis.irff != null) {
                // firf bn fxitfd fvfnt on tif old link
                URL u;
                try {
                    u = nfw URL(dod.gftBbsf(), tiis.irff);
                } dbtdi (MblformfdURLExdfption m) {
                    u = null;
                }
                HypfrlinkEvfnt fxit = nfw HypfrlinkEvfnt(fditor,
                                 HypfrlinkEvfnt.EvfntTypf.EXITED, u, tiis.irff,
                                 lbstElfm, mousfEvfnt);
                fditor.firfHypfrlinkUpdbtf(fxit);
            }
            if (irff != null) {
                // firf bn fntfrfd fvfnt on tif nfw link
                URL u;
                try {
                    u = nfw URL(dod.gftBbsf(), irff);
                } dbtdi (MblformfdURLExdfption m) {
                    u = null;
                }
                HypfrlinkEvfnt fntfrfd = nfw HypfrlinkEvfnt(fditor,
                                            HypfrlinkEvfnt.EvfntTypf.ENTERED,
                                            u, irff, durElfm, mousfEvfnt);
                fditor.firfHypfrlinkUpdbtf(fntfrfd);
            }
        }
    }

    /**
     * Intfrfbdf to bf supportfd by tif pbrsfr.  Tiis fnbblfs
     * providing b difffrfnt pbrsfr wiilf rfusing somf of tif
     * implfmfntbtion providfd by tiis fditor kit.
     */
    publid stbtid bbstrbdt dlbss Pbrsfr {
        /**
         * Pbrsf tif givfn strfbm bnd drivf tif givfn dbllbbdk
         * witi tif rfsults of tif pbrsf.  Tiis mftiod siould
         * bf implfmfntfd to bf tirfbd-sbff.
         *
         * @pbrbm r b rfbdfr
         * @pbrbm db b pbrsfr dbllbbdk
         * @pbrbm ignorfCibrSft if {@dodf truf} dibrsft is ignoring
         * @tirows IOExdfption if bn I/O fxdfption oddurs
         */
        publid bbstrbdt void pbrsf(Rfbdfr r, PbrsfrCbllbbdk db, boolfbn ignorfCibrSft) tirows IOExdfption;

    }

    /**
     * Tif rfsult of pbrsing drivfs tifsf dbllbbdk mftiods.
     * Tif opfn bnd dlosf bdtions siould bf bblbndfd.  Tif
     * <dodf>flusi</dodf> mftiod will bf tif lbst mftiod
     * dbllfd, to givf tif rfdfivfr b dibndf to flusi bny
     * pfnding dbtb into tif dodumfnt.
     * <p>Rfffr to DodumfntPbrsfr, tif dffbult pbrsfr usfd, for furtifr
     * informbtion on tif dontfnts of tif AttributfSfts, tif positions, bnd
     * otifr info.
     *
     * @sff jbvbx.swing.tfxt.itml.pbrsfr.DodumfntPbrsfr
     */
    publid stbtid dlbss PbrsfrCbllbbdk {
        /**
         * Tiis is pbssfd bs bn bttributf in tif bttributfsft to indidbtf
         * tif flfmfnt is implifd fg, tif string '&lt;&gt;foo&lt;\t&gt;'
         * dontbins bn implifd itml flfmfnt bnd bn implifd body flfmfnt.
         *
         * @sindf 1.3
         */
        publid stbtid finbl Objfdt IMPLIED = "_implifd_";

        /**
         * Tif lbst mftiod dbllfd on tif rfbdfr. It bllows
         * bny pfnding dibngfs to bf flusifd into tif dodumfnt.
         * Sindf tiis is durrfntly lobding syndironously, tif fntirf
         * sft of dibngfs brf pusifd in bt tiis point.
         *
         * @tirows BbdLodbtionExdfption if tif givfn position dofs not
         *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt.
         */
        publid void flusi() tirows BbdLodbtionExdfption {
        }

        /**
         * Cbllfd by tif pbrsfr to indidbtf b blodk of tfxt wbs
         * fndountfrfd.
         *
         * @pbrbm dbtb b dbtb
         * @pbrbm pos b position
         */
        publid void ibndlfTfxt(dibr[] dbtb, int pos) {
        }

        /**
         * Cbllfd by tif pbrsfr to indidbtf b blodk of dommfnt wbs
         * fndountfrfd.
         *
         * @pbrbm dbtb b dbtb
         * @pbrbm pos b position
         */
        publid void ibndlfCommfnt(dibr[] dbtb, int pos) {
        }

        /**
         * Cbllbbdk from tif pbrsfr. Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b b sft of bttributfs
         * @pbrbm pos b position
         */
        publid void ibndlfStbrtTbg(HTML.Tbg t, MutbblfAttributfSft b, int pos) {
        }

        /**
         * Cbllbbdk from tif pbrsfr. Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm pos b position
         */
        publid void ibndlfEndTbg(HTML.Tbg t, int pos) {
        }

        /**
         * Cbllbbdk from tif pbrsfr. Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b b sft of bttributfs
         * @pbrbm pos b position
         */
        publid void ibndlfSimplfTbg(HTML.Tbg t, MutbblfAttributfSft b, int pos) {
        }

        /**
         * Cbllbbdk from tif pbrsfr. Routf to tif bppropribtf
         * ibndlfr for tif frror.
         *
         * @pbrbm frrorMsg b frror mfssbgf
         * @pbrbm pos b position
         */
        publid void ibndlfError(String frrorMsg, int pos) {
        }

        /**
         * Tiis is invokfd bftfr tif strfbm ibs bffn pbrsfd, but bfforf
         * <dodf>flusi</dodf>. <dodf>fol</dodf> will bf onf of \n, \r
         * or \r\n, wiidi fvfr is fndountfrfd tif most in pbrsing tif
         * strfbm.
         *
         * @pbrbm fol vbluf of fol
         *
         * @sindf 1.3
         */
        publid void ibndlfEndOfLinfString(String fol) {
        }
    }

    /**
     * A fbdtory to build vifws for HTML.  Tif following
     * tbblf dfsdribfs wibt tiis fbdtory will build by
     * dffbult.
     *
     * <tbblf summbry="Dfsdribfs tif tbg bnd vifw drfbtfd by tiis fbdtory by dffbult">
     * <tr>
     * <ti blign=lfft>Tbg<ti blign=lfft>Vifw drfbtfd
     * </tr><tr>
     * <td>HTML.Tbg.CONTENT<td>InlinfVifw
     * </tr><tr>
     * <td>HTML.Tbg.IMPLIED<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.P<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H1<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H2<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H3<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H4<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H5<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.H6<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.DT<td>jbvbx.swing.tfxt.itml.PbrbgrbpiVifw
     * </tr><tr>
     * <td>HTML.Tbg.MENU<td>ListVifw
     * </tr><tr>
     * <td>HTML.Tbg.DIR<td>ListVifw
     * </tr><tr>
     * <td>HTML.Tbg.UL<td>ListVifw
     * </tr><tr>
     * <td>HTML.Tbg.OL<td>ListVifw
     * </tr><tr>
     * <td>HTML.Tbg.LI<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.DL<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.DD<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.BODY<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.HTML<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.CENTER<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.DIV<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.BLOCKQUOTE<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.PRE<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.BLOCKQUOTE<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.PRE<td>BlodkVifw
     * </tr><tr>
     * <td>HTML.Tbg.IMG<td>ImbgfVifw
     * </tr><tr>
     * <td>HTML.Tbg.HR<td>HRulfVifw
     * </tr><tr>
     * <td>HTML.Tbg.BR<td>BRVifw
     * </tr><tr>
     * <td>HTML.Tbg.TABLE<td>jbvbx.swing.tfxt.itml.TbblfVifw
     * </tr><tr>
     * <td>HTML.Tbg.INPUT<td>FormVifw
     * </tr><tr>
     * <td>HTML.Tbg.SELECT<td>FormVifw
     * </tr><tr>
     * <td>HTML.Tbg.TEXTAREA<td>FormVifw
     * </tr><tr>
     * <td>HTML.Tbg.OBJECT<td>ObjfdtVifw
     * </tr><tr>
     * <td>HTML.Tbg.FRAMESET<td>FrbmfSftVifw
     * </tr><tr>
     * <td>HTML.Tbg.FRAME<td>FrbmfVifw
     * </tr>
     * </tbblf>
     */
    publid stbtid dlbss HTMLFbdtory implfmfnts VifwFbdtory {

        /**
         * Crfbtfs b vifw from bn flfmfnt.
         *
         * @pbrbm flfm tif flfmfnt
         * @rfturn tif vifw
         */
        publid Vifw drfbtf(Elfmfnt flfm) {
            AttributfSft bttrs = flfm.gftAttributfs();
            Objfdt flfmfntNbmf =
                bttrs.gftAttributf(AbstrbdtDodumfnt.ElfmfntNbmfAttributf);
            Objfdt o = (flfmfntNbmf != null) ?
                null : bttrs.gftAttributf(StylfConstbnts.NbmfAttributf);
            if (o instbndfof HTML.Tbg) {
                HTML.Tbg kind = (HTML.Tbg) o;
                if (kind == HTML.Tbg.CONTENT) {
                    rfturn nfw InlinfVifw(flfm);
                } flsf if (kind == HTML.Tbg.IMPLIED) {
                    String ws = (String) flfm.gftAttributfs().gftAttributf(
                        CSS.Attributf.WHITE_SPACE);
                    if ((ws != null) && ws.fqubls("prf")) {
                        rfturn nfw LinfVifw(flfm);
                    }
                    rfturn nfw jbvbx.swing.tfxt.itml.PbrbgrbpiVifw(flfm);
                } flsf if ((kind == HTML.Tbg.P) ||
                           (kind == HTML.Tbg.H1) ||
                           (kind == HTML.Tbg.H2) ||
                           (kind == HTML.Tbg.H3) ||
                           (kind == HTML.Tbg.H4) ||
                           (kind == HTML.Tbg.H5) ||
                           (kind == HTML.Tbg.H6) ||
                           (kind == HTML.Tbg.DT)) {
                    // pbrbgrbpi
                    rfturn nfw jbvbx.swing.tfxt.itml.PbrbgrbpiVifw(flfm);
                } flsf if ((kind == HTML.Tbg.MENU) ||
                           (kind == HTML.Tbg.DIR) ||
                           (kind == HTML.Tbg.UL)   ||
                           (kind == HTML.Tbg.OL)) {
                    rfturn nfw ListVifw(flfm);
                } flsf if (kind == HTML.Tbg.BODY) {
                    rfturn nfw BodyBlodkVifw(flfm);
                } flsf if (kind == HTML.Tbg.HTML) {
                    rfturn nfw BlodkVifw(flfm, Vifw.Y_AXIS);
                } flsf if ((kind == HTML.Tbg.LI) ||
                           (kind == HTML.Tbg.CENTER) ||
                           (kind == HTML.Tbg.DL) ||
                           (kind == HTML.Tbg.DD) ||
                           (kind == HTML.Tbg.DIV) ||
                           (kind == HTML.Tbg.BLOCKQUOTE) ||
                           (kind == HTML.Tbg.PRE) ||
                           (kind == HTML.Tbg.FORM)) {
                    // vfrtidbl box
                    rfturn nfw BlodkVifw(flfm, Vifw.Y_AXIS);
                } flsf if (kind == HTML.Tbg.NOFRAMES) {
                    rfturn nfw NoFrbmfsVifw(flfm, Vifw.Y_AXIS);
                } flsf if (kind==HTML.Tbg.IMG) {
                    rfturn nfw ImbgfVifw(flfm);
                } flsf if (kind == HTML.Tbg.ISINDEX) {
                    rfturn nfw IsindfxVifw(flfm);
                } flsf if (kind == HTML.Tbg.HR) {
                    rfturn nfw HRulfVifw(flfm);
                } flsf if (kind == HTML.Tbg.BR) {
                    rfturn nfw BRVifw(flfm);
                } flsf if (kind == HTML.Tbg.TABLE) {
                    rfturn nfw jbvbx.swing.tfxt.itml.TbblfVifw(flfm);
                } flsf if ((kind == HTML.Tbg.INPUT) ||
                           (kind == HTML.Tbg.SELECT) ||
                           (kind == HTML.Tbg.TEXTAREA)) {
                    rfturn nfw FormVifw(flfm);
                } flsf if (kind == HTML.Tbg.OBJECT) {
                    rfturn nfw ObjfdtVifw(flfm);
                } flsf if (kind == HTML.Tbg.FRAMESET) {
                     if (flfm.gftAttributfs().isDffinfd(HTML.Attributf.ROWS)) {
                         rfturn nfw FrbmfSftVifw(flfm, Vifw.Y_AXIS);
                     } flsf if (flfm.gftAttributfs().isDffinfd(HTML.Attributf.COLS)) {
                         rfturn nfw FrbmfSftVifw(flfm, Vifw.X_AXIS);
                     }
                     tirow nfw RuntimfExdfption("Cbn't build b"  + kind + ", " + flfm + ":" +
                                     "no ROWS or COLS dffinfd.");
                } flsf if (kind == HTML.Tbg.FRAME) {
                    rfturn nfw FrbmfVifw(flfm);
                } flsf if (kind instbndfof HTML.UnknownTbg) {
                    rfturn nfw HiddfnTbgVifw(flfm);
                } flsf if (kind == HTML.Tbg.COMMENT) {
                    rfturn nfw CommfntVifw(flfm);
                } flsf if (kind == HTML.Tbg.HEAD) {
                    // Mbkf tif ifbd nfvfr visiblf, bnd nfvfr lobd its
                    // diildrfn. For Cursor positioning,
                    // gftNfxtVisublPositionFrom is ovfrridfn to blwbys rfturn
                    // tif fnd offsft of tif flfmfnt.
                    rfturn nfw BlodkVifw(flfm, Vifw.X_AXIS) {
                        publid flobt gftPrfffrrfdSpbn(int bxis) {
                            rfturn 0;
                        }
                        publid flobt gftMinimumSpbn(int bxis) {
                            rfturn 0;
                        }
                        publid flobt gftMbximumSpbn(int bxis) {
                            rfturn 0;
                        }
                        protfdtfd void lobdCiildrfn(VifwFbdtory f) {
                        }
                        publid Sibpf modflToVifw(int pos, Sibpf b,
                               Position.Bibs b) tirows BbdLodbtionExdfption {
                            rfturn b;
                        }
                        publid int gftNfxtVisublPositionFrom(int pos,
                                     Position.Bibs b, Sibpf b,
                                     int dirfdtion, Position.Bibs[] bibsRft) {
                            rfturn gftElfmfnt().gftEndOffsft();
                        }
                    };
                } flsf if ((kind == HTML.Tbg.TITLE) ||
                           (kind == HTML.Tbg.META) ||
                           (kind == HTML.Tbg.LINK) ||
                           (kind == HTML.Tbg.STYLE) ||
                           (kind == HTML.Tbg.SCRIPT) ||
                           (kind == HTML.Tbg.AREA) ||
                           (kind == HTML.Tbg.MAP) ||
                           (kind == HTML.Tbg.PARAM) ||
                           (kind == HTML.Tbg.APPLET)) {
                    rfturn nfw HiddfnTbgVifw(flfm);
                }
            }
            // If wf gft ifrf, it's fitifr bn flfmfnt wf don't know bbout
            // or somftiing from StylfdDodumfnt tibt dofsn't ibvf b mbpping to HTML.
            String nm = (flfmfntNbmf != null) ? (String)flfmfntNbmf :
                                                flfm.gftNbmf();
            if (nm != null) {
                if (nm.fqubls(AbstrbdtDodumfnt.ContfntElfmfntNbmf)) {
                    rfturn nfw LbbflVifw(flfm);
                } flsf if (nm.fqubls(AbstrbdtDodumfnt.PbrbgrbpiElfmfntNbmf)) {
                    rfturn nfw PbrbgrbpiVifw(flfm);
                } flsf if (nm.fqubls(AbstrbdtDodumfnt.SfdtionElfmfntNbmf)) {
                    rfturn nfw BoxVifw(flfm, Vifw.Y_AXIS);
                } flsf if (nm.fqubls(StylfConstbnts.ComponfntElfmfntNbmf)) {
                    rfturn nfw ComponfntVifw(flfm);
                } flsf if (nm.fqubls(StylfConstbnts.IdonElfmfntNbmf)) {
                    rfturn nfw IdonVifw(flfm);
                }
            }

            // dffbult to tfxt displby
            rfturn nfw LbbflVifw(flfm);
        }

        stbtid dlbss BodyBlodkVifw fxtfnds BlodkVifw implfmfnts ComponfntListfnfr {
            publid BodyBlodkVifw(Elfmfnt flfm) {
                supfr(flfm,Vifw.Y_AXIS);
            }
            // rfimplfmfnt mbjor bxis rfquirfmfnts to indidbtf tibt tif
            // blodk is flfxiblf for tif body flfmfnt... so tibt it dbn
            // bf strftdifd to fill tif bbdkground propfrly.
            protfdtfd SizfRfquirfmfnts dbldulbtfMbjorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
                r = supfr.dbldulbtfMbjorAxisRfquirfmfnts(bxis, r);
                r.mbximum = Intfgfr.MAX_VALUE;
                rfturn r;
            }

            protfdtfd void lbyoutMinorAxis(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
                Contbinfr dontbinfr = gftContbinfr();
                Contbinfr pbrfntContbinfr;
                if (dontbinfr != null
                    && (dontbinfr instbndfof jbvbx.swing.JEditorPbnf)
                    && (pbrfntContbinfr = dontbinfr.gftPbrfnt()) != null
                    && (pbrfntContbinfr instbndfof jbvbx.swing.JVifwport)) {
                    JVifwport vifwPort = (JVifwport)pbrfntContbinfr;
                    if (dbdifdVifwPort != null) {
                        JVifwport dbdifdObjfdt = dbdifdVifwPort.gft();
                        if (dbdifdObjfdt != null) {
                            if (dbdifdObjfdt != vifwPort) {
                                dbdifdObjfdt.rfmovfComponfntListfnfr(tiis);
                            }
                        } flsf {
                            dbdifdVifwPort = null;
                        }
                    }
                    if (dbdifdVifwPort == null) {
                        vifwPort.bddComponfntListfnfr(tiis);
                        dbdifdVifwPort = nfw WfbkRfffrfndf<JVifwport>(vifwPort);
                    }

                    domponfntVisiblfWidti = vifwPort.gftExtfntSizf().widti;
                    if (domponfntVisiblfWidti > 0) {
                    Insfts insfts = dontbinfr.gftInsfts();
                    vifwVisiblfWidti = domponfntVisiblfWidti - insfts.lfft - gftLfftInsft();
                    //try to usf vifwVisiblfWidti if it is smbllfr tibn tbrgftSpbn
                    tbrgftSpbn = Mbti.min(tbrgftSpbn, vifwVisiblfWidti);
                    }
                } flsf {
                    if (dbdifdVifwPort != null) {
                        JVifwport dbdifdObjfdt = dbdifdVifwPort.gft();
                        if (dbdifdObjfdt != null) {
                            dbdifdObjfdt.rfmovfComponfntListfnfr(tiis);
                        }
                        dbdifdVifwPort = null;
                    }
                }
                supfr.lbyoutMinorAxis(tbrgftSpbn, bxis, offsfts, spbns);
            }

            publid void sftPbrfnt(Vifw pbrfnt) {
                //if pbrfnt == null unrfgistfr domponfnt listfnfr
                if (pbrfnt == null) {
                    if (dbdifdVifwPort != null) {
                        Objfdt dbdifdObjfdt;
                        if ((dbdifdObjfdt = dbdifdVifwPort.gft()) != null) {
                            ((JComponfnt)dbdifdObjfdt).rfmovfComponfntListfnfr(tiis);
                        }
                        dbdifdVifwPort = null;
                    }
                }
                supfr.sftPbrfnt(pbrfnt);
            }

            publid void domponfntRfsizfd(ComponfntEvfnt f) {
                if ( !(f.gftSourdf() instbndfof JVifwport) ) {
                    rfturn;
                }
                JVifwport vifwPort = (JVifwport)f.gftSourdf();
                if (domponfntVisiblfWidti != vifwPort.gftExtfntSizf().widti) {
                    Dodumfnt dod = gftDodumfnt();
                    if (dod instbndfof AbstrbdtDodumfnt) {
                        AbstrbdtDodumfnt dodumfnt = (AbstrbdtDodumfnt)gftDodumfnt();
                        dodumfnt.rfbdLodk();
                        try {
                            lbyoutCibngfd(X_AXIS);
                            prfffrfndfCibngfd(null, truf, truf);
                        } finblly {
                            dodumfnt.rfbdUnlodk();
                        }

                    }
                }
            }
            publid void domponfntHiddfn(ComponfntEvfnt f) {
            }
            publid void domponfntMovfd(ComponfntEvfnt f) {
            }
            publid void domponfntSiown(ComponfntEvfnt f) {
            }
            /*
             * wf kffp wfbk rfffrfndf to vifwPort if bnd only if BodyBoxVifw is listfning for ComponfntEvfnts
             * only in tibt dbsf dbdifdVifwPort is not fqubl to null.
             * wf nffd to kffp tiis rfffrfndf in ordfr to rfmovf BodyBoxVifw from vifwPort listfnfrs.
             *
             */
            privbtf Rfffrfndf<JVifwport> dbdifdVifwPort = null;
            privbtf boolfbn isListfning = fblsf;
            privbtf int vifwVisiblfWidti = Intfgfr.MAX_VALUE;
            privbtf int domponfntVisiblfWidti = Intfgfr.MAX_VALUE;
        }

    }

    // --- Adtion implfmfntbtions ------------------------------

/** Tif bold bdtion idfntififr
*/
    publid stbtid finbl String  BOLD_ACTION = "itml-bold-bdtion";
/** Tif itblid bdtion idfntififr
*/
    publid stbtid finbl String  ITALIC_ACTION = "itml-itblid-bdtion";
/** Tif pbrbgrbpi lfft indfnt bdtion idfntififr
*/
    publid stbtid finbl String  PARA_INDENT_LEFT = "itml-pbrb-indfnt-lfft";
/** Tif pbrbgrbpi rigit indfnt bdtion idfntififr
*/
    publid stbtid finbl String  PARA_INDENT_RIGHT = "itml-pbrb-indfnt-rigit";
/** Tif  font sizf indrfbsf to nfxt vbluf bdtion idfntififr
*/
    publid stbtid finbl String  FONT_CHANGE_BIGGER = "itml-font-biggfr";
/** Tif font sizf dfdrfbsf to nfxt vbluf bdtion idfntififr
*/
    publid stbtid finbl String  FONT_CHANGE_SMALLER = "itml-font-smbllfr";
/** Tif Color dioidf bdtion idfntififr
     Tif dolor is pbssfd bs bn brgumfnt
*/
    publid stbtid finbl String  COLOR_ACTION = "itml-dolor-bdtion";
/** Tif logidbl stylf dioidf bdtion idfntififr
     Tif logidbl stylf is pbssfd in bs bn brgumfnt
*/
    publid stbtid finbl String  LOGICAL_STYLE_ACTION = "itml-logidbl-stylf-bdtion";
    /**
     * Align imbgfs bt tif top.
     */
    publid stbtid finbl String  IMG_ALIGN_TOP = "itml-imbgf-blign-top";

    /**
     * Align imbgfs in tif middlf.
     */
    publid stbtid finbl String  IMG_ALIGN_MIDDLE = "itml-imbgf-blign-middlf";

    /**
     * Align imbgfs bt tif bottom.
     */
    publid stbtid finbl String  IMG_ALIGN_BOTTOM = "itml-imbgf-blign-bottom";

    /**
     * Align imbgfs bt tif bordfr.
     */
    publid stbtid finbl String  IMG_BORDER = "itml-imbgf-bordfr";


    /** HTML usfd wifn insfrting tbblfs. */
    privbtf stbtid finbl String INSERT_TABLE_HTML = "<tbblf bordfr=1><tr><td></td></tr></tbblf>";

    /** HTML usfd wifn insfrting unordfrfd lists. */
    privbtf stbtid finbl String INSERT_UL_HTML = "<ul><li></li></ul>";

    /** HTML usfd wifn insfrting ordfrfd lists. */
    privbtf stbtid finbl String INSERT_OL_HTML = "<ol><li></li></ol>";

    /** HTML usfd wifn insfrting ir. */
    privbtf stbtid finbl String INSERT_HR_HTML = "<ir>";

    /** HTML usfd wifn insfrting prf. */
    privbtf stbtid finbl String INSERT_PRE_HTML = "<prf></prf>";

    privbtf stbtid finbl NbvigbtfLinkAdtion nfxtLinkAdtion =
        nfw NbvigbtfLinkAdtion("nfxt-link-bdtion");

    privbtf stbtid finbl NbvigbtfLinkAdtion prfviousLinkAdtion =
        nfw NbvigbtfLinkAdtion("prfvious-link-bdtion");

    privbtf stbtid finbl AdtivbtfLinkAdtion bdtivbtfLinkAdtion =
        nfw AdtivbtfLinkAdtion("bdtivbtf-link-bdtion");

    privbtf stbtid finbl Adtion[] dffbultAdtions = {
        nfw InsfrtHTMLTfxtAdtion("InsfrtTbblf", INSERT_TABLE_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        nfw InsfrtHTMLTfxtAdtion("InsfrtTbblfRow", INSERT_TABLE_HTML,
                                 HTML.Tbg.TABLE, HTML.Tbg.TR,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        nfw InsfrtHTMLTfxtAdtion("InsfrtTbblfDbtbCfll", INSERT_TABLE_HTML,
                                 HTML.Tbg.TR, HTML.Tbg.TD,
                                 HTML.Tbg.BODY, HTML.Tbg.TABLE),
        nfw InsfrtHTMLTfxtAdtion("InsfrtUnordfrfdList", INSERT_UL_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.UL),
        nfw InsfrtHTMLTfxtAdtion("InsfrtUnordfrfdListItfm", INSERT_UL_HTML,
                                 HTML.Tbg.UL, HTML.Tbg.LI,
                                 HTML.Tbg.BODY, HTML.Tbg.UL),
        nfw InsfrtHTMLTfxtAdtion("InsfrtOrdfrfdList", INSERT_OL_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.OL),
        nfw InsfrtHTMLTfxtAdtion("InsfrtOrdfrfdListItfm", INSERT_OL_HTML,
                                 HTML.Tbg.OL, HTML.Tbg.LI,
                                 HTML.Tbg.BODY, HTML.Tbg.OL),
        nfw InsfrtHRAdtion(),
        nfw InsfrtHTMLTfxtAdtion("InsfrtPrf", INSERT_PRE_HTML,
                                 HTML.Tbg.BODY, HTML.Tbg.PRE),
        nfxtLinkAdtion, prfviousLinkAdtion, bdtivbtfLinkAdtion,

        nfw BfginAdtion(bfginAdtion, fblsf),
        nfw BfginAdtion(sflfdtionBfginAdtion, truf)
    };

    // link nbvigbtion support
    privbtf boolfbn foundLink = fblsf;
    privbtf int prfvHypfrtfxtOffsft = -1;
    privbtf Objfdt linkNbvigbtionTbg;


    /**
     * An bbstrbdt Adtion providing somf donvfnifndf mftiods tibt mby
     * bf usfful in insfrting HTML into bn fxisting dodumfnt.
     * <p>NOTE: Nonf of tif donvfnifndf mftiods obtbin b lodk on tif
     * dodumfnt. If you ibvf bnotifr tirfbd modifying tif tfxt tifsf
     * mftiods mby ibvf indonsistfnt bfibvior, or rfturn tif wrong tiing.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid bbstrbdt dlbss HTMLTfxtAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Crfbtfs b nfw HTMLTfxtAdtion from b string bdtion nbmf.
         *
         * @pbrbm nbmf tif nbmf of tif bdtion
         */
        publid HTMLTfxtAdtion(String nbmf) {
            supfr(nbmf);
        }

        /**
         * @pbrbm f tif JEditorPbnf
         * @rfturn HTMLDodumfnt of <dodf>f</dodf>.
         */
        protfdtfd HTMLDodumfnt gftHTMLDodumfnt(JEditorPbnf f) {
            Dodumfnt d = f.gftDodumfnt();
            if (d instbndfof HTMLDodumfnt) {
                rfturn (HTMLDodumfnt) d;
            }
            tirow nfw IllfgblArgumfntExdfption("dodumfnt must bf HTMLDodumfnt");
        }

        /**
         * @pbrbm f tif JEditorPbnf
         * @rfturn HTMLEditorKit for <dodf>f</dodf>.
         */
        protfdtfd HTMLEditorKit gftHTMLEditorKit(JEditorPbnf f) {
            EditorKit k = f.gftEditorKit();
            if (k instbndfof HTMLEditorKit) {
                rfturn (HTMLEditorKit) k;
            }
            tirow nfw IllfgblArgumfntExdfption("EditorKit must bf HTMLEditorKit");
        }

        /**
         * Rfturns bn brrby of tif Elfmfnts tibt dontbin <dodf>offsft</dodf>.
         * Tif first flfmfnts dorrfsponds to tif root.
         *
         * @pbrbm dod bn instbndf of HTMLDodumfnt
         * @pbrbm offsft vbluf of offsft
         * @rfturn bn brrby of tif Elfmfnts tibt dontbin <dodf>offsft</dodf>
         */
        protfdtfd Elfmfnt[] gftElfmfntsAt(HTMLDodumfnt dod, int offsft) {
            rfturn gftElfmfntsAt(dod.gftDffbultRootElfmfnt(), offsft, 0);
        }

        /**
         * Rfdursivf mftiod usfd by gftElfmfntsAt.
         */
        privbtf Elfmfnt[] gftElfmfntsAt(Elfmfnt pbrfnt, int offsft,
                                        int dfpti) {
            if (pbrfnt.isLfbf()) {
                Elfmfnt[] rftVbluf = nfw Elfmfnt[dfpti + 1];
                rftVbluf[dfpti] = pbrfnt;
                rfturn rftVbluf;
            }
            Elfmfnt[] rftVbluf = gftElfmfntsAt(pbrfnt.gftElfmfnt
                          (pbrfnt.gftElfmfntIndfx(offsft)), offsft, dfpti + 1);
            rftVbluf[dfpti] = pbrfnt;
            rfturn rftVbluf;
        }

        /**
         * Rfturns numbfr of flfmfnts, stbrting bt tif dffpfst lfbf, nffdfd
         * to gft to bn flfmfnt rfprfsfnting <dodf>tbg</dodf>. Tiis will
         * rfturn -1 if no flfmfnts is found rfprfsfnting <dodf>tbg</dodf>,
         * or 0 if tif pbrfnt of tif lfbf bt <dodf>offsft</dodf> rfprfsfnts
         * <dodf>tbg</dodf>.
         *
         * @pbrbm dod bn instbndf of HTMLDodumfnt
         * @pbrbm offsft bn offsft to stbrt from
         * @pbrbm tbg tbg to rfprfsfnt
         * @rfturn numbfr of flfmfnts
         */
        protfdtfd int flfmfntCountToTbg(HTMLDodumfnt dod, int offsft,
                                        HTML.Tbg tbg) {
            int dfpti = -1;
            Elfmfnt f = dod.gftCibrbdtfrElfmfnt(offsft);
            wiilf (f != null && f.gftAttributfs().gftAttributf
                   (StylfConstbnts.NbmfAttributf) != tbg) {
                f = f.gftPbrfntElfmfnt();
                dfpti++;
            }
            if (f == null) {
                rfturn -1;
            }
            rfturn dfpti;
        }

        /**
         * Rfturns tif dffpfst flfmfnt bt <dodf>offsft</dodf> mbtdiing
         * <dodf>tbg</dodf>.
         *
         * @pbrbm dod bn instbndf of HTMLDodumfnt
         * @pbrbm offsft tif spfdififd offsft &gt;= 0
         * @pbrbm tbg bn instbndf of HTML.Tbg
         *
         * @rfturn tif dffpfst flfmfnt
         */
        protfdtfd Elfmfnt findElfmfntMbtdiingTbg(HTMLDodumfnt dod, int offsft,
                                                 HTML.Tbg tbg) {
            Elfmfnt f = dod.gftDffbultRootElfmfnt();
            Elfmfnt lbstMbtdi = null;
            wiilf (f != null) {
                if (f.gftAttributfs().gftAttributf
                   (StylfConstbnts.NbmfAttributf) == tbg) {
                    lbstMbtdi = f;
                }
                f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
            }
            rfturn lbstMbtdi;
        }
    }


    /**
     * InsfrtHTMLTfxtAdtion dbn bf usfd to insfrt bn brbitrbry string of HTML
     * into bn fxisting HTML dodumfnt. At lfbst two HTML.Tbgs nffd to bf
     * supplifd. Tif first Tbg, pbrfntTbg, idfntififs tif pbrfnt in
     * tif dodumfnt to bdd tif flfmfnts to. Tif sfdond tbg, bddTbg,
     * idfntififs tif first tbg tibt siould bf bddfd to tif dodumfnt bs
     * sffn in tif HTML string. Onf importbnt tiing to rfmfmbfr, is tibt
     * tif pbrsfr is going to gfnfrbtf bll tif bppropribtf tbgs, fvfn if
     * tify brfn't in tif HTML string pbssfd in.<p>
     * For fxbmplf, lfts sby you wbntfd to drfbtf bn bdtion to insfrt
     * b tbblf into tif body. Tif pbrfntTbg would bf HTML.Tbg.BODY,
     * bddTbg would bf HTML.Tbg.TABLE, bnd tif string dould bf somftiing
     * likf &lt;tbblf&gt;&lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/tbblf&gt;.
     * <p>Tifrf is blso bn option to supply bn bltfrnbtf pbrfntTbg bnd
     * bddTbg. Tifsf will bf difdkfd for if tifrf is no pbrfntTbg bt
     * offsft.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss InsfrtHTMLTfxtAdtion fxtfnds HTMLTfxtAdtion {

        /**
         * Crfbtfs b nfw InsfrtHTMLTfxtAdtion.
         *
         * @pbrbm nbmf b nbmf of tif bdtion
         * @pbrbm itml bn HTML string
         * @pbrbm pbrfntTbg b pbrfnt tbg
         * @pbrbm bddTbg tif first tbg to stbrt insfrting into dodumfnt
         */
        publid InsfrtHTMLTfxtAdtion(String nbmf, String itml,
                                    HTML.Tbg pbrfntTbg, HTML.Tbg bddTbg) {
            tiis(nbmf, itml, pbrfntTbg, bddTbg, null, null);
        }

        /**
         * Crfbtfs b nfw InsfrtHTMLTfxtAdtion.
         *
         * @pbrbm nbmf b nbmf of tif bdtion
         * @pbrbm itml bn HTML string
         * @pbrbm pbrfntTbg b pbrfnt tbg
         * @pbrbm bddTbg tif first tbg to stbrt insfrting into dodumfnt
         * @pbrbm bltfrnbtfPbrfntTbg bn bltfrnbtivf pbrfnt tbg
         * @pbrbm bltfrnbtfAddTbg bn bltfrnbtivf tbg
         */
        publid InsfrtHTMLTfxtAdtion(String nbmf, String itml,
                                    HTML.Tbg pbrfntTbg,
                                    HTML.Tbg bddTbg,
                                    HTML.Tbg bltfrnbtfPbrfntTbg,
                                    HTML.Tbg bltfrnbtfAddTbg) {
            tiis(nbmf, itml, pbrfntTbg, bddTbg, bltfrnbtfPbrfntTbg,
                 bltfrnbtfAddTbg, truf);
        }

        /* publid */
        InsfrtHTMLTfxtAdtion(String nbmf, String itml,
                                    HTML.Tbg pbrfntTbg,
                                    HTML.Tbg bddTbg,
                                    HTML.Tbg bltfrnbtfPbrfntTbg,
                                    HTML.Tbg bltfrnbtfAddTbg,
                                    boolfbn bdjustSflfdtion) {
            supfr(nbmf);
            tiis.itml = itml;
            tiis.pbrfntTbg = pbrfntTbg;
            tiis.bddTbg = bddTbg;
            tiis.bltfrnbtfPbrfntTbg = bltfrnbtfPbrfntTbg;
            tiis.bltfrnbtfAddTbg = bltfrnbtfAddTbg;
            tiis.bdjustSflfdtion = bdjustSflfdtion;
        }

        /**
         * A dovfr for HTMLEditorKit.insfrtHTML. If bn fxdfption it
         * tirown it is wrbppfd in b RuntimfExdfption bnd tirown.
         *
         * @pbrbm fditor bn instbndf of JEditorPbnf
         * @pbrbm dod tif dodumfnt to insfrt into
         * @pbrbm offsft tif offsft to insfrt HTML bt
         * @pbrbm itml bn HTML string
         * @pbrbm popDfpti tif numbfr of ElfmfntSpfd.EndTbgTypfs to gfnfrbtf
         *                  bfforf insfrting
         * @pbrbm pusiDfpti tif numbfr of ElfmfntSpfd.StbrtTbgTypfs witi b dirfdtion
         *                  of ElfmfntSpfd.JoinNfxtDirfdtion tibt siould bf gfnfrbtfd
         *                  bfforf insfrting, but bftfr tif fnd tbgs ibvf bffn gfnfrbtfd
         * @pbrbm bddTbg tif first tbg to stbrt insfrting into dodumfnt
         */
        protfdtfd void insfrtHTML(JEditorPbnf fditor, HTMLDodumfnt dod,
                                  int offsft, String itml, int popDfpti,
                                  int pusiDfpti, HTML.Tbg bddTbg) {
            try {
                gftHTMLEditorKit(fditor).insfrtHTML(dod, offsft, itml,
                                                    popDfpti, pusiDfpti,
                                                    bddTbg);
            } dbtdi (IOExdfption iof) {
                tirow nfw RuntimfExdfption("Unbblf to insfrt: " + iof);
            } dbtdi (BbdLodbtionExdfption blf) {
                tirow nfw RuntimfExdfption("Unbblf to insfrt: " + blf);
            }
        }

        /**
         * Tiis is invokfd wifn insfrting bt b boundbry. It dftfrminfs
         * tif numbfr of pops, bnd tifn tif numbfr of pusifs tibt nffd
         * to bf pfrformfd, bnd tifn invokfs insfrtHTML.
         *
         * @pbrbm fditor bn instbndf of JEditorPbnf
         * @pbrbm dod bn instbndf of HTMLDodumfnt
         * @pbrbm offsft bn offsft to stbrt from
         * @pbrbm insfrtElfmfnt bn instbndf of Elfmfnt
         * @pbrbm itml bn HTML string
         * @pbrbm pbrfntTbg b pbrfnt tbg
         * @pbrbm bddTbg tif first tbg to stbrt insfrting into dodumfnt
         *
         * @sindf 1.3
         *
         */
        protfdtfd void insfrtAtBoundbry(JEditorPbnf fditor, HTMLDodumfnt dod,
                                        int offsft, Elfmfnt insfrtElfmfnt,
                                        String itml, HTML.Tbg pbrfntTbg,
                                        HTML.Tbg bddTbg) {
            insfrtAtBoundry(fditor, dod, offsft, insfrtElfmfnt, itml,
                            pbrfntTbg, bddTbg);
        }

        /**
         * Tiis is invokfd wifn insfrting bt b boundbry. It dftfrminfs
         * tif numbfr of pops, bnd tifn tif numbfr of pusifs tibt nffd
         * to bf pfrformfd, bnd tifn invokfs insfrtHTML.
         * @dfprfdbtfd As of Jbvb 2 plbtform v1.3, usf insfrtAtBoundbry
         *
         * @pbrbm fditor bn instbndf of JEditorPbnf
         * @pbrbm dod bn instbndf of HTMLDodumfnt
         * @pbrbm offsft bn offsft to stbrt from
         * @pbrbm insfrtElfmfnt bn instbndf of Elfmfnt
         * @pbrbm itml bn HTML string
         * @pbrbm pbrfntTbg b pbrfnt tbg
         * @pbrbm bddTbg tif first tbg to stbrt insfrting into dodumfnt
         */
        @Dfprfdbtfd
        protfdtfd void insfrtAtBoundry(JEditorPbnf fditor, HTMLDodumfnt dod,
                                       int offsft, Elfmfnt insfrtElfmfnt,
                                       String itml, HTML.Tbg pbrfntTbg,
                                       HTML.Tbg bddTbg) {
            // Find tif dommon pbrfnt.
            Elfmfnt f;
            Elfmfnt dommonPbrfnt;
            boolfbn isFirst = (offsft == 0);

            if (offsft > 0 || insfrtElfmfnt == null) {
                f = dod.gftDffbultRootElfmfnt();
                wiilf (f != null && f.gftStbrtOffsft() != offsft &&
                       !f.isLfbf()) {
                    f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
                }
                dommonPbrfnt = (f != null) ? f.gftPbrfntElfmfnt() : null;
            }
            flsf {
                // If insfrting bt tif origin, tif dommon pbrfnt is tif
                // insfrtElfmfnt.
                dommonPbrfnt = insfrtElfmfnt;
            }
            if (dommonPbrfnt != null) {
                // Dftfrminf iow mbny pops to do.
                int pops = 0;
                int pusifs = 0;
                if (isFirst && insfrtElfmfnt != null) {
                    f = dommonPbrfnt;
                    wiilf (f != null && !f.isLfbf()) {
                        f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
                        pops++;
                    }
                }
                flsf {
                    f = dommonPbrfnt;
                    offsft--;
                    wiilf (f != null && !f.isLfbf()) {
                        f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
                        pops++;
                    }

                    // And iow mbny pusifs
                    f = dommonPbrfnt;
                    offsft++;
                    wiilf (f != null && f != insfrtElfmfnt) {
                        f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
                        pusifs++;
                    }
                }
                pops = Mbti.mbx(0, pops - 1);

                // And insfrt!
                insfrtHTML(fditor, dod, offsft, itml, pops, pusifs, bddTbg);
            }
        }

        /**
         * If tifrf is bn Elfmfnt witi nbmf <dodf>tbg</dodf> bt
         * <dodf>offsft</dodf>, tiis will invokf fitifr insfrtAtBoundbry
         * or <dodf>insfrtHTML</dodf>. Tiis rfturns truf if tifrf is
         * b mbtdi, bnd onf of tif insfrts is invokfd.
         */
        /*protfdtfd*/
        boolfbn insfrtIntoTbg(JEditorPbnf fditor, HTMLDodumfnt dod,
                              int offsft, HTML.Tbg tbg, HTML.Tbg bddTbg) {
            Elfmfnt f = findElfmfntMbtdiingTbg(dod, offsft, tbg);
            if (f != null && f.gftStbrtOffsft() == offsft) {
                insfrtAtBoundbry(fditor, dod, offsft, f, itml,
                                 tbg, bddTbg);
                rfturn truf;
            }
            flsf if (offsft > 0) {
                int dfpti = flfmfntCountToTbg(dod, offsft - 1, tbg);
                if (dfpti != -1) {
                    insfrtHTML(fditor, dod, offsft, itml, dfpti, 0, bddTbg);
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /**
         * Cbllfd bftfr bn insfrtion to bdjust tif sflfdtion.
         */
        /* protfdtfd */
        void bdjustSflfdtion(JEditorPbnf pbnf, HTMLDodumfnt dod,
                             int stbrtOffsft, int oldLfngti) {
            int nfwLfngti = dod.gftLfngti();
            if (nfwLfngti != oldLfngti && stbrtOffsft < nfwLfngti) {
                if (stbrtOffsft > 0) {
                    String tfxt;
                    try {
                        tfxt = dod.gftTfxt(stbrtOffsft - 1, 1);
                    } dbtdi (BbdLodbtionExdfption blf) {
                        tfxt = null;
                    }
                    if (tfxt != null && tfxt.lfngti() > 0 &&
                        tfxt.dibrAt(0) == '\n') {
                        pbnf.sflfdt(stbrtOffsft, stbrtOffsft);
                    }
                    flsf {
                        pbnf.sflfdt(stbrtOffsft + 1, stbrtOffsft + 1);
                    }
                }
                flsf {
                    pbnf.sflfdt(1, 1);
                }
            }
        }

        /**
         * Insfrts tif HTML into tif dodumfnt.
         *
         * @pbrbm bf tif fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt bf) {
            JEditorPbnf fditor = gftEditor(bf);
            if (fditor != null) {
                HTMLDodumfnt dod = gftHTMLDodumfnt(fditor);
                int offsft = fditor.gftSflfdtionStbrt();
                int lfngti = dod.gftLfngti();
                boolfbn insfrtfd;
                // Try first dioidf
                if (!insfrtIntoTbg(fditor, dod, offsft, pbrfntTbg, bddTbg) &&
                    bltfrnbtfPbrfntTbg != null) {
                    // Tifn bltfrnbtf.
                    insfrtfd = insfrtIntoTbg(fditor, dod, offsft,
                                             bltfrnbtfPbrfntTbg,
                                             bltfrnbtfAddTbg);
                }
                flsf {
                    insfrtfd = truf;
                }
                if (bdjustSflfdtion && insfrtfd) {
                    bdjustSflfdtion(fditor, dod, offsft, lfngti);
                }
            }
        }

        /** HTML to insfrt. */
        protfdtfd String itml;
        /** Tbg to difdk for in tif dodumfnt. */
        protfdtfd HTML.Tbg pbrfntTbg;
        /** Tbg in HTML to stbrt bdding tbgs from. */
        protfdtfd HTML.Tbg bddTbg;
        /** Altfrnbtf Tbg to difdk for in tif dodumfnt if pbrfntTbg is
         * not found. */
        protfdtfd HTML.Tbg bltfrnbtfPbrfntTbg;
        /** Altfrnbtf tbg in HTML to stbrt bdding tbgs from if pbrfntTbg
         * is not found bnd bltfrnbtfPbrfntTbg is found. */
        protfdtfd HTML.Tbg bltfrnbtfAddTbg;
        /** Truf indidbtfs tif sflfdtion siould bf bdjustfd bftfr bn insfrt. */
        boolfbn bdjustSflfdtion;
    }


    /**
     * InsfrtHRAdtion is spfdibl, bt bdtionPfrformfd timf it will dftfrminf
     * tif pbrfnt HTML.Tbg bbsfd on tif pbrbgrbpi flfmfnt bt tif sflfdtion
     * stbrt.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss InsfrtHRAdtion fxtfnds InsfrtHTMLTfxtAdtion {
        InsfrtHRAdtion() {
            supfr("InsfrtHR", "<ir>", null, HTML.Tbg.IMPLIED, null, null,
                  fblsf);
        }

        /**
         * Insfrts tif HTML into tif dodumfnt.
         *
         * @pbrbm bf tif fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt bf) {
            JEditorPbnf fditor = gftEditor(bf);
            if (fditor != null) {
                HTMLDodumfnt dod = gftHTMLDodumfnt(fditor);
                int offsft = fditor.gftSflfdtionStbrt();
                Elfmfnt pbrbgrbpi = dod.gftPbrbgrbpiElfmfnt(offsft);
                if (pbrbgrbpi.gftPbrfntElfmfnt() != null) {
                    pbrfntTbg = (HTML.Tbg)pbrbgrbpi.gftPbrfntElfmfnt().
                                  gftAttributfs().gftAttributf
                                  (StylfConstbnts.NbmfAttributf);
                    supfr.bdtionPfrformfd(bf);
                }
            }
        }

    }

    /*
     * Rfturns tif objfdt in bn AttributfSft mbtdiing b kfy
     */
    stbtid privbtf Objfdt gftAttrVbluf(AttributfSft bttr, HTML.Attributf kfy) {
        Enumfrbtion<?> nbmfs = bttr.gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt nfxtKfy = nbmfs.nfxtElfmfnt();
            Objfdt nfxtVbl = bttr.gftAttributf(nfxtKfy);
            if (nfxtVbl instbndfof AttributfSft) {
                Objfdt vbluf = gftAttrVbluf((AttributfSft)nfxtVbl, kfy);
                if (vbluf != null) {
                    rfturn vbluf;
                }
            } flsf if (nfxtKfy == kfy) {
                rfturn nfxtVbl;
            }
        }
        rfturn null;
    }

    /*
     * Adtion to movf tif fodus on tif nfxt or prfvious iypfrtfxt link
     * or objfdt. TODO: Tiis mftiod rflifs on support from tif
     * jbvbx.bddfssibility pbdkbgf.  Tif tfxt pbdkbgf siould support
     * kfybobrd nbvigbtion of tfxt flfmfnts dirfdtly.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss NbvigbtfLinkAdtion fxtfnds TfxtAdtion implfmfnts CbrftListfnfr {

        privbtf stbtid finbl FodusHigiligitPbintfr fodusPbintfr =
            nfw FodusHigiligitPbintfr(null);
        privbtf finbl boolfbn fodusBbdk;

        /*
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         */
        publid NbvigbtfLinkAdtion(String bdtionNbmf) {
            supfr(bdtionNbmf);
            fodusBbdk = "prfvious-link-bdtion".fqubls(bdtionNbmf);
        }

        /**
         * Cbllfd wifn tif dbrft position is updbtfd.
         *
         * @pbrbm f tif dbrft fvfnt
         */
        publid void dbrftUpdbtf(CbrftEvfnt f) {
            Objfdt srd = f.gftSourdf();
            if (srd instbndfof JTfxtComponfnt) {
                JTfxtComponfnt domp = (JTfxtComponfnt) srd;
                HTMLEditorKit kit = gftHTMLEditorKit(domp);
                if (kit != null && kit.foundLink) {
                    kit.foundLink = fblsf;
                    // TODO: Tif AddfssiblfContfxt for tif fditor siould rfgistfr
                    // bs b listfnfr for CbrftEvfnts bnd forwbrd tif fvfnts to
                    // bssistivf tfdinologifs listfning for sudi fvfnts.
                    domp.gftAddfssiblfContfxt().firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_HYPERTEXT_OFFSET,
                        Intfgfr.vblufOf(kit.prfvHypfrtfxtOffsft),
                        Intfgfr.vblufOf(f.gftDot()));
                }
            }
        }

        /*
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt domp = gftTfxtComponfnt(f);
            if (domp == null || domp.isEditbblf()) {
                rfturn;
            }

            Dodumfnt dod = domp.gftDodumfnt();
            HTMLEditorKit kit = gftHTMLEditorKit(domp);
            if (dod == null || kit == null) {
                rfturn;
            }

            // TODO: Siould stbrt suddfssivf itfrbtions from tif
            // durrfnt dbrft position.
            ElfmfntItfrbtor fi = nfw ElfmfntItfrbtor(dod);
            int durrfntOffsft = domp.gftCbrftPosition();
            int prfvStbrtOffsft = -1;
            int prfvEndOffsft = -1;

            // iigiligit tif nfxt link or objfdt bftfr tif durrfnt dbrft position
            Elfmfnt nfxtElfmfnt;
            wiilf ((nfxtElfmfnt = fi.nfxt()) != null) {
                String nbmf = nfxtElfmfnt.gftNbmf();
                AttributfSft bttr = nfxtElfmfnt.gftAttributfs();

                Objfdt irff = gftAttrVbluf(bttr, HTML.Attributf.HREF);
                if (!(nbmf.fqubls(HTML.Tbg.OBJECT.toString())) && irff == null) {
                    dontinuf;
                }

                int flfmfntOffsft = nfxtElfmfnt.gftStbrtOffsft();
                if (fodusBbdk) {
                    if (flfmfntOffsft >= durrfntOffsft &&
                        prfvStbrtOffsft >= 0) {

                        kit.foundLink = truf;
                        domp.sftCbrftPosition(prfvStbrtOffsft);
                        movfCbrftPosition(domp, kit, prfvStbrtOffsft,
                                          prfvEndOffsft);
                        kit.prfvHypfrtfxtOffsft = prfvStbrtOffsft;
                        rfturn;
                    }
                } flsf { // fodus forwbrd
                    if (flfmfntOffsft > durrfntOffsft) {

                        kit.foundLink = truf;
                        domp.sftCbrftPosition(flfmfntOffsft);
                        movfCbrftPosition(domp, kit, flfmfntOffsft,
                                          nfxtElfmfnt.gftEndOffsft());
                        kit.prfvHypfrtfxtOffsft = flfmfntOffsft;
                        rfturn;
                    }
                }
                prfvStbrtOffsft = nfxtElfmfnt.gftStbrtOffsft();
                prfvEndOffsft = nfxtElfmfnt.gftEndOffsft();
            }
            if (fodusBbdk && prfvStbrtOffsft >= 0) {
                kit.foundLink = truf;
                domp.sftCbrftPosition(prfvStbrtOffsft);
                movfCbrftPosition(domp, kit, prfvStbrtOffsft, prfvEndOffsft);
                kit.prfvHypfrtfxtOffsft = prfvStbrtOffsft;
            }
        }

        /*
         * Movfs tif dbrft from mbrk to dot
         */
        privbtf void movfCbrftPosition(JTfxtComponfnt domp, HTMLEditorKit kit,
                                       int mbrk, int dot) {
            Higiligitfr i = domp.gftHigiligitfr();
            if (i != null) {
                int p0 = Mbti.min(dot, mbrk);
                int p1 = Mbti.mbx(dot, mbrk);
                try {
                    if (kit.linkNbvigbtionTbg != null) {
                        i.dibngfHigiligit(kit.linkNbvigbtionTbg, p0, p1);
                    } flsf {
                        kit.linkNbvigbtionTbg =
                                i.bddHigiligit(p0, p1, fodusPbintfr);
                    }
                } dbtdi (BbdLodbtionExdfption f) {
                }
            }
        }

        privbtf HTMLEditorKit gftHTMLEditorKit(JTfxtComponfnt domp) {
            if (domp instbndfof JEditorPbnf) {
                EditorKit kit = ((JEditorPbnf) domp).gftEditorKit();
                if (kit instbndfof HTMLEditorKit) {
                    rfturn (HTMLEditorKit) kit;
                }
            }
            rfturn null;
        }

        /**
         * A iigiligit pbintfr tibt drbws b onf-pixfl bordfr bround
         * tif iigiligitfd brfb.
         */
        stbtid dlbss FodusHigiligitPbintfr fxtfnds
            DffbultHigiligitfr.DffbultHigiligitPbintfr {

            FodusHigiligitPbintfr(Color dolor) {
                supfr(dolor);
            }

            /**
             * Pbints b portion of b iigiligit.
             *
             * @pbrbm g tif grbpiids dontfxt
             * @pbrbm offs0 tif stbrting modfl offsft &gf; 0
             * @pbrbm offs1 tif fnding modfl offsft &gf; offs1
             * @pbrbm bounds tif bounding box of tif vifw, wiidi is not
             *               nfdfssbrily tif rfgion to pbint.
             * @pbrbm d tif fditor
             * @pbrbm vifw Vifw pbinting for
             * @rfturn rfgion in wiidi drbwing oddurrfd
             */
            publid Sibpf pbintLbyfr(Grbpiids g, int offs0, int offs1,
                                    Sibpf bounds, JTfxtComponfnt d, Vifw vifw) {

                Color dolor = gftColor();

                if (dolor == null) {
                    g.sftColor(d.gftSflfdtionColor());
                }
                flsf {
                    g.sftColor(dolor);
                }
                if (offs0 == vifw.gftStbrtOffsft() &&
                    offs1 == vifw.gftEndOffsft()) {
                    // Contbinfd in vifw, dbn just usf bounds.
                    Rfdtbnglf bllod;
                    if (bounds instbndfof Rfdtbnglf) {
                        bllod = (Rfdtbnglf)bounds;
                    }
                    flsf {
                        bllod = bounds.gftBounds();
                    }
                    g.drbwRfdt(bllod.x, bllod.y, bllod.widti - 1, bllod.ifigit);
                    rfturn bllod;
                }
                flsf {
                    // Siould only rfndfr pbrt of Vifw.
                    try {
                        // --- dftfrminf lodbtions ---
                        Sibpf sibpf = vifw.modflToVifw(offs0, Position.Bibs.Forwbrd,
                                                       offs1,Position.Bibs.Bbdkwbrd,
                                                       bounds);
                        Rfdtbnglf r = (sibpf instbndfof Rfdtbnglf) ?
                            (Rfdtbnglf)sibpf : sibpf.gftBounds();
                        g.drbwRfdt(r.x, r.y, r.widti - 1, r.ifigit);
                        rfturn r;
                    } dbtdi (BbdLodbtionExdfption f) {
                        // dbn't rfndfr
                    }
                }
                // Only if fxdfption
                rfturn null;
            }
        }
    }

    /*
     * Adtion to bdtivbtf tif iypfrtfxt link tibt ibs fodus.
     * TODO: Tiis mftiod rflifs on support from tif
     * jbvbx.bddfssibility pbdkbgf.  Tif tfxt pbdkbgf siould support
     * kfybobrd nbvigbtion of tfxt flfmfnts dirfdtly.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss AdtivbtfLinkAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         */
        publid AdtivbtfLinkAdtion(String bdtionNbmf) {
            supfr(bdtionNbmf);
        }

        /*
         * bdtivbtfs tif iypfrlink bt offsft
         */
        privbtf void bdtivbtfLink(String irff, HTMLDodumfnt dod,
                                  JEditorPbnf fditor, int offsft) {
            try {
                URL pbgf =
                    (URL)dod.gftPropfrty(Dodumfnt.StrfbmDfsdriptionPropfrty);
                URL url = nfw URL(pbgf, irff);
                HypfrlinkEvfnt linkEvfnt = nfw HypfrlinkEvfnt
                    (fditor, HypfrlinkEvfnt.EvfntTypf.
                     ACTIVATED, url, url.toExtfrnblForm(),
                     dod.gftCibrbdtfrElfmfnt(offsft));
                fditor.firfHypfrlinkUpdbtf(linkEvfnt);
            } dbtdi (MblformfdURLExdfption m) {
            }
        }

        /*
         * Invokfs dffbult bdtion on tif objfdt in bn flfmfnt
         */
        privbtf void doObjfdtAdtion(JEditorPbnf fditor, Elfmfnt flfm) {
            Vifw vifw = gftVifw(fditor, flfm);
            if (vifw != null && vifw instbndfof ObjfdtVifw) {
                Componfnt domp = ((ObjfdtVifw)vifw).gftComponfnt();
                if (domp != null && domp instbndfof Addfssiblf) {
                    AddfssiblfContfxt bd = domp.gftAddfssiblfContfxt();
                    if (bd != null) {
                        AddfssiblfAdtion bb = bd.gftAddfssiblfAdtion();
                        if (bb != null) {
                            bb.doAddfssiblfAdtion(0);
                        }
                    }
                }
            }
        }

        /*
         * Rfturns tif root vifw for b dodumfnt
         */
        privbtf Vifw gftRootVifw(JEditorPbnf fditor) {
            rfturn fditor.gftUI().gftRootVifw(fditor);
        }

        /*
         * Rfturns b vifw bssodibtfd witi bn flfmfnt
         */
        privbtf Vifw gftVifw(JEditorPbnf fditor, Elfmfnt flfm) {
            Objfdt lodk = lodk(fditor);
            try {
                Vifw rootVifw = gftRootVifw(fditor);
                int stbrt = flfm.gftStbrtOffsft();
                if (rootVifw != null) {
                    rfturn gftVifw(rootVifw, flfm, stbrt);
                }
                rfturn null;
            } finblly {
                unlodk(lodk);
            }
        }

        privbtf Vifw gftVifw(Vifw pbrfnt, Elfmfnt flfm, int stbrt) {
            if (pbrfnt.gftElfmfnt() == flfm) {
                rfturn pbrfnt;
            }
            int indfx = pbrfnt.gftVifwIndfx(stbrt, Position.Bibs.Forwbrd);

            if (indfx != -1 && indfx < pbrfnt.gftVifwCount()) {
                rfturn gftVifw(pbrfnt.gftVifw(indfx), flfm, stbrt);
            }
            rfturn null;
        }

        /*
         * If possiblf bdquirfs b lodk on tif Dodumfnt.  If b lodk ibs bffn
         * obtbinfd b kfy will bf rfturfd tibt siould bf pbssfd to
         * <dodf>unlodk</dodf>.
         */
        privbtf Objfdt lodk(JEditorPbnf fditor) {
            Dodumfnt dodumfnt = fditor.gftDodumfnt();

            if (dodumfnt instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dodumfnt).rfbdLodk();
                rfturn dodumfnt;
            }
            rfturn null;
        }

        /*
         * Rflfbsfs b lodk prfviously obtbinfd vib <dodf>lodk</dodf>.
         */
        privbtf void unlodk(Objfdt kfy) {
            if (kfy != null) {
                ((AbstrbdtDodumfnt)kfy).rfbdUnlodk();
            }
        }

        /*
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {

            JTfxtComponfnt d = gftTfxtComponfnt(f);
            if (d.isEditbblf() || !(d instbndfof JEditorPbnf)) {
                rfturn;
            }
            JEditorPbnf fditor = (JEditorPbnf)d;

            Dodumfnt d = fditor.gftDodumfnt();
            if (d == null || !(d instbndfof HTMLDodumfnt)) {
                rfturn;
            }
            HTMLDodumfnt dod = (HTMLDodumfnt)d;

            ElfmfntItfrbtor fi = nfw ElfmfntItfrbtor(dod);
            int durrfntOffsft = fditor.gftCbrftPosition();

            // invokf tif nfxt link or objfdt bdtion
            String urlString = null;
            String objString = null;
            Elfmfnt durrfntElfmfnt;
            wiilf ((durrfntElfmfnt = fi.nfxt()) != null) {
                String nbmf = durrfntElfmfnt.gftNbmf();
                AttributfSft bttr = durrfntElfmfnt.gftAttributfs();

                Objfdt irff = gftAttrVbluf(bttr, HTML.Attributf.HREF);
                if (irff != null) {
                    if (durrfntOffsft >= durrfntElfmfnt.gftStbrtOffsft() &&
                        durrfntOffsft <= durrfntElfmfnt.gftEndOffsft()) {

                        bdtivbtfLink((String)irff, dod, fditor, durrfntOffsft);
                        rfturn;
                    }
                } flsf if (nbmf.fqubls(HTML.Tbg.OBJECT.toString())) {
                    Objfdt obj = gftAttrVbluf(bttr, HTML.Attributf.CLASSID);
                    if (obj != null) {
                        if (durrfntOffsft >= durrfntElfmfnt.gftStbrtOffsft() &&
                            durrfntOffsft <= durrfntElfmfnt.gftEndOffsft()) {

                            doObjfdtAdtion(fditor, durrfntElfmfnt);
                            rfturn;
                        }
                    }
                }
            }
        }
    }

    privbtf stbtid int gftBodyElfmfntStbrt(JTfxtComponfnt domp) {
        Elfmfnt rootElfmfnt = domp.gftDodumfnt().gftRootElfmfnts()[0];
        for (int i = 0; i < rootElfmfnt.gftElfmfntCount(); i++) {
            Elfmfnt durrElfmfnt = rootElfmfnt.gftElfmfnt(i);
            if("body".fqubls(durrElfmfnt.gftNbmf())) {
                rfturn durrElfmfnt.gftStbrtOffsft();
            }
        }
        rfturn 0;
    }

    /*
     * Movf tif dbrft to tif bfginning of tif dodumfnt.
     * @sff DffbultEditorKit#bfginAdtion
     * @sff HTMLEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BfginAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        BfginAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            int bodyStbrt = gftBodyElfmfntStbrt(tbrgft);

            if (tbrgft != null) {
                if (sflfdt) {
                    tbrgft.movfCbrftPosition(bodyStbrt);
                } flsf {
                    tbrgft.sftCbrftPosition(bodyStbrt);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }
}
