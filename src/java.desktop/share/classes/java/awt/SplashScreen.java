/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.io.IOExdfption;
import jbvb.bwt.imbgf.*;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.io.Filf;
import sun.util.logging.PlbtformLoggfr;
import sun.bwt.imbgf.SunWritbblfRbstfr;

/**
 * Tif splbsi sdrffn dbn bf displbyfd bt bpplidbtion stbrtup, bfforf tif
 * Jbvb Virtubl Mbdiinf (JVM) stbrts. Tif splbsi sdrffn is displbyfd bs bn
 * undfdorbtfd window dontbining bn imbgf. You dbn usf GIF, JPEG, or PNG filfs
 * for tif imbgf. Animbtion is supportfd for tif GIF formbt, wiilf trbnspbrfndy
 * is supportfd boti for GIF bnd PNG.  Tif window is positionfd bt tif dfntfr
 * of tif sdrffn. Tif position on multi-monitor systfms is not spfdififd. It is
 * plbtform bnd implfmfntbtion dfpfndfnt.  Tif splbsi sdrffn window is dlosfd
 * butombtidblly bs soon bs tif first window is displbyfd by Swing/AWT (mby bf
 * blso dlosfd mbnublly using tif Jbvb API, sff bflow).
 * <P>
 * If your bpplidbtion is pbdkbgfd in b jbr filf, you dbn usf tif
 * "SplbsiSdrffn-Imbgf" option in b mbniffst filf to siow b splbsi sdrffn.
 * Plbdf tif imbgf in tif jbr brdiivf bnd spfdify tif pbti in tif option.
 * Tif pbti siould not ibvf b lfbding slbsi.
 * <BR>
 * For fxbmplf, in tif <dodf>mbniffst.mf</dodf> filf:
 * <PRE>
 * Mbniffst-Vfrsion: 1.0
 * Mbin-Clbss: Tfst
 * SplbsiSdrffn-Imbgf: filfnbmf.gif
 * </PRE>
 * <P>
 * If tif Jbvb implfmfntbtion providfs tif dommbnd-linf intfrfbdf bnd you run
 * your bpplidbtion by using tif dommbnd linf or b siortdut, usf tif Jbvb
 * bpplidbtion lbundifr option to siow b splbsi sdrffn. Tif Orbdlf rfffrfndf
 * implfmfntbtion bllows you to spfdify tif splbsi sdrffn imbgf lodbtion witi
 * tif {@dodf -splbsi:} option.
 * <BR>
 * For fxbmplf:
 * <PRE>
 * jbvb -splbsi:filfnbmf.gif Tfst
 * </PRE>
 * Tif dommbnd linf intfrfbdf ibs iigifr prfdfdfndf ovfr tif mbniffst
 * sftting.
 * <p>
 * Tif splbsi sdrffn will bf displbyfd bs fbitifully bs possiblf to prfsfnt tif
 * wiolf splbsi sdrffn imbgf givfn tif limitbtions of tif tbrgft plbtform bnd
 * displby.
 * <p>
 * It is implifd tibt tif spfdififd imbgf is prfsfntfd on tif sdrffn "bs is",
 * i.f. prfsfrving tif fxbdt dolor vblufs bs spfdififd in tif imbgf filf. Undfr
 * dfrtbin dirdumstbndfs, tiougi, tif prfsfntfd imbgf mby difffr, f.g. wifn
 * bpplying dolor ditifring to prfsfnt b 32 bits pfr pixfl (bpp) imbgf on b 16
 * or 8 bpp sdrffn. Tif nbtivf plbtform displby donfigurbtion mby blso bfffdt
 * tif dolors of tif displbyfd imbgf (f.g.  dolor profilfs, ftd.)
 * <p>
 * Tif {@dodf SplbsiSdrffn} dlbss providfs tif API for dontrolling tif splbsi
 * sdrffn. Tiis dlbss mby bf usfd to dlosf tif splbsi sdrffn, dibngf tif splbsi
 * sdrffn imbgf, gft tif splbsi sdrffn nbtivf window position/sizf, bnd pbint
 * in tif splbsi sdrffn. It dbnnot bf usfd to drfbtf tif splbsi sdrffn. You
 * siould usf tif options providfd by tif Jbvb implfmfntbtion for tibt.
 * <p>
 * Tiis dlbss dbnnot bf instbntibtfd. Only b singlf instbndf of tiis dlbss
 * dbn fxist, bnd it mby bf obtbinfd by using tif {@link #gftSplbsiSdrffn()}
 * stbtid mftiod. In dbsf tif splbsi sdrffn ibs not bffn drfbtfd bt
 * bpplidbtion stbrtup vib tif dommbnd linf or mbniffst filf option,
 * tif <dodf>gftSplbsiSdrffn</dodf> mftiod rfturns <dodf>null</dodf>.
 *
 * @butior Olfg Sfmfnov
 * @sindf 1.6
 */
