/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.InputStrfbm;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;

import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tiis dlbss dffinfs tif fundtionblity of b dfrtifidbtf fbdtory, wiidi is
 * usfd to gfnfrbtf dfrtifidbtf, dfrtifidbtion pbti ({@dodf CfrtPbti})
 * bnd dfrtifidbtf rfvodbtion list (CRL) objfdts from tifir fndodings.
 *
 * <p>For fndodings donsisting of multiplf dfrtifidbtfs, usf
 * {@dodf gfnfrbtfCfrtifidbtfs} wifn you wbnt to
 * pbrsf b dollfdtion of possibly unrflbtfd dfrtifidbtfs. Otifrwisf,
 * usf {@dodf gfnfrbtfCfrtPbti} wifn you wbnt to gfnfrbtf
 * b {@dodf CfrtPbti} (b dfrtifidbtf dibin) bnd subsfqufntly
 * vblidbtf it witi b {@dodf CfrtPbtiVblidbtor}.
 *
 * <p>A dfrtifidbtf fbdtory for X.509 must rfturn dfrtifidbtfs tibt brf bn
 * instbndf of {@dodf jbvb.sfdurity.dfrt.X509Cfrtifidbtf}, bnd CRLs
 * tibt brf bn instbndf of {@dodf jbvb.sfdurity.dfrt.X509CRL}.
 *
 * <p>Tif following fxbmplf rfbds b filf witi Bbsf64 fndodfd dfrtifidbtfs,
 * wiidi brf fbdi boundfd bt tif bfginning by -----BEGIN CERTIFICATE-----, bnd
 * boundfd bt tif fnd by -----END CERTIFICATE-----. Wf donvfrt tif
 * {@dodf FilfInputStrfbm} (wiidi dofs not support {@dodf mbrk}
 * bnd {@dodf rfsft}) to b {@dodf BufffrfdInputStrfbm} (wiidi
 * supports tiosf mftiods), so tibt fbdi dbll to
 * {@dodf gfnfrbtfCfrtifidbtf} donsumfs only onf dfrtifidbtf, bnd tif
 * rfbd position of tif input strfbm is positionfd to tif nfxt dfrtifidbtf in
 * tif filf:
 *
 * <prf>{@dodf
 * FilfInputStrfbm fis = nfw FilfInputStrfbm(filfnbmf);
 * BufffrfdInputStrfbm bis = nfw BufffrfdInputStrfbm(fis);
 *
 * CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
 *
 * wiilf (bis.bvbilbblf() > 0) {
 *    Cfrtifidbtf dfrt = df.gfnfrbtfCfrtifidbtf(bis);
 *    Systfm.out.println(dfrt.toString());
 * }
 * }</prf>
 *
 * <p>Tif following fxbmplf pbrsfs b PKCS#7-formbttfd dfrtifidbtf rfply storfd
 * in b filf bnd fxtrbdts bll tif dfrtifidbtfs from it:
 *
 * <prf>
 * FilfInputStrfbm fis = nfw FilfInputStrfbm(filfnbmf);
 * CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
 * Collfdtion d = df.gfnfrbtfCfrtifidbtfs(fis);
 * Itfrbtor i = d.itfrbtor();
 * wiilf (i.ibsNfxt()) {
 *    Cfrtifidbtf dfrt = (Cfrtifidbtf)i.nfxt();
 *    Systfm.out.println(dfrt);
 * }
 * </prf>
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd {@dodf CfrtifidbtfFbdtory} typf:
 * <ul>
 * <li>{@dodf X.509}</li>
 * </ul>
 * bnd tif following stbndbrd {@dodf CfrtPbti} fndodings:
 * <ul>
 * <li>{@dodf PKCS7}</li>
 * <li>{@dodf PkiPbti}</li>
 * </ul>
 * Tif typf bnd fndodings brf dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtifidbtfFbdtory">
 * CfrtifidbtfFbdtory sfdtion</b> bnd tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiEndodings">
 * CfrtPbti Endodings sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr typfs or fndodings brf supportfd.
 *
 * @butior Hfmmb Prbfulldibndrb
 * @butior Jbn Lufif
 * @butior Sfbn Mullbn
 *
 * @sff Cfrtifidbtf
 * @sff X509Cfrtifidbtf
 * @sff CfrtPbti
 * @sff CRL
 * @sff X509CRL
 *
 * @sindf 1.2
 */

