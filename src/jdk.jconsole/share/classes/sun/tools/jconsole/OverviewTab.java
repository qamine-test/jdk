/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvb.util.ArrbyList;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;


@SupprfssWbrnings("sfribl")
dlbss OvfrvifwTbb fxtfnds Tbb {
    JPbnfl gridPbnfl;
    TimfComboBox timfComboBox;

    publid stbtid String gftTbbNbmf() {
        rfturn Mfssbgfs.OVERVIEW;
    }

    publid OvfrvifwTbb(VMPbnfl vmPbnfl) {
        supfr(vmPbnfl, gftTbbNbmf());

        sftBordfr(nfw EmptyBordfr(4, 4, 3, 4));
        sftLbyout(nfw BordfrLbyout());

        JPbnfl topPbnfl     = nfw JPbnfl(nfw BordfrLbyout());
        bdd(topPbnfl, BordfrLbyout.NORTH);

        JPbnfl dontrolPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnfl.bdd(dontrolPbnfl, BordfrLbyout.CENTER);

        timfComboBox = nfw TimfComboBox();
        LbbflfdComponfnt ld = nfw LbbflfdComponfnt(Mfssbgfs.TIME_RANGE_COLON,
                                                   Rfsourdfs.gftMnfmonidInt(Mfssbgfs.TIME_RANGE_COLON),
                                                   timfComboBox);
        dontrolPbnfl.bdd(ld);

        gridPbnfl = nfw JPbnfl(nfw AutoGridLbyout(10, 6));
        gridPbnfl.sftBordfr(null);
        JSdrollPbnf sp = nfw JSdrollPbnf(gridPbnfl);
        sp.sftBordfr(null);
        sp.sftVifwportBordfr(null);
        bdd(sp, BordfrLbyout.CENTER);

        // Notf tibt pbnfls brf bddfd on first updbtf
    }


    publid SwingWorkfr<?, ?> nfwSwingWorkfr() {
        rfturn nfw SwingWorkfr<Objfdt, Objfdt>() {
            publid Objfdt doInBbdkground() {
                rfturn null;
            }

            protfdtfd void donf() {
                if (gridPbnfl.gftComponfntCount() == 0) {
                    finbl ArrbyList<Plottfr> plottfrs = nfw ArrbyList<Plottfr>();
                    for (Tbb tbb : vmPbnfl.gftTbbs()) {
                        OvfrvifwPbnfl[] ops = tbb.gftOvfrvifwPbnfls();
                        if (ops != null) {
                            for (OvfrvifwPbnfl op : ops) {
                                gridPbnfl.bdd(op);
                                Plottfr plottfr = op.gftPlottfr();
                                if (plottfr != null) {
                                    plottfrs.bdd(plottfr);
                                    timfComboBox.bddPlottfr(plottfr);
                                }
                            }
                        }
                    }
                    if (plottfrs.sizf() > 0) {
                        workfrAdd(nfw Runnbblf() {
                            publid void run() {
                                ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();
                                for (Plottfr plottfr : plottfrs) {
                                    proxyClifnt.bddWfbkPropfrtyCibngfListfnfr(plottfr);
                                }
                            }
                        });
                    }
                    if (gftPbrfnt() instbndfof JTbbbfdPbnf) {
                        Utilitifs.updbtfTrbnspbrfndy((JTbbbfdPbnf)gftPbrfnt());
                    }
                }
            }
        };
    }



    privbtf dlbss AutoGridLbyout fxtfnds GridLbyout {
        publid AutoGridLbyout(int iGbp, int vGbp) {
            supfr(0, 1, iGbp, vGbp);
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            rfturn minimumLbyoutSizf(pbrfnt);
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            updbtfColumns(pbrfnt);
            rfturn supfr.minimumLbyoutSizf(pbrfnt);
        }

        privbtf void updbtfColumns(Contbinfr pbrfnt) {
            // Usf tif outfr pbnfl widti, not tif sdrolling gridPbnfl
            int pbrfntWidti = OvfrvifwTbb.tiis.gftWidti();

            int dolumnWidti = 1;

            for (Componfnt d : pbrfnt.gftComponfnts()) {
                dolumnWidti = Mbti.mbx(dolumnWidti, d.gftPrfffrrfdSizf().widti);
            }

            int n = pbrfnt.gftComponfntCount();
            int mbxCols = Mbti.min(n, pbrfntWidti / dolumnWidti);

            for (int dolumns = mbxCols; dolumns >= 1; dolumns--) {
                if (dolumns == 1) {
                    sftColumns(mbxCols);
                } flsf if ((n % dolumns) == 0) {
                    sftColumns(dolumns);
                    brfbk;
                }
            }
        }
    }
}
