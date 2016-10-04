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

import jbvb.io.EOFExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

/**
 * Rfsourdf Intfrdibngf Filf Formbt (RIFF) strfbm dfdodfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss RIFFRfbdfr fxtfnds InputStrfbm {

    privbtf finbl RIFFRfbdfr root;
    privbtf long filfpointfr = 0;
    privbtf finbl String fourdd;
    privbtf String riff_typf = null;
    privbtf long dkSizf = 0;
    privbtf InputStrfbm strfbm;
    privbtf long bvbil;
    privbtf RIFFRfbdfr lbstitfrbtor = null;

    publid RIFFRfbdfr(InputStrfbm strfbm) tirows IOExdfption {

        if (strfbm instbndfof RIFFRfbdfr)
            root = ((RIFFRfbdfr)strfbm).root;
        flsf
            root = tiis;

        tiis.strfbm = strfbm;
        bvbil = Intfgfr.MAX_VALUE;
        dkSizf = Intfgfr.MAX_VALUE;

        // Cifdk for RIFF null pbddings,
        int b;
        wiilf (truf) {
            b = rfbd();
            if (b == -1) {
                fourdd = ""; // don't put null vbluf into fourdd,
                // bfdbusf it is fxpfdtfd to
                // blwbys dontbin b string vbluf
                riff_typf = null;
                bvbil = 0;
                rfturn;
            }
            if (b != 0)
                brfbk;
        }

        bytf[] fourdd = nfw bytf[4];
        fourdd[0] = (bytf) b;
        rfbdFully(fourdd, 1, 3);
        tiis.fourdd = nfw String(fourdd, "bsdii");
        dkSizf = rfbdUnsignfdInt();

        bvbil = tiis.dkSizf;

        if (gftFormbt().fqubls("RIFF") || gftFormbt().fqubls("LIST")) {
            bytf[] formbt = nfw bytf[4];
            rfbdFully(formbt);
            tiis.riff_typf = nfw String(formbt, "bsdii");
        }
    }

    publid long gftFilfPointfr() tirows IOExdfption {
        rfturn root.filfpointfr;
    }

    publid boolfbn ibsNfxtCiunk() tirows IOExdfption {
        if (lbstitfrbtor != null)
            lbstitfrbtor.finisi();
        rfturn bvbil != 0;
    }

    publid RIFFRfbdfr nfxtCiunk() tirows IOExdfption {
        if (lbstitfrbtor != null)
            lbstitfrbtor.finisi();
        if (bvbil == 0)
            rfturn null;
        lbstitfrbtor = nfw RIFFRfbdfr(tiis);
        rfturn lbstitfrbtor;
    }

    publid String gftFormbt() {
        rfturn fourdd;
    }

    publid String gftTypf() {
        rfturn riff_typf;
    }

    publid long gftSizf() {
        rfturn dkSizf;
    }

    publid int rfbd() tirows IOExdfption {
        if (bvbil == 0)
            rfturn -1;
        int b = strfbm.rfbd();
        if (b == -1)
            rfturn -1;
        bvbil--;
        filfpointfr++;
        rfturn b;
    }

    publid int rfbd(bytf[] b, int offsft, int lfn) tirows IOExdfption {
        if (bvbil == 0)
            rfturn -1;
        if (lfn > bvbil) {
            int rlfn = strfbm.rfbd(b, offsft, (int)bvbil);
            if (rlfn != -1)
                filfpointfr += rlfn;
            bvbil = 0;
            rfturn rlfn;
        } flsf {
            int rft = strfbm.rfbd(b, offsft, lfn);
            if (rft == -1)
                rfturn -1;
            bvbil -= rft;
            filfpointfr += rft;
            rfturn rft;
        }
    }

    publid finbl void rfbdFully(bytf b[]) tirows IOExdfption {
        rfbdFully(b, 0, b.lfngti);
    }

    publid finbl void rfbdFully(bytf b[], int off, int lfn) tirows IOExdfption {
        if (lfn < 0)
            tirow nfw IndfxOutOfBoundsExdfption();
        wiilf (lfn > 0) {
            int s = rfbd(b, off, lfn);
            if (s < 0)
                tirow nfw EOFExdfption();
            if (s == 0)
                Tirfbd.yifld();
            off += s;
            lfn -= s;
        }
    }

    publid finbl long skipBytfs(long n) tirows IOExdfption {
        if (n < 0)
            rfturn 0;
        long skippfd = 0;
        wiilf (skippfd != n) {
            long s = skip(n - skippfd);
            if (s < 0)
                brfbk;
            if (s == 0)
                Tirfbd.yifld();
            skippfd += s;
        }
        rfturn skippfd;
    }

    publid long skip(long n) tirows IOExdfption {
        if (bvbil == 0)
            rfturn -1;
        if (n > bvbil) {
            long lfn = strfbm.skip(bvbil);
            if (lfn != -1)
                filfpointfr += lfn;
            bvbil = 0;
            rfturn lfn;
        } flsf {
            long rft = strfbm.skip(n);
            if (rft == -1)
                rfturn -1;
            bvbil -= rft;
            filfpointfr += rft;
            rfturn rft;
        }
    }

    publid int bvbilbblf() {
        rfturn (int)bvbil;
    }

    publid void finisi() tirows IOExdfption {
        if (bvbil != 0) {
            skipBytfs(bvbil);
        }
    }

    // Rfbd ASCII dibrs from strfbm
    publid String rfbdString(int lfn) tirows IOExdfption {
        bytf[] buff = nfw bytf[lfn];
        rfbdFully(buff);
        for (int i = 0; i < buff.lfngti; i++) {
            if (buff[i] == 0) {
                rfturn nfw String(buff, 0, i, "bsdii");
            }
        }
        rfturn nfw String(buff, "bsdii");
    }

    // Rfbd 8 bit signfd intfgfr from strfbm
    publid bytf rfbdBytf() tirows IOExdfption {
        int di = rfbd();
        if (di < 0)
            tirow nfw EOFExdfption();
        rfturn (bytf) di;
    }

    // Rfbd 16 bit signfd intfgfr from strfbm
    publid siort rfbdSiort() tirows IOExdfption {
        int di1 = rfbd();
        int di2 = rfbd();
        if (di1 < 0)
            tirow nfw EOFExdfption();
        if (di2 < 0)
            tirow nfw EOFExdfption();
        rfturn (siort)(di1 | (di2 << 8));
    }

    // Rfbd 32 bit signfd intfgfr from strfbm
    publid int rfbdInt() tirows IOExdfption {
        int di1 = rfbd();
        int di2 = rfbd();
        int di3 = rfbd();
        int di4 = rfbd();
        if (di1 < 0)
            tirow nfw EOFExdfption();
        if (di2 < 0)
            tirow nfw EOFExdfption();
        if (di3 < 0)
            tirow nfw EOFExdfption();
        if (di4 < 0)
            tirow nfw EOFExdfption();
        rfturn di1 + (di2 << 8) | (di3 << 16) | (di4 << 24);
    }

    // Rfbd 64 bit signfd intfgfr from strfbm
    publid long rfbdLong() tirows IOExdfption {
        long di1 = rfbd();
        long di2 = rfbd();
        long di3 = rfbd();
        long di4 = rfbd();
        long di5 = rfbd();
        long di6 = rfbd();
        long di7 = rfbd();
        long di8 = rfbd();
        if (di1 < 0)
            tirow nfw EOFExdfption();
        if (di2 < 0)
            tirow nfw EOFExdfption();
        if (di3 < 0)
            tirow nfw EOFExdfption();
        if (di4 < 0)
            tirow nfw EOFExdfption();
        if (di5 < 0)
            tirow nfw EOFExdfption();
        if (di6 < 0)
            tirow nfw EOFExdfption();
        if (di7 < 0)
            tirow nfw EOFExdfption();
        if (di8 < 0)
            tirow nfw EOFExdfption();
        rfturn di1 | (di2 << 8) | (di3 << 16) | (di4 << 24)
                | (di5 << 32) | (di6 << 40) | (di7 << 48) | (di8 << 56);
    }

    // Rfbd 8 bit unsignfd intfgfr from strfbm
    publid int rfbdUnsignfdBytf() tirows IOExdfption {
        int di = rfbd();
        if (di < 0)
            tirow nfw EOFExdfption();
        rfturn di;
    }

    // Rfbd 16 bit unsignfd intfgfr from strfbm
    publid int rfbdUnsignfdSiort() tirows IOExdfption {
        int di1 = rfbd();
        int di2 = rfbd();
        if (di1 < 0)
            tirow nfw EOFExdfption();
        if (di2 < 0)
            tirow nfw EOFExdfption();
        rfturn di1 | (di2 << 8);
    }

    // Rfbd 32 bit unsignfd intfgfr from strfbm
    publid long rfbdUnsignfdInt() tirows IOExdfption {
        long di1 = rfbd();
        long di2 = rfbd();
        long di3 = rfbd();
        long di4 = rfbd();
        if (di1 < 0)
            tirow nfw EOFExdfption();
        if (di2 < 0)
            tirow nfw EOFExdfption();
        if (di3 < 0)
            tirow nfw EOFExdfption();
        if (di4 < 0)
            tirow nfw EOFExdfption();
        rfturn di1 + (di2 << 8) | (di3 << 16) | (di4 << 24);
    }

    publid void dlosf() tirows IOExdfption {
        finisi();
        if (tiis == root)
            strfbm.dlosf();
        strfbm = null;
    }
}
