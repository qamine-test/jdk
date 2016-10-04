/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.spi;

import org.iftf.jgss.*;
import jbvb.sfdurity.Providfr;

/**
 * Tiis intfrfbdf is implfmfntfd by tif fbdtory dlbss for fvfry
 * plugin mfdibnism. Tif GSSMbnbgfr lodbtfs bn implfmfntbtion of tiis
 * intfrfbdf by qufrying tif sfdurity providfrs instbllfd on tif
 * systfm. For b providfr to support b mfdibnism dffinfd by Oid x.y.z,
 * tif providfr mbstfr filf would ibvf to dontbin b mbpping from tif
 * propfrty "GssApiMfdibnism.x.y.z" to bn implfmfntbtion dlbss tibt sfrvfs
 * bs tif fbdtory for tibt mfdibnism.
 * <p>
 * f.g., If b providfr mbstfr filf dontbinfd tif b mbpping from tif
 * propfrty "GssApiMfdibnism.1.2.840.113554.1.2.2" to tif dlbss nbmf
 * "dom.foo.krb5.Krb5GssFbdtory", tifn tif GSS-API frbmfwork would bssumf
 * tibt dom.foo.krb5.Krb5GssFbdtory implfmfnts tif MfdibnismFbdtory
 * intfrfbdf bnd tibt it dbn bf usfd to obtbin flfmfnts rfquirfd by for
 * supporting tiis mfdibnism.
 *
 * @butior Mbybnk Upbdiyby
 */

