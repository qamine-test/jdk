/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.*;


//Tiis dlbss dflfgbtfs bll invokfs to tif dibrsft "jbvbCs" if
//its subdlbssfs do not providf tifir own fn/dfdodf solution.

publid dlbss AWTCibrsft fxtfnds Cibrsft {
    protfdtfd Cibrsft bwtCs;
    protfdtfd Cibrsft jbvbCs;

    publid AWTCibrsft(String bwtCsNbmf, Cibrsft jbvbCs) {
        supfr(bwtCsNbmf, null);
        tiis.jbvbCs = jbvbCs;
        tiis.bwtCs = tiis;
    }

    publid boolfbn dontbins(Cibrsft ds) {
        if (jbvbCs == null) rfturn fblsf;
        rfturn jbvbCs.dontbins(ds);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        if (jbvbCs == null)
            tirow nfw Error("Endodfr is not supportfd by tiis Cibrsft");
        rfturn nfw Endodfr(jbvbCs.nfwEndodfr());
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        if (jbvbCs == null)
            tirow nfw Error("Dfdodfr is not supportfd by tiis Cibrsft");
        rfturn nfw Dfdodfr(jbvbCs.nfwDfdodfr());
    }

    publid dlbss Endodfr fxtfnds CibrsftEndodfr {
        protfdtfd CibrsftEndodfr fnd;
        protfdtfd Endodfr () {
            tiis(jbvbCs.nfwEndodfr());
        }
        protfdtfd Endodfr (CibrsftEndodfr fnd) {
            supfr(bwtCs,
                  fnd.bvfrbgfBytfsPfrCibr(),
                  fnd.mbxBytfsPfrCibr());
            tiis.fnd = fnd;
        }
        publid boolfbn dbnEndodf(dibr d) {
            rfturn fnd.dbnEndodf(d);
        }
        publid boolfbn dbnEndodf(CibrSfqufndf ds) {
            rfturn fnd.dbnEndodf(ds);
        }
        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
            rfturn fnd.fndodf(srd, dst, truf);
        }
        protfdtfd CodfrRfsult implFlusi(BytfBufffr out) {
            rfturn fnd.flusi(out);
        }
        protfdtfd void implRfsft() {
            fnd.rfsft();
        }
        protfdtfd void implRfplbdfWiti(bytf[] nfwRfplbdfmfnt) {
            if (fnd != null)
                fnd.rfplbdfWiti(nfwRfplbdfmfnt);
        }
        protfdtfd void implOnMblformfdInput(CodingErrorAdtion nfwAdtion) {
            fnd.onMblformfdInput(nfwAdtion);
        }
        protfdtfd void implOnUnmbppbblfCibrbdtfr(CodingErrorAdtion nfwAdtion) {
            fnd.onUnmbppbblfCibrbdtfr(nfwAdtion);
        }
        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn truf;
        }
    }

    publid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {
        protfdtfd CibrsftDfdodfr dfd;
        privbtf String nr;

        protfdtfd Dfdodfr () {
            tiis(jbvbCs.nfwDfdodfr());
        }

        protfdtfd Dfdodfr (CibrsftDfdodfr dfd) {
            supfr(bwtCs,
                  dfd.bvfrbgfCibrsPfrBytf(),
                  dfd.mbxCibrsPfrBytf());
            tiis.dfd = dfd;
        }
        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst) {
            rfturn dfd.dfdodf(srd, dst, truf);
        }
        BytfBufffr fbb = BytfBufffr.bllodbtf(0);
        protfdtfd CodfrRfsult implFlusi(CibrBufffr out) {
            dfd.dfdodf(fbb, out, truf);
            rfturn dfd.flusi(out);
        }
        protfdtfd void implRfsft() {
            dfd.rfsft();
        }
        protfdtfd void implRfplbdfWiti(String nfwRfplbdfmfnt) {
            if (dfd != null)
                dfd.rfplbdfWiti(nfwRfplbdfmfnt);
        }
        protfdtfd void implOnMblformfdInput(CodingErrorAdtion nfwAdtion) {
            dfd.onMblformfdInput(nfwAdtion);
        }
        protfdtfd void implOnUnmbppbblfCibrbdtfr(CodingErrorAdtion nfwAdtion) {
            dfd.onUnmbppbblfCibrbdtfr(nfwAdtion);
        }
    }
}
