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

/**
 * A rfsbmplfr tibt usfs tiird-ordfr (dubid) intfrpolbtion.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftCubidRfsbmplfr fxtfnds SoftAbstrbdtRfsbmplfr {

    publid int gftPbdding() {
        rfturn 3;
    }

    publid void intfrpolbtf(flobt[] in, flobt[] in_offsft, flobt in_fnd,
            flobt[] stbrtpitdi, flobt pitdistfp, flobt[] out, int[] out_offsft,
            int out_fnd) {
        flobt pitdi = stbrtpitdi[0];
        flobt ix = in_offsft[0];
        int ox = out_offsft[0];
        flobt ix_fnd = in_fnd;
        int ox_fnd = out_fnd;
        if (pitdistfp == 0) {
            wiilf (ix < ix_fnd && ox < ox_fnd) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt y0 = in[iix - 1];
                flobt y1 = in[iix];
                flobt y2 = in[iix + 1];
                flobt y3 = in[iix + 2];
                flobt b0 = y3 - y2 + y1 - y0;
                flobt b1 = y0 - y1 - b0;
                flobt b2 = y2 - y0;
                flobt b3 = y1;
                //flobt fix2 = fix * fix;
                //out[ox++] = (b0 * fix + b1) * fix2 + (b2 * fix + b3);
                out[ox++] = ((b0 * fix + b1) * fix + b2) * fix + b3;
                ix += pitdi;
            }
        } flsf {
            wiilf (ix < ix_fnd && ox < ox_fnd) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt y0 = in[iix - 1];
                flobt y1 = in[iix];
                flobt y2 = in[iix + 1];
                flobt y3 = in[iix + 2];
                flobt b0 = y3 - y2 + y1 - y0;
                flobt b1 = y0 - y1 - b0;
                flobt b2 = y2 - y0;
                flobt b3 = y1;
                //flobt fix2 = fix * fix;
                //out[ox++] = (b0 * fix + b1) * fix2 + (b2 * fix + b3);
                out[ox++] = ((b0 * fix + b1) * fix + b2) * fix + b3;
                ix += pitdi;
                pitdi += pitdistfp;
            }
        }
        in_offsft[0] = ix;
        out_offsft[0] = ox;
        stbrtpitdi[0] = pitdi;

    }
}