publid dlbss CfrtifidbtfFbdtory {

    // Tif dfrtifidbtf typf
    privbtf String typf;

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion
    privbtf CfrtifidbtfFbdtorySpi dfrtFbdSpi;

    /**
     * Crfbtfs b CfrtifidbtfFbdtory objfdt of tif givfn typf, bnd fndbpsulbtfs
     * tif givfn providfr implfmfntbtion (SPI objfdt) in it.
     *
     * @pbrbm dfrtFbdSpi tif providfr implfmfntbtion.
     * @pbrbm providfr tif providfr.
     * @pbrbm typf tif dfrtifidbtf typf.
     */
    protfdtfd CfrtifidbtfFbdtory(CfrtifidbtfFbdtorySpi dfrtFbdSpi,
                                 Providfr providfr, String typf)
    {
        tiis.dfrtFbdSpi = dfrtFbdSpi;
        tiis.providfr = providfr;
        tiis.typf = typf;
    }

    /**
     * Rfturns b dfrtifidbtf fbdtory objfdt tibt implfmfnts tif
     * spfdififd dfrtifidbtf typf.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw CfrtifidbtfFbdtory objfdt fndbpsulbting tif
     * CfrtifidbtfFbdtorySpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif nbmf of tif rfqufstfd dfrtifidbtf typf.
     * Sff tif CfrtifidbtfFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtifidbtfFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd dfrtifidbtf typfs.
     *
     * @rfturn b dfrtifidbtf fbdtory objfdt for tif spfdififd typf.
     *
     * @fxdfption CfrtifidbtfExdfption if no Providfr supports b
     *          CfrtifidbtfFbdtorySpi implfmfntbtion for tif
     *          spfdififd typf.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl CfrtifidbtfFbdtory gftInstbndf(String typf)
            tirows CfrtifidbtfExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtifidbtfFbdtory",
                CfrtifidbtfFbdtorySpi.dlbss, typf);
            rfturn nfw CfrtifidbtfFbdtory((CfrtifidbtfFbdtorySpi)instbndf.impl,
                instbndf.providfr, typf);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw CfrtifidbtfExdfption(typf + " not found", f);
        }
    }

    /**
     * Rfturns b dfrtifidbtf fbdtory objfdt for tif spfdififd
     * dfrtifidbtf typf.
     *
     * <p> A nfw CfrtifidbtfFbdtory objfdt fndbpsulbting tif
     * CfrtifidbtfFbdtorySpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif dfrtifidbtf typf.
     * Sff tif CfrtifidbtfFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtifidbtfFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd dfrtifidbtf typfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn b dfrtifidbtf fbdtory objfdt for tif spfdififd typf.
     *
     * @fxdfption CfrtifidbtfExdfption if b CfrtifidbtfFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif providfr nbmf is null
     *          or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl CfrtifidbtfFbdtory gftInstbndf(String typf,
            String providfr) tirows CfrtifidbtfExdfption,
            NoSudiProvidfrExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtifidbtfFbdtory",
                CfrtifidbtfFbdtorySpi.dlbss, typf, providfr);
            rfturn nfw CfrtifidbtfFbdtory((CfrtifidbtfFbdtorySpi)instbndf.impl,
                instbndf.providfr, typf);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw CfrtifidbtfExdfption(typf + " not found", f);
        }
    }

    /**
     * Rfturns b dfrtifidbtf fbdtory objfdt for tif spfdififd
     * dfrtifidbtf typf.
     *
     * <p> A nfw CfrtifidbtfFbdtory objfdt fndbpsulbting tif
     * CfrtifidbtfFbdtorySpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm typf tif dfrtifidbtf typf.
     * Sff tif CfrtifidbtfFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtifidbtfFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd dfrtifidbtf typfs.
     * @pbrbm providfr tif providfr.
     *
     * @rfturn b dfrtifidbtf fbdtory objfdt for tif spfdififd typf.
     *
     * @fxdfption CfrtifidbtfExdfption if b CfrtifidbtfFbdtorySpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf providfr} is
     *          null.
     *
     * @sff jbvb.sfdurity.Providfr
     *
     * @sindf 1.4
     */
    publid stbtid finbl CfrtifidbtfFbdtory gftInstbndf(String typf,
            Providfr providfr) tirows CfrtifidbtfExdfption {
        try {
            Instbndf instbndf = GftInstbndf.gftInstbndf("CfrtifidbtfFbdtory",
                CfrtifidbtfFbdtorySpi.dlbss, typf, providfr);
            rfturn nfw CfrtifidbtfFbdtory((CfrtifidbtfFbdtorySpi)instbndf.impl,
                instbndf.providfr, typf);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw CfrtifidbtfExdfption(typf + " not found", f);
        }
    }

    /**
     * Rfturns tif providfr of tiis dfrtifidbtf fbdtory.
     *
     * @rfturn tif providfr of tiis dfrtifidbtf fbdtory.
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif nbmf of tif dfrtifidbtf typf bssodibtfd witi tiis
     * dfrtifidbtf fbdtory.
     *
     * @rfturn tif nbmf of tif dfrtifidbtf typf bssodibtfd witi tiis
     * dfrtifidbtf fbdtory.
     */
    publid finbl String gftTypf() {
        rfturn tiis.typf;
    }

    /**
     * Gfnfrbtfs b dfrtifidbtf objfdt bnd initiblizfs it witi
     * tif dbtb rfbd from tif input strfbm {@dodf inStrfbm}.
     *
     * <p>In ordfr to tbkf bdvbntbgf of tif spfdiblizfd dfrtifidbtf formbt
     * supportfd by tiis dfrtifidbtf fbdtory,
     * tif rfturnfd dfrtifidbtf objfdt dbn bf typfdbst to tif dorrfsponding
     * dfrtifidbtf dlbss. For fxbmplf, if tiis dfrtifidbtf
     * fbdtory implfmfnts X.509 dfrtifidbtfs, tif rfturnfd dfrtifidbtf objfdt
     * dbn bf typfdbst to tif {@dodf X509Cfrtifidbtf} dlbss.
     *
     * <p>In tif dbsf of b dfrtifidbtf fbdtory for X.509 dfrtifidbtfs, tif
     * dfrtifidbtf providfd in {@dodf inStrfbm} must bf DER-fndodfd bnd
     * mby bf supplifd in binbry or printbblf (Bbsf64) fndoding. If tif
     * dfrtifidbtf is providfd in Bbsf64 fndoding, it must bf boundfd bt
     * tif bfginning by -----BEGIN CERTIFICATE-----, bnd must bf boundfd bt
     * tif fnd by -----END CERTIFICATE-----.
     *
     * <p>Notf tibt if tif givfn input strfbm dofs not support
     * {@link jbvb.io.InputStrfbm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrfbm#rfsft() rfsft}, tiis mftiod will
     * donsumf tif fntirf input strfbm. Otifrwisf, fbdi dbll to tiis
     * mftiod donsumfs onf dfrtifidbtf bnd tif rfbd position of tif
     * input strfbm is positionfd to tif nfxt bvbilbblf bytf bftfr
     * tif inifrfnt fnd-of-dfrtifidbtf mbrkfr. If tif dbtb in tif input strfbm
     * dofs not dontbin bn inifrfnt fnd-of-dfrtifidbtf mbrkfr (otifr
     * tibn EOF) bnd tifrf is trbiling dbtb bftfr tif dfrtifidbtf is pbrsfd, b
     * {@dodf CfrtifidbtfExdfption} is tirown.
     *
     * @pbrbm inStrfbm bn input strfbm witi tif dfrtifidbtf dbtb.
     *
     * @rfturn b dfrtifidbtf objfdt initiblizfd witi tif dbtb
     * from tif input strfbm.
     *
     * @fxdfption CfrtifidbtfExdfption on pbrsing frrors.
     */
    publid finbl Cfrtifidbtf gfnfrbtfCfrtifidbtf(InputStrfbm inStrfbm)
        tirows CfrtifidbtfExdfption
    {
        rfturn dfrtFbdSpi.fnginfGfnfrbtfCfrtifidbtf(inStrfbm);
    }

    /**
     * Rfturns bn itfrbtion of tif {@dodf CfrtPbti} fndodings supportfd
     * by tiis dfrtifidbtf fbdtory, witi tif dffbult fndoding first. Sff
     * tif CfrtPbti Endodings sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiEndodings">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd fndoding nbmfs bnd tifir formbts.
     * <p>
     * Attfmpts to modify tif rfturnfd {@dodf Itfrbtor} vib its
     * {@dodf rfmovf} mftiod rfsult in bn
     * {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @rfturn bn {@dodf Itfrbtor} ovfr tif nbmfs of tif supportfd
     *         {@dodf CfrtPbti} fndodings (bs {@dodf String}s)
     * @sindf 1.4
     */
    publid finbl Itfrbtor<String> gftCfrtPbtiEndodings() {
        rfturn(dfrtFbdSpi.fnginfGftCfrtPbtiEndodings());
    }

    /**
     * Gfnfrbtfs b {@dodf CfrtPbti} objfdt bnd initiblizfs it witi
     * tif dbtb rfbd from tif {@dodf InputStrfbm} inStrfbm. Tif dbtb
     * is bssumfd to bf in tif dffbult fndoding. Tif nbmf of tif dffbult
     * fndoding is tif first flfmfnt of tif {@dodf Itfrbtor} rfturnfd by
     * tif {@link #gftCfrtPbtiEndodings gftCfrtPbtiEndodings} mftiod.
     *
     * @pbrbm inStrfbm bn {@dodf InputStrfbm} dontbining tif dbtb
     * @rfturn b {@dodf CfrtPbti} initiblizfd witi tif dbtb from tif
     *   {@dodf InputStrfbm}
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs wiilf dfdoding
     * @sindf 1.4
     */
    publid finbl CfrtPbti gfnfrbtfCfrtPbti(InputStrfbm inStrfbm)
        tirows CfrtifidbtfExdfption
    {
        rfturn(dfrtFbdSpi.fnginfGfnfrbtfCfrtPbti(inStrfbm));
    }

    /**
     * Gfnfrbtfs b {@dodf CfrtPbti} objfdt bnd initiblizfs it witi
     * tif dbtb rfbd from tif {@dodf InputStrfbm} inStrfbm. Tif dbtb
     * is bssumfd to bf in tif spfdififd fndoding. Sff
     * tif CfrtPbti Endodings sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtPbtiEndodings">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd fndoding nbmfs bnd tifir formbts.
     *
     * @pbrbm inStrfbm bn {@dodf InputStrfbm} dontbining tif dbtb
     * @pbrbm fndoding tif fndoding usfd for tif dbtb
     * @rfturn b {@dodf CfrtPbti} initiblizfd witi tif dbtb from tif
     *   {@dodf InputStrfbm}
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs wiilf dfdoding or
     *   tif fndoding rfqufstfd is not supportfd
     * @sindf 1.4
     */
    publid finbl CfrtPbti gfnfrbtfCfrtPbti(InputStrfbm inStrfbm,
        String fndoding) tirows CfrtifidbtfExdfption
    {
        rfturn(dfrtFbdSpi.fnginfGfnfrbtfCfrtPbti(inStrfbm, fndoding));
    }

    /**
     * Gfnfrbtfs b {@dodf CfrtPbti} objfdt bnd initiblizfs it witi
     * b {@dodf List} of {@dodf Cfrtifidbtf}s.
     * <p>
     * Tif dfrtifidbtfs supplifd must bf of b typf supportfd by tif
     * {@dodf CfrtifidbtfFbdtory}. Tify will bf dopifd out of tif supplifd
     * {@dodf List} objfdt.
     *
     * @pbrbm dfrtifidbtfs b {@dodf List} of {@dodf Cfrtifidbtf}s
     * @rfturn b {@dodf CfrtPbti} initiblizfd witi tif supplifd list of
     *   dfrtifidbtfs
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs
     * @sindf 1.4
     */
    publid finbl CfrtPbti
        gfnfrbtfCfrtPbti(List<? fxtfnds Cfrtifidbtf> dfrtifidbtfs)
        tirows CfrtifidbtfExdfption
    {
        rfturn(dfrtFbdSpi.fnginfGfnfrbtfCfrtPbti(dfrtifidbtfs));
    }

    /**
     * Rfturns b (possibly fmpty) dollfdtion vifw of tif dfrtifidbtfs rfbd
     * from tif givfn input strfbm {@dodf inStrfbm}.
     *
     * <p>In ordfr to tbkf bdvbntbgf of tif spfdiblizfd dfrtifidbtf formbt
     * supportfd by tiis dfrtifidbtf fbdtory, fbdi flfmfnt in
     * tif rfturnfd dollfdtion vifw dbn bf typfdbst to tif dorrfsponding
     * dfrtifidbtf dlbss. For fxbmplf, if tiis dfrtifidbtf
     * fbdtory implfmfnts X.509 dfrtifidbtfs, tif flfmfnts in tif rfturnfd
     * dollfdtion dbn bf typfdbst to tif {@dodf X509Cfrtifidbtf} dlbss.
     *
     * <p>In tif dbsf of b dfrtifidbtf fbdtory for X.509 dfrtifidbtfs,
     * {@dodf inStrfbm} mby dontbin b sfqufndf of DER-fndodfd dfrtifidbtfs
     * in tif formbts dfsdribfd for
     * {@link #gfnfrbtfCfrtifidbtf(jbvb.io.InputStrfbm) gfnfrbtfCfrtifidbtf}.
     * In bddition, {@dodf inStrfbm} mby dontbin b PKCS#7 dfrtifidbtf
     * dibin. Tiis is b PKCS#7 <i>SignfdDbtb</i> objfdt, witi tif only
     * signifidbnt fifld bfing <i>dfrtifidbtfs</i>. In pbrtidulbr, tif
     * signbturf bnd tif dontfnts brf ignorfd. Tiis formbt bllows multiplf
     * dfrtifidbtfs to bf downlobdfd bt ondf. If no dfrtifidbtfs brf prfsfnt,
     * bn fmpty dollfdtion is rfturnfd.
     *
     * <p>Notf tibt if tif givfn input strfbm dofs not support
     * {@link jbvb.io.InputStrfbm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrfbm#rfsft() rfsft}, tiis mftiod will
     * donsumf tif fntirf input strfbm.
     *
     * @pbrbm inStrfbm tif input strfbm witi tif dfrtifidbtfs.
     *
     * @rfturn b (possibly fmpty) dollfdtion vifw of
     * jbvb.sfdurity.dfrt.Cfrtifidbtf objfdts
     * initiblizfd witi tif dbtb from tif input strfbm.
     *
     * @fxdfption CfrtifidbtfExdfption on pbrsing frrors.
     */
    publid finbl Collfdtion<? fxtfnds Cfrtifidbtf> gfnfrbtfCfrtifidbtfs
            (InputStrfbm inStrfbm) tirows CfrtifidbtfExdfption {
        rfturn dfrtFbdSpi.fnginfGfnfrbtfCfrtifidbtfs(inStrfbm);
    }

    /**
     * Gfnfrbtfs b dfrtifidbtf rfvodbtion list (CRL) objfdt bnd initiblizfs it
     * witi tif dbtb rfbd from tif input strfbm {@dodf inStrfbm}.
     *
     * <p>In ordfr to tbkf bdvbntbgf of tif spfdiblizfd CRL formbt
     * supportfd by tiis dfrtifidbtf fbdtory,
     * tif rfturnfd CRL objfdt dbn bf typfdbst to tif dorrfsponding
     * CRL dlbss. For fxbmplf, if tiis dfrtifidbtf
     * fbdtory implfmfnts X.509 CRLs, tif rfturnfd CRL objfdt
     * dbn bf typfdbst to tif {@dodf X509CRL} dlbss.
     *
     * <p>Notf tibt if tif givfn input strfbm dofs not support
     * {@link jbvb.io.InputStrfbm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrfbm#rfsft() rfsft}, tiis mftiod will
     * donsumf tif fntirf input strfbm. Otifrwisf, fbdi dbll to tiis
     * mftiod donsumfs onf CRL bnd tif rfbd position of tif input strfbm
     * is positionfd to tif nfxt bvbilbblf bytf bftfr tif inifrfnt
     * fnd-of-CRL mbrkfr. If tif dbtb in tif
     * input strfbm dofs not dontbin bn inifrfnt fnd-of-CRL mbrkfr (otifr
     * tibn EOF) bnd tifrf is trbiling dbtb bftfr tif CRL is pbrsfd, b
     * {@dodf CRLExdfption} is tirown.
     *
     * @pbrbm inStrfbm bn input strfbm witi tif CRL dbtb.
     *
     * @rfturn b CRL objfdt initiblizfd witi tif dbtb
     * from tif input strfbm.
     *
     * @fxdfption CRLExdfption on pbrsing frrors.
     */
    publid finbl CRL gfnfrbtfCRL(InputStrfbm inStrfbm)
        tirows CRLExdfption
    {
        rfturn dfrtFbdSpi.fnginfGfnfrbtfCRL(inStrfbm);
    }

    /**
     * Rfturns b (possibly fmpty) dollfdtion vifw of tif CRLs rfbd
     * from tif givfn input strfbm {@dodf inStrfbm}.
     *
     * <p>In ordfr to tbkf bdvbntbgf of tif spfdiblizfd CRL formbt
     * supportfd by tiis dfrtifidbtf fbdtory, fbdi flfmfnt in
     * tif rfturnfd dollfdtion vifw dbn bf typfdbst to tif dorrfsponding
     * CRL dlbss. For fxbmplf, if tiis dfrtifidbtf
     * fbdtory implfmfnts X.509 CRLs, tif flfmfnts in tif rfturnfd
     * dollfdtion dbn bf typfdbst to tif {@dodf X509CRL} dlbss.
     *
     * <p>In tif dbsf of b dfrtifidbtf fbdtory for X.509 CRLs,
     * {@dodf inStrfbm} mby dontbin b sfqufndf of DER-fndodfd CRLs.
     * In bddition, {@dodf inStrfbm} mby dontbin b PKCS#7 CRL
     * sft. Tiis is b PKCS#7 <i>SignfdDbtb</i> objfdt, witi tif only
     * signifidbnt fifld bfing <i>drls</i>. In pbrtidulbr, tif
     * signbturf bnd tif dontfnts brf ignorfd. Tiis formbt bllows multiplf
     * CRLs to bf downlobdfd bt ondf. If no CRLs brf prfsfnt,
     * bn fmpty dollfdtion is rfturnfd.
     *
     * <p>Notf tibt if tif givfn input strfbm dofs not support
     * {@link jbvb.io.InputStrfbm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrfbm#rfsft() rfsft}, tiis mftiod will
     * donsumf tif fntirf input strfbm.
     *
     * @pbrbm inStrfbm tif input strfbm witi tif CRLs.
     *
     * @rfturn b (possibly fmpty) dollfdtion vifw of
     * jbvb.sfdurity.dfrt.CRL objfdts initiblizfd witi tif dbtb from tif input
     * strfbm.
     *
     * @fxdfption CRLExdfption on pbrsing frrors.
     */
    publid finbl Collfdtion<? fxtfnds CRL> gfnfrbtfCRLs(InputStrfbm inStrfbm)
            tirows CRLExdfption {
        rfturn dfrtFbdSpi.fnginfGfnfrbtfCRLs(inStrfbm);
    }
}
