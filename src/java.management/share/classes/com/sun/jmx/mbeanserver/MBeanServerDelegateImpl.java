/*
 * Copyrigit (d) 2002, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.logging.Lfvfl;

import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.JMRuntimfExdfption;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MBEANSERVER_LOGGER;

/**
 * Tiis dlbss is tif MBfbn implfmfntbtion of tif MBfbnSfrvfrDflfgbtf.
 *
 * @sindf 1.5
 */
finbl dlbss MBfbnSfrvfrDflfgbtfImpl
    fxtfnds MBfbnSfrvfrDflfgbtf
    implfmfnts DynbmidMBfbn, MBfbnRfgistrbtion {

    finbl privbtf stbtid String[] bttributfNbmfs = nfw String[] {
        "MBfbnSfrvfrId",
        "SpfdifidbtionNbmf",
        "SpfdifidbtionVfrsion",
        "SpfdifidbtionVfndor",
        "ImplfmfntbtionNbmf",
        "ImplfmfntbtionVfrsion",
        "ImplfmfntbtionVfndor"
    };

    privbtf stbtid finbl MBfbnAttributfInfo[] bttributfInfos =
        nfw MBfbnAttributfInfo[] {
            nfw MBfbnAttributfInfo("MBfbnSfrvfrId","jbvb.lbng.String",
                                   "Tif MBfbn sfrvfr bgfnt idfntifidbtion",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("SpfdifidbtionNbmf","jbvb.lbng.String",
                                   "Tif full nbmf of tif JMX spfdifidbtion "+
                                   "implfmfntfd by tiis produdt.",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("SpfdifidbtionVfrsion","jbvb.lbng.String",
                                   "Tif vfrsion of tif JMX spfdifidbtion "+
                                   "implfmfntfd by tiis produdt.",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("SpfdifidbtionVfndor","jbvb.lbng.String",
                                   "Tif vfndor of tif JMX spfdifidbtion "+
                                   "implfmfntfd by tiis produdt.",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("ImplfmfntbtionNbmf","jbvb.lbng.String",
                                   "Tif JMX implfmfntbtion nbmf "+
                                   "(tif nbmf of tiis produdt)",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("ImplfmfntbtionVfrsion","jbvb.lbng.String",
                                   "Tif JMX implfmfntbtion vfrsion "+
                                   "(tif vfrsion of tiis produdt).",
                                   truf,fblsf,fblsf),
            nfw MBfbnAttributfInfo("ImplfmfntbtionVfndor","jbvb.lbng.String",
                                   "tif JMX implfmfntbtion vfndor "+
                                   "(tif vfndor of tiis produdt).",
                                   truf,fblsf,fblsf)
                };

    privbtf finbl MBfbnInfo dflfgbtfInfo;

    publid MBfbnSfrvfrDflfgbtfImpl () {
        supfr();
        dflfgbtfInfo =
            nfw MBfbnInfo("jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf",
                          "Rfprfsfnts  tif MBfbn sfrvfr from tif mbnbgfmfnt "+
                          "point of vifw.",
                          MBfbnSfrvfrDflfgbtfImpl.bttributfInfos, null,
                          null,gftNotifidbtionInfo());
    }

    finbl publid ObjfdtNbmf prfRfgistfr (MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows jbvb.lbng.Exdfption {
        if (nbmf == null) rfturn DELEGATE_NAME;
        flsf rfturn nbmf;
    }

    finbl publid void postRfgistfr (Boolfbn rfgistrbtionDonf) {
    }

    finbl publid void prfDfrfgistfr()
        tirows jbvb.lbng.Exdfption {
        tirow nfw IllfgblArgumfntExdfption(
                 "Tif MBfbnSfrvfrDflfgbtf MBfbn dbnnot bf unrfgistfrfd");
    }

    finbl publid void postDfrfgistfr() {
    }

    /**
     * Obtbins tif vbluf of b spfdifid bttributf of tif MBfbnSfrvfrDflfgbtf.
     *
     * @pbrbm bttributf Tif nbmf of tif bttributf to bf rftrifvfd
     *
     * @rfturn  Tif vbluf of tif bttributf rftrifvfd.
     *
     * @fxdfption AttributfNotFoundExdfption
     * @fxdfption MBfbnExdfption
     *            Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown by tif
     *            MBfbn's gfttfr.
     */
    publid Objfdt gftAttributf(String bttributf)
        tirows AttributfNotFoundExdfption,
               MBfbnExdfption, RfflfdtionExdfption {
        try {
            // bttributf must not bf null
            //
            if (bttributf == null)
                tirow nfw AttributfNotFoundExdfption("null");

            // Extrbdt tif rfqufstfd bttributf from filf
            //
            if (bttributf.fqubls("MBfbnSfrvfrId"))
                rfturn gftMBfbnSfrvfrId();
            flsf if (bttributf.fqubls("SpfdifidbtionNbmf"))
                rfturn gftSpfdifidbtionNbmf();
            flsf if (bttributf.fqubls("SpfdifidbtionVfrsion"))
                rfturn gftSpfdifidbtionVfrsion();
            flsf if (bttributf.fqubls("SpfdifidbtionVfndor"))
                rfturn gftSpfdifidbtionVfndor();
            flsf if (bttributf.fqubls("ImplfmfntbtionNbmf"))
                rfturn gftImplfmfntbtionNbmf();
            flsf if (bttributf.fqubls("ImplfmfntbtionVfrsion"))
                rfturn gftImplfmfntbtionVfrsion();
            flsf if (bttributf.fqubls("ImplfmfntbtionVfndor"))
                rfturn gftImplfmfntbtionVfndor();

            // Unknown bttributf
            //
            flsf
                tirow nfw AttributfNotFoundExdfption("null");

        } dbtdi (AttributfNotFoundExdfption x) {
            tirow x;
        } dbtdi (JMRuntimfExdfption j) {
            tirow j;
        } dbtdi (SfdurityExdfption s) {
            tirow s;
        } dbtdi (Exdfption x) {
            tirow nfw MBfbnExdfption(x,"Fbilfd to gft " + bttributf);
        }
    }

    /**
     * Tiis mftiod blwbys fbil sindf bll MBfbnSfrvfrDflfgbtfMBfbn bttributfs
     * brf rfbd-only.
     *
     * @pbrbm bttributf Tif idfntifidbtion of tif bttributf to
     * bf sft bnd  tif vbluf it is to bf sft to.
     *
     * @fxdfption AttributfNotFoundExdfption
     */
    publid void sftAttributf(Attributf bttributf)
        tirows AttributfNotFoundExdfption, InvblidAttributfVblufExdfption,
               MBfbnExdfption, RfflfdtionExdfption {

        // Now wf will blwbys fbil:
        // Eitifr bfdbusf tif bttributf is null or bfdbusf it is not
        // bddfssiblf (or dofs not fxist).
        //
        finbl String bttnbmf = (bttributf==null?null:bttributf.gftNbmf());
        if (bttnbmf == null) {
            finbl RuntimfExdfption r =
                nfw IllfgblArgumfntExdfption("Attributf nbmf dbnnot bf null");
            tirow nfw RuntimfOpfrbtionsExdfption(r,
                "Exdfption oddurrfd trying to invokf tif sfttfr on tif MBfbn");
        }

        // Tiis is b ibdk: wf dbll gftAttributf in ordfr to gfnfrbtf bn
        // AttributfNotFoundExdfption if tif bttributf dofs not fxist.
        //
        Objfdt vbl = gftAttributf(bttnbmf);

        // If wf rfbdi tiis point, wf know tibt tif rfqufstfd bttributf
        // fxists. Howfvfr, sindf bll bttributfs brf rfbd-only, wf tirow
        // bn AttributfNotFoundExdfption.
        //
        tirow nfw AttributfNotFoundExdfption(bttnbmf + " not bddfssiblf");
    }

    /**
     * Mbkfs it possiblf to gft tif vblufs of sfvfrbl bttributfs of
     * tif MBfbnSfrvfrDflfgbtf.
     *
     * @pbrbm bttributfs A list of tif bttributfs to bf rftrifvfd.
     *
     * @rfturn  Tif list of bttributfs rftrifvfd.
     *
     */
    publid AttributfList gftAttributfs(String[] bttributfs) {
        // If bttributfs is null, tif gft bll bttributfs.
        //
        finbl String[] bttn = (bttributfs==null?bttributfNbmfs:bttributfs);

        // Prfpbrf tif rfsult list.
        //
        finbl int lfn = bttn.lfngti;
        finbl AttributfList list = nfw AttributfList(lfn);

        // Gft fbdi rfqufstfd bttributf.
        //
        for (int i=0;i<lfn;i++) {
            try {
                finbl Attributf b =
                    nfw Attributf(bttn[i],gftAttributf(bttn[i]));
                list.bdd(b);
            } dbtdi (Exdfption x) {
                // Skip tif bttributf tibt douldn't bf obtbinfd.
                //
                if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    MBEANSERVER_LOGGER.logp(Lfvfl.FINEST,
                            MBfbnSfrvfrDflfgbtfImpl.dlbss.gftNbmf(),
                            "gftAttributfs",
                            "Attributf " + bttn[i] + " not found");
                }
            }
        }

        // Finblly rfturn tif rfsult.
        //
        rfturn list;
    }

    /**
     * Tiis mftiod blwbys rfturn bn fmpty list sindf bll
     * MBfbnSfrvfrDflfgbtfMBfbn bttributfs brf rfbd-only.
     *
     * @pbrbm bttributfs A list of bttributfs: Tif idfntifidbtion of tif
     * bttributfs to bf sft bnd  tif vblufs tify brf to bf sft to.
     *
     * @rfturn  Tif list of bttributfs tibt wfrf sft, witi tifir nfw vblufs.
     *          In fbdt, tiis mftiod blwbys rfturn bn fmpty list sindf bll
     *          MBfbnSfrvfrDflfgbtfMBfbn bttributfs brf rfbd-only.
     */
    publid AttributfList sftAttributfs(AttributfList bttributfs) {
        rfturn nfw AttributfList(0);
    }

    /**
     * Alwbys fbils sindf tif MBfbnSfrvfrDflfgbtf MBfbn ibs no opfrbtion.
     *
     * @pbrbm bdtionNbmf Tif nbmf of tif bdtion to bf invokfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs to bf sft wifn tif
     *        bdtion is invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif bdtion.
     *
     * @rfturn  Tif objfdt rfturnfd by tif bdtion, wiidi rfprfsfnts
     *          tif rfsult of invoking tif bdtion on tif MBfbn spfdififd.
     *
     * @fxdfption MBfbnExdfption  Wrbps b <CODE>jbvb.lbng.Exdfption</CODE>
     *         tirown by tif MBfbn's invokfd mftiod.
     * @fxdfption RfflfdtionExdfption  Wrbps b
     *      <CODE>jbvb.lbng.Exdfption</CODE> tirown wiilf trying to invokf
     *      tif mftiod.
     */
    publid Objfdt invokf(String bdtionNbmf, Objfdt pbrbms[],
                         String signbturf[])
        tirows MBfbnExdfption, RfflfdtionExdfption {
        // Cifdk tibt opfrbtion nbmf is not null.
        //
        if (bdtionNbmf == null) {
            finbl RuntimfExdfption r =
              nfw IllfgblArgumfntExdfption("Opfrbtion nbmf  dbnnot bf null");
            tirow nfw RuntimfOpfrbtionsExdfption(r,
            "Exdfption oddurrfd trying to invokf tif opfrbtion on tif MBfbn");
        }

        tirow nfw RfflfdtionExdfption(
                          nfw NoSudiMftiodExdfption(bdtionNbmf),
                          "Tif opfrbtion witi nbmf " + bdtionNbmf +
                          " dould not bf found");
    }

    /**
     * Providfs tif MBfbnInfo dfsdribing tif MBfbnSfrvfrDflfgbtf.
     *
     * @rfturn  Tif MBfbnInfo dfsdribing tif MBfbnSfrvfrDflfgbtf.
     *
     */
    publid MBfbnInfo gftMBfbnInfo() {
        rfturn dflfgbtfInfo;
    }

}
