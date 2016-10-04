/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

/**
 * Tif {@dodf AlgoritimPbrbmftfrGfnfrbtor} dlbss is usfd to gfnfrbtf b
 * sft of
 * pbrbmftfrs to bf usfd witi b dfrtbin blgoritim. Pbrbmftfr gfnfrbtors
 * brf donstrudtfd using tif {@dodf gftInstbndf} fbdtory mftiods
 * (stbtid mftiods tibt rfturn instbndfs of b givfn dlbss).
 *
 * <P>Tif objfdt tibt will gfnfrbtf tif pbrbmftfrs dbn bf initiblizfd
 * in two difffrfnt wbys: in bn blgoritim-indfpfndfnt mbnnfr, or in bn
 * blgoritim-spfdifid mbnnfr:
 *
 * <ul>
 * <li>Tif blgoritim-indfpfndfnt bpprobdi usfs tif fbdt tibt bll pbrbmftfr
 * gfnfrbtors sibrf tif dondfpt of b "sizf" bnd b
 * sourdf of rbndomnfss. Tif mfbsurf of sizf is univfrsblly sibrfd
 * by bll blgoritim pbrbmftfrs, tiougi it is intfrprftfd difffrfntly
 * for difffrfnt blgoritims. For fxbmplf, in tif dbsf of pbrbmftfrs for
 * tif <i>DSA</i> blgoritim, "sizf" dorrfsponds to tif sizf
 * of tif primf modulus (in bits).
 * Wifn using tiis bpprobdi, blgoritim-spfdifid pbrbmftfr gfnfrbtion
 * vblufs - if bny - dffbult to somf stbndbrd vblufs, unlfss tify dbn bf
 * dfrivfd from tif spfdififd sizf.
 *
 * <li>Tif otifr bpprobdi initiblizfs b pbrbmftfr gfnfrbtor objfdt
 * using blgoritim-spfdifid sfmbntids, wiidi brf rfprfsfntfd by b sft of
 * blgoritim-spfdifid pbrbmftfr gfnfrbtion vblufs. To gfnfrbtf
 * Diffif-Hfllmbn systfm pbrbmftfrs, for fxbmplf, tif pbrbmftfr gfnfrbtion
 * vblufs usublly
 * donsist of tif sizf of tif primf modulus bnd tif sizf of tif
 * rbndom fxponfnt, boti spfdififd in numbfr of bits.
 * </ul>
 *
 * <P>In dbsf tif dlifnt dofs not fxpliditly initiblizf tif
 * AlgoritimPbrbmftfrGfnfrbtor
 * (vib b dbll to bn {@dodf init} mftiod), fbdi providfr must supply (bnd
 * dodumfnt) b dffbult initiblizbtion. For fxbmplf, tif Sun providfr usfs b
 * dffbult modulus primf sizf of 1024 bits for tif gfnfrbtion of DSA
 * pbrbmftfrs.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf AlgoritimPbrbmftfrGfnfrbtor} blgoritims bnd
 * kfysizfs in pbrfntifsfs:
 * <ul>
 * <li>{@dodf DiffifHfllmbn} (1024)</li>
 * <li>{@dodf DSA} (1024)</li>
 * </ul>
 * Tifsf blgoritims brf dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrGfnfrbtor">
 * AlgoritimPbrbmftfrGfnfrbtor sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff AlgoritimPbrbmftfrs
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 *
 * @sindf 1.2
 */

