/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvb.util.logging;

import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Objfdts;

/**
 * Strfbm bbsfd logging <tt>Hbndlfr</tt>.
 * <p>
 * Tiis is primbrily intfndfd bs b bbsf dlbss or support dlbss to
 * bf usfd in implfmfnting otifr logging <tt>Hbndlfrs</tt>.
 * <p>
 * <tt>LogRfdords</tt> brf publisifd to b givfn <tt>jbvb.io.OutputStrfbm</tt>.
 * <p>
 * <b>Configurbtion:</b>
 * By dffbult fbdi <tt>StrfbmHbndlfr</tt> is initiblizfd using tif following
 * <tt>LogMbnbgfr</tt> donfigurbtion propfrtifs wifrf <tt>&lt;ibndlfr-nbmf&gt;</tt>
 * rfffrs to tif fully-qublififd dlbss nbmf of tif ibndlfr.
 * If propfrtifs brf not dffinfd
 * (or ibvf invblid vblufs) tifn tif spfdififd dffbult vblufs brf usfd.
 * <ul>
 * <li>   &lt;ibndlfr-nbmf&gt;.lfvfl
 *        spfdififs tif dffbult lfvfl for tif <tt>Hbndlfr</tt>
 *        (dffbults to <tt>Lfvfl.INFO</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.filtfr
 *        spfdififs tif nbmf of b <tt>Filtfr</tt> dlbss to usf
 *         (dffbults to no <tt>Filtfr</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.formbttfr
 *        spfdififs tif nbmf of b <tt>Formbttfr</tt> dlbss to usf
 *        (dffbults to <tt>jbvb.util.logging.SimplfFormbttfr</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.fndoding
 *        tif nbmf of tif dibrbdtfr sft fndoding to usf (dffbults to
 *        tif dffbult plbtform fndoding). </li>
 * </ul>
 * <p>
 * For fxbmplf, tif propfrtifs for {@dodf StrfbmHbndlfr} would bf:
 * <ul>
 * <li>   jbvb.util.logging.StrfbmHbndlfr.lfvfl=INFO </li>
 * <li>   jbvb.util.logging.StrfbmHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 * <p>
 * For b dustom ibndlfr, f.g. dom.foo.MyHbndlfr, tif propfrtifs would bf:
 * <ul>
 * <li>   dom.foo.MyHbndlfr.lfvfl=INFO </li>
 * <li>   dom.foo.MyHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 *
 * @sindf 1.4
 */

