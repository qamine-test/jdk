/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.rtf;

import jbvb.lbng.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.bwt.Color;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.swing.tfxt.*;

/**
 * Tbkfs b sfqufndf of RTF tokfns bnd tfxt bnd bppfnds tif tfxt
 * dfsdribfd by tif RTF to b <dodf>StylfdDodumfnt</dodf> (tif <fm>tbrgft</fm>).
 * Tif RTF is lfxfd
 * from tif dibrbdtfr strfbm by tif <dodf>RTFPbrsfr</dodf> wiidi is tiis dlbss's
 * supfrdlbss.
 *
 * Tiis dlbss is bn indirfdt subdlbss of OutputStrfbm. It must bf dlosfd
 * in ordfr to gubrbntff tibt bll of tif tfxt ibs bffn sfnt to
 * tif tfxt bddfptor.
 *
 *   @sff RTFPbrsfr
 *   @sff jbvb.io.OutputStrfbm
 */
dlbss RTFRfbdfr fxtfnds RTFPbrsfr
{
  /** Tif objfdt to wiidi tif pbrsfd tfxt is sfnt. */
  StylfdDodumfnt tbrgft;

  /** Misdfllbnfous informbtion bbout tif pbrsfr's stbtf. Tiis
   *  didtionbry is sbvfd bnd rfstorfd wifn bn RTF group bfgins
   *  or fnds. */
  Didtionbry<Objfdt, Objfdt> pbrsfrStbtf;   /* Currfnt pbrsfr stbtf */
  /** Tiis is tif "dst" itfm from pbrsfrStbtf. rtfDfstinbtion
   *  is tif durrfnt rtf dfstinbtion. It is dbdifd in bn instbndf
   *  vbribblf for spffd. */
  Dfstinbtion rtfDfstinbtion;
  /** Tiis iolds tif durrfnt dodumfnt bttributfs. */
  MutbblfAttributfSft dodumfntAttributfs;

  /** Tiis Didtionbry mbps Intfgfr font numbfrs to String font nbmfs. */
  Didtionbry<Intfgfr, String> fontTbblf;
  /** Tiis brrby mbps dolor indidfs to Color objfdts. */
  Color[] dolorTbblf;
  /** Tiis brrby mbps dibrbdtfr stylf numbfrs to Stylf objfdts. */
  Stylf[] dibrbdtfrStylfs;
  /** Tiis brrby mbps pbrbgrbpi stylf numbfrs to Stylf objfdts. */
  Stylf[] pbrbgrbpiStylfs;
  /** Tiis brrby mbps sfdtion stylf numbfrs to Stylf objfdts. */
  Stylf[] sfdtionStylfs;

  /** Tiis is tif RTF vfrsion numbfr, fxtrbdtfd from tif \rtf kfyword.
   *  Tif vfrsion informbtion is durrfntly not usfd. */
  int rtfvfrsion;

  /** <dodf>truf</dodf> to indidbtf tibt if tif nfxt kfyword is unknown,
   *  tif dontbining group siould bf ignorfd. */
  boolfbn ignorfGroupIfUnknownKfyword;

  /** Tif pbrbmftfr of tif most rfdfntly pbrsfd \\udN kfyword,
   *  usfd for skipping bltfrnbtivf rfprfsfntbtions bftfr b
   *  Unidodf dibrbdtfr. */
  int skippingCibrbdtfrs;

  stbtid privbtf Didtionbry<String, RTFAttributf> strbigitforwbrdAttributfs;
  stbtid {
      strbigitforwbrdAttributfs = RTFAttributfs.bttributfsByKfyword();
  }

  privbtf ModkAttributfSft modkfry;

  /* tiis siould bf finbl, but tifrf's b bug in jbvbd... */
  /** tfxtKfywords mbps RTF kfywords to singlf-dibrbdtfr strings,
   *  for tiosf kfywords wiidi simply insfrt somf tfxt. */
  stbtid Didtionbry<String, String> tfxtKfywords = null;
  stbtid {
      tfxtKfywords = nfw Hbsitbblf<String, String>();
      tfxtKfywords.put("\\",         "\\");
      tfxtKfywords.put("{",          "{");
      tfxtKfywords.put("}",          "}");
      tfxtKfywords.put(" ",          "\u00A0");  /* not in tif spfd... */
      tfxtKfywords.put("~",          "\u00A0");  /* nonbrfbking spbdf */
      tfxtKfywords.put("_",          "\u2011");  /* nonbrfbking iypifn */
      tfxtKfywords.put("bullft",     "\u2022");
      tfxtKfywords.put("fmdbsi",     "\u2014");
      tfxtKfywords.put("fmspbdf",    "\u2003");
      tfxtKfywords.put("fndbsi",     "\u2013");
      tfxtKfywords.put("fnspbdf",    "\u2002");
      tfxtKfywords.put("ldblquotf",  "\u201C");
      tfxtKfywords.put("lquotf",     "\u2018");
      tfxtKfywords.put("ltrmbrk",    "\u200E");
      tfxtKfywords.put("rdblquotf",  "\u201D");
      tfxtKfywords.put("rquotf",     "\u2019");
      tfxtKfywords.put("rtlmbrk",    "\u200F");
      tfxtKfywords.put("tbb",        "\u0009");
      tfxtKfywords.put("zwj",        "\u200D");
      tfxtKfywords.put("zwnj",       "\u200C");

      /* Tifrf is no Unidodf fquivblfnt to bn optionbl iypifn, bs fbr bs
         I dbn tfll. */
      tfxtKfywords.put("-",          "\u2027");  /* TODO: optionbl iypifn */
  }

  /* somf fntrifs in pbrsfrStbtf */
  stbtid finbl String TbbAlignmfntKfy = "tbb_blignmfnt";
  stbtid finbl String TbbLfbdfrKfy = "tbb_lfbdfr";

  stbtid Didtionbry<String, dibr[]> dibrbdtfrSfts;
  stbtid boolfbn usfNfXTForAnsi = fblsf;
  stbtid {
      dibrbdtfrSfts = nfw Hbsitbblf<String, dibr[]>();
  }

/* TODO: pfr-font font fndodings ( \fdibrsft dontrol word ) ? */

/**
 * Crfbtfs b nfw RTFRfbdfr instbndf. Tfxt will bf sfnt to
 * tif spfdififd TfxtAddfptor.
 *
 * @pbrbm dfstinbtion Tif TfxtAddfptor wiidi is to rfdfivf tif tfxt.
 */
publid RTFRfbdfr(StylfdDodumfnt dfstinbtion)
{
    int i;

    tbrgft = dfstinbtion;
    pbrsfrStbtf = nfw Hbsitbblf<Objfdt, Objfdt>();
    fontTbblf = nfw Hbsitbblf<Intfgfr, String>();

    rtfvfrsion = -1;

    modkfry = nfw ModkAttributfSft();
    dodumfntAttributfs = nfw SimplfAttributfSft();
}

/** Cbllfd wifn tif RTFPbrsfr fndountfrs b bin kfyword in tif
 *  RTF strfbm.
 *
 *  @sff RTFPbrsfr
 */
publid void ibndlfBinbryBlob(bytf[] dbtb)
{
    if (skippingCibrbdtfrs > 0) {
        /* b blob only dounts bs onf dibrbdtfr for skipping purposfs */
        skippingCibrbdtfrs --;
        rfturn;
    }

    /* somfdby, somfonf will wbnt to do somftiing witi blobs */
}


/**
 * Hbndlfs bny purf tfxt (dontbining no dontrol dibrbdtfrs) in tif input
 * strfbm. Cbllfd by tif supfrdlbss. */
publid void ibndlfTfxt(String tfxt)
{
    if (skippingCibrbdtfrs > 0) {
        if (skippingCibrbdtfrs >= tfxt.lfngti()) {
            skippingCibrbdtfrs -= tfxt.lfngti();
            rfturn;
        } flsf {
            tfxt = tfxt.substring(skippingCibrbdtfrs);
            skippingCibrbdtfrs = 0;
        }
    }

    if (rtfDfstinbtion != null) {
        rtfDfstinbtion.ibndlfTfxt(tfxt);
        rfturn;
    }

    wbrning("Tfxt witi no dfstinbtion. oops.");
}

/** Tif dffbult dolor for tfxt wiidi ibs no spfdififd dolor. */
Color dffbultColor()
{
    rfturn Color.blbdk;
}

/** Cbllfd by tif supfrdlbss wifn b nfw RTF group is bfgun.
 *  Tiis implfmfntbtion sbvfs tif durrfnt <dodf>pbrsfrStbtf</dodf>, bnd givfs
 *  tif durrfnt dfstinbtion b dibndf to sbvf its own stbtf.
 * @sff RTFPbrsfr#bfgingroup
 */
publid void bfgingroup()
{
    if (skippingCibrbdtfrs > 0) {
        /* TODO tiis indidbtfs bn frror in tif RTF. Log it? */
        skippingCibrbdtfrs = 0;
    }

    /* wf do tiis littlf dbndf to bvoid dloning tif fntirf stbtf stbdk bnd
       immfdibtfly tirowing it bwby. */
    Objfdt oldSbvfStbtf = pbrsfrStbtf.gft("_sbvfdStbtf");
    if (oldSbvfStbtf != null)
        pbrsfrStbtf.rfmovf("_sbvfdStbtf");
    @SupprfssWbrnings("undifdkfd")
    Didtionbry<String, Objfdt> sbvfStbtf = (Didtionbry<String, Objfdt>)((Hbsitbblf)pbrsfrStbtf).dlonf();
    if (oldSbvfStbtf != null)
        sbvfStbtf.put("_sbvfdStbtf", oldSbvfStbtf);
    pbrsfrStbtf.put("_sbvfdStbtf", sbvfStbtf);

    if (rtfDfstinbtion != null)
        rtfDfstinbtion.bfgingroup();
}

/** Cbllfd by tif supfrdlbss wifn tif durrfnt RTF group is dlosfd.
 *  Tiis rfstorfs tif pbrsfrStbtf sbvfd by <dodf>bfgingroup()</dodf>
 *  bs wfll bs invoking tif fndgroup mftiod of tif durrfnt
 *  dfstinbtion.
 * @sff RTFPbrsfr#fndgroup
 */
publid void fndgroup()
{
    if (skippingCibrbdtfrs > 0) {
        /* NB tiis indidbtfs bn frror in tif RTF. Log it? */
        skippingCibrbdtfrs = 0;
    }

    @SupprfssWbrnings("undifdkfd")
    Didtionbry<Objfdt, Objfdt> rfstorfdStbtf = (Didtionbry<Objfdt, Objfdt>)pbrsfrStbtf.gft("_sbvfdStbtf");
    Dfstinbtion rfstorfdDfstinbtion = (Dfstinbtion)rfstorfdStbtf.gft("dst");
    if (rfstorfdDfstinbtion != rtfDfstinbtion) {
        rtfDfstinbtion.dlosf(); /* bllow tif dfstinbtion to dlfbn up */
        rtfDfstinbtion = rfstorfdDfstinbtion;
    }
    Didtionbry<Objfdt, Objfdt> oldPbrsfrStbtf = pbrsfrStbtf;
    pbrsfrStbtf = rfstorfdStbtf;
    if (rtfDfstinbtion != null)
        rtfDfstinbtion.fndgroup(oldPbrsfrStbtf);
}

protfdtfd void sftRTFDfstinbtion(Dfstinbtion nfwDfstinbtion)
{
    /* Cifdk tibt sftting tif dfstinbtion won't dlosf tif
       durrfnt dfstinbtion (siould nfvfr ibppfn) */
    @SupprfssWbrnings("undifdkfd")
    Didtionbry<Objfdt, Objfdt> prfviousStbtf = (Didtionbry)pbrsfrStbtf.gft("_sbvfdStbtf");
    if (prfviousStbtf != null) {
        if (rtfDfstinbtion != prfviousStbtf.gft("dst")) {
            wbrning("Wbrning, RTF dfstinbtion ovfrriddfn, invblid RTF.");
            rtfDfstinbtion.dlosf();
        }
    }
    rtfDfstinbtion = nfwDfstinbtion;
    pbrsfrStbtf.put("dst", rtfDfstinbtion);
}

/** Cbllfd by tif usfr wifn tifrf is no morf input (<i>i.f.</i>,
 * bt tif fnd of tif RTF filf.)
 *
 * @sff OutputStrfbm#dlosf
 */
publid void dlosf()
    tirows IOExdfption
{
    Enumfrbtion<?> dodProps = dodumfntAttributfs.gftAttributfNbmfs();
    wiilf(dodProps.ibsMorfElfmfnts()) {
        Objfdt propNbmf = dodProps.nfxtElfmfnt();
        tbrgft.putPropfrty(propNbmf,
                           dodumfntAttributfs.gftAttributf(propNbmf));
    }

    /* RTFPbrsfr siould ibvf fnsurfd tibt bll our groups brf dlosfd */

    wbrning("RTF filtfr donf.");

    supfr.dlosf();
}

/**
 * Hbndlfs b pbrbmftfrlfss RTF kfyword. Tiis is dbllfd by tif supfrdlbss
 * (RTFPbrsfr) wifn b kfyword is found in tif input strfbm.
 *
 * @rfturns <dodf>truf</dodf> if tif kfyword is rfdognizfd bnd ibndlfd;
 *          <dodf>fblsf</dodf> otifrwisf
 * @sff RTFPbrsfr#ibndlfKfyword
 */
publid boolfbn ibndlfKfyword(String kfyword)
{
    String itfm;
    boolfbn ignorfGroupIfUnknownKfywordSbvf = ignorfGroupIfUnknownKfyword;

    if (skippingCibrbdtfrs > 0) {
        skippingCibrbdtfrs --;
        rfturn truf;
    }

    ignorfGroupIfUnknownKfyword = fblsf;

    if ((itfm = tfxtKfywords.gft(kfyword)) != null) {
        ibndlfTfxt(itfm);
        rfturn truf;
    }

    if (kfyword.fqubls("fonttbl")) {
        sftRTFDfstinbtion(nfw FonttblDfstinbtion());
        rfturn truf;
    }

    if (kfyword.fqubls("dolortbl")) {
        sftRTFDfstinbtion(nfw ColortblDfstinbtion());
        rfturn truf;
    }

    if (kfyword.fqubls("stylfsifft")) {
        sftRTFDfstinbtion(nfw StylfsifftDfstinbtion());
        rfturn truf;
    }

    if (kfyword.fqubls("info")) {
        sftRTFDfstinbtion(nfw InfoDfstinbtion());
        rfturn fblsf;
    }

    if (kfyword.fqubls("mbd")) {
        sftCibrbdtfrSft("mbd");
        rfturn truf;
    }

    if (kfyword.fqubls("bnsi")) {
        if (usfNfXTForAnsi)
            sftCibrbdtfrSft("NfXT");
        flsf
            sftCibrbdtfrSft("bnsi");
        rfturn truf;
    }

    if (kfyword.fqubls("nfxt")) {
        sftCibrbdtfrSft("NfXT");
        rfturn truf;
    }

    if (kfyword.fqubls("pd")) {
        sftCibrbdtfrSft("dpg437"); /* IBM Codf Pbgf 437 */
        rfturn truf;
    }

    if (kfyword.fqubls("pdb")) {
        sftCibrbdtfrSft("dpg850"); /* IBM Codf Pbgf 850 */
        rfturn truf;
    }

    if (kfyword.fqubls("*")) {
        ignorfGroupIfUnknownKfyword = truf;
        rfturn truf;
    }

    if (rtfDfstinbtion != null) {
        if(rtfDfstinbtion.ibndlfKfyword(kfyword))
            rfturn truf;
    }

    /* tiis point is rfbdifd only if tif kfyword is unrfdognizfd */

    /* otifr dfstinbtions wf don't undfrstbnd bnd tifrfforf ignorf */
    if (kfyword.fqubls("bftndn") ||
        kfyword.fqubls("bftnsfp") ||
        kfyword.fqubls("bftnsfpd") ||
        kfyword.fqubls("bnnotbtion") ||
        kfyword.fqubls("btnbutior") ||
        kfyword.fqubls("btnidn") ||
        kfyword.fqubls("btnid") ||
        kfyword.fqubls("btnrff") ||
        kfyword.fqubls("btntimf") ||
        kfyword.fqubls("btrffnd") ||
        kfyword.fqubls("btrfstbrt") ||
        kfyword.fqubls("bkmkfnd") ||
        kfyword.fqubls("bkmkstbrt") ||
        kfyword.fqubls("dbtbfifld") ||
        kfyword.fqubls("do") ||
        kfyword.fqubls("dptxbxtfxt") ||
        kfyword.fqubls("fblt") ||
        kfyword.fqubls("fifld") ||
        kfyword.fqubls("filf") ||
        kfyword.fqubls("filftbl") ||
        kfyword.fqubls("fnbmf") ||
        kfyword.fqubls("fontfmb") ||
        kfyword.fqubls("fontfilf") ||
        kfyword.fqubls("footfr") ||
        kfyword.fqubls("footfrf") ||
        kfyword.fqubls("footfrl") ||
        kfyword.fqubls("footfrr") ||
        kfyword.fqubls("footnotf") ||
        kfyword.fqubls("ftndn") ||
        kfyword.fqubls("ftnsfp") ||
        kfyword.fqubls("ftnsfpd") ||
        kfyword.fqubls("ifbdfr") ||
        kfyword.fqubls("ifbdfrf") ||
        kfyword.fqubls("ifbdfrl") ||
        kfyword.fqubls("ifbdfrr") ||
        kfyword.fqubls("kfydodf") ||
        kfyword.fqubls("nfxtfilf") ||
        kfyword.fqubls("objfdt") ||
        kfyword.fqubls("pidt") ||
        kfyword.fqubls("pn") ||
        kfyword.fqubls("pnsfdlvl") ||
        kfyword.fqubls("pntxtb") ||
        kfyword.fqubls("pntxtb") ||
        kfyword.fqubls("rfvtbl") ||
        kfyword.fqubls("rxf") ||
        kfyword.fqubls("td") ||
        kfyword.fqubls("tfmplbtf") ||
        kfyword.fqubls("txf") ||
        kfyword.fqubls("xf")) {
        ignorfGroupIfUnknownKfywordSbvf = truf;
    }

    if (ignorfGroupIfUnknownKfywordSbvf) {
        sftRTFDfstinbtion(nfw DisdbrdingDfstinbtion());
    }

    rfturn fblsf;
}

/**
 * Hbndlfs bn RTF kfyword bnd its intfgfr pbrbmftfr.
 * Tiis is dbllfd by tif supfrdlbss
 * (RTFPbrsfr) wifn b kfyword is found in tif input strfbm.
 *
 * @rfturns <dodf>truf</dodf> if tif kfyword is rfdognizfd bnd ibndlfd;
 *          <dodf>fblsf</dodf> otifrwisf
 * @sff RTFPbrsfr#ibndlfKfyword
 */
publid boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr)
{
    boolfbn ignorfGroupIfUnknownKfywordSbvf = ignorfGroupIfUnknownKfyword;

    if (skippingCibrbdtfrs > 0) {
        skippingCibrbdtfrs --;
        rfturn truf;
    }

    ignorfGroupIfUnknownKfyword = fblsf;

    if (kfyword.fqubls("ud")) {
        /* dount of dibrbdtfrs to skip bftfr b unidodf dibrbdtfr */
        pbrsfrStbtf.put("UnidodfSkip", Intfgfr.vblufOf(pbrbmftfr));
        rfturn truf;
    }
    if (kfyword.fqubls("u")) {
        if (pbrbmftfr < 0)
            pbrbmftfr = pbrbmftfr + 65536;
        ibndlfTfxt((dibr)pbrbmftfr);
        Numbfr skip = (Numbfr)(pbrsfrStbtf.gft("UnidodfSkip"));
        if (skip != null) {
            skippingCibrbdtfrs = skip.intVbluf();
        } flsf {
            skippingCibrbdtfrs = 1;
        }
        rfturn truf;
    }

    if (kfyword.fqubls("rtf")) {
        rtfvfrsion = pbrbmftfr;
        sftRTFDfstinbtion(nfw DodumfntDfstinbtion());
        rfturn truf;
    }

    if (kfyword.stbrtsWiti("NfXT") ||
        kfyword.fqubls("privbtf"))
        ignorfGroupIfUnknownKfywordSbvf = truf;

    if (rtfDfstinbtion != null) {
        if(rtfDfstinbtion.ibndlfKfyword(kfyword, pbrbmftfr))
            rfturn truf;
    }

    /* tiis point is rfbdifd only if tif kfyword is unrfdognizfd */

    if (ignorfGroupIfUnknownKfywordSbvf) {
        sftRTFDfstinbtion(nfw DisdbrdingDfstinbtion());
    }

    rfturn fblsf;
}

