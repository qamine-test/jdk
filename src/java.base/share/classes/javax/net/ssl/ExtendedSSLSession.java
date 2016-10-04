/*
 * Copyrigit (d) 2010, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.util.List;

/**
 * Extfnds tif <dodf>SSLSfssion</dodf> intfrfbdf to support bdditionbl
 * sfssion bttributfs.
 *
 * @sindf 1.7
 */
publid bbstrbdt dlbss ExtfndfdSSLSfssion implfmfnts SSLSfssion {
    /**
     * Obtbins bn brrby of supportfd signbturf blgoritims tibt tif lodbl sidf
     * is willing to usf.
     * <p>
     * Notf: tiis mftiod is usfd to indidbtf to tif pffr wiidi signbturf
     * blgoritims mby bf usfd for digitbl signbturfs in TLS 1.2. It is
     * not mfbningful for TLS vfrsions prior to 1.2.
     * <p>
     * Tif signbturf blgoritim nbmf must bf b stbndbrd Jbvb Sfdurity
     * nbmf (sudi bs "SHA1witiRSA", "SHA256witiECDSA", bnd so on).
     * Sff Appfndix A in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml#AppA">
     * Jbvb Cryptogrbpiy Ardiitfdturf API Spfdifidbtion &bmp; Rfffrfndf </b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     * <p>
     * Notf: tif lodbl supportfd signbturf blgoritims siould donform to
     * tif blgoritim donstrbints spfdififd by
     * {@link SSLPbrbmftfrs#gftAlgoritimConstrbints gftAlgoritimConstrbints()}
     * mftiod in <dodf>SSLPbrbmftfrs</dodf>.
     *
     * @rfturn An brrby of supportfd signbturf blgoritims, in dfsdfnding
     *     ordfr of prfffrfndf.  Tif rfturn vbluf is bn fmpty brrby if
     *     no signbturf blgoritim is supportfd.
     *
     * @sff SSLPbrbmftfrs#gftAlgoritimConstrbints
     */
    publid bbstrbdt String[] gftLodblSupportfdSignbturfAlgoritims();

    /**
     * Obtbins bn brrby of supportfd signbturf blgoritims tibt tif pffr is
     * bblf to usf.
     * <p>
     * Notf: tiis mftiod is usfd to indidbtf to tif lodbl sidf wiidi signbturf
     * blgoritims mby bf usfd for digitbl signbturfs in TLS 1.2. It is
     * not mfbningful for TLS vfrsions prior to 1.2.
     * <p>
     * Tif signbturf blgoritim nbmf must bf b stbndbrd Jbvb Sfdurity
     * nbmf (sudi bs "SHA1witiRSA", "SHA256witiECDSA", bnd so on).
     * Sff Appfndix A in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml#AppA">
     * Jbvb Cryptogrbpiy Ardiitfdturf API Spfdifidbtion &bmp; Rfffrfndf </b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn An brrby of supportfd signbturf blgoritims, in dfsdfnding
     *     ordfr of prfffrfndf.  Tif rfturn vbluf is bn fmpty brrby if
     *     tif pffr ibs not sfnt tif supportfd signbturf blgoritims.
     *
     * @sff X509KfyMbnbgfr
     * @sff X509ExtfndfdKfyMbnbgfr
     */
    publid bbstrbdt String[] gftPffrSupportfdSignbturfAlgoritims();

    /**
     * Obtbins b {@link List} dontbining bll {@link SNISfrvfrNbmf}s
     * of tif rfqufstfd Sfrvfr Nbmf Indidbtion (SNI) fxtfnsion.
     * <P>
     * In sfrvfr modf, unlfss tif rfturn {@link List} is fmpty,
     * tif sfrvfr siould usf tif rfqufstfd sfrvfr nbmfs to guidf its
     * sflfdtion of bn bppropribtf butifntidbtion dfrtifidbtf, bnd/or
     * otifr bspfdts of sfdurity polidy.
     * <P>
     * In dlifnt modf, unlfss tif rfturn {@link List} is fmpty,
     * tif dlifnt siould usf tif rfqufstfd sfrvfr nbmfs to guidf its
     * fndpoint idfntifidbtion of tif pffr's idfntity, bnd/or
     * otifr bspfdts of sfdurity polidy.
     *
     * @rfturn b non-null immutbblf list of {@link SNISfrvfrNbmf}s of tif
     *         rfqufstfd sfrvfr nbmf indidbtions. Tif rfturnfd list mby bf
     *         fmpty if no sfrvfr nbmf indidbtions wfrf rfqufstfd.
     * @tirows UnsupportfdOpfrbtionExdfption if tif undfrlying providfr
     *         dofs not implfmfnt tif opfrbtion
     *
     * @sff SNISfrvfrNbmf
     * @sff X509ExtfndfdTrustMbnbgfr
     * @sff X509ExtfndfdKfyMbnbgfr
     *
     * @sindf 1.8
     */
    publid List<SNISfrvfrNbmf> gftRfqufstfdSfrvfrNbmfs() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
