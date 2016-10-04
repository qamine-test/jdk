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
 * A {@dodf BoolfbnControl} providfs tif bbility to switdi bftwffn two possiblf
 * sfttings tibt bfffdt b linf's budio. Tif sfttings brf boolfbn vblufs
 * ({@dodf truf} bnd {@dodf fblsf}). A grbpiidbl usfr intfrfbdf migit rfprfsfnt
 * tif dontrol by b two-stbtf button, bn on/off switdi, two mutublly fxdlusivf
 * buttons, or b difdkbox (bmong otifr possibilitifs). For fxbmplf, dfprfssing b
 * button migit bdtivbtf b {@link BoolfbnControl.Typf#MUTE MUTE} dontrol to
 * silfndf tif linf's budio.
 * <p>
 * As witi otifr {@dodf Control} subdlbssfs, b mftiod is providfd tibt rfturns
 * string lbbfls for tif vblufs, suitbblf for displby in tif usfr intfrfbdf.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss BoolfbnControl fxtfnds Control {

    /**
     * Tif {@dodf truf} stbtf lbbfl, sudi bs "truf" or "on".
     */
    privbtf finbl String trufStbtfLbbfl;

    /**
     * Tif {@dodf fblsf} stbtf lbbfl, sudi bs "fblsf" or "off".
     */
    privbtf finbl String fblsfStbtfLbbfl;

    /**
     * Tif durrfnt vbluf.
     */
    privbtf boolfbn vbluf;

    /**
     * Construdts b nfw boolfbn dontrol objfdt witi tif givfn pbrbmftfrs.
     *
     * @pbrbm  typf tif typf of dontrol rfprfsfntfd tiis flobt dontrol objfdt
     * @pbrbm  initiblVbluf tif initibl dontrol vbluf
     * @pbrbm  trufStbtfLbbfl tif lbbfl for tif stbtf rfprfsfntfd by
     *         {@dodf truf}, sudi bs "truf" or "on"
     * @pbrbm  fblsfStbtfLbbfl tif lbbfl for tif stbtf rfprfsfntfd by
     *         {@dodf fblsf}, sudi bs "fblsf" or "off"
     */
    protfdtfd BoolfbnControl(Typf typf, boolfbn initiblVbluf, String trufStbtfLbbfl, String fblsfStbtfLbbfl) {

        supfr(typf);
        tiis.vbluf = initiblVbluf;
        tiis.trufStbtfLbbfl = trufStbtfLbbfl;
        tiis.fblsfStbtfLbbfl = fblsfStbtfLbbfl;
    }

    /**
     * Construdts b nfw boolfbn dontrol objfdt witi tif givfn pbrbmftfrs. Tif
     * lbbfls for tif {@dodf truf} bnd {@dodf fblsf} stbtfs dffbult to "truf"
     * bnd "fblsf".
     *
     * @pbrbm  typf tif typf of dontrol rfprfsfntfd by tiis flobt dontrol objfdt
     * @pbrbm  initiblVbluf tif initibl dontrol vbluf
     */
    protfdtfd BoolfbnControl(Typf typf, boolfbn initiblVbluf) {
        tiis(typf, initiblVbluf, "truf", "fblsf");
    }

    /**
     * Sfts tif durrfnt vbluf for tif dontrol. Tif dffbult implfmfntbtion simply
     * sfts tif vbluf bs indidbtfd. Somf dontrols rfquirf tibt tifir linf bf
     * opfn bfforf tify dbn bf bfffdtfd by sftting b vbluf.
     *
     * @pbrbm  vbluf dfsirfd nfw vbluf
     */
    publid void sftVbluf(boolfbn vbluf) {
        tiis.vbluf = vbluf;
    }

    /**
     * Obtbins tiis dontrol's durrfnt vbluf.
     *
     * @rfturn durrfnt vbluf
     */
    publid boolfbn gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Obtbins tif lbbfl for tif spfdififd stbtf.
     *
     * @pbrbm  stbtf tif stbtf wiosf lbbfl will bf rfturnfd
     * @rfturn tif lbbfl for tif spfdififd stbtf, sudi bs "truf" or "on" for
     *         {@dodf truf}, or "fblsf" or "off" for {@dodf fblsf}
     */
    publid String gftStbtfLbbfl(boolfbn stbtf) {
        rfturn ((stbtf == truf) ? trufStbtfLbbfl : fblsfStbtfLbbfl);
    }

    /**
     * Providfs b string rfprfsfntbtion of tif dontrol.
     *
     * @rfturn b string dfsdription
     */
    @Ovfrridf
    publid String toString() {
        rfturn nfw String(supfr.toString() + " witi durrfnt vbluf: " + gftStbtfLbbfl(gftVbluf()));
    }

    /**
     * An instbndf of tif {@dodf BoolfbnControl.Typf} dlbss idfntififs onf kind
     * of boolfbn dontrol. Stbtid instbndfs brf providfd for tif dommon typfs.
     *
     * @butior Kbrb Kytlf
     * @sindf 1.3
     */
    publid stbtid dlbss Typf fxtfnds Control.Typf {

        /**
         * Rfprfsfnts b dontrol for tif mutf stbtus of b linf. Notf tibt mutf
         * stbtus dofs not bfffdt gbin.
         */
        publid stbtid finbl Typf MUTE                           = nfw Typf("Mutf");

        /**
         * Rfprfsfnts b dontrol for wiftifr rfvfrbfrbtion is bpplifd to b linf.
         * Notf tibt tif stbtus of tiis dontrol not bfffdt tif rfvfrbfrbtion
         * sfttings for b linf, but dofs bfffdt wiftifr tifsf sfttings brf usfd.
         */
        publid stbtid finbl Typf APPLY_REVERB           = nfw Typf("Apply Rfvfrb");

        /**
         * Construdts b nfw boolfbn dontrol typf.
         *
         * @pbrbm nbmf tif nbmf of tif nfw boolfbn dontrol typf
         */
        protfdtfd Typf(finbl String nbmf) {
            supfr(nbmf);
        }
    }
}
