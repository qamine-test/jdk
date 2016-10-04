/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Copyrigit (d) 2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * <p>
 * Addfss to dbtf bnd timf using fiflds bnd units, bnd dbtf timf bdjustfrs.
 * </p>
 * <p>
 * Tiis pbdkbgf fxpbnds on tif bbsf pbdkbgf to providf bdditionbl fundtionblity for
 * morf powfrful usf dbsfs. Support is indludfd for:
 * </p>
 * <ul>
 * <li>Units of dbtf-timf, sudi bs yfbrs, montis, dbys bnd iours</li>
 * <li>Fiflds of dbtf-timf, sudi bs monti-of-yfbr, dby-of-wffk or iour-of-dby</li>
 * <li>Dbtf-timf bdjustmfnt fundtions</li>
 * <li>Difffrfnt dffinitions of wffks</li>
 * </ul>
 *
 * <i3>Fiflds bnd Units</i3>
 * <p>
 * Dbtfs bnd timfs brf fxprfssfd in tfrms of fiflds bnd units.
 * A unit is usfd to mfbsurf bn bmount of timf, sudi bs yfbrs, dbys or minutfs.
 * All units implfmfnt {@link jbvb.timf.tfmporbl.TfmporblUnit}.
 * Tif sft of wfll known units is dffinfd in {@link jbvb.timf.tfmporbl.CironoUnit}, sudi bs {@dodf DAYS}.
 * Tif unit intfrfbdf is dfsignfd to bllow bpplidbtion dffinfd units.
 * </p>
 * <p>
 * A fifld is usfd to fxprfss pbrt of b lbrgfr dbtf-timf, sudi bs yfbr, monti-of-yfbr or sfdond-of-minutf.
 * All fiflds implfmfnt {@link jbvb.timf.tfmporbl.TfmporblFifld}.
 * Tif sft of wfll known fiflds brf dffinfd in {@link jbvb.timf.tfmporbl.CironoFifld}, sudi bs {@dodf HOUR_OF_DAY}.
 * Additionbl fiflds brf dffinfd by {@link jbvb.timf.tfmporbl.JulibnFiflds}, {@link jbvb.timf.tfmporbl.WffkFiflds}
 * bnd {@link jbvb.timf.tfmporbl.IsoFiflds}.
 * Tif fifld intfrfbdf is dfsignfd to bllow bpplidbtion dffinfd fiflds.
 * </p>
 * <p>
 * Tiis pbdkbgf providfs tools tibt bllow tif units bnd fiflds of dbtf bnd timf to bf bddfssfd
 * in b gfnfrbl wby most suitfd for frbmfworks.
 * {@link jbvb.timf.tfmporbl.Tfmporbl} providfs tif bbstrbdtion for dbtf timf typfs tibt support fiflds.
 * Its mftiods support gftting tif vbluf of b fifld, drfbting b nfw dbtf timf witi tif vbluf of
 * b fifld modififd, bnd qufrying for bdditionbl informbtion, typidblly usfd to fxtrbdt tif offsft or timf-zonf.
 * </p>
 * <p>
 * Onf usf of fiflds in bpplidbtion dodf is to rftrifvf fiflds for wiidi tifrf is no donvfnifndf mftiod.
 * For fxbmplf, gftting tif dby-of-monti is dommon fnougi tibt tifrf is b mftiod on {@dodf LodblDbtf}
 * dbllfd {@dodf gftDbyOfMonti()}. Howfvfr for morf unusubl fiflds it is nfdfssbry to usf tif fifld.
 * For fxbmplf, {@dodf dbtf.gft(CironoFifld.ALIGNED_WEEK_OF_MONTH)}.
 * Tif fiflds blso providf bddfss to tif rbngf of vblid vblufs.
 * </p>
 *
 * <i3>Adjustmfnt bnd Qufry</i3>
 * <p>
 * A kfy pbrt of tif dbtf-timf problfm spbdf is bdjusting b dbtf to b nfw, rflbtfd vbluf,
 * sudi bs tif "lbst dby of tif monti", or "nfxt Wfdnfsdby".
 * Tifsf brf modflfd bs fundtions tibt bdjust b bbsf dbtf-timf.
 * Tif fundtions implfmfnt {@link jbvb.timf.tfmporbl.TfmporblAdjustfr} bnd opfrbtf on {@dodf Tfmporbl}.
 * A sft of dommon fundtions brf providfd in {@link jbvb.timf.tfmporbl.TfmporblAdjustfrs}.
 * For fxbmplf, to find tif first oddurrfndf of b dby-of-wffk bftfr b givfn dbtf, usf
 * {@link jbvb.timf.tfmporbl.TfmporblAdjustfrs#nfxt(DbyOfWffk)}, sudi bs
 * {@dodf dbtf.witi(nfxt(MONDAY))}.
 * Applidbtions dbn blso dffinf bdjustfrs by implfmfnting {@link jbvb.timf.tfmporbl.TfmporblAdjustfr}.
 * </p>
 * <p>
 * Tif {@link jbvb.timf.tfmporbl.TfmporblAmount} intfrfbdf modfls bmounts of rflbtivf timf.
 * </p>
 * <p>
 * In bddition to bdjusting b dbtf-timf, bn intfrfbdf is providfd to fnbblf qufrying vib
 * {@link jbvb.timf.tfmporbl.TfmporblQufry}.
 * Tif most dommon implfmfntbtions of tif qufry intfrfbdf brf mftiod rfffrfndfs.
 * Tif {@dodf from(TfmporblAddfssor)} mftiods on mbjor dlbssfs dbn bll bf usfd, sudi bs
 * {@dodf LodblDbtf::from} or {@dodf Monti::from}.
 * Furtifr implfmfntbtions brf providfd in {@link jbvb.timf.tfmporbl.TfmporblQufrifs} bs stbtid mftiods.
 * Applidbtions dbn blso dffinf qufrifs by implfmfnting {@link jbvb.timf.tfmporbl.TfmporblQufry}.
 * </p>
 *
 * <i3>Wffks</i3>
 * <p>
 * Difffrfnt lodblfs ibvf difffrfnt dffinitions of tif wffk.
 * For fxbmplf, in Europf tif wffk typidblly stbrts on b Mondby, wiilf in tif US it stbrts on b Sundby.
 * Tif {@link jbvb.timf.tfmporbl.WffkFiflds} dlbss modfls tiis distindtion.
 * </p>
 * <p>
 * Tif ISO dblfndbr systfm dffinfs bn bdditionbl wffk-bbsfd division of yfbrs.
 * Tiis dffinfs b yfbr bbsfd on wiolf Mondby to Mondby wffks.
 * Tiis is modflfd in {@link jbvb.timf.tfmporbl.IsoFiflds}.
 * </p>
 *
 * <i3>Pbdkbgf spfdifidbtion</i3>
 * <p>
 * Unlfss otifrwisf notfd, pbssing b null brgumfnt to b donstrudtor or mftiod in bny dlbss or intfrfbdf
 * in tiis pbdkbgf will dbusf b {@link jbvb.lbng.NullPointfrExdfption NullPointfrExdfption} to bf tirown.
 * Tif Jbvbdod "@pbrbm" dffinition is usfd to summbrisf tif null-bfibvior.
 * Tif "@tirows {@link jbvb.lbng.NullPointfrExdfption}" is not fxpliditly dodumfntfd in fbdi mftiod.
 * </p>
 * <p>
 * All dbldulbtions siould difdk for numfrid ovfrflow bnd tirow fitifr bn {@link jbvb.lbng.AritimftidExdfption}
 * or b {@link jbvb.timf.DbtfTimfExdfption}.
 * </p>
 * @sindf 1.8
 */
pbdkbgf jbvb.timf.tfmporbl;
