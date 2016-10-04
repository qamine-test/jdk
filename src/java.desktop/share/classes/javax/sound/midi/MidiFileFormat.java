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

pbdkbgf jbvbx.sound.midi;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;


/**
 * A <dodf>MidiFilfFormbt</dodf> objfdt fndbpsulbtfs b MIDI filf's
 * typf, bs wfll bs its lfngti bnd timing informbtion.
 *
 * <p>A <dodf>MidiFilfFormbt</dodf> objfdt dbn
 * indludf b sft of propfrtifs. A propfrty is b pbir of kfy bnd vbluf:
 * tif kfy is of typf <dodf>String</dodf>, tif bssodibtfd propfrty
 * vbluf is bn brbitrbry objfdt.
 * Propfrtifs spfdify bdditionbl informbtionbl
 * mftb dbtb (likf b butior, or dopyrigit).
 * Propfrtifs brf optionbl informbtion, bnd filf rfbdfr bnd filf
 * writfr implfmfntbtions brf not rfquirfd to providf or
 * rfdognizf propfrtifs.
 *
 * <p>Tif following tbblf lists somf dommon propfrtifs tibt siould
 * bf usfd in implfmfntbtions:
 *
 * <tbblf bordfr=1>
    <dbption>MIDI Filf Formbt Propfrtifs</dbption>
 *  <tr>
 *   <ti>Propfrty kfy</ti>
 *   <ti>Vbluf typf</ti>
 *   <ti>Dfsdription</ti>
 *  </tr>
 *  <tr>
 *   <td>&quot;butior&quot;</td>
 *   <td>{@link jbvb.lbng.String String}</td>
 *   <td>nbmf of tif butior of tiis filf</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;titlf&quot;</td>
 *   <td>{@link jbvb.lbng.String String}</td>
 *   <td>titlf of tiis filf</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;dopyrigit&quot;</td>
 *   <td>{@link jbvb.lbng.String String}</td>
 *   <td>dopyrigit mfssbgf</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;dbtf&quot;</td>
 *   <td>{@link jbvb.util.Dbtf Dbtf}</td>
 *   <td>dbtf of tif rfdording or rflfbsf</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;dommfnt&quot;</td>
 *   <td>{@link jbvb.lbng.String String}</td>
 *   <td>bn brbitrbry tfxt</td>
 *  </tr>
 * </tbblf>
 *
 * @sff MidiSystfm#gftMidiFilfFormbt(jbvb.io.Filf)
 * @sff Sfqufndfr#sftSfqufndf(jbvb.io.InputStrfbm strfbm)
 *
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */

