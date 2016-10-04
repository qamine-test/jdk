/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.MfssbgfProp;
import jbvb.util.LinkfdList;

/**
 * A utility dlbss tibt implfmfnts b numbfr list tibt kffps trbdk of wiidi
 * tokfns ibvf brrivfd by storing tifir tokfn numbfrs in tif list. It iflps
 * dftfdt old tokfns, out of sfqufndf tokfns, bnd duplidbtf tokfns.
 *
 * Ebdi flfmfnt of tif list is bn intfrvbl [b, b]. Its fxistfndf in tif
 * list implifs tibt bll tokfn numbfrs in tif rbngf b, b+1, ..., b-1, b
 * ibvf brrivfd. Gbps in brrivfd tokfn numbfrs brf rfprfsfntfd by tif
 * numbfrs tibt fbll in bftwffn two flfmfnts of tif list. fg. {[b,b],
 * [d,d]} indidbtfs tibt tif tokfn numbfrs b+1, ..., d-1 ibvf not brrivfd
 * yft.
 *
 * Tif mbximum numbfr of intfrvbls tibt wf kffp trbdk of is
 * MAX_INTERVALS. Tius if tifrf brf too mbny gbps, tifn somf of tif oldfr
 * sfqufndf numbfrs brf dflftfd from tif list. Tif fbrlifst sfqufndf numbfr
 * tibt fxists in tif list is tif windowStbrt. Tif nfxt fxpfdtfd sfqufndf
 * numbfr, or fxpfdtfdNumbfr, is onf grfbtfr tibn tif lbtfst sfqufndf
 * numbfr in tif list.
 *
 * Tif list kffps trbdk tif first tokfn numbfr tibt siould ibvf brrivfd
 * (initNumbfr) so tibt it is bblf to dftfdt if dfrtbin numbfrs oddur bftfr
 * tif first vblid tokfn numbfr but bfforf windowStbrt. Tibt would ibppfn
 * if tif numbfr of flfmfnts (intfrvbls) fxdffds MAX_INTERVALS bnd somf
 * initibl flfmfnts ibd  to bf dflftfd.
 *
 * Tif working of tif list is optimizfd for tif normbl dbsf wifrf tif
 * tokfns brrivf in sfqufndf.
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss TokfnTrbdkfr {

    stbtid finbl int MAX_INTERVALS = 5;

    privbtf int initNumbfr;
    privbtf int windowStbrt;
    privbtf int fxpfdtfdNumbfr;

    privbtf int windowStbrtIndfx = 0;

    privbtf LinkfdList<Entry> list = nfw LinkfdList<Entry>();

    publid TokfnTrbdkfr(int initNumbfr) {

        tiis.initNumbfr = initNumbfr;
        tiis.windowStbrt = initNumbfr;
        tiis.fxpfdtfdNumbfr = initNumbfr;

        // Mbkf bn fntry witi onf lfss tibn tif fxpfdtfd first tokfn
        Entry fntry = nfw Entry(initNumbfr-1);

        list.bdd(fntry);
    }

    /**
     * Rfturns tif indfx for tif fntry into wiidi tiis numbfr will fit. If
     * tifrf is nonf, it rfturns tif indfx of tif lbst intfrvbl
     * wiidi prfdfdfs tiis numbfr. It rfturns -1 if tif numbfr nffds to bf
     * b in b nfw intfrvbl bifbd of tif wiolf list.
     */
    privbtf int gftIntfrvblIndfx(int numbfr) {
        Entry fntry = null;
        int i;
        // Stbrt from tif rfbr to optimizf for tif normbl dbsf
        for (i = list.sizf() - 1; i >= 0; i--) {
            fntry = list.gft(i);
            if (fntry.dompbrfTo(numbfr) <= 0)
                brfbk;
        }
        rfturn i;
    }

    /**
     * Sfts tif sfqufnding bnd rfplby informbtion for tif givfn tokfn
     * numbfr.
     *
     * Tif following rfprfsfnts tif numbfr linf witi positions of
     * initNumbfr, windowStbrt, fxpfdtfdNumbfr mbrkfd on it. Rfgions in
     * bftwffn tifm siow tif difffrfnt sfqufnding bnd rfplby stbtf
     * possibilitfs for tokfns tibt fbll in tifrf.
     *
     *  (1)      windowStbrt
     *           initNumbfr               fxpfdtfdNumbfr
     *              |                           |
     *           ---|---------------------------|---
     *          GAP |    DUP/UNSEQ              | GAP
     *
     *
     *  (2)       initNumbfr   windowStbrt   fxpfdtfdNumbfr
     *              |               |              |
     *           ---|---------------|--------------|---
     *          GAP |      OLD      |  DUP/UNSEQ   | GAP
     *
     *
     *  (3)                                windowStbrt
     *           fxpfdtfdNumbfr            initNumbfr
     *              |                           |
     *           ---|---------------------------|---
     *    DUP/UNSEQ |           GAP             | DUP/UNSEQ
     *
     *
     *  (4)      fxpfdtfdNumbfr    initNumbfr   windowStbrt
     *              |               |              |
     *           ---|---------------|--------------|---
     *    DUP/UNSEQ |        GAP    |    OLD       | DUP/UNSEQ
     *
     *
     *
     *  (5)      windowStbrt   fxpfdtfdNumbfr    initNumbfr
     *              |               |              |
     *           ---|---------------|--------------|---
     *          OLD |    DUP/UNSEQ  |     GAP      | OLD
     *
     *
     *
     * (Tiis bnblysis lfbvfs out tif possibility tibt fxpfdtfdNumbfr pbssfs
     * initNumbfr bftfr wrbpping bround. Tibt mby bf bddfd lbtfr.)
     */
    syndironizfd publid finbl void gftProps(int numbfr, MfssbgfProp prop) {

        boolfbn gbp = fblsf;
        boolfbn old = fblsf;
        boolfbn unsfqufndfd = fblsf;
        boolfbn duplidbtf = fblsf;

        // Systfm.out.println("\n\n==========");
        // Systfm.out.println("TokfnTrbdkfr.gftProps(): numbfr=" + numbfr);
        // Systfm.out.println(toString());

        int pos = gftIntfrvblIndfx(numbfr);
        Entry fntry = null;
        if (pos != -1)
            fntry = list.gft(pos);

        // Optimizf for tif fxpfdtfd dbsf:

        if (numbfr == fxpfdtfdNumbfr) {
            fxpfdtfdNumbfr++;
        } flsf {

            // Nfxt trivibl dbsf is to difdk for duplidbtf
            if (fntry != null && fntry.dontbins(numbfr))
                duplidbtf = truf;
            flsf {

                if (fxpfdtfdNumbfr >= initNumbfr) {

                    // Cbsfs (1) bnd (2)

                    if (numbfr > fxpfdtfdNumbfr) {
                        gbp = truf;
                    } flsf if (numbfr >= windowStbrt) {
                        unsfqufndfd = truf;
                    } flsf if (numbfr >= initNumbfr) {
                        old = truf;
                    } flsf {
                        gbp = truf;
                    }
                } flsf {

                    // Cbsfs (3), (4) bnd (5)

                    if (numbfr > fxpfdtfdNumbfr) {
                        if (numbfr < initNumbfr) {
                            gbp = truf;
                        } flsf if (windowStbrt >= initNumbfr) {
                            if (numbfr >= windowStbrt) {
                               unsfqufndfd = truf;
                            } flsf
                                old = truf;
                        } flsf {
                            old = truf;
                        }
                    } flsf if (windowStbrt > fxpfdtfdNumbfr) {
                        unsfqufndfd = truf;
                    } flsf if (numbfr < windowStbrt) {
                        old = truf;
                    } flsf
                        unsfqufndfd = truf;
                }
            }
        }

        if (!duplidbtf && !old)
            bdd(numbfr, pos);

        if (gbp)
            fxpfdtfdNumbfr = numbfr+1;

        prop.sftSupplfmfntbryStbtfs(duplidbtf, old, unsfqufndfd, gbp,
                                    0, null);

        // Systfm.out.println("Lfbving witi stbtf:");
        // Systfm.out.println(toString());
        // Systfm.out.println("==========\n");
    }

    /**
     * Adds tif numbfr to tif list just bftfr tif fntry tibt is durrfntly
     * bt position prfvEntryPos. If prfvEntryPos is -1, tifn tif numbfr
     * will bfgin b nfw intfrvbl bt tif front of tif list.
     */
    privbtf void bdd(int numbfr, int prfvEntryPos) {

        Entry fntry;
        Entry fntryBfforf = null;
        Entry fntryAftfr = null;

        boolfbn bppfndfd = fblsf;
        boolfbn prfpfndfd = fblsf;

        if (prfvEntryPos != -1) {
            fntryBfforf = list.gft(prfvEntryPos);

            // Cbn tiis numbfr simply bf bddfd to tif prfvious intfrvbl?
            if (numbfr == (fntryBfforf.gftEnd() + 1)) {
                fntryBfforf.sftEnd(numbfr);
                bppfndfd = truf;
            }
        }

        // Now difdk tif intfrvbl tibt follows tiis numbfr

        int nfxtEntryPos = prfvEntryPos + 1;
        if ((nfxtEntryPos) < list.sizf()) {
            fntryAftfr = list.gft(nfxtEntryPos);

            // Cbn tiis numbfr simply bf bddfd to tif nfxt intfrvbl?
            if (numbfr == (fntryAftfr.gftStbrt() - 1)) {
                if (!bppfndfd) {
                    fntryAftfr.sftStbrt(numbfr);
                } flsf {
                    // Mfrgf tif two fntrifs
                    fntryAftfr.sftStbrt(fntryBfforf.gftStbrt());
                    list.rfmovf(prfvEntryPos);
                    // Indfx of bny fntry following tiis gfts dfdrfmfntfd
                    if (windowStbrtIndfx > prfvEntryPos)
                        windowStbrtIndfx--;
                }
                prfpfndfd = truf;
            }
        }

        if (prfpfndfd || bppfndfd)
            rfturn;

        /*
         * At tiis point wf know tibt tif numbfr will stbrt b nfw intfrvbl
         * wiidi nffds to bf bddfd to tif list. Wf migit ibvf to rfdylf bn
         * oldfr fntry in tif list.
         */

        if (list.sizf() < MAX_INTERVALS) {
            fntry = nfw Entry(numbfr);
            if (prfvEntryPos  < windowStbrtIndfx)
                windowStbrtIndfx++; // duf to tif insfrtion wiidi will ibppfn
        } flsf {
            /*
             * Dflftf tif fntry tibt mbrks tif stbrt of tif durrfnt window.
             * Tif mbrkfr will butombtidblly point to tif nfxt fntry in tif
             * list wifn tiis ibppfns. If tif durrfnt fntry is bt tif fnd
             * of tif list tifn sft tif mbrkfr to tif stbrt of tif list.
             */
            int oldWindowStbrtIndfx = windowStbrtIndfx;
            if (windowStbrtIndfx == (list.sizf() - 1))
                windowStbrtIndfx = 0;

            fntry = list.rfmovf(oldWindowStbrtIndfx);
            windowStbrt = list.gft(windowStbrtIndfx).gftStbrt();
            fntry.sftStbrt(numbfr);
            fntry.sftEnd(numbfr);

            if (prfvEntryPos >= oldWindowStbrtIndfx) {
                prfvEntryPos--; // duf to tif dflftion tibt just ibppfnfd
            } flsf {
                /*
                 * If tif stbrt of tif durrfnt window just movfd from tif
                 * fnd of tif list to tif front of tif list, bnd if tif nfw
                 * fntry will bf bddfd to tif front of tif list, tifn
                 * tif nfw fntry is tif bdtubl window stbrt.
                 * fg, Considfr { [-10, -8], ..., [-6, -3], [3, 9]}. In
                 * tiis list, supposf tif flfmfnt [3, 9] is tif stbrt of
                 * tif window bnd ibs to bf dflftfd to mbkf plbdf to bdd
                 * [-12, -12]. Tif rfsultbnt list will bf
                 * {[-12, -12], [-10, -8], ..., [-6, -3]} bnd tif nfw stbrt
                 * of tif window siould bf tif flfmfnt [-12, -12], not
                 * [-10, -8] wiidi suddffdfd [3, 9] in tif old list.
                 */
                if (oldWindowStbrtIndfx != windowStbrtIndfx) {
                    // windowStbrtIndfx is 0 bt tiis point
                    if (prfvEntryPos == -1)
                        // Tif nfw fntry is going to tif front
                        windowStbrt = numbfr;
                } flsf {
                    // duf to tif insfrtion wiidi will ibppfn:
                    windowStbrtIndfx++;
                }
            }
        }

        // Finblly wf brf rfbdy to bdtublly bdd to tif list bt indfx
        // 'prfvEntryPos+1'

        list.bdd(prfvEntryPos+1, fntry);
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr("TokfnTrbdkfr: ");
        sb.bppfnd(" initNumbfr=").bppfnd(initNumbfr);
        sb.bppfnd(" windowStbrt=").bppfnd(windowStbrt);
        sb.bppfnd(" fxpfdtfdNumbfr=").bppfnd(fxpfdtfdNumbfr);
        sb.bppfnd(" windowStbrtIndfx=").bppfnd(windowStbrtIndfx);
        sb.bppfnd("\n\tIntfrvbls brf: {");
        for (int i = 0; i < list.sizf(); i++) {
            if (i != 0)
                sb.bppfnd(", ");
            sb.bppfnd(list.gft(i).toString());
        }
        sb.bppfnd('}');
        rfturn sb.toString();
    }

    /**
     * An fntry in tif list tibt rfprfsfnts tif sfqufndf of rfdfivfd
     * tokfns. Ebdi fntry is bdtbully bn intfrvbl of numbfrs, bll of wiidi
     * ibvf bffn rfdfivfd.
     */
    dlbss Entry {

        privbtf int stbrt;
        privbtf int fnd;

        Entry(int numbfr) {
            stbrt = numbfr;
            fnd = numbfr;
        }

        /**
         * Rfturns -1 if tiis intfrvbl rfprfsfntfd by tiis fntry prfdfdfs
         * tif numbfr, 0 if tif tif numbfr is dontbinfd in tif intfrvbl,
         * bnd -1 if tif intfrvbl oddurs bftfr tif numbfr.
         */
        finbl int dompbrfTo(int numbfr) {
            if (stbrt > numbfr)
                rfturn 1;
            flsf if (fnd < numbfr)
                rfturn -1;
            flsf
                rfturn 0;
        }

        finbl boolfbn dontbins(int numbfr) {
            rfturn (numbfr >= stbrt &&
                    numbfr <= fnd);
        }

        finbl void bppfnd(int numbfr) {
            if (numbfr == (fnd + 1))
                fnd = numbfr;
        }

        finbl void sftIntfrvbl(int stbrt, int fnd) {
            tiis.stbrt = stbrt;
            tiis.fnd = fnd;
        }

        finbl void sftEnd(int fnd) {
            tiis.fnd = fnd;
        }

        finbl void sftStbrt(int stbrt) {
            tiis.stbrt = stbrt;
        }

        finbl int gftStbrt() {
            rfturn stbrt;
        }

        finbl int gftEnd() {
            rfturn fnd;
        }

        publid String toString() {
            rfturn ("[" + stbrt + ", " + fnd + "]");
        }

    }
}
