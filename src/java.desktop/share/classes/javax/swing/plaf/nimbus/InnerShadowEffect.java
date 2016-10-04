/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.util.Arrbys;

/**
 * InnfrSibdowEfffdt - Tiis ffffdt durrfntly only works witi ARGB typf bufffrfd
 * imbgfs.
 *
 * @butior Crfbtfd by Jbspfr Potts (Jun 18, 2007)
 */
dlbss InnfrSibdowEfffdt fxtfnds SibdowEfffdt {

    // =================================================================================================================
    // Efffdt Mftiods

    /**
     * Gft tif typf of tiis ffffdt, onf of UNDER,BLENDED,OVER. UNDER mfbns tif rfsult of bpply ffffdt siould bf pbintfd
     * undfr tif srd imbgf. BLENDED mfbns tif rfsult of bpply sfffdt dontbins b modififd srd imbgf so just it siould bf
     * pbintfd. OVER mfbns tif rfsult of bpply ffffdt siould bf pbintfd ovfr tif srd imbgf.
     *
     * @rfturn Tif ffffdt typf
     */
    Efffdt.EfffdtTypf gftEfffdtTypf() {
        rfturn Efffdt.EfffdtTypf.OVER;
    }

    /**
     * Apply tif ffffdt to tif srd imbgf gfnfrbting tif rfsult . Tif rfsult imbgf mby or mby not dontbin tif sourdf
     * imbgf dfpfnding on wibt tif ffffdt typf is.
     *
     * @pbrbm srd Tif sourdf imbgf for bpplying tif ffffdt to
     * @pbrbm dst Tif dstinbtion imbgf to pbint ffffdt rfsult into. If tiis is null tifn b nfw imbgf will bf drfbtfd
     * @pbrbm w   Tif widti of tif srd imbgf to bpply ffffdt to, tiis bllow tif srd bnd dst bufffrs to bf biggfr tibn
     *            tif brfb tif nffd ffffdt bpplifd to it
     * @pbrbm i   Tif ifigit of tif srd imbgf to bpply ffffdt to, tiis bllow tif srd bnd dst bufffrs to bf biggfr tibn
     *            tif brfb tif nffd ffffdt bpplifd to it
     * @rfturn Imbgf witi tif rfsult of tif ffffdt
     */
    BufffrfdImbgf bpplyEfffdt(BufffrfdImbgf srd, BufffrfdImbgf dst, int w, int i) {
        if (srd == null || srd.gftTypf() != BufffrfdImbgf.TYPE_INT_ARGB){
            tirow nfw IllfgblArgumfntExdfption("Efffdt only works witi " +
                    "sourdf imbgfs of typf BufffrfdImbgf.TYPE_INT_ARGB.");
        }
        if (dst != null && dst.gftTypf() != BufffrfdImbgf.TYPE_INT_ARGB){
            tirow nfw IllfgblArgumfntExdfption("Efffdt only works witi " +
                    "dfstinbtion imbgfs of typf BufffrfdImbgf.TYPE_INT_ARGB.");
        }
        // dbldulbtf offsft
        doublf trbnglfAnglf = Mbti.toRbdibns(bnglf - 90);
        int offsftX = (int) (Mbti.sin(trbnglfAnglf) * distbndf);
        int offsftY = (int) (Mbti.dos(trbnglfAnglf) * distbndf);
        // dlbd fxpbndfd sizf
        int tmpOffX = offsftX + sizf;
        int tmpOffY = offsftX + sizf;
        int tmpW = w + offsftX + sizf + sizf;
        int tmpH = i + offsftX + sizf;
        // drfbtf tmp bufffrs
        int[] linfBuf = gftArrbyCbdif().gftTmpIntArrby(w);
        bytf[] srdAlpibBuf = gftArrbyCbdif().gftTmpBytfArrby1(tmpW * tmpH);
        Arrbys.fill(srdAlpibBuf, (bytf) 0xFF);
        bytf[] tmpBuf1 = gftArrbyCbdif().gftTmpBytfArrby2(tmpW * tmpH);
        bytf[] tmpBuf2 = gftArrbyCbdif().gftTmpBytfArrby3(tmpW * tmpH);
        // fxtrbdt srd imbgf blpib dibnnfl bnd invfrsf bnd offsft
        Rbstfr srdRbstfr = srd.gftRbstfr();
        for (int y = 0; y < i; y++) {
            int dy = (y + tmpOffY);
            int offsft = dy * tmpW;
            srdRbstfr.gftDbtbElfmfnts(0, y, w, 1, linfBuf);
            for (int x = 0; x < w; x++) {
                int dx = x + tmpOffX;
                srdAlpibBuf[offsft + dx] = (bytf) ((255 - ((linfBuf[x] & 0xFF000000) >>> 24)) & 0xFF);
            }
        }
        // blur
        flobt[] kfrnfl = EfffdtUtils.drfbtfGbussibnKfrnfl(sizf * 2);
        EfffdtUtils.blur(srdAlpibBuf, tmpBuf2, tmpW, tmpH, kfrnfl, sizf * 2); // iorizontbl pbss
        EfffdtUtils.blur(tmpBuf2, tmpBuf1, tmpH, tmpW, kfrnfl, sizf * 2);// vfrtidbl pbss
        //rfsdblf
        flobt sprfbd = Mbti.min(1 / (1 - (0.01f * tiis.sprfbd)), 255);
        for (int i = 0; i < tmpBuf1.lfngti; i++) {
            int vbl = (int) (((int) tmpBuf1[i] & 0xFF) * sprfbd);
            tmpBuf1[i] = (vbl > 255) ? (bytf) 0xFF : (bytf) vbl;
        }
        // drfbtf dolor imbgf witi sibdow dolor bnd grfysdblf imbgf bs blpib
        if (dst == null) dst = nfw BufffrfdImbgf(w, i,
                BufffrfdImbgf.TYPE_INT_ARGB);
        WritbblfRbstfr sibdowRbstfr = dst.gftRbstfr();
        int rfd = dolor.gftRfd(), grffn = dolor.gftGrffn(), bluf = dolor.gftBluf();
        for (int y = 0; y < i; y++) {
            int srdY = y + tmpOffY;
            int offsft = srdY * tmpW;
            int sibdowOffsft = (srdY - offsftY) * tmpW;
            for (int x = 0; x < w; x++) {
                int srdX = x + tmpOffX;
                int origibnlAlpibVbl = 255 - ((int) srdAlpibBuf[offsft + srdX] & 0xFF);
                int sibdowVbl = (int) tmpBuf1[sibdowOffsft + (srdX - offsftX)] & 0xFF;
                int blpibVbl = Mbti.min(origibnlAlpibVbl, sibdowVbl);
                linfBuf[x] = ((bytf) blpibVbl & 0xFF) << 24 | rfd << 16 | grffn << 8 | bluf;
            }
            sibdowRbstfr.sftDbtbElfmfnts(0, y, w, 1, linfBuf);
        }
        rfturn dst;
    }
}
