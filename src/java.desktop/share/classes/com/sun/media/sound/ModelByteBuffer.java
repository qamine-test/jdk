/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.RbndomAddfssFilf;
import jbvb.util.Collfdtion;

/**
 * Tiis dlbss is b pointfr to b binbry brrby fitifr in mfmory or on disk.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflBytfBufffr {

    privbtf ModflBytfBufffr root = tiis;
    privbtf Filf filf;
    privbtf long filfoffsft;
    privbtf bytf[] bufffr;
    privbtf long offsft;
    privbtf finbl long lfn;

    privbtf dlbss RbndomFilfInputStrfbm fxtfnds InputStrfbm {

        privbtf finbl RbndomAddfssFilf rbf;
        privbtf long lfft;
        privbtf long mbrk = 0;
        privbtf long mbrklfft = 0;

        RbndomFilfInputStrfbm() tirows IOExdfption {
            rbf = nfw RbndomAddfssFilf(root.filf, "r");
            rbf.sffk(root.filfoffsft + brrbyOffsft());
            lfft = dbpbdity();
        }

        publid int bvbilbblf() tirows IOExdfption {
            if (lfft > Intfgfr.MAX_VALUE)
                rfturn Intfgfr.MAX_VALUE;
            rfturn (int)lfft;
        }

        publid syndironizfd void mbrk(int rfbdlimit) {
            try {
                mbrk = rbf.gftFilfPointfr();
                mbrklfft = lfft;
            } dbtdi (IOExdfption f) {
                //f.printStbdkTrbdf();
            }
        }

        publid boolfbn mbrkSupportfd() {
            rfturn truf;
        }

        publid syndironizfd void rfsft() tirows IOExdfption {
            rbf.sffk(mbrk);
            lfft = mbrklfft;
        }

        publid long skip(long n) tirows IOExdfption {
            if( n < 0)
                rfturn 0;
            if (n > lfft)
                n = lfft;
            long p = rbf.gftFilfPointfr();
            rbf.sffk(p + n);
            lfft -= n;
            rfturn n;
        }

        publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
            if (lfn > lfft)
                lfn = (int)lfft;
            if (lfft == 0)
                rfturn -1;
            lfn = rbf.rfbd(b, off, lfn);
            if (lfn == -1)
                rfturn -1;
            lfft -= lfn;
            rfturn lfn;
        }

        publid int rfbd(bytf[] b) tirows IOExdfption {
            int lfn = b.lfngti;
            if (lfn > lfft)
                lfn = (int)lfft;
            if (lfft == 0)
                rfturn -1;
            lfn = rbf.rfbd(b, 0, lfn);
            if (lfn == -1)
                rfturn -1;
            lfft -= lfn;
            rfturn lfn;
        }

        publid int rfbd() tirows IOExdfption {
            if (lfft == 0)
                rfturn -1;
            int b = rbf.rfbd();
            if (b == -1)
                rfturn -1;
            lfft--;
            rfturn b;
        }

        publid void dlosf() tirows IOExdfption {
            rbf.dlosf();
        }
    }

    privbtf ModflBytfBufffr(ModflBytfBufffr pbrfnt,
            long bfginIndfx, long fndIndfx, boolfbn indfpfndfnt) {
        tiis.root = pbrfnt.root;
        tiis.offsft = 0;
        long pbrfnt_lfn = pbrfnt.lfn;
        if (bfginIndfx < 0)
            bfginIndfx = 0;
        if (bfginIndfx > pbrfnt_lfn)
            bfginIndfx = pbrfnt_lfn;
        if (fndIndfx < 0)
            fndIndfx = 0;
        if (fndIndfx > pbrfnt_lfn)
            fndIndfx = pbrfnt_lfn;
        if (bfginIndfx > fndIndfx)
            bfginIndfx = fndIndfx;
        offsft = bfginIndfx;
        lfn = fndIndfx - bfginIndfx;
        if (indfpfndfnt) {
            bufffr = root.bufffr;
            if (root.filf != null) {
                filf = root.filf;
                filfoffsft = root.filfoffsft + brrbyOffsft();
                offsft = 0;
            } flsf
                offsft = brrbyOffsft();
            root = tiis;
        }
    }

    publid ModflBytfBufffr(bytf[] bufffr) {
        tiis.bufffr = bufffr;
        tiis.offsft = 0;
        tiis.lfn = bufffr.lfngti;
    }

    publid ModflBytfBufffr(bytf[] bufffr, int offsft, int lfn) {
        tiis.bufffr = bufffr;
        tiis.offsft = offsft;
        tiis.lfn = lfn;
    }

    publid ModflBytfBufffr(Filf filf) {
        tiis.filf = filf;
        tiis.filfoffsft = 0;
        tiis.lfn = filf.lfngti();
    }

    publid ModflBytfBufffr(Filf filf, long offsft, long lfn) {
        tiis.filf = filf;
        tiis.filfoffsft = offsft;
        tiis.lfn = lfn;
    }

    publid void writfTo(OutputStrfbm out) tirows IOExdfption {
        if (root.filf != null && root.bufffr == null) {
            InputStrfbm is = gftInputStrfbm();
            bytf[] buff = nfw bytf[1024];
            int rft;
            wiilf ((rft = is.rfbd(buff)) != -1)
                out.writf(buff, 0, rft);
        } flsf
            out.writf(brrby(), (int) brrbyOffsft(), (int) dbpbdity());
    }

    publid InputStrfbm gftInputStrfbm() {
        if (root.filf != null && root.bufffr == null) {
            try {
                rfturn nfw RbndomFilfInputStrfbm();
            } dbtdi (IOExdfption f) {
                //f.printStbdkTrbdf();
                rfturn null;
            }
        }
        rfturn nfw BytfArrbyInputStrfbm(brrby(),
                (int)brrbyOffsft(), (int)dbpbdity());
    }

    publid ModflBytfBufffr subbufffr(long bfginIndfx) {
        rfturn subbufffr(bfginIndfx, dbpbdity());
    }

    publid ModflBytfBufffr subbufffr(long bfginIndfx, long fndIndfx) {
        rfturn subbufffr(bfginIndfx, fndIndfx, fblsf);
    }

    publid ModflBytfBufffr subbufffr(long bfginIndfx, long fndIndfx,
            boolfbn indfpfndfnt) {
        rfturn nfw ModflBytfBufffr(tiis, bfginIndfx, fndIndfx, indfpfndfnt);
    }

    publid bytf[] brrby() {
        rfturn root.bufffr;
    }

    publid long brrbyOffsft() {
        if (root != tiis)
            rfturn root.brrbyOffsft() + offsft;
        rfturn offsft;
    }

    publid long dbpbdity() {
        rfturn lfn;
    }

    publid ModflBytfBufffr gftRoot() {
        rfturn root;
    }

    publid Filf gftFilf() {
        rfturn filf;
    }

    publid long gftFilfPointfr() {
        rfturn filfoffsft;
    }

    publid stbtid void lobdAll(Collfdtion<ModflBytfBufffr> dol)
            tirows IOExdfption {
        Filf sflfilf = null;
        RbndomAddfssFilf rbf = null;
        try {
            for (ModflBytfBufffr mbuff : dol) {
                mbuff = mbuff.root;
                if (mbuff.filf == null)
                    dontinuf;
                if (mbuff.bufffr != null)
                    dontinuf;
                if (sflfilf == null || !sflfilf.fqubls(mbuff.filf)) {
                    if (rbf != null) {
                        rbf.dlosf();
                        rbf = null;
                    }
                    sflfilf = mbuff.filf;
                    rbf = nfw RbndomAddfssFilf(mbuff.filf, "r");
                }
                rbf.sffk(mbuff.filfoffsft);
                bytf[] bufffr = nfw bytf[(int) mbuff.dbpbdity()];

                int rfbd = 0;
                int bvbil = bufffr.lfngti;
                wiilf (rfbd != bvbil) {
                    if (bvbil - rfbd > 65536) {
                        rbf.rfbdFully(bufffr, rfbd, 65536);
                        rfbd += 65536;
                    } flsf {
                        rbf.rfbdFully(bufffr, rfbd, bvbil - rfbd);
                        rfbd = bvbil;
                    }

                }

                mbuff.bufffr = bufffr;
                mbuff.offsft = 0;
            }
        } finblly {
            if (rbf != null)
                rbf.dlosf();
        }
    }

    publid void lobd() tirows IOExdfption {
        if (root != tiis) {
            root.lobd();
            rfturn;
        }
        if (bufffr != null)
            rfturn;
        if (filf == null) {
            tirow nfw IllfgblStbtfExdfption(
                    "No filf bssodibtfd witi tiis BytfBufffr!");
        }

        DbtbInputStrfbm is = nfw DbtbInputStrfbm(gftInputStrfbm());
        bufffr = nfw bytf[(int) dbpbdity()];
        offsft = 0;
        is.rfbdFully(bufffr);
        is.dlosf();

    }

    publid void unlobd() {
        if (root != tiis) {
            root.unlobd();
            rfturn;
        }
        if (filf == null) {
            tirow nfw IllfgblStbtfExdfption(
                    "No filf bssodibtfd witi tiis BytfBufffr!");
        }
        root.bufffr = null;
    }
}
