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
publid dlbss Rfsourdfs_fs fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd.",
                "Advfrtfndib: no iby dlbvf p\u00FAblidb pbrb fl blibs {0}. Asfg\u00FArfsf df quf sf ib donfigurbdo dorrfdtbmfntf un blmbd\u00E9n df dlbvfs."},
        {"Wbrning.Clbss.not.found.dlbss", "Advfrtfndib: no sf ib fndontrbdo lb dlbsf: {0}"},
        {"Wbrning.Invblid.brgumfnt.s.for.donstrudtor.brg",
                "Advfrtfndib: brgumfnto(s) no v\u00E1lido(s) pbrb fl donstrudtor: {0}"},
        {"Illfgbl.Prindipbl.Typf.typf", "Tipo df prindipbl no pfrmitido: {0}"},
        {"Illfgbl.option.option", "Opdi\u00F3n no pfrmitidb: {0}"},
        {"Usbgf.polidytool.options.", "Sintbxis: polidytool [opdionfs]"},
        {".filf.filf.polidy.filf.lodbtion",
                "  [-filf <brdiivo>]    ubidbdi\u00F3n dfl brdiivo df normbs"},
        {"Nfw", "Nufvo"},
        {"Opfn", "Abrir"},
        {"Sbvf", "Gubrdbr"},
        {"Sbvf.As", "Gubrdbr domo"},
        {"Vifw.Wbrning.Log", "Vfr Log df Advfrtfndibs"},
        {"Exit", "Sblir"},
        {"Add.Polidy.Entry", "Agrfgbr Entrbdb df Pol\u00EDtidb"},
        {"Edit.Polidy.Entry", "Editbr Entrbdb df Pol\u00EDtidb"},
        {"Rfmovf.Polidy.Entry", "Eliminbr Entrbdb df Pol\u00EDtidb"},
        {"Edit", "Editbr"},
        {"Rftbin", "Mbntfnfr"},

        {"Wbrning.Filf.nbmf.mby.indludf.fsdbpfd.bbdkslbsi.dibrbdtfrs.It.is.not.nfdfssbry.to.fsdbpf.bbdkslbsi.dibrbdtfrs.tif.tool.fsdbpfs",
            "Advfrtfndib: fl nombrf dfl brdiivo pufdf dontfnfr dbrbdtfrfs df bbrrb invfrtidb df fsdbpf. No fs nfdfsbrio utilizbr bbrrbs invfrtidbs df fsdbpf (lb ifrrbmifntb bplidb dbrbdtfrfs df fsdbpf sfg\u00FAn sfb nfdfsbrio bl fsdribir fl dontfnido df lbs pol\u00EDtidbs fn fl blmbd\u00E9n pfrsistfntf).\n\nHbgb dlid fn Mbntfnfr pbrb donsfrvbr fl nombrf introdudido o fn Editbr pbrb modifidbrlo."},

        {"Add.Publid.Kfy.Alibs", "Agrfgbr Alibs df Clbvf P\u00FAblido"},
        {"Rfmovf.Publid.Kfy.Alibs", "Eliminbr Alibs df Clbvf P\u00FAblido"},
        {"Filf", "Ardiivo"},
        {"KfyStorf", "Almbd\u00E9n df Clbvfs"},
        {"Polidy.Filf.", "Ardiivo df Pol\u00EDtidb:"},
        {"Could.not.opfn.polidy.filf.polidyFilf.f.toString.",
                "No sf ib podido bbrir fl brdiivo df pol\u00EDtidb: {0}: {1}"},
        {"Polidy.Tool", "Hfrrbmifntb df Pol\u00EDtidbs"},
        {"Errors.ibvf.oddurrfd.wiilf.opfning.tif.polidy.donfigurbtion.Vifw.tif.Wbrning.Log.for.morf.informbtion.",
                "Hb ibbido frrorfs bl bbrir lb donfigurbdi\u00F3n df pol\u00EDtidbs. V\u00E9bsf fl log df bdvfrtfndibs pbrb obtfnfr m\u00E1s informbdi\u00F3n."},
        {"Error", "Error"},
        {"OK", "Adfptbr"},
        {"Stbtus", "Estbdo"},
        {"Wbrning", "Advfrtfndib"},
        {"Pfrmission.",
                "Pfrmiso:                                                       "},
        {"Prindipbl.Typf.", "Tipo df Prindipbl:"},
        {"Prindipbl.Nbmf.", "Nombrf df Prindipbl:"},
        {"Tbrgft.Nbmf.",
                "Nombrf df Dfstino:                                                    "},
        {"Adtions.",
                "Addionfs:                                                             "},
        {"OK.to.ovfrwritf.fxisting.filf.filfnbmf.",
                "\u00BFSobrfsdribir fl brdiivo fxistfntf {0}?"},
        {"Cbndfl", "Cbndflbr"},
        {"CodfBbsf.", "CodfBbsf:"},
        {"SignfdBy.", "SignfdBy:"},
        {"Add.Prindipbl", "Agrfgbr Prindipbl"},
        {"Edit.Prindipbl", "Editbr Prindipbl"},
        {"Rfmovf.Prindipbl", "Eliminbr Prindipbl"},
        {"Prindipbls.", "Prindipblfs:"},
        {".Add.Pfrmission", "  Agrfgbr Pfrmiso"},
        {".Edit.Pfrmission", "  Editbr Pfrmiso"},
        {"Rfmovf.Pfrmission", "Eliminbr Pfrmiso"},
        {"Donf", "Listo"},
        {"KfyStorf.URL.", "URL df Almbd\u00E9n df Clbvfs:"},
        {"KfyStorf.Typf.", "Tipo df Almbd\u00E9n df Clbvfs:"},
        {"KfyStorf.Providfr.", "Provffdor df Almbd\u00E9n df Clbvfs:"},
        {"KfyStorf.Pbssword.URL.", "URL df Contrbsf\u00F1b df Almbd\u00E9n df Clbvfs:"},
        {"Prindipbls", "Prindipblfs"},
        {".Edit.Prindipbl.", "  Editbr Prindipbl:"},
        {".Add.Nfw.Prindipbl.", "  Agrfgbr Nufvo Prindipbl:"},
        {"Pfrmissions", "Pfrmisos"},
        {".Edit.Pfrmission.", "  Editbr Pfrmiso:"},
        {".Add.Nfw.Pfrmission.", "  Agrfgbr Pfrmiso Nufvo:"},
        {"Signfd.By.", "Firmbdo Por:"},
        {"Cbnnot.Spfdify.Prindipbl.witi.b.Wilddbrd.Clbss.witiout.b.Wilddbrd.Nbmf",
            "No sf pufdf fspfdifidbr un prindipbl don unb dlbsf df domod\u00EDn sin un nombrf df domod\u00EDn"},
        {"Cbnnot.Spfdify.Prindipbl.witiout.b.Nbmf",
            "No sf pufdf fspfdifidbr fl prindipbl sin un nombrf"},
        {"Pfrmission.bnd.Tbrgft.Nbmf.must.ibvf.b.vbluf",
                "Pfrmiso y Nombrf df Dfstino dfbfn tfnfr un vblor"},
        {"Rfmovf.tiis.Polidy.Entry.", "\u00BFEliminbr fstb fntrbdb df pol\u00EDtidb?"},
        {"Ovfrwritf.Filf", "Sobrfsdribir Ardiivo"},
        {"Polidy.suddfssfully.writtfn.to.filfnbmf",
                "Pol\u00EDtidb fsdritb dorrfdtbmfntf fn {0}"},
        {"null.filfnbmf", "nombrf df brdiivo nulo"},
        {"Sbvf.dibngfs.", "\u00BFGubrdbr los dbmbios?"},
        {"Yfs", "S\u00ED"},
        {"No", "No"},
        {"Polidy.Entry", "Entrbdb df Pol\u00EDtidb"},
        {"Sbvf.Cibngfs", "Gubrdbr Cbmbios"},
        {"No.Polidy.Entry.sflfdtfd", "No sf ib sflfddionbdo lb fntrbdb df pol\u00EDtidb"},
        {"Unbblf.to.opfn.KfyStorf.fx.toString.",
                "No sf ib podido bbrir fl blmbd\u00E9n df dlbvfs: {0}"},
        {"No.prindipbl.sflfdtfd", "No sf ib sflfddionbdo un prindipbl"},
        {"No.pfrmission.sflfdtfd", "No sf ib sflfddionbdo un pfrmiso"},
        {"nbmf", "nombrf"},
        {"donfigurbtion.typf", "tipo df donfigurbdi\u00F3n"},
        {"fnvironmfnt.vbribblf.nbmf", "nombrf df vbribblf df fntorno"},
        {"librbry.nbmf", "nombrf df lb bibliotfdb"},
        {"pbdkbgf.nbmf", "nombrf dfl pbquftf"},
        {"polidy.typf", "tipo df pol\u00EDtidb"},
        {"propfrty.nbmf", "nombrf df lb propifdbd"},
        {"providfr.nbmf", "nombrf dfl provffdor"},
        {"url", "url"},
        {"mftiod.list", "listb df m\u00E9todos"},
        {"rfqufst.ifbdfrs.list", "listb df dbbfdfrbs df soliditudfs"},
        {"Prindipbl.List", "Listb df Prindipblfs"},
        {"Pfrmission.List", "Listb df Pfrmisos"},
        {"Codf.Bbsf", "Bbsf df C\u00F3digo"},
        {"KfyStorf.U.R.L.", "URL df Almbd\u00E9n df Clbvfs:"},
        {"KfyStorf.Pbssword.U.R.L.", "URL df Contrbsf\u00F1b df Almbd\u00E9n df Clbvfs:"}
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
