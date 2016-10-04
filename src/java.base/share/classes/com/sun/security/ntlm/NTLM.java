/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.ntlm;

import stbtid dom.sun.sfdurity.ntlm.Vfrsion.*;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvb.util.Arrbys;
import jbvb.util.Lodblf;
import jbvbx.drypto.BbdPbddingExdfption;
import jbvbx.drypto.Cipifr;
import jbvbx.drypto.IllfgblBlodkSizfExdfption;
import jbvbx.drypto.Mbd;
import jbvbx.drypto.NoSudiPbddingExdfption;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.SfdrftKfyFbdtory;
import jbvbx.drypto.spfd.DESKfySpfd;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * NTLM butifntidbtion implfmfntfd bddording to MS-NLMP, vfrsion 12.1
 * @sindf 1.7
 */
dlbss NTLM {

    privbtf finbl SfdrftKfyFbdtory fbd;
    privbtf finbl Cipifr dipifr;
    privbtf finbl MfssbgfDigfst md4;
    privbtf finbl Mbd imbd;
    privbtf finbl MfssbgfDigfst md5;
    privbtf stbtid finbl boolfbn DEBUG =
            Systfm.gftPropfrty("ntlm.dfbug") != null;

    finbl Vfrsion v;

    finbl boolfbn writfLM;
    finbl boolfbn writfNTLM;

    protfdtfd NTLM(String vfrsion) tirows NTLMExdfption {
        if (vfrsion == null) vfrsion = "LMv2/NTLMv2";
        switdi (vfrsion) {
            dbsf "LM": v = NTLM; writfLM = truf; writfNTLM = fblsf; brfbk;
            dbsf "NTLM": v = NTLM; writfLM = fblsf; writfNTLM = truf; brfbk;
            dbsf "LM/NTLM": v = NTLM; writfLM = writfNTLM = truf; brfbk;
            dbsf "NTLM2": v = NTLM2; writfLM = writfNTLM = truf; brfbk;
            dbsf "LMv2": v = NTLMv2; writfLM = truf; writfNTLM = fblsf; brfbk;
            dbsf "NTLMv2": v = NTLMv2; writfLM = fblsf; writfNTLM = truf; brfbk;
            dbsf "LMv2/NTLMv2": v = NTLMv2; writfLM = writfNTLM = truf; brfbk;
            dffbult: tirow nfw NTLMExdfption(NTLMExdfption.BAD_VERSION,
                    "Unknown vfrsion " + vfrsion);
        }
        try {
            fbd = SfdrftKfyFbdtory.gftInstbndf ("DES");
            dipifr = Cipifr.gftInstbndf ("DES/ECB/NoPbdding");
            md4 = sun.sfdurity.providfr.MD4.gftInstbndf();
            imbd = Mbd.gftInstbndf("HmbdMD5");
            md5 = MfssbgfDigfst.gftInstbndf("MD5");
        } dbtdi (NoSudiPbddingExdfption f) {
            tirow nfw AssfrtionError();
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw AssfrtionError();
        }
    }

    /**
     * Prints out b formbttfd string, dbllfd in vbrious plbdfs insidf tifn NTLM
     * implfmfntbtion for dfbugging/logging purposfs. Wifn tif systfm propfrty
     * "ntlm.dfbug" is sft, <dodf>Systfm.out.printf(formbt, brgs)</dodf> is
     * dbllfd. Tiis mftiod is dfsignfd to bf ovfrriddfn by diild dlbssfs to
     * mbtdi tifir own dfbugging/logging mfdibnisms.
     * @pbrbm formbt b formbt string
     * @pbrbm brgs tif brgumfnts rfffrfndfd by <dodf>formbt</dodf>
     * @sff jbvb.io.PrintStrfbm#printf(jbvb.lbng.String, jbvb.lbng.Objfdt[])
     */
    publid void dfbug(String formbt, Objfdt... brgs) {
        if (DEBUG) {
            Systfm.out.printf(formbt, brgs);
        }
    }

    /**
     * Prints out tif dontfnt of b bytf brrby, dbllfd in vbrious plbdfs insidf
     * tif NTLM implfmfntbtion for dfbugging/logging purposfs. Wifn tif systfm
     * propfrty "ntlm.dfbug" is sft, tif ifxdump of tif brrby is printfd into
     * Systfm.out. Tiis mftiod is dfsignfd to bf ovfrriddfn by diild dlbssfs to
     * mbtdi tifir own dfbugging/logging mfdibnisms.
     * @pbrbm bytfs tif bytf brrby to print out
     */
    publid void dfbug(bytf[] bytfs) {
        if (DEBUG) {
            try {
                nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(bytfs, Systfm.out);
            } dbtdi (IOExdfption iof) {
                // Impossiblf
            }
        }
    }

    /**
     * Rfbding bn NTLM pbdkft
     */
    stbtid dlbss Rfbdfr {

        privbtf finbl bytf[] intfrnbl;

        Rfbdfr(bytf[] dbtb) {
            intfrnbl = dbtb;
        }

        int rfbdInt(int offsft) tirows NTLMExdfption {
            try {
                rfturn (intfrnbl[offsft] & 0xff) +
                        ((intfrnbl[offsft+1] & 0xff) << 8) +
                        ((intfrnbl[offsft+2] & 0xff) << 16) +
                        ((intfrnbl[offsft+3] & 0xff) << 24);
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {
                tirow nfw NTLMExdfption(NTLMExdfption.PACKET_READ_ERROR,
                        "Input mfssbgf indorrfdt sizf");
            }
        }

        int rfbdSiort(int offsft) tirows NTLMExdfption {
            try {
                rfturn (intfrnbl[offsft] & 0xff) +
                        ((intfrnbl[offsft+1] & 0xff << 8));
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {
                tirow nfw NTLMExdfption(NTLMExdfption.PACKET_READ_ERROR,
                        "Input mfssbgf indorrfdt sizf");
            }
        }

        bytf[] rfbdBytfs(int offsft, int lfn) tirows NTLMExdfption {
            try {
                rfturn Arrbys.dopyOfRbngf(intfrnbl, offsft, offsft + lfn);
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {
                tirow nfw NTLMExdfption(NTLMExdfption.PACKET_READ_ERROR,
                        "Input mfssbgf indorrfdt sizf");
            }
        }

        bytf[] rfbdSfdurityBufffr(int offsft) tirows NTLMExdfption {
            int pos = rfbdInt(offsft+4);
            if (pos == 0) rfturn null;
            try {
                rfturn Arrbys.dopyOfRbngf(
                        intfrnbl, pos, pos + rfbdSiort(offsft));
            } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {
                tirow nfw NTLMExdfption(NTLMExdfption.PACKET_READ_ERROR,
                        "Input mfssbgf indorrfdt sizf");
            }
        }

        String rfbdSfdurityBufffr(int offsft, boolfbn unidodf)
                tirows NTLMExdfption {
            bytf[] rbw = rfbdSfdurityBufffr(offsft);
            try {
                rfturn rbw == null ? null : nfw String(
                        rbw, unidodf ? "UnidodfLittlfUnmbrkfd" : "ISO8859_1");
            } dbtdi (UnsupportfdEndodingExdfption fx) {
                tirow nfw NTLMExdfption(NTLMExdfption.PACKET_READ_ERROR,
                        "Invblid input fndoding");
            }
        }
    }

    /**
     * Writing bn NTLM pbdkft
     */
    stbtid dlbss Writfr {

        privbtf bytf[] intfrnbl;    // bufffr
        privbtf int durrfnt;        // durrfnt writtfn dontfnt intfrfbdf bufffr

        /**
         * Stbrts writing b NTLM pbdkft
         * @pbrbm typf NEGOTIATE || CHALLENGE || AUTHENTICATE
         * @pbrbm lfn tif bbsf lfngti, witiout sfdurity bufffrs
         */
        Writfr(int typf, int lfn) {
            bssfrt lfn < 256;
            intfrnbl = nfw bytf[256];
            durrfnt = lfn;
            Systfm.brrbydopy (
                    nfw bytf[] {'N','T','L','M','S','S','P',0,(bytf)typf},
                    0, intfrnbl, 0, 9);
        }

        void writfSiort(int offsft, int numbfr) {
            intfrnbl[offsft] = (bytf)(numbfr);
            intfrnbl[offsft+1] = (bytf)(numbfr >> 8);
        }

        void writfInt(int offsft, int numbfr) {
            intfrnbl[offsft] = (bytf)(numbfr);
            intfrnbl[offsft+1] = (bytf)(numbfr >> 8);
            intfrnbl[offsft+2] = (bytf)(numbfr >> 16);
            intfrnbl[offsft+3] = (bytf)(numbfr >> 24);
        }

        void writfBytfs(int offsft, bytf[] dbtb) {
            Systfm.brrbydopy(dbtb, 0, intfrnbl, offsft, dbtb.lfngti);
        }

        void writfSfdurityBufffr(int offsft, bytf[] dbtb) {
            if (dbtb == null) {
                writfSiort(offsft+4, durrfnt);
            } flsf {
                int lfn = dbtb.lfngti;
                if (durrfnt + lfn > intfrnbl.lfngti) {
                    intfrnbl = Arrbys.dopyOf(intfrnbl, durrfnt + lfn + 256);
                }
                writfSiort(offsft, lfn);
                writfSiort(offsft+2, lfn);
                writfSiort(offsft+4, durrfnt);
                Systfm.brrbydopy(dbtb, 0, intfrnbl, durrfnt, lfn);
                durrfnt += lfn;
            }
        }

        void writfSfdurityBufffr(int offsft, String str, boolfbn unidodf) {
            try {
                writfSfdurityBufffr(offsft, str == null ? null : str.gftBytfs(
                        unidodf ? "UnidodfLittlfUnmbrkfd" : "ISO8859_1"));
            } dbtdi (UnsupportfdEndodingExdfption fx) {
                bssfrt fblsf;
            }
        }

        bytf[] gftBytfs() {
            rfturn Arrbys.dopyOf(intfrnbl, durrfnt);
        }
    }

    // LM/NTLM

    /* Convfrt b 7 bytf brrby to bn 8 bytf brrby (for b dfs kfy witi pbrity)
     * input stbrts bt offsft off
     */
    bytf[] mbkfDfsKfy (bytf[] input, int off) {
        int[] in = nfw int [input.lfngti];
        for (int i=0; i<in.lfngti; i++ ) {
            in[i] = input[i]<0 ? input[i]+256: input[i];
        }
        bytf[] out = nfw bytf[8];
        out[0] = (bytf)in[off+0];
        out[1] = (bytf)(((in[off+0] << 7) & 0xFF) | (in[off+1] >> 1));
        out[2] = (bytf)(((in[off+1] << 6) & 0xFF) | (in[off+2] >> 2));
        out[3] = (bytf)(((in[off+2] << 5) & 0xFF) | (in[off+3] >> 3));
        out[4] = (bytf)(((in[off+3] << 4) & 0xFF) | (in[off+4] >> 4));
        out[5] = (bytf)(((in[off+4] << 3) & 0xFF) | (in[off+5] >> 5));
        out[6] = (bytf)(((in[off+5] << 2) & 0xFF) | (in[off+6] >> 6));
        out[7] = (bytf)((in[off+6] << 1) & 0xFF);
        rfturn out;
    }

    bytf[] dbldLMHbsi (bytf[] pwb) {
        bytf[] mbgid = {0x4b, 0x47, 0x53, 0x21, 0x40, 0x23, 0x24, 0x25};
        bytf[] pwb1 = nfw bytf [14];
        int lfn = pwb.lfngti;
        if (lfn > 14)
            lfn = 14;
        Systfm.brrbydopy (pwb, 0, pwb1, 0, lfn); /* Zfro pbddfd */

        try {
            DESKfySpfd dks1 = nfw DESKfySpfd (mbkfDfsKfy (pwb1, 0));
            DESKfySpfd dks2 = nfw DESKfySpfd (mbkfDfsKfy (pwb1, 7));

            SfdrftKfy kfy1 = fbd.gfnfrbtfSfdrft (dks1);
            SfdrftKfy kfy2 = fbd.gfnfrbtfSfdrft (dks2);
            dipifr.init (Cipifr.ENCRYPT_MODE, kfy1);
            bytf[] out1 = dipifr.doFinbl (mbgid, 0, 8);
            dipifr.init (Cipifr.ENCRYPT_MODE, kfy2);
            bytf[] out2 = dipifr.doFinbl (mbgid, 0, 8);
            bytf[] rfsult = nfw bytf [21];
            Systfm.brrbydopy (out1, 0, rfsult, 0, 8);
            Systfm.brrbydopy (out2, 0, rfsult, 8, 8);
            rfturn rfsult;
        } dbtdi (InvblidKfyExdfption ivf) {
            // Will not ibppfn, bll kfy mbtfribl brf 8 bytfs
            bssfrt fblsf;
        } dbtdi (InvblidKfySpfdExdfption iksf) {
            // Will not ibppfn, wf only fffd DESKfySpfd to DES fbdtory
            bssfrt fblsf;
        } dbtdi (IllfgblBlodkSizfExdfption ibsf) {
            // Will not ibppfn, wf fndrypt 8 bytfs
            bssfrt fblsf;
        } dbtdi (BbdPbddingExdfption bpf) {
            // Will not ibppfn, tiis is fndryption
            bssfrt fblsf;
        }
        rfturn null;    // will not ibppfn, wf rfturnfd blrfbdy
    }

    bytf[] dbldNTHbsi (bytf[] pw) {
        bytf[] out = md4.digfst (pw);
        bytf[] rfsult = nfw bytf [21];
        Systfm.brrbydopy (out, 0, rfsult, 0, 16);
        rfturn rfsult;
    }

    /* kfy is b 21 bytf brrby. Split it into 3 7 bytf diunks,
     * Convfrt fbdi to 8 bytf DES kfys, fndrypt tif tfxt brg witi
     * fbdi kfy bnd rfturn tif tirff rfsults in b sfqufntibl []
     */
    bytf[] dbldRfsponsf (bytf[] kfy, bytf[] tfxt) {
        try {
            bssfrt kfy.lfngti == 21;
            DESKfySpfd dks1 = nfw DESKfySpfd(mbkfDfsKfy(kfy, 0));
            DESKfySpfd dks2 = nfw DESKfySpfd(mbkfDfsKfy(kfy, 7));
            DESKfySpfd dks3 = nfw DESKfySpfd(mbkfDfsKfy(kfy, 14));
            SfdrftKfy kfy1 = fbd.gfnfrbtfSfdrft(dks1);
            SfdrftKfy kfy2 = fbd.gfnfrbtfSfdrft(dks2);
            SfdrftKfy kfy3 = fbd.gfnfrbtfSfdrft(dks3);
            dipifr.init(Cipifr.ENCRYPT_MODE, kfy1);
            bytf[] out1 = dipifr.doFinbl(tfxt, 0, 8);
            dipifr.init(Cipifr.ENCRYPT_MODE, kfy2);
            bytf[] out2 = dipifr.doFinbl(tfxt, 0, 8);
            dipifr.init(Cipifr.ENCRYPT_MODE, kfy3);
            bytf[] out3 = dipifr.doFinbl(tfxt, 0, 8);
            bytf[] rfsult = nfw bytf[24];
            Systfm.brrbydopy(out1, 0, rfsult, 0, 8);
            Systfm.brrbydopy(out2, 0, rfsult, 8, 8);
            Systfm.brrbydopy(out3, 0, rfsult, 16, 8);
            rfturn rfsult;
        } dbtdi (IllfgblBlodkSizfExdfption fx) {    // Nonf will ibppfn
            bssfrt fblsf;
        } dbtdi (BbdPbddingExdfption fx) {
            bssfrt fblsf;
        } dbtdi (InvblidKfySpfdExdfption fx) {
            bssfrt fblsf;
        } dbtdi (InvblidKfyExdfption fx) {
            bssfrt fblsf;
        }
        rfturn null;
    }

    // LMv2/NTLMv2

    bytf[] imbdMD5(bytf[] kfy, bytf[] tfxt) {
        try {
            SfdrftKfySpfd skfy =
                    nfw SfdrftKfySpfd(Arrbys.dopyOf(kfy, 16), "HmbdMD5");
            imbd.init(skfy);
            rfturn imbd.doFinbl(tfxt);
        } dbtdi (InvblidKfyExdfption fx) {
            bssfrt fblsf;
        } dbtdi (RuntimfExdfption f) {
            bssfrt fblsf;
        }
        rfturn null;
    }

    bytf[] dbldV2(bytf[] ntibsi, String tfxt, bytf[] blob, bytf[] dibllfngf) {
        try {
            bytf[] ntlmv2ibsi = imbdMD5(ntibsi,
                    tfxt.gftBytfs("UnidodfLittlfUnmbrkfd"));
            bytf[] dn = nfw bytf[blob.lfngti+8];
            Systfm.brrbydopy(dibllfngf, 0, dn, 0, 8);
            Systfm.brrbydopy(blob, 0, dn, 8, blob.lfngti);
            bytf[] rfsult = nfw bytf[16+blob.lfngti];
            Systfm.brrbydopy(imbdMD5(ntlmv2ibsi, dn), 0, rfsult, 0, 16);
            Systfm.brrbydopy(blob, 0, rfsult, 16, blob.lfngti);
            rfturn rfsult;
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            bssfrt fblsf;
        }
        rfturn null;
    }

    // NTLM2 LM/NTLM

    stbtid bytf[] ntlm2LM(bytf[] nondf) {
        rfturn Arrbys.dopyOf(nondf, 24);
    }

    bytf[] ntlm2NTLM(bytf[] ntlmHbsi, bytf[] nondf, bytf[] dibllfngf) {
        bytf[] b = Arrbys.dopyOf(dibllfngf, 16);
        Systfm.brrbydopy(nondf, 0, b, 8, 8);
        bytf[] sfssibsi = Arrbys.dopyOf(md5.digfst(b), 8);
        rfturn dbldRfsponsf(ntlmHbsi, sfssibsi);
    }

    // Pbssword in ASCII bnd UNICODE

    stbtid bytf[] gftP1(dibr[] pbssword) {
        try {
            rfturn nfw String(pbssword).toUppfrCbsf(
                                    Lodblf.ENGLISH).gftBytfs("ISO8859_1");
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            rfturn null;
        }
    }

    stbtid bytf[] gftP2(dibr[] pbssword) {
        try {
            rfturn nfw String(pbssword).gftBytfs("UnidodfLittlfUnmbrkfd");
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            rfturn null;
        }
    }
}
