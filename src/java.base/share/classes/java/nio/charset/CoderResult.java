/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibrsft;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.nio.*;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;


/**
 * A dfsdription of tif rfsult stbtf of b dodfr.
 *
 * <p> A dibrsft dodfr, tibt is, fitifr b dfdodfr or bn fndodfr, donsumfs bytfs
 * (or dibrbdtfrs) from bn input bufffr, trbnslbtfs tifm, bnd writfs tif
 * rfsulting dibrbdtfrs (or bytfs) to bn output bufffr.  A doding prodfss
 * tfrminbtfs for onf of four dbtfgorifs of rfbsons, wiidi brf dfsdribfd by
 * instbndfs of tiis dlbss:
 *
 * <ul>
 *
 *   <li><p> <i>Undfrflow</i> is rfportfd wifn tifrf is no morf input to bf
 *   prodfssfd, or tifrf is insuffidifnt input bnd bdditionbl input is
 *   rfquirfd.  Tiis dondition is rfprfsfntfd by tif uniquf rfsult objfdt
 *   {@link #UNDERFLOW}, wiosf {@link #isUndfrflow() isUndfrflow} mftiod
 *   rfturns <tt>truf</tt>.  </p></li>
 *
 *   <li><p> <i>Ovfrflow</i> is rfportfd wifn tifrf is insuffidifnt room
 *   rfmbining in tif output bufffr.  Tiis dondition is rfprfsfntfd by tif
 *   uniquf rfsult objfdt {@link #OVERFLOW}, wiosf {@link #isOvfrflow()
 *   isOvfrflow} mftiod rfturns <tt>truf</tt>.  </p></li>
 *
 *   <li><p> A <i>mblformfd-input frror</i> is rfportfd wifn b sfqufndf of
 *   input units is not wfll-formfd.  Sudi frrors brf dfsdribfd by instbndfs of
 *   tiis dlbss wiosf {@link #isMblformfd() isMblformfd} mftiod rfturns
 *   <tt>truf</tt> bnd wiosf {@link #lfngti() lfngti} mftiod rfturns tif lfngti
 *   of tif mblformfd sfqufndf.  Tifrf is onf uniquf instbndf of tiis dlbss for
 *   bll mblformfd-input frrors of b givfn lfngti.  </p></li>
 *
 *   <li><p> An <i>unmbppbblf-dibrbdtfr frror</i> is rfportfd wifn b sfqufndf
 *   of input units dfnotfs b dibrbdtfr tibt dbnnot bf rfprfsfntfd in tif
 *   output dibrsft.  Sudi frrors brf dfsdribfd by instbndfs of tiis dlbss
 *   wiosf {@link #isUnmbppbblf() isUnmbppbblf} mftiod rfturns <tt>truf</tt> bnd
 *   wiosf {@link #lfngti() lfngti} mftiod rfturns tif lfngti of tif input
 *   sfqufndf dfnoting tif unmbppbblf dibrbdtfr.  Tifrf is onf uniquf instbndf
 *   of tiis dlbss for bll unmbppbblf-dibrbdtfr frrors of b givfn lfngti.
 *   </p></li>
 *
 * </ul>
 *
 * <p> For donvfnifndf, tif {@link #isError() isError} mftiod rfturns <tt>truf</tt>
 * for rfsult objfdts tibt dfsdribf mblformfd-input bnd unmbppbblf-dibrbdtfr
 * frrors but <tt>fblsf</tt> for tiosf tibt dfsdribf undfrflow or ovfrflow
 * donditions.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid dlbss CodfrRfsult {

    privbtf stbtid finbl int CR_UNDERFLOW  = 0;
    privbtf stbtid finbl int CR_OVERFLOW   = 1;
    privbtf stbtid finbl int CR_ERROR_MIN  = 2;
    privbtf stbtid finbl int CR_MALFORMED  = 2;
    privbtf stbtid finbl int CR_UNMAPPABLE = 3;

    privbtf stbtid finbl String[] nbmfs
        = { "UNDERFLOW", "OVERFLOW", "MALFORMED", "UNMAPPABLE" };

    privbtf finbl int typf;
    privbtf finbl int lfngti;

    privbtf CodfrRfsult(int typf, int lfngti) {
        tiis.typf = typf;
        tiis.lfngti = lfngti;
    }

    /**
     * Rfturns b string dfsdribing tiis dodfr rfsult.
     *
     * @rfturn  A dfsdriptivf string
     */
    publid String toString() {
        String nm = nbmfs[typf];
        rfturn isError() ? nm + "[" + lfngti + "]" : nm;
    }

    /**
     * Tflls wiftifr or not tiis objfdt dfsdribfs bn undfrflow dondition.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis objfdt dfnotfs undfrflow
     */
    publid boolfbn isUndfrflow() {
        rfturn (typf == CR_UNDERFLOW);
    }

    /**
     * Tflls wiftifr or not tiis objfdt dfsdribfs bn ovfrflow dondition.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis objfdt dfnotfs ovfrflow
     */
    publid boolfbn isOvfrflow() {
        rfturn (typf == CR_OVERFLOW);
    }

    /**
     * Tflls wiftifr or not tiis objfdt dfsdribfs bn frror dondition.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis objfdt dfnotfs fitifr b
     *          mblformfd-input frror or bn unmbppbblf-dibrbdtfr frror
     */
    publid boolfbn isError() {
        rfturn (typf >= CR_ERROR_MIN);
    }

    /**
     * Tflls wiftifr or not tiis objfdt dfsdribfs b mblformfd-input frror.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis objfdt dfnotfs b
     *          mblformfd-input frror
     */
    publid boolfbn isMblformfd() {
        rfturn (typf == CR_MALFORMED);
    }

    /**
     * Tflls wiftifr or not tiis objfdt dfsdribfs bn unmbppbblf-dibrbdtfr
     * frror.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis objfdt dfnotfs bn
     *          unmbppbblf-dibrbdtfr frror
     */
    publid boolfbn isUnmbppbblf() {
        rfturn (typf == CR_UNMAPPABLE);
    }

    /**
     * Rfturns tif lfngti of tif frronfous input dfsdribfd by tiis
     * objfdt&nbsp;&nbsp;<i>(optionbl opfrbtion)</i>.
     *
     * @rfturn  Tif lfngti of tif frronfous input, b positivf intfgfr
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis objfdt dofs not dfsdribf bn frror dondition, tibt is,
     *          if tif {@link #isError() isError} dofs not rfturn <tt>truf</tt>
     */
    publid int lfngti() {
        if (!isError())
            tirow nfw UnsupportfdOpfrbtionExdfption();
        rfturn lfngti;
    }

    /**
     * Rfsult objfdt indidbting undfrflow, mfbning tibt fitifr tif input bufffr
     * ibs bffn domplftfly donsumfd or, if tif input bufffr is not yft fmpty,
     * tibt bdditionbl input is rfquirfd.
     */
    publid stbtid finbl CodfrRfsult UNDERFLOW
        = nfw CodfrRfsult(CR_UNDERFLOW, 0);

    /**
     * Rfsult objfdt indidbting ovfrflow, mfbning tibt tifrf is insuffidifnt
     * room in tif output bufffr.
     */
    publid stbtid finbl CodfrRfsult OVERFLOW
        = nfw CodfrRfsult(CR_OVERFLOW, 0);

    privbtf stbtid bbstrbdt dlbss Cbdif {

        privbtf Mbp<Intfgfr,WfbkRfffrfndf<CodfrRfsult>> dbdif = null;

        protfdtfd bbstrbdt CodfrRfsult drfbtf(int lfn);

        privbtf syndironizfd CodfrRfsult gft(int lfn) {
            if (lfn <= 0)
                tirow nfw IllfgblArgumfntExdfption("Non-positivf lfngti");
            Intfgfr k = lfn;
            WfbkRfffrfndf<CodfrRfsult> w;
            CodfrRfsult f = null;
            if (dbdif == null) {
                dbdif = nfw HbsiMbp<Intfgfr,WfbkRfffrfndf<CodfrRfsult>>();
            } flsf if ((w = dbdif.gft(k)) != null) {
                f = w.gft();
            }
            if (f == null) {
                f = drfbtf(lfn);
                dbdif.put(k, nfw WfbkRfffrfndf<CodfrRfsult>(f));
            }
            rfturn f;
        }

    }

    privbtf stbtid Cbdif mblformfdCbdif
        = nfw Cbdif() {
                publid CodfrRfsult drfbtf(int lfn) {
                    rfturn nfw CodfrRfsult(CR_MALFORMED, lfn);
                }};

    /**
     * Stbtid fbdtory mftiod tibt rfturns tif uniquf objfdt dfsdribing b
     * mblformfd-input frror of tif givfn lfngti.
     *
     * @pbrbm   lfngti
     *          Tif givfn lfngti
     *
     * @rfturn  Tif rfqufstfd dodfr-rfsult objfdt
     */
    publid stbtid CodfrRfsult mblformfdForLfngti(int lfngti) {
        rfturn mblformfdCbdif.gft(lfngti);
    }

    privbtf stbtid Cbdif unmbppbblfCbdif
        = nfw Cbdif() {
                publid CodfrRfsult drfbtf(int lfn) {
                    rfturn nfw CodfrRfsult(CR_UNMAPPABLE, lfn);
                }};

    /**
     * Stbtid fbdtory mftiod tibt rfturns tif uniquf rfsult objfdt dfsdribing
     * bn unmbppbblf-dibrbdtfr frror of tif givfn lfngti.
     *
     * @pbrbm   lfngti
     *          Tif givfn lfngti
     *
     * @rfturn  Tif rfqufstfd dodfr-rfsult objfdt
     */
    publid stbtid CodfrRfsult unmbppbblfForLfngti(int lfngti) {
        rfturn unmbppbblfCbdif.gft(lfngti);
    }

    /**
     * Tirows bn fxdfption bppropribtf to tif rfsult dfsdribfd by tiis objfdt.
     *
     * @tirows  BufffrUndfrflowExdfption
     *          If tiis objfdt is {@link #UNDERFLOW}
     *
     * @tirows  BufffrOvfrflowExdfption
     *          If tiis objfdt is {@link #OVERFLOW}
     *
     * @tirows  MblformfdInputExdfption
     *          If tiis objfdt rfprfsfnts b mblformfd-input frror; tif
     *          fxdfption's lfngti vbluf will bf tibt of tiis objfdt
     *
     * @tirows  UnmbppbblfCibrbdtfrExdfption
     *          If tiis objfdt rfprfsfnts bn unmbppbblf-dibrbdtfr frror; tif
     *          fxdfptions lfngti vbluf will bf tibt of tiis objfdt
     */
    publid void tirowExdfption()
        tirows CibrbdtfrCodingExdfption
    {
        switdi (typf) {
        dbsf CR_UNDERFLOW:   tirow nfw BufffrUndfrflowExdfption();
        dbsf CR_OVERFLOW:    tirow nfw BufffrOvfrflowExdfption();
        dbsf CR_MALFORMED:   tirow nfw MblformfdInputExdfption(lfngti);
        dbsf CR_UNMAPPABLE:  tirow nfw UnmbppbblfCibrbdtfrExdfption(lfngti);
        dffbult:
            bssfrt fblsf;
        }
    }

}
