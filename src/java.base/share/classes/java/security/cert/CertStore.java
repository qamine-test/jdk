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
import jbvb.util.Collfdtion;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * A dlbss for rftrifving {@dodf Cfrtifidbtf}s bnd {@dodf CRL}s
 * from b rfpository.
 * <p>
 * Tiis dlbss usfs b providfr-bbsfd brdiitfdturf.
 * To drfbtf b {@dodf CfrtStorf}, dbll onf of tif stbtid
 * {@dodf gftInstbndf} mftiods, pbssing in tif typf of
 * {@dodf CfrtStorf} dfsirfd, bny bpplidbblf initiblizbtion pbrbmftfrs
 * bnd optionblly tif nbmf of tif providfr dfsirfd.
 * <p>
 * Ondf tif {@dodf CfrtStorf} ibs bffn drfbtfd, it dbn bf usfd to
 * rftrifvf {@dodf Cfrtifidbtf}s bnd {@dodf CRL}s by dblling its
 * {@link #gftCfrtifidbtfs(CfrtSflfdtor sflfdtor) gftCfrtifidbtfs} bnd
 * {@link #gftCRLs(CRLSflfdtor sflfdtor) gftCRLs} mftiods.
 * <p>
 * Unlikf b {@link jbvb.sfdurity.KfyStorf KfyStorf}, wiidi providfs bddfss
 * to b dbdif of privbtf kfys bnd trustfd dfrtifidbtfs, b
 * {@dodf CfrtStorf} is dfsignfd to providf bddfss to b potfntiblly
 * vbst rfpository of untrustfd dfrtifidbtfs bnd CRLs. For fxbmplf, bn LDAP
 * implfmfntbtion of {@dodf CfrtStorf} providfs bddfss to dfrtifidbtfs
 * bnd CRLs storfd in onf or morf dirfdtorifs using tif LDAP protodol bnd tif
 * sdifmb bs dffinfd in tif RFC sfrvidf bttributf.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf CfrtStorf} typf:
 * <ul>
 * <li>{@dodf Collfdtion}</li>
 * </ul>
 * Tiis typf is dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtStorf">
 * CfrtStorf sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr typfs brf supportfd.
 *
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * All publid mftiods of {@dodf CfrtStorf} objfdts must bf tirfbd-sbff.
 * Tibt is, multiplf tirfbds mby dondurrfntly invokf tifsf mftiods on b
 * singlf {@dodf CfrtStorf} objfdt (or morf tibn onf) witi no
 * ill ffffdts. Tiis bllows b {@dodf CfrtPbtiBuildfr} to sfbrdi for b
 * CRL wiilf simultbnfously sfbrdiing for furtifr dfrtifidbtfs, for instbndf.
 * <p>
 * Tif stbtid mftiods of tiis dlbss brf blso gubrbntffd to bf tirfbd-sbff.
 * Multiplf tirfbds mby dondurrfntly invokf tif stbtid mftiods dffinfd in
 * tiis dlbss witi no ill ffffdts.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn, Stfvf Hbnnb
 */
publid dlbss CfrtStorf {
    /*
     * Constbnt to lookup in tif Sfdurity propfrtifs filf to dftfrminf
     * tif dffbult dfrtstorf typf. In tif Sfdurity propfrtifs filf, tif
     * dffbult dfrtstorf typf is givfn bs:
     * <prf>
     * dfrtstorf.typf=LDAP
     * </prf>
     */
    privbtf stbtid finbl String CERTSTORE_TYPE = "dfrtstorf.typf";
    privbtf CfrtStorfSpi storfSpi;
    privbtf Providfr providfr;
    privbtf String typf;
    privbtf CfrtStorfPbrbmftfrs pbrbms;

    /**
     * Crfbtfs b {@dodf CfrtStorf} objfdt of tif givfn typf, bnd
     * fndbpsulbtfs tif givfn providfr implfmfntbtion (SPI objfdt) in it.
     *
     * @pbrbm storfSpi tif providfr implfmfntbtion
     * @pbrbm providfr tif providfr
     * @pbrbm typf tif typf
     * @pbrbm pbrbms tif initiblizbtion pbrbmftfrs (mby bf {@dodf null})
     */
    protfdtfd CfrtStorf(CfrtStorfSpi storfSpi, Providfr providfr,
                        String typf, CfrtStorfPbrbmftfrs pbrbms) {
        tiis.storfSpi = storfSpi;
        tiis.providfr = providfr;
        tiis.typf = typf;
        if (pbrbms != null)
            tiis.pbrbms = (CfrtStorfPbrbmftfrs) pbrbms.dlonf();
    }

    /**
     * Rfturns b {@dodf Collfdtion} of {@dodf Cfrtifidbtf}s tibt
     * mbtdi tif spfdififd sflfdtor. If no {@dodf Cfrtifidbtf}s
     * mbtdi tif sflfdtor, bn fmpty {@dodf Collfdtion} will bf rfturnfd.
     * <p>
     * For somf {@dodf CfrtStorf} typfs, tif rfsulting
     * {@dodf Collfdtion} mby not dontbin <b>bll</b> of tif
     * {@dodf Cfrtifidbtf}s tibt mbtdi tif sflfdtor. For instbndf,
     * bn LDAP {@dodf CfrtStorf} mby not sfbrdi bll fntrifs in tif
     * dirfdtory. Instfbd, it mby just sfbrdi fntrifs tibt brf likfly to
     * dontbin tif {@dodf Cfrtifidbtf}s it is looking for.
     * <p>
     * Somf {@dodf CfrtStorf} implfmfntbtions (fspfdiblly LDAP
     * {@dodf CfrtStorf}s) mby tirow b {@dodf CfrtStorfExdfption}
     * unlfss b non-null {@dodf CfrtSflfdtor} is providfd tibt
     * indludfs spfdifid dritfrib tibt dbn bf usfd to find tif dfrtifidbtfs.
     * Issufr bnd/or subjfdt nbmfs brf fspfdiblly usfful dritfrib.
     *
     * @pbrbm sflfdtor A {@dodf CfrtSflfdtor} usfd to sflfdt wiidi
     *  {@dodf Cfrtifidbtf}s siould bf rfturnfd. Spfdify {@dodf null}
     *  to rfturn bll {@dodf Cfrtifidbtf}s (if supportfd).
     * @rfturn A {@dodf Collfdtion} of {@dodf Cfrtifidbtf}s tibt
     *         mbtdi tif spfdififd sflfdtor (nfvfr {@dodf null})
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid finbl Collfdtion<? fxtfnds Cfrtifidbtf> gftCfrtifidbtfs
            (CfrtSflfdtor sflfdtor) tirows CfrtStorfExdfption {
        rfturn storfSpi.fnginfGftCfrtifidbtfs(sflfdtor);
    }

    /**
     * Rfturns b {@dodf Collfdtion} of {@dodf CRL}s tibt
     * mbtdi tif spfdififd sflfdtor. If no {@dodf CRL}s
     * mbtdi tif sflfdtor, bn fmpty {@dodf Collfdtion} will bf rfturnfd.
     * <p>
     * For somf {@dodf CfrtStorf} typfs, tif rfsulting
     * {@dodf Collfdtion} mby not dontbin <b>bll</b> of tif
     * {@dodf CRL}s tibt mbtdi tif sflfdtor. For instbndf,
     * bn LDAP {@dodf CfrtStorf} mby not sfbrdi bll fntrifs in tif
     * dirfdtory. Instfbd, it mby just sfbrdi fntrifs tibt brf likfly to
     * dontbin tif {@dodf CRL}s it is looking for.
     * <p>
     * Somf {@dodf CfrtStorf} implfmfntbtions (fspfdiblly LDAP
     * {@dodf CfrtStorf}s) mby tirow b {@dodf CfrtStorfExdfption}
     * unlfss b non-null {@dodf CRLSflfdtor} is providfd tibt
     * indludfs spfdifid dritfrib tibt dbn bf usfd to find tif CRLs.
     * Issufr nbmfs bnd/or tif dfrtifidbtf to bf difdkfd brf fspfdiblly usfful.
     *
     * @pbrbm sflfdtor A {@dodf CRLSflfdtor} usfd to sflfdt wiidi
     *  {@dodf CRL}s siould bf rfturnfd. Spfdify {@dodf null}
     *  to rfturn bll {@dodf CRL}s (if supportfd).
     * @rfturn A {@dodf Collfdtion} of {@dodf CRL}s tibt
     *         mbtdi tif spfdififd sflfdtor (nfvfr {@dodf null})
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid finbl Collfdtion<? fxtfnds CRL> gftCRLs(CRLSflfdtor sflfdtor)
            tirows CfrtStorfExdfption {
        rfturn storfSpi.fnginfGftCRLs(sflfdtor);
    }

    /**
     * Rfturns b {@dodf CfrtStorf} objfdt tibt implfmfnts tif spfdififd
     * {@dodf CfrtStorf} typf bnd is initiblizfd witi tif spfdififd
     * pbrbmftfrs.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw CfrtStorf objfdt fndbpsulbting tif
     * CfrtStorfSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p>Tif {@dodf CfrtStorf} tibt is rfturnfd is initiblizfd witi tif
     * spfdififd {@dodf CfrtStorfPbrbmftfrs}. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of {@dodf CfrtStorf}s.
     * Notf tibt tif spfdififd {@dodf CfrtStorfPbrbmftfrs} objfdt is
     * dlonfd.
     *
     * @pbrbm typf tif nbmf of tif rfqufstfd {@dodf CfrtStorf} typf.
     * Sff tif CfrtStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd typfs.
     *
     * @pbrbm pbrbms tif initiblizbtion pbrbmftfrs (mby bf {@dodf null}).
     *
     * @rfturn b {@dodf CfrtStorf} objfdt tibt implfmfnts tif spfdififd
     *          {@dodf CfrtStorf} typf.
     *
     * @tirows NoSudiAlgoritimExdfption if no Providfr supports b
     *          CfrtStorfSpi implfmfntbtion for tif spfdififd typf.
     *
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     *          initiblizbtion pbrbmftfrs brf inbppropribtf for tiis
     *          {@dodf CfrtStorf}.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtStorf gftInstbndf(String typf, CfrtStorfPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption,
            NoSudiAlgoritimExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtStorf",
                CfrtStorfSpi.dlbss, typf, pbrbms);
            rfturn nfw CfrtStorf((CfrtStorfSpi)instbndf.impl,
                instbndf.providfr, typf, pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            rfturn ibndlfExdfption(f);
        }
    }

    privbtf stbtid CfrtStorf ibndlfExdfption(NoSudiAlgoritimExdfption f)
            tirows NoSudiAlgoritimExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        Tirowbblf dbusf = f.gftCbusf();
        if (dbusf instbndfof InvblidAlgoritimPbrbmftfrExdfption) {
            tirow (InvblidAlgoritimPbrbmftfrExdfption)dbusf;
        }
        tirow f;
    }

    /**
     * Rfturns b {@dodf CfrtStorf} objfdt tibt implfmfnts tif spfdififd
     * {@dodf CfrtStorf} typf.
     *
     * <p> A nfw CfrtStorf objfdt fndbpsulbting tif
     * CfrtStorfSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * <p>Tif {@dodf CfrtStorf} tibt is rfturnfd is initiblizfd witi tif
     * spfdififd {@dodf CfrtStorfPbrbmftfrs}. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of {@dodf CfrtStorf}s.
     * Notf tibt tif spfdififd {@dodf CfrtStorfPbrbmftfrs} objfdt is
     * dlonfd.
     *
     * @pbrbm typf tif rfqufstfd {@dodf CfrtStorf} typf.
     * Sff tif CfrtStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd typfs.
     *
     * @pbrbm pbrbms tif initiblizbtion pbrbmftfrs (mby bf {@dodf null}).
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn b {@dodf CfrtStorf} objfdt tibt implfmfnts tif
     *          spfdififd typf.
     *
     * @tirows NoSudiAlgoritimExdfption if b CfrtStorfSpi
     *          implfmfntbtion for tif spfdififd typf is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     *          initiblizbtion pbrbmftfrs brf inbppropribtf for tiis
     *          {@dodf CfrtStorf}.
     *
     * @tirows NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf providfr} is
     *          null or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtStorf gftInstbndf(String typf,
            CfrtStorfPbrbmftfrs pbrbms, String providfr)
            tirows InvblidAlgoritimPbrbmftfrExdfption,
            NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtStorf",
                CfrtStorfSpi.dlbss, typf, pbrbms, providfr);
            rfturn nfw CfrtStorf((CfrtStorfSpi)instbndf.impl,
                instbndf.providfr, typf, pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            rfturn ibndlfExdfption(f);
        }
    }

    /**
     * Rfturns b {@dodf CfrtStorf} objfdt tibt implfmfnts tif spfdififd
     * {@dodf CfrtStorf} typf.
     *
     * <p> A nfw CfrtStorf objfdt fndbpsulbting tif
     * CfrtStorfSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * <p>Tif {@dodf CfrtStorf} tibt is rfturnfd is initiblizfd witi tif
     * spfdififd {@dodf CfrtStorfPbrbmftfrs}. Tif typf of pbrbmftfrs
     * nffdfd mby vbry bftwffn difffrfnt typfs of {@dodf CfrtStorf}s.
     * Notf tibt tif spfdififd {@dodf CfrtStorfPbrbmftfrs} objfdt is
     * dlonfd.
     *
     * @pbrbm typf tif rfqufstfd {@dodf CfrtStorf} typf.
     * Sff tif CfrtStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd typfs.
     *
     * @pbrbm pbrbms tif initiblizbtion pbrbmftfrs (mby bf {@dodf null}).
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn b {@dodf CfrtStorf} objfdt tibt implfmfnts tif
     *          spfdififd typf.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b CfrtStorfSpi
     *          implfmfntbtion for tif spfdififd typf is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     *          initiblizbtion pbrbmftfrs brf inbppropribtf for tiis
     *          {@dodf CfrtStorf}
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf providfr} is
     *          null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid CfrtStorf gftInstbndf(String typf, CfrtStorfPbrbmftfrs pbrbms,
            Providfr providfr) tirows NoSudiAlgoritimExdfption,
            InvblidAlgoritimPbrbmftfrExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtStorf",
                CfrtStorfSpi.dlbss, typf, pbrbms, providfr);
            rfturn nfw CfrtStorf((CfrtStorfSpi)instbndf.impl,
                instbndf.providfr, typf, pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            rfturn ibndlfExdfption(f);
        }
    }

    /**
     * Rfturns tif pbrbmftfrs usfd to initiblizf tiis {@dodf CfrtStorf}.
     * Notf tibt tif {@dodf CfrtStorfPbrbmftfrs} objfdt is dlonfd bfforf
     * it is rfturnfd.
     *
     * @rfturn tif pbrbmftfrs usfd to initiblizf tiis {@dodf CfrtStorf}
     * (mby bf {@dodf null})
     */
    publid finbl CfrtStorfPbrbmftfrs gftCfrtStorfPbrbmftfrs() {
        rfturn (pbrbms == null ? null : (CfrtStorfPbrbmftfrs) pbrbms.dlonf());
    }

    /**
     * Rfturns tif typf of tiis {@dodf CfrtStorf}.
     *
     * @rfturn tif typf of tiis {@dodf CfrtStorf}
     */
    publid finbl String gftTypf() {
        rfturn tiis.typf;
    }

    /**
     * Rfturns tif providfr of tiis {@dodf CfrtStorf}.
     *
     * @rfturn tif providfr of tiis {@dodf CfrtStorf}
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif dffbult {@dodf CfrtStorf} typf bs spfdififd by tif
     * {@dodf dfrtstorf.typf} sfdurity propfrty, or tif string
     * {@litfrbl "LDAP"} if no sudi propfrty fxists.
     *
     * <p>Tif dffbult {@dodf CfrtStorf} typf dbn bf usfd by bpplidbtions
     * tibt do not wbnt to usf b ibrd-dodfd typf wifn dblling onf of tif
     * {@dodf gftInstbndf} mftiods, bnd wbnt to providf b dffbult
     * {@dodf CfrtStorf} typf in dbsf b usfr dofs not spfdify its own.
     *
     * <p>Tif dffbult {@dodf CfrtStorf} typf dbn bf dibngfd by sftting
     * tif vbluf of tif {@dodf dfrtstorf.typf} sfdurity propfrty to tif
     * dfsirfd typf.
     *
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     * @rfturn tif dffbult {@dodf CfrtStorf} typf bs spfdififd by tif
     * {@dodf dfrtstorf.typf} sfdurity propfrty, or tif string
     * {@litfrbl "LDAP"} if no sudi propfrty fxists.
     */
    publid finbl stbtid String gftDffbultTypf() {
        String dstypf;
        dstypf = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn Sfdurity.gftPropfrty(CERTSTORE_TYPE);
            }
        });
        if (dstypf == null) {
            dstypf = "LDAP";
        }
        rfturn dstypf;
    }
}
