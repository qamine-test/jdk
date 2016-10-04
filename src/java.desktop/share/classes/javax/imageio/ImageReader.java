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

pbdkbgf jbvbx.imbgfio;

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.Sft;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.fvfnt.IIORfbdWbrningListfnfr;
import jbvbx.imbgfio.fvfnt.IIORfbdProgrfssListfnfr;
import jbvbx.imbgfio.fvfnt.IIORfbdUpdbtfListfnfr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

/**
 * An bbstrbdt supfrdlbss for pbrsing bnd dfdoding of imbgfs.  Tiis
 * dlbss must bf subdlbssfd by dlbssfs tibt rfbd in imbgfs in tif
 * dontfxt of tif Jbvb Imbgf I/O frbmfwork.
 *
 * <p> <dodf>ImbgfRfbdfr</dodf> objfdts brf normblly instbntibtfd by
 * tif sfrvidf providfr intfrfbdf (SPI) dlbss for tif spfdifid formbt.
 * Sfrvidf providfr dlbssfs (f.g., instbndfs of
 * <dodf>ImbgfRfbdfrSpi</dodf>) brf rfgistfrfd witi tif
 * <dodf>IIORfgistry</dodf>, wiidi usfs tifm for formbt rfdognition
 * bnd prfsfntbtion of bvbilbblf formbt rfbdfrs bnd writfrs.
 *
 * <p> Wifn bn input sourdf is sft (using tif <dodf>sftInput</dodf>
 * mftiod), it mby bf mbrkfd bs "sffk forwbrd only".  Tiis sftting
 * mfbns tibt imbgfs dontbinfd witiin tif input sourdf will only bf
 * rfbd in ordfr, possibly bllowing tif rfbdfr to bvoid dbdiing
 * portions of tif input dontbining dbtb bssodibtfd witi imbgfs tibt
 * ibvf bffn rfbd prfviously.
 *
 * @sff ImbgfWritfr
 * @sff jbvbx.imbgfio.spi.IIORfgistry
 * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi
 *
 */
