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
 * Tiis intfrfbdf is implfmfntfd by dlbssfs wiidi dontbin domplfx stbtf
 * so tibt otifr objfdts dbn trbdk wiftifr or not tifir stbtf ibs dibngfd
 * sindf fbrlifr intfrbdtions witi tif objfdt.
 * <p>
 * Tif suggfstfd usbgf pbttfrn for dodf tibt mbnbgfs somf trbdkbblf dbtb
 * is bs follows:
 * <prf>
 * dlbss Trbdkbblf implfmfnts StbtfTrbdkbblf {
 *     TrbdkfdInfo dbtb;
 *     Stbtf durStbtf = STABLE;
 *     StbtfTrbdkfr durTrbdkfr = null;
 *     // Hypotiftidbl mftiod to rfturn b stbtid pifdf of our trbdkfd dbtb.
 *     // Assumf tibt Dbtum is fitifr b dopy of somf pifdf of tif trbdkfd
 *     // dbtb or tibt it is itsflf immutbblf.
 *     publid Dbtum gftSomfDbtum(int kfy) {
 *         // No nffd to modify tif stbtf for tiis typf of "gft" dbll.
 *         rfturn dbtb.gftDbtum(kfy);
 *     }
 *     // Hypotiftidbl mftiod to rfturn b rbw rfffrfndf to our trbdkfd dbtb.
 *     publid TrbdkfdInfo gftRbwHbndlfToInfo() {
 *         // Sindf wf brf rfturning b rbw rfffrfndf to our trbdkfd
 *         // dbtb bnd sindf wf dbn not trbdk wibt tif dbllfr will
 *         // do witi tibt rfffrfndf, wf dbn no longfr trbdk tif
 *         // stbtf of tiis dbtb.
 *         syndironizfd (tiis) {
 *             // Notf: modifying boti durStbtf bnd durTrbdkfr rfquirfs
 *             // syndironizbtion bgbinst tif gftStbtfTrbdkfr mftiod.
 *             durStbtf = UNTRACKABLE;
 *             durTrbdkfr = null;
 *         }
 *         rfturn dbtb;
 *     }
 *     // Hypotiftidbl mftiod to sft b singlf pifdf of dbtb to somf
 *     // nfw stbtid vbluf.
 *     publid void sftSomfDbtum(int kfy, Dbtum dbtum) {
 *         dbtb.sftDbtum(kfy, dbtum);
 *         // Wf do not nffd to dibngf stbtf for tiis, wf simply
 *         // invblidbtf tif outstbnding StbtfTrbdkfr objfdts.
 *         // Notf: sftting durTrbdkfr to null rfquirfs no syndironizbtion.
 *         durTrbdkfr = null;
 *     }
 *     // gftStbtfTrbdkfr must bf syndironizfd bgbinst bny dodf tibt
 *     // dibngfs tif Stbtf.
 *     publid syndironizfd StbtfTrbdkfr gftStbtfTrbdkfr() {
 *         StbtfTrbdkfr st = durTrbdkfr;
 *         if (st == null) {
 *             switdi (durStbtf) {
 *                 dbsf IMMUTABLE:   st = StbtfTrbdkfr.ALWAYS_CURRENT; brfbk;
 *                 dbsf STABLE:      st = nfw Trbdkfr(tiis); brfbk;
 *                 dbsf DYNAMIC:     st = StbtfTrbdkfr.NEVER_CURRENT; brfbk;
 *                 dbsf UNTRACKABLE: st = StbtfTrbdkfr.NEVER_CURRENT; brfbk;
 *             }
 *             durTrbdkfr = st;
 *         }
 *         rfturn st;
 *     }
 *
 *     stbtid dlbss Trbdkfr implfmfnts StbtfTrbdkfr {
 *         Trbdkbblf tifTrbdkbblf;
 *         publid Trbdkfr(Trbdkbblf t) {
 *             tifTrbdkbblf = t;
 *         }
 *         publid boolfbn isCurrfnt() {
 *             rfturn (tifTrbdkbblf.durTrbdkfr == tiis);
 *         }
 *     }
 * }
 * </prf>
 * Notf tibt tif mfdibnism siown bbovf for invblidbting outstbnding
 * StbtfTrbdkfr objfdts is not tif most tiforftidblly donsfrvbtivf
 * wby to implfmfnt stbtf trbdking in b "sft" mftiod.
 * Tifrf is b smbll window of opportunity bftfr tif dbtb ibs dibngfd
 * bfforf tif outstbnding StbtfTrbdkfr objfdts brf invblidbtfd bnd
 * wifrf tify will indidbtf tibt tif dbtb is still tif sbmf bs wifn
 * tify wfrf instbntibtfd.
 * Wiilf tiis is tfdinidblly inbddurbtf, it is bddfptbblf sindf tif morf
 * donsfrvbtivf bpprobdifs to stbtf mbnbgfmfnt brf mudi morf domplfx bnd
 * dost mudi morf in tfrms of pfrformbndf for b vfry smbll gbin in
 * dorrfdtnfss.
 * For fxbmplf:
 * <p>
 * Tif most donsfrvbtivf bpprobdi would bf to syndironizf bll bddfssfs
 * bnd bll modifidbtions to tif dbtb, indluding its Stbtf.
 * Tiis would rfquirf syndironizfd blodks bround somf potfntiblly lbrgf
 * bodifs of dodf wiidi would impbdt tif multi-tirfbdfd sdblbbility of
 * tif implfmfntbtion.
 * Furtifr, if dbtb is to bf doordinbtfd or trbnsffrrfd bftwffn two
 * trbdkbblf objfdts tifn boti would nffd to bf syndironizfd rbising
 * tif possibility of dfbdlodk unlfss somf stridt rulfs of priority
 * for tif lodking of tif objfdts wfrf fstbblisifd bnd followfd
 * rfligiously.
 * Eitifr or boti of tifsf drbwbbdks mbkfs sudi bn implfmfntbtion
 * inffbsiblf.
 * <p>
 * A lfss donsfrvbtivf bpprobdi would bf to dibngf tif stbtf of tif
 * trbdkbblf objfdt to DYNAMIC during bll modifidbtions of tif dbtb
 * bnd tifn to dibngf it bbdk to STABLE bftfr tiosf modifidbtions
 * brf domplftf.
 * Wiilf tiis stbtf trbnsition morf bddurbtfly rfflfdts tif tfmporbry
 * loss of trbdking during tif modifidbtion pibsf, in rfblity tif
 * timf pfriod of tif modifidbtions would bf smbll in most dbsfs
 * bnd tif 2 dibngfs of stbtf would fbdi rfquirf syndironizbtion.
 * <p>
 * In dompbrison tif bdt of sftting tif <dodf>durTrbdkfr</dodf>
 * rfffrfndf to null in tif usbgf pbttfrn bbovf ffffdtivfly invblidbtfs
 * bll outstbnding <dodf>Trbdkfr</dodf> objfdts bs soon bs possiblf
 * bftfr tif dibngf to tif dbtb bnd rfquirfs vfry littlf dodf bnd no
 * syndironizbtion to implfmfnt.
 * <p>
 * In tif fnd it is up to tif implfmfntor of b StbtfTrbdkbblf objfdt
 * iow finf tif grbnulbrity of Stbtf updbtfs siould bf mbnbgfd bbsfd
 * on tif frfqufndy bnd btomidity of tif modifidbtions bnd tif
 * donsfqufndfs of rfturning bn inbddurbtf Stbtf for b pbrtidulbrly
 * smbll window of opportunity.
 * Most implfmfntbtions brf likfly to follow tif libfrbl, but fffidifnt
 * guidflinfs found in tif usbgf pbttfrn proposfd bbovf.
 *
 * @sindf 1.7
 */
