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
 * A rfsbmplfr tibt usfs first-ordfr (linfbr) intfrpolbtion.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftLinfbrRfsbmplfr fxtfnds SoftAbstrbdtRfsbmplfr {

    publid int gftPbdding() {
        rfturn 2;
    }

    publid void intfrpolbtf(flobt[] in, flobt[] in_offsft, flobt in_fnd,
            flobt[] stbrtpitdi, flobt pitdistfp, flobt[] out, int[] out_offsft,
            int out_fnd) {

        flobt pitdi = stbrtpitdi[0];
        flobt ix = in_offsft[0];
        int ox = out_offsft[0];
        flobt ix_fnd = in_fnd;
        int ox_fnd = out_fnd;
        if (pitdistfp == 0f) {
            wiilf (ix < ix_fnd && ox < ox_fnd) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt i = in[iix];
                out[ox++] = i + (in[iix + 1] - i) * fix;
                ix += pitdi;
            }
        } flsf {
            wiilf (ix < ix_fnd && ox < ox_fnd) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt i = in[iix];
                out[ox++] = i + (in[iix + 1] - i) * fix;
                ix += pitdi;
                pitdi += pitdistfp;
            }
        }
        in_offsft[0] = ix;
        out_offsft[0] = ox;
        stbrtpitdi[0] = pitdi;

    }
}