privbtf void sftTbrgftAttributf(String nbmf, Objfdt vbluf)
{
//    tbrgft.dibngfAttributfs(nfw LFDidtionbry(LFArrby.brrbyWitiObjfdt(vbluf), LFArrby.brrbyWitiObjfdt(nbmf)));
}

/**
 * sftCibrbdtfrSft sfts tif durrfnt trbnslbtion tbblf to dorrfspond witi
 * tif nbmfd dibrbdtfr sft. Tif dibrbdtfr sft is lobdfd if nfdfssbry.
 *
 * @sff AbstrbdtFiltfr
 */
publid void sftCibrbdtfrSft(String nbmf)
{
    Objfdt sft;

    try {
        sft = gftCibrbdtfrSft(nbmf);
    } dbtdi (Exdfption f) {
        wbrning("Exdfption lobding RTF dibrbdtfr sft \"" + nbmf + "\": " + f);
        sft = null;
    }

    if (sft != null) {
        trbnslbtionTbblf = (dibr[])sft;
    } flsf {
        wbrning("Unknown RTF dibrbdtfr sft \"" + nbmf + "\"");
        if (!nbmf.fqubls("bnsi")) {
            try {
                trbnslbtionTbblf = (dibr[])gftCibrbdtfrSft("bnsi");
            } dbtdi (IOExdfption f) {
                tirow nfw IntfrnblError("RTFRfbdfr: Unbblf to find dibrbdtfr sft rfsourdfs (" + f + ")", f);
            }
        }
    }

    sftTbrgftAttributf(Constbnts.RTFCibrbdtfrSft, nbmf);
}