publid bbstrbdt dlbss ImbgfRfbdfr {

    /**
     * Tif <dodf>ImbgfRfbdfrSpi</dodf> tibt instbntibtfd tiis objfdt,
     * or <dodf>null</dodf> if its idfntity is not known or nonf
     * fxists.  By dffbult it is initiblizfd to <dodf>null</dodf>.
     */
    protfdtfd ImbgfRfbdfrSpi originbtingProvidfr;

    /**
     * Tif <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> by <dodf>sftInput</dodf> bnd rftrifvfd
     * by <dodf>gftInput</dodf>.  By dffbult it is initiblizfd to
     * <dodf>null</dodf>.
     */
    protfdtfd Objfdt input = null;

    /**
     * <dodf>truf</dodf> if tif durrfnt input sourdf ibs bffn mbrkfd
     * bs bllowing only forwbrd sffking by <dodf>sftInput</dodf>.  By
     * dffbult, tif vbluf is <dodf>fblsf</dodf>.
     *
     * @sff #minIndfx
     * @sff #sftInput
     */
    protfdtfd boolfbn sffkForwbrdOnly = fblsf;

    /**
     * <dodf>truf</dodf> if tif durrfnt input sourdf ibs bffn mbrkfd
     * bs bllowing mftbdbtb to bf ignorfd by <dodf>sftInput</dodf>.
     * By dffbult, tif vbluf is <dodf>fblsf</dodf>.
     *
     * @sff #sftInput
     */
    protfdtfd boolfbn ignorfMftbdbtb = fblsf;

    /**
     * Tif smbllfst vblid indfx for rfbding, initiblly 0.  Wifn
     * <dodf>sffkForwbrdOnly</dodf> is <dodf>truf</dodf>, vbrious mftiods
     * mby tirow bn <dodf>IndfxOutOfBoundsExdfption</dodf> on bn
     * bttfmpt to bddfss dbtb bssodibtf witi bn imbgf ibving b lowfr
     * indfx.
     *
     * @sff #sffkForwbrdOnly
     * @sff #sftInput
     */
    protfdtfd int minIndfx = 0;

    /**
     * An brrby of <dodf>Lodblf</dodf>s wiidi mby bf usfd to lodblizf
     * wbrning mfssbgfs, or <dodf>null</dodf> if lodblizbtion is not
     * supportfd.
     */
    protfdtfd Lodblf[] bvbilbblfLodblfs = null;

    /**
     * Tif durrfnt <dodf>Lodblf</dodf> to bf usfd for lodblizbtion, or
     * <dodf>null</dodf> if nonf ibs bffn sft.
     */
    protfdtfd Lodblf lodblf = null;

    /**
     * A <dodf>List</dodf> of durrfntly rfgistfrfd
     * <dodf>IIORfbdWbrningListfnfr</dodf>s, initiblizfd by dffbult to
     * <dodf>null</dodf>, wiidi is synonymous witi bn fmpty
     * <dodf>List</dodf>.
     */
    protfdtfd List<IIORfbdWbrningListfnfr> wbrningListfnfrs = null;

    /**
     * A <dodf>List</dodf> of tif <dodf>Lodblf</dodf>s bssodibtfd witi
     * fbdi durrfntly rfgistfrfd <dodf>IIORfbdWbrningListfnfr</dodf>,
     * initiblizfd by dffbult to <dodf>null</dodf>, wiidi is
     * synonymous witi bn fmpty <dodf>List</dodf>.
     */
    protfdtfd List<Lodblf> wbrningLodblfs = null;

    /**
     * A <dodf>List</dodf> of durrfntly rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s, initiblizfd by dffbult
     * to <dodf>null</dodf>, wiidi is synonymous witi bn fmpty
     * <dodf>List</dodf>.
     */
    protfdtfd List<IIORfbdProgrfssListfnfr> progrfssListfnfrs = null;

    /**
     * A <dodf>List</dodf> of durrfntly rfgistfrfd
     * <dodf>IIORfbdUpdbtfListfnfr</dodf>s, initiblizfd by dffbult to
     * <dodf>null</dodf>, wiidi is synonymous witi bn fmpty
     * <dodf>List</dodf>.
     */
    protfdtfd List<IIORfbdUpdbtfListfnfr> updbtfListfnfrs = null;

    /**
     * If <dodf>truf</dodf>, tif durrfnt rfbd opfrbtion siould bf
     * bbortfd.
     */
    privbtf boolfbn bbortFlbg = fblsf;

    /**
     * Construdts bn <dodf>ImbgfRfbdfr</dodf> bnd sfts its
     * <dodf>originbtingProvidfr</dodf> fifld to tif supplifd vbluf.
     *
     * <p> Subdlbssfs tibt mbkf usf of fxtfnsions siould providf b
     * donstrudtor witi signbturf <dodf>(ImbgfRfbdfrSpi,
     * Objfdt)</dodf> in ordfr to rftrifvf tif fxtfnsion objfdt.  If
     * tif fxtfnsion objfdt is unsuitbblf, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> siould bf tirown.
     *
     * @pbrbm originbtingProvidfr tif <dodf>ImbgfRfbdfrSpi</dodf> tibt is
     * invoking tiis donstrudtor, or <dodf>null</dodf>.
     */
    protfdtfd ImbgfRfbdfr(ImbgfRfbdfrSpi originbtingProvidfr) {
        tiis.originbtingProvidfr = originbtingProvidfr;
    }

    /**
     * Rfturns b <dodf>String</dodf> idfntifying tif formbt of tif
     * input sourdf.
     *
     * <p> Tif dffbult implfmfntbtion rfturns
     * <dodf>originbtingProvidfr.gftFormbtNbmfs()[0]</dodf>.
     * Implfmfntbtions tibt mby not ibvf bn originbting sfrvidf
     * providfr, or wiidi dfsirf b difffrfnt nbming polidy siould
     * ovfrridf tiis mftiod.
     *
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif
     * informbtion from tif input sourdf.
     *
     * @rfturn tif formbt nbmf, bs b <dodf>String</dodf>.
     */
    publid String gftFormbtNbmf() tirows IOExdfption {
        rfturn originbtingProvidfr.gftFormbtNbmfs()[0];
    }

    /**
     * Rfturns tif <dodf>ImbgfRfbdfrSpi</dodf> tibt wbs pbssfd in on
     * tif donstrudtor.  Notf tibt tiis vbluf mby bf <dodf>null</dodf>.
     *
     * @rfturn bn <dodf>ImbgfRfbdfrSpi</dodf>, or <dodf>null</dodf>.
     *
     * @sff ImbgfRfbdfrSpi
     */
    publid ImbgfRfbdfrSpi gftOriginbtingProvidfr() {
        rfturn originbtingProvidfr;
    }

    /**
     * Sfts tif input sourdf to usf to tif givfn
     * <dodf>ImbgfInputStrfbm</dodf> or otifr <dodf>Objfdt</dodf>.
     * Tif input sourdf must bf sft bfforf bny of tif qufry or rfbd
     * mftiods brf usfd.  If <dodf>input</dodf> is <dodf>null</dodf>,
     * bny durrfntly sft input sourdf will bf rfmovfd.  In bny dbsf,
     * tif vbluf of <dodf>minIndfx</dodf> will bf initiblizfd to 0.
     *
     * <p> Tif <dodf>sffkForwbrdOnly</dodf> pbrbmftfr dontrols wiftifr
     * tif vbluf rfturnfd by <dodf>gftMinIndfx</dodf> will bf
     * indrfbsfd bs fbdi imbgf (or tiumbnbil, or imbgf mftbdbtb) is
     * rfbd.  If <dodf>sffkForwbrdOnly</dodf> is truf, tifn b dbll to
     * <dodf>rfbd(indfx)</dodf> will tirow bn
     * <dodf>IndfxOutOfBoundsExdfption</dodf> if {@dodf indfx < tiis.minIndfx};
     * otifrwisf, tif vbluf of
     * <dodf>minIndfx</dodf> will bf sft to <dodf>indfx</dodf>.  If
     * <dodf>sffkForwbrdOnly</dodf> is <dodf>fblsf</dodf>, tif vbluf of
     * <dodf>minIndfx</dodf> will rfmbin 0 rfgbrdlfss of bny rfbd
     * opfrbtions.
     *
     * <p> Tif <dodf>ignorfMftbdbtb</dodf> pbrbmftfr, if sft to
     * <dodf>truf</dodf>, bllows tif rfbdfr to disrfgbrd bny mftbdbtb
     * fndountfrfd during tif rfbd.  Subsfqufnt dblls to tif
     * <dodf>gftStrfbmMftbdbtb</dodf> bnd
     * <dodf>gftImbgfMftbdbtb</dodf> mftiods mby rfturn
     * <dodf>null</dodf>, bnd bn <dodf>IIOImbgf</dodf> rfturnfd from
     * <dodf>rfbdAll</dodf> mby rfturn <dodf>null</dodf> from tifir
     * <dodf>gftMftbdbtb</dodf> mftiod.  Sftting tiis pbrbmftfr mby
     * bllow tif rfbdfr to work morf fffidifntly.  Tif rfbdfr mby
     * dioosf to disrfgbrd tiis sftting bnd rfturn mftbdbtb normblly.
     *
     * <p> Subdlbssfs siould tbkf dbrf to rfmovf bny dbdifd
     * informbtion bbsfd on tif prfvious strfbm, sudi bs ifbdfr
     * informbtion or pbrtiblly dfdodfd imbgf dbtb.
     *
     * <p> Usf of b gfnfrbl <dodf>Objfdt</dodf> otifr tibn bn
     * <dodf>ImbgfInputStrfbm</dodf> is intfndfd for rfbdfrs tibt
     * intfrbdt dirfdtly witi b dbpturf dfvidf or imbging protodol.
     * Tif sft of lfgbl dlbssfs is bdvfrtisfd by tif rfbdfr's sfrvidf
     * providfr's <dodf>gftInputTypfs</dodf> mftiod; most rfbdfrs
     * will rfturn b singlf-flfmfnt brrby dontbining only
     * <dodf>ImbgfInputStrfbm.dlbss</dodf> to indidbtf tibt tify
     * bddfpt only bn <dodf>ImbgfInputStrfbm</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion difdks tif <dodf>input</dodf>
     * brgumfnt bgbinst tif list rfturnfd by
     * <dodf>originbtingProvidfr.gftInputTypfs()</dodf> bnd fbils
     * if tif brgumfnt is not bn instbndf of onf of tif dlbssfs
     * in tif list.  If tif originbting providfr is sft to
     * <dodf>null</dodf>, tif input is bddfptfd only if it is bn
     * <dodf>ImbgfInputStrfbm</dodf>.
     *
     * @pbrbm input tif <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> to usf for futurf dfdoding.
     * @pbrbm sffkForwbrdOnly if <dodf>truf</dodf>, imbgfs bnd mftbdbtb
     * mby only bf rfbd in bsdfnding ordfr from tiis input sourdf.
     * @pbrbm ignorfMftbdbtb if <dodf>truf</dodf>, mftbdbtb
     * mby bf ignorfd during rfbds.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * not bn instbndf of onf of tif dlbssfs rfturnfd by tif
     * originbting sfrvidf providfr's <dodf>gftInputTypfs</dodf>
     * mftiod, or is not bn <dodf>ImbgfInputStrfbm</dodf>.
     *
     * @sff ImbgfInputStrfbm
     * @sff #gftInput
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#gftInputTypfs
     */
    publid void sftInput(Objfdt input,
                         boolfbn sffkForwbrdOnly,
                         boolfbn ignorfMftbdbtb) {
        if (input != null) {
            boolfbn found = fblsf;
            if (originbtingProvidfr != null) {
                Clbss<?>[] dlbssfs = originbtingProvidfr.gftInputTypfs();
                for (int i = 0; i < dlbssfs.lfngti; i++) {
                    if (dlbssfs[i].isInstbndf(input)) {
                        found = truf;
                        brfbk;
                    }
                }
            } flsf {
                if (input instbndfof ImbgfInputStrfbm) {
                    found = truf;
                }
            }
            if (!found) {
                tirow nfw IllfgblArgumfntExdfption("Indorrfdt input typf!");
            }

            tiis.sffkForwbrdOnly = sffkForwbrdOnly;
            tiis.ignorfMftbdbtb = ignorfMftbdbtb;
            tiis.minIndfx = 0;
        }

        tiis.input = input;
    }

    /**
     * Sfts tif input sourdf to usf to tif givfn
     * <dodf>ImbgfInputStrfbm</dodf> or otifr <dodf>Objfdt</dodf>.
     * Tif input sourdf must bf sft bfforf bny of tif qufry or rfbd
     * mftiods brf usfd.  If <dodf>input</dodf> is <dodf>null</dodf>,
     * bny durrfntly sft input sourdf will bf rfmovfd.  In bny dbsf,
     * tif vbluf of <dodf>minIndfx</dodf> will bf initiblizfd to 0.
     *
     * <p> Tif <dodf>sffkForwbrdOnly</dodf> pbrbmftfr dontrols wiftifr
     * tif vbluf rfturnfd by <dodf>gftMinIndfx</dodf> will bf
     * indrfbsfd bs fbdi imbgf (or tiumbnbil, or imbgf mftbdbtb) is
     * rfbd.  If <dodf>sffkForwbrdOnly</dodf> is truf, tifn b dbll to
     * <dodf>rfbd(indfx)</dodf> will tirow bn
     * <dodf>IndfxOutOfBoundsExdfption</dodf> if {@dodf indfx < tiis.minIndfx};
     * otifrwisf, tif vbluf of
     * <dodf>minIndfx</dodf> will bf sft to <dodf>indfx</dodf>.  If
     * <dodf>sffkForwbrdOnly</dodf> is <dodf>fblsf</dodf>, tif vbluf of
     * <dodf>minIndfx</dodf> will rfmbin 0 rfgbrdlfss of bny rfbd
     * opfrbtions.
     *
     * <p> Tiis mftiod is fquivblfnt to <dodf>sftInput(input,
     * sffkForwbrdOnly, fblsf)</dodf>.
     *
     * @pbrbm input tif <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> to usf for futurf dfdoding.
     * @pbrbm sffkForwbrdOnly if <dodf>truf</dodf>, imbgfs bnd mftbdbtb
     * mby only bf rfbd in bsdfnding ordfr from tiis input sourdf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * not bn instbndf of onf of tif dlbssfs rfturnfd by tif
     * originbting sfrvidf providfr's <dodf>gftInputTypfs</dodf>
     * mftiod, or is not bn <dodf>ImbgfInputStrfbm</dodf>.
     *
     * @sff #gftInput
     */
    publid void sftInput(Objfdt input,
                         boolfbn sffkForwbrdOnly) {
        sftInput(input, sffkForwbrdOnly, fblsf);
    }

    /**
     * Sfts tif input sourdf to usf to tif givfn
     * <dodf>ImbgfInputStrfbm</dodf> or otifr <dodf>Objfdt</dodf>.
     * Tif input sourdf must bf sft bfforf bny of tif qufry or rfbd
     * mftiods brf usfd.  If <dodf>input</dodf> is <dodf>null</dodf>,
     * bny durrfntly sft input sourdf will bf rfmovfd.  In bny dbsf,
     * tif vbluf of <dodf>minIndfx</dodf> will bf initiblizfd to 0.
     *
     * <p> Tiis mftiod is fquivblfnt to <dodf>sftInput(input, fblsf,
     * fblsf)</dodf>.
     *
     * @pbrbm input tif <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> to usf for futurf dfdoding.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * not bn instbndf of onf of tif dlbssfs rfturnfd by tif
     * originbting sfrvidf providfr's <dodf>gftInputTypfs</dodf>
     * mftiod, or is not bn <dodf>ImbgfInputStrfbm</dodf>.
     *
     * @sff #gftInput
     */
    publid void sftInput(Objfdt input) {
        sftInput(input, fblsf, fblsf);
    }

    /**
     * Rfturns tif <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> prfviously sft bs tif input sourdf.  If tif
     * input sourdf ibs not bffn sft, <dodf>null</dodf> is rfturnfd.
     *
     * @rfturn tif <dodf>Objfdt</dodf> tibt will bf usfd for futurf
     * dfdoding, or <dodf>null</dodf>.
     *
     * @sff ImbgfInputStrfbm
     * @sff #sftInput
     */
    publid Objfdt gftInput() {
        rfturn input;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif durrfnt input sourdf ibs bffn
     * mbrkfd bs sffk forwbrd only by pbssing <dodf>truf</dodf> bs tif
     * <dodf>sffkForwbrdOnly</dodf> brgumfnt to tif
     * <dodf>sftInput</dodf> mftiod.
     *
     * @rfturn <dodf>truf</dodf> if tif input sourdf is sffk forwbrd
     * only.
     *
     * @sff #sftInput
     */
    publid boolfbn isSffkForwbrdOnly() {
        rfturn sffkForwbrdOnly;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif durrfnt input sourdf ibs bffn
     * mbrkfd bs bllowing mftbdbtb to bf ignorfd by pbssing
     * <dodf>truf</dodf> bs tif <dodf>ignorfMftbdbtb</dodf> brgumfnt
     * to tif <dodf>sftInput</dodf> mftiod.
     *
     * @rfturn <dodf>truf</dodf> if tif mftbdbtb mby bf ignorfd.
     *
     * @sff #sftInput
     */
    publid boolfbn isIgnoringMftbdbtb() {
        rfturn ignorfMftbdbtb;
    }

    /**
     * Rfturns tif lowfst vblid indfx for rfbding bn imbgf, tiumbnbil,
     * or imbgf mftbdbtb.  If <dodf>sffkForwbrdOnly()</dodf> is
     * <dodf>fblsf</dodf>, tiis vbluf will typidblly rfmbin 0,
     * indidbting tibt rbndom bddfss is possiblf.  Otifrwisf, it will
     * dontbin tif vbluf of tif most rfdfntly bddfssfd indfx, bnd
     * indrfbsf in b monotonid fbsiion.
     *
     * @rfturn tif minimum lfgbl indfx for rfbding.
     */
    publid int gftMinIndfx() {
        rfturn minIndfx;
    }

    // Lodblizbtion

    /**
     * Rfturns bn brrby of <dodf>Lodblf</dodf>s tibt mby bf usfd to
     * lodblizf wbrning listfnfrs bnd domprfssion sfttings.  A rfturn
     * vbluf of <dodf>null</dodf> indidbtfs tibt lodblizbtion is not
     * supportfd.
     *
     * <p> Tif dffbult implfmfntbtion rfturns b dlonf of tif
     * <dodf>bvbilbblfLodblfs</dodf> instbndf vbribblf if it is
     * non-<dodf>null</dodf>, or flsf rfturns <dodf>null</dodf>.
     *
     * @rfturn bn brrby of <dodf>Lodblf</dodf>s tibt mby bf usfd bs
     * brgumfnts to <dodf>sftLodblf</dodf>, or <dodf>null</dodf>.
     */
    publid Lodblf[] gftAvbilbblfLodblfs() {
        if (bvbilbblfLodblfs == null) {
            rfturn null;
        } flsf {
            rfturn bvbilbblfLodblfs.dlonf();
        }
    }

    /**
     * Sfts tif durrfnt <dodf>Lodblf</dodf> of tiis
     * <dodf>ImbgfRfbdfr</dodf> to tif givfn vbluf.  A vbluf of
     * <dodf>null</dodf> rfmovfs bny prfvious sftting, bnd indidbtfs
     * tibt tif rfbdfr siould lodblizf bs it sffs fit.
     *
     * @pbrbm lodblf tif dfsirfd <dodf>Lodblf</dodf>, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> is
     * non-<dodf>null</dodf> but is not onf of tif vblufs rfturnfd by
     * <dodf>gftAvbilbblfLodblfs</dodf>.
     *
     * @sff #gftLodblf
     */
    publid void sftLodblf(Lodblf lodblf) {
        if (lodblf != null) {
            Lodblf[] lodblfs = gftAvbilbblfLodblfs();
            boolfbn found = fblsf;
            if (lodblfs != null) {
                for (int i = 0; i < lodblfs.lfngti; i++) {
                    if (lodblf.fqubls(lodblfs[i])) {
                        found = truf;
                        brfbk;
                    }
                }
            }
            if (!found) {
                tirow nfw IllfgblArgumfntExdfption("Invblid lodblf!");
            }
        }
        tiis.lodblf = lodblf;
    }

    /**
     * Rfturns tif durrfntly sft <dodf>Lodblf</dodf>, or
     * <dodf>null</dodf> if nonf ibs bffn sft.
     *
     * @rfturn tif durrfnt <dodf>Lodblf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #sftLodblf
     */
    publid Lodblf gftLodblf() {
        rfturn lodblf;
    }

    // Imbgf qufrifs

    /**
     * Rfturns tif numbfr of imbgfs, not indluding tiumbnbils, bvbilbblf
     * from tif durrfnt input sourdf.
     *
     * <p> Notf tibt somf imbgf formbts (sudi bs bnimbtfd GIF) do not
     * spfdify iow mbny imbgfs brf prfsfnt in tif strfbm.  Tius
     * dftfrmining tif numbfr of imbgfs will rfquirf tif fntirf strfbm
     * to bf sdbnnfd bnd mby rfquirf mfmory for bufffring.  If imbgfs
     * brf to bf prodfssfd in ordfr, it mby bf morf fffidifnt to
     * simply dbll <dodf>rfbd</dodf> witi indrfbsing indidfs until bn
     * <dodf>IndfxOutOfBoundsExdfption</dodf> is tirown to indidbtf
     * tibt no morf imbgfs brf bvbilbblf.  Tif
     * <dodf>bllowSfbrdi</dodf> pbrbmftfr mby bf sft to
     * <dodf>fblsf</dodf> to indidbtf tibt bn fxibustivf sfbrdi is not
     * dfsirfd; tif rfturn vbluf will bf <dodf>-1</dodf> to indidbtf
     * tibt b sfbrdi is nfdfssbry.  If tif input ibs bffn spfdififd
     * witi <dodf>sffkForwbrdOnly</dodf> sft to <dodf>truf</dodf>,
     * tiis mftiod tirows bn <dodf>IllfgblStbtfExdfption</dodf> if
     * <dodf>bllowSfbrdi</dodf> is sft to <dodf>truf</dodf>.
     *
     * @pbrbm bllowSfbrdi if <dodf>truf</dodf>, tif truf numbfr of
     * imbgfs will bf rfturnfd fvfn if b sfbrdi is rfquirfd.  If
     * <dodf>fblsf</dodf>, tif rfbdfr mby rfturn <dodf>-1</dodf>
     * witiout pfrforming tif sfbrdi.
     *
     * @rfturn tif numbfr of imbgfs, bs bn <dodf>int</dodf>, or
     * <dodf>-1</dodf> if <dodf>bllowSfbrdi</dodf> is
     * <dodf>fblsf</dodf> bnd b sfbrdi would bf rfquirfd.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft,
     * or if tif input ibs bffn spfdififd witi <dodf>sffkForwbrdOnly</dodf>
     * sft to <dodf>truf</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif
     * informbtion from tif input sourdf.
     *
     * @sff #sftInput
     */
    publid bbstrbdt int gftNumImbgfs(boolfbn bllowSfbrdi) tirows IOExdfption;

    /**
     * Rfturns tif widti in pixfls of tif givfn imbgf witiin tif input
     * sourdf.
     *
     * <p> If tif imbgf dbn bf rfndfrfd to b usfr-spfdififd sizf, tifn
     * tiis mftiod rfturns tif dffbult widti.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn tif widti of tif imbgf, bs bn <dodf>int</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif widti
     * informbtion from tif input sourdf.
     */
    publid bbstrbdt int gftWidti(int imbgfIndfx) tirows IOExdfption;

    /**
     * Rfturns tif ifigit in pixfls of tif givfn imbgf witiin tif
     * input sourdf.
     *
     * <p> If tif imbgf dbn bf rfndfrfd to b usfr-spfdififd sizf, tifn
     * tiis mftiod rfturns tif dffbult ifigit.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn tif ifigit of tif imbgf, bs bn <dodf>int</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif ifigit
     * informbtion from tif input sourdf.
     */
    publid bbstrbdt int gftHfigit(int imbgfIndfx) tirows IOExdfption;

    /**
     * Rfturns <dodf>truf</dodf> if tif storbgf formbt of tif givfn
     * imbgf plbdfs no inifrfnt impfdimfnt on rbndom bddfss to pixfls.
     * For most domprfssfd formbts, sudi bs JPEG, tiis mftiod siould
     * rfturn <dodf>fblsf</dodf>, bs b lbrgf sfdtion of tif imbgf in
     * bddition to tif rfgion of intfrfst mby nffd to bf dfdodfd.
     *
     * <p> Tiis is mfrfly b iint for progrbms tibt wisi to bf
     * fffidifnt; bll rfbdfrs must bf bblf to rfbd brbitrbry rfgions
     * bs spfdififd in bn <dodf>ImbgfRfbdPbrbm</dodf>.
     *
     * <p> Notf tibt formbts tibt rfturn <dodf>fblsf</dodf> from
     * tiis mftiod mby nonftiflfss bllow tiling (<i>f.g.</i> Rfstbrt
     * Mbrkfrs in JPEG), bnd rbndom bddfss will likfly bf rfbsonbbly
     * fffidifnt on tilfs.  Sff {@link #isImbgfTilfd isImbgfTilfd}.
     *
     * <p> A rfbdfr for wiidi bll imbgfs brf gubrbntffd to support
     * fbsy rbndom bddfss, or brf gubrbntffd not to support fbsy
     * rbndom bddfss, mby rfturn <dodf>truf</dodf> or
     * <dodf>fblsf</dodf> rfspfdtivfly witiout bddfssing bny imbgf
     * dbtb.  In sudi dbsfs, it is not nfdfssbry to tirow bn fxdfption
     * fvfn if no input sourdf ibs bffn sft or tif imbgf indfx is out
     * of bounds.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>fblsf</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn <dodf>truf</dodf> if rfbding b rfgion of intfrfst of
     * tif givfn imbgf is likfly to bf fffidifnt.
     *
     * @fxdfption IllfgblStbtfExdfption if bn input sourdf is rfquirfd
     * to dftfrminf tif rfturn vbluf, but nonf ibs bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if bn imbgf must bf
     * bddfssfd to dftfrminf tif rfturn vbluf, but tif supplifd indfx
     * is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid boolfbn isRbndomAddfssEbsy(int imbgfIndfx) tirows IOExdfption {
        rfturn fblsf;
    }

    /**
     * Rfturns tif bspfdt rbtio of tif givfn imbgf (tibt is, its widti
     * dividfd by its ifigit) bs b <dodf>flobt</dodf>.  For imbgfs
     * tibt brf inifrfntly rfsizbblf, tiis mftiod providfs b wby to
     * dftfrminf tif bppropribtf widti givfn b dfsirfd ifigit, or vidf
     * vfrsb.  For non-rfsizbblf imbgfs, tif truf widti bnd ifigit
     * brf usfd.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>(flobt)gftWidti(imbgfIndfx)/gftHfigit(imbgfIndfx)</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn b <dodf>flobt</dodf> indidbting tif bspfdt rbtio of tif
     * givfn imbgf.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid flobt gftAspfdtRbtio(int imbgfIndfx) tirows IOExdfption {
        rfturn (flobt)gftWidti(imbgfIndfx)/gftHfigit(imbgfIndfx);
    }

    /**
     * Rfturns bn <dodf>ImbgfTypfSpfdififr</dodf> indidbting tif
     * <dodf>SbmplfModfl</dodf> bnd <dodf>ColorModfl</dodf> wiidi most
     * dlosfly rfprfsfnts tif "rbw" intfrnbl formbt of tif imbgf.  For
     * fxbmplf, for b JPEG imbgf tif rbw typf migit ibvf b YCbCr dolor
     * spbdf fvfn tiougi tif imbgf would donvfntionblly bf trbnsformfd
     * into bn RGB dolor spbdf prior to displby.  Tif rfturnfd vbluf
     * siould blso bf indludfd in tif list of vblufs rfturnfd by
     * <dodf>gftImbgfTypfs</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns tif first fntry
     * from tif list providfd by <dodf>gftImbgfTypf</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn bn <dodf>ImbgfTypfSpfdififr</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif formbt
     * informbtion from tif input sourdf.
     */
    publid ImbgfTypfSpfdififr gftRbwImbgfTypf(int imbgfIndfx)
        tirows IOExdfption {
        rfturn gftImbgfTypfs(imbgfIndfx).nfxt();
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining possiblf imbgf
     * typfs to wiidi tif givfn imbgf mby bf dfdodfd, in tif form of
     * <dodf>ImbgfTypfSpfdififrs</dodf>s.  At lfbst onf lfgbl imbgf
     * typf will bf rfturnfd.
     *
     * <p> Tif first flfmfnt of tif itfrbtor siould bf tif most
     * "nbturbl" typf for dfdoding tif imbgf witi bs littlf loss bs
     * possiblf.  For fxbmplf, for b JPEG imbgf tif first fntry siould
     * bf bn RGB imbgf, fvfn tiougi tif imbgf dbtb is storfd
     * intfrnblly in b YCbCr dolor spbdf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf
     * <dodf>rftrifvfd</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining bt lfbst onf
     * <dodf>ImbgfTypfSpfdififr</dodf> rfprfsfnting suggfstfd imbgf
     * typfs for dfdoding tif durrfnt givfn imbgf.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs rfbding tif formbt
     * informbtion from tif input sourdf.
     *
     * @sff ImbgfRfbdPbrbm#sftDfstinbtion(BufffrfdImbgf)
     * @sff ImbgfRfbdPbrbm#sftDfstinbtionTypf(ImbgfTypfSpfdififr)
     */
    publid bbstrbdt Itfrbtor<ImbgfTypfSpfdififr>
        gftImbgfTypfs(int imbgfIndfx) tirows IOExdfption;

    /**
     * Rfturns b dffbult <dodf>ImbgfRfbdPbrbm</dodf> objfdt
     * bppropribtf for tiis formbt.  All subdlbssfs siould dffinf b
     * sft of dffbult vblufs for bll pbrbmftfrs bnd rfturn tifm witi
     * tiis dbll.  Tiis mftiod mby bf dbllfd bfforf tif input sourdf
     * is sft.
     *
     * <p> Tif dffbult implfmfntbtion donstrudts bnd rfturns b nfw
     * <dodf>ImbgfRfbdPbrbm</dodf> objfdt tibt dofs not bllow sourdf
     * sdbling (<i>i.f.</i>, it rfturns <dodf>nfw
     * ImbgfRfbdPbrbm()</dodf>.
     *
     * @rfturn bn <dodf>ImbgfRfbdPbrbm</dodf> objfdt wiidi mby bf usfd
     * to dontrol tif dfdoding prodfss using b sft of dffbult sfttings.
     */
    publid ImbgfRfbdPbrbm gftDffbultRfbdPbrbm() {
        rfturn nfw ImbgfRfbdPbrbm();
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtb</dodf> objfdt rfprfsfnting tif
     * mftbdbtb bssodibtfd witi tif input sourdf bs b wiolf (i.f., not
     * bssodibtfd witi bny pbrtidulbr imbgf), or <dodf>null</dodf> if
     * tif rfbdfr dofs not support rfbding mftbdbtb, is sft to ignorf
     * mftbdbtb, or if no mftbdbtb is bvbilbblf.
     *
     * @rfturn bn <dodf>IIOMftbdbtb</dodf> objfdt, or <dodf>null</dodf>.
     *
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid bbstrbdt IIOMftbdbtb gftStrfbmMftbdbtb() tirows IOExdfption;

    /**
     * Rfturns bn <dodf>IIOMftbdbtb</dodf> objfdt rfprfsfnting tif
     * mftbdbtb bssodibtfd witi tif input sourdf bs b wiolf (i.f.,
     * not bssodibtfd witi bny pbrtidulbr imbgf).  If no sudi dbtb
     * fxists, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif rfsulting mftbdbtb objfdt is only rfsponsiblf for
     * rfturning dodumfnts in tif formbt nbmfd by
     * <dodf>formbtNbmf</dodf>.  Witiin bny dodumfnts tibt brf
     * rfturnfd, only nodfs wiosf nbmfs brf mfmbfrs of
     * <dodf>nodfNbmfs</dodf> brf rfquirfd to bf rfturnfd.  In tiis
     * wby, tif bmount of mftbdbtb prodfssing donf by tif rfbdfr mby
     * bf kfpt to b minimum, bbsfd on wibt informbtion is bdtublly
     * nffdfd.
     *
     * <p> If <dodf>formbtNbmf</dodf> is not tif nbmf of b supportfd
     * mftbdbtb formbt, <dodf>null</dodf> is rfturnfd.
     *
     * <p> In bll dbsfs, it is lfgbl to rfturn b morf dbpbblf mftbdbtb
     * objfdt tibn stridtly nfdfssbry.  Tif formbt nbmf bnd nodf nbmfs
     * brf mfrfly iints tibt mby bf usfd to rfdudf tif rfbdfr's
     * worklobd.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns tif rfsult of
     * dblling <dodf>gftStrfbmMftbdbtb()</dodf>, bftfr difdking tibt
     * tif formbt nbmf is supportfd.  If it is not,
     * <dodf>null</dodf> is rfturnfd.
     *
     * @pbrbm formbtNbmf b mftbdbtb formbt nbmf tibt mby bf usfd to rftrifvf
     * b dodumfnt from tif rfturnfd <dodf>IIOMftbdbtb</dodf> objfdt.
     * @pbrbm nodfNbmfs b <dodf>Sft</dodf> dontbining tif nbmfs of
     * nodfs tibt mby bf dontbinfd in b rftrifvfd dodumfnt.
     *
     * @rfturn bn <dodf>IIOMftbdbtb</dodf> objfdt, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>nodfNbmfs</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid IIOMftbdbtb gftStrfbmMftbdbtb(String formbtNbmf,
                                         Sft<String> nodfNbmfs)
        tirows IOExdfption
    {
        rfturn gftMftbdbtb(formbtNbmf, nodfNbmfs, truf, 0);
    }

    privbtf IIOMftbdbtb gftMftbdbtb(String formbtNbmf,
                                    Sft<String> nodfNbmfs,
                                    boolfbn wbntStrfbm,
                                    int imbgfIndfx) tirows IOExdfption {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }
        if (nodfNbmfs == null) {
            tirow nfw IllfgblArgumfntExdfption("nodfNbmfs == null!");
        }
        IIOMftbdbtb mftbdbtb =
            wbntStrfbm
            ? gftStrfbmMftbdbtb()
            : gftImbgfMftbdbtb(imbgfIndfx);
        if (mftbdbtb != null) {
            if (mftbdbtb.isStbndbrdMftbdbtbFormbtSupportfd() &&
                formbtNbmf.fqubls
                (IIOMftbdbtbFormbtImpl.stbndbrdMftbdbtbFormbtNbmf)) {
                rfturn mftbdbtb;
            }
            String nbtivfNbmf = mftbdbtb.gftNbtivfMftbdbtbFormbtNbmf();
            if (nbtivfNbmf != null && formbtNbmf.fqubls(nbtivfNbmf)) {
                rfturn mftbdbtb;
            }
            String[] fxtrbNbmfs = mftbdbtb.gftExtrbMftbdbtbFormbtNbmfs();
            if (fxtrbNbmfs != null) {
                for (int i = 0; i < fxtrbNbmfs.lfngti; i++) {
                    if (formbtNbmf.fqubls(fxtrbNbmfs[i])) {
                        rfturn mftbdbtb;
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtb</dodf> objfdt dontbining mftbdbtb
     * bssodibtfd witi tif givfn imbgf, or <dodf>null</dodf> if tif
     * rfbdfr dofs not support rfbding mftbdbtb, is sft to ignorf
     * mftbdbtb, or if no mftbdbtb is bvbilbblf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf wiosf mftbdbtb is to
     * bf rftrifvfd.
     *
     * @rfturn bn <dodf>IIOMftbdbtb</dodf> objfdt, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid bbstrbdt IIOMftbdbtb gftImbgfMftbdbtb(int imbgfIndfx)
        tirows IOExdfption;

    /**
     * Rfturns bn <dodf>IIOMftbdbtb</dodf> objfdt rfprfsfnting tif
     * mftbdbtb bssodibtfd witi tif givfn imbgf, or <dodf>null</dodf>
     * if tif rfbdfr dofs not support rfbding mftbdbtb or nonf
     * is bvbilbblf.
     *
     * <p> Tif rfsulting mftbdbtb objfdt is only rfsponsiblf for
     * rfturning dodumfnts in tif formbt nbmfd by
     * <dodf>formbtNbmf</dodf>.  Witiin bny dodumfnts tibt brf
     * rfturnfd, only nodfs wiosf nbmfs brf mfmbfrs of
     * <dodf>nodfNbmfs</dodf> brf rfquirfd to bf rfturnfd.  In tiis
     * wby, tif bmount of mftbdbtb prodfssing donf by tif rfbdfr mby
     * bf kfpt to b minimum, bbsfd on wibt informbtion is bdtublly
     * nffdfd.
     *
     * <p> If <dodf>formbtNbmf</dodf> is not tif nbmf of b supportfd
     * mftbdbtb formbt, <dodf>null</dodf> mby bf rfturnfd.
     *
     * <p> In bll dbsfs, it is lfgbl to rfturn b morf dbpbblf mftbdbtb
     * objfdt tibn stridtly nfdfssbry.  Tif formbt nbmf bnd nodf nbmfs
     * brf mfrfly iints tibt mby bf usfd to rfdudf tif rfbdfr's
     * worklobd.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns tif rfsult of
     * dblling <dodf>gftImbgfMftbdbtb(imbgfIndfx)</dodf>, bftfr
     * difdking tibt tif formbt nbmf is supportfd.  If it is not,
     * <dodf>null</dodf> is rfturnfd.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf wiosf mftbdbtb is to
     * bf rftrifvfd.
     * @pbrbm formbtNbmf b mftbdbtb formbt nbmf tibt mby bf usfd to rftrifvf
     * b dodumfnt from tif rfturnfd <dodf>IIOMftbdbtb</dodf> objfdt.
     * @pbrbm nodfNbmfs b <dodf>Sft</dodf> dontbining tif nbmfs of
     * nodfs tibt mby bf dontbinfd in b rftrifvfd dodumfnt.
     *
     * @rfturn bn <dodf>IIOMftbdbtb</dodf> objfdt, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>nodfNbmfs</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid IIOMftbdbtb gftImbgfMftbdbtb(int imbgfIndfx,
                                        String formbtNbmf,
                                        Sft<String> nodfNbmfs)
        tirows IOExdfption {
        rfturn gftMftbdbtb(formbtNbmf, nodfNbmfs, fblsf, imbgfIndfx);
    }

    /**
     * Rfbds tif imbgf indfxfd by <dodf>imbgfIndfx</dodf> bnd rfturns
     * it bs b domplftf <dodf>BufffrfdImbgf</dodf>, using b dffbult
     * <dodf>ImbgfRfbdPbrbm</dodf>.  Tiis is b donvfnifndf mftiod
     * tibt dblls <dodf>rfbd(imbgfIndfx, null)</dodf>.
     *
     * <p> Tif imbgf rfturnfd will bf formbttfd bddording to tif first
     * <dodf>ImbgfTypfSpfdififr</dodf> rfturnfd from
     * <dodf>gftImbgfTypfs</dodf>.
     *
     * <p> Any rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts
     * will bf notififd by dblling tifir <dodf>imbgfStbrtfd</dodf>
     * mftiod, followfd by dblls to tifir <dodf>imbgfProgrfss</dodf>
     * mftiod bs tif rfbd progrfssfs.  Finblly tifir
     * <dodf>imbgfComplftf</dodf> mftiod will bf dbllfd.
     * <dodf>IIORfbdUpdbtfListfnfr</dodf> objfdts mby bf updbtfd bt
     * otifr timfs during tif rfbd bs pixfls brf dfdodfd.  Finblly,
     * <dodf>IIORfbdWbrningListfnfr</dodf> objfdts will rfdfivf
     * notifidbtion of bny non-fbtbl wbrnings tibt oddur during
     * dfdoding.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     *
     * @rfturn tif dfsirfd portion of tif imbgf bs b
     * <dodf>BufffrfdImbgf</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid BufffrfdImbgf rfbd(int imbgfIndfx) tirows IOExdfption {
        rfturn rfbd(imbgfIndfx, null);
    }

    /**
     * Rfbds tif imbgf indfxfd by <dodf>imbgfIndfx</dodf> bnd rfturns
     * it bs b domplftf <dodf>BufffrfdImbgf</dodf>, using b supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf>.
     *
     * <p> Tif bdtubl <dodf>BufffrfdImbgf</dodf> rfturnfd will bf
     * diosfn using tif blgoritim dffinfd by tif
     * <dodf>gftDfstinbtion</dodf> mftiod.
     *
     * <p> Any rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts
     * will bf notififd by dblling tifir <dodf>imbgfStbrtfd</dodf>
     * mftiod, followfd by dblls to tifir <dodf>imbgfProgrfss</dodf>
     * mftiod bs tif rfbd progrfssfs.  Finblly tifir
     * <dodf>imbgfComplftf</dodf> mftiod will bf dbllfd.
     * <dodf>IIORfbdUpdbtfListfnfr</dodf> objfdts mby bf updbtfd bt
     * otifr timfs during tif rfbd bs pixfls brf dfdodfd.  Finblly,
     * <dodf>IIORfbdWbrningListfnfr</dodf> objfdts will rfdfivf
     * notifidbtion of bny non-fbtbl wbrnings tibt oddur during
     * dfdoding.
     *
     * <p> Tif sft of sourdf bbnds to bf rfbd bnd dfstinbtion bbnds to
     * bf writtfn is dftfrminfd by dblling <dodf>gftSourdfBbnds</dodf>
     * bnd <dodf>gftDfstinbtionBbnds</dodf> on tif supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf>.  If tif lfngtis of tif brrbys
     * rfturnfd by tifsf mftiods difffr, tif sft of sourdf bbnds
     * dontbins bn indfx lbrgfr tibt tif lbrgfst bvbilbblf sourdf
     * indfx, or tif sft of dfstinbtion bbnds dontbins bn indfx lbrgfr
     * tibn tif lbrgfst lfgbl dfstinbtion indfx, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * <p> If tif supplifd <dodf>ImbgfRfbdPbrbm</dodf> dontbins
     * optionbl sftting vblufs not supportfd by tiis rfbdfr (<i>f.g.</i>
     * sourdf rfndfr sizf or bny formbt-spfdifid sfttings), tify will
     * bf ignorfd.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf> usfd to dontrol
     * tif rfbding prodfss, or <dodf>null</dodf>.
     *
     * @rfturn tif dfsirfd portion of tif imbgf bs b
     * <dodf>BufffrfdImbgf</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif sft of sourdf bnd
     * dfstinbtion bbnds spfdififd by
     * <dodf>pbrbm.gftSourdfBbnds</dodf> bnd
     * <dodf>pbrbm.gftDfstinbtionBbnds</dodf> difffr in lfngti or
     * indludf indidfs tibt brf out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif rfsulting imbgf would
     * ibvf b widti or ifigit lfss tibn 1.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid bbstrbdt BufffrfdImbgf rfbd(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption;

    /**
     * Rfbds tif imbgf indfxfd by <dodf>imbgfIndfx</dodf> bnd rfturns
     * bn <dodf>IIOImbgf</dodf> dontbining tif imbgf, tiumbnbils, bnd
     * bssodibtfd imbgf mftbdbtb, using b supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf>.
     *
     * <p> Tif bdtubl <dodf>BufffrfdImbgf</dodf> rfffrfndfd by tif
     * rfturnfd <dodf>IIOImbgf</dodf> will bf diosfn using tif
     * blgoritim dffinfd by tif <dodf>gftDfstinbtion</dodf> mftiod.
     *
     * <p> Any rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts
     * will bf notififd by dblling tifir <dodf>imbgfStbrtfd</dodf>
     * mftiod, followfd by dblls to tifir <dodf>imbgfProgrfss</dodf>
     * mftiod bs tif rfbd progrfssfs.  Finblly tifir
     * <dodf>imbgfComplftf</dodf> mftiod will bf dbllfd.
     * <dodf>IIORfbdUpdbtfListfnfr</dodf> objfdts mby bf updbtfd bt
     * otifr timfs during tif rfbd bs pixfls brf dfdodfd.  Finblly,
     * <dodf>IIORfbdWbrningListfnfr</dodf> objfdts will rfdfivf
     * notifidbtion of bny non-fbtbl wbrnings tibt oddur during
     * dfdoding.
     *
     * <p> Tif sft of sourdf bbnds to bf rfbd bnd dfstinbtion bbnds to
     * bf writtfn is dftfrminfd by dblling <dodf>gftSourdfBbnds</dodf>
     * bnd <dodf>gftDfstinbtionBbnds</dodf> on tif supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf>.  If tif lfngtis of tif brrbys
     * rfturnfd by tifsf mftiods difffr, tif sft of sourdf bbnds
     * dontbins bn indfx lbrgfr tibt tif lbrgfst bvbilbblf sourdf
     * indfx, or tif sft of dfstinbtion bbnds dontbins bn indfx lbrgfr
     * tibn tif lbrgfst lfgbl dfstinbtion indfx, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * <p> Tiumbnbils will bf rfturnfd in tifir fntirfty rfgbrdlfss of
     * tif rfgion sfttings.
     *
     * <p> If tif supplifd <dodf>ImbgfRfbdPbrbm</dodf> dontbins
     * optionbl sftting vblufs not supportfd by tiis rfbdfr (<i>f.g.</i>
     * sourdf rfndfr sizf or bny formbt-spfdifid sfttings), tiosf
     * vblufs will bf ignorfd.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf> usfd to dontrol
     * tif rfbding prodfss, or <dodf>null</dodf>.
     *
     * @rfturn bn <dodf>IIOImbgf</dodf> dontbining tif dfsirfd portion
     * of tif imbgf, b sft of tiumbnbils, bnd bssodibtfd imbgf
     * mftbdbtb.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif sft of sourdf bnd
     * dfstinbtion bbnds spfdififd by
     * <dodf>pbrbm.gftSourdfBbnds</dodf> bnd
     * <dodf>pbrbm.gftDfstinbtionBbnds</dodf> difffr in lfngti or
     * indludf indidfs tibt brf out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif rfsulting imbgf
     * would ibvf b widti or ifigit lfss tibn 1.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid IIOImbgf rfbdAll(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption {
        if (imbgfIndfx < gftMinIndfx()) {
            tirow nfw IndfxOutOfBoundsExdfption("imbgfIndfx < gftMinIndfx()!");
        }

        BufffrfdImbgf im = rfbd(imbgfIndfx, pbrbm);

        ArrbyList<BufffrfdImbgf> tiumbnbils = null;
        int numTiumbnbils = gftNumTiumbnbils(imbgfIndfx);
        if (numTiumbnbils > 0) {
            tiumbnbils = nfw ArrbyList<>();
            for (int j = 0; j < numTiumbnbils; j++) {
                tiumbnbils.bdd(rfbdTiumbnbil(imbgfIndfx, j));
            }
        }

        IIOMftbdbtb mftbdbtb = gftImbgfMftbdbtb(imbgfIndfx);
        rfturn nfw IIOImbgf(im, tiumbnbils, mftbdbtb);
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll tif imbgfs,
     * tiumbnbils, bnd mftbdbtb, stbrting bt tif indfx givfn by
     * <dodf>gftMinIndfx</dodf>, from tif input sourdf in tif form of
     * <dodf>IIOImbgf</dodf> objfdts.  An <dodf>Itfrbtor</dodf>
     * dontbining <dodf>ImbgfRfbdPbrbm</dodf> objfdts is supplifd; onf
     * flfmfnt is donsumfd for fbdi imbgf rfbd from tif input sourdf
     * until no morf imbgfs brf bvbilbblf.  If tif rfbd pbrbm
     * <dodf>Itfrbtor</dodf> runs out of flfmfnts, but tifrf brf still
     * morf imbgfs bvbilbblf from tif input sourdf, dffbult rfbd
     * pbrbms brf usfd for tif rfmbining imbgfs.
     *
     * <p> If <dodf>pbrbms</dodf> is <dodf>null</dodf>, b dffbult rfbd
     * pbrbm will bf usfd for bll imbgfs.
     *
     * <p> Tif bdtubl <dodf>BufffrfdImbgf</dodf> rfffrfndfd by tif
     * rfturnfd <dodf>IIOImbgf</dodf> will bf diosfn using tif
     * blgoritim dffinfd by tif <dodf>gftDfstinbtion</dodf> mftiod.
     *
     * <p> Any rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts
     * will bf notififd by dblling tifir <dodf>sfqufndfStbrtfd</dodf>
     * mftiod ondf.  Tifn, for fbdi imbgf dfdodfd, tifrf will bf b
     * dbll to <dodf>imbgfStbrtfd</dodf>, followfd by dblls to
     * <dodf>imbgfProgrfss</dodf> bs tif rfbd progrfssfs, bnd finblly
     * to <dodf>imbgfComplftf</dodf>.  Tif
     * <dodf>sfqufndfComplftf</dodf> mftiod will bf dbllfd bftfr tif
     * lbst imbgf ibs bffn dfdodfd.
     * <dodf>IIORfbdUpdbtfListfnfr</dodf> objfdts mby bf updbtfd bt
     * otifr timfs during tif rfbd bs pixfls brf dfdodfd.  Finblly,
     * <dodf>IIORfbdWbrningListfnfr</dodf> objfdts will rfdfivf
     * notifidbtion of bny non-fbtbl wbrnings tibt oddur during
     * dfdoding.
     *
     * <p> Tif sft of sourdf bbnds to bf rfbd bnd dfstinbtion bbnds to
     * bf writtfn is dftfrminfd by dblling <dodf>gftSourdfBbnds</dodf>
     * bnd <dodf>gftDfstinbtionBbnds</dodf> on tif supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf>.  If tif lfngtis of tif brrbys
     * rfturnfd by tifsf mftiods difffr, tif sft of sourdf bbnds
     * dontbins bn indfx lbrgfr tibt tif lbrgfst bvbilbblf sourdf
     * indfx, or tif sft of dfstinbtion bbnds dontbins bn indfx lbrgfr
     * tibn tif lbrgfst lfgbl dfstinbtion indfx, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     *
     * <p> Tiumbnbils will bf rfturnfd in tifir fntirfty rfgbrdlfss of tif
     * rfgion sfttings.
     *
     * <p> If bny of tif supplifd <dodf>ImbgfRfbdPbrbm</dodf>s dontbin
     * optionbl sftting vblufs not supportfd by tiis rfbdfr (<i>f.g.</i>
     * sourdf rfndfr sizf or bny formbt-spfdifid sfttings), tify will
     * bf ignorfd.
     *
     * @pbrbm pbrbms bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfRfbdPbrbm</dodf> objfdts.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> rfprfsfnting tif
     * dontfnts of tif input sourdf bs <dodf>IIOImbgf</dodf>s.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IllfgblArgumfntExdfption if bny
     * non-<dodf>null</dodf> flfmfnt of <dodf>pbrbms</dodf> is not bn
     * <dodf>ImbgfRfbdPbrbm</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if tif sft of sourdf bnd
     * dfstinbtion bbnds spfdififd by
     * <dodf>pbrbm.gftSourdfBbnds</dodf> bnd
     * <dodf>pbrbm.gftDfstinbtionBbnds</dodf> difffr in lfngti or
     * indludf indidfs tibt brf out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if b rfsulting imbgf would
     * ibvf b widti or ifigit lfss tibn 1.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     *
     * @sff ImbgfRfbdPbrbm
     * @sff IIOImbgf
     */
    publid Itfrbtor<IIOImbgf>
        rfbdAll(Itfrbtor<? fxtfnds ImbgfRfbdPbrbm> pbrbms)
        tirows IOExdfption
    {
        List<IIOImbgf> output = nfw ArrbyList<>();

        int imbgfIndfx = gftMinIndfx();

        // Inform IIORfbdProgrfssListfnfrs wf'rf stbrting b sfqufndf
        prodfssSfqufndfStbrtfd(imbgfIndfx);

        wiilf (truf) {
            // Inform IIORfbdProgrfssListfnfrs bnd IIORfbdUpdbtfListfnfrs
            // tibt wf'rf stbrting b nfw imbgf

            ImbgfRfbdPbrbm pbrbm = null;
            if (pbrbms != null && pbrbms.ibsNfxt()) {
                Objfdt o = pbrbms.nfxt();
                if (o != null) {
                    if (o instbndfof ImbgfRfbdPbrbm) {
                        pbrbm = (ImbgfRfbdPbrbm)o;
                    } flsf {
                        tirow nfw IllfgblArgumfntExdfption
                            ("Non-ImbgfRfbdPbrbm supplifd bs pbrt of pbrbms!");
                    }
                }
            }

            BufffrfdImbgf bi = null;
            try {
                bi = rfbd(imbgfIndfx, pbrbm);
            } dbtdi (IndfxOutOfBoundsExdfption f) {
                brfbk;
            }

            ArrbyList<BufffrfdImbgf> tiumbnbils = null;
            int numTiumbnbils = gftNumTiumbnbils(imbgfIndfx);
            if (numTiumbnbils > 0) {
                tiumbnbils = nfw ArrbyList<>();
                for (int j = 0; j < numTiumbnbils; j++) {
                    tiumbnbils.bdd(rfbdTiumbnbil(imbgfIndfx, j));
                }
            }

            IIOMftbdbtb mftbdbtb = gftImbgfMftbdbtb(imbgfIndfx);
            IIOImbgf im = nfw IIOImbgf(bi, tiumbnbils, mftbdbtb);
            output.bdd(im);

            ++imbgfIndfx;
        }

        // Inform IIORfbdProgrfssListfnfrs wf'rf fnding b sfqufndf
        prodfssSfqufndfComplftf();

        rfturn output.itfrbtor();
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis plug-in supports rfbding
     * just b {@link jbvb.bwt.imbgf.Rbstfr Rbstfr} of pixfl dbtb.
     * If tiis mftiod rfturns <dodf>fblsf</dodf>, dblls to
     * {@link #rfbdRbstfr rfbdRbstfr} or {@link #rfbdTilfRbstfr rfbdTilfRbstfr}
     * will tirow bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>fblsf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tiis plug-in supports rfbding rbw
     * <dodf>Rbstfr</dodf>s.
     *
     * @sff #rfbdRbstfr
     * @sff #rfbdTilfRbstfr
     */
    publid boolfbn dbnRfbdRbstfr() {
        rfturn fblsf;
    }

    /**
     * Rfturns b nfw <dodf>Rbstfr</dodf> objfdt dontbining tif rbw pixfl dbtb
     * from tif imbgf strfbm, witiout bny dolor donvfrsion bpplifd.  Tif
     * bpplidbtion must dftfrminf iow to intfrprft tif pixfl dbtb by otifr
     * mfbns.  Any dfstinbtion or imbgf-typf pbrbmftfrs in tif supplifd
     * <dodf>ImbgfRfbdPbrbm</dodf> objfdt brf ignorfd, but bll otifr
     * pbrbmftfrs brf usfd fxbdtly bs in tif {@link #rfbd rfbd}
     * mftiod, fxdfpt tibt bny dfstinbtion offsft is usfd bs b logidbl rbtifr
     * tibn b piysidbl offsft.  Tif sizf of tif rfturnfd <dodf>Rbstfr</dodf>
     * will blwbys bf tibt of tif sourdf rfgion dlippfd to tif bdtubl imbgf.
     * Logidbl offsfts in tif strfbm itsflf brf ignorfd.
     *
     * <p> Tiis mftiod bllows formbts tibt normblly bpply b dolor
     * donvfrsion, sudi bs JPEG, bnd formbts tibt do not normblly ibvf bn
     * bssodibtfd dolorspbdf, sudi bs rfmotf sfnsing or mfdidbl imbging dbtb,
     * to providf bddfss to rbw pixfl dbtb.
     *
     * <p> Any rfgistfrfd <dodf>rfbdUpdbtfListfnfr</dodf>s brf ignorfd, bs
     * tifrf is no <dodf>BufffrfdImbgf</dodf>, but bll otifr listfnfrs brf
     * dbllfd fxbdtly bs tify brf for tif {@link #rfbd rfbd} mftiod.
     *
     * <p> If {@link #dbnRfbdRbstfr dbnRfbdRbstfr()} rfturns
     * <dodf>fblsf</dodf>, tiis mftiod tirows bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * <p> If tif supplifd <dodf>ImbgfRfbdPbrbm</dodf> dontbins
     * optionbl sftting vblufs not supportfd by tiis rfbdfr (<i>f.g.</i>
     * sourdf rfndfr sizf or bny formbt-spfdifid sfttings), tify will
     * bf ignorfd.
     *
     * <p> Tif dffbult implfmfntbtion tirows bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rfbd.
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf> usfd to dontrol
     * tif rfbding prodfss, or <dodf>null</dodf>.
     *
     * @rfturn tif dfsirfd portion of tif imbgf bs b
     * <dodf>Rbstfr</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis plug-in dofs not
     * support rfbding rbw <dodf>Rbstfr</dodf>s.
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     *
     * @sff #dbnRfbdRbstfr
     * @sff #rfbd
     * @sff jbvb.bwt.imbgf.Rbstfr
     */
    publid Rbstfr rfbdRbstfr(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("rfbdRbstfr not supportfd!");
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif imbgf is orgbnizfd into
     * <i>tilfs</i>, tibt is, fqubl-sizfd non-ovfrlbpping rfdtbnglfs.
     *
     * <p> A rfbdfr plug-in mby dioosf wiftifr or not to fxposf tiling
     * tibt is prfsfnt in tif imbgf bs it is storfd.  It mby fvfn
     * dioosf to bdvfrtisf tiling wifn nonf is fxpliditly prfsfnt.  In
     * gfnfrbl, tiling siould only bf bdvfrtisfd if tifrf is somf
     * bdvbntbgf (in spffd or spbdf) to bddfssing individubl tilfs.
     * Rfgbrdlfss of wiftifr tif rfbdfr bdvfrtisfs tiling, it must bf
     * dbpbblf of rfbding bn brbitrbry rfdtbngulbr rfgion spfdififd in
     * bn <dodf>ImbgfRfbdPbrbm</dodf>.
     *
     * <p> A rfbdfr for wiidi bll imbgfs brf gubrbntffd to bf tilfd,
     * or brf gubrbntffd not to bf tilfd, mby rfturn <dodf>truf</dodf>
     * or <dodf>fblsf</dodf> rfspfdtivfly witiout bddfssing bny imbgf
     * dbtb.  In sudi dbsfs, it is not nfdfssbry to tirow bn fxdfption
     * fvfn if no input sourdf ibs bffn sft or tif imbgf indfx is out
     * of bounds.
     *
     * <p> Tif dffbult implfmfntbtion just rfturns <dodf>fblsf</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @rfturn <dodf>truf</dodf> if tif imbgf is tilfd.
     *
     * @fxdfption IllfgblStbtfExdfption if bn input sourdf is rfquirfd
     * to dftfrminf tif rfturn vbluf, but nonf ibs bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if bn imbgf must bf
     * bddfssfd to dftfrminf tif rfturn vbluf, but tif supplifd indfx
     * is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid boolfbn isImbgfTilfd(int imbgfIndfx) tirows IOExdfption {
        rfturn fblsf;
    }

    /**
     * Rfturns tif widti of b tilf in tif givfn imbgf.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>gftWidti(imbgfIndfx)</dodf>, wiidi is dorrfdt for
     * non-tilfd imbgfs.  Rfbdfrs tibt support tiling siould ovfrridf
     * tiis mftiod.
     *
     * @rfturn tif widti of b tilf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTilfWidti(int imbgfIndfx) tirows IOExdfption {
        rfturn gftWidti(imbgfIndfx);
    }

    /**
     * Rfturns tif ifigit of b tilf in tif givfn imbgf.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>gftHfigit(imbgfIndfx)</dodf>, wiidi is dorrfdt for
     * non-tilfd imbgfs.  Rfbdfrs tibt support tiling siould ovfrridf
     * tiis mftiod.
     *
     * @rfturn tif ifigit of b tilf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTilfHfigit(int imbgfIndfx) tirows IOExdfption {
        rfturn gftHfigit(imbgfIndfx);
    }

    /**
     * Rfturns tif X doordinbtf of tif uppfr-lfft dornfr of tilf (0,
     * 0) in tif givfn imbgf.
     *
     * <p> A rfbdfr for wiidi tif tilf grid X offsft blwbys ibs tif
     * sbmf vbluf (usublly 0), mby rfturn tif vbluf witiout bddfssing
     * bny imbgf dbtb.  In sudi dbsfs, it is not nfdfssbry to tirow bn
     * fxdfption fvfn if no input sourdf ibs bffn sft or tif imbgf
     * indfx is out of bounds.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns 0, wiidi is
     * dorrfdt for non-tilfd imbgfs bnd tilfd imbgfs in most formbts.
     * Rfbdfrs tibt support tiling witi non-(0, 0) offsfts siould
     * ovfrridf tiis mftiod.
     *
     * @rfturn tif X offsft of tif tilf grid.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @fxdfption IllfgblStbtfExdfption if bn input sourdf is rfquirfd
     * to dftfrminf tif rfturn vbluf, but nonf ibs bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if bn imbgf must bf
     * bddfssfd to dftfrminf tif rfturn vbluf, but tif supplifd indfx
     * is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTilfGridXOffsft(int imbgfIndfx) tirows IOExdfption {
        rfturn 0;
    }

    /**
     * Rfturns tif Y doordinbtf of tif uppfr-lfft dornfr of tilf (0,
     * 0) in tif givfn imbgf.
     *
     * <p> A rfbdfr for wiidi tif tilf grid Y offsft blwbys ibs tif
     * sbmf vbluf (usublly 0), mby rfturn tif vbluf witiout bddfssing
     * bny imbgf dbtb.  In sudi dbsfs, it is not nfdfssbry to tirow bn
     * fxdfption fvfn if no input sourdf ibs bffn sft or tif imbgf
     * indfx is out of bounds.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns 0, wiidi is
     * dorrfdt for non-tilfd imbgfs bnd tilfd imbgfs in most formbts.
     * Rfbdfrs tibt support tiling witi non-(0, 0) offsfts siould
     * ovfrridf tiis mftiod.
     *
     * @rfturn tif Y offsft of tif tilf grid.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf qufrifd.
     *
     * @fxdfption IllfgblStbtfExdfption if bn input sourdf is rfquirfd
     * to dftfrminf tif rfturn vbluf, but nonf ibs bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if bn imbgf must bf
     * bddfssfd to dftfrminf tif rfturn vbluf, but tif supplifd indfx
     * is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTilfGridYOffsft(int imbgfIndfx) tirows IOExdfption {
        rfturn 0;
    }

    /**
     * Rfbds tif tilf indidbtfd by tif <dodf>tilfX</dodf> bnd
     * <dodf>tilfY</dodf> brgumfnts, rfturning it bs b
     * <dodf>BufffrfdImbgf</dodf>.  If tif brgumfnts brf out of rbngf,
     * bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.  If tif
     * imbgf is not tilfd, tif vblufs 0, 0 will rfturn tif fntirf
     * imbgf; bny otifr vblufs will dbusf bn
     * <dodf>IllfgblArgumfntExdfption</dodf> to bf tirown.
     *
     * <p> Tiis mftiod is mfrfly b donvfnifndf fquivblfnt to dblling
     * <dodf>rfbd(int, ImbgfRfbdPbrbm)</dodf> witi b rfbd pbrbm
     * spfdifying b sourdf rfgion ibving offsfts of
     * <dodf>tilfX*gftTilfWidti(imbgfIndfx)</dodf>,
     * <dodf>tilfY*gftTilfHfigit(imbgfIndfx)</dodf> bnd widti bnd
     * ifigit of <dodf>gftTilfWidti(imbgfIndfx)</dodf>,
     * <dodf>gftTilfHfigit(imbgfIndfx)</dodf>; bnd subsbmpling
     * fbdtors of 1 bnd offsfts of 0.  To subsbmplf b tilf, dbll
     * <dodf>rfbd</dodf> witi b rfbd pbrbm spfdifying tiis rfgion
     * bnd difffrfnt subsbmpling pbrbmftfrs.
     *
     * <p> Tif dffbult implfmfntbtion rfturns tif fntirf imbgf if
     * <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf> brf 0, or tirows
     * bn <dodf>IllfgblArgumfntExdfption</dodf> otifrwisf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm tilfX tif dolumn indfx (stbrting witi 0) of tif tilf
     * to bf rftrifvfd.
     * @pbrbm tilfY tif row indfx (stbrting witi 0) of tif tilf
     * to bf rftrifvfd.
     *
     * @rfturn tif tilf bs b <dodf>BufffrfdImbgf</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>imbgfIndfx</dodf>
     * is out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif tilf indidfs brf
     * out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid BufffrfdImbgf rfbdTilf(int imbgfIndfx,
                                  int tilfX, int tilfY) tirows IOExdfption {
        if ((tilfX != 0) || (tilfY != 0)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid tilf indidfs");
        }
        rfturn rfbd(imbgfIndfx);
    }

    /**
     * Rfturns b nfw <dodf>Rbstfr</dodf> objfdt dontbining tif rbw
     * pixfl dbtb from tif tilf, witiout bny dolor donvfrsion bpplifd.
     * Tif bpplidbtion must dftfrminf iow to intfrprft tif pixfl dbtb by otifr
     * mfbns.
     *
     * <p> If {@link #dbnRfbdRbstfr dbnRfbdRbstfr()} rfturns
     * <dodf>fblsf</dodf>, tiis mftiod tirows bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * <p> Tif dffbult implfmfntbtion difdks if rfbding
     * <dodf>Rbstfr</dodf>s is supportfd, bnd if so dblls {@link
     * #rfbdRbstfr rfbdRbstfr(imbgfIndfx, null)} if
     * <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf> brf 0, or tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> otifrwisf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm tilfX tif dolumn indfx (stbrting witi 0) of tif tilf
     * to bf rftrifvfd.
     * @pbrbm tilfY tif row indfx (stbrting witi 0) of tif tilf
     * to bf rftrifvfd.
     *
     * @rfturn tif tilf bs b <dodf>Rbstfr</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis plug-in dofs not
     * support rfbding rbw <dodf>Rbstfr</dodf>s.
     * @fxdfption IllfgblArgumfntExdfption if tif tilf indidfs brf
     * out of bounds.
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>imbgfIndfx</dodf>
     * is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     *
     * @sff #rfbdTilf
     * @sff #rfbdRbstfr
     * @sff jbvb.bwt.imbgf.Rbstfr
     */
    publid Rbstfr rfbdTilfRbstfr(int imbgfIndfx,
                                 int tilfX, int tilfY) tirows IOExdfption {
        if (!dbnRfbdRbstfr()) {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("rfbdTilfRbstfr not supportfd!");
        }
        if ((tilfX != 0) || (tilfY != 0)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid tilf indidfs");
        }
        rfturn rfbdRbstfr(imbgfIndfx, null);
    }

    // RfndfrfdImbgfs

    /**
     * Rfturns b <dodf>RfndfrfdImbgf</dodf> objfdt tibt dontbins tif
     * dontfnts of tif imbgf indfxfd by <dodf>imbgfIndfx</dodf>.  By
     * dffbult, tif rfturnfd imbgf is simply tif
     * <dodf>BufffrfdImbgf</dodf> rfturnfd by <dodf>rfbd(imbgfIndfx,
     * pbrbm)</dodf>.
     *
     * <p> Tif sfmbntids of tiis mftiod mby difffr from tiosf of tif
     * otifr <dodf>rfbd</dodf> mftiods in sfvfrbl wbys.  First, bny
     * dfstinbtion imbgf bnd/or imbgf typf sft in tif
     * <dodf>ImbgfRfbdPbrbm</dodf> mby bf ignorfd.  Sfdond, tif usubl
     * listfnfr dblls brf not gubrbntffd to bf mbdf, or to bf
     * mfbningful if tify brf.  Tiis is bfdbusf tif rfturnfd imbgf mby
     * not bf fully populbtfd witi pixfl dbtb bt tif timf it is
     * rfturnfd, or indffd bt bny timf.
     *
     * <p> If tif supplifd <dodf>ImbgfRfbdPbrbm</dodf> dontbins
     * optionbl sftting vblufs not supportfd by tiis rfbdfr (<i>f.g.</i>
     * sourdf rfndfr sizf or bny formbt-spfdifid sfttings), tify will
     * bf ignorfd.
     *
     * <p> Tif dffbult implfmfntbtion just dblls
     * {@link #rfbd rfbd(imbgfIndfx, pbrbm)}.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf> usfd to dontrol
     * tif rfbding prodfss, or <dodf>null</dodf>.
     *
     * @rfturn b <dodf>RfndfrfdImbgf</dodf> objfdt providing b vifw of
     * tif imbgf.
     *
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn
     * sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif sft of sourdf bnd
     * dfstinbtion bbnds spfdififd by
     * <dodf>pbrbm.gftSourdfBbnds</dodf> bnd
     * <dodf>pbrbm.gftDfstinbtionBbnds</dodf> difffr in lfngti or
     * indludf indidfs tibt brf out of bounds.
     * @fxdfption IllfgblArgumfntExdfption if tif rfsulting imbgf
     * would ibvf b widti or ifigit lfss tibn 1.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid RfndfrfdImbgf rfbdAsRfndfrfdImbgf(int imbgfIndfx,
                                             ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption {
        rfturn rfbd(imbgfIndfx, pbrbm);
    }

    // Tiumbnbils

    /**
     * Rfturns <dodf>truf</dodf> if tif imbgf formbt undfrstood by
     * tiis rfbdfr supports tiumbnbil prfvifw imbgfs bssodibtfd witi
     * it.  Tif dffbult implfmfntbtion rfturns <dodf>fblsf</dodf>.
     *
     * <p> If tiis mftiod rfturns <dodf>fblsf</dodf>,
     * <dodf>ibsTiumbnbils</dodf> bnd <dodf>gftNumTiumbnbils</dodf>
     * will rfturn <dodf>fblsf</dodf> bnd <dodf>0</dodf>,
     * rfspfdtivfly, bnd <dodf>rfbdTiumbnbil</dodf> will tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>, rfgbrdlfss of tifir
     * brgumfnts.
     *
     * <p> A rfbdfr tibt dofs not support tiumbnbils nffd not
     * implfmfnt bny of tif tiumbnbil-rflbtfd mftiods.
     *
     * @rfturn <dodf>truf</dodf> if tiumbnbils brf supportfd.
     */
    publid boolfbn rfbdfrSupportsTiumbnbils() {
        rfturn fblsf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif givfn imbgf ibs tiumbnbil
     * prfvifw imbgfs bssodibtfd witi it.  If tif formbt dofs not
     * support tiumbnbils (<dodf>rfbdfrSupportsTiumbnbils</dodf>
     * rfturns <dodf>fblsf</dodf>), <dodf>fblsf</dodf> will bf
     * rfturnfd rfgbrdlfss of wiftifr bn input sourdf ibs bffn sft or
     * wiftifr <dodf>imbgfIndfx</dodf> is in bounds.
     *
     * <p> Tif dffbult implfmfntbtion rfturns <dodf>truf</dodf> if
     * <dodf>gftNumTiumbnbils</dodf> rfturns b vbluf grfbtfr tibn 0.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bfing qufrifd.
     *
     * @rfturn <dodf>truf</dodf> if tif givfn imbgf ibs tiumbnbils.
     *
     * @fxdfption IllfgblStbtfExdfption if tif rfbdfr supports
     * tiumbnbils but tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif rfbdfr supports
     * tiumbnbils but <dodf>imbgfIndfx</dodf> is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid boolfbn ibsTiumbnbils(int imbgfIndfx) tirows IOExdfption {
        rfturn gftNumTiumbnbils(imbgfIndfx) > 0;
    }

    /**
     * Rfturns tif numbfr of tiumbnbil prfvifw imbgfs bssodibtfd witi
     * tif givfn imbgf.  If tif formbt dofs not support tiumbnbils,
     * (<dodf>rfbdfrSupportsTiumbnbils</dodf> rfturns
     * <dodf>fblsf</dodf>), <dodf>0</dodf> will bf rfturnfd rfgbrdlfss
     * of wiftifr bn input sourdf ibs bffn sft or wiftifr
     * <dodf>imbgfIndfx</dodf> is in bounds.
     *
     * <p> Tif dffbult implfmfntbtion rfturns 0 witiout difdking its
     * brgumfnt.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bfing qufrifd.
     *
     * @rfturn tif numbfr of tiumbnbils bssodibtfd witi tif givfn
     * imbgf.
     *
     * @fxdfption IllfgblStbtfExdfption if tif rfbdfr supports
     * tiumbnbils but tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if tif rfbdfr supports
     * tiumbnbils but <dodf>imbgfIndfx</dodf> is out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftNumTiumbnbils(int imbgfIndfx)
        tirows IOExdfption {
        rfturn 0;
    }

    /**
     * Rfturns tif widti of tif tiumbnbil prfvifw imbgf indfxfd by
     * <dodf>tiumbnbilIndfx</dodf>, bssodibtfd witi tif imbgf indfxfd
     * by <dodf>ImbgfIndfx</dodf>.
     *
     * <p> If tif rfbdfr dofs not support tiumbnbils,
     * (<dodf>rfbdfrSupportsTiumbnbils</dodf> rfturns
     * <dodf>fblsf</dodf>), bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>
     * will bf tirown.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>rfbdTiumbnbil(imbgfindfx,
     * tiumbnbilIndfx).gftWidti()</dodf>.  Subdlbssfs siould tifrfforf
     * ovfrridf tiis mftiod if possiblf in ordfr to bvoid fording tif
     * tiumbnbil to bf rfbd.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm tiumbnbilIndfx tif indfx of tif tiumbnbil to bf rftrifvfd.
     *
     * @rfturn tif widti of tif dfsirfd tiumbnbil bs bn <dodf>int</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiumbnbils brf not
     * supportfd.
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if fitifr of tif supplifd
     * indidfs brf out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTiumbnbilWidti(int imbgfIndfx, int tiumbnbilIndfx)
        tirows IOExdfption {
        rfturn rfbdTiumbnbil(imbgfIndfx, tiumbnbilIndfx).gftWidti();
    }

    /**
     * Rfturns tif ifigit of tif tiumbnbil prfvifw imbgf indfxfd by
     * <dodf>tiumbnbilIndfx</dodf>, bssodibtfd witi tif imbgf indfxfd
     * by <dodf>ImbgfIndfx</dodf>.
     *
     * <p> If tif rfbdfr dofs not support tiumbnbils,
     * (<dodf>rfbdfrSupportsTiumbnbils</dodf> rfturns
     * <dodf>fblsf</dodf>), bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>
     * will bf tirown.
     *
     * <p> Tif dffbult implfmfntbtion simply rfturns
     * <dodf>rfbdTiumbnbil(imbgfindfx,
     * tiumbnbilIndfx).gftHfigit()</dodf>.  Subdlbssfs siould
     * tifrfforf ovfrridf tiis mftiod if possiblf in ordfr to bvoid
     * fording tif tiumbnbil to bf rfbd.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm tiumbnbilIndfx tif indfx of tif tiumbnbil to bf rftrifvfd.
     *
     * @rfturn tif ifigit of tif dfsirfd tiumbnbil bs bn <dodf>int</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiumbnbils brf not
     * supportfd.
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if fitifr of tif supplifd
     * indidfs brf out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid int gftTiumbnbilHfigit(int imbgfIndfx, int tiumbnbilIndfx)
        tirows IOExdfption {
        rfturn rfbdTiumbnbil(imbgfIndfx, tiumbnbilIndfx).gftHfigit();
    }

    /**
     * Rfturns tif tiumbnbil prfvifw imbgf indfxfd by
     * <dodf>tiumbnbilIndfx</dodf>, bssodibtfd witi tif imbgf indfxfd
     * by <dodf>ImbgfIndfx</dodf> bs b <dodf>BufffrfdImbgf</dodf>.
     *
     * <p> Any rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts
     * will bf notififd by dblling tifir
     * <dodf>tiumbnbilStbrtfd</dodf>, <dodf>tiumbnbilProgrfss</dodf>,
     * bnd <dodf>tiumbnbilComplftf</dodf> mftiods.
     *
     * <p> If tif rfbdfr dofs not support tiumbnbils,
     * (<dodf>rfbdfrSupportsTiumbnbils</dodf> rfturns
     * <dodf>fblsf</dodf>), bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>
     * will bf tirown rfgbrdlfss of wiftifr bn input sourdf ibs bffn
     * sft or wiftifr tif indidfs brf in bounds.
     *
     * <p> Tif dffbult implfmfntbtion tirows bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf to bf rftrifvfd.
     * @pbrbm tiumbnbilIndfx tif indfx of tif tiumbnbil to bf rftrifvfd.
     *
     * @rfturn tif dfsirfd tiumbnbil bs b <dodf>BufffrfdImbgf</dodf>.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiumbnbils brf not
     * supportfd.
     * @fxdfption IllfgblStbtfExdfption if tif input sourdf ibs not bffn sft.
     * @fxdfption IndfxOutOfBoundsExdfption if fitifr of tif supplifd
     * indidfs brf out of bounds.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid BufffrfdImbgf rfbdTiumbnbil(int imbgfIndfx,
                                       int tiumbnbilIndfx)
        tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tiumbnbils not supportfd!");
    }

    // Abort

    /**
     * Rfqufsts tibt bny durrfnt rfbd opfrbtion bf bbortfd.  Tif
     * dontfnts of tif imbgf following tif bbort will bf undffinfd.
     *
     * <p> Rfbdfrs siould dbll <dodf>dlfbrAbortRfqufst</dodf> bt tif
     * bfginning of fbdi rfbd opfrbtion, bnd poll tif vbluf of
     * <dodf>bbortRfqufstfd</dodf> rfgulbrly during tif rfbd.
     */
    publid syndironizfd void bbort() {
        tiis.bbortFlbg = truf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if b rfqufst to bbort tif durrfnt
     * rfbd opfrbtion ibs bffn mbdf sindf tif rfbdfr wbs instbntibtfd or
     * <dodf>dlfbrAbortRfqufst</dodf> wbs dbllfd.
     *
     * @rfturn <dodf>truf</dodf> if tif durrfnt rfbd opfrbtion siould
     * bf bbortfd.
     *
     * @sff #bbort
     * @sff #dlfbrAbortRfqufst
     */
    protfdtfd syndironizfd boolfbn bbortRfqufstfd() {
        rfturn tiis.bbortFlbg;
    }

    /**
     * Clfbrs bny prfvious bbort rfqufst.  Aftfr tiis mftiod ibs bffn
     * dbllfd, <dodf>bbortRfqufstfd</dodf> will rfturn
     * <dodf>fblsf</dodf>.
     *
     * @sff #bbort
     * @sff #bbortRfqufstfd
     */
    protfdtfd syndironizfd void dlfbrAbortRfqufst() {
        tiis.bbortFlbg = fblsf;
    }

    // Listfnfrs

    // Add bn flfmfnt to b list, drfbting b nfw list if tif
    // fxisting list is null, bnd rfturn tif list.
    stbtid <T> List<T> bddToList(List<T> l, T flt) {
        if (l == null) {
            l = nfw ArrbyList<>();
        }
        l.bdd(flt);
        rfturn l;
    }


    // Rfmovf bn flfmfnt from b list, disdbrding tif list if tif
    // rfsulting list is fmpty, bnd rfturn tif list or null.
    stbtid <T> List<T> rfmovfFromList(List<T> l, T flt) {
        if (l == null) {
            rfturn l;
        }
        l.rfmovf(flt);
        if (l.sizf() == 0) {
            l = null;
        }
        rfturn l;
    }

    /**
     * Adds bn <dodf>IIORfbdWbrningListfnfr</dodf> to tif list of
     * rfgistfrfd wbrning listfnfrs.  If <dodf>listfnfr</dodf> is
     * <dodf>null</dodf>, no fxdfption will bf tirown bnd no bdtion
     * will bf tbkfn.  Mfssbgfs sfnt to tif givfn listfnfr will bf
     * lodblizfd, if possiblf, to mbtdi tif durrfnt
     * <dodf>Lodblf</dodf>.  If no <dodf>Lodblf</dodf> ibs bffn sft,
     * wbrning mfssbgfs mby bf lodblizfd bs tif rfbdfr sffs fit.
     *
     * @pbrbm listfnfr bn <dodf>IIORfbdWbrningListfnfr</dodf> to bf rfgistfrfd.
     *
     * @sff #rfmovfIIORfbdWbrningListfnfr
     */
    publid void bddIIORfbdWbrningListfnfr(IIORfbdWbrningListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        wbrningListfnfrs = bddToList(wbrningListfnfrs, listfnfr);
        wbrningLodblfs = bddToList(wbrningLodblfs, gftLodblf());
    }

    /**
     * Rfmovfs bn <dodf>IIORfbdWbrningListfnfr</dodf> from tif list of
     * rfgistfrfd frror listfnfrs.  If tif listfnfr wbs not prfviously
     * rfgistfrfd, or if <dodf>listfnfr</dodf> is <dodf>null</dodf>,
     * no fxdfption will bf tirown bnd no bdtion will bf tbkfn.
     *
     * @pbrbm listfnfr bn IIORfbdWbrningListfnfr to bf unrfgistfrfd.
     *
     * @sff #bddIIORfbdWbrningListfnfr
     */
    publid void rfmovfIIORfbdWbrningListfnfr(IIORfbdWbrningListfnfr listfnfr) {
        if (listfnfr == null || wbrningListfnfrs == null) {
            rfturn;
        }
        int indfx = wbrningListfnfrs.indfxOf(listfnfr);
        if (indfx != -1) {
            wbrningListfnfrs.rfmovf(indfx);
            wbrningLodblfs.rfmovf(indfx);
            if (wbrningListfnfrs.sizf() == 0) {
                wbrningListfnfrs = null;
                wbrningLodblfs = null;
            }
        }
    }

    /**
     * Rfmovfs bll durrfntly rfgistfrfd
     * <dodf>IIORfbdWbrningListfnfr</dodf> objfdts.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif
     * <dodf>wbrningListfnfrs</dodf> bnd <dodf>wbrningLodblfs</dodf>
     * instbndf vbribblfs to <dodf>null</dodf>.
     */
    publid void rfmovfAllIIORfbdWbrningListfnfrs() {
        wbrningListfnfrs = null;
        wbrningLodblfs = null;
    }

    /**
     * Adds bn <dodf>IIORfbdProgrfssListfnfr</dodf> to tif list of
     * rfgistfrfd progrfss listfnfrs.  If <dodf>listfnfr</dodf> is
     * <dodf>null</dodf>, no fxdfption will bf tirown bnd no bdtion
     * will bf tbkfn.
     *
     * @pbrbm listfnfr bn IIORfbdProgrfssListfnfr to bf rfgistfrfd.
     *
     * @sff #rfmovfIIORfbdProgrfssListfnfr
     */
    publid void bddIIORfbdProgrfssListfnfr(IIORfbdProgrfssListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        progrfssListfnfrs = bddToList(progrfssListfnfrs, listfnfr);
    }

    /**
     * Rfmovfs bn <dodf>IIORfbdProgrfssListfnfr</dodf> from tif list
     * of rfgistfrfd progrfss listfnfrs.  If tif listfnfr wbs not
     * prfviously rfgistfrfd, or if <dodf>listfnfr</dodf> is
     * <dodf>null</dodf>, no fxdfption will bf tirown bnd no bdtion
     * will bf tbkfn.
     *
     * @pbrbm listfnfr bn IIORfbdProgrfssListfnfr to bf unrfgistfrfd.
     *
     * @sff #bddIIORfbdProgrfssListfnfr
     */
    publid void
        rfmovfIIORfbdProgrfssListfnfr (IIORfbdProgrfssListfnfr listfnfr) {
        if (listfnfr == null || progrfssListfnfrs == null) {
            rfturn;
        }
        progrfssListfnfrs = rfmovfFromList(progrfssListfnfrs, listfnfr);
    }

    /**
     * Rfmovfs bll durrfntly rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf> objfdts.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif
     * <dodf>progrfssListfnfrs</dodf> instbndf vbribblf to
     * <dodf>null</dodf>.
     */
    publid void rfmovfAllIIORfbdProgrfssListfnfrs() {
        progrfssListfnfrs = null;
    }

    /**
     * Adds bn <dodf>IIORfbdUpdbtfListfnfr</dodf> to tif list of
     * rfgistfrfd updbtf listfnfrs.  If <dodf>listfnfr</dodf> is
     * <dodf>null</dodf>, no fxdfption will bf tirown bnd no bdtion
     * will bf tbkfn.  Tif listfnfr will rfdfivf notifidbtion of pixfl
     * updbtfs bs imbgfs bnd tiumbnbils brf dfdodfd, indluding tif
     * stbrts bnd fnds of progrfssivf pbssfs.
     *
     * <p> If no updbtf listfnfrs brf prfsfnt, tif rfbdfr mby dioosf
     * to pfrform ffwfr updbtfs to tif pixfls of tif dfstinbtion
     * imbgfs bnd/or tiumbnbils, wiidi mby rfsult in morf fffidifnt
     * dfdoding.
     *
     * <p> For fxbmplf, in progrfssivf JPEG dfdoding fbdi pbss
     * dontbins updbtfs to b sft of dofffidifnts, wiidi would ibvf to
     * bf trbnsformfd into pixfl vblufs bnd donvfrtfd to bn RGB dolor
     * spbdf for fbdi pbss if listfnfrs brf prfsfnt.  If no listfnfrs
     * brf prfsfnt, tif dofffidifnts mby simply bf bddumulbtfd bnd tif
     * finbl rfsults trbnsformfd bnd dolor donvfrtfd onf timf only.
     *
     * <p> Tif finbl rfsults of dfdoding will bf tif sbmf wiftifr or
     * not intfrmfdibtf updbtfs brf pfrformfd.  Tius if only tif finbl
     * imbgf is dfsirfd it mby bf prfffrbblf not to rfgistfr bny
     * <dodf>IIORfbdUpdbtfListfnfr</dodf>s.  In gfnfrbl, progrfssivf
     * updbting is most ffffdtivf wifn fftdiing imbgfs ovfr b nftwork
     * donnfdtion tibt is vfry slow dompbrfd to lodbl CPU prodfssing;
     * ovfr b fbst donnfdtion, progrfssivf updbtfs mby bdtublly slow
     * down tif prfsfntbtion of tif imbgf.
     *
     * @pbrbm listfnfr bn IIORfbdUpdbtfListfnfr to bf rfgistfrfd.
     *
     * @sff #rfmovfIIORfbdUpdbtfListfnfr
     */
    publid void
        bddIIORfbdUpdbtfListfnfr(IIORfbdUpdbtfListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        updbtfListfnfrs = bddToList(updbtfListfnfrs, listfnfr);
    }

    /**
     * Rfmovfs bn <dodf>IIORfbdUpdbtfListfnfr</dodf> from tif list of
     * rfgistfrfd updbtf listfnfrs.  If tif listfnfr wbs not
     * prfviously rfgistfrfd, or if <dodf>listfnfr</dodf> is
     * <dodf>null</dodf>, no fxdfption will bf tirown bnd no bdtion
     * will bf tbkfn.
     *
     * @pbrbm listfnfr bn IIORfbdUpdbtfListfnfr to bf unrfgistfrfd.
     *
     * @sff #bddIIORfbdUpdbtfListfnfr
     */
    publid void rfmovfIIORfbdUpdbtfListfnfr(IIORfbdUpdbtfListfnfr listfnfr) {
        if (listfnfr == null || updbtfListfnfrs == null) {
            rfturn;
        }
        updbtfListfnfrs = rfmovfFromList(updbtfListfnfrs, listfnfr);
    }

    /**
     * Rfmovfs bll durrfntly rfgistfrfd
     * <dodf>IIORfbdUpdbtfListfnfr</dodf> objfdts.
     *
     * <p> Tif dffbult implfmfntbtion sfts tif
     * <dodf>updbtfListfnfrs</dodf> instbndf vbribblf to
     * <dodf>null</dodf>.
     */
    publid void rfmovfAllIIORfbdUpdbtfListfnfrs() {
        updbtfListfnfrs = null;
    }

    /**
     * Brobddbsts tif stbrt of bn sfqufndf of imbgf rfbds to bll
     * rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling
     * tifir <dodf>sfqufndfStbrtfd</dodf> mftiod.  Subdlbssfs mby usf
     * tiis mftiod bs b donvfnifndf.
     *
     * @pbrbm minIndfx tif lowfst indfx bfing rfbd.
     */
    protfdtfd void prodfssSfqufndfStbrtfd(int minIndfx) {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.sfqufndfStbrtfd(tiis, minIndfx);
        }
    }

    /**
     * Brobddbsts tif domplftion of bn sfqufndf of imbgf rfbds to bll
     * rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling
     * tifir <dodf>sfqufndfComplftf</dodf> mftiod.  Subdlbssfs mby usf
     * tiis mftiod bs b donvfnifndf.
     */
    protfdtfd void prodfssSfqufndfComplftf() {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.sfqufndfComplftf(tiis);
        }
    }

    /**
     * Brobddbsts tif stbrt of bn imbgf rfbd to bll rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling tifir
     * <dodf>imbgfStbrtfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bbout to bf rfbd.
     */
    protfdtfd void prodfssImbgfStbrtfd(int imbgfIndfx) {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.imbgfStbrtfd(tiis, imbgfIndfx);
        }
    }

    /**
     * Brobddbsts tif durrfnt pfrdfntbgf of imbgf domplftion to bll
     * rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling
     * tifir <dodf>imbgfProgrfss</dodf> mftiod.  Subdlbssfs mby usf
     * tiis mftiod bs b donvfnifndf.
     *
     * @pbrbm pfrdfntbgfDonf tif durrfnt pfrdfntbgf of domplftion,
     * bs b <dodf>flobt</dodf>.
     */
    protfdtfd void prodfssImbgfProgrfss(flobt pfrdfntbgfDonf) {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.imbgfProgrfss(tiis, pfrdfntbgfDonf);
        }
    }

    /**
     * Brobddbsts tif domplftion of bn imbgf rfbd to bll rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling tifir
     * <dodf>imbgfComplftf</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     */
    protfdtfd void prodfssImbgfComplftf() {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.imbgfComplftf(tiis);
        }
    }

    /**
     * Brobddbsts tif stbrt of b tiumbnbil rfbd to bll rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling tifir
     * <dodf>tiumbnbilStbrtfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm imbgfIndfx tif indfx of tif imbgf bssodibtfd witi tif
     * tiumbnbil.
     * @pbrbm tiumbnbilIndfx tif indfx of tif tiumbnbil.
     */
    protfdtfd void prodfssTiumbnbilStbrtfd(int imbgfIndfx,
                                           int tiumbnbilIndfx) {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.tiumbnbilStbrtfd(tiis, imbgfIndfx, tiumbnbilIndfx);
        }
    }

    /**
     * Brobddbsts tif durrfnt pfrdfntbgf of tiumbnbil domplftion to
     * bll rfgistfrfd <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling
     * tifir <dodf>tiumbnbilProgrfss</dodf> mftiod.  Subdlbssfs mby
     * usf tiis mftiod bs b donvfnifndf.
     *
     * @pbrbm pfrdfntbgfDonf tif durrfnt pfrdfntbgf of domplftion,
     * bs b <dodf>flobt</dodf>.
     */
    protfdtfd void prodfssTiumbnbilProgrfss(flobt pfrdfntbgfDonf) {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.tiumbnbilProgrfss(tiis, pfrdfntbgfDonf);
        }
    }

    /**
     * Brobddbsts tif domplftion of b tiumbnbil rfbd to bll rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling tifir
     * <dodf>tiumbnbilComplftf</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     */
    protfdtfd void prodfssTiumbnbilComplftf() {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.tiumbnbilComplftf(tiis);
        }
    }

    /**
     * Brobddbsts tibt tif rfbd ibs bffn bbortfd to bll rfgistfrfd
     * <dodf>IIORfbdProgrfssListfnfr</dodf>s by dblling tifir
     * <dodf>rfbdAbortfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     */
    protfdtfd void prodfssRfbdAbortfd() {
        if (progrfssListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = progrfssListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdProgrfssListfnfr listfnfr =
                progrfssListfnfrs.gft(i);
            listfnfr.rfbdAbortfd(tiis);
        }
    }

    /**
     * Brobddbsts tif bfginning of b progrfssivf pbss to bll
     * rfgistfrfd <dodf>IIORfbdUpdbtfListfnfr</dodf>s by dblling tifir
     * <dodf>pbssStbrtfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     * @pbrbm pbss tif indfx of tif durrfnt pbss, stbrting witi 0.
     * @pbrbm minPbss tif indfx of tif first pbss tibt will bf dfdodfd.
     * @pbrbm mbxPbss tif indfx of tif lbst pbss tibt will bf dfdodfd.
     * @pbrbm minX tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm minY tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm pfriodX tif iorizontbl sfpbrbtion bftwffn pixfls.
     * @pbrbm pfriodY tif vfrtidbl sfpbrbtion bftwffn pixfls.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif
     * sft of bfffdtfd bbnds of tif dfstinbtion.
     */
    protfdtfd void prodfssPbssStbrtfd(BufffrfdImbgf tifImbgf,
                                      int pbss,
                                      int minPbss, int mbxPbss,
                                      int minX, int minY,
                                      int pfriodX, int pfriodY,
                                      int[] bbnds) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.pbssStbrtfd(tiis, tifImbgf, pbss,
                                 minPbss,
                                 mbxPbss,
                                 minX, minY,
                                 pfriodX, pfriodY,
                                 bbnds);
        }
    }

    /**
     * Brobddbsts tif updbtf of b sft of sbmplfs to bll rfgistfrfd
     * <dodf>IIORfbdUpdbtfListfnfr</dodf>s by dblling tifir
     * <dodf>imbgfUpdbtf</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     * @pbrbm minX tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm minY tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm widti tif totbl widti of tif brfb bfing updbtfd, indluding
     * pixfls bfing skippfd if <dodf>pfriodX &gt; 1</dodf>.
     * @pbrbm ifigit tif totbl ifigit of tif brfb bfing updbtfd,
     * indluding pixfls bfing skippfd if <dodf>pfriodY &gt; 1</dodf>.
     * @pbrbm pfriodX tif iorizontbl sfpbrbtion bftwffn pixfls.
     * @pbrbm pfriodY tif vfrtidbl sfpbrbtion bftwffn pixfls.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif
     * sft of bfffdtfd bbnds of tif dfstinbtion.
     */
    protfdtfd void prodfssImbgfUpdbtf(BufffrfdImbgf tifImbgf,
                                      int minX, int minY,
                                      int widti, int ifigit,
                                      int pfriodX, int pfriodY,
                                      int[] bbnds) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.imbgfUpdbtf(tiis,
                                 tifImbgf,
                                 minX, minY,
                                 widti, ifigit,
                                 pfriodX, pfriodY,
                                 bbnds);
        }
    }

    /**
     * Brobddbsts tif fnd of b progrfssivf pbss to bll
     * rfgistfrfd <dodf>IIORfbdUpdbtfListfnfr</dodf>s by dblling tifir
     * <dodf>pbssComplftf</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm tifImbgf tif <dodf>BufffrfdImbgf</dodf> bfing updbtfd.
     */
    protfdtfd void prodfssPbssComplftf(BufffrfdImbgf tifImbgf) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.pbssComplftf(tiis, tifImbgf);
        }
    }

    /**
     * Brobddbsts tif bfginning of b tiumbnbil progrfssivf pbss to bll
     * rfgistfrfd <dodf>IIORfbdUpdbtfListfnfr</dodf>s by dblling tifir
     * <dodf>tiumbnbilPbssStbrtfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     * @pbrbm pbss tif indfx of tif durrfnt pbss, stbrting witi 0.
     * @pbrbm minPbss tif indfx of tif first pbss tibt will bf dfdodfd.
     * @pbrbm mbxPbss tif indfx of tif lbst pbss tibt will bf dfdodfd.
     * @pbrbm minX tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm minY tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm pfriodX tif iorizontbl sfpbrbtion bftwffn pixfls.
     * @pbrbm pfriodY tif vfrtidbl sfpbrbtion bftwffn pixfls.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif
     * sft of bfffdtfd bbnds of tif dfstinbtion.
     */
    protfdtfd void prodfssTiumbnbilPbssStbrtfd(BufffrfdImbgf tifTiumbnbil,
                                               int pbss,
                                               int minPbss, int mbxPbss,
                                               int minX, int minY,
                                               int pfriodX, int pfriodY,
                                               int[] bbnds) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.tiumbnbilPbssStbrtfd(tiis, tifTiumbnbil, pbss,
                                          minPbss,
                                          mbxPbss,
                                          minX, minY,
                                          pfriodX, pfriodY,
                                          bbnds);
        }
    }

    /**
     * Brobddbsts tif updbtf of b sft of sbmplfs in b tiumbnbil imbgf
     * to bll rfgistfrfd <dodf>IIORfbdUpdbtfListfnfr</dodf>s by
     * dblling tifir <dodf>tiumbnbilUpdbtf</dodf> mftiod.  Subdlbssfs mby
     * usf tiis mftiod bs b donvfnifndf.
     *
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     * @pbrbm minX tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm minY tif X doordinbtf of tif uppfr-lfft pixfl indludfd
     * in tif pbss.
     * @pbrbm widti tif totbl widti of tif brfb bfing updbtfd, indluding
     * pixfls bfing skippfd if <dodf>pfriodX &gt; 1</dodf>.
     * @pbrbm ifigit tif totbl ifigit of tif brfb bfing updbtfd,
     * indluding pixfls bfing skippfd if <dodf>pfriodY &gt; 1</dodf>.
     * @pbrbm pfriodX tif iorizontbl sfpbrbtion bftwffn pixfls.
     * @pbrbm pfriodY tif vfrtidbl sfpbrbtion bftwffn pixfls.
     * @pbrbm bbnds bn brrby of <dodf>int</dodf>s indidbting tif
     * sft of bfffdtfd bbnds of tif dfstinbtion.
     */
    protfdtfd void prodfssTiumbnbilUpdbtf(BufffrfdImbgf tifTiumbnbil,
                                          int minX, int minY,
                                          int widti, int ifigit,
                                          int pfriodX, int pfriodY,
                                          int[] bbnds) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.tiumbnbilUpdbtf(tiis,
                                     tifTiumbnbil,
                                     minX, minY,
                                     widti, ifigit,
                                     pfriodX, pfriodY,
                                     bbnds);
        }
    }

    /**
     * Brobddbsts tif fnd of b tiumbnbil progrfssivf pbss to bll
     * rfgistfrfd <dodf>IIORfbdUpdbtfListfnfr</dodf>s by dblling tifir
     * <dodf>tiumbnbilPbssComplftf</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm tifTiumbnbil tif <dodf>BufffrfdImbgf</dodf> tiumbnbil
     * bfing updbtfd.
     */
    protfdtfd void prodfssTiumbnbilPbssComplftf(BufffrfdImbgf tifTiumbnbil) {
        if (updbtfListfnfrs == null) {
            rfturn;
        }
        int numListfnfrs = updbtfListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdUpdbtfListfnfr listfnfr =
                updbtfListfnfrs.gft(i);
            listfnfr.tiumbnbilPbssComplftf(tiis, tifTiumbnbil);
        }
    }

    /**
     * Brobddbsts b wbrning mfssbgf to bll rfgistfrfd
     * <dodf>IIORfbdWbrningListfnfr</dodf>s by dblling tifir
     * <dodf>wbrningOddurrfd</dodf> mftiod.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm wbrning tif wbrning mfssbgf to sfnd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>wbrning</dodf>
     * is <dodf>null</dodf>.
     */
    protfdtfd void prodfssWbrningOddurrfd(String wbrning) {
        if (wbrningListfnfrs == null) {
            rfturn;
        }
        if (wbrning == null) {
            tirow nfw IllfgblArgumfntExdfption("wbrning == null!");
        }
        int numListfnfrs = wbrningListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdWbrningListfnfr listfnfr =
                wbrningListfnfrs.gft(i);

            listfnfr.wbrningOddurrfd(tiis, wbrning);
        }
    }

    /**
     * Brobddbsts b lodblizfd wbrning mfssbgf to bll rfgistfrfd
     * <dodf>IIORfbdWbrningListfnfr</dodf>s by dblling tifir
     * <dodf>wbrningOddurrfd</dodf> mftiod witi b string tbkfn
     * from b <dodf>RfsourdfBundlf</dodf>.  Subdlbssfs mby usf tiis
     * mftiod bs b donvfnifndf.
     *
     * @pbrbm bbsfNbmf tif bbsf nbmf of b sft of
     * <dodf>RfsourdfBundlf</dodf>s dontbining lodblizfd wbrning
     * mfssbgfs.
     * @pbrbm kfyword tif kfyword usfd to indfx tif wbrning mfssbgf
     * witiin tif sft of <dodf>RfsourdfBundlf</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bbsfNbmf</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>kfyword</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if no bppropribtf
     * <dodf>RfsourdfBundlf</dodf> mby bf lodbtfd.
     * @fxdfption IllfgblArgumfntExdfption if tif nbmfd rfsourdf is
     * not found in tif lodbtfd <dodf>RfsourdfBundlf</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if tif objfdt rftrifvfd
     * from tif <dodf>RfsourdfBundlf</dodf> is not b
     * <dodf>String</dodf>.
     */
    protfdtfd void prodfssWbrningOddurrfd(String bbsfNbmf,
                                          String kfyword) {
        if (wbrningListfnfrs == null) {
            rfturn;
        }
        if (bbsfNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("bbsfNbmf == null!");
        }
        if (kfyword == null) {
            tirow nfw IllfgblArgumfntExdfption("kfyword == null!");
        }
        int numListfnfrs = wbrningListfnfrs.sizf();
        for (int i = 0; i < numListfnfrs; i++) {
            IIORfbdWbrningListfnfr listfnfr =
                wbrningListfnfrs.gft(i);
            Lodblf lodblf = wbrningLodblfs.gft(i);
            if (lodblf == null) {
                lodblf = Lodblf.gftDffbult();
            }

            /**
             * If bn bpplft supplifs bn implfmfntbtion of ImbgfRfbdfr bnd
             * rfsourdf bundlfs, tifn tif rfsourdf bundlf will nffd to bf
             * bddfssfd vib tif bpplft dlbss lobdfr. So first try tif dontfxt
             * dlbss lobdfr to lodbtf tif rfsourdf bundlf.
             * If tibt tirows MissingRfsourdfExdfption, tifn try tif
             * systfm dlbss lobdfr.
             */
            ClbssLobdfr lobdfr =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                   nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
                      publid ClbssLobdfr run() {
                        rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                      }
                });

            RfsourdfBundlf bundlf = null;
            try {
                bundlf = RfsourdfBundlf.gftBundlf(bbsfNbmf, lodblf, lobdfr);
            } dbtdi (MissingRfsourdfExdfption mrf) {
                try {
                    bundlf = RfsourdfBundlf.gftBundlf(bbsfNbmf, lodblf);
                } dbtdi (MissingRfsourdfExdfption mrf1) {
                    tirow nfw IllfgblArgumfntExdfption("Bundlf not found!");
                }
            }

            String wbrning = null;
            try {
                wbrning = bundlf.gftString(kfyword);
            } dbtdi (ClbssCbstExdfption ddf) {
                tirow nfw IllfgblArgumfntExdfption("Rfsourdf is not b String!");
            } dbtdi (MissingRfsourdfExdfption mrf) {
                tirow nfw IllfgblArgumfntExdfption("Rfsourdf is missing!");
            }

            listfnfr.wbrningOddurrfd(tiis, wbrning);
        }
    }

    // Stbtf mbnbgfmfnt

    /**
     * Rfstorfs tif <dodf>ImbgfRfbdfr</dodf> to its initibl stbtf.
     *
     * <p> Tif dffbult implfmfntbtion dblls <dodf>sftInput(null,
     * fblsf)</dodf>, <dodf>sftLodblf(null)</dodf>,
     * <dodf>rfmovfAllIIORfbdUpdbtfListfnfrs()</dodf>,
     * <dodf>rfmovfAllIIORfbdWbrningListfnfrs()</dodf>,
     * <dodf>rfmovfAllIIORfbdProgrfssListfnfrs()</dodf>, bnd
     * <dodf>dlfbrAbortRfqufst</dodf>.
     */
    publid void rfsft() {
        sftInput(null, fblsf, fblsf);
        sftLodblf(null);
        rfmovfAllIIORfbdUpdbtfListfnfrs();
        rfmovfAllIIORfbdProgrfssListfnfrs();
        rfmovfAllIIORfbdWbrningListfnfrs();
        dlfbrAbortRfqufst();
    }

    /**
     * Allows bny rfsourdfs ifld by tiis objfdt to bf rflfbsfd.  Tif
     * rfsult of dblling bny otifr mftiod (otifr tibn
     * <dodf>finblizf</dodf>) subsfqufnt to b dbll to tiis mftiod
     * is undffinfd.
     *
     * <p>It is importbnt for bpplidbtions to dbll tiis mftiod wifn tify
     * know tify will no longfr bf using tiis <dodf>ImbgfRfbdfr</dodf>.
     * Otifrwisf, tif rfbdfr mby dontinuf to iold on to rfsourdfs
     * indffinitfly.
     *
     * <p>Tif dffbult implfmfntbtion of tiis mftiod in tif supfrdlbss dofs
     * notiing.  Subdlbss implfmfntbtions siould fnsurf tibt bll rfsourdfs,
     * fspfdiblly nbtivf rfsourdfs, brf rflfbsfd.
     */
    publid void disposf() {
    }

    // Utility mftiods

    /**
     * A utility mftiod tibt mby bf usfd by rfbdfrs to domputf tif
     * rfgion of tif sourdf imbgf tibt siould bf rfbd, tbking into
     * bddount bny sourdf rfgion bnd subsbmpling offsft sfttings in
     * tif supplifd <dodf>ImbgfRfbdPbrbm</dodf>.  Tif bdtubl
     * subsbmpling fbdtors, dfstinbtion sizf, bnd dfstinbtion offsft
     * brf <fm>not</fm> tbkfn into donsidfrbtion, tius furtifr
     * dlipping must tbkf plbdf.  Tif {@link #domputfRfgions domputfRfgions}
     * mftiod pfrforms bll nfdfssbry dlipping.
     *
     * @pbrbm pbrbm tif <dodf>ImbgfRfbdPbrbm</dodf> bfing usfd, or
     * <dodf>null</dodf>.
     * @pbrbm srdWidti tif widti of tif sourdf imbgf.
     * @pbrbm srdHfigit tif ifigit of tif sourdf imbgf.
     *
     * @rfturn tif sourdf rfgion bs b <dodf>Rfdtbnglf</dodf>.
     */
    protfdtfd stbtid Rfdtbnglf gftSourdfRfgion(ImbgfRfbdPbrbm pbrbm,
                                               int srdWidti,
                                               int srdHfigit) {
        Rfdtbnglf sourdfRfgion = nfw Rfdtbnglf(0, 0, srdWidti, srdHfigit);
        if (pbrbm != null) {
            Rfdtbnglf rfgion = pbrbm.gftSourdfRfgion();
            if (rfgion != null) {
                sourdfRfgion = sourdfRfgion.intfrsfdtion(rfgion);
            }

            int subsbmplfXOffsft = pbrbm.gftSubsbmplingXOffsft();
            int subsbmplfYOffsft = pbrbm.gftSubsbmplingYOffsft();
            sourdfRfgion.x += subsbmplfXOffsft;
            sourdfRfgion.y += subsbmplfYOffsft;
            sourdfRfgion.widti -= subsbmplfXOffsft;
            sourdfRfgion.ifigit -= subsbmplfYOffsft;
        }

        rfturn sourdfRfgion;
    }

    /**
     * Computfs tif sourdf rfgion of intfrfst bnd tif dfstinbtion
     * rfgion of intfrfst, tbking tif widti bnd ifigit of tif sourdf
     * imbgf, bn optionbl dfstinbtion imbgf, bnd bn optionbl
     * <dodf>ImbgfRfbdPbrbm</dodf> into bddount.  Tif sourdf rfgion
     * bfgins witi tif fntirf sourdf imbgf.  Tifn tibt is dlippfd to
     * tif sourdf rfgion spfdififd in tif <dodf>ImbgfRfbdPbrbm</dodf>,
     * if onf is spfdififd.
     *
     * <p> If fitifr of tif dfstinbtion offsfts brf nfgbtivf, tif
     * sourdf rfgion is dlippfd so tibt its top lfft will doindidf
     * witi tif top lfft of tif dfstinbtion imbgf, tbking subsbmpling
     * into bddount.  Tifn tif rfsult is dlippfd to tif dfstinbtion
     * imbgf on tif rigit bnd bottom, if onf is spfdififd, tbking
     * subsbmpling bnd dfstinbtion offsfts into bddount.
     *
     * <p> Similbrly, tif dfstinbtion rfgion bfgins witi tif sourdf
     * imbgf, is trbnslbtfd to tif dfstinbtion offsft givfn in tif
     * <dodf>ImbgfRfbdPbrbm</dodf> if tifrf is onf, bnd finblly is
     * dlippfd to tif dfstinbtion imbgf, if tifrf is onf.
     *
     * <p> If fitifr tif sourdf or dfstinbtion rfgions fnd up ibving b
     * widti or ifigit of 0, bn <dodf>IllfgblArgumfntExdfption</dodf>
     * is tirown.
     *
     * <p> Tif {@link #gftSourdfRfgion gftSourdfRfgion>}
     * mftiod mby bf usfd if only sourdf dlipping is dfsirfd.
     *
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf>, or <dodf>null</dodf>.
     * @pbrbm srdWidti tif widti of tif sourdf imbgf.
     * @pbrbm srdHfigit tif ifigit of tif sourdf imbgf.
     * @pbrbm imbgf b <dodf>BufffrfdImbgf</dodf> tibt will bf tif
     * dfstinbtion imbgf, or <dodf>null</dodf>.
     * @pbrbm srdRfgion b <dodf>Rfdtbnglf</dodf> tibt will bf fillfd witi
     * tif sourdf rfgion of intfrfst.
     * @pbrbm dfstRfgion b <dodf>Rfdtbnglf</dodf> tibt will bf fillfd witi
     * tif dfstinbtion rfgion of intfrfst.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>srdRfgion</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dstRfgion</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if tif rfsulting sourdf or
     * dfstinbtion rfgion is fmpty.
     */
    protfdtfd stbtid void domputfRfgions(ImbgfRfbdPbrbm pbrbm,
                                         int srdWidti,
                                         int srdHfigit,
                                         BufffrfdImbgf imbgf,
                                         Rfdtbnglf srdRfgion,
                                         Rfdtbnglf dfstRfgion) {
        if (srdRfgion == null) {
            tirow nfw IllfgblArgumfntExdfption("srdRfgion == null!");
        }
        if (dfstRfgion == null) {
            tirow nfw IllfgblArgumfntExdfption("dfstRfgion == null!");
        }

        // Stbrt witi tif fntirf sourdf imbgf
        srdRfgion.sftBounds(0, 0, srdWidti, srdHfigit);

        // Dfstinbtion blso stbrts witi sourdf imbgf, bs tibt is tif
        // mbximum fxtfnt if tifrf is no subsbmpling
        dfstRfgion.sftBounds(0, 0, srdWidti, srdHfigit);

        // Clip tibt to tif pbrbm rfgion, if tifrf is onf
        int pfriodX = 1;
        int pfriodY = 1;
        int gridX = 0;
        int gridY = 0;
        if (pbrbm != null) {
            Rfdtbnglf pbrbmSrdRfgion = pbrbm.gftSourdfRfgion();
            if (pbrbmSrdRfgion != null) {
                srdRfgion.sftBounds(srdRfgion.intfrsfdtion(pbrbmSrdRfgion));
            }
            pfriodX = pbrbm.gftSourdfXSubsbmpling();
            pfriodY = pbrbm.gftSourdfYSubsbmpling();
            gridX = pbrbm.gftSubsbmplingXOffsft();
            gridY = pbrbm.gftSubsbmplingYOffsft();
            srdRfgion.trbnslbtf(gridX, gridY);
            srdRfgion.widti -= gridX;
            srdRfgion.ifigit -= gridY;
            dfstRfgion.sftLodbtion(pbrbm.gftDfstinbtionOffsft());
        }

        // Now dlip bny nfgbtivf dfstinbtion offsfts, i.f. dlip
        // to tif top bnd lfft of tif dfstinbtion imbgf
        if (dfstRfgion.x < 0) {
            int dfltb = -dfstRfgion.x*pfriodX;
            srdRfgion.x += dfltb;
            srdRfgion.widti -= dfltb;
            dfstRfgion.x = 0;
        }
        if (dfstRfgion.y < 0) {
            int dfltb = -dfstRfgion.y*pfriodY;
            srdRfgion.y += dfltb;
            srdRfgion.ifigit -= dfltb;
            dfstRfgion.y = 0;
        }

        // Now dlip tif dfstinbtion Rfgion to tif subsbmplfd widti bnd ifigit
        int subsbmplfdWidti = (srdRfgion.widti + pfriodX - 1)/pfriodX;
        int subsbmplfdHfigit = (srdRfgion.ifigit + pfriodY - 1)/pfriodY;
        dfstRfgion.widti = subsbmplfdWidti;
        dfstRfgion.ifigit = subsbmplfdHfigit;

        // Now dlip tibt to rigit bnd bottom of tif dfstinbtion imbgf,
        // if tifrf is onf, tbking subsbmpling into bddount
        if (imbgf != null) {
            Rfdtbnglf dfstImbgfRfdt = nfw Rfdtbnglf(0, 0,
                                                    imbgf.gftWidti(),
                                                    imbgf.gftHfigit());
            dfstRfgion.sftBounds(dfstRfgion.intfrsfdtion(dfstImbgfRfdt));
            if (dfstRfgion.isEmpty()) {
                tirow nfw IllfgblArgumfntExdfption
                    ("Empty dfstinbtion rfgion!");
            }

            int dfltbX = dfstRfgion.x + subsbmplfdWidti - imbgf.gftWidti();
            if (dfltbX > 0) {
                srdRfgion.widti -= dfltbX*pfriodX;
            }
            int dfltbY =  dfstRfgion.y + subsbmplfdHfigit - imbgf.gftHfigit();
            if (dfltbY > 0) {
                srdRfgion.ifigit -= dfltbY*pfriodY;
            }
        }
        if (srdRfgion.isEmpty() || dfstRfgion.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption("Empty rfgion!");
        }
    }

    /**
     * A utility mftiod tibt mby bf usfd by rfbdfrs to tfst tif
     * vblidity of tif sourdf bnd dfstinbtion bbnd sfttings of bn
     * <dodf>ImbgfRfbdPbrbm</dodf>.  Tiis mftiod mby bf dbllfd bs soon
     * bs tif rfbdfr knows boti tif numbfr of bbnds of tif sourdf
     * imbgf bs it fxists in tif input strfbm, bnd tif numbfr of bbnds
     * of tif dfstinbtion imbgf tibt bfing writtfn.
     *
     * <p> Tif mftiod rftrifvfs tif sourdf bnd dfstinbtion bbnd
     * sftting brrbys from pbrbm using tif <dodf>gftSourdfBbnds</dodf>
     * bnd <dodf>gftDfstinbtionBbnds</dodf>mftiods (or donsidfrs tifm
     * to bf <dodf>null</dodf> if <dodf>pbrbm</dodf> is
     * <dodf>null</dodf>).  If tif sourdf bbnd sftting brrby is
     * <dodf>null</dodf>, it is donsidfrfd to bf fqubl to tif brrby
     * <dodf>{ 0, 1, ..., numSrdBbnds - 1 }</dodf>, bnd similbrly for
     * tif dfstinbtion bbnd sftting brrby.
     *
     * <p> Tif mftiod tifn tfsts tibt boti brrbys brf fqubl in lfngti,
     * bnd tibt nfitifr brrby dontbins b vbluf lbrgfr tibn tif lbrgfst
     * bvbilbblf bbnd indfx.
     *
     * <p> Any fbilurf rfsults in bn
     * <dodf>IllfgblArgumfntExdfption</dodf> bfing tirown; suddfss
     * rfsults in tif mftiod rfturning silfntly.
     *
     * @pbrbm pbrbm tif <dodf>ImbgfRfbdPbrbm</dodf> bfing usfd to rfbd
     * tif imbgf.
     * @pbrbm numSrdBbnds tif numbfr of bbnds of tif imbgf bs it fxists
     * int tif input sourdf.
     * @pbrbm numDstBbnds tif numbfr of bbnds in tif dfstinbtion imbgf
     * bfing writtfn.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>pbrbm</dodf>
     * dontbins bn invblid spfdifidbtion of b sourdf bnd/or
     * dfstinbtion bbnd subsft.
     */
    protfdtfd stbtid void difdkRfbdPbrbmBbndSfttings(ImbgfRfbdPbrbm pbrbm,
                                                     int numSrdBbnds,
                                                     int numDstBbnds) {
        // A null pbrbm is fquivblfnt to srdBbnds == dstBbnds == null.
        int[] srdBbnds = null;
        int[] dstBbnds = null;
        if (pbrbm != null) {
            srdBbnds = pbrbm.gftSourdfBbnds();
            dstBbnds = pbrbm.gftDfstinbtionBbnds();
        }

        int pbrbmSrdBbndLfngti =
            (srdBbnds == null) ? numSrdBbnds : srdBbnds.lfngti;
        int pbrbmDstBbndLfngti =
            (dstBbnds == null) ? numDstBbnds : dstBbnds.lfngti;

        if (pbrbmSrdBbndLfngti != pbrbmDstBbndLfngti) {
            tirow nfw IllfgblArgumfntExdfption("ImbgfRfbdPbrbm num sourdf & dfst bbnds difffr!");
        }

        if (srdBbnds != null) {
            for (int i = 0; i < srdBbnds.lfngti; i++) {
                if (srdBbnds[i] >= numSrdBbnds) {
                    tirow nfw IllfgblArgumfntExdfption("ImbgfRfbdPbrbm sourdf bbnds dontbins b vbluf >= tif numbfr of sourdf bbnds!");
                }
            }
        }

        if (dstBbnds != null) {
            for (int i = 0; i < dstBbnds.lfngti; i++) {
                if (dstBbnds[i] >= numDstBbnds) {
                    tirow nfw IllfgblArgumfntExdfption("ImbgfRfbdPbrbm dfst bbnds dontbins b vbluf >= tif numbfr of dfst bbnds!");
                }
            }
        }
    }

    /**
     * Rfturns tif <dodf>BufffrfdImbgf</dodf> to wiidi dfdodfd pixfl
     * dbtb siould bf writtfn.  Tif imbgf is dftfrminfd by inspfdting
     * tif supplifd <dodf>ImbgfRfbdPbrbm</dodf> if it is
     * non-<dodf>null</dodf>; if its <dodf>gftDfstinbtion</dodf>
     * mftiod rfturns b non-<dodf>null</dodf> vbluf, tibt imbgf is
     * simply rfturnfd.  Otifrwisf,
     * <dodf>pbrbm.gftDfstinbtionTypf</dodf> mftiod is dbllfd to
     * dftfrminf if b pbrtidulbr imbgf typf ibs bffn spfdififd.  If
     * so, tif rfturnfd <dodf>ImbgfTypfSpfdififr</dodf> is usfd bftfr
     * difdking tibt it is fqubl to onf of tiosf indludfd in
     * <dodf>imbgfTypfs</dodf>.
     *
     * <p> If <dodf>pbrbm</dodf> is <dodf>null</dodf> or tif bbovf
     * stfps ibvf not yifldfd bn imbgf or bn
     * <dodf>ImbgfTypfSpfdififr</dodf>, tif first vbluf obtbinfd from
     * tif <dodf>imbgfTypfs</dodf> pbrbmftfr is usfd.  Typidblly, tif
     * dbllfr will sft <dodf>imbgfTypfs</dodf> to tif vbluf of
     * <dodf>gftImbgfTypfs(imbgfIndfx)</dodf>.
     *
     * <p> Nfxt, tif dimfnsions of tif imbgf brf dftfrminfd by b dbll
     * to <dodf>domputfRfgions</dodf>.  Tif bdtubl widti bnd ifigit of
     * tif imbgf bfing dfdodfd brf pbssfd in bs tif <dodf>widti</dodf>
     * bnd <dodf>ifigit</dodf> pbrbmftfrs.
     *
     * @pbrbm pbrbm bn <dodf>ImbgfRfbdPbrbm</dodf> to bf usfd to gft
     * tif dfstinbtion imbgf or imbgf typf, or <dodf>null</dodf>.
     * @pbrbm imbgfTypfs bn <dodf>Itfrbtor</dodf> of
     * <dodf>ImbgfTypfSpfdififr</dodf>s indidbting tif lfgbl imbgf
     * typfs, witi tif dffbult first.
     * @pbrbm widti tif truf widti of tif imbgf or tilf bfgin dfdodfd.
     * @pbrbm ifigit tif truf widti of tif imbgf or tilf bfing dfdodfd.
     *
     * @rfturn tif <dodf>BufffrfdImbgf</dodf> to wiidi dfdodfd pixfl
     * dbtb siould bf writtfn.
     *
     * @fxdfption IIOExdfption if tif <dodf>ImbgfTypfSpfdififr</dodf>
     * spfdififd by <dodf>pbrbm</dodf> dofs not mbtdi bny of tif lfgbl
     * onfs from <dodf>imbgfTypfs</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>imbgfTypfs</dodf>
     * is <dodf>null</dodf> or fmpty, or if bn objfdt not of typf
     * <dodf>ImbgfTypfSpfdififr</dodf> is rftrifvfd from it.
     * @fxdfption IllfgblArgumfntExdfption if tif rfsulting imbgf would
     * ibvf b widti or ifigit lfss tibn 1.
     * @fxdfption IllfgblArgumfntExdfption if tif produdt of
     * <dodf>widti</dodf> bnd <dodf>ifigit</dodf> is grfbtfr tibn
     * <dodf>Intfgfr.MAX_VALUE</dodf>.
     */
    protfdtfd stbtid BufffrfdImbgf
        gftDfstinbtion(ImbgfRfbdPbrbm pbrbm,
                       Itfrbtor<ImbgfTypfSpfdififr> imbgfTypfs,
                       int widti, int ifigit)
        tirows IIOExdfption {
        if (imbgfTypfs == null || !imbgfTypfs.ibsNfxt()) {
            tirow nfw IllfgblArgumfntExdfption("imbgfTypfs null or fmpty!");
        }
        if ((long)widti*ifigit > Intfgfr.MAX_VALUE) {
            tirow nfw IllfgblArgumfntExdfption
                ("widti*ifigit > Intfgfr.MAX_VALUE!");
        }

        BufffrfdImbgf dfst = null;
        ImbgfTypfSpfdififr imbgfTypf = null;

        // If pbrbm is non-null, usf it
        if (pbrbm != null) {
            // Try to gft tif imbgf itsflf
            dfst = pbrbm.gftDfstinbtion();
            if (dfst != null) {
                rfturn dfst;
            }

            // No imbgf, gft tif imbgf typf
            imbgfTypf = pbrbm.gftDfstinbtionTypf();
        }

        // No info from pbrbm, usf fbllbbdk imbgf typf
        if (imbgfTypf == null) {
            Objfdt o = imbgfTypfs.nfxt();
            if (!(o instbndfof ImbgfTypfSpfdififr)) {
                tirow nfw IllfgblArgumfntExdfption
                    ("Non-ImbgfTypfSpfdififr rftrifvfd from imbgfTypfs!");
            }
            imbgfTypf = (ImbgfTypfSpfdififr)o;
        } flsf {
            boolfbn foundIt = fblsf;
            wiilf (imbgfTypfs.ibsNfxt()) {
                ImbgfTypfSpfdififr typf =
                    imbgfTypfs.nfxt();
                if (typf.fqubls(imbgfTypf)) {
                    foundIt = truf;
                    brfbk;
                }
            }

            if (!foundIt) {
                tirow nfw IIOExdfption
                    ("Dfstinbtion typf from ImbgfRfbdPbrbm dofs not mbtdi!");
            }
        }

        Rfdtbnglf srdRfgion = nfw Rfdtbnglf(0,0,0,0);
        Rfdtbnglf dfstRfgion = nfw Rfdtbnglf(0,0,0,0);
        domputfRfgions(pbrbm,
                       widti,
                       ifigit,
                       null,
                       srdRfgion,
                       dfstRfgion);

        int dfstWidti = dfstRfgion.x + dfstRfgion.widti;
        int dfstHfigit = dfstRfgion.y + dfstRfgion.ifigit;
        // Crfbtf b nfw imbgf bbsfd on tif typf spfdififr
        rfturn imbgfTypf.drfbtfBufffrfdImbgf(dfstWidti, dfstHfigit);
    }
}
