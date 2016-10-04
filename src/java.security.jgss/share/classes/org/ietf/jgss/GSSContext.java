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
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;

/**
 * Tiis intfrfbdf fndbpsulbtfs tif GSS-API sfdurity dontfxt bnd providfs
 * tif sfdurity sfrvidfs tibt brf bvbilbblf ovfr tif dontfxt.  Sfdurity
 * dontfxts brf fstbblisifd bftwffn pffrs using lodblly bdquirfd
 * drfdfntibls.  Multiplf dontfxts mby fxist simultbnfously bftwffn b pbir
 * of pffrs, using tif sbmf or difffrfnt sft of drfdfntibls.  GSS-API
 * fundtions in b mbnnfr indfpfndfnt of tif undfrlying trbnsport protodol
 * bnd dfpfnds on its dblling bpplidbtion to trbnsport tif tokfns tibt brf
 * gfnfrbtfd by tif sfdurity dontfxt bftwffn tif pffrs.<p>
 *
 * If tif dbllfr instbntibtfs tif dontfxt using tif dffbult
 * <dodf>GSSMbnbgfr</dodf> instbndf, tifn tif Kfrbfros v5 GSS-API mfdibnism
 * is gubrbntffd to bf bvbilbblf for dontfxt fstbblisimfnt. Tiis mfdibnism
 * is idfntififd by tif Oid "1.2.840.113554.1.2.2" bnd is dffinfd in RFC
 * 1964.<p>
 *
 * Bfforf tif dontfxt fstbblisimfnt pibsf is initibtfd, tif dontfxt
 * initibtor mby rfqufst spfdifid dibrbdtfristids dfsirfd of tif
 * fstbblisifd dontfxt. Not bll undfrlying mfdibnisms support bll
 * dibrbdtfristids tibt b dbllfr migit dfsirf. Aftfr tif dontfxt is
 * fstbblisifd, tif dbllfr dbn difdk tif bdtubl dibrbdtfristids bnd sfrvidfs
 * offfrfd by tibt dontfxt by mfbns of vbrious qufry mftiods. Wifn using
 * tif Kfrbfros v5 GSS-API mfdibnism offfrfd by tif dffbult
 * <dodf>GSSMbnbgfr</dodf> instbndf, bll optionbl sfrvidfs will bf
 * bvbilbblf lodblly. Tify brf mutubl butifntidbtion, drfdfntibl
 * dflfgbtion, donfidfntiblity bnd intfgrity protfdtion, bnd pfr-mfssbgf
 * rfplby dftfdtion bnd sfqufnding. Notf tibt in tif GSS-API, mfssbgf intfgrity
 * is b prfrfquisitf for mfssbgf donfidfntiblity.<p>
 *
 * Tif dontfxt fstbblisimfnt oddurs in b loop wifrf tif
 * initibtor dblls {@link #initSfdContfxt(bytf[], int, int) initSfdContfxt}
 * bnd tif bddfptor dblls {@link #bddfptSfdContfxt(bytf[], int, int)
 * bddfptSfdContfxt} until tif dontfxt is fstbblisifd. Wiilf in tiis loop
 * tif <dodf>initSfdContfxt</dodf> bnd <dodf>bddfptSfdContfxt</dodf>
 * mftiods produdf tokfns tibt tif bpplidbtion sfnds ovfr to tif pffr. Tif
 * pffr pbssfs bny sudi tokfn bs input to its <dodf>bddfptSfdContfxt</dodf>
 * or <dodf>initSfdContfxt</dodf> bs tif dbsf mby bf.<p>
 *
 * During tif dontfxt fstbblisimfnt pibsf, tif {@link
 * #isProtRfbdy() isProtRfbdy} mftiod mby bf dbllfd to dftfrminf if tif
 * dontfxt dbn bf usfd for tif pfr-mfssbgf opfrbtions of {@link
 * #wrbp(bytf[], int, int, MfssbgfProp) wrbp} bnd {@link #gftMIC(bytf[],
 * int, int, MfssbgfProp) gftMIC}.  Tiis bllows bpplidbtions to usf
 * pfr-mfssbgf opfrbtions on dontfxts wiidi brfn't yft fully
 * fstbblisifd.<p>
 *
 * Aftfr tif dontfxt ibs bffn fstbblisifd or tif <dodf>isProtRfbdy</dodf>
 * mftiod rfturns <dodf>truf</dodf>, tif qufry routinfs dbn bf invokfd to
 * dftfrminf tif bdtubl dibrbdtfristids bnd sfrvidfs of tif fstbblisifd
 * dontfxt.  Tif bpplidbtion dbn blso stbrt using tif pfr-mfssbgf mftiods
 * of {@link #wrbp(bytf[], int, int, MfssbgfProp) wrbp} bnd
 * {@link #gftMIC(bytf[], int, int, MfssbgfProp) gftMIC} to obtbin
 * dryptogrbpiid opfrbtions on bpplidbtion supplifd dbtb.<p>
 *
 * Wifn tif dontfxt is no longfr nffdfd, tif bpplidbtion siould dbll
 * {@link #disposf() disposf} to rflfbsf bny systfm rfsourdfs tif dontfxt
 * mby bf using.<p>
 *
 * A sfdurity dontfxt typidblly mbintbins sfqufnding bnd rfplby dftfdtion
 * informbtion bbout tif tokfns it prodfssfs. Tifrfforf, tif sfqufndf in
 * wiidi bny tokfns brf prfsfntfd to tiis dontfxt for prodfssing dbn bf
 * importbnt. Also notf tibt nonf of tif mftiods in tiis intfrfbdf brf
 * syndironizfd. Tifrfforf, it is not bdvisbblf to sibrf b
 * <dodf>GSSContfxt</dodf> bmong sfvfrbl tirfbds unlfss somf bpplidbtion
 * lfvfl syndironizbtion is in plbdf.<p>
 *
 * Finblly, difffrfnt mfdibnism providfrs migit plbdf difffrfnt sfdurity
 * rfstridtions on using GSS-API dontfxts. Tifsf will bf dodumfntfd by tif
 * mfdibnism providfr. Tif bpplidbtion will nffd to fnsurf tibt it ibs tif
 * bppropribtf pfrmissions if sudi difdks brf mbdf in tif mfdibnism lbyfr.<p>
 *
 * Tif fxbmplf dodf prfsfntfd bflow dfmonstrbtfs tif usbgf of tif
 * <dodf>GSSContfxt</dodf> intfrfbdf for tif initibting pffr.  Difffrfnt
 * opfrbtions on tif <dodf>GSSContfxt</dodf> objfdt brf prfsfntfd,
 * indluding: objfdt instbntibtion, sftting of dfsirfd flbgs, dontfxt
 * fstbblisimfnt, qufry of bdtubl dontfxt flbgs, pfr-mfssbgf opfrbtions on
 * bpplidbtion dbtb, bnd finblly dontfxt dflftion.<p>
 *
 * <prf>
 *    // Crfbtf b dontfxt using dffbult drfdfntibls
 *    // bnd tif implfmfntbtion spfdifid dffbult mfdibnism
 *    GSSMbnbgfr mbnbgfr ...
 *    GSSNbmf tbrgftNbmf ...
 *    GSSContfxt dontfxt = mbnbgfr.drfbtfContfxt(tbrgftNbmf, null, null,
 *                                           GSSContfxt.INDEFINITE_LIFETIME);
 *
 *    // sft dfsirfd dontfxt options prior to dontfxt fstbblisimfnt
 *    dontfxt.rfqufstConf(truf);
 *    dontfxt.rfqufstMutublAuti(truf);
 *    dontfxt.rfqufstRfplbyDft(truf);
 *    dontfxt.rfqufstSfqufndfDft(truf);
 *
 *    // fstbblisi b dontfxt bftwffn pffrs
 *
 *    bytf []inTokfn = nfw bytf[0];
 *
 *    // Loop wiilf tifrf still is b tokfn to bf prodfssfd
 *
 *    wiilf (!dontfxt.isEstbblisifd()) {
 *
 *        bytf[] outTokfn
 *            = dontfxt.initSfdContfxt(inTokfn, 0, inTokfn.lfngti);
 *
 *        // sfnd tif output tokfn if gfnfrbtfd
 *        if (outTokfn != null)
 *            sfndTokfn(outTokfn);
 *
 *        if (!dontfxt.isEstbblisifd()) {
 *            inTokfn = rfbdTokfn();
 *    }
 *
 *     // displby dontfxt informbtion
 *     Systfm.out.println("Rfmbining lifftimf in sfdonds = "
 *                                          + dontfxt.gftLifftimf());
 *     Systfm.out.println("Contfxt mfdibnism = " + dontfxt.gftMfdi());
 *     Systfm.out.println("Initibtor = " + dontfxt.gftSrdNbmf());
 *     Systfm.out.println("Addfptor = " + dontfxt.gftTbrgNbmf());
 *
 *     if (dontfxt.gftConfStbtf())
 *             Systfm.out.println("Confidfntiblity (i.f., privbdy) is bvbilbblf");
 *
 *     if (dontfxt.gftIntfgStbtf())
 *             Systfm.out.println("Intfgrity is bvbilbblf");
 *
 *     // pfrform wrbp on bn bpplidbtion supplifd mfssbgf, bppMsg,
 *     // using QOP = 0, bnd rfqufsting privbdy sfrvidf
 *     bytf [] bppMsg ...
 *
 *     MfssbgfProp mProp = nfw MfssbgfProp(0, truf);
 *
 *     bytf []tok = dontfxt.wrbp(bppMsg, 0, bppMsg.lfngti, mProp);
 *
 *     sfndTokfn(tok);
 *
 *     // rflfbsf tif lodbl-fnd of tif dontfxt
 *     dontfxt.disposf();
 *
 * </prf>
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid intfrfbdf GSSContfxt {

    /**
     * A lifftimf donstbnt rfprfsfnting tif dffbult dontfxt lifftimf.  Tiis
     * vbluf is sft to 0.
     */
    publid stbtid finbl int DEFAULT_LIFETIME = 0;

    /**
     * A lifftimf donstbnt rfprfsfnting indffinitf dontfxt lifftimf.
     * Tiis vbluf must is sft to tif mbximum intfgfr vbluf in Jbvb -
     * {@link jbvb.lbng.Intfgfr#MAX_VALUE Intfgfr.MAX_VALUE}.
     */
    publid stbtid finbl int INDEFINITE_LIFETIME = Intfgfr.MAX_VALUE;

    /**
     * Cbllfd by tif dontfxt initibtor to stbrt tif dontfxt drfbtion
     * pibsf bnd prodfss bny tokfns gfnfrbtfd
     * by tif pffr's <dodf>bddfptSfdContfxt</dodf> mftiod.
     * Tiis mftiod mby rfturn bn output tokfn wiidi tif bpplidbtion will nffd
     * to sfnd to tif pffr for prodfssing by its <dodf>bddfptSfdContfxt</dodf>
     * mftiod. Tif bpplidbtion dbn dbll {@link #isEstbblisifd()
     * isEstbblisifd} to dftfrminf if tif dontfxt fstbblisimfnt pibsf is
     * domplftf on tiis sidf of tif dontfxt.  A rfturn vbluf of
     * <dodf>fblsf</dodf> from <dodf>isEstbblisifd</dodf> indidbtfs tibt
     * morf tokfns brf fxpfdtfd to bf supplifd to
     * <dodf>initSfdContfxt</dodf>.  Upon domplftion of tif dontfxt
     * fstbblisimfnt, tif bvbilbblf dontfxt options mby bf qufrifd tirougi
     * tif gft mftiods.<p>
     *
     * Notf tibt it is possiblf tibt tif <dodf>initSfdContfxt</dodf> mftiod
     * rfturn b tokfn for tif pffr, bnd <dodf>isEstbblisifd</dodf> rfturn
     * <dodf>truf</dodf> blso. Tiis indidbtfs tibt tif tokfn nffds to bf sfnt
     * to tif pffr, but tif lodbl fnd of tif dontfxt is now fully
     * fstbblisifd.<p>
     *
     * Somf mfdibnism providfrs migit rfquirf tibt tif dbllfr bf grbntfd
     * pfrmission to initibtf b sfdurity dontfxt. A fbilfd pfrmission difdk
     * migit dbusf b {@link jbvb.lbng.SfdurityExdfption SfdurityExdfption}
     * to bf tirown from tiis mftiod.<p>
     *
     * @rfturn b bytf[] dontbining tif tokfn to bf sfnt to tif
     * pffr. <dodf>null</dodf> indidbtfs tibt no tokfn is gfnfrbtfd.
     * @pbrbm inputBuf tokfn gfnfrbtfd by tif pffr. Tiis pbrbmftfr is ignorfd
     * on tif first dbll sindf no tokfn ibs bffn rfdfivfd from tif pffr.
     * @pbrbm offsft tif offsft witiin tif inputBuf wifrf tif tokfn bfgins.
     * @pbrbm lfn tif lfngti of tif tokfn.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#NO_CRED GSSExdfption.NO_CRED},
     *   {@link GSSExdfption#CREDENTIALS_EXPIRED
     *                                  GSSExdfption.CREDENTIALS_EXPIRED},
     *   {@link GSSExdfption#BAD_BINDINGS GSSExdfption.BAD_BINDINGS},
     *   {@link GSSExdfption#OLD_TOKEN GSSExdfption.OLD_TOKEN},
     *   {@link GSSExdfption#DUPLICATE_TOKEN GSSExdfption.DUPLICATE_TOKEN},
     *   {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *   {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf[] initSfdContfxt(bytf inputBuf[], int offsft, int lfn)
        tirows GSSExdfption;

    /**
     * Cbllfd by tif dontfxt initibtor to stbrt tif dontfxt drfbtion
     * pibsf bnd prodfss bny tokfns gfnfrbtfd
     * by tif pffr's <dodf>bddfptSfdContfxt</dodf> mftiod using
     * strfbms. Tiis mftiod mby writf bn output tokfn to tif
     * <dodf>OutpuStrfbm</dodf>, wiidi tif bpplidbtion will
     * nffd to sfnd to tif pffr for prodfssing by its
     * <dodf>bddfptSfdContfxt</dodf> dbll. Typidblly, tif bpplidbtion would
     * fnsurf tiis by dblling tif  {@link jbvb.io.OutputStrfbm#flusi() flusi}
     * mftiod on bn <dodf>OutputStrfbm</dodf> tibt fndbpsulbtfs tif
     * donnfdtion bftwffn tif two pffrs. Tif bpplidbtion dbn
     * dftfrminf if b tokfn is writtfn to tif OutputStrfbm from tif rfturn
     * vbluf of tiis mftiod. A rfturn vbluf of <dodf>0</dodf> indidbtfs tibt
     * no tokfn wbs writtfn. Tif bpplidbtion dbn dbll
     * {@link #isEstbblisifd() isEstbblisifd} to dftfrminf if tif dontfxt
     * fstbblisimfnt pibsf is domplftf on tiis sidf of tif dontfxt. A
     * rfturn  vbluf of <dodf>fblsf</dodf> from <dodf>isEstbblisifd</dodf>
     * indidbtfs tibt morf tokfns brf fxpfdtfd to bf supplifd to
     * <dodf>initSfdContfxt</dodf>.
     * Upon domplftion of tif dontfxt fstbblisimfnt, tif bvbilbblf dontfxt
     * options mby bf qufrifd tirougi tif gft mftiods.<p>
     *
     * Notf tibt it is possiblf tibt tif <dodf>initSfdContfxt</dodf> mftiod
     * rfturn b tokfn for tif pffr, bnd <dodf>isEstbblisifd</dodf> rfturn
     * <dodf>truf</dodf> blso. Tiis indidbtfs tibt tif tokfn nffds to bf sfnt
     * to tif pffr, but tif lodbl fnd of tif dontfxt is now fully
     * fstbblisifd.<p>
     *
     * Tif GSS-API butifntidbtion tokfns dontbin b dffinitivf stbrt bnd
     * fnd. Tiis mftiod will bttfmpt to rfbd onf of tifsf tokfns pfr
     * invodbtion, bnd mby blodk on tif strfbm if only pbrt of tif tokfn is
     * bvbilbblf.  In bll otifr rfspfdts tiis mftiod is fquivblfnt to tif
     * bytf brrby bbsfd {@link #initSfdContfxt(bytf[], int, int)
     * initSfdContfxt}.<p>
     *
     * Somf mfdibnism providfrs migit rfquirf tibt tif dbllfr bf grbntfd
     * pfrmission to initibtf b sfdurity dontfxt. A fbilfd pfrmission difdk
     * migit dbusf b {@link jbvb.lbng.SfdurityExdfption SfdurityExdfption}
     * to bf tirown from tiis mftiod.<p>
     *
     * Tif following fxbmplf dodf dfmonstrbtfs iow tiis mftiod migit bf
     * usfd:<p>
     * <prf>
     *     InputStrfbm is ...
     *     OutputStrfbm os ...
     *     GSSContfxt dontfxt ...
     *
     *     // Loop wiilf tifrf is still b tokfn to bf prodfssfd
     *
     *     wiilf (!dontfxt.isEstbblisifd()) {
     *
     *         dontfxt.initSfdContfxt(is, os);
     *
     *         // sfnd output tokfn if gfnfrbtfd
     *         os.flusi();
     *     }
     * </prf>
     *
     *
     * @rfturn tif numbfr of bytfs writtfn to tif OutputStrfbm bs pbrt of tif
     * tokfn to bf sfnt to tif pffr. A vbluf of 0 indidbtfs tibt no tokfn
     * nffds to bf sfnt.
     * @pbrbm inStrfbm bn InputStrfbm tibt dontbins tif tokfn gfnfrbtfd by
     * tif pffr. Tiis pbrbmftfr is ignorfd on tif first dbll sindf no tokfn
     * ibs bffn or will bf rfdfivfd from tif pffr bt tibt point.
     * @pbrbm outStrfbm bn OutputStrfbm wifrf tif output tokfn will bf
     * writtfn. During tif finbl stbgf of dontfxt fstbblisimfnt, tifrf mby bf
     * no bytfs writtfn.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#NO_CRED GSSExdfption.NO_CRED},
     *   {@link GSSExdfption#CREDENTIALS_EXPIRED GSSExdfption.CREDENTIALS_EXPIRED},
     *   {@link GSSExdfption#BAD_BINDINGS GSSExdfption.BAD_BINDINGS},
     *   {@link GSSExdfption#OLD_TOKEN GSSExdfption.OLD_TOKEN},
     *   {@link GSSExdfption#DUPLICATE_TOKEN GSSExdfption.DUPLICATE_TOKEN},
     *   {@link GSSExdfption#BAD_NAMETYPE GSSExdfption.BAD_NAMETYPE},
     *   {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int initSfdContfxt(InputStrfbm inStrfbm,
                              OutputStrfbm outStrfbm) tirows GSSExdfption;

    /**
     * Cbllfd by tif dontfxt bddfptor upon rfdfiving b tokfn from tif
     * pffr. Tiis mftiod mby rfturn bn output tokfn wiidi tif bpplidbtion
     * will nffd to sfnd to tif pffr for furtifr prodfssing by its
     * <dodf>initSfdContfxt</dodf> dbll.<p>
     *
     * Tif bpplidbtion dbn dbll {@link #isEstbblisifd() isEstbblisifd} to
     * dftfrminf if tif dontfxt fstbblisimfnt pibsf is domplftf for tiis
     * pffr.  A rfturn vbluf of <dodf>fblsf</dodf> from
     * <dodf>isEstbblisifd</dodf> indidbtfs tibt morf tokfns brf fxpfdtfd to
     * bf supplifd to tiis mftiod.    Upon domplftion of tif dontfxt
     * fstbblisimfnt, tif bvbilbblf dontfxt options mby bf qufrifd tirougi
     * tif gft mftiods.<p>
     *
     * Notf tibt it is possiblf tibt <dodf>bddfptSfdContfxt</dodf> rfturn b
     * tokfn for tif pffr, bnd <dodf>isEstbblisifd</dodf> rfturn
     * <dodf>truf</dodf> blso.  Tiis indidbtfs tibt tif tokfn nffds to bf
     * sfnt to tif pffr, but tif lodbl fnd of tif dontfxt is now fully
     * fstbblisifd.<p>
     *
     * Somf mfdibnism providfrs migit rfquirf tibt tif dbllfr bf grbntfd
     * pfrmission to bddfpt b sfdurity dontfxt. A fbilfd pfrmission difdk
     * migit dbusf b {@link jbvb.lbng.SfdurityExdfption SfdurityExdfption}
     * to bf tirown from tiis mftiod.<p>
     *
     * Tif following fxbmplf dodf dfmonstrbtfs iow tiis mftiod migit bf
     * usfd:<p>
     * <prf>
     *     bytf[] inTokfn;
     *     bytf[] outTokfn;
     *     GSSContfxt dontfxt ...
     *
     *     // Loop wiilf tifrf is still b tokfn to bf prodfssfd
     *
     *     wiilf (!dontfxt.isEstbblisifd()) {
     *         inTokfn = rfbdTokfn();
     *         outTokfn = dontfxt.bddfptSfdContfxt(inTokfn, 0,
     *                                             inTokfn.lfngti);
     *         // sfnd output tokfn if gfnfrbtfd
     *         if (outTokfn != null)
     *             sfndTokfn(outTokfn);
     *     }
     * </prf>
     *
     *
     * @rfturn b bytf[] dontbining tif tokfn to bf sfnt to tif
     * pffr. <dodf>null</dodf> indidbtfs tibt no tokfn is gfnfrbtfd.
     * @pbrbm inTokfn tokfn gfnfrbtfd by tif pffr.
     * @pbrbm offsft tif offsft witiin tif inTokfn wifrf tif tokfn bfgins.
     * @pbrbm lfn tif lfngti of tif tokfn.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#NO_CRED GSSExdfption.NO_CRED},
     *   {@link GSSExdfption#CREDENTIALS_EXPIRED
     *                               GSSExdfption.CREDENTIALS_EXPIRED},
     *   {@link GSSExdfption#BAD_BINDINGS GSSExdfption.BAD_BINDINGS},
     *   {@link GSSExdfption#OLD_TOKEN GSSExdfption.OLD_TOKEN},
     *   {@link GSSExdfption#DUPLICATE_TOKEN GSSExdfption.DUPLICATE_TOKEN},
     *   {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf[] bddfptSfdContfxt(bytf inTokfn[], int offsft, int lfn)
        tirows GSSExdfption;

    /**
     * Cbllfd by tif dontfxt bddfptor to prodfss b tokfn from tif pffr using
     * strfbms.   It mby writf bn output tokfn to tif
     * <dodf>OutputStrfbm</dodf>, wiidi tif bpplidbtion
     * will nffd to sfnd to tif pffr for prodfssing by its
     * <dodf>initSfdContfxt</dodf> mftiod.  Typidblly, tif bpplidbtion would
     * fnsurf tiis by dblling tif  {@link jbvb.io.OutputStrfbm#flusi() flusi}
     * mftiod on bn <dodf>OutputStrfbm</dodf> tibt fndbpsulbtfs tif
     * donnfdtion bftwffn tif two pffrs. Tif bpplidbtion dbn dbll
     * {@link #isEstbblisifd() isEstbblisifd} to dftfrminf if tif dontfxt
     * fstbblisimfnt pibsf is domplftf on tiis sidf of tif dontfxt. A
     * rfturn  vbluf of <dodf>fblsf</dodf> from <dodf>isEstbblisifd</dodf>
     * indidbtfs tibt morf tokfns brf fxpfdtfd to bf supplifd to
     * <dodf>bddfptSfdContfxt</dodf>.
     * Upon domplftion of tif dontfxt fstbblisimfnt, tif bvbilbblf dontfxt
     * options mby bf qufrifd tirougi tif gft mftiods.<p>
     *
     * Notf tibt it is possiblf tibt <dodf>bddfptSfdContfxt</dodf> rfturn b
     * tokfn for tif pffr, bnd <dodf>isEstbblisifd</dodf> rfturn
     * <dodf>truf</dodf> blso.  Tiis indidbtfs tibt tif tokfn nffds to bf
     * sfnt to tif pffr, but tif lodbl fnd of tif dontfxt is now fully
     * fstbblisifd.<p>
     *
     * Tif GSS-API butifntidbtion tokfns dontbin b dffinitivf stbrt bnd
     * fnd. Tiis mftiod will bttfmpt to rfbd onf of tifsf tokfns pfr
     * invodbtion, bnd mby blodk on tif strfbm if only pbrt of tif tokfn is
     * bvbilbblf. In bll otifr rfspfdts tiis mftiod is fquivblfnt to tif bytf
     * brrby bbsfd {@link #bddfptSfdContfxt(bytf[], int, int)
     * bddfptSfdContfxt}.<p>
     *
     * Somf mfdibnism providfrs migit rfquirf tibt tif dbllfr bf grbntfd
     * pfrmission to bddfpt b sfdurity dontfxt. A fbilfd pfrmission difdk
     * migit dbusf b {@link jbvb.lbng.SfdurityExdfption SfdurityExdfption}
     * to bf tirown from tiis mftiod.<p>
     *
     * Tif following fxbmplf dodf dfmonstrbtfs iow tiis mftiod migit bf
     * usfd:<p>
     * <prf>
     *     InputStrfbm is ...
     *     OutputStrfbm os ...
     *     GSSContfxt dontfxt ...
     *
     *     // Loop wiilf tifrf is still b tokfn to bf prodfssfd
     *
     *     wiilf (!dontfxt.isEstbblisifd()) {
     *
     *         dontfxt.bddfptSfdContfxt(is, os);
     *
     *         // sfnd output tokfn if gfnfrbtfd
     *         os.flusi();
     *     }
     * </prf>
     *
     *
     * @pbrbm inStrfbm bn InputStrfbm tibt dontbins tif tokfn gfnfrbtfd by
     * tif pffr.
     * @pbrbm outStrfbm bn OutputStrfbm wifrf tif output tokfn will bf
     * writtfn. During tif finbl stbgf of dontfxt fstbblisimfnt, tifrf mby bf
     * no bytfs writtfn.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#NO_CRED GSSExdfption.NO_CRED},
     *   {@link GSSExdfption#CREDENTIALS_EXPIRED
     *                           GSSExdfption.CREDENTIALS_EXPIRED},
     *   {@link GSSExdfption#BAD_BINDINGS GSSExdfption.BAD_BINDINGS},
     *   {@link GSSExdfption#OLD_TOKEN GSSExdfption.OLD_TOKEN},
     *   {@link GSSExdfption#DUPLICATE_TOKEN GSSExdfption.DUPLICATE_TOKEN},
     *   {@link GSSExdfption#BAD_MECH GSSExdfption.BAD_MECH},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    /* Missing rfturn vbluf in RFC. int siould ibvf bffn rfturnfd.
     * -----------------------------------------------------------
     *
     * Tif bpplidbtion dbn dftfrminf if b tokfn is writtfn to tif
     * OutputStrfbm from tif rfturn vbluf of tiis mftiod. A rfturn vbluf of
     * <dodf>0</dodf> indidbtfs tibt no tokfn wbs writtfn.
     *
     * @rfturn <strong>tif numbfr of bytfs writtfn to tif
     * OutputStrfbm bs pbrt of tif tokfn to bf sfnt to tif pffr. A vbluf of
     * 0 indidbtfs tibt no tokfn  nffds to bf
     * sfnt.</strong>
     */
    publid void bddfptSfdContfxt(InputStrfbm inStrfbm,
                                 OutputStrfbm outStrfbm) tirows GSSExdfption;

    /**
     * Usfd during dontfxt fstbblisimfnt to dftfrminf tif stbtf of tif
     * dontfxt.
     *
     * @rfturn <dodf>truf</dodf> if tiis is b fully fstbblisifd dontfxt on
     * tif dbllfr's sidf bnd no morf tokfns brf nffdfd from tif pffr.
     */
    publid boolfbn isEstbblisifd();

    /**
     * Rflfbsfs bny systfm rfsourdfs bnd dryptogrbpiid informbtion storfd in
     * tif dontfxt objfdt bnd invblidbtfs tif dontfxt.
     *
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void disposf() tirows GSSExdfption;

    /**
     * Usfd to dftfrminf limits on tif sizf of tif mfssbgf
     * tibt dbn bf pbssfd to <dodf>wrbp</dodf>. Rfturns tif mbximum
     * mfssbgf sizf tibt, if prfsfntfd to tif <dodf>wrbp</dodf> mftiod witi
     * tif sbmf <dodf>donfRfq</dodf> bnd <dodf>qop</dodf> pbrbmftfrs, will
     * rfsult in bn output tokfn dontbining no morf
     * tibn <dodf>mbxTokfnSizf</dodf> bytfs.<p>
     *
     * Tiis dbll is intfndfd for usf by bpplidbtions tibt dommunidbtf ovfr
     * protodols tibt imposf b mbximum mfssbgf sizf.  It fnbblfs tif
     * bpplidbtion to frbgmfnt mfssbgfs prior to bpplying protfdtion.<p>
     *
     * GSS-API implfmfntbtions brf rfdommfndfd but not rfquirfd to dftfdt
     * invblid QOP vblufs wifn <dodf>gftWrbpSizfLimit</dodf> is dbllfd.
     * Tiis routinf gubrbntffs only b mbximum mfssbgf sizf, not tif
     * bvbilbbility of spfdifid QOP vblufs for mfssbgf protfdtion.<p>
     *
     * @pbrbm qop tif lfvfl of protfdtion wrbp will bf bskfd to providf.
     * @pbrbm donfRfq <dodf>truf</dodf> if wrbp will bf bskfd to providf
     * privbdy, <dodf>fblsf</dodf>  otifrwisf.
     * @pbrbm mbxTokfnSizf tif dfsirfd mbximum sizf of tif tokfn fmittfd by
     * wrbp.
     * @rfturn tif mbximum sizf of tif input tokfn for tif givfn output
     * tokfn sizf
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#BAD_QOP GSSExdfption.BAD_QOP},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid int gftWrbpSizfLimit(int qop, boolfbn donfRfq,
                                int mbxTokfnSizf) tirows GSSExdfption;

    /**
     * Applifs pfr-mfssbgf sfdurity sfrvidfs ovfr tif fstbblisifd sfdurity
     * dontfxt. Tif mftiod will rfturn b tokfn witi tif
     * bpplidbtion supplifd dbtb bnd b dryptogrbpiid MIC ovfr it.
     * Tif dbtb mby bf fndryptfd if donfidfntiblity (privbdy) wbs
     * rfqufstfd.<p>
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd usfd
     * to spfdify b QOP vbluf wiidi sflfdts dryptogrbpiid blgoritims, bnd b
     * privbdy sfrvidf to optionblly fndrypt tif mfssbgf.  Tif undfrlying
     * mfdibnism tibt is usfd in tif dbll mby not bf bblf to providf tif
     * privbdy sfrvidf.  It sfts tif bdtubl privbdy sfrvidf tibt it dofs
     * providf in tiis MfssbgfProp objfdt wiidi tif dbllfr siould tifn
     * qufry upon rfturn.  If tif mfdibnism is not bblf to providf tif
     * rfqufstfd QOP, it tirows b GSSExdfption witi tif BAD_QOP dodf.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns
     * fmittfd by wrbp to providf "sfdurf frbming", implfmfntbtions siould
     * support tif wrbpping of zfro-lfngti mfssbgfs.<p>
     *
     * Tif bpplidbtion will bf rfsponsiblf for sfnding tif tokfn to tif
     * pffr.
     *
     * @pbrbm inBuf bpplidbtion dbtb to bf protfdtfd.
     * @pbrbm offsft tif offsft witiin tif inBuf wifrf tif dbtb bfgins.
     * @pbrbm lfn tif lfngti of tif dbtb
     * @pbrbm msgProp instbndf of MfssbgfProp tibt is usfd by tif
     * bpplidbtion to sft tif dfsirfd QOP bnd privbdy stbtf. Sft tif
     * dfsirfd QOP to 0 to rfqufst tif dffbult QOP. Upon rfturn from tiis
     * mftiod, tiis objfdt will dontbin tif tif bdtubl privbdy stbtf tibt
     * wbs bpplifd to tif mfssbgf by tif undfrlying mfdibnism.
     * @rfturn b bytf[] dontbining tif tokfn to bf sfnt to tif pffr.
     *
     * @tirows GSSExdfption dontbining tif following mbjor frror dodfs:
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#BAD_QOP GSSExdfption.BAD_QOP},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf[] wrbp(bytf inBuf[], int offsft, int lfn,
                       MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Applifs pfr-mfssbgf sfdurity sfrvidfs ovfr tif fstbblisifd sfdurity
     * dontfxt using strfbms. Tif mftiod will rfturn b
     * tokfn witi tif bpplidbtion supplifd dbtb bnd b dryptogrbpiid MIC ovfr it.
     * Tif dbtb mby bf fndryptfd if donfidfntiblity
     * (privbdy) wbs rfqufstfd. Tiis mftiod is fquivblfnt to tif bytf brrby
     * bbsfd {@link #wrbp(bytf[], int, int, MfssbgfProp) wrbp} mftiod.<p>
     *
     * Tif bpplidbtion will bf rfsponsiblf for sfnding tif tokfn to tif
     * pffr.  Typidblly, tif bpplidbtion would
     * fnsurf tiis by dblling tif  {@link jbvb.io.OutputStrfbm#flusi() flusi}
     * mftiod on bn <dodf>OutputStrfbm</dodf> tibt fndbpsulbtfs tif
     * donnfdtion bftwffn tif two pffrs.<p>
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd usfd
     * to spfdify b QOP vbluf wiidi sflfdts dryptogrbpiid blgoritims, bnd b
     * privbdy sfrvidf to optionblly fndrypt tif mfssbgf.  Tif undfrlying
     * mfdibnism tibt is usfd in tif dbll mby not bf bblf to providf tif
     * privbdy sfrvidf.  It sfts tif bdtubl privbdy sfrvidf tibt it dofs
     * providf in tiis MfssbgfProp objfdt wiidi tif dbllfr siould tifn
     * qufry upon rfturn.  If tif mfdibnism is not bblf to providf tif
     * rfqufstfd QOP, it tirows b GSSExdfption witi tif BAD_QOP dodf.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns
     * fmittfd by wrbp to providf "sfdurf frbming", implfmfntbtions siould
     * support tif wrbpping of zfro-lfngti mfssbgfs.<p>
     *
     * @pbrbm inStrfbm bn InputStrfbm dontbining tif bpplidbtion dbtb to bf
     * protfdtfd. All of tif dbtb tibt is bvbilbblf in
     * inStrfbm is usfd.
     * @pbrbm outStrfbm bn OutputStrfbm to writf tif protfdtfd mfssbgf
     * to.
     * @pbrbm msgProp instbndf of MfssbgfProp tibt is usfd by tif
     * bpplidbtion to sft tif dfsirfd QOP bnd privbdy stbtf. Sft tif
     * dfsirfd QOP to 0 to rfqufst tif dffbult QOP. Upon rfturn from tiis
     * mftiod, tiis objfdt will dontbin tif tif bdtubl privbdy stbtf tibt
     * wbs bpplifd to tif mfssbgf by tif undfrlying mfdibnism.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#BAD_QOP GSSExdfption.BAD_QOP},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void wrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                     MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Usfd to prodfss tokfns gfnfrbtfd by tif <dodf>wrbp</dodf> mftiod on
     * tif otifr sidf of tif dontfxt. Tif mftiod will rfturn tif mfssbgf
     * supplifd by tif pffr bpplidbtion to its wrbp dbll, wiilf bt tif sbmf
     * timf vfrifying tif fmbfddfd MIC for tibt mfssbgf.<p>
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd is
     * usfd by tif undfrlying mfdibnism to rfturn informbtion to tif dbllfr
     * sudi bs tif QOP, wiftifr donfidfntiblity wbs bpplifd to tif mfssbgf,
     * bnd otifr supplfmfntbry mfssbgf stbtf informbtion.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns
     * fmittfd by wrbp to providf "sfdurf frbming", implfmfntbtions siould
     * support tif wrbpping bnd unwrbpping of zfro-lfngti mfssbgfs.<p>
     *
     * @pbrbm inBuf b bytf brrby dontbining tif wrbp tokfn rfdfivfd from
     * pffr.
     * @pbrbm offsft tif offsft wifrf tif tokfn bfgins.
     * @pbrbm lfn tif lfngti of tif tokfn
     * @pbrbm msgProp upon rfturn from tif mftiod, tiis objfdt will dontbin
     * tif bpplifd QOP, tif privbdy stbtf of tif mfssbgf, bnd supplfmfntbry
     * informbtion stbting if tif tokfn wbs b duplidbtf, old, out of
     * sfqufndf or brriving bftfr b gbp.
     * @rfturn b bytf[] dontbining tif mfssbgf unwrbppfd from tif input
     * tokfn.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf [] unwrbp(bytf[] inBuf, int offsft, int lfn,
                          MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Usfs strfbms to prodfss tokfns gfnfrbtfd by tif <dodf>wrbp</dodf>
     * mftiod on tif otifr sidf of tif dontfxt. Tif mftiod will rfturn tif
     * mfssbgf supplifd by tif pffr bpplidbtion to its wrbp dbll, wiilf bt
     * tif sbmf timf vfrifying tif fmbfddfd MIC for tibt mfssbgf.<p>
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd is
     * usfd by tif undfrlying mfdibnism to rfturn informbtion to tif dbllfr
     * sudi bs tif QOP, wiftifr donfidfntiblity wbs bpplifd to tif mfssbgf,
     * bnd otifr supplfmfntbry mfssbgf stbtf informbtion.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns
     * fmittfd by wrbp to providf "sfdurf frbming", implfmfntbtions siould
     * support tif wrbpping bnd unwrbpping of zfro-lfngti mfssbgfs.<p>
     *
     * Tif formbt of tif input tokfn tibt tiis mftiod
     * rfbds is dffinfd in tif spfdifidbtion for tif undfrlying mfdibnism tibt
     * will bf usfd. Tiis mftiod will bttfmpt to rfbd onf of tifsf tokfns pfr
     * invodbtion. If tif mfdibnism tokfn dontbins b dffinitivf stbrt bnd
     * fnd tiis mftiod mby blodk on tif <dodf>InputStrfbm</dodf> if only
     * pbrt of tif tokfn is bvbilbblf. If tif stbrt bnd fnd of tif tokfn
     * brf not dffinitivf tifn tif mftiod will bttfmpt to trfbt bll
     * bvbilbblf bytfs bs pbrt of tif tokfn.<p>
     *
     * Otifr tibn tif possiblf blodking bfibvior dfsdribfd bbovf, tiis
     * mftiod is fquivblfnt to tif bytf brrby bbsfd {@link #unwrbp(bytf[],
     * int, int, MfssbgfProp) unwrbp} mftiod.<p>
     *
     * @pbrbm inStrfbm bn InputStrfbm tibt dontbins tif wrbp tokfn gfnfrbtfd
     * by tif pffr.
     * @pbrbm outStrfbm bn OutputStrfbm to writf tif bpplidbtion mfssbgf
     * to.
     * @pbrbm msgProp upon rfturn from tif mftiod, tiis objfdt will dontbin
     * tif bpplifd QOP, tif privbdy stbtf of tif mfssbgf, bnd supplfmfntbry
     * informbtion stbting if tif tokfn wbs b duplidbtf, old, out of
     * sfqufndf or brriving bftfr b gbp.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN},
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC},
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void unwrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Rfturns b tokfn dontbining b dryptogrbpiid Mfssbgf Intfgrity Codf
     * (MIC) for tif supplifd mfssbgf,  for trbnsffr to tif pffr
     * bpplidbtion.  Unlikf wrbp, wiidi fndbpsulbtfs tif usfr mfssbgf in tif
     * rfturnfd tokfn, only tif mfssbgf MIC is rfturnfd in tif output
     * tokfn.<p>
     *
     * Notf tibt privbdy dbn only bf bpplifd tirougi tif wrbp dbll.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns fmittfd
     * by gftMIC to providf "sfdurf frbming", implfmfntbtions siould support
     * dfrivbtion of MICs from zfro-lfngti mfssbgfs.
     *
     * @pbrbm inMsg tif mfssbgf to gfnfrbtf tif MIC ovfr.
     * @pbrbm offsft offsft witiin tif inMsg wifrf tif mfssbgf bfgins.
     * @pbrbm lfn tif lfngti of tif mfssbgf
     * @pbrbm msgProp bn instbndf of <dodf>MfssbgfProp</dodf> tibt is usfd
     * by tif bpplidbtion to sft tif dfsirfd QOP.  Sft tif dfsirfd QOP to
     * <dodf>0</dodf> in <dodf>msgProp</dodf> to rfqufst tif dffbult
     * QOP. Altfrnbtivfly pbss in <dodf>null</dodf> for <dodf>msgProp</dodf>
     * to rfqufst tif dffbult QOP.
     * @rfturn b bytf[] dontbining tif tokfn to bf sfnt to tif pffr.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#BAD_QOP GSSExdfption.BAD_QOP},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf[] gftMIC(bytf []inMsg, int offsft, int lfn,
                         MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Usfs strfbms to produdf b tokfn dontbining b dryptogrbpiid MIC for
     * tif supplifd mfssbgf, for trbnsffr to tif pffr bpplidbtion.
     * Unlikf wrbp, wiidi fndbpsulbtfs tif usfr mfssbgf in tif rfturnfd
     * tokfn, only tif mfssbgf MIC is produdfd in tif output tokfn. Tiis
     * mftiod is fquivblfnt to tif bytf brrby bbsfd {@link #gftMIC(bytf[],
     * int, int, MfssbgfProp) gftMIC} mftiod.
     *
     * Notf tibt privbdy dbn only bf bpplifd tirougi tif wrbp dbll.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns fmittfd
     * by gftMIC to providf "sfdurf frbming", implfmfntbtions siould support
     * dfrivbtion of MICs from zfro-lfngti mfssbgfs.
     *
     * @pbrbm inStrfbm bn InputStrfbm dontbining tif mfssbgf to gfnfrbtf tif
     * MIC ovfr. All of tif dbtb tibt is bvbilbblf in
     * inStrfbm is usfd.
     * @pbrbm outStrfbm bn OutputStrfbm to writf tif output tokfn to.
     * @pbrbm msgProp bn instbndf of <dodf>MfssbgfProp</dodf> tibt is usfd
     * by tif bpplidbtion to sft tif dfsirfd QOP.  Sft tif dfsirfd QOP to
     * <dodf>0</dodf> in <dodf>msgProp</dodf> to rfqufst tif dffbult
     * QOP. Altfrnbtivfly pbss in <dodf>null</dodf> for <dodf>msgProp</dodf>
     * to rfqufst tif dffbult QOP.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#BAD_QOP GSSExdfption.BAD_QOP},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void gftMIC(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Vfrififs tif dryptogrbpiid MIC, dontbinfd in tif tokfn pbrbmftfr,
     * ovfr tif supplifd mfssbgf.<p>
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd is usfd
     * by tif undfrlying mfdibnism to rfturn informbtion to tif dbllfr sudi
     * bs tif QOP indidbting tif strfngti of protfdtion tibt wbs bpplifd to
     * tif mfssbgf bnd otifr supplfmfntbry mfssbgf stbtf informbtion.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns fmittfd
     * by gftMIC to providf "sfdurf frbming", implfmfntbtions siould support
     * tif dbldulbtion bnd vfrifidbtion of MICs ovfr zfro-lfngti mfssbgfs.
     *
     * @pbrbm inTokfn tif tokfn gfnfrbtfd by pffr's gftMIC mftiod.
     * @pbrbm tokOffsft tif offsft witiin tif inTokfn wifrf tif tokfn
     * bfgins.
     * @pbrbm tokLfn tif lfngti of tif tokfn.
     * @pbrbm inMsg tif bpplidbtion mfssbgf to vfrify tif dryptogrbpiid MIC
     * ovfr.
     * @pbrbm msgOffsft tif offsft in inMsg wifrf tif mfssbgf bfgins.
     * @pbrbm msgLfn tif lfngti of tif mfssbgf.
     * @pbrbm msgProp upon rfturn from tif mftiod, tiis objfdt will dontbin
     * tif bpplifd QOP bnd supplfmfntbry informbtion stbting if tif tokfn
     * wbs b duplidbtf, old, out of sfqufndf or brriving bftfr b gbp.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN}
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC}
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED}
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void vfrifyMIC(bytf[] inTokfn, int tokOffsft, int tokLfn,
                          bytf[] inMsg, int msgOffsft, int msgLfn,
                          MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Usfs strfbms to vfrify tif dryptogrbpiid MIC, dontbinfd in tif tokfn
     * pbrbmftfr, ovfr tif supplifd mfssbgf.  Tiis mftiod is fquivblfnt to
     * tif bytf brrby bbsfd {@link #vfrifyMIC(bytf[], int, int, bytf[], int,
     * int, MfssbgfProp) vfrifyMIC} mftiod.
     *
     * Tif MfssbgfProp objfdt is instbntibtfd by tif bpplidbtion bnd is usfd
     * by tif undfrlying mfdibnism to rfturn informbtion to tif dbllfr sudi
     * bs tif QOP indidbting tif strfngti of protfdtion tibt wbs bpplifd to
     * tif mfssbgf bnd otifr supplfmfntbry mfssbgf stbtf informbtion.<p>
     *
     * Sindf somf bpplidbtion-lfvfl protodols mby wisi to usf tokfns fmittfd
     * by gftMIC to providf "sfdurf frbming", implfmfntbtions siould support
     * tif dbldulbtion bnd vfrifidbtion of MICs ovfr zfro-lfngti mfssbgfs.<p>
     *
     * Tif formbt of tif input tokfn tibt tiis mftiod
     * rfbds is dffinfd in tif spfdifidbtion for tif undfrlying mfdibnism tibt
     * will bf usfd. Tiis mftiod will bttfmpt to rfbd onf of tifsf tokfns pfr
     * invodbtion. If tif mfdibnism tokfn dontbins b dffinitivf stbrt bnd
     * fnd tiis mftiod mby blodk on tif <dodf>InputStrfbm</dodf> if only
     * pbrt of tif tokfn is bvbilbblf. If tif stbrt bnd fnd of tif tokfn
     * brf not dffinitivf tifn tif mftiod will bttfmpt to trfbt bll
     * bvbilbblf bytfs bs pbrt of tif tokfn.<p>
     *
     * Otifr tibn tif possiblf blodking bfibvior dfsdribfd bbovf, tiis
     * mftiod is fquivblfnt to tif bytf brrby bbsfd {@link #vfrifyMIC(bytf[],
     * int, int, bytf[], int, int, MfssbgfProp) vfrifyMIC} mftiod.<p>
     *
     * @pbrbm tokStrfbm bn InputStrfbm dontbining tif tokfn gfnfrbtfd by tif
     * pffr's gftMIC mftiod.
     * @pbrbm msgStrfbm bn InputStrfbm dontbining tif bpplidbtion mfssbgf to
     * vfrify tif dryptogrbpiid MIC ovfr. All of tif dbtb
     * tibt is bvbilbblf in msgStrfbm is usfd.
     * @pbrbm msgProp upon rfturn from tif mftiod, tiis objfdt will dontbin
     * tif bpplifd QOP bnd supplfmfntbry informbtion stbting if tif tokfn
     * wbs b duplidbtf, old, out of sfqufndf or brriving bftfr b gbp.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#DEFECTIVE_TOKEN GSSExdfption.DEFECTIVE_TOKEN}
     *   {@link GSSExdfption#BAD_MIC GSSExdfption.BAD_MIC}
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED}
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void vfrifyMIC(InputStrfbm tokStrfbm, InputStrfbm msgStrfbm,
                          MfssbgfProp msgProp) tirows GSSExdfption;

    /**
     * Exports tiis dontfxt so tibt bnotifr prodfss mby
     * import it.. Providfd to support tif sibring of work bftwffn
     * multiplf prodfssfs. Tiis routinf will typidblly bf usfd by tif
     * dontfxt-bddfptor, in bn bpplidbtion wifrf b singlf prodfss rfdfivfs
     * indoming donnfdtion rfqufsts bnd bddfpts sfdurity dontfxts ovfr
     * tifm, tifn pbssfs tif fstbblisifd dontfxt to onf or morf otifr
     * prodfssfs for mfssbgf fxdibngf.<p>
     *
     * Tiis mftiod dfbdtivbtfs tif sfdurity dontfxt bnd drfbtfs bn
     * intfrprodfss tokfn wiidi, wifn pbssfd to {@link
     * GSSMbnbgfr#drfbtfContfxt(bytf[]) GSSMbnbgfr.drfbtfContfxt} in
     * bnotifr prodfss, will rf-bdtivbtf tif dontfxt in tif sfdond prodfss.
     * Only b singlf instbntibtion of b givfn dontfxt mby bf bdtivf bt bny
     * onf timf; b subsfqufnt bttfmpt by b dontfxt fxportfr to bddfss tif
     * fxportfd sfdurity dontfxt will fbil.<p>
     *
     * Tif implfmfntbtion mby donstrbin tif sft of prodfssfs by wiidi tif
     * intfrprodfss tokfn mby bf importfd, fitifr bs b fundtion of lodbl
     * sfdurity polidy, or bs b rfsult of implfmfntbtion dfdisions.  For
     * fxbmplf, somf implfmfntbtions mby donstrbin dontfxts to bf pbssfd
     * only bftwffn prodfssfs tibt run undfr tif sbmf bddount, or wiidi brf
     * pbrt of tif sbmf prodfss group.<p>
     *
     * Tif intfrprodfss tokfn mby dontbin sfdurity-sfnsitivf informbtion
     * (for fxbmplf dryptogrbpiid kfys).  Wiilf mfdibnisms brf fndourbgfd
     * to fitifr bvoid plbding sudi sfnsitivf informbtion witiin
     * intfrprodfss tokfns, or to fndrypt tif tokfn bfforf rfturning it to
     * tif bpplidbtion, in b typidbl GSS-API implfmfntbtion tiis mby not bf
     * possiblf.  Tius tif bpplidbtion must tbkf dbrf to protfdt tif
     * intfrprodfss tokfn, bnd fnsurf tibt bny prodfss to wiidi tif tokfn
     * is trbnsffrrfd is trustwortiy. <p>
     *
     * Implfmfntbtions brf not rfquirfd to support tif intfr-prodfss
     * trbnsffr of sfdurity dontfxts.  Cblling tif {@link #isTrbnsffrbblf()
     * isTrbnsffrbblf} mftiod will indidbtf if tif dontfxt objfdt is
     * trbnsffrbblf.<p>
     *
     * Cblling tiis mftiod on b dontfxt tibt
     * is not fxportbblf will rfsult in tiis fxdfption bfing tirown witi
     * tif frror dodf {@link GSSExdfption#UNAVAILABLE
     * GSSExdfption.UNAVAILABLE}.
     *
     * @rfturn b bytf[] dontbining tif fxportfd dontfxt
     * @sff GSSMbnbgfr#drfbtfContfxt(bytf[])
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#UNAVAILABLE GSSExdfption.UNAVAILABLE},
     *   {@link GSSExdfption#CONTEXT_EXPIRED GSSExdfption.CONTEXT_EXPIRED},
     *   {@link GSSExdfption#NO_CONTEXT GSSExdfption.NO_CONTEXT},
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid bytf [] fxport() tirows GSSExdfption;

    /**
     * Rfqufsts tibt mutubl butifntidbtion bf donf during
     * dontfxt fstbblisimfnt. Tiis rfqufst dbn only bf mbdf on tif dontfxt
     * initibtor's sidf bnd it ibs to bf donf prior to tif first dbll to
     * <dodf>initSfdContfxt</dodf>.<p>
     *
     * Not bll mfdibnisms support mutubl butifntidbtion bnd somf mfdibnisms
     * migit rfquirf mutubl butifntidbtion fvfn if tif bpplidbtion
     * dofsn't. Tifrfforf, tif bpplidbtion siould difdk to sff if tif
     * rfqufst wbs ionorfd witi tif {@link #gftMutublAutiStbtf()
     * gftMutublAutiStbtf} mftiod.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr mutubl
     * butifntidbtion siould bf usfd or not.
     * @sff #gftMutublAutiStbtf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstMutublAuti(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt rfplby dftfdtion bf fnbblfd for tif
     * pfr-mfssbgf sfdurity sfrvidfs bftfr dontfxt fstbblisimfnt. Tiis
     * rfqufst dbn only bf mbdf on tif dontfxt initibtor's sidf bnd it ibs
     * to bf donf prior to tif first dbll to
     * <dodf>initSfdContfxt</dodf>. During dontfxt fstbblisimfnt rfplby
     * dftfdtion is not bn option bnd is b fundtion of tif undfrlying
     * mfdibnism's dbpbbilitifs.<p>
     *
     * Not bll mfdibnisms support rfplby dftfdtion bnd somf mfdibnisms
     * migit rfquirf rfplby dftfdtion fvfn if tif bpplidbtion
     * dofsn't. Tifrfforf, tif bpplidbtion siould difdk to sff if tif
     * rfqufst wbs ionorfd witi tif {@link #gftRfplbyDftStbtf()
     * gftRfplbyDftStbtf} mftiod. If rfplby dftfdtion is fnbblfd tifn tif
     * {@link MfssbgfProp#isDuplidbtfTokfn() MfssbgfProp.isDuplidbtfTokfn} bnd {@link
     * MfssbgfProp#isOldTokfn() MfssbgfProp.isOldTokfn} mftiods will rfturn
     * vblid rfsults for tif <dodf>MfssbgfProp</dodf> objfdt tibt is pbssfd
     * in to tif <dodf>unwrbp</dodf> mftiod or tif <dodf>vfrifyMIC</dodf>
     * mftiod.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr rfplby dftfdtion
     * siould bf fnbblfd ovfr tif fstbblisifd dontfxt or not.
     * @sff #gftRfplbyDftStbtf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstRfplbyDft(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt sfqufndf difdking bf fnbblfd for tif
     * pfr-mfssbgf sfdurity sfrvidfs bftfr dontfxt fstbblisimfnt. Tiis
     * rfqufst dbn only bf mbdf on tif dontfxt initibtor's sidf bnd it ibs
     * to bf donf prior to tif first dbll to
     * <dodf>initSfdContfxt</dodf>. During dontfxt fstbblisimfnt sfqufndf
     * difdking is not bn option bnd is b fundtion of tif undfrlying
     * mfdibnism's dbpbbilitifs.<p>
     *
     * Not bll mfdibnisms support sfqufndf difdking bnd somf mfdibnisms
     * migit rfquirf sfqufndf difdking fvfn if tif bpplidbtion
     * dofsn't. Tifrfforf, tif bpplidbtion siould difdk to sff if tif
     * rfqufst wbs ionorfd witi tif {@link #gftSfqufndfDftStbtf()
     * gftSfqufndfDftStbtf} mftiod. If sfqufndf difdking is fnbblfd tifn tif
     * {@link MfssbgfProp#isDuplidbtfTokfn() MfssbgfProp.isDuplidbtfTokfn},
     * {@link MfssbgfProp#isOldTokfn() MfssbgfProp.isOldTokfn},
     * {@link MfssbgfProp#isUnsfqTokfn() MfssbgfProp.isUnsfqTokfn}, bnd
     * {@link MfssbgfProp#isGbpTokfn() MfssbgfProp.isGbpTokfn} mftiods will rfturn
     * vblid rfsults for tif <dodf>MfssbgfProp</dodf> objfdt tibt is pbssfd
     * in to tif <dodf>unwrbp</dodf> mftiod or tif <dodf>vfrifyMIC</dodf>
     * mftiod.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr sfqufndf difdking
     * siould bf fnbblfd ovfr tif fstbblisifd dontfxt or not.
     * @sff #gftSfqufndfDftStbtf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstSfqufndfDft(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt tif initibtor's drfdfntibls bf
     * dflfgbtfd to tif bddfptor during dontfxt fstbblisimfnt. Tiis
     * rfqufst dbn only bf mbdf on tif dontfxt initibtor's sidf bnd it ibs
     * to bf donf prior to tif first dbll to
     * <dodf>initSfdContfxt</dodf>.
     *
     * Not bll mfdibnisms support drfdfntibl dflfgbtion. Tifrfforf, bn
     * bpplidbtion tibt dfsirfs dflfgbtion siould difdk to sff if tif
     * rfqufst wbs ionorfd witi tif {@link #gftCrfdDflfgStbtf()
     * gftCrfdDflfgStbtf} mftiod. If tif bpplidbtion indidbtfs tibt
     * dflfgbtion must not bf usfd, tifn tif mfdibnism will ionor tif
     * rfqufst bnd dflfgbtion will not oddur. Tiis is bn fxdfption
     * to tif gfnfrbl rulf tibt b mfdibnism mby fnbblf b sfrvidf fvfn if it
     * is not rfqufstfd.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr tif drfdfntibls
     * siould bf dflfgbtfd or not.
     * @sff #gftCrfdDflfgStbtf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstCrfdDflfg(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt tif initibtor's idfntity not bf
     * disdlosfd to tif bddfptor. Tiis rfqufst dbn only bf mbdf on tif
     * dontfxt initibtor's sidf bnd it ibs to bf donf prior to tif first
     * dbll to <dodf>initSfdContfxt</dodf>.
     *
     * Not bll mfdibnisms support bnonymity for tif initibtor. Tifrfforf, tif
     * bpplidbtion siould difdk to sff if tif rfqufst wbs ionorfd witi tif
     * {@link #gftAnonymityStbtf() gftAnonymityStbtf} mftiod.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting if tif initibtor siould
     * bf butifntidbtfd to tif bddfptor bs bn bnonymous prindipbl.
     * @sff #gftAnonymityStbtf
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstAnonymity(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt dbtb donfidfntiblity bf fnbblfd
     * for tif <dodf>wrbp</dodf> mftiod. Tiis rfqufst dbn only bf mbdf on
     * tif dontfxt initibtor's sidf bnd it ibs to bf donf prior to tif
     * first dbll to <dodf>initSfdContfxt</dodf>.
     *
     * Not bll mfdibnisms support donfidfntiblity bnd otifr mfdibnisms
     * migit fnbblf it fvfn if tif bpplidbtion dofsn't rfqufst
     * it. Tif bpplidbtion mby difdk to sff if tif rfqufst wbs ionorfd witi
     * tif {@link #gftConfStbtf() gftConfStbtf} mftiod. If donfidfntiblity
     * is fnbblfd, only tifn will tif mfdibnism ionor b rfqufst for privbdy
     * in tif {@link MfssbgfProp#MfssbgfProp(int, boolfbn) MfssbgfProp}
     * objfdt tibt is pbssfd in to tif <dodf>wrbp</dodf> mftiod.<p>
     *
     * Enbbling donfidfntiblity will blso butombtidblly fnbblf
     * intfgrity.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr donfidfntiblity
     * siould bf fnbblfd or not.
     * @sff #gftConfStbtf()
     * @sff #gftIntfgStbtf()
     * @sff #rfqufstIntfg(boolfbn)
     * @sff MfssbgfProp
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstConf(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts tibt dbtb intfgrity bf fnbblfd
     * for tif <dodf>wrbp</dodf> bnd <dodf>gftMIC</dodf>mftiods. Tiis
     * rfqufst dbn only bf mbdf on tif dontfxt initibtor's sidf bnd it ibs
     * to bf donf prior to tif first dbll to <dodf>initSfdContfxt</dodf>.
     *
     * Not bll mfdibnisms support intfgrity bnd otifr mfdibnisms
     * migit fnbblf it fvfn if tif bpplidbtion dofsn't rfqufst
     * it. Tif bpplidbtion mby difdk to sff if tif rfqufst wbs ionorfd witi
     * tif {@link #gftIntfgStbtf() gftIntfgStbtf} mftiod.<p>
     *
     * Disbbling intfgrity will blso butombtidblly disbblf
     * donfidfntiblity.<p>
     *
     * @pbrbm stbtf b boolfbn vbluf indidbting wiftifr intfgrity
     * siould bf fnbblfd or not.
     * @sff #gftIntfgStbtf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstIntfg(boolfbn stbtf) tirows GSSExdfption;

    /**
     * Rfqufsts b lifftimf in sfdonds for tif
     * dontfxt. Tiis mftiod dbn only bf dbllfd on tif dontfxt initibtor's
     * sidf  bnd it ibs to bf donf prior to tif first dbll to
     * <dodf>initSfdContfxt</dodf>.<p>
     *
     * Tif bdtubl lifftimf of tif dontfxt will dfpfnd on tif dbpbbilitifs of
     * tif undfrlying mfdibnism bnd tif bpplidbtion siould dbll tif {@link
     * #gftLifftimf() gftLifftimf} mftiod to dftfrminf tiis.<p>
     *
     * @pbrbm lifftimf tif dfsirfd dontfxt lifftimf in sfdonds. Usf
     * <dodf>INDEFINITE_LIFETIME</dodf> to rfqufst bn indffinitf lifftimf
     * bnd <dodf>DEFAULT_LIFETIME</dodf> to rfqufst b dffbult lifftimf.
     * @sff #gftLifftimf()
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void rfqufstLifftimf(int lifftimf) tirows GSSExdfption;

    /**
     * Sfts tif dibnnfl bindings to bf usfd during dontfxt
     * fstbblisimfnt. Tiis mftiod dbn bf dbllfd on boti
     * tif dontfxt initibtor's bnd tif dontfxt bddfptor's sidf, but it must
     * bf dbllfd bfforf dontfxt fstbblisimfnt bfgins. Tiis mfbns tibt bn
     * initibtor must dbll it bfforf tif first dbll to
     * <dodf>initSfdContfxt</dodf> bnd tif bddfptor must dbll it bfforf tif
     * first dbll to <dodf>bddfptSfdContfxt</dodf>.
     *
     * @pbrbm db tif dibnnfl bindings to usf.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid void sftCibnnflBinding(CibnnflBinding db) tirows GSSExdfption;

    /**
     * Dftfrminfs if drfdfntibl dflfgbtion is fnbblfd on
     * tiis dontfxt. It dbn bf dbllfd by boti tif dontfxt initibtor bnd tif
     * dontfxt bddfptor. For b dffinitivf bnswfr tiis mftiod must bf
     * dbllfd only bftfr dontfxt fstbblisimfnt is domplftf. Notf tibt if bn
     * initibtor rfqufsts tibt dflfgbtion not bf bllowfd tif {@link
     * #rfqufstCrfdDflfg(boolfbn) rfqufstCrfdDflfg} mftiod will ionor tibt
     * rfqufst bnd tiis mftiod will rfturn <dodf>fblsf</dodf> on tif
     * initibtor's sidf from tibt point onwbrds. <p>
     *
     * @rfturn truf if dflfgbtion is fnbblfd, fblsf otifrwisf.
     * @sff #rfqufstCrfdDflfg(boolfbn)
     */
    publid boolfbn gftCrfdDflfgStbtf();

    /**
     * Dftfrminfs if mutubl butifntidbtion is fnbblfd on
     * tiis dontfxt. It dbn bf dbllfd by boti tif dontfxt initibtor bnd tif
     * dontfxt bddfptor. For b dffinitivf bnswfr tiis mftiod must bf
     * dbllfd only bftfr dontfxt fstbblisimfnt is domplftf. An initibtor
     * tibt rfqufsts mutubl butifntidbtion dbn dbll tiis mftiod bftfr
     * dontfxt domplftion bnd disposf tif dontfxt if its rfqufst wbs not
     * ionorfd.<p>
     *
     * @rfturn truf if mutubl butifntidbtion is fnbblfd, fblsf otifrwisf.
     * @sff #rfqufstMutublAuti(boolfbn)
     */
    publid boolfbn gftMutublAutiStbtf();

    /**
     * Dftfrminfs if rfplby dftfdtion is fnbblfd for tif
     * pfr-mfssbgf sfdurity sfrvidfs from tiis dontfxt. It dbn bf dbllfd by
     * boti tif dontfxt initibtor bnd tif dontfxt bddfptor. For b
     * dffinitivf bnswfr tiis mftiod must bf dbllfd only bftfr dontfxt
     * fstbblisimfnt is domplftf. An initibtor tibt rfqufsts rfplby
     * dftfdtion dbn dbll tiis mftiod bftfr dontfxt domplftion bnd
     * disposf tif dontfxt if its rfqufst wbs not ionorfd.<p>
     *
     * @rfturn truf if rfplby dftfdtion is fnbblfd, fblsf otifrwisf.
     * @sff #rfqufstRfplbyDft(boolfbn)
     */
    publid boolfbn gftRfplbyDftStbtf();

    /**
     * Dftfrminfs if sfqufndf difdking is fnbblfd for tif
     * pfr-mfssbgf sfdurity sfrvidfs from tiis dontfxt. It dbn bf dbllfd by
     * boti tif dontfxt initibtor bnd tif dontfxt bddfptor. For b
     * dffinitivf bnswfr tiis mftiod must bf dbllfd only bftfr dontfxt
     * fstbblisimfnt is domplftf. An initibtor tibt rfqufsts sfqufndf
     * difdking dbn dbll tiis mftiod bftfr dontfxt domplftion bnd
     * disposf tif dontfxt if its rfqufst wbs not ionorfd.<p>
     *
     * @rfturn truf if sfqufndf difdking is fnbblfd, fblsf otifrwisf.
     * @sff #rfqufstSfqufndfDft(boolfbn)
     */
    publid boolfbn gftSfqufndfDftStbtf();

    /**
     * Dftfrminfs if tif dontfxt initibtor is
     * bnonymously butifntidbtfd to tif dontfxt bddfptor. It dbn bf dbllfd by
     * boti tif dontfxt initibtor bnd tif dontfxt bddfptor, bnd bt bny
     * timf. <strong>On tif initibtor sidf, b dbll to tiis mftiod dftfrminfs
     * if tif idfntity of tif initibtor ibs bffn disdlosfd in bny of tif
     * dontfxt fstbblisimfnt tokfns tibt migit ibvf bffn gfnfrbtfd tius fbr
     * by <dodf>initSfdContfxt</dodf>. An initibtor tibt bbsolutfly must bf
     * butifntidbtfd bnonymously siould dbll tiis mftiod bftfr fbdi dbll to
     * <dodf>initSfdContfxt</dodf> to dftfrminf if tif gfnfrbtfd tokfn
     * siould bf sfnt to tif pffr or tif dontfxt bbortfd.</strong> On tif
     * bddfptor sidf, b dbll to tiis mftiod dftfrminfs if bny of tif tokfns
     * prodfssfd by <dodf>bddfptSfdContfxt</dodf> tius fbr ibvf divulgfd
     * tif idfntity of tif initibtor.<p>
     *
     * @rfturn truf if tif dontfxt initibtor is still bnonymous, fblsf
     * otifrwisf.
     * @sff #rfqufstAnonymity(boolfbn)
     */
    publid boolfbn gftAnonymityStbtf();

    /**
     * Dftfrminfs if tif dontfxt is trbnsffrbblf to otifr prodfssfs
     * tirougi tif usf of tif {@link #fxport() fxport} mftiod.  Tiis dbll
     * is only vblid on fully fstbblisifd dontfxts.
     *
     * @rfturn truf if tiis dontfxt dbn bf fxportfd, fblsf otifrwisf.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid boolfbn isTrbnsffrbblf() tirows GSSExdfption;

    /**
     * Dftfrminfs if tif dontfxt is rfbdy for pfr mfssbgf opfrbtions to bf
     * usfd ovfr it.  Somf mfdibnisms mby bllow tif usbgf of tif
     * pfr-mfssbgf opfrbtions bfforf tif dontfxt is fully fstbblisifd.
     *
     * @rfturn truf if mftiods likf <dodf>wrbp</dodf>, <dodf>unwrbp</dodf>,
     * <dodf>gftMIC</dodf>, bnd <dodf>vfrifyMIC</dodf> dbn bf usfd witi
     * tiis dontfxt bt tif durrfnt stbgf of dontfxt fstbblisimfnt, fblsf
     * otifrwisf.
     */
    publid boolfbn isProtRfbdy();

    /**
     * Dftfrminfs if dbtb donfidfntiblity is bvbilbblf
     * ovfr tif dontfxt. Tiis mftiod dbn bf dbllfd by boti tif dontfxt
     * initibtor bnd tif dontfxt bddfptor, but only bftfr onf of {@link
     * #isProtRfbdy() isProtRfbdy} or {@link #isEstbblisifd()
     * isEstbblisifd} rfturn <dodf>truf</dodf>. If tiis mftiod rfturns
     * <dodf>truf</dodf>, so will {@link #gftIntfgStbtf()
     * gftIntfgStbtf}<p>
     *
     * @rfturn truf if donfidfntiblity sfrvidfs brf bvbilbblf, fblsf
     * otifrwisf.
     * @sff #rfqufstConf(boolfbn)
     */
    publid boolfbn gftConfStbtf();

    /**
     * Dftfrminfs if dbtb intfgrity is bvbilbblf
     * ovfr tif dontfxt. Tiis mftiod dbn bf dbllfd by boti tif dontfxt
     * initibtor bnd tif dontfxt bddfptor, but only bftfr onf of {@link
     * #isProtRfbdy() isProtRfbdy} or {@link #isEstbblisifd()
     * isEstbblisifd} rfturn <dodf>truf</dodf>. Tiis mftiod will blwbys
     * rfturn <dodf>truf</dodf> if {@link #gftConfStbtf() gftConfStbtf}
     * rfturns truf.<p>
     *
     * @rfturn truf if intfgrity sfrvidfs brf bvbilbblf, fblsf otifrwisf.
     * @sff #rfqufstIntfg(boolfbn)
     */
    publid boolfbn gftIntfgStbtf();

    /**
     * Dftfrminfs wibt tif rfmbining lifftimf for tiis
     * dontfxt is. It dbn bf dbllfd by boti tif dontfxt initibtor bnd tif
     * dontfxt bddfptor, but for b dffinitivf bnswfr it siould bf dbllfd
     * only bftfr {@link #isEstbblisifd() isEstbblisifd} rfturns
     * truf.<p>
     *
     * @rfturn tif rfmbining lifftimf in sfdonds
     * @sff #rfqufstLifftimf(int)
     */
    publid int gftLifftimf();

    /**
     * Rfturns tif nbmf of tif dontfxt initibtor. Tiis dbll is vblid only
     * bftfr onf of {@link #isProtRfbdy() isProtRfbdy} or {@link
     * #isEstbblisifd() isEstbblisifd} rfturn <dodf>truf</dodf>.
     *
     * @rfturn b GSSNbmf tibt is bn MN dontbining tif nbmf of tif dontfxt
     * initibtor.
     * @sff GSSNbmf
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSNbmf gftSrdNbmf() tirows GSSExdfption;

    /**
     * Rfturns tif nbmf of tif dontfxt bddfptor. Tiis dbll is vblid only
     * bftfr onf of {@link #isProtRfbdy() isProtRfbdy} or {@link
     * #isEstbblisifd() isEstbblisifd} rfturn <dodf>truf</dodf>.
     *
     * @rfturn b GSSNbmf tibt is bn MN dontbining tif nbmf of tif dontfxt
     * bddfptor.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSNbmf gftTbrgNbmf() tirows GSSExdfption;

    /**
     * Dftfrminfs wibt mfdibnism is bfing usfd for tiis
     * dontfxt. Tiis mftiod mby bf dbllfd bfforf tif dontfxt is fully
     * fstbblisifd, but tif mfdibnism rfturnfd mby dibngf on suddfssivf
     * dblls in tif nfgotibtfd mfdibnism dbsf.
     *
     * @rfturn tif Oid of tif mfdibnism bfing usfd
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid Oid gftMfdi() tirows GSSExdfption;

    /**
     * Obtbins tif drfdfntibls dflfgbtfd by tif dontfxt
     * initibtor to tif dontfxt bddfptor. It siould bf dbllfd only on tif
     * dontfxt bddfptor's sidf, bnd ondf tif dontfxt is fully
     * fstbblisifd. Tif dbllfr dbn usf tif mftiod {@link
     * #gftCrfdDflfgStbtf() gftCrfdDflfgStbtf} to dftfrminf if tifrf brf
     * bny dflfgbtfd drfdfntibls.
     *
     * @rfturn b GSSCrfdfntibl dontbining tif initibtor's dflfgbtfd
     * drfdfntibls, or <dodf>null</dodf> is no drfdfntibls
     * wfrf dflfgbtfd.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid GSSCrfdfntibl gftDflfgCrfd() tirows GSSExdfption;

    /**
     * Dftfrminfs if tiis is tif dontfxt initibtor. Tiis
     * dbn bf dbllfd on boti tif dontfxt initibtor's bnd dontfxt bddfptor's
     * sidf.
     *
     * @rfturn truf if tiis is tif dontfxt initibtor, fblsf if it is tif
     * dontfxt bddfptor.
     *
     * @tirows GSSExdfption dontbining tif following
     * mbjor frror dodfs:
     *   {@link GSSExdfption#FAILURE GSSExdfption.FAILURE}
     */
    publid boolfbn isInitibtor() tirows GSSExdfption;
}