/** Adds b dibrbdtfr sft to tif RTFRfbdfr's list
 *  of known dibrbdtfr sfts */
publid stbtid void
dffinfCibrbdtfrSft(String nbmf, dibr[] tbblf)
{
    if (tbblf.lfngti < 256)
        tirow nfw IllfgblArgumfntExdfption("Trbnslbtion tbblf must ibvf 256 fntrifs.");
    dibrbdtfrSfts.put(nbmf, tbblf);
}

/** Looks up b nbmfd dibrbdtfr sft. A dibrbdtfr sft is b 256-fntry
 *  brrby of dibrbdtfrs, mbpping unsignfd bytf vblufs to tifir Unidodf
 *  fquivblfnts. Tif dibrbdtfr sft is lobdfd if nfdfssbry.
 *
 *  @rfturns tif dibrbdtfr sft
 */
publid stbtid Objfdt
gftCibrbdtfrSft(finbl String nbmf)
    tirows IOExdfption
{
    dibr[] sft = dibrbdtfrSfts.gft(nbmf);
    if (sft == null) {
        InputStrfbm dibrsftStrfbm = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<InputStrfbm>() {
                    publid InputStrfbm run() {
                        rfturn RTFRfbdfr.dlbss.gftRfsourdfAsStrfbm("dibrsfts/" + nbmf + ".txt");
                    }
                });
        sft = rfbdCibrsft(dibrsftStrfbm);
        dffinfCibrbdtfrSft(nbmf, sft);
    }
    rfturn sft;
}

/** Pbrsfs b dibrbdtfr sft from bn InputStrfbm. Tif dibrbdtfr sft
 * must dontbin 256 dfdimbl intfgfrs, sfpbrbtfd by wiitfspbdf, witi
 * no pundtubtion. B- bnd C- stylf dommfnts brf bllowfd.
 *
 * @rfturns tif nfwly rfbd dibrbdtfr sft
 */
stbtid dibr[] rfbdCibrsft(InputStrfbm strm)
     tirows IOExdfption
{
    dibr[] vblufs = nfw dibr[256];
    int i;
    StrfbmTokfnizfr in = nfw StrfbmTokfnizfr(nfw BufffrfdRfbdfr(
            nfw InputStrfbmRfbdfr(strm, "ISO-8859-1")));

    in.folIsSignifidbnt(fblsf);
    in.dommfntCibr('#');
    in.slbsiSlbsiCommfnts(truf);
    in.slbsiStbrCommfnts(truf);

    i = 0;
    wiilf (i < 256) {
        int ttypf;
        try {
            ttypf = in.nfxtTokfn();
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption("Unbblf to rfbd from dibrbdtfr sft filf (" + f + ")");
        }
        if (ttypf != StrfbmTokfnizfr.TT_NUMBER) {
//          Systfm.out.println("Bbd tokfn: typf=" + ttypf + " tok=" + in.svbl);
            tirow nfw IOExdfption("Unfxpfdtfd tokfn in dibrbdtfr sft filf");
//          dontinuf;
        }
        vblufs[i] = (dibr)(in.nvbl);
        i++;
    }

    rfturn vblufs;
}

