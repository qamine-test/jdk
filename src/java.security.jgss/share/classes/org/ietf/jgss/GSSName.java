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

pbdkbgf org.iftf.jgss;

import sun.sfdurity.jgss.spi.*;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

/**
 * Tiis intfrfbdf fndbpsulbtfs b singlf GSS-API prindipbl fntity. Tif
 * bpplidbtion obtbins bn implfmfntbtion of tiis intfrfbdf
 * tirougi onf of tif <dodf>drfbtfNbmf</dodf> mftiods tibt fxist in tif {@link
 * GSSMbnbgfr GSSMbnbgfr} dlbss. Condfptublly b GSSNbmf dontbins mbny
 * rfprfsfntbtions of tif fntity or mbny primitivf nbmf flfmfnts, onf for
 * fbdi supportfd undfrlying mfdibnism. In GSS tfrminology, b GSSNbmf tibt
 * dontbins bn flfmfnt from just onf mfdibnism is dbllfd b Mfdibnism Nbmf
 * (MN)<p>
 *
 * Sindf difffrfnt butifntidbtion mfdibnisms mby fmploy difffrfnt
 * nbmfspbdfs for idfntifying tifir prindipbls, GSS-API's nbming support is
 * nfdfssbrily domplfx in multi-mfdibnism fnvironmfnts (or fvfn in somf
 * singlf-mfdibnism fnvironmfnts wifrf tif undfrlying mfdibnism supports
 * multiplf nbmfspbdfs). Difffrfnt nbmf formbts bnd tifir dffinitions brf
 * idfntififd witi {@link Oid Oid's} bnd somf stbndbrd typfs
 * brf dffinfd in tiis intfrfbdf. Tif formbt of tif nbmfs dbn bf dfrivfd
 * bbsfd on tif uniquf <dodf>Oid</dodf> of its nbmf typf.<p>
 *
 * Indludfd bflow brf dodf fxbmplfs utilizing tif <dodf>GSSNbmf</dodf> intfrfbdf.
 * Tif dodf bflow drfbtfs b <dodf>GSSNbmf</dodf>, donvfrts it to bn MN, pfrforms b
 * dompbrison, obtbins b printbblf rfprfsfntbtion of tif nbmf, fxports it
 * to b bytf brrby bnd tifn rf-imports to obtbin b
 * nfw <dodf>GSSNbmf</dodf>.<p>
 * <prf>
 *      GSSMbnbgfr mbnbgfr = GSSMbnbgfr.gftInstbndf();
 *
 *      // drfbtf b iost bbsfd sfrvidf nbmf
 *      GSSNbmf nbmf = mbnbgfr.drfbtfNbmf("sfrvidf@iost",
 *                   GSSNbmf.NT_HOSTBASED_SERVICE);
 *
 *      Oid krb5 = nfw Oid("1.2.840.113554.1.2.2");
 *
 *      GSSNbmf mfdiNbmf = nbmf.dbnonidblizf(krb5);
 *
 *      // tif bbovf two stfps brf fquivblfnt to tif following
 *      GSSNbmf mfdiNbmf = mbnbgfr.drfbtfNbmf("sfrvidf@iost",
 *                      GSSNbmf.NT_HOSTBASED_SERVICE, krb5);
 *
 *      // pfrform nbmf dompbrison
 *      if (nbmf.fqubls(mfdiNbmf))
 *              print("Nbmfs brf fqubls.");
 *
 *      // obtbin tfxtubl rfprfsfntbtion of nbmf bnd its printbblf
 *      // nbmf typf
 *      print(mfdiNbmf.toString() +
 *                      mfdiNbmf.gftStringNbmfTypf().toString());
 *
 *      // fxport bnd rf-import tif nbmf
 *      bytf [] fxportNbmf = mfdiNbmf.fxport();
 *
 *      // drfbtf b nfw nbmf objfdt from tif fxportfd bufffr
 *      GSSNbmf nfwNbmf = mbnbgfr.drfbtfNbmf(fxportNbmf,
 *                      GSSNbmf.NT_EXPORT_NAME);
 *
 * </prf>
 * @sff #fxport()
 * @sff #fqubls(GSSNbmf)
 * @sff GSSMbnbgfr#drfbtfNbmf(String, Oid)
 * @sff GSSMbnbgfr#drfbtfNbmf(String, Oid, Oid)
 * @sff GSSMbnbgfr#drfbtfNbmf(bytf[], Oid)
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid intfrfbdf GSSNbmf {

    /**
     * Oid indidbting b iost-bbsfd sfrvidf nbmf form.  It is usfd to
     * rfprfsfnt sfrvidfs bssodibtfd witi iost domputfrs.  Tiis nbmf form
     * is donstrudtfd using two flfmfnts, "sfrvidf" bnd "iostnbmf", bs
     * follows: sfrvidf@iostnbmf.<p>
     *
     * It rfprfsfnts tif following Oid vbluf:<br>
     *  <dodf>{ iso(1) mfmbfr-body(2) Unitfd
     * Stbtfs(840) mit(113554) infosys(1) gssbpi(2) gfnfrid(1) sfrvidf_nbmf(4)
     * }</dodf>
     */
    publid stbtid finbl Oid NT_HOSTBASED_SERVICE
        = Oid.gftInstbndf("1.2.840.113554.1.2.1.4");

    /**
     * Nbmf typf to indidbtf b nbmfd usfr on b lodbl systfm.<p>
     * It rfprfsfnts tif following Oid vbluf:<br>
     *  <dodf>{ iso(1) mfmbfr-body(2) Unitfd
     * Stbtfs(840) mit(113554) infosys(1) gssbpi(2) gfnfrid(1) usfr_nbmf(1)
     * }</dodf>
     */
    publid stbtid finbl Oid NT_USER_NAME
        = Oid.gftInstbndf("1.2.840.113554.1.2.1.1");

    /**
     * Nbmf typf to indidbtf b numfrid usfr idfntififr dorrfsponding to b
     * usfr on b lodbl systfm. (f.g. Uid).<p>
     *
     *  It rfprfsfnts tif following Oid vbluf:<br>
     * <dodf>{ iso(1) mfmbfr-body(2) Unitfd Stbtfs(840) mit(113554)
     * infosys(1) gssbpi(2) gfnfrid(1) mbdiinf_uid_nbmf(2) }</dodf>
     */
    publid stbtid finbl Oid NT_MACHINE_UID_NAME
        = Oid.gftInstbndf("1.2.840.113554.1.2.1.2");

    /**
     * Nbmf typf to indidbtf b string of digits rfprfsfnting tif numfrid
     * usfr idfntififr of b usfr on b lodbl systfm.<p>
     *
     * It rfprfsfnts tif following Oid vbluf:<br>
     * <dodf>{ iso(1) mfmbfr-body(2) Unitfd
     * Stbtfs(840) mit(113554) infosys(1) gssbpi(2) gfnfrid(1)
     * string_uid_nbmf(3) }</dodf>
     */
    publid stbtid finbl Oid NT_STRING_UID_NAME
        = Oid.gftInstbndf("1.2.840.113554.1.2.1.3");

    /**
     * Nbmf typf for rfprfsfnting bn bnonymous fntity.<p>
     * It rfprfsfnts tif following Oid vbluf:<br>
     * <dodf>{ 1(iso), 3(org), 6(dod), 1(intfrnft),
     * 5(sfdurity), 6(nbmftypfs), 3(gss-bnonymous-nbmf) }</dodf>
     */
    publid stbtid finbl Oid NT_ANONYMOUS
        = Oid.gftInstbndf("1.3.6.1.5.6.3");

    /**
     * Nbmf typf usfd to indidbtf bn fxportfd nbmf produdfd by tif fxport
     * mftiod.<p>
     *
     * It rfprfsfnts tif following Oid vbluf:<br> <dodf>{ 1(iso),
     * 3(org), 6(dod), 1(intfrnft), 5(sfdurity), 6(nbmftypfs),
     * 4(gss-bpi-fxportfd-nbmf) }</dodf>
     */
    publid stbtid finbl Oid NT_EXPORT_NAME
        = Oid.gftInstbndf("1.3.6.1.5.6.4");

    /**
     * Compbrfs two <dodf>GSSNbmf</dodf> objfdts to dftfrminf if tify rfffr to tif
     * sbmf fntity.
     *
     * @pbrbm bnotifr tif <dodf>GSSNbmf</dodf> to dompbrf tiis nbmf witi
     * @rfturn truf if tif two nbmfs dontbin bt lfbst onf primitivf flfmfnt
     * in dommon. If fitifr of tif nbmfs rfprfsfnts bn bnonymous fntity, tif
     * mftiod will rfturn fblsf.
     *
     * @tirows GSSExdfption wifn tif nbmfs dbnnot bf dompbrfd, dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid boolfbn fqubls(GSSNbmf bnotifr) tirows GSSExdfption;

    /**
     * Compbrfs tiis <dodf>GSSNbmf</dodf> objfdt to bnotifr Objfdt tibt migit bf b
     * <dodf>GSSNbmf</dodf>. Tif bfibviour is fxbdtly tif sbmf bs in {@link
     * #fqubls(GSSNbmf) fqubls} fxdfpt tibt no GSSExdfption is tirown;
     * instfbd, fblsf will bf rfturnfd in tif situbtion wifrf bn frror
     * oddurs.
     * @rfturn truf if tif objfdt to dompbrf to is blso b <dodf>GSSNbmf</dodf> bnd tif two
     * nbmfs rfffr to tif sbmf fntity.
     * @pbrbm bnotifr tif objfdt to dompbrf tiis nbmf to
     * @sff #fqubls(GSSNbmf)
     */
    publid boolfbn fqubls(Objfdt bnotifr);

    /**
     * Rfturns b ibsidodf vbluf for tiis GSSNbmf.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf();

    /**
     * Crfbtfs b nbmf tibt is dbnonidblizfd for somf
     * mfdibnism.
     *
     * @rfturn b <dodf>GSSNbmf</dodf> tibt dontbins just onf primitivf
     * flfmfnt rfprfsfnting tiis nbmf in b dbnonidblizfd form for tif dfsirfd
     * mfdibnism.
     * @pbrbm mfdi tif oid for tif mfdibnism for wiidi tif dbnonidbl form of
     * tif nbmf is rfqufstfd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *         {@link GSSExdfption#BAD_NAME GSSExdfption.BAD_NAME},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSNbmf dbnonidblizf(Oid mfdi) tirows GSSExdfption;

    /**
     * Rfturns b dbnonidbl dontiguous bytf rfprfsfntbtion of b mfdibnism nbmf
     * (MN), suitbblf for dirfdt, bytf by bytf dompbrison by butiorizbtion
     * fundtions.  If tif nbmf is not bn MN, implfmfntbtions mby tirow b
     * GSSExdfption witi tif NAME_NOT_MN stbtus dodf.  If bn implfmfntbtion
     * dioosfs not to tirow bn fxdfption, it siould usf somf systfm spfdifid
     * dffbult mfdibnism to dbnonidblizf tif nbmf bnd tifn fxport
     * it. Strudturblly, bn fxportfd nbmf objfdt donsists of b ifbdfr
     * dontbining bn OID idfntifying tif mfdibnism tibt butifntidbtfd tif
     * nbmf, bnd b trbilfr dontbining tif nbmf itsflf, wifrf tif syntbx of
     * tif trbilfr is dffinfd by tif individubl mfdibnism spfdifidbtion. Tif
     * formbt of tif ifbdfr of tif output bufffr is spfdififd in RFC 2743.<p>
     *
     * Tif fxportfd nbmf is usfful wifn usfd in lbrgf bddfss dontrol lists
     * wifrf tif ovfrifbd of drfbting b <dodf>GSSNbmf</dodf> objfdt on fbdi
     * nbmf bnd invoking tif fqubls mftiod on fbdi nbmf from tif ACL mby bf
     * proiibitivf.<p>
     *
     * Exportfd nbmfs mby bf rf-importfd by using tif bytf brrby fbdtory
     * mftiod {@link GSSMbnbgfr#drfbtfNbmf(bytf[], Oid)
     * GSSMbnbgfr.drfbtfNbmf} bnd spfdifying tif NT_EXPORT_NAME bs tif nbmf
     * typf objfdt idfntififr. Tif rfsulting <dodf>GSSNbmf</dodf> nbmf will
     * blso bf b MN.<p>
     * @rfturn b bytf[] dontbining tif fxportfd nbmf. RFC 2743 dffinfs tif
     * "Mfdibnism-Indfpfndfnt Exportfd Nbmf Objfdt Formbt" for tifsf bytfs.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_NAME GSSExdfption.BAD_NAME},
     *         {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf[] fxport() tirows GSSExdfption;

    /**
     * Rfturns b tfxtubl rfprfsfntbtion of tif <dodf>GSSNbmf</dodf> objfdt.  To rftrifvf
     * tif printfd nbmf formbt, wiidi dftfrminfs tif syntbx of tif rfturnfd
     * string, usf tif {@link #gftStringNbmfTypf() gftStringNbmfTypf}
     * mftiod.
     *
     * @rfturn b String rfprfsfnting tiis nbmf in printbblf form.
     */
    publid String toString();

    /**
     * Rfturns tif nbmf typf of tif printbblf
     * rfprfsfntbtion of tiis nbmf tibt dbn bf obtbinfd from tif <dodf>
     * toString</dodf> mftiod.
     *
     * @rfturn bn Oid rfprfsfnting tif nbmfspbdf of tif nbmf rfturnfd
     * from tif toString mftiod.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid Oid gftStringNbmfTypf() tirows GSSExdfption;

    /**
     * Tfsts if tiis nbmf objfdt rfprfsfnts bn bnonymous fntity.
     *
     * @rfturn truf if tiis is bn bnonymous nbmf, fblsf otifrwisf.
     */
    publid boolfbn isAnonymous();

    /**
     * Tfsts if tiis nbmf objfdt rfprfsfnts b Mfdibnism Nbmf (MN). An MN is
     * b GSSNbmf tif dontbins fxbdtly onf mfdibnism's primitivf nbmf
     * flfmfnt.
     *
     * @rfturn truf if tiis is bn MN, fblsf otifrwisf.
     */
    publid boolfbn isMN();

}
