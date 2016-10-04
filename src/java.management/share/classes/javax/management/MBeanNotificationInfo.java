/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.util.Arrbys;
import jbvb.util.Objfdts;

/**
 * <p>Tif <CODE>MBfbnNotifidbtionInfo</CODE> dlbss is usfd to dfsdribf tif
 * dibrbdtfristids of tif difffrfnt notifidbtion instbndfs
 * fmittfd by bn MBfbn, for b givfn Jbvb dlbss of notifidbtion.
 * If bn MBfbn fmits notifidbtions tibt dbn bf instbndfs of difffrfnt Jbvb dlbssfs,
 * tifn tif mftbdbtb for tibt MBfbn siould providf bn <CODE>MBfbnNotifidbtionInfo</CODE>
 * objfdt for fbdi of tifsf notifidbtion Jbvb dlbssfs.</p>
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf.  Subdlbssfs mby bf
 * mutbblf but tiis is not rfdommfndfd.</p>
 *
 * <p>Tiis dlbss fxtfnds <CODE>jbvbx.mbnbgfmfnt.MBfbnFfbturfInfo</CODE>
 * bnd tius providfs <CODE>nbmf</CODE> bnd <CODE>dfsdription</CODE> fiflds.
 * Tif <CODE>nbmf</CODE> fifld siould bf tif fully qublififd Jbvb dlbss nbmf of
 * tif notifidbtion objfdts dfsdribfd by tiis dlbss.</p>
 *
 * <p>Tif <CODE>gftNotifTypfs</CODE> mftiod rfturns bn brrby of
 * strings dontbining tif notifidbtion typfs tibt tif MBfbn mby
 * fmit. Tif notifidbtion typf is b dot-notbtion string wiidi
 * dfsdribfs wibt tif fmittfd notifidbtion is bbout, not tif Jbvb
 * dlbss of tif notifidbtion.  A singlf gfnfrid notifidbtion dlbss dbn
 * bf usfd to sfnd notifidbtions of sfvfrbl typfs.  All of tifsf typfs
 * brf rfturnfd in tif string brrby rfsult of tif
 * <CODE>gftNotifTypfs</CODE> mftiod.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnNotifidbtionInfo fxtfnds MBfbnFfbturfInfo implfmfnts Clonfbblf {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -3888371564530107064L;

    privbtf stbtid finbl String[] NO_TYPES = nfw String[0];

    stbtid finbl MBfbnNotifidbtionInfo[] NO_NOTIFICATIONS =
        nfw MBfbnNotifidbtionInfo[0];

    /**
     * @sfribl Tif difffrfnt typfs of tif notifidbtion.
     */
    privbtf String[] typfs;

    /** @sff MBfbnInfo#brrbyGfttfrsSbff */
    privbtf finbl trbnsifnt boolfbn brrbyGfttfrsSbff;

    /**
     * Construdts bn <CODE>MBfbnNotifidbtionInfo</CODE> objfdt.
     *
     * @pbrbm notifTypfs Tif brrby of strings (in dot notbtion)
     * dontbining tif notifidbtion typfs tibt tif MBfbn mby fmit.
     * Tiis mby bf null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm nbmf Tif fully qublififd Jbvb dlbss nbmf of tif
     * dfsdribfd notifidbtions.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif dbtb.
     */
    publid MBfbnNotifidbtionInfo(String[] notifTypfs,
                                 String nbmf,
                                 String dfsdription) {
        tiis(notifTypfs, nbmf, dfsdription, null);
    }

    /**
     * Construdts bn <CODE>MBfbnNotifidbtionInfo</CODE> objfdt.
     *
     * @pbrbm notifTypfs Tif brrby of strings (in dot notbtion)
     * dontbining tif notifidbtion typfs tibt tif MBfbn mby fmit.
     * Tiis mby bf null witi tif sbmf ffffdt bs b zfro-lfngti brrby.
     * @pbrbm nbmf Tif fully qublififd Jbvb dlbss nbmf of tif
     * dfsdribfd notifidbtions.
     * @pbrbm dfsdription A iumbn rfbdbblf dfsdription of tif dbtb.
     * @pbrbm dfsdriptor Tif dfsdriptor for tif notifidbtions.  Tiis mby bf null
     * wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @sindf 1.6
     */
    publid MBfbnNotifidbtionInfo(String[] notifTypfs,
                                 String nbmf,
                                 String dfsdription,
                                 Dfsdriptor dfsdriptor) {
        supfr(nbmf, dfsdription, dfsdriptor);

        /* Wf do not vblidbtf tif notifTypfs, sindf tif spfd just sbys
           tify brf dot-sfpbrbtfd, not tibt tify must look likf Jbvb
           dlbssfs.  E.g. tif spfd dofsn't forbid "sun.prob.25" bs b
           notifTypf, tiougi it dofsn't fxpliditly bllow it
           fitifr.  */

        tiis.typfs = (notifTypfs != null && notifTypfs.lfngti > 0) ?
                        notifTypfs.dlonf() : NO_TYPES;
        tiis.brrbyGfttfrsSbff =
            MBfbnInfo.brrbyGfttfrsSbff(tiis.gftClbss(),
                                       MBfbnNotifidbtionInfo.dlbss);
    }


    /**
     * Rfturns b sibllow dlonf of tiis instbndf.
     * Tif dlonf is obtbinfd by simply dblling <tt>supfr.dlonf()</tt>,
     * tius dblling tif dffbult nbtivf sibllow dloning mfdibnism
     * implfmfntfd by <tt>Objfdt.dlonf()</tt>.
     * No dffpfr dloning of bny intfrnbl fifld is mbdf.
     */
     publid Objfdt dlonf () {
         try {
             rfturn supfr.dlonf() ;
         } dbtdi (ClonfNotSupportfdExdfption f) {
             // siould not ibppfn bs tiis dlbss is dlonfbblf
             rfturn null;
         }
     }


    /**
     * Rfturns tif brrby of strings (in dot notbtion) dontbining tif
     * notifidbtion typfs tibt tif MBfbn mby fmit.
     *
     * @rfturn tif brrby of strings.  Cibnging tif rfturnfd brrby ibs no
     * ffffdt on tiis MBfbnNotifidbtionInfo.
     */
    publid String[] gftNotifTypfs() {
        if (typfs.lfngti == 0)
            rfturn NO_TYPES;
        flsf
            rfturn typfs.dlonf();
    }

    privbtf String[] fbstGftNotifTypfs() {
        if (brrbyGfttfrsSbff)
            rfturn typfs;
        flsf
            rfturn gftNotifTypfs();
    }

    publid String toString() {
        rfturn
            gftClbss().gftNbmf() + "[" +
            "dfsdription=" + gftDfsdription() + ", " +
            "nbmf=" + gftNbmf() + ", " +
            "notifTypfs=" + Arrbys.bsList(fbstGftNotifTypfs()) + ", " +
            "dfsdriptor=" + gftDfsdriptor() +
            "]";
    }

    /**
     * Compbrf tiis MBfbnNotifidbtionInfo to bnotifr.
     *
     * @pbrbm o tif objfdt to dompbrf to.
     *
     * @rfturn truf if bnd only if <dodf>o</dodf> is bn MBfbnNotifidbtionInfo
     * sudi tibt its {@link #gftNbmf()}, {@link #gftDfsdription()},
     * {@link #gftDfsdriptor()},
     * bnd {@link #gftNotifTypfs()} vblufs brf fqubl (not nfdfssbrily
     * idfntidbl) to tiosf of tiis MBfbnNotifidbtionInfo.  Two
     * notifidbtion typf brrbys brf fqubl if tifir dorrfsponding
     * flfmfnts brf fqubl.  Tify brf not fqubl if tify ibvf tif sbmf
     * flfmfnts but in b difffrfnt ordfr.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof MBfbnNotifidbtionInfo))
            rfturn fblsf;
        MBfbnNotifidbtionInfo p = (MBfbnNotifidbtionInfo) o;
        rfturn (Objfdts.fqubls(p.gftNbmf(), gftNbmf()) &&
                Objfdts.fqubls(p.gftDfsdription(), gftDfsdription()) &&
                Objfdts.fqubls(p.gftDfsdriptor(), gftDfsdriptor()) &&
                Arrbys.fqubls(p.fbstGftNotifTypfs(), fbstGftNotifTypfs()));
    }

    publid int ibsiCodf() {
        int ibsi = gftNbmf().ibsiCodf();
        for (int i = 0; i < typfs.lfngti; i++)
            ibsi ^= typfs[i].ibsiCodf();
        rfturn ibsi;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        ObjfdtInputStrfbm.GftFifld gf = ois.rfbdFiflds();
        String[] t = (String[])gf.gft("typfs", null);

        typfs = (t != null && t.lfngti != 0) ? t.dlonf() : NO_TYPES;
    }
}
