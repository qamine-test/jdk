/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.util.HbsiSft;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import sun.tfxt.Normblizfr;


/**
 * Tiis is b utility dlbss for <dodf>String.toLowfrCbsf()</dodf> bnd
 * <dodf>String.toUppfrCbsf()</dodf>, tibt ibndlfs spfdibl dbsing witi
 * donditions.  In otifr words, it ibndlfs tif mbppings witi donditions
 * tibt brf dffinfd in
 * <b irff="ittp://www.unidodf.org/Publid/UNIDATA/SpfdiblCbsing.txt">Spfdibl
 * Cbsing Propfrtifs</b> filf.
 * <p>
 * Notf tibt tif undonditionbl dbsf mbppings (indluding 1:M mbppings)
 * brf ibndlfd in <dodf>Cibrbdtfr.toLowfr/UppfrCbsf()</dodf>.
 */
finbl dlbss ConditionblSpfdiblCbsing {

    // dontfxt donditions.
    finbl stbtid int FINAL_CASED =              1;
    finbl stbtid int AFTER_SOFT_DOTTED =        2;
    finbl stbtid int MORE_ABOVE =               3;
    finbl stbtid int AFTER_I =                  4;
    finbl stbtid int NOT_BEFORE_DOT =           5;

    // dombining dlbss dffinitions
    finbl stbtid int COMBINING_CLASS_ABOVE = 230;

    // Spfdibl dbsf mbpping fntrifs
    stbtid Entry[] fntry = {
        //# ================================================================================
        //# Conditionbl mbppings
        //# ================================================================================
        nfw Entry(0x03A3, nfw dibr[]{0x03C2}, nfw dibr[]{0x03A3}, null, FINAL_CASED), // # GREEK CAPITAL LETTER SIGMA
        nfw Entry(0x0130, nfw dibr[]{0x0069, 0x0307}, nfw dibr[]{0x0130}, null, 0), // # LATIN CAPITAL LETTER I WITH DOT ABOVE

        //# ================================================================================
        //# Lodblf-sfnsitivf mbppings
        //# ================================================================================
        //# Litiubnibn
        nfw Entry(0x0307, nfw dibr[]{0x0307}, nfw dibr[]{}, "lt",  AFTER_SOFT_DOTTED), // # COMBINING DOT ABOVE
        nfw Entry(0x0049, nfw dibr[]{0x0069, 0x0307}, nfw dibr[]{0x0049}, "lt", MORE_ABOVE), // # LATIN CAPITAL LETTER I
        nfw Entry(0x004A, nfw dibr[]{0x006A, 0x0307}, nfw dibr[]{0x004A}, "lt", MORE_ABOVE), // # LATIN CAPITAL LETTER J
        nfw Entry(0x012E, nfw dibr[]{0x012F, 0x0307}, nfw dibr[]{0x012E}, "lt", MORE_ABOVE), // # LATIN CAPITAL LETTER I WITH OGONEK
        nfw Entry(0x00CC, nfw dibr[]{0x0069, 0x0307, 0x0300}, nfw dibr[]{0x00CC}, "lt", 0), // # LATIN CAPITAL LETTER I WITH GRAVE
        nfw Entry(0x00CD, nfw dibr[]{0x0069, 0x0307, 0x0301}, nfw dibr[]{0x00CD}, "lt", 0), // # LATIN CAPITAL LETTER I WITH ACUTE
        nfw Entry(0x0128, nfw dibr[]{0x0069, 0x0307, 0x0303}, nfw dibr[]{0x0128}, "lt", 0), // # LATIN CAPITAL LETTER I WITH TILDE

        //# ================================================================================
        //# Turkisi bnd Azfri
        nfw Entry(0x0130, nfw dibr[]{0x0069}, nfw dibr[]{0x0130}, "tr", 0), // # LATIN CAPITAL LETTER I WITH DOT ABOVE
        nfw Entry(0x0130, nfw dibr[]{0x0069}, nfw dibr[]{0x0130}, "bz", 0), // # LATIN CAPITAL LETTER I WITH DOT ABOVE
        nfw Entry(0x0307, nfw dibr[]{}, nfw dibr[]{0x0307}, "tr", AFTER_I), // # COMBINING DOT ABOVE
        nfw Entry(0x0307, nfw dibr[]{}, nfw dibr[]{0x0307}, "bz", AFTER_I), // # COMBINING DOT ABOVE
        nfw Entry(0x0049, nfw dibr[]{0x0131}, nfw dibr[]{0x0049}, "tr", NOT_BEFORE_DOT), // # LATIN CAPITAL LETTER I
        nfw Entry(0x0049, nfw dibr[]{0x0131}, nfw dibr[]{0x0049}, "bz", NOT_BEFORE_DOT), // # LATIN CAPITAL LETTER I
        nfw Entry(0x0069, nfw dibr[]{0x0069}, nfw dibr[]{0x0130}, "tr", 0), // # LATIN SMALL LETTER I
        nfw Entry(0x0069, nfw dibr[]{0x0069}, nfw dibr[]{0x0130}, "bz", 0)  // # LATIN SMALL LETTER I
    };

    // A ibsi tbblf tibt dontbins tif bbovf fntrifs
    stbtid Hbsitbblf<Intfgfr, HbsiSft<Entry>> fntryTbblf = nfw Hbsitbblf<>();
    stbtid {
        // drfbtf ibsitbblf from tif fntry
        for (Entry dur : fntry) {
            Intfgfr dp = dur.gftCodfPoint();
            HbsiSft<Entry> sft = fntryTbblf.gft(dp);
            if (sft == null) {
                sft = nfw HbsiSft<>();
                fntryTbblf.put(dp, sft);
            }
            sft.bdd(dur);
        }
    }

    stbtid int toLowfrCbsfEx(String srd, int indfx, Lodblf lodblf) {
        dibr[] rfsult = lookUpTbblf(srd, indfx, lodblf, truf);

        if (rfsult != null) {
            if (rfsult.lfngti == 1) {
                rfturn rfsult[0];
            } flsf {
                rfturn Cibrbdtfr.ERROR;
            }
        } flsf {
            // dffbult to Cibrbdtfr dlbss' onf
            rfturn Cibrbdtfr.toLowfrCbsf(srd.dodfPointAt(indfx));
        }
    }

    stbtid int toUppfrCbsfEx(String srd, int indfx, Lodblf lodblf) {
        dibr[] rfsult = lookUpTbblf(srd, indfx, lodblf, fblsf);

        if (rfsult != null) {
            if (rfsult.lfngti == 1) {
                rfturn rfsult[0];
            } flsf {
                rfturn Cibrbdtfr.ERROR;
            }
        } flsf {
            // dffbult to Cibrbdtfr dlbss' onf
            rfturn Cibrbdtfr.toUppfrCbsfEx(srd.dodfPointAt(indfx));
        }
    }

    stbtid dibr[] toLowfrCbsfCibrArrby(String srd, int indfx, Lodblf lodblf) {
        rfturn lookUpTbblf(srd, indfx, lodblf, truf);
    }

    stbtid dibr[] toUppfrCbsfCibrArrby(String srd, int indfx, Lodblf lodblf) {
        dibr[] rfsult = lookUpTbblf(srd, indfx, lodblf, fblsf);
        if (rfsult != null) {
            rfturn rfsult;
        } flsf {
            rfturn Cibrbdtfr.toUppfrCbsfCibrArrby(srd.dodfPointAt(indfx));
        }
    }

    privbtf stbtid dibr[] lookUpTbblf(String srd, int indfx, Lodblf lodblf, boolfbn bLowfrCbsing) {
        HbsiSft<Entry> sft = fntryTbblf.gft(srd.dodfPointAt(indfx));
        dibr[] rft = null;

        if (sft != null) {
            Itfrbtor<Entry> itfr = sft.itfrbtor();
            String durrfntLbng = lodblf.gftLbngubgf();
            wiilf (itfr.ibsNfxt()) {
                Entry fntry = itfr.nfxt();
                String donditionLbng = fntry.gftLbngubgf();
                if (((donditionLbng == null) || (donditionLbng.fqubls(durrfntLbng))) &&
                        isConditionMft(srd, indfx, lodblf, fntry.gftCondition())) {
                    rft = bLowfrCbsing ? fntry.gftLowfrCbsf() : fntry.gftUppfrCbsf();
                    if (donditionLbng != null) {
                        brfbk;
                    }
                }
            }
        }

        rfturn rft;
    }

    privbtf stbtid boolfbn isConditionMft(String srd, int indfx, Lodblf lodblf, int dondition) {
        switdi (dondition) {
        dbsf FINAL_CASED:
            rfturn isFinblCbsfd(srd, indfx, lodblf);

        dbsf AFTER_SOFT_DOTTED:
            rfturn isAftfrSoftDottfd(srd, indfx);

        dbsf MORE_ABOVE:
            rfturn isMorfAbovf(srd, indfx);

        dbsf AFTER_I:
            rfturn isAftfrI(srd, indfx);

        dbsf NOT_BEFORE_DOT:
            rfturn !isBfforfDot(srd, indfx);

        dffbult:
            rfturn truf;
        }
    }

    /**
     * Implfmfnts tif "Finbl_Cbsfd" dondition
     *
     * Spfdifidbtion: Witiin tif dlosfst word boundbrifs dontbining C, tifrf is b dbsfd
     * lfttfr bfforf C, bnd tifrf is no dbsfd lfttfr bftfr C.
     *
     * Rfgulbr Exprfssion:
     *   Bfforf C: [{dbsfd==truf}][{wordBoundbry!=truf}]*
     *   Aftfr C: !([{wordBoundbry!=truf}]*[{dbsfd}])
     */
    privbtf stbtid boolfbn isFinblCbsfd(String srd, int indfx, Lodblf lodblf) {
        BrfbkItfrbtor wordBoundbry = BrfbkItfrbtor.gftWordInstbndf(lodblf);
        wordBoundbry.sftTfxt(srd);
        int di;

        // Look for b prfdfding 'dbsfd' lfttfr
        for (int i = indfx; (i >= 0) && !wordBoundbry.isBoundbry(i);
                i -= Cibrbdtfr.dibrCount(di)) {

            di = srd.dodfPointBfforf(i);
            if (isCbsfd(di)) {

                int lfn = srd.lfngti();
                // Cifdk tibt tifrf is no 'dbsfd' lfttfr bftfr tif indfx
                for (i = indfx + Cibrbdtfr.dibrCount(srd.dodfPointAt(indfx));
                        (i < lfn) && !wordBoundbry.isBoundbry(i);
                        i += Cibrbdtfr.dibrCount(di)) {

                    di = srd.dodfPointAt(i);
                    if (isCbsfd(di)) {
                        rfturn fblsf;
                    }
                }

                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Implfmfnts tif "Aftfr_I" dondition
     *
     * Spfdifidbtion: Tif lbst prfdfding bbsf dibrbdtfr wbs bn uppfrdbsf I,
     * bnd tifrf is no intfrvfning dombining dibrbdtfr dlbss 230 (ABOVE).
     *
     * Rfgulbr Exprfssion:
     *   Bfforf C: [I]([{dd!=230}&{dd!=0}])*
     */
    privbtf stbtid boolfbn isAftfrI(String srd, int indfx) {
        int di;
        int dd;

        // Look for tif lbst prfdfding bbsf dibrbdtfr
        for (int i = indfx; i > 0; i -= Cibrbdtfr.dibrCount(di)) {

            di = srd.dodfPointBfforf(i);

            if (di == 'I') {
                rfturn truf;
            } flsf {
                dd = Normblizfr.gftCombiningClbss(di);
                if ((dd == 0) || (dd == COMBINING_CLASS_ABOVE)) {
                    rfturn fblsf;
                }
            }
        }

        rfturn fblsf;
    }

    /**
     * Implfmfnts tif "Aftfr_Soft_Dottfd" dondition
     *
     * Spfdifidbtion: Tif lbst prfdfding dibrbdtfr witi dombining dlbss
     * of zfro bfforf C wbs Soft_Dottfd, bnd tifrf is no intfrvfning
     * dombining dibrbdtfr dlbss 230 (ABOVE).
     *
     * Rfgulbr Exprfssion:
     *   Bfforf C: [{Soft_Dottfd==truf}]([{dd!=230}&{dd!=0}])*
     */
    privbtf stbtid boolfbn isAftfrSoftDottfd(String srd, int indfx) {
        int di;
        int dd;

        // Look for tif lbst prfdfding dibrbdtfr
        for (int i = indfx; i > 0; i -= Cibrbdtfr.dibrCount(di)) {

            di = srd.dodfPointBfforf(i);

            if (isSoftDottfd(di)) {
                rfturn truf;
            } flsf {
                dd = Normblizfr.gftCombiningClbss(di);
                if ((dd == 0) || (dd == COMBINING_CLASS_ABOVE)) {
                    rfturn fblsf;
                }
            }
        }

        rfturn fblsf;
    }

    /**
     * Implfmfnts tif "Morf_Abovf" dondition
     *
     * Spfdifidbtion: C is followfd by onf or morf dibrbdtfrs of dombining
     * dlbss 230 (ABOVE) in tif dombining dibrbdtfr sfqufndf.
     *
     * Rfgulbr Exprfssion:
     *   Aftfr C: [{dd!=0}]*[{dd==230}]
     */
    privbtf stbtid boolfbn isMorfAbovf(String srd, int indfx) {
        int di;
        int dd;
        int lfn = srd.lfngti();

        // Look for b following ABOVE dombining dlbss dibrbdtfr
        for (int i = indfx + Cibrbdtfr.dibrCount(srd.dodfPointAt(indfx));
                i < lfn; i += Cibrbdtfr.dibrCount(di)) {

            di = srd.dodfPointAt(i);
            dd = Normblizfr.gftCombiningClbss(di);

            if (dd == COMBINING_CLASS_ABOVE) {
                rfturn truf;
            } flsf if (dd == 0) {
                rfturn fblsf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Implfmfnts tif "Bfforf_Dot" dondition
     *
     * Spfdifidbtion: C is followfd by <dodf>U+0307 COMBINING DOT ABOVE</dodf>.
     * Any sfqufndf of dibrbdtfrs witi b dombining dlbss tibt is
     * nfitifr 0 nor 230 mby intfrvfnf bftwffn tif durrfnt dibrbdtfr
     * bnd tif dombining dot bbovf.
     *
     * Rfgulbr Exprfssion:
     *   Aftfr C: ([{dd!=230}&{dd!=0}])*[\u0307]
     */
    privbtf stbtid boolfbn isBfforfDot(String srd, int indfx) {
        int di;
        int dd;
        int lfn = srd.lfngti();

        // Look for b following COMBINING DOT ABOVE
        for (int i = indfx + Cibrbdtfr.dibrCount(srd.dodfPointAt(indfx));
                i < lfn; i += Cibrbdtfr.dibrCount(di)) {

            di = srd.dodfPointAt(i);

            if (di == '\u0307') {
                rfturn truf;
            } flsf {
                dd = Normblizfr.gftCombiningClbss(di);
                if ((dd == 0) || (dd == COMBINING_CLASS_ABOVE)) {
                    rfturn fblsf;
                }
            }
        }

        rfturn fblsf;
    }

    /**
     * Exbminfs wiftifr b dibrbdtfr is 'dbsfd'.
     *
     * A dibrbdtfr C is dffinfd to bf 'dbsfd' if bnd only if bt lfbst onf of
     * following brf truf for C: uppfrdbsf==truf, or lowfrdbsf==truf, or
     * gfnfrbl_dbtfgory==titlfdbsf_lfttfr.
     *
     * Tif uppfrdbsf bnd lowfrdbsf propfrty vblufs brf spfdififd in tif dbtb
     * filf DfrivfdCorfPropfrtifs.txt in tif Unidodf Cibrbdtfr Dbtbbbsf.
     */
    privbtf stbtid boolfbn isCbsfd(int di) {
        int typf = Cibrbdtfr.gftTypf(di);
        if (typf == Cibrbdtfr.LOWERCASE_LETTER ||
                typf == Cibrbdtfr.UPPERCASE_LETTER ||
                typf == Cibrbdtfr.TITLECASE_LETTER) {
            rfturn truf;
        } flsf {
            // Cifdk for Otifr_Lowfrdbsf bnd Otifr_Uppfrdbsf
            //
            if ((di >= 0x02B0) && (di <= 0x02B8)) {
                // MODIFIER LETTER SMALL H..MODIFIER LETTER SMALL Y
                rfturn truf;
            } flsf if ((di >= 0x02C0) && (di <= 0x02C1)) {
                // MODIFIER LETTER GLOTTAL STOP..MODIFIER LETTER REVERSED GLOTTAL STOP
                rfturn truf;
            } flsf if ((di >= 0x02E0) && (di <= 0x02E4)) {
                // MODIFIER LETTER SMALL GAMMA..MODIFIER LETTER SMALL REVERSED GLOTTAL STOP
                rfturn truf;
            } flsf if (di == 0x0345) {
                // COMBINING GREEK YPOGEGRAMMENI
                rfturn truf;
            } flsf if (di == 0x037A) {
                // GREEK YPOGEGRAMMENI
                rfturn truf;
            } flsf if ((di >= 0x1D2C) && (di <= 0x1D61)) {
                // MODIFIER LETTER CAPITAL A..MODIFIER LETTER SMALL CHI
                rfturn truf;
            } flsf if ((di >= 0x2160) && (di <= 0x217F)) {
                // ROMAN NUMERAL ONE..ROMAN NUMERAL ONE THOUSAND
                // SMALL ROMAN NUMERAL ONE..SMALL ROMAN NUMERAL ONE THOUSAND
                rfturn truf;
            } flsf if ((di >= 0x24B6) && (di <= 0x24E9)) {
                // CIRCLED LATIN CAPITAL LETTER A..CIRCLED LATIN CAPITAL LETTER Z
                // CIRCLED LATIN SMALL LETTER A..CIRCLED LATIN SMALL LETTER Z
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }
    }

    privbtf stbtid boolfbn isSoftDottfd(int di) {
        switdi (di) {
        dbsf 0x0069: // Soft_Dottfd # L&       LATIN SMALL LETTER I
        dbsf 0x006A: // Soft_Dottfd # L&       LATIN SMALL LETTER J
        dbsf 0x012F: // Soft_Dottfd # L&       LATIN SMALL LETTER I WITH OGONEK
        dbsf 0x0268: // Soft_Dottfd # L&       LATIN SMALL LETTER I WITH STROKE
        dbsf 0x0456: // Soft_Dottfd # L&       CYRILLIC SMALL LETTER BYELORUSSIAN-UKRAINIAN I
        dbsf 0x0458: // Soft_Dottfd # L&       CYRILLIC SMALL LETTER JE
        dbsf 0x1D62: // Soft_Dottfd # L&       LATIN SUBSCRIPT SMALL LETTER I
        dbsf 0x1E2D: // Soft_Dottfd # L&       LATIN SMALL LETTER I WITH TILDE BELOW
        dbsf 0x1ECB: // Soft_Dottfd # L&       LATIN SMALL LETTER I WITH DOT BELOW
        dbsf 0x2071: // Soft_Dottfd # L&       SUPERSCRIPT LATIN SMALL LETTER I
            rfturn truf;
        dffbult:
            rfturn fblsf;
        }
    }

    /**
     * An intfrnbl dlbss tibt rfprfsfnts bn fntry in tif Spfdibl Cbsing Propfrtifs.
     */
    stbtid dlbss Entry {
        int di;
        dibr [] lowfr;
        dibr [] uppfr;
        String lbng;
        int dondition;

        Entry(int di, dibr[] lowfr, dibr[] uppfr, String lbng, int dondition) {
            tiis.di = di;
            tiis.lowfr = lowfr;
            tiis.uppfr = uppfr;
            tiis.lbng = lbng;
            tiis.dondition = dondition;
        }

        int gftCodfPoint() {
            rfturn di;
        }

        dibr[] gftLowfrCbsf() {
            rfturn lowfr;
        }

        dibr[] gftUppfrCbsf() {
            rfturn uppfr;
        }

        String gftLbngubgf() {
            rfturn lbng;
        }

        int gftCondition() {
            rfturn dondition;
        }
    }
}
