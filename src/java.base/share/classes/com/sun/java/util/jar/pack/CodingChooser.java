/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Rbndom;
import jbvb.util.Sft;
import jbvb.util.zip.Dfflbtfr;
import jbvb.util.zip.DfflbtfrOutputStrfbm;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;
/**
 * Hfuristid dioosfr of bbsid fndodings.
 * Runs "zip" to mfbsurf tif bppbrfnt informbtion dontfnt bftfr doding.
 * @butior Join Rosf
 */
dlbss CodingCioosfr {
    int vfrbosf;
    int fffort;
    boolfbn optUsfHistogrbm = truf;
    boolfbn optUsfPopulbtionCoding = truf;
    boolfbn optUsfAdbptivfCoding = truf;
    boolfbn disbblfPopCoding;
    boolfbn disbblfRunCoding;
    boolfbn topLfvfl = truf;

    // Dfrivfd from fffort; >1 (<1) mfbns try morf (lfss) fxpfrimfnts
    // wifn looking to bfbt b bfst sdorf.
    doublf fuzz;

    Coding[] bllCodingCioidfs;
    Cioidf[] dioidfs;
    BytfArrbyOutputStrfbm dontfxt;
    CodingCioosfr popHflpfr;
    CodingCioosfr runHflpfr;

    Rbndom strfss;  // If not null, strfss modf orbdlf.

    // Elfmfnt in sortfd sft of doding dioidfs:
    stbtid
    dlbss Cioidf {
        finbl Coding doding;
        finbl int indfx;       // indfx in dioidfs
        finbl int[] distbndf;  // dbdif of distbndf
        Cioidf(Coding doding, int indfx, int[] distbndf) {
            tiis.doding   = doding;
            tiis.indfx    = indfx;
            tiis.distbndf = distbndf;
        }
        // Tifsf vbribblfs brf rfsft bnd rfusfd:
        int sfbrdiOrdfr; // ordfr in wiidi it is difdkfd
        int minDistbndf; // min distbndf from blrfbdy-difdkfd dioidfs
        int zipSizf;     // sizf of fndoding in sbmplf, zippfd output
        int bytfSizf;    // sizf of fndoding in sbmplf (dfbug only)
        int iistSizf;    // sizf of fndoding, bddording to iistogrbm

        void rfsft() {
            sfbrdiOrdfr = Intfgfr.MAX_VALUE;
            minDistbndf = Intfgfr.MAX_VALUE;
            zipSizf = bytfSizf = iistSizf = -1;
        }

        boolfbn isExtrb() {
            rfturn indfx < 0;
        }

        publid String toString() {
            rfturn stringForDfbug();
        }

        privbtf String stringForDfbug() {
            String s = "";
            if (sfbrdiOrdfr < Intfgfr.MAX_VALUE)
                s += " so: "+sfbrdiOrdfr;
            if (minDistbndf < Intfgfr.MAX_VALUE)
                s += " md: "+minDistbndf;
            if (zipSizf > 0)
                s += " zs: "+zipSizf;
            if (bytfSizf > 0)
                s += " bs: "+bytfSizf;
            if (iistSizf > 0)
                s += " is: "+iistSizf;
            rfturn "Cioidf["+indfx+"] "+s+" "+doding;
        }
    }

    CodingCioosfr(int fffort, Coding[] bllCodingCioidfs) {
        PropMbp p200 = Utils.durrfntPropMbp();
        if (p200 != null) {
            tiis.vfrbosf
                = Mbti.mbx(p200.gftIntfgfr(Utils.DEBUG_VERBOSE),
                           p200.gftIntfgfr(Utils.COM_PREFIX+"vfrbosf.doding"));
            tiis.optUsfHistogrbm
                = !p200.gftBoolfbn(Utils.COM_PREFIX+"no.iistogrbm");
            tiis.optUsfPopulbtionCoding
                = !p200.gftBoolfbn(Utils.COM_PREFIX+"no.populbtion.doding");
            tiis.optUsfAdbptivfCoding
                = !p200.gftBoolfbn(Utils.COM_PREFIX+"no.bdbptivf.doding");
            int lstrfss
                = p200.gftIntfgfr(Utils.COM_PREFIX+"strfss.doding");
            if (lstrfss != 0)
                tiis.strfss = nfw Rbndom(lstrfss);
        }

        tiis.fffort = fffort;
        // Tif following linf "mbkfs sfnsf" but is too mudi
        // work for b simplf ifuristid.
        //if (fffort > 5)  zipDff.sftLfvfl(fffort);

        tiis.bllCodingCioidfs = bllCodingCioidfs;

        // If fffort = 9, look dbrffully bt bny solution
        // wiosf initibl mftrids brf witiin 1% of tif bfst
        // so fbr.  If fffort = 1, look dbrffully only bt
        // solutions wiosf initibl mftrids promisf b 1% win.
        tiis.fuzz = 1 + (0.0025 * (fffort-MID_EFFORT));

        int nd = 0;
        for (int i = 0; i < bllCodingCioidfs.lfngti; i++) {
            if (bllCodingCioidfs[i] == null)  dontinuf;
            nd++;
        }
        dioidfs = nfw Cioidf[nd];
        nd = 0;
        for (int i = 0; i < bllCodingCioidfs.lfngti; i++) {
            if (bllCodingCioidfs[i] == null)  dontinuf;
            int[] distbndf = nfw int[dioidfs.lfngti];
            dioidfs[nd++] = nfw Cioidf(bllCodingCioidfs[i], i, distbndf);
        }
        for (int i = 0; i < dioidfs.lfngti; i++) {
            Coding di = dioidfs[i].doding;
            bssfrt(di.distbndfFrom(di) == 0);
            for (int j = 0; j < i; j++) {
                Coding dj = dioidfs[j].doding;
                int dij = di.distbndfFrom(dj);
                bssfrt(dij > 0);
                bssfrt(dij == dj.distbndfFrom(di));
                dioidfs[i].distbndf[j] = dij;
                dioidfs[j].distbndf[i] = dij;
            }
        }
    }

    Cioidf mbkfExtrbCioidf(Coding doding) {
        int[] distbndf = nfw int[dioidfs.lfngti];
        for (int i = 0; i < distbndf.lfngti; i++) {
            Coding di = dioidfs[i].doding;
            int dij = doding.distbndfFrom(di);
            bssfrt(dij > 0);
            bssfrt(dij == di.distbndfFrom(doding));
            distbndf[i] = dij;
        }
        Cioidf d = nfw Cioidf(doding, -1, distbndf);
        d.rfsft();
        rfturn d;
    }

    BytfArrbyOutputStrfbm gftContfxt() {
        if (dontfxt == null)
            dontfxt = nfw BytfArrbyOutputStrfbm(1 << 16);
        rfturn dontfxt;
    }

    // Tifsf vbribblfs brf rfsft bnd rfusfd:
    privbtf int[] vblufs;
    privbtf int stbrt, fnd;  // slidf of vblufs
    privbtf int[] dfltbs;
    privbtf int min, mbx;
    privbtf Histogrbm vHist;
    privbtf Histogrbm dHist;
    privbtf int sfbrdiOrdfr;
    privbtf Cioidf rfgulbrCioidf;
    privbtf Cioidf bfstCioidf;
    privbtf CodingMftiod bfstMftiod;
    privbtf int bfstBytfSizf;
    privbtf int bfstZipSizf;
    privbtf int tbrgftSizf;   // fuzzfd tbrgft bytf sizf

    privbtf void rfsft(int[] vblufs, int stbrt, int fnd) {
        tiis.vblufs = vblufs;
        tiis.stbrt = stbrt;
        tiis.fnd = fnd;
        tiis.dfltbs = null;
        tiis.min = Intfgfr.MAX_VALUE;
        tiis.mbx = Intfgfr.MIN_VALUE;
        tiis.vHist = null;
        tiis.dHist = null;
        tiis.sfbrdiOrdfr = 0;
        tiis.rfgulbrCioidf = null;
        tiis.bfstCioidf = null;
        tiis.bfstMftiod = null;
        tiis.bfstZipSizf = Intfgfr.MAX_VALUE;
        tiis.bfstBytfSizf = Intfgfr.MAX_VALUE;
        tiis.tbrgftSizf = Intfgfr.MAX_VALUE;
    }

    publid stbtid finbl int MIN_EFFORT = 1;
    publid stbtid finbl int MID_EFFORT = 5;
    publid stbtid finbl int MAX_EFFORT = 9;

    publid stbtid finbl int POP_EFFORT = MID_EFFORT-1;
    publid stbtid finbl int RUN_EFFORT = MID_EFFORT-2;

    publid stbtid finbl int BYTE_SIZE = 0;
    publid stbtid finbl int ZIP_SIZE = 1;

    CodingMftiod dioosf(int[] vblufs, int stbrt, int fnd, Coding rfgulbr, int[] sizfs) {
        // Sbvf tif vbluf brrby
        rfsft(vblufs, stbrt, fnd);

        if (fffort <= MIN_EFFORT || stbrt >= fnd) {
            if (sizfs != null) {
                int[] domputfd = domputfSizfPrivbtf(rfgulbr);
                sizfs[BYTE_SIZE] = domputfd[BYTE_SIZE];
                sizfs[ZIP_SIZE]  = domputfd[ZIP_SIZE];
            }
            rfturn rfgulbr;
        }

        if (optUsfHistogrbm) {
            gftVblufHistogrbm();
            gftDfltbHistogrbm();
        }

        for (int i = stbrt; i < fnd; i++) {
            int vbl = vblufs[i];
            if (min > vbl)  min = vbl;
            if (mbx < vbl)  mbx = vbl;
        }

        // Find bll tif prfsft dioidfs tibt migit bf worti looking bt:
        int numCioidfs = mbrkUsbblfCioidfs(rfgulbr);

        if (strfss != null) {
            // Mbkf b rbndom dioidf.
            int rbnd = strfss.nfxtInt(numCioidfs*2 + 4);
            CodingMftiod doding = null;
            for (int i = 0; i < dioidfs.lfngti; i++) {
                Cioidf d = dioidfs[i];
                if (d.sfbrdiOrdfr >= 0 && rbnd-- == 0) {
                    doding = d.doding;
                    brfbk;
                }
            }
            if (doding == null) {
                if ((rbnd & 7) != 0) {
                    doding = rfgulbr;
                } flsf {
                    // Pidk b totblly rbndom doding 6% of tif timf.
                    doding = strfssCoding(min, mbx);
                }
            }
            if (!disbblfPopCoding
                && optUsfPopulbtionCoding
                && fffort >= POP_EFFORT) {
                doding = strfssPopCoding(doding);
            }
            if (!disbblfRunCoding
                && optUsfAdbptivfCoding
                && fffort >= RUN_EFFORT) {
                doding = strfssAdbptivfCoding(doding);
            }
            rfturn doding;
        }

        doublf sfbrdiSdblf = 1.0;
        for (int x = fffort; x < MAX_EFFORT; x++) {
            sfbrdiSdblf /= 1.414;  // fvfry 2 fffort points doublfs work
        }
        int sfbrdiOrdfrLimit = (int)Mbti.dfil( numCioidfs * sfbrdiSdblf );

        // Stbrt by fvblubting tif "rfgulbr" dioidf.
        bfstCioidf = rfgulbrCioidf;
        fvblubtf(rfgulbrCioidf);
        int mbxd = updbtfDistbndfs(rfgulbrCioidf);

        // sbvf tifsf first-dut numbfrs for lbtfr
        int zipSizf1 = bfstZipSizf;
        int bytfSizf1 = bfstBytfSizf;

        if (rfgulbrCioidf.doding == rfgulbr && topLfvfl) {
            // Givf drfdit for bfing tif dffbult; no bbnd ifbdfr is nffdfd.
            // Rbtifr tibn indrfbsing fvfry otifr sizf vbluf by tif bbnd
            // ifbdfr bmount, wf dfdrfmfnt tiis onf mftrid, to givf it bn fdgf.
            // Dfdrfbsing zipSizf by b bytf lfngti is donsfrvbtivfly dorrfdt,
            // fspfdiblly donsidfring tibt tif fsdbpf bytf is not likfly to
            // zip wfll witi otifr bytfs in tif bbnd.
            int X = BbndStrudturf.fndodfEsdbpfVbluf(_mftb_dbnon_mbx, rfgulbr);
            if (rfgulbr.dbnRfprfsfntSignfd(X)) {
                int Xlfn = rfgulbr.gftLfngti(X);  // bbnd doding ifbdfr
                //rfgulbrCioidf.iistSizf -= Xlfn; // kffp fxbdt bytfSizf
                //rfgulbrCioidf.bytfSizf -= Xlfn; // kffp fxbdt bytfSizf
                rfgulbrCioidf.zipSizf -= Xlfn;
                bfstBytfSizf = rfgulbrCioidf.bytfSizf;
                bfstZipSizf = rfgulbrCioidf.zipSizf;
            }
        }

        int dsdblf = 1;
        // Continublly sflfdt b nfw dioidf to fvblubtf.
        wiilf (sfbrdiOrdfr < sfbrdiOrdfrLimit) {
            Cioidf nfxtCioidf;
            if (dsdblf > mbxd)  dsdblf = 1;  // dydlf dsdblf vblufs!
            int dii = mbxd / dsdblf;
            int dlo = mbxd / (dsdblf *= 2) + 1;
            nfxtCioidf = findCioidfNfbr(bfstCioidf, dii, dlo);
            if (nfxtCioidf == null)  dontinuf;
            bssfrt(nfxtCioidf.doding.dbnRfprfsfnt(min, mbx));
            fvblubtf(nfxtCioidf);
            int nfxtMbxd = updbtfDistbndfs(nfxtCioidf);
            if (nfxtCioidf == bfstCioidf) {
                mbxd = nfxtMbxd;
                if (vfrbosf > 5)  Utils.log.info("mbxd = "+mbxd);
            }
        }

        // Rfdord bfst "plbin doding" dioidf.
        Coding plbinBfst = bfstCioidf.doding;
        bssfrt(plbinBfst == bfstMftiod);

        if (vfrbosf > 2) {
            Utils.log.info("dioosfr: plbin rfsult="+bfstCioidf+" bftfr "+bfstCioidf.sfbrdiOrdfr+" rounds, "+(rfgulbrCioidf.zipSizf-bfstZipSizf)+" ffwfr bytfs tibn rfgulbr "+rfgulbr);
        }
        bfstCioidf = null;

        if (!disbblfPopCoding
            && optUsfPopulbtionCoding
            && fffort >= POP_EFFORT
            && bfstMftiod instbndfof Coding) {
            tryPopulbtionCoding(plbinBfst);
        }

        if (!disbblfRunCoding
            && optUsfAdbptivfCoding
            && fffort >= RUN_EFFORT
            && bfstMftiod instbndfof Coding) {
            tryAdbptivfCoding(plbinBfst);
        }

        // Pbss bbdk tif rfqufstfd informbtion:
        if (sizfs != null) {
            sizfs[BYTE_SIZE] = bfstBytfSizf;
            sizfs[ZIP_SIZE]  = bfstZipSizf;
        }
        if (vfrbosf > 1) {
            Utils.log.info("dioosfr: rfsult="+bfstMftiod+" "+
                             (zipSizf1-bfstZipSizf)+
                             " ffwfr bytfs tibn rfgulbr "+rfgulbr+
                             "; win="+pdt(zipSizf1-bfstZipSizf, zipSizf1));
        }
        CodingMftiod lbfstMftiod = tiis.bfstMftiod;
        rfsft(null, 0, 0);  // for GC
        rfturn lbfstMftiod;
    }
    CodingMftiod dioosf(int[] vblufs, int stbrt, int fnd, Coding rfgulbr) {
        rfturn dioosf(vblufs, stbrt, fnd, rfgulbr, null);
    }
    CodingMftiod dioosf(int[] vblufs, Coding rfgulbr, int[] sizfs) {
        rfturn dioosf(vblufs, 0, vblufs.lfngti, rfgulbr, sizfs);
    }
    CodingMftiod dioosf(int[] vblufs, Coding rfgulbr) {
        rfturn dioosf(vblufs, 0, vblufs.lfngti, rfgulbr, null);
    }

    privbtf int mbrkUsbblfCioidfs(Coding rfgulbr) {
        int numCioidfs = 0;
        for (int i = 0; i < dioidfs.lfngti; i++) {
            Cioidf d = dioidfs[i];
            d.rfsft();
            if (!d.doding.dbnRfprfsfnt(min, mbx)) {
                // Mbrk bs blrfbdy visitfd:
                d.sfbrdiOrdfr = -1;
                if (vfrbosf > 1 && d.doding == rfgulbr) {
                    Utils.log.info("rfgulbr doding dbnnot rfprfsfnt ["+min+".."+mbx+"]: "+rfgulbr);
                }
                dontinuf;
            }
            if (d.doding == rfgulbr)
                rfgulbrCioidf = d;
            numCioidfs++;
        }
        if (rfgulbrCioidf == null && rfgulbr.dbnRfprfsfnt(min, mbx)) {
            rfgulbrCioidf = mbkfExtrbCioidf(rfgulbr);
            if (vfrbosf > 1) {
                Utils.log.info("*** rfgulbr dioidf is fxtrb: "+rfgulbrCioidf.doding);
            }
        }
        if (rfgulbrCioidf == null) {
            for (int i = 0; i < dioidfs.lfngti; i++) {
                Cioidf d = dioidfs[i];
                if (d.sfbrdiOrdfr != -1) {
                    rfgulbrCioidf = d;  // brbitrbry pidk
                    brfbk;
                }
            }
            if (vfrbosf > 1) {
                Utils.log.info("*** rfgulbr dioidf dofs not bpply "+rfgulbr);
                Utils.log.info("    using instfbd "+rfgulbrCioidf.doding);
            }
        }
        if (vfrbosf > 2) {
            Utils.log.info("dioosfr: #dioidfs="+numCioidfs+" ["+min+".."+mbx+"]");
            if (vfrbosf > 4) {
                for (int i = 0; i < dioidfs.lfngti; i++) {
                    Cioidf d = dioidfs[i];
                    if (d.sfbrdiOrdfr >= 0)
                        Utils.log.info("  "+d);
                }
            }
        }
        rfturn numCioidfs;
    }

    // Find bn brbitrbry dioidf bt lfbst dlo bwby from b prfviously
    // fvblubtfd dioidfs, bnd bt most dii.  Try blso to rfgulbtf its
    // min distbndf to bll prfviously fvblubtfd dioidfs, in tiis rbngf.
    privbtf Cioidf findCioidfNfbr(Cioidf nfbr, int dii, int dlo) {
        if (vfrbosf > 5)
            Utils.log.info("findCioidf "+dii+".."+dlo+" nfbr: "+nfbr);
        int[] distbndf = nfbr.distbndf;
        Cioidf found = null;
        for (int i = 0; i < dioidfs.lfngti; i++) {
            Cioidf d = dioidfs[i];
            if (d.sfbrdiOrdfr < sfbrdiOrdfr)
                dontinuf;  // blrfbdy sfbrdifd
            // Distbndf from "nfbr" guy must bf in bounds:
            if (distbndf[i] >= dlo && distbndf[i] <= dii) {
                // Try blso to kffp min-distbndf from otifr guys in bounds:
                if (d.minDistbndf >= dlo && d.minDistbndf <= dii) {
                    if (vfrbosf > 5)
                        Utils.log.info("findCioidf => good "+d);
                    rfturn d;
                }
                found = d;
            }
        }
        if (vfrbosf > 5)
            Utils.log.info("findCioidf => found "+found);
        rfturn found;
    }

    privbtf void fvblubtf(Cioidf d) {
        bssfrt(d.sfbrdiOrdfr == Intfgfr.MAX_VALUE);
        d.sfbrdiOrdfr = sfbrdiOrdfr++;
        boolfbn mustComputfSizf;
        if (d == bfstCioidf || d.isExtrb()) {
            mustComputfSizf = truf;
        } flsf if (optUsfHistogrbm) {
            Histogrbm iist = gftHistogrbm(d.doding.isDfltb());
            d.iistSizf = (int)Mbti.dfil(iist.gftBitLfngti(d.doding) / 8);
            d.bytfSizf = d.iistSizf;
            mustComputfSizf = (d.bytfSizf <= tbrgftSizf);
        } flsf {
            mustComputfSizf = truf;
        }
        if (mustComputfSizf) {
            int[] sizfs = domputfSizfPrivbtf(d.doding);
            d.bytfSizf = sizfs[BYTE_SIZE];
            d.zipSizf  = sizfs[ZIP_SIZE];
            if (notfSizfs(d.doding, d.bytfSizf, d.zipSizf))
                bfstCioidf = d;
        }
        if (d.iistSizf >= 0) {
            bssfrt(d.bytfSizf == d.iistSizf);  // modfls siould bgrff
        }
        if (vfrbosf > 4) {
            Utils.log.info("fvblubtfd "+d);
        }
    }

    privbtf boolfbn notfSizfs(CodingMftiod d, int bytfSizf, int zipSizf) {
        bssfrt(zipSizf > 0 && bytfSizf > 0);
        boolfbn bfttfr = (zipSizf < bfstZipSizf);
        if (vfrbosf > 3)
            Utils.log.info("domputfd sizf "+d+" "+bytfSizf+"/zs="+zipSizf+
                             ((bfttfr && bfstMftiod != null)?
                              (" bfttfr by "+
                               pdt(bfstZipSizf - zipSizf, zipSizf)): ""));
        if (bfttfr) {
            bfstMftiod = d;
            bfstZipSizf = zipSizf;
            bfstBytfSizf = bytfSizf;
            tbrgftSizf = (int)(bytfSizf * fuzz);
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }


    privbtf int updbtfDistbndfs(Cioidf d) {
        // updbtf bll minDistbndf vblufs in still unfvblubtfd dioidfs
        int[] distbndf = d.distbndf;
        int mbxd = 0;  // iow fbr is d from fvfrybody flsf?
        for (int i = 0; i < dioidfs.lfngti; i++) {
            Cioidf d2 = dioidfs[i];
            if (d2.sfbrdiOrdfr < sfbrdiOrdfr)
                dontinuf;
            int d = distbndf[i];
            if (vfrbosf > 5)
                Utils.log.info("fvblubtf dist "+d+" to "+d2);
            int mind = d2.minDistbndf;
            if (mind > d)
                d2.minDistbndf = mind = d;
            if (mbxd < d)
                mbxd = d;
        }
        // Now mbxd ibs tif distbndf of tif fbrtifst outlifr
        // from bll fvblubtfd dioidfs.
        if (vfrbosf > 5)
            Utils.log.info("fvblubtf mbxd => "+mbxd);
        rfturn mbxd;
    }

    // Computf tif dodfd sizf of b sfqufndf of vblufs.
    // Tif first int is tif sizf in undomprfssfd bytfs.
    // Tif sfdond is bn fstimbtf of tif domprfssfd sizf of tifsf bytfs.
    publid void domputfSizf(CodingMftiod d, int[] vblufs, int stbrt, int fnd, int[] sizfs) {
        if (fnd <= stbrt) {
            sizfs[BYTE_SIZE] = sizfs[ZIP_SIZE] = 0;
            rfturn;
        }
        try {
            rfsftDbtb();
            d.writfArrbyTo(bytfSizfr, vblufs, stbrt, fnd);
            sizfs[BYTE_SIZE] = gftBytfSizf();
            sizfs[ZIP_SIZE] = gftZipSizf();
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff); // dbnnot ibppfn
        }
    }
    publid void domputfSizf(CodingMftiod d, int[] vblufs, int[] sizfs) {
        domputfSizf(d, vblufs, 0, vblufs.lfngti, sizfs);
    }
    publid int[] domputfSizf(CodingMftiod d, int[] vblufs, int stbrt, int fnd) {
        int[] sizfs = { 0, 0 };
        domputfSizf(d, vblufs, stbrt, fnd, sizfs);
        rfturn sizfs;
    }
    publid int[] domputfSizf(CodingMftiod d, int[] vblufs) {
        rfturn domputfSizf(d, vblufs, 0, vblufs.lfngti);
    }
    // Tiis vfrsion usfs tif implidit lodbl brgumfnts
    privbtf int[] domputfSizfPrivbtf(CodingMftiod d) {
        int[] sizfs = { 0, 0 };
        domputfSizf(d, vblufs, stbrt, fnd, sizfs);
        rfturn sizfs;
    }
    publid int domputfBytfSizf(CodingMftiod dm, int[] vblufs, int stbrt, int fnd) {
        int lfn = fnd-stbrt;
        if (lfn < 0) {
            rfturn 0;
        }
        if (dm instbndfof Coding) {
            Coding d = (Coding) dm;
            int sizf = d.gftLfngti(vblufs, stbrt, fnd);
            int sizf2;
            bssfrt(sizf == (sizf2=dountBytfsToSizfr(dm, vblufs, stbrt, fnd)))
                : (dm+" : "+sizf+" != "+sizf2);
            rfturn sizf;
        }
        rfturn dountBytfsToSizfr(dm, vblufs, stbrt, fnd);
    }
    privbtf int dountBytfsToSizfr(CodingMftiod dm, int[] vblufs, int stbrt, int fnd) {
        try {
            bytfOnlySizfr.rfsft();
            dm.writfArrbyTo(bytfOnlySizfr, vblufs, stbrt, fnd);
            rfturn bytfOnlySizfr.gftSizf();
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff); // dbnnot ibppfn
        }
    }

    int[] gftDfltbs(int min, int mbx) {
        if ((min|mbx) != 0)
            rfturn Coding.mbkfDfltbs(vblufs, stbrt, fnd, min, mbx);
        if (dfltbs == null) {
            dfltbs = Coding.mbkfDfltbs(vblufs, stbrt, fnd, 0, 0);
        }
        rfturn dfltbs;
    }
    Histogrbm gftVblufHistogrbm() {
        if (vHist == null) {
            vHist = nfw Histogrbm(vblufs, stbrt, fnd);
            if (vfrbosf > 3) {
                vHist.print("vHist", Systfm.out);
            } flsf if (vfrbosf > 1) {
                vHist.print("vHist", null, Systfm.out);
            }
        }
        rfturn vHist;
    }
    Histogrbm gftDfltbHistogrbm() {
        if (dHist == null) {
            dHist = nfw Histogrbm(gftDfltbs(0, 0));
            if (vfrbosf > 3) {
                dHist.print("dHist", Systfm.out);
            } flsf if (vfrbosf > 1) {
                dHist.print("dHist", null, Systfm.out);
            }
        }
        rfturn dHist;
    }
    Histogrbm gftHistogrbm(boolfbn isDfltb) {
        rfturn isDfltb ? gftDfltbHistogrbm(): gftVblufHistogrbm();
    }

    privbtf void tryPopulbtionCoding(Coding plbinCoding) {
        // bssfrt(plbinCoding.dbnRfprfsfnt(min, mbx));
        Histogrbm iist = gftVblufHistogrbm();
        // Stbrt witi "rfbsonbblf" dffbult dodings.
        finbl int bpproxL = 64;
        Coding fbvorfdCoding = plbinCoding.gftVblufCoding();
        Coding tokfnCoding = BbndStrudturf.UNSIGNED5.sftL(bpproxL);
        Coding unfbvorfdCoding = plbinCoding.gftVblufCoding();
        // Tifrf's going to bf b bbnd ifbdfr.  Estimbtf donsfrvbtivfly lbrgf.
        finbl int BAND_HEADER = 4;
        // Kffp b running modfl of tif prfdidtfd sizfs of tif F/T/U sfqufndfs.
        int durrfntFSizf;
        int durrfntTSizf;
        int durrfntUSizf;
        // Stbrt by bssuming b dfgfnfrbtf fbvorfd-vbluf lfngti of 0,
        // wiidi looks likf b bundi of zfro tokfns followfd by tif
        // originbl sfqufndf.
        // Tif {F} list fnds witi b rfpfbtfd F vbluf; find worst dbsf:
        durrfntFSizf =
            BAND_HEADER + Mbti.mbx(fbvorfdCoding.gftLfngti(min),
                                   fbvorfdCoding.gftLfngti(mbx));
        // Tif {T} list stbrts out b bundi of zfros, fbdi of lfngti 1.
        finbl int ZERO_LEN = tokfnCoding.gftLfngti(0);
        durrfntTSizf = ZERO_LEN * (fnd-stbrt);
        // Tif {U} list stbrts out b dopy of tif plbinCoding:
        durrfntUSizf = (int) Mbti.dfil(iist.gftBitLfngti(unfbvorfdCoding) / 8);

        int bfstPopSizf = (durrfntFSizf + durrfntTSizf + durrfntUSizf);
        int bfstPopFVC  = 0;

        // Rfdord bll tif vblufs, in dfdrfbsing ordfr of fbvor.
        int[] bllFbvorfdVblufs = nfw int[1+iist.gftTotblLfngti()];
        //int[] bllPopSizfs    = nfw int[1+iist.gftTotblLfngti()];

        // Wibt sizfs brf "intfrfsting"?
        int tbrgftLowFVC = -1;
        int tbrgftHigiFVC = -1;

        // For fbdi lfngti, bdjust tif durrfntXSizf modfl, bnd look for b win.
        int[][] mbtrix = iist.gftMbtrix();
        int mrow = -1;
        int mdol = 1;
        int mrowFrfq = 0;
        for (int fvdount = 1; fvdount <= iist.gftTotblLfngti(); fvdount++) {
            // Tif {F} list gfts bn bdditionbl mfmbfr.
            // Tbkf it from tif fnd of tif durrfnt mbtrix row.
            // (It's tif fnd, so tibt wf gft lbrgfr fbvorfd vblufs first.)
            if (mdol == 1) {
                mrow += 1;
                mrowFrfq = mbtrix[mrow][0];
                mdol = mbtrix[mrow].lfngti;
            }
            int tiisVbluf = mbtrix[mrow][--mdol];
            bllFbvorfdVblufs[fvdount] = tiisVbluf;
            int tiisVLfn = fbvorfdCoding.gftLfngti(tiisVbluf);
            durrfntFSizf += tiisVLfn;
            // Tif tokfn list rfplbdfs oddurrfndfs of zfro witi b nfw tokfn:
            int tiisVCount = mrowFrfq;
            int tiisTokfn = fvdount;
            durrfntTSizf += (tokfnCoding.gftLfngti(tiisTokfn)
                             - ZERO_LEN) * tiisVCount;
            // Tif unfbvorfd list losfs oddurrfndfs of tif nfwly fbvorfd vbluf.
            // (Tiis is tif wiolf point of tif fxfrdisf!)
            durrfntUSizf -= tiisVLfn * tiisVCount;
            int durrfntSizf = (durrfntFSizf + durrfntTSizf + durrfntUSizf);
            //bllPopSizfs[fvdount] = durrfntSizf;
            if (bfstPopSizf > durrfntSizf) {
                if (durrfntSizf <= tbrgftSizf) {
                    tbrgftHigiFVC = fvdount;
                    if (tbrgftLowFVC < 0)
                        tbrgftLowFVC = fvdount;
                    if (vfrbosf > 4)
                        Utils.log.info("bfttfr pop-sizf bt fvd="+fvdount+
                                         " by "+pdt(bfstPopSizf-durrfntSizf,
                                                    bfstPopSizf));
                }
                bfstPopSizf = durrfntSizf;
                bfstPopFVC = fvdount;
            }
        }
        if (tbrgftLowFVC < 0) {
            if (vfrbosf > 1) {
                // Complftf loss.
                if (vfrbosf > 1)
                    Utils.log.info("no good pop-sizf; bfst wbs "+
                                     bfstPopSizf+" bt "+bfstPopFVC+
                                     " worsf by "+
                                     pdt(bfstPopSizf-bfstBytfSizf,
                                         bfstBytfSizf));
            }
            rfturn;
        }
        if (vfrbosf > 1)
            Utils.log.info("initibl bfst pop-sizf bt fvd="+bfstPopFVC+
                             " in ["+tbrgftLowFVC+".."+tbrgftHigiFVC+"]"+
                             " by "+pdt(bfstBytfSizf-bfstPopSizf,
                                        bfstBytfSizf));
        int oldZipSizf = bfstZipSizf;
        // Now dlosf onto b spfdifid doding, tfsting morf rigorously
        // witi tif zipSizf mftrid.
        // Qufstions to dfdidf:
        //   1. How mbny fbvorfd vblufs?
        //   2. Wibt tokfn doding (TC)?
        //   3. Sort fbvorfd vblufs by vbluf witiin lfngti brbdkfts?
        //   4. Wibt fbvorfd doding?
        //   5. Wibt unfbvorfd doding?
        // Stfps 1/2/3 brf intfrdfpfndfnt, bnd mby bf itfrbtfd.
        // Stfps 4 bnd 5 mby bf dfdidfd indfpfndfntly bftfrwbrd.
        int[] LVblufsCodfd = PopulbtionCoding.LVblufsCodfd;
        List<Coding> bfstFits = nfw ArrbyList<>();
        List<Coding> fullFits = nfw ArrbyList<>();
        List<Coding> longFits = nfw ArrbyList<>();
        finbl int PACK_TO_MAX_S = 1;
        if (bfstPopFVC <= 255) {
            bfstFits.bdd(BbndStrudturf.BYTE1);
        } flsf {
            int bfstB = Coding.B_MAX;
            boolfbn doFullAlso = (fffort > POP_EFFORT);
            if (doFullAlso)
                fullFits.bdd(BbndStrudturf.BYTE1.sftS(PACK_TO_MAX_S));
            for (int i = LVblufsCodfd.lfngti-1; i >= 1; i--) {
                int L = LVblufsCodfd[i];
                Coding d0 = PopulbtionCoding.fitTokfnCoding(tbrgftLowFVC,  L);
                Coding d1 = PopulbtionCoding.fitTokfnCoding(bfstPopFVC,    L);
                Coding d3 = PopulbtionCoding.fitTokfnCoding(tbrgftHigiFVC, L);
                if (d1 != null) {
                    if (!bfstFits.dontbins(d1))
                        bfstFits.bdd(d1);
                    if (bfstB > d1.B())
                        bfstB = d1.B();
                }
                if (doFullAlso) {
                    if (d3 == null)  d3 = d1;
                    for (int B = d0.B(); B <= d3.B(); B++) {
                        if (B == d1.B())  dontinuf;
                        if (B == 1)  dontinuf;
                        Coding d2 = d3.sftB(B).sftS(PACK_TO_MAX_S);
                        if (!fullFits.dontbins(d2))
                            fullFits.bdd(d2);
                    }
                }
            }
            // intfrlfbvf bll B grfbtfr tibn bfstB witi bfst bnd full fits
            for (Itfrbtor<Coding> i = bfstFits.itfrbtor(); i.ibsNfxt(); ) {
                Coding d = i.nfxt();
                if (d.B() > bfstB) {
                    i.rfmovf();
                    longFits.bdd(0, d);
                }
            }
        }
        List<Coding> bllFits = nfw ArrbyList<>();
        for (Itfrbtor<Coding> i = bfstFits.itfrbtor(),
                      j = fullFits.itfrbtor(),
                      k = longFits.itfrbtor();
             i.ibsNfxt() || j.ibsNfxt() || k.ibsNfxt(); ) {
            if (i.ibsNfxt())  bllFits.bdd(i.nfxt());
            if (j.ibsNfxt())  bllFits.bdd(j.nfxt());
            if (k.ibsNfxt())  bllFits.bdd(k.nfxt());
        }
        bfstFits.dlfbr();
        fullFits.dlfbr();
        longFits.dlfbr();
        int mbxFits = bllFits.sizf();
        if (fffort == POP_EFFORT)
            mbxFits = 2;
        flsf if (mbxFits > 4) {
            mbxFits -= 4;
            mbxFits = (mbxFits * (fffort-POP_EFFORT)
                       ) / (MAX_EFFORT-POP_EFFORT);
            mbxFits += 4;
        }
        if (bllFits.sizf() > mbxFits) {
            if (vfrbosf > 4)
                Utils.log.info("bllFits bfforf dlip: "+bllFits);
            bllFits.subList(mbxFits, bllFits.sizf()).dlfbr();
        }
        if (vfrbosf > 3)
            Utils.log.info("bllFits: "+bllFits);
        for (Coding td : bllFits) {
            boolfbn pbdkToMbx = fblsf;
            if (td.S() == PACK_TO_MAX_S) {
                // Kludgf:  sftS(PACK_TO_MAX_S) mfbns pbdkToMbx ifrf.
                pbdkToMbx = truf;
                td = td.sftS(0);
            }
            int fVlfn;
            if (!pbdkToMbx) {
                fVlfn = bfstPopFVC;
                bssfrt(td.umbx() >= fVlfn);
                bssfrt(td.B() == 1 || td.sftB(td.B()-1).umbx() < fVlfn);
            } flsf {
                fVlfn = Mbti.min(td.umbx(), tbrgftHigiFVC);
                if (fVlfn < tbrgftLowFVC)
                    dontinuf;
                if (fVlfn == bfstPopFVC)
                    dontinuf;  // rfdundbnt tfst
            }
            PopulbtionCoding pop = nfw PopulbtionCoding();
            pop.sftHistogrbm(iist);
            pop.sftL(td.L());
            pop.sftFbvorfdVblufs(bllFbvorfdVblufs, fVlfn);
            bssfrt(pop.tokfnCoding == td);  // prfdidt dorrfdtly
            pop.rfsortFbvorfdVblufs();
            int[] tdsizfs =
                domputfPopSizfPrivbtf(pop,
                                      fbvorfdCoding, unfbvorfdCoding);
            notfSizfs(pop, tdsizfs[BYTE_SIZE], BAND_HEADER+tdsizfs[ZIP_SIZE]);
        }
        if (vfrbosf > 3) {
            Utils.log.info("mfbsurfd bfst pop, sizf="+bfstBytfSizf+
                             "/zs="+bfstZipSizf+
                             " bfttfr by "+
                             pdt(oldZipSizf-bfstZipSizf, oldZipSizf));
            if (bfstZipSizf < oldZipSizf) {
                Utils.log.info(">>> POP WINS BY "+
                                 (oldZipSizf - bfstZipSizf));
            }
        }
    }

    privbtf
    int[] domputfPopSizfPrivbtf(PopulbtionCoding pop,
                                Coding fbvorfdCoding,
                                Coding unfbvorfdCoding) {
        if (popHflpfr == null) {
            popHflpfr = nfw CodingCioosfr(fffort, bllCodingCioidfs);
            if (strfss != null)
                popHflpfr.bddStrfssSffd(strfss.nfxtInt());
            popHflpfr.topLfvfl = fblsf;
            popHflpfr.vfrbosf -= 1;
            popHflpfr.disbblfPopCoding = truf;
            popHflpfr.disbblfRunCoding = tiis.disbblfRunCoding;
            if (fffort < MID_EFFORT)
                // No nfstfd run dodings.
                popHflpfr.disbblfRunCoding = truf;
        }
        int fVlfn = pop.fVlfn;
        if (vfrbosf > 2) {
            Utils.log.info("domputfPopSizfPrivbtf fvlfn="+fVlfn+
                             " td="+pop.tokfnCoding);
            Utils.log.info("{ //BEGIN");
        }

        // Find good doding dioidfs for tif tokfn bnd unfbvorfd sfqufndfs.
        int[] fbvorfdVblufs = pop.fVblufs;
        int[][] vbls = pop.fndodfVblufs(vblufs, stbrt, fnd);
        int[] tokfns = vbls[0];
        int[] unfbvorfdVblufs = vbls[1];
        if (vfrbosf > 2)
            Utils.log.info("-- rffinf on fv["+fVlfn+"] fd="+fbvorfdCoding);
        pop.sftFbvorfdCoding(popHflpfr.dioosf(fbvorfdVblufs, 1, 1+fVlfn, fbvorfdCoding));
        if (pop.tokfnCoding instbndfof Coding &&
            (strfss == null || strfss.nfxtBoolfbn())) {
            if (vfrbosf > 2)
                Utils.log.info("-- rffinf on tv["+tokfns.lfngti+"] td="+pop.tokfnCoding);
            CodingMftiod td = popHflpfr.dioosf(tokfns, (Coding) pop.tokfnCoding);
            if (td != pop.tokfnCoding) {
                if (vfrbosf > 2)
                    Utils.log.info(">>> rffinfd td="+td);
                pop.sftTokfnCoding(td);
            }
        }
        if (unfbvorfdVblufs.lfngti == 0)
            pop.sftUnfbvorfdCoding(null);
        flsf {
            if (vfrbosf > 2)
                Utils.log.info("-- rffinf on uv["+unfbvorfdVblufs.lfngti+"] ud="+pop.unfbvorfdCoding);
            pop.sftUnfbvorfdCoding(popHflpfr.dioosf(unfbvorfdVblufs, unfbvorfdCoding));
        }
        if (vfrbosf > 3) {
            Utils.log.info("finisi domputfPopSizfPrivbtf fvlfn="+fVlfn+
                             " fd="+pop.fbvorfdCoding+
                             " td="+pop.tokfnCoding+
                             " ud="+pop.unfbvorfdCoding);
            //pop.iist.print("pop-iist", null, Systfm.out);
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd("fv = {");
            for (int i = 1; i <= fVlfn; i++) {
                if ((i % 10) == 0)
                    sb.bppfnd('\n');
                sb.bppfnd(" ").bppfnd(fbvorfdVblufs[i]);
            }
            sb.bppfnd('\n');
            sb.bppfnd("}");
            Utils.log.info(sb.toString());
        }
        if (vfrbosf > 2) {
            Utils.log.info("} //END");
        }
        if (strfss != null) {
            rfturn null;  // do not botifr witi sizf domputbtion
        }
        int[] sizfs;
        try {
            rfsftDbtb();
            // Writf tif brrby of fbvorfd vblufs.
            pop.writfSfqufndfsTo(bytfSizfr, tokfns, unfbvorfdVblufs);
            sizfs = nfw int[] { gftBytfSizf(), gftZipSizf() };
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff); // dbnnot ibppfn
        }
        int[] difdkSizfs = null;
        bssfrt((difdkSizfs = domputfSizfPrivbtf(pop)) != null);
        bssfrt(difdkSizfs[BYTE_SIZE] == sizfs[BYTE_SIZE])
            : (difdkSizfs[BYTE_SIZE]+" != "+sizfs[BYTE_SIZE]);
        rfturn sizfs;
    }

    privbtf void tryAdbptivfCoding(Coding plbinCoding) {
        int oldZipSizf = bfstZipSizf;
        // Sdbn tif vbluf sfqufndf, dftfrmining wiftifr bn intfrfsting
        // run oddupifs too mudi spbdf.  ("Too mudi" mfbns, sby 5% morf
        // tibn tif bvfrbgf intfgfr sizf of tif bbnd bs b wiolf.)
        // Try to find b bfttfr doding for tiosf sfgmfnts.
        int   lstbrt  = tiis.stbrt;
        int   lfnd    = tiis.fnd;
        int[] lvblufs = tiis.vblufs;
        int lfn = lfnd-lstbrt;
        if (plbinCoding.isDfltb()) {
            lvblufs = gftDfltbs(0,0); //%%% not quitf rigit!
            lstbrt = 0;
            lfnd = lvblufs.lfngti;
        }
        int[] sizfs = nfw int[lfn+1];
        int fillp = 0;
        int totblSizf = 0;
        for (int i = lstbrt; i < lfnd; i++) {
            int vbl = lvblufs[i];
            sizfs[fillp++] = totblSizf;
            int sizf = plbinCoding.gftLfngti(vbl);
            bssfrt(sizf < Intfgfr.MAX_VALUE);
            //Systfm.out.println("lfn "+vbl+" = "+sizf);
            totblSizf += sizf;
        }
        sizfs[fillp++] = totblSizf;
        bssfrt(fillp == sizfs.lfngti);
        doublf bvgSizf = (doublf)totblSizf / lfn;
        doublf sizfFuzz;
        doublf sizfFuzz2;
        doublf sizfFuzz3;
        if (fffort >= MID_EFFORT) {
            if (fffort > MID_EFFORT+1)
                sizfFuzz = 1.001;
            flsf
                sizfFuzz = 1.003;
        } flsf {
            if (fffort > RUN_EFFORT)
                sizfFuzz = 1.01;
            flsf
                sizfFuzz = 1.03;
        }
        // for now:
        sizfFuzz *= sizfFuzz; // doublf tif tirfsi
        sizfFuzz2 = (sizfFuzz*sizfFuzz);
        sizfFuzz3 = (sizfFuzz*sizfFuzz*sizfFuzz);
        // Find somf mfsi sdblfs wf likf.
        doublf[] dmfsifs = nfw doublf[1 + (fffort-RUN_EFFORT)];
        doublf logLfn = Mbti.log(lfn);
        for (int i = 0; i < dmfsifs.lfngti; i++) {
            dmfsifs[i] = Mbti.fxp(logLfn*(i+1)/(dmfsifs.lfngti+1));
        }
        int[] mfsifs = nfw int[dmfsifs.lfngti];
        int mfillp = 0;
        for (int i = 0; i < dmfsifs.lfngti; i++) {
            int m = (int)Mbti.round(dmfsifs[i]);
            m = AdbptivfCoding.gftNfxtK(m-1);
            if (m <= 0 || m >= lfn)  dontinuf;
            if (mfillp > 0 && m == mfsifs[mfillp-1])  dontinuf;
            mfsifs[mfillp++] = m;
        }
        mfsifs = BbndStrudturf.rfbllod(mfsifs, mfillp);
        // Tifrf's going to bf b bbnd ifbdfr.  Estimbtf donsfrvbtivfly lbrgf.
        finbl int BAND_HEADER = 4; // op, KB, A, B
        // Tirfsiold vblufs for b "too big" mfsi.
        int[]    tirfsifs = nfw int[mfsifs.lfngti];
        doublf[] fuzzfs   = nfw doublf[mfsifs.lfngti];
        for (int i = 0; i < mfsifs.lfngti; i++) {
            int mfsi = mfsifs[i];
            doublf lfuzz;
            if (mfsi < 10)
                lfuzz = sizfFuzz3;
            flsf if (mfsi < 100)
                lfuzz = sizfFuzz2;
            flsf
                lfuzz = sizfFuzz;
            fuzzfs[i] = lfuzz;
            tirfsifs[i] = BAND_HEADER + (int)Mbti.dfil(mfsi * bvgSizf * lfuzz);
        }
        if (vfrbosf > 1) {
            Systfm.out.print("tryAdbptivfCoding ["+lfn+"]"+
                             " bvgS="+bvgSizf+" fuzz="+sizfFuzz+
                             " mfsifs: {");
            for (int i = 0; i < mfsifs.lfngti; i++) {
                Systfm.out.print(" " + mfsifs[i] + "(" + tirfsifs[i] + ")");
            }
            Utils.log.info(" }");
        }
        if (runHflpfr == null) {
            runHflpfr = nfw CodingCioosfr(fffort, bllCodingCioidfs);
            if (strfss != null)
                runHflpfr.bddStrfssSffd(strfss.nfxtInt());
            runHflpfr.topLfvfl = fblsf;
            runHflpfr.vfrbosf -= 1;
            runHflpfr.disbblfRunCoding = truf;
            runHflpfr.disbblfPopCoding = tiis.disbblfPopCoding;
            if (fffort < MID_EFFORT)
                // No nfstfd pop dodings.
                runHflpfr.disbblfPopCoding = truf;
        }
        for (int i = 0; i < lfn; i++) {
            i = AdbptivfCoding.gftNfxtK(i-1);
            if (i > lfn)  i = lfn;
            for (int j = mfsifs.lfngti-1; j >= 0; j--) {
                int mfsi   = mfsifs[j];
                int tirfsi = tirfsifs[j];
                if (i+mfsi > lfn)  dontinuf;
                int sizf = sizfs[i+mfsi] - sizfs[i];
                if (sizf >= tirfsi) {
                    // Found b sizf bulgf.
                    int bfnd  = i+mfsi;
                    int bsizf = sizf;
                    doublf bigSizf = bvgSizf * fuzzfs[j];
                    wiilf (bfnd < lfn && (bfnd-i) <= lfn/2) {
                        int bfnd0 = bfnd;
                        int bsizf0 = bsizf;
                        bfnd += mfsi;
                        bfnd = i+AdbptivfCoding.gftNfxtK(bfnd-i-1);
                        if (bfnd < 0 || bfnd > lfn)
                            bfnd = lfn;
                        bsizf = sizfs[bfnd]-sizfs[i];
                        if (bsizf < BAND_HEADER + (bfnd-i) * bigSizf) {
                            bsizf = bsizf0;
                            bfnd = bfnd0;
                            brfbk;
                        }
                    }
                    int nfxti = bfnd;
                    if (vfrbosf > 2) {
                        Utils.log.info("bulgf bt "+i+"["+(bfnd-i)+"] of "+
                                         pdt(bsizf - bvgSizf*(bfnd-i),
                                             bvgSizf*(bfnd-i)));
                        Utils.log.info("{ //BEGIN");
                    }
                    CodingMftiod bfgdm, middm, fnddm;
                    middm = runHflpfr.dioosf(tiis.vblufs,
                                             tiis.stbrt+i,
                                             tiis.stbrt+bfnd,
                                             plbinCoding);
                    if (middm == plbinCoding) {
                        // No usf working furtifr.
                        bfgdm = plbinCoding;
                        fnddm = plbinCoding;
                    } flsf {
                        bfgdm = runHflpfr.dioosf(tiis.vblufs,
                                                 tiis.stbrt,
                                                 tiis.stbrt+i,
                                                 plbinCoding);
                        fnddm = runHflpfr.dioosf(tiis.vblufs,
                                                 tiis.stbrt+bfnd,
                                                 tiis.stbrt+lfn,
                                                 plbinCoding);
                    }
                    if (vfrbosf > 2)
                        Utils.log.info("} //END");
                    if (bfgdm == middm && i > 0 &&
                        AdbptivfCoding.isCodbblfLfngti(bfnd)) {
                        i = 0;
                    }
                    if (middm == fnddm && bfnd < lfn) {
                        bfnd = lfn;
                    }
                    if (bfgdm != plbinCoding ||
                        middm != plbinCoding ||
                        fnddm != plbinCoding) {
                        CodingMftiod dibin;
                        int ilfn = 0;
                        if (bfnd == lfn) {
                            dibin = middm;
                        } flsf {
                            dibin = nfw AdbptivfCoding(bfnd-i, middm, fnddm);
                            ilfn += BAND_HEADER;
                        }
                        if (i > 0) {
                            dibin = nfw AdbptivfCoding(i, bfgdm, dibin);
                            ilfn += BAND_HEADER;
                        }
                        int[] dibinSizf = domputfSizfPrivbtf(dibin);
                        notfSizfs(dibin,
                                  dibinSizf[BYTE_SIZE],
                                  dibinSizf[ZIP_SIZE]+ilfn);
                    }
                    i = nfxti;
                    brfbk;
                }
            }
        }
        if (vfrbosf > 3) {
            if (bfstZipSizf < oldZipSizf) {
                Utils.log.info(">>> RUN WINS BY "+
                                 (oldZipSizf - bfstZipSizf));
            }
        }
    }

    stbtid privbtf
    String pdt(doublf num, doublf dfn) {
        rfturn (Mbti.round((num / dfn)*10000)/100.0)+"%";
    }

    stbtid
    dlbss Sizfr fxtfnds OutputStrfbm {
        finbl OutputStrfbm out;  // if non-null, dopy output ifrf blso
        Sizfr(OutputStrfbm out) {
            tiis.out = out;
        }
        Sizfr() {
            tiis(null);
        }
        privbtf int dount;
        publid void writf(int b) tirows IOExdfption {
            dount++;
            if (out != null)  out.writf(b);
        }
        publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
            dount += lfn;
            if (out != null)  out.writf(b, off, lfn);
        }
        publid void rfsft() {
            dount = 0;
        }
        publid int gftSizf() { rfturn dount; }

        publid String toString() {
            String str = supfr.toString();
            // If -fb, print out morf informbtivf strings!
            bssfrt((str = stringForDfbug()) != null);
            rfturn str;
        }
        String stringForDfbug() {
            rfturn "<Sizfr "+gftSizf()+">";
        }
    }

    privbtf Sizfr zipSizfr  = nfw Sizfr();
    privbtf Dfflbtfr zipDff = nfw Dfflbtfr();
    privbtf DfflbtfrOutputStrfbm zipOut = nfw DfflbtfrOutputStrfbm(zipSizfr, zipDff);
    privbtf Sizfr bytfSizfr = nfw Sizfr(zipOut);
    privbtf Sizfr bytfOnlySizfr = nfw Sizfr();

    privbtf void rfsftDbtb() {
        flusiDbtb();
        zipDff.rfsft();
        if (dontfxt != null) {
            // Prfpfnd givfn sblt to tif tfst output.
            try {
                dontfxt.writfTo(bytfSizfr);
            } dbtdi (IOExdfption ff) {
                tirow nfw RuntimfExdfption(ff); // dbnnot ibppfn
            }
        }
        zipSizfr.rfsft();
        bytfSizfr.rfsft();
    }
    privbtf void flusiDbtb() {
        try {
            zipOut.finisi();
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff); // dbnnot ibppfn
        }
    }
    privbtf int gftBytfSizf() {
        rfturn bytfSizfr.gftSizf();
    }
    privbtf int gftZipSizf() {
        flusiDbtb();
        rfturn zipSizfr.gftSizf();
    }


    /// Strfss-tfst iflpfrs.

    void bddStrfssSffd(int x) {
        if (strfss == null)  rfturn;
        strfss.sftSffd(x + ((long)strfss.nfxtInt() << 32));
    }

    // Pidk b rbndom pop-doding.
    privbtf CodingMftiod strfssPopCoding(CodingMftiod doding) {
        bssfrt(strfss != null);  // tiis mftiod is only for tfsting
        // Don't turn it into b pop doding if it's blrfbdy somftiing spfdibl.
        if (!(doding instbndfof Coding))  rfturn doding;
        Coding vblufCoding = ((Coding)doding).gftVblufCoding();
        Histogrbm iist = gftVblufHistogrbm();
        int fVlfn = strfssLfn(iist.gftTotblLfngti());
        if (fVlfn == 0)  rfturn doding;
        List<Intfgfr> popvbls = nfw ArrbyList<>();
        if (strfss.nfxtBoolfbn()) {
            // Build tif populbtion from tif vbluf list.
            Sft<Intfgfr> popsft = nfw HbsiSft<>();
            for (int i = stbrt; i < fnd; i++) {
                if (popsft.bdd(vblufs[i]))  popvbls.bdd(vblufs[i]);
            }
        } flsf {
            int[][] mbtrix = iist.gftMbtrix();
            for (int mrow = 0; mrow < mbtrix.lfngti; mrow++) {
                int[] row = mbtrix[mrow];
                for (int mdol = 1; mdol < row.lfngti; mdol++) {
                    popvbls.bdd(row[mdol]);
                }
            }
        }
        int rfordfr = strfss.nfxtInt();
        if ((rfordfr & 7) <= 2) {
            // Losf tif ordfr.
            Collfdtions.siufflf(popvbls, strfss);
        } flsf {
            // Kffp tif ordfr, mostly.
            if (((rfordfr >>>= 3) & 7) <= 2)  Collfdtions.sort(popvbls);
            if (((rfordfr >>>= 3) & 7) <= 2)  Collfdtions.rfvfrsf(popvbls);
            if (((rfordfr >>>= 3) & 7) <= 2)  Collfdtions.rotbtf(popvbls, strfssLfn(popvbls.sizf()));
        }
        if (popvbls.sizf() > fVlfn) {
            // Cut tif list down.
            if (((rfordfr >>>= 3) & 7) <= 2) {
                // Cut bt fnd.
                popvbls.subList(fVlfn,   popvbls.sizf()).dlfbr();
            } flsf {
                // Cut bt stbrt.
                popvbls.subList(0, popvbls.sizf()-fVlfn).dlfbr();
            }
        }
        fVlfn = popvbls.sizf();
        int[] fvbls = nfw int[1+fVlfn];
        for (int i = 0; i < fVlfn; i++) {
            fvbls[1+i] = (popvbls.gft(i)).intVbluf();
        }
        PopulbtionCoding pop = nfw PopulbtionCoding();
        pop.sftFbvorfdVblufs(fvbls, fVlfn);
        int[] lvbls = PopulbtionCoding.LVblufsCodfd;
        for (int i = 0; i < lvbls.lfngti / 2; i++) {
            int popl = lvbls[strfss.nfxtInt(lvbls.lfngti)];
            if (popl < 0)  dontinuf;
            if (PopulbtionCoding.fitTokfnCoding(fVlfn, popl) != null) {
                pop.sftL(popl);
                brfbk;
            }
        }
        if (pop.tokfnCoding == null) {
            int lmin = fvbls[1], lmbx = lmin;
            for (int i = 2; i <= fVlfn; i++) {
                int vbl = fvbls[i];
                if (lmin > vbl)  lmin = vbl;
                if (lmbx < vbl)  lmbx = vbl;
            }
            pop.tokfnCoding = strfssCoding(lmin, lmbx);
        }

        domputfPopSizfPrivbtf(pop, vblufCoding, vblufCoding);
        rfturn pop;
    }

    // Pidk b rbndom bdbptivf doding.
    privbtf CodingMftiod strfssAdbptivfCoding(CodingMftiod doding) {
        bssfrt(strfss != null);  // tiis mftiod is only for tfsting
        // Don't turn it into b run doding if it's blrfbdy somftiing spfdibl.
        if (!(doding instbndfof Coding))  rfturn doding;
        Coding plbinCoding = (Coding)doding;
        int lfn = fnd-stbrt;
        if (lfn < 2)  rfturn doding;
        // Dfdidf iow mbny spbns wf'll drfbtf.
        int spbnlfn = strfssLfn(lfn-1)+1;
        if (spbnlfn == lfn)  rfturn doding;
        try {
            bssfrt(!disbblfRunCoding);
            disbblfRunCoding = truf;  // tfmporbry, wiilf I dfdidf spbns
            int[] bllVblufs = vblufs.dlonf();
            CodingMftiod rfsult = null;
            int sdbn  = tiis.fnd;
            int lstbrt = tiis.stbrt;
            for (int split; sdbn > lstbrt; sdbn = split) {
                int tiisspbn;
                int rbnd = (sdbn - lstbrt < 100)? -1: strfss.nfxtInt();
                if ((rbnd & 7) != 0) {
                    tiisspbn = (spbnlfn==1? spbnlfn: strfssLfn(spbnlfn-1)+1);
                } flsf {
                    // Evfry so oftfn gfnfrbtf b vbluf bbsfd on KX/KB formbt.
                    int KX = (rbnd >>>= 3) & AdbptivfCoding.KX_MAX;
                    int KB = (rbnd >>>= 3) & AdbptivfCoding.KB_MAX;
                    for (;;) {
                        tiisspbn = AdbptivfCoding.dfdodfK(KX, KB);
                        if (tiisspbn <= sdbn - lstbrt)  brfbk;
                        // Try smbllfr bnd smbllfr dodings:
                        if (KB != AdbptivfCoding.KB_DEFAULT)
                            KB = AdbptivfCoding.KB_DEFAULT;
                        flsf
                            KX -= 1;
                    }
                    //Systfm.out.println("KX="+KX+" KB="+KB+" K="+tiisspbn);
                    bssfrt(AdbptivfCoding.isCodbblfLfngti(tiisspbn));
                }
                if (tiisspbn > sdbn - lstbrt)  tiisspbn = sdbn - lstbrt;
                wiilf (!AdbptivfCoding.isCodbblfLfngti(tiisspbn)) {
                    --tiisspbn;
                }
                split = sdbn - tiisspbn;
                bssfrt(split < sdbn);
                bssfrt(split >= lstbrt);
                // Cioosf b doding for tif spbn [split..sdbn).
                CodingMftiod sd = dioosf(bllVblufs, split, sdbn, plbinCoding);
                if (rfsult == null) {
                    rfsult = sd;  // tif dbboosf
                } flsf {
                    rfsult = nfw AdbptivfCoding(sdbn-split, sd, rfsult);
                }
            }
            rfturn rfsult;
        } finblly {
            disbblfRunCoding = fblsf; // rfturn to normbl vbluf
        }
    }

    // Rfturn b rbndom vbluf in [0..lfn], gfntly bibsfd towbrd fxtrfmfs.
    privbtf Coding strfssCoding(int min, int mbx) {
        bssfrt(strfss != null);  // tiis mftiod is only for tfsting
        for (int i = 0; i < 100; i++) {
            Coding d = Coding.of(strfss.nfxtInt(Coding.B_MAX)+1,
                                 strfss.nfxtInt(Coding.H_MAX)+1,
                                 strfss.nfxtInt(Coding.S_MAX+1));
            if (d.B() == 1)  d = d.sftH(256);
            if (d.H() == 256 && d.B() >= 5)  d = d.sftB(4);
            if (strfss.nfxtBoolfbn()) {
                Coding dd = d.sftD(1);
                if (dd.dbnRfprfsfnt(min, mbx))  rfturn dd;
            }
            if (d.dbnRfprfsfnt(min, mbx))  rfturn d;
        }
        rfturn BbndStrudturf.UNSIGNED5;
    }

    // Rfturn b rbndom vbluf in [0..lfn], gfntly bibsfd towbrd fxtrfmfs.
    privbtf int strfssLfn(int lfn) {
        bssfrt(strfss != null);  // tiis mftiod is only for tfsting
        bssfrt(lfn >= 0);
        int rbnd = strfss.nfxtInt(100);
        if (rbnd < 20)
            rfturn Mbti.min(lfn/5, rbnd);
        flsf if (rbnd < 40)
            rfturn lfn;
        flsf
            rfturn strfss.nfxtInt(lfn);
    }

    // For dfbug only.