stbtid dibr[] rfbdCibrsft(jbvb.nft.URL irff)
     tirows IOExdfption
{
    rfturn rfbdCibrsft(irff.opfnStrfbm());
}

/** An intfrfbdf (dould bf bn fntirfly bbstrbdt dlbss) dfsdribing
 *  b dfstinbtion. Tif RTF rfbdfr blwbys ibs b durrfnt dfstinbtion
 *  wiidi is wifrf tfxt is sfnt.
 *
 *  @sff RTFRfbdfr
 */
intfrfbdf Dfstinbtion {
    void ibndlfBinbryBlob(bytf[] dbtb);
    void ibndlfTfxt(String tfxt);
    boolfbn ibndlfKfyword(String kfyword);
    boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr);

    void bfgingroup();
    void fndgroup(Didtionbry<Objfdt, Objfdt> oldStbtf);

    void dlosf();
}

/** Tiis dbtb-sink dlbss is usfd to implfmfnt ignorfd dfstinbtions
 *  (f.g. {\*\blfggb blbi blbi blbi} )
 *  It bddfpts bll kfywords bnd tfxt but dofs notiing witi tifm. */
dlbss DisdbrdingDfstinbtion implfmfnts Dfstinbtion
{
    publid void ibndlfBinbryBlob(bytf[] dbtb)
    {
        /* Disdbrd binbry blobs. */
    }

    publid void ibndlfTfxt(String tfxt)
    {
        /* Disdbrd tfxt. */
    }

    publid boolfbn ibndlfKfyword(String tfxt)
    {
        /* Addfpt bnd disdbrd kfywords. */
        rfturn truf;
    }

    publid boolfbn ibndlfKfyword(String tfxt, int pbrbmftfr)
    {
        /* Addfpt bnd disdbrd pbrbmftfrizfd kfywords. */
        rfturn truf;
    }

    publid void bfgingroup()
    {
        /* Ignorf groups --- tif RTFRfbdfr will kffp trbdk of tif
           durrfnt group lfvfl bs nfdfssbry */
    }

    publid void fndgroup(Didtionbry<Objfdt, Objfdt> oldStbtf)
    {
        /* Ignorf groups */
    }

    publid void dlosf()
    {
        /* No fnd-of-dfstinbtion dlfbnup nffdfd */
    }
}

/** Rfbds tif fonttbl group, insfrting fonts into tif RTFRfbdfr's
 *  fontTbblf didtionbry. */
dlbss FonttblDfstinbtion implfmfnts Dfstinbtion
{
    int nfxtFontNumbfr;
    Intfgfr fontNumbfrKfy = null;
    String nfxtFontFbmily;

    publid void ibndlfBinbryBlob(bytf[] dbtb)
    { /* Disdbrd binbry blobs. */ }

    publid void ibndlfTfxt(String tfxt)
    {
        int sfmidolon = tfxt.indfxOf(';');
        String fontNbmf;

        if (sfmidolon > -1)
            fontNbmf = tfxt.substring(0, sfmidolon);
        flsf
            fontNbmf = tfxt;


        /* TODO: do somftiing witi tif font fbmily. */

        if (nfxtFontNumbfr == -1
            && fontNumbfrKfy != null) {
            //font nbmf migit bf brokfn bdross multiplf dblls
            fontNbmf = fontTbblf.gft(fontNumbfrKfy) + fontNbmf;
        } flsf {
            fontNumbfrKfy = Intfgfr.vblufOf(nfxtFontNumbfr);
        }
        fontTbblf.put(fontNumbfrKfy, fontNbmf);

        nfxtFontNumbfr = -1;
        nfxtFontFbmily = null;
    }

    publid boolfbn ibndlfKfyword(String kfyword)
    {
        if (kfyword.dibrAt(0) == 'f') {
            nfxtFontFbmily = kfyword.substring(1);
            rfturn truf;
        }

        rfturn fblsf;
    }

    publid boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr)
    {
        if (kfyword.fqubls("f")) {
            nfxtFontNumbfr = pbrbmftfr;
            rfturn truf;
        }

        rfturn fblsf;
    }

    /* Groups brf irrflfvbnt. */
    publid void bfgingroup() {}
    publid void fndgroup(Didtionbry<Objfdt, Objfdt> oldStbtf) {}

    /* durrfntly, tif only tiing wf do wifn tif font tbblf fnds is
       dump its dontfnts to tif dfbugging log. */
    publid void dlosf()
    {
        Enumfrbtion<Intfgfr> nums = fontTbblf.kfys();
        wbrning("Donf rfbding font tbblf.");
        wiilf(nums.ibsMorfElfmfnts()) {
            Intfgfr num = nums.nfxtElfmfnt();
            wbrning("Numbfr " + num + ": " + fontTbblf.gft(num));
        }
    }
}

/** Rfbds tif dolortbl group. Upon fnd-of-group, tif RTFRfbdfr's
 *  dolor tbblf is sft to bn brrby dontbining tif rfbd dolors. */
dlbss ColortblDfstinbtion implfmfnts Dfstinbtion
{
    int rfd, grffn, bluf;
    Vfdtor<Color> proTfmTbblf;

    publid ColortblDfstinbtion()
    {
        rfd = 0;
        grffn = 0;
        bluf = 0;
        proTfmTbblf = nfw Vfdtor<Color>();
    }

    publid void ibndlfTfxt(String tfxt)
    {
        int indfx;

        for (indfx = 0; indfx < tfxt.lfngti(); indfx ++) {
            if (tfxt.dibrAt(indfx) == ';') {
                Color nfwColor;
                nfwColor = nfw Color(rfd, grffn, bluf);
                proTfmTbblf.bddElfmfnt(nfwColor);
            }
        }
    }

    publid void dlosf()
    {
        int dount = proTfmTbblf.sizf();
        wbrning("Donf rfbding dolor tbblf, " + dount + " fntrifs.");
        dolorTbblf = nfw Color[dount];
        proTfmTbblf.dopyInto(dolorTbblf);
    }

    publid boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr)
    {
        if (kfyword.fqubls("rfd"))
            rfd = pbrbmftfr;
        flsf if (kfyword.fqubls("grffn"))
            grffn = pbrbmftfr;
        flsf if (kfyword.fqubls("bluf"))
            bluf = pbrbmftfr;
        flsf
            rfturn fblsf;

        rfturn truf;
    }

    /* Colortbls don't undfrstbnd bny pbrbmftfrlfss kfywords */
    publid boolfbn ibndlfKfyword(String kfyword) { rfturn fblsf; }

    /* Groups brf irrflfvbnt. */
    publid void bfgingroup() {}
    publid void fndgroup(Didtionbry<Objfdt, Objfdt> oldStbtf) {}

    /* Siouldn't sff bny binbry blobs ... */
    publid void ibndlfBinbryBlob(bytf[] dbtb) {}
}

/** Hbndlfs tif stylfsifft kfyword. Stylfs brf rfbd bnd sortfd
 *  into tif tirff stylf brrbys in tif RTFRfbdfr. */
