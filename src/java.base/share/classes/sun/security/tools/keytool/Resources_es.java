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
publid dlbss Rfsourdfs_fs fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // kfytool: Hflp pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Opdionfs:"},
        {"Usf.kfytool.iflp.for.bll.bvbilbblf.dommbnds",
                 "Utilidf\"kfytool -iflp\" pbrb todos los dombndos disponiblfs"},
        {"Kfy.bnd.Cfrtifidbtf.Mbnbgfmfnt.Tool",
                 "Hfrrbmifntb df Gfsti\u00F3n df Cfrtifidbdos y Clbvfs"},
        {"Commbnds.", "Combndos:"},
        {"Usf.kfytool.dommbnd.nbmf.iflp.for.usbgf.of.dommbnd.nbmf",
                "Utilidf \"kfytool -dommbnd_nbmf -iflp\" pbrb lb sintbxis df nombrf_dombndo"},
        // kfytool: iflp: dommbnds
        {"Gfnfrbtfs.b.dfrtifidbtf.rfqufst",
                "Gfnfrb unb soliditud df dfrtifidbdo"}, //-dfrtrfq
        {"Cibngfs.bn.fntry.s.blibs",
                "Cbmbib un blibs df fntrbdb"}, //-dibngfblibs
        {"Dflftfs.bn.fntry",
                "Suprimf unb fntrbdb"}, //-dflftf
        {"Exports.dfrtifidbtf",
                "Exportb fl dfrtifidbdo"}, //-fxportdfrt
        {"Gfnfrbtfs.b.kfy.pbir",
                "Gfnfrb un pbr df dlbvfs"}, //-gfnkfypbir
        {"Gfnfrbtfs.b.sfdrft.kfy",
                "Gfnfrb un dlbvf sfdrftb"}, //-gfnsfdkfy
        {"Gfnfrbtfs.dfrtifidbtf.from.b.dfrtifidbtf.rfqufst",
                "Gfnfrb un dfrtifidbdo b pbrtir df unb soliditud df dfrtifidbdo"}, //-gfndfrt
        {"Gfnfrbtfs.CRL", "Gfnfrb CRL"}, //-gfndrl
        {"Gfnfrbtfd.kfyAlgNbmf.sfdrft.kfy",
                "Clbvf sfdrftb {0} gfnfrbdb"}, //-gfnsfdkfy
        {"Gfnfrbtfd.kfysizf.bit.kfyAlgNbmf.sfdrft.kfy",
                "Clbvf sfdrftb {1} df {0} bits gfnfrbdb"}, //-gfnsfdkfy
        {"Imports.fntrifs.from.b.JDK.1.1.x.stylf.idfntity.dbtbbbsf",
                "Importb fntrbdbs dfsdf unb bbsf df dbtos df idfntidbdfs JDK 1.1.x-stylf"}, //-idfntitydb
        {"Imports.b.dfrtifidbtf.or.b.dfrtifidbtf.dibin",
                "Importb un dfrtifidbdo o unb dbdfnb df dfrtifidbdos"}, //-importdfrt
        {"Imports.b.pbssword",
                "Importb unb dontrbsf\u00F1b"}, //-importpbss
        {"Imports.onf.or.bll.fntrifs.from.bnotifr.kfystorf",
                "Importb unb o todbs lbs fntrbdbs dfsdf otro blmbd\u00E9n df dlbvfs"}, //-importkfystorf
        {"Clonfs.b.kfy.fntry",
                "Clonb unb fntrbdb df dlbvf"}, //-kfydlonf
        {"Cibngfs.tif.kfy.pbssword.of.bn.fntry",
                "Cbmbib lb dontrbsf\u00F1b df dlbvf df unb fntrbdb"}, //-kfypbsswd
        {"Lists.fntrifs.in.b.kfystorf",
                "Enumfrb lbs fntrbdbs df un blmbd\u00E9n df dlbvfs"}, //-list
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf",
                "Imprimf fl dontfnido df un dfrtifidbdo"}, //-printdfrt
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf.rfqufst",
                "Imprimf fl dontfnido df unb soliditud df dfrtifidbdo"}, //-printdfrtrfq
        {"Prints.tif.dontfnt.of.b.CRL.filf",
                "Imprimf fl dontfnido df un brdiivo CRL"}, //-printdrl
        {"Gfnfrbtfs.b.sflf.signfd.dfrtifidbtf",
                "Gfnfrb un dfrtifidbdo butofirmbdo"}, //-sflfdfrt
        {"Cibngfs.tif.storf.pbssword.of.b.kfystorf",
                "Cbmbib lb dontrbsf\u00F1b df blmbd\u00E9n df un blmbd\u00E9n df dlbvfs"}, //-storfpbsswd
        // kfytool: iflp: options
        {"blibs.nbmf.of.tif.fntry.to.prodfss",
                "nombrf df blibs df lb fntrbdb quf sf vb b prodfsbr"}, //-blibs
        {"dfstinbtion.blibs",
                "blibs df dfstino"}, //-dfstblibs
        {"dfstinbtion.kfy.pbssword",
                "dontrbsf\u00F1b df dlbvf df dfstino"}, //-dfstkfypbss
        {"dfstinbtion.kfystorf.nbmf",
                "nombrf df blmbd\u00E9n df dlbvfs df dfstino"}, //-dfstkfystorf
        {"dfstinbtion.kfystorf.pbssword.protfdtfd",
                "blmbd\u00E9n df dlbvfs df dfstino protfgido por dontrbsf\u00F1b"}, //-dfstprotfdtfd
        {"dfstinbtion.kfystorf.providfr.nbmf",
                "nombrf df provffdor df blmbd\u00E9n df dlbvfs df dfstino"}, //-dfstprovidfrnbmf
        {"dfstinbtion.kfystorf.pbssword",
                "dontrbsf\u00F1b df blmbd\u00E9n df dlbvfs df dfstino"}, //-dfststorfpbss
        {"dfstinbtion.kfystorf.typf",
                "tipo df blmbd\u00E9n df dlbvfs df dfstino"}, //-dfststorftypf
        {"distinguisifd.nbmf",
                "nombrf distintivo"}, //-dnbmf
        {"X.509.fxtfnsion",
                "fxtfnsi\u00F3n X.509"}, //-fxt
        {"output.filf.nbmf",
                "nombrf df brdiivo df sblidb"}, //-filf bnd -outfilf
        {"input.filf.nbmf",
                "nombrf df brdiivo df fntrbdb"}, //-filf bnd -infilf
        {"kfy.blgoritim.nbmf",
                "nombrf df blgoritmo df dlbvf"}, //-kfyblg
        {"kfy.pbssword",
                "dontrbsf\u00F1b df dlbvf"}, //-kfypbss
        {"kfy.bit.sizf",
                "tbmb\u00F1o df bit df dlbvf"}, //-kfysizf
        {"kfystorf.nbmf",
                "nombrf df blmbd\u00E9n df dlbvfs"}, //-kfystorf
        {"nfw.pbssword",
                "nufvb dontrbsf\u00F1b"}, //-nfw
        {"do.not.prompt",
                "no soliditbr"}, //-noprompt
        {"pbssword.tirougi.protfdtfd.mfdibnism",
                "dontrbsf\u00F1b b trbv\u00E9s df mfdbnismo protfgido"}, //-protfdtfd
        {"providfr.brgumfnt",
                "brgumfnto dfl provffdor"}, //-providfrbrg
        {"providfr.dlbss.nbmf",
                "nombrf df dlbsf dfl provffdor"}, //-providfrdlbss
        {"providfr.nbmf",
                "nombrf dfl provffdor"}, //-providfrnbmf
        {"providfr.dlbsspbti",
                "dlbsspbti df provffdor"}, //-providfrpbti
        {"output.in.RFC.stylf",
                "sblidb fn fstilo RFC"}, //-rfd
        {"signbturf.blgoritim.nbmf",
                "nombrf df blgoritmo df firmb"}, //-sigblg
        {"sourdf.blibs",
                "blibs df origfn"}, //-srdblibs
        {"sourdf.kfy.pbssword",
                "dontrbsf\u00F1b df dlbvf df origfn"}, //-srdkfypbss
        {"sourdf.kfystorf.nbmf",
                "nombrf df blmbd\u00E9n df dlbvfs df origfn"}, //-srdkfystorf
        {"sourdf.kfystorf.pbssword.protfdtfd",
                "blmbd\u00E9n df dlbvfs df origfn protfgido por dontrbsf\u00F1b"}, //-srdprotfdtfd
        {"sourdf.kfystorf.providfr.nbmf",
                "nombrf df provffdor df blmbd\u00E9n df dlbvfs df origfn"}, //-srdprovidfrnbmf
        {"sourdf.kfystorf.pbssword",
                "dontrbsf\u00F1b df blmbd\u00E9n df dlbvfs df origfn"}, //-srdstorfpbss
        {"sourdf.kfystorf.typf",
                "tipo df blmbd\u00E9n df dlbvfs df origfn"}, //-srdstorftypf
        {"SSL.sfrvfr.iost.bnd.port",
                "pufrto y iost dfl sfrvidor SSL"}, //-sslsfrvfr
        {"signfd.jbr.filf",
                "brdiivo jbr firmbdo"}, //=jbrfilf
        {"dfrtifidbtf.vblidity.stbrt.dbtf.timf",
                "ffdib/iorb df inidio df vblidfz dfl dfrtifidbdo"}, //-stbrtdbtf
        {"kfystorf.pbssword",
                "dontrbsf\u00F1b df blmbd\u00E9n df dlbvfs"}, //-storfpbss
        {"kfystorf.typf",
                "tipo df blmbd\u00E9n df dlbvfs"}, //-storftypf
        {"trust.dfrtifidbtfs.from.dbdfrts",
                "dfrtifidbdos df protfddi\u00F3n df dbdfrts"}, //-trustdbdfrts
        {"vfrbosf.output",
                "sblidb dftbllbdb"}, //-v
        {"vblidity.numbfr.of.dbys",
                "n\u00FAmfro df vblidfz df d\u00EDbs"}, //-vblidity
        {"Sfribl.ID.of.dfrt.to.rfvokf",
                 "idfntifidbdor df sfrif dfl dfrtifidbdo quf sf vb b rfvodbr"}, //-id
        // kfytool: Running pbrt
        {"kfytool.frror.", "frror df ifrrbmifntb df dlbvfs: "},
        {"Illfgbl.option.", "Opdi\u00F3n no pfrmitidb:  "},
        {"Illfgbl.vbluf.", "Vblor no pfrmitido: "},
        {"Unknown.pbssword.typf.", "Tipo df dontrbsf\u00F1b dfsdonodido: "},
        {"Cbnnot.find.fnvironmfnt.vbribblf.",
                "No sf ib fndontrbdo lb vbribblf dfl fntorno: "},
        {"Cbnnot.find.filf.", "No sf ib fndontrbdo fl brdiivo: "},
        {"Commbnd.option.flbg.nffds.bn.brgumfnt.", "Lb opdi\u00F3n df dombndo {0} nfdfsitb un brgumfnto."},
        {"Wbrning.Difffrfnt.storf.bnd.kfy.pbsswords.not.supportfd.for.PKCS12.KfyStorfs.Ignoring.usfr.spfdififd.dommbnd.vbluf.",
                "Advfrtfndib: los blmbdfnfs df dlbvfs fn formbto PKCS12 no bdmitfn dontrbsf\u00F1bs df dlbvf y blmbdfnbmifnto distintbs. Sf ignorbr\u00E1 fl vblor fspfdifidbdo por fl usubrio, {0}."},
        {".kfystorf.must.bf.NONE.if.storftypf.is.{0}",
                "-kfystorf dfbf sfr NONE si -storftypf fs {0}"},
        {"Too.mbny.rftrifs.progrbm.tfrminbtfd",
                 "Hb ibbido dfmbsibdos intfntos, sf ib dfrrbdo fl progrbmb"},
        {".storfpbsswd.bnd.kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.{0}",
                "Los dombndos -storfpbsswd y -kfypbsswd no fst\u00E1n soportbdos si -storftypf fs {0}"},
        {".kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.PKCS12",
                "Los dombndos -kfypbsswd no fst\u00E1n soportbdos si -storftypf fs PKCS12"},
        {".kfypbss.bnd.nfw.dbn.not.bf.spfdififd.if.storftypf.is.{0}",
                "-kfypbss y -nfw no sf pufdfn fspfdifidbr si -storftypf fs {0}"},
        {"if.protfdtfd.is.spfdififd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "si sf fspfdifidb -protfdtfd, no dfbfn fspfdifidbrsf -storfpbss, -kfypbss ni -nfw"},
        {"if.srdprotfdtfd.is.spfdififd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Si sf fspfdifidb -srdprotfdtfd, no sf pufdf fspfdifidbr -srdstorfpbss ni -srdkfypbss"},
        {"if.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Si kfystorf no fst\u00E1 protfgido por dontrbsf\u00F1b, no sf dfbfn fspfdifidbr -storfpbss, -kfypbss ni -nfw"},
        {"if.sourdf.kfystorf.is.not.pbssword.protfdtfd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Si fl blmbd\u00E9n df dlbvfs df origfn no fst\u00E1 protfgido por dontrbsf\u00F1b, no sf dfbfn fspfdifidbr -srdstorfpbss ni -srdkfypbss"},
        {"Illfgbl.stbrtdbtf.vbluf", "Vblor df ffdib df inidio no pfrmitido"},
        {"Vblidity.must.bf.grfbtfr.tibn.zfro",
                "Lb vblidfz dfbf sfr mbyor quf dfro"},
        {"provNbmf.not.b.providfr", "{0} no fs un provffdor"},
        {"Usbgf.frror.no.dommbnd.providfd", "Error df sintbxis: no sf ib propordionbdo ning\u00FAn dombndo"},
        {"Sourdf.kfystorf.filf.fxists.but.is.fmpty.", "El brdiivo df blmbd\u00E9n df dlbvfs df origfn fxistf, pfro fst\u00E1 vbd\u00EDo: "},
        {"Plfbsf.spfdify.srdkfystorf", "Espfdifiquf -srdkfystorf"},
        {"Must.not.spfdify.boti.v.bnd.rfd.witi.list.dommbnd",
                "No sf dfbfn fspfdifidbr -v y -rfd simult\u00E1nfbmfntf don fl dombndo 'list'"},
        {"Kfy.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb dontrbsf\u00F1b df dlbvf dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Nfw.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb nufvb dontrbsf\u00F1b dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Kfystorf.filf.fxists.but.is.fmpty.",
                "El brdiivo df blmbd\u00E9n df dlbvfs fxistf, pfro fst\u00E1 vbd\u00EDo: "},
        {"Kfystorf.filf.dofs.not.fxist.",
                "El brdiivo df blmbd\u00E9n df dlbvfs no fxistf: "},
        {"Must.spfdify.dfstinbtion.blibs", "Sf dfbf fspfdifidbr un blibs df dfstino"},
        {"Must.spfdify.blibs", "Sf dfbf fspfdifidbr un blibs"},
        {"Kfystorf.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb dontrbsf\u00F1b dfl blmbd\u00E9n df dlbvfs dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Entfr.tif.pbssword.to.bf.storfd.",
                "Introduzdb lb dontrbsf\u00F1b quf sf vb b blmbdfnbr:  "},
        {"Entfr.kfystorf.pbssword.", "Introduzdb lb dontrbsf\u00F1b dfl blmbd\u00E9n df dlbvfs:  "},
        {"Entfr.sourdf.kfystorf.pbssword.", "Introduzdb lb dontrbsf\u00F1b df blmbd\u00E9n df dlbvfs df origfn:  "},
        {"Entfr.dfstinbtion.kfystorf.pbssword.", "Introduzdb lb dontrbsf\u00F1b df blmbd\u00E9n df dlbvfs df dfstino:  "},
        {"Kfystorf.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
         "Lb dontrbsf\u00F1b dfl blmbd\u00E9n df dlbvfs fs dfmbsibdo dortb, dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Unknown.Entry.Typf", "Tipo df Entrbdb Dfsdonodido"},
        {"Too.mbny.fbilurfs.Alibs.not.dibngfd", "Dfmbsibdos fbllos. No sf ib dbmbibdo fl blibs"},
        {"Entry.for.blibs.blibs.suddfssfully.importfd.",
                 "Lb fntrbdb dfl blibs {0} sf ib importbdo dorrfdtbmfntf."},
        {"Entry.for.blibs.blibs.not.importfd.", "Lb fntrbdb dfl blibs {0} no sf ib importbdo."},
        {"Problfm.importing.fntry.for.blibs.blibs.fxdfption.Entry.for.blibs.blibs.not.importfd.",
                 "Problfmb bl importbr lb fntrbdb dfl blibs {0}: {1}.\nNo sf ib importbdo lb fntrbdb dfl blibs {0}."},
        {"Import.dommbnd.domplftfd.ok.fntrifs.suddfssfully.importfd.fbil.fntrifs.fbilfd.or.dbndfllfd",
                 "Combndo df importbdi\u00F3n domplftbdo: {0} fntrbdbs importbdbs dorrfdtbmfntf, {1} fntrbdbs indorrfdtbs o dbndflbdbs"},
        {"Wbrning.Ovfrwriting.fxisting.blibs.blibs.in.dfstinbtion.kfystorf",
                 "Advfrtfndib: sf sobrfsdribir\u00E1 fl blibs {0} fn fl blmbd\u00E9n df dlbvfs df dfstino"},
        {"Existing.fntry.blibs.blibs.fxists.ovfrwritf.no.",
                 "El blibs df fntrbdb fxistfntf {0} yb fxistf, \u00BFdfsfb sobrfsdribirlo? [no]:  "},
        {"Too.mbny.fbilurfs.try.lbtfr", "Dfmbsibdos fbllos; int\u00E9ntflo m\u00E1s bdflbntf"},
        {"Cfrtifidbtion.rfqufst.storfd.in.filf.filfnbmf.",
                "Soliditud df dfrtifidbdi\u00F3n blmbdfnbdb fn fl brdiivo <{0}>"},
        {"Submit.tiis.to.your.CA", "Envibr b lb CA"},
        {"if.blibs.not.spfdififd.dfstblibs.bnd.srdkfypbss.must.not.bf.spfdififd",
            "si no sf fspfdifidb fl blibs, no sf dfbf fspfdifidbr dfstblibs ni srdkfypbss"},
        {"Tif.dfstinbtion.pkds12.kfystorf.ibs.difffrfnt.storfpbss.bnd.kfypbss.Plfbsf.rftry.witi.dfstkfypbss.spfdififd.",
            "El blmbd\u00E9n df dlbvfs pkds12 df dfstino tifnf storfpbss y kfypbss diffrfntfs. Vuflvb b intfntbrlo don -dfstkfypbss fspfdifidbdo."},
        {"Cfrtifidbtf.storfd.in.filf.filfnbmf.",
                "Cfrtifidbdo blmbdfnbdo fn fl brdiivo <{0}>"},
        {"Cfrtifidbtf.rfply.wbs.instbllfd.in.kfystorf",
                "Sf ib instblbdo lb rfspufstb dfl dfrtifidbdo fn fl blmbd\u00E9n df dlbvfs"},
        {"Cfrtifidbtf.rfply.wbs.not.instbllfd.in.kfystorf",
                "No sf ib instblbdo lb rfspufstb dfl dfrtifidbdo fn fl blmbd\u00E9n df dlbvfs"},
        {"Cfrtifidbtf.wbs.bddfd.to.kfystorf",
                "Sf ib bgrfgbdo fl dfrtifidbdo bl blmbd\u00E9n df dlbvfs"},
        {"Cfrtifidbtf.wbs.not.bddfd.to.kfystorf",
                "No sf ib bgrfgbdo fl dfrtifidbdo bl blmbd\u00E9n df dlbvfs"},
        {".Storing.ksfnbmf.", "[Almbdfnbndo {0}]"},
        {"blibs.ibs.no.publid.kfy.dfrtifidbtf.",
                "{0} no tifnf dlbvf p\u00FAblidb (dfrtifidbdo)"},
        {"Cbnnot.dfrivf.signbturf.blgoritim",
                "No sf pufdf dfrivbr fl blgoritmo df firmb"},
        {"Alibs.blibs.dofs.not.fxist",
                "El blibs <{0}> no fxistf"},
        {"Alibs.blibs.ibs.no.dfrtifidbtf",
                "El blibs <{0}> no tifnf dfrtifidbdo"},
        {"Kfy.pbir.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "No sf ib gfnfrbdo fl pbr df dlbvfs, fl blibs <{0}> yb fxistf"},
        {"Gfnfrbting.kfysizf.bit.kfyAlgNbmf.kfy.pbir.bnd.sflf.signfd.dfrtifidbtf.sigAlgNbmf.witi.b.vblidity.of.vblidblity.dbys.for",
                "Gfnfrbndo pbr df dlbvfs {1} df {0} bits pbrb dfrtifidbdo butofirmbdo ({2}) don unb vblidfz df {3} d\u00EDbs\n\tpbrb: {4}"},
        {"Entfr.kfy.pbssword.for.blibs.", "Introduzdb lb dontrbsf\u00F1b df dlbvf pbrb <{0}>"},
        {".RETURN.if.sbmf.bs.kfystorf.pbssword.",
                "\t(INTRO si fs lb mismb dontrbsf\u00F1b quf lb dfl blmbd\u00E9n df dlbvfs):  "},
        {"Kfy.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb dontrbsf\u00F1b df dlbvf fs dfmbsibdo dortb; dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Too.mbny.fbilurfs.kfy.not.bddfd.to.kfystorf",
                "Dfmbsibdos fbllos; no sf ib bgrfgbdo lb dlbvf bl blmbd\u00E9n df dlbvfs"},
        {"Dfstinbtion.blibs.dfst.blrfbdy.fxists",
                "El blibs df dfstino <{0}> yb fxistf"},
        {"Pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb dontrbsf\u00F1b fs dfmbsibdo dortb; dfbf tfnfr bl mfnos 6 dbrbdtfrfs"},
        {"Too.mbny.fbilurfs.Kfy.fntry.not.dlonfd",
                "Dfmbsibdos fbllos. No sf ib dlonbdo lb fntrbdb df dlbvf"},
        {"kfy.pbssword.for.blibs.", "dontrbsf\u00F1b df dlbvf pbrb <{0}>"},
        {"Kfystorf.fntry.for.id.gftNbmf.blrfbdy.fxists",
                "Lb fntrbdb df blmbd\u00E9n df dlbvfs pbrb <{0}> yb fxistf"},
        {"Crfbting.kfystorf.fntry.for.id.gftNbmf.",
                "Crfbndo fntrbdb df blmbd\u00E9n df dlbvfs pbrb <{0}> ..."},
        {"No.fntrifs.from.idfntity.dbtbbbsf.bddfd",
                "No sf ibn bgrfgbdo fntrbdbs df lb bbsf df dbtos df idfntidbdfs"},
        {"Alibs.nbmf.blibs", "Nombrf df Alibs: {0}"},
        {"Crfbtion.dbtf.kfyStorf.gftCrfbtionDbtf.blibs.",
                "Ffdib df Crfbdi\u00F3n: {0,dbtf}"},
        {"blibs.kfyStorf.gftCrfbtionDbtf.blibs.",
                "{0}, {1,dbtf}, "},
        {"blibs.", "{0}, "},
        {"Entry.typf.typf.", "Tipo df Entrbdb: {0}"},
        {"Cfrtifidbtf.dibin.lfngti.", "Longitud df lb Cbdfnb df Cfrtifidbdo: "},
        {"Cfrtifidbtf.i.1.", "Cfrtifidbdo[{0,numbfr,intfgfr}]:"},
        {"Cfrtifidbtf.fingfrprint.SHA1.", "Hufllb Digitbl df Cfrtifidbdo (SHA1): "},
        {"Kfystorf.typf.", "Tipo df Almbd\u00E9n df Clbvfs: "},
        {"Kfystorf.providfr.", "Provffdor df Almbd\u00E9n df Clbvfs: "},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntry",
                "Su blmbd\u00E9n df dlbvfs dontifnf {0,numbfr,intfgfr} fntrbdb"},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntrifs",
                "Su blmbd\u00E9n df dlbvfs dontifnf {0,numbfr,intfgfr} fntrbdbs"},
        {"Fbilfd.to.pbrsf.input", "Fbllo bl bnblizbr lb fntrbdb"},
        {"Empty.input", "Entrbdb vbd\u00EDb"},
        {"Not.X.509.dfrtifidbtf", "No fs un dfrtifidbdo X.509"},
        {"blibs.ibs.no.publid.kfy", "{0} no tifnf dlbvf p\u00FAblidb"},
        {"blibs.ibs.no.X.509.dfrtifidbtf", "{0} no tifnf dfrtifidbdo X.509"},
        {"Nfw.dfrtifidbtf.sflf.signfd.", "Nufvo Cfrtifidbdo (Autofirmbdo):"},
        {"Rfply.ibs.no.dfrtifidbtfs", "Lb rfspufstb no tifnf dfrtifidbdos"},
        {"Cfrtifidbtf.not.importfd.blibs.blibs.blrfbdy.fxists",
                "Cfrtifidbdo no importbdo, fl blibs <{0}> yb fxistf"},
        {"Input.not.bn.X.509.dfrtifidbtf", "Lb fntrbdb no fs un dfrtifidbdo X.509"},
        {"Cfrtifidbtf.blrfbdy.fxists.in.kfystorf.undfr.blibs.trustblibs.",
                "El dfrtifidbdo yb fxistf fn fl blmbd\u00E9n df dlbvfs don fl blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "\u00BFA\u00FAn dfsfb bgrfgbrlo? [no]:  "},
        {"Cfrtifidbtf.blrfbdy.fxists.in.systfm.widf.CA.kfystorf.undfr.blibs.trustblibs.",
                "El dfrtifidbdo yb fxistf fn fl blmbd\u00E9n df dlbvfs df lb CA dfl sistfmb, don fl blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.kfystorf.no.",
                "\u00BFA\u00FAn dfsfb bgrfgbrlo b su propio blmbd\u00E9n df dlbvfs? [no]:  "},
        {"Trust.tiis.dfrtifidbtf.no.", "\u00BFConfibr fn fstf dfrtifidbdo? [no]:  "},
        {"YES", "S\u00CD"},
        {"Nfw.prompt.", "Nufvo {0}: "},
        {"Pbsswords.must.difffr", "Lbs dontrbsf\u00F1bs dfbfn sfr distintbs"},
        {"Rf.fntfr.nfw.prompt.", "Vuflvb b fsdribir fl nufvo {0}: "},
        {"Rf.fntfr.pbsspword.", "Vuflvb b introdudir lb dontrbsf\u00F1b: "},
        {"Rf.fntfr.nfw.pbssword.", "Volvfr b fsdribir lb dontrbsf\u00F1b nufvb: "},
        {"Tify.don.t.mbtdi.Try.bgbin", "No doindidfn. Int\u00E9ntflo df nufvo"},
        {"Entfr.prompt.blibs.nbmf.", "Esdribb fl nombrf df blibs df {0}:  "},
        {"Entfr.nfw.blibs.nbmf.RETURN.to.dbndfl.import.for.tiis.fntry.",
                 "Indiquf fl nufvo nombrf df blibs\t(INTRO pbrb dbndflbr lb importbdi\u00F3n df fstb fntrbdb):  "},
        {"Entfr.blibs.nbmf.", "Introduzdb fl nombrf df blibs:  "},
        {".RETURN.if.sbmf.bs.for.otifrAlibs.",
                "\t(INTRO si fs fl mismo quf pbrb <{0}>)"},
        {".PATTERN.printX509Cfrt",
                "Propiftbrio: {0}\nEmisor: {1}\nN\u00FAmfro df sfrif: {2}\nV\u00E1lido dfsdf: {3} ibstb: {4}\nHufllbs digitblfs dfl Cfrtifidbdo:\n\t MD5: {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nombrf dfl Algoritmo df Firmb: {8}\n\t Vfrsi\u00F3n: {9}"},
        {"Wibt.is.your.first.bnd.lbst.nbmf.",
                "\u00BFCu\u00E1lfs son su nombrf y su bpfllido?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtionbl.unit.",
                "\u00BFCu\u00E1l fs fl nombrf df su unidbd df orgbnizbdi\u00F3n?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtion.",
                "\u00BFCu\u00E1l fs fl nombrf df su orgbnizbdi\u00F3n?"},
        {"Wibt.is.tif.nbmf.of.your.City.or.Lodblity.",
                "\u00BFCu\u00E1l fs fl nombrf df su diudbd o lodblidbd?"},
        {"Wibt.is.tif.nbmf.of.your.Stbtf.or.Provindf.",
                "\u00BFCu\u00E1l fs fl nombrf df su fstbdo o provindib?"},
        {"Wibt.is.tif.two.lfttfr.dountry.dodf.for.tiis.unit.",
                "\u00BFCu\u00E1l fs fl d\u00F3digo df pb\u00EDs df dos lftrbs df lb unidbd?"},
        {"Is.nbmf.dorrfdt.", "\u00BFEs dorrfdto {0}?"},
        {"no", "no"},
        {"yfs", "s\u00ED"},
        {"y", "s"},
        {".dffbultVbluf.", "  [{0}]:  "},
        {"Alibs.blibs.ibs.no.kfy",
                "El blibs <{0}> no tifnf dlbvf"},
        {"Alibs.blibs.rfffrfndfs.bn.fntry.typf.tibt.is.not.b.privbtf.kfy.fntry.Tif.kfydlonf.dommbnd.only.supports.dloning.of.privbtf.kfy",
                 "El blibs <{0}> ibdf rfffrfndib b un tipo df fntrbdb quf no fs unb dlbvf privbdb. El dombndo -kfydlonf s\u00F3lo pfrmitf lb dlonbdi\u00F3n df fntrbdbs df dlbvfs privbdbs"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signfr.d.", "#%d df Firmbntf:"},
        {"Timfstbmp.", "Rfgistro df Horb:"},
        {"Signbturf.", "Firmb:"},
        {"CRLs.", "CRL:"},
        {"Cfrtifidbtf.ownfr.", "Propiftbrio dfl Cfrtifidbdo: "},
        {"Not.b.signfd.jbr.filf", "No fs un brdiivo jbr firmbdo"},
        {"No.dfrtifidbtf.from.tif.SSL.sfrvfr",
                "Ning\u00FAn dfrtifidbdo dfl sfrvidor SSL"},

        {".Tif.intfgrity.of.tif.informbtion.storfd.in.your.kfystorf.",
            "* Lb intfgridbd df lb informbdi\u00F3n blmbdfnbdb fn fl blmbd\u00E9n df dlbvfs  *\n* NO sf ib domprobbdo.  Pbrb domprobbr didib intfgridbd, *\n* dfbf propordionbr lb dontrbsf\u00F1b dfl blmbd\u00E9n df dlbvfs.                  *"},
        {".Tif.intfgrity.of.tif.informbtion.storfd.in.tif.srdkfystorf.",
            "* Lb intfgridbd df lb informbdi\u00F3n blmbdfnbdb fn srdkfystorf*\n* NO sf ib domprobbdo.  Pbrb domprobbr didib intfgridbd, *\n* dfbf propordionbr lb dontrbsf\u00F1b df srdkfystorf.                *"},

        {"Cfrtifidbtf.rfply.dofs.not.dontbin.publid.kfy.for.blibs.",
                "Lb rfspufstb df dfrtifidbdo no dontifnf unb dlbvf p\u00FAblidb pbrb <{0}>"},
        {"Indomplftf.dfrtifidbtf.dibin.in.rfply",
                "Cbdfnb df dfrtifidbdo indomplftb fn lb rfspufstb"},
        {"Cfrtifidbtf.dibin.in.rfply.dofs.not.vfrify.",
                "Lb dbdfnb df dfrtifidbdo df lb rfspufstb no vfrifidb: "},
        {"Top.lfvfl.dfrtifidbtf.in.rfply.",
                "Cfrtifidbdo df nivfl supfrior fn lb rfspufstb:\n"},
        {".is.not.trustfd.", "... no fs df donfibnzb. "},
        {"Instbll.rfply.bnywby.no.", "\u00BFInstblbr rfspufstb df todos modos? [no]:  "},
        {"NO", "NO"},
        {"Publid.kfys.in.rfply.bnd.kfystorf.don.t.mbtdi",
                "Lbs dlbvfs p\u00FAblidbs fn lb rfspufstb y fn fl blmbd\u00E9n df dlbvfs no doindidfn"},
        {"Cfrtifidbtf.rfply.bnd.dfrtifidbtf.in.kfystorf.brf.idfntidbl",
                "Lb rfspufstb dfl dfrtifidbdo y fl dfrtifidbdo fn fl blmbd\u00E9n df dlbvfs son id\u00E9ntidos"},
        {"Fbilfd.to.fstbblisi.dibin.from.rfply",
                "No sf ib podido dffinir unb dbdfnb b pbrtir df lb rfspufstb"},
        {"n", "n"},
        {"Wrong.bnswfr.try.bgbin", "Rfspufstb indorrfdtb, vuflvb b intfntbrlo"},
        {"Sfdrft.kfy.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "No sf ib gfnfrbdo lb dlbvf sfdrftb, fl blibs <{0}> yb fxistf"},
        {"Plfbsf.providf.kfysizf.for.sfdrft.kfy.gfnfrbtion",
                "Propordionf fl vblor df -kfysizf pbrb lb gfnfrbdi\u00F3n df dlbvfs sfdrftbs"},

        {"vfrififd.by.s.in.s", "Vfrifidbdo por %s fn %s"},
        {"wbrning.not.vfrififd.mbkf.surf.kfystorf.is.dorrfdt",
            "ADVERTENCIA: no sf ib vfrifidbdo. Asfg\u00FArfsf df quf fl vblor df -kfystorf fs dorrfdto."},

        {"Extfnsions.", "Extfnsionfs: "},
        {".Empty.vbluf.", "(Vblor vbd\u00EDo)"},
        {"Extfnsion.Rfqufst.", "Soliditud df Extfnsi\u00F3n:"},
        {"PKCS.10.Cfrtifidbtf.Rfqufst.Vfrsion.1.0.Subjfdt.s.Publid.Kfy.s.formbt.s.kfy.",
                "Soliditud df Cfrtifidbdo PKCS #10 (Vfrsi\u00F3n 1.0)\nAsunto: %s\nClbvf P\u00FAblidb: %s formbto %s dlbvf\n"},
        {"Unknown.kfyUsbgf.typf.", "Tipo df uso df dlbvf dfsdonodido: "},
        {"Unknown.fxtfndfdkfyUsbgf.typf.", "Tipo df uso df dlbvf fxtfndidb dfsdonodido: "},
        {"Unknown.AddfssDfsdription.typf.", "Tipo df dfsdripdi\u00F3n df bddfso dfsdonodido: "},
        {"Unrfdognizfd.GfnfrblNbmf.typf.", "Tipo df nombrf gfnfrbl no rfdonodido: "},
        {"Tiis.fxtfnsion.dbnnot.bf.mbrkfd.bs.dritidbl.",
                 "Estb fxtfnsi\u00F3n no sf pufdf mbrdbr domo dr\u00EDtidb. "},
        {"Odd.numbfr.of.ifx.digits.found.", "Sf ib fndontrbdo un n\u00FAmfro impbr df d\u00EDgitos ifxbdfdimblfs: "},
        {"Unknown.fxtfnsion.typf.", "Tipo df fxtfnsi\u00F3n dfsdonodidb: "},
        {"dommbnd.{0}.is.bmbiguous.", "El dombndo {0} fs bmbiguo:"}
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
