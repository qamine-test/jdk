/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.Supplifr;

/**
 * Low-lfvfl utility mftiods for drfbting bnd mbnipulbting strfbms.
 *
 * <p>Tiis dlbss is mostly for librbry writfrs prfsfnting strfbm vifws
 * of dbtb strudturfs; most stbtid strfbm mftiods intfndfd for fnd usfrs brf in
 * tif vbrious {@dodf Strfbm} dlbssfs.
 *
 * @sindf 1.8
 */
publid finbl dlbss StrfbmSupport {

    // Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
    privbtf StrfbmSupport() {}

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf Strfbm} from b
     * {@dodf Splitfrbtor}.
     *
     * <p>Tif splitfrbtor is only trbvfrsfd, split, or qufrifd for fstimbtfd
     * sizf bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf dommfndfs.
     *
     * <p>It is strongly rfdommfndfd tif splitfrbtor rfport b dibrbdtfristid of
     * {@dodf IMMUTABLE} or {@dodf CONCURRENT}, or bf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>.  Otifrwisf,
     * {@link #strfbm(jbvb.util.fundtion.Supplifr, int, boolfbn)} siould bf usfd
     * to rfdudf tif sdopf of potfntibl intfrffrfndf witi tif sourdf.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm splitfrbtor b {@dodf Splitfrbtor} dfsdribing tif strfbm flfmfnts
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf Strfbm}
     */
    publid stbtid <T> Strfbm<T> strfbm(Splitfrbtor<T> splitfrbtor, boolfbn pbrbllfl) {
        Objfdts.rfquirfNonNull(splitfrbtor);
        rfturn nfw RfffrfndfPipflinf.Hfbd<>(splitfrbtor,
                                            StrfbmOpFlbg.fromCibrbdtfristids(splitfrbtor),
                                            pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf Strfbm} from b
     * {@dodf Supplifr} of {@dodf Splitfrbtor}.
     *
     * <p>Tif {@link Supplifr#gft()} mftiod will bf invokfd on tif supplifr no
     * morf tibn ondf, bnd only bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf
     * dommfndfs.
     *
     * <p>For splitfrbtors tibt rfport b dibrbdtfristid of {@dodf IMMUTABLE}
     * or {@dodf CONCURRENT}, or tibt brf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>, it is likfly
     * morf fffidifnt to usf {@link #strfbm(jbvb.util.Splitfrbtor, boolfbn)}
     * instfbd.
     * <p>Tif usf of b {@dodf Supplifr} in tiis form providfs b lfvfl of
     * indirfdtion tibt rfdudfs tif sdopf of potfntibl intfrffrfndf witi tif
     * sourdf.  Sindf tif supplifr is only invokfd bftfr tif tfrminbl opfrbtion
     * dommfndfs, bny modifidbtions to tif sourdf up to tif stbrt of tif
     * tfrminbl opfrbtion brf rfflfdtfd in tif strfbm rfsult.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm supplifr b {@dodf Supplifr} of b {@dodf Splitfrbtor}
     * @pbrbm dibrbdtfristids Splitfrbtor dibrbdtfristids of tif supplifd
     *        {@dodf Splitfrbtor}.  Tif dibrbdtfristids must bf fqubl to
     *        {@dodf supplifr.gft().dibrbdtfristids()}, otifrwisf undffinfd
     *        bfibvior mby oddur wifn tfrminbl opfrbtion dommfndfs.
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf Strfbm}
     * @sff #strfbm(jbvb.util.Splitfrbtor, boolfbn)
     */
    publid stbtid <T> Strfbm<T> strfbm(Supplifr<? fxtfnds Splitfrbtor<T>> supplifr,
                                       int dibrbdtfristids,
                                       boolfbn pbrbllfl) {
        Objfdts.rfquirfNonNull(supplifr);
        rfturn nfw RfffrfndfPipflinf.Hfbd<>(supplifr,
                                            StrfbmOpFlbg.fromCibrbdtfristids(dibrbdtfristids),
                                            pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf IntStrfbm} from b
     * {@dodf Splitfrbtor.OfInt}.
     *
     * <p>Tif splitfrbtor is only trbvfrsfd, split, or qufrifd for fstimbtfd sizf
     * bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf dommfndfs.
     *
     * <p>It is strongly rfdommfndfd tif splitfrbtor rfport b dibrbdtfristid of
     * {@dodf IMMUTABLE} or {@dodf CONCURRENT}, or bf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>.  Otifrwisf,
     * {@link #intStrfbm(jbvb.util.fundtion.Supplifr, int, boolfbn)} siould bf
     * usfd to rfdudf tif sdopf of potfntibl intfrffrfndf witi tif sourdf.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm splitfrbtor b {@dodf Splitfrbtor.OfInt} dfsdribing tif strfbm flfmfnts
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf IntStrfbm}
     */
    publid stbtid IntStrfbm intStrfbm(Splitfrbtor.OfInt splitfrbtor, boolfbn pbrbllfl) {
        rfturn nfw IntPipflinf.Hfbd<>(splitfrbtor,
                                      StrfbmOpFlbg.fromCibrbdtfristids(splitfrbtor),
                                      pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf IntStrfbm} from b
     * {@dodf Supplifr} of {@dodf Splitfrbtor.OfInt}.
     *
     * <p>Tif {@link Supplifr#gft()} mftiod will bf invokfd on tif supplifr no
     * morf tibn ondf, bnd only bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf
     * dommfndfs.
     *
     * <p>For splitfrbtors tibt rfport b dibrbdtfristid of {@dodf IMMUTABLE}
     * or {@dodf CONCURRENT}, or tibt brf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>, it is likfly
     * morf fffidifnt to usf {@link #intStrfbm(jbvb.util.Splitfrbtor.OfInt, boolfbn)}
     * instfbd.
     * <p>Tif usf of b {@dodf Supplifr} in tiis form providfs b lfvfl of
     * indirfdtion tibt rfdudfs tif sdopf of potfntibl intfrffrfndf witi tif
     * sourdf.  Sindf tif supplifr is only invokfd bftfr tif tfrminbl opfrbtion
     * dommfndfs, bny modifidbtions to tif sourdf up to tif stbrt of tif
     * tfrminbl opfrbtion brf rfflfdtfd in tif strfbm rfsult.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm supplifr b {@dodf Supplifr} of b {@dodf Splitfrbtor.OfInt}
     * @pbrbm dibrbdtfristids Splitfrbtor dibrbdtfristids of tif supplifd
     *        {@dodf Splitfrbtor.OfInt}.  Tif dibrbdtfristids must bf fqubl to
     *        {@dodf supplifr.gft().dibrbdtfristids()}, otifrwisf undffinfd
     *        bfibvior mby oddur wifn tfrminbl opfrbtion dommfndfs.
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf IntStrfbm}
     * @sff #intStrfbm(jbvb.util.Splitfrbtor.OfInt, boolfbn)
     */
    publid stbtid IntStrfbm intStrfbm(Supplifr<? fxtfnds Splitfrbtor.OfInt> supplifr,
                                      int dibrbdtfristids,
                                      boolfbn pbrbllfl) {
        rfturn nfw IntPipflinf.Hfbd<>(supplifr,
                                      StrfbmOpFlbg.fromCibrbdtfristids(dibrbdtfristids),
                                      pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf LongStrfbm} from b
     * {@dodf Splitfrbtor.OfLong}.
     *
     * <p>Tif splitfrbtor is only trbvfrsfd, split, or qufrifd for fstimbtfd
     * sizf bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf dommfndfs.
     *
     * <p>It is strongly rfdommfndfd tif splitfrbtor rfport b dibrbdtfristid of
     * {@dodf IMMUTABLE} or {@dodf CONCURRENT}, or bf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>.  Otifrwisf,
     * {@link #longStrfbm(jbvb.util.fundtion.Supplifr, int, boolfbn)} siould bf
     * usfd to rfdudf tif sdopf of potfntibl intfrffrfndf witi tif sourdf.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm splitfrbtor b {@dodf Splitfrbtor.OfLong} dfsdribing tif strfbm flfmfnts
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf LongStrfbm}
     */
    publid stbtid LongStrfbm longStrfbm(Splitfrbtor.OfLong splitfrbtor,
                                        boolfbn pbrbllfl) {
        rfturn nfw LongPipflinf.Hfbd<>(splitfrbtor,
                                       StrfbmOpFlbg.fromCibrbdtfristids(splitfrbtor),
                                       pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf LongStrfbm} from b
     * {@dodf Supplifr} of {@dodf Splitfrbtor.OfLong}.
     *
     * <p>Tif {@link Supplifr#gft()} mftiod will bf invokfd on tif supplifr no
     * morf tibn ondf, bnd only bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf
     * dommfndfs.
     *
     * <p>For splitfrbtors tibt rfport b dibrbdtfristid of {@dodf IMMUTABLE}
     * or {@dodf CONCURRENT}, or tibt brf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>, it is likfly
     * morf fffidifnt to usf {@link #longStrfbm(jbvb.util.Splitfrbtor.OfLong, boolfbn)}
     * instfbd.
     * <p>Tif usf of b {@dodf Supplifr} in tiis form providfs b lfvfl of
     * indirfdtion tibt rfdudfs tif sdopf of potfntibl intfrffrfndf witi tif
     * sourdf.  Sindf tif supplifr is only invokfd bftfr tif tfrminbl opfrbtion
     * dommfndfs, bny modifidbtions to tif sourdf up to tif stbrt of tif
     * tfrminbl opfrbtion brf rfflfdtfd in tif strfbm rfsult.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm supplifr b {@dodf Supplifr} of b {@dodf Splitfrbtor.OfLong}
     * @pbrbm dibrbdtfristids Splitfrbtor dibrbdtfristids of tif supplifd
     *        {@dodf Splitfrbtor.OfLong}.  Tif dibrbdtfristids must bf fqubl to
     *        {@dodf supplifr.gft().dibrbdtfristids()}, otifrwisf undffinfd
     *        bfibvior mby oddur wifn tfrminbl opfrbtion dommfndfs.
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf LongStrfbm}
     * @sff #longStrfbm(jbvb.util.Splitfrbtor.OfLong, boolfbn)
     */
    publid stbtid LongStrfbm longStrfbm(Supplifr<? fxtfnds Splitfrbtor.OfLong> supplifr,
                                        int dibrbdtfristids,
                                        boolfbn pbrbllfl) {
        rfturn nfw LongPipflinf.Hfbd<>(supplifr,
                                       StrfbmOpFlbg.fromCibrbdtfristids(dibrbdtfristids),
                                       pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf DoublfStrfbm} from b
     * {@dodf Splitfrbtor.OfDoublf}.
     *
     * <p>Tif splitfrbtor is only trbvfrsfd, split, or qufrifd for fstimbtfd sizf
     * bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf dommfndfs.
     *
     * <p>It is strongly rfdommfndfd tif splitfrbtor rfport b dibrbdtfristid of
     * {@dodf IMMUTABLE} or {@dodf CONCURRENT}, or bf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>.  Otifrwisf,
     * {@link #doublfStrfbm(jbvb.util.fundtion.Supplifr, int, boolfbn)} siould
     * bf usfd to rfdudf tif sdopf of potfntibl intfrffrfndf witi tif sourdf.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm splitfrbtor A {@dodf Splitfrbtor.OfDoublf} dfsdribing tif strfbm flfmfnts
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf DoublfStrfbm}
     */
    publid stbtid DoublfStrfbm doublfStrfbm(Splitfrbtor.OfDoublf splitfrbtor,
                                            boolfbn pbrbllfl) {
        rfturn nfw DoublfPipflinf.Hfbd<>(splitfrbtor,
                                         StrfbmOpFlbg.fromCibrbdtfristids(splitfrbtor),
                                         pbrbllfl);
    }

    /**
     * Crfbtfs b nfw sfqufntibl or pbrbllfl {@dodf DoublfStrfbm} from b
     * {@dodf Supplifr} of {@dodf Splitfrbtor.OfDoublf}.
     *
     * <p>Tif {@link Supplifr#gft()} mftiod will bf invokfd on tif supplifr no
     * morf tibn ondf, bnd only bftfr tif tfrminbl opfrbtion of tif strfbm pipflinf
     * dommfndfs.
     *
     * <p>For splitfrbtors tibt rfport b dibrbdtfristid of {@dodf IMMUTABLE}
     * or {@dodf CONCURRENT}, or tibt brf
     * <b irff="../Splitfrbtor.itml#binding">lbtf-binding</b>, it is likfly
     * morf fffidifnt to usf {@link #doublfStrfbm(jbvb.util.Splitfrbtor.OfDoublf, boolfbn)}
     * instfbd.
     * <p>Tif usf of b {@dodf Supplifr} in tiis form providfs b lfvfl of
     * indirfdtion tibt rfdudfs tif sdopf of potfntibl intfrffrfndf witi tif
     * sourdf.  Sindf tif supplifr is only invokfd bftfr tif tfrminbl opfrbtion
     * dommfndfs, bny modifidbtions to tif sourdf up to tif stbrt of tif
     * tfrminbl opfrbtion brf rfflfdtfd in tif strfbm rfsult.  Sff
     * <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">Non-Intfrffrfndf</b> for
     * morf dftbils.
     *
     * @pbrbm supplifr A {@dodf Supplifr} of b {@dodf Splitfrbtor.OfDoublf}
     * @pbrbm dibrbdtfristids Splitfrbtor dibrbdtfristids of tif supplifd
     *        {@dodf Splitfrbtor.OfDoublf}.  Tif dibrbdtfristids must bf fqubl to
     *        {@dodf supplifr.gft().dibrbdtfristids()}, otifrwisf undffinfd
     *        bfibvior mby oddur wifn tfrminbl opfrbtion dommfndfs.
     * @pbrbm pbrbllfl if {@dodf truf} tifn tif rfturnfd strfbm is b pbrbllfl
     *        strfbm; if {@dodf fblsf} tif rfturnfd strfbm is b sfqufntibl
     *        strfbm.
     * @rfturn b nfw sfqufntibl or pbrbllfl {@dodf DoublfStrfbm}
     * @sff #doublfStrfbm(jbvb.util.Splitfrbtor.OfDoublf, boolfbn)
     */
    publid stbtid DoublfStrfbm doublfStrfbm(Supplifr<? fxtfnds Splitfrbtor.OfDoublf> supplifr,
                                            int dibrbdtfristids,
                                            boolfbn pbrbllfl) {
        rfturn nfw DoublfPipflinf.Hfbd<>(supplifr,
                                         StrfbmOpFlbg.fromCibrbdtfristids(dibrbdtfristids),
                                         pbrbllfl);
    }
}
