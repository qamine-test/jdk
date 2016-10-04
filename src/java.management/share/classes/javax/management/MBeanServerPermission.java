/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.sfdurity.BbsidPfrmission;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.Sft;
import jbvb.util.StringTokfnizfr;

/** A Pfrmission to pfrform bdtions rflbtfd to MBfbnSfrvfrs.
    Tif <fm>nbmf</fm> of tif pfrmission spfdififs tif opfrbtion rfqufstfd
    or grbntfd by tif pfrmission.  For b grbntfd pfrmission, it dbn bf
    <dodf>*</dodf> to bllow bll of tif MBfbnSfrvfr opfrbtions spfdififd bflow.
    Otifrwisf, for b grbntfd or rfqufstfd pfrmission, it must bf onf of tif
    following:
    <dl>
    <dt>drfbtfMBfbnSfrvfr</dt>
    <dd>Crfbtf b nfw MBfbnSfrvfr objfdt using tif mftiod
    {@link MBfbnSfrvfrFbdtory#drfbtfMBfbnSfrvfr()} or
    {@link MBfbnSfrvfrFbdtory#drfbtfMBfbnSfrvfr(jbvb.lbng.String)}.
    <dt>findMBfbnSfrvfr</dt>
    <dd>Find bn MBfbnSfrvfr witi b givfn nbmf, or bll MBfbnSfrvfrs in tiis
    JVM, using tif mftiod {@link MBfbnSfrvfrFbdtory#findMBfbnSfrvfr}.
    <dt>nfwMBfbnSfrvfr</dt>
    <dd>Crfbtf b nfw MBfbnSfrvfr objfdt witiout kffping b rfffrfndf to it,
    using tif mftiod {@link MBfbnSfrvfrFbdtory#nfwMBfbnSfrvfr()} or
    {@link MBfbnSfrvfrFbdtory#nfwMBfbnSfrvfr(jbvb.lbng.String)}.
    <dt>rflfbsfMBfbnSfrvfr</dt>
    <dd>Rfmovf tif MBfbnSfrvfrFbdtory's rfffrfndf to bn MBfbnSfrvfr,
    using tif mftiod {@link MBfbnSfrvfrFbdtory#rflfbsfMBfbnSfrvfr}.
    </dl>
    Tif <fm>nbmf</fm> of tif pfrmission dbn blso dfnotf b list of onf or morf
    dommb-sfpbrbtfd opfrbtions.  Spbdfs brf bllowfd bt tif bfginning bnd
    fnd of tif <fm>nbmf</fm> bnd bfforf bnd bftfr dommbs.
    <p>
    <dodf>MBfbnSfrvfrPfrmission("drfbtfMBfbnSfrvfr")</dodf> implifs
    <dodf>MBfbnSfrvfrPfrmission("nfwMBfbnSfrvfr")</dodf>.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnSfrvfrPfrmission fxtfnds BbsidPfrmission {
    privbtf stbtid finbl long sfriblVfrsionUID = -5661980843569388590L;

    privbtf finbl stbtid int
        CREATE = 0,
        FIND = 1,
        NEW = 2,
        RELEASE = 3,
        N_NAMES = 4;

    privbtf finbl stbtid String[] nbmfs = {
        "drfbtfMBfbnSfrvfr",
        "findMBfbnSfrvfr",
        "nfwMBfbnSfrvfr",
        "rflfbsfMBfbnSfrvfr",
    };

    privbtf finbl stbtid int
        CREATE_MASK = 1<<CREATE,
        FIND_MASK = 1<<FIND,
        NEW_MASK = 1<<NEW,
        RELEASE_MASK = 1<<RELEASE,
        ALL_MASK = CREATE_MASK|FIND_MASK|NEW_MASK|RELEASE_MASK;

    /*
     * Mbp from pfrmission mbsks to dbnonidbl nbmfs.  Tiis brrby is
     * fillfd in on dfmbnd.
     *
     * Tiis isn't vfry sdblbblf.  If wf ibvf morf tibn fivf or six
     * pfrmissions, wf siould donsidfr doing tiis difffrfntly,
     * f.g. witi b Mbp.
     */
    privbtf finbl stbtid String[] dbnonidblNbmfs = nfw String[1 << N_NAMES];

    /*
     * Tif tbrgft nbmfs mbsk.  Tiis is not privbtf to bvoid ibving to
     * gfnfrbtf bddfssor mftiods for bddfssfs from tif dollfdtion dlbss.
     *
     * Tiis mbsk indludfs implifd bits.  So if it ibs CREATE_MASK tifn
     * it nfdfssbrily ibs NEW_MASK too.
     */
    trbnsifnt int mbsk;

    /** <p>Crfbtf b nfw MBfbnSfrvfrPfrmission witi tif givfn nbmf.</p>
        <p>Tiis donstrudtor is fquivblfnt to
        <dodf>MBfbnSfrvfrPfrmission(nbmf,null)</dodf>.</p>
        @pbrbm nbmf tif nbmf of tif grbntfd pfrmission.  It must
        rfspfdt tif donstrbints spflt out in tif dfsdription of tif
        {@link MBfbnSfrvfrPfrmission} dlbss.
        @fxdfption NullPointfrExdfption if tif nbmf is null.
        @fxdfption IllfgblArgumfntExdfption if tif nbmf is not
        <dodf>*</dodf> or onf of tif bllowfd nbmfs or b dommb-sfpbrbtfd
        list of tif bllowfd nbmfs.
    */
    publid MBfbnSfrvfrPfrmission(String nbmf) {
        tiis(nbmf, null);
    }

    /** <p>Crfbtf b nfw MBfbnSfrvfrPfrmission witi tif givfn nbmf.</p>
        @pbrbm nbmf tif nbmf of tif grbntfd pfrmission.  It must
        rfspfdt tif donstrbints spflt out in tif dfsdription of tif
        {@link MBfbnSfrvfrPfrmission} dlbss.
        @pbrbm bdtions tif bssodibtfd bdtions.  Tiis pbrbmftfr is not
        durrfntly usfd bnd must bf null or tif fmpty string.
        @fxdfption NullPointfrExdfption if tif nbmf is null.
        @fxdfption IllfgblArgumfntExdfption if tif nbmf is not
        <dodf>*</dodf> or onf of tif bllowfd nbmfs or b dommb-sfpbrbtfd
        list of tif bllowfd nbmfs, or if <dodf>bdtions</dodf> is b non-null
        non-fmpty string.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty or
     * if brgumfnts brf invblid.
     */
    publid MBfbnSfrvfrPfrmission(String nbmf, String bdtions) {
        supfr(gftCbnonidblNbmf(pbrsfMbsk(nbmf)), bdtions);

        /* It's bnnoying to ibvf to pbrsf tif nbmf twidf, but sindf
           Pfrmission.gftNbmf() is finbl bnd sindf wf dbn't bddfss "tiis"
           until bftfr tif dbll to tif supfrdlbss donstrudtor, tifrf
           isn't bny vfry dlfbn wby to do tiis.  MBfbnSfrvfrPfrmission
           objfdts brfn't donstrudtfd vfry oftfn, ludkily.  */
        mbsk = pbrsfMbsk(nbmf);

        /* Cifdk tibt bdtions is b null fmpty string */
        if (bdtions != null && bdtions.lfngti() > 0)
            tirow nfw IllfgblArgumfntExdfption("MBfbnSfrvfrPfrmission " +
                                               "bdtions must bf null: " +
                                               bdtions);
    }

    MBfbnSfrvfrPfrmission(int mbsk) {
        supfr(gftCbnonidblNbmf(mbsk));
        tiis.mbsk = implifdMbsk(mbsk);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
        in.dffbultRfbdObjfdt();
        mbsk = pbrsfMbsk(gftNbmf());
    }

    stbtid int simplifyMbsk(int mbsk) {
        if ((mbsk & CREATE_MASK) != 0)
            mbsk &= ~NEW_MASK;
        rfturn mbsk;
    }

    stbtid int implifdMbsk(int mbsk) {
        if ((mbsk & CREATE_MASK) != 0)
            mbsk |= NEW_MASK;
        rfturn mbsk;
    }

    stbtid String gftCbnonidblNbmf(int mbsk) {
        if (mbsk == ALL_MASK)
            rfturn "*";

        mbsk = simplifyMbsk(mbsk);

        syndironizfd (dbnonidblNbmfs) {
            if (dbnonidblNbmfs[mbsk] == null)
                dbnonidblNbmfs[mbsk] = mbkfCbnonidblNbmf(mbsk);
        }

        rfturn dbnonidblNbmfs[mbsk];
    }

    privbtf stbtid String mbkfCbnonidblNbmf(int mbsk) {
        finbl StringBuildfr buf = nfw StringBuildfr();
        for (int i = 0; i < N_NAMES; i++) {
            if ((mbsk & (1<<i)) != 0) {
                if (buf.lfngti() > 0)
                    buf.bppfnd(',');
                buf.bppfnd(nbmfs[i]);
            }
        }
        rfturn buf.toString().intfrn();
        /* intfrn() bvoids duplidbtion wifn tif mbsk ibs only
           onf bit, so is fquivblfnt to tif string donstbnts
           wf ibvf for tif nbmfs[] brrby.  */
    }

    /* Convfrt tif string into b bitmbsk, indluding bits tibt
       brf implifd by tif pfrmissions in tif string.  */
    privbtf stbtid int pbrsfMbsk(String nbmf) {
        /* Cifdk tibt tbrgft nbmf is b non-null non-fmpty string */
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("MBfbnSfrvfrPfrmission: " +
                                           "tbrgft nbmf dbn't bf null");
        }

        nbmf = nbmf.trim();
        if (nbmf.fqubls("*"))
            rfturn ALL_MASK;

        /* If tif nbmf is fmpty, nbmfIndfx will bbrf. */
        if (nbmf.indfxOf(',') < 0)
            rfturn implifdMbsk(1 << nbmfIndfx(nbmf.trim()));

        int mbsk = 0;

        StringTokfnizfr tok = nfw StringTokfnizfr(nbmf, ",");
        wiilf (tok.ibsMorfTokfns()) {
            String bdtion = tok.nfxtTokfn();
            int i = nbmfIndfx(bdtion.trim());
            mbsk |= (1 << i);
        }

        rfturn implifdMbsk(mbsk);
    }

    privbtf stbtid int nbmfIndfx(String nbmf)
            tirows IllfgblArgumfntExdfption {
        for (int i = 0; i < N_NAMES; i++) {
            if (nbmfs[i].fqubls(nbmf))
                rfturn i;
        }
        finbl String msg =
            "Invblid MBfbnSfrvfrPfrmission nbmf: \"" + nbmf + "\"";
        tirow nfw IllfgblArgumfntExdfption(msg);
    }

    publid int ibsiCodf() {
        rfturn mbsk;
    }

    /**
     * <p>Cifdks if tiis MBfbnSfrvfrPfrmission objfdt "implifs" tif spfdififd
     * pfrmission.</p>
     *
     * <p>Morf spfdifidblly, tiis mftiod rfturns truf if:</p>
     *
     * <ul>
     * <li> <i>p</i> is bn instbndf of MBfbnSfrvfrPfrmission,</li>
     * <li> <i>p</i>'s tbrgft nbmfs brf b subsft of tiis objfdt's tbrgft
     * nbmfs</li>
     * </ul>
     *
     * <p>Tif <dodf>drfbtfMBfbnSfrvfr</dodf> pfrmission implifs tif
     * <dodf>nfwMBfbnSfrvfr</dodf> pfrmission.</p>
     *
     * @pbrbm p tif pfrmission to difdk bgbinst.
     * @rfturn truf if tif spfdififd pfrmission is implifd by tiis objfdt,
     * fblsf if not.
     */
    publid boolfbn implifs(Pfrmission p) {
        if (!(p instbndfof MBfbnSfrvfrPfrmission))
            rfturn fblsf;

        MBfbnSfrvfrPfrmission tibt = (MBfbnSfrvfrPfrmission) p;

        rfturn ((tiis.mbsk & tibt.mbsk) == tibt.mbsk);
    }

    /**
     * Cifdks two MBfbnSfrvfrPfrmission objfdts for fqublity. Cifdks tibt
     * <i>obj</i> is bn MBfbnSfrvfrPfrmission, bnd rfprfsfnts tif sbmf
     * list of bllowbblf bdtions bs tiis objfdt.
     *
     * @pbrbm obj tif objfdt wf brf tfsting for fqublity witi tiis objfdt.
     * @rfturn truf if tif objfdts brf fqubl.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (! (obj instbndfof MBfbnSfrvfrPfrmission))
            rfturn fblsf;

        MBfbnSfrvfrPfrmission tibt = (MBfbnSfrvfrPfrmission) obj;

        rfturn (tiis.mbsk == tibt.mbsk);
    }

    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw MBfbnSfrvfrPfrmissionCollfdtion();
    }
}

/**
 * Clbss rfturnfd by {@link MBfbnSfrvfrPfrmission#nfwPfrmissionCollfdtion()}.
 *
 * @sfribl indludf
 */

