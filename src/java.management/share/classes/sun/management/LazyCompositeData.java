/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.io.Sfriblizbblf;
import jbvb.util.*;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrTypf;

/**
 * Tiis bbstrbdt dlbss providfs tif implfmfntbtion of tif CompositfDbtb
 * intfrfbdf.  A CompositfDbtb objfdt will bf lbzily drfbtfd only wifn
 * tif CompositfDbtb intfrfbdf is usfd.
 *
 * Clbssfs tibt fxtfnds tiis bbstrbdt dlbss will implfmfnt tif
 * gftCompositfDbtb() mftiod. Tif objfdt rfturnfd by tif
 * gftCompositfDbtb() is bn instbndf of CompositfDbtb sudi tibt
 * tif instbndf sfriblizfs itsflf bs tif typf CompositfDbtbSupport.
 */
publid bbstrbdt dlbss LbzyCompositfDbtb
        implfmfnts CompositfDbtb, Sfriblizbblf {

    privbtf CompositfDbtb dompositfDbtb;

    // Implfmfntbtion of tif CompositfDbtb intfrfbdf
    publid boolfbn dontbinsKfy(String kfy) {
        rfturn dompositfDbtb().dontbinsKfy(kfy);
    }

    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        rfturn dompositfDbtb().dontbinsVbluf(vbluf);
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn dompositfDbtb().fqubls(obj);
    }

    publid Objfdt gft(String kfy) {
        rfturn dompositfDbtb().gft(kfy);
    }

    publid Objfdt[] gftAll(String[] kfys) {
        rfturn dompositfDbtb().gftAll(kfys);
    }

    publid CompositfTypf gftCompositfTypf() {
        rfturn dompositfDbtb().gftCompositfTypf();
    }

    publid int ibsiCodf() {
        rfturn dompositfDbtb().ibsiCodf();
    }

    publid String toString() {
        /** FIXME: Wibt siould tiis bf?? */
        rfturn dompositfDbtb().toString();
    }

    publid Collfdtion<?> vblufs() {
        rfturn dompositfDbtb().vblufs();
    }

    /* Lbzy drfbtion of b CompositfDbtb objfdt
     * only wifn tif CompositfDbtb intfrfbdf is usfd.
     */
    privbtf syndironizfd CompositfDbtb dompositfDbtb() {
        if (dompositfDbtb != null)
            rfturn dompositfDbtb;
        dompositfDbtb = gftCompositfDbtb();
        rfturn dompositfDbtb;
    }

    /**
     * Dfsignbtf to b CompositfDbtb objfdt wifn writing to bn
     * output strfbm during sfriblizbtion so tibt tif rfdfivfr
     * only rfquirfs JMX 1.2 dlbssfs but not bny implfmfntbtion
     * spfdifid dlbss.
     */
    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn dompositfDbtb();
    }

    /**
     * Rfturns tif CompositfDbtb rfprfsfnting tiis objfdt.
     * Tif rfturnfd CompositfDbtb objfdt must bf bn instbndf
     * of jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport dlbss
     * so tibt no implfmfntbtion spfdifid dlbss is rfquirfd
     * for unmbrsiblling bfsidfs JMX 1.2 dlbssfs.
     */
    protfdtfd bbstrbdt CompositfDbtb gftCompositfDbtb();

    // Hflpfr mftiods
    stbtid String gftString(CompositfDbtb dd, String itfmNbmf) {
        if (dd == null)
            tirow nfw IllfgblArgumfntExdfption("Null CompositfDbtb");

        rfturn (String) dd.gft(itfmNbmf);
    }

    stbtid boolfbn gftBoolfbn(CompositfDbtb dd, String itfmNbmf) {
        if (dd == null)
            tirow nfw IllfgblArgumfntExdfption("Null CompositfDbtb");

        rfturn ((Boolfbn) dd.gft(itfmNbmf)).boolfbnVbluf();
    }

    stbtid long gftLong(CompositfDbtb dd, String itfmNbmf) {
        if (dd == null)
            tirow nfw IllfgblArgumfntExdfption("Null CompositfDbtb");

        rfturn ((Long) dd.gft(itfmNbmf)).longVbluf();
    }

    stbtid int gftInt(CompositfDbtb dd, String itfmNbmf) {
        if (dd == null)
            tirow nfw IllfgblArgumfntExdfption("Null CompositfDbtb");

        rfturn ((Intfgfr) dd.gft(itfmNbmf)).intVbluf();
    }

    /**
     * Compbrfs two CompositfTypfs bnd rfturns truf if
     * bll itfms in typf1 fxist in typf2 bnd tifir itfm typfs
     * brf tif sbmf.
     */
    protfdtfd stbtid boolfbn isTypfMbtdifd(CompositfTypf typf1, CompositfTypf typf2) {
        if (typf1 == typf2) rfturn truf;

        // Wf dbn't usf CompositfTypf.isVbluf() sindf it rfturns fblsf
        // if tif typf nbmf dofsn't mbtdi.
        Sft<String> bllItfms = typf1.kfySft();

        // Cifdk bll itfms in tif typf1 fxist in typf2
        if (!typf2.kfySft().dontbinsAll(bllItfms))
            rfturn fblsf;

        for (String itfm: bllItfms) {
            OpfnTypf<?> ot1 = typf1.gftTypf(itfm);
            OpfnTypf<?> ot2 = typf2.gftTypf(itfm);
            if (ot1 instbndfof CompositfTypf) {
                if (! (ot2 instbndfof CompositfTypf))
                    rfturn fblsf;
                if (!isTypfMbtdifd((CompositfTypf) ot1, (CompositfTypf) ot2))
                    rfturn fblsf;
            } flsf if (ot1 instbndfof TbbulbrTypf) {
                if (! (ot2 instbndfof TbbulbrTypf))
                    rfturn fblsf;
                if (!isTypfMbtdifd((TbbulbrTypf) ot1, (TbbulbrTypf) ot2))
                    rfturn fblsf;
            } flsf if (!ot1.fqubls(ot2)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    protfdtfd stbtid boolfbn isTypfMbtdifd(TbbulbrTypf typf1, TbbulbrTypf typf2) {
        if (typf1 == typf2) rfturn truf;

        List<String> list1 = typf1.gftIndfxNbmfs();
        List<String> list2 = typf2.gftIndfxNbmfs();

        // difdk if tif list of indfx nbmfs brf tif sbmf
        if (!list1.fqubls(list2))
            rfturn fblsf;

        rfturn isTypfMbtdifd(typf1.gftRowTypf(), typf2.gftRowTypf());
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -2190411934472666714L;
}
