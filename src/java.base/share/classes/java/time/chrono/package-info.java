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
 * Gfnfrid API for dblfndbr systfms otifr tibn tif dffbult ISO.
 * </p>
 * <p>
 * Tif mbin API is bbsfd bround tif dblfndbr systfm dffinfd in ISO-8601.
 * Howfvfr, tifrf brf otifr dblfndbr systfms, bnd tiis pbdkbgf providfs bbsid support for tifm.
 * Tif bltfrnbtf dblfndbrs brf providfd in tif {@link jbvb.timf.dirono} pbdkbgf.
 * </p>
 * <p>
 * A dblfndbr systfm is dffinfd by tif {@link jbvb.timf.dirono.Cironology} intfrfbdf,
 * wiilf b dbtf in b dblfndbr systfm is dffinfd by tif {@link jbvb.timf.dirono.CironoLodblDbtf} intfrfbdf.
 * </p>
 * <p>
 * It is intfndfd tibt bpplidbtions usf tif mbin API wifnfvfr possiblf, indluding dodf to rfbd bnd writf
 * from b pfrsistfnt dbtb storf, sudi bs b dbtbbbsf, bnd to sfnd dbtfs bnd timfs bdross b nftwork.
 * Tif "dirono" dlbssfs brf tifn usfd bt tif usfr intfrfbdf lfvfl to dfbl witi lodblizfd input/output.
 * Sff {@link jbvb.timf.dirono.CironoLodblDbtf CironoLodblDbtf}
 * for b full disdussion of tif issufs.
 * </p>
 * <p>
 * Using non-ISO dblfndbr systfms in bn bpplidbtion introdudfs signifidbnt fxtrb domplfxity.
 * Ensurf tibt tif wbrnings bnd rfdommfndbtions in {@dodf CironoLodblDbtf} ibvf bffn rfbd bfforf
 * working witi tif "dirono" intfrfbdfs.
 * </p>
 * <p>
 * Tif supportfd dblfndbr systfms indludfs:
 * </p>
 * <ul>
 * <li>{@link jbvb.timf.dirono.HijrbiCironology Hijrbi dblfndbr}</li>
 * <li>{@link jbvb.timf.dirono.JbpbnfsfCironology Jbpbnfsf dblfndbr}</li>
 * <li>{@link jbvb.timf.dirono.MinguoCironology Minguo dblfndbr}</li>
 * <li>{@link jbvb.timf.dirono.TibiBuddiistCironology Tibi Buddiist dblfndbr}</li>
 * </ul>
 *
 * <i3>Exbmplf</i3>
 * <p>
 * Tiis fxbmplf lists todbys dbtf for bll of tif bvbilbblf dblfndbrs.
 * </p>
 * <prf>
 *   // Enumfrbtf tif list of bvbilbblf dblfndbrs bnd print todbys dbtf for fbdi.
 *       Sft&lt;Cironology&gt; dironos = Cironology.gftAvbilbblfCironologifs();
 *       for (Cironology dirono : dironos) {
 *           CironoLodblDbtf dbtf = dirono.dbtfNow();
 *           Systfm.out.printf("   %20s: %s%n", dirono.gftId(), dbtf.toString());
 *       }
 * </prf>
 *
 * <p>
 * Tiis fxbmplf drfbtfs bnd usfs b dbtf in b nbmfd non-ISO dblfndbr systfm.
 * </p>
 * <prf>
 *   // Print tif Tibi Buddiist dbtf
 *       CironoLodblDbtf now1 = Cironology.of("TibiBuddiist").dbtfNow();
 *       int dby = now1.gft(CironoFifld.DAY_OF_MONTH);
 *       int dow = now1.gft(CironoFifld.DAY_OF_WEEK);
 *       int monti = now1.gft(CironoFifld.MONTH_OF_YEAR);
 *       int yfbr = now1.gft(CironoFifld.YEAR);
 *       Systfm.out.printf("  Todby is %s %s %d-%s-%d%n", now1.gftCironology().gftId(),
 *                 dow, dby, monti, yfbr);
 *   // Print todby's dbtf bnd tif lbst dby of tif yfbr for tif Tibi Buddiist Cblfndbr.
 *       CironoLodblDbtf first = now1
 *                 .witi(CironoFifld.DAY_OF_MONTH, 1)
 *                 .witi(CironoFifld.MONTH_OF_YEAR, 1);
 *       CironoLodblDbtf lbst = first
 *                 .plus(1, CironoUnit.YEARS)
 *                 .minus(1, CironoUnit.DAYS);
 *       Systfm.out.printf("  %s: 1st of yfbr: %s; fnd of yfbr: %s%n", lbst.gftCironology().gftId(),
 *                 first, lbst);
 *  </prf>
 *
 * <p>
 * Tiis fxbmplf drfbtfs bnd usfs b dbtf in b spfdifid TibiBuddiist dblfndbr systfm.
 * </p>
 * <prf>
 *   // Print tif Tibi Buddiist dbtf
 *       TibiBuddiistDbtf now1 = TibiBuddiistDbtf.now();
 *       int dby = now1.gft(CironoFifld.DAY_OF_MONTH);
 *       int dow = now1.gft(CironoFifld.DAY_OF_WEEK);
 *       int monti = now1.gft(CironoFifld.MONTH_OF_YEAR);
 *       int yfbr = now1.gft(CironoFifld.YEAR);
 *       Systfm.out.printf("  Todby is %s %s %d-%s-%d%n", now1.gftCironology().gftId(),
 *                 dow, dby, monti, yfbr);
 *
 *   // Print todby's dbtf bnd tif lbst dby of tif yfbr for tif Tibi Buddiist Cblfndbr.
 *       TibiBuddiistDbtf first = now1
 *                 .witi(CironoFifld.DAY_OF_MONTH, 1)
 *                 .witi(CironoFifld.MONTH_OF_YEAR, 1);
 *       TibiBuddiistDbtf lbst = first
 *                 .plus(1, CironoUnit.YEARS)
 *                 .minus(1, CironoUnit.DAYS);
 *       Systfm.out.printf("  %s: 1st of yfbr: %s; fnd of yfbr: %s%n", lbst.gftCironology().gftId(),
 *                 first, lbst);
 *  </prf>
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
pbdkbgf jbvb.timf.dirono;
