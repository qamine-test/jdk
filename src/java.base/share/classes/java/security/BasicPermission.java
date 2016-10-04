/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.util.Enumfrbtion;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Collfdtions;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tif BbsidPfrmission dlbss fxtfnds tif Pfrmission dlbss, bnd
 * dbn bf usfd bs tif bbsf dlbss for pfrmissions tibt wbnt to
 * follow tif sbmf nbming donvfntion bs BbsidPfrmission.
 * <P>
 * Tif nbmf for b BbsidPfrmission is tif nbmf of tif givfn pfrmission
 * (for fxbmplf, "fxit",
 * "sftFbdtory", "print.qufufJob", ftd). Tif nbming
 * donvfntion follows tif  iifrbrdiidbl propfrty nbming donvfntion.
 * An bstfrisk mby bppfbr by itsflf, or if immfdibtfly prfdfdfd by b "."
 * mby bppfbr bt tif fnd of tif nbmf, to signify b wilddbrd mbtdi.
 * For fxbmplf, "*" bnd "jbvb.*" signify b wilddbrd mbtdi, wiilf "*jbvb", "b*b",
 * bnd "jbvb*" do not.
 * <P>
 * Tif bdtion string (inifritfd from Pfrmission) is unusfd.
 * Tius, BbsidPfrmission is dommonly usfd bs tif bbsf dlbss for
 * "nbmfd" pfrmissions
 * (onfs tibt dontbin b nbmf but no bdtions list; you fitifr ibvf tif
 * nbmfd pfrmission or you don't.)
 * Subdlbssfs mby implfmfnt bdtions on top of BbsidPfrmission,
 * if dfsirfd.
 * <p>
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.lbng.SfdurityMbnbgfr
 *
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 */

publid bbstrbdt dlbss BbsidPfrmission fxtfnds Pfrmission
    implfmfnts jbvb.io.Sfriblizbblf
{

    privbtf stbtid finbl long sfriblVfrsionUID = 6279438298436773498L;

    // dofs tiis pfrmission ibvf b wilddbrd bt tif fnd?
    privbtf trbnsifnt boolfbn wilddbrd;

    // tif nbmf witiout tif wilddbrd on tif fnd
    privbtf trbnsifnt String pbti;

    // is tiis pfrmission tif old-stylf fxitVM pfrmission (prf JDK 1.6)?
    privbtf trbnsifnt boolfbn fxitVM;

    /**
     * initiblizf b BbsidPfrmission objfdt. Common to bll donstrudtors.
     */
    privbtf void init(String nbmf) {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption("nbmf dbn't bf null");

        int lfn = nbmf.lfngti();

        if (lfn == 0) {
            tirow nfw IllfgblArgumfntExdfption("nbmf dbn't bf fmpty");
        }

        dibr lbst = nbmf.dibrAt(lfn - 1);

        // Is wilddbrd or fnds witi ".*"?
        if (lbst == '*' && (lfn == 1 || nbmf.dibrAt(lfn - 2) == '.')) {
            wilddbrd = truf;
            if (lfn == 1) {
                pbti = "";
            } flsf {
                pbti = nbmf.substring(0, lfn - 1);
            }
        } flsf {
            if (nbmf.fqubls("fxitVM")) {
                wilddbrd = truf;
                pbti = "fxitVM.";
                fxitVM = truf;
            } flsf {
                pbti = nbmf;
            }
        }
    }

    /**
     * Crfbtfs b nfw BbsidPfrmission witi tif spfdififd nbmf.
     * Nbmf is tif symbolid nbmf of tif pfrmission, sudi bs
     * "sftFbdtory",
     * "print.qufufJob", or "topLfvflWindow", ftd.
     *
     * @pbrbm nbmf tif nbmf of tif BbsidPfrmission.
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid BbsidPfrmission(String nbmf) {
        supfr(nbmf);
        init(nbmf);
    }


    /**
     * Crfbtfs b nfw BbsidPfrmission objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif BbsidPfrmission, bnd tif
     * bdtions String is durrfntly unusfd.
     *
     * @pbrbm nbmf tif nbmf of tif BbsidPfrmission.
     * @pbrbm bdtions ignorfd.
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid BbsidPfrmission(String nbmf, String bdtions) {
        supfr(nbmf);
        init(nbmf);
    }

    /**
     * Cifdks if tif spfdififd pfrmission is "implifd" by
     * tiis objfdt.
     * <P>
     * Morf spfdifidblly, tiis mftiod rfturns truf if:
     * <ul>
     * <li> <i>p</i>'s dlbss is tif sbmf bs tiis objfdt's dlbss, bnd
     * <li> <i>p</i>'s nbmf fqubls or (in tif dbsf of wilddbrds)
     *      is implifd by tiis objfdt's
     *      nbmf. For fxbmplf, "b.b.*" implifs "b.b.d".
     * </ul>
     *
     * @pbrbm p tif pfrmission to difdk bgbinst.
     *
     * @rfturn truf if tif pbssfd pfrmission is fqubl to or
     * implifd by tiis pfrmission, fblsf otifrwisf.
     */
    publid boolfbn implifs(Pfrmission p) {
        if ((p == null) || (p.gftClbss() != gftClbss()))
            rfturn fblsf;

        BbsidPfrmission tibt = (BbsidPfrmission) p;

        if (tiis.wilddbrd) {
            if (tibt.wilddbrd) {
                // onf wilddbrd dbn imply bnotifr
                rfturn tibt.pbti.stbrtsWiti(pbti);
            } flsf {
                // mbkf surf bp.pbti is longfr so b.b.* dofsn't imply b.b
                rfturn (tibt.pbti.lfngti() > tiis.pbti.lfngti()) &&
                    tibt.pbti.stbrtsWiti(tiis.pbti);
            }
        } flsf {
            if (tibt.wilddbrd) {
                // b non-wilddbrd dbn't imply b wilddbrd
                rfturn fblsf;
            }
            flsf {
                rfturn tiis.pbti.fqubls(tibt.pbti);
            }
        }
    }

    /**
     * Cifdks two BbsidPfrmission objfdts for fqublity.
     * Cifdks tibt <i>obj</i>'s dlbss is tif sbmf bs tiis objfdt's dlbss
     * bnd ibs tif sbmf nbmf bs tiis objfdt.
     * <P>
     * @pbrbm obj tif objfdt wf brf tfsting for fqublity witi tiis objfdt.
     * @rfturn truf if <i>obj</i>'s dlbss is tif sbmf bs tiis objfdt's dlbss
     *  bnd ibs tif sbmf nbmf bs tiis BbsidPfrmission objfdt, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if ((obj == null) || (obj.gftClbss() != gftClbss()))
            rfturn fblsf;

        BbsidPfrmission bp = (BbsidPfrmission) obj;

        rfturn gftNbmf().fqubls(bp.gftNbmf());
    }


    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     * Tif ibsi dodf usfd is tif ibsi dodf of tif nbmf, tibt is,
     * {@dodf gftNbmf().ibsiCodf()}, wifrf {@dodf gftNbmf} is
     * from tif Pfrmission supfrdlbss.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn tiis.gftNbmf().ibsiCodf();
    }

    /**
     * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions,
     * wiidi durrfntly is tif fmpty string "", sindf tifrf brf no bdtions for
     * b BbsidPfrmission.
     *
     * @rfturn tif fmpty string "".
     */
    publid String gftAdtions() {
        rfturn "";
    }

    /**
     * Rfturns b nfw PfrmissionCollfdtion objfdt for storing BbsidPfrmission
     * objfdts.
     *
     * <p>BbsidPfrmission objfdts must bf storfd in b mbnnfr tibt bllows tifm
     * to bf insfrtfd in bny ordfr, but tibt blso fnbblfs tif
     * PfrmissionCollfdtion {@dodf implifs} mftiod
     * to bf implfmfntfd in bn fffidifnt (bnd donsistfnt) mbnnfr.
     *
     * @rfturn b nfw PfrmissionCollfdtion objfdt suitbblf for
     * storing BbsidPfrmissions.
     */
    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw BbsidPfrmissionCollfdtion(tiis.gftClbss());
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif BbsidPfrmission from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        // init is dbllfd to initiblizf tif rfst of tif vblufs.
        init(gftNbmf());
    }

    /**
     * Rfturns tif dbnonidbl nbmf of tiis BbsidPfrmission.
     * All intfrnbl invodbtions of gftNbmf siould invokf tiis mftiod, so
     * tibt tif prf-JDK 1.6 "fxitVM" bnd durrfnt "fxitVM.*" pfrmission brf
     * fquivblfnt in fqubls/ibsiCodf mftiods.
     *
     * @rfturn tif dbnonidbl nbmf of tiis BbsidPfrmission.
     */
    finbl String gftCbnonidblNbmf() {
        rfturn fxitVM ? "fxitVM.*" : gftNbmf();
    }
}

