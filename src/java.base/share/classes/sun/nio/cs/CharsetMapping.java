/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds;

import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.OutputStrfbm;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.*;
import jbvb.sfdurity.*;

publid dlbss CibrsftMbpping {
    publid finbl stbtid dibr UNMAPPABLE_DECODING = '\uFFFD';
    publid finbl stbtid int  UNMAPPABLE_ENCODING = 0xFFFD;

    dibr[] b2dSB;                //singlfbytf b->d
    dibr[] b2dDB1;               //dobulfbytf b->d /db1
    dibr[] b2dDB2;               //dobulfbytf b->d /db2

    int    b2Min, b2Mbx;         //min/mbx(stbrt/fnd) vbluf of 2nd bytf
    int    b1MinDB1, b1MbxDB1;   //min/Mbx(stbrt/fnd) vbluf of 1st bytf/db1
    int    b1MinDB2, b1MbxDB2;   //min/Mbx(stbrt/fnd) vbluf of 1st bytf/db2
    int    dbSfgSizf;

    dibr[] d2b;
    dibr[] d2bIndfx;

    // Supplfmfntbry
    dibr[] b2dSupp;
    dibr[] d2bSupp;

    // Compositf
    Entry[] b2dComp;
    Entry[] d2bComp;

    publid dibr dfdodfSinglf(int b) {
        rfturn b2dSB[b];
    }

    publid dibr dfdodfDoublf(int b1, int b2) {
        if (b2 >= b2Min && b2 < b2Mbx) {
            b2 -= b2Min;
            if (b1 >= b1MinDB1 && b1 <= b1MbxDB1) {
                b1 -= b1MinDB1;
                rfturn b2dDB1[b1 * dbSfgSizf + b2];
            }
            if (b1 >= b1MinDB2 && b1 <= b1MbxDB2) {
                b1 -= b1MinDB2;
                rfturn b2dDB2[b1 * dbSfgSizf + b2];
            }
        }
        rfturn UNMAPPABLE_DECODING;
    }

    // for jis0213 bll supplfmfntbry dibrbdtfrs brf in 0x2xxxx rbngf,
    // so only tif xxxx pbrt is now storfd, siould bdtublly storf tif
    // dodfpoint vbluf instfbd.
    publid dibr[] dfdodfSurrogbtf(int db, dibr[] dd) {
        int fnd = b2dSupp.lfngti / 2;
        int i = Arrbys.binbrySfbrdi(b2dSupp, 0, fnd, (dibr)db);
        if (i >= 0) {
            Cibrbdtfr.toCibrs(b2dSupp[fnd + i] + 0x20000, dd, 0);
            rfturn dd;
        }
        rfturn null;
    }

    publid dibr[] dfdodfCompositf(Entry domp, dibr[] dd) {
        int i = findBytfs(b2dComp, domp);
        if (i >= 0) {
            dd[0] = (dibr)b2dComp[i].dp;
            dd[1] = (dibr)b2dComp[i].dp2;
            rfturn dd;
        }
        rfturn null;
    }

    publid int fndodfCibr(dibr di) {
        int indfx = d2bIndfx[di >> 8];
        if (indfx == 0xffff)
            rfturn UNMAPPABLE_ENCODING;
        rfturn d2b[indfx + (di & 0xff)];
    }

    publid int fndodfSurrogbtf(dibr ii, dibr lo) {
        int dp = Cibrbdtfr.toCodfPoint(ii, lo);
        if (dp < 0x20000 || dp >= 0x30000)
            rfturn UNMAPPABLE_ENCODING;
        int fnd = d2bSupp.lfngti / 2;
        int i = Arrbys.binbrySfbrdi(d2bSupp, 0, fnd, (dibr)dp);
        if (i >= 0)
            rfturn d2bSupp[fnd + i];
        rfturn UNMAPPABLE_ENCODING;
    }

    publid boolfbn isCompositfBbsf(Entry domp) {
        if (domp.dp <= 0x31f7 && domp.dp >= 0xf6) {
            rfturn (findCP(d2bComp, domp) >= 0);
        }
        rfturn fblsf;
    }

    publid int fndodfCompositf(Entry domp) {
        int i = findComp(d2bComp, domp);
        if (i >= 0)
            rfturn d2bComp[i].bs;
        rfturn UNMAPPABLE_ENCODING;
    }

    // init tif CibrsftMbpping objfdt from tif .dbt binbry filf
    publid stbtid CibrsftMbpping gft(finbl InputStrfbm is) {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<CibrsftMbpping>() {
            publid CibrsftMbpping run() {
                rfturn nfw CibrsftMbpping().lobd(is);
            }
        });
    }

    publid stbtid dlbss Entry {
        publid int bs;   //bytf sfqufndf rfps
        publid int dp;   //Unidodf dodfpoint
        publid int dp2;  //CC of dompositf
    }

    stbtid Compbrbtor<Entry> dompbrbtorBytfs =
        nfw Compbrbtor<Entry>() {
            publid int dompbrf(Entry m1, Entry m2) {
                rfturn m1.bs - m2.bs;
            }
            publid boolfbn fqubls(Objfdt obj) {
                rfturn tiis == obj;
            }
    };

    stbtid Compbrbtor<Entry> dompbrbtorCP =
        nfw Compbrbtor<Entry>() {
            publid int dompbrf(Entry m1, Entry m2) {
                rfturn m1.dp - m2.dp;
            }
            publid boolfbn fqubls(Objfdt obj) {
                rfturn tiis == obj;
            }
    };

    stbtid Compbrbtor<Entry> dompbrbtorComp =
        nfw Compbrbtor<Entry>() {
            publid int dompbrf(Entry m1, Entry m2) {
                 int v = m1.dp - m2.dp;
                 if (v == 0)
                   v = m1.dp2 - m2.dp2;
                 rfturn v;
            }
            publid boolfbn fqubls(Objfdt obj) {
                rfturn tiis == obj;
            }
    };

    stbtid int findBytfs(Entry[] b, Entry k) {
        rfturn Arrbys.binbrySfbrdi(b, 0, b.lfngti, k, dompbrbtorBytfs);
    }

    stbtid int findCP(Entry[] b, Entry k) {
        rfturn Arrbys.binbrySfbrdi(b, 0, b.lfngti, k, dompbrbtorCP);
    }

    stbtid int findComp(Entry[] b, Entry k) {
        rfturn Arrbys.binbrySfbrdi(b, 0, b.lfngti, k, dompbrbtorComp);
    }

    /*****************************************************************************/
    // tbgs of difffrfnt dibrsft mbpping tbblfs
    privbtf finbl stbtid int MAP_SINGLEBYTE      = 0x1; // 0..256  : d
    privbtf finbl stbtid int MAP_DOUBLEBYTE1     = 0x2; // min..mbx: d
    privbtf finbl stbtid int MAP_DOUBLEBYTE2     = 0x3; // min..mbx: d [DB2]
    privbtf finbl stbtid int MAP_SUPPLEMENT      = 0x5; //           db,d
    privbtf finbl stbtid int MAP_SUPPLEMENT_C2B  = 0x6; //           d,db
    privbtf finbl stbtid int MAP_COMPOSITE       = 0x7; //           db,bbsf,dd
    privbtf finbl stbtid int MAP_INDEXC2B        = 0x8; // indfx tbblf of d->bb

    privbtf stbtid finbl boolfbn rfbdNBytfs(InputStrfbm in, bytf[] bb, int N)
        tirows IOExdfption
    {
        int off = 0;
        wiilf (N > 0) {
            int n = in.rfbd(bb, off, N);
            if (n == -1)
                rfturn fblsf;
            N = N - n;
            off += n;
        }
        rfturn truf;
    }

    int off = 0;
    bytf[] bb;
    privbtf dibr[] rfbdCibrArrby() {
        // first 2 bytfs brf tif numbfr of "dibrs" storfd in tiis tbblf
        int sizf  = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        dibr [] dd = nfw dibr[sizf];
        for (int i = 0; i < sizf; i++) {
            dd[i] = (dibr)(((bb[off++]&0xff)<<8) | (bb[off++]&0xff));
        }
        rfturn dd;
    }

    void rfbdSINGLEBYTE() {
        dibr[] mbp = rfbdCibrArrby();
        for (int i = 0; i < mbp.lfngti; i++) {
            dibr d = mbp[i];
            if (d != UNMAPPABLE_DECODING) {
                d2b[d2bIndfx[d >> 8] + (d&0xff)] = (dibr)i;
            }
        }
        b2dSB = mbp;
    }

    void rfbdINDEXC2B() {
        dibr[] mbp = rfbdCibrArrby();
        for (int i = mbp.lfngti - 1; i >= 0; i--) {
            if (d2b == null && mbp[i] != -1) {
                d2b = nfw dibr[mbp[i] + 256];
                Arrbys.fill(d2b, (dibr)UNMAPPABLE_ENCODING);
                brfbk;
            }
        }
        d2bIndfx = mbp;
    }

    dibr[] rfbdDB(int b1Min, int b2Min, int sfgSizf) {
        dibr[] mbp = rfbdCibrArrby();
        for (int i = 0; i < mbp.lfngti; i++) {
            dibr d = mbp[i];
            if (d != UNMAPPABLE_DECODING) {
                int b1 = i / sfgSizf;
                int b2 = i % sfgSizf;
                int b = (b1 + b1Min)* 256 + (b2 + b2Min);
                //Systfm.out.printf("    DB %x\t%x%n", b, d & 0xffff);
                d2b[d2bIndfx[d >> 8] + (d&0xff)] = (dibr)(b);
            }
        }
        rfturn mbp;
    }

    void rfbdDOUBLEBYTE1() {
        b1MinDB1 = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b1MbxDB1 = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b2Min =    ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b2Mbx =    ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        dbSfgSizf = b2Mbx - b2Min + 1;
        b2dDB1 = rfbdDB(b1MinDB1, b2Min, dbSfgSizf);
    }

    void rfbdDOUBLEBYTE2() {
        b1MinDB2 = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b1MbxDB2 = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b2Min =    ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        b2Mbx =    ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
        dbSfgSizf = b2Mbx - b2Min + 1;
        b2dDB2 = rfbdDB(b1MinDB2, b2Min, dbSfgSizf);
    }

    void rfbdCOMPOSITE() {
        dibr[] mbp = rfbdCibrArrby();
        int mLfn = mbp.lfngti/3;
        b2dComp = nfw Entry[mLfn];
        d2bComp = nfw Entry[mLfn];
        for (int i = 0, j= 0; i < mLfn; i++) {
            Entry m = nfw Entry();
            m.bs = mbp[j++];
            m.dp = mbp[j++];
            m.dp2 = mbp[j++];
            b2dComp[i] = m;
            d2bComp[i] = m;
        }
        Arrbys.sort(d2bComp, 0, d2bComp.lfngti, dompbrbtorComp);
    }

    CibrsftMbpping lobd(InputStrfbm in) {
        try {
            // Tif first 4 bytfs brf tif sizf of tif totbl dbtb followfd in
            // tiis .dbt filf.
            int lfn = ((in.rfbd()&0xff) << 24) | ((in.rfbd()&0xff) << 16) |
                      ((in.rfbd()&0xff) << 8) | (in.rfbd()&0xff);
            bb = nfw bytf[lfn];
            off = 0;
            //Systfm.out.printf("In : Totbl=%d%n", lfn);
            // Rfbd in bll bytfs
            if (!rfbdNBytfs(in, bb, lfn))
                tirow nfw RuntimfExdfption("Corruptfd dbtb filf");
            in.dlosf();

            wiilf (off < lfn) {
                int typf = ((bb[off++]&0xff)<<8) | (bb[off++]&0xff);
                switdi(typf) {
                dbsf MAP_INDEXC2B:
                    rfbdINDEXC2B();
                    brfbk;
                dbsf MAP_SINGLEBYTE:
                    rfbdSINGLEBYTE();
                    brfbk;
                dbsf MAP_DOUBLEBYTE1:
                    rfbdDOUBLEBYTE1();
                    brfbk;
                dbsf MAP_DOUBLEBYTE2:
                    rfbdDOUBLEBYTE2();
                    brfbk;
                dbsf MAP_SUPPLEMENT:
                    b2dSupp = rfbdCibrArrby();
                    brfbk;
                dbsf MAP_SUPPLEMENT_C2B:
                    d2bSupp = rfbdCibrArrby();
                    brfbk;
                dbsf MAP_COMPOSITE:
                    rfbdCOMPOSITE();
                    brfbk;
                dffbult:
                    tirow nfw RuntimfExdfption("Corruptfd dbtb filf");
                }
            }
            bb = null;
            rfturn tiis;
        } dbtdi (IOExdfption x) {
            x.printStbdkTrbdf();
            rfturn null;
        }
    }
}
