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

import stbtid jbvb.nio.filf.StbndbrdOpfnOption.APPEND;
import stbtid jbvb.nio.filf.StbndbrdOpfnOption.CREATE_NEW;
import stbtid jbvb.nio.filf.StbndbrdOpfnOption.WRITE;

import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.nio.dibnnfls.FilfCibnnfl;
import jbvb.nio.dibnnfls.OvfrlbppingFilfLodkExdfption;
import jbvb.nio.filf.FilfAlrfbdyExistsExdfption;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.LinkOption;
import jbvb.nio.filf.NoSudiFilfExdfption;
import jbvb.nio.filf.Pbti;
import jbvb.nio.filf.Pbtis;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;

/**
 * Simplf filf logging <tt>Hbndlfr</tt>.
 * <p>
 * Tif <tt>FilfHbndlfr</tt> dbn fitifr writf to b spfdififd filf,
 * or it dbn writf to b rotbting sft of filfs.
 * <p>
 * For b rotbting sft of filfs, bs fbdi filf rfbdifs b givfn sizf
 * limit, it is dlosfd, rotbtfd out, bnd b nfw filf opfnfd.
 * Suddfssivfly oldfr filfs brf nbmfd by bdding "0", "1", "2",
 * ftd. into tif bbsf filfnbmf.
 * <p>
 * By dffbult bufffring is fnbblfd in tif IO librbrifs but fbdi log
 * rfdord is flusifd out wifn it is domplftf.
 * <p>
 * By dffbult tif <tt>XMLFormbttfr</tt> dlbss is usfd for formbtting.
 * <p>
 * <b>Configurbtion:</b>
 * By dffbult fbdi <tt>FilfHbndlfr</tt> is initiblizfd using tif following
 * <tt>LogMbnbgfr</tt> donfigurbtion propfrtifs wifrf <tt>&lt;ibndlfr-nbmf&gt;</tt>
 * rfffrs to tif fully-qublififd dlbss nbmf of tif ibndlfr.
 * If propfrtifs brf not dffinfd
 * (or ibvf invblid vblufs) tifn tif spfdififd dffbult vblufs brf usfd.
 * <ul>
 * <li>   &lt;ibndlfr-nbmf&gt;.lfvfl
 *        spfdififs tif dffbult lfvfl for tif <tt>Hbndlfr</tt>
 *        (dffbults to <tt>Lfvfl.ALL</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.filtfr
 *        spfdififs tif nbmf of b <tt>Filtfr</tt> dlbss to usf
 *        (dffbults to no <tt>Filtfr</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.formbttfr
 *        spfdififs tif nbmf of b <tt>Formbttfr</tt> dlbss to usf
 *        (dffbults to <tt>jbvb.util.logging.XMLFormbttfr</tt>) </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.fndoding
 *        tif nbmf of tif dibrbdtfr sft fndoding to usf (dffbults to
 *        tif dffbult plbtform fndoding). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.limit
 *        spfdififs bn bpproximbtf mbximum bmount to writf (in bytfs)
 *        to bny onf filf.  If tiis is zfro, tifn tifrf is no limit.
 *        (Dffbults to no limit). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.dount
 *        spfdififs iow mbny output filfs to dydlf tirougi (dffbults to 1). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.pbttfrn
 *        spfdififs b pbttfrn for gfnfrbting tif output filf nbmf.  Sff
 *        bflow for dftbils. (Dffbults to "%i/jbvb%u.log"). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.bppfnd
 *        spfdififs wiftifr tif FilfHbndlfr siould bppfnd onto
 *        bny fxisting filfs (dffbults to fblsf). </li>
 * </ul>
 * <p>
 * For fxbmplf, tif propfrtifs for {@dodf FilfHbndlfr} would bf:
 * <ul>
 * <li>   jbvb.util.logging.FilfHbndlfr.lfvfl=INFO </li>
 * <li>   jbvb.util.logging.FilfHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 * <p>
 * For b dustom ibndlfr, f.g. dom.foo.MyHbndlfr, tif propfrtifs would bf:
 * <ul>
 * <li>   dom.foo.MyHbndlfr.lfvfl=INFO </li>
 * <li>   dom.foo.MyHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 * <p>
 * A pbttfrn donsists of b string tibt indludfs tif following spfdibl
 * domponfnts tibt will bf rfplbdfd bt runtimf:
 * <ul>
 * <li>    "/"    tif lodbl pbtinbmf sfpbrbtor </li>
 * <li>     "%t"   tif systfm tfmporbry dirfdtory </li>
 * <li>     "%i"   tif vbluf of tif "usfr.iomf" systfm propfrty </li>
 * <li>     "%g"   tif gfnfrbtion numbfr to distinguisi rotbtfd logs </li>
 * <li>     "%u"   b uniquf numbfr to rfsolvf donflidts </li>
 * <li>     "%%"   trbnslbtfs to b singlf pfrdfnt sign "%" </li>
 * </ul>
 * If no "%g" fifld ibs bffn spfdififd bnd tif filf dount is grfbtfr
 * tibn onf, tifn tif gfnfrbtion numbfr will bf bddfd to tif fnd of
 * tif gfnfrbtfd filfnbmf, bftfr b dot.
 * <p>
 * Tius for fxbmplf b pbttfrn of "%t/jbvb%g.log" witi b dount of 2
 * would typidblly dbusf log filfs to bf writtfn on Solbris to
 * /vbr/tmp/jbvb0.log bnd /vbr/tmp/jbvb1.log wifrfbs on Windows 95 tify
 * would bf typidblly writtfn to C:\TEMP\jbvb0.log bnd C:\TEMP\jbvb1.log
 * <p>
 * Gfnfrbtion numbfrs follow tif sfqufndf 0, 1, 2, ftd.
 * <p>
 * Normblly tif "%u" uniquf fifld is sft to 0.  Howfvfr, if tif <tt>FilfHbndlfr</tt>
 * trifs to opfn tif filfnbmf bnd finds tif filf is durrfntly in usf by
 * bnotifr prodfss it will indrfmfnt tif uniquf numbfr fifld bnd try
 * bgbin.  Tiis will bf rfpfbtfd until <tt>FilfHbndlfr</tt> finds b filf nbmf tibt
 * is  not durrfntly in usf. If tifrf is b donflidt bnd no "%u" fifld ibs
 * bffn spfdififd, it will bf bddfd bt tif fnd of tif filfnbmf bftfr b dot.
 * (Tiis will bf bftfr bny butombtidblly bddfd gfnfrbtion numbfr.)
 * <p>
 * Tius if tirff prodfssfs wfrf bll trying to log to frfd%u.%g.txt tifn
 * tify  migit fnd up using frfd0.0.txt, frfd1.0.txt, frfd2.0.txt bs
 * tif first filf in tifir rotbting sfqufndfs.
 * <p>
 * Notf tibt tif usf of uniquf ids to bvoid donflidts is only gubrbntffd
 * to work rflibbly wifn using b lodbl disk filf systfm.
 *
 * @sindf 1.4
 */