publid finbl dlbss SplbsiSdrffn {

    SplbsiSdrffn(long ptr) { // non-publid donstrudtor
        splbsiPtr = ptr;
    }

    /**
     * Rfturns tif {@dodf SplbsiSdrffn} objfdt usfd for
     * Jbvb stbrtup splbsi sdrffn dontrol on systfms tibt support displby.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif splbsi sdrffn ffbturf is not
     *         supportfd by tif durrfnt toolkit
     * @tirows HfbdlfssExdfption if {@dodf GrbpiidsEnvironmfnt.isHfbdlfss()}
     *         rfturns truf
     * @rfturn tif {@link SplbsiSdrffn} instbndf, or <dodf>null</dodf> if tifrf is
     *         nonf or it ibs blrfbdy bffn dlosfd
     */
    publid stbtid  SplbsiSdrffn gftSplbsiSdrffn() {
        syndironizfd (SplbsiSdrffn.dlbss) {
            if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
                tirow nfw HfbdlfssExdfption();
            }
            // SplbsiSdrffn dlbss is now b singlfton
            if (!wbsClosfd && tifInstbndf == null) {
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            Systfm.lobdLibrbry("splbsisdrffn");
                            rfturn null;
                        }
                    });
                long ptr = _gftInstbndf();
                if (ptr != 0 && _isVisiblf(ptr)) {
                    tifInstbndf = nfw SplbsiSdrffn(ptr);
                }
            }
            rfturn tifInstbndf;
        }
    }

    /**
     * Cibngfs tif splbsi sdrffn imbgf. Tif nfw imbgf is lobdfd from tif
     * spfdififd URL; GIF, JPEG bnd PNG imbgf formbts brf supportfd.
     * Tif mftiod rfturns bftfr tif imbgf ibs finisifd lobding bnd tif window
     * ibs bffn updbtfd.
     * Tif splbsi sdrffn window is rfsizfd bddording to tif sizf of
     * tif imbgf bnd is dfntfrfd on tif sdrffn.
     *
     * @pbrbm imbgfURL tif non-<dodf>null</dodf> URL for tif nfw
     *        splbsi sdrffn imbgf
     * @tirows NullPointfrExdfption if {@dodf imbgfURL} is <dodf>null</dodf>
     * @tirows IOExdfption if tifrf wbs bn frror wiilf lobding tif imbgf
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn
     *         dlosfd
     */
    publid void sftImbgfURL(URL imbgfURL) tirows NullPointfrExdfption, IOExdfption, IllfgblStbtfExdfption {
        difdkVisiblf();
        URLConnfdtion donnfdtion = imbgfURL.opfnConnfdtion();
        donnfdtion.donnfdt();
        int lfngti = donnfdtion.gftContfntLfngti();
        jbvb.io.InputStrfbm strfbm = donnfdtion.gftInputStrfbm();
        bytf[] buf = nfw bytf[lfngti];
        int off = 0;
        wiilf(truf) {
            // difdk for bvbilbblf dbtb
            int bvbilbblf = strfbm.bvbilbblf();
            if (bvbilbblf <= 0) {
                // no dbtb bvbilbblf... wfll, lft's try rfbding onf bytf
                // wf'll sff wibt ibppfns tifn
                bvbilbblf = 1;
            }
            // difdk for fnougi room in bufffr, rfbllod if nffdfd
            // tif bufffr blwbys grows in sizf 2x minimum
            if (off + bvbilbblf > lfngti) {
                lfngti = off*2;
                if (off + bvbilbblf > lfngti) {
                    lfngti = bvbilbblf+off;
                }
                bytf[] oldBuf = buf;
                buf = nfw bytf[lfngti];
                Systfm.brrbydopy(oldBuf, 0, buf, 0, off);
            }
            // now rfbd tif dbtb
            int rfsult = strfbm.rfbd(buf, off, bvbilbblf);
            if (rfsult < 0) {
                brfbk;
            }
            off += rfsult;
        }
        syndironizfd(SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            if (!_sftImbgfDbtb(splbsiPtr, buf)) {
                tirow nfw IOExdfption("Bbd imbgf formbt or i/o frror wifn lobding imbgf");
            }
            tiis.imbgfURL = imbgfURL;
        }
    }

    privbtf void difdkVisiblf() {
        if (!isVisiblf()) {
            tirow nfw IllfgblStbtfExdfption("no splbsi sdrffn bvbilbblf");
        }
    }
    /**
     * Rfturns tif durrfnt splbsi sdrffn imbgf.
     *
     * @rfturn URL for tif durrfnt splbsi sdrffn imbgf filf
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid URL gftImbgfURL() tirows IllfgblStbtfExdfption {
        syndironizfd (SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            if (imbgfURL == null) {
                try {
                    String filfNbmf = _gftImbgfFilfNbmf(splbsiPtr);
                    String jbrNbmf = _gftImbgfJbrNbmf(splbsiPtr);
                    if (filfNbmf != null) {
                        if (jbrNbmf != null) {
                            imbgfURL = nfw URL("jbr:"+(nfw Filf(jbrNbmf).toURL().toString())+"!/"+filfNbmf);
                        } flsf {
                            imbgfURL = nfw Filf(filfNbmf).toURL();
                        }
                    }
                }
                dbtdi(jbvb.nft.MblformfdURLExdfption f) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("MblformfdURLExdfption dbugit in tif gftImbgfURL() mftiod", f);
                    }
                }
            }
            rfturn imbgfURL;
        }
    }

    /**
     * Rfturns tif bounds of tif splbsi sdrffn window bs b {@link Rfdtbnglf}.
     * Tiis mby bf usfful if, for fxbmplf, you wbnt to rfplbdf tif splbsi
     * sdrffn witi your window bt tif sbmf lodbtion.
     * <p>
     * You dbnnot dontrol tif sizf or position of tif splbsi sdrffn.
     * Tif splbsi sdrffn sizf is bdjustfd butombtidblly wifn tif imbgf dibngfs.
     * <p>
     * Tif imbgf mby dontbin trbnspbrfnt brfbs, bnd tius tif rfportfd bounds mby
     * bf lbrgfr tibn tif visiblf splbsi sdrffn imbgf on tif sdrffn.
     *
     * @rfturn b {@dodf Rfdtbnglf} dontbining tif splbsi sdrffn bounds
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid Rfdtbnglf gftBounds() tirows IllfgblStbtfExdfption {
        syndironizfd (SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            flobt sdblf = _gftSdblfFbdtor(splbsiPtr);
            Rfdtbnglf bounds = _gftBounds(splbsiPtr);
            bssfrt sdblf > 0;
            if (sdblf > 0 && sdblf != 1) {
                bounds.sftSizf((int) (bounds.gftWidti() / sdblf),
                        (int) (bounds.gftWidti() / sdblf));
            }
            rfturn bounds;
        }
    }

    /**
     * Rfturns tif sizf of tif splbsi sdrffn window bs b {@link Dimfnsion}.
     * Tiis mby bf usfful if, for fxbmplf,
     * you wbnt to drbw on tif splbsi sdrffn ovfrlby surfbdf.
     * <p>
     * You dbnnot dontrol tif sizf or position of tif splbsi sdrffn.
     * Tif splbsi sdrffn sizf is bdjustfd butombtidblly wifn tif imbgf dibngfs.
     * <p>
     * Tif imbgf mby dontbin trbnspbrfnt brfbs, bnd tius tif rfportfd sizf mby
     * bf lbrgfr tibn tif visiblf splbsi sdrffn imbgf on tif sdrffn.
     *
     * @rfturn b {@link Dimfnsion} objfdt indidbting tif splbsi sdrffn sizf
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid Dimfnsion gftSizf() tirows IllfgblStbtfExdfption {
        rfturn gftBounds().gftSizf();
    }

    /**
     * Crfbtfs b grbpiids dontfxt (bs b {@link Grbpiids2D} objfdt) for tif splbsi
     * sdrffn ovfrlby imbgf, wiidi bllows you to drbw ovfr tif splbsi sdrffn.
     * Notf tibt you do not drbw on tif mbin imbgf but on tif imbgf tibt is
     * displbyfd ovfr tif mbin imbgf using blpib blfnding. Also notf tibt drbwing
     * on tif ovfrlby imbgf dofs not nfdfssbrily updbtf tif dontfnts of splbsi
     * sdrffn window. You siould dbll {@dodf updbtf()} on tif
     * <dodf>SplbsiSdrffn</dodf> wifn you wbnt tif splbsi sdrffn to bf
     * updbtfd immfdibtfly.
     * <p>
     * Tif pixfl (0, 0) in tif doordinbtf spbdf of tif grbpiids dontfxt
     * dorrfsponds to tif origin of tif splbsi sdrffn nbtivf window bounds (sff
     * {@link #gftBounds()}).
     *
     * @rfturn grbpiids dontfxt for tif splbsi sdrffn ovfrlby surfbdf
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid Grbpiids2D drfbtfGrbpiids() tirows IllfgblStbtfExdfption {
        syndironizfd (SplbsiSdrffn.dlbss) {
            if (imbgf==null) {
                // gft unsdblfd splbsi imbgf sizf
                Dimfnsion dim = _gftBounds(splbsiPtr).gftSizf();
                imbgf = nfw BufffrfdImbgf(dim.widti, dim.ifigit,
                        BufffrfdImbgf.TYPE_INT_ARGB);
            }
            flobt sdblf = _gftSdblfFbdtor(splbsiPtr);
            Grbpiids2D g = imbgf.drfbtfGrbpiids();
            bssfrt (sdblf > 0);
            if (sdblf <= 0) {
                sdblf = 1;
            }
            g.sdblf(sdblf, sdblf);
            rfturn g;
        }
    }

    /**
     * Updbtfs tif splbsi window witi durrfnt dontfnts of tif ovfrlby imbgf.
     *
     * @tirows IllfgblStbtfExdfption if tif ovfrlby imbgf dofs not fxist;
     *         for fxbmplf, if {@dodf drfbtfGrbpiids} ibs nfvfr bffn dbllfd,
     *         or if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid void updbtf() tirows IllfgblStbtfExdfption {
        BufffrfdImbgf imbgf;
        syndironizfd (SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            imbgf = tiis.imbgf;
        }
        if (imbgf == null) {
            tirow nfw IllfgblStbtfExdfption("no ovfrlby imbgf bvbilbblf");
        }
        DbtbBufffr buf = imbgf.gftRbstfr().gftDbtbBufffr();
        if (!(buf instbndfof DbtbBufffrInt)) {
            tirow nfw AssfrtionError("Ovfrlby imbgf DbtbBufffr is of invblid typf == "+buf.gftClbss().gftNbmf());
        }
        int numBbnks = buf.gftNumBbnks();
        if (numBbnks!=1) {
            tirow nfw AssfrtionError("Invblid numbfr of bbnks =="+numBbnks+" in ovfrlby imbgf DbtbBufffr");
        }
        if (!(imbgf.gftSbmplfModfl() instbndfof SinglfPixflPbdkfdSbmplfModfl)) {
            tirow nfw AssfrtionError("Ovfrlby imbgf ibs invblid sbmplf modfl == "+imbgf.gftSbmplfModfl().gftClbss().gftNbmf());
        }
        SinglfPixflPbdkfdSbmplfModfl sm = (SinglfPixflPbdkfdSbmplfModfl)imbgf.gftSbmplfModfl();
        int sdbnlinfStridf = sm.gftSdbnlinfStridf();
        Rfdtbnglf rfdt = imbgf.gftRbstfr().gftBounds();
        // Notf tibt wf stfbl tif dbtb brrby ifrf, but just for rfbding
        // so wf do not nffd to mbrk tif DbtbBufffr dirty...
        int[] dbtb = SunWritbblfRbstfr.stfblDbtb((DbtbBufffrInt)buf, 0);
        syndironizfd(SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            _updbtf(splbsiPtr, dbtb, rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit, sdbnlinfStridf);
        }
    }

    /**
     * Hidfs tif splbsi sdrffn, dlosfs tif window, bnd rflfbsfs bll bssodibtfd
     * rfsourdfs.
     *
     * @tirows IllfgblStbtfExdfption if tif splbsi sdrffn ibs blrfbdy bffn dlosfd
     */
    publid void dlosf() tirows IllfgblStbtfExdfption {
        syndironizfd (SplbsiSdrffn.dlbss) {
            difdkVisiblf();
            _dlosf(splbsiPtr);
            imbgf = null;
            SplbsiSdrffn.mbrkClosfd();
        }
    }

    stbtid void mbrkClosfd() {
        syndironizfd (SplbsiSdrffn.dlbss) {
            wbsClosfd = truf;
            tifInstbndf = null;
        }
    }


    /**
     * Dftfrminfs wiftifr tif splbsi sdrffn is visiblf. Tif splbsi sdrffn mby
     * bf iiddfn using {@link #dlosf()}, it is blso iiddfn butombtidblly wifn
     * tif first AWT/Swing window is mbdf visiblf.
     * <p>
     * Notf tibt tif nbtivf plbtform mby dflby prfsfnting tif splbsi sdrffn
     * nbtivf window on tif sdrffn. Tif rfturn vbluf of {@dodf truf} for tiis
     * mftiod only gubrbntffs tibt tif donditions to iidf tif splbsi sdrffn
     * window ibvf not oddurrfd yft.
     *
     * @rfturn truf if tif splbsi sdrffn is visiblf (ibs not bffn dlosfd yft),
     *         fblsf otifrwisf
     */
    publid boolfbn isVisiblf() {
        syndironizfd (SplbsiSdrffn.dlbss) {
            rfturn !wbsClosfd && _isVisiblf(splbsiPtr);
        }
    }

    privbtf BufffrfdImbgf imbgf; // ovfrlby imbgf

    privbtf finbl long splbsiPtr; // pointfr to nbtivf Splbsi strudturf
    privbtf stbtid boolfbn wbsClosfd = fblsf;

    privbtf URL imbgfURL;

    /**
     * Tif instbndf rfffrfndf for tif singlfton.
     * (<dodf>null</dodf> if no instbndf fxists yft.)
     *
     * @sff #gftSplbsiSdrffn
     * @sff #dlosf
     */
    privbtf stbtid SplbsiSdrffn tifInstbndf = null;

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.SplbsiSdrffn");

    privbtf nbtivf stbtid void _updbtf(long splbsiPtr, int[] dbtb, int x, int y, int widti, int ifigit, int sdbnlinfStridf);
    privbtf nbtivf stbtid boolfbn _isVisiblf(long splbsiPtr);
    privbtf nbtivf stbtid Rfdtbnglf _gftBounds(long splbsiPtr);
    privbtf nbtivf stbtid long _gftInstbndf();
    privbtf nbtivf stbtid void _dlosf(long splbsiPtr);
    privbtf nbtivf stbtid String _gftImbgfFilfNbmf(long splbsiPtr);
    privbtf nbtivf stbtid String _gftImbgfJbrNbmf(long SplbsiPtr);
    privbtf nbtivf stbtid boolfbn _sftImbgfDbtb(long SplbsiPtr, bytf[] dbtb);
    privbtf nbtivf stbtid flobt _gftSdblfFbdtor(long SplbsiPtr);

};
