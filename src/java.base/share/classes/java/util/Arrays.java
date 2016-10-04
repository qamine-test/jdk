/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.dondurrfnt.ForkJoinPool;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfBinbryOpfrbtor;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.IntToDoublfFundtion;
import jbvb.util.fundtion.IntToLongFundtion;
import jbvb.util.fundtion.IntUnbryOpfrbtor;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import jbvb.util.fundtion.UnbryOpfrbtor;
import jbvb.util.strfbm.DoublfStrfbm;
import jbvb.util.strfbm.IntStrfbm;
import jbvb.util.strfbm.LongStrfbm;
import jbvb.util.strfbm.Strfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * Tiis dlbss dontbins vbrious mftiods for mbnipulbting brrbys (sudi bs
 * sorting bnd sfbrdiing). Tiis dlbss blso dontbins b stbtid fbdtory
 * tibt bllows brrbys to bf vifwfd bs lists.
 *
 * <p>Tif mftiods in tiis dlbss bll tirow b {@dodf NullPointfrExdfption},
 * if tif spfdififd brrby rfffrfndf is null, fxdfpt wifrf notfd.
 *
 * <p>Tif dodumfntbtion for tif mftiods dontbinfd in tiis dlbss indludfs
 * briff dfsdriptions of tif <i>implfmfntbtions</i>. Sudi dfsdriptions siould
 * bf rfgbrdfd bs <i>implfmfntbtion notfs</i>, rbtifr tibn pbrts of tif
 * <i>spfdifidbtion</i>. Implfmfntors siould fffl frff to substitutf otifr
 * blgoritims, so long bs tif spfdifidbtion itsflf is bdifrfd to. (For
 * fxbmplf, tif blgoritim usfd by {@dodf sort(Objfdt[])} dofs not ibvf to bf
 * b MfrgfSort, but it dofs ibvf to bf <i>stbblf</i>.)
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior Josi Blodi
 * @butior Nfbl Gbftfr
 * @butior Join Rosf
 * @sindf  1.2
 */