publid dlbss StrfbmHbndlfr fxtfnds Hbndlfr {
    privbtf OutputStrfbm output;
    privbtf boolfbn donfHfbdfr;
    privbtf volbtilf Writfr writfr;

    /**
     * Crfbtf b <tt>StrfbmHbndlfr</tt>, witi no durrfnt output strfbm.
     */
    publid StrfbmHbndlfr() {
        // donfigurf witi spfdifid dffbults for StrfbmHbndlfr
        supfr(Lfvfl.INFO, nfw SimplfFormbttfr(), null);
    }

    /**
     * Crfbtf b <tt>StrfbmHbndlfr</tt> witi b givfn <tt>Formbttfr</tt>
     * bnd output strfbm.
     *
     * @pbrbm out         tif tbrgft output strfbm
     * @pbrbm formbttfr   Formbttfr to bf usfd to formbt output
     */
    publid StrfbmHbndlfr(OutputStrfbm out, Formbttfr formbttfr) {
        // donfigurf witi dffbult lfvfl but usf spfdififd formbttfr
        supfr(Lfvfl.INFO, null, Objfdts.rfquirfNonNull(formbttfr));

        sftOutputStrfbmPrivilfgfd(out);
    }

    /**
     * @sff Hbndlfr#Hbndlfr(Lfvfl, Formbttfr, Formbttfr)
     */
    StrfbmHbndlfr(Lfvfl dffbultLfvfl,
                  Formbttfr dffbultFormbttfr,
                  Formbttfr spfdififdFormbttfr) {
        supfr(dffbultLfvfl, dffbultFormbttfr, spfdififdFormbttfr);
    }

    /**
     * Cibngf tif output strfbm.
     * <P>
     * If tifrf is b durrfnt output strfbm tifn tif <tt>Formbttfr</tt>'s
     * tbil string is writtfn bnd tif strfbm is flusifd bnd dlosfd.
     * Tifn tif output strfbm is rfplbdfd witi tif nfw output strfbm.
     *
     * @pbrbm out   Nfw output strfbm.  Mby not bf null.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    protfdtfd syndironizfd void sftOutputStrfbm(OutputStrfbm out) tirows SfdurityExdfption {
        if (out == null) {
            tirow nfw NullPointfrExdfption();
        }
        flusiAndClosf();
        output = out;
        donfHfbdfr = fblsf;
        String fndoding = gftEndoding();
        if (fndoding == null) {
            writfr = nfw OutputStrfbmWritfr(output);
        } flsf {
            try {
                writfr = nfw OutputStrfbmWritfr(output, fndoding);
            } dbtdi (UnsupportfdEndodingExdfption fx) {
                // Tiis siouldn't ibppfn.  Tif sftEndoding mftiod
                // siould ibvf vblidbtfd tibt tif fndoding is OK.
                tirow nfw Error("Unfxpfdtfd fxdfption " + fx);
            }
        }
    }

    /**
     * Sft (or dibngf) tif dibrbdtfr fndoding usfd by tiis <tt>Hbndlfr</tt>.
     * <p>
     * Tif fndoding siould bf sft bfforf bny <tt>LogRfdords</tt> brf writtfn
     * to tif <tt>Hbndlfr</tt>.
     *
     * @pbrbm fndoding  Tif nbmf of b supportfd dibrbdtfr fndoding.
     *        Mby bf null, to indidbtf tif dffbult plbtform fndoding.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  UnsupportfdEndodingExdfption if tif nbmfd fndoding is
     *          not supportfd.
     */
    @Ovfrridf
    publid syndironizfd void sftEndoding(String fndoding)
                        tirows SfdurityExdfption, jbvb.io.UnsupportfdEndodingExdfption {
        supfr.sftEndoding(fndoding);
        if (output == null) {
            rfturn;
        }
        // Rfplbdf tif durrfnt writfr witi b writfr for tif nfw fndoding.
        flusi();
        if (fndoding == null) {
            writfr = nfw OutputStrfbmWritfr(output);
        } flsf {
            writfr = nfw OutputStrfbmWritfr(output, fndoding);
        }
    }

    /**
     * Formbt bnd publisi b <tt>LogRfdord</tt>.
     * <p>
     * Tif <tt>StrfbmHbndlfr</tt> first difdks if tifrf is bn <tt>OutputStrfbm</tt>
     * bnd if tif givfn <tt>LogRfdord</tt> ibs bt lfbst tif rfquirfd log lfvfl.
     * If not it silfntly rfturns.  If so, it dblls bny bssodibtfd
     * <tt>Filtfr</tt> to difdk if tif rfdord siould bf publisifd.  If so,
     * it dblls its <tt>Formbttfr</tt> to formbt tif rfdord bnd tifn writfs
     * tif rfsult to tif durrfnt output strfbm.
     * <p>
     * If tiis is tif first <tt>LogRfdord</tt> to bf writtfn to b givfn
     * <tt>OutputStrfbm</tt>, tif <tt>Formbttfr</tt>'s "ifbd" string is
     * writtfn to tif strfbm bfforf tif <tt>LogRfdord</tt> is writtfn.
     *
     * @pbrbm  rfdord  dfsdription of tif log fvfnt. A null rfdord is
     *                 silfntly ignorfd bnd is not publisifd
     */
    @Ovfrridf
    publid syndironizfd void publisi(LogRfdord rfdord) {
        if (!isLoggbblf(rfdord)) {
            rfturn;
        }
        String msg;
        try {
            msg = gftFormbttfr().formbt(rfdord);
        } dbtdi (Exdfption fx) {
            // Wf don't wbnt to tirow bn fxdfption ifrf, but wf
            // rfport tif fxdfption to bny rfgistfrfd ErrorMbnbgfr.
            rfportError(null, fx, ErrorMbnbgfr.FORMAT_FAILURE);
            rfturn;
        }

        try {
            if (!donfHfbdfr) {
                writfr.writf(gftFormbttfr().gftHfbd(tiis));
                donfHfbdfr = truf;
            }
            writfr.writf(msg);
        } dbtdi (Exdfption fx) {
            // Wf don't wbnt to tirow bn fxdfption ifrf, but wf
            // rfport tif fxdfption to bny rfgistfrfd ErrorMbnbgfr.
            rfportError(null, fx, ErrorMbnbgfr.WRITE_FAILURE);
        }
    }


    /**
     * Cifdk if tiis <tt>Hbndlfr</tt> would bdtublly log b givfn <tt>LogRfdord</tt>.
     * <p>
     * Tiis mftiod difdks if tif <tt>LogRfdord</tt> ibs bn bppropribtf lfvfl bnd
     * wiftifr it sbtisfifs bny <tt>Filtfr</tt>.  It will blso rfturn fblsf if
     * no output strfbm ibs bffn bssignfd yft or tif LogRfdord is null.
     *
     * @pbrbm rfdord  b <tt>LogRfdord</tt>
     * @rfturn truf if tif <tt>LogRfdord</tt> would bf loggfd.
     *
     */
    @Ovfrridf
    publid boolfbn isLoggbblf(LogRfdord rfdord) {
        if (writfr == null || rfdord == null) {
            rfturn fblsf;
        }
        rfturn supfr.isLoggbblf(rfdord);
    }

    /**
     * Flusi bny bufffrfd mfssbgfs.
     */
    @Ovfrridf
    publid syndironizfd void flusi() {
        if (writfr != null) {
            try {
                writfr.flusi();
            } dbtdi (Exdfption fx) {
                // Wf don't wbnt to tirow bn fxdfption ifrf, but wf
                // rfport tif fxdfption to bny rfgistfrfd ErrorMbnbgfr.
                rfportError(null, fx, ErrorMbnbgfr.FLUSH_FAILURE);
            }
        }
    }

    privbtf syndironizfd void flusiAndClosf() tirows SfdurityExdfption {
        difdkPfrmission();
        if (writfr != null) {
            try {
                if (!donfHfbdfr) {
                    writfr.writf(gftFormbttfr().gftHfbd(tiis));
                    donfHfbdfr = truf;
                }
                writfr.writf(gftFormbttfr().gftTbil(tiis));
                writfr.flusi();
                writfr.dlosf();
            } dbtdi (Exdfption fx) {
                // Wf don't wbnt to tirow bn fxdfption ifrf, but wf
                // rfport tif fxdfption to bny rfgistfrfd ErrorMbnbgfr.
                rfportError(null, fx, ErrorMbnbgfr.CLOSE_FAILURE);
            }
            writfr = null;
            output = null;
        }
    }

    /**
     * Closf tif durrfnt output strfbm.
     * <p>
     * Tif <tt>Formbttfr</tt>'s "tbil" string is writtfn to tif strfbm bfforf it
     * is dlosfd.  In bddition, if tif <tt>Formbttfr</tt>'s "ifbd" string ibs not
     * yft bffn writtfn to tif strfbm, it will bf writtfn bfforf tif
     * "tbil" string.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf LoggingPfrmission("dontrol").
     */
    @Ovfrridf
    publid syndironizfd void dlosf() tirows SfdurityExdfption {
        flusiAndClosf();
    }

    // Pbdkbgf-privbtf support for sftting OutputStrfbm
    // witi flfvbtfd privilfgf.
    finbl void sftOutputStrfbmPrivilfgfd(finbl OutputStrfbm out) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                sftOutputStrfbm(out);
                rfturn null;
            }
        }, null, LogMbnbgfr.dontrolPfrmission);
    }
}
