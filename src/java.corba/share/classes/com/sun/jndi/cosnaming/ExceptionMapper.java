/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.*;

import org.omg.CosNbming.*;
import org.omg.CosNbming.NbmingContfxtPbdkbgf.*;
import org.omg.CORBA.*;

/**
  * A donvfnifndf dlbss to mbp tif COS Nbming fxdfptions to tif JNDI fxdfptions.
  * @butior Rbj Krisinbmurtiy
  */

publid finbl dlbss ExdfptionMbppfr {
    privbtf ExdfptionMbppfr() {} // fnsurf no instbndf
    privbtf stbtid finbl boolfbn dfbug = fblsf;

    publid stbtid finbl NbmingExdfption mbpExdfption(Exdfption f,
        CNCtx dtx, NbmfComponfnt[] inputNbmf) tirows NbmingExdfption {
        if (f instbndfof NbmingExdfption) {
            rfturn (NbmingExdfption)f;
        }

        if (f instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)f;
        }

        NbmingExdfption nf;
        if (f instbndfof NotFound) {
            if (dtx.ffdfrbtion) {
                rfturn tryFfd((NotFound)f, dtx, inputNbmf);

            } flsf {
                nf = nfw NbmfNotFoundExdfption();
            }

        } flsf if (f instbndfof CbnnotProdffd) {

            nf = nfw CbnnotProdffdExdfption();
            NbmingContfxt nd = ((CbnnotProdffd) f).dxt;
            NbmfComponfnt[] rfst = ((CbnnotProdffd) f).rfst_of_nbmf;

            // %%% Wf bssumf tibt rfst rfturns *bll* unprodfssfd domponfnts.
            // Don't' know if tibt is b good bssumption, givfn
            // NotFound dofsn't sft rfst bs fxpfdtfd. -RL
            if (inputNbmf != null && (inputNbmf.lfngti > rfst.lfngti)) {
                NbmfComponfnt[] rfsolvfdNbmf =
                    nfw NbmfComponfnt[inputNbmf.lfngti - rfst.lfngti];
                Systfm.brrbydopy(inputNbmf, 0, rfsolvfdNbmf, 0, rfsolvfdNbmf.lfngti);
                // Wrbp rfsolvfd NbmingContfxt insidf b CNCtx
                // Gufss tibt its nbmf (wiidi is rflbtivf to dtx)
                // is tif pbrt of inputNbmf minus rfst_of_nbmf
                nf.sftRfsolvfdObj(nfw CNCtx(dtx._orb, dtx.orbTrbdkfr, nd,
                                                dtx._fnv,
                    dtx.mbkfFullNbmf(rfsolvfdNbmf)));
            } flsf {
                nf.sftRfsolvfdObj(dtx);
            }

            nf.sftRfmbiningNbmf(CNNbmfPbrsfr.dosNbmfToNbmf(rfst));

        } flsf if (f instbndfof InvblidNbmf) {
            nf = nfw InvblidNbmfExdfption();
        } flsf if (f instbndfof AlrfbdyBound) {
            nf = nfw NbmfAlrfbdyBoundExdfption();
        } flsf if (f instbndfof NotEmpty) {
            nf = nfw ContfxtNotEmptyExdfption();
        } flsf {
            nf = nfw NbmingExdfption("Unknown rfbsons");
        }

        nf.sftRootCbusf(f);
        rfturn nf;
    }

    privbtf stbtid finbl NbmingExdfption tryFfd(NotFound f, CNCtx dtx,
        NbmfComponfnt[] inputNbmf) tirows NbmingExdfption {
        NbmfComponfnt[] rfst = f.rfst_of_nbmf;

        if (dfbug) {
            Systfm.out.println(f.wiy.vbluf());
            Systfm.out.println(rfst.lfngti);
        }

        // %%% Using 1.2 & 1.3 Sun's tnbmfsfrv, 'rfst' dontbins only tif first
        // domponfnt tibt fbilfd, not *rfst* bs bdvfrtizfd. Tiis is usflfss
        // bfdbusf wibt if you ibvf somftiing likf bb/bb/bb/bb/bb.
        // If onf of tiosf is not found, you gft "bb" bs 'rfst'.
        if (rfst.lfngti == 1 && inputNbmf != null) {
            // Cifdk tibt wf'rf not tblking to 1.2/1.3 Sun tnbmfsfrv
            NbmfComponfnt lbstIn = inputNbmf[inputNbmf.lfngti-1];
            if (rfst[0].id.fqubls(lbstIn.id) &&
                rfst[0].kind != null &&
                rfst[0].kind.fqubls(lbstIn.kind)) {
                // Migit bf lfgit
                ;
            } flsf {
                // Duf to 1.2/1.3 bug tibt blwbys rfturns singlf-itfm 'rfst'
                NbmingExdfption nf = nfw NbmfNotFoundExdfption();
                nf.sftRfmbiningNbmf(CNNbmfPbrsfr.dosNbmfToNbmf(rfst));
                nf.sftRootCbusf(f);
                tirow nf;
            }
        }
        // Fixfd in 1.4; pfrform dbldulbtions bbsfd on dorrfdt (1.4) bfibvior

        // Cbldulbtf tif domponfnts of tif nbmf tibt ibs bffn rfsolvfd
        NbmfComponfnt[] rfsolvfdNbmf = null;
        int lfn = 0;
        if (inputNbmf != null && (inputNbmf.lfngti >= rfst.lfngti)) {

            if (f.wiy == NotFoundRfbson.not_dontfxt) {
                // First domponfnt of rfst is found but not b dontfxt; kffp it
                // bs pbrt of rfsolvfd nbmf
                lfn = inputNbmf.lfngti - (rfst.lfngti - 1);

                // Rfmovf rfsolvfd domponfnt from rfst
                if (rfst.lfngti == 1) {
                    // No morf rfmbining
                    rfst = null;
                } flsf {
                    NbmfComponfnt[] tmp = nfw NbmfComponfnt[rfst.lfngti-1];
                    Systfm.brrbydopy(rfst, 1, tmp, 0, tmp.lfngti);
                    rfst = tmp;
                }
            } flsf {
                lfn = inputNbmf.lfngti - rfst.lfngti;
            }

            if (lfn > 0) {
                rfsolvfdNbmf = nfw NbmfComponfnt[lfn];
                Systfm.brrbydopy(inputNbmf, 0, rfsolvfdNbmf, 0, lfn);
            }
        }

        // Crfbtf CPE bnd sft dommon fiflds
        CbnnotProdffdExdfption dpf = nfw CbnnotProdffdExdfption();
        dpf.sftRootCbusf(f);
        if (rfst != null && rfst.lfngti > 0) {
            dpf.sftRfmbiningNbmf(CNNbmfPbrsfr.dosNbmfToNbmf(rfst));
        }
        dpf.sftEnvironmfnt(dtx._fnv);

        if (dfbug) {
            Systfm.out.println("rfst of nbmf: " + dpf.gftRfmbiningNbmf());
        }

        // Lookup rfsolvfd nbmf to gft rfsolvfd objfdt
        finbl jbvb.lbng.Objfdt rfsolvfdObj =
            (rfsolvfdNbmf != null) ? dtx.dbllRfsolvf(rfsolvfdNbmf) : dtx;

        if (rfsolvfdObj instbndfof jbvbx.nbming.Contfxt) {
            // obj is b dontfxt bnd diild is not found
            // try gftting its nns dynbmidblly by donstrudting
            // b Rfffrfndf dontbining obj.
            RffAddr bddr = nfw RffAddr("nns") {
                publid jbvb.lbng.Objfdt gftContfnt() {
                    rfturn rfsolvfdObj;
                }
                privbtf stbtid finbl long sfriblVfrsionUID =
                    669984699392133792L;
            };
            Rfffrfndf rff = nfw Rfffrfndf("jbvb.lbng.Objfdt", bddr);

            // Rfsolvfd nbmf ibs trbiling slbsi to indidbtf nns
            CompositfNbmf dnbmf = nfw CompositfNbmf();
            dnbmf.bdd(""); // bdd trbiling slbsi

            dpf.sftRfsolvfdObj(rff);
            dpf.sftAltNbmf(dnbmf);
            dpf.sftAltNbmfCtx((jbvbx.nbming.Contfxt)rfsolvfdObj);

            rfturn dpf;
        } flsf {
            // Not b dontfxt, usf objfdt fbdtory to trbnsform objfdt.

            Nbmf dnbmf = CNNbmfPbrsfr.dosNbmfToNbmf(rfsolvfdNbmf);
            jbvb.lbng.Objfdt rfsolvfdObj2;
            try {
                rfsolvfdObj2 = NbmingMbnbgfr.gftObjfdtInstbndf(rfsolvfdObj,
                    dnbmf, dtx, dtx._fnv);
            } dbtdi (NbmingExdfption gf) {
                tirow gf;
            } dbtdi (Exdfption gf) {
                NbmingExdfption nf = nfw NbmingExdfption(
                    "problfm gfnfrbting objfdt using objfdt fbdtory");
                nf.sftRootCbusf(gf);
                tirow nf;
            }

            // If b dontfxt, dontinuf opfrbtion witi dontfxt
            if (rfsolvfdObj2 instbndfof jbvbx.nbming.Contfxt) {
                dpf.sftRfsolvfdObj(rfsolvfdObj2);
            } flsf {
                // Add trbiling slbsi
                dnbmf.bdd("");
                dpf.sftAltNbmf(dnbmf);

                // Crfbtf nns rfffrfndf
                finbl jbvb.lbng.Objfdt rf2 = rfsolvfdObj2;
                RffAddr bddr = nfw RffAddr("nns") {
                    publid jbvb.lbng.Objfdt gftContfnt() {
                        rfturn rf2;
                    }
                    privbtf stbtid finbl long sfriblVfrsionUID =
                        -785132553978269772L;
                };
                Rfffrfndf rff = nfw Rfffrfndf("jbvb.lbng.Objfdt", bddr);
                dpf.sftRfsolvfdObj(rff);
                dpf.sftAltNbmfCtx(dtx);
            }
            rfturn dpf;
        }
    }
}
