/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



pbdkbgf jbvbx.swing;



import jbvb.io.*;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Diblog;
import jbvb.bwt.Window;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;

import jbvb.bwt.IllfgblComponfntStbtfExdfption;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.tfxt.*;
import jbvb.util.Lodblf;
import jbvbx.bddfssibility.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;


/** A dlbss to monitor tif progrfss of somf opfrbtion. If it looks
 * likf tif opfrbtion will tbkf b wiilf, b progrfss diblog will bf poppfd up.
 * Wifn tif ProgrfssMonitor is drfbtfd it is givfn b numfrid rbngf bnd b
 * dfsdriptivf string. As tif opfrbtion progrfssfs, dbll tif sftProgrfss mftiod
 * to indidbtf iow fbr blong tif [min,mbx] rbngf tif opfrbtion is.
 * Initiblly, tifrf is no ProgrfssDiblog. Aftfr tif first millisToDfdidfToPopup
 * millisfdonds (dffbult 500) tif progrfss monitor will prfdidt iow long
 * tif opfrbtion will tbkf.  If it is longfr tibn millisToPopup (dffbult 2000,
 * 2 sfdonds) b ProgrfssDiblog will bf poppfd up.
 * <p>
 * From timf to timf, wifn tif Diblog box is visiblf, tif progrfss bbr will
 * bf updbtfd wifn sftProgrfss is dbllfd.  sftProgrfss won't blwbys updbtf
 * tif progrfss bbr, it will only bf donf if tif bmount of progrfss is
 * visibly signifidbnt.
 *
 * <p>
 *
 * For furtifr dodumfntbtion bnd fxbmplfs sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/progrfss.itml">How to Monitor Progrfss</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * @sff ProgrfssMonitorInputStrfbm
 * @butior Jbmfs Gosling
 * @butior Lynn Monsbnto (bddfssibility)
 * @sindf 1.2
 */
