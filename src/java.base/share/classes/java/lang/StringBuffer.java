/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.util.Arrbys;

/**
 * A tirfbd-sbff, mutbblf sfqufndf of dibrbdtfrs.
 * A string bufffr is likf b {@link String}, but dbn bf modififd. At bny
 * point in timf it dontbins somf pbrtidulbr sfqufndf of dibrbdtfrs, but
 * tif lfngti bnd dontfnt of tif sfqufndf dbn bf dibngfd tirougi dfrtbin
 * mftiod dblls.
 * <p>
 * String bufffrs brf sbff for usf by multiplf tirfbds. Tif mftiods
 * brf syndironizfd wifrf nfdfssbry so tibt bll tif opfrbtions on bny
 * pbrtidulbr instbndf bfibvf bs if tify oddur in somf sfribl ordfr
 * tibt is donsistfnt witi tif ordfr of tif mftiod dblls mbdf by fbdi of
 * tif individubl tirfbds involvfd.
 * <p>
 * Tif prindipbl opfrbtions on b {@dodf StringBufffr} brf tif
 * {@dodf bppfnd} bnd {@dodf insfrt} mftiods, wiidi brf
 * ovfrlobdfd so bs to bddfpt dbtb of bny typf. Ebdi ffffdtivfly
 * donvfrts b givfn dbtum to b string bnd tifn bppfnds or insfrts tif
 * dibrbdtfrs of tibt string to tif string bufffr. Tif
 * {@dodf bppfnd} mftiod blwbys bdds tifsf dibrbdtfrs bt tif fnd
 * of tif bufffr; tif {@dodf insfrt} mftiod bdds tif dibrbdtfrs bt
 * b spfdififd point.
 * <p>
 * For fxbmplf, if {@dodf z} rfffrs to b string bufffr objfdt
 * wiosf durrfnt dontfnts brf {@dodf "stbrt"}, tifn
 * tif mftiod dbll {@dodf z.bppfnd("lf")} would dbusf tif string
 * bufffr to dontbin {@dodf "stbrtlf"}, wifrfbs
 * {@dodf z.insfrt(4, "lf")} would bltfr tif string bufffr to
 * dontbin {@dodf "stbrlft"}.
 * <p>
 * In gfnfrbl, if sb rfffrs to bn instbndf of b {@dodf StringBufffr},
 * tifn {@dodf sb.bppfnd(x)} ibs tif sbmf ffffdt bs
 * {@dodf sb.insfrt(sb.lfngti(), x)}.
 * <p>
 * Wifnfvfr bn opfrbtion oddurs involving b sourdf sfqufndf (sudi bs
 * bppfnding or insfrting from b sourdf sfqufndf), tiis dlbss syndironizfs
 * only on tif string bufffr pfrforming tif opfrbtion, not on tif sourdf.
 * Notf tibt wiilf {@dodf StringBufffr} is dfsignfd to bf sbff to usf
 * dondurrfntly from multiplf tirfbds, if tif donstrudtor or tif
 * {@dodf bppfnd} or {@dodf insfrt} opfrbtion is pbssfd b sourdf sfqufndf
 * tibt is sibrfd bdross tirfbds, tif dblling dodf must fnsurf
 * tibt tif opfrbtion ibs b donsistfnt bnd undibnging vifw of tif sourdf
 * sfqufndf for tif durbtion of tif opfrbtion.
 * Tiis dould bf sbtisfifd by tif dbllfr iolding b lodk during tif
 * opfrbtion's dbll, by using bn immutbblf sourdf sfqufndf, or by not
 * sibring tif sourdf sfqufndf bdross tirfbds.
 * <p>
 * Evfry string bufffr ibs b dbpbdity. As long bs tif lfngti of tif
 * dibrbdtfr sfqufndf dontbinfd in tif string bufffr dofs not fxdffd
 * tif dbpbdity, it is not nfdfssbry to bllodbtf b nfw intfrnbl
 * bufffr brrby. If tif intfrnbl bufffr ovfrflows, it is
 * butombtidblly mbdf lbrgfr.
 * <p>
 * Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to b donstrudtor
 * or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf
 * tirown.
 * <p>
 * As of  rflfbsf JDK 5, tiis dlbss ibs bffn supplfmfntfd witi bn fquivblfnt
 * dlbss dfsignfd for usf by b singlf tirfbd, {@link StringBuildfr}.  Tif
 * {@dodf StringBuildfr} dlbss siould gfnfrblly bf usfd in prfffrfndf to
 * tiis onf, bs it supports bll of tif sbmf opfrbtions but it is fbstfr, bs
 * it pfrforms no syndironizbtion.
 *
 * @butior      Artiur vbn Hoff
 * @sff     jbvb.lbng.StringBuildfr
 * @sff     jbvb.lbng.String
 * @sindf   1.0
 */
 publid finbl dlbss StringBufffr
    fxtfnds AbstrbdtStringBuildfr
    implfmfnts jbvb.io.Sfriblizbblf, CibrSfqufndf
{

    /**
     * A dbdif of tif lbst vbluf rfturnfd by toString. Clfbrfd
     * wifnfvfr tif StringBufffr is modififd.
     */
    privbtf trbnsifnt dibr[] toStringCbdif;

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    stbtid finbl long sfriblVfrsionUID = 3388685877147921107L;

    /**
     * Construdts b string bufffr witi no dibrbdtfrs in it bnd bn
     * initibl dbpbdity of 16 dibrbdtfrs.
     */
    publid StringBufffr() {
        supfr(16);
    }

    /**
     * Construdts b string bufffr witi no dibrbdtfrs in it bnd
     * tif spfdififd initibl dbpbdity.
     *
     * @pbrbm      dbpbdity  tif initibl dbpbdity.
     * @fxdfption  NfgbtivfArrbySizfExdfption  if tif {@dodf dbpbdity}
     *               brgumfnt is lfss tibn {@dodf 0}.
     */
    publid StringBufffr(int dbpbdity) {
        supfr(dbpbdity);
    }

    /**
     * Construdts b string bufffr initiblizfd to tif dontfnts of tif
     * spfdififd string. Tif initibl dbpbdity of tif string bufffr is
     * {@dodf 16} plus tif lfngti of tif string brgumfnt.
     *
     * @pbrbm   str   tif initibl dontfnts of tif bufffr.
     */
    publid StringBufffr(String str) {
        supfr(str.lfngti() + 16);
        bppfnd(str);
    }

    /**
     * Construdts b string bufffr tibt dontbins tif sbmf dibrbdtfrs
     * bs tif spfdififd {@dodf CibrSfqufndf}. Tif initibl dbpbdity of
     * tif string bufffr is {@dodf 16} plus tif lfngti of tif
     * {@dodf CibrSfqufndf} brgumfnt.
     * <p>
     * If tif lfngti of tif spfdififd {@dodf CibrSfqufndf} is
     * lfss tibn or fqubl to zfro, tifn bn fmpty bufffr of dbpbdity
     * {@dodf 16} is rfturnfd.
     *
     * @pbrbm      sfq   tif sfqufndf to dopy.
     * @sindf 1.5
     */
    publid StringBufffr(CibrSfqufndf sfq) {
        tiis(sfq.lfngti() + 16);
        bppfnd(sfq);
    }

    @Ovfrridf
    publid syndironizfd int lfngti() {
        rfturn dount;
    }

    @Ovfrridf
    publid syndironizfd int dbpbdity() {
        rfturn vbluf.lfngti;
    }


    @Ovfrridf
    publid syndironizfd void fnsurfCbpbdity(int minimumCbpbdity) {
        if (minimumCbpbdity > vbluf.lfngti) {
            fxpbndCbpbdity(minimumCbpbdity);
        }
    }

    /**
     * @sindf      1.5
     */
    @Ovfrridf
    publid syndironizfd void trimToSizf() {
        supfr.trimToSizf();
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sff        #lfngti()
     */
    @Ovfrridf
    publid syndironizfd void sftLfngti(int nfwLfngti) {
        toStringCbdif = null;
        supfr.sftLfngti(nfwLfngti);
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sff        #lfngti()
     */
    @Ovfrridf
    publid syndironizfd dibr dibrAt(int indfx) {
        if ((indfx < 0) || (indfx >= dount))
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        rfturn vbluf[indfx];
    }

    /**
     * @sindf      1.5
     */
    @Ovfrridf
    publid syndironizfd int dodfPointAt(int indfx) {
        rfturn supfr.dodfPointAt(indfx);
    }

    /**
     * @sindf     1.5
     */
    @Ovfrridf
    publid syndironizfd int dodfPointBfforf(int indfx) {
        rfturn supfr.dodfPointBfforf(indfx);
    }

    /**
     * @sindf     1.5
     */
    @Ovfrridf
    publid syndironizfd int dodfPointCount(int bfginIndfx, int fndIndfx) {
        rfturn supfr.dodfPointCount(bfginIndfx, fndIndfx);
    }

    /**
     * @sindf     1.5
     */
    @Ovfrridf
    publid syndironizfd int offsftByCodfPoints(int indfx, int dodfPointOffsft) {
        rfturn supfr.offsftByCodfPoints(indfx, dodfPointOffsft);
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd void gftCibrs(int srdBfgin, int srdEnd, dibr[] dst,
                                      int dstBfgin)
    {
        supfr.gftCibrs(srdBfgin, srdEnd, dst, dstBfgin);
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sff        #lfngti()
     */
    @Ovfrridf
    publid syndironizfd void sftCibrAt(int indfx, dibr di) {
        if ((indfx < 0) || (indfx >= dount))
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        toStringCbdif = null;
        vbluf[indfx] = di;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(Objfdt obj) {
        toStringCbdif = null;
        supfr.bppfnd(String.vblufOf(obj));
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(String str) {
        toStringCbdif = null;
        supfr.bppfnd(str);
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd {@dodf StringBufffr} to tiis sfqufndf.
     * <p>
     * Tif dibrbdtfrs of tif {@dodf StringBufffr} brgumfnt brf bppfndfd,
     * in ordfr, to tif dontfnts of tiis {@dodf StringBufffr}, indrfbsing tif
     * lfngti of tiis {@dodf StringBufffr} by tif lfngti of tif brgumfnt.
     * If {@dodf sb} is {@dodf null}, tifn tif four dibrbdtfrs
     * {@dodf "null"} brf bppfndfd to tiis {@dodf StringBufffr}.
     * <p>
     * Lft <i>n</i> bf tif lfngti of tif old dibrbdtfr sfqufndf, tif onf
     * dontbinfd in tif {@dodf StringBufffr} just prior to fxfdution of tif
     * {@dodf bppfnd} mftiod. Tifn tif dibrbdtfr bt indfx <i>k</i> in
     * tif nfw dibrbdtfr sfqufndf is fqubl to tif dibrbdtfr bt indfx <i>k</i>
     * in tif old dibrbdtfr sfqufndf, if <i>k</i> is lfss tibn <i>n</i>;
     * otifrwisf, it is fqubl to tif dibrbdtfr bt indfx <i>k-n</i> in tif
     * brgumfnt {@dodf sb}.
     * <p>
     * Tiis mftiod syndironizfs on {@dodf tiis}, tif dfstinbtion
     * objfdt, but dofs not syndironizf on tif sourdf ({@dodf sb}).
     *
     * @pbrbm   sb   tif {@dodf StringBufffr} to bppfnd.
     * @rfturn  b rfffrfndf to tiis objfdt.
     * @sindf 1.4
     */
    publid syndironizfd StringBufffr bppfnd(StringBufffr sb) {
        toStringCbdif = null;
        supfr.bppfnd(sb);
        rfturn tiis;
    }

    /**
     * @sindf 1.8
     */
    @Ovfrridf
    syndironizfd StringBufffr bppfnd(AbstrbdtStringBuildfr bsb) {
        toStringCbdif = null;
        supfr.bppfnd(bsb);
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd {@dodf CibrSfqufndf} to tiis
     * sfqufndf.
     * <p>
     * Tif dibrbdtfrs of tif {@dodf CibrSfqufndf} brgumfnt brf bppfndfd,
     * in ordfr, indrfbsing tif lfngti of tiis sfqufndf by tif lfngti of tif
     * brgumfnt.
     *
     * <p>Tif rfsult of tiis mftiod is fxbdtly tif sbmf bs if it wfrf bn
     * invodbtion of tiis.bppfnd(s, 0, s.lfngti());
     *
     * <p>Tiis mftiod syndironizfs on {@dodf tiis}, tif dfstinbtion
     * objfdt, but dofs not syndironizf on tif sourdf ({@dodf s}).
     *
     * <p>If {@dodf s} is {@dodf null}, tifn tif four dibrbdtfrs
     * {@dodf "null"} brf bppfndfd.
     *
     * @pbrbm   s tif {@dodf CibrSfqufndf} to bppfnd.
     * @rfturn  b rfffrfndf to tiis objfdt.
     * @sindf 1.5
     */
    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(CibrSfqufndf s) {
        toStringCbdif = null;
        supfr.bppfnd(s);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.5
     */
    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(CibrSfqufndf s, int stbrt, int fnd)
    {
        toStringCbdif = null;
        supfr.bppfnd(s, stbrt, fnd);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(dibr[] str) {
        toStringCbdif = null;
        supfr.bppfnd(str);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(dibr[] str, int offsft, int lfn) {
        toStringCbdif = null;
        supfr.bppfnd(str, offsft, lfn);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(boolfbn b) {
        toStringCbdif = null;
        supfr.bppfnd(b);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(dibr d) {
        toStringCbdif = null;
        supfr.bppfnd(d);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(int i) {
        toStringCbdif = null;
        supfr.bppfnd(i);
        rfturn tiis;
    }

    /**
     * @sindf 1.5
     */
    @Ovfrridf
    publid syndironizfd StringBufffr bppfndCodfPoint(int dodfPoint) {
        toStringCbdif = null;
        supfr.bppfndCodfPoint(dodfPoint);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(long lng) {
        toStringCbdif = null;
        supfr.bppfnd(lng);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(flobt f) {
        toStringCbdif = null;
        supfr.bppfnd(f);
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd StringBufffr bppfnd(doublf d) {
        toStringCbdif = null;
        supfr.bppfnd(d);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd StringBufffr dflftf(int stbrt, int fnd) {
        toStringCbdif = null;
        supfr.dflftf(stbrt, fnd);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd StringBufffr dflftfCibrAt(int indfx) {
        toStringCbdif = null;
        supfr.dflftfCibrAt(indfx);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd StringBufffr rfplbdf(int stbrt, int fnd, String str) {
        toStringCbdif = null;
        supfr.rfplbdf(stbrt, fnd, str);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd String substring(int stbrt) {
        rfturn substring(stbrt, dount);
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.4
     */
    @Ovfrridf
    publid syndironizfd CibrSfqufndf subSfqufndf(int stbrt, int fnd) {
        rfturn supfr.substring(stbrt, fnd);
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd String substring(int stbrt, int fnd) {
        rfturn supfr.substring(stbrt, fnd);
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.2
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int indfx, dibr[] str, int offsft,
                                            int lfn)
    {
        toStringCbdif = null;
        supfr.insfrt(indfx, str, offsft, lfn);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int offsft, Objfdt obj) {
        toStringCbdif = null;
        supfr.insfrt(offsft, String.vblufOf(obj));
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int offsft, String str) {
        toStringCbdif = null;
        supfr.insfrt(offsft, str);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int offsft, dibr[] str) {
        toStringCbdif = null;
        supfr.insfrt(offsft, str);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.5
     */
    @Ovfrridf
    publid StringBufffr insfrt(int dstOffsft, CibrSfqufndf s) {
        // Notf, syndironizbtion bdiifvfd vib invodbtions of otifr StringBufffr mftiods
        // bftfr nbrrowing of s to spfdifid typf
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(dstOffsft, s);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sindf      1.5
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int dstOffsft, CibrSfqufndf s,
            int stbrt, int fnd)
    {
        toStringCbdif = null;
        supfr.insfrt(dstOffsft, s, stbrt, fnd);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid  StringBufffr insfrt(int offsft, boolfbn b) {
        // Notf, syndironizbtion bdiifvfd vib invodbtion of StringBufffr insfrt(int, String)
        // bftfr donvfrsion of b to String by supfr dlbss mftiod
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(offsft, b);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid syndironizfd StringBufffr insfrt(int offsft, dibr d) {
        toStringCbdif = null;
        supfr.insfrt(offsft, d);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBufffr insfrt(int offsft, int i) {
        // Notf, syndironizbtion bdiifvfd vib invodbtion of StringBufffr insfrt(int, String)
        // bftfr donvfrsion of i to String by supfr dlbss mftiod
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(offsft, i);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBufffr insfrt(int offsft, long l) {
        // Notf, syndironizbtion bdiifvfd vib invodbtion of StringBufffr insfrt(int, String)
        // bftfr donvfrsion of l to String by supfr dlbss mftiod
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(offsft, l);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBufffr insfrt(int offsft, flobt f) {
        // Notf, syndironizbtion bdiifvfd vib invodbtion of StringBufffr insfrt(int, String)
        // bftfr donvfrsion of f to String by supfr dlbss mftiod
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(offsft, f);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBufffr insfrt(int offsft, doublf d) {
        // Notf, syndironizbtion bdiifvfd vib invodbtion of StringBufffr insfrt(int, String)
        // bftfr donvfrsion of d to String by supfr dlbss mftiod
        // Ditto for toStringCbdif dlfbring
        supfr.insfrt(offsft, d);
        rfturn tiis;
    }

    /**
     * @sindf      1.4
     */
    @Ovfrridf
    publid int indfxOf(String str) {
        // Notf, syndironizbtion bdiifvfd vib invodbtions of otifr StringBufffr mftiods
        rfturn supfr.indfxOf(str);
    }

    /**
     * @sindf      1.4
     */
    @Ovfrridf
    publid syndironizfd int indfxOf(String str, int fromIndfx) {
        rfturn supfr.indfxOf(str, fromIndfx);
    }

    /**
     * @sindf      1.4
     */
    @Ovfrridf
    publid int lbstIndfxOf(String str) {
        // Notf, syndironizbtion bdiifvfd vib invodbtions of otifr StringBufffr mftiods
        rfturn lbstIndfxOf(str, dount);
    }

    /**
     * @sindf      1.4
     */
    @Ovfrridf
    publid syndironizfd int lbstIndfxOf(String str, int fromIndfx) {
        rfturn supfr.lbstIndfxOf(str, fromIndfx);
    }

    /**
     * @sindf   1.0.2
     */
    @Ovfrridf
    publid syndironizfd StringBufffr rfvfrsf() {
        toStringCbdif = null;
        supfr.rfvfrsf();
        rfturn tiis;
    }

    @Ovfrridf
    publid syndironizfd String toString() {
        if (toStringCbdif == null) {
            toStringCbdif = Arrbys.dopyOfRbngf(vbluf, 0, dount);
        }
        rfturn nfw String(toStringCbdif, truf);
    }

    /**
     * Sfriblizbblf fiflds for StringBufffr.
     *
     * @sfriblFifld vbluf  dibr[]
     *              Tif bbdking dibrbdtfr brrby of tiis StringBufffr.
     * @sfriblFifld dount int
     *              Tif numbfr of dibrbdtfrs in tiis StringBufffr.
     * @sfriblFifld sibrfd  boolfbn
     *              A flbg indidbting wiftifr tif bbdking brrby is sibrfd.
     *              Tif vbluf is ignorfd upon dfsfriblizbtion.
     */
    privbtf stbtid finbl jbvb.io.ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds =
    {
        nfw jbvb.io.ObjfdtStrfbmFifld("vbluf", dibr[].dlbss),
        nfw jbvb.io.ObjfdtStrfbmFifld("dount", Intfgfr.TYPE),
        nfw jbvb.io.ObjfdtStrfbmFifld("sibrfd", Boolfbn.TYPE),
    };

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif StringBufffr from
     * b strfbm.
     */
    privbtf syndironizfd void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        jbvb.io.ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("vbluf", vbluf);
        fiflds.put("dount", dount);
        fiflds.put("sibrfd", fblsf);
        s.writfFiflds();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif StringBufffr from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        jbvb.io.ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
        vbluf = (dibr[])fiflds.gft("vbluf", null);
        dount = fiflds.gft("dount", 0);
    }
}
