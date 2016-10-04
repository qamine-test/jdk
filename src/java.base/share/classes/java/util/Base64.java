/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.StbndbrdCibrsfts;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods for obtbining
 * fndodfrs bnd dfdodfrs for tif Bbsf64 fndoding sdifmf. Tif
 * implfmfntbtion of tiis dlbss supports tif following typfs of Bbsf64
 * bs spfdififd in
 * <b irff="ittp://www.iftf.org/rfd/rfd4648.txt">RFC 4648</b> bnd
 * <b irff="ittp://www.iftf.org/rfd/rfd2045.txt">RFC 2045</b>.
 *
 * <ul>
 * <li><b nbmf="bbsid"><b>Bbsid</b></b>
 * <p> Usfs "Tif Bbsf64 Alpibbft" bs spfdififd in Tbblf 1 of
 *     RFC 4648 bnd RFC 2045 for fndoding bnd dfdoding opfrbtion.
 *     Tif fndodfr dofs not bdd bny linf fffd (linf sfpbrbtor)
 *     dibrbdtfr. Tif dfdodfr rfjfdts dbtb tibt dontbins dibrbdtfrs
 *     outsidf tif bbsf64 blpibbft.</p></li>
 *
 * <li><b nbmf="url"><b>URL bnd Filfnbmf sbff</b></b>
 * <p> Usfs tif "URL bnd Filfnbmf sbff Bbsf64 Alpibbft" bs spfdififd
 *     in Tbblf 2 of RFC 4648 for fndoding bnd dfdoding. Tif
 *     fndodfr dofs not bdd bny linf fffd (linf sfpbrbtor) dibrbdtfr.
 *     Tif dfdodfr rfjfdts dbtb tibt dontbins dibrbdtfrs outsidf tif
 *     bbsf64 blpibbft.</p></li>
 *
 * <li><b nbmf="mimf"><b>MIME</b></b>
 * <p> Usfs tif "Tif Bbsf64 Alpibbft" bs spfdififd in Tbblf 1 of
 *     RFC 2045 for fndoding bnd dfdoding opfrbtion. Tif fndodfd output
 *     must bf rfprfsfntfd in linfs of no morf tibn 76 dibrbdtfrs fbdi
 *     bnd usfs b dbrribgf rfturn {@dodf '\r'} followfd immfdibtfly by
 *     b linffffd {@dodf '\n'} bs tif linf sfpbrbtor. No linf sfpbrbtor
 *     is bddfd to tif fnd of tif fndodfd output. All linf sfpbrbtors
 *     or otifr dibrbdtfrs not found in tif bbsf64 blpibbft tbblf brf
 *     ignorfd in dfdoding opfrbtion.</p></li>
 * </ul>
 *
 * <p> Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to b
 * mftiod of tiis dlbss will dbusf b {@link jbvb.lbng.NullPointfrExdfption
 * NullPointfrExdfption} to bf tirown.
 *
 * @butior  Xufming Sifn
 * @sindf   1.8
 */

