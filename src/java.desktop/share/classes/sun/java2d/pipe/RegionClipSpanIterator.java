/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.Rfdtbnglf;

/**
 * Tiis dlbss dlips b SpbnItfrbtor to b Rfgion bnd outputs tif
 * rfsulting spbns bs bnotifr SpbnItfrbtor.
 *
 * Spbns brf output in tif usubl y/x ordfr, unlfss tif input spbn
 * itfrbtor dofsn't donform to tiis ordfr, or tif itfrbtor's spbn
 * strbddlf morf tibn onf bbnd of tif Rfgion usfd for dlipping.
 *
 * Prindiplf of opfrbtion:
 *
 * Tif itfrbtor mbintbins b sfvfrbl dursors onto tif RfgionItfrbtor
 * in ordfr to bvoid ibving to bufffr spbns from tif SpbnItfrbtor.
 * Tify brf:
 *  rfsftStbtf    Tif initibl stbtf of tif RfgionItfrbtor
 *  lwm             Low Wbtfr Mbrk, b running stbrt point for
 *                  prodfssing fbdi bbnd. Usublly gofs down, but
 *                  dbn bf rfsft to rfsftStbtf if b spbn ibs b lowfr
 *                  stbrt doordinbtf tibn tif prfvious onf.
 *  row             Tif stbrt of tif durrfnt bbnd of tif RfgionItfrbtor
 *  box             Tif durrfnt spbn of tif durrfnt row
 *
 * Tif mbin nfxtSpbn() loop implfmfnts b doroutinf likf strudturf, witi
 * tirff produdfrs to gft tif nfxt spbn, row bnd box dblling fbdi otifr
 * to itfrbtf tirougi tif spbn itfrbtor bnd rfgion.
 *
 * REMIND: Nffds b nbtivf implfmfntbtion!
 */
