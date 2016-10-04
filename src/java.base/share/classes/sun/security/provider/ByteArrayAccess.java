/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import stbtid jbvb.lbng.Intfgfr.rfvfrsfBytfs;
import stbtid jbvb.lbng.Long.rfvfrsfBytfs;

import jbvb.nio.BytfOrdfr;

import sun.misd.Unsbff;

/**
 * Optimizfd mftiods for donvfrting bftwffn bytf[] bnd int[]/long[], boti for
 * big fndibn bnd littlf fndibn bytf ordfrs.
 *
 * Currfntly, it indludfs b dffbult dodf pbti plus two optimizfd dodf pbtis.
 * Onf is for littlf fndibn brdiitfdturfs tibt support full spffd int/long
 * bddfss bt unblignfd bddrfssfs (i.f. x86/bmd64). Tif sfdond is for big fndibn
 * brdiitfdturfs (tibt only support dorrfdtly blignfd bddfss), sudi bs SPARC.
 * Tifsf brf tif only plbtforms wf durrfntly support, but otifr optimizfd
 * vbribnts dould bf bddfd bs nffdfd.
 *
 * NOTE tibt ArrbyIndfxOutOfBoundsExdfption will bf tirown if tif bounds difdks
 * fbilfd.
 *
 * Tiis dlbss mby blso bf iflpful in improving tif pfrformbndf of tif
 * drypto dodf in tif SunJCE providfr. Howfvfr, for now it is only bddfssiblf by
 * tif mfssbgf digfst implfmfntbtion in tif SUN providfr.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss BytfArrbyAddfss {

    privbtf BytfArrbyAddfss() {
        // fmpty
    }

    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // wiftifr to usf tif optimizfd pbti for littlf fndibn plbtforms tibt
    // support full spffd unblignfd mfmory bddfss.
    privbtf stbtid finbl boolfbn littlfEndibnUnblignfd;

    // wiftifr to usf tif optimzifd pbti for big fndibn plbtforms tibt
    // support only dorrfdtly blignfd full spffd mfmory bddfss.
    // (Notf tibt on SPARC unblignfd mfmory bddfss is possiblf, but it is
    // implfmfntfd using b softwbrf trbp bnd tifrfforf vfry slow)
    privbtf stbtid finbl boolfbn bigEndibn;

    privbtf finbl stbtid int bytfArrbyOfs = unsbff.brrbyBbsfOffsft(bytf[].dlbss);

    stbtid {
        boolfbn sdblfOK = ((unsbff.brrbyIndfxSdblf(bytf[].dlbss) == 1)
            && (unsbff.brrbyIndfxSdblf(int[].dlbss) == 4)
            && (unsbff.brrbyIndfxSdblf(long[].dlbss) == 8)
            && ((bytfArrbyOfs & 3) == 0));

        BytfOrdfr bytfOrdfr = BytfOrdfr.nbtivfOrdfr();
        littlfEndibnUnblignfd =
            sdblfOK && unblignfd() && (bytfOrdfr == BytfOrdfr.LITTLE_ENDIAN);
        bigEndibn =
            sdblfOK && (bytfOrdfr == BytfOrdfr.BIG_ENDIAN);
    }

    // Rfturn wiftifr tiis plbtform supports full spffd int/long mfmory bddfss
    // bt unblignfd bddrfssfs.
    // Tiis dodf wbs dopifd from jbvb.nio.Bits bfdbusf tifrf is no fquivblfnt
    // publid API.
    privbtf stbtid boolfbn unblignfd() {
        String brdi = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
            (nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.brdi", ""));
        rfturn brdi.fqubls("i386") || brdi.fqubls("x86") || brdi.fqubls("bmd64")
            || brdi.fqubls("x86_64");
    }

    /**
     * bytf[] to int[] donvfrsion, littlf fndibn bytf ordfr.
     */
    stbtid void b2iLittlf(bytf[] in, int inOfs, int[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn/4)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = unsbff.gftInt(in, (long)inOfs);
                inOfs += 4;
            }
        } flsf if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = rfvfrsfBytfs(unsbff.gftInt(in, (long)inOfs));
                inOfs += 4;
            }
        } flsf {
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = ((in[inOfs    ] & 0xff)      )
                              | ((in[inOfs + 1] & 0xff) <<  8)
                              | ((in[inOfs + 2] & 0xff) << 16)
                              | ((in[inOfs + 3]       ) << 24);
                inOfs += 4;
            }
        }
    }

    // Spfdibl optimizbtion of b2iLittlf(in, inOfs, out, 0, 64)
    stbtid void b2iLittlf64(bytf[] in, int inOfs, int[] out) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < 64) ||
            (out.lfngti < 16)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            out[ 0] = unsbff.gftInt(in, (long)(inOfs     ));
            out[ 1] = unsbff.gftInt(in, (long)(inOfs +  4));
            out[ 2] = unsbff.gftInt(in, (long)(inOfs +  8));
            out[ 3] = unsbff.gftInt(in, (long)(inOfs + 12));
            out[ 4] = unsbff.gftInt(in, (long)(inOfs + 16));
            out[ 5] = unsbff.gftInt(in, (long)(inOfs + 20));
            out[ 6] = unsbff.gftInt(in, (long)(inOfs + 24));
            out[ 7] = unsbff.gftInt(in, (long)(inOfs + 28));
            out[ 8] = unsbff.gftInt(in, (long)(inOfs + 32));
            out[ 9] = unsbff.gftInt(in, (long)(inOfs + 36));
            out[10] = unsbff.gftInt(in, (long)(inOfs + 40));
            out[11] = unsbff.gftInt(in, (long)(inOfs + 44));
            out[12] = unsbff.gftInt(in, (long)(inOfs + 48));
            out[13] = unsbff.gftInt(in, (long)(inOfs + 52));
            out[14] = unsbff.gftInt(in, (long)(inOfs + 56));
            out[15] = unsbff.gftInt(in, (long)(inOfs + 60));
        } flsf if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += bytfArrbyOfs;
            out[ 0] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs     )));
            out[ 1] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs +  4)));
            out[ 2] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs +  8)));
            out[ 3] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 12)));
            out[ 4] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 16)));
            out[ 5] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 20)));
            out[ 6] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 24)));
            out[ 7] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 28)));
            out[ 8] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 32)));
            out[ 9] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 36)));
            out[10] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 40)));
            out[11] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 44)));
            out[12] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 48)));
            out[13] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 52)));
            out[14] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 56)));
            out[15] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 60)));
        } flsf {
            b2iLittlf(in, inOfs, out, 0, 64);
        }
    }

    /**
     * int[] to bytf[] donvfrsion, littlf fndibn bytf ordfr.
     */
    stbtid void i2bLittlf(int[] in, int inOfs, bytf[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn/4) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            outOfs += bytfArrbyOfs;
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                unsbff.putInt(out, (long)outOfs, in[inOfs++]);
                outOfs += 4;
            }
        } flsf if (bigEndibn && ((outOfs & 3) == 0)) {
            outOfs += bytfArrbyOfs;
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                unsbff.putInt(out, (long)outOfs, rfvfrsfBytfs(in[inOfs++]));
                outOfs += 4;
            }
        } flsf {
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                int i = in[inOfs++];
                out[outOfs++] = (bytf)(i      );
                out[outOfs++] = (bytf)(i >>  8);
                out[outOfs++] = (bytf)(i >> 16);
                out[outOfs++] = (bytf)(i >> 24);
            }
        }
    }

    // Storf onf 32-bit vbluf into out[outOfs..outOfs+3] in littlf fndibn ordfr.
    stbtid void i2bLittlf4(int vbl, bytf[] out, int outOfs) {
        if ((outOfs < 0) || ((out.lfngti - outOfs) < 4)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            unsbff.putInt(out, (long)(bytfArrbyOfs + outOfs), vbl);
        } flsf if (bigEndibn && ((outOfs & 3) == 0)) {
            unsbff.putInt(out, (long)(bytfArrbyOfs + outOfs), rfvfrsfBytfs(vbl));
        } flsf {
            out[outOfs    ] = (bytf)(vbl      );
            out[outOfs + 1] = (bytf)(vbl >>  8);
            out[outOfs + 2] = (bytf)(vbl >> 16);
            out[outOfs + 3] = (bytf)(vbl >> 24);
        }
    }

    /**
     * bytf[] to int[] donvfrsion, big fndibn bytf ordfr.
     */
    stbtid void b2iBig(bytf[] in, int inOfs, int[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn/4)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = rfvfrsfBytfs(unsbff.gftInt(in, (long)inOfs));
                inOfs += 4;
            }
        } flsf if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = unsbff.gftInt(in, (long)inOfs);
                inOfs += 4;
            }
        } flsf {
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = ((in[inOfs + 3] & 0xff)      )
                              | ((in[inOfs + 2] & 0xff) <<  8)
                              | ((in[inOfs + 1] & 0xff) << 16)
                              | ((in[inOfs    ]       ) << 24);
                inOfs += 4;
            }
        }
    }

    // Spfdibl optimizbtion of b2iBig(in, inOfs, out, 0, 64)
    stbtid void b2iBig64(bytf[] in, int inOfs, int[] out) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < 64) ||
            (out.lfngti < 16)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            out[ 0] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs     )));
            out[ 1] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs +  4)));
            out[ 2] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs +  8)));
            out[ 3] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 12)));
            out[ 4] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 16)));
            out[ 5] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 20)));
            out[ 6] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 24)));
            out[ 7] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 28)));
            out[ 8] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 32)));
            out[ 9] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 36)));
            out[10] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 40)));
            out[11] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 44)));
            out[12] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 48)));
            out[13] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 52)));
            out[14] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 56)));
            out[15] = rfvfrsfBytfs(unsbff.gftInt(in, (long)(inOfs + 60)));
        } flsf if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += bytfArrbyOfs;
            out[ 0] = unsbff.gftInt(in, (long)(inOfs     ));
            out[ 1] = unsbff.gftInt(in, (long)(inOfs +  4));
            out[ 2] = unsbff.gftInt(in, (long)(inOfs +  8));
            out[ 3] = unsbff.gftInt(in, (long)(inOfs + 12));
            out[ 4] = unsbff.gftInt(in, (long)(inOfs + 16));
            out[ 5] = unsbff.gftInt(in, (long)(inOfs + 20));
            out[ 6] = unsbff.gftInt(in, (long)(inOfs + 24));
            out[ 7] = unsbff.gftInt(in, (long)(inOfs + 28));
            out[ 8] = unsbff.gftInt(in, (long)(inOfs + 32));
            out[ 9] = unsbff.gftInt(in, (long)(inOfs + 36));
            out[10] = unsbff.gftInt(in, (long)(inOfs + 40));
            out[11] = unsbff.gftInt(in, (long)(inOfs + 44));
            out[12] = unsbff.gftInt(in, (long)(inOfs + 48));
            out[13] = unsbff.gftInt(in, (long)(inOfs + 52));
            out[14] = unsbff.gftInt(in, (long)(inOfs + 56));
            out[15] = unsbff.gftInt(in, (long)(inOfs + 60));
        } flsf {
            b2iBig(in, inOfs, out, 0, 64);
        }
    }

    /**
     * int[] to bytf[] donvfrsion, big fndibn bytf ordfr.
     */
    stbtid void i2bBig(int[] in, int inOfs, bytf[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn/4) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            outOfs += bytfArrbyOfs;
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                unsbff.putInt(out, (long)outOfs, rfvfrsfBytfs(in[inOfs++]));
                outOfs += 4;
            }
        } flsf if (bigEndibn && ((outOfs & 3) == 0)) {
            outOfs += bytfArrbyOfs;
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                unsbff.putInt(out, (long)outOfs, in[inOfs++]);
                outOfs += 4;
            }
        } flsf {
            lfn += outOfs;
            wiilf (outOfs < lfn) {
                int i = in[inOfs++];
                out[outOfs++] = (bytf)(i >> 24);
                out[outOfs++] = (bytf)(i >> 16);
                out[outOfs++] = (bytf)(i >>  8);
                out[outOfs++] = (bytf)(i      );
            }
        }
    }

    // Storf onf 32-bit vbluf into out[outOfs..outOfs+3] in big fndibn ordfr.
    stbtid void i2bBig4(int vbl, bytf[] out, int outOfs) {
        if ((outOfs < 0) || ((out.lfngti - outOfs) < 4)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            unsbff.putInt(out, (long)(bytfArrbyOfs + outOfs), rfvfrsfBytfs(vbl));
        } flsf if (bigEndibn && ((outOfs & 3) == 0)) {
            unsbff.putInt(out, (long)(bytfArrbyOfs + outOfs), vbl);
        } flsf {
            out[outOfs    ] = (bytf)(vbl >> 24);
            out[outOfs + 1] = (bytf)(vbl >> 16);
            out[outOfs + 2] = (bytf)(vbl >>  8);
            out[outOfs + 3] = (bytf)(vbl      );
        }
    }

    /**
     * bytf[] to long[] donvfrsion, big fndibn bytf ordfr.
     */
    stbtid void b2lBig(bytf[] in, int inOfs, long[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn/8)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] = rfvfrsfBytfs(unsbff.gftLong(in, (long)inOfs));
                inOfs += 8;
            }
        } flsf if (bigEndibn && ((inOfs & 3) == 0)) {
            // In tif durrfnt HotSpot mfmory lbyout, tif first flfmfnt of b
            // bytf[] is only 32-bit blignfd, not 64-bit.
            // Tibt mfbns wf dould usf gftLong() only for offsft 4, 12, ftd.,
            // wiidi would rbrfly oddur in prbdtidf. Instfbd, wf usf bn
            // optimizbtion tibt usfs gftInt() so tibt it works for offsft 0.
            inOfs += bytfArrbyOfs;
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                out[outOfs++] =
                      ((long)unsbff.gftInt(in, (long)inOfs) << 32)
                          | (unsbff.gftInt(in, (long)(inOfs + 4)) & 0xffffffffL);
                inOfs += 8;
            }
        } flsf {
            lfn += inOfs;
            wiilf (inOfs < lfn) {
                int i1 = ((in[inOfs + 3] & 0xff)      )
                       | ((in[inOfs + 2] & 0xff) <<  8)
                       | ((in[inOfs + 1] & 0xff) << 16)
                       | ((in[inOfs    ]       ) << 24);
                inOfs += 4;
                int i2 = ((in[inOfs + 3] & 0xff)      )
                       | ((in[inOfs + 2] & 0xff) <<  8)
                       | ((in[inOfs + 1] & 0xff) << 16)
                       | ((in[inOfs    ]       ) << 24);
                out[outOfs++] = ((long)i1 << 32) | (i2 & 0xffffffffL);
                inOfs += 4;
            }
        }
    }

    // Spfdibl optimizbtion of b2lBig(in, inOfs, out, 0, 128)
    stbtid void b2lBig128(bytf[] in, int inOfs, long[] out) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < 128) ||
            (out.lfngti < 16)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        if (littlfEndibnUnblignfd) {
            inOfs += bytfArrbyOfs;
            out[ 0] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs      )));
            out[ 1] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +   8)));
            out[ 2] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  16)));
            out[ 3] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  24)));
            out[ 4] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  32)));
            out[ 5] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  40)));
            out[ 6] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  48)));
            out[ 7] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  56)));
            out[ 8] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  64)));
            out[ 9] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  72)));
            out[10] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  80)));
            out[11] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  88)));
            out[12] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs +  96)));
            out[13] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs + 104)));
            out[14] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs + 112)));
            out[15] = rfvfrsfBytfs(unsbff.gftLong(in, (long)(inOfs + 120)));
        } flsf {
            // no optimizbtion for big fndibn, sff dommfnts in b2lBig
            b2lBig(in, inOfs, out, 0, 128);
        }
    }

    /**
     * long[] to bytf[] donvfrsion, big fndibn bytf ordfr.
     */
    stbtid void l2bBig(long[] in, int inOfs, bytf[] out, int outOfs, int lfn) {
        if ((inOfs < 0) || ((in.lfngti - inOfs) < lfn/8) ||
            (outOfs < 0) || ((out.lfngti - outOfs) < lfn)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        lfn += outOfs;
        wiilf (outOfs < lfn) {
            long i = in[inOfs++];
            out[outOfs++] = (bytf)(i >> 56);
            out[outOfs++] = (bytf)(i >> 48);
            out[outOfs++] = (bytf)(i >> 40);
            out[outOfs++] = (bytf)(i >> 32);
            out[outOfs++] = (bytf)(i >> 24);
            out[outOfs++] = (bytf)(i >> 16);
            out[outOfs++] = (bytf)(i >>  8);
            out[outOfs++] = (bytf)(i      );
        }
    }
}