/*
 * Sindf fvfry dollfdtion of MBSP dbn bf rfprfsfntfd by b singlf MBSP,
 * tibt is wibt our PfrmissionCollfdtion dofs.  Wf nffd to dffinf b
 * PfrmissionCollfdtion bfdbusf tif onf inifritfd from BbsidPfrmission
 * dofsn't know tibt drfbtfMBfbnSfrvfr implifs nfwMBfbnSfrvfr.
 *
 * Tiougi tif sfribl form is dffinfd, tif TCK dofs not difdk it.  Wf do
 * not rfquirf indfpfndfnt implfmfntbtions to duplidbtf it.  Evfn tiougi
 * PfrmissionCollfdtion is Sfriblizbblf, instbndfs of tiis dlbss will
 * ibrdly fvfr bf sfriblizfd, bnd difffrfnt implfmfntbtions do not
 * typidblly fxdibngf sfriblizfd pfrmission dollfdtions.
 *
 * If wf did rfquirf tibt b pbrtidulbr form bf rfspfdtfd ifrf, wf would
 * logidblly blso ibvf to rfquirf it for
 * MBfbnPfrmission.nfwPfrmissionCollfdtion, wiidi would prfdludf bn
 * implfmfntbtion from dffining b PfrmissionCollfdtion tifrf witi bn
 * optimizfd "implifs" mftiod.
 */