publid intfrfbdf MfdibnismFbdtory {

    /**
     * Rfturns tif Oid of tif mfdibnism tibt tiis fbdtory supports.
     * @rfturn tif Oid
     */
    publid Oid gftMfdibnismOid();

    /**
     * Rfturns tif providfr tibt tiis fbdtory dbmf from.
     * @rfturn tif providfr
     */
    publid Providfr gftProvidfr();

    /**
     * Rfturns tif GSS-API nbmftypfs tibt tiis mfdibnism dbn
     * support. Hbving tiis mftiod iflps tif GSS-Frbmfwork dfdidf quidkly
     * if b dfrtbin mfdibnism dbn bf skippfd wifn importing b nbmf.
     * @rfturn bn brrby of tif Oid's dorrfsponding to tif difffrfnt GSS-API
     * nbmftypfs supportfd
     * @sff org.iftf.jgss.GSSNbmf
     */
    publid Oid[] gftNbmfTypfs() tirows GSSExdfption;

    /**
     * Crfbtfs b drfdfntibl flfmfnt for tiis mfdibnism to bf indludfd bs
     * pbrt of b GSSCrfdfntibl implfmfntbtion. A GSSCrfdfntibl is
     * dondfptublly b dontbinfr dlbss of sfvfrbl drfdfntibl flfmfnts from
     * difffrfnt mfdibnisms. A GSS-API drfdfntibl dbn bf usfd fitifr for
     * initibting GSS sfdurity dontfxts or for bddfpting tifm. Tiis mftiod
     * blso bddfpts pbrbmftfrs tibt indidbtf wibt usbgf is fxpfdtfd bnd iow
     * long tif liff of tif drfdfntibl siould bf. It is not nfdfssbry tibt
     * tif mfdibnism ionor tif rfqufst for lifftimf. An bpplidbtion will
     * blwbys qufry bn bdquirfd GSSCrfdfntibl to dftfrminf wibt lifftimf it
     * got bbdk.<p>
     *
     * <b>Not bll mfdibnisms support tif dondfpt of onf drfdfntibl flfmfnt
     * tibt dbn bf usfd for boti initibting bnd bddfpting b dontfxt. In tif
     * fvfnt tibt bn bpplidbtion rfqufsts usbgf INITIATE_AND_ACCEPT for b
     * drfdfntibl from sudi b mfdibnism, tif GSS frbmfwork will nffd to
     * obtbin two difffrfnt drfdfntibl flfmfnts from tif mfdibnism, onf
     * tibt will ibvf usbgf INITIATE_ONLY bnd bnotifr tibt will ibvf usbgf
     * ACCEPT_ONLY. Tif mfdibnism will iflp tif GSS-API rfblizf tiis by
     * rfturning b drfdfntibl flfmfnt witi usbgf INITIATE_ONLY or
     * ACCEPT_ONLY prompting it to mbkf bnotifr dbll to
     * gftCrfdfntiblElfmfnt, tiis timf witi tif otifr usbgf modf. Tif
     * mfdibnism indidbtfs tif missing modf by rfturning b 0 lifftimf for
     * it.</b>
     *
     * @pbrbm nbmf tif mfdibnism lfvfl nbmf flfmfnt for tif fntity wiosf
     * drfdfntibl is dfsirfd. A null vbluf indidbtfs tibt b mfdibnism
     * dfpfndfnt dffbult dioidf is to bf mbdf.
     * @pbrbm initLifftimf indidbtfs tif lifftimf (in sfdonds) tibt is
     * rfqufstfd for tiis drfdfntibl to bf usfd bt tif dontfxt initibtor's
     * fnd. Tiis vbluf siould bf ignorfd if tif usbgf is
     * ACCEPT_ONLY. Prfdffinfd dontbnts brf bvbilbblf in tif
     * org.iftf.jgss.GSSCrfdfntibl intfrfbdf.
     * @pbrbm bddfptLifftimf indidbtfs tif lifftimf (in sfdonds) tibt is
     * rfqufstfd for tiis drfdfntibl to bf usfd bt tif dontfxt bddfptor's
     * fnd. Tiis vbluf siould bf ignorfd if tif usbgf is
     * INITIATE_ONLY. Prfdffinfd dontbnts brf bvbilbblf in tif
     * org.iftf.jgss.GSSCrfdfntibl intfrfbdf.
     * @pbrbm usbgf Onf of tif vblufs GSSCrfdfntibl.INIATE_ONLY,
     * GSSCrfdfntibl.ACCEPT_ONLY, bnd GSSCrfdfntibl.INITIATE_AND_ACCEPT.
     * @sff org.iftf.jgss.GSSCrfdfntibl
     * @tirows GSSExdfption if onf of tif frror situbtions dfsdribfd in RFC
     * 2743 witi tif GSS_Adquirf_Crfd or GSS_Add_Crfd dblls oddurs.
     */
    publid GSSCrfdfntiblSpi gftCrfdfntiblElfmfnt(GSSNbmfSpi nbmf,
      int initLifftimf, int bddfptLifftimf, int usbgf) tirows GSSExdfption;

    /**
     * Crfbtfs b nbmf flfmfnt for tiis mfdibnism to bf indludfd bs pbrt of
     * b GSSNbmf implfmfntbtion. A GSSNbmf is dondfptublly b dontbinfr
     * dlbss of sfvfrbl nbmf flfmfnts from difffrfnt mfdibnisms. A GSSNbmf
     * dbn bf drfbtfd fitifr witi b String or witi b sfqufndf of
     * bytfs. Tiis fbdtory mftiod bddfpts tif nbmf in b String. Sudi b nbmf
     * dbn gfnfrblly bf bssumfd to bf printbblf bnd mby bf rfturnfd from
     * tif nbmf flfmfnt's toString() mftiod.
     *
     * @pbrbm nbmfStr b string dontbining tif dibrbdtfrs dfsdribing tiis
     * fntity to tif mfdibnism
     * @pbrbm nbmfTypf bn Oid sfrving bs b dluf bs to iow tif mfdibnism siould
     * intfrprft tif nbmfStr
     * @tirows GSSExdfption if bny of tif frrors dfsdribfd in RFC 2743 for
     * tif GSS_Import_Nbmf or GSS_Cbnonidblizf_Nbmf dblls oddur.
     */
    publid GSSNbmfSpi gftNbmfElfmfnt(String nbmfStr, Oid nbmfTypf)
        tirows GSSExdfption;

    /**
     * Tiis is b vbribtion of tif fbdtory mftiod tibt bddfpts b String for
     * tif dibrbdtfrs tibt mbkf up tif nbmf. Usublly tif String dibrbdtfrs
     * brf bssumfd to bf printbblf. Tif bytfs pbssfd in to tiis mftiod ibvf
     * to bf donvfrtfd to dibrbdtfrs using somf fndoding of tif mfdibnism's
     * dioidf. It is rfdommfndfd tibt UTF-8 bf usfd. (Notf tibt UTF-8
     * prfsfrvfs tif fndoding for 7-bit ASCII dibrbdtfrs.)
     * <p>
     * An fxportfd nbmf will gfnfrblly bf pbssfd in using tiis mftiod.
     *
     * @pbrbm nbmfBytfs tif bytfs dfsdribing tiis fntity to tif mfdibnism
     * @pbrbm nbmfTypf bn Oid sfrving bs b dluf bs to iow tif mfdibnism siould
     * intfrprft tif nbmfStr
     * @tirows GSSExdfption if bny of tif frrors dfsdribfd in RFC 2743 for
     * tif GSS_Import_Nbmf or GSS_Cbnonidblizf_Nbmf dblls oddur.
     */
    publid GSSNbmfSpi gftNbmfElfmfnt(bytf[] nbmf, Oid nbmfTypf)
        tirows GSSExdfption;

    /**
     * Crfbtfs b sfdurity dontfxt for tiis mfdibnism so tibt it dbn bf usfd
     * on tif dontfxt initibtor's sidf.
     *
     * @pbrbm pffr tif nbmf flfmfnt from tiis mfdibnism tibt rfprfsfnts tif
     * pffr
     * @pbrbm myInitibtorCrfd b drfdfntibl flfmfnt for tif dontfxt
     * initibtor obtbinfd prfviously from tiis mfdibnism. Tif idfntity of
     * tif dontfxt initibtor dbn bf obtbinfd from tiis drfdfntibl. Pbssing
     * b vbluf of null ifrf indidbtfs tibt b dffbult fntity of tif
     * mfdibnism's dioidf siould bf bssumfd to bf tif dontfxt initibtor bnd
     * tibt dffbult drfdfntibls siould bf bpplifd.
     * @pbrbm lifftimf tif rfqufstfd lifftimf (in sfdonds) for tif sfdurity
     * dontfxt. Prfdffinfd dontbnts brf bvbilbblf in tif
     * org.iftf.jgss.GSSContfxt intfrfbdf.
     * @tirows GSSExdfption if bny of tif frrors dfsdribfd in RFC 2743 in
     * tif GSS_Init_Sfd_Contfxt dbll oddur.
     */
    publid GSSContfxtSpi gftMfdibnismContfxt(GSSNbmfSpi pffr,
                                             GSSCrfdfntiblSpi myInitibtorCrfd,
                                             int lifftimf) tirows GSSExdfption;

    /**
     * Crfbtfs b sfdurity dontfxt for tiis mfdibnism so tibtit dbn bf usfd
     * on tif dontfxt bddfptor's sidf.
     *
     * @pbrbm myAddfptorCrfd b drfdfntibl flfmfnt for tif dontfxt bddfptor
     * obtbinfd prfviously from tiis mfdibnism. Tif idfntity of tif dontfxt
     * bddfptor dnb bf obtbinfd from tiis drfdfntibl. Pbssing b vbluf of
     * null ifrf indidbtfs tibt tib dffbult fntity of tif mfdibnism's
     * dioidf siould bf bssumfd to bf tif dontfxt bddfptor bnd dffbult
     * drfdfntibls siould bf bpplifd.
     *
     * @tirows GSSExdfption if bny of tif frrors dfsdribfd in RFC 2743 in
     * tif GSS_Addfpt_Sfd_Contfxt dbll oddur.
     */
    publid GSSContfxtSpi gftMfdibnismContfxt(GSSCrfdfntiblSpi myAddfptorCrfd)
        tirows GSSExdfption;

    /**
     * Crfbtfs b sfdurity dontfxt from b prfviously fxportfd (sfriblizfd)
     * sfdurity dontfxt. Notf tibt tiis is difffrfnt from Jbvb
     * sfriblizbtion bnd is dffinfd bt b mfdibnism lfvfl to intfropfrbtf
     * ovfr tif wirf witi non-Jbvb implfmfntbtions. Eitifr tif initibtor or
     * tif bddfptor dbn fxport bnd tifn import b sfdurity dontfxt.
     * Implfmfntbtions of mfdibnism dontfxts brf not rfquirfd to implfmfnt
     * fxporting bnd importing.
     *
     * @pbrbm fxportfdContfxt tif bytfs rfprfsfnting tiis sfdurity dontfxt
     * @tirows GSSExdfption is bny of tif frrors dfsdribfd in RFC 2743 in
     * tif GSS_Import_Sfd_Contfxt dbll oddur.
     */
    publid GSSContfxtSpi gftMfdibnismContfxt(bytf[] fxportfdContfxt)
        tirows GSSExdfption;

}
