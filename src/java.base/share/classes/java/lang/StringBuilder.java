/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * A mutbblf sfqufndf of dibrbdtfrs.  Tiis dlbss providfs bn API dompbtiblf
 * witi {@dodf StringBufffr}, but witi no gubrbntff of syndironizbtion.
 * Tiis dlbss is dfsignfd for usf bs b drop-in rfplbdfmfnt for
 * {@dodf StringBufffr} in plbdfs wifrf tif string bufffr wbs bfing
 * usfd by b singlf tirfbd (bs is gfnfrblly tif dbsf).   Wifrf possiblf,
 * it is rfdommfndfd tibt tiis dlbss bf usfd in prfffrfndf to
 * {@dodf StringBufffr} bs it will bf fbstfr undfr most implfmfntbtions.
 *
 * <p>Tif prindipbl opfrbtions on b {@dodf StringBuildfr} brf tif
 * {@dodf bppfnd} bnd {@dodf insfrt} mftiods, wiidi brf
 * ovfrlobdfd so bs to bddfpt dbtb of bny typf. Ebdi ffffdtivfly
 * donvfrts b givfn dbtum to b string bnd tifn bppfnds or insfrts tif
 * dibrbdtfrs of tibt string to tif string buildfr. Tif
 * {@dodf bppfnd} mftiod blwbys bdds tifsf dibrbdtfrs bt tif fnd
 * of tif buildfr; tif {@dodf insfrt} mftiod bdds tif dibrbdtfrs bt
 * b spfdififd point.
 * <p>
 * For fxbmplf, if {@dodf z} rfffrs to b string buildfr objfdt
 * wiosf durrfnt dontfnts brf "{@dodf stbrt}", tifn
 * tif mftiod dbll {@dodf z.bppfnd("lf")} would dbusf tif string
 * buildfr to dontbin "{@dodf stbrtlf}", wifrfbs
 * {@dodf z.insfrt(4, "lf")} would bltfr tif string buildfr to
 * dontbin "{@dodf stbrlft}".
 * <p>
 * In gfnfrbl, if sb rfffrs to bn instbndf of b {@dodf StringBuildfr},
 * tifn {@dodf sb.bppfnd(x)} ibs tif sbmf ffffdt bs
 * {@dodf sb.insfrt(sb.lfngti(), x)}.
 * <p>
 * Evfry string buildfr ibs b dbpbdity. As long bs tif lfngti of tif
 * dibrbdtfr sfqufndf dontbinfd in tif string buildfr dofs not fxdffd
 * tif dbpbdity, it is not nfdfssbry to bllodbtf b nfw intfrnbl
 * bufffr. If tif intfrnbl bufffr ovfrflows, it is butombtidblly mbdf lbrgfr.
 *
 * <p>Instbndfs of {@dodf StringBuildfr} brf not sbff for
 * usf by multiplf tirfbds. If sudi syndironizbtion is rfquirfd tifn it is
 * rfdommfndfd tibt {@link jbvb.lbng.StringBufffr} bf usfd.
 *
 * <p>Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to b donstrudtor
 * or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf
 * tirown.
 *
 * @butior      Midibfl MdCloskfy
 * @sff         jbvb.lbng.StringBufffr
 * @sff         jbvb.lbng.String
 * @sindf       1.5
 */