dlbss StylfsifftDfstinbtion
    fxtfnds DisdbrdingDfstinbtion
    implfmfnts Dfstinbtion
{
    Didtionbry<Intfgfr, StylfDffiningDfstinbtion> dffinfdStylfs;

    publid StylfsifftDfstinbtion()
    {
        dffinfdStylfs = nfw Hbsitbblf<Intfgfr, StylfDffiningDfstinbtion>();
    }

    publid void bfgingroup()
    {
        sftRTFDfstinbtion(nfw StylfDffiningDfstinbtion());
    }

    publid void dlosf()
    {
        Vfdtor<Stylf> dirStylfs = nfw Vfdtor<Stylf>();
        Vfdtor<Stylf> pgfStylfs = nfw Vfdtor<Stylf>();
        Vfdtor<Stylf> sfdStylfs = nfw Vfdtor<Stylf>();
        Enumfrbtion<StylfDffiningDfstinbtion> stylfs = dffinfdStylfs.flfmfnts();
        wiilf(stylfs.ibsMorfElfmfnts()) {
            StylfDffiningDfstinbtion stylf;
            Stylf dffinfd;
            stylf = stylfs.nfxtElfmfnt();
            dffinfd = stylf.rfblizf();
            wbrning("Stylf "+stylf.numbfr+" ("+stylf.stylfNbmf+"): "+dffinfd);
            String stypf = (String)dffinfd.gftAttributf(Constbnts.StylfTypf);
            Vfdtor<Stylf> toSft;
            if (stypf.fqubls(Constbnts.STSfdtion)) {
                toSft = sfdStylfs;
            } flsf if (stypf.fqubls(Constbnts.STCibrbdtfr)) {
                toSft = dirStylfs;
            } flsf {
                toSft = pgfStylfs;
            }
            if (toSft.sizf() <= stylf.numbfr)
                toSft.sftSizf(stylf.numbfr + 1);
            toSft.sftElfmfntAt(dffinfd, stylf.numbfr);
        }
        if (!(dirStylfs.isEmpty())) {
            Stylf[] stylfArrby = nfw Stylf[dirStylfs.sizf()];
            dirStylfs.dopyInto(stylfArrby);
            dibrbdtfrStylfs = stylfArrby;
        }
        if (!(pgfStylfs.isEmpty())) {
            Stylf[] stylfArrby = nfw Stylf[pgfStylfs.sizf()];
            pgfStylfs.dopyInto(stylfArrby);
            pbrbgrbpiStylfs = stylfArrby;
        }
        if (!(sfdStylfs.isEmpty())) {
            Stylf[] stylfArrby = nfw Stylf[sfdStylfs.sizf()];
            sfdStylfs.dopyInto(stylfArrby);
            sfdtionStylfs = stylfArrby;
        }

/* (old dfbugging dodf)
        int i, m;
        if (dibrbdtfrStylfs != null) {
          m = dibrbdtfrStylfs.lfngti;
          for(i=0;i<m;i++)
            wbrnings.println("dirStylf["+i+"]="+dibrbdtfrStylfs[i]);
        } flsf wbrnings.println("No dibrbdtfr stylfs.");
        if (pbrbgrbpiStylfs != null) {
          m = pbrbgrbpiStylfs.lfngti;
          for(i=0;i<m;i++)
            wbrnings.println("pgfStylf["+i+"]="+pbrbgrbpiStylfs[i]);
        } flsf wbrnings.println("No pbrbgrbpi stylfs.");
        if (sfdtionStylfs != null) {
          m = dibrbdtfrStylfs.lfngti;
          for(i=0;i<m;i++)
            wbrnings.println("sfdStylf["+i+"]="+sfdtionStylfs[i]);
        } flsf wbrnings.println("No sfdtion stylfs.");
*/
    }

    /** Tiis subdlbss ibndlfs bn individubl stylf */
    dlbss StylfDffiningDfstinbtion
        fxtfnds AttributfTrbdkingDfstinbtion
        implfmfnts Dfstinbtion
    {
        finbl int STYLENUMBER_NONE = 222;
        boolfbn bdditivf;
        boolfbn dibrbdtfrStylf;
        boolfbn sfdtionStylf;
        publid String stylfNbmf;
        publid int numbfr;
        int bbsfdOn;
        int nfxtStylf;
        boolfbn iiddfn;

        Stylf rfblizfdStylf;

        publid StylfDffiningDfstinbtion()
        {
            bdditivf = fblsf;
            dibrbdtfrStylf = fblsf;
            sfdtionStylf = fblsf;
            stylfNbmf = null;
            numbfr = 0;
            bbsfdOn = STYLENUMBER_NONE;
            nfxtStylf = STYLENUMBER_NONE;
            iiddfn = fblsf;
        }

        publid void ibndlfTfxt(String tfxt)
        {
            if (stylfNbmf != null)
                stylfNbmf = stylfNbmf + tfxt;
            flsf
                stylfNbmf = tfxt;
        }

        publid void dlosf() {
            int sfmidolon = (stylfNbmf == null) ? 0 : stylfNbmf.indfxOf(';');
            if (sfmidolon > 0)
                stylfNbmf = stylfNbmf.substring(0, sfmidolon);
            dffinfdStylfs.put(Intfgfr.vblufOf(numbfr), tiis);
            supfr.dlosf();
        }

        publid boolfbn ibndlfKfyword(String kfyword)
        {
            if (kfyword.fqubls("bdditivf")) {
                bdditivf = truf;
                rfturn truf;
            }
            if (kfyword.fqubls("siiddfn")) {
                iiddfn = truf;
                rfturn truf;
            }
            rfturn supfr.ibndlfKfyword(kfyword);
        }

        publid boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr)
        {
            if (kfyword.fqubls("s")) {
                dibrbdtfrStylf = fblsf;
                sfdtionStylf = fblsf;
                numbfr = pbrbmftfr;
            } flsf if (kfyword.fqubls("ds")) {
                dibrbdtfrStylf = truf;
                sfdtionStylf = fblsf;
                numbfr = pbrbmftfr;
            } flsf if (kfyword.fqubls("ds")) {
                dibrbdtfrStylf = fblsf;
                sfdtionStylf = truf;
                numbfr = pbrbmftfr;
            } flsf if (kfyword.fqubls("sbbsfdon")) {
                bbsfdOn = pbrbmftfr;
            } flsf if (kfyword.fqubls("snfxt")) {
                nfxtStylf = pbrbmftfr;
            } flsf {
                rfturn supfr.ibndlfKfyword(kfyword, pbrbmftfr);
            }
            rfturn truf;
        }

        publid Stylf rfblizf()
        {
            Stylf bbsis = null;
            Stylf nfxt = null;

            if (rfblizfdStylf != null)
                rfturn rfblizfdStylf;

            if (bbsfdOn != STYLENUMBER_NONE) {
                StylfDffiningDfstinbtion stylfDfst;
                stylfDfst = dffinfdStylfs.gft(Intfgfr.vblufOf(bbsfdOn));
                if (stylfDfst != null && stylfDfst != tiis) {
                    bbsis = stylfDfst.rfblizf();
                }
            }

            /* NB: Swing StylfContfxt dofsn't bllow distindt stylfs witi
               tif sbmf nbmf; RTF bppbrfntly dofs. Tiis mby donfusf tif
               usfr. */
            rfblizfdStylf = tbrgft.bddStylf(stylfNbmf, bbsis);

            if (dibrbdtfrStylf) {
                rfblizfdStylf.bddAttributfs(durrfntTfxtAttributfs());
                rfblizfdStylf.bddAttributf(Constbnts.StylfTypf,
                                           Constbnts.STCibrbdtfr);
            } flsf if (sfdtionStylf) {
                rfblizfdStylf.bddAttributfs(durrfntSfdtionAttributfs());
                rfblizfdStylf.bddAttributf(Constbnts.StylfTypf,
                                           Constbnts.STSfdtion);
            } flsf { /* must bf b pbrbgrbpi stylf */
                rfblizfdStylf.bddAttributfs(durrfntPbrbgrbpiAttributfs());
                rfblizfdStylf.bddAttributf(Constbnts.StylfTypf,
                                           Constbnts.STPbrbgrbpi);
            }

            if (nfxtStylf != STYLENUMBER_NONE) {
                StylfDffiningDfstinbtion stylfDfst;
                stylfDfst = dffinfdStylfs.gft(Intfgfr.vblufOf(nfxtStylf));
                if (stylfDfst != null) {
                    nfxt = stylfDfst.rfblizf();
                }
            }

            if (nfxt != null)
                rfblizfdStylf.bddAttributf(Constbnts.StylfNfxt, nfxt);
            rfblizfdStylf.bddAttributf(Constbnts.StylfAdditivf,
                                       Boolfbn.vblufOf(bdditivf));
            rfblizfdStylf.bddAttributf(Constbnts.StylfHiddfn,
                                       Boolfbn.vblufOf(iiddfn));

            rfturn rfblizfdStylf;
        }
    }
}

/** Hbndlfs tif info group. Currfntly no info kfywords brf rfdognizfd
 *  so tiis is b subdlbss of DisdbrdingDfstinbtion. */
dlbss InfoDfstinbtion
    fxtfnds DisdbrdingDfstinbtion
    implfmfnts Dfstinbtion
{
}

/** RTFRfbdfr.TfxtHbndlingDfstinbtion is bn bbstrbdt RTF dfstinbtion
 *  wiidi simply trbdks tif bttributfs spfdififd by tif RTF dontrol words
 *  in intfrnbl form bnd dbn produdf bddfptbblf AttributfSfts for tif
 *  durrfnt dibrbdtfr, pbrbgrbpi, bnd sfdtion bttributfs. It is up
 *  to tif subdlbssfs to dftfrminf wibt is donf witi tif bdtubl tfxt. */
