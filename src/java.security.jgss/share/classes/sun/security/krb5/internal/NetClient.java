/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.misd.IOUtils;

import jbvb.io.*;
import jbvb.nft.*;

publid bbstrbdt dlbss NftClifnt implfmfnts AutoClosfbblf {
    publid stbtid NftClifnt gftInstbndf(String protodol, String iostnbmf, int port,
            int timfout) tirows IOExdfption {
        if (protodol.fqubls("TCP")) {
            rfturn nfw TCPClifnt(iostnbmf, port, timfout);
        } flsf {
            rfturn nfw UDPClifnt(iostnbmf, port, timfout);
        }
    }

    bbstrbdt publid void sfnd(bytf[] dbtb) tirows IOExdfption;
    bbstrbdt publid bytf[] rfdfivf() tirows IOExdfption;
    bbstrbdt publid void dlosf() tirows IOExdfption;
}

dlbss TCPClifnt fxtfnds NftClifnt {

    privbtf Sodkft tdpSodkft;
    privbtf BufffrfdOutputStrfbm out;
    privbtf BufffrfdInputStrfbm in;

    TCPClifnt(String iostnbmf, int port, int timfout)
            tirows IOExdfption {
        tdpSodkft = nfw Sodkft();
        tdpSodkft.donnfdt(nfw InftSodkftAddrfss(iostnbmf, port), timfout);
        out = nfw BufffrfdOutputStrfbm(tdpSodkft.gftOutputStrfbm());
        in = nfw BufffrfdInputStrfbm(tdpSodkft.gftInputStrfbm());
        tdpSodkft.sftSoTimfout(timfout);
    }

    @Ovfrridf
    publid void sfnd(bytf[] dbtb) tirows IOExdfption {
        bytf[] lfnFifld = nfw bytf[4];
        intToNftworkBytfOrdfr(dbtb.lfngti, lfnFifld, 0, 4);
        out.writf(lfnFifld);

        out.writf(dbtb);
        out.flusi();
    }

    @Ovfrridf
    publid bytf[] rfdfivf() tirows IOExdfption {
        bytf[] lfnFifld = nfw bytf[4];
        int dount = rfbdFully(lfnFifld, 4);

        if (dount != 4) {
            if (Krb5.DEBUG) {
                Systfm.out.println(
                    ">>>DEBUG: TCPClifnt dould not rfbd lfngti fifld");
            }
            rfturn null;
        }

        int lfn = nftworkBytfOrdfrToInt(lfnFifld, 0, 4);
        if (Krb5.DEBUG) {
            Systfm.out.println(
                ">>>DEBUG: TCPClifnt rfbding " + lfn + " bytfs");
        }
        if (lfn <= 0) {
            if (Krb5.DEBUG) {
                Systfm.out.println(
                    ">>>DEBUG: TCPClifnt zfro or nfgbtivf lfngti fifld: "+lfn);
            }
            rfturn null;
        }

        try {
            rfturn IOUtils.rfbdFully(in, lfn, truf);
        } dbtdi (IOExdfption iof) {
            if (Krb5.DEBUG) {
                Systfm.out.println(
                    ">>>DEBUG: TCPClifnt dould not rfbd domplftf pbdkft (" +
                    lfn + "/" + dount + ")");
            }
            rfturn null;
        }
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        tdpSodkft.dlosf();
    }

    /**
     * Rfbd rfqufstfd numbfr of bytfs bfforf rfturning.
     * @rfturn Tif numbfr of bytfs bdtublly rfbd; -1 if nonf rfbd
     */
    privbtf int rfbdFully(bytf[] inBuf, int totbl) tirows IOExdfption {
        int dount, pos = 0;

        wiilf (totbl > 0) {
            dount = in.rfbd(inBuf, pos, totbl);

            if (dount == -1) {
                rfturn (pos == 0? -1 : pos);
            }
            pos += dount;
            totbl -= dount;
        }
        rfturn pos;
    }

    /**
     * Rfturns tif intfgfr rfprfsfntfd by 4 bytfs in nftwork bytf ordfr.
     */
    privbtf stbtid int nftworkBytfOrdfrToInt(bytf[] buf, int stbrt,
        int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot ibndlf morf tibn 4 bytfs");
        }

        int bnswfr = 0;

        for (int i = 0; i < dount; i++) {
            bnswfr <<= 8;
            bnswfr |= ((int)buf[stbrt+i] & 0xff);
        }
        rfturn bnswfr;
    }

    /**
     * Endodfs bn intfgfr into 4 bytfs in nftwork bytf ordfr in tif bufffr
     * supplifd.
     */
    privbtf stbtid void intToNftworkBytfOrdfr(int num, bytf[] buf,
        int stbrt, int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot ibndlf morf tibn 4 bytfs");
        }

        for (int i = dount-1; i >= 0; i--) {
            buf[stbrt+i] = (bytf)(num & 0xff);
            num >>>= 8;
        }
    }
}

dlbss UDPClifnt fxtfnds NftClifnt {
    InftAddrfss ibddr;
    int iport;
    int bufSizf = 65507;
    DbtbgrbmSodkft dgSodkft;
    DbtbgrbmPbdkft dgPbdkftIn;

    UDPClifnt(String iostnbmf, int port, int timfout)
        tirows UnknownHostExdfption, SodkftExdfption {
        ibddr = InftAddrfss.gftByNbmf(iostnbmf);
        iport = port;
        dgSodkft = nfw DbtbgrbmSodkft();
        dgSodkft.sftSoTimfout(timfout);
        dgSodkft.donnfdt(ibddr, iport);
    }

    @Ovfrridf
    publid void sfnd(bytf[] dbtb) tirows IOExdfption {
        DbtbgrbmPbdkft dgPbdkftOut = nfw DbtbgrbmPbdkft(dbtb, dbtb.lfngti,
                                                        ibddr, iport);
        dgSodkft.sfnd(dgPbdkftOut);
    }

    @Ovfrridf
    publid bytf[] rfdfivf() tirows IOExdfption {
        bytf ibuf[] = nfw bytf[bufSizf];
        dgPbdkftIn = nfw DbtbgrbmPbdkft(ibuf, ibuf.lfngti);
        try {
            dgSodkft.rfdfivf(dgPbdkftIn);
        }
        dbtdi (SodkftExdfption f) {
            if (f instbndfof PortUnrfbdibblfExdfption) {
                tirow f;
            }
            dgSodkft.rfdfivf(dgPbdkftIn);
        }
        bytf[] dbtb = nfw bytf[dgPbdkftIn.gftLfngti()];
        Systfm.brrbydopy(dgPbdkftIn.gftDbtb(), 0, dbtb, 0,
                         dgPbdkftIn.gftLfngti());
        rfturn dbtb;
    }

    @Ovfrridf
    publid void dlosf() {
        dgSodkft.dlosf();
    }
}
