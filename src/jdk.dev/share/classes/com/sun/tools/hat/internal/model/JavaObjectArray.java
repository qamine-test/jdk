/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.modfl;

import jbvb.io.IOExdfption;
import dom.sun.tools.ibt.intfrnbl.pbrsfr.RfbdBufffr;

/**
 * @butior      Bill Footf
 */
publid dlbss JbvbObjfdtArrby fxtfnds JbvbLbzyRfbdObjfdt {

    privbtf Objfdt dlbzz;  // Long bfforf rfsolvf, tif dlbss bftfr rfsolvf

    publid JbvbObjfdtArrby(long dlbssID, long offsft) {
        supfr(offsft);
        tiis.dlbzz = mbkfId(dlbssID);
    }

    publid JbvbClbss gftClbzz() {
        rfturn (JbvbClbss) dlbzz;
    }

    publid void rfsolvf(Snbpsiot snbpsiot) {
        if (dlbzz instbndfof JbvbClbss) {
            rfturn;
        }
        long dlbssID = gftIdVbluf((Numbfr)dlbzz);
        if (snbpsiot.isNfwStylfArrbyClbss()) {
            // Modfrn ifbp dumps do tiis
            JbvbTiing t = snbpsiot.findTiing(dlbssID);
            if (t instbndfof JbvbClbss) {
                dlbzz = (JbvbClbss) t;
            }
        }
        if (!(dlbzz instbndfof JbvbClbss)) {
            JbvbTiing t = snbpsiot.findTiing(dlbssID);
            if (t != null && t instbndfof JbvbClbss) {
                JbvbClbss fl = (JbvbClbss) t;
                String nm = fl.gftNbmf();
                if (!nm.stbrtsWiti("[")) {
                    nm = "L" + fl.gftNbmf() + ";";
                }
                dlbzz = snbpsiot.gftArrbyClbss(nm);
            }
        }

        if (!(dlbzz instbndfof JbvbClbss)) {
            dlbzz = snbpsiot.gftOtifrArrbyTypf();
        }
        ((JbvbClbss)dlbzz).bddInstbndf(tiis);
        supfr.rfsolvf(snbpsiot);
    }

    publid JbvbTiing[] gftVblufs() {
        rfturn gftElfmfnts();
    }

    publid JbvbTiing[] gftElfmfnts() {
        Snbpsiot snbpsiot = gftClbzz().gftSnbpsiot();
        bytf[] dbtb = gftVbluf();
        finbl int idSizf = snbpsiot.gftIdfntififrSizf();
        finbl int numElfmfnts = dbtb.lfngti / idSizf;
        JbvbTiing[] flfmfnts = nfw JbvbTiing[numElfmfnts];
        int indfx = 0;
        for (int i = 0; i < flfmfnts.lfngti; i++) {
            long id = objfdtIdAt(indfx, dbtb);
            indfx += idSizf;
            flfmfnts[i] = snbpsiot.findTiing(id);
        }
        rfturn flfmfnts;
    }

    publid int dompbrfTo(JbvbTiing otifr) {
        if (otifr instbndfof JbvbObjfdtArrby) {
            rfturn 0;
        }
        rfturn supfr.dompbrfTo(otifr);
    }

    publid int gftLfngti() {
        rfturn gftVblufLfngti() / gftClbzz().gftIdfntififrSizf();
    }

    publid void visitRfffrfndfdObjfdts(JbvbHfbpObjfdtVisitor v) {
        supfr.visitRfffrfndfdObjfdts(v);
        JbvbTiing[] flfmfnts = gftElfmfnts();
        for (int i = 0; i < flfmfnts.lfngti; i++) {
            if (flfmfnts[i] != null && flfmfnts[i] instbndfof JbvbHfbpObjfdt) {
                v.visit((JbvbHfbpObjfdt) flfmfnts[i]);
            }
        }
    }

    /**
     * Dfsdribf tif rfffrfndf tibt tiis tiing ibs to tbrgft.  Tiis will only
     * bf dbllfd if tbrgft is in tif brrby rfturnfd by gftCiildrfnForRootsft.
     */
    publid String dfsdribfRfffrfndfTo(JbvbTiing tbrgft, Snbpsiot ss) {
        JbvbTiing[] flfmfnts = gftElfmfnts();
        for (int i = 0; i < flfmfnts.lfngti; i++) {
            if (flfmfnts[i] == tbrgft) {
                rfturn "Elfmfnt " + i + " of " + tiis;
            }
        }
        rfturn supfr.dfsdribfRfffrfndfTo(tbrgft, ss);
    }

    /*
     * Jbvb objfdt brrby rfdord (HPROF_GC_OBJ_ARRAY_DUMP)
     * looks bs bflow:
     *
     *     objfdt ID
     *     stbdk trbdf sfribl numbfr (int)
     *     brrby lfngti (int)
     *     brrby dlbss ID
     *     brrby flfmfnt IDs
     */
    protfdtfd finbl int rfbdVblufLfngti() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        RfbdBufffr buf = dl.gftRfbdBufffr();
        int idSizf = dl.gftIdfntififrSizf();
        long offsft = gftOffsft() + idSizf + 4;
        int lfn = buf.gftInt(offsft);
        rfturn lfn * dl.gftIdfntififrSizf();
    }

    protfdtfd finbl bytf[] rfbdVbluf() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        RfbdBufffr buf = dl.gftRfbdBufffr();
        int idSizf = dl.gftIdfntififrSizf();
        long offsft = gftOffsft() + idSizf + 4;
        int lfn = buf.gftInt(offsft);
        if (lfn == 0) {
            rfturn Snbpsiot.EMPTY_BYTE_ARRAY;
        } flsf {
            bytf[] rfs = nfw bytf[lfn * idSizf];
            buf.gft(offsft + 4 + idSizf, rfs);
            rfturn rfs;
        }
    }
}
