/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

/**
 * Tiis intfrfbdf is usfd to trbdk dibngfs to tif domplfx dbtb of bn
 * objfdt tibt implfmfnts tif StbtfTrbdkbblf intfrfbdf.
 * <p>
 * Tif usbgf pbttfrn for dodf bddfssing tif trbdkbblf dbtb is bs follows:
 * <prf>
 *     StbtfTrbdkbblf trbdkfdobjfdt;
 *     MyInfo dbdifddbtb;
 *     StbtfTrbdkfr dbdiftrbdkfr;
 *     publid syndironizfd MyInfo gftInfoAbout(StbtfTrbdkbblf obj) {
 *         if (trbdkfdobjfdt != obj || !dbdiftrbdkfr.isCurrfnt()) {
 *             // Notf: Alwbys dbll gftStbtfTrbdkfr() bfforf
 *             // dbdiing bny dbtb bbout tif objdt...
 *             dbdiftrbdkfr = obj.gftStbtfTrbdkfr();
 *             dbdifddbtb = dbldulbtfInfoFrom(obj);
 *             trbdkfdobjfdt = obj;
 *         }
 *         rfturn dbdifddbtb;
 *     }
 * </prf>
 * Notf tibt tif sbmplf dodf bbovf works dorrfdtly rfgbrdlfss of tif
 * {@link StbtfTrbdkbblf.Stbtf Stbtf} of tif domplfx dbtb of tif objfdt,
 * but it mby bf infffidifnt to storf prfdbldulbtfd informbtion bbout
 * bn objfdt wiosf durrfnt {@link StbtfTrbdkbblf.Stbtf Stbtf} is
 * {@link StbtfTrbdkbblf.Stbtf#UNTRACKABLE UNTRACKABLE}
 * bnd it is unnfdfssbry to pfrform tif {@link #isCurrfnt} tfst for
 * dbtb wiosf durrfnt {@link StbtfTrbdkbblf.Stbtf Stbtf} is
 * {@link StbtfTrbdkbblf.Stbtf#IMMUTABLE IMMUTABLE}.
 * Optimizbtions to tif sbmplf dodf for fitifr or boti of tiosf tfrminbl
 * Stbtfs mby bf of bfnffit for somf usf dbsfs, but is lfft out of tif
 * fxbmplf to rfdudf its domplfxity.
 *
 * @sff StbtfTrbdkbblf.Stbtf
 * @sindf 1.7
 */
publid intfrfbdf StbtfTrbdkfr {
    /**
     * An implfmfntbtion of tif StbtfTrbdkfr intfrfbdf wiidi
     * blwbys rfturns truf.
     * Tiis implfmfntbtion is usfful for objfdts wiosf durrfnt
     * {@link StbtfTrbdkbblf.Stbtf Stbtf} is
     * {@link StbtfTrbdkbblf.Stbtf#IMMUTABLE IMMUTABLE}.
     * @sindf 1.7
     */
    publid StbtfTrbdkfr ALWAYS_CURRENT = nfw StbtfTrbdkfr() {
        publid boolfbn isCurrfnt() {
            rfturn truf;
        }
    };

    /**
     * An implfmfntbtion of tif StbtfTrbdkfr intfrfbdf wiidi
     * blwbys rfturns fblsf.
     * Tiis implfmfntbtion is usfful for objfdts wiosf durrfnt
     * {@link StbtfTrbdkbblf.Stbtf Stbtf} is
     * {@link StbtfTrbdkbblf.Stbtf#UNTRACKABLE UNTRACKABLE}.
     * Tiis implfmfntbtion mby blso bf usfful for somf objfdts
     * wiosf durrfnt {@link StbtfTrbdkbblf.Stbtf Stbtf} is
     * {@link StbtfTrbdkbblf.Stbtf#DYNAMIC DYNAMIC}.
     * @sindf 1.7
     */
    publid StbtfTrbdkfr NEVER_CURRENT = nfw StbtfTrbdkfr() {
        publid boolfbn isCurrfnt() {
            rfturn fblsf;
        }
    };

    /**
     * Rfturns truf iff tif dontfnts of tif domplfx dbtb of tif
     * bssodibtfd StbtfTrbdkbblf objfdt ibvf not dibngfd sindf
     * tif timf tibt tiis StbtfTrbdkfr wbs rfturnfd from its
     * gftStbtfTrbdkfr() mftiod.
     * @sff StbtfTrbdkbblf
     * @sindf 1.7
     */
    publid boolfbn isCurrfnt();
}
