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

import jbvb.io.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;

/**
 * Tiis dlbss is usfd bs bn opbquf rfprfsfntbtion of dryptogrbpiid pbrbmftfrs.
 *
 * <p>An {@dodf AlgoritimPbrbmftfrs} objfdt for mbnbging tif pbrbmftfrs
 * for b pbrtidulbr blgoritim dbn bf obtbinfd by
 * dblling onf of tif {@dodf gftInstbndf} fbdtory mftiods
 * (stbtid mftiods tibt rfturn instbndfs of b givfn dlbss).
 *
 * <p>Ondf bn {@dodf AlgoritimPbrbmftfrs} objfdt is obtbinfd, it must bf
 * initiblizfd vib b dbll to {@dodf init}, using bn bppropribtf pbrbmftfr
 * spfdifidbtion or pbrbmftfr fndoding.
 *
 * <p>A trbnspbrfnt pbrbmftfr spfdifidbtion is obtbinfd from bn
 * {@dodf AlgoritimPbrbmftfrs} objfdt vib b dbll to
 * {@dodf gftPbrbmftfrSpfd}, bnd b bytf fndoding of tif pbrbmftfrs is
 * obtbinfd vib b dbll to {@dodf gftEndodfd}.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf AlgoritimPbrbmftfrs} blgoritims:
 * <ul>
 * <li>{@dodf AES}</li>
 * <li>{@dodf DES}</li>
 * <li>{@dodf DESfdf}</li>
 * <li>{@dodf DiffifHfllmbn}</li>
 * <li>{@dodf DSA}</li>
 * </ul>
 * Tifsf blgoritims brf dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrs">
 * AlgoritimPbrbmftfrs sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 * @sff jbvb.sfdurity.spfd.DSAPbrbmftfrSpfd
 * @sff KfyPbirGfnfrbtor
 *
 * @sindf 1.2
 */

