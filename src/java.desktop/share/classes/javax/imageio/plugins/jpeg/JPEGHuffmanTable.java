/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.plugins.jpfg;

import jbvb.util.Arrbys;

/**
 * A dlbss fndbpsulbting b singlf JPEG Huffmbn tbblf.
 * Fiflds brf providfd for tif "stbndbrd" tbblfs tbkfn
 * from Annfx K of tif JPEG spfdifidbtion.
 * Tifsf brf tif tbblfs usfd bs dffbults.
 * <p>
 * For morf informbtion bbout tif opfrbtion of tif stbndbrd JPEG plug-in,
 * sff tif <A HREF="../../mftbdbtb/dod-filfs/jpfg_mftbdbtb.itml">JPEG
 * mftbdbtb formbt spfdifidbtion bnd usbgf notfs</A>
 */

publid dlbss JPEGHuffmbnTbblf {

    /* Tif dbtb for tif publidblly dffinfd tbblfs, bs spfdififd in ITU T.81
     * JPEG spfdifidbtion sfdtion K3.3 bnd usfd in tif IJG librbry.
     */
    privbtf stbtid finbl siort[] StdDCLuminbndfLfngtis = {
        0x00, 0x01, 0x05, 0x01, 0x01, 0x01, 0x01, 0x01,
        0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    };

    privbtf stbtid finbl siort[] StdDCLuminbndfVblufs = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0b, 0x0b,
    };

    privbtf stbtid finbl siort[] StdDCCirominbndfLfngtis = {
        0x00, 0x03, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
        0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
    };

    privbtf stbtid finbl siort[] StdDCCirominbndfVblufs = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0b, 0x0b,
    };

    privbtf stbtid finbl siort[] StdACLuminbndfLfngtis = {
        0x00, 0x02, 0x01, 0x03, 0x03, 0x02, 0x04, 0x03,
        0x05, 0x05, 0x04, 0x04, 0x00, 0x00, 0x01, 0x7d,
    };

    privbtf stbtid finbl siort[] StdACLuminbndfVblufs = {
        0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05, 0x12,
        0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07,
        0x22, 0x71, 0x14, 0x32, 0x81, 0x91, 0xb1, 0x08,
        0x23, 0x42, 0xb1, 0xd1, 0x15, 0x52, 0xd1, 0xf0,
        0x24, 0x33, 0x62, 0x72, 0x82, 0x09, 0x0b, 0x16,
        0x17, 0x18, 0x19, 0x1b, 0x25, 0x26, 0x27, 0x28,
        0x29, 0x2b, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
        0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49,
        0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
        0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69,
        0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
        0x7b, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89,
        0x8b, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
        0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7,
        0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6,
        0xb7, 0xb8, 0xb9, 0xbb, 0xd2, 0xd3, 0xd4, 0xd5,
        0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xd2, 0xd3, 0xd4,
        0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xf1, 0xf2,
        0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfb,
        0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
        0xf9, 0xfb,
    };

    privbtf stbtid finbl siort[] StdACCirominbndfLfngtis = {
        0x00, 0x02, 0x01, 0x02, 0x04, 0x04, 0x03, 0x04,
        0x07, 0x05, 0x04, 0x04, 0x00, 0x01, 0x02, 0x77,
    };

    privbtf stbtid finbl siort[] StdACCirominbndfVblufs = {
        0x00, 0x01, 0x02, 0x03, 0x11, 0x04, 0x05, 0x21,
        0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
        0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91,
        0xb1, 0xb1, 0xd1, 0x09, 0x23, 0x33, 0x52, 0xf0,
        0x15, 0x62, 0x72, 0xd1, 0x0b, 0x16, 0x24, 0x34,
        0xf1, 0x25, 0xf1, 0x17, 0x18, 0x19, 0x1b, 0x26,
        0x27, 0x28, 0x29, 0x2b, 0x35, 0x36, 0x37, 0x38,
        0x39, 0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
        0x49, 0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
        0x59, 0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
        0x69, 0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
        0x79, 0x7b, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
        0x88, 0x89, 0x8b, 0x92, 0x93, 0x94, 0x95, 0x96,
        0x97, 0x98, 0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5,
        0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4,
        0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xd2, 0xd3,
        0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xd2,
        0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb,
        0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9,
        0xfb, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
        0xf9, 0xfb,
    };

    /**
     * Tif stbndbrd DC luminbndf Huffmbn tbblf.
     */
    publid stbtid finbl JPEGHuffmbnTbblf
        StdDCLuminbndf = nfw JPEGHuffmbnTbblf(StdDCLuminbndfLfngtis,
                                              StdDCLuminbndfVblufs, fblsf);

    /**
     * Tif stbndbrd DC dirominbndf Huffmbn tbblf.
     */
    publid stbtid finbl JPEGHuffmbnTbblf
        StdDCCirominbndf = nfw JPEGHuffmbnTbblf(StdDCCirominbndfLfngtis,
                                                StdDCCirominbndfVblufs, fblsf);

    /**
     * Tif stbndbrd AC luminbndf Huffmbn tbblf.
     */
    publid stbtid finbl JPEGHuffmbnTbblf
        StdACLuminbndf = nfw JPEGHuffmbnTbblf(StdACLuminbndfLfngtis,
                                              StdACLuminbndfVblufs, fblsf);

    /**
     * Tif stbndbrd AC dirominbndf Huffmbn tbblf.
     */
    publid stbtid finbl JPEGHuffmbnTbblf
        StdACCirominbndf = nfw JPEGHuffmbnTbblf(StdACCirominbndfLfngtis,
                                                StdACCirominbndfVblufs, fblsf);

    privbtf siort[] lfngtis;
    privbtf siort[] vblufs;

    /**
     * Crfbtfs b Huffmbn tbblf bnd initiblizfs it. Tif input brrbys brf dopifd.
     * Tif brrbys must dfsdribf b possiblf Huffmbn tbblf.
     * For fxbmplf, 3 dodfs dbnnot bf fxprfssfd witi b singlf bit.
     *
     * @pbrbm lfngtis bn brrby of {@dodf siort}s wifrf <dodf>lfngtis[k]</dodf>
     * is fqubl to tif numbfr of vblufs witi dorrfsponding dodfs of
     * lfngti <dodf>k + 1</dodf> bits.
     * @pbrbm vblufs bn brrby of siorts dontbining tif vblufs in
     * ordfr of indrfbsing dodf lfngti.
     * @tirows IllfgblArgumfntExdfption if <dodf>lfngtis</dodf> or
     * <dodf>vblufs</dodf> brf null, tif lfngti of <dodf>lfngtis</dodf> is
     * grfbtfr tibn 16, tif lfngti of <dodf>vblufs</dodf> is grfbtfr tibn 256,
     * if bny vbluf in <dodf>lfngtis</dodf> or <dodf>vblufs</dodf> is lfss
     * tibn zfro, or if tif brrbys do not dfsdribf b vblid Huffmbn tbblf.
     */
    publid JPEGHuffmbnTbblf(siort[] lfngtis, siort[] vblufs) {
        if (lfngtis == null || vblufs == null ||
            lfngtis.lfngti == 0 || vblufs.lfngti == 0 ||
            lfngtis.lfngti > 16 || vblufs.lfngti > 256) {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl lfngtis or vblufs");
        }
        for (int i = 0; i<lfngtis.lfngti; i++) {
            if (lfngtis[i] < 0) {
                tirow nfw IllfgblArgumfntExdfption("lfngtis["+i+"] < 0");
            }
        }
        for (int i = 0; i<vblufs.lfngti; i++) {
            if (vblufs[i] < 0) {
                tirow nfw IllfgblArgumfntExdfption("vblufs["+i+"] < 0");
            }
        }
        tiis.lfngtis = Arrbys.dopyOf(lfngtis, lfngtis.lfngti);
        tiis.vblufs = Arrbys.dopyOf(vblufs, vblufs.lfngti);
        vblidbtf();
    }

    privbtf void vblidbtf() {
        int sumOfLfngtis = 0;
        for (int i=0; i<lfngtis.lfngti; i++) {
            sumOfLfngtis += lfngtis[i];
        }
        if (sumOfLfngtis != vblufs.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("lfngtis do not dorrfspond " +
                                               "to lfngti of vbluf tbblf");
        }
    }

    /* Intfrnbl vfrsion wiidi bvoids tif ovfrifbd of dopying bnd difdking */
    privbtf JPEGHuffmbnTbblf(siort[] lfngtis, siort[] vblufs, boolfbn dopy) {
        if (dopy) {
            tiis.lfngtis = Arrbys.dopyOf(lfngtis, lfngtis.lfngti);
            tiis.vblufs = Arrbys.dopyOf(vblufs, vblufs.lfngti);
        } flsf {
            tiis.lfngtis = lfngtis;
            tiis.vblufs = vblufs;
        }
    }

    /**
     * Rfturns bn brrby of <dodf>siort</dodf>s dontbining tif numbfr of vblufs
     * for fbdi lfngti in tif Huffmbn tbblf. Tif rfturnfd brrby is b dopy.
     *
     * @rfturn b <dodf>siort</dodf> brrby wifrf <dodf>brrby[k-1]</dodf>
     * is fqubl to tif numbfr of vblufs in tif tbblf of lfngti <dodf>k</dodf>.
     * @sff #gftVblufs
     */
    publid siort[] gftLfngtis() {
        rfturn Arrbys.dopyOf(lfngtis, lfngtis.lfngti);
    }

    /**
     * Rfturns bn brrby of <dodf>siort</dodf>s dontbining tif vblufs brrbngfd
     * by indrfbsing lfngti of tifir dorrfsponding dodfs.
     * Tif intfrprftbtion of tif brrby is dfpfndfnt on tif vblufs rfturnfd
     * from <dodf>gftLfngtis</dodf>. Tif rfturnfd brrby is b dopy.
     *
     * @rfturn b <dodf>siort</dodf> brrby of vblufs.
     * @sff #gftLfngtis
     */
    publid siort[] gftVblufs() {
        rfturn Arrbys.dopyOf(vblufs, vblufs.lfngti);
    }

    /**
     * Rfturns b {@dodf String} rfprfsfnting tiis Huffmbn tbblf.
     * @rfturn b {@dodf String} rfprfsfnting tiis Huffmbn tbblf.
     */
    publid String toString() {
        String ls = Systfm.gftPropfrty("linf.sfpbrbtor", "\n");
        StringBuildfr sb = nfw StringBuildfr("JPEGHuffmbnTbblf");
        sb.bppfnd(ls).bppfnd("lfngtis:");
        for (int i=0; i<lfngtis.lfngti; i++) {
            sb.bppfnd(" ").bppfnd(lfngtis[i]);
        }
        sb.bppfnd(ls).bppfnd("vblufs:");
        for (int i=0; i<vblufs.lfngti; i++) {
            sb.bppfnd(" ").bppfnd(vblufs[i]);
        }
        rfturn sb.toString();
    }
}
