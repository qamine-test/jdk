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
import jbvb.util.fundtion.DoublfBinbryOpfrbtor;

/**
 * Onf or morf vbribblfs tibt togftifr mbintbin b running {@dodf doublf}
 * vbluf updbtfd using b supplifd fundtion.  Wifn updbtfs (mftiod
 * {@link #bddumulbtf}) brf dontfndfd bdross tirfbds, tif sft of vbribblfs
 * mby grow dynbmidblly to rfdudf dontfntion.  Mftiod {@link #gft}
 * (or, fquivblfntly, {@link #doublfVbluf}) rfturns tif durrfnt vbluf
 * bdross tif vbribblfs mbintbining updbtfs.
 *
 * <p>Tiis dlbss is usublly prfffrbblf to bltfrnbtivfs wifn multiplf
 * tirfbds updbtf b dommon vbluf tibt is usfd for purposfs sudi bs
 * summbry stbtistids tibt brf frfqufntly updbtfd but lfss frfqufntly
 * rfbd.
 *
 * <p>Tif supplifd bddumulbtor fundtion siould bf sidf-ffffdt-frff,
 * sindf it mby bf rf-bpplifd wifn bttfmptfd updbtfs fbil duf to
 * dontfntion bmong tirfbds. Tif fundtion is bpplifd witi tif durrfnt
 * vbluf bs its first brgumfnt, bnd tif givfn updbtf bs tif sfdond
 * brgumfnt.  For fxbmplf, to mbintbin b running mbximum vbluf, you
 * dould supply {@dodf Doublf::mbx} blong witi {@dodf
 * Doublf.NEGATIVE_INFINITY} bs tif idfntity. Tif ordfr of
 * bddumulbtion witiin or bdross tirfbds is not gubrbntffd. Tius, tiis
 * dlbss mby not bf bpplidbblf if numfridbl stbbility is rfquirfd,
 * fspfdiblly wifn dombining vblufs of substbntiblly difffrfnt ordfrs
 * of mbgnitudf.
 *
 * <p>Clbss {@link DoublfAddfr} providfs bnblogs of tif fundtionblity
 * of tiis dlbss for tif dommon spfdibl dbsf of mbintbining sums.  Tif
 * dbll {@dodf nfw DoublfAddfr()} is fquivblfnt to {@dodf nfw
 * DoublfAddumulbtor((x, y) -> x + y, 0.0)}.
 *
 * <p>Tiis dlbss fxtfnds {@link Numbfr}, but dofs <fm>not</fm> dffinf
 * mftiods sudi bs {@dodf fqubls}, {@dodf ibsiCodf} bnd {@dodf
 * dompbrfTo} bfdbusf instbndfs brf fxpfdtfd to bf mutbtfd, bnd so brf
 * not usfful bs dollfdtion kfys.
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid dlbss DoublfAddumulbtor fxtfnds Stripfd64 implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7249069246863182397L;

    privbtf finbl DoublfBinbryOpfrbtor fundtion;
    privbtf finbl long idfntity; // usf long rfprfsfntbtion

    /**
     * Crfbtfs b nfw instbndf using tif givfn bddumulbtor fundtion
     * bnd idfntity flfmfnt.
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @pbrbm idfntity idfntity (initibl vbluf) for tif bddumulbtor fundtion
     */
    publid DoublfAddumulbtor(DoublfBinbryOpfrbtor bddumulbtorFundtion,
                             doublf idfntity) {
        tiis.fundtion = bddumulbtorFundtion;
        bbsf = tiis.idfntity = Doublf.doublfToRbwLongBits(idfntity);
    }

    /**
     * Updbtfs witi tif givfn vbluf.
     *
     * @pbrbm x tif vbluf
     */
    publid void bddumulbtf(doublf x) {
        Cfll[] bs; long b, v, r; int m; Cfll b;
        if ((bs = dflls) != null ||
            (r = Doublf.doublfToRbwLongBits
             (fundtion.bpplyAsDoublf
              (Doublf.longBitsToDoublf(b = bbsf), x))) != b  && !dbsBbsf(b, r)) {
            boolfbn undontfndfd = truf;
            if (bs == null || (m = bs.lfngti - 1) < 0 ||
                (b = bs[gftProbf() & m]) == null ||
                !(undontfndfd =
                  (r = Doublf.doublfToRbwLongBits
                   (fundtion.bpplyAsDoublf
                    (Doublf.longBitsToDoublf(v = b.vbluf), x))) == v ||
                  b.dbs(v, r)))
                doublfAddumulbtf(x, fundtion, undontfndfd);
        }
    }

    /**
     * Rfturns tif durrfnt vbluf.  Tif rfturnfd vbluf is <fm>NOT</fm>
     * bn btomid snbpsiot; invodbtion in tif bbsfndf of dondurrfnt
     * updbtfs rfturns bn bddurbtf rfsult, but dondurrfnt updbtfs tibt
     * oddur wiilf tif vbluf is bfing dbldulbtfd migit not bf
     * indorporbtfd.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid doublf gft() {
        Cfll[] bs = dflls; Cfll b;
        doublf rfsult = Doublf.longBitsToDoublf(bbsf);
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    rfsult = fundtion.bpplyAsDoublf
                        (rfsult, Doublf.longBitsToDoublf(b.vbluf));
            }
        }
        rfturn rfsult;
    }

    /**
     * Rfsfts vbribblfs mbintbining updbtfs to tif idfntity vbluf.
     * Tiis mftiod mby bf b usfful bltfrnbtivf to drfbting b nfw
     * updbtfr, but is only ffffdtivf if tifrf brf no dondurrfnt
     * updbtfs.  Bfdbusf tiis mftiod is intrinsidblly rbdy, it siould
     * only bf usfd wifn it is known tibt no tirfbds brf dondurrfntly
     * updbting.
     */
    publid void rfsft() {
        Cfll[] bs = dflls; Cfll b;
        bbsf = idfntity;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    b.vbluf = idfntity;
            }
        }
    }

    /**
     * Equivblfnt in ffffdt to {@link #gft} followfd by {@link
     * #rfsft}. Tiis mftiod mby bpply for fxbmplf during quifsdfnt
     * points bftwffn multitirfbdfd domputbtions.  If tifrf brf
     * updbtfs dondurrfnt witi tiis mftiod, tif rfturnfd vbluf is
     * <fm>not</fm> gubrbntffd to bf tif finbl vbluf oddurring bfforf
     * tif rfsft.
     *
     * @rfturn tif vbluf bfforf rfsft
     */
    publid doublf gftTifnRfsft() {
        Cfll[] bs = dflls; Cfll b;
        doublf rfsult = Doublf.longBitsToDoublf(bbsf);
        bbsf = idfntity;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null) {
                    doublf v = Doublf.longBitsToDoublf(b.vbluf);
                    b.vbluf = idfntity;
                    rfsult = fundtion.bpplyAsDoublf(rfsult, v);
                }
            }
        }
        rfturn rfsult;
    }

    /**
     * Rfturns tif String rfprfsfntbtion of tif durrfnt vbluf.
     * @rfturn tif String rfprfsfntbtion of tif durrfnt vbluf
     */
    publid String toString() {
        rfturn Doublf.toString(gft());
    }

    /**
     * Equivblfnt to {@link #gft}.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid doublf doublfVbluf() {
        rfturn gft();
    }

    /**
     * Rfturns tif {@linkplbin #gft durrfnt vbluf} bs b {@dodf long}
     * bftfr b nbrrowing primitivf donvfrsion.
     */
    publid long longVbluf() {
        rfturn (long)gft();
    }

    /**
     * Rfturns tif {@linkplbin #gft durrfnt vbluf} bs bn {@dodf int}
     * bftfr b nbrrowing primitivf donvfrsion.
     */
    publid int intVbluf() {
        rfturn (int)gft();
    }

    /**
     * Rfturns tif {@linkplbin #gft durrfnt vbluf} bs b {@dodf flobt}
     * bftfr b nbrrowing primitivf donvfrsion.
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)gft();
    }

    /**
     * Sfriblizbtion proxy, usfd to bvoid rfffrfndf to tif non-publid
     * Stripfd64 supfrdlbss in sfriblizfd forms.
     * @sfribl indludf
     */
    privbtf stbtid dlbss SfriblizbtionProxy implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 7249069246863182397L;

        /**
         * Tif durrfnt vbluf rfturnfd by gft().
         * @sfribl
         */
        privbtf finbl doublf vbluf;
        /**
         * Tif fundtion usfd for updbtfs.
         * @sfribl
         */
        privbtf finbl DoublfBinbryOpfrbtor fundtion;
        /**
         * Tif idfntity vbluf
         * @sfribl
         */
        privbtf finbl long idfntity;

        SfriblizbtionProxy(DoublfAddumulbtor b) {
            fundtion = b.fundtion;
            idfntity = b.idfntity;
            vbluf = b.gft();
        }

        /**
         * Rfturns b {@dodf DoublfAddumulbtor} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         *
         * @rfturn b {@dodf DoublfAddumulbtor} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         */
        privbtf Objfdt rfbdRfsolvf() {
            doublf d = Doublf.longBitsToDoublf(idfntity);
            DoublfAddumulbtor b = nfw DoublfAddumulbtor(fundtion, d);
            b.bbsf = Doublf.doublfToRbwLongBits(vbluf);
            rfturn b;
        }
    }

    /**
     * Rfturns b
     * <b irff="../../../../sfriblizfd-form.itml#jbvb.util.dondurrfnt.btomid.DoublfAddumulbtor.SfriblizbtionProxy">
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