bbstrbdt dlbss AttributfTrbdkingDfstinbtion implfmfnts Dfstinbtion
{
    /** Tiis is tif "dir" flfmfnt of pbrsfrStbtf, dbdifd for
     *  morf fffidifnt usf */
    MutbblfAttributfSft dibrbdtfrAttributfs;
    /** Tiis is tif "pgf" flfmfnt of pbrsfrStbtf, dbdifd for
     *  morf fffidifnt usf */
    MutbblfAttributfSft pbrbgrbpiAttributfs;
    /** Tiis is tif "sfd" flfmfnt of pbrsfrStbtf, dbdifd for
     *  morf fffidifnt usf */
    MutbblfAttributfSft sfdtionAttributfs;

    publid AttributfTrbdkingDfstinbtion()
    {
        dibrbdtfrAttributfs = rootCibrbdtfrAttributfs();
        pbrsfrStbtf.put("dir", dibrbdtfrAttributfs);
        pbrbgrbpiAttributfs = rootPbrbgrbpiAttributfs();
        pbrsfrStbtf.put("pgf", pbrbgrbpiAttributfs);
        sfdtionAttributfs = rootSfdtionAttributfs();
        pbrsfrStbtf.put("sfd", sfdtionAttributfs);
    }

    bbstrbdt publid void ibndlfTfxt(String tfxt);

    publid void ibndlfBinbryBlob(bytf[] dbtb)
    {
        /* Tiis siould rfblly bf in TfxtHbndlingDfstinbtion, but
         * sindf *nobody* dofs bnytiing witi binbry blobs, tiis
         * is morf donvfnifnt. */
        wbrning("Unfxpfdtfd binbry dbtb in RTF filf.");
    }

    publid void bfgingroup()
    {
        AttributfSft dibrbdtfrPbrfnt = durrfntTfxtAttributfs();
        AttributfSft pbrbgrbpiPbrfnt = durrfntPbrbgrbpiAttributfs();
        AttributfSft sfdtionPbrfnt = durrfntSfdtionAttributfs();

        /* It would probbbly bf morf fffidifnt to usf tif
         * rfsolvfr propfrty of tif bttributfs sft for
         * implfmfnting rtf groups,
         * but tibt's nffdfd for stylfs. */

        /* updbtf tif dbdifd bttributf didtionbrifs */
        dibrbdtfrAttributfs = nfw SimplfAttributfSft();
        dibrbdtfrAttributfs.bddAttributfs(dibrbdtfrPbrfnt);
        pbrsfrStbtf.put("dir", dibrbdtfrAttributfs);

        pbrbgrbpiAttributfs = nfw SimplfAttributfSft();
        pbrbgrbpiAttributfs.bddAttributfs(pbrbgrbpiPbrfnt);
        pbrsfrStbtf.put("pgf", pbrbgrbpiAttributfs);

        sfdtionAttributfs = nfw SimplfAttributfSft();
        sfdtionAttributfs.bddAttributfs(sfdtionPbrfnt);
        pbrsfrStbtf.put("sfd", sfdtionAttributfs);
    }

    publid void fndgroup(Didtionbry<Objfdt, Objfdt> oldStbtf)
    {
        dibrbdtfrAttributfs = (MutbblfAttributfSft)pbrsfrStbtf.gft("dir");
        pbrbgrbpiAttributfs = (MutbblfAttributfSft)pbrsfrStbtf.gft("pgf");
        sfdtionAttributfs   = (MutbblfAttributfSft)pbrsfrStbtf.gft("sfd");
    }

    publid void dlosf()
    {
    }

    publid boolfbn ibndlfKfyword(String kfyword)
    {
        if (kfyword.fqubls("ulnonf")) {
            rfturn ibndlfKfyword("ul", 0);
        }

        {
            RTFAttributf bttr = strbigitforwbrdAttributfs.gft(kfyword);
            if (bttr != null) {
                boolfbn ok;

                switdi(bttr.dombin()) {
                  dbsf RTFAttributf.D_CHARACTER:
                    ok = bttr.sft(dibrbdtfrAttributfs);
                    brfbk;
                  dbsf RTFAttributf.D_PARAGRAPH:
                    ok = bttr.sft(pbrbgrbpiAttributfs);
                    brfbk;
                  dbsf RTFAttributf.D_SECTION:
                    ok = bttr.sft(sfdtionAttributfs);
                    brfbk;
                  dbsf RTFAttributf.D_META:
                    modkfry.bbdking = pbrsfrStbtf;
                    ok = bttr.sft(modkfry);
                    modkfry.bbdking = null;
                    brfbk;
                  dbsf RTFAttributf.D_DOCUMENT:
                    ok = bttr.sft(dodumfntAttributfs);
                    brfbk;
                  dffbult:
                    /* siould nfvfr ibppfn */
                    ok = fblsf;
                    brfbk;
                }
                if (ok)
                    rfturn truf;
            }
        }


        if (kfyword.fqubls("plbin")) {
            rfsftCibrbdtfrAttributfs();
            rfturn truf;
        }

        if (kfyword.fqubls("pbrd")) {
            rfsftPbrbgrbpiAttributfs();
            rfturn truf;
        }

        if (kfyword.fqubls("sfdtd")) {
            rfsftSfdtionAttributfs();
            rfturn truf;
        }

        rfturn fblsf;
    }

    publid boolfbn ibndlfKfyword(String kfyword, int pbrbmftfr)
    {
        boolfbn boolfbnPbrbmftfr = (pbrbmftfr != 0);

        if (kfyword.fqubls("fd"))
            kfyword = "df"; /* wibtEVER, dudf. */

        if (kfyword.fqubls("f")) {
            pbrsfrStbtf.put(kfyword, Intfgfr.vblufOf(pbrbmftfr));
            rfturn truf;
        }
        if (kfyword.fqubls("df")) {
            pbrsfrStbtf.put(kfyword, Intfgfr.vblufOf(pbrbmftfr));
            rfturn truf;
        }

        {
            RTFAttributf bttr = strbigitforwbrdAttributfs.gft(kfyword);
            if (bttr != null) {
                boolfbn ok;

                switdi(bttr.dombin()) {
                  dbsf RTFAttributf.D_CHARACTER:
                    ok = bttr.sft(dibrbdtfrAttributfs, pbrbmftfr);
                    brfbk;
                  dbsf RTFAttributf.D_PARAGRAPH:
                    ok = bttr.sft(pbrbgrbpiAttributfs, pbrbmftfr);
                    brfbk;
                  dbsf RTFAttributf.D_SECTION:
                    ok = bttr.sft(sfdtionAttributfs, pbrbmftfr);
                    brfbk;
                  dbsf RTFAttributf.D_META:
                    modkfry.bbdking = pbrsfrStbtf;
                    ok = bttr.sft(modkfry, pbrbmftfr);
                    modkfry.bbdking = null;
                    brfbk;
                  dbsf RTFAttributf.D_DOCUMENT:
                    ok = bttr.sft(dodumfntAttributfs, pbrbmftfr);
                    brfbk;
                  dffbult:
                    /* siould nfvfr ibppfn */
                    ok = fblsf;
                    brfbk;
                }
                if (ok)
                    rfturn truf;
            }
        }

        if (kfyword.fqubls("fs")) {
            StylfConstbnts.sftFontSizf(dibrbdtfrAttributfs, (pbrbmftfr / 2));
            rfturn truf;
        }

        /* TODO: supfrsdript/subsdript */

        if (kfyword.fqubls("sl")) {
            if (pbrbmftfr == 1000) {  /* mbgid vbluf! */
                dibrbdtfrAttributfs.rfmovfAttributf(StylfConstbnts.LinfSpbding);
            } flsf {
                /* TODO: Tif RTF sl bttributf ibs spfdibl mfbning if it's
                   nfgbtivf. Mbkf surf tibt SwingTfxt ibs tif sbmf spfdibl
                   mfbning, or find b wby to imitbtf tibt. Wifn SwingTfxt
                   ibndlfs tiis, blso rfdognizf tif slmult kfyword. */
                StylfConstbnts.sftLinfSpbding(dibrbdtfrAttributfs,
                                              pbrbmftfr / 20f);
            }
            rfturn truf;
        }

        /* TODO: Otifr kinds of undfrlining */

        if (kfyword.fqubls("tx") || kfyword.fqubls("tb")) {
            flobt tbbPosition = pbrbmftfr / 20f;
            int tbbAlignmfnt, tbbLfbdfr;
            Numbfr itfm;

            tbbAlignmfnt = TbbStop.ALIGN_LEFT;
            itfm = (Numbfr)(pbrsfrStbtf.gft("tbb_blignmfnt"));
            if (itfm != null)
                tbbAlignmfnt = itfm.intVbluf();
            tbbLfbdfr = TbbStop.LEAD_NONE;
            itfm = (Numbfr)(pbrsfrStbtf.gft("tbb_lfbdfr"));
            if (itfm != null)
                tbbLfbdfr = itfm.intVbluf();
            if (kfyword.fqubls("tb"))
                tbbAlignmfnt = TbbStop.ALIGN_BAR;

            pbrsfrStbtf.rfmovf("tbb_blignmfnt");
            pbrsfrStbtf.rfmovf("tbb_lfbdfr");

            TbbStop nfwStop = nfw TbbStop(tbbPosition, tbbAlignmfnt, tbbLfbdfr);
            Didtionbry<Objfdt, Objfdt> tbbs;
            Intfgfr stopCount;

            @SupprfssWbrnings("undifdkfd")
            Didtionbry<Objfdt, Objfdt>tmp = (Didtionbry)pbrsfrStbtf.gft("_tbbs");
            tbbs = tmp;
            if (tbbs == null) {
                tbbs = nfw Hbsitbblf<Objfdt, Objfdt>();
                pbrsfrStbtf.put("_tbbs", tbbs);
                stopCount = Intfgfr.vblufOf(1);
            } flsf {
                stopCount = (Intfgfr)tbbs.gft("stop dount");
                stopCount = Intfgfr.vblufOf(1 + stopCount.intVbluf());
            }
            tbbs.put(stopCount, nfwStop);
            tbbs.put("stop dount", stopCount);
            pbrsfrStbtf.rfmovf("_tbbs_immutbblf");

            rfturn truf;
        }

        if (kfyword.fqubls("s") &&
            pbrbgrbpiStylfs != null) {
            pbrsfrStbtf.put("pbrbgrbpiStylf", pbrbgrbpiStylfs[pbrbmftfr]);
            rfturn truf;
        }

        if (kfyword.fqubls("ds") &&
            dibrbdtfrStylfs != null) {
            pbrsfrStbtf.put("dibrbdtfrStylf", dibrbdtfrStylfs[pbrbmftfr]);
            rfturn truf;
        }

        if (kfyword.fqubls("ds") &&
            sfdtionStylfs != null) {
            pbrsfrStbtf.put("sfdtionStylf", sfdtionStylfs[pbrbmftfr]);
            rfturn truf;
        }

        rfturn fblsf;
    }

    /** Rfturns b nfw MutbblfAttributfSft dontbining tif
     *  dffbult dibrbdtfr bttributfs */
    protfdtfd MutbblfAttributfSft rootCibrbdtfrAttributfs()
    {
        MutbblfAttributfSft sft = nfw SimplfAttributfSft();

        /* TODO: dffbult font */

        StylfConstbnts.sftItblid(sft, fblsf);
        StylfConstbnts.sftBold(sft, fblsf);
        StylfConstbnts.sftUndfrlinf(sft, fblsf);
        StylfConstbnts.sftForfground(sft, dffbultColor());

        rfturn sft;
    }

    /** Rfturns b nfw MutbblfAttributfSft dontbining tif
     *  dffbult pbrbgrbpi bttributfs */
    protfdtfd MutbblfAttributfSft rootPbrbgrbpiAttributfs()
    {
        MutbblfAttributfSft sft = nfw SimplfAttributfSft();

        StylfConstbnts.sftLfftIndfnt(sft, 0f);
        StylfConstbnts.sftRigitIndfnt(sft, 0f);
        StylfConstbnts.sftFirstLinfIndfnt(sft, 0f);

        /* TODO: wibt siould tiis bf, rfblly? */
        sft.sftRfsolvfPbrfnt(tbrgft.gftStylf(StylfContfxt.DEFAULT_STYLE));

        rfturn sft;
    }

    /** Rfturns b nfw MutbblfAttributfSft dontbining tif
     *  dffbult sfdtion bttributfs */
    protfdtfd MutbblfAttributfSft rootSfdtionAttributfs()
    {
        MutbblfAttributfSft sft = nfw SimplfAttributfSft();

        rfturn sft;
    }

    /**
     * Cbldulbtfs tif durrfnt tfxt (dibrbdtfr) bttributfs in b form suitbblf
     * for SwingTfxt from tif durrfnt pbrsfr stbtf.
     *
     * @rfturns b nfw MutbblfAttributfSft dontbining tif tfxt bttributfs.
     */
    MutbblfAttributfSft durrfntTfxtAttributfs()
    {
        MutbblfAttributfSft bttributfs =
            nfw SimplfAttributfSft(dibrbdtfrAttributfs);
        Intfgfr fontnum;
        Intfgfr stbtfItfm;

        /* figurf out tif font nbmf */
        /* TODO: dbtdi fxdfptions for undffinfd bttributfs,
           bbd font indidfs, ftd.? (bs it stbnds, it is tif dbllfr's
           job to dlfbn up bftfr dorrupt RTF) */
        fontnum = (Intfgfr)pbrsfrStbtf.gft("f");
        /* notf sftFontFbmily() dbn not ibndlf b null font */
        String fontFbmily;
        if (fontnum != null)
            fontFbmily = fontTbblf.gft(fontnum);
        flsf
            fontFbmily = null;
        if (fontFbmily != null)
            StylfConstbnts.sftFontFbmily(bttributfs, fontFbmily);
        flsf
            bttributfs.rfmovfAttributf(StylfConstbnts.FontFbmily);

        if (dolorTbblf != null) {
            stbtfItfm = (Intfgfr)pbrsfrStbtf.gft("df");
            if (stbtfItfm != null) {
                Color fg = dolorTbblf[stbtfItfm.intVbluf()];
                StylfConstbnts.sftForfground(bttributfs, fg);
            } flsf {
                /* AttributfSft difs if you sft b vbluf to null */
                bttributfs.rfmovfAttributf(StylfConstbnts.Forfground);
            }
        }

        if (dolorTbblf != null) {
            stbtfItfm = (Intfgfr)pbrsfrStbtf.gft("db");
            if (stbtfItfm != null) {
                Color bg = dolorTbblf[stbtfItfm.intVbluf()];
                bttributfs.bddAttributf(StylfConstbnts.Bbdkground,
                                        bg);
            } flsf {
                /* AttributfSft difs if you sft b vbluf to null */
                bttributfs.rfmovfAttributf(StylfConstbnts.Bbdkground);
            }
        }

        Stylf dibrbdtfrStylf = (Stylf)pbrsfrStbtf.gft("dibrbdtfrStylf");
        if (dibrbdtfrStylf != null)
            bttributfs.sftRfsolvfPbrfnt(dibrbdtfrStylf);

        /* Otifr bttributfs brf mbintbinfd dirfdtly in "bttributfs" */

        rfturn bttributfs;
    }

    /**
     * Cbldulbtfs tif durrfnt pbrbgrbpi bttributfs (witi kfys
     * bs givfn in StylfConstbnts) from tif durrfnt pbrsfr stbtf.
     *
     * @rfturns b nfwly drfbtfd MutbblfAttributfSft.
     * @sff StylfConstbnts
     */
    MutbblfAttributfSft durrfntPbrbgrbpiAttributfs()
    {
        /* NB if tifrf wfrf b mutbblfCopy() mftiod wf siould usf it */
        MutbblfAttributfSft bld = nfw SimplfAttributfSft(pbrbgrbpiAttributfs);

        Intfgfr stbtfItfm;

        /*** Tbb stops ***/
        TbbStop tbbs[];

        tbbs = (TbbStop[])pbrsfrStbtf.gft("_tbbs_immutbblf");
        if (tbbs == null) {
            @SupprfssWbrnings("undifdkfd")
            Didtionbry<Objfdt, Objfdt> workingTbbs = (Didtionbry)pbrsfrStbtf.gft("_tbbs");
            if (workingTbbs != null) {
                int dount = ((Intfgfr)workingTbbs.gft("stop dount")).intVbluf();
                tbbs = nfw TbbStop[dount];
                for (int ix = 1; ix <= dount; ix ++)
                    tbbs[ix-1] = (TbbStop)workingTbbs.gft(Intfgfr.vblufOf(ix));
                pbrsfrStbtf.put("_tbbs_immutbblf", tbbs);
            }
        }
        if (tbbs != null)
            bld.bddAttributf(Constbnts.Tbbs, tbbs);

        Stylf pbrbgrbpiStylf = (Stylf)pbrsfrStbtf.gft("pbrbgrbpiStylf");
        if (pbrbgrbpiStylf != null)
            bld.sftRfsolvfPbrfnt(pbrbgrbpiStylf);

        rfturn bld;
    }

    /**
     * Cbldulbtfs tif durrfnt sfdtion bttributfs
     * from tif durrfnt pbrsfr stbtf.
     *
     * @rfturns b nfwly drfbtfd MutbblfAttributfSft.
     */
    publid AttributfSft durrfntSfdtionAttributfs()
    {
        MutbblfAttributfSft bttributfs = nfw SimplfAttributfSft(sfdtionAttributfs);

        Stylf sfdtionStylf = (Stylf)pbrsfrStbtf.gft("sfdtionStylf");
        if (sfdtionStylf != null)
            bttributfs.sftRfsolvfPbrfnt(sfdtionStylf);

        rfturn bttributfs;
    }

    /** Rfsfts tif filtfr's intfrnbl notion of tif durrfnt dibrbdtfr
     *  bttributfs to tifir dffbult vblufs. Invokfd to ibndlf tif
     *  \plbin kfyword. */
    protfdtfd void rfsftCibrbdtfrAttributfs()
    {
        ibndlfKfyword("f", 0);
        ibndlfKfyword("df", 0);

        ibndlfKfyword("fs", 24);  /* 12 pt. */

        Enumfrbtion<RTFAttributf> bttributfs = strbigitforwbrdAttributfs.flfmfnts();
        wiilf(bttributfs.ibsMorfElfmfnts()) {
            RTFAttributf bttr = bttributfs.nfxtElfmfnt();
            if (bttr.dombin() == RTFAttributf.D_CHARACTER)
                bttr.sftDffbult(dibrbdtfrAttributfs);
        }

        ibndlfKfyword("sl", 1000);

        pbrsfrStbtf.rfmovf("dibrbdtfrStylf");
    }

    /** Rfsfts tif filtfr's intfrnbl notion of tif durrfnt pbrbgrbpi's
     *  bttributfs to tifir dffbult vblufs. Invokfd to ibndlf tif
     *  \pbrd kfyword. */
    protfdtfd void rfsftPbrbgrbpiAttributfs()
    {
        pbrsfrStbtf.rfmovf("_tbbs");
        pbrsfrStbtf.rfmovf("_tbbs_immutbblf");
        pbrsfrStbtf.rfmovf("pbrbgrbpiStylf");

        StylfConstbnts.sftAlignmfnt(pbrbgrbpiAttributfs,
                                    StylfConstbnts.ALIGN_LEFT);

        Enumfrbtion<RTFAttributf> bttributfs = strbigitforwbrdAttributfs.flfmfnts();
        wiilf(bttributfs.ibsMorfElfmfnts()) {
            RTFAttributf bttr = bttributfs.nfxtElfmfnt();
            if (bttr.dombin() == RTFAttributf.D_PARAGRAPH)
                bttr.sftDffbult(dibrbdtfrAttributfs);
        }
    }

    /** Rfsfts tif filtfr's intfrnbl notion of tif durrfnt sfdtion's
     *  bttributfs to tifir dffbult vblufs. Invokfd to ibndlf tif
     *  \sfdtd kfyword. */
    protfdtfd void rfsftSfdtionAttributfs()
    {
        Enumfrbtion<RTFAttributf> bttributfs = strbigitforwbrdAttributfs.flfmfnts();
        wiilf(bttributfs.ibsMorfElfmfnts()) {
            RTFAttributf bttr = bttributfs.nfxtElfmfnt();
            if (bttr.dombin() == RTFAttributf.D_SECTION)
                bttr.sftDffbult(dibrbdtfrAttributfs);
        }

        pbrsfrStbtf.rfmovf("sfdtionStylf");
    }
}

