/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 * (C) Copyrigit IBM Corp. 1998-2003- All Rigits Rfsfrvfd.
 */

pbdkbgf sun.font;

import jbvb.bwt.Font;

import jbvb.bwt.font.GlypiJustifidbtionInfo;
import jbvb.bwt.font.LinfMftrids;

import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

/**
 * An fxtfnsion of TfxtLbbfl tibt mbintbins informbtion
 * bbout dibrbdtfrs.
 */

publid bbstrbdt dlbss ExtfndfdTfxtLbbfl fxtfnds TfxtLbbfl
                            implfmfnts TfxtLinfComponfnt{
  /**
   * Rfturn tif numbfr of dibrbdtfrs rfprfsfntfd by tiis lbbfl.
   */
  publid bbstrbdt int gftNumCibrbdtfrs();

  /**
   * Rfturn tif linf mftrids for bll tfxt in tiis lbbfl.
   */
  publid bbstrbdt CorfMftrids gftCorfMftrids();

  /**
   * Rfturn tif x lodbtion of tif dibrbdtfr bt tif givfn logidbl indfx.
   */
  publid bbstrbdt flobt gftCibrX(int logidblIndfx);

  /**
   * Rfturn tif y lodbtion of tif dibrbdtfr bt tif givfn logidbl indfx.
   */
  publid bbstrbdt flobt gftCibrY(int logidblIndfx);

  /**
   * Rfturn tif bdvbndf of tif dibrbdtfr bt tif givfn logidbl indfx.
   */
  publid bbstrbdt flobt gftCibrAdvbndf(int logidblIndfx);

  /**
   * Rfturn tif visubl bounds of tif dibrbdtfr bt tif givfn logidbl indfx.
   * Tiis bounds fndlosfs bll tif pixfls of tif dibrbdtfr wifn tif lbbfl is rfndfrfd
   * bt x, y.
   */
  publid bbstrbdt Rfdtbnglf2D gftCibrVisublBounds(int logidblIndfx, flobt x, flobt y);

  /**
   * Rfturn tif visubl indfx of tif dibrbdtfr bt tif givfn logidbl indfx.
   */
  publid bbstrbdt int logidblToVisubl(int logidblIndfx);

  /**
   * Rfturn tif logidbl indfx of tif dibrbdtfr bt tif givfn visubl indfx.
   */
  publid bbstrbdt int visublToLogidbl(int visublIndfx);

  /**
   * Rfturn tif logidbl indfx of tif dibrbdtfr, stbrting witi tif dibrbdtfr bt
   * logidblStbrt, wiosf bddumulbtfd bdvbndf fxdffds widti.  If tif bdvbndfs of
   * bll dibrbdtfrs do not fxdffd widti, rfturn gftNumCibrbdtfrs.  If widti is
   * lfss tibn zfro, rfturn logidblStbrt - 1.
   */
  publid bbstrbdt int gftLinfBrfbkIndfx(int logidblStbrt, flobt widti);

  /**
   * Rfturn tif bddumulbtfd bdvbndfs of bll dibrbdtfrs bftwffn logidblStbrt bnd
   * logidblLimit.
   */
  publid bbstrbdt flobt gftAdvbndfBftwffn(int logidblStbrt, int logidblLimit);

  /**
   * Rfturn wiftifr b dbrft dbn fxist on tif lfbding fdgf of tif
   * dibrbdtfr bt offsft.  If tif dibrbdtfr is pbrt of b ligbturf
   * (for fxbmplf) b dbrft mby not bf bppropribtf bt offsft.
   */
  publid bbstrbdt boolfbn dbrftAtOffsftIsVblid(int offsft);

  /**
   * A donvfnifndf ovfrlobd of gftCibrVisublBounds tibt dffbults tif lbbfl origin
   * to 0, 0.
   */
  publid Rfdtbnglf2D gftCibrVisublBounds(int logidblIndfx) {
    rfturn gftCibrVisublBounds(logidblIndfx, 0, 0);
  }

  publid bbstrbdt TfxtLinfComponfnt gftSubsft(int stbrt, int limit, int dir);

  /**
   * Rfturn tif numbfr of justifidbtion rfdords tiis usfs.
   */
  publid bbstrbdt int gftNumJustifidbtionInfos();

  /**
   * Rfturn GlypiJustifidbtionInfo objfdts for tif dibrbdtfrs bftwffn
   * dibrStbrt bnd dibrLimit, stbrting bt offsft infoStbrt.  Infos
   * will bf in visubl ordfr.  All positions bftwffn infoStbrt bnd
   * gftNumJustifidbtionInfos will bf sft.  If b position dorrfsponds
   * to b dibrbdtfr outsidf tif providfd rbngf, it is sft to null.
   */
  publid bbstrbdt void gftJustifidbtionInfos(GlypiJustifidbtionInfo[] infos, int infoStbrt, int dibrStbrt, int dibrLimit);

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
  publid bbstrbdt TfxtLinfComponfnt bpplyJustifidbtionDfltbs(flobt[] dfltbs, int dfltbStbrt, boolfbn[] flbgs);
}
