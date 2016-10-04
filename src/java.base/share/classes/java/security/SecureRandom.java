/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.rfgfx.*;

import jbvb.sfdurity.Providfr.Sfrvidf;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tiis dlbss providfs b dryptogrbpiidblly strong rbndom numbfr
 * gfnfrbtor (RNG).
 *
 * <p>A dryptogrbpiidblly strong rbndom numbfr
 * minimblly domplifs witi tif stbtistidbl rbndom numbfr gfnfrbtor tfsts
 * spfdififd in <b irff="ittp://dsrd.nist.gov/dryptvbl/140-2.itm">
 * <i>FIPS 140-2, Sfdurity Rfquirfmfnts for Cryptogrbpiid Modulfs</i></b>,
 * sfdtion 4.9.1.
 * Additionblly, SfdurfRbndom must produdf non-dftfrministid output.
 * Tifrfforf bny sffd mbtfribl pbssfd to b SfdurfRbndom objfdt must bf
 * unprfdidtbblf, bnd bll SfdurfRbndom output sfqufndfs must bf
 * dryptogrbpiidblly strong, bs dfsdribfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd1750.txt">
 * <i>RFC 1750: Rbndomnfss Rfdommfndbtions for Sfdurity</i></b>.
 *
 * <p>A dbllfr obtbins b SfdurfRbndom instbndf vib tif
 * no-brgumfnt donstrudtor or onf of tif {@dodf gftInstbndf} mftiods:
 *
 * <prf>
 *      SfdurfRbndom rbndom = nfw SfdurfRbndom();
 * </prf>
 *
 * <p> Mbny SfdurfRbndom implfmfntbtions brf in tif form of b psfudo-rbndom
 * numbfr gfnfrbtor (PRNG), wiidi mfbns tify usf b dftfrministid blgoritim
 * to produdf b psfudo-rbndom sfqufndf from b truf rbndom sffd.
 * Otifr implfmfntbtions mby produdf truf rbndom numbfrs,
 * bnd yft otifrs mby usf b dombinbtion of boti tfdiniqufs.
 *
 * <p> Typidbl dbllfrs of SfdurfRbndom invokf tif following mftiods
 * to rftrifvf rbndom bytfs:
 *
 * <prf>
 *      SfdurfRbndom rbndom = nfw SfdurfRbndom();
 *      bytf bytfs[] = nfw bytf[20];
 *      rbndom.nfxtBytfs(bytfs);
 * </prf>
 *
 * <p> Cbllfrs mby blso invokf tif {@dodf gfnfrbtfSffd} mftiod
 * to gfnfrbtf b givfn numbfr of sffd bytfs (to sffd otifr rbndom numbfr
 * gfnfrbtors, for fxbmplf):
 * <prf>
 *      bytf sffd[] = rbndom.gfnfrbtfSffd(20);
 * </prf>
 *
 * Notf: Dfpfnding on tif implfmfntbtion, tif {@dodf gfnfrbtfSffd} bnd
 * {@dodf nfxtBytfs} mftiods mby blodk bs fntropy is bfing gbtifrfd,
 * for fxbmplf, if tify nffd to rfbd from /dfv/rbndom on vbrious Unix-likf
 * opfrbting systfms.
 *
 * @sff jbvb.sfdurity.SfdurfRbndomSpi
 * @sff jbvb.util.Rbndom
 *
 * @butior Bfnjbmin Rfnbud
 * @butior Josi Blodi
 */

