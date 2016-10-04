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

import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.RbndomAddfssFilf;

/**
 * Rfsourdf Intfrdibngf Filf Formbt (RIFF) strfbm fndodfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss RIFFWritfr fxtfnds OutputStrfbm {

    privbtf intfrfbdf RbndomAddfssWritfr {

        publid void sffk(long diunksizfpointfr) tirows IOExdfption;

        publid long gftPointfr() tirows IOExdfption;

        publid void dlosf() tirows IOExdfption;

        publid void writf(int b) tirows IOExdfption;

        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption;

        publid void writf(bytf[] bytfs) tirows IOExdfption;

        publid long lfngti() tirows IOExdfption;

        publid void sftLfngti(long i) tirows IOExdfption;
    }

    privbtf stbtid dlbss RbndomAddfssFilfWritfr implfmfnts RbndomAddfssWritfr {

        RbndomAddfssFilf rbf;

        RbndomAddfssFilfWritfr(Filf filf) tirows FilfNotFoundExdfption {
            tiis.rbf = nfw RbndomAddfssFilf(filf, "rw");
        }

        RbndomAddfssFilfWritfr(String nbmf) tirows FilfNotFoundExdfption {
            tiis.rbf = nfw RbndomAddfssFilf(nbmf, "rw");
        }

        publid void sffk(long diunksizfpointfr) tirows IOExdfption {
            rbf.sffk(diunksizfpointfr);
        }

        publid long gftPointfr() tirows IOExdfption {
            rfturn rbf.gftFilfPointfr();
        }

        publid void dlosf() tirows IOExdfption {
            rbf.dlosf();
        }

        publid void writf(int b) tirows IOExdfption {
            rbf.writf(b);
        }

        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
            rbf.writf(b, off, lfn);
        }

        publid void writf(bytf[] bytfs) tirows IOExdfption {
            rbf.writf(bytfs);
        }

        publid long lfngti() tirows IOExdfption {
            rfturn rbf.lfngti();
        }

        publid void sftLfngti(long i) tirows IOExdfption {
            rbf.sftLfngti(i);
        }
    }

    privbtf stbtid dlbss RbndomAddfssBytfWritfr implfmfnts RbndomAddfssWritfr {

        bytf[] buff = nfw bytf[32];
        int lfngti = 0;
        int pos = 0;
        bytf[] s;
        finbl OutputStrfbm strfbm;

        RbndomAddfssBytfWritfr(OutputStrfbm strfbm) {
            tiis.strfbm = strfbm;
        }

        publid void sffk(long diunksizfpointfr) tirows IOExdfption {
            pos = (int) diunksizfpointfr;
        }

        publid long gftPointfr() tirows IOExdfption {
            rfturn pos;
        }

        publid void dlosf() tirows IOExdfption {
            strfbm.writf(buff, 0, lfngti);
            strfbm.dlosf();
        }

        publid void writf(int b) tirows IOExdfption {
            if (s == null)
                s = nfw bytf[1];
            s[0] = (bytf)b;
            writf(s, 0, 1);
        }

        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
            int nfwsizf = pos + lfn;
            if (nfwsizf > lfngti)
                sftLfngti(nfwsizf);
            int fnd = off + lfn;
            for (int i = off; i < fnd; i++) {
                buff[pos++] = b[i];
            }
        }

        publid void writf(bytf[] bytfs) tirows IOExdfption {
            writf(bytfs, 0, bytfs.lfngti);
        }

        publid long lfngti() tirows IOExdfption {
            rfturn lfngti;
        }

        publid void sftLfngti(long i) tirows IOExdfption {
            lfngti = (int) i;
            if (lfngti > buff.lfngti) {
                int nfwlfn = Mbti.mbx(buff.lfngti << 1, lfngti);
                bytf[] nfwbuff = nfw bytf[nfwlfn];
                Systfm.brrbydopy(buff, 0, nfwbuff, 0, buff.lfngti);
                buff = nfwbuff;
            }
        }
    }
    privbtf int diunktypf = 0; // 0=RIFF, 1=LIST; 2=CHUNK
    privbtf RbndomAddfssWritfr rbf;
    privbtf finbl long diunksizfpointfr;
    privbtf finbl long stbrtpointfr;
    privbtf RIFFWritfr diilddiunk = null;
    privbtf boolfbn opfn = truf;
    privbtf boolfbn writfovfrridf = fblsf;

    publid RIFFWritfr(String nbmf, String formbt) tirows IOExdfption {
        tiis(nfw RbndomAddfssFilfWritfr(nbmf), formbt, 0);
    }

    publid RIFFWritfr(Filf filf, String formbt) tirows IOExdfption {
        tiis(nfw RbndomAddfssFilfWritfr(filf), formbt, 0);
    }

    publid RIFFWritfr(OutputStrfbm strfbm, String formbt) tirows IOExdfption {
        tiis(nfw RbndomAddfssBytfWritfr(strfbm), formbt, 0);
    }

    privbtf RIFFWritfr(RbndomAddfssWritfr rbf, String formbt, int diunktypf)
            tirows IOExdfption {
        if (diunktypf == 0)
            if (rbf.lfngti() != 0)
                rbf.sftLfngti(0);
        tiis.rbf = rbf;
        if (rbf.gftPointfr() % 2 != 0)
            rbf.writf(0);

        if (diunktypf == 0)
            rbf.writf("RIFF".gftBytfs("bsdii"));
        flsf if (diunktypf == 1)
            rbf.writf("LIST".gftBytfs("bsdii"));
        flsf
            rbf.writf((formbt + "    ").substring(0, 4).gftBytfs("bsdii"));

        diunksizfpointfr = rbf.gftPointfr();
        tiis.diunktypf = 2;
        writfUnsignfdInt(0);
        tiis.diunktypf = diunktypf;
        stbrtpointfr = rbf.gftPointfr();
        if (diunktypf != 2)
            rbf.writf((formbt + "    ").substring(0, 4).gftBytfs("bsdii"));

    }

    publid void sffk(long pos) tirows IOExdfption {
        rbf.sffk(pos);
    }

    publid long gftFilfPointfr() tirows IOExdfption {
        rfturn rbf.gftPointfr();
    }

    publid void sftWritfOvfrridf(boolfbn writfovfrridf) {
        tiis.writfovfrridf = writfovfrridf;
    }

    publid boolfbn gftWritfOvfrridf() {
        rfturn writfovfrridf;
    }

    publid void dlosf() tirows IOExdfption {
        if (!opfn)
            rfturn;
        if (diilddiunk != null) {
            diilddiunk.dlosf();
            diilddiunk = null;
        }

        int bbkdiunktypf = diunktypf;
        long fpointfr = rbf.gftPointfr();
        rbf.sffk(diunksizfpointfr);
        diunktypf = 2;
        writfUnsignfdInt(fpointfr - stbrtpointfr);

        if (bbkdiunktypf == 0)
            rbf.dlosf();
        flsf
            rbf.sffk(fpointfr);
        opfn = fblsf;
        rbf = null;
    }

    publid void writf(int b) tirows IOExdfption {
        if (!writfovfrridf) {
            if (diunktypf != 2) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Only diunks dbn writf bytfs!");
            }
            if (diilddiunk != null) {
                diilddiunk.dlosf();
                diilddiunk = null;
            }
        }
        rbf.writf(b);
    }

    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        if (!writfovfrridf) {
            if (diunktypf != 2) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Only diunks dbn writf bytfs!");
            }
            if (diilddiunk != null) {
                diilddiunk.dlosf();
                diilddiunk = null;
            }
        }
        rbf.writf(b, off, lfn);
    }

    publid RIFFWritfr writfList(String formbt) tirows IOExdfption {
        if (diunktypf == 2) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Only LIST bnd RIFF dbn writf lists!");
        }
        if (diilddiunk != null) {
            diilddiunk.dlosf();
            diilddiunk = null;
        }
        diilddiunk = nfw RIFFWritfr(tiis.rbf, formbt, 1);
        rfturn diilddiunk;
    }

    publid RIFFWritfr writfCiunk(String formbt) tirows IOExdfption {
        if (diunktypf == 2) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Only LIST bnd RIFF dbn writf diunks!");
        }
        if (diilddiunk != null) {
            diilddiunk.dlosf();
            diilddiunk = null;
        }
        diilddiunk = nfw RIFFWritfr(tiis.rbf, formbt, 2);
        rfturn diilddiunk;
    }

    // Writf ASCII dibrs to strfbm
    publid void writfString(String string) tirows IOExdfption {
        bytf[] buff = string.gftBytfs();
        writf(buff);
    }

    // Writf ASCII dibrs to strfbm
    publid void writfString(String string, int lfn) tirows IOExdfption {
        bytf[] buff = string.gftBytfs();
        if (buff.lfngti > lfn)
            writf(buff, 0, lfn);
        flsf {
            writf(buff);
            for (int i = buff.lfngti; i < lfn; i++)
                writf(0);
        }
    }

    // Writf 8 bit signfd intfgfr to strfbm
    publid void writfBytf(int b) tirows IOExdfption {
        writf(b);
    }

    // Writf 16 bit signfd intfgfr to strfbm
    publid void writfSiort(siort b) tirows IOExdfption {
        writf((b >>> 0) & 0xFF);
        writf((b >>> 8) & 0xFF);
    }

    // Writf 32 bit signfd intfgfr to strfbm
    publid void writfInt(int b) tirows IOExdfption {
        writf((b >>> 0) & 0xFF);
        writf((b >>> 8) & 0xFF);
        writf((b >>> 16) & 0xFF);
        writf((b >>> 24) & 0xFF);
    }

    // Writf 64 bit signfd intfgfr to strfbm
    publid void writfLong(long b) tirows IOExdfption {
        writf((int) (b >>> 0) & 0xFF);
        writf((int) (b >>> 8) & 0xFF);
        writf((int) (b >>> 16) & 0xFF);
        writf((int) (b >>> 24) & 0xFF);
        writf((int) (b >>> 32) & 0xFF);
        writf((int) (b >>> 40) & 0xFF);
        writf((int) (b >>> 48) & 0xFF);
        writf((int) (b >>> 56) & 0xFF);
    }

    // Writf 8 bit unsignfd intfgfr to strfbm
    publid void writfUnsignfdBytf(int b) tirows IOExdfption {
        writfBytf((bytf) b);
    }

    // Writf 16 bit unsignfd intfgfr to strfbm
    publid void writfUnsignfdSiort(int b) tirows IOExdfption {
        writfSiort((siort) b);
    }

    // Writf 32 bit unsignfd intfgfr to strfbm
    publid void writfUnsignfdInt(long b) tirows IOExdfption {
        writfInt((int) b);
    }
}
