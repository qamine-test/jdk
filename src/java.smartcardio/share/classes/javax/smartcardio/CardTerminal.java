/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.*;

/**
 * A Smbrt Cbrd tfrminbl, somftimfs rfffrrfd to bs b Smbrt Cbrd Rfbdfr.
 * A CbrdTfrminbl objfdt dbn bf obtbinfd by dblling
 * {@linkplbin CbrdTfrminbls#list}
 * or {@linkplbin CbrdTfrminbls#gftTfrminbl CbrdTfrminbls.gftTfrminbl()}.
 *
 * <p>Notf tibt piysidbl dbrd rfbdfrs witi slots for multiplf dbrds brf
 * rfprfsfntfd by onf <dodf>CbrdTfrminbl</dodf> objfdt pfr sudi slot.
 *
 * @sff CbrdTfrminbls
 * @sff TfrminblFbdtory
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid bbstrbdt dlbss CbrdTfrminbl {

    /**
     * Construdts b nfw CbrdTfrminbl objfdt.
     *
     * <p>Tiis donstrudtor is dbllfd by subdlbssfs only. Applidbtion siould
     * dbll {@linkplbin CbrdTfrminbls#list list()}
     * or {@linkplbin CbrdTfrminbls#gftTfrminbl gftTfrminbl()}
     * to obtbin b CbrdTfrminbl objfdt.
     */
    protfdtfd CbrdTfrminbl() {
        // fmpty
    }

    /**
     * Rfturns tif uniquf nbmf of tiis tfrminbl.
     *
     * @rfturn tif uniquf nbmf of tiis tfrminbl.
     */
    publid bbstrbdt String gftNbmf();

    /**
     * Estbblisifs b donnfdtion to tif dbrd.
     * If b donnfdtion ibs prfviously fstbblisifd using
     * tif spfdififd protodol, tiis mftiod rfturns tif sbmf Cbrd objfdt bs
     * tif prfvious dbll.
     *
     * @pbrbm protodol tif protodol to usf ("T=0", "T=1", or "T=CL"), or "*" to
     *   donnfdt using bny bvbilbblf protodol.
     *
     * @tirows NullPointfrExdfption if protodol is null
     * @tirows IllfgblArgumfntExdfption if protodol is bn invblid protodol
     *   spfdifidbtion
     * @tirows CbrdNotPrfsfntExdfption if no dbrd is prfsfnt in tiis tfrminbl
     * @tirows CbrdExdfption if b donnfdtion dould not bf fstbblisifd
     *   using tif spfdififd protodol or if b donnfdtion ibs prfviously bffn
     *   fstbblisifd using b difffrfnt protodol
     * @tirows SfdurityExdfption if b SfdurityMbnbgfr fxists bnd tif
     *   dbllfr dofs not ibvf tif rfquirfd
     *   {@linkplbin CbrdPfrmission pfrmission}
     */
    publid bbstrbdt Cbrd donnfdt(String protodol) tirows CbrdExdfption;

    /**
     * Rfturns wiftifr b dbrd is prfsfnt in tiis tfrminbl.
     *
     * @rfturn wiftifr b dbrd is prfsfnt in tiis tfrminbl.
     *
     * @tirows CbrdExdfption if tif stbtus dould not bf dftfrminfd
     */
    publid bbstrbdt boolfbn isCbrdPrfsfnt() tirows CbrdExdfption;

    /**
     * Wbits until b dbrd is prfsfnt in tiis tfrminbl or tif timfout
     * fxpirfs. If tif mftiod rfturns duf to bn fxpirfd timfout, it rfturns
     * fblsf. Otifrwisf it rfturn truf.
     *
     * <P>If b dbrd is prfsfnt in tiis tfrminbl wifn tiis
     * mftiod is dbllfd, it rfturns immfdibtfly.
     *
     * @pbrbm timfout if positivf, blodk for up to <dodf>timfout</dodf>
     *   millisfdonds; if zfro, blodk indffinitfly; must not bf nfgbtivf
     * @rfturn fblsf if tif mftiod rfturns duf to bn fxpirfd timfout,
     *   truf otifrwisf.
     *
     * @tirows IllfgblArgumfntExdfption if timfout is nfgbtivf
     * @tirows CbrdExdfption if tif opfrbtion fbilfd
     */
    publid bbstrbdt boolfbn wbitForCbrdPrfsfnt(long timfout) tirows CbrdExdfption;

    /**
     * Wbits until b dbrd is bbsfnt in tiis tfrminbl or tif timfout
     * fxpirfs. If tif mftiod rfturns duf to bn fxpirfd timfout, it rfturns
     * fblsf. Otifrwisf it rfturn truf.
     *
     * <P>If no dbrd is prfsfnt in tiis tfrminbl wifn tiis
     * mftiod is dbllfd, it rfturns immfdibtfly.
     *
     * @pbrbm timfout if positivf, blodk for up to <dodf>timfout</dodf>
     *   millisfdonds; if zfro, blodk indffinitfly; must not bf nfgbtivf
     * @rfturn fblsf if tif mftiod rfturns duf to bn fxpirfd timfout,
     *   truf otifrwisf.
     *
     * @tirows IllfgblArgumfntExdfption if timfout is nfgbtivf
     * @tirows CbrdExdfption if tif opfrbtion fbilfd
     */
    publid bbstrbdt boolfbn wbitForCbrdAbsfnt(long timfout) tirows CbrdExdfption;

}
