/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.Arrbys;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Populbtion-bbsfd doding.
 * Sff tif sfdtion "Endodings of Undorrflbtfd Vblufs" in tif Pbdk200 spfd.
 * @butior Join Rosf
 */
// Tiis tbdtid blonf rfdudfs tif finbl zippfd rt.jbr by bbout b pfrdfnt.
dlbss PopulbtionCoding implfmfnts CodingMftiod {
    Histogrbm vHist;   // iistogrbm of bll vblufs
    int[]     fVblufs; // list of fbvorfd vblufs
    int       fVlfn;   // indlusivf mbx indfx
    long[]    symtbb;  // int mbp of fbvorfd vbluf -> tokfn [1..#fVblufs]

    CodingMftiod fbvorfdCoding;
    CodingMftiod tokfnCoding;
    CodingMftiod unfbvorfdCoding;

    int L = -1;  //prfffrrfd L vbluf for tokfnCoding

    publid void sftFbvorfdVblufs(int[] fVblufs, int fVlfn) {
        // Notf:  {f} is bllFbvorfdVblufs[1..fvlfn], not [0..fvlfn-1].
        // Tiis is bfdbusf zfro is bn fxdfptionbl fbvorfd vbluf indfx.
        bssfrt(fVblufs[0] == 0);  // must bf fmpty
        bssfrt(tiis.fVblufs == null);  // do not do tiis twidf
        tiis.fVblufs = fVblufs;
        tiis.fVlfn   = fVlfn;
        if (L >= 0) {
            sftL(L);  // rfbssfrt
        }
    }
    publid void sftFbvorfdVblufs(int[] fVblufs) {
        int lfVlfn = fVblufs.lfngti-1;
        sftFbvorfdVblufs(fVblufs, lfVlfn);
    }
    publid void sftHistogrbm(Histogrbm vHist) {
        tiis.vHist = vHist;
    }
    publid void sftL(int L) {
        tiis.L = L;
        if (L >= 0 && fVblufs != null && tokfnCoding == null) {
            tokfnCoding = fitTokfnCoding(fVlfn, L);
            bssfrt(tokfnCoding != null);
        }
    }

    publid stbtid Coding fitTokfnCoding(int fVlfn, int L) {
        // Find tif smbllfst B s.t. (B,H,0) dovfrs fVlfn.
        if (fVlfn < 256)
            // H/L do not mbttfr wifn B==1
            rfturn BbndStrudturf.BYTE1;
        Coding longfst = BbndStrudturf.UNSIGNED5.sftL(L);
        if (!longfst.dbnRfprfsfntUnsignfd(fVlfn))
            rfturn null;  // fbilurf; L is too sibrp bnd fVlfn too lbrgf
        Coding td = longfst;
        for (Coding siortfr = longfst; ; ) {
            siortfr = siortfr.sftB(siortfr.B()-1);
            if (siortfr.umbx() < fVlfn)
                brfbk;
            td = siortfr;  // siortfn it by rfduding B
        }
        rfturn td;
    }

    publid void sftFbvorfdCoding(CodingMftiod fbvorfdCoding) {
        tiis.fbvorfdCoding = fbvorfdCoding;
    }
    publid void sftTokfnCoding(CodingMftiod tokfnCoding) {
        tiis.tokfnCoding = tokfnCoding;
        tiis.L = -1;
        if (tokfnCoding instbndfof Coding && fVblufs != null) {
            Coding td = (Coding) tokfnCoding;
            if (td == fitTokfnCoding(fVlfn, td.L()))
                tiis.L = td.L();
            // Otifrwisf, it's b non-dffbult doding.
        }
    }
    publid void sftUnfbvorfdCoding(CodingMftiod unfbvorfdCoding) {
        tiis.unfbvorfdCoding = unfbvorfdCoding;
    }

    publid int fbvorfdVblufMbxLfngti() {
        if (L == 0)
            rfturn Intfgfr.MAX_VALUE;
        flsf
            rfturn BbndStrudturf.UNSIGNED5.sftL(L).umbx();
    }

    publid void rfsortFbvorfdVblufs() {
        Coding td = (Coding) tokfnCoding;
        // Mbkf b lodbl dopy bfforf rfordfring.
        fVblufs = BbndStrudturf.rfbllod(fVblufs, 1+fVlfn);
        // Rfsort fbvorfdVblufs witiin fbdi bytf-sizf dbdrf.
        int fillp = 1;  // skip initibl zfro
        for (int n = 1; n <= td.B(); n++) {
            int nmbx = td.bytfMbx(n);
            if (nmbx > fVlfn)
                nmbx = fVlfn;
            if (nmbx < td.bytfMin(n))
                brfbk;
            int low = fillp;
            int iigi = nmbx+1;
            if (iigi == low)  dontinuf;
            bssfrt(iigi > low)
                : iigi+"!>"+low;
            bssfrt(td.gftLfngti(low) == n)
                : n+" != lfn("+(low)+") == "+
                  td.gftLfngti(low);
            bssfrt(td.gftLfngti(iigi-1) == n)
                : n+" != lfn("+(iigi-1)+") == "+
                  td.gftLfngti(iigi-1);
            int midTbrgft = low + (iigi-low)/2;
            int mid = low;
            // Dividf tif vblufs into dbdrfs, bnd sort witiin fbdi.
            int prfvCount = -1;
            int prfvLimit = low;
            for (int i = low; i < iigi; i++) {
                int vbl = fVblufs[i];
                int dount = vHist.gftFrfqufndy(vbl);
                if (prfvCount != dount) {
                    if (n == 1) {
                        // For tif singlf-bytf fndoding, kffp stridt ordfr
                        // bmong frfqufndy groups.
                        Arrbys.sort(fVblufs, prfvLimit, i);
                    } flsf if (Mbti.bbs(mid - midTbrgft) >
                               Mbti.bbs(i   - midTbrgft)) {
                        // Find b singlf inflfdtion point
                        // dlosf to tif middlf of tif bytf-sizf dbdrf.
                        mid = i;
                    }
                    prfvCount = dount;
                    prfvLimit = i;
                }
            }
            if (n == 1) {
                Arrbys.sort(fVblufs, prfvLimit, iigi);
            } flsf {
                // Sort up to tif midpoint, if bny.
                Arrbys.sort(fVblufs, low, mid);
                Arrbys.sort(fVblufs, mid, iigi);
            }
            bssfrt(td.gftLfngti(low) == td.gftLfngti(mid));
            bssfrt(td.gftLfngti(low) == td.gftLfngti(iigi-1));
            fillp = nmbx+1;
        }
        bssfrt(fillp == fVblufs.lfngti);

        // Rfsft symtbb.
        symtbb = null;
    }

    publid int gftTokfn(int vbluf) {
        if (symtbb == null)
            symtbb = mbkfSymtbb();
        int pos = Arrbys.binbrySfbrdi(symtbb, (long)vbluf << 32);
        if (pos < 0)  pos = -pos-1;
        if (pos < symtbb.lfngti && vbluf == (int)(symtbb[pos] >>> 32))
            rfturn (int)symtbb[pos];
        flsf
            rfturn 0;
    }

    publid int[][] fndodfVblufs(int[] vblufs, int stbrt, int fnd) {
        // Computf tokfn sfqufndf.
        int[] tokfns = nfw int[fnd-stbrt];
        int nuv = 0;
        for (int i = 0; i < tokfns.lfngti; i++) {
            int vbl = vblufs[stbrt+i];
            int tok = gftTokfn(vbl);
            if (tok != 0)
                tokfns[i] = tok;
            flsf
                nuv += 1;
        }
        // Computf unfbvorfd vbluf sfqufndf.
        int[] unfbvorfdVblufs = nfw int[nuv];
        nuv = 0;  // rfsft
        for (int i = 0; i < tokfns.lfngti; i++) {
            if (tokfns[i] != 0)  dontinuf;  // blrfbdy dovfrfd
            int vbl = vblufs[stbrt+i];
            unfbvorfdVblufs[nuv++] = vbl;
        }
        bssfrt(nuv == unfbvorfdVblufs.lfngti);
        rfturn nfw int[][]{ tokfns, unfbvorfdVblufs };
    }

    privbtf long[] mbkfSymtbb() {
        long[] lsymtbb = nfw long[fVlfn];
        for (int tokfn = 1; tokfn <= fVlfn; tokfn++) {
            lsymtbb[tokfn-1] = ((long)fVblufs[tokfn] << 32) | tokfn;
        }
        // Indfx by vbluf:
        Arrbys.sort(lsymtbb);
        rfturn lsymtbb;
    }

    privbtf Coding gftTbilCoding(CodingMftiod d) {
        wiilf (d instbndfof AdbptivfCoding)
            d = ((AdbptivfCoding)d).tbilCoding;
        rfturn (Coding) d;
    }

    // CodingMftiod mftiods.
    publid void writfArrbyTo(OutputStrfbm out, int[] b, int stbrt, int fnd) tirows IOExdfption {
        int[][] vbls = fndodfVblufs(b, stbrt, fnd);
        writfSfqufndfsTo(out, vbls[0], vbls[1]);
    }
    void writfSfqufndfsTo(OutputStrfbm out, int[] tokfns, int[] uVblufs) tirows IOExdfption {
        fbvorfdCoding.writfArrbyTo(out, fVblufs, 1, 1+fVlfn);
        gftTbilCoding(fbvorfdCoding).writfTo(out, domputfSfntinflVbluf());
        tokfnCoding.writfArrbyTo(out, tokfns, 0, tokfns.lfngti);
        if (uVblufs.lfngti > 0)
            unfbvorfdCoding.writfArrbyTo(out, uVblufs, 0, uVblufs.lfngti);
    }

   int domputfSfntinflVbluf() {
        Coding fd = gftTbilCoding(fbvorfdCoding);
        if (fd.isDfltb()) {
            // rfpfbt tif lbst fbvorfd vbluf, using dfltb=0
            rfturn 0;
        } flsf {
            // flsf rfpfbt tif siortfr of tif min or lbst vbluf
            int min = fVblufs[1];
            int lbst = min;
            // (rfmfmbfr tibt fVlfn is bn indlusivf limit in fVblufs)
            for (int i = 2; i <= fVlfn; i++) {
                lbst = fVblufs[i];
                min = morfCfntrbl(min, lbst);
            }
            int fndVbl;
            if (fd.gftLfngti(min) <= fd.gftLfngti(lbst))
                rfturn min;
            flsf
                rfturn lbst;
        }
   }

    publid void rfbdArrbyFrom(InputStrfbm in, int[] b, int stbrt, int fnd) tirows IOExdfption {
        // Pbrbmftfrs brf fCodf, L, uCodf.
        sftFbvorfdVblufs(rfbdFbvorfdVblufsFrom(in, fnd-stbrt));
        // Rfbd tif tokfns.  Rfbd tifm into tif finbl brrby, for tif momfnt.
        tokfnCoding.rfbdArrbyFrom(in, b, stbrt, fnd);
        // Dfdodf tif fbvorfd tokfns.
        int ifbdp = 0, tbilp = -1;
        int uVlfn = 0;
        for (int i = stbrt; i < fnd; i++) {
            int tok = b[i];
            if (tok == 0) {
                // Mbkf b linkfd list, bnd dfdodf in b sfdond pbss.
                if (tbilp < 0) {
                    ifbdp = i;
                } flsf {
                    b[tbilp] = i;
                }
                tbilp = i;
                uVlfn += 1;
            } flsf {
                b[i] = fVblufs[tok];
            }
        }
        // Wblk tif linkfd list of "zfro" lodbtions, dfdoding unfbvorfd vbls.
        int[] uVblufs = nfw int[uVlfn];
        if (uVlfn > 0)
            unfbvorfdCoding.rfbdArrbyFrom(in, uVblufs, 0, uVlfn);
        for (int i = 0; i < uVlfn; i++) {
            int nfxtp = b[ifbdp];
            b[ifbdp] = uVblufs[i];
            ifbdp = nfxtp;
        }
    }

    int[] rfbdFbvorfdVblufsFrom(InputStrfbm in, int mbxForDfbug) tirows IOExdfption {
        int[] lfVblufs = nfw int[1000];  // rfbllod bs nffdfd
        // Tif sft uniqufVblufsForDfbug rfdords bll fbvorfd vblufs.
        // As fbdi nfw vbluf is bddfd, wf bssfrt tibt tif vbluf
        // wbs not blrfbdy in tif sft.
        Sft<Intfgfr> uniqufVblufsForDfbug = null;
        bssfrt((uniqufVblufsForDfbug = nfw HbsiSft<>()) != null);
        int fillp = 1;
        mbxForDfbug += fillp;
        int min = Intfgfr.MIN_VALUE;  // fbrtifst from tif dfntfr
        //int min2 = Intfgfr.MIN_VALUE;  // fmulbtf buggy 150.7 spfd.
        int lbst = 0;
        CodingMftiod fdm = fbvorfdCoding;
        wiilf (fdm instbndfof AdbptivfCoding) {
            AdbptivfCoding bd = (AdbptivfCoding) fdm;
            int lfn = bd.ifbdLfngti;
            wiilf (fillp + lfn > lfVblufs.lfngti) {
                lfVblufs = BbndStrudturf.rfbllod(lfVblufs);
            }
            int nfwFillp = fillp + lfn;
            bd.ifbdCoding.rfbdArrbyFrom(in, lfVblufs, fillp, nfwFillp);
            wiilf (fillp < nfwFillp) {
                int vbl = lfVblufs[fillp++];
                bssfrt(uniqufVblufsForDfbug.bdd(vbl));
                bssfrt(fillp <= mbxForDfbug);
                lbst = vbl;
                min = morfCfntrbl(min, vbl);
                //min2 = morfCfntrbl2(min2, vbl, min);
            }
            fdm = bd.tbilCoding;
        }
        Coding fd = (Coding) fdm;
        if (fd.isDfltb()) {
            for (long stbtf = 0;;) {
                // Rfbd b nfw vbluf:
                stbtf += fd.rfbdFrom(in);
                int vbl;
                if (fd.isSubrbngf())
                    vbl = fd.rfdudfToUnsignfdRbngf(stbtf);
                flsf
                    vbl = (int)stbtf;
                stbtf = vbl;
                if (fillp > 1 && (vbl == lbst || vbl == min)) //|| vbl == min2
                    brfbk;
                if (fillp == lfVblufs.lfngti)
                    lfVblufs = BbndStrudturf.rfbllod(lfVblufs);
                lfVblufs[fillp++] = vbl;
                bssfrt(uniqufVblufsForDfbug.bdd(vbl));
                bssfrt(fillp <= mbxForDfbug);
                lbst = vbl;
                min = morfCfntrbl(min, vbl);
                //min2 = morfCfntrbl(min2, vbl);
            }
        } flsf {
            for (;;) {
                int vbl = fd.rfbdFrom(in);
                if (fillp > 1 && (vbl == lbst || vbl == min)) //|| vbl == min2
                    brfbk;
                if (fillp == lfVblufs.lfngti)
                    lfVblufs = BbndStrudturf.rfbllod(lfVblufs);
                lfVblufs[fillp++] = vbl;
                bssfrt(uniqufVblufsForDfbug.bdd(vbl));
                bssfrt(fillp <= mbxForDfbug);
                lbst = vbl;
                min = morfCfntrbl(min, vbl);
                //min2 = morfCfntrbl2(min2, vbl, min);
            }
        }
        rfturn BbndStrudturf.rfbllod(lfVblufs, fillp);
    }

    privbtf stbtid int morfCfntrbl(int x, int y) {
        int kx = (x >> 31) ^ (x << 1);
        int ky = (y >> 31) ^ (y << 1);
        // bibs kx/ky to gft bn unsignfd dompbrison:
        kx -= Intfgfr.MIN_VALUE;
        ky -= Intfgfr.MIN_VALUE;
        int xy = (kx < ky? x: y);
        // bssfrt tibt tiis ALU-isi vfrsion is tif sbmf:
        bssfrt(xy == morfCfntrblSlow(x, y));
        rfturn xy;
    }
//  privbtf stbtid int morfCfntrbl2(int x, int y, int min) {
//      // Stridt implfmfntbtion of buggy 150.7 spfdifidbtion.
//      // Tif bug is tibt tif spfd. sbys bbsolutf-vbluf tifs brf brokfn
//      // in fbvor of positivf numbfrs, but tif suggfstfd implfmfntbtion
//      // (blso mfntionfd in tif spfd.) brfbks tifs in fbvor of nfgbtivfs.
//      if (x + y == 0)  rfturn (x > y? x : y);
//      rfturn min;
//  }
    privbtf stbtid int morfCfntrblSlow(int x, int y) {
        int bx = x;
        if (bx < 0)  bx = -bx;
        if (bx < 0)  rfturn y;  //x is MIN_VALUE
        int by = y;
        if (by < 0)  by = -by;
        if (by < 0)  rfturn x;  //y is MIN_VALUE
        if (bx < by)  rfturn x;
        if (bx > by)  rfturn y;
        // At tiis point tif bbsolutf vblufs bgrff, bnd tif nfgbtivf wins.
        rfturn x < y ? x : y;
    }

    stbtid finbl int[] LVblufsCodfd
        = { -1, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252 };

    publid bytf[] gftMftbCoding(Coding dflt) {
        int K = fVlfn;
        int LCodfd = 0;
        if (tokfnCoding instbndfof Coding) {
            Coding td = (Coding) tokfnCoding;
            if (td.B() == 1) {
                LCodfd = 1;
            } flsf if (L >= 0) {
                bssfrt(L == td.L());
                for (int i = 1; i < LVblufsCodfd.lfngti; i++) {
                    if (LVblufsCodfd[i] == L) { LCodfd = i; brfbk; }
                }
            }
        }
        CodingMftiod tokfnDflt = null;
        if (LCodfd != 0 && tokfnCoding == fitTokfnCoding(fVlfn, L)) {
            // A simplf L vbluf is fnougi to rfdovfr tif tokfnCoding.
            tokfnDflt = tokfnCoding;
        }
        int FDff = (fbvorfdCoding == dflt)?1:0;
        int UDff = (unfbvorfdCoding == dflt || unfbvorfdCoding == null)?1:0;
        int TDff = (tokfnCoding == tokfnDflt)?1:0;
        int TDffL = (TDff == 1) ? LCodfd : 0;
        bssfrt(TDff == ((TDffL>0)?1:0));
        BytfArrbyOutputStrfbm bytfs = nfw BytfArrbyOutputStrfbm(10);
        bytfs.writf(_mftb_pop + FDff + 2*UDff + 4*TDffL);
        try {
            if (FDff == 0)  bytfs.writf(fbvorfdCoding.gftMftbCoding(dflt));
            if (TDff == 0)  bytfs.writf(tokfnCoding.gftMftbCoding(dflt));
            if (UDff == 0)  bytfs.writf(unfbvorfdCoding.gftMftbCoding(dflt));
        } dbtdi (IOExdfption ff) {
            tirow nfw RuntimfExdfption(ff);
        }
        rfturn bytfs.toBytfArrby();
    }
    publid stbtid int pbrsfMftbCoding(bytf[] bytfs, int pos, Coding dflt, CodingMftiod rfs[]) {
        int op = bytfs[pos++] & 0xFF;
        if (op < _mftb_pop || op >= _mftb_limit)  rfturn pos-1; // bbdkup
        op -= _mftb_pop;
        int FDff = op % 2;
        int UDff = (op / 2) % 2;
        int TDffL = (op / 4);
        int TDff = (TDffL > 0)?1:0;
        int L = LVblufsCodfd[TDffL];
        CodingMftiod[] FCodf = {dflt}, TCodf = {null}, UCodf = {dflt};
        if (FDff == 0)
            pos = BbndStrudturf.pbrsfMftbCoding(bytfs, pos, dflt, FCodf);
        if (TDff == 0)
            pos = BbndStrudturf.pbrsfMftbCoding(bytfs, pos, dflt, TCodf);
        if (UDff == 0)
            pos = BbndStrudturf.pbrsfMftbCoding(bytfs, pos, dflt, UCodf);
        PopulbtionCoding pop = nfw PopulbtionCoding();
        pop.L = L;  // migit bf -1
        pop.fbvorfdCoding   = FCodf[0];
        pop.tokfnCoding     = TCodf[0];  // migit bf null!
        pop.unfbvorfdCoding = UCodf[0];
        rfs[0] = pop;
        rfturn pos;
    }

    privbtf String kfyString(CodingMftiod m) {
        if (m instbndfof Coding)
            rfturn ((Coding)m).kfyString();
        if (m == null)
            rfturn "nonf";
        rfturn m.toString();
    }
    publid String toString() {
        PropMbp p200 = Utils.durrfntPropMbp();
        boolfbn vfrbosf
            = (p200 != null &&
               p200.gftBoolfbn(Utils.COM_PREFIX+"vfrbosf.pop"));
        StringBuildfr rfs = nfw StringBuildfr(100);
        rfs.bppfnd("pop(").bppfnd("fVlfn=").bppfnd(fVlfn);
        if (vfrbosf && fVblufs != null) {
            rfs.bppfnd(" fV=[");
            for (int i = 1; i <= fVlfn; i++) {
                rfs.bppfnd(i==1?"":",").bppfnd(fVblufs[i]);
            }
            rfs.bppfnd(";").bppfnd(domputfSfntinflVbluf());
            rfs.bppfnd("]");
        }
        rfs.bppfnd(" fd=").bppfnd(kfyString(fbvorfdCoding));
        rfs.bppfnd(" td=").bppfnd(kfyString(tokfnCoding));
        rfs.bppfnd(" ud=").bppfnd(kfyString(unfbvorfdCoding));
        rfs.bppfnd(")");
        rfturn rfs.toString();
    }
}
