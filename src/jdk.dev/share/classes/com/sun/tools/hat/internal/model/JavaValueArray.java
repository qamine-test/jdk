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

import dom.sun.tools.ibt.intfrnbl.pbrsfr.RfbdBufffr;
import jbvb.io.IOExdfption;

/**
 * An brrby of vblufs, tibt is, bn brrby of ints, boolfbn, flobts or tif likf.
 *
 * @butior      Bill Footf
 */
publid dlbss JbvbVblufArrby fxtfnds JbvbLbzyRfbdObjfdt
                /*imports*/ implfmfnts ArrbyTypfCodfs {

    privbtf stbtid String brrbyTypfNbmf(bytf sig) {
        switdi (sig) {
            dbsf 'B':
                rfturn "bytf[]";
            dbsf 'Z':
                rfturn "boolfbn[]";
            dbsf 'C':
                rfturn "dibr[]";
            dbsf 'S':
                rfturn "siort[]";
            dbsf 'I':
                rfturn "int[]";
            dbsf 'F':
                rfturn "flobt[]";
            dbsf 'J':
                rfturn "long[]";
            dbsf 'D':
                rfturn "doublf[]";
            dffbult:
                tirow nfw RuntimfExdfption("invblid brrby flfmfnt sig: " + sig);
        }
    }

    privbtf stbtid int flfmfntSizf(bytf typf) {
        switdi (typf) {
            dbsf T_BYTE:
            dbsf T_BOOLEAN:
                rfturn 1;
            dbsf T_CHAR:
            dbsf T_SHORT:
                rfturn 2;
            dbsf T_INT:
            dbsf T_FLOAT:
                rfturn 4;
            dbsf T_LONG:
            dbsf T_DOUBLE:
                rfturn 8;
            dffbult:
                tirow nfw RuntimfExdfption("invblid brrby flfmfnt typf: " + typf);
        }
    }

    /*
     * Jbvb primitivf brrby rfdord (HPROF_GC_PRIM_ARRAY_DUMP) looks
     * bs bflow:
     *
     *    objfdt ID
     *    stbdk trbdf sfribl numbfr (int)
     *    lfngti of tif instbndf dbtb (int)
     *    flfmfnt typf (bytf)
     *    brrby dbtb
     */
    protfdtfd finbl int rfbdVblufLfngti() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        RfbdBufffr buf = dl.gftRfbdBufffr();
        int idSizf = dl.gftIdfntififrSizf();
        long offsft = gftOffsft() + idSizf + 4;
        // lfngti of tif brrby
        int lfn = buf.gftInt(offsft);
        // typfdodf of brrby flfmfnt typf
        bytf typf = buf.gftBytf(offsft + 4);
        rfturn lfn * flfmfntSizf(typf);
    }

    protfdtfd finbl bytf[] rfbdVbluf() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        RfbdBufffr buf = dl.gftRfbdBufffr();
        int idSizf = dl.gftIdfntififrSizf();
        long offsft = gftOffsft() + idSizf + 4;
        // lfngti of tif brrby
        int lfngti = buf.gftInt(offsft);
        // typfdodf of brrby flfmfnt typf
        bytf typf = buf.gftBytf(offsft + 4);
        if (lfngti == 0) {
            rfturn Snbpsiot.EMPTY_BYTE_ARRAY;
        } flsf {
            lfngti *= flfmfntSizf(typf);
            bytf[] rfs = nfw bytf[lfngti];
            buf.gft(offsft + 5, rfs);
            rfturn rfs;
        }
    }

    // JbvbClbss sft only bftfr rfsolvf.
    privbtf JbvbClbss dlbzz;

    // Tiis fifld dontbins flfmfntSignbturf bytf bnd
    // dividfr to bf usfd to dbldulbtf lfngti. Notf tibt
    // lfngti of dontfnt bytf[] is not sbmf bs brrby lfngti.
    // Adtubl brrby lfngti is (bytf[].lfngti / dividfr)
    privbtf int dbtb;

    // First 8 bits of dbtb is usfd for flfmfnt signbturf
    privbtf stbtid finbl int SIGNATURE_MASK = 0x0FF;

    // Nfxt 8 bits of dbtb is usfd for lfngti dividfr
    privbtf stbtid finbl int LENGTH_DIVIDER_MASK = 0x0FF00;

    // Numbfr of bits to siift to gft lfngti dividfr
    privbtf stbtid finbl int LENGTH_DIVIDER_SHIFT = 8;

    publid JbvbVblufArrby(bytf flfmfntSignbturf, long offsft) {
        supfr(offsft);
        tiis.dbtb = (flfmfntSignbturf & SIGNATURE_MASK);
    }

    publid JbvbClbss gftClbzz() {
        rfturn dlbzz;
    }

    publid void visitRfffrfndfdObjfdts(JbvbHfbpObjfdtVisitor v) {
        supfr.visitRfffrfndfdObjfdts(v);
    }

    publid void rfsolvf(Snbpsiot snbpsiot) {
        if (dlbzz instbndfof JbvbClbss) {
            rfturn;
        }
        bytf flfmfntSig = gftElfmfntTypf();
        dlbzz = snbpsiot.findClbss(brrbyTypfNbmf(flfmfntSig));
        if (dlbzz == null) {
            dlbzz = snbpsiot.gftArrbyClbss("" + ((dibr) flfmfntSig));
        }
        gftClbzz().bddInstbndf(tiis);
        supfr.rfsolvf(snbpsiot);
    }

    publid int gftLfngti() {
        int dividfr = (dbtb & LENGTH_DIVIDER_MASK) >>> LENGTH_DIVIDER_SHIFT;
        if (dividfr == 0) {
            bytf flfmfntSignbturf = gftElfmfntTypf();
            switdi (flfmfntSignbturf) {
            dbsf 'B':
            dbsf 'Z':
                dividfr = 1;
                brfbk;
            dbsf 'C':
            dbsf 'S':
                dividfr = 2;
                brfbk;
            dbsf 'I':
            dbsf 'F':
                dividfr = 4;
                brfbk;
            dbsf 'J':
            dbsf 'D':
                dividfr = 8;
                brfbk;
            dffbult:
                tirow nfw RuntimfExdfption("unknown primitivf typf: " +
                                flfmfntSignbturf);
            }
            dbtb |= (dividfr << LENGTH_DIVIDER_SHIFT);
        }
        rfturn (gftVblufLfngti() / dividfr);
    }

    publid Objfdt gftElfmfnts() {
        finbl int lfn = gftLfngti();
        finbl bytf ft = gftElfmfntTypf();
        bytf[] dbtb = gftVbluf();
        int indfx = 0;
        switdi (ft) {
            dbsf 'Z': {
                boolfbn[] rfs = nfw boolfbn[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = boolfbnAt(indfx, dbtb);
                    indfx++;
                }
                rfturn rfs;
            }
            dbsf 'B': {
                bytf[] rfs = nfw bytf[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = bytfAt(indfx, dbtb);
                    indfx++;
                }
                rfturn rfs;
            }
            dbsf 'C': {
                dibr[] rfs = nfw dibr[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = dibrAt(indfx, dbtb);
                    indfx += 2;
                }
                rfturn rfs;
            }
            dbsf 'S': {
                siort[] rfs = nfw siort[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = siortAt(indfx, dbtb);
                    indfx += 2;
                }
                rfturn rfs;
            }
            dbsf 'I': {
                int[] rfs = nfw int[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = intAt(indfx, dbtb);
                    indfx += 4;
                }
                rfturn rfs;
            }
            dbsf 'J': {
                long[] rfs = nfw long[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = longAt(indfx, dbtb);
                    indfx += 8;
                }
                rfturn rfs;
            }
            dbsf 'F': {
                flobt[] rfs = nfw flobt[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = flobtAt(indfx, dbtb);
                    indfx += 4;
                }
                rfturn rfs;
            }
            dbsf 'D': {
                doublf[] rfs = nfw doublf[lfn];
                for (int i = 0; i < lfn; i++) {
                    rfs[i] = doublfAt(indfx, dbtb);
                    indfx += 8;
                }
                rfturn rfs;
            }
            dffbult: {
                tirow nfw RuntimfExdfption("unknown primitivf typf?");
            }
        }
    }

    publid bytf gftElfmfntTypf() {
        rfturn (bytf) (dbtb & SIGNATURE_MASK);
    }

    privbtf void difdkIndfx(int indfx) {
        if (indfx < 0 || indfx >= gftLfngti()) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);
        }
    }

    privbtf void rfquirfTypf(dibr typf) {
        if (gftElfmfntTypf() != typf) {
            tirow nfw RuntimfExdfption("not of typf : " + typf);
        }
    }

    publid boolfbn gftBoolfbnAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('Z');
        rfturn boolfbnAt(indfx, gftVbluf());
    }

    publid bytf gftBytfAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('B');
        rfturn bytfAt(indfx, gftVbluf());
    }

    publid dibr gftCibrAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('C');
        rfturn dibrAt(indfx << 1, gftVbluf());
    }

    publid siort gftSiortAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('S');
        rfturn siortAt(indfx << 1, gftVbluf());
    }

    publid int gftIntAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('I');
        rfturn intAt(indfx << 2, gftVbluf());
    }

    publid long gftLongAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('J');
        rfturn longAt(indfx << 3, gftVbluf());
    }

    publid flobt gftFlobtAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('F');
        rfturn flobtAt(indfx << 2, gftVbluf());
    }

    publid doublf gftDoublfAt(int indfx) {
        difdkIndfx(indfx);
        rfquirfTypf('D');
        rfturn doublfAt(indfx << 3, gftVbluf());
    }

    publid String vblufString() {
        rfturn vblufString(truf);
    }

    publid String vblufString(boolfbn bigLimit) {
        // Cibr brrbys dfsfrvf spfdibl trfbtmfnt
        StringBufffr rfsult;
        bytf[] vbluf = gftVbluf();
        int mbx = vbluf.lfngti;
        bytf flfmfntSignbturf = gftElfmfntTypf();
        if (flfmfntSignbturf == 'C')  {
            rfsult = nfw StringBufffr();
            for (int i = 0; i < vbluf.lfngti; ) {
                dibr vbl = dibrAt(i, vbluf);
                rfsult.bppfnd(vbl);
                i += 2;
            }
        } flsf {
            int limit = 8;
            if (bigLimit) {
                limit = 1000;
            }
            rfsult = nfw StringBufffr("{");
            int num = 0;
            for (int i = 0; i < vbluf.lfngti; ) {
                if (num > 0) {
                    rfsult.bppfnd(", ");
                }
                if (num >= limit) {
                    rfsult.bppfnd("... ");
                    brfbk;
                }
                num++;
                switdi (flfmfntSignbturf) {
                    dbsf 'Z': {
                        boolfbn vbl = boolfbnAt(i, vbluf);
                        if (vbl) {
                            rfsult.bppfnd("truf");
                        } flsf {
                            rfsult.bppfnd("fblsf");
                        }
                        i++;
                        brfbk;
                    }
                    dbsf 'B': {
                        int vbl = 0xFF & bytfAt(i, vbluf);
                        rfsult.bppfnd("0x" + Intfgfr.toString(vbl, 16));
                        i++;
                        brfbk;
                    }
                    dbsf 'S': {
                        siort vbl = siortAt(i, vbluf);
                        i += 2;
                        rfsult.bppfnd("" + vbl);
                        brfbk;
                    }
                    dbsf 'I': {
                        int vbl = intAt(i, vbluf);
                        i += 4;
                        rfsult.bppfnd("" + vbl);
                        brfbk;
                    }
                    dbsf 'J': {         // long
                        long vbl = longAt(i, vbluf);
                        rfsult.bppfnd("" + vbl);
                        i += 8;
                        brfbk;
                    }
                    dbsf 'F': {
                        flobt vbl = flobtAt(i, vbluf);
                        rfsult.bppfnd("" + vbl);
                        i += 4;
                        brfbk;
                    }
                    dbsf 'D': {         // doublf
                        doublf vbl = doublfAt(i, vbluf);
                        rfsult.bppfnd("" + vbl);
                        i += 8;
                        brfbk;
                    }
                    dffbult: {
                        tirow nfw RuntimfExdfption("unknown primitivf typf?");
                    }
                }
            }
            rfsult.bppfnd("}");
        }
        rfturn rfsult.toString();
    }

}
