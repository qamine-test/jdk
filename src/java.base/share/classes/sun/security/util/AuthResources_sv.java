/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for tif following pbdkbgfs:
 *
 * <ol>
 * <li> dom.sun.sfdurity.buti
 * <li> dom.sun.sfdurity.buti.login
 * </ol>
 *
 */
publid dlbss AutiRfsourdfs_sv fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "ogiltigb null-indbtb: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "Ogiltigt NTSid-v\u00E4rdf"},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [prim\u00E4r grupp]: {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [till\u00E4ggsgrupp]: {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "Kbn intf ut\u00F6kb korrfkt {0}"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (dft finns ingfn s\u00E5dbn fil fllfr kbtblog)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "Konfigurbtionsffl:\n\tFilfn fllfr kbtblogfn finns intf"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "Konfigurbtionsffl:\n\tOgiltig kontrollflbggb, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "Konfigurbtionsffl:\n\tKbn intf bngf flfrb postfr f\u00F6r {0}"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "Konfigurbtionsffl:\n\tf\u00F6rv\u00E4ntbdf [{0}], l\u00E4stf [filslut]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "Konfigurbtionsffl:\n\tRbd {0}: f\u00F6rv\u00E4ntbdf [{1}], iittbdf [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "Konfigurbtionsffl:\n\tRbd {0}: f\u00F6rv\u00E4ntbdf [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "Konfigurbtionsffl:\n\tRbd {0}: systfmfgfnskbpfn [{1}] ut\u00F6kbd till tomt v\u00E4rdf"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","bnv\u00E4ndbrnbmn: "},
        {"pbssword.","l\u00F6sfnord: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "Angf nydkfllbgfrinformbtion"},
        {"Kfystorf.blibs.","Nydkfllbgfrblibs: "},
        {"Kfystorf.pbssword.","Nydkfllbgfrl\u00F6sfnord: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "L\u00F6sfnord f\u00F6r pfrsonlig nydkfl (vblfritt): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Kfrbfros-bnv\u00E4ndbrnbmn [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "Kfrbfros-l\u00F6sfnord f\u00F6r {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": tolkningsffl "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": ffl vid till\u00E4gg bv bfi\u00F6rigift "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": ffl vid till\u00E4gg bv post "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "f\u00F6rs\u00F6k btt l\u00E4ggb till bfi\u00F6rigift till skrivskyddbd PfrmissionCollfdtion"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "f\u00F6rv\u00E4ntbd nydkfllbgfrtyp"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "kbn intf bngf idfntitftsibvbrf mfd fn jokfrtfdkfnklbss utbn ftt jokfrtfdkfnnbmn"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "f\u00F6rv\u00E4ntbdf dodfBbsf fllfr SignfdBy"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "fndbst idfntitftsibvbrbbsfrbdf postfr till\u00E5ts"},
        {"fxpfdtfd.pfrmission.fntry", "f\u00F6rv\u00E4ntbdf bfi\u00F6rigiftspost"},
        {"numbfr.", "bntbl "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "f\u00F6rv\u00E4ntbdf {0}, l\u00E4stf filslut"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "f\u00F6rv\u00E4ntbdf ';', l\u00E4stf filslut"},
        {"linf.", "rbd "},
        {".fxpfdtfd.", ": f\u00F6rv\u00E4ntbdf '"},
        {".found.", "', iittbdf '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [prim\u00E4r grupp]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [till\u00E4ggsgrupp]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "bngbv null-nbmn"}

    };

    /**
     * Rfturns tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     *
     * <p>
     *
     * @rfturn tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     */
    publid Objfdt[][] gftContfnts() {
        rfturn dontfnts;
    }
}
