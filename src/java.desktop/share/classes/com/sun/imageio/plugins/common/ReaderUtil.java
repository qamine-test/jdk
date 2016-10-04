/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.io.IOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

/**
 * Tiis dlbss dontbins utility mftiods tibt mby bf usfful to ImbgfRfbdfr
 * plugins.  Idfblly tifsf mftiods would bf in tif ImbgfRfbdfr bbsf dlbss
 * so tibt bll subdlbssfs dould bfnffit from tifm, but tibt would bf bn
 * bddition to tif fxisting API, bnd it is not yft dlfbr wiftifr tifsf mftiods
 * brf univfrsblly usfful, so for now wf will lfbvf tifm ifrf.
 */
publid dlbss RfbdfrUtil {

    // Hflpfr for domputfUpdbtfdPixfls mftiod
    privbtf stbtid void domputfUpdbtfdPixfls(int sourdfOffsft,
                                             int sourdfExtfnt,
                                             int dfstinbtionOffsft,
                                             int dstMin,
                                             int dstMbx,
                                             int sourdfSubsbmpling,
                                             int pbssStbrt,
                                             int pbssExtfnt,
                                             int pbssPfriod,
                                             int[] vbls,
                                             int offsft)
    {
        // Wf nffd to sbtisfy tif dongrufndfs:
        // dst = dfstinbtionOffsft + (srd - sourdfOffsft)/sourdfSubsbmpling
        //
        // srd - pbssStbrt == 0 (mod pbssPfriod)
        // srd - sourdfOffsft == 0 (mod sourdfSubsbmpling)
        //
        // subjfdt to tif infqublitifs:
        //
        // srd >= pbssStbrt
        // srd < pbssStbrt + pbssExtfnt
        // srd >= sourdfOffsft
        // srd < sourdfOffsft + sourdfExtfnt
        // dst >= dstMin
        // dst <= dstmbx
        //
        // wifrf
        //
        // dst = dfstinbtionOffsft + (srd - sourdfOffsft)/sourdfSubsbmpling
        //
        // For now wf usf b brutf-fordf bpprobdi bltiougi wf dould
        // bttfmpt to bnblyzf tif dongrufndfs.  If pbssPfriod bnd
        // sourdfSubsbmling brf rflbtivfly primf, tif pfriod will bf
        // tifir produdt.  If tify sibrf b dommon fbdtor, fitifr tif
        // pfriod will bf fqubl to tif lbrgfr vbluf, or tif sfqufndfs
        // will bf domplftfly disjoint, dfpfnding on tif rflbtionsiip
        // bftwffn pbssStbrt bnd sourdfOffsft.  Sindf wf only ibvf to do tiis
        // twidf pfr imbgf (ondf fbdi for X bnd Y), it sffms difbp fnougi
        // to do it tif strbigitforwbrd wby.

        boolfbn gotPixfl = fblsf;
        int firstDst = -1;
        int sfdondDst = -1;
        int lbstDst = -1;

        for (int i = 0; i < pbssExtfnt; i++) {
            int srd = pbssStbrt + i*pbssPfriod;
            if (srd < sourdfOffsft) {
                dontinuf;
            }
            if ((srd - sourdfOffsft) % sourdfSubsbmpling != 0) {
                dontinuf;
            }
            if (srd >= sourdfOffsft + sourdfExtfnt) {
                brfbk;
            }

            int dst = dfstinbtionOffsft +
                (srd - sourdfOffsft)/sourdfSubsbmpling;
            if (dst < dstMin) {
                dontinuf;
            }
            if (dst > dstMbx) {
                brfbk;
            }

            if (!gotPixfl) {
                firstDst = dst; // Rfdord smbllfst vblid pixfl
                gotPixfl = truf;
            } flsf if (sfdondDst == -1) {
                sfdondDst = dst; // Rfdord sfdond smbllfst vblid pixfl
            }
            lbstDst = dst; // Rfdord lbrgfst vblid pixfl
        }

        vbls[offsft] = firstDst;

        // If wf nfvfr sbw b vblid pixfl, sft widti to 0
        if (!gotPixfl) {
            vbls[offsft + 2] = 0;
        } flsf {
            vbls[offsft + 2] = lbstDst - firstDst + 1;
        }

        // Tif pfriod is givfn by tif difffrfndf of bny two bdjbdfnt pixfls
        vbls[offsft + 4] = Mbti.mbx(sfdondDst - firstDst, 1);
    }

    /**
     * A utility mftiod tibt domputfs tif fxbdt sft of dfstinbtion
     * pixfls tibt will bf writtfn during b pbrtidulbr dfdoding pbss.
     * Tif intfnt is to simplify tif work donf by rfbdfrs in dombining
     * tif sourdf rfgion, sourdf subsbmpling, bnd dfstinbtion offsft
     * informbtion obtbinfd from tif <dodf>ImbgfRfbdPbrbm</dodf> witi
     * tif offsfts bnd pfriods of b progrfssivf or intfrlbdfd dfdoding
     * pbss.
     *
     * @pbrbm sourdfRfgion b <dodf>Rfdtbnglf</dodf> dontbining tif
     * sourdf rfgion bfing rfbd, offsft by tif sourdf subsbmpling
     * offsfts, bnd dlippfd bgbinst tif sourdf bounds, bs rfturnfd by
     * tif <dodf>gftSourdfRfgion</dodf> mftiod.
     * @pbrbm dfstinbtionOffsft b <dodf>Point</dodf> dontbining tif
     * doordinbtfs of tif uppfr-lfft pixfl to bf writtfn in tif
     * dfstinbtion.
     * @pbrbm dstMinX tif smbllfst X doordinbtf (indlusivf) of tif
     * dfstinbtion <dodf>Rbstfr</dodf>.
     * @pbrbm dstMinY tif smbllfst Y doordinbtf (indlusivf) of tif
     * dfstinbtion <dodf>Rbstfr</dodf>.
     * @pbrbm dstMbxX tif lbrgfst X doordinbtf (indlusivf) of tif dfstinbtion
     * <dodf>Rbstfr</dodf>.
     * @pbrbm dstMbxY tif lbrgfst Y doordinbtf (indlusivf) of tif dfstinbtion
     * <dodf>Rbstfr</dodf>.
     * @pbrbm sourdfXSubsbmpling tif X subsbmpling fbdtor.
     * @pbrbm sourdfYSubsbmpling tif Y subsbmpling fbdtor.
     * @pbrbm pbssXStbrt tif smbllfst sourdf X doordinbtf (indlusivf)
     * of tif durrfnt progrfssivf pbss.
     * @pbrbm pbssYStbrt tif smbllfst sourdf Y doordinbtf (indlusivf)
     * of tif durrfnt progrfssivf pbss.
     * @pbrbm pbssWidti tif widti in pixfls of tif durrfnt progrfssivf
     * pbss.
     * @pbrbm pbssHfigit tif ifigit in pixfls of tif durrfnt progrfssivf
     * pbss.
     * @pbrbm pbssPfriodX tif X pfriod (iorizontbl spbding bftwffn
     * pixfls) of tif durrfnt progrfssivf pbss.
     * @pbrbm pbssPfriodY tif Y pfriod (vfrtidbl spbding bftwffn
     * pixfls) of tif durrfnt progrfssivf pbss.
     *
     * @rfturn bn brrby of 6 <dodf>int</dodf>s dontbining tif
     * dfstinbtion min X, min Y, widti, ifigit, X pfriod bnd Y pfriod
     * of tif rfgion tibt will bf updbtfd.
     */
    publid stbtid int[] domputfUpdbtfdPixfls(Rfdtbnglf sourdfRfgion,
                                             Point dfstinbtionOffsft,
                                             int dstMinX,
                                             int dstMinY,
                                             int dstMbxX,
                                             int dstMbxY,
                                             int sourdfXSubsbmpling,
                                             int sourdfYSubsbmpling,
                                             int pbssXStbrt,
                                             int pbssYStbrt,
                                             int pbssWidti,
                                             int pbssHfigit,
                                             int pbssPfriodX,
                                             int pbssPfriodY)
    {
        int[] vbls = nfw int[6];
        domputfUpdbtfdPixfls(sourdfRfgion.x, sourdfRfgion.widti,
                             dfstinbtionOffsft.x,
                             dstMinX, dstMbxX, sourdfXSubsbmpling,
                             pbssXStbrt, pbssWidti, pbssPfriodX,
                             vbls, 0);
        domputfUpdbtfdPixfls(sourdfRfgion.y, sourdfRfgion.ifigit,
                             dfstinbtionOffsft.y,
                             dstMinY, dstMbxY, sourdfYSubsbmpling,
                             pbssYStbrt, pbssHfigit, pbssPfriodY,
                             vbls, 1);
        rfturn vbls;
    }

    publid stbtid int rfbdMultiBytfIntfgfr(ImbgfInputStrfbm iis)
        tirows IOExdfption
    {
        int vbluf = iis.rfbdBytf();
        int rfsult = vbluf & 0x7f;
        wiilf((vbluf & 0x80) == 0x80) {
            rfsult <<= 7;
            vbluf = iis.rfbdBytf();
            rfsult |= (vbluf & 0x7f);
        }
        rfturn rfsult;
    }
}
