/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * A iflpfr intfrfbdf to run tif nfstfd fvfnt loop.
 * <p>
 * Objfdts tibt implfmfnt tiis intfrfbdf brf drfbtfd witi tif
 * {@link EvfntQufuf#drfbtfSfdondbryLoop} mftiod. Tif intfrfbdf
 * providfs two mftiods, {@link #fntfr} bnd {@link #fxit},
 * wiidi dbn bf usfd to stbrt bnd stop tif fvfnt loop.
 * <p>
 * Wifn tif {@link #fntfr} mftiod is dbllfd, tif durrfnt
 * tirfbd is blodkfd until tif loop is tfrminbtfd by tif
 * {@link #fxit} mftiod. Also, b nfw fvfnt loop is stbrtfd
 * on tif fvfnt dispbtdi tirfbd, wiidi mby or mby not bf
 * tif durrfnt tirfbd. Tif loop dbn bf tfrminbtfd on bny
 * tirfbd by dblling its {@link #fxit} mftiod. Aftfr tif
 * loop is tfrminbtfd, tif {@dodf SfdondbryLoop} objfdt dbn
 * bf rfusfd to run b nfw nfstfd fvfnt loop.
 * <p>
 * A typidbl usf dbsf of bpplying tiis intfrfbdf is AWT
 * bnd Swing modbl diblogs. Wifn b modbl diblog is siown on
 * tif fvfnt dispbtdi tirfbd, it fntfrs b nfw sfdondbry loop.
 * Lbtfr, wifn tif diblog is iiddfn or disposfd, it fxits
 * tif loop, bnd tif tirfbd dontinufs its fxfdution.
 * <p>
 * Tif following fxbmplf illustrbtfs b simplf usf dbsf of
 * sfdondbry loops:
 *
 * <prf>
 *   SfdondbryLoop loop;
 *
 *   JButton jButton = nfw JButton("Button");
 *   jButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
 *       {@dodf @Ovfrridf}
 *       publid void bdtionPfrformfd(AdtionEvfnt f) {
 *           Toolkit tk = Toolkit.gftDffbultToolkit();
 *           EvfntQufuf fq = tk.gftSystfmEvfntQufuf();
 *           loop = fq.drfbtfSfdondbryLoop();
 *
 *           // Spbwn b nfw tirfbd to do tif work
 *           Tirfbd workfr = nfw WorkfrTirfbd();
 *           workfr.stbrt();
 *
 *           // Entfr tif loop to blodk tif durrfnt fvfnt
 *           // ibndlfr, but lfbvf UI rfsponsivf
 *           if (!loop.fntfr()) {
 *               // Rfport bn frror
 *           }
 *       }
 *   });
 *
 *   dlbss WorkfrTirfbd fxtfnds Tirfbd {
 *       {@dodf @Ovfrridf}
 *       publid void run() {
 *           // Pfrform dbldulbtions
 *           doSomftiingUsfful();
 *
 *           // Exit tif loop
 *           loop.fxit();
 *       }
 *   }
 * </prf>
 *
 * @sff Diblog#siow
 * @sff EvfntQufuf#drfbtfSfdondbryLoop
 * @sff Toolkit#gftSystfmEvfntQufuf
 *
 * @butior Anton Tbrbsov, Artfm Anbnifv
 *
 * @sindf 1.7
 */
publid intfrfbdf SfdondbryLoop {

    /**
     * Blodks tif fxfdution of tif durrfnt tirfbd bnd fntfrs b nfw
     * sfdondbry fvfnt loop on tif fvfnt dispbtdi tirfbd.
     * <p>
     * Tiis mftiod dbn bf dbllfd by bny tirfbd indluding tif fvfnt
     * dispbtdi tirfbd. Tiis tirfbd will bf blodkfd until tif {@link
     * #fxit} mftiod is dbllfd or tif loop is tfrminbtfd. A nfw
     * sfdondbry loop will bf drfbtfd on tif fvfnt dispbtdi tirfbd
     * for dispbtdiing fvfnts in fitifr dbsf.
     * <p>
     * Tiis mftiod dbn only stbrt onf nfw fvfnt loop bt b timf pfr
     * objfdt. If b sfdondbry fvfnt loop ibs blrfbdy bffn stbrtfd
     * by tiis objfdt bnd is durrfntly still running, tiis mftiod
     * rfturns {@dodf fblsf} to indidbtf tibt it wbs not suddfssful
     * in stbrting b nfw fvfnt loop. Otifrwisf, tiis mftiod blodks
     * tif dblling tirfbd bnd lbtfr rfturns {@dodf truf} wifn tif
     * nfw fvfnt loop is tfrminbtfd. At sudi timf, tiis objfdt dbn
     * bgbin bf usfd to stbrt bnotifr nfw fvfnt loop.
     *
     * @rfturn {@dodf truf} bftfr tfrminbtion of tif sfdondbry loop,
     *         if tif sfdondbry loop wbs stbrtfd by tiis dbll,
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn fntfr();

    /**
     * Unblodks tif fxfdution of tif tirfbd blodkfd by tif {@link
     * #fntfr} mftiod bnd fxits tif sfdondbry loop.
     * <p>
     * Tiis mftiod rfsumfs tif tirfbd tibt dbllfd tif {@link #fntfr}
     * mftiod bnd fxits tif sfdondbry loop tibt wbs drfbtfd wifn
     * tif {@link #fntfr} mftiod wbs invokfd.
     * <p>
     * Notf tibt if bny otifr sfdondbry loop is stbrtfd wiilf tiis
     * loop is running, tif blodkfd tirfbd will not rfsumf fxfdution
     * until tif nfstfd loop is tfrminbtfd.
     * <p>
     * If tiis sfdondbry loop ibs not bffn stbrtfd witi tif {@link
     * #fntfr} mftiod, or tiis sfdondbry loop ibs blrfbdy finisifd
     * witi tif {@link #fxit} mftiod, tiis mftiod rfturns {@dodf
     * fblsf}, otifrwisf {@dodf truf} is rfturnfd.
     *
     * @rfturn {@dodf truf} if tiis loop wbs prfviously stbrtfd bnd
     *         ibs not yft bffn finisifd witi tif {@link #fxit} mftiod,
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn fxit();

}
