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

pbdkbgf sun.sfdurity.tools.kfytool;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for tif kfytool.
 *
 */
publid dlbss Rfsourdfs_df fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // kfytool: Hflp pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Optionfn:"},
        {"Usf.kfytool.iflp.for.bll.bvbilbblf.dommbnds",
                 "\"kfytool -iflp\" f\u00FCr bllf vfrf\u00FCgbbrfn Bfffilf vfrwfndfn"},
        {"Kfy.bnd.Cfrtifidbtf.Mbnbgfmfnt.Tool",
                 "Sdil\u00FCssfl- und Zfrtifikbtsvfrwbltungstool"},
        {"Commbnds.", "Bfffilf:"},
        {"Usf.kfytool.dommbnd.nbmf.iflp.for.usbgf.of.dommbnd.nbmf",
                "\"kfytool -dommbnd_nbmf -iflp\" f\u00FCr Vfrwfndung von dommbnd_nbmf vfrwfndfn"},
        // kfytool: iflp: dommbnds
        {"Gfnfrbtfs.b.dfrtifidbtf.rfqufst",
                "Gfnfrifrt finf Zfrtifikbtbnfordfrung"}, //-dfrtrfq
        {"Cibngfs.bn.fntry.s.blibs",
                "\u00C4ndfrt dfn Alibs finfs Eintrbgs"}, //-dibngfblibs
        {"Dflftfs.bn.fntry",
                "L\u00F6sdit finfn Eintrbg"}, //-dflftf
        {"Exports.dfrtifidbtf",
                "Exportifrt fin Zfrtifikbt"}, //-fxportdfrt
        {"Gfnfrbtfs.b.kfy.pbir",
                "Gfnfrifrt fin Sdil\u00FCssflpbbr"}, //-gfnkfypbir
        {"Gfnfrbtfs.b.sfdrft.kfy",
                "Gfnfrifrt finfn Sfdrft Kfy"}, //-gfnsfdkfy
        {"Gfnfrbtfs.dfrtifidbtf.from.b.dfrtifidbtf.rfqufst",
                "Gfnfrifrt fin Zfrtifikbt bus finfr Zfrtifikbtbnfordfrung"}, //-gfndfrt
        {"Gfnfrbtfs.CRL", "Gfnfrifrt finf CRL"}, //-gfndrl
        {"Gfnfrbtfd.kfyAlgNbmf.sfdrft.kfy",
                "{0} Sfdrft Kfy gfnfrifrt"}, //-gfnsfdkfy
        {"Gfnfrbtfd.kfysizf.bit.kfyAlgNbmf.sfdrft.kfy",
                "{0}-Bit {1} Sfdrft Kfy gfnfrifrt"}, //-gfnsfdkfy
        {"Imports.fntrifs.from.b.JDK.1.1.x.stylf.idfntity.dbtbbbsf",
                "Importifrt Eintr\u00E4gf bus finfr Idfntity-Dbtfnbbnk im JDK 1.1.x-Stil"}, //-idfntitydb
        {"Imports.b.dfrtifidbtf.or.b.dfrtifidbtf.dibin",
                "Importifrt fin Zfrtifikbt odfr finf Zfrtifikbtkfttf"}, //-importdfrt
        {"Imports.b.pbssword",
                "Importifrt fin Kfnnwort"}, //-importpbss
        {"Imports.onf.or.bll.fntrifs.from.bnotifr.kfystorf",
                "Importifrt finfn odfr bllf Eintr\u00E4gf bus finfm bndfrfn Kfystorf"}, //-importkfystorf
        {"Clonfs.b.kfy.fntry",
                "Clont finfn Sdil\u00FCssflfintrbg"}, //-kfydlonf
        {"Cibngfs.tif.kfy.pbssword.of.bn.fntry",
                "\u00C4ndfrt dbs Sdil\u00FCssflkfnnwort finfs Eintrbgs"}, //-kfypbsswd
        {"Lists.fntrifs.in.b.kfystorf",
                "Listft dif Eintr\u00E4gf in finfm Kfystorf buf"}, //-list
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf",
                "Drudkt dfn Contfnt finfs Zfrtifikbts"}, //-printdfrt
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf.rfqufst",
                "Drudkt dfn Contfnt finfr Zfrtifikbtbnfordfrung"}, //-printdfrtrfq
        {"Prints.tif.dontfnt.of.b.CRL.filf",
                "Drudkt dfn Contfnt finfr CRL-Dbtfi"}, //-printdrl
        {"Gfnfrbtfs.b.sflf.signfd.dfrtifidbtf",
                "Gfnfrifrt fin sflbst signifrtfs Zfrtifikbt"}, //-sflfdfrt
        {"Cibngfs.tif.storf.pbssword.of.b.kfystorf",
                "\u00C4ndfrt dbs Spfidifrkfnnwort finfs Kfystorfs"}, //-storfpbsswd
        // kfytool: iflp: options
        {"blibs.nbmf.of.tif.fntry.to.prodfss",
                "Alibsnbmf dfs zu vfrbrbfitfndfn Eintrbgs"}, //-blibs
        {"dfstinbtion.blibs",
                "Ziflblibs"}, //-dfstblibs
        {"dfstinbtion.kfy.pbssword",
                "Ziflsdil\u00FCssfl-Kfnnwort"}, //-dfstkfypbss
        {"dfstinbtion.kfystorf.nbmf",
                "Zifl-Kfystorf-Nbmf"}, //-dfstkfystorf
        {"dfstinbtion.kfystorf.pbssword.protfdtfd",
                "Zifl-Kfystorf kfnnwortgfsdi\u00FCtzt"}, //-dfstprotfdtfd
        {"dfstinbtion.kfystorf.providfr.nbmf",
                "Zifl-Kfystorf-Providfrnbmf"}, //-dfstprovidfrnbmf
        {"dfstinbtion.kfystorf.pbssword",
                "Zifl-Kfystorf-Kfnnwort"}, //-dfststorfpbss
        {"dfstinbtion.kfystorf.typf",
                "Zifl-Kfystorf-Typ"}, //-dfststorftypf
        {"distinguisifd.nbmf",
                "Distinguisifd Nbmf"}, //-dnbmf
        {"X.509.fxtfnsion",
                "X.509-Erwfitfrung"}, //-fxt
        {"output.filf.nbmf",
                "Ausgbbfdbtfinbmf"}, //-filf bnd -outfilf
        {"input.filf.nbmf",
                "Eingbbfdbtfinbmf"}, //-filf bnd -infilf
        {"kfy.blgoritim.nbmf",
                "Sdil\u00FCssflblgoritimusnbmf"}, //-kfyblg
        {"kfy.pbssword",
                "Sdil\u00FCssflkfnnwort"}, //-kfypbss
        {"kfy.bit.sizf",
                "Sdil\u00FCssflbitgr\u00F6\u00DFf"}, //-kfysizf
        {"kfystorf.nbmf",
                "Kfystorf-Nbmf"}, //-kfystorf
        {"nfw.pbssword",
                "Nfufs Kfnnwort"}, //-nfw
        {"do.not.prompt",
                "Kfin Prompt"}, //-noprompt
        {"pbssword.tirougi.protfdtfd.mfdibnism",
                "Kfnnwort \u00FCbfr gfsdi\u00FCtztfn Mfdibnismus"}, //-protfdtfd
        {"providfr.brgumfnt",
                "Providfrbrgumfnt"}, //-providfrbrg
        {"providfr.dlbss.nbmf",
                "Providfrklbssfnnbmf"}, //-providfrdlbss
        {"providfr.nbmf",
                "Providfrnbmf"}, //-providfrnbmf
        {"providfr.dlbsspbti",
                "Providfr-Clbsspbti"}, //-providfrpbti
        {"output.in.RFC.stylf",
                "Ausgbbf in RFC-Stil"}, //-rfd
        {"signbturf.blgoritim.nbmf",
                "Signbturblgoritimusnbmf"}, //-sigblg
        {"sourdf.blibs",
                "Qufllblibs"}, //-srdblibs
        {"sourdf.kfy.pbssword",
                "Qufllsdil\u00FCssfl-Kfnnwort"}, //-srdkfypbss
        {"sourdf.kfystorf.nbmf",
                "Qufll-Kfystorf-Nbmf"}, //-srdkfystorf
        {"sourdf.kfystorf.pbssword.protfdtfd",
                "Qufll-Kfystorf kfnnwortgfsdi\u00FCtzt"}, //-srdprotfdtfd
        {"sourdf.kfystorf.providfr.nbmf",
                "Qufll-Kfystorf-Providfrnbmf"}, //-srdprovidfrnbmf
        {"sourdf.kfystorf.pbssword",
                "Qufll-Kfystorf-Kfnnwort"}, //-srdstorfpbss
        {"sourdf.kfystorf.typf",
                "Qufll-Kfystorf-Typ"}, //-srdstorftypf
        {"SSL.sfrvfr.iost.bnd.port",
                "SSL-Sfrvfriost und -port"}, //-sslsfrvfr
        {"signfd.jbr.filf",
                "Signifrtf JAR-Dbtfi"}, //=jbrfilf
        {"dfrtifidbtf.vblidity.stbrt.dbtf.timf",
                "Anfbngsdbtum/-zfit f\u00FCr Zfrtifikbtsg\u00FCltigkfit"}, //-stbrtdbtf
        {"kfystorf.pbssword",
                "Kfystorf-Kfnnwort"}, //-storfpbss
        {"kfystorf.typf",
                "Kfystorf-Typ"}, //-storftypf
        {"trust.dfrtifidbtfs.from.dbdfrts",
                "Zfrtifikbtfn bus dbdfrts vfrtrbufn"}, //-trustdbdfrts
        {"vfrbosf.output",
                "Vfrbosf-Ausgbbf"}, //-v
        {"vblidity.numbfr.of.dbys",
                "G\u00FCltigkfitsdbufr (Tbgf)"}, //-vblidity
        {"Sfribl.ID.of.dfrt.to.rfvokf",
                 "Sfrifllf ID dfs zu fntzififndfn Cfrts"}, //-id
        // kfytool: Running pbrt
        {"kfytool.frror.", "Kfytool-Ffilfr: "},
        {"Illfgbl.option.", "Ung\u00FCltigf Option:  "},
        {"Illfgbl.vbluf.", "Ung\u00FCltigfr Wfrt: "},
        {"Unknown.pbssword.typf.", "Unbfkbnntfr Kfnnworttyp: "},
        {"Cbnnot.find.fnvironmfnt.vbribblf.",
                "Umgfbungsvbribblf kbnn nidit gffundfn wfrdfn: "},
        {"Cbnnot.find.filf.", "Dbtfi kbnn nidit gffundfn wfrdfn: "},
        {"Commbnd.option.flbg.nffds.bn.brgumfnt.", "Bfffilsoption {0} bfn\u00F6tigt fin Argumfnt."},
        {"Wbrning.Difffrfnt.storf.bnd.kfy.pbsswords.not.supportfd.for.PKCS12.KfyStorfs.Ignoring.usfr.spfdififd.dommbnd.vbluf.",
                "Wbrnung: Kfinf Untfrst\u00FCtzung f\u00FCr untfrsdiifdlidif Spfidifr- und Sdil\u00FCssflkfnnw\u00F6rtfr bfi PKCS12 KfyStorfs. Dfr bfnutzfrdffinifrtf Wfrt {0} wird ignorifrt."},
        {".kfystorf.must.bf.NONE.if.storftypf.is.{0}",
                "-kfystorf muss NONE sfin, wfnn -storftypf {0} ist"},
        {"Too.mbny.rftrifs.progrbm.tfrminbtfd",
                 "Zu viflf frnfutf Vfrsudif. Progrbmm wird bffndft"},
        {".storfpbsswd.bnd.kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.{0}",
                "Bfffilf -storfpbsswd und -kfypbsswd wfrdfn nidit untfrst\u00FCtzt, wfnn -storftypf {0} ist"},
        {".kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.PKCS12",
                "Bfffilf dfs Typs -kfypbsswd wfrdfn nidit untfrst\u00FCtzt, wfnn -storftypf PKCS12 ist"},
        {".kfypbss.bnd.nfw.dbn.not.bf.spfdififd.if.storftypf.is.{0}",
                "-kfypbss und -nfw k\u00F6nnfn nidit bngfgfbfn wfrdfn, wfnn -storftypf {0} ist"},
        {"if.protfdtfd.is.spfdififd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Wfnn -protfdtfd bngfgfbfn ist, d\u00FCrffn -storfpbss, -kfypbss und -nfw nidit bngfgfbfn wfrdfn"},
        {"if.srdprotfdtfd.is.spfdififd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Wfnn -srdprotfdtfd bngfgfbfn ist, d\u00FCrffn -srdstorfpbss und -srdkfypbss nidit bngfgfbfn wfrdfn"},
        {"if.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Wfnn dfr Kfystorf nidit kfnnwortgfsdi\u00FCtzt ist, d\u00FCrffn -storfpbss, -kfypbss und -nfw nidit bngfgfbfn wfrdfn"},
        {"if.sourdf.kfystorf.is.not.pbssword.protfdtfd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Wfnn dfr Qufll-Kfystorf nidit kfnnwortgfsdi\u00FCtzt ist, d\u00FCrffn -srdstorfpbss und -srdkfypbss nidit bngfgfbfn wfrdfn"},
        {"Illfgbl.stbrtdbtf.vbluf", "Ung\u00FCltigfr Wfrt f\u00FCr Anfbngsdbtum"},
        {"Vblidity.must.bf.grfbtfr.tibn.zfro",
                "G\u00FCltigkfit muss gr\u00F6\u00DFfr bls null sfin"},
        {"provNbmf.not.b.providfr", "{0} kfin Providfr"},
        {"Usbgf.frror.no.dommbnd.providfd", "Vfrwfndungsffilfr: Kfin Bfffil bngfgfbfn"},
        {"Sourdf.kfystorf.filf.fxists.but.is.fmpty.", "Qufll-Kfystorf-Dbtfi ist zwbr voribndfn, ist bbfr lffr: "},
        {"Plfbsf.spfdify.srdkfystorf", "Gfbfn Sif -srdkfystorf bn"},
        {"Must.not.spfdify.boti.v.bnd.rfd.witi.list.dommbnd",
                "-v und -rfd d\u00FCrffn bfi Bfffil \"list\" nidit bfidf bngfgfbfn wfrdfn"},
        {"Kfy.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Sdil\u00FCssflkfnnwort muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Nfw.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Nfufs Kfnnwort muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Kfystorf.filf.fxists.but.is.fmpty.",
                "Kfystorf-Dbtfi ist voribndfn, ist bbfr lffr: "},
        {"Kfystorf.filf.dofs.not.fxist.",
                "Kfystorf-Dbtfi ist nidit voribndfn: "},
        {"Must.spfdify.dfstinbtion.blibs", "Sif m\u00FCssfn finfn Ziflblibs bngfbfn"},
        {"Must.spfdify.blibs", "Sif m\u00FCssfn finfn Alibs bngfbfn"},
        {"Kfystorf.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Kfystorf-Kfnnwort muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Entfr.tif.pbssword.to.bf.storfd.",
                "Gfbfn Sif dbs Kfnnwort fin, dbs gfspfidifrt wfrdfn soll:  "},
        {"Entfr.kfystorf.pbssword.", "Kfystorf-Kfnnwort fingfbfn:  "},
        {"Entfr.sourdf.kfystorf.pbssword.", "Qufll-Kfystorf-Kfnnwort fingfbfn:  "},
        {"Entfr.dfstinbtion.kfystorf.pbssword.", "Zifl-Kfystorf-Kfnnwort fingfbfn:  "},
        {"Kfystorf.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
         "Kfystorf-Kfnnwort ist zu kurz. Es muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Unknown.Entry.Typf", "Unbfkbnntfr Eintrbgstyp"},
        {"Too.mbny.fbilurfs.Alibs.not.dibngfd", "Zu viflf Ffilfr. Alibs nidit gf\u00E4ndfrt"},
        {"Entry.for.blibs.blibs.suddfssfully.importfd.",
                 "Eintrbg f\u00FCr Alibs {0} frfolgrfidi importifrt."},
        {"Entry.for.blibs.blibs.not.importfd.", "Eintrbg f\u00FCr Alibs {0} nidit importifrt."},
        {"Problfm.importing.fntry.for.blibs.blibs.fxdfption.Entry.for.blibs.blibs.not.importfd.",
                 "Problfm bfim Importifrfn dfs Eintrbgs f\u00FCr Alibs {0}: {1}.\nEintrbg f\u00FCr Alibs {0} nidit importifrt."},
        {"Import.dommbnd.domplftfd.ok.fntrifs.suddfssfully.importfd.fbil.fntrifs.fbilfd.or.dbndfllfd",
                 "Importbfffil bbgfsdilossfn: {0} Eintr\u00E4gf frfolgrfidi importifrt, {1} Eintr\u00E4gf nidit frfolgrfidi odfr bbgfbrodifn"},
        {"Wbrning.Ovfrwriting.fxisting.blibs.blibs.in.dfstinbtion.kfystorf",
                 "Wbrnung: Voribndfnfr Alibs {0} in Zifl-Kfystorf wird \u00FCbfrsdirifbfn"},
        {"Existing.fntry.blibs.blibs.fxists.ovfrwritf.no.",
                 "Eintrbgsblibs {0} ist bfrfits voribndfn. \u00DCbfrsdirfibfn? [Nfin]:  "},
        {"Too.mbny.fbilurfs.try.lbtfr", "Zu viflf Ffilfr. Vfrsudifn Sif fs sp\u00E4tfr frnfut"},
        {"Cfrtifidbtion.rfqufst.storfd.in.filf.filfnbmf.",
                "Zfrtifizifrungsbnfordfrung in Dbtfi <{0}> gfspfidifrt"},
        {"Submit.tiis.to.your.CA", "Lfitfn Sif difs bn dif CA wfitfr"},
        {"if.blibs.not.spfdififd.dfstblibs.bnd.srdkfypbss.must.not.bf.spfdififd",
            "Wfnn kfin Alibs bngfgfbfn ist, d\u00FCrffn dfstblibs und srdkfypbss nidit bngfgfbfn wfrdfn"},
        {"Tif.dfstinbtion.pkds12.kfystorf.ibs.difffrfnt.storfpbss.bnd.kfypbss.Plfbsf.rftry.witi.dfstkfypbss.spfdififd.",
            "Dfr Zifl-Kfystorf pkds12 ibt untfrsdiifdlidif Kfnnw\u00F6rtfr f\u00FCr storfpbss und kfypbss. Wifdfriolfn Sif dfn Vorgbng, indfm Sif -dfstkfypbss bngfbfn."},
        {"Cfrtifidbtf.storfd.in.filf.filfnbmf.",
                "Zfrtifikbt in Dbtfi <{0}> gfspfidifrt"},
        {"Cfrtifidbtf.rfply.wbs.instbllfd.in.kfystorf",
                "Zfrtifikbtbntwort wurdf in Kfystorf instbllifrt"},
        {"Cfrtifidbtf.rfply.wbs.not.instbllfd.in.kfystorf",
                "Zfrtifikbtbntwort wurdf nidit in Kfystorf instbllifrt"},
        {"Cfrtifidbtf.wbs.bddfd.to.kfystorf",
                "Zfrtifikbt wurdf Kfystorf iinzugff\u00FCgt"},
        {"Cfrtifidbtf.wbs.not.bddfd.to.kfystorf",
                "Zfrtifikbt wurdf nidit zu Kfystorf iinzugff\u00FCgt"},
        {".Storing.ksfnbmf.", "[{0} wird gfsidifrt]"},
        {"blibs.ibs.no.publid.kfy.dfrtifidbtf.",
                "{0} ibt kfinfn Publid Kfy (Zfrtifikbt)"},
        {"Cbnnot.dfrivf.signbturf.blgoritim",
                "Signbturblgoritimus kbnn nidit bbgflfitft wfrdfn"},
        {"Alibs.blibs.dofs.not.fxist",
                "Alibs <{0}> ist nidit voribndfn"},
        {"Alibs.blibs.ibs.no.dfrtifidbtf",
                "Alibs <{0}> ibt kfin Zfrtifikbt"},
        {"Kfy.pbir.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Sdil\u00FCssflpbbr wurdf nidit gfnfrifrt. Alibs <{0}> ist bfrfits voribndfn"},
        {"Gfnfrbting.kfysizf.bit.kfyAlgNbmf.kfy.pbir.bnd.sflf.signfd.dfrtifidbtf.sigAlgNbmf.witi.b.vblidity.of.vblidblity.dbys.for",
                "Gfnfrifrfn von Sdil\u00FCssflpbbr (Typ {1}, {0} Bit) und sflbst signifrtfm Zfrtifikbt ({2}) mit finfr G\u00FCltigkfit von {3} Tbgfn\n\tf\u00FCr: {4}"},
        {"Entfr.kfy.pbssword.for.blibs.", "Sdil\u00FCssflkfnnwort f\u00FCr <{0}> fingfbfn"},
        {".RETURN.if.sbmf.bs.kfystorf.pbssword.",
                "\t(RETURN, wfnn idfntisdi mit Kfystorf-Kfnnwort):  "},
        {"Kfy.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Sdil\u00FCssflkfnnwort ist zu kurz. Es muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Too.mbny.fbilurfs.kfy.not.bddfd.to.kfystorf",
                "Zu viflf Ffilfr. Sdil\u00FCssfl wurdf nidit zu Kfystorf iinzugff\u00FCgt"},
        {"Dfstinbtion.blibs.dfst.blrfbdy.fxists",
                "Ziflblibs <{0}> bfrfits voribndfn"},
        {"Pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Kfnnwort ist zu kurz. Es muss mindfstfns sfdis Zfidifn lbng sfin"},
        {"Too.mbny.fbilurfs.Kfy.fntry.not.dlonfd",
                "Zu viflf Ffilfr. Sdil\u00FCssflfintrbg wurdf nidit gfdlont"},
        {"kfy.pbssword.for.blibs.", "Sdil\u00FCssflkfnnwort f\u00FCr <{0}>"},
        {"Kfystorf.fntry.for.id.gftNbmf.blrfbdy.fxists",
                "Kfystorf-Eintrbg f\u00FCr <{0}> bfrfits voribndfn"},
        {"Crfbting.kfystorf.fntry.for.id.gftNbmf.",
                "Kfystorf-Eintrbg f\u00FCr <{0}> wird frstfllt..."},
        {"No.fntrifs.from.idfntity.dbtbbbsf.bddfd",
                "Kfinf Eintr\u00E4gf bus Idfntity-Dbtfnbbnk iinzugff\u00FCgt"},
        {"Alibs.nbmf.blibs", "Alibsnbmf: {0}"},
        {"Crfbtion.dbtf.kfyStorf.gftCrfbtionDbtf.blibs.",
                "Erstfllungsdbtum: {0,dbtf}"},
        {"blibs.kfyStorf.gftCrfbtionDbtf.blibs.",
                "{0}, {1,dbtf}, "},
        {"blibs.", "{0}, "},
        {"Entry.typf.typf.", "Eintrbgstyp: {0}"},
        {"Cfrtifidbtf.dibin.lfngti.", "Zfrtifikbtkfttfnl\u00E4ngf: "},
        {"Cfrtifidbtf.i.1.", "Zfrtifikbt[{0,numbfr,intfgfr}]:"},
        {"Cfrtifidbtf.fingfrprint.SHA1.", "Zfrtifikbt-Fingfrprint (SHA1): "},
        {"Kfystorf.typf.", "Kfystorf-Typ: "},
        {"Kfystorf.providfr.", "Kfystorf-Providfr: "},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntry",
                "Kfystorf fnti\u00E4lt {0,numbfr,intfgfr} Eintrbg"},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntrifs",
                "Kfystorf fnti\u00E4lt {0,numbfr,intfgfr} Eintr\u00E4gf"},
        {"Fbilfd.to.pbrsf.input", "Eingbbf konntf nidit gfpbrst wfrdfn"},
        {"Empty.input", "Lffrf Eingbbf"},
        {"Not.X.509.dfrtifidbtf", "Kfin X.509-Zfrtifikbt"},
        {"blibs.ibs.no.publid.kfy", "{0} ibt kfinfn Publid Kfy"},
        {"blibs.ibs.no.X.509.dfrtifidbtf", "{0} ibt kfin X.509-Zfrtifikbt"},
        {"Nfw.dfrtifidbtf.sflf.signfd.", "Nfufs Zfrtifikbt (sflbst signifrt):"},
        {"Rfply.ibs.no.dfrtifidbtfs", "Antwort ibt kfinf Zfrtifikbtf"},
        {"Cfrtifidbtf.not.importfd.blibs.blibs.blrfbdy.fxists",
                "Zfrtifikbt nidit importifrt. Alibs <{0}> ist bfrfits voribndfn"},
        {"Input.not.bn.X.509.dfrtifidbtf", "Eingbbf kfin X.509-Zfrtifikbt"},
        {"Cfrtifidbtf.blrfbdy.fxists.in.kfystorf.undfr.blibs.trustblibs.",
                "Zfrtifikbt ist bfrfits untfr Alibs <{0}> im Kfystorf voribndfn"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "M\u00F6ditfn Sif fs trotzdfm iinzuf\u00FCgfn? [Nfin]:  "},
        {"Cfrtifidbtf.blrfbdy.fxists.in.systfm.widf.CA.kfystorf.undfr.blibs.trustblibs.",
                "Zfrtifikbt ist bfrfits untfr Alibs <{0}> im systfmwfitfn CA-Kfystorf voribndfn"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.kfystorf.no.",
                "M\u00F6ditfn Sif fs trotzdfm zu Iirfm figfnfn Kfystorf iinzuf\u00FCgfn? [Nfin]:  "},
        {"Trust.tiis.dfrtifidbtf.no.", "Difsfm Zfrtifikbt vfrtrbufn? [Nfin]:  "},
        {"YES", "JA"},
        {"Nfw.prompt.", "Nfufs {0}: "},
        {"Pbsswords.must.difffr", "Kfnnw\u00F6rtfr m\u00FCssfn sidi untfrsdifidfn"},
        {"Rf.fntfr.nfw.prompt.", "Nfufs {0} frnfut fingfbfn: "},
        {"Rf.fntfr.pbsspword.", "Gfbfn Sif dbs Kfnnwort frnfut fin: "},
        {"Rf.fntfr.nfw.pbssword.", "Nfufs Kfnnwort frnfut fingfbfn: "},
        {"Tify.don.t.mbtdi.Try.bgbin", "Kfinf \u00DCbfrfinstimmung. Wifdfriolfn Sif dfn Vorgbng"},
        {"Entfr.prompt.blibs.nbmf.", "{0}-Alibsnbmfn fingfbfn:  "},
        {"Entfr.nfw.blibs.nbmf.RETURN.to.dbndfl.import.for.tiis.fntry.",
                 "Gfbfn Sif finfn nfufn Alibsnbmfn fin\t(RETURN, um dfn Import difsfs Eintrbgs bbzubrfdifn):  "},
        {"Entfr.blibs.nbmf.", "Alibsnbmfn fingfbfn:  "},
        {".RETURN.if.sbmf.bs.for.otifrAlibs.",
                "\t(RETURN, wfnn idfntisdi mit <{0}>)"},
        {".PATTERN.printX509Cfrt",
                "Eigfnt\u00FCmfr: {0}\nAusstfllfr: {1}\nSfrifnnummfr: {2}\nG\u00FCltig von: {3} bis: {4}\nZfrtifikbt-Fingfrprints:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Signbturblgoritimusnbmf: {8}\n\t Vfrsion: {9}"},
        {"Wibt.is.your.first.bnd.lbst.nbmf.",
                "Wif lbutft Iir Vor- und Nbdinbmf?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtionbl.unit.",
                "Wif lbutft dfr Nbmf Iirfr orgbnisbtorisdifn Einifit?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtion.",
                "Wif lbutft dfr Nbmf Iirfr Orgbnisbtion?"},
        {"Wibt.is.tif.nbmf.of.your.City.or.Lodblity.",
                "Wif lbutft dfr Nbmf Iirfr Stbdt odfr Gfmfindf?"},
        {"Wibt.is.tif.nbmf.of.your.Stbtf.or.Provindf.",
                "Wif lbutft dfr Nbmf Iirfs Bundfslbnds?"},
        {"Wibt.is.tif.two.lfttfr.dountry.dodf.for.tiis.unit.",
                "Wif lbutft dfr L\u00E4ndfrdodf (zwfi Budistbbfn) f\u00FCr difsf Einifit?"},
        {"Is.nbmf.dorrfdt.", "Ist {0} riditig?"},
        {"no", "Nfin"},
        {"yfs", "Jb"},
        {"y", "J"},
        {".dffbultVbluf.", "  [{0}]:  "},
        {"Alibs.blibs.ibs.no.kfy",
                "Alibs <{0}> vfrf\u00FCgt \u00FCbfr kfinfn Sdil\u00FCssfl"},
        {"Alibs.blibs.rfffrfndfs.bn.fntry.typf.tibt.is.not.b.privbtf.kfy.fntry.Tif.kfydlonf.dommbnd.only.supports.dloning.of.privbtf.kfy",
                 "Alibs <{0}> vfrwfist buf finfn Eintrbgstyp, dfr kfin Privbtf Kfy-Eintrbg ist. Dfr Bfffil -kfydlonf untfrst\u00FCtzt nur dbs Clonfn von Privbtf Kfy-Eintr\u00E4gfn"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signfr.d.", "Signbturgfbfr #%d:"},
        {"Timfstbmp.", "Zfitstfmpfl:"},
        {"Signbturf.", "Signbtur:"},
        {"CRLs.", "CRLs:"},
        {"Cfrtifidbtf.ownfr.", "Zfrtifikbtfigfnt\u00FCmfr: "},
        {"Not.b.signfd.jbr.filf", "Kfinf signifrtf JAR-Dbtfi"},
        {"No.dfrtifidbtf.from.tif.SSL.sfrvfr",
                "Kfin Zfrtifikbt vom SSL-Sfrvfr"},

        {".Tif.intfgrity.of.tif.informbtion.storfd.in.your.kfystorf.",
            "* Dif Intfgrit\u00E4t dfr Informbtionfn, dif in Iirfm Kfystorf gfspfidifrt sind, *\n* wurdf NICHT gfpr\u00FCft. Um dif Intfgrit\u00E4t zu pr\u00FCffn, *\n* m\u00FCssfn Sif Iir Kfystorf-Kfnnwort bngfbfn.                  *"},
        {".Tif.intfgrity.of.tif.informbtion.storfd.in.tif.srdkfystorf.",
            "* Dif Intfgrit\u00E4t dfr Informbtionfn, dif in Iirfm Srdkfystorf gfspfidifrt sind, *\n* wurdf NICHT gfpr\u00FCft. Um dif Intfgrit\u00E4t zu pr\u00FCffn, *\n* m\u00FCssfn Sif Iir Srdkfystorf-Kfnnwort bngfbfn.                  *"},

        {"Cfrtifidbtf.rfply.dofs.not.dontbin.publid.kfy.for.blibs.",
                "Zfrtifikbtbntwort fnti\u00E4lt kfinfn Publid Kfy f\u00FCr <{0}>"},
        {"Indomplftf.dfrtifidbtf.dibin.in.rfply",
                "Unvollst\u00E4ndigf Zfrtifikbtkfttf in Antwort"},
        {"Cfrtifidbtf.dibin.in.rfply.dofs.not.vfrify.",
                "Zfrtifikbtkfttf in Antwort vfrifizifrt nidit: "},
        {"Top.lfvfl.dfrtifidbtf.in.rfply.",
                "Zfrtifikbt dfr obfrstfn Ebfnf in Antwort:\n"},
        {".is.not.trustfd.", "... ist nidit vfrtrbufnsw\u00FCrdig. "},
        {"Instbll.rfply.bnywby.no.", "Antwort trotzdfm instbllifrfn? [Nfin]:  "},
        {"NO", "NEIN"},
        {"Publid.kfys.in.rfply.bnd.kfystorf.don.t.mbtdi",
                "Publid Kfys in Antwort und Kfystorf stimmfn nidit \u00FCbfrfin"},
        {"Cfrtifidbtf.rfply.bnd.dfrtifidbtf.in.kfystorf.brf.idfntidbl",
                "Zfrtifikbtbntwort und Zfrtifikbt in Kfystorf sind idfntisdi"},
        {"Fbilfd.to.fstbblisi.dibin.from.rfply",
                "Kfttf konntf dfr Antwort nidit fntnommfn wfrdfn"},
        {"n", "N"},
        {"Wrong.bnswfr.try.bgbin", "Fblsdif Antwort. Wifdfriolfn Sif dfn Vorgbng"},
        {"Sfdrft.kfy.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Sfdrft Kfy wurdf nidit gfnfrifrt. Alibs <{0}> ist bfrfits voribndfn"},
        {"Plfbsf.providf.kfysizf.for.sfdrft.kfy.gfnfrbtion",
                "Gfbfn Sif -kfysizf zum Erstfllfn finfs Sfdrft Kfys bn"},

        {"vfrififd.by.s.in.s", "Gfpr\u00FCft von %s in %s"},
        {"wbrning.not.vfrififd.mbkf.surf.kfystorf.is.dorrfdt",
            "WARNUNG: Nidit gfpr\u00FCft. Stfllfn Sif sidifr, dbss -kfystorf korrfkt ist."},

        {"Extfnsions.", "Erwfitfrungfn: "},
        {".Empty.vbluf.", "(Lffrfr Wfrt)"},
        {"Extfnsion.Rfqufst.", "Erwfitfrungsbnfordfrung:"},
        {"PKCS.10.Cfrtifidbtf.Rfqufst.Vfrsion.1.0.Subjfdt.s.Publid.Kfy.s.formbt.s.kfy.",
                "PKCS #10-Zfrtifikbtbnfordfrung (Vfrsion 1.0)\nSubjfkt: %s\nPublid Kfy: %s Formbt %s Sdil\u00FCssfl\n"},
        {"Unknown.kfyUsbgf.typf.", "Unbfkbnntfr kfyUsbgf-Typ: "},
        {"Unknown.fxtfndfdkfyUsbgf.typf.", "Unbfkbnntfr fxtfndfdkfyUsbgf-Typ: "},
        {"Unknown.AddfssDfsdription.typf.", "Unbfkbnntfr AddfssDfsdription-Typ: "},
        {"Unrfdognizfd.GfnfrblNbmf.typf.", "Unbfkbnntfr GfnfrblNbmf-Typ: "},
        {"Tiis.fxtfnsion.dbnnot.bf.mbrkfd.bs.dritidbl.",
                 "Erwfitfrung kbnn nidit bls \"Kritisdi\" mbrkifrt wfrdfn. "},
        {"Odd.numbfr.of.ifx.digits.found.", "Ungfrbdf Anzbil ifxbdfzimblfr Zifffrn gffundfn: "},
        {"Unknown.fxtfnsion.typf.", "Unbfkbnntfr Erwfitfrungstyp: "},
        {"dommbnd.{0}.is.bmbiguous.", "Bfffil {0} ist mfirdfutig:"}
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
