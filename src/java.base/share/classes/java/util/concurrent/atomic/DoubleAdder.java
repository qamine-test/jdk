/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.btomid;
import jbvb.io.Sfriblizbblf;

/**
 * Onf or morf vbribblfs tibt togftifr mbintbin bn initiblly zfro
 * {@dodf doublf} sum.  Wifn updbtfs (mftiod {@link #bdd}) brf
 * dontfndfd bdross tirfbds, tif sft of vbribblfs mby grow dynbmidblly
 * to rfdudf dontfntion.  Mftiod {@link #sum} (or, fquivblfntly {@link
 * #doublfVbluf}) rfturns tif durrfnt totbl dombinfd bdross tif
 * vbribblfs mbintbining tif sum. Tif ordfr of bddumulbtion witiin or
 * bdross tirfbds is not gubrbntffd. Tius, tiis dlbss mby not bf
 * bpplidbblf if numfridbl stbbility is rfquirfd, fspfdiblly wifn
 * dombining vblufs of substbntiblly difffrfnt ordfrs of mbgnitudf.
 *
 * <p>Tiis dlbss is usublly prfffrbblf to bltfrnbtivfs wifn multiplf
 * tirfbds updbtf b dommon vbluf tibt is usfd for purposfs sudi bs
 * summbry stbtistids tibt brf frfqufntly updbtfd but lfss frfqufntly
 * rfbd.
 *
 * <p>Tiis dlbss fxtfnds {@link Numbfr}, but dofs <fm>not</fm> dffinf
 * mftiods sudi bs {@dodf fqubls}, {@dodf ibsiCodf} bnd {@dodf
 * dompbrfTo} bfdbusf instbndfs brf fxpfdtfd to bf mutbtfd, bnd so brf
 * not usfful bs dollfdtion kfys.
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid dlbss DoublfAddfr fxtfnds Stripfd64 implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7249069246863182397L;

    /*
     * Notf tibt wf must usf "long" for undfrlying rfprfsfntbtions,
     * bfdbusf tifrf is no dompbrfAndSft for doublf, duf to tif fbdt
     * tibt tif bitwisf fqubls usfd in bny CAS implfmfntbtion is not
     * tif sbmf bs doublf-prfdision fqubls.  Howfvfr, wf usf CAS only
     * to dftfdt bnd bllfvibtf dontfntion, for wiidi bitwisf fqubls
     * works bfst bnywby. In prindiplf, tif long/doublf donvfrsions
     * usfd ifrf siould bf fssfntiblly frff on most plbtforms sindf
     * tify just rf-intfrprft bits.
     */

    /**
     * Crfbtfs b nfw bddfr witi initibl sum of zfro.
     */
    publid DoublfAddfr() {
    }

    /**
     * Adds tif givfn vbluf.
     *
     * @pbrbm x tif vbluf to bdd
     */
    publid void bdd(doublf x) {
        Cfll[] bs; long b, v; int m; Cfll b;
        if ((bs = dflls) != null ||
            !dbsBbsf(b = bbsf,
                     Doublf.doublfToRbwLongBits
                     (Doublf.longBitsToDoublf(b) + x))) {
            boolfbn undontfndfd = truf;
            if (bs == null || (m = bs.lfngti - 1) < 0 ||
                (b = bs[gftProbf() & m]) == null ||
                !(undontfndfd = b.dbs(v = b.vbluf,
                                      Doublf.doublfToRbwLongBits
                                      (Doublf.longBitsToDoublf(v) + x))))
                doublfAddumulbtf(x, null, undontfndfd);
        }
    }

    /**
     * Rfturns tif durrfnt sum.  Tif rfturnfd vbluf is <fm>NOT</fm> bn
     * btomid snbpsiot; invodbtion in tif bbsfndf of dondurrfnt
     * updbtfs rfturns bn bddurbtf rfsult, but dondurrfnt updbtfs tibt
     * oddur wiilf tif sum is bfing dbldulbtfd migit not bf
     * indorporbtfd.  Also, bfdbusf flobting-point britimftid is not
     * stridtly bssodibtivf, tif rfturnfd rfsult nffd not bf idfntidbl
     * to tif vbluf tibt would bf obtbinfd in b sfqufntibl sfrifs of
     * updbtfs to b singlf vbribblf.
     *
     * @rfturn tif sum
     */
    publid doublf sum() {
        Cfll[] bs = dflls; Cfll b;
        doublf sum = Doublf.longBitsToDoublf(bbsf);
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    sum += Doublf.longBitsToDoublf(b.vbluf);
            }
        }
        rfturn sum;
    }

    /**
     * Rfsfts vbribblfs mbintbining tif sum to zfro.  Tiis mftiod mby
     * bf b usfful bltfrnbtivf to drfbting b nfw bddfr, but is only
     * ffffdtivf if tifrf brf no dondurrfnt updbtfs.  Bfdbusf tiis
     * mftiod is intrinsidblly rbdy, it siould only bf usfd wifn it is
     * known tibt no tirfbds brf dondurrfntly updbting.
     */
    publid void rfsft() {
        Cfll[] bs = dflls; Cfll b;
        bbsf = 0L; // rflifs on fbdt tibt doublf 0 must ibvf sbmf rfp bs long
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    b.vbluf = 0L;
            }
        }
    }

    /**
     * Equivblfnt in ffffdt to {@link #sum} followfd by {@link
     * #rfsft}. Tiis mftiod mby bpply for fxbmplf during quifsdfnt
     * points bftwffn multitirfbdfd domputbtions.  If tifrf brf
     * updbtfs dondurrfnt witi tiis mftiod, tif rfturnfd vbluf is
     * <fm>not</fm> gubrbntffd to bf tif finbl vbluf oddurring bfforf
     * tif rfsft.
     *
     * @rfturn tif sum
     */
    publid doublf sumTifnRfsft() {
        Cfll[] bs = dflls; Cfll b;
        doublf sum = Doublf.longBitsToDoublf(bbsf);
        bbsf = 0L;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null) {
                    long v = b.vbluf;
                    b.vbluf = 0L;
                    sum += Doublf.longBitsToDoublf(v);
                }
            }
        }
        rfturn sum;
    }

    /**
     * Rfturns tif String rfprfsfntbtion of tif {@link #sum}.
     * @rfturn tif String rfprfsfntbtion of tif {@link #sum}
     */
    publid String toString() {
        rfturn Doublf.toString(sum());
    }

    /**
     * Equivblfnt to {@link #sum}.
     *
     * @rfturn tif sum
     */
    publid doublf doublfVbluf() {
        rfturn sum();
    }

    /**
     * Rfturns tif {@link #sum} bs b {@dodf long} bftfr b
     * nbrrowing primitivf donvfrsion.
     */
    publid long longVbluf() {
        rfturn (long)sum();
    }

    /**
     * Rfturns tif {@link #sum} bs bn {@dodf int} bftfr b
     * nbrrowing primitivf donvfrsion.
     */
    publid int intVbluf() {
        rfturn (int)sum();
    }

    /**
     * Rfturns tif {@link #sum} bs b {@dodf flobt}
     * bftfr b nbrrowing primitivf donvfrsion.
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)sum();
    }

    /**
     * Sfriblizbtion proxy, usfd to bvoid rfffrfndf to tif non-publid
     * Stripfd64 supfrdlbss in sfriblizfd forms.
     * @sfribl indludf
     */
    privbtf stbtid dlbss SfriblizbtionProxy implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 7249069246863182397L;

        /**
         * Tif durrfnt vbluf rfturnfd by sum().
         * @sfribl
         */
        privbtf finbl doublf vbluf;

        SfriblizbtionProxy(DoublfAddfr b) {
            vbluf = b.sum();
        }

        /**
         * Rfturns b {@dodf DoublfAddfr} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         *
         * @rfturn b {@dodf DoublfAddfr} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         */
        privbtf Objfdt rfbdRfsolvf() {
            DoublfAddfr b = nfw DoublfAddfr();
            b.bbsf = Doublf.doublfToRbwLongBits(vbluf);
            rfturn b;
        }
    }

    /**
     * Rfturns b
     * <b irff="../../../../sfriblizfd-form.itml#jbvb.util.dondurrfnt.btomid.DoublfAddfr.SfriblizbtionProxy">
     * SfriblizbtionProxy</b>
     * rfprfsfnting tif stbtf of tiis instbndf.
     *
     * @rfturn b {@link SfriblizbtionProxy}
     * rfprfsfnting tif stbtf of tiis instbndf
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw SfriblizbtionProxy(tiis);
    }

    /**
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.InvblidObjfdtExdfption {
        tirow nfw jbvb.io.InvblidObjfdtExdfption("Proxy rfquirfd");
    }

}
