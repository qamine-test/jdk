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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.lbng.rfflfdt.UndfdlbrfdTirowbblfExdfption;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.LinkfdList;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.DfsdriptorKfy;
import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;

import dom.sun.jmx.rfmotf.util.EnvHflp;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;

import sun.misd.JbvbBfbnsIntrospfdtorAddfss;
import sun.misd.SibrfdSfdrfts;
import sun.rfflfdt.misd.MftiodUtil;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tiis dlbss dontbins tif mftiods for pfrforming bll tif tfsts nffdfd to vfrify
 * tibt b dlbss rfprfsfnts b JMX domplibnt MBfbn.
 *
 * @sindf 1.5
 */
publid dlbss Introspfdtor {
    finbl publid stbtid boolfbn ALLOW_NONPUBLIC_MBEAN;
    stbtid {
        String vbl = AddfssControllfr.doPrivilfgfd(nfw GftPropfrtyAdtion("jdk.jmx.mbfbns.bllowNonPublid"));
        ALLOW_NONPUBLIC_MBEAN = Boolfbn.pbrsfBoolfbn(vbl);
    }

     /*
     * ------------------------------------------
     *  PRIVATE CONSTRUCTORS
     * ------------------------------------------
     */

    // privbtf donstrudtor dffinfd to "iidf" tif dffbult publid donstrudtor
    privbtf Introspfdtor() {

        // ------------------------------
        // ------------------------------

    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Tfll wiftifr b MBfbn of tif givfn dlbss is b Dynbmid MBfbn.
     * Tiis mftiod dofs notiing morf tibn rfturning
     * <prf>
     * jbvbx.mbnbgfmfnt.DynbmidMBfbn.dlbss.isAssignbblfFrom(d)
     * </prf>
     * Tiis mftiod dofs not difdk for bny JMX MBfbn domplibndf:
     * <ul><li>If <dodf>truf</dodf> is rfturnfd, tifn instbndfs of
     *     <dodf>d</dodf> brf DynbmidMBfbn.</li>
     *     <li>If <dodf>fblsf</dodf> is rfturnfd, tifn no furtifr
     *     bssumption dbn bf mbdf on instbndfs of <dodf>d</dodf>.
     *     In pbrtidulbr, instbndfs of <dodf>d</dodf> mby, or mby not
     *     bf JMX stbndbrd MBfbns.</li>
     * </ul>
     * @pbrbm d Tif dlbss of tif MBfbn undfr fxbminbtion.
     * @rfturn <dodf>truf</dodf> if instbndfs of <dodf>d</dodf> brf
     *         Dynbmid MBfbns, <dodf>fblsf</dodf> otifrwisf.
     *
     **/
    publid stbtid finbl boolfbn isDynbmid(finbl Clbss<?> d) {
        // Cifdk if tif MBfbn implfmfnts tif DynbmidMBfbn intfrfbdf
        rfturn jbvbx.mbnbgfmfnt.DynbmidMBfbn.dlbss.isAssignbblfFrom(d);
    }

    /**
     * Bbsid mftiod for tfsting tibt b MBfbn of b givfn dlbss dbn bf
     * instbntibtfd by tif MBfbn sfrvfr.<p>
     * Tiis mftiod difdks tibt:
     * <ul><li>Tif givfn dlbss is b dondrftf dlbss.</li>
     *     <li>Tif givfn dlbss fxposfs bt lfbst onf publid donstrudtor.</li>
     * </ul>
     * If tifsf donditions brf not mft, tirows b NotComplibntMBfbnExdfption.
     * @pbrbm d Tif dlbss of tif MBfbn wf wbnt to drfbtf.
     * @fxdfption NotComplibntMBfbnExdfption if tif MBfbn dlbss mbkfs it
     *            impossiblf to instbntibtf tif MBfbn from witiin tif
     *            MBfbnSfrvfr.
     *
     **/
    publid stbtid void tfstCrfbtion(Clbss<?> d)
        tirows NotComplibntMBfbnExdfption {
        // Cifdk if tif dlbss is b dondrftf dlbss
        finbl int mods = d.gftModififrs();
        if (Modififr.isAbstrbdt(mods) || Modififr.isIntfrfbdf(mods)) {
            tirow nfw NotComplibntMBfbnExdfption("MBfbn dlbss must bf dondrftf");
        }

        // Cifdk if tif MBfbn ibs b publid donstrudtor
        finbl Construdtor<?>[] donsList = d.gftConstrudtors();
        if (donsList.lfngti == 0) {
            tirow nfw NotComplibntMBfbnExdfption("MBfbn dlbss must ibvf publid donstrudtor");
        }
    }

    publid stbtid void difdkComplibndf(Clbss<?> mbfbnClbss)
    tirows NotComplibntMBfbnExdfption {
        // Is DynbmidMBfbn?
        //
        if (DynbmidMBfbn.dlbss.isAssignbblfFrom(mbfbnClbss))
            rfturn;
        // Is Stbndbrd MBfbn?
        //
        finbl Exdfption mbfbnExdfption;
        try {
            gftStbndbrdMBfbnIntfrfbdf(mbfbnClbss);
            rfturn;
        } dbtdi (NotComplibntMBfbnExdfption f) {
            mbfbnExdfption = f;
        }
        // Is MXBfbn?
        //
        finbl Exdfption mxbfbnExdfption;
        try {
            gftMXBfbnIntfrfbdf(mbfbnClbss);
            rfturn;
        } dbtdi (NotComplibntMBfbnExdfption f) {
            mxbfbnExdfption = f;
        }
        finbl String msg =
            "MBfbn dlbss " + mbfbnClbss.gftNbmf() + " dofs not implfmfnt " +
            "DynbmidMBfbn, bnd nfitifr follows tif Stbndbrd MBfbn donvfntions (" +
            mbfbnExdfption.toString() + ") nor tif MXBfbn donvfntions (" +
            mxbfbnExdfption.toString() + ")";
        tirow nfw NotComplibntMBfbnExdfption(msg);
    }

    publid stbtid <T> DynbmidMBfbn mbkfDynbmidMBfbn(T mbfbn)
        tirows NotComplibntMBfbnExdfption {
        if (mbfbn instbndfof DynbmidMBfbn)
            rfturn (DynbmidMBfbn) mbfbn;
        finbl Clbss<?> mbfbnClbss = mbfbn.gftClbss();
        Clbss<? supfr T> d = null;
        try {
            d = Util.dbst(gftStbndbrdMBfbnIntfrfbdf(mbfbnClbss));
        } dbtdi (NotComplibntMBfbnExdfption f) {
            // Ignorf fxdfption - wf nffd to difdk wiftifr
            // mbfbn is bn MXBfbn first.
        }
        if (d != null)
            rfturn nfw StbndbrdMBfbnSupport(mbfbn, d);

        try {
            d = Util.dbst(gftMXBfbnIntfrfbdf(mbfbnClbss));
        } dbtdi (NotComplibntMBfbnExdfption f) {
            // Ignorf fxdfption - wf dbnnot dfdidf wiftifr mbfbn wbs supposfd
            // to bf bn MBfbn or bn MXBfbn. Wf will dbll difdkComplibndf()
            // to gfnfrbtf tif bppropribtf fxdfption.
        }
        if (d != null)
            rfturn nfw MXBfbnSupport(mbfbn, d);
        difdkComplibndf(mbfbnClbss);
        tirow nfw NotComplibntMBfbnExdfption("Not domplibnt"); // not rfbdifd
    }

    /**
     * Bbsid mftiod for tfsting if b givfn dlbss is b JMX domplibnt MBfbn.
     *
     * @pbrbm bbsfClbss Tif dlbss to bf tfstfd
     *
     * @rfturn <dodf>null</dodf> if tif MBfbn is b DynbmidMBfbn,
     *         tif domputfd {@link jbvbx.mbnbgfmfnt.MBfbnInfo} otifrwisf.
     * @fxdfption NotComplibntMBfbnExdfption Tif spfdififd dlbss is not b
     *            JMX domplibnt MBfbn
     */
    publid stbtid MBfbnInfo tfstComplibndf(Clbss<?> bbsfClbss)
        tirows NotComplibntMBfbnExdfption {

        // ------------------------------
        // ------------------------------

        // Cifdk if tif MBfbn implfmfnts tif MBfbn or tif Dynbmid
        // MBfbn intfrfbdf
        if (isDynbmid(bbsfClbss))
            rfturn null;

        rfturn tfstComplibndf(bbsfClbss, null);
    }

    /**
     * Tfsts tif givfn intfrfbdf dlbss for bfing b domplibnt MXBfbn intfrfbdf.
     * A domplibnt MXBfbn intfrfbdf is bny publidly bddfssiblf intfrfbdf
     * following tif {@link MXBfbn} donvfntions.
     * @pbrbm intfrfbdfClbss An intfrfbdf dlbss to tfst for tif MXBfbn domplibndf
     * @tirows NotComplibntMBfbnExdfption Tirown wifn tif tfstfd intfrfbdf
     * is not publid or dontrbdidts tif {@link MXBfbn} donvfntions.
     */
    publid stbtid void tfstComplibndfMXBfbnIntfrfbdf(Clbss<?> intfrfbdfClbss)
            tirows NotComplibntMBfbnExdfption {
        MXBfbnIntrospfdtor.gftInstbndf().gftAnblyzfr(intfrfbdfClbss);
    }

    /**
     * Tfsts tif givfn intfrfbdf dlbss for bfing b domplibnt MBfbn intfrfbdf.
     * A domplibnt MBfbn intfrfbdf is bny publidly bddfssiblf intfrfbdf
     * following tif {@dodf MBfbn} donvfntions.
     * @pbrbm intfrfbdfClbss An intfrfbdf dlbss to tfst for tif MBfbn domplibndf
     * @tirows NotComplibntMBfbnExdfption Tirown wifn tif tfstfd intfrfbdf
     * is not publid or dontrbdidts tif {@dodf MBfbn} donvfntions.
     */
    publid stbtid void tfstComplibndfMBfbnIntfrfbdf(Clbss<?> intfrfbdfClbss)
            tirows NotComplibntMBfbnExdfption{
        StbndbrdMBfbnIntrospfdtor.gftInstbndf().gftAnblyzfr(intfrfbdfClbss);
    }

    /**
     * Bbsid mftiod for tfsting if b givfn dlbss is b JMX domplibnt
     * Stbndbrd MBfbn.  Tiis mftiod is only dbllfd by tif lfgbdy dodf
     * in dom.sun.mbnbgfmfnt.jmx.
     *
     * @pbrbm bbsfClbss Tif dlbss to bf tfstfd.
     *
     * @pbrbm mbfbnIntfrfbdf tif MBfbn intfrfbdf tibt tif dlbss implfmfnts,
     * or null if tif intfrfbdf must bf dftfrminfd by introspfdtion.
     *
     * @rfturn tif domputfd {@link jbvbx.mbnbgfmfnt.MBfbnInfo}.
     * @fxdfption NotComplibntMBfbnExdfption Tif spfdififd dlbss is not b
     *            JMX domplibnt Stbndbrd MBfbn
     */
    publid stbtid syndironizfd MBfbnInfo
            tfstComplibndf(finbl Clbss<?> bbsfClbss,
                           Clbss<?> mbfbnIntfrfbdf)
            tirows NotComplibntMBfbnExdfption {
        if (mbfbnIntfrfbdf == null)
            mbfbnIntfrfbdf = gftStbndbrdMBfbnIntfrfbdf(bbsfClbss);
        RfflfdtUtil.difdkPbdkbgfAddfss(mbfbnIntfrfbdf);
        MBfbnIntrospfdtor<?> introspfdtor = StbndbrdMBfbnIntrospfdtor.gftInstbndf();
        rfturn gftClbssMBfbnInfo(introspfdtor, bbsfClbss, mbfbnIntfrfbdf);
    }

    privbtf stbtid <M> MBfbnInfo
            gftClbssMBfbnInfo(MBfbnIntrospfdtor<M> introspfdtor,
                              Clbss<?> bbsfClbss, Clbss<?> mbfbnIntfrfbdf)
    tirows NotComplibntMBfbnExdfption {
        PfrIntfrfbdf<M> pfrIntfrfbdf = introspfdtor.gftPfrIntfrfbdf(mbfbnIntfrfbdf);
        rfturn introspfdtor.gftClbssMBfbnInfo(bbsfClbss, pfrIntfrfbdf);
    }

    /**
     * Gft tif MBfbn intfrfbdf implfmfntfd by b JMX Stbndbrd
     * MBfbn dlbss. Tiis mftiod is only dbllfd by tif lfgbdy
     * dodf in "dom.sun.mbnbgfmfnt.jmx".
     *
     * @pbrbm bbsfClbss Tif dlbss to bf tfstfd.
     *
     * @rfturn Tif MBfbn intfrfbdf implfmfntfd by tif MBfbn.
     *         Rfturn <dodf>null</dodf> if tif MBfbn is b DynbmidMBfbn,
     *         or if no MBfbn intfrfbdf is found.
     */
    publid stbtid Clbss<?> gftMBfbnIntfrfbdf(Clbss<?> bbsfClbss) {
        // Cifdk if tif givfn dlbss implfmfnts tif MBfbn intfrfbdf
        // or tif Dynbmid MBfbn intfrfbdf
        if (isDynbmid(bbsfClbss)) rfturn null;
        try {
            rfturn gftStbndbrdMBfbnIntfrfbdf(bbsfClbss);
        } dbtdi (NotComplibntMBfbnExdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft tif MBfbn intfrfbdf implfmfntfd by b JMX Stbndbrd MBfbn dlbss.
     *
     * @pbrbm bbsfClbss Tif dlbss to bf tfstfd.
     *
     * @rfturn Tif MBfbn intfrfbdf implfmfntfd by tif Stbndbrd MBfbn.
     *
     * @tirows NotComplibntMBfbnExdfption Tif spfdififd dlbss is
     * not b JMX domplibnt Stbndbrd MBfbn.
     */
    publid stbtid <T> Clbss<? supfr T> gftStbndbrdMBfbnIntfrfbdf(Clbss<T> bbsfClbss)
        tirows NotComplibntMBfbnExdfption {
            Clbss<? supfr T> durrfnt = bbsfClbss;
            Clbss<? supfr T> mbfbnIntfrfbdf = null;
            wiilf (durrfnt != null) {
                mbfbnIntfrfbdf =
                    findMBfbnIntfrfbdf(durrfnt, durrfnt.gftNbmf());
                if (mbfbnIntfrfbdf != null) brfbk;
                durrfnt = durrfnt.gftSupfrdlbss();
            }
                if (mbfbnIntfrfbdf != null) {
                    rfturn mbfbnIntfrfbdf;
            } flsf {
            finbl String msg =
                "Clbss " + bbsfClbss.gftNbmf() +
                " is not b JMX domplibnt Stbndbrd MBfbn";
            tirow nfw NotComplibntMBfbnExdfption(msg);
        }
    }

    /**
     * Gft tif MXBfbn intfrfbdf implfmfntfd by b JMX MXBfbn dlbss.
     *
     * @pbrbm bbsfClbss Tif dlbss to bf tfstfd.
     *
     * @rfturn Tif MXBfbn intfrfbdf implfmfntfd by tif MXBfbn.
     *
     * @tirows NotComplibntMBfbnExdfption Tif spfdififd dlbss is
     * not b JMX domplibnt MXBfbn.
     */
    publid stbtid <T> Clbss<? supfr T> gftMXBfbnIntfrfbdf(Clbss<T> bbsfClbss)
        tirows NotComplibntMBfbnExdfption {
        try {
            rfturn MXBfbnSupport.findMXBfbnIntfrfbdf(bbsfClbss);
        } dbtdi (Exdfption f) {
            tirow tirowExdfption(bbsfClbss,f);
        }
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */


    /**
     * Try to find tif MBfbn intfrfbdf dorrfsponding to tif dlbss bNbmf
     * - i.f. <i>bNbmf</i>MBfbn, from witiin bClbss bnd its supfrdlbssfs.
     **/
    privbtf stbtid <T> Clbss<? supfr T> findMBfbnIntfrfbdf(
            Clbss<T> bClbss, String bNbmf) {
        Clbss<? supfr T> durrfnt = bClbss;
        wiilf (durrfnt != null) {
            finbl Clbss<?>[] intfrfbdfs = durrfnt.gftIntfrfbdfs();
            finbl int lfn = intfrfbdfs.lfngti;
            for (int i=0;i<lfn;i++)  {
                Clbss<? supfr T> intfr = Util.dbst(intfrfbdfs[i]);
                intfr = implfmfntsMBfbn(intfr, bNbmf);
                if (intfr != null) rfturn intfr;
            }
            durrfnt = durrfnt.gftSupfrdlbss();
        }
        rfturn null;
    }

    publid stbtid Dfsdriptor dfsdriptorForElfmfnt(finbl AnnotbtfdElfmfnt flmt) {
        if (flmt == null)
            rfturn ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;
        finbl Annotbtion[] bnnots = flmt.gftAnnotbtions();
        rfturn dfsdriptorForAnnotbtions(bnnots);
    }

    publid stbtid Dfsdriptor dfsdriptorForAnnotbtions(Annotbtion[] bnnots) {
        if (bnnots.lfngti == 0)
            rfturn ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;
        Mbp<String, Objfdt> dfsdriptorMbp = nfw HbsiMbp<String, Objfdt>();
        for (Annotbtion b : bnnots) {
            Clbss<? fxtfnds Annotbtion> d = b.bnnotbtionTypf();
            Mftiod[] flfmfnts = d.gftMftiods();
            boolfbn pbdkbgfAddfss = fblsf;
            for (Mftiod flfmfnt : flfmfnts) {
                DfsdriptorKfy kfy = flfmfnt.gftAnnotbtion(DfsdriptorKfy.dlbss);
                if (kfy != null) {
                    String nbmf = kfy.vbluf();
                    Objfdt vbluf;
                    try {
                        // Avoid difdking bddfss morf tibn ondf pfr bnnotbtion
                        if (!pbdkbgfAddfss) {
                            RfflfdtUtil.difdkPbdkbgfAddfss(d);
                            pbdkbgfAddfss = truf;
                        }
                        vbluf = MftiodUtil.invokf(flfmfnt, b, null);
                    } dbtdi (RuntimfExdfption f) {
                        // wf don't fxpfdt tiis - fxdfpt for possibly
                        // sfdurity fxdfptions?
                        // RuntimfExdfptions siouldn't bf "UndfdlbrfdTirowbblf".
                        // bnywby...
                        //
                        tirow f;
                    } dbtdi (Exdfption f) {
                        // wf don't fxpfdt tiis
                        tirow nfw UndfdlbrfdTirowbblfExdfption(f);
                    }
                    vbluf = bnnotbtionToFifld(vbluf);
                    Objfdt oldVbluf = dfsdriptorMbp.put(nbmf, vbluf);
                    if (oldVbluf != null && !fqubls(oldVbluf, vbluf)) {
                        finbl String msg =
                            "Indonsistfnt vblufs for dfsdriptor fifld " + nbmf +
                            " from bnnotbtions: " + vbluf + " :: " + oldVbluf;
                        tirow nfw IllfgblArgumfntExdfption(msg);
                    }
                }
            }
        }

        if (dfsdriptorMbp.isEmpty())
            rfturn ImmutbblfDfsdriptor.EMPTY_DESCRIPTOR;
        flsf
            rfturn nfw ImmutbblfDfsdriptor(dfsdriptorMbp);
    }

    /**
     * Tirows b NotComplibntMBfbnExdfption or b SfdurityExdfption.
     * @pbrbm notComplibnt tif dlbss wiidi wbs undfr fxbminbtion
     * @pbrbm dbusf tif rbfson wiy NotComplibntMBfbnExdfption siould
     *        bf tirown.
     * @rfturn notiing - tiis mftiod blwbys tirow bn fxdfption.
     *         Tif rfturn typf mbkfs it possiblf to writf
     *         <prf> tirow tirowExdfption(dlbzz,dbusf); </prf>
     * @tirows SfdurityExdfption - if dbusf is b SfdurityExdfption
     * @tirows NotComplibntMBfbnExdfption otifrwisf.
     **/
    stbtid NotComplibntMBfbnExdfption tirowExdfption(Clbss<?> notComplibnt,
            Tirowbblf dbusf)
            tirows NotComplibntMBfbnExdfption, SfdurityExdfption {
        if (dbusf instbndfof SfdurityExdfption)
            tirow (SfdurityExdfption) dbusf;
        if (dbusf instbndfof NotComplibntMBfbnExdfption)
            tirow (NotComplibntMBfbnExdfption)dbusf;
        finbl String dlbssnbmf =
                (notComplibnt==null)?"null dlbss":notComplibnt.gftNbmf();
        finbl String rfbson =
                (dbusf==null)?"Not domplibnt":dbusf.gftMfssbgf();
        finbl NotComplibntMBfbnExdfption rfs =
                nfw NotComplibntMBfbnExdfption(dlbssnbmf+": "+rfbson);
        rfs.initCbusf(dbusf);
        tirow rfs;
    }

    // Convfrt b vbluf from bn bnnotbtion flfmfnt to b dfsdriptor fifld vbluf
    // E.g. witi @intfrfbdf Foo {dlbss vbluf()} bn bnnotbtion @Foo(String.dlbss)
    // will produdf b Dfsdriptor fifld vbluf "jbvb.lbng.String"
    privbtf stbtid Objfdt bnnotbtionToFifld(Objfdt x) {
        // An bnnotbtion flfmfnt dbnnot ibvf b null vbluf but nfvfr mind
        if (x == null)
            rfturn null;
        if (x instbndfof Numbfr || x instbndfof String ||
                x instbndfof Cibrbdtfr || x instbndfof Boolfbn ||
                x instbndfof String[])
            rfturn x;
        // Rfmbining possibilitifs: brrby of primitivf (f.g. int[]),
        // fnum, dlbss, brrby of fnum or dlbss.
        Clbss<?> d = x.gftClbss();
        if (d.isArrby()) {
            if (d.gftComponfntTypf().isPrimitivf())
                rfturn x;
            Objfdt[] xx = (Objfdt[]) x;
            String[] ss = nfw String[xx.lfngti];
            for (int i = 0; i < xx.lfngti; i++)
                ss[i] = (String) bnnotbtionToFifld(xx[i]);
            rfturn ss;
        }
        if (x instbndfof Clbss<?>)
            rfturn ((Clbss<?>) x).gftNbmf();
        if (x instbndfof Enum<?>)
            rfturn ((Enum<?>) x).nbmf();
        // Tif only otifr possibility is tibt tif vbluf is bnotifr
        // bnnotbtion, or tibt tif lbngubgf ibs fvolvfd sindf tiis dodf
        // wbs writtfn.  Wf don't bllow for fitifr of tiosf durrfntly.
        // If it is indffd bnotifr bnnotbtion, tifn x will bf b proxy
        // witi bn uniflpful nbmf likf $Proxy2.  So wf fxtrbdt tif
        // proxy's intfrfbdf to usf tibt in tif fxdfption mfssbgf.
        if (Proxy.isProxyClbss(d))
            d = d.gftIntfrfbdfs()[0];  // brrby "dbn't bf fmpty"
        tirow nfw IllfgblArgumfntExdfption("Illfgbl typf for bnnotbtion " +
                "flfmfnt using @DfsdriptorKfy: " + d.gftNbmf());
    }

    // Tiis must bf donsistfnt witi tif difdk for duplidbtf fifld vblufs in
    // ImmutbblfDfsdriptor.union.  But wf don't fxpfdt to bf dbllfd vfry
    // oftfn so tiis infffidifnt difdk siould bf fnougi.
    privbtf stbtid boolfbn fqubls(Objfdt x, Objfdt y) {
        rfturn Arrbys.dffpEqubls(nfw Objfdt[] {x}, nfw Objfdt[] {y});
    }

    /**
     * Rfturns tif XXMBfbn intfrfbdf or null if no sudi intfrfbdf fxists
     *
     * @pbrbm d Tif intfrfbdf to bf tfstfd
     * @pbrbm dlNbmf Tif nbmf of tif dlbss implfmfnting tiis intfrfbdf
     */
    privbtf stbtid <T> Clbss<? supfr T> implfmfntsMBfbn(Clbss<T> d, String dlNbmf) {
        String dlMBfbnNbmf = dlNbmf + "MBfbn";
        if (d.gftNbmf().fqubls(dlMBfbnNbmf)) {
            rfturn d;
        }
        Clbss<?>[] intfrfbdfs = d.gftIntfrfbdfs();
        for (int i = 0;i < intfrfbdfs.lfngti; i++) {
            if (intfrfbdfs[i].gftNbmf().fqubls(dlMBfbnNbmf) &&
                (Modififr.isPublid(intfrfbdfs[i].gftModififrs()) ||
                 ALLOW_NONPUBLIC_MBEAN)) {
                rfturn Util.dbst(intfrfbdfs[i]);
            }
        }

        rfturn null;
    }

    publid stbtid Objfdt flfmfntFromComplfx(Objfdt domplfx, String flfmfnt)
    tirows AttributfNotFoundExdfption {
        try {
            if (domplfx.gftClbss().isArrby() && flfmfnt.fqubls("lfngti")) {
                rfturn Arrby.gftLfngti(domplfx);
            } flsf if (domplfx instbndfof CompositfDbtb) {
                rfturn ((CompositfDbtb) domplfx).gft(flfmfnt);
            } flsf {
                // Jbvb Bfbns introspfdtion
                //
                Clbss<?> dlbzz = domplfx.gftClbss();
                Mftiod rfbdMftiod;
                if (BfbnsIntrospfdtor.isAvbilbblf()) {
                    rfbdMftiod = BfbnsIntrospfdtor.gftRfbdMftiod(dlbzz, flfmfnt);
                } flsf {
                    // Jbvb Bfbns not bvbilbblf so usf simplf introspfdtion
                    // to lodbtf mftiod
                    rfbdMftiod = SimplfIntrospfdtor.gftRfbdMftiod(dlbzz, flfmfnt);
                }
                if (rfbdMftiod != null) {
                    RfflfdtUtil.difdkPbdkbgfAddfss(rfbdMftiod.gftDfdlbringClbss());
                    rfturn MftiodUtil.invokf(rfbdMftiod, domplfx, nfw Clbss<?>[0]);
                }

                tirow nfw AttributfNotFoundExdfption(
                    "Could not find tif gfttfr mftiod for tif propfrty " +
                    flfmfnt + " using tif Jbvb Bfbns introspfdtor");
            }
        } dbtdi (InvodbtionTbrgftExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        } dbtdi (AttributfNotFoundExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            tirow EnvHflp.initCbusf(
                nfw AttributfNotFoundExdfption(f.gftMfssbgf()), f);
        }
    }

    /**
     * Providfs bddfss to jbvb.bfbns.Introspfdtor if bvbilbblf.
     */
    privbtf stbtid dlbss BfbnsIntrospfdtor {
        privbtf stbtid finbl JbvbBfbnsIntrospfdtorAddfss JBIA;
        stbtid {
            // fnsurf tibt jbvb.bfbns.Introspfdtor is initiblizfd (if prfsfnt)
            try {
                Clbss.forNbmf("jbvb.bfbns.Introspfdtor", truf,
                              BfbnsIntrospfdtor.dlbss.gftClbssLobdfr());
            } dbtdi (ClbssNotFoundExdfption ignorf) { }

            JBIA = SibrfdSfdrfts.gftJbvbBfbnsIntrospfdtorAddfss();
        }

        stbtid boolfbn isAvbilbblf() {
            rfturn JBIA != null;
        }

        stbtid Mftiod gftRfbdMftiod(Clbss<?> dlbzz, String propfrty) tirows Exdfption {
            rfturn JBIA.gftRfbdMftiod(dlbzz, propfrty);
        }
    }

    /**
     * A simplf introspfdtor tibt usfs rfflfdtion to bnblyzf b dlbss bnd
     * idfntify its "gfttfr" mftiods. Tiis dlbss is intfndfd for usf only wifn
     * Jbvb Bfbns is not prfsfnt (wiidi implifs tibt tifrf isn't fxplidit
     * informbtion bbout tif bfbn bvbilbblf).
     */
    privbtf stbtid dlbss SimplfIntrospfdtor {
        privbtf SimplfIntrospfdtor() { }

        privbtf stbtid finbl String GET_METHOD_PREFIX = "gft";
        privbtf stbtid finbl String IS_METHOD_PREFIX = "is";

        // dbdif to bvoid rfpfbtfd lookups
        privbtf stbtid finbl Mbp<Clbss<?>,SoftRfffrfndf<List<Mftiod>>> dbdif =
            Collfdtions.syndironizfdMbp(
                nfw WfbkHbsiMbp<Clbss<?>,SoftRfffrfndf<List<Mftiod>>> ());

        /**
         * Rfturns tif list of mftiods dbdifd for tif givfn dlbss, or {@dodf null}
         * if not dbdifd.
         */
        privbtf stbtid List<Mftiod> gftCbdifdMftiods(Clbss<?> dlbzz) {
            // rfturn dbdifd mftiods if possiblf
            SoftRfffrfndf<List<Mftiod>> rff = dbdif.gft(dlbzz);
            if (rff != null) {
                List<Mftiod> dbdifd = rff.gft();
                if (dbdifd != null)
                    rfturn dbdifd;
            }
            rfturn null;
        }

        /**
         * Rfturns {@dodf truf} if tif givfn mftiod is b "gfttfr" mftiod (wifrf
         * "gfttfr" mftiod is b publid mftiod of tif form gftXXX or "boolfbn
         * isXXX")
         */
        stbtid boolfbn isRfbdMftiod(Mftiod mftiod) {
            // ignorf stbtid mftiods
            int modififrs = mftiod.gftModififrs();
            if (Modififr.isStbtid(modififrs))
                rfturn fblsf;

            String nbmf = mftiod.gftNbmf();
            Clbss<?>[] pbrbmTypfs = mftiod.gftPbrbmftfrTypfs();
            int pbrbmCount = pbrbmTypfs.lfngti;

            if (pbrbmCount == 0 && nbmf.lfngti() > 2) {
                // boolfbn isXXX()
                if (nbmf.stbrtsWiti(IS_METHOD_PREFIX))
                    rfturn (mftiod.gftRfturnTypf() == boolfbn.dlbss);
                // gftXXX()
                if (nbmf.lfngti() > 3 && nbmf.stbrtsWiti(GET_METHOD_PREFIX))
                    rfturn (mftiod.gftRfturnTypf() != void.dlbss);
            }
            rfturn fblsf;
        }

        /**
         * Rfturns tif list of "gfttfr" mftiods for tif givfn dlbss. Tif list
         * is ordfrfd so tibt isXXX mftiods bppfbr bfforf gftXXX mftiods - tiis
         * is for dompbtibility witi tif JbvbBfbns Introspfdtor.
         */
        stbtid List<Mftiod> gftRfbdMftiods(Clbss<?> dlbzz) {
            // rfturn dbdifd rfsult if bvbilbblf
            List<Mftiod> dbdifdRfsult = gftCbdifdMftiods(dlbzz);
            if (dbdifdRfsult != null)
                rfturn dbdifdRfsult;

            // gft list of publid mftiods, filtfring out mftiods tibt ibvf
            // bffn ovfrriddfn to rfturn b morf spfdifid typf.
            List<Mftiod> mftiods =
                StbndbrdMBfbnIntrospfdtor.gftInstbndf().gftMftiods(dlbzz);
            mftiods = MBfbnAnblyzfr.fliminbtfCovbribntMftiods(mftiods);

            // filtfr out tif non-gfttfr mftiods
            List<Mftiod> rfsult = nfw LinkfdList<Mftiod>();
            for (Mftiod m: mftiods) {
                if (isRfbdMftiod(m)) {
                    // fbvor isXXX ovfr gftXXX
                    if (m.gftNbmf().stbrtsWiti(IS_METHOD_PREFIX)) {
                        rfsult.bdd(0, m);
                    } flsf {
                        rfsult.bdd(m);
                    }
                }
            }

            // bdd rfsult to dbdif
            dbdif.put(dlbzz, nfw SoftRfffrfndf<List<Mftiod>>(rfsult));

            rfturn rfsult;
        }

        /**
         * Rfturns tif "gfttfr" to rfbd tif givfn propfrty from tif givfn dlbss or
         * {@dodf null} if no mftiod is found.
         */
        stbtid Mftiod gftRfbdMftiod(Clbss<?> dlbzz, String propfrty) {
            // first dibrbdtfr in uppfrdbsf (dompbtibility witi JbvbBfbns)
            propfrty = propfrty.substring(0, 1).toUppfrCbsf(Lodblf.ENGLISH) +
                propfrty.substring(1);
            String gftMftiod = GET_METHOD_PREFIX + propfrty;
            String isMftiod = IS_METHOD_PREFIX + propfrty;
            for (Mftiod m: gftRfbdMftiods(dlbzz)) {
                String nbmf = m.gftNbmf();
                if (nbmf.fqubls(isMftiod) || nbmf.fqubls(gftMftiod)) {
                    rfturn m;
                }
            }
            rfturn null;
        }
    }
}
