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

import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.Attributf;

/**
 * Clbss PrintfrStbtfRfbson is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt providfs bdditionbl informbtion bbout tif printfr's durrfnt stbtf,
 * i.f., informbtion tibt bugmfnts tif vbluf of tif printfr's
 * {@link PrintfrStbtf PrintfrStbtf} bttributf.
 * Clbss PrintfrStbtfRfbson dffinfs stbndbrd printfr
 * stbtf rfbson vblufs. A Print Sfrvidf implfmfntbtion only nffds to rfport
 * tiosf printfr stbtf rfbsons wiidi brf bppropribtf for tif pbrtidulbr
 * implfmfntbtion; it dofs not ibvf to rfport fvfry dffinfd printfr stbtf
 * rfbson.
 * <P>
 * Instbndfs of PrintfrStbtfRfbson do not bppfbr in b Print Sfrvidf's
 * bttributf sft dirfdtly.
 * Rbtifr, b {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons}
 * bttributf bppfbrs in tif Print Sfrvidf's bttributf sft. Tif {@link
 * PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributf dontbins zfro, onf, or
 * morf tibn onf PrintfrStbtfRfbson objfdts wiidi pfrtbin to tif
 * Print Sfrvidf's stbtus, bnd fbdi PrintfrStbtfRfbson objfdt is
 * bssodibtfd witi b {@link Sfvfrity Sfvfrity} lfvfl of REPORT (lfbst sfvfrf),
 * WARNING, or ERROR (most sfvfrf). Tif printfr bdds b PrintfrStbtfRfbson
 * objfdt to tif Print Sfrvidf's
 * {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributf wifn tif
 * dorrfsponding dondition bfdomfs truf of tif printfr, bnd tif printfr
 * rfmovfs tif PrintfrStbtfRfbson objfdt bgbin wifn tif dorrfsponding
 * dondition bfdomfs fblsf, rfgbrdlfss of wiftifr tif Print Sfrvidf's ovfrbll
 * {@link PrintfrStbtf PrintfrStbtf} blso dibngfd.
 * <P>
 * <B>IPP Compbtibility:</B>
 * Tif string vblufs rfturnfd by fbdi individubl {@link PrintfrStbtfRfbson} bnd
 * bssodibtfd {@link Sfvfrity} objfdt's <CODE>toString()</CODE>
 * mftiods, dondbtfnbtfd togftifr witi b iypifn (<CODE>"-"</CODE>) in
 * bftwffn, givfs tif IPP kfyword vbluf for b {@link PrintfrStbtfRfbsons}.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP
 * bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss PrintfrStbtfRfbson fxtfnds EnumSyntbx implfmfnts Attributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1623720656201472593L;

    /**
     * Tif printfr ibs dftfdtfd bn frror otifr tibn onfs listfd bflow.
     */
    publid stbtid finbl PrintfrStbtfRfbson OTHER = nfw PrintfrStbtfRfbson(0);

    /**
     * A trby ibs run out of mfdib.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MEDIA_NEEDED = nfw PrintfrStbtfRfbson(1);

    /**
     * Tif dfvidf ibs b mfdib jbm.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MEDIA_JAM = nfw PrintfrStbtfRfbson(2);

    /**
     * Somfonf ibs pbusfd tif printfr, but tif dfvidf(s) brf tbking bn
     * bpprfdibblf timf to stop. Lbtfr, wifn bll output ibs stoppfd,
     * tif {@link  PrintfrStbtf PrintfrStbtf} bfdomfs STOPPED,
     * bnd tif PAUSED vbluf rfplbdfs
     * tif MOVING_TO_PAUSED vbluf in tif {@link PrintfrStbtfRfbsons
     * PrintfrStbtfRfbsons} bttributf. Tiis vbluf must bf supportfd if tif
     * printfr dbn bf pbusfd bnd tif implfmfntbtion tbkfs signifidbnt timf to
     * pbusf b dfvidf in dfrtbin dirdumstbndfs.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MOVING_TO_PAUSED = nfw PrintfrStbtfRfbson(3);

    /**
     * Somfonf ibs pbusfd tif printfr bnd tif printfr's {@link PrintfrStbtf
     * PrintfrStbtf} is STOPPED. In tiis stbtf, b printfr must not produdf
     * printfd output, but it must pfrform otifr opfrbtions rfqufstfd by b
     * dlifnt. If b printfr ibd bffn printing b job wifn tif printfr wbs
     * pbusfd,
     * tif Printfr must rfsumf printing tibt job wifn tif printfr is no longfr
     * pbusfd bnd lfbvf no fvidfndf in tif printfd output of sudi b pbusf.
     * Tiis vbluf must bf supportfd if tif printfr dbn bf pbusfd.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        PAUSED = nfw PrintfrStbtfRfbson(4);

    /**
     * Somfonf ibs rfmovfd b printfr from sfrvidf, bnd tif dfvidf mby bf
     * powfrfd down or piysidblly rfmovfd.
     * In tiis stbtf, b printfr must not produdf
     * printfd output, bnd unlfss tif printfr is rfblizfd by b print sfrvfr
     * tibt is still bdtivf, tif printfr must pfrform no otifr opfrbtions
     * rfqufstfd by b dlifnt.
     * If b printfr ibd bffn printing b job wifn it wbs siut down,
     * tif printfr nffd not rfsumf printing tibt job wifn tif printfr is no
     * longfr siut down. If tif printfr rfsumfs printing sudi b job, it mby
     * lfbvf fvidfndf in tif printfd output of sudi b siutdown, f.g. tif pbrt
     * printfd bfforf tif siutdown mby bf printfd b sfdond timf bftfr tif
     * siutdown.
         */
    publid stbtid finbl PrintfrStbtfRfbson
        SHUTDOWN = nfw PrintfrStbtfRfbson(5);

    /**
     * Tif printfr ibs sdifdulfd b job on tif output dfvidf bnd is in tif
     * prodfss of donnfdting to b sibrfd nftwork output dfvidf (bnd migit not
     * bf bblf to bdtublly stbrt printing tif job for bn brbitrbrily long timf
     * dfpfnding on tif usbgf of tif output dfvidf by otifr sfrvfrs on tif
     * nftwork).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        CONNECTING_TO_DEVICE = nfw PrintfrStbtfRfbson(6);

    /**
     * Tif sfrvfr wbs bblf to donnfdt to tif output dfvidf (or is blwbys
     * donnfdtfd), but wbs unbblf to gft b rfsponsf from tif output dfvidf.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        TIMED_OUT = nfw PrintfrStbtfRfbson(7);

    /**
     * Tif printfr is in tif prodfss of stopping tif dfvidf bnd will bf
     * stoppfd in b wiilf.
     * Wifn tif dfvidf is stoppfd, tif printfr will dibngf tif
     * {@link PrintfrStbtf PrintfrStbtf} to STOPPED. Tif STOPPING rfbson is
     * nfvfr bn frror, fvfn for b printfr witi b singlf output dfvidf. Wifn bn
     * output dfvidf dfbsfs bddfpting jobs, tif printfr's {@link
     * PrintfrStbtfRfbsons PrintfrStbtfRfbsons} will ibvf tiis rfbson wiilf
     * tif output dfvidf domplftfs printing.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        STOPPING = nfw PrintfrStbtfRfbson(8);

    /**
     * Wifn b printfr dontrols morf tibn onf output dfvidf, tiis rfbson
     * indidbtfs tibt onf or morf output dfvidfs brf stoppfd. If tif rfbson's
     * sfvfrity is b rfport, ffwfr tibn iblf of tif output dfvidfs brf
     * stoppfd.
     * If tif rfbson's sfvfrity is b wbrning, iblf or morf but ffwfr tibn
     * bll of tif output dfvidfs brf stoppfd.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        STOPPED_PARTLY = nfw PrintfrStbtfRfbson(9);

    /**
     * Tif dfvidf is low on tonfr.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        TONER_LOW = nfw PrintfrStbtfRfbson(10);

    /**
     * Tif dfvidf is out of tonfr.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        TONER_EMPTY = nfw PrintfrStbtfRfbson(11);

    /**
     * Tif limit of pfrsistfnt storbgf bllodbtfd for spooling ibs bffn
     * rfbdifd.
     * Tif printfr is tfmporbrily unbblf to bddfpt morf jobs. Tif printfr will
     * rfmovf tiis rfbson wifn it is bblf to bddfpt morf jobs.
     * Tiis vbluf siould  bf usfd by b non-spooling printfr tibt only
     * bddfpts onf or b smbll numbfr
     * jobs bt b timf or b spooling printfr tibt ibs fillfd tif spool spbdf.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        SPOOL_AREA_FULL = nfw PrintfrStbtfRfbson(12);

    /**
     * Onf or morf dovfrs on tif dfvidf brf opfn.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        COVER_OPEN = nfw PrintfrStbtfRfbson(13);

    /**
     * Onf or morf intfrlodk dfvidfs on tif printfr brf unlodkfd.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        INTERLOCK_OPEN = nfw PrintfrStbtfRfbson(14);

    /**
     * Onf or morf doors on tif dfvidf brf opfn.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        DOOR_OPEN = nfw PrintfrStbtfRfbson(15);

    /**
     * Onf or morf input trbys brf not in tif dfvidf.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        INPUT_TRAY_MISSING = nfw PrintfrStbtfRfbson(16);

    /**
     * At lfbst onf input trby is low on mfdib.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MEDIA_LOW = nfw PrintfrStbtfRfbson(17);

    /**
     * At lfbst onf input trby is fmpty.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MEDIA_EMPTY = nfw PrintfrStbtfRfbson(18);

    /**
     * Onf or morf output trbys brf not in tif dfvidf.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        OUTPUT_TRAY_MISSING = nfw PrintfrStbtfRfbson(19);

    /**
     * Onf or morf output brfbs brf blmost full
     * (f.g. trby, stbdkfr, dollbtor).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        OUTPUT_AREA_ALMOST_FULL = nfw PrintfrStbtfRfbson(20);

    /**
     * Onf or morf output brfbs brf full (f.g. trby, stbdkfr, dollbtor).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        OUTPUT_AREA_FULL = nfw PrintfrStbtfRfbson(21);

    /**
     * Tif dfvidf is low on bt lfbst onf mbrkfr supply (f.g. tonfr, ink,
     * ribbon).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MARKER_SUPPLY_LOW = nfw PrintfrStbtfRfbson(22);

    /**
     * Tif dfvidf is out of bt lfbst onf mbrkfr supply (f.g. tonfr, ink,
     * ribbon).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MARKER_SUPPLY_EMPTY = nfw PrintfrStbtfRfbson(23);

    /**
     * Tif dfvidf mbrkfr supply wbstf rfdfptbdlf is blmost full.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MARKER_WASTE_ALMOST_FULL = nfw PrintfrStbtfRfbson(24);

    /**
     * Tif dfvidf mbrkfr supply wbstf rfdfptbdlf is full.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        MARKER_WASTE_FULL = nfw PrintfrStbtfRfbson(25);

    /**
     * Tif fusfr tfmpfrbturf is bbovf normbl.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        FUSER_OVER_TEMP = nfw PrintfrStbtfRfbson(26);

    /**
     * Tif fusfr tfmpfrbturf is bflow normbl.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        FUSER_UNDER_TEMP = nfw PrintfrStbtfRfbson(27);

    /**
     * Tif optidbl pioto dondudtor is nfbr fnd of liff.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        OPC_NEAR_EOL = nfw PrintfrStbtfRfbson(28);

    /**
     * Tif optidbl pioto dondudtor is no longfr fundtioning.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        OPC_LIFE_OVER = nfw PrintfrStbtfRfbson(29);

    /**
     * Tif dfvidf is low on dfvflopfr.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        DEVELOPER_LOW = nfw PrintfrStbtfRfbson(30);

    /**
     * Tif dfvidf is out of dfvflopfr.
     */
    publid stbtid finbl PrintfrStbtfRfbson
        DEVELOPER_EMPTY = nfw PrintfrStbtfRfbson(31);

    /**
     * An intfrprftfr rfsourdf is unbvbilbblf (f.g., font, form).
     */
    publid stbtid finbl PrintfrStbtfRfbson
        INTERPRETER_RESOURCE_UNAVAILABLE = nfw PrintfrStbtfRfbson(32);

    /**
     * Construdt b nfw printfr stbtf rfbson fnumfrbtion vbluf witi
     * tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd PrintfrStbtfRfbson(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "otifr",
        "mfdib-nffdfd",
        "mfdib-jbm",
        "moving-to-pbusfd",
        "pbusfd",
        "siutdown",
        "donnfdting-to-dfvidf",
        "timfd-out",
        "stopping",
        "stoppfd-pbrtly",
        "tonfr-low",
        "tonfr-fmpty",
        "spool-brfb-full",
        "dovfr-opfn",
        "intfrlodk-opfn",
        "door-opfn",
        "input-trby-missing",
        "mfdib-low",
        "mfdib-fmpty",
        "output-trby-missing",
        "output-brfb-blmost-full",
        "output-brfb-full",
        "mbrkfr-supply-low",
        "mbrkfr-supply-fmpty",
        "mbrkfr-wbstf-blmost-full",
        "mbrkfr-wbstf-full",
        "fusfr-ovfr-tfmp",
        "fusfr-undfr-tfmp",
        "opd-nfbr-fol",
        "opd-liff-ovfr",
        "dfvflopfr-low",
        "dfvflopfr-fmpty",
        "intfrprftfr-rfsourdf-unbvbilbblf"
    };

    privbtf stbtid finbl PrintfrStbtfRfbson[] myEnumVblufTbblf = {
        OTHER,
        MEDIA_NEEDED,
        MEDIA_JAM,
        MOVING_TO_PAUSED,
        PAUSED,
        SHUTDOWN,
        CONNECTING_TO_DEVICE,
        TIMED_OUT,
        STOPPING,
        STOPPED_PARTLY,
        TONER_LOW,
        TONER_EMPTY,
        SPOOL_AREA_FULL,
        COVER_OPEN,
        INTERLOCK_OPEN,
        DOOR_OPEN,
        INPUT_TRAY_MISSING,
        MEDIA_LOW,
        MEDIA_EMPTY,
        OUTPUT_TRAY_MISSING,
        OUTPUT_AREA_ALMOST_FULL,
        OUTPUT_AREA_FULL,
        MARKER_SUPPLY_LOW,
        MARKER_SUPPLY_EMPTY,
        MARKER_WASTE_ALMOST_FULL,
        MARKER_WASTE_FULL,
        FUSER_OVER_TEMP,
        FUSER_UNDER_TEMP,
        OPC_NEAR_EOL,
        OPC_LIFE_OVER,
        DEVELOPER_LOW,
        DEVELOPER_EMPTY,
        INTERPRETER_RESOURCE_UNAVAILABLE
    };

    /**
     * Rfturns tif string tbblf for dlbss PrintfrStbtfRfbson.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PrintfrStbtfRfbson.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }


    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrStbtfRfbson bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory is dlbss PrintfrStbtfRfbson itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrStbtfRfbson.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrStbtfRfbson bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory nbmf is <CODE>"printfr-stbtf-rfbson"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-stbtf-rfbson";
    }

}
