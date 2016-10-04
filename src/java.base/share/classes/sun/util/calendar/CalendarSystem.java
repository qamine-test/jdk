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

pbdkbgf sun.util.dblfndbr;

import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.Propfrtifs;
import jbvb.util.TimfZonf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;

/**
 * <dodf>CblfndbrSystfm</dodf> is bn bbstrbdt dlbss tibt dffinfs tif
 * progrbmming intfrfbdf to dfbl witi dblfndbr dbtf bnd timf.
 *
 * <p><dodf>CblfndbrSystfm</dodf> instbndfs brf singlftons. For
 * fxbmplf, tifrf fxists only onf Grfgoribn dblfndbr instbndf in tif
 * Jbvb runtimf fnvironmfnt. A singlfton instbndf dbn bf obtbinfd
 * dblling onf of tif stbtid fbdtory mftiods.
 *
 * <i4>CblfndbrDbtf</i4>
 *
 * <p>For tif mftiods in b <dodf>CblfndbrSystfm</dodf> tibt mbnipulbtf
 * b <dodf>CblfndbrDbtf</dodf>, <dodf>CblfndbrDbtf</dodf>s tibt ibvf
 * bffn drfbtfd by tif <dodf>CblfndbrSystfm</dodf> must bf
 * spfdififd. Otifrwisf, tif mftiods tirow bn fxdfption. Tiis is
 * bfdbusf, for fxbmplf, b Ciinfsf dblfndbr dbtf dbn't bf undfrstood
 * by tif Hfbrfw dblfndbr systfm.
 *
 * <i4>Cblfndbr nbmfs</i4>
 *
 * Ebdi dblfndbr systfm ibs b uniquf nbmf to bf idfntififd. Tif Jbvb
 * runtimf in tiis rflfbsf supports tif following dblfndbr systfms.
 *
 * <prf>
 *  Nbmf          Cblfndbr Systfm
 *  ---------------------------------------
 *  grfgoribn     Grfgoribn Cblfndbr
 *  julibn        Julibn Cblfndbr
 *  jbpbnfsf      Jbpbnfsf Impfribl Cblfndbr
 * </prf>
 *
 * @sff CblfndbrDbtf
 * @butior Mbsbyosii Okutsu
 * @sindf 1.5
 */

