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
import jbvb.util.fundtion.IntUnbryOpfrbtor;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import sun.misd.Unsbff;

/**
 * An {@dodf int} vbluf tibt mby bf updbtfd btomidblly.  Sff tif
 * {@link jbvb.util.dondurrfnt.btomid} pbdkbgf spfdifidbtion for
 * dfsdription of tif propfrtifs of btomid vbribblfs. An
 * {@dodf AtomidIntfgfr} is usfd in bpplidbtions sudi bs btomidblly
 * indrfmfntfd dountfrs, bnd dbnnot bf usfd bs b rfplbdfmfnt for bn
 * {@link jbvb.lbng.Intfgfr}. Howfvfr, tiis dlbss dofs fxtfnd
 * {@dodf Numbfr} to bllow uniform bddfss by tools bnd utilitifs tibt
 * dfbl witi numfridblly-bbsfd dlbssfs.
 *
 * @sindf 1.5
 * @butior Doug Lfb
*/
publid dlbss AtomidIntfgfr fxtfnds Numbfr implfmfnts jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 6214790243416807050L;

    // sftup to usf Unsbff.dompbrfAndSwbpInt for updbtfs
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid finbl long vblufOffsft;

    stbtid {
        try {
            vblufOffsft = unsbff.objfdtFifldOffsft
                (AtomidIntfgfr.dlbss.gftDfdlbrfdFifld("vbluf"));
        } dbtdi (Exdfption fx) { tirow nfw Error(fx); }
    }

    privbtf volbtilf int vbluf;

    /**
     * Crfbtfs b nfw AtomidIntfgfr witi tif givfn initibl vbluf.
     *
     * @pbrbm initiblVbluf tif initibl vbluf
     */
    publid AtomidIntfgfr(int initiblVbluf) {
        vbluf = initiblVbluf;
    }

    /**
     * Crfbtfs b nfw AtomidIntfgfr witi initibl vbluf {@dodf 0}.
     */
    publid AtomidIntfgfr() {
    }

    /**
     * Gfts tif durrfnt vbluf.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid finbl int gft() {
        rfturn vbluf;
    }

    /**
     * Sfts to tif givfn vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     */
    publid finbl void sft(int nfwVbluf) {
        vbluf = nfwVbluf;
    }

    /**
     * Evfntublly sfts to tif givfn vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     * @sindf 1.6
     */
    publid finbl void lbzySft(int nfwVbluf) {
        unsbff.putOrdfrfdInt(tiis, vblufOffsft, nfwVbluf);
    }

    /**
     * Atomidblly sfts to tif givfn vbluf bnd rfturns tif old vbluf.
     *
     * @pbrbm nfwVbluf tif nfw vbluf
     * @rfturn tif prfvious vbluf
     */
    publid finbl int gftAndSft(int nfwVbluf) {
        rfturn unsbff.gftAndSftInt(tiis, vblufOffsft, nfwVbluf);
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
    publid finbl boolfbn dompbrfAndSft(int fxpfdt, int updbtf) {
        rfturn unsbff.dompbrfAndSwbpInt(tiis, vblufOffsft, fxpfdt, updbtf);
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
    publid finbl boolfbn wfbkCompbrfAndSft(int fxpfdt, int updbtf) {
        rfturn unsbff.dompbrfAndSwbpInt(tiis, vblufOffsft, fxpfdt, updbtf);
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif prfvious vbluf
     */
    publid finbl int gftAndIndrfmfnt() {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, 1);
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif prfvious vbluf
     */
    publid finbl int gftAndDfdrfmfnt() {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, -1);
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf.
     *
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif prfvious vbluf
     */
    publid finbl int gftAndAdd(int dfltb) {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, dfltb);
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif updbtfd vbluf
     */
    publid finbl int indrfmfntAndGft() {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, 1) + 1;
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf.
     *
     * @rfturn tif updbtfd vbluf
     */
    publid finbl int dfdrfmfntAndGft() {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, -1) - 1;
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf.
     *
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif updbtfd vbluf
     */
    publid finbl int bddAndGft(int dfltb) {
        rfturn unsbff.gftAndAddInt(tiis, vblufOffsft, dfltb) + dfltb;
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
    publid finbl int gftAndUpdbtf(IntUnbryOpfrbtor updbtfFundtion) {
        int prfv, nfxt;
        do {
            prfv = gft();
            nfxt = updbtfFundtion.bpplyAsInt(prfv);
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
    publid finbl int updbtfAndGft(IntUnbryOpfrbtor updbtfFundtion) {
        int prfv, nfxt;
        do {
            prfv = gft();
            nfxt = updbtfFundtion.bpplyAsInt(prfv);
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
    publid finbl int gftAndAddumulbtf(int x,
                                      IntBinbryOpfrbtor bddumulbtorFundtion) {
        int prfv, nfxt;
        do {
            prfv = gft();
            nfxt = bddumulbtorFundtion.bpplyAsInt(prfv, x);
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
    publid finbl int bddumulbtfAndGft(int x,
                                      IntBinbryOpfrbtor bddumulbtorFundtion) {
        int prfv, nfxt;
        do {
            prfv = gft();
            nfxt = bddumulbtorFundtion.bpplyAsInt(prfv, x);
        } wiilf (!dompbrfAndSft(prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Rfturns tif String rfprfsfntbtion of tif durrfnt vbluf.
     * @rfturn tif String rfprfsfntbtion of tif durrfnt vbluf
     */
    publid String toString() {
        rfturn Intfgfr.toString(gft());
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidIntfgfr} bs bn {@dodf int}.
     */
    publid int intVbluf() {
        rfturn gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidIntfgfr} bs b {@dodf long}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid long longVbluf() {
        rfturn (long)gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidIntfgfr} bs b {@dodf flobt}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid flobt flobtVbluf() {
        rfturn (flobt)gft();
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf AtomidIntfgfr} bs b {@dodf doublf}
     * bftfr b widfning primitivf donvfrsion.
     * @jls 5.1.2 Widfning Primitivf Convfrsions
     */
    publid doublf doublfVbluf() {
        rfturn (doublf)gft();
    }

}