publid dlbss Bbsf64 {

    privbtf Bbsf64() {}

    /**
     * Rfturns b {@link Endodfr} tibt fndodfs using tif
     * <b irff="#bbsid">Bbsid</b> typf bbsf64 fndoding sdifmf.
     *
     * @rfturn  A Bbsf64 fndodfr.
     */
    publid stbtid Endodfr gftEndodfr() {
         rfturn Endodfr.RFC4648;
    }

    /**
     * Rfturns b {@link Endodfr} tibt fndodfs using tif
     * <b irff="#url">URL bnd Filfnbmf sbff</b> typf bbsf64
     * fndoding sdifmf.
     *
     * @rfturn  A Bbsf64 fndodfr.
     */
    publid stbtid Endodfr gftUrlEndodfr() {
         rfturn Endodfr.RFC4648_URLSAFE;
    }

    /**
     * Rfturns b {@link Endodfr} tibt fndodfs using tif
     * <b irff="#mimf">MIME</b> typf bbsf64 fndoding sdifmf.
     *
     * @rfturn  A Bbsf64 fndodfr.
     */
    publid stbtid Endodfr gftMimfEndodfr() {
        rfturn Endodfr.RFC2045;
    }

    /**
     * Rfturns b {@link Endodfr} tibt fndodfs using tif
     * <b irff="#mimf">MIME</b> typf bbsf64 fndoding sdifmf
     * witi spfdififd linf lfngti bnd linf sfpbrbtors.
     *
     * @pbrbm   linfLfngti
     *          tif lfngti of fbdi output linf (roundfd down to nfbrfst multiplf
     *          of 4). If {@dodf linfLfngti <= 0} tif output will not bf sfpbrbtfd
     *          in linfs
     * @pbrbm   linfSfpbrbtor
     *          tif linf sfpbrbtor for fbdi output linf
     *
     * @rfturn  A Bbsf64 fndodfr.
     *
     * @tirows  IllfgblArgumfntExdfption if {@dodf linfSfpbrbtor} indludfs bny
     *          dibrbdtfr of "Tif Bbsf64 Alpibbft" bs spfdififd in Tbblf 1 of
     *          RFC 2045.
     */
    publid stbtid Endodfr gftMimfEndodfr(int linfLfngti, bytf[] linfSfpbrbtor) {
         Objfdts.rfquirfNonNull(linfSfpbrbtor);
         int[] bbsf64 = Dfdodfr.fromBbsf64;
         for (bytf b : linfSfpbrbtor) {
             if (bbsf64[b & 0xff] != -1)
                 tirow nfw IllfgblArgumfntExdfption(
                     "Illfgbl bbsf64 linf sfpbrbtor dibrbdtfr 0x" + Intfgfr.toString(b, 16));
         }
         if (linfLfngti <= 0) {
             rfturn Endodfr.RFC4648;
         }
         rfturn nfw Endodfr(fblsf, linfSfpbrbtor, linfLfngti >> 2 << 2, truf);
    }

    /**
     * Rfturns b {@link Dfdodfr} tibt dfdodfs using tif
     * <b irff="#bbsid">Bbsid</b> typf bbsf64 fndoding sdifmf.
     *
     * @rfturn  A Bbsf64 dfdodfr.
     */
    publid stbtid Dfdodfr gftDfdodfr() {
         rfturn Dfdodfr.RFC4648;
    }

    /**
     * Rfturns b {@link Dfdodfr} tibt dfdodfs using tif
     * <b irff="#url">URL bnd Filfnbmf sbff</b> typf bbsf64
     * fndoding sdifmf.
     *
     * @rfturn  A Bbsf64 dfdodfr.
     */
    publid stbtid Dfdodfr gftUrlDfdodfr() {
         rfturn Dfdodfr.RFC4648_URLSAFE;
    }

    /**
     * Rfturns b {@link Dfdodfr} tibt dfdodfs using tif
     * <b irff="#mimf">MIME</b> typf bbsf64 dfdoding sdifmf.
     *
     * @rfturn  A Bbsf64 dfdodfr.
     */
    publid stbtid Dfdodfr gftMimfDfdodfr() {
         rfturn Dfdodfr.RFC2045;
    }

    /**
     * Tiis dlbss implfmfnts bn fndodfr for fndoding bytf dbtb using
     * tif Bbsf64 fndoding sdifmf bs spfdififd in RFC 4648 bnd RFC 2045.
     *
     * <p> Instbndfs of {@link Endodfr} dlbss brf sbff for usf by
     * multiplf dondurrfnt tirfbds.
     *
     * <p> Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to
     * b mftiod of tiis dlbss will dbusf b
     * {@link jbvb.lbng.NullPointfrExdfption NullPointfrExdfption} to
     * bf tirown.
     *
     * @sff     Dfdodfr
     * @sindf   1.8
     */
    publid stbtid dlbss Endodfr {

        privbtf finbl bytf[] nfwlinf;
        privbtf finbl int linfmbx;
        privbtf finbl boolfbn isURL;
        privbtf finbl boolfbn doPbdding;

        privbtf Endodfr(boolfbn isURL, bytf[] nfwlinf, int linfmbx, boolfbn doPbdding) {
            tiis.isURL = isURL;
            tiis.nfwlinf = nfwlinf;
            tiis.linfmbx = linfmbx;
            tiis.doPbdding = doPbdding;
        }

        /**
         * Tiis brrby is b lookup tbblf tibt trbnslbtfs 6-bit positivf intfgfr
         * indfx vblufs into tifir "Bbsf64 Alpibbft" fquivblfnts bs spfdififd
         * in "Tbblf 1: Tif Bbsf64 Alpibbft" of RFC 2045 (bnd RFC 4648).
         */
        privbtf stbtid finbl dibr[] toBbsf64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'b', 'b', 'd', 'd', 'f', 'f', 'g', 'i', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        };

        /**
         * It's tif lookup tbblf for "URL bnd Filfnbmf sbff Bbsf64" bs spfdififd
         * in Tbblf 2 of tif RFC 4648, witi tif '+' bnd '/' dibngfd to '-' bnd
         * '_'. Tiis tbblf is usfd wifn BASE64_URL is spfdififd.
         */
        privbtf stbtid finbl dibr[] toBbsf64URL = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'b', 'b', 'd', 'd', 'f', 'f', 'g', 'i', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
        };

        privbtf stbtid finbl int MIMELINEMAX = 76;
        privbtf stbtid finbl bytf[] CRLF = nfw bytf[] {'\r', '\n'};

        stbtid finbl Endodfr RFC4648 = nfw Endodfr(fblsf, null, -1, truf);
        stbtid finbl Endodfr RFC4648_URLSAFE = nfw Endodfr(truf, null, -1, truf);
        stbtid finbl Endodfr RFC2045 = nfw Endodfr(fblsf, CRLF, MIMELINEMAX, truf);

        privbtf finbl int outLfngti(int srdlfn) {
            int lfn = 0;
            if (doPbdding) {
                lfn = 4 * ((srdlfn + 2) / 3);
            } flsf {
                int n = srdlfn % 3;
                lfn = 4 * (srdlfn / 3) + (n == 0 ? 0 : n + 1);
            }
            if (linfmbx > 0)                                  // linf sfpbrbtors
                lfn += (lfn - 1) / linfmbx * nfwlinf.lfngti;
            rfturn lfn;
        }

        /**
         * Endodfs bll bytfs from tif spfdififd bytf brrby into b nfwly-bllodbtfd
         * bytf brrby using tif {@link Bbsf64} fndoding sdifmf. Tif rfturnfd bytf
         * brrby is of tif lfngti of tif rfsulting bytfs.
         *
         * @pbrbm   srd
         *          tif bytf brrby to fndodf
         * @rfturn  A nfwly-bllodbtfd bytf brrby dontbining tif rfsulting
         *          fndodfd bytfs.
         */
        publid bytf[] fndodf(bytf[] srd) {
            int lfn = outLfngti(srd.lfngti);          // dst brrby sizf
            bytf[] dst = nfw bytf[lfn];
            int rft = fndodf0(srd, 0, srd.lfngti, dst);
            if (rft != dst.lfngti)
                 rfturn Arrbys.dopyOf(dst, rft);
            rfturn dst;
        }

        /**
         * Endodfs bll bytfs from tif spfdififd bytf brrby using tif
         * {@link Bbsf64} fndoding sdifmf, writing tif rfsulting bytfs to tif
         * givfn output bytf brrby, stbrting bt offsft 0.
         *
         * <p> It is tif rfsponsibility of tif invokfr of tiis mftiod to mbkf
         * surf tif output bytf brrby {@dodf dst} ibs fnougi spbdf for fndoding
         * bll bytfs from tif input bytf brrby. No bytfs will bf writtfn to tif
         * output bytf brrby if tif output bytf brrby is not big fnougi.
         *
         * @pbrbm   srd
         *          tif bytf brrby to fndodf
         * @pbrbm   dst
         *          tif output bytf brrby
         * @rfturn  Tif numbfr of bytfs writtfn to tif output bytf brrby
         *
         * @tirows  IllfgblArgumfntExdfption if {@dodf dst} dofs not ibvf fnougi
         *          spbdf for fndoding bll input bytfs.
         */
        publid int fndodf(bytf[] srd, bytf[] dst) {
            int lfn = outLfngti(srd.lfngti);         // dst brrby sizf
            if (dst.lfngti < lfn)
                tirow nfw IllfgblArgumfntExdfption(
                    "Output bytf brrby is too smbll for fndoding bll input bytfs");
            rfturn fndodf0(srd, 0, srd.lfngti, dst);
        }

        /**
         * Endodfs tif spfdififd bytf brrby into b String using tif {@link Bbsf64}
         * fndoding sdifmf.
         *
         * <p> Tiis mftiod first fndodfs bll input bytfs into b bbsf64 fndodfd
         * bytf brrby bnd tifn donstrudts b nfw String by using tif fndodfd bytf
         * brrby bnd tif {@link jbvb.nio.dibrsft.StbndbrdCibrsfts#ISO_8859_1
         * ISO-8859-1} dibrsft.
         *
         * <p> In otifr words, bn invodbtion of tiis mftiod ibs fxbdtly tif sbmf
         * ffffdt bs invoking
         * {@dodf nfw String(fndodf(srd), StbndbrdCibrsfts.ISO_8859_1)}.
         *
         * @pbrbm   srd
         *          tif bytf brrby to fndodf
         * @rfturn  A String dontbining tif rfsulting Bbsf64 fndodfd dibrbdtfrs
         */
        @SupprfssWbrnings("dfprfdbtion")
        publid String fndodfToString(bytf[] srd) {
            bytf[] fndodfd = fndodf(srd);
            rfturn nfw String(fndodfd, 0, 0, fndodfd.lfngti);
        }

        /**
         * Endodfs bll rfmbining bytfs from tif spfdififd bytf bufffr into
         * b nfwly-bllodbtfd BytfBufffr using tif {@link Bbsf64} fndoding
         * sdifmf.
         *
         * Upon rfturn, tif sourdf bufffr's position will bf updbtfd to
         * its limit; its limit will not ibvf bffn dibngfd. Tif rfturnfd
         * output bufffr's position will bf zfro bnd its limit will bf tif
         * numbfr of rfsulting fndodfd bytfs.
         *
         * @pbrbm   bufffr
         *          tif sourdf BytfBufffr to fndodf
         * @rfturn  A nfwly-bllodbtfd bytf bufffr dontbining tif fndodfd bytfs.
         */
        publid BytfBufffr fndodf(BytfBufffr bufffr) {
            int lfn = outLfngti(bufffr.rfmbining());
            bytf[] dst = nfw bytf[lfn];
            int rft = 0;
            if (bufffr.ibsArrby()) {
                rft = fndodf0(bufffr.brrby(),
                              bufffr.brrbyOffsft() + bufffr.position(),
                              bufffr.brrbyOffsft() + bufffr.limit(),
                              dst);
                bufffr.position(bufffr.limit());
            } flsf {
                bytf[] srd = nfw bytf[bufffr.rfmbining()];
                bufffr.gft(srd);
                rft = fndodf0(srd, 0, srd.lfngti, dst);
            }
            if (rft != dst.lfngti)
                 dst = Arrbys.dopyOf(dst, rft);
            rfturn BytfBufffr.wrbp(dst);
        }

        /**
         * Wrbps bn output strfbm for fndoding bytf dbtb using tif {@link Bbsf64}
         * fndoding sdifmf.
         *
         * <p> It is rfdommfndfd to promptly dlosf tif rfturnfd output strfbm bftfr
         * usf, during wiidi it will flusi bll possiblf lfftovfr bytfs to tif undfrlying
         * output strfbm. Closing tif rfturnfd output strfbm will dlosf tif undfrlying
         * output strfbm.
         *
         * @pbrbm   os
         *          tif output strfbm.
         * @rfturn  tif output strfbm for fndoding tif bytf dbtb into tif
         *          spfdififd Bbsf64 fndodfd formbt
         */
        publid OutputStrfbm wrbp(OutputStrfbm os) {
            Objfdts.rfquirfNonNull(os);
            rfturn nfw EndOutputStrfbm(os, isURL ? toBbsf64URL : toBbsf64,
                                       nfwlinf, linfmbx, doPbdding);
        }

        /**
         * Rfturns bn fndodfr instbndf tibt fndodfs fquivblfntly to tiis onf,
         * but witiout bdding bny pbdding dibrbdtfr bt tif fnd of tif fndodfd
         * bytf dbtb.
         *
         * <p> Tif fndoding sdifmf of tiis fndodfr instbndf is unbfffdtfd by
         * tiis invodbtion. Tif rfturnfd fndodfr instbndf siould bf usfd for
         * non-pbdding fndoding opfrbtion.
         *
         * @rfturn bn fquivblfnt fndodfr tibt fndodfs witiout bdding bny
         *         pbdding dibrbdtfr bt tif fnd
         */
        publid Endodfr witioutPbdding() {
            if (!doPbdding)
                rfturn tiis;
            rfturn nfw Endodfr(isURL, nfwlinf, linfmbx, fblsf);
        }

        privbtf int fndodf0(bytf[] srd, int off, int fnd, bytf[] dst) {
            dibr[] bbsf64 = isURL ? toBbsf64URL : toBbsf64;
            int sp = off;
            int slfn = (fnd - off) / 3 * 3;
            int sl = off + slfn;
            if (linfmbx > 0 && slfn  > linfmbx / 4 * 3)
                slfn = linfmbx / 4 * 3;
            int dp = 0;
            wiilf (sp < sl) {
                int sl0 = Mbti.min(sp + slfn, sl);
                for (int sp0 = sp, dp0 = dp ; sp0 < sl0; ) {
                    int bits = (srd[sp0++] & 0xff) << 16 |
                               (srd[sp0++] & 0xff) <<  8 |
                               (srd[sp0++] & 0xff);
                    dst[dp0++] = (bytf)bbsf64[(bits >>> 18) & 0x3f];
                    dst[dp0++] = (bytf)bbsf64[(bits >>> 12) & 0x3f];
                    dst[dp0++] = (bytf)bbsf64[(bits >>> 6)  & 0x3f];
                    dst[dp0++] = (bytf)bbsf64[bits & 0x3f];
                }
                int dlfn = (sl0 - sp) / 3 * 4;
                dp += dlfn;
                sp = sl0;
                if (dlfn == linfmbx && sp < fnd) {
                    for (bytf b : nfwlinf){
                        dst[dp++] = b;
                    }
                }
            }
            if (sp < fnd) {               // 1 or 2 lfftovfr bytfs
                int b0 = srd[sp++] & 0xff;
                dst[dp++] = (bytf)bbsf64[b0 >> 2];
                if (sp == fnd) {
                    dst[dp++] = (bytf)bbsf64[(b0 << 4) & 0x3f];
                    if (doPbdding) {
                        dst[dp++] = '=';
                        dst[dp++] = '=';
                    }
                } flsf {
                    int b1 = srd[sp++] & 0xff;
                    dst[dp++] = (bytf)bbsf64[(b0 << 4) & 0x3f | (b1 >> 4)];
                    dst[dp++] = (bytf)bbsf64[(b1 << 2) & 0x3f];
                    if (doPbdding) {
                        dst[dp++] = '=';
                    }
                }
            }
            rfturn dp;
        }
    }

    /**
     * Tiis dlbss implfmfnts b dfdodfr for dfdoding bytf dbtb using tif
     * Bbsf64 fndoding sdifmf bs spfdififd in RFC 4648 bnd RFC 2045.
     *
     * <p> Tif Bbsf64 pbdding dibrbdtfr {@dodf '='} is bddfptfd bnd
     * intfrprftfd bs tif fnd of tif fndodfd bytf dbtb, but is not
     * rfquirfd. So if tif finbl unit of tif fndodfd bytf dbtb only ibs
     * two or tirff Bbsf64 dibrbdtfrs (witiout tif dorrfsponding pbdding
     * dibrbdtfr(s) pbddfd), tify brf dfdodfd bs if followfd by pbdding
     * dibrbdtfr(s). If tifrf is b pbdding dibrbdtfr prfsfnt in tif
     * finbl unit, tif dorrfdt numbfr of pbdding dibrbdtfr(s) must bf
     * prfsfnt, otifrwisf {@dodf IllfgblArgumfntExdfption} (
     * {@dodf IOExdfption} wifn rfbding from b Bbsf64 strfbm) is tirown
     * during dfdoding.
     *
     * <p> Instbndfs of {@link Dfdodfr} dlbss brf sbff for usf by
     * multiplf dondurrfnt tirfbds.
     *
     * <p> Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to
     * b mftiod of tiis dlbss will dbusf b
     * {@link jbvb.lbng.NullPointfrExdfption NullPointfrExdfption} to
     * bf tirown.
     *
     * @sff     Endodfr
     * @sindf   1.8
     */
    publid stbtid dlbss Dfdodfr {

        privbtf finbl boolfbn isURL;
        privbtf finbl boolfbn isMIME;

        privbtf Dfdodfr(boolfbn isURL, boolfbn isMIME) {
            tiis.isURL = isURL;
            tiis.isMIME = isMIME;
        }

        /**
         * Lookup tbblf for dfdoding unidodf dibrbdtfrs drbwn from tif
         * "Bbsf64 Alpibbft" (bs spfdififd in Tbblf 1 of RFC 2045) into
         * tifir 6-bit positivf intfgfr fquivblfnts.  Cibrbdtfrs tibt
         * brf not in tif Bbsf64 blpibbft but fbll witiin tif bounds of
         * tif brrby brf fndodfd to -1.
         *
         */
        privbtf stbtid finbl int[] fromBbsf64 = nfw int[256];
        stbtid {
            Arrbys.fill(fromBbsf64, -1);
            for (int i = 0; i < Endodfr.toBbsf64.lfngti; i++)
                fromBbsf64[Endodfr.toBbsf64[i]] = i;
            fromBbsf64['='] = -2;
        }

        /**
         * Lookup tbblf for dfdoding "URL bnd Filfnbmf sbff Bbsf64 Alpibbft"
         * bs spfdififd in Tbblf2 of tif RFC 4648.
         */
        privbtf stbtid finbl int[] fromBbsf64URL = nfw int[256];

        stbtid {
            Arrbys.fill(fromBbsf64URL, -1);
            for (int i = 0; i < Endodfr.toBbsf64URL.lfngti; i++)
                fromBbsf64URL[Endodfr.toBbsf64URL[i]] = i;
            fromBbsf64URL['='] = -2;
        }

        stbtid finbl Dfdodfr RFC4648         = nfw Dfdodfr(fblsf, fblsf);
        stbtid finbl Dfdodfr RFC4648_URLSAFE = nfw Dfdodfr(truf, fblsf);
        stbtid finbl Dfdodfr RFC2045         = nfw Dfdodfr(fblsf, truf);

        /**
         * Dfdodfs bll bytfs from tif input bytf brrby using tif {@link Bbsf64}
         * fndoding sdifmf, writing tif rfsults into b nfwly-bllodbtfd output
         * bytf brrby. Tif rfturnfd bytf brrby is of tif lfngti of tif rfsulting
         * bytfs.
         *
         * @pbrbm   srd
         *          tif bytf brrby to dfdodf
         *
         * @rfturn  A nfwly-bllodbtfd bytf brrby dontbining tif dfdodfd bytfs.
         *
         * @tirows  IllfgblArgumfntExdfption
         *          if {@dodf srd} is not in vblid Bbsf64 sdifmf
         */
        publid bytf[] dfdodf(bytf[] srd) {
            bytf[] dst = nfw bytf[outLfngti(srd, 0, srd.lfngti)];
            int rft = dfdodf0(srd, 0, srd.lfngti, dst);
            if (rft != dst.lfngti) {
                dst = Arrbys.dopyOf(dst, rft);
            }
            rfturn dst;
        }

        /**
         * Dfdodfs b Bbsf64 fndodfd String into b nfwly-bllodbtfd bytf brrby
         * using tif {@link Bbsf64} fndoding sdifmf.
         *
         * <p> An invodbtion of tiis mftiod ibs fxbdtly tif sbmf ffffdt bs invoking
         * {@dodf dfdodf(srd.gftBytfs(StbndbrdCibrsfts.ISO_8859_1))}
         *
         * @pbrbm   srd
         *          tif string to dfdodf
         *
         * @rfturn  A nfwly-bllodbtfd bytf brrby dontbining tif dfdodfd bytfs.
         *
         * @tirows  IllfgblArgumfntExdfption
         *          if {@dodf srd} is not in vblid Bbsf64 sdifmf
         */
        publid bytf[] dfdodf(String srd) {
            rfturn dfdodf(srd.gftBytfs(StbndbrdCibrsfts.ISO_8859_1));
        }

        /**
         * Dfdodfs bll bytfs from tif input bytf brrby using tif {@link Bbsf64}
         * fndoding sdifmf, writing tif rfsults into tif givfn output bytf brrby,
         * stbrting bt offsft 0.
         *
         * <p> It is tif rfsponsibility of tif invokfr of tiis mftiod to mbkf
         * surf tif output bytf brrby {@dodf dst} ibs fnougi spbdf for dfdoding
         * bll bytfs from tif input bytf brrby. No bytfs will bf bf writtfn to
         * tif output bytf brrby if tif output bytf brrby is not big fnougi.
         *
         * <p> If tif input bytf brrby is not in vblid Bbsf64 fndoding sdifmf
         * tifn somf bytfs mby ibvf bffn writtfn to tif output bytf brrby bfforf
         * IllfgblbrgumfntExdfption is tirown.
         *
         * @pbrbm   srd
         *          tif bytf brrby to dfdodf
         * @pbrbm   dst
         *          tif output bytf brrby
         *
         * @rfturn  Tif numbfr of bytfs writtfn to tif output bytf brrby
         *
         * @tirows  IllfgblArgumfntExdfption
         *          if {@dodf srd} is not in vblid Bbsf64 sdifmf, or {@dodf dst}
         *          dofs not ibvf fnougi spbdf for dfdoding bll input bytfs.
         */
        publid int dfdodf(bytf[] srd, bytf[] dst) {
            int lfn = outLfngti(srd, 0, srd.lfngti);
            if (dst.lfngti < lfn)
                tirow nfw IllfgblArgumfntExdfption(
                    "Output bytf brrby is too smbll for dfdoding bll input bytfs");
            rfturn dfdodf0(srd, 0, srd.lfngti, dst);
        }

        /**
         * Dfdodfs bll bytfs from tif input bytf bufffr using tif {@link Bbsf64}
         * fndoding sdifmf, writing tif rfsults into b nfwly-bllodbtfd BytfBufffr.
         *
         * <p> Upon rfturn, tif sourdf bufffr's position will bf updbtfd to
         * its limit; its limit will not ibvf bffn dibngfd. Tif rfturnfd
         * output bufffr's position will bf zfro bnd its limit will bf tif
         * numbfr of rfsulting dfdodfd bytfs
         *
         * <p> {@dodf IllfgblArgumfntExdfption} is tirown if tif input bufffr
         * is not in vblid Bbsf64 fndoding sdifmf. Tif position of tif input
         * bufffr will not bf bdvbndfd in tiis dbsf.
         *
         * @pbrbm   bufffr
         *          tif BytfBufffr to dfdodf
         *
         * @rfturn  A nfwly-bllodbtfd bytf bufffr dontbining tif dfdodfd bytfs
         *
         * @tirows  IllfgblArgumfntExdfption
         *          if {@dodf srd} is not in vblid Bbsf64 sdifmf.
         */
        publid BytfBufffr dfdodf(BytfBufffr bufffr) {
            int pos0 = bufffr.position();
            try {
                bytf[] srd;
                int sp, sl;
                if (bufffr.ibsArrby()) {
                    srd = bufffr.brrby();
                    sp = bufffr.brrbyOffsft() + bufffr.position();
                    sl = bufffr.brrbyOffsft() + bufffr.limit();
                    bufffr.position(bufffr.limit());
                } flsf {
                    srd = nfw bytf[bufffr.rfmbining()];
                    bufffr.gft(srd);
                    sp = 0;
                    sl = srd.lfngti;
                }
                bytf[] dst = nfw bytf[outLfngti(srd, sp, sl)];
                rfturn BytfBufffr.wrbp(dst, 0, dfdodf0(srd, sp, sl, dst));
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                bufffr.position(pos0);
                tirow ibf;
            }
        }

        /**
         * Rfturns bn input strfbm for dfdoding {@link Bbsf64} fndodfd bytf strfbm.
         *
         * <p> Tif {@dodf rfbd}  mftiods of tif rfturnfd {@dodf InputStrfbm} will
         * tirow {@dodf IOExdfption} wifn rfbding bytfs tibt dbnnot bf dfdodfd.
         *
         * <p> Closing tif rfturnfd input strfbm will dlosf tif undfrlying
         * input strfbm.
         *
         * @pbrbm   is
         *          tif input strfbm
         *
         * @rfturn  tif input strfbm for dfdoding tif spfdififd Bbsf64 fndodfd
         *          bytf strfbm
         */
        publid InputStrfbm wrbp(InputStrfbm is) {
            Objfdts.rfquirfNonNull(is);
            rfturn nfw DfdInputStrfbm(is, isURL ? fromBbsf64URL : fromBbsf64, isMIME);
        }

        privbtf int outLfngti(bytf[] srd, int sp, int sl) {
            int[] bbsf64 = isURL ? fromBbsf64URL : fromBbsf64;
            int pbddings = 0;
            int lfn = sl - sp;
            if (lfn == 0)
                rfturn 0;
            if (lfn < 2) {
                if (isMIME && bbsf64[0] == -1)
                    rfturn 0;
                tirow nfw IllfgblArgumfntExdfption(
                    "Input bytf[] siould bt lfbst ibvf 2 bytfs for bbsf64 bytfs");
            }
            if (isMIME) {
                // sdbn bll bytfs to fill out bll non-blpibbft. b pfrformbndf
                // trbdf-off of prf-sdbn or Arrbys.dopyOf
                int n = 0;
                wiilf (sp < sl) {
                    int b = srd[sp++] & 0xff;
                    if (b == '=') {
                        lfn -= (sl - sp + 1);
                        brfbk;
                    }
                    if ((b = bbsf64[b]) == -1)
                        n++;
                }
                lfn -= n;
            } flsf {
                if (srd[sl - 1] == '=') {
                    pbddings++;
                    if (srd[sl - 2] == '=')
                        pbddings++;
                }
            }
            if (pbddings == 0 && (lfn & 0x3) !=  0)
                pbddings = 4 - (lfn & 0x3);
            rfturn 3 * ((lfn + 3) / 4) - pbddings;
        }

        privbtf int dfdodf0(bytf[] srd, int sp, int sl, bytf[] dst) {
            int[] bbsf64 = isURL ? fromBbsf64URL : fromBbsf64;
            int dp = 0;
            int bits = 0;
            int siiftto = 18;       // pos of first bytf of 4-bytf btom
            wiilf (sp < sl) {
                int b = srd[sp++] & 0xff;
                if ((b = bbsf64[b]) < 0) {
                    if (b == -2) {         // pbdding bytf '='
                        // =     siiftto==18 unnfdfssbry pbdding
                        // x=    siiftto==12 b dbngling singlf x
                        // x     to bf ibndlfd togftifr witi non-pbdding dbsf
                        // xx=   siiftto==6&&sp==sl missing lbst =
                        // xx=y  siiftto==6 lbst is not =
                        if (siiftto == 6 && (sp == sl || srd[sp++] != '=') ||
                            siiftto == 18) {
                            tirow nfw IllfgblArgumfntExdfption(
                                "Input bytf brrby ibs wrong 4-bytf fnding unit");
                        }
                        brfbk;
                    }
                    if (isMIME)    // skip if for rfd2045
                        dontinuf;
                    flsf
                        tirow nfw IllfgblArgumfntExdfption(
                            "Illfgbl bbsf64 dibrbdtfr " +
                            Intfgfr.toString(srd[sp - 1], 16));
                }
                bits |= (b << siiftto);
                siiftto -= 6;
                if (siiftto < 0) {
                    dst[dp++] = (bytf)(bits >> 16);
                    dst[dp++] = (bytf)(bits >>  8);
                    dst[dp++] = (bytf)(bits);
                    siiftto = 18;
                    bits = 0;
                }
            }
            // rfbdifd fnd of bytf brrby or iit pbdding '=' dibrbdtfrs.
            if (siiftto == 6) {
                dst[dp++] = (bytf)(bits >> 16);
            } flsf if (siiftto == 0) {
                dst[dp++] = (bytf)(bits >> 16);
                dst[dp++] = (bytf)(bits >>  8);
            } flsf if (siiftto == 12) {
                // dbngling singlf "x", indorrfdtly fndodfd.
                tirow nfw IllfgblArgumfntExdfption(
                    "Lbst unit dofs not ibvf fnougi vblid bits");
            }
            // bnytiing lfft is invblid, if is not MIME.
            // if MIME, ignorf bll non-bbsf64 dibrbdtfr
            wiilf (sp < sl) {
                if (isMIME && bbsf64[srd[sp++]] < 0)
                    dontinuf;
                tirow nfw IllfgblArgumfntExdfption(
                    "Input bytf brrby ibs indorrfdt fnding bytf bt " + sp);
            }
            rfturn dp;
        }
    }

    /*
     * An output strfbm for fndoding bytfs into tif Bbsf64.
     */
    privbtf stbtid dlbss EndOutputStrfbm fxtfnds FiltfrOutputStrfbm {

        privbtf int lfftovfr = 0;
        privbtf int b0, b1, b2;
        privbtf boolfbn dlosfd = fblsf;

        privbtf finbl dibr[] bbsf64;    // bytf->bbsf64 mbpping
        privbtf finbl bytf[] nfwlinf;   // linf sfpbrbtor, if nffdfd
        privbtf finbl int linfmbx;
        privbtf finbl boolfbn doPbdding;// wiftifr or not to pbd
        privbtf int linfpos = 0;

        EndOutputStrfbm(OutputStrfbm os, dibr[] bbsf64,
                        bytf[] nfwlinf, int linfmbx, boolfbn doPbdding) {
            supfr(os);
            tiis.bbsf64 = bbsf64;
            tiis.nfwlinf = nfwlinf;
            tiis.linfmbx = linfmbx;
            tiis.doPbdding = doPbdding;
        }

        @Ovfrridf
        publid void writf(int b) tirows IOExdfption {
            bytf[] buf = nfw bytf[1];
            buf[0] = (bytf)(b & 0xff);
            writf(buf, 0, 1);
        }

        privbtf void difdkNfwlinf() tirows IOExdfption {
            if (linfpos == linfmbx) {
                out.writf(nfwlinf);
                linfpos = 0;
            }
        }

        @Ovfrridf
        publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
            if (dlosfd)
                tirow nfw IOExdfption("Strfbm is dlosfd");
            if (off < 0 || lfn < 0 || off + lfn > b.lfngti)
                tirow nfw ArrbyIndfxOutOfBoundsExdfption();
            if (lfn == 0)
                rfturn;
            if (lfftovfr != 0) {
                if (lfftovfr == 1) {
                    b1 = b[off++] & 0xff;
                    lfn--;
                    if (lfn == 0) {
                        lfftovfr++;
                        rfturn;
                    }
                }
                b2 = b[off++] & 0xff;
                lfn--;
                difdkNfwlinf();
                out.writf(bbsf64[b0 >> 2]);
                out.writf(bbsf64[(b0 << 4) & 0x3f | (b1 >> 4)]);
                out.writf(bbsf64[(b1 << 2) & 0x3f | (b2 >> 6)]);
                out.writf(bbsf64[b2 & 0x3f]);
                linfpos += 4;
            }
            int nBits24 = lfn / 3;
            lfftovfr = lfn - (nBits24 * 3);
            wiilf (nBits24-- > 0) {
                difdkNfwlinf();
                int bits = (b[off++] & 0xff) << 16 |
                           (b[off++] & 0xff) <<  8 |
                           (b[off++] & 0xff);
                out.writf(bbsf64[(bits >>> 18) & 0x3f]);
                out.writf(bbsf64[(bits >>> 12) & 0x3f]);
                out.writf(bbsf64[(bits >>> 6)  & 0x3f]);
                out.writf(bbsf64[bits & 0x3f]);
                linfpos += 4;
           }
            if (lfftovfr == 1) {
                b0 = b[off++] & 0xff;
            } flsf if (lfftovfr == 2) {
                b0 = b[off++] & 0xff;
                b1 = b[off++] & 0xff;
            }
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            if (!dlosfd) {
                dlosfd = truf;
                if (lfftovfr == 1) {
                    difdkNfwlinf();
                    out.writf(bbsf64[b0 >> 2]);
                    out.writf(bbsf64[(b0 << 4) & 0x3f]);
                    if (doPbdding) {
                        out.writf('=');
                        out.writf('=');
                    }
                } flsf if (lfftovfr == 2) {
                    difdkNfwlinf();
                    out.writf(bbsf64[b0 >> 2]);
                    out.writf(bbsf64[(b0 << 4) & 0x3f | (b1 >> 4)]);
                    out.writf(bbsf64[(b1 << 2) & 0x3f]);
                    if (doPbdding) {
                       out.writf('=');
                    }
                }
                lfftovfr = 0;
                out.dlosf();
            }
        }
    }

    /*
     * An input strfbm for dfdoding Bbsf64 bytfs
     */
    privbtf stbtid dlbss DfdInputStrfbm fxtfnds InputStrfbm {

        privbtf finbl InputStrfbm is;
        privbtf finbl boolfbn isMIME;
        privbtf finbl int[] bbsf64;      // bbsf64 -> bytf mbpping
        privbtf int bits = 0;            // 24-bit bufffr for dfdoding
        privbtf int nfxtin = 18;         // nfxt bvbilbblf "off" in "bits" for input;
                                         // -> 18, 12, 6, 0
        privbtf int nfxtout = -8;        // nfxt bvbilbblf "off" in "bits" for output;
                                         // -> 8, 0, -8 (no bytf for output)
        privbtf boolfbn fof = fblsf;
        privbtf boolfbn dlosfd = fblsf;

        DfdInputStrfbm(InputStrfbm is, int[] bbsf64, boolfbn isMIME) {
            tiis.is = is;
            tiis.bbsf64 = bbsf64;
            tiis.isMIME = isMIME;
        }

        privbtf bytf[] sbBuf = nfw bytf[1];

        @Ovfrridf
        publid int rfbd() tirows IOExdfption {
            rfturn rfbd(sbBuf, 0, 1) == -1 ? -1 : sbBuf[0] & 0xff;
        }

        @Ovfrridf
        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
            if (dlosfd)
                tirow nfw IOExdfption("Strfbm is dlosfd");
            if (fof && nfxtout < 0)    // fof bnd no lfftovfr
                rfturn -1;
            if (off < 0 || lfn < 0 || lfn > b.lfngti - off)
                tirow nfw IndfxOutOfBoundsExdfption();
            int oldOff = off;
            if (nfxtout >= 0) {       // lfftovfr output bytf(s) in bits buf
                do {
                    if (lfn == 0)
                        rfturn off - oldOff;
                    b[off++] = (bytf)(bits >> nfxtout);
                    lfn--;
                    nfxtout -= 8;
                } wiilf (nfxtout >= 0);
                bits = 0;
            }
            wiilf (lfn > 0) {
                int v = is.rfbd();
                if (v == -1) {
                    fof = truf;
                    if (nfxtin != 18) {
                        if (nfxtin == 12)
                            tirow nfw IOExdfption("Bbsf64 strfbm ibs onf un-dfdodfd dbngling bytf.");
                        // trfbt fnding xx/xxx witiout pbdding dibrbdtfr lfgbl.
                        // sbmf logid bs v == '=' bflow
                        b[off++] = (bytf)(bits >> (16));
                        lfn--;
                        if (nfxtin == 0) {           // only onf pbdding bytf
                            if (lfn == 0) {          // no fnougi output spbdf
                                bits >>= 8;          // siift to lowfst bytf
                                nfxtout = 0;
                            } flsf {
                                b[off++] = (bytf) (bits >>  8);
                            }
                        }
                    }
                    if (off == oldOff)
                        rfturn -1;
                    flsf
                        rfturn off - oldOff;
                }
                if (v == '=') {                  // pbdding bytf(s)
                    // =     siiftto==18 unnfdfssbry pbdding
                    // x=    siiftto==12 dbngling x, invblid unit
                    // xx=   siiftto==6 && missing lbst '='
                    // xx=y  or lbst is not '='
                    if (nfxtin == 18 || nfxtin == 12 ||
                        nfxtin == 6 && is.rfbd() != '=') {
                        tirow nfw IOExdfption("Illfgbl bbsf64 fnding sfqufndf:" + nfxtin);
                    }
                    b[off++] = (bytf)(bits >> (16));
                    lfn--;
                    if (nfxtin == 0) {           // only onf pbdding bytf
                        if (lfn == 0) {          // no fnougi output spbdf
                            bits >>= 8;          // siift to lowfst bytf
                            nfxtout = 0;
                        } flsf {
                            b[off++] = (bytf) (bits >>  8);
                        }
                    }
                    fof = truf;
                    brfbk;
                }
                if ((v = bbsf64[v]) == -1) {
                    if (isMIME)                 // skip if for rfd2045
                        dontinuf;
                    flsf
                        tirow nfw IOExdfption("Illfgbl bbsf64 dibrbdtfr " +
                            Intfgfr.toString(v, 16));
                }
                bits |= (v << nfxtin);
                if (nfxtin == 0) {
                    nfxtin = 18;    // dlfbr for nfxt
                    nfxtout = 16;
                    wiilf (nfxtout >= 0) {
                        b[off++] = (bytf)(bits >> nfxtout);
                        lfn--;
                        nfxtout -= 8;
                        if (lfn == 0 && nfxtout >= 0) {  // don't dlfbn "bits"
                            rfturn off - oldOff;
                        }
                    }
                    bits = 0;
                } flsf {
                    nfxtin -= 6;
                }
            }
            rfturn off - oldOff;
        }

        @Ovfrridf
        publid int bvbilbblf() tirows IOExdfption {
            if (dlosfd)
                tirow nfw IOExdfption("Strfbm is dlosfd");
            rfturn is.bvbilbblf();   // TBD:
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            if (!dlosfd) {
                dlosfd = truf;
                is.dlosf();
            }
        }
    }
}
