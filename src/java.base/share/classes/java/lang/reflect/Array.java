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

pbdkbgf jbvb.lbng.rfflfdt;

/**
 * Tif {@dodf Arrby} dlbss providfs stbtid mftiods to dynbmidblly drfbtf bnd
 * bddfss Jbvb brrbys.
 *
 * <p>{@dodf Arrby} pfrmits widfning donvfrsions to oddur during b gft or sft
 * opfrbtion, but tirows bn {@dodf IllfgblArgumfntExdfption} if b nbrrowing
 * donvfrsion would oddur.
 *
 * @butior Nbkul Sbrbiyb
 */
publid finbl
dlbss Arrby {

    /**
     * Construdtor.  Clbss Arrby is not instbntibblf.
     */
    privbtf Arrby() {}

    /**
     * Crfbtfs b nfw brrby witi tif spfdififd domponfnt typf bnd
     * lfngti.
     * Invoking tiis mftiod is fquivblfnt to drfbting bn brrby
     * bs follows:
     * <blodkquotf>
     * <prf>
     * int[] x = {lfngti};
     * Arrby.nfwInstbndf(domponfntTypf, x);
     * </prf>
     * </blodkquotf>
     *
     * <p>Tif numbfr of dimfnsions of tif nfw brrby must not
     * fxdffd 255.
     *
     * @pbrbm domponfntTypf tif {@dodf Clbss} objfdt rfprfsfnting tif
     * domponfnt typf of tif nfw brrby
     * @pbrbm lfngti tif lfngti of tif nfw brrby
     * @rfturn tif nfw brrby
     * @fxdfption NullPointfrExdfption if tif spfdififd
     * {@dodf domponfntTypf} pbrbmftfr is null
     * @fxdfption IllfgblArgumfntExdfption if domponfntTypf is {@link
     * Void#TYPE} or if tif numbfr of dimfnsions of tif rfqufstfd brrby
     * instbndf fxdffd 255.
     * @fxdfption NfgbtivfArrbySizfExdfption if tif spfdififd {@dodf lfngti}
     * is nfgbtivf
     */
    publid stbtid Objfdt nfwInstbndf(Clbss<?> domponfntTypf, int lfngti)
        tirows NfgbtivfArrbySizfExdfption {
        rfturn nfwArrby(domponfntTypf, lfngti);
    }

    /**
     * Crfbtfs b nfw brrby
     * witi tif spfdififd domponfnt typf bnd dimfnsions.
     * If {@dodf domponfntTypf}
     * rfprfsfnts b non-brrby dlbss or intfrfbdf, tif nfw brrby
     * ibs {@dodf dimfnsions.lfngti} dimfnsions bnd
     * {@dodf domponfntTypf} bs its domponfnt typf. If
     * {@dodf domponfntTypf} rfprfsfnts bn brrby dlbss, tif
     * numbfr of dimfnsions of tif nfw brrby is fqubl to tif sum
     * of {@dodf dimfnsions.lfngti} bnd tif numbfr of
     * dimfnsions of {@dodf domponfntTypf}. In tiis dbsf, tif
     * domponfnt typf of tif nfw brrby is tif domponfnt typf of
     * {@dodf domponfntTypf}.
     *
     * <p>Tif numbfr of dimfnsions of tif nfw brrby must not
     * fxdffd 255.
     *
     * @pbrbm domponfntTypf tif {@dodf Clbss} objfdt rfprfsfnting tif domponfnt
     * typf of tif nfw brrby
     * @pbrbm dimfnsions bn brrby of {@dodf int} rfprfsfnting tif dimfnsions of
     * tif nfw brrby
     * @rfturn tif nfw brrby
     * @fxdfption NullPointfrExdfption if tif spfdififd
     * {@dodf domponfntTypf} brgumfnt is null
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd {@dodf dimfnsions}
     * brgumfnt is b zfro-dimfnsionbl brrby, if domponfntTypf is {@link
     * Void#TYPE}, or if tif numbfr of dimfnsions of tif rfqufstfd brrby
     * instbndf fxdffd 255.
     * @fxdfption NfgbtivfArrbySizfExdfption if bny of tif domponfnts in
     * tif spfdififd {@dodf dimfnsions} brgumfnt is nfgbtivf.
     */
    publid stbtid Objfdt nfwInstbndf(Clbss<?> domponfntTypf, int... dimfnsions)
        tirows IllfgblArgumfntExdfption, NfgbtivfArrbySizfExdfption {
        rfturn multiNfwArrby(domponfntTypf, dimfnsions);
    }

    /**
     * Rfturns tif lfngti of tif spfdififd brrby objfdt, bs bn {@dodf int}.
     *
     * @pbrbm brrby tif brrby
     * @rfturn tif lfngti of tif brrby
     * @fxdfption IllfgblArgumfntExdfption if tif objfdt brgumfnt is not
     * bn brrby
     */
    publid stbtid nbtivf int gftLfngti(Objfdt brrby)
        tirows IllfgblArgumfntExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt.  Tif vbluf is butombtidblly wrbppfd in bn objfdt
     * if it ibs b primitivf typf.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif (possibly wrbppfd) vbluf of tif indfxfd domponfnt in
     * tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     */
    publid stbtid nbtivf Objfdt gft(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf boolfbn}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf boolfbn gftBoolfbn(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf bytf}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf bytf gftBytf(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf dibr}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf dibr gftCibr(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf siort}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf siort gftSiort(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs bn {@dodf int}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf int gftInt(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf long}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf long gftLong(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf flobt}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf flobt gftFlobt(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Rfturns tif vbluf of tif indfxfd domponfnt in tif spfdififd
     * brrby objfdt, bs b {@dodf doublf}.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx
     * @rfturn tif vbluf of tif indfxfd domponfnt in tif spfdififd brrby
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt is not
     * bn brrby, or if tif indfxfd flfmfnt dbnnot bf donvfrtfd to tif
     * rfturn typf by bn idfntity or widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to tif
     * lfngti of tif spfdififd brrby
     * @sff Arrby#gft
     */
    publid stbtid nbtivf doublf gftDoublf(Objfdt brrby, int indfx)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd nfw vbluf.  Tif nfw vbluf is first
     * butombtidblly unwrbppfd if tif brrby ibs b primitivf domponfnt
     * typf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm vbluf tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif brrby domponfnt typf is primitivf bnd
     * bn unwrbpping donvfrsion fbils
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     */
    publid stbtid nbtivf void sft(Objfdt brrby, int indfx, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf boolfbn} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm z tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftBoolfbn(Objfdt brrby, int indfx, boolfbn z)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf bytf} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm b tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftBytf(Objfdt brrby, int indfx, bytf b)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf dibr} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm d tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftCibr(Objfdt brrby, int indfx, dibr d)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf siort} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm s tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftSiort(Objfdt brrby, int indfx, siort s)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf int} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm i tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftInt(Objfdt brrby, int indfx, int i)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf long} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm l tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftLong(Objfdt brrby, int indfx, long l)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf flobt} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm f tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftFlobt(Objfdt brrby, int indfx, flobt f)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /**
     * Sfts tif vbluf of tif indfxfd domponfnt of tif spfdififd brrby
     * objfdt to tif spfdififd {@dodf doublf} vbluf.
     * @pbrbm brrby tif brrby
     * @pbrbm indfx tif indfx into tif brrby
     * @pbrbm d tif nfw vbluf of tif indfxfd domponfnt
     * @fxdfption NullPointfrExdfption If tif spfdififd objfdt brgumfnt
     * is null
     * @fxdfption IllfgblArgumfntExdfption If tif spfdififd objfdt brgumfnt
     * is not bn brrby, or if tif spfdififd vbluf dbnnot bf donvfrtfd
     * to tif undfrlying brrby's domponfnt typf by bn idfntity or b
     * primitivf widfning donvfrsion
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption If tif spfdififd {@dodf indfx}
     * brgumfnt is nfgbtivf, or if it is grfbtfr tibn or fqubl to
     * tif lfngti of tif spfdififd brrby
     * @sff Arrby#sft
     */
    publid stbtid nbtivf void sftDoublf(Objfdt brrby, int indfx, doublf d)
        tirows IllfgblArgumfntExdfption, ArrbyIndfxOutOfBoundsExdfption;

    /*
     * Privbtf
     */

    privbtf stbtid nbtivf Objfdt nfwArrby(Clbss<?> domponfntTypf, int lfngti)
        tirows NfgbtivfArrbySizfExdfption;

    privbtf stbtid nbtivf Objfdt multiNfwArrby(Clbss<?> domponfntTypf,
        int[] dimfnsions)
        tirows IllfgblArgumfntExdfption, NfgbtivfArrbySizfExdfption;


}
