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
publid dlbss Rfsourdfs_fr fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // kfytool: Hflp pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Options :"},
        {"Usf.kfytool.iflp.for.bll.bvbilbblf.dommbnds",
                 "Utilisfr \"kfytool -iflp\" pour toutfs lfs dommbndfs disponiblfs"},
        {"Kfy.bnd.Cfrtifidbtf.Mbnbgfmfnt.Tool",
                 "Outil df gfstion df dfrtifidbts ft df dl\u00E9s"},
        {"Commbnds.", "Commbndfs :"},
        {"Usf.kfytool.dommbnd.nbmf.iflp.for.usbgf.of.dommbnd.nbmf",
                "Utilisfr \"kfytool -dommbnd_nbmf -iflp\" pour lb syntbxf df dommbnd_nbmf"},
        // kfytool: iflp: dommbnds
        {"Gfnfrbtfs.b.dfrtifidbtf.rfqufst",
                "G\u00E9n\u00E8rf unf dfmbndf df dfrtifidbt"}, //-dfrtrfq
        {"Cibngfs.bn.fntry.s.blibs",
                "Modifif l'blibs d'unf fntr\u00E9f"}, //-dibngfblibs
        {"Dflftfs.bn.fntry",
                "Supprimf unf fntr\u00E9f"}, //-dflftf
        {"Exports.dfrtifidbtf",
                "Exportf lf dfrtifidbt"}, //-fxportdfrt
        {"Gfnfrbtfs.b.kfy.pbir",
                "G\u00E9n\u00E8rf unf pbirf df dl\u00E9s"}, //-gfnkfypbir
        {"Gfnfrbtfs.b.sfdrft.kfy",
                "G\u00E9n\u00E8rf unf dl\u00E9 sfdr\u00E8tf"}, //-gfnsfdkfy
        {"Gfnfrbtfs.dfrtifidbtf.from.b.dfrtifidbtf.rfqufst",
                "G\u00E9n\u00E8rf lf dfrtifidbt \u00E0 pbrtir d'unf dfmbndf df dfrtifidbt"}, //-gfndfrt
        {"Gfnfrbtfs.CRL", "G\u00E9n\u00E8rf lb listf dfs dfrtifidbts r\u00E9voqu\u00E9s (CRL)"}, //-gfndrl
        {"Gfnfrbtfd.kfyAlgNbmf.sfdrft.kfy",
                "Cl\u00E9 sfdr\u00E8tf {0} g\u00E9n\u00E9r\u00E9f"}, //-gfnsfdkfy
        {"Gfnfrbtfd.kfysizf.bit.kfyAlgNbmf.sfdrft.kfy",
                "Cl\u00E9 sfdr\u00E8tf {0} bits {1} g\u00E9n\u00E9r\u00E9f"}, //-gfnsfdkfy
        {"Imports.fntrifs.from.b.JDK.1.1.x.stylf.idfntity.dbtbbbsf",
                "Importf lfs fntr\u00E9fs \u00E0 pbrtir d'unf bbsf df donn\u00E9fs d'idfntit\u00E9s df typf JDK 1.1.x"}, //-idfntitydb
        {"Imports.b.dfrtifidbtf.or.b.dfrtifidbtf.dibin",
                "Importf un dfrtifidbt ou unf dib\u00EEnf df dfrtifidbt"}, //-importdfrt
        {"Imports.b.pbssword",
                "Importf un mot df pbssf"}, //-importpbss
        {"Imports.onf.or.bll.fntrifs.from.bnotifr.kfystorf",
                "Importf unf fntr\u00E9f ou lb totblit\u00E9 dfs fntr\u00E9fs dfpuis un butrf fidiifr df dl\u00E9s"}, //-importkfystorf
        {"Clonfs.b.kfy.fntry",
                "Clonf unf fntr\u00E9f df dl\u00E9"}, //-kfydlonf
        {"Cibngfs.tif.kfy.pbssword.of.bn.fntry",
                "Modifif lf mot df pbssf df dl\u00E9 d'unf fntr\u00E9f"}, //-kfypbsswd
        {"Lists.fntrifs.in.b.kfystorf",
                "R\u00E9pfrtorif lfs fntr\u00E9fs d'un fidiifr df dl\u00E9s"}, //-list
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf",
                "Imprimf lf dontfnu d'un dfrtifidbt"}, //-printdfrt
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf.rfqufst",
                "Imprimf lf dontfnu d'unf dfmbndf df dfrtifidbt"}, //-printdfrtrfq
        {"Prints.tif.dontfnt.of.b.CRL.filf",
                "Imprimf lf dontfnu d'un fidiifr df listf dfs dfrtifidbts r\u00E9voqu\u00E9s (CRL)"}, //-printdrl
        {"Gfnfrbtfs.b.sflf.signfd.dfrtifidbtf",
                "G\u00E9n\u00E8rf un dfrtifidbt buto-sign\u00E9"}, //-sflfdfrt
        {"Cibngfs.tif.storf.pbssword.of.b.kfystorf",
                "Modifif lf mot df pbssf df bbnquf d'un fidiifr df dl\u00E9s"}, //-storfpbsswd
        // kfytool: iflp: options
        {"blibs.nbmf.of.tif.fntry.to.prodfss",
                "nom d'blibs df l'fntr\u00E9f \u00E0 trbitfr"}, //-blibs
        {"dfstinbtion.blibs",
                "blibs df dfstinbtion"}, //-dfstblibs
        {"dfstinbtion.kfy.pbssword",
                "mot df pbssf df lb dl\u00E9 df dfstinbtion"}, //-dfstkfypbss
        {"dfstinbtion.kfystorf.nbmf",
                "nom du fidiifr df dl\u00E9s df dfstinbtion"}, //-dfstkfystorf
        {"dfstinbtion.kfystorf.pbssword.protfdtfd",
                "mot df pbssf du fidiifr df dl\u00E9s df dfstinbtion prot\u00E9g\u00E9"}, //-dfstprotfdtfd
        {"dfstinbtion.kfystorf.providfr.nbmf",
                "nom du fournissfur du fidiifr df dl\u00E9s df dfstinbtion"}, //-dfstprovidfrnbmf
        {"dfstinbtion.kfystorf.pbssword",
                "mot df pbssf du fidiifr df dl\u00E9s df dfstinbtion"}, //-dfststorfpbss
        {"dfstinbtion.kfystorf.typf",
                "typf du fidiifr df dl\u00E9s df dfstinbtion"}, //-dfststorftypf
        {"distinguisifd.nbmf",
                "nom distindtif"}, //-dnbmf
        {"X.509.fxtfnsion",
                "fxtfnsion X.509"}, //-fxt
        {"output.filf.nbmf",
                "nom du fidiifr df sortif"}, //-filf bnd -outfilf
        {"input.filf.nbmf",
                "nom du fidiifr d'fntr\u00E9f"}, //-filf bnd -infilf
        {"kfy.blgoritim.nbmf",
                "nom df l'blgoritimf df dl\u00E9"}, //-kfyblg
        {"kfy.pbssword",
                "mot df pbssf df lb dl\u00E9"}, //-kfypbss
        {"kfy.bit.sizf",
                "tbillf fn bits df lb dl\u00E9"}, //-kfysizf
        {"kfystorf.nbmf",
                "nom du fidiifr df dl\u00E9s"}, //-kfystorf
        {"nfw.pbssword",
                "nouvfbu mot df pbssf"}, //-nfw
        {"do.not.prompt",
                "nf pbs invitfr"}, //-noprompt
        {"pbssword.tirougi.protfdtfd.mfdibnism",
                "mot df pbssf vib m\u00E9dbnismf prot\u00E9g\u00E9"}, //-protfdtfd
        {"providfr.brgumfnt",
                "brgumfnt du fournissfur"}, //-providfrbrg
        {"providfr.dlbss.nbmf",
                "nom df lb dlbssf df fournissfur"}, //-providfrdlbss
        {"providfr.nbmf",
                "nom du fournissfur"}, //-providfrnbmf
        {"providfr.dlbsspbti",
                "vbribblf d'fnvironnfmfnt CLASSPATH du fournissfur"}, //-providfrpbti
        {"output.in.RFC.stylf",
                "sortif bu stylf RFC"}, //-rfd
        {"signbturf.blgoritim.nbmf",
                "nom df l'blgoritimf df signbturf"}, //-sigblg
        {"sourdf.blibs",
                "blibs sourdf"}, //-srdblibs
        {"sourdf.kfy.pbssword",
                "mot df pbssf df lb dl\u00E9 sourdf"}, //-srdkfypbss
        {"sourdf.kfystorf.nbmf",
                "nom du fidiifr df dl\u00E9s sourdf"}, //-srdkfystorf
        {"sourdf.kfystorf.pbssword.protfdtfd",
                "mot df pbssf du fidiifr df dl\u00E9s sourdf prot\u00E9g\u00E9"}, //-srdprotfdtfd
        {"sourdf.kfystorf.providfr.nbmf",
                "nom du fournissfur du fidiifr df dl\u00E9s sourdf"}, //-srdprovidfrnbmf
        {"sourdf.kfystorf.pbssword",
                "mot df pbssf du fidiifr df dl\u00E9s sourdf"}, //-srdstorfpbss
        {"sourdf.kfystorf.typf",
                "typf du fidiifr df dl\u00E9s sourdf"}, //-srdstorftypf
        {"SSL.sfrvfr.iost.bnd.port",
                "Port ft i\u00F4tf du sfrvfur SSL"}, //-sslsfrvfr
        {"signfd.jbr.filf",
                "fidiifr JAR sign\u00E9"}, //=jbrfilf
        {"dfrtifidbtf.vblidity.stbrt.dbtf.timf",
                "dbtf/ifurf df d\u00E9but df vblidit\u00E9 du dfrtifidbt"}, //-stbrtdbtf
        {"kfystorf.pbssword",
                "mot df pbssf du fidiifr df dl\u00E9s"}, //-storfpbss
        {"kfystorf.typf",
                "typf du fidiifr df dl\u00E9s"}, //-storftypf
        {"trust.dfrtifidbtfs.from.dbdfrts",
                "dfrtifidbts s\u00E9duris\u00E9s issus df dfrtifidbts CA"}, //-trustdbdfrts
        {"vfrbosf.output",
                "sortif fn modf vfrbosf"}, //-v
        {"vblidity.numbfr.of.dbys",
                "nombrf df jours df vblidit\u00E9"}, //-vblidity
        {"Sfribl.ID.of.dfrt.to.rfvokf",
                 "ID df s\u00E9rif du dfrtifidbt \u00E0 r\u00E9voqufr"}, //-id
        // kfytool: Running pbrt
        {"kfytool.frror.", "frrfur kfytool : "},
        {"Illfgbl.option.", "Option non bdmisf :  "},
        {"Illfgbl.vbluf.", "Vblfur non bdmisf : "},
        {"Unknown.pbssword.typf.", "Typf df mot df pbssf indonnu : "},
        {"Cbnnot.find.fnvironmfnt.vbribblf.",
                "Vbribblf d'fnvironnfmfnt introuvbblf : "},
        {"Cbnnot.find.filf.", "Fidiifr introuvbblf : "},
        {"Commbnd.option.flbg.nffds.bn.brgumfnt.", "L''option df dommbndf {0} rfquifrt un brgumfnt."},
        {"Wbrning.Difffrfnt.storf.bnd.kfy.pbsswords.not.supportfd.for.PKCS12.KfyStorfs.Ignoring.usfr.spfdififd.dommbnd.vbluf.",
                "Avfrtissfmfnt\u00A0: lfs mots df pbssf df dl\u00E9 ft df bbnquf distindts nf sont pbs pris fn dibrgf pour lfs fidiifrs df dl\u00E9s d''bdd\u00E8s PKCS12. Lb vblfur {0} sp\u00E9difi\u00E9f pbr l''utilisbtfur fst ignor\u00E9f."},
        {".kfystorf.must.bf.NONE.if.storftypf.is.{0}",
                "-kfystorf doit \u00EAtrf d\u00E9fini sur NONE si -storftypf fst {0}"},
        {"Too.mbny.rftrifs.progrbm.tfrminbtfd",
                 "Trop df tfntbtivfs, fin du progrbmmf"},
        {".storfpbsswd.bnd.kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.{0}",
                "Lfs dommbndfs -storfpbsswd ft -kfypbsswd nf sont pbs prisfs fn dibrgf si -storftypf fst d\u00E9fini sur {0}"},
        {".kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.PKCS12",
                "Lfs dommbndfs -kfypbsswd nf sont pbs prisfs fn dibrgf si -storftypf fst d\u00E9fini sur PKCS12"},
        {".kfypbss.bnd.nfw.dbn.not.bf.spfdififd.if.storftypf.is.{0}",
                "Lfs dommbndfs -kfypbss ft -nfw nf pfuvfnt pbs \u00EAtrf sp\u00E9difi\u00E9fs si -storftypf fst d\u00E9fini sur {0}"},
        {"if.protfdtfd.is.spfdififd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "si -protfdtfd fst sp\u00E9difi\u00E9, -storfpbss, -kfypbss ft -nfw nf doivfnt pbs \u00EAtrf indiqu\u00E9s"},
        {"if.srdprotfdtfd.is.spfdififd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Si -srdprotfdtfd fst indiqu\u00E9, lfs dommbndfs -srdstorfpbss ft -srdkfypbss nf doivfnt pbs \u00EAtrf sp\u00E9difi\u00E9fs"},
        {"if.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Si lf fidiifr df dl\u00E9s n'fst pbs prot\u00E9g\u00E9 pbr un mot df pbssf, lfs dommbndfs -storfpbss, -kfypbss ft -nfw nf doivfnt pbs \u00EAtrf sp\u00E9difi\u00E9fs"},
        {"if.sourdf.kfystorf.is.not.pbssword.protfdtfd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Si lf fidiifr df dl\u00E9s sourdf n'fst pbs prot\u00E9g\u00E9 pbr un mot df pbssf, lfs dommbndfs -srdstorfpbss ft -srdkfypbss nf doivfnt pbs \u00EAtrf sp\u00E9difi\u00E9fs"},
        {"Illfgbl.stbrtdbtf.vbluf", "Vblfur df dbtf df d\u00E9but non bdmisf"},
        {"Vblidity.must.bf.grfbtfr.tibn.zfro",
                "Lb vblidit\u00E9 doit \u00EAtrf sup\u00E9rifurf \u00E0 z\u00E9ro"},
        {"provNbmf.not.b.providfr", "{0} n''fst pbs un fournissfur"},
        {"Usbgf.frror.no.dommbnd.providfd", "Errfur df syntbxf\u00A0: budunf dommbndf fournif"},
        {"Sourdf.kfystorf.filf.fxists.but.is.fmpty.", "Lf fidiifr df dl\u00E9s sourdf fxistf mbis il fst vidf : "},
        {"Plfbsf.spfdify.srdkfystorf", "Indiqufz -srdkfystorf"},
        {"Must.not.spfdify.boti.v.bnd.rfd.witi.list.dommbnd",
                "-v ft -rfd nf doivfnt pbs \u00EAtrf sp\u00E9difi\u00E9s bvfd lb dommbndf 'list'"},
        {"Kfy.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Un mot df pbssf df dl\u00E9 doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Nfw.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lf nouvfbu mot df pbssf doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Kfystorf.filf.fxists.but.is.fmpty.",
                "Fidiifr df dl\u00E9s fxistbnt mbis vidf : "},
        {"Kfystorf.filf.dofs.not.fxist.",
                "Lf fidiifr df dl\u00E9s n'fxistf pbs : "},
        {"Must.spfdify.dfstinbtion.blibs", "L'blibs df dfstinbtion doit \u00EAtrf sp\u00E9difi\u00E9"},
        {"Must.spfdify.blibs", "L'blibs doit \u00EAtrf sp\u00E9difi\u00E9"},
        {"Kfystorf.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Un mot df pbssf df fidiifr df dl\u00E9s doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Entfr.tif.pbssword.to.bf.storfd.",
                "Sbisissfz lf mot df pbssf \u00E0 stodkfr :  "},
        {"Entfr.kfystorf.pbssword.", "Entrfz lf mot df pbssf du fidiifr df dl\u00E9s :  "},
        {"Entfr.sourdf.kfystorf.pbssword.", "Entrfz lf mot df pbssf du fidiifr df dl\u00E9s sourdf\u00A0:  "},
        {"Entfr.dfstinbtion.kfystorf.pbssword.", "Entrfz lf mot df pbssf du fidiifr df dl\u00E9s df dfstinbtion\u00A0:  "},
        {"Kfystorf.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
         "Lf mot df pbssf du fidiifr df dl\u00E9s fst trop dourt : il doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Unknown.Entry.Typf", "Typf d'fntr\u00E9f indonnu"},
        {"Too.mbny.fbilurfs.Alibs.not.dibngfd", "Trop d'frrfurs. Alibs non modifi\u00E9"},
        {"Entry.for.blibs.blibs.suddfssfully.importfd.",
                 "L''fntr\u00E9f df l''blibs {0} b \u00E9t\u00E9 import\u00E9f."},
        {"Entry.for.blibs.blibs.not.importfd.", "L''fntr\u00E9f df l''blibs {0} n''b pbs \u00E9t\u00E9 import\u00E9f."},
        {"Problfm.importing.fntry.for.blibs.blibs.fxdfption.Entry.for.blibs.blibs.not.importfd.",
                 "Probl\u00E8mf lors df l''import df l''fntr\u00E9f df l''blibs {0}\u00A0: {1}.\nL''fntr\u00E9f df l''blibs {0} n''b pbs \u00E9t\u00E9 import\u00E9f."},
        {"Import.dommbnd.domplftfd.ok.fntrifs.suddfssfully.importfd.fbil.fntrifs.fbilfd.or.dbndfllfd",
                 "Commbndf d''import fx\u00E9dut\u00E9f\u00A0: {0} fntr\u00E9fs import\u00E9fs, \u00E9difd ou bnnulbtion df {1} fntr\u00E9fs"},
        {"Wbrning.Ovfrwriting.fxisting.blibs.blibs.in.dfstinbtion.kfystorf",
                 "Avfrtissfmfnt\u00A0: l''blibs {0} fxistbnt sfrb rfmplbd\u00E9 dbns lf fidiifr df dl\u00E9s d''bdd\u00E8s df dfstinbtion"},
        {"Existing.fntry.blibs.blibs.fxists.ovfrwritf.no.",
                 "L''blibs d''fntr\u00E9f {0} fxistf d\u00E9j\u00E0. Voulfz-vous lf rfmplbdfr ? [non]\u00A0:  "},
        {"Too.mbny.fbilurfs.try.lbtfr", "Trop d'frrfurs. R\u00E9fssbyfz plus tbrd"},
        {"Cfrtifidbtion.rfqufst.storfd.in.filf.filfnbmf.",
                "Dfmbndf df dfrtifidbtion stodk\u00E9f dbns lf fidiifr <{0}>"},
        {"Submit.tiis.to.your.CA", "Soumfttrf \u00E0 votrf CA"},
        {"if.blibs.not.spfdififd.dfstblibs.bnd.srdkfypbss.must.not.bf.spfdififd",
            "si l'blibs n'fst pbs sp\u00E9difi\u00E9, dfstblibs ft srdkfypbss nf doivfnt pbs \u00EAtrf sp\u00E9difi\u00E9s"},
        {"Tif.dfstinbtion.pkds12.kfystorf.ibs.difffrfnt.storfpbss.bnd.kfypbss.Plfbsf.rftry.witi.dfstkfypbss.spfdififd.",
            "Lf fidiifr df dl\u00E9s pkds12 df dfstinbtion dontifnt un mot df pbssf df fidiifr df dl\u00E9s ft un mot df pbssf df dl\u00E9 diff\u00E9rfnts. R\u00E9fssbyfz fn sp\u00E9difibnt -dfstkfypbss."},
        {"Cfrtifidbtf.storfd.in.filf.filfnbmf.",
                "Cfrtifidbt stodk\u00E9 dbns lf fidiifr <{0}>"},
        {"Cfrtifidbtf.rfply.wbs.instbllfd.in.kfystorf",
                "R\u00E9ponsf df dfrtifidbt instbll\u00E9f dbns lf fidiifr df dl\u00E9s"},
        {"Cfrtifidbtf.rfply.wbs.not.instbllfd.in.kfystorf",
                "R\u00E9ponsf df dfrtifidbt non instbll\u00E9f dbns lf fidiifr df dl\u00E9s"},
        {"Cfrtifidbtf.wbs.bddfd.to.kfystorf",
                "Cfrtifidbt bjout\u00E9 bu fidiifr df dl\u00E9s"},
        {"Cfrtifidbtf.wbs.not.bddfd.to.kfystorf",
                "Cfrtifidbt non bjout\u00E9 bu fidiifr df dl\u00E9s"},
        {".Storing.ksfnbmf.", "[Stodkbgf df {0}]"},
        {"blibs.ibs.no.publid.kfy.dfrtifidbtf.",
                "{0} nf poss\u00E8df pbs df dl\u00E9 publiquf (dfrtifidbt)"},
        {"Cbnnot.dfrivf.signbturf.blgoritim",
                "Impossiblf df d\u00E9duirf l'blgoritimf df signbturf"},
        {"Alibs.blibs.dofs.not.fxist",
                "L''blibs <{0}> n''fxistf pbs"},
        {"Alibs.blibs.ibs.no.dfrtifidbtf",
                "L''blibs <{0}> nf poss\u00E8df pbs df dfrtifidbt"},
        {"Kfy.pbir.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Pbirf df dl\u00E9s non g\u00E9n\u00E9r\u00E9f, l''blibs <{0}> fxistf d\u00E9j\u00E0"},
        {"Gfnfrbting.kfysizf.bit.kfyAlgNbmf.kfy.pbir.bnd.sflf.signfd.dfrtifidbtf.sigAlgNbmf.witi.b.vblidity.of.vblidblity.dbys.for",
                "G\u00E9n\u00E9rbtion d''unf pbirf df dl\u00E9s {1} df {0} bits ft d''un dfrtifidbt buto-sign\u00E9 ({2}) d''unf vblidit\u00E9 df {3} jours\n\tpour : {4}"},
        {"Entfr.kfy.pbssword.for.blibs.", "Entrfz lf mot df pbssf df lb dl\u00E9 pour <{0}>"},
        {".RETURN.if.sbmf.bs.kfystorf.pbssword.",
                "\t(bppuyfz sur Entr\u00E9f s'il s'bgit du mot df pbssf du fidiifr df dl\u00E9s) :  "},
        {"Kfy.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lf mot df pbssf df lb dl\u00E9 fst trop dourt : il doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Too.mbny.fbilurfs.kfy.not.bddfd.to.kfystorf",
                "Trop d'frrfurs. Cl\u00E9 non bjout\u00E9f bu fidiifr df dl\u00E9s"},
        {"Dfstinbtion.blibs.dfst.blrfbdy.fxists",
                "L''blibs df lb dfstinbtion <{0}> fxistf d\u00E9j\u00E0"},
        {"Pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lf mot df pbssf fst trop dourt : il doit domportfr bu moins 6 dbrbdt\u00E8rfs"},
        {"Too.mbny.fbilurfs.Kfy.fntry.not.dlonfd",
                "Trop d'frrfurs. Entr\u00E9f df dl\u00E9 non dlon\u00E9f"},
        {"kfy.pbssword.for.blibs.", "mot df pbssf df dl\u00E9 pour <{0}>"},
        {"Kfystorf.fntry.for.id.gftNbmf.blrfbdy.fxists",
                "L''fntr\u00E9f df fidiifr df dl\u00E9s d''bdd\u00E8s pour <{0}> fxistf d\u00E9j\u00E0"},
        {"Crfbting.kfystorf.fntry.for.id.gftNbmf.",
                "Cr\u00E9btion d''unf fntr\u00E9f df fidiifr df dl\u00E9s d''bdd\u00E8s pour <{0}>..."},
        {"No.fntrifs.from.idfntity.dbtbbbsf.bddfd",
                "Audunf fntr\u00E9f bjout\u00E9f \u00E0 pbrtir df lb bbsf df donn\u00E9fs d'idfntit\u00E9s"},
        {"Alibs.nbmf.blibs", "Nom d''blibs : {0}"},
        {"Crfbtion.dbtf.kfyStorf.gftCrfbtionDbtf.blibs.",
                "Dbtf df dr\u00E9btion : {0,dbtf}"},
        {"blibs.kfyStorf.gftCrfbtionDbtf.blibs.",
                "{0}, {1,dbtf}, "},
        {"blibs.", "{0}, "},
        {"Entry.typf.typf.", "Typf d''fntr\u00E9f\u00A0: {0}"},
        {"Cfrtifidbtf.dibin.lfngti.", "Longufur df dib\u00EEnf du dfrtifidbt : "},
        {"Cfrtifidbtf.i.1.", "Cfrtifidbt[{0,numbfr,intfgfr}]:"},
        {"Cfrtifidbtf.fingfrprint.SHA1.", "Emprfintf du dfrtifidbt (SHA1) : "},
        {"Kfystorf.typf.", "Typf df fidiifr df dl\u00E9s : "},
        {"Kfystorf.providfr.", "Fournissfur df fidiifr df dl\u00E9s : "},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntry",
                "Votrf fidiifr df dl\u00E9s d''bdd\u00E8s dontifnt {0,numbfr,intfgfr} fntr\u00E9f"},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntrifs",
                "Votrf fidiifr df dl\u00E9s d''bdd\u00E8s dontifnt {0,numbfr,intfgfr} fntr\u00E9fs"},
        {"Fbilfd.to.pbrsf.input", "L'bnblysf df l'fntr\u00E9f b \u00E9diou\u00E9"},
        {"Empty.input", "Entr\u00E9f vidf"},
        {"Not.X.509.dfrtifidbtf", "Pbs un dfrtifidbt X.509"},
        {"blibs.ibs.no.publid.kfy", "{0} nf poss\u00E8df pbs df dl\u00E9 publiquf"},
        {"blibs.ibs.no.X.509.dfrtifidbtf", "{0} nf poss\u00E8df pbs df dfrtifidbt X.509"},
        {"Nfw.dfrtifidbtf.sflf.signfd.", "Nouvfbu dfrtifidbt (buto-sign\u00E9) :"},
        {"Rfply.ibs.no.dfrtifidbtfs", "Lb r\u00E9ponsf n'b pbs df dfrtifidbt"},
        {"Cfrtifidbtf.not.importfd.blibs.blibs.blrfbdy.fxists",
                "Cfrtifidbt non import\u00E9, l''blibs <{0}> fxistf d\u00E9j\u00E0"},
        {"Input.not.bn.X.509.dfrtifidbtf", "L'fntr\u00E9f n'fst pbs un dfrtifidbt X.509"},
        {"Cfrtifidbtf.blrfbdy.fxists.in.kfystorf.undfr.blibs.trustblibs.",
                "Lf dfrtifidbt fxistf d\u00E9j\u00E0 dbns lf fidiifr df dl\u00E9s d''bdd\u00E8s sous l''blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Voulfz-vous toujours l'bjoutfr ? [non] :  "},
        {"Cfrtifidbtf.blrfbdy.fxists.in.systfm.widf.CA.kfystorf.undfr.blibs.trustblibs.",
                "Lf dfrtifidbt fxistf d\u00E9j\u00E0 dbns lf fidiifr df dl\u00E9s d''bdd\u00E8s CA syst\u00E8mf sous l''blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.kfystorf.no.",
                "Voulfz-vous toujours l'bjoutfr \u00E0 votrf fidiifr df dl\u00E9s ? [non] :  "},
        {"Trust.tiis.dfrtifidbtf.no.", "Fbirf donfibndf \u00E0 df dfrtifidbt ? [non] :  "},
        {"YES", "OUI"},
        {"Nfw.prompt.", "Nouvfbu {0} : "},
        {"Pbsswords.must.difffr", "Lfs mots df pbssf doivfnt diff\u00E9rfr"},
        {"Rf.fntfr.nfw.prompt.", "Indiqufz fndorf lf nouvfbu {0} : "},
        {"Rf.fntfr.pbsspword.", "R\u00E9p\u00E9tfz lf mot df pbssf : "},
        {"Rf.fntfr.nfw.pbssword.", "Rfssbisissfz lf nouvfbu mot df pbssf : "},
        {"Tify.don.t.mbtdi.Try.bgbin", "Ils sont diff\u00E9rfnts. R\u00E9fssbyfz."},
        {"Entfr.prompt.blibs.nbmf.", "Indiqufz lf nom d''blibs {0} :  "},
        {"Entfr.nfw.blibs.nbmf.RETURN.to.dbndfl.import.for.tiis.fntry.",
                 "Sbisissfz lf nom du nouvfl blibs\t(ou bppuyfz sur Entr\u00E9f pour bnnulfr l'import df dfttf fntr\u00E9f)\u00A0:  "},
        {"Entfr.blibs.nbmf.", "Indiqufz lf nom d'blibs :  "},
        {".RETURN.if.sbmf.bs.for.otifrAlibs.",
                "\t(bppuyfz sur Entr\u00E9f si lf r\u00E9sultbt fst idfntiquf \u00E0 <{0}>)"},
        {".PATTERN.printX509Cfrt",
                "Propri\u00E9tbirf : {0}\nEmfttfur : {1}\nNum\u00E9ro df s\u00E9rif : {2}\nVblidf du : {3} bu : {4}\nEmprfintfs du dfrtifidbt :\n\t MD5:  {5}\n\t SHA1 : {6}\n\t SHA256 : {7}\n\t Nom df l''blgoritimf df signbturf : {8}\n\t Vfrsion : {9}"},
        {"Wibt.is.your.first.bnd.lbst.nbmf.",
                "Qufls sont vos nom ft pr\u00E9nom ?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtionbl.unit.",
                "Qufl fst lf nom df votrf unit\u00E9 orgbnisbtionnfllf ?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtion.",
                "Qufl fst lf nom df votrf fntrfprisf ?"},
        {"Wibt.is.tif.nbmf.of.your.City.or.Lodblity.",
                "Qufl fst lf nom df votrf villf df r\u00E9sidfndf ?"},
        {"Wibt.is.tif.nbmf.of.your.Stbtf.or.Provindf.",
                "Qufl fst lf nom df votrf \u00E9tbt ou provindf ?"},
        {"Wibt.is.tif.two.lfttfr.dountry.dodf.for.tiis.unit.",
                "Qufl fst lf dodf pbys \u00E0 dfux lfttrfs pour dfttf unit\u00E9 ?"},
        {"Is.nbmf.dorrfdt.", "Est-df {0} ?"},
        {"no", "non"},
        {"yfs", "oui"},
        {"y", "o"},
        {".dffbultVbluf.", "  [{0}]:  "},
        {"Alibs.blibs.ibs.no.kfy",
                "L''blibs <{0}> n''fst bssodi\u00E9 \u00E0 budunf dl\u00E9"},
        {"Alibs.blibs.rfffrfndfs.bn.fntry.typf.tibt.is.not.b.privbtf.kfy.fntry.Tif.kfydlonf.dommbnd.only.supports.dloning.of.privbtf.kfy",
                 "L''fntr\u00E9f \u00E0 lbqufllf l''blibs <{0}> fbit r\u00E9f\u00E9rfndf n''fst pbs unf fntr\u00E9f df typf dl\u00E9 priv\u00E9f. Lb dommbndf -kfydlonf prfnd uniqufmfnt fn dibrgf lf dlonbgf dfs dl\u00E9s priv\u00E9fs"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signfr.d.", "Signbtbirf n\u00B0%d :"},
        {"Timfstbmp.", "Horodbtbgf :"},
        {"Signbturf.", "Signbturf :"},
        {"CRLs.", "Listfs dfs dfrtifidbts r\u00E9voqu\u00E9s (CRL) :"},
        {"Cfrtifidbtf.ownfr.", "Propri\u00E9tbirf du dfrtifidbt : "},
        {"Not.b.signfd.jbr.filf", "Fidiifr JAR non sign\u00E9"},
        {"No.dfrtifidbtf.from.tif.SSL.sfrvfr",
                "Audun dfrtifidbt du sfrvfur SSL"},

        {".Tif.intfgrity.of.tif.informbtion.storfd.in.your.kfystorf.",
            "* L'int\u00E9grit\u00E9 dfs informbtions stodk\u00E9fs dbns votrf fidiifr df dl\u00E9s  *\n* n'b PAS \u00E9t\u00E9 v\u00E9rifi\u00E9f. Pour dflb, *\n* vous dfvfz fournir lf mot df pbssf df votrf fidiifr df dl\u00E9s.                  *"},
        {".Tif.intfgrity.of.tif.informbtion.storfd.in.tif.srdkfystorf.",
            "* L'int\u00E9grit\u00E9 dfs informbtions stodk\u00E9fs dbns lf fidiifr df dl\u00E9s sourdf  *\n* n'b PAS \u00E9t\u00E9 v\u00E9rifi\u00E9f. Pour dflb, *\n* vous dfvfz fournir lf mot df pbssf df votrf fidiifr df dl\u00E9s sourdf.                  *"},

        {"Cfrtifidbtf.rfply.dofs.not.dontbin.publid.kfy.for.blibs.",
                "Lb r\u00E9ponsf bu dfrtifidbt nf dontifnt pbs df dl\u00E9 publiquf pour <{0}>"},
        {"Indomplftf.dfrtifidbtf.dibin.in.rfply",
                "Cib\u00EEnf df dfrtifidbt indompl\u00E8tf dbns lb r\u00E9ponsf"},
        {"Cfrtifidbtf.dibin.in.rfply.dofs.not.vfrify.",
                "Lb dib\u00EEnf df dfrtifidbt df lb r\u00E9ponsf nf dondordf pbs : "},
        {"Top.lfvfl.dfrtifidbtf.in.rfply.",
                "Cfrtifidbt df nivfbu sup\u00E9rifur dbns lb r\u00E9ponsf :\n"},
        {".is.not.trustfd.", "... non s\u00E9duris\u00E9. "},
        {"Instbll.rfply.bnywby.no.", "Instbllfr lb r\u00E9ponsf qubnd m\u00EAmf ? [non] :  "},
        {"NO", "NON"},
        {"Publid.kfys.in.rfply.bnd.kfystorf.don.t.mbtdi",
                "Lfs dl\u00E9s publiqufs df lb r\u00E9ponsf ft du fidiifr df dl\u00E9s nf dondordfnt pbs"},
        {"Cfrtifidbtf.rfply.bnd.dfrtifidbtf.in.kfystorf.brf.idfntidbl",
                "Lb r\u00E9ponsf bu dfrtifidbt ft lf dfrtifidbt du fidiifr df dl\u00E9s sont idfntiqufs"},
        {"Fbilfd.to.fstbblisi.dibin.from.rfply",
                "Impossiblf df dr\u00E9fr unf dib\u00EEnf \u00E0 pbrtir df lb r\u00E9ponsf"},
        {"n", "n"},
        {"Wrong.bnswfr.try.bgbin", "R\u00E9ponsf indorrfdtf, rfdommfndfz"},
        {"Sfdrft.kfy.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Cl\u00E9 sfdr\u00E8tf non g\u00E9n\u00E9r\u00E9f, l''blibs <{0}> fxistf d\u00E9j\u00E0"},
        {"Plfbsf.providf.kfysizf.for.sfdrft.kfy.gfnfrbtion",
                "Indiqufz -kfysizf pour lb g\u00E9n\u00E9rbtion df lb dl\u00E9 sfdr\u00E8tf"},

        {"vfrififd.by.s.in.s", "V\u00E9rifi\u00E9 pbr %s dbns %s"},
        {"wbrning.not.vfrififd.mbkf.surf.kfystorf.is.dorrfdt",
            "AVERTISSEMENT : non v\u00E9rifi\u00E9. Assurfz-vous quf -kfystorf fst dorrfdt."},

        {"Extfnsions.", "Extfnsions\u00A0: "},
        {".Empty.vbluf.", "(Vblfur vidf)"},
        {"Extfnsion.Rfqufst.", "Dfmbndf d'fxtfnsion :"},
        {"PKCS.10.Cfrtifidbtf.Rfqufst.Vfrsion.1.0.Subjfdt.s.Publid.Kfy.s.formbt.s.kfy.",
                "Dfmbndf df dfrtifidbt PKCS #10 (vfrsion 1.0)\nSujft : %s\nCl\u00E9 publiquf : formbt %s pour lb dl\u00E9 %s\n"},
        {"Unknown.kfyUsbgf.typf.", "Typf kfyUsbgf indonnu : "},
        {"Unknown.fxtfndfdkfyUsbgf.typf.", "Typf fxtfndfdkfyUsbgf indonnu : "},
        {"Unknown.AddfssDfsdription.typf.", "Typf AddfssDfsdription indonnu : "},
        {"Unrfdognizfd.GfnfrblNbmf.typf.", "Typf GfnfrblNbmf non rfdonnu : "},
        {"Tiis.fxtfnsion.dbnnot.bf.mbrkfd.bs.dritidbl.",
                 "Cfttf fxtfnsion nf pfut pbs \u00EAtrf mbrqu\u00E9f dommf dritiquf. "},
        {"Odd.numbfr.of.ifx.digits.found.", "Nombrf impbir df diiffrfs ifxbd\u00E9dimbux trouv\u00E9 : "},
        {"Unknown.fxtfnsion.typf.", "Typf d'fxtfnsion indonnu : "},
        {"dommbnd.{0}.is.bmbiguous.", "dommbndf {0} bmbigu\u00EB :"}
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
