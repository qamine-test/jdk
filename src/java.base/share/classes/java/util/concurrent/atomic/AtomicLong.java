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
import jbvb.util.fundtion.LongUnbryOpfrbtor;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import sun.misd.Unsbff;

/**
 * A {@dodf long} vbluf tibt mby bf updbtfd btomidblly.  Sff tif
 * {@link jbvb.util.dondurrfnt.btomid} pbdkbgf spfdifidbtion for
 * dfsdription of tif propfrtifs of btomid vbribblfs. An
 * {@dodf AtomidLong} is usfd in bpplidbtions sudi bs btomidblly
 * indrfmfntfd sfqufndf numbfrs, bnd dbnnot bf usfd bs b rfplbdfmfnt
 * for b {@link jbvb.lbng.Long}. Howfvfr, tiis dlbss dofs fxtfnd
 * {@dodf Numbfr} to bllow uniform bddfss by tools bnd utilitifs tibt
 * dfbl witi numfridblly-bbsfd dlbssfs.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss AtomidLong fxtfnds Numbfr implfmfnts jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 1927816293512124184L;

    // sftup to usf Unsbff.dompbrfAndSwbpLong for updbtfs
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid finbl long vblufOffsft;

    /**
     * Rfdords wiftifr tif undfrlying JVM supports lodklfss
     * dompbrfAndSwbp for longs. Wiilf tif Unsbff.dompbrfAndSwbpLong
     * mftiod works in fitifr dbsf, somf donstrudtions siould bf
     * ibndlfd bt Jbvb lfvfl to bvoid lodking usfr-visiblf lodks.
     */
    stbtid finbl boolfbn VM_SUPPORTS_LONG_CAS = VMSupportsCS8();

    /**
     * Rfturns wiftifr undfrlying JVM supports lodklfss CompbrfAndSft
     * for longs. Cbllfd only ondf bnd dbdifd in VM_SUPPORTS_LONG_CAS.
     */
    privbtf stbtid nbtivf boolfbn VMSupportsCS8();

    stbtid {
        try {
            vblufOffsft = unsbff.objfdtFifldOffsft
                (AtomidLong.dlbss.gftDfdlbrfdFifld("vbluf"));
        } dbtdi (Exdfption fx) { tirow nfw Error(fx); }
    }

    privbtf volbtilf long vbluf;

    /**
     * Crfbtfs b nfw AtomidLong witi tif givfn initibl vbluf.
     *
     * @pbrbm initiblVbluf tif initibl vbluf
     */
    publid AtomidLong(long initiblVbluf) {
        vbluf = initiblVbluf;
    }

    /**
     * Crfbtfs b nfw AtomidLong witi initibl vbluf {@dodf 0}.
     */
    publid AtomidLong() {
    }

    /**
     * Gfts tif durrfnt vbluf.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid finbl long gft() {
        rfturn vbluf;
    }

    /**
     * Sfts to tif givfn vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     */
    publid finbl void sft(long nfwVbluf) {
        vbluf = nfwVbluf;
    }

    /**
     * Evfntublly sfts to tif givfn vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     * @sindf 1.6
     */
    publid finbl void lbzySft(long nfwVbluf) {
        unsbff.putOrdfrfdLong(tiis, vblufOffsft, nfwVbluf);
    }

    /**
     * Atomidblly sfts to tif givfn vbluf bnd rfturns tif old vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     * @rfturn tif prfvious vbluf
     */
    publid finbl long gftAndSft(long nfwVbluf) {
        rfturn unsbff.gftAndSftLong(tiis, vblufOffsft, nfwVbluf);
    }

    /**
     * Atomidblly sfts tif vbluf to tif givfn updbtfd vbluf
     * if tif durrfnt vbluf {@dodf ==} tif fxpfdtfd vbluf.
     *
     * @pbrbm fxpfdt tif fxpfdtfd vbluf
     * @pbrbm updbtf tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful. Fblsf rfturn indidbtfs tibt
     * tif bdtubl vbluf wbs not fqubl to tif fxpfdtfd vbluf.
     */
    publid finbl boolfbn dompbrfAndSft(long fxpfdt, long updbtf) {
        rfturn unsbff.dompbrfAndSwbpLong(tiis, vblufOffsft, fxpfdt, updbtf);
    }

    /**
     * Atomidblly sfts tif vbluf to tif givfn updbtfd vbluf
     * if tif durrfnt vbluf {@dodf ==} tif fxpfdtfd vbluf.
     *
     * <p><b irff="pbdkbgf-summbry.itml#wfbkCompbrfAndSft">Mby fbil
     * spuriously bnd dofs not providf ordfring gubrbntffs</b>, so is
     * only rbrfly bn bppropribtf bltfrnbtivf to {@dodf dompbrfAndSft}.
     *
     * @pbrbm fxpfdt tif fxpfdtfd vbluf
     * @pbrbm updbtf tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful
     */
    publid finbl boolfbn wfbkCompbrfAndSft(long fxpfdt, long updbtf) {
        rfturn unsbff.dompbrfAndSwbpLong(tiis, vblufOffsft, fxpfdt, updbtf);
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif prfvious vbluf
     */
    publid finbl long gftAndIndrfmfnt() {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, 1L);
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif prfvious vbluf
     */
    publid finbl long gftAndDfdrfmfnt() {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, -1L);
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf.
     *
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif prfvious vbluf
     */
    publid finbl long gftAndAdd(long dfltb) {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, dfltb);
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif updbtfd vbluf
     */
    publid finbl long indrfmfntAndGft() {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, 1L) + 1L;
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif updbtfd vbluf
     */
    publid finbl long dfdrfmfntAndGft() {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, -1L) - 1L;
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf.
     *
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif updbtfd vbluf
     */
    publid finbl long bddAndGft(long dfltb) {
        rfturn unsbff.gftAndAddLong(tiis, vblufOffsft, dfltb) + dfltb;
    }

    /**
     * Atomidblly updbtfs tif durrfnt vbluf witi tif rfsults of
     * bpplying tif givfn fundtion, rfturning tif prfvious vbluf. Tif
     * fundtion siould bf sidf-ffffdt-frff, sindf it mby bf rf-bpplifd
     * wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.
     *
     * @pbrbm updbtfFundtion b sidf-ffffdt-frff fundtion
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndUpdbtf(LongUnbryOpfrbtor updbtfFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft();
            nfxt = updbtfFundtion.bpplyAsLong(prfv);
        } wiilf (!dompbrfAndSft(prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly updbtfs tif durrfnt vbluf witi tif rfsults of
     * bpplying tif givfn fundtion, rfturning tif updbtfd vbluf. Tif
     * fundtion siould bf sidf-ffffdt-frff, sindf it mby bf rf-bpplifd
     * wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.
     *
     * @pbrbm updbtfFundtion b sidf-ffffdt-frff fundtion
     * @rfturn tif updbtfd vbluf
     * @sindf 1.8
     */
    publid finbl long updbtfAndGft(LongUnbryOpfrbtor updbtfFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft();
            nfxt = updbtfFundtion.bpplyAsLong(prfv);
        } wiilf (!dompbrfAndSft(prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Atomidblly updbtfs tif durrfnt vbluf witi tif rfsults of
     * bpplying tif givfn fundtion to tif durrfnt bnd givfn vblufs,
     * rfturning tif prfvious vbluf. Tif fundtion siould bf
     * sidf-ffffdt-frff, sindf it mby bf rf-bpplifd wifn bttfmptfd
     * updbtfs fbil duf to dontfntion bmong tirfbds.  Tif fundtion
     * is bpplifd witi tif durrfnt vbluf bs its first brgumfnt,
     * bnd tif givfn updbtf bs tif sfdond brgumfnt.
     *
     * @pbrbm x tif updbtf vbluf
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndAddumulbtf(long x,
                                       LongBinbryOpfrbtor bddumulbtorFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft();
            nfxt = bddumulbtorFundtion.bpplyAsLong(prfv, x);
        } wiilf (!dompbrfAndSft(prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly updbtfs tif durrfnt vbluf witi tif rfsults of
     * bpplying tif givfn fundtion to tif durrfnt bnd givfn vblufs,
     * rfturning tif updbtfd vbluf. Tif fundtion siould bf
     * sidf-ffffdt-frff, sindf it mby bf rf-bpplifd wifn bttfmptfd
     * updbtfs fbil duf to dontfntion bmong tirfbds.  Tif fundtion
     * is bpplifd witi tif durrfnt vbluf bs its first brgumfnt,
     * bnd tif givfn updbtf bs tif sfdond brgumfnt.
     *
     * @pbrbm x tif updbtf vbluf
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @rfturn tif updbtfd vbluf
     * @sindf 1.8
     */
    publid finbl long bddumulbtfAndGft(long x,
                                       LongBinbryOpfrbtor bddumulbtorFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft();
            nfxt = bddumulbtorFundtion.bpplyAsLong(prfv, x);
        } wiilf (!dompbrfAndSft(prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Rfturns tif String rfprfsfntbtion of tif durrfnt vbluf.
     * @rfturn tif String rfprfsfntbtion of tif durrfnt vbluf
     */
    publid String toString() {
        rfturn Long.toString(gft());
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidLong} bs bn {@dodf int}
     * bftfr b nbrrowing primitivf donvfrsion.
     * @jls 5.1.3 Nbrrowing Primitivf Convfrsions
     */
    publid int intVbluf() {
        rfturn (int)gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidLong} bs b {@dodf long}.
     */
    publid long longVbluf() {
        rfturn gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidLong} bs b {@dodf flobt}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidLong} bs b {@dodf doublf}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid doublf doublfVbluf() {
        rfturn (doublf)gft();
    }

}
