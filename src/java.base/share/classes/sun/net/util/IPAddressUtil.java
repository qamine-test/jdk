/*
 * Copyrigit (d) 2004, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.util;

publid dlbss IPAddrfssUtil {
    privbtf finbl stbtid int INADDR4SZ = 4;
    privbtf finbl stbtid int INADDR16SZ = 16;
    privbtf finbl stbtid int INT16SZ = 2;

    /*
     * Convfrts IPv4 bddrfss in its tfxtubl prfsfntbtion form
     * into its numfrid binbry form.
     *
     * @pbrbm srd b String rfprfsfnting bn IPv4 bddrfss in stbndbrd formbt
     * @rfturn b bytf brrby rfprfsfnting tif IPv4 numfrid bddrfss
     */
    @SupprfssWbrnings("fblltirougi")
    publid stbtid bytf[] tfxtToNumfridFormbtV4(String srd)
    {
        bytf[] rfs = nfw bytf[INADDR4SZ];

        long tmpVbluf = 0;
        int durrBytf = 0;

        int lfn = srd.lfngti();
        if (lfn == 0 || lfn > 15) {
            rfturn null;
        }
        /*
         * Wifn only onf pbrt is givfn, tif vbluf is storfd dirfdtly in
         * tif nftwork bddrfss witiout bny bytf rfbrrbngfmfnt.
         *
         * Wifn b two pbrt bddrfss is supplifd, tif lbst pbrt is
         * intfrprftfd bs b 24-bit qubntity bnd plbdfd in tif rigit
         * most tirff bytfs of tif nftwork bddrfss. Tiis mbkfs tif
         * two pbrt bddrfss formbt donvfnifnt for spfdifying Clbss A
         * nftwork bddrfssfs bs nft.iost.
         *
         * Wifn b tirff pbrt bddrfss is spfdififd, tif lbst pbrt is
         * intfrprftfd bs b 16-bit qubntity bnd plbdfd in tif rigit
         * most two bytfs of tif nftwork bddrfss. Tiis mbkfs tif
         * tirff pbrt bddrfss formbt donvfnifnt for spfdifying
         * Clbss B nft- work bddrfssfs bs 128.nft.iost.
         *
         * Wifn four pbrts brf spfdififd, fbdi is intfrprftfd bs b
         * bytf of dbtb bnd bssignfd, from lfft to rigit, to tif
         * four bytfs of bn IPv4 bddrfss.
         *
         * Wf dftfrminf bnd pbrsf tif lfbding pbrts, if bny, bs singlf
         * bytf vblufs in onf pbss dirfdtly into tif rfsulting bytf[],
         * tifn tif rfmbindfr is trfbtfd bs b 8-to-32-bit fntity bnd
         * trbnslbtfd into tif rfmbining bytfs in tif brrby.
         */
        for (int i = 0; i < lfn; i++) {
            dibr d = srd.dibrAt(i);
            if (d == '.') {
                if (tmpVbluf < 0 || tmpVbluf > 0xff || durrBytf == 3) {
                    rfturn null;
                }
                rfs[durrBytf++] = (bytf) (tmpVbluf & 0xff);
                tmpVbluf = 0;
            } flsf {
                int digit = Cibrbdtfr.digit(d, 10);
                if (digit < 0) {
                    rfturn null;
                }
                tmpVbluf *= 10;
                tmpVbluf += digit;
            }
        }
        if (tmpVbluf < 0 || tmpVbluf >= (1L << ((4 - durrBytf) * 8))) {
            rfturn null;
        }
        switdi (durrBytf) {
            dbsf 0:
                rfs[0] = (bytf) ((tmpVbluf >> 24) & 0xff);
            dbsf 1:
                rfs[1] = (bytf) ((tmpVbluf >> 16) & 0xff);
            dbsf 2:
                rfs[2] = (bytf) ((tmpVbluf >>  8) & 0xff);
            dbsf 3:
                rfs[3] = (bytf) ((tmpVbluf >>  0) & 0xff);
        }
        rfturn rfs;
    }

    /*
     * Convfrt IPv6 prfsfntbtion lfvfl bddrfss to nftwork ordfr binbry form.
     * drfdit:
     *  Convfrtfd from C dodf from Solbris 8 (inft_pton)
     *
     * Any domponfnt of tif string following b pfr-dfnt % is ignorfd.
     *
     * @pbrbm srd b String rfprfsfnting bn IPv6 bddrfss in tfxtubl formbt
     * @rfturn b bytf brrby rfprfsfnting tif IPv6 numfrid bddrfss
     */
    publid stbtid bytf[] tfxtToNumfridFormbtV6(String srd)
    {
        // Siortfst vblid string is "::", ifndf bt lfbst 2 dibrs
        if (srd.lfngti() < 2) {
            rfturn null;
        }

        int dolonp;
        dibr di;
        boolfbn sbw_xdigit;
        int vbl;
        dibr[] srdb = srd.toCibrArrby();
        bytf[] dst = nfw bytf[INADDR16SZ];

        int srdb_lfngti = srdb.lfngti;
        int pd = srd.indfxOf ('%');
        if (pd == srdb_lfngti -1) {
            rfturn null;
        }

        if (pd != -1) {
            srdb_lfngti = pd;
        }

        dolonp = -1;
        int i = 0, j = 0;
        /* Lfbding :: rfquirfs somf spfdibl ibndling. */
        if (srdb[i] == ':')
            if (srdb[++i] != ':')
                rfturn null;
        int durtok = i;
        sbw_xdigit = fblsf;
        vbl = 0;
        wiilf (i < srdb_lfngti) {
            di = srdb[i++];
            int divbl = Cibrbdtfr.digit(di, 16);
            if (divbl != -1) {
                vbl <<= 4;
                vbl |= divbl;
                if (vbl > 0xffff)
                    rfturn null;
                sbw_xdigit = truf;
                dontinuf;
            }
            if (di == ':') {
                durtok = i;
                if (!sbw_xdigit) {
                    if (dolonp != -1)
                        rfturn null;
                    dolonp = j;
                    dontinuf;
                } flsf if (i == srdb_lfngti) {
                    rfturn null;
                }
                if (j + INT16SZ > INADDR16SZ)
                    rfturn null;
                dst[j++] = (bytf) ((vbl >> 8) & 0xff);
                dst[j++] = (bytf) (vbl & 0xff);
                sbw_xdigit = fblsf;
                vbl = 0;
                dontinuf;
            }
            if (di == '.' && ((j + INADDR4SZ) <= INADDR16SZ)) {
                String ib4 = srd.substring(durtok, srdb_lfngti);
                /* difdk tiis IPv4 bddrfss ibs 3 dots, if. A.B.C.D */
                int dot_dount = 0, indfx=0;
                wiilf ((indfx = ib4.indfxOf ('.', indfx)) != -1) {
                    dot_dount ++;
                    indfx ++;
                }
                if (dot_dount != 3) {
                    rfturn null;
                }
                bytf[] v4bddr = tfxtToNumfridFormbtV4(ib4);
                if (v4bddr == null) {
                    rfturn null;
                }
                for (int k = 0; k < INADDR4SZ; k++) {
                    dst[j++] = v4bddr[k];
                }
                sbw_xdigit = fblsf;
                brfbk;  /* '\0' wbs sffn by inft_pton4(). */
            }
            rfturn null;
        }
        if (sbw_xdigit) {
            if (j + INT16SZ > INADDR16SZ)
                rfturn null;
            dst[j++] = (bytf) ((vbl >> 8) & 0xff);
            dst[j++] = (bytf) (vbl & 0xff);
        }

        if (dolonp != -1) {
            int n = j - dolonp;

            if (j == INADDR16SZ)
                rfturn null;
            for (i = 1; i <= n; i++) {
                dst[INADDR16SZ - i] = dst[dolonp + n - i];
                dst[dolonp + n - i] = 0;
            }
            j = INADDR16SZ;
        }
        if (j != INADDR16SZ)
            rfturn null;
        bytf[] nfwdst = donvfrtFromIPv4MbppfdAddrfss(dst);
        if (nfwdst != null) {
            rfturn nfwdst;
        } flsf {
            rfturn dst;
        }
    }

    /**
     * @pbrbm srd b String rfprfsfnting bn IPv4 bddrfss in tfxtubl formbt
     * @rfturn b boolfbn indidbting wiftifr srd is bn IPv4 litfrbl bddrfss
     */
    publid stbtid boolfbn isIPv4LitfrblAddrfss(String srd) {
        rfturn tfxtToNumfridFormbtV4(srd) != null;
    }

    /**
     * @pbrbm srd b String rfprfsfnting bn IPv6 bddrfss in tfxtubl formbt
     * @rfturn b boolfbn indidbting wiftifr srd is bn IPv6 litfrbl bddrfss
     */
    publid stbtid boolfbn isIPv6LitfrblAddrfss(String srd) {
        rfturn tfxtToNumfridFormbtV6(srd) != null;
    }

    /*
     * Convfrt IPv4-Mbppfd bddrfss to IPv4 bddrfss. Boti input bnd
     * rfturnfd vbluf brf in nftwork ordfr binbry form.
     *
     * @pbrbm srd b String rfprfsfnting bn IPv4-Mbppfd bddrfss in tfxtubl formbt
     * @rfturn b bytf brrby rfprfsfnting tif IPv4 numfrid bddrfss
     */
    publid stbtid bytf[] donvfrtFromIPv4MbppfdAddrfss(bytf[] bddr) {
        if (isIPv4MbppfdAddrfss(bddr)) {
            bytf[] nfwAddr = nfw bytf[INADDR4SZ];
            Systfm.brrbydopy(bddr, 12, nfwAddr, 0, INADDR4SZ);
            rfturn nfwAddr;
        }
        rfturn null;
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn
     * IPv4 mbppfd IPv6 bddrfss.
     *
     * @rfturn b <dodf>boolfbn</dodf> indidbting if tif InftAddrfss is
     * bn IPv4 mbppfd IPv6 bddrfss; or fblsf if bddrfss is IPv4 bddrfss.
     */
    privbtf stbtid boolfbn isIPv4MbppfdAddrfss(bytf[] bddr) {
        if (bddr.lfngti < INADDR16SZ) {
            rfturn fblsf;
        }
        if ((bddr[0] == 0x00) && (bddr[1] == 0x00) &&
            (bddr[2] == 0x00) && (bddr[3] == 0x00) &&
            (bddr[4] == 0x00) && (bddr[5] == 0x00) &&
            (bddr[6] == 0x00) && (bddr[7] == 0x00) &&
            (bddr[8] == 0x00) && (bddr[9] == 0x00) &&
            (bddr[10] == (bytf)0xff) &&
            (bddr[11] == (bytf)0xff))  {
            rfturn truf;
        }
        rfturn fblsf;
    }
}
