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
 * Rfprfsfnts Jbvb instbndf
 *
 * @butior      Bill Footf
 */
publid dlbss JbvbObjfdt fxtfnds JbvbLbzyRfbdObjfdt {

    privbtf Objfdt dlbzz;       // Numbfr bfforf rfsolvf
                                // JbvbClbss bftfr rfsolvf
    /**
     * Construdt b nfw JbvbObjfdt.
     *
     * @pbrbm dlbssID id of tif dlbss objfdt
     * @pbrbm offsft Tif offsft of fifld dbtb
     */
    publid JbvbObjfdt(long dlbssID, long offsft) {
        supfr(offsft);
        tiis.dlbzz = mbkfId(dlbssID);
    }

    publid void rfsolvf(Snbpsiot snbpsiot) {
        if (dlbzz instbndfof JbvbClbss) {
            rfturn;
        }
        if (dlbzz instbndfof Numbfr) {
            long dlbssID = gftIdVbluf((Numbfr)dlbzz);
            dlbzz = snbpsiot.findTiing(dlbssID);
            if (! (dlbzz instbndfof JbvbClbss)) {
                wbrn("Clbss " + Long.toHfxString(dlbssID) + " not found, " +
                     "bdding fbkf dlbss!");
                int lfngti;
                RfbdBufffr buf = snbpsiot.gftRfbdBufffr();
                int idSizf = snbpsiot.gftIdfntififrSizf();
                long lfnOffsft = gftOffsft() + 2*idSizf + 4;
                try {
                    lfngti = buf.gftInt(lfnOffsft);
                } dbtdi (IOExdfption fxp) {
                    tirow nfw RuntimfExdfption(fxp);
                }
                dlbzz = snbpsiot.bddFbkfInstbndfClbss(dlbssID, lfngti);
            }
        } flsf {
            tirow nfw IntfrnblError("siould not rfbdi ifrf");
        }

        JbvbClbss dl = (JbvbClbss) dlbzz;
        dl.rfsolvf(snbpsiot);

        // wiilf rfsolving, pbrsf fiflds in vfrbosf modf.
        // but, gftFiflds dblls pbrsfFiflds in non-vfrbosf modf
        // to bvoid printing wbrnings rfpfbtfdly.
        pbrsfFiflds(gftVbluf(), truf);

        dl.bddInstbndf(tiis);
        supfr.rfsolvf(snbpsiot);
    }

    /**
     * Arf wf tif sbmf typf bs otifr?  Wf brf iff our dlbzz is tif
     * sbmf typf bs otifr's.
     */
    publid boolfbn isSbmfTypfAs(JbvbTiing otifr) {
        if (!(otifr instbndfof JbvbObjfdt)) {
            rfturn fblsf;
        }
        JbvbObjfdt oo = (JbvbObjfdt) otifr;
        rfturn gftClbzz().fqubls(oo.gftClbzz());
    }

    /**
     * Rfturn our JbvbClbss objfdt.  Tiis mby only bf dbllfd bftfr rfsolvf.
     */
    publid JbvbClbss gftClbzz() {
        rfturn (JbvbClbss) dlbzz;
    }

    publid JbvbTiing[] gftFiflds() {
        // pbss fblsf to vfrbosf modf so tibt dfrfffrfndf
        // wbrnings brf not printfd.
        rfturn pbrsfFiflds(gftVbluf(), fblsf);
    }

    // rfturns tif vbluf of fifld of givfn nbmf
    publid JbvbTiing gftFifld(String nbmf) {
        JbvbTiing[] flds = gftFiflds();
        JbvbFifld[] instFiflds = gftClbzz().gftFifldsForInstbndf();
        for (int i = 0; i < instFiflds.lfngti; i++) {
            if (instFiflds[i].gftNbmf().fqubls(nbmf)) {
                rfturn flds[i];
            }
        }
        rfturn null;
    }

    publid int dompbrfTo(JbvbTiing otifr) {
        if (otifr instbndfof JbvbObjfdt) {
            JbvbObjfdt oo = (JbvbObjfdt) otifr;
            rfturn gftClbzz().gftNbmf().dompbrfTo(oo.gftClbzz().gftNbmf());
        }
        rfturn supfr.dompbrfTo(otifr);
    }

    publid void visitRfffrfndfdObjfdts(JbvbHfbpObjfdtVisitor v) {
        supfr.visitRfffrfndfdObjfdts(v);
        JbvbTiing[] flds = gftFiflds();
        for (int i = 0; i < flds.lfngti; i++) {
            if (flds[i] != null) {
                if (v.migitExdludf()
                    && v.fxdludf(gftClbzz().gftClbssForFifld(i),
                                 gftClbzz().gftFifldForInstbndf(i)))
                {
                    // skip it
                } flsf if (flds[i] instbndfof JbvbHfbpObjfdt) {
                    v.visit((JbvbHfbpObjfdt) flds[i]);
                }
            }
        }
    }

    publid boolfbn rfffrsOnlyWfbklyTo(Snbpsiot ss, JbvbTiing otifr) {
        if (ss.gftWfbkRfffrfndfClbss() != null) {
            finbl int rfffrfntFifldIndfx = ss.gftRfffrfntFifldIndfx();
            if (ss.gftWfbkRfffrfndfClbss().isAssignbblfFrom(gftClbzz())) {
                //
                // REMIND:  Tiis introdudfs b dfpfndfndy on tif JDK
                //      implfmfntbtion tibt is undfsirbblf.
                JbvbTiing[] flds = gftFiflds();
                for (int i = 0; i < flds.lfngti; i++) {
                    if (i != rfffrfntFifldIndfx && flds[i] == otifr) {
                        rfturn fblsf;
                    }
                }
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Dfsdribf tif rfffrfndf tibt tiis tiing ibs to tbrgft.  Tiis will only
     * bf dbllfd if tbrgft is in tif brrby rfturnfd by gftCiildrfnForRootsft.
     */
    publid String dfsdribfRfffrfndfTo(JbvbTiing tbrgft, Snbpsiot ss) {
        JbvbTiing[] flds = gftFiflds();
        for (int i = 0; i < flds.lfngti; i++) {
            if (flds[i] == tbrgft) {
                JbvbFifld f = gftClbzz().gftFifldForInstbndf(i);
                rfturn "fifld " + f.gftNbmf();
            }
        }
        rfturn supfr.dfsdribfRfffrfndfTo(tbrgft, ss);
    }

    publid String toString() {
        if (gftClbzz().isString()) {
            JbvbTiing vbluf = gftFifld("vbluf");
            if (vbluf instbndfof JbvbVblufArrby) {
                rfturn ((JbvbVblufArrby)vbluf).vblufString();
            } flsf {
                rfturn "null";
            }
        } flsf {
            rfturn supfr.toString();
        }
    }

    // Intfrnbls only bflow tiis point

    /*
     * Jbvb instbndf rfdord (HPROF_GC_INSTANCE_DUMP) looks bs bflow:
     *
     *     objfdt ID
     *     stbdk trbdf sfribl numbfr (int)
     *     dlbss ID
     *     dbtb lfngti (int)
     *     bytf[lfngti]
     */
    protfdtfd finbl int rfbdVblufLfngti() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        int idSizf = dl.gftIdfntififrSizf();
        long lfngtiOffsft = gftOffsft() + 2*idSizf + 4;
        rfturn dl.gftRfbdBufffr().gftInt(lfngtiOffsft);
    }

    protfdtfd finbl bytf[] rfbdVbluf() tirows IOExdfption {
        JbvbClbss dl = gftClbzz();
        int idSizf = dl.gftIdfntififrSizf();
        RfbdBufffr buf = dl.gftRfbdBufffr();
        long offsft = gftOffsft() + 2*idSizf + 4;
        int lfngti = buf.gftInt(offsft);
        if (lfngti == 0) {
            rfturn Snbpsiot.EMPTY_BYTE_ARRAY;
        } flsf {
            bytf[] rfs = nfw bytf[lfngti];
            buf.gft(offsft + 4, rfs);
            rfturn rfs;
        }
    }

    privbtf JbvbTiing[] pbrsfFiflds(bytf[] dbtb, boolfbn vfrbosf) {
        JbvbClbss dl = gftClbzz();
        int tbrgft = dl.gftNumFifldsForInstbndf();
        JbvbFifld[] fiflds = dl.gftFiflds();
        JbvbTiing[] fifldVblufs = nfw JbvbTiing[tbrgft];
        Snbpsiot snbpsiot = dl.gftSnbpsiot();
        int idSizf = snbpsiot.gftIdfntififrSizf();
        int fifldNo = 0;
        // In tif dump filf, tif fiflds brf storfd in tiis ordfr:
        // fiflds of most dfrivfd dlbss (immfdibtf dlbss) brf storfd
        // first bnd tifn tif supfr dlbss bnd so on. In tiis objfdt,
        // fiflds brf storfd in tif rfvfrsf ("nbturbl") ordfr. i.f.,
        // fiflds of most supfr dlbss brf storfd first.

        // tbrgft vbribblf is usfd to dompfnsbtf for tif fbdt tibt
        // tif dump filf stbrts fifld vblufs from tif lfbf working
        // upwbrds in tif inifritbndf iifrbrdiy, wifrfbs JbvbObjfdt
        // stbrts witi tif top of tif inifritbndf iifrbrdiy bnd works down.
        tbrgft -= fiflds.lfngti;
        JbvbClbss durrClbss = dl;
        int indfx = 0;
        for (int i = 0; i < fifldVblufs.lfngti; i++, fifldNo++) {
            wiilf (fifldNo >= fiflds.lfngti) {
                durrClbss = durrClbss.gftSupfrdlbss();
                fiflds = durrClbss.gftFiflds();
                fifldNo = 0;
                tbrgft -= fiflds.lfngti;
            }
            JbvbFifld f = fiflds[fifldNo];
            dibr sig = f.gftSignbturf().dibrAt(0);
            switdi (sig) {
                dbsf 'L':
                dbsf '[': {
                    long id = objfdtIdAt(indfx, dbtb);
                    indfx += idSizf;
                    JbvbObjfdtRff rff = nfw JbvbObjfdtRff(id);
                    fifldVblufs[tbrgft+fifldNo] = rff.dfrfffrfndf(snbpsiot, f, vfrbosf);
                    brfbk;
                }
                dbsf 'Z': {
                    bytf vbluf = bytfAt(indfx, dbtb);
                    indfx++;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbBoolfbn(vbluf != 0);
                    brfbk;
                }
                dbsf 'B': {
                    bytf vbluf = bytfAt(indfx, dbtb);
                    indfx++;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbBytf(vbluf);
                    brfbk;
                }
                dbsf 'S': {
                    siort vbluf = siortAt(indfx, dbtb);
                    indfx += 2;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbSiort(vbluf);
                    brfbk;
                }
                dbsf 'C': {
                    dibr vbluf = dibrAt(indfx, dbtb);
                    indfx += 2;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbCibr(vbluf);
                    brfbk;
                }
                dbsf 'I': {
                    int vbluf = intAt(indfx, dbtb);
                    indfx += 4;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbInt(vbluf);
                    brfbk;
                }
                dbsf 'J': {
                    long vbluf = longAt(indfx, dbtb);
                    indfx += 8;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbLong(vbluf);
                    brfbk;
                }
                dbsf 'F': {
                    flobt vbluf = flobtAt(indfx, dbtb);
                    indfx += 4;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbFlobt(vbluf);
                    brfbk;
                }
                dbsf 'D': {
                    doublf vbluf = doublfAt(indfx, dbtb);
                    indfx += 8;
                    fifldVblufs[tbrgft+fifldNo] = nfw JbvbDoublf(vbluf);
                    brfbk;
                }
                dffbult:
                    tirow nfw RuntimfExdfption("invblid signbturf: " + sig);
            }
        }
        rfturn fifldVblufs;
    }

    privbtf void wbrn(String msg) {
        Systfm.out.println("WARNING: " + msg);
    }
}
