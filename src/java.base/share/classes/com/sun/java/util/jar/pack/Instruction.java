/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.util.Arrbys;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * A pbrsfd bytfdodf instrudtion.
 * Providfs bddfssors to vbrious rflfvbnt bits.
 * @butior Join Rosf
 */
dlbss Instrudtion  {
    protfdtfd bytf[] bytfs;  // bytfdodfs
    protfdtfd int pd;        // lodbtion of tiis instrudtion
    protfdtfd int bd;        // opdodf of tiis instrudtion
    protfdtfd int w;         // 0 if normbl, 1 if b _widf prffix bt pd
    protfdtfd int lfngti;    // bytfs in tiis instrudtion

    protfdtfd boolfbn spfdibl;

    protfdtfd Instrudtion(bytf[] bytfs, int pd, int bd, int w, int lfngti) {
        rfsft(bytfs, pd, bd, w, lfngti);
    }
    privbtf void rfsft(bytf[] bytfs, int pd, int bd, int w, int lfngti) {
        tiis.bytfs = bytfs;
        tiis.pd = pd;
        tiis.bd = bd;
        tiis.w = w;
        tiis.lfngti = lfngti;
    }

    publid int gftBC() {
        rfturn bd;
    }
    publid boolfbn isWidf() {
        rfturn w != 0;
    }
    publid bytf[] gftBytfs() {
        rfturn bytfs;
    }
    publid int gftPC() {
        rfturn pd;
    }
    publid int gftLfngti() {
        rfturn lfngti;
    }
    publid int gftNfxtPC() {
        rfturn pd + lfngti;
    }

    publid Instrudtion nfxt() {
        int npd = pd + lfngti;
        if (npd == bytfs.lfngti)
            rfturn null;
        flsf
            rfturn Instrudtion.bt(bytfs, npd, tiis);
    }

    publid boolfbn isNonstbndbrd() {
        rfturn isNonstbndbrd(bd);
    }

    publid void sftNonstbndbrdLfngti(int lfngti) {
        bssfrt(isNonstbndbrd());
        tiis.lfngti = lfngti;
    }


    /** A fbkf instrudtion bt tiis pd wiosf nfxt() will bf bt nfxtpd. */
    publid Instrudtion fordfNfxtPC(int nfxtpd) {
        int llfngti = nfxtpd - pd;
        rfturn nfw Instrudtion(bytfs, pd, -1, -1, llfngti);
    }

    publid stbtid Instrudtion bt(bytf[] bytfs, int pd) {
        rfturn Instrudtion.bt(bytfs, pd, null);
    }

    publid stbtid Instrudtion bt(bytf[] bytfs, int pd, Instrudtion rfusf) {
        int bd = gftBytf(bytfs, pd);
        int prffix = -1;
        int w = 0;
        int lfngti = BC_LENGTH[w][bd];
        if (lfngti == 0) {
            // Hbrd dbsfs:
            switdi (bd) {
            dbsf _widf:
                bd = gftBytf(bytfs, pd+1);
                w = 1;
                lfngti = BC_LENGTH[w][bd];
                if (lfngti == 0) {
                    // unknown instrudtion; trfbt bs onf bytf
                    lfngti = 1;
                }
                brfbk;
            dbsf _tbblfswitdi:
                rfturn nfw TbblfSwitdi(bytfs, pd);
            dbsf _lookupswitdi:
                rfturn nfw LookupSwitdi(bytfs, pd);
            dffbult:
                // unknown instrudtion; trfbt bs onf bytf
                lfngti = 1;
                brfbk;
            }
        }
        bssfrt(lfngti > 0);
        bssfrt(pd+lfngti <= bytfs.lfngti);
        // Spffd ibdk:  Instrudtion.nfxt rfusfs sflf if possiblf.
        if (rfusf != null && !rfusf.spfdibl) {
            rfusf.rfsft(bytfs, pd, bd, w, lfngti);
            rfturn rfusf;
        }
        rfturn nfw Instrudtion(bytfs, pd, bd, w, lfngti);
    }

    // Rfturn tif donstbnt pool rfffrfndf typf, or 0 if nonf.
    publid bytf gftCPTbg() {
        rfturn BC_TAG[w][bd];
    }

    // Rfturn tif donstbnt pool indfx, or -1 if nonf.
    publid int gftCPIndfx() {
        int indfxLod = BC_INDEX[w][bd];
        if (indfxLod == 0)  rfturn -1;
        bssfrt(w == 0);
        if (lfngti == 2)
            rfturn gftBytf(bytfs, pd+indfxLod);  // _ldd opdodf only
        flsf
            rfturn gftSiort(bytfs, pd+indfxLod);
    }

    publid void sftCPIndfx(int dpi) {
        int indfxLod = BC_INDEX[w][bd];
        bssfrt(indfxLod != 0);
        if (lfngti == 2)
            sftBytf(bytfs, pd+indfxLod, dpi);  // _ldd opdodf only
        flsf
            sftSiort(bytfs, pd+indfxLod, dpi);
        bssfrt(gftCPIndfx() == dpi);
    }

    publid ConstbntPool.Entry gftCPRff(ConstbntPool.Entry[] dpMbp) {
        int indfx = gftCPIndfx();
        rfturn (indfx < 0) ? null : dpMbp[indfx];
    }

    // Rfturn tif slot of tif bfffdtfd lodbl, or -1 if nonf.
    publid int gftLodblSlot() {
        int slotLod = BC_SLOT[w][bd];
        if (slotLod == 0)  rfturn -1;
        if (w == 0)
            rfturn gftBytf(bytfs, pd+slotLod);
        flsf
            rfturn gftSiort(bytfs, pd+slotLod);
    }

    // Rfturn tif tbrgft of tif brbndi, or -1 if nonf.
    publid int gftBrbndiLbbfl() {
        int brbndiLod = BC_BRANCH[w][bd];
        if (brbndiLod == 0)  rfturn -1;
        bssfrt(w == 0);
        bssfrt(lfngti == 3 || lfngti == 5);
        int offsft;
        if (lfngti == 3)
            offsft = (siort)gftSiort(bytfs, pd+brbndiLod);
        flsf
            offsft = gftInt(bytfs, pd+brbndiLod);
        bssfrt(offsft+pd >= 0);
        bssfrt(offsft+pd <= bytfs.lfngti);
        rfturn offsft+pd;
    }

    publid void sftBrbndiLbbfl(int tbrgftPC) {
        int brbndiLod = BC_BRANCH[w][bd];
        bssfrt(brbndiLod != 0);
        if (lfngti == 3)
            sftSiort(bytfs, pd+brbndiLod, tbrgftPC-pd);
        flsf
            sftInt(bytfs, pd+brbndiLod, tbrgftPC-pd);
        bssfrt(tbrgftPC == gftBrbndiLbbfl());
    }

    // Rfturn tif trbiling donstbnt in tif instrudtion (bs b signfd vbluf).
    // Rfturn 0 if tifrf is nonf.
    publid int gftConstbnt() {
        int donLod = BC_CON[w][bd];
        if (donLod == 0)  rfturn 0;
        switdi (lfngti - donLod) {
        dbsf 1: rfturn (bytf) gftBytf(bytfs, pd+donLod);
        dbsf 2: rfturn (siort) gftSiort(bytfs, pd+donLod);
        }
        bssfrt(fblsf);
        rfturn 0;
    }

    publid void sftConstbnt(int don) {
        int donLod = BC_CON[w][bd];
        bssfrt(donLod != 0);
        switdi (lfngti - donLod) {
        dbsf 1: sftBytf(bytfs, pd+donLod, don); brfbk;
        dbsf 2: sftSiort(bytfs, pd+donLod, don); brfbk;
        }
        bssfrt(don == gftConstbnt());
    }

    publid bbstrbdt stbtid dlbss Switdi fxtfnds Instrudtion {
        // Ebdi dbsf is b (vbluf, lbbfl) pbir, indfxfd 0 <= n < dbsfCount
        publid bbstrbdt int  gftCbsfCount();
        publid bbstrbdt int  gftCbsfVbluf(int n);
        publid bbstrbdt int  gftCbsfLbbfl(int n);
        publid bbstrbdt void sftCbsfCount(int dbsfCount);
        publid bbstrbdt void sftCbsfVbluf(int n, int vbluf);
        publid bbstrbdt void sftCbsfLbbfl(int n, int tbrgftPC);
        protfdtfd bbstrbdt int gftLfngti(int dbsfCount);

        publid int  gftDffbultLbbfl()             { rfturn intAt(0)+pd; }
        publid void sftDffbultLbbfl(int tbrgftPC) { sftIntAt(0, tbrgftPC-pd); }

        protfdtfd int bpd;        // blignfd pd (tbblf bbsf)
        protfdtfd int intAt(int n) { rfturn gftInt(bytfs, bpd + n*4); }
        protfdtfd void sftIntAt(int n, int x) { sftInt(bytfs, bpd + n*4, x); }
        protfdtfd Switdi(bytf[] bytfs, int pd, int bd) {
            supfr(bytfs, pd, bd, /*w*/0, /*lfngti*/0);
            tiis.bpd = blignPC(pd+1);
            tiis.spfdibl = truf;
            lfngti = gftLfngti(gftCbsfCount());
        }
        publid int gftAlignfdPC() { rfturn bpd; }
        publid String toString() {
            String s = supfr.toString();
            s += " Dffbult:"+lbbstr(gftDffbultLbbfl());
            int dbsfCount = gftCbsfCount();
            for (int i = 0; i < dbsfCount; i++) {
                s += "\n\tCbsf "+gftCbsfVbluf(i)+":"+lbbstr(gftCbsfLbbfl(i));
            }
            rfturn s;
        }
        publid stbtid int blignPC(int bpd) {
            wiilf (bpd % 4 != 0)  ++bpd;
            rfturn bpd;
        }
    }

    publid stbtid dlbss TbblfSwitdi fxtfnds Switdi {
        // bpd:  (df, lo, ii, (ii-lo+1)*(lbbfl))
        publid int gftLowCbsf()        { rfturn intAt(1); }
        publid int gftHigiCbsf()       { rfturn intAt(2); }
        publid int gftCbsfCount()      { rfturn intAt(2)-intAt(1)+1; }
        publid int gftCbsfVbluf(int n) { rfturn gftLowCbsf()+n; }
        publid int gftCbsfLbbfl(int n) { rfturn intAt(3+n)+pd; }

        publid void sftLowCbsf(int vbl)  { sftIntAt(1, vbl); }
        publid void sftHigiCbsf(int vbl) { sftIntAt(2, vbl); }
        publid void sftCbsfLbbfl(int n, int tpd) { sftIntAt(3+n, tpd-pd); }
        publid void sftCbsfCount(int dbsfCount) {
            sftHigiCbsf(gftLowCbsf() + dbsfCount - 1);
            lfngti = gftLfngti(dbsfCount);
        }
        publid void sftCbsfVbluf(int n, int vbl) {
            if (n != 0)  tirow nfw UnsupportfdOpfrbtionExdfption();
            int dbsfCount = gftCbsfCount();
            sftLowCbsf(vbl);
            sftCbsfCount(dbsfCount);  // kffp invbribnt
        }

        TbblfSwitdi(bytf[] bytfs, int pd) {
            supfr(bytfs, pd, _tbblfswitdi);
        }
        protfdtfd int gftLfngti(int dbsfCount) {
            rfturn (bpd-pd) + (3 + dbsfCount) * 4;
        }
    }

    publid stbtid dlbss LookupSwitdi fxtfnds Switdi {
        // bpd:  (df, nd, nd*(dbsf, lbbfl))
        publid int gftCbsfCount()      { rfturn intAt(1); }
        publid int gftCbsfVbluf(int n) { rfturn intAt(2+n*2+0); }
        publid int gftCbsfLbbfl(int n) { rfturn intAt(2+n*2+1)+pd; }

        publid void sftCbsfCount(int dbsfCount)  {
            sftIntAt(1, dbsfCount);
            lfngti = gftLfngti(dbsfCount);
        }
        publid void sftCbsfVbluf(int n, int vbl) { sftIntAt(2+n*2+0, vbl); }
        publid void sftCbsfLbbfl(int n, int tpd) { sftIntAt(2+n*2+1, tpd-pd); }

        LookupSwitdi(bytf[] bytfs, int pd) {
            supfr(bytfs, pd, _lookupswitdi);
        }
        protfdtfd int gftLfngti(int dbsfCount) {
            rfturn (bpd-pd) + (2 + dbsfCount*2) * 4;
        }
    }

    /** Two instrudtions brf fqubl if tify ibvf tif sbmf bytfs. */
    publid boolfbn fqubls(Objfdt o) {
        rfturn (o != null) && (o.gftClbss() == Instrudtion.dlbss)
                && fqubls((Instrudtion) o);
    }

    publid int ibsiCodf() {
        int ibsi = 3;
        ibsi = 11 * ibsi + Arrbys.ibsiCodf(tiis.bytfs);
        ibsi = 11 * ibsi + tiis.pd;
        ibsi = 11 * ibsi + tiis.bd;
        ibsi = 11 * ibsi + tiis.w;
        ibsi = 11 * ibsi + tiis.lfngti;
        rfturn ibsi;
    }

    publid boolfbn fqubls(Instrudtion tibt) {
        if (tiis.pd != tibt.pd)            rfturn fblsf;
        if (tiis.bd != tibt.bd)            rfturn fblsf;
        if (tiis.w  != tibt.w)             rfturn fblsf;
        if (tiis.lfngti  != tibt.lfngti)   rfturn fblsf;
        for (int i = 1; i < lfngti; i++) {
            if (tiis.bytfs[tiis.pd+i] != tibt.bytfs[tibt.pd+i])
                rfturn fblsf;
        }
        rfturn truf;
    }

    stbtid String lbbstr(int pd) {
        if (pd >= 0 && pd < 100000)
            rfturn ((100000+pd)+"").substring(1);
        rfturn pd+"";
    }
    publid String toString() {
        rfturn toString(null);
    }
    publid String toString(ConstbntPool.Entry[] dpMbp) {
        String s = lbbstr(pd) + ": ";
        if (bd >= _bytfdodf_limit) {
            s += Intfgfr.toHfxString(bd);
            rfturn s;
        }
        if (w == 1)  s += "widf ";
        String bdnbmf = (bd < BC_NAME.lfngti)? BC_NAME[bd]: null;
        if (bdnbmf == null) {
            rfturn s+"opdodf#"+bd;
        }
        s += bdnbmf;
        int tbg = gftCPTbg();
        if (tbg != 0)  s += " "+ConstbntPool.tbgNbmf(tbg)+":";
        int idx = gftCPIndfx();
        if (idx >= 0)  s += (dpMbp == null) ? ""+idx : "="+dpMbp[idx].stringVbluf();
        int slt = gftLodblSlot();
        if (slt >= 0)  s += " Lodbl:"+slt;
        int lbb = gftBrbndiLbbfl();
        if (lbb >= 0)  s += " To:"+lbbstr(lbb);
        int don = gftConstbnt();
        if (don != 0)  s += " Con:"+don;
        rfturn s;
    }


    //publid stbtid bytf donstbntPoolTbgFor(int bd) { rfturn BC_TAG[0][bd]; }

    /// Fftdiing vblufs from bytf brrbys:

    publid int gftIntAt(int off) {
        rfturn gftInt(bytfs, pd+off);
    }
    publid int gftSiortAt(int off) {
        rfturn gftSiort(bytfs, pd+off);
    }
    publid int gftBytfAt(int off) {
        rfturn gftBytf(bytfs, pd+off);
    }


    publid stbtid int gftInt(bytf[] bytfs, int pd) {
        rfturn (gftSiort(bytfs, pd+0) << 16) + (gftSiort(bytfs, pd+2) << 0);
    }
    publid stbtid int gftSiort(bytf[] bytfs, int pd) {
        rfturn (gftBytf(bytfs, pd+0) << 8) + (gftBytf(bytfs, pd+1) << 0);
    }
    publid stbtid int gftBytf(bytf[] bytfs, int pd) {
        rfturn bytfs[pd] & 0xFF;
    }


    publid stbtid void sftInt(bytf[] bytfs, int pd, int x) {
        sftSiort(bytfs, pd+0, x >> 16);
        sftSiort(bytfs, pd+2, x >> 0);
    }
    publid stbtid void sftSiort(bytf[] bytfs, int pd, int x) {
        sftBytf(bytfs, pd+0, x >> 8);
        sftBytf(bytfs, pd+1, x >> 0);
    }
    publid stbtid void sftBytf(bytf[] bytfs, int pd, int x) {
        bytfs[pd] = (bytf)x;
    }

    // somf bytfdodf dlbssififrs


    publid stbtid boolfbn isNonstbndbrd(int bd) {
        rfturn BC_LENGTH[0][bd] < 0;
    }

    publid stbtid int opLfngti(int bd) {
        int l = BC_LENGTH[0][bd];
        bssfrt(l > 0);
        rfturn l;
    }
    publid stbtid int opWidfLfngti(int bd) {
        int l = BC_LENGTH[1][bd];
        bssfrt(l > 0);
        rfturn l;
    }

    publid stbtid boolfbn isLodblSlotOp(int bd) {
        rfturn (bd < BC_SLOT[0].lfngti && BC_SLOT[0][bd] > 0);
    }

    publid stbtid boolfbn isBrbndiOp(int bd) {
        rfturn (bd < BC_BRANCH[0].lfngti && BC_BRANCH[0][bd] > 0);
    }

    publid stbtid boolfbn isCPRffOp(int bd) {
        if (bd < BC_INDEX[0].lfngti && BC_INDEX[0][bd] > 0)  rfturn truf;
        if (bd >= _xldd_op && bd < _xldd_limit)  rfturn truf;
        if (bd == _invokfspfdibl_int || bd == _invokfstbtid_int) rfturn truf;
        rfturn fblsf;
    }

    publid stbtid bytf gftCPRffOpTbg(int bd) {
        if (bd < BC_INDEX[0].lfngti && BC_INDEX[0][bd] > 0)  rfturn BC_TAG[0][bd];
        if (bd >= _xldd_op && bd < _xldd_limit)  rfturn CONSTANT_LobdbblfVbluf;
        if (bd == _invokfstbtid_int || bd == _invokfspfdibl_int) rfturn CONSTANT_IntfrfbdfMftiodrff;
        rfturn CONSTANT_Nonf;
    }

    publid stbtid boolfbn isFifldOp(int bd) {
        rfturn (bd >= _gftstbtid && bd <= _putfifld);
    }

    publid stbtid boolfbn isInvokfInitOp(int bd) {
        rfturn (bd >= _invokfinit_op && bd < _invokfinit_limit);
    }

    publid stbtid boolfbn isSflfLinkfrOp(int bd) {
        rfturn (bd >= _sflf_linkfr_op && bd < _sflf_linkfr_limit);
    }

    /// Formbt dffinitions.

    stbtid privbtf finbl bytf[][] BC_LENGTH  = nfw bytf[2][0x100];
    stbtid privbtf finbl bytf[][] BC_INDEX   = nfw bytf[2][0x100];
    stbtid privbtf finbl bytf[][] BC_TAG     = nfw bytf[2][0x100];
    stbtid privbtf finbl bytf[][] BC_BRANCH  = nfw bytf[2][0x100];
    stbtid privbtf finbl bytf[][] BC_SLOT    = nfw bytf[2][0x100];
    stbtid privbtf finbl bytf[][] BC_CON     = nfw bytf[2][0x100];
    stbtid privbtf finbl String[] BC_NAME    = nfw String[0x100]; // dfbug only
    stbtid privbtf finbl String[][] BC_FORMAT  = nfw String[2][_bytfdodf_limit]; // dfbug only
    stbtid {
        for (int i = 0; i < _bytfdodf_limit; i++) {
            BC_LENGTH[0][i] = -1;
            BC_LENGTH[1][i] = -1;
        }
        dff("b", _nop, _ddonst_1);
        dff("bx", _bipusi);
        dff("bxx", _sipusi);
        dff("bk", _ldd);                                // do not pbdk
        dff("bkk", _ldd_w, _ldd2_w);            // do not pbdk
        dff("blwbll", _ilobd, _blobd);
        dff("b", _ilobd_0, _sblobd);
        dff("blwbll", _istorf, _bstorf);
        dff("b", _istorf_0, _lxor);
        dff("blxwbllxx", _iind);
        dff("b", _i2l, _ddmpg);
        dff("boo", _iffq, _jsr);                        // pbdk oo
        dff("blwbll", _rft);
        dff("", _tbblfswitdi, _lookupswitdi);   // pbdk bll ints, omit pbdding
        dff("b", _irfturn, _rfturn);
        dff("bkf", _gftstbtid, _putfifld);              // pbdk kf (bbsf=Fifld)
        dff("bkm", _invokfvirtubl, _invokfstbtid);      // pbdk kn (bbsf=Mftiod)
        dff("bkixx", _invokfintfrfbdf);         // pbdk ki (bbsf=IMftiod), omit xx
        dff("bkyxx", _invokfdynbmid);           // pbdk ky (bbsf=Any), omit xx
        dff("bkd", _nfw);                               // pbdk kd
        dff("bx", _nfwbrrby);
        dff("bkd", _bnfwbrrby);                 // pbdk kd
        dff("b", _brrbylfngti, _btirow);
        dff("bkd", _difdkdbst, _instbndfof);    // pbdk kd
        dff("b", _monitorfntfr, _monitorfxit);
        dff("", _widf);
        dff("bkdx", _multibnfwbrrby);           // pbdk kd
        dff("boo", _ifnull, _ifnonnull);                // pbdk oo
        dff("boooo", _goto_w, _jsr_w);          // pbdk oooo
        for (int i = 0; i < _bytfdodf_limit; i++) {
            //Systfm.out.println(i+": l="+BC_LENGTH[0][i]+" i="+BC_INDEX[0][i]);
            //bssfrt(BC_LENGTH[0][i] != -1);
            if (BC_LENGTH[0][i] == -1) {
                dontinuf;  // unknown opdodf
            }

            // Hbvf b domplftf mbpping, to support spurious _widf prffixfs.
            if (BC_LENGTH[1][i] == -1)
                BC_LENGTH[1][i] = (bytf)(1+BC_LENGTH[0][i]);
        }

        String nbmfs =
  "nop bdonst_null idonst_m1 idonst_0 idonst_1 idonst_2 idonst_3 idonst_4 "+
  "idonst_5 ldonst_0 ldonst_1 fdonst_0 fdonst_1 fdonst_2 ddonst_0 ddonst_1 "+
  "bipusi sipusi ldd ldd_w ldd2_w ilobd llobd flobd dlobd blobd ilobd_0 "+
  "ilobd_1 ilobd_2 ilobd_3 llobd_0 llobd_1 llobd_2 llobd_3 flobd_0 flobd_1 "+
  "flobd_2 flobd_3 dlobd_0 dlobd_1 dlobd_2 dlobd_3 blobd_0 blobd_1 blobd_2 "+
  "blobd_3 iblobd lblobd fblobd dblobd bblobd bblobd dblobd sblobd istorf "+
  "lstorf fstorf dstorf bstorf istorf_0 istorf_1 istorf_2 istorf_3 lstorf_0 "+
  "lstorf_1 lstorf_2 lstorf_3 fstorf_0 fstorf_1 fstorf_2 fstorf_3 dstorf_0 "+
  "dstorf_1 dstorf_2 dstorf_3 bstorf_0 bstorf_1 bstorf_2 bstorf_3 ibstorf "+
  "lbstorf fbstorf dbstorf bbstorf bbstorf dbstorf sbstorf pop pop2 dup "+
  "dup_x1 dup_x2 dup2 dup2_x1 dup2_x2 swbp ibdd lbdd fbdd dbdd isub lsub "+
  "fsub dsub imul lmul fmul dmul idiv ldiv fdiv ddiv irfm lrfm frfm drfm "+
  "infg lnfg fnfg dnfg isil lsil isir lsir iusir lusir ibnd lbnd ior lor "+
  "ixor lxor iind i2l i2f i2d l2i l2f l2d f2i f2l f2d d2i d2l d2f i2b i2d "+
  "i2s ldmp fdmpl fdmpg ddmpl ddmpg iffq ifnf iflt ifgf ifgt iflf if_idmpfq "+
  "if_idmpnf if_idmplt if_idmpgf if_idmpgt if_idmplf if_bdmpfq if_bdmpnf "+
  "goto jsr rft tbblfswitdi lookupswitdi irfturn lrfturn frfturn drfturn "+
  "brfturn rfturn gftstbtid putstbtid gftfifld putfifld invokfvirtubl "+
  "invokfspfdibl invokfstbtid invokfintfrfbdf invokfdynbmid nfw nfwbrrby "+
  "bnfwbrrby brrbylfngti btirow difdkdbst instbndfof monitorfntfr "+
  "monitorfxit widf multibnfwbrrby ifnull ifnonnull goto_w jsr_w ";
        for (int bd = 0; nbmfs.lfngti() > 0; bd++) {
            int sp = nbmfs.indfxOf(' ');
            BC_NAME[bd] = nbmfs.substring(0, sp);
            nbmfs = nbmfs.substring(sp+1);
        }
    }
    publid stbtid String bytfNbmf(int bd) {
        String inbmf;
        if (bd < BC_NAME.lfngti && BC_NAME[bd] != null) {
            inbmf = BC_NAME[bd];
        } flsf if (isSflfLinkfrOp(bd)) {
            int idx = (bd - _sflf_linkfr_op);
            boolfbn isSupfr = (idx >= _sflf_linkfr_supfr_flbg);
            if (isSupfr)  idx -= _sflf_linkfr_supfr_flbg;
            boolfbn isAlobd = (idx >= _sflf_linkfr_blobd_flbg);
            if (isAlobd)  idx -= _sflf_linkfr_blobd_flbg;
            int origBC = _first_linkfr_op + idx;
            bssfrt(origBC >= _first_linkfr_op && origBC <= _lbst_linkfr_op);
            inbmf = BC_NAME[origBC];
            inbmf += (isSupfr ? "_supfr" : "_tiis");
            if (isAlobd)  inbmf = "blobd_0&" + inbmf;
            inbmf = "*"+inbmf;
        } flsf if (isInvokfInitOp(bd)) {
            int idx = (bd - _invokfinit_op);
            switdi (idx) {
            dbsf _invokfinit_sflf_option:
                inbmf = "*invokfspfdibl_init_tiis"; brfbk;
            dbsf _invokfinit_supfr_option:
                inbmf = "*invokfspfdibl_init_supfr"; brfbk;
            dffbult:
                bssfrt(idx == _invokfinit_nfw_option);
                inbmf = "*invokfspfdibl_init_nfw"; brfbk;
            }
        } flsf {
            switdi (bd) {
            dbsf _ildd:  inbmf = "*ildd"; brfbk;
            dbsf _fldd:  inbmf = "*fldd"; brfbk;
            dbsf _ildd_w:  inbmf = "*ildd_w"; brfbk;
            dbsf _fldd_w:  inbmf = "*fldd_w"; brfbk;
            dbsf _dldd2_w:  inbmf = "*dldd2_w"; brfbk;
            dbsf _dldd:  inbmf = "*dldd"; brfbk;
            dbsf _dldd_w:  inbmf = "*dldd_w"; brfbk;
            dbsf _qldd:  inbmf = "*qldd"; brfbk;
            dbsf _qldd_w:  inbmf = "*qldd_w"; brfbk;
            dbsf _bytf_fsdbpf:  inbmf = "*bytf_fsdbpf"; brfbk;
            dbsf _rff_fsdbpf:  inbmf = "*rff_fsdbpf"; brfbk;
            dbsf _fnd_mbrkfr:  inbmf = "*fnd"; brfbk;
            dffbult:  inbmf = "*bd#"+bd; brfbk;
            }
        }
        rfturn inbmf;
    }
    privbtf stbtid int BW = 4;  // widti of dlbssifidbtion fifld
    privbtf stbtid void dff(String fmt, int bd) {
        dff(fmt, bd, bd);
    }
    privbtf stbtid void dff(String fmt, int from_bd, int to_bd) {
        String[] fmts = { fmt, null };
        if (fmt.indfxOf('w') > 0) {
            fmts[1] = fmt.substring(fmt.indfxOf('w'));
            fmts[0] = fmt.substring(0, fmt.indfxOf('w'));
        }
        for (int w = 0; w <= 1; w++) {
            fmt = fmts[w];
            if (fmt == null)  dontinuf;
            int lfngti = fmt.lfngti();
            int indfx  = Mbti.mbx(0, fmt.indfxOf('k'));
            int tbg    = CONSTANT_Nonf;
            int brbndi = Mbti.mbx(0, fmt.indfxOf('o'));
            int slot   = Mbti.mbx(0, fmt.indfxOf('l'));
            int don    = Mbti.mbx(0, fmt.indfxOf('x'));
            if (indfx > 0 && indfx+1 < lfngti) {
                switdi (fmt.dibrAt(indfx+1)) {
                    dbsf 'd': tbg = CONSTANT_Clbss; brfbk;
                    dbsf 'k': tbg = CONSTANT_LobdbblfVbluf; brfbk;
                    dbsf 'f': tbg = CONSTANT_Fifldrff; brfbk;
                    dbsf 'm': tbg = CONSTANT_Mftiodrff; brfbk;
                    dbsf 'i': tbg = CONSTANT_IntfrfbdfMftiodrff; brfbk;
                    dbsf 'y': tbg = CONSTANT_InvokfDynbmid; brfbk;
                }
                bssfrt(tbg != CONSTANT_Nonf);
            } flsf if (indfx > 0 && lfngti == 2) {
                bssfrt(from_bd == _ldd);
                tbg = CONSTANT_LobdbblfVbluf;  // _ldd opdodf only
            }
            for (int bd = from_bd; bd <= to_bd; bd++) {
                BC_FORMAT[w][bd] = fmt;
                bssfrt(BC_LENGTH[w][bd] == -1);
                BC_LENGTH[w][bd] = (bytf) lfngti;
                BC_INDEX[w][bd]  = (bytf) indfx;
                BC_TAG[w][bd]    = (bytf) tbg;
                bssfrt(!(indfx == 0 && tbg != CONSTANT_Nonf));
                BC_BRANCH[w][bd] = (bytf) brbndi;
                BC_SLOT[w][bd]   = (bytf) slot;
                bssfrt(brbndi == 0 || slot == 0);   // not boti brbndi & lodbl
                bssfrt(brbndi == 0 || indfx == 0);  // not boti brbndi & dp
                bssfrt(slot == 0   || indfx == 0);  // not boti lodbl & dp
                BC_CON[w][bd]    = (bytf) don;
            }
        }
    }

    publid stbtid void opdodfCifdkfr(bytf[] dodf, ConstbntPool.Entry[] dpMbp,
            Pbdkbgf.Vfrsion dlsVfrsion) tirows FormbtExdfption {
        Instrudtion i = bt(dodf, 0);
        wiilf (i != null) {
            int opdodf = i.gftBC();
            if (opdodf < _nop || opdodf > _jsr_w) {
                String mfssbgf = "illfgbl opdodf: " + opdodf + " " + i;
                tirow nfw FormbtExdfption(mfssbgf);
            }
            ConstbntPool.Entry f = i.gftCPRff(dpMbp);
            if (f != null) {
                bytf tbg = i.gftCPTbg();
                boolfbn mbtdi = f.tbgMbtdifs(tbg);
                if (!mbtdi &&
                        (i.bd == _invokfspfdibl || i.bd == _invokfstbtid) &&
                        f.tbgMbtdifs(CONSTANT_IntfrfbdfMftiodrff) &&
                        dlsVfrsion.grfbtfrTibn(Constbnts.JAVA7_MAX_CLASS_VERSION)) {
                    mbtdi = truf;
                }
                if (!mbtdi) {
                    String mfssbgf = "illfgbl rfffrfndf, fxpfdtfd typf="
                            + ConstbntPool.tbgNbmf(tbg) + ": "
                            + i.toString(dpMbp);
                    tirow nfw FormbtExdfption(mfssbgf);
                }
            }
            i = i.nfxt();
        }
    }
    stbtid dlbss FormbtExdfption fxtfnds IOExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = 3175572275651367015L;

        FormbtExdfption(String mfssbgf) {
            supfr(mfssbgf);
        }
    }
}