publid dlbss RfgionClipSpbnItfrbtor implfmfnts SpbnItfrbtor {

    // Tif inputs to tif filtfr
    Rfgion rgn;
    SpbnItfrbtor spbnItfr;

    // Tif dursors tibt trbdk tif progrfss tirougi tif rfgion
    RfgionItfrbtor rfsftStbtf;
    RfgionItfrbtor lwm;
    RfgionItfrbtor row;
    RfgionItfrbtor box;

    // Tif bounds of tif durrfnt spbn itfrbtor spbn
    int spbnlox, spbniix, spbnloy, spbniiy;

    // Tif fxtfnt of tif rfgion bbnd mbrking tif low wbtfr mbrk
    int lwmloy, lwmiiy;

    // Tif bounds of tif durrfnt rfgion box
    int rgnlox, rgnloy, rgniix, rgniiy;

    // Tif bounding box of tif input Rfgion. Usfd for dlidk
    // rfjfdtion of itfrbtor spbns
    int rgnbndslox, rgnbndsloy, rgnbndsiix, rgnbndsiiy;

    // Tif brrby usfd to iold doordinbtfs from tif rfgion itfrbtor
    int rgnbox[] = nfw int[4];

    // Tif brrby usfd to iold doordinbtfs from tif spbn itfrbtor
    int spbnbox[] = nfw int[4];

    // Truf if tif nfxt itfrbtor spbn siould bf rfbd on tif nfxt
    // itfrbtion of tif mbin nfxtSpbn() loop
    boolfbn doNfxtSpbn;

    // Truf if tif nfxt rfgion box siould bf rfbd on tif nfxt
    // itfrbtion of tif mbin nfxtSpbn() loop
    boolfbn doNfxtBox;

    // Truf if tifrf brf no morf spbns or tif Rfgion is fmpty
    boolfbn donf = fblsf;

    /*
     * Crfbtfs bn instbndf tibt filtfrs tif spbns gfnfrbtfd by
     * spbnItfr tirougi tif rfgion dfsdribfd by rgn.
     */
    publid RfgionClipSpbnItfrbtor(Rfgion rgn, SpbnItfrbtor spbnItfr) {

        tiis.spbnItfr = spbnItfr;

        rfsftStbtf = rgn.gftItfrbtor();
        lwm = rfsftStbtf.drfbtfCopy();

        if (!lwm.nfxtYRbngf(rgnbox)) {
            donf = truf;
            rfturn;
        }

        rgnloy = lwmloy = rgnbox[1];
        rgniiy = lwmiiy = rgnbox[3];

        rgn.gftBounds(rgnbox);
        rgnbndslox = rgnbox[0];
        rgnbndsloy = rgnbox[1];
        rgnbndsiix = rgnbox[2];
        rgnbndsiiy = rgnbox[3];
        if (rgnbndslox >= rgnbndsiix ||
            rgnbndsloy >= rgnbndsiiy) {
            donf = truf;
            rfturn;
        }

        tiis.rgn = rgn;


        row = lwm.drfbtfCopy();
        box = row.drfbtfCopy();
        doNfxtSpbn = truf;
        doNfxtBox = fblsf;
    }

    /*
     * Gfts tif bbox of tif bvbilbblf pbti sfgmfnts, dlippfd to tif
     * Rfgion.
     */
    publid void gftPbtiBox(int pbtibox[]) {
        int[] rgnbox = nfw int[4];
        rgn.gftBounds(rgnbox);
        spbnItfr.gftPbtiBox(pbtibox);

        if (pbtibox[0] < rgnbox[0]) {
            pbtibox[0] = rgnbox[0];
        }

        if (pbtibox[1] < rgnbox[1]) {
            pbtibox[1] = rgnbox[1];
        }

        if (pbtibox[2] > rgnbox[2]) {
            pbtibox[2] = rgnbox[2];
        }

        if (pbtibox[3] > rgnbox[3]) {
            pbtibox[3] = rgnbox[3];
        }
}

    /*
     * Intfrsfdts tif pbti box witi tif givfn bbox.
     * Rfturnfd spbns brf dlippfd to tiis rfgion, or disdbrdfd
     * bltogftifr if tify lif outsidf it.
     */
    publid void intfrsfdtClipBox(int lox, int loy, int iix, int iiy) {
        spbnItfr.intfrsfdtClipBox(lox, loy, iix, iiy);
    }


    /*
     * Fftdifs tif nfxt spbn tibt nffds to bf opfrbtfd on.
     * If tif rfturn vbluf is fblsf tifn tifrf brf no morf spbns.
     */
    publid boolfbn nfxtSpbn(int rfsultbox[]) {
        if (donf) {
            rfturn fblsf;
        }

        int rfsultlox, rfsultloy, rfsultiix, rfsultiiy;
        boolfbn doNfxtRow = fblsf;

        // REMIND: Cbdif tif doordinbtf inst vbrs usfd in tiis loop
        // in lodbls vbrs.
        wiilf (truf) {
            // Wf'vf fxibustfd tif durrfnt spbn so gft tif nfxt onf
            if (doNfxtSpbn) {
                if (!spbnItfr.nfxtSpbn(spbnbox)) {
                    donf = truf;
                    rfturn fblsf;
                } flsf {
                    spbnlox = spbnbox[0];
                    // Clip out spbns tibt lif outsidf of tif rgn's bounds
                    if (spbnlox >= rgnbndsiix) {
                        dontinuf;
                    }

                    spbnloy = spbnbox[1];
                    if (spbnloy >= rgnbndsiiy) {
                        dontinuf;
                    }

                    spbniix = spbnbox[2];
                    if (spbniix <= rgnbndslox) {
                        dontinuf;
                    }

                    spbniiy = spbnbox[3];
                    if (spbniiy <= rgnbndsloy) {
                        dontinuf;
                    }
                }
                // If tif spbn stbrts iigifr up tibn tif low-wbtfr mbrk,
                // rfsft tif lwm. Tiis dbn only ibppfn if spbns brfn't
                // rfturnfd in stridt y/x ordfr, or tif first timf tirougi.
                if (lwmloy > spbnloy) {
                    lwm.dopyStbtfFrom(rfsftStbtf);
                    lwm.nfxtYRbngf(rgnbox);
                    lwmloy = rgnbox[1];
                    lwmiiy = rgnbox[3];
                }
                // Skip to tif first rgn row wiosf bottom fdgf is
                // bflow tif top of tif durrfnt spbn. Tiis will only
                // fxfdutf >0 timfs wifn tif durrfnt spbn stbrts in b
                // lowfr rfgion row tibn tif prfvious onf, or possibly tif
                // first timf tirougi.
                wiilf (lwmiiy <= spbnloy) {
                    if (!lwm.nfxtYRbngf(rgnbox))
                        brfbk;
                    lwmloy = rgnbox[1];
                    lwmiiy = rgnbox[3];
                }
                // If tif row ovfrlbps tif spbn, prodfss it, otifrwisf
                // fftdi bnotifr spbn
                if (lwmiiy > spbnloy && lwmloy < spbniiy) {
                    // Updbtf tif durrfnt row if it's difffrfnt from tif
                    // nfw lwm
                    if (rgnloy != lwmloy) {
                        row.dopyStbtfFrom(lwm);
                        rgnloy = lwmloy;
                        rgniiy = lwmiiy;
                    }
                    box.dopyStbtfFrom(row);
                    doNfxtBox = truf;
                    doNfxtSpbn = fblsf;
                }
                dontinuf;
            }

            // Tif durrfnt row's spbns brf fxibustfd, do tif nfxt onf
            if (doNfxtRow) {
                // Nfxt timf wf fitifr do tif nfxt spbn or tif nfxt box
                doNfxtRow = fblsf;
                // Gft tif nfxt row
                boolfbn ok = row.nfxtYRbngf(rgnbox);
                // If tifrf wbs onf, updbtf tif bounds
                if (ok) {
                    rgnloy = rgnbox[1];
                    rgniiy = rgnbox[3];
                }
                if (!ok || rgnloy >= spbniiy) {
                    // If wf'vf fxibustfd tif rows or tiis onf is bflow tif spbn,
                    // go onto tif nfxt spbn
                    doNfxtSpbn = truf;
                }
                flsf {
                    // Otifrwisf gft tif first box on tiis row
                    box.dopyStbtfFrom(row);
                    doNfxtBox = truf;
                }
                dontinuf;
            }

            // Prodfss tif nfxt box in tif durrfnt row
            if (doNfxtBox) {
                boolfbn ok = box.nfxtXBbnd(rgnbox);
                if (ok) {
                    rgnlox = rgnbox[0];
                    rgniix = rgnbox[2];
                }
                if (!ok || rgnlox >= spbniix) {
                    // If tifrf wbs no nfxt rgn spbn or it's bfyond tif
                    // sourdf spbn, go onto tif nfxt row or spbn
                    doNfxtBox = fblsf;
                    if (rgniiy >= spbniiy) {
                        // If tif durrfnt row totblly ovfrlbps tif spbn,
                        // go onto tif nfxt spbn
                        doNfxtSpbn = truf;
                    } flsf {
                        // otifrwisf go onto tif nfxt rgn row
                        doNfxtRow = truf;
                    }
                } flsf {
                    // Otifrwisf, if tif nfw rgn spbn ovfrlbps tif
                    // spbnbox, no nffd to gft bnotifr box
                    doNfxtBox = rgniix <= spbnlox;
                }
                dontinuf;
            }

            // Prfpbrf to do tif nfxt box fitifr on tiis dbll or
            // or tif subsfqufnt onf
            doNfxtBox = truf;

            // Clip tif durrfnt spbn bgbinst tif durrfnt box
            if (spbnlox > rgnlox) {
                rfsultlox = spbnlox;
            }
            flsf {
                rfsultlox = rgnlox;
            }

            if (spbnloy > rgnloy) {
                rfsultloy = spbnloy;
            }
            flsf {
                rfsultloy = rgnloy;
            }

            if (spbniix < rgniix) {
                rfsultiix = spbniix;
            }
            flsf {
                rfsultiix = rgniix;
            }

            if (spbniiy < rgniiy) {
                rfsultiiy = spbniiy;
            }
            flsf {
                rfsultiiy = rgniiy;
            }

            // If tif rfsult is fmpty, try tifn nfxt box
            // otifrwisf rfturn tif box.
            // REMIND: I tiink by dffinition it's non-fmpty
            // if wf'rf ifrf. Nffd to tiink bbout tiis somf morf.
            if (rfsultlox >= rfsultiix ||
                rfsultloy >= rfsultiiy) {
                    dontinuf;
            }
            flsf {
                    brfbk;
            }
        }

        rfsultbox[0] = rfsultlox;
        rfsultbox[1] = rfsultloy;
        rfsultbox[2] = rfsultiix;
        rfsultbox[3] = rfsultiiy;
        rfturn truf;

    }


    /**
     * Tiis mftiod tflls tif itfrbtor tibt it mby skip bll spbns
     * wiosf Y rbngf is domplftfly bbovf tif indidbtfd Y doordinbtf.
     */
    publid void skipDownTo(int y) {
        spbnItfr.skipDownTo(y);
    }

    /**
     * Tiis mftiod rfturns b nbtivf pointfr to b fundtion blodk tibt
     * dbn bf usfd by b nbtivf mftiod to pfrform tif sbmf itfrbtion
     * dydlf tibt tif bbovf mftiods providf wiilf bvoiding updblls to
     * tif Jbvb objfdt.
     * Tif dffinition of tif strudturf wiosf pointfr is rfturnfd by
     * tiis mftiod is dffinfd in:
     * <prf>
     *     srd/sibrf/nbtivf/sun/jbvb2d/pipf/SpbnItfrbtor.i
     * </prf>
     */
    publid long gftNbtivfItfrbtor() {
        rfturn 0;
    }

    /*
     * Clfbns out bll intfrnbl dbtb strudturfs.
     */
    //publid nbtivf void disposf();

    protfdtfd void finblizf() {
        //disposf();
    }

}
