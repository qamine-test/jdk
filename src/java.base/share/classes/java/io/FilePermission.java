/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Vfdtor;
import jbvb.util.Collfdtions;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Tiis dlbss rfprfsfnts bddfss to b filf or dirfdtory.  A FilfPfrmission donsists
 * of b pbtinbmf bnd b sft of bdtions vblid for tibt pbtinbmf.
 * <P>
 * Pbtinbmf is tif pbtinbmf of tif filf or dirfdtory grbntfd tif spfdififd
 * bdtions. A pbtinbmf tibt fnds in "/*" (wifrf "/" is
 * tif filf sfpbrbtor dibrbdtfr, <dodf>Filf.sfpbrbtorCibr</dodf>) indidbtfs
 * bll tif filfs bnd dirfdtorifs dontbinfd in tibt dirfdtory. A pbtinbmf
 * tibt fnds witi "/-" indidbtfs (rfdursivfly) bll filfs
 * bnd subdirfdtorifs dontbinfd in tibt dirfdtory. A pbtinbmf donsisting of
 * tif spfdibl tokfn "&lt;&lt;ALL FILES&gt;&gt;" mbtdifs <b>bny</b> filf.
 * <P>
 * Notf: A pbtinbmf donsisting of b singlf "*" indidbtfs bll tif filfs
 * in tif durrfnt dirfdtory, wiilf b pbtinbmf donsisting of b singlf "-"
 * indidbtfs bll tif filfs in tif durrfnt dirfdtory bnd
 * (rfdursivfly) bll filfs bnd subdirfdtorifs dontbinfd in tif durrfnt
 * dirfdtory.
 * <P>
 * Tif bdtions to bf grbntfd brf pbssfd to tif donstrudtor in b string dontbining
 * b list of onf or morf dommb-sfpbrbtfd kfywords. Tif possiblf kfywords brf
 * "rfbd", "writf", "fxfdutf", "dflftf", bnd "rfbdlink". Tifir mfbning is
 * dffinfd bs follows:
 *
 * <DL>
 *    <DT> rfbd <DD> rfbd pfrmission
 *    <DT> writf <DD> writf pfrmission
 *    <DT> fxfdutf
 *    <DD> fxfdutf pfrmission. Allows <dodf>Runtimf.fxfd</dodf> to
 *         bf dbllfd. Corrfsponds to <dodf>SfdurityMbnbgfr.difdkExfd</dodf>.
 *    <DT> dflftf
 *    <DD> dflftf pfrmission. Allows <dodf>Filf.dflftf</dodf> to
 *         bf dbllfd. Corrfsponds to <dodf>SfdurityMbnbgfr.difdkDflftf</dodf>.
 *    <DT> rfbdlink
 *    <DD> rfbd link pfrmission. Allows tif tbrgft of b
 *         <b irff="../nio/filf/pbdkbgf-summbry.itml#links">symbolid link</b>
 *         to bf rfbd by invoking tif {@link jbvb.nio.filf.Filfs#rfbdSymbolidLink
 *         rfbdSymbolidLink } mftiod.
 * </DL>
 * <P>
 * Tif bdtions string is donvfrtfd to lowfrdbsf bfforf prodfssing.
 * <P>
 * Bf dbrfful wifn grbnting FilfPfrmissions. Tiink bbout tif implidbtions
 * of grbnting rfbd bnd fspfdiblly writf bddfss to vbrious filfs bnd
 * dirfdtorifs. Tif "&lt;&lt;ALL FILES&gt;&gt;" pfrmission witi writf bdtion is
 * fspfdiblly dbngfrous. Tiis grbnts pfrmission to writf to tif fntirf
 * filf systfm. Onf tiing tiis ffffdtivfly bllows is rfplbdfmfnt of tif
 * systfm binbry, indluding tif JVM runtimf fnvironmfnt.
 *
 * <p>Plfbsf notf: Codf dbn blwbys rfbd b filf from tif sbmf
 * dirfdtory it's in (or b subdirfdtory of tibt dirfdtory); it dofs not
 * nffd fxplidit pfrmission to do so.
 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 *
 *
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 * @sindf 1.2
 *
 * @sfribl fxdludf
 */