dlbss MBfbnSfrvfrPfrmissionCollfdtion fxtfnds PfrmissionCollfdtion {
    /** @sfribl Null if no pfrmissions in dollfdtion, otifrwisf b
        singlf pfrmission tibt is tif union of bll pfrmissions tibt
        ibvf bffn bddfd.  */
    privbtf MBfbnSfrvfrPfrmission dollfdtionPfrmission;

    privbtf stbtid finbl long sfriblVfrsionUID = -5661980843569388590L;

    publid syndironizfd void bdd(Pfrmission pfrmission) {
        if (!(pfrmission instbndfof MBfbnSfrvfrPfrmission)) {
            finbl String msg =
                "Pfrmission not bn MBfbnSfrvfrPfrmission: " + pfrmission;
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption("Rfbd-only pfrmission dollfdtion");
        MBfbnSfrvfrPfrmission mbsp = (MBfbnSfrvfrPfrmission) pfrmission;
        if (dollfdtionPfrmission == null)
            dollfdtionPfrmission = mbsp;
        flsf if (!dollfdtionPfrmission.implifs(pfrmission)) {
            int nfwmbsk = dollfdtionPfrmission.mbsk | mbsp.mbsk;
            dollfdtionPfrmission = nfw MBfbnSfrvfrPfrmission(nfwmbsk);
        }
    }

    publid syndironizfd boolfbn implifs(Pfrmission pfrmission) {
        rfturn (dollfdtionPfrmission != null &&
                dollfdtionPfrmission.implifs(pfrmission));
    }

    publid syndironizfd Enumfrbtion<Pfrmission> flfmfnts() {
        Sft<Pfrmission> sft;
        if (dollfdtionPfrmission == null)
            sft = Collfdtions.fmptySft();
        flsf
            sft = Collfdtions.singlfton((Pfrmission) dollfdtionPfrmission);
        rfturn Collfdtions.fnumfrbtion(sft);
    }
}
