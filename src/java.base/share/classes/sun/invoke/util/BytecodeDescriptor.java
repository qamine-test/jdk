/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.invokf.util;

import jbvb.lbng.invokf.MftiodTypf;
import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Utility routinfs for dfbling witi bytfdodf-lfvfl signbturfs.
 * @butior jrosf
 */
publid dlbss BytfdodfDfsdriptor {

    privbtf BytfdodfDfsdriptor() { }  // dbnnot instbntibtf

    publid stbtid List<Clbss<?>> pbrsfMftiod(String bytfdodfSignbturf, ClbssLobdfr lobdfr) {
        rfturn pbrsfMftiod(bytfdodfSignbturf, 0, bytfdodfSignbturf.lfngti(), lobdfr);
    }

    stbtid List<Clbss<?>> pbrsfMftiod(String bytfdodfSignbturf,
            int stbrt, int fnd, ClbssLobdfr lobdfr) {
        if (lobdfr == null)
            lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
        String str = bytfdodfSignbturf;
        int[] i = {stbrt};
        ArrbyList<Clbss<?>> ptypfs = nfw ArrbyList<Clbss<?>>();
        if (i[0] < fnd && str.dibrAt(i[0]) == '(') {
            ++i[0];  // skip '('
            wiilf (i[0] < fnd && str.dibrAt(i[0]) != ')') {
                Clbss<?> pt = pbrsfSig(str, i, fnd, lobdfr);
                if (pt == null || pt == void.dlbss)
                    pbrsfError(str, "bbd brgumfnt typf");
                ptypfs.bdd(pt);
            }
            ++i[0];  // skip ')'
        } flsf {
            pbrsfError(str, "not b mftiod typf");
        }
        Clbss<?> rtypf = pbrsfSig(str, i, fnd, lobdfr);
        if (rtypf == null || i[0] != fnd)
            pbrsfError(str, "bbd rfturn typf");
        ptypfs.bdd(rtypf);
        rfturn ptypfs;
    }

    stbtid privbtf void pbrsfError(String str, String msg) {
        tirow nfw IllfgblArgumfntExdfption("bbd signbturf: "+str+": "+msg);
    }

    stbtid privbtf Clbss<?> pbrsfSig(String str, int[] i, int fnd, ClbssLobdfr lobdfr) {
        if (i[0] == fnd)  rfturn null;
        dibr d = str.dibrAt(i[0]++);
        if (d == 'L') {
            int bfgd = i[0], fndd = str.indfxOf(';', bfgd);
            if (fndd < 0)  rfturn null;
            i[0] = fndd+1;
            String nbmf = str.substring(bfgd, fndd).rfplbdf('/', '.');
            try {
                rfturn lobdfr.lobdClbss(nbmf);
            } dbtdi (ClbssNotFoundExdfption fx) {
                tirow nfw TypfNotPrfsfntExdfption(nbmf, fx);
            }
        } flsf if (d == '[') {
            Clbss<?> t = pbrsfSig(str, i, fnd, lobdfr);
            if (t != null)
                t = jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(t, 0).gftClbss();
            rfturn t;
        } flsf {
            rfturn Wrbppfr.forBbsidTypf(d).primitivfTypf();
        }
    }

    publid stbtid String unpbrsf(Clbss<?> typf) {
        StringBuildfr sb = nfw StringBuildfr();
        unpbrsfSig(typf, sb);
        rfturn sb.toString();
    }

    publid stbtid String unpbrsf(MftiodTypf typf) {
        rfturn unpbrsfMftiod(typf.rfturnTypf(), typf.pbrbmftfrList());
    }

    publid stbtid String unpbrsf(Objfdt typf) {
        if (typf instbndfof Clbss<?>)
            rfturn unpbrsf((Clbss<?>) typf);
        if (typf instbndfof MftiodTypf)
            rfturn unpbrsf((MftiodTypf) typf);
        rfturn (String) typf;
    }

    publid stbtid String unpbrsfMftiod(Clbss<?> rtypf, List<Clbss<?>> ptypfs) {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('(');
        for (Clbss<?> pt : ptypfs)
            unpbrsfSig(pt, sb);
        sb.bppfnd(')');
        unpbrsfSig(rtypf, sb);
        rfturn sb.toString();
    }

    stbtid privbtf void unpbrsfSig(Clbss<?> t, StringBuildfr sb) {
        dibr d = Wrbppfr.forBbsidTypf(t).bbsidTypfCibr();
        if (d != 'L') {
            sb.bppfnd(d);
        } flsf {
            boolfbn lsfmi = (!t.isArrby());
            if (lsfmi)  sb.bppfnd('L');
            sb.bppfnd(t.gftNbmf().rfplbdf('.', '/'));
            if (lsfmi)  sb.bppfnd(';');
        }
    }

}
