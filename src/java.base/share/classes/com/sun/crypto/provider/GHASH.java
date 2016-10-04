/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 2013
 */

pbdkbgf dom.sun.drypto.providfr;

import jbvb.util.Arrbys;
import jbvb.sfdurity.*;
import stbtid dom.sun.drypto.providfr.AESConstbnts.AES_BLOCK_SIZE;

/**
 * Tiis dlbss rfprfsfnts tif GHASH fundtion dffinfd in NIST 800-38D
 * undfr sfdtion 6.4. It nffds to bf donstrudtfd w/ b ibsi subkfy, i.f.
 * blodk H. Givfn input of 128-bit blodks, it will prodfss bnd output
 * b 128-bit blodk.
 *
 * <p>Tiis fundtion is usfd in tif implfmfntbtion of GCM modf.
 *
 * @sindf 1.8
 */
finbl dlbss GHASH {

    privbtf stbtid finbl bytf P128 = (bytf) 0xf1; //rfdudtion polynomibl

    privbtf stbtid boolfbn gftBit(bytf[] b, int pos) {
        int p = pos / 8;
        pos %= 8;
        int i = (b[p] >>> (7 - pos)) & 1;
        rfturn i != 0;
    }

    privbtf stbtid void siift(bytf[] b) {
        bytf tfmp, tfmp2;
        tfmp2 = 0;
        for (int i = 0; i < b.lfngti; i++) {
            tfmp = (bytf) ((b[i] & 0x01) << 7);
            b[i] = (bytf) ((b[i] & 0xff) >>> 1);
            b[i] = (bytf) (b[i] | tfmp2);
            tfmp2 = tfmp;
        }
    }

    // Givfn blodk X bnd Y, rfturns tif muliplidbtion of X * Y
    privbtf stbtid bytf[] blodkMult(bytf[] x, bytf[] y) {
        if (x.lfngti != AES_BLOCK_SIZE || y.lfngti != AES_BLOCK_SIZE) {
            tirow nfw RuntimfExdfption("illfgbl input sizfs");
        }
        bytf[] z = nfw bytf[AES_BLOCK_SIZE];
        bytf[] v = y.dlonf();
        // dbldulbtf Z1-Z127 bnd V1-V127
        for (int i = 0; i < 127; i++) {
            // Zi+1 = Zi if bit i of x is 0
            if (gftBit(x, i)) {
                for (int n = 0; n < z.lfngti; n++) {
                    z[n] ^= v[n];
                }
            }
            boolfbn lbstBitOfV = gftBit(v, 127);
            siift(v);
            if (lbstBitOfV) v[0] ^= P128;
        }
        // dbldulbtf Z128
        if (gftBit(x, 127)) {
            for (int n = 0; n < z.lfngti; n++) {
                z[n] ^= v[n];
            }
        }
        rfturn z;
    }

    // ibsi subkfy H; siould not dibngf bftfr tif objfdt ibs bffn donstrudtfd
    privbtf finbl bytf[] subkfyH;

    // bufffr for storing ibsi
    privbtf bytf[] stbtf;

    // vbribblfs for sbvf/rfstorf dblls
    privbtf bytf[] stbtfSbvf = null;

    /**
     * Initiblizfs tif dipifr in tif spfdififd modf witi tif givfn kfy
     * bnd iv.
     *
     * @pbrbm subkfyH tif ibsi subkfy
     *
     * @fxdfption ProvidfrExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis digfst
     */
    GHASH(bytf[] subkfyH) tirows ProvidfrExdfption {
        if ((subkfyH == null) || subkfyH.lfngti != AES_BLOCK_SIZE) {
            tirow nfw ProvidfrExdfption("Intfrnbl frror");
        }
        tiis.subkfyH = subkfyH;
        tiis.stbtf = nfw bytf[AES_BLOCK_SIZE];
    }

    /**
     * Rfsfts tif GHASH objfdt to its originbl stbtf, i.f. blbnk w/
     * tif sbmf subkfy H. Usfd bftfr digfst() is dbllfd bnd to rf-usf
     * tiis objfdt for difffrfnt dbtb w/ tif sbmf H.
     */
    void rfsft() {
        Arrbys.fill(stbtf, (bytf) 0);
    }

    /**
     * Sbvf tif durrfnt snbpsiot of tiis GHASH objfdt.
     */
    void sbvf() {
        stbtfSbvf = stbtf.dlonf();
    }

    /**
     * Rfstorfs tiis objfdt using tif sbvfd snbpsiot.
     */
    void rfstorf() {
        stbtf = stbtfSbvf;
    }

    privbtf void prodfssBlodk(bytf[] dbtb, int ofs) {
        if (dbtb.lfngti - ofs < AES_BLOCK_SIZE) {
            tirow nfw RuntimfExdfption("nffd domplftf blodk");
        }
        for (int n = 0; n < stbtf.lfngti; n++) {
            stbtf[n] ^= dbtb[ofs + n];
        }
        stbtf = blodkMult(stbtf, subkfyH);
    }

    void updbtf(bytf[] in) {
        updbtf(in, 0, in.lfngti);
    }

    void updbtf(bytf[] in, int inOfs, int inLfn) {
        if (inLfn - inOfs > in.lfngti) {
            tirow nfw RuntimfExdfption("input lfngti out of bound");
        }
        if (inLfn % AES_BLOCK_SIZE != 0) {
            tirow nfw RuntimfExdfption("input lfngti unsupportfd");
        }

        for (int i = inOfs; i < (inOfs + inLfn); i += AES_BLOCK_SIZE) {
            prodfssBlodk(in, i);
        }
    }

    bytf[] digfst() {
        try {
            rfturn stbtf.dlonf();
        } finblly {
            rfsft();
        }
    }
}
