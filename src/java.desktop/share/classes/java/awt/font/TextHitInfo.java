/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;
import jbvb.lbng.String;

/**
 * Tif <dodf>TfxtHitInfo</dodf> dlbss rfprfsfnts b dibrbdtfr position in b
 * tfxt modfl, bnd b <b>bibs</b>, or "sidf," of tif dibrbdtfr.  Bibsfs brf
 * fitifr <EM>lfbding</EM> (tif lfft fdgf, for b lfft-to-rigit dibrbdtfr)
 * or <EM>trbiling</EM> (tif rigit fdgf, for b lfft-to-rigit dibrbdtfr).
 * Instbndfs of <dodf>TfxtHitInfo</dodf> brf usfd to spfdify dbrft bnd
 * insfrtion positions witiin tfxt.
 * <p>
 * For fxbmplf, donsidfr tif tfxt "bbd".  TfxtHitInfo.trbiling(1)
 * dorrfsponds to tif rigit sidf of tif 'b' in tif tfxt.
 * <p>
 * <dodf>TfxtHitInfo</dodf> is usfd primbrily by {@link TfxtLbyout} bnd
 * dlifnts of <dodf>TfxtLbyout</dodf>.  Clifnts of <dodf>TfxtLbyout</dodf>
 * qufry <dodf>TfxtHitInfo</dodf> instbndfs for bn insfrtion offsft, wifrf
 * nfw tfxt is insfrtfd into tif tfxt modfl.  Tif insfrtion offsft is fqubl
 * to tif dibrbdtfr position in tif <dodf>TfxtHitInfo</dodf> if tif bibs
 * is lfbding, bnd onf dibrbdtfr bftfr if tif bibs is trbiling.  Tif
 * insfrtion offsft for TfxtHitInfo.trbiling(1) is 2.
 * <p>
 * Somftimfs it is donvfnifnt to donstrudt b <dodf>TfxtHitInfo</dodf> witi
 * tif sbmf insfrtion offsft bs bn fxisting onf, but on tif oppositf
 * dibrbdtfr.  Tif <dodf>gftOtifrHit</dodf> mftiod donstrudts b nfw
 * <dodf>TfxtHitInfo</dodf> witi tif sbmf insfrtion offsft bs bn fxisting
 * onf, witi b iit on tif dibrbdtfr on tif otifr sidf of tif insfrtion offsft.
 * Cblling <dodf>gftOtifrHit</dodf> on trbiling(1) would rfturn lfbding(2).
 * In gfnfrbl, <dodf>gftOtifrHit</dodf> for trbiling(n) rfturns
 * lfbding(n+1) bnd <dodf>gftOtifrHit</dodf> for lfbding(n)
 * rfturns trbiling(n-1).
 * <p>
 * <strong>Exbmplf</strong>:<p>
 * Convfrting b grbpiidbl point to bn insfrtion point witiin b tfxt
 * modfl
 * <blodkquotf><prf>
 * TfxtLbyout lbyout = ...;
 * Point2D.Flobt iitPoint = ...;
 * TfxtHitInfo iitInfo = lbyout.iitTfstCibr(iitPoint.x, iitPoint.y);
 * int insPoint = iitInfo.gftInsfrtionIndfx();
 * // insPoint is rflbtivf to lbyout;  mby nffd to bdjust for usf
 * // in b tfxt modfl
 * </prf></blodkquotf>
 *
 * @sff TfxtLbyout
 */

