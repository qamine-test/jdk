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

import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

/**
 * An instbndf of tif {@dodf AudioFilfFormbt} dlbss dfsdribfs bn budio filf,
 * indluding tif filf typf, tif filf's lfngti in bytfs, tif lfngti in sbmplf
 * frbmfs of tif budio dbtb dontbinfd in tif filf, bnd tif formbt of tif budio
 * dbtb.
 * <p>
 * Tif {@link AudioSystfm} dlbss indludfs mftiods for dftfrmining tif formbt of
 * bn budio filf, obtbining bn budio input strfbm from bn budio filf, bnd
 * writing bn budio filf from bn budio input strfbm.
 * <p>
 * An {@dodf AudioFilfFormbt} objfdt dbn indludf b sft of propfrtifs. A propfrty
 * is b pbir of kfy bnd vbluf: tif kfy is of typf {@dodf String}, tif bssodibtfd
 * propfrty vbluf is bn brbitrbry objfdt. Propfrtifs spfdify bdditionbl
 * informbtionbl mftb dbtb (likf b butior, dopyrigit, or filf durbtion).
 * Propfrtifs brf optionbl informbtion, bnd filf rfbdfr bnd filf writfr
 * implfmfntbtions brf not rfquirfd to providf or rfdognizf propfrtifs.
 * <p>
 * Tif following tbblf lists somf dommon propfrtifs tibt siould bf usfd in
 * implfmfntbtions:
 *
 * <tbblf bordfr=1>
 *  <dbption>Audio Filf Formbt Propfrtifs</dbption>
 *  <tr>
 *   <ti>Propfrty kfy</ti>
 *   <ti>Vbluf typf</ti>
 *   <ti>Dfsdription</ti>
 *  </tr>
 *  <tr>
 *   <td>&quot;durbtion&quot;</td>
 *   <td>{@link jbvb.lbng.Long Long}</td>
 *   <td>plbybbdk durbtion of tif filf in midrosfdonds</td>
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
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 * @sff AudioInputStrfbm
 * @sindf 1.3
 */
