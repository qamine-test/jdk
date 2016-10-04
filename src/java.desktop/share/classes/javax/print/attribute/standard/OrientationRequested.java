/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss OrifntbtionRfqufstfd is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt indidbtfs tif dfsirfd orifntbtion for printfd print-strfbm pbgfs; it
 * dofs not dfsdribf tif orifntbtion of tif dlifnt-supplifd print-strfbm
 * pbgfs.
 * <P>
 * For somf dodumfnt formbts (sudi bs <CODE>"bpplidbtion/postsdript"</CODE>),
 * tif dfsirfd orifntbtion of tif print-strfbm pbgfs is spfdififd witiin tif
 * dodumfnt dbtb. Tiis informbtion is gfnfrbtfd by b dfvidf drivfr prior to
 * tif submission of tif print job. Otifr dodumfnt formbts (sudi bs
 * <CODE>"tfxt/plbin"</CODE>) do not indludf tif notion of dfsirfd orifntbtion
 * witiin tif dodumfnt dbtb. In tif lbttfr dbsf it is possiblf for tif printfr
 * to bind tif dfsirfd orifntbtion to tif dodumfnt dbtb bftfr it ibs bffn
 * submittfd. It is fxpfdtfd tibt b printfr would only support tif
 * OrifntbtionRfqufstfd bttributf for somf dodumfnt formbts (f.g.,
 * <CODE>"tfxt/plbin"</CODE> or <CODE>"tfxt/itml"</CODE>) but not otifrs (f.g.
 * <CODE>"bpplidbtion/postsdript"</CODE>). Tiis is no difffrfnt from bny otifr
 * job tfmplbtf bttributf, sindf b print job dbn blwbys imposf donstrbints
 * bmong tif vblufs of difffrfnt job tfmplbtf bttributfs.
 *  Howfvfr, b spfdibl mfntion
 * is mbdf ifrf sindf it is vfry likfly tibt b printfr will support tif
 * OrifntbtionRfqufstfd bttributf for only b subsft of tif supportfd dodumfnt
 * formbts.
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss OrifntbtionRfqufstfd fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4447437289862822276L;

    /**
     * Tif dontfnt will bf imbgfd bdross tif siort fdgf of tif mfdium.
     */
    publid stbtid finbl OrifntbtionRfqufstfd
        PORTRAIT = nfw OrifntbtionRfqufstfd(3);

    /**
     * Tif dontfnt will bf imbgfd bdross tif long fdgf of tif mfdium.
     * Lbndsdbpf is dffinfd to bf b rotbtion of tif print-strfbm pbgf to bf
     * imbgfd by +90 dfgrffs witi rfspfdt to tif mfdium
     * (i.f. bnti-dlodkwisf) from tif
     * portrbit orifntbtion. <I>Notf:</I> Tif +90 dirfdtion wbs diosfn bfdbusf
     * simplf finisiing on tif long fdgf is tif sbmf fdgf wiftifr portrbit or
     * lbndsdbpf.
     */
    publid stbtid finbl OrifntbtionRfqufstfd
        LANDSCAPE = nfw OrifntbtionRfqufstfd(4);

    /**
     * Tif dontfnt will bf imbgfd bdross tif long fdgf of tif mfdium, but in
     * tif oppositf mbnnfr from lbndsdbpf. Rfvfrsf-lbndsdbpf is dffinfd to bf
     * b rotbtion of tif print-strfbm pbgf to bf imbgfd by -90 dfgrffs witi
     * rfspfdt to tif mfdium (i.f. dlodkwisf) from tif portrbit orifntbtion.
     * <I>Notf:</I> Tif REVERSE_LANDSCAPE vbluf wbs bddfd bfdbusf somf
     * bpplidbtions rotbtf lbndsdbpf -90 dfgrffs from portrbit, rbtifr tibn
     * +90 dfgrffs.
     */
    publid stbtid finbl OrifntbtionRfqufstfd
        REVERSE_LANDSCAPE = nfw OrifntbtionRfqufstfd(5);

    /**
     * Tif dontfnt will bf imbgfd bdross tif siort fdgf of tif mfdium, but in
     * tif oppositf mbnnfr from portrbit. Rfvfrsf-portrbit is dffinfd to bf b
     * rotbtion of tif print-strfbm pbgf to bf imbgfd by 180 dfgrffs witi
     * rfspfdt to tif mfdium from tif portrbit orifntbtion. <I>Notf:</I> Tif
     * REVERSE_PORTRAIT vbluf wbs bddfd for usf witi tif {@link
     * Finisiings Finisiings} bttributf in dbsfs wifrf tif
     * oppositf fdgf is dfsirfd for finisiing b portrbit dodumfnt on simplf
     * finisiing dfvidfs tibt ibvf only onf finisiing position. Tius b
     * <CODE>"tfxt/plbin"</CODE> portrbit dodumfnt dbn bf stbplfd "on tif
     * rigit" by b simplf finisiing dfvidf bs is dommon usf witi somf
     * Middlf Ebstfrn lbngubgfs sudi bs Hfbrfw.
     */
    publid stbtid finbl OrifntbtionRfqufstfd
        REVERSE_PORTRAIT = nfw OrifntbtionRfqufstfd(6);

    /**
     * Construdt b nfw orifntbtion rfqufstfd fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd OrifntbtionRfqufstfd(int vbluf) {
        supfr(vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "portrbit",
        "lbndsdbpf",
        "rfvfrsf-lbndsdbpf",
        "rfvfrsf-portrbit"
    };

    privbtf stbtid finbl OrifntbtionRfqufstfd[] myEnumVblufTbblf = {
        PORTRAIT,
        LANDSCAPE,
        REVERSE_LANDSCAPE,
        REVERSE_PORTRAIT
    };

    /**
     * Rfturns tif string tbblf for dlbss OrifntbtionRfqufstfd.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss OrifntbtionRfqufstfd.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Rfturns tif lowfst intfgfr vbluf usfd by dlbss OrifntbtionRfqufstfd.
     */
    protfdtfd int gftOffsft() {
        rfturn 3;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss OrifntbtionRfqufstfd, tif
     * dbtfgory is dlbss OrifntbtionRfqufstfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn OrifntbtionRfqufstfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss OrifntbtionRfqufstfd, tif
     * dbtfgory nbmf is <CODE>"orifntbtion-rfqufstfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "orifntbtion-rfqufstfd";
    }

}