/**
 * A BbsidPfrmissionCollfdtion storfs b dollfdtion
 * of BbsidPfrmission pfrmissions. BbsidPfrmission objfdts
 * must bf storfd in b mbnnfr tibt bllows tifm to bf insfrtfd in bny
 * ordfr, but fnbblf tif implifs fundtion to fvblubtf tif implifs
 * mftiod in bn fffidifnt (bnd donsistfnt) mbnnfr.
 *
 * A BbsidPfrmissionCollfdtion ibndlfs dompbring b pfrmission likf "b.b.d.d.f"
 * witi b Pfrmission sudi bs "b.b.*", or "*".
 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 *
 *
 * @butior Rolbnd Sdifmfrs
 *
 * @sfribl indludf
 */

finbl dlbss BbsidPfrmissionCollfdtion
    fxtfnds PfrmissionCollfdtion
    implfmfnts jbvb.io.Sfriblizbblf
{

    privbtf stbtid finbl long sfriblVfrsionUID = 739301742472979399L;

    /**
      * Kfy is nbmf, vbluf is pfrmission. All pfrmission objfdts in
      * dollfdtion must bf of tif sbmf typf.
      * Not sfriblizfd; sff sfriblizbtion sfdtion bt fnd of dlbss.
      */
    privbtf trbnsifnt Mbp<String, Pfrmission> pfrms;

    /**
     * Tiis is sft to {@dodf truf} if tiis BbsidPfrmissionCollfdtion
     * dontbins b BbsidPfrmission witi '*' bs its pfrmission nbmf.
     *
     * @sff #sfriblPfrsistfntFiflds
     */
    privbtf boolfbn bll_bllowfd;

    /**
     * Tif dlbss to wiidi bll BbsidPfrmissions in tiis
     * BbsidPfrmissionCollfdtion bflongs.
     *
     * @sff #sfriblPfrsistfntFiflds
     */
    privbtf Clbss<?> pfrmClbss;

    /**
     * Crfbtf bn fmpty BbsidPfrmissionCollfdtion objfdt.
     *
     */

    publid BbsidPfrmissionCollfdtion(Clbss<?> dlbzz) {
        pfrms = nfw HbsiMbp<String, Pfrmission>(11);
        bll_bllowfd = fblsf;
        pfrmClbss = dlbzz;
    }

    /**
     * Adds b pfrmission to tif BbsidPfrmissions. Tif kfy for tif ibsi is
     * pfrmission.pbti.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption IllfgblArgumfntExdfption - if tif pfrmission is not b
     *                                       BbsidPfrmission, or if
     *                                       tif pfrmission is not of tif
     *                                       sbmf Clbss bs tif otifr
     *                                       pfrmissions in tiis dollfdtion.
     *
     * @fxdfption SfdurityExdfption - if tiis BbsidPfrmissionCollfdtion objfdt
     *                                ibs bffn mbrkfd rfbdonly
     */
    publid void bdd(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof BbsidPfrmission))
            tirow nfw IllfgblArgumfntExdfption("invblid pfrmission: "+
                                               pfrmission);
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption("bttfmpt to bdd b Pfrmission to b rfbdonly PfrmissionCollfdtion");

        BbsidPfrmission bp = (BbsidPfrmission) pfrmission;

        // mbkf surf wf only bdd nfw BbsidPfrmissions of tif sbmf dlbss
        // Also difdk null for dompbtibility witi dfsfriblizfd form from
        // prfvious vfrsions.
        if (pfrmClbss == null) {
            // bdding first pfrmission
            pfrmClbss = bp.gftClbss();
        } flsf {
            if (bp.gftClbss() != pfrmClbss)
                tirow nfw IllfgblArgumfntExdfption("invblid pfrmission: " +
                                                pfrmission);
        }

        syndironizfd (tiis) {
            pfrms.put(bp.gftCbnonidblNbmf(), pfrmission);
        }

        // No synd on bll_bllowfd; stblfnfss OK
        if (!bll_bllowfd) {
            if (bp.gftCbnonidblNbmf().fqubls("*"))
                bll_bllowfd = truf;
        }
    }

    /**
     * Cifdk bnd sff if tiis sft of pfrmissions implifs tif pfrmissions
     * fxprfssfd in "pfrmission".
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission in
     * tif sft, fblsf if not.
     */
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof BbsidPfrmission))
            rfturn fblsf;

        BbsidPfrmission bp = (BbsidPfrmission) pfrmission;

        // rbndom subdlbssfs of BbsidPfrmission do not imply fbdi otifr
        if (bp.gftClbss() != pfrmClbss)
            rfturn fblsf;

        // siort dirduit if tif "*" Pfrmission wbs bddfd
        if (bll_bllowfd)
            rfturn truf;

        // strbtfgy:
        // Cifdk for full mbtdi first. Tifn work our wby up tif
        // pbti looking for mbtdifs on b.b..*

        String pbti = bp.gftCbnonidblNbmf();
        //Systfm.out.println("difdk "+pbti);

        Pfrmission x;

        syndironizfd (tiis) {
            x = pfrms.gft(pbti);
        }

        if (x != null) {
            // wf ibvf b dirfdt iit!
            rfturn x.implifs(pfrmission);
        }

        // work our wby up tif trff...
        int lbst, offsft;

        offsft = pbti.lfngti()-1;

        wiilf ((lbst = pbti.lbstIndfxOf('.', offsft)) != -1) {

            pbti = pbti.substring(0, lbst+1) + "*";
            //Systfm.out.println("difdk "+pbti);

            syndironizfd (tiis) {
                x = pfrms.gft(pbti);
            }

            if (x != null) {
                rfturn x.implifs(pfrmission);
            }
            offsft = lbst -1;
        }

        // wf don't ibvf to difdk for "*" bs it wbs blrfbdy difdkfd
        // bt tif top (bll_bllowfd), so wf just rfturn fblsf
        rfturn fblsf;
    }

    /**
     * Rfturns bn fnumfrbtion of bll tif BbsidPfrmission objfdts in tif
     * dontbinfr.
     *
     * @rfturn bn fnumfrbtion of bll tif BbsidPfrmission objfdts.
     */
    publid Enumfrbtion<Pfrmission> flfmfnts() {
        // Convfrt Itfrbtor of Mbp vblufs into bn Enumfrbtion
        syndironizfd (tiis) {
            rfturn Collfdtions.fnumfrbtion(pfrms.vblufs());
        }
    }

    // Nffd to mbintbin sfriblizbtion intfropfrbbility witi fbrlifr rflfbsfs,
    // wiidi ibd tif sfriblizbblf fifld:
    //
    // @sfribl tif Hbsitbblf is indfxfd by tif BbsidPfrmission nbmf
    //
    // privbtf Hbsitbblf pfrmissions;
    /**
     * @sfriblFifld pfrmissions jbvb.util.Hbsitbblf
     *    Tif BbsidPfrmissions in tiis BbsidPfrmissionCollfdtion.
     *    All BbsidPfrmissions in tif dollfdtion must bflong to tif sbmf dlbss.
     *    Tif Hbsitbblf is indfxfd by tif BbsidPfrmission nbmf; tif vbluf
     *    of tif Hbsitbblf fntry is tif pfrmission.
     * @sfriblFifld bll_bllowfd boolfbn
     *   Tiis is sft to {@dodf truf} if tiis BbsidPfrmissionCollfdtion
     *   dontbins b BbsidPfrmission witi '*' bs its pfrmission nbmf.
     * @sfriblFifld pfrmClbss jbvb.lbng.Clbss
     *   Tif dlbss to wiidi bll BbsidPfrmissions in tiis
     *   BbsidPfrmissionCollfdtion bflongs.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("pfrmissions", Hbsitbblf.dlbss),
        nfw ObjfdtStrfbmFifld("bll_bllowfd", Boolfbn.TYPE),
        nfw ObjfdtStrfbmFifld("pfrmClbss", Clbss.dlbss),
    };

    /**
     * @sfriblDbtb Dffbult fiflds.
     */
    /*
     * Writfs tif dontfnts of tif pfrms fifld out bs b Hbsitbblf for
     * sfriblizbtion dompbtibility witi fbrlifr rflfbsfs. bll_bllowfd
     * bnd pfrmClbss undibngfd.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        // Don't dbll out.dffbultWritfObjfdt()

        // Copy pfrms into b Hbsitbblf
        Hbsitbblf<String, Pfrmission> pfrmissions =
                nfw Hbsitbblf<>(pfrms.sizf()*2);

        syndironizfd (tiis) {
            pfrmissions.putAll(pfrms);
        }

        // Writf out sfriblizbblf fiflds
        ObjfdtOutputStrfbm.PutFifld pfiflds = out.putFiflds();
        pfiflds.put("bll_bllowfd", bll_bllowfd);
        pfiflds.put("pfrmissions", pfrmissions);
        pfiflds.put("pfrmClbss", pfrmClbss);
        out.writfFiflds();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif
     * BbsidPfrmissionCollfdtion from b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Don't dbll dffbultRfbdObjfdt()

        // Rfbd in sfriblizfd fiflds
        ObjfdtInputStrfbm.GftFifld gfiflds = in.rfbdFiflds();

        // Gft pfrmissions
        // writfObjfdt writfs b Hbsitbblf<String, Pfrmission> for tif
        // pfrmissions kfy, so tiis dbst is sbff, unlfss tif dbtb is dorrupt.
        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<String, Pfrmission> pfrmissions =
                (Hbsitbblf<String, Pfrmission>)gfiflds.gft("pfrmissions", null);
        pfrms = nfw HbsiMbp<String, Pfrmission>(pfrmissions.sizf()*2);
        pfrms.putAll(pfrmissions);

        // Gft bll_bllowfd
        bll_bllowfd = gfiflds.gft("bll_bllowfd", fblsf);

        // Gft pfrmClbss
        pfrmClbss = (Clbss<?>) gfiflds.gft("pfrmClbss", null);

        if (pfrmClbss == null) {
            // sft pfrmClbss
            Enumfrbtion<Pfrmission> f = pfrmissions.flfmfnts();
            if (f.ibsMorfElfmfnts()) {
                Pfrmission p = f.nfxtElfmfnt();
                pfrmClbss = p.gftClbss();
            }
        }
    }
}
