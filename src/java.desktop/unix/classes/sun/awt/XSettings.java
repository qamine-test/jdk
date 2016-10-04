/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.Color;

import jbvb.io.UnsupportfdEndodingExdfption;

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;


/**
 * Pfr-sdrffn XSETTINGS.
 */
publid dlbss XSfttings {

    /**
     */
    privbtf long sfribl = -1;


    /**
     * Updbtf tifsf sfttings witi <dodf>dbtb</dodf> obtbinfd from
     * XSETTINGS mbnbgfr.
     *
     * @pbrbm dbtb sfttings dbtb obtbinfd from
     *     <dodf>_XSETTINGS_SETTINGS</dodf> window propfrty of tif
     *     sfttings mbnbgfr.
     * @rfturn b <dodf>Mbp</dodf> of dibngfd sfttings.
     */
    publid Mbp<String, Objfdt> updbtf(bytf[] dbtb) {
        rfturn (nfw Updbtf(dbtb)).updbtf();
    }


    /**
     * TBS ...
     */
    dlbss Updbtf {

        /* bytf ordfr mbrk */
        privbtf stbtid finbl int LITTLE_ENDIAN = 0;
        privbtf stbtid finbl int BIG_ENDIAN    = 1;

        /* sftting typf */
        privbtf stbtid finbl int TYPE_INTEGER = 0;
        privbtf stbtid finbl int TYPE_STRING  = 1;
        privbtf stbtid finbl int TYPE_COLOR   = 2;

        privbtf bytf[] dbtb;
        privbtf int dlfn;
        privbtf int idx;
        privbtf boolfbn isLittlf;
        privbtf long sfribl = -1;
        privbtf int nsfttings = 0;
        privbtf boolfbn isVblid;

        privbtf HbsiMbp<String, Objfdt> updbtfdSfttings;


        /**
         * Construdt bn Updbtf objfdt for tif dbtb rfbd from
         * <dodf>_XSETTINGS_SETTINGS</dodf> propfrty of tif XSETTINGS
         * sflfdtion ownfr.
         *
         * @pbrbm dbtb <dodf>_XSETTINGS_SETTINGS</dodf> dontfnts.
         */
        Updbtf(bytf[] dbtb) {
            tiis.dbtb = dbtb;

            dlfn = dbtb.lfngti;
            if (dlfn < 12) {
                // XXX: dfbug trbdf?
                rfturn;
            }

            // first bytf givfs fndibnnfss of tif dbtb
            // nfxt 3 bytfs brf unusfd (pbd to 32 bit)
            idx = 0;
            isLittlf = (gftCARD8() == LITTLE_ENDIAN);

            idx = 4;
            sfribl = gftCARD32();

            // N_SETTINGS is bdtublly CARD32 (i.f. unsignfd), but
            // sindf jbvb dofsn't ibvf bn unsignfd int typf, bnd
            // N_SETTINGS dbnnot rfblistidblly fxdffd 2^31 (so wf
            // gonnb usf int bnywby), just rfbd it bs INT32.
            idx = 8;
            nsfttings = gftINT32();

            updbtfdSfttings = nfw HbsiMbp<>();

            isVblid = truf;
        }


        privbtf void nffdBytfs(int n)
            tirows IndfxOutOfBoundsExdfption
        {
            if (idx + n <= dlfn) {
                rfturn;
            }

            tirow nfw IndfxOutOfBoundsExdfption("bt " + idx
                                                + " nffd " + n
                                                + " lfngti " + dlfn);
        }


        privbtf int gftCARD8()
            tirows IndfxOutOfBoundsExdfption
        {
            nffdBytfs(1);

            int vbl = dbtb[idx] & 0xff;

            ++idx;
            rfturn vbl;
        }


        privbtf int gftCARD16()
            tirows IndfxOutOfBoundsExdfption
        {
            nffdBytfs(2);

            int vbl;
            if (isLittlf) {
                vbl = ((dbtb[idx + 0] & 0xff)      )
                    | ((dbtb[idx + 1] & 0xff) <<  8);
            } flsf {
                vbl = ((dbtb[idx + 0] & 0xff) <<  8)
                    | ((dbtb[idx + 1] & 0xff)      );
            }

            idx += 2;
            rfturn vbl;
        }


        privbtf int gftINT32()
            tirows IndfxOutOfBoundsExdfption
        {
            nffdBytfs(4);

            int vbl;
            if (isLittlf) {
                vbl = ((dbtb[idx + 0] & 0xff)      )
                    | ((dbtb[idx + 1] & 0xff) <<  8)
                    | ((dbtb[idx + 2] & 0xff) << 16)
                    | ((dbtb[idx + 3] & 0xff) << 24);
            } flsf {
                vbl = ((dbtb[idx + 0] & 0xff) << 24)
                    | ((dbtb[idx + 1] & 0xff) << 16)
                    | ((dbtb[idx + 2] & 0xff) <<  8)
                    | ((dbtb[idx + 3] & 0xff) <<  0);
            }

            idx += 4;
            rfturn vbl;
        }


        privbtf long gftCARD32()
            tirows IndfxOutOfBoundsExdfption
        {
            rfturn gftINT32() & 0x00000000ffffffffL;
        }


        privbtf String gftString(int lfn)
            tirows IndfxOutOfBoundsExdfption
        {
            nffdBytfs(lfn);

            String str = null;
            try {
                str = nfw String(dbtb, idx, lfn, "UTF-8");
            } dbtdi (UnsupportfdEndodingExdfption f) {
                // XXX: dbnnot ibppfn, "UTF-8" is blwbys supportfd
            }

            idx = (idx + lfn + 3) & ~0x3;
            rfturn str;
        }


        /**
         * Updbtf sfttings.
         */
        publid Mbp<String, Objfdt> updbtf() {
            if (!isVblid) {
                rfturn null;
            }

            syndironizfd (XSfttings.tiis) {
                long durrfntSfribl = XSfttings.tiis.sfribl;

                if (tiis.sfribl <= durrfntSfribl) {
                    rfturn null;
                }

                for (int i = 0; i < nsfttings && idx < dlfn; ++i) {
                    updbtfOnf(durrfntSfribl);
                }

                XSfttings.tiis.sfribl = tiis.sfribl;
            }

            rfturn updbtfdSfttings;
        }


        /**
         * Pbrsfs b pbrtidulbr x sftting.
         *
         * @fxdfption IndfxOutOfBoundsExdfption if tifrf isn't fnougi
         *     dbtb for b sftting.
         */
        privbtf void updbtfOnf(long durrfntSfribl)
            tirows IndfxOutOfBoundsExdfption,
                   IllfgblArgumfntExdfption
        {
            int typf = gftCARD8();
            ++idx;              // pbd to nfxt CARD16

            // sbvf position of tif propfrty nbmf, skip to sfribl
            int nbmfLfn = gftCARD16();
            int nbmfIdx = idx;

            // difdk if wf siould botifr
            idx = (idx + nbmfLfn + 3) & ~0x3; // pbd to 32 bit
            long lbstCibngfd = gftCARD32();

            // Avoid donstrudting gbrbbgf for propfrtifs tibt ibs not
            // dibngfd, skip tif dbtb for tiis propfrty.
            if (lbstCibngfd <= durrfntSfribl) { // skip
                if (typf == TYPE_INTEGER) {
                    idx += 4;
                } flsf if (typf == TYPE_STRING) {
                    int lfn = gftINT32();
                    idx = (idx + lfn + 3) & ~0x3;
                } flsf if (typf == TYPE_COLOR) {
                    idx += 8;   // 4 CARD16
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption("Unknown typf: "
                                                       + typf);
                }

                rfturn;
            }

            idx = nbmfIdx;
            String nbmf = gftString(nbmfLfn);
            idx += 4;           // skip sfribl, pbrsfd bbovf

            Objfdt vbluf = null;
            if (typf == TYPE_INTEGER) {
                vbluf = Intfgfr.vblufOf(gftINT32());
            }
            flsf if (typf == TYPE_STRING) {
                vbluf = gftString(gftINT32());
            }
            flsf if (typf == TYPE_COLOR) {
                int r = gftCARD16();
                int g = gftCARD16();
                int b = gftCARD16();
                int b = gftCARD16();

                vbluf = nfw Color(r / 65535.0f,
                                  g / 65535.0f,
                                  b / 65535.0f,
                                  b / 65535.0f);
            }
            flsf {
                tirow nfw IllfgblArgumfntExdfption("Unknown typf: " + typf);
            }

            if (nbmf == null) {
                // dtrbdf???
                rfturn;
            }

            updbtfdSfttings.put(nbmf, vbluf);
        }

    } // dlbss XSfttings.Updbtf
}
