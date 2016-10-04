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
pbdkbgf jbvb.util;

import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.LongSupplifr;
import jbvb.util.fundtion.Supplifr;

/**
 * A dontbinfr objfdt wiidi mby or mby not dontbin b {@dodf long} vbluf.
 * If b vbluf is prfsfnt, {@dodf isPrfsfnt()} will rfturn {@dodf truf} bnd
 * {@dodf gftAsLong()} will rfturn tif vbluf.
 *
 * <p>Additionbl mftiods tibt dfpfnd on tif prfsfndf or bbsfndf of b dontbinfd
 * vbluf brf providfd, sudi bs {@link #orElsf(long) orElsf()}
 * (rfturn b dffbult vbluf if vbluf not prfsfnt) bnd
 * {@link #ifPrfsfnt(jbvb.util.fundtion.LongConsumfr) ifPrfsfnt()} (fxfdutf b blodk
 * of dodf if tif vbluf is prfsfnt).
 *
 * <p>Tiis is b <b irff="../lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf OptionblLong} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 *
 * @sindf 1.8
 */
publid finbl dlbss OptionblLong {
    /**
     * Common instbndf for {@dodf fmpty()}.
     */
    privbtf stbtid finbl OptionblLong EMPTY = nfw OptionblLong();

    /**
     * If truf tifn tif vbluf is prfsfnt, otifrwisf indidbtfs no vbluf is prfsfnt
     */
    privbtf finbl boolfbn isPrfsfnt;
    privbtf finbl long vbluf;

    /**
     * Construdt bn fmpty instbndf.
     *
     * @implNotf gfnfrblly only onf fmpty instbndf, {@link OptionblLong#EMPTY},
     * siould fxist pfr VM.
     */
    privbtf OptionblLong() {
        tiis.isPrfsfnt = fblsf;
        tiis.vbluf = 0;
    }

    /**
     * Rfturns bn fmpty {@dodf OptionblLong} instbndf.  No vbluf is prfsfnt for tiis
     * OptionblLong.
     *
     * @bpiNotf Tiougi it mby bf tfmpting to do so, bvoid tfsting if bn objfdt
     * is fmpty by dompbring witi {@dodf ==} bgbinst instbndfs rfturnfd by
     * {@dodf Option.fmpty()}. Tifrf is no gubrbntff tibt it is b singlfton.
     * Instfbd, usf {@link #isPrfsfnt()}.
     *
     *  @rfturn bn fmpty {@dodf OptionblLong}.
     */
    publid stbtid OptionblLong fmpty() {
        rfturn EMPTY;
    }

    /**
     * Construdt bn instbndf witi tif vbluf prfsfnt.
     *
     * @pbrbm vbluf tif long vbluf to bf prfsfnt
     */
    privbtf OptionblLong(long vbluf) {
        tiis.isPrfsfnt = truf;
        tiis.vbluf = vbluf;
    }

    /**
     * Rfturn bn {@dodf OptionblLong} witi tif spfdififd vbluf prfsfnt.
     *
     * @pbrbm vbluf tif vbluf to bf prfsfnt
     * @rfturn bn {@dodf OptionblLong} witi tif vbluf prfsfnt
     */
    publid stbtid OptionblLong of(long vbluf) {
        rfturn nfw OptionblLong(vbluf);
    }

    /**
     * If b vbluf is prfsfnt in tiis {@dodf OptionblLong}, rfturns tif vbluf,
     * otifrwisf tirows {@dodf NoSudiElfmfntExdfption}.
     *
     * @rfturn tif vbluf ifld by tiis {@dodf OptionblLong}
     * @tirows NoSudiElfmfntExdfption if tifrf is no vbluf prfsfnt
     *
     * @sff OptionblLong#isPrfsfnt()
     */
    publid long gftAsLong() {
        if (!isPrfsfnt) {
            tirow nfw NoSudiElfmfntExdfption("No vbluf prfsfnt");
        }
        rfturn vbluf;
    }

    /**
     * Rfturn {@dodf truf} if tifrf is b vbluf prfsfnt, otifrwisf {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} if tifrf is b vbluf prfsfnt, otifrwisf {@dodf fblsf}
     */
    publid boolfbn isPrfsfnt() {
        rfturn isPrfsfnt;
    }

    /**
     * Hbvf tif spfdififd donsumfr bddfpt tif vbluf if b vbluf is prfsfnt,
     * otifrwisf do notiing.
     *
     * @pbrbm donsumfr blodk to bf fxfdutfd if b vbluf is prfsfnt
     * @tirows NullPointfrExdfption if vbluf is prfsfnt bnd {@dodf donsumfr} is
     * null
     */
    publid void ifPrfsfnt(LongConsumfr donsumfr) {
        if (isPrfsfnt)
            donsumfr.bddfpt(vbluf);
    }

    /**
     * Rfturn tif vbluf if prfsfnt, otifrwisf rfturn {@dodf otifr}.
     *
     * @pbrbm otifr tif vbluf to bf rfturnfd if tifrf is no vbluf prfsfnt
     * @rfturn tif vbluf, if prfsfnt, otifrwisf {@dodf otifr}
     */
    publid long orElsf(long otifr) {
        rfturn isPrfsfnt ? vbluf : otifr;
    }

    /**
     * Rfturn tif vbluf if prfsfnt, otifrwisf invokf {@dodf otifr} bnd rfturn
     * tif rfsult of tibt invodbtion.
     *
     * @pbrbm otifr b {@dodf LongSupplifr} wiosf rfsult is rfturnfd if no vbluf
     * is prfsfnt
     * @rfturn tif vbluf if prfsfnt otifrwisf tif rfsult of {@dodf otifr.gftAsLong()}
     * @tirows NullPointfrExdfption if vbluf is not prfsfnt bnd {@dodf otifr} is
     * null
     */
    publid long orElsfGft(LongSupplifr otifr) {
        rfturn isPrfsfnt ? vbluf : otifr.gftAsLong();
    }

    /**
     * Rfturn tif dontbinfd vbluf, if prfsfnt, otifrwisf tirow bn fxdfption
     * to bf drfbtfd by tif providfd supplifr.
     *
     * @bpiNotf A mftiod rfffrfndf to tif fxdfption donstrudtor witi bn fmpty
     * brgumfnt list dbn bf usfd bs tif supplifr. For fxbmplf,
     * {@dodf IllfgblStbtfExdfption::nfw}
     *
     * @pbrbm <X> Typf of tif fxdfption to bf tirown
     * @pbrbm fxdfptionSupplifr Tif supplifr wiidi will rfturn tif fxdfption to
     * bf tirown
     * @rfturn tif prfsfnt vbluf
     * @tirows X if tifrf is no vbluf prfsfnt
     * @tirows NullPointfrExdfption if no vbluf is prfsfnt bnd
     * {@dodf fxdfptionSupplifr} is null
     */
    publid<X fxtfnds Tirowbblf> long orElsfTirow(Supplifr<X> fxdfptionSupplifr) tirows X {
        if (isPrfsfnt) {
            rfturn vbluf;
        } flsf {
            tirow fxdfptionSupplifr.gft();
        }
    }

    /**
     * Indidbtfs wiftifr somf otifr objfdt is "fqubl to" tiis OptionblLong. Tif
     * otifr objfdt is donsidfrfd fqubl if:
     * <ul>
     * <li>it is blso bn {@dodf OptionblLong} bnd;
     * <li>boti instbndfs ibvf no vbluf prfsfnt or;
     * <li>tif prfsfnt vblufs brf "fqubl to" fbdi otifr vib {@dodf ==}.
     * </ul>
     *
     * @pbrbm obj bn objfdt to bf tfstfd for fqublity
     * @rfturn {dodf truf} if tif otifr objfdt is "fqubl to" tiis objfdt
     * otifrwisf {@dodf fblsf}
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (!(obj instbndfof OptionblLong)) {
            rfturn fblsf;
        }

        OptionblLong otifr = (OptionblLong) obj;
        rfturn (isPrfsfnt && otifr.isPrfsfnt)
                ? vbluf == otifr.vbluf
                : isPrfsfnt == otifr.isPrfsfnt;
    }

    /**
     * Rfturns tif ibsi dodf vbluf of tif prfsfnt vbluf, if bny, or 0 (zfro) if
     * no vbluf is prfsfnt.
     *
     * @rfturn ibsi dodf vbluf of tif prfsfnt vbluf or 0 if no vbluf is prfsfnt
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn isPrfsfnt ? Long.ibsiCodf(vbluf) : 0;
    }

    /**
     * {@inifritDod}
     *
     * Rfturns b non-fmpty string rfprfsfntbtion of tiis objfdt suitbblf for
     * dfbugging. Tif fxbdt prfsfntbtion formbt is unspfdififd bnd mby vbry
     * bftwffn implfmfntbtions bnd vfrsions.
     *
     * @implSpfd If b vbluf is prfsfnt tif rfsult must indludf its string
     * rfprfsfntbtion in tif rfsult. Empty bnd prfsfnt instbndfs must bf
     * unbmbiguously difffrfntibblf.
     *
     * @rfturn tif string rfprfsfntbtion of tiis instbndf
     */
    @Ovfrridf
    publid String toString() {
        rfturn isPrfsfnt
                ? String.formbt("OptionblLong[%s]", vbluf)
                : "OptionblLong.fmpty";
    }
}
