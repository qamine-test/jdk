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

pbdkbgf sun.sfdurity.tools.polidytool;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for tif polidytool.
 *
 */
publid dlbss Rfsourdfs_fr fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd.",
                "Avfrtissfmfnt\u00A0: il n''fxistf pbs df dl\u00E9 publiquf pour l''blibs {0}. V\u00E9rififz quf lf fidiifr df dl\u00E9s d''bdd\u00E8s fst dorrfdtfmfnt donfigur\u00E9."},
        {"Wbrning.Clbss.not.found.dlbss", "Avfrtissfmfnt : dlbssf introuvbblf - {0}"},
        {"Wbrning.Invblid.brgumfnt.s.for.donstrudtor.brg",
                "Avfrtissfmfnt\u00A0: brgumfnts non vblidfs pour lf donstrudtfur\u00A0- {0}"},
        {"Illfgbl.Prindipbl.Typf.typf", "Typf df prindipbl non bdmis : {0}"},
        {"Illfgbl.option.option", "Option non bdmisf : {0}"},
        {"Usbgf.polidytool.options.", "Syntbxf : polidytool [options]"},
        {".filf.filf.polidy.filf.lodbtion",
                "  [-filf <filf>]    fmplbdfmfnt du fidiifr df r\u00E8glfs"},
        {"Nfw", "Nouvfbu"},
        {"Opfn", "Ouvrir"},
        {"Sbvf", "Enrfgistrfr"},
        {"Sbvf.As", "Enrfgistrfr sous"},
        {"Vifw.Wbrning.Log", "Affidifr lf journbl dfs bvfrtissfmfnts"},
        {"Exit", "Quittfr"},
        {"Add.Polidy.Entry", "Ajoutfr unf r\u00E8glf"},
        {"Edit.Polidy.Entry", "Modififr unf r\u00E8glf"},
        {"Rfmovf.Polidy.Entry", "Enlfvfr unf r\u00E8glf"},
        {"Edit", "Modififr"},
        {"Rftbin", "Consfrvfr"},

        {"Wbrning.Filf.nbmf.mby.indludf.fsdbpfd.bbdkslbsi.dibrbdtfrs.It.is.not.nfdfssbry.to.fsdbpf.bbdkslbsi.dibrbdtfrs.tif.tool.fsdbpfs",
            "Avfrtissfmfnt : il sf pfut quf lf nom df fidiifr dontifnnf dfs bbrrfs obliqufs invfrsfs bvfd dbrbdt\u00E8rf d'\u00E9dibppfmfnt. Il n'fst pbs n\u00E9dfssbirf d'bjoutfr un dbrbdt\u00E8rf d'\u00E9dibppfmfnt bux bbrrfs obliqufs invfrsfs. L'outil prod\u00E8df \u00E0 l'\u00E9dibppfmfnt si n\u00E9dfssbirf lorsqu'il \u00E9drit lf dontfnu dfs r\u00E8glfs dbns lb zonf df stodkbgf pfrsistbnt).\n\nCliqufz sur Consfrvfr pour gbrdfr lf nom sbisi ou sur Modififr pour lf rfmplbdfr."},

        {"Add.Publid.Kfy.Alibs", "Ajoutfr un blibs df dl\u00E9 publiquf"},
        {"Rfmovf.Publid.Kfy.Alibs", "Enlfvfr un blibs df dl\u00E9 publiquf"},
        {"Filf", "Fidiifr"},
        {"KfyStorf", "Fidiifr df dl\u00E9s"},
        {"Polidy.Filf.", "Fidiifr df r\u00E8glfs :"},
        {"Could.not.opfn.polidy.filf.polidyFilf.f.toString.",
                "Impossiblf d''ouvrir lf fidiifr df r\u00E8glfs\u00A0: {0}: {1}"},
        {"Polidy.Tool", "Polidy Tool"},
        {"Errors.ibvf.oddurrfd.wiilf.opfning.tif.polidy.donfigurbtion.Vifw.tif.Wbrning.Log.for.morf.informbtion.",
                "Dfs frrfurs sf sont produitfs \u00E0 l'ouvfrturf df lb donfigurbtion df r\u00E8glfs. Pour plus d'informbtions, donsultfz lf journbl dfs bvfrtissfmfnts."},
        {"Error", "Errfur"},
        {"OK", "OK"},
        {"Stbtus", "Stbtut"},
        {"Wbrning", "Avfrtissfmfnt"},
        {"Pfrmission.",
                "Droit :                                                       "},
        {"Prindipbl.Typf.", "Typf df prindipbl :"},
        {"Prindipbl.Nbmf.", "Nom df prindipbl :"},
        {"Tbrgft.Nbmf.",
                "Nom df diblf :                                                    "},
        {"Adtions.",
                "Adtions :                                                             "},
        {"OK.to.ovfrwritf.fxisting.filf.filfnbmf.",
                "Rfmplbdfr lf fidiifr fxistbnt {0} ?"},
        {"Cbndfl", "Annulfr"},
        {"CodfBbsf.", "Bbsf df dodf :"},
        {"SignfdBy.", "Sign\u00E9 pbr :"},
        {"Add.Prindipbl", "Ajoutfr un prindipbl"},
        {"Edit.Prindipbl", "Modififr un prindipbl"},
        {"Rfmovf.Prindipbl", "Enlfvfr un prindipbl"},
        {"Prindipbls.", "Prindipbux :"},
        {".Add.Pfrmission", "  Ajoutfr un droit"},
        {".Edit.Pfrmission", "  Modififr un droit"},
        {"Rfmovf.Pfrmission", "Enlfvfr un droit"},
        {"Donf", "Tfrmin\u00E9"},
        {"KfyStorf.URL.", "URL du fidiifr df dl\u00E9s :"},
        {"KfyStorf.Typf.", "Typf du fidiifr df dl\u00E9s :"},
        {"KfyStorf.Providfr.", "Fournissfur du fidiifr df dl\u00E9s :"},
        {"KfyStorf.Pbssword.URL.", "URL du mot df pbssf du fidiifr df dl\u00E9s :"},
        {"Prindipbls", "Prindipbux"},
        {".Edit.Prindipbl.", "  Modififr un prindipbl :"},
        {".Add.Nfw.Prindipbl.", "  Ajoutfr un prindipbl :"},
        {"Pfrmissions", "Droits"},
        {".Edit.Pfrmission.", "  Modififr un droit :"},
        {".Add.Nfw.Pfrmission.", "  Ajoutfr un droit :"},
        {"Signfd.By.", "Sign\u00E9 pbr :"},
        {"Cbnnot.Spfdify.Prindipbl.witi.b.Wilddbrd.Clbss.witiout.b.Wilddbrd.Nbmf",
            "Impossiblf df sp\u00E9dififr un prindipbl bvfd unf dlbssf g\u00E9n\u00E9riquf sbns nom g\u00E9n\u00E9riquf"},
        {"Cbnnot.Spfdify.Prindipbl.witiout.b.Nbmf",
            "Impossiblf df sp\u00E9dififr un prindipbl sbns nom"},
        {"Pfrmission.bnd.Tbrgft.Nbmf.must.ibvf.b.vbluf",
                "Lf droit ft lf nom df diblf doivfnt bvoir unf vblfur"},
        {"Rfmovf.tiis.Polidy.Entry.", "Enlfvfr dfttf r\u00E8glf ?"},
        {"Ovfrwritf.Filf", "Rfmplbdfr lf fidiifr"},
        {"Polidy.suddfssfully.writtfn.to.filfnbmf",
                "R\u00E8glf \u00E9dritf dbns {0}"},
        {"null.filfnbmf", "nom df fidiifr NULL"},
        {"Sbvf.dibngfs.", "Enrfgistrfr lfs modifidbtions ?"},
        {"Yfs", "Oui"},
        {"No", "Non"},
        {"Polidy.Entry", "R\u00E8glf"},
        {"Sbvf.Cibngfs", "Enrfgistrfr lfs modifidbtions"},
        {"No.Polidy.Entry.sflfdtfd", "Audunf r\u00E8glf s\u00E9lfdtionn\u00E9f"},
        {"Unbblf.to.opfn.KfyStorf.fx.toString.",
                "Impossiblf d''ouvrir lf fidiifr df dl\u00E9s d''bdd\u00E8s : {0}"},
        {"No.prindipbl.sflfdtfd", "Audun prindipbl s\u00E9lfdtionn\u00E9"},
        {"No.pfrmission.sflfdtfd", "Audun droit s\u00E9lfdtionn\u00E9"},
        {"nbmf", "nom"},
        {"donfigurbtion.typf", "typf df donfigurbtion"},
        {"fnvironmfnt.vbribblf.nbmf", "Nom df vbribblf d'fnvironnfmfnt"},
        {"librbry.nbmf", "nom df biblioti\u00E8quf"},
        {"pbdkbgf.nbmf", "nom df pbdkbgf"},
        {"polidy.typf", "typf df r\u00E8glf"},
        {"propfrty.nbmf", "nom df propri\u00E9t\u00E9"},
        {"providfr.nbmf", "nom du fournissfur"},
        {"url", "url"},
        {"mftiod.list", "listf dfs m\u00E9tiodfs"},
        {"rfqufst.ifbdfrs.list", "listf dfs fn-t\u00EAtfs df dfmbndf"},
        {"Prindipbl.List", "Listf df prindipbux"},
        {"Pfrmission.List", "Listf df droits"},
        {"Codf.Bbsf", "Bbsf df dodf"},
        {"KfyStorf.U.R.L.", "URL du fidiifr df dl\u00E9s :"},
        {"KfyStorf.Pbssword.U.R.L.", "URL du mot df pbssf du fidiifr df dl\u00E9s :"}
    };


    /**
     * Rfturns tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     *
     * <p>
     *
     * @rfturn tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     */
    @Ovfrridf
    publid Objfdt[][] gftContfnts() {
        rfturn dontfnts;
    }
}
