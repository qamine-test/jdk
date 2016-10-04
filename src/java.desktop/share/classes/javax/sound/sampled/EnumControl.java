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
 * A {@dodf EnumControl} providfs dontrol ovfr b sft of disdrftf possiblf vblufs
 * , fbdi rfprfsfntfd by bn objfdt. In b grbpiidbl usfr intfrfbdf, sudi b
 * dontrol migit bf rfprfsfntfd by b sft of buttons, fbdi of wiidi dioosfs onf
 * vbluf or sftting. For fxbmplf, b rfvfrb dontrol migit providf sfvfrbl prfsft
 * rfvfrbfrbtion sfttings, instfbd of providing dontinuously bdjustbblf
 * pbrbmftfrs of tif sort tibt would bf rfprfsfntfd by {@link FlobtControl}
 * objfdts.
 * <p>
 * Controls tibt providf b dioidf bftwffn only two sfttings dbn oftfn bf
 * implfmfntfd instfbd bs b {@link BoolfbnControl}, bnd dontrols tibt providf b
 * sft of vblufs blong somf qubntifibblf dimfnsion migit bf implfmfntfd instfbd
 * bs b {@dodf FlobtControl} witi b dobrsf rfsolution. Howfvfr, b kfy ffbturf of
 * {@dodf EnumControl} is tibt tif rfturnfd vblufs brf brbitrbry objfdts, rbtifr
 * tibn numfridbl or boolfbn vblufs. Tiis mfbns tibt fbdi rfturnfd objfdt dbn
 * providf furtifr informbtion. As bn fxbmplf, tif sfttings of b
 * {@link EnumControl.Typf#REVERB REVERB} dontrol brf instbndfs of
 * {@link RfvfrbTypf} tibt dbn bf qufrifd for tif pbrbmftfr vblufs usfd for fbdi
 * sftting.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss EnumControl fxtfnds Control {

    /**
     * Tif sft of possiblf vblufs.
     */
    privbtf Objfdt[] vblufs;

    /**
     * Tif durrfnt vbluf.
     */
    privbtf Objfdt vbluf;

    /**
     * Construdts b nfw fnumfrbtfd dontrol objfdt witi tif givfn pbrbmftfrs.
     *
     * @pbrbm  typf tif typf of dontrol rfprfsfntfd tiis fnumfrbtfd dontrol
     *         objfdt
     * @pbrbm  vblufs tif sft of possiblf vblufs for tif dontrol
     * @pbrbm  vbluf tif initibl dontrol vbluf
     */
    protfdtfd EnumControl(Typf typf, Objfdt[] vblufs, Objfdt vbluf) {
        supfr(typf);
        tiis.vblufs = vblufs;
        tiis.vbluf = vbluf;
    }

    /**
     * Sfts tif durrfnt vbluf for tif dontrol. Tif dffbult implfmfntbtion simply
     * sfts tif vbluf bs indidbtfd. If tif vbluf indidbtfd is not supportfd, bn
     * {@dodf IllfgblArgumfntExdfption} is tirown. Somf dontrols rfquirf tibt
     * tifir linf bf opfn bfforf tify dbn bf bfffdtfd by sftting b vbluf.
     *
     * @pbrbm  vbluf tif dfsirfd nfw vbluf
     * @tirows IllfgblArgumfntExdfption if tif vbluf indidbtfd dofs not fbll
     *         witiin tif bllowbblf rbngf
     */
    publid void sftVbluf(Objfdt vbluf) {
        if (!isVblufSupportfd(vbluf)) {
            tirow nfw IllfgblArgumfntExdfption("Rfqufstfd vbluf " + vbluf + " is not supportfd.");
        }

        tiis.vbluf = vbluf;
    }

    /**
     * Obtbins tiis dontrol's durrfnt vbluf.
     *
     * @rfturn tif durrfnt vbluf
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif sft of possiblf vblufs for tiis dontrol.
     *
     * @rfturn tif sft of possiblf vblufs
     */
    publid Objfdt[] gftVblufs() {

        Objfdt[] lodblArrby = nfw Objfdt[vblufs.lfngti];

        for (int i = 0; i < vblufs.lfngti; i++) {
            lodblArrby[i] = vblufs[i];
        }

        rfturn lodblArrby;
    }

    /**
     * Indidbtfs wiftifr tif vbluf spfdififd is supportfd.
     *
     * @pbrbm  vbluf tif vbluf for wiidi support is qufrifd
     * @rfturn {@dodf truf} if tif vbluf is supportfd, otifrwisf {@dodf fblsf}
     */
    privbtf boolfbn isVblufSupportfd(Objfdt vbluf) {

        for (int i = 0; i < vblufs.lfngti; i++) {
            //$$fb 2001-07-20: Fix for bug 4400392: sftVbluf() in RfvfrbControl blwbys tirows Exdfption
            //if (vblufs.fqubls(vblufs[i])) {
            if (vbluf.fqubls(vblufs[i])) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Providfs b string rfprfsfntbtion of tif dontrol.
     *
     * @rfturn b string dfsdription
     */
    @Ovfrridf
    publid String toString() {
        rfturn nfw String(gftTypf() + " witi durrfnt vbluf: " + gftVbluf());
    }

    /**
     * An instbndf of tif {@dodf EnumControl.Typf} innfr dlbss idfntififs onf
     * kind of fnumfrbtfd dontrol. Stbtid instbndfs brf providfd for tif dommon
     * typfs.
     *
     * @butior Kbrb Kytlf
     * @sff EnumControl
     * @sindf 1.3
     */
    publid stbtid dlbss Typf fxtfnds Control.Typf {

        /**
         * Rfprfsfnts b dontrol ovfr b sft of possiblf rfvfrbfrbtion sfttings.
         * Ebdi rfvfrbfrbtion sftting is dfsdribfd by bn instbndf of tif
         * {@link RfvfrbTypf} dlbss. (To bddfss tifsf sfttings, invokf
         * {@link EnumControl#gftVblufs} on bn fnumfrbtfd dontrol of typf
         * {@dodf REVERB}.)
         */
        publid stbtid finbl Typf REVERB         = nfw Typf("Rfvfrb");

        /**
         * Construdts b nfw fnumfrbtfd dontrol typf.
         *
         * @pbrbm  nbmf tif nbmf of tif nfw fnumfrbtfd dontrol typf
         */
        protfdtfd Typf(finbl String nbmf) {
            supfr(nbmf);
        }
    }
}
