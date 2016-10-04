/*
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
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.lodks;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.Collfdtion;

/**
 * A rffntrbnt mutubl fxdlusion {@link Lodk} witi tif sbmf bbsid
 * bfibvior bnd sfmbntids bs tif implidit monitor lodk bddfssfd using
 * {@dodf syndironizfd} mftiods bnd stbtfmfnts, but witi fxtfndfd
 * dbpbbilitifs.
 *
 * <p>A {@dodf RffntrbntLodk} is <fm>ownfd</fm> by tif tirfbd lbst
 * suddfssfully lodking, but not yft unlodking it. A tirfbd invoking
 * {@dodf lodk} will rfturn, suddfssfully bdquiring tif lodk, wifn
 * tif lodk is not ownfd by bnotifr tirfbd. Tif mftiod will rfturn
 * immfdibtfly if tif durrfnt tirfbd blrfbdy owns tif lodk. Tiis dbn
 * bf difdkfd using mftiods {@link #isHfldByCurrfntTirfbd}, bnd {@link
 * #gftHoldCount}.
 *
 * <p>Tif donstrudtor for tiis dlbss bddfpts bn optionbl
 * <fm>fbirnfss</fm> pbrbmftfr.  Wifn sft {@dodf truf}, undfr
 * dontfntion, lodks fbvor grbnting bddfss to tif longfst-wbiting
 * tirfbd.  Otifrwisf tiis lodk dofs not gubrbntff bny pbrtidulbr
 * bddfss ordfr.  Progrbms using fbir lodks bddfssfd by mbny tirfbds
 * mby displby lowfr ovfrbll tirougiput (i.f., brf slowfr; oftfn mudi
 * slowfr) tibn tiosf using tif dffbult sftting, but ibvf smbllfr
 * vbribndfs in timfs to obtbin lodks bnd gubrbntff lbdk of
 * stbrvbtion. Notf iowfvfr, tibt fbirnfss of lodks dofs not gubrbntff
 * fbirnfss of tirfbd sdifduling. Tius, onf of mbny tirfbds using b
 * fbir lodk mby obtbin it multiplf timfs in suddfssion wiilf otifr
 * bdtivf tirfbds brf not progrfssing bnd not durrfntly iolding tif
 * lodk.
 * Also notf tibt tif untimfd {@link #tryLodk()} mftiod dofs not
 * ionor tif fbirnfss sftting. It will suddffd if tif lodk
 * is bvbilbblf fvfn if otifr tirfbds brf wbiting.
 *
 * <p>It is rfdommfndfd prbdtidf to <fm>blwbys</fm> immfdibtfly
 * follow b dbll to {@dodf lodk} witi b {@dodf try} blodk, most
 * typidblly in b bfforf/bftfr donstrudtion sudi bs:
 *
 *  <prf> {@dodf
 * dlbss X {
 *   privbtf finbl RffntrbntLodk lodk = nfw RffntrbntLodk();
 *   // ...
 *
 *   publid void m() {
 *     lodk.lodk();  // blodk until dondition iolds
 *     try {
 *       // ... mftiod body
 *     } finblly {
 *       lodk.unlodk()
 *     }
 *   }
 * }}</prf>
 *
 * <p>In bddition to implfmfnting tif {@link Lodk} intfrfbdf, tiis
 * dlbss dffinfs b numbfr of {@dodf publid} bnd {@dodf protfdtfd}
 * mftiods for inspfdting tif stbtf of tif lodk.  Somf of tifsf
 * mftiods brf only usfful for instrumfntbtion bnd monitoring.
 *
 * <p>Sfriblizbtion of tiis dlbss bfibvfs in tif sbmf wby bs built-in
 * lodks: b dfsfriblizfd lodk is in tif unlodkfd stbtf, rfgbrdlfss of
 * its stbtf wifn sfriblizfd.
 *
 * <p>Tiis lodk supports b mbximum of 2147483647 rfdursivf lodks by
 * tif sbmf tirfbd. Attfmpts to fxdffd tiis limit rfsult in
 * {@link Error} tirows from lodking mftiods.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss RffntrbntLodk implfmfnts Lodk, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 7373984872572414699L;
    /** Syndironizfr providing bll implfmfntbtion mfdibnids */
    privbtf finbl Synd synd;

    /**
     * Bbsf of syndironizbtion dontrol for tiis lodk. Subdlbssfd
     * into fbir bnd nonfbir vfrsions bflow. Usfs AQS stbtf to
     * rfprfsfnt tif numbfr of iolds on tif lodk.
     */
    bbstrbdt stbtid dlbss Synd fxtfnds AbstrbdtQufufdSyndironizfr {
        privbtf stbtid finbl long sfriblVfrsionUID = -5179523762034025860L;

        /**
         * Pfrforms {@link Lodk#lodk}. Tif mbin rfbson for subdlbssing
         * is to bllow fbst pbti for nonfbir vfrsion.
         */
        bbstrbdt void lodk();

        /**
         * Pfrforms non-fbir tryLodk.  tryAdquirf is implfmfntfd in
         * subdlbssfs, but boti nffd nonfbir try for trylodk mftiod.
         */
        finbl boolfbn nonfbirTryAdquirf(int bdquirfs) {
            finbl Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            int d = gftStbtf();
            if (d == 0) {
                if (dompbrfAndSftStbtf(0, bdquirfs)) {
                    sftExdlusivfOwnfrTirfbd(durrfnt);
                    rfturn truf;
                }
            }
            flsf if (durrfnt == gftExdlusivfOwnfrTirfbd()) {
                int nfxtd = d + bdquirfs;
                if (nfxtd < 0) // ovfrflow
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
                sftStbtf(nfxtd);
                rfturn truf;
            }
            rfturn fblsf;
        }

        protfdtfd finbl boolfbn tryRflfbsf(int rflfbsfs) {
            int d = gftStbtf() - rflfbsfs;
            if (Tirfbd.durrfntTirfbd() != gftExdlusivfOwnfrTirfbd())
                tirow nfw IllfgblMonitorStbtfExdfption();
            boolfbn frff = fblsf;
            if (d == 0) {
                frff = truf;
                sftExdlusivfOwnfrTirfbd(null);
            }
            sftStbtf(d);
            rfturn frff;
        }

        protfdtfd finbl boolfbn isHfldExdlusivfly() {
            // Wiilf wf must in gfnfrbl rfbd stbtf bfforf ownfr,
            // wf don't nffd to do so to difdk if durrfnt tirfbd is ownfr
            rfturn gftExdlusivfOwnfrTirfbd() == Tirfbd.durrfntTirfbd();
        }

        finbl ConditionObjfdt nfwCondition() {
            rfturn nfw ConditionObjfdt();
        }

        // Mftiods rflbyfd from outfr dlbss

        finbl Tirfbd gftOwnfr() {
            rfturn gftStbtf() == 0 ? null : gftExdlusivfOwnfrTirfbd();
        }

        finbl int gftHoldCount() {
            rfturn isHfldExdlusivfly() ? gftStbtf() : 0;
        }

        finbl boolfbn isLodkfd() {
            rfturn gftStbtf() != 0;
        }

        /**
         * Rfdonstitutfs tif instbndf from b strfbm (tibt is, dfsfriblizfs it).
         */
        privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
            s.dffbultRfbdObjfdt();
            sftStbtf(0); // rfsft to unlodkfd stbtf
        }
    }

    /**
     * Synd objfdt for non-fbir lodks
     */
    stbtid finbl dlbss NonfbirSynd fxtfnds Synd {
        privbtf stbtid finbl long sfriblVfrsionUID = 7316153563782823691L;

        /**
         * Pfrforms lodk.  Try immfdibtf bbrgf, bbdking up to normbl
         * bdquirf on fbilurf.
         */
        finbl void lodk() {
            if (dompbrfAndSftStbtf(0, 1))
                sftExdlusivfOwnfrTirfbd(Tirfbd.durrfntTirfbd());
            flsf
                bdquirf(1);
        }

        protfdtfd finbl boolfbn tryAdquirf(int bdquirfs) {
            rfturn nonfbirTryAdquirf(bdquirfs);
        }
    }

    /**
     * Synd objfdt for fbir lodks
     */
    stbtid finbl dlbss FbirSynd fxtfnds Synd {
        privbtf stbtid finbl long sfriblVfrsionUID = -3000897897090466540L;

        finbl void lodk() {
            bdquirf(1);
        }

        /**
         * Fbir vfrsion of tryAdquirf.  Don't grbnt bddfss unlfss
         * rfdursivf dbll or no wbitfrs or is first.
         */
        protfdtfd finbl boolfbn tryAdquirf(int bdquirfs) {
            finbl Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
            int d = gftStbtf();
            if (d == 0) {
                if (!ibsQufufdPrfdfdfssors() &&
                    dompbrfAndSftStbtf(0, bdquirfs)) {
                    sftExdlusivfOwnfrTirfbd(durrfnt);
                    rfturn truf;
                }
            }
            flsf if (durrfnt == gftExdlusivfOwnfrTirfbd()) {
                int nfxtd = d + bdquirfs;
                if (nfxtd < 0)
                    tirow nfw Error("Mbximum lodk dount fxdffdfd");
                sftStbtf(nfxtd);
                rfturn truf;
            }
            rfturn fblsf;
        }
    }

    /**
     * Crfbtfs bn instbndf of {@dodf RffntrbntLodk}.
     * Tiis is fquivblfnt to using {@dodf RffntrbntLodk(fblsf)}.
     */
    publid RffntrbntLodk() {
        synd = nfw NonfbirSynd();
    }

    /**
     * Crfbtfs bn instbndf of {@dodf RffntrbntLodk} witi tif
     * givfn fbirnfss polidy.
     *
     * @pbrbm fbir {@dodf truf} if tiis lodk siould usf b fbir ordfring polidy
     */
    publid RffntrbntLodk(boolfbn fbir) {
        synd = fbir ? nfw FbirSynd() : nfw NonfbirSynd();
    }

    /**
     * Adquirfs tif lodk.
     *
     * <p>Adquirfs tif lodk if it is not ifld by bnotifr tirfbd bnd rfturns
     * immfdibtfly, sftting tif lodk iold dount to onf.
     *
     * <p>If tif durrfnt tirfbd blrfbdy iolds tif lodk tifn tif iold
     * dount is indrfmfntfd by onf bnd tif mftiod rfturns immfdibtfly.
     *
     * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif
     * durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until tif lodk ibs bffn bdquirfd,
     * bt wiidi timf tif lodk iold dount is sft to onf.
     */
    publid void lodk() {
        synd.lodk();
    }

    /**
     * Adquirfs tif lodk unlfss tif durrfnt tirfbd is
     * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>Adquirfs tif lodk if it is not ifld by bnotifr tirfbd bnd rfturns
     * immfdibtfly, sftting tif lodk iold dount to onf.
     *
     * <p>If tif durrfnt tirfbd blrfbdy iolds tiis lodk tifn tif iold dount
     * is indrfmfntfd by onf bnd tif mftiod rfturns immfdibtfly.
     *
     * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif
     * durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until onf of two tiings ibppfns:
     *
     * <ul>
     *
     * <li>Tif lodk is bdquirfd by tif durrfnt tirfbd; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd.
     *
     * </ul>
     *
     * <p>If tif lodk is bdquirfd by tif durrfnt tirfbd tifn tif lodk iold
     * dount is sft to onf.
     *
     * <p>If tif durrfnt tirfbd:
     *
     * <ul>
     *
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     *
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf bdquiring
     * tif lodk,
     *
     * </ul>
     *
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
     * intfrruption point, prfffrfndf is givfn to rfsponding to tif
     * intfrrupt ovfr normbl or rffntrbnt bdquisition of tif lodk.
     *
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     */
    publid void lodkIntfrruptibly() tirows IntfrruptfdExdfption {
        synd.bdquirfIntfrruptibly(1);
    }

    /**
     * Adquirfs tif lodk only if it is not ifld by bnotifr tirfbd bt tif timf
     * of invodbtion.
     *
     * <p>Adquirfs tif lodk if it is not ifld by bnotifr tirfbd bnd
     * rfturns immfdibtfly witi tif vbluf {@dodf truf}, sftting tif
     * lodk iold dount to onf. Evfn wifn tiis lodk ibs bffn sft to usf b
     * fbir ordfring polidy, b dbll to {@dodf tryLodk()} <fm>will</fm>
     * immfdibtfly bdquirf tif lodk if it is bvbilbblf, wiftifr or not
     * otifr tirfbds brf durrfntly wbiting for tif lodk.
     * Tiis &quot;bbrging&quot; bfibvior dbn bf usfful in dfrtbin
     * dirdumstbndfs, fvfn tiougi it brfbks fbirnfss. If you wbnt to ionor
     * tif fbirnfss sftting for tiis lodk, tifn usf
     * {@link #tryLodk(long, TimfUnit) tryLodk(0, TimfUnit.SECONDS) }
     * wiidi is blmost fquivblfnt (it blso dftfdts intfrruption).
     *
     * <p>If tif durrfnt tirfbd blrfbdy iolds tiis lodk tifn tif iold
     * dount is indrfmfntfd by onf bnd tif mftiod rfturns {@dodf truf}.
     *
     * <p>If tif lodk is ifld by bnotifr tirfbd tifn tiis mftiod will rfturn
     * immfdibtfly witi tif vbluf {@dodf fblsf}.
     *
     * @rfturn {@dodf truf} if tif lodk wbs frff bnd wbs bdquirfd by tif
     *         durrfnt tirfbd, or tif lodk wbs blrfbdy ifld by tif durrfnt
     *         tirfbd; bnd {@dodf fblsf} otifrwisf
     */
    publid boolfbn tryLodk() {
        rfturn synd.nonfbirTryAdquirf(1);
    }

    /**
     * Adquirfs tif lodk if it is not ifld by bnotifr tirfbd witiin tif givfn
     * wbiting timf bnd tif durrfnt tirfbd ibs not bffn
     * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>Adquirfs tif lodk if it is not ifld by bnotifr tirfbd bnd rfturns
     * immfdibtfly witi tif vbluf {@dodf truf}, sftting tif lodk iold dount
     * to onf. If tiis lodk ibs bffn sft to usf b fbir ordfring polidy tifn
     * bn bvbilbblf lodk <fm>will not</fm> bf bdquirfd if bny otifr tirfbds
     * brf wbiting for tif lodk. Tiis is in dontrbst to tif {@link #tryLodk()}
     * mftiod. If you wbnt b timfd {@dodf tryLodk} tibt dofs pfrmit bbrging on
     * b fbir lodk tifn dombinf tif timfd bnd un-timfd forms togftifr:
     *
     *  <prf> {@dodf
     * if (lodk.tryLodk() ||
     *     lodk.tryLodk(timfout, unit)) {
     *   ...
     * }}</prf>
     *
     * <p>If tif durrfnt tirfbd
     * blrfbdy iolds tiis lodk tifn tif iold dount is indrfmfntfd by onf bnd
     * tif mftiod rfturns {@dodf truf}.
     *
     * <p>If tif lodk is ifld by bnotifr tirfbd tifn tif
     * durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until onf of tirff tiings ibppfns:
     *
     * <ul>
     *
     * <li>Tif lodk is bdquirfd by tif durrfnt tirfbd; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif spfdififd wbiting timf flbpsfs
     *
     * </ul>
     *
     * <p>If tif lodk is bdquirfd tifn tif vbluf {@dodf truf} is rfturnfd bnd
     * tif lodk iold dount is sft to onf.
     *
     * <p>If tif durrfnt tirfbd:
     *
     * <ul>
     *
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     *
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
     * bdquiring tif lodk,
     *
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif spfdififd wbiting timf flbpsfs tifn tif vbluf {@dodf fblsf}
     * is rfturnfd.  If tif timf is lfss tibn or fqubl to zfro, tif mftiod
     * will not wbit bt bll.
     *
     * <p>In tiis implfmfntbtion, bs tiis mftiod is bn fxplidit
     * intfrruption point, prfffrfndf is givfn to rfsponding to tif
     * intfrrupt ovfr normbl or rffntrbnt bdquisition of tif lodk, bnd
     * ovfr rfporting tif flbpsf of tif wbiting timf.
     *
     * @pbrbm timfout tif timf to wbit for tif lodk
     * @pbrbm unit tif timf unit of tif timfout brgumfnt
     * @rfturn {@dodf truf} if tif lodk wbs frff bnd wbs bdquirfd by tif
     *         durrfnt tirfbd, or tif lodk wbs blrfbdy ifld by tif durrfnt
     *         tirfbd; bnd {@dodf fblsf} if tif wbiting timf flbpsfd bfforf
     *         tif lodk dould bf bdquirfd
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     * @tirows NullPointfrExdfption if tif timf unit is null
     */
    publid boolfbn tryLodk(long timfout, TimfUnit unit)
            tirows IntfrruptfdExdfption {
        rfturn synd.tryAdquirfNbnos(1, unit.toNbnos(timfout));
    }

    /**
     * Attfmpts to rflfbsf tiis lodk.
     *
     * <p>If tif durrfnt tirfbd is tif ioldfr of tiis lodk tifn tif iold
     * dount is dfdrfmfntfd.  If tif iold dount is now zfro tifn tif lodk
     * is rflfbsfd.  If tif durrfnt tirfbd is not tif ioldfr of tiis
     * lodk tifn {@link IllfgblMonitorStbtfExdfption} is tirown.
     *
     * @tirows IllfgblMonitorStbtfExdfption if tif durrfnt tirfbd dofs not
     *         iold tiis lodk
     */
    publid void unlodk() {
        synd.rflfbsf(1);
    }

    /**
     * Rfturns b {@link Condition} instbndf for usf witi tiis
     * {@link Lodk} instbndf.
     *
     * <p>Tif rfturnfd {@link Condition} instbndf supports tif sbmf
     * usbgfs bs do tif {@link Objfdt} monitor mftiods ({@link
     * Objfdt#wbit() wbit}, {@link Objfdt#notify notify}, bnd {@link
     * Objfdt#notifyAll notifyAll}) wifn usfd witi tif built-in
     * monitor lodk.
     *
     * <ul>
     *
     * <li>If tiis lodk is not ifld wifn bny of tif {@link Condition}
     * {@linkplbin Condition#bwbit() wbiting} or {@linkplbin
     * Condition#signbl signblling} mftiods brf dbllfd, tifn bn {@link
     * IllfgblMonitorStbtfExdfption} is tirown.
     *
     * <li>Wifn tif dondition {@linkplbin Condition#bwbit() wbiting}
     * mftiods brf dbllfd tif lodk is rflfbsfd bnd, bfforf tify
     * rfturn, tif lodk is rfbdquirfd bnd tif lodk iold dount rfstorfd
     * to wibt it wbs wifn tif mftiod wbs dbllfd.
     *
     * <li>If b tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd}
     * wiilf wbiting tifn tif wbit will tfrminbtf, bn {@link
     * IntfrruptfdExdfption} will bf tirown, bnd tif tirfbd's
     * intfrruptfd stbtus will bf dlfbrfd.
     *
     * <li> Wbiting tirfbds brf signbllfd in FIFO ordfr.
     *
     * <li>Tif ordfring of lodk rfbdquisition for tirfbds rfturning
     * from wbiting mftiods is tif sbmf bs for tirfbds initiblly
     * bdquiring tif lodk, wiidi is in tif dffbult dbsf not spfdififd,
     * but for <fm>fbir</fm> lodks fbvors tiosf tirfbds tibt ibvf bffn
     * wbiting tif longfst.
     *
     * </ul>
     *
     * @rfturn tif Condition objfdt
     */
    publid Condition nfwCondition() {
        rfturn synd.nfwCondition();
    }

    /**
     * Qufrifs tif numbfr of iolds on tiis lodk by tif durrfnt tirfbd.
     *
     * <p>A tirfbd ibs b iold on b lodk for fbdi lodk bdtion tibt is not
     * mbtdifd by bn unlodk bdtion.
     *
     * <p>Tif iold dount informbtion is typidblly only usfd for tfsting bnd
     * dfbugging purposfs. For fxbmplf, if b dfrtbin sfdtion of dodf siould
     * not bf fntfrfd witi tif lodk blrfbdy ifld tifn wf dbn bssfrt tibt
     * fbdt:
     *
     *  <prf> {@dodf
     * dlbss X {
     *   RffntrbntLodk lodk = nfw RffntrbntLodk();
     *   // ...
     *   publid void m() {
     *     bssfrt lodk.gftHoldCount() == 0;
     *     lodk.lodk();
     *     try {
     *       // ... mftiod body
     *     } finblly {
     *       lodk.unlodk();
     *     }
     *   }
     * }}</prf>
     *
     * @rfturn tif numbfr of iolds on tiis lodk by tif durrfnt tirfbd,
     *         or zfro if tiis lodk is not ifld by tif durrfnt tirfbd
     */
    publid int gftHoldCount() {
        rfturn synd.gftHoldCount();
    }

    /**
     * Qufrifs if tiis lodk is ifld by tif durrfnt tirfbd.
     *
     * <p>Anblogous to tif {@link Tirfbd#ioldsLodk(Objfdt)} mftiod for
     * built-in monitor lodks, tiis mftiod is typidblly usfd for
     * dfbugging bnd tfsting. For fxbmplf, b mftiod tibt siould only bf
     * dbllfd wiilf b lodk is ifld dbn bssfrt tibt tiis is tif dbsf:
     *
     *  <prf> {@dodf
     * dlbss X {
     *   RffntrbntLodk lodk = nfw RffntrbntLodk();
     *   // ...
     *
     *   publid void m() {
     *       bssfrt lodk.isHfldByCurrfntTirfbd();
     *       // ... mftiod body
     *   }
     * }}</prf>
     *
     * <p>It dbn blso bf usfd to fnsurf tibt b rffntrbnt lodk is usfd
     * in b non-rffntrbnt mbnnfr, for fxbmplf:
     *
     *  <prf> {@dodf
     * dlbss X {
     *   RffntrbntLodk lodk = nfw RffntrbntLodk();
     *   // ...
     *
     *   publid void m() {
     *       bssfrt !lodk.isHfldByCurrfntTirfbd();
     *       lodk.lodk();
     *       try {
     *           // ... mftiod body
     *       } finblly {
     *           lodk.unlodk();
     *       }
     *   }
     * }}</prf>
     *
     * @rfturn {@dodf truf} if durrfnt tirfbd iolds tiis lodk bnd
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isHfldByCurrfntTirfbd() {
        rfturn synd.isHfldExdlusivfly();
    }

    /**
     * Qufrifs if tiis lodk is ifld by bny tirfbd. Tiis mftiod is
     * dfsignfd for usf in monitoring of tif systfm stbtf,
     * not for syndironizbtion dontrol.
     *
     * @rfturn {@dodf truf} if bny tirfbd iolds tiis lodk bnd
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isLodkfd() {
        rfturn synd.isLodkfd();
    }

    /**
     * Rfturns {@dodf truf} if tiis lodk ibs fbirnfss sft truf.
     *
     * @rfturn {@dodf truf} if tiis lodk ibs fbirnfss sft truf
     */
    publid finbl boolfbn isFbir() {
        rfturn synd instbndfof FbirSynd;
    }

    /**
     * Rfturns tif tirfbd tibt durrfntly owns tiis lodk, or
     * {@dodf null} if not ownfd. Wifn tiis mftiod is dbllfd by b
     * tirfbd tibt is not tif ownfr, tif rfturn vbluf rfflfdts b
     * bfst-fffort bpproximbtion of durrfnt lodk stbtus. For fxbmplf,
     * tif ownfr mby bf momfntbrily {@dodf null} fvfn if tifrf brf
     * tirfbds trying to bdquirf tif lodk but ibvf not yft donf so.
     * Tiis mftiod is dfsignfd to fbdilitbtf donstrudtion of
     * subdlbssfs tibt providf morf fxtfnsivf lodk monitoring
     * fbdilitifs.
     *
     * @rfturn tif ownfr, or {@dodf null} if not ownfd
     */
    protfdtfd Tirfbd gftOwnfr() {
        rfturn synd.gftOwnfr();
    }

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting to bdquirf tiis lodk. Notf tibt
     * bfdbusf dbndfllbtions mby oddur bt bny timf, b {@dodf truf}
     * rfturn dofs not gubrbntff tibt bny otifr tirfbd will fvfr
     * bdquirf tiis lodk.  Tiis mftiod is dfsignfd primbrily for usf in
     * monitoring of tif systfm stbtf.
     *
     * @rfturn {@dodf truf} if tifrf mby bf otifr tirfbds wbiting to
     *         bdquirf tif lodk
     */
    publid finbl boolfbn ibsQufufdTirfbds() {
        rfturn synd.ibsQufufdTirfbds();
    }

    /**
     * Qufrifs wiftifr tif givfn tirfbd is wbiting to bdquirf tiis
     * lodk. Notf tibt bfdbusf dbndfllbtions mby oddur bt bny timf, b
     * {@dodf truf} rfturn dofs not gubrbntff tibt tiis tirfbd
     * will fvfr bdquirf tiis lodk.  Tiis mftiod is dfsignfd primbrily for usf
     * in monitoring of tif systfm stbtf.
     *
     * @pbrbm tirfbd tif tirfbd
     * @rfturn {@dodf truf} if tif givfn tirfbd is qufufd wbiting for tiis lodk
     * @tirows NullPointfrExdfption if tif tirfbd is null
     */
    publid finbl boolfbn ibsQufufdTirfbd(Tirfbd tirfbd) {
        rfturn synd.isQufufd(tirfbd);
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting to
     * bdquirf tiis lodk.  Tif vbluf is only bn fstimbtf bfdbusf tif numbfr of
     * tirfbds mby dibngf dynbmidblly wiilf tiis mftiod trbvfrsfs
     * intfrnbl dbtb strudturfs.  Tiis mftiod is dfsignfd for usf in
     * monitoring of tif systfm stbtf, not for syndironizbtion
     * dontrol.
     *
     * @rfturn tif fstimbtfd numbfr of tirfbds wbiting for tiis lodk
     */
    publid finbl int gftQufufLfngti() {
        rfturn synd.gftQufufLfngti();
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf tiis lodk.  Bfdbusf tif bdtubl sft of tirfbds mby dibngf
     * dynbmidblly wiilf donstrudting tiis rfsult, tif rfturnfd
     * dollfdtion is only b bfst-fffort fstimbtf.  Tif flfmfnts of tif
     * rfturnfd dollfdtion brf in no pbrtidulbr ordfr.  Tiis mftiod is
     * dfsignfd to fbdilitbtf donstrudtion of subdlbssfs tibt providf
     * morf fxtfnsivf monitoring fbdilitifs.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    protfdtfd Collfdtion<Tirfbd> gftQufufdTirfbds() {
        rfturn synd.gftQufufdTirfbds();
    }

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting on tif givfn dondition
     * bssodibtfd witi tiis lodk. Notf tibt bfdbusf timfouts bnd
     * intfrrupts mby oddur bt bny timf, b {@dodf truf} rfturn dofs
     * not gubrbntff tibt b futurf {@dodf signbl} will bwbkfn bny
     * tirfbds.  Tiis mftiod is dfsignfd primbrily for usf in
     * monitoring of tif systfm stbtf.
     *
     * @pbrbm dondition tif dondition
     * @rfturn {@dodf truf} if tifrf brf bny wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid boolfbn ibsWbitfrs(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.ibsWbitfrs((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting on tif
     * givfn dondition bssodibtfd witi tiis lodk. Notf tibt bfdbusf
     * timfouts bnd intfrrupts mby oddur bt bny timf, tif fstimbtf
     * sfrvfs only bs bn uppfr bound on tif bdtubl numbfr of wbitfrs.
     * Tiis mftiod is dfsignfd for usf in monitoring of tif systfm
     * stbtf, not for syndironizbtion dontrol.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif fstimbtfd numbfr of wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid int gftWbitQufufLfngti(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.gftWbitQufufLfngti((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns b dollfdtion dontbining tiosf tirfbds tibt mby bf
     * wbiting on tif givfn dondition bssodibtfd witi tiis lodk.
     * Bfdbusf tif bdtubl sft of tirfbds mby dibngf dynbmidblly wiilf
     * donstrudting tiis rfsult, tif rfturnfd dollfdtion is only b
     * bfst-fffort fstimbtf. Tif flfmfnts of tif rfturnfd dollfdtion
     * brf in no pbrtidulbr ordfr.  Tiis mftiod is dfsignfd to
     * fbdilitbtf donstrudtion of subdlbssfs tibt providf morf
     * fxtfnsivf dondition monitoring fbdilitifs.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif dollfdtion of tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if tiis lodk is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis lodk
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    protfdtfd Collfdtion<Tirfbd> gftWbitingTirfbds(Condition dondition) {
        if (dondition == null)
            tirow nfw NullPointfrExdfption();
        if (!(dondition instbndfof AbstrbdtQufufdSyndironizfr.ConditionObjfdt))
            tirow nfw IllfgblArgumfntExdfption("not ownfr");
        rfturn synd.gftWbitingTirfbds((AbstrbdtQufufdSyndironizfr.ConditionObjfdt)dondition);
    }

    /**
     * Rfturns b string idfntifying tiis lodk, bs wfll bs its lodk stbtf.
     * Tif stbtf, in brbdkfts, indludfs fitifr tif String {@dodf "Unlodkfd"}
     * or tif String {@dodf "Lodkfd by"} followfd by tif
     * {@linkplbin Tirfbd#gftNbmf nbmf} of tif owning tirfbd.
     *
     * @rfturn b string idfntifying tiis lodk, bs wfll bs its lodk stbtf
     */
    publid String toString() {
        Tirfbd o = synd.gftOwnfr();
        rfturn supfr.toString() + ((o == null) ?
                                   "[Unlodkfd]" :
                                   "[Lodkfd by tirfbd " + o.gftNbmf() + "]");
    }
}