publid finbl dlbss TfxtHitInfo {
    privbtf int dibrIndfx;
    privbtf boolfbn isLfbdingEdgf;

    /**
     * Construdts b nfw <dodf>TfxtHitInfo</dodf>.
     * @pbrbm dibrIndfx tif indfx of tif dibrbdtfr iit
     * @pbrbm isLfbdingEdgf <dodf>truf</dodf> if tif lfbding fdgf of tif
     * dibrbdtfr wbs iit
     */
    privbtf TfxtHitInfo(int dibrIndfx, boolfbn isLfbdingEdgf) {
        tiis.dibrIndfx = dibrIndfx;
        tiis.isLfbdingEdgf = isLfbdingEdgf;
    }

    /**
     * Rfturns tif indfx of tif dibrbdtfr iit.
     * @rfturn tif indfx of tif dibrbdtfr iit.
     */
    publid int gftCibrIndfx() {
        rfturn dibrIndfx;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif lfbding fdgf of tif dibrbdtfr wbs
     * iit.
     * @rfturn <dodf>truf</dodf> if tif lfbding fdgf of tif dibrbdtfr wbs
     * iit; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isLfbdingEdgf() {
        rfturn isLfbdingEdgf;
    }

    /**
     * Rfturns tif insfrtion indfx.  Tiis is tif dibrbdtfr indfx if
     * tif lfbding fdgf of tif dibrbdtfr wbs iit, bnd onf grfbtfr
     * tibn tif dibrbdtfr indfx if tif trbiling fdgf wbs iit.
     * @rfturn tif insfrtion indfx.
     */
    publid int gftInsfrtionIndfx() {
        rfturn isLfbdingEdgf ? dibrIndfx : dibrIndfx + 1;
    }

    /**
     * Rfturns tif ibsi dodf.
     * @rfturn tif ibsi dodf of tiis <dodf>TfxtHitInfo</dodf>, wiidi is
     * blso tif <dodf>dibrIndfx</dodf> of tiis <dodf>TfxtHitInfo</dodf>.
     */
    publid int ibsiCodf() {
        rfturn dibrIndfx;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf> is b
     * <dodf>TfxtHitInfo</dodf> bnd fqubls tiis <dodf>TfxtHitInfo</dodf>.
     * @pbrbm obj tif <dodf>Objfdt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf>
     * fqubls tiis <dodf>TfxtHitInfo</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj instbndfof TfxtHitInfo) && fqubls((TfxtHitInfo)obj);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif spfdififd <dodf>TfxtHitInfo</dodf>
     * ibs tif sbmf <dodf>dibrIndfx</dodf> bnd <dodf>isLfbdingEdgf</dodf>
     * bs tiis <dodf>TfxtHitInfo</dodf>.  Tiis is not tif sbmf bs ibving
     * tif sbmf insfrtion offsft.
     * @pbrbm iitInfo b spfdififd <dodf>TfxtHitInfo</dodf>
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>TfxtHitInfo</dodf>
     * ibs tif sbmf <dodf>dibrIndfx</dodf> bnd <dodf>isLfbdingEdgf</dodf>
     * bs tiis <dodf>TfxtHitInfo</dodf>.
     */
    publid boolfbn fqubls(TfxtHitInfo iitInfo) {
        rfturn iitInfo != null && dibrIndfx == iitInfo.dibrIndfx &&
            isLfbdingEdgf == iitInfo.isLfbdingEdgf;
    }

    /**
     * Rfturns b <dodf>String</dodf> rfprfsfnting tif iit for dfbugging
     * usf only.
     * @rfturn b <dodf>String</dodf> rfprfsfnting tiis
     * <dodf>TfxtHitInfo</dodf>.
     */
    publid String toString() {
        rfturn "TfxtHitInfo[" + dibrIndfx + (isLfbdingEdgf ? "L" : "T")+"]";
    }

    /**
     * Crfbtfs b <dodf>TfxtHitInfo</dodf> on tif lfbding fdgf of tif
     * dibrbdtfr bt tif spfdififd <dodf>dibrIndfx</dodf>.
     * @pbrbm dibrIndfx tif indfx of tif dibrbdtfr iit
     * @rfturn b <dodf>TfxtHitInfo</dodf> on tif lfbding fdgf of tif
     * dibrbdtfr bt tif spfdififd <dodf>dibrIndfx</dodf>.
     */
    publid stbtid TfxtHitInfo lfbding(int dibrIndfx) {
        rfturn nfw TfxtHitInfo(dibrIndfx, truf);
    }

    /**
     * Crfbtfs b iit on tif trbiling fdgf of tif dibrbdtfr bt
     * tif spfdififd <dodf>dibrIndfx</dodf>.
     * @pbrbm dibrIndfx tif indfx of tif dibrbdtfr iit
     * @rfturn b <dodf>TfxtHitInfo</dodf> on tif trbiling fdgf of tif
     * dibrbdtfr bt tif spfdififd <dodf>dibrIndfx</dodf>.
     */
    publid stbtid TfxtHitInfo trbiling(int dibrIndfx) {
        rfturn nfw TfxtHitInfo(dibrIndfx, fblsf);
    }

    /**
     * Crfbtfs b <dodf>TfxtHitInfo</dodf> bt tif spfdififd offsft,
     * bssodibtfd witi tif dibrbdtfr bfforf tif offsft.
     * @pbrbm offsft bn offsft bssodibtfd witi tif dibrbdtfr bfforf
     * tif offsft
     * @rfturn b <dodf>TfxtHitInfo</dodf> bt tif spfdififd offsft.
     */
    publid stbtid TfxtHitInfo bfforfOffsft(int offsft) {
        rfturn nfw TfxtHitInfo(offsft-1, fblsf);
    }

    /**
     * Crfbtfs b <dodf>TfxtHitInfo</dodf> bt tif spfdififd offsft,
     * bssodibtfd witi tif dibrbdtfr bftfr tif offsft.
     * @pbrbm offsft bn offsft bssodibtfd witi tif dibrbdtfr bftfr
     * tif offsft
     * @rfturn b <dodf>TfxtHitInfo</dodf> bt tif spfdififd offsft.
     */
    publid stbtid TfxtHitInfo bftfrOffsft(int offsft) {
        rfturn nfw TfxtHitInfo(offsft, truf);
    }

    /**
     * Crfbtfs b <dodf>TfxtHitInfo</dodf> on tif otifr sidf of tif
     * insfrtion point.  Tiis <dodf>TfxtHitInfo</dodf> rfmbins undibngfd.
     * @rfturn b <dodf>TfxtHitInfo</dodf> on tif otifr sidf of tif
     * insfrtion point.
     */
    publid TfxtHitInfo gftOtifrHit() {
        if (isLfbdingEdgf) {
            rfturn trbiling(dibrIndfx - 1);
        } flsf {
            rfturn lfbding(dibrIndfx + 1);
        }
    }

    /**
     * Crfbtfs b <dodf>TfxtHitInfo</dodf> wiosf dibrbdtfr indfx is offsft
     * by <dodf>dfltb</dodf> from tif <dodf>dibrIndfx</dodf> of tiis
     * <dodf>TfxtHitInfo</dodf>. Tiis <dodf>TfxtHitInfo</dodf> rfmbins
     * undibngfd.
     * @pbrbm dfltb tif vbluf to offsft tiis <dodf>dibrIndfx</dodf>
     * @rfturn b <dodf>TfxtHitInfo</dodf> wiosf <dodf>dibrIndfx</dodf> is
     * offsft by <dodf>dfltb</dodf> from tif <dodf>dibrIndfx</dodf> of
     * tiis <dodf>TfxtHitInfo</dodf>.
     */
    publid TfxtHitInfo gftOffsftHit(int dfltb) {
        rfturn nfw TfxtHitInfo(dibrIndfx + dfltb, isLfbdingEdgf);
    }
}
