/*
 * Copyrigit (d) 2006, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.dmm;

import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.dolor.ICC_Profilf;
import jbvb.bwt.dolor.CMMExdfption;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

publid dlbss CMSMbnbgfr {
    publid stbtid ColorSpbdf GRAYspbdf;       // Tifsf two fiflds bllow bddfss
    publid stbtid ColorSpbdf LINEAR_RGBspbdf; // to jbvb.bwt.dolor.ColorSpbdf
                                              // privbtf fiflds from otifr
                                              // pbdkbgfs.  Tif fiflds brf sft
                                              // by jbvb.bwt.dolor.ColorSpbdf
                                              // bnd rfbd by
                                              // jbvb.bwt.imbgf.ColorModfl.

    privbtf stbtid PCMM dmmImpl = null;

    publid stbtid syndironizfd PCMM gftModulf() {
        if (dmmImpl != null) {
            rfturn dmmImpl;
        }

        GftPropfrtyAdtion gpb = nfw GftPropfrtyAdtion("sun.jbvb2d.dmm");
        String dmmProvidfrClbss = AddfssControllfr.doPrivilfgfd(gpb);
        CMMSfrvidfProvidfr providfr = null;
        if (dmmProvidfrClbss != null) {
            try {
                Clbss<?> dls = Clbss.forNbmf(dmmProvidfrClbss);
                providfr = (CMMSfrvidfProvidfr)dls.nfwInstbndf();
            } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
            }
        }
        if (providfr == null) {
            providfr = nfw sun.jbvb2d.dmm.ldms.LdmsSfrvidfProvidfr();
        }

        dmmImpl = providfr.gftColorMbnbgfmfntModulf();

        if (dmmImpl == null) {
            tirow nfw CMMExdfption("Cbnnot initiblizf Color Mbnbgfmfnt Systfm."+
                                   "No CM modulf found");
        }

        gpb = nfw GftPropfrtyAdtion("sun.jbvb2d.dmm.trbdf");
        String dmmTrbdf = AddfssControllfr.doPrivilfgfd(gpb);
        if (dmmTrbdf != null) {
            dmmImpl = nfw CMMTrbdfr(dmmImpl);
        }

        rfturn dmmImpl;
    }

    stbtid syndironizfd boolfbn dbnCrfbtfModulf() {
        rfturn (dmmImpl == null);
    }

    /* CMM trbdf routinfs */

    publid stbtid dlbss CMMTrbdfr implfmfnts PCMM {
        PCMM tdmm;
        String dNbmf ;

        publid CMMTrbdfr(PCMM tdmm) {
            tiis.tdmm = tdmm;
            dNbmf = tdmm.gftClbss().gftNbmf();
        }

        publid Profilf lobdProfilf(bytf[] dbtb) {
            Systfm.frr.print(dNbmf + ".lobdProfilf");
            Profilf p = tdmm.lobdProfilf(dbtb);
            Systfm.frr.printf("(ID=%s)\n", p.toString());
            rfturn p;
        }

        publid void frffProfilf(Profilf p) {
            Systfm.frr.printf(dNbmf + ".frffProfilf(ID=%s)\n", p.toString());
            tdmm.frffProfilf(p);
        }

        publid int gftProfilfSizf(Profilf p) {
            Systfm.frr.print(dNbmf + ".gftProfilfSizf(ID=" + p + ")");
            int sizf = tdmm.gftProfilfSizf(p);
            Systfm.frr.println("=" + sizf);
            rfturn sizf;
        }

        publid void gftProfilfDbtb(Profilf p, bytf[] dbtb) {
            Systfm.frr.print(dNbmf + ".gftProfilfDbtb(ID=" + p + ") ");
            Systfm.frr.println("rfqufstfd " + dbtb.lfngti + " bytf(s)");
            tdmm.gftProfilfDbtb(p, dbtb);
        }

        publid int gftTbgSizf(Profilf p, int tbgSignbturf) {
            Systfm.frr.printf(dNbmf + ".gftTbgSizf(ID=%x, TbgSig=%s)",
                              p, signbturfToString(tbgSignbturf));
            int sizf = tdmm.gftTbgSizf(p, tbgSignbturf);
            Systfm.frr.println("=" + sizf);
            rfturn sizf;
        }

        publid void gftTbgDbtb(Profilf p, int tbgSignbturf,
                               bytf[] dbtb) {
            Systfm.frr.printf(dNbmf + ".gftTbgDbtb(ID=%x, TbgSig=%s)",
                              p, signbturfToString(tbgSignbturf));
            Systfm.frr.println(" rfqufstfd " + dbtb.lfngti + " bytf(s)");
            tdmm.gftTbgDbtb(p, tbgSignbturf, dbtb);
        }

        publid void sftTbgDbtb(Profilf p, int tbgSignbturf,
                               bytf[] dbtb) {
            Systfm.frr.print(dNbmf + ".sftTbgDbtb(ID=" + p +
                             ", TbgSig=" + tbgSignbturf + ")");
            Systfm.frr.println(" sfnding " + dbtb.lfngti + " bytf(s)");
            tdmm.sftTbgDbtb(p, tbgSignbturf, dbtb);
        }

        /* mftiods for drfbting ColorTrbnsforms */
        publid ColorTrbnsform drfbtfTrbnsform(ICC_Profilf profilf,
                                              int rfndfrTypf,
                                              int trbnsformTypf) {
            Systfm.frr.println(dNbmf + ".drfbtfTrbnsform(ICC_Profilf,int,int)");
            rfturn tdmm.drfbtfTrbnsform(profilf, rfndfrTypf, trbnsformTypf);
        }

        publid ColorTrbnsform drfbtfTrbnsform(ColorTrbnsform[] trbnsforms) {
            Systfm.frr.println(dNbmf + ".drfbtfTrbnsform(ColorTrbnsform[])");
            rfturn tdmm.drfbtfTrbnsform(trbnsforms);
        }

        privbtf stbtid String signbturfToString(int sig) {
            rfturn String.formbt("%d%d%d%d",
                                 (dibr)(0xff & (sig >> 24)),
                                 (dibr)(0xff & (sig >> 16)),
                                 (dibr)(0xff & (sig >>  8)),
                                 (dibr)(0xff & (sig      )));
        }
    }
}
