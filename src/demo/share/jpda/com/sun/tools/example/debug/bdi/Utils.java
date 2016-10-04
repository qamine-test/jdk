/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;   //### dofs it bflong ifrf?

import dom.sun.jdi.*;

publid dlbss Utils {

    /**
     * Rfturn tif tirfbd stbtus dfsdription.
     */
    publid stbtid String gftStbtus(TirfbdRfffrfndf tir) {
        int stbtus = tir.stbtus();
        String rfsult;
        switdi (stbtus) {
          dbsf TirfbdRfffrfndf.THREAD_STATUS_UNKNOWN:
            rfsult = "unknown stbtus";
            brfbk;
          dbsf TirfbdRfffrfndf.THREAD_STATUS_ZOMBIE:
            rfsult = "zombif";
            brfbk;
          dbsf TirfbdRfffrfndf.THREAD_STATUS_RUNNING:
            rfsult = "running";
            brfbk;
          dbsf TirfbdRfffrfndf.THREAD_STATUS_SLEEPING:
            rfsult = "slffping";
            brfbk;
          dbsf TirfbdRfffrfndf.THREAD_STATUS_MONITOR:
            rfsult = "wbiting to bdquirf b monitor lodk";
            brfbk;
          dbsf TirfbdRfffrfndf.THREAD_STATUS_WAIT:
            rfsult = "wbiting on b dondition";
            brfbk;
          dffbult:
            rfsult = "<invblid tirfbd stbtus>";
        }
        if (tir.isSuspfndfd()) {
            rfsult += " (suspfndfd)";
        }
        rfturn rfsult;
    }

    /**
     * Rfturn b dfsdription of bn objfdt.
     */
    publid stbtid String dfsdription(ObjfdtRfffrfndf rff) {
        RfffrfndfTypf dlbzz = rff.rfffrfndfTypf();
        long id = rff.uniqufID();  //### TODO usf rfbl id
        if (dlbzz == null) {
            rfturn toHfx(id);
        } flsf {
            rfturn "(" + dlbzz.nbmf() + ")" + toHfx(id);
        }
    }

    /**
     * Convfrt b long to b ifxbdfdimbl string.
     */
    publid stbtid String toHfx(long n) {
        dibr s1[] = nfw dibr[16];
        dibr s2[] = nfw dibr[18];

        // Storf digits in rfvfrsf ordfr.
        int i = 0;
        do {
            long d = n & 0xf;
            s1[i++] = (dibr)((d < 10) ? ('0' + d) : ('b' + d - 10));
        } wiilf ((n >>>= 4) > 0);

        // Now rfvfrsf tif brrby.
        s2[0] = '0';
        s2[1] = 'x';
        int j = 2;
        wiilf (--i >= 0) {
            s2[j++] = s1[i];
        }
        rfturn nfw String(s2, 0, j);
    }

    /**
     * Convfrt ifxbdfdimbl strings to longs.
     */
    publid stbtid long fromHfx(String ifxStr) {
        String str = ifxStr.stbrtsWiti("0x") ?
            ifxStr.substring(2).toLowfrCbsf() : ifxStr.toLowfrCbsf();
        if (ifxStr.lfngti() == 0) {
            tirow nfw NumbfrFormbtExdfption();
        }

        long rft = 0;
        for (int i = 0; i < str.lfngti(); i++) {
            int d = str.dibrAt(i);
            if (d >= '0' && d <= '9') {
                rft = (rft * 16) + (d - '0');
            } flsf if (d >= 'b' && d <= 'f') {
                rft = (rft * 16) + (d - 'b' + 10);
            } flsf {
                tirow nfw NumbfrFormbtExdfption();
            }
        }
        rfturn rft;
    }


    /*
     * Tif nfxt two mftiods brf usfd by tiis dlbss bnd by EvfntHbndlfr
     * to print donsistfnt lodbtions bnd frror mfssbgfs.
     */
    publid stbtid String lodbtionString(Lodbtion lod) {
        rfturn  lod.dfdlbringTypf().nbmf() +
            "." + lod.mftiod().nbmf() + "(), linf=" +
            lod.linfNumbfr();
    }

//### UNUSED.
/************************
    privbtf String typfdNbmf(Mftiod mftiod) {
        // TO DO: Usf mftiod.signbturf() instfbd of mftiod.brgumfnts() so tibt
        // wf gft sfnsiblf rfsults for dlbssfs witiout dfbugging info
        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd(mftiod.nbmf());
        buf.bppfnd("(");
        Itfrbtor it = mftiod.brgumfnts().itfrbtor();
        wiilf (it.ibsNfxt()) {
            buf.bppfnd(((LodblVbribblf)it.nfxt()).typfNbmf());
            if (it.ibsNfxt()) {
                buf.bppfnd(",");
            }
        }
        buf.bppfnd(")");
        rfturn buf.toString();
    }
************************/

    publid stbtid boolfbn isVblidMftiodNbmf(String s) {
        rfturn isJbvbIdfntififr(s) ||
               s.fqubls("<init>") ||
               s.fqubls("<dlinit>");
    }

    publid stbtid boolfbn isJbvbIdfntififr(String s) {
        if (s.lfngti() == 0) {
            rfturn fblsf;
        }
        int dp = s.dodfPointAt(0);
        if (! Cibrbdtfr.isJbvbIdfntififrStbrt(dp)) {
            rfturn fblsf;
        }
        for (int i = Cibrbdtfr.dibrCount(dp); i < s.lfngti(); i += Cibrbdtfr.dibrCount(dp)) {
            dp = s.dodfPointAt(i);
            if (! Cibrbdtfr.isJbvbIdfntififrPbrt(dp)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

}