publid dlbss AlgoritimPbrbmftfrs {

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf AlgoritimPbrbmftfrsSpi pbrbmSpi;

    // Tif blgoritim
    privbtf String blgoritim;

    // Hbs tiis objfdt bffn initiblizfd?
    privbtf boolfbn initiblizfd = fblsf;

    /**
     * Crfbtfs bn AlgoritimPbrbmftfrs objfdt.
     *
     * @pbrbm pbrbmSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd AlgoritimPbrbmftfrs(AlgoritimPbrbmftfrsSpi pbrbmSpi,
                                  Providfr providfr, String blgoritim)
    {
        tiis.pbrbmSpi = pbrbmSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns tif nbmf of tif blgoritim bssodibtfd witi tiis pbrbmftfr objfdt.
     *
     * @rfturn tif blgoritim nbmf.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Rfturns b pbrbmftfr objfdt for tif spfdififd blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw AlgoritimPbrbmftfrs objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrsSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p> Tif rfturnfd pbrbmftfr objfdt must bf initiblizfd vib b dbll to
     * {@dodf init}, using bn bppropribtf pbrbmftfr spfdifidbtion or
     * pbrbmftfr fndoding.
     *
     * @pbrbm blgoritim tif nbmf of tif blgoritim rfqufstfd.
     * Sff tif AlgoritimPbrbmftfrs sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrs">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw pbrbmftfr objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports bn
     *          AlgoritimPbrbmftfrsSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff Providfr
     */
    publid stbtid AlgoritimPbrbmftfrs gftInstbndf(String blgoritim)
    tirows NoSudiAlgoritimExdfption {
        try {
            Objfdt[] objs = Sfdurity.gftImpl(blgoritim, "AlgoritimPbrbmftfrs",
                                             (String)null);
            rfturn nfw AlgoritimPbrbmftfrs((AlgoritimPbrbmftfrsSpi)objs[0],
                                           (Providfr)objs[1],
                                           blgoritim);
        } dbtdi(NoSudiProvidfrExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(blgoritim + " not found");
        }
    }

    /**
     * Rfturns b pbrbmftfr objfdt for tif spfdififd blgoritim.
     *
     * <p> A nfw AlgoritimPbrbmftfrs objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrsSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p>Tif rfturnfd pbrbmftfr objfdt must bf initiblizfd vib b dbll to
     * {@dodf init}, using bn bppropribtf pbrbmftfr spfdifidbtion or
     * pbrbmftfr fndoding.
     *
     * @pbrbm blgoritim tif nbmf of tif blgoritim rfqufstfd.
     * Sff tif AlgoritimPbrbmftfrs sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrs">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw pbrbmftfr objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn AlgoritimPbrbmftfrsSpi
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
    publid stbtid AlgoritimPbrbmftfrs gftInstbndf(String blgoritim,
                                                  String providfr)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = Sfdurity.gftImpl(blgoritim, "AlgoritimPbrbmftfrs",
                                         providfr);
        rfturn nfw AlgoritimPbrbmftfrs((AlgoritimPbrbmftfrsSpi)objs[0],
                                       (Providfr)objs[1],
                                       blgoritim);
    }

    /**
     * Rfturns b pbrbmftfr objfdt for tif spfdififd blgoritim.
     *
     * <p> A nfw AlgoritimPbrbmftfrs objfdt fndbpsulbting tif
     * AlgoritimPbrbmftfrsSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * <p>Tif rfturnfd pbrbmftfr objfdt must bf initiblizfd vib b dbll to
     * {@dodf init}, using bn bppropribtf pbrbmftfr spfdifidbtion or
     * pbrbmftfr fndoding.
     *
     * @pbrbm blgoritim tif nbmf of tif blgoritim rfqufstfd.
     * Sff tif AlgoritimPbrbmftfrs sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#AlgoritimPbrbmftfrs">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw pbrbmftfr objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn AlgoritimPbrbmftfrGfnfrbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid AlgoritimPbrbmftfrs gftInstbndf(String blgoritim,
                                                  Providfr providfr)
        tirows NoSudiAlgoritimExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        Objfdt[] objs = Sfdurity.gftImpl(blgoritim, "AlgoritimPbrbmftfrs",
                                         providfr);
        rfturn nfw AlgoritimPbrbmftfrs((AlgoritimPbrbmftfrsSpi)objs[0],
                                       (Providfr)objs[1],
                                       blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis pbrbmftfr objfdt.
     *
     * @rfturn tif providfr of tiis pbrbmftfr objfdt
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Initiblizfs tiis pbrbmftfr objfdt using tif pbrbmftfrs
     * spfdififd in {@dodf pbrbmSpfd}.
     *
     * @pbrbm pbrbmSpfd tif pbrbmftfr spfdifidbtion.
     *
     * @fxdfption InvblidPbrbmftfrSpfdExdfption if tif givfn pbrbmftfr
     * spfdifidbtion is inbppropribtf for tif initiblizbtion of tiis pbrbmftfr
     * objfdt, or if tiis pbrbmftfr objfdt ibs blrfbdy bffn initiblizfd.
     */
    publid finbl void init(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption
    {
        if (tiis.initiblizfd)
            tirow nfw InvblidPbrbmftfrSpfdExdfption("blrfbdy initiblizfd");
        pbrbmSpi.fnginfInit(pbrbmSpfd);
        tiis.initiblizfd = truf;
    }

    /**
     * Imports tif spfdififd pbrbmftfrs bnd dfdodfs tifm bddording to tif
     * primbry dfdoding formbt for pbrbmftfrs. Tif primbry dfdoding
     * formbt for pbrbmftfrs is ASN.1, if bn ASN.1 spfdifidbtion for tiis typf
     * of pbrbmftfrs fxists.
     *
     * @pbrbm pbrbms tif fndodfd pbrbmftfrs.
     *
     * @fxdfption IOExdfption on dfdoding frrors, or if tiis pbrbmftfr objfdt
     * ibs blrfbdy bffn initiblizfd.
     */
    publid finbl void init(bytf[] pbrbms) tirows IOExdfption {
        if (tiis.initiblizfd)
            tirow nfw IOExdfption("blrfbdy initiblizfd");
        pbrbmSpi.fnginfInit(pbrbms);
        tiis.initiblizfd = truf;
    }

    /**
     * Imports tif pbrbmftfrs from {@dodf pbrbms} bnd dfdodfs tifm
     * bddording to tif spfdififd dfdoding sdifmf.
     * If {@dodf formbt} is null, tif
     * primbry dfdoding formbt for pbrbmftfrs is usfd. Tif primbry dfdoding
     * formbt is ASN.1, if bn ASN.1 spfdifidbtion for tifsf pbrbmftfrs
     * fxists.
     *
     * @pbrbm pbrbms tif fndodfd pbrbmftfrs.
     *
     * @pbrbm formbt tif nbmf of tif dfdoding sdifmf.
     *
     * @fxdfption IOExdfption on dfdoding frrors, or if tiis pbrbmftfr objfdt
     * ibs blrfbdy bffn initiblizfd.
     */
    publid finbl void init(bytf[] pbrbms, String formbt) tirows IOExdfption {
        if (tiis.initiblizfd)
            tirow nfw IOExdfption("blrfbdy initiblizfd");
        pbrbmSpi.fnginfInit(pbrbms, formbt);
        tiis.initiblizfd = truf;
    }

    /**
     * Rfturns b (trbnspbrfnt) spfdifidbtion of tiis pbrbmftfr objfdt.
     * {@dodf pbrbmSpfd} idfntififs tif spfdifidbtion dlbss in wiidi
     * tif pbrbmftfrs siould bf rfturnfd. It dould, for fxbmplf, bf
     * {@dodf DSAPbrbmftfrSpfd.dlbss}, to indidbtf tibt tif
     * pbrbmftfrs siould bf rfturnfd in bn instbndf of tif
     * {@dodf DSAPbrbmftfrSpfd} dlbss.
     *
     * @pbrbm <T> tif typf of tif pbrbmftfr spfdifidbtion to bf rfturrnfd
     * @pbrbm pbrbmSpfd tif spfdifidbtion dlbss in wiidi
     * tif pbrbmftfrs siould bf rfturnfd.
     *
     * @rfturn tif pbrbmftfr spfdifidbtion.
     *
     * @fxdfption InvblidPbrbmftfrSpfdExdfption if tif rfqufstfd pbrbmftfr
     * spfdifidbtion is inbppropribtf for tiis pbrbmftfr objfdt, or if tiis
     * pbrbmftfr objfdt ibs not bffn initiblizfd.
     */
    publid finbl <T fxtfnds AlgoritimPbrbmftfrSpfd>
        T gftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption
    {
        if (tiis.initiblizfd == fblsf) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption("not initiblizfd");
        }
        rfturn pbrbmSpi.fnginfGftPbrbmftfrSpfd(pbrbmSpfd);
    }

    /**
     * Rfturns tif pbrbmftfrs in tifir primbry fndoding formbt.
     * Tif primbry fndoding formbt for pbrbmftfrs is ASN.1, if bn ASN.1
     * spfdifidbtion for tiis typf of pbrbmftfrs fxists.
     *
     * @rfturn tif pbrbmftfrs fndodfd using tifir primbry fndoding formbt.
     *
     * @fxdfption IOExdfption on fndoding frrors, or if tiis pbrbmftfr objfdt
     * ibs not bffn initiblizfd.
     */
    publid finbl bytf[] gftEndodfd() tirows IOExdfption
    {
        if (tiis.initiblizfd == fblsf) {
            tirow nfw IOExdfption("not initiblizfd");
        }
        rfturn pbrbmSpi.fnginfGftEndodfd();
    }

    /**
     * Rfturns tif pbrbmftfrs fndodfd in tif spfdififd sdifmf.
     * If {@dodf formbt} is null, tif
     * primbry fndoding formbt for pbrbmftfrs is usfd. Tif primbry fndoding
     * formbt is ASN.1, if bn ASN.1 spfdifidbtion for tifsf pbrbmftfrs
     * fxists.
     *
     * @pbrbm formbt tif nbmf of tif fndoding formbt.
     *
     * @rfturn tif pbrbmftfrs fndodfd using tif spfdififd fndoding sdifmf.
     *
     * @fxdfption IOExdfption on fndoding frrors, or if tiis pbrbmftfr objfdt
     * ibs not bffn initiblizfd.
     */
    publid finbl bytf[] gftEndodfd(String formbt) tirows IOExdfption
    {
        if (tiis.initiblizfd == fblsf) {
            tirow nfw IOExdfption("not initiblizfd");
        }
        rfturn pbrbmSpi.fnginfGftEndodfd(formbt);
    }

    /**
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     *
     * @rfturn b formbttfd string dfsdribing tif pbrbmftfrs, or null if tiis
     * pbrbmftfr objfdt ibs not bffn initiblizfd.
     */
    publid finbl String toString() {
        if (tiis.initiblizfd == fblsf) {
            rfturn null;
        }
        rfturn pbrbmSpi.fnginfToString();
    }
}
