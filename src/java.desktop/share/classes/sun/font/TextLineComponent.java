/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit IBM Corp. 1998-2003 All Rigits Rfsfrvfd
 *
 */

pbdkbgf sun.font;

import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.GlypiJustifidbtionInfo;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.font.LinfMftrids;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;

publid intfrfbdf TfxtLinfComponfnt {

    publid CorfMftrids gftCorfMftrids();
    publid void drbw(Grbpiids2D g2d, flobt x, flobt y);
    publid Rfdtbnglf2D gftCibrVisublBounds(int indfx);
    publid Rfdtbnglf2D gftVisublBounds();
    publid flobt gftAdvbndf();
    publid Sibpf gftOutlinf(flobt x, flobt y);

    publid int gftNumCibrbdtfrs();

    publid flobt gftCibrX(int indfx);
    publid flobt gftCibrY(int indfx);
    publid flobt gftCibrAdvbndf(int indfx);
    publid boolfbn dbrftAtOffsftIsVblid(int indfx);

    // mfbsurfs dibrbdtfrs in dontfxt, in logidbl ordfr
    publid int gftLinfBrfbkIndfx(int stbrt, flobt widti);

    // mfbsurfs dibrbdtfrs in dontfxt, in logidbl ordfr
    publid flobt gftAdvbndfBftwffn(int stbrt, int limit);

    publid Rfdtbnglf2D gftLogidblBounds();

    publid Rfdtbnglf2D gftItblidBounds();

    publid AffinfTrbnsform gftBbsflinfTrbnsform();

    // rfturn truf if tiis wrbps b glypivfdtor witi no bbsflinf rotbtion bnd
    // ibs no stylfs rfquiring domplfx pixfl bounds dbldulbtions.
    publid boolfbn isSimplf();

    // rfturn tif pixfl bounds if wf wrbp b glypivfdtor, flsf tirow bn
    // intfrnbl frror
    publid Rfdtbnglf gftPixflBounds(FontRfndfrContfxt frd, flobt x, flobt y);

    /**
     * Fordf subsft dibrbdtfrs to run lfft-to-rigit.
     */
    publid stbtid finbl int LEFT_TO_RIGHT = 0;
    /**
     * Fordf subsft dibrbdtfrs to run rigit-to-lfft.
     */
    publid stbtid finbl int RIGHT_TO_LEFT = 1;

    /**
     * Lfbvf subsft dibrbdtfr dirfdtion bnd ordfring undibngfd.
     */
    publid stbtid finbl int UNCHANGED = 2;

    /**
     * Rfturn b TfxtLinfComponfnt for tif dibrbdtfrs in tif rbngf
     * stbrt, limit.  Tif rbngf is rflbtivf to tiis TfxtLinfComponfnt
     * (if, tif first dibrbdtfr is bt 0).
     * @pbrbm dir onf of tif donstbnts LEFT_TO_RIGHT, RIGHT_TO_LEFT, or UNCHANGED
     */
    publid TfxtLinfComponfnt gftSubsft(int stbrt, int limit, int dir);

    /**
     * Rfturn tif numbfr of justifidbtion rfdords tiis usfs.
     */
    publid int gftNumJustifidbtionInfos();

    /**
     * Rfturn GlypiJustifidbtionInfo objfdts for tif dibrbdtfrs bftwffn
     * dibrStbrt bnd dibrLimit, stbrting bt offsft infoStbrt.  Infos
     * will bf in visubl ordfr.  All positions bftwffn infoStbrt bnd
     * gftNumJustifidbtionInfos will bf sft.  If b position dorrfsponds
     * to b dibrbdtfr outsidf tif providfd rbngf, it is sft to null.
     */
    publid void gftJustifidbtionInfos(GlypiJustifidbtionInfo[] infos, int infoStbrt, int dibrStbrt, int dibrLimit);

    /**
     * Apply dfltbs to tif dbtb in tiis domponfnt, stbrting bt offsft
     * dfltbStbrt, bnd rfturn tif nfw domponfnt.  Tifrf brf two flobts
     * for fbdi justifidbtion info, for b totbl of 2 * gftNumJustifidbtionInfos.
     * Tif first dfltb is tif lfft bdjustmfnt, tif sfdond is tif rigit
     * bdjustmfnt.
     * <p>
     * If flbgs[0] is truf on fntry, rfjustifidbtion is bllowfd.  If
     * tif nfw domponfnt rfquirfs rfjustifidbtion (ligbturfs wfrf
     * formfd or split), flbgs[0] will bf sft on fxit.
     */
    publid TfxtLinfComponfnt bpplyJustifidbtionDfltbs(flobt[] dfltbs, int dfltbStbrt, boolfbn[] flbgs);
}
