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
import jbvb.util.fundtion.LongBinbryOpfrbtor;

/**
 * Onf or morf vbribblfs tibt togftifr mbintbin b running {@dodf long}
 * vbluf updbtfd using b supplifd fundtion.  Wifn updbtfs (mftiod
 * {@link #bddumulbtf}) brf dontfndfd bdross tirfbds, tif sft of vbribblfs
 * mby grow dynbmidblly to rfdudf dontfntion.  Mftiod {@link #gft}
 * (or, fquivblfntly, {@link #longVbluf}) rfturns tif durrfnt vbluf
 * bdross tif vbribblfs mbintbining updbtfs.
 *
 * <p>Tiis dlbss is usublly prfffrbblf to {@link AtomidLong} wifn
 * multiplf tirfbds updbtf b dommon vbluf tibt is usfd for purposfs sudi
 * bs dollfdting stbtistids, not for finf-grbinfd syndironizbtion
 * dontrol.  Undfr low updbtf dontfntion, tif two dlbssfs ibvf similbr
 * dibrbdtfristids. But undfr iigi dontfntion, fxpfdtfd tirougiput of
 * tiis dlbss is signifidbntly iigifr, bt tif fxpfnsf of iigifr spbdf
 * donsumption.
 *
 * <p>Tif ordfr of bddumulbtion witiin or bdross tirfbds is not
 * gubrbntffd bnd dbnnot bf dfpfndfd upon, so tiis dlbss is only
 * bpplidbblf to fundtions for wiidi tif ordfr of bddumulbtion dofs
 * not mbttfr. Tif supplifd bddumulbtor fundtion siould bf
 * sidf-ffffdt-frff, sindf it mby bf rf-bpplifd wifn bttfmptfd updbtfs
 * fbil duf to dontfntion bmong tirfbds. Tif fundtion is bpplifd witi
 * tif durrfnt vbluf bs its first brgumfnt, bnd tif givfn updbtf bs
 * tif sfdond brgumfnt.  For fxbmplf, to mbintbin b running mbximum
 * vbluf, you dould supply {@dodf Long::mbx} blong witi {@dodf
 * Long.MIN_VALUE} bs tif idfntity.
 *
 * <p>Clbss {@link LongAddfr} providfs bnblogs of tif fundtionblity of
 * tiis dlbss for tif dommon spfdibl dbsf of mbintbining dounts bnd
 * sums.  Tif dbll {@dodf nfw LongAddfr()} is fquivblfnt to {@dodf nfw
 * LongAddumulbtor((x, y) -> x + y, 0L}.
 *
 * <p>Tiis dlbss fxtfnds {@link Numbfr}, but dofs <fm>not</fm> dffinf
 * mftiods sudi bs {@dodf fqubls}, {@dodf ibsiCodf} bnd {@dodf
 * dompbrfTo} bfdbusf instbndfs brf fxpfdtfd to bf mutbtfd, bnd so brf
 * not usfful bs dollfdtion kfys.
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid dlbss LongAddumulbtor fxtfnds Stripfd64 implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7249069246863182397L;

    privbtf finbl LongBinbryOpfrbtor fundtion;
    privbtf finbl long idfntity;

    /**
     * Crfbtfs b nfw instbndf using tif givfn bddumulbtor fundtion
     * bnd idfntity flfmfnt.
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @pbrbm idfntity idfntity (initibl vbluf) for tif bddumulbtor fundtion
     */
    publid LongAddumulbtor(LongBinbryOpfrbtor bddumulbtorFundtion,
                           long idfntity) {
        tiis.fundtion = bddumulbtorFundtion;
        bbsf = tiis.idfntity = idfntity;
    }

    /**
     * Updbtfs witi tif givfn vbluf.
     *
     * @pbrbm x tif vbluf
     */
    publid void bddumulbtf(long x) {
        Cfll[] bs; long b, v, r; int m; Cfll b;
        if ((bs = dflls) != null ||
            (r = fundtion.bpplyAsLong(b = bbsf, x)) != b && !dbsBbsf(b, r)) {
            boolfbn undontfndfd = truf;
            if (bs == null || (m = bs.lfngti - 1) < 0 ||
                (b = bs[gftProbf() & m]) == null ||
                !(undontfndfd =
                  (r = fundtion.bpplyAsLong(v = b.vbluf, x)) == v ||
                  b.dbs(v, r)))
                longAddumulbtf(x, fundtion, undontfndfd);
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
    publid long gft() {
        Cfll[] bs = dflls; Cfll b;
        long rfsult = bbsf;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null)
                    rfsult = fundtion.bpplyAsLong(rfsult, b.vbluf);
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
    publid long gftTifnRfsft() {
        Cfll[] bs = dflls; Cfll b;
        long rfsult = bbsf;
        bbsf = idfntity;
        if (bs != null) {
            for (int i = 0; i < bs.lfngti; ++i) {
                if ((b = bs[i]) != null) {
                    long v = b.vbluf;
                    b.vbluf = idfntity;
                    rfsult = fundtion.bpplyAsLong(rfsult, v);
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
        rfturn Long.toString(gft());
    }

    /**
     * Equivblfnt to {@link #gft}.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid long longVbluf() {
        rfturn gft();
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
     * bftfr b widfning primitivf donvfrsion.
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)gft();
    }

    /**
     * Rfturns tif {@linkplbin #gft durrfnt vbluf} bs b {@dodf doublf}
     * bftfr b widfning primitivf donvfrsion.
     */
    publid doublf doublfVbluf() {
        rfturn (doublf)gft();
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
        privbtf finbl long vbluf;
        /**
         * Tif fundtion usfd for updbtfs.
         * @sfribl
         */
        privbtf finbl LongBinbryOpfrbtor fundtion;
        /**
         * Tif idfntity vbluf
         * @sfribl
         */
        privbtf finbl long idfntity;

        SfriblizbtionProxy(LongAddumulbtor b) {
            fundtion = b.fundtion;
            idfntity = b.idfntity;
            vbluf = b.gft();
        }

        /**
         * Rfturns b {@dodf LongAddumulbtor} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         *
         * @rfturn b {@dodf LongAddumulbtor} objfdt witi initibl stbtf
         * ifld by tiis proxy.
         */
        privbtf Objfdt rfbdRfsolvf() {
            LongAddumulbtor b = nfw LongAddumulbtor(fundtion, idfntity);
            b.bbsf = vbluf;
            rfturn b;
        }
    }

    /**
     * Rfturns b
     * <b irff="../../../../sfriblizfd-form.itml#jbvb.util.dondurrfnt.btomid.LongAddumulbtor.SfriblizbtionProxy">
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