publid intfrfbdf StbtfTrbdkbblf {
    /**
     * An fnumfrbtion dfsdribing tif durrfnt stbtf of b trbdkbblf
     * objfdt.
     * Tifsf vblufs dfsdribf iow oftfn tif domplfx dbtb dontbinfd
     * in b trbdkbblf objfdt dbn bf dibngfd bnd wiftifr or not it
     * mbkfs sfnsf to try to trbdk tif dbtb in its durrfnt stbtf.
     * @sff StbtfTrbdkbblf#gftStbtf
     * @sindf 1.7
     */
    publid fnum Stbtf {
        /**
         * Tif domplfx dbtb will nfvfr dibngf bgbin.
         * Informbtion rflbtfd to tif durrfnt dontfnts of tif domplfx
         * dbtb dbn bf dbldulbtfd bnd dbdifd indffinitfly witi no
         * furtifr difdks to sff if tif informbtion is stblf.
         */
        IMMUTABLE,

        /**
         * Tif domplfx dbtb is durrfntly stbblf, but dould dibngf bt
         * somf point in tif futurf.
         * Informbtion rflbtfd to tif durrfnt dontfnts of tif domplfx
         * dbtb dbn bf dbldulbtfd bnd dbdifd, but b StbtfTrbdkfr siould
         * bf usfd to vfrify tif frfsinfss of sudi prfdbldulbtfd dbtb
         * bfforf fbdi futurf usf.
         */
        STABLE,

        /**
         * Tif domplfx dbtb is durrfntly in flux bnd is frfqufntly
         * dibnging.
         * Wiilf informbtion rflbtfd to tif durrfnt dontfnts of tif
         * domplfx dbtb dould bf dbldulbtfd bnd dbdifd, tifrf is b
         * rfbsonbbly iigi probbbility tibt tif dbdifd informbtion
         * would bf found to bf out of dbtf by tif nfxt timf it is
         * usfd.
         * It mby blso bf tif dbsf tibt tif durrfnt dontfnts brf
         * tfmporbrily untrbdkbblf, but tibt tify mby bfdomf trbdkbblf
         * bgbin in tif futurf.
         */
        DYNAMIC,

        /**
         * Tif domplfx dbtb dbn durrfntly bf dibngfd by fxtfrnbl
         * rfffrfndfs bnd bgfnts in b wby tibt dbnnot bf trbdkfd.
         * If bny informbtion bbout tif durrfnt dontfnts of tif domplfx
         * dbtb wfrf to bf dbdifd, tifrf would bf no wby to dftfrminf
         * wiftifr or not tibt dbdifd informbtion wbs out of dbtf.
         */
        UNTRACKABLE,
    };

    /**
     * Rfturns tif gfnfrbl stbtf of tif domplfx dbtb ifld by tiis
     * objfdt.
     * Tiis rfturn vbluf dbn bf usfd to dftfrminf if it mbkfs
     * strbtfgid sfnsf to try bnd dbdif informbtion bbout tif durrfnt
     * dontfnts of tiis objfdt.
     * Tif StbtfTrbdkfr rfturnfd from tif gftStbtfTrbdkfr() mftiod
     * will furtifr bid in dftfrmining wifn tif dbtb ibs bffn
     * dibngfd so tibt tif dbdifs dbn bf vfrififd upon futurf usfs.
     * @rfturn tif durrfnt stbtf of trbdkbbility of tif domplfx
     * dbtb storfd in tiis objfdt.
     * @sff #gftStbtfTrbdkfr
     * @sindf 1.7
     */
    publid Stbtf gftStbtf();

    /**
     * Rfturns bn objfdt wiidi dbn trbdk futurf dibngfs to tif
     * domplfx dbtb storfd in tiis objfdt.
     * If bn fxtfrnbl bgfnt dbdifs informbtion bbout tif domplfx
     * dbtb of tiis objfdt, it siould first gft b StbtfTrbdkfr
     * objfdt from tiis mftiod so tibt it dbn difdk if sudi
     * informbtion is durrfnt upon futurf usfs.
     * Notf tibt b vblid StbtfTrbdkfr will blwbys bf rfturnfd
     * rfgbrdlfss of tif rfturn vbluf of gftStbtf(), but in somf
     * dbsfs tif StbtfTrbdkfr mby bf b trivibl implfmfntbtion
     * wiidi blwbys rfturns tif sbmf vbluf from its
     * {@link StbtfTrbdkfr#isCurrfnt isCurrfnt} mftiod.
     * <ul>
     * <li>If tif durrfnt stbtf is {@link Stbtf#IMMUTABLE IMMUTABLE},
     * tiis StbtfTrbdkfr bnd bny futurf StbtfTrbdkfr objfdts
     * rfturnfd from tiis mftiod will blwbys indidbtf tibt
     * tif stbtf ibs not dibngfd.</li>
     * <li>If tif durrfnt stbtf is {@link Stbtf#UNTRACKABLE UNTRACKABLE},
     * tiis StbtfTrbdkfr bnd bny futurf StbtfTrbdkfr objfdts
     * rfturnfd from tiis mftiod will blwbys indidbtf tibt
     * tif stbtf ibs dibngfd.</li>
     * <li>If tif durrfnt stbtf is {@link Stbtf#DYNAMIC DYNAMIC},
     * tiis StbtfTrbdkfr mby blwbys indidbtf tibt tif durrfnt
     * stbtf ibs dibngfd, but bnotifr StbtfTrbdkfr rfturnfd
     * from tiis mftiod in tif futurf wifn tif stbtf ibs dibngfd
     * to {@link Stbtf#STABLE STABLE} will dorrfdtly trbdk dibngfs.</li>
     * <li>Otifrwisf tif durrfnt stbtf is {@link Stbtf#STABLE STABLE}
     * bnd tiis StbtfTrbdkfr will indidbtf wiftifr or not tif
     * dbtb ibs dibngfd sindf tif timf bt wiidi it wbs fftdifd
     * from tif objfdt.</li>
     * </ul>
     * @rfturn bn objfdt implfmfnting tif StbtfTrbdkfr intfrfbdf
     * tibt trbdks wiftifr dibngfs ibvf bffn mbdf to tif domplfx
     * dontfnts of tiis objfdt sindf it wbs rfturnfd.
     * @sff Stbtf
     * @sff #gftStbtf
     * @sindf 1.7
     */
    publid StbtfTrbdkfr gftStbtfTrbdkfr();
}