publid dlbss Arrbys {

    /**
     * Tif minimum brrby lfngti bflow wiidi b pbrbllfl sorting
     * blgoritim will not furtifr pbrtition tif sorting tbsk. Using
     * smbllfr sizfs typidblly rfsults in mfmory dontfntion bdross
     * tbsks tibt mbkfs pbrbllfl spffdups unlikfly.
     */
    privbtf stbtid finbl int MIN_ARRAY_SORT_GRAN = 1 << 13;

    // Supprfssfs dffbult donstrudtor, fnsuring non-instbntibbility.
    privbtf Arrbys() {}

    /**
     * A dompbrbtor tibt implfmfnts tif nbturbl ordfring of b group of
     * mutublly dompbrbblf flfmfnts. Mby bf usfd wifn b supplifd
     * dompbrbtor is null. To simplify dodf-sibring witiin undfrlying
     * implfmfntbtions, tif dompbrf mftiod only dfdlbrfs typf Objfdt
     * for its sfdond brgumfnt.
     *
     * Arrbys dlbss implfmfntor's notf: It is bn fmpiridbl mbttfr
     * wiftifr CompbrbblfTimSort offfrs bny pfrformbndf bfnffit ovfr
     * TimSort usfd witi tiis dompbrbtor.  If not, you brf bfttfr off
     * dflfting or bypbssing CompbrbblfTimSort.  Tifrf is durrfntly no
     * fmpiridbl dbsf for sfpbrbting tifm for pbrbllfl sorting, so bll
     * publid Objfdt pbrbllflSort mftiods usf tif sbmf dompbrbtor
     * bbsfd implfmfntbtion.
     */
    stbtid finbl dlbss NbturblOrdfr implfmfnts Compbrbtor<Objfdt> {
        @SupprfssWbrnings("undifdkfd")
        publid int dompbrf(Objfdt first, Objfdt sfdond) {
            rfturn ((Compbrbblf<Objfdt>)first).dompbrfTo(sfdond);
        }
        stbtid finbl NbturblOrdfr INSTANCE = nfw NbturblOrdfr();
    }

    /**
     * Cifdks tibt {@dodf fromIndfx} bnd {@dodf toIndfx} brf in
     * tif rbngf bnd tirows bn fxdfption if tify brfn't.
     */
    privbtf stbtid void rbngfCifdk(int brrbyLfngti, int fromIndfx, int toIndfx) {
        if (fromIndfx > toIndfx) {
            tirow nfw IllfgblArgumfntExdfption(
                    "fromIndfx(" + fromIndfx + ") > toIndfx(" + toIndfx + ")");
        }
        if (fromIndfx < 0) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(fromIndfx);
        }
        if (toIndfx > brrbyLfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(toIndfx);
        }
    }

    /*
     * Sorting mftiods. Notf tibt bll publid "sort" mftiods tbkf tif
     * sbmf form: Pfrforming brgumfnt difdks if nfdfssbry, bnd tifn
     * fxpbnding brgumfnts into tiosf rfquirfd for tif intfrnbl
     * implfmfntbtion mftiods rfsiding in otifr pbdkbgf-privbtf
     * dlbssfs (fxdfpt for lfgbdyMfrgfSort, indludfd in tiis dlbss).
     */

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(int[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(int[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(long[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(long[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(siort[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(siort[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(dibr[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(dibr[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(bytf[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(bytf[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll flobt
     * vblufs: {@dodf -0.0f == 0.0f} is {@dodf truf} bnd b {@dodf Flobt.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Flobt#dompbrfTo}: {@dodf -0.0f} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0f} bnd {@dodf Flobt.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Flobt.NbN} vblufs brf donsidfrfd fqubl.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(flobt[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll flobt
     * vblufs: {@dodf -0.0f == 0.0f} is {@dodf truf} bnd b {@dodf Flobt.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Flobt#dompbrfTo}: {@dodf -0.0f} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0f} bnd {@dodf Flobt.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Flobt.NbN} vblufs brf donsidfrfd fqubl.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(flobt[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll doublf
     * vblufs: {@dodf -0.0d == 0.0d} is {@dodf truf} bnd b {@dodf Doublf.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Doublf#dompbrfTo}: {@dodf -0.0d} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0d} bnd {@dodf Doublf.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Doublf.NbN} vblufs brf donsidfrfd fqubl.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     */
    publid stbtid void sort(doublf[] b) {
        DublPivotQuidksort.sort(b, 0, b.lfngti - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding ordfr. Tif rbngf
     * to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx}, indlusivf, to
     * tif indfx {@dodf toIndfx}, fxdlusivf. If {@dodf fromIndfx == toIndfx},
     * tif rbngf to bf sortfd is fmpty.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll doublf
     * vblufs: {@dodf -0.0d == 0.0d} is {@dodf truf} bnd b {@dodf Doublf.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Doublf#dompbrfTo}: {@dodf -0.0d} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0d} bnd {@dodf Doublf.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Doublf.NbN} vblufs brf donsidfrfd fqubl.
     *
     * <p>Implfmfntbtion notf: Tif sorting blgoritim is b Dubl-Pivot Quidksort
     * by Vlbdimir Ybroslbvskiy, Jon Bfntlfy, bnd Josiub Blodi. Tiis blgoritim
     * offfrs O(n log(n)) pfrformbndf on mbny dbtb sfts tibt dbusf otifr
     * quidksorts to dfgrbdf to qubdrbtid pfrformbndf, bnd is typidblly
     * fbstfr tibn trbditionbl (onf-pivot) Quidksort implfmfntbtions.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     */
    publid stbtid void sort(doublf[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(bytf[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(bytf[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(bytf[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJBytf.Sortfr
                (null, b, nfw bytf[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(bytf[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(bytf[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(bytf[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJBytf.Sortfr
                (null, b, nfw bytf[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(dibr[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(dibr[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(dibr[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJCibr.Sortfr
                (null, b, nfw dibr[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
      @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(dibr[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(dibr[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(dibr[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJCibr.Sortfr
                (null, b, nfw dibr[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(siort[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(siort[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(siort[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJSiort.Sortfr
                (null, b, nfw siort[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(siort[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(siort[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(siort[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJSiort.Sortfr
                (null, b, nfw siort[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(int[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(int[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(int[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJInt.Sortfr
                (null, b, nfw int[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(int[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(int[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(int[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJInt.Sortfr
                (null, b, nfw int[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(long[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(long[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(long[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJLong.Sortfr
                (null, b, nfw long[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(long[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(long[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(long[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJLong.Sortfr
                (null, b, nfw long[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll flobt
     * vblufs: {@dodf -0.0f == 0.0f} is {@dodf truf} bnd b {@dodf Flobt.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Flobt#dompbrfTo}: {@dodf -0.0f} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0f} bnd {@dodf Flobt.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Flobt.NbN} vblufs brf donsidfrfd fqubl.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(flobt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(flobt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(flobt[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJFlobt.Sortfr
                (null, b, nfw flobt[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll flobt
     * vblufs: {@dodf -0.0f == 0.0f} is {@dodf truf} bnd b {@dodf Flobt.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Flobt#dompbrfTo}: {@dodf -0.0f} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0f} bnd {@dodf Flobt.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Flobt.NbN} vblufs brf donsidfrfd fqubl.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(flobt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(flobt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(flobt[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJFlobt.Sortfr
                (null, b, nfw flobt[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby into bsdfnding numfridbl ordfr.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll doublf
     * vblufs: {@dodf -0.0d == 0.0d} is {@dodf truf} bnd b {@dodf Doublf.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Doublf#dompbrfTo}: {@dodf -0.0d} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0d} bnd {@dodf Doublf.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Doublf.NbN} vblufs brf donsidfrfd fqubl.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(doublf[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(doublf[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(doublf[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, 0, n - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJDoublf.Sortfr
                (null, b, nfw doublf[n], 0, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif brrby into bsdfnding numfridbl ordfr.
     * Tif rbngf to bf sortfd fxtfnds from tif indfx {@dodf fromIndfx},
     * indlusivf, to tif indfx {@dodf toIndfx}, fxdlusivf. If
     * {@dodf fromIndfx == toIndfx}, tif rbngf to bf sortfd is fmpty.
     *
     * <p>Tif {@dodf <} rflbtion dofs not providf b totbl ordfr on bll doublf
     * vblufs: {@dodf -0.0d == 0.0d} is {@dodf truf} bnd b {@dodf Doublf.NbN}
     * vbluf dompbrfs nfitifr lfss tibn, grfbtfr tibn, nor fqubl to bny vbluf,
     * fvfn itsflf. Tiis mftiod usfs tif totbl ordfr imposfd by tif mftiod
     * {@link Doublf#dompbrfTo}: {@dodf -0.0d} is trfbtfd bs lfss tibn vbluf
     * {@dodf 0.0d} bnd {@dodf Doublf.NbN} is donsidfrfd grfbtfr tibn bny
     * otifr vbluf bnd bll {@dodf Doublf.NbN} vblufs brf donsidfrfd fqubl.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(doublf[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(doublf[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > b.lfngti}
     *
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSort(doublf[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            DublPivotQuidksort.sort(b, fromIndfx, toIndfx - 1, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJDoublf.Sortfr
                (null, b, nfw doublf[n], fromIndfx, n, 0,
                 ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g).invokf();
    }

    /**
     * Sorts tif spfdififd brrby of objfdts into bsdfnding ordfr, bddording
     * to tif {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     * All flfmfnts in tif brrby must implfmfnt tif {@link Compbrbblf}
     * intfrfbdf.  Furtifrmorf, bll flfmfnts in tif brrby must bf
     * <i>mutublly dompbrbblf</i> (tibt is, {@dodf f1.dompbrfTo(f2)} must
     * not tirow b {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1}
     * bnd {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(Objfdt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(Objfdt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     *
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd intfgfrs)
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif nbturbl
     *         ordfring of tif brrby flfmfnts is found to violbtf tif
     *         {@link Compbrbblf} dontrbdt
     *
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T fxtfnds Compbrbblf<? supfr T>> void pbrbllflSort(T[] b) {
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            TimSort.sort(b, 0, n, NbturblOrdfr.INSTANCE, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJObjfdt.Sortfr<>
                (null, b,
                 (T[])Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), n),
                 0, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g, NbturblOrdfr.INSTANCE).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif spfdififd brrby of objfdts into
     * bsdfnding ordfr, bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of its
     * flfmfnts.  Tif rbngf to bf sortfd fxtfnds from indfx
     * {@dodf fromIndfx}, indlusivf, to indfx {@dodf toIndfx}, fxdlusivf.
     * (If {@dodf fromIndfx==toIndfx}, tif rbngf to bf sortfd is fmpty.)  All
     * flfmfnts in tiis rbngf must implfmfnt tif {@link Compbrbblf}
     * intfrfbdf.  Furtifrmorf, bll flfmfnts in tiis rbngf must bf <i>mutublly
     * dompbrbblf</i> (tibt is, {@dodf f1.dompbrfTo(f2)} must not tirow b
     * {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1} bnd
     * {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(Objfdt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(Objfdt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sortfd
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx} or
     *         (optionbl) if tif nbturbl ordfring of tif brrby flfmfnts is
     *         found to violbtf tif {@link Compbrbblf} dontrbdt
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf fromIndfx < 0} or
     *         {@dodf toIndfx > b.lfngti}
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs).
     *
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T fxtfnds Compbrbblf<? supfr T>>
    void pbrbllflSort(T[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            TimSort.sort(b, fromIndfx, toIndfx, NbturblOrdfr.INSTANCE, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJObjfdt.Sortfr<>
                (null, b,
                 (T[])Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), n),
                 fromIndfx, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g, NbturblOrdfr.INSTANCE).invokf();
    }

    /**
     * Sorts tif spfdififd brrby of objfdts bddording to tif ordfr indudfd by
     * tif spfdififd dompbrbtor.  All flfmfnts in tif brrby must bf
     * <i>mutublly dompbrbblf</i> by tif spfdififd dompbrbtor (tibt is,
     * {@dodf d.dompbrf(f1, f2)} must not tirow b {@dodf ClbssCbstExdfption}
     * for bny flfmfnts {@dodf f1} bnd {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(Objfdt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(Objfdt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b
     * working spbdf no grfbtfr tibn tif sizf of tif originbl brrby. Tif
     * {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is usfd to
     * fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm dmp tif dompbrbtor to dftfrminf tif ordfr of tif brrby.  A
     *        {@dodf null} vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif dompbrbtor is
     *         found to violbtf tif {@link jbvb.util.Compbrbtor} dontrbdt
     *
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> void pbrbllflSort(T[] b, Compbrbtor<? supfr T> dmp) {
        if (dmp == null)
            dmp = NbturblOrdfr.INSTANCE;
        int n = b.lfngti, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            TimSort.sort(b, 0, n, dmp, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJObjfdt.Sortfr<>
                (null, b,
                 (T[])Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), n),
                 0, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g, dmp).invokf();
    }

    /**
     * Sorts tif spfdififd rbngf of tif spfdififd brrby of objfdts bddording
     * to tif ordfr indudfd by tif spfdififd dompbrbtor.  Tif rbngf to bf
     * sortfd fxtfnds from indfx {@dodf fromIndfx}, indlusivf, to indfx
     * {@dodf toIndfx}, fxdlusivf.  (If {@dodf fromIndfx==toIndfx}, tif
     * rbngf to bf sortfd is fmpty.)  All flfmfnts in tif rbngf must bf
     * <i>mutublly dompbrbblf</i> by tif spfdififd dompbrbtor (tibt is,
     * {@dodf d.dompbrf(f1, f2)} must not tirow b {@dodf ClbssCbstExdfption}
     * for bny flfmfnts {@dodf f1} bnd {@dodf f2} in tif rbngf).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * @implNotf Tif sorting blgoritim is b pbrbllfl sort-mfrgf tibt brfbks tif
     * brrby into sub-brrbys tibt brf tifmsflvfs sortfd bnd tifn mfrgfd. Wifn
     * tif sub-brrby lfngti rfbdifs b minimum grbnulbrity, tif sub-brrby is
     * sortfd using tif bppropribtf {@link Arrbys#sort(Objfdt[]) Arrbys.sort}
     * mftiod. If tif lfngti of tif spfdififd brrby is lfss tibn tif minimum
     * grbnulbrity, tifn it is sortfd using tif bppropribtf {@link
     * Arrbys#sort(Objfdt[]) Arrbys.sort} mftiod. Tif blgoritim rfquirfs b working
     * spbdf no grfbtfr tibn tif sizf of tif spfdififd rbngf of tif originbl
     * brrby. Tif {@link ForkJoinPool#dommonPool() ForkJoin dommon pool} is
     * usfd to fxfdutf bny pbrbllfl tbsks.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sortfd
     * @pbrbm dmp tif dompbrbtor to dftfrminf tif ordfr of tif brrby.  A
     *        {@dodf null} vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx} or
     *         (optionbl) if tif nbturbl ordfring of tif brrby flfmfnts is
     *         found to violbtf tif {@link Compbrbblf} dontrbdt
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf fromIndfx < 0} or
     *         {@dodf toIndfx > b.lfngti}
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs).
     *
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> void pbrbllflSort(T[] b, int fromIndfx, int toIndfx,
                                        Compbrbtor<? supfr T> dmp) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        if (dmp == null)
            dmp = NbturblOrdfr.INSTANCE;
        int n = toIndfx - fromIndfx, p, g;
        if (n <= MIN_ARRAY_SORT_GRAN ||
            (p = ForkJoinPool.gftCommonPoolPbrbllflism()) == 1)
            TimSort.sort(b, fromIndfx, toIndfx, dmp, null, 0, 0);
        flsf
            nfw ArrbysPbrbllflSortHflpfrs.FJObjfdt.Sortfr<>
                (null, b,
                 (T[])Arrby.nfwInstbndf(b.gftClbss().gftComponfntTypf(), n),
                 fromIndfx, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
                 MIN_ARRAY_SORT_GRAN : g, dmp).invokf();
    }

    /*
     * Sorting of domplfx typf brrbys.
     */

    /**
     * Old mfrgf sort implfmfntbtion dbn bf sflfdtfd (for
     * dompbtibility witi brokfn dompbrbtors) using b systfm propfrty.
     * Cbnnot bf b stbtid boolfbn in tif fndlosing dlbss duf to
     * dirdulbr dfpfndfndifs. To bf rfmovfd in b futurf rflfbsf.
     */
    stbtid finbl dlbss LfgbdyMfrgfSort {
        privbtf stbtid finbl boolfbn usfrRfqufstfd =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion(
                    "jbvb.util.Arrbys.usfLfgbdyMfrgfSort")).boolfbnVbluf();
    }

    /**
     * Sorts tif spfdififd brrby of objfdts into bsdfnding ordfr, bddording
     * to tif {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     * All flfmfnts in tif brrby must implfmfnt tif {@link Compbrbblf}
     * intfrfbdf.  Furtifrmorf, bll flfmfnts in tif brrby must bf
     * <i>mutublly dompbrbblf</i> (tibt is, {@dodf f1.dompbrfTo(f2)} must
     * not tirow b {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1}
     * bnd {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Implfmfntbtion notf: Tiis implfmfntbtion is b stbblf, bdbptivf,
     * itfrbtivf mfrgfsort tibt rfquirfs fbr ffwfr tibn n lg(n) dompbrisons
     * wifn tif input brrby is pbrtiblly sortfd, wiilf offfring tif
     * pfrformbndf of b trbditionbl mfrgfsort wifn tif input brrby is
     * rbndomly ordfrfd.  If tif input brrby is nfbrly sortfd, tif
     * implfmfntbtion rfquirfs bpproximbtfly n dompbrisons.  Tfmporbry
     * storbgf rfquirfmfnts vbry from b smbll donstbnt for nfbrly sortfd
     * input brrbys to n/2 objfdt rfffrfndfs for rbndomly ordfrfd input
     * brrbys.
     *
     * <p>Tif implfmfntbtion tbkfs fqubl bdvbntbgf of bsdfnding bnd
     * dfsdfnding ordfr in its input brrby, bnd dbn tbkf bdvbntbgf of
     * bsdfnding bnd dfsdfnding ordfr in difffrfnt pbrts of tif tif sbmf
     * input brrby.  It is wfll-suitfd to mfrging two or morf sortfd brrbys:
     * simply dondbtfnbtf tif brrbys bnd sort tif rfsulting brrby.
     *
     * <p>Tif implfmfntbtion wbs bdbptfd from Tim Pftfrs's list sort for Pytion
     * (<b irff="ittp://svn.pytion.org/projfdts/pytion/trunk/Objfdts/listsort.txt">
     * TimSort</b>).  It usfs tfdiniqufs from Pftfr MdIlroy's "Optimistid
     * Sorting bnd Informbtion Tiforftid Complfxity", in Prodffdings of tif
     * Fourti Annubl ACM-SIAM Symposium on Disdrftf Algoritims, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd intfgfrs)
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif nbturbl
     *         ordfring of tif brrby flfmfnts is found to violbtf tif
     *         {@link Compbrbblf} dontrbdt
     */
    publid stbtid void sort(Objfdt[] b) {
        if (LfgbdyMfrgfSort.usfrRfqufstfd)
            lfgbdyMfrgfSort(b);
        flsf
            CompbrbblfTimSort.sort(b, 0, b.lfngti, null, 0, 0);
    }

    /** To bf rfmovfd in b futurf rflfbsf. */
    privbtf stbtid void lfgbdyMfrgfSort(Objfdt[] b) {
        Objfdt[] bux = b.dlonf();
        mfrgfSort(bux, b, 0, b.lfngti, 0);
    }

    /**
     * Sorts tif spfdififd rbngf of tif spfdififd brrby of objfdts into
     * bsdfnding ordfr, bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of its
     * flfmfnts.  Tif rbngf to bf sortfd fxtfnds from indfx
     * {@dodf fromIndfx}, indlusivf, to indfx {@dodf toIndfx}, fxdlusivf.
     * (If {@dodf fromIndfx==toIndfx}, tif rbngf to bf sortfd is fmpty.)  All
     * flfmfnts in tiis rbngf must implfmfnt tif {@link Compbrbblf}
     * intfrfbdf.  Furtifrmorf, bll flfmfnts in tiis rbngf must bf <i>mutublly
     * dompbrbblf</i> (tibt is, {@dodf f1.dompbrfTo(f2)} must not tirow b
     * {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1} bnd
     * {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Implfmfntbtion notf: Tiis implfmfntbtion is b stbblf, bdbptivf,
     * itfrbtivf mfrgfsort tibt rfquirfs fbr ffwfr tibn n lg(n) dompbrisons
     * wifn tif input brrby is pbrtiblly sortfd, wiilf offfring tif
     * pfrformbndf of b trbditionbl mfrgfsort wifn tif input brrby is
     * rbndomly ordfrfd.  If tif input brrby is nfbrly sortfd, tif
     * implfmfntbtion rfquirfs bpproximbtfly n dompbrisons.  Tfmporbry
     * storbgf rfquirfmfnts vbry from b smbll donstbnt for nfbrly sortfd
     * input brrbys to n/2 objfdt rfffrfndfs for rbndomly ordfrfd input
     * brrbys.
     *
     * <p>Tif implfmfntbtion tbkfs fqubl bdvbntbgf of bsdfnding bnd
     * dfsdfnding ordfr in its input brrby, bnd dbn tbkf bdvbntbgf of
     * bsdfnding bnd dfsdfnding ordfr in difffrfnt pbrts of tif tif sbmf
     * input brrby.  It is wfll-suitfd to mfrging two or morf sortfd brrbys:
     * simply dondbtfnbtf tif brrbys bnd sort tif rfsulting brrby.
     *
     * <p>Tif implfmfntbtion wbs bdbptfd from Tim Pftfrs's list sort for Pytion
     * (<b irff="ittp://svn.pytion.org/projfdts/pytion/trunk/Objfdts/listsort.txt">
     * TimSort</b>).  It usfs tfdiniqufs from Pftfr MdIlroy's "Optimistid
     * Sorting bnd Informbtion Tiforftid Complfxity", in Prodffdings of tif
     * Fourti Annubl ACM-SIAM Symposium on Disdrftf Algoritims, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sortfd
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx} or
     *         (optionbl) if tif nbturbl ordfring of tif brrby flfmfnts is
     *         found to violbtf tif {@link Compbrbblf} dontrbdt
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf fromIndfx < 0} or
     *         {@dodf toIndfx > b.lfngti}
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> (for fxbmplf, strings bnd
     *         intfgfrs).
     */
    publid stbtid void sort(Objfdt[] b, int fromIndfx, int toIndfx) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        if (LfgbdyMfrgfSort.usfrRfqufstfd)
            lfgbdyMfrgfSort(b, fromIndfx, toIndfx);
        flsf
            CompbrbblfTimSort.sort(b, fromIndfx, toIndfx, null, 0, 0);
    }

    /** To bf rfmovfd in b futurf rflfbsf. */
    privbtf stbtid void lfgbdyMfrgfSort(Objfdt[] b,
                                        int fromIndfx, int toIndfx) {
        Objfdt[] bux = dopyOfRbngf(b, fromIndfx, toIndfx);
        mfrgfSort(bux, b, fromIndfx, toIndfx, -fromIndfx);
    }

    /**
     * Tuning pbrbmftfr: list sizf bt or bflow wiidi insfrtion sort will bf
     * usfd in prfffrfndf to mfrgfsort.
     * To bf rfmovfd in b futurf rflfbsf.
     */
    privbtf stbtid finbl int INSERTIONSORT_THRESHOLD = 7;

    /**
     * Srd is tif sourdf brrby tibt stbrts bt indfx 0
     * Dfst is tif (possibly lbrgfr) brrby dfstinbtion witi b possiblf offsft
     * low is tif indfx in dfst to stbrt sorting
     * iigi is tif fnd indfx in dfst to fnd sorting
     * off is tif offsft to gfnfrbtf dorrfsponding low, iigi in srd
     * To bf rfmovfd in b futurf rflfbsf.
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf stbtid void mfrgfSort(Objfdt[] srd,
                                  Objfdt[] dfst,
                                  int low,
                                  int iigi,
                                  int off) {
        int lfngti = iigi - low;

        // Insfrtion sort on smbllfst brrbys
        if (lfngti < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<iigi; i++)
                for (int j=i; j>low &&
                         ((Compbrbblf) dfst[j-1]).dompbrfTo(dfst[j])>0; j--)
                    swbp(dfst, j, j-1);
            rfturn;
        }

        // Rfdursivfly sort iblvfs of dfst into srd
        int dfstLow  = low;
        int dfstHigi = iigi;
        low  += off;
        iigi += off;
        int mid = (low + iigi) >>> 1;
        mfrgfSort(dfst, srd, low, mid, -off);
        mfrgfSort(dfst, srd, mid, iigi, -off);

        // If list is blrfbdy sortfd, just dopy from srd to dfst.  Tiis is bn
        // optimizbtion tibt rfsults in fbstfr sorts for nfbrly ordfrfd lists.
        if (((Compbrbblf)srd[mid-1]).dompbrfTo(srd[mid]) <= 0) {
            Systfm.brrbydopy(srd, low, dfst, dfstLow, lfngti);
            rfturn;
        }

        // Mfrgf sortfd iblvfs (now in srd) into dfst
        for(int i = dfstLow, p = low, q = mid; i < dfstHigi; i++) {
            if (q >= iigi || p < mid && ((Compbrbblf)srd[p]).dompbrfTo(srd[q])<=0)
                dfst[i] = srd[p++];
            flsf
                dfst[i] = srd[q++];
        }
    }

    /**
     * Swbps x[b] witi x[b].
     */
    privbtf stbtid void swbp(Objfdt[] x, int b, int b) {
        Objfdt t = x[b];
        x[b] = x[b];
        x[b] = t;
    }

    /**
     * Sorts tif spfdififd brrby of objfdts bddording to tif ordfr indudfd by
     * tif spfdififd dompbrbtor.  All flfmfnts in tif brrby must bf
     * <i>mutublly dompbrbblf</i> by tif spfdififd dompbrbtor (tibt is,
     * {@dodf d.dompbrf(f1, f2)} must not tirow b {@dodf ClbssCbstExdfption}
     * for bny flfmfnts {@dodf f1} bnd {@dodf f2} in tif brrby).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Implfmfntbtion notf: Tiis implfmfntbtion is b stbblf, bdbptivf,
     * itfrbtivf mfrgfsort tibt rfquirfs fbr ffwfr tibn n lg(n) dompbrisons
     * wifn tif input brrby is pbrtiblly sortfd, wiilf offfring tif
     * pfrformbndf of b trbditionbl mfrgfsort wifn tif input brrby is
     * rbndomly ordfrfd.  If tif input brrby is nfbrly sortfd, tif
     * implfmfntbtion rfquirfs bpproximbtfly n dompbrisons.  Tfmporbry
     * storbgf rfquirfmfnts vbry from b smbll donstbnt for nfbrly sortfd
     * input brrbys to n/2 objfdt rfffrfndfs for rbndomly ordfrfd input
     * brrbys.
     *
     * <p>Tif implfmfntbtion tbkfs fqubl bdvbntbgf of bsdfnding bnd
     * dfsdfnding ordfr in its input brrby, bnd dbn tbkf bdvbntbgf of
     * bsdfnding bnd dfsdfnding ordfr in difffrfnt pbrts of tif tif sbmf
     * input brrby.  It is wfll-suitfd to mfrging two or morf sortfd brrbys:
     * simply dondbtfnbtf tif brrbys bnd sort tif rfsulting brrby.
     *
     * <p>Tif implfmfntbtion wbs bdbptfd from Tim Pftfrs's list sort for Pytion
     * (<b irff="ittp://svn.pytion.org/projfdts/pytion/trunk/Objfdts/listsort.txt">
     * TimSort</b>).  It usfs tfdiniqufs from Pftfr MdIlroy's "Optimistid
     * Sorting bnd Informbtion Tiforftid Complfxity", in Prodffdings of tif
     * Fourti Annubl ACM-SIAM Symposium on Disdrftf Algoritims, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm d tif dompbrbtor to dftfrminf tif ordfr of tif brrby.  A
     *        {@dodf null} vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf
     *         not <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor
     * @tirows IllfgblArgumfntExdfption (optionbl) if tif dompbrbtor is
     *         found to violbtf tif {@link Compbrbtor} dontrbdt
     */
    publid stbtid <T> void sort(T[] b, Compbrbtor<? supfr T> d) {
        if (d == null) {
            sort(b);
        } flsf {
            if (LfgbdyMfrgfSort.usfrRfqufstfd)
                lfgbdyMfrgfSort(b, d);
            flsf
                TimSort.sort(b, 0, b.lfngti, d, null, 0, 0);
        }
    }

    /** To bf rfmovfd in b futurf rflfbsf. */
    privbtf stbtid <T> void lfgbdyMfrgfSort(T[] b, Compbrbtor<? supfr T> d) {
        T[] bux = b.dlonf();
        if (d==null)
            mfrgfSort(bux, b, 0, b.lfngti, 0);
        flsf
            mfrgfSort(bux, b, 0, b.lfngti, 0, d);
    }

    /**
     * Sorts tif spfdififd rbngf of tif spfdififd brrby of objfdts bddording
     * to tif ordfr indudfd by tif spfdififd dompbrbtor.  Tif rbngf to bf
     * sortfd fxtfnds from indfx {@dodf fromIndfx}, indlusivf, to indfx
     * {@dodf toIndfx}, fxdlusivf.  (If {@dodf fromIndfx==toIndfx}, tif
     * rbngf to bf sortfd is fmpty.)  All flfmfnts in tif rbngf must bf
     * <i>mutublly dompbrbblf</i> by tif spfdififd dompbrbtor (tibt is,
     * {@dodf d.dompbrf(f1, f2)} must not tirow b {@dodf ClbssCbstExdfption}
     * for bny flfmfnts {@dodf f1} bnd {@dodf f2} in tif rbngf).
     *
     * <p>Tiis sort is gubrbntffd to bf <i>stbblf</i>:  fqubl flfmfnts will
     * not bf rfordfrfd bs b rfsult of tif sort.
     *
     * <p>Implfmfntbtion notf: Tiis implfmfntbtion is b stbblf, bdbptivf,
     * itfrbtivf mfrgfsort tibt rfquirfs fbr ffwfr tibn n lg(n) dompbrisons
     * wifn tif input brrby is pbrtiblly sortfd, wiilf offfring tif
     * pfrformbndf of b trbditionbl mfrgfsort wifn tif input brrby is
     * rbndomly ordfrfd.  If tif input brrby is nfbrly sortfd, tif
     * implfmfntbtion rfquirfs bpproximbtfly n dompbrisons.  Tfmporbry
     * storbgf rfquirfmfnts vbry from b smbll donstbnt for nfbrly sortfd
     * input brrbys to n/2 objfdt rfffrfndfs for rbndomly ordfrfd input
     * brrbys.
     *
     * <p>Tif implfmfntbtion tbkfs fqubl bdvbntbgf of bsdfnding bnd
     * dfsdfnding ordfr in its input brrby, bnd dbn tbkf bdvbntbgf of
     * bsdfnding bnd dfsdfnding ordfr in difffrfnt pbrts of tif tif sbmf
     * input brrby.  It is wfll-suitfd to mfrging two or morf sortfd brrbys:
     * simply dondbtfnbtf tif brrbys bnd sort tif rfsulting brrby.
     *
     * <p>Tif implfmfntbtion wbs bdbptfd from Tim Pftfrs's list sort for Pytion
     * (<b irff="ittp://svn.pytion.org/projfdts/pytion/trunk/Objfdts/listsort.txt">
     * TimSort</b>).  It usfs tfdiniqufs from Pftfr MdIlroy's "Optimistid
     * Sorting bnd Informbtion Tiforftid Complfxity", in Prodffdings of tif
     * Fourti Annubl ACM-SIAM Symposium on Disdrftf Algoritims, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm <T> tif dlbss of tif objfdts to bf sortfd
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        sortfd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sortfd
     * @pbrbm d tif dompbrbtor to dftfrminf tif ordfr of tif brrby.  A
     *        {@dodf null} vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor.
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx} or
     *         (optionbl) if tif dompbrbtor is found to violbtf tif
     *         {@link Compbrbtor} dontrbdt
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf fromIndfx < 0} or
     *         {@dodf toIndfx > b.lfngti}
     */
    publid stbtid <T> void sort(T[] b, int fromIndfx, int toIndfx,
                                Compbrbtor<? supfr T> d) {
        if (d == null) {
            sort(b, fromIndfx, toIndfx);
        } flsf {
            rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
            if (LfgbdyMfrgfSort.usfrRfqufstfd)
                lfgbdyMfrgfSort(b, fromIndfx, toIndfx, d);
            flsf
                TimSort.sort(b, fromIndfx, toIndfx, d, null, 0, 0);
        }
    }

    /** To bf rfmovfd in b futurf rflfbsf. */
    privbtf stbtid <T> void lfgbdyMfrgfSort(T[] b, int fromIndfx, int toIndfx,
                                            Compbrbtor<? supfr T> d) {
        T[] bux = dopyOfRbngf(b, fromIndfx, toIndfx);
        if (d==null)
            mfrgfSort(bux, b, fromIndfx, toIndfx, -fromIndfx);
        flsf
            mfrgfSort(bux, b, fromIndfx, toIndfx, -fromIndfx, d);
    }

    /**
     * Srd is tif sourdf brrby tibt stbrts bt indfx 0
     * Dfst is tif (possibly lbrgfr) brrby dfstinbtion witi b possiblf offsft
     * low is tif indfx in dfst to stbrt sorting
     * iigi is tif fnd indfx in dfst to fnd sorting
     * off is tif offsft into srd dorrfsponding to low in dfst
     * To bf rfmovfd in b futurf rflfbsf.
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    privbtf stbtid void mfrgfSort(Objfdt[] srd,
                                  Objfdt[] dfst,
                                  int low, int iigi, int off,
                                  Compbrbtor d) {
        int lfngti = iigi - low;

        // Insfrtion sort on smbllfst brrbys
        if (lfngti < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<iigi; i++)
                for (int j=i; j>low && d.dompbrf(dfst[j-1], dfst[j])>0; j--)
                    swbp(dfst, j, j-1);
            rfturn;
        }

        // Rfdursivfly sort iblvfs of dfst into srd
        int dfstLow  = low;
        int dfstHigi = iigi;
        low  += off;
        iigi += off;
        int mid = (low + iigi) >>> 1;
        mfrgfSort(dfst, srd, low, mid, -off, d);
        mfrgfSort(dfst, srd, mid, iigi, -off, d);

        // If list is blrfbdy sortfd, just dopy from srd to dfst.  Tiis is bn
        // optimizbtion tibt rfsults in fbstfr sorts for nfbrly ordfrfd lists.
        if (d.dompbrf(srd[mid-1], srd[mid]) <= 0) {
           Systfm.brrbydopy(srd, low, dfst, dfstLow, lfngti);
           rfturn;
        }

        // Mfrgf sortfd iblvfs (now in srd) into dfst
        for(int i = dfstLow, p = low, q = mid; i < dfstHigi; i++) {
            if (q >= iigi || p < mid && d.dompbrf(srd[p], srd[q]) <= 0)
                dfst[i] = srd[p++];
            flsf
                dfst[i] = srd[q++];
        }
    }

    // Pbrbllfl prffix

    /**
     * Cumulbtfs, in pbrbllfl, fbdi flfmfnt of tif givfn brrby in plbdf,
     * using tif supplifd fundtion. For fxbmplf if tif brrby initiblly
     * iolds {@dodf [2, 1, 0, 3]} bnd tif opfrbtion pfrforms bddition,
     * tifn upon rfturn tif brrby iolds {@dodf [2, 3, 3, 6]}.
     * Pbrbllfl prffix domputbtion is usublly morf fffidifnt tibn
     * sfqufntibl loops for lbrgf brrbys.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm brrby tif brrby, wiidi is modififd in-plbdf by tiis mftiod
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid <T> void pbrbllflPrffix(T[] brrby, BinbryOpfrbtor<T> op) {
        Objfdts.rfquirfNonNull(op);
        if (brrby.lfngti > 0)
            nfw ArrbyPrffixHflpfrs.CumulbtfTbsk<>
                    (null, op, brrby, 0, brrby.lfngti).invokf();
    }

    /**
     * Pfrforms {@link #pbrbllflPrffix(Objfdt[], BinbryOpfrbtor)}
     * for tif givfn subrbngf of tif brrby.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm brrby tif brrby
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > brrby.lfngti}
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid <T> void pbrbllflPrffix(T[] brrby, int fromIndfx,
                                          int toIndfx, BinbryOpfrbtor<T> op) {
        Objfdts.rfquirfNonNull(op);
        rbngfCifdk(brrby.lfngti, fromIndfx, toIndfx);
        if (fromIndfx < toIndfx)
            nfw ArrbyPrffixHflpfrs.CumulbtfTbsk<>
                    (null, op, brrby, fromIndfx, toIndfx).invokf();
    }

    /**
     * Cumulbtfs, in pbrbllfl, fbdi flfmfnt of tif givfn brrby in plbdf,
     * using tif supplifd fundtion. For fxbmplf if tif brrby initiblly
     * iolds {@dodf [2, 1, 0, 3]} bnd tif opfrbtion pfrforms bddition,
     * tifn upon rfturn tif brrby iolds {@dodf [2, 3, 3, 6]}.
     * Pbrbllfl prffix domputbtion is usublly morf fffidifnt tibn
     * sfqufntibl loops for lbrgf brrbys.
     *
     * @pbrbm brrby tif brrby, wiidi is modififd in-plbdf by tiis mftiod
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(long[] brrby, LongBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        if (brrby.lfngti > 0)
            nfw ArrbyPrffixHflpfrs.LongCumulbtfTbsk
                    (null, op, brrby, 0, brrby.lfngti).invokf();
    }

    /**
     * Pfrforms {@link #pbrbllflPrffix(long[], LongBinbryOpfrbtor)}
     * for tif givfn subrbngf of tif brrby.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > brrby.lfngti}
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(long[] brrby, int fromIndfx,
                                      int toIndfx, LongBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        rbngfCifdk(brrby.lfngti, fromIndfx, toIndfx);
        if (fromIndfx < toIndfx)
            nfw ArrbyPrffixHflpfrs.LongCumulbtfTbsk
                    (null, op, brrby, fromIndfx, toIndfx).invokf();
    }

    /**
     * Cumulbtfs, in pbrbllfl, fbdi flfmfnt of tif givfn brrby in plbdf,
     * using tif supplifd fundtion. For fxbmplf if tif brrby initiblly
     * iolds {@dodf [2.0, 1.0, 0.0, 3.0]} bnd tif opfrbtion pfrforms bddition,
     * tifn upon rfturn tif brrby iolds {@dodf [2.0, 3.0, 3.0, 6.0]}.
     * Pbrbllfl prffix domputbtion is usublly morf fffidifnt tibn
     * sfqufntibl loops for lbrgf brrbys.
     *
     * <p> Bfdbusf flobting-point opfrbtions mby not bf stridtly bssodibtivf,
     * tif rfturnfd rfsult mby not bf idfntidbl to tif vbluf tibt would bf
     * obtbinfd if tif opfrbtion wbs pfrformfd sfqufntiblly.
     *
     * @pbrbm brrby tif brrby, wiidi is modififd in-plbdf by tiis mftiod
     * @pbrbm op b sidf-ffffdt-frff fundtion to pfrform tif dumulbtion
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(doublf[] brrby, DoublfBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        if (brrby.lfngti > 0)
            nfw ArrbyPrffixHflpfrs.DoublfCumulbtfTbsk
                    (null, op, brrby, 0, brrby.lfngti).invokf();
    }

    /**
     * Pfrforms {@link #pbrbllflPrffix(doublf[], DoublfBinbryOpfrbtor)}
     * for tif givfn subrbngf of tif brrby.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > brrby.lfngti}
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(doublf[] brrby, int fromIndfx,
                                      int toIndfx, DoublfBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        rbngfCifdk(brrby.lfngti, fromIndfx, toIndfx);
        if (fromIndfx < toIndfx)
            nfw ArrbyPrffixHflpfrs.DoublfCumulbtfTbsk
                    (null, op, brrby, fromIndfx, toIndfx).invokf();
    }

    /**
     * Cumulbtfs, in pbrbllfl, fbdi flfmfnt of tif givfn brrby in plbdf,
     * using tif supplifd fundtion. For fxbmplf if tif brrby initiblly
     * iolds {@dodf [2, 1, 0, 3]} bnd tif opfrbtion pfrforms bddition,
     * tifn upon rfturn tif brrby iolds {@dodf [2, 3, 3, 6]}.
     * Pbrbllfl prffix domputbtion is usublly morf fffidifnt tibn
     * sfqufntibl loops for lbrgf brrbys.
     *
     * @pbrbm brrby tif brrby, wiidi is modififd in-plbdf by tiis mftiod
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(int[] brrby, IntBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        if (brrby.lfngti > 0)
            nfw ArrbyPrffixHflpfrs.IntCumulbtfTbsk
                    (null, op, brrby, 0, brrby.lfngti).invokf();
    }

    /**
     * Pfrforms {@link #pbrbllflPrffix(int[], IntBinbryOpfrbtor)}
     * for tif givfn subrbngf of tif brrby.
     *
     * @pbrbm brrby tif brrby
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt, indlusivf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt, fxdlusivf
     * @pbrbm op b sidf-ffffdt-frff, bssodibtivf fundtion to pfrform tif
     * dumulbtion
     * @tirows IllfgblArgumfntExdfption if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *     if {@dodf fromIndfx < 0} or {@dodf toIndfx > brrby.lfngti}
     * @tirows NullPointfrExdfption if tif spfdififd brrby or fundtion is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflPrffix(int[] brrby, int fromIndfx,
                                      int toIndfx, IntBinbryOpfrbtor op) {
        Objfdts.rfquirfNonNull(op);
        rbngfCifdk(brrby.lfngti, fromIndfx, toIndfx);
        if (fromIndfx < toIndfx)
            nfw ArrbyPrffixHflpfrs.IntCumulbtfTbsk
                    (null, op, brrby, fromIndfx, toIndfx).invokf();
    }

    // Sfbrdiing

    /**
     * Sfbrdifs tif spfdififd brrby of longs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.  Tif brrby must bf sortfd (bs
     * by tif {@link #sort(long[])} mftiod) prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(long[] b, long kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of longs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd (bs
     * by tif {@link #sort(long[], int, int)} mftiod)
     * prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(long[] b, int fromIndfx, int toIndfx,
                                   long kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(long[] b, int fromIndfx, int toIndfx,
                                     long kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            long midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;
            flsf if (midVbl > kfy)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of ints for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.  Tif brrby must bf sortfd (bs
     * by tif {@link #sort(int[])} mftiod) prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(int[] b, int kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of ints for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd (bs
     * by tif {@link #sort(int[], int, int)} mftiod)
     * prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(int[] b, int fromIndfx, int toIndfx,
                                   int kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(int[] b, int fromIndfx, int toIndfx,
                                     int kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            int midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;
            flsf if (midVbl > kfy)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of siorts for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim.  Tif brrby must bf sortfd
     * (bs by tif {@link #sort(siort[])} mftiod) prior to mbking tiis dbll.  If
     * it is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(siort[] b, siort kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of siorts for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd
     * (bs by tif {@link #sort(siort[], int, int)} mftiod)
     * prior to mbking tiis dbll.  If
     * it is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(siort[] b, int fromIndfx, int toIndfx,
                                   siort kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(siort[] b, int fromIndfx, int toIndfx,
                                     siort kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            siort midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;
            flsf if (midVbl > kfy)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of dibrs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.  Tif brrby must bf sortfd (bs
     * by tif {@link #sort(dibr[])} mftiod) prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(dibr[] b, dibr kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of dibrs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd (bs
     * by tif {@link #sort(dibr[], int, int)} mftiod)
     * prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(dibr[] b, int fromIndfx, int toIndfx,
                                   dibr kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(dibr[] b, int fromIndfx, int toIndfx,
                                     dibr kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            dibr midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;
            flsf if (midVbl > kfy)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of bytfs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.  Tif brrby must bf sortfd (bs
     * by tif {@link #sort(bytf[])} mftiod) prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(bytf[] b, bytf kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of bytfs for tif spfdififd vbluf using tif
     * binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd (bs
     * by tif {@link #sort(bytf[], int, int)} mftiod)
     * prior to mbking tiis dbll.  If it
     * is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(bytf[] b, int fromIndfx, int toIndfx,
                                   bytf kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(bytf[] b, int fromIndfx, int toIndfx,
                                     bytf kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            bytf midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;
            flsf if (midVbl > kfy)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of doublfs for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim.  Tif brrby must bf sortfd
     * (bs by tif {@link #sort(doublf[])} mftiod) prior to mbking tiis dbll.
     * If it is not sortfd, tif rfsults brf undffinfd.  If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.  Tiis mftiod donsidfrs bll NbN vblufs to bf
     * fquivblfnt bnd fqubl.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(doublf[] b, doublf kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of doublfs for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd
     * (bs by tif {@link #sort(doublf[], int, int)} mftiod)
     * prior to mbking tiis dbll.
     * If it is not sortfd, tif rfsults brf undffinfd.  If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found.  Tiis mftiod donsidfrs bll NbN vblufs to bf
     * fquivblfnt bnd fqubl.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(doublf[] b, int fromIndfx, int toIndfx,
                                   doublf kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(doublf[] b, int fromIndfx, int toIndfx,
                                     doublf kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            doublf midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;  // Nfitifr vbl is NbN, tiisVbl is smbllfr
            flsf if (midVbl > kfy)
                iigi = mid - 1; // Nfitifr vbl is NbN, tiisVbl is lbrgfr
            flsf {
                long midBits = Doublf.doublfToLongBits(midVbl);
                long kfyBits = Doublf.doublfToLongBits(kfy);
                if (midBits == kfyBits)     // Vblufs brf fqubl
                    rfturn mid;             // Kfy found
                flsf if (midBits < kfyBits) // (-0.0, 0.0) or (!NbN, NbN)
                    low = mid + 1;
                flsf                        // (0.0, -0.0) or (NbN, !NbN)
                    iigi = mid - 1;
            }
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby of flobts for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim. Tif brrby must bf sortfd
     * (bs by tif {@link #sort(flobt[])} mftiod) prior to mbking tiis dbll. If
     * it is not sortfd, tif rfsults brf undffinfd. If tif brrby dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found. Tiis mftiod donsidfrs bll NbN vblufs to bf
     * fquivblfnt bnd fqubl.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>. Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy. Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     */
    publid stbtid int binbrySfbrdi(flobt[] b, flobt kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby of flobts for tif spfdififd vbluf using
     * tif binbry sfbrdi blgoritim.
     * Tif rbngf must bf sortfd
     * (bs by tif {@link #sort(flobt[], int, int)} mftiod)
     * prior to mbking tiis dbll. If
     * it is not sortfd, tif rfsults brf undffinfd. If tif rbngf dontbins
     * multiplf flfmfnts witi tif spfdififd vbluf, tifrf is no gubrbntff wiidi
     * onf will bf found. Tiis mftiod donsidfrs bll NbN vblufs to bf
     * fquivblfnt bnd fqubl.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>. Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy. Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(flobt[] b, int fromIndfx, int toIndfx,
                                   flobt kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(flobt[] b, int fromIndfx, int toIndfx,
                                     flobt kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            flobt midVbl = b[mid];

            if (midVbl < kfy)
                low = mid + 1;  // Nfitifr vbl is NbN, tiisVbl is smbllfr
            flsf if (midVbl > kfy)
                iigi = mid - 1; // Nfitifr vbl is NbN, tiisVbl is lbrgfr
            flsf {
                int midBits = Flobt.flobtToIntBits(midVbl);
                int kfyBits = Flobt.flobtToIntBits(kfy);
                if (midBits == kfyBits)     // Vblufs brf fqubl
                    rfturn mid;             // Kfy found
                flsf if (midBits < kfyBits) // (-0.0, 0.0) or (!NbN, NbN)
                    low = mid + 1;
                flsf                        // (0.0, -0.0) or (NbN, !NbN)
                    iigi = mid - 1;
            }
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim. Tif brrby must bf sortfd into bsdfnding ordfr
     * bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring}
     * of its flfmfnts (bs by tif
     * {@link #sort(Objfdt[])} mftiod) prior to mbking tiis dbll.
     * If it is not sortfd, tif rfsults brf undffinfd.
     * (If tif brrby dontbins flfmfnts tibt brf not mutublly dompbrbblf (for
     * fxbmplf, strings bnd intfgfrs), it <i>dbnnot</i> bf sortfd bddording
     * to tif nbturbl ordfring of its flfmfnts, ifndf rfsults brf undffinfd.)
     * If tif brrby dontbins multiplf
     * flfmfnts fqubl to tif spfdififd objfdt, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif sfbrdi kfy is not dompbrbblf to tif
     *         flfmfnts of tif brrby.
     */
    publid stbtid int binbrySfbrdi(Objfdt[] b, Objfdt kfy) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim.
     * Tif rbngf must bf sortfd into bsdfnding ordfr
     * bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring}
     * of its flfmfnts (bs by tif
     * {@link #sort(Objfdt[], int, int)} mftiod) prior to mbking tiis
     * dbll.  If it is not sortfd, tif rfsults brf undffinfd.
     * (If tif rbngf dontbins flfmfnts tibt brf not mutublly dompbrbblf (for
     * fxbmplf, strings bnd intfgfrs), it <i>dbnnot</i> bf sortfd bddording
     * to tif nbturbl ordfring of its flfmfnts, ifndf rfsults brf undffinfd.)
     * If tif rbngf dontbins multiplf
     * flfmfnts fqubl to tif spfdififd objfdt, tifrf is no gubrbntff wiidi
     * onf will bf found.
     *
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif sfbrdi kfy is not dompbrbblf to tif
     *         flfmfnts of tif brrby witiin tif spfdififd rbngf.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid int binbrySfbrdi(Objfdt[] b, int fromIndfx, int toIndfx,
                                   Objfdt kfy) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid int binbrySfbrdi0(Objfdt[] b, int fromIndfx, int toIndfx,
                                     Objfdt kfy) {
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            @SupprfssWbrnings("rbwtypfs")
            Compbrbblf midVbl = (Compbrbblf)b[mid];
            @SupprfssWbrnings("undifdkfd")
            int dmp = midVbl.dompbrfTo(kfy);

            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    /**
     * Sfbrdifs tif spfdififd brrby for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim.  Tif brrby must bf sortfd into bsdfnding ordfr
     * bddording to tif spfdififd dompbrbtor (bs by tif
     * {@link #sort(Objfdt[], Compbrbtor) sort(T[], Compbrbtor)}
     * mftiod) prior to mbking tiis dbll.  If it is
     * not sortfd, tif rfsults brf undffinfd.
     * If tif brrby dontbins multiplf
     * flfmfnts fqubl to tif spfdififd objfdt, tifrf is no gubrbntff wiidi onf
     * will bf found.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @pbrbm d tif dompbrbtor by wiidi tif brrby is ordfrfd.  A
     *        <tt>null</tt> vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt grfbtfr tibn tif kfy, or <tt>b.lfngti</tt> if bll
     *         flfmfnts in tif brrby brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif brrby dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor,
     *         or tif sfbrdi kfy is not dompbrbblf to tif
     *         flfmfnts of tif brrby using tiis dompbrbtor.
     */
    publid stbtid <T> int binbrySfbrdi(T[] b, T kfy, Compbrbtor<? supfr T> d) {
        rfturn binbrySfbrdi0(b, 0, b.lfngti, kfy, d);
    }

    /**
     * Sfbrdifs b rbngf of
     * tif spfdififd brrby for tif spfdififd objfdt using tif binbry
     * sfbrdi blgoritim.
     * Tif rbngf must bf sortfd into bsdfnding ordfr
     * bddording to tif spfdififd dompbrbtor (bs by tif
     * {@link #sort(Objfdt[], int, int, Compbrbtor)
     * sort(T[], int, int, Compbrbtor)}
     * mftiod) prior to mbking tiis dbll.
     * If it is not sortfd, tif rfsults brf undffinfd.
     * If tif rbngf dontbins multiplf flfmfnts fqubl to tif spfdififd objfdt,
     * tifrf is no gubrbntff wiidi onf will bf found.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm b tif brrby to bf sfbrdifd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *          sfbrdifd
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf sfbrdifd
     * @pbrbm kfy tif vbluf to bf sfbrdifd for
     * @pbrbm d tif dompbrbtor by wiidi tif brrby is ordfrfd.  A
     *        <tt>null</tt> vbluf indidbtfs tibt tif flfmfnts'
     *        {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     * @rfturn indfx of tif sfbrdi kfy, if it is dontbinfd in tif brrby
     *         witiin tif spfdififd rbngf;
     *         otifrwisf, <tt>(-(<i>insfrtion point</i>) - 1)</tt>.  Tif
     *         <i>insfrtion point</i> is dffinfd bs tif point bt wiidi tif
     *         kfy would bf insfrtfd into tif brrby: tif indfx of tif first
     *         flfmfnt in tif rbngf grfbtfr tibn tif kfy,
     *         or <tt>toIndfx</tt> if bll
     *         flfmfnts in tif rbngf brf lfss tibn tif spfdififd kfy.  Notf
     *         tibt tiis gubrbntffs tibt tif rfturn vbluf will bf &gt;= 0 if
     *         bnd only if tif kfy is found.
     * @tirows ClbssCbstExdfption if tif rbngf dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor,
     *         or tif sfbrdi kfy is not dompbrbblf to tif
     *         flfmfnts in tif rbngf using tiis dompbrbtor.
     * @tirows IllfgblArgumfntExdfption
     *         if {@dodf fromIndfx > toIndfx}
     * @tirows ArrbyIndfxOutOfBoundsExdfption
     *         if {@dodf fromIndfx < 0 or toIndfx > b.lfngti}
     * @sindf 1.6
     */
    publid stbtid <T> int binbrySfbrdi(T[] b, int fromIndfx, int toIndfx,
                                       T kfy, Compbrbtor<? supfr T> d) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy, d);
    }

    // Likf publid vfrsion, but witiout rbngf difdks.
    privbtf stbtid <T> int binbrySfbrdi0(T[] b, int fromIndfx, int toIndfx,
                                         T kfy, Compbrbtor<? supfr T> d) {
        if (d == null) {
            rfturn binbrySfbrdi0(b, fromIndfx, toIndfx, kfy);
        }
        int low = fromIndfx;
        int iigi = toIndfx - 1;

        wiilf (low <= iigi) {
            int mid = (low + iigi) >>> 1;
            T midVbl = b[mid];
            int dmp = d.dompbrf(midVbl, kfy);
            if (dmp < 0)
                low = mid + 1;
            flsf if (dmp > 0)
                iigi = mid - 1;
            flsf
                rfturn mid; // kfy found
        }
        rfturn -(low + 1);  // kfy not found.
    }

    // Equblity Tfsting

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of longs brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(long[] b, long[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of ints brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(int[] b, int[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of siorts brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(siort[] b, siort b2[]) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of dibrs brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(dibr[] b, dibr[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of bytfs brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(bytf[] b, bytf[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of boolfbns brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(boolfbn[] b, boolfbn[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (b[i] != b2[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of doublfs brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * Two doublfs <tt>d1</tt> bnd <tt>d2</tt> brf donsidfrfd fqubl if:
     * <prf>    <tt>nfw Doublf(d1).fqubls(nfw Doublf(d2))</tt></prf>
     * (Unlikf tif <tt>==</tt> opfrbtor, tiis mftiod donsidfrs
     * <tt>NbN</tt> fqubls to itsflf, bnd 0.0d unfqubl to -0.0d.)
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     * @sff Doublf#fqubls(Objfdt)
     */
    publid stbtid boolfbn fqubls(doublf[] b, doublf[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (Doublf.doublfToLongBits(b[i])!=Doublf.doublfToLongBits(b2[i]))
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of flobts brf
     * <i>fqubl</i> to onf bnotifr.  Two brrbys brf donsidfrfd fqubl if boti
     * brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding pbirs
     * of flfmfnts in tif two brrbys brf fqubl.  In otifr words, two brrbys
     * brf fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also,
     * two brrby rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * Two flobts <tt>f1</tt> bnd <tt>f2</tt> brf donsidfrfd fqubl if:
     * <prf>    <tt>nfw Flobt(f1).fqubls(nfw Flobt(f2))</tt></prf>
     * (Unlikf tif <tt>==</tt> opfrbtor, tiis mftiod donsidfrs
     * <tt>NbN</tt> fqubls to itsflf, bnd 0.0f unfqubl to -0.0f.)
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     * @sff Flobt#fqubls(Objfdt)
     */
    publid stbtid boolfbn fqubls(flobt[] b, flobt[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++)
            if (Flobt.flobtToIntBits(b[i])!=Flobt.flobtToIntBits(b2[i]))
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys of Objfdts brf
     * <i>fqubl</i> to onf bnotifr.  Tif two brrbys brf donsidfrfd fqubl if
     * boti brrbys dontbin tif sbmf numbfr of flfmfnts, bnd bll dorrfsponding
     * pbirs of flfmfnts in tif two brrbys brf fqubl.  Two objfdts <tt>f1</tt>
     * bnd <tt>f2</tt> brf donsidfrfd <i>fqubl</i> if <tt>(f1==null ? f2==null
     * : f1.fqubls(f2))</tt>.  In otifr words, tif two brrbys brf fqubl if
     * tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Also, two brrby
     * rfffrfndfs brf donsidfrfd fqubl if boti brf <tt>null</tt>.
     *
     * @pbrbm b onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     */
    publid stbtid boolfbn fqubls(Objfdt[] b, Objfdt[] b2) {
        if (b==b2)
            rfturn truf;
        if (b==null || b2==null)
            rfturn fblsf;

        int lfngti = b.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i=0; i<lfngti; i++) {
            Objfdt o1 = b[i];
            Objfdt o2 = b2[i];
            if (!(o1==null ? o2==null : o1.fqubls(o2)))
                rfturn fblsf;
        }

        rfturn truf;
    }

    // Filling

    /**
     * Assigns tif spfdififd long vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of longs.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(long[] b, long vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd long vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of longs.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(long[] b, int fromIndfx, int toIndfx, long vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd int vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of ints.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(int[] b, int vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd int vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of ints.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(int[] b, int fromIndfx, int toIndfx, int vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd siort vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of siorts.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(siort[] b, siort vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd siort vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of siorts.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(siort[] b, int fromIndfx, int toIndfx, siort vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd dibr vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of dibrs.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(dibr[] b, dibr vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd dibr vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of dibrs.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(dibr[] b, int fromIndfx, int toIndfx, dibr vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd bytf vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of bytfs.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(bytf[] b, bytf vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd bytf vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of bytfs.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(bytf[] b, int fromIndfx, int toIndfx, bytf vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd boolfbn vbluf to fbdi flfmfnt of tif spfdififd
     * brrby of boolfbns.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(boolfbn[] b, boolfbn vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd boolfbn vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of boolfbns.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(boolfbn[] b, int fromIndfx, int toIndfx,
                            boolfbn vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd doublf vbluf to fbdi flfmfnt of tif spfdififd
     * brrby of doublfs.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(doublf[] b, doublf vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd doublf vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of doublfs.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(doublf[] b, int fromIndfx, int toIndfx,doublf vbl){
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd flobt vbluf to fbdi flfmfnt of tif spfdififd brrby
     * of flobts.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     */
    publid stbtid void fill(flobt[] b, flobt vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd flobt vbluf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of flobts.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     */
    publid stbtid void fill(flobt[] b, int fromIndfx, int toIndfx, flobt vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd Objfdt rfffrfndf to fbdi flfmfnt of tif spfdififd
     * brrby of Objfdts.
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows ArrbyStorfExdfption if tif spfdififd vbluf is not of b
     *         runtimf typf tibt dbn bf storfd in tif spfdififd brrby
     */
    publid stbtid void fill(Objfdt[] b, Objfdt vbl) {
        for (int i = 0, lfn = b.lfngti; i < lfn; i++)
            b[i] = vbl;
    }

    /**
     * Assigns tif spfdififd Objfdt rfffrfndf to fbdi flfmfnt of tif spfdififd
     * rbngf of tif spfdififd brrby of Objfdts.  Tif rbngf to bf fillfd
     * fxtfnds from indfx <tt>fromIndfx</tt>, indlusivf, to indfx
     * <tt>toIndfx</tt>, fxdlusivf.  (If <tt>fromIndfx==toIndfx</tt>, tif
     * rbngf to bf fillfd is fmpty.)
     *
     * @pbrbm b tif brrby to bf fillfd
     * @pbrbm fromIndfx tif indfx of tif first flfmfnt (indlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm toIndfx tif indfx of tif lbst flfmfnt (fxdlusivf) to bf
     *        fillfd witi tif spfdififd vbluf
     * @pbrbm vbl tif vbluf to bf storfd in bll flfmfnts of tif brrby
     * @tirows IllfgblArgumfntExdfption if <tt>fromIndfx &gt; toIndfx</tt>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <tt>fromIndfx &lt; 0</tt> or
     *         <tt>toIndfx &gt; b.lfngti</tt>
     * @tirows ArrbyStorfExdfption if tif spfdififd vbluf is not of b
     *         runtimf typf tibt dbn bf storfd in tif spfdififd brrby
     */
    publid stbtid void fill(Objfdt[] b, int fromIndfx, int toIndfx, Objfdt vbl) {
        rbngfCifdk(b.lfngti, fromIndfx, toIndfx);
        for (int i = fromIndfx; i < toIndfx; i++)
            b[i] = vbl;
    }

    // Cloning

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi nulls (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>null</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     * Tif rfsulting brrby is of fxbdtly tif sbmf dlbss bs tif originbl brrby.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi nulls
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> T[] dopyOf(T[] originbl, int nfwLfngti) {
        rfturn (T[]) dopyOf(originbl, nfwLfngti, originbl.gftClbss());
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi nulls (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>null</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     * Tif rfsulting brrby is of tif dlbss <tt>nfwTypf</tt>.
     *
     * @pbrbm <U> tif dlbss of tif objfdts in tif originbl brrby
     * @pbrbm <T> tif dlbss of tif objfdts in tif rfturnfd brrby
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @pbrbm nfwTypf tif dlbss of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi nulls
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @tirows ArrbyStorfExdfption if bn flfmfnt dopifd from
     *     <tt>originbl</tt> is not of b runtimf typf tibt dbn bf storfd in
     *     bn brrby of dlbss <tt>nfwTypf</tt>
     * @sindf 1.6
     */
    publid stbtid <T,U> T[] dopyOf(U[] originbl, int nfwLfngti, Clbss<? fxtfnds T[]> nfwTypf) {
        @SupprfssWbrnings("undifdkfd")
        T[] dopy = ((Objfdt)nfwTypf == (Objfdt)Objfdt[].dlbss)
            ? (T[]) nfw Objfdt[nfwLfngti]
            : (T[]) Arrby.nfwInstbndf(nfwTypf.gftComponfntTypf(), nfwLfngti);
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>(bytf)0</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid bytf[] dopyOf(bytf[] originbl, int nfwLfngti) {
        bytf[] dopy = nfw bytf[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>(siort)0</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid siort[] dopyOf(siort[] originbl, int nfwLfngti) {
        siort[] dopy = nfw siort[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>0</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid int[] dopyOf(int[] originbl, int nfwLfngti) {
        int[] dopy = nfw int[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>0L</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid long[] dopyOf(long[] originbl, int nfwLfngti) {
        long[] dopy = nfw long[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi null dibrbdtfrs (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf vblid
     * in boti tif originbl brrby bnd tif dopy, tif two brrbys will dontbin
     * idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif dopy but not
     * tif originbl, tif dopy will dontbin <tt>'\\u000'</tt>.  Sudi indidfs
     * will fxist if bnd only if tif spfdififd lfngti is grfbtfr tibn tibt of
     * tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi null dibrbdtfrs
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid dibr[] dopyOf(dibr[] originbl, int nfwLfngti) {
        dibr[] dopy = nfw dibr[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>0f</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid flobt[] dopyOf(flobt[] originbl, int nfwLfngti) {
        flobt[] dopy = nfw flobt[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi zfros (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>0d</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi zfros
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid doublf[] dopyOf(doublf[] originbl, int nfwLfngti) {
        doublf[] dopy = nfw doublf[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd brrby, trundbting or pbdding witi <tt>fblsf</tt> (if nfdfssbry)
     * so tif dopy ibs tif spfdififd lfngti.  For bll indidfs tibt brf
     * vblid in boti tif originbl brrby bnd tif dopy, tif two brrbys will
     * dontbin idfntidbl vblufs.  For bny indidfs tibt brf vblid in tif
     * dopy but not tif originbl, tif dopy will dontbin <tt>fblsf</tt>.
     * Sudi indidfs will fxist if bnd only if tif spfdififd lfngti
     * is grfbtfr tibn tibt of tif originbl brrby.
     *
     * @pbrbm originbl tif brrby to bf dopifd
     * @pbrbm nfwLfngti tif lfngti of tif dopy to bf rfturnfd
     * @rfturn b dopy of tif originbl brrby, trundbtfd or pbddfd witi fblsf flfmfnts
     *     to obtbin tif spfdififd lfngti
     * @tirows NfgbtivfArrbySizfExdfption if <tt>nfwLfngti</tt> is nfgbtivf
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid boolfbn[] dopyOf(boolfbn[] originbl, int nfwLfngti) {
        boolfbn[] dopy = nfw boolfbn[nfwLfngti];
        Systfm.brrbydopy(originbl, 0, dopy, 0,
                         Mbti.min(originbl.lfngti, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>null</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     * <p>
     * Tif rfsulting brrby is of fxbdtly tif sbmf dlbss bs tif originbl brrby.
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi nulls to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid <T> T[] dopyOfRbngf(T[] originbl, int from, int to) {
        rfturn dopyOfRbngf(originbl, from, to, (Clbss<? fxtfnds T[]>) originbl.gftClbss());
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>null</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     * Tif rfsulting brrby is of tif dlbss <tt>nfwTypf</tt>.
     *
     * @pbrbm <U> tif dlbss of tif objfdts in tif originbl brrby
     * @pbrbm <T> tif dlbss of tif objfdts in tif rfturnfd brrby
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @pbrbm nfwTypf tif dlbss of tif dopy to bf rfturnfd
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi nulls to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @tirows ArrbyStorfExdfption if bn flfmfnt dopifd from
     *     <tt>originbl</tt> is not of b runtimf typf tibt dbn bf storfd in
     *     bn brrby of dlbss <tt>nfwTypf</tt>.
     * @sindf 1.6
     */
    publid stbtid <T,U> T[] dopyOfRbngf(U[] originbl, int from, int to, Clbss<? fxtfnds T[]> nfwTypf) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        @SupprfssWbrnings("undifdkfd")
        T[] dopy = ((Objfdt)nfwTypf == (Objfdt)Objfdt[].dlbss)
            ? (T[]) nfw Objfdt[nfwLfngti]
            : (T[]) Arrby.nfwInstbndf(nfwTypf.gftComponfntTypf(), nfwLfngti);
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>(bytf)0</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid bytf[] dopyOfRbngf(bytf[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        bytf[] dopy = nfw bytf[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>(siort)0</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid siort[] dopyOfRbngf(siort[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        siort[] dopy = nfw siort[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>0</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid int[] dopyOfRbngf(int[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        int[] dopy = nfw int[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>0L</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid long[] dopyOfRbngf(long[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        long[] dopy = nfw long[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>'\\u000'</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi null dibrbdtfrs to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid dibr[] dopyOfRbngf(dibr[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        dibr[] dopy = nfw dibr[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>0f</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid flobt[] dopyOfRbngf(flobt[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        flobt[] dopy = nfw flobt[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>0d</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi zfros to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid doublf[] dopyOfRbngf(doublf[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        doublf[] dopy = nfw doublf[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    /**
     * Copifs tif spfdififd rbngf of tif spfdififd brrby into b nfw brrby.
     * Tif initibl indfx of tif rbngf (<tt>from</tt>) must lif bftwffn zfro
     * bnd <tt>originbl.lfngti</tt>, indlusivf.  Tif vbluf bt
     * <tt>originbl[from]</tt> is plbdfd into tif initibl flfmfnt of tif dopy
     * (unlfss <tt>from == originbl.lfngti</tt> or <tt>from == to</tt>).
     * Vblufs from subsfqufnt flfmfnts in tif originbl brrby brf plbdfd into
     * subsfqufnt flfmfnts in tif dopy.  Tif finbl indfx of tif rbngf
     * (<tt>to</tt>), wiidi must bf grfbtfr tibn or fqubl to <tt>from</tt>,
     * mby bf grfbtfr tibn <tt>originbl.lfngti</tt>, in wiidi dbsf
     * <tt>fblsf</tt> is plbdfd in bll flfmfnts of tif dopy wiosf indfx is
     * grfbtfr tibn or fqubl to <tt>originbl.lfngti - from</tt>.  Tif lfngti
     * of tif rfturnfd brrby will bf <tt>to - from</tt>.
     *
     * @pbrbm originbl tif brrby from wiidi b rbngf is to bf dopifd
     * @pbrbm from tif initibl indfx of tif rbngf to bf dopifd, indlusivf
     * @pbrbm to tif finbl indfx of tif rbngf to bf dopifd, fxdlusivf.
     *     (Tiis indfx mby lif outsidf tif brrby.)
     * @rfturn b nfw brrby dontbining tif spfdififd rbngf from tif originbl brrby,
     *     trundbtfd or pbddfd witi fblsf flfmfnts to obtbin tif rfquirfd lfngti
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf from < 0}
     *     or {@dodf from > originbl.lfngti}
     * @tirows IllfgblArgumfntExdfption if <tt>from &gt; to</tt>
     * @tirows NullPointfrExdfption if <tt>originbl</tt> is null
     * @sindf 1.6
     */
    publid stbtid boolfbn[] dopyOfRbngf(boolfbn[] originbl, int from, int to) {
        int nfwLfngti = to - from;
        if (nfwLfngti < 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        boolfbn[] dopy = nfw boolfbn[nfwLfngti];
        Systfm.brrbydopy(originbl, from, dopy, 0,
                         Mbti.min(originbl.lfngti - from, nfwLfngti));
        rfturn dopy;
    }

    // Misd

    /**
     * Rfturns b fixfd-sizf list bbdkfd by tif spfdififd brrby.  (Cibngfs to
     * tif rfturnfd list "writf tirougi" to tif brrby.)  Tiis mftiod bdts
     * bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd APIs, in
     * dombinbtion witi {@link Collfdtion#toArrby}.  Tif rfturnfd list is
     * sfriblizbblf bnd implfmfnts {@link RbndomAddfss}.
     *
     * <p>Tiis mftiod blso providfs b donvfnifnt wby to drfbtf b fixfd-sizf
     * list initiblizfd to dontbin sfvfrbl flfmfnts:
     * <prf>
     *     List&lt;String&gt; stoogfs = Arrbys.bsList("Lbrry", "Mof", "Curly");
     * </prf>
     *
     * @pbrbm <T> tif dlbss of tif objfdts in tif brrby
     * @pbrbm b tif brrby by wiidi tif list will bf bbdkfd
     * @rfturn b list vifw of tif spfdififd brrby
     */
    @SbffVbrbrgs
    @SupprfssWbrnings("vbrbrgs")
    publid stbtid <T> List<T> bsList(T... b) {
        rfturn nfw ArrbyList<>(b);
    }

    /**
     * @sfribl indludf
     */
    privbtf stbtid dlbss ArrbyList<E> fxtfnds AbstrbdtList<E>
        implfmfnts RbndomAddfss, jbvb.io.Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = -2764017481108945198L;
        privbtf finbl E[] b;

        ArrbyList(E[] brrby) {
            b = Objfdts.rfquirfNonNull(brrby);
        }

        @Ovfrridf
        publid int sizf() {
            rfturn b.lfngti;
        }

        @Ovfrridf
        publid Objfdt[] toArrby() {
            rfturn b.dlonf();
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid <T> T[] toArrby(T[] b) {
            int sizf = sizf();
            if (b.lfngti < sizf)
                rfturn Arrbys.dopyOf(tiis.b, sizf,
                                     (Clbss<? fxtfnds T[]>) b.gftClbss());
            Systfm.brrbydopy(tiis.b, 0, b, 0, sizf);
            if (b.lfngti > sizf)
                b[sizf] = null;
            rfturn b;
        }

        @Ovfrridf
        publid E gft(int indfx) {
            rfturn b[indfx];
        }

        @Ovfrridf
        publid E sft(int indfx, E flfmfnt) {
            E oldVbluf = b[indfx];
            b[indfx] = flfmfnt;
            rfturn oldVbluf;
        }

        @Ovfrridf
        publid int indfxOf(Objfdt o) {
            E[] b = tiis.b;
            if (o == null) {
                for (int i = 0; i < b.lfngti; i++)
                    if (b[i] == null)
                        rfturn i;
            } flsf {
                for (int i = 0; i < b.lfngti; i++)
                    if (o.fqubls(b[i]))
                        rfturn i;
            }
            rfturn -1;
        }

        @Ovfrridf
        publid boolfbn dontbins(Objfdt o) {
            rfturn indfxOf(o) != -1;
        }

        @Ovfrridf
        publid Splitfrbtor<E> splitfrbtor() {
            rfturn Splitfrbtors.splitfrbtor(b, Splitfrbtor.ORDERED);
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            for (E f : b) {
                bdtion.bddfpt(f);
            }
        }

        @Ovfrridf
        publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
            Objfdts.rfquirfNonNull(opfrbtor);
            E[] b = tiis.b;
            for (int i = 0; i < b.lfngti; i++) {
                b[i] = opfrbtor.bpply(b[i]);
            }
        }

        @Ovfrridf
        publid void sort(Compbrbtor<? supfr E> d) {
            Arrbys.sort(b, d);
        }
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>long</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Long}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(long b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (long flfmfnt : b) {
            int flfmfntHbsi = (int)(flfmfnt ^ (flfmfnt >>> 32));
            rfsult = 31 * rfsult + flfmfntHbsi;
        }

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two non-null <tt>int</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Intfgfr}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(int b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (int flfmfnt : b)
            rfsult = 31 * rfsult + flfmfnt;

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>siort</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Siort}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(siort b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (siort flfmfnt : b)
            rfsult = 31 * rfsult + flfmfnt;

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>dibr</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Cibrbdtfr}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(dibr b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (dibr flfmfnt : b)
            rfsult = 31 * rfsult + flfmfnt;

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>bytf</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Bytf}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(bytf b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (bytf flfmfnt : b)
            rfsult = 31 * rfsult + flfmfnt;

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>boolfbn</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Boolfbn}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(boolfbn b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (boolfbn flfmfnt : b)
            rfsult = 31 * rfsult + (flfmfnt ? 1231 : 1237);

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>flobt</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Flobt}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(flobt b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (flobt flfmfnt : b)
            rfsult = 31 * rfsult + Flobt.flobtToIntBits(flfmfnt);

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.
     * For bny two <tt>doublf</tt> brrbys <tt>b</tt> bnd <tt>b</tt>
     * sudi tibt <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is tif sbmf vbluf tibt would bf
     * obtbinfd by invoking tif {@link List#ibsiCodf() <tt>ibsiCodf</tt>}
     * mftiod on b {@link List} dontbining b sfqufndf of {@link Doublf}
     * instbndfs rfprfsfnting tif flfmfnts of <tt>b</tt> in tif sbmf ordfr.
     * If <tt>b</tt> is <tt>null</tt>, tiis mftiod rfturns 0.
     *
     * @pbrbm b tif brrby wiosf ibsi vbluf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(doublf b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;
        for (doublf flfmfnt : b) {
            long bits = Doublf.doublfToLongBits(flfmfnt);
            rfsult = 31 * rfsult + (int)(bits ^ (bits >>> 32));
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif dontfnts of tif spfdififd brrby.  If
     * tif brrby dontbins otifr brrbys bs flfmfnts, tif ibsi dodf is bbsfd on
     * tifir idfntitifs rbtifr tibn tifir dontfnts.  It is tifrfforf
     * bddfptbblf to invokf tiis mftiod on bn brrby tibt dontbins itsflf bs bn
     * flfmfnt,  fitifr dirfdtly or indirfdtly tirougi onf or morf lfvfls of
     * brrbys.
     *
     * <p>For bny two brrbys <tt>b</tt> bnd <tt>b</tt> sudi tibt
     * <tt>Arrbys.fqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.ibsiCodf(b) == Arrbys.ibsiCodf(b)</tt>.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is fqubl to tif vbluf tibt would
     * bf rfturnfd by <tt>Arrbys.bsList(b).ibsiCodf()</tt>, unlfss <tt>b</tt>
     * is <tt>null</tt>, in wiidi dbsf <tt>0</tt> is rfturnfd.
     *
     * @pbrbm b tif brrby wiosf dontfnt-bbsfd ibsi dodf to domputf
     * @rfturn b dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sff #dffpHbsiCodf(Objfdt[])
     * @sindf 1.5
     */
    publid stbtid int ibsiCodf(Objfdt b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;

        for (Objfdt flfmfnt : b)
            rfsult = 31 * rfsult + (flfmfnt == null ? 0 : flfmfnt.ibsiCodf());

        rfturn rfsult;
    }

    /**
     * Rfturns b ibsi dodf bbsfd on tif "dffp dontfnts" of tif spfdififd
     * brrby.  If tif brrby dontbins otifr brrbys bs flfmfnts, tif
     * ibsi dodf is bbsfd on tifir dontfnts bnd so on, bd infinitum.
     * It is tifrfforf unbddfptbblf to invokf tiis mftiod on bn brrby tibt
     * dontbins itsflf bs bn flfmfnt, fitifr dirfdtly or indirfdtly tirougi
     * onf or morf lfvfls of brrbys.  Tif bfibvior of sudi bn invodbtion is
     * undffinfd.
     *
     * <p>For bny two brrbys <tt>b</tt> bnd <tt>b</tt> sudi tibt
     * <tt>Arrbys.dffpEqubls(b, b)</tt>, it is blso tif dbsf tibt
     * <tt>Arrbys.dffpHbsiCodf(b) == Arrbys.dffpHbsiCodf(b)</tt>.
     *
     * <p>Tif domputbtion of tif vbluf rfturnfd by tiis mftiod is similbr to
     * tibt of tif vbluf rfturnfd by {@link List#ibsiCodf()} on b list
     * dontbining tif sbmf flfmfnts bs <tt>b</tt> in tif sbmf ordfr, witi onf
     * difffrfndf: If bn flfmfnt <tt>f</tt> of <tt>b</tt> is itsflf bn brrby,
     * its ibsi dodf is domputfd not by dblling <tt>f.ibsiCodf()</tt>, but bs
     * by dblling tif bppropribtf ovfrlobding of <tt>Arrbys.ibsiCodf(f)</tt>
     * if <tt>f</tt> is bn brrby of b primitivf typf, or bs by dblling
     * <tt>Arrbys.dffpHbsiCodf(f)</tt> rfdursivfly if <tt>f</tt> is bn brrby
     * of b rfffrfndf typf.  If <tt>b</tt> is <tt>null</tt>, tiis mftiod
     * rfturns 0.
     *
     * @pbrbm b tif brrby wiosf dffp-dontfnt-bbsfd ibsi dodf to domputf
     * @rfturn b dffp-dontfnt-bbsfd ibsi dodf for <tt>b</tt>
     * @sff #ibsiCodf(Objfdt[])
     * @sindf 1.5
     */
    publid stbtid int dffpHbsiCodf(Objfdt b[]) {
        if (b == null)
            rfturn 0;

        int rfsult = 1;

        for (Objfdt flfmfnt : b) {
            int flfmfntHbsi = 0;
            if (flfmfnt instbndfof Objfdt[])
                flfmfntHbsi = dffpHbsiCodf((Objfdt[]) flfmfnt);
            flsf if (flfmfnt instbndfof bytf[])
                flfmfntHbsi = ibsiCodf((bytf[]) flfmfnt);
            flsf if (flfmfnt instbndfof siort[])
                flfmfntHbsi = ibsiCodf((siort[]) flfmfnt);
            flsf if (flfmfnt instbndfof int[])
                flfmfntHbsi = ibsiCodf((int[]) flfmfnt);
            flsf if (flfmfnt instbndfof long[])
                flfmfntHbsi = ibsiCodf((long[]) flfmfnt);
            flsf if (flfmfnt instbndfof dibr[])
                flfmfntHbsi = ibsiCodf((dibr[]) flfmfnt);
            flsf if (flfmfnt instbndfof flobt[])
                flfmfntHbsi = ibsiCodf((flobt[]) flfmfnt);
            flsf if (flfmfnt instbndfof doublf[])
                flfmfntHbsi = ibsiCodf((doublf[]) flfmfnt);
            flsf if (flfmfnt instbndfof boolfbn[])
                flfmfntHbsi = ibsiCodf((boolfbn[]) flfmfnt);
            flsf if (flfmfnt != null)
                flfmfntHbsi = flfmfnt.ibsiCodf();

            rfsult = 31 * rfsult + flfmfntHbsi;
        }

        rfturn rfsult;
    }

    /**
     * Rfturns <tt>truf</tt> if tif two spfdififd brrbys brf <i>dffply
     * fqubl</i> to onf bnotifr.  Unlikf tif {@link #fqubls(Objfdt[],Objfdt[])}
     * mftiod, tiis mftiod is bppropribtf for usf witi nfstfd brrbys of
     * brbitrbry dfpti.
     *
     * <p>Two brrby rfffrfndfs brf donsidfrfd dffply fqubl if boti
     * brf <tt>null</tt>, or if tify rfffr to brrbys tibt dontbin tif sbmf
     * numbfr of flfmfnts bnd bll dorrfsponding pbirs of flfmfnts in tif two
     * brrbys brf dffply fqubl.
     *
     * <p>Two possibly <tt>null</tt> flfmfnts <tt>f1</tt> bnd <tt>f2</tt> brf
     * dffply fqubl if bny of tif following donditions iold:
     * <ul>
     *    <li> <tt>f1</tt> bnd <tt>f2</tt> brf boti brrbys of objfdt rfffrfndf
     *         typfs, bnd <tt>Arrbys.dffpEqubls(f1, f2) would rfturn truf</tt>
     *    <li> <tt>f1</tt> bnd <tt>f2</tt> brf brrbys of tif sbmf primitivf
     *         typf, bnd tif bppropribtf ovfrlobding of
     *         <tt>Arrbys.fqubls(f1, f2)</tt> would rfturn truf.
     *    <li> <tt>f1 == f2</tt>
     *    <li> <tt>f1.fqubls(f2)</tt> would rfturn truf.
     * </ul>
     * Notf tibt tiis dffinition pfrmits <tt>null</tt> flfmfnts bt bny dfpti.
     *
     * <p>If fitifr of tif spfdififd brrbys dontbin tifmsflvfs bs flfmfnts
     * fitifr dirfdtly or indirfdtly tirougi onf or morf lfvfls of brrbys,
     * tif bfibvior of tiis mftiod is undffinfd.
     *
     * @pbrbm b1 onf brrby to bf tfstfd for fqublity
     * @pbrbm b2 tif otifr brrby to bf tfstfd for fqublity
     * @rfturn <tt>truf</tt> if tif two brrbys brf fqubl
     * @sff #fqubls(Objfdt[],Objfdt[])
     * @sff Objfdts#dffpEqubls(Objfdt, Objfdt)
     * @sindf 1.5
     */
    publid stbtid boolfbn dffpEqubls(Objfdt[] b1, Objfdt[] b2) {
        if (b1 == b2)
            rfturn truf;
        if (b1 == null || b2==null)
            rfturn fblsf;
        int lfngti = b1.lfngti;
        if (b2.lfngti != lfngti)
            rfturn fblsf;

        for (int i = 0; i < lfngti; i++) {
            Objfdt f1 = b1[i];
            Objfdt f2 = b2[i];

            if (f1 == f2)
                dontinuf;
            if (f1 == null)
                rfturn fblsf;

            // Figurf out wiftifr tif two flfmfnts brf fqubl
            boolfbn fq = dffpEqubls0(f1, f2);

            if (!fq)
                rfturn fblsf;
        }
        rfturn truf;
    }

    stbtid boolfbn dffpEqubls0(Objfdt f1, Objfdt f2) {
        bssfrt f1 != null;
        boolfbn fq;
        if (f1 instbndfof Objfdt[] && f2 instbndfof Objfdt[])
            fq = dffpEqubls ((Objfdt[]) f1, (Objfdt[]) f2);
        flsf if (f1 instbndfof bytf[] && f2 instbndfof bytf[])
            fq = fqubls((bytf[]) f1, (bytf[]) f2);
        flsf if (f1 instbndfof siort[] && f2 instbndfof siort[])
            fq = fqubls((siort[]) f1, (siort[]) f2);
        flsf if (f1 instbndfof int[] && f2 instbndfof int[])
            fq = fqubls((int[]) f1, (int[]) f2);
        flsf if (f1 instbndfof long[] && f2 instbndfof long[])
            fq = fqubls((long[]) f1, (long[]) f2);
        flsf if (f1 instbndfof dibr[] && f2 instbndfof dibr[])
            fq = fqubls((dibr[]) f1, (dibr[]) f2);
        flsf if (f1 instbndfof flobt[] && f2 instbndfof flobt[])
            fq = fqubls((flobt[]) f1, (flobt[]) f2);
        flsf if (f1 instbndfof doublf[] && f2 instbndfof doublf[])
            fq = fqubls((doublf[]) f1, (doublf[]) f2);
        flsf if (f1 instbndfof boolfbn[] && f2 instbndfof boolfbn[])
            fq = fqubls((boolfbn[]) f1, (boolfbn[]) f2);
        flsf
            fq = f1.fqubls(f2);
        rfturn fq;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(long)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt>
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(long[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(int)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt> is
     * <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(int[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(siort)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt>
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(siort[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(dibr)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt>
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(dibr[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts
     * brf sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd
     * by b spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(bytf)</tt>.  Rfturns <tt>"null"</tt> if
     * <tt>b</tt> is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(bytf[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(boolfbn)</tt>.  Rfturns <tt>"null"</tt> if
     * <tt>b</tt> is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(boolfbn[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(flobt)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt>
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(flobt[] b) {
        if (b == null)
            rfturn "null";

        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * Tif string rfprfsfntbtion donsists of b list of tif brrby's flfmfnts,
     * fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt flfmfnts brf
     * sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb followfd by b
     * spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(doublf)</tt>.  Rfturns <tt>"null"</tt> if <tt>b</tt>
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sindf 1.5
     */
    publid stbtid String toString(doublf[] b) {
        if (b == null)
            rfturn "null";
        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(b[i]);
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif dontfnts of tif spfdififd brrby.
     * If tif brrby dontbins otifr brrbys bs flfmfnts, tify brf donvfrtfd to
     * strings by tif {@link Objfdt#toString} mftiod inifritfd from
     * <tt>Objfdt</tt>, wiidi dfsdribfs tifir <i>idfntitifs</i> rbtifr tibn
     * tifir dontfnts.
     *
     * <p>Tif vbluf rfturnfd by tiis mftiod is fqubl to tif vbluf tibt would
     * bf rfturnfd by <tt>Arrbys.bsList(b).toString()</tt>, unlfss <tt>b</tt>
     * is <tt>null</tt>, in wiidi dbsf <tt>"null"</tt> is rfturnfd.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sff #dffpToString(Objfdt[])
     * @sindf 1.5
     */
    publid stbtid String toString(Objfdt[] b) {
        if (b == null)
            rfturn "null";

        int iMbx = b.lfngti - 1;
        if (iMbx == -1)
            rfturn "[]";

        StringBuildfr b = nfw StringBuildfr();
        b.bppfnd('[');
        for (int i = 0; ; i++) {
            b.bppfnd(String.vblufOf(b[i]));
            if (i == iMbx)
                rfturn b.bppfnd(']').toString();
            b.bppfnd(", ");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif "dffp dontfnts" of tif spfdififd
     * brrby.  If tif brrby dontbins otifr brrbys bs flfmfnts, tif string
     * rfprfsfntbtion dontbins tifir dontfnts bnd so on.  Tiis mftiod is
     * dfsignfd for donvfrting multidimfnsionbl brrbys to strings.
     *
     * <p>Tif string rfprfsfntbtion donsists of b list of tif brrby's
     * flfmfnts, fndlosfd in squbrf brbdkfts (<tt>"[]"</tt>).  Adjbdfnt
     * flfmfnts brf sfpbrbtfd by tif dibrbdtfrs <tt>", "</tt> (b dommb
     * followfd by b spbdf).  Elfmfnts brf donvfrtfd to strings bs by
     * <tt>String.vblufOf(Objfdt)</tt>, unlfss tify brf tifmsflvfs
     * brrbys.
     *
     * <p>If bn flfmfnt <tt>f</tt> is bn brrby of b primitivf typf, it is
     * donvfrtfd to b string bs by invoking tif bppropribtf ovfrlobding of
     * <tt>Arrbys.toString(f)</tt>.  If bn flfmfnt <tt>f</tt> is bn brrby of b
     * rfffrfndf typf, it is donvfrtfd to b string bs by invoking
     * tiis mftiod rfdursivfly.
     *
     * <p>To bvoid infinitf rfdursion, if tif spfdififd brrby dontbins itsflf
     * bs bn flfmfnt, or dontbins bn indirfdt rfffrfndf to itsflf tirougi onf
     * or morf lfvfls of brrbys, tif sflf-rfffrfndf is donvfrtfd to tif string
     * <tt>"[...]"</tt>.  For fxbmplf, bn brrby dontbining only b rfffrfndf
     * to itsflf would bf rfndfrfd bs <tt>"[[...]]"</tt>.
     *
     * <p>Tiis mftiod rfturns <tt>"null"</tt> if tif spfdififd brrby
     * is <tt>null</tt>.
     *
     * @pbrbm b tif brrby wiosf string rfprfsfntbtion to rfturn
     * @rfturn b string rfprfsfntbtion of <tt>b</tt>
     * @sff #toString(Objfdt[])
     * @sindf 1.5
     */
    publid stbtid String dffpToString(Objfdt[] b) {
        if (b == null)
            rfturn "null";

        int bufLfn = 20 * b.lfngti;
        if (b.lfngti != 0 && bufLfn <= 0)
            bufLfn = Intfgfr.MAX_VALUE;
        StringBuildfr buf = nfw StringBuildfr(bufLfn);
        dffpToString(b, buf, nfw HbsiSft<>());
        rfturn buf.toString();
    }

    privbtf stbtid void dffpToString(Objfdt[] b, StringBuildfr buf,
                                     Sft<Objfdt[]> dfjbVu) {
        if (b == null) {
            buf.bppfnd("null");
            rfturn;
        }
        int iMbx = b.lfngti - 1;
        if (iMbx == -1) {
            buf.bppfnd("[]");
            rfturn;
        }

        dfjbVu.bdd(b);
        buf.bppfnd('[');
        for (int i = 0; ; i++) {

            Objfdt flfmfnt = b[i];
            if (flfmfnt == null) {
                buf.bppfnd("null");
            } flsf {
                Clbss<?> fClbss = flfmfnt.gftClbss();

                if (fClbss.isArrby()) {
                    if (fClbss == bytf[].dlbss)
                        buf.bppfnd(toString((bytf[]) flfmfnt));
                    flsf if (fClbss == siort[].dlbss)
                        buf.bppfnd(toString((siort[]) flfmfnt));
                    flsf if (fClbss == int[].dlbss)
                        buf.bppfnd(toString((int[]) flfmfnt));
                    flsf if (fClbss == long[].dlbss)
                        buf.bppfnd(toString((long[]) flfmfnt));
                    flsf if (fClbss == dibr[].dlbss)
                        buf.bppfnd(toString((dibr[]) flfmfnt));
                    flsf if (fClbss == flobt[].dlbss)
                        buf.bppfnd(toString((flobt[]) flfmfnt));
                    flsf if (fClbss == doublf[].dlbss)
                        buf.bppfnd(toString((doublf[]) flfmfnt));
                    flsf if (fClbss == boolfbn[].dlbss)
                        buf.bppfnd(toString((boolfbn[]) flfmfnt));
                    flsf { // flfmfnt is bn brrby of objfdt rfffrfndfs
                        if (dfjbVu.dontbins(flfmfnt))
                            buf.bppfnd("[...]");
                        flsf
                            dffpToString((Objfdt[])flfmfnt, buf, dfjbVu);
                    }
                } flsf {  // flfmfnt is non-null bnd not bn brrby
                    buf.bppfnd(flfmfnt.toString());
                }
            }
            if (i == iMbx)
                brfbk;
            buf.bppfnd(", ");
        }
        buf.bppfnd(']');
        dfjbVu.rfmovf(b);
    }


    /**
     * Sft bll flfmfnts of tif spfdififd brrby, using tif providfd
     * gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr bnd tif brrby is lfft in bn indftfrminbtf stbtf.
     *
     * @pbrbm <T> typf of flfmfnts of tif brrby
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid <T> void sftAll(T[] brrby, IntFundtion<? fxtfnds T> gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        for (int i = 0; i < brrby.lfngti; i++)
            brrby[i] = gfnfrbtor.bpply(i);
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, in pbrbllfl, using tif
     * providfd gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, bn undifdkfd fxdfption
     * is tirown from {@dodf pbrbllflSftAll} bnd tif brrby is lfft in bn
     * indftfrminbtf stbtf.
     *
     * @pbrbm <T> typf of flfmfnts of tif brrby
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid <T> void pbrbllflSftAll(T[] brrby, IntFundtion<? fxtfnds T> gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        IntStrfbm.rbngf(0, brrby.lfngti).pbrbllfl().forEbdi(i -> { brrby[i] = gfnfrbtor.bpply(i); });
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, using tif providfd
     * gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr bnd tif brrby is lfft in bn indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void sftAll(int[] brrby, IntUnbryOpfrbtor gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        for (int i = 0; i < brrby.lfngti; i++)
            brrby[i] = gfnfrbtor.bpplyAsInt(i);
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, in pbrbllfl, using tif
     * providfd gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, bn undifdkfd fxdfption
     * is tirown from {@dodf pbrbllflSftAll} bnd tif brrby is lfft in bn
     * indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     * vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSftAll(int[] brrby, IntUnbryOpfrbtor gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        IntStrfbm.rbngf(0, brrby.lfngti).pbrbllfl().forEbdi(i -> { brrby[i] = gfnfrbtor.bpplyAsInt(i); });
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, using tif providfd
     * gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr bnd tif brrby is lfft in bn indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void sftAll(long[] brrby, IntToLongFundtion gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        for (int i = 0; i < brrby.lfngti; i++)
            brrby[i] = gfnfrbtor.bpplyAsLong(i);
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, in pbrbllfl, using tif
     * providfd gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, bn undifdkfd fxdfption
     * is tirown from {@dodf pbrbllflSftAll} bnd tif brrby is lfft in bn
     * indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSftAll(long[] brrby, IntToLongFundtion gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        IntStrfbm.rbngf(0, brrby.lfngti).pbrbllfl().forEbdi(i -> { brrby[i] = gfnfrbtor.bpplyAsLong(i); });
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, using tif providfd
     * gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, it is rflbyfd to
     * tif dbllfr bnd tif brrby is lfft in bn indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void sftAll(doublf[] brrby, IntToDoublfFundtion gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        for (int i = 0; i < brrby.lfngti; i++)
            brrby[i] = gfnfrbtor.bpplyAsDoublf(i);
    }

    /**
     * Sft bll flfmfnts of tif spfdififd brrby, in pbrbllfl, using tif
     * providfd gfnfrbtor fundtion to domputf fbdi flfmfnt.
     *
     * <p>If tif gfnfrbtor fundtion tirows bn fxdfption, bn undifdkfd fxdfption
     * is tirown from {@dodf pbrbllflSftAll} bnd tif brrby is lfft in bn
     * indftfrminbtf stbtf.
     *
     * @pbrbm brrby brrby to bf initiblizfd
     * @pbrbm gfnfrbtor b fundtion bddfpting bn indfx bnd produding tif dfsirfd
     *        vbluf for tibt position
     * @tirows NullPointfrExdfption if tif gfnfrbtor is null
     * @sindf 1.8
     */
    publid stbtid void pbrbllflSftAll(doublf[] brrby, IntToDoublfFundtion gfnfrbtor) {
        Objfdts.rfquirfNonNull(gfnfrbtor);
        IntStrfbm.rbngf(0, brrby.lfngti).pbrbllfl().forEbdi(i -> { brrby[i] = gfnfrbtor.bpplyAsDoublf(i); });
    }

    /**
     * Rfturns b {@link Splitfrbtor} dovfring bll of tif spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm <T> typf of flfmfnts
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @sindf 1.8
     */
    publid stbtid <T> Splitfrbtor<T> splitfrbtor(T[] brrby) {
        rfturn Splitfrbtors.splitfrbtor(brrby,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor} dovfring tif spfdififd rbngf of tif
     * spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm <T> typf of flfmfnts
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid <T> Splitfrbtor<T> splitfrbtor(T[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn Splitfrbtors.splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfInt} dovfring bll of tif spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfInt splitfrbtor(int[] brrby) {
        rfturn Splitfrbtors.splitfrbtor(brrby,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfInt} dovfring tif spfdififd rbngf of tif
     * spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfInt splitfrbtor(int[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn Splitfrbtors.splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfLong} dovfring bll of tif spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn tif splitfrbtor for tif brrby flfmfnts
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfLong splitfrbtor(long[] brrby) {
        rfturn Splitfrbtors.splitfrbtor(brrby,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfLong} dovfring tif spfdififd rbngf of tif
     * spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfLong splitfrbtor(long[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn Splitfrbtors.splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfDoublf} dovfring bll of tif spfdififd
     * brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfDoublf splitfrbtor(doublf[] brrby) {
        rfturn Splitfrbtors.splitfrbtor(brrby,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b {@link Splitfrbtor.OfDoublf} dovfring tif spfdififd rbngf of
     * tif spfdififd brrby.
     *
     * <p>Tif splitfrbtor rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#IMMUTABLE}.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b splitfrbtor for tif brrby flfmfnts
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid Splitfrbtor.OfDoublf splitfrbtor(doublf[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn Splitfrbtors.splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf,
                                        Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE);
    }

    /**
     * Rfturns b sfqufntibl {@link Strfbm} witi tif spfdififd brrby bs its
     * sourdf.
     *
     * @pbrbm <T> Tif typf of tif brrby flfmfnts
     * @pbrbm brrby Tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b {@dodf Strfbm} for tif brrby
     * @sindf 1.8
     */
    publid stbtid <T> Strfbm<T> strfbm(T[] brrby) {
        rfturn strfbm(brrby, 0, brrby.lfngti);
    }

    /**
     * Rfturns b sfqufntibl {@link Strfbm} witi tif spfdififd rbngf of tif
     * spfdififd brrby bs its sourdf.
     *
     * @pbrbm <T> tif typf of tif brrby flfmfnts
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b {@dodf Strfbm} for tif brrby rbngf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid <T> Strfbm<T> strfbm(T[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn StrfbmSupport.strfbm(splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf), fblsf);
    }

    /**
     * Rfturns b sfqufntibl {@link IntStrfbm} witi tif spfdififd brrby bs its
     * sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn bn {@dodf IntStrfbm} for tif brrby
     * @sindf 1.8
     */
    publid stbtid IntStrfbm strfbm(int[] brrby) {
        rfturn strfbm(brrby, 0, brrby.lfngti);
    }

    /**
     * Rfturns b sfqufntibl {@link IntStrfbm} witi tif spfdififd rbngf of tif
     * spfdififd brrby bs its sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn bn {@dodf IntStrfbm} for tif brrby rbngf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid IntStrfbm strfbm(int[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn StrfbmSupport.intStrfbm(splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf), fblsf);
    }

    /**
     * Rfturns b sfqufntibl {@link LongStrfbm} witi tif spfdififd brrby bs its
     * sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b {@dodf LongStrfbm} for tif brrby
     * @sindf 1.8
     */
    publid stbtid LongStrfbm strfbm(long[] brrby) {
        rfturn strfbm(brrby, 0, brrby.lfngti);
    }

    /**
     * Rfturns b sfqufntibl {@link LongStrfbm} witi tif spfdififd rbngf of tif
     * spfdififd brrby bs its sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b {@dodf LongStrfbm} for tif brrby rbngf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid LongStrfbm strfbm(long[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn StrfbmSupport.longStrfbm(splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf), fblsf);
    }

    /**
     * Rfturns b sfqufntibl {@link DoublfStrfbm} witi tif spfdififd brrby bs its
     * sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @rfturn b {@dodf DoublfStrfbm} for tif brrby
     * @sindf 1.8
     */
    publid stbtid DoublfStrfbm strfbm(doublf[] brrby) {
        rfturn strfbm(brrby, 0, brrby.lfngti);
    }

    /**
     * Rfturns b sfqufntibl {@link DoublfStrfbm} witi tif spfdififd rbngf of tif
     * spfdififd brrby bs its sourdf.
     *
     * @pbrbm brrby tif brrby, bssumfd to bf unmodififd during usf
     * @pbrbm stbrtIndlusivf tif first indfx to dovfr, indlusivf
     * @pbrbm fndExdlusivf indfx immfdibtfly pbst tif lbst indfx to dovfr
     * @rfturn b {@dodf DoublfStrfbm} for tif brrby rbngf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf stbrtIndlusivf} is
     *         nfgbtivf, {@dodf fndExdlusivf} is lfss tibn
     *         {@dodf stbrtIndlusivf}, or {@dodf fndExdlusivf} is grfbtfr tibn
     *         tif brrby sizf
     * @sindf 1.8
     */
    publid stbtid DoublfStrfbm strfbm(doublf[] brrby, int stbrtIndlusivf, int fndExdlusivf) {
        rfturn StrfbmSupport.doublfStrfbm(splitfrbtor(brrby, stbrtIndlusivf, fndExdlusivf), fblsf);
    }
}