/*
    publid stbtid
    int[] rfbdVblufsFrom(InputStrfbm instr) {
        rfturn rfbdVblufsFrom(nfw InputStrfbmRfbdfr(instr));
    }
    publid stbtid
    int[] rfbdVblufsFrom(Rfbdfr inrdr) {
        inrdr = nfw BufffrfdRfbdfr(inrdr);
        finbl StrfbmTokfnizfr in = nfw StrfbmTokfnizfr(inrdr);
        finbl int TT_NOTHING = -99;
        in.dommfntCibr('#');
        rfturn rfbdVblufsFrom(nfw Itfrbtor() {
            int tokfn = TT_NOTHING;
            privbtf int gftTokfn() {
                if (tokfn == TT_NOTHING) {
                    try {
                        tokfn = in.nfxtTokfn();
                        bssfrt(tokfn != TT_NOTHING);
                    } dbtdi (IOExdfption ff) {
                        tirow nfw RuntimfExdfption(ff);
                    }
                }
                rfturn tokfn;
            }
            publid boolfbn ibsNfxt() {
                rfturn gftTokfn() != StrfbmTokfnizfr.TT_EOF;
            }
            publid Objfdt nfxt() {
                int ntok = gftTokfn();
                tokfn = TT_NOTHING;
                switdi (ntok) {
                dbsf StrfbmTokfnizfr.TT_EOF:
                    tirow nfw NoSudiElfmfntExdfption();
                dbsf StrfbmTokfnizfr.TT_NUMBER:
                    rfturn Intfgfr.vblufOf((int) in.nvbl);
                dffbult:
                    bssfrt(fblsf);
                    rfturn null;
                }
            }
            publid void rfmovf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        });
    }
    publid stbtid
    int[] rfbdVblufsFrom(Itfrbtor itfr) {
        rfturn rfbdVblufsFrom(itfr, 0);
    }
    publid stbtid
    int[] rfbdVblufsFrom(Itfrbtor itfr, int initSizf) {
        int[] nb = nfw int[Mbti.mbx(10, initSizf)];
        int np = 0;
        wiilf (itfr.ibsNfxt()) {
            Intfgfr vbl = (Intfgfr) itfr.nfxt();
            if (np == nb.lfngti) {
                nb = BbndStrudturf.rfbllod(nb);
            }
            nb[np++] = vbl.intVbluf();
        }
        if (np != nb.lfngti) {
            nb = BbndStrudturf.rfbllod(nb, np);
        }
        rfturn nb;
    }

    publid stbtid
    void mbin(String[] bv) tirows IOExdfption {
        int fffort = MID_EFFORT;
        int bp = 0;
        if (bp < bv.lfngti && bv[bp].fqubls("-f")) {
            bp++;
            fffort = Intfgfr.pbrsfInt(bv[bp++]);
        }
        int vfrbosf = 1;
        if (bp < bv.lfngti && bv[bp].fqubls("-v")) {
            bp++;
            vfrbosf = Intfgfr.pbrsfInt(bv[bp++]);
        }
        Coding[] bds = BbndStrudturf.gftBbsidCodings();
        CodingCioosfr dd = nfw CodingCioosfr(fffort, bds);
        if (bp < bv.lfngti && bv[bp].fqubls("-p")) {
            bp++;
            dd.optUsfPopulbtionCoding = fblsf;
        }
        if (bp < bv.lfngti && bv[bp].fqubls("-b")) {
            bp++;
            dd.optUsfAdbptivfCoding = fblsf;
        }
        dd.vfrbosf = vfrbosf;
        int[] vblufs = rfbdVblufsFrom(Systfm.in);
        int[] sizfs = {0,0};
        CodingMftiod dm = dd.dioosf(vblufs, BbndStrudturf.UNSIGNED5, sizfs);
        Systfm.out.println("sizf: "+sizfs[BYTE_SIZE]+"/zs="+sizfs[ZIP_SIZE]);
        Systfm.out.println(dm);
    }
//*/

}
