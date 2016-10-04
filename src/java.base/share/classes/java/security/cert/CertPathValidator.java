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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import sun.sfdurity.util.Dfbug;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * A dlbss for vblidbting dfrtifidbtion pbtis (blso known bs dfrtifidbtf
 * dibins).
 * <p>
 * Tiis dlbss usfs b providfr-bbsfd brdiitfdturf.
 * To drfbtf b {@dodf CfrtPbtiVblidbtor},
 * dbll onf of tif stbtid {@dodf gftInstbndf} mftiods, pbssing in tif
 * blgoritim nbmf of tif {@dodf CfrtPbtiVblidbtor} dfsirfd bnd
 * optionblly tif nbmf of tif providfr dfsirfd.
 *
 * <p>Ondf b {@dodf CfrtPbtiVblidbtor} objfdt ibs bffn drfbtfd, it dbn
 * bf usfd to vblidbtf dfrtifidbtion pbtis by dblling tif {@link #vblidbtf
 * vblidbtf} mftiod bnd pbssing it tif {@dodf CfrtPbti} to bf vblidbtfd
 * bnd bn blgoritim-spfdifid sft of pbrbmftfrs. If suddfssful, tif rfsult is
 * rfturnfd in bn objfdt tibt implfmfnts tif
 * {@dodf CfrtPbtiVblidbtorRfsult} intfrfbdf.
 *
 * <p>Tif {@link #gftRfvodbtionCifdkfr} mftiod bllows bn bpplidbtion to spfdify
 * bdditionbl blgoritim-spfdifid pbrbmftfrs bnd options usfd by tif
 * {@dodf CfrtPbtiVblidbtor} wifn difdking tif rfvodbtion stbtus of
 * dfrtifidbtfs. Hfrf is bn fxbmplf dfmonstrbting iow it is usfd witi tif PKIX
 * blgoritim:
 *
 * <prf>
 * CfrtPbtiVblidbtor dpv = CfrtPbtiVblidbtor.gftInstbndf("PKIX");
 * PKIXRfvodbtionCifdkfr rd = (PKIXRfvodbtionCifdkfr)dpv.gftRfvodbtionCifdkfr();
 * rd.sftOptions(EnumSft.of(Option.SOFT_FAIL));
 * pbrbms.bddCfrtPbtiCifdkfr(rd);
 * CfrtPbtiVblidbtorRfsult dpvr = dpv.vblidbtf(pbti, pbrbms);
 * </prf>
 *
 * <p>Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf CfrtPbtiVblidbtor} blgoritim:
 * <ul>
 * <li>{@dodf PKIX}</li>
 * </ul>
 * Tiis blgoritim is dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiVblidbtor">
 * CfrtPbtiVblidbtor sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Tif stbtid mftiods of tiis dlbss brf gubrbntffd to bf tirfbd-sbff.
 * Multiplf tirfbds mby dondurrfntly invokf tif stbtid mftiods dffinfd in
 * tiis dlbss witi no ill ffffdts.
 * <p>
 * Howfvfr, tiis is not truf for tif non-stbtid mftiods dffinfd by tiis dlbss.
 * Unlfss otifrwisf dodumfntfd by b spfdifid providfr, tirfbds tibt nffd to
 * bddfss b singlf {@dodf CfrtPbtiVblidbtor} instbndf dondurrfntly siould
 * syndironizf bmongst tifmsflvfs bnd providf tif nfdfssbry lodking. Multiplf
 * tirfbds fbdi mbnipulbting b difffrfnt {@dodf CfrtPbtiVblidbtor}
 * instbndf nffd not syndironizf.
 *
 * @sff CfrtPbti
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
publid dlbss CfrtPbtiVblidbtor {

    /*
     * Constbnt to lookup in tif Sfdurity propfrtifs filf to dftfrminf
     * tif dffbult dfrtpbtivblidbtor typf. In tif Sfdurity propfrtifs filf,
     * tif dffbult dfrtpbtivblidbtor typf is givfn bs:
     * <prf>
     * dfrtpbtivblidbtor.typf=PKIX
     * </prf>
     */
    privbtf stbtid finbl String CPV_TYPE = "dfrtpbtivblidbtor.typf";
    privbtf finbl CfrtPbtiVblidbtorSpi vblidbtorSpi;
    privbtf finbl Providfr providfr;
    privbtf finbl String blgoritim;

    /**
     * Crfbtfs b {@dodf CfrtPbtiVblidbtor} objfdt of tif givfn blgoritim,
     * bnd fndbpsulbtfs tif givfn providfr implfmfntbtion (SPI objfdt) in it.
     *
     * @pbrbm vblidbtorSpi tif providfr implfmfntbtion
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim nbmf
     */
    protfdtfd CfrtPbtiVblidbtor(CfrtPbtiVblidbtorSpi vblidbtorSpi,
        Providfr providfr, String blgoritim)
    {
        tiis.vblidbtorSpi = vblidbtorSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
    }

    /**
     * Rfturns b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     * spfdififd blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw CfrtPbtiVblidbtor objfdt fndbpsulbting tif
     * CfrtPbtiVblidbtorSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd {@dodf CfrtPbtiVblidbtor}
     *  blgoritim. Sff tif CfrtPbtiVblidbtor sfdtion in tif <b irff=
     *  "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiVblidbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     *          spfdififd blgoritim.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          CfrtPbtiVblidbtorSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtPbtiVblidbtor gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtPbtiVblidbtor",
            CfrtPbtiVblidbtorSpi.dlbss, blgoritim);
        rfturn nfw CfrtPbtiVblidbtor((CfrtPbtiVblidbtorSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     * spfdififd blgoritim.
     *
     * <p> A nfw CfrtPbtiVblidbtor objfdt fndbpsulbting tif
     * CfrtPbtiVblidbtorSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd {@dodf CfrtPbtiVblidbtor}
     *  blgoritim. Sff tif CfrtPbtiVblidbtor sfdtion in tif <b irff=
     *  "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiVblidbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     *          spfdififd blgoritim.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b CfrtPbtiVblidbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf providfr} is
     *          null or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtPbtiVblidbtor gftInstbndf(String blgoritim,
            String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtPbtiVblidbtor",
            CfrtPbtiVblidbtorSpi.dlbss, blgoritim, providfr);
        rfturn nfw CfrtPbtiVblidbtor((CfrtPbtiVblidbtorSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     * spfdififd blgoritim.
     *
     * <p> A nfw CfrtPbtiVblidbtor objfdt fndbpsulbting tif
     * CfrtPbtiVblidbtorSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif nbmf of tif rfqufstfd {@dodf CfrtPbtiVblidbtor}
     * blgoritim. Sff tif CfrtPbtiVblidbtor sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiVblidbtor">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn b {@dodf CfrtPbtiVblidbtor} objfdt tibt implfmfnts tif
     *          spfdififd blgoritim.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b CfrtPbtiVblidbtorSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf providfr} is
     *          null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtPbtiVblidbtor gftInstbndf(String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtPbtiVblidbtor",
            CfrtPbtiVblidbtorSpi.dlbss, blgoritim, providfr);
        rfturn nfw CfrtPbtiVblidbtor((CfrtPbtiVblidbtorSpi)instbndf.impl,
            instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns tif {@dodf Providfr} of tiis
     * {@dodf CfrtPbtiVblidbtor}.
     *
     * @rfturn tif {@dodf Providfr} of tiis {@dodf CfrtPbtiVblidbtor}
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis {@dodf CfrtPbtiVblidbtor}.
     *
     * @rfturn tif blgoritim nbmf of tiis {@dodf CfrtPbtiVblidbtor}
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Vblidbtfs tif spfdififd dfrtifidbtion pbti using tif spfdififd
     * blgoritim pbrbmftfr sft.
     * <p>
     * Tif {@dodf CfrtPbti} spfdififd must bf of b typf tibt is
     * supportfd by tif vblidbtion blgoritim, otifrwisf bn
     * {@dodf InvblidAlgoritimPbrbmftfrExdfption} will bf tirown. For
     * fxbmplf, b {@dodf CfrtPbtiVblidbtor} tibt implfmfnts tif PKIX
     * blgoritim vblidbtfs {@dodf CfrtPbti} objfdts of typf X.509.
     *
     * @pbrbm dfrtPbti tif {@dodf CfrtPbti} to bf vblidbtfd
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @rfturn tif rfsult of tif vblidbtion blgoritim
     * @fxdfption CfrtPbtiVblidbtorExdfption if tif {@dodf CfrtPbti}
     * dofs not vblidbtf
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     * pbrbmftfrs or tif typf of tif spfdififd {@dodf CfrtPbti} brf
     * inbppropribtf for tiis {@dodf CfrtPbtiVblidbtor}
     */
    publid finbl CfrtPbtiVblidbtorRfsult vblidbtf(CfrtPbti dfrtPbti,
        CfrtPbtiPbrbmftfrs pbrbms)
        tirows CfrtPbtiVblidbtorExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        rfturn vblidbtorSpi.fnginfVblidbtf(dfrtPbti, pbrbms);
    }

    /**
     * Rfturns tif dffbult {@dodf CfrtPbtiVblidbtor} typf bs spfdififd by
     * tif {@dodf dfrtpbtivblidbtor.typf} sfdurity propfrty, or tif string
     * {@litfrbl "PKIX"} if no sudi propfrty fxists.
     *
     * <p>Tif dffbult {@dodf CfrtPbtiVblidbtor} typf dbn bf usfd by
     * bpplidbtions tibt do not wbnt to usf b ibrd-dodfd typf wifn dblling onf
     * of tif {@dodf gftInstbndf} mftiods, bnd wbnt to providf b dffbult
     * typf in dbsf b usfr dofs not spfdify its own.
     *
     * <p>Tif dffbult {@dodf CfrtPbtiVblidbtor} typf dbn bf dibngfd by
     * sftting tif vbluf of tif {@dodf dfrtpbtivblidbtor.typf} sfdurity
     * propfrty to tif dfsirfd typf.
     *
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     * @rfturn tif dffbult {@dodf CfrtPbtiVblidbtor} typf bs spfdififd
     * by tif {@dodf dfrtpbtivblidbtor.typf} sfdurity propfrty, or tif string
     * {@litfrbl "PKIX"} if no sudi propfrty fxists.
     */
    publid finbl stbtid String gftDffbultTypf() {
        String dpvtypf =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Sfdurity.gftPropfrty(CPV_TYPE);
                }
            });
        rfturn (dpvtypf == null) ? "PKIX" : dpvtypf;
    }

    /**
     * Rfturns b {@dodf CfrtPbtiCifdkfr} tibt tif fndbpsulbtfd
     * {@dodf CfrtPbtiVblidbtorSpi} implfmfntbtion usfs to difdk tif rfvodbtion
     * stbtus of dfrtifidbtfs. A PKIX implfmfntbtion rfturns objfdts of
     * typf {@dodf PKIXRfvodbtionCifdkfr}. Ebdi invodbtion of tiis mftiod
     * rfturns b nfw instbndf of {@dodf CfrtPbtiCifdkfr}.
     *
     * <p>Tif primbry purposf of tiis mftiod is to bllow dbllfrs to spfdify
     * bdditionbl input pbrbmftfrs bnd options spfdifid to rfvodbtion difdking.
     * Sff tif dlbss dfsdription for bn fxbmplf.
     *
     * @rfturn b {@dodf CfrtPbtiCifdkfr}
     * @tirows UnsupportfdOpfrbtionExdfption if tif sfrvidf providfr dofs not
     *         support tiis mftiod
     * @sindf 1.8
     */
    publid finbl CfrtPbtiCifdkfr gftRfvodbtionCifdkfr() {
        rfturn vblidbtorSpi.fnginfGftRfvodbtionCifdkfr();
    }
}
