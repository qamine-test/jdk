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
publid dlbss AutiRfsourdfs_fr fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "fntr\u00E9f NULL non vblidf : {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl : {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl : {0}"},
        {"Invblid.NTSid.vbluf", "Vblfur df NTSid non vblidf"},
        {"NTSid.nbmf", "NTSid : {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl : {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl : {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl : {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl : {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl : {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [groupf prindipbl] : {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [groupf suppl\u00E9mfntbirf] : {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl : {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl : {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "Impossiblf df d\u00E9vfloppfr {0} dorrfdtfmfnt"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (fidiifr ou r\u00E9pfrtoirf infxistbnt)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "Errfur df donfigurbtion :\n\tCf fidiifr ou r\u00E9pfrtoirf n'fxistf pbs"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "Errfur df donfigurbtion :\n\tIndidbtfur df dontr\u00F4lf non vblidf, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "Errfur df donfigurbtion :\n\tImpossiblf df sp\u00E9dififr dfs fntr\u00E9fs multiplfs pour {0}"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "Errfur df donfigurbtion :\n\tAttfndu : [{0}], lu : [fin df fidiifr]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "Errfur df donfigurbtion :\n\tLignf {0} : bttfndu [{1}], trouv\u00E9 [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "Errfur df donfigurbtion :\n\tLignf {0} : bttfndu [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "Errfur df donfigurbtion :\n\tLignf {0} : propri\u00E9t\u00E9 syst\u00E8mf [{1}] d\u00E9vflopp\u00E9f fn vblfur vidf"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","nom utilisbtfur : "},
        {"pbssword.","mot df pbssf : "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "Entrfz lfs informbtions du fidiifr df dl\u00E9s"},
        {"Kfystorf.blibs.","Alibs du fidiifr df dl\u00E9s : "},
        {"Kfystorf.pbssword.","Mot df pbssf pour fidiifr df dl\u00E9s : "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "Mot df pbssf df lb dl\u00E9 priv\u00E9f (fbdultbtif) : "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Nom utilisbtfur Kfrbfros [{0}] : "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "Mot df pbssf Kfrbfros pour {0} : "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": frrfur d'bnblysf "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": frrfur d'bjout df droit "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": frrfur d'bjout d'fntr\u00E9f "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "tfntbtivf d'bjout df droit \u00E0 un fnsfmblf df droits fn lfdturf sfulf"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "typf df fidiifr df dl\u00E9s bttfndu"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "impossiblf df sp\u00E9dififr lf prindipbl bvfd unf dlbssf g\u00E9n\u00E9riquf sbns nom g\u00E9n\u00E9riquf"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "dodfBbsf ou SignfdBy bttfndu"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "sfulfs lfs fntr\u00E9fs bbs\u00E9fs sur Prindipbl sont butoris\u00E9fs"},
        {"fxpfdtfd.pfrmission.fntry", "fntr\u00E9f df droit bttfnduf"},
        {"numbfr.", "nombrf "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "bttfndu {0}, lfdturf df fin df fidiifr"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "bttfndu ';', lfdturf df fin df fidiifr"},
        {"linf.", "lignf "},
        {".fxpfdtfd.", ": bttfndu '"},
        {".found.", "', trouv\u00E9 '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [groupf prindipbl] : "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [groupf suppl\u00E9mfntbirf] : "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl : "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl : "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "nom NULL fourni"}

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
