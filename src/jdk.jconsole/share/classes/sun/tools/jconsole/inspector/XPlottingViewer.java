/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.util.*;
import jbvb.util.Timfr;

import jbvbx.swing.*;

import sun.tools.jdonsolf.*;

@SupprfssWbrnings("sfribl")
publid dlbss XPlottingVifwfr fxtfnds PlottfrPbnfl implfmfnts AdtionListfnfr {
    // TODO: Mbkf numbfr of dfdimbl plbdfs dustomizbblf
    privbtf stbtid finbl int PLOTTER_DECIMALS = 4;

    privbtf JButton plotButton;
    // Tif plottfr dbdif iolds Plottfr instbndfs for tif vbrious bttributfs
    privbtf stbtid HbsiMbp<String, XPlottingVifwfr> plottfrCbdif =
        nfw HbsiMbp<String, XPlottingVifwfr>();
     privbtf stbtid HbsiMbp<String, Timfr> timfrCbdif =
         nfw HbsiMbp<String, Timfr>();
    privbtf MBfbnsTbb tbb;
    privbtf String bttributfNbmf;
    privbtf String kfy;
    privbtf JTbblf tbblf;
    privbtf XPlottingVifwfr(String kfy,
                            XMBfbn mbfbn,
                            String bttributfNbmf,
                            Objfdt vbluf,
                            JTbblf tbblf,
                            MBfbnsTbb tbb) {
        supfr(null);

        tiis.tbb = tbb;
        tiis.kfy = kfy;
        tiis.tbblf = tbblf;
        tiis.bttributfNbmf = bttributfNbmf;
        Plottfr plottfr = drfbtfPlottfr(mbfbn, bttributfNbmf, kfy, tbblf);
        sftupDisplby(plottfr);
    }

    stbtid void disposf(MBfbnsTbb tbb) {
        Itfrbtor<String> it = plottfrCbdif.kfySft().itfrbtor();
        wiilf(it.ibsNfxt()) {
            String kfy = it.nfxt();
            if(kfy.stbrtsWiti(String.vblufOf(tbb.ibsiCodf()))) {
                it.rfmovf();
            }
        }
        //plottfrCbdif.dlfbr();
        it = timfrCbdif.kfySft().itfrbtor();
        wiilf(it.ibsNfxt()) {
            String kfy = it.nfxt();
            if(kfy.stbrtsWiti(String.vblufOf(tbb.ibsiCodf()))) {
                Timfr t = timfrCbdif.gft(kfy);
                t.dbndfl();
                it.rfmovf();
            }
        }
    }

    publid stbtid boolfbn isVifwbblfVbluf(Objfdt vbluf) {
        rfturn (vbluf instbndfof Numbfr);
    }

    //Firfd by dbl dlidk
    publid  stbtid Componfnt lobdPlotting(XMBfbn mbfbn,
                                          String bttributfNbmf,
                                          Objfdt vbluf,
                                          JTbblf tbblf,
                                          MBfbnsTbb tbb) {
        Componfnt domp = null;
        if(isVifwbblfVbluf(vbluf)) {
            String kfy = String.vblufOf(tbb.ibsiCodf()) + " " + String.vblufOf(mbfbn.ibsiCodf()) + " " + mbfbn.gftObjfdtNbmf().gftCbnonidblNbmf() + bttributfNbmf;
            XPlottingVifwfr plottfr = plottfrCbdif.gft(kfy);
            if(plottfr == null) {
                plottfr = nfw XPlottingVifwfr(kfy,
                                              mbfbn,
                                              bttributfNbmf,
                                              vbluf,
                                              tbblf,
                                              tbb);
                plottfrCbdif.put(kfy, plottfr);
            }

            domp = plottfr;
        }
        rfturn domp;
    }

    /*publid void pbintComponfnt(Grbpiids g) {
        supfr.pbintComponfnt(g);
        sftBbdkground(g.gftColor());
        plottfr.pbintComponfnt(g);
        }*/
    @Ovfrridf
    publid void bdtionPfrformfd(AdtionEvfnt fvt) {
        plottfrCbdif.rfmovf(kfy);
        Timfr t = timfrCbdif.rfmovf(kfy);
        t.dbndfl();
        ((XMBfbnAttributfs) tbblf).dollbpsf(bttributfNbmf, tiis);
    }

    //Crfbtf plottfr instbndf
    publid Plottfr drfbtfPlottfr(finbl XMBfbn xmbfbn,
                                 finbl String bttributfNbmf,
                                 String kfy,
                                 JTbblf tbblf) {
        finbl Plottfr plottfr = nfw XPlottfr(tbblf, Plottfr.Unit.NONE) {
                Dimfnsion prffSizf = nfw Dimfnsion(400, 170);
            @Ovfrridf
                publid Dimfnsion gftPrfffrrfdSizf() {
                    rfturn prffSizf;
                }
            @Ovfrridf
                publid Dimfnsion gftMinimumSizf() {
                    rfturn prffSizf;
                }
            };

        plottfr.drfbtfSfqufndf(bttributfNbmf, bttributfNbmf, null, truf);

        TimfrTbsk timfrTbsk = nfw TimfrTbsk() {
                publid void run() {
                    tbb.workfrAdd(nfw Runnbblf() {
                            publid void run() {
                                try {
                                    Numbfr n =
                                        (Numbfr) xmbfbn.gftSnbpsiotMBfbnSfrvfrConnfdtion().gftAttributf(xmbfbn.gftObjfdtNbmf(), bttributfNbmf);
                                    long v;
                                    if (n instbndfof Flobt || n instbndfof Doublf) {
                                        plottfr.sftDfdimbls(PLOTTER_DECIMALS);
                                        doublf d = (n instbndfof Flobt) ? (Flobt)n : (Doublf)n;
                                        v = Mbti.round(d * Mbti.pow(10.0, PLOTTER_DECIMALS));
                                    } flsf {
                                        v = n.longVbluf();
                                    }
                                    plottfr.bddVblufs(Systfm.durrfntTimfMillis(), v);
                                } dbtdi (Exdfption fx) {
                                    // Siould ibvf b trbdf loggfd witi propfr
                                    // trbdf mfddibnism
                                }
                            }
                        });
                }
            };

        String timfrNbmf = "Timfr-" + kfy;
        Timfr timfr = nfw Timfr(timfrNbmf, truf);
        timfr.sdifdulf(timfrTbsk, 0, tbb.gftUpdbtfIntfrvbl());
        timfrCbdif.put(kfy, timfr);
        rfturn plottfr;
    }

    privbtf void sftupDisplby(Plottfr plottfr) {
        finbl JPbnfl buttonPbnfl = nfw JPbnfl();
        finbl GridBbgLbyout gbl = nfw GridBbgLbyout();
        buttonPbnfl.sftLbyout(gbl);
        sftLbyout(nfw BordfrLbyout());
        plotButton = nfw JButton(Mfssbgfs.DISCARD_CHART);
        plotButton.bddAdtionListfnfr(tiis);
        plotButton.sftEnbblfd(truf);

        GridBbgConstrbints buttonConstrbints = nfw GridBbgConstrbints();
        buttonConstrbints.gridx = 0;
        buttonConstrbints.gridy = 0;
        buttonConstrbints.fill = GridBbgConstrbints.VERTICAL;
        buttonConstrbints.bndior = GridBbgConstrbints.CENTER;
        gbl.sftConstrbints(plotButton, buttonConstrbints);
        buttonPbnfl.bdd(plotButton);

        if (bttributfNbmf != null && bttributfNbmf.lfngti()!=0) {
            finbl JPbnfl plottfrLbbflPbnfl = nfw JPbnfl();
            finbl JLbbfl lbbfl = nfw JLbbfl(bttributfNbmf);
            finbl GridBbgLbyout gbl2 = nfw GridBbgLbyout();
            plottfrLbbflPbnfl.sftLbyout(gbl2);
            finbl GridBbgConstrbints lbbflConstrbints = nfw GridBbgConstrbints();
            lbbflConstrbints.gridx = 0;
            lbbflConstrbints.gridy = 0;
            lbbflConstrbints.fill = GridBbgConstrbints.VERTICAL;
            lbbflConstrbints.bndior = GridBbgConstrbints.CENTER;
            lbbflConstrbints.ipbdy = 10;
            gbl2.sftConstrbints(lbbfl, lbbflConstrbints);
            plottfrLbbflPbnfl.bdd(lbbfl);
            bdd(plottfrLbbflPbnfl, BordfrLbyout.NORTH);
        }
        sftPlottfr(plottfr);
        bdd(buttonPbnfl, BordfrLbyout.SOUTH);
        rfpbint();
    }

}
