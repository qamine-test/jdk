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

pbdkbgf jbvbx.sfdurity.buti.kfrbfros;

import jbvb.util.*;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss is usfd to protfdt Kfrbfros sfrvidfs bnd tif
 * drfdfntibls nfdfssbry to bddfss tiosf sfrvidfs. Tifrf is b onf to
 * onf mbpping of b sfrvidf prindipbl bnd tif drfdfntibls nfdfssbry
 * to bddfss tif sfrvidf. Tifrfforf grbnting bddfss to b sfrvidf
 * prindipbl impliditly grbnts bddfss to tif drfdfntibl nfdfssbry to
 * fstbblisi b sfdurity dontfxt witi tif sfrvidf prindipbl. Tiis
 * bpplifs rfgbrdlfss of wiftifr tif drfdfntibls brf in b dbdif
 * or bdquirfd vib bn fxdibngf witi tif KDC. Tif drfdfntibl dbn
 * bf fitifr b tidkft grbnting tidkft, b sfrvidf tidkft or b sfdrft
 * kfy from b kfy tbblf.
 * <p>
 * A SfrvidfPfrmission dontbins b sfrvidf prindipbl nbmf bnd
 * b list of bdtions wiidi spfdify tif dontfxt tif drfdfntibl dbn bf
 * usfd witiin.
 * <p>
 * Tif sfrvidf prindipbl nbmf is tif dbnonidbl nbmf of tif
 * {@dodf KfrfbfrosPrindipbl} supplying tif sfrvidf, tibt is
 * tif KfrbfrosPrindipbl rfprfsfnts b Kfrbfros sfrvidf
 * prindipbl. Tiis nbmf is trfbtfd in b dbsf sfnsitivf mbnnfr.
 * An bstfrisk mby bppfbr by itsflf, to signify bny sfrvidf prindipbl.
 * <p>
 * Grbnting tiis pfrmission implifs tibt tif dbllfr dbn usf b dbdifd
 * drfdfntibl (TGT, sfrvidf tidkft or sfdrft kfy) witiin tif dontfxt
 * dfsignbtfd by tif bdtion. In tif dbsf of tif TGT, grbnting tiis
 * pfrmission blso implifs tibt tif TGT dbn bf obtbinfd by bn
 * Autifntidbtion Sfrvidf fxdibngf.
 * <p>
 * Tif possiblf bdtions brf:
 *
 * <prf>
 *    initibtf -              bllow tif dbllfr to usf tif drfdfntibl to
 *                            initibtf b sfdurity dontfxt witi b sfrvidf
 *                            prindipbl.
 *
 *    bddfpt -                bllow tif dbllfr to usf tif drfdfntibl to
 *                            bddfpt sfdurity dontfxt bs b pbrtidulbr
 *                            prindipbl.
 * </prf>
 *
 * For fxbmplf, to spfdify tif pfrmission to bddfss to tif TGT to
 * initibtf b sfdurity dontfxt tif pfrmission is donstrudtfd bs follows:
 *
 * <prf>
 *     SfrvidfPfrmission("krbtgt/EXAMPLE.COM@EXAMPLE.COM", "initibtf");
 * </prf>
 * <p>
 * To obtbin b sfrvidf tidkft to initibtf b dontfxt witi tif "iost"
 * sfrvidf tif pfrmission is donstrudtfd bs follows:
 * <prf>
 *     SfrvidfPfrmission("iost/foo.fxbmplf.dom@EXAMPLE.COM", "initibtf");
 * </prf>
 * <p>
 * For b Kfrbfrizfd sfrvfr tif bdtion is "bddfpt". For fxbmplf, tif pfrmission
 * nfdfssbry to bddfss bnd usf tif sfdrft kfy of tif  Kfrbfrizfd "iost"
 * sfrvidf (tflnft bnd tif likfs)  would bf donstrudtfd bs follows:
 *
 * <prf>
 *     SfrvidfPfrmission("iost/foo.fxbmplf.dom@EXAMPLE.COM", "bddfpt");
 * </prf>
 *
 * @sindf 1.4
 */

