/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd;

/**
 * A {@dodf CompoundControl}, sudi bs b grbpiid fqublizfr, providfs dontrol ovfr
 * two or morf rflbtfd propfrtifs, fbdi of wiidi is itsflf rfprfsfntfd bs b
 * {@dodf Control}.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss CompoundControl fxtfnds Control {

    /**
     * Tif sft of mfmbfr dontrols.
     */
    privbtf Control[] dontrols;

    /**
     * Construdts b nfw dompound dontrol objfdt witi tif givfn pbrbmftfrs.
     *
     * @pbrbm  typf tif typf of dontrol rfprfsfntfd tiis dompound dontrol objfdt
     * @pbrbm  mfmbfrControls tif sft of mfmbfr dontrols
     */
    protfdtfd CompoundControl(Typf typf, Control[] mfmbfrControls) {
        supfr(typf);
        tiis.dontrols = mfmbfrControls;
    }

    /**
     * Rfturns tif sft of mfmbfr dontrols tibt domprisf tif dompound dontrol.
     *
     * @rfturn tif sft of mfmbfr dontrols
     */
    publid Control[] gftMfmbfrControls() {
        Control[] lodblArrby = nfw Control[dontrols.lfngti];

        for (int i = 0; i < dontrols.lfngti; i++) {
            lodblArrby[i] = dontrols[i];
        }

        rfturn lodblArrby;
    }

    /**
     * Providfs b string rfprfsfntbtion of tif dontrol.
     *
     * @rfturn b string dfsdription
     */
    @Ovfrridf
    publid String toString() {

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < dontrols.lfngti; i++) {
            if (i != 0) {
                sb.bppfnd(", ");
                if ((i + 1) == dontrols.lfngti) {
                    sb.bppfnd("bnd ");
                }
            }
            sb.bppfnd(dontrols[i].gftTypf());
        }

        rfturn nfw String(gftTypf() + " Control dontbining " + sb + " Controls.");
    }

    /**
     * An instbndf of tif {@dodf CompoundControl.Typf} innfr dlbss idfntififs
     * onf kind of dompound dontrol. Stbtid instbndfs brf providfd for tif
     * dommon typfs.
     *
     * @butior Kbrb Kytlf
     * @sindf 1.3
     */
    publid stbtid dlbss Typf fxtfnds Control.Typf {

        /**
         * Construdts b nfw dompound dontrol typf.
         *
         * @pbrbm  nbmf tif nbmf of tif nfw dompound dontrol typf
         */
        protfdtfd Typf(finbl String nbmf) {
            supfr(nbmf);
        }
    }
}