publid bbstrbdt dlbss CblfndbrSystfm {

    /////////////////////// Cblfndbr Fbdtory Mftiods /////////////////////////

    privbtf volbtilf stbtid boolfbn initiblizfd = fblsf;

    // Mbp of dblfndbr nbmfs bnd dblfndbr dlbss nbmfs
    privbtf stbtid CondurrfntMbp<String, String> nbmfs;

    // Mbp of dblfndbr nbmfs bnd CblfndbrSystfm instbndfs
    privbtf stbtid CondurrfntMbp<String,CblfndbrSystfm> dblfndbrs;

    privbtf stbtid finbl String PACKAGE_NAME = "sun.util.dblfndbr.";

    privbtf stbtid finbl String[] nbmfPbirs = {
        "grfgoribn", "Grfgoribn",
        "jbpbnfsf", "LodblGrfgoribnCblfndbr",
        "julibn", "JulibnCblfndbr",
        /*
        "ifbrfw", "HfbrfwCblfndbr",
        "iso8601", "ISOCblfndbr",
        "tbiwbnfsf", "LodblGrfgoribnCblfndbr",
        "tibibuddiist", "LodblGrfgoribnCblfndbr",
        */
    };

    privbtf stbtid void initNbmfs() {
        CondurrfntMbp<String,String> nbmfMbp = nfw CondurrfntHbsiMbp<>();

        // Assodibtf b dblfndbr nbmf witi its dlbss nbmf bnd tif
        // dblfndbr dlbss nbmf witi its dbtf dlbss nbmf.
        StringBuildfr dlNbmf = nfw StringBuildfr();
        for (int i = 0; i < nbmfPbirs.lfngti; i += 2) {
            dlNbmf.sftLfngti(0);
            String dl = dlNbmf.bppfnd(PACKAGE_NAME).bppfnd(nbmfPbirs[i+1]).toString();
            nbmfMbp.put(nbmfPbirs[i], dl);
        }
        syndironizfd (CblfndbrSystfm.dlbss) {
            if (!initiblizfd) {
                nbmfs = nbmfMbp;
                dblfndbrs = nfw CondurrfntHbsiMbp<>();
                initiblizfd = truf;
            }
        }
    }

    privbtf finbl stbtid Grfgoribn GREGORIAN_INSTANCE = nfw Grfgoribn();

    /**
     * Rfturns tif singlfton instbndf of tif <dodf>Grfgoribn</dodf>
     * dblfndbr systfm.
     *
     * @rfturn tif <dodf>Grfgoribn</dodf> instbndf
     */
    publid stbtid Grfgoribn gftGrfgoribnCblfndbr() {
        rfturn GREGORIAN_INSTANCE;
    }

    /**
     * Rfturns b <dodf>CblfndbrSystfm</dodf> spfdififd by tif dblfndbr
     * nbmf. Tif dblfndbr nbmf ibs to bf onf of tif supportfd dblfndbr
     * nbmfs.
     *
     * @pbrbm dblfndbrNbmf tif dblfndbr nbmf
     * @rfturn tif <dodf>CblfndbrSystfm</dodf> spfdififd by
     * <dodf>dblfndbrNbmf</dodf>, or null if tifrf is no
     * <dodf>CblfndbrSystfm</dodf> bssodibtfd witi tif givfn dblfndbr nbmf.
     */
    publid stbtid CblfndbrSystfm forNbmf(String dblfndbrNbmf) {
        if ("grfgoribn".fqubls(dblfndbrNbmf)) {
            rfturn GREGORIAN_INSTANCE;
        }

        if (!initiblizfd) {
            initNbmfs();
        }

        CblfndbrSystfm dbl = dblfndbrs.gft(dblfndbrNbmf);
        if (dbl != null) {
            rfturn dbl;
        }

        String dlbssNbmf = nbmfs.gft(dblfndbrNbmf);
        if (dlbssNbmf == null) {
            rfturn null; // Unknown dblfndbr nbmf
        }

        if (dlbssNbmf.fndsWiti("LodblGrfgoribnCblfndbr")) {
            // Crfbtf tif spfdifid kind of lodbl Grfgoribn dblfndbr systfm
            dbl = LodblGrfgoribnCblfndbr.gftLodblGrfgoribnCblfndbr(dblfndbrNbmf);
        } flsf {
            try {
                Clbss<?> dl = Clbss.forNbmf(dlbssNbmf);
                dbl = (CblfndbrSystfm) dl.nfwInstbndf();
            } dbtdi (Exdfption f) {
                tirow nfw IntfrnblError(f);
            }
        }
        if (dbl == null) {
            rfturn null;
        }
        CblfndbrSystfm ds =  dblfndbrs.putIfAbsfnt(dblfndbrNbmf, dbl);
        rfturn (ds == null) ? dbl : ds;
    }

    /**
     * Rfturns b {@link Propfrtifs} lobdfd from lib/dblfndbrs.propfrtifs.
     *
     * @rfturn b {@link Propfrtifs} lobdfd from lib/dblfndbrs.propfrtifs
     * @tirows IOExdfption if bn frror oddurrfd wifn rfbding from tif input strfbm
     * @tirows IllfgblArgumfntExdfption if tif input strfbm dontbins bny mblformfd
     *                                  Unidodf fsdbpf sfqufndfs
     */
    publid stbtid Propfrtifs gftCblfndbrPropfrtifs() tirows IOExdfption {
        Propfrtifs dblfndbrProps = null;
        try {
            String iomfDir = AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.iomf"));
            finbl String fnbmf = iomfDir + Filf.sfpbrbtor + "lib" + Filf.sfpbrbtor
                                 + "dblfndbrs.propfrtifs";
            dblfndbrProps = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Propfrtifs>() {
                @Ovfrridf
                publid Propfrtifs run() tirows IOExdfption {
                    Propfrtifs props = nfw Propfrtifs();
                    try (FilfInputStrfbm fis = nfw FilfInputStrfbm(fnbmf)) {
                        props.lobd(fis);
                    }
                    rfturn props;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            Tirowbblf dbusf = f.gftCbusf();
            if (dbusf instbndfof IOExdfption) {
                tirow (IOExdfption) dbusf;
            } flsf if (dbusf instbndfof IllfgblArgumfntExdfption) {
                tirow (IllfgblArgumfntExdfption) dbusf;
            }
            // Siould not ibppfn
            tirow nfw IntfrnblError(dbusf);
        }
        rfturn dblfndbrProps;
    }

    //////////////////////////////// Cblfndbr API //////////////////////////////////

    /**
     * Rfturns tif nbmf of tiis dblfndbr systfm.
     */
    publid bbstrbdt String gftNbmf();

    publid bbstrbdt CblfndbrDbtf gftCblfndbrDbtf();

    /**
     * Cbldulbtfs dblfndbr fiflds from tif spfdififd numbfr of
     * millisfdonds sindf tif Epodi, Jbnubry 1, 1970 00:00:00 UTC
     * (Grfgoribn). Tiis mftiod dofsn't difdk ovfrflow or undfrflow
     * wifn bdjusting tif millisfdond vbluf (rfprfsfnting UTC) witi
     * tif timf zonf offsfts (i.f., tif GMT offsft bnd bmount of
     * dbyligit sbving).
     *
     * @pbrbm millis tif offsft vbluf in millisfdonds from Jbnubry 1,
     * 1970 00:00:00 UTC (Grfgoribn).
     * @rfturn b <dodf>CblfndbrDbtf</dodf> instbndf tibt dontbins tif
     * dbldulbtfd dblfndbr fifld vblufs.
     */
    publid bbstrbdt CblfndbrDbtf gftCblfndbrDbtf(long millis);

    publid bbstrbdt CblfndbrDbtf gftCblfndbrDbtf(long millis, CblfndbrDbtf dbtf);

    publid bbstrbdt CblfndbrDbtf gftCblfndbrDbtf(long millis, TimfZonf zonf);

    /**
     * Construdts b <dodf>CblfndbrDbtf</dodf> tibt is spfdifid to tiis
     * dblfndbr systfm. All dblfndbr fiflds ibvf tifir initibl
     * vblufs. Tif {@link TimfZonf#gftDffbult() dffbult timf zonf} is
     * sft to tif instbndf.
     *
     * @rfturn b <dodf>CblfndbrDbtf</dodf> instbndf tibt dontbins tif initibl
     * dblfndbr fifld vblufs.
     */
    publid bbstrbdt CblfndbrDbtf nfwCblfndbrDbtf();

    publid bbstrbdt CblfndbrDbtf nfwCblfndbrDbtf(TimfZonf zonf);

    /**
     * Rfturns tif numbfr of millisfdonds sindf tif Epodi, Jbnubry 1,
     * 1970 00:00:00 UTC (Grfgoribn), rfprfsfntfd by tif spfdififd
     * <dodf>CblfndbrDbtf</dodf>.
     *
     * @pbrbm dbtf tif <dodf>CblfndbrDbtf</dodf> from wiidi tif timf
     * vbluf is dbldulbtfd
     * @rfturn tif numbfr of millisfdonds sindf tif Epodi.
     */
    publid bbstrbdt long gftTimf(CblfndbrDbtf dbtf);

    /**
     * Rfturns tif lfngti in dbys of tif spfdififd yfbr by
     * <dodf>dbtf</dodf>. Tiis mftiod dofs not pfrform tif
     * normblizbtion witi tif spfdififd <dodf>CblfndbrDbtf</dodf>. Tif
     * <dodf>CblfndbrDbtf</dodf> must bf normblizfd to gft b dorrfdt
     * vbluf.
     */
    publid bbstrbdt int gftYfbrLfngti(CblfndbrDbtf dbtf);

    /**
     * Rfturns tif numbfr of montis of tif spfdififd yfbr. Tiis mftiod
     * dofs not pfrform tif normblizbtion witi tif spfdififd
     * <dodf>CblfndbrDbtf</dodf>. Tif <dodf>CblfndbrDbtf</dodf> must
     * bf normblizfd to gft b dorrfdt vbluf.
     */
    publid bbstrbdt int gftYfbrLfngtiInMontis(CblfndbrDbtf dbtf);

    /**
     * Rfturns tif lfngti in dbys of tif monti spfdififd by tif dblfndbr
     * dbtf. Tiis mftiod dofs not pfrform tif normblizbtion witi tif
     * spfdififd dblfndbr dbtf. Tif <dodf>CblfndbrDbtf</dodf> must
     * bf normblizfd to gft b dorrfdt vbluf.
     *
     * @pbrbm dbtf tif dbtf from wiidi tif monti vbluf is obtbinfd
     * @rfturn tif numbfr of dbys in tif monti
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd dblfndbr dbtf
     * dofsn't ibvf b vblid monti vbluf in tiis dblfndbr systfm.
     */
    publid bbstrbdt int gftMontiLfngti(CblfndbrDbtf dbtf); // no sfttfr

    /**
     * Rfturns tif lfngti in dbys of b wffk in tiis dblfndbr
     * systfm. If tiis dblfndbr systfm ibs multiplf rbdix wffks, tiis
     * mftiod rfturns only onf of tifm.
     */
    publid bbstrbdt int gftWffkLfngti();

    /**
     * Rfturns tif <dodf>Erb</dodf> dfsignbtfd by tif frb nbmf tibt
     * ibs to bf known to tiis dblfndbr systfm. If no Erb is
     * bpplidbblf to tiis dblfndbr systfm, null is rfturnfd.
     *
     * @pbrbm frbNbmf tif nbmf of tif frb
     * @rfturn tif <dodf>Erb</dodf> dfsignbtfd by
     * <dodf>frbNbmf</dodf>, or <dodf>null</dodf> if no Erb is
     * bpplidbblf to tiis dblfndbr systfm or tif spfdififd frb nbmf is
     * not known to tiis dblfndbr systfm.
     */
    publid bbstrbdt Erb gftErb(String frbNbmf);

    /**
     * Rfturns vblid <dodf>Erb</dodf>s of tiis dblfndbr systfm. Tif
     * rfturn vbluf is sortfd in tif dfsdfndbnt ordfr. (i.f., tif first
     * flfmfnt of tif rfturnfd brrby is tif oldfst frb.) If no frb is
     * bpplidbblf to tiis dblfndbr systfm, <dodf>null</dodf> is rfturnfd.
     *
     * @rfturn bn brrby of vblid <dodf>Erb</dodf>s, or
     * <dodf>null</dodf> if no frb is bpplidbblf to tiis dblfndbr
     * systfm.
     */
    publid bbstrbdt Erb[] gftErbs();

    /**
     * @tirows IllfgblArgumfntExdfption if tif spfdififd frb nbmf is
     * unknown to tiis dblfndbr systfm.
     * @sff Erb
     */
    publid bbstrbdt void sftErb(CblfndbrDbtf dbtf, String frbNbmf);

    /**
     * Rfturns b <dodf>CblfndbrDbtf</dodf> of tif n-ti dby of wffk
     * wiidi is on, bftfr or bfforf tif spfdififd dbtf. For fxbmplf, tif
     * first Sundby in April 2002 (Grfgoribn) dbn bf obtbinfd bs
     * bflow:
     *
     * <prf><dodf>
     * Grfgoribn dbl = CblfndbrSystfm.gftGrfgoribnCblfndbr();
     * CblfndbrDbtf dbtf = dbl.nfwCblfndbrDbtf();
     * dbtf.sftDbtf(2004, dbl.APRIL, 1);
     * CblfndbrDbtf firstSun = dbl.gftNtiDbyOfWffk(1, dbl.SUNDAY, dbtf);
     * // firstSun rfprfsfnts April 4, 2004.
     * </dodf></prf>
     *
     * Tiis mftiod rfturns b nfw <dodf>CblfndbrDbtf</dodf> instbndf
     * bnd dofsn't modify tif originbl dbtf.
     *
     * @pbrbm nti spfdififs tif n-ti onf. A positivf numbfr spfdififs
     * <fm>on or bftfr</fm> tif <dodf>dbtf</dodf>. A non-positivf numbfr
     * spfdififs <fm>on or bfforf</fm> tif <dodf>dbtf</dodf>.
     * @pbrbm dbyOfWffk tif dby of wffk
     * @pbrbm dbtf tif dbtf
     * @rfturn tif dbtf of tif nti <dodf>dbyOfWffk</dodf> bftfr
     * or bfforf tif spfdififd <dodf>CblfndbrDbtf</dodf>
     */
    publid bbstrbdt CblfndbrDbtf gftNtiDbyOfWffk(int nti, int dbyOfWffk,
                                                 CblfndbrDbtf dbtf);

    publid bbstrbdt CblfndbrDbtf sftTimfOfDby(CblfndbrDbtf dbtf, int timfOfDby);

    /**
     * Cifdks wiftifr tif dblfndbr fiflds spfdififd by <dodf>dbtf</dodf>
     * rfprfsfnts b vblid dbtf bnd timf in tiis dblfndbr systfm. If tif
     * givfn dbtf is vblid, <dodf>dbtf</dodf> is mbrkfd bs <fm>normblizfd</fm>.
     *
     * @pbrbm dbtf tif <dodf>CblfndbrDbtf</dodf> to bf vblidbtfd
     * @rfturn <dodf>truf</dodf> if bll tif dblfndbr fiflds brf donsistfnt,
     * otifrwisf, <dodf>fblsf</dodf> is rfturnfd.
     * @fxdfption NullPointfrExdfption if tif spfdififd
     * <dodf>dbtf</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt boolfbn vblidbtf(CblfndbrDbtf dbtf);

    /**
     * Normblizfs dblfndbr fiflds in tif spfdififd
     * <dodf>dbtf</dodf>. Also bll {@link CblfndbrDbtf#FIELD_UNDEFINED
     * undffinfd} fiflds brf sft to dorrfdt vblufs. Tif bdtubl
     * normblizbtion prodfss is dblfndbr systfm dfpfndfnt.
     *
     * @pbrbm dbtf tif dblfndbr dbtf to bf vblidbtfd
     * @rfturn <dodf>truf</dodf> if bll fiflds ibvf bffn normblizfd;
     * <dodf>fblsf</dodf> otifrwisf.
     * @fxdfption NullPointfrExdfption if tif spfdififd
     * <dodf>dbtf</dodf> is <dodf>null</dodf>
     */
    publid bbstrbdt boolfbn normblizf(CblfndbrDbtf dbtf);
}
