/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import sun.nio.ds.StrfbmDfdodfr;


/**
 * An InputStrfbmRfbdfr is b bridgf from bytf strfbms to dibrbdtfr strfbms: It
 * rfbds bytfs bnd dfdodfs tifm into dibrbdtfrs using b spfdififd {@link
 * jbvb.nio.dibrsft.Cibrsft dibrsft}.  Tif dibrsft tibt it usfs
 * mby bf spfdififd by nbmf or mby bf givfn fxpliditly, or tif plbtform's
 * dffbult dibrsft mby bf bddfptfd.
 *
 * <p> Ebdi invodbtion of onf of bn InputStrfbmRfbdfr's rfbd() mftiods mby
 * dbusf onf or morf bytfs to bf rfbd from tif undfrlying bytf-input strfbm.
 * To fnbblf tif fffidifnt donvfrsion of bytfs to dibrbdtfrs, morf bytfs mby
 * bf rfbd bifbd from tif undfrlying strfbm tibn brf nfdfssbry to sbtisfy tif
 * durrfnt rfbd opfrbtion.
 *
 * <p> For top fffidifndy, donsidfr wrbpping bn InputStrfbmRfbdfr witiin b
 * BufffrfdRfbdfr.  For fxbmplf:
 *
 * <prf>
 * BufffrfdRfbdfr in
 *   = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(Systfm.in));
 * </prf>
 *
 * @sff BufffrfdRfbdfr
 * @sff InputStrfbm
 * @sff jbvb.nio.dibrsft.Cibrsft
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss InputStrfbmRfbdfr fxtfnds Rfbdfr {

    privbtf finbl StrfbmDfdodfr sd;

    /**
     * Crfbtfs bn InputStrfbmRfbdfr tibt usfs tif dffbult dibrsft.
     *
     * @pbrbm  in   An InputStrfbm
     */
    publid InputStrfbmRfbdfr(InputStrfbm in) {
        supfr(in);
        try {
            sd = StrfbmDfdodfr.forInputStrfbmRfbdfr(in, tiis, (String)null); // ## difdk lodk objfdt
        } dbtdi (UnsupportfdEndodingExdfption f) {
            // Tif dffbult fndoding siould blwbys bf bvbilbblf
            tirow nfw Error(f);
        }
    }

    /**
     * Crfbtfs bn InputStrfbmRfbdfr tibt usfs tif nbmfd dibrsft.
     *
     * @pbrbm  in
     *         An InputStrfbm
     *
     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of b supportfd
     *         {@link jbvb.nio.dibrsft.Cibrsft dibrsft}
     *
     * @fxdfption  UnsupportfdEndodingExdfption
     *             If tif nbmfd dibrsft is not supportfd
     */
    publid InputStrfbmRfbdfr(InputStrfbm in, String dibrsftNbmf)
        tirows UnsupportfdEndodingExdfption
    {
        supfr(in);
        if (dibrsftNbmf == null)
            tirow nfw NullPointfrExdfption("dibrsftNbmf");
        sd = StrfbmDfdodfr.forInputStrfbmRfbdfr(in, tiis, dibrsftNbmf);
    }

    /**
     * Crfbtfs bn InputStrfbmRfbdfr tibt usfs tif givfn dibrsft.
     *
     * @pbrbm  in       An InputStrfbm
     * @pbrbm  ds       A dibrsft
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid InputStrfbmRfbdfr(InputStrfbm in, Cibrsft ds) {
        supfr(in);
        if (ds == null)
            tirow nfw NullPointfrExdfption("dibrsft");
        sd = StrfbmDfdodfr.forInputStrfbmRfbdfr(in, tiis, ds);
    }

    /**
     * Crfbtfs bn InputStrfbmRfbdfr tibt usfs tif givfn dibrsft dfdodfr.
     *
     * @pbrbm  in       An InputStrfbm
     * @pbrbm  dfd      A dibrsft dfdodfr
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid InputStrfbmRfbdfr(InputStrfbm in, CibrsftDfdodfr dfd) {
        supfr(in);
        if (dfd == null)
            tirow nfw NullPointfrExdfption("dibrsft dfdodfr");
        sd = StrfbmDfdodfr.forInputStrfbmRfbdfr(in, tiis, dfd);
    }

    /**
     * Rfturns tif nbmf of tif dibrbdtfr fndoding bfing usfd by tiis strfbm.
     *
     * <p> If tif fndoding ibs bn iistoridbl nbmf tifn tibt nbmf is rfturnfd;
     * otifrwisf tif fndoding's dbnonidbl nbmf is rfturnfd.
     *
     * <p> If tiis instbndf wbs drfbtfd witi tif {@link
     * #InputStrfbmRfbdfr(InputStrfbm, String)} donstrudtor tifn tif rfturnfd
     * nbmf, bfing uniquf for tif fndoding, mby difffr from tif nbmf pbssfd to
     * tif donstrudtor. Tiis mftiod will rfturn <dodf>null</dodf> if tif
     * strfbm ibs bffn dlosfd.
     * </p>
     * @rfturn Tif iistoridbl nbmf of tiis fndoding, or
     *         <dodf>null</dodf> if tif strfbm ibs bffn dlosfd
     *
     * @sff jbvb.nio.dibrsft.Cibrsft
     *
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid String gftEndoding() {
        rfturn sd.gftEndoding();
    }

    /**
     * Rfbds b singlf dibrbdtfr.
     *
     * @rfturn Tif dibrbdtfr rfbd, or -1 if tif fnd of tif strfbm ibs bffn
     *         rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd() tirows IOExdfption {
        rfturn sd.rfbd();
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.
     *
     * @pbrbm      dbuf     Dfstinbtion bufffr
     * @pbrbm      offsft   Offsft bt wiidi to stbrt storing dibrbdtfrs
     * @pbrbm      lfngti   Mbximum numbfr of dibrbdtfrs to rfbd
     *
     * @rfturn     Tif numbfr of dibrbdtfrs rfbd, or -1 if tif fnd of tif
     *             strfbm ibs bffn rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd(dibr dbuf[], int offsft, int lfngti) tirows IOExdfption {
        rfturn sd.rfbd(dbuf, offsft, lfngti);
    }

    /**
     * Tflls wiftifr tiis strfbm is rfbdy to bf rfbd.  An InputStrfbmRfbdfr is
     * rfbdy if its input bufffr is not fmpty, or if bytfs brf bvbilbblf to bf
     * rfbd from tif undfrlying bytf strfbm.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid boolfbn rfbdy() tirows IOExdfption {
        rfturn sd.rfbdy();
    }

    publid void dlosf() tirows IOExdfption {
        sd.dlosf();
    }
}
