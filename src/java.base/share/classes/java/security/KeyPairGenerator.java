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

import jbvb.util.*;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvb.sfdurity.Providfr.Sfrvidf;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tif KfyPbirGfnfrbtor dlbss is usfd to gfnfrbtf pbirs of
 * publid bnd privbtf kfys. Kfy pbir gfnfrbtors brf donstrudtfd using tif
 * {@dodf gftInstbndf} fbdtory mftiods (stbtid mftiods tibt
 * rfturn instbndfs of b givfn dlbss).
 *
 * <p>A Kfy pbir gfnfrbtor for b pbrtidulbr blgoritim drfbtfs b publid/privbtf
 * kfy pbir tibt dbn bf usfd witi tiis blgoritim. It blso bssodibtfs
 * blgoritim-spfdifid pbrbmftfrs witi fbdi of tif gfnfrbtfd kfys.
 *
 * <p>Tifrf brf two wbys to gfnfrbtf b kfy pbir: in bn blgoritim-indfpfndfnt
 * mbnnfr, bnd in bn blgoritim-spfdifid mbnnfr.
 * Tif only difffrfndf bftwffn tif two is tif initiblizbtion of tif objfdt:
 *
 * <ul>
 * <li><b>Algoritim-Indfpfndfnt Initiblizbtion</b>
 * <p>All kfy pbir gfnfrbtors sibrf tif dondfpts of b kfysizf bnd b
 * sourdf of rbndomnfss. Tif kfysizf is intfrprftfd difffrfntly for difffrfnt
 * blgoritims (f.g., in tif dbsf of tif <i>DSA</i> blgoritim, tif kfysizf
 * dorrfsponds to tif lfngti of tif modulus).
 * Tifrf is bn
 * {@link #initiblizf(int, jbvb.sfdurity.SfdurfRbndom) initiblizf}
 * mftiod in tiis KfyPbirGfnfrbtor dlbss tibt tbkfs tifsf two univfrsblly
 * sibrfd typfs of brgumfnts. Tifrf is blso onf tibt tbkfs just b
 * {@dodf kfysizf} brgumfnt, bnd usfs tif {@dodf SfdurfRbndom}
 * implfmfntbtion of tif iigifst-priority instbllfd providfr bs tif sourdf
 * of rbndomnfss. (If nonf of tif instbllfd providfrs supply bn implfmfntbtion
 * of {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
 * usfd.)
 *
 * <p>Sindf no otifr pbrbmftfrs brf spfdififd wifn you dbll tif bbovf
 * blgoritim-indfpfndfnt {@dodf initiblizf} mftiods, it is up to tif
 * providfr wibt to do bbout tif blgoritim-spfdifid pbrbmftfrs (if bny) to bf
 * bssodibtfd witi fbdi of tif kfys.
 *
 * <p>If tif blgoritim is tif <i>DSA</i> blgoritim, bnd tif kfysizf (modulus
 * sizf) is 512, 768, or 1024, tifn tif <i>Sun</i> providfr usfs b sft of
 * prfdomputfd vblufs for tif {@dodf p}, {@dodf q}, bnd
 * {@dodf g} pbrbmftfrs. If tif modulus sizf is not onf of tif bbovf
 * vblufs, tif <i>Sun</i> providfr drfbtfs b nfw sft of pbrbmftfrs. Otifr
 * providfrs migit ibvf prfdomputfd pbrbmftfr sfts for morf tibn just tif
 * tirff modulus sizfs mfntionfd bbovf. Still otifrs migit not ibvf b list of
 * prfdomputfd pbrbmftfrs bt bll bnd instfbd blwbys drfbtf nfw pbrbmftfr sfts.
 *
 * <li><b>Algoritim-Spfdifid Initiblizbtion</b>
 * <p>For situbtions wifrf b sft of blgoritim-spfdifid pbrbmftfrs blrfbdy
 * fxists (f.g., so-dbllfd <i>dommunity pbrbmftfrs</i> in DSA), tifrf brf two
 * {@link #initiblizf(jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd)
 * initiblizf} mftiods tibt ibvf bn {@dodf AlgoritimPbrbmftfrSpfd}
 * brgumfnt. Onf blso ibs b {@dodf SfdurfRbndom} brgumfnt, wiilf tif
 * tif otifr usfs tif {@dodf SfdurfRbndom}
 * implfmfntbtion of tif iigifst-priority instbllfd providfr bs tif sourdf
 * of rbndomnfss. (If nonf of tif instbllfd providfrs supply bn implfmfntbtion
 * of {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
 * usfd.)
 * </ul>
 *
 * <p>In dbsf tif dlifnt dofs not fxpliditly initiblizf tif KfyPbirGfnfrbtor
 * (vib b dbll to bn {@dodf initiblizf} mftiod), fbdi providfr must
 * supply (bnd dodumfnt) b dffbult initiblizbtion.
 * For fxbmplf, tif <i>Sun</i> providfr usfs b dffbult modulus sizf (kfysizf)
 * of 1024 bits.
 *
 * <p>Notf tibt tiis dlbss is bbstrbdt bnd fxtfnds from
 * {@dodf KfyPbirGfnfrbtorSpi} for iistoridbl rfbsons.
 * Applidbtion dfvflopfrs siould only tbkf notidf of tif mftiods dffinfd in
 * tiis {@dodf KfyPbirGfnfrbtor} dlbss; bll tif mftiods in
 * tif supfrdlbss brf intfndfd for dryptogrbpiid sfrvidf providfrs wio wisi to
 * supply tifir own implfmfntbtions of kfy pbir gfnfrbtors.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf KfyPbirGfnfrbtor} blgoritims bnd kfysizfs in
 * pbrfntifsfs:
 * <ul>
 * <li>{@dodf DiffifHfllmbn} (1024)</li>
 * <li>{@dodf DSA} (1024)</li>
 * <li>{@dodf RSA} (1024, 2048)</li>
 * </ul>
 * Tifsf blgoritims brf dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
 * KfyPbirGfnfrbtor sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @butior Bfnjbmin Rfnbud
 *
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 */

publid bbstrbdt dlbss KfyPbirGfnfrbtor fxtfnds KfyPbirGfnfrbtorSpi {

    privbtf finbl String blgoritim;

    // Tif providfr
    Providfr providfr;

    /**
     * Crfbtfs b KfyPbirGfnfrbtor objfdt for tif spfdififd blgoritim.
     *
     * @pbrbm blgoritim tif stbndbrd string nbmf of tif blgoritim.
     * Sff tif KfyPbirGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     */
    protfdtfd KfyPbirGfnfrbtor(String blgoritim) {
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif stbndbrd nbmf of tif blgoritim for tiis kfy pbir gfnfrbtor.
     * Sff tif KfyPbirGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif stbndbrd string nbmf of tif blgoritim.
     */
    publid String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    privbtf stbtid KfyPbirGfnfrbtor gftInstbndf(Instbndf instbndf,
            String blgoritim) {
        KfyPbirGfnfrbtor kpg;
        if (instbndf.impl instbndfof KfyPbirGfnfrbtor) {
            kpg = (KfyPbirGfnfrbtor)instbndf.impl;
        } flsf {
            KfyPbirGfnfrbtorSpi spi = (KfyPbirGfnfrbtorSpi)instbndf.impl;
            kpg = nfw Dflfgbtf(spi, blgoritim);
        }
        kpg.providfr = instbndf.providfr;
        rfturn kpg;
    }

    /**
     * Rfturns b KfyPbirGfnfrbtor objfdt tibt gfnfrbtfs publid/privbtf
     * kfy pbirs for tif spfdififd blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw KfyPbirGfnfrbtor objfdt fndbpsulbting tif
     * KfyPbirGfnfrbtorSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd string nbmf of tif blgoritim.
     * Sff tif KfyPbirGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw KfyPbirGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          KfyPbirGfnfrbtorSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff Providfr
     */
    publid stbtid KfyPbirGfnfrbtor gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        List<Sfrvidf> list =
                GftInstbndf.gftSfrvidfs("KfyPbirGfnfrbtor", blgoritim);
        Itfrbtor<Sfrvidf> t = list.itfrbtor();
        if (t.ibsNfxt() == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption
                (blgoritim + " KfyPbirGfnfrbtor not bvbilbblf");
        }
        // find b working Spi or KfyPbirGfnfrbtor subdlbss
        NoSudiAlgoritimExdfption fbilurf = null;
        do {
            Sfrvidf s = t.nfxt();
            try {
                Instbndf instbndf =
                    GftInstbndf.gftInstbndf(s, KfyPbirGfnfrbtorSpi.dlbss);
                if (instbndf.impl instbndfof KfyPbirGfnfrbtor) {
                    rfturn gftInstbndf(instbndf, blgoritim);
                } flsf {
                    rfturn nfw Dflfgbtf(instbndf, t, blgoritim);
                }
            } dbtdi (NoSudiAlgoritimExdfption f) {
                if (fbilurf == null) {
                    fbilurf = f;
                }
            }
        } wiilf (t.ibsNfxt());
        tirow fbilurf;
    }

    /**
     * Rfturns b KfyPbirGfnfrbtor objfdt tibt gfnfrbtfs publid/privbtf
     * kfy pbirs for tif spfdififd blgoritim.
     *
     * <p> A nfw KfyPbirGfnfrbtor objfdt fndbpsulbting tif
     * KfyPbirGfnfrbtorSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd string nbmf of tif blgoritim.
     * Sff tif KfyPbirGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif string nbmf of tif providfr.
     *
     * @rfturn tif nfw KfyPbirGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b KfyPbirGfnfrbtorSpi
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
    publid stbtid KfyPbirGfnfrbtor gftInstbndf(String blgoritim,
            String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("KfyPbirGfnfrbtor",
                KfyPbirGfnfrbtorSpi.dlbss, blgoritim, providfr);
        rfturn gftInstbndf(instbndf, blgoritim);
    }

    /**
     * Rfturns b KfyPbirGfnfrbtor objfdt tibt gfnfrbtfs publid/privbtf
     * kfy pbirs for tif spfdififd blgoritim.
     *
     * <p> A nfw KfyPbirGfnfrbtor objfdt fndbpsulbting tif
     * KfyPbirGfnfrbtorSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif stbndbrd string nbmf of tif blgoritim.
     * Sff tif KfyPbirGfnfrbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyPbirGfnfrbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw KfyPbirGfnfrbtor objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b KfyPbirGfnfrbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid KfyPbirGfnfrbtor gftInstbndf(String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("KfyPbirGfnfrbtor",
                KfyPbirGfnfrbtorSpi.dlbss, blgoritim, providfr);
        rfturn gftInstbndf(instbndf, blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis kfy pbir gfnfrbtor objfdt.
     *
     * @rfturn tif providfr of tiis kfy pbir gfnfrbtor objfdt
     */
    publid finbl Providfr gftProvidfr() {
        disbblfFbilovfr();
        rfturn tiis.providfr;
    }

    void disbblfFbilovfr() {
        // fmpty, ovfrriddfn in Dflfgbtf
    }

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor for b dfrtbin kfysizf using
     * b dffbult pbrbmftfr sft bnd tif {@dodf SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority instbllfd providfr bs tif sourdf
     * of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
     * usfd.)
     *
     * @pbrbm kfysizf tif kfysizf. Tiis is bn
     * blgoritim-spfdifid mftrid, sudi bs modulus lfngti, spfdififd in
     * numbfr of bits.
     *
     * @fxdfption InvblidPbrbmftfrExdfption if tif {@dodf kfysizf} is not
     * supportfd by tiis KfyPbirGfnfrbtor objfdt.
     */
    publid void initiblizf(int kfysizf) {
        initiblizf(kfysizf, JCAUtil.gftSfdurfRbndom());
    }

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor for b dfrtbin kfysizf witi
     * tif givfn sourdf of rbndomnfss (bnd b dffbult pbrbmftfr sft).
     *
     * @pbrbm kfysizf tif kfysizf. Tiis is bn
     * blgoritim-spfdifid mftrid, sudi bs modulus lfngti, spfdififd in
     * numbfr of bits.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidPbrbmftfrExdfption if tif {@dodf kfysizf} is not
     * supportfd by tiis KfyPbirGfnfrbtor objfdt.
     *
     * @sindf 1.2
     */
    publid void initiblizf(int kfysizf, SfdurfRbndom rbndom) {
        // Tiis dofs notiing, bfdbusf fitifr
        // 1. tif implfmfntbtion objfdt rfturnfd by gftInstbndf() is bn
        //    instbndf of KfyPbirGfnfrbtor wiidi ibs its own
        //    initiblizf(kfysizf, rbndom) mftiod, so tif bpplidbtion would
        //    bf dblling tibt mftiod dirfdtly, or
        // 2. tif implfmfntbtion rfturnfd by gftInstbndf() is bn instbndf
        //    of Dflfgbtf, in wiidi dbsf initiblizf(kfysizf, rbndom) is
        //    ovfrriddfn to dbll tif dorrfsponding SPI mftiod.
        // (Tiis is b spfdibl dbsf, bfdbusf tif API bnd SPI mftiod ibvf tif
        // sbmf nbmf.)
    }

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor using tif spfdififd pbrbmftfr
     * sft bnd tif {@dodf SfdurfRbndom}
     * implfmfntbtion of tif iigifst-priority instbllfd providfr bs tif sourdf
     * of rbndomnfss.
     * (If nonf of tif instbllfd providfrs supply bn implfmfntbtion of
     * {@dodf SfdurfRbndom}, b systfm-providfd sourdf of rbndomnfss is
     * usfd.).
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss.
     * Tiis mftiod dblls tif KfyPbirGfnfrbtorSpi
     * {@link KfyPbirGfnfrbtorSpi#initiblizf(
     * jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd,
     * jbvb.sfdurity.SfdurfRbndom) initiblizf} mftiod,
     * pbssing it {@dodf pbrbms} bnd b sourdf of rbndomnfss (obtbinfd
     * from tif iigifst-priority instbllfd providfr or systfm-providfd if nonf
     * of tif instbllfd providfrs supply onf).
     * Tibt {@dodf initiblizf} mftiod blwbys tirows bn
     * UnsupportfdOpfrbtionExdfption if it is not ovfrriddfn by tif providfr.
     *
     * @pbrbm pbrbms tif pbrbmftfr sft usfd to gfnfrbtf tif kfys.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy pbir gfnfrbtor.
     *
     * @sindf 1.2
     */
    publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        initiblizf(pbrbms, JCAUtil.gftSfdurfRbndom());
    }

    /**
     * Initiblizfs tif kfy pbir gfnfrbtor witi tif givfn pbrbmftfr
     * sft bnd sourdf of rbndomnfss.
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss.
     * Tiis mftiod dblls tif KfyPbirGfnfrbtorSpi {@link
     * KfyPbirGfnfrbtorSpi#initiblizf(
     * jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd,
     * jbvb.sfdurity.SfdurfRbndom) initiblizf} mftiod,
     * pbssing it {@dodf pbrbms} bnd {@dodf rbndom}.
     * Tibt {@dodf initiblizf}
     * mftiod blwbys tirows bn
     * UnsupportfdOpfrbtionExdfption if it is not ovfrriddfn by tif providfr.
     *
     * @pbrbm pbrbms tif pbrbmftfr sft usfd to gfnfrbtf tif kfys.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy pbir gfnfrbtor.
     *
     * @sindf 1.2
     */
    publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms,
                           SfdurfRbndom rbndom)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        // Tiis dofs notiing, bfdbusf fitifr
        // 1. tif implfmfntbtion objfdt rfturnfd by gftInstbndf() is bn
        //    instbndf of KfyPbirGfnfrbtor wiidi ibs its own
        //    initiblizf(pbrbms, rbndom) mftiod, so tif bpplidbtion would
        //    bf dblling tibt mftiod dirfdtly, or
        // 2. tif implfmfntbtion rfturnfd by gftInstbndf() is bn instbndf
        //    of Dflfgbtf, in wiidi dbsf initiblizf(pbrbms, rbndom) is
        //    ovfrriddfn to dbll tif dorrfsponding SPI mftiod.
        // (Tiis is b spfdibl dbsf, bfdbusf tif API bnd SPI mftiod ibvf tif
        // sbmf nbmf.)
    }

    /**
     * Gfnfrbtfs b kfy pbir.
     *
     * <p>If tiis KfyPbirGfnfrbtor ibs not bffn initiblizfd fxpliditly,
     * providfr-spfdifid dffbults will bf usfd for tif sizf bnd otifr
     * (blgoritim-spfdifid) vblufs of tif gfnfrbtfd kfys.
     *
     * <p>Tiis will gfnfrbtf b nfw kfy pbir fvfry timf it is dbllfd.
     *
     * <p>Tiis mftiod is fundtionblly fquivblfnt to
     * {@link #gfnfrbtfKfyPbir() gfnfrbtfKfyPbir}.
     *
     * @rfturn tif gfnfrbtfd kfy pbir
     *
     * @sindf 1.2
     */
    publid finbl KfyPbir gfnKfyPbir() {
        rfturn gfnfrbtfKfyPbir();
    }

    /**
     * Gfnfrbtfs b kfy pbir.
     *
     * <p>If tiis KfyPbirGfnfrbtor ibs not bffn initiblizfd fxpliditly,
     * providfr-spfdifid dffbults will bf usfd for tif sizf bnd otifr
     * (blgoritim-spfdifid) vblufs of tif gfnfrbtfd kfys.
     *
     * <p>Tiis will gfnfrbtf b nfw kfy pbir fvfry timf it is dbllfd.
     *
     * <p>Tiis mftiod is fundtionblly fquivblfnt to
     * {@link #gfnKfyPbir() gfnKfyPbir}.
     *
     * @rfturn tif gfnfrbtfd kfy pbir
     */
    publid KfyPbir gfnfrbtfKfyPbir() {
        // Tiis dofs notiing (fxdfpt rfturning null), bfdbusf fitifr:
        //
        // 1. tif implfmfntbtion objfdt rfturnfd by gftInstbndf() is bn
        //    instbndf of KfyPbirGfnfrbtor wiidi ibs its own implfmfntbtion
        //    of gfnfrbtfKfyPbir (ovfrriding tiis onf), so tif bpplidbtion
        //    would bf dblling tibt mftiod dirfdtly, or
        //
        // 2. tif implfmfntbtion rfturnfd by gftInstbndf() is bn instbndf
        //    of Dflfgbtf, in wiidi dbsf gfnfrbtfKfyPbir is
        //    ovfrriddfn to invokf tif dorrfsponding SPI mftiod.
        //
        // (Tiis is b spfdibl dbsf, bfdbusf in JDK 1.1.x tif gfnfrbtfKfyPbir
        // mftiod wbs usfd boti bs bn API bnd b SPI mftiod.)
        rfturn null;
    }


    /*
     * Tif following dlbss bllows providfrs to fxtfnd from KfyPbirGfnfrbtorSpi
     * rbtifr tibn from KfyPbirGfnfrbtor. It rfprfsfnts b KfyPbirGfnfrbtor
     * witi bn fndbpsulbtfd, providfr-supplifd SPI objfdt (of typf
     * KfyPbirGfnfrbtorSpi).
     * If tif providfr implfmfntbtion is bn instbndf of KfyPbirGfnfrbtorSpi,
     * tif gftInstbndf() mftiods bbovf rfturn bn instbndf of tiis dlbss, witi
     * tif SPI objfdt fndbpsulbtfd.
     *
     * Notf: All SPI mftiods from tif originbl KfyPbirGfnfrbtor dlbss ibvf bffn
     * movfd up tif iifrbrdiy into b nfw dlbss (KfyPbirGfnfrbtorSpi), wiidi ibs
     * bffn intfrposfd in tif iifrbrdiy bftwffn tif API (KfyPbirGfnfrbtor)
     * bnd its originbl pbrfnt (Objfdt).
     */

    //
    // frror fbilovfr notfs:
    //
    //  . wf fbilovfr if tif implfmfntbtion tirows bn frror during init
    //    by rftrying tif init on otifr providfrs
    //
    //  . wf blso fbilovfr if tif init suddffdfd but tif subsfqufnt dbll
    //    to gfnfrbtfKfyPbir() fbils. In ordfr for tiis to work, wf nffd
    //    to rfmfmbfr tif pbrbmftfrs to tif lbst suddfssful dbll to init
    //    bnd initiblizf() tif nfxt spi using tifm.
    //
    //  . bltiougi not spfdififd, KfyPbirGfnfrbtors dould bf tirfbd sbff,
    //    so wf mbkf surf wf do not intfrffrf witi tibt
    //
    //  . fbilovfr is not bvbilbblf, if:
    //    . gftInstbndf(blgoritim, providfr) wbs usfd
    //    . b providfr fxtfnds KfyPbirGfnfrbtor rbtifr tibn
    //      KfyPbirGfnfrbtorSpi (JDK 1.1 stylf)
    //    . ondf gftProvidfr() is dbllfd
    //

    privbtf stbtid finbl dlbss Dflfgbtf fxtfnds KfyPbirGfnfrbtor {

        // Tif providfr implfmfntbtion (dflfgbtf)
        privbtf volbtilf KfyPbirGfnfrbtorSpi spi;

        privbtf finbl Objfdt lodk = nfw Objfdt();

        privbtf Itfrbtor<Sfrvidf> sfrvidfItfrbtor;

        privbtf finbl stbtid int I_NONE   = 1;
        privbtf finbl stbtid int I_SIZE   = 2;
        privbtf finbl stbtid int I_PARAMS = 3;

        privbtf int initTypf;
        privbtf int initKfySizf;
        privbtf AlgoritimPbrbmftfrSpfd initPbrbms;
        privbtf SfdurfRbndom initRbndom;

        // donstrudtor
        Dflfgbtf(KfyPbirGfnfrbtorSpi spi, String blgoritim) {
            supfr(blgoritim);
            tiis.spi = spi;
        }

        Dflfgbtf(Instbndf instbndf, Itfrbtor<Sfrvidf> sfrvidfItfrbtor,
                String blgoritim) {
            supfr(blgoritim);
            spi = (KfyPbirGfnfrbtorSpi)instbndf.impl;
            providfr = instbndf.providfr;
            tiis.sfrvidfItfrbtor = sfrvidfItfrbtor;
            initTypf = I_NONE;
        }

        /**
         * Updbtf tif bdtivf spi of tiis dlbss bnd rfturn tif nfxt
         * implfmfntbtion for fbilovfr. If no morf implfmfnbtions brf
         * bvbilbblf, tiis mftiod rfturns null. Howfvfr, tif bdtivf spi of
         * tiis dlbss is nfvfr sft to null.
         */
        privbtf KfyPbirGfnfrbtorSpi nfxtSpi(KfyPbirGfnfrbtorSpi oldSpi,
                boolfbn rfinit) {
            syndironizfd (lodk) {
                // somfbody flsf did b fbilovfr dondurrfntly
                // try tibt spi now
                if ((oldSpi != null) && (oldSpi != spi)) {
                    rfturn spi;
                }
                if (sfrvidfItfrbtor == null) {
                    rfturn null;
                }
                wiilf (sfrvidfItfrbtor.ibsNfxt()) {
                    Sfrvidf s = sfrvidfItfrbtor.nfxt();
                    try {
                        Objfdt inst = s.nfwInstbndf(null);
                        // ignorf non-spis
                        if (inst instbndfof KfyPbirGfnfrbtorSpi == fblsf) {
                            dontinuf;
                        }
                        if (inst instbndfof KfyPbirGfnfrbtor) {
                            dontinuf;
                        }
                        KfyPbirGfnfrbtorSpi spi = (KfyPbirGfnfrbtorSpi)inst;
                        if (rfinit) {
                            if (initTypf == I_SIZE) {
                                spi.initiblizf(initKfySizf, initRbndom);
                            } flsf if (initTypf == I_PARAMS) {
                                spi.initiblizf(initPbrbms, initRbndom);
                            } flsf if (initTypf != I_NONE) {
                                tirow nfw AssfrtionError
                                    ("KfyPbirGfnfrbtor initTypf: " + initTypf);
                            }
                        }
                        providfr = s.gftProvidfr();
                        tiis.spi = spi;
                        rfturn spi;
                    } dbtdi (Exdfption f) {
                        // ignorf
                    }
                }
                disbblfFbilovfr();
                rfturn null;
            }
        }

        void disbblfFbilovfr() {
            sfrvidfItfrbtor = null;
            initTypf = 0;
            initPbrbms = null;
            initRbndom = null;
        }

        // fnginf mftiod
        publid void initiblizf(int kfysizf, SfdurfRbndom rbndom) {
            if (sfrvidfItfrbtor == null) {
                spi.initiblizf(kfysizf, rbndom);
                rfturn;
            }
            RuntimfExdfption fbilurf = null;
            KfyPbirGfnfrbtorSpi mySpi = spi;
            do {
                try {
                    mySpi.initiblizf(kfysizf, rbndom);
                    initTypf = I_SIZE;
                    initKfySizf = kfysizf;
                    initPbrbms = null;
                    initRbndom = rbndom;
                    rfturn;
                } dbtdi (RuntimfExdfption f) {
                    if (fbilurf == null) {
                        fbilurf = f;
                    }
                    mySpi = nfxtSpi(mySpi, fblsf);
                }
            } wiilf (mySpi != null);
            tirow fbilurf;
        }

        // fnginf mftiod
        publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms,
                SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
            if (sfrvidfItfrbtor == null) {
                spi.initiblizf(pbrbms, rbndom);
                rfturn;
            }
            Exdfption fbilurf = null;
            KfyPbirGfnfrbtorSpi mySpi = spi;
            do {
                try {
                    mySpi.initiblizf(pbrbms, rbndom);
                    initTypf = I_PARAMS;
                    initKfySizf = 0;
                    initPbrbms = pbrbms;
                    initRbndom = rbndom;
                    rfturn;
                } dbtdi (Exdfption f) {
                    if (fbilurf == null) {
                        fbilurf = f;
                    }
                    mySpi = nfxtSpi(mySpi, fblsf);
                }
            } wiilf (mySpi != null);
            if (fbilurf instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)fbilurf;
            }
            // must bf bn InvblidAlgoritimPbrbmftfrExdfption
            tirow (InvblidAlgoritimPbrbmftfrExdfption)fbilurf;
        }

        // fnginf mftiod
        publid KfyPbir gfnfrbtfKfyPbir() {
            if (sfrvidfItfrbtor == null) {
                rfturn spi.gfnfrbtfKfyPbir();
            }
            RuntimfExdfption fbilurf = null;
            KfyPbirGfnfrbtorSpi mySpi = spi;
            do {
                try {
                    rfturn mySpi.gfnfrbtfKfyPbir();
                } dbtdi (RuntimfExdfption f) {
                    if (fbilurf == null) {
                        fbilurf = f;
                    }
                    mySpi = nfxtSpi(mySpi, truf);
                }
            } wiilf (mySpi != null);
            tirow fbilurf;
        }
    }

}