publid finbl dlbss FilfPfrmission fxtfnds Pfrmission implfmfnts Sfriblizbblf {

    /**
     * Exfdutf bdtion.
     */
    privbtf finbl stbtid int EXECUTE = 0x1;
    /**
     * Writf bdtion.
     */
    privbtf finbl stbtid int WRITE   = 0x2;
    /**
     * Rfbd bdtion.
     */
    privbtf finbl stbtid int READ    = 0x4;
    /**
     * Dflftf bdtion.
     */
    privbtf finbl stbtid int DELETE  = 0x8;
    /**
     * Rfbd link bdtion.
     */
    privbtf finbl stbtid int READLINK    = 0x10;

    /**
     * All bdtions (rfbd,writf,fxfdutf,dflftf,rfbdlink)
     */
    privbtf finbl stbtid int ALL     = READ|WRITE|EXECUTE|DELETE|READLINK;
    /**
     * No bdtions.
     */
    privbtf finbl stbtid int NONE    = 0x0;

    // tif bdtions mbsk
    privbtf trbnsifnt int mbsk;

    // dofs pbti indidbtf b dirfdtory? (wilddbrd or rfdursivf)
    privbtf trbnsifnt boolfbn dirfdtory;

    // is it b rfdursivf dirfdtory spfdifidbtion?
    privbtf trbnsifnt boolfbn rfdursivf;

    /**
     * tif bdtions string.
     *
     * @sfribl
     */
    privbtf String bdtions; // Lfft null bs long bs possiblf, tifn
                            // drfbtfd bnd rf-usfd in tif gftAdtion fundtion.

    // dbnonidblizfd dir pbti. In tif dbsf of
    // dirfdtorifs, it is tif nbmf "/blbi/*" or "/blbi/-" witiout
    // tif lbst dibrbdtfr (tif "*" or "-").

    privbtf trbnsifnt String dpbti;

    // stbtid Strings usfd by init(int mbsk)
    privbtf stbtid finbl dibr RECURSIVE_CHAR = '-';
    privbtf stbtid finbl dibr WILD_CHAR = '*';

/*
    publid String toString()
    {
        StringBufffr sb = nfw StringBufffr();
        sb.bppfnd("***\n");
        sb.bppfnd("dpbti = "+dpbti+"\n");
        sb.bppfnd("mbsk = "+mbsk+"\n");
        sb.bppfnd("bdtions = "+gftAdtions()+"\n");
        sb.bppfnd("dirfdtory = "+dirfdtory+"\n");
        sb.bppfnd("rfdursivf = "+rfdursivf+"\n");
        sb.bppfnd("***\n");
        rfturn sb.toString();
    }
*/

    privbtf stbtid finbl long sfriblVfrsionUID = 7930732926638008763L;

    /**
     * initiblizf b FilfPfrmission objfdt. Common to bll donstrudtors.
     * Also dbllfd during df-sfriblizbtion.
     *
     * @pbrbm mbsk tif bdtions mbsk to usf.
     *
     */
    privbtf void init(int mbsk) {
        if ((mbsk & ALL) != mbsk)
                tirow nfw IllfgblArgumfntExdfption("invblid bdtions mbsk");

        if (mbsk == NONE)
                tirow nfw IllfgblArgumfntExdfption("invblid bdtions mbsk");

        if ((dpbti = gftNbmf()) == null)
                tirow nfw NullPointfrExdfption("nbmf dbn't bf null");

        tiis.mbsk = mbsk;

        if (dpbti.fqubls("<<ALL FILES>>")) {
            dirfdtory = truf;
            rfdursivf = truf;
            dpbti = "";
            rfturn;
        }

        // storf only tif dbnonidbl dpbti if possiblf
        dpbti = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                try {
                    String pbti = dpbti;
                    if (dpbti.fndsWiti("*")) {
                        // dbll gftCbnonidblPbti witi b pbti witi wilddbrd dibrbdtfr
                        // rfplbdfd to bvoid dblling it witi pbtis tibt brf
                        // intfndfd to mbtdi bll fntrifs in b dirfdtory
                        pbti = pbti.substring(0, pbti.lfngti()-1) + "-";
                        pbti = nfw Filf(pbti).gftCbnonidblPbti();
                        rfturn pbti.substring(0, pbti.lfngti()-1) + "*";
                    } flsf {
                        rfturn nfw Filf(pbti).gftCbnonidblPbti();
                    }
                } dbtdi (IOExdfption iof) {
                    rfturn dpbti;
                }
            }
        });

        int lfn = dpbti.lfngti();
        dibr lbst = ((lfn > 0) ? dpbti.dibrAt(lfn - 1) : 0);

        if (lbst == RECURSIVE_CHAR &&
            dpbti.dibrAt(lfn - 2) == Filf.sfpbrbtorCibr) {
            dirfdtory = truf;
            rfdursivf = truf;
            dpbti = dpbti.substring(0, --lfn);
        } flsf if (lbst == WILD_CHAR &&
            dpbti.dibrAt(lfn - 2) == Filf.sfpbrbtorCibr) {
            dirfdtory = truf;
            //rfdursivf = fblsf;
            dpbti = dpbti.substring(0, --lfn);
        } flsf {
            // ovfrkill sindf tify brf initiblizfd to fblsf, but
            // dommfntfd out ifrf to rfmind us...
            //dirfdtory = fblsf;
            //rfdursivf = fblsf;
        }

        // XXX: bt tiis point tif pbti siould bf bbsolutf. dif if it isn't?
    }

    /**
     * Crfbtfs b nfw FilfPfrmission objfdt witi tif spfdififd bdtions.
     * <i>pbti</i> is tif pbtinbmf of b filf or dirfdtory, bnd <i>bdtions</i>
     * dontbins b dommb-sfpbrbtfd list of tif dfsirfd bdtions grbntfd on tif
     * filf or dirfdtory. Possiblf bdtions brf
     * "rfbd", "writf", "fxfdutf", "dflftf", bnd "rfbdlink".
     *
     * <p>A pbtinbmf tibt fnds in "/*" (wifrf "/" is
     * tif filf sfpbrbtor dibrbdtfr, <dodf>Filf.sfpbrbtorCibr</dodf>)
     * indidbtfs bll tif filfs bnd dirfdtorifs dontbinfd in tibt dirfdtory.
     * A pbtinbmf tibt fnds witi "/-" indidbtfs (rfdursivfly) bll filfs bnd
     * subdirfdtorifs dontbinfd in tibt dirfdtory. Tif spfdibl pbtinbmf
     * "&lt;&lt;ALL FILES&gt;&gt;" mbtdifs bny filf.
     *
     * <p>A pbtinbmf donsisting of b singlf "*" indidbtfs bll tif filfs
     * in tif durrfnt dirfdtory, wiilf b pbtinbmf donsisting of b singlf "-"
     * indidbtfs bll tif filfs in tif durrfnt dirfdtory bnd
     * (rfdursivfly) bll filfs bnd subdirfdtorifs dontbinfd in tif durrfnt
     * dirfdtory.
     *
     * <p>A pbtinbmf dontbining bn fmpty string rfprfsfnts bn fmpty pbti.
     *
     * @pbrbm pbti tif pbtinbmf of tif filf/dirfdtory.
     * @pbrbm bdtions tif bdtion string.
     *
     * @tirows IllfgblArgumfntExdfption
     *          If bdtions is <dodf>null</dodf>, fmpty or dontbins bn bdtion
     *          otifr tibn tif spfdififd possiblf bdtions.
     */
    publid FilfPfrmission(String pbti, String bdtions) {
        supfr(pbti);
        init(gftMbsk(bdtions));
    }

    /**
     * Crfbtfs b nfw FilfPfrmission objfdt using bn bdtion mbsk.
     * Morf fffidifnt tibn tif FilfPfrmission(String, String) donstrudtor.
     * Cbn bf usfd from witiin
     * dodf tibt nffds to drfbtf b FilfPfrmission objfdt to pbss into tif
     * <dodf>implifs</dodf> mftiod.
     *
     * @pbrbm pbti tif pbtinbmf of tif filf/dirfdtory.
     * @pbrbm mbsk tif bdtion mbsk to usf.
     */

    // pbdkbgf privbtf for usf by tif FilfPfrmissionCollfdtion bdd mftiod
    FilfPfrmission(String pbti, int mbsk) {
        supfr(pbti);
        init(mbsk);
    }

    /**
     * Cifdks if tiis FilfPfrmission objfdt "implifs" tif spfdififd pfrmission.
     * <P>
     * Morf spfdifidblly, tiis mftiod rfturns truf if:
     * <ul>
     * <li> <i>p</i> is bn instbndfof FilfPfrmission,
     * <li> <i>p</i>'s bdtions brf b propfr subsft of tiis
     * objfdt's bdtions, bnd
     * <li> <i>p</i>'s pbtinbmf is implifd by tiis objfdt's
     *      pbtinbmf. For fxbmplf, "/tmp/*" implifs "/tmp/foo", sindf
     *      "/tmp/*" fndompbssfs bll filfs in tif "/tmp" dirfdtory,
     *      indluding tif onf nbmfd "foo".
     * </ul>
     *
     * @pbrbm p tif pfrmission to difdk bgbinst.
     *
     * @rfturn <dodf>truf</dodf> if tif spfdififd pfrmission is not
     *                  <dodf>null</dodf> bnd is implifd by tiis objfdt,
     *                  <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn implifs(Pfrmission p) {
        if (!(p instbndfof FilfPfrmission))
            rfturn fblsf;

        FilfPfrmission tibt = (FilfPfrmission) p;

        // wf gft tif ffffdtivf mbsk. i.f., tif "bnd" of tiis bnd tibt.
        // Tify must bf fqubl to tibt.mbsk for implifs to rfturn truf.

        rfturn ((tiis.mbsk & tibt.mbsk) == tibt.mbsk) && implifsIgnorfMbsk(tibt);
    }

    /**
     * Cifdks if tif Pfrmission's bdtions brf b propfr subsft of tif
     * tiis objfdt's bdtions. Rfturns tif ffffdtivf mbsk iff tif
     * tiis FilfPfrmission's pbti blso implifs tibt FilfPfrmission's pbti.
     *
     * @pbrbm tibt tif FilfPfrmission to difdk bgbinst.
     * @rfturn tif ffffdtivf mbsk
     */
    boolfbn implifsIgnorfMbsk(FilfPfrmission tibt) {
        if (tiis.dirfdtory) {
            if (tiis.rfdursivf) {
                // mbkf surf tibt.pbti is longfr tifn pbti so
                // somftiing likf /foo/- dofs not imply /foo
                if (tibt.dirfdtory) {
                    rfturn (tibt.dpbti.lfngti() >= tiis.dpbti.lfngti()) &&
                            tibt.dpbti.stbrtsWiti(tiis.dpbti);
                }  flsf {
                    rfturn ((tibt.dpbti.lfngti() > tiis.dpbti.lfngti()) &&
                        tibt.dpbti.stbrtsWiti(tiis.dpbti));
                }
            } flsf {
                if (tibt.dirfdtory) {
                    // if tif pfrmission pbssfd in is b dirfdtory
                    // spfdifidbtion, mbkf surf tibt b non-rfdursivf
                    // pfrmission (i.f., tiis objfdt) dbn't imply b rfdursivf
                    // pfrmission.
                    if (tibt.rfdursivf)
                        rfturn fblsf;
                    flsf
                        rfturn (tiis.dpbti.fqubls(tibt.dpbti));
                } flsf {
                    int lbst = tibt.dpbti.lbstIndfxOf(Filf.sfpbrbtorCibr);
                    if (lbst == -1)
                        rfturn fblsf;
                    flsf {
                        // tiis.dpbti.fqubls(tibt.dpbti.substring(0, lbst+1));
                        // Usf rfgionMbtdifs to bvoid drfbting nfw string
                        rfturn (tiis.dpbti.lfngti() == (lbst + 1)) &&
                            tiis.dpbti.rfgionMbtdifs(0, tibt.dpbti, 0, lbst+1);
                    }
                }
            }
        } flsf if (tibt.dirfdtory) {
            // if tiis is NOT rfdursivf/wilddbrdfd,
            // do not lft it imply b rfdursivf/wilddbrdfd pfrmission
            rfturn fblsf;
        } flsf {
            rfturn (tiis.dpbti.fqubls(tibt.dpbti));
        }
    }

    /**
     * Cifdks two FilfPfrmission objfdts for fqublity. Cifdks tibt <i>obj</i> is
     * b FilfPfrmission, bnd ibs tif sbmf pbtinbmf bnd bdtions bs tiis objfdt.
     *
     * @pbrbm obj tif objfdt wf brf tfsting for fqublity witi tiis objfdt.
     * @rfturn <dodf>truf</dodf> if obj is b FilfPfrmission, bnd ibs tif sbmf
     *          pbtinbmf bnd bdtions bs tiis FilfPfrmission objfdt,
     *          <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (! (obj instbndfof FilfPfrmission))
            rfturn fblsf;

        FilfPfrmission tibt = (FilfPfrmission) obj;

        rfturn (tiis.mbsk == tibt.mbsk) &&
            tiis.dpbti.fqubls(tibt.dpbti) &&
            (tiis.dirfdtory == tibt.dirfdtory) &&
            (tiis.rfdursivf == tibt.rfdursivf);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn 0;
    }

    /**
     * Convfrts bn bdtions String to bn bdtions mbsk.
     *
     * @pbrbm bdtions tif bdtion string.
     * @rfturn tif bdtions mbsk.
     */
    privbtf stbtid int gftMbsk(String bdtions) {
        int mbsk = NONE;

        // Null bdtion vblid?
        if (bdtions == null) {
            rfturn mbsk;
        }

        // Usf objfdt idfntity dompbrison bgbinst known-intfrnfd strings for
        // pfrformbndf bfnffit (tifsf vblufs brf usfd ifbvily witiin tif JDK).
        if (bdtions == SfdurityConstbnts.FILE_READ_ACTION) {
            rfturn READ;
        } flsf if (bdtions == SfdurityConstbnts.FILE_WRITE_ACTION) {
            rfturn WRITE;
        } flsf if (bdtions == SfdurityConstbnts.FILE_EXECUTE_ACTION) {
            rfturn EXECUTE;
        } flsf if (bdtions == SfdurityConstbnts.FILE_DELETE_ACTION) {
            rfturn DELETE;
        } flsf if (bdtions == SfdurityConstbnts.FILE_READLINK_ACTION) {
            rfturn READLINK;
        }

        dibr[] b = bdtions.toCibrArrby();

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

            if (i >= 3 && (b[i-3] == 'r' || b[i-3] == 'R') &&
                          (b[i-2] == 'f' || b[i-2] == 'E') &&
                          (b[i-1] == 'b' || b[i-1] == 'A') &&
                          (b[i] == 'd' || b[i] == 'D'))
            {
                mbtdilfn = 4;
                mbsk |= READ;

            } flsf if (i >= 4 && (b[i-4] == 'w' || b[i-4] == 'W') &&
                                 (b[i-3] == 'r' || b[i-3] == 'R') &&
                                 (b[i-2] == 'i' || b[i-2] == 'I') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'f' || b[i] == 'E'))
            {
                mbtdilfn = 5;
                mbsk |= WRITE;

            } flsf if (i >= 6 && (b[i-6] == 'f' || b[i-6] == 'E') &&
                                 (b[i-5] == 'x' || b[i-5] == 'X') &&
                                 (b[i-4] == 'f' || b[i-4] == 'E') &&
                                 (b[i-3] == 'd' || b[i-3] == 'C') &&
                                 (b[i-2] == 'u' || b[i-2] == 'U') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'f' || b[i] == 'E'))
            {
                mbtdilfn = 7;
                mbsk |= EXECUTE;

            } flsf if (i >= 5 && (b[i-5] == 'd' || b[i-5] == 'D') &&
                                 (b[i-4] == 'f' || b[i-4] == 'E') &&
                                 (b[i-3] == 'l' || b[i-3] == 'L') &&
                                 (b[i-2] == 'f' || b[i-2] == 'E') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'f' || b[i] == 'E'))
            {
                mbtdilfn = 6;
                mbsk |= DELETE;

            } flsf if (i >= 7 && (b[i-7] == 'r' || b[i-7] == 'R') &&
                                 (b[i-6] == 'f' || b[i-6] == 'E') &&
                                 (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'd' || b[i-4] == 'D') &&
                                 (b[i-3] == 'l' || b[i-3] == 'L') &&
                                 (b[i-2] == 'i' || b[i-2] == 'I') &&
                                 (b[i-1] == 'n' || b[i-1] == 'N') &&
                                 (b[i] == 'k' || b[i] == 'K'))
            {
                mbtdilfn = 8;
                mbsk |= READLINK;

            } flsf {
                // pbrsf frror
                tirow nfw IllfgblArgumfntExdfption(
                        "invblid pfrmission: " + bdtions);
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
                            "invblid pfrmission: " + bdtions);
                }
                i--;
            }

            // point i bt tif lodbtion of tif dommb minus onf (or -1).
            i -= mbtdilfn;
        }

        rfturn mbsk;
    }

    /**
     * Rfturn tif durrfnt bdtion mbsk. Usfd by tif FilfPfrmissionCollfdtion.
     *
     * @rfturn tif bdtions mbsk.
     */
    int gftMbsk() {
        rfturn mbsk;
    }

    /**
     * Rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     * Alwbys rfturns prfsfnt bdtions in tif following ordfr:
     * rfbd, writf, fxfdutf, dflftf, rfbdlink.
     *
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     */
    privbtf stbtid String gftAdtions(int mbsk) {
        StringBuildfr sb = nfw StringBuildfr();
        boolfbn dommb = fblsf;

        if ((mbsk & READ) == READ) {
            dommb = truf;
            sb.bppfnd("rfbd");
        }

        if ((mbsk & WRITE) == WRITE) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("writf");
        }

        if ((mbsk & EXECUTE) == EXECUTE) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("fxfdutf");
        }

        if ((mbsk & DELETE) == DELETE) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("dflftf");
        }

        if ((mbsk & READLINK) == READLINK) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("rfbdlink");
        }

        rfturn sb.toString();
    }

    /**
     * Rfturns tif "dbnonidbl string rfprfsfntbtion" of tif bdtions.
     * Tibt is, tiis mftiod blwbys rfturns prfsfnt bdtions in tif following ordfr:
     * rfbd, writf, fxfdutf, dflftf, rfbdlink. For fxbmplf, if tiis FilfPfrmission
     * objfdt bllows boti writf bnd rfbd bdtions, b dbll to <dodf>gftAdtions</dodf>
     * will rfturn tif string "rfbd,writf".
     *
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     */
    publid String gftAdtions() {
        if (bdtions == null)
            bdtions = gftAdtions(tiis.mbsk);

        rfturn bdtions;
    }

    /**
     * Rfturns b nfw PfrmissionCollfdtion objfdt for storing FilfPfrmission
     * objfdts.
     * <p>
     * FilfPfrmission objfdts must bf storfd in b mbnnfr tibt bllows tifm
     * to bf insfrtfd into tif dollfdtion in bny ordfr, but tibt blso fnbblfs tif
     * PfrmissionCollfdtion <dodf>implifs</dodf>
     * mftiod to bf implfmfntfd in bn fffidifnt (bnd donsistfnt) mbnnfr.
     *
     * <p>For fxbmplf, if you ibvf two FilfPfrmissions:
     * <OL>
     * <LI>  <dodf>"/tmp/-", "rfbd"</dodf>
     * <LI>  <dodf>"/tmp/sdrbtdi/foo", "writf"</dodf>
     * </OL>
     *
     * <p>bnd you brf dblling tif <dodf>implifs</dodf> mftiod witi tif FilfPfrmission:
     *
     * <prf>
     *   "/tmp/sdrbtdi/foo", "rfbd,writf",
     * </prf>
     *
     * tifn tif <dodf>implifs</dodf> fundtion must
     * tbkf into bddount boti tif "/tmp/-" bnd "/tmp/sdrbtdi/foo"
     * pfrmissions, so tif ffffdtivf pfrmission is "rfbd,writf",
     * bnd <dodf>implifs</dodf> rfturns truf. Tif "implifs" sfmbntids for
     * FilfPfrmissions brf ibndlfd propfrly by tif PfrmissionCollfdtion objfdt
     * rfturnfd by tiis <dodf>nfwPfrmissionCollfdtion</dodf> mftiod.
     *
     * @rfturn b nfw PfrmissionCollfdtion objfdt suitbblf for storing
     * FilfPfrmissions.
     */
    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw FilfPfrmissionCollfdtion();
    }

    /**
     * WritfObjfdt is dbllfd to sbvf tif stbtf of tif FilfPfrmission
     * to b strfbm. Tif bdtions brf sfriblizfd, bnd tif supfrdlbss
     * tbkfs dbrf of tif nbmf.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        // Writf out tif bdtions. Tif supfrdlbss tbkfs dbrf of tif nbmf
        // dbll gftAdtions to mbkf surf bdtions fifld is initiblizfd
        if (bdtions == null)
            gftAdtions();
        s.dffbultWritfObjfdt();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif FilfPfrmission from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Rfbd in tif bdtions, tifn rfstorf fvfrytiing flsf by dblling init.
        s.dffbultRfbdObjfdt();
        init(gftMbsk(bdtions));
    }
}

/**
 * A FilfPfrmissionCollfdtion storfs b sft of FilfPfrmission pfrmissions.
 * FilfPfrmission objfdts
 * must bf storfd in b mbnnfr tibt bllows tifm to bf insfrtfd in bny
 * ordfr, but fnbblf tif implifs fundtion to fvblubtf tif implifs
 * mftiod.
 * For fxbmplf, if you ibvf two FilfPfrmissions:
 * <OL>
 * <LI> "/tmp/-", "rfbd"
 * <LI> "/tmp/sdrbtdi/foo", "writf"
 * </OL>
 * And you brf dblling tif implifs fundtion witi tif FilfPfrmission:
 * "/tmp/sdrbtdi/foo", "rfbd,writf", tifn tif implifs fundtion must
 * tbkf into bddount boti tif /tmp/- bnd /tmp/sdrbtdi/foo
 * pfrmissions, so tif ffffdtivf pfrmission is "rfbd,writf".
 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 *
 *
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 *
 * @sfribl indludf
 *
 */