publid finbl dlbss StringBuildfr
    fxtfnds AbstrbdtStringBuildfr
    implfmfnts jbvb.io.Sfriblizbblf, CibrSfqufndf
{

    /** usf sfriblVfrsionUID for intfropfrbbility */
    stbtid finbl long sfriblVfrsionUID = 4383685877147921099L;

    /**
     * Construdts b string buildfr witi no dibrbdtfrs in it bnd bn
     * initibl dbpbdity of 16 dibrbdtfrs.
     */
    publid StringBuildfr() {
        supfr(16);
    }

    /**
     * Construdts b string buildfr witi no dibrbdtfrs in it bnd bn
     * initibl dbpbdity spfdififd by tif {@dodf dbpbdity} brgumfnt.
     *
     * @pbrbm      dbpbdity  tif initibl dbpbdity.
     * @tirows     NfgbtivfArrbySizfExdfption  if tif {@dodf dbpbdity}
     *               brgumfnt is lfss tibn {@dodf 0}.
     */
    publid StringBuildfr(int dbpbdity) {
        supfr(dbpbdity);
    }

    /**
     * Construdts b string buildfr initiblizfd to tif dontfnts of tif
     * spfdififd string. Tif initibl dbpbdity of tif string buildfr is
     * {@dodf 16} plus tif lfngti of tif string brgumfnt.
     *
     * @pbrbm   str   tif initibl dontfnts of tif bufffr.
     */
    publid StringBuildfr(String str) {
        supfr(str.lfngti() + 16);
        bppfnd(str);
    }

    /**
     * Construdts b string buildfr tibt dontbins tif sbmf dibrbdtfrs
     * bs tif spfdififd {@dodf CibrSfqufndf}. Tif initibl dbpbdity of
     * tif string buildfr is {@dodf 16} plus tif lfngti of tif
     * {@dodf CibrSfqufndf} brgumfnt.
     *
     * @pbrbm      sfq   tif sfqufndf to dopy.
     */
    publid StringBuildfr(CibrSfqufndf sfq) {
        tiis(sfq.lfngti() + 16);
        bppfnd(sfq);
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(Objfdt obj) {
        rfturn bppfnd(String.vblufOf(obj));
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(String str) {
        supfr.bppfnd(str);
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd {@dodf StringBufffr} to tiis sfqufndf.
     * <p>
     * Tif dibrbdtfrs of tif {@dodf StringBufffr} brgumfnt brf bppfndfd,
     * in ordfr, to tiis sfqufndf, indrfbsing tif
     * lfngti of tiis sfqufndf by tif lfngti of tif brgumfnt.
     * If {@dodf sb} is {@dodf null}, tifn tif four dibrbdtfrs
     * {@dodf "null"} brf bppfndfd to tiis sfqufndf.
     * <p>
     * Lft <i>n</i> bf tif lfngti of tiis dibrbdtfr sfqufndf just prior to
     * fxfdution of tif {@dodf bppfnd} mftiod. Tifn tif dibrbdtfr bt indfx
     * <i>k</i> in tif nfw dibrbdtfr sfqufndf is fqubl to tif dibrbdtfr bt
     * indfx <i>k</i> in tif old dibrbdtfr sfqufndf, if <i>k</i> is lfss tibn
     * <i>n</i>; otifrwisf, it is fqubl to tif dibrbdtfr bt indfx <i>k-n</i>
     * in tif brgumfnt {@dodf sb}.
     *
     * @pbrbm   sb   tif {@dodf StringBufffr} to bppfnd.
     * @rfturn  b rfffrfndf to tiis objfdt.
     */
    publid StringBuildfr bppfnd(StringBufffr sb) {
        supfr.bppfnd(sb);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(CibrSfqufndf s) {
        supfr.bppfnd(s);
        rfturn tiis;
    }

    /**
     * @tirows     IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr bppfnd(CibrSfqufndf s, int stbrt, int fnd) {
        supfr.bppfnd(s, stbrt, fnd);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(dibr[] str) {
        supfr.bppfnd(str);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr bppfnd(dibr[] str, int offsft, int lfn) {
        supfr.bppfnd(str, offsft, lfn);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(boolfbn b) {
        supfr.bppfnd(b);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(dibr d) {
        supfr.bppfnd(d);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(int i) {
        supfr.bppfnd(i);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(long lng) {
        supfr.bppfnd(lng);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(flobt f) {
        supfr.bppfnd(f);
        rfturn tiis;
    }

    @Ovfrridf
    publid StringBuildfr bppfnd(doublf d) {
        supfr.bppfnd(d);
        rfturn tiis;
    }

    /**
     * @sindf 1.5
     */
    @Ovfrridf
    publid StringBuildfr bppfndCodfPoint(int dodfPoint) {
        supfr.bppfndCodfPoint(dodfPoint);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr dflftf(int stbrt, int fnd) {
        supfr.dflftf(stbrt, fnd);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr dflftfCibrAt(int indfx) {
        supfr.dflftfCibrAt(indfx);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr rfplbdf(int stbrt, int fnd, String str) {
        supfr.rfplbdf(stbrt, fnd, str);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int indfx, dibr[] str, int offsft,
                                int lfn)
    {
        supfr.insfrt(indfx, str, offsft, lfn);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, Objfdt obj) {
            supfr.insfrt(offsft, obj);
            rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, String str) {
        supfr.insfrt(offsft, str);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, dibr[] str) {
        supfr.insfrt(offsft, str);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int dstOffsft, CibrSfqufndf s) {
            supfr.insfrt(dstOffsft, s);
            rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int dstOffsft, CibrSfqufndf s,
                                int stbrt, int fnd)
    {
        supfr.insfrt(dstOffsft, s, stbrt, fnd);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, boolfbn b) {
        supfr.insfrt(offsft, b);
        rfturn tiis;
    }

    /**
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, dibr d) {
        supfr.insfrt(offsft, d);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, int i) {
        supfr.insfrt(offsft, i);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, long l) {
        supfr.insfrt(offsft, l);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, flobt f) {
        supfr.insfrt(offsft, f);
        rfturn tiis;
    }

    /**
     * @tirows StringIndfxOutOfBoundsExdfption {@inifritDod}
     */
    @Ovfrridf
    publid StringBuildfr insfrt(int offsft, doublf d) {
        supfr.insfrt(offsft, d);
        rfturn tiis;
    }

    @Ovfrridf
    publid int indfxOf(String str) {
        rfturn supfr.indfxOf(str);
    }

    @Ovfrridf
    publid int indfxOf(String str, int fromIndfx) {
        rfturn supfr.indfxOf(str, fromIndfx);
    }

    @Ovfrridf
    publid int lbstIndfxOf(String str) {
        rfturn supfr.lbstIndfxOf(str);
    }

    @Ovfrridf
    publid int lbstIndfxOf(String str, int fromIndfx) {
        rfturn supfr.lbstIndfxOf(str, fromIndfx);
    }

    @Ovfrridf
    publid StringBuildfr rfvfrsf() {
        supfr.rfvfrsf();
        rfturn tiis;
    }

    @Ovfrridf
    publid String toString() {
        // Crfbtf b dopy, don't sibrf tif brrby
        rfturn nfw String(vbluf, 0, dount);
    }

    /**
     * Sbvf tif stbtf of tif {@dodf StringBuildfr} instbndf to b strfbm
     * (tibt is, sfriblizf it).
     *
     * @sfriblDbtb tif numbfr of dibrbdtfrs durrfntly storfd in tif string
     *             buildfr ({@dodf int}), followfd by tif dibrbdtfrs in tif
     *             string buildfr ({@dodf dibr[]}).   Tif lfngti of tif
     *             {@dodf dibr} brrby mby bf grfbtfr tibn tif numbfr of
     *             dibrbdtfrs durrfntly storfd in tif string buildfr, in wiidi
     *             dbsf fxtrb dibrbdtfrs brf ignorfd.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt();
        s.writfInt(dount);
        s.writfObjfdt(vbluf);
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif StringBufffr from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        dount = s.rfbdInt();
        vbluf = (dibr[]) s.rfbdObjfdt();
    }

}