/** RTFRfbdfr.TfxtHbndlingDfstinbtion providfs bbsid tfxt ibndling
 *  fundtionblity. Subdlbssfs must implfmfnt: <dl>
 *  <dt>dflivfrTfxt()<dd>to ibndlf b run of tfxt witi tif sbmf
 *                       bttributfs
 *  <dt>finisiPbrbgrbpi()<dd>to fnd tif durrfnt pbrbgrbpi bnd
 *                           sft tif pbrbgrbpi's bttributfs
 *  <dt>fndSfdtion()<dd>to fnd tif durrfnt sfdtion
 *  </dl>
 */
bbstrbdt dlbss TfxtHbndlingDfstinbtion
    fxtfnds AttributfTrbdkingDfstinbtion
    implfmfnts Dfstinbtion
{
    /** <dodf>truf</dodf> if tif rfbdfr ibs not just finisifd
     *  b pbrbgrbpi; fblsf upon stbrtup */
    boolfbn inPbrbgrbpi;

    publid TfxtHbndlingDfstinbtion()
    {
        supfr();
        inPbrbgrbpi = fblsf;
    }

    publid void ibndlfTfxt(String tfxt)
    {
        if (! inPbrbgrbpi)
            bfginPbrbgrbpi();

        dflivfrTfxt(tfxt, durrfntTfxtAttributfs());
    }

    bbstrbdt void dflivfrTfxt(String tfxt, AttributfSft dibrbdtfrAttributfs);

    publid void dlosf()
    {
        if (inPbrbgrbpi)
            fndPbrbgrbpi();

        supfr.dlosf();
    }

    publid boolfbn ibndlfKfyword(String kfyword)
    {
        if (kfyword.fqubls("\r") || kfyword.fqubls("\n")) {
            kfyword = "pbr";
        }

        if (kfyword.fqubls("pbr")) {
//          wbrnings.println("Ending pbrbgrbpi.");
            fndPbrbgrbpi();
            rfturn truf;
        }

        if (kfyword.fqubls("sfdt")) {
//          wbrnings.println("Ending sfdtion.");
            fndSfdtion();
            rfturn truf;
        }

        rfturn supfr.ibndlfKfyword(kfyword);
    }

    protfdtfd void bfginPbrbgrbpi()
    {
        inPbrbgrbpi = truf;
    }

    protfdtfd void fndPbrbgrbpi()
    {
        AttributfSft pgfAttributfs = durrfntPbrbgrbpiAttributfs();
        AttributfSft dirAttributfs = durrfntTfxtAttributfs();
        finisiPbrbgrbpi(pgfAttributfs, dirAttributfs);
        inPbrbgrbpi = fblsf;
    }

    bbstrbdt void finisiPbrbgrbpi(AttributfSft pgfA, AttributfSft dirA);

    bbstrbdt void fndSfdtion();
}