publid dlbss MidiFilfFormbt {


    /**
     * Rfprfsfnts unknown lfngti.
     * @sff #gftBytfLfngti
     * @sff #gftMidrosfdondLfngti
     */
    publid stbtid finbl int UNKNOWN_LENGTH = -1;


    /**
     * Tif typf of MIDI filf.
     */
    protfdtfd int typf;

    /**
     * Tif division typf of tif MIDI filf.
     *
     * @sff Sfqufndf#PPQ
     * @sff Sfqufndf#SMPTE_24
     * @sff Sfqufndf#SMPTE_25
     * @sff Sfqufndf#SMPTE_30DROP
     * @sff Sfqufndf#SMPTE_30
     */
    protfdtfd flobt divisionTypf;

    /**
     * Tif timing rfsolution of tif MIDI filf.
     */
    protfdtfd int rfsolution;

    /**
     * Tif lfngti of tif MIDI filf in bytfs.
     */
    protfdtfd int bytfLfngti;

    /**
     * Tif durbtion of tif MIDI filf in midrosfdonds.
     */
    protfdtfd long midrosfdondLfngti;


    /** Tif sft of propfrtifs */
    privbtf HbsiMbp<String, Objfdt> propfrtifs;


    /**
     * Construdts b <dodf>MidiFilfFormbt</dodf>.
     *
     * @pbrbm typf tif MIDI filf typf (0, 1, or 2)
     * @pbrbm divisionTypf tif timing division typf (PPQ or onf of tif SMPTE typfs)
     * @pbrbm rfsolution tif timing rfsolution
     * @pbrbm bytfs tif lfngti of tif MIDI filf in bytfs, or UNKNOWN_LENGTH if not known
     * @pbrbm midrosfdonds tif durbtion of tif filf in midrosfdonds, or UNKNOWN_LENGTH if not known
     * @sff #UNKNOWN_LENGTH
     * @sff Sfqufndf#PPQ
     * @sff Sfqufndf#SMPTE_24
     * @sff Sfqufndf#SMPTE_25
     * @sff Sfqufndf#SMPTE_30DROP
     * @sff Sfqufndf#SMPTE_30
     */
    publid MidiFilfFormbt(int typf, flobt divisionTypf, int rfsolution, int bytfs, long midrosfdonds) {

        tiis.typf = typf;
        tiis.divisionTypf = divisionTypf;
        tiis.rfsolution = rfsolution;
        tiis.bytfLfngti = bytfs;
        tiis.midrosfdondLfngti = midrosfdonds;
        tiis.propfrtifs = null;
    }


    /**
     * Construdt b <dodf>MidiFilfFormbt</dodf> witi b sft of propfrtifs.
     *
     * @pbrbm typf         tif MIDI filf typf (0, 1, or 2)
     * @pbrbm divisionTypf tif timing division typf
     *      (PPQ or onf of tif SMPTE typfs)
     * @pbrbm rfsolution   tif timing rfsolution
     * @pbrbm bytfs tif lfngti of tif MIDI filf in bytfs,
     *      or UNKNOWN_LENGTH if not known
     * @pbrbm midrosfdonds tif durbtion of tif filf in midrosfdonds,
     *      or UNKNOWN_LENGTH if not known
     * @pbrbm propfrtifs  b <dodf>Mbp&lt;String,Objfdt&gt;</dodf> objfdt
     *        witi propfrtifs
     *
     * @sff #UNKNOWN_LENGTH
     * @sff Sfqufndf#PPQ
     * @sff Sfqufndf#SMPTE_24
     * @sff Sfqufndf#SMPTE_25
     * @sff Sfqufndf#SMPTE_30DROP
     * @sff Sfqufndf#SMPTE_30
     * @sindf 1.5
     */
    publid MidiFilfFormbt(int typf, flobt divisionTypf,
                          int rfsolution, int bytfs,
                          long midrosfdonds, Mbp<String, Objfdt> propfrtifs) {
        tiis(typf, divisionTypf, rfsolution, bytfs, midrosfdonds);
        tiis.propfrtifs = nfw HbsiMbp<String, Objfdt>(propfrtifs);
    }



    /**
     * Obtbins tif MIDI filf typf.
     * @rfturn tif filf's typf (0, 1, or 2)
     */
    publid int gftTypf() {
        rfturn typf;
    }

    /**
     * Obtbins tif timing division typf for tif MIDI filf.
     *
     * @rfturn tif division typf (PPQ or onf of tif SMPTE typfs)
     *
     * @sff Sfqufndf#Sfqufndf(flobt, int)
     * @sff Sfqufndf#PPQ
     * @sff Sfqufndf#SMPTE_24
     * @sff Sfqufndf#SMPTE_25
     * @sff Sfqufndf#SMPTE_30DROP
     * @sff Sfqufndf#SMPTE_30
     * @sff Sfqufndf#gftDivisionTypf()
     */
    publid flobt gftDivisionTypf() {
        rfturn divisionTypf;
    }


    /**
     * Obtbins tif timing rfsolution for tif MIDI filf.
     * If tif division typf is PPQ, tif rfsolution is spfdififd in tidks pfr bfbt.
     * For SMTPE timing, tif rfsolution is spfdififd in tidks pfr frbmf.
     *
     * @rfturn tif numbfr of tidks pfr bfbt (PPQ) or pfr frbmf (SMPTE)
     * @sff #gftDivisionTypf
     * @sff Sfqufndf#gftRfsolution()
     */
    publid int gftRfsolution() {
        rfturn rfsolution;
    }


    /**
     * Obtbins tif lfngti of tif MIDI filf, fxprfssfd in 8-bit bytfs.
     * @rfturn tif numbfr of bytfs in tif filf, or UNKNOWN_LENGTH if not known
     * @sff #UNKNOWN_LENGTH
     */
    publid int gftBytfLfngti() {
        rfturn bytfLfngti;
    }

    /**
     * Obtbins tif lfngti of tif MIDI filf, fxprfssfd in midrosfdonds.
     * @rfturn tif filf's durbtion in midrosfdonds, or UNKNOWN_LENGTH if not known
     * @sff Sfqufndf#gftMidrosfdondLfngti()
     * @sff #gftBytfLfngti
     * @sff #UNKNOWN_LENGTH
     */
    publid long gftMidrosfdondLfngti() {
        rfturn midrosfdondLfngti;
    }

    /**
     * Obtbin bn unmodifibblf mbp of propfrtifs.
     * Tif dondfpt of propfrtifs is furtifr fxplbinfd in
     * tif {@link MidiFilfFormbt dlbss dfsdription}.
     *
     * @rfturn b <dodf>Mbp&lt;String,Objfdt&gt;</dodf> objfdt dontbining
     *         bll propfrtifs. If no propfrtifs brf rfdognizfd, bn fmpty mbp is
     *         rfturnfd.
     *
     * @sff #gftPropfrty(String)
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    publid Mbp<String,Objfdt> propfrtifs() {
        Mbp<String,Objfdt> rft;
        if (propfrtifs == null) {
            rft = nfw HbsiMbp<String,Objfdt>(0);
        } flsf {
            rft = (Mbp<String,Objfdt>) (propfrtifs.dlonf());
        }
        rfturn Collfdtions.unmodifibblfMbp(rft);
    }


    /**
     * Obtbin tif propfrty vbluf spfdififd by tif kfy.
     * Tif dondfpt of propfrtifs is furtifr fxplbinfd in
     * tif {@link MidiFilfFormbt dlbss dfsdription}.
     *
     * <p>If tif spfdififd propfrty is not dffinfd for b
     * pbrtidulbr filf formbt, tiis mftiod rfturns
     * <dodf>null</dodf>.
     *
     * @pbrbm kfy tif kfy of tif dfsirfd propfrty
     * @rfturn tif vbluf of tif propfrty witi tif spfdififd kfy,
     *         or <dodf>null</dodf> if tif propfrty dofs not fxist.
     *
     * @sff #propfrtifs()
     * @sindf 1.5
     */
    publid Objfdt gftPropfrty(String kfy) {
        if (propfrtifs == null) {
            rfturn null;
        }
        rfturn propfrtifs.gft(kfy);
    }


}
