/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.Sfriblizbblf;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtStrfbmExdfption;

/**
 * Tiis is tif dommon bbsf dlbss of bll Jbvb lbngubgf fnumfrbtion typfs.
 *
 * Morf informbtion bbout fnums, indluding dfsdriptions of tif
 * impliditly dfdlbrfd mftiods syntifsizfd by tif dompilfr, dbn bf
 * found in sfdtion 8.9 of
 * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
 *
 * <p> Notf tibt wifn using bn fnumfrbtion typf bs tif typf of b sft
 * or bs tif typf of tif kfys in b mbp, spfdiblizfd bnd fffidifnt
 * {@linkplbin jbvb.util.EnumSft sft} bnd {@linkplbin
 * jbvb.util.EnumMbp mbp} implfmfntbtions brf bvbilbblf.
 *
 * @pbrbm <E> Tif fnum typf subdlbss
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff     Clbss#gftEnumConstbnts()
 * @sff     jbvb.util.EnumSft
 * @sff     jbvb.util.EnumMbp
 * @sindf   1.5
 */
@SupprfssWbrnings("sfribl") // No sfriblVfrsionUID nffdfd duf to
                            // spfdibl-dbsing of fnum typfs.
publid bbstrbdt dlbss Enum<E fxtfnds Enum<E>>
        implfmfnts Compbrbblf<E>, Sfriblizbblf {
    /**
     * Tif nbmf of tiis fnum donstbnt, bs dfdlbrfd in tif fnum dfdlbrbtion.
     * Most progrbmmfrs siould usf tif {@link #toString} mftiod rbtifr tibn
     * bddfssing tiis fifld.
     */
    privbtf finbl String nbmf;

    /**
     * Rfturns tif nbmf of tiis fnum donstbnt, fxbdtly bs dfdlbrfd in its
     * fnum dfdlbrbtion.
     *
     * <b>Most progrbmmfrs siould usf tif {@link #toString} mftiod in
     * prfffrfndf to tiis onf, bs tif toString mftiod mby rfturn
     * b morf usfr-frifndly nbmf.</b>  Tiis mftiod is dfsignfd primbrily for
     * usf in spfdiblizfd situbtions wifrf dorrfdtnfss dfpfnds on gftting tif
     * fxbdt nbmf, wiidi will not vbry from rflfbsf to rflfbsf.
     *
     * @rfturn tif nbmf of tiis fnum donstbnt
     */
    publid finbl String nbmf() {
        rfturn nbmf;
    }

    /**
     * Tif ordinbl of tiis fnumfrbtion donstbnt (its position
     * in tif fnum dfdlbrbtion, wifrf tif initibl donstbnt is bssignfd
     * bn ordinbl of zfro).
     *
     * Most progrbmmfrs will ibvf no usf for tiis fifld.  It is dfsignfd
     * for usf by sopiistidbtfd fnum-bbsfd dbtb strudturfs, sudi bs
     * {@link jbvb.util.EnumSft} bnd {@link jbvb.util.EnumMbp}.
     */
    privbtf finbl int ordinbl;

    /**
     * Rfturns tif ordinbl of tiis fnumfrbtion donstbnt (its position
     * in its fnum dfdlbrbtion, wifrf tif initibl donstbnt is bssignfd
     * bn ordinbl of zfro).
     *
     * Most progrbmmfrs will ibvf no usf for tiis mftiod.  It is
     * dfsignfd for usf by sopiistidbtfd fnum-bbsfd dbtb strudturfs, sudi
     * bs {@link jbvb.util.EnumSft} bnd {@link jbvb.util.EnumMbp}.
     *
     * @rfturn tif ordinbl of tiis fnumfrbtion donstbnt
     */
    publid finbl int ordinbl() {
        rfturn ordinbl;
    }

    /**
     * Solf donstrudtor.  Progrbmmfrs dbnnot invokf tiis donstrudtor.
     * It is for usf by dodf fmittfd by tif dompilfr in rfsponsf to
     * fnum typf dfdlbrbtions.
     *
     * @pbrbm nbmf - Tif nbmf of tiis fnum donstbnt, wiidi is tif idfntififr
     *               usfd to dfdlbrf it.
     * @pbrbm ordinbl - Tif ordinbl of tiis fnumfrbtion donstbnt (its position
     *         in tif fnum dfdlbrbtion, wifrf tif initibl donstbnt is bssignfd
     *         bn ordinbl of zfro).
     */
    protfdtfd Enum(String nbmf, int ordinbl) {
        tiis.nbmf = nbmf;
        tiis.ordinbl = ordinbl;
    }

    /**
     * Rfturns tif nbmf of tiis fnum donstbnt, bs dontbinfd in tif
     * dfdlbrbtion.  Tiis mftiod mby bf ovfrriddfn, tiougi it typidblly
     * isn't nfdfssbry or dfsirbblf.  An fnum typf siould ovfrridf tiis
     * mftiod wifn b morf "progrbmmfr-frifndly" string form fxists.
     *
     * @rfturn tif nbmf of tiis fnum donstbnt
     */
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Rfturns truf if tif spfdififd objfdt is fqubl to tiis
     * fnum donstbnt.
     *
     * @pbrbm otifr tif objfdt to bf dompbrfd for fqublity witi tiis objfdt.
     * @rfturn  truf if tif spfdififd objfdt is fqubl to tiis
     *          fnum donstbnt.
     */
    publid finbl boolfbn fqubls(Objfdt otifr) {
        rfturn tiis==otifr;
    }

    /**
     * Rfturns b ibsi dodf for tiis fnum donstbnt.
     *
     * @rfturn b ibsi dodf for tiis fnum donstbnt.
     */
    publid finbl int ibsiCodf() {
        rfturn supfr.ibsiCodf();
    }

    /**
     * Tirows ClonfNotSupportfdExdfption.  Tiis gubrbntffs tibt fnums
     * brf nfvfr dlonfd, wiidi is nfdfssbry to prfsfrvf tifir "singlfton"
     * stbtus.
     *
     * @rfturn (nfvfr rfturns)
     */
    protfdtfd finbl Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        tirow nfw ClonfNotSupportfdExdfption();
    }

    /**
     * Compbrfs tiis fnum witi tif spfdififd objfdt for ordfr.  Rfturns b
     * nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis objfdt is lfss
     * tibn, fqubl to, or grfbtfr tibn tif spfdififd objfdt.
     *
     * Enum donstbnts brf only dompbrbblf to otifr fnum donstbnts of tif
     * sbmf fnum typf.  Tif nbturbl ordfr implfmfntfd by tiis
     * mftiod is tif ordfr in wiidi tif donstbnts brf dfdlbrfd.
     */
    publid finbl int dompbrfTo(E o) {
        Enum<?> otifr = (Enum<?>)o;
        Enum<E> sflf = tiis;
        if (sflf.gftClbss() != otifr.gftClbss() && // optimizbtion
            sflf.gftDfdlbringClbss() != otifr.gftDfdlbringClbss())
            tirow nfw ClbssCbstExdfption();
        rfturn sflf.ordinbl - otifr.ordinbl;
    }

    /**
     * Rfturns tif Clbss objfdt dorrfsponding to tiis fnum donstbnt's
     * fnum typf.  Two fnum donstbnts f1 bnd  f2 brf of tif
     * sbmf fnum typf if bnd only if
     *   f1.gftDfdlbringClbss() == f2.gftDfdlbringClbss().
     * (Tif vbluf rfturnfd by tiis mftiod mby difffr from tif onf rfturnfd
     * by tif {@link Objfdt#gftClbss} mftiod for fnum donstbnts witi
     * donstbnt-spfdifid dlbss bodifs.)
     *
     * @rfturn tif Clbss objfdt dorrfsponding to tiis fnum donstbnt's
     *     fnum typf
     */
    @SupprfssWbrnings("undifdkfd")
    publid finbl Clbss<E> gftDfdlbringClbss() {
        Clbss<?> dlbzz = gftClbss();
        Clbss<?> zupfr = dlbzz.gftSupfrdlbss();
        rfturn (zupfr == Enum.dlbss) ? (Clbss<E>)dlbzz : (Clbss<E>)zupfr;
    }

    /**
     * Rfturns tif fnum donstbnt of tif spfdififd fnum typf witi tif
     * spfdififd nbmf.  Tif nbmf must mbtdi fxbdtly bn idfntififr usfd
     * to dfdlbrf bn fnum donstbnt in tiis typf.  (Extrbnfous wiitfspbdf
     * dibrbdtfrs brf not pfrmittfd.)
     *
     * <p>Notf tibt for b pbrtidulbr fnum typf {@dodf T}, tif
     * impliditly dfdlbrfd {@dodf publid stbtid T vblufOf(String)}
     * mftiod on tibt fnum mby bf usfd instfbd of tiis mftiod to mbp
     * from b nbmf to tif dorrfsponding fnum donstbnt.  All tif
     * donstbnts of bn fnum typf dbn bf obtbinfd by dblling tif
     * implidit {@dodf publid stbtid T[] vblufs()} mftiod of tibt
     * typf.
     *
     * @pbrbm <T> Tif fnum typf wiosf donstbnt is to bf rfturnfd
     * @pbrbm fnumTypf tif {@dodf Clbss} objfdt of tif fnum typf from wiidi
     *      to rfturn b donstbnt
     * @pbrbm nbmf tif nbmf of tif donstbnt to rfturn
     * @rfturn tif fnum donstbnt of tif spfdififd fnum typf witi tif
     *      spfdififd nbmf
     * @tirows IllfgblArgumfntExdfption if tif spfdififd fnum typf ibs
     *         no donstbnt witi tif spfdififd nbmf, or tif spfdififd
     *         dlbss objfdt dofs not rfprfsfnt bn fnum typf
     * @tirows NullPointfrExdfption if {@dodf fnumTypf} or {@dodf nbmf}
     *         is null
     * @sindf 1.5
     */
    publid stbtid <T fxtfnds Enum<T>> T vblufOf(Clbss<T> fnumTypf,
                                                String nbmf) {
        T rfsult = fnumTypf.fnumConstbntDirfdtory().gft(nbmf);
        if (rfsult != null)
            rfturn rfsult;
        if (nbmf == null)
            tirow nfw NullPointfrExdfption("Nbmf is null");
        tirow nfw IllfgblArgumfntExdfption(
            "No fnum donstbnt " + fnumTypf.gftCbnonidblNbmf() + "." + nbmf);
    }

    /**
     * fnum dlbssfs dbnnot ibvf finblizf mftiods.
     */
    protfdtfd finbl void finblizf() { }

    /**
     * prfvfnt dffbult dfsfriblizbtion
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in) tirows IOExdfption,
        ClbssNotFoundExdfption {
        tirow nfw InvblidObjfdtExdfption("dbn't dfsfriblizf fnum");
    }

    privbtf void rfbdObjfdtNoDbtb() tirows ObjfdtStrfbmExdfption {
        tirow nfw InvblidObjfdtExdfption("dbn't dfsfriblizf fnum");
    }
}