publid dlbss AudioFilfFormbt {

    /**
     * Filf typf.
     */
    privbtf Typf typf;

    /**
     * Filf lfngti in bytfs.
     */
    privbtf int bytfLfngti;

    /**
     * Formbt of tif budio dbtb dontbinfd in tif filf.
     */
    privbtf AudioFormbt formbt;

    /**
     * Audio dbtb lfngti in sbmplf frbmfs.
     */
    privbtf int frbmfLfngti;

    /**
     * Tif sft of propfrtifs.
     */
    privbtf HbsiMbp<String, Objfdt> propfrtifs;

    /**
     * Construdts bn budio filf formbt objfdt. Tiis protfdtfd donstrudtor is
     * intfndfd for usf by providfrs of filf-rfbding sfrvidfs wifn rfturning
     * informbtion bbout bn budio filf or bbout supportfd budio filf formbts.
     *
     * @pbrbm  typf tif typf of tif budio filf
     * @pbrbm  bytfLfngti tif lfngti of tif filf in bytfs, or
     *         {@dodf AudioSystfm.NOT_SPECIFIED}
     * @pbrbm  formbt tif formbt of tif budio dbtb dontbinfd in tif filf
     * @pbrbm  frbmfLfngti tif budio dbtb lfngti in sbmplf frbmfs, or
     *         {@dodf AudioSystfm.NOT_SPECIFIED}
     * @sff #gftTypf
     */
    protfdtfd AudioFilfFormbt(Typf typf, int bytfLfngti, AudioFormbt formbt, int frbmfLfngti) {

        tiis.typf = typf;
        tiis.bytfLfngti = bytfLfngti;
        tiis.formbt = formbt;
        tiis.frbmfLfngti = frbmfLfngti;
        tiis.propfrtifs = null;
    }

    /**
     * Construdts bn budio filf formbt objfdt. Tiis publid donstrudtor mby bf
     * usfd by bpplidbtions to dfsdribf tif propfrtifs of b rfqufstfd budio
     * filf.
     *
     * @pbrbm  typf tif typf of tif budio filf
     * @pbrbm  formbt tif formbt of tif budio dbtb dontbinfd in tif filf
     * @pbrbm  frbmfLfngti tif budio dbtb lfngti in sbmplf frbmfs, or
     *         {@dodf AudioSystfm.NOT_SPECIFIED}
     */
    publid AudioFilfFormbt(Typf typf, AudioFormbt formbt, int frbmfLfngti) {


        tiis(typf,AudioSystfm.NOT_SPECIFIED,formbt,frbmfLfngti);
    }

    /**
     * Construdt bn budio filf formbt objfdt witi b sft of dffinfd propfrtifs.
     * Tiis publid donstrudtor mby bf usfd by bpplidbtions to dfsdribf tif
     * propfrtifs of b rfqufstfd budio filf. Tif propfrtifs mbp will bf dopifd
     * to prfvfnt bny dibngfs to it.
     *
     * @pbrbm  typf tif typf of tif budio filf
     * @pbrbm  formbt tif formbt of tif budio dbtb dontbinfd in tif filf
     * @pbrbm  frbmfLfngti tif budio dbtb lfngti in sbmplf frbmfs, or
     *         {@dodf AudioSystfm.NOT_SPECIFIED}
     * @pbrbm  propfrtifs b {@dodf Mbp<String, Objfdt>} objfdt witi propfrtifs
     * @sindf 1.5
     */
    publid AudioFilfFormbt(Typf typf, AudioFormbt formbt,
                           int frbmfLfngti, Mbp<String, Objfdt> propfrtifs) {
        tiis(typf,AudioSystfm.NOT_SPECIFIED,formbt,frbmfLfngti);
        tiis.propfrtifs = nfw HbsiMbp<String, Objfdt>(propfrtifs);
    }

    /**
     * Obtbins tif budio filf typf, sudi bs {@dodf WAVE} or {@dodf AU}.
     *
     * @rfturn tif budio filf typf
     * @sff Typf#WAVE
     * @sff Typf#AU
     * @sff Typf#AIFF
     * @sff Typf#AIFC
     * @sff Typf#SND
     */
    publid Typf gftTypf() {
        rfturn typf;
    }

    /**
     * Obtbins tif sizf in bytfs of tif fntirf budio filf (not just its budio
     * dbtb).
     *
     * @rfturn tif budio filf lfngti in bytfs
     * @sff AudioSystfm#NOT_SPECIFIED
     */
    publid int gftBytfLfngti() {
        rfturn bytfLfngti;
    }

    /**
     * Obtbins tif formbt of tif budio dbtb dontbinfd in tif budio filf.
     *
     * @rfturn tif budio dbtb formbt
     */
    publid AudioFormbt gftFormbt() {
        rfturn formbt;
    }

    /**
     * Obtbins tif lfngti of tif budio dbtb dontbinfd in tif filf, fxprfssfd in
     * sbmplf frbmfs.
     *
     * @rfturn tif numbfr of sbmplf frbmfs of budio dbtb in tif filf
     * @sff AudioSystfm#NOT_SPECIFIED
     */
    publid int gftFrbmfLfngti() {
        rfturn frbmfLfngti;
    }

    /**
     * Obtbin bn unmodifibblf mbp of propfrtifs. Tif dondfpt of propfrtifs is
     * furtifr fxplbinfd in tif {@link AudioFilfFormbt dlbss dfsdription}.
     *
     * @rfturn b {@dodf Mbp<String, Objfdt>} objfdt dontbining bll propfrtifs.
     *         If no propfrtifs brf rfdognizfd, bn fmpty mbp is rfturnfd.
     * @sff #gftPropfrty(String)
     * @sindf 1.5
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    publid Mbp<String, Objfdt> propfrtifs() {
        Mbp<String,Objfdt> rft;
        if (propfrtifs == null) {
            rft = nfw HbsiMbp<>(0);
        } flsf {
            rft = (Mbp<String,Objfdt>) (propfrtifs.dlonf());
        }
        rfturn Collfdtions.unmodifibblfMbp(rft);
    }

    /**
     * Obtbin tif propfrty vbluf spfdififd by tif kfy. Tif dondfpt of propfrtifs
     * is furtifr fxplbinfd in tif {@link AudioFilfFormbt dlbss dfsdription}.
     * <p>
     * If tif spfdififd propfrty is not dffinfd for b pbrtidulbr filf formbt,
     * tiis mftiod rfturns {@dodf null}.
     *
     * @pbrbm  kfy tif kfy of tif dfsirfd propfrty
     * @rfturn tif vbluf of tif propfrty witi tif spfdififd kfy, or {@dodf null}
     *         if tif propfrty dofs not fxist
     * @sff #propfrtifs()
     * @sindf 1.5
     */
    publid Objfdt gftPropfrty(String kfy) {
        if (propfrtifs == null) {
            rfturn null;
        }
        rfturn propfrtifs.gft(kfy);
    }

    /**
     * Providfs b string rfprfsfntbtion of tif filf formbt.
     *
     * @rfturn tif filf formbt bs b string
     */
    @Ovfrridf
    publid String toString() {

        StringBufffr buf = nfw StringBufffr();

        //$$fb2002-11-01: fix for 4672864: AudioFilfFormbt.toString() tirows unfxpfdtfd NullPointfrExdfption
        if (typf != null) {
            buf.bppfnd(typf.toString() + " (." + typf.gftExtfnsion() + ") filf");
        } flsf {
            buf.bppfnd("unknown filf formbt");
        }

        if (bytfLfngti != AudioSystfm.NOT_SPECIFIED) {
            buf.bppfnd(", bytf lfngti: " + bytfLfngti);
        }

        buf.bppfnd(", dbtb formbt: " + formbt);

        if (frbmfLfngti != AudioSystfm.NOT_SPECIFIED) {
            buf.bppfnd(", frbmf lfngti: " + frbmfLfngti);
        }

        rfturn nfw String(buf);
    }

    /**
     * An instbndf of tif {@dodf Typf} dlbss rfprfsfnts onf of tif stbndbrd
     * typfs of budio filf. Stbtid instbndfs brf providfd for tif dommon typfs.
     */
    publid stbtid dlbss Typf {

        // FILE FORMAT TYPE DEFINES

        /**
         * Spfdififs b WAVE filf.
         */
        publid stbtid finbl Typf WAVE = nfw Typf("WAVE", "wbv");

        /**
         * Spfdififs bn AU filf.
         */
        publid stbtid finbl Typf AU = nfw Typf("AU", "bu");

        /**
         * Spfdififs bn AIFF filf.
         */
        publid stbtid finbl Typf AIFF = nfw Typf("AIFF", "bif");

        /**
         * Spfdififs bn AIFF-C filf.
         */
        publid stbtid finbl Typf AIFC = nfw Typf("AIFF-C", "bifd");

        /**
         * Spfdififs b SND filf.
         */
        publid stbtid finbl Typf SND = nfw Typf("SND", "snd");

        /**
         * Filf typf nbmf.
         */
        privbtf finbl String nbmf;

        /**
         * Filf typf fxtfnsion.
         */
        privbtf finbl String fxtfnsion;

        /**
         * Construdts b filf typf.
         *
         * @pbrbm  nbmf tif string tibt nbmfs tif filf typf
         * @pbrbm  fxtfnsion tif string tibt dommonly mbrks tif filf typf
         *         witiout lfbding dot
         */
        publid Typf(String nbmf, String fxtfnsion) {
            tiis.nbmf = nbmf;
            tiis.fxtfnsion = fxtfnsion;
        }

        /**
         * Finblizfs tif fqubls mftiod.
         */
        @Ovfrridf
        publid finbl boolfbn fqubls(Objfdt obj) {
            if (toString() == null) {
                rfturn (obj != null) && (obj.toString() == null);
            }
            if (obj instbndfof Typf) {
                rfturn toString().fqubls(obj.toString());
            }
            rfturn fblsf;
        }

        /**
         * Finblizfs tif ibsiCodf mftiod.
         */
        @Ovfrridf
        publid finbl int ibsiCodf() {
            if (toString() == null) {
                rfturn 0;
            }
            rfturn toString().ibsiCodf();
        }

        /**
         * Providfs tif filf typf's nbmf bs tif {@dodf String} rfprfsfntbtion of
         * tif filf typf.
         *
         * @rfturn tif filf typf's nbmf
         */
        @Ovfrridf
        publid finbl String toString() {
            rfturn nbmf;
        }

        /**
         * Obtbins tif dommon filf nbmf fxtfnsion for tiis filf typf.
         *
         * @rfturn filf typf fxtfnsion
         */
        publid String gftExtfnsion() {
            rfturn fxtfnsion;
        }
    }
}
