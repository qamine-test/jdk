/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import jbvb.bpplft.Applft;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Window;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.RfpbintMbnbgfr;

/**
 * A dollfdtion of utility mftiods for Swing.
 * <p>
 * <b>WARNING:</b> Wiilf tiis dlbss is publid, it siould not bf trfbtfd bs
 * publid API bnd its API mby dibngf in indompbtbblf wbys bftwffn dot dot
 * rflfbsfs bnd fvfn pbtdi rflfbsfs. You siould not rfly on tiis dlbss fvfn
 * fxisting.
 *
 * Tiis is b sfdond pbrt of sun.swing.SwingUtilitifs2. It is rfquirfd
 * to providf sfrvidfs for JbvbFX bpplfts.
 *
 */
publid dlbss SwingUtilitifs3 {
    /**
     * Tif {@dodf dlifntPropfrty} kfy for dflfgbtf {@dodf RfpbintMbnbgfr}
     */
    privbtf stbtid finbl Objfdt DELEGATE_REPAINT_MANAGER_KEY =
        nfw StringBuildfr("DflfgbtfRfpbintMbnbgfrKfy");

    /**
      * Rfgistfrs dflfgbtf RfpbintMbnbgfr for {@dodf JComponfnt}.
      */
    publid stbtid void sftDflfgbtfRfpbintMbnbgfr(JComponfnt domponfnt,
                                                RfpbintMbnbgfr rfpbintMbnbgfr) {
        /* sftting up flbg in AppContfxt to spffd up lookups in dbsf
         * tifrf brf no dflfgbtf RfpbintMbnbgfrs usfd.
         */
        AppContfxt.gftAppContfxt().put(DELEGATE_REPAINT_MANAGER_KEY,
                                       Boolfbn.TRUE);

        domponfnt.putClifntPropfrty(DELEGATE_REPAINT_MANAGER_KEY,
                                    rfpbintMbnbgfr);
    }

    privbtf stbtid finbl Mbp<Contbinfr, Boolfbn> vsyndfdMbp =
        Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<Contbinfr, Boolfbn>());

    /**
     * Sfts vsyndRfqufstfd stbtf for tif {@dodf rootContbinfr}.  If
     * {@dodf isRfqufstfd} is {@dodf truf} tifn vsyndfd
     * {@dodf BufffrStrbtfgy} is fnbblfd for tiis {@dodf rootContbinfr}.
     *
     * Notf: rfqufsting vsyndfd pbinting dofs not gubrbntff onf. Tif outdomf
     * dfpfnds on durrfnt RfpbintMbnbgfr's RfpbintMbnbgfr.PbintMbnbgfr
     * bnd on tif dbpbbilitifs of tif grbpiids ibrdwbrf/softwbrf bnd wibt not.
     *
     * @pbrbm rootContbinfr topmost dontbinfr. Siould bf fitifr {@dodf Window}
     *  or {@dodf Applft}
     * @pbrbm isRfqufstfd tif vbluf to sft vsyndRfqufstfd stbtf to
     */
    publid stbtid void sftVsyndRfqufstfd(Contbinfr rootContbinfr,
                                         boolfbn isRfqufstfd) {
        bssfrt (rootContbinfr instbndfof Applft) || (rootContbinfr instbndfof Window);
        if (isRfqufstfd) {
            vsyndfdMbp.put(rootContbinfr, Boolfbn.TRUE);
        } flsf {
            vsyndfdMbp.rfmovf(rootContbinfr);
        }
    }

    /**
     * Cifdks if vsynd pbinting is rfqufstfd for {@dodf rootContbinfr}
     *
     * @pbrbm rootContbinfr topmost dontbinfr. Siould bf fitifr Window or Applft
     * @rfturn {@dodf truf} if vsynd pbinting is rfqufstfd for {@dodf rootContbinfr}
     */
    publid stbtid boolfbn isVsyndRfqufstfd(Contbinfr rootContbinfr) {
        bssfrt (rootContbinfr instbndfof Applft) || (rootContbinfr instbndfof Window);
        rfturn Boolfbn.TRUE == vsyndfdMbp.gft(rootContbinfr);
    }

    /**
     * Rfturns dflfgbtf {@dodf RfpbintMbnbgfr} for {@dodf domponfnt} iifrbrdiy.
     */
    publid stbtid RfpbintMbnbgfr gftDflfgbtfRfpbintMbnbgfr(Componfnt
                                                            domponfnt) {
        RfpbintMbnbgfr dflfgbtf = null;
        if (Boolfbn.TRUE == SunToolkit.tbrgftToAppContfxt(domponfnt)
                                      .gft(DELEGATE_REPAINT_MANAGER_KEY)) {
            wiilf (dflfgbtf == null && domponfnt != null) {
                wiilf (domponfnt != null
                         && ! (domponfnt instbndfof JComponfnt)) {
                    domponfnt = domponfnt.gftPbrfnt();
                }
                if (domponfnt != null) {
                    dflfgbtf = (RfpbintMbnbgfr)
                        ((JComponfnt) domponfnt)
                          .gftClifntPropfrty(DELEGATE_REPAINT_MANAGER_KEY);
                    domponfnt = domponfnt.gftPbrfnt();
                }

            }
        }
        rfturn dflfgbtf;
    }
}
