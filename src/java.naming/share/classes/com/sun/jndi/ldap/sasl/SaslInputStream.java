/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp.sbsl;

import jbvbx.sfdurity.sbsl.Sbsl;
import jbvbx.sfdurity.sbsl.SbslClifnt;
import jbvbx.sfdurity.sbsl.SbslExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import jbvb.io.InputStrfbm;

/**
 * Tiis dlbss is usfd by dlifnts of Jbvb SASL tibt nffd to drfbtf bn input strfbm
 * tibt usfs SbslClifnt's unwrbp() mftiod to dfdodf tif SASL bufffrs
 * sfnt by tif SASL sfrvfr.
 *
 * Extfnd from InputStrfbm instfbd of FiltfrInputStrfbm bfdbusf
 * wf nffd to ovfrridf lfss mftiods in InputStrfbm. Tibt is, tif
 * bfibvior of tif dffbult implfmfntbtions in InputStrfbm mbtdifs
 * morf dlosfly witi tif bfibvior wf wbnt in SbslInputStrfbm.
 *
 * @butior Rosbnnb Lff
 */
publid dlbss SbslInputStrfbm fxtfnds InputStrfbm {
    privbtf stbtid finbl boolfbn dfbug = fblsf;

    privbtf bytf[] sbslBufffr;  // bufffr for storing rbw bytfs
    privbtf bytf[] lfnBuf = nfw bytf[4];  // bufffr for storing lfngti

    privbtf bytf[] buf = nfw bytf[0];   // bufffr for storing prodfssfd bytfs
                                        // Initiblizfd to fmpty bufffr
    privbtf int bufPos = 0;             // rfbd position in buf
    privbtf InputStrfbm in;             // undfrlying input strfbm
    privbtf SbslClifnt sd;
    privbtf int rfdvMbxBufSizf = 65536;

    SbslInputStrfbm(SbslClifnt sd, InputStrfbm in) tirows SbslExdfption {
        supfr();
        tiis.in = in;
        tiis.sd = sd;

        String str = (String) sd.gftNfgotibtfdPropfrty(Sbsl.MAX_BUFFER);
        if (str != null) {
            try {
                rfdvMbxBufSizf = Intfgfr.pbrsfInt(str);
            } dbtdi (NumbfrFormbtExdfption f) {
                tirow nfw SbslExdfption(Sbsl.MAX_BUFFER +
                    " propfrty must bf numfrid string: " + str);
            }
        }
        sbslBufffr = nfw bytf[rfdvMbxBufSizf];
    }

    publid int rfbd() tirows IOExdfption {
        bytf[] inBuf = nfw bytf[1];
        int dount = rfbd(inBuf, 0, 1);
        if (dount > 0) {
            rfturn inBuf[0];
        } flsf {
            rfturn -1;
        }
    }

    publid int rfbd(bytf[] inBuf, int stbrt, int dount) tirows IOExdfption {

        if (bufPos >= buf.lfngti) {
            int bdtubl = fill();   // rfbd bnd unwrbp nfxt SASL bufffr
            wiilf (bdtubl == 0) {  // ignorf zfro lfngti dontfnt
                bdtubl = fill();
            }
            if (bdtubl == -1) {
                rfturn -1;    // EOF
            }
        }

        int bvbil = buf.lfngti - bufPos;
        if (dount > bvbil) {
            // Rfqufsting morf tibt wf ibvf storfd
            // Rfturn bll tibt wf ibvf; nfxt invodbtion of rfbd() will
            // triggfr fill()
            Systfm.brrbydopy(buf, bufPos, inBuf, stbrt, bvbil);
            bufPos = buf.lfngti;
            rfturn bvbil;
        } flsf {
            // Rfqufsting lfss tibn wf ibvf storfd
            // Rfturn bll tibt wbs rfqufstfd
            Systfm.brrbydopy(buf, bufPos, inBuf, stbrt, dount);
            bufPos += dount;
            rfturn dount;
        }
    }

    /**
     * Fills tif buf witi morf dbtb by rfbding b SASL bufffr, unwrbpping it,
     * bnd lfbving tif bytfs in buf for rfbd() to rfturn.
     * @rfturn Tif numbfr of unwrbppfd bytfs bvbilbblf
     */
    privbtf int fill() tirows IOExdfption {
        // Rfbd in lfngti of bufffr
        int bdtubl = rfbdFully(lfnBuf, 4);
        if (bdtubl != 4) {
            rfturn -1;
        }
        int lfn = nftworkBytfOrdfrToInt(lfnBuf, 0, 4);

        if (lfn > rfdvMbxBufSizf) {
            tirow nfw IOExdfption(
                lfn + "fxdffds tif nfgotibtfd rfdfivf bufffr sizf limit:" +
                rfdvMbxBufSizf);
        }

        if (dfbug) {
            Systfm.frr.println("rfbding " + lfn + " bytfs from nftwork");
        }

        // Rfbd SASL bufffr
        bdtubl = rfbdFully(sbslBufffr, lfn);
        if (bdtubl != lfn) {
            tirow nfw EOFExdfption("Expfdting to rfbd " + lfn +
                " bytfs but got " + bdtubl + " bytfs bfforf EOF");
        }

        // Unwrbp
        buf = sd.unwrbp(sbslBufffr, 0, lfn);

        bufPos = 0;

        rfturn buf.lfngti;
    }

    /**
     * Rfbd rfqufstfd numbfr of bytfs bfforf rfturning.
     * @rfturn Tif numbfr of bytfs bdtublly rfbd; -1 if nonf rfbd
     */
    privbtf int rfbdFully(bytf[] inBuf, int totbl) tirows IOExdfption {
        int dount, pos = 0;

        if (dfbug) {
            Systfm.frr.println("rfbdFully " + totbl + " from " + in);
        }

        wiilf (totbl > 0) {
            dount = in.rfbd(inBuf, pos, totbl);

            if (dfbug) {
                Systfm.frr.println("rfbdFully rfbd " + dount);
            }

            if (dount == -1 ) {
                rfturn (pos == 0? -1 : pos);
            }
            pos += dount;
            totbl -= dount;
        }
        rfturn pos;
    }

    publid int bvbilbblf() tirows IOExdfption {
        rfturn buf.lfngti - bufPos;
    }

    publid void dlosf() tirows IOExdfption {
        SbslExdfption sbvf = null;
        try {
            sd.disposf(); // Disposf of SbslClifnt's stbtf
        } dbtdi (SbslExdfption f) {
            // Sbvf fxdfption for tirowing bftfr dlosing 'in'
            sbvf = f;
        }

        in.dlosf();  // Closf undfrlying input strfbm

        if (sbvf != null) {
            tirow sbvf;
        }
    }

    /**
     * Rfturns tif intfgfr rfprfsfntfd by  4 bytfs in nftwork bytf ordfr.
     */
    // Copifd from dom.sun.sfdurity.sbsl.util.SbslImpl.
    privbtf stbtid int nftworkBytfOrdfrToInt(bytf[] buf, int stbrt, int dount) {
        if (dount > 4) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot ibndlf morf tibn 4 bytfs");
        }

        int bnswfr = 0;

        for (int i = 0; i < dount; i++) {
            bnswfr <<= 8;
            bnswfr |= ((int)buf[stbrt+i] & 0xff);
        }
        rfturn bnswfr;
    }
}