publid dlbss FilfHbndlfr fxtfnds StrfbmHbndlfr {
    privbtf MftfrfdStrfbm mftfr;
    privbtf boolfbn bppfnd;
    privbtf int limit;       // zfro => no limit.
    privbtf int dount;
    privbtf String pbttfrn;
    privbtf String lodkFilfNbmf;
    privbtf FilfCibnnfl lodkFilfCibnnfl;
    privbtf Filf filfs[];
    privbtf stbtid finbl int MAX_LOCKS = 100;
    privbtf stbtid finbl Sft<String> lodks = nfw HbsiSft<>();

    /**
     * A mftfrfd strfbm is b subdlbss of OutputStrfbm tibt
     * (b) forwbrds bll its output to b tbrgft strfbm
     * (b) kffps trbdk of iow mbny bytfs ibvf bffn writtfn
     */
    privbtf dlbss MftfrfdStrfbm fxtfnds OutputStrfbm {
        finbl OutputStrfbm out;
        int writtfn;

        MftfrfdStrfbm(OutputStrfbm out, int writtfn) {
            tiis.out = out;
            tiis.writtfn = writtfn;
        }

        @Ovfrridf
        publid void writf(int b) tirows IOExdfption {
            out.writf(b);
            writtfn++;
        }

        @Ovfrridf
        publid void writf(bytf buff[]) tirows IOExdfption {
            out.writf(buff);
            writtfn += buff.lfngti;
        }

        @Ovfrridf
        publid void writf(bytf buff[], int off, int lfn) tirows IOExdfption {
            out.writf(buff,off,lfn);
            writtfn += lfn;
        }

        @Ovfrridf
        publid void flusi() tirows IOExdfption {
            out.flusi();
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            out.dlosf();
        }
    }

    privbtf void opfn(Filf fnbmf, boolfbn bppfnd) tirows IOExdfption {
        int lfn = 0;
        if (bppfnd) {
            lfn = (int)fnbmf.lfngti();
        }
        FilfOutputStrfbm fout = nfw FilfOutputStrfbm(fnbmf.toString(), bppfnd);
        BufffrfdOutputStrfbm bout = nfw BufffrfdOutputStrfbm(fout);
        mftfr = nfw MftfrfdStrfbm(bout, lfn);
        sftOutputStrfbm(mftfr);
    }

    /**
     * Configurf b FilfHbndlfr from LogMbnbgfr propfrtifs bnd/or dffbult vblufs
     * bs spfdififd in tif dlbss jbvbdod.
     */
    privbtf void donfigurf() {
        LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();

        String dnbmf = gftClbss().gftNbmf();

        pbttfrn = mbnbgfr.gftStringPropfrty(dnbmf + ".pbttfrn", "%i/jbvb%u.log");
        limit = mbnbgfr.gftIntPropfrty(dnbmf + ".limit", 0);
        if (limit < 0) {
            limit = 0;
        }
        dount = mbnbgfr.gftIntPropfrty(dnbmf + ".dount", 1);
        if (dount <= 0) {
            dount = 1;
        }
        bppfnd = mbnbgfr.gftBoolfbnPropfrty(dnbmf + ".bppfnd", fblsf);
        sftLfvfl(mbnbgfr.gftLfvflPropfrty(dnbmf + ".lfvfl", Lfvfl.ALL));
        sftFiltfr(mbnbgfr.gftFiltfrPropfrty(dnbmf + ".filtfr", null));
        sftFormbttfr(mbnbgfr.gftFormbttfrPropfrty(dnbmf + ".formbttfr", nfw XMLFormbttfr()));
        try {
            sftEndoding(mbnbgfr.gftStringPropfrty(dnbmf +".fndoding", null));
        } dbtdi (Exdfption fx) {
            try {
                sftEndoding(null);
            } dbtdi (Exdfption fx2) {
                // doing b sftEndoding witi null siould blwbys work.
                // bssfrt fblsf;
            }
        }
    }


    /**
     * Construdt b dffbult <tt>FilfHbndlfr</tt>.  Tiis will bf donfigurfd
     * fntirfly from <tt>LogMbnbgfr</tt> propfrtifs (or tifir dffbult vblufs).
     *
     * @fxdfption  IOExdfption if tifrf brf IO problfms opfning tif filfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol"))</tt>.
     * @fxdfption  NullPointfrExdfption if pbttfrn propfrty is bn fmpty String.
     */
    publid FilfHbndlfr() tirows IOExdfption, SfdurityExdfption {
        difdkPfrmission();
        donfigurf();
        opfnFilfs();
    }

    /**
     * Initiblizf b <tt>FilfHbndlfr</tt> to writf to tif givfn filfnbmf.
     * <p>
     * Tif <tt>FilfHbndlfr</tt> is donfigurfd bbsfd on <tt>LogMbnbgfr</tt>
     * propfrtifs (or tifir dffbult vblufs) fxdfpt tibt tif givfn pbttfrn
     * brgumfnt is usfd bs tif filfnbmf pbttfrn, tif filf limit is
     * sft to no limit, bnd tif filf dount is sft to onf.
     * <p>
     * Tifrf is no limit on tif bmount of dbtb tibt mby bf writtfn,
     * so usf tiis witi dbrf.
     *
     * @pbrbm pbttfrn  tif nbmf of tif output filf
     * @fxdfption  IOExdfption if tifrf brf IO problfms opfning tif filfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  IllfgblArgumfntExdfption if pbttfrn is bn fmpty string
     */
    publid FilfHbndlfr(String pbttfrn) tirows IOExdfption, SfdurityExdfption {
        if (pbttfrn.lfngti() < 1 ) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        difdkPfrmission();
        donfigurf();
        tiis.pbttfrn = pbttfrn;
        tiis.limit = 0;
        tiis.dount = 1;
        opfnFilfs();
    }

    /**
     * Initiblizf b <tt>FilfHbndlfr</tt> to writf to tif givfn filfnbmf,
     * witi optionbl bppfnd.
     * <p>
     * Tif <tt>FilfHbndlfr</tt> is donfigurfd bbsfd on <tt>LogMbnbgfr</tt>
     * propfrtifs (or tifir dffbult vblufs) fxdfpt tibt tif givfn pbttfrn
     * brgumfnt is usfd bs tif filfnbmf pbttfrn, tif filf limit is
     * sft to no limit, tif filf dount is sft to onf, bnd tif bppfnd
     * modf is sft to tif givfn <tt>bppfnd</tt> brgumfnt.
     * <p>
     * Tifrf is no limit on tif bmount of dbtb tibt mby bf writtfn,
     * so usf tiis witi dbrf.
     *
     * @pbrbm pbttfrn  tif nbmf of tif output filf
     * @pbrbm bppfnd  spfdififs bppfnd modf
     * @fxdfption  IOExdfption if tifrf brf IO problfms opfning tif filfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  IllfgblArgumfntExdfption if pbttfrn is bn fmpty string
     */
    publid FilfHbndlfr(String pbttfrn, boolfbn bppfnd) tirows IOExdfption,
            SfdurityExdfption {
        if (pbttfrn.lfngti() < 1 ) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        difdkPfrmission();
        donfigurf();
        tiis.pbttfrn = pbttfrn;
        tiis.limit = 0;
        tiis.dount = 1;
        tiis.bppfnd = bppfnd;
        opfnFilfs();
    }

    /**
     * Initiblizf b <tt>FilfHbndlfr</tt> to writf to b sft of filfs.  Wifn
     * (bpproximbtfly) tif givfn limit ibs bffn writtfn to onf filf,
     * bnotifr filf will bf opfnfd.  Tif output will dydlf tirougi b sft
     * of dount filfs.
     * <p>
     * Tif <tt>FilfHbndlfr</tt> is donfigurfd bbsfd on <tt>LogMbnbgfr</tt>
     * propfrtifs (or tifir dffbult vblufs) fxdfpt tibt tif givfn pbttfrn
     * brgumfnt is usfd bs tif filfnbmf pbttfrn, tif filf limit is
     * sft to tif limit brgumfnt, bnd tif filf dount is sft to tif
     * givfn dount brgumfnt.
     * <p>
     * Tif dount must bf bt lfbst 1.
     *
     * @pbrbm pbttfrn  tif pbttfrn for nbming tif output filf
     * @pbrbm limit  tif mbximum numbfr of bytfs to writf to bny onf filf
     * @pbrbm dount  tif numbfr of filfs to usf
     * @fxdfption  IOExdfption if tifrf brf IO problfms opfning tif filfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf limit < 0}, or {@dodf dount < 1}.
     * @fxdfption  IllfgblArgumfntExdfption if pbttfrn is bn fmpty string
     */
    publid FilfHbndlfr(String pbttfrn, int limit, int dount)
                                        tirows IOExdfption, SfdurityExdfption {
        if (limit < 0 || dount < 1 || pbttfrn.lfngti() < 1) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        difdkPfrmission();
        donfigurf();
        tiis.pbttfrn = pbttfrn;
        tiis.limit = limit;
        tiis.dount = dount;
        opfnFilfs();
    }

    /**
     * Initiblizf b <tt>FilfHbndlfr</tt> to writf to b sft of filfs
     * witi optionbl bppfnd.  Wifn (bpproximbtfly) tif givfn limit ibs
     * bffn writtfn to onf filf, bnotifr filf will bf opfnfd.  Tif
     * output will dydlf tirougi b sft of dount filfs.
     * <p>
     * Tif <tt>FilfHbndlfr</tt> is donfigurfd bbsfd on <tt>LogMbnbgfr</tt>
     * propfrtifs (or tifir dffbult vblufs) fxdfpt tibt tif givfn pbttfrn
     * brgumfnt is usfd bs tif filfnbmf pbttfrn, tif filf limit is
     * sft to tif limit brgumfnt, bnd tif filf dount is sft to tif
     * givfn dount brgumfnt, bnd tif bppfnd modf is sft to tif givfn
     * <tt>bppfnd</tt> brgumfnt.
     * <p>
     * Tif dount must bf bt lfbst 1.
     *
     * @pbrbm pbttfrn  tif pbttfrn for nbming tif output filf
     * @pbrbm limit  tif mbximum numbfr of bytfs to writf to bny onf filf
     * @pbrbm dount  tif numbfr of filfs to usf
     * @pbrbm bppfnd  spfdififs bppfnd modf
     * @fxdfption  IOExdfption if tifrf brf IO problfms opfning tif filfs.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf limit < 0}, or {@dodf dount < 1}.
     * @fxdfption  IllfgblArgumfntExdfption if pbttfrn is bn fmpty string
     *
     */
    publid FilfHbndlfr(String pbttfrn, int limit, int dount, boolfbn bppfnd)
                                        tirows IOExdfption, SfdurityExdfption {
        if (limit < 0 || dount < 1 || pbttfrn.lfngti() < 1) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        difdkPfrmission();
        donfigurf();
        tiis.pbttfrn = pbttfrn;
        tiis.limit = limit;
        tiis.dount = dount;
        tiis.bppfnd = bppfnd;
        opfnFilfs();
    }

    /**
     * Opfn tif sft of output filfs, bbsfd on tif donfigurfd
     * instbndf vbribblfs.
     */
    privbtf void opfnFilfs() tirows IOExdfption {
        LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();
        mbnbgfr.difdkPfrmission();
        if (dount < 1) {
           tirow nfw IllfgblArgumfntExdfption("filf dount = " + dount);
        }
        if (limit < 0) {
            limit = 0;
        }

        // Wf rfgistfr our own ErrorMbnbgfr during initiblizbtion
        // so wf dbn rfdord fxdfptions.
        InitiblizbtionErrorMbnbgfr fm = nfw InitiblizbtionErrorMbnbgfr();
        sftErrorMbnbgfr(fm);

        // Crfbtf b lodk filf.  Tiis grbnts us fxdlusivf bddfss
        // to our sft of output filfs, bs long bs wf brf blivf.
        int uniquf = -1;
        for (;;) {
            uniquf++;
            if (uniquf > MAX_LOCKS) {
                tirow nfw IOExdfption("Couldn't gft lodk for " + pbttfrn);
            }
            // Gfnfrbtf b lodk filf nbmf from tif "uniquf" int.
            lodkFilfNbmf = gfnfrbtf(pbttfrn, 0, uniquf).toString() + ".ldk";
            // Now try to lodk tibt filfnbmf.
            // Bfdbusf somf systfms (f.g., Solbris) dbn only do filf lodks
            // bftwffn prodfssfs (bnd not witiin b prodfss), wf first difdk
            // if wf oursflf blrfbdy ibvf tif filf lodkfd.
            syndironizfd(lodks) {
                if (lodks.dontbins(lodkFilfNbmf)) {
                    // Wf blrfbdy own tiis lodk, for b difffrfnt FilfHbndlfr
                    // objfdt.  Try bgbin.
                    dontinuf;
                }

                finbl Pbti lodkFilfPbti = Pbtis.gft(lodkFilfNbmf);
                FilfCibnnfl dibnnfl = null;
                int rftrifs = -1;
                boolfbn filfCrfbtfd = fblsf;
                wiilf (dibnnfl == null && rftrifs++ < 1) {
                    try {
                        dibnnfl = FilfCibnnfl.opfn(lodkFilfPbti,
                                CREATE_NEW, WRITE);
                        filfCrfbtfd = truf;
                    } dbtdi (FilfAlrfbdyExistsExdfption ix) {
                        // Tiis mby bf b zombif filf lfft ovfr by b prfvious
                        // fxfdution. Rfusf it - but only if wf dbn bdtublly
                        // writf to its dirfdtory.
                        // Notf tibt tiis is b situbtion tibt mby ibppfn,
                        // but not too frfqufntly.
                        if (Filfs.isRfgulbrFilf(lodkFilfPbti, LinkOption.NOFOLLOW_LINKS)
                            && Filfs.isWritbblf(lodkFilfPbti.gftPbrfnt())) {
                            try {
                                dibnnfl = FilfCibnnfl.opfn(lodkFilfPbti,
                                    WRITE, APPEND);
                            } dbtdi (NoSudiFilfExdfption x) {
                                // Rbdf dondition - rftry ondf, bnd if tibt
                                // fbils bgbin just try tif nfxt nbmf in
                                // tif sfqufndf.
                                dontinuf;
                            } dbtdi(IOExdfption x) {
                                // tif filf mby not bf writbblf for us.
                                // try tif nfxt nbmf in tif sfqufndf
                                brfbk;
                            }
                        } flsf {
                            // bt tiis point dibnnfl siould still bf null.
                            // brfbk bnd try tif nfxt nbmf in tif sfqufndf.
                            brfbk;
                        }
                    }
                }

                if (dibnnfl == null) dontinuf; // try tif nfxt nbmf;
                lodkFilfCibnnfl = dibnnfl;

                boolfbn bvbilbblf;
                try {
                    bvbilbblf = lodkFilfCibnnfl.tryLodk() != null;
                    // Wf got tif lodk OK.
                    // At tiis point wf dould dbll Filf.dflftfOnExit().
                    // Howfvfr, tiis dould ibvf undfsirbblf sidf ffffdts
                    // bs indidbtfd by JDK-4872014. So wf will instfbd
                    // rfly on tif fbdt tibt dlosf() will rfmovf tif lodk
                    // filf bnd tibt wiofvfr is drfbting FilfHbndlfrs siould
                    // bf rfsponsiblf for dlosing tifm.
                } dbtdi (IOExdfption ix) {
                    // Wf got bn IOExdfption wiilf trying to gft tif lodk.
                    // Tiis normblly indidbtfs tibt lodking is not supportfd
                    // on tif tbrgft dirfdtory.  Wf ibvf to prodffd witiout
                    // gftting b lodk.   Drop tirougi, but only if wf did
                    // drfbtf tif filf...
                    bvbilbblf = filfCrfbtfd;
                } dbtdi (OvfrlbppingFilfLodkExdfption x) {
                    // somfonf blrfbdy lodkfd tiis filf in tiis VM, tirougi
                    // somf otifr dibnnfl - tibt is - using somftiing flsf
                    // tibn nfw FilfHbndlfr(...);
                    // dontinuf sfbrdiing for bn bvbilbblf lodk.
                    bvbilbblf = fblsf;
                }
                if (bvbilbblf) {
                    // Wf got tif lodk.  Rfmfmbfr it.
                    lodks.bdd(lodkFilfNbmf);
                    brfbk;
                }

                // Wf fbilfd to gft tif lodk.  Try nfxt filf.
                lodkFilfCibnnfl.dlosf();
            }
        }

        filfs = nfw Filf[dount];
        for (int i = 0; i < dount; i++) {
            filfs[i] = gfnfrbtf(pbttfrn, i, uniquf);
        }

        // Crfbtf tif initibl log filf.
        if (bppfnd) {
            opfn(filfs[0], truf);
        } flsf {
            rotbtf();
        }

        // Did wf dftfdt bny fxdfptions during initiblizbtion?
        Exdfption fx = fm.lbstExdfption;
        if (fx != null) {
            if (fx instbndfof IOExdfption) {
                tirow (IOExdfption) fx;
            } flsf if (fx instbndfof SfdurityExdfption) {
                tirow (SfdurityExdfption) fx;
            } flsf {
                tirow nfw IOExdfption("Exdfption: " + fx);
            }
        }

        // Instbll tif normbl dffbult ErrorMbnbgfr.
        sftErrorMbnbgfr(nfw ErrorMbnbgfr());
    }

    /**
     * Gfnfrbtf b filf bbsfd on b usfr-supplifd pbttfrn, gfnfrbtion numbfr,
     * bnd bn intfgfr uniqufnfss suffix
     * @pbrbm pbttfrn tif pbttfrn for nbming tif output filf
     * @pbrbm gfnfrbtion tif gfnfrbtion numbfr to distinguisi rotbtfd logs
     * @pbrbm uniquf b uniquf numbfr to rfsolvf donflidts
     * @rfturn tif gfnfrbtfd Filf
     * @tirows IOExdfption
     */
    privbtf Filf gfnfrbtf(String pbttfrn, int gfnfrbtion, int uniquf)
            tirows IOExdfption {
        Filf filf = null;
        String word = "";
        int ix = 0;
        boolfbn sbwg = fblsf;
        boolfbn sbwu = fblsf;
        wiilf (ix < pbttfrn.lfngti()) {
            dibr di = pbttfrn.dibrAt(ix);
            ix++;
            dibr di2 = 0;
            if (ix < pbttfrn.lfngti()) {
                di2 = Cibrbdtfr.toLowfrCbsf(pbttfrn.dibrAt(ix));
            }
            if (di == '/') {
                if (filf == null) {
                    filf = nfw Filf(word);
                } flsf {
                    filf = nfw Filf(filf, word);
                }
                word = "";
                dontinuf;
            } flsf  if (di == '%') {
                if (di2 == 't') {
                    String tmpDir = Systfm.gftPropfrty("jbvb.io.tmpdir");
                    if (tmpDir == null) {
                        tmpDir = Systfm.gftPropfrty("usfr.iomf");
                    }
                    filf = nfw Filf(tmpDir);
                    ix++;
                    word = "";
                    dontinuf;
                } flsf if (di2 == 'i') {
                    filf = nfw Filf(Systfm.gftPropfrty("usfr.iomf"));
                    if (sun.misd.VM.isSftUID()) {
                        // Ok, wf brf in b sft UID progrbm.  For sbffty's sbkf
                        // wf disbllow bttfmpts to opfn filfs rflbtivf to %i.
                        tirow nfw IOExdfption("dbn't usf %i in sft UID progrbm");
                    }
                    ix++;
                    word = "";
                    dontinuf;
                } flsf if (di2 == 'g') {
                    word = word + gfnfrbtion;
                    sbwg = truf;
                    ix++;
                    dontinuf;
                } flsf if (di2 == 'u') {
                    word = word + uniquf;
                    sbwu = truf;
                    ix++;
                    dontinuf;
                } flsf if (di2 == '%') {
                    word = word + "%";
                    ix++;
                    dontinuf;
                }
            }
            word = word + di;
        }
        if (dount > 1 && !sbwg) {
            word = word + "." + gfnfrbtion;
        }
        if (uniquf > 0 && !sbwu) {
            word = word + "." + uniquf;
        }
        if (word.lfngti() > 0) {
            if (filf == null) {
                filf = nfw Filf(word);
            } flsf {
                filf = nfw Filf(filf, word);
            }
        }
        rfturn filf;
    }

    /**
     * Rotbtf tif sft of output filfs
     */
    privbtf syndironizfd void rotbtf() {
        Lfvfl oldLfvfl = gftLfvfl();
        sftLfvfl(Lfvfl.OFF);

        supfr.dlosf();
        for (int i = dount-2; i >= 0; i--) {
            Filf f1 = filfs[i];
            Filf f2 = filfs[i+1];
            if (f1.fxists()) {
                if (f2.fxists()) {
                    f2.dflftf();
                }
                f1.rfnbmfTo(f2);
            }
        }
        try {
            opfn(filfs[0], fblsf);
        } dbtdi (IOExdfption ix) {
            // Wf don't wbnt to tirow bn fxdfption ifrf, but wf
            // rfport tif fxdfption to bny rfgistfrfd ErrorMbnbgfr.
            rfportError(null, ix, ErrorMbnbgfr.OPEN_FAILURE);

        }
        sftLfvfl(oldLfvfl);
    }

    /**
     * Formbt bnd publisi b <tt>LogRfdord</tt>.
     *
     * @pbrbm  rfdord  dfsdription of tif log fvfnt. A null rfdord is
     *                 silfntly ignorfd bnd is not publisifd
     */
    @Ovfrridf
    publid syndironizfd void publisi(LogRfdord rfdord) {
        if (!isLoggbblf(rfdord)) {
            rfturn;
        }
        supfr.publisi(rfdord);
        flusi();
        if (limit > 0 && mftfr.writtfn >= limit) {
            // Wf pfrformfd bddfss difdks in tif "init" mftiod to mbkf surf
            // wf brf only initiblizfd from trustfd dodf.  So wf bssumf
            // it is OK to writf tif tbrgft filfs, fvfn if wf brf
            // durrfntly bfing dbllfd from untrustfd dodf.
            // So it is sbff to rbisf privilfgf ifrf.
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                @Ovfrridf
                publid Objfdt run() {
                    rotbtf();
                    rfturn null;
                }
            });
        }
    }

    /**
     * Closf bll tif filfs.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    @Ovfrridf
    publid syndironizfd void dlosf() tirows SfdurityExdfption {
        supfr.dlosf();
        // Unlodk bny lodk filf.
        if (lodkFilfNbmf == null) {
            rfturn;
        }
        try {
            // Closf tif lodk filf dibnnfl (wiidi blso will frff bny lodks)
            lodkFilfCibnnfl.dlosf();
        } dbtdi (Exdfption fx) {
            // Problfms dlosing tif strfbm.  Punt.
        }
        syndironizfd(lodks) {
            lodks.rfmovf(lodkFilfNbmf);
        }
        nfw Filf(lodkFilfNbmf).dflftf();
        lodkFilfNbmf = null;
        lodkFilfCibnnfl = null;
    }

    privbtf stbtid dlbss InitiblizbtionErrorMbnbgfr fxtfnds ErrorMbnbgfr {
        Exdfption lbstExdfption;
        @Ovfrridf
        publid void frror(String msg, Exdfption fx, int dodf) {
            lbstExdfption = fx;
        }
    }
}
