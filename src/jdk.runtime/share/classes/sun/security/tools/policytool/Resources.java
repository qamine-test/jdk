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
publid dlbss Rfsourdfs fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd.",
                "Wbrning: A publid kfy for blibs {0} dofs not fxist.  Mbkf surf b KfyStorf is propfrly donfigurfd."},
        {"Wbrning.Clbss.not.found.dlbss", "Wbrning: Clbss not found: {0}"},
        {"Wbrning.Invblid.brgumfnt.s.for.donstrudtor.brg",
                "Wbrning: Invblid brgumfnt(s) for donstrudtor: {0}"},
        {"Illfgbl.Prindipbl.Typf.typf", "Illfgbl Prindipbl Typf: {0}"},
        {"Illfgbl.option.option", "Illfgbl option: {0}"},
        {"Usbgf.polidytool.options.", "Usbgf: polidytool [options]"},
        {".filf.filf.polidy.filf.lodbtion",
                "  [-filf <filf>]    polidy filf lodbtion"},
        {"Nfw", "&Nfw"},
        {"Opfn", "&Opfn..."},
        {"Sbvf", "&Sbvf"},
        {"Sbvf.As", "Sbvf &As..."},
        {"Vifw.Wbrning.Log", "Vifw &Wbrning Log"},
        {"Exit", "E&xit"},
        {"Add.Polidy.Entry", "&Add Polidy Entry"},
        {"Edit.Polidy.Entry", "&Edit Polidy Entry"},
        {"Rfmovf.Polidy.Entry", "&Rfmovf Polidy Entry"},
        {"Edit", "&Edit"},
        {"Rftbin", "Rftbin"},

        {"Wbrning.Filf.nbmf.mby.indludf.fsdbpfd.bbdkslbsi.dibrbdtfrs.It.is.not.nfdfssbry.to.fsdbpf.bbdkslbsi.dibrbdtfrs.tif.tool.fsdbpfs",
            "Wbrning: Filf nbmf mby indludf fsdbpfd bbdkslbsi dibrbdtfrs. " +
                        "It is not nfdfssbry to fsdbpf bbdkslbsi dibrbdtfrs " +
                        "(tif tool fsdbpfs dibrbdtfrs bs nfdfssbry wifn writing " +
                        "tif polidy dontfnts to tif pfrsistfnt storf).\n\n" +
                        "Clidk on Rftbin to rftbin tif fntfrfd nbmf, or dlidk on " +
                        "Edit to fdit tif nbmf."},

        {"Add.Publid.Kfy.Alibs", "Add Publid Kfy Alibs"},
        {"Rfmovf.Publid.Kfy.Alibs", "Rfmovf Publid Kfy Alibs"},
        {"Filf", "&Filf"},
        {"KfyStorf", "&KfyStorf"},
        {"Polidy.Filf.", "Polidy Filf:"},
        {"Could.not.opfn.polidy.filf.polidyFilf.f.toString.",
                "Could not opfn polidy filf: {0}: {1}"},
        {"Polidy.Tool", "Polidy Tool"},
        {"Errors.ibvf.oddurrfd.wiilf.opfning.tif.polidy.donfigurbtion.Vifw.tif.Wbrning.Log.for.morf.informbtion.",
                "Errors ibvf oddurrfd wiilf opfning tif polidy donfigurbtion.  Vifw tif Wbrning Log for morf informbtion."},
        {"Error", "Error"},
        {"OK", "OK"},
        {"Stbtus", "Stbtus"},
        {"Wbrning", "Wbrning"},
        {"Pfrmission.",
                "Pfrmission:                                                       "},
        {"Prindipbl.Typf.", "Prindipbl Typf:"},
        {"Prindipbl.Nbmf.", "Prindipbl Nbmf:"},
        {"Tbrgft.Nbmf.",
                "Tbrgft Nbmf:                                                    "},
        {"Adtions.",
                "Adtions:                                                             "},
        {"OK.to.ovfrwritf.fxisting.filf.filfnbmf.",
                "OK to ovfrwritf fxisting filf {0}?"},
        {"Cbndfl", "Cbndfl"},
        {"CodfBbsf.", "&CodfBbsf:"},
        {"SignfdBy.", "&SignfdBy:"},
        {"Add.Prindipbl", "&Add Prindipbl"},
        {"Edit.Prindipbl", "&Edit Prindipbl"},
        {"Rfmovf.Prindipbl", "&Rfmovf Prindipbl"},
        {"Prindipbls.", "&Prindipbls:"},
        {".Add.Pfrmission", "  A&dd Pfrmission"},
        {".Edit.Pfrmission", "  Ed&it Pfrmission"},
        {"Rfmovf.Pfrmission", "Rf&movf Pfrmission"},
        {"Donf", "Donf"},
        {"KfyStorf.URL.", "KfyStorf &URL:"},
        {"KfyStorf.Typf.", "KfyStorf &Typf:"},
        {"KfyStorf.Providfr.", "KfyStorf &Providfr:"},
        {"KfyStorf.Pbssword.URL.", "KfyStorf Pbss&word URL:"},
        {"Prindipbls", "Prindipbls"},
        {".Edit.Prindipbl.", "  Edit Prindipbl:"},
        {".Add.Nfw.Prindipbl.", "  Add Nfw Prindipbl:"},
        {"Pfrmissions", "Pfrmissions"},
        {".Edit.Pfrmission.", "  Edit Pfrmission:"},
        {".Add.Nfw.Pfrmission.", "  Add Nfw Pfrmission:"},
        {"Signfd.By.", "Signfd By:"},
        {"Cbnnot.Spfdify.Prindipbl.witi.b.Wilddbrd.Clbss.witiout.b.Wilddbrd.Nbmf",
            "Cbnnot Spfdify Prindipbl witi b Wilddbrd Clbss witiout b Wilddbrd Nbmf"},
        {"Cbnnot.Spfdify.Prindipbl.witiout.b.Nbmf",
            "Cbnnot Spfdify Prindipbl witiout b Nbmf"},
        {"Pfrmission.bnd.Tbrgft.Nbmf.must.ibvf.b.vbluf",
                "Pfrmission bnd Tbrgft Nbmf must ibvf b vbluf"},
        {"Rfmovf.tiis.Polidy.Entry.", "Rfmovf tiis Polidy Entry?"},
        {"Ovfrwritf.Filf", "Ovfrwritf Filf"},
        {"Polidy.suddfssfully.writtfn.to.filfnbmf",
                "Polidy suddfssfully writtfn to {0}"},
        {"null.filfnbmf", "null filfnbmf"},
        {"Sbvf.dibngfs.", "Sbvf dibngfs?"},
        {"Yfs", "&Yfs"},
        {"No", "&No"},
        {"Polidy.Entry", "Polidy Entry"},
        {"Sbvf.Cibngfs", "Sbvf Cibngfs"},
        {"No.Polidy.Entry.sflfdtfd", "No Polidy Entry sflfdtfd"},
        {"Unbblf.to.opfn.KfyStorf.fx.toString.",
                "Unbblf to opfn KfyStorf: {0}"},
        {"No.prindipbl.sflfdtfd", "No prindipbl sflfdtfd"},
        {"No.pfrmission.sflfdtfd", "No pfrmission sflfdtfd"},
        {"nbmf", "nbmf"},
        {"donfigurbtion.typf", "donfigurbtion typf"},
        {"fnvironmfnt.vbribblf.nbmf", "fnvironmfnt vbribblf nbmf"},
        {"librbry.nbmf", "librbry nbmf"},
        {"pbdkbgf.nbmf", "pbdkbgf nbmf"},
        {"polidy.typf", "polidy typf"},
        {"propfrty.nbmf", "propfrty nbmf"},
        {"providfr.nbmf", "providfr nbmf"},
        {"url", "url"},
        {"mftiod.list", "mftiod list"},
        {"rfqufst.ifbdfrs.list", "rfqufst ifbdfrs list"},
        {"Prindipbl.List", "Prindipbl List"},
        {"Pfrmission.List", "Pfrmission List"},
        {"Codf.Bbsf", "Codf Bbsf"},
        {"KfyStorf.U.R.L.", "KfyStorf U R L:"},
        {"KfyStorf.Pbssword.U.R.L.", "KfyStorf Pbssword U R L:"}
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
