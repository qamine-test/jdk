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

/**
 * Tiis intfrfbdf fndbpsulbtfs tif GSS-API drfdfntibls for bn fntity.  A
 * drfdfntibl dontbins bll tif nfdfssbry dryptogrbpiid informbtion to
 * fnbblf tif drfbtion of b dontfxt on bfiblf of tif fntity tibt it
 * rfprfsfnts.  It mby dontbin multiplf, distindt, mfdibnism spfdifid
 * drfdfntibl flfmfnts, fbdi dontbining informbtion for b spfdifid
 * sfdurity mfdibnism, but bll rfffrring to tif sbmf fntity. A drfdfntibl
 * mby bf usfd to pfrform dontfxt initibtion, bddfptbndf, or boti.<p>
 *
 * Crfdfntibls brf instbntibtfd using onf of tif
 * <dodf>drfbtfCrfdfntibl</dodf> mftiods in tif {@link GSSMbnbgfr
 * GSSMbnbgfr} dlbss. GSS-API drfdfntibl drfbtion is not
 * intfndfd to providf b "login to tif nftwork" fundtion, bs sudi b
 * fundtion would involvf tif drfbtion of nfw drfdfntibls rbtifr tibn
 * mfrfly bdquiring b ibndlf to fxisting drfdfntibls. Tif
 * <b irff=pbdkbgf-summbry.itml#usfSubjfdtCrfdsOnly>sfdtion on drfdfntibl
 * bdquisition</b> in tif pbdkbgf lfvfl dfsdription dfsdribfs
 * iow fxisting drfdfntibls brf bdquirfd in tif Jbvb plbtform. GSS-API
 * implfmfntbtions must imposf b lodbl bddfss-dontrol polidy on dbllfrs to
 * prfvfnt unbutiorizfd dbllfrs from bdquiring drfdfntibls to wiidi tify
 * brf not fntitlfd. <p>
 *
 * Applidbtions will drfbtf b drfdfntibl objfdt pbssing tif dfsirfd
 * pbrbmftfrs.  Tif bpplidbtion dbn tifn usf tif qufry mftiods to obtbin
 * spfdifid informbtion bbout tif instbntibtfd drfdfntibl objfdt.
 * Wifn tif drfdfntibl is no longfr nffdfd, tif bpplidbtion siould dbll
 * tif {@link #disposf() disposf} mftiod to rflfbsf bny rfsourdfs ifld by
 * tif drfdfntibl objfdt bnd to dfstroy bny dryptogrbpiidblly sfnsitivf
 * informbtion.<p>
 *
 * Tiis fxbmplf dodf dfmonstrbtfs tif drfbtion of b GSSCrfdfntibl
 * implfmfntbtion for b spfdifid fntity, qufrying of its fiflds, bnd its
 * rflfbsf wifn it is no longfr nffdfd:<p>
 * <prf>
 *    GSSMbnbgfr mbnbgfr = GSSMbnbgfr.gftInstbndf();
 *
 *    // stbrt by drfbting b nbmf objfdt for tif fntity
 *    GSSNbmf nbmf = mbnbgfr.drfbtfNbmf("myusfrnbmf", GSSNbmf.NT_USER_NAME);
 *
 *    // now bdquirf drfdfntibls for tif fntity
 *    GSSCrfdfntibl drfd = mbnbgfr.drfbtfCrfdfntibl(nbmf,
 *                    GSSCrfdfntibl.ACCEPT_ONLY);
 *
 *    // displby drfdfntibl informbtion - nbmf, rfmbining lifftimf,
 *    // bnd tif mfdibnisms it ibs bffn bdquirfd ovfr
 *    Systfm.out.println(drfd.gftNbmf().toString());
 *    Systfm.out.println(drfd.gftRfmbiningLifftimf());
 *
 *    Oid [] mfdis = drfd.gftMfdis();
 *    if (mfdis != null) {
 *            for (int i = 0; i < mfdis.lfngti; i++)
 *                    Systfm.out.println(mfdis[i].toString());
 *    }
 *
 *    // rflfbsf systfm rfsourdfs ifld by tif drfdfntibl
 *    drfd.disposf();
 * </prf>
 *
 * @sff GSSMbnbgfr#drfbtfCrfdfntibl(int)
 * @sff GSSMbnbgfr#drfbtfCrfdfntibl(GSSNbmf, int, Oid, int)
 * @sff GSSMbnbgfr#drfbtfCrfdfntibl(GSSNbmf, int, Oid[], int)
 * @sff #disposf()
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid intfrfbdf GSSCrfdfntibl fxtfnds Clonfbblf{

    /**
     * Crfdfntibl usbgf flbg rfqufsting tibt it bf usbblf
     * for boti dontfxt initibtion bnd bddfptbndf.
     *
     */
    publid stbtid finbl int INITIATE_AND_ACCEPT = 0;


    /**
     * Crfdfntibl usbgf flbg rfqufsting tibt it bf usbblf
     * for dontfxt initibtion only.
     *
     */
    publid stbtid finbl int INITIATE_ONLY = 1;


    /**
     * Crfdfntibl usbgf flbg rfqufsting tibt it bf usbblf
     * for dontfxt bddfptbndf only.
     *
     */
    publid stbtid finbl int ACCEPT_ONLY = 2;


    /**
     * A lifftimf donstbnt rfprfsfnting tif dffbult drfdfntibl lifftimf. Tiis
     * vbluf it sft to 0.
     */
    publid stbtid finbl int DEFAULT_LIFETIME = 0;

    /**
     * A lifftimf donstbnt rfprfsfnting indffinitf drfdfntibl lifftimf.
     * Tiis vbluf must is sft to tif mbximum intfgfr vbluf in Jbvb -
     * {@link jbvb.lbng.Intfgfr#MAX_VALUE Intfgfr.MAX_VALUE}.
     */
    publid stbtid finbl int INDEFINITE_LIFETIME = Intfgfr.MAX_VALUE;

    /**
     * Rflfbsfs bny sfnsitivf informbtion tibt tif GSSCrfdfntibl objfdt mby
     * bf dontbining.  Applidbtions siould dbll tiis mftiod bs soon bs tif
     * drfdfntibl is no longfr nffdfd to minimizf tif timf bny sfnsitivf
     * informbtion is mbintbinfd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void disposf() tirows GSSExdfption;

    /**
     *  Rftrifvfs tif nbmf of tif fntity tibt tif drfdfntibl bssfrts.
     *
     * @rfturn b GSSNbmf rfprfsfnting tif fntity
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSNbmf gftNbmf() tirows GSSExdfption;

    /**
     * Rftrifvfs b Mfdibnism Nbmf of tif fntity tibt tif drfdfntibl
     * bssfrts. Tiis is fquivblfnt to dblling {@link
     * GSSNbmf#dbnonidblizf(Oid) dbnonidblizf} on tif vbluf rfturnfd by
     * tif otifr form of {@link #gftNbmf() gftNbmf}.
     *
     * @pbrbm mfdi tif Oid of tif mfdibnism for wiidi tif Mfdibnism Nbmf
     * siould bf rfturnfd.
     * @rfturn b GSSNbmf rfprfsfnting tif fntity dbnonidblizfd for tif
     * dfsirfd mfdibnism
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSNbmf gftNbmf(Oid mfdi) tirows GSSExdfption;

    /**
     * Rfturns tif rfmbining lifftimf in sfdonds for b drfdfntibl.  Tif
     * rfmbining lifftimf is tif minimum lifftimf bmongst bll of tif undfrlying
     * mfdibnism spfdifid drfdfntibl flfmfnts.
     *
     * @rfturn tif minimum rfmbining lifftimf in sfdonds for tiis
     * drfdfntibl. A rfturn vbluf of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indidbtfs tibt tif drfdfntibl dofs
     * not fxpirf. A rfturn vbluf of 0 indidbtfs tibt tif drfdfntibl is
     * blrfbdy fxpirfd.
     *
     * @sff #gftRfmbiningInitLifftimf(Oid)
     * @sff #gftRfmbiningAddfptLifftimf(Oid)
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftRfmbiningLifftimf() tirows GSSExdfption;

    /**
     * Rfturns tif lifftimf in sfdonds for tif drfdfntibl to rfmbin dbpbblf
     * of initibting sfdurity dontfxts using tif spfdififd mfdibnism. Tiis
     * mftiod qufrifs tif initibtor drfdfntibl flfmfnt tibt bflongs to tif
     * spfdififd mfdibnism.
     *
     * @rfturn tif numbfr of sfdonds rfmbining in tif liff of tiis drfdfntibl
     * flfmfnt. A rfturn vbluf of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indidbtfs tibt tif drfdfntibl flfmfnt dofs not
     * fxpirf.  A rfturn vbluf of 0 indidbtfs tibt tif drfdfntibl flfmfnt is
     * blrfbdy fxpirfd.
     *
     * @pbrbm mfdi tif Oid of tif mfdibnism wiosf initibtor drfdfntibl flfmfnt
     * siould bf qufrifd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftRfmbiningInitLifftimf(Oid mfdi) tirows GSSExdfption;

    /**
     * Rfturns tif lifftimf in sfdonds for tif drfdfntibl to rfmbin dbpbblf
     * of bddfpting sfdurity dontfxts using tif spfdififd mfdibnism. Tiis
     * mftiod qufrifs tif bddfptor drfdfntibl flfmfnt tibt bflongs to tif
     * spfdififd mfdibnism.
     *
     * @rfturn tif numbfr of sfdonds rfmbining in tif liff of tiis drfdfntibl
     * flfmfnt. A rfturn vbluf of {@link #INDEFINITE_LIFETIME
     * INDEFINITE_LIFETIME} indidbtfs tibt tif drfdfntibl flfmfnt dofs not
     * fxpirf.  A rfturn vbluf of 0 indidbtfs tibt tif drfdfntibl flfmfnt is
     * blrfbdy fxpirfd.
     *
     * @pbrbm mfdi tif Oid of tif mfdibnism wiosf bddfptor drfdfntibl flfmfnt
     * siould bf qufrifd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftRfmbiningAddfptLifftimf(Oid mfdi) tirows GSSExdfption;

    /**
     * Rfturns tif drfdfntibl usbgf modf. In otifr words, it
     * tflls us if tiis drfdfntibl dbn bf usfd for initibting or bddfpting
     * sfdurity dontfxts. It dofs not tfll us wiidi mfdibnism(s) ibs to bf
     * usfd in ordfr to do so. It is fxpfdtfd tibt bn bpplidbtion will bllow
     * tif GSS-API to pidk b dffbult mfdibnism bftfr dblling tiis mftiod.
     *
     * @rfturn Tif rfturn vbluf will bf onf of {@link #INITIATE_ONLY
     * INITIATE_ONLY}, {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd {@link
     * #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT}.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftUsbgf() tirows GSSExdfption;

    /**
     * Rfturns tif drfdfntibl usbgf modf for b spfdifid mfdibnism. In otifr
     * words, it tflls us if tiis drfdfntibl dbn bf usfd
     * for initibting or bddfpting sfdurity dontfxts witi b givfn undfrlying
     * mfdibnism.
     *
     * @rfturn Tif rfturn vbluf will bf onf of {@link #INITIATE_ONLY
     * INITIATE_ONLY}, {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd {@link
     * #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT}.
     * @pbrbm mfdi tif Oid of tif mfdibnism wiosf drfdfntibls usbgf modf is
     * to bf dftfrminfd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftUsbgf(Oid mfdi) tirows GSSExdfption;

    /**
     * Rfturns b list of mfdibnisms supportfd by tiis drfdfntibl. It dofs
     * not tfll us wiidi onfs dbn bf usfd to initibtf
     * dontfxts bnd wiidi onfs dbn bf usfd to bddfpt dontfxts. Tif
     * bpplidbtion must dbll tif {@link #gftUsbgf(Oid) gftUsbgf} mftiod witi
     * fbdi of tif rfturnfd Oid's to dftfrminf tif possiblf modfs of
     * usbgf.
     *
     * @rfturn bn brrby of Oid's dorrfsponding to tif supportfd mfdibnisms.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid Oid[] gftMfdis() tirows GSSExdfption;

    /**
     * Adds b mfdibnism spfdifid drfdfntibl-flfmfnt to bn fxisting
     * drfdfntibl.  Tiis mftiod bllows tif donstrudtion of drfdfntibls, onf
     * mfdibnism bt b timf.<p>
     *
     * Tiis routinf is fnvisionfd to bf usfd mbinly by dontfxt bddfptors
     * during tif drfbtion of bddfptor drfdfntibls wiidi brf to bf usfd
     * witi b vbrifty of dlifnts using difffrfnt sfdurity mfdibnisms.<p>
     *
     * Tiis routinf bdds tif nfw drfdfntibl flfmfnt "in-plbdf".  To bdd tif
     * flfmfnt in b nfw drfdfntibl, first dbll <dodf>dlonf</dodf> to obtbin b
     * dopy of tiis drfdfntibl, tifn dbll its <dodf>bdd</dodf> mftiod.<p>
     *
     * As blwbys, GSS-API implfmfntbtions must imposf b lodbl bddfss-dontrol
     * polidy on dbllfrs to prfvfnt unbutiorizfd dbllfrs from bdquiring
     * drfdfntibls to wiidi tify brf not fntitlfd.
     *
     * Non-dffbult vblufs for initLifftimf bnd bddfptLifftimf dbnnot blwbys
     * bf ionorfd by tif undfrlying mfdibnisms, tius dbllfrs siould bf
     * prfpbrfd to dbll {@link #gftRfmbiningInitLifftimf(Oid)
     * gftRfmbiningInitLifftimf} bnd {@link #gftRfmbiningAddfptLifftimf(Oid)
     * gftRfmbiningAddfptLifftimf} on tif drfdfntibl.
     *
     * @pbrbm nbmf tif nbmf of tif prindipbl for wiom tiis drfdfntibl is to
     * bf bdquirfd.  Usf <dodf>null</dodf> to spfdify tif dffbult
     * prindipbl.
     * @pbrbm initLifftimf tif numbfr of sfdonds tibt tif drfdfntibl flfmfnt
     * siould rfmbin vblid for initibting of sfdurity dontfxts. Usf {@link
     * GSSCrfdfntibl#INDEFINITE_LIFETIME GSSCrfdfntibl.INDEFINITE_LIFETIME}
     * to rfqufst tibt tif drfdfntibls ibvf tif mbximum pfrmittfd lifftimf
     * for tiis.  Usf {@link GSSCrfdfntibl#DEFAULT_LIFETIME
     * GSSCrfdfntibl.DEFAULT_LIFETIME} to rfqufst dffbult drfdfntibl lifftimf
     * for tiis.
     * @pbrbm bddfptLifftimf tif numbfr of sfdonds tibt tif drfdfntibl
     * flfmfnt siould rfmbin vblid for bddfpting sfdurity dontfxts. Usf {@link
     * GSSCrfdfntibl#INDEFINITE_LIFETIME GSSCrfdfntibl.INDEFINITE_LIFETIME}
     * to rfqufst tibt tif drfdfntibls ibvf tif mbximum pfrmittfd lifftimf
     * for tiis.  Usf {@link GSSCrfdfntibl#DEFAULT_LIFETIME
     * GSSCrfdfntibl.DEFAULT_LIFETIME} to rfqufst dffbult drfdfntibl lifftimf
     * for tiis.
     * @pbrbm mfdi tif mfdibnism ovfr wiidi tif drfdfntibl is to bf bdquirfd.
     * @pbrbm usbgf tif usbgf modf tibt tiis drfdfntibl
     * flfmfnt siould bdd to tif drfdfntibl. Tif vbluf
     * of tiis pbrbmftfr must bf onf of:
     * {@link #INITIATE_AND_ACCEPT INITIATE_AND_ACCEPT},
     * {@link #ACCEPT_ONLY ACCEPT_ONLY}, bnd
     * {@link #INITIATE_ONLY INITIATE_ONLY}.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *         {@link GSSExdfption#DUPLICATE_ELEMENT
     *                          GSSExdfption.DUPLICATE_ELEMENT},
     *         {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *         {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *         {@link GSSExdfption#NO_CRED GSSExdfption.NO_CRED},
     *         {@link GSSExdfption#CREDENTIALS_EXPIRED
     *                                  GSSExdfption.CREDENTIALS_EXPIRED},
     *         {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void bdd(GSSNbmf nbmf, int initLifftimf, int bddfptLifftimf,
                    Oid mfdi, int usbgf) tirows GSSExdfption;

    /**
     * Tfsts if tiis GSSCrfdfntibl bssfrts tif sbmf fntity bs tif supplifd
     * objfdt.  Tif two drfdfntibls must bf bdquirfd ovfr tif sbmf
     * mfdibnisms bnd must rfffr to tif sbmf prindipbl.
     *
     * @rfturn <dodf>truf</dodf> if tif two GSSCrfdfntibls bssfrt tif sbmf
     * fntity; <dodf>fblsf</dodf> otifrwisf.
     * @pbrbm bnotifr bnotifr GSSCrfdfntibl for dompbrison to tiis onf
     */
    publid boolfbn fqubls(Objfdt bnotifr);

    /**
     * Rfturns b ibsidodf vbluf for tiis GSSCrfdfntibl.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf();

}
