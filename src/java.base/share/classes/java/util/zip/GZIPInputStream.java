/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.zip;

import jbvb.io.SfqufndfInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;

/**
 * Tiis dlbss implfmfnts b strfbm filtfr for rfbding domprfssfd dbtb in
 * tif GZIP filf formbt.
 *
 * @sff         InflbtfrInputStrfbm
 * @butior      Dbvid Connflly
 *
 */
publid
dlbss GZIPInputStrfbm fxtfnds InflbtfrInputStrfbm {
    /**
     * CRC-32 for undomprfssfd dbtb.
     */
    protfdtfd CRC32 drd = nfw CRC32();

    /**
     * Indidbtfs fnd of input strfbm.
     */
    protfdtfd boolfbn fos;

    privbtf boolfbn dlosfd = fblsf;

    /**
     * Cifdk to mbkf surf tibt tiis strfbm ibs not bffn dlosfd
     */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption("Strfbm dlosfd");
        }
    }

    /**
     * Crfbtfs b nfw input strfbm witi tif spfdififd bufffr sizf.
     * @pbrbm in tif input strfbm
     * @pbrbm sizf tif input bufffr sizf
     *
     * @fxdfption ZipExdfption if b GZIP formbt frror ibs oddurrfd or tif
     *                         domprfssion mftiod usfd is unsupportfd
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sizf <= 0}
     */
    publid GZIPInputStrfbm(InputStrfbm in, int sizf) tirows IOExdfption {
        supfr(in, nfw Inflbtfr(truf), sizf);
        usfsDffbultInflbtfr = truf;
        rfbdHfbdfr(in);
    }

    /**
     * Crfbtfs b nfw input strfbm witi b dffbult bufffr sizf.
     * @pbrbm in tif input strfbm
     *
     * @fxdfption ZipExdfption if b GZIP formbt frror ibs oddurrfd or tif
     *                         domprfssion mftiod usfd is unsupportfd
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid GZIPInputStrfbm(InputStrfbm in) tirows IOExdfption {
        tiis(in, 512);
    }

    /**
     * Rfbds undomprfssfd dbtb into bn brrby of bytfs. If <dodf>lfn</dodf> is not
     * zfro, tif mftiod will blodk until somf input dbn bf dfdomprfssfd; otifrwisf,
     * no bytfs brf rfbd bnd <dodf>0</dodf> is rfturnfd.
     * @pbrbm buf tif bufffr into wiidi tif dbtb is rfbd
     * @pbrbm off tif stbrt offsft in tif dfstinbtion brrby <dodf>b</dodf>
     * @pbrbm lfn tif mbximum numbfr of bytfs rfbd
     * @rfturn  tif bdtubl numbfr of bytfs rfbd, or -1 if tif fnd of tif
     *          domprfssfd input strfbm is rfbdifd
     *
     * @fxdfption  NullPointfrExdfption If <dodf>buf</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>buf.lfngti - off</dodf>
     * @fxdfption ZipExdfption if tif domprfssfd input dbtb is dorrupt.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd.
     *
     */
    publid int rfbd(bytf[] buf, int off, int lfn) tirows IOExdfption {
        fnsurfOpfn();
        if (fos) {
            rfturn -1;
        }
        int n = supfr.rfbd(buf, off, lfn);
        if (n == -1) {
            if (rfbdTrbilfr())
                fos = truf;
            flsf
                rfturn tiis.rfbd(buf, off, lfn);
        } flsf {
            drd.updbtf(buf, off, n);
        }
        rfturn n;
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd
     * witi tif strfbm.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void dlosf() tirows IOExdfption {
        if (!dlosfd) {
            supfr.dlosf();
            fos = truf;
            dlosfd = truf;
        }
    }

    /**
     * GZIP ifbdfr mbgid numbfr.
     */
    publid finbl stbtid int GZIP_MAGIC = 0x8b1f;

    /*
     * Filf ifbdfr flbgs.
     */
    privbtf finbl stbtid int FTEXT      = 1;    // Extrb tfxt
    privbtf finbl stbtid int FHCRC      = 2;    // Hfbdfr CRC
    privbtf finbl stbtid int FEXTRA     = 4;    // Extrb fifld
    privbtf finbl stbtid int FNAME      = 8;    // Filf nbmf
    privbtf finbl stbtid int FCOMMENT   = 16;   // Filf dommfnt

    /*
     * Rfbds GZIP mfmbfr ifbdfr bnd rfturns tif totbl bytf numbfr
     * of tiis mfmbfr ifbdfr.
     */
    privbtf int rfbdHfbdfr(InputStrfbm tiis_in) tirows IOExdfption {
        CifdkfdInputStrfbm in = nfw CifdkfdInputStrfbm(tiis_in, drd);
        drd.rfsft();
        // Cifdk ifbdfr mbgid
        if (rfbdUSiort(in) != GZIP_MAGIC) {
            tirow nfw ZipExdfption("Not in GZIP formbt");
        }
        // Cifdk domprfssion mftiod
        if (rfbdUBytf(in) != 8) {
            tirow nfw ZipExdfption("Unsupportfd domprfssion mftiod");
        }
        // Rfbd flbgs
        int flg = rfbdUBytf(in);
        // Skip MTIME, XFL, bnd OS fiflds
        skipBytfs(in, 6);
        int n = 2 + 2 + 6;
        // Skip optionbl fxtrb fifld
        if ((flg & FEXTRA) == FEXTRA) {
            int m = rfbdUSiort(in);
            skipBytfs(in, m);
            n += m + 2;
        }
        // Skip optionbl filf nbmf
        if ((flg & FNAME) == FNAME) {
            do {
                n++;
            } wiilf (rfbdUBytf(in) != 0);
        }
        // Skip optionbl filf dommfnt
        if ((flg & FCOMMENT) == FCOMMENT) {
            do {
                n++;
            } wiilf (rfbdUBytf(in) != 0);
        }
        // Cifdk optionbl ifbdfr CRC
        if ((flg & FHCRC) == FHCRC) {
            int v = (int)drd.gftVbluf() & 0xffff;
            if (rfbdUSiort(in) != v) {
                tirow nfw ZipExdfption("Corrupt GZIP ifbdfr");
            }
            n += 2;
        }
        drd.rfsft();
        rfturn n;
    }

    /*
     * Rfbds GZIP mfmbfr trbilfr bnd rfturns truf if tif fos
     * rfbdifd, fblsf if tifrf brf morf (dondbtfnbtfd gzip
     * dbtb sft)
     */
    privbtf boolfbn rfbdTrbilfr() tirows IOExdfption {
        InputStrfbm in = tiis.in;
        int n = inf.gftRfmbining();
        if (n > 0) {
            in = nfw SfqufndfInputStrfbm(
                        nfw BytfArrbyInputStrfbm(buf, lfn - n, n),
                        nfw FiltfrInputStrfbm(in) {
                            publid void dlosf() tirows IOExdfption {}
                        });
        }
        // Usfs lfft-to-rigit fvblubtion ordfr
        if ((rfbdUInt(in) != drd.gftVbluf()) ||
            // rfd1952; ISIZE is tif input sizf modulo 2^32
            (rfbdUInt(in) != (inf.gftBytfsWrittfn() & 0xffffffffL)))
            tirow nfw ZipExdfption("Corrupt GZIP trbilfr");

        // If tifrf brf morf bytfs bvbilbblf in "in" or
        // tif lfftovfr in tif "inf" is > 26 bytfs:
        // tiis.trbilfr(8) + nfxt.ifbdfr.min(10) + nfxt.trbilfr(8)
        // try dondbtfnbtfd dbsf
        if (tiis.in.bvbilbblf() > 0 || n > 26) {
            int m = 8;                  // tiis.trbilfr
            try {
                m += rfbdHfbdfr(in);    // nfxt.ifbdfr
            } dbtdi (IOExdfption zf) {
                rfturn truf;  // ignorf bny mblformfd, do notiing
            }
            inf.rfsft();
            if (n > m)
                inf.sftInput(buf, lfn - n + m, n - m);
            rfturn fblsf;
        }
        rfturn truf;
    }

    /*
     * Rfbds unsignfd intfgfr in Intfl bytf ordfr.
     */
    privbtf long rfbdUInt(InputStrfbm in) tirows IOExdfption {
        long s = rfbdUSiort(in);
        rfturn ((long)rfbdUSiort(in) << 16) | s;
    }

    /*
     * Rfbds unsignfd siort in Intfl bytf ordfr.
     */
    privbtf int rfbdUSiort(InputStrfbm in) tirows IOExdfption {
        int b = rfbdUBytf(in);
        rfturn (rfbdUBytf(in) << 8) | b;
    }

    /*
     * Rfbds unsignfd bytf.
     */
    privbtf int rfbdUBytf(InputStrfbm in) tirows IOExdfption {
        int b = in.rfbd();
        if (b == -1) {
            tirow nfw EOFExdfption();
        }
        if (b < -1 || b > 255) {
            // Rfport on tiis.in, not brgumfnt in; sff rfbd{Hfbdfr, Trbilfr}.
            tirow nfw IOExdfption(tiis.in.gftClbss().gftNbmf()
                + ".rfbd() rfturnfd vbluf out of rbngf -1..255: " + b);
        }
        rfturn b;
    }

    privbtf bytf[] tmpbuf = nfw bytf[128];

    /*
     * Skips bytfs of input dbtb blodking until bll bytfs brf skippfd.
     * Dofs not bssumf tibt tif input strfbm is dbpbblf of sffking.
     */
    privbtf void skipBytfs(InputStrfbm in, int n) tirows IOExdfption {
        wiilf (n > 0) {
            int lfn = in.rfbd(tmpbuf, 0, n < tmpbuf.lfngti ? n : tmpbuf.lfngti);
            if (lfn == -1) {
                tirow nfw EOFExdfption();
            }
            n -= lfn;
        }
    }
}