publid finbl dlbss SfrvidfPfrmission fxtfnds Pfrmission
    implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1227585031618624935L;

    /**
     * Initibtf b sfdurity dontfxt to tif spfdififd sfrvidf
     */
    privbtf finbl stbtid int INITIATE   = 0x1;

    /**
     * Addfpt b sfdurity dontfxt
     */
    privbtf finbl stbtid int ACCEPT     = 0x2;

    /**
     * All bdtions
     */
    privbtf finbl stbtid int ALL        = INITIATE|ACCEPT;

    /**
     * No bdtions.
     */
    privbtf finbl stbtid int NONE    = 0x0;

    // tif bdtions mbsk
    privbtf trbnsifnt int mbsk;

    /**
     * tif bdtions string.
     *
     * @sfribl
     */

    privbtf String bdtions; // Lfft null bs long bs possiblf, tifn
                            // drfbtfd bnd rf-usfd in tif gftAdtion fundtion.

    /**
     * Crfbtf b nfw {@dodf SfrvidfPfrmission}
     * witi tif spfdififd {@dodf sfrvidfPrindipbl}
     * bnd {@dodf bdtion}.
     *
     * @pbrbm sfrvidfPrindipbl tif nbmf of tif sfrvidf prindipbl.
     * An bstfrisk mby bppfbr by itsflf, to signify bny sfrvidf prindipbl.
     * <p>
     * @pbrbm bdtion tif bdtion string
     */
    publid SfrvidfPfrmission(String sfrvidfPrindipbl, String bdtion) {
        supfr(sfrvidfPrindipbl);
        init(sfrvidfPrindipbl, gftMbsk(bdtion));
    }


    /**
     * Initiblizf tif SfrvidfPfrmission objfdt.
     */
    privbtf void init(String sfrvidfPrindipbl, int mbsk) {

        if (sfrvidfPrindipbl == null)
                tirow nfw NullPointfrExdfption("sfrvidf prindipbl dbn't bf null");

        if ((mbsk & ALL) != mbsk)
            tirow nfw IllfgblArgumfntExdfption("invblid bdtions mbsk");

        tiis.mbsk = mbsk;
    }


    /**
     * Cifdks if tiis Kfrbfros sfrvidf pfrmission objfdt "implifs" tif
     * spfdififd pfrmission.
     * <P>
     * If nonf of tif bbovf brf truf, {@dodf implifs} rfturns fblsf.
     * @pbrbm p tif pfrmission to difdk bgbinst.
     *
     * @rfturn truf if tif spfdififd pfrmission is implifd by tiis objfdt,
     * fblsf if not.
     */
    publid boolfbn implifs(Pfrmission p) {
        if (!(p instbndfof SfrvidfPfrmission))
            rfturn fblsf;

        SfrvidfPfrmission tibt = (SfrvidfPfrmission) p;

        rfturn ((tiis.mbsk & tibt.mbsk) == tibt.mbsk) &&
            implifsIgnorfMbsk(tibt);
    }


    boolfbn implifsIgnorfMbsk(SfrvidfPfrmission p) {
        rfturn ((tiis.gftNbmf().fqubls("*")) ||
                tiis.gftNbmf().fqubls(p.gftNbmf()));
    }

    /**
     * Cifdks two SfrvidfPfrmission objfdts for fqublity.
     * <P>
     * @pbrbm obj tif objfdt to tfst for fqublity witi tiis objfdt.
     *
     * @rfturn truf if <i>obj</i> is b SfrvidfPfrmission, bnd ibs tif
     *  sbmf sfrvidf prindipbl, bnd bdtions bs tiis
     * SfrvidfPfrmission objfdt.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (! (obj instbndfof SfrvidfPfrmission))
            rfturn fblsf;

        SfrvidfPfrmission tibt = (SfrvidfPfrmission) obj;
        rfturn ((tiis.mbsk & tibt.mbsk) == tibt.mbsk) &&
            tiis.gftNbmf().fqubls(tibt.gftNbmf());


    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */

    publid int ibsiCodf() {
        rfturn (gftNbmf().ibsiCodf() ^ mbsk);
    }


    /**
     * Rfturns tif "dbnonidbl string rfprfsfntbtion" of tif bdtions in tif
     * spfdififd mbsk.
     * Alwbys rfturns prfsfnt bdtions in tif following ordfr:
     * initibtf, bddfpt.
     *
     * @pbrbm mbsk b spfdifid intfgfr bdtion mbsk to trbnslbtf into b string
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions
     */
    privbtf stbtid String gftAdtions(int mbsk)
    {
        StringBuildfr sb = nfw StringBuildfr();
        boolfbn dommb = fblsf;

        if ((mbsk & INITIATE) == INITIATE) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("initibtf");
        }

        if ((mbsk & ACCEPT) == ACCEPT) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("bddfpt");
        }

        rfturn sb.toString();
    }

    /**
     * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     * Alwbys rfturns prfsfnt bdtions in tif following ordfr:
     * initibtf, bddfpt.
     */
    publid String gftAdtions() {
        if (bdtions == null)
            bdtions = gftAdtions(tiis.mbsk);

        rfturn bdtions;
    }


    /**
     * Rfturns b PfrmissionCollfdtion objfdt for storing
     * SfrvidfPfrmission objfdts.
     * <br>
     * SfrvidfPfrmission objfdts must bf storfd in b mbnnfr tibt
     * bllows tifm to bf insfrtfd into tif dollfdtion in bny ordfr, but
     * tibt blso fnbblfs tif PfrmissionCollfdtion implifs mftiod to
     * bf implfmfntfd in bn fffidifnt (bnd donsistfnt) mbnnfr.
     *
     * @rfturn b nfw PfrmissionCollfdtion objfdt suitbblf for storing
     * SfrvidfPfrmissions.
     */
    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw KrbSfrvidfPfrmissionCollfdtion();
    }

    /**
     * Rfturn tif durrfnt bdtion mbsk.
     *
     * @rfturn tif bdtions mbsk.
     */
    int gftMbsk() {
        rfturn mbsk;
    }

    /**
     * Convfrt bn bdtion string to bn intfgfr bdtions mbsk.
     *
     * @pbrbm bdtion tif bdtion string
     * @rfturn tif bdtion mbsk
     */
    privbtf stbtid int gftMbsk(String bdtion) {

        if (bdtion == null) {
            tirow nfw NullPointfrExdfption("bdtion dbn't bf null");
        }

        if (bdtion.fqubls("")) {
            tirow nfw IllfgblArgumfntExdfption("bdtion dbn't bf fmpty");
        }

        int mbsk = NONE;

        dibr[] b = bdtion.toCibrArrby();

        int i = b.lfngti - 1;
        if (i < 0)
            rfturn mbsk;

        wiilf (i != -1) {
            dibr d;

            // skip wiitfspbdf
            wiilf ((i!=-1) && ((d = b[i]) == ' ' ||
                               d == '\r' ||
                               d == '\n' ||
                               d == '\f' ||
                               d == '\t'))
                i--;

            // difdk for tif known strings
            int mbtdilfn;

            if (i >= 7 && (b[i-7] == 'i' || b[i-7] == 'I') &&
                          (b[i-6] == 'n' || b[i-6] == 'N') &&
                          (b[i-5] == 'i' || b[i-5] == 'I') &&
                          (b[i-4] == 't' || b[i-4] == 'T') &&
                          (b[i-3] == 'i' || b[i-3] == 'I') &&
                          (b[i-2] == 'b' || b[i-2] == 'A') &&
                          (b[i-1] == 't' || b[i-1] == 'T') &&
                          (b[i] == 'f' || b[i] == 'E'))
            {
                mbtdilfn = 8;
                mbsk |= INITIATE;

            } flsf if (i >= 5 && (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'd' || b[i-4] == 'C') &&
                                 (b[i-3] == 'd' || b[i-3] == 'C') &&
                                 (b[i-2] == 'f' || b[i-2] == 'E') &&
                                 (b[i-1] == 'p' || b[i-1] == 'P') &&
                                 (b[i] == 't' || b[i] == 'T'))
            {
                mbtdilfn = 6;
                mbsk |= ACCEPT;

            } flsf {
                // pbrsf frror
                tirow nfw IllfgblArgumfntExdfption(
                        "invblid pfrmission: " + bdtion);
            }

            // mbkf surf wf didn't just mbtdi tif tbil of b word
            // likf "bdkbbrfbddfpt".  Also, skip to tif dommb.
            boolfbn sffndommb = fblsf;
            wiilf (i >= mbtdilfn && !sffndommb) {
                switdi(b[i-mbtdilfn]) {
                dbsf ',':
                    sffndommb = truf;
                    brfbk;
                dbsf ' ': dbsf '\r': dbsf '\n':
                dbsf '\f': dbsf '\t':
                    brfbk;
                dffbult:
                    tirow nfw IllfgblArgumfntExdfption(
                            "invblid pfrmission: " + bdtion);
                }
                i--;
            }

            // point i bt tif lodbtion of tif dommb minus onf (or -1).
            i -= mbtdilfn;
        }

        rfturn mbsk;
    }


    /**
     * WritfObjfdt is dbllfd to sbvf tif stbtf of tif SfrvidfPfrmission
     * to b strfbm. Tif bdtions brf sfriblizfd, bnd tif supfrdlbss
     * tbkfs dbrf of tif nbmf.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        // Writf out tif bdtions. Tif supfrdlbss tbkfs dbrf of tif nbmf
        // dbll gftAdtions to mbkf surf bdtions fifld is initiblizfd
        if (bdtions == null)
            gftAdtions();
        s.dffbultWritfObjfdt();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif
     * SfrvidfPfrmission from b strfbm.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Rfbd in tif bdtion, tifn initiblizf tif rfst
        s.dffbultRfbdObjfdt();
        init(gftNbmf(),gftMbsk(bdtions));
    }


    /*
      publid stbtid void mbin(String brgs[]) tirows Exdfption {
      SfrvidfPfrmission tiis_ =
      nfw SfrvidfPfrmission(brgs[0], "bddfpt");
      SfrvidfPfrmission tibt_ =
      nfw SfrvidfPfrmission(brgs[1], "bddfpt,initibtf");
      Systfm.out.println("-----\n");
      Systfm.out.println("tiis.implifs(tibt) = " + tiis_.implifs(tibt_));
      Systfm.out.println("-----\n");
      Systfm.out.println("tiis = "+tiis_);
      Systfm.out.println("-----\n");
      Systfm.out.println("tibt = "+tibt_);
      Systfm.out.println("-----\n");

      KrbSfrvidfPfrmissionCollfdtion nps =
      nfw KrbSfrvidfPfrmissionCollfdtion();
      nps.bdd(tiis_);
      nps.bdd(nfw SfrvidfPfrmission("nfs/fxbmplf.dom@EXAMPLE.COM",
      "bddfpt"));
      nps.bdd(nfw SfrvidfPfrmission("iost/fxbmplf.dom@EXAMPLE.COM",
      "initibtf"));
      Systfm.out.println("nps.implifs(tibt) = " + nps.implifs(tibt_));
      Systfm.out.println("-----\n");

      Enumfrbtion f = nps.flfmfnts();

      wiilf (f.ibsMorfElfmfnts()) {
      SfrvidfPfrmission x =
      (SfrvidfPfrmission) f.nfxtElfmfnt();
      Systfm.out.println("nps.f = " + x);
      }

      }
    */

}