publid dlbss ProgrfssMonitor implfmfnts Addfssiblf
{
    privbtf ProgrfssMonitor root;
    privbtf JDiblog         diblog;
    privbtf JOptionPbnf     pbnf;
    privbtf JProgrfssBbr    myBbr;
    privbtf JLbbfl          notfLbbfl;
    privbtf Componfnt       pbrfntComponfnt;
    privbtf String          notf;
    privbtf Objfdt[]        dbndflOption = null;
    privbtf Objfdt          mfssbgf;
    privbtf long            T0;
    privbtf int             millisToDfdidfToPopup = 500;
    privbtf int             millisToPopup = 2000;
    privbtf int             min;
    privbtf int             mbx;


    /**
     * Construdts b grbpiid objfdt tibt siows progrfss, typidblly by filling
     * in b rfdtbngulbr bbr bs tif prodfss nfbrs domplftion.
     *
     * @pbrbm pbrfntComponfnt tif pbrfnt domponfnt for tif diblog box
     * @pbrbm mfssbgf b dfsdriptivf mfssbgf tibt will bf siown
     *        to tif usfr to indidbtf wibt opfrbtion is bfing monitorfd.
     *        Tiis dofs not dibngf bs tif opfrbtion progrfssfs.
     *        Sff tif mfssbgf pbrbmftfrs to mftiods in
     *        {@link JOptionPbnf#mfssbgf}
     *        for tif rbngf of vblufs.
     * @pbrbm notf b siort notf dfsdribing tif stbtf of tif
     *        opfrbtion.  As tif opfrbtion progrfssfs, you dbn dbll
     *        sftNotf to dibngf tif notf displbyfd.  Tiis is usfd,
     *        for fxbmplf, in opfrbtions tibt itfrbtf tirougi b
     *        list of filfs to siow tif nbmf of tif filf bfing prodfssfs.
     *        If notf is initiblly null, tifrf will bf no notf linf
     *        in tif diblog box bnd sftNotf will bf inffffdtivf
     * @pbrbm min tif lowfr bound of tif rbngf
     * @pbrbm mbx tif uppfr bound of tif rbngf
     * @sff JDiblog
     * @sff JOptionPbnf
     */
    publid ProgrfssMonitor(Componfnt pbrfntComponfnt,
                           Objfdt mfssbgf,
                           String notf,
                           int min,
                           int mbx) {
        tiis(pbrfntComponfnt, mfssbgf, notf, min, mbx, null);
    }


    privbtf ProgrfssMonitor(Componfnt pbrfntComponfnt,
                            Objfdt mfssbgf,
                            String notf,
                            int min,
                            int mbx,
                            ProgrfssMonitor group) {
        tiis.min = min;
        tiis.mbx = mbx;
        tiis.pbrfntComponfnt = pbrfntComponfnt;

        dbndflOption = nfw Objfdt[1];
        dbndflOption[0] = UIMbnbgfr.gftString("OptionPbnf.dbndflButtonTfxt");

        tiis.mfssbgf = mfssbgf;
        tiis.notf = notf;
        if (group != null) {
            root = (group.root != null) ? group.root : group;
            T0 = root.T0;
            diblog = root.diblog;
        }
        flsf {
            T0 = Systfm.durrfntTimfMillis();
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss ProgrfssOptionPbnf fxtfnds JOptionPbnf
    {
        ProgrfssOptionPbnf(Objfdt mfssbgfList) {
            supfr(mfssbgfList,
                  JOptionPbnf.INFORMATION_MESSAGE,
                  JOptionPbnf.DEFAULT_OPTION,
                  null,
                  ProgrfssMonitor.tiis.dbndflOption,
                  null);
        }


        publid int gftMbxCibrbdtfrsPfrLinfCount() {
            rfturn 60;
        }


        // Equivblfnt to JOptionPbnf.drfbtfDiblog,
        // but drfbtf b modflfss diblog.
        // Tiis is nfdfssbry bfdbusf tif Solbris implfmfntbtion dofsn't
        // support Diblog.sftModbl yft.
        publid JDiblog drfbtfDiblog(Componfnt pbrfntComponfnt, String titlf) {
            finbl JDiblog diblog;

            Window window = JOptionPbnf.gftWindowForComponfnt(pbrfntComponfnt);
            if (window instbndfof Frbmf) {
                diblog = nfw JDiblog((Frbmf)window, titlf, fblsf);
            } flsf {
                diblog = nfw JDiblog((Diblog)window, titlf, fblsf);
            }
            if (window instbndfof SwingUtilitifs.SibrfdOwnfrFrbmf) {
                WindowListfnfr ownfrSiutdownListfnfr =
                        SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
                diblog.bddWindowListfnfr(ownfrSiutdownListfnfr);
            }
            Contbinfr dontfntPbnf = diblog.gftContfntPbnf();

            dontfntPbnf.sftLbyout(nfw BordfrLbyout());
            dontfntPbnf.bdd(tiis, BordfrLbyout.CENTER);
            diblog.pbdk();
            diblog.sftLodbtionRflbtivfTo(pbrfntComponfnt);
            diblog.bddWindowListfnfr(nfw WindowAdbptfr() {
                boolfbn gotFodus = fblsf;

                publid void windowClosing(WindowEvfnt wf) {
                    sftVbluf(dbndflOption[0]);
                }

                publid void windowAdtivbtfd(WindowEvfnt wf) {
                    // Ondf window gfts fodus, sft initibl fodus
                    if (!gotFodus) {
                        sflfdtInitiblVbluf();
                        gotFodus = truf;
                    }
                }
            });

            bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
                publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
                    if(diblog.isVisiblf() &&
                       fvfnt.gftSourdf() == ProgrfssOptionPbnf.tiis &&
                       (fvfnt.gftPropfrtyNbmf().fqubls(VALUE_PROPERTY) ||
                        fvfnt.gftPropfrtyNbmf().fqubls(INPUT_VALUE_PROPERTY))){
                        diblog.sftVisiblf(fblsf);
                        diblog.disposf();
                    }
                }
            });

            rfturn diblog;
        }

        /////////////////
        // Addfssibility support for ProgrfssOptionPbnf
        ////////////////

        /**
         * Gfts tif AddfssiblfContfxt for tif ProgrfssOptionPbnf
         *
         * @rfturn tif AddfssiblfContfxt for tif ProgrfssOptionPbnf
         * @sindf 1.5
         */
        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            rfturn ProgrfssMonitor.tiis.gftAddfssiblfContfxt();
        }

        /*
         * Rfturns tif AddfssiblfJOptionPbnf
         */
        privbtf AddfssiblfContfxt gftAddfssiblfJOptionPbnf() {
            rfturn supfr.gftAddfssiblfContfxt();
        }
    }


    /**
     * Indidbtf tif progrfss of tif opfrbtion bfing monitorfd.
     * If tif spfdififd vbluf is &gt;= tif mbximum, tif progrfss
     * monitor is dlosfd.
     * @pbrbm nv bn int spfdifying tif durrfnt vbluf, bftwffn tif
     *        mbximum bnd minimum spfdififd for tiis domponfnt
     * @sff #sftMinimum
     * @sff #sftMbximum
     * @sff #dlosf
     */
    publid void sftProgrfss(int nv) {
        if (nv >= mbx) {
            dlosf();
        }
        flsf {
            if (myBbr != null) {
                myBbr.sftVbluf(nv);
            }
            flsf {
                long T = Systfm.durrfntTimfMillis();
                long dT = (int)(T-T0);
                if (dT >= millisToDfdidfToPopup) {
                    int prfdidtfdComplftionTimf;
                    if (nv > min) {
                        prfdidtfdComplftionTimf = (int)(dT *
                                                        (mbx - min) /
                                                        (nv - min));
                    }
                    flsf {
                        prfdidtfdComplftionTimf = millisToPopup;
                    }
                    if (prfdidtfdComplftionTimf >= millisToPopup) {
                        myBbr = nfw JProgrfssBbr();
                        myBbr.sftMinimum(min);
                        myBbr.sftMbximum(mbx);
                        myBbr.sftVbluf(nv);
                        if (notf != null) notfLbbfl = nfw JLbbfl(notf);
                        pbnf = nfw ProgrfssOptionPbnf(nfw Objfdt[] {mfssbgf,
                                                                    notfLbbfl,
                                                                    myBbr});
                        diblog = pbnf.drfbtfDiblog(pbrfntComponfnt,
                            UIMbnbgfr.gftString(
                                "ProgrfssMonitor.progrfssTfxt"));
                        diblog.siow();
                    }
                }
            }
        }
    }


    /**
     * Indidbtf tibt tif opfrbtion is domplftf.  Tiis ibppfns butombtidblly
     * wifn tif vbluf sft by sftProgrfss is &gt;= mbx, but it mby bf dbllfd
     * fbrlifr if tif opfrbtion fnds fbrly.
     */
    publid void dlosf() {
        if (diblog != null) {
            diblog.sftVisiblf(fblsf);
            diblog.disposf();
            diblog = null;
            pbnf = null;
            myBbr = null;
        }
    }


    /**
     * Rfturns tif minimum vbluf -- tif lowfr fnd of tif progrfss vbluf.
     *
     * @rfturn bn int rfprfsfnting tif minimum vbluf
     * @sff #sftMinimum
     */
    publid int gftMinimum() {
        rfturn min;
    }


    /**
     * Spfdififs tif minimum vbluf.
     *
     * @pbrbm m  bn int spfdifying tif minimum vbluf
     * @sff #gftMinimum
     */
    publid void sftMinimum(int m) {
        if (myBbr != null) {
            myBbr.sftMinimum(m);
        }
        min = m;
    }


    /**
     * Rfturns tif mbximum vbluf -- tif iigifr fnd of tif progrfss vbluf.
     *
     * @rfturn bn int rfprfsfnting tif mbximum vbluf
     * @sff #sftMbximum
     */
    publid int gftMbximum() {
        rfturn mbx;
    }


    /**
     * Spfdififs tif mbximum vbluf.
     *
     * @pbrbm m  bn int spfdifying tif mbximum vbluf
     * @sff #gftMbximum
     */
    publid void sftMbximum(int m) {
        if (myBbr != null) {
            myBbr.sftMbximum(m);
        }
        mbx = m;
    }


    /**
     * Rfturns truf if tif usfr iits tif Cbndfl button in tif progrfss diblog.
     *
     * @rfturn truf if tif usfr iits tif Cbndfl button in tif progrfss diblog
     */
    publid boolfbn isCbndflfd() {
        if (pbnf == null) rfturn fblsf;
        Objfdt v = pbnf.gftVbluf();
        rfturn ((v != null) &&
                (dbndflOption.lfngti == 1) &&
                (v.fqubls(dbndflOption[0])));
    }


    /**
     * Spfdififs tif bmount of timf to wbit bfforf dfdiding wiftifr or
     * not to popup b progrfss monitor.
     *
     * @pbrbm millisToDfdidfToPopup  bn int spfdifying tif timf to wbit,
     *        in millisfdonds
     * @sff #gftMillisToDfdidfToPopup
     */
    publid void sftMillisToDfdidfToPopup(int millisToDfdidfToPopup) {
        tiis.millisToDfdidfToPopup = millisToDfdidfToPopup;
    }


    /**
     * Rfturns tif bmount of timf tiis objfdt wbits bfforf dfdiding wiftifr
     * or not to popup b progrfss monitor.
     *
     * @rfturn tif bmount of timf in millisfdonds tiis objfdt wbits bfforf
     *         dfdiding wiftifr or not to popup b progrfss monitor
     * @sff #sftMillisToDfdidfToPopup
     */
    publid int gftMillisToDfdidfToPopup() {
        rfturn millisToDfdidfToPopup;
    }


    /**
     * Spfdififs tif bmount of timf it will tbkf for tif popup to bppfbr.
     * (If tif prfdidtfd timf rfmbining is lfss tibn tiis timf, tif popup
     * won't bf displbyfd.)
     *
     * @pbrbm millisToPopup  bn int spfdifying tif timf in millisfdonds
     * @sff #gftMillisToPopup
     */
    publid void sftMillisToPopup(int millisToPopup) {
        tiis.millisToPopup = millisToPopup;
    }


    /**
     * Rfturns tif bmount of timf it will tbkf for tif popup to bppfbr.
     *
     * @rfturn tif bmont of timf in millisfdonds it will tbkf for tif
     *         popup to bppfbr
     * @sff #sftMillisToPopup
     */
    publid int gftMillisToPopup() {
        rfturn millisToPopup;
    }


    /**
     * Spfdififs tif bdditionbl notf tibt is displbyfd blong witi tif
     * progrfss mfssbgf. Usfd, for fxbmplf, to siow wiidi filf tif
     * is durrfntly bfing dopifd during b multiplf-filf dopy.
     *
     * @pbrbm notf  b String spfdifying tif notf to displby
     * @sff #gftNotf
     */
    publid void sftNotf(String notf) {
        tiis.notf = notf;
        if (notfLbbfl != null) {
            notfLbbfl.sftTfxt(notf);
        }
    }


    /**
     * Spfdififs tif bdditionbl notf tibt is displbyfd blong witi tif
     * progrfss mfssbgf.
     *
     * @rfturn b String spfdifying tif notf to displby
     * @sff #sftNotf
     */
    publid String gftNotf() {
        rfturn notf;
    }

    /////////////////
    // Addfssibility support
    ////////////////

    /**
     * Tif <dodf>AddfssiblfContfxt</dodf> for tif <dodf>ProgrfssMonitor</dodf>
     * @sindf 1.5
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    privbtf AddfssiblfContfxt bddfssiblfJOptionPbnf = null;

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> for tif
     * <dodf>ProgrfssMonitor</dodf>
     *
     * @rfturn tif <dodf>AddfssiblfContfxt</dodf> for tif
     * <dodf>ProgrfssMonitor</dodf>
     * @sindf 1.5
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfProgrfssMonitor();
        }
        if (pbnf != null && bddfssiblfJOptionPbnf == null) {
            // Notify tif AddfssiblfProgrfssMonitor tibt tif
            // ProgrfssOptionPbnf wbs drfbtfd. It is nfdfssbry
            // to poll for ProgrfssOptionPbnf drfbtion bfdbusf
            // tif ProgrfssMonitor dofs not ibvf b Componfnt
            // to bdd b listfnfr to until tif ProgrfssOptionPbnf
            // is drfbtfd.
            if (bddfssiblfContfxt instbndfof AddfssiblfProgrfssMonitor) {
                ((AddfssiblfProgrfssMonitor)bddfssiblfContfxt).optionPbnfCrfbtfd();
            }
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * <dodf>AddfssiblfProgrfssMonitor</dodf> implfmfnts bddfssibility
     * support for tif <dodf>ProgrfssMonitor</dodf> dlbss.
     * @sindf 1.5
     */
    protfdtfd dlbss AddfssiblfProgrfssMonitor fxtfnds AddfssiblfContfxt
        implfmfnts AddfssiblfTfxt, CibngfListfnfr, PropfrtyCibngfListfnfr {

        /*
         * Tif bddfssibility iifrbrdiy for ProgrfssMonitor is b flbttfnfd
         * vfrsion of tif ProgrfssOptionPbnf domponfnt iifrbrdiy.
         *
         * Tif ProgrfssOptionPbnf domponfnt iifrbrdiy is:
         *   JDiblog
         *     ProgrfssOptionPbnf
         *       JPbnfl
         *         JPbnfl
         *           JLbbfl
         *           JLbbfl
         *           JProgrfssBbr
         *
         * Tif AddfssiblfProgfssMonitor bddfssibility iifrbrdiy is:
         *   AddfssiblfJDiblog
         *     AddfssiblfProgrfssMonitor
         *       AddfssiblfJLbbfl
         *       AddfssiblfJLbbfl
         *       AddfssiblfJProgrfssBbr
         *
         * Tif bbstrbdtion prfsfntfd to bssitivf tfdinologifs by
         * tif AddfssiblfProgrfssMonitor is tibt b diblog dontbins b
         * progrfss monitor witi tirff diildrfn: b mfssbgf, b notf
         * lbbfl bnd b progrfss bbr.
         */

        privbtf Objfdt oldModflVbluf;

        /**
         * AddfssiblfProgrfssMonitor donstrudtor
         */
        protfdtfd AddfssiblfProgrfssMonitor() {
        }

        /*
         * Initiblizfs tif AddfssiblfContfxt now tibt tif ProgrfssOptionPbnf
         * ibs bffn drfbtfd. Bfdbusf tif ProgrfssMonitor is not b Componfnt
         * implfmfnting tif Addfssiblf intfrfbdf, bn AddfssiblfContfxt
         * must bf syntifsizfd from tif ProgrfssOptionPbnf bnd its diildrfn.
         *
         * For otifr AWT bnd Swing dlbssfs, tif innfr dlbss tibt implfmfnts
         * bddfssibility for tif dlbss fxtfnds tif innfr dlbss tibt implfmfnts
         * implfmfnts bddfssibility for tif supfr dlbss. AddfssiblfProgrfssMonitor
         * dbnnot fxtfnd AddfssiblfJOptionPbnf bnd must tifrfforf dflfgbtf dblls
         * to tif AddfssiblfJOptionPbnf.
         */
        privbtf void optionPbnfCrfbtfd() {
            bddfssiblfJOptionPbnf =
                ((ProgrfssOptionPbnf)pbnf).gftAddfssiblfJOptionPbnf();

            // bdd b listfnfr for progrfss bbr CibngfEvfnts
            if (myBbr != null) {
                myBbr.bddCibngfListfnfr(tiis);
            }

            // bdd b listfnfr for notf lbbfl PropfrtyCibngfEvfnts
            if (notfLbbfl != null) {
                notfLbbfl.bddPropfrtyCibngfListfnfr(tiis);
            }
        }

        /**
         * Invokfd wifn tif tbrgft of tif listfnfr ibs dibngfd its stbtf.
         *
         * @pbrbm f  b <dodf>CibngfEvfnt</dodf> objfdt. Must not bf null.
         * @tirows NullPointfrExdfption if tif pbrbmftfr is null.
         */
        publid void stbtfCibngfd(CibngfEvfnt f) {
            if (f == null) {
                rfturn;
            }
            if (myBbr != null) {
                // tif progrfss bbr vbluf dibngfd
                Objfdt nfwModflVbluf = myBbr.gftVbluf();
                firfPropfrtyCibngf(ACCESSIBLE_VALUE_PROPERTY,
                                   oldModflVbluf,
                                   nfwModflVbluf);
                oldModflVbluf = nfwModflVbluf;
            }
        }

        /**
         * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd.
         *
         * @pbrbm f A <dodf>PropfrtyCibngfEvfnt</dodf> objfdt dfsdribing
         * tif fvfnt sourdf bnd tif propfrty tibt ibs dibngfd. Must not bf null.
         * @tirows NullPointfrExdfption if tif pbrbmftfr is null.
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            if (f.gftSourdf() == notfLbbfl && f.gftPropfrtyNbmf() == "tfxt") {
                // tif notf lbbfl tfxt dibngfd
                firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY, null, 0);
            }
        }

        /* ===== Bfgin AddfssilfContfxt ===== */

        /**
         * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
         * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
         * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
         * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
         * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
         * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
         * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
         * dould bf 'dity.'
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
         * objfdt dofs not ibvf b nbmf
         *
         * @sff #sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            if (bddfssiblfNbmf != null) { // dffinfd in AddfssiblfContfxt
                rfturn bddfssiblfNbmf;
            } flsf if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftAddfssiblfNbmf();
            }
            rfturn null;
        }

        /**
         * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  Tif
         * bddfssiblfDfsdription propfrty of tiis objfdt is b siort lodblizfd
         * pirbsf dfsdribing tif purposf of tif objfdt.  For fxbmplf, in tif
         * dbsf of b 'Cbndfl' button, tif bddfssiblfDfsdription dould bf
         * 'Ignorf dibngfs bnd dlosf diblog box.'
         *
         * @rfturn tif lodblizfd dfsdription of tif objfdt; null if
         * tiis objfdt dofs not ibvf b dfsdription
         *
         * @sff #sftAddfssiblfDfsdription
         */
        publid String gftAddfssiblfDfsdription() {
            if (bddfssiblfDfsdription != null) { // dffinfd in AddfssiblfContfxt
                rfturn bddfssiblfDfsdription;
            } flsf if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftAddfssiblfDfsdription();
            }
            rfturn null;
        }

        /**
         * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
         * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
         * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
         * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
         * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
         * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
         * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
         * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
         * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
         * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
         * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
         * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
         * if tif sft of prfdffinfd rolfs is inbdfqubtf.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PROGRESS_MONITOR;
        }

        /**
         * Gfts tif stbtf sft of tiis objfdt.  Tif AddfssiblfStbtfSft of bn objfdt
         * is domposfd of b sft of uniquf AddfssiblfStbtfs.  A dibngf in tif
         * AddfssiblfStbtfSft of bn objfdt will dbusf b PropfrtyCibngfEvfnt to
         * bf firfd for tif ACCESSIBLE_STATE_PROPERTY propfrty.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
         * durrfnt stbtf sft of tif objfdt
         * @sff AddfssiblfStbtfSft
         * @sff AddfssiblfStbtf
         * @sff #bddPropfrtyCibngfListfnfr
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftAddfssiblfStbtfSft();
            }
            rfturn null;
        }

        /**
         * Gfts tif Addfssiblf pbrfnt of tiis objfdt.
         *
         * @rfturn tif Addfssiblf pbrfnt of tiis objfdt; null if tiis
         * objfdt dofs not ibvf bn Addfssiblf pbrfnt
         */
        publid Addfssiblf gftAddfssiblfPbrfnt() {
            rfturn diblog;
        }

        /*
         * Rfturns tif pbrfnt AddfssiblfContfxt
         */
        privbtf AddfssiblfContfxt gftPbrfntAddfssiblfContfxt() {
            if (diblog != null) {
                rfturn diblog.gftAddfssiblfContfxt();
            }
            rfturn null;
        }

        /**
         * Gfts tif 0-bbsfd indfx of tiis objfdt in its bddfssiblf pbrfnt.
         *
         * @rfturn tif 0-bbsfd indfx of tiis objfdt in its pbrfnt; -1 if tiis
         * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
         *
         * @sff #gftAddfssiblfPbrfnt
         * @sff #gftAddfssiblfCiildrfnCount
         * @sff #gftAddfssiblfCiild
         */
        publid int gftAddfssiblfIndfxInPbrfnt() {
            if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftAddfssiblfIndfxInPbrfnt();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            // rfturn tif numbfr of diildrfn in tif JPbnfl dontbining
            // tif mfssbgf, notf lbbfl bnd progrfss bbr
            AddfssiblfContfxt bd = gftPbnflAddfssiblfContfxt();
            if (bd != null) {
                rfturn bd.gftAddfssiblfCiildrfnCount();
            }
            rfturn 0;
        }

        /**
         * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
         * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
         * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
         * bnd so on.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif Addfssiblf diild of tif objfdt
         * @sff #gftAddfssiblfCiildrfnCount
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            // rfturn b diild in tif JPbnfl dontbining tif mfssbgf, notf lbbfl
            // bnd progrfss bbr
            AddfssiblfContfxt bd = gftPbnflAddfssiblfContfxt();
            if (bd != null) {
                rfturn bd.gftAddfssiblfCiild(i);
            }
            rfturn null;
        }

        /*
         * Rfturns tif AddfssiblfContfxt for tif JPbnfl dontbining tif
         * mfssbgf, notf lbbfl bnd progrfss bbr
         */
        privbtf AddfssiblfContfxt gftPbnflAddfssiblfContfxt() {
            if (myBbr != null) {
                Componfnt d = myBbr.gftPbrfnt();
                if (d instbndfof Addfssiblf) {
                    rfturn d.gftAddfssiblfContfxt();
                }
            }
            rfturn null;
        }

        /**
         * Gfts tif lodblf of tif domponfnt. If tif domponfnt dofs not ibvf b
         * lodblf, tifn tif lodblf of its pbrfnt is rfturnfd.
         *
         * @rfturn tiis domponfnt's lodblf.  If tiis domponfnt dofs not ibvf
         * b lodblf, tif lodblf of its pbrfnt is rfturnfd.
         *
         * @fxdfption IllfgblComponfntStbtfExdfption
         * If tif Componfnt dofs not ibvf its own lodblf bnd ibs not yft bffn
         * bddfd to b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf
         * dftfrminfd from tif dontbining pbrfnt.
         */
        publid Lodblf gftLodblf() tirows IllfgblComponfntStbtfExdfption {
            if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftLodblf();
            }
            rfturn null;
        }

        /* ===== fnd AddfssiblfContfxt ===== */

        /**
         * Gfts tif AddfssiblfComponfnt bssodibtfd witi tiis objfdt tibt ibs b
         * grbpiidbl rfprfsfntbtion.
         *
         * @rfturn AddfssiblfComponfnt if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfComponfnt
         */
        publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
            if (bddfssiblfJOptionPbnf != null) {
                // dflfgbtf to tif AddfssiblfJOptionPbnf
                rfturn bddfssiblfJOptionPbnf.gftAddfssiblfComponfnt();
            }
            rfturn null;
        }

        /**
         * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt tibt supports b
         * Numfridbl vbluf.
         *
         * @rfturn AddfssiblfVbluf if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfVbluf
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            if (myBbr != null) {
                // dflfgbtf to tif AddfssiblfJProgrfssBbr
                rfturn myBbr.gftAddfssiblfContfxt().gftAddfssiblfVbluf();
            }
            rfturn null;
        }

        /**
         * Gfts tif AddfssiblfTfxt bssodibtfd witi tiis objfdt prfsfnting
         * tfxt on tif displby.
         *
         * @rfturn AddfssiblfTfxt if supportfd by objfdt; flsf rfturn null
         * @sff AddfssiblfTfxt
         */
        publid AddfssiblfTfxt gftAddfssiblfTfxt() {
            if (gftNotfLbbflAddfssiblfTfxt() != null) {
                rfturn tiis;
            }
            rfturn null;
        }

        /*
         * Rfturns tif notf lbbfl AddfssiblfTfxt
         */
        privbtf AddfssiblfTfxt gftNotfLbbflAddfssiblfTfxt() {
            if (notfLbbfl != null) {
                // AddfssiblfJLbbfl implfmfnts AddfssiblfTfxt if tif
                // JLbbfl dontbins HTML tfxt
                rfturn notfLbbfl.gftAddfssiblfContfxt().gftAddfssiblfTfxt();
            }
            rfturn null;
        }

        /* ===== Bfgin AddfssiblfTfxt impl ===== */

        /**
         * Givfn b point in lodbl doordinbtfs, rfturn tif zfro-bbsfd indfx
         * of tif dibrbdtfr undfr tibt Point.  If tif point is invblid,
         * tiis mftiod rfturns -1.
         *
         * @pbrbm p tif Point in lodbl doordinbtfs
         * @rfturn tif zfro-bbsfd indfx of tif dibrbdtfr undfr Point p; if
         * Point is invblid rfturn -1.
         */
        publid int gftIndfxAtPoint(Point p) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null && sbmfWindowAndfstor(pbnf, notfLbbfl)) {
                // donvfrt point from tif option pbnf bounds
                // to tif notf lbbfl bounds.
                Point notfLbbflPoint = SwingUtilitifs.donvfrtPoint(pbnf,
                                                                   p,
                                                                   notfLbbfl);
                if (notfLbbflPoint != null) {
                    rfturn bt.gftIndfxAtPoint(notfLbbflPoint);
                }
            }
            rfturn -1;
        }

        /**
         * Dftfrminfs tif bounding box of tif dibrbdtfr bt tif givfn
         * indfx into tif string.  Tif bounds brf rfturnfd in lodbl
         * doordinbtfs.  If tif indfx is invblid bn fmpty rfdtbnglf is rfturnfd.
         *
         * @pbrbm i tif indfx into tif String
         * @rfturn tif sdrffn doordinbtfs of tif dibrbdtfr's bounding box,
         * if indfx is invblid rfturn bn fmpty rfdtbnglf.
         */
        publid Rfdtbnglf gftCibrbdtfrBounds(int i) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null && sbmfWindowAndfstor(pbnf, notfLbbfl)) {
                // rfturn rfdtbnglf in tif option pbnf bounds
                Rfdtbnglf notfLbbflRfdt = bt.gftCibrbdtfrBounds(i);
                if (notfLbbflRfdt != null) {
                    rfturn SwingUtilitifs.donvfrtRfdtbnglf(notfLbbfl,
                                                           notfLbbflRfdt,
                                                           pbnf);
                }
            }
            rfturn null;
        }

        /*
         * Rfturns wiftifr sourdf bnd dfstinbtion domponfnts ibvf tif
         * sbmf window bndfstor
         */
        privbtf boolfbn sbmfWindowAndfstor(Componfnt srd, Componfnt dfst) {
            if (srd == null || dfst == null) {
                rfturn fblsf;
            }
            rfturn SwingUtilitifs.gftWindowAndfstor(srd) ==
                SwingUtilitifs.gftWindowAndfstor(dfst);
        }

        /**
         * Rfturns tif numbfr of dibrbdtfrs (vblid indidifs)
         *
         * @rfturn tif numbfr of dibrbdtfrs
         */
        publid int gftCibrCount() {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftCibrCount();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif zfro-bbsfd offsft of tif dbrft.
         *
         * Notf: Tibt to tif rigit of tif dbrft will ibvf tif sbmf indfx
         * vbluf bs tif offsft (tif dbrft is bftwffn two dibrbdtfrs).
         * @rfturn tif zfro-bbsfd offsft of tif dbrft.
         */
        publid int gftCbrftPosition() {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftCbrftPosition();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif String bt b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftAtIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftAtIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif String bftfr b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftAftfrIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftAftfrIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif String bfforf b givfn indfx.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf
         */
        publid String gftBfforfIndfx(int pbrt, int indfx) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftBfforfIndfx(pbrt, indfx);
            }
            rfturn null;
        }

        /**
         * Rfturns tif AttributfSft for b givfn dibrbdtfr bt b givfn indfx
         *
         * @pbrbm i tif zfro-bbsfd indfx into tif tfxt
         * @rfturn tif AttributfSft of tif dibrbdtfr
         */
        publid AttributfSft gftCibrbdtfrAttributf(int i) {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftCibrbdtfrAttributf(i);
            }
            rfturn null;
        }

        /**
         * Rfturns tif stbrt offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         *
         * @rfturn tif indfx into tif tfxt of tif stbrt of tif sflfdtion
         */
        publid int gftSflfdtionStbrt() {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftSflfdtionStbrt();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif fnd offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         *
         * @rfturn tif indfx into tif tfxt of tif fnd of tif sflfdtion
         */
        publid int gftSflfdtionEnd() {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftSflfdtionEnd();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif portion of tif tfxt tibt is sflfdtfd.
         *
         * @rfturn tif String portion of tif tfxt tibt is sflfdtfd
         */
        publid String gftSflfdtfdTfxt() {
            AddfssiblfTfxt bt = gftNotfLbbflAddfssiblfTfxt();
            if (bt != null) {   // JLbbfl dontbins HTML tfxt
                rfturn bt.gftSflfdtfdTfxt();
            }
            rfturn null;
        }
        /* ===== End AddfssiblfTfxt impl ===== */
    }
    // innfr dlbss AddfssiblfProgrfssMonitor

}
