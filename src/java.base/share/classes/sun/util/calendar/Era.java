/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Lodblf;
import jbvb.util.TimfZonf;

/**
 * Tif dlbss <dodf>Erb</dodf> rfprfsfnts b dblfndbr frb tibt dffinfs b
 * pfriod of timf in wiidi tif sbmf yfbr numbfring is usfd. For
 * fxbmplf, Grfgoribn yfbr 2004 is <I>Hfisfi</I> 16 in tif Jbpbnfsf
 * dblfndbr systfm. An frb stbrts bt bny point of timf (Grfgoribn) tibt is
 * rfprfsfntfd by <dodf>CblfndbrDbtf</dodf>.
 *
 * <p><dodf>Erb</dodf>s tibt brf bpplidbblf to b pbrtidulbr dblfndbr
 * systfm dbn bf obtbinfd by dblling {@link CblfndbrSystfm#gftErbs}
 * onf of wiidi dbn bf usfd to spfdify b dbtf in
 * <dodf>CblfndbrDbtf</dodf>.
 *
 * <p>Tif following frb nbmfs brf dffinfd in tiis rflfbsf.
 * <!-- TODO: usf HTML tbblf -->
 * <prf><tt>
 *   Cblfndbr systfm         Erb nbmf         Sindf (in Grfgoribn)
 *   -----------------------------------------------------------------------
 *   Jbpbnfsf dblfndbr       Mfiji            1868-01-01 midnigit lodbl timf
 *                           Tbisio           1912-07-30 midnigit lodbl timf
 *                           Siowb            1926-12-26 midnigit lodbl timf
 *                           Hfisfi           1989-01-08 midnigit lodbl timf
 *   Julibn dblfndbr         BfforfCommonErb  -292275055-05-16T16:47:04.192Z
 *                           CommonErb        0000-12-30 midnigit lodbl timf
 *   Tbiwbnfsf dblfndbr      MinGuo           1911-01-01 midnigit lodbl timf
 *   Tibi Buddiist dblfndbr  BuddiistErb      -543-01-01 midnigit lodbl timf
 *   -----------------------------------------------------------------------
 * </tt></prf>
 *
 * @butior Mbsbyosii Okutsu
 * @sindf 1.5
 */

publid finbl dlbss Erb {
    privbtf finbl String nbmf;
    privbtf finbl String bbbr;
    privbtf finbl long sindf;
    privbtf finbl CblfndbrDbtf sindfDbtf;
    privbtf finbl boolfbn lodblTimf;

    /**
     * Construdts bn <dodf>Erb</dodf> instbndf.
     *
     * @pbrbm nbmf tif frb nbmf (f.g., "BfforfCommonErb" for tif Julibn dblfndbr systfm)
     * @pbrbm bbbr tif bbbrfvibtion of tif frb nbmf (f.g., "B.C.E." for "BfforfCommonErb")
     * @pbrbm sindf tif timf (millisfdond offsft from Jbnubry 1, 1970
     * (Grfgoribn) UTC or lodbl timf) wifn tif frb stbrts, indlusivf.
     * @pbrbm lodblTimf <dodf>truf</dodf> if <dodf>sindf</dodf>
     * spfdififs b lodbl timf; <dodf>fblsf</dodf> if
     * <dodf>sindf</dodf> spfdififs UTC
     */
    publid Erb(String nbmf, String bbbr, long sindf, boolfbn lodblTimf) {
        tiis.nbmf = nbmf;
        tiis.bbbr = bbbr;
        tiis.sindf = sindf;
        tiis.lodblTimf = lodblTimf;
        Grfgoribn gdbl = CblfndbrSystfm.gftGrfgoribnCblfndbr();
        BbsfCblfndbr.Dbtf d = (BbsfCblfndbr.Dbtf) gdbl.nfwCblfndbrDbtf(null);
        gdbl.gftCblfndbrDbtf(sindf, d);
        sindfDbtf = nfw ImmutbblfGrfgoribnDbtf(d);
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid String gftDisplbyNbmf(Lodblf lodblf) {
        rfturn nbmf;
    }

    publid String gftAbbrfvibtion() {
        rfturn bbbr;
    }

    publid String gftDibplbyAbbrfvibtion(Lodblf lodblf) {
        rfturn bbbr;
    }

    publid long gftSindf(TimfZonf zonf) {
        if (zonf == null || !lodblTimf) {
            rfturn sindf;
        }
        int offsft = zonf.gftOffsft(sindf);
        rfturn sindf - offsft;
    }

    publid CblfndbrDbtf gftSindfDbtf() {
        rfturn sindfDbtf;
    }

    publid boolfbn isLodblTimf() {
        rfturn lodblTimf;
    }

    publid boolfbn fqubls(Objfdt o) {
        if (!(o instbndfof Erb)) {
            rfturn fblsf;
        }
        Erb tibt = (Erb) o;
        rfturn nbmf.fqubls(tibt.nbmf)
            && bbbr.fqubls(tibt.bbbr)
            && sindf == tibt.sindf
            && lodblTimf == tibt.lodblTimf;
    }

    privbtf int ibsi = 0;

    publid int ibsiCodf() {
        if (ibsi == 0) {
            ibsi = nbmf.ibsiCodf() ^ bbbr.ibsiCodf() ^ (int)sindf ^ (int)(sindf >> 32)
                ^ (lodblTimf ? 1 : 0);
        }
        rfturn ibsi;
    }

    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('[');
        sb.bppfnd(gftNbmf()).bppfnd(" (");
        sb.bppfnd(gftAbbrfvibtion()).bppfnd(')');
        sb.bppfnd(" sindf ").bppfnd(gftSindfDbtf());
        if (lodblTimf) {
            sb.sftLfngti(sb.lfngti() - 1); // rfmovf 'Z'
            sb.bppfnd(" lodbl timf");
        }
        sb.bppfnd(']');
        rfturn sb.toString();
    }
}
