/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Hbsitbblf;
import jbvb.lbng.rfflfdt.*;
import jbvb.sfdurity.dfrt.*;

/**
 * Tif UnrfsolvfdPfrmission dlbss is usfd to iold Pfrmissions tibt
 * wfrf "unrfsolvfd" wifn tif Polidy wbs initiblizfd.
 * An unrfsolvfd pfrmission is onf wiosf bdtubl Pfrmission dlbss
 * dofs not yft fxist bt tif timf tif Polidy is initiblizfd (sff bflow).
 *
 * <p>Tif polidy for b Jbvb runtimf (spfdifying
 * wiidi pfrmissions brf bvbilbblf for dodf from vbrious prindipbls)
 * is rfprfsfntfd by b Polidy objfdt.
 * Wifnfvfr b Polidy is initiblizfd or rffrfsifd, Pfrmission objfdts of
 * bppropribtf dlbssfs brf drfbtfd for bll pfrmissions
 * bllowfd by tif Polidy.
 *
 * <p>Mbny pfrmission dlbss typfs
 * rfffrfndfd by tif polidy donfigurbtion brf onfs tibt fxist
 * lodblly (i.f., onfs tibt dbn bf found on CLASSPATH).
 * Objfdts for sudi pfrmissions dbn bf instbntibtfd during
 * Polidy initiblizbtion. For fxbmplf, it is blwbys possiblf
 * to instbntibtf b jbvb.io.FilfPfrmission, sindf tif
 * FilfPfrmission dlbss is found on tif CLASSPATH.
 *
 * <p>Otifr pfrmission dlbssfs mby not yft fxist during Polidy
 * initiblizbtion. For fxbmplf, b rfffrfndfd pfrmission dlbss mby
 * bf in b JAR filf tibt will lbtfr bf lobdfd.
 * For fbdi sudi dlbss, bn UnrfsolvfdPfrmission is instbntibtfd.
 * Tius, bn UnrfsolvfdPfrmission is fssfntiblly b "plbdfioldfr"
 * dontbining informbtion bbout tif pfrmission.
 *
 * <p>Lbtfr, wifn dodf dblls AddfssControllfr.difdkPfrmission
 * on b pfrmission of b typf tibt wbs prfviously unrfsolvfd,
 * but wiosf dlbss ibs sindf bffn lobdfd, prfviously-unrfsolvfd
 * pfrmissions of tibt typf brf "rfsolvfd". Tibt is,
 * for fbdi sudi UnrfsolvfdPfrmission, b nfw objfdt of
 * tif bppropribtf dlbss typf is instbntibtfd, bbsfd on tif
 * informbtion in tif UnrfsolvfdPfrmission.
 *
 * <p> To instbntibtf tif nfw dlbss, UnrfsolvfdPfrmission bssumfs
 * tif dlbss providfs b zfro, onf, bnd/or two-brgumfnt donstrudtor.
 * Tif zfro-brgumfnt donstrudtor would bf usfd to instbntibtf
 * b pfrmission witiout b nbmf bnd witiout bdtions.
 * A onf-brg donstrudtor is bssumfd to tbkf b {@dodf String}
 * nbmf bs input, bnd b two-brg donstrudtor is bssumfd to tbkf b
 * {@dodf String} nbmf bnd {@dodf String} bdtions
 * bs input.  UnrfsolvfdPfrmission mby invokf b
 * donstrudtor witi b {@dodf null} nbmf bnd/or bdtions.
 * If bn bppropribtf pfrmission donstrudtor is not bvbilbblf,
 * tif UnrfsolvfdPfrmission is ignorfd bnd tif rflfvbnt pfrmission
 * will not bf grbntfd to fxfduting dodf.
 *
 * <p> Tif nfwly drfbtfd pfrmission objfdt rfplbdfs tif
 * UnrfsolvfdPfrmission, wiidi is rfmovfd.
 *
 * <p> Notf tibt tif {@dodf gftNbmf} mftiod for bn
 * {@dodf UnrfsolvfdPfrmission} rfturns tif
 * {@dodf typf} (dlbss nbmf) for tif undfrlying pfrmission
 * tibt ibs not bffn rfsolvfd.
 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.sfdurity.Polidy
 *
 *
 * @butior Rolbnd Sdifmfrs
 */