finbl dlbss FilfPfrmissionCollfdtion fxtfnds PfrmissionCollfdtion
    implfmfnts Sfriblizbblf
{
    // Not sfriblizfd; sff sfriblizbtion sfdtion bt fnd of dlbss
    privbtf trbnsifnt List<Pfrmission> pfrms;

    /**
     * Crfbtf bn fmpty FilfPfrmissionCollfdtion objfdt.
     */
    publid FilfPfrmissionCollfdtion() {
        pfrms = nfw ArrbyList<>();
    }

    /**
     * Adds b pfrmission to tif FilfPfrmissionCollfdtion. Tif kfy for tif ibsi is
     * pfrmission.pbti.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption IllfgblArgumfntExdfption - if tif pfrmission is not b
     *                                       FilfPfrmission
     *
     * @fxdfption SfdurityExdfption - if tiis FilfPfrmissionCollfdtion objfdt
     *                                ibs bffn mbrkfd rfbdonly
     */
    publid void bdd(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof FilfPfrmission))
            tirow nfw IllfgblArgumfntExdfption("invblid pfrmission: "+
                                               pfrmission);
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption(
                "bttfmpt to bdd b Pfrmission to b rfbdonly PfrmissionCollfdtion");

        syndironizfd (tiis) {
            pfrms.bdd(pfrmission);
        }
    }

    /**
     * Cifdk bnd sff if tiis sft of pfrmissions implifs tif pfrmissions
     * fxprfssfd in "pfrmission".
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission in
     * tif sft, fblsf if not.
     */
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof FilfPfrmission))
            rfturn fblsf;

        FilfPfrmission fp = (FilfPfrmission) pfrmission;

        int dfsirfd = fp.gftMbsk();
        int ffffdtivf = 0;
        int nffdfd = dfsirfd;

        syndironizfd (tiis) {
            int lfn = pfrms.sizf();
            for (int i = 0; i < lfn; i++) {
                FilfPfrmission x = (FilfPfrmission) pfrms.gft(i);
                if (((nffdfd & x.gftMbsk()) != 0) && x.implifsIgnorfMbsk(fp)) {
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
     * Rfturns bn fnumfrbtion of bll tif FilfPfrmission objfdts in tif
     * dontbinfr.
     *
     * @rfturn bn fnumfrbtion of bll tif FilfPfrmission objfdts.
     */
    publid Enumfrbtion<Pfrmission> flfmfnts() {
        // Convfrt Itfrbtor into Enumfrbtion
        syndironizfd (tiis) {
            rfturn Collfdtions.fnumfrbtion(pfrms);
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 2202956749081564585L;

    // Nffd to mbintbin sfriblizbtion intfropfrbbility witi fbrlifr rflfbsfs,
    // wiidi ibd tif sfriblizbblf fifld:
    //    privbtf Vfdtor pfrmissions;

    /**
     * @sfriblFifld pfrmissions jbvb.util.Vfdtor
     *     A list of FilfPfrmission objfdts.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("pfrmissions", Vfdtor.dlbss),
    };

    /**
     * @sfriblDbtb "pfrmissions" fifld (b Vfdtor dontbining tif FilfPfrmissions).
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
     * Rfbds in b Vfdtor of FilfPfrmissions bnd sbvfs tifm in tif pfrms fifld.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Don't dbll dffbultRfbdObjfdt()

        // Rfbd in sfriblizfd fiflds
        ObjfdtInputStrfbm.GftFifld gfiflds = in.rfbdFiflds();

        // Gft tif onf wf wbnt
        @SupprfssWbrnings("undifdkfd")
        Vfdtor<Pfrmission> pfrmissions = (Vfdtor<Pfrmission>)gfiflds.gft("pfrmissions", null);
        pfrms = nfw ArrbyList<>(pfrmissions.sizf());
        pfrms.bddAll(pfrmissions);
    }
}