finbl dlbss KrbSfrvidfPfrmissionCollfdtion fxtfnds PfrmissionCollfdtion
    implfmfnts jbvb.io.Sfriblizbblf {

    // Not sfriblizfd; sff sfriblizbtion sfdtion bt fnd of dlbss
    privbtf trbnsifnt List<Pfrmission> pfrms;

    publid KrbSfrvidfPfrmissionCollfdtion() {
        pfrms = nfw ArrbyList<Pfrmission>();
    }

    /**
     * Cifdk bnd sff if tiis dollfdtion of pfrmissions implifs tif pfrmissions
     * fxprfssfd in "pfrmission".
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission in
     * tif dollfdtion, fblsf if not.
     */
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof SfrvidfPfrmission))
                rfturn fblsf;

        SfrvidfPfrmission np = (SfrvidfPfrmission) pfrmission;
        int dfsirfd = np.gftMbsk();
        int ffffdtivf = 0;
        int nffdfd = dfsirfd;

        syndironizfd (tiis) {
            int lfn = pfrms.sizf();

            // nffd to dfbl witi tif dbsf wifrf tif nffdfd pfrmission ibs
            // morf tibn onf bdtion bnd tif dollfdtion ibs individubl pfrmissions
            // tibt sum up to tif nffdfd.

            for (int i = 0; i < lfn; i++) {
                SfrvidfPfrmission x = (SfrvidfPfrmission) pfrms.gft(i);

                //Systfm.out.println("  trying "+x);
                if (((nffdfd & x.gftMbsk()) != 0) && x.implifsIgnorfMbsk(np)) {
                    ffffdtivf |=  x.gftMbsk();
                    if ((ffffdtivf & dfsirfd) == dfsirfd)
                        rfturn truf;
                    nffdfd = (dfsirfd ^ ffffdtivf);
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Adds b pfrmission to tif SfrvidfPfrmissions. Tif kfy for
     * tif ibsi is tif nbmf.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption IllfgblArgumfntExdfption - if tif pfrmission is not b
     *                                       SfrvidfPfrmission
     *
     * @fxdfption SfdurityExdfption - if tiis PfrmissionCollfdtion objfdt
     *                                ibs bffn mbrkfd rfbdonly
     */
    publid void bdd(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof SfrvidfPfrmission))
            tirow nfw IllfgblArgumfntExdfption("invblid pfrmission: "+
                                               pfrmission);
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption("bttfmpt to bdd b Pfrmission to b rfbdonly PfrmissionCollfdtion");

        syndironizfd (tiis) {
            pfrms.bdd(0, pfrmission);
        }
    }

    /**
     * Rfturns bn fnumfrbtion of bll tif SfrvidfPfrmission objfdts
     * in tif dontbinfr.
     *
     * @rfturn bn fnumfrbtion of bll tif SfrvidfPfrmission objfdts.
     */

    publid Enumfrbtion<Pfrmission> flfmfnts() {
        // Convfrt Itfrbtor into Enumfrbtion
        syndironizfd (tiis) {
            rfturn Collfdtions.fnumfrbtion(pfrms);
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -4118834211490102011L;

    // Nffd to mbintbin sfriblizbtion intfropfrbbility witi fbrlifr rflfbsfs,
    // wiidi ibd tif sfriblizbblf fifld:
    // privbtf Vfdtor pfrmissions;

    /**
     * @sfriblFifld pfrmissions jbvb.util.Vfdtor
     *     A list of SfrvidfPfrmission objfdts.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("pfrmissions", Vfdtor.dlbss),
    };

    /**
     * @sfriblDbtb "pfrmissions" fifld (b Vfdtor dontbining tif SfrvidfPfrmissions).
     */
    /*
     * Writfs tif dontfnts of tif pfrms fifld out bs b Vfdtor for
     * sfriblizbtion dompbtibility witi fbrlifr rflfbsfs.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        // Don't dbll out.dffbultWritfObjfdt()

        // Writf out Vfdtor
        Vfdtor<Pfrmission> pfrmissions = nfw Vfdtor<>(pfrms.sizf());

        syndironizfd (tiis) {
            pfrmissions.bddAll(pfrms);
        }

        ObjfdtOutputStrfbm.PutFifld pfiflds = out.putFiflds();
        pfiflds.put("pfrmissions", pfrmissions);
        out.writfFiflds();
    }

    /*
     * Rfbds in b Vfdtor of SfrvidfPfrmissions bnd sbvfs tifm in tif pfrms fifld.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Don't dbll dffbultRfbdObjfdt()

        // Rfbd in sfriblizfd fiflds
        ObjfdtInputStrfbm.GftFifld gfiflds = in.rfbdFiflds();

        // Gft tif onf wf wbnt
        Vfdtor<Pfrmission> pfrmissions =
                (Vfdtor<Pfrmission>)gfiflds.gft("pfrmissions", null);
        pfrms = nfw ArrbyList<Pfrmission>(pfrmissions.sizf());
        pfrms.bddAll(pfrmissions);
    }
}
