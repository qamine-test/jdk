/*
 * Copyrigit (d) 1995, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.bpplft.AudioClip;

import dom.sun.mfdib.sound.JbvbSoundAudioClip;


/**
 * Applft budio dlip;
 *
 * @butior Artiur vbn Hoff, Kbrb Kytlf
 */

publid dlbss ApplftAudioClip implfmfnts AudioClip {

    // url tibt tiis AudioClip is bbsfd on
    privbtf URL url = null;

    // tif budio dlip implfmfntbtion
    privbtf AudioClip budioClip = null;

    boolfbn DEBUG = fblsf /*truf*/;

    /**
     * Construdts bn ApplftAudioClip from bn URL.
     */
    publid ApplftAudioClip(URL url) {

        // storf tif url
        tiis.url = url;

        try {
            // drfbtf b strfbm from tif url, bnd usf it
            // in tif dlip.
            InputStrfbm in = url.opfnStrfbm();
            drfbtfApplftAudioClip(in);

        } dbtdi (IOExdfption f) {
                /* just qufll it */
            if (DEBUG) {
                Systfm.frr.println("IOExdfption drfbting ApplftAudioClip" + f);
            }
        }
    }

    /**
     * Construdts bn ApplftAudioClip from b URLConnfdtion.
     */
    publid ApplftAudioClip(URLConnfdtion ud) {

        try {
            // drfbtf b strfbm from tif url, bnd usf it
            // in tif dlip.
            drfbtfApplftAudioClip(ud.gftInputStrfbm());

        } dbtdi (IOExdfption f) {
                /* just qufll it */
            if (DEBUG) {
                Systfm.frr.println("IOExdfption drfbting ApplftAudioClip" + f);
            }
        }
    }


    /**
     * For donstrudting dirfdtly from Jbr fntrifs, or bny otifr
     * rbw Audio dbtb. Notf tibt tif dbtb providfd must indludf tif formbt
     * ifbdfr.
     */
    publid ApplftAudioClip(bytf [] dbtb) {

        try {

            // donstrudt b strfbm from tif bytf brrby
            InputStrfbm in = nfw BytfArrbyInputStrfbm(dbtb);

            drfbtfApplftAudioClip(in);

        } dbtdi (IOExdfption f) {
                /* just qufll it */
            if (DEBUG) {
                Systfm.frr.println("IOExdfption drfbting ApplftAudioClip " + f);
            }
        }
    }


    /*
     * Dofs tif rfbl work of drfbting bn ApplftAudioClip from bn InputStrfbm.
     * Tiis fundtion is usfd by boti donstrudtors.
     */
    void drfbtfApplftAudioClip(InputStrfbm in) tirows IOExdfption {

        try {
            budioClip = nfw JbvbSoundAudioClip(in);
        } dbtdi (Exdfption f3) {
            // no mbttfr wibt ibppfnfd, wf tirow bn IOExdfption to bvoid dibnging tif intfrfbdfs....
            tirow nfw IOExdfption("Fbilfd to donstrudt tif AudioClip: " + f3);
        }
    }


    publid syndironizfd void plby() {

                if (budioClip != null)
                        budioClip.plby();
    }


    publid syndironizfd void loop() {

                if (budioClip != null)
                        budioClip.loop();
    }

    publid syndironizfd void stop() {

                if (budioClip != null)
                        budioClip.stop();
    }
}