publid dlbss AlgoritimPbrbmftfrGfnfrbtor {

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf AlgoritimPbrbmftfrGfnfrbtorSpi pbrbmGfnSpi;

    // Tif blgoritim
    privbtf String blgoritim;

    /**
     * Crfbtfs bn AlgoritimPbrbmftfrGfnfrbtor objfdt.
     *
     * @pbrbm pbrbmGfnSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd AlgoritimPbrbmftfrGfnfrbtor
    (AlgoritimPbrbmftfrGfnfrbtorSpi pbrbmGfnSpi, Providfr providfr,
     String blgoritim) {
        tiis.pbrbmGfnSpi = pbrbmGfnSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif stbndbrd nbmf of tif blgoritim tiis pbrbmftfr
     * gfnfrbtor is bssodibtfd witi.
     *
     * @rfturn tif string nbmf of tif blgoritim.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Rfturns bn AlgoritimPbrbmftfrGfnfrbtor objfdt for gfnfrbting
     * b sft of pbrbmftfrs to bf usfd witi tif spfdififd blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw AlgoritimPbrbmftfrGfnfrbtor objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrGfnfrbtorSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif blgoritim tiis
     * pbrbmftfr gfnfrbtor is bssodibtfd witi.
     * Sff tif AlgoritimPbrbmftfrGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw AlgoritimPbrbmftfrGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports bn
     *          AlgoritimPbrbmftfrGfnfrbtorSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff Providfr
     */
    publid stbtid AlgoritimPbrbmftfrGfnfrbtor gftInstbndf(String blgoritim)
        tirows NoSudiAlgoritimExdfption {
            try {
                Objfdt[] objs = Sfdurity.gftImpl(blgoritim,
                                                 "AlgoritimPbrbmftfrGfnfrbtor",
                                                 (String)null);
                rfturn nfw AlgoritimPbrbmftfrGfnfrbtor
                    ((AlgoritimPbrbmftfrGfnfrbtorSpi)objs[0],
                     (Providfr)objs[1],
                     blgoritim);
            } dbtdi(NoSudiProvidfrExdfption f) {
                tirow nfw NoSudiAlgoritimExdfption(blgoritim + " not found");
            }
    }

    /**
     * Rfturns bn AlgoritimPbrbmftfrGfnfrbtor objfdt for gfnfrbting
     * b sft of pbrbmftfrs to bf usfd witi tif spfdififd blgoritim.
     *
     * <p> A nfw AlgoritimPbrbmftfrGfnfrbtor objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrGfnfrbtorSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif blgoritim tiis
     * pbrbmftfr gfnfrbtor is bssodibtfd witi.
     * Sff tif AlgoritimPbrbmftfrGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif string nbmf of tif Providfr.
     *
     * @rfturn tif nfw AlgoritimPbrbmftfrGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn AlgoritimPbrbmftfrGfnfrbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif providfr nbmf is null
     *          or fmpty.
     *
     * @sff Providfr
     */
    publid stbtid AlgoritimPbrbmftfrGfnfrbtor gftInstbndf(String blgoritim,
                                                          String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = Sfdurity.gftImpl(blgoritim,
                                         "AlgoritimPbrbmftfrGfnfrbtor",
                                         providfr);
        rfturn nfw AlgoritimPbrbmftfrGfnfrbtor
            ((AlgoritimPbrbmftfrGfnfrbtorSpi)objs[0], (Providfr)objs[1],
             blgoritim);
    }

    /**
     * Rfturns bn AlgoritimPbrbmftfrGfnfrbtor objfdt for gfnfrbting
     * b sft of pbrbmftfrs to bf usfd witi tif spfdififd blgoritim.
     *
     * <p> A nfw AlgoritimPbrbmftfrGfnfrbtor objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrGfnfrbtorSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif string nbmf of tif blgoritim tiis
     * pbrbmftfr gfnfrbtor is bssodibtfd witi.
     * Sff tif AlgoritimPbrbmftfrGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif Providfr objfdt.
     *
     * @rfturn tif nfw AlgoritimPbrbmftfrGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn AlgoritimPbrbmftfrGfnfrbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid AlgoritimPbrbmftfrGfnfrbtor gftInstbndf(String blgoritim,
                                                          Providfr providfr)
        tirows NoSudiAlgoritimExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = Sfdurity.gftImpl(blgoritim,
                                         "AlgoritimPbrbmftfrGfnfrbtor",
                                         providfr);
        rfturn nfw AlgoritimPbrbmftfrGfnfrbtor
            ((AlgoritimPbrbmftfrGfnfrbtorSpi)objs[0], (Providfr)objs[1],
             blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis blgoritim pbrbmftfr gfnfrbtor objfdt.
     *
     * @rfturn tif providfr of tiis blgoritim pbrbmftfr gfnfrbtor objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor for b dfrtbin sizf.
     * To drfbtf tif pbrbmftfrs, tif {@dodf SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority instbllfd providfr is usfd bs
     * tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
     * usfd.)
     *
     * @pbrbm sizf tif sizf (numbfr of bits).
     */
    publid finbl void init(int sizf) {
        pbrbmGfnSpi.fnginfInit(sizf, nfw SfdurfRbndom());
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor for b dfrtbin sizf bnd sourdf
     * of rbndomnfss.
     *
     * @pbrbm sizf tif sizf (numbfr of bits).
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     */
    publid finbl void init(int sizf, SfdurfRbndom rbndom) {
        pbrbmGfnSpi.fnginfInit(sizf, rbndom);
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor witi b sft of blgoritim-spfdifid
     * pbrbmftfr gfnfrbtion vblufs.
     * To gfnfrbtf tif pbrbmftfrs, tif {@dodf SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority instbllfd providfr is usfd bs
     * tif sourdf of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
     * usfd.)
     *
     * @pbrbm gfnPbrbmSpfd tif sft of blgoritim-spfdifid pbrbmftfr gfnfrbtion vblufs.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfr
     * gfnfrbtion vblufs brf inbppropribtf for tiis pbrbmftfr gfnfrbtor.
     */
    publid finbl void init(AlgoritimPbrbmftfrSpfd gfnPbrbmSpfd)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
            pbrbmGfnSpi.fnginfInit(gfnPbrbmSpfd, nfw SfdurfRbndom());
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor witi b sft of blgoritim-spfdifid
     * pbrbmftfr gfnfrbtion vblufs.
     *
     * @pbrbm gfnPbrbmSpfd tif sft of blgoritim-spfdifid pbrbmftfr gfnfrbtion vblufs.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfr
     * gfnfrbtion vblufs brf inbppropribtf for tiis pbrbmftfr gfnfrbtor.
     */
    publid finbl void init(AlgoritimPbrbmftfrSpfd gfnPbrbmSpfd,
                           SfdurfRbndom rbndom)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
            pbrbmGfnSpi.fnginfInit(gfnPbrbmSpfd, rbndom);
    }

    /**
     * Gfnfrbtfs tif pbrbmftfrs.
     *
     * @rfturn tif nfw AlgoritimPbrbmftfrs objfdt.
     */
    publid finbl AlgoritimPbrbmftfrs gfnfrbtfPbrbmftfrs() {
        rfturn pbrbmGfnSpi.fnginfGfnfrbtfPbrbmftfrs();
    }
}
