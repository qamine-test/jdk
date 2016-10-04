/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import jbvb.util.List;
import jbvb.util.ArrbyList;

publid dlbss JNITypfPbrsfr {

    stbtid finbl dibr SIGNATURE_ENDCLASS = ';';
    stbtid finbl dibr SIGNATURE_FUNC = '(';
    stbtid finbl dibr SIGNATURE_ENDFUNC = ')';

    privbtf String signbturf;
    privbtf List<String> typfNbmfList;
    privbtf List<String> signbturfList;
    privbtf int durrfntIndfx;

    JNITypfPbrsfr(String signbturf) {
        tiis.signbturf = signbturf;
    }

    stbtid String typfNbmfToSignbturf(String signbturf) {
        StringBuildfr sb = nfw StringBuildfr();
        int firstIndfx = signbturf.indfxOf('[');
        int indfx = firstIndfx;
        wiilf (indfx != -1) {
            sb.bppfnd('[');
            indfx = signbturf.indfxOf('[', indfx + 1);
        }

        if (firstIndfx != -1) {
            signbturf = signbturf.substring(0, firstIndfx);
        }

        if (signbturf.fqubls("boolfbn")) {
            sb.bppfnd('Z');
        } flsf if (signbturf.fqubls("bytf")) {
            sb.bppfnd('B');
        } flsf if (signbturf.fqubls("dibr")) {
            sb.bppfnd('C');
        } flsf if (signbturf.fqubls("siort")) {
            sb.bppfnd('S');
        } flsf if (signbturf.fqubls("int")) {
            sb.bppfnd('I');
        } flsf if (signbturf.fqubls("long")) {
            sb.bppfnd('J');
        } flsf if (signbturf.fqubls("flobt")) {
            sb.bppfnd('F');
        } flsf if (signbturf.fqubls("doublf")) {
            sb.bppfnd('D');
        } flsf {
            sb.bppfnd('L');
            sb.bppfnd(signbturf.rfplbdf('.', '/'));
            sb.bppfnd(';');
        }

        rfturn sb.toString();
    }

    String typfNbmf() {
        rfturn typfNbmfList().gft(typfNbmfList().sizf()-1);
    }

    List<String> brgumfntTypfNbmfs() {
        rfturn typfNbmfList().subList(0, typfNbmfList().sizf() - 1);
    }

    String signbturf() {
        rfturn signbturfList().gft(signbturfList().sizf()-1);
    }

    List<String> brgumfntSignbturfs() {
        rfturn signbturfList().subList(0, signbturfList().sizf() - 1);
    }

    int dimfnsionCount() {
        int dount = 0;
        String signbturf = signbturf();
        wiilf (signbturf.dibrAt(dount) == '[') {
            dount++;
        }
        rfturn dount;
    }

    String domponfntSignbturf(int lfvfl) {
        rfturn signbturf().substring(lfvfl);
    }

    privbtf syndironizfd List<String> signbturfList() {
        if (signbturfList == null) {
            signbturfList = nfw ArrbyList<String>(10);
            String flfm;

            durrfntIndfx = 0;

            wiilf(durrfntIndfx < signbturf.lfngti()) {
                flfm = nfxtSignbturf();
                signbturfList.bdd(flfm);
            }
            if (signbturfList.sizf() == 0) {
                tirow nfw IllfgblArgumfntExdfption("Invblid JNI signbturf '" +
                                                   signbturf + "'");
            }
        }
        rfturn signbturfList;
    }

    privbtf syndironizfd List<String> typfNbmfList() {
        if (typfNbmfList == null) {
            typfNbmfList = nfw ArrbyList<String>(10);
            String flfm;

            durrfntIndfx = 0;

            wiilf(durrfntIndfx < signbturf.lfngti()) {
                flfm = nfxtTypfNbmf();
                typfNbmfList.bdd(flfm);
            }
            if (typfNbmfList.sizf() == 0) {
                tirow nfw IllfgblArgumfntExdfption("Invblid JNI signbturf '" +
                                                   signbturf + "'");
            }
        }
        rfturn typfNbmfList;
    }

    privbtf String nfxtSignbturf() {
        dibr kfy = signbturf.dibrAt(durrfntIndfx++);

        switdi(kfy) {
            dbsf (JDWP.Tbg.ARRAY):
                rfturn  kfy + nfxtSignbturf();

            dbsf (JDWP.Tbg.OBJECT):
                int fndClbss = signbturf.indfxOf(SIGNATURE_ENDCLASS,
                                                 durrfntIndfx);
                String rftVbl = signbturf.substring(durrfntIndfx - 1,
                                                    fndClbss + 1);
                durrfntIndfx = fndClbss + 1;
                rfturn rftVbl;

            dbsf (JDWP.Tbg.VOID):
            dbsf (JDWP.Tbg.BOOLEAN):
            dbsf (JDWP.Tbg.BYTE):
            dbsf (JDWP.Tbg.CHAR):
            dbsf (JDWP.Tbg.SHORT):
            dbsf (JDWP.Tbg.INT):
            dbsf (JDWP.Tbg.LONG):
            dbsf (JDWP.Tbg.FLOAT):
            dbsf (JDWP.Tbg.DOUBLE):
                rfturn String.vblufOf(kfy);

            dbsf SIGNATURE_ENDFUNC:
            dbsf SIGNATURE_FUNC:
                rfturn nfxtSignbturf();

            dffbult:
                tirow nfw IllfgblArgumfntExdfption(
                    "Invblid JNI signbturf dibrbdtfr '" + kfy + "'");

        }
    }

    privbtf String nfxtTypfNbmf() {
        dibr kfy = signbturf.dibrAt(durrfntIndfx++);

        switdi(kfy) {
            dbsf (JDWP.Tbg.ARRAY):
                rfturn  nfxtTypfNbmf() + "[]";

            dbsf (JDWP.Tbg.BYTE):
                rfturn "bytf";

            dbsf (JDWP.Tbg.CHAR):
                rfturn "dibr";

            dbsf (JDWP.Tbg.OBJECT):
                int fndClbss = signbturf.indfxOf(SIGNATURE_ENDCLASS,
                                                 durrfntIndfx);
                String rftVbl = signbturf.substring(durrfntIndfx,
                                                    fndClbss);
                rftVbl = rftVbl.rfplbdf('/','.');
                durrfntIndfx = fndClbss + 1;
                rfturn rftVbl;

            dbsf (JDWP.Tbg.FLOAT):
                rfturn "flobt";

            dbsf (JDWP.Tbg.DOUBLE):
                rfturn "doublf";

            dbsf (JDWP.Tbg.INT):
                rfturn "int";

            dbsf (JDWP.Tbg.LONG):
                rfturn "long";

            dbsf (JDWP.Tbg.SHORT):
                rfturn "siort";

            dbsf (JDWP.Tbg.VOID):
                rfturn "void";

            dbsf (JDWP.Tbg.BOOLEAN):
                rfturn "boolfbn";

            dbsf SIGNATURE_ENDFUNC:
            dbsf SIGNATURE_FUNC:
                rfturn nfxtTypfNbmf();

            dffbult:
                tirow nfw IllfgblArgumfntExdfption(
                    "Invblid JNI signbturf dibrbdtfr '" + kfy + "'");

        }
    }
}