publid dlbss SfdurfRbndom fxtfnds jbvb.util.Rbndom {

    /**
     * Tif providfr.
     *
     * @sfribl
     * @sindf 1.2
     */
    privbtf Providfr providfr = null;

    /**
     * Tif providfr implfmfntbtion.
     *
     * @sfribl
     * @sindf 1.2
     */
    privbtf SfdurfRbndomSpi sfdurfRbndomSpi = null;

    /*
     * Tif blgoritim nbmf of null if unknown.
     *
     * @sfribl
     * @sindf 1.5
     */
    privbtf String blgoritim;

    // Sffd Gfnfrbtor
    privbtf stbtid volbtilf SfdurfRbndom sffdGfnfrbtor = null;

    /**
     * Construdts b sfdurf rbndom numbfr gfnfrbtor (RNG) implfmfnting tif
     * dffbult rbndom numbfr blgoritim.
     *
     * <p> Tiis donstrudtor trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw SfdurfRbndom objfdt fndbpsulbting tif
     * SfdurfRbndomSpi implfmfntbtion from tif first
     * Providfr tibt supports b SfdurfRbndom (RNG) blgoritim is rfturnfd.
     * If nonf of tif Providfrs support b RNG blgoritim,
     * tifn bn implfmfntbtion-spfdifid dffbult is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p> Sff tif SfdurfRbndom sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SfdurfRbndom">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd RNG blgoritim nbmfs.
     *
     * <p> Tif rfturnfd SfdurfRbndom objfdt ibs not bffn sffdfd.  To sffd tif
     * rfturnfd objfdt, dbll tif {@dodf sftSffd} mftiod.
     * If {@dodf sftSffd} is not dbllfd, tif first dbll to
     * {@dodf nfxtBytfs} will fordf tif SfdurfRbndom objfdt to sffd itsflf.
     * Tiis sflf-sffding will not oddur if {@dodf sftSffd} wbs
     * prfviously dbllfd.
     */
    publid SfdurfRbndom() {
        /*
         * Tiis dbll to our supfrdlbss donstrudtor will rfsult in b dbll
         * to our own {@dodf sftSffd} mftiod, wiidi will rfturn
         * immfdibtfly wifn it is pbssfd zfro.
         */
        supfr(0);
        gftDffbultPRNG(fblsf, null);
    }

    /**
     * Construdts b sfdurf rbndom numbfr gfnfrbtor (RNG) implfmfnting tif
     * dffbult rbndom numbfr blgoritim.
     * Tif SfdurfRbndom instbndf is sffdfd witi tif spfdififd sffd bytfs.
     *
     * <p> Tiis donstrudtor trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw SfdurfRbndom objfdt fndbpsulbting tif
     * SfdurfRbndomSpi implfmfntbtion from tif first
     * Providfr tibt supports b SfdurfRbndom (RNG) blgoritim is rfturnfd.
     * If nonf of tif Providfrs support b RNG blgoritim,
     * tifn bn implfmfntbtion-spfdifid dffbult is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p> Sff tif SfdurfRbndom sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SfdurfRbndom">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd RNG blgoritim nbmfs.
     *
     * @pbrbm sffd tif sffd.
     */
    publid SfdurfRbndom(bytf sffd[]) {
        supfr(0);
        gftDffbultPRNG(truf, sffd);
    }

    privbtf void gftDffbultPRNG(boolfbn sftSffd, bytf[] sffd) {
        String prng = gftPrngAlgoritim();
        if (prng == null) {
            // bummfr, gft tif SUN implfmfntbtion
            prng = "SHA1PRNG";
            tiis.sfdurfRbndomSpi = nfw sun.sfdurity.providfr.SfdurfRbndom();
            tiis.providfr = Providfrs.gftSunProvidfr();
            if (sftSffd) {
                tiis.sfdurfRbndomSpi.fnginfSftSffd(sffd);
            }
        } flsf {
            try {
                SfdurfRbndom rbndom = SfdurfRbndom.gftInstbndf(prng);
                tiis.sfdurfRbndomSpi = rbndom.gftSfdurfRbndomSpi();
                tiis.providfr = rbndom.gftProvidfr();
                if (sftSffd) {
                    tiis.sfdurfRbndomSpi.fnginfSftSffd(sffd);
                }
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                // nfvfr ibppfns, bfdbusf wf mbdf surf tif blgoritim fxists
                tirow nfw RuntimfExdfption(nsbf);
            }
        }
        // JDK 1.1 bbsfd implfmfntbtions subdlbss SfdurfRbndom instfbd of
        // SfdurfRbndomSpi. Tify will blso go tirougi tiis dodf pbti bfdbusf
        // tify must dbll b SfdurfRbndom donstrudtor bs it is tifir supfrdlbss.
        // If wf brf dfbling witi sudi bn implfmfntbtion, do not sft tif
        // blgoritim vbluf bs it would bf inbddurbtf.
        if (gftClbss() == SfdurfRbndom.dlbss) {
            tiis.blgoritim = prng;
        }
    }

    /**
     * Crfbtfs b SfdurfRbndom objfdt.
     *
     * @pbrbm sfdurfRbndomSpi tif SfdurfRbndom implfmfntbtion.
     * @pbrbm providfr tif providfr.
     */
    protfdtfd SfdurfRbndom(SfdurfRbndomSpi sfdurfRbndomSpi,
                           Providfr providfr) {
        tiis(sfdurfRbndomSpi, providfr, null);
    }

    privbtf SfdurfRbndom(SfdurfRbndomSpi sfdurfRbndomSpi, Providfr providfr,
            String blgoritim) {
        supfr(0);
        tiis.sfdurfRbndomSpi = sfdurfRbndomSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns b SfdurfRbndom objfdt tibt implfmfnts tif spfdififd
     * Rbndom Numbfr Gfnfrbtor (RNG) blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw SfdurfRbndom objfdt fndbpsulbting tif
     * SfdurfRbndomSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p> Tif rfturnfd SfdurfRbndom objfdt ibs not bffn sffdfd.  To sffd tif
     * rfturnfd objfdt, dbll tif {@dodf sftSffd} mftiod.
     * If {@dodf sftSffd} is not dbllfd, tif first dbll to
     * {@dodf nfxtBytfs} will fordf tif SfdurfRbndom objfdt to sffd itsflf.
     * Tiis sflf-sffding will not oddur if {@dodf sftSffd} wbs
     * prfviously dbllfd.
     *
     * @pbrbm blgoritim tif nbmf of tif RNG blgoritim.
     * Sff tif SfdurfRbndom sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SfdurfRbndom">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd RNG blgoritim nbmfs.
     *
     * @rfturn tif nfw SfdurfRbndom objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          SfdurfRbndomSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff Providfr
     *
     * @sindf 1.2
     */
    publid stbtid SfdurfRbndom gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("SfdurfRbndom",
            SfdurfRbndomSpi.dlbss, blgoritim);
        rfturn nfw SfdurfRbndom((SfdurfRbndomSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b SfdurfRbndom objfdt tibt implfmfnts tif spfdififd
     * Rbndom Numbfr Gfnfrbtor (RNG) blgoritim.
     *
     * <p> A nfw SfdurfRbndom objfdt fndbpsulbting tif
     * SfdurfRbndomSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p> Tif rfturnfd SfdurfRbndom objfdt ibs not bffn sffdfd.  To sffd tif
     * rfturnfd objfdt, dbll tif {@dodf sftSffd} mftiod.
     * If {@dodf sftSffd} is not dbllfd, tif first dbll to
     * {@dodf nfxtBytfs} will fordf tif SfdurfRbndom objfdt to sffd itsflf.
     * Tiis sflf-sffding will not oddur if {@dodf sftSffd} wbs
     * prfviously dbllfd.
     *
     * @pbrbm blgoritim tif nbmf of tif RNG blgoritim.
     * Sff tif SfdurfRbndom sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SfdurfRbndom">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd RNG blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw SfdurfRbndom objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b SfdurfRbndomSpi
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
     *
     * @sindf 1.2
     */
    publid stbtid SfdurfRbndom gftInstbndf(String blgoritim, String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("SfdurfRbndom",
            SfdurfRbndomSpi.dlbss, blgoritim, providfr);
        rfturn nfw SfdurfRbndom((SfdurfRbndomSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b SfdurfRbndom objfdt tibt implfmfnts tif spfdififd
     * Rbndom Numbfr Gfnfrbtor (RNG) blgoritim.
     *
     * <p> A nfw SfdurfRbndom objfdt fndbpsulbting tif
     * SfdurfRbndomSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * <p> Tif rfturnfd SfdurfRbndom objfdt ibs not bffn sffdfd.  To sffd tif
     * rfturnfd objfdt, dbll tif {@dodf sftSffd} mftiod.
     * If {@dodf sftSffd} is not dbllfd, tif first dbll to
     * {@dodf nfxtBytfs} will fordf tif SfdurfRbndom objfdt to sffd itsflf.
     * Tiis sflf-sffding will not oddur if {@dodf sftSffd} wbs
     * prfviously dbllfd.
     *
     * @pbrbm blgoritim tif nbmf of tif RNG blgoritim.
     * Sff tif SfdurfRbndom sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#SfdurfRbndom">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd RNG blgoritim nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw SfdurfRbndom objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b SfdurfRbndomSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid SfdurfRbndom gftInstbndf(String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("SfdurfRbndom",
            SfdurfRbndomSpi.dlbss, blgoritim, providfr);
        rfturn nfw SfdurfRbndom((SfdurfRbndomSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns tif SfdurfRbndomSpi of tiis SfdurfRbndom objfdt.
     */
    SfdurfRbndomSpi gftSfdurfRbndomSpi() {
        rfturn sfdurfRbndomSpi;
    }

    /**
     * Rfturns tif providfr of tiis SfdurfRbndom objfdt.
     *
     * @rfturn tif providfr of tiis SfdurfRbndom objfdt.
     */
    publid finbl Providfr gftProvidfr() {
        rfturn providfr;
    }

    /**
     * Rfturns tif nbmf of tif blgoritim implfmfntfd by tiis SfdurfRbndom
     * objfdt.
     *
     * @rfturn tif nbmf of tif blgoritim or {@dodf unknown}
     *          if tif blgoritim nbmf dbnnot bf dftfrminfd.
     * @sindf 1.5
     */
    publid String gftAlgoritim() {
        rfturn (blgoritim != null) ? blgoritim : "unknown";
    }

    /**
     * Rfsffds tiis rbndom objfdt. Tif givfn sffd supplfmfnts, rbtifr tibn
     * rfplbdfs, tif fxisting sffd. Tius, rfpfbtfd dblls brf gubrbntffd
     * nfvfr to rfdudf rbndomnfss.
     *
     * @pbrbm sffd tif sffd.
     *
     * @sff #gftSffd
     */
    syndironizfd publid void sftSffd(bytf[] sffd) {
        sfdurfRbndomSpi.fnginfSftSffd(sffd);
    }

    /**
     * Rfsffds tiis rbndom objfdt, using tif figit bytfs dontbinfd
     * in tif givfn {@dodf long sffd}. Tif givfn sffd supplfmfnts,
     * rbtifr tibn rfplbdfs, tif fxisting sffd. Tius, rfpfbtfd dblls
     * brf gubrbntffd nfvfr to rfdudf rbndomnfss.
     *
     * <p>Tiis mftiod is dffinfd for dompbtibility witi
     * {@dodf jbvb.util.Rbndom}.
     *
     * @pbrbm sffd tif sffd.
     *
     * @sff #gftSffd
     */
    @Ovfrridf
    publid void sftSffd(long sffd) {
        /*
         * Ignorf dbll from supfr donstrudtor (bs wfll bs bny otifr dblls
         * unfortunbtf fnougi to bf pbssing 0).  It's dritidbl tibt wf
         * ignorf dbll from supfrdlbss donstrudtor, bs digfst ibs not
         * yft bffn initiblizfd bt tibt point.
         */
        if (sffd != 0) {
            sfdurfRbndomSpi.fnginfSftSffd(longToBytfArrby(sffd));
        }
    }

    /**
     * Gfnfrbtfs b usfr-spfdififd numbfr of rbndom bytfs.
     *
     * <p> If b dbll to {@dodf sftSffd} ibd not oddurrfd prfviously,
     * tif first dbll to tiis mftiod fordfs tiis SfdurfRbndom objfdt
     * to sffd itsflf.  Tiis sflf-sffding will not oddur if
     * {@dodf sftSffd} wbs prfviously dbllfd.
     *
     * @pbrbm bytfs tif brrby to bf fillfd in witi rbndom bytfs.
     */
    @Ovfrridf
    syndironizfd publid void nfxtBytfs(bytf[] bytfs) {
        sfdurfRbndomSpi.fnginfNfxtBytfs(bytfs);
    }

    /**
     * Gfnfrbtfs bn intfgfr dontbining tif usfr-spfdififd numbfr of
     * psfudo-rbndom bits (rigit justififd, witi lfbding zfros).  Tiis
     * mftiod ovfrridfs b {@dodf jbvb.util.Rbndom} mftiod, bnd sfrvfs
     * to providf b sourdf of rbndom bits to bll of tif mftiods inifritfd
     * from tibt dlbss (for fxbmplf, {@dodf nfxtInt},
     * {@dodf nfxtLong}, bnd {@dodf nfxtFlobt}).
     *
     * @pbrbm numBits numbfr of psfudo-rbndom bits to bf gfnfrbtfd, wifrf
     * {@dodf 0 <= numBits <= 32}.
     *
     * @rfturn bn {@dodf int} dontbining tif usfr-spfdififd numbfr
     * of psfudo-rbndom bits (rigit justififd, witi lfbding zfros).
     */
    @Ovfrridf
    finbl protfdtfd int nfxt(int numBits) {
        int numBytfs = (numBits+7)/8;
        bytf b[] = nfw bytf[numBytfs];
        int nfxt = 0;

        nfxtBytfs(b);
        for (int i = 0; i < numBytfs; i++) {
            nfxt = (nfxt << 8) + (b[i] & 0xFF);
        }

        rfturn nfxt >>> (numBytfs*8 - numBits);
    }

    /**
     * Rfturns tif givfn numbfr of sffd bytfs, domputfd using tif sffd
     * gfnfrbtion blgoritim tibt tiis dlbss usfs to sffd itsflf.  Tiis
     * dbll mby bf usfd to sffd otifr rbndom numbfr gfnfrbtors.
     *
     * <p>Tiis mftiod is only indludfd for bbdkwbrds dompbtibility.
     * Tif dbllfr is fndourbgfd to usf onf of tif bltfrnbtivf
     * {@dodf gftInstbndf} mftiods to obtbin b SfdurfRbndom objfdt, bnd
     * tifn dbll tif {@dodf gfnfrbtfSffd} mftiod to obtbin sffd bytfs
     * from tibt objfdt.
     *
     * @pbrbm numBytfs tif numbfr of sffd bytfs to gfnfrbtf.
     *
     * @rfturn tif sffd bytfs.
     *
     * @sff #sftSffd
     */
    publid stbtid bytf[] gftSffd(int numBytfs) {
        if (sffdGfnfrbtor == null) {
            sffdGfnfrbtor = nfw SfdurfRbndom();
        }
        rfturn sffdGfnfrbtor.gfnfrbtfSffd(numBytfs);
    }

    /**
     * Rfturns tif givfn numbfr of sffd bytfs, domputfd using tif sffd
     * gfnfrbtion blgoritim tibt tiis dlbss usfs to sffd itsflf.  Tiis
     * dbll mby bf usfd to sffd otifr rbndom numbfr gfnfrbtors.
     *
     * @pbrbm numBytfs tif numbfr of sffd bytfs to gfnfrbtf.
     *
     * @rfturn tif sffd bytfs.
     */
    publid bytf[] gfnfrbtfSffd(int numBytfs) {
        rfturn sfdurfRbndomSpi.fnginfGfnfrbtfSffd(numBytfs);
    }

    /**
     * Hflpfr fundtion to donvfrt b long into b bytf brrby (lfbst signifidbnt
     * bytf first).
     */
    privbtf stbtid bytf[] longToBytfArrby(long l) {
        bytf[] rftVbl = nfw bytf[8];

        for (int i = 0; i < 8; i++) {
            rftVbl[i] = (bytf) l;
            l >>= 8;
        }

        rfturn rftVbl;
    }

    /**
     * Gfts b dffbult PRNG blgoritim by looking tirougi bll rfgistfrfd
     * providfrs. Rfturns tif first PRNG blgoritim of tif first providfr tibt
     * ibs rfgistfrfd b SfdurfRbndom implfmfntbtion, or null if nonf of tif
     * rfgistfrfd providfrs supplifs b SfdurfRbndom implfmfntbtion.
     */
    privbtf stbtid String gftPrngAlgoritim() {
        for (Providfr p : Providfrs.gftProvidfrList().providfrs()) {
            for (Sfrvidf s : p.gftSfrvidfs()) {
                if (s.gftTypf().fqubls("SfdurfRbndom")) {
                    rfturn s.gftAlgoritim();
                }
            }
        }
        rfturn null;
    }

    /*
     * Lbzily initiblizf sindf Pbttfrn.dompilf() is ifbvy.
     * Efffdtivf Jbvb (2nd Edition), Itfm 71.
     */
    privbtf stbtid finbl dlbss StrongPbttfrnHoldfr {
        /*
         * Entrifs brf blg:prov sfpbrbtfd by ,
         * Allow for prfpfndfd/bppfndfd wiitfspbdf bftwffn fntrifs.
         *
         * Cbpturf groups:
         *     1 - blg
         *     2 - :prov (optionbl)
         *     3 - prov (optionbl)
         *     4 - ,nfxtEntry (optionbl)
         *     5 - nfxtEntry (optionbl)
         */
        privbtf stbtid Pbttfrn pbttfrn =
            Pbttfrn.dompilf(
                "\\s*([\\S&&[^:,]]*)(\\:([\\S&&[^,]]*))?\\s*(\\,(.*))?");
    }

    /**
     * Rfturns b {@dodf SfdurfRbndom} objfdt tibt wbs sflfdtfd by using
     * tif blgoritims/providfrs spfdififd in tif {@dodf
     * sfdurfrbndom.strongAlgoritims} {@link Sfdurity} propfrty.
     * <p>
     * Somf situbtions rfquirf strong rbndom vblufs, sudi bs wifn
     * drfbting iigi-vbluf/long-livfd sfdrfts likf RSA publid/privbtf
     * kfys.  To iflp guidf bpplidbtions in sflfdting b suitbblf strong
     * {@dodf SfdurfRbndom} implfmfntbtion, Jbvb distributions
     * indludf b list of known strong {@dodf SfdurfRbndom}
     * implfmfntbtions in tif {@dodf sfdurfrbndom.strongAlgoritims}
     * Sfdurity propfrty.
     * <p>
     * Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to
     * support bt lfbst onf strong {@dodf SfdurfRbndom} implfmfntbtion.
     *
     * @rfturn b strong {@dodf SfdurfRbndom} implfmfntbtion bs indidbtfd
     * by tif {@dodf sfdurfrbndom.strongAlgoritims} Sfdurity propfrty
     *
     * @tirows NoSudiAlgoritimExdfption if no blgoritim is bvbilbblf
     *
     * @sff Sfdurity#gftPropfrty(String)
     *
     * @sindf 1.8
     */
    publid stbtid SfdurfRbndom gftInstbndfStrong()
            tirows NoSudiAlgoritimExdfption {

        String propfrty = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                @Ovfrridf
                publid String run() {
                    rfturn Sfdurity.gftPropfrty(
                        "sfdurfrbndom.strongAlgoritims");
                }
            });

        if ((propfrty == null) || (propfrty.lfngti() == 0)) {
            tirow nfw NoSudiAlgoritimExdfption(
                "Null/fmpty sfdurfrbndom.strongAlgoritims Sfdurity Propfrty");
        }

        String rfmbindfr = propfrty;
        wiilf (rfmbindfr != null) {
            Mbtdifr m;
            if ((m = StrongPbttfrnHoldfr.pbttfrn.mbtdifr(
                    rfmbindfr)).mbtdifs()) {

                String blg = m.group(1);
                String prov = m.group(3);

                try {
                    if (prov == null) {
                        rfturn SfdurfRbndom.gftInstbndf(blg);
                    } flsf {
                        rfturn SfdurfRbndom.gftInstbndf(blg, prov);
                    }
                } dbtdi (NoSudiAlgoritimExdfption |
                        NoSudiProvidfrExdfption f) {
                }
                rfmbindfr = m.group(5);
            } flsf {
                rfmbindfr = null;
            }
        }

        tirow nfw NoSudiAlgoritimExdfption(
            "No strong SfdurfRbndom impls bvbilbblf: " + propfrty);
    }

    // Dfdlbrf sfriblVfrsionUID to bf dompbtiblf witi JDK1.1
    stbtid finbl long sfriblVfrsionUID = 4940670005562187L;

    // Rftbin unusfd vblufs sfriblizfd from JDK1.1
    /**
     * @sfribl
     */
    privbtf bytf[] stbtf;
    /**
     * @sfribl
     */
    privbtf MfssbgfDigfst digfst = null;
    /**
     * @sfribl
     *
     * Wf know tibt tif MfssbgfDigfst dlbss dofs not implfmfnt
     * jbvb.io.Sfriblizbblf.  Howfvfr, sindf tiis fifld is no longfr
     * usfd, it will blwbys bf NULL bnd won't bfffdt tif sfriblizbtion
     * of tif SfdurfRbndom dlbss itsflf.
     */
    privbtf bytf[] rbndomBytfs;
    /**
     * @sfribl
     */
    privbtf int rbndomBytfsUsfd;
    /**
     * @sfribl
     */
    privbtf long dountfr;
}
