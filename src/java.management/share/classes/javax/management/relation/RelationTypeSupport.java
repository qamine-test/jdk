/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.RELATION_LOGGER;
import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.dbst;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;

import jbvb.sfdurity.AddfssControllfr;

import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.logging.Lfvfl;

/**
 * A RflbtionTypfSupport objfdt implfmfnts tif RflbtionTypf intfrfbdf.
 * <P>It rfprfsfnts b rflbtion typf, providing rolf informbtion for fbdi rolf
 * fxpfdtfd to bf supportfd in fvfry rflbtion of tibt typf.
 *
 * <P>A rflbtion typf indludfs b rflbtion typf nbmf bnd b list of
 * rolf infos (rfprfsfntfd by RolfInfo objfdts).
 *
 * <P>A rflbtion typf ibs to bf dfdlbrfd in tif Rflbtion Sfrvidf:
 * <P>- fitifr using tif drfbtfRflbtionTypf() mftiod, wifrf b RflbtionTypfSupport
 * objfdt will bf drfbtfd bnd kfpt in tif Rflbtion Sfrvidf
 * <P>- fitifr using tif bddRflbtionTypf() mftiod wifrf tif usfr ibs to drfbtf
 * bn objfdt implfmfnting tif RflbtionTypf intfrfbdf, bnd tiis objfdt will bf
 * usfd bs rfprfsfnting b rflbtion typf in tif Rflbtion Sfrvidf.
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>4611072955724144607L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss RflbtionTypfSupport implfmfnts RflbtionTypf {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -8179019472410837190L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = 4611072955724144607L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("myTypfNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("myRolfNbmf2InfoMbp", HbsiMbp.dlbss),
      nfw ObjfdtStrfbmFifld("myIsInRflSfrvFlg", boolfbn.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("typfNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("rolfNbmf2InfoMbp", Mbp.dlbss),
      nfw ObjfdtStrfbmFifld("isInRflbtionSfrvidf", boolfbn.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld typfNbmf String Rflbtion typf nbmf
     * @sfriblFifld rolfNbmf2InfoMbp Mbp {@link Mbp} iolding tif mbpping:
     *              &lt;rolf nbmf ({@link String})&gt; -&gt; &lt;rolf info ({@link RolfInfo} objfdt)&gt;
     * @sfriblFifld isInRflbtionSfrvidf boolfbn Flbg spfdifying wiftifr tif rflbtion typf ibs bffn dfdlbrfd in tif
     *              Rflbtion Sfrvidf (so dbn no longfr bf updbtfd)
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
     * @sfribl Rflbtion typf nbmf
     */
    privbtf String typfNbmf = null;

    /**
     * @sfribl {@link Mbp} iolding tif mbpping:
     *           &lt;rolf nbmf ({@link String})&gt; -&gt; &lt;rolf info ({@link RolfInfo} objfdt)&gt;
     */
    privbtf Mbp<String,RolfInfo> rolfNbmf2InfoMbp =
        nfw HbsiMbp<String,RolfInfo>();

    /**
     * @sfribl Flbg spfdifying wiftifr tif rflbtion typf ibs bffn dfdlbrfd in tif
     *         Rflbtion Sfrvidf (so dbn no longfr bf updbtfd)
     */
    privbtf boolfbn isInRflbtionSfrvidf = fblsf;

    //
    // Construdtors
    //

    /**
     * Construdtor wifrf bll rolf dffinitions brf dynbmidblly drfbtfd bnd
     * pbssfd bs pbrbmftfr.
     *
     * @pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf
     * @pbrbm rolfInfoArrby  List of rolf dffinitions (RolfInfo objfdts)
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption InvblidRflbtionTypfExdfption  if:
     * <P>- tif sbmf nbmf ibs bffn usfd for two difffrfnt rolfs
     * <P>- no rolf info providfd
     * <P>- onf null rolf info providfd
     */
    publid RflbtionTypfSupport(String rflbtionTypfNbmf,
                            RolfInfo[] rolfInfoArrby)
        tirows IllfgblArgumfntExdfption,
               InvblidRflbtionTypfExdfption {

        if (rflbtionTypfNbmf == null || rolfInfoArrby == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionTypfSupport.dlbss.gftNbmf(),
                "RflbtionTypfSupport", rflbtionTypfNbmf);

        // Cbn tirow InvblidRflbtionTypfExdfption, ClbssNotFoundExdfption
        // bnd NotComplibntMBfbnExdfption
        initMfmbfrs(rflbtionTypfNbmf, rolfInfoArrby);

        RELATION_LOGGER.fxiting(RflbtionTypfSupport.dlbss.gftNbmf(),
                "RflbtionTypfSupport");
        rfturn;
    }

    /**
     * Construdtor to bf usfd for subdlbssfs.
     *
     * @pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr.
     */
    protfdtfd RflbtionTypfSupport(String rflbtionTypfNbmf)
    {
        if (rflbtionTypfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionTypfSupport.dlbss.gftNbmf(),
                "RflbtionTypfSupport", rflbtionTypfNbmf);

        typfNbmf = rflbtionTypfNbmf;

        RELATION_LOGGER.fxiting(RflbtionTypfSupport.dlbss.gftNbmf(),
                "RflbtionTypfSupport");
        rfturn;
    }

    //
    // Addfssors
    //

    /**
     * Rfturns tif rflbtion typf nbmf.
     *
     * @rfturn tif rflbtion typf nbmf.
     */
    publid String gftRflbtionTypfNbmf() {
        rfturn typfNbmf;
    }

    /**
     * Rfturns tif list of rolf dffinitions (ArrbyList of RolfInfo objfdts).
     */
    publid List<RolfInfo> gftRolfInfos() {
        rfturn nfw ArrbyList<RolfInfo>(rolfNbmf2InfoMbp.vblufs());
    }

    /**
     * Rfturns tif rolf info (RolfInfo objfdt) for tif givfn rolf info nbmf
     * (null if not found).
     *
     * @pbrbm rolfInfoNbmf  rolf info nbmf
     *
     * @rfturn RolfInfo objfdt providing rolf dffinition
     * dofs not fxist
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption RolfInfoNotFoundExdfption  if no rolf info witi tibt nbmf in
     * rflbtion typf.
     */
    publid RolfInfo gftRolfInfo(String rolfInfoNbmf)
        tirows IllfgblArgumfntExdfption,
               RolfInfoNotFoundExdfption {

        if (rolfInfoNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionTypfSupport.dlbss.gftNbmf(),
                "gftRolfInfo", rolfInfoNbmf);

        // No null RolfInfo bllowfd, so usf gft()
        RolfInfo rfsult = rolfNbmf2InfoMbp.gft(rolfInfoNbmf);

        if (rfsult == null) {
            StringBuildfr fxdMsgStrB = nfw StringBuildfr();
            String fxdMsg = "No rolf info for rolf ";
            fxdMsgStrB.bppfnd(fxdMsg);
            fxdMsgStrB.bppfnd(rolfInfoNbmf);
            tirow nfw RolfInfoNotFoundExdfption(fxdMsgStrB.toString());
        }

        RELATION_LOGGER.fxiting(RflbtionTypfSupport.dlbss.gftNbmf(),
                "gftRolfInfo");
        rfturn rfsult;
    }

    //
    // Misd
    //

    /**
     * Add b rolf info.
     * Tiis mftiod of doursf siould not bf usfd bftfr tif drfbtion of tif
     * rflbtion typf, bfdbusf updbting it would invblidbtf tibt tif rflbtions
     * drfbtfd bssodibtfd to tibt typf still donform to it.
     * Cbn tirow b RuntimfExdfption if trying to updbtf b rflbtion typf
     * dfdlbrfd in tif Rflbtion Sfrvidf.
     *
     * @pbrbm rolfInfo  rolf info to bf bddfd.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr.
     * @fxdfption InvblidRflbtionTypfExdfption  if tifrf is blrfbdy b rolf
     *  info in durrfnt rflbtion typf witi tif sbmf nbmf.
     */
    protfdtfd void bddRolfInfo(RolfInfo rolfInfo)
        tirows IllfgblArgumfntExdfption,
               InvblidRflbtionTypfExdfption {

        if (rolfInfo == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionTypfSupport.dlbss.gftNbmf(),
                "bddRolfInfo", rolfInfo);

        if (isInRflbtionSfrvidf) {
            // Trying to updbtf b dfdlbrfd rflbtion typf
            String fxdMsg = "Rflbtion typf dbnnot bf updbtfd bs it is dfdlbrfd in tif Rflbtion Sfrvidf.";
            tirow nfw RuntimfExdfption(fxdMsg);
        }

        String rolfNbmf = rolfInfo.gftNbmf();

        // Cifdks if tif rolf info ibs blrfbdy bffn dfsdribfd
        if (rolfNbmf2InfoMbp.dontbinsKfy(rolfNbmf)) {
            StringBuildfr fxdMsgStrB = nfw StringBuildfr();
            String fxdMsg = "Two rolf infos providfd for rolf ";
            fxdMsgStrB.bppfnd(fxdMsg);
            fxdMsgStrB.bppfnd(rolfNbmf);
            tirow nfw InvblidRflbtionTypfExdfption(fxdMsgStrB.toString());
        }

        rolfNbmf2InfoMbp.put(rolfNbmf, nfw RolfInfo(rolfInfo));

        RELATION_LOGGER.fxiting(RflbtionTypfSupport.dlbss.gftNbmf(),
                "bddRolfInfo");
        rfturn;
    }

    // Sfts tif intfrnbl flbg to spfdify tibt tif rflbtion typf ibs bffn
    // dfdlbrfd in tif Rflbtion Sfrvidf
    void sftRflbtionSfrvidfFlbg(boolfbn flbg) {
        isInRflbtionSfrvidf = flbg;
        rfturn;
    }

    // Initiblizfs tif mfmbfrs, i.f. typf nbmf bnd rolf info list.
    //
    // -pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf
    // -pbrbm rolfInfoArrby  List of rolf dffinitions (RolfInfo objfdts)
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
    // -fxdfption InvblidRflbtionTypfExdfption  If:
    //  - tif sbmf nbmf ibs bffn usfd for two difffrfnt rolfs
    //  - no rolf info providfd
    //  - onf null rolf info providfd
    privbtf void initMfmbfrs(String rflbtionTypfNbmf,
                             RolfInfo[] rolfInfoArrby)
        tirows IllfgblArgumfntExdfption,
               InvblidRflbtionTypfExdfption {

        if (rflbtionTypfNbmf == null || rolfInfoArrby == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionTypfSupport.dlbss.gftNbmf(),
                "initMfmbfrs", rflbtionTypfNbmf);

        typfNbmf = rflbtionTypfNbmf;

        // Vfrififs rolf infos bfforf sftting tifm
        // Cbn tirow InvblidRflbtionTypfExdfption
        difdkRolfInfos(rolfInfoArrby);

        for (int i = 0; i < rolfInfoArrby.lfngti; i++) {
            RolfInfo durrRolfInfo = rolfInfoArrby[i];
            rolfNbmf2InfoMbp.put(durrRolfInfo.gftNbmf(),
                                 nfw RolfInfo(durrRolfInfo));
        }

        RELATION_LOGGER.fxiting(RflbtionTypfSupport.dlbss.gftNbmf(),
                "initMfmbfrs");
        rfturn;
    }

    // Cifdks tif givfn RolfInfo brrby to vfrify tibt:
    // - tif brrby is not fmpty
    // - it dofs not dontbin b null flfmfnt
    // - b givfn rolf nbmf is usfd only for onf RolfInfo
    //
    // -pbrbm rolfInfoArrby  brrby to bf difdkfd
    //
    // -fxdfption IllfgblArgumfntExdfption
    // -fxdfption InvblidRflbtionTypfExdfption  If:
    //  - tif sbmf nbmf ibs bffn usfd for two difffrfnt rolfs
    //  - no rolf info providfd
    //  - onf null rolf info providfd
    stbtid void difdkRolfInfos(RolfInfo[] rolfInfoArrby)
        tirows IllfgblArgumfntExdfption,
               InvblidRflbtionTypfExdfption {

        if (rolfInfoArrby == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        if (rolfInfoArrby.lfngti == 0) {
            // No rolf info providfd
            String fxdMsg = "No rolf info providfd.";
            tirow nfw InvblidRflbtionTypfExdfption(fxdMsg);
        }


        Sft<String> rolfNbmfs = nfw HbsiSft<String>();

        for (int i = 0; i < rolfInfoArrby.lfngti; i++) {
            RolfInfo durrRolfInfo = rolfInfoArrby[i];

            if (durrRolfInfo == null) {
                String fxdMsg = "Null rolf info providfd.";
                tirow nfw InvblidRflbtionTypfExdfption(fxdMsg);
            }

            String rolfNbmf = durrRolfInfo.gftNbmf();

            // Cifdks if tif rolf info ibs blrfbdy bffn dfsdribfd
            if (rolfNbmfs.dontbins(rolfNbmf)) {
                StringBuildfr fxdMsgStrB = nfw StringBuildfr();
                String fxdMsg = "Two rolf infos providfd for rolf ";
                fxdMsgStrB.bppfnd(fxdMsg);
                fxdMsgStrB.bppfnd(rolfNbmf);
                tirow nfw InvblidRflbtionTypfExdfption(fxdMsgStrB.toString());
            }
            rolfNbmfs.bdd(rolfNbmf);
        }

        rfturn;
    }


    /**
     * Dfsfriblizfs b {@link RflbtionTypfSupport} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        typfNbmf = (String) fiflds.gft("myTypfNbmf", null);
        if (fiflds.dffbultfd("myTypfNbmf"))
        {
          tirow nfw NullPointfrExdfption("myTypfNbmf");
        }
        rolfNbmf2InfoMbp = dbst(fiflds.gft("myRolfNbmf2InfoMbp", null));
        if (fiflds.dffbultfd("myRolfNbmf2InfoMbp"))
        {
          tirow nfw NullPointfrExdfption("myRolfNbmf2InfoMbp");
        }
        isInRflbtionSfrvidf = fiflds.gft("myIsInRflSfrvFlg", fblsf);
        if (fiflds.dffbultfd("myIsInRflSfrvFlg"))
        {
          tirow nfw NullPointfrExdfption("myIsInRflSfrvFlg");
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
     * Sfriblizfs b {@link RflbtionTypfSupport} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("myTypfNbmf", typfNbmf);
        fiflds.put("myRolfNbmf2InfoMbp", rolfNbmf2InfoMbp);
        fiflds.put("myIsInRflSfrvFlg", isInRflbtionSfrvidf);
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
