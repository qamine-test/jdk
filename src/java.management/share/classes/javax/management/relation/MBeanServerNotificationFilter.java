/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rflbtion;

import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.dbst;
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.RELATION_LOGGER;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.sfdurity.AddfssControllfr;

import jbvb.util.List;
import jbvb.util.Vfdtor;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion;

import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfrSupport;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import jbvb.util.List;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;

/**
 * Filtfr for {@link MBfbnSfrvfrNotifidbtion}.
 * Tiis filtfr filtfrs MBfbnSfrvfrNotifidbtion notifidbtions by
 * sflfdting tif ObjfdtNbmfs of intfrfst bnd tif opfrbtions (rfgistrbtion,
 * unrfgistrbtion, boti) of intfrfst (dorrfsponding to notifidbtion
 * typfs).
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>2605900539589789736L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID must bf donstbnt
publid dlbss MBfbnSfrvfrNotifidbtionFiltfr fxtfnds NotifidbtionFiltfrSupport {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = 6001782699077323605L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = 2605900539589789736L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("mySflfdtObjNbmfList", Vfdtor.dlbss),
      nfw ObjfdtStrfbmFifld("myDfsflfdtObjNbmfList", Vfdtor.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("sflfdtfdNbmfs", List.dlbss),
      nfw ObjfdtStrfbmFifld("dfsflfdtfdNbmfs", List.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld sflfdtfdNbmfs List List of {@link ObjfdtNbmf}s of intfrfst
     *         <ul>
     *         <li><dodf>null</dodf> mfbns tibt bll {@link ObjfdtNbmf}s brf impliditly sflfdtfd
     *         (difdk for fxplidit dfsflfdtions)</li>
     *         <li>Empty vfdtor mfbns tibt no {@link ObjfdtNbmf} is fxpliditly sflfdtfd</li>
     *         </ul>
     * @sfriblFifld dfsflfdtfdNbmfs List List of {@link ObjfdtNbmf}s witi no intfrfst
     *         <ul>
     *         <li><dodf>null</dodf> mfbns tibt bll {@link ObjfdtNbmf}s brf impliditly dfsflfdtfd
     *         (difdk for fxplidit sflfdtions))</li>
     *         <li>Empty vfdtor mfbns tibt no {@link ObjfdtNbmf} is fxpliditly dfsflfdtfd</li>
     *         </ul>
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf stbtid boolfbn dompbt = fblsf;
    stbtid {
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK : Too bbd, no dompbt witi 1.0
        }
        if (dompbt) {
            sfriblPfrsistfntFiflds = oldSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = oldSfriblVfrsionUID;
        } flsf {
            sfriblPfrsistfntFiflds = nfwSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = nfwSfriblVfrsionUID;
        }
    }
    //
    // END Sfriblizbtion dompbtibility stuff

    //
    // Privbtf mfmbfrs
    //

    /**
     * @sfribl List of {@link ObjfdtNbmf}s of intfrfst
     *         <ul>
     *         <li><dodf>null</dodf> mfbns tibt bll {@link ObjfdtNbmf}s brf impliditly sflfdtfd
     *         (difdk for fxplidit dfsflfdtions)</li>
     *         <li>Empty vfdtor mfbns tibt no {@link ObjfdtNbmf} is fxpliditly sflfdtfd</li>
     *         </ul>
     */
    privbtf List<ObjfdtNbmf> sflfdtfdNbmfs = nfw Vfdtor<ObjfdtNbmf>();

    /**
     * @sfribl List of {@link ObjfdtNbmf}s witi no intfrfst
     *         <ul>
     *         <li><dodf>null</dodf> mfbns tibt bll {@link ObjfdtNbmf}s brf impliditly dfsflfdtfd
     *         (difdk for fxplidit sflfdtions))</li>
     *         <li>Empty vfdtor mfbns tibt no {@link ObjfdtNbmf} is fxpliditly dfsflfdtfd</li>
     *         </ul>
     */
    privbtf List<ObjfdtNbmf> dfsflfdtfdNbmfs = null;

    //
    // Construdtor
    //

    /**
     * Crfbtfs b filtfr sflfdting bll MBfbnSfrvfrNotifidbtion notifidbtions for
     * bll ObjfdtNbmfs.
     */
    publid MBfbnSfrvfrNotifidbtionFiltfr() {

        supfr();
        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "MBfbnSfrvfrNotifidbtionFiltfr");

        fnbblfTypf(MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION);
        fnbblfTypf(MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION);

        RELATION_LOGGER.fxiting(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "MBfbnSfrvfrNotifidbtionFiltfr");
        rfturn;
    }

    //
    // Addfssors
    //

    /**
     * Disbblfs bny MBfbnSfrvfrNotifidbtion (bll ObjfdtNbmfs brf
     * dfsflfdtfd).
     */
    publid syndironizfd void disbblfAllObjfdtNbmfs() {

        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "disbblfAllObjfdtNbmfs");

        sflfdtfdNbmfs = nfw Vfdtor<ObjfdtNbmf>();
        dfsflfdtfdNbmfs = null;

        RELATION_LOGGER.fxiting(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "disbblfAllObjfdtNbmfs");
        rfturn;
    }

    /**
     * Disbblfs MBfbnSfrvfrNotifidbtions dondfrning givfn ObjfdtNbmf.
     *
     * @pbrbm objfdtNbmf  ObjfdtNbmf no longfr of intfrfst
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif givfn ObjfdtNbmf is null
     */
    publid syndironizfd void disbblfObjfdtNbmf(ObjfdtNbmf objfdtNbmf)
        tirows IllfgblArgumfntExdfption {

        if (objfdtNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "disbblfObjfdtNbmf", objfdtNbmf);

        // Rfmovfs from sflfdtfd ObjfdtNbmfs, if prfsfnt
        if (sflfdtfdNbmfs != null) {
            if (sflfdtfdNbmfs.sizf() != 0) {
                sflfdtfdNbmfs.rfmovf(objfdtNbmf);
            }
        }

        // Adds it in dfsflfdtfd ObjfdtNbmfs
        if (dfsflfdtfdNbmfs != null) {
            // If bll brf dfsflfdtfd, no nffd to do bnytiing :)
            if (!(dfsflfdtfdNbmfs.dontbins(objfdtNbmf))) {
                // ObjfdtNbmf wbs not blrfbdy dfsflfdtfd
                dfsflfdtfdNbmfs.bdd(objfdtNbmf);
            }
        }

        RELATION_LOGGER.fxiting(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "disbblfObjfdtNbmf");
        rfturn;
    }

    /**
     * Enbblfs bll MBfbnSfrvfrNotifidbtions (bll ObjfdtNbmfs brf sflfdtfd).
     */
    publid syndironizfd void fnbblfAllObjfdtNbmfs() {

        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "fnbblfAllObjfdtNbmfs");

        sflfdtfdNbmfs = null;
        dfsflfdtfdNbmfs = nfw Vfdtor<ObjfdtNbmf>();

        RELATION_LOGGER.fxiting(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "fnbblfAllObjfdtNbmfs");
        rfturn;
    }

    /**
     * Enbblfs MBfbnSfrvfrNotifidbtions dondfrning givfn ObjfdtNbmf.
     *
     * @pbrbm objfdtNbmf  ObjfdtNbmf of intfrfst
     *
     * @fxdfption IllfgblArgumfntExdfption  if tif givfn ObjfdtNbmf is null
     */
    publid syndironizfd void fnbblfObjfdtNbmf(ObjfdtNbmf objfdtNbmf)
        tirows IllfgblArgumfntExdfption {

        if (objfdtNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "fnbblfObjfdtNbmf", objfdtNbmf);

        // Rfmovfs from dfsflfdtfd ObjfdtNbmfs, if prfsfnt
        if (dfsflfdtfdNbmfs != null) {
            if (dfsflfdtfdNbmfs.sizf() != 0) {
                dfsflfdtfdNbmfs.rfmovf(objfdtNbmf);
            }
        }

        // Adds it in sflfdtfd ObjfdtNbmfs
        if (sflfdtfdNbmfs != null) {
            // If bll brf sflfdtfd, no nffd to do bnytiing :)
            if (!(sflfdtfdNbmfs.dontbins(objfdtNbmf))) {
                // ObjfdtNbmf wbs not blrfbdy sflfdtfd
                sflfdtfdNbmfs.bdd(objfdtNbmf);
            }
        }

        RELATION_LOGGER.fxiting(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "fnbblfObjfdtNbmf");
        rfturn;
    }

    /**
     * Gfts bll tif ObjfdtNbmfs fnbblfd.
     *
     * @rfturn Vfdtor of ObjfdtNbmfs:
     * <P>- null mfbns bll ObjfdtNbmfs brf impliditly sflfdtfd, fxdfpt tif
     * ObjfdtNbmfs fxpliditly dfsflfdtfd
     * <P>- fmpty mfbns bll ObjfdtNbmfs brf dfsflfdtfd, i.f. no ObjfdtNbmf
     * sflfdtfd.
     */
    publid syndironizfd Vfdtor<ObjfdtNbmf> gftEnbblfdObjfdtNbmfs() {
        if (sflfdtfdNbmfs != null) {
            rfturn nfw Vfdtor<ObjfdtNbmf>(sflfdtfdNbmfs);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Gfts bll tif ObjfdtNbmfs disbblfd.
     *
     * @rfturn Vfdtor of ObjfdtNbmfs:
     * <P>- null mfbns bll ObjfdtNbmfs brf impliditly dfsflfdtfd, fxdfpt tif
     * ObjfdtNbmfs fxpliditly sflfdtfd
     * <P>- fmpty mfbns bll ObjfdtNbmfs brf sflfdtfd, i.f. no ObjfdtNbmf
     * dfsflfdtfd.
     */
    publid syndironizfd Vfdtor<ObjfdtNbmf> gftDisbblfdObjfdtNbmfs() {
        if (dfsflfdtfdNbmfs != null) {
            rfturn nfw Vfdtor<ObjfdtNbmf>(dfsflfdtfdNbmfs);
        } flsf {
            rfturn null;
        }
    }

    //
    // NotifidbtionFiltfr intfrfbdf
    //

    /**
     * Invokfd bfforf sfnding tif spfdififd notifidbtion to tif listfnfr.
     * <P>If:
     * <P>- tif ObjfdtNbmf of tif dondfrnfd MBfbn is sflfdtfd (fxpliditly OR
     * (impliditly bnd not fxpliditly dfsflfdtfd))
     * <P>AND
     * <P>- tif typf of tif opfrbtion (rfgistrbtion or unrfgistrbtion) is
     * sflfdtfd
     * <P>tifn tif notifidbtion is sfnt to tif listfnfr.
     *
     * @pbrbm notif  Tif notifidbtion to bf sfnt.
     *
     * @rfturn truf if tif notifidbtion ibs to bf sfnt to tif listfnfr, fblsf
     * otifrwisf.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     */
    publid syndironizfd boolfbn isNotifidbtionEnbblfd(Notifidbtion notif)
        tirows IllfgblArgumfntExdfption {

        if (notif == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "isNotifidbtionEnbblfd", notif);

        // Cifdks tif typf first
        String ntfTypf = notif.gftTypf();
        Vfdtor<String> fnbblfdTypfs = gftEnbblfdTypfs();
        if (!(fnbblfdTypfs.dontbins(ntfTypf))) {
            RELATION_LOGGER.logp(Lfvfl.FINER,
                    MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                    "isNotifidbtionEnbblfd",
                    "Typf not sflfdtfd, fxiting");
            rfturn fblsf;
        }

        // Wf ibvf b MBfbnSfrvfrNotifidbtion: downdbsts it
        MBfbnSfrvfrNotifidbtion mbsNtf = (MBfbnSfrvfrNotifidbtion)notif;

        // Cifdks tif ObjfdtNbmf
        ObjfdtNbmf objNbmf = mbsNtf.gftMBfbnNbmf();
        // Is it sflfdtfd?
        boolfbn isSflfdtfdFlg = fblsf;
        if (sflfdtfdNbmfs != null) {
            // Not bll brf impliditly sflfdtfd:
            // difdks for fxplidit sflfdtion
            if (sflfdtfdNbmfs.sizf() == 0) {
                // All brf fxpliditly not sflfdtfd
                RELATION_LOGGER.logp(Lfvfl.FINER,
                        MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                        "isNotifidbtionEnbblfd",
                        "No ObjfdtNbmfs sflfdtfd, fxiting");
                rfturn fblsf;
            }

            isSflfdtfdFlg = sflfdtfdNbmfs.dontbins(objNbmf);
            if (!isSflfdtfdFlg) {
                // Not in tif fxplidit sflfdtfd list
                RELATION_LOGGER.logp(Lfvfl.FINER,
                        MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                        "isNotifidbtionEnbblfd",
                        "ObjfdtNbmf not in sflfdtfd list, fxiting");
                rfturn fblsf;
            }
        }

        if (!isSflfdtfdFlg) {
            // Not fxpliditly sflfdtfd: is it dfsflfdtfd?

            if (dfsflfdtfdNbmfs == null) {
                // All brf impliditly dfsflfdtfd bnd it is not fxpliditly
                // sflfdtfd
                RELATION_LOGGER.logp(Lfvfl.FINER,
                        MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                        "isNotifidbtionEnbblfd",
                        "ObjfdtNbmf not sflfdtfd, bnd bll " +
                        "nbmfs dfsflfdtfd, fxiting");
                rfturn fblsf;

            } flsf if (dfsflfdtfdNbmfs.dontbins(objNbmf)) {
                // Expliditly dfsflfdtfd
                RELATION_LOGGER.logp(Lfvfl.FINER,
                        MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                        "isNotifidbtionEnbblfd",
                        "ObjfdtNbmf fxpliditly not sflfdtfd, fxiting");
                rfturn fblsf;
            }
        }

        RELATION_LOGGER.logp(Lfvfl.FINER,
                MBfbnSfrvfrNotifidbtionFiltfr.dlbss.gftNbmf(),
                "isNotifidbtionEnbblfd",
                "ObjfdtNbmf sflfdtfd, fxiting");
        rfturn truf;
    }


    /**
     * Dfsfriblizfs bn {@link MBfbnSfrvfrNotifidbtionFiltfr} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        sflfdtfdNbmfs = dbst(fiflds.gft("mySflfdtObjNbmfList", null));
        if (fiflds.dffbultfd("mySflfdtObjNbmfList"))
        {
          tirow nfw NullPointfrExdfption("mySflfdtObjNbmfList");
        }
        dfsflfdtfdNbmfs = dbst(fiflds.gft("myDfsflfdtObjNbmfList", null));
        if (fiflds.dffbultfd("myDfsflfdtObjNbmfList"))
        {
          tirow nfw NullPointfrExdfption("myDfsflfdtObjNbmfList");
        }
      }
      flsf
      {
        // Rfbd bn objfdt sfriblizfd in tif nfw sfribl form
        //
        in.dffbultRfbdObjfdt();
      }
    }


    /**
     * Sfriblizfs bn {@link MBfbnSfrvfrNotifidbtionFiltfr} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("mySflfdtObjNbmfList", sflfdtfdNbmfs);
        fiflds.put("myDfsflfdtObjNbmfList", dfsflfdtfdNbmfs);
        out.writfFiflds();
      }
      flsf
      {
        // Sfriblizfs tiis instbndf in tif nfw sfribl form
        //
        out.dffbultWritfObjfdt();
      }
    }
}