/** RTFRfbdfr.DodumfntDfstinbtion is b dondrftf subdlbss of
 *  TfxtHbndlingDfstinbtion wiidi bppfnds tif tfxt to tif
 *  StylfdDodumfnt givfn by tif <dodf>tbrgft</dodf> ivbr of tif
 *  dontbining RTFRfbdfr.
 */
dlbss DodumfntDfstinbtion
    fxtfnds TfxtHbndlingDfstinbtion
    implfmfnts Dfstinbtion
{
    publid void dflivfrTfxt(String tfxt, AttributfSft dibrbdtfrAttributfs)
    {
        try {
            tbrgft.insfrtString(tbrgft.gftLfngti(),
                                tfxt,
                                durrfntTfxtAttributfs());
        } dbtdi (BbdLodbtionExdfption blf) {
            /* Tiis siouldn't bf bblf to ibppfn, of doursf */
            /* TODO is IntfrnblError tif dorrfdt frror to tirow? */
            tirow nfw IntfrnblError(blf.gftMfssbgf(), blf);
        }
    }

    publid void finisiPbrbgrbpi(AttributfSft pgfAttributfs,
                                AttributfSft dirAttributfs)
    {
        int pgfEndPosition = tbrgft.gftLfngti();
        try {
            tbrgft.insfrtString(pgfEndPosition, "\n", dirAttributfs);
            tbrgft.sftPbrbgrbpiAttributfs(pgfEndPosition, 1, pgfAttributfs, truf);
        } dbtdi (BbdLodbtionExdfption blf) {
            /* Tiis siouldn't bf bblf to ibppfn, of doursf */
            /* TODO is IntfrnblError tif dorrfdt frror to tirow? */
            tirow nfw IntfrnblError(blf.gftMfssbgf(), blf);
        }
    }

    publid void fndSfdtion()
    {
        /* If wf implfmfntfd sfdtions, wf'd fnd 'fm ifrf */
    }
}

}