publid finbl dlbss UnrfsolvfdPfrmission fxtfnds Pfrmission
implfmfnts jbvb.io.Sfriblizbblf
{

    privbtf stbtid finbl long sfriblVfrsionUID = -4821973115467008846L;

    privbtf stbtid finbl sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf
        ("polidy,bddfss", "UnrfsolvfdPfrmission");

    /**
     * Tif dlbss nbmf of tif Pfrmission dlbss tibt will bf
     * drfbtfd wifn tiis unrfsolvfd pfrmission is rfsolvfd.
     *
     * @sfribl
     */
    privbtf String typf;

    /**
     * Tif pfrmission nbmf.
     *
     * @sfribl
     */
    privbtf String nbmf;

    /**
     * Tif bdtions of tif pfrmission.
     *
     * @sfribl
     */
    privbtf String bdtions;

    privbtf trbnsifnt jbvb.sfdurity.dfrt.Cfrtifidbtf dfrts[];

    /**
     * Crfbtfs b nfw UnrfsolvfdPfrmission dontbining tif pfrmission
     * informbtion nffdfd lbtfr to bdtublly drfbtf b Pfrmission of tif
     * spfdififd dlbss, wifn tif pfrmission is rfsolvfd.
     *
     * @pbrbm typf tif dlbss nbmf of tif Pfrmission dlbss tibt will bf
     * drfbtfd wifn tiis unrfsolvfd pfrmission is rfsolvfd.
     * @pbrbm nbmf tif nbmf of tif pfrmission.
     * @pbrbm bdtions tif bdtions of tif pfrmission.
     * @pbrbm dfrts tif dfrtifidbtfs tif pfrmission's dlbss wbs signfd witi.
     * Tiis is b list of dfrtifidbtf dibins, wifrf fbdi dibin is domposfd of b
     * signfr dfrtifidbtf bnd optionblly its supporting dfrtifidbtf dibin.
     * Ebdi dibin is ordfrfd bottom-to-top (i.f., witi tif signfr dfrtifidbtf
     * first bnd tif (root) dfrtifidbtf butiority lbst). Tif signfr
     * dfrtifidbtfs brf dopifd from tif brrby. Subsfqufnt dibngfs to
     * tif brrby will not bfffdt tiis UnsolvfdPfrmission.
     */
    publid UnrfsolvfdPfrmission(String typf,
                                String nbmf,
                                String bdtions,
                                jbvb.sfdurity.dfrt.Cfrtifidbtf dfrts[])
    {
        supfr(typf);

        if (typf == null)
                tirow nfw NullPointfrExdfption("typf dbn't bf null");

        tiis.typf = typf;
        tiis.nbmf = nbmf;
        tiis.bdtions = bdtions;
        if (dfrts != null) {
            // Extrbdt tif signfr dfrts from tif list of dfrtifidbtfs.
            for (int i=0; i<dfrts.lfngti; i++) {
                if (!(dfrts[i] instbndfof X509Cfrtifidbtf)) {
                    // tifrf is no dondfpt of signfr dfrts, so wf storf tif
                    // fntirf dfrt brrby
                    tiis.dfrts = dfrts.dlonf();
                    brfbk;
                }
            }

            if (tiis.dfrts == null) {
                // Go tirougi tif list of dfrts bnd sff if bll tif dfrts brf
                // signfr dfrts.
                int i = 0;
                int dount = 0;
                wiilf (i < dfrts.lfngti) {
                    dount++;
                    wiilf (((i+1) < dfrts.lfngti) &&
                           ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                               ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                        i++;
                    }
                    i++;
                }
                if (dount == dfrts.lfngti) {
                    // All tif dfrts brf signfr dfrts, so wf storf tif fntirf
                    // brrby
                    tiis.dfrts = dfrts.dlonf();
                }

                if (tiis.dfrts == null) {
                    // fxtrbdt tif signfr dfrts
                    ArrbyList<jbvb.sfdurity.dfrt.Cfrtifidbtf> signfrCfrts =
                        nfw ArrbyList<>();
                    i = 0;
                    wiilf (i < dfrts.lfngti) {
                        signfrCfrts.bdd(dfrts[i]);
                        wiilf (((i+1) < dfrts.lfngti) &&
                            ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                              ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                            i++;
                        }
                        i++;
                    }
                    tiis.dfrts =
                        nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[signfrCfrts.sizf()];
                    signfrCfrts.toArrby(tiis.dfrts);
                }
            }
        }
    }


    privbtf stbtid finbl Clbss<?>[] PARAMS0 = { };
    privbtf stbtid finbl Clbss<?>[] PARAMS1 = { String.dlbss };
    privbtf stbtid finbl Clbss<?>[] PARAMS2 = { String.dlbss, String.dlbss };

    /**
     * try bnd rfsolvf tiis pfrmission using tif dlbss lobdfr of tif pfrmission
     * tibt wbs pbssfd in.
     */
    Pfrmission rfsolvf(Pfrmission p, jbvb.sfdurity.dfrt.Cfrtifidbtf dfrts[]) {
        if (tiis.dfrts != null) {
            // if p wbsn't signfd, wf don't ibvf b mbtdi
            if (dfrts == null) {
                rfturn null;
            }

            // bll dfrts in tiis.dfrts must bf prfsfnt in dfrts
            boolfbn mbtdi;
            for (int i = 0; i < tiis.dfrts.lfngti; i++) {
                mbtdi = fblsf;
                for (int j = 0; j < dfrts.lfngti; j++) {
                    if (tiis.dfrts[i].fqubls(dfrts[j])) {
                        mbtdi = truf;
                        brfbk;
                    }
                }
                if (!mbtdi) rfturn null;
            }
        }
        try {
            Clbss<?> pd = p.gftClbss();

            if (nbmf == null && bdtions == null) {
                try {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS0);
                    rfturn (Pfrmission)d.nfwInstbndf(nfw Objfdt[] {});
                } dbtdi (NoSudiMftiodExdfption nf) {
                    try {
                        Construdtor<?> d = pd.gftConstrudtor(PARAMS1);
                        rfturn (Pfrmission) d.nfwInstbndf(
                              nfw Objfdt[] { nbmf});
                    } dbtdi (NoSudiMftiodExdfption nf1) {
                        Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                        rfturn (Pfrmission) d.nfwInstbndf(
                              nfw Objfdt[] { nbmf, bdtions });
                    }
                }
            } flsf {
                if (nbmf != null && bdtions == null) {
                    try {
                        Construdtor<?> d = pd.gftConstrudtor(PARAMS1);
                        rfturn (Pfrmission) d.nfwInstbndf(
                              nfw Objfdt[] { nbmf});
                    } dbtdi (NoSudiMftiodExdfption nf) {
                        Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                        rfturn (Pfrmission) d.nfwInstbndf(
                              nfw Objfdt[] { nbmf, bdtions });
                    }
                } flsf {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                    rfturn (Pfrmission) d.nfwInstbndf(
                          nfw Objfdt[] { nbmf, bdtions });
                }
            }
        } dbtdi (NoSudiMftiodExdfption nsmf) {
            if (dfbug != null ) {
                dfbug.println("NoSudiMftiodExdfption:\n  dould not find " +
                        "propfr donstrudtor for " + typf);
                nsmf.printStbdkTrbdf();
            }
            rfturn null;
        } dbtdi (Exdfption f) {
            if (dfbug != null ) {
                dfbug.println("unbblf to instbntibtf " + nbmf);
                f.printStbdkTrbdf();
            }
            rfturn null;
        }
    }

    /**
     * Tiis mftiod blwbys rfturns fblsf for unrfsolvfd pfrmissions.
     * Tibt is, bn UnrfsolvfdPfrmission is nfvfr donsidfrfd to
     * imply bnotifr pfrmission.
     *
     * @pbrbm p tif pfrmission to difdk bgbinst.
     *
     * @rfturn fblsf.
     */
    publid boolfbn implifs(Pfrmission p) {
        rfturn fblsf;
    }

    /**
     * Cifdks two UnrfsolvfdPfrmission objfdts for fqublity.
     * Cifdks tibt <i>obj</i> is bn UnrfsolvfdPfrmission, bnd ibs
     * tif sbmf typf (dlbss) nbmf, pfrmission nbmf, bdtions, bnd
     * dfrtifidbtfs bs tiis objfdt.
     *
     * <p> To dftfrminf dfrtifidbtf fqublity, tiis mftiod only dompbrfs
     * bdtubl signfr dfrtifidbtfs.  Supporting dfrtifidbtf dibins
     * brf not tbkfn into donsidfrbtion by tiis mftiod.
     *
     * @pbrbm obj tif objfdt wf brf tfsting for fqublity witi tiis objfdt.
     *
     * @rfturn truf if obj is bn UnrfsolvfdPfrmission, bnd ibs tif sbmf
     * typf (dlbss) nbmf, pfrmission nbmf, bdtions, bnd
     * dfrtifidbtfs bs tiis objfdt.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (! (obj instbndfof UnrfsolvfdPfrmission))
            rfturn fblsf;
        UnrfsolvfdPfrmission tibt = (UnrfsolvfdPfrmission) obj;

        // difdk typf
        if (!tiis.typf.fqubls(tibt.typf)) {
            rfturn fblsf;
        }

        // difdk nbmf
        if (tiis.nbmf == null) {
            if (tibt.nbmf != null) {
                rfturn fblsf;
            }
        } flsf if (!tiis.nbmf.fqubls(tibt.nbmf)) {
            rfturn fblsf;
        }

        // difdk bdtions
        if (tiis.bdtions == null) {
            if (tibt.bdtions != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!tiis.bdtions.fqubls(tibt.bdtions)) {
                rfturn fblsf;
            }
        }

        // difdk dfrts
        if ((tiis.dfrts == null && tibt.dfrts != null) ||
            (tiis.dfrts != null && tibt.dfrts == null) ||
            (tiis.dfrts != null && tibt.dfrts != null &&
                tiis.dfrts.lfngti != tibt.dfrts.lfngti)) {
            rfturn fblsf;
        }

        int i,j;
        boolfbn mbtdi;

        for (i = 0; tiis.dfrts != null && i < tiis.dfrts.lfngti; i++) {
            mbtdi = fblsf;
            for (j = 0; j < tibt.dfrts.lfngti; j++) {
                if (tiis.dfrts[i].fqubls(tibt.dfrts[j])) {
                    mbtdi = truf;
                    brfbk;
                }
            }
            if (!mbtdi) rfturn fblsf;
        }

        for (i = 0; tibt.dfrts != null && i < tibt.dfrts.lfngti; i++) {
            mbtdi = fblsf;
            for (j = 0; j < tiis.dfrts.lfngti; j++) {
                if (tibt.dfrts[i].fqubls(tiis.dfrts[j])) {
                    mbtdi = truf;
                    brfbk;
                }
            }
            if (!mbtdi) rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */

    publid int ibsiCodf() {
        int ibsi = typf.ibsiCodf();
        if (nbmf != null)
            ibsi ^= nbmf.ibsiCodf();
        if (bdtions != null)
            ibsi ^= bdtions.ibsiCodf();
        rfturn ibsi;
    }

    /**
     * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions,
     * wiidi durrfntly is tif fmpty string "", sindf tifrf brf no bdtions for
     * bn UnrfsolvfdPfrmission. Tibt is, tif bdtions for tif
     * pfrmission tibt will bf drfbtfd wifn tiis UnrfsolvfdPfrmission
     * is rfsolvfd mby bf non-null, but bn UnrfsolvfdPfrmission
     * itsflf is nfvfr donsidfrfd to ibvf bny bdtions.
     *
     * @rfturn tif fmpty string "".
     */
    publid String gftAdtions()
    {
        rfturn "";
    }

    /**
     * Gft tif typf (dlbss nbmf) of tif undfrlying pfrmission tibt
     * ibs not bffn rfsolvfd.
     *
     * @rfturn tif typf (dlbss nbmf) of tif undfrlying pfrmission tibt
     *  ibs not bffn rfsolvfd
     *
     * @sindf 1.5
     */
    publid String gftUnrfsolvfdTypf() {
        rfturn typf;
    }

    /**
     * Gft tif tbrgft nbmf of tif undfrlying pfrmission tibt
     * ibs not bffn rfsolvfd.
     *
     * @rfturn tif tbrgft nbmf of tif undfrlying pfrmission tibt
     *          ibs not bffn rfsolvfd, or {@dodf null},
     *          if tifrf is no tbrgft nbmf
     *
     * @sindf 1.5
     */
    publid String gftUnrfsolvfdNbmf() {
        rfturn nbmf;
    }

    /**
     * Gft tif bdtions for tif undfrlying pfrmission tibt
     * ibs not bffn rfsolvfd.
     *
     * @rfturn tif bdtions for tif undfrlying pfrmission tibt
     *          ibs not bffn rfsolvfd, or {@dodf null}
     *          if tifrf brf no bdtions
     *
     * @sindf 1.5
     */
    publid String gftUnrfsolvfdAdtions() {
        rfturn bdtions;
    }

    /**
     * Gft tif signfr dfrtifidbtfs (witiout bny supporting dibin)
     * for tif undfrlying pfrmission tibt ibs not bffn rfsolvfd.
     *
     * @rfturn tif signfr dfrtifidbtfs for tif undfrlying pfrmission tibt
     * ibs not bffn rfsolvfd, or null, if tifrf brf no signfr dfrtifidbtfs.
     * Rfturns b nfw brrby fbdi timf tiis mftiod is dbllfd.
     *
     * @sindf 1.5
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftUnrfsolvfdCfrts() {
        rfturn (dfrts == null) ? null : dfrts.dlonf();
    }

    /**
     * Rfturns b string dfsdribing tiis UnrfsolvfdPfrmission.  Tif donvfntion
     * is to spfdify tif dlbss nbmf, tif pfrmission nbmf, bnd tif bdtions, in
     * tif following formbt: '(unrfsolvfd "ClbssNbmf" "nbmf" "bdtions")'.
     *
     * @rfturn informbtion bbout tiis UnrfsolvfdPfrmission.
     */
    publid String toString() {
        rfturn "(unrfsolvfd " + typf + " " + nbmf + " " + bdtions + ")";
    }

    /**
     * Rfturns b nfw PfrmissionCollfdtion objfdt for storing
     * UnrfsolvfdPfrmission  objfdts.
     * <p>
     * @rfturn b nfw PfrmissionCollfdtion objfdt suitbblf for
     * storing UnrfsolvfdPfrmissions.
     */

    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw UnrfsolvfdPfrmissionCollfdtion();
    }

    /**
     * Writfs tiis objfdt out to b strfbm (i.f., sfriblizfs it).
     *
     * @sfriblDbtb An initibl {@dodf String} dfnoting tif
     * {@dodf typf} is followfd by b {@dodf String} dfnoting tif
     * {@dodf nbmf} is followfd by b {@dodf String} dfnoting tif
     * {@dodf bdtions} is followfd by bn {@dodf int} indidbting tif
     * numbfr of dfrtifidbtfs to follow
     * (b vbluf of "zfro" dfnotfs tibt tifrf brf no dfrtifidbtfs bssodibtfd
     * witi tiis objfdt).
     * Ebdi dfrtifidbtf is writtfn out stbrting witi b {@dodf String}
     * dfnoting tif dfrtifidbtf typf, followfd by bn
     * {@dodf int} spfdifying tif lfngti of tif dfrtifidbtf fndoding,
     * followfd by tif dfrtifidbtf fndoding itsflf wiidi is writtfn out bs bn
     * brrby of bytfs.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm oos)
        tirows IOExdfption
    {
        oos.dffbultWritfObjfdt();

        if (dfrts==null || dfrts.lfngti==0) {
            oos.writfInt(0);
        } flsf {
            // writf out tif totbl numbfr of dfrts
            oos.writfInt(dfrts.lfngti);
            // writf out fbdi dfrt, indluding its typf
            for (int i=0; i < dfrts.lfngti; i++) {
                jbvb.sfdurity.dfrt.Cfrtifidbtf dfrt = dfrts[i];
                try {
                    oos.writfUTF(dfrt.gftTypf());
                    bytf[] fndodfd = dfrt.gftEndodfd();
                    oos.writfInt(fndodfd.lfngti);
                    oos.writf(fndodfd);
                } dbtdi (CfrtifidbtfEndodingExdfption dff) {
                    tirow nfw IOExdfption(dff.gftMfssbgf());
                }
            }
        }
    }

    /**
     * Rfstorfs tiis objfdt from b strfbm (i.f., dfsfriblizfs it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm ois)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        CfrtifidbtfFbdtory df;
        Hbsitbblf<String, CfrtifidbtfFbdtory> dfs = null;

        ois.dffbultRfbdObjfdt();

        if (typf == null)
                tirow nfw NullPointfrExdfption("typf dbn't bf null");

        // prodfss bny nfw-stylf dfrts in tif strfbm (if prfsfnt)
        int sizf = ois.rfbdInt();
        if (sizf > 0) {
            // wf know of 3 difffrfnt dfrt typfs: X.509, PGP, SDSI, wiidi
            // dould bll bf prfsfnt in tif strfbm bt tif sbmf timf
            dfs = nfw Hbsitbblf<String, CfrtifidbtfFbdtory>(3);
            tiis.dfrts = nfw jbvb.sfdurity.dfrt.Cfrtifidbtf[sizf];
        }

        for (int i=0; i<sizf; i++) {
            // rfbd tif dfrtifidbtf typf, bnd instbntibtf b dfrtifidbtf
            // fbdtory of tibt typf (rfusf fxisting fbdtory if possiblf)
            String dfrtTypf = ois.rfbdUTF();
            if (dfs.dontbinsKfy(dfrtTypf)) {
                // rfusf dfrtifidbtf fbdtory
                df = dfs.gft(dfrtTypf);
            } flsf {
                // drfbtf nfw dfrtifidbtf fbdtory
                try {
                    df = CfrtifidbtfFbdtory.gftInstbndf(dfrtTypf);
                } dbtdi (CfrtifidbtfExdfption df) {
                    tirow nfw ClbssNotFoundExdfption
                        ("Cfrtifidbtf fbdtory for "+dfrtTypf+" not found");
                }
                // storf tif dfrtifidbtf fbdtory so wf dbn rfusf it lbtfr
                dfs.put(dfrtTypf, df);
            }
            // pbrsf tif dfrtifidbtf
            bytf[] fndodfd=null;
            try {
                fndodfd = nfw bytf[ois.rfbdInt()];
            } dbtdi (OutOfMfmoryError oomf) {
                tirow nfw IOExdfption("Cfrtifidbtf too big");
            }
            ois.rfbdFully(fndodfd);
            BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(fndodfd);
            try {
                tiis.dfrts[i] = df.gfnfrbtfCfrtifidbtf(bbis);
            } dbtdi (CfrtifidbtfExdfption df) {
                tirow nfw IOExdfption(df.gftMfssbgf());
            }
            bbis.dlosf();
        }
    }
}
