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

pbdkbgf sun.sfdurity.util;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for jbvbx.sfdurity.buti bnd sun.sfdurity.
 *
 */
publid dlbss Rfsourdfs_pt_BR fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
        {"invblid.null.input.s.", "fntrbdb(s) nulb(s) inv\u00E1lidb(s)"},
        {"bdtions.dbn.only.bf.rfbd.", "bs b\u00E7\u00F5fs s\u00F3 podfm sfr 'lidbs'"},
        {"pfrmission.nbmf.nbmf.syntbx.invblid.",
                "sintbxf inv\u00E1lidb do nomf db pfrmiss\u00E3o [{0}]: "},
        {"Crfdfntibl.Clbss.not.followfd.by.b.Prindipbl.Clbss.bnd.Nbmf",
                "Clbssf db Crfdfndibl n\u00E3o sfguidb por um Nomf f umb Clbssf do Prindipbl"},
        {"Prindipbl.Clbss.not.followfd.by.b.Prindipbl.Nbmf",
                "Clbssf do Prindipbl n\u00E3o sfguidb por um Nomf do Prindipbl"},
        {"Prindipbl.Nbmf.must.bf.surroundfd.by.quotfs",
                "O Nomf do Prindipbl dfvf fstbr fntrf bspbs"},
        {"Prindipbl.Nbmf.missing.fnd.quotf",
                "Fbltbm bs bspbs finbis no Nomf do Prindipbl"},
        {"PrivbtfCrfdfntiblPfrmission.Prindipbl.Clbss.dbn.not.bf.b.wilddbrd.vbluf.if.Prindipbl.Nbmf.is.not.b.wilddbrd.vbluf",
                "A Clbssf do Prindipbl PrivbtfCrfdfntiblPfrmission n\u00E3o podf sfr um vblor duringb (*) sf o Nomf do Prindipbl n\u00E3o for um vblor duringb (*)"},
        {"CrfdOwnfr.Prindipbl.Clbss.dlbss.Prindipbl.Nbmf.nbmf",
                "CrfdOwnfr:\n\tClbssf do Prindipbl = {0}\n\tNomf do Prindipbl = {1}"},

        // jbvbx.sfdurity.buti.x500
        {"providfd.null.nbmf", "nomf nulo fornfdido"},
        {"providfd.null.kfyword.mbp", "mbpb df pblbvrb-dibvf nulo fornfdido"},
        {"providfd.null.OID.mbp", "mbpb OID nulo fornfdido"},

        // jbvbx.sfdurity.buti.Subjfdt
        {"NEWLINE", "\n"},
        {"invblid.null.AddfssControlContfxt.providfd",
                "AddfssControlContfxt nulo inv\u00E1lido fornfdido"},
        {"invblid.null.bdtion.providfd", "b\u00E7\u00E3o nulb inv\u00E1lidb fornfdidb"},
        {"invblid.null.Clbss.providfd", "Clbssf nulb inv\u00E1lidb fornfdidb"},
        {"Subjfdt.", "Assunto:\n"},
        {".Prindipbl.", "\tPrindipbl: "},
        {".Publid.Crfdfntibl.", "\tCrfdfndibl P\u00FAblidb: "},
        {".Privbtf.Crfdfntibls.inbddfssiblf.",
                "\tCrfdfndibis Privbdbs inbdfss\u00EDvfis\n"},
        {".Privbtf.Crfdfntibl.", "\tCrfdfndibl Privbdb: "},
        {".Privbtf.Crfdfntibl.inbddfssiblf.",
                "\tCrfdfndibl Privbdb inbdfss\u00EDvfl\n"},
        {"Subjfdt.is.rfbd.only", "O Assunto \u00E9 somfntf pbrb lfiturb"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft",
                "tfntbtivb df bdidionbr um objfto quf n\u00E3o \u00E9 umb inst\u00E2ndib df jbvb.sfdurity.Prindipbl b um donjunto df prindipbis do Subjfdt"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss",
                "tfntbtivb df bdidionbr um objfto quf n\u00E3o \u00E9 umb inst\u00E2ndib df {0}"},

        // jbvbx.sfdurity.buti.login.AppConfigurbtionEntry
        {"LoginModulfControlFlbg.", "LoginModulfControlFlbg: "},

        // jbvbx.sfdurity.buti.login.LoginContfxt
        {"Invblid.null.input.nbmf", "Entrbdb nulb inv\u00E1lidb: nomf"},
        {"No.LoginModulfs.donfigurfd.for.nbmf",
         "Nfnium LoginModulf donfigurbdo pbrb {0}"},
        {"invblid.null.Subjfdt.providfd", "Subjfdt nulo inv\u00E1lido fornfdido"},
        {"invblid.null.CbllbbdkHbndlfr.providfd",
                "CbllbbdkHbndlfr nulo inv\u00E1lido fornfdido"},
        {"null.subjfdt.logout.dbllfd.bfforf.login",
                "Subjfdt nulo - log-out dibmbdo bntfs do log-in"},
        {"unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor",
                "n\u00E3o \u00E9 poss\u00EDvfl instbndibr LoginModulf, {0}, porquf flf n\u00E3o fornfdf um donstrutor sfm brgumfnto"},
        {"unbblf.to.instbntibtf.LoginModulf",
                "n\u00E3o \u00E9 poss\u00EDvfl instbndibr LoginModulf"},
        {"unbblf.to.instbntibtf.LoginModulf.",
                "n\u00E3o \u00E9 poss\u00EDvfl instbndibr LoginModulf: "},
        {"unbblf.to.find.LoginModulf.dlbss.",
                "n\u00E3o \u00E9 poss\u00EDvfl lodblizbr b dlbssf LoginModulf: "},
        {"unbblf.to.bddfss.LoginModulf.",
                "n\u00E3o \u00E9 poss\u00EDvfl bdfssbr LoginModulf: "},
        {"Login.Fbilurf.bll.modulfs.ignorfd",
                "Fblib df Log-in: todos os m\u00F3dulos ignorbdos"},

        // sun.sfdurity.providfr.PolidyFilf

        {"jbvb.sfdurity.polidy.frror.pbrsing.polidy.mfssbgf",
                "jbvb.sfdurity.polidy: frro durbntf o pbrsing df {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Pfrmission.pfrm.mfssbgf",
                "jbvb.sfdurity.polidy: frro bo bdidionbr b pfrmiss\u00E3o, {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Entry.mfssbgf",
                "jbvb.sfdurity.polidy: frro bo bdidionbr b Entrbdb:\n\t{0}"},
        {"blibs.nbmf.not.providfd.pf.nbmf.", "nomf df blibs n\u00E3o fornfdido ({0})"},
        {"unbblf.to.pfrform.substitution.on.blibs.suffix",
                "n\u00E3o \u00E9 poss\u00EDvfl rfblizbr b substitui\u00E7\u00E3o no blibs, {0}"},
        {"substitution.vbluf.prffix.unsupportfd",
                "vblor db substitui\u00E7\u00E3o, {0}, n\u00E3o suportbdo"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"typf.dbn.t.bf.null","o tipo n\u00E3o podf sfr nulo"},

        // sun.sfdurity.providfr.PolidyPbrsfr
        {"kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf",
                "kfystorfPbsswordURL n\u00E3o podf sfr fspfdifidbdo sfm quf b \u00E1rfb df brmbzfnbmfnto df dibvfs tbmb\u00E9m sfjb fspfdifidbdb"},
        {"fxpfdtfd.kfystorf.typf", "tipo df brmbzfnbmfnto df dibvfs fspfrbdo"},
        {"fxpfdtfd.kfystorf.providfr", "fornfdfdor db \u00E1rfb df brmbzfnbmfnto df dibvfs fspfrbdo"},
        {"multiplf.Codfbbsf.fxprfssions",
                "v\u00E1ribs fxprfss\u00F5fs CodfBbsf"},
        {"multiplf.SignfdBy.fxprfssions","v\u00E1ribs fxprfss\u00F5fs SignfdBy"},
        {"duplidbtf.kfystorf.dombin.nbmf","nomf do dom\u00EDnio db \u00E1rfb df brmbzfnbmfnto df tfdlbs duplidbdo: {0}"},
        {"duplidbtf.kfystorf.nbmf","nomf db \u00E1rfb df brmbzfnbmfnto df dibvfs duplidbdo: {0}"},
        {"SignfdBy.ibs.fmpty.blibs","SignfdBy tfm blibs vbzio"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "n\u00E3o \u00E9 poss\u00EDvfl fspfdifidbr um prindipbl dom umb dlbssf duringb sfm um nomf duringb"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl",
                "CodfBbsf ou SignfdBy ou Prindipbl fspfrbdo"},
        {"fxpfdtfd.pfrmission.fntry", "fntrbdb df pfrmiss\u00E3o fspfrbdb"},
        {"numbfr.", "n\u00FAmfro "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "fspfrbdo [{0}], lido [fim do brquivo]"},
        {"fxpfdtfd.rfbd.fnd.of.filf.",
                "fspfrbdo [;], lido [fim do brquivo]"},
        {"linf.numbfr.msg", "linib {0}: {1}"},
        {"linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl.",
                "linib {0}: fspfrbdb [{1}], fndontrbdb [{2}]"},
        {"null.prindipblClbss.or.prindipblNbmf",
                "prindipblClbss ou prindipblNbmf nulo"},

        // sun.sfdurity.pkds11.SunPKCS11
        {"PKCS11.Tokfn.providfrNbmf.Pbssword.",
                "Sfnib PKCS11 df Tokfn [{0}]: "},

        /* --- DEPRECATED --- */
        // jbvbx.sfdurity.buti.Polidy
        {"unbblf.to.instbntibtf.Subjfdt.bbsfd.polidy",
                "n\u00E3o \u00E9 poss\u00EDvfl instbndibr b pol\u00EDtidb dom bbsf fm Subjfdt"}
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

